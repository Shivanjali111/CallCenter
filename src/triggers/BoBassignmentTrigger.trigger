trigger BoBassignmentTrigger on BoB_Assignment__c (before update,after update) {
    if(GeneralUtil.triggerOverrideCheck('BoBassignmentTrigger')){
        if(trigger.isBefore && trigger.isUpdate){
            BoBassignmentUtil.updateRejectedBobAssignments (trigger.New,trigger.OldMap);
        }
        if(trigger.isAfter && trigger.isUpdate){
            if(RecursiveTriggerUtil.RunOnce('BoBassignment-Update-createRejectedCaseAndEmails')  || Test.isRunningTest())
            {
            	BoBassignmentUtil.createRejectedCaseAndEmails(trigger.New,trigger.OldMap);
        	}
            if(!AccountUtil.skipBobATeamUpdate){
	        	BoBassignmentUtil.updateAccountTeamOnBobAssignmentOwnerUpdate(trigger.New,trigger.OldMap);
	        }
        }
    }
}