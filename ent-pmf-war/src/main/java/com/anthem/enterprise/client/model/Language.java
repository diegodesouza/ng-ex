
package com.anthem.enterprise.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Language {

    @SerializedName("requestActionCd")
    @Expose
    private String requestActionCd;
    @SerializedName("langCd")
    @Expose
    private String langCd;
    @SerializedName("langDesc")
    @Expose
    private String langDesc;
    @SerializedName("adrSeqNo")
    @Expose
    private String adrSeqNo;

    public String getRequestActionCd() {
        return requestActionCd;
    }

    public void setRequestActionCd(String requestActionCd) {
        this.requestActionCd = requestActionCd;
    }

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

    public String getAdrSeqNo() {
        return adrSeqNo;
    }

    public void setAdrSeqNo(String adrSeqNo) {
        this.adrSeqNo = adrSeqNo;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Language [getRequestActionCd()=");
		builder.append(getRequestActionCd());
		builder.append(", getLangCd()=");
		builder.append(getLangCd());
		builder.append(", getLangDesc()=");
		builder.append(getLangDesc());
		builder.append(", getAdrSeqNo()=");
		builder.append(getAdrSeqNo());
		builder.append("]");
		return builder.toString();
	}

}
