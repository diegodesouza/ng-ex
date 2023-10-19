
package com.anthem.enterprise.client.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddChangeHandicappedAccessibility {

    @SerializedName("handAcc")
    @Expose
    private List<HandAcc> handAcc = null;

    public List<HandAcc> getHandAcc() {
        return handAcc;
    }

    public void setHandAcc(List<HandAcc> handAcc) {
        this.handAcc = handAcc;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddChangeHandicappedAccessibility [getHandAcc()=");
		builder.append(getHandAcc());
		builder.append("]");
		return builder.toString();
	}

}
