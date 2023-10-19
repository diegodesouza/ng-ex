/**
 * Created by AD94882 on 4/18/17.
 */
import { NgModule } from '@angular/core';
import { FooterComponent } from '../landing-page/sub-components/footer-component';
import { HeaderComponent } from './header-component';

@NgModule({
  imports: [],
  declarations: [
    FooterComponent,
    HeaderComponent
  ],
  exports: [
    FooterComponent,
    HeaderComponent
  ]
})
export class CommonAppModule {}
