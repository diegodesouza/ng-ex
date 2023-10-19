/**
 * Created by AC56833 on 5/22/2017.
 */

import {Component, AfterViewInit, ViewChildren, QueryList, OnInit} from '@angular/core';
import {DataService} from '../../../../common/config/data-constants';
import {PMFDataServiceDetails} from '../../../../common/config/pmf-data.service';
import {PayLoadService} from '../../../../common/services/payload-all.service';
import {HospitalPrivilege} from '../../../../common/models/hospital-privilege.model';
import {NgModel, NgForm} from '@angular/forms';
import {FormValidations} from '../../../../common/validations/form-validations';

@Component({
    selector: 'hospital-affiliation',
    template: require('./body.html'),
    styles: [require('../../../../common/css/pmf-main.css')]
  }
)

export class HospitalPrivilegeComponent implements AfterViewInit, OnInit {
  @ViewChildren(NgModel) controls: QueryList<NgModel>;
  controlsList: NgModel [] = [];
  public options: Select2Options;
  // This is a patch to a bug with uxd where the toggleId and our Index get out of sync
  public index: number = -1;
  public indexArray: number [] = [];

  constructor(
    private dataService: DataService,
    private pmfDataService: PMFDataServiceDetails,
    private payloadService: PayLoadService,
    private parentForm: NgForm,
    private formValidations: FormValidations
  ) {}

  ngOnInit(): void {
    this.options = {};

    if (this.payloadService.hospitalPrivileges.length === 0) {
      this.payloadService.hospitalPrivileges.push(
        new HospitalPrivilege()
      );
    }
  }

  ngAfterViewInit() {
    this.updateParentForm();
    if(this.controls) {
      this.controls.changes.subscribe((data) => this.updateFormOnChange(data));
    }
  }

  addHospitalPrivilege() {
    this.index++;
    this.indexArray.push(this.index);
    this.payloadService.hospitalPrivileges.push(
      new HospitalPrivilege()
    );
  }

  removeHospitalPrivilege(index) {
    this.indexArray.slice(index, 1);
    this.payloadService.hospitalPrivileges.splice(index, 1);
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
      'required': 'Name of Affiliation is required.',
      'pattern':  'Name of Affiliation is invalid.'
    };
    return validationMessages;
  }
}
