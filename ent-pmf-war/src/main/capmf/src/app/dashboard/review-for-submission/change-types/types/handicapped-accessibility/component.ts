import {Component, Input} from '@angular/core';
import {PayLoadService} from '../../../../../common/services/payload-all.service';

@Component({
  selector: 'review-for-submission-handicapped-accessibility-component',
  template: require('./body.html')
})

export class ReviewForSubmissionHandicappedAccessibilityComponent {
  @Input() payLoadService: PayLoadService;
}
