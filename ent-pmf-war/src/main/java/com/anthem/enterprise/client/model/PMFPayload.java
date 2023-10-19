
package com.anthem.enterprise.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PMFPayload {

    @SerializedName("servicePayload")
    @Expose
    private ServicePayload servicePayload;

    public ServicePayload getServicePayload() {
        return servicePayload;
    }

    public void setServicePayload(ServicePayload servicePayload) {
        this.servicePayload = servicePayload;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PMFPayload [getServicePayload()=");
		builder.append(getServicePayload());
		builder.append("]");
		return builder.toString();
	}

}
