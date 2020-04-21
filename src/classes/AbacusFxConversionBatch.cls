/*
* Cognizant : 21/11/2013 ==
* AbacusFxConversionBatch: Updates Conversion Ration on Corp Bookings based on Opportunity Currecny ISO code, ===
*                          and Corp Booking Close Date
* Change log: 10/4/2015: Changes Added by VCHOUHAN for Infinity Enhancements for storing Plan Fx Rate on Opportunity
* Dinesh(Project OLIS - 01/08/2015): Added the execute code for OLISRates batch and modified the finish methid of batch to schedule OLIS batch once the OpportunityRates batch is completed
* Haritha - Fixing OLIS rates batch being called everytime - case # 05187576
* Ankur(Golden Gate SFDC-1268 - 17th Feb 2017): Added the execute code for CLISRates batch to process all CLIS records.
* Vivek - Check for CICD
* Vivek - Test commit CICD
* Test commit
*/

global class AbacusFxConversionBatch implements Database.Batchable<sObject>, Database.Stateful,Database.AllowsCallouts {
    public String query;
    public List<String> errorList;
    public Map<String,List<String>> currencyDate;
    public Date lowestDate;
    public List<Corp_Booking__c> corpBookings;
    public List<Opportunity> opportunities;
    public Set<String> currencyDateKeyset;
    public Id feedItemId;
    public String fileName;
    public String batchName;
    public String sessionId;
    public Boolean isPlannedFxRate;
    public List<Opportunity_Line_Item_Schedule__c> opportunityLineItemScheduleList;
    public List<Contract_Line_Item_Schedule__c> contractLineItemScheduleList;

    //Constructor for Dated Exchange Rates, Corp Booking and Opprtunity records
    global AbacusFxConversionBatch(String q, Map<String,List<String>> currencyDate, Date lowestDate, List<String> errorList,
                                   Id feedItemId,String fileName,String batchName, Boolean isPlannedFxRate, String sessionId){
        this.query = q;
        this.currencyDate = currencyDate;  
        this.currencyDateKeyset =  currencyDate.keyset();
        this.lowestDate = lowestDate; 
        this.feedItemId = feedItemId;
        this.fileName = fileName;
        this.errorList = (errorList != null && errorList.size()>0) ? errorList : new List<String>();
        this.batchName = batchName;
        this.isPlannedFxRate = isPlannedFxRate;
        this.sessionId = sessionId;
    }
    
    global database.querylocator start(Database.BatchableContext BC){
    	return Database.getQueryLocator(query);
    }

    global void execute(Database.BatchableContext BC, List<sObject> scope){ 
        if(batchName == 'CorpBooking'){
            corpBookings = new List<Corp_Booking__c>();
            for(Corp_Booking__c corpBooking : (List<Corp_Booking__c>)scope){
                if(currencyDate.get(corpBooking.Opportunity__r.CurrencyIsoCode) != null){
                    List<String> corpBookingDetails = currencyDate.get(corpBooking.Opportunity__r.CurrencyIsoCode);
                    if(corpBookingDetails.size() == 2){
                        Decimal fxRate = Decimal.valueOf(corpBookingDetails[0]);
                        Date startDate = DateTime.valueOf(corpBookingDetails[1]).date();
                        if(corpBooking.Corp_Close_Date__c >= startDate){
                            if(isPlannedFxRate){
                                //update Plan_Fx_Rate__c field if planned exchange rates are posted
                                corpBooking.Plan_Fx_Rate__c = fxRate;
                            }
                            else{
                            	//update Actual_Fx_Rate__c field if actual exchange rates are posted
                                corpBooking.Actual_Fx_Rate__c = fxRate;
                            }
                            corpBookings.add(corpBooking);
                        }
                    }
                }
            }
            
            // Update data
            if(!corpBookings.isEmpty()){
                errorList.addAll(GeneralUtil.updateRecords(corpBookings, 'AbacusFxConversionBatch', 'execute-corpBookings', 'High'));
            }
        }
        else if(batchName == 'OpportunityRates'){
            opportunities = new List<Opportunity>();
            for(Opportunity Oppty : (List<Opportunity>)scope){
                if(currencyDate.containskey(Oppty.CurrencyIsoCode)){
                   Datetime dt = DateTime.valueOf(currencyDate.get(Oppty.CurrencyIsoCode)[1]);
                   Date fxStartDate = dt.date();
                   if(Oppty.CloseDate >= fxStartDate){
                      //Oppty.Actual_Fx_Rate__c =  Double.ValueOf(currencyDate.get(Oppty.CurrencyIsoCode)[0]);
                      //Changes Added by VCHOUHAN for Infinity Enhancements for storing Plan Fx Rate on Opportunity
                       if(isPlannedFxRate){
                            //update Plan_Fx_Rate__c field if planned exchange rates are posted
                            Oppty.Plan_Fx_Rate__c = Double.ValueOf(currencyDate.get(Oppty.CurrencyIsoCode)[0]);
                        }
                        else{
                            //update Actual_Fx_Rate__c field if actual exchange rates are posted
                      		Oppty.Actual_Fx_Rate__c =  Double.ValueOf(currencyDate.get(Oppty.CurrencyIsoCode)[0]);
                        }
                      opportunities.add(Oppty);
                   }
                }    
            }
            // Update data
            if(!opportunities.isEmpty()){
                errorList.addAll(GeneralUtil.updateRecords(opportunities, 'AbacusFxConversionBatch', 'execute-opportunities', 'High'));
            }
        }
        else if(batchName == 'CurrencyType'){
            for(CurrencyType conversionRate : (List<CurrencyType>)scope){
                if(currencyDate.containskey(conversionRate.IsoCode)){
                	
                	//create http request for CurrencyType with the ISO code and conversion rate.
                    Http h = new Http();
                    HttpRequest req = new HttpRequest();
                    req.setEndpoint(URL.getSalesforceBaseUrl().toExternalForm() + '/services/data/v29.0/sobjects/CurrencyType/'+conversionRate.Id+'?_HttpMethod=PATCH');
                    req.setBody('{ "ConversionRate" : '+currencyDate.get(conversionRate.IsoCode)[0]+' }');
                    req.setHeader('Authorization', 'OAuth ' + sessionId);
                    req.setHeader('Content-Type', 'application/json');
                    req.setMethod('POST');
                    try{
                    	//get the response for the request sent.
		                HttpResponse res = h.send(req);
		                if(res.getStatusCode() != 201 && res.getStatusCode() != 204){
		                	//if failure, add exception to the error list.
		                    errorList.add(conversionRate.IsoCode+','+ currencyDate.get(conversionRate.IsoCode)[0] +','+ currencyDate.get(conversionRate.IsoCode)[1] +','+res.getStatus());
		                }
		            }
		            catch(CalloutException e){//Exception handling
		            	//if there is any exception, add exception to the error list.
		                errorList.add(conversionRate.IsoCode+','+ currencyDate.get(conversionRate.IsoCode)[0] +','+ currencyDate.get(conversionRate.IsoCode)[1] +','+e.getMessage());
		            }
                }    
            }
        }
        /* Project OLIS changes starts here */
        //check if the batchName is 'OLISRates'
        else if(batchName == 'OLISRates'){
        	if(isPlannedFxRate){// Run this logic only if the isPlannedFxRate is true
            opportunityLineItemScheduleList = new List<Opportunity_Line_Item_Schedule__c>();
            //Iterate over the OLIS context records
            for(Opportunity_Line_Item_Schedule__c opportunityLineItemScheduleObj : (List<Opportunity_Line_Item_Schedule__c>)scope){
                //If the CurrencyIsoCode of Opportunity assciated to OLIS record is not null
                if(currencyDate.get(opportunityLineItemScheduleObj.Opportunity__r.CurrencyIsoCode) != null){
                    List<String> olisDetails = currencyDate.get(opportunityLineItemScheduleObj.Opportunity__r.CurrencyIsoCode);
                    if(olisDetails.size() == 2){
                        //Store the Fx Rate from posted Chatter file
                        Decimal fxRate = Decimal.valueOf(olisDetails[0]);
                        //Store the Start Date from posted Chatter file
                        Date startDate = DateTime.valueOf(olisDetails[1]).date();
                        //If the Start Date associated to OLIS is greater than or equal to the Start Date from posted Chatter file and isPlannedFxRate is true
                        if(opportunityLineItemScheduleObj.Start_Date__c >= startDate){
                            //Update Plan Fx Rate field of OLIS record if planned exchange rates are posted from Chatter
                            opportunityLineItemScheduleObj.Plan_Fx_Rate__c = fxRate;
                            opportunityLineItemScheduleList.add(opportunityLineItemScheduleObj);
                        }
                    }
                }
            }
            //Update Opportunity Line Item Schedule records
            if(!opportunityLineItemScheduleList.isEmpty()){
                errorList.addAll(GeneralUtil.updateRecords(opportunityLineItemScheduleList, 'AbacusFxConversionBatch', 'execute-OLIS', 'High'));
            }
          }
        }
        /* Project OLIS changes ends here */
        /* SFDC-1268: CLIS batch changes starts here */
        else if(batchName == 'CLISRates'){
            if(isPlannedFxRate){// Run this logic only if the isPlannedFxRate is true
            contractLineItemScheduleList = new List<Contract_Line_Item_Schedule__c>();
            //Iterate over the CLIS context records
            for(Contract_Line_Item_Schedule__c contractLineItemScheduleObj : (List<Contract_Line_Item_Schedule__c>)scope){
                //If the CurrencyIsoCode of CLIS record is not null
                if(currencyDate.get(contractLineItemScheduleObj.CurrencyIsoCode) != null){
                    List<String> clisDetails = currencyDate.get(contractLineItemScheduleObj.CurrencyIsoCode);
                    if(clisDetails.size() == 2){
                        //Store the Fx Rate from posted Chatter file
                        Decimal fxRate = Decimal.valueOf(clisDetails[0]);
                        //Store the Start Date from posted Chatter file
                        Date startDate = DateTime.valueOf(clisDetails[1]).date();
                        //If the Start Date associated to CLIS is greater than or equal to the Start Date from posted Chatter file and isPlannedFxRate is true
                        if(contractLineItemScheduleObj.Start_Date__c >= startDate){
                            //Update Plan Fx Rate field of CLIS record if planned exchange rates are posted from Chatter
                            contractLineItemScheduleObj.Plan_Fx_Rate__c = fxRate;
                            contractLineItemScheduleList.add(contractLineItemScheduleObj);
                        }
                    }
                }
            }
            //Update Opportunity Line Item Schedule records
            if(!contractLineItemScheduleList.isEmpty()){
                errorList.addAll(GeneralUtil.updateRecords(contractLineItemScheduleList, 'AbacusFxConversionBatch', 'execute-CLIS', 'High'));
            }
          }
        }
    }
    global void finish(Database.BatchableContext BC){ 
        try
        {
            GeneralUtil.debug('AbacusFxConversionBatch -finish() - errorList', '' + errorList);
            /* Project OLIS changes starts here */
            
            //OLIS: Query the Status of AsyncApexJob record for the current batch execution 
            AsyncApexJob aSyncApexJobObj = [SELECT Id, Status FROM AsyncApexJob WHERE Id=: BC.getJobId()];
            
            //OLIS: If the current batch is 'OpportunityRates' and Status of AsycnApexJob is 'Completed' then schedule the OLIS batch. This approach is implemented to prevent record lock issue of Opportunity records
            if(batchname == 'OpportunityRates' && aSyncApexJobObj.Status == 'Completed' && isPlannedFxRate){
                //Batch for OLIS to update Planned Exchange Rates
                String olisQuery = 'SELECT Id, Plan_Fx_Rate__c, Start_Date__c, Opportunity__r.CurrencyIsoCode ' +
                                            'FROM Opportunity_Line_Item_Schedule__c WHERE Start_Date__c >= :lowestDate ' + 
                                            'AND Opportunity__r.CurrencyIsoCode IN :currencyDateKeyset';
                                            
                AbacusFxConversionBatch olisFxBatch = new AbacusFxConversionBatch(olisQuery, currencyDate, lowestDate, errorList, feedItemId, fileName, 'OLISRates', true, Userinfo.getSessionId());
                String olisJobId = Database.executeBatch(olisFxBatch);
            }
            /* Project OLIS changes ends here */
            if(errorList.isEmpty() || errorList[0] == 'TEST'){
            	//if all records successfully updated, send a mail and post a chatter comment.
                GeneralUtil.addFeedComment(feedItemId, batchName + ' - All records successfully updated!');
                GeneralUtil.sendEmail(new list<String> {UserInfo.getUserEmail()},'Upload Success - ' + fileName,'All records successfully updated!');          
            }  
            else{ 
            	//if there are failures, send a mail and post as a chatter comment with csv file.
                GeneralUtil.sendEmail(new list<String> {UserInfo.getUserEmail()},'Upload Failure - ' + fileName,'Some records failed to upload. Please check chatter comment.');          
                
                // create the error file
                String header ='Error \n'; 
                String finalString = header + String.join(errorList, '\n');
                Blob csvBlob = Blob.valueof(finalString);
                ContentVersion cc= new ContentVersion( versionData = csvBlob, Title = fileName + '_Failure.csv', PathOnClient = fileName + '_Failure.csv', Origin = 'H');                       
                insert cc;
                
                // post error file to chatter
                insert new FeedComment(RelatedRecordId = cc.id,feedItemId = feedItemId, CommentType = 'ContentComment', 
                                        CommentBody=batchName + ' - Some records failed to upload, please find attached is the failure report.');
            }   
        }
        catch(Exception exObj)
        {   
        	//if there is any exception, add feedComment with the exception details.
            GeneralUtil.addFeedComment(feedItemId, 'There is error in upload process, please contact system admin. - ' + exObj + exObj.getStackTraceString());
        }
    }
}