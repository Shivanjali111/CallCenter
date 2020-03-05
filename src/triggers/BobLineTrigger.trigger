/**
*   Contains all triggering events pertaining to the BoB_Line__c Object
**/
trigger BobLineTrigger on BoB_Line__c (before insert, before update, after insert, after update) {
    if(GeneralUtil.triggerOverrideCheck('BobLineTrigger')){
        if (Trigger.isBefore) {
            if (Trigger.isInsert) {
                BobLineUtils.stampAccountInformation(Trigger.new);
                BobLineUtils.updateOpportunityInfo(Trigger.new);
            }
            if (Trigger.isInsert || Trigger.isUpdate) {
               //BobLineUtils.checkValidCreation(Trigger.new, Trigger.oldMap);
                BobLineUtils.checkValidCreation(Trigger.new);
                BobLineUtils.updateBobLiOwner(Trigger.new, Trigger.oldMap);             
                BobLineUtils.updateAccountStars(Trigger.new, Trigger.oldMap);
            }
        }

        if(Trigger.isAfter){
            if(Trigger.isInsert || Trigger.isUpdate){
                BobLineUtils.createShare(Trigger.new, Trigger.oldMap);
            }
            // JIRA : LTSGSS - 116 : Mohit Modi
            if(Trigger.isUpdate){
                BobLineUtils.updateChildBobLinesWithParent(Trigger.new, Trigger.oldMap);
            }
        }
    }
}