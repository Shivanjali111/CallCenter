<?xml version="1.0" encoding="UTF-8"?>
<Workflow xmlns="http://soap.sforce.com/2006/04/metadata">
    <alerts>
        <fullName>Account_Reactivation_Email_Alert</fullName>
        <description>Account Reactivation Email Alert</description>
        <protected>false</protected>
        <recipients>
            <type>owner</type>
        </recipients>
        <senderType>CurrentUser</senderType>
        <template>Account_Rejection_Templates/AccountRejection_Reactivation</template>
    </alerts>
    <alerts>
        <fullName>Account_Rejection_Email_Alert</fullName>
        <description>Account Rejection Email Alert</description>
        <protected>false</protected>
        <recipients>
            <type>owner</type>
        </recipients>
        <senderType>CurrentUser</senderType>
        <template>Account_Rejection_Templates/AccountRejection_Rejection</template>
    </alerts>
    <alerts>
        <fullName>Set_BoB_Assignment_Status_Account</fullName>
        <ccEmails>sobjectdml@w-271frd1hgyli60aph3cm9l921456j99xlcrkompqibvmj58ps6.3-1xrieae.na4.apex.salesforce.com</ccEmails>
        <description>Set BoB Assignment Status Account</description>
        <protected>false</protected>
        <senderType>CurrentUser</senderType>
        <template>WorkflowTemplates/Set_BoB_Assignment_Status_Account</template>
    </alerts>
    <fieldUpdates>
        <fullName>Account_Inactive_Not_Synced_to_TRUE</fullName>
        <field>Account_Inactive_Not_Synced_2__c</field>
        <literalValue>1</literalValue>
        <name>Account Inactive Not Synced to TRUE</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Literal</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Bob_Assignment_Assigned_Status</fullName>
        <field>Status__c</field>
        <literalValue>Assigned</literalValue>
        <name>Bob Assignment Assigned Status</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Literal</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Bob_Assignment_Clear_Assigned_Date</fullName>
        <field>Assigned_Date__c</field>
        <name>Bob Assignment Clear Assigned Date</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Null</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Bob_Assignment_Clear_Reject_Reason</fullName>
        <field>Reject_Reason__c</field>
        <name>Bob Assignment Clear Reject Reason</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Literal</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Bob_Assignment_Clear_Rejected_Date</fullName>
        <field>Rejected_Date__c</field>
        <name>Bob Assignment Clear Rejected Date</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Null</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Bob_Assignment_Clear_Rejection_Comments</fullName>
        <field>Rejection_Comments__c</field>
        <name>Bob Assignment Clear Rejection Comments</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Null</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Bob_Assignment_Clear_Status</fullName>
        <field>Status__c</field>
        <literalValue>Assigned</literalValue>
        <name>Bob Assignment Clear Status</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Literal</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Bob_Assignment_Set_Assigned_Date</fullName>
        <field>Assigned_Date__c</field>
        <formula>TODAY()</formula>
        <name>Bob Assignment Set Assigned Date</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Bob_Assignment_Unassigned_Status</fullName>
        <field>Status__c</field>
        <literalValue>Unassigned</literalValue>
        <name>Bob Assignment Unassigned Status</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Literal</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Reset_Bypass_Validation_Field_Update</fullName>
        <field>Bypass_Validation__c</field>
        <literalValue>0</literalValue>
        <name>Reset Bypass Validation Field Update</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Literal</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Update_Bob_Assignment_Name_Field_Update</fullName>
        <field>Name</field>
        <formula>&apos;BA-&apos; + LEFT(Account__r.Name,40) + &apos;_&apos; + TEXT(Business_Unit__c)</formula>
        <name>Update Bob Assignment Name Field Update</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <rules>
        <fullName>Bob Assignment Rejection Email Alert</fullName>
        <actions>
            <name>Account_Rejection_Email_Alert</name>
            <type>Alert</type>
        </actions>
        <active>true</active>
        <formula>(ISPICKVAL(Status__c,&apos;Rejected&apos;) || ISPICKVAL(Status__c,&apos;Inactive&apos;)) &amp;&amp;  ($User.Id !=  Owner:User.Id) &amp;&amp; (  Owner:User.Id  &lt;&gt; $Label.linkedin_solutions_user_id  || Owner:User.Id  &lt;&gt;  $Label.LTS_AE_Pool ||Owner:User.Id  &lt;&gt;  $Label.LSS_AE_Pool  || Owner:User.Id  &lt;&gt;   $Label.LMS_AE_Pool  ) &amp;&amp; $User.OverrideWorkflow__c = &quot;False&quot;</formula>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
    </rules>
    <rules>
        <fullName>Bob Assignment Reset Inactive %28Assigned%29 After 6 Months</fullName>
        <active>true</active>
        <criteriaItems>
            <field>BoB_Assignment__c.Status__c</field>
            <operation>equals</operation>
            <value>Inactive</value>
        </criteriaItems>
        <criteriaItems>
            <field>BoB_Assignment__c.Rejected_Date__c</field>
            <operation>notEqual</operation>
        </criteriaItems>
        <criteriaItems>
            <field>BoB_Assignment__c.Reject_Reason__c</field>
            <operation>equals</operation>
            <value>No current interest,No budget</value>
        </criteriaItems>
        <criteriaItems>
            <field>User.OverrideWorkflow__c</field>
            <operation>equals</operation>
            <value>False</value>
        </criteriaItems>
        <criteriaItems>
            <field>BoB_Assignment__c.OwnerId</field>
            <operation>notEqual</operation>
            <value>LinkedIn Solutions,POOL Unassigned</value>
        </criteriaItems>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
        <workflowTimeTriggers>
            <actions>
                <name>Account_Reactivation_Email_Alert</name>
                <type>Alert</type>
            </actions>
            <actions>
                <name>Bob_Assignment_Assigned_Status</name>
                <type>FieldUpdate</type>
            </actions>
            <actions>
                <name>Bob_Assignment_Clear_Reject_Reason</name>
                <type>FieldUpdate</type>
            </actions>
            <actions>
                <name>Bob_Assignment_Clear_Rejected_Date</name>
                <type>FieldUpdate</type>
            </actions>
            <actions>
                <name>Bob_Assignment_Clear_Rejection_Comments</name>
                <type>FieldUpdate</type>
            </actions>
            <offsetFromField>BoB_Assignment__c.Rejected_Date__c</offsetFromField>
            <timeLength>180</timeLength>
            <workflowTimeTriggerUnit>Days</workflowTimeTriggerUnit>
        </workflowTimeTriggers>
    </rules>
    <rules>
        <fullName>Bob Assignment Reset Inactive %28Unassigned%29 After 6 Months</fullName>
        <active>true</active>
        <criteriaItems>
            <field>BoB_Assignment__c.Status__c</field>
            <operation>equals</operation>
            <value>Inactive</value>
        </criteriaItems>
        <criteriaItems>
            <field>BoB_Assignment__c.Rejected_Date__c</field>
            <operation>notEqual</operation>
        </criteriaItems>
        <criteriaItems>
            <field>BoB_Assignment__c.Reject_Reason__c</field>
            <operation>equals</operation>
            <value>No current interest,No budget</value>
        </criteriaItems>
        <criteriaItems>
            <field>User.OverrideWorkflow__c</field>
            <operation>equals</operation>
            <value>False</value>
        </criteriaItems>
        <criteriaItems>
            <field>BoB_Assignment__c.OwnerId</field>
            <operation>equals</operation>
            <value>LinkedIn Solutions,POOL Unassigned</value>
        </criteriaItems>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
        <workflowTimeTriggers>
            <actions>
                <name>Bob_Assignment_Clear_Reject_Reason</name>
                <type>FieldUpdate</type>
            </actions>
            <actions>
                <name>Bob_Assignment_Clear_Rejected_Date</name>
                <type>FieldUpdate</type>
            </actions>
            <actions>
                <name>Bob_Assignment_Clear_Rejection_Comments</name>
                <type>FieldUpdate</type>
            </actions>
            <actions>
                <name>Bob_Assignment_Unassigned_Status</name>
                <type>FieldUpdate</type>
            </actions>
            <offsetFromField>BoB_Assignment__c.Rejected_Date__c</offsetFromField>
            <timeLength>180</timeLength>
            <workflowTimeTriggerUnit>Days</workflowTimeTriggerUnit>
        </workflowTimeTriggers>
    </rules>
    <rules>
        <fullName>Bob Assignment Reset Inactive After 6 Months</fullName>
        <active>true</active>
        <criteriaItems>
            <field>BoB_Assignment__c.Status__c</field>
            <operation>equals</operation>
            <value>Inactive</value>
        </criteriaItems>
        <criteriaItems>
            <field>BoB_Assignment__c.Rejected_Date__c</field>
            <operation>notEqual</operation>
        </criteriaItems>
        <criteriaItems>
            <field>BoB_Assignment__c.Reject_Reason__c</field>
            <operation>equals</operation>
            <value>No current interest,No budget</value>
        </criteriaItems>
        <criteriaItems>
            <field>User.OverrideWorkflow__c</field>
            <operation>equals</operation>
            <value>False</value>
        </criteriaItems>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
        <workflowTimeTriggers>
            <actions>
                <name>Account_Reactivation_Email_Alert</name>
                <type>Alert</type>
            </actions>
            <actions>
                <name>Bob_Assignment_Clear_Reject_Reason</name>
                <type>FieldUpdate</type>
            </actions>
            <actions>
                <name>Bob_Assignment_Clear_Rejected_Date</name>
                <type>FieldUpdate</type>
            </actions>
            <actions>
                <name>Bob_Assignment_Clear_Status</name>
                <type>FieldUpdate</type>
            </actions>
            <offsetFromField>BoB_Assignment__c.Rejected_Date__c</offsetFromField>
            <timeLength>180</timeLength>
            <workflowTimeTriggerUnit>Days</workflowTimeTriggerUnit>
        </workflowTimeTriggers>
    </rules>
    <rules>
        <fullName>Bob Assignment Status Update %28Assigned%29</fullName>
        <actions>
            <name>Bob_Assignment_Assigned_Status</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Bob_Assignment_Clear_Reject_Reason</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Bob_Assignment_Clear_Rejected_Date</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Bob_Assignment_Clear_Rejection_Comments</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Bob_Assignment_Set_Assigned_Date</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <description>If owner is changed and new owner is not LinkedIn * or POOL *, then update the Status to Assigned with Assigned Date</description>
        <formula>AND  (  ISCHANGED(OwnerId),  NOT(ISPICKVAL(Status__c,&apos;Rejected&apos;)),  Owner:User.FirstName != &apos;LinkedIn&apos;,  Owner:User.FirstName != &apos;POOL&apos;  )</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>Bob Assignment Status Update %28Unassigned%29</fullName>
        <actions>
            <name>Bob_Assignment_Clear_Assigned_Date</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Bob_Assignment_Clear_Reject_Reason</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Bob_Assignment_Clear_Rejected_Date</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Bob_Assignment_Clear_Rejection_Comments</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Bob_Assignment_Unassigned_Status</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <description>If owner is changed and new owner is either LinkedIn * or POOL *, AND not rejected, then update the Status to Unassigned and clear Assigned Date</description>
        <formula>AND(ISCHANGED(OwnerId), NOT(ISPICKVAL(PRIORVALUE(Status__c),&apos;Rejected&apos;)), NOT(ISPICKVAL(PRIORVALUE(Status__c),&apos;Inactive&apos;)), OR(Owner:User.FirstName == &apos;LinkedIn&apos;, Owner:User.FirstName == &apos;POOL&apos;))</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>Reset Bypass Validation Flag to false</fullName>
        <actions>
            <name>Reset_Bypass_Validation_Field_Update</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <criteriaItems>
            <field>BoB_Assignment__c.Name</field>
            <operation>notEqual</operation>
        </criteriaItems>
        <criteriaItems>
            <field>User.OverrideWorkflow__c</field>
            <operation>equals</operation>
            <value>False</value>
        </criteriaItems>
        <description>This rule is used to reset the Bypass Validation to false. Code components that need to change owner will set the Bypass Validation flag to true and this workflow will always reset it back to false</description>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>Set BoB Assignment Status Account</fullName>
        <actions>
            <name>Set_BoB_Assignment_Status_Account</name>
            <type>Alert</type>
        </actions>
        <active>true</active>
        <description>Whenever BoB status is updated, stamp the appropriate LXX_BOB_Active field on the account. This is to speed up CB load times and eliminate a query to the BoB object</description>
        <formula>AND(
NOT(ISBLANK(Account_Id__c)),
NOT(ISNEW()),
ISCHANGED(Status__c), 
$User.OverrideWorkflow__c = &quot;False&quot;)</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>Update BoB Assignment Name Rule</fullName>
        <actions>
            <name>Update_Bob_Assignment_Name_Field_Update</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <formula>TRUE</formula>
        <triggerType>onCreateOnly</triggerType>
    </rules>
    <rules>
        <fullName>Update BoB Assignment%27s Account Inactive Not Synced 2</fullName>
        <actions>
            <name>Account_Inactive_Not_Synced_to_TRUE</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <description>This workflow updates the &quot;Account Inactive Not Synced 2&quot; checkbox to TRUE if the Account&apos;s BoB assignment inactive status is out of sync with the related BoB assignment object</description>
        <formula>AND( 
Account_Merged_Deleted__c = FALSE, 
OR( 
AND( 
ISPICKVAL(Business_Unit__c, &apos;LTS&apos;), 
OR( 
AND( 
Account__r.LTS_BOB_Inactive__c = FALSE, 
OR( 
ISPICKVAL(Status__c, &apos;Inactive&apos;), 
ISPICKVAL(Status__c, &apos;Rejected&apos;)	
) 
), 

AND( 
Account__r.LTS_BOB_Inactive__c = TRUE, 
AND( 
NOT(ISPICKVAL(Status__c, &apos;Inactive&apos;)), 
NOT(ISPICKVAL(Status__c, &apos;Rejected&apos;))	
) 
) 
) 
), 

AND( 
ISPICKVAL(Business_Unit__c, &apos;LSS&apos;), 
OR( 
AND( 
Account__r.LSS_BOB_Inactive__c = FALSE, 
OR( 
ISPICKVAL(Status__c, &apos;Inactive&apos;), 
ISPICKVAL(Status__c, &apos;Rejected&apos;)	
) 
), 

AND( 
Account__r.LSS_BOB_Inactive__c = TRUE, 
AND( 
NOT(ISPICKVAL(Status__c, &apos;Inactive&apos;)), 
NOT(ISPICKVAL(Status__c, &apos;Rejected&apos;))	
) 
) 
) 
), 

AND( 
ISPICKVAL(Business_Unit__c, &apos;LMS&apos;), 
OR( 
AND( 
Account__r.LMS_BOB_Inactive__c = FALSE, 
OR( 
ISPICKVAL(Status__c, &apos;Inactive&apos;), 
ISPICKVAL(Status__c, &apos;Rejected&apos;)	
) 
), 

AND( 
Account__r.LMS_BOB_Inactive__c = TRUE, 
AND( 
NOT(ISPICKVAL(Status__c, &apos;Inactive&apos;)), 
NOT(ISPICKVAL(Status__c, &apos;Rejected&apos;))	
) 
) 
) 
) 
) 
)</formula>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
    </rules>
</Workflow>
