/**
 * Created by AF39842 on 5/25/23.
 */
import { Component } from '@angular/core';
import { DataService } from '../../common/config/data-constants';
import { PMFConfigService } from '../../common/config/pmf-config.service';

@Component({
    selector: 'landingpage-description-steps',
    template: require('./description-steps.html')
  }
)

export class DescriptionStepsComponent {
  constructor(public dataService: DataService, public configService: PMFConfigService) {
  }
}
