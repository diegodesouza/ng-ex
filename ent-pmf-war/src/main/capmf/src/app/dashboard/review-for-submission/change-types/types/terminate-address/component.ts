import {Component, Input} from '@angular/core';
import {PayLoadService} from '../../../../../common/services/payload-all.service';
import {PMFConfigService} from '../../../../../common/config/pmf-config.service';

@Component({
  selector: 'review-for-submission-terminate-address-component',
  template: require('./body.html')
})

export class ReviewForSubmissionTerminateAddressComponent {
  @Input() payLoadService: PayLoadService;
  @Input() heading: string;

  constructor(private configService: PMFConfigService) {}
}
