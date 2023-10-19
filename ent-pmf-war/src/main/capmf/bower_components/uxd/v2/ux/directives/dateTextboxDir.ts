import { NG_VALUE_ACCESSOR, ControlValueAccessor } from '@angular/forms';
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
  EventEmitter,
  forwardRef
} from '@angular/core';
declare var JQuery: any;

//istanbul ignore next
@Directive({
  selector: '[data-is-date]',
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => DateTextboxDirective),
      multi: true
    }
  ]
})
export class DateTextboxDirective implements AfterViewInit, OnDestroy, ControlValueAccessor {
  @Input('maxDate') private _maxDate: string;
  @Input('minDate') private _minDate: string;
  private jElem = $(this._element.nativeElement);

  private _onChangeCallback = (_: any) => {
    //noop
  }

  private _onTouchedCallback = () => {
    //noop
  }

  constructor(private _element: ElementRef,
    protected renderer: Renderer,
    private _datePipe: DatePipe) {
  }

  writeValue(value: any) {
    this.ngOnChanges({ '_modelValue': { currentValue: value } });
  }

  registerOnChange(fn: any) {
    //todo
    this._onChangeCallback = fn;
  }

  registerOnTouched(fn: any) {
    //todo
    this._onTouchedCallback = fn;
  }

  ngAfterViewInit(): any {
    this.setupDatePicker();
  }

  ngOnChanges(changes: SimpleChanges | any) {
    if (typeof changes['_modelValue'] !== 'undefined') {
        this.renderer.setElementClass(this._element.nativeElement, 'hasValue', (changes['_modelValue'].currentValue ? true : false));
      this._element.nativeElement.value = changes['_modelValue'].currentValue || '';
    }
  }

  ngOnDestroy() {
    if (this.jElem) {
      this.jElem.datepicker('destroy');
    }
  }

  @HostListener('keyup')
  onChange() {
    this._onChangeCallback(this._element.nativeElement.value);
  }

  private setupDatePicker() {
    this.jElem.datepicker({
      showOn: 'button',
      buttonText: '<span class="fa fa-calendar"></span>',
      showOtherMonths: true,
      beforeShow: (input: Element, inst: any): any => {
        if (this._maxDate) {
          $(this.jElem).datepicker('option', 'maxDate', this._maxDate);
        }

        if (this._minDate) {
          $(this.jElem).datepicker('option', 'minDate', this._minDate);
        }

        this.jElem.next().find('.fa').removeClass('fa-calendar').addClass('fa-remove');
        return;
      },
      onClose: (e: any) => {
        if (this._element.nativeElement.value !== '') {
          this.renderer.setElementClass(this._element.nativeElement, 'hasValue', true);
        } else {
          this.renderer.setElementClass(this._element.nativeElement, 'hasValue', false);
        }

        this._onTouchedCallback();
        this._onChangeCallback(this._element.nativeElement.value);

        this._element.nativeElement.focus();
        this.jElem.next().find('.fa').addClass('fa-calendar').removeClass('fa-remove');
      }
    });

    $(window).resize(() => {
      this.jElem.datepicker('hide');
    });

    if (this._maxDate) {
      this.jElem.datepicker('option', 'maxDate', this._datePipe.transform(this._maxDate, 'M/d/yyyy') || this._maxDate || '');
    }

    if (this._minDate) {
      this.jElem.datepicker('option', 'minDate', this._datePipe.transform(this._minDate, 'M/d/yyyy') || this._minDate || '');
    }

    this.jElem.next().prop('tabindex', '-1');
  }
}
