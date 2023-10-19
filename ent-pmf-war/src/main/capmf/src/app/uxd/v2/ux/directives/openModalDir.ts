import { UxEvents } from '../enums/uxEvents';
import { IOpenModalEvent } from './../interfaces/iOpenModalEvent';
import { UxHelper } from './../services/uxHelper';
import { Attribute, Directive, EventEmitter, HostListener } from '@angular/core';
declare var JQuery: any;

//istanbul ignore next
@Directive({
  selector: '[data-uxd-open-modal-dir]'
})
export class OpenModalDirective {
  private _openModalEvent: EventEmitter<IOpenModalEvent> = this._uxHlpr.getUxEvents(UxEvents[UxEvents.OPEN_GLOBAL_MODAL]);

  constructor(private _uxHlpr: UxHelper,
    @Attribute('data-modal-html-template') private templateUrl: string) {
    if (!templateUrl) {
      console.error('OpenModalDirective missing data-modal-html-template attribute');
    }
  }

  @HostListener('click', ['$event.button', '$event.ctrlKey', '$event.metaKey'])
  onClick(button: number, ctrlKey: boolean, metaKey: boolean): boolean {
    if (this.templateUrl) {
      this._openModalEvent.emit({
        modalTemplateUrl: this.templateUrl
      });
    }
    return true;
  }
}
