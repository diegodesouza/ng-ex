/**
 * Created by AD94882 on 6/6/17.
 */

import {Component, AfterViewInit, ViewChildren, QueryList, OnInit} from '@angular/core';
import {PMFDataServiceDetails} from '../../../../common/config/pmf-data.service';
import {PayLoadService} from '../../../../common/services/payload-all.service';
import {NgModel, NgForm} from '@angular/forms';
import {PhoneFax} from '../../../../common/models/phone-fax.model';
import {FormValidations} from '../../../../common/validations/form-validations';

@Component({
    selector: 'phone-fax',
    template: require('./body.html'),
    styles: [require('../../../../common/css/pmf-main.css')]
  }
)

export class PhoneFaxComponent implements AfterViewInit, OnInit {
  @ViewChildren(NgModel) controls: QueryList<NgModel>;
  public options: Select2Options;
  controlsList: NgModel [] = [];
  // This is a patch to a bug with uxd where the toggleId and our Index get out of sync
  public index: number = -1;
  public indexArray: number [] = [];

  constructor(
    private pmfDataService: PMFDataServiceDetails,
    private payloadService: PayLoadService,
    private parentForm: NgForm,
    private formValidations: FormValidations
  ) {
    if(!this.payloadService.phoneFax) {
      this.payloadService.phoneFax = [];
    }
  }

  ngOnInit(): void {
    this.options = {};

    if (this.payloadService.phoneFax.length === 0) {
      this.payloadService.phoneFax.push(
        new PhoneFax()
      );
    }
  }

  ngAfterViewInit() {
    this.addToParentForm();
    if(this.controls) {
      this.controls.changes.subscribe((data) => this.updateFormOnChange(data));
    }
  }

  addPhoneFax() {
    this.index++;
    this.indexArray.push(this.index);
    this.payloadService.phoneFax.push(
      new PhoneFax()
    );
  }

  removePhoneFax(index) {
    this.indexArray.slice(index, 1);
    this.payloadService.phoneFax.splice(index, 1);
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
        this.formValidations.registerValidationMessages(this.getValidationMessages(control));
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

  getValidationMessages(control: NgModel): {} {
    let validationMessages = {};
    validationMessages[control.name] = {
      'required': 'Phone Number is required.',
      'pattern':  'Phone Number is invalid.'
    };
    return validationMessages;
  }
}
