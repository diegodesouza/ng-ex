
package com.anthem.enterprise.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServicesOffered {

    @SerializedName("servicesOfferedCd")
    @Expose
    private String servicesOfferedCd;
    @SerializedName("servicesOfferedDesc")
    @Expose
    private String servicesOfferedDesc;

    public String getServicesOfferedCd() {
        return servicesOfferedCd;
    }

    public void setServicesOfferedCd(String servicesOfferedCd) {
        this.servicesOfferedCd = servicesOfferedCd;
    }

    public String getServicesOfferedDesc() {
        return servicesOfferedDesc;
    }

    public void setServicesOfferedDesc(String servicesOfferedDesc) {
        this.servicesOfferedDesc = servicesOfferedDesc;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServicesOffered [getServicesOfferedCd()=");
		builder.append(getServicesOfferedCd());
		builder.append(", getServicesOfferedDesc()=");
		builder.append(getServicesOfferedDesc());
		builder.append("]");
		return builder.toString();
	}

}
