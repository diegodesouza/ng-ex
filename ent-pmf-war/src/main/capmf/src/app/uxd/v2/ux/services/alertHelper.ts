import { UxHelper } from './uxHelper';
import { IDissmisableAlertEvent } from './../interfaces/iDissmisableAlert';
import { Injectable, Inject, EventEmitter } from '@angular/core';
import { UxEvents } from '../enums/uxEvents';

//istanbul ignore next
@Injectable()
export class AlertHelper {
  private _alertEvent: EventEmitter<IDissmisableAlertEvent> = this._uxHlpr.getUxEvents(UxEvents[UxEvents.DISMISSABLE_ALERT]);

  constructor(private _uxHlpr: UxHelper,
    @Inject('uxdAlertHlpr') private _uxdAlertHlpr: any) {
  }

  openAlert(content: string, alertType: string, timeout: number = 4000) {
    this._uxdAlertHlpr.openAlert(content, alertType, timeout);
    this._alertEvent.emit({
      eventType: 'OPEN',
      type: alertType,
      content: content,
      timeout: timeout
    });
  }

  closeAlert() {
    this._uxdAlertHlpr.closeAlert();
    this._alertEvent.emit({
      eventType: 'CLOSE',
      type: '',
      content: ''
    });
  }

}
