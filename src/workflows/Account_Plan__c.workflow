<?xml version="1.0" encoding="UTF-8"?>
<Workflow xmlns="http://soap.sforce.com/2006/04/metadata">
    <fieldUpdates>
        <fullName>Account_Plan_Name_Current_year</fullName>
        <description>It updates Account Plan Name with current year for Lynda</description>
        <field>Name</field>
        <formula>Name+&quot;_&quot;+TEXT(YEAR(DATEVALUE(CreatedDate)))</formula>
        <name>Account Plan Name Current year</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>CSM_Status_Field_Update</fullName>
        <description>Case: 07681358 , Update CSM Status to Null</description>
        <field>CSM_Status__c</field>
        <name>CSM Status Field Update</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Literal</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Date_Red_Account_Nominated</fullName>
        <field>Date_Nominated__c</field>
        <formula>NOW()</formula>
        <name>Date Red Account Nominated</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Populate_Unique_ID</fullName>
        <description>Populate Unique Account Plan field on Account Plann object with account ID + &quot;-&quot; + plan year for any record type except for CSO Success Plans. CSO Success Plans are updated with plan number + &quot;-&quot; plan name.</description>
        <field>Unique_Account_Plan_Name__c</field>
        <formula>IF( 
RecordType.Id = $Label.RT_CSO_Success_Plans, 
Plan_Number__c&amp;&quot;-&quot;&amp;Name, 
IF( 
RecordType.Id = $Label.RT_LDC_Success_Plan, 
&quot;LDC-&quot;&amp;Plan_Number__c,
IF( 
RecordType.Id = $Label.LSS_CS_Churn_Mitigation, 
Plan_Number__c&amp;&quot;-&quot;&amp;Name, 
Plan_Account__c&amp;&quot;-&quot;&amp;TEXT( Plan_Year__c ) 
) 
)
)</formula>
        <name>Populate Unique ID</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Set_Renewal_Health_Change_Date</fullName>
        <description>To Set the Renewal Health Change Date with time stamp when Renewal Health is changed.</description>
        <field>Renewal_Health_Change_Date__c</field>
        <formula>NOW()</formula>
        <name>Set Renewal Health Change Date</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Update_Record_type_to_Read_Only</fullName>
        <description>Update record type to LTS Customer Collaboration Page - Read Only</description>
        <field>RecordTypeId</field>
        <lookupValue>LTS_Customer_Collaboration_Page_Read_Only</lookupValue>
        <lookupValueType>RecordType</lookupValueType>
        <name>Update Record type to Read Only</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>LookupValue</operation>
        <protected>false</protected>
    </fieldUpdates>
    <flowActions>
        <fullName>Account_Flag_Notification</fullName>
        <flow>Send_Mail_Notification_to_HS_Customer_Success_Owner</flow>
        <flowInputs>
            <name>AccId</name>
            <value>{!Plan_Account__r.ID_15_Char__c}</value>
        </flowInputs>
        <flowInputs>
            <name>AccountPlanId</name>
            <value>{!Id}</value>
        </flowInputs>
        <label>Account Flag Notification</label>
        <language>en_US</language>
        <protected>false</protected>
    </flowActions>
    <flowActions>
        <fullName>Send_Email_Notification_on_Approval_Status</fullName>
        <description>Case 07681358, Email Notification when Flagged is moved to Approval Status &apos;Approved&apos;, &apos;Dismissed&apos; , &apos;Insufficient Information</description>
        <flow>Email_Notification_When_Flagged_Moved_To_Approval_Status</flow>
        <flowInputs>
            <name>AccountId</name>
            <value>{!Plan_Account__c}</value>
        </flowInputs>
        <flowInputs>
            <name>AccountName</name>
            <value>{!Plan_Account__r.Name}</value>
        </flowInputs>
        <flowInputs>
            <name>AccountPlanId</name>
            <value>{!Id}</value>
        </flowInputs>
        <flowInputs>
            <name>ApprovalStatus</name>
            <value>{!Approval_Status__c}</value>
        </flowInputs>
        <flowInputs>
            <name>SFDC_Server_URL</name>
            <value>{!$Label.SFDC_Server_URL}</value>
        </flowInputs>
        <label>Send Email Notification on Approval Status</label>
        <language>en_US</language>
        <protected>false</protected>
    </flowActions>
    <rules>
        <fullName>Account Plan Approval Notification</fullName>
        <actions>
            <name>Send_Email_Notification_on_Approval_Status</name>
            <type>FlowAction</type>
        </actions>
        <active>true</active>
        <description>Case 07681358 : Send an email when approval status value chages to Approved, Dismissed, or Insufficient Information.</description>
        <formula>AND( 
      ISCHANGED( Approval_Status__c ), 
      OR( 
      ISPICKVAL(Approval_Status__c, &apos;Approved&apos;), 
      ISPICKVAL(Approval_Status__c, &apos;Dismissed&apos;), 
      ISPICKVAL(Approval_Status__c, &apos;Insufficient Information&apos;) 
      ) 
      )</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>Account Plan Name Current year for Lynda</fullName>
        <actions>
            <name>Account_Plan_Name_Current_year</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <description>It populates Account plan name with current year for Lynda</description>
        <formula>AND($User.OverrideWorkflow__c = &quot;False&quot;,RecordTypeId == $Label.RT_LDC_Success_Plan, ISNEW())</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>Candidate Type Notification</fullName>
        <actions>
            <name>Account_Flag_Notification</name>
            <type>FlowAction</type>
        </actions>
        <active>true</active>
        <description>Case : 07134255, to send the mail notification whenever the Candidate Type is toggled</description>
        <formula>AND( OR(     AND(ISNEW(),          NOT(ISPICKVAL(Red_Candidate__c , &apos;&apos;))),     ISCHANGED(Red_Candidate__c)),      RecordTypeId = $Label.RT_CSO_Success_Plans,      $Setup.OverrideRulesSetup__c.OverrideWorkflow__c = False)</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>Lock Account Plan</fullName>
        <actions>
            <name>Update_Record_type_to_Read_Only</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <criteriaItems>
            <field>Account_Plan__c.RecordTypeId</field>
            <operation>equals</operation>
            <value>LTS Customer Collaboration Page</value>
        </criteriaItems>
        <criteriaItems>
            <field>User.OverrideWorkflow__c</field>
            <operation>equals</operation>
            <value>False</value>
        </criteriaItems>
        <criteriaItems>
            <field>Account_Plan__c.Primary_CCP__c</field>
            <operation>equals</operation>
            <value>No</value>
        </criteriaItems>
        <description>Lock Account Plan when Primary CCP? is No</description>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
    </rules>
    <rules>
        <fullName>Populate Unique ID</fullName>
        <actions>
            <name>Populate_Unique_ID</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <description>Populates Unique ID on Account Plan</description>
        <formula>OR( 
ISCHANGED( Plan_Year__c ), 
ISCHANGED( Plan_Account__c), 
ISCHANGED( Name ), 
ISNEW() 
)</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>Set Renewal Health Change Date</fullName>
        <actions>
            <name>Set_Renewal_Health_Change_Date</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <description>Time of  Renewal Health change</description>
        <formula>AND(ISCHANGED(Renewal_Health__c),$User.OverrideWorkflow__c = &quot;False&quot; )</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>Update CSM Status</fullName>
        <actions>
            <name>CSM_Status_Field_Update</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <criteriaItems>
            <field>Account_Plan__c.Approval_Status__c</field>
            <operation>equals</operation>
            <value>Dismissed</value>
        </criteriaItems>
        <criteriaItems>
            <field>User.OverrideWorkflow__c</field>
            <operation>equals</operation>
            <value>false</value>
        </criteriaItems>
        <description>Case: 07681358 , Update &apos;CSM Status&apos; to Null when Approval Status = &apos;Dismissed&apos;</description>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>Update Date Red Account Nominated</fullName>
        <actions>
            <name>Date_Red_Account_Nominated</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <criteriaItems>
            <field>Account_Plan__c.Red_Candidate__c</field>
            <operation>notEqual</operation>
            <value>,No</value>
        </criteriaItems>
        <criteriaItems>
            <field>User.OverrideWorkflow__c</field>
            <operation>equals</operation>
            <value>False</value>
        </criteriaItems>
        <description>Each time the Red Candidate goes from any value to &quot;Yes&quot;, the Date Nominated field will be populated with the Date/Time.</description>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
    </rules>
</Workflow>
