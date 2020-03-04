import { LightningElement, wire, track, api } from 'lwc';
import executeSOQLQuery from '@salesforce/apex/LightningUtilController.executeSOQLQuery';

export default class relatedListComp extends LightningElement {
    @api objectName;
    @api parentFieldName;
    @api parentId;
    @api columns;
    @api header;
    @api nameField;
    @api isQuickEdit;
    @api editColumns;
    @api criteria;
    @api order;
    @track records;
    @track editRecord = false;
    @track showTable = true;
    @track error;
    @track modColumns;


    connectedCallback() {
        var query =  'SELECT Id';
        for (var i = 0; i < this.columns.length; i++) {
            query = query + ', ' + this.columns[i].fieldName;
        }
    
        query = query + ' FROM ' + this.objectName + ' WHERE ' + this.parentFieldName + ' = \'' + this.parentId + '\'';
        if(this.criteria){
            query = query + ' AND ' + this.criteria;
        }
        if(this.order){
            query = query + ' ORDER BY ' + this.order;
        }
        executeSOQLQuery({soql: query})
        .then(result => {
            var columns = JSON.parse(JSON.stringify(this.columns));
            var records = JSON.parse(JSON.stringify(result));
            records.forEach(function(item){
                columns.forEach(function(col){
                    if(col.fieldName.includes('.')){
                        var par = col.fieldName.split('.')[0];
                        var fName = col.fieldName.split('.')[1];
                        if(item[par]){
                            item[par + fName] = item[par][fName];
                        }
                    }
                    if(col.fieldName == 'Name'){
                        item['Name_url'] = '/' + item.Id;
                    }
                });
            });
            columns.forEach(function(col){
            if(col.fieldName.includes('.')){
                var par = col.fieldName.split('.')[0];
                var fName = col.fieldName.split('.')[1];
                col.fieldName = par + fName;
            }
            if(col.fieldName == 'Name'){
                col.type = 'url';
                col.typeAttributes = {};
                col.typeAttributes.label = {fieldName: 'Name'};
                col.typeAttributes.target = '_blank';
                col.fieldName = 'Name_url';
            }
            });

            if(this.isQuickEdit || this.objectName == 'EmailMessage'){
                var col = {};
                col.type = 'button';
                col.label = 'Action';
                col.typeAttributes = {};
                if(this.objectName == 'EmailMessage'){
                    col.typeAttributes.label = 'Reply All';
                    col.typeAttributes.name = 'replyAll';
                    col.typeAttributes.variant = 'base';
                }
                else {
                    col.typeAttributes.label = 'Edit';
                    col.typeAttributes.name = 'edit';
                    col.typeAttributes.variant = 'base';
                }
                columns.push(col);
            }
            this.records = records;
            this.modColumns = columns;

        })
        .catch(error => {
            alert(JSON.stringify(error));
            this.error = error;
        });
    }

    renderedCallback() {   
        const style = document.createElement('style');
        style.innerText = `
        .slds-button_icon-bare {
            display: none;
        }
        `;
        if(this.template.querySelector('lightning-datatable')){
            this.template.querySelector('lightning-datatable').appendChild(style);
        }   
      }

      showEdit(event) {
        const actionName = event.detail.action.name;
        const row = event.detail.row;
        if(actionName == 'edit'){
            this.recordId = row.Id;
            this.editRecord = true;
            this.showTable = false;
        }
        else if(actionName == 'replyAll' && this.objectName == 'EmailMessage'){
            window.open('/_ui/core/email/author/EmailAuthor?replyToAll=1&email_id=' + row.Id,'_blank');
        }
    }

    handleSuccess(event){
        this.editRecord = false;
        this.showTable = true;
        this.recordId = undefined;
     }

    cancelEdit(){
        this.editRecord = false;
        this.showTable = true;  
    }
}