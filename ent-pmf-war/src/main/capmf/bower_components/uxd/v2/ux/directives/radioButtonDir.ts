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
declare var window: any;

//istanbul ignore next
@Directive({
  selector: '[data-uxd-radio-dir]'
})
export class RadioButtonDirective implements OnInit, AfterViewInit, OnChanges {
  private ngOnChangeRun = false;
  @Input('ngModel') private _modelValue: string;
  @Output() ngModelChange: EventEmitter<any> = new EventEmitter(false);
  @Input('optional') isOptional: boolean;
  @Output('onChange') onChange: EventEmitter<any> = new EventEmitter(false);

  constructor(private _element: ElementRef,
    private _renderer: Renderer) {
  }

  ngOnInit() {
    //TODO:
  }

  ngAfterViewInit(): any {
    if (!this._element.nativeElement.id) {
      console.error('RadiobuttonDirective id missing !!!');
    }
    else if (!this._element.nativeElement.name) {
      console.error('RadiobuttonDirective name missing !!!');
    }

    if (this._element.nativeElement.nextElementSibling.tagName.toLowerCase() !== 'label') {
      console.error('RadiobuttonDirective label missing !!!');
    }

    this._renderer.setElementClass(this._element.nativeElement, 'prOption', true);
    this._renderer.setElementClass(this._element.nativeElement.nextElementSibling, 'prLabel', true);
    if (this._modelValue) {
      this.ngOnChanges({ '_modelValue': { currentValue: this._modelValue } }, true);
    this.ngOnChangeRun = false;
    }
  }

  ngOnChanges(changes: SimpleChanges | any, setCss: boolean = false) {
    if (!this._element.nativeElement.nextElementSibling) {
      return;
    }

    this.ngOnChangeRun = true;
    if (typeof changes['_modelValue'] !== 'undefined' &&
      changes['_modelValue'].currentValue &&
      changes['_modelValue'].currentValue.toString() === this._element.nativeElement.defaultValue) {
      if (this._element.nativeElement.nextElementSibling) {
        this._renderer.setElementClass(this._element.nativeElement.nextElementSibling, 'active', true);
      }
    }
    else {
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
    if ((typeof this.isOptional !== 'undefined') && this._element.nativeElement.nextElementSibling.classList.contains('active') && this.isOptional && ((!window.isSafari && !window.isIE) || !this.ngOnChangeRun)) {
      this.ngModelChange.emit('');
    }
    else {
      this._renderer.setElementClass(this._element.nativeElement.nextElementSibling, 'active', true);
      this.ngModelChange.emit(this._element.nativeElement.defaultValue);
    }

    this.ngOnChangeRun = false;
    this._renderer.setElementClass(this._element.nativeElement.nextElementSibling, 'focus', true);
    this.onChange.emit();
  }
}
