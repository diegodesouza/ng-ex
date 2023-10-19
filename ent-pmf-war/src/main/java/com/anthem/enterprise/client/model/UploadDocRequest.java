
package com.anthem.enterprise.client.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UploadDocRequest {

    @SerializedName("contentData")
    @Expose
    private String contentData;
    @SerializedName("contentMetadata")
    @Expose
    private List<ContentMetadata> contentMetadata = null;

    public String getContentData() {
        return contentData;
    }

    public void setContentData(String contentData) {
        this.contentData = contentData;
    }

    public UploadDocRequest withContentData(String contentData) {
        this.contentData = contentData;
        return this;
    }

    public List<ContentMetadata> getContentMetadata() {
        return contentMetadata;
    }

    public void setContentMetadata(List<ContentMetadata> contentMetadata) {
        this.contentMetadata = contentMetadata;
    }

    public UploadDocRequest withContentMetadata(List<ContentMetadata> contentMetadata) {
        this.contentMetadata = contentMetadata;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(UploadDocRequest.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("contentData");
        sb.append('=');
        sb.append(((this.contentData == null)?"<null>":this.contentData));
        sb.append(',');
        sb.append("contentMetadata");
        sb.append('=');
        sb.append(((this.contentMetadata == null)?"<null>":this.contentMetadata));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
