/**
 * Created by AF39997 on 6/6/2017.
 */

export class WebAddress {
    public addWebAddress: boolean = true;
    public requestActionCd: string;
    public webAddress: string;

    constructor() {
        this.requestActionCd = null;
        this.webAddress = null;
        this.addWebAddress = true;
    }
}
