/**
 * Created by AD94882 on 5/11/17.
 */

import {Component} from '@angular/core';
import {PayLoadService} from '../../../common/services/payload-all.service';

@Component({
  template: require('./body.html'),
  styles: [require('./styles.css')]
})

export class UserReferenceComponent {
  constructor(private payLoadService: PayLoadService) {
  }
}
