import {Component, Input} from '@angular/core';
import {PayLoadService} from '../../../../../common/services/payload-all.service';

@Component({
  selector: 'review-for-submission-email-component',
  template: require('./body.html')
})

export class ReviewForSubmissionEmailComponent {
  @Input() payLoadService: PayLoadService;
}
