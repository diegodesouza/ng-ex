
package com.anthem.enterprise.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProviderLanguage {

    @SerializedName("langCd")
    @Expose
    private String langCd;
    @SerializedName("langDesc")
    @Expose
    private String langDesc;

    public String getLangCd() {
        return langCd;
    }

    public void setLangCd(String langCd) {
        this.langCd = langCd;
    }

    public String getLangDesc() {
        return langDesc;
    }

    public void setLangDesc(String langDesc) {
        this.langDesc = langDesc;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProviderLanguage [getLangCd()=");
		builder.append(getLangCd());
		builder.append(", getLangDesc()=");
		builder.append(getLangDesc());
		builder.append("]");
		return builder.toString();
	}
}
