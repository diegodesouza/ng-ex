import {Component, Input} from '@angular/core';
import {PayLoadService} from '../../../../../common/services/payload-all.service';

@Component({
  selector: 'review-for-submission-npi-component',
  template: require('./body.html')
})

export class ReviewForSubmissionNPIComponent {
  @Input() payLoadService: PayLoadService;
}
