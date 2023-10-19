export class UnsolicitedRoster {
  public requestActionCd: string;
  public numberOfProviders: number;
  public delegatedCredentialingEntity: string;

  constructor() {
    this.numberOfProviders = null;
    this.delegatedCredentialingEntity = '';
  }
}
