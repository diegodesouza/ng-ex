
package com.anthem.enterprise.client.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateHospitalAffiliation {

    @SerializedName("privilege")
    @Expose
    private List<Privilege> privilege = null;

    public List<Privilege> getPrivilege() {
        return privilege;
    }

    public void setPrivilege(List<Privilege> privilege) {
        this.privilege = privilege;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UpdateHospitalAffiliation [getPrivilege()=");
		builder.append(getPrivilege());
		builder.append("]");
		return builder.toString();
	}

}
