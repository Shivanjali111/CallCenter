<?xml version="1.0" encoding="UTF-8"?>
<Workflow xmlns="http://soap.sforce.com/2006/04/metadata">
    <alerts>
        <fullName>Campaign_Status_Completed</fullName>
        <description>Campaign Status Completed</description>
        <protected>false</protected>
        <recipients>
            <type>owner</type>
        </recipients>
        <senderType>CurrentUser</senderType>
        <template>Marketing_Ops/Campaign_Status_Completed_Template</template>
    </alerts>
    <alerts>
        <fullName>Campaign_Status_changed_Mail_Completed</fullName>
        <description>Campaign Status changed Mail- Completed</description>
        <protected>false</protected>
        <recipients>
            <type>owner</type>
        </recipients>
        <senderType>CurrentUser</senderType>
        <template>WorkflowTemplates/Campaign_Status_Changed_Template_Completed</template>
    </alerts>
    <alerts>
        <fullName>Campaign_Status_changed_to_In_Progress</fullName>
        <description>Campaign Status changed to In Progress</description>
        <protected>false</protected>
        <recipients>
            <type>owner</type>
        </recipients>
        <senderType>CurrentUser</senderType>
        <template>WorkflowTemplates/Campaign_Status_Changed_Template_in_progress</template>
    </alerts>
    <fieldUpdates>
        <fullName>Campaign_Name_Standard</fullName>
        <description>Per: https://na4.salesforce.com/a1W60000000ChG6</description>
        <field>Name</field>
        <formula>$RecordType.Name+&apos;_&apos;+Name</formula>
        <name>Campaign Name: Standard</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>MarkCampaignCompleted</fullName>
        <description>If the end date of a campaign is today, mark the campaign status as completed</description>
        <field>Status</field>
        <literalValue>Completed</literalValue>
        <name>Mark Campaign Completed</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Literal</operation>
        <protected>false</protected>
    </fieldUpdates>
    <rules>
        <fullName>Campaign Name%3A Standard</fullName>
        <actions>
            <name>Campaign_Name_Standard</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <description>Per: https://na4.salesforce.com/a1W60000000ChG6</description>
        <formula>NOT( BEGINS( Name , $RecordType.Name ) )</formula>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
    </rules>
    <rules>
        <fullName>Campaign Status Changed to Completed</fullName>
        <actions>
            <name>Campaign_Status_changed_Mail_Completed</name>
            <type>Alert</type>
        </actions>
        <active>true</active>
        <formula>(ISPICKVAL(PRIORVALUE( Status ), &quot;In Progress&quot;) )&amp;&amp; (ISPICKVAL(Status , &quot;completed&quot;))&amp;&amp;( $User.OverrideWorkflow__c =&apos;false&apos;)</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>Campaign Status Changed to In Progress</fullName>
        <actions>
            <name>Campaign_Status_changed_to_In_Progress</name>
            <type>Alert</type>
        </actions>
        <active>true</active>
        <formula>(ISPICKVAL(PRIORVALUE( Status ), &quot;Planned&quot;)) &amp;&amp; (ISPICKVAL(Status , &quot;In Progress&quot;))&amp;&amp; ($User.OverrideWorkflow__c =&apos;false&apos;)</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>Email to Campaign Owner when status is Completed</fullName>
        <actions>
            <name>Campaign_Status_Completed</name>
            <type>Alert</type>
        </actions>
        <active>false</active>
        <description>When status is changed to completed, send an email to the Campaign Owner</description>
        <formula>AND(
(ISPICKVAL(Status , &quot;Completed&quot;)),
ISCHANGED(Status),
($User.OverrideWorkflow__c = &quot;False&quot;),
 NumberSent  &gt; 10
)</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>Mark Campaign Completed</fullName>
        <actions>
            <name>MarkCampaignCompleted</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <criteriaItems>
            <field>Campaign.EndDate</field>
            <operation>equals</operation>
            <value>TODAY</value>
        </criteriaItems>
        <criteriaItems>
            <field>Campaign.RecordTypeId</field>
            <operation>equals</operation>
            <value>CS_Master</value>
        </criteriaItems>
        <criteriaItems>
            <field>Campaign.Status</field>
            <operation>notEqual</operation>
            <value>Completed</value>
        </criteriaItems>
        <criteriaItems>
            <field>User.OverrideWorkflow__c</field>
            <operation>equals</operation>
            <value>False</value>
        </criteriaItems>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
    </rules>
</Workflow>
