
package com.anthem.enterprise.client.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddChangeProviderSpecialty {

    @SerializedName("requestActionCd")
    @Expose
    private String requestActionCd;
    @SerializedName("providerSpecialty")
    @Expose
    private List<ProviderSpecialty_> providerSpecialty = null;
    @SerializedName("pcpindicator")
    @Expose
    private String pcpindicator;

    public String getRequestActionCd() {
        return requestActionCd;
    }

    public void setRequestActionCd(String requestActionCd) {
        this.requestActionCd = requestActionCd;
    }

    public List<ProviderSpecialty_> getProviderSpecialty() {
        return providerSpecialty;
    }

    public void setProviderSpecialty(List<ProviderSpecialty_> providerSpecialty) {
        this.providerSpecialty = providerSpecialty;
    }

    public String getPcpindicator() {
        return pcpindicator;
    }

    public void setPcpindicator(String pcpindicator) {
        this.pcpindicator = pcpindicator;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddChangeProviderSpecialty [getRequestActionCd()=");
		builder.append(getRequestActionCd());
		builder.append(", getProviderSpecialty()=");
		builder.append(getProviderSpecialty());
		builder.append(", getPcpindicator()=");
		builder.append(getPcpindicator());
		builder.append("]");
		return builder.toString();
	}
}
