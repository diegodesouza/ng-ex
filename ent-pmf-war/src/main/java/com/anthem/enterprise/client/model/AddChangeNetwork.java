
package com.anthem.enterprise.client.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddChangeNetwork {

    @SerializedName("network")
    @Expose
    private List<Network> network = null;

    public List<Network> getNetwork() {
        return network;
    }

    public void setNetwork(List<Network> network) {
        this.network = network;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddChangeNetwork [getNetwork()=");
		builder.append(getNetwork());
		builder.append("]");
		return builder.toString();
	}

}
