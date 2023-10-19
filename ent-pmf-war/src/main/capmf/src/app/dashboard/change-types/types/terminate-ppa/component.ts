/**
 * Created by AC56833 on 5/17/2017.
 */
import {Component, AfterViewInit, ViewChildren, QueryList, Input, OnInit, DoCheck} from '@angular/core';
import {PayLoadService} from '../../../../common/services/payload-all.service';
import {DataService} from '../../../../common/config/data-constants';
import {NgModel, NgForm} from '@angular/forms';
import {FormValidations} from '../../../../common/validations/form-validations';
import {PMFDataServiceDetails} from '../../../../common/config/pmf-data.service';
import {TerminatePPA} from '../../../../common/models/terminate-ppa.model';

@Component({
    selector: 'terminate-ppa',
    template: require('./body.html'),
    styles: [require('./styles.css'), require('../../../../common/css/pmf-main.css')]
  }
)

export class TerminatePPAComponent implements AfterViewInit, OnInit, DoCheck {
  @ViewChildren(NgModel) controls: QueryList<NgModel>;
  controlsList: NgModel [];
  @Input() parentName: string;
  public options: Select2Options;

  constructor(
    private payloadService: PayLoadService,
    private dataService: DataService,
    private pmfDataService: PMFDataServiceDetails,
    private parentForm: NgForm,
    private formValidations: FormValidations
  ) {
    if(!payloadService.terminatePPA) {
      payloadService.terminatePPA = new TerminatePPA();
    }
  }

  ngOnInit() {
    this.options = {};
    this.formValidations.registerValidationMessages(this.validationMessages);
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

  validationMessages = {
    'terminationDate': {
      'required': 'Termination Date is required.',
      'pattern':  'Termination Date is invalid.'
    }
  };
}
