
package com.anthem.enterprise.client.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneralInformationOrganizationProvider {

    @SerializedName("organizationDetails")
    @Expose
    private OrganizationDetails organizationDetails;
    @SerializedName("practiceAddress")
    @Expose
    private List<PracticeAddress> practiceAddress = null;
    @SerializedName("contactInfo")
    @Expose
    private ContactInfo contactInfo;

    public OrganizationDetails getOrganizationDetails() {
        return organizationDetails;
    }

    public void setOrganizationDetails(OrganizationDetails organizationDetails) {
        this.organizationDetails = organizationDetails;
    }

    public List<PracticeAddress> getPracticeAddress() {
        return practiceAddress;
    }

    public void setPracticeAddress(List<PracticeAddress> practiceAddress) {
        this.practiceAddress = practiceAddress;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GeneralInformationOrganizationProvider [getOrganizationDetails()=");
		builder.append(getOrganizationDetails());
		builder.append(", getPracticeAddress()=");
		builder.append(getPracticeAddress());
		builder.append(", getContactInfo()=");
		builder.append(getContactInfo());
		builder.append("]");
		return builder.toString();
	}
}
