
package com.anthem.enterprise.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateProviderName {

    @SerializedName("requestActionCd")
    @Expose
    private String requestActionCd;
    @SerializedName("updatedFirstName")
    @Expose
    private String updatedFirstName;
    @SerializedName("updatedLastName")
    @Expose
    private String updatedLastName;
    @SerializedName("updatedMiddleName")
    @Expose
    private String updatedMiddleName;
    @SerializedName("updatedSuffix")
    @Expose
    private String updatedSuffix;
    @SerializedName("updatedTitle")
    @Expose
    private String updatedTitle;
    @SerializedName("updatedLicenseNumber")
    @Expose
    private String updatedLicenseNumber;

    public String getRequestActionCd() {
        return requestActionCd;
    }

    public void setRequestActionCd(String requestActionCd) {
        this.requestActionCd = requestActionCd;
    }

    public String getUpdatedFirstName() {
        return updatedFirstName;
    }

    public void setUpdatedFirstName(String updatedFirstName) {
        this.updatedFirstName = updatedFirstName;
    }

    public String getUpdatedLastName() {
        return updatedLastName;
    }

    public void setUpdatedLastName(String updatedLastName) {
        this.updatedLastName = updatedLastName;
    }

    public String getUpdatedMiddleName() {
        return updatedMiddleName;
    }

    public void setUpdatedMiddleName(String updatedMiddleName) {
        this.updatedMiddleName = updatedMiddleName;
    }

    public String getUpdatedSuffix() {
        return updatedSuffix;
    }

    public void setUpdatedSuffix(String updatedSuffix) {
        this.updatedSuffix = updatedSuffix;
    }

    public String getUpdatedTitle() {
        return updatedTitle;
    }

    public void setUpdatedTitle(String updatedTitle) {
        this.updatedTitle = updatedTitle;
    }

    public String getUpdatedLicenseNumber() {
        return updatedLicenseNumber;
    }

    public void setUpdatedLicenseNumber(String updatedLicenseNumber) {
        this.updatedLicenseNumber = updatedLicenseNumber;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UpdateProviderName [getRequestActionCd()=");
		builder.append(getRequestActionCd());
		builder.append(", getUpdatedFirstName()=");
		builder.append(getUpdatedFirstName());
		builder.append(", getUpdatedLastName()=");
		builder.append(getUpdatedLastName());
		builder.append(", getUpdatedMiddleName()=");
		builder.append(getUpdatedMiddleName());
		builder.append(", getUpdatedSuffix()=");
		builder.append(getUpdatedSuffix());
		builder.append(", getUpdatedTitle()=");
		builder.append(getUpdatedTitle());
		builder.append(", getUpdatedLicenseNumber()=");
		builder.append(getUpdatedLicenseNumber());
		builder.append("]");
		return builder.toString();
	}

}
