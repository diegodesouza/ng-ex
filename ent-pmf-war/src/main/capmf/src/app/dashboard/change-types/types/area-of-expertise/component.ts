/**
 * Created by AB74296 on 5/15/2017.
 */

import {
  Component, AfterViewInit, ViewChildren, QueryList, OnInit, OnDestroy
} from '@angular/core';
import { PayLoadService } from '../../../../common/services/payload-all.service';
import { AreaOfExpertise } from '../../../../common/models/area-of-expertise.model';
import {NgModel, NgForm} from '@angular/forms';
import {PMFDataServiceDetails} from '../../../../common/config/pmf-data.service';

@Component({
        selector: 'expertise',
        template: require('./body.html'),
        styles: [require('../../../../common/css/pmf-main.css')]
    }
)

export class ProviderExpertiseComponent implements AfterViewInit, OnInit, OnDestroy {
    @ViewChildren(NgModel) controls: QueryList<NgModel>;
    controlsList: NgModel [] = [];
    // This is a patch to a bug with uxd where the toggleId and our Index get out of sync
    public index: number = -1;
    public indexArray: number [] = [];

    constructor(
        private pmfDataService: PMFDataServiceDetails,
        private payLoadService: PayLoadService,
        private parentForm: NgForm
    ) {}

    ngOnInit(): void {
        if (this.payLoadService.areaOfExpertise.length === 0) {
            this.payLoadService.areaOfExpertise.push(
                new AreaOfExpertise()
            );
        }
    }

    ngAfterViewInit() {
      this.updateParentForm();
      if(this.controls) {
        this.controls.changes.subscribe((data) => this.updateFormOnChange(data));
      }
    }

  ngOnDestroy() {
      if(this.controls) {
        this.removeFromParentForm();
      }
  }

  addAreasOfExpertise() {
      this.index++;
      this.indexArray.push(this.index);
      this.payLoadService.areaOfExpertise.push(
          new AreaOfExpertise()
      );
  }

  removeAreaOfExpertise(index) {
      this.indexArray.slice(index, 1);
      this.payLoadService.areaOfExpertise.splice(index, 1);
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
}
