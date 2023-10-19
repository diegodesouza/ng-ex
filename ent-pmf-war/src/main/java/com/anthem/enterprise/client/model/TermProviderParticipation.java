
package com.anthem.enterprise.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TermProviderParticipation {

    @SerializedName("requestActionCd")
    @Expose
    private String requestActionCd;
    @SerializedName("managedCarePcpIndicator")
    @Expose
    private String managedCarePcpIndicator;
    @SerializedName("pcpIndicator")
    @Expose
    private String pcpIndicator;
    @SerializedName("termDate")
    @Expose
    private String termDate;
    @SerializedName("termReasonCd")
    @Expose
    private String termReasonCd;
    @SerializedName("termReasonDesc")
    @Expose
    private String termReasonDesc;
    @SerializedName("transfertoFirstName")
    @Expose
    private String transfertoFirstName;
    @SerializedName("transfertoLastName")
    @Expose
    private String transfertoLastName;
    @SerializedName("transfertoMiddleName")
    @Expose
    private String transfertoMiddleName;
    @SerializedName("transfertoProviderSpecialty")
    @Expose
    private TransfertoProviderSpecialty transfertoProviderSpecialty;
    @SerializedName("transfertoPracticeAddress")
    @Expose
    private TransfertoPracticeAddress transfertoPracticeAddress;

    public String getRequestActionCd() {
        return requestActionCd;
    }

    public void setRequestActionCd(String requestActionCd) {
        this.requestActionCd = requestActionCd;
    }

    public String getManagedCarePcpIndicator() {
        return managedCarePcpIndicator;
    }

    public void setManagedCarePcpIndicator(String managedCarePcpIndicator) {
        this.managedCarePcpIndicator = managedCarePcpIndicator;
    }

    public String getPcpIndicator() {
        return pcpIndicator;
    }

    public void setPcpIndicator(String pcpIndicator) {
        this.pcpIndicator = pcpIndicator;
    }

    public String getTermDate() {
        return termDate;
    }

    public void setTermDate(String termDate) {
        this.termDate = termDate;
    }

    public String getTermReasonCd() {
        return termReasonCd;
    }

    public void setTermReasonCd(String termReasonCd) {
        this.termReasonCd = termReasonCd;
    }

    public String getTermReasonDesc() {
        return termReasonDesc;
    }

    public void setTermReasonDesc(String termReasonDesc) {
        this.termReasonDesc = termReasonDesc;
    }

    public String getTransfertoFirstName() {
        return transfertoFirstName;
    }

    public void setTransfertoFirstName(String transfertoFirstName) {
        this.transfertoFirstName = transfertoFirstName;
    }

    public String getTransfertoLastName() {
        return transfertoLastName;
    }

    public void setTransfertoLastName(String transfertoLastName) {
        this.transfertoLastName = transfertoLastName;
    }

    public String getTransfertoMiddleName() {
        return transfertoMiddleName;
    }

    public void setTransfertoMiddleName(String transfertoMiddleName) {
        this.transfertoMiddleName = transfertoMiddleName;
    }

    public TransfertoProviderSpecialty getTransfertoProviderSpecialty() {
        return transfertoProviderSpecialty;
    }

    public void setTransfertoProviderSpecialty(TransfertoProviderSpecialty transfertoProviderSpecialty) {
        this.transfertoProviderSpecialty = transfertoProviderSpecialty;
    }

    public TransfertoPracticeAddress getTransfertoPracticeAddress() {
        return transfertoPracticeAddress;
    }

    public void setTransfertoPracticeAddress(TransfertoPracticeAddress transfertoPracticeAddress) {
        this.transfertoPracticeAddress = transfertoPracticeAddress;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TermProviderParticipation [getRequestActionCd()=");
		builder.append(getRequestActionCd());
		builder.append(", getManagedCarePcpIndicator()=");
		builder.append(getManagedCarePcpIndicator());
		builder.append(", getPcpIndicator()=");
		builder.append(getPcpIndicator());
		builder.append(", getTermDate()=");
		builder.append(getTermDate());
		builder.append(", getTermReasonCd()=");
		builder.append(getTermReasonCd());
		builder.append(", getTermReasonDesc()=");
		builder.append(getTermReasonDesc());
		builder.append(", getTransfertoFirstName()=");
		builder.append(getTransfertoFirstName());
		builder.append(", getTransfertoLastName()=");
		builder.append(getTransfertoLastName());
		builder.append(", getTransfertoMiddleName()=");
		builder.append(getTransfertoMiddleName());
		builder.append(", getTransfertoProviderSpecialty()=");
		builder.append(getTransfertoProviderSpecialty());
		builder.append(", getTransfertoPracticeAddress()=");
		builder.append(getTransfertoPracticeAddress());
		builder.append("]");
		return builder.toString();
	}

}
