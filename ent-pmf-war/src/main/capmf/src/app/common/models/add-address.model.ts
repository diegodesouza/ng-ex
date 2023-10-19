/**
 * Created by AD94882 on 6/1/17.
 */

import {AcceptingNewPatients} from './accepting-new-patients.model';
import {Address} from './address.model';
import {OperationHours} from './operation-hours.model';
import {LanguageSpoken} from './languages-spoken.model';
import {HandicappedAccessibility} from './handicapped-accesibility.model';

export class AddAddress {
  public addressType: string;
  public correspondenceAddress: Address;
  public remitAddress: Address;
  public physicalAddress: PhysicalAddress;

  constructor() {
    this.addressType = '';
    this.correspondenceAddress = new Address();
    this.remitAddress = new Address();
    this.physicalAddress = new PhysicalAddress();
  }
}

export class PhysicalAddress {
  public addressType: string;
  public requestActionCd: string;
  public pcpIndicator: string;
  public physicianAssistant: string;
  public isCorrAddedToPracticeIndicator: string;
  public isCorrespondenceAddress: string;
  public isPublicTransportation: string;
  public isCorrespondenceSelected: boolean;
  public isRemittanceSelected: boolean;
  public address: Address;
  public acceptNewpatient: AcceptingNewPatients;
  public handicappedAccessibility: HandicappedAccessibility;
  public languagesSpoken: LanguageSpoken[] = [];
  public operationHours: OperationHours;
  public correspondenceAddress: Address;
  public remitAddress: Address;
  public serviceOffers: ServiceOffered[] = [];
  public specialty: string;

  constructor() {
    this.addressType = null;
    this.requestActionCd = null;
    this.pcpIndicator = null;
    this.physicianAssistant = null;
    this.isCorrAddedToPracticeIndicator = null;
    this.isCorrespondenceAddress = 'no';
    this.isPublicTransportation = 'yes';
    this.isCorrespondenceSelected = null;
    this.isRemittanceSelected = null;
    this.address = new Address();
    this.acceptNewpatient = new AcceptingNewPatients();
    this.handicappedAccessibility = new HandicappedAccessibility();
    this.correspondenceAddress = new Address();
    this.remitAddress = new Address();
    this.specialty = null;
  }
}

export class ServiceOffered {
  public servicesOfferedCd: string;
  public servicesOfferedDesc: string;

  constructor() {
    this.servicesOfferedDesc = null;
    this.servicesOfferedCd = 'A';
  }
}
