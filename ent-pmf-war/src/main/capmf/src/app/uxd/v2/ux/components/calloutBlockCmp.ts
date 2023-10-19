import { ICalloutBlock } from './../interfaces/iCalloutBlock';
import { Component, OnInit, Input } from '@angular/core';

//istanbul ignore next
@Component({
  moduleId: module.id,
  selector: '[data-uxd-callout-block-cmp]',
  template: `
    <div class="ant-content-callout clearfix" [ngClass]="calloutContent.class">
      <div *ngFor="let callout of calloutContent.item">
        <div class="ant-callout-img-wrapper">
          <img [src]="callout.img" *ngIf="callout.img" />
          <span class="fa" [ngClass]="callout.icon" *ngIf="callout.icon"></span>
        </div>
        <div class="ant-callout-content">
          <h3>{{ callout.title }}</h3>
          <div class="ant-callout-body" [innerHTML]="callout.body"></div>
          <a [href]="callout.linkUrl">{{ callout.linkText }}</a>
        </div>
      </div>
    </div>
  `
})
export class CalloutBlockComponent {
  @Input() calloutContent: ICalloutBlock;
}
