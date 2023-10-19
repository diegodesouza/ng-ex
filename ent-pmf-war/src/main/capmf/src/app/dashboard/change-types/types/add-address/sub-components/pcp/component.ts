import {Component, AfterViewInit, ViewChildren, QueryList, Input, DoCheck, OnDestroy} from '@angular/core';
import {NgModel, NgForm} from '@angular/forms';
import {PayLoadService} from '../../../../../../common/services/payload-all.service';
import {PMFDataServiceDetails} from '../../../../../../common/config/pmf-data.service';

@Component({
    selector: 'pcp-indicator',
    template: require('./body.html')
  }
)

export class PCPIndicatorComponent implements AfterViewInit, DoCheck, OnDestroy {
  @ViewChildren(NgModel) controls: QueryList<NgModel>;
  controlsList: NgModel [];
  @Input() parentName: string;
  @Input() payLoadService: PayLoadService;
  @Input() pmfDataService: PMFDataServiceDetails;

  constructor(
    private parentForm: NgForm,
  ) {}

  ngAfterViewInit() {
    this.updateParentForm();
  }

  ngDoCheck() {
    // Check if Controls Changed
    if(this.controls && (this.controlsList.length !== this.controls.length)) {
      this.updateParentForm();
    }
  }

  ngOnDestroy() {
    if(this.controls) {
      this.removeFromParentForm();
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
