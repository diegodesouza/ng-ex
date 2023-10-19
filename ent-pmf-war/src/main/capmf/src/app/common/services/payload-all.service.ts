/**
 * Created by AB74296 on 4/21/2017.
 */
import { Injectable } from '@angular/core';
import { Address } from '../models/address.model';
import { Individual } from '../models/individual.model';
import { Organization } from '../models/organization.model';
import { Provider } from '../models/provider.model';
import { Contact } from '../models/contact.model';
import { Specialty } from '../models/specialty.model';
import { TinOwnership } from '../models/tin-ownership.model';
import { PractitionerChangeType } from '../models/practitioner-change-type.model';
import { NetworkParticipation } from '../models/network-participation.model';
import { AgeGenderUpdate } from '../models/age-gender-update.model';
import { AcceptingNewPatients } from '../models/accepting-new-patients.model';
import { TerminatePPA } from '../models/terminate-ppa.model';
import { IndividualNpi } from '../models/individual-npi.model';
import { UpdateLicense } from '../models/update-license.model';
import { AreaOfExpertise } from '../models/area-of-expertise.model';
import { HospitalPrivilege } from '../models/hospital-privilege.model';
import { LanguageSpoken } from '../models/languages-spoken.model';
import { HandicappedAccessibility } from '../models/handicapped-accesibility.model';
import { Email } from '../models/email.model';
import { OperationHours } from '../models/operation-hours.model';
import { OrganizationNpi } from '../models/organization-npi.model';
import { AddAddress } from '../models/add-address.model';
import { UpdateAddress } from '../models/update-address.model';
import {TerminateAddress} from '../models/terminate-address.model';
import {PhoneFax} from '../models/phone-fax.model';
import {ProviderLeavingGroup} from '../models/provider-leaving-group.model';
import {RemoveProviderFromLocation} from '../models/remove-provider-location';
import { Attachment } from '../models/attachment.model';
import {WebAddress} from '../models/web-address.model';
import {Telehealth} from '../models/telehealth.model';
import {Ambulance} from '../models/ambulance.model';
import {UnsolicitedRoster} from '../models/unsolicited-roster';

@Injectable()
export class PayLoadService {
    private userType: number = 0;
    public previousUserType: number = -1;
    public individual: Individual = new Individual();
    public organization: Organization = new Organization();
    public provider: Provider = new Provider();
    public contact: Contact = new Contact();
    public addresses: Address = new Address();
    public specialty: Specialty = new Specialty();
    public tinOwnership: TinOwnership = new TinOwnership();
    public practitionerChangeType: PractitionerChangeType = new PractitionerChangeType();
    public networkParticipation: NetworkParticipation = new NetworkParticipation();
    public ageGenderUpdate: AgeGenderUpdate = new AgeGenderUpdate();
    public acceptingNewPatients: AcceptingNewPatients = new AcceptingNewPatients();
    public terminatePPA: TerminatePPA = new TerminatePPA();
    public individualNpi: IndividualNpi = new IndividualNpi();
    public updateLicense: UpdateLicense = new UpdateLicense();
    public areaOfExpertise: AreaOfExpertise[] = [];
    public hospitalPrivileges: HospitalPrivilege[] = [];
    public languagesSpoken: LanguageSpoken[] = [];
    public targetSystems: any[] = [];
    public handicappedAccessibility = new HandicappedAccessibility();
    public providerNameChange = new PractitionerChangeType();
    public email: Email[] = [];
    public operationHours: OperationHours;
    public organizationNpi: OrganizationNpi = new OrganizationNpi();
    public addAddress: AddAddress;
    public updateAddress: UpdateAddress;
    public terminateAddress: TerminateAddress;
    public phoneFax: PhoneFax[] = [];
    public providerLeavingGroup: ProviderLeavingGroup;
    public removeProviderFromLocation: RemoveProviderFromLocation;
    public unsolicitedRoster: UnsolicitedRoster = new UnsolicitedRoster();
    public attachments: Attachment[] = [];
    public isAttachment: boolean = false;
    public isAttachmentRequired: boolean;
    public isAcceptance: boolean = false;
    public webAddress: WebAddress;
    public telehealth: Telehealth;
    public ambulance: Ambulance;
    public responseStatus: string;
    public responseMessage: string;

    getUserType() {
        return this.userType;
    }

    setUserType(userType: number) {
        this.userType = userType;
    }

    onButtonClick(val: number) {
        this.userType = val;
    }

    initializePayLoadService() {
        this.isAttachment = false;
        this.isAttachmentRequired = false;
        this.isAcceptance = false;
        this.specialty = new Specialty();
        this.tinOwnership = new TinOwnership();
        this.practitionerChangeType = new PractitionerChangeType();
        this.networkParticipation = new NetworkParticipation();
        this.ageGenderUpdate = new AgeGenderUpdate();
        this.acceptingNewPatients = new AcceptingNewPatients();
        this.terminatePPA = new TerminatePPA();
        this.individualNpi = new IndividualNpi();
        this.updateLicense = new UpdateLicense();
        this.areaOfExpertise = [];
        this.hospitalPrivileges = [];
        this.languagesSpoken = [];
        this.handicappedAccessibility = new HandicappedAccessibility();
        this.providerNameChange = new PractitionerChangeType();
        this.email = [];
        this.operationHours = undefined;
        this.organizationNpi = new OrganizationNpi();
        this.addAddress = new AddAddress();
        this.updateAddress = new UpdateAddress();
        this.terminateAddress = new TerminateAddress();
        this.phoneFax = [];
        this.providerLeavingGroup = new ProviderLeavingGroup();
        this.removeProviderFromLocation = new RemoveProviderFromLocation();
        this.unsolicitedRoster = new UnsolicitedRoster();
        this.attachments = [];
        this.webAddress = new WebAddress();
        this.ambulance = new Ambulance();
        this.responseStatus = '';
        this.responseMessage = '';
    }
}
