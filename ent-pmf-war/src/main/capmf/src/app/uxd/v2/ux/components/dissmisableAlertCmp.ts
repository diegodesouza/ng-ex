import { UxEvents } from '../enums/uxEvents';
import { IDissmisableAlertEvent } from './../interfaces/iDissmisableAlert';
import { UxHelper } from './../services/uxHelper';
import { Component, EventEmitter, OnDestroy, OnInit, QueryList, ViewChildren, ViewContainerRef } from '@angular/core';

//istanbul ignore next
@Component({
  moduleId: module.id,
  selector: '[data-uxd-dismissable-alert-cmp]',
  template: `
            <div class="ant-anthem-alert ant-dismissable alert-pattern-v2"
              role="alert" aria-hidden="true" tabindex="-1"
              [ngClass]="{'on': (alertOpened), 'ant-negative': (alertType === 'negative'), 'ant-positive': (alertType === 'positive'), 'ant-info': (alertType === 'info')}">
              <div class="media">
                <div class="media-left media-middle">
                  <span class="fa" aria-hidden="true" tabindex="-1"
                  [ngClass]="{'fa-exclamation': (alertType === 'negative'), 'fa-check': (alertType === 'positive'), 'fa-info': (alertType === 'information')}"></span>
                </div>
                <div class="media-body media-middle">
                  <a #alertCloseBtn href="javascript:void(0)" (click)="closeAlert()" class="ant-dismiss-alert" role="link" aria-describedby="dismissAlertContent" aria-live="polite" aria-atomic="true"><span class="fa fa-remove"></span></a>
                  <p [innerHTML]="content" id="dismissAlertContent"></p>
                </div>
              </div>
            </div>
          `
})
export class DissmisableAlertComponent implements OnInit, OnDestroy {
  private _alertEvent: EventEmitter<IDissmisableAlertEvent> = this._uxHlpr.getUxEvents(UxEvents[UxEvents.DISMISSABLE_ALERT]);
  alertType: string;
  content: any;
  alertOpened: boolean = false;
  private _alertTimer: any = null;
  private _prevFocusElem: any;
  private _subscription1: any;
  @ViewChildren('alertCloseBtn', { read: ViewContainerRef }) private _alertCloseBtn: QueryList<ViewContainerRef>;

  constructor(private _uxHlpr: UxHelper) {
  }

  ngOnInit() {
    this._subscription1 = this._alertEvent.subscribe((e: IDissmisableAlertEvent) => {
      if (e.eventType === 'OPEN') {
        this.showAlert(e);
      }
      else {
        this.closeAlert();
      }
    });
  }

  ngOnDestroy() {
    this._subscription1.unsubscribe();
  }

  showAlert(alert: IDissmisableAlertEvent) {
    if (document.activeElement) {
      this._prevFocusElem = document.activeElement;
    }

    this.closeAlert();
    this.alertType = alert.type;
    this.content = alert.content;
    this.alertOpened = true;
    setTimeout(() => {
      this._alertCloseBtn.first.element.nativeElement.focus();
    }, 100);

    this._alertTimer = setTimeout(() => {
      this.closeAlert();
    }, (alert.timeout || 4000));
  }

  closeAlert() {
    if (this._prevFocusElem) {
      this._prevFocusElem.focus();
      this._prevFocusElem = null;
    }

    this.alertOpened = false;
    this.content = '';
    if (this._alertTimer) {
      clearTimeout(this._alertTimer);
      this._alertTimer = null;
    }
  }

}
