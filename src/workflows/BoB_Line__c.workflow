<?xml version="1.0" encoding="UTF-8"?>
<Workflow xmlns="http://soap.sforce.com/2006/04/metadata">
    <fieldUpdates>
        <fullName>BOB_line_hold_owner_set</fullName>
        <description>Updates hold owner with sales solutions</description>
        <field>Hold_Owner__c</field>
        <lookupValue>sales_solutions_alias@linkedin.com</lookupValue>
        <lookupValueType>User</lookupValueType>
        <name>BOB line hold owner set</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>LookupValue</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Bob_Line_Clear_Hold_Expiration_Field</fullName>
        <field>Hold_Owner_Expiration__c</field>
        <name>Bob Line Clear Hold Expiration Field</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Null</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Bob_Line_Clear_Hold_Owner_Field</fullName>
        <field>Hold_Owner__c</field>
        <name>Bob Line Clear Hold Owner Field</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>LookupValue</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Bob_Update_First_Assigned_Reason</fullName>
        <description>Copy Assigned Reason to First Assigned Reason</description>
        <field>First_Assigned_Reason__c</field>
        <formula>TEXT(Assigned_Reason__c)</formula>
        <name>Bob Update First Assigned Reason</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Bob_Update_First_Assigned_Rep</fullName>
        <description>Copy Assigned Rep to First Assigned Rep</description>
        <field>First_Assigned_Rep__c</field>
        <formula>PRIORVALUE(Assigned_Rep_Name__c)</formula>
        <name>Bob Update First Assigned Rep</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Bob_Update_First_Assigned_Rep_Id</fullName>
        <description>Copy Assigned Rep Id to First Assigned Rep Id</description>
        <field>First_Assigned_Rep_Id__c</field>
        <formula>PRIORVALUE( Owner__c )
&amp; MID(&quot;ABCDEFGHIJKLMNOPQRSTUVWXYZ012345&quot;,(
IF(FIND(MID(PRIORVALUE( Owner__c ),1,1), &quot;ABCDEFGHIJKLMNOPQRSTUVWXYZ&quot;)&gt;0,1,0)
+IF(FIND(MID(PRIORVALUE( Owner__c ),2,1), &quot;ABCDEFGHIJKLMNOPQRSTUVWXYZ&quot;)&gt;0,2,0)
+IF(FIND(MID(PRIORVALUE( Owner__c ),3,1), &quot;ABCDEFGHIJKLMNOPQRSTUVWXYZ&quot;)&gt;0,4,0)
+IF(FIND(MID(PRIORVALUE( Owner__c ),4,1), &quot;ABCDEFGHIJKLMNOPQRSTUVWXYZ&quot;)&gt;0,8,0)
+IF(FIND(MID(PRIORVALUE( Owner__c ),5,1), &quot;ABCDEFGHIJKLMNOPQRSTUVWXYZ&quot;)&gt;0,16,0)
)+1,1)
&amp; MID(&quot;ABCDEFGHIJKLMNOPQRSTUVWXYZ012345&quot;,(
IF(FIND(MID(PRIORVALUE( Owner__c ),6,1), &quot;ABCDEFGHIJKLMNOPQRSTUVWXYZ&quot;)&gt;0,1,0)
+IF(FIND(MID(PRIORVALUE( Owner__c ),7,1), &quot;ABCDEFGHIJKLMNOPQRSTUVWXYZ&quot;)&gt;0,2,0)
+IF(FIND(MID(PRIORVALUE( Owner__c ),8,1), &quot;ABCDEFGHIJKLMNOPQRSTUVWXYZ&quot;)&gt;0,4,0)
+IF(FIND(MID(PRIORVALUE( Owner__c ),9,1), &quot;ABCDEFGHIJKLMNOPQRSTUVWXYZ&quot;)&gt;0,8,0)
+IF(FIND(MID(PRIORVALUE( Owner__c ),10,1), &quot;ABCDEFGHIJKLMNOPQRSTUVWXYZ&quot;)&gt;0,16,0)
)+1,1)
&amp; MID(&quot;ABCDEFGHIJKLMNOPQRSTUVWXYZ012345&quot;,(
IF(FIND(MID(PRIORVALUE( Owner__c ),11,1), &quot;ABCDEFGHIJKLMNOPQRSTUVWXYZ&quot;)&gt;0,1,0)
+IF(FIND(MID(PRIORVALUE( Owner__c ),12,1), &quot;ABCDEFGHIJKLMNOPQRSTUVWXYZ&quot;)&gt;0,2,0)
+IF(FIND(MID(PRIORVALUE( Owner__c ),13,1), &quot;ABCDEFGHIJKLMNOPQRSTUVWXYZ&quot;)&gt;0,4,0)
+IF(FIND(MID(PRIORVALUE( Owner__c ),14,1), &quot;ABCDEFGHIJKLMNOPQRSTUVWXYZ&quot;)&gt;0,8,0)
+IF(FIND(MID(PRIORVALUE( Owner__c ),15,1), &quot;ABCDEFGHIJKLMNOPQRSTUVWXYZ&quot;)&gt;0,16,0)
)+1,1)</formula>
        <name>Bob Update First Assigned Rep Id</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Hold_Owner_expiration_date_set</fullName>
        <description>sets the Hold owner expiration date to 6 months from today</description>
        <field>Hold_Owner_Expiration__c</field>
        <formula>today() + 180</formula>
        <name>Hold Owner expiration date set</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Stamp_Bob_Line_Key_Field_Update</fullName>
        <description>Stamps the Bob Line Unique Key</description>
        <field>Bob_Line_Key__c</field>
        <formula>IF((In_Planning__c), LEFT(Account_Id__c, 15) + UPPER(Business_Unit__c), LEFT(Account_Id__c, 15) + UPPER(Business_Unit__c) + TEXT(NOW()))</formula>
        <name>Stamp Bob Line Key Field Update</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Update_Bob_Line_Assigned_Status_FU</fullName>
        <description>Update Bob Line Status to Assigned</description>
        <field>Status__c</field>
        <literalValue>Assigned</literalValue>
        <name>Update Bob Line Assigned Status FU</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Literal</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Update_Bob_Line_Name_Field_Update</fullName>
        <field>Name</field>
        <formula>Left(BoB_Assignment__r.Account__r.Name , 65)+&quot;_&quot;+ TEXT(BoB_Assignment__r.Business_Unit__c) +&quot;-&quot;+TEXT(Quarter__c)+&quot;-&quot;+TEXT(Year__c)</formula>
        <name>Update Bob Line Name Field Update</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Update_Bob_Line_Unassigned_Status_FU</fullName>
        <description>Update Bob Line Status to Unssigned</description>
        <field>Status__c</field>
        <literalValue>Unassigned</literalValue>
        <name>Update Bob Line Unassigned Status FU</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Literal</operation>
        <protected>false</protected>
    </fieldUpdates>
    <rules>
        <fullName>BoB Update Status for Pooled Unassigned</fullName>
        <actions>
            <name>Update_Bob_Line_Unassigned_Status_FU</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <description>If someone reassigns a bobline to Pool Unassigned, the Status will be set to Unassigned.  So if someone did it manually or via Chatter BoB, the same WFR would fire.</description>
        <formula>NOT(ISPICKVAL(Status__c,&apos;Unassigned&apos;)) &amp;&amp;(LEFT($Label.LTS_AE_Pool,15) == LEFT(Owner__c,15) ||  LEFT($Label.LSS_AE_Pool,15) == LEFT(Owner__c,15) || LEFT($Label.LMS_AE_Pool,15) == LEFT(Owner__c,15))</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>Bob Line Hold Expiration Rule</fullName>
        <active>true</active>
        <criteriaItems>
            <field>BoB_Line__c.Hold_Owner__c</field>
            <operation>notEqual</operation>
        </criteriaItems>
        <criteriaItems>
            <field>BoB_Line__c.Hold_Owner_Expiration__c</field>
            <operation>notEqual</operation>
        </criteriaItems>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
        <workflowTimeTriggers>
            <actions>
                <name>Bob_Line_Clear_Hold_Expiration_Field</name>
                <type>FieldUpdate</type>
            </actions>
            <actions>
                <name>Bob_Line_Clear_Hold_Owner_Field</name>
                <type>FieldUpdate</type>
            </actions>
            <offsetFromField>BoB_Line__c.Hold_Owner_Expiration__c</offsetFromField>
            <timeLength>0</timeLength>
            <workflowTimeTriggerUnit>Days</workflowTimeTriggerUnit>
        </workflowTimeTriggers>
    </rules>
    <rules>
        <fullName>Bob Update First Assigned Reason</fullName>
        <actions>
            <name>Bob_Update_First_Assigned_Reason</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <description>This workflow updates First Assigned Reason field with Assigned Reason when BOb line Assigned Reason is changed for the first time.</description>
        <formula>ISBLANK(First_Assigned_Reason__c)&amp;&amp; NOT(ISBLANK(TEXT(Assigned_Reason__c)))</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>Bob Update First Assigned Rep</fullName>
        <actions>
            <name>Bob_Update_First_Assigned_Rep</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Bob_Update_First_Assigned_Rep_Id</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <description>This workflow updates First Assigned Rep field with Assigned Rep when BOb line assignment is changed for the first time.</description>
        <formula>ISBLANK(First_Assigned_Rep__c )&amp;&amp; NOT(ISBLANK(Owner__c)) &amp;&amp; ISCHANGED(Owner__c) &amp;&amp; NOT(ISPICKVAL(Status__c,&apos;Unassigned&apos;))&amp;&amp; NOT(ISPICKVAL( PRIORVALUE(Status__c),&apos;Unassigned&apos;))</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>Stamp Bob Line Key Rule</fullName>
        <actions>
            <name>Stamp_Bob_Line_Key_Field_Update</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <description>Bob Line Key is concatenation of 15 digit Account Id + Business Unit</description>
        <formula>ISNEW() || ISCHANGED(Business_Unit__c) || ISCHANGED(BoB_Assignment__c) || ISCHANGED(In_Planning__c)</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>Update Bob Line Assigned Status</fullName>
        <actions>
            <name>Update_Bob_Line_Assigned_Status_FU</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <description>If a bob line previously with no owner or LinkedIn Solutions owner is updated to any other user, then assign the status to &quot;Assigned&quot;</description>
        <formula>ISCHANGED(Owner__c) &amp;&amp;  ISPICKVAL(Status__c,&apos;Unassigned&apos;) &amp;&amp; NOT(LEFT(Owner__c,15) == LEFT($Label.linkedin_solutions_user_id,15)) &amp;&amp; NOT(ISBLANK(Owner__c)) &amp;&amp; (ISBLANK(PRIORVALUE(Owner__c)) || (LEFT(PRIORVALUE(Owner__c),15) == LEFT($Label.linkedin_solutions_user_id,15)))</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>Update Bob Line Name Rule</fullName>
        <actions>
            <name>Update_Bob_Line_Name_Field_Update</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <criteriaItems>
            <field>BoB_Line__c.Name</field>
            <operation>notEqual</operation>
        </criteriaItems>
        <triggerType>onCreateOnly</triggerType>
    </rules>
    <rules>
        <fullName>Update Bob Line Unassigned Status</fullName>
        <actions>
            <name>Update_Bob_Line_Unassigned_Status_FU</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <description>If a bob line previously is updated to have no owner or LinkedIn Solutions owner from any other user, then update the status to &quot;Unassigned&quot;</description>
        <formula>ISCHANGED(Owner__c) &amp;&amp;  NOT(ISPICKVAL(Status__c,&apos;Unassigned&apos;)) &amp;&amp; (LEFT($Label.linkedin_solutions_user_id,15) == LEFT(Owner__c,15) || ISBLANK(Owner__c)) &amp;&amp; NOT(ISBLANK(PRIORVALUE(Owner__c))) &amp;&amp; (LEFT($Label.linkedin_solutions_user_id,15) != LEFT(PRIORVALUE(Owner__c),15))</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>Update Hold fields</fullName>
        <actions>
            <name>BOB_line_hold_owner_set</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Hold_Owner_expiration_date_set</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <description>Updates Hold fields if Assigned reason is changed to &quot;Hold&quot;</description>
        <formula>AND(  ISPICKVAL(Assigned_Reason__c, &apos;Hold&apos;),   ISBLANK(Hold_Owner__c),  ISBLANK(Hold_Owner_Expiration__c) )</formula>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
    </rules>
</Workflow>
