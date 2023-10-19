export class FormSectionsUpdated {
  actionCode: string[];
}

export class Attachment {
  documentContentKey: string;
  fileName: string;
  fileSize: string;
  fileType: string;
  fileComments: string;
}

export class ProviderDetails {
  effectiveDate: string;
  individualNPI: string;
  individualTaxId: string;
  title: string;
  lastName: string;
  firstName: string;
  middleName: string;
  suffix: string;
  licenseNumber: string;
}

export class PracticeAddress {
  addressLine1: string;
  addressLine2: string;
  city: string;
  state: string;
  zip: string;
  county: string;
  adrSeqNo: string;
}

export class ContactInfo {
  firstName: string;
  lastName: string;
  phoneNumber: string;
  email: string;
}

export class GeneralInformationIndividualProvider {
  providerDetails: ProviderDetails;
  practiceAddress: PracticeAddress[];
  contactInfo: ContactInfo;
}

export class OrganizationDetails {
  effectiveDate: string;
  organizationTaxId: string;
  organizationName: string;
  organizationLicenseNumber: string;
  organizationNPI: string;
  dbaName: string;
}

export class PracticeAddress2 {
  addressLine1: string;
  addressLine2: string;
  city: string;
  state: string;
  zip: string;
  county: string;
  adrSeqNo: string;
}

export class ContactInfo2 {
  firstName: string;
  lastName: string;
  phoneNumber: string;
  email: string;
}

export class GeneralInformationOrganizationProvider {
  organizationDetails: OrganizationDetails;
  practiceAddress: PracticeAddress2[];
  contactInfo: ContactInfo2;
}

export class UpdateLanguage {
  language: Language[] = [];
}

export class Language {
  requestActionCd: string;
  langCd: string;
  langDesc: string;
  adrSeqNo: string;
}

export class ProviderSpecialty {
  primaryIndicator: string;
  specialtyCd: string;
  specialtyDesc: string;
}

export class AcceptingPatientInfo {
  acceptingNewPatientsIndicator: string;
  ageCriteriaCd: string;
  ageCriteriaDesc: string;
  genderPreferenceCd: string;
  genderPreferenceDesc: string;
  adrSeqNo: string;
}

export class UpdateAcceptingNewPatients {
  requestActionCd: string;
  providerSpecialty: ProviderSpecialty[];
  lobCd: string[];
  acceptingPatientInfo: AcceptingPatientInfo;
}

export class UpdateProviderName {
  requestActionCd: string;
  updatedFirstName: string;
  updatedLastName: string;
  updatedMiddleName: string;
  updatedSuffix: string;
  updatedTitle: string;
  updatedLicenseNumber: string;
}

export class Privilege {
  requestActionCd: string;
  hospitalPrivilegeCd: string;
  hospitalPrivilegeDesc: string;
  hospitalName: string;
}

export class UpdateHospitalAffiliation {
  privilege: Privilege[];
}

export class HandicappedAccessibility {
  handaccCd: string;
  handaccdesc: string;
}

export class ProviderLanguage {
  langCd: string;
  langDesc: string;
}

export class OfficeHour {
  dayofTheWeek: string;
  openHour: string;
  closedHour: string;
  scheduleTypeCd: string;
  scheduleTypeDesc: string;
  openIndicator: string;
  timeZoneCd: string;
  timeZoneDesc: string;
}

export class ServicesOffered {
  servicesOfferedCd: string;
  servicesOfferedDesc: string;
}

export class PracticeAddress3 {
  addressLine1: string;
  addressLine2: string;
  city: string;
  state: string;
  zip: string;
  county: string;
  faxNumber: string;
  phoneNumber: string;
  email: string;
  checkName: string;
  acceptingPatientsIndicator: string;
  acceptingPatientInfo: AcceptingPatientInfo = new AcceptingPatientInfo();
  handicappedAccessibility: HandicappedAccessibility = new HandicappedAccessibility();
  publicTransportationAccessibleIndicator: string;
  providerLanguage: ProviderLanguage[] = [];
  officeHours: OfficeHour[] = [];
  servicesOffered: ServicesOffered[] = [];
  pacorrespondenceAddress: CorrespondenceAddress = new CorrespondenceAddress();
  paremitAddress: RemitAddress = new RemitAddress();
  isCorrAddedToPracticeIndicator: string;
}

export class RemitAddress {
  addressLine1: string;
  addressLine2: string;
  city: string;
  state: string;
  zip: string;
  county: string;
  faxNumber: string;
  phoneNumber: string;
  email: string;
}

export class CorrespondenceAddress {
  addressLine1: string;
  addressLine2: string;
  city: string;
  state: string;
  zip: string;
  county: string;
  faxNumber: string;
  phoneNumber: string;
  email: string;
}

export class AddAddress {
  requestActionCd: string;
  pcpIndicator: string;
  addressType: string;
  practiceAddress: PracticeAddress3 = new PracticeAddress3();
  remitAddress: RemitAddress = new RemitAddress();
  correspondenceAddress: CorrespondenceAddress = new CorrespondenceAddress();
}

export class PhoneNumber {
  requestActionCd: string;
  phoneNumber: string;
  phoneTypeCd: string;
  phoneTypeDesc: string;
  adrSeqNo: string;
}

export class UpdatePhoneNumber {
  phoneNumber: PhoneNumber[] = [];
}

export class AddLicenseNumber {
  requestActionCd: string;
  licenseNumber: string;
  issuingState: string;
}

export class Network {
  requestActionCd: string;
  lobCd: string;
}

export class AddChangeNetwork {
  network: Network[];
}

export class Expertise {
  requestActionCd: string;
  expertiseCd: string;
  expertiseDesc: string;
}

export class AddChangeAreasofExpertise {
  expertise: Expertise[];
}

export class Email {
  requestActionCd: string;
  adrSeqNo: string;
  email: string;
}

export class AddChangeEmailAddress {
  email: Email[];
}

export class ProviderSpecialty2 {
  specialtyCd: string;
  specialtyDesc: string;
  primaryIndicator: string;
}

export class AddChangeProviderSpecialty {
  requestActionCd: string;
  providerSpecialty: ProviderSpecialty2[];
  pcpindicator: string;
}

export class UpdateAgeGenderPreference {
  requestActionCd: string;
  minage: string;
  maxage: string;
  genderCd: string;
  genderDesc: string;
}

export class OfficeHour2 {
  dayofTheWeek: string;
  openHour: string;
  closedHour: string;
  scheduleTypeCd: string;
  scheduleTypeDesc: string;
  openIndicator: string;
  timeZoneCd: string;
  timeZoneDesc: string;
}

export class UpdateOfficeHours {
  requestActionCd: string;
  officeHours: OfficeHour2[];
}

export class UpdateTaxId {
  requestActionCd: string;
  npi: string;
  newTaxId: string;
}

export class TransfertoProviderSpecialty {
  specialtyCd: string;
  specialtyDesc: string;
}

export class TransfertoPracticeAddress {
  addressLine1: string;
  addressLine2: string;
  city: string;
  state: string;
  zip: string;
  county: string;
  adrSeqNo: string;
}

export class TermProviderParticipation {
  requestActionCd: string;
  managedCarePcpIndicator: string;
  pcpIndicator: string;
  termDate: string;
  termReasonCd: string;
  termReasonDesc: string;
}

export class RemitAddress2 {
  addressLine1: string;
  addressLine2: string;
  city: string;
  state: string;
  zip: string;
  county: string;
  adrSeqNo: string;
}

export class AddChangeNpi {
  requestActionCd: string;
  remitAddress: RemitAddress2;
  newNpi: string;
}

export class HandAcc {
  requestActionCd: string;
  handaccCd: string;
  handaccdesc: string;
}

export class AddChangeHandicappedAccessibility {
  handAcc: HandAcc[];
}

export class TransfertoProviderSpecialty2 {
  specialtyCd: string;
  specialtyDesc: string;
}

export class TransfertoPracticeAddress2 {
  addressLine1: string;
  addressLine2: string;
  city: string;
  state: string;
  zip: string;
  county: string;
}

export class ProviderLeavingGroup {
  requestActionCd: string;
  pcpIndicator: string;
  termDate: string;
  termReasonCd: string;
  termReasonDesc: string;
  provFirstName: string;
  provLastName: string;
  provMiddleName: string;
  provNpi: string;
}

export class TransfertoProviderSpecialty3 {
  specialtyCd: string;
  specialtyDesc: string;
}

export class TransfertoPracticeAddress3 {
  addressLine1: string;
  addressLine2: string;
  city: string;
  state: string;
  zip: string;
  county: string;
}

export class RemoveFromLocation {
  requestActionCd: string;
  pcpIndicator: string;
  termDate: string;
  termReasonCd: string;
  termReasonDesc: string;
  provFirstName: string;
  provLastName: string;
  provMiddleName: string;
  provNpi: string;
}

export class UnsolicitedRoster {
  requestActionCd: string;
  numberOfProviders: number;
  delegatedCredentialingEntity: string;
}

export class TermAddress {
  requestActionCd: string;
  addressType: string;
  termDate: string;
  termReason: string;
  adrSeqNo: string;
  pcpIndicator: string;
}

export class GroupNameChange {
  updatedPracticeName: string;
  requestActionCd: string;
  itemsAttached: string;
}

export class UpdateURL {
  requestActionCd: string;
  url: string;
}

export class ChangeAddress {
  addressLine1: string;
  addressLine2: string;
  city: string;
  state: string;
  zip: string;
  county: string;
  faxNumber: string;
  phoneNumber: string;
  email: string;
  requestActionCd: string;
}

export class ServiceRequest {
  formState: string;
  formType: string;
  providerType: string;
  formSectionsUpdated: FormSectionsUpdated;
  targetSystems: string[];
  attachments: Attachment[];
  generalInformationIndividualProvider: GeneralInformationIndividualProvider;
  generalInformationOrganizationProvider: GeneralInformationOrganizationProvider;
  updateLanguage: UpdateLanguage;
  updateAcceptingNewPatients: UpdateAcceptingNewPatients;
  updateProviderName: UpdateProviderName;
  updateHospitalAffiliation: UpdateHospitalAffiliation;
  addAddress: AddAddress;
  updatePhoneNumber: UpdatePhoneNumber;
  addLicenseNumber: AddLicenseNumber;
  addChangeNetwork: AddChangeNetwork;
  addChangeAreasofExpertise: AddChangeAreasofExpertise;
  addChangeEmailAddress: AddChangeEmailAddress;
  addChangeProviderSpecialty: AddChangeProviderSpecialty;
  updateAgeGenderPreference: UpdateAgeGenderPreference;
  updateOfficeHours: UpdateOfficeHours;
  updateTaxId: UpdateTaxId;
  termProviderParticipation: TermProviderParticipation;
  addChangeNpi: AddChangeNpi;
  addChangeHandicappedAccessibility: AddChangeHandicappedAccessibility;
  providerLeavingGroup: ProviderLeavingGroup;
  removeFromLocation: RemoveFromLocation;
  unsolicitedRoster: UnsolicitedRoster;
  termAddress: TermAddress;
  groupNameChange: GroupNameChange;
  updateURL: UpdateURL;
  changeAddress: ChangeAddress;
}

export class ServicePayload {
  serviceRequest: ServiceRequest;
}

export class RootObject {
  servicePayload: ServicePayload;
}
