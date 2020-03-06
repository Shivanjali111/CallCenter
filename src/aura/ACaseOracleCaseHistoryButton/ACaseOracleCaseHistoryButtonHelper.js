({
  /*
   * Hold the account
   *
   * @param component {Object}
   * @return {void}
   */
	oracleCaseHistory: function(component) {
    	var simpleCaseRecord = component.get("v.simpleCaseRecord");
		//alert(simpleCaseRecord.Account.Account__c);
        //alert(simpleCaseRecord.Contact.Email);
        //alert(simpleCaseRecord.Contact.Member_Id__c);
        //alert(simpleCaseRecord.AccountId);
        //alert(simpleCaseRecord.ContactId);
        var accountId = (simpleCaseRecord.AccountId != null) ? simpleCaseRecord.Account.Account__c : null;
        var contactEmailField = (simpleCaseRecord.ContactId != null) ? simpleCaseRecord.Contact.Email : null;
        var contactMemberId = (simpleCaseRecord.ContactId != null) ? simpleCaseRecord.Contact.Member_Id__c : null;
        var oracleServiceCloudCases = $A.get("$Label.c.Oracle_Service_Cloud_Cases");

        var urlEvent = $A.get("e.force:navigateToURL");
		urlEvent.setParams({
			"url": oracleServiceCloudCases+"?account="+accountId+"&email="+contactEmailField+"&mid="+contactMemberId,
			"isredirect": "true"
		});
		urlEvent.fire();
    }

    /*
    window.open("{!$Label.Oracle_Service_Cloud_Cases}?account={!Account.Account__c}&email={!Contact.Email}&mid={!Contact.Member_Id__c}");
	*/
})