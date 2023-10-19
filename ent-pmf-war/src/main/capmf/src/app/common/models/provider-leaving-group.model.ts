/**
 * Created by AF39997 on 6/6/2017.
 */

import {ProviderChange} from './providerchange.model';

export class ProviderLeavingGroup {
    public providerChange: ProviderChange;

    constructor() {
        this.providerChange = new ProviderChange();
    }
}
