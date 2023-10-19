package com.anthem.enterprise.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TermAddress {

	@SerializedName("requestActionCd")
	@Expose
	private String requestActionCd;
	@SerializedName("addressType")
	@Expose
	private String addressType;
	@SerializedName("termDate")
	@Expose
	private String termDate;
	@SerializedName("termReason")
	@Expose
	private String termReason;
	@SerializedName("adrSeqNo")
	@Expose
	private String adrSeqNo;
	@SerializedName("pcpIndicator")
	@Expose
	private String pcpIndicator;

	public String getRequestActionCd() {
		return requestActionCd;
	}

	public void setRequestActionCd(String requestActionCd) {
		this.requestActionCd = requestActionCd;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getTermDate() {
		return termDate;
	}

	public void setTermDate(String termDate) {
		this.termDate = termDate;
	}

	public String getTermReason() {
		return termReason;
	}

	public void setTermReason(String termReason) {
		this.termReason = termReason;
	}

	public String getAdrSeqNo() {
		return adrSeqNo;
	}

	public void setAdrSeqNo(String adrSeqNo) {
		this.adrSeqNo = adrSeqNo;
	}

	public String getPcpIndicator() {
		return pcpIndicator;
	}

	public void setPcpIndicator(String pcpIndicator) {
		this.pcpIndicator = pcpIndicator;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TermAddress [getRequestActionCd()=");
		builder.append(getRequestActionCd());
		builder.append(", getAddressType()=");
		builder.append(getAddressType());
		builder.append(", getTermDate()=");
		builder.append(getTermDate());
		builder.append(", getTermReason()=");
		builder.append(getTermReason());
		builder.append(", getAdrSeqNo()=");
		builder.append(getAdrSeqNo());
		builder.append(", getPcpIndicator()=");
		builder.append(getPcpIndicator());
		builder.append("]");
		return builder.toString();
	}

}
