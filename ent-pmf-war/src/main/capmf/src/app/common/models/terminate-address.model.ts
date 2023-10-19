/**
 * Created by AF39997 on 6/6/17.
 */

export class TerminateAddress {
  public requestActionCd: string;
  public addressType: string;
  public tterminationDate: Date;
  public termReason: string;
  public adrSeqNo: string;
  public pcpIndicator: string;

  constructor() {
    this.requestActionCd = null;
    this.addressType = null;
    this.tterminationDate = null;
    this.termReason = null;
    this.adrSeqNo = null;
    this.pcpIndicator = null;
  }
}
