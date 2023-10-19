/**
 * Created by AD94882 on 5/8/17.
 */

import {JsonProperty} from 'json2typescript';

export class Provider {
    @JsonProperty('effectiveDate')
    effectiveDate: Date;
    public npi: string;
    public practiceDBA: string;
    public exempt: boolean;

    constructor() {
        this.effectiveDate = null;
        this.npi = null;
        this.practiceDBA = null;
        this.exempt = null;
    }
}
