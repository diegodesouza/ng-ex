/**
 * Created by AD94882 on 4/18/17.
 */
import {Component} from '@angular/core';
import {PMFConfigService} from '../../common/config/pmf-config.service';

@Component({
  selector: 'app-footer',
  template: require('./footer.html')
})
export class FooterComponent {
  constructor(private configService: PMFConfigService) {}
}
