/**
 * Created by AF39997 on 6/6/2017.
 */
import {
  Component, AfterViewInit, ViewChildren, QueryList, OnInit, Input, OnDestroy
} from '@angular/core';
import { NgModel, NgForm } from '@angular/forms';
import {PayLoadService} from '../../../../common/services/payload-all.service';
import {FormValidations} from '../../../../common/validations/form-validations';
import {DataService} from '../../../../common/config/data-constants';
import {SelectUpdatesModel} from '../../../../common/config/select-updates.model';

@Component({
      selector: 'organization-name',
      template: require('./body.html'),
      styles: [require('../../../../common/css/pmf-main.css')]
    }
)

export class OrganizationNameChangeComponent implements AfterViewInit, OnInit, OnDestroy {
  @Input() attachments: boolean;
  @ViewChildren(NgModel) controls: QueryList<NgModel>;

  constructor(
      private dataService: DataService,
      private payLoadService: PayLoadService,
      private parentForm: NgForm,
      private formValidations: FormValidations,
      private updates: SelectUpdatesModel
  ) {}

  ngOnInit() {
    this.formValidations.registerValidationMessages(this.validationMessages);
    if(this.attachments) {
      this.payLoadService.isAttachmentRequired = true;
      this.updates.updateAttachmentStatus();
    }
  }

  ngAfterViewInit() {
    this.addToParentForm();
  }

  ngOnDestroy() {
    this.updates.updateRequiredAttachment();
  }

  addToParentForm() {
    if (this.controls != null) {
      this.controls.forEach((control: NgModel) => {
        if(!this.parentForm.form.contains(control.name)) {
          this.parentForm.addControl(control);
        }
      });
    }
  }

  validationMessages = {
    'organizationName': {
      'required': 'Organization Name is required.',
      'pattern':  'Organization Name is invalid.'
    }
  };
}
