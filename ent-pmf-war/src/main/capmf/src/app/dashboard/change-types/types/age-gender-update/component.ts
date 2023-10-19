/**
 * Created by AB74296 on 5/15/2017.
 */

import { Component, AfterViewInit, OnInit, ViewChildren, QueryList, Input } from '@angular/core';
import { PMFDataServiceDetails } from '../../../../common/config/pmf-data.service';
import { PayLoadService } from '../../../../common/services/payload-all.service';
import { NgModel, NgForm, AbstractControl } from '@angular/forms';
import {FormValidations} from '../../../../common/validations/form-validations';

@Component({
        selector: 'age-gender',
        template: require('./body.html')
    }
)

export class AgeGenderComponent implements AfterViewInit, OnInit {
    @ViewChildren(NgModel) controls: QueryList<NgModel>;
    @Input() parentName: string;
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
        let validators = {
            'maximumAge': this.validateMaximumAge
        };
        this.formValidations.registerCustomValidators(validators);
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

    validateMaximumAge(caller: any, control?: AbstractControl, field?: string) {
        if(!control || !control.value) {
            return;
        }
        let isInValidAge = parseInt(control.value) < 1;
        if(isInValidAge) {
            control.setErrors({'pattern':  true});
        }
    }

    validationMessages = {
        'minimumAge': {
            'required': 'Minimum Age is required.',
            'pattern':  'Minimum Age is invalid.'
        },
        'maximumAge': {
            'required': 'Maximum Age is required.',
            'pattern':  'Maximum Age is invalid.'
        }
    };
}
