package com.anthem.mwpmf;

import java.io.Serializable;
import java.util.List;

public class NYVAProvMaintFormBean implements Serializable {
	
	private String brand = "";
	private String effDate = "";
	private String provLastName = "";
	private String provFirstName = "";
	private String provTaxId = "";
	private String provNPI = "";
	private String pracName = "";
	private String pracTaxId = "";
	private String pracNPI = "";
	private String contactLastName = "";
	private String contactFirstName = "";
	private String contactEmail = "";
	private String contactPhone = "";
	private List<UploadDocListBean> uploadFileListDtls = null;
	private String emailBody = "";
	private String toEmail = "";
	private String allFileNames = "";
	private String allFileSizes = "";
	private String allFileComments = "";
	
	
	
	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}
	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
	/**
	 * @return the toEmail
	 */
	public String getToEmail() {
		return toEmail;
	}
	/**
	 * @param toEmail the toEmail to set
	 */
	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}
	/**
	 * @return the emailBody
	 */
	public String getEmailBody() {
		return emailBody;
	}
	/**
	 * @param emailBody the emailBody to set
	 */
	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}
	/**
	 * @return the effDate
	 */
	public String getEffDate() {
		return effDate;
	}
	/**
	 * @param effDate the effDate to set
	 */
	public void setEffDate(String effDate) {
		this.effDate = effDate;
	}
	/**
	 * @return the contactLastName
	 */
	public String getContactLastName() {
		return contactLastName;
	}
	/**
	 * @param contactLastName the contactLastName to set
	 */
	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}
	/**
	 * @return the contactFirstName
	 */
	public String getContactFirstName() {
		return contactFirstName;
	}
	/**
	 * @param contactFirstName the contactFirstName to set
	 */
	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}
	/**
	 * @return the contactEmail
	 */
	public String getContactEmail() {
		return contactEmail;
	}
	/**
	 * @param contactEmail the contactEmail to set
	 */
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	/**
	 * @return the contactPhone
	 */
	public String getContactPhone() {
		return contactPhone;
	}
	/**
	 * @param contactPhone the contactPhone to set
	 */
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	
	/**
	 * @return the provTaxId
	 */
	public String getProvTaxId() {
		return provTaxId;
	}
	/**
	 * @param provTaxId the provTaxId to set
	 */
	public void setProvTaxId(String provTaxId) {
		this.provTaxId = provTaxId;
	}
	/**
	 * @return the provNPI
	 */
	public String getProvNPI() {
		return provNPI;
	}
	/**
	 * @param provNPI the provNPI to set
	 */
	public void setProvNPI(String provNPI) {
		this.provNPI = provNPI;
	}

	/**
	 * @return the pracName
	 */
	public String getPracName() {
		return pracName;
	}
	/**
	 * @param pracName the pracName to set
	 */
	public void setPracName(String pracName) {
		this.pracName = pracName;
	}
	/**
	 * @return the pracTaxId
	 */
	public String getPracTaxId() {
		return pracTaxId;
	}
	/**
	 * @param pracTaxId the pracTaxId to set
	 */
	public void setPracTaxId(String pracTaxId) {
		this.pracTaxId = pracTaxId;
	}
	/**
	 * @return the pracNPI
	 */
	public String getPracNPI() {
		return pracNPI;
	}
	/**
	 * @param pracNPI the pracNPI to set
	 */
	public void setPracNPI(String pracNPI) {
		this.pracNPI = pracNPI;
	}
	/**
	 * @return the provLastName
	 */
	public String getProvLastName() {
		return provLastName;
	}
	/**
	 * @param provLastName the provLastName to set
	 */
	public void setProvLastName(String provLastName) {
		this.provLastName = provLastName;
	}
	/**
	 * @return the provFirstName
	 */
	public String getProvFirstName() {
		return provFirstName;
	}
	/**
	 * @param provFirstName the provFirstName to set
	 */
	public void setProvFirstName(String provFirstName) {
		this.provFirstName = provFirstName;
	}
	/**
	 * @return the uploadFileListDtls
	 */
	public List<UploadDocListBean> getUploadFileListDtls() {
		return uploadFileListDtls;
	}
	/**
	 * @param uploadFileListDtls the uploadFileListDtls to set
	 */
	public void setUploadFileListDtls(List<UploadDocListBean> uploadFileListDtls) {
		this.uploadFileListDtls = uploadFileListDtls;
	}
	/**
	 * @return the allFileNames
	 */
	public String getAllFileNames() {
		return allFileNames;
	}
	/**
	 * @param allFileNames the allFileNames to set
	 */
	public void setAllFileNames(String allFileNames) {
		this.allFileNames = allFileNames;
	}
	/**
	 * @return the allFileSizes
	 */
	public String getAllFileSizes() {
		return allFileSizes;
	}
	/**
	 * @param allFileSizes the allFileSizes to set
	 */
	public void setAllFileSizes(String allFileSizes) {
		this.allFileSizes = allFileSizes;
	}
	/**
	 * @return the allFileComments
	 */
	public String getAllFileComments() {
		return allFileComments;
	}
	/**
	 * @param allFileComments the allFileComments to set
	 */
	public void setAllFileComments(String allFileComments) {
		this.allFileComments = allFileComments;
	}
	
}
