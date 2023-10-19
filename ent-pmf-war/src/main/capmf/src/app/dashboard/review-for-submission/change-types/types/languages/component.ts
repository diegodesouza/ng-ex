import {Component, Input} from '@angular/core';
import {PayLoadService} from '../../../../../common/services/payload-all.service';

@Component({
  selector: 'review-for-submission-languages-component',
  template: require('./body.html')
})

export class ReviewForSubmissionLanguagesComponent {
  @Input() payLoadService: PayLoadService;
}
