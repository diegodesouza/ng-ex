/**
 * Created by AD94882 on 4/18/17.
 */
import {NgModule} from '@angular/core';
import {DashboardComponent} from './component';
import {CommonAppModule} from '../common';
import {FormsModule} from '@angular/forms';
import {GeneralInfoComponent} from './general-info/component';
import {AttestSubmitComponent} from './attest-submit/component';
import {ChangeTypesComponent} from './change-types/component';
import {SelectUpdatesComponent} from './select-updates/component';
import {RouterModule} from '@angular/router';
import {CommonModule} from '@angular/common';
import {PayLoadService} from '../common/services/payload-all.service';
import {UxModule} from '../uxd/v2/ux/uxModule';
import {EmailComponent} from './change-types/types/email/component';
import {ProviderSpecialtyComponent} from './change-types/types/provider-specialty/component';
import {AddressComponent} from './general-info/sub-components/practice-addr.component';
import {PractitionerNameChangeComponent} from './change-types/types/practitioner-name-change/component';
import {NetworkParticipationComponent} from './change-types/types/network-participation/component';
import {AgeGenderComponent} from './change-types/types/age-gender-update/component';
import {NewPatientComponent} from './change-types/types/accepting-new-patients/component';
import {TelehealthComponent} from './change-types/types/telehealth/component';
import {AmbulanceComponent} from './change-types/types/ambulance/component';
import {IndividualNpiComponent} from './change-types/types/individual-npi/component';
import {routing} from './routes';
import {AddressChangeComponent} from './change-types/types/sub-components/address-change.component';
import {UserReferenceComponent} from './change-types/user-reference/component';
import {TaxIdOwnershipComponent} from './change-types/types/tax-id-ownership/component';
import {TerminatePPAComponent} from './change-types/types/terminate-ppa/component';
import {LicenseUpdateComponent} from './change-types/types/license-update/component';
import {ProviderExpertiseComponent} from './change-types/types/area-of-expertise/component';
import {HospitalPrivilegeComponent} from './change-types/types/hospital-privilege/component';
import {LanguagesSpokenComponent} from './change-types/types/languages/component';
import {CrossJSONMapper} from '../common/models/payload-output/cross-json-mapper.service';
import {HandicappedAccessibilityComponent} from './change-types/types/handicapped-accessibility/component';
import {TextMaskModule} from 'angular2-text-mask';
import {DaysHoursOperationComponent} from './change-types/types/days-hours-operation/component';
import {AddAddressComponent} from './change-types/types/add-address/component';
import {UpdateAddressComponent} from './change-types/types/update-address/component';
import {TerminateAddressComponent} from './change-types/types/terminate-address/component';
import {PhoneFaxComponent} from './change-types/types/phone-fax/component';
import {OrganizationNameChangeComponent} from './change-types/types/organization-name-change/component';
import {ProviderLeavingGroupComponent} from './change-types/types/provider-leaving-group/component';
import {RemoveProviderFromLocationComponent} from './change-types/types/remove-provider-location/component';
import {ProviderChangeComponent} from './change-types/types/sub-components/provider-change.component';
import {AttachmentComponent} from './change-types/attachments/component';
import {WebAddressChangeComponent} from './change-types/types/web-address/component';
import {ServicesOfferedComponent} from './change-types/types/sub-components/services-offered.component';
import {FormErrorsPipe} from '../common/pipes/errors.pipe';
import {FormErrorsPipeWithParent} from '../common/pipes/errors-pipe-sub.pipe';
import {LastFourPipe} from '../common/pipes/last-four.pipe';
import {OptionsPipe} from '../common/pipes/options.pipe';
import {Accordion} from '../common/component/accordion.component';
import {AccordionGroup} from '../common/component/accordion.component';
import {Select2Module} from 'ng2-select2';
import {FormValidations} from '../common/validations/form-validations';
import {ReviewForSubmissionComponent} from './review-for-submission/component';
import {ReviewForSubmissionGeneralInfoComponent} from './review-for-submission/general-info/component';
import {ReviewForSubmissionContactInfoComponent} from './review-for-submission/general-info/contact-info/component';
import {ReviewForSubmissionPracticeDetailComponent} from './review-for-submission/general-info/practice-detail/component';
import {ReviewForSubmissionChangeTypesComponent} from './review-for-submission/change-types/component';
import {ReviewForSubmissionAcceptingNewPatientsComponent} from './review-for-submission/change-types/types/accepting-new-patients/component';
import {ReviewForSubmissionTelehealthComponent} from './review-for-submission/change-types/types/telehealth/component';
import {ReviewForSubmissionAmbulanceComponent} from './review-for-submission/change-types/types/ambulance/component';
import {ReviewForSubmissionAddAddressComponent} from './review-for-submission/change-types/types/add-address/component';
import {ReviewForSubmissionHandicappedAccessibilityComponent} from './review-for-submission/change-types/types/handicapped-accessibility/component';
import {ReviewForSubmissionPublicTransportationComponent} from './review-for-submission/change-types/types/public-transportation/component';
import {ReviewForSubmissionDaysHoursOperationComponent} from './review-for-submission/change-types/types/days-hours-operation/component';
import {ReviewForSubmissionServicesOfferedComponent} from './review-for-submission/change-types/types/services-offered/component';
import {ReviewForSubmissionLanguagesComponent} from './review-for-submission/change-types/types/languages/component';
import {ReviewForSubmissionAreaOfExpertiseComponent} from './review-for-submission/change-types/types/area-of-expertise/component';
import {ReviewForSubmissionEmailComponent} from './review-for-submission/change-types/types/email/component';
import {ReviewForSubmissionHospitalPrivilegeComponent} from './review-for-submission/change-types/types/hospital-privilege/component';
import {ReviewForSubmissionAddressComponent} from './review-for-submission/change-types/types/address/component';
import {ReviewForSubmissionAddAddressInfoComponent} from './review-for-submission/change-types/types/add-address/sub-components/info/component';
import {ReviewForSubmissionRemoveProviderLocationComponent} from './review-for-submission/change-types/types/remove-provider-location/component';
import {ReviewForSubmissionTerminateAddressComponent} from './review-for-submission/change-types/types/terminate-address/component';
import {ReviewForSubmissionLicenseUpdateComponent} from './review-for-submission/change-types/types/license-update/component';
import {ReviewForSubmissionNPIComponent} from './review-for-submission/change-types/types/npi/component';
import {ReviewForSubmissionNetworkParticipationComponent} from './review-for-submission/change-types/types/network-participation/component';
import {ReviewForSubmissionPhoneFaxComponent} from './review-for-submission/change-types/types/phone-fax/component';
import {ReviewForSubmissionTerminatePPAComponent} from './review-for-submission/change-types/types/terminate-ppa/component';
import {ReviewForSubmissionProviderSpecialtyComponent} from './review-for-submission/change-types/types/provider-specialty/component';
import {ReviewForSubmissionTaxIdComponent} from './review-for-submission/change-types/types/tax-id/component';
import {ReviewForSubmissionPractitionerNameChangeComponent} from './review-for-submission/change-types/types/practitioner-name-change/component';
import {ReviewForSubmissionBasicInfoComponent} from './review-for-submission/change-types/types/basic-info/component';
import {ReviewForSubmissionProviderLeavingGroupComponent} from './review-for-submission/change-types/types/provider-leaving-group/component';
import {ReviewForSubmissionOrganizationNameChangeComponent} from './review-for-submission/change-types/types/organization-name-change/component';
import {ReviewForSubmissionWebAddressComponent} from './review-for-submission/change-types/types/web-address/component';
import {ReviewForSubmissionAgeGenderUpdateComponent} from './review-for-submission/change-types/types/age-gender-update/component';
import {ReviewPrint} from '../common/directive/review-print.directive';
import {ReviewForSubmissionAttachmentComponent} from './review-for-submission/change-types/types/attachments/component';
import {ScrollService} from '../common/services/scroll.service';
import {PCPIndicatorComponent} from './change-types/types/add-address/sub-components/pcp/component';
import {UnmaskModelDirective} from '../common/directive/unmask-model.directive';
import {MaskPhonePipe} from '../common/pipes/mask-phone.pipe';
import {UnsolicitedRosterComponent} from './change-types/types/unsolicited-roster/component';
import {ReviewForSubmissionUnsolicitedRosterComponent} from './review-for-submission/change-types/types/unsolicited-roster/component';

@NgModule({
  imports: [
    CommonAppModule,
    RouterModule,
    CommonModule,
    UxModule,
    FormsModule,
    TextMaskModule,
    routing,
    Select2Module
  ],
  declarations: [
    DashboardComponent,
    GeneralInfoComponent,
    AttestSubmitComponent,
    ChangeTypesComponent,
    SelectUpdatesComponent,
    EmailComponent,
    ProviderSpecialtyComponent,
    AddressComponent,
    AddressChangeComponent,
    UserReferenceComponent,
    PractitionerNameChangeComponent,
    NetworkParticipationComponent,
    AgeGenderComponent,
    NewPatientComponent,
    TelehealthComponent,
    AmbulanceComponent,
    TaxIdOwnershipComponent,
    TerminatePPAComponent,
    IndividualNpiComponent,
    LicenseUpdateComponent,
    ProviderExpertiseComponent,
    HospitalPrivilegeComponent,
    LanguagesSpokenComponent,
    HandicappedAccessibilityComponent,
    DaysHoursOperationComponent,
    AddAddressComponent,
    UpdateAddressComponent,
    TerminateAddressComponent,
    PhoneFaxComponent,
    OrganizationNameChangeComponent,
    ProviderLeavingGroupComponent,
    RemoveProviderFromLocationComponent,
    UnsolicitedRosterComponent,
    ProviderChangeComponent,
    AttachmentComponent,
    WebAddressChangeComponent,
    ServicesOfferedComponent,
    PCPIndicatorComponent,
    ReviewForSubmissionComponent,
    ReviewForSubmissionGeneralInfoComponent,
    ReviewForSubmissionPracticeDetailComponent,
    ReviewForSubmissionAddressComponent,
    ReviewForSubmissionContactInfoComponent,
    ReviewForSubmissionChangeTypesComponent,
    ReviewForSubmissionAcceptingNewPatientsComponent,
    ReviewForSubmissionTelehealthComponent,
    ReviewForSubmissionAmbulanceComponent,
    ReviewForSubmissionAddAddressComponent,
    ReviewForSubmissionHandicappedAccessibilityComponent,
    ReviewForSubmissionPublicTransportationComponent,
    ReviewForSubmissionDaysHoursOperationComponent,
    ReviewForSubmissionServicesOfferedComponent,
    ReviewForSubmissionLanguagesComponent,
    ReviewForSubmissionAreaOfExpertiseComponent,
    ReviewForSubmissionEmailComponent,
    ReviewForSubmissionHospitalPrivilegeComponent,
    ReviewForSubmissionRemoveProviderLocationComponent,
    ReviewForSubmissionUnsolicitedRosterComponent,
    ReviewForSubmissionAddAddressInfoComponent,
    ReviewForSubmissionTerminateAddressComponent,
    ReviewForSubmissionLicenseUpdateComponent,
    ReviewForSubmissionNPIComponent,
    ReviewForSubmissionNetworkParticipationComponent,
    ReviewForSubmissionPhoneFaxComponent,
    ReviewForSubmissionTerminatePPAComponent,
    ReviewForSubmissionProviderSpecialtyComponent,
    ReviewForSubmissionTaxIdComponent,
    ReviewForSubmissionPractitionerNameChangeComponent,
    ReviewForSubmissionBasicInfoComponent,
    ReviewForSubmissionProviderLeavingGroupComponent,
    ReviewForSubmissionOrganizationNameChangeComponent,
    ReviewForSubmissionWebAddressComponent,
    ReviewForSubmissionAgeGenderUpdateComponent,
    ReviewForSubmissionAttachmentComponent,
    FormErrorsPipe,
    FormErrorsPipeWithParent,
    LastFourPipe,
    OptionsPipe,
    Accordion,
    AccordionGroup,
    ReviewPrint,
    UnmaskModelDirective,
    MaskPhonePipe
  ],
  exports: [
    DashboardComponent,
    GeneralInfoComponent,
    AttestSubmitComponent,
    ChangeTypesComponent,
    SelectUpdatesComponent,
    EmailComponent,
    ProviderSpecialtyComponent,
    AddressComponent,
    AddressChangeComponent,
    UserReferenceComponent,
    PractitionerNameChangeComponent,
    NetworkParticipationComponent,
    AgeGenderComponent,
    NewPatientComponent,
    TelehealthComponent,
    AmbulanceComponent,
    TaxIdOwnershipComponent,
    TerminatePPAComponent,
    IndividualNpiComponent,
    LicenseUpdateComponent,
    ProviderExpertiseComponent,
    HospitalPrivilegeComponent,
    LanguagesSpokenComponent,
    DaysHoursOperationComponent,
    AddAddressComponent,
    UpdateAddressComponent,
    TerminateAddressComponent,
    PhoneFaxComponent,
    OrganizationNameChangeComponent,
    ProviderLeavingGroupComponent,
    RemoveProviderFromLocationComponent,
    UnsolicitedRosterComponent,
    ProviderChangeComponent,
    AttachmentComponent,
    WebAddressChangeComponent,
    ServicesOfferedComponent,
    PCPIndicatorComponent,
    ReviewForSubmissionComponent,
    ReviewForSubmissionGeneralInfoComponent,
    ReviewForSubmissionPracticeDetailComponent,
    ReviewForSubmissionAddressComponent,
    ReviewForSubmissionContactInfoComponent,
    ReviewForSubmissionChangeTypesComponent,
    ReviewForSubmissionAcceptingNewPatientsComponent,
    ReviewForSubmissionTelehealthComponent,
    ReviewForSubmissionAmbulanceComponent,
    ReviewForSubmissionAddAddressComponent,
    ReviewForSubmissionHandicappedAccessibilityComponent,
    ReviewForSubmissionPublicTransportationComponent,
    ReviewForSubmissionDaysHoursOperationComponent,
    ReviewForSubmissionServicesOfferedComponent,
    ReviewForSubmissionLanguagesComponent,
    ReviewForSubmissionAreaOfExpertiseComponent,
    ReviewForSubmissionEmailComponent,
    ReviewForSubmissionHospitalPrivilegeComponent,
    ReviewForSubmissionRemoveProviderLocationComponent,
    ReviewForSubmissionUnsolicitedRosterComponent,
    ReviewForSubmissionAddAddressInfoComponent,
    ReviewForSubmissionTerminateAddressComponent,
    ReviewForSubmissionLicenseUpdateComponent,
    ReviewForSubmissionNetworkParticipationComponent,
    ReviewForSubmissionPhoneFaxComponent,
    ReviewForSubmissionTerminatePPAComponent,
    ReviewForSubmissionProviderSpecialtyComponent,
    ReviewForSubmissionTaxIdComponent,
    ReviewForSubmissionPractitionerNameChangeComponent,
    ReviewForSubmissionBasicInfoComponent,
    ReviewForSubmissionProviderLeavingGroupComponent,
    ReviewForSubmissionOrganizationNameChangeComponent,
    ReviewForSubmissionWebAddressComponent,
    ReviewForSubmissionAgeGenderUpdateComponent,
    ReviewForSubmissionAttachmentComponent,
    Accordion,
    AccordionGroup,
    ReviewPrint,
    UnmaskModelDirective,
    MaskPhonePipe
  ],
  providers: [
    PayLoadService,
    CrossJSONMapper,
    FormValidations,
    ScrollService
  ]
})
export class DashboardModule {}
