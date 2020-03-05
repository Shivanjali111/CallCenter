<?xml version="1.0" encoding="UTF-8"?>
<Workflow xmlns="http://soap.sforce.com/2006/04/metadata">
    <fieldUpdates>
        <fullName>Uncheck_Autofill_Party_Info</fullName>
        <field>Autofill_Party_Info__c</field>
        <literalValue>0</literalValue>
        <name>Uncheck Autofill Party Info</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Literal</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Update_Addl_Party_City</fullName>
        <field>City__c</field>
        <formula>IF(AND(
LEN(Account__c)&gt;0,
LEN(City__c) = 0),
Account__r.ShippingCity,
City__c
)</formula>
        <name>Update Add&apos;l Party City</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Update_Addl_Party_City_Notice</fullName>
        <field>City_Notice__c</field>
        <formula>IF(AND(
LEN(Account__c)&gt;0,
LEN(City_Notice__c) = 0),
Account__r.ShippingCity,
City_Notice__c
)</formula>
        <name>Update Add&apos;l Party City Notice</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Update_Addl_Party_Country</fullName>
        <field>Country__c</field>
        <formula>IF(AND(
LEN(Account__c)&gt;0,
LEN(Country__c) = 0),
Account__r.ShippingCountry,
Country__c
)</formula>
        <name>Update Add&apos;l Party Country</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Update_Addl_Party_Country_Notice</fullName>
        <field>Country_Notice__c</field>
        <formula>IF(AND(
LEN(Account__c)&gt;0,
LEN(Country_Notice__c) = 0),
Account__r.ShippingCountry,
Country_Notice__c
)</formula>
        <name>Update Add&apos;l Party Country Notice</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Update_Addl_Party_Name</fullName>
        <field>Party_Name__c</field>
        <formula>IF(AND(
LEN(Account__c)&gt;0,
LEN(Party_Name__c) = 0),
Account__r.Name,
Party_Name__c
)</formula>
        <name>Update Add&apos;l Party Name</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Update_Addl_Party_State</fullName>
        <field>State_Province__c</field>
        <formula>IF(AND(
LEN(Account__c)&gt;0,
LEN(State_Province__c) = 0),
Account__r.ShippingState,
State_Province__c
)</formula>
        <name>Update Add&apos;l Party State</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Update_Addl_Party_State_Notice</fullName>
        <field>State_Province_Notice__c</field>
        <formula>IF(AND(
LEN(Account__c)&gt;0,
LEN(State_Province_Notice__c) = 0),
Account__r.ShippingState,
State_Province_Notice__c
)</formula>
        <name>Update Add&apos;l Party State Notice</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Update_Addl_Party_Street_Addr_1_Notice</fullName>
        <field>Street_Address_1_Notice__c</field>
        <formula>IF(AND(
LEN(Account__c)&gt;0,
LEN(Street_Address_1_Notice__c) = 0),
Account__r.ShippingStreet,
Street_Address_1_Notice__c
)</formula>
        <name>Update Add&apos;l Party Street Addr 1 Notice</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Update_Addl_Party_Street_Address_1</fullName>
        <field>Street_Address_1__c</field>
        <formula>IF(AND(
LEN(Account__c)&gt;0,
LEN(Street_Address_1__c) = 0),
Account__r.ShippingStreet,
Street_Address_1__c
)</formula>
        <name>Update Add&apos;l Party Street Address 1</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Update_Addl_Party_Zip_Code</fullName>
        <field>Postal_Code__c</field>
        <formula>IF(AND(
LEN(Account__c)&gt;0,
LEN(Postal_Code__c) = 0),
Account__r.ShippingPostalCode,
Postal_Code__c
)</formula>
        <name>Update Add&apos;l Party Zip Code</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Update_Addl_Party_Zip_Code_Notice</fullName>
        <field>Postal_Code_Notice__c</field>
        <formula>IF(AND(
LEN(Account__c)&gt;0,
LEN(Postal_Code_Notice__c) = 0),
Account__r.ShippingPostalCode,
Postal_Code_Notice__c
)</formula>
        <name>Update Add&apos;l Party Zip Code Notice</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <rules>
        <fullName>Additional Party - Reset Autofill Party Info</fullName>
        <actions>
            <name>Uncheck_Autofill_Party_Info</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <description>Resets Autofill Party Info checkbox to false on Additional Party.</description>
        <formula>AND(Autofill_Party_Info__c, $Setup.OverrideRulesSetup__c.OverrideWorkflow__c = False )</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>Additional Party Information WFR</fullName>
        <actions>
            <name>Update_Addl_Party_City</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Update_Addl_Party_Country</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Update_Addl_Party_Name</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Update_Addl_Party_State</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Update_Addl_Party_Street_Address_1</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Update_Addl_Party_Zip_Code</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <description>Update Additional Parties Party Information.</description>
        <formula>AND(LEN(Account__c)&gt;0, Autofill_Party_Info__c, $Setup.OverrideRulesSetup__c.OverrideWorkflow__c=False)</formula>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
    </rules>
    <rules>
        <fullName>Additional Party Notice Information WFR</fullName>
        <actions>
            <name>Update_Addl_Party_City_Notice</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Update_Addl_Party_Country_Notice</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Update_Addl_Party_State_Notice</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Update_Addl_Party_Street_Addr_1_Notice</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Update_Addl_Party_Zip_Code_Notice</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <description>Update Additional Parties Party Notice Information</description>
        <formula>AND(LEN(Account__c)&gt;0, Autofill_Party_Info__c, $Setup.OverrideRulesSetup__c.OverrideWorkflow__c=False)</formula>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
    </rules>
</Workflow>
