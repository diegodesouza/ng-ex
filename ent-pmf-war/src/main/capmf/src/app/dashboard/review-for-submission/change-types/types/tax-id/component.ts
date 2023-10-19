import {Component, Input} from '@angular/core';
import {PayLoadService} from '../../../../../common/services/payload-all.service';

@Component({
  selector: 'review-for-submission-tax-id-component',
  template: require('./body.html')
})

export class ReviewForSubmissionTaxIdComponent {
  @Input() payLoadService: PayLoadService;

  constructor(private _payLoadService: PayLoadService) {}
}
