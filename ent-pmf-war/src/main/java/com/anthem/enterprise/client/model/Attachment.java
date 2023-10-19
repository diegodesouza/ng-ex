
package com.anthem.enterprise.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attachment {

    @SerializedName("documentContentKey")
    @Expose
    private String documentContentKey;
    @SerializedName("fileName")
    @Expose
    private String fileName;
    @SerializedName("fileSize")
    @Expose
    private String fileSize;
    @SerializedName("fileType")
    @Expose
    private String fileType;
    @SerializedName("fileComments")
    @Expose
    private String fileComments;

    public String getDocumentContentKey() {
        return documentContentKey;
    }

    public void setDocumentContentKey(String documentContentKey) {
        this.documentContentKey = documentContentKey;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileComments() {
        return fileComments;
    }

    public void setFileComments(String fileComments) {
        this.fileComments = fileComments;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Attachment [getDocumentContentKey()=");
		builder.append(getDocumentContentKey());
		builder.append(", getFileName()=");
		builder.append(getFileName());
		builder.append(", getFileSize()=");
		builder.append(getFileSize());
		builder.append(", getFileType()=");
		builder.append(getFileType());
		builder.append(", getFileComments()=");
		builder.append(getFileComments());
		builder.append("]");
		return builder.toString();
	}

}
