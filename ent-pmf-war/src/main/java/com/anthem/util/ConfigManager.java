package com.anthem.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <p>Title: ConfigManager</p>
 * <p>Description: This contains functions for the Log4J and Connection configurations.</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company:Anthem Insurance </p>
 * @author Isac
 * @version 1.0
 */

public class ConfigManager
{

    private static ConfigManager instance = new ConfigManager();
    public static final String ERRORLOGINSTANCE = "PMFErrors";
    public static final String LOGCONFIGFILE = "log4j.properties";
    public static final String DATALOGINSTANCE = "DataLog" ;
    public static final String DATALOGCONFIGFILE = "log4jdata.properties" ;
    public static final String BATCHEXTRACTPROPERTYFILE = "/PMFBatchExtract.properties";
    public static final String PMFCONNECTIONPROPERTYFILE = "/pmfconnection.properties";

    private ConfigManager()
    {
    }
    public static ConfigManager getInstance()
    {
		if (instance ==null)
		{
			instance = new ConfigManager();
		}
        return instance;
    }

    public  Logger getErrorLoggerInstance()
    {
        return LogManager.getLogger(ERRORLOGINSTANCE);
    }

    public  Logger getDataLoggerInstance()
    {
         return  LogManager.getLogger(DATALOGINSTANCE);
    }

    private String getBatchExtractPropertyFile()
    {
        return BATCHEXTRACTPROPERTYFILE;
    }

    private String getPMFConnectionPropertyFile()
    {
        return PMFCONNECTIONPROPERTYFILE;
    }

    public Properties getBatchExtractProperties() throws IOException
    {
        return loadProperty( getBatchExtractPropertyFile());
    }

    public Properties getPMFConnectionProperties() throws IOException
    {
        return loadProperty( getPMFConnectionPropertyFile());
    }

    private Properties loadProperty(String propertyFileName) throws IOException
    {
        Properties props = new Properties();
        InputStream in = getClass().getResourceAsStream(propertyFileName);
        props.load(in);
        return props;
    }

    private void configureLog4JProperties(String propertfyFile)
    {
       try
        {
    	   LoggerContext context = (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);	
    	   File file = new File(propertfyFile);	
    	   context.setConfigLocation(file.toURI());
        }
        catch(Exception e)
        {
            System.out.println(StringUtils.stack2string(e));
        }
        return;
    }

	public Logger configureErrorLog()
    {
        Logger logger = null;
        try
        {
            configureLog4JProperties(LOGCONFIGFILE);
            logger =  getErrorLoggerInstance();
        }
        catch(Exception e)
        {
            System.out.println("Error configuring error log");
        }
        return logger;
    }

	/**
	* Method to log a write the data log
	*/
	public Logger configureDataLog()
    {
        Logger logger = null;
        try
        {
            configureLog4JProperties(DATALOGCONFIGFILE);
            logger =  getDataLoggerInstance();
        }
        catch(Exception e)
        {
            System.out.println("Error configuring data log");
        }
        return logger;
    }
}