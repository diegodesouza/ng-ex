
package com.anthem.enterprise.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroupNameChange {	

	@SerializedName("requestActionCd")
    @Expose
    private String requestActionCd;
	@SerializedName("updatedPracticeName")
    @Expose
    private String updatedPracticeName;
    @SerializedName("itemsAttached")
    @Expose
    private String itemsAttached;

    public String getRequestActionCd() {
		return requestActionCd;
	}

	public void setRequestActionCd(String requestActionCd) {
		this.requestActionCd = requestActionCd;
	}
	
	public String getUpdatedPracticeName() {
        return updatedPracticeName;
    }

    public void setUpdatedPracticeName(String updatedPracticeName) {
        this.updatedPracticeName = updatedPracticeName;
    }

    public String getItemsAttached() {
        return itemsAttached;
    }

    public void setItemsAttached(String itemsAttached) {
        this.itemsAttached = itemsAttached;
    }


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GroupNameChange [getRequestActionCd()=");
		builder.append(getRequestActionCd());
		builder.append(", getUpdatedPracticeName()=");
		builder.append(getUpdatedPracticeName());
		builder.append(", getItemsAttached()=");
		builder.append(getItemsAttached());
		builder.append("]");
		return builder.toString();
	}
}
