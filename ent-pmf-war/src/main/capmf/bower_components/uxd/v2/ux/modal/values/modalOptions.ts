import { IModalOptions } from '../interfaces/iModalOptions';
export const modalConfigDefaults: IModalOptions = {
  backdrop: true,
  keyboard: false,
  focus: true,
  show: true,
  ignoreBackdropClick: true
};

export const modalClassNames: any = {
  SCROLLBAR_MEASURER: 'modal-scrollbar-measure',
  BACKDROP: 'modal-backdrop',
  OPEN: 'modal-open',
  FADE: 'fade',
  IN: 'in',         // bs3
  ACTIVE: 'active'  // bs4
};

export const modalSelectors: any = {
  DIALOG: '.modal-dialog',
  DATA_TOGGLE: '[data-toggle="modal"]',
  DATA_DISMISS: '[data-dismiss="modal"]',
  FIXED_CONTENT: '.navbar-fixed-top, .navbar-fixed-bottom, .is-fixed'
};
