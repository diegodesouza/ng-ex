
package com.anthem.enterprise.client.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddChangeAreasofExpertise {

    @SerializedName("expertise")
    @Expose
    private List<Expertise> expertise = null;

    public List<Expertise> getExpertise() {
        return expertise;
    }

    public void setExpertise(List<Expertise> expertise) {
        this.expertise = expertise;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddChangeAreasofExpertise [getExpertise()=");
		builder.append(getExpertise());
		builder.append("]");
		return builder.toString();
	}

}
