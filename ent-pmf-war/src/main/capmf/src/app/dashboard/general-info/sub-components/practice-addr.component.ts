/**
 * Created by AD94882 on 5/4/17.
 */
import {
    Component, AfterViewInit, AfterViewChecked, ViewChildren, QueryList, Input, OnInit,
    DoCheck, AfterContentChecked, OnChanges
} from '@angular/core';
import { PMFDataServiceDetails } from '../../../common/config/pmf-data.service';
import { PayLoadService } from '../../../common/services/payload-all.service';
import { Address } from '../../../common/models/address.model';
import { NgModel, NgForm } from '@angular/forms';
import {Router} from '@angular/router';
import {FormValidations} from '../../../common/validations/form-validations';

@Component({
        selector: 'practice-address',
        template: require('./practice-address.html'),
        styles: [require('./practice-address.css'), require('../../../common/css/pmf-main.css')]
    }
)

export class AddressComponent implements AfterViewInit, OnInit {
    @ViewChildren(NgModel) controls: QueryList<NgModel>;
    @Input() address: Address;
    @Input() parentName;
    private isHidden: boolean;
    public options: Select2Options;

    constructor(
        private pmfDataService: PMFDataServiceDetails,
        private userTypeService: PayLoadService,
        private parentForm: NgForm,
        private router: Router,
        private formValidations: FormValidations
    ) {}

    ngOnInit() {
      this.options = {};
      this.showToolTip();
    }

    ngAfterViewInit() {
        this.controls.forEach((control: NgModel) => {
            this.addToParentForm();
        });
    }

    showToolTip() {
      if(this.router.url === '/dashboard/generalinfo') {
        this.isHidden = true;
      }

    }

    addToParentForm() {
        if(this.controls != null) {
            this.controls.forEach((control: NgModel) => {
                if(!this.parentForm.form.contains(control.name)) {
                    this.addErrorMessages(control);
                    this.parentForm.addControl(control);
                }
            });
        }
    }

    addErrorMessages(control: NgModel) {
        switch (control.name)
        {
            case this.parentName+'-streetAddress1':
                this.formValidations.registerValidationMessages(this.getStreetAddress1ErrorMessages(control));
                break;
            case this.parentName+'-city':
                this.formValidations.registerValidationMessages(this.getCityErrorMessages(control));
                break;
            case this.parentName+'-zipcode':
                this.formValidations.registerValidationMessages(this.getZipErrorMessages(control));
                break;
            case this.parentName+'-county':
                this.formValidations.registerValidationMessages(this.getCountyErrorMessages(control));
                break;
        }
    }

    getStreetAddress1ErrorMessages(control: NgModel): {} {
        let validationMessages = {};
        validationMessages[control.name] = {
            'required':      'Address Line 1 is required.',
            'pattern':      'Address Line 1 is invalid.'
        };
        return validationMessages;
    }

    getCityErrorMessages(control: NgModel): {} {
        let validationMessages = {};
        validationMessages[control.name] = {
            'required':      'City is required.',
            'pattern':      'City is invalid.'
        };
        return validationMessages;
    }

    getZipErrorMessages(control: NgModel): {} {
        let validationMessages = {};
        validationMessages[control.name] = {
            'required':      'Zip Code is required.',
            'pattern':      'Zip Code is invalid.'
        };
        return validationMessages;
    }

    getCountyErrorMessages(control: NgModel): {} {
        let validationMessages = {};
        validationMessages[control.name] = {
            'required':      'County is required.',
            'pattern':      'County is invalid.'
        };
        return validationMessages;
    }
}
