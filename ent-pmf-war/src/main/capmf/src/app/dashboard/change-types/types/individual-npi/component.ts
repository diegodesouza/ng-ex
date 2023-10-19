/**
 * Created by AB74296 on 5/15/2017.
 */

import {Component, AfterViewInit, OnInit, ViewChildren, QueryList, Input} from '@angular/core';
import {PMFDataServiceDetails} from '../../../../common/config/pmf-data.service';
import {PayLoadService} from '../../../../common/services/payload-all.service';
import {NgModel, NgForm} from '@angular/forms';
import {FormValidations} from '../../../../common/validations/form-validations';
import {DataService} from '../../../../common/config/data-constants';

@Component({
    selector: 'npi',
    template: require('./body.html'),
    styles:  [require('../../../../common/css/pmf-main.css')]
  }
)

export class IndividualNpiComponent implements AfterViewInit, OnInit {
  @ViewChildren(NgModel) controls: QueryList<NgModel>;
  @Input() parentName: string;
  public options: Select2Options;

  constructor(
    private dataService: DataService,
    private pmfDataService: PMFDataServiceDetails,
    private payLoadService: PayLoadService,
    private parentForm: NgForm,
    private formValidations: FormValidations
  ) {}

  ngOnInit() {
    this.options = {};
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
    'individualNpi': {
      'required': 'National Provider Identifier (NPI) is required.',
      'pattern':  'National Provider Identifier (NPI) is invalid.'
    },
    'orgNpiStreetAddress1': {
      'required': 'Address Line 1 is required.',
      'pattern':  'Address Line 1 is invalid.'
    },
    'orgNpiCity': {
      'required': 'City is required.',
      'pattern':  'City is invalid.'
    },
    'orgNpiState': {
      'required': 'State is required.'
    },
    'orgNpiZip': {
      'required': 'Zip Code is required.',
      'pattern':  'Zip Code is invalid.'
    },
    'orgNpiCounty': {
      'required': 'County is required.',
      'pattern':  'County is invalid.'
    }
  };
}
