/**
 * Created by AB74296 on 5/15/2017.
 */

import {Component, AfterViewInit, ViewChildren, QueryList, OnInit} from '@angular/core';
import {DataService} from '../../../../common/config/data-constants';
import {PMFDataServiceDetails} from '../../../../common/config/pmf-data.service';
import {PayLoadService} from '../../../../common/services/payload-all.service';
import {NgModel, NgForm} from '@angular/forms';
import {NetworkParticipation} from '../../../../common/models/network-participation.model';
import {PMFConfigService} from '../../../../common/config/pmf-config.service';

@Component({
    selector: 'network-participation',
    template: require('./body.html')
  }
)

export class NetworkParticipationComponent implements OnInit, AfterViewInit {
  @ViewChildren(NgModel) controls: QueryList<NgModel>;
  public options: Select2Options;

  constructor(
    private dataService: DataService,
    private pmfDataService: PMFDataServiceDetails,
    private payLoadService: PayLoadService,
    private parentForm: NgForm,
    private pmfConfigService: PMFConfigService
  ) {
    if(!payLoadService.networkParticipation) {
      payLoadService.networkParticipation = new NetworkParticipation();
    }
  }

  ngOnInit() {
    this.options = {};
  }

  ngAfterViewInit() {
    this.controls.forEach((control: NgModel) => {
      this.parentForm.addControl(control);
    });
  }
}
