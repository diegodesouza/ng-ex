
package com.anthem.enterprise.client.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddChangeEmailAddress {

    @SerializedName("email")
    @Expose
    private List<Email> email = null;

    public List<Email> getEmail() {
        return email;
    }

    public void setEmail(List<Email> email) {
        this.email = email;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddChangeEmailAddress [getEmail()=");
		builder.append(getEmail());
		builder.append("]");
		return builder.toString();
	}

}
