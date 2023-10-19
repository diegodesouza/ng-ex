import { TooltipDef } from './../interfaces/iTooltip';
import { UxHelper } from './../services/uxHelper';
import { Directive, ViewContainerRef, ChangeDetectorRef, Input, OnInit } from '@angular/core';
import { TooltipDirective } from './../tooltip/directives/tooltipDir';

@Directive({
  selector: '[data-uxd-tooltip-dir]',
  exportAs: 'tooltip'
})
/* tslint:enable */
export class UxdTooltipDirective extends TooltipDirective implements OnInit {
  @Input() definition: TooltipDef = new TooltipDef();

  constructor(viewContainerRef: ViewContainerRef,
    componentsHelper: UxHelper,
    uxHlpr: ChangeDetectorRef) {
    super(viewContainerRef, componentsHelper, uxHlpr);
  }

  ngOnInit() {
    if (this.definition.content) {
      this.content = this.definition.content;
    }

    if (this.definition.htmlContent) {
      this.htmlContent = this.definition.htmlContent;
    }

    if (this.definition.placement) {
      this.placement = this.definition.placement;
    }

    if (this.definition.isOpen) {
      this.isOpen = this.definition.isOpen;
    }

    if (this.definition.popupClass) {
      this.popupClass = this.definition.popupClass;
    }

    if (this.definition.tooltipContext) {
      this.tooltipContext = this.definition.tooltipContext;
    }

    if (this.definition.delay) {
      this.delay = this.definition.delay;
    }

    if (typeof this.definition.hasCloseBtn !== 'undefined') {
      this.hasCloseBtn = this.definition.hasCloseBtn;
    }

    if (this.definition.autoFocus === false) {
      this.autoFocus = this.definition.autoFocus;
    }

    if (this.definition.openOnFocus === true) {
      this.openOnFocus = this.definition.openOnFocus;
    }
  }

}
