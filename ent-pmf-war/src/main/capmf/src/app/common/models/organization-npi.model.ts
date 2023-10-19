/**
 * Created by AC56833 on 6/2/2017.
 */

export class OrganizationNpi {
  public requestType: string;
  public orgNpi: string;
  public streetAddress1: string;
  public streetAddress2: string;
  public city: string;
  public state: string;
  public zipCode: string;
  public county: string;

  constructor() {
    this.requestType = 'A';
    this.orgNpi = null;
    this.streetAddress1 = null;
    this.streetAddress2 = null;
    this.city = null;
    this.state = null;
    this.zipCode = null;
    this.county = null;
  }
}
