import { ITabView } from './../interfaces/iTabView';
import { Component, Input, ViewChildren, QueryList, Output, EventEmitter } from '@angular/core';

@Component({
  moduleId: module.id,
  selector: '[data-uxd-tabbed-body-cmp]',
  template: `
      <div id="tab-{{$index}}" role="tabpanel" class="tab-pane" >
        <ng-content>
			</ng-content>
      </div>
  `,
    host: {
    '[class.hidden]': '!tabData.isActive',
    '[class.active]': 'tabData.isActive',
    '[class.tab-body]': 'true',
    '[id]': 'tabData.tabId'
  }
})
export class TabbedViewBodyComponent {
  @Input() tabData: ITabView;
  @Output() onClick: EventEmitter<any> = new EventEmitter(false);
}
