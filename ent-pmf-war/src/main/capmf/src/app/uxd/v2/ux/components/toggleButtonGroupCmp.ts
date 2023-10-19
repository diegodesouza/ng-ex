import { ControlValueAccessor, NG_VALUE_ACCESSOR, NG_VALIDATORS, FormControl } from '@angular/forms';
import { Component, OnChanges, Input, ContentChild, AfterViewInit, ElementRef, Renderer, Output, EventEmitter, forwardRef } from '@angular/core';
//istanbul ignore next
@Component({
  selector: '[data-tcp-toggle-button-group-cmp]',
  template: `<span class="toggle-pattern-v2">
              <fieldset class="pfToggleB">
                      <ng-content></ng-content>
                </fieldset>
              </span>
             `,
})
export class ToggleButtonGroupComponent {
  //noop
}
