///<reference path='../../common/models/payload-output/payload-pega.model.ts'/>
/**
 * Created by AD94882 on 4/18/17.
 */

import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {Router} from '@angular/router';
import {SelectUpdatesModel} from '../../common/config/select-updates.model';
import {FormValidations} from '../../common/validations/form-validations';
import {NgForm} from '@angular/forms';
import {PayLoadService} from '../../common/services/payload-all.service';
import {PMFDataServiceDetails} from '../../common/config/pmf-data.service';
import {OperationHours} from '../../common/models/operation-hours.model';
import {DayOperation} from '../../common/models/day-operation.model';
import {ScrollService} from '../../common/services/scroll.service';

@Component({
    template: require('./body.html'),
    styles: [require('./styles.css')]
  }
)

export class ChangeTypesComponent implements OnInit, AfterViewInit {
  @ViewChild('changeTypesForm') changeTypesForm: NgForm;
  private isAttachmentRequired: boolean;

  constructor(
    private scrollService: ScrollService,
    private formValidations: FormValidations,
    private router: Router,
    private payLoadService: PayLoadService,
    private pmfDataService: PMFDataServiceDetails,
    private updates: SelectUpdatesModel
  ) {
    if (!payLoadService.operationHours) {
      payLoadService.operationHours = new OperationHours();
      for (let i: number = 0; i < this.pmfDataService.JSON_DATA.field_mapping_options.daysOfTheWeek.fields.length; i++) {
        payLoadService.operationHours.daysOperationHours.push(new DayOperation(this.pmfDataService.JSON_DATA.field_mapping_options.daysOfTheWeek.fields[i].value));
      }
    }

  }

  ngOnInit() {
    this.changeTypesForm.name = 'changeTypesForm';
    this.formValidations.subscribeForm(this.changeTypesForm);
    this.updates.updateRequiredAttachment();
  }

  ngAfterViewInit() {
    this.scrollService.scrollToTop();
  }

  checkCorrespondenceAndRemittance() {
    if(this.payLoadService.getUserType() === 1 && (this.payLoadService && this.payLoadService.addAddress && this.payLoadService.addAddress.addressType === 'Practice Location')) {
      if(this.payLoadService.addAddress.physicalAddress.isCorrAddedToPracticeIndicator === 'yes') {
        if (this.payLoadService.addAddress.physicalAddress.isCorrespondenceSelected || this.payLoadService.addAddress.physicalAddress.isRemittanceSelected) {
          return false;
        } else {
          return true;
        }
      }
    }
  }

  checkPCPIndicator() {
    if((this.payLoadService && this.payLoadService.terminateAddress) && this.payLoadService.getUserType() === 0 && this.payLoadService.terminateAddress.addressType === 'Practice Location') {
      if(this.payLoadService.terminateAddress.pcpIndicator) {
        return false;
      } else {
        return true;
      }
    }
  }

  reviewForSubmission() {
    if(!this.payLoadService.isAttachmentRequired || this.payLoadService.attachments.length > 0) {
      this.routeToNext();
    }
  }

  routeToNext() {
    this.router.navigate(['dashboard/reviewforsubmission'], {skipLocationChange: true});
  }

  checkForAttachment() {
    if(this.payLoadService.isAttachmentRequired) {
      this.isAttachmentRequired = true;
      this.reviewForSubmission();
    } else {
      this.isAttachmentRequired = false;
      this.reviewForSubmission();
    }
  }
}
