<!doctype html public "-//w3c//dtd html 4.0 transitional//en">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" %>
<%@ page session="true" import="com.anthem.mwpmf.Provider" %>
<%@ page import="com.anthem.mwpmf.AreasOfExpertise" %>
<%@ page import="com.anthem.mwpmf.PMFUtils" %>
<%@ page import="com.anthem.util.StringUtils" %>
<%@ page import="com.anthem.util.Constants" %>
<%@page import="java.util.*" %>
<%@ page import="com.anthem.mwpmf.PMFUtils" %>
<%@ page import="org.owasp.esapi.ESAPI" %>

<!-- force recompile in test -->

<%
	Provider providerData = (Provider)request.getAttribute("Provider");	
    String action = "1";
    String taxID = "";
    String anthemPIN = "";
    String moId = "";
    String wiId = "";
    String grpmedicaidId = "";
    String grpNpiNumber = "";
    String provNpiNumber = "";
    String taxonomyNum = "";
    String pracName1 = "";
    String soloGroup = "";
    String solo = "S";
    String group = "G";
    String numberInGroup = "";
    String rapidUpdate = "";
    String effectiveDate = "";
    String addProvider = "";
    String delProvider = "";
    String addLocation = "";
    String addProvToLocation = "";
    String chgSpecialty = "";
    String chgProvName = "";
    String delLocation = "";
    String addNPI = "";
    String chgNPI = "";
    String delProvFromLocation = "";
    String chgPracName = "";
    String chgPracAddress = "";
    String chgPracPhone = "";
    String chgTaxID = "";
    String chgBillName = "";
    String chgBillAddress = "";
    String chgBillPhone = "";
    String delReason = "";
    String comments = "";
    String confirmation = "";
    //changes PMF SSCR 13503
    String confProvAgreement = "";
    String confW2 = "";
    String confProvW2 = ""; 
    //PMF SSCR 13503
    String oldTaxID = "";
    String provFnm = "";
    String provMI = "";
    String provLnm = "";
    String title = "";
    String primSpecialtyPhy = "";
    String specialtyCarePhy = "";
    String other = "";
    // change for section c 13/6/2012
    String specialty = "";
    String specialityInfo = " ";
    String clearSelection = " ";
    String medicarePartTrad = " ";
    String medicareApplDt = "";
    String medicareOptOut = " ";
    String medicareOptOutDt = "";
    String npSupSpec= " ";
    String npSupPMP= " ";
    String retailHealthClinic= " ";
    String walkInDrOffice= " ";
    String urgentCare= " ";
    String coveringPMP= " ";
    String certMidwife= " ";
    String prenatelCareCoord= " ";
    String tribalHealthCtr= " ";
    String clinic= " ";
    String medicationAssistedTreatment = " " ;
    String residentialTreatmentCenter = " " ;
    String substanceUseDisorderAdults = " " ;
    String substanceUseDisorderChild = " ";
    String telehealthProv = " ";
    
    String fwdHealthCertNPI1= "";
    String fwdHealthCertNPIBilled1= " ";
    String fwdHealthCertEffDt1= "";
    String fwdHealthRecertEffDt1= "";
    String fwdHealthCertNPI2= "";
    String fwdHealthCertNPIBilled2= " ";
    String fwdHealthCertEffDt2= "";
    String fwdHealthRecertEffDt2= "";
    String fwdHealthCertNPI3= "";
    String fwdHealthCertNPIBilled3= " ";
    String fwdHealthCertEffDt3= "";
    String fwdHealthRecertEffDt3= "";
    
    // change for section c 13/6/2012
    String provNumber = "";
    String upinNumber = "";
    String profLicenseNumber = "";
	String ihcpProviderNumber ="";
    String caqhIDNumber = "";
    String provDOB = "";
    String provGender = "";
	String genderMale = "M";
	String genderFemale = "F";
	String genderMaleOrFemale ="MF";
    String pracOfficeAddress1 = "";
    String pracOfficeCity1 = "";
    String pracOfficeState1 = "";
    String pracOfficeZip1 = "";
    String pracOfficeCounty1 = "";
    String pracOfficePhone1 = "";
    String pracOfficeFax1 = "";
    String pracOfficeEmail1 = "";
    String billAddressSame1 = "";
    String pracBillAddress1 = "";
    String pracBillContactName1 = "";
    String pracBillContactEmail1 ="";
    String pracBillCity1 = "";
    String pracBillState1 = "";
    String pracBillZip1 = "";
    String pracBillCounty1 = "";
    String pracBillPhone1 = "";
    String pracBillFax1 = "";
    String pracNPINo1 = "";
    String billMedicareGroup1 = "";
    String billMedicareIndividual1 = "";
    String medicaidGroup1 = "";
    String medicaidIndividual1 = "";
    String pracName2 = "";
    String pracOfficeAddress2 = "";
    String pracOfficeCity2 = "";
    String pracOfficeState2 = "";
    String pracOfficeZip2 = "";
    String pracOfficeCounty2 = "";
    String pracOfficePhone2 = "";
    String pracOfficeFax2 = "";
    String pracOfficeEmail2 = "";
    String billAddressSame2 = "";
    String pracBillContactName2 = "";
    String pracBillContactEmail2 ="";
    String pracBillAddress2 = "";
    String pracBillCity2 = "";
    String pracBillState2 = "";
    String pracBillZip2 = "";
    String pracBillCounty2 = "";
    String pracBillPhone2 = "";
    String pracBillFax2 = "";
    String pracNPINo2 = "";
    String billMedicareGroup2 = "";
    String billMedicareIndividual2 = "";
    String medicaidGroup2 = "";
    String medicaidIndividual2 = "";
    String pracName3 = "";
    String pracOfficeAddress3 = "";
    String pracOfficeCity3 = "";
    String pracOfficeState3 = "";
    String pracOfficeZip3 = "";
    String pracOfficeCounty3 = "";
    String pracOfficePhone3 = "";
    String pracOfficeFax3 = "";
    String pracOfficeEmail3 = "";
    String billAddressSame3 = "";
    String pracBillContactName3 = "";
    String pracBillContactEmail3 ="";
    String pracBillAddress3 = "";
    String pracBillCity3 = "";
    String pracBillState3 = "";
    String pracBillZip3 = "";
    String pracBillCounty3 = "";
    String pracBillPhone3 = "";
    String pracBillFax3 = "";
    String pracNPINo3 = "";
    String billMedicareGroup3 = "";
    String billMedicareIndividual3 = "";
    String medicaidGroup3 = "";
    String medicaidIndividual3 = "";
    String pracName4 = "";
    String pracOfficeAddress4 = "";
    String pracOfficeCity4 = "";
    String pracOfficeState4 = "";
    String pracOfficeZip4 = "";
    String pracOfficeCounty4 = "";
    String pracOfficePhone4 = "";
    String pracOfficeFax4 = "";
    String pracOfficeEmail4 = "";
    String billAddressSame4 = "";
    String pracBillContactName4 = "";
    String pracBillContactEmail4 ="";
    String pracBillAddress4 = "";
    String pracBillCity4 = "";
    String pracBillState4 = "";
    String pracBillZip4 = "";
    String pracBillCounty4 = "";
    String pracBillPhone4 = "";
    String pracBillFax4 = "";
    String pracNPINo4 = "";
    String billMedicareGroup4 = "";
    String billMedicareIndividual4 = "";
    String medicaidGroup4 = "";
    String medicaidIndividual4 = "";
    String pracName5 = "";
    String pracOfficeAddress5 = "";
    String pracOfficeCity5 = "";
    String pracOfficeState5 = "";
    String pracOfficeZip5 = "";
    String pracOfficeCounty5 = "";
    String pracOfficePhone5 = "";
    String pracOfficeFax5 = "";
    String pracOfficeEmail5 = "";
    String billAddressSame5 = "";
    String pracBillContactName5 = "";
    String pracBillContactEmail5 ="";
    String pracBillAddress5 = "";
    String pracBillCity5 = "";
    String pracBillState5 = "";
    String pracBillZip5 = "";
    String pracBillCounty5 = "";
    String pracBillPhone5 = "";
    String pracBillFax5 = "";
    String pracNPINo5 = "";
    String billMedicareGroup5 = "";
    String billMedicareIndividual5 = "";
    String medicaidGroup5 = "";
    String medicaidIndividual5 = "";
    String pracName6 = "";
    String pracOfficeAddress6 = "";
    String pracOfficeCity6 = "";
    String pracOfficeState6 = "";
    String pracOfficeZip6 = "";
    String pracOfficeCounty6 = "";
    String pracOfficePhone6 = "";
    String pracOfficeFax6 = "";
    String pracOfficeEmail6 = "";
    String billAddressSame6 = "";
    String pracBillContactName6 = "";
    String pracBillContactEmail6 ="";
    String pracBillAddress6 = "";
    String pracBillCity6 = "";
    String pracBillState6 = "";
    String pracBillZip6 = "";
    String pracBillCounty6 = "";
    String pracBillPhone6 = "";
    String pracBillFax6 = "";
    String pracNPINo6 = "";
    String billMedicareGroup6 = "";
    String billMedicareIndividual6 = "";
    String medicaidGroup6 = "";
    String medicaidIndividual6 = "";
    String grpEntityName1 = "";
    String specialty1 = "";
    String taxID1 = "";
    String effectiveDate1 = "";
    String grpEntityName2 = "";
    String specialty2 = "";
    String taxID2 = "";
    String effectiveDate2 = "";
    String grpEntityName3 = "";
    String specialty3 = "";
    String taxID3 = "";
    String effectiveDate3 = "";
    String grpEntityName4 = "";
    String specialty4 = "";
    String taxID4 = "";
    String effectiveDate4 = "";
    String grpEntityName5 = "";
    String specialty5 = "";
    String taxID5 = "";
    String effectiveDate5 = "";
    
    //08/15/2008 Changes
    String caqhExplanation = "";
    String chgOfficeHours = "";
    String boardCertified = "";
    String certExamDT = "";
    String newPatients = "";
    String ageLimitMin = "";
    String ageLimitMax = "";    
    
    String Yes = "Y"; 											
    String No = "N";
    String NA = "A";
    String pubicTrans1 = "";
    String handicapAccess1 = "";
    String eveningHrs1 = "";
    
    String pubicTrans2 = "";
    String handicapAccess2 = "";
    String eveningHrs2 = "";
    String pubicTrans3 = "";
    String handicapAccess3 = "";
    String eveningHrs3 = "";
    String pubicTrans4 = "";
    String handicapAccess4 = "";
    String eveningHrs4 = "";
    String pubicTrans5 = "";
    String handicapAccess5 = "";
    String eveningHrs5 = "";

    String pubicTrans6 = "";
    String handicapAccess6 = "";
    String eveningHrs6 = "";
     // 2013 SSCR 13503 changes
    String provDir1 = "";
    String remitSamePrim2 = "";
    String provDir2 = "";
    String remitSamePrim3 = "";
	String remitSamePrim4 = "";
	String remitSamePrim5 = "";
	String remitSamePrim6 = "";
	String provDir3 = "";
	String provDir4 = "";
	String provDir5 = "";
	String provDir6 = "";
	
     //end SSCR 13503 2013 changes
    
    //Changes for the state mandate on 02/10/10 start
    String medicaidIndicator = "";
	String medicaidPCP = "";
	String medicaidMaxPanel = "";
	String hipMaxPanel = "";
	String medicaidSpecialist = "";
	String hipIndicator = "";
	String hipPCP = "";
	String hipSpecialist = "";
	String ssMedical = "";
	String ssDental = "";
	String ssVision = "";
	String ssOtherServType = "";
	String indivPractice = "";
	String groupPractice = "";
	String schoolBasedClinic = "";
	String tribalHealthCenter = "";
	String ruralHealthClinic = "";
	String fedQualHealthClinic = "";
	String communityHealthCenter = "";
	String deptOfHealth = "";
	String otherPractice = "";
	String radHospBased = "";
	String radFreeStandingCenter = "";
	String languagesSpoken1="";
    String offerECI1="";
    String offerEPSDT1="";
    String provideADB1="";
    String provideCSHCN1="";
    String languagesSpoken2="";
    String offerECI2="";
    String offerEPSDT2="";
    String provideADB2="";
    String provideCSHCN2="";
    String languagesSpoken3="";
    String offerECI3="";
    String offerEPSDT3="";
    String provideADB3="";
    String provideCSHCN3="";
    String languagesSpoken4="";
    String offerECI4="";
    String offerEPSDT4="";
    String provideADB4="";
    String provideCSHCN4="";
    String languagesSpoken5="";
    String offerECI5="";
    String offerEPSDT5="";
    String provideADB5="";
    String provideCSHCN5="";
    String languagesSpoken6="";
    String offerECI6="";
    String offerEPSDT6="";
    String provideADB6="";
    String provideCSHCN6="";
    String mgdCareDisenroll = "";
    String ihcpProvNo = "";
    String pmp = "";
    String pmpSpecialty  = "";
    String hospAdmitPriv ="";
    String relationshipPriv ="";
    String deliveryPriv="";
    String ageRestriction="";
    String pmpScopeOb = "";
    String pmpScopeAll = "";
    String genderScope = "";
    String medPanelStatus ="";
    String hipPanelStatus ="";
    String medNbrLocations = "";
    String hipNbrLocations = "";
    String hold ="Hold";
    String open ="Open";
    String medPldPanelDecrease ="";
    String hipPldPanelDecrease ="";
    String medPldPlacePanelAt ="";
    String hipPldPlacePanelAt ="";
    String medPldGrpMedicaidNo = "";
    String hipPldGrpMedicaidNo = "";
    
    String medPliPanelIncrease = "";
    String hipPliPanelIncrease = "";
    String medPliPlacePanelAt = "";
    String hipPliPlacePanelAt = "";
    String medPliGrpMedicaidNbr = "";
    String hipPliGrpMedicaidNbr = "";
    
    String medPanelHold = "";
    String hipPanelHold = "";
    String medPlrPanelHold = "";
    String hipPlrPanelHold = "";
    
    String medPanelHoldRemove = "";
    String hipPanelHoldRemove = "";
    
    String medPlrPanelHoldRemove = "";
    String hipPlrPanelHoldRemove = "";
    
    String deaNo = "";
    String csrNo = "";
    String enrollAs = "";
    String enrollClinicType ="";
    String locationType = "";
    String stateLicIssueDt = "";
    String stateLicExpDt ="";
    String profLiabCarrier = "";
    String profLiabCovgLimit ="";
    String profLiabPolicy = "";
    String profLiabExtDt = "";
   	String malPracInsRevoke = "";
   	String underGovInvestigation = "";
   	String expellMedPay ="";
    
	String paPractice="";
	String npPractice="";
	String naPractice="";
    String fileUploadStat = "";
    String[] apHospitalName = new String[3];
    String[] apHospitalAddress = new String[3];
    String[] apHospitalCity= new String[3];
    String[] apHospitalState = new String[3];
    String[] apHospitalZip = new String[3];
    String[] dpHospitalName = new String[3];
    String[] dpHospitalAddress = new String[3];
    String[] dpHospitalCity = new String[3];
    String[] dpHospitalState = new String[3];
    String[] dpHospitalZip = new String[3];
    String[] cpHospitalAddress = new String[5];
    String[] cpHospitalCity = new String[5];
    String[] cpHospitalState = new String[5];
    String[] cpHospitalZip = new String[5];
    
    StringUtils.fillArrayWithBlanks(apHospitalName);
    StringUtils.fillArrayWithBlanks(apHospitalAddress);
    StringUtils.fillArrayWithBlanks(apHospitalCity);
    StringUtils.fillArrayWithBlanks(apHospitalState);
    StringUtils.fillArrayWithBlanks(apHospitalZip);
    StringUtils.fillArrayWithBlanks(dpHospitalName);
    StringUtils.fillArrayWithBlanks(dpHospitalAddress);
    StringUtils.fillArrayWithBlanks(dpHospitalCity);
    StringUtils.fillArrayWithBlanks(dpHospitalState);
    StringUtils.fillArrayWithBlanks(dpHospitalZip);
    StringUtils.fillArrayWithBlanks(cpHospitalAddress);
    StringUtils.fillArrayWithBlanks(cpHospitalCity);
    StringUtils.fillArrayWithBlanks(cpHospitalState);
    StringUtils.fillArrayWithBlanks(cpHospitalZip);
    
    //Changes for the state mandate on 02/10/10 end
    
    String[] timeZoneOfficeHrs = new String[6];
	
    String daysOpenMon0 = "";
    String daysOpenTue0 = "";
    String daysOpenWed0 = "";
    String daysOpenThu0 = "";
    String daysOpenFri0 = "";
    String daysOpenSat0 = "";
    String daysOpenSun0 = "";
	
	String daysOpenMon1 = "";
    String daysOpenTue1 = "";
    String daysOpenWed1 = "";
    String daysOpenThu1 = "";
    String daysOpenFri1 = "";
    String daysOpenSat1 = "";
    String daysOpenSun1 = "";
	
	String daysOpenMon3 = "";
    String daysOpenTue3 = "";
    String daysOpenWed3 = "";
    String daysOpenThu3 = "";
    String daysOpenFri3 = "";
    String daysOpenSat3 = "";
    String daysOpenSun3 = "";
	
	String daysOpenMon2 = "";
    String daysOpenTue2 = "";
    String daysOpenWed2 = "";
    String daysOpenThu2 = "";
    String daysOpenFri2 = "";
    String daysOpenSat2 = "";
    String daysOpenSun2 = "";
	
	String daysOpenMon4 = "";
    String daysOpenTue4 = "";
    String daysOpenWed4 = "";
    String daysOpenThu4 = "";
    String daysOpenFri4 = "";
    String daysOpenSat4 = "";
    String daysOpenSun4 = "";
	
	String daysOpenMon5 = "";
    String daysOpenTue5 = "";
    String daysOpenWed5 = "";
    String daysOpenThu5 = "";
    String daysOpenFri5 = "";
    String daysOpenSat5 = "";
    String daysOpenSun5 = "";
    
    String[] daysOpenTimeMon = new String[6];
    String[] daysCloseTimeMon = new String[6];
    String[] daysOpenTimeTue = new String[6];
    String[] daysCloseTimeTue = new String[6];
    String[] daysOpenTimeWed = new String[6];
    String[] daysCloseTimeWed = new String[6];
    String[] daysOpenTimeThu = new String[6];
    String[] daysCloseTimeThu = new String[6];
    String[] daysOpenTimeFri = new String[6];
    String[] daysCloseTimeFri = new String[6];
    String[] daysOpenTimeSat = new String[6];
    String[] daysCloseTimeSat = new String[6];
    String[] daysOpenTimeSun = new String[6];
    String[] daysCloseTimeSun = new String[6];
    //for speciality display 14/06/2012
    String[] speciality = new String[166];
    //for(int cntr = 0; cntr <= 166;cntr++){
    	StringUtils.fillArrayWithBlanks(speciality);
    //}
    //for speciality display 14/06/2012
    StringUtils.fillArrayWithBlanks(timeZoneOfficeHrs);
        
    StringUtils.fillArrayWithBlanks(daysOpenTimeMon);
    StringUtils.fillArrayWithBlanks(daysCloseTimeMon);
    StringUtils.fillArrayWithBlanks(daysOpenTimeTue);
    StringUtils.fillArrayWithBlanks(daysCloseTimeTue);
    StringUtils.fillArrayWithBlanks(daysOpenTimeWed);
    StringUtils.fillArrayWithBlanks(daysCloseTimeWed);
    StringUtils.fillArrayWithBlanks(daysOpenTimeThu);
    StringUtils.fillArrayWithBlanks(daysCloseTimeThu);
    StringUtils.fillArrayWithBlanks(daysOpenTimeFri);
    StringUtils.fillArrayWithBlanks(daysCloseTimeFri);
    StringUtils.fillArrayWithBlanks(daysOpenTimeSat);
    StringUtils.fillArrayWithBlanks(daysCloseTimeSat);
    StringUtils.fillArrayWithBlanks(daysOpenTimeSun);
    StringUtils.fillArrayWithBlanks(daysCloseTimeSun);
    
    //section I
    String inPatient = "inpatient";
    String outPatient = "outpatient";
    String both = "both";
    // WLPRD00971518 | Fix for Clear Selection - removed unwanted variable | UST Global | 2014 October    
    
    
    String nonPsychEval = "";
    //Section I End
	String hipShow = "Y";
	
	/* PMF Section C, E, F, G, L Changes -- AD21239 -- Start*/
	//Sec C
    String paSupSpec="";
    String paSupPMP="";
    //Sec E
    String kyMedicaidPart1="";
    String kyMedicaidId1="";
    //Sec F
    String kyMedicaidPart2="";
    String kyMedicaidId2="";
    //Sec G
    String kyMedicaidPart3="";
    String kyMedicaidId3="";
    
    String kyMedicaidPart4="";
    String kyMedicaidId4="";
    
    String kyMedicaidPart5="";
    String kyMedicaidId5="";
    
    String kyMedicaidPart6="";
    String kyMedicaidId6="";
    //Sec L
    String w2Comments="";
	/* PMF Section Changes -- AD21239 --*/
	
	String matWaiveredPrescriber1 = "";
	String certOpioidTreat1 = "";
	String matOpioid1 = "";
	String counselOpioid1 = "";
	String sudProv1 ="";
	String resTreatCtr1 = "";
	String matWaiveredPrescriber2 = "";
	String certOpioidTreat2 = "";
	String matOpioid2 = "";
	String counselOpioid2 = "";
	String sudProv2 ="";
	String resTreatCtr2 = "";
	String matWaiveredPrescriber3 = "";
	String certOpioidTreat3 = "";
	String matOpioid3 = "";
	String counselOpioid3 = "";
	String sudProv3 ="";
	String resTreatCtr3 = "";
	String matWaiveredPrescriber4 = "";
	String certOpioidTreat4 = "";
	String matOpioid4 = "";
	String counselOpioid4 = "";
	String sudProv4 ="";
	String resTreatCtr4 = "";
	String matWaiveredPrescriber5 = "";
	String certOpioidTreat5 = "";
	String matOpioid5 = "";
	String counselOpioid5 = "";
	String sudProv5 ="";
	String resTreatCtr5 = "";
	String matWaiveredPrescriber6 = "";
	String certOpioidTreat6 = "";
	String matOpioid6 = "";
	String counselOpioid6 = "";
	String sudProv6 ="";
	String resTreatCtr6 = "";
	String provideTelehealth1 = "";
	String provideTelehealth2 = "";
	String provideTelehealth3 = "";
	String provideTelehealth4 = "";
	String provideTelehealth5 = "";
	String provideTelehealth6 = "";
	
	if(request.getParameter("hipShow")==null)
	{
		if(request.getAttribute("hipShow") != null)
		{
			hipShow = (String) request.getAttribute("hipShow");
		}
	}
	else
	{
		hipShow = request.getParameter("hipShow");
	}
	
	String indianaStyle="";
	if ("n".equalsIgnoreCase(hipShow))
	{
		indianaStyle="style='display: none;'";
		
	}
    Vector errorMessagesVectorAll  = new Vector();  
	Vector errorMessagesVector  = new Vector();
	Vector errorMessagesVectorA = new Vector();
	Vector errorMessagesVectorB = new Vector();
	Vector errorMessagesVectorC = new Vector();
	Vector errorMessagesVectorD = new Vector();
	Vector errorMessagesVectorE = new Vector();
	Vector errorMessagesVectorF = new Vector();
	Vector errorMessagesVectorG = new Vector();
	Vector errorMessagesVectorH = new Vector();
	Vector errorMessagesVectorI = new Vector();
	Vector errorMessagesVectorJ = new Vector();
	Vector errorMessagesVectorK = new Vector();
	Vector errorMessagesVectorL = new Vector();
	AreasOfExpertise expertiseList; 
	
	if (providerData == null)
	{	
		providerData = new Provider();
		expertiseList = new AreasOfExpertise();
    }
    else
    {	
    	expertiseList = providerData.getAreasOfExpertise();    	
        taxID = providerData.getTaxID().trim();
        anthemPIN = providerData.getAnthemPIN().trim();
        moId = providerData.getMOProviderId().trim();
        wiId = providerData.getWIProviderId().trim();
        grpmedicaidId =  providerData.getGrpMedicaidId().trim();
        grpNpiNumber = providerData.getGrpNPINumber().trim();
		provNpiNumber = providerData.getProvNPINumber().trim();
        taxonomyNum = PMFUtils.convertSplChar(providerData.getTaxonomyNumber().trim());
        pracName1 = providerData.getPracName1().trim();
        soloGroup = providerData.getSoloGroup();
        numberInGroup = providerData.getNumberInGroup().trim();
		rapidUpdate = providerData.getRapidUpdate();
        effectiveDate = providerData.getEffectiveDate().trim();
		addProvider = providerData.getAddProvider();
		delProvider = providerData.getDelProvider();
        addLocation = providerData.getAddLocation();
       
        addNPI = providerData.getAddNPI();
        chgNPI = providerData.getChgNPI();
        addProvToLocation = providerData.getAddProvToLocation();
        chgSpecialty = providerData.getChgSpecialty();
        chgProvName = providerData.getChgProvName();
        delLocation = providerData.getDelLocation();
        delProvFromLocation = providerData.getDelProvFromLocation();
        chgPracName = providerData.getChgPracName();
        chgPracAddress = providerData.getChgPracAddress();
        chgPracPhone = providerData.getChgPracPhone();
        chgTaxID = providerData.getChgTaxID();
        chgBillName = providerData.getChgBillName();
        chgBillAddress = providerData.getChgBillAddress();
        chgBillPhone = providerData.getChgBillPhone();
        delReason = providerData.getDelReason().trim();
        comments = providerData.getComments().trim();
        confirmation = providerData.getConfirmation();
        
        //PMF SSCR 13503
        confProvAgreement = providerData.getConfProvAgreement();
        confW2 = providerData.getConfW2();
        //PMF SSCR 13503
        
        oldTaxID = providerData.getOldTaxID().trim();
        provFnm = providerData.getProvFnm().trim();
        provMI = providerData.getProvMI().trim();
        provLnm = providerData.getProvLnm().trim();
        title = providerData.getTitle().trim();
        primSpecialtyPhy = PMFUtils.convertSplChar(providerData.getPrimSpecialtyPhy().trim());
       // System.out.println("primSpecialtyPhy=++ "+primSpecialtyPhy);
        specialtyCarePhy = PMFUtils.convertSplChar(providerData.getSpecialtyCarePhy().trim());
       // System.out.println("specialtyCarePhy+++ "+specialtyCarePhy);
        other = PMFUtils.convertSplChar(providerData.getOther().trim());
      //  System.out.println("other+++ "+other);
        specialty = PMFUtils.convertSplChar(providerData.getSpecialty().trim());
        // change for section c 13/6/2012
        specialityInfo = providerData.getSpecialityInfo();
        clearSelection = PMFUtils.convertSplChar(providerData.getClearSelection().trim());
        medicarePartTrad = PMFUtils.convertSplChar(providerData.getMedicarePartTrad().trim());
        medicareApplDt = providerData.getMedicareApplDt().trim();
        medicareOptOut = PMFUtils.convertSplChar(providerData.getMedicareOptOut().trim());
        medicareOptOutDt = providerData.getMedicareOptOutDt().trim();
        npSupSpec= providerData.getNpSupSpec().trim();
        npSupPMP= providerData.getNpSupPMP().trim();
        retailHealthClinic= providerData.getRetailHealthClinic().trim();
        walkInDrOffice= providerData.getWalkInDrOffice().trim();
        urgentCare= providerData.getUrgentCare().trim();
        coveringPMP= providerData.getCoveringPMP().trim();
        certMidwife= providerData.getCertMidwife().trim();
        prenatelCareCoord= providerData.getPrenatelCareCoord().trim();
        tribalHealthCtr= providerData.getTribalHealthCtr().trim();
        clinic= providerData.getClinic().trim();
        medicationAssistedTreatment = providerData.getMedicationAssistedTreatment();
        residentialTreatmentCenter = providerData.getResidentialTreatmentCenter();
        substanceUseDisorderAdults = providerData.getSubstanceUseDisorderAdults();
        substanceUseDisorderChild = providerData.getSubstanceUseDisorderChild();
        telehealthProv = providerData.getTelehealthProv();
        fwdHealthCertNPI1= providerData.getFwdHealthCertNPI1().trim();
        fwdHealthCertNPIBilled1= PMFUtils.convertSplChar(providerData.getFwdHealthCertNPIBilled1().trim());
        fwdHealthCertEffDt1= providerData.getFwdHealthCertEffDt1().trim();
        fwdHealthRecertEffDt1= providerData.getFwdHealthRecertEffDt1().trim();
        fwdHealthCertNPI2= providerData.getFwdHealthCertNPI2().trim();
        fwdHealthCertNPIBilled2= PMFUtils.convertSplChar(providerData.getFwdHealthCertNPIBilled2().trim());
        fwdHealthCertEffDt2= providerData.getFwdHealthCertEffDt2().trim();
        fwdHealthRecertEffDt2= providerData.getFwdHealthRecertEffDt2().trim();
        fwdHealthCertNPI3= providerData.getFwdHealthCertNPI3().trim();
        fwdHealthCertNPIBilled3= PMFUtils.convertSplChar(providerData.getFwdHealthCertNPIBilled3().trim());
        fwdHealthCertEffDt3= providerData.getFwdHealthCertEffDt3().trim();
        fwdHealthRecertEffDt3= providerData.getFwdHealthRecertEffDt3().trim();
        // billingAndRendering = PMFUtils.convertSplChar(providerData.getBillingAndRendering().trim());
    	//renderingProvider = PMFUtils.convertSplChar(providerData.getRenderingProvider().trim());
     	//groupProvider = PMFUtils.convertSplChar(providerData.getGroupProvider().trim());
        // change for section c 13/6/2012
        provNumber = providerData.getProvSSN().trim();
        upinNumber = PMFUtils.convertSplChar(providerData.getUpinNumber().trim());
        profLicenseNumber = providerData.getProfLicenseNumber().trim();
        caqhIDNumber = providerData.getCaqhIDNumber().trim();
        provDOB = providerData.getProvDOB().trim();
        provGender = providerData.getProvGender().trim();
	    pracOfficeAddress1 = providerData.getPracOfficeAddress1().trim();
        pracOfficeCity1 = providerData.getPracOfficeCity1().trim();
        pracOfficeState1 = providerData.getPracOfficeState1().trim();
        pracOfficeZip1 = providerData.getPracOfficeZip1().trim();
        pracOfficeCounty1 = providerData.getPracOfficeCounty1().trim();
        pracOfficePhone1 = providerData.getPracOfficePhone1().trim();
        pracOfficeFax1 = providerData.getPracOfficeFax1().trim();
        pracOfficeEmail1 = providerData.getPracOfficeEmail1().trim();
        billAddressSame1 = providerData.getBillAddressSame1().trim();
        pracBillAddress1 = providerData.getPracBillAddress1().trim();
        pracBillContactName1 = providerData.getPracBillContactName1().trim();
        pracBillCity1 = providerData.getPracBillCity1().trim();
        pracBillState1 = providerData.getPracBillState1().trim();
        pracBillZip1 = providerData.getPracBillZip1().trim();
        pracBillCounty1 = providerData.getPracBillCounty1().trim();
        pracBillPhone1 = providerData.getPracBillPhone1().trim();
        pracBillFax1 = providerData.getPracBillFax1().trim();        
        pracBillContactEmail1 = providerData.getPracBillContactEmail1().trim();
        billMedicareGroup1 = providerData.getBillMedicareGroup1().trim();
        billMedicareIndividual1 = providerData.getBillMedicareIndividual1().trim();
        medicaidGroup1 = providerData.getMedicaidGroup1().trim();
        medicaidIndividual1 = providerData.getMedicaidIndividual1().trim();
        pracName2 = providerData.getPracName2();
	    pracOfficeAddress2 = providerData.getPracOfficeAddress2();
        pracOfficeCity2 = providerData.getPracOfficeCity2();
        pracOfficeState2 = providerData.getPracOfficeState2();
        pracOfficeZip2 = providerData.getPracOfficeZip2();
        pracOfficeCounty2 = providerData.getPracOfficeCounty2();
        pracOfficePhone2 = providerData.getPracOfficePhone2();
        pracOfficeFax2 = providerData.getPracOfficeFax2();
        pracOfficeEmail2 = providerData.getPracOfficeEmail2();
        billAddressSame2 = providerData.getBillAddressSame2();
        pracBillContactName2 = providerData.getPracBillContactName2().trim();
        pracBillAddress2 = providerData.getPracBillAddress2();
        pracBillCity2 = providerData.getPracBillCity2();
        pracBillState2 = providerData.getPracBillState2();
        pracBillZip2 = providerData.getPracBillZip2();
        pracBillCounty2 = providerData.getPracBillCounty2();
        pracBillPhone2 = providerData.getPracBillPhone2();
        pracBillFax2 = providerData.getPracBillFax2();
        pracBillContactEmail2 = providerData.getPracBillContactEmail2().trim();
        billMedicareGroup2 = providerData.getBillMedicareGroup2();
        billMedicareIndividual2 = providerData.getBillMedicareIndividual2();
        medicaidGroup2 = providerData.getMedicaidGroup2().trim();
        medicaidIndividual2 = providerData.getMedicaidIndividual2().trim();
	    pracName3 = providerData.getPracName3();
	   	pracOfficeAddress3 = providerData.getPracOfficeAddress3();
        pracOfficeCity3 = providerData.getPracOfficeCity3();
        pracOfficeState3 = providerData.getPracOfficeState3();
        pracOfficeZip3 = providerData.getPracOfficeZip3();
        pracOfficeCounty3 = providerData.getPracOfficeCounty3();
        pracOfficePhone3 = providerData.getPracOfficePhone3();
        pracOfficeFax3 = providerData.getPracOfficeFax3();
        pracOfficeEmail3 = providerData.getPracOfficeEmail3();
        billAddressSame3 = providerData.getBillAddressSame3();
        pracBillContactName3 = providerData.getPracBillContactName3().trim();
        pracBillAddress3 = providerData.getPracBillAddress3();
        pracBillCity3 = providerData.getPracBillCity3();
        pracBillState3 = providerData.getPracBillState3();
        pracBillZip3 = providerData.getPracBillZip3();
        pracBillCounty3 = providerData.getPracBillCounty3();
        pracBillPhone3 = providerData.getPracBillPhone3();
        pracBillFax3 = providerData.getPracBillFax3();
        pracBillContactEmail3 = providerData.getPracBillContactEmail3().trim();
        billMedicareGroup3 = providerData.getBillMedicareGroup3();
        billMedicareIndividual3 = providerData.getBillMedicareIndividual3();
        medicaidGroup3 = providerData.getMedicaidGroup3().trim();
        medicaidIndividual3 = providerData.getMedicaidIndividual3().trim();
	    pracName4 = providerData.getPracName4();
	    pracOfficeAddress4 = providerData.getPracOfficeAddress4();
        pracOfficeCity4 = providerData.getPracOfficeCity4();
        pracOfficeState4 = providerData.getPracOfficeState4();
        pracOfficeZip4 = providerData.getPracOfficeZip4();
        pracOfficeCounty4 = providerData.getPracOfficeCounty4();
        pracOfficePhone4 = providerData.getPracOfficePhone4();
        pracOfficeFax4 = providerData.getPracOfficeFax4();
        pracOfficeEmail4 = providerData.getPracOfficeEmail4();
        billAddressSame4 = providerData.getBillAddressSame4();
        pracBillContactName4 = providerData.getPracBillContactName4().trim();
        pracBillAddress4 = providerData.getPracBillAddress4();
        pracBillCity4 = providerData.getPracBillCity4();
        pracBillState4 = providerData.getPracBillState4();
        pracBillZip4 = providerData.getPracBillZip4();
        pracBillCounty4 = providerData.getPracBillCounty4();
        pracBillPhone4 = providerData.getPracBillPhone4();
        pracBillFax4 = providerData.getPracBillFax4();
        pracBillContactEmail4 = providerData.getPracBillContactEmail4().trim();
        billMedicareGroup4 = providerData.getBillMedicareGroup4();
        billMedicareIndividual4 = providerData.getBillMedicareIndividual4();
        medicaidGroup4 = providerData.getMedicaidGroup4().trim();
        medicaidIndividual4 = providerData.getMedicaidIndividual4().trim();
	    pracName5 = providerData.getPracName5();
	    pracOfficeAddress5 = providerData.getPracOfficeAddress5();
        pracOfficeCity5 = providerData.getPracOfficeCity5();
        pracOfficeState5 = providerData.getPracOfficeState5();
        pracOfficeZip5 = providerData.getPracOfficeZip5();
        pracOfficeCounty5 = providerData.getPracOfficeCounty5();
        pracOfficePhone5 = providerData.getPracOfficePhone5();
        pracOfficeFax5 = providerData.getPracOfficeFax5();
        pracOfficeEmail5 = providerData.getPracOfficeEmail5();
        billAddressSame5 = providerData.getBillAddressSame5();
        pracBillContactName5 = providerData.getPracBillContactName5().trim();
        pracBillAddress5 = providerData.getPracBillAddress5();
        pracBillCity5 = providerData.getPracBillCity5();
        pracBillState5 = providerData.getPracBillState5();
        pracBillZip5 = providerData.getPracBillZip5();
        pracBillCounty5 = providerData.getPracBillCounty5();
        pracBillPhone5 = providerData.getPracBillPhone5();
        pracBillFax5 = providerData.getPracBillFax5();
        pracBillContactEmail5 = providerData.getPracBillContactEmail5().trim();
        billMedicareGroup5 = providerData.getBillMedicareGroup5();
        billMedicareIndividual5 = providerData.getBillMedicareIndividual5();
        medicaidGroup5 = providerData.getMedicaidGroup5().trim();
        medicaidIndividual5 = providerData.getMedicaidIndividual5().trim();
	    pracName6 = providerData.getPracName6();
	    pracOfficeAddress6 = providerData.getPracOfficeAddress6();
        pracOfficeCity6 = providerData.getPracOfficeCity6();
        pracOfficeState6 = providerData.getPracOfficeState6();
        pracOfficeZip6 = providerData.getPracOfficeZip6();
        pracOfficeCounty6 = providerData.getPracOfficeCounty6();
        pracOfficePhone6 = providerData.getPracOfficePhone6();
        pracOfficeFax6 = providerData.getPracOfficeFax6();
        pracOfficeEmail6 = providerData.getPracOfficeEmail6();
        billAddressSame6 = providerData.getBillAddressSame6();
        pracBillContactName6 = providerData.getPracBillContactName6().trim();
        pracBillAddress6 = providerData.getPracBillAddress6();
        pracBillCity6 = providerData.getPracBillCity6();
        pracBillState6 = providerData.getPracBillState6();
        pracBillZip6 = providerData.getPracBillZip6();
        pracBillCounty6 = providerData.getPracBillCounty6();
        pracBillPhone6 = providerData.getPracBillPhone6();
        pracBillFax6 = providerData.getPracBillFax6();
        pracBillContactEmail6 = providerData.getPracBillContactEmail6().trim();
        billMedicareGroup6 = providerData.getBillMedicareGroup6();
        billMedicareIndividual6 = providerData.getBillMedicareIndividual6();
        medicaidGroup6 = providerData.getMedicaidGroup6().trim();
        medicaidIndividual6 = providerData.getMedicaidIndividual6().trim();
        grpEntityName1 = providerData.getGrpEntityName1();
        specialty1 = providerData.getSpecialty1();
        taxID1 = providerData.getTaxID1();
        effectiveDate1 = providerData.getEffectiveDate1();
        grpEntityName2 = providerData.getGrpEntityName2();
        specialty2 = providerData.getSpecialty2();
        taxID2 = providerData.getTaxID2();
        effectiveDate2 = providerData.getEffectiveDate2();
        grpEntityName3 = providerData.getGrpEntityName3();
        specialty3 = providerData.getSpecialty3();
        taxID3 = providerData.getTaxID3();
        effectiveDate3 = providerData.getEffectiveDate3();
        grpEntityName4 = providerData.getGrpEntityName4();
        specialty4 = providerData.getSpecialty4();
        taxID4 = providerData.getTaxID4();
        effectiveDate4 = providerData.getEffectiveDate4();
        grpEntityName5 = providerData.getGrpEntityName5();
        specialty5 = providerData.getSpecialty5();
        taxID5 = providerData.getTaxID5();
        effectiveDate5 = providerData.getEffectiveDate5();
        		
		cpHospitalAddress = providerData.getCpHospitalAddress();
		cpHospitalCity = providerData.getCpHospitalCity();
		cpHospitalState = providerData.getCpHospitalState();
		cpHospitalZip = providerData.getCpHospitalZip();

        caqhExplanation = PMFUtils.convertSplChar(providerData.getCaqhExplanation());
        chgOfficeHours = providerData.getChgOfficeHours();
        boardCertified = providerData.getBoardCertified();
        certExamDT = providerData.getCertExamDT();
        newPatients = providerData.getNewPatients();
        ageLimitMin = PMFUtils.convertSplChar(providerData.getAgeLimitMin());
        ageLimitMax = PMFUtils.convertSplChar(providerData.getAgeLimitMax());     
               
        pubicTrans1 =  providerData.getPubicTrans1();
        handicapAccess1 = providerData.getHandicapAccess1();
        eveningHrs1 = providerData.getEveningHrs1();
		
        timeZoneOfficeHrs = providerData.getTimeZoneOfficeHrs();
		// 2013 SSCR 13503 changes
    	provDir1 = providerData.getProvDir1();
    	remitSamePrim2 = providerData.getRemitSamePrim2().trim();
    	provDir2 = providerData.getProvDir2();
    	remitSamePrim3 = providerData.getRemitSamePrim3().trim();
		remitSamePrim4 = providerData.getRemitSamePrim4().trim();
		remitSamePrim5 = providerData.getRemitSamePrim5().trim();
		remitSamePrim6 = providerData.getRemitSamePrim6().trim();
		provDir3 = providerData.getProvDir3();
		provDir4 = providerData.getProvDir4();
		provDir5 = providerData.getProvDir5();
		provDir6 = providerData.getProvDir6();
		
    	
     	//end 2013 SSCR 13503 changes
		daysOpenMon0 = providerData.getDaysOpenMon0();
        daysOpenTue0 = providerData.getDaysOpenTue0();
        daysOpenWed0 = providerData.getDaysOpenWed0();
        daysOpenThu0 = providerData.getDaysOpenThu0();
        daysOpenFri0 = providerData.getDaysOpenFri0();
        daysOpenSat0 = providerData.getDaysOpenSat0();
        daysOpenSun0 = providerData.getDaysOpenSun0();
		
		daysOpenMon1 = providerData.getDaysOpenMon1();
		daysOpenTue1 = providerData.getDaysOpenTue1();
		daysOpenWed1 = providerData.getDaysOpenWed1();
		daysOpenThu1 = providerData.getDaysOpenThu1();
		daysOpenFri1 = providerData.getDaysOpenFri1();
		daysOpenSat1 = providerData.getDaysOpenSat1();
		daysOpenSun1 = providerData.getDaysOpenSun1();
		
		daysOpenMon2 = providerData.getDaysOpenMon2();
		daysOpenTue2 = providerData.getDaysOpenTue2();
		daysOpenWed2 = providerData.getDaysOpenWed2();
		daysOpenThu2 = providerData.getDaysOpenThu2();
		daysOpenFri2 = providerData.getDaysOpenFri2();
		daysOpenSat2 = providerData.getDaysOpenSat2();
		daysOpenSun2 = providerData.getDaysOpenSun2();

		daysOpenMon3 = providerData.getDaysOpenMon3();
		daysOpenTue3 = providerData.getDaysOpenTue3();
		daysOpenWed3 = providerData.getDaysOpenWed3();
		daysOpenThu3 = providerData.getDaysOpenThu3();
		daysOpenFri3 = providerData.getDaysOpenFri3();
		daysOpenSat3 = providerData.getDaysOpenSat3();
		daysOpenSun3 = providerData.getDaysOpenSun3();		

		daysOpenMon4 = providerData.getDaysOpenMon4();
		daysOpenTue4 = providerData.getDaysOpenTue4();
		daysOpenWed4 = providerData.getDaysOpenWed4();
		daysOpenThu4 = providerData.getDaysOpenThu4();
		daysOpenFri4 = providerData.getDaysOpenFri4();
		daysOpenSat4 = providerData.getDaysOpenSat4();
		daysOpenSun4 = providerData.getDaysOpenSun4();

		daysOpenMon5 = providerData.getDaysOpenMon5();
		daysOpenTue5 = providerData.getDaysOpenTue5();
		daysOpenWed5 = providerData.getDaysOpenWed5();
		daysOpenThu5 = providerData.getDaysOpenThu5();
		daysOpenFri5 = providerData.getDaysOpenFri5();
		daysOpenSat5 = providerData.getDaysOpenSat5();
		daysOpenSat5 = providerData.getDaysOpenSat5();
		//for speciality display 14/06/2012
		
    	
    	/*for(int cntr = 0; cntr <= 166;cntr++){
    		speciality[cntr] = providerData.getSpeciality();
    	}*/
    	specialty = providerData.getSpecialty();
    	System.out.println(specialty+" specialty value....");
    	//for speciality display 14/06/2012
		
		daysOpenTimeMon = providerData.getDaysOpenTimeMon();
		daysCloseTimeMon = providerData.getDaysCloseTimeMon();
		daysOpenTimeTue = providerData.getDaysOpenTimeTue();
		daysCloseTimeTue = providerData.getDaysCloseTimeTue();
		daysOpenTimeWed = providerData.getDaysOpenTimeWed();
		daysCloseTimeWed = providerData.getDaysCloseTimeWed();
		daysOpenTimeThu = providerData.getDaysOpenTimeThu();
		daysCloseTimeThu = providerData.getDaysCloseTimeThu();
		daysOpenTimeFri = providerData.getDaysOpenTimeFri();
		daysCloseTimeFri = providerData.getDaysCloseTimeFri();
		daysOpenTimeSat= providerData.getDaysOpenTimeSat();
		daysCloseTimeSat = providerData.getDaysCloseTimeSat();		
		daysOpenTimeSun = providerData.getDaysOpenTimeSun();
		daysCloseTimeSun = providerData.getDaysCloseTimeSun();
	
		
	
        pubicTrans2 = providerData.getPubicTrans2();
        handicapAccess2 =  providerData.getHandicapAccess2();
        eveningHrs2 = providerData.getEveningHrs2();
       
        pubicTrans3 = providerData.getPubicTrans3();
        handicapAccess3 = providerData.getHandicapAccess3();
        eveningHrs3 = providerData.getEveningHrs3();
        
        pubicTrans4 = providerData.getPubicTrans4();
        handicapAccess4 = providerData.getHandicapAccess4();
        eveningHrs4 = providerData.getEveningHrs4();
        
                pubicTrans5 = providerData.getPubicTrans5();
        handicapAccess5 = providerData.getHandicapAccess5();
        eveningHrs5 = providerData.getEveningHrs5();
        
        pubicTrans6 =  providerData.getPubicTrans6();
        handicapAccess6 = providerData.getHandicapAccess6();
        eveningHrs6 = providerData.getEveningHrs6();
        //Changes for the state mandate on 02/10/10 start
		medicaidIndicator = providerData.getMedicaidIndicator();
		medicaidPCP = providerData.getMedicaidPCP();
		medicaidMaxPanel = providerData.getMedicaidMaxPanel();
		hipMaxPanel = providerData.getHipMaxPanel();
		medicaidSpecialist = providerData.getMedicaidSpecialist();
		hipIndicator = providerData.getHipIndicator();
		hipPCP = providerData.getHipPCP();
		hipSpecialist = providerData.getHipSpecialist();
		ssMedical = providerData.getSsMedical();
		ssDental = providerData.getSsDental();
		ssVision = providerData.getSsVision();
		ssOtherServType = providerData.getSsOtherServType();
		indivPractice = providerData.getIndivPractice();
		groupPractice = providerData.getGroupPractice();
		schoolBasedClinic = providerData.getSchoolBasedClinic();
		tribalHealthCenter = providerData.getTribalHealthCenter();
		ruralHealthClinic = providerData.getRuralHealthClinic();
		fedQualHealthClinic = providerData.getFedQualHealthClinic();
		communityHealthCenter = providerData.getCommunityHealthCenter();
		deptOfHealth = providerData.getDeptOfHealth();
		otherPractice = providerData.getOtherPractice();
		radHospBased = providerData.getRadHospBased();
		radFreeStandingCenter = providerData.getRadFreeStandingCenter();
		languagesSpoken1 = providerData.getLanguagesSpoken1();
	    offerECI1 = providerData.getOfferECI1();
	    offerEPSDT1 = providerData.getOfferEPSDT1();
	    provideADB1 = providerData.getProvideADB1();
	    provideCSHCN1 = providerData.getProvideCSHCN1();
	    languagesSpoken2 = PMFUtils.convertSplChar(providerData.getLanguagesSpoken2());
	    offerECI2 = providerData.getOfferECI2();
	    offerEPSDT2 = providerData.getOfferEPSDT2();
	    provideADB2 = providerData.getProvideADB2();
	    provideCSHCN2 = providerData.getProvideCSHCN2();
	    languagesSpoken3 = PMFUtils.convertSplChar(providerData.getLanguagesSpoken3());
	    offerECI3 = providerData.getOfferECI3();
	    offerEPSDT3 = providerData.getOfferEPSDT3();
	    provideADB3 = providerData.getProvideADB3();
	    provideCSHCN3 = providerData.getProvideCSHCN3();
	    languagesSpoken4 = PMFUtils.convertSplChar(providerData.getLanguagesSpoken4());
	    offerECI4 = providerData.getOfferECI4();
	    offerEPSDT4 = providerData.getOfferEPSDT4();
	    provideADB4 = providerData.getProvideADB4();
	    provideCSHCN4 = providerData.getProvideCSHCN4();
	    languagesSpoken5 = PMFUtils.convertSplChar(providerData.getLanguagesSpoken5());
	    offerECI5 = providerData.getOfferECI5();
	    offerEPSDT5 = providerData.getOfferEPSDT5();
	    provideADB5 = providerData.getProvideADB5();
	    provideCSHCN5 = providerData.getProvideCSHCN5();
	    languagesSpoken6 = PMFUtils.convertSplChar(providerData.getLanguagesSpoken6());
	    offerECI6 = providerData.getOfferECI6();
	    offerEPSDT6 = providerData.getOfferEPSDT6();
	    provideADB6 = providerData.getProvideADB6();
	    provideCSHCN6 = providerData.getProvideCSHCN6();
	    //Changes for the state mandate on 02/10/10 end
	    
	    matWaiveredPrescriber1 = providerData.getMatWaiveredPrescriber1();
	    certOpioidTreat1 = providerData.getCertOpioidTreat1();
		matOpioid1 = providerData.getMatOpioid1();
		counselOpioid1 = providerData.getCounselOpioid1();
		sudProv1 = providerData.getSudProv1();
		resTreatCtr1 = providerData.getResTreatCtr1();
		matWaiveredPrescriber2 = providerData.getMatWaiveredPrescriber2();
		certOpioidTreat2 = providerData.getCertOpioidTreat2();
		matOpioid2 = providerData.getMatOpioid2();
		counselOpioid2 = providerData.getCounselOpioid2();
		sudProv2 = providerData.getSudProv2();
		resTreatCtr2 = providerData.getResTreatCtr2();
		matWaiveredPrescriber3 = providerData.getMatWaiveredPrescriber3();
		certOpioidTreat3 = providerData.getCertOpioidTreat3();
		matOpioid3 = providerData.getMatOpioid3();
		counselOpioid3 = providerData.getCounselOpioid3();
		sudProv3 = providerData.getSudProv3();
		resTreatCtr3 = providerData.getResTreatCtr3();
		matWaiveredPrescriber4 = providerData.getMatWaiveredPrescriber4();
		certOpioidTreat4 = providerData.getCertOpioidTreat4();
		matOpioid4 = providerData.getMatOpioid4();
		counselOpioid4 = providerData.getCounselOpioid4();
		sudProv4 = providerData.getSudProv4();
		resTreatCtr4 = providerData.getResTreatCtr4();
		matWaiveredPrescriber5 = providerData.getMatWaiveredPrescriber5();
		certOpioidTreat5 = providerData.getCertOpioidTreat5();
		matOpioid5 = providerData.getMatOpioid5();
		counselOpioid5 = providerData.getCounselOpioid5();
		sudProv5 = providerData.getSudProv5();
		resTreatCtr5 = providerData.getResTreatCtr5();
		matWaiveredPrescriber6 = providerData.getMatWaiveredPrescriber6();
		certOpioidTreat6 = providerData.getCertOpioidTreat6();
		matOpioid6 = providerData.getMatOpioid6();
		counselOpioid6 = providerData.getCounselOpioid6();
		sudProv6 = providerData.getSudProv6();
		resTreatCtr6 = providerData.getResTreatCtr6();
		
		provideTelehealth1 = providerData.getProvideTelehealth1();
		provideTelehealth2 = providerData.getProvideTelehealth2();
		provideTelehealth3 = providerData.getProvideTelehealth3();
		provideTelehealth4 = providerData.getProvideTelehealth4();
		provideTelehealth5 = providerData.getProvideTelehealth5();
		provideTelehealth6 = providerData.getProvideTelehealth6();
	    
	    //The following are added to show the old values in UI when some error occurs 03/04/2010
		pracNPINo1 = providerData.getPracNPINo1();
		pracNPINo2 = providerData.getPracNPINo2();
		pracNPINo3 = providerData.getPracNPINo3();
		pracNPINo4 = providerData.getPracNPINo4();
		pracNPINo5 = providerData.getPracNPINo5();							   
		pracNPINo6 = providerData.getPracNPINo6();
		
		
		apHospitalName = providerData.getApHospitalName();
		apHospitalAddress = providerData.getApHospitalAddress();
		apHospitalCity = providerData.getApHospitalCity();
		apHospitalState = providerData.getApHospitalState();
		apHospitalZip = providerData.getApHospitalZip();
		
		dpHospitalName = providerData.getDpHospitalName();
		dpHospitalAddress = providerData.getDpHospitalAddress();
		dpHospitalCity = providerData.getDpHospitalCity();
		dpHospitalState = providerData.getDpHospitalState();
		dpHospitalZip = providerData.getDpHospitalZip();
		
		mgdCareDisenroll = providerData.getMgdCareDisenroll();
	    ihcpProvNo = providerData.getIhcpProvNo();
	    pmp = providerData.getPmp();
	    pmpSpecialty = providerData.getPmpSpecialty();
	    hospAdmitPriv = providerData.getHospAdmitPriv();
	    relationshipPriv = providerData.getRelationshipPriv();
	    deliveryPriv = providerData.getDeliveryPriv();
	    ageRestriction = providerData.getAgeRestriction();
	    pmpScopeOb = providerData.getPmpScopeOb();
	    pmpScopeAll = providerData.getPmpScopeAll();
	    genderScope = providerData.getGenderScope();
	    medPanelStatus = providerData.getMedPanelStatus();
	    hipPanelStatus = providerData.getHipPanelStatus();
	    medNbrLocations = providerData.getMedNbrLocations();
	    hipNbrLocations = providerData.getHipNbrLocations();
	    medPldPanelDecrease = providerData.getMedPldPanelDecrease();
	    hipPldPanelDecrease = providerData.getHipPldPanelDecrease();
	    medPldPlacePanelAt = providerData.getMedPldPlacePanelAt();
	    hipPldPlacePanelAt = providerData.getHipPldPlacePanelAt();
	    medPldGrpMedicaidNo = providerData.getMedPldGrpMedicaidNo();
	    hipPldGrpMedicaidNo = providerData.getHipPldGrpMedicaidNo();
	    medPliPanelIncrease = providerData.getMedPliPanelIncrease();
	    hipPliPanelIncrease = providerData.getHipPliPanelIncrease();
	    medPliPlacePanelAt = providerData.getMedPliPlacePanelAt();
	    hipPliPlacePanelAt = providerData.getHipPliPlacePanelAt();
	    medPliGrpMedicaidNbr = providerData.getMedPliGrpMedicaidNbr();
	    hipPliGrpMedicaidNbr = providerData.getHipPliGrpMedicaidNbr();
	    medPanelHold = PMFUtils.convertSplChar(providerData.getMedPanelHold());
	    hipPanelHold = PMFUtils.convertSplChar(providerData.getHipPanelHold());
	    medPlrPanelHold  = providerData.getMedPlrPanelHold();
	    hipPlrPanelHold = providerData.getHipPlrPanelHold();
	    medPanelHoldRemove  = PMFUtils.convertSplChar(providerData.getMedPanelHoldRemove());
	    hipPanelHoldRemove = PMFUtils.convertSplChar(providerData.getHipPanelHoldRemove());
	    medPlrPanelHoldRemove = providerData.getMedPlrPanelHoldRemove();
	    hipPlrPanelHoldRemove = providerData.getHipPlrPanelHoldRemove();
	    deaNo = providerData.getDeaNo();
	    csrNo = providerData.getCsrNo();
	    enrollAs = providerData.getEnrollAs();
	    enrollClinicType = providerData.getEnrollClinicType();
	    locationType = providerData.getLocationType();
	    paPractice = providerData.getPaPractice();
	    npPractice = providerData.getNpPractice();
	    naPractice = providerData.getNaPractice();
	    stateLicIssueDt = providerData.getStateLicIssueDt();
	    stateLicExpDt = providerData.getStateLicExpDt();
	    profLiabCarrier = providerData.getProfLiabilityCarrierName();
	    profLiabCovgLimit = providerData.getProfLiabilityCarrierLimit();
	    profLiabPolicy = providerData.getProfLiabilityPolicyNo();
	    profLiabExtDt = providerData.getProfLiabilityExpDate();
	    malPracInsRevoke = PMFUtils.convertSplChar(providerData.getMalPracInsRevoke());
	    underGovInvestigation = PMFUtils.convertSplChar(providerData.getUnderGovInvestigation());
	    expellMedPay = PMFUtils.convertSplChar(providerData.getExpellMedPay());
		nonPsychEval  = PMFUtils.convertSplChar(providerData.getNonPsychEval());
		
		/* PMF Section Changes -- AD21239 --*/
		//radio button
		kyMedicaidPart1 = PMFUtils.convertSplChar(providerData.getKyMedicaidPart1().trim());
		kyMedicaidPart2 = PMFUtils.convertSplChar(providerData.getKyMedicaidPart2().trim());
		kyMedicaidPart3 = PMFUtils.convertSplChar(providerData.getKyMedicaidPart3().trim());
		kyMedicaidPart4 = PMFUtils.convertSplChar(providerData.getKyMedicaidPart4().trim());
		kyMedicaidPart5 = PMFUtils.convertSplChar(providerData.getKyMedicaidPart5().trim());
		kyMedicaidPart6 = PMFUtils.convertSplChar(providerData.getKyMedicaidPart6().trim());
      	
      	//textbox
      	kyMedicaidId1 = providerData.getKyMedicaidId1().trim();
      	kyMedicaidId2 = providerData.getKyMedicaidId2().trim();
      	kyMedicaidId3 = providerData.getKyMedicaidId3().trim();
      	kyMedicaidId4 = providerData.getKyMedicaidId4().trim();
      	kyMedicaidId5 = providerData.getKyMedicaidId5().trim();
      	kyMedicaidId6 = providerData.getKyMedicaidId6().trim();
	      	
      	//checkbox
      	paSupSpec= providerData.getPaSupSpec().trim();
      	paSupPMP= providerData.getPaSupPMP().trim();
      	
      	w2Comments = providerData.getW2Comments().trim();
 		/* PMF Section Changes -- AD21239 --*/
 		
		errorMessagesVectorAll = (Vector)providerData.getErrorMessagesVectorAll();
		errorMessagesVectorA = (Vector)providerData.getErrorMessagesVectorA();
		errorMessagesVectorB = (Vector)providerData.getErrorMessagesVectorB();
		errorMessagesVectorC = (Vector)providerData.getErrorMessagesVectorC();
		errorMessagesVectorD = (Vector)providerData.getErrorMessagesVectorD();
		errorMessagesVectorE = (Vector)providerData.getErrorMessagesVectorE();
		errorMessagesVectorF = (Vector)providerData.getErrorMessagesVectorF();
		errorMessagesVectorG = (Vector)providerData.getErrorMessagesVectorG();
		errorMessagesVectorH = (Vector)providerData.getErrorMessagesVectorH();
		errorMessagesVectorI = (Vector)providerData.getErrorMessagesVectorI();
		errorMessagesVectorJ = (Vector)providerData.getErrorMessagesVectorJ();
		errorMessagesVectorK = (Vector)providerData.getErrorMessagesVectorK();
		errorMessagesVectorL = (Vector)providerData.getErrorMessagesVectorL();
		fileUploadStat = providerData.getUploadFileStat();
	}	
%>
<script language="javascript">
	<%@ include file="mwpmf_javascript.js" %>
	
	function callOnLoad()
	{
		var filaStat = '<%= fileUploadStat %>';
		if(filaStat=='Y')
		{
		
		document.forms[0].fileContentData.focus();
		}
		
	}
</script>
<html>
<head>
<title>Anthem Blue Cross and Blue Shield Provider Maintenance Form</title>
<link REL="stylesheet" HREF="anthemcssv1.css">
<meta http-equiv="Expires" content="Mon, 18 Jun 2000 00:01:00 GMT">
<meta http=equiv="Pragma" content="no-cache,no-store">
</head>

<body onload="callOnLoad()" topmargin="0" leftmargin="0" marginheight="0" marginwidth="0" bgcolor="#FFFFFF">
<%@ include file="anthem_header.jsp" %>
<!--BEGIN MAIN TABLE-->
<div id="mainSection">
<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0" id="mainSectiontable">
	<TR>
		<TD>
			This site gives you the capability to submit the Provider Maintenance Form electronically..
		</TD>
	</TR>
	<tr VALIGN="TOP" ALIGN="LEFT">
    	<td>
			<FORM NAME="MWPMF" METHOD="POST" ACTION="PMFControllerServlet" >
			<input type="hidden" name="formUpdateAction" id="formUpdateAction" value="<%=action%>">
      		<input type="hidden" name="pracName2" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracName2)%>">
			<input type="hidden" name="hipShow" value="<%=ESAPI.encoder().encodeForHTMLAttribute(hipShow)%>">
				<TABLE BORDER="1" CELLSPACING=2 CELLPADDING=0 WIDTH="100%">
					<%	Iterator theData = errorMessagesVectorAll.iterator();
						if (!errorMessagesVectorAll.isEmpty())
							{%>
						<tr>
							<td valign="middle" align="center" colspan="2" BGCOLOR="#FF0000" height="30"><font size="2" COLOR="#ffffff"><b>Error Messages</b></font></td>
						</tr>
					<%	while(theData.hasNext())
						{
						String value = (String)theData.next(); %>
						<tr>
							<TD colspan="2" style="FONT-SIZE: large;" height="12"><font size="2" COLOR="#FF0000"><b><%=value%></b></font></TD>
						</tr>
					<%	} } %>
						<tr>
							<td colspan="2">
							<%@ include file="/jsps/sectionA.jsp" %>
							</td>
						</tr>
						<tr>
							<td colspan="2">
							<%@ include file="/jsps/sectionB.jsp" %>
							</td>
						</tr>
						<tr>
							<td colspan="2">
							<%@ include file="/jsps/sectionC.jsp" %>
							</td>
						</tr>
						<tr>
							<td colspan="2">
							<%@ include file="/jsps/sectionD.jsp" %>
							</td>
						</tr>
						<tr>
							<td colspan="2">
							<%@ include file="/jsps/sectionE.jsp" %>
							</td >
						</tr>
						<tr>
							<td colspan="2">
							<%@ include file="/jsps/sectionF.jsp" %>
							</td>
						</tr>
						<tr>
							<td colspan="2">
							<%@ include file="/jsps/sectionG.jsp" %>
							</td >
						</tr>
						<tr>
							<td colspan="2">
							<%@ include file="/jsps/sectionH.jsp" %>
							</td >
						</tr>
						<tr>
							<td colspan="2">
							<%@ include file="/jsps/sectionI.jsp" %>
							</td >
						</tr>
						<tr>
							<td colspan="2">
							<%@ include file="/jsps/sectionJ.jsp" %>
							</td >
						</tr>
						<tr>
							<td colspan="2">
							<%@ include file="/jsps/sectionK.jsp" %>
							</td >
						</tr> 
						<tr>
							<td colspan="2">
							<%@ include file="/jsps/sectionL.jsp" %>
							</td >
						</tr>				
						<tr>
							<td valign="top" align="center" colspan="2" BGCOLOR="#ffffff"><font size="2" COLOR="#000080"><b>
							<input type="submit" name="submitFormBtn" id="submitFormBtn" value="Submit Form">&nbsp;&nbsp;&nbsp;
							<input type="submit" name="submitFormBtn" id="submitFormBtn" value="Clear Form">&nbsp;&nbsp;&nbsp;
							<input type="submit" name="submitFormBtn" id="submitFormBtn" value="Exit"></b></font></td>
						</tr>
            	</table>
			</FORM>
		</td>
	</tr>
</TABLE>
</div>
<%@ include file="anthem_footer.htm" %>
</BODY>
</HTML>
