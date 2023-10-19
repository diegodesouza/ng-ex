/**
 * Created by AD94882 on 5/8/17.
 */

export class Address {
    public streetAddress1: string;
    public streetAddress2: string;
    public city: string;
    public state: string;
    public zipCode: string;
    public county: string;
    public phone: string;
    public fax: string;
    public email: string;

    constructor() {
        this.streetAddress1 = null;
        this.streetAddress2 = null;
        this.city = null;
        this.state = null;
        this.zipCode = null;
        this.county = null;
        this.phone = null;
        this.fax = null;
        this.email = null;
    }
}
