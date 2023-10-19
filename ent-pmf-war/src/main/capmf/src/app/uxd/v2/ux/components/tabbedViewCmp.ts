import { ITabViews } from './../interfaces/iTabView';
import { TabbedViewBodyComponent } from './tabbedViewBodyCmp';
import { Component, Input, ContentChildren, QueryList } from '@angular/core';

//istanbul ignore next
@Component({
  moduleId: module.id,
  selector: '[data-uxd-tabbed-view-cmp], [data-uxd-tabbed-view-v2-cmp]',
  template: `
  <div *ngIf="tabs.length !== 0">
    <div class="tabs" >
    <span class="tab-label"></span>
    <ul class="nav nav-pills nav-justified" role="tablist">
      <ng-container *ngFor="let tile of tabs; let first = first; let $index = index">
        <li  role="presentation" [ngClass]="{'active': activeTab === $index}">
          <a (click)="tabClick(tile, $event)" href="javascript:void(0)"
            class="viewButton" (keyup.enter)="tabClick(tile, $event)" (keyup.space)="tabClick(tile, $event)"  [attr.aria-controls]="tile.tabId"
            role="tab" data-toggle="tab" attr.aria-expanded="{true:first} {false:!first}" innerHTML="{{tile.tabData.heading}}" tabindex="0"></a>
        </li>
      </ng-container>
    </ul>
    <div class="tab-content">
      <ng-content>
      </ng-content>
      </div>
  </div>
</div>

<ng-container *ngIf="tabs.length === 0">
    <div class="tabs" >
    <span class="tab-label"></span>
    <ul class="nav nav-pills nav-justified" role="tablist">
      <li  role="presentation" [ngClass]="{'active': activeTab === $index}" *ngFor="let tile of tabbedView.tabs; let first = first; let $index = index">
        <a (click)="tileClick($index, $event)" (keyup.enter)="tileClick($index, $event)" (keyup.space)="tileClick($index, $event)" href="javascript:void(0)"
          class="viewButton"  [attr.aria-controls]="tile.tabId" role="tab" data-toggle="tab"
          attr.aria-expanded="{true:first} {false:!first}" innerHTML="{{tile.heading}}" tabindex="0"></a>
      </li>
    </ul>
    <div class="tab-content">
      <div id="tab-{{$index}}" role="tabpanel" [id]="tile.tabId" class="tab-pane" [ngClass]="{'active': activeTab === $index}" *ngFor="let tile of tabbedView.tabs; trackBy let first = first; let $index = index">
            <p innerHTML="{{tile.content}}"></p>
        </div>
    </div>
  </div>
</ng-container>
`
})
export class TabbedViewComponent {
  @Input() tabbedView: ITabViews;
  @ContentChildren(TabbedViewBodyComponent) tabs: QueryList<TabbedViewBodyComponent>;
  activeTab: number = 0;

  /** Tranclusion click method */
  tabClick(tile: TabbedViewBodyComponent, event?: KeyboardEvent) {
    this.tabs.forEach((t: TabbedViewBodyComponent) => {
      t.tabData.isActive = false;
    });
    this.preventSpacebarDefault(event);

    tile.tabData.isActive = true;
    tile.onClick.emit();
  }

  /** Non-transclusion click method */
  tileClick(index: number, event?: KeyboardEvent) {
    this.activeTab = index;
    this.preventSpacebarDefault(event);
  }

  /** Prevent spacebar default scrolling */
  preventSpacebarDefault(event: KeyboardEvent) {
    if (event.keyCode === 32) {
      event.preventDefault();
    }
  }
}
