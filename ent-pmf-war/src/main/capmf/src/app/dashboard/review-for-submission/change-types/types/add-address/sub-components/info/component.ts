import {Component, Input} from '@angular/core';
import {PayLoadService} from '../../../../../../../common/services/payload-all.service';

@Component({
  selector: 'review-for-submission-add-address-info-component',
  template: require('./body.html')
})

export class ReviewForSubmissionAddAddressInfoComponent {
  @Input() payLoadService: PayLoadService;
}
