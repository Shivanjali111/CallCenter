<?xml version="1.0" encoding="UTF-8"?>
<Workflow xmlns="http://soap.sforce.com/2006/04/metadata">
    <alerts>
        <fullName>Bug_Notification_Alert</fullName>
        <description>Bug Notification Alert</description>
        <protected>false</protected>
        <recipients>
            <field>Assignee__c</field>
            <type>userLookup</type>
        </recipients>
        <recipients>
            <field>QA_Owner__c</field>
            <type>userLookup</type>
        </recipients>
        <senderType>CurrentUser</senderType>
        <template>Auto_Emails/Bug_Notification</template>
    </alerts>
    <fieldUpdates>
        <fullName>ES_Update_Bug_Name</fullName>
        <description>Update Bug Name to concatenate Bug JIRA Number and Summary</description>
        <field>Name</field>
        <formula>LEFT(JIRA_Number__c &amp; &quot; &quot; &amp; Summary__c, 80)</formula>
        <name>ES: Update Bug Name</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <rules>
        <fullName>Bug Notification</fullName>
        <actions>
            <name>Bug_Notification_Alert</name>
            <type>Alert</type>
        </actions>
        <active>true</active>
        <description>Notifies assignee and qa owner when status/priority of bug is changed</description>
        <formula>AND(  CONTAINS(RecordType.Name,&quot;Run&quot;),  OR (ISNEW(),ISCHANGED( Priority__c ), ISCHANGED( Status__c )),  $User.OverrideWorkflow__c = &quot;False&quot;  )</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>ES%3A Bug Name</fullName>
        <actions>
            <name>ES_Update_Bug_Name</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <description>Update Bug Name to concatenate JIRA Number and Summary</description>
        <formula>OR(
ISNEW(),
ISCHANGED( JIRA_Number__c ),
ISCHANGED( Summary__c ) 
)</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
</Workflow>
