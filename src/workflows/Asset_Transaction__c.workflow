<?xml version="1.0" encoding="UTF-8"?>
<Workflow xmlns="http://soap.sforce.com/2006/04/metadata">
    <fieldUpdates>
        <fullName>AST_Quantity_Multiplication_Field_update</fullName>
        <description>Asset transaction quantity multiplication field update</description>
        <field>WeightedParameterForSummaryCal__c</field>
        <formula>CalcWeightedSummaryCode__c</formula>
        <name>AST Quantity Multiplication Field update</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <rules>
        <fullName>Update Quantity Multiplication Factor</fullName>
        <actions>
            <name>AST_Quantity_Multiplication_Field_update</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <description>This will help update the Quantity as zero for pre-order products and capinmail, capseatholderadministrator cs products so that it doesnt count against rolled up &quot;Granted Quantity&quot;.</description>
        <formula>IF(AND(OR(ISNEW(),ISCHANGED(CS_Product_Code__c), ISCHANGED(Granted_Quantity__c)),$User.OverrideWorkflow__c =&quot;False&quot;), true, false)</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
</Workflow>
