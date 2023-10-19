package com.anthem.mwpmf;

import java.io.Serializable;

import com.anthem.common.Address;
import com.anthem.util.Constants;

/**
 * <p>Title: CoveringPhysicians</p>
 * <p>Description: value object to store the Covering Physicians</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Anthem </p>
 * @author unascribed
 * @version 1.0
 */

public class CoveringPhysicians implements Serializable, Constants
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String grpEntityName;
	private String specialty;
	private String taxID;
	private String effectiveDate;
	private Address address;

	public CoveringPhysicians()
	{
		grpEntityName = BLANK;
		specialty = BLANK;
		taxID = BLANK;
		effectiveDate = BLANK;
		address = new Address("BLANK", "BLANK", "BLANK", "BLANK", "BLANK", "BLANK", "BLANK", "BLANK","BLANK");
	}

	/**
	 * The getter methods for each property are defined below.
	 */
	public String getGrpEntityName()
	{
	    return grpEntityName;
	}

	public String getSpecialty()
	{
	    return specialty;
	}

	public String getTaxID()
	{
	    return taxID;
	}

	public String getEffectiveDate()
	{
	    return effectiveDate;
	}

	public void setGrpEntityName(String sGrpEntityName)
	{
		grpEntityName = sGrpEntityName;
	}

	public void setSpecialty(String sSpecialty)
	{
		specialty = sSpecialty;
	}

	public void setTaxID(String sTaxID)
	{
		taxID = sTaxID;
	}

	public void setEffectiveDate(String sEffectiveDate)
	{
		effectiveDate = sEffectiveDate;
	}

	public void setAddress(Address sAddress) {
		address = sAddress;
	}

	public Address getAddress() {
		return address;
	}
}
