/**
 * Created by AD94882 on 5/24/17.
 */
import { PayLoadService } from '../../services/payload-all.service';
import {
  ServiceRequest, ProviderDetails, PracticeAddress, ContactInfo, RootObject,
  UpdateProviderName, UpdateAcceptingNewPatients, AddChangeHandicappedAccessibility,
  UpdateHospitalAffiliation, AddChangeEmailAddress, Email, UpdateOfficeHours, OfficeHour2, AddChangeAreasofExpertise,
  HandAcc, Privilege, AddChangeNpi, Expertise, AddLicenseNumber, AddChangeNetwork, Network, FormSectionsUpdated,
  GeneralInformationIndividualProvider, AcceptingPatientInfo, TermAddress, ServicePayload, ChangeAddress,
  OrganizationDetails, GeneralInformationOrganizationProvider, UpdatePhoneNumber, PhoneNumber,
  HandicappedAccessibility, ProviderLanguage, OfficeHour, CorrespondenceAddress, AddAddress,
  RemoveFromLocation, UnsolicitedRoster, ProviderLeavingGroup, TransfertoProviderSpecialty, TransfertoPracticeAddress, Attachment,
  GroupNameChange, UpdateURL, ServicesOffered, RemitAddress, Language, UpdateLanguage, AddChangeProviderSpecialty,
  ProviderSpecialty2, UpdateAgeGenderPreference, UpdateTaxId, TermProviderParticipation, RemitAddress2
} from './payload-pega.model';
import { Injectable } from '@angular/core';
import { SelectUpdatesModel } from '../../config/select-updates.model';
import { DatePipe } from '@angular/common';
import {PMFConfigService} from '../../config/pmf-config.service';

@Injectable()
export class CrossJSONMapper {
  public rootObject = new RootObject();

  constructor(private payloadService: PayLoadService, private selectUpdates: SelectUpdatesModel, public datepipe: DatePipe,
              private pmfConfigService: PMFConfigService) {

  }

  mapUIJSONxPEGA() {
    // individual
    this.rootObject.servicePayload = new ServicePayload();
    this.rootObject.servicePayload.serviceRequest = new ServiceRequest();
    this.rootObject.servicePayload.serviceRequest.formState = this.pmfConfigService.getState().toUpperCase();
    this.rootObject.servicePayload.serviceRequest.formType = 'PMF';
    if (this.payloadService.getUserType() === 0) {
      this.rootObject.servicePayload.serviceRequest.providerType = 'IND';
    } else {
      this.rootObject.servicePayload.serviceRequest.providerType = 'ORG';
    }
    this.rootObject.servicePayload.serviceRequest.targetSystems = this.getSelectedLOBS();
    this.rootObject.servicePayload.serviceRequest.formSectionsUpdated = new FormSectionsUpdated();
    this.rootObject.servicePayload.serviceRequest.formSectionsUpdated.actionCode = [];
    for (let action of this.selectUpdates.getAllSelectedUpdates()) {
      if (action.id === '00027' || action.id === '00028') {
        if (this.selectUpdates.getSelectedUpdatesById('00010').length === 0) {
          this.rootObject.servicePayload.serviceRequest.formSectionsUpdated.actionCode.push('00010');
        }
      } else {
        this.rootObject.servicePayload.serviceRequest.formSectionsUpdated.actionCode.push(action.id);
      }
    }
    this.mapProvider(this.rootObject.servicePayload.serviceRequest);

    if (this.selectUpdates.getSelectedUpdatesById('00001').length > 0) {
      this.mapLanguagesSpoken(this.rootObject.servicePayload.serviceRequest);
    }
    if (this.selectUpdates.getSelectedUpdatesById('00003').length > 0) {
      this.mapNewPatients(this.rootObject.servicePayload.serviceRequest);
    }
    if (this.selectUpdates.getSelectedUpdatesById('00004').length > 0) {
      this.mapProviderNameChange(this.rootObject.servicePayload.serviceRequest);
    }
    if (this.selectUpdates.getSelectedUpdatesById('00005').length > 0) {
      this.mapHospitalAffiliation(this.rootObject.servicePayload.serviceRequest);
    }
    if (this.selectUpdates.getSelectedUpdatesById('00006').length > 0) {
      this.mapNewAddress(this.rootObject.servicePayload.serviceRequest);
    }
    if (this.selectUpdates.getSelectedUpdatesById('00007').length > 0) {
      this.mapPhoneFax(this.rootObject.servicePayload.serviceRequest);
    }
    if (this.selectUpdates.getSelectedUpdatesById('00008').length > 0) {
      this.mapLicenseNumber(this.rootObject.servicePayload.serviceRequest);
    }
    if (this.selectUpdates.getSelectedUpdatesById('00009').length > 0) {
      this.mapNetworkParticipation(this.rootObject.servicePayload.serviceRequest);
    }
    if (this.selectUpdates.getSelectedUpdatesById('00010').length > 0) {
      this.mapAreasOfExpertise(this.rootObject.servicePayload.serviceRequest);
    }
    if (this.selectUpdates.getSelectedUpdatesById('00011').length > 0) {
      this.mapEmailAddress(this.rootObject.servicePayload.serviceRequest);
    }
    if (this.selectUpdates.getSelectedUpdatesById('00012').length > 0) {
      this.mapProviderSpecialty(this.rootObject.servicePayload.serviceRequest);
    }
    if (this.selectUpdates.getSelectedUpdatesById('00013').length > 0) {
      this.mapAgeGenderPreference(this.rootObject.servicePayload.serviceRequest);
    }
    if (this.selectUpdates.getSelectedUpdatesById('00014').length > 0) {
      this.mapOfficeHours(this.rootObject.servicePayload.serviceRequest);
    }
    if (this.selectUpdates.getSelectedUpdatesById('00015').length > 0) {
      this.mapUpdateTaxIdentificationNumber(this.rootObject.servicePayload.serviceRequest);
    }
    if (this.selectUpdates.getSelectedUpdatesById('00016').length > 0) {
      this.mapTerminatePPA(this.rootObject.servicePayload.serviceRequest);
    }
    if (this.selectUpdates.getSelectedUpdatesById('00017').length > 0) {
      this.mapNPI(this.rootObject.servicePayload.serviceRequest);
    }
    if (this.selectUpdates.getSelectedUpdatesById('00018').length > 0) {
      this.mapHandicappedAccessibility(this.rootObject.servicePayload.serviceRequest);
    }
    if (this.selectUpdates.getSelectedUpdatesById('00019').length > 0) {
      this.mapRemoveProviderLocation(this.rootObject.servicePayload.serviceRequest);
    }
    if (this.selectUpdates.getSelectedUpdatesById('00020').length > 0) {
      this.mapTerminateAddress(this.rootObject.servicePayload.serviceRequest);
    }
    if (this.selectUpdates.getSelectedUpdatesById('00021').length > 0) {
      this.mapWebAddress(this.rootObject.servicePayload.serviceRequest);
    }
    if (this.selectUpdates.getSelectedUpdatesById('00022').length > 0) {
      this.mapProviderLeavingGroup(this.rootObject.servicePayload.serviceRequest);
    }
    if (this.selectUpdates.getSelectedUpdatesById('00023').length > 0) {
      this.mapUpdateOrganizationName(this.rootObject.servicePayload.serviceRequest);
    }
    if (this.selectUpdates.getSelectedUpdatesById('00025').length > 0) {
      this.mapUpdateAddress(this.rootObject.servicePayload.serviceRequest);
    }
    if (this.selectUpdates.getSelectedUpdatesById('00026').length > 0) {
      this.mapUnsolicitedRoster(this.rootObject.servicePayload.serviceRequest);
    }
    if (this.selectUpdates.getSelectedUpdatesById('00027').length > 0) {
      this.mapTelehealth(this.rootObject.servicePayload.serviceRequest);
    }
    if (this.selectUpdates.getSelectedUpdatesById('00028').length > 0) {
      this.mapAmbulance(this.rootObject.servicePayload.serviceRequest);
    }

    this.mapAttachments(this.rootObject.servicePayload.serviceRequest);
  }

  getSelectedLOBS() {
    return this.payloadService.targetSystems
      .filter((opt) => opt.checked)
      .map((opt) => opt.name);
  }

  // map individual and Organization
  mapProvider(serviceRequest: ServiceRequest) {
    if (this.payloadService.getUserType() === 0) {
      serviceRequest.generalInformationIndividualProvider = new GeneralInformationIndividualProvider();
      let providerDetails = new ProviderDetails();
      let effectiveDate = this.datepipe.transform(this.payloadService.provider.effectiveDate, 'MM/dd/yyyy');
      providerDetails.effectiveDate = effectiveDate;
      providerDetails.firstName = this.payloadService.individual.firstName;
      providerDetails.lastName = this.payloadService.individual.lastName;
      providerDetails.individualNPI = this.payloadService.provider.npi;
      providerDetails.individualTaxId = this.payloadService.individual.taxId;
      providerDetails.licenseNumber = this.payloadService.individual.license;
      providerDetails.middleName = this.payloadService.individual.middleName;
      providerDetails.suffix = this.payloadService.individual.suffix;
      providerDetails.title = this.payloadService.individual.title;
      serviceRequest.generalInformationIndividualProvider.providerDetails = providerDetails;
    } else {
      serviceRequest.generalInformationOrganizationProvider = new GeneralInformationOrganizationProvider();
      let organizationDetails = new OrganizationDetails();
      organizationDetails.organizationName = this.payloadService.organization.name;
      organizationDetails.dbaName = this.payloadService.provider.practiceDBA;
      organizationDetails.organizationTaxId = this.payloadService.organization.taxId;
      organizationDetails.organizationNPI = this.payloadService.provider.npi;
      let effectiveDate = this.datepipe.transform(this.payloadService.provider.effectiveDate, 'MM/dd/yyyy');
      organizationDetails.effectiveDate = effectiveDate;
      organizationDetails.organizationLicenseNumber = '';
      serviceRequest.generalInformationOrganizationProvider.organizationDetails = organizationDetails;
    }

    let practiceAddress = new PracticeAddress();
    practiceAddress.addressLine1 = this.payloadService.addresses.streetAddress1;
    practiceAddress.addressLine2 = this.payloadService.addresses.streetAddress2;
    practiceAddress.city = this.payloadService.addresses.city;
    practiceAddress.county = this.payloadService.addresses.county;
    practiceAddress.state = this.payloadService.addresses.state;
    practiceAddress.zip = this.payloadService.addresses.zipCode;
    practiceAddress.adrSeqNo = '01';

    if (this.payloadService.getUserType() === 0) {
      serviceRequest.generalInformationIndividualProvider.practiceAddress = [];
      serviceRequest.generalInformationIndividualProvider.practiceAddress.push(practiceAddress);
    } else {
      // for organization
      serviceRequest.generalInformationOrganizationProvider.practiceAddress = [];
      serviceRequest.generalInformationOrganizationProvider.practiceAddress.push(practiceAddress);
    }

    let contactInfo = new ContactInfo();
    contactInfo.firstName = this.payloadService.contact.ctFirstName;
    contactInfo.lastName = this.payloadService.contact.ctLastName;
    contactInfo.phoneNumber = this.payloadService.contact.phoneNumber;
    contactInfo.email = this.payloadService.contact.emailAddress;

    if (this.payloadService.getUserType() === 0) {
      serviceRequest.generalInformationIndividualProvider.contactInfo = contactInfo;
    } else {
      // for organization
      serviceRequest.generalInformationOrganizationProvider.contactInfo = contactInfo;
    }
  }

  // map add new patients
  mapNewPatients(serviceRequest: ServiceRequest) {
    serviceRequest.updateAcceptingNewPatients = new UpdateAcceptingNewPatients();
    serviceRequest.updateAcceptingNewPatients.acceptingPatientInfo = new AcceptingPatientInfo();
    if (this.payloadService.acceptingNewPatients.acceptingNewPatientIndicator === 'yes') {
      serviceRequest.updateAcceptingNewPatients.acceptingPatientInfo.acceptingNewPatientsIndicator = 'yes';
      serviceRequest.updateAcceptingNewPatients.acceptingPatientInfo.ageCriteriaDesc =
        this.payloadService.acceptingNewPatients.patientMinimumAge + '-' +
        this.payloadService.acceptingNewPatients.patientMaximumAge;
      serviceRequest.updateAcceptingNewPatients.acceptingPatientInfo.genderPreferenceDesc =
        this.payloadService.acceptingNewPatients.patientGender;
    } else {
      serviceRequest.updateAcceptingNewPatients.acceptingPatientInfo.acceptingNewPatientsIndicator = 'no';
    }
    serviceRequest.updateAcceptingNewPatients.requestActionCd = 'U';
  }

  mapAgeGenderPreference(serviceRequest: ServiceRequest) {
    serviceRequest.updateAgeGenderPreference = new UpdateAgeGenderPreference();
    serviceRequest.updateAgeGenderPreference.requestActionCd = 'U';
    serviceRequest.updateAgeGenderPreference.maxage = this.payloadService.ageGenderUpdate.maximumAge;
    serviceRequest.updateAgeGenderPreference.minage = this.payloadService.ageGenderUpdate.minimumAge;
    serviceRequest.updateAgeGenderPreference.genderDesc = this.payloadService.ageGenderUpdate.gender;
    serviceRequest.updateAgeGenderPreference.genderCd = '';
  }

  // map handicapped accessibility
  mapHandicappedAccessibility(serviceRequest: ServiceRequest) {
    serviceRequest.addChangeHandicappedAccessibility = new AddChangeHandicappedAccessibility();
    serviceRequest.addChangeHandicappedAccessibility.handAcc = [];
    let handaccAdd = new HandAcc();
    handaccAdd.requestActionCd = 'U';
    handaccAdd.handaccCd = '';
    handaccAdd.handaccdesc = this.payloadService.handicappedAccessibility.description;
    serviceRequest.addChangeHandicappedAccessibility.handAcc.push(handaccAdd);
  }

  // map provider name change
  mapProviderNameChange(serviceRequest: ServiceRequest) {
    serviceRequest.updateProviderName = new UpdateProviderName();
    serviceRequest.updateProviderName.requestActionCd = 'U';
    serviceRequest.updateProviderName.updatedFirstName = this.payloadService.practitionerChangeType.practitionerFirstName;
    serviceRequest.updateProviderName.updatedLastName = this.payloadService.practitionerChangeType.practitionerLastName;
    serviceRequest.updateProviderName.updatedLicenseNumber = this.payloadService.practitionerChangeType.practitionerLicense;
    serviceRequest.updateProviderName.updatedMiddleName = this.payloadService.practitionerChangeType.practitionerMiddleName;
    serviceRequest.updateProviderName.updatedSuffix = this.payloadService.practitionerChangeType.practitionerSuffix;
    serviceRequest.updateProviderName.updatedTitle = this.payloadService.practitionerChangeType.practitionerTitle;
  }

  // map Hospital Affiliation
  mapHospitalAffiliation(serviceRequest: ServiceRequest) {
    serviceRequest.updateHospitalAffiliation = new UpdateHospitalAffiliation();
    serviceRequest.updateHospitalAffiliation.privilege = [];
    for (let hospitalAffiliation of this.payloadService.hospitalPrivileges) {
      let hospitalPrivilege = new Privilege();
      hospitalPrivilege.requestActionCd = hospitalAffiliation.requestActionCd;
      hospitalPrivilege.hospitalName = hospitalAffiliation.affiliationName;
      hospitalPrivilege.hospitalPrivilegeCd = '';
      hospitalPrivilege.hospitalPrivilegeDesc = hospitalAffiliation.privilegeType;
      serviceRequest.updateHospitalAffiliation.privilege.push(hospitalPrivilege);
    }
  }

  // map email address
  mapEmailAddress(serviceRequest: ServiceRequest) {
    serviceRequest.addChangeEmailAddress = new AddChangeEmailAddress();
    serviceRequest.addChangeEmailAddress.email = [];
    for (let email of this.payloadService.email) {
      let emailPega = new Email();
      emailPega.adrSeqNo = '';
      emailPega.email = email.emailAddress;
      emailPega.requestActionCd = email.requestActionCd;
      serviceRequest.addChangeEmailAddress.email.push(emailPega);
    }
  }

  // map languages spoken
  mapLanguagesSpoken(serviceRequest: ServiceRequest) {
    serviceRequest.updateLanguage = new UpdateLanguage();
    for (let providerLanguage of this.payloadService.languagesSpoken) {
      let providerLanguagePega = new Language();
      providerLanguagePega.requestActionCd = providerLanguage.actionCd;
      providerLanguagePega.langCd = '';
      providerLanguagePega.langDesc = providerLanguage.language;
      providerLanguagePega.adrSeqNo = '01';
      serviceRequest.updateLanguage.language.push(providerLanguagePega);
    }
  }

  // map Hours of Operations
  mapOfficeHours(serviceRequest: ServiceRequest) {
    serviceRequest.updateOfficeHours = new UpdateOfficeHours();
    serviceRequest.updateOfficeHours.requestActionCd = 'U';
    serviceRequest.updateOfficeHours.officeHours = [];
    for (let weekDayHours of this.payloadService.operationHours.daysOperationHours) {
      let eachDayHours = new OfficeHour2();
      eachDayHours.dayofTheWeek = weekDayHours.dayName;
      eachDayHours.openHour = weekDayHours.openingTime;
      eachDayHours.closedHour = weekDayHours.closingTime;
      serviceRequest.updateOfficeHours.officeHours.push(eachDayHours);
    }
  }

  // map NPI
  mapNPI(serviceRequest: ServiceRequest) {
    serviceRequest.addChangeNpi = new AddChangeNpi();
    serviceRequest.addChangeNpi.requestActionCd = 'U';
    serviceRequest.addChangeNpi.newNpi = this.payloadService.individualNpi.individualNpi;

    if (this.payloadService.getUserType() === 1) {
      serviceRequest.addChangeNpi.requestActionCd = this.payloadService.organizationNpi.requestType;
      serviceRequest.addChangeNpi.newNpi=this.payloadService.individualNpi.individualNpi;
      serviceRequest.addChangeNpi.remitAddress = new RemitAddress2();
      serviceRequest.addChangeNpi.remitAddress.addressLine1=this.payloadService.organizationNpi.streetAddress1;
      serviceRequest.addChangeNpi.remitAddress.addressLine2=this.payloadService.organizationNpi.streetAddress2;
      serviceRequest.addChangeNpi.remitAddress.city=this.payloadService.organizationNpi.city;
      serviceRequest.addChangeNpi.remitAddress.state=this.payloadService.organizationNpi.state;
      serviceRequest.addChangeNpi.remitAddress.zip=this.payloadService.organizationNpi.zipCode;
      serviceRequest.addChangeNpi.remitAddress.county=this.payloadService.organizationNpi.county;
    }
  }

  // map Areas Of Expertise
  mapAreasOfExpertise(serviceRequest: ServiceRequest) {
    serviceRequest.addChangeAreasofExpertise = new AddChangeAreasofExpertise();
    serviceRequest.addChangeAreasofExpertise.expertise = [];
    for (let areaOfExpertise of this.payloadService.areaOfExpertise) {
      let expertise: Expertise = new Expertise();
      expertise.requestActionCd = areaOfExpertise.requestActionCd;
      expertise.expertiseDesc = areaOfExpertise.expertise;
      serviceRequest.addChangeAreasofExpertise.expertise.push(expertise);
    }
  }

  // map License Number
  mapLicenseNumber(serviceRequest: ServiceRequest) {
    serviceRequest.addLicenseNumber = new AddLicenseNumber();
    serviceRequest.addLicenseNumber.requestActionCd = 'A';
    serviceRequest.addLicenseNumber.issuingState = this.payloadService.updateLicense.licenseState;
    serviceRequest.addLicenseNumber.licenseNumber = this.payloadService.updateLicense.updateLicense;
  }

  // map Network Participation
  mapNetworkParticipation(serviceRequest: ServiceRequest) {
    serviceRequest.addChangeNetwork = new AddChangeNetwork();
    serviceRequest.addChangeNetwork.network = [];
    let network = new Network();
    network.lobCd = this.payloadService.networkParticipation.network;
    network.requestActionCd = this.payloadService.networkParticipation.networkModify;
    serviceRequest.addChangeNetwork.network.push(network);
  }

  // Map the Terminate Address here
  mapTerminateAddress(serviceRequest: ServiceRequest) {
    serviceRequest.termAddress = new TermAddress();
    serviceRequest.termAddress.requestActionCd = 'U';
    serviceRequest.termAddress.adrSeqNo = '01';
    serviceRequest.termAddress.addressType = this.payloadService.terminateAddress.addressType;
    let termDate = this.datepipe.transform(this.payloadService.terminateAddress.tterminationDate, 'MM/dd/yyyy');
    serviceRequest.termAddress.termDate = termDate.toString();
    serviceRequest.termAddress.termReason = this.payloadService.terminateAddress.termReason;
    if(this.payloadService.terminateAddress.addressType === 'Practice Location') {
      serviceRequest.termAddress.pcpIndicator = this.payloadService.terminateAddress.pcpIndicator;
    }
  }

  // Map the Address Update here.
  mapUpdateAddress(serviceRequest: ServiceRequest) {
    serviceRequest.changeAddress = new ChangeAddress();
    serviceRequest.changeAddress.addressLine1 = this.payloadService.updateAddress.address.streetAddress1;
    serviceRequest.changeAddress.addressLine2 = this.payloadService.updateAddress.address.streetAddress2;
    serviceRequest.changeAddress.city = this.payloadService.updateAddress.address.city;
    serviceRequest.changeAddress.state = this.payloadService.updateAddress.address.state;
    serviceRequest.changeAddress.zip = this.payloadService.updateAddress.address.zipCode;
    serviceRequest.changeAddress.county = this.payloadService.updateAddress.address.county;
    serviceRequest.changeAddress.requestActionCd = 'U';
  }

  //map phone/fax
  mapPhoneFax(serviceRequest: ServiceRequest) {
    serviceRequest.updatePhoneNumber = new UpdatePhoneNumber();
    for (let phoneFax of this.payloadService.phoneFax) {
      let phoneFaxPega = new PhoneNumber();
      phoneFaxPega.adrSeqNo = '01';
      phoneFaxPega.phoneNumber = phoneFax.phoneNumber;
      phoneFaxPega.phoneTypeDesc = phoneFax.phoneTypeDesc;
      phoneFaxPega.requestActionCd = phoneFax.requestActionCd;
      phoneFaxPega.phoneTypeCd = '';
      serviceRequest.updatePhoneNumber.phoneNumber.push(phoneFaxPega);
    }
  }

  //map new address
  mapNewAddress(serviceRequest: ServiceRequest) {
    serviceRequest.addAddress = new AddAddress();
    serviceRequest.addAddress.requestActionCd = 'A';
    serviceRequest.addAddress.addressType = this.payloadService.addAddress.addressType;
    if (this.payloadService.addAddress.addressType === 'Correspondence') {
      this.mapCorrespondenceAddress(serviceRequest);
    }
    if (this.payloadService.addAddress.addressType === 'Practice Location') {
      this.mapPracticeAddress(serviceRequest);
    }
    if (this.payloadService.addAddress.addressType === 'Payment/Remittance') {
      this.mapPaymentAndRemittanceAddress(serviceRequest);
    }
  }

  // map address in New Address
  mapCorrespondenceAddress(serviceRequest: ServiceRequest) {
    serviceRequest.addAddress.correspondenceAddress.addressLine1 =
      this.payloadService.addAddress.correspondenceAddress.streetAddress1;
    serviceRequest.addAddress.correspondenceAddress.addressLine2 =
      this.payloadService.addAddress.correspondenceAddress.streetAddress2;
    serviceRequest.addAddress.correspondenceAddress.city =
      this.payloadService.addAddress.correspondenceAddress.city;
    serviceRequest.addAddress.correspondenceAddress.county =
      this.payloadService.addAddress.correspondenceAddress.county;
    serviceRequest.addAddress.correspondenceAddress.email =
      this.payloadService.addAddress.correspondenceAddress.email;
    serviceRequest.addAddress.correspondenceAddress.faxNumber =
      this.payloadService.addAddress.correspondenceAddress.fax;
    serviceRequest.addAddress.correspondenceAddress.phoneNumber =
      this.payloadService.addAddress.correspondenceAddress.phone;
    serviceRequest.addAddress.correspondenceAddress.state =
      this.payloadService.addAddress.correspondenceAddress.state;
    serviceRequest.addAddress.correspondenceAddress.zip =
      this.payloadService.addAddress.correspondenceAddress.zipCode;
  }

  // map address in New Address (Payment/Remittance) here..
  mapPaymentAndRemittanceAddress(serviceRequest: ServiceRequest) {
    serviceRequest.addAddress.remitAddress.addressLine1 =
      this.payloadService.addAddress.remitAddress.streetAddress1;
    serviceRequest.addAddress.remitAddress.addressLine2 =
      this.payloadService.addAddress.remitAddress.streetAddress2;
    serviceRequest.addAddress.remitAddress.city =
      this.payloadService.addAddress.remitAddress.city;
    serviceRequest.addAddress.remitAddress.county =
      this.payloadService.addAddress.remitAddress.county;
    serviceRequest.addAddress.remitAddress.state =
      this.payloadService.addAddress.remitAddress.state;
    serviceRequest.addAddress.remitAddress.zip =
      this.payloadService.addAddress.remitAddress.zipCode;
    serviceRequest.addAddress.remitAddress.email =
      this.payloadService.addAddress.remitAddress.email;
    serviceRequest.addAddress.remitAddress.faxNumber =
      this.payloadService.addAddress.remitAddress.fax;
    serviceRequest.addAddress.remitAddress.phoneNumber =
      this.payloadService.addAddress.remitAddress.phone;
  }

  // map practice address for Practice Address..
  mapPracticeAddress(serviceRequest: ServiceRequest) {
    serviceRequest.addAddress.practiceAddress.addressLine1 =
      this.payloadService.addAddress.physicalAddress.address.streetAddress1;
    serviceRequest.addAddress.practiceAddress.addressLine2 =
      this.payloadService.addAddress.physicalAddress.address.streetAddress2;
    serviceRequest.addAddress.practiceAddress.city =
      this.payloadService.addAddress.physicalAddress.address.city;
    serviceRequest.addAddress.practiceAddress.county =
      this.payloadService.addAddress.physicalAddress.address.county;
    serviceRequest.addAddress.practiceAddress.email =
      this.payloadService.addAddress.physicalAddress.address.email;
    serviceRequest.addAddress.practiceAddress.faxNumber =
      this.payloadService.addAddress.physicalAddress.address.fax;
    serviceRequest.addAddress.practiceAddress.phoneNumber =
      this.payloadService.addAddress.physicalAddress.address.phone;
    serviceRequest.addAddress.practiceAddress.state =
      this.payloadService.addAddress.physicalAddress.address.state;
    serviceRequest.addAddress.practiceAddress.zip =
      this.payloadService.addAddress.physicalAddress.address.zipCode;

    this.mapPracticeAddressPrimaryCarePhysician(serviceRequest.addAddress);

    this.mapPracticeAddressAcceptingNewPatient(serviceRequest.addAddress.practiceAddress.acceptingPatientInfo);

    serviceRequest.addAddress.practiceAddress.handicappedAccessibility = new HandicappedAccessibility();
    this.mapPracticeAddressHandicap(serviceRequest.addAddress.practiceAddress.handicappedAccessibility);

    if (this.payloadService.getUserType() === 1) {
      serviceRequest.addAddress.practiceAddress.publicTransportationAccessibleIndicator =
        this.payloadService.addAddress.physicalAddress.isPublicTransportation;
    }

    this.mapPracticeLanguagesSpoken(serviceRequest.addAddress.practiceAddress.providerLanguage);

    this.mapAddAddressOfficeHours(serviceRequest.addAddress.practiceAddress.officeHours);

    if (this.payloadService.getUserType() === 1) {
      this.mapPracticeServicesOffered(serviceRequest.addAddress.practiceAddress.servicesOffered);
    }

    if (this.payloadService.getUserType() === 0) {
      this.mapPracticeSpecialty(serviceRequest.addAddress.practiceAddress.servicesOffered);
    }

    if (this.payloadService.addAddress.physicalAddress.isCorrAddedToPracticeIndicator === 'yes') {
      serviceRequest.addAddress.practiceAddress.isCorrAddedToPracticeIndicator = 'yes';
      this.mapPracticeAddressCorrespondence(serviceRequest.addAddress.practiceAddress.pacorrespondenceAddress);
    } else {
      serviceRequest.addAddress.practiceAddress.isCorrAddedToPracticeIndicator = 'no';
    }

    if (this.payloadService.addAddress.physicalAddress.isCorrespondenceSelected) {
      this.mapPracticeAddressCorrespondence(serviceRequest.addAddress.practiceAddress.pacorrespondenceAddress);
    }

    if (this.payloadService.addAddress.physicalAddress.isRemittanceSelected) {
      this.mapPracticeAddressPracticeRemittance(serviceRequest.addAddress.practiceAddress.paremitAddress);
    }
  }

  // map practice address acting as a PCP at this location
  mapPracticeAddressPrimaryCarePhysician(addAddress: AddAddress) {
    if(this.payloadService.addAddress.physicalAddress.pcpIndicator === 'no'){
      addAddress.pcpIndicator = 'no';
      if (this.payloadService.addAddress.physicalAddress.physicianAssistant) {
        if (this.payloadService.addAddress.physicalAddress.physicianAssistant.length > 0) {
          addAddress.pcpIndicator = addAddress.pcpIndicator + '-' +
            this.payloadService.addAddress.physicalAddress.physicianAssistant;
        }
      }
    }else{
      addAddress.pcpIndicator = 'yes';
    }
  }

  // map practice address accepting new patient
  mapPracticeAddressAcceptingNewPatient(acceptingPatientInfo: AcceptingPatientInfo) {
    if (this.payloadService.addAddress.physicalAddress.acceptNewpatient.acceptingNewPatientIndicator === 'yes') {
      acceptingPatientInfo.acceptingNewPatientsIndicator = 'yes';
      acceptingPatientInfo.ageCriteriaDesc =
        this.payloadService.addAddress.physicalAddress.acceptNewpatient.patientMinimumAge + '-' +
        this.payloadService.addAddress.physicalAddress.acceptNewpatient.patientMaximumAge;
      acceptingPatientInfo.genderPreferenceDesc =
        this.payloadService.addAddress.physicalAddress.acceptNewpatient.patientGender;
    } else {
      acceptingPatientInfo.acceptingNewPatientsIndicator = 'no';
    }

  }

  // map practice address correspondence
  mapPracticeAddressCorrespondence(address: CorrespondenceAddress) {
    address.addressLine1 =
      this.payloadService.addAddress.physicalAddress.correspondenceAddress.streetAddress1;
    address.addressLine2 =
      this.payloadService.addAddress.physicalAddress.correspondenceAddress.streetAddress2;
    address.city =
      this.payloadService.addAddress.physicalAddress.correspondenceAddress.city;
    address.county =
      this.payloadService.addAddress.physicalAddress.correspondenceAddress.county;
    address.email =
      this.payloadService.addAddress.physicalAddress.correspondenceAddress.email;
    address.faxNumber =
      this.payloadService.addAddress.physicalAddress.correspondenceAddress.fax;
    address.phoneNumber =
      this.payloadService.addAddress.physicalAddress.correspondenceAddress.phone;
    address.state =
      this.payloadService.addAddress.physicalAddress.correspondenceAddress.state;
    address.zip =
      this.payloadService.addAddress.physicalAddress.correspondenceAddress.zipCode;
  }

  // map practice address handicap
  mapPracticeAddressHandicap(handicappedAccessibility: HandicappedAccessibility) {
    handicappedAccessibility.handaccCd = '';
    handicappedAccessibility.handaccdesc = this.payloadService.addAddress.physicalAddress.handicappedAccessibility.description;
  }

  // map services offered here..
  mapPracticeServicesOffered(servicesOffered: ServicesOffered[]) {
    for (let serviceOffers of this.payloadService.addAddress.physicalAddress.serviceOffers) {
      let servicesOfferPega = new ServicesOffered();
      servicesOfferPega.servicesOfferedCd = serviceOffers.servicesOfferedDesc;
      servicesOfferPega.servicesOfferedDesc = serviceOffers.servicesOfferedDesc;
      servicesOffered.push(servicesOfferPega);
    }
  }

  // map specialty to services offered here..ind prov only
  mapPracticeSpecialty(servicesOffered: ServicesOffered[]) {
    let servicesOfferPega = new ServicesOffered();
    servicesOfferPega.servicesOfferedCd = this.payloadService.addAddress.physicalAddress.specialty;
    servicesOfferPega.servicesOfferedDesc = this.payloadService.addAddress.physicalAddress.specialty;
    servicesOffered.push(servicesOfferPega);
  }

  // map Languages Spoken
  mapPracticeLanguagesSpoken(providerLanguages: ProviderLanguage[]) {
    for (let providerLanguage of this.payloadService.addAddress.physicalAddress.languagesSpoken) {
      let providerLanguagePega = new ProviderLanguage();
      providerLanguagePega.langCd = '';
      providerLanguagePega.langDesc = providerLanguage.language;
      providerLanguages.push(providerLanguagePega);
    }
  }

  // map Hours of Operations
  mapAddAddressOfficeHours(officeHours: OfficeHour[]) {
    for (let weekDayHours of this.payloadService.addAddress.physicalAddress.operationHours.daysOperationHours) {
      let eachDayHours = new OfficeHour2();
      eachDayHours.dayofTheWeek = weekDayHours.dayName;
      eachDayHours.openHour = weekDayHours.openingTime;
      eachDayHours.closedHour = weekDayHours.closingTime;
      officeHours.push(eachDayHours);
    }
  }

  // map practice addreess - payment/remittance
  mapPracticeAddressPracticeRemittance(address: RemitAddress) {
    address.addressLine1 =
      this.payloadService.addAddress.physicalAddress.remitAddress.streetAddress1;
    address.addressLine2 =
      this.payloadService.addAddress.physicalAddress.remitAddress.streetAddress2;
    address.city =
      this.payloadService.addAddress.physicalAddress.remitAddress.city;
    address.county =
      this.payloadService.addAddress.physicalAddress.remitAddress.county;
    address.state =
      this.payloadService.addAddress.physicalAddress.remitAddress.state;
    address.zip =
      this.payloadService.addAddress.physicalAddress.remitAddress.zipCode;
    address.email =
      this.payloadService.addAddress.physicalAddress.remitAddress.email;
    address.faxNumber =
      this.payloadService.addAddress.physicalAddress.remitAddress.fax;
    address.phoneNumber =
      this.payloadService.addAddress.physicalAddress.remitAddress.phone;
  }

  // Map the Provider Leaving Group here.
  mapProviderLeavingGroup(serviceRequest: ServiceRequest) {
    serviceRequest.providerLeavingGroup = new ProviderLeavingGroup();
    serviceRequest.providerLeavingGroup.requestActionCd = 'U';
    serviceRequest.providerLeavingGroup.pcpIndicator = this.payloadService.providerLeavingGroup.providerChange.pcpIndicator;
    serviceRequest.providerLeavingGroup.termDate = '';
    serviceRequest.providerLeavingGroup.termReasonDesc = this.payloadService.providerLeavingGroup.providerChange.termReasonCd;
    serviceRequest.providerLeavingGroup.provFirstName = this.payloadService.providerLeavingGroup.providerChange.provFirstName;
    serviceRequest.providerLeavingGroup.provLastName = this.payloadService.providerLeavingGroup.providerChange.provLastName;
    serviceRequest.providerLeavingGroup.provMiddleName = this.payloadService.providerLeavingGroup.providerChange.provMiddleName;
    serviceRequest.providerLeavingGroup.provNpi = this.payloadService.providerLeavingGroup.providerChange.provNpi;
  }

  // Map the Remove Provider From Location here...
  mapRemoveProviderLocation(serviceRequest: ServiceRequest) {
    serviceRequest.removeFromLocation = new RemoveFromLocation();
    serviceRequest.removeFromLocation.requestActionCd = 'U';
    serviceRequest.removeFromLocation.pcpIndicator = this.payloadService.removeProviderFromLocation.providerChange.pcpIndicator;
    serviceRequest.removeFromLocation.termDate = '';
    serviceRequest.removeFromLocation.termReasonDesc = this.payloadService.removeProviderFromLocation.providerChange.termReasonCd;
    serviceRequest.removeFromLocation.provFirstName = this.payloadService.removeProviderFromLocation.providerChange.provFirstName;
    serviceRequest.removeFromLocation.provLastName = this.payloadService.removeProviderFromLocation.providerChange.provLastName;
    serviceRequest.removeFromLocation.provMiddleName = this.payloadService.removeProviderFromLocation.providerChange.provMiddleName;
    serviceRequest.removeFromLocation.provNpi = this.payloadService.removeProviderFromLocation.providerChange.provNpi;
  }

  mapUnsolicitedRoster(serviceRequest: ServiceRequest) {
    serviceRequest.unsolicitedRoster = new UnsolicitedRoster();
    serviceRequest.unsolicitedRoster.requestActionCd = 'U';
    serviceRequest.unsolicitedRoster.delegatedCredentialingEntity = this.payloadService.unsolicitedRoster.delegatedCredentialingEntity;
    serviceRequest.unsolicitedRoster.numberOfProviders = this.payloadService.unsolicitedRoster.numberOfProviders;
  }

  mapAttachments(serviceRequest: ServiceRequest) {
    if (this.payloadService.attachments.length > 0) {
      serviceRequest.attachments = [];
      for (let attachment of this.payloadService.attachments) {
        let payLoadAttachment: Attachment = new Attachment();
        payLoadAttachment.fileName = attachment.fileName;
        payLoadAttachment.fileComments = attachment.fileComments;
        payLoadAttachment.fileSize = attachment.fileSize.toFixed(2);
        payLoadAttachment.fileType = attachment.fileType;
        serviceRequest.attachments.push(payLoadAttachment);
      }
    }
  }

  // Mapping for the Update Organization Name
  mapUpdateOrganizationName(serviceRequest: ServiceRequest) {
    serviceRequest.groupNameChange = new GroupNameChange();
    serviceRequest.groupNameChange.requestActionCd = 'U';
    serviceRequest.groupNameChange.updatedPracticeName = this.payloadService.organization.updatedPracticeName;
  }

  // Mapping for the Web Address
  mapWebAddress(serviceRequest: ServiceRequest) {
    serviceRequest.updateURL = new UpdateURL();
    serviceRequest.updateURL.requestActionCd = 'U';
    serviceRequest.updateURL.url = this.payloadService.webAddress.webAddress;
  }

  // Mapping for Telehealth
  mapTelehealth(serviceRequest: ServiceRequest) {
    //  serviceRequest.telehealth = new Telehealth();
    if (serviceRequest.addChangeAreasofExpertise === undefined) {
      serviceRequest.addChangeAreasofExpertise = new AddChangeAreasofExpertise();
      serviceRequest.addChangeAreasofExpertise.expertise = [];
    }
    let expertise: Expertise = new Expertise();
    if (this.payloadService.telehealth.telehealthServicesIndicator === 'yes') {
      expertise.requestActionCd = 'A';
    } else if (this.payloadService.telehealth.telehealthServicesIndicator === 'no') {
      expertise.requestActionCd = 'D';
    }
    //expertise.expertiseCd = 'Other';
    expertise.expertiseDesc = 'Telehealth';
    serviceRequest.addChangeAreasofExpertise.expertise.push(expertise);
  }

  // Mapping for Ambulance
  mapAmbulance(serviceRequest: ServiceRequest) {
    if (serviceRequest.addChangeAreasofExpertise === undefined) {
      serviceRequest.addChangeAreasofExpertise = new AddChangeAreasofExpertise();
      serviceRequest.addChangeAreasofExpertise.expertise = [];
    }
    let expertise: Expertise = new Expertise();
    if (this.payloadService.ambulance.ambulanceServicesIndicator === 'yes') {
      expertise.requestActionCd = 'A';
    } else if (this.payloadService.ambulance.ambulanceServicesIndicator === 'no') {
      expertise.requestActionCd = 'D';
    }
    //expertise.expertiseCd = 'Other';
    expertise.expertiseDesc = 'Publicly Funded Ambulance';
    serviceRequest.addChangeAreasofExpertise.expertise.push(expertise);
  }

  private mapProviderSpecialty(serviceRequest: ServiceRequest) {
    serviceRequest.addChangeProviderSpecialty = new AddChangeProviderSpecialty();
    serviceRequest.addChangeProviderSpecialty.requestActionCd = this.payloadService.specialty.addNewRequest;
    serviceRequest.addChangeProviderSpecialty.pcpindicator = this.payloadService.specialty.pcpIndicator;
    serviceRequest.addChangeProviderSpecialty.providerSpecialty = [];
    let primarySpecialty: ProviderSpecialty2 = new ProviderSpecialty2();
    primarySpecialty.primaryIndicator = 'primary';
    primarySpecialty.specialtyDesc = this.payloadService.specialty.primarySpecialty;
    serviceRequest.addChangeProviderSpecialty.providerSpecialty.push(primarySpecialty);
    for (let additionalSpecialty of this.payloadService.specialty.additionalSpecialties) {
      let specialty: ProviderSpecialty2 = new ProviderSpecialty2();
      specialty.specialtyDesc = additionalSpecialty.specialty;
      serviceRequest.addChangeProviderSpecialty.providerSpecialty.push(specialty);
    }
  }

  // Mapping for the Update Tax Id.
  private mapUpdateTaxIdentificationNumber(serviceRequest: ServiceRequest) {
    serviceRequest.updateTaxId = new UpdateTaxId();
    serviceRequest.updateTaxId.requestActionCd = 'U';
    serviceRequest.updateTaxId.npi = this.payloadService.tinOwnership.npi;
    serviceRequest.updateTaxId.newTaxId = this.payloadService.tinOwnership.tin;
  }

  // Mapping Terminate Provider Participation Agreement
  private mapTerminatePPA(serviceRequest: ServiceRequest) {
    serviceRequest.termProviderParticipation = new TermProviderParticipation();
    serviceRequest.termProviderParticipation.requestActionCd = 'U';
    serviceRequest.termProviderParticipation.termDate = this.datepipe.transform(this.payloadService.terminatePPA.terminationDate, 'MM/dd/yyyy');
    serviceRequest.termProviderParticipation.termReasonDesc = this.payloadService.terminatePPA.terminationReason;
    if(this.payloadService.getUserType() === 0) {
      serviceRequest.termProviderParticipation.managedCarePcpIndicator = this.payloadService.terminatePPA.managedCarePCPIndicator;
      serviceRequest.termProviderParticipation.pcpIndicator = this.payloadService.terminatePPA.managedCarePCPIndicator;
    }
  }
}
