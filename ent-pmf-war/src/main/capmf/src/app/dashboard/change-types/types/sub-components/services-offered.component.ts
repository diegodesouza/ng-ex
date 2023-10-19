/**
 * Created by AD94882 on 5/8/17.
 */
import {Input, Component, OnInit, ViewChildren, QueryList, AfterViewInit, OnDestroy} from '@angular/core';
import {PMFDataServiceDetails} from '../../../../common/config/pmf-data.service';
import {NgForm, NgModel} from '@angular/forms';
import {FormValidations} from '../../../../common/validations/form-validations';
import {ServiceOffered} from '../../../../common/models/add-address.model';

@Component({
    selector: 'services-offered',
    template: require('./services-offered.html'),
    styles: [require('../../../../common/css/pmf-main.css')]
  }
)

export class ServicesOfferedComponent implements OnInit, AfterViewInit, OnDestroy {
  @ViewChildren(NgModel) controls: QueryList<NgModel>;
  controlsList: NgModel [] = [];
  @Input() servicesOffers: ServiceOffered[];
  @Input() parentName: string;
  public options: Select2Options;

  constructor(
    private pmfDataService: PMFDataServiceDetails,
    private parentForm: NgForm,
    private formValidations: FormValidations
  ) {}

  ngOnInit(): void {
    this.options = {};

    if (this.servicesOffers.length === 0) {
      this.servicesOffers.push(
        new ServiceOffered()
      );
    }
  }

  ngAfterViewInit() {
    this.updateParentForm();
    if(this.controls) {
      this.controls.changes.subscribe((data) => this.updateFormOnChange(data));
    }
  }

  ngOnDestroy() {
    if(this.controls) {
      this.removeFromParentForm();
    }
  }

  updateFormOnChange(data?: any) {
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

  addServices(){
    this.servicesOffers.push(
      new ServiceOffered()
    );
  }

  removeServicesOffered(index) {
    this.servicesOffers.splice(index, 1);
    this.removeFromParentForm();
  }

}
