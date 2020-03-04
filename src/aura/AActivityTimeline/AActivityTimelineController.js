({
	/*
	 * To display a modal that contains list of task record types to select.
	 */
	taskRecordTypeSelectionModal: function(component, event, helper) {
		helper.taskRecordTypeSelectionModal(component, event);
	},

	/*
	 * Update the attribute when the user changes the task record type
	 */
	changeTaskRecordType: function(component, event, helper) {
		var recordType = event.getParam("value");
		component.set('v.selectedTaskRecordType', recordType);
	},

	/*
	 * Display the modal to create a task
	 */
	createTask: function(component, event, helper) {
		helper.createTask(component);
	},

	/*
	 * To display a modal that contains list of event record types to select.
	 */
	eventRecordTypeSelectionModal: function(component, event, helper) {
		helper.eventRecordTypeSelectionModal(component);
	},
    
    /*
	 * Update the attribute when the user changes the event record type
	 */
	changeEventRecordType: function(component, event, helper) {
		var recordType = event.getParam("value");
		component.set('v.selectedEventRecordType', recordType);
	},

	/*
	 * This method is used to create an event
	 */
	createEvent: function(component, event, helper) {
		helper.createEvent(component);
	},
    
    /*
     * Hardcode and open the report.
     */
    openNonMktngActivity: function(component, event, helper) {
        var urlEvent = $A.get("e.force:navigateToURL");
        var accId = component.get('v.recordId');
        urlEvent.setParams({
            url: '/00O60000002i6Jw?pv0=%22' + accId + '%22'
        });
        urlEvent.fire();
    },

    /*
     * Close the opened modal
     */
    closeModal: function(component, event, helper) {
		var param = event.target.getAttribute('data-param');
		if(!param) {
			return false;
		}

		component.set(param, false);
    }
})