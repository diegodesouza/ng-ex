import {Component, Input, OnInit, QueryList, ViewChildren} from '@angular/core';
import {DataService} from '../../../../common/config/data-constants';
import {PayLoadService} from '../../../../common/services/payload-all.service';
import {SelectUpdatesModel} from '../../../../common/config/select-updates.model';
import {NgForm, NgModel} from '@angular/forms';
import {FormValidations} from '../../../../common/validations/form-validations';

@Component({
        selector: 'unsolicited-roster',
        template: require('./body.html'),
        styles:  [require('../../../../common/css/pmf-main.css')]
    }
)

export class UnsolicitedRosterComponent implements OnInit{
  @Input() attachments: boolean;
  @ViewChildren(NgModel) controls: QueryList<NgModel>;
  controlsList: NgModel [];

  constructor(
    private dataService: DataService,
    private payloadService: PayLoadService,
    private formValidations: FormValidations,
    private parentForm: NgForm,
    private updates: SelectUpdatesModel
  ) {}

  ngOnInit() {
    this.formValidations.registerValidationMessages(this.validationMessages);
    if(this.attachments) {
      this.payloadService.isAttachmentRequired = true;
      this.updates.updateAttachmentStatus();
    }
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

  ngOnDestroy() {
    this.updates.updateRequiredAttachment();
  }

  validationMessages = {
    'rosterNumberOfProviders': {
      'required': 'Number of Providers Impacted is required.',
      'pattern':  'Number of Providers Impacted is invalid.'
    }
  };
}
