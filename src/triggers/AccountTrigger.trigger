/**
*   @JKnight - 6 July 2011
*   @Contains all triggering events pertaining to the Account Object
*   @NKamadolli - 13 September 2013 - Territory Planning 2014
*   @Saakshi - 6 April 2016 - SRD7: GSA/SEWP Pricing - Added updateGSASEWPQuotes method in after update call
*   @Weronika -  22 June 2016 - Added method updateLoosingInsights as per 05564346.===
*   @Aieleeta - 22 June 2016 -  Added 'after delete' in the list of trigger events. Also added  !Trigger.isDelete to the method createAccountTeam
*   @Hkwan - Added new Anchorage triggers, submitForPaymentTermsApproval and updateCreditFields
*   @Sid - 10/18/2017- Added New trigger for createHistory method
*   @Neel - 22 Mar 2018 - Orbit: Create integration log for account create/update/delete
*   @Tracy - 7 June 2018 - Only certain profiles will be allowed to delete/merge account #Monarch
*   @Paresh - 22 August 2018 - Added method 'billingShippingCountryMismatch' to check if Billing Country/ShippingCountry entered are correct as per project 'Minecraft - QuickSilver Billing Data - Typeahead Simplification' Requirement3
*   @Anurag - 17 Oct 2018 - Added Method 'createRSCActivationOutreachTask' to create new task when ATS field on Account is updated to a certain specified values.The Changes are for case #10955614
*   @Ravi - 31 Oct 2018 - Added method 'autoPopulateShippingAddress' to check and copy billing address to shipping address if blank as per project 'Minecraft - QuickSilver Billing Data - Typeahead Simplification' MCRFT-10251
*   @Samit - 28 Nov 2018 - Commented method call tempCoverageFlag for Project: Modify Temp Coverage for Renewal
*   @Vivek - 13 Feb 2019 - Moving AccountAccessService.createShareForUpdatedAccounts to Queueable apex
*   @Sonal - 22 March 2019 - Invoking 'autoCreateCCPForNewAccounts' to create CCP on the account when it is being created as per Case 12532096.
*	@Sonal - 02 April 2019 - Invoking 'deleteAccountPlans' for deleting the account plans before the deletion of accounts as per Case 12532096.
*   @Samit - 22 May, 2019 - Calling updateAccountfromDBCompany method to populate account fields from DNB company record
**/
trigger AccountTrigger on Account (before update, before insert, after insert, after update, before delete, after undelete, after delete) {
    if (GeneralUtil.triggerOverrideCheck('AccountTrigger')) {
        if (Trigger.isUpdate || Trigger.isUnDelete) {
            if (!AccountUtil.ultimateParentRunning) {
                AccountUtil.findUltimateParent(Trigger.newMap, Trigger.oldMap, Trigger.isUpdate);
            }
            if (Trigger.isAfter && Trigger.isUpdate) {
                AccountUtil.createCaseForOFACAccount(Trigger.new, Trigger.oldMap);
                //Samit - 28 Nov 2018 - Commented the method call tempCoverageFlag
                //AccountUtil.tempCoverageFlag(Trigger.new, Trigger.oldMap);
                if (RecursiveTriggerUtil.RunOnce('Account-Update-updateOrderLegalName') || Test.isRunningTest()) {
                    AccountUtil.updateOrderLegalName(Trigger.new, Trigger.oldMap);
                }
                if (Trigger.new.size() == 1) {
                    CPQUtil.updateGSASEWPQuotes(Trigger.new, Trigger.oldMap);
                }
                //Create Histroy for field update
                if (GeneralProperties__c.getInstance('Enable_AccountTrigger_RecursionCheck') != null && GeneralProperties__c.getInstance('Enable_AccountTrigger_RecursionCheck').Value__c == 'true') {
                    if (RecursiveTriggerutil.RunOnce('Account_CreateHistory'))
                        GeneralUtil.createHistory(Trigger.newMap, Trigger.oldMap, 'Account__c');
                } else {
                    GeneralUtil.createHistory(Trigger.newMap, Trigger.oldMap, 'Account__c');
                }
                //Jira ORBIT-1098: Create Integration Log record if any update on the tracked account fields
                if (RecursiveTriggerUtil.RunOnce('Mayflower_IntegrationLog_Account')) {
                    AccountUtil.createIntegrationLogsForVerifiedAccounts(Trigger.new, Trigger.oldMap, 'update');
                }

                AccountUtil.updateABMProgramDetailsOnOpportunity(trigger.newMap, trigger.oldMap, trigger.isUpdate);
                //Anurag chages for case#10955614
                AccountUtil.createRSCActivationOutreachTask(trigger.new, trigger.oldMap);
                //Anurag changes end 
            }
        } else if (Trigger.isInsert) {
            if (Trigger.isBefore) {
                AccountUtil.findUltimateParent(Trigger.new);
            } else if (Trigger.isAfter) {
                AccountUtil.autoCreateBobs(Trigger.new);
                AccountUtil.createCaseForOFACAccount(Trigger.new, Trigger.oldMap);
                //Jira ORBIT-1098: Create integration log record when account is created
                if (RecursiveTriggerUtil.RunOnce('Mayflower_IntegrationLog_Account')) {
                    AccountUtil.createIntegrationLogsForVerifiedAccounts(Trigger.new, null, 'insert');
                }
                //@Sonal - 22 March 2019 - Create CCP on the account when it is being created as per Case 12532096.
                AccountUtil.autoCreateCCPForNewAccounts(Trigger.new);
            }
        } else if (Trigger.isDelete) {
            if (Trigger.isBefore) {
                // Tracy - 7 June 2018 - check whether user has permission for account deletion #Monarch
                AccountUtil.checkDeletePermission(Trigger.old);
            }
            AccountUtil.resetUltimateParent(Trigger.oldMap);
            AccountUtil.deactivateBobAssignments(Trigger.oldMap);
        }
        if (Trigger.isAfter && !Trigger.isUnDelete && !Trigger.isDelete) {
            if (Trigger.size == 1) {
                AccountUtil.createAccountTeam(Trigger.new, Trigger.oldMap);
            }
            //Submit for Payment Terms approval logic. #anchorage
            CreditUtil.submitForPaymentTermsApproval(Trigger.new, Trigger.oldMap);
        }
        if (Trigger.isBefore && (Trigger.isInsert || Trigger.isUpdate)) {
            //Samit: Adding  method to update Account fields from D&B company record
            if (RecursiveTriggerUtil.RunOnce('AccountUtil_updateAccountfromDBCompany')) {
                AccountUtil.updateAccountfromDBCompany(Trigger.New);
            }
            AccountUtil.findIndustry(Trigger.new, Trigger.oldMap);
            //Ravi Changes Start - 31-Oct-2018 - Shipping Address will auto populate from Billing Address if blank. MCRFT-10251
            if (Trigger.isInsert) {
                AccountUtil.autoPopulateShippingAddress(Trigger.new);
            }
            //Ravi Changes End            
            //run all update credit field logic. #anchorage
            CreditUtil.updateCreditFields(Trigger.new, Trigger.oldMap, Trigger.isUpdate);
            //Paresh Changes Start
            //Update value of Billing/Shipping Country from clean name of CountryToEntityMapping custom setting with respect to variation name and compare this value with oracle country name field value of ISOCountryMapping custom setting.
            if (RecursiveTriggerUtil.RunOnce('AccountUtil_billingShippingCountryMismatch')) {
                AccountUtil.billingShippingCountryMismatch(Trigger.new, Trigger.oldMap, Trigger.isInsert, Trigger.isUpdate);
            }
            //Paresh Changes End
        }
        if (Trigger.isAfter && Trigger.isDelete) {
            AccountUtil.updateLoosingInsights(Trigger.oldMap.keySet());
            //Jira ORBIT-1098: Create integration log record when account is deleted
            if (RecursiveTriggerUtil.RunOnce('Mayflower_IntegrationLog_Account_Delete')) {
                AccountUtil.createIntegrationLogsForVerifiedAccounts(null, Trigger.oldMap, 'delete');
		//@Sonal - Deleting the account plans before the deletion of accounts as per Case 12532096.
		AccountPlanUtil.deleteAccountPlans(Trigger.old);
            }
			
        }
        if (Trigger.isAfter && Trigger.isUpdate) {
            if (System.Label.Account_Access_Available.equals('true') && (!System.IsFuture() && !System.IsBatch()))
                //GDPR Phase II - Moving the method to queueable apex
                //AccountAccessService.createShareForUpdatedAccounts(Trigger.New, Trigger.oldMap);
                System.enqueueJob(new AccountAccessServiceQueueableApex(Trigger.New, Trigger.oldMap));
        }
    }
}