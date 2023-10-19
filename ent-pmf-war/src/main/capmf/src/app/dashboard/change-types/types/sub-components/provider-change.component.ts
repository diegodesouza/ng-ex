/**
 * Created by AD94882 on 5/8/17.
 */

import {
    Component, AfterViewInit, ViewChildren, QueryList, Input, OnInit, DoCheck
} from '@angular/core';
import {ProviderChange} from '../../../../common/models/providerchange.model';
import {NgModel, NgForm} from '@angular/forms';
import {FormValidations} from '../../../../common/validations/form-validations';
import {PMFDataServiceDetails} from '../../../../common/config/pmf-data.service';

@Component({
        selector: 'provider-change',
        template: require('./provider-change.html'),
        styles: [require('../../../../common/css/pmf-main.css')]
    }
)

export class ProviderChangeComponent implements AfterViewInit, OnInit, DoCheck {
    controlsList: NgModel [];
    @Input() providerChange: ProviderChange;
    @Input() parentName: string;
    @ViewChildren(NgModel) controls: QueryList<NgModel>;
    public options: Select2Options;

    constructor(
        private parentForm: NgForm,
        private formValidations: FormValidations,
        private pmfDataService: PMFDataServiceDetails
    ) {}

    ngOnInit() {
        this.options = {};
        this.formValidations.registerValidationMessages(this.validationMessages());
    }

    ngAfterViewInit() {
        this.updateParentForm();
    }

    ngDoCheck() {
        // Check if Controls Changed
        if(this.controls && (this.controlsList.length !== this.controls.length)) {
            this.updateParentForm();
        }
    }

    updateParentForm() {
        this.removeFromParentForm();
        this.addToParentForm();
    }

    addToParentForm() {
        if(this.controls) {
            this.controlsList = this.controls.toArray();
            this.controlsList.forEach((control: NgModel) => {
                this.parentForm.addControl(control);
            });
        }
    }

    removeFromParentForm() {
        if(this.controlsList) {
            this.controlsList.forEach((control: NgModel) => {
                this.parentForm.removeControl(control);
            });
        }
    }

    validationMessages(): {} {
        let firstName =   this.parentName + '-firstName';
        let lastName =    this.parentName + '-lastName';
        let middleName =  this.parentName + '-middleName';
        let orgNpi =      this.parentName + '-orgNpi';

        let validationMessages = {};
        validationMessages[firstName] = {
            'required': 'First Name is required.',
            'pattern':  'First Name is invalid.'
        };
        validationMessages[lastName] = {
            'required': 'Last Name is required.',
            'pattern':  'Last Name is invalid.'
        };
        validationMessages[middleName] = {
            'pattern':  'Middle Name is invalid.'
        };
        validationMessages[orgNpi] = {
            'required': 'NPI is required.',
            'pattern':  'NPI is invalid.'
        };

        return validationMessages;
    }
}
