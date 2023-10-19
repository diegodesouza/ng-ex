/**
 * Created by AB74296 on 5/15/2017.
 */

import { Component, AfterViewInit, OnInit, ViewChildren, QueryList } from '@angular/core';
import { PMFDataServiceDetails } from '../../../../common/config/pmf-data.service';
import { PayLoadService } from '../../../../common/services/payload-all.service';
import { NgModel, NgForm } from '@angular/forms';
import {FormValidations} from '../../../../common/validations/form-validations';

@Component({
        selector: 'license-number',
        template: require('./body.html')
    }
)

export class LicenseUpdateComponent implements AfterViewInit, OnInit {
    @ViewChildren(NgModel) controls: QueryList<NgModel>;
    public options: Select2Options;

    constructor(
        private pmfDataService: PMFDataServiceDetails,
        private payLoadService: PayLoadService,
        private parentForm: NgForm,
        private formValidations: FormValidations
    ) {}

    ngOnInit() {
      this.options = {};
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
        'updateLicense': {
            'required': 'License / Certification Number is required.',
            'pattern':  'License / Certification Number is invalid.'
        }
    };
}
