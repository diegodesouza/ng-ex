import { IAlert } from './../interfaces/ialert';
import { Component, OnInit, Attribute, Input } from '@angular/core';

//istanbul ignore next
@Component({
  moduleId: module.id,
  selector: '[data-uxd-alert-cmp-v2]',
  template: `<div class="ant-anthem-alert alert-pattern-v2" [ngClass]="{'ant-negative': (alert.alertType === 'negative'), 'ant-positive': (alert.alertType === 'positive')}">
              <div class="media">
                <div class="media-left media-middle">
                  <span class="fa" [ngClass]="{'fa-check': alert.alertType === 'positive', 'fa-exclamation': alert.alertType === 'negative', 'fa-info': alert.alertType === 'information' }" ></span>
                </div>
                <div class="media-body media-middle ">
                  <p>
                    <span #transclude>
                      <ng-content></ng-content>
                    </span>
                    <span *ngIf="transclude.children.length == 0">{{alert.alertContent}} </span>
                  </p>
                </div>
              </div>
            </div>`
})
export class AlertComponentV2{
  @Input() alert: IAlert;
}
