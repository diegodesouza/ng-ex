import { Component, OnInit } from '@angular/core';

//istanbul ignore next
@Component({
  moduleId: module.id,
  selector: '[data-uxd-data-loader-cmp]',
  template: `<div>
                <div class="ant-data-load" >
                  <div class="sk-spinner sk-spinner-wave ant-data-spin">
                    <div class="sk-rect1"></div>
                    <div class="sk-rect2"></div>
                    <div class="sk-rect3"></div>
                    <div class="sk-rect4"></div>
                    <div class="sk-rect5"></div>
                  </div>
                  <div class="ant-data-text"><ng-content></ng-content></div>
                </div>
              </div>`
})
export class DateLoaderComponent implements OnInit {

  ngOnInit() {
    // init
  }

}
