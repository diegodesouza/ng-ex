import {Component, Input} from '@angular/core';
import {PayLoadService} from '../../../common/services/payload-all.service';
import {SelectUpdatesModel} from '../../../common/config/select-updates.model';
import {Router} from '@angular/router';

@Component({
  selector: 'review-for-submission-change-types-component',
  template: require('./body.html'),
  styles:  [require('../../../common/css/pmf-main.css')]
})

export class ReviewForSubmissionChangeTypesComponent {
  @Input() payLoadService: PayLoadService;

  constructor(private updates: SelectUpdatesModel, private router: Router) {}

  routeToChangeTypes() {
    let outlets = {};
    // todo: the below is inefficent and non-maintanable as angular routing does not support dynamic named routes at this point. It is a WIP.
    // https://github.com/angular/angular/issues/12522  needs to be watched
    // until then this condition need to be updated for every new chage type route
    Object.assign(outlets, { 'userreference': 'userreference' });
    this.router.navigate(['dashboard/changetypes', { outlets: outlets }], { skipLocationChange: true });
  }
}
