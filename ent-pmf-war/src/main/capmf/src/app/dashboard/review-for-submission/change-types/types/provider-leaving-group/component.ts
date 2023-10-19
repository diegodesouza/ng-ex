import {Component, Input} from '@angular/core';
import {PayLoadService} from '../../../../../common/services/payload-all.service';

@Component({
  selector: 'review-for-submission-provider-leaving-group-component',
  template: require('./body.html')
})

export class ReviewForSubmissionProviderLeavingGroupComponent {
  @Input() payLoadService: PayLoadService;
}
