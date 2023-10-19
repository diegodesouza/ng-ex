/**
 * Created by AC56833 on 5/22/2017.
 */

import {Component, AfterViewInit, Input, ViewChildren, QueryList, OnInit, OnDestroy} from '@angular/core';
import { PMFDataServiceDetails } from '../../../../common/config/pmf-data.service';
import { HandicappedAccessibility } from '../../../../common/models/handicapped-accesibility.model';
import { NgModel, NgForm } from '@angular/forms';

@Component({
        selector: 'handicap-accessibility',
        template: require('./body.html'),
        styles:  [require('../../../../common/css/pmf-main.css')]
    }
)

export class HandicappedAccessibilityComponent implements OnInit, AfterViewInit, OnDestroy {
    @ViewChildren(NgModel) controls: QueryList<NgModel>;
    controlsList: NgModel [] = [];
    @Input() handicappedAccessibility: HandicappedAccessibility;
    @Input() parentName: string;
    public options: Select2Options;

    constructor(private pmfDataService: PMFDataServiceDetails, private parentForm: NgForm) {
    }

    ngOnInit() {
       this.options = {};
    }

    ngAfterViewInit() {
        this.addToParentForm();
    }

    addToParentForm() {
        if(this.controls) {
          this.controlsList = this.controls.toArray();
            this.controls.forEach((control: NgModel) => {
                if(!this.parentForm.form.contains(control.name)) {
                    this.parentForm.addControl(control);
                }
            });
        }
    }

  ngOnDestroy() {
    if(this.controls) {
      this.removeFromParentForm();
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
