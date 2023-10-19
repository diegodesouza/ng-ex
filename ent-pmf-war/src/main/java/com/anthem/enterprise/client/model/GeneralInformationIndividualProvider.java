
package com.anthem.enterprise.client.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneralInformationIndividualProvider {

    @SerializedName("providerDetails")
    @Expose
    private ProviderDetails providerDetails;
    @SerializedName("practiceAddress")
    @Expose
    private List<PracticeAddress> practiceAddress = null;
    @SerializedName("contactInfo")
    @Expose
    private ContactInfo contactInfo;

    public ProviderDetails getProviderDetails() {
        return providerDetails;
    }

    public void setProviderDetails(ProviderDetails providerDetails) {
        this.providerDetails = providerDetails;
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
		builder.append("GeneralInformationIndividualProvider [getProviderDetails()=");
		builder.append(getProviderDetails());
		builder.append(", getPracticeAddress()=");
		builder.append(getPracticeAddress());
		builder.append(", getContactInfo()=");
		builder.append(getContactInfo());
		builder.append("]");
		return builder.toString();
	}

}
