import {Component, Input} from '@angular/core';
import {PayLoadService} from '../../../../../common/services/payload-all.service';
import {DataService} from '../../../../../common/config/data-constants';

@Component({
  selector: 'review-for-submission-terminate-ppa-component',
  template: require('./body.html')
})

export class ReviewForSubmissionTerminatePPAComponent {
  @Input() payLoadService: PayLoadService;

  constructor(private _payLoadService: PayLoadService, private dataService: DataService) {}
}
