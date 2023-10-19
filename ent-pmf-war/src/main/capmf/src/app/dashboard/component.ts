/**
 * Created by AD94882 on 4/18/17.
 */

import {AfterViewInit, Component} from '@angular/core';
import {Router} from '@angular/router';
import {SelectUpdatesModel} from '../common/config/select-updates.model';
import {PayLoadService} from '../common/services/payload-all.service';
import {ScrollService} from '../common/services/scroll.service';

@Component({
    template: require('./body.html'),
    styles: [require('./style/bootstrap-nav-wizard.css')]
  }
)

export class DashboardComponent implements AfterViewInit  {
  public selectedUpdates: Object;

  constructor(
    private scrollService: ScrollService,
    private router: Router,
    private updates: SelectUpdatesModel,
    private payLoadService: PayLoadService
  ) {}

  ngAfterViewInit() {
    this.scrollService.scrollToTop();
  }
}
