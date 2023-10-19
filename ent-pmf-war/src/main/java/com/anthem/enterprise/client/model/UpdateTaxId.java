
package com.anthem.enterprise.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateTaxId {

    @SerializedName("requestActionCd")
    @Expose
    private String requestActionCd;
    @SerializedName("npi")
    @Expose
    private String npi;
    @SerializedName("newTaxId")
    @Expose
    private String newTaxId;

    public String getRequestActionCd() {
        return requestActionCd;
    }

    public void setRequestActionCd(String requestActionCd) {
        this.requestActionCd = requestActionCd;
    }

    public String getNpi() {
        return npi;
    }

    public void setNpi(String npi) {
        this.npi = npi;
    }

    public String getNewTaxId() {
        return newTaxId;
    }

    public void setNewTaxId(String newTaxId) {
        this.newTaxId = newTaxId;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UpdateTaxId [getRequestActionCd()=");
		builder.append(getRequestActionCd());
		builder.append(", getNpi()=");
		builder.append(getNpi());
		builder.append(", getNewTaxId()=");
		builder.append(getNewTaxId());
		builder.append("]");
		return builder.toString();
	}

}
