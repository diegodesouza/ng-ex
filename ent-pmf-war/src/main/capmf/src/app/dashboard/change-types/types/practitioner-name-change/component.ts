/**
 * Created by AB74296 on 5/15/2017.
 */

import {Component, AfterViewInit, OnInit, ViewChildren, QueryList, Input, OnDestroy} from '@angular/core';
import { PMFDataServiceDetails } from '../../../../common/config/pmf-data.service';
import { PayLoadService } from '../../../../common/services/payload-all.service';
import { NgModel, NgForm } from '@angular/forms';
import {FormValidations} from '../../../../common/validations/form-validations';
import {SelectUpdatesModel} from '../../../../common/config/select-updates.model';

@Component({
        selector: 'practitioner-name',
        template: require('./body.html'),
        styles:  [require('../../../../common/css/pmf-main.css')]
    }
)

export class PractitionerNameChangeComponent implements AfterViewInit, OnInit, OnDestroy {
    @ViewChildren(NgModel) controls: QueryList<NgModel>;
    public options: Select2Options;

    constructor(
        private pmfDataService: PMFDataServiceDetails,
        private payLoadService: PayLoadService,
        private parentForm: NgForm,
        private formValidations: FormValidations,
        private updates: SelectUpdatesModel
    ) {}

    ngOnInit() {
        this.options = {};
        this.formValidations.registerValidationMessages(this.validationMessages);
    }

    ngAfterViewInit() {
        this.addToParentForm();
    }

    ngOnDestroy() {
      this.updates.updateRequiredAttachment();
    }

    addToParentForm() {
        if(this.controls != null) {
            this.controls.forEach((control: NgModel) => {
                if(!this.parentForm.form.contains(control.name)) {
                    this.parentForm.addControl(control);
                }
            });
        }
    }

    validationMessages = {
        'practitionerLastName': {
            'required': 'Last Name is required.',
            'pattern':  'Last Name is invalid.'
        },
        'practitionerMiddleName': {
            'pattern':  'Middle Name is invalid.'
        },
        'practitionerFirstName': {
            'required': 'First Name is required.',
            'pattern':  'First Name is invalid.'
        },
        'practitionerLicense': {
            'required': 'License / Certification Number is required.',
            'pattern':  'License / Certification Number is invalid.'
        }
    };
}
