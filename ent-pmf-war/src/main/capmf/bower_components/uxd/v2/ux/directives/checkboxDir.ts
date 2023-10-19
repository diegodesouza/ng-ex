import {
  AfterViewInit,
  Directive,
  ElementRef,
  HostListener,
  Input,
  OnChanges,
  OnInit,
  Renderer,
  SimpleChanges
} from '@angular/core';
declare var JQuery: any;

//istanbul ignore next
@Directive({
  selector: '[data-uxd-checkbox-dir]'
})
export class CheckboxDirective implements OnInit, AfterViewInit, OnChanges {
  @Input('ngModel') private _modelValue: string;
  private _isChecked: boolean = false;

  constructor(private _element: ElementRef,
    private _renderer: Renderer) {
  }

  ngOnInit() {
    //TODO
  }

  ngAfterViewInit(): any {
    if (!this._element.nativeElement.id) {
      console.error('CheckboxDirective id missing !!!');
    }
    else if (!this._element.nativeElement.name) {
      console.error('CheckboxDirective name missing !!!');
    }

    if (this._element.nativeElement.nextElementSibling.tagName.toLowerCase() !== 'label') {
      console.error('CheckboxDirective label missing !!!');
    }

    this._renderer.setElementClass(this._element.nativeElement, 'pcOption', true);
    this._renderer.setElementClass(this._element.nativeElement.nextElementSibling, 'pcLabel', true);
    if (this._modelValue) {
      this.ngOnChanges({ '_modelValue': { currentValue: this._modelValue } });
    }
  }

  ngOnChanges(changes: SimpleChanges | any) {
    if (typeof changes['_modelValue'] !== 'undefined' &&
      changes['_modelValue'].currentValue) {
      this._isChecked = true;
      if (this._element.nativeElement.nextElementSibling) {
        this._renderer.setElementClass(this._element.nativeElement.nextElementSibling, 'active', true);
      }
    }
    else {
      this._isChecked = false;
      if (this._element.nativeElement.nextElementSibling) {
        this._renderer.setElementClass(this._element.nativeElement.nextElementSibling, 'active', false);
      }
    }
  }

  @HostListener('focus')
  onFocus() {
    this._renderer.setElementClass(this._element.nativeElement, 'active', true);
    this._renderer.setElementClass(this._element.nativeElement.nextElementSibling, 'focus', true);
  }

  @HostListener('blur')
  onBlur() {
    this._renderer.setElementClass(this._element.nativeElement, 'active', false);
    this._renderer.setElementClass(this._element.nativeElement.nextElementSibling, 'focus', false);
  }

  @HostListener('click')
  onClick() {
    this._renderer.setElementClass(this._element.nativeElement, 'active', true);
    this._renderer.setElementClass(this._element.nativeElement.nextElementSibling, 'focus', true);
  }
}
