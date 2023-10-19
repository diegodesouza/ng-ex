
package com.anthem.enterprise.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrganizationDetails {

    @SerializedName("effectiveDate")
    @Expose
    private String effectiveDate;
    @SerializedName("organizationTaxId")
    @Expose
    private String organizationTaxId;
    @SerializedName("organizationName")
    @Expose
    private String organizationName;
    @SerializedName("organizationLicenseNumber")
    @Expose
    private String organizationLicenseNumber;
    @SerializedName("organizationNPI")
    @Expose
    private String organizationNPI;
    @SerializedName("dbaName")
    @Expose
    private String dbaName;

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getOrganizationTaxId() {
        return organizationTaxId;
    }

    public void setOrganizationTaxId(String organizationTaxId) {
        this.organizationTaxId = organizationTaxId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationLicenseNumber() {
        return organizationLicenseNumber;
    }

    public void setOrganizationLicenseNumber(String organizationLicenseNumber) {
        this.organizationLicenseNumber = organizationLicenseNumber;
    }

    public String getOrganizationNPI() {
        return organizationNPI;
    }

    public void setOrganizationNPI(String organizationNPI) {
        this.organizationNPI = organizationNPI;
    }

    public String getDbaName() {
        return dbaName;
    }

    public void setDbaName(String dbaName) {
        this.dbaName = dbaName;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrganizationDetails [getEffectiveDate()=");
		builder.append(getEffectiveDate());
		builder.append(", getOrganizationTaxId()=");
		builder.append(getOrganizationTaxId());
		builder.append(", getOrganizationName()=");
		builder.append(getOrganizationName());
		builder.append(", getOrganizationLicenseNumber()=");
		builder.append(getOrganizationLicenseNumber());
		builder.append(", getOrganizationNPI()=");
		builder.append(getOrganizationNPI());
		builder.append(", getDbaName()=");
		builder.append(getDbaName());
		builder.append("]");
		return builder.toString();
	}

}
