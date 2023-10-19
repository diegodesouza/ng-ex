import { Component, ElementRef } from '@angular/core';

@Component({
  selector: '[data-uxd-alt-toggle-body-cmp]',
  host: {
    '[class.alt-toggle-body]': 'true',
    '[class.closed]': '!isExpanded',
    '[id]': 'buildToggleBodyId()',
  },
  template: `
      <ng-content></ng-content>
  `
})

export class AlternateToggleBodyComponent {
  private _isExpanded: boolean = false;
  private _toggleNumber: number;

  constructor(private _toggleBodyRef: ElementRef) {
  }

  private buildToggleBodyId(): string {
    let id = 'alt-toggle-body-container-' + this._toggleNumber;
    return id;
  }

  set isExpanded(bool: boolean) {
    this._isExpanded = bool;
  }

  get isExpanded(): boolean {
    return this._isExpanded;
  }

  set toggleNumber(toggleNum: number) {
    this._toggleNumber = toggleNum;
  }

  get toggleNumber(): number {
    return this._toggleNumber;
  }

  get toggleBodyRef(): ElementRef {
    return this._toggleBodyRef;
  }
}
