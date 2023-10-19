import { Directive, OnInit, Attribute, Component, ElementRef, Renderer } from '@angular/core';

//istanbul ignore next
@Directive({
  selector: 'li[role]'
})
export class ButtonDropdownItemDirective {
  isSelected: boolean = false;

  constructor(private _eleRef: ElementRef,
    private _renderer: Renderer) {

  }

  setActive() {
    this._renderer.setElementClass(this._eleRef.nativeElement, 'active', true);
    this._eleRef.nativeElement.focus();
    this.isSelected = true;
  }

  unsetActive() {
    this._renderer.setElementClass(this._eleRef.nativeElement, 'active', false);
    this.isSelected = false;
  }

  handleClick() {
    let f = $(this._eleRef.nativeElement).find(':focusable');
    if (f.length) {
      f[0].click();
    }
  }

  getText() {
    return this._eleRef.nativeElement.innerText;
  }
}
