/**
 * Created by AD94882 on 5/23/17.
 */
export class ProviderDetails {
    public effectiveDate: Date;
    public individualNPI: string;
    public title: string;
    public lastName: string;
    public firstName: string;
    public middleName: string;
    public suffix: string;
    public licenseNumber: string;

    constructor() {
        this.effectiveDate = null;
        this.individualNPI = null;
        this.title = null;
        this.lastName = null;
        this.firstName = null;
        this.middleName = null;
        this.suffix = null;
        this.licenseNumber = null;
    }
}
