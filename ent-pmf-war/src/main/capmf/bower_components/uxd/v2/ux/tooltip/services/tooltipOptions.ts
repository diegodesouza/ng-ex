import { Injectable } from '@angular/core';

//istanbul ignore next
@Injectable()
export class TooltipOptions {
  placement: string;
  popupClass: string;
  animation: boolean;
  appendToBody: boolean;
  isOpen: boolean;
  content: string;
  htmlContent: any;
  context: any;
  hasCloseBtn: boolean;

  constructor(options: any) {
    Object.assign(this, options);
  }
}
