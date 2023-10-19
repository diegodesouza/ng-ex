import {Component, Input} from '@angular/core';
import {PayLoadService} from '../../../../../common/services/payload-all.service';

@Component({
  selector: 'review-for-submission-provider-specialty-component',
  template: require('./body.html')
})

export class ReviewForSubmissionProviderSpecialtyComponent {
  @Input() payLoadService: PayLoadService;
}
