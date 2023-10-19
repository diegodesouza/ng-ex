import { Component, OnInit } from '@angular/core';

//istanbul ignore next
@Component({
  moduleId: module.id,
  selector: '[data-uxd-ajax-loader-cmp]',
  template: `<div>
                  <div class="ant-ui-widget-overlay"></div>
                  <div class="ant-ajax-load" >
                    <div class="sk-spinner sk-spinner-wave ant-ajax-spin">
                      <div class="sk-rect1"></div>
                      <div class="sk-rect2"></div>
                      <div class="sk-rect3"></div>
                      <div class="sk-rect4"></div>
                      <div class="sk-rect5"></div>
                    </div>
                    <ng-content class="ant-ajax-text"></ng-content>
                  </div>
                </div>`
})
export class AjaxLoaderComponent implements OnInit {

  ngOnInit() {
    // init
  }

}
