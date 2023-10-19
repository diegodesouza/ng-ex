import { AlternateToggleBodyComponent } from './altToggleBodyCmp';
import { Component, ContentChild, AfterContentInit, QueryList, ContentChildren, ViewChild, forwardRef, AfterContentChecked, Input, AfterViewInit, Renderer } from '@angular/core';

@Component({
  moduleId: module.id,
  selector: '[data-uxd-alt-toggle-cmp]',
  host: {
    '[class.clearfix]': 'true',
    '[class.alt-toggle-cmp]': 'true'
  },
  template: `
    <!-- Transcluded content goes in here, including header + body -->
    <div class="alt-toggle-header" [id]="'alt-toggle-header-container-'+toggleNumber">
      <ng-content></ng-content>
    </div>
    <!-- End transclusion -->

    <div class="alt-toggle-icon" role="button" tabindex="0" [attr.aria-expanded]="_expanded" (click) ="_expandClick()"
    (keyup.enter) ="_expandClick()" (keyup.space) ="_expandClick()" [attr.aria-controls]="'alt-toggle-body-container-'+ toggleNumber"
    [attr.name]="buttonName">
      <span class="fa md" [ngClass] ="_expanded ? 'alt-toggle-minus' : 'alt-toggle-plus'"></span>
      <span class="sr-only" [innerText]="'Select this button to toggle item ' + toggleNumber"></span>
    </div>
  `
})

/**
 *  Toggle component with full transclusion capabilities
 */
export class AlternateToggleComponent implements AfterContentChecked, AfterViewInit {
  @Input() toggleNumber?: number; // has to come in from the view
  @Input() buttonName: string;
  @ContentChildren(AlternateToggleBodyComponent) toggleBodyList: QueryList<AlternateToggleBodyComponent>;
  private _firstToggleBody: AlternateToggleBodyComponent;
  private _expanded: boolean; // this boolean is controlled by child component class

  constructor(private _renderer: Renderer) {
  }

  /**
   *  After initialization, if input value is not passed it, automatically
   *  assigns its value to 1. Primarily used for accessibility
   */
  ngAfterViewInit() {
    this._expanded = this._firstToggleBody.isExpanded;
    if (this.toggleNumber === undefined || this.toggleNumber === null) {
      this.toggleNumber = 1;
    }
  }

  /**
   *  After content has been confirmed in the view, set the expanded or collapse
   *  status. If the length of AlternateToggleBodyComponent is not 0, assign
   *  variable
   */
  ngAfterContentChecked() {
    if (this.toggleBodyList.toArray().length > 0) {
      this._firstToggleBody = this.toggleBodyList.toArray()[0];
    }
    this._firstToggleBody.toggleNumber = this.toggleNumber;
  }

  /**
   *  On user click or enter key, toggle the boolean state of the body between
   *  show and hide
   */
  private _expandClick(): void {
    this._firstToggleBody.isExpanded = !this._firstToggleBody.isExpanded;
    this._expanded = this._firstToggleBody.isExpanded;
    if (this._expanded) {
      this._renderer.setElementStyle(this._firstToggleBody.toggleBodyRef.nativeElement, 'max-height', this._firstToggleBody.toggleBodyRef.nativeElement.scrollHeight+'px');
    } else {
      this._renderer.setElementStyle(this._firstToggleBody.toggleBodyRef.nativeElement, 'max-height', null);
    }
  }
}
