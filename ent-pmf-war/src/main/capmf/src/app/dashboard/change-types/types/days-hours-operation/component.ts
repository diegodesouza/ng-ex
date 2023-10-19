/**
 * Created by AC56833 on 6/1/2017.
 */
import {Input, Component, OnInit} from '@angular/core';
import { OperationHours } from '../../../../common/models/operation-hours.model';
import {DataService} from '../../../../common/config/data-constants';
import {PMFDataServiceDetails} from '../../../../common/config/pmf-data.service';

@Component({
        selector: 'operation-hours',
        template: require('./body.html')
    }
)

export class DaysHoursOperationComponent implements OnInit {
    @Input() operationHours: OperationHours;
    @Input() parentName: string;
    public options: Select2Options;

    constructor(private dataService: DataService, private pmfDataService: PMFDataServiceDetails) {
    }

    ngOnInit() {
      this.options = {};
    }
}
