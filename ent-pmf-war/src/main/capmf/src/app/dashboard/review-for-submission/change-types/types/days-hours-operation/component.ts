import {Component, Input} from '@angular/core';
import {PayLoadService} from '../../../../../common/services/payload-all.service';

@Component({
  selector: 'review-for-submission-days-hours-operation-component',
  template: require('./body.html')
})

export class ReviewForSubmissionDaysHoursOperationComponent {
  @Input() payLoadService: PayLoadService;
}
