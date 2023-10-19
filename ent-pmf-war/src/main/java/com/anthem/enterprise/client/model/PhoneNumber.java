
package com.anthem.enterprise.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhoneNumber {

    @SerializedName("requestActionCd")
    @Expose
    private String requestActionCd;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("phoneTypeCd")
    @Expose
    private String phoneTypeCd;
    @SerializedName("phoneTypeDesc")
    @Expose
    private String phoneTypeDesc;
    @SerializedName("adrSeqNo")
    @Expose
    private String adrSeqNo;

    public String getRequestActionCd() {
        return requestActionCd;
    }

    public void setRequestActionCd(String requestActionCd) {
        this.requestActionCd = requestActionCd;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneTypeCd() {
        return phoneTypeCd;
    }

    public void setPhoneTypeCd(String phoneTypeCd) {
        this.phoneTypeCd = phoneTypeCd;
    }

    public String getPhoneTypeDesc() {
        return phoneTypeDesc;
    }

    public void setPhoneTypeDesc(String phoneTypeDesc) {
        this.phoneTypeDesc = phoneTypeDesc;
    }

    public String getAdrSeqNo() {
        return adrSeqNo;
    }

    public void setAdrSeqNo(String adrSeqNo) {
        this.adrSeqNo = adrSeqNo;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PhoneNumber [getRequestActionCd()=");
		builder.append(getRequestActionCd());
		builder.append(", getPhoneNumber()=");
		builder.append(getPhoneNumber());
		builder.append(", getPhoneTypeCd()=");
		builder.append(getPhoneTypeCd());
		builder.append(", getPhoneTypeDesc()=");
		builder.append(getPhoneTypeDesc());
		builder.append(", getAdrSeqNo()=");
		builder.append(getAdrSeqNo());
		builder.append("]");
		return builder.toString();
	}

}
