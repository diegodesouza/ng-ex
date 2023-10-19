import {Component, Input } from '@angular/core';
import {PayLoadService} from '../../../../../common/services/payload-all.service';

@Component({
  selector: 'review-for-submission-remove-provider-location-component',
  template: require('./body.html')
})

export class ReviewForSubmissionRemoveProviderLocationComponent  {
  @Input() payLoadService: PayLoadService;
}
