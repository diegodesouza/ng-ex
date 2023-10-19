import {Component, ViewContainerRef} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { LandingPageComponent } from './landing-page/component';
import {UxHelper} from './uxd/v2/ux/services/uxHelper';
import {CanActivatePMFGuard} from './common/gaurds/activate-pmf-gaurd';
import {CanActivateLandingPageGuard} from './common/gaurds/activate-landingpage-gaurd';

@Component({
  selector: 'app-root',
  template: '<router-outlet></router-outlet>'
})
export class RootComponent {
  constructor(viewContainerRef: ViewContainerRef, private uxHelper: UxHelper) {
    uxHelper.setRootViewContainerRef(viewContainerRef);
  }
}

export const routes: Routes = [
  {
    path: '',
    component: LandingPageComponent,
    canActivate: [CanActivatePMFGuard]
  },
  {
    path: 'landingpage',
    component: LandingPageComponent,
    canActivate: [CanActivateLandingPageGuard]
  }
];

export const routing = RouterModule.forRoot(routes);
