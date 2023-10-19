import { IFormInput } from './../interfaces/iFormItem';
import { Component, OnChanges, Input, ContentChild, AfterViewInit, ElementRef, Renderer, Pipe, PipeTransform } from '@angular/core';

//istanbul ignore next
/**
 *  Usage:
 *  <div data-tcp-form-item [definition]="{errorDefs: {required: 'required toggle button'}, showErrors: btnToggleSw.errors, inputErrors: btnToggleSw.errors}">
 *     <div #input #btnToggleSw="ngModel" required ngDefaultControl data-tcp-toggle-switch-cmp [id]="'btnToggleSw'" [name]="'btnToggleSw'"
 *    [(ngModel)]="toggleTest" [labelText]="'toggle switch'" [options]="[{label:'On',value: 'on'},{label:'Off',value:'off'}]"></div>
 *  </div>
 *  <div data-tcp-form-item [definition]="{errorDefs:{required: content.errors.reqErrText}, showErrors:btnToggleSw.errors, inputErrors:btnToggleSw.errors}">
 *     <input #input #txtTest="ngModel" type="text" required [id]="'txtTest'" [name]="'txtTest'"/>
 *  </div>
 */
@Component({
  selector: '[data-uxd-form-item-cmp]',
  template: `<div class="form-item" [class.has-error]="definition.showErrors">
              <label *ngIf="definition.label" [id]="'lbl_'+elementId" class="" [for]="elementId" [innerHTML]="definition.label">
              </label>
              <span [id]="'tTip_'+elementId" class="pfText" [innerHTML]="definition.waterMark" *ngIf="definition.waterMark"></span>
              <ng-content></ng-content>
              <div *ngIf="errorMessage && !definition.alwaysShowErrors" class="text-negative ant-float-left ant-full-width error-msgs">
                    <span [id]="elementId+'Err'" class="ant-form-item-error ant-float-left" [innerHTML]="errorMessage">
                    </span>
              </div>
              <div *ngIf="definition.alwaysShowErrors && definition.showErrors">
                <ng-container *ngFor="let errDef of (definition.errorDefs | objKeys); let i = index;">
                <span [id]="'err_'+elementId+i" class="ant-float-left ant-full-width" [class.ant-form-item-error]="definition.inputErrors && definition.inputErrors[errDef]" [class.ant-form-item-valid]="!definition.inputErrors || !definition.inputErrors[errDef]"
							[innerHTML]="definition.errorDefs[errDef]"></span>
                </ng-container>
              </div>
             </div>`
})
export class FormItemComponent implements OnChanges, AfterViewInit {
  @Input() definition: IFormInput = {
    id: '',
    showErrors: false
  };
  @ContentChild('input', {read: ElementRef}) inputElement: any;
  errorMessage: string = '';
  elementType: string = '';
  elementId: string = '';

  constructor(private _renderer: Renderer) { }

  ngAfterViewInit() {
    this.elementType = (this.inputElement && this.inputElement.nativeElement && this.inputElement.nativeElement.type) ? this.inputElement.nativeElement.type.toLowerCase() : '';
    if (/^(text|tel|password|number|email)$/.test(this.elementType)) {
      this._renderer.setElementClass(this.inputElement.nativeElement, 'ant-text-input', true);
      if (this.definition.waterMark) {
        this._renderer.setElementClass(this.inputElement.nativeElement, 'pfText', true);
      }
    }

    if (this.inputElement && this.inputElement.nativeElement && this.inputElement.nativeElement.id) {
      this.elementId = this.inputElement.nativeElement.id;
    }
  }

  ngOnChanges(changes: any): void {
    let errors: any = changes.inputErrors ? changes.inputErrors.currentValue : this.definition.inputErrors;
    let showErr: boolean = changes.showErrors ? changes.showErrors.currentValue : this.definition.showErrors;
    this.errorMessage = '';
    if (errors && showErr && this.definition.errorDefs) {
      Object.keys(this.definition.errorDefs).some((key: any) => {
        if (errors[key]) {
          this.errorMessage = this.definition.errorDefs[key];
          return true;
        }
        return false;
      });
    }
  }
}

@Pipe({ name: 'objKeys' })
export class ObjectKeysPipe implements PipeTransform {
  transform(value: any): Array<any> {
    return value ? Object.keys(value) : [];
  }
}
