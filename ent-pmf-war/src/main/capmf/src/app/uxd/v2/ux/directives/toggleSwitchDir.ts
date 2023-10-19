import {
  AfterViewInit,
  Directive,
  ElementRef,
  EventEmitter,
  HostListener,
  Input,
  OnChanges,
  OnInit,
  Output,
  Renderer,
  SimpleChanges
} from '@angular/core';
declare var JQuery: any;

//istanbul ignore next
@Directive({
  selector: '[data-uxd-toggle-switch-dir]'
})
export class ToggleSwitchDirective implements OnInit, AfterViewInit, OnChanges {
  @Input('ngModel') private _modelValue: string;
  @Output() ngModelChange: EventEmitter<any> = new EventEmitter(false);
  private _isChecked: boolean = false;

  constructor(private _element: ElementRef,
    private _renderer: Renderer) {
  }

  ngOnInit() {
    //TODO
  }

  ngAfterViewInit(): any {
    if (!this._element.nativeElement.id) {
      console.error('ToggleSwitchDirective id missing !!!');
    }
    else if (!this._element.nativeElement.name) {
      console.error('ToggleSwitchDirective name missing !!!');
    }

    if (this._element.nativeElement.nextElementSibling.tagName.toLowerCase() !== 'label') {
      console.error('ToggleSwitchDirective label missing !!!');
    }

    this._renderer.setElementClass(this._element.nativeElement, 'ptOption', true);
    this._renderer.setElementClass(this._element.nativeElement.nextElementSibling, 'ptLabel', true);
    if (this._modelValue) {
      this.ngOnChanges({ '_modelValue': { currentValue: this._modelValue } });
    }
  }

  ngOnChanges(changes: SimpleChanges | any) {
    if (typeof changes['_modelValue'] !== 'undefined' &&
      changes['_modelValue'].currentValue &&
      changes['_modelValue'].currentValue.toString() === this._element.nativeElement.defaultValue) {
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
    this._renderer.setElementClass(this._element.nativeElement.nextElementSibling, 'focus', true);
  }

  @HostListener('blur')
  onBlur() {
    this._renderer.setElementClass(this._element.nativeElement.nextElementSibling, 'focus', false);
  }

  @HostListener('click')
  onClick() {
    this._renderer.setElementClass(this._element.nativeElement.nextElementSibling, 'active', true);
    this._renderer.setElementClass(this._element.nativeElement.nextElementSibling, 'focus', true);
  }
}
