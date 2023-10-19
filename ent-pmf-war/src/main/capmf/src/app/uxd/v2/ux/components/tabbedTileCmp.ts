import { Component, Input, ViewChildren, QueryList } from '@angular/core';
import { ITab } from './../interfaces/iTabTiles';

@Component({
  moduleId: module.id,
  selector: '[data-uxd-tabbed-tile-cmp]',
  template: `
          <h5>
            <span [innerHTML]="tabData.label"></span>
          </h5>
          <div>
            <ng-content>
            </ng-content>
          </div>
  `,
  host: {
    '[class.inactive]': '!isActive',
    '[class.active]': 'isActive',
    '[class.tab-body]': 'true',
    '[id]': 'tabData.tabId'
  }
})

/**
 *  Child component class that contains the body for transcluded tiles
 *  @param  { tabData } - Pass an object containing header label and ID for tile
 *  @see    tabbedTilesCmp.ts
 */
export class TabbedTileComponent {
  @Input() tabData: ITab;
  isActive: boolean = false;
}
