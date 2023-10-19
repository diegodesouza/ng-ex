import { DatePipe } from '@angular/common';
import {
  AfterViewInit,
  Attribute,
  Directive,
  ElementRef,
  HostListener,
  Input,
  OnChanges,
  OnDestroy,
  Renderer,
  SimpleChanges,
  Output,
  EventEmitter
} from '@angular/core';
declare var JQuery: any;

//istanbul ignore next
@Directive({
  selector: '[data-uxd-text-dir]'
})
export class TextboxDirective {

  constructor( private _element: ElementRef,
    protected renderer: Renderer) {
  }

  @HostListener('focus')
  onFocus() {
    this.renderer.setElementClass(this._element.nativeElement, 'focus', true);
  }

  @HostListener('blur')
  onBlur() {
    this.renderer.setElementClass(this._element.nativeElement, 'focus', false);
  }
}
