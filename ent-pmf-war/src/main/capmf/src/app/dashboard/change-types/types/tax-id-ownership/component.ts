/**
 * Created by AC56833 on 5/16/2017.
 */

import { DataService } from '../../../../common/config/data-constants';
import { Component, AfterViewInit, OnInit, ViewChildren, QueryList } from '@angular/core';
import { PayLoadService } from '../../../../common/services/payload-all.service';
import { NgModel, NgForm } from '@angular/forms';
import {FormValidations} from '../../../../common/validations/form-validations';

@Component({
        selector: 'tax-identification',
        template: require('./body.html'),
        styles: [require('./styles.css')]
    }
)
export class TaxIdOwnershipComponent implements AfterViewInit, OnInit {
    @ViewChildren(NgModel) controls: QueryList<NgModel>;

    constructor(private dataService: DataService,
                private payloadService: PayLoadService,
                private parentForm: NgForm,
                private formValidations: FormValidations
    ) {}

    ngOnInit() {
        this.formValidations.registerValidationMessages(this.validationMessages);
    }

    ngAfterViewInit() {
        this.addToParentForm();
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
        'tinUpdate': {
            'required': 'Tax Identification Number (TIN) is required.',
            'pattern':  'Tax Identification Number (TIN) is invalid.'
        },
        'npiUpdate': {
            'required': 'National Provider Identifier (NPI) is required.',
            'pattern':  'National Provider Identifier (NPI) is invalid.'
        }
    };
}
