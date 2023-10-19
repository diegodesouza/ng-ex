package com.anthem.enterprise.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdditionalParams {

@SerializedName("F_DOCNUM")
@Expose
private String fDocnum;
@SerializedName("DCN")
@Expose
private String dcn;

public String getFDocnum() {
return fDocnum;
}

public void setFDocnum(String fDocnum) {
this.fDocnum = fDocnum;
}

public AdditionalParams withFDocnum(String fDocnum) {
this.fDocnum = fDocnum;
return this;
}

public String getDcn() {
return dcn;
}

public void setDcn(String dcn) {
this.dcn = dcn;
}

public AdditionalParams withDcn(String dcn) {
this.dcn = dcn;
return this;
}

@Override
public String toString() {
StringBuilder sb = new StringBuilder();
sb.append(AdditionalParams.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
sb.append("fDocnum");
sb.append('=');
sb.append(((this.fDocnum == null)?"<null>":this.fDocnum));
sb.append(',');
sb.append("dcn");
sb.append('=');
sb.append(((this.dcn == null)?"<null>":this.dcn));
sb.append(',');
if (sb.charAt((sb.length()- 1)) == ',') {
sb.setCharAt((sb.length()- 1), ']');
} else {
sb.append(']');
}
return sb.toString();
}

}