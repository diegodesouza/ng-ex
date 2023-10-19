/**
 * Created by AC56833 on 5/22/2017.
 */

import {
    Component, AfterViewInit, ViewChildren, QueryList, Input, OnInit, OnDestroy
} from '@angular/core';
import {DataService} from '../../../../common/config/data-constants';
import {PMFDataServiceDetails} from '../../../../common/config/pmf-data.service';
import {LanguageSpoken} from '../../../../common/models/languages-spoken.model';
import {NgModel, NgForm} from '@angular/forms';
import {PayLoadService} from '../../../../common/services/payload-all.service';

@Component({
    selector: 'languages-spoken',
    template: require('./body.html'),
    styles: [require('../../../../common/css/pmf-main.css')]
  }
)

export class LanguagesSpokenComponent implements AfterViewInit, OnInit, OnDestroy {
  @Input() languagesSpoken: LanguageSpoken[];
  @Input() parentName: string;
  @ViewChildren(NgModel) controls: QueryList<NgModel>;
  controlsList: NgModel [] = [];
  public options: Select2Options;
  // This is a patch to a bug with uxd where the toggleId and our Index get out of sync
  public index: number = -1;
  public indexArray: number [] = [];

  constructor(
    private dataService: DataService,
    private pmfDataService: PMFDataServiceDetails,
    private payLoadService: PayLoadService,
    private parentForm: NgForm
  ) {}

  ngOnInit(): void {
    if (this.languagesSpoken.length === 0) {
      this.languagesSpoken.push(
        new LanguageSpoken()
      );
    }

    this.options = {};
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

  addLanguage() {
    this.index++;
    this.indexArray.push(this.index);
    this.languagesSpoken.push(
      new LanguageSpoken()
    );
  }

  removeLanguage(index) {
    this.indexArray.slice(index, 1);
    this.languagesSpoken.splice(index, 1);
    this.removeFromParentForm();
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
}
