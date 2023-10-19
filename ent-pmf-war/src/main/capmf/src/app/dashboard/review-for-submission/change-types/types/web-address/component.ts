import {Component, Input} from '@angular/core';
import {PayLoadService} from '../../../../../common/services/payload-all.service';

@Component({
  selector: 'review-for-submission-web-address-component',
  template: require('./body.html')
})

export class ReviewForSubmissionWebAddressComponent {
  @Input() payLoadService: PayLoadService;
}
