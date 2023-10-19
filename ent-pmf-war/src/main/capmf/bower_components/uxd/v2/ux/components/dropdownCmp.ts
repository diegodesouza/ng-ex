import { IDropdownItem } from './../interfaces/iDropdownItem';
import {
  AfterViewInit,
  Component,
  ElementRef,
  EventEmitter,
  forwardRef,
  HostListener,
  Input,
  OnChanges,
  OnInit,
  Output,
  QueryList,
  Renderer,
  SimpleChanges,
  ViewChildren,
  ViewContainerRef
} from '@angular/core';
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';

export const DROPDOWN_COMPONENT_VALUE_ACCESSOR: any = {
  provide: NG_VALUE_ACCESSOR,
  useExisting: forwardRef(() => DropDownComponent),
  multi: true
};

//istanbul ignore next
@Component({
  moduleId: module.id,
  selector: '[data-uxd-dropdown-cmp]',
  template: `<fieldset class="pfSelect" [ngClass]="{'borderless': (dropDownType === 'borderless')}">
              <span class="sr-only" [innerText]="'label:'"></span>
              <legend [id]="'legend'+eleRef.nativeElement.id" [innerHTML]="labelText"></legend>
              <button [id]="'psButton'+eleRef.nativeElement.id" (click)="onButtonClick($event)" class="btn-primary psButton btn" tabindex="-1" type="button">
              <span [id]="'psActiveOption'+eleRef.nativeElement.id" class="psActiveOption">{{innerText}}</span>
              <span class="psArrow fa fa-caret-down"></span>
              </button>
              <div #psDropdown [id]="'psDropdown'+eleRef.nativeElement.id" class="psDropdown" tabindex="-1" [style.height]="itemsHeight" [ngClass]="{'active': dropdownOpened}">
                <div [style.height]="itemsHeight">
                  <span class="sr-only" [innerText]="'Use up and down arrow keys to cycle through options. Press enter to select'"></span>
                  <ng-container *ngFor="let item of items; let i = index;">
                    <input *ngIf="!item.hidden && !isFiltered(item.value)" #itemInput (focus)="onItemFocus($event, item)" (keydown)="onItemKeydown($event, item)" [disabled]="item.disabled" [attr.checked]="item.value === innerValue ? '' : null" [ngClass]="{'focus': (item.value === innerValue)}" [id]="'ddlInput'+eleRef.nativeElement.id+i" [attr.name]="'ddlInput'+eleRef.nativeElement.id" [attr.data-analytics]="item.analytics ? item.analytics : undefined" type="radio" [value]="item.value" class="psOption" [checked]="innerValue === item.value"/>
                    <label *ngIf="!item.hidden && !isFiltered(item.value)" [id]="'ddlLabel'+eleRef.nativeElement.id+i" [ngClass]="{'psDisabled': item.disabled}" [attr.for]="'ddlInput'+eleRef.nativeElement.id+i" #itemLabel (click)="onItemClick($event, item)" class="psLabel" [innerHTML]="item.label"></label>
                  </ng-container>
                </div>
              </div>
            </fieldset>`,
  providers: [DROPDOWN_COMPONENT_VALUE_ACCESSOR]
})
export class DropDownComponent implements OnInit, OnChanges, ControlValueAccessor, AfterViewInit {
  private prevFocusedEle: any;
  private _innerValue: any;

  private _onChangeCallback = (_: any) => {
    //noop
  }

  private _onTouchedCallback = () => {
    //noop
  }

  @ViewChildren('itemLabel', { read: ViewContainerRef }) itemLabels: QueryList<ViewContainerRef>;
  @ViewChildren('itemInput', { read: ViewContainerRef }) itemInputs: QueryList<ViewContainerRef>;
  @ViewChildren('psDropdown', { read: ViewContainerRef }) dropDown: QueryList<ViewContainerRef>;
  dropdownOpened: boolean = false;
  @Input('items') items: Array<IDropdownItem> = [];
  innerText: string = 'Select One';
  itemsHeight: any = '0px';
  @Input('defaultText') defaultText: string;
  @Input('labelText') labelText: string;
  @Input('filterItemValue') filterItemValue: Array<string>;
  @Output('onChange') onChange: EventEmitter<any> = new EventEmitter();
  @Input('hasError') hasError: boolean;
  @Input('dropDownType') dropDownType: string;
  //@Output() ngModelChange: EventEmitter<any> = new EventEmitter(false);

  constructor(public eleRef: ElementRef,
    private _renderer: Renderer) {
    if (this.defaultText) {
      this.innerText = this.defaultText;
    }
  }

  ngOnInit() {
    // init
  }

  ngAfterViewInit(): any {
    if (!this.eleRef.nativeElement.id) {
      console.error('DropDownComponent id missing !!!');
    }
    this._renderer.setElementClass(this.eleRef.nativeElement, 'uxd-btn-ddl', true);
    this._renderer.setElementProperty(this.eleRef.nativeElement, 'tabindex', '-1');

    this.selectItemByValue(this.innerValue);
  }

  get innerValue() {
    return this._innerValue;
  }

  set innerValue(val) {
    this._innerValue = val;
  }

  onButtonClick(event: Event) {
    if (this.dropdownOpened) {
      this.closeDropdown();
    }
    else {
      this.openDropdown();
    }
  }

  onItemKeydown(e: any, item: IDropdownItem) {
    let key = e.which || e.keyCode;

    if (key === 37 || key === 39) { // preventing left & right key to scroll up/down
      return false;
    }

    if (key === 27) {
      this.closeDropdown();
    }

    if (key === 13) {
      this.innerText = item.label;
      this.onChangeCallback(this.innerValue);
      this.closeDropdown();
      if (e.shiftKey && this.prevFocusedEle) {
        this.prevFocusedEle.focus();
        e.stopPropagation();
        return false;
      }

    }

    if (key === 9) {
      this.innerText = item.label;
      this.onChangeCallback(this.innerValue);
      this.closeDropdown();
    }

    return true;
  }

  onItemFocus(e: any, item: IDropdownItem) {
    if (!this.itemInputs.length) {
      return;
    }

    if (!this.dropdownOpened) {
      this.openDropdown();
    }

    this.selectItem(item);

    this.itemInputs.forEach((e) => {
      if (e.element.nativeElement.value === item.value.toString()) {
        $(this.dropDown.first.element.nativeElement).scrollTop(0);
        $(this.dropDown.first.element.nativeElement).scrollTop(e.element.nativeElement.offsetTop);
      }
    });
  }

  onItemClick(event: Event, item: IDropdownItem) {
    if (!item.disabled) {
      this.selectItem(item);
      this.innerText = item.label;
      this.onChangeCallback(item.value);
      this.closeDropdown();
    }
  }

  ngOnChanges(changes: SimpleChanges) {
    if (typeof changes['hasError'] !== 'undefined' &&
      changes['hasError'].currentValue === true) {
      this._renderer.setElementClass(this.eleRef.nativeElement, 'ant-error-state', true);

    } else {
      this._renderer.setElementClass(this.eleRef.nativeElement, 'ant-error-state', false);
    }
  }

  writeValue(value: any) {
    if (value !== this._innerValue) {
      this.selectItemByValue(value);
    }
  }

  registerOnChange(fn: any) {
    //todo
    this._onChangeCallback = fn;
  }

  registerOnTouched(fn: any) {
    this._onTouchedCallback = fn;
  }

  isFiltered(value: any) {
    if (!this.filterItemValue) {
      return false;
    }

    let result = false;
    this.filterItemValue.forEach((f: string) => {
      if (f.toString() === value.toString()) {
        result = true;
      }
    });
    return result;
  }

  private selectItemByValue(value: any) {
    if (!value) {
      this.innerValue = value;
      this.innerText = this.defaultText;
      return;
    }

    this.items.forEach((item: IDropdownItem) => {
      if (value.toString() === item.value.toString() && !item.disabled && !item.hidden) {
        this.selectItem(item);
        this.innerText = item.label;
        return;
      }
    });
  }

  private openDropdown() {
    if (!this.itemInputs.length) {
      return;
    }

    this.itemsHeight = this.calculateDropdownHeight() + 'px';
    this.dropdownOpened = true;
    this._onTouchedCallback();

    let ele: any = this.itemInputs.first.element.nativeElement;
    if (this.innerValue) {
      this.itemInputs.forEach((e) => {
        if (!e.element.nativeElement.disabled &&
          this.innerValue.toString() === e.element.nativeElement.value) {
          ele = e.element.nativeElement;
        }
      });
    }

    ele.focus();
  }

  private closeDropdown() {
    setTimeout(() => {
      this.dropdownOpened = false;
    }, 100);
  }

  private calculateDropdownHeight(): any {
    if (!this.itemLabels.length) {
      return;
    }

    let firstEleHeight = $(this.itemLabels.first.element.nativeElement).outerHeight();
    let height = firstEleHeight * 3;
    if (this.items.length < 3) {
      height = (this.items.length * firstEleHeight);
    }

    return (height + 2);
  }

  private getSelectedItem(value: any = ''): IDropdownItem {
    let result: IDropdownItem = null;
    this.items.forEach((item: IDropdownItem) => {
      if (value.toString() === item.value.toString() && !item.disabled && !item.hidden) {
        result = item;
      }
    });

    return result;
  }

  private onChangeCallback(value: any) {
    let r = this.getSelectedItem(value);
    if (r) {
      this.onChange.emit(r);
      //this.ngModelChange.emit(r.value);
      this._onChangeCallback(r.value);
    }
  }

  selectItem(item: IDropdownItem) {
    if (!item.disabled) {
      this.innerValue = item.value;
    }
  }

  @HostListener('document:click', ['$event'])
  onDocumentClick(event: any) {
    if (event.target !== this.eleRef.nativeElement &&
      !this.eleRef.nativeElement.contains(event.target) &&
      this.dropdownOpened) {
      this.closeDropdown();
    }
  }
}
