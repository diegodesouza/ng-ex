import {Component, Input} from '@angular/core';
import {PayLoadService} from '../../../../../common/services/payload-all.service';

@Component({
  selector: 'review-for-submission-license-update-component',
  template: require('./body.html')
})

export class ReviewForSubmissionLicenseUpdateComponent {
  @Input() payLoadService: PayLoadService;
}
