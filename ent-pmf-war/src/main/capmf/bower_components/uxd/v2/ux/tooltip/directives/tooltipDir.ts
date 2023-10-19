import { UxHelper } from './../../services/uxHelper';
import { TooltipContainerComponent } from './../components/tooltipContainerCmp';
import { TooltipOptions } from './../services/tooltipOptions';
import {
  ChangeDetectorRef,
  ComponentRef,
  Directive,
  EventEmitter,
  HostListener,
  Input,
  OnDestroy,
  Output,
  ReflectiveInjector,
  TemplateRef,
  ViewContainerRef
} from '@angular/core';

//istanbul ignore next
/* tslint:disable */
@Directive({
  selector: '[tooltip], [tooltipHtml]',
  exportAs: 'tooltip'
})
/* tslint:enable */
export class TooltipDirective implements OnDestroy {
  /* tslint:disable */
  @Input('tooltip') content: string;
  @Input('tooltipHtml') htmlContent: string | TemplateRef<any>;
  @Input('tooltipPlacement') placement: string = 'top';
  @Input('tooltipIsOpen') isOpen: boolean;
  @Input('tooltipEnable') enable: boolean = true;
  @Input('tooltipAnimation') animation: boolean = true;
  @Input('tooltipAppendToBody') appendToBody: boolean = false;
  @Input('tooltipClass') popupClass: string;
  @Input('tooltipContext') tooltipContext: any;
  @Input('tooltipPopupDelay') delay: number = 0;
  @Input('tooltipHasClose') hasCloseBtn: boolean = true;
  @Input('tooltipAutoFocus') autoFocus: boolean = true;
  @Input('tooltipOpenOnFocus') openOnFocus: boolean = false;
  /* tslint:enable */

  @Output() tooltipStateChanged: EventEmitter<boolean> = new EventEmitter<boolean>();

  viewContainerRef: ViewContainerRef;
  uxHlpr: UxHelper;

  protected changeDetectorRef: ChangeDetectorRef;
  protected visible: boolean = false;
  protected tooltip: ComponentRef<TooltipContainerComponent>;

  protected delayTimeoutId: any;
  private _subscription1: any;

  constructor(viewContainerRef: ViewContainerRef,
    componentsHelper: UxHelper,
    uxHlpr: ChangeDetectorRef) {
    this.viewContainerRef = viewContainerRef;
    this.uxHlpr = componentsHelper;
    this.changeDetectorRef = uxHlpr;
  }

  ngOnDestroy() {
    if (this._subscription1) {
      this._subscription1.unsubscribe();
    }
  }

  @HostListener('focus', ['$event'])
  onFocus(e: any) {
    if (this.openOnFocus) {
      this.show();
    }
  }

  // todo: filter triggers
  // params: event, target
  @HostListener('click')
  show(): void {
    if (this.visible || !this.enable || this.delayTimeoutId) {
      return;
    }
    const showTooltip = () => {
      this.visible = true;
      let options = new TooltipOptions({
        content: this.content,
        htmlContent: this.htmlContent,
        placement: this.placement,
        animation: this.animation,
        appendToBody: this.appendToBody,
        hostEl: this.viewContainerRef.element,
        popupClass: this.popupClass,
        context: this.tooltipContext,
        hasCloseBtn: this.hasCloseBtn,
        autoFocus: this.autoFocus
      });

      if (this.appendToBody) {
        this.tooltip = this.uxHlpr
          .appendNextToRoot(TooltipContainerComponent, TooltipOptions, options);
      } else {
        let binding = ReflectiveInjector.resolve([
          { provide: TooltipOptions, useValue: options }
        ]);
        this.tooltip = this.uxHlpr
          .appendNextToLocation(TooltipContainerComponent, this.viewContainerRef, binding);
      }

      if (this.tooltip) {
        this._subscription1 = this.tooltip.instance.manualHideEvent.subscribe((focusHost: boolean) => {
          this.hide(focusHost);
        });
      }

      this.changeDetectorRef.markForCheck();
      this.triggerStateChanged();
    };

    if (this.delay) {
      this.delayTimeoutId = setTimeout(() => { showTooltip(); }, this.delay);
    } else {
      showTooltip();
    }
  }

  // params event, target
  // @HostListener('focusout')
  // @HostListener('mouseleave')
  hide(focusHost: boolean): void {
    if (this.delayTimeoutId) {
      clearTimeout(this.delayTimeoutId);
      this.delayTimeoutId = undefined;
    }

    if (!this.visible) {
      return;
    }

    this.visible = false;
    this.tooltip.destroy();
    this.triggerStateChanged();

    if (focusHost) {
      this.viewContainerRef.element.nativeElement.focus();
    }
  }

  protected triggerStateChanged(): void {
    if (this.tooltipStateChanged) {
      this.tooltipStateChanged.emit(this.visible);
    }
  }
}
