import {Component, Input, OnChanges, OnInit} from '@angular/core';
import {PayLoadService} from '../../../../../common/services/payload-all.service';

@Component({
  selector: 'review-for-submission-telehealth-component',
  template: require('./body.html')
})

export class ReviewForSubmissionTelehealthComponent {
  @Input() payLoadService: PayLoadService;
}
