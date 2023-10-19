/**
 * Created by AF39997 on 6/6/2017.
 */
import {
  Component, AfterViewInit, ViewChildren, QueryList, OnInit
} from '@angular/core';
import {NgForm, NgModel} from '@angular/forms';
import {PayLoadService} from '../../../../common/services/payload-all.service';
import {WebAddress} from '../../../../common/models/web-address.model';
import {FormValidations} from '../../../../common/validations/form-validations';

@Component({
    selector: 'web-address',
    template: require('./body.html'),
    styles: [require('../../../../common/css/pmf-main.css')]
  }
)

export class WebAddressChangeComponent implements AfterViewInit, OnInit {
  @ViewChildren(NgModel) controls: QueryList<NgModel>;

  constructor(
    private payLoadService: PayLoadService,
    private parentForm: NgForm,
    private formValidations: FormValidations
  ) {
    if(!this.payLoadService.webAddress) {
      this.payLoadService.webAddress = new WebAddress();
    }
  }

  ngOnInit() {
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
    'webAddress': {
      'required': 'Website URL is required.',
      'pattern':  'Website URL is invalid.'
    }
  };
}
