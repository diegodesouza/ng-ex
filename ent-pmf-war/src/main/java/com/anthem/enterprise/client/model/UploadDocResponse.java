package com.anthem.enterprise.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadDocResponse {

@SerializedName("contentId")
@Expose
private String contentId;
@SerializedName("checksum")
@Expose
private String checksum;
@SerializedName("contentSize")
@Expose
private Integer contentSize;
@SerializedName("additionalParams")
@Expose
private AdditionalParams additionalParams;

public String getContentId() {
return contentId;
}

public void setContentId(String contentId) {
this.contentId = contentId;
}

public UploadDocResponse withContentId(String contentId) {
this.contentId = contentId;
return this;
}

public String getChecksum() {
return checksum;
}

public void setChecksum(String checksum) {
this.checksum = checksum;
}

public UploadDocResponse withChecksum(String checksum) {
this.checksum = checksum;
return this;
}

public Integer getContentSize() {
return contentSize;
}

public void setContentSize(Integer contentSize) {
this.contentSize = contentSize;
}

public UploadDocResponse withContentSize(Integer contentSize) {
this.contentSize = contentSize;
return this;
}

public AdditionalParams getAdditionalParams() {
return additionalParams;
}

public void setAdditionalParams(AdditionalParams additionalParams) {
this.additionalParams = additionalParams;
}

public UploadDocResponse withAdditionalParams(AdditionalParams additionalParams) {
this.additionalParams = additionalParams;
return this;
}

@Override
public String toString() {
StringBuilder sb = new StringBuilder();
sb.append(UploadDocResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
sb.append("contentId");
sb.append('=');
sb.append(((this.contentId == null)?"<null>":this.contentId));
sb.append(',');
sb.append("checksum");
sb.append('=');
sb.append(((this.checksum == null)?"<null>":this.checksum));
sb.append(',');
sb.append("contentSize");
sb.append('=');
sb.append(((this.contentSize == null)?"<null>":this.contentSize));
sb.append(',');
sb.append("additionalParams");
sb.append('=');
sb.append(((this.additionalParams == null)?"<null>":this.additionalParams));
sb.append(',');
if (sb.charAt((sb.length()- 1)) == ',') {
sb.setCharAt((sb.length()- 1), ']');
} else {
sb.append(']');
}
return sb.toString();
}

}