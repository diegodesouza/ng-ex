import {Component, Input} from '@angular/core';
import {PayLoadService} from '../../../../../common/services/payload-all.service';

@Component({
  selector: 'review-for-submission-area-of-expertise-component',
  template: require('./body.html')
})

export class ReviewForSubmissionAreaOfExpertiseComponent {
  @Input() payLoadService: PayLoadService;
}
