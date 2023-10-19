import {Component, Input} from '@angular/core';
import {PayLoadService} from '../../../../../common/services/payload-all.service';

@Component({
  selector: 'review-for-submission-network-participation-component',
  template: require('./body.html')
})

export class ReviewForSubmissionNetworkParticipationComponent {
  @Input() payLoadService: PayLoadService;
}
