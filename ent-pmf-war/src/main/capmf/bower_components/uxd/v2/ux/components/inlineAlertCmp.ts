import { Attribute, Component, OnInit } from '@angular/core';

//istanbul ignore next
@Component({
  moduleId: module.id,
  selector: '[data-uxd-inline-alert-cmp]',
  template: `<p class="ant-inline-alert"> <ng-content></ng-content> </p>`
})
export class InlineAlertComponent implements OnInit {

  ngOnInit() {
    // init
  }

}
