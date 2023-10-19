import {Component, Input} from '@angular/core';
import {PayLoadService} from '../../../../../common/services/payload-all.service';

@Component({
  selector: 'review-for-submission-attachment-component',
  template: require('./body.html')
})

export class ReviewForSubmissionAttachmentComponent {
  @Input() payLoadService: PayLoadService;
}
