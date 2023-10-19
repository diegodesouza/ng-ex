import { IAlphabetCarousel } from './../interfaces/iAlphabetCarousel';
import { Component, Input, ContentChildren, QueryList, EventEmitter, Output, AfterViewInit } from '@angular/core';
import { AlphabetCarouselBodyComponent } from './alphabetCarouselBodyCmp';
import { ICarouselItem } from './../interfaces/iCarouselItem';

@Component({
  moduleId: module.id,
  selector: '[data-uxd-alphabet-carousel-cmp]',
  template: `
      <div class="ant-alphabet-carousel-container" [id]="alphabetItems.idPrefix + '-container'">
        <div class="ant-fringe-mask"></div>
        <div class="ant-alphabet-carousel">
          <ul role="tablist" [id]="alphabetItems.idPrefix + '-tablist'">
            <ng-container *ngFor="let alphaRange of alphabetItems.alphabetRanges; let i = index">
              <li tabindex="0" role="tab" [class.active]="activeAlphaTab === i"
              [attr.aria-controls] ="buildAriaControls(i)"
              [id]="alphabetItems.idPrefix + '-item-' + i"
              (click)="alphabetRangeClick(i, alphaRange)"
              (keyup.enter)="alphabetRangeClick(i, alphaRange)">
                <span class="sr-only" [innerText]="'Filter by letter range'"></span>
                <a [innerText]="alphaRange.label" [id]="alphabetItems.idPrefix + '-range-link-' + i"></a>
              </li>
            </ng-container>
          </ul>
        </div>
      </div>
      <div *ngIf="isTranscluding" class="ant-alphabet-carousel-body" [id]="alphabetItems.idPrefix + '-body'">
        <ng-content></ng-content>
      </div>
  `,
})

/**
 *  Alphabet Carousel/Ribbon/Tabs global component. Used to index
 *  various content from A - Z; i.e. Glossary or Find Benefits
 *
 *  @param  { alphabetItems } IAlphabetCarousel - Input param to receive alphabet data interface
 *  @param  { onAlphabetRangeClick } EventEmitter<ICarouselItem> - Emit event for parent container to receive
 *  @param  { alphabetContentChildren } QueryList - Scans for transclusion and setting active/inactive classes
 *  @param  { activeAlphaTab } Number - Index to set active alphabet range tab
 *  @param  { isTranscluding } Boolean - Holds boolean to check for transclusion or emittence
 *  @see    AlphabetCarouselBodyComponent, IAlphabetCarousel, ICarouselItem
 */
export class AlphabetCarouselComponent implements AfterViewInit {
  @Input() alphabetItems: IAlphabetCarousel;
  @Output() onAlphabetRangeClick: EventEmitter<ICarouselItem> = new EventEmitter<ICarouselItem>();
  @ContentChildren(AlphabetCarouselBodyComponent) alphabetContentChildren: QueryList<AlphabetCarouselBodyComponent>;
  private activeAlphaTab: number;
  private isTranscluding: boolean;

  /**
   *  Sets the first content child to be active, if transcluding. If no idPrefix is given in the
   *  data, a default ID will be declared
   */
  ngAfterViewInit() {
    if (typeof (this.alphabetItems.idPrefix) === 'undefined' || this.alphabetItems.idPrefix === '') {
      this.alphabetItems.idPrefix = 'tcp-alphabet-carousel';
    }
    this.isTranscluding = this.alphabetContentChildren.length > 0;
    if (this.isTranscluding) {
      this.activeAlphaTab = 0;
      this.alphabetContentChildren.toArray()[this.activeAlphaTab].setIsActive(true);
      this.alphabetContentChildren.forEach((alphaBody: AlphabetCarouselBodyComponent, $index) => {
        alphaBody.buildIdString(this.alphabetItems.idPrefix, $index);
      });
    }
  }

  /**
   *  On alphabet range click, assign active index. Emit event; parent listener optional.
   *  If transcluding, will set the corresponding content index to be active
   *  @param  { index } Number - Element index
   *  @param  { alphaRange } ICarouselItem - Carousel item object for event emitter
   *  @see    ICarouselItem
   */
  alphabetRangeClick(index: number, alphaRange: ICarouselItem) {
    this.activeAlphaTab = index;
    if (this.alphabetContentChildren.length > 0) {
      this.alphabetContentChildren.forEach((alphaBody: AlphabetCarouselBodyComponent) => {
        alphaBody.setIsActive(false);
      });
      this.alphabetContentChildren.toArray()[index].setIsActive(true);
    }

    this.emitRangeClickEvent(alphaRange);
  }

  /**
   *  Emits an event to the parent container of this component
   *  @param  { alphaRange } ICarouselItem - Carousel item object, contains strings
   *  @see    ICarouselItem
   */
  emitRangeClickEvent(alphaRange: ICarouselItem) {
    this.onAlphabetRangeClick.emit(alphaRange);
  }

  /**
   *  Builds ID string for aria-controls attribute if transcluding to connect
   *  tab and body for screenreaders
   *
   *  @param { index } Number - Index tagged on at the end of ID
   */
  buildAriaControls(index: number) {
    let ariaControlsString;
    if (this.isTranscluding) {
      ariaControlsString = this.alphabetItems.idPrefix + '-body-' + index;
    } else {
      ariaControlsString = this.alphabetItems.idPrefix + '-body';
    }
    return ariaControlsString;
  }

  /**
   * Reset any active tabs to default state
   */

  resetActiveTab() {
    this.activeAlphaTab = -1;
    if (this.alphabetContentChildren.length > 0) {
      this.alphabetContentChildren.forEach((alphaBody: AlphabetCarouselBodyComponent) => {
        alphaBody.setIsActive(false);
      });
    }
  }
}
