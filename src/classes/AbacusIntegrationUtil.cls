/**
* @author       Nalin Kumar Srivastava
* @date         07/20/2016
* @description  Purpose: Contains invocable method for Process builder 
*                        and @future method for asynchronous processing for CAC-SFDC Integration.
*/ 
public without sharing class AbacusIntegrationUtil {
    
    //public static List<id> addrecs{get;set;} 
    private static Map<Id,Id> oppRenewalMap{get;set;}
    
    public class OrderCompleteRequest{
        @InvocableVariable
        public  Integration_Log__c integrationRec;
    }
    
    /**
    * @author       Nalin Kumar Srivastava
    * @date         07/20/2016
    * @description  This invocable method is called from Process Builder. Integration log records ids are passed to future method for processing.
    * @param        orderCompleteRequests, List of wrapper class OrderCompleteRequest (Integration_Log__c records)
    */ 
    @InvocableMethod(label='CAC Order Complete Integration' description='Order Complete integration - to update opportunity, create quote & handoff notes and update contract')
    public static void fetchDataAndInvoke(List<OrderCompleteRequest> orderCompleteRequests){
        
        //set of Integration_Log__c record ids
        Set<Id> integrationLogRecIds= new set<Id>();
        
        for(OrderCompleteRequest o : orderCompleteRequests){ 
            integrationLogRecIds.add(o.integrationRec.Id);
        }
        
        //pass integration log record ids to @future method for processing
        processAsynchronousRecords(integrationLogRecIds);
        
    }
    
    /**
    * @author       Nalin Kumar Srivastava
    * @date         07/20/2016
    * @description  Asynchronous method which will take care of invoking all sub processes like Quote/renewal Oppty creation
    * @param        integrationRecIds, Set of Integration_Log__c record ids
    */
    @future
    public static void processAsynchronousRecords(Set<Id> integrationRecIds) {

        //to keep list of exception/errors occured
        String[] strErrorList = null;
        //to keep list of opportunities whose status to be updated to 'Closed Won'
        List<Opportunity> listUpdateClosedWon = new List<Opportunity>();
        //to keep list of opportunities to be updated as Order Complete
        List<Opportunity> listUpdateOrderComplete = new List<Opportunity>();
        //to keep list of quote created
        List<SBQQ__Quote__c> listUpdatePrimaryQuote = new List<SBQQ__Quote__c>();
        //to keep list of renewal opportunities to be created
        List<Opportunity> listRenewalOpp = new List<Opportunity>();
        //to keep list of handoff notes to be created
        List<SObject> listHandOffNotes = new List<SObject>();
        //to keep list of integration log records to be updated with complete/error
        List<Integration_Log__c> listIntegrationLogs = new List<Integration_Log__c>();
        //set of opportunity ids from integration logs' Record_Id__c field
        Set<Id> oppIds = new Set<Id>();
        
        //store Database Save Point so that we can revert back in case of any exception
        Savepoint sp = Database.setSavepoint();
        
        try {

            //Fetch Integration Log records using passed ids
            List<Integration_Log__c> integrationLogRecs = Database.query(Label.AbacusIntegrationUtil_IntegrationRecordsQuery);
            
            //get opportunity ids from integraion log recods
            for(Integration_Log__c rec : integrationLogRecs){
                oppIds.add(rec.Record_Id__c);
            }
            
            //query opportunities
            List<Opportunity> oppRecs = Database.query(Label.AbacusIntegrationUtil_OppecordsQuery);
            //prepare opportunity map
            Map<Id, Opportunity> mapOppRecs = new Map<Id, Opportunity>(oppRecs);
            
            //get custom setting records with details of whether to trigger particular (process builder) process.
            Map<String,InvokeOrderComplete__c> mcs = InvokeOrderComplete__c.getAll();
        
            //Invoke Sub Processes
            for(Integration_Log__c rec : integrationLogRecs){
                
                //Only process Abacus Opportunity (Opportunity.Is_Abacus_Opportunity__c = true)
                if(isAbacusOpportunity(mapOppRecs.get(rec.Record_Id__c))) {

                    //check whether to create Quick Quotes
                    if(mcs.get('CreateQuickQuote').InvokeProcess__c){
                        Id quoteId = quickQuote(rec.Record_Id__c);
                        listUpdatePrimaryQuote.add(new SBQQ__Quote__c(Id = quoteId, SBQQ__Primary__c = true));
                    }

                    //check whether to create Handoff Note records by parsing JSON field
                    if(mcs.get('CreateHandoffNotes').InvokeProcess__c){
                        createHandoffNotes(rec);
                    }

                    //check whether to update Opportunity record (mark them as Cloesd Won)
                    if(mcs.get('UpdateOpportunity').InvokeProcess__c){
                        listUpdateClosedWon.add(updateOpportunity(mapOppRecs.get(rec.Record_Id__c)));
                    }
                }     
            }
            //mark Quote as Primary
            if(!listUpdatePrimaryQuote.isEmpty()){
                checkError(GeneralUtil.updateRecords(listUpdatePrimaryQuote, null, null, null, false));
            }
            //mark opportunities 'Closed Won'
            if(!listUpdateClosedWon.isEmpty()){
                checkError(GeneralUtil.updateRecords(listUpdateClosedWon, null, null, null, false));
            }
            //loop through integration log recods
            for(Integration_Log__c rec : integrationLogRecs) {

                //Only process Abacus Opportunity (Opportunity.Is_Abacus_Opportunity__c = true)
                if(isAbacusOpportunity(mapOppRecs.get(rec.Record_Id__c))) {

                    //check whether to create Renewal Opportunities
                    if(mcs.get('CreateRenewalOpportunity').InvokeProcess__c){

                        Opportunity renewalOppty = createRenewalOpportunity(rec.Record_Id__c);
                        listRenewalOpp.add(renewalOppty);
                    }
                }    
            }
            
            //populate info required for renewal opportunity
            List<opportunity> updateOppAfterRenewal = new List<opportunity>();
            if(!listRenewalOpp.isEmpty()){
                updateOppAfterRenewal = populateRenewalOppInfo(listRenewalOpp);
            }
            
            for(Integration_Log__c rec : integrationLogRecs) {

                //Only process Abacus Opportunity (Opportunity.Is_Abacus_Opportunity__c = true)
                if(isAbacusOpportunity(mapOppRecs.get(rec.Record_Id__c))) {

                    //Mark Opportunity as Order complete after Renewal
                    if(mcs.get('UpdateOrderComplete').InvokeProcess__c) {
                        listUpdateOrderComplete.add(updateOrderComplete(mapOppRecs.get(rec.Record_Id__c), oppRenewalMap.get(rec.Record_Id__c)));
                    }
                }
                //rec.Status__c='Complete';   
                listIntegrationLogs.add(new Integration_Log__c(Id = rec.Id, Status__c = 'Complete'));
            }
            
            if(!updateOppAfterRenewal.isEmpty()){
                checkError(GeneralUtil.updateRecords(updateOppAfterRenewal, null, null, null, false));
            }    
            if(!listUpdateOrderComplete.isEmpty()){
                checkError(GeneralUtil.updateRecords(listUpdateOrderComplete, null, null, null, false));
            }
            if(!listIntegrationLogs.isEmpty()){
                checkError(GeneralUtil.updateRecords(listIntegrationLogs, null, null, null, false));
            }
            
        }
        catch(exception ex) {

            Database.rollback(sp);

            Integer errorMessageFieldSize = Integration_Log__c.Error_Message__c.getDescribe().getLength();
            Integer errorMessageLongFieldSize = Integration_Log__c.Error_Message_Long__c.getDescribe().getLength();

            listIntegrationLogs.clear();
            for(String logId : integrationRecIds) {

                String errMsg = 'Line#' + String.valueOf(ex.getLineNumber()) + ' | ' + ex.getMessage();

                Integration_Log__c log = new Integration_Log__c(Id = logId);
                log.Status__c = 'Error';
                log.Error_Message__c = errMsg.left(errorMessageFieldSize);
                log.Error_Message_Long__c = errMsg.left(errorMessageLongFieldSize);
                listIntegrationLogs.add(log);
            }

            GeneralUtil.updateRecords(listIntegrationLogs, null, null, null, false);
        }
    }
    
    //Creation of handoff notes from JSON data
    private static void createHandoffNotes(Integration_Log__c rec) {
        //leverage custom JSON Parser - parse JSON to Note__c sObject and insert into database
        JSONParserUtil.parseAndUpsert(rec.JSONField__c);
    }
    
    //Create renewal opportunity
    private static Opportunity createRenewalOpportunity(Id opportunityId) {
        //leveraging existing code and using parameter is_automatic=true
        OpportunityRenewalControllerExtension renewal_controller = new OpportunityRenewalControllerExtension(opportunityId, true);

        //Neel - ABACUS-1255 - If renewal opportunity did not created due to some exception, then pass that exception to this class to capture it on integration log record
        if(!renewal_controller.listExceptions.isEmpty()) {
            throw new AbacusIntegrationUtilException(renewal_controller.listExceptions[0]);
        }
        
        return renewal_controller.renewal;
    }
    
    //Update opportunity to Closed Won
    private static Opportunity updateOpportunity(Opportunity oppRec) {
        //use custom label for Closed Won stage value
        oppRec.StageName = Label.Oppportunity_closed_won_stage_name; //'Closed Won' stage
        return oppRec;    
    }
    
    //Mark opportunity as Order Complete
    private static Opportunity updateOrderComplete(Opportunity oppRec, Id renewalId) {
        //set opportunity field values
        oppRec.Renewal_Opportunity__c = renewalId;
        oppRec.Order_Complete__c=true;
        return oppRec;    
    }
    
    //Create quick quote for the opportunity
    private static Id quickQuote(Id opportunityId) {
        //leveraging QuickQuoteController's web service method
        String str = QuickQuoteController.quickQuote(opportunityId);
        String[] retURL = str.split('&id=');
        return (retURL != null && retURL.size() > 0) ? retURL[1] : null;
    }
    
    //Check if opportunity is Abacus opportunity
    private static boolean isAbacusOpportunity(Opportunity opp) {
        //check Abacus flag
        return opp.Is_Abacus_Opportunity__c ;
    }
    
    //populate Renewal Opportunity information
    private static List<Opportunity> populateRenewalOppInfo(List<Opportunity> listOpp) {

        List<Opportunity> listOppRecs = new List<Opportunity>();  
        Set<Id> oppIds = new Set<Id>();
        //initialize map
        oppRenewalMap = new Map<Id,Id>();
        
        for(Opportunity opp : listOpp){
            oppIds.add(opp.Id);
        }
        
        //query opportunity records
        List<Opportunity> queryOppRecs = Database.query(Label.AbacusIntegrationUtil_OppecordsQuery);
        
        for(Opportunity opp : queryOppRecs) {
            //set Abacus flag  
            opp.Is_Abacus_Opportunity__c = true;
            listOppRecs.add(opp);
            oppRenewalMap.put(opp.Originating_Opportunity__c, opp.Id);
        }

        return listOppRecs;
    }

    //method to handle exception
    @testVisible private static void checkError(String[] strErrorList) {

        if(Test.isRunningTest() && strErrorList != null && !strErrorList.isEmpty() && strErrorList[0] == 'TEST') {
            strErrorList.remove(0); //remove 'TEST' added by GeneralUtil method when test class is running
        }

        if(strErrorList != null && !strErrorList.isEmpty()) {

            throw new AbacusIntegrationUtilException(String.join(strErrorList, '\n'));
        }
        
    }

    //Exception class for throwing custom exception
    public class AbacusIntegrationUtilException extends Exception { }
}