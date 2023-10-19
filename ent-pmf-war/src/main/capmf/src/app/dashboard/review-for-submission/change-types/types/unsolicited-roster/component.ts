import {Component, Input} from '@angular/core';
import {PayLoadService} from '../../../../../common/services/payload-all.service';
import {DataService} from '../../../../../common/config/data-constants';

@Component({
  selector: 'review-for-submission-unsolicited-roster-component',
  template: require('./body.html')
})

export class ReviewForSubmissionUnsolicitedRosterComponent {
  @Input() payLoadService: PayLoadService;

  constructor(private dataService: DataService) {}
}
