/**
 * Created by AF39997 on 06/06/17.
 */
import {Component, AfterViewInit, ViewChildren, QueryList} from '@angular/core';
import {PayLoadService} from '../../../../common/services/payload-all.service';
import {NgModel, NgForm} from '@angular/forms';
import {ProviderLeavingGroup} from '../../../../common/models/provider-leaving-group.model';
import {ProviderChange} from '../../../../common/models/providerchange.model';

@Component({
  selector: 'provider-leaving-group',
  template: require('./body.html')
})

export class ProviderLeavingGroupComponent implements AfterViewInit {
  providerChange: ProviderChange;
  parentName: string = 'providerLeavingGroup';
  @ViewChildren(NgModel) controls: QueryList<NgModel>;

  constructor(
    private payLoadService: PayLoadService,
    private parentForm: NgForm
  ) {
    if(!payLoadService.providerLeavingGroup) {
      payLoadService.providerLeavingGroup = new ProviderLeavingGroup();
    }
  }

  ngAfterViewInit() {
    this.controls.forEach((control: NgModel) => {
      this.addToParentForm();
    });
  }

  addToParentForm() {
    if(this.controls !== null) {
      this.controls.forEach((control: NgModel) => {
        if(!this.parentForm.form.contains(control.name)) {
          this.parentForm.addControl(control);
        }
      });
    }
  }
}
