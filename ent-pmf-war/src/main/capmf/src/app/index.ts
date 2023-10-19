import 'reflect-metadata';
require('jquery-ui-bundle');
import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {routing, RootComponent} from './routes';
import {DatePipe} from '@angular/common';
import {CommonAppModule} from './common';
import {LandingPageModule} from './landing-page';
import {DashboardModule} from './dashboard';
import './uxd/assets/styles/main.css';
import '../../node_modules/select2/dist/js/select2.full.js';
import {UxModule} from './uxd/v2/ux/uxModule';
import {DataService} from './common/config/data-constants';
import {PayLoadService} from './common/services/payload-all.service';
import {PMFDataServiceDetails} from './common/config/pmf-data.service';
import {SelectUpdatesModel} from './common/config/select-updates.model';
import {Select2Module} from 'ng2-select2';
import {UxHelper} from './uxd/v2/ux/services/uxHelper';
import {PMFConfigService} from './common/config/pmf-config.service';
import {CanActivatePMFGuard} from './common/gaurds/activate-pmf-gaurd';
import {CanActivateLandingPageGuard} from './common/gaurds/activate-landingpage-gaurd';

@NgModule({
  imports: [
    BrowserModule,
    HttpModule,
    routing,
    UxModule,
    CommonAppModule,
    LandingPageModule,
    DashboardModule,
    CommonModule,
    FormsModule,
    Select2Module
  ],
  declarations: [RootComponent],
  providers: [
    DataService,
    PayLoadService,
    PMFDataServiceDetails,
    PMFConfigService,
    SelectUpdatesModel,
    DatePipe,
    UxHelper,
    CanActivatePMFGuard,
    CanActivateLandingPageGuard,
  ],
  bootstrap: [RootComponent]
})
export class AppModule {}
