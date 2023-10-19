
package com.anthem.enterprise.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Email {

    @SerializedName("requestActionCd")
    @Expose
    private String requestActionCd;
    @SerializedName("adrSeqNo")
    @Expose
    private String adrSeqNo;
    @SerializedName("email")
    @Expose
    private String email;

    public String getRequestActionCd() {
        return requestActionCd;
    }

    public void setRequestActionCd(String requestActionCd) {
        this.requestActionCd = requestActionCd;
    }

    public String getAdrSeqNo() {
        return adrSeqNo;
    }

    public void setAdrSeqNo(String adrSeqNo) {
        this.adrSeqNo = adrSeqNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Email [getRequestActionCd()=");
		builder.append(getRequestActionCd());
		builder.append(", getAdrSeqNo()=");
		builder.append(getAdrSeqNo());
		builder.append(", getEmail()=");
		builder.append(getEmail());
		builder.append("]");
		return builder.toString();
	}

}
