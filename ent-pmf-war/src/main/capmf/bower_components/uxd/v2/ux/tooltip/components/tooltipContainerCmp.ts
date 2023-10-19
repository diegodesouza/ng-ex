import { positionService } from './../../services/positionSvc';
import { TooltipOptions } from './../services/tooltipOptions';
import {
  AfterViewInit,
  ChangeDetectorRef,
  Component,
  ElementRef,
  EventEmitter,
  HostListener,
  Inject,
  QueryList,
  TemplateRef,
  ViewChildren,
  ViewContainerRef,
  VERSION
} from '@angular/core';

let tempContent = `<div class="popover" role="tooltip"
              [ngStyle]="{top: top, left: left, display: display}"
              [ngClass]="classMap">
                <div class="popover-close" *ngIf="hasCloseBtn"><a #tooltipCloseBtn aria-label="close tooltip" href="javascript:void(0)" (click)="manualHide($event)" data-autofocus=""><span class="fa fa-remove"></span></a></div>
                <div class="popover-content"
                    *ngIf="htmlContent && !isTemplate"
                    innerHtml="{{htmlContent}}" [tabindex]="autoFocus?0:-1" (keydown)="onContentKeydown($event)">
                </div>
                <div #tooltipContent class="popover-content"
                    *ngIf="htmlContent && isTemplate" [tabindex]="autoFocus?0:-1" (keydown)="onContentKeydown($event)">
                  <template [ngTemplateOutlet]="htmlContent"
                            [ngOutletContext]="{model: context}" >
                  </template>
                </div>
                <div class="popover-content"
                    *ngIf="content" [tabindex]="autoFocus?0:-1" (keydown)="onContentKeydown($event)">
                  {{content}}
                </div>
              </div>`;

if (VERSION.major === '4') {
  tempContent = `<div class="popover" role="tooltip"
              [ngStyle]="{top: top, left: left, display: display}"
              [ngClass]="classMap">
                <div class="popover-close" *ngIf="hasCloseBtn"><a #tooltipCloseBtn aria-label="close tooltip" href="javascript:void(0)" (click)="manualHide($event)" data-autofocus=""><span class="fa fa-remove"></span></a></div>
                <div class="popover-content"
                    *ngIf="htmlContent && !isTemplate"
                    innerHtml="{{htmlContent}}" [tabindex]="autoFocus?0:-1" (keydown)="onContentKeydown($event)">
                </div>
                <div #tooltipContent class="popover-content"
                    *ngIf="htmlContent && isTemplate" [tabindex]="autoFocus?0:-1" (keydown)="onContentKeydown($event)">
                  <ng-template [ngTemplateOutlet]="htmlContent"
                            [ngOutletContext]="{model: context}" >
                  </ng-template>
                </div>
                <div class="popover-content"
                    *ngIf="content" [tabindex]="autoFocus?0:-1" (keydown)="onContentKeydown($event)">
                  {{content}}
                </div>
              </div>`;
}

//istanbul ignore next
@Component({
  moduleId: module.id,
  selector: 'tooltip-container',
  // changeDetection: ChangeDetectionStrategy.OnPush,
  template: tempContent
})
export class TooltipContainerComponent implements AfterViewInit {
  /* tslint:disable */
  manualHideEvent: EventEmitter<any> = new EventEmitter<any>();
  classMap: any;
  top: string = '-1000px';
  left: string = '-1000px';
  display: string = 'block';
  content: string;
  htmlContent: string | TemplateRef<any>;
  protected placement: string;
  protected popupClass: string;
  protected animation: boolean;
  protected isOpen: boolean;
  protected appendToBody: boolean;
  protected hostEl: ElementRef;
  protected context: any;
  protected hasCloseBtn: boolean;
  protected autoFocus: boolean;
  @ViewChildren('tooltipCloseBtn', { read: ViewContainerRef }) closeBtn: QueryList<ViewContainerRef>;
  @ViewChildren('tooltipContent', { read: ViewContainerRef }) mainContent: QueryList<ViewContainerRef>;
  /* tslint:enable */
  protected cdr: ChangeDetectorRef;

  constructor(protected element: ElementRef,
    cdr: ChangeDetectorRef,
    @Inject(TooltipOptions) options: TooltipOptions) {
    this.element = element;
    this.cdr = cdr;
    Object.assign(this, options);
    this.classMap = { 'in': false, 'fade': false };
    this.classMap[options.placement] = true;
    this.classMap['tooltip-' + options.placement] = true;
  }

  ngAfterViewInit(): void {
    let p = positionService
      .positionElements(
      this.hostEl.nativeElement,
      this.element.nativeElement.children[0],
      (this.placement || 'left'), this.appendToBody);
    this.top = p.top + 'px';
    this.left = p.left + 'px';
    this.classMap.in = true;
    if (this.animation) {
      this.classMap.fade = true;
    }

    if (this.popupClass) {
      this.classMap[this.popupClass] = true;
    }

    this.cdr.detectChanges();
    if (this.hasCloseBtn && this.autoFocus) {
      this.closeBtn.first.element.nativeElement.focus();
    }
    else {
      //TODO
      //this.mainContent.first.element.nativeElement.focus();
    }
  }

  get isTemplate(): boolean {
    return this.htmlContent instanceof TemplateRef;
  }

  manualHide(focusHost: boolean = true) {
    this.manualHideEvent.emit();
  }

  onContentKeydown(e: any) {
    let key = e.which || e.keyCode;

    if (key === 9) {
      this.manualHide();
    }
  }

  @HostListener('document:click', ['$event'])
  onDocumentClick(event: any) {
    this.autoCloseOnBlur(event, false);
  }

  @HostListener('document:keydown', ['$event'])
  onDocumentKeydown(event: any) {
    this.autoCloseOnBlur(event, true);
  }

  private autoCloseOnBlur(event: any, focusHost: boolean) {
    if (event.target !== this.hostEl.nativeElement &&
      !this.hostEl.nativeElement.contains(event.target) &&
      !this.element.nativeElement.contains(event.target) &&
      this.classMap.in) {
      this.manualHide(focusHost);
    }
  }
}
