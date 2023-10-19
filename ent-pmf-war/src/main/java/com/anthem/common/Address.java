package com.anthem.common;

import com.anthem.util.Constants;

/**
 * <p>Title: Address</p>
 * <p>Description: Stores the Provider Address information </p>
 * Presently this is used only in the validations since
 * there is a widespread usage of individual address fields in the code
 * existing. The existing code was not modified yet to use this due to the wide usage
 * throughout the code.
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Anthem, Inc.</p>
 * @author Isac
 * @version 1.0
 */

/* The addresses for the Provider should be changed to use this
*/
public class Address implements Constants
{
	private String contactName;
	private String address1;
    private String city;
    private String state;
    private String zipCode;
    private String county;
    private String phoneNumber;
    private String faxNumber;
    private String emai1;
    public Address(String contactName, String address1,String city, String state, String zipCode,
		                  String county, String phoneNumber, String faxNumber, String email  )
    {   this.contactName = contactName;
		this.address1 = address1;
		this.city     = city;
		this.state    = state;
		this.zipCode  = zipCode;
		this.county   = county;
		this.phoneNumber = phoneNumber;
		this.faxNumber = faxNumber;
		this.emai1     = email;
    }
    
    /* The below Address object is to be used by Covering Physicians and for Section H*/
    
    public Address(String address1,String city, String state, String zipCode){
    	this.contactName = BLANK;
    	this.address1 = address1;
    	this.city = city;
    	this.state = state;
    	this.zipCode = zipCode;
    	this.county   = BLANK;
		this.phoneNumber = BLANK;
		this.faxNumber = BLANK;
		this.emai1     = BLANK;
    }
 

	public String getAddress1()
	{
		return address1;
	}
    public String getCity()
	{
		return city;
	}
    public String getState()
	{
		return state;
	}
    public String getZipCode()
	{
		return zipCode;
	}
    public String getCounty()
	{
		return county;
	}
    public String getPhoneNumber()
	{
		return phoneNumber;
	}
    public String getFaxNumber()
	{
		return faxNumber;
	}
    public String getEmai1()
	{
		return emai1;
	}
    /**
	 * @return the contactName
	 */
	public String getContactName() {
		return contactName;
	}
}