import { modalClassNames } from '../values/modalOptions';
import { Component, ElementRef, Renderer } from '@angular/core';

export class ModalBackdropOptions {
  animate: boolean = true;

  constructor(options: ModalBackdropOptions) {
    Object.assign(this, options);
  }
}

//istanbul ignore next
@Component({
  moduleId: module.id,
  selector: '[data-uxd-modal-backdrop]',
  template: '',
  host: { 'class': modalClassNames.BACKDROP }
})
export class ModalBackdropComponent {
  get isAnimated(): boolean {
    return this._isAnimated;
  }

  set isAnimated(value: boolean) {
    this._isAnimated = value;
    this.renderer.setElementClass(this.element.nativeElement, `${modalClassNames.FADE}`, value);
  }

  get isShown(): boolean {
    return this._isShown;
  }

  set isShown(value: boolean) {
    this._isShown = value;
    this.renderer.setElementClass(this.element.nativeElement, `${modalClassNames.IN}`, value);
    this.renderer.setElementClass(this.element.nativeElement, `${modalClassNames.ACTIVE}`, value);
  }

  element: ElementRef;
  renderer: Renderer;

  protected _isAnimated: boolean;
  protected _isShown: boolean = false;

  constructor(options: ModalBackdropOptions, element: ElementRef, renderer: Renderer) {
    this.element = element;
    this.renderer = renderer;
    this.isAnimated = options.animate !== false;
  }
}
