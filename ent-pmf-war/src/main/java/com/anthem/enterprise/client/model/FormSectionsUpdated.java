
package com.anthem.enterprise.client.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FormSectionsUpdated {

    @SerializedName("actionCode")
    @Expose
    private List<String> actionCode = null;

    public List<String> getActionCode() {
        return actionCode;
    }

    public void setActionCode(List<String> actionCode) {
        this.actionCode = actionCode;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FormSectionsUpdated [getActionCode()=");
		builder.append(getActionCode());
		builder.append("]");
		return builder.toString();
	}

}
