import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {PMFConfigService} from '../config/pmf-config.service';

@Injectable()
export class CanActivatePMFGuard implements CanActivate {

  constructor(private configService: PMFConfigService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    let brand = route.queryParams['brand'];
    if(!brand) { // TODO: Remove once brand is set from service.
      brand = 'caabc';
    }
    if(brand) {
      this.router.navigate(['/landingpage'], { queryParams: { brand: brand },  queryParamsHandling: 'merge' });
    }
    return false;
  }
}
