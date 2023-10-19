
package com.anthem.enterprise.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HandicappedAccessibility {

    @SerializedName("handaccCd")
    @Expose
    private String handaccCd;
    @SerializedName("handaccdesc")
    @Expose
    private String handaccdesc;

    public String getHandaccCd() {
        return handaccCd;
    }

    public void setHandaccCd(String handaccCd) {
        this.handaccCd = handaccCd;
    }

    public String getHandaccdesc() {
        return handaccdesc;
    }

    public void setHandaccdesc(String handaccdesc) {
        this.handaccdesc = handaccdesc;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HandicappedAccessibility [getHandaccCd()=");
		builder.append(getHandaccCd());
		builder.append(", getHandaccdesc()=");
		builder.append(getHandaccdesc());
		builder.append("]");
		return builder.toString();
	}

}
