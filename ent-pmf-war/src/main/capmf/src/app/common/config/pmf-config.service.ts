import { Injectable } from '@angular/core';

@Injectable()
export class PMFConfigService {
  public CONFIG: any;
  brandMap = {
    'caabc':   'ca',
    'coabcbs': 'co',
    'ctabcbs': 'ct',
    'gaabcbs': 'ga',
    'inabcbs': 'in',
    'kyabcbs': 'ky',
    'meabcbs': 'me',
    'moabcbs': 'mo',
    'nvabcbs': 'nv',
    'nhabcbs': 'nh',
    'ohabcbs': 'oh',
    'vaabcbs': 'va',
    'wiabcbs': 'wi',
    'wvunc':   'wv'
  };
  private state: string;

  constructor() {
    this.CONFIG = require('./default/pmf-config.json');
  }

  setState(brand) {
    this.state = this.brandMap[brand];
  }

  getState(): string {
    return this.state;
  }

  validBrand(brand: string): boolean {
    return this.brandMap[brand] != null;
  }

  setBrand(brand?: string) {
    let brandDir = this.brandMap[brand];
    if(brandDir) {
      let brandConfig = require('./'+brandDir+'/pmf-config.json');
      this.CONFIG = this.mergeDeep(this.CONFIG, brandConfig);
      this.CONFIG['BRAND'] = brand;
    }
  }

  isBrandSet(): boolean {
    return this.CONFIG['BRAND'] != null;
  }

  hasLandingPageSteps(): boolean {
    return this.CONFIG?.landingPage && this.CONFIG?.landingPage.steps;
  }

  isObject(item) {
    return (item && typeof item === 'object' && !Array.isArray(item));
  }

  mergeDeep(defaultObj, sourceObj) {
    let output = Object.assign({}, defaultObj);
    if (this.isObject(defaultObj) && this.isObject(sourceObj)) {
      Object.keys(sourceObj).forEach((key) => {
        if (this.isObject(sourceObj[key])) {
          if (!(key in defaultObj)) {
            Object.assign(output, {[key]: sourceObj[key]});
          } else {
            output[key] = this.mergeDeep(defaultObj[key], sourceObj[key]);
          }
        } else {
          Object.assign(output, { [key]: sourceObj[key] });
        }
      });
    }
    return output;
  }
}
