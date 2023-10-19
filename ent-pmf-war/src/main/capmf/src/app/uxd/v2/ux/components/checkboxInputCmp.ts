import { ICheckboxInput } from './../interfaces/iCheckboxInput';
import { ControlValueAccessor, NG_VALUE_ACCESSOR, NG_VALIDATORS, FormControl } from '@angular/forms';
import { Component, OnChanges, Input, ContentChild, AfterViewInit, ElementRef, Renderer, Output, EventEmitter, forwardRef } from '@angular/core';

//istanbul ignore next
@Component({
  selector: '[data-uxd-checkbox-input-cmp]',
  template: `<input (focus)="onItemFocus()" (blur)="onItemBlur()" (click)="onItemClick()" [checked]="isSelected" class="pcOption" type="checkbox" [name]="definition.name" [id]="definition.id" />
             <label [class.focus]="isFocused" [id]="'lbl_'+definition.id" [class.active]="isSelected"  class="pcLabel" [attr.for]="definition.id" [innerHTML]="definition.label"></label>`,
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => CheckboxInputComponent),
      multi: true
    }
  ]
})
export class CheckboxInputComponent implements OnChanges, AfterViewInit, ControlValueAccessor {
  @Input() definition: ICheckboxInput = {
    id: '',
    label: '',
    name: '',
    trueValue: true,
    falseValue: false
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
    this.isSelected = !this.isSelected;
    this._onChangeCallback(this.isSelected ? this.definition.trueValue : this.definition.falseValue);
  }

  onItemFocus(o: any) {
    this.isFocused = true;
  }

  onItemBlur(o: any) {
    this.isFocused = false;
    this._onTouchedCallback();
  }

  writeValue(v: any) {
    if ((typeof v !== 'undefined') &&
      (typeof this.definition.trueValue !== 'undefined') &&
      (typeof this.definition.falseValue !== 'undefined') &&
      (v !== null) &&
      (this.definition.trueValue !== null) && (this.definition.falseValue !== null) && v.toString() === this.definition.trueValue.toString()) {
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
