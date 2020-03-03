({
    /**
    * Method to Claim Account Hierarchy Access.
    *
    * @param component {Object}
    * @return {void}
    */
	getAccountHierarchyAccess : function(component) {
		//get generic utility component
        var genericUtility = component.find('genericUtility');
        var action = component.get('c.claimHierarchyAccess');
        var accountId = component.get('v.recordId');
        action.setParams({
            "accountId": accountId
        });
        action.setCallback(this, function(response) {
			genericUtility.handleResponse(response, function(data) {
                if(data) {
                    //show the toast message
                    genericUtility.showToast({
                        type: 'success',
                        title: 'Success',
                        message: data
                    });
                }
                
                //close the quick action dialog
                $A.get('e.force:closeQuickAction').fire();
			});
		});
        $A.enqueueAction(action);
	}
})