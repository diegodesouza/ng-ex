package com.anthem.enterprise.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UnsolicitedRoster {
	@SerializedName("requestActionCd")
	@Expose
	private String requestActionCd;

	@SerializedName("numberOfProviders")
	@Expose
	private String numberOfProviders;

	@SerializedName("delegatedCredentialingEntity")
	@Expose
	private String delegatedCredentialingEntity;

	public String getRequestActionCd() {
		return requestActionCd;
	}

	public void setRequestActionCd(String requestActionCd) {
		this.requestActionCd = requestActionCd;
	}

	public String getNumberOfProviders() {
		return numberOfProviders;
	}

	public void setNumberOfProviders(String numberOfProviders) {
		this.numberOfProviders = numberOfProviders;
	}

	public String getDelegatedCredentialingEntity() {
		return delegatedCredentialingEntity;
	}

	public void setDelegatedCredentialingEntity(String delegatedCredentialingEntity) {
		this.delegatedCredentialingEntity = delegatedCredentialingEntity;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UnsolicitedRoster [getRequestActionCd()=");
		builder.append(getRequestActionCd());
		builder.append(", getNumberOfProviders()=");
		builder.append(getNumberOfProviders());
		builder.append(", getDelegatedCredentialingEntity()=");
		builder.append(getDelegatedCredentialingEntity());
		builder.append("]");
		return builder.toString();
	}

}
