package com.anthem.enterprise.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddAddress {

	@SerializedName("requestActionCd")
	@Expose
	private String requestActionCd;
	@SerializedName("practiceAddress")
	@Expose
	private PracticeAddress__ practiceAddress;
	@SerializedName("remitAddress")
	@Expose
	private RemitAddress remitAddress;
	@SerializedName("correspondenceAddress")
	@Expose
	private CorrespondenceAddress correspondenceAddress;
	@SerializedName("pcpIndicator")
	@Expose
	private String pcpIndicator;
	@SerializedName("addressType")
	@Expose
	private String addressType;	

	public String getRequestActionCd() {
		return requestActionCd;
	}

	public void setRequestActionCd(String requestActionCd) {
		this.requestActionCd = requestActionCd;
	}

	public PracticeAddress__ getPracticeAddress() {
		return practiceAddress;
	}

	public void setPracticeAddress(PracticeAddress__ practiceAddress) {
		this.practiceAddress = practiceAddress;
	}

	public RemitAddress getRemitAddress() {
		return remitAddress;
	}

	public void setRemitAddress(RemitAddress remitAddress) {
		this.remitAddress = remitAddress;
	}

	public CorrespondenceAddress getCorrespondenceAddress() {
		return correspondenceAddress;
	}

	public void setCorrespondenceAddress(CorrespondenceAddress correspondenceAddress) {
		this.correspondenceAddress = correspondenceAddress;
	}

	public String getPcpIndicator() {
		return pcpIndicator;
	}

	public void setPcpIndicator(String pcpIndicator) {
		this.pcpIndicator = pcpIndicator;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddAddress [getRequestActionCd()=");
		builder.append(getRequestActionCd());
		builder.append(", getPracticeAddress()=");
		builder.append(getPracticeAddress());
		builder.append(", getRemitAddress()=");
		builder.append(getRemitAddress());
		builder.append(", getCorrespondenceAddress()=");
		builder.append(getCorrespondenceAddress());
		builder.append(", getPcpIndicator()=");
		builder.append(getPcpIndicator());
		builder.append(", getAddressType()=");
		builder.append(getAddressType());
		builder.append("]");
		return builder.toString();
	}
}
