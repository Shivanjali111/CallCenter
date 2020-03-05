/**
*   JKnight - 11 September 2012
*   Contains all triggers regarding the Campaign object
*   
**/
trigger CampaignMemberTrigger on CampaignMember (after insert, after update) {
    String retValue;
    if(GeneralUtil.triggerOverrideCheck('CampaignMemberTrigger')){
        if(trigger.IsAfter)
            CampaignMemberUtil.updateExlatestOnLeadContact(Trigger.new);
        if(!TaskUtil.campaignTriggerRunning){
            TaskUtil.createCampaignMemberTasks(Trigger.new, Trigger.oldMap);
        }
    }
}