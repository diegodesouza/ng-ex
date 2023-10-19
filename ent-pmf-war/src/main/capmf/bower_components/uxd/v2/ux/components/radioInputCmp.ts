import { IRadioInput } from './../interfaces/iRadioInput';
import { ControlValueAccessor, NG_VALUE_ACCESSOR, NG_VALIDATORS, FormControl } from '@angular/forms';
import { Component, OnChanges, Input, ContentChild, AfterViewInit, ElementRef, Renderer, Output, EventEmitter, forwardRef } from '@angular/core';

//istanbul ignore next
@Component({
  selector: '[data-uxd-radio-input-cmp]',
  template: `<input (focus)="onItemFocus()" (blur)="onItemBlur()" (click)="onItemClick()" [checked]="isSelected" class="prOption" type="radio" [name]="definition.name" [id]="definition.id" [value]="definition.value" [attr.value]="definition.value" />
             <label [class.focus]="isFocused" [id]="'lbl_'+definition.id" [class.active]="isSelected"  class="prLabel" [attr.for]="definition.id" [innerHTML]="definition.label"></label>`,
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => RadioInputComponent),
      multi: true
    }
  ]
})
export class RadioInputComponent implements OnChanges, AfterViewInit, ControlValueAccessor {
  @Input() definition: IRadioInput = {
    id: '',
    label: '',
    name: '',
    value: ''
  };
  isSelected: boolean = false;
  isFocused: boolean = false;

  private _onChangeCallback = (_: any) => {
    //noop
  }

  private _onTouchedCallback = () => {
    //noop
  }

  constructor(private _renderer: Renderer) { }

  ngAfterViewInit() {
    //noop
  }

  ngOnChanges(changes: any): void {
    //noop
  }

  onItemClick() {
    this.isSelected = true;
    this._onChangeCallback(this.definition.value);
  }

  onItemFocus(o: any) {
    this.isFocused = true;
  }

  onItemBlur(o: any) {
    this.isFocused = false;
    this._onTouchedCallback();
  }

  writeValue(v: any) {
    if ((typeof v !== 'undefined') && (typeof this.definition.value !== 'undefined') && (v !== null) && (this.definition.value !== null) && v.toString() === this.definition.value.toString()) {
      this.isSelected = true;
    }
    else {
      this.isSelected = false;
    }
  }

  registerOnChange(fn: any) {
    //todo
    this._onChangeCallback = fn;
  }

  registerOnTouched(fn: any) {
    //todo
    this._onTouchedCallback = fn;
  }
}
