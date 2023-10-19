/**
 * Created by AD94882 on 5/31/17.
 */
import {Component, AfterViewInit, ViewChildren, QueryList, OnInit, DoCheck} from '@angular/core';
import {PayLoadService} from '../../../../common/services/payload-all.service';
import {AddAddress} from '../../../../common/models/add-address.model';
import {AcceptingNewPatients} from '../../../../common/models/accepting-new-patients.model';
import {PMFDataServiceDetails} from '../../../../common/config/pmf-data.service';
import {Address} from '../../../../common/models/address.model';
import {OperationHours} from '../../../../common/models/operation-hours.model';
import {DayOperation} from '../../../../common/models/day-operation.model';
import {LanguageSpoken} from '../../../../common/models/languages-spoken.model';
import {NgModel, NgForm} from '@angular/forms';
import {SelectUpdatesModel} from '../../../../common/config/select-updates.model';

@Component({
    selector: 'add-address',
    template: require('./body.html')
  }
)

export class AddAddressComponent implements OnInit, AfterViewInit, DoCheck {
  @ViewChildren(NgModel) controls: QueryList<NgModel>;
  controlsList: NgModel [];
  acceptingNewPatients: AcceptingNewPatients;
  operationHours: OperationHours;
  parentName: string = 'addAddress';
  address: Address;
  languagesSpoken: LanguageSpoken[];
  public options: Select2Options;

  constructor(
    private payLoadService: PayLoadService,
    private pmfDataService: PMFDataServiceDetails,
    private updates: SelectUpdatesModel,
    private parentForm: NgForm
  ) {
    if(!payLoadService.addAddress) {
      payLoadService.addAddress = new AddAddress();
    }
    if (!payLoadService.addAddress.physicalAddress.operationHours) {
      payLoadService.addAddress.physicalAddress.operationHours = new OperationHours();
      let i: number = 0;
      for (let i: number = 0; i < this.pmfDataService.JSON_DATA.field_mapping_options.daysOfTheWeek.fields.length; i++) {
        payLoadService.addAddress.physicalAddress.operationHours.daysOperationHours.push(new DayOperation(this.pmfDataService.JSON_DATA.field_mapping_options.daysOfTheWeek.fields[i].value));
      }
    }
  }

  ngOnInit() {
    this.options = {};
  }

  ngAfterViewInit() {
    this.updateParentForm();
  }

  ngDoCheck() {
    // Check if Controls Changed
    if(this.controls && (this.controlsList.length !== this.controls.length)) {
      this.updateParentForm();
    }
  }

  updateParentForm() {
    this.removeFromParentForm();
    this.addToParentForm();
  }

  addToParentForm() {
    if(this.controls) {
      this.controlsList = this.controls.toArray();
      this.controlsList.forEach((control: NgModel) => {
        this.parentForm.addControl(control);
      });
    }
  }

  removeFromParentForm() {
    if(this.controlsList) {
      this.controlsList.forEach((control: NgModel) => {
        this.parentForm.removeControl(control);
      });
    }
  }
}
