
package com.anthem.enterprise.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProviderDetails {

    @SerializedName("individualTaxId")
    @Expose
    private String individualTaxId;
    @SerializedName("effectiveDate")
    @Expose
    private String effectiveDate;
    @SerializedName("individualNPI")
    @Expose
    private String individualNPI;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("middleName")
    @Expose
    private String middleName;
    @SerializedName("suffix")
    @Expose
    private String suffix;
    @SerializedName("licenseNumber")
    @Expose
    private String licenseNumber;

    public String getIndividualTaxId() {
        return individualTaxId;
    }

    public void setIndividualTaxId(String individualTaxId) {
        this.individualTaxId = individualTaxId;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getIndividualNPI() {
        return individualNPI;
    }

    public void setIndividualNPI(String individualNPI) {
        this.individualNPI = individualNPI;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProviderDetails [getIndividualTaxId()=");
		builder.append(getIndividualTaxId());
		builder.append(", getEffectiveDate()=");
		builder.append(getEffectiveDate());
		builder.append(", getIndividualNPI()=");
		builder.append(getIndividualNPI());
		builder.append(", getTitle()=");
		builder.append(getTitle());
		builder.append(", getLastName()=");
		builder.append(getLastName());
		builder.append(", getFirstName()=");
		builder.append(getFirstName());
		builder.append(", getMiddleName()=");
		builder.append(getMiddleName());
		builder.append(", getSuffix()=");
		builder.append(getSuffix());
		builder.append(", getLicenseNumber()=");
		builder.append(getLicenseNumber());
		builder.append("]");
		return builder.toString();
	}

}
