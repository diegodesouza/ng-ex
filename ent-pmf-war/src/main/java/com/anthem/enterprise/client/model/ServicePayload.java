
package com.anthem.enterprise.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServicePayload {

    @SerializedName("serviceRequest")
    @Expose
    private ServiceRequest serviceRequest;

    public ServiceRequest getServiceRequest() {
        return serviceRequest;
    }

    public void setServiceRequest(ServiceRequest serviceRequest) {
        this.serviceRequest = serviceRequest;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServicePayload [getServiceRequest()=");
		builder.append(getServiceRequest());
		builder.append("]");
		return builder.toString();
	}

}
