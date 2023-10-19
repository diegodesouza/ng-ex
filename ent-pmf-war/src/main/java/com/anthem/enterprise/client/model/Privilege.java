
package com.anthem.enterprise.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Privilege {

    @SerializedName("requestActionCd")
    @Expose
    private String requestActionCd;
    @SerializedName("hospitalPrivilegeCd")
    @Expose
    private String hospitalPrivilegeCd;
    @SerializedName("hospitalPrivilegeDesc")
    @Expose
    private String hospitalPrivilegeDesc;
    @SerializedName("hospitalName")
    @Expose
    private String hospitalName;

    public String getRequestActionCd() {
        return requestActionCd;
    }

    public void setRequestActionCd(String requestActionCd) {
        this.requestActionCd = requestActionCd;
    }

    public String getHospitalPrivilegeCd() {
        return hospitalPrivilegeCd;
    }

    public void setHospitalPrivilegeCd(String hospitalPrivilegeCd) {
        this.hospitalPrivilegeCd = hospitalPrivilegeCd;
    }

    public String getHospitalPrivilegeDesc() {
        return hospitalPrivilegeDesc;
    }

    public void setHospitalPrivilegeDesc(String hospitalPrivilegeDesc) {
        this.hospitalPrivilegeDesc = hospitalPrivilegeDesc;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Privilege [getRequestActionCd()=");
		builder.append(getRequestActionCd());
		builder.append(", getHospitalPrivilegeCd()=");
		builder.append(getHospitalPrivilegeCd());
		builder.append(", getHospitalPrivilegeDesc()=");
		builder.append(getHospitalPrivilegeDesc());
		builder.append(", getHospitalName()=");
		builder.append(getHospitalName());
		builder.append("]");
		return builder.toString();
	}

}
