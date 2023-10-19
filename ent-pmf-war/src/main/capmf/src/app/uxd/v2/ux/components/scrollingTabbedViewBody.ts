import { ITabView } from './../interfaces/iTabView';
import { Component, Input, ViewChildren, QueryList, Output, EventEmitter } from '@angular/core';

@Component({
  moduleId: module.id,
  selector: '[data-uxd-scrolling-tabbed-view-body-cmp]',
  template: `
      <div id="tab-{{$index}}" role="tabpanel" class="tab-pane" >
        <ng-content>
			</ng-content>
      </div>
  `,
    host: {
    '[class.active]': 'isActive',
    '[class.hidden]': '!isActive',
    '[class.tab-body]': 'true',
    '[id]': 'tabData.tabId'
  }
})
export class ScrollingTabbedViewBodyComponent {
  @Input() tabData: ITabView;
  @Output() onClick: EventEmitter<any> = new EventEmitter<any>(false);
  isActive: boolean;
}
