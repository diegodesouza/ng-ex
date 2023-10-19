
package com.anthem.enterprise.client.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdatePhoneNumber {

    @SerializedName("phoneNumber")
    @Expose
    private List<PhoneNumber> phoneNumber = null;

    public List<PhoneNumber> getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(List<PhoneNumber> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UpdatePhoneNumber [getPhoneNumber()=");
		builder.append(getPhoneNumber());
		builder.append("]");
		return builder.toString();
	}

}
