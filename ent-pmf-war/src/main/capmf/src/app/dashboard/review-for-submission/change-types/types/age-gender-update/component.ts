import {Component, Input} from '@angular/core';
import {PayLoadService} from '../../../../../common/services/payload-all.service';

@Component({
  selector: 'review-for-submission-age-gender-update-component',
  template: require('./body.html')
})

export class ReviewForSubmissionAgeGenderUpdateComponent {
  @Input() payLoadService: PayLoadService;
}
