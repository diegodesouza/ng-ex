import { InlineAlertComponent } from './inlineAlertCmp';
import { IDropdown } from './../interfaces/iDropdown';
import { ButtonDropdownItemDirective } from './btnDropdownItemCmp';
import {
  AfterViewInit,
  Attribute,
  Component,
  ContentChildren,
  ElementRef,
  HostListener,
  OnChanges,
  OnInit,
  QueryList,
  Renderer,
  SimpleChanges,
  ViewChild,
  Input
} from '@angular/core';

// TODO: Remove obsolete code

//istanbul ignore next
@Component({
  moduleId: module.id,
  selector: '[data-uxd-btn-ddl-cmp]',
  template: `<button #psButton id="psButton{{id}}" (click)="onButtonClick($event)" (keydown)="onButtonKeydown($event)" type="button" class="btn dropdown-toggle" [attr.aria-expanded]="dropdownOpened">
                <span class="buttonMain" [innerHTML]="dropdown?.label"></span>
                <span class="fa fa-caret-down"></span>
                <span class="sr-only">Toggle Dropdown</span>
              </button>
              <div *ngIf="dropdown" #psDropdown id="psDropdown{{id}}" class="ant-main-div">
                <ul class="dropdown-menu" role="menu" >
                  <li role="menuitem" *ngFor="let li of dropdown.options">
                    <a href="{{li.link}}" target="{{li.targetType? li.targetType: '_self'}}">
                      <span *ngIf="li.className" class={{li.className}}></span>
                      <span [innerHTML]="li?.title"></span>
                    </a>
                   </li>
                </ul>
              </div>`
})
export class ButtonDropDownComponent implements OnInit, OnChanges, AfterViewInit {
  @Input() dropdown: IDropdown;
  private onTouchedCallback: () => {};
  private onChangeCallback: (_: any) => {};
  private prevFocusedEle: any;

  dropdownOpened: boolean = false;
  innerText: string = 'Select One';
  @ViewChild('psButton') mainBtn: ElementRef;
  @ContentChildren(ButtonDropdownItemDirective) items: QueryList<ButtonDropdownItemDirective>;

  constructor( @Attribute('data-btn-class') private btnCssClass: string,
    @Attribute('data-default-text') private defaultText: string,
    @Attribute('id') public id: string,
    private _eleRef: ElementRef,
    private _renderer: Renderer) {
    if (!id) {
      console.error('ButtonDropDownComponent id missing !!!');
    }

    this.innerText = defaultText;
  }

  ngOnInit() {
    // init
  }

  ngAfterViewInit(): any {
    if (this.btnCssClass) {
      this._renderer.setElementClass(this.mainBtn.nativeElement, this.btnCssClass, true);
    }
  }

  ngOnChanges(changes: SimpleChanges) {
    // changes
  }

  onButtonClick(event: Event) {

    if (this.dropdownOpened) {
      this.closeDropdown();
    }
    else {
      this.openDropdown();
    }
  }

  onButtonFocus(event: Event) {
    setTimeout(() => {
      if (!this.dropdownOpened) {
        this.openDropdown();

        if (this.items.length && this.dropdownOpened) {
          this.setNextItemSelected();
        }
      }
    }, 100);
  }

  onButtonFocusout(event: Event) {
    this.closeDropdown();
    this.unSelectAllItems();
  }

  onButtonKeydown(event: any) {
    let key = event.which || event.keyCode;
    switch (key) {
      case 38: // up
        this.setPrevItemSelected();
        event.preventDefault();
        return false;

      case 40: // down
        this.setNextItemSelected();
        event.preventDefault();
        return false;

      case 13: // enter
        this.clickSelectedItem();
        this.closeDropdown();
        event.preventDefault();
        return false;

      case 27: // esc
        this.closeDropdown();
        event.preventDefault();
        return false;

      case 9: // tab
        this.closeDropdown();
        break;

      default: return true; // exit this handler for other keys
    }

    return true;
  }

  @HostListener('document:click', ['$event'])
  onDocumentClick(event: any) {
    if (event.target !== this._eleRef.nativeElement &&
      !this._eleRef.nativeElement.contains(event.target) &&
      this.dropdownOpened) {
      this.closeDropdown();
    }
  }

  private setNextItemSelected() {
    let selectedIndex = -1;;
    this.items.forEach((item: ButtonDropdownItemDirective, index: number) => {
      if (item.isSelected) {
        selectedIndex = index;
      }
    });

    this.unSelectAllItems();
    if (selectedIndex > -1 && selectedIndex < (this.items.length - 1)) {
      this.items.toArray()[selectedIndex + 1].setActive();
      this.innerText = this.items.toArray()[selectedIndex + 1].getText();
    }
    else {
      this.items.first.setActive();
      this.innerText = this.items.first.getText();
    }
  }

  private setPrevItemSelected() {
    let selectedIndex = -1;;
    this.items.forEach((item: ButtonDropdownItemDirective, index: number) => {
      if (item.isSelected) {
        selectedIndex = index;
      }
    });

    this.unSelectAllItems();
    if (selectedIndex > 0) {
      this.items.toArray()[selectedIndex - 1].setActive();
      this.innerText = this.items.toArray()[selectedIndex - 1].getText();
    }
    else {
      this.items.last.setActive();
      this.innerText = this.items.last.getText();
    }
  }

  private unSelectAllItems() {
    this.items.forEach((item: ButtonDropdownItemDirective) => {
      item.unsetActive();
    });
    this.innerText = this.defaultText;
  }

  private closeDropdown() {
    this._renderer.setElementClass(this._eleRef.nativeElement, 'open', false);
    this.dropdownOpened = false;
  }

  private openDropdown() {
    this.unSelectAllItems();
    this._renderer.setElementClass(this._eleRef.nativeElement, 'open', true);
    this.dropdownOpened = true;
  }

  private clickSelectedItem() {
    this.items.forEach((item: ButtonDropdownItemDirective) => {
      if (item.isSelected) {
        item.handleClick();
        return;
      }
    });
  }
}
