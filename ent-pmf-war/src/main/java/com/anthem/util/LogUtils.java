package com.anthem.util;


import org.apache.logging.log4j.Logger;

/**
 * <p>Title:Log Utilities </p>
 * <p>Description: Contains the functions for logging.</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Anthem Insurance </p>
 * @author unascribed
 * @version 1.0
 */

public class LogUtils
{

    public LogUtils()
    {
    }

	/**
	* Method to log a standard message on an exception
	*/
    public static void logException(Logger logger, String message, Exception e )
    {
        logger.error(DateUtils.getCurrentDateStr() + message + e.getMessage());
        logger.error(StringUtils.stack2string(e));
    }

}