/**
 * Created by AD94882 on 5/8/17.
 */
import {
    Component, AfterViewInit, ViewChildren, QueryList, Input, OnInit, OnDestroy, DoCheck
} from '@angular/core';
import { Address } from '../../../../common/models/address.model';
import { NgModel, NgForm } from '@angular/forms';
import {FormValidations} from '../../../../common/validations/form-validations';
import {PMFDataServiceDetails} from '../../../../common/config/pmf-data.service';
import {PayLoadService} from '../../../../common/services/payload-all.service';
import {SelectUpdatesModel} from '../../../../common/config/select-updates.model';

@Component({
        selector: 'address-change',
        template: require('./address-change.html'),
        styles: [require('../../../../common/css/pmf-main.css')]
    }
)

export class AddressChangeComponent implements AfterViewInit, OnInit, OnDestroy, DoCheck {
    constructor(
        private parentForm: NgForm,
        private payLoadService: PayLoadService,
        private formValidations: FormValidations,
        private pmfDataService: PMFDataServiceDetails,
        private updates: SelectUpdatesModel
    ) {}

    @ViewChildren(NgModel) controls: QueryList<NgModel>;
    @Input() address: Address;
    @Input() parentName: string;
    @Input() attachments: boolean;
    private controlsLoaded: boolean;
    public options: Select2Options;

    ngOnInit() {
        this.controlsLoaded = false;
        this.formValidations.registerValidationMessages(this.validationMessages());
        this.options = {};
        if(this.attachments) {
          this.payLoadService.isAttachmentRequired = true;
          this.updates.updateAttachmentStatus();
        }
    }

    ngAfterViewInit() {
        this.addToParentForm();
    }

    ngDoCheck()  {
        if(this.controls && !this.controlsLoaded) {
            this.addToParentForm();
            this.controlsLoaded = true;
        }
    }

    ngOnDestroy() {
        if(this.controls && this.controlsLoaded) {
            this.removeToParentForm();
            this.controlsLoaded = false;
        }
        this.updates.updateRequiredAttachment();
    }

    removeToParentForm() {
        if(this.controls != null) {
            this.controls.forEach((control: NgModel) => {
                if(this.parentForm.form.contains(control.name)) {
                    this.parentForm.removeControl(control);
                }
            });
        }
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

  validationMessages(): {} {
    let streetAddress1 = this.parentName + '-streetAddress1';
    let streetAddress2 = this.parentName + '-streetAddress2';
    let city =           this.parentName + '-city';
    let zip =            this.parentName + '-zip';
    let county =         this.parentName + '-county';
    let phone =          this.parentName + '-phone-address';
    let fax =            this.parentName + '-fax-address';
    let email =          this.parentName + '-email-address';
    let validationMessages = {};

    validationMessages[streetAddress1] = {
      'required': 'Address Line 1 is required.',
      'pattern':  'Address Line 1 is invalid.'
    };
    validationMessages[streetAddress2] = {
      'required': 'Address Line 2 is required.',
      'pattern':  'Address Line 2 is invalid.'
    };
    validationMessages[city] = {
      'required': 'City is required.',
      'pattern':  'City is invalid.'
    };
    validationMessages[zip] = {
      'required': 'Zip is required.',
      'pattern':  'Zip is invalid.'
    };
    validationMessages[county] = {
      'required': 'County is required.',
      'pattern':  'County is invalid.'
    };
    validationMessages[phone] = {
      'required': 'Phone is required.',
      'pattern':  'Phone is invalid.'
    };
    validationMessages[fax] = {
      'required': 'FAX is required.',
      'pattern':  'FAX is invalid.'
    };
    validationMessages[email] = {
      'required': 'Email is required.',
      'pattern':  'Email is invalid.'
    };

    return validationMessages;
  }
}
