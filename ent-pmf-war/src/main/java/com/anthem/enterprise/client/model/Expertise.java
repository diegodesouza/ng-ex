
package com.anthem.enterprise.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Expertise {

    @SerializedName("requestActionCd")
    @Expose
    private String requestActionCd;
    @SerializedName("expertiseCd")
    @Expose
    private String expertiseCd;
    @SerializedName("expertiseDesc")
    @Expose
    private String expertiseDesc;

    public String getRequestActionCd() {
        return requestActionCd;
    }

    public void setRequestActionCd(String requestActionCd) {
        this.requestActionCd = requestActionCd;
    }

    public String getExpertiseCd() {
        return expertiseCd;
    }

    public void setExpertiseCd(String expertiseCd) {
        this.expertiseCd = expertiseCd;
    }

    public String getExpertiseDesc() {
        return expertiseDesc;
    }

    public void setExpertiseDesc(String expertiseDesc) {
        this.expertiseDesc = expertiseDesc;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Expertise [getRequestActionCd()=");
		builder.append(getRequestActionCd());
		builder.append(", getExpertiseCd()=");
		builder.append(getExpertiseCd());
		builder.append(", getExpertiseDesc()=");
		builder.append(getExpertiseDesc());
		builder.append("]");
		return builder.toString();
	}

}
