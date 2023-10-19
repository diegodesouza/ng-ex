import {Component, Input, OnChanges, OnInit} from '@angular/core';
import {PayLoadService} from '../../../../../common/services/payload-all.service';

@Component({
  selector: 'review-for-submission-ambulance-component',
  template: require('./body.html')
})

export class ReviewForSubmissionAmbulanceComponent {
  @Input() payLoadService: PayLoadService;
}
