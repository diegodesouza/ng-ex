import { ICollapseItem } from '../interfaces/ICollapseItem';
import { Component, OnInit, Input } from '@angular/core';

//istanbul ignore next
@Component({
  moduleId: module.id,
  selector: '[data-uxd-collapse-item-cmp]',
  template: `<div class="collapse-block alert-pattern-v2" [class.ant-block-expanded]="startBlockExpanded">

    <div class="ant-collapse"  [ngClass]="{'blue': (color === 'blue'), 'ant-mobile-collapse': (mobileOnly === true)}">
      <h3 class="ant-collapse-heading" [id]="id" data-uxd-block-toggle-dir>
        <a href="javascript:void(0)" [ngClass]="{'hideIcon': (mobileOnly === true)}" tabindex="-1">
          <span class="fa md"></span>
        </a>
          <span class="collapsible-heading-text" [innerHTML]="headingContent"></span>
      </h3>
      <div class="ant-collapse-body">
          <div>
            <ng-content> </ng-content>
          </div>
          <div *ngIf="bodyContent" [innerHTML]="bodyContent">
          </div>
      </div>
    </div>
  </div>`
})
export class CollapseItemCmp implements OnInit {
  @Input() public collapseItem: any;
  // dataUxdGrouped: boolean;
  private color: string;
  private mobileOnly: boolean;
  private limitOne: boolean;
  @Input() headingContent: string;
  @Input() bodyContent: string;
  @Input() id: string = '';
  @Input('startBlockExpanded') startBlockExpanded: boolean = false;
  ngOnInit() {
    if (!this.collapseItem.data) {
      this.headingContent = this.collapseItem.headingContent;
      this.bodyContent = this.collapseItem.bodyContent;
      this.id = '';
      this.startBlockExpanded = this.collapseItem.startBlockExpanded;
    }
    this.color = this.collapseItem.color;
    this.mobileOnly = this.collapseItem.mobileOnly;
    this.limitOne = this.collapseItem.grouped;
  }
}
