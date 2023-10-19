
package com.anthem.enterprise.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateURL {

    @SerializedName("requestActionCd")
    @Expose
    private String requestActionCd;
    @SerializedName("url")
    @Expose
    private String url;

    public String getRequestActionCd() {
        return requestActionCd;
    }

    public void setRequestActionCd(String requestActionCd) {
        this.requestActionCd = requestActionCd;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UpdateURL [getRequestActionCd()=");
		builder.append(getRequestActionCd());
		builder.append(", getUrl()=");
		builder.append(getUrl());
		builder.append("]");
		return builder.toString();
	}

}
