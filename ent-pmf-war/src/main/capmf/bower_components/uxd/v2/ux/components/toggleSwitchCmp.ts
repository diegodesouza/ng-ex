import { IToggleSwitch } from './../interfaces/iToggleSwitch';
import { ControlValueAccessor, NG_VALUE_ACCESSOR, NG_VALIDATORS, FormControl } from '@angular/forms';
import { Component, OnChanges, Input, ContentChild, AfterViewInit, ElementRef, Renderer, Output, EventEmitter, forwardRef, OnInit } from '@angular/core';

//istanbul ignore next
@Component({
  selector: '[data-tcp-toggle-switch-cmp]',
  template: `<span class="toggle-pattern-v2">
            <fieldset class="pfToggleSwitch">
                <legend [class.sr-only]="toggle.legendSrOnly"  [id]="'lblOptLgnd'+toggle.id" class="" [innerHTML]="toggle.labelText"></legend>
                <ng-container *ngFor="let o of toggle.options; let pIdx = index;">
                  <div class="optionWrapper">
                    <input (focus)="onItemFocus(o)" (blur)="onItemBlur(o)" (click)="onItemClick(o)" class="ptOption" type="radio" [name]="'rbtnOpt'+toggle.id" [id]="'rbtnOpt'+toggle.id+pIdx" [value]="o.value" />
                    <label [class.focus]="o.isFocused" [id]="'lblOpt'+toggle.id+pIdx" [ngClass]="{'active': (o.isSelected)}"  class="ptLabel" [attr.for]="'rbtnOpt'+toggle.id+pIdx"><span [innerHTML]="o.label"></span></label>
                  </div>
                </ng-container>
             </fieldset>
             </span>`,
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => ToggleSwitchComponent),
      multi: true
    }
  ]
})
export class ToggleSwitchComponent implements OnInit, OnChanges, AfterViewInit, ControlValueAccessor {
  @Input() options: any = [];
  @Input() labelText: string = '';
  @Input() id: string = '';
  @Input() legendSrOnly: boolean = false;
  @Input() toggle: IToggleSwitch = {
    id: '',
    legendSrOnly: false,
    labelText: '',
    options: null,
  };

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
  ngOnInit() {
    if (this.id) {
      this.toggle.id = this.id;
    }
    if ((typeof this.options !== 'undefined') && this.options.length){
      this.toggle.options = this.options;
    }
    if (this.labelText) {
      this.toggle.labelText = this.labelText;
    }
    if (this.legendSrOnly) {
      this.toggle.legendSrOnly = this.legendSrOnly;
    }
  }
  ngOnChanges(changes: any): void {
    //noop
  }

  onItemClick(o: any) {
    this.setSelected(o.value);
  }

  onItemFocus(o: any) {
    this.setFocus(true, o.value);
  }

  onItemBlur(o: any) {
    this.setFocus(false, o.value);
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

  private setSelected(value: any) {
    this.toggle.options.forEach((o: any) => {
      if (o.value.toString() === value.toString()) {
        o.isSelected = true;
      }
      else {
        o.isSelected = false;
      }
    });
    this._onChangeCallback(value);
  }

  private setFocus(set: boolean, value: any) {
    this.toggle.options.forEach((o: any) => {
      if (o.value.toString() === value.toString()) {
        o.isFocused = set;
      }
    });
  }
}
