<?xml version="1.0" encoding="UTF-8"?>
<Workflow xmlns="http://soap.sforce.com/2006/04/metadata">
    <fieldUpdates>
        <fullName>Stamp_Bob_Line_Staging_Key_Field_Update</fullName>
        <description>Unique Key on Bob Line Staging record.</description>
        <field>Bob_Line_Staging_Key__c</field>
        <formula>IF((TEXT(Status__c)= &apos;New&apos;), LEFT(Account__c, 15) + UPPER(TEXT(Business_Unit__c))+&apos;-&apos;+UPPER(TEXT(Operation_Type__c)), LEFT(Account__c, 15) + UPPER(TEXT(Business_Unit__c)) +&apos;-&apos;+UPPER(TEXT(Operation_Type__c))+ TEXT(NOW()))</formula>
        <name>Stamp Bob Line Staging Key Field Update</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <rules>
        <fullName>Stamp Bob Line Staging Key Rule</fullName>
        <actions>
            <name>Stamp_Bob_Line_Staging_Key_Field_Update</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <description>This workflow stamps Unique Key on Bob Line Staging record.</description>
        <formula>ISNEW() || ISCHANGED(Business_Unit__c) || ISCHANGED(Account__c) || ISCHANGED(Status__c)</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
</Workflow>
