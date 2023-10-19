
package com.anthem.enterprise.client.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateOfficeHours {

    @SerializedName("requestActionCd")
    @Expose
    private String requestActionCd;
    @SerializedName("officeHours")
    @Expose
    private List<OfficeHour> officeHours = null;

    public String getRequestActionCd() {
        return requestActionCd;
    }

    public void setRequestActionCd(String requestActionCd) {
        this.requestActionCd = requestActionCd;
    }

    public List<OfficeHour> getOfficeHours() {
        return officeHours;
    }

    public void setOfficeHours(List<OfficeHour> officeHours) {
        this.officeHours = officeHours;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UpdateOfficeHours [getRequestActionCd()=");
		builder.append(getRequestActionCd());
		builder.append(", getOfficeHours()=");
		builder.append(getOfficeHours());
		builder.append("]");
		return builder.toString();
	}

}
