
package com.anthem.enterprise.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OfficeHour {

    @SerializedName("dayofTheWeek")
    @Expose
    private String dayofTheWeek;
    @SerializedName("openHour")
    @Expose
    private String openHour;
    @SerializedName("closedHour")
    @Expose
    private String closedHour;
    @SerializedName("scheduleTypeCd")
    @Expose
    private String scheduleTypeCd;
    @SerializedName("scheduleTypeDesc")
    @Expose
    private String scheduleTypeDesc;
    @SerializedName("openIndicator")
    @Expose
    private String openIndicator;
    @SerializedName("timeZoneCd")
    @Expose
    private String timeZoneCd;
    @SerializedName("timeZoneDesc")
    @Expose
    private String timeZoneDesc;

    public String getDayofTheWeek() {
        return dayofTheWeek;
    }

    public void setDayofTheWeek(String dayofTheWeek) {
        this.dayofTheWeek = dayofTheWeek;
    }

    public String getOpenHour() {
        return openHour;
    }

    public void setOpenHour(String openHour) {
        this.openHour = openHour;
    }

    public String getClosedHour() {
        return closedHour;
    }

    public void setClosedHour(String closedHour) {
        this.closedHour = closedHour;
    }

    public String getScheduleTypeCd() {
        return scheduleTypeCd;
    }

    public void setScheduleTypeCd(String scheduleTypeCd) {
        this.scheduleTypeCd = scheduleTypeCd;
    }

    public String getScheduleTypeDesc() {
        return scheduleTypeDesc;
    }

    public void setScheduleTypeDesc(String scheduleTypeDesc) {
        this.scheduleTypeDesc = scheduleTypeDesc;
    }

    public String getOpenIndicator() {
        return openIndicator;
    }

    public void setOpenIndicator(String openIndicator) {
        this.openIndicator = openIndicator;
    }

    public String getTimeZoneCd() {
        return timeZoneCd;
    }

    public void setTimeZoneCd(String timeZoneCd) {
        this.timeZoneCd = timeZoneCd;
    }

    public String getTimeZoneDesc() {
        return timeZoneDesc;
    }

    public void setTimeZoneDesc(String timeZoneDesc) {
        this.timeZoneDesc = timeZoneDesc;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OfficeHour [getDayofTheWeek()=");
		builder.append(getDayofTheWeek());
		builder.append(", getOpenHour()=");
		builder.append(getOpenHour());
		builder.append(", getClosedHour()=");
		builder.append(getClosedHour());
		builder.append(", getScheduleTypeCd()=");
		builder.append(getScheduleTypeCd());
		builder.append(", getScheduleTypeDesc()=");
		builder.append(getScheduleTypeDesc());
		builder.append(", getOpenIndicator()=");
		builder.append(getOpenIndicator());
		builder.append(", getTimeZoneCd()=");
		builder.append(getTimeZoneCd());
		builder.append(", getTimeZoneDesc()=");
		builder.append(getTimeZoneDesc());
		builder.append("]");
		return builder.toString();
	}

}
