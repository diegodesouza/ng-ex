package com.anthem.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * <p>Title: String Utilities</p>
 * <p>Description:Contains string manipulation functions</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class StringUtils {

    public StringUtils()
    {
    }

	/**
	* Method to print a stack trace when an exception occurs
	*/
    public  static String stack2string(Exception e)
    {
        try
        {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            return "------\r\n" + sw.toString() + "------\r\n";
        }
        catch(Exception e2)
        {
            return "bad stack2string";
        }
    }

    /**
    * Format zip code - Puts zip code into a format of 5 digits, or
    * 5 digits, dash, 4 digits.  The zip code is formatted in this manner before
    * writing to the database to meet the needs of the Lotus Notes application
    * that reads the data.
    * The zip code should be edited before calling this function.
    */
/*   public static String formatZipCode(String s) {
     String sReturn = s;
     int sLength = s.length();
     if (sLength == 9) {
       sReturn = s.substring(0, 5) + "-" + s.substring(5, 9);
     }
     else {
       if (sLength == 10) {
         if (s.substring(5, 6).equals(" ")) {
           sReturn = s.substring(0, 5) + "-" + s.substring(6, 10);
         }
       }
     }
     return sReturn;
   }
   */
    
    public static String formatZipCode(String s)
    {
    	String sReturn = s;
    	if ((sReturn != null) && (sReturn.length() == 10))
    		sReturn = sReturn.replace("-", "");
    	
    	return sReturn;
    }

   /**
    * Fixes apostrophe's - Used for SQL inserts
    */
    public static String fixApostrophe(String s)
    {
        String tempS = "";
        int sLength = s.length();
        for (int i = 0; i < sLength; i++)
        {
            if (s.substring(i,i+1).equalsIgnoreCase("'"))
            {
                tempS = tempS + "''";
            }
            else
            {
                tempS = tempS + s.substring(i,i+1);
            }
        }
        return tempS;
    }

	/**
	 * Carriage returns entered in any of the comment fields have to be converted to some special character
	 * since by default they represent the end of a record.
	 * @param s
	 * @return
	 */
	public static String fixCarriageReturn(String s)
    {
		s = getSafeString(s);
        String tempS = "";
        int sLength = s.length();
        for (int i = 0; i < sLength; i++)
        {
			//Carriage returns entered in any of the comment fields have to be converted to some special character
			// since by default they represent the end of a record.
			if (s.substring(i,i+1).equalsIgnoreCase("\n") || s.substring(i,i+1).equalsIgnoreCase("\r"))
			{
				tempS = tempS + "|";
			}
			else
			{
				tempS = tempS + s.substring(i,i+1);
			}
        }
        return tempS;
    }


	/**
	 * Return "" if a null string is entered. The value "null was too included
	 * to account for data written to the PMFData file. Since there was too many usages
	 * which did not account for "null" value this too was added in the check for null.
	 * @param str
	 * @return
	 */
	public static String getSafeString(String str)
    {
        if (str==null || str.length()==0 )
		{
			 return "";
		}
        return str;
    }
	/**
	 * Fills the Sting Array with BLANKs
	 * @param arryObject String array
	 */
	public static void fillArrayWithBlanks(String[] arryObject) {
		for(int i=0;i<arryObject.length;i++) {
			arryObject[i] = Constants.BLANK;
		}
	}
	
	public static boolean isEmpty(String str) {
        return (str == null || str.length() == 0);
    }
	
	public static boolean isNotEmpty(String str) {
        return (str != null && str.length() > 0);
    }

}