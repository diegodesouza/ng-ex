
package com.anthem.enterprise.client.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateLanguage {

	@SerializedName("requestActionCd")
    @Expose
    private String requestActionCd;
	@SerializedName("language")
    @Expose
    private List<Language> language = null;

    public String getRequestActionCd() {
		return requestActionCd;
	}

	public void setRequestActionCd(String requestActionCd) {
		this.requestActionCd = requestActionCd;
	}

	public List<Language> getLanguage() {
        return language;
    }

    public void setLanguage(List<Language> language) {
        this.language = language;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UpdateLanguage [requestActionCd=");
		builder.append(requestActionCd);
		builder.append(", language=");
		builder.append(language);
		builder.append(", getRequestActionCd()=");
		builder.append(getRequestActionCd());
		builder.append(", getLanguage()=");
		builder.append(getLanguage());
		builder.append(", getClass()=");
		builder.append(getClass());
		builder.append(", hashCode()=");
		builder.append(hashCode());
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
