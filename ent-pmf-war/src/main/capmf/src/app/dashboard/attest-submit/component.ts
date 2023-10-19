/**
 * Created by AD94882 on 4/18/17.
 */

import {AfterViewInit, Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {SelectUpdatesModel} from '../../common/config/select-updates.model';
import {PayLoadService} from '../../common/services/payload-all.service';
import {Individual} from '../../common/models/individual.model';
import {Organization} from '../../common/models/organization.model';
import {Provider} from '../../common/models/provider.model';
import {Contact} from '../../common/models/contact.model';
import {Address} from '../../common/models/address.model';
import {PMFConfigService} from '../../common/config/pmf-config.service';
import {ScrollService} from '../../common/services/scroll.service';

@Component({
    template: require('./body.html'),
    styles:   [require('./style.css')]
  }
)

export class AttestSubmitComponent implements OnInit, AfterViewInit {
  private responseStatus: string;
  private responseMessage: string;

  constructor(
    private scrollService: ScrollService,
    private updates: SelectUpdatesModel,
    private payLoadService: PayLoadService,
    private route: ActivatedRoute,
    private configService: PMFConfigService) {
  }

  ngOnInit(): void {
    this.responseMessage = this.payLoadService.responseMessage;
    this.responseStatus = this.payLoadService.responseStatus;
    this.initializePayLoadService();
  }

  ngAfterViewInit() {
    this.scrollService.scroll();
  }

  initializePayLoadService() {
    this.payLoadService.initializePayLoadService();
    this.payLoadService.individual = new Individual();
    this.payLoadService.organization = new Organization();
    this.payLoadService.provider = new Provider();
    this.payLoadService.contact = new Contact();
    this.payLoadService.addresses = new Address();
    this.payLoadService.targetSystems = this.configService.CONFIG.generalInfo.LOBS.fields;
    this.updates.resetSelectedUpdates();
    if(this.configService.getState() !== 'wv') {
      this.updates.resetSelectedNetworks();
    }
  }
}
