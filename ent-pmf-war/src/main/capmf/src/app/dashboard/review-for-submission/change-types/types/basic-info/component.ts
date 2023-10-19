import {Component, Input} from '@angular/core';
import {PayLoadService} from '../../../../../common/services/payload-all.service';

@Component({
  selector: 'review-for-submission-basic-info-component',
  template: require('./body.html')
})

export class ReviewForSubmissionBasicInfoComponent {
  @Input() payLoadService: PayLoadService;
}
