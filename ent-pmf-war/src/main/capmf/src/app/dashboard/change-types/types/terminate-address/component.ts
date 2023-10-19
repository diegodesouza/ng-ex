/**
 * Created by AF39997 on 06/06/17.
 */
import { Input, Component, AfterViewInit, ViewChildren, QueryList, OnInit } from '@angular/core';
import { PMFDataServiceDetails } from '../../../../common/config/pmf-data.service';
import { PayLoadService } from '../../../../common/services/payload-all.service';
import { NgModel, NgForm, AbstractControl } from '@angular/forms';
import { TerminateAddress } from '../../../../common/models/terminate-address.model';
import { DataService } from '../../../../common/config/data-constants';
import {FormValidations} from '../../../../common/validations/form-validations';

@Component({
        selector: 'terminate-address',
        template: require('./body.html'),
        styles: [require('./styles.css')]
    }
)

export class TerminateAddressComponent implements AfterViewInit, OnInit {
    terminateAddress: TerminateAddress;
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
      this.addTerminationAddress();
      this.formValidations.registerValidationMessages(this.validationMessages);
    }

    ngAfterViewInit() {
        this.controls.forEach((control: NgModel) => {
            this.parentForm.addControl(control);
        });
    }

    addTerminationAddress() {
        if(!this.payLoadService.terminateAddress) {
            this.payLoadService.terminateAddress = new TerminateAddress();
        }
    }

    validationMessages = {
        'tterminationDate': {
            'required': 'Termination Date is required.',
            'pattern':  'Termination Date is invalid.'
        }
    };
}
