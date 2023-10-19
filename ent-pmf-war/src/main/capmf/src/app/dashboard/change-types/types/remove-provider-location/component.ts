/**
 * Created by AF39997 on 06/06/17.
 */
import {Component, AfterViewInit, ViewChildren, QueryList} from '@angular/core';
import {PayLoadService} from '../../../../common/services/payload-all.service';
import {NgModel, NgForm} from '@angular/forms';
import {RemoveProviderFromLocation} from '../../../../common/models/remove-provider-location';
import {ProviderChange} from '../../../../common/models/providerchange.model';

@Component({
    selector: 'remove-provider-location',
    template: require('./body.html')
  }
)

export class RemoveProviderFromLocationComponent implements AfterViewInit {
  providerChange: ProviderChange;
  parentName: string = 'removeProviderLocation';
  @ViewChildren(NgModel) controls: QueryList<NgModel>;

  constructor (
    private payLoadService: PayLoadService,
    private parentForm: NgForm
  ) {
    if(!payLoadService.removeProviderFromLocation) {
      payLoadService.removeProviderFromLocation = new RemoveProviderFromLocation();
    }
  }

  ngAfterViewInit() {
    this.controls.forEach((control: NgModel) => {
      this.parentForm.addControl(control);
    });
  }
}
