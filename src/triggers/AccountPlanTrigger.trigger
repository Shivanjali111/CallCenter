/*
* Vanaj Nayak - 21 Feb 2018
* case #09346536 - Customer objective char count to help RM reps===
* @Neel - 22 Aug 2018 - Jira ORBIT-3364: Create integration log on account plan create event.
* @Neel - 03 Oct 2018 - Jira ORBIT-3676: Lock Down Account Plan for Ramped RM.
* @Sonal - 22 March 2019 - Restrict CCP Creation if a CCP already exists on an account except for Sys Admin Profiles as per Case 12532096.
*/

trigger AccountPlanTrigger on Account_Plan__c (before insert, before update, after insert) {
    if(Trigger.isBefore){
        if(Trigger.isUpdate){
            AccountPlanUtil.updateCustomerObjectivesCharCount(Trigger.new, Trigger.oldMap);
        }
        if(Trigger.isInsert){
            AccountPlanUtil.updateCustomerObjectivesCharCount(Trigger.new, null);
			//@Sonal - 22 March 2019 - Restrict CCP Creation if a CCP already exists on an account except for Sys Admin Profiles as per Case 12532096.
			AccountPlanUtil.restrictAccountPlanCreation(Trigger.new);
        }
        //@Neel - 03 Oct 2018 - Jira ORBIT-3676: Lock Down Account Plan for Ramped RM
        AccountPlanUtil.lockDownAccountPlanForRampedRM(Trigger.new, Trigger.oldMap);
    }

    if(Trigger.isAfter) {
        if(Trigger.isInsert) {
            //@Neel - 22 Aug 2018 - Jira ORBIT-3364: Create integration log on account plan create event.
            AccountPlanUtil.createIntegrationLogsForAccountPlans(Trigger.new);
        }
    }
}