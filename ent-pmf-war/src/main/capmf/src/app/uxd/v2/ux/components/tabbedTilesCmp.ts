import { TabbedTileComponent } from './tabbedTileCmp';
import { Component, Input, ViewChildren, QueryList, ContentChildren, AfterContentInit, AfterContentChecked } from '@angular/core';
import { ITabTiles } from './../interfaces/iTabTiles';

@Component({
  moduleId: module.id,
  selector: '[data-uxd-tabbed-tiles-cmp]',
  host: { '[class.tab-wrapper]': 'true' },
  template: `
    <!-- If transcluding content (Data is put in the view manually) -->
    <div *ngIf="tiles.length !== 0">
      <ul role="tablist" class="clearfix">
        <ng-container *ngFor="let tile of tiles; let first = first; let $index = index">
          <li class="tab-tile" [id]="tilesContent.id + $index" role="tab" (click)="tabClick($index)"
          (keyup.enter)="tabClick($index)" (keyup.space)="tabClick($index)" tabindex="0"
          [attr.aria-controls]="tile.tabData.tabId" [class.active]="tile.isActive">
            <h6 [attr.data-analytics]="tile.tabData.analyticsText">
              <span class="tab-tile-label" [innerText]="tile.tabData.label" [attr.data-analytics]="tile.tabData.analyticsText"></span>
              <span class="fa" [ngClass]="tile.tabData.icon" [attr.aria-label]="tile.tabData.label" [attr.data-analytics]="tile.tabData.analyticsText"></span>
            </h6>
          </li>
        </ng-container>
      </ul>
      <!-- Transclusion goes in here -->
      <ng-content>
      </ng-content>
      <!-- End transclusion -->
    </div>

    <!-- Non transclusion (Defaults to data from JSON object) -->
    <ng-container *ngIf="tiles.length === 0">
      <ul role="tablist" class="clearfix">
        <li class="tab-tile" [id]="tilesContent.id + $index" role="tab" *ngFor="let tile of tilesContent.tabs; let first = first; let $index = index"
        (click)="tabClick($index)" (keyup.enter)="tabClick($index)"
        (keyup.space)="tabClick($index)" tabindex="0"
        [attr.aria-controls]="'tabTile'+$index" [class.active]="activeTab === $index">
          <h6 [attr.data-analytics]="tile.analyticsText">
            <span class="tab-tile-label" [innerText]="tile.label" [attr.data-analytics]="tile.analyticsText"></span>
            <span class="fa" [ngClass]="tile.icon" [attr.aria-label]="tile.label" [attr.data-analytics]="tile.analyticsText"></span>
          </h6>
        </li>
      </ul>
      <div class="tab-body" *ngFor="let tile of tilesContent.tabs; let first = first; let $index = index"
      (click)="tabClick($index)" [id]="'tabTile'+$index" [class.active]="activeTab === $index" [class.inactive]="activeTab != $index">
        <h5 [innerHTML]="tile.label"></h5>
        <div [innerHTML]="tile.content"></div>
      </div>
    </ng-container>
  `
})

/**
 *  Colored tab tiles component
 *  @param { tilesContent } ITabTiles - Array of tile data
 *  @param { tiles } QueryList - containers list of child Tile components to access/reference
 *  @param { activeTab } - variable to hold the currently selected tab in the view
 *  @see ITabTiles, TabbedTileComponent
 */
export class TabbedTilesComponent implements AfterContentChecked, AfterContentInit {
  @Input() tilesContent: ITabTiles;
  @ContentChildren(TabbedTileComponent) tiles: QueryList<TabbedTileComponent>;
  activeTab: number = 0;

  /**
   *  After content is initialized, set first tile to active on page load
   */
  ngAfterContentInit() {
    if (this.tiles.length > 0) {
      this.resetActiveTile(0);
    }
  }

  /**
   *  After the content loads, set first tile to active if no current
   *  child tile is set to active; handles reload from dropdown
   */
  ngAfterContentChecked() {
    let activeCount = 0;
    if (this.tiles.length > 0) {
      this.tiles.forEach((t: TabbedTileComponent, index: number) => {
        if (t.isActive === true) {
          activeCount = index;
        }
      });
      this.resetActiveTile(activeCount);
    }
  }

  /**
   *  Sets the isActive boolean of passed in index to true
   *  @param {index} number - index to search for inside QueryList array
   */
  resetActiveTile(index: number) {
      this.tiles.toArray()[index].isActive = true;
  }

  /**
   *  On tile click, set the activeTab to passed in Index
   *  @param {index} number - index passed in from user click
   */
  tabClick(index: number) {
    if (this.tiles.length > 0) {
      this.tiles.forEach((t: TabbedTileComponent) => {
        t.isActive = false;
      });
      this.tiles.toArray()[index].isActive = true;
    }
    this.activeTab = index;
  }
}
