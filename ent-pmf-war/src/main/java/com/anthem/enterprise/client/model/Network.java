
package com.anthem.enterprise.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Network {

    @SerializedName("requestActionCd")
    @Expose
    private String requestActionCd;
    @SerializedName("lobCd")
    @Expose
    private String lobCd;

    public String getRequestActionCd() {
        return requestActionCd;
    }

    public void setRequestActionCd(String requestActionCd) {
        this.requestActionCd = requestActionCd;
    }

    public String getLobCd() {
        return lobCd;
    }

    public void setLobCd(String lobCd) {
        this.lobCd = lobCd;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Network [getRequestActionCd()=");
		builder.append(getRequestActionCd());
		builder.append(", getLobCd()=");
		builder.append(getLobCd());
		builder.append("]");
		return builder.toString();
	}

}
