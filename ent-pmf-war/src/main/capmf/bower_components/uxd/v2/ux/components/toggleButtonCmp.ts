import { IToggleButton } from './../interfaces/iToggleButton';
import { ControlValueAccessor, NG_VALUE_ACCESSOR, NG_VALIDATORS, FormControl } from '@angular/forms';
import { Component, OnChanges, Input, ContentChild, AfterViewInit, ElementRef, Renderer, Output, EventEmitter, forwardRef } from '@angular/core';
//istanbul ignore next
@Component({
  selector: '[data-tcp-toggle-button-cmp]',
  template: `<legend [class.sr-only]="toggle.legendSrOnly" [id]="'lblOptLgnd'+toggle.id" [innerHTML]="toggle.labelText"></legend>
                <div class="optionWrapper">
                    <input (focus)="onItemFocus()" (blur)="onItemBlur()" (click)="onItemClick()" [checked]="toggle.isSelected" class="ptOption" type="checkbox" [name]="'rbtnOpt'+toggle.id" [id]="'rbtnOpt'+toggle.id" />
                    <label [class.focus]="isFocused" [id]="'lblOpt'+toggle.id" [ngClass]="{'active': (isSelected)}"  class="ptLabel" [attr.for]="'rbtnOpt'+toggle.id" >
                    <span [innerHTML]="toggle.optionText"></span>
                    </label>
                  </div>
             `,
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => ToggleButtonComponent),
      multi: true
    }
  ]
})
export class ToggleButtonComponent implements OnChanges, AfterViewInit, ControlValueAccessor {
  @Input() toggle: IToggleButton = {
    optionText: '',
    labelText: '',
    id: '',
    trueValue: true,
    falseValue: false,
    legendSrOnly: false,
  };
  isSelected: boolean = false;
  isFocused: boolean = false;
  @Input() legendSrOnly: boolean = false;

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
    this.setSelected(this.isSelected ? this.toggle.falseValue : this.toggle.trueValue);
  }

  onItemFocus(o: any) {
    this.isFocused = true;
  }

  onItemBlur(o: any) {
    this.isFocused = false;
    this._onTouchedCallback();
  }

  writeValue(value: any) {
    if (value) {
      this.setSelected(value);
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

  private setSelected(value: any = '') {
    if (value.toString() === this.toggle.trueValue.toString()) {
      this.isSelected = true;
    }
    else {
      this.isSelected = false;
    }
    this._onChangeCallback(value);
  }
}
