import { IRadioInput } from './../interfaces/iRadioInput';
import { ControlValueAccessor, NG_VALUE_ACCESSOR, NG_VALIDATORS, FormControl } from '@angular/forms';
import { Component, OnChanges, Input, ContentChild, AfterViewInit, ElementRef, Renderer, Output, EventEmitter, forwardRef } from '@angular/core';

//istanbul ignore next
@Component({
  selector: '[data-uxd-radio-input-group-cmp]',
  template: `<fieldset class="pfRadio">
                <legend [id]="'lgnd_'+radioGroup.id" class="" [class.ant-span-label]="radioGroup.showLegendAsLbl" [class.sr-only]="radioGroup.legendSrOnly" [innerHTML]="radioGroup.label"></legend>
                  <div [class.alignVertical]="radioGroup.alignVertical">
                  <ng-container *ngFor="let o of radioGroup.options; let pIdx = index;">
                    <div>
                      <input (focus)="onItemFocus(o)" (blur)="onItemBlur(o)" (click)="onItemClick(o)" [checked]="o.isSelected" class="prOption" type="radio" [name]="radioGroup.name" [id]="radioGroup.id+pIdx" [value]="o.value" [attr.value]="o.value" />
                      <label [class.focus]="o.isFocused" [id]="'lbl_'+radioGroup.id+pIdx" [class.active]="o.isSelected"  class="prLabel" [attr.for]="radioGroup.id+pIdx" [innerHTML]="o.label"></label>
                    </div>
                  </ng-container>
                  </div>
                    <input class="sr-only" *ngIf="radioGroup.isOptional" (focus)="onOptionalFocus()" [checked]="optionalSelected" class="prOption" type="radio" [name]="radioGroup.name" [id]="radioGroup.id+'_opt'" [value]="" [attr.value]="" />
             </fieldset>`,
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => RadioInputGroupComponent),
      multi: true
    }
  ]
})
export class RadioInputGroupComponent implements OnChanges, AfterViewInit, ControlValueAccessor {
  @Input() radioGroup: any = {
    id: '',
    name: '',
    label: '',
    options: [],
    isOptional: false,
    legendSrOnly: false,
    showLegendAsLbl: false,
    alignVertical: false
  };
  optionalSelected = false;

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

  onItemClick(item: any) {
    if (item.isSelected && this.radioGroup.isOptional) {
      this.onOptionalFocus();
    }
    else {
      this.setSelectedValue(item.value);
      this._onChangeCallback(item.value);
    }
  }

  onOptionalFocus() {
    this.optionalSelected = true;
    this.setSelectedValue('');
    this._onChangeCallback('');
  }

  onItemFocus(item: any) {
    item.isFocused = true;
  }

  onItemBlur(item: any) {
    item.isFocused = false;
    this._onTouchedCallback();
  }

  writeValue(v: any) {
    this.setSelectedValue(v);
  }

  registerOnChange(fn: any) {
    //todo
    this._onChangeCallback = fn;
  }

  registerOnTouched(fn: any) {
    //todo
    this._onTouchedCallback = fn;
  }

  private setSelectedValue(value: any = '') {
    this.optionalSelected = false;
    this.radioGroup.options.forEach((item: any) => {
      if (item.value.toString() === (value || '').toString()) {
        item.isSelected = true;
      }
      else {
        item.isSelected = false;
      }
    });
  }
}
