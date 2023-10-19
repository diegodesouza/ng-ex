
package com.anthem.enterprise.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateAgeGenderPreference {

    @SerializedName("requestActionCd")
    @Expose
    private String requestActionCd;
    @SerializedName("minage")
    @Expose
    private String minage;
    @SerializedName("maxage")
    @Expose
    private String maxage;
    @SerializedName("genderCd")
    @Expose
    private String genderCd;
    @SerializedName("genderDesc")
    @Expose
    private String genderDesc;

    public String getRequestActionCd() {
        return requestActionCd;
    }

    public void setRequestActionCd(String requestActionCd) {
        this.requestActionCd = requestActionCd;
    }

    public String getMinage() {
        return minage;
    }

    public void setMinage(String minage) {
        this.minage = minage;
    }

    public String getMaxage() {
        return maxage;
    }

    public void setMaxage(String maxage) {
        this.maxage = maxage;
    }

    public String getGenderCd() {
        return genderCd;
    }

    public void setGenderCd(String genderCd) {
        this.genderCd = genderCd;
    }

    public String getGenderDesc() {
        return genderDesc;
    }

    public void setGenderDesc(String genderDesc) {
        this.genderDesc = genderDesc;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UpdateAgeGenderPreference [getRequestActionCd()=");
		builder.append(getRequestActionCd());
		builder.append(", getMinage()=");
		builder.append(getMinage());
		builder.append(", getMaxage()=");
		builder.append(getMaxage());
		builder.append(", getGenderCd()=");
		builder.append(getGenderCd());
		builder.append(", getGenderDesc()=");
		builder.append(getGenderDesc());
		builder.append("]");
		return builder.toString();
	}

}
