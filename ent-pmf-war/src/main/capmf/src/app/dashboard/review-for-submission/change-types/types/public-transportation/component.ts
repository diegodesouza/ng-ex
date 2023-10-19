import {Component, Input} from '@angular/core';
import {PayLoadService} from '../../../../../common/services/payload-all.service';

@Component({
  selector: 'review-for-submission-public-transportation-component',
  template: require('./body.html')
})

export class ReviewForSubmissionPublicTransportationComponent {
  @Input() payLoadService: PayLoadService;
}
