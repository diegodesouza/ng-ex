package com.anthem.mwpmf;

import java.io.Serializable;
import com.anthem.util.Constants;

/**
 * Value Object which stores the PRACTICE_INFO details
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */ 
public class PracticeInfo implements Serializable, Constants
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pracName;
	private String pracOfficeAddress;
	private String pracOfficeCity;
	private String pracOfficeState;
	private String pracOfficeZip;
	private String pracOfficeCounty;
	private String pracOfficePhone;
	private String pracOfficeFax;
	private String pracOfficeEmail;
	private String pracBillAddress;
	private String pracBillCity;
	private String pracBillState;
	private String pracBillZip;
	private String pracBillCounty;
	private String pracBillPhone;
	private String pracBillFax;
	private String pracNPINo;
	private String billMedicareGroup;
	private String billMedicareIndividual;
    private String medicaidGrpId;
	private String medicaidIndvId;
	
	private String pubicTrans;
    private String handicapAccess;
    private String eveningHrs;
    //2013 changes SSCR 13503
    private String provDir;
    /*private String remitSamePrim2;
    private String provDir2;
    private String remitSamePrim3;
    private String remitSamePrim4;
    private String remitSamePrim5;
    private String remitSamePrim6;

    private String provDir3;
    private String provDir4;
    private String provDir5
    private String provDir6;*/


    private String weekendHrs;
    private String daysOpenMon;
    private String daysOpenTue;
    private String daysOpenWed;
    private String daysOpenThu;
    private String daysOpenFri;
    private String daysOpenSat;
    private String daysOpenSun;
    private String timeZone;
    
	private String daysOpenTimeMon;
	private String daysCloseTimeMon;
	private String daysOpenTimeTue;
	private String daysCloseTimeTue;
	private String daysOpenTimeWed;
	private String daysCloseTimeWed;
	private String daysOpenTimeThu;
	private String daysCloseTimeThu;
	private String daysOpenTimeFri;
	private String daysCloseTimeFri;
	private String daysOpenTimeSat;
	private String daysCloseTimeSat;
	private String daysOpenTimeSun;
	private String daysCloseTimeSun;
	
	/* PMF Section Changes -- AD21239 --*/
	private String kyMedicaidPart;
	private String kyMedicaidId;
	
	private String matWaiveredPrescriber = "";
	private String certOpioidTreat = "";
	private String matOpioid = "";
	private String counselOpioid = "";
	private String sudProv = "";
	private String resTreatCtr = "";
	private String provideTelehealth = "";
		
    public String getKyMedicaidPart() {
		return kyMedicaidPart;
	}

	public void setKyMedicaidPart(String kyMedicaidPart) {
		this.kyMedicaidPart = kyMedicaidPart;
	}

	public String getKyMedicaidId() {
		return kyMedicaidId;
	}

	public void setKyMedicaidId(String kyMedicaidId) {
		this.kyMedicaidId = kyMedicaidId;
	}

	public String getDaysOpenTimeMon() {
		return daysOpenTimeMon;
	}

	public void setDaysOpenTimeMon(String daysOpenTimeMon) {
		this.daysOpenTimeMon = daysOpenTimeMon;
	}

	public String getDaysCloseTimeMon() {
		return daysCloseTimeMon;
	}

	public void setDaysCloseTimeMon(String daysCloseTimeMon) {
		this.daysCloseTimeMon = daysCloseTimeMon;
	}

	public String getDaysOpenTimeTue() {
		return daysOpenTimeTue;
	}

	public void setDaysOpenTimeTue(String daysOpenTimeTue) {
		this.daysOpenTimeTue = daysOpenTimeTue;
	}

	public String getDaysCloseTimeTue() {
		return daysCloseTimeTue;
	}

	public void setDaysCloseTimeTue(String daysCloseTimeTue) {
		this.daysCloseTimeTue = daysCloseTimeTue;
	}

	public String getDaysOpenTimeWed() {
		return daysOpenTimeWed;
	}

	public void setDaysOpenTimeWed(String daysOpenTimeWed) {
		this.daysOpenTimeWed = daysOpenTimeWed;
	}

	public String getDaysCloseTimeWed() {
		return daysCloseTimeWed;
	}

	public void setDaysCloseTimeWed(String daysCloseTimeWed) {
		this.daysCloseTimeWed = daysCloseTimeWed;
	}

	public String getDaysOpenTimeThu() {
		return daysOpenTimeThu;
	}

	public void setDaysOpenTimeThu(String daysOpenTimeThu) {
		this.daysOpenTimeThu = daysOpenTimeThu;
	}

	public String getDaysCloseTimeThu() {
		return daysCloseTimeThu;
	}

	public void setDaysCloseTimeThu(String daysCloseTimeThu) {
		this.daysCloseTimeThu = daysCloseTimeThu;
	}

	public String getDaysOpenTimeFri() {
		return daysOpenTimeFri;
	}

	public void setDaysOpenTimeFri(String daysOpenTimeFri) {
		this.daysOpenTimeFri = daysOpenTimeFri;
	}

	public String getDaysCloseTimeFri() {
		return daysCloseTimeFri;
	}

	public void setDaysCloseTimeFri(String daysCloseTimeFri) {
		this.daysCloseTimeFri = daysCloseTimeFri;
	}

	public String getDaysOpenTimeSat() {
		return daysOpenTimeSat;
	}

	public void setDaysOpenTimeSat(String daysOpenTimeSat) {
		this.daysOpenTimeSat = daysOpenTimeSat;
	}

	public String getDaysCloseTimeSat() {
		return daysCloseTimeSat;
	}

	public void setDaysCloseTimeSat(String daysCloseTimeSat) {
		this.daysCloseTimeSat = daysCloseTimeSat;
	}

	public String getDaysOpenTimeSun() {
		return daysOpenTimeSun;
	}

	public void setDaysOpenTimeSun(String daysOpenTimeSun) {
		this.daysOpenTimeSun = daysOpenTimeSun;
	}

	public String getDaysCloseTimeSun() {
		return daysCloseTimeSun;
	}

	public void setDaysCloseTimeSun(String daysCloseTimeSun) {
		this.daysCloseTimeSun = daysCloseTimeSun;
	}
	//2013  SSCR 13503changes
	public String getProvDir() {
		return provDir;
	}

	public void setProvDir(String provDir) {
		this. provDir = provDir;
	}


	//  Changes for the state mandate on 02/10/10 start
    private String languagesSpoken="";
    private String offerECI="";
    private String offerEPSDT="";
    private String provideADB="";
    private String provideCSHCN="";
    //  Changes for the state mandate on 02/10/10 end
    
    private String pracBillContactName;
    private String pracBillContactEmail;

	/**
	* Initialize fields. Note: The names of the form inadd elements match the
	* names of the bean properties.
	*/
    public PracticeInfo()
    {
        pracName =BLANK;
        pracOfficeAddress =BLANK;
        pracOfficeCity =BLANK;
        pracOfficeState =BLANK;
        pracOfficeZip =BLANK;
        pracOfficeCounty =BLANK;
        pracOfficePhone =BLANK;
        pracOfficeFax =BLANK;
        pracOfficeEmail =BLANK;
        pracBillAddress =BLANK;
        pracBillCity =BLANK;
        pracBillState =BLANK;
        pracBillZip =BLANK;
        pracBillCounty =BLANK;
        pracBillPhone =BLANK;
        pracBillContactName =BLANK;
        pracBillContactEmail =BLANK;
        pracBillFax =BLANK;
        billMedicareGroup =BLANK;
        billMedicareIndividual =BLANK;
        /* PMF Section Changes -- AD21239 --*/
        kyMedicaidPart =BLANK;
    	kyMedicaidId =BLANK;
    }

    /**
     * The getter methods for each property are defined below.
     */
    public String getPracName()
    {
        return pracName;
    }

    public String getPracOfficeAddress()
    {
        return pracOfficeAddress;
    }

    public String getPracOfficeCity()
    {
        return pracOfficeCity;
    }

    public String getPracOfficeState()
    {
        return pracOfficeState;
    }

    public String getPracOfficeZip()
    {
    return pracOfficeZip;
    }

    public String getPracOfficeCounty()
    {
        return pracOfficeCounty;
    }

    public String getPracOfficePhone()
    {
        return pracOfficePhone;
    }

    public String getPracOfficeFax()
    {
        return pracOfficeFax;
    }

    public String getPracOfficeEmail()
    {
        return pracOfficeEmail;
    }

    public String getPracBillAddress()
    {
        return pracBillAddress;
    }

    public String getPracBillCity()
    {
        return pracBillCity;
    }

    public String getPracBillState()
    {
        return pracBillState;
    }

    public String getPracBillZip()
    {
        return pracBillZip;
    }

    public String getPracBillCounty()
    {
        return pracBillCounty;
    }

    public String getPracBillPhone()
    {
        return pracBillPhone;
    }

    public String getPracBillFax()
    {
        return pracBillFax;
    }
    
    public String getPracBillContactName() {
		return pracBillContactName;
	}

	public void setPracBillContactName(String pracBillContactName) {
		this.pracBillContactName = pracBillContactName;
	}

	public String getPracBillContactEmail() {
		return pracBillContactEmail;
	}

	public void setPracBillContactEmail(String pracBillContactEmail) {
		this.pracBillContactEmail = pracBillContactEmail;
	}

	public String getPracNPINo()
    {
        return pracNPINo;
    }

    public String getBillMedicareGroup()
    {
        return billMedicareGroup;
    }

    public String getBillMedicareIndividual()
    {
        return billMedicareIndividual;
    }


	public void setPracName(String sPracName)
	{
	    pracName = sPracName;
	}

    public void setPracOfficeAddress(String sPracOfficeAddress)
	{
		pracOfficeAddress = sPracOfficeAddress;
	}

    public void setPracOfficeCity(String sPracOfficeCity)
	{
		pracOfficeCity = sPracOfficeCity;
	}

    public void setPracOfficeState(String sPracOfficeState)
    {
		pracOfficeState = sPracOfficeState;
	}

	public void setPracOfficeZip(String sPracOfficeZip)
	{
		pracOfficeZip = sPracOfficeZip;
	}

	public void setPracOfficeCounty(String sPracOfficeCounty)
	{
		pracOfficeCounty = sPracOfficeCounty;
	}

	public void setPracOfficePhone(String sPracOfficePhone)
	{
		pracOfficePhone = sPracOfficePhone;
	}

	public void setPracOfficeFax(String sPracOfficeFax)
	{
		pracOfficeFax = sPracOfficeFax;
	}

	public void setPracOfficeEmail(String sPracOfficeEmail)
	{
		pracOfficeEmail = sPracOfficeEmail;
	}

	public void setPracBillAddress(String sPracBillAddress)
	{
		pracBillAddress = sPracBillAddress;
	}

	public void setPracBillCity(String sPracBillCity)
	{
		pracBillCity = sPracBillCity;
	}

	public void setPracBillState(String sPracBillState)
	{
		pracBillState = sPracBillState;
	}

	public void setPracBillZip(String sPracBillZip)
	{
		pracBillZip = sPracBillZip;
	}

	public void setPracBillCounty(String sPracBillCounty)
	{
		pracBillCounty = sPracBillCounty;
	}

	public void setPracBillPhone(String sPracBillPhone)
	{
		pracBillPhone = sPracBillPhone;
	}

	public void setPracBillFax(String sPracBillFax)
	{
		pracBillFax = sPracBillFax;
	}
	
	public void setPracNPINo(String sPracNPINo)
	{
		pracNPINo = sPracNPINo;
	}

	public void setBillMedicareGroup(String sBillMedicareGroup)
	{
		billMedicareGroup = sBillMedicareGroup;
	}

	public void setBillMedicareIndividual(String sBillMedicareIndividual)
	{
		billMedicareIndividual = sBillMedicareIndividual;
	}

        public String getMedicaidGroup()
        {
          return medicaidGrpId;
        }

        public void setMedicaidGroup(String medicaidGroup)
        {
          medicaidGrpId = medicaidGroup;
        }

        public String getMedicaidIndividual()
        {
          return medicaidIndvId;
        }

        public void setMedicaidIndividual(String medicaidIndividual)
        {
                medicaidIndvId = medicaidIndividual;
        }

		public String getDaysOpenMon() {
			return daysOpenMon;
		}

		public void setDaysOpenMon(String daysOpenMon) {
			this.daysOpenMon = daysOpenMon;
		}

		public String getDaysOpenThu() {
			return daysOpenThu;
		}

		public void setDaysOpenThu(String daysOpenThu) {
			this.daysOpenThu = daysOpenThu;
		}

		public String getDaysOpenTue() {
			return daysOpenTue;
		}

		public void setDaysOpenTue(String daysOpenTue) {
			this.daysOpenTue = daysOpenTue;
		}

		public String getDaysOpenWed() {
			return daysOpenWed;
		}

		public void setDaysOpenWed(String daysOpenWed) {
			this.daysOpenWed = daysOpenWed;
		}
		
		public String getDaysOpenFri() {
			return daysOpenFri;
		}

		public void setDaysOpenFri(String daysOpenFri) {
			this.daysOpenFri = daysOpenFri;
		}

		
		public String getDaysOpenSat() {
			return daysOpenSat;
		}

		public void setDaysOpenSat(String daysOpenSat) {
			this.daysOpenSat = daysOpenSat;
		}

		public String getDaysOpenSun() {
			return daysOpenSun;
		}

		public void setDaysOpenSun(String daysOpenSun) {
			this.daysOpenSun = daysOpenSun;
		}
		
		public String getTimeZone() {
			return timeZone;
		}

		public void setTimeZone(String timeZone) {
			this.timeZone = timeZone;
		}

		public String getEveningHrs() {
			return eveningHrs;
		}

		public void setEveningHrs(String eveningHrs) {
			this.eveningHrs = eveningHrs;
		}

		public String getHandicapAccess() {
			return handicapAccess;
		}

		public void setHandicapAccess(String handicapAccess) {
			this.handicapAccess = handicapAccess;
		}

		public String getPubicTrans() {
			return pubicTrans;
		}

		public void setPubicTrans(String pubicTrans) {
			this.pubicTrans = pubicTrans;
		}

		public String getWeekendHrs() {
			return weekendHrs;
		}

		public void setWeekendHrs(String weekendHrs) {
			this.weekendHrs = weekendHrs;
		}
	    //  Changes for the state mandate on 02/10/10 start
		public String getLanguagesSpoken() {
			return languagesSpoken;
		}

		public void setLanguagesSpoken(String languagesSpoken) {
			this.languagesSpoken = languagesSpoken;
		}

		public String getOfferECI() {
			return offerECI;
		}

		public void setOfferECI(String offerECI) {
			this.offerECI = offerECI;
		}

		public String getOfferEPSDT() {
			return offerEPSDT;
		}

		public void setOfferEPSDT(String offerEPSDT) {
			this.offerEPSDT = offerEPSDT;
		}

		public String getProvideADB() {
			return provideADB;
		}

		public void setProvideADB(String provideADB) {
			this.provideADB = provideADB;
		}

		public String getProvideCSHCN() {
			return provideCSHCN;
		}

		public void setProvideCSHCN(String provideCSHCN) {
			this.provideCSHCN = provideCSHCN;
		}
	    //  Changes for the state mandate on 02/10/10 end

	public String getMatWaiveredPrescriber() {
		return matWaiveredPrescriber;
	}
	
	public void setMatWaiveredPrescriber(String matWaiveredPrescriber) {
		this.matWaiveredPrescriber = matWaiveredPrescriber;
	}
		
	public String getCertOpioidTreat() {
		return certOpioidTreat;
	}
	
	public void setCertOpioidTreat(String certOpioidTreat) {
		this.certOpioidTreat = certOpioidTreat;
	}
	
	public String getMatOpioid() {
		return matOpioid;
	}
	
	public void setMatOpioid(String matOpioid) {
		this.matOpioid = matOpioid;
	}
	
	public String getCounselOpioid() {
		return counselOpioid;
	}
	
	public void setCounselOpioid(String counselOpioid) {
		this.counselOpioid = counselOpioid;
	}
	
	public String getSudProv() {
		return sudProv;
	}
	
	public void setSudProv(String sudProv) {
		this.sudProv = sudProv;
	}
	
	public String getResTreatCtr() {
		return resTreatCtr;
	}
	
	public void setResTreatCtr(String resTreatCtr) {
		this.resTreatCtr = resTreatCtr;
	}
	
	public String getProvideTelehealth() {
		return provideTelehealth;
	}
	
	public void setProvideTelehealth(String provideTelehealth) {
		this.provideTelehealth = provideTelehealth;
	}
}
