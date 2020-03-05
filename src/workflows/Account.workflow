<?xml version="1.0" encoding="UTF-8"?>
<Workflow xmlns="http://soap.sforce.com/2006/04/metadata">
    <alerts>
        <fullName>Account_Hold_Date</fullName>
        <ccEmails>sobjectcreation@2ax20tz92ytizuv8nvuhf5xtwnm8it3ioskw3uqslepb1kkhuq.3-1xrieae.na4.apex.salesforce.com</ccEmails>
        <description>Account Hold Date</description>
        <protected>false</protected>
        <senderType>CurrentUser</senderType>
        <template>SFDC_Cases/Account_Hold_Date</template>
    </alerts>
    <alerts>
        <fullName>Create_Case_when_LDC_Hold_Over_Expiration_Today</fullName>
        <ccEmails>sobjectdml@w-271frd1hgyli60aph3cm9l921456j99xlcrkompqibvmj58ps6.3-1xrieae.na4.apex.salesforce.com</ccEmails>
        <description>Create Case when LDC Hold Over Expiration &lt; Today</description>
        <protected>false</protected>
        <senderType>CurrentUser</senderType>
        <template>LDC/LDC_Hold_Over_Expiration</template>
    </alerts>
    <alerts>
        <fullName>Create_Case_when_LSS_Hold_Over_Expiration_Today</fullName>
        <ccEmails>sobjectdml@w-271frd1hgyli60aph3cm9l921456j99xlcrkompqibvmj58ps6.3-1xrieae.na4.apex.salesforce.com</ccEmails>
        <description>Create Case when LSS Hold Over Expiration &lt; Today</description>
        <protected>false</protected>
        <senderType>CurrentUser</senderType>
        <template>WorkflowTemplates/LSS_Hold_Over_Expiration</template>
    </alerts>
    <alerts>
        <fullName>Create_Case_when_LTS_Hold_Over_Expiration_Today</fullName>
        <ccEmails>sobjectdml@w-271frd1hgyli60aph3cm9l921456j99xlcrkompqibvmj58ps6.3-1xrieae.na4.apex.salesforce.com</ccEmails>
        <description>Create Case when LTS Hold Over Expiration &lt; Today</description>
        <protected>false</protected>
        <senderType>CurrentUser</senderType>
        <template>WorkflowTemplates/LTS_Hold_Over_Expiration</template>
    </alerts>
    <alerts>
        <fullName>CreditCard_RecurringChargesNotValid</fullName>
        <ccEmails>ar-receipts@linkedin.com</ccEmails>
        <description>CreditCard-RecurringChargesNotValid</description>
        <protected>false</protected>
        <recipients>
            <recipient>HS_Account Executive</recipient>
            <type>accountTeam</type>
        </recipients>
        <recipients>
            <recipient>HS_RM</recipient>
            <type>accountTeam</type>
        </recipients>
        <recipients>
            <recipient>LDC_Account Executive</recipient>
            <type>accountTeam</type>
        </recipients>
        <recipients>
            <recipient>LDC_RM</recipient>
            <type>accountTeam</type>
        </recipients>
        <senderType>CurrentUser</senderType>
        <template>CreditCheck_Templates/CreditCard_AuthorizationNeeded_Recurring_ChargesExpired</template>
    </alerts>
    <alerts>
        <fullName>CreditCheck_Application_Rquired</fullName>
        <ccEmails>creditapp@linkedin.com.example</ccEmails>
        <description>CreditCheck-Application Rquired</description>
        <protected>false</protected>
        <recipients>
            <recipient>HS_Account Executive</recipient>
            <type>accountTeam</type>
        </recipients>
        <recipients>
            <recipient>LDC_Account Executive</recipient>
            <type>accountTeam</type>
        </recipients>
        <senderType>CurrentUser</senderType>
        <template>CreditCheck_Templates/CreditCheck_MoreInfo</template>
    </alerts>
    <alerts>
        <fullName>CreditCheck_Approved</fullName>
        <ccEmails>creditapp@linkedin.com</ccEmails>
        <description>CreditCheck-Approved</description>
        <protected>false</protected>
        <recipients>
            <recipient>HS_Account Executive</recipient>
            <type>accountTeam</type>
        </recipients>
        <recipients>
            <recipient>LDC_Account Executive</recipient>
            <type>accountTeam</type>
        </recipients>
        <senderType>CurrentUser</senderType>
        <template>CreditCheck_Templates/CreditCheck_Approved</template>
    </alerts>
    <alerts>
        <fullName>CreditCheck_NOTApproved</fullName>
        <ccEmails>creditapp@linkedin.com</ccEmails>
        <description>CreditCheck-NOTApproved</description>
        <protected>false</protected>
        <recipients>
            <recipient>HS_Account Executive</recipient>
            <type>accountTeam</type>
        </recipients>
        <recipients>
            <recipient>LDC_Account Executive</recipient>
            <type>accountTeam</type>
        </recipients>
        <senderType>CurrentUser</senderType>
        <template>CreditCheck_Templates/CreditCheck_PrepayOnly</template>
    </alerts>
    <alerts>
        <fullName>Email_alert_Created_for_Do_Not_Sell_removal</fullName>
        <ccEmails>sobjectdml@w-271frd1hgyli60aph3cm9l921456j99xlcrkompqibvmj58ps6.3-1xrieae.na4.apex.salesforce.com</ccEmails>
        <description>Email alert - Created for Do Not Sell removal</description>
        <protected>false</protected>
        <senderType>CurrentUser</senderType>
        <template>Case_Email_Templates/DQ_CASE_Remove_Credit_hold_from_Do_Not_Sell_in_Account</template>
    </alerts>
    <alerts>
        <fullName>LDC_Free_Trial_Approval_Email_Alert</fullName>
        <description>LDC Free Trial Approval Email Alert</description>
        <protected>false</protected>
        <recipients>
            <field>Initial_Submitter__c</field>
            <type>email</type>
        </recipients>
        <senderType>CurrentUser</senderType>
        <template>ApprovalTemplates/LDC_Free_Trial_Approved</template>
    </alerts>
    <alerts>
        <fullName>LDC_Free_Trial_Rejection_Email_Alert</fullName>
        <description>LDC Free Trial Rejection Email Alert</description>
        <protected>false</protected>
        <recipients>
            <field>Initial_Submitter__c</field>
            <type>email</type>
        </recipients>
        <senderType>CurrentUser</senderType>
        <template>ApprovalTemplates/LDC_Free_Trial_Rejected</template>
    </alerts>
    <alerts>
        <fullName>Notify_AR_Payment_Terms_Have_Changed_AMER</fullName>
        <ccEmails>billingsupport@linkedin.com</ccEmails>
        <description>Notify AR - Payment Terms Have Changed_AMER</description>
        <protected>false</protected>
        <senderAddress>mail-noreply@linkedin.com</senderAddress>
        <senderType>OrgWideEmailAddress</senderType>
        <template>AR_Templates/Notify_AR_Payment_Terms_Have_Changed</template>
    </alerts>
    <alerts>
        <fullName>Notify_AR_Payment_Terms_Have_Changed_APAC</fullName>
        <ccEmails>billingsupport-intl@linkedin.com</ccEmails>
        <description>Notify AR - Payment Terms Have Changed_APAC</description>
        <protected>false</protected>
        <senderAddress>mail-noreply@linkedin.com</senderAddress>
        <senderType>OrgWideEmailAddress</senderType>
        <template>AR_Templates/Notify_AR_Payment_Terms_Have_Changed</template>
    </alerts>
    <alerts>
        <fullName>Use_sObjectDML_Email_Service_to_update_Insight_s_CEG_field_to_false</fullName>
        <ccEmails>sobjectdml@w-271frd1hgyli60aph3cm9l921456j99xlcrkompqibvmj58ps6.3-1xrieae.na4.apex.salesforce.com</ccEmails>
        <description>Use sObjectDML Email Service to update Insight&apos;s CEG field to false</description>
        <protected>false</protected>
        <senderType>CurrentUser</senderType>
        <template>WorkflowTemplates/Insights_Update_CEG_flag_to_false</template>
    </alerts>
    <alerts>
        <fullName>Use_sObjectDML_Email_Service_to_update_Insight_s_CEG_field_to_true</fullName>
        <ccEmails>sobjectdml@w-271frd1hgyli60aph3cm9l921456j99xlcrkompqibvmj58ps6.3-1xrieae.na4.apex.salesforce.com</ccEmails>
        <description>Use sObjectDML Email Service to update Insight&apos;s CEG field to true</description>
        <protected>false</protected>
        <senderType>CurrentUser</senderType>
        <template>WorkflowTemplates/Insights_Update_CEG_flag_to_true</template>
    </alerts>
    <fieldUpdates>
        <fullName>Account_RType_to_Inactive</fullName>
        <field>RecordTypeId</field>
        <lookupValue>Inactive</lookupValue>
        <lookupValueType>RecordType</lookupValueType>
        <name>Account RType to Inactive</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>LookupValue</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Account_Source_to_Account_Drip</fullName>
        <description>Tag as Account Drip</description>
        <field>AccountSource</field>
        <literalValue>Account Drip</literalValue>
        <name>Account Source to Account Drip</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Literal</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Cleaning_Is_Hold_field</fullName>
        <description>https://linkedin.my.salesforce.com/5003200000rTqEi</description>
        <field>Is_Hold_button_used__c</field>
        <literalValue>0</literalValue>
        <name>Cleaning Is Hold field</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Literal</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Clear_PT_Approval</fullName>
        <field>Payment_Terms_Approved__c</field>
        <literalValue>0</literalValue>
        <name>Clear PT Approval</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Literal</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Data_com_Override_All_Employees</fullName>
        <field>NumberOfEmployees</field>
        <formula>IF(
AND(
NOT(ISBLANK(DandbCompany.EmployeesTotal)),
NumberOfEmployees &lt;&gt; CEILING(DandbCompany.EmployeesTotal)
),
CEILING(DandbCompany.EmployeesTotal),
NumberOfEmployees
)</formula>
        <name>Data.com - Override All Employees</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Data_com_Override_Annual_Revenue</fullName>
        <field>AnnualRevenue</field>
        <formula>IF(
ISBLANK(DandbCompany.SalesVolume),
AnnualRevenue, 
DandbCompany.SalesVolume
)</formula>
        <name>Data.com - Override Annual Revenue</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Data_com_Override_BillingStateProvince</fullName>
        <field>BillingState</field>
        <formula>IF(
AND(
NOT(ISBLANK(DandbCompany.State)),
BillingState &lt;&gt; DandbCompany.State
),
DandbCompany.State,
BillingState
)</formula>
        <name>Data.com - Override BillingStateProvince</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
        <reevaluateOnChange>true</reevaluateOnChange>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Data_com_Override_Billing_City</fullName>
        <field>BillingCity</field>
        <formula>IF(
AND(
NOT(ISBLANK(DandbCompany.City)),
BillingCity &lt;&gt; DandbCompany.City
),
DandbCompany.City,
BillingCity
)</formula>
        <name>Data.com - Override Billing City</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Data_com_Override_Billing_Country</fullName>
        <field>BillingCountry</field>
        <formula>IF(
AND(
NOT(ISBLANK(DandbCompany.Country)),
BillingCountry &lt;&gt; DandbCompany.Country
),
DandbCompany.Country,
BillingCountry
)</formula>
        <name>Data.com - Override Billing Country</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
        <reevaluateOnChange>true</reevaluateOnChange>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Data_com_Override_Billing_Street</fullName>
        <field>BillingStreet</field>
        <formula>IF(
AND(
NOT(ISBLANK(DandbCompany.Street)),
BillingStreet &lt;&gt; DandbCompany.Street
),
DandbCompany.Street,
BillingStreet
)</formula>
        <name>Data.com - Override Billing Street</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Data_com_Override_Billing_ZipCode</fullName>
        <field>BillingPostalCode</field>
        <formula>IF(
AND(
NOT(ISBLANK(DandbCompany.PostalCode)),
BillingPostalCode &lt;&gt; DandbCompany.PostalCode
),
DandbCompany.PostalCode,
BillingPostalCode
)</formula>
        <name>Data.com - Override Billing ZipCode</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Data_com_Override_DUNS</fullName>
        <field>D_U_N_S__c</field>
        <formula>IF(
AND(
NOT(ISBLANK(DandbCompany.DunsNumber)),
D_U_N_S__c &lt;&gt; DandbCompany.DunsNumber
),
DandbCompany.DunsNumber,
D_U_N_S__c
)</formula>
        <name>Data.com - Override DUNS</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Data_com_Override_Doing_Business_As</fullName>
        <field>Doing_Business_As__c</field>
        <formula>IF(
ISBLANK(DandbCompany.TradeStyle1),
Doing_Business_As__c,
(
IF(
Doing_Business_As__c &lt;&gt;(IF(ISBLANK(DandbCompany.TradeStyle1),&quot;&quot;,DandbCompany.TradeStyle1)&amp;
IF(ISBLANK(DandbCompany.TradeStyle2),&quot;&quot;,&quot;, &quot;&amp;DandbCompany.TradeStyle2)&amp;
IF(ISBLANK(DandbCompany.TradeStyle3),&quot;&quot;,&quot;, &quot;&amp;DandbCompany.TradeStyle3)&amp;
IF(ISBLANK(DandbCompany.TradeStyle4),&quot;&quot;,&quot;, &quot;&amp;DandbCompany.TradeStyle4)&amp;
IF(ISBLANK(DandbCompany.TradeStyle5),&quot;&quot;,&quot;, &quot;&amp;DandbCompany.TradeStyle5)),
(IF(ISBLANK(DandbCompany.TradeStyle1),&quot;&quot;,DandbCompany.TradeStyle1)&amp;
IF(ISBLANK(DandbCompany.TradeStyle2),&quot;&quot;,&quot;, &quot;&amp;DandbCompany.TradeStyle2)&amp;
IF(ISBLANK(DandbCompany.TradeStyle3),&quot;&quot;,&quot;, &quot;&amp;DandbCompany.TradeStyle3)&amp;
IF(ISBLANK(DandbCompany.TradeStyle4),&quot;&quot;,&quot;, &quot;&amp;DandbCompany.TradeStyle4)&amp;
IF(ISBLANK(DandbCompany.TradeStyle5),&quot;&quot;,&quot;, &quot;&amp;DandbCompany.TradeStyle5)),
Doing_Business_As__c 
)
)
)</formula>
        <name>Data.com - Override Doing Business As</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Data_com_Override_Employees_This_Site</fullName>
        <field>Employees_This_Site__c</field>
        <formula>IF(
ISBLANK(DandbCompany.EmployeesHere),
Employees_This_Site__c ,
DandbCompany.EmployeesHere
)</formula>
        <name>Data.com - Override Employees This Site</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Data_com_Override_Fax_Number</fullName>
        <field>Fax</field>
        <formula>IF(
AND(
NOT(ISBLANK(DandbCompany.Fax)),
Fax &lt;&gt; DandbCompany.Fax
),
DandbCompany.Fax,
Fax
)</formula>
        <name>Data.com - Override Fax Number</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Data_com_Override_Hoovers_Account_Name</fullName>
        <field>Hoovers_Account_Name__c</field>
        <formula>IF(
AND(
NOT(ISBLANK(DandbCompany.Name)),
Hoovers_Account_Name__c &lt;&gt; DandbCompany.Name
),
DandbCompany.Name,
Hoovers_Account_Name__c
)</formula>
        <name>Data.com - Override Hoovers Account Name</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Data_com_Override_ImmediateParent_DUNS</fullName>
        <field>Immediate_Parent_DUNS__c</field>
        <formula>IF(
AND(
NOT(ISBLANK(DandbCompany.ParentOrHqDunsNumber)), 
DandbCompany.ParentOrHqDunsNumber &lt;&gt; &apos;000000000&apos;),
DandbCompany.ParentOrHqDunsNumber,
DandbCompany.GlobalUltimateDunsNumber
)</formula>
        <name>Data.com - Override ImmediateParent DUNS</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Data_com_Override_ImmediateParent_Name</fullName>
        <field>Immediate_Parent_Name__c</field>
        <formula>IF(
AND(
NOT(ISBLANK(DandbCompany.ParentOrHqBusinessName)),
Immediate_Parent_Name__c &lt;&gt; DandbCompany.ParentOrHqBusinessName
), 
DandbCompany.ParentOrHqBusinessName,
Immediate_Parent_Name__c
)</formula>
        <name>Data.com - Override ImmediateParent Name</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Data_com_Override_NAICS_Code</fullName>
        <field>NAICS__c</field>
        <formula>IF(
AND(
NOT(ISBLANK(DandbCompany.PrimaryNaics)),
NAICS__c &lt;&gt; DandbCompany.PrimaryNaics
),
DandbCompany.PrimaryNaics,
NAICS__c
)</formula>
        <name>Data.com - Override NAICS Code</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Data_com_Override_Phone_Number</fullName>
        <field>Phone</field>
        <formula>IF(
AND(
NOT(ISBLANK(DandbCompany.Phone)),
Phone &lt;&gt; DandbCompany.Phone
),
DandbCompany.Phone,
Phone
)</formula>
        <name>Data.com - Override Phone Number</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Data_com_Override_SIC_Code</fullName>
        <field>Sic</field>
        <formula>IF(
AND(
NOT(ISBLANK(DandbCompany.PrimarySic)),
Sic &lt;&gt; DandbCompany.PrimarySic
),
DandbCompany.PrimarySic,
Sic
)</formula>
        <name>Data.com - Override SIC Code</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Data_com_Override_Ship_StateProvince</fullName>
        <field>ShippingState</field>
        <formula>IF(
AND(
NOT(ISBLANK(DandbCompany.State)),
ShippingState &lt;&gt; DandbCompany.State
),
DandbCompany.State,
ShippingState 
)</formula>
        <name>Data.com - Override Ship StateProvince</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
        <reevaluateOnChange>true</reevaluateOnChange>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Data_com_Override_Shipping_City</fullName>
        <field>ShippingCity</field>
        <formula>IF(
AND(
NOT(ISBLANK(DandbCompany.City)),
ShippingCity &lt;&gt; DandbCompany.City
),
DandbCompany.City,
ShippingCity
)</formula>
        <name>Data.com - Override Shipping City</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Data_com_Override_Shipping_Country</fullName>
        <field>ShippingCountry</field>
        <formula>IF(
AND(
NOT(ISBLANK(DandbCompany.Country)),
ShippingCountry &lt;&gt; DandbCompany.Country
),
DandbCompany.Country,
ShippingCountry
)</formula>
        <name>Data.com - Override Shipping Country</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
        <reevaluateOnChange>true</reevaluateOnChange>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Data_com_Override_Shipping_Street</fullName>
        <field>ShippingStreet</field>
        <formula>IF(
AND(
NOT(ISBLANK(DandbCompany.Street)),
ShippingStreet &lt;&gt; DandbCompany.Street
),
DandbCompany.Street,
ShippingStreet
)</formula>
        <name>Data.com - Override Shipping Street</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Data_com_Override_Shipping_ZipCode</fullName>
        <field>ShippingPostalCode</field>
        <formula>IF(
AND(
NOT(ISBLANK(DandbCompany.PostalCode)),
ShippingPostalCode &lt;&gt; DandbCompany.PostalCode
),
DandbCompany.PostalCode,
ShippingPostalCode
)</formula>
        <name>Data.com - Override Shipping ZipCode</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Data_com_Override_UltimateParent_DUNS</fullName>
        <field>Ultimate_Parent_DUNS__c</field>
        <formula>IF(
AND(
NOT(ISBLANK(DandbCompany.GlobalUltimateDunsNumber)),
Ultimate_Parent_DUNS__c &lt;&gt; DandbCompany.GlobalUltimateDunsNumber
),
DandbCompany.GlobalUltimateDunsNumber,
Ultimate_Parent_DUNS__c
)</formula>
        <name>Data.com - Override UltimateParent DUNS</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Data_com_Override_UltimateParent_Name</fullName>
        <description>Update Hoover&apos;s Ultimate Parent Name with D&amp;B&apos;s Global Parent Name</description>
        <field>Ultimate_Parent_Name__c</field>
        <formula>IF(
AND(
NOT(ISBLANK(DandbCompany.GlobalUltimateBusinessName)),
Ultimate_Parent_Name__c &lt;&gt; DandbCompany.GlobalUltimateBusinessName
),
DandbCompany.GlobalUltimateBusinessName,
Ultimate_Parent_Name__c
)</formula>
        <name>Data.com - Override UltimateParent Name</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Data_com_Override_Website</fullName>
        <field>Website</field>
        <formula>IF(
AND(
NOT(ISBLANK(DandbCompany.URL)),
Website &lt;&gt; DandbCompany.URL
),
DandbCompany.URL,
Website
)</formula>
        <name>Data.com - Override Website</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Data_com_Update_Bill_Country_If_Blank</fullName>
        <description>Update Billing Country with D&amp;B&apos;s Country</description>
        <field>BillingCountry</field>
        <formula>IF(
ISBLANK(BillingCountry),
DandbCompany.Country,
BillingCountry
)</formula>
        <name>Data.com - Update Bill Country If Blank</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Data_com_Update_Bill_State_If_Blank</fullName>
        <description>Update Billing State/Province with D&amp;B&apos;s State/Province</description>
        <field>BillingState</field>
        <formula>IF( 
ISBLANK(BillingState),
DandbCompany.State,
BillingState
)</formula>
        <name>Data.com - Update Bill State If  Blank</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Data_com_Update_Billing_City_If_Blank</fullName>
        <description>Update Billing City with D&amp;B&apos;s Billing City</description>
        <field>BillingCity</field>
        <formula>IF(
ISBLANK(BillingCity),
DandbCompany.City,
BillingCity
)</formula>
        <name>Data.com - Update Billing City If Blank</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Data_com_Update_Billing_St_If_Blank</fullName>
        <description>Update Billing Street to D&amp;B&apos;s Street</description>
        <field>BillingStreet</field>
        <formula>IF(
ISBLANK(BillingStreet),
DandbCompany.Street,
BillingStreet
)</formula>
        <name>Data.com - Update Billing St If Blank</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Data_com_Update_Billing_Zip_If_Blank</fullName>
        <description>Update Zip code with D&amp;B&apos;s Postal Code</description>
        <field>BillingPostalCode</field>
        <formula>IF(
ISBLANK(BillingPostalCode),
DandbCompany.PostalCode,
BillingPostalCode
)</formula>
        <name>Data.com - Update Billing Zip If Blank</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Data_com_Update_DBA_if_Blank</fullName>
        <description>Update DBA with concatenated D&amp;B DBAs</description>
        <field>Doing_Business_As__c</field>
        <formula>IF(
ISBLANK(Doing_Business_As__c), 
IF(ISBLANK(DandbCompany.TradeStyle1),&quot;&quot;,DandbCompany.TradeStyle1)&amp;
IF(ISBLANK(DandbCompany.TradeStyle2),&quot;&quot;,&quot;, &quot;&amp;DandbCompany.TradeStyle2)&amp;
IF(ISBLANK(DandbCompany.TradeStyle3),&quot;&quot;,&quot;, &quot;&amp;DandbCompany.TradeStyle3)&amp;
IF(ISBLANK(DandbCompany.TradeStyle4),&quot;&quot;,&quot;, &quot;&amp;DandbCompany.TradeStyle4)&amp;
IF(ISBLANK(DandbCompany.TradeStyle5),&quot;&quot;,&quot;, &quot;&amp;DandbCompany.TradeStyle5)
,
Doing_Business_As__c
)</formula>
        <name>Data.com - Update DBA If Blank</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Data_com_Update_Fax_Number_If_Blank</fullName>
        <description>Update Fax Number with D&amp;B&apos;s Facsimilie</description>
        <field>Fax</field>
        <formula>IF(
ISBLANK(Fax),
DandbCompany.Fax,
Fax
)</formula>
        <name>Data.com - Update Fax Number If Blank</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Data_com_Update_Phone_If_Blank</fullName>
        <description>Update Phone with D&amp;B&apos;s Phone</description>
        <field>Phone</field>
        <formula>IF(
ISBLANK(Phone),
DandbCompany.Phone,
Phone
)</formula>
        <name>Data.com - Update Phone If Blank</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Data_com_Update_Ship_Country_If_Blank</fullName>
        <description>Update Shipping Country with D&amp;B&apos;s Country</description>
        <field>ShippingCountry</field>
        <formula>IF(
ISBLANK(ShippingCountry),
DandbCompany.Country,
ShippingCountry
)</formula>
        <name>Data.com - Update Ship Country If Blank</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Data_com_Update_Ship_State_If_Blank</fullName>
        <description>Update shipping state/province with D&amp;B&apos;s  State/Province</description>
        <field>ShippingState</field>
        <formula>IF(
ISBLANK(ShippingState),
DandbCompany.State,
ShippingState
)</formula>
        <name>Data.com - Update Ship State If Blank</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Data_com_Update_Shipping_City_If_Blank</fullName>
        <description>Update Shipping City with D&amp;B&apos;s City</description>
        <field>ShippingCity</field>
        <formula>IF(
ISBLANK(ShippingCity),
DandbCompany.City,
ShippingCity
)</formula>
        <name>Data.com - Update Shipping City If Blank</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Data_com_Update_Shipping_St_If_Blank</fullName>
        <description>Update Shipping Street with D&amp;B&apos;s Shipping Street</description>
        <field>ShippingStreet</field>
        <formula>IF(
ISBLANK(ShippingStreet),
DandbCompany.Street,
ShippingStreet
)</formula>
        <name>Data.com - Update Shipping St If Blank</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Data_com_Update_Shipping_Zip_If_Blank</fullName>
        <description>Update shipping zip/postal code with D&amp;B&apos;s zip code</description>
        <field>ShippingPostalCode</field>
        <formula>IF(
ISBLANK(ShippingPostalCode),
DandbCompany.PostalCode,
ShippingPostalCode
)</formula>
        <name>Data.com - Update Shipping Zip If Blank</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Data_com_Update_Website_If_Blank</fullName>
        <description>Update website with D&amp;B&apos;s URL</description>
        <field>Website</field>
        <formula>IF(
ISBLANK(Website),
DandbCompany.URL,
Website
)</formula>
        <name>Data.com - Update Website If Blank</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Initial_Submitter</fullName>
        <field>Initial_Submitter__c</field>
        <formula>$User.Email</formula>
        <name>Initial Submitter</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>LDC_Upd_Free_Trial_Limit_Approval_Date</fullName>
        <field>LDC_Free_Trial_Limit_Approval_Date__c</field>
        <formula>TODAY()</formula>
        <name>LDC Upd Free Trial Limit Approval Date</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Leo_Verified_field_update_to_Nonexistent</fullName>
        <field>gso_LEO_Verified__c</field>
        <literalValue>Nonexistent</literalValue>
        <name>Leo Verified field update to Nonexistent</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Literal</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Leo_Verified_field_update_to_Parent</fullName>
        <field>gso_LEO_Verified__c</field>
        <literalValue>Parent</literalValue>
        <name>Leo Verified field update to &apos;Parent&apos;</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Literal</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Leo_Verified_field_update_to_Verified</fullName>
        <field>gso_LEO_Verified__c</field>
        <literalValue>Verified</literalValue>
        <name>Leo Verified field update to &apos;Verified&apos;</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Literal</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>OFAC_Do_Not_Sell</fullName>
        <description>OFAC account that is listed on the Office of Foreign Assets Control</description>
        <field>Do_Not_Sell_Reason__c</field>
        <literalValue>OFAC</literalValue>
        <name>OFAC- Do Not Sell</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Literal</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Owner_to_LinkedIn_Solutions</fullName>
        <field>OwnerId</field>
        <lookupValue>sfdcadmin@linkedin.com</lookupValue>
        <lookupValueType>User</lookupValueType>
        <name>Owner to LinkedIn Solutions</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>LookupValue</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Payment_Terms_30_days</fullName>
        <description>Change Payment Terms to 30 days</description>
        <field>Payment_Terms__c</field>
        <literalValue>30 Days</literalValue>
        <name>Payment Terms = 30 days</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Literal</operation>
        <protected>false</protected>
        <reevaluateOnChange>true</reevaluateOnChange>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Populate_Do_Not_Sell_Credit_Hold</fullName>
        <description>Automatically updates the Do Not Sell field to &quot;Credit Hold&quot; on an account once the Credit Check Status is updated to &quot;Credit Hold - Do Not Sell.&quot;</description>
        <field>Do_Not_Sell_Reason__c</field>
        <literalValue>Credit Hold</literalValue>
        <name>Populate Do Not Sell: Credit Hold</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Literal</operation>
        <protected>false</protected>
        <reevaluateOnChange>true</reevaluateOnChange>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Relationship_Type_Concatenator_Update</fullName>
        <field>Relationship_Type_Concatenator__c</field>
        <formula>IF( INCLUDES( Relationship_Type__c , &apos;SBS Low Touch Account&apos;), &apos;SBS Low Touch Account;&apos;,&quot;&quot;) &amp;
IF( INCLUDES( Relationship_Type__c , &apos;Agency - MS&apos;), &apos;Agency - MS;&apos;,&quot;&quot;) &amp;
IF( INCLUDES( Relationship_Type__c , &apos;Agency - TS&apos;), &apos;Agency - TS;&apos;,&quot;&quot;) &amp;
IF( INCLUDES( Relationship_Type__c , &apos;Federal&apos;), &apos;Federal;&apos;,&quot;&quot;) &amp;
IF( INCLUDES( Relationship_Type__c , &apos;Competitor - HS&apos;), &apos;Competitor - HS;&apos;,&quot;&quot;)&amp;
IF( INCLUDES( Relationship_Type__c , &apos;Competitor - LSS&apos;), &apos;Competitor - LSS;&apos;,&quot;&quot;)&amp;
IF( INCLUDES( Relationship_Type__c , &apos;Competitor - MS&apos;), &apos;Competitor - MS;&apos;,&quot;&quot;)&amp;
IF( INCLUDES( Relationship_Type__c , &apos;HS_AR&apos;), &apos;HS_AR;&apos;,&quot;&quot;)&amp;
IF( INCLUDES( Relationship_Type__c , &apos;Nonprofit&apos;), &apos;Nonprofit;&apos;,&quot;&quot;)&amp;
IF( INCLUDES( Relationship_Type__c , &apos;Local Govt&apos;), &apos;Local Govt;&apos;,&quot;&quot;)&amp;
IF( INCLUDES( Relationship_Type__c , &apos;Partner&apos;), &apos;Partner;&apos;,&quot;&quot;)&amp;
IF( INCLUDES( Relationship_Type__c , &apos;Staffing&apos;), &apos;Staffing;&apos;,&quot;&quot;)&amp;
IF( INCLUDES( Relationship_Type__c , &apos;Online&apos;), &apos;Online;&apos;,&quot;&quot;)&amp;
IF( INCLUDES( Relationship_Type__c , &apos;RPO&apos;), &apos;RPO;&apos;,&quot;&quot;)&amp;
IF( INCLUDES( Relationship_Type__c , &apos;SOPS Override&apos;), &apos;SOPS Override;&apos;,&quot;&quot;)&amp;
IF( INCLUDES( Relationship_Type__c , &apos;Rosetta - DN&apos;), &apos;Rosetta - DN;&apos;,&quot;&quot;)&amp;
IF( INCLUDES( Relationship_Type__c , &apos;GoldRush&apos;), &apos;GoldRush;&apos;,&quot;&quot;)&amp;
IF( INCLUDES( Relationship_Type__c , &apos;Data.com&apos;), &apos;Data.com;&apos;,&quot;&quot;)&amp;
IF( INCLUDES( Relationship_Type__c , &apos;TS_Auto Renewal&apos;), &apos;TS_Auto Renewal;&apos;,&quot;&quot;)&amp;
IF( INCLUDES( Relationship_Type__c , &apos;Darwin Test Batch #1&apos;), &apos;Darwin Test Batch #1;&apos;,&quot;&quot;) &amp;
IF( INCLUDES( Relationship_Type__c , &apos;Darwin Test Batch #2&apos;), &apos;Darwin Test Batch #2;&apos;,&quot;&quot;) &amp;
IF( INCLUDES( Relationship_Type__c , &apos;Darwin Test Batch #3&apos;), &apos;Darwin Test Batch #3;&apos;,&quot;&quot;) &amp;
IF( INCLUDES( Relationship_Type__c , &apos;Zeppelin&apos;), &apos;Zeppelin;&apos;,&quot;&quot;) &amp;
IF( INCLUDES( Relationship_Type__c , &apos;Active RM Subsidiary - NSO&apos;), &apos;Active RM Subsidiary - NSO;&apos;,&quot;&quot;) &amp;
IF( INCLUDES( Relationship_Type__c , &apos;SMB Solution Pilot&apos;), &apos;SMB Solution Pilot;&apos;,&quot;&quot;) &amp;
IF( INCLUDES( Relationship_Type__c , &apos;Holding Company&apos;), &apos;Holding Company;&apos;,&quot;&quot;) &amp;
IF( INCLUDES( Relationship_Type__c , &apos;RPO-Client&apos;), &apos;RPO-Client;&apos;,&quot;&quot;) &amp;
IF( INCLUDES( Relationship_Type__c , &apos;D&amp;B Sept 2017 Ingestion&apos;), &apos;D&amp;B Sept 2017 Ingestion;&apos;,&quot;&quot;) &amp;
IF( INCLUDES( Relationship_Type__c , &apos;LSO - LTS&apos;), &apos;LSO - LTS;&apos;,&quot;&quot;) &amp;
IF( INCLUDES( Relationship_Type__c , &apos;Investor Education&apos;), &apos;Investor Education;&apos;,&quot;&quot;) &amp; 
IF( INCLUDES( Relationship_Type__c , &apos;GSA&apos;), &apos;GSA;&apos;,&quot;&quot;) &amp; 
IF( INCLUDES( Relationship_Type__c , &apos;SEWP&apos;), &apos;SEWP;&apos;,&quot;&quot;) &amp;
IF( INCLUDES( Relationship_Type__c , &apos;Competitor - LDC&apos;), &apos;Competitor - LDC;&apos;,&quot;&quot;) &amp;
IF( INCLUDES( Relationship_Type__c , &apos;LDC GSA&apos;), &apos;LDC GSA;&apos;,&quot;&quot;) &amp;
IF( INCLUDES( Relationship_Type__c , &apos;Project Monstro&apos;), &apos;Project Monstro;&apos;,&quot;&quot;) &amp; 
IF( INCLUDES( Relationship_Type__c , &apos;Topple - Skillsoft&apos;), &apos;Topple - Skillsoft;&apos;,&quot;&quot;) &amp;
IF( INCLUDES( Relationship_Type__c , &apos;LSS Co-Sell&apos;), &apos;LSS Co-Sell;&apos;,&quot;&quot;) &amp;
IF( INCLUDES( Relationship_Type__c , &apos;LSO - LLS&apos;), &apos;LSO - LLS;&apos;,&quot;&quot;)  &amp; 
IF( INCLUDES( Relationship_Type__c , &apos;NAMER KAM&apos;), &apos;NAMER KAM;&apos;,&quot;&quot;) &amp;
IF( INCLUDES( Relationship_Type__c , &apos;Jerboa&apos;), &apos;Jerboa;&apos;,&quot;&quot;) &amp;
IF( INCLUDES( Relationship_Type__c , &apos;Project Chrysalis&apos;), &apos;Project Chrysalis;&apos;,&quot;&quot;) &amp; 
IF( INCLUDES( Relationship_Type__c , &apos;MS - Sales House&apos;), &apos;MS - Sales House;&apos;,&quot;&quot;) &amp; 
IF( INCLUDES( Relationship_Type__c , &apos;GR-scrubbed&apos;), &apos;GR-scrubbed;&apos;,&quot;&quot;)&amp; 
IF( INCLUDES( Relationship_Type__c , &apos;2020 Strategic Account&apos;), &apos;2020 Strategic Account;&apos;,&quot;&quot;) &amp; 
IF ( INCLUDES( Relationship_Type__c , &apos;2020 Strategic Child Account&apos;), &apos;2020 Strategic Child Account;&apos;,&quot;&quot;) &amp; 
IF( INCLUDES( Relationship_Type__c , &apos;2021 Strategic Account&apos;), &apos;2021 Strategic Account;&apos;,&quot;&quot;) &amp; 
IF ( INCLUDES( Relationship_Type__c , &apos;2021 Strategic Child Account&apos;), &apos;2021 Strategic Child Account;&apos;,&quot;&quot;) &amp;
IF( INCLUDES( Relationship_Type__c , &apos;Bilingual&apos;), &apos;Bilingual;&apos;,&quot;&quot;) &amp;
IF( INCLUDES( Relationship_Type__c , &apos;Ignite-scrubbed&apos;), &apos;Ignite-scrubbed;&apos;,&quot;&quot;)</formula>
        <name>Relationship Type Concatenator Update</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Revert_PT</fullName>
        <description>Sets account payment terms to &quot;rejected&quot; which kicks off flow to revert payment terms</description>
        <field>Payment_Terms__c</field>
        <literalValue>Rejected</literalValue>
        <name>Revert PT</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Literal</operation>
        <protected>false</protected>
        <reevaluateOnChange>true</reevaluateOnChange>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Set_Do_Not_Sell_to_blank</fullName>
        <field>Do_Not_Sell_Reason__c</field>
        <name>Set Do Not Sell to blank</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Literal</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Set_Employee_Range_10001</fullName>
        <description>Employee Range - 10001+</description>
        <field>Employee_Range__c</field>
        <literalValue>10001+</literalValue>
        <name>Set Employee Range - 10001+</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Literal</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Set_Employee_Range_1001_5000</fullName>
        <description>Set Employee Range 1001-5000</description>
        <field>Employee_Range__c</field>
        <literalValue>1001-5000</literalValue>
        <name>Set Employee Range 1001-5000</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Literal</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Set_Employee_Range_11_50</fullName>
        <description>Update Set Employee Range 11-50</description>
        <field>Employee_Range__c</field>
        <literalValue>11-50</literalValue>
        <name>Set Employee Range 11-50</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Literal</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Set_Employee_Range_1_10</fullName>
        <description>Set Employee Range 1-10</description>
        <field>Employee_Range__c</field>
        <literalValue>1-10</literalValue>
        <name>Set Employee Range 1-10</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Literal</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Set_Employee_Range_201_500</fullName>
        <description>Set Employee Range 201-500</description>
        <field>Employee_Range__c</field>
        <literalValue>201-500</literalValue>
        <name>Set Employee Range 201-500</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Literal</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Set_Employee_Range_5001_10000</fullName>
        <description>Set Employee Range 5001-10000</description>
        <field>Employee_Range__c</field>
        <literalValue>5001-10000</literalValue>
        <name>Set Employee Range 5001-10000</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Literal</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Set_Employee_Range_501_1000</fullName>
        <description>Employee Range - 501-1000</description>
        <field>Employee_Range__c</field>
        <literalValue>501-1000</literalValue>
        <name>Set Employee Range - 501-1000</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Literal</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Set_Employee_Range_51_200</fullName>
        <description>Set Employee Range 51-200</description>
        <field>Employee_Range__c</field>
        <literalValue>51-200</literalValue>
        <name>Set Employee Range 51-200</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Literal</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Set_Employees_based_on_Employee_Range</fullName>
        <description>Set Employees based on Employee Range</description>
        <field>NumberOfEmployees</field>
        <formula>IF( 
ISPICKVAL(Employee_Range__c,&apos;1&apos;) , 1,
if (ISPICKVAL(Employee_Range__c,&apos;1-10&apos;) , 1, 
if (ISPICKVAL(Employee_Range__c,&apos;11-50&apos;) , 11, 
if (ISPICKVAL(Employee_Range__c,&apos;51-200&apos;) , 51, 
if (ISPICKVAL(Employee_Range__c,&apos;201-500&apos;) , 201, 
if (ISPICKVAL(Employee_Range__c,&apos;501-1000&apos;) , 501, 
if (ISPICKVAL(Employee_Range__c,&apos;1001-5000&apos;) , 1001, 
if (ISPICKVAL(Employee_Range__c,&apos;5001-10000&apos;) , 5001, 
if (ISPICKVAL(Employee_Range__c,&apos;10001+&apos;) , 10001, 
null)))))))))</formula>
        <name>Set Employees based on Employee Range</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Set_Geographic_Territory_APAC</fullName>
        <field>Geographic_Territory__c</field>
        <literalValue>APAC</literalValue>
        <name>Set Geographic Territory- APAC</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Literal</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Set_Geographic_Territory_EMEA</fullName>
        <field>Geographic_Territory__c</field>
        <literalValue>EMEA</literalValue>
        <name>Set Geographic Territory- EMEA</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Literal</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Set_Geographic_Territory_LATAM</fullName>
        <field>Geographic_Territory__c</field>
        <literalValue>LATAM</literalValue>
        <name>Set Geographic Territory- LATAM</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Literal</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Set_Geographic_Territory_US</fullName>
        <field>Geographic_Territory__c</field>
        <literalValue>US</literalValue>
        <name>Set Geographic Territory- US</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Literal</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Set_PO_Threshold_to_Blank</fullName>
        <field>PO_Threshold__c</field>
        <name>Set PO Threshold to Blank</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Null</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Set_PT_Approved</fullName>
        <field>Payment_Terms_Approved__c</field>
        <literalValue>1</literalValue>
        <name>Set PT Approved</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Literal</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Set_Payment_Terms_to_60</fullName>
        <field>Payment_Terms__c</field>
        <literalValue>60 Days</literalValue>
        <name>Set Payment Terms to 60</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Literal</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Set_SD_Target_LHS_to_Y</fullName>
        <description>See WFR of same name</description>
        <field>SD_Target__c</field>
        <literalValue>Y</literalValue>
        <name>Set SD Target LHS to Y</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Literal</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Tier_Field_Update</fullName>
        <field>Tier__c</field>
        <literalValue>Strategic</literalValue>
        <name>Tier Field Update</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Literal</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Update_Account_Highest_Accuracy_Score</fullName>
        <field>LI_Highest_Accuracy_Score__c</field>
        <formula>LI_Accuracy_Score__c</formula>
        <name>Update Account_Highest Accuracy Score</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Update_Account_Main_Fields_Last_Modified</fullName>
        <field>Account_Main_Fields_Last_Modified_Date__c</field>
        <formula>now()</formula>
        <name>Update Account Main Fields Last Modified</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Update_DUNS_Number_Custom</fullName>
        <field>D_U_N_S__c</field>
        <formula>DNBoptimizer__DNB_D_U_N_S_Number__c</formula>
        <name>Update DUNS Number Custom</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Update_ERP_SID</fullName>
        <description>This WFR copies the Supper ID value into the ERP SID value if Account is Author or Agent/Licensor.</description>
        <field>ERP_SID__c</field>
        <formula>Supplier_ID__c</formula>
        <name>Update ERP SID</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
    <fieldUpdates>
        <fullName>Update_Tax_Type_to_Federal</fullName>
        <description>Update Tax Type value to Federal</description>
        <field>Tax_Type__c</field>
        <literalValue>Federal</literalValue>
        <name>Update Tax Type to Federal</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Literal</operation>
        <protected>false</protected>
    </fieldUpdates>
    <rules>
        <fullName>Auto Case - Created for Do Not Sell removal</fullName>
        <actions>
            <name>Email_alert_Created_for_Do_Not_Sell_removal</name>
            <type>Alert</type>
        </actions>
        <active>false</active>
        <criteriaItems>
            <field>Account.Do_Not_Sell_Reason__c</field>
            <operation>equals</operation>
            <value>Credit Hold</value>
        </criteriaItems>
        <criteriaItems>
            <field>Account.Credit_Check_Status__c</field>
            <operation>notEqual</operation>
            <value>CREDIT HOLD - DO NOT SELL</value>
        </criteriaItems>
        <criteriaItems>
            <field>User.OverrideWorkflow__c</field>
            <operation>equals</operation>
            <value>false</value>
        </criteriaItems>
        <description>when Do Not Sell on the Account is Credit hold and the Credit Check status is changed to not equal credit hold. 
case # 01472277 . #anchorage</description>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
    </rules>
    <rules>
        <fullName>Cleaning Is Hold button used field</fullName>
        <actions>
            <name>Cleaning_Is_Hold_field</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <description>https://linkedin.my.salesforce.com/5003200000rTqEi</description>
        <formula>AND( NOT(  OR(  ISNULL(PRIORVALUE(LTS_Account_Hold__c)),  ISBLANK(PRIORVALUE(LTS_Account_Hold__c))  )  ), OR(  ISNULL(LTS_Account_Hold__c),  ISBLANK(LTS_Account_Hold__c)  ), $User.OverrideWorkflow__c = &quot;False&quot; )</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>Create Case when LDC Hold Over Expiration %3C Today</fullName>
        <active>true</active>
        <description>Create Case when LDC Hold Over Expiration &lt; Today</description>
        <formula>AND (OR( $RecordType.Id=$Label.RT_AccountVerified, $RecordType.Id=$Label.RT_Account_Inactive_15),NOT(ISBLANK(LDC_Hold_Over_Expiration__c)))</formula>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
        <workflowTimeTriggers>
            <actions>
                <name>Create_Case_when_LDC_Hold_Over_Expiration_Today</name>
                <type>Alert</type>
            </actions>
            <offsetFromField>Account.LDC_Hold_Over_Expiration__c</offsetFromField>
            <timeLength>1</timeLength>
            <workflowTimeTriggerUnit>Days</workflowTimeTriggerUnit>
        </workflowTimeTriggers>
    </rules>
    <rules>
        <fullName>Create Case when LSS Hold Over Expiration %3C Today</fullName>
        <active>true</active>
        <description>Create Case when LSS Hold Over Expiration &lt; Today</description>
        <formula>AND  (  OR(  $RecordType.Id=$Label.RT_AccountVerified,  $RecordType.Id=$Label.RT_Account_Inactive_15  ),  NOT(ISBLANK(LSS_Hold_Over_Expiration__c ))  )</formula>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
        <workflowTimeTriggers>
            <actions>
                <name>Create_Case_when_LSS_Hold_Over_Expiration_Today</name>
                <type>Alert</type>
            </actions>
            <offsetFromField>Account.LSS_Hold_Over_Expiration__c</offsetFromField>
            <timeLength>1</timeLength>
            <workflowTimeTriggerUnit>Days</workflowTimeTriggerUnit>
        </workflowTimeTriggers>
    </rules>
    <rules>
        <fullName>Create Case when LTS Hold Over Expiration %3C Today</fullName>
        <active>true</active>
        <description>Create Case when LTS Hold Over Expiration &lt; Today.
AE-RM Mapping Project:- Adding one more condition to bypass workflow rule for create the case.</description>
        <formula>AND  (  OR(  $RecordType.Id=$Label.RT_AccountVerified,  $RecordType.Id=$Label.RT_Account_Inactive_15  ),  NOT(ISBLANK(Hold_Over_Expiration__c)),NOT(Exclude_Amplify__c )  )</formula>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
        <workflowTimeTriggers>
            <actions>
                <name>Create_Case_when_LTS_Hold_Over_Expiration_Today</name>
                <type>Alert</type>
            </actions>
            <offsetFromField>Account.Hold_Over_Expiration__c</offsetFromField>
            <timeLength>1</timeLength>
            <workflowTimeTriggerUnit>Days</workflowTimeTriggerUnit>
        </workflowTimeTriggers>
    </rules>
    <rules>
        <fullName>CreditCard-AuthorizationCleared-Recurring Charges</fullName>
        <actions>
            <name>CreditCard_RecurringChargesNotValid</name>
            <type>Alert</type>
        </actions>
        <active>true</active>
        <formula>AND(
ISPICKVAL(Credit_Card_Status__c ,&quot;&quot;),
ISPICKVAL(PRIORVALUE(Credit_Card_Status__c ),&quot;Recurring Charges&quot;),
$User.OverrideWorkflow__c = &quot;False&quot; 
)</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>CreditCheck - ApplicationRequired</fullName>
        <actions>
            <name>CreditCheck_Application_Rquired</name>
            <type>Alert</type>
        </actions>
        <actions>
            <name>Credit_Check_Application_Required</name>
            <type>Task</type>
        </actions>
        <active>false</active>
        <criteriaItems>
            <field>Account.Credit_Check_Status__c</field>
            <operation>equals</operation>
            <value>Credit Application Required</value>
        </criteriaItems>
        <criteriaItems>
            <field>User.OverrideWorkflow__c</field>
            <operation>equals</operation>
            <value>False</value>
        </criteriaItems>
        <description>A Credit Application is required for this account. deprecated per #anchorage</description>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
    </rules>
    <rules>
        <fullName>CreditCheck - Approved</fullName>
        <actions>
            <name>CreditCheck_Approved</name>
            <type>Alert</type>
        </actions>
        <actions>
            <name>Credit_Check_Approved</name>
            <type>Task</type>
        </actions>
        <active>false</active>
        <criteriaItems>
            <field>Account.Credit_Check_Status__c</field>
            <operation>equals</operation>
            <value>Approve</value>
        </criteriaItems>
        <criteriaItems>
            <field>User.OverrideWorkflow__c</field>
            <operation>equals</operation>
            <value>False</value>
        </criteriaItems>
        <description>This account is approved for the credit check. deprecated per #anchorage</description>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
    </rules>
    <rules>
        <fullName>CreditCheck - Prepay Only</fullName>
        <actions>
            <name>CreditCheck_NOTApproved</name>
            <type>Alert</type>
        </actions>
        <actions>
            <name>Credit_Check_NOT_Approved</name>
            <type>Task</type>
        </actions>
        <active>false</active>
        <criteriaItems>
            <field>Account.Credit_Check_Status__c</field>
            <operation>equals</operation>
            <value>Prepay Only</value>
        </criteriaItems>
        <criteriaItems>
            <field>User.OverrideWorkflow__c</field>
            <operation>equals</operation>
            <value>False</value>
        </criteriaItems>
        <description>This account is not approved for the credit check, Only Prepay is allowed. deprecated per #anchorage</description>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
    </rules>
    <rules>
        <fullName>D%26B Hoovers Field Updates</fullName>
        <actions>
            <name>Update_DUNS_Number_Custom</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <description>This performs the field updates as per the D&amp;B Hoovers changes.</description>
        <formula>AND( NOT(ISBLANK(DNBoptimizer__DNB_D_U_N_S_Number__c)), ISCHANGED( DNBoptimizer__DNB_D_U_N_S_Number__c ), ISBLANK(D_U_N_S__c) )</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>Data%2Ecom - If Active%2FVerified %26 Blank then Update Billing %26 Shipping Info</fullName>
        <actions>
            <name>Data_com_Update_Bill_Country_If_Blank</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Update_Bill_State_If_Blank</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Update_Billing_City_If_Blank</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Update_Billing_St_If_Blank</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Update_Billing_Zip_If_Blank</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Update_Ship_Country_If_Blank</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Update_Ship_State_If_Blank</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Update_Shipping_City_If_Blank</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Update_Shipping_St_If_Blank</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Update_Shipping_Zip_If_Blank</name>
            <type>FieldUpdate</type>
        </actions>
        <active>false</active>
        <description>Data.com - If Active/Verified &amp; Blank then Update Billing &amp; Shipping Info</description>
        <formula>AND(  OR( Status__c = &apos;Active&apos;, Status__c = &apos;Prospect&apos;, (AND( Year(Verification_Date__c) &lt; Year(Today()), NOT(Status__c = &apos;Active&apos;))) ),  NOT(ispickval(CleanStatus,&quot;In Sync&quot;)), NOT(ispickval(CleanStatus,&quot;Reviewed&quot;)), $User.OverrideWorkflow__c = &quot;False&quot; )</formula>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
    </rules>
    <rules>
        <fullName>Data%2Ecom - If Active%2FVerified %26 Blank then Update Doing Business As%2C Website%2C and Phone</fullName>
        <actions>
            <name>Data_com_Update_DBA_if_Blank</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Update_Fax_Number_If_Blank</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Update_Phone_If_Blank</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Update_Website_If_Blank</name>
            <type>FieldUpdate</type>
        </actions>
        <active>false</active>
        <description>Data.com - If Active/Verified &amp; Blank then Update Doing Business As, Website, and Phone</description>
        <formula>AND(  OR( Status__c = &apos;Active&apos;, Status__c = &apos;Prospect&apos;, (AND( Year(Verification_Date__c) &lt; Year(Today()), NOT(Status__c = &apos;Active&apos;))) ),  NOT(ispickval(CleanStatus,&quot;In Sync&quot;)), NOT(ispickval(CleanStatus,&quot;Reviewed&quot;)), $User.OverrideWorkflow__c = &quot;False&quot; )</formula>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
    </rules>
    <rules>
        <fullName>Data%2Ecom - If Created by Data%2Ecom Then Update All Hoovers Information</fullName>
        <actions>
            <name>Data_com_Override_All_Employees</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Override_Employees_This_Site</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Override_Hoovers_Account_Name</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Override_ImmediateParent_DUNS</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Override_ImmediateParent_Name</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Override_NAICS_Code</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Override_SIC_Code</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Override_UltimateParent_DUNS</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Override_UltimateParent_Name</name>
            <type>FieldUpdate</type>
        </actions>
        <active>false</active>
        <description>Data.com - If Created by Data.com Then Update All Hoovers Information</description>
        <formula>AND(
(ISPICKVAL(CleanStatus,&quot;In Sync&quot;)),
$User.OverrideWorkflow__c = &quot;False&quot;
)</formula>
        <triggerType>onCreateOnly</triggerType>
    </rules>
    <rules>
        <fullName>Data%2Ecom - If Created by Data%2Ecom Then Update All Hoovers Information %28cont%2E%29</fullName>
        <actions>
            <name>Data_com_Override_Annual_Revenue</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Override_DUNS</name>
            <type>FieldUpdate</type>
        </actions>
        <active>false</active>
        <description>Data.com - If Created by Data.com Then Update All Hoovers Information (cont.)</description>
        <formula>AND(
(ISPICKVAL(CleanStatus,&quot;In Sync&quot;)),
$User.OverrideWorkflow__c = &quot;False&quot;
)</formula>
        <triggerType>onCreateOnly</triggerType>
    </rules>
    <rules>
        <fullName>Data%2Ecom - If Created by Data%2Ecom Then Update All Shipping%2FBilling Information</fullName>
        <actions>
            <name>Data_com_Update_Bill_Country_If_Blank</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Update_Bill_State_If_Blank</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Update_Billing_City_If_Blank</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Update_Billing_St_If_Blank</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Update_Billing_Zip_If_Blank</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Update_Ship_Country_If_Blank</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Update_Ship_State_If_Blank</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Update_Shipping_City_If_Blank</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Update_Shipping_St_If_Blank</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Update_Shipping_Zip_If_Blank</name>
            <type>FieldUpdate</type>
        </actions>
        <active>false</active>
        <description>Data.com - If Created by Data.com Then Update All Shipping/Billing Information</description>
        <formula>AND(
(ISPICKVAL(CleanStatus,&quot;In Sync&quot;)),
$User.OverrideWorkflow__c = &quot;False&quot;
)</formula>
        <triggerType>onCreateOnly</triggerType>
    </rules>
    <rules>
        <fullName>Data%2Ecom - If Created by Data%2Ecom Then Update Tradestyle</fullName>
        <actions>
            <name>Data_com_Update_DBA_if_Blank</name>
            <type>FieldUpdate</type>
        </actions>
        <active>false</active>
        <description>Data.com - If Created by Data.com Then Update Tradestyle</description>
        <formula>AND(
(ISPICKVAL(CleanStatus,&quot;In Sync&quot;)),
$User.OverrideWorkflow__c = &quot;False&quot;
)</formula>
        <triggerType>onCreateOnly</triggerType>
    </rules>
    <rules>
        <fullName>Data%2Ecom - If Unverified %2F Not Strategic%2C Update%2FOverride Billing%2FShipping</fullName>
        <actions>
            <name>Data_com_Override_BillingStateProvince</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Override_Billing_City</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Override_Billing_Country</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Override_Billing_Street</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Override_Billing_ZipCode</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Override_Ship_StateProvince</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Override_Shipping_City</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Override_Shipping_Country</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Override_Shipping_Street</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Override_Shipping_ZipCode</name>
            <type>FieldUpdate</type>
        </actions>
        <active>false</active>
        <description>Data.com - If Unverified / Not Strategic, Update/Override Billing/Shipping</description>
        <formula>AND( 	Status__c = &apos;Unverified&apos;, 	NOT(ISPICKVAL(Tier__c,&quot;Strategic&quot;)), 	NOT(ISPICKVAL(CleanStatus,&quot;In Sync&quot;)), 	NOT(ISPICKVAL(CleanStatus,&quot;Reviewed&quot;)), 	NOT(ISPICKVAL(AccountSource,&quot;Rosetta-Drip&quot;)), 	NOT(ISPICKVAL(AccountSource,&quot;Rosetta-Drip-InferredCountry&quot;)), 	$User.OverrideWorkflow__c = &quot;False&quot;, 	$User.Id &lt;&gt; $Label.Boomi, 	$Profile.Id &lt;&gt; $Label.Profile_GSO_Data_Quality )</formula>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
    </rules>
    <rules>
        <fullName>Data%2Ecom - If Unverified %2F Not Strategic%2C Update%2FOverride DBA%2C Website%2C %26 Phone</fullName>
        <actions>
            <name>Data_com_Override_Doing_Business_As</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Override_Fax_Number</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Override_Phone_Number</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Override_Website</name>
            <type>FieldUpdate</type>
        </actions>
        <active>false</active>
        <description>Data.com - If Unverified / Not Strategic, Update/Override DBA, Website, &amp; Phone</description>
        <formula>AND(    Status__c = &apos;Unverified&apos;,    NOT(ISPICKVAL(Tier__c,&quot;Strategic&quot;)),    NOT(ISPICKVAL(CleanStatus,&quot;In Sync&quot;)),    NOT(ISPICKVAL(CleanStatus,&quot;Reviewed&quot;)),    NOT(ISPICKVAL(AccountSource,&quot;Rosetta-Drip&quot;)),    NOT(ISPICKVAL(AccountSource,&quot;Rosetta-Drip-InferredCountry&quot;)),    $User.OverrideWorkflow__c = &quot;False&quot;,    $Profile.Id &lt;&gt; &apos;00e60000000rLMNAA2&apos; )</formula>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
    </rules>
    <rules>
        <fullName>Data%2Ecom - Override All Hoovers Information</fullName>
        <actions>
            <name>Data_com_Override_All_Employees</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Override_Employees_This_Site</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Override_Hoovers_Account_Name</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Override_ImmediateParent_DUNS</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Override_ImmediateParent_Name</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Override_NAICS_Code</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Override_SIC_Code</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Override_UltimateParent_DUNS</name>
            <type>FieldUpdate</type>
        </actions>
        <actions>
            <name>Data_com_Override_UltimateParent_Name</name>
            <type>FieldUpdate</type>
        </actions>
        <active>false</active>
        <description>Data.com - Override All Hoovers Information</description>
        <formula>AND( NOT(ISPICKVAL(CleanStatus,&quot;In Sync&quot;)), NOT(ISPICKVAL(CleanStatus,&quot;Reviewed&quot;)), $User.OverrideWorkflow__c = &quot;False&quot; )</formula>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
    </rules>
    <rules>
        <fullName>Data%2Ecom - Override All Hoovers Information %28cont%2E%29</fullName>
        <actions>
            <name>Data_com_Override_Annual_Revenue</name>
            <type>FieldUpdate</type>
        </actions>
        <active>false</active>
        <description>Data.com - Override All Hoovers Information (cont.)</description>
        <formula>AND( NOT(ISPICKVAL(CleanStatus,&quot;In Sync&quot;)), NOT(ISPICKVAL(CleanStatus,&quot;Reviewed&quot;)), $User.OverrideWorkflow__c = &quot;False&quot; )</formula>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
    </rules>
    <rules>
        <fullName>Default Agency Payment Terms to Net 60</fullName>
        <actions>
            <name>Set_Payment_Terms_to_60</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <description>If an account is flagged as an agency and has payment terms less than Net 60, update payment terms to Net 60. #anchorage</description>
        <formula>AND( 	$User.OverrideWorkflow__c = &quot;False&quot;, 	CONTAINS($Label.PreapprovedPaymentTerms, TEXT(Payment_Terms__c)), 	OR( 		INCLUDES(Relationship_Type__c, &quot;Agency - MS&quot;), 		INCLUDES(Relationship_Type__c, &quot;Agency - TS&quot;) 	), 	ISCHANGED(Relationship_Type__c) )</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>Do Not Sell%3A Blank</fullName>
        <actions>
            <name>Set_Do_Not_Sell_to_blank</name>
            <type>FieldUpdate</type>
        </actions>
        <active>false</active>
        <description>If the Credit Check Status is not equal to &quot;CREDIT HOLD - DO NOT SELL,&quot; automatically update the Do Not Sell field to &quot;Blank&quot;

deprecated per #anchorage</description>
        <formula>AND(  NOT(ISPICKVAL( Credit_Check_Status__c, &quot;CREDIT HOLD - DO NOT SELL&quot;)), ISPICKVAL( Do_Not_Sell_Reason__c, &quot;Credit Hold&quot;), $User.OverrideWorkflow__c = &apos;False&apos;  )</formula>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
    </rules>
    <rules>
        <fullName>Do Not Sell%3A Credit Hold</fullName>
        <actions>
            <name>Populate_Do_Not_Sell_Credit_Hold</name>
            <type>FieldUpdate</type>
        </actions>
        <active>false</active>
        <description>If the Credit Check Status is updated to &quot;CREDIT HOLD - DO NOT SELL,&quot; automatically update the Do Not Sell field to &quot;Credit Hold.&quot;

deprecated per #anchorage</description>
        <formula>AND( ISPICKVAL( Credit_Check_Status__c, &quot;CREDIT HOLD - DO NOT SELL&quot;), ISBLANK( TEXT(Do_Not_Sell_Reason__c)), $User.OverrideWorkflow__c = &apos;False&apos;  )</formula>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
    </rules>
    <rules>
        <fullName>Employee Range - 1-10</fullName>
        <actions>
            <name>Set_Employee_Range_1_10</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <description>Update Employee Range - 1-10</description>
        <formula>not(ispickval( Employee_Range__c ,&apos;1-10&apos;)) &amp;&amp; 
NumberOfEmployees &gt;=-9 &amp;&amp; 
NumberOfEmployees &lt;=10 &amp;&amp; 
ischanged(NumberOfEmployees )</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>Employee Range - 10001%2B</fullName>
        <actions>
            <name>Set_Employee_Range_10001</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <description>Update Employee Range - 10001+</description>
        <formula>not(ispickval( Employee_Range__c ,&apos;10001+&apos;))&amp;&amp; 
NumberOfEmployees &gt;=10001 &amp;&amp; 
ischanged(NumberOfEmployees )</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>Employee Range - 1001-5000</fullName>
        <actions>
            <name>Set_Employee_Range_1001_5000</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <description>UpdateEmployee Range - 1001-5000</description>
        <formula>not(ispickval( Employee_Range__c ,&apos;1001-5000&apos;)) &amp;&amp; 
NumberOfEmployees &gt;=1001 &amp;&amp; 
NumberOfEmployees &lt;=5000 &amp;&amp; 
ischanged(NumberOfEmployees )</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>Employee Range - 11-50</fullName>
        <actions>
            <name>Set_Employee_Range_11_50</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <description>Update Employee Range - 11-50</description>
        <formula>not(ispickval( Employee_Range__c ,&apos;11-50&apos;)) &amp;&amp; 
NumberOfEmployees &gt;=11 &amp;&amp; 
NumberOfEmployees &lt;=50 &amp;&amp; 
ischanged(NumberOfEmployees )</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>Employee Range - 201-500</fullName>
        <actions>
            <name>Set_Employee_Range_201_500</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <description>Update Employee Range - 201-500</description>
        <formula>not(ispickval( Employee_Range__c ,&apos;201-500&apos;)) &amp;&amp; 
NumberOfEmployees &gt;=201 &amp;&amp; 
NumberOfEmployees &lt;=500 &amp;&amp; 
ischanged(NumberOfEmployees )</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>Employee Range - 5001-10000</fullName>
        <actions>
            <name>Set_Employee_Range_5001_10000</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <description>Update Employee Range - 5001-10000</description>
        <formula>not(ispickval( Employee_Range__c ,&apos;5001-10000&apos;)) &amp;&amp; 
NumberOfEmployees &gt;=5001 &amp;&amp; 
NumberOfEmployees &lt;=10000 &amp;&amp; 
ischanged(NumberOfEmployees )</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>Employee Range - 501-1000</fullName>
        <actions>
            <name>Set_Employee_Range_501_1000</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <description>Update Employee Range - 501-1000</description>
        <formula>not(ispickval( Employee_Range__c ,&apos;501-1000&apos;)) &amp;&amp; 
NumberOfEmployees &gt;=501 &amp;&amp; 
NumberOfEmployees &lt;=1000 &amp;&amp; 
ischanged(NumberOfEmployees )</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>Employee Range - 51-200</fullName>
        <actions>
            <name>Set_Employee_Range_51_200</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <description>Update Employee Range - 51-200</description>
        <formula>not(ispickval( Employee_Range__c ,&apos;51-200&apos;)) &amp;&amp; 
NumberOfEmployees &gt;=51 &amp;&amp; 
NumberOfEmployees &lt;=200 &amp;&amp; 
ischanged(NumberOfEmployees )</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>Employee Range - Update Employees based on Range</fullName>
        <actions>
            <name>Set_Employees_based_on_Employee_Range</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <description>If Employee Range has Changed, but Number of Employees hasn&apos;t, then update Employees to the lowest number of employees in the Range.</description>
        <formula>$User.OverrideWorkflow__c =&quot;False&quot; &amp;&amp; 
( 
ISNEW() 
&amp;&amp; NOT(ISPICKVAL(Employee_Range__c,&quot;&quot;)) 
&amp;&amp; ISBLANK(NumberOfEmployees) 
) 

|| ( 
ischanged( Employee_Range__c ) 
&amp;&amp; not(ischanged( NumberOfEmployees )) 
)</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>Geographic Territory - LATAM</fullName>
        <actions>
            <name>Set_Geographic_Territory_LATAM</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <booleanFilter>(1 or  2 or 3 or 4) or ( 5 and (6 or 7 or 8 or 9) ) and 10</booleanFilter>
        <criteriaItems>
            <field>Account.ShippingCountry</field>
            <operation>equals</operation>
            <value>Anguilla,Antarctica,Antigua and Barbuda,Argentina,Aruba,Bahamas,Barbados,Belize,Bolivia,Bouvet Island,Brazil,Brasil,Cayman Islands,Chile,Colombia,Costa Rica,Dominica,Dominican Republic,Ecuador,El Salvador</value>
        </criteriaItems>
        <criteriaItems>
            <field>Account.ShippingCountry</field>
            <operation>equals</operation>
            <value>Equatorial Guinea,Eritrea,Falkland Islands (Malvinas),French Guiana,French Southern Territories,Grenada,Guadeloupe,Guatemala,Guyana,Haiti,Heard Island and McDonald Islands,Honduras,Jamaica,Martinique,Mexico</value>
        </criteriaItems>
        <criteriaItems>
            <field>Account.ShippingCountry</field>
            <operation>equals</operation>
            <value>Montserrat,Nicaragua,Palestinian Territory,Panama,Paraguay,Peru,Puerto Rico,S. Georgia and S. Sandwich Islands,Saint Barthelemy,Saint Helena,Saint Kitts and Nevis,Saint Lucia,Saint Martin</value>
        </criteriaItems>
        <criteriaItems>
            <field>Account.ShippingCountry</field>
            <operation>equals</operation>
            <value>Saint Vincent and The Grenadines,Suriname,Turks and Caicos Islands,Uruguay,Venezuela,Virgin Islands (British),Virgin Islands (U.S.)</value>
        </criteriaItems>
        <criteriaItems>
            <field>Account.ShippingCountry</field>
            <operation>equals</operation>
        </criteriaItems>
        <criteriaItems>
            <field>Account.BillingCountry</field>
            <operation>equals</operation>
            <value>Anguilla,Antarctica,Antigua and Barbuda,Argentina,Aruba,Bahamas,Barbados,Belize,Bolivia,Bouvet Island,Brazil,Cayman Islands,Chile,Colombia,Costa Rica,Dominica,Dominican Republic,Ecuador,El Salvador</value>
        </criteriaItems>
        <criteriaItems>
            <field>Account.BillingCountry</field>
            <operation>equals</operation>
            <value>Equatorial Guinea,Eritrea,Falkland Islands (Malvinas),French Guiana,French Southern Territories,Grenada,Guadeloupe,Guatemala,Guyana,Haiti,Heard Island and McDonald Islands,Honduras,Jamaica,Martinique,Mexico</value>
        </criteriaItems>
        <criteriaItems>
            <field>Account.BillingCountry</field>
            <operation>equals</operation>
            <value>Montserrat,Nicaragua,Palestinian Territory,Panama,Paraguay,Peru,Puerto Rico,S. Georgia and S. Sandwich Islands,Saint Barthelemy,Saint Helena,Saint Kitts and Nevis,Saint Lucia,Saint Martin</value>
        </criteriaItems>
        <criteriaItems>
            <field>Account.BillingCountry</field>
            <operation>equals</operation>
            <value>Saint Vincent and The Grenadines,Suriname,Turks and Caicos Islands,Uruguay,Venezuela,Virgin Islands (British),Virgin Islands (U.S.)</value>
        </criteriaItems>
        <criteriaItems>
            <field>Account.BillingCountry</field>
            <operation>equals</operation>
            <value>False</value>
        </criteriaItems>
        <description>If the shipping country contains any of these countries, update the Major Continent field with North America and the minor continent field with North America</description>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
    </rules>
    <rules>
        <fullName>Geographic Territory -APAC</fullName>
        <actions>
            <name>Set_Geographic_Territory_APAC</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <booleanFilter>(1 or 2 or 3 or 4) or ( 5 and (6 or 7 or 8 or 9) ) and 10</booleanFilter>
        <criteriaItems>
            <field>Account.ShippingCountry</field>
            <operation>equals</operation>
            <value>Afghanistan,Australia,Bangladesh,Bhutan,Brunei Darussalam,Cambodia,China,Christmas Island,Cocos (Keeling) Islands,Cook Islands,Federated States of Micronesia,Fiji,French Polynesia,Guam,Hong Kong,India</value>
        </criteriaItems>
        <criteriaItems>
            <field>Account.ShippingCountry</field>
            <operation>equals</operation>
            <value>Indonesia,Iran,Japan,Kiribati,Korea,Laos,Macao,Malaysia,Maldives,Marshall Islands,Mongolia,Myanmar,Nauru,Nepal,New Caledonia,New Zealand,Niue,Norfolk Island,North Korea,Northern Mariana Islands</value>
        </criteriaItems>
        <criteriaItems>
            <field>Account.ShippingCountry</field>
            <operation>equals</operation>
            <value>Mongolia,Myanmar,Nauru,Nepal,New Caledonia,New Zealand,Niue,Norfolk Island,North Korea,Northern Mariana Islands,Pakistan,Palau,Papua New Guinea,Philippines,Pitcairn,Samoa,Singapore,Solomon Islands</value>
        </criteriaItems>
        <criteriaItems>
            <field>Account.ShippingCountry</field>
            <operation>equals</operation>
            <value>Sri Lanka,Taiwan,Tajikistan,Thailand,Timor-leste,Tokelau,Tonga,Turkmenistan,Tuvalu,United States Minor Outlying Islands,Uzbekistan,Vanuatu,Vietnam,Wallis and Futuna</value>
        </criteriaItems>
        <criteriaItems>
            <field>Account.ShippingCountry</field>
            <operation>equals</operation>
        </criteriaItems>
        <criteriaItems>
            <field>Account.BillingCountry</field>
            <operation>equals</operation>
            <value>Afghanistan,Australia,Bangladesh,Bhutan,Brunei Darussalam,Cambodia,China,Christmas Island,Cocos (Keeling) Islands,Cook Islands,Federated States of Micronesia,Fiji,French Polynesia,Guam,Hong Kong,India</value>
        </criteriaItems>
        <criteriaItems>
            <field>Account.BillingCountry</field>
            <operation>equals</operation>
            <value>Indonesia,Iran,Japan,Kiribati,Korea,Laos,Macao,Malaysia,Maldives,Marshall Islands,Mongolia,Myanmar,Nauru,Nepal,New Caledonia,New Zealand,Niue,Norfolk Island,North Korea,Northern Mariana Islands</value>
        </criteriaItems>
        <criteriaItems>
            <field>Account.BillingCountry</field>
            <operation>equals</operation>
            <value>Mongolia,Myanmar,Nauru,Nepal,New Caledonia,New Zealand,Niue,Norfolk Island,North Korea,Northern Mariana Islands,Pakistan,Palau,Papua New Guinea,Philippines,Pitcairn,Samoa,Singapore,Solomon Islands</value>
        </criteriaItems>
        <criteriaItems>
            <field>Account.BillingCountry</field>
            <operation>equals</operation>
            <value>Sri Lanka,Taiwan,Tajikistan,Thailand,Timor-leste,Tokelau,Tonga,Turkmenistan,Tuvalu,United States Minor Outlying Islands,Uzbekistan,Vanuatu,Vietnam,Wallis and Futuna</value>
        </criteriaItems>
        <criteriaItems>
            <field>User.OverrideWorkflow__c</field>
            <operation>equals</operation>
            <value>False</value>
        </criteriaItems>
        <description>Set Territory to APAC</description>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
    </rules>
    <rules>
        <fullName>Geographic Territory -EMEA</fullName>
        <actions>
            <name>Set_Geographic_Territory_EMEA</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <booleanFilter>(1 or  2 or 3 or 4 or 5 or 6 or 7) or ( 8 and (9 or 10 or 11 or 12 or 13 or 14 or 15)) and 16</booleanFilter>
        <criteriaItems>
            <field>Account.ShippingCountry</field>
            <operation>equals</operation>
            <value>Aland Islands,Albania,Algeria,Andorra,Angola,Armenia,Austria,Azerbaijan,Bahrain,Belarus,Belgium,Benin,Bosnia and Herzegovina,Botswana,British Indian Ocean Territory,Bulgaria,Burkina Faso,Burundi</value>
        </criteriaItems>
        <criteriaItems>
            <field>Account.ShippingCountry</field>
            <operation>equals</operation>
            <value>Cameroon,Cape Verde,Central African Republic,Chad,Comoros,Congo,Cote D&apos;Ivoire (Ivory Coast),Croatia,Cyprus,Czech Republic,Democratic Republic of the Congo,Denmark,Djibouti,Egypt,Estonia,Ethiopia</value>
        </criteriaItems>
        <criteriaItems>
            <field>Account.ShippingCountry</field>
            <operation>equals</operation>
            <value>Finland,France,Gabon,Gambia,Georgia,Germany,Ghana,Gibraltar,Greece,Greenland,Guernsey,Guinea,Guinea-Bissau,Hungary,Iceland,Iraq,Ireland,Isle Of Man,Israel,Italy,Jersey,Jordan,Kazakhstan</value>
        </criteriaItems>
        <criteriaItems>
            <field>Account.ShippingCountry</field>
            <operation>equals</operation>
            <value>Kenya,Kuwait,Kyrgyzstan,Latvia,Lebanon,Lesotho,Liberia,Libya,Liechtenstein,Lithuania,Luxembourg,Macedonia,Madagascar,Malawi,Mali,Malta,Mauritania,Mauritius,Mayotte,Moldova,Monaco,Montenegro</value>
        </criteriaItems>
        <criteriaItems>
            <field>Account.ShippingCountry</field>
            <operation>equals</operation>
            <value>Morocco,Mozambique,Namibia,Netherlands Antilles,Netherlands,The Netherlands,Niger,Nigeria,Norway,Poland,Portugal,Qatar,Reunion,Romania,Russian Federation,Rwanda,Saint Pierre and Miquelon,San Marino</value>
        </criteriaItems>
        <criteriaItems>
            <field>Account.ShippingCountry</field>
            <operation>equals</operation>
            <value>Sao Tome and Principe,Saudi Arabia,Senegal,Serbia,Seychelles,Sierra Leone,Slovak Republic,Slovenia,Somalia,South Africa,Spain,Sudan,Sultanate of Oman,Svalbard and Jan Mayen,Swaziland,Sweden,Switzerland</value>
        </criteriaItems>
        <criteriaItems>
            <field>Account.ShippingCountry</field>
            <operation>equals</operation>
            <value>Syria,Tanzania,Togo,Tunisia,Turkey,Uganda,Ukraine,United Arab Emirates,United Kingdom,Vatican City State (Holy See),Western Sahara,Yemen,Zambia,Zimbabwe</value>
        </criteriaItems>
        <criteriaItems>
            <field>Account.ShippingCountry</field>
            <operation>equals</operation>
        </criteriaItems>
        <criteriaItems>
            <field>Account.BillingCountry</field>
            <operation>equals</operation>
            <value>Aland Islands,Albania,Algeria,Andorra,Angola,Armenia,Austria,Azerbaijan,Bahrain,Belarus,Belgium,Benin,Bosnia and Herzegovina,Botswana,British Indian Ocean Territory,Bulgaria,Burkina Faso,Burundi</value>
        </criteriaItems>
        <criteriaItems>
            <field>Account.BillingCountry</field>
            <operation>equals</operation>
            <value>Cameroon,Cape Verde,Central African Republic,Chad,Comoros,Congo,Cote D&apos;Ivoire (Ivory Coast),Croatia,Cyprus,Czech Republic,Democratic Republic of the Congo,Denmark,Djibouti,Egypt,Estonia,Ethiopia</value>
        </criteriaItems>
        <criteriaItems>
            <field>Account.BillingCountry</field>
            <operation>equals</operation>
            <value>Finland,France,Gabon,Gambia,Georgia,Germany,Ghana,Gibraltar,Greece,Greenland,Guernsey,Guinea,Guinea-Bissau,Hungary,Iceland,Iraq,Ireland,Isle Of Man,Israel,Italy,Jersey,Jordan,Kazakhstan</value>
        </criteriaItems>
        <criteriaItems>
            <field>Account.BillingCountry</field>
            <operation>equals</operation>
            <value>Kenya,Kuwait,Kyrgyzstan,Latvia,Lebanon,Lesotho,Liberia,Libya,Liechtenstein,Lithuania,Luxembourg,Macedonia,Madagascar,Malawi,Mali,Malta,Mauritania,Mauritius,Mayotte,Moldova,Monaco,Montenegro</value>
        </criteriaItems>
        <criteriaItems>
            <field>Account.BillingCountry</field>
            <operation>equals</operation>
            <value>Morocco,Mozambique,Namibia,Netherlands Antilles,Netherlands,Niger,Nigeria,Norway,Poland,Portugal,Qatar,Reunion,Romania,Russian Federation,Rwanda,Saint Pierre and Miquelon,San Marino</value>
        </criteriaItems>
        <criteriaItems>
            <field>Account.BillingCountry</field>
            <operation>equals</operation>
            <value>Sao Tome and Principe,Saudi Arabia,Senegal,Serbia,Seychelles,Sierra Leone,Slovak Republic,Slovenia,Somalia,South Africa,Spain,Sudan,Sultanate of Oman,Svalbard and Jan Mayen,Swaziland,Sweden,Switzerland</value>
        </criteriaItems>
        <criteriaItems>
            <field>Account.BillingCountry</field>
            <operation>equals</operation>
            <value>Syria,Tanzania,Togo,Tunisia,Turkey,Uganda,Ukraine,United Arab Emirates,United Kingdom,Vatican City State (Holy See),Western Sahara,Yemen,Zambia,Zimbabwe</value>
        </criteriaItems>
        <criteriaItems>
            <field>User.OverrideWorkflow__c</field>
            <operation>equals</operation>
            <value>False</value>
        </criteriaItems>
        <description>Set Territory to EMEA</description>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
    </rules>
    <rules>
        <fullName>Geographic Territory -US</fullName>
        <actions>
            <name>Set_Geographic_Territory_US</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <booleanFilter>(1 or (2 and 3)) and 4</booleanFilter>
        <criteriaItems>
            <field>Account.ShippingCountry</field>
            <operation>equals</operation>
            <value>Trinidad and Tobago,Canada,United States,American Samoa,Bermuda</value>
        </criteriaItems>
        <criteriaItems>
            <field>Account.ShippingCountry</field>
            <operation>equals</operation>
        </criteriaItems>
        <criteriaItems>
            <field>Account.BillingCountry</field>
            <operation>equals</operation>
            <value>Trinidad and Tobago,Canada,United States,American Samoa,Bermuda</value>
        </criteriaItems>
        <criteriaItems>
            <field>User.OverrideWorkflow__c</field>
            <operation>equals</operation>
            <value>FALSE</value>
        </criteriaItems>
        <description>If the shipping country contains any of these countries, update the Major Continent field with North America and the minor continent field with North America</description>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
    </rules>
    <rules>
        <fullName>LSS Account Hold Date</fullName>
        <active>true</active>
        <formula>not(isblank( LSS_Hold_Over_Expiration__c ))</formula>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
        <workflowTimeTriggers>
            <actions>
                <name>Account_Hold_Date</name>
                <type>Alert</type>
            </actions>
            <offsetFromField>Account.LSS_Hold_Over_Expiration__c</offsetFromField>
            <timeLength>1</timeLength>
            <workflowTimeTriggerUnit>Hours</workflowTimeTriggerUnit>
        </workflowTimeTriggers>
    </rules>
    <rules>
        <fullName>LTS Account Hold Date</fullName>
        <active>true</active>
        <formula>not(isblank(Hold_Over_Expiration__c ))</formula>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
        <workflowTimeTriggers>
            <actions>
                <name>Account_Hold_Date</name>
                <type>Alert</type>
            </actions>
            <offsetFromField>Account.Hold_Over_Expiration__c</offsetFromField>
            <timeLength>1</timeLength>
            <workflowTimeTriggerUnit>Hours</workflowTimeTriggerUnit>
        </workflowTimeTriggers>
    </rules>
    <rules>
        <fullName>Leo Verified field update to %27Non Existent%27</fullName>
        <actions>
            <name>Leo_Verified_field_update_to_Nonexistent</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <formula>AND(  $User.OverrideWorkflow__c =&quot;False&quot;,  ISBLANK( LI_Co_ID__c),  ISBLANK( Parent.LI_Co_ID__c ),  ISBLANK( Ultimate_Parent__r.LI_Co_ID__c )  )</formula>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
    </rules>
    <rules>
        <fullName>Leo Verified field update to %27Parent%27</fullName>
        <actions>
            <name>Leo_Verified_field_update_to_Parent</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <formula>AND (  ISBLANK( LI_Co_ID__c),  OR(  NOT( ISBLANK( Ultimate_Parent__r.LI_Co_ID__c )),  NOT( ISBLANK( Parent.LI_Co_ID__c ) )  ),  $User.OverrideWorkflow__c =&quot;False&quot;  )</formula>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
    </rules>
    <rules>
        <fullName>Leo Verified field update to %27Verified%27</fullName>
        <actions>
            <name>Leo_Verified_field_update_to_Verified</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <description>https://linkedin.my.salesforce.com/a1W32000002Fh2h</description>
        <formula>AND(
NOT( ISBLANK( LI_Co_ID__c) ),
$User.OverrideWorkflow__c =&quot;False&quot;
)</formula>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
    </rules>
    <rules>
        <fullName>Notify AR - Payment Terms Have Changed_AMER</fullName>
        <actions>
            <name>Notify_AR_Payment_Terms_Have_Changed_AMER</name>
            <type>Alert</type>
        </actions>
        <active>false</active>
        <description>Email-to-case to Accounts Receivable if the Payment Terms field is changed for an account based in US, LATAM or &quot;blank&quot; territory. When payment terms are changed by the Credit Team, AR needs to update Oracle. deprecated per #anchorage</description>
        <formula>AND  (   	ISCHANGED(Payment_Terms__c),   	OR  	( 		ISPICKVAL(Geographic_Territory__c, &quot;US&quot;),  		ISPICKVAL(Geographic_Territory__c, &quot;LATAM&quot;),  		ISPICKVAL(Geographic_Territory__c, &quot;&quot;)  	),   	NOT 	( 		OR  		( 			AND  			( 				ISPICKVAL(Payment_Terms__c, &quot;&quot;),  				ISPICKVAL(PRIORVALUE(Payment_Terms__c), &quot;30 Days&quot;)  			),  			AND  			( 				ISPICKVAL(Payment_Terms__c, &quot;30 Days&quot;),  				ISPICKVAL(PRIORVALUE(Payment_Terms__c), &quot;&quot;)  			) 		)   	)   )</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>Notify AR - Payment Terms Have Changed_APAC</fullName>
        <actions>
            <name>Notify_AR_Payment_Terms_Have_Changed_APAC</name>
            <type>Alert</type>
        </actions>
        <active>false</active>
        <description>Email-to-case to Accounts Receivable if the Payment Terms field is changed for an account based in APAC territory. When payment terms are changed by the Credit Team, AR needs to update Oracle. deprecated per #anchorage</description>
        <formula>AND  (   	ISCHANGED(Payment_Terms__c),  	NOT(ISPICKVAL(Geographic_Territory__c, &quot;US&quot;)),  	NOT(ISPICKVAL(Geographic_Territory__c, &quot;LATAM&quot;)),  	NOT(ISPICKVAL(Geographic_Territory__c, &quot;&quot;)),   	NOT 	( 		OR  		( 			AND  			( 				ISPICKVAL(Payment_Terms__c, &quot;&quot;),  				ISPICKVAL(PRIORVALUE(Payment_Terms__c), &quot;30 Days&quot;)  			),  			AND  			( 				ISPICKVAL(Payment_Terms__c, &quot;30 Days&quot;),  				ISPICKVAL(PRIORVALUE(Payment_Terms__c), &quot;&quot;)  			) 		)   	)   )</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>OFAC- Do Not Sell</fullName>
        <actions>
            <name>OFAC_Do_Not_Sell</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <booleanFilter>(1 OR 2) AND 3</booleanFilter>
        <criteriaItems>
            <field>Account.BillingCountry</field>
            <operation>equals</operation>
            <value>Iran,Syria,Sudan,North Korea,Cuba,IR,SY,SD,KP,CU</value>
        </criteriaItems>
        <criteriaItems>
            <field>Account.ShippingCountry</field>
            <operation>equals</operation>
            <value>Iran,Syria,Sudan,North Korea,Cuba,IR,SY,SD,KP,CU</value>
        </criteriaItems>
        <criteriaItems>
            <field>User.OverrideWorkflow__c</field>
            <operation>equals</operation>
            <value>False</value>
        </criteriaItems>
        <description>If an account has a billing address of  Iran,Syria,Sudan,North Korea,or Cuba account will be updated to do not sell.</description>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
    </rules>
    <rules>
        <fullName>Payment Terms defaults to 30 days</fullName>
        <actions>
            <name>Payment_Terms_30_days</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <criteriaItems>
            <field>Account.RecordTypeId</field>
            <operation>notEqual</operation>
        </criteriaItems>
        <criteriaItems>
            <field>Account.Payment_Terms__c</field>
            <operation>equals</operation>
        </criteriaItems>
        <description>Payment Terms defaults to 30 days when account is first created</description>
        <triggerType>onCreateOnly</triggerType>
    </rules>
    <rules>
        <fullName>Relationship Type Concatenator Update</fullName>
        <actions>
            <name>Relationship_Type_Concatenator_Update</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <description>This WFR updates the Account field, Relationship Type Concatenator, to a concatenated string that includes all values from the Relationship Type multipicklist field, whenever the Relationship Type field is updated. There CANNOT be an WF Override exception</description>
        <formula>OR( ISNEW() ,  ISCHANGED(Relationship_Type__c )  )</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>Set PO Threshold</fullName>
        <actions>
            <name>Set_PO_Threshold_to_Blank</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <description>Set PO Threshold to Blank when Po Required field is changed to NO</description>
        <formula>AND( $User.OverrideWorkflow__c = &quot;False&quot; , ISCHANGED(PO_Required__c ), ISPICKVAL(PO_Required__c , &apos;No&apos;), NOT(ISNULL(PO_Threshold__c)) )</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>Set SD Target LHS to Y</fullName>
        <actions>
            <name>Set_SD_Target_LHS_to_Y</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <description>Once an account changes from Prospect to Active, reset the &quot;SD Target (LHS)&quot; to &apos;Y&apos;</description>
        <formula>AND(
  ISCHANGED(  Latest_Contract_End_Date__c  ),
  ISNULL(PRIORVALUE( Latest_Contract_End_Date__c )) ,
  NOT(ISNULL(Latest_Contract_End_Date__c) ) 
)</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>Tag Account Drip</fullName>
        <actions>
            <name>Account_Source_to_Account_Drip</name>
            <type>FieldUpdate</type>
        </actions>
        <active>false</active>
        <criteriaItems>
            <field>Account.CreatedById</field>
            <operation>contains</operation>
            <value>boomi</value>
        </criteriaItems>
        <criteriaItems>
            <field>Account.gso_LEO_Verified__c</field>
            <operation>equals</operation>
            <value>Verified</value>
        </criteriaItems>
        <description>Update Account Source = Account Drip</description>
        <triggerType>onCreateOnly</triggerType>
    </rules>
    <rules>
        <fullName>Task creation when RelationShip Type is changed</fullName>
        <actions>
            <name>Please_note_the_reason_for_why_the_account_was_marked_as_a_competitor</name>
            <type>Task</type>
        </actions>
        <active>false</active>
        <criteriaItems>
            <field>Account.Relationship_Type__c</field>
            <operation>includes</operation>
            <value>Competitor - MS,Competitor - LSS,Competitor - HS,Competitor - LDC</value>
        </criteriaItems>
        <criteriaItems>
            <field>User.OverrideWorkflow__c</field>
            <operation>equals</operation>
            <value>False</value>
        </criteriaItems>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
    </rules>
    <rules>
        <fullName>Update Account Main Fields Last Modified Date</fullName>
        <actions>
            <name>Update_Account_Main_Fields_Last_Modified</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <description>Update &quot;Account Main Fields Last Modified Date&quot; field when account name, url, billing address, or shipping address changed</description>
        <formula>and(
or(
ischanged( Name ), ischanged( Website ), ischanged( BillingCity ), ischanged( BillingCountry ), ischanged( BillingState ), ischanged( BillingStreet ), ischanged( BillingPostalCode ), ischanged(ShippingCity), ischanged(ShippingCountry), ischanged(ShippingState), ischanged(ShippingStreet), ischanged(ShippingPostalCode)
),
$User.Id &lt;&gt; $Label.Boomi,
$User.OverrideWorkflow__c =&quot;False&quot;
)</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>Update Account Owner - Verified</fullName>
        <actions>
            <name>Owner_to_LinkedIn_Solutions</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <criteriaItems>
            <field>Account.RecordTypeId</field>
            <operation>equals</operation>
            <value>Verified</value>
        </criteriaItems>
        <criteriaItems>
            <field>Account.OwnerId</field>
            <operation>notEqual</operation>
            <value>LinkedIn Solutions</value>
        </criteriaItems>
        <criteriaItems>
            <field>User.OverrideWorkflow__c</field>
            <operation>equals</operation>
            <value>False</value>
        </criteriaItems>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
    </rules>
    <rules>
        <fullName>Update Highest Accuracy Score Account</fullName>
        <actions>
            <name>Update_Account_Highest_Accuracy_Score</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <description>Populate highest accuracy score if current accuracy score is higher.</description>
        <formula>and (
 or(
   LI_Accuracy_Score__c &gt; LI_Highest_Accuracy_Score__c,
   and(
     isblank(LI_Highest_Accuracy_Score__c),LI_Accuracy_Score__c&gt;0)
   ),
 or(
   DWH_Extract_Date__c&gt;Account_Main_Fields_Last_Modified_Date__c,
   and(
     isblank(Account_Main_Fields_Last_Modified_Date__c),
     not(isblank(DWH_Extract_Date__c)))),
 $User.OverrideWorkflow__c =&quot;False&quot;  )</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>Update Insights when Ultimate Parent changes and is CEG</fullName>
        <actions>
            <name>Use_sObjectDML_Email_Service_to_update_Insight_s_CEG_field_to_true</name>
            <type>Alert</type>
        </actions>
        <active>true</active>
        <formula>AND(
ISCHANGED(Ultimate_Parent__c), Ultimate_Parent__r.CEG_Account__c = TRUE,
$User.OverrideWorkflow__c =&quot;False&quot;
)</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>Update Insights when Ultimate Parent changes and is NOT CEG</fullName>
        <actions>
            <name>Use_sObjectDML_Email_Service_to_update_Insight_s_CEG_field_to_false</name>
            <type>Alert</type>
        </actions>
        <active>true</active>
        <formula>AND( ISCHANGED(Ultimate_Parent__c), Ultimate_Parent__r.CEG_Account__c = FALSE,
$User.OverrideWorkflow__c =&quot;False&quot;)</formula>
        <triggerType>onAllChanges</triggerType>
    </rules>
    <rules>
        <fullName>Update Record Type to Inactive</fullName>
        <actions>
            <name>Account_RType_to_Inactive</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <criteriaItems>
            <field>Account.RecordTypeId</field>
            <operation>notEqual</operation>
            <value>Inactive</value>
        </criteriaItems>
        <criteriaItems>
            <field>Account.Do_Not_Sell_Reason__c</field>
            <operation>notEqual</operation>
        </criteriaItems>
        <criteriaItems>
            <field>User.OverrideWorkflow__c</field>
            <operation>equals</operation>
            <value>False</value>
        </criteriaItems>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
    </rules>
    <rules>
        <fullName>Update Tax Type on Relationship Type Update</fullName>
        <actions>
            <name>Update_Tax_Type_to_Federal</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <criteriaItems>
            <field>User.OverrideWorkflow__c</field>
            <operation>equals</operation>
            <value>False</value>
        </criteriaItems>
        <criteriaItems>
            <field>Account.Relationship_Type__c</field>
            <operation>includes</operation>
            <value>Federal</value>
        </criteriaItems>
        <description>When relationship type updates to include Federal, update Tax Type value to Federal as well</description>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
    </rules>
    <rules>
        <fullName>Update Tier Field</fullName>
        <actions>
            <name>Tier_Field_Update</name>
            <type>FieldUpdate</type>
        </actions>
        <active>true</active>
        <description>If the ultimate Parent&apos;s Strategic field is tier, then update the child accounts Tier field to Strategic
https://linkedin.my.salesforce.com/a1W32000002FWIM</description>
        <formula>AND(ISPICKVAL(Ultimate_Parent__r.Tier__c, &apos;Strategic&apos;),
  $User.OverrideWorkflow__c =&apos;false&apos;)</formula>
        <triggerType>onCreateOrTriggeringUpdate</triggerType>
    </rules>
    <tasks>
        <fullName>Credit_Check_Application_Required</fullName>
        <assignedToType>owner</assignedToType>
        <description>Notification to AE that a Credit Application is Required.</description>
        <dueDateOffset>0</dueDateOffset>
        <notifyAssignee>false</notifyAssignee>
        <priority>Normal</priority>
        <protected>false</protected>
        <status>Completed</status>
        <subject>Credit Check Application Required.</subject>
    </tasks>
    <tasks>
        <fullName>Credit_Check_Approved</fullName>
        <assignedToType>owner</assignedToType>
        <description>Notification that the Credit Check was approved.</description>
        <dueDateOffset>0</dueDateOffset>
        <notifyAssignee>false</notifyAssignee>
        <priority>Normal</priority>
        <protected>false</protected>
        <status>Completed</status>
        <subject>Credit Check Approved</subject>
    </tasks>
    <tasks>
        <fullName>Credit_Check_NOT_Approved</fullName>
        <assignedToType>owner</assignedToType>
        <description>Notification that the Credit Check was not approved.  This customer is only allowed to buy if Prepay is selected in Payment Period.</description>
        <dueDateOffset>0</dueDateOffset>
        <notifyAssignee>false</notifyAssignee>
        <priority>Normal</priority>
        <protected>false</protected>
        <status>Completed</status>
        <subject>Credit Check NOT Approved</subject>
    </tasks>
    <tasks>
        <fullName>Hold_Over_Date_Has_Expired</fullName>
        <assignedTo>eadisa@linkedin.com</assignedTo>
        <assignedToType>user</assignedToType>
        <description>This Account Hold Over has expired.1.  Reassign Account to appropriate person in team.  (see activity history if unclear who to assign it to).2.  Clear out the Hold Over Expiration Date.3.  Notify both parties.</description>
        <dueDateOffset>0</dueDateOffset>
        <notifyAssignee>false</notifyAssignee>
        <priority>High</priority>
        <protected>false</protected>
        <status>Not Started</status>
        <subject>Hold Over Date Has Expired</subject>
    </tasks>
    <tasks>
        <fullName>Please_note_the_reason_for_why_the_account_was_marked_as_a_competitor</fullName>
        <assignedTo>kloper@linkedin.com</assignedTo>
        <assignedToType>user</assignedToType>
        <dueDateOffset>1</dueDateOffset>
        <notifyAssignee>false</notifyAssignee>
        <priority>High</priority>
        <protected>false</protected>
        <status>Not Started</status>
        <subject>Please note the reason for why the account was marked as a competitor</subject>
    </tasks>
</Workflow>
