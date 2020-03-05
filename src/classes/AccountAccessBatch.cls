/**
* @author       Vivek Gupta
* @description  Batch class to create Account share for Account Teams added through Bob 
*/
global class AccountAccessBatch implements Database.Batchable<sObject>, Database.Stateful {
    global String startTime = Datetime.now().formatGMT('yyyy-MM-dd\'T\'HH:mm:ssZ');
    global Database.QueryLocator start(Database.BatchableContext BC) {
        /*String batchRunDate = datetime.now().addHours(Integer.valueOf(System.Label.Account_Access_Last_N_Hours)).format('yyyy-MM-dd\'T\'hh:mm:ss\'Z\'', 'GMT');*/
        String query;
        GeneralProperties__c prop = GeneralProperties__c.getValues('AccountAccessBatch_LastRun');
        String lastBatchRunDateTime = (prop != null) ? prop.Value__c : Datetime.now().addDays(-1).formatGMT('yyyy-MM-dd\'T\'HH:mm:ssZ');
        if(!Test.isRunningTest()){
            query = '' + System.Label.Account_Access_Batch_Query + ' SystemModstamp >= ' + lastBatchRunDateTime + ' and CreatedById IN '+ System.Label.Account_Access_User_Ids.split(',') + ' order by SystemModstamp asc';
        }else {
            query = 'Select Id,AccountId,UserId from AccountTeamMember where Account.Ultimate_Parent__c != null LIMIT 1 ';
        }
        System.debug('Query'+query);
        return Database.getQueryLocator(query); 
    } 

    global void execute(Database.BatchableContext BC, List<AccountTeamMember> atmList) {
        try{
            AccountAccessWrapper.ATMUpdateWrapper params = AccountAccessService.createATMParameters(atmList);
            AccountAccessService.addATMForNewATMInAccount(params.accList, params.mapOfAccountIdATM, false);
        }catch (Exception e) {
            // exception logging
            String stackTrace = e.getStackTraceString();
            stackTrace = stackTrace.length() > 32768 ? stackTrace.substring(0, 32768) : stackTrace;
            ExceptionLogUtil.logException(UserInfo.getUserId(), null, 'AccountAccessBatch', 'execute', new String[] {e.getMessage(), stackTrace, e.getTypeName()}, e.getLineNumber(), null, 'High');
        }
    }

    global void finish(Database.BatchableContext BC) {
        GeneralProperties__c prop = GeneralProperties__c.getInstance('AccountAccessBatch_LastRun');
        if(prop != null) {
            prop.Value__c = startTime;
            update prop;
        }
    }
}