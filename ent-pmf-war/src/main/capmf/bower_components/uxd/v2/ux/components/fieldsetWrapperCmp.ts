import { FormControl } from '@angular/forms';
import { Component, OnChanges, Input, ElementRef, Renderer, OnInit, Output, EventEmitter, forwardRef } from '@angular/core';
//istanbul ignore next
@Component({
  selector: '[data-uxd-form-group-cmp]',
  template: `<span class="toggle-pattern-v2">
              <fieldset [ngClass]="fieldsetClass">
              <legend [class.sr-only]="legendSrOnly" [innerHTML]="label"></legend>
                <span [class.alignVertical]="alignVertical">
                  <ng-content></ng-content>
                </span>
                </fieldset>
              </span>
             `,
})
export class FieldSetWrapperComponent {
  @Input() label: string = '';
  @Input() fieldsetClass: string = '';
  @Input() legendSrOnly: boolean = false;
  @Input() alignVertical: boolean = false;
}
