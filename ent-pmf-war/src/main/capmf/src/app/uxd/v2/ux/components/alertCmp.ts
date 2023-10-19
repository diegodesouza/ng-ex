import { Component, OnInit, Attribute } from '@angular/core';

//istanbul ignore next
//alertCmp depricated use alertCmpV2
@Component({
  moduleId: module.id,
  selector: '[data-uxd-alert-cmp]',
  template: `<div class="ant-anthem-alert" [ngClass]="{'ant-negative': (alertType === 'negative'), 'ant-positive': (alertType === 'positive')}">
              <div class="media">
                <div class="media-left media-top">
                  <span class="fa " [ngClass]="{'fa-info-circle': (!alertType || alertType === 'information'), 'fa-check-circle': (alertType === 'positive'), 'fa-exclamation-circle': (alertType === 'negative')}"></span>
                </div>
                <div class="media-body media-middle ">
                  <p>
                    <ng-content></ng-content>
                  </p>
                </div>
              </div>
            </div>`
})
export class AlertComponent implements OnInit {

  constructor( @Attribute('data-alert-type') private alertType: string) {
  }

  ngOnInit() {
    // init
  }

}
