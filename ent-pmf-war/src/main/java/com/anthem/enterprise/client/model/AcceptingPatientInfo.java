
package com.anthem.enterprise.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AcceptingPatientInfo {

    @SerializedName("acceptingNewPatientsIndicator")
    @Expose
    private String acceptingNewPatientsIndicator;
    @SerializedName("ageCriteriaCd")
    @Expose
    private String ageCriteriaCd;
    @SerializedName("ageCriteriaDesc")
    @Expose
    private String ageCriteriaDesc;
    @SerializedName("genderPreferenceCd")
    @Expose
    private String genderPreferenceCd;
    @SerializedName("genderPreferenceDesc")
    @Expose
    private String genderPreferenceDesc;
    @SerializedName("adrSeqNo")
    @Expose
    private String adrSeqNo;

    public String getAcceptingNewPatientsIndicator() {
        return acceptingNewPatientsIndicator;
    }

    public void setAcceptingNewPatientsIndicator(String acceptingNewPatientsIndicator) {
        this.acceptingNewPatientsIndicator = acceptingNewPatientsIndicator;
    }

    public String getAgeCriteriaCd() {
        return ageCriteriaCd;
    }

    public void setAgeCriteriaCd(String ageCriteriaCd) {
        this.ageCriteriaCd = ageCriteriaCd;
    }

    public String getAgeCriteriaDesc() {
        return ageCriteriaDesc;
    }

    public void setAgeCriteriaDesc(String ageCriteriaDesc) {
        this.ageCriteriaDesc = ageCriteriaDesc;
    }

    public String getGenderPreferenceCd() {
        return genderPreferenceCd;
    }

    public void setGenderPreferenceCd(String genderPreferenceCd) {
        this.genderPreferenceCd = genderPreferenceCd;
    }

    public String getGenderPreferenceDesc() {
        return genderPreferenceDesc;
    }

    public void setGenderPreferenceDesc(String genderPreferenceDesc) {
        this.genderPreferenceDesc = genderPreferenceDesc;
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
		builder.append("AcceptingPatientInfo [getAcceptingNewPatientsIndicator()=");
		builder.append(getAcceptingNewPatientsIndicator());
		builder.append(", getAgeCriteriaCd()=");
		builder.append(getAgeCriteriaCd());
		builder.append(", getAgeCriteriaDesc()=");
		builder.append(getAgeCriteriaDesc());
		builder.append(", getGenderPreferenceCd()=");
		builder.append(getGenderPreferenceCd());
		builder.append(", getGenderPreferenceDesc()=");
		builder.append(getGenderPreferenceDesc());
		builder.append(", getAdrSeqNo()=");
		builder.append(getAdrSeqNo());
		builder.append("]");
		return builder.toString();
	}

}
