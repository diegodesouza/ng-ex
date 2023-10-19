/**
 * Created by AC56833 on 4/27/17.
 */

import { Injectable } from '@angular/core';
import { PayLoadService } from '../services/payload-all.service';
import {PMFConfigService} from './pmf-config.service';

@Injectable()
export class SelectUpdatesModel {

  constructor(private payLoadService: PayLoadService,  private configService: PMFConfigService) {
  }

  // get the count of selected changes
  public getSelectedUpdatesCount(): number {
    return this.configService.CONFIG.selectUpdates.updates.filter((x) => (x.isSelected === true)).length;
  }

  // get list of selected updates
  public getAllSelectedUpdates() {
    return this.configService.CONFIG.selectUpdates.updates.filter((x) => (x.isSelected === true ));
  }
  // get the selected change type based on the route
  public getSelectedUpdates(route: string) {
    let selectedUpdates = this.configService.CONFIG.selectUpdates.updates.filter((x) => (x.isSelected === true && x.route === route));

    return selectedUpdates;
  }

  // get the selected change type based on the route
  public getSelectedUpdatesById(id: string) {
    let selectedUpdates = this.configService.CONFIG.selectUpdates.updates.filter((x) => (x.isSelected === true && x.id === id));

    return selectedUpdates;
  }

  // get the sorted and filtered updates
  public getSortedFilteredUpdates(userType: string) {
    let filteredUpdates = this.configService.CONFIG.selectUpdates.updates.filter((x) => (x.appliesTo === userType || x.appliesTo === 'all'));
    let sortedUpdates = filteredUpdates.sort((n1, n2) => {
      if (n1.title > n2.title) {
        return 1;
      }
      if (n1.title < n2.title) {
        return -1;
      }
      return 0;
    });
    return sortedUpdates;
  }

  // get the selected updates by filtering on the attachments.
  public updateSelectedUpdatesAttachment() {
    this.payLoadService.isAttachmentRequired = this.configService.CONFIG.selectUpdates.updates.filter((x) =>
      (x.isSelected === true && x.attachments === true)).length > 0;
  }

  public updateRequiredAttachment() {
    this.updateSelectedUpdatesAttachment();
    this.updateAttachmentStatus();
  }

  public updateAttachmentStatus() {
    this.payLoadService.isAttachment = !this.payLoadService.isAttachmentRequired || this.payLoadService.attachments.length > 0;
  }

  // Resetting the selected updates.
  public resetSelectedUpdates() {
    let selectedUpdates = this.configService.CONFIG.selectUpdates.updates;
    for (let updates in selectedUpdates) {
      if(selectedUpdates.hasOwnProperty(updates)) {
        selectedUpdates[updates].isSelected = false;
      }
    }
  }

  // Resetting the selected networks.
  public resetSelectedNetworks() {
    let selectedNetworks = this.configService.CONFIG.generalInfo.LOBS.fields;
    for (let network in selectedNetworks) {
      if(selectedNetworks.hasOwnProperty(network)) {
        selectedNetworks[network].checked = false;
      }
    }
  }
}
