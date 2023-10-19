
package com.anthem.enterprise.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddLicenseNumber {

    @SerializedName("requestActionCd")
    @Expose
    private String requestActionCd;
    @SerializedName("licenseNumber")
    @Expose
    private String licenseNumber;
    @SerializedName("issuingState")
    @Expose
    private String issuingState;

    public String getRequestActionCd() {
        return requestActionCd;
    }

    public void setRequestActionCd(String requestActionCd) {
        this.requestActionCd = requestActionCd;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getIssuingState() {
        return issuingState;
    }

    public void setIssuingState(String issuingState) {
        this.issuingState = issuingState;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddLicenseNumber [getRequestActionCd()=");
		builder.append(getRequestActionCd());
		builder.append(", getLicenseNumber()=");
		builder.append(getLicenseNumber());
		builder.append(", getIssuingState()=");
		builder.append(getIssuingState());
		builder.append("]");
		return builder.toString();
	}

}
