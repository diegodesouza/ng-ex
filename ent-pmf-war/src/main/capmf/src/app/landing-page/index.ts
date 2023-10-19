/**
 * Created by AD94882 on 4/18/17.
 */
import { NgModule } from '@angular/core';
import { LandingPageComponent } from './component';
import { CommonAppModule } from '../common';
import { LandingPageSubCompModule } from './sub-components';
import {CommonModule} from '@angular/common';

@NgModule({
  imports: [
    CommonAppModule,
    LandingPageSubCompModule,
    CommonModule
  ],
  declarations: [
    LandingPageComponent,
  ],
  exports: [
    LandingPageComponent
  ]
})
export class LandingPageModule {}
