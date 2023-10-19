
package com.anthem.enterprise.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateAcceptingNewPatients {

    @SerializedName("requestActionCd")
    @Expose
    private String requestActionCd;
    @SerializedName("acceptingPatientInfo")
    @Expose
    private AcceptingPatientInfo acceptingPatientInfo;

    public String getRequestActionCd() {
        return requestActionCd;
    }

    public void setRequestActionCd(String requestActionCd) {
        this.requestActionCd = requestActionCd;
    }    

    public AcceptingPatientInfo getAcceptingPatientInfo() {
        return acceptingPatientInfo;
    }

    public void setAcceptingPatientInfo(AcceptingPatientInfo acceptingPatientInfo) {
        this.acceptingPatientInfo = acceptingPatientInfo;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UpdateAcceptingNewPatients [getRequestActionCd()=");
		builder.append(getRequestActionCd());
		builder.append(", getAcceptingPatientInfo()=");
		builder.append(getAcceptingPatientInfo());
		builder.append("]");
		return builder.toString();
	}

}
