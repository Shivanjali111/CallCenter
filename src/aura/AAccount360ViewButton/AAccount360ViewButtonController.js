({
	/*
	 * Initialize the component
	 */
	doInit: function(component, event, helper) {
		var genericUtility = component.find('genericUtility');
		genericUtility.getCurrentUserInfo(function(userData) {
			helper.get360View(component, userData);
		});
	}
})