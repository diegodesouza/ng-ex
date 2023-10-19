/**
 * Created by AD94882 on 4/18/17.
 */
import { Component } from '@angular/core';
import { DataService } from '../../common/config/data-constants';
import { PMFConfigService } from '../../common/config/pmf-config.service';

@Component({
    selector: 'landingpage-description',
    template: require('./description.html')
  }
)

export class DescriptionComponent {
  constructor(public dataService: DataService, public configService: PMFConfigService) {
  }

  shouldDisplayAnnouncement(): boolean {
      return false;
  }
}
