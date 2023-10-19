/**
 * Created by AD94882 on 4/18/17.
 */

import {
    Component, AfterViewInit, ViewChildren, QueryList, OnInit
} from '@angular/core';
import { DataService } from '../../../../common/config/data-constants';
import { PMFDataServiceDetails } from '../../../../common/config/pmf-data.service';
import { PayLoadService } from '../../../../common/services/payload-all.service';
import {NgModel, NgForm} from '@angular/forms';
import {AdditionalSpecialty} from '../../../../common/models/specialty.model';

@Component({
        selector: 'provider-specialty',
        template: require('./body.html'),
        styles: [require('./styles.css'), require('../../../../common/css/pmf-main.css')]
    }
)

export class ProviderSpecialtyComponent implements AfterViewInit, OnInit {
    @ViewChildren(NgModel) controls: QueryList<NgModel>;
    controlsList: NgModel [] = [];
    public options: Select2Options;

    constructor(
        private dataService: DataService,
        private pmfDataService: PMFDataServiceDetails,
        private payloadService: PayLoadService,
        private parentForm: NgForm
    ) {}

    ngOnInit() {
      this.options = {};

      if (this.payloadService.specialty.additionalSpecialties.length === 0) {
        this.payloadService.specialty.additionalSpecialties.push(new AdditionalSpecialty());
      }
    }

    ngAfterViewInit() {
        this.updateParentForm();
        if(this.controls) {
            this.controls.changes.subscribe((data) => this.updateFormOnChange(data));
        }
    }

    addAdditionalSpecialty() {
        this.payloadService.specialty.additionalSpecialties.push(new AdditionalSpecialty());
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

    removeProviderSpecialty(index) {
        this.payloadService.specialty.additionalSpecialties.splice(index, 1);
        this.removeFromParentForm();
    }
}
