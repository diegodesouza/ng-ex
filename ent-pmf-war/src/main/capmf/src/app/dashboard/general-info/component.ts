/**
 * Created by AD94882 on 4/18/17.
 */

import { Component, OnInit, ViewChild } from '@angular/core';
import { PayLoadService } from '../../common/services/payload-all.service';
import { PMFDataServiceDetails } from '../../common/config/pmf-data.service';
import { Address } from '../../common/models/address.model';
import { Router } from '@angular/router';
import { NgForm, AbstractControl } from '@angular/forms';
import {FormValidations} from '../../common/validations/form-validations';
import {PMFConfigService} from '../../common/config/pmf-config.service';
// import {Select2OptionData} from 'ng2-select2';

@Component({
    template: require('./body.html'),
    styles:  [require('./style/styles.css'), require('../../common/css/pmf-main.css')]
  }
)

export class GeneralInfoComponent implements OnInit {
  address: Address;
  generalInfoForm: NgForm;
  @ViewChild('generalInfoForm') currentForm: NgForm;
  public options: {};

  constructor(
    private pmfDataService: PMFDataServiceDetails,
    private payLoadService: PayLoadService,
    private router: Router,
    private formValidations: FormValidations,
    private configService: PMFConfigService
  ) {
    payLoadService.targetSystems = configService.CONFIG.generalInfo.LOBS.fields;
    if(configService.getState() === 'me') {
      pmfDataService.JSON_DATA.field_mapping_options.title.fields = configService.CONFIG.title.fields;
      pmfDataService.JSON_DATA.field_mapping_options.provider_specialty.fields = configService.CONFIG.provider_specialty.fields;
      pmfDataService.JSON_DATA.field_mapping_options.pcp_assistant.fields = configService.CONFIG.pcp_assistant.fields;
      pmfDataService.JSON_DATA.field_mapping_options.pcp_assistant.field_name = configService.CONFIG.pcp_assistant.field_name;
    }
  }

  ngOnInit() {
    let validators = {};
    this.payLoadService.targetSystems.forEach((item, index) => {
      validators['targetSystems-'+index] = this.validateNetworks.bind(this);
      this.validationMessages['targetSystems-'+index] = {'required':'Network is required.'};
    });
    this.currentForm.name = 'generalInfoForm';
    this.formValidations.subscribeForm(this.currentForm);
    this.formValidations.registerValidationMessages(this.validationMessages);
    this.formValidations.registerCustomValidators(validators);
    this.options = {};
  }

  validateNetworks(caller: any, control?: AbstractControl, field?: string) {
    let selectedNetworks = caller.payLoadService.targetSystems
      .filter((opt) => opt.checked).length;

    if(selectedNetworks < 1) {
      this.setNetworksErrorMessage(control, field);
      control.setErrors({'required':  true});
    } else {
      this.clearNetworksErrorMessage();
      control.setErrors(null);
    }
  }

  setNetworksErrorMessage(control?: AbstractControl, field?: string) {
    if(control.dirty) {
      let message = this.validationMessages[field]['required'];
      this.formValidations.addCustomMessage('SelectedNetworksErrorMessage', message);
    }
  }

  clearNetworksErrorMessage() {
    this.formValidations.clearCustomMessage('SelectedNetworksErrorMessage');
  }

  routeToNext() {
    this.router.navigate(['dashboard/selectupdates'], {skipLocationChange: true});
  }

  routeToPrevious() {
    this.router.navigate(['/landingpage'], {skipLocationChange: true});
  }

  validationMessages = {
    'effectiveDate': {
      'required':     'Effective Date is required.',
      'pattern':      'Effective Date is invalid.'
    },
    'indTaxId': {
      'required':     'Tax Identification Number (TIN) is required.',
      'pattern':      'Tax Identification Number (TIN) is invalid.'
    },
    'npi': {
      'required':     'Provider National Provider Identifier (NPI) is required.',
      'pattern':      'Provider National Provider Identifier (NPI) is invalid.'
    },
    'lastName': {
      'required':     'Last Name is required.',
      'pattern':      'Last Name is invalid.'
    },
    'middleName': {
      'pattern':      'Middle Name is invalid.'
    },
    'firstName': {
      'required':     'First Name is required.',
      'pattern':      'First Name is invalid.'
    },
    'licenseNumber': {
      'required':     'License / Certification Number is required.',
      'pattern':      'License / Certification Number is invalid.'
    },
    'practiceName': {
      'required':     'Practice Name is required.'
    },
    'tin': {
      'required':     'Organizational Tax Identification Number (TIN) is required.',
      'pattern':      'Organizational Tax Identification Number (TIN) is invalid.'
    },
    'orgNpi': {
      'required':     'Organizational National Provider Identifier (NPI) is required.',
      'pattern':      'Organizational National Provider Identifier (NPI) is invalid.'
    },
    'ctfirstName': {
      'required':     'First Name is required.',
      'pattern':      'First Name is invalid.'
    },
    'ctlastName': {
      'required':     'Last Name is required.',
      'pattern':      'Last Name is invalid.'
    },
    'email': {
      'required':     'Email Address is required.',
      'pattern':      'Email Address is invalid.'
    },
    'phoneNumber': {
      'required':     'Phone Number is required.',
      'pattern':      'Phone Number is invalid.'
    }
  };

}
