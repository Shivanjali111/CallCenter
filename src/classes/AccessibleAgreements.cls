/**
* @author       Eric Luu
* @date         12/15/2014
* @description  Purpose: allow users without an Apttus license to continue generating a DDP contract as usual without running into an error. ==
*/ 
global class AccessibleAgreements implements Loop.IApexDataSource {

    /**
    * @author       Eric Luu
    * @date         12/15/2014
    * @description  Returns a list of object names that will be selectable as an Apex Data Type Relationship in the the DDP Relationship UI. 
    *               The object name will ultimately be mapped to an Agreement Id, which gets passed into Docusign.
    */     
    public Set<string> getGlobalDescribe() {
        return new Set<String>{
            label.Opp_Agreement_Object_Name 
        };
    }
    
    /**
    * @author       Eric Luu
    * @date         12/15/2014
    * @description  Creates a "mapping" of field names to SObject field types
    * @param        List<Loop.ExternalData.DataObject>, List of DataObject - SFDC object tied with fields 
    */    
    public List<Loop.ExternalData.DataObject> describeObjects(List<string> objNames) { //passes in a list 
        List<Loop.ExternalData.DataObject> dataObject = new List<Loop.ExternalData.DataObject>();    
        List<Loop.ExternalData.FieldInfo> dataObjectFields = new List<Loop.ExternalData.FieldInfo>();
        // Map 'Id' to Id field type
        dataObjectFields.add(new Loop.ExternalData.FieldInfo('Id', Schema.DisplayType.ID));
        dataObject.add(new Loop.ExternalData.DataObject(objNames[0], dataObjectFields)); // only one objName defined, so no need to loop through list.
        return dataObject;
    }
    
    // Returns list of child objects for specified object name
    public Set<string> getChildRelationships(string objectName) {
        // not necesary for our use case. return empty Set
        return new Set<String>();
    }
     
    /**
    * @author       Eric Luu
    * @date         12/15/2014
    * @description  Query for the related Sales Agreement and add the Id to a QueryResultSet.
    *               @requestInfo is an object set by Drawloop and is based on the Opportunity that launched the DDP.
    * @param        Loop.ExternalData.QueryResultSet - Result of query, returns Agreements ID if criteria is met and Agreements Record exist. 
    */    
    public Loop.ExternalData.QueryResultSet query(Loop.ExternalData.QueryRequestInfo requestInfo) {
        Loop.ExternalData.QueryResultSet results = new Loop.ExternalData.QueryResultSet();   
        UserPackageLicense[] userPackageLicenses = new UserPackageLicense[0];
        try {
            if (string.valueOf(requestInfo.RecordId).startswith(Schema.sObjectType.Opportunity.keyprefix)){ // validates that the originating record is an Opportunity
                // loop through object names and "map" it to an Agreement Id
                for (string objectName : requestInfo.GetObjectNames()){
                    if (objectName == label.Opp_Agreement_Object_Name){
                        // construct a QueryResult object to store the Agreement Id                 
                        Loop.ExternalData.QueryResult agreementResults = new Loop.ExternalData.QueryResult(label.Opp_Agreement_Object_Name, new List<string>{ 'Id' });
                        // only query for Agreements if the user is licensed for the Apttus Contract Management package
                        userPackageLicenses = [select id from UserPackageLicense where PackageLicenseId = :label.Apttus_Contract_Package_Id and UserId = :UserInfo.getUserId()];                                                                    
                        if(userPackageLicenses.size() > 0){
                            // loop through Agreements and add each Id to the QueryResult object. There should always be only one Agreement for our use case.
                            for (Apttus__APTS_Agreement__c agreement : database.query(label.Opp_Agreement_DDP_Query.replace('{oppId}',requestInfo.RecordId))) // requestInfo.RecordId is the current Opportunity (set by Drawloop)
                            {
                                agreementResults.rows.add(new List<string>{ agreement.Id });
                            }
                        }
                        results.add(agreementResults); // add method provided by the QueryResultSet class
                    }
                }
            }
        } catch (Exception e){
            ExceptionLogUtil.logException(UserInfo.getUserId(),requestInfo.RecordId, 'AccessibleAgreements', 'query', new String[]{e.getMessage(), e.getStackTraceString()}, e.getLineNumber(), null, 'Medium');
        }
        return results;
    }
}