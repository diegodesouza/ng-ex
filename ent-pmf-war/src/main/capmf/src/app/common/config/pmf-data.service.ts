/**
 * Created by AB74296 on 4/25/2017.
 */

import { Injectable } from '@angular/core';
import { PMF_DATA } from './pmf-data-mapping';

@Injectable()
export class PMFDataServiceDetails {
    public readonly JSON_DATA: any;

    constructor() {
        this.JSON_DATA = PMF_DATA;
    }

    public getSortedDropdownValues(values: Array<object>): Array<object> {
        let sortedValues = values.sort((n1, n2) => {
            if (n1['text'] > n2['text']) {
                return 1;
            }
            if (n1['text'] < n2['text']) {
                return -1;
            }
            return 0;
        });
        return sortedValues;
    }
}
