import {Component, Input} from '@angular/core';
import {PayLoadService} from '../../../../common/services/payload-all.service';

@Component({
  selector: 'review-for-submission-practice-detail-component',
  template: require('./body.html')
})

export class ReviewForSubmissionPracticeDetailComponent {
  @Input() payLoadService: PayLoadService;

}
