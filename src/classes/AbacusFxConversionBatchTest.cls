/**
*   Cognizant - 25 Nov 2013
*   Test methods for AbacusFxConversionBatch, CorpBookUtil and FeedItemUtil class methods
*    
*   NKamadolli - 21 Aug 2014 - Splitting up test methods into individual methods
*   Dinesh(Project OLIS - 01/08/2015): Added testOLISPlanFxRate method to test Plan Fx Rate on OLIS is updated based on  file posted on Abacus Chatter group
*   Ankur(Project GoldenGate - 28/02/2017): Added testCLISPlanFxRate method to test Plan Fx Rate on CLIS is updated based on file posted on Abacus Chatter group
**/
@isTest
global class AbacusFxConversionBatchTest implements HttpCalloutMock{    
    static Account[] accounts = new Account[0];
    static Opportunity oppty;
    static Opportunity lmsGGOppty;
    static Corp_Booking__c corpBooking;
    static List<Product2> lmsProductList;
    
    //To create test records
    @testSetup static void create_test_records(){
        insert new OverrideTrigger__c(Name = 'FeedItemTrigger');
        accounts = TestingUtils.createAccounts(1, 'Parent', TestingUtils.rType);
        insert accounts;
        
        //test record for opportunity
        oppty = TestingUtils.createOpportunity(accounts[0].Id, Label.RT_CorpSolutions, 'New Business', 'CAD', 'Solution Overview', 10, date.parse('11/30/2018'));        
        insert oppty;
        
        //test record for corp booking
        corpBooking = new Corp_Booking__c(Name = 'CB-2012-12-Lc528AAB',
                                                          Amount__c = 200,
                                                          CurrencyIsoCode = 'CAD',
                                                          Corp_Close_Date__c = date.parse('11/30/2018'),
                                                          Opportunity__c = oppty.Id);
        insert corpBooking;

        //Create an Opportunity record to be associated to Quote
        lmsGGOppty = TestingUtils.createOpportunity(accounts[0].Id, Label.RT_MS_GG_Oppty_RecordType, 'New Business', 'USD', 'Discovery (5%)', 12, System.today());
        lmsGGOppty.PriceBook2Id = Test.getStandardPricebookId();
        lmsGGOppty.Credit_Check_Status2__c = 'Approve';
        insert lmsGGOppty;

        //Insert LMS Products
        Id LMSProductRecordType = [SELECT Id FROM RecordType WHERE DeveloperName = 'MS_Product' LIMIT 1].Id;
                
        lmsProductList = TestingUtils.createProducts(1);
        lmsProductList[0].ProductCode = 'TestCode1';
        lmsProductList[0].RecordTypeId = LMSProductRecordType;
        lmsProductList[0].CMT_Product__c = true;
        lmsProductList[0].SBQQ__SubscriptionPricing__c = 'Fixed Price';
        lmsProductList[0].SBQQ__SubscriptionTerm__c = 12;
        lmsProductList[0].SBQQ__SubscriptionType__c = 'One-time';
        lmsProductList[0].SBQQ__PricingMethod__c = 'List';
        lmsProductList[0].SBQQ__AssetConversion__c = '';

        insert lmsProductList ;

        List<PricebookEntry> pricebookEntryList = TestingUtils.createEntries(lmsProductList, 'USD', 1);
        insert pricebookEntryList;

        Map<String,String> cpqConstantMap = new Map<String,String>{'Increased number of days'=>'0','Bundle'=>'Bundle','Bundle Option'=>'Bundle Option'
                                                                    ,'Check Primary Quote Line Group'=>'There should be one and only one primary quote line group on a primary quote'
                                                                    ,'Check Primary Quote Line Group Message'=>'ERROR: There should be one and only one primary quote line group on a primary quote'
                                                                    ,'Child'=>'Child','Clone Value'=>'Clone Value','Default Quote Line Group Name'=>'Quote 1'
                                                                    ,'Delete Proposal Message'=>'Cannot delete a proposal when opportunity is at Generate Contract.'
                                                                    ,'Forecast Category Message'=>'ERROR: No two quote line groups should have same forecast category.'
                                                                    ,'Forecast Category Outlook Value'=>'Outlook','Forecast Category Upside Value'=>'Upside','Forecast Category Worst Case Value'=>'Worst Case'
                                                                    ,'Goal'=>'Double click here to enter text','Job Credits'=>'Job Credits','Job Slots'=>'Job Slots'
                                                                    ,'Maximum Quote Line Group Message'=>'ERROR: There can be a maximum of three Quote Line Group per Quote'
                                                                    ,'Minimum Quote Line Group Message'=>'ERROR: There should be atleast one Quote Line Group per Quote'
                                                                    ,'Not Clone Value'=>'Not Cloned','Parent HQ'=>'Parent HQ'
                                                                    ,'Primary Quote Line Group Message'=>'ERROR: There can be only one Primary Quote Line Group','Quote Trigger'=>'QuoteTrigger'
                                                                    ,'Solution Benefits'=>'Double click here to enter text','Standalone Product'=>'Standalone Product'
                                                                    ,'Total Savings'=>'Double click here to enter text','Ultimate Parent HQ'=>'Ultimate Parent HQ','Upfront'=>'Upfront'
                                                                    ,'Business Unit LTS Value'=>'LTS','Quote Line Group Trigger'=>'QuoteLineGroupTrigger','Quote Line Trigger'=>'QuoteLineTrigger'
                                                                    ,'Active Contract Message'=>'Cannot delete a primary proposal when opportunity has active contract'
                                                                    ,'Restrict_OLI_Error_Msg'=>'You are not allowed to add/edit/delete products to opportunity directly, please perform the same operations on opportunity\'s primary quote using CPQ or contact OM/SPA team for assistance.'};
        List<CPQ_Constants__c> cpqConstantList = TestingUtils.createCPQConstants(cpqConstantMap);
        insert cpqConstantList;

        List<String> names = new List<String>{'QuoteTrigger','QuoteLineGroupTrigger','QuoteLineTrigger','OpportunityTrigger','OpportunitySplitTrigger'
                                                    ,'EstablishSchedulesGG','DeleteSchedulesGG','OpportunityLineItemTrigger','ContractLineItemTrigger'};
        List<OverrideTrigger__c> overrideTriggerList = TestingUtils.createTriggerOverrides(names);
        insert overrideTriggerList;

        GeneralProperties__c testGeneralSett = new GeneralProperties__c ();
            testGeneralSett.Name = 'NAMER';
            testGeneralSett.value__c ='NAMER';
        insert testGeneralSett;

        HasAdminOverwrite__c[] hasAdminOverwriteList = new HasAdminOverwrite__c[0];
            hasAdminOverwriteList.add(new HasAdminOverwrite__c(Name = Label.Profile_Sys_Admin, CanOverwrite__c = true));
        insert hasAdminOverwriteList;

    }

    static void loadTestData() {

        accounts = [SELECT Id FROM Account];

        oppty = [SELECT Id FROM Opportunity where RecordTypeId = :Label.RT_CorpSolutions LIMIT 1];

        corpBooking = [SELECT Id FROM Corp_Booking__c LIMIT 1];

        lmsGGOppty = [SELECT Id FROM Opportunity where RecordTypeId = :Label.RT_MS_GG_Oppty_RecordType LIMIT 1];

        lmsProductList = [SELECT Id FROM Product2 LIMIT 1];

    }

    //To test processFeedItem method in FeedItemUtil class
    static testMethod void TEST_processFeedItem_EmptyFile()
    {
        loadTestData();
        
        //To test empty file
        String header= '\n';  
        String recordString = '';  
        Blob csvBlob = Blob.valueof(header+recordString);
        FeedItem post = new FeedItem(ParentId = Label.Abacus,Body = '#ActualExchangeRates',ContentData = csvBlob,ContentFileName = 'AE_Update.csv',type = 'ContentPost');
        
        Test.startTest();
        insert post;
        Test.stopTest();
        
        FeedComment fc = [Select CommentBody from FeedComment where FeedItemId = :post.Id];
        // Checking if errors are posted to comments.
        System.assertEquals(true,fc.CommentBody.containsIgnoreCase('File is empty!'));
        // Checking if Actual Fx Rate is not updated
        System.assertEquals(null,[Select Actual_Fx_Rate__c from Corp_Booking__c where Id = :corpBooking.Id][0].Actual_Fx_Rate__c);
    }
    
    //To test processFeedItem method in FeedItemUtil class
    static testMethod void TEST_processFeedItem_NoRecordsFile()
    {
        loadTestData();
        
        //to test no records found in the file
        String header = 'Currency,Conversion Rate,Start Date,End Date,x\n';  
        Blob csvBlob = Blob.valueof(header);
        FeedItem post = new FeedItem(ParentId = Label.Abacus,Body = '#ActualExchangeRates',ContentData = csvBlob,ContentFileName = 'AE_Update.csv',type = 'ContentPost');
        
        Test.startTest();
        insert post;
        Test.stopTest();
        
        FeedComment fc = [Select CommentBody from FeedComment where FeedItemId = :post.Id];
        // Checking if errors are posted to comments.
        System.assertEquals(true,fc.CommentBody.containsIgnoreCase('No Records found in the file!'));
        // Checking if Actual Fx Rate is not updated
        System.assertEquals(null,[Select Actual_Fx_Rate__c from Corp_Booking__c where Id = :corpBooking.Id][0].Actual_Fx_Rate__c);
    }
    
    //To test processFeedItem method in FeedItemUtil class
    static testMethod void TEST_processFeedItem_InvalidHashtag()
    {
        loadTestData();
        
        //to test Invalid hashtag
        String header= 'Currency,Conversion Rate,Start Date,End Date,x\n';  
        String recordString = 'CAD,6,11/30/2018,11/30/2019,x\n';  
        Blob csvBlob = Blob.valueof(header+recordString);
        FeedItem post = new FeedItem(ParentId = Label.Abacus,Body = '#InvalidTag',ContentData = csvBlob,ContentFileName = 'AE_Update.csv',type = 'ContentPost');
        
        Test.startTest();
        insert post;
        Test.stopTest();
        
        FeedComment fc = [Select CommentBody from FeedComment where FeedItemId = :post.Id];
        // Checking if errors are posted to comments.
        System.assertEquals(true,fc.CommentBody.containsIgnoreCase('Hashtag is not defined or invalid!'));
        // Checking if Actual Fx Rate is not updated
        System.assertEquals(null,[Select Actual_Fx_Rate__c from Corp_Booking__c where Id = :corpBooking.Id][0].Actual_Fx_Rate__c);
    }
    
    //To test processFeedItem method in FeedItemUtil class
    static testMethod void TEST_processFeedItem_FileSizeError()
    {
        loadTestData();
        
        //to test file size
        String header= 'Currency,Conversion Rate,Start Date,End Date,x\n';
        String recordString = 'MYR,2.5,11/30/2018,11/30/2019,x\n';
        for(integer i=0; i<30; i++){
            recordString = recordString + 'AUD,1.25,11/30/2018,11/30/2019,x\n';
            recordString = recordString + 'CAD,3.25,11/30/2018,11/30/2019,x\n';
            recordString = recordString + 'EUR,0.34,11/30/2018,11/30/2019,x\n';
            recordString = recordString + 'MYR,2.5,11/30/2018,11/30/2019,x\n';
        }  
        Blob csvBlob = Blob.valueof(header+recordString);
        FeedItem post = new FeedItem(ParentId = Label.Abacus,Body = '#ActualExchangeRates',ContentData = csvBlob,ContentFileName = 'AE_Update.csv',type = 'ContentPost');
        
        Test.startTest();
        insert post;
        Test.stopTest();
        
        FeedComment fc = [Select CommentBody from FeedComment where FeedItemId = :post.Id];
        // Checking if errors are posted to comments.
        System.assertEquals(true,fc.CommentBody.containsIgnoreCase('Records failed to upload. File size exceeds the maximum allowed limit!'));
        // Checking if Actual Fx Rate is not updated
        System.assertEquals(null,[Select Actual_Fx_Rate__c from Corp_Booking__c where Id = :corpBooking.Id][0].Actual_Fx_Rate__c);
    }
    
    //To test error scenario for execute method in AbacusFxCoversionBatch class.
    static testMethod void TEST_Conversion_ActualError()
    {
        loadTestData();
        
        //To test error in the file
        String header= 'Currency,Conversion Rate,Start Date,End Date,x\n';  
        String recordString = ',6,11/30/2018,11/30/2019,x\n';  
        Blob csvBlob = Blob.valueof(header+recordString);
        FeedItem post = new FeedItem(ParentId = Label.Abacus,Body = '#ActualExchangeRates',ContentData = csvBlob,ContentFileName = 'AE_Update.csv',type = 'ContentPost');
        
        Test.startTest();
        insert post;
        Test.stopTest();
        
        FeedComment fc = [Select CommentBody from FeedComment where FeedItemId = :post.Id];
        // Checking if errors are posted to comments.
        System.assertEquals(true,fc.CommentBody.containsIgnoreCase('Some records failed to upload, please find attached is the failure report'));
        // Checking if Actual Fx Rate is not updated to 6
        System.assertNotEquals(6,[Select Actual_Fx_Rate__c from Corp_Booking__c where Id = :corpBooking.Id][0].Actual_Fx_Rate__c);
        // Checking if Actual Fx Rate is not inserted to Actual_Fx_Rate__c custom setting
        System.assertNotEquals(1,[Select count() from Actual_Fx_Rate__c where Name = 'CAD_30-Nov-2018_30-Nov-2019']);
    }
    
    //To test storeFxRates method in CorpUtil class and execute method in AbacusFxCoversionBatch class for Actual rates
    static testMethod void TEST_Conversion_Actual()
    {
        loadTestData();
        
        //Test.setMock(HttpCalloutMock.class, new AbacusFxConversionBatchTest());
        
        //To Test Actual Exchange Rates
        String header= 'Currency,Conversion Rate,Start Date,End Date,x\n';  
        String recordString = 'CAD,6,11/30/2018,11/30/2019,x\n';  
        Blob csvBlob = Blob.valueof(header+recordString);

        // Checking if Actual Fx Rate is not 6 prior to posting feeditem
        // Checking Corp booking rates
        System.assertNotEquals(6,[Select Actual_Fx_Rate__c from Corp_Booking__c where Id = :corpBooking.Id][0].Actual_Fx_Rate__c);
        // Checking Opportunity rates
        System.assertNotEquals(6,[Select Actual_Fx_Rate__c from Opportunity where Id = :oppty.Id][0].Actual_Fx_Rate__c);
        // Checking if Actual Fx Rate is not inserted to Actual_Fx_Rate__c custom setting
        System.assertNotEquals(1,[Select count() from Actual_Fx_Rate__c where Name = 'CAD_30-Nov-2018_30-Nov-2019']);
        
        //To Test Actual Fx Rate
        FeedItem post = new FeedItem(ParentId = Label.Abacus,Body = '#ActualExchangeRates',ContentData = csvBlob,ContentFileName = 'AE_Update.csv',type = 'ContentPost');
        
        Test.startTest();
        insert post;
        Test.stopTest();
        
        // Checking if Actual Fx Rate is updated
        // Checking Corp booking rates
        System.assertEquals(6,[Select Actual_Fx_Rate__c from Corp_Booking__c where Id = :corpBooking.Id][0].Actual_Fx_Rate__c);
        // Checking Opportunity rates
        System.assertEquals(6,[Select Actual_Fx_Rate__c from Opportunity where Id = :oppty.Id][0].Actual_Fx_Rate__c);
        // Checking if Actual Fx Rate is inserted to Actual_Fx_Rate__c custom setting with the value 6
        System.assertEquals(6,[Select Actual_Fx_Rate__c from Actual_Fx_Rate__c where Name = 'CAD_30-Nov-2018_30-Nov-2019'][0].Actual_Fx_Rate__c);
    }
    
    //To test execute method in AbacusFxCoversionBatch class for planned rates
    static testMethod void TEST_Conversion_Planned()
    {
        loadTestData();
        
        Test.setMock(HttpCalloutMock.class, new AbacusFxConversionBatchTest());
        
        //To Test Planned Exchange Rates
        String header= 'Currency,Conversion Rate,Start Date,End Date,x\n';  
        String recordString = 'CAD,6,11/30/2018,11/30/2019,x\n';  
        Blob csvBlob = Blob.valueof(header+recordString);
        
        // Checking if Planned Fx Rate is not 6 prior to posting feeditem
        System.assertNotEquals(6,[Select Plan_Fx_Rate__c from Corp_Booking__c where Id = :corpBooking.Id][0].Plan_Fx_Rate__c);
        
        //To Test Planned Fx Rate
        FeedItem post = new FeedItem(ParentId = Label.Abacus,Body = '#PlannedExchangeRates',ContentData = csvBlob,ContentFileName = 'AE_Update.csv',type = 'ContentPost');
        
        Test.startTest();
        insert post;
        Test.stopTest();
        
        // Checking if Planned Fx Rate is updated
        System.assertEquals(6,[Select Plan_Fx_Rate__c from Corp_Booking__c where Id = :corpBooking.Id][0].Plan_Fx_Rate__c);    
        // Checking if Planned Fx Rate is updated
        System.assertEquals(6,[Select Plan_Fx_Rate__c from Opportunity where Id = :oppty.Id][0].Plan_Fx_Rate__c);
        // checking with planned fx rate custom setting records insertion
        system.assertEquals(1, [select id from Planned_Fx_Rate__c].size());
    }
    /**
    * @author        : Dinesh Talreja
    * @date          : 08-January-2016
    * @description   : Method to check if the Plan Fx Rate on OLIS record is updated
    **/
    static testMethod void testOLISPlanFxRate(){
        loadTestData();
        
        Date startDate = date.newInstance(2018, 12, 30);
         
        //Create Products to associate it to Opportunity Line Item
        Product2 prodObj = TestingUtils.createProducts(1)[0];
        prodObj.Name = 'Recruiter - Corporate (1-2 Seats)';
        prodObj.ProductCode = 'NRCRUI02';
        prodObj.Product_Type__c = 'Subscription Based';
        insert prodObj;
        
        List<Product2> productList = new List<Product2>{prodObj};
        
        //Create Pricebook Entries for Products
        PricebookEntry[] entries = TestingUtils.createEntries(productList, 'CAD', 100);            
        insert entries;
        
        //Create Opportunity Line Item
        OpportunityLineItem opportunityLineItemObj = TestingUtils.createOpportunityProducts(1,oppty.id,'CAD',entries,50,50)[0];        
        insert opportunityLineItemObj;
        
        //Create Opportunity Split record
        Opportunity_Split__c opportunitySplitObj = TestingUtils.createOptySplit(oppty.id, 1.0);
        opportunitySplitObj.CurrencyIsoCode = 'CAD';
        insert opportunitySplitObj;
        
        //Create Opportunity Line Item Schedule record
        Opportunity_Line_Item_Schedule__c opportunityLineItemScheduleObj = TestingUtils.createOLIS(oppty.id, 'CAD', startDate, opportunitySplitObj.id, opportunityLineItemObj.Id);
        insert opportunityLineItemScheduleObj;
        
        Test.setMock(HttpCalloutMock.class, new AbacusFxConversionBatchTest());
        
        //To Test Planned Exchange Rates
        String header= 'Currency,Conversion Rate,Start Date,End Date,x\n';  
        String recordString = 'CAD,6,11/30/2018,11/30/2019,x\n';  
        Blob csvBlob = Blob.valueof(header+recordString);
        
        // Checking if Planned Fx Rate is not 6 prior to posting feeditem
        System.assertNotEquals(6,[SELECT Plan_Fx_Rate__c FROM Opportunity_Line_Item_Schedule__c WHERE Id =:opportunityLineItemScheduleObj.Id LIMIT 1].Plan_Fx_Rate__c);
        
        //To Test Planned Fx Rate
        FeedItem post = new FeedItem(ParentId = Label.Abacus,Body = '#PlannedExchangeRates',ContentData = csvBlob,ContentFileName = 'AE_Update.csv',type = 'ContentPost');
        
        Test.startTest();
        insert post;
        Test.stopTest();
        
        // Checking if Planned Fx Rate is updated on OLIS record
        System.assertEquals(6,[SELECT Plan_Fx_Rate__c FROM Opportunity_Line_Item_Schedule__c WHERE Id =:opportunityLineItemScheduleObj.Id LIMIT 1].Plan_Fx_Rate__c);    
    }
        /**
    * @author        : Ankur Paul
    * @date          : 28-Feb-2017
    * @description   : Method to check if the Plan Fx Rate on CLIS record is updated
    **/
    static testMethod void testCLISPlanFxRate(){
        loadTestData();
        
        Date startDate = date.newInstance(2018, 9, 30);

        List<Product2> lmsProducts = lmsProductList;

        List<CMT_Account__c> lstCMTAccount = new List<CMT_Account__c>();
        CMT_Account__c testCMTAccount1 = new CMT_Account__c ();
        CMT_Account__c testCMTAccount2 = new CMT_Account__c ();
        lstCMTAccount.add(testCMTAccount1);
        lstCMTAccount.add(testCMTAccount2);
        insert lstCMTAccount;
        // Prep for DATA For CLIS Creation.

        //Create Quote record in order to test QuoteCalculator class
        SBQQ__Quote__c LMSquoteObjMain = TestingUtils.createQuote('Test Proposal', lmsGGOppty.Id);
        LMSquoteObjMain.Total_Discounts__c = true;
        LMSquoteObjMain.List_Total_Annual__c = true;
        LMSquoteObjMain.List_Total_Monthly__c = true;
        LMSquoteObjMain.Net_Total_Annual__c = true;
        LMSquoteObjMain.Net_Total_Monthly__c = true;
        LMSquoteObjMain.Subscription_Terms__c = true;
        LMSquoteObjMain.SBQQ__Notes__c = 'Test Notes';
        LMSquoteObjMain.SBQQ__StartDate__c = startDate;
        insert LMSquoteObjMain;
        
        //Create Quote Line Group record and associate it to Quote.
        SBQQ__QuoteLineGroup__c LMSquoteLineGroupObjMain = TestingUtils.createQuoteLineGroup('Option 1', LMSquoteObjMain.Id);
        insert LMSquoteLineGroupObjMain;
        
        SBQQ__QuoteLine__c LMSquoteLineObjmain = TestingUtils.createQuoteLine(LMSquoteObjMain.Id, LMSquoteLineGroupObjMain.Id, lmsProducts[0].Id);
        LMSquoteLineObjmain.SBQQ__ProratedListPrice__c = 10000;
        LMSquoteLineObjmain.SBQQ__RegularPrice__c = 10000;
        LMSquoteLineObjmain.SBQQ__CustomerPrice__c = 10000;
        LMSquoteLineObjmain.SBQQ__Quantity__c = 1;
        LMSquoteLineObjmain.SBQQ__NetPrice__c = 10000;
        LMSquoteLineObjmain.Budget__c = 10000;
        LMSquoteLineObjmain.SBQQ__ListPrice__c = 10000;
        LMSquoteLineObjmain.Prior_Budget__c = 10;
        LMSquoteLineObjmain.Grouped__c = true;
        LMSquoteLineObjmain.Added_Value_Type__c = 'Make-Good';
        LMSquoteLineObjmain.LI_Co_Id__c = '54327';
        LMSquoteLineObjmain.CMT_Account__c = lstCMTAccount[0].id;
        Date firstDayOfNextMonth = startDate.addMonths(1).toStartOfMonth();
        LMSquoteLineObjmain.Service_Start_Date__c = firstDayOfNextMonth;
        LMSquoteLineObjmain.Service_End_Date__c = firstDayOfNextMonth.addMonths(2).addDays(-1);
        LMSquoteLineObjmain.CMT_Campaign_Id__c = '12345';
        insert LMSquoteLineObjmain;

        //check if split record is created - requirement for CLIS
        List<Opportunity_Split__c> splits = [Select Id, Rep__c, Split_Percent__c from Opportunity_Split__c where Opportunity__c = :lmsGGOppty.Id];
        if(splits == null || splits.isEmpty()){
            Opportunity_Split__c split = new Opportunity_Split__c();
            split.Rep__c = UserInfo.getUserId();
            split.Split_Percent__c = 10;
            split.Opportunity__c = lmsGGOppty.Id;
            insert split;
        }
        
        //Make Quote Primary to trigger CLI and CLIS creation
        LMSquoteObjMain.SBQQ__Primary__c = true;
        update LMSquoteObjMain;   

        List<Contract_Line_Item__c> cliList = [Select Id, Master_Pipeline_Amount__c from Contract_Line_Item__c where Originating_Quote_Line__c = :LMSquoteLineObjmain.Id];

        System.assertNotEquals(null, cliList, 'Contract Line Item should be created on making a quote primary for LMS GG QLI');
        System.assertEquals(1, cliList.size(), 'One Contract Line Item should be created on making a quote primary for LMS GG QLI');

        List<Contract_Line_Item_Schedule__c> listCLIS = [Select Id,CurrencyISOCode, Type__c, Pipeline_Amount_Gross_Local__c from Contract_Line_Item_Schedule__c where Contract_Line_Item__c = :cliList[0].Id];

        //Assert that the CLIS records are created - 1 for each month
        System.assertNotEquals(null, listCLIS, 'Contract Line Item Schedules should be created on creation of CLI');
        System.assertEquals(2, listCLIS.size(), 'Expecting 2 CLIS, one for each month of CLI');
        
        Test.setMock(HttpCalloutMock.class, new AbacusFxConversionBatchTest());
        
        //To Test Planned Exchange Rates
        String header= 'Currency,Conversion Rate,Start Date,End Date,x\n';  
        String recordString = 'CAD,6,09/30/2018,09/30/2019,x\n';  
        Blob csvBlob = Blob.valueof(header+recordString);
        
        // Checking if Planned Fx Rate is not 6 prior to posting feeditem
        System.assertNotEquals(6,[SELECT Plan_Fx_Rate__c FROM Contract_Line_Item_Schedule__c WHERE Id =:listCLIS[0].Id LIMIT 1].Plan_Fx_Rate__c);
        
        //To Test Planned Fx Rate
        FeedItem post = new FeedItem(ParentId = Label.Abacus,Body = '#PlannedExchangeRates',ContentData = csvBlob,ContentFileName = 'AE_Update.csv',type = 'ContentPost');
        
        Test.startTest();
            insert post;
        Test.stopTest();
        
        // Checking if Planned Fx Rate is updated on CLIS record
        System.assertNotEquals(6,[SELECT Plan_Fx_Rate__c FROM Contract_Line_Item_Schedule__c WHERE Id =:listCLIS[0].Id LIMIT 1].Plan_Fx_Rate__c); 
    }
    //Mock http response
    global HTTPResponse respond(HTTPRequest req) {
        // Create a fake response
        HttpResponse res = new HttpResponse();
        res.setHeader('Content-Type', 'application/json');
        res.setBody('{"foo":"bar"}');
        res.setStatusCode(201);
        return res;
    }
}