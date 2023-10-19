/**
 * Created by AC56833 on 5/16/2017.
 */

export class Specialty {
  public addNewRequest: string;
  public pcpIndicator: string;
  public providerType: string;
  public primarySpecialty: string;
  public additionalSpecialties: AdditionalSpecialty[];
  public specialityCd: string;
  public specialtyDesc: string;

  constructor() {
    this.addNewRequest = null;
    this.pcpIndicator = null;
    this.providerType = null;
    this.primarySpecialty = null;
    this.specialityCd = null;
    this.specialtyDesc = null;
    this.additionalSpecialties = [];
  }
}

export class AdditionalSpecialty {
  public specialty: string;

  constructor() {
    this.specialty = null;
  }
}
