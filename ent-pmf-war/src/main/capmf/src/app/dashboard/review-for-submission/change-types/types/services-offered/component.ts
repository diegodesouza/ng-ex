import {Component, Input} from '@angular/core';
import {PayLoadService} from '../../../../../common/services/payload-all.service';

@Component({
  selector: 'review-for-submission-services-offered-component',
  template: require('./body.html')
})

export class ReviewForSubmissionServicesOfferedComponent {
  @Input() payLoadService: PayLoadService;
}
