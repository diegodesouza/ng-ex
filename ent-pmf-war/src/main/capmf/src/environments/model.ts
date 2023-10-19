import { NgModuleRef } from '@angular/core';

export interface Environment {
  production: boolean;
  showDevModule: boolean;
  decorateModuleRef(modRef: NgModuleRef<any>): NgModuleRef<any>;
}
