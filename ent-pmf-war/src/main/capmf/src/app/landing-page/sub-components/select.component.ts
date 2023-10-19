/**
 * Created by AD94882 on 4/18/17.
 */
import {Component, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { PayLoadService } from '../../common/services/payload-all.service';
import { SelectUpdatesModel } from '../../common/config/select-updates.model';
import {ModalComponent} from '../../uxd/v2/ux/components/modalCmp';
import {DataService} from '../../common/config/data-constants';
import {PMFConfigService} from '../../common/config/pmf-config.service';

@Component({
        selector: 'landingpage-select',
        template: require('./select.html'),
        styles: [require('../css/landingpage.css')]
    }
)

export class SelectComponent {

  @ViewChild('selectInfoModal') selectInfoModal: ModalComponent;

  startBlockExpanded: boolean = false;

  constructor(
    public dataService: DataService,
    private userTypeService: PayLoadService,
    private selectUpdatesModel: SelectUpdatesModel,
    private router: Router,
    private configService: PMFConfigService
  ) {}

  next() {
    if(this.userTypeService.previousUserType > -1
      && this.userTypeService.getUserType() !== this.userTypeService.previousUserType) {
      this.openModal();
    } else {
      this.routeToNext();
    }
  }

  routeToNext() {
    this.userTypeService.previousUserType = this.userTypeService.getUserType();
    this.router.navigate(['dashboard/generalinfo'], {skipLocationChange: true});
  }

  openModal() {
    this.selectInfoModal.show();
  }

  continue() {
    this.userTypeService.initializePayLoadService();
    this.selectUpdatesModel.resetSelectedUpdates();
    if(this.configService.getState() !== 'wv') {
      this.selectUpdatesModel.resetSelectedNetworks();
    }
    this.selectInfoModal.handleClose();
    this.routeToNext();
  }

  cancel() {
    this.userTypeService.setUserType(this.userTypeService.previousUserType);
    this.selectInfoModal.handleClose();
  }

  toggleStartBlockExpanded() {
    this.startBlockExpanded = !this.startBlockExpanded;
  }

  handleChange(key, value) {
    if (!key && !value) {
      return;
    }
    this[key] = value;
  }
}
