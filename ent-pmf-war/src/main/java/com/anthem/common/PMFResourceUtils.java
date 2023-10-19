package com.anthem.common;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class PMFResourceUtils {
	private static Properties webConfigProp = null;
	public static final String RESOURCE_PROPERTIES = "pmfConfig.properties";

	private PMFResourceUtils() {

	}

	public static Properties getProperties() {
		try {
			PMFResourceUtils.init();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return webConfigProp;
	}

	protected static void init() throws Exception {

		if (webConfigProp == null) {
			webConfigProp = loadProperties(RESOURCE_PROPERTIES);
		}
		if (webConfigProp == null)
			throw new Exception("Resource not found : pmfConfig.properties");
		// TODO Auto-generated method stub

	}

	public static Properties loadProperties(String name) throws Exception
	{
		InputStream is = null;	
		try  
		{
			is = PMFResourceUtils.class.getResourceAsStream(name);
		    if (is == null) {
			    is = Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
		    }
	
	    	if (is == null)
			{
	            System.out.println("PMF config property file not found");
				return null;
			}

			
		    Properties props = new Properties();
			props.load(is);
			is.close();
			return props;
	    }
        catch (IOException ioe)
        {
        	System.out.println("IOException: Error loading PMF config properties");
		}finally {
			 if(is != null){
				 is.close();
			 }
		}

		return null;
	}
	
	
}