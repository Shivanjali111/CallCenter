/**
* @author       Eric Luu
* @date         12/15/2014
* @description  Positive testing to check if a user can query the Agreements ID when user has the Apttus license. 
*               Negative testing is currently not possible due to Drawloop limitations on retrieving rows for QueryResult.
* Test commit
*/ 
@isTest
private class AccessibleAgreementsTest {
    static testMethod void testAccessibleAgreements_Access() {
        User[] users;
        String[] repRegions = new String[]{'SMB', 'RM'};
        System.runAs(new User(Id = Label.SystemAdmin))
        {
            users = TestingUtils.createUsers(1, new Profile[]{new Profile(Id = Label.Profile_LCS_Mgmt_AE)}, repRegions, 'GBP');
            insert users;
            
            UserPackageLicense upl = new UserPackageLicense(UserId = users[0].id,PackageLicenseId=label.Apttus_Contract_Package_Id);
            insert upl;
        }        
        
        Test.startTest();
            Account acc = TestingUtils.createAccounts(1, 'Entertainment', TestingUtils.rType)[0];
            acc.leo_Company_SOP__c = 50000;
            acc.ShippingCountry = '';
            acc.Director_Type__c = 'EXE/DIR';
            insert acc; 
            AccountTeamMember[] members = TestingUtils.createTeamMembers(acc.Id, new Id[]{users[0].Id});
            insert members;    
            System.runAs(users[0]){                            
                Opportunity opp = TestingUtils.createOpportunity(acc.Id, Label.RT_CorpSolutions, 'New Business', 'USD', 'Negotiation', 12, System.today());
                insert opp;
                
                // Create a related Sales Agreement. Apttus__Is_System_Update__c=true required to bypass validation rules
                Apttus__APTS_Agreement__c agreement = new Apttus__APTS_Agreement__c(Apttus__Related_Opportunity__c=opp.Id,
                                                                                    Apttus__Account__c = acc.Id,
                                                                                    Apttus__Status_Category__c = 'In Signatures',
                                                                                    RecordTypeId = label.RT_Sales_Agreement,
                                                                                    Apttus__Is_System_Update__c=true);
                insert agreement;
                
                // Instantiate AccessibleAgreements and call all methods
                AccessibleAgreements aa = new AccessibleAgreements();                
                system.assertEquals(1, aa.getGlobalDescribe().size());
                
                List<String> testObjNames = new List<String>{label.Opp_Agreement_Object_Name};                   
                System.assertEquals(1,aa.describeObjects(testObjNames).size());
                System.assertEquals(0,aa.getChildRelationships(label.Opp_Agreement_Object_Name).size());     
                
                Loop.ExternalData.Relationship relationship = new Loop.ExternalData.Relationship();
                relationship.Name=label.Opp_Agreement_Object_Name;
                
                // Create a QueryRequestInfo object to be used as input for query method
                Loop.ExternalData.QueryRequestInfo requestInfo = new Loop.ExternalData.QueryRequestInfo();
                requestInfo.RecordId = opp.Id;
                requestInfo.relationships = new List<Loop.ExternalData.Relationship>{relationship};
                
                // Ideally, we should be able to compare the id in the row(s) returned in the QueryResult but cannot due to limitations with interface provided by Drawloop
                system.assertNotEquals(null,aa.query(requestInfo));
            }
        Test.stopTest();
    }
}