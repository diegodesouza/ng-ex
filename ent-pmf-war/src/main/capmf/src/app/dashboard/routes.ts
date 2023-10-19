import {RouterModule, Routes} from '@angular/router';
import {DashboardComponent} from './component';
import {GeneralInfoComponent} from './general-info/component';
import {AttestSubmitComponent} from './attest-submit/component';
import {ChangeTypesComponent} from './change-types/component';
import {SelectUpdatesComponent} from './select-updates/component';
import {EmailComponent} from './change-types/types/email/component';
import {UserReferenceComponent} from './change-types/user-reference/component';
import {PractitionerNameChangeComponent} from './change-types/types/practitioner-name-change/component';
import {NetworkParticipationComponent} from './change-types/types/network-participation/component';
import {ProviderSpecialtyComponent} from './change-types/types/provider-specialty/component';
import {AgeGenderComponent} from './change-types/types/age-gender-update/component';
import {NewPatientComponent} from './change-types/types/accepting-new-patients/component';
import {TaxIdOwnershipComponent} from './change-types/types/tax-id-ownership/component';
import {TerminatePPAComponent} from './change-types/types/terminate-ppa/component';
import {IndividualNpiComponent} from './change-types/types/individual-npi/component';
import {LicenseUpdateComponent} from './change-types/types/license-update/component';
import {ProviderExpertiseComponent} from './change-types/types/area-of-expertise/component';
import {HospitalPrivilegeComponent} from './change-types/types/hospital-privilege/component';
import {LanguagesSpokenComponent} from './change-types/types/languages/component';
import {HandicappedAccessibilityComponent} from './change-types/types/handicapped-accessibility/component';
import {DaysHoursOperationComponent} from './change-types/types/days-hours-operation/component';
import {TelehealthComponent} from './change-types/types/telehealth/component';
import {AmbulanceComponent} from './change-types/types/ambulance/component';
import {ReviewForSubmissionComponent} from './review-for-submission/component';

export const routes: Routes = [
  {
    path: 'dashboard',
    component: DashboardComponent,
    children: [
      { path: 'generalinfo', component: GeneralInfoComponent },
      { path: 'submit', component: AttestSubmitComponent },
      { path: 'changetypes', component: ChangeTypesComponent,
        children: [
          { path: 'userreference', component: UserReferenceComponent, outlet: 'userreference' },
          { path: 'changeaddress', component: EmailComponent, outlet: 'changeaddress' },
          { path: 'providerspecialty', component: ProviderSpecialtyComponent, outlet: 'providerspecialty' },
          { path: 'tin', component: TaxIdOwnershipComponent, outlet: 'tin' },
          { path: 'practitionername', component: PractitionerNameChangeComponent, outlet: 'practitionername' },
          { path: 'networkParticipation', component: NetworkParticipationComponent, outlet: 'networkParticipation' },
          { path: 'agegender', component: AgeGenderComponent, outlet: 'agegender' },
          { path: 'newpatient', component: NewPatientComponent, outlet: 'newpatient' },
          { path: 'ppaTermination', component: TerminatePPAComponent, outlet: 'ppaTermination'},
          { path: 'individualNpi', component: IndividualNpiComponent, outlet: 'individualNpi' },
          { path: 'updateLicense', component: LicenseUpdateComponent, outlet: 'updateLicense' },
          { path: 'providerexpertise', component: ProviderExpertiseComponent, outlet: 'providerexpertise' },
          { path: 'affiliation', component: HospitalPrivilegeComponent, outlet: 'affiliation' },
          { path: 'languages', component: LanguagesSpokenComponent, outlet: 'languages' },
          { path: 'accessibility', component: HandicappedAccessibilityComponent, outlet: 'accessibility' },
          { path: 'operationHours', component: DaysHoursOperationComponent, outlet: 'operationHours' },
          { path: 'telehealth', component: TelehealthComponent, outlet: 'telehealth' },
          { path: 'ambulance', component: AmbulanceComponent, outlet: 'ambulance' }
        ]
      },
      {
        path: 'selectupdates',
        component: SelectUpdatesComponent
      },
      {
        path: 'reviewforsubmission',
        component: ReviewForSubmissionComponent
      }
    ]
  }
];

export const routing = RouterModule.forChild(routes);
