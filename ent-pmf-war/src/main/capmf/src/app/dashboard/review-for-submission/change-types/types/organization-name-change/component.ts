import {Component, Input} from '@angular/core';
import {PayLoadService} from '../../../../../common/services/payload-all.service';

@Component({
  selector: 'review-for-submission-organization-name-change-component',
  template: require('./body.html')
})

export class ReviewForSubmissionOrganizationNameChangeComponent {
  @Input() payLoadService: PayLoadService;
}
