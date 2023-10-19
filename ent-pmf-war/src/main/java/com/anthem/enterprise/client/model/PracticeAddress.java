
package com.anthem.enterprise.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PracticeAddress {

    @SerializedName("addressLine1")
    @Expose
    private String addressLine1;
    @SerializedName("addressLine2")
    @Expose
    private String addressLine2;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("zip")
    @Expose
    private String zip;
    @SerializedName("county")
    @Expose
    private String county;
    @SerializedName("adrSeqNo")
    @Expose
    private String adrSeqNo;

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
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
		builder.append("PracticeAddress [getAddressLine1()=");
		builder.append(getAddressLine1());
		builder.append(", getAddressLine2()=");
		builder.append(getAddressLine2());
		builder.append(", getCity()=");
		builder.append(getCity());
		builder.append(", getState()=");
		builder.append(getState());
		builder.append(", getZip()=");
		builder.append(getZip());
		builder.append(", getCounty()=");
		builder.append(getCounty());
		builder.append(", getAdrSeqNo()=");
		builder.append(getAdrSeqNo());
		builder.append("]");
		return builder.toString();
	}

}
