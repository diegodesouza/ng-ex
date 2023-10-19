package com.anthem.enterprise.client.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PracticeAddress__ {

	@SerializedName("addressLine1")
	@Expose
	private String addressLine1;
	@SerializedName("addressLine2")
	@Expose
	private String addressLine2;
	@SerializedName("city")
	@Expose
	private String city;
	@SerializedName("state")
	@Expose
	private String state;
	@SerializedName("zip")
	@Expose
	private String zip;
	@SerializedName("county")
	@Expose
	private String county;
	@SerializedName("faxNumber")
	@Expose
	private String faxNumber;
	@SerializedName("phoneNumber")
	@Expose
	private String phoneNumber;
	@SerializedName("email")
	@Expose
	private String email;
	@SerializedName("acceptingPatientInfo")
	@Expose
	private AcceptingPatientInfo acceptingPatientInfo;
	@SerializedName("handicappedAccessibility")
	@Expose
	private HandicappedAccessibility handicappedAccessibility;
	@SerializedName("publicTransportationAccessibleIndicator")
	@Expose
	private String publicTransportationAccessibleIndicator;
	@SerializedName("providerLanguage")
	@Expose
	private List<ProviderLanguage> providerLanguage = null;
	@SerializedName("officeHours")
	@Expose
	private List<OfficeHour> officeHours = null;
	@SerializedName("servicesOffered")
	@Expose
	private List<ServicesOffered> servicesOffered = null;
	@SerializedName("paremitAddress")
	@Expose
	private RemitAddress paremitAddress;
	@SerializedName("pacorrespondenceAddress")
	@Expose
	private CorrespondenceAddress pacorrespondenceAddress;
	@SerializedName("isCorrAddedToPracticeIndicator")
	@Expose
	private String isCorrAddedToPracticeIndicator;

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public AcceptingPatientInfo getAcceptingPatientInfo() {
		return acceptingPatientInfo;
	}

	public void setAcceptingPatientInfo(AcceptingPatientInfo acceptingPatientInfo) {
		this.acceptingPatientInfo = acceptingPatientInfo;
	}

	public HandicappedAccessibility getHandicappedAccessibility() {
		return handicappedAccessibility;
	}

	public void setHandicappedAccessibility(HandicappedAccessibility handicappedAccessibility) {
		this.handicappedAccessibility = handicappedAccessibility;
	}

	public String getPublicTransportationAccessibleIndicator() {
		return publicTransportationAccessibleIndicator;
	}

	public void setPublicTransportationAccessibleIndicator(String publicTransportationAccessibleIndicator) {
		this.publicTransportationAccessibleIndicator = publicTransportationAccessibleIndicator;
	}

	public List<ProviderLanguage> getProviderLanguage() {
		return providerLanguage;
	}

	public void setProviderLanguage(List<ProviderLanguage> providerLanguage) {
		this.providerLanguage = providerLanguage;
	}

	public List<OfficeHour> getOfficeHours() {
		return officeHours;
	}

	public void setOfficeHours(List<OfficeHour> officeHours) {
		this.officeHours = officeHours;
	}

	public List<ServicesOffered> getServicesOffered() {
		return servicesOffered;
	}

	public void setServicesOffered(List<ServicesOffered> servicesOffered) {
		this.servicesOffered = servicesOffered;
	}

	public RemitAddress getParemitAddress() {
		return paremitAddress;
	}

	public void setParemitAddress(RemitAddress paremitAddress) {
		this.paremitAddress = paremitAddress;
	}

	public CorrespondenceAddress getPacorrespondenceAddress() {
		return pacorrespondenceAddress;
	}

	public void setPacorrespondenceAddress(CorrespondenceAddress pacorrespondenceAddress) {
		this.pacorrespondenceAddress = pacorrespondenceAddress;
	}

	public String getIsCorrAddedToPracticeIndicator() {
		return isCorrAddedToPracticeIndicator;
	}

	public void setIsCorrAddedToPracticeIndicator(String isCorrAddedToPracticeIndicator) {
		this.isCorrAddedToPracticeIndicator = isCorrAddedToPracticeIndicator;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PracticeAddress__ [getAddressLine1()=");
		builder.append(getAddressLine1());
		builder.append(", getAddressLine2()=");
		builder.append(getAddressLine2());
		builder.append(", getCity()=");
		builder.append(getCity());
		builder.append(", getState()=");
		builder.append(getState());
		builder.append(", getZip()=");
		builder.append(getZip());
		builder.append(", getCounty()=");
		builder.append(getCounty());
		builder.append(", getFaxNumber()=");
		builder.append(getFaxNumber());
		builder.append(", getPhoneNumber()=");
		builder.append(getPhoneNumber());
		builder.append(", getEmail()=");
		builder.append(getEmail());
		builder.append(", getAcceptingPatientInfo()=");
		builder.append(getAcceptingPatientInfo());
		builder.append(", getHandicappedAccessibility()=");
		builder.append(getHandicappedAccessibility());
		builder.append(", getPublicTransportationAccessibleIndicator()=");
		builder.append(getPublicTransportationAccessibleIndicator());
		builder.append(", getProviderLanguage()=");
		builder.append(getProviderLanguage());
		builder.append(", getOfficeHours()=");
		builder.append(getOfficeHours());
		builder.append(", getServicesOffered()=");
		builder.append(getServicesOffered());
		builder.append(", getParemitAddress()=");
		builder.append(getParemitAddress());
		builder.append(", getPacorrespondenceAddress()=");
		builder.append(getPacorrespondenceAddress());
		builder.append(", getIsCorrAddedToPracticeIndicator()=");
		builder.append(getIsCorrAddedToPracticeIndicator());		
		builder.append("]");
		return builder.toString();
	}

}
