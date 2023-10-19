import {Component, Input} from '@angular/core';
import {PayLoadService} from '../../../../../common/services/payload-all.service';

@Component({
  selector: 'review-for-submission-phone-fax-component',
  template: require('./body.html')
})

export class ReviewForSubmissionPhoneFaxComponent {
  @Input() payLoadService: PayLoadService;
}
