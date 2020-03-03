({
	get360View: function(component, userData) {
		var helper = this;
		var genericUtility = component.find('genericUtility');
		var profileId = userData.ProfileId.substring(0 , 15);
		var userId = userData.Id.substring(0 , 15);
		var lmsAEProfileLabel = $A.get('$Label.c.Profile_MS_Mgmt_AE');
		var sysAdminProfileLabel = $A.get('$Label.c.Profile_Sys_Admin');
		var msAdminProfileLabel = $A.get('$Label.c.Profile_MS_Admin');
		var msOtherOpsProfileLabel = $A.get('$Label.c.MS_Other_Ops');

		var lms360LinkLabel = $A.get('$Label.c.LMS_Case_360_Link');
		var ldc360ViewLabel = $A.get('$Label.c.LDC_360_View');
		var ldc360LinkLabel = $A.get('$Label.c.LDC_AccountPlan_360_Link');

		helper.getAccountData(component, function(accData) {
			helper.getPermissionSetData(component, userId, function(permissionSetList) {
				if(profileId ==  lmsAEProfileLabel ||
					profileId == sysAdminProfileLabel || 
					profileId == msAdminProfileLabel || 
					profileId == msOtherOpsProfileLabel) { 

					var url = lms360LinkLabel + 'account=' + accData.Account_Number__c;
					window.open(url, '_blank');
					$A.get("e.force:closeQuickAction").fire();
					return;
				}

				if(permissionSetList.length > 0 || 
					ldc360ViewLabel.indexOf(userId) != -1) {

					var url = ldc360LinkLabel + 'account=' + accData.Id;
					window.open(url, '_blank');
					$A.get("e.force:closeQuickAction").fire();
					return;
				}

				genericUtility.showToast({
					type: 'error',
					title: 'Error',
					message: 'You don not have access to 360 view.'
				});
				$A.get("e.force:closeQuickAction").fire();
			});
		});
	},

	/*
	 * Get docusign status data
	 *
	 * @param component {Object}
	 * @param callback {Function}
     * @return {void}
	 */
	getAccountData: function(component, callback) {
		var genericUtility = component.find('genericUtility');
		var action = component.get('c.getRecordData');
		var accId = component.get('v.recordId');
		action.setParams({
			request: {
				sobject: "Account",
				fields: "Account_Number__c",
				where: "Id = '" + accId + "'"
			}
		});
		action.setCallback(this, function(response) {
			genericUtility.handleResponse(response, function(data) {
				var accList = data || [];
				if(typeof callback == 'function') {
					callback(accList.length > 0 ? accList[0] : null);
				}
			});
		});
		$A.enqueueAction(action);
	},

	/*
	 * Get permission set assignment detail
	 *
	 * @param component {Object}
	 * @param userId {String}
	 * @param callback {Function}
     * @return {void}
	 */
	getPermissionSetData: function(component, userId, callback) {
		var genericUtility = component.find('genericUtility');
		var action = component.get('c.getRecordData');
		action.setParams({
			request: {
				sobject: "PermissionSetAssignment",
				fields: "AssigneeId",
				where: "PermissionSet.Name = 'Permission_to_create_Lynda_Account_Plan' AND AssigneeId = '" + userId + "'"
			}
		});
		action.setCallback(this, function(response) {
			genericUtility.handleResponse(response, function(data) {
				var permissionSetList = data || [];
				if(typeof callback == 'function') {
					callback(permissionSetList);
				}
			});
		});
		$A.enqueueAction(action);
	}
})