package com.anthem.common;

import java.util.Properties;
import java.io.InputStream;

/**
 * <p>Title: GetProps</p>
 * <p>Description: Read in and return the requested properties file.
 * </p>
 */
public class GetProps
{
	/**
	 * Loads the property file
	 * @param propertyFileName
	 * @return
	 * @throws Exception
	 */
    public Properties initProps(String propertyFileName)  throws Exception
    {
        java.util.Properties props = new java.util.Properties();
        InputStream in = getClass().getResourceAsStream(propertyFileName);
        props.load(in);
        return props;
    }
}