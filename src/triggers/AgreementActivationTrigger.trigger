/*************************************************************************************************************************
 @Name: AgreementActivationTrigger
 @Description: This Trigger invoked the AgreementActivationTriggerHandler for Agreement Activation 
 @Used By:
 @Author: Chang Liu
 @Modified By:Umang Modi 
*************************************************************************************************************************/
trigger AgreementActivationTrigger on Apttus__APTS_Agreement__c (after update) {
	if(Trigger.isUpdate) {
        AgreementActivationTriggerHandler.ActivateAgreements(Trigger.New);
    }
}