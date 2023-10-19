/**
 * Created by AD94882 on 4/18/17.
 */

import {
  Component, AfterViewInit, ViewChildren, QueryList, OnInit
} from '@angular/core';
import {PayLoadService} from '../../../../common/services/payload-all.service';
import {Address} from '../../../../common/models/address.model';
import {Email} from '../../../../common/models/email.model';
import {NgModel, NgForm} from '@angular/forms';
import {FormValidations} from '../../../../common/validations/form-validations';

@Component({
    selector: 'email-address',
    template: require('./body.html'),
    styles: [require('../../../../common/css/pmf-main.css')]
  }
)

export class EmailComponent implements AfterViewInit, OnInit {
  @ViewChildren(NgModel) controls: QueryList<NgModel>;
  address: Address;
  controlsList: NgModel [] = [];
  // This is a patch to a bug with uxd where the toggleId and our Index get out of sync
  public index: number = -1;
  public indexArray: number [] = [];

  constructor(
      private payloadService: PayLoadService,
      private parentForm: NgForm,
      private formValidations: FormValidations
  ) {}

  ngOnInit() {
    if(this.payloadService.email.length === 0) {
      this.addEmailAddress();
    }
  }

  ngAfterViewInit() {
    this.updateParentForm();
    if(this.controls) {
      this.controls.changes.subscribe((data) => this.updateFormOnChange(data));
    }
  }

  addEmailAddress() {
    let email = new Email();
    email.requestActionCd = 'A';
    this.index++;
    this.indexArray.push(this.index);
    this.payloadService.email.push(email);
  }

  removeAddress(index) {
    this.indexArray.slice(index, 1);
    this.payloadService.email.splice(index, 1);
    this.removeFromParentForm();
  }

  updateFormOnChange(data?: any) {
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
        this.formValidations.registerValidationMessages(this.getErrorMessages(control));
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

  getErrorMessages(control: NgModel): {} {
    let validationMessages = {};
    validationMessages[control.name] = {
      'required': 'Email Address is required.',
      'pattern':  'Email Address is invalid.'
    };
    return validationMessages;
  }
}
