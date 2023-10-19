
package com.anthem.enterprise.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddChangeNpi {

    @SerializedName("requestActionCd")
    @Expose
    private String requestActionCd;
    @SerializedName("remitAddress")
    @Expose
    private RemitAddress remitAddress;
    @SerializedName("newNpi")
    @Expose
    private String newNpi;

    public String getRequestActionCd() {
        return requestActionCd;
    }

    public void setRequestActionCd(String requestActionCd) {
        this.requestActionCd = requestActionCd;
    }

    public RemitAddress getRemitAddress() {
        return remitAddress;
    }

    public void setRemitAddress(RemitAddress remitAddress) {
        this.remitAddress = remitAddress;
    }

    public String getNewNpi() {
        return newNpi;
    }

    public void setNewNpi(String newNpi) {
        this.newNpi = newNpi;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddChangeNpi [getRequestActionCd()=");
		builder.append(getRequestActionCd());
		builder.append(", getRemitAddress()=");
		builder.append(getRemitAddress());
		builder.append(", getNewNpi()=");
		builder.append(getNewNpi());
		builder.append("]");
		return builder.toString();
	}

}
