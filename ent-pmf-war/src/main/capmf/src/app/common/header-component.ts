/**
 * Created by AD94882 on 4/18/17.
 */
import {Component} from '@angular/core';
import {PMFConfigService} from './config/pmf-config.service';
require('./images/ABC_black_blue.png');
require('./images/logo_abcbs.png');
require('./images/unicare_wv.png');
require('./images/BCBSGA.png');
require('./images/ABCBS_Affiliate_HealthKeepers_Inc.png');

@Component({
  selector: 'app-header',
  template: require('./header.html'),
  styles: [require('./css/header.css')],
})
export class HeaderComponent {
  constructor(private configService: PMFConfigService) {}
}
