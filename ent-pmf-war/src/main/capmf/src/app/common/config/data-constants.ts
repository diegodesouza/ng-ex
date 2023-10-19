/**
 * Created by AD94882 on 4/21/17.
 */
import { Injectable } from '@angular/core';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { dataConstants } from './pmf-constants';

@Injectable()
export class DataService {
  public readonly JSON_DATA: any;

  constructor () {
    this.JSON_DATA = dataConstants;
  }
}
