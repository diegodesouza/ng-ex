import {Component, AfterViewInit, ViewChildren, QueryList, Input, OnInit, DoCheck, OnDestroy} from '@angular/core';
import {PayLoadService} from '../../../../common/services/payload-all.service';
import {DataService} from '../../../../common/config/data-constants';
import { NgModel, NgForm, AbstractControl } from '@angular/forms';
import { Telehealth } from '../../../../common/models/telehealth.model';
import {FormValidations} from '../../../../common/validations/form-validations';

@Component({
        selector: 'telehealth',
        template: require('./body.html'),
        styles:  [require('../../../../common/css/pmf-main.css')]
    }
)

export class TelehealthComponent implements AfterViewInit, OnInit, DoCheck, OnDestroy {
    @ViewChildren(NgModel) controls: QueryList<NgModel>;
    controlsList: NgModel [];
    @Input() telehealth: Telehealth;
    @Input() parentName: string;
    public options: Select2Options;

    constructor(
        private payLoadService: PayLoadService,
        private dataService: DataService,
        private parentForm: NgForm,
        private formValidations: FormValidations
    ) {
      if(!this.payLoadService.telehealth) {
        this.payLoadService.telehealth = new Telehealth();
      }
    }

    ngOnInit() {
        this.formValidations.registerValidationMessages(this.validationMessages);
        this.options = {};
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

    ngOnDestroy() {
      if(this.controls) {
        this.removeFromParentForm();
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

    validationMessages = {
      'telehealthServicesIndicator': {
        'required': 'Telehealth Services is required.'
      }
    };
}
