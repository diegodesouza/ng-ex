/**
 * Created by AD94882 on 4/18/17.
 */
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DescriptionComponent } from './description.component';
import { DescriptionStepsComponent } from './description-steps.component';
import { HeaderComponent } from './header.component';
import { SelectComponent } from './select.component';
import { UxModule } from '../../uxd/v2/ux/uxModule';
import { RouterModule } from '@angular/router';
import { PayLoadService } from '../../common/services/payload-all.service';
import { ImportantNoticeComponent } from './important-notice.component';

@NgModule({
  imports: [
    UxModule,
    RouterModule,
    CommonModule
  ],
  declarations: [
    DescriptionComponent,
    DescriptionStepsComponent,
    ImportantNoticeComponent,
    HeaderComponent,
    SelectComponent
  ],
  exports: [
    DescriptionComponent,
    DescriptionStepsComponent,
    ImportantNoticeComponent,
    HeaderComponent,
    SelectComponent
  ],
  providers: [PayLoadService]
})

export class LandingPageSubCompModule {}
