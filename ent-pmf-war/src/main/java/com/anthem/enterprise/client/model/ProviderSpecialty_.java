
package com.anthem.enterprise.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProviderSpecialty_ {

    @SerializedName("specialtyCd")
    @Expose
    private String specialtyCd;
    @SerializedName("specialtyDesc")
    @Expose
    private String specialtyDesc;
    @SerializedName("primaryIndicator")
    @Expose
    private String primaryIndicator;

    public String getSpecialtyCd() {
        return specialtyCd;
    }

    public void setSpecialtyCd(String specialtyCd) {
        this.specialtyCd = specialtyCd;
    }

    public String getSpecialtyDesc() {
        return specialtyDesc;
    }

    public void setSpecialtyDesc(String specialtyDesc) {
        this.specialtyDesc = specialtyDesc;
    }

    public String getPrimaryIndicator() {
        return primaryIndicator;
    }

    public void setPrimaryIndicator(String primaryIndicator) {
        this.primaryIndicator = primaryIndicator;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProviderSpecialty_ [getSpecialtyCd()=");
		builder.append(getSpecialtyCd());
		builder.append(", getSpecialtyDesc()=");
		builder.append(getSpecialtyDesc());
		builder.append(", getPrimaryIndicator()=");
		builder.append(getPrimaryIndicator());
		builder.append("]");
		return builder.toString();
	}

}
