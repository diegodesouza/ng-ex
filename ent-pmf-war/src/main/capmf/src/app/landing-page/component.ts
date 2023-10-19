/**
 * Created by AD94882 on 4/18/17.
 */

import { Component } from '@angular/core';
import { DataService } from '../common/config/data-constants';
import {PMFConfigService} from '../common/config/pmf-config.service';

@Component({
  selector: 'landing-page',
  template: require('./body.html'),
  styles:  [require('./css/landingpage.css')]
  }
)

export class LandingPageComponent {
  constructor(public dataService: DataService, private configService: PMFConfigService) {

  }

}
