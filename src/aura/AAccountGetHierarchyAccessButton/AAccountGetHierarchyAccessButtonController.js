({
    /**
    * Initialize the component
    */
	doInit: function(component, event, helper) {
        //call Apex method to get hierarchy access
        helper.getAccountHierarchyAccess(component);
	}
})