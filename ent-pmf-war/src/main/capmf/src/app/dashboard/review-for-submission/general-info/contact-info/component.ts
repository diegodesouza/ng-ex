import {Component, Input} from '@angular/core';
import {PayLoadService} from '../../../../common/services/payload-all.service';

@Component({
  selector: 'review-for-submission-contact-info-component',
  template: require('./body.html')
})

export class ReviewForSubmissionContactInfoComponent {
  @Input() payLoadService: PayLoadService;
}
