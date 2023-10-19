/**
 * Created by AB74296 on 5/15/2017.
 */

import {Component, AfterViewInit, ViewChildren, QueryList, Input, OnInit, DoCheck, OnDestroy} from '@angular/core';
import { PMFDataServiceDetails } from '../../../../common/config/pmf-data.service';
import { NgModel, NgForm, AbstractControl } from '@angular/forms';
import { AcceptingNewPatients } from '../../../../common/models/accepting-new-patients.model';
import {FormValidations} from '../../../../common/validations/form-validations';

@Component({
        selector: 'accept-patients',
        template: require('./body.html'),
        styles:  [require('../../../../common/css/pmf-main.css')]
    }
)

export class NewPatientComponent implements AfterViewInit, OnInit, DoCheck, OnDestroy {
    @ViewChildren(NgModel) controls: QueryList<NgModel>;
    controlsList: NgModel [];
    @Input() acceptingNewPatients: AcceptingNewPatients;
    @Input() parentName: string;
    public options: Select2Options;

    constructor(
        private pmfDataService: PMFDataServiceDetails,
        private parentForm: NgForm,
        private formValidations: FormValidations
    ) {}

    ngOnInit() {
        let validators = {};
        validators[this.parentName + '-patientMaximumAge'] = this.validateMaximumAge;
        let validationMessages = this.getValidationMessages();
        this.formValidations.registerValidationMessages(validationMessages);
        this.formValidations.registerCustomValidators(validators);
        this.options = {};
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

    ngOnDestroy() {
      if(this.controls) {
        this.removeFromParentForm();
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

    getValidationMessages(): {} {
        let patientMinimumAge =   this.parentName + '-patientMinimumAge';
        let patientMaximumAge =    this.parentName + '-patientMaximumAge';

        let validationMessages = {};
        validationMessages[patientMinimumAge] = {
            'required': 'Minimum is required.',
            'pattern':  'Minimum is invalid.'
        };
        validationMessages[patientMaximumAge] = {
            'required': 'Maximum is required.',
            'pattern':  'Maximum is invalid.'
        };

        return validationMessages;
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

}
