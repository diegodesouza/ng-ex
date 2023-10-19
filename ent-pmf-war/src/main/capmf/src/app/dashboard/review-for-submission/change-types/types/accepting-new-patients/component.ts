import {Component, Input, OnChanges, OnInit} from '@angular/core';
import {PayLoadService} from '../../../../../common/services/payload-all.service';

@Component({
  selector: 'review-for-submission-accepting-new-patients-component',
  template: require('./body.html')
})

export class ReviewForSubmissionAcceptingNewPatientsComponent {
  @Input() payLoadService: PayLoadService;
}
