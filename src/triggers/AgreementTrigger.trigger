/**
*   Neetu Ojha - 14 July 2015
*   Agreement Object Trigger - To manage default recipients for Agreement
**/

trigger AgreementTrigger on Apttus__APTS_Agreement__c (after insert, after update) {
    
    if(Trigger.isAfter){
    	//Calling method to add default recipients on Agreement creation    
	    if(Trigger.isInsert){
	        Legal_DocuSignHandler.addDefaultRecipient(Trigger.new);
            if (RecursiveTriggerutil.RunOnce('Share_Agreement_With_Requestor')) {
                AgreementUtil.shareAgreementWithRequestor( Trigger.new, Trigger.oldMap, true);
            }
        APTMS_DocuSignRecipientsHandler.insertDefaultRecipientForAuthorType(Trigger.new);
        }
        
        //Calling method to modify default recipients if Agreement is modified
        if(Trigger.isUpdate){
            Legal_DocuSignHandler.updateDefaultRecipient(Trigger.new, Trigger.oldMap);
            if (RecursiveTriggerutil.RunOnce('Share_Agreement_With_Requestor')) {
                AgreementUtil.shareAgreementWithRequestor( Trigger.new, Trigger.oldMap, false);
            }
            APTMS_DocuSignRecipientsHandler.updateDefaultRecipientForAuthorType(Trigger.new, Trigger.oldMap);
        }
    }
}