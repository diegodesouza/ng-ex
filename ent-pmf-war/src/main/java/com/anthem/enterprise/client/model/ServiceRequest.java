package com.anthem.enterprise.client.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceRequest {

	@SerializedName("formState")
	@Expose
	private String formState;
	@SerializedName("formType")
	@Expose
	private String formType;
	@SerializedName("providerType")
	@Expose
	private String providerType;
	@SerializedName("formSectionsUpdated")
	@Expose
	private FormSectionsUpdated formSectionsUpdated;
	@SerializedName("targetSystems")
	@Expose
	private List<String> targetSystems = null;
	@SerializedName("attachments")
	@Expose
	private List<Attachment> attachments = null;
	@SerializedName("generalInformationIndividualProvider")
	@Expose
	private GeneralInformationIndividualProvider generalInformationIndividualProvider;
	@SerializedName("generalInformationOrganizationProvider")
	@Expose
	private GeneralInformationOrganizationProvider generalInformationOrganizationProvider;
	@SerializedName("updateLanguage")
	@Expose
	private UpdateLanguage updateLanguage;
	@SerializedName("updateAcceptingNewPatients")
	@Expose
	private UpdateAcceptingNewPatients updateAcceptingNewPatients;
	@SerializedName("updateProviderName")
	@Expose
	private UpdateProviderName updateProviderName;
	@SerializedName("updateHospitalAffiliation")
	@Expose
	private UpdateHospitalAffiliation updateHospitalAffiliation;
	@SerializedName("addAddress")
	@Expose
	private AddAddress addAddress;
	@SerializedName("updatePhoneNumber")
	@Expose
	private UpdatePhoneNumber updatePhoneNumber;
	@SerializedName("addLicenseNumber")
	@Expose
	private AddLicenseNumber addLicenseNumber;
	@SerializedName("addChangeNetwork")
	@Expose
	private AddChangeNetwork addChangeNetwork;
	@SerializedName("addChangeAreasofExpertise")
	@Expose
	private AddChangeAreasofExpertise addChangeAreasofExpertise;
	@SerializedName("addChangeEmailAddress")
	@Expose
	private AddChangeEmailAddress addChangeEmailAddress;
	@SerializedName("addChangeProviderSpecialty")
	@Expose
	private AddChangeProviderSpecialty addChangeProviderSpecialty;
	@SerializedName("updateAgeGenderPreference")
	@Expose
	private UpdateAgeGenderPreference updateAgeGenderPreference;
	@SerializedName("updateOfficeHours")
	@Expose
	private UpdateOfficeHours updateOfficeHours;
	@SerializedName("updateTaxId")
	@Expose
	private UpdateTaxId updateTaxId;
	@SerializedName("termProviderParticipation")
	@Expose
	private TermProviderParticipation termProviderParticipation;
	@SerializedName("addChangeNpi")
	@Expose
	private AddChangeNpi addChangeNpi;
	@SerializedName("addChangeHandicappedAccessibility")
	@Expose
	private AddChangeHandicappedAccessibility addChangeHandicappedAccessibility;
	@SerializedName("providerLeavingGroup")
	@Expose
	private ProviderLeavingGroup providerLeavingGroup;
	@SerializedName("removeFromLocation")
	@Expose
	private RemoveFromLocation removeFromLocation;
	@SerializedName("termAddress")
	@Expose
	private TermAddress termAddress;
	@SerializedName("groupNameChange")
	@Expose
	private GroupNameChange groupNameChange;
	@SerializedName("updateURL")
	@Expose
	private UpdateURL updateURL;
	@SerializedName("changeAddress")
	@Expose
	private ChangeAddress changeAddress;
	@SerializedName("unsolicitedRoster")
	@Expose
	private UnsolicitedRoster unsolicitedRoster;

	public String getFormState() {
		return formState;
	}

	public void setFormState(String formState) {
		this.formState = formState;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public String getProviderType() {
		return providerType;
	}

	public void setProviderType(String providerType) {
		this.providerType = providerType;
	}

	public FormSectionsUpdated getFormSectionsUpdated() {
		return formSectionsUpdated;
	}

	public void setFormSectionsUpdated(FormSectionsUpdated formSectionsUpdated) {
		this.formSectionsUpdated = formSectionsUpdated;
	}

	public List<String> getTargetSystems() {
		return targetSystems;
	}

	public void setTargetSystems(List<String> targetSystems) {
		this.targetSystems = targetSystems;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public GeneralInformationIndividualProvider getGeneralInformationIndividualProvider() {
		return generalInformationIndividualProvider;
	}

	public void setGeneralInformationIndividualProvider(
			GeneralInformationIndividualProvider generalInformationIndividualProvider) {
		this.generalInformationIndividualProvider = generalInformationIndividualProvider;
	}

	public GeneralInformationOrganizationProvider getGeneralInformationOrganizationProvider() {
		return generalInformationOrganizationProvider;
	}

	public void setGeneralInformationOrganizationProvider(
			GeneralInformationOrganizationProvider generalInformationOrganizationProvider) {
		this.generalInformationOrganizationProvider = generalInformationOrganizationProvider;
	}

	public UpdateLanguage getUpdateLanguage() {
		return updateLanguage;
	}

	public void setUpdateLanguage(UpdateLanguage updateLanguage) {
		this.updateLanguage = updateLanguage;
	}

	public UpdateAcceptingNewPatients getUpdateAcceptingNewPatients() {
		return updateAcceptingNewPatients;
	}

	public void setUpdateAcceptingNewPatients(UpdateAcceptingNewPatients updateAcceptingNewPatients) {
		this.updateAcceptingNewPatients = updateAcceptingNewPatients;
	}

	public UpdateProviderName getUpdateProviderName() {
		return updateProviderName;
	}

	public void setUpdateProviderName(UpdateProviderName updateProviderName) {
		this.updateProviderName = updateProviderName;
	}

	public UpdateHospitalAffiliation getUpdateHospitalAffiliation() {
		return updateHospitalAffiliation;
	}

	public void setUpdateHospitalAffiliation(UpdateHospitalAffiliation updateHospitalAffiliation) {
		this.updateHospitalAffiliation = updateHospitalAffiliation;
	}

	public AddAddress getAddAddress() {
		return addAddress;
	}

	public void setAddAddress(AddAddress addAddress) {
		this.addAddress = addAddress;
	}

	public UpdatePhoneNumber getUpdatePhoneNumber() {
		return updatePhoneNumber;
	}

	public void setUpdatePhoneNumber(UpdatePhoneNumber updatePhoneNumber) {
		this.updatePhoneNumber = updatePhoneNumber;
	}

	public AddLicenseNumber getAddLicenseNumber() {
		return addLicenseNumber;
	}

	public void setAddLicenseNumber(AddLicenseNumber addLicenseNumber) {
		this.addLicenseNumber = addLicenseNumber;
	}

	public AddChangeNetwork getAddChangeNetwork() {
		return addChangeNetwork;
	}

	public void setAddChangeNetwork(AddChangeNetwork addChangeNetwork) {
		this.addChangeNetwork = addChangeNetwork;
	}

	public AddChangeAreasofExpertise getAddChangeAreasofExpertise() {
		return addChangeAreasofExpertise;
	}

	public void setAddChangeAreasofExpertise(AddChangeAreasofExpertise addChangeAreasofExpertise) {
		this.addChangeAreasofExpertise = addChangeAreasofExpertise;
	}

	public AddChangeEmailAddress getAddChangeEmailAddress() {
		return addChangeEmailAddress;
	}

	public void setAddChangeEmailAddress(AddChangeEmailAddress addChangeEmailAddress) {
		this.addChangeEmailAddress = addChangeEmailAddress;
	}

	public AddChangeProviderSpecialty getAddChangeProviderSpecialty() {
		return addChangeProviderSpecialty;
	}

	public void setAddChangeProviderSpecialty(AddChangeProviderSpecialty addChangeProviderSpecialty) {
		this.addChangeProviderSpecialty = addChangeProviderSpecialty;
	}

	public UpdateAgeGenderPreference getUpdateAgeGenderPreference() {
		return updateAgeGenderPreference;
	}

	public void setUpdateAgeGenderPreference(UpdateAgeGenderPreference updateAgeGenderPreference) {
		this.updateAgeGenderPreference = updateAgeGenderPreference;
	}

	public UpdateOfficeHours getUpdateOfficeHours() {
		return updateOfficeHours;
	}

	public void setUpdateOfficeHours(UpdateOfficeHours updateOfficeHours) {
		this.updateOfficeHours = updateOfficeHours;
	}

	public UpdateTaxId getUpdateTaxId() {
		return updateTaxId;
	}

	public void setUpdateTaxId(UpdateTaxId updateTaxId) {
		this.updateTaxId = updateTaxId;
	}

	public TermProviderParticipation getTermProviderParticipation() {
		return termProviderParticipation;
	}

	public void setTermProviderParticipation(TermProviderParticipation termProviderParticipation) {
		this.termProviderParticipation = termProviderParticipation;
	}

	public AddChangeNpi getAddChangeNpi() {
		return addChangeNpi;
	}

	public void setAddChangeNpi(AddChangeNpi addChangeNpi) {
		this.addChangeNpi = addChangeNpi;
	}

	public AddChangeHandicappedAccessibility getAddChangeHandicappedAccessibility() {
		return addChangeHandicappedAccessibility;
	}

	public void setAddChangeHandicappedAccessibility(AddChangeHandicappedAccessibility addChangeHandicappedAccessibility) {
		this.addChangeHandicappedAccessibility = addChangeHandicappedAccessibility;
	}

	public ProviderLeavingGroup getProviderLeavingGroup() {
		return providerLeavingGroup;
	}

	public void setProviderLeavingGroup(ProviderLeavingGroup providerLeavingGroup) {
		this.providerLeavingGroup = providerLeavingGroup;
	}

	public RemoveFromLocation getRemoveFromLocation() {
		return removeFromLocation;
	}

	public void setRemoveFromLocation(RemoveFromLocation removeFromLocation) {
		this.removeFromLocation = removeFromLocation;
	}

	public TermAddress getTermAddress() {
		return termAddress;
	}

	public void setTermAddress(TermAddress termAddress) {
		this.termAddress = termAddress;
	}

	public GroupNameChange getGroupNameChange() {
		return groupNameChange;
	}

	public void setGroupNameChange(GroupNameChange groupNameChange) {
		this.groupNameChange = groupNameChange;
	}

	public UpdateURL getUpdateURL() {
		return updateURL;
	}

	public void setUpdateURL(UpdateURL updateURL) {
		this.updateURL = updateURL;
	}

	public ChangeAddress getChangeAddress() {
		return changeAddress;
	}

	public void setChangeAddress(ChangeAddress changeAddress) {
		this.changeAddress = changeAddress;
	}

	public UnsolicitedRoster getUnsolicitedRoster() {
		return unsolicitedRoster;
	}

	public void setUnsolicitedRoster(UnsolicitedRoster unsolicitedRoster) {
		this.unsolicitedRoster = unsolicitedRoster;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServiceRequest [getFormState()=");
		builder.append(getFormState());
		builder.append(", getFormType()=");
		builder.append(getFormType());
		builder.append(", getProviderType()=");
		builder.append(getProviderType());
		builder.append(", getFormSectionsUpdated()=");
		builder.append(getFormSectionsUpdated());
		builder.append(", getTargetSystems()=");
		builder.append(getTargetSystems());
		builder.append(", getAttachments()=");
		builder.append(getAttachments());
		builder.append(", getGeneralInformationIndividualProvider()=");
		builder.append(getGeneralInformationIndividualProvider());
		builder.append(", getGeneralInformationOrganizationProvider()=");
		builder.append(getGeneralInformationOrganizationProvider());
		builder.append(", getUpdateLanguage()=");
		builder.append(getUpdateLanguage());
		builder.append(", getUpdateAcceptingNewPatients()=");
		builder.append(getUpdateAcceptingNewPatients());
		builder.append(", getUpdateProviderName()=");
		builder.append(getUpdateProviderName());
		builder.append(", getUpdateHospitalAffiliation()=");
		builder.append(getUpdateHospitalAffiliation());
		builder.append(", getAddAddress()=");
		builder.append(getAddAddress());
		builder.append(", getUpdatePhoneNumber()=");
		builder.append(getUpdatePhoneNumber());
		builder.append(", getAddLicenseNumber()=");
		builder.append(getAddLicenseNumber());
		builder.append(", getAddChangeNetwork()=");
		builder.append(getAddChangeNetwork());
		builder.append(", getAddChangeAreasofExpertise()=");
		builder.append(getAddChangeAreasofExpertise());
		builder.append(", getAddChangeEmailAddress()=");
		builder.append(getAddChangeEmailAddress());
		builder.append(", getAddChangeProviderSpecialty()=");
		builder.append(getAddChangeProviderSpecialty());
		builder.append(", getUpdateAgeGenderPreference()=");
		builder.append(getUpdateAgeGenderPreference());
		builder.append(", getUpdateOfficeHours()=");
		builder.append(getUpdateOfficeHours());
		builder.append(", getUpdateTaxId()=");
		builder.append(getUpdateTaxId());
		builder.append(", getTermProviderParticipation()=");
		builder.append(getTermProviderParticipation());
		builder.append(", getAddChangeNpi()=");
		builder.append(getAddChangeNpi());
		builder.append(", getAddChangeHandicappedAccessibility()=");
		builder.append(getAddChangeHandicappedAccessibility());
		builder.append(", getProviderLeavingGroup()=");
		builder.append(getProviderLeavingGroup());
		builder.append(", getRemoveFromLocation()=");
		builder.append(getRemoveFromLocation());
		builder.append(", getTermAddress()=");
		builder.append(getTermAddress());
		builder.append(", getGroupNameChange()=");
		builder.append(getGroupNameChange());
		builder.append(", getUpdateURL()=");
		builder.append(getUpdateURL());
		builder.append(", getChangeAddress()=");
		builder.append(getChangeAddress());
		builder.append(", getUnsolicitedRoster()=");
		builder.append(getUnsolicitedRoster());
		builder.append("]");
		return builder.toString();
	}

}
