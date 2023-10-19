import { TemplateRef } from '@angular/core';

export class TooltipDef {
  content: string = '';
  htmlContent: string | TemplateRef<any> = '';
  placement: string = 'top';
  isOpen: boolean = false;
  enable: boolean = true;
  animation: boolean = true;
  appendToBody: boolean = false;
  popupClass: string = '';
  tooltipContext: any;
  delay: number = 0;
  hasCloseBtn: boolean = true;
  autoFocus: boolean = true;
  openOnFocus: boolean = false;
}
