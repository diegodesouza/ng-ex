import { IModal } from './../interfaces/iModals';
import { ModalDirective } from './../modal/directives/modalDir';
import { UxModalHelper } from './../services/modalHlpr';
import { UxHelper } from './../services/uxHelper';
import { Component, ElementRef, EventEmitter, HostListener, Input, Output, Renderer, ViewChild } from '@angular/core';

//istanbul ignore next
@Component({
  moduleId: module.id,
  selector: '[data-uxd-modal-cmp]',
  exportAs: 'modal',
  template: `
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-top">
        <div class="modal-header">
          <button *ngIf="!modal?.hideClose" [id]="modal.id+'_close'" type="button" class="close" data-dismiss="modal" aria-label="Close" data-autofocus (click)="handleClose()">
            <span class="fa fa-remove"></span>
          </button>
          <h2 #title class="modal-title" [id]="modal.id+'_title'" [innerHTML]="modal?.title"> </h2>
        </div>
        <div class="modal-body">
          <ng-content select=".modal-component-body"></ng-content>
          <span *ngIf="modal?.content" [id]="modal.id+'_body'" [innerHTML]="modal?.content"></span>
        </div>
      </div>
      <div class="modal-footer">
        <ng-content select=".modal-component-footer"></ng-content>
      </div>
      <div data-lastfocus tabindex="0"></div>
    </div>
  </div>`
})
export class ModalComponent extends ModalDirective {
  @Input() modal: IModal;
  @Output() onClose: EventEmitter<ModalDirective> = new EventEmitter<ModalDirective>();
  @ViewChild('title') titleElement: ElementRef;

  constructor(element: ElementRef,
    renderer: Renderer,
    uxHlpr: UxHelper,
    private _modalHlpr: UxModalHelper) {
    super(element, renderer, uxHlpr);
  }

  handleClose() {
    this.hide();
    this.onClose.emit();
  }

  ngAfterViewInit(): any {
    if (!this.modal || !this.modal.id) {
      console.error('ModalComponent requires id');
    }

    this._config = this._config || this.getConfig();
    this.renderer.setElementClass(this.element.nativeElement, 'modal', true);
    this.renderer.setElementClass(this.element.nativeElement, 'fade', true);
    this.renderer.setElementAttribute(this.element.nativeElement, 'role', 'dialog');

    if (this.modal && this.modal.hideClose) {
      this.renderer.setElementAttribute(this.titleElement.nativeElement, 'data-autofocus', 'true');
    }

    if (this.modal && this.modal.cssClass) {
      this.renderer.setElementClass(this.element.nativeElement, this.modal.cssClass, true);
    }

    if (this.modal && this.modal.modalType === 'error') {
      this.renderer.setElementClass(this.element.nativeElement, 'ant-error-modal', true);
    }
    else if (this.modal && this.modal.modalType === 'fullscreen') {
      this.renderer.setElementClass(this.element.nativeElement, 'ant-fullscreen-modal', true);
    }
  }

  @HostListener('click', ['$event'])
  private handleAnchorClicks(event: any) {
    return this._modalHlpr.handleClick(event, this.element);
  }
}
