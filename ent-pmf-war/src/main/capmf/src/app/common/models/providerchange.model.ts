export class ProviderChange {
  public termReasonCd: string;
  public provFirstName: string;
  public provLastName: string;
  public provMiddleName: string;
  public provNpi: string;
  public pcpIndicator: string;

  constructor() {
    this.termReasonCd = null;
    this.provFirstName = null;
    this.provLastName = null;
    this.provMiddleName = null;
    this.provNpi = null;
    this.pcpIndicator = null;
  }
}
