import { ScrollingTabbedViewBodyComponent } from './scrollingTabbedViewBody';
import { IScrollingTabViews } from './../interfaces/iScrollingTabView';
import { Component, Input, ContentChildren, QueryList, ElementRef, ViewChild, AfterViewInit } from '@angular/core';

//istanbul ignore next
@Component({
  moduleId: module.id,
  selector: '[data-uxd-scrolling-tabbed-view-cmp]',
  template: `
  <div *ngIf="tabs.length !== 0">
<div class="tabs scrollingTabbedViewComponent" >
    <span class="tab-label"></span>
    <div class="tab-header">
    <a href="javascript:void(0)" (click)="arrowClick('left')" [class.disabled]="maxLeft === true">
      <span class="fa fa-chevron-left"></span>
    </a>
    <div class="tab-wrapper">
    <ul class="nav nav-pills nav-justified scoll-tab-ul" role="tablist">
      <ng-container >
        <li *ngFor="let tile of scrollingTabbedView.tabs; let i = index" tabindex="0" role="presentation" tabindex="0" (click)="tabClick(i)" (keyup.enter)="tabClick(i)" (keyup.space)="tabClick(i)"
          [class.active]="activeTab === i" [attr.aria-controls]="tile.tabId + i" [id]="tile.tabId+i" [style.left.%]="movePosition">
          <a class="viewButton" [attr.aria-controls]="tile.tabId"
            role="tab" data-toggle="tab" attr.aria-expanded="{true:first} {false:!first}" >
            <span class="scrolling-tab-title" innerHTML="{{tile.heading}}"></span>
            <span class="scrolling-tab-description" innerHTML="{{tile.subHeading}}"></span>
            </a>
        </li>
      </ng-container>
    </ul>
    </div>
    <a href="javascript:void(0)" (click)="arrowClick('right')" [class.disabled]="maxRight === true">
      <span class="fa fa-chevron-right"></span>
    </a>
    </div>
    <div class="tab-content">
      <ng-content>
      </ng-content>
      </div>
  </div>
</div>
`
})
export class ScrollingTabbedViewComponent implements AfterViewInit {
  @Input() scrollingTabbedView: IScrollingTabViews;
  @ContentChildren(ScrollingTabbedViewBodyComponent) tabs: QueryList<ScrollingTabbedViewBodyComponent>;
  @ViewChild('ulWrapper') ulWrapper: ElementRef;
  activeTab: number = 0;
  cssPosition: number = 0;
  movePosition: number = 0;
  maxLeft: boolean;
  maxRight: boolean;
//*ngFor="let tile of scrollingTabbedView.tabs;
  ngAfterViewInit(){
    if(this.tabs.length > 0){
      this.tabs.toArray()[this.activeTab].isActive = true;
    }
    this.maxLeft = true;
    this.maxRight = false;
  }

  /** Tranclusion click method */
  tabClick(index: number) {
    this.tabs.forEach((t: ScrollingTabbedViewBodyComponent) => {
      t.isActive = false;
    });
    this.activeTab = index;
    this.tabs.toArray()[this.activeTab].isActive = true;
  }

  /** Prevent spacebar default scrolling */
  preventSpacebarDefault(event: KeyboardEvent) {
    if (event.keyCode === 32) {
      event.preventDefault();
    }
  }

  arrowClick( direction: string){
    let index: any;
    let maxNegativeLength = 4 - this.scrollingTabbedView.tabs.length;
    if(direction === 'right'){
      this.cssPosition--;
      index = this.cssPosition;
      this.maxLeft = false;
      if(index <= maxNegativeLength){
        this.maxRight = true;
        index = maxNegativeLength;
        this.cssPosition = maxNegativeLength;
      }
    } else {
      this.cssPosition++;
      index = this.cssPosition;
      this.maxRight = false;
      if(index >= 0){
        this.maxLeft = true;
        index = 0;
        this.cssPosition = 0;
      }
    }
    this.applyCssPercentage(index);
  }
  applyCssPercentage(index: number){
    this.movePosition = index * 25;
  }
}
