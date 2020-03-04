({
	/*
	 * Fetch the record type related to the user profile and list out for the selection.
	 * Pass the selected record type to 'create task' modal once the user selects it.
	 *
	 * @param component {Object}
     * @return {void}
	 */
	taskRecordTypeSelectionModal: function(component, event) {
		var taskOption = event.currentTarget.getAttribute('data-task-option');
		var recordTypes = component.get('v.taskRecordTypePicklistValues');
		component.set('v.isTaskModalOpen', true);
		component.set('v.taskRecordTypes', recordTypes.options);
		component.set('v.selectedTaskRecordType', recordTypes.selected);
		component.set('v.taskOption', taskOption);
	},

	/*
	 * Trigger the method to create a task
	 *
	 * @param component {Object}
     * @return {void}
	 */
	createTask: function (component) {
		// Close the existing modal before opening the othe one.
		component.set('v.isTaskModalOpen', false);

		var helper = this;
		helper.getObjectNameByRecordId(component, function (objName) {
			var taskOption = component.get('v.taskOption');
			var objId = component.get('v.recordId');
			var selectedTaskRecordType = component.get('v.selectedTaskRecordType');
			var defaultFieldValues = {
				"RecordTypeId": selectedTaskRecordType
			};

			if (objName == 'Contact' || objName == 'Lead') {
				defaultFieldValues.WhoId = objId;
			} else {
				defaultFieldValues.WhatId = objId;
			}

			if (taskOption == 'logCall') {
				defaultFieldValues.Subject = 'Call';
			}

			var createTask = $A.get("e.force:createRecord");
			createTask.setParams({
				"entityApiName": "Task", "recordTypeId": selectedTaskRecordType, "defaultFieldValues": defaultFieldValues
			});
			createTask.fire();
		});
	},

	/*
	 * Fetch the record type related to the user profile and list out for the selection.
	 * Pass the selected record type to 'create event' modal once the user selects it.
	 *
	 * @param component {Object}
     * @return {void}
	 */
	eventRecordTypeSelectionModal: function(component) {
		var recordTypes = component.get('v.eventRecordTypePicklistValues');
		component.set('v.isEventModalOpen', true);
		component.set('v.eventRecordTypes', recordTypes.options);
		component.set('v.selectedEventRecordType', recordTypes.selected);
	},

	/*
	 * Trigger the method to create an event
	 *
	 * @param component {Object}
     * @return {void}
	 */
	createEvent: function (component) {
		// Close the existing modal before opening the othe one.
		component.set('v.isEventModalOpen', false);

		var helper = this;
		helper.getObjectNameByRecordId(component, function (objName) {
			var objId = component.get('v.recordId');
			var selectedEventRecordType = component.get('v.selectedEventRecordType');
			var defaultFieldValues = {
				"RecordTypeId": selectedEventRecordType
			};

			if (objName == 'Contact' || objName == 'Lead') {
				defaultFieldValues.WhoId = objId;
			} else {
				defaultFieldValues.WhatId = objId;
			}

			var createEvent = $A.get("e.force:createRecord");
			createEvent.setParams({
				"entityApiName": "Event", "recordTypeId": selectedEventRecordType, "defaultFieldValues": defaultFieldValues
			});
			createEvent.fire();
		});
	},

	/*
	 * get the object name of the current reord
	 *
	 * @param component {Object}
     * @return {void}
	 */
    getObjectNameByRecordId: function(component, callback) {
        var genericUtility = component.find('genericUtility');
        var objId = component.get('v.recordId');
        var action = component.get('c.getObjectNameByRecordId');
        action.setParams({
            objId: objId
        });
        action.setCallback(this, function(response) {
            genericUtility.handleResponse(response, function(data) {
                if(typeof callback == 'function') {
                    callback(data);
                }
            });
        });
        $A.enqueueAction(action);
    }
})