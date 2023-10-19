package com.anthem.util;

import java.text.DateFormat;
/**
 * <p>Title: </p>
 * <p>Description: Utilities for date manipulation</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Anthem</p>
 * @author unascribed
 * @version 1.0
 */

public class DateUtils
{
    public DateUtils()
    {
    }
	/**
	 * Get date as a formatted string
	 * @return
	 */
    public static String getCurrentDateStr()
    {
		java.util.Date date = new java.util.Date();
		DateFormat dateTimeFormat = java.text.DateFormat.getDateTimeInstance();
		return dateTimeFormat.format(date);
    }
	/**
	 *Get the date in SQL format for the prepared statements
	 */
    public static java.sql.Date getCurrentSQLDate()
    {
         java.sql.Date  currentDate = new java.sql.Date(System.currentTimeMillis());
         return currentDate;
    }

}