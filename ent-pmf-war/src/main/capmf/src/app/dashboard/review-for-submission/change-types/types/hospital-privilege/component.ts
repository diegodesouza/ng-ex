import {Component, Input} from '@angular/core';
import {PayLoadService} from '../../../../../common/services/payload-all.service';

@Component({
  selector: 'review-for-submission-hospital-privilege-component',
  template: require('./body.html')
})

export class ReviewForSubmissionHospitalPrivilegeComponent {
  @Input() payLoadService: PayLoadService;
}
