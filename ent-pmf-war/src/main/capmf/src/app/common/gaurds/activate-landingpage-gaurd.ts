import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {PMFConfigService} from '../config/pmf-config.service';

@Injectable()
export class CanActivateLandingPageGuard implements CanActivate {

  constructor(private configService: PMFConfigService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if(this.configService.isBrandSet()) {
      return true;
    }
    let brand = route.queryParams['brand'];
    this.configService.setState(brand);
    if(this.configService.validBrand(brand)) {
      this.configService.setBrand(brand);
      return true;
    }
    return false;
  }
}
