import { IModalOptions } from './../modal/interfaces/iModalOptions';

export interface IModal {
  id: string;
  label?: string;
  buttonContent?: string;
  modalType?: string;
  title?: string;
  content?: any;
  cssClass?: string;
  hideClose?: boolean;
}
