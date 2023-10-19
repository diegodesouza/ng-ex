/**
 * Created by AC56833 on 4/27/17.
 */

import {AfterViewInit, Component} from '@angular/core';
import {DataService} from '../../common/config/data-constants';
import {SelectUpdatesModel} from '../../common/config/select-updates.model';
import {PayLoadService} from '../../common/services/payload-all.service';
import {Router} from '@angular/router';
import {ScrollService} from '../../common/services/scroll.service';

@Component({
        template: require('./body.html'),
        styles: [require('./css/select-updates.css'), require('../../common/css/pmf-main.css')]
    }
)

export class SelectUpdatesComponent implements AfterViewInit {
  constructor(
    private scrollService: ScrollService,
    private dataService: DataService,
    private selectUpdatesModel: SelectUpdatesModel,
    private userTypeService: PayLoadService,
    private router: Router
  ) {}

  routeToChangeTypes() {
    let outlets = {};
    // todo: the below is inefficent and non-maintanable as angular routing does not support dynamic named routes at this point. It is a WIP.
    // https://github.com/angular/angular/issues/12522  needs to be watched
    // until then this condition need to be updated for every new chage type route
    Object.assign(outlets, { 'userreference': 'userreference' });
    this.router.navigate(['dashboard/changetypes',
      { outlets: outlets }], { skipLocationChange: true });
  }

  ngAfterViewInit() {
    this.scrollService.scroll();
  }
}
