package com.anthem.mwpmf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.fileupload.FileItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.anthem.common.Address;
import com.anthem.util.Constants;
import com.anthem.util.StringUtils;

/**
 * <p>Title: Provider</p>
 * <p>Description: This contains the Provider data. There are presently validations</p>
 * too in this class which should have been moved out to a separate class.
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company:Anthem Insurance </p>
 * @author
 * @version 1.0
 */
public class Provider implements Serializable
{
	private final static Logger logger = LogManager.getLogger(Provider.class);
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String taxID;
    private String anthemPIN = " ";
    private String m_moId = " ";
    private String m_wiId = " ";
    private String grpMedicaidId = " ";
    private String m_grpNPINumber = " ";
    private String m_provNPINumber = " ";
    private String m_taxonomyNum = " ";
    private String pracName1;
    private String soloGroup;
    private String numberInGroup;
    private String rapidUpdate;
    private String effectiveDate;
    private String addProvider;
    private String addNPI = " ";
    private String chgNPI = " ";
    private String delProvider;
    private String addLocation;
    private String addProvToLocation;
    private String chgSpecialty;
    private String chgProvName;
    private String delLocation;
    private String delProvFromLocation;
    private String chgPracName;
    private String chgPracAddress;
    private String chgPracPhone;
    private String chgTaxID;
    private String chgBillName;
    private String chgBillAddress;
    private String chgBillPhone;
    private String delReason;
    private String comments;
    private String confirmation;
    //2013 SSCR 13503 change
    private String confProvAgreement;
    private String confW2;
    //2013 SSCR 13503 change end
    private String oldTaxID;
    private String provFnm;
    private String provMI;
    private String provLnm;
    private String title;
    private String primSpecialtyPhy;
    private String specialtyCarePhy;
    private String other;
    private String submitFormBtn ;
    
	//PMF Change SSCR 13503 13/6/2012 sectionC Start
    private String specialityInfo;
    private String clearSelection;
    private String medicarePartTrad;
    private String medicareApplDt;
    private String medicareOptOut;
    private String medicareOptOutDt;
    
    private String npSupSpec;
    private String npSupPMP;
    private String retailHealthClinic;
    private String walkInDrOffice;
    private String urgentCare;
    private String coveringPMP;
    private String certMidwife;
    private String prenatelCareCoord;
    private String tribalHealthCtr;
    private String clinic;
    private String specialty;
    private String medicationAssistedTreatment="";
    private String residentialTreatmentCenter="";
    private String substanceUseDisorderAdults="";
    private String substanceUseDisorderChild="";
    private String telehealthProv="";
    
	private String fwdHealthCertNPI1;
    private String fwdHealthCertNPIBilled1;
    private String fwdHealthCertEffDt1;
    private String fwdHealthRecertEffDt1;
    private String fwdHealthCertNPI2;
    private String fwdHealthCertNPIBilled2;
    private String fwdHealthCertEffDt2;
    private String fwdHealthRecertEffDt2;
    private String fwdHealthCertNPI3;
    private String fwdHealthCertNPIBilled3;
    private String fwdHealthCertEffDt3;
    private String fwdHealthRecertEffDt3;
    /*private String billingAndRendering;
    private String renderingProvider;
    private String groupProvider;*/
    
    //PMF Change SSCR 13503 13/6/2012 sectionC End
    
    private String provSSN;
    private String upinNumber;
    private String profLicenseNumber;
    private String caqhIDNumber;
    private String provDOB;
    private String provGender;
    private String pracOfficeAddress1;
    private String pracOfficeCity1;
    private String pracOfficeState1;
    private String pracOfficeZip1;
    private String pracOfficeCounty1;
    private String pracOfficePhone1;
    private String pracOfficeFax1;
    private String pracOfficeEmail1;
    private String billAddressSame1;
    private String pracBillContactName1;
    private String pracBillAddress1;
    private String pracBillCity1;
    private String pracBillState1;
    private String pracBillZip1;
    private String pracBillCounty1;
    private String pracBillPhone1;
	private String pracBillFax1 = " ";
    private String pracBillContactEmail1;
    private String pracNPINo1 = " ";
    private String billMedicareGroup1;
    private String billMedicareIndividual1;
    private String medicaidGroup1 = " ";
    private String medicaidIndividual1 = " ";
    public  String pracName2 = " ";
    private String pracOfficeAddress2;
    private String pracOfficeCity2;
    private String pracOfficeState2;
    private String pracOfficeZip2;
    private String pracOfficeCounty2;
    private String pracOfficePhone2;
    private String pracOfficeFax2;
    private String pracOfficeEmail2;
    private String billAddressSame2;
    private String pracBillContactName2;
    private String pracBillAddress2;
    private String pracBillCity2;
    private String pracBillState2;
    private String pracBillZip2;
    private String pracBillCounty2;
    private String pracBillPhone2;
    private String pracBillFax2;
    private String pracBillContactEmail2;
    private String pracNPINo2 = " ";
    private String billMedicareGroup2;
    private String billMedicareIndividual2;
    private String medicaidGroup2 = " ";
    private String medicaidIndividual2 = " ";
    private String pracName3;
    private String pracOfficeAddress3;
    private String pracOfficeCity3;
    private String pracOfficeState3;
    private String pracOfficeZip3;
    private String pracOfficeCounty3;
    private String pracOfficePhone3;
    private String pracOfficeFax3;
    private String pracOfficeEmail3;
    private String billAddressSame3;
    private String pracBillContactName3;
    private String pracBillAddress3;
    private String pracBillCity3;
    private String pracBillState3;
    private String pracBillZip3;
    private String pracBillCounty3;
    private String pracBillPhone3;
    private String pracBillFax3;
    private String pracBillContactEmail3;
    private String pracNPINo3 = " ";
    private String billMedicareGroup3;
    private String billMedicareIndividual3;
    private String medicaidGroup3 = " ";
    private String medicaidIndividual3 = " ";
    private String pracName4;
    private String pracOfficeAddress4;
    private String pracOfficeCity4;
    private String pracOfficeState4;
    private String pracOfficeZip4;
    private String pracOfficeCounty4;
    private String pracOfficePhone4;
    private String pracOfficeFax4;
    private String pracOfficeEmail4;
    private String billAddressSame4;
    private String pracBillContactName4;
    private String pracBillAddress4;
    private String pracBillCity4;
    private String pracBillState4;
    private String pracBillZip4;
    private String pracBillCounty4;
    private String pracBillPhone4;
    private String pracBillFax4;
    private String pracBillContactEmail4;
    private String pracNPINo4 = " ";
    private String billMedicareGroup4;
    private String billMedicareIndividual4;
    private String medicaidGroup4 = " ";
    private String medicaidIndividual4 = " ";
    private String pracName5;
    private String pracOfficeAddress5;
    private String pracOfficeCity5;
    private String pracOfficeState5;
    private String pracOfficeZip5;
    private String pracOfficeCounty5;
    private String pracOfficePhone5;
    private String pracOfficeFax5;
    private String pracOfficeEmail5;
    private String billAddressSame5;
    private String pracBillContactName5;
    private String pracBillAddress5;
    private String pracBillCity5;
    private String pracBillState5;
    private String pracBillZip5;
    private String pracBillCounty5;
    private String pracBillPhone5;
    private String pracBillFax5;
    private String pracBillContactEmail5;
    private String pracNPINo5 = " ";
    private String billMedicareGroup5;
    private String billMedicareIndividual5;
    private String medicaidGroup5 = " ";
    private String medicaidIndividual5 = " ";
    private String pracName6;
    private String pracOfficeAddress6;
    private String pracOfficeCity6;
    private String pracOfficeState6;
    private String pracOfficeZip6;
    private String pracOfficeCounty6;
    private String pracOfficePhone6;
    private String pracOfficeFax6;
    private String pracOfficeEmail6;
    private String billAddressSame6;
    private String pracBillContactName6;
    private String pracBillAddress6;
    private String pracBillCity6;
    private String pracBillState6;
    private String pracBillZip6;
    private String pracBillCounty6;
    private String pracBillPhone6;
    private String pracBillFax6;
    private String pracBillContactEmail6;
    private String pracNPINo6 = " ";
    private String billMedicareGroup6;
    private String billMedicareIndividual6;
    private String medicaidGroup6 = " ";
    private String medicaidIndividual6 = " ";
    
    private String grpEntityName1;
    private String specialty1;
    private String taxID1;
    private String effectiveDate1;
    private String grpEntityName2;
    private String specialty2;
    private String taxID2;
    private String effectiveDate2;
    private String grpEntityName3;
    private String specialty3;
    private String taxID3;
    private String effectiveDate3;
    private String grpEntityName4;
    private String specialty4;
    private String taxID4;
    private String effectiveDate4;
    private String grpEntityName5;
    private String specialty5;
    private String taxID5;
    private String effectiveDate5;
    

	private String[] cpHospitalAddress = new String[5];
	private String[] cpHospitalCity = new String[5];
	private String[] cpHospitalState = new String[5];
	private String[] cpHospitalZip = new String[5];
	 
   
    //Aug. 15 field changes
    private String caqhExplanation;
    private String chgOfficeHours ;
    private String boardCertified ;
    private String certExamDT ;
    private String newPatients;
    private String ageLimitMin;
    private String ageLimitMax;

    private String pubicTrans1;
    private String handicapAccess1;
    private String eveningHrs1;
    //2013 SSCR 13503 changes
    private String provDir1;
    private String remitSamePrim2;
    private String provDir2;
    private String remitSamePrim3;
    private String remitSamePrim4;
    private String remitSamePrim5;
    private String remitSamePrim6;

    private String provDir3;
    private String provDir4;
    private String provDir5;
    private String provDir6;
    //2013 SSCR 13503 changes end

    private String pubicTrans2;
    private String handicapAccess2;
    private String eveningHrs2;
  

    private String pubicTrans3;
    private String handicapAccess3;
    private String eveningHrs3;


    private String pubicTrans4;
    private String handicapAccess4;
    private String eveningHrs4;
    

    private String pubicTrans5;
    private String handicapAccess5;
    private String eveningHrs5;
 

    private String pubicTrans6;
    private String handicapAccess6;
    private String eveningHrs6;
    
  
	private String addPatientInfo=" ";
    private String addAreasOfExpertise=" ";
    private String ethnicOrigin =" ";
    private List patientInfo;
    private AreasOfExpertise areasOfExpertise;
    
    //  Changes for the state mandate on 02/10/10 start
    private String medicaidIndicator="";
    private String medicaidPCP="";
    private String medicaidMaxPanel="";
    private String hipMaxPanel="";
	private String medicaidSpecialist="";
    private String hipIndicator="";
    private String hipPCP="";
    private String hipSpecialist="";
    private String indServTypes = "";
    private String ssMedical="";
    private String ssDental="";
    private String ssVision="";
    private String ssOtherServType="";
    private String indivPractice="";
    private String groupPractice="";
    private String schoolBasedClinic="";
    private String tribalHealthCenter="";
    private String ruralHealthClinic="";
    private String fedQualHealthClinic="";
    private String communityHealthCenter="";
    private String deptOfHealth="";
    private String otherPractice="";
    private String radHospBased="";
    private String radFreeStandingCenter="";
    private String languagesSpoken1="";
    private String offerECI1="";
    private String offerEPSDT1="";
    private String provideADB1="";
    private String provideCSHCN1="";
    private String languagesSpoken2="";
    private String offerECI2="";
    private String offerEPSDT2="";
    private String provideADB2="";
    private String provideCSHCN2="";
    private String languagesSpoken3="";
    private String offerECI3="";
    private String offerEPSDT3="";
    private String provideADB3="";
    private String provideCSHCN3="";
    private String languagesSpoken4="";
    private String offerECI4="";
    private String offerEPSDT4="";
    private String provideADB4="";
    private String provideCSHCN4="";
    private String languagesSpoken5="";
    private String offerECI5="";
    private String offerEPSDT5="";
    private String provideADB5="";
    private String provideCSHCN5="";
    private String languagesSpoken6="";
    private String offerECI6="";
    private String offerEPSDT6="";
    private String provideADB6="";
    private String provideCSHCN6="";
    
    private String matWaiveredPrescriber1 = "";
    private String certOpioidTreat1 = "";
    private String matOpioid1 = "";
    private String counselOpioid1 = "";
    private String sudProv1 = "";
    private String resTreatCtr1 = "";
    private String matWaiveredPrescriber2 = "";
    private String certOpioidTreat2 = "";
    private String matOpioid2 = "";
    private String counselOpioid2 = "";
    private String sudProv2 = "";
    private String resTreatCtr2 = "";
    private String matWaiveredPrescriber3 = "";
    private String certOpioidTreat3 = "";
    private String matOpioid3 = "";
    private String counselOpioid3 = "";
    private String sudProv3 = "";
    private String resTreatCtr3 = "";
    private String matWaiveredPrescriber4 = "";
    private String certOpioidTreat4 = "";
    private String matOpioid4 = "";
    private String counselOpioid4 = "";
    private String sudProv4 = "";
    private String resTreatCtr4 = "";
    private String matWaiveredPrescriber5 = "";
    private String certOpioidTreat5 = "";
    private String matOpioid5 = "";
    private String counselOpioid5 = "";
    private String sudProv5 = "";
    private String resTreatCtr5 = "";
    private String matWaiveredPrescriber6 = "";
    private String certOpioidTreat6 = "";
    private String matOpioid6 = "";
    private String counselOpioid6 = "";
    private String sudProv6 = "";
    private String resTreatCtr6 = "";
    
    private String mgdCareDisenroll="";
	private String ihcpProvNo="";
	private String pmp="";
	private String pmpSpecialty ="";
	private String hospAdmitPriv ="";
	private String[] apHospitalName = new String[3];
    private String[] apHospitalAddress = new String[3];
    private String[] apHospitalCity = new String[3];
    private String[] apHospitalState = new String[3];
    private String[] apHospitalZip = new String[3];
	private String relationshipPriv ="";
	private String deliveryPriv="";
	private String ageRestriction="";
	private String[] dpHospitalName = new String[3];
    private String[] dpHospitalAddress = new String[3];
    private String[] dpHospitalCity = new String[3];
    private String[] dpHospitalState = new String[3];
    private String[] dpHospitalZip = new String[3];
	private String pmpScopeOb="";
	private String pmpScopeAll="";
	private String genderScope="";
	private String medPanelStatus ="";
	private String hipPanelStatus ="";
	private String medNbrLocations="";
	private String hipNbrLocations="";
	private String hold ="H";
	private String open ="O";
	private String medPldPanelDecrease="";
	private String hipPldPanelDecrease="";
	private String medPldPlacePanelAt="";
	private String hipPldPlacePanelAt="";
	private String medPldGrpMedicaidNo="";
	private String hipPldGrpMedicaidNo="";
	private String medPliPanelIncrease="";
	private String hipPliPanelIncrease="";
	private String medPliPlacePanelAt="";
	private String hipPliPlacePanelAt="";
	private String medPliGrpMedicaidNbr="";
	private String hipPliGrpMedicaidNbr="";
	private String medPanelHold="";
	private String hipPanelHold="";
	private String medPlrPanelHold="";
	private String hipPlrPanelHold="";
	private String medPanelHoldRemove="";
	private String hipPanelHoldRemove= "";
	private String medPlrPanelHoldRemove="";
	private String hipPlrPanelHoldRemove="";
	private String deaNo="";
	private String csrNo="";
	private String enrollAs="";
	private String enrollClinicType="";
	private String locationType="";
	private String profLiabilityCarrierName="";
	private String profLiabilityCarrierLimit="";
	private String profLiabilityPolicyNo="";
	private String profLiabilityExpDate="";
	private String malPracInsRevoke="";
 	private String underGovInvestigation = "";
   	private String expellMedPay ="";
	
	private String paPractice="";
	private String npPractice="";
	private String naPractice="";
	private String stateLicIssueDt="";
	private String stateLicExpDt="";
	//Changes for the state mandate on 02/10/10 end
	//PMF Change SSCR 13503 13/6/2012 sectionI Start
	
	private String nonPsychEval="";
	//PMF Change SSCR 13503 13/6/2012 sectionI End
	
	//PMF Section K Changes start(AC55479)
	
	private List fileList = null;
	private String fileName = "";
	private int fileSize = 0;
	private String docComment = "";
	private FileItem document;
	private String fileListSize ="";
	private String dltRowCounter ="";
	private String uploadFileName = "";
	private String uploadFileSize = "";
	private String uploadedFileComment = "";
	private String uploadDocComment = "";
	private String formUpdateAction="";
	private List<UploadDocListBean> uploadFileListDtls = null;
	
	private String uploadFileStat = "";
	
	private String provideTelehealth1 = "";
	private String provideTelehealth2 = "";
	private String provideTelehealth3 = "";
	private String provideTelehealth4 = "";
	private String provideTelehealth5 = "";
	private String provideTelehealth6 = "";
	
	/* PMF Section C, E, F, G, L Changes -- AD21239 -- Start*/
	//Sec C
    private String paSupSpec="";
    private String paSupPMP="";
    //Sec E
    private String kyMedicaidPart1;
    private String kyMedicaidId1="";
    //Sec F
    private String kyMedicaidPart2;
    private String kyMedicaidId2="";
    //Sec G
    private String kyMedicaidPart3;
    private String kyMedicaidId3="";
    
    private String kyMedicaidPart4;
    private String kyMedicaidId4="";
    
    private String kyMedicaidPart5;
    private String kyMedicaidId5="";
    
    private String kyMedicaidPart6;
    private String kyMedicaidId6="";
    //Sec L
    private String w2Comments="";
    
    public String getW2Comments()
    {
        return w2Comments;
    }
    
    public void setW2Comments(String sW2Comments)
    {
        this.w2Comments = sW2Comments;
    }
    
    public String getKyMedicaidId1() {
		return kyMedicaidId1;
	}

	public void setKyMedicaidId1(String sKyMedicaidId1)
    {
		this.kyMedicaidId1 = sKyMedicaidId1;
    }
	
	public String getKyMedicaidId2() {
		return kyMedicaidId2;
	}

	public void setKyMedicaidId2(String sKyMedicaidId2)
    {
		this.kyMedicaidId2 = sKyMedicaidId2;
    }
	
	public String getKyMedicaidId3() {
		return kyMedicaidId3;
	}

	public void setKyMedicaidId3(String sKyMedicaidId3)
    {
		this.kyMedicaidId3 = sKyMedicaidId3;
    }
	
	public String getKyMedicaidId4() {
		return kyMedicaidId4;
	}

	public void setKyMedicaidId4(String sKyMedicaidId4)
    {
		this.kyMedicaidId4 = sKyMedicaidId4;
    }
	
	public String getKyMedicaidId5() {
		return kyMedicaidId5;
	}

	public void setKyMedicaidId5(String sKyMedicaidId5)
    {
		this.kyMedicaidId5 = sKyMedicaidId5;
    }
	
	public String getKyMedicaidId6() {
		return kyMedicaidId6;
	}

	public void setKyMedicaidId6(String sKyMedicaidId6)
    {
		this.kyMedicaidId6 = sKyMedicaidId6;
    }
	
	public String getProvideTelehealth1() {
		return provideTelehealth1;
	}

	public void setProvideTelehealth1(String provideTelehealth)
    {
		this.provideTelehealth1 = provideTelehealth;
    }
	
	public String getProvideTelehealth2() {
		return provideTelehealth2;
	}

	public void setProvideTelehealth2(String provideTelehealth)
    {
		this.provideTelehealth2 = provideTelehealth;
    }
	
	public String getProvideTelehealth3() {
		return provideTelehealth3;
	}

	public void setProvideTelehealth3(String provideTelehealth)
    {
		this.provideTelehealth3 = provideTelehealth;
    }
	
	public String getProvideTelehealth4() {
		return provideTelehealth4;
	}

	public void setProvideTelehealth4(String provideTelehealth)
    {
		this.provideTelehealth4 = provideTelehealth;
    }
	
	public String getProvideTelehealth5() {
		return provideTelehealth5;
	}

	public void setProvideTelehealth5(String provideTelehealth)
    {
		this.provideTelehealth5 = provideTelehealth;
    }
	
	public String getProvideTelehealth6() {
		return provideTelehealth6;
	}

	public void setProvideTelehealth6(String provideTelehealth)
    {
		this.provideTelehealth6 = provideTelehealth;
    }
	
    public String getPaSupSpec() {
		return paSupSpec;
	}

	public void setPaSupSpec(String sPaSupSpec)
    {
       	this.paSupSpec = sPaSupSpec;
    }
    public void setPaSupPMP(String sPaSupPMP)
    {
       this.paSupPMP = sPaSupPMP;
    }

	public String getPaSupPMP() {
		return paSupPMP;
	}
	
	//Sec E
	public void setKyMedicaidPart1(String sKyMedicaidPart1) {
		this.kyMedicaidPart1 = sKyMedicaidPart1;
	}

	public String getKyMedicaidPart1() {
		return kyMedicaidPart1;
	}
	//Sec  F
	public void setKyMedicaidPart2(String sKyMedicaidPart2) {
		this.kyMedicaidPart2 = sKyMedicaidPart2;
	}

	public String getKyMedicaidPart2() {
		return kyMedicaidPart2;
	}
	//Sec G
	public void setKyMedicaidPart3(String sKyMedicaidPart3) {
		this.kyMedicaidPart3 = sKyMedicaidPart3;
	}

	public String getKyMedicaidPart3() {
		return kyMedicaidPart3;
	}
	
	public void setKyMedicaidPart4(String sKyMedicaidPart4) {
		this.kyMedicaidPart4 = sKyMedicaidPart4;
	}

	public String getKyMedicaidPart4() {
		return kyMedicaidPart4;
	}
	
	public void setKyMedicaidPart5(String sKyMedicaidPart5) {
		this.kyMedicaidPart5 = sKyMedicaidPart5;
	}

	public String getKyMedicaidPart5() {
		return kyMedicaidPart5;
	}
	
	public void setKyMedicaidPart6(String sKyMedicaidPart6) {
		this.kyMedicaidPart6 = sKyMedicaidPart6;
	}

	public String getKyMedicaidPart6() {
		return kyMedicaidPart6;
	}
	
	/* PMF Section C Changes -- AD21239 -- end*/
	public String getUploadFileStat() {
		return uploadFileStat;
	}

	public void setUploadFileStat(String uploadFileStat) {
		this.uploadFileStat = uploadFileStat;
	}

	public String getSubmitFormBtn() {
		return submitFormBtn;
	}

	public void setSubmitFormBtn(String submitFormBtn) {
		this.submitFormBtn = submitFormBtn;
	}

	public String getFormUpdateAction() {
		return formUpdateAction;
	}

	public void setFormUpdateAction(String formUpdateAction) {
		this.formUpdateAction = formUpdateAction;
	}
	public List<UploadDocListBean> getUploadFileListDtls() {
		return uploadFileListDtls;
	}

	public void setUploadFileListDtls(List<UploadDocListBean> uploadFileListDtls) {
		this.uploadFileListDtls = uploadFileListDtls;
	}

	public List getFileList() {
		return fileList;
	}

	public void setFileList(List fileList) {
		this.fileList = fileList;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public String getDocComment() {
		return docComment;
	}

	public void setDocComment(String docComment) {
		this.docComment = docComment;
	}

	public FileItem getDocument() {
		return document;
	}

	public void setDocument(FileItem document) {
		this.document = document;
	}

	public String getFileListSize() {
		return fileListSize;
	}

	public void setFileListSize(String fileListSize) {
		this.fileListSize = fileListSize;
	}

	public String getDltRowCounter() {
		return dltRowCounter;
	}

	public void setDltRowCounter(String dltRowCounter) {
		this.dltRowCounter = dltRowCounter;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadFileSize() {
		return uploadFileSize;
	}

	public void setUploadFileSize(String uploadFileSize) {
		this.uploadFileSize = uploadFileSize;
	}

	public String getUploadedFileComment() {
		return uploadedFileComment;
	}

	public void setUploadedFileComment(String uploadedFileComment) {
		this.uploadedFileComment = uploadedFileComment;
	}

	public String getUploadDocComment() {
		return uploadDocComment;
	}

	public void setUploadDocComment(String uploadDocComment) {
		this.uploadDocComment = uploadDocComment;
	}
	
	//PMF Section K Changes end(AC55479)
	
	private String[] timeZoneOfficeHrs = new String[6];
	
	public String[] getTimeZoneOfficeHrs() {
		return timeZoneOfficeHrs;
	}
	
	public void setTimeZoneOfficeHrs(String[] timeZoneOfficeHrs) {
		this.timeZoneOfficeHrs = timeZoneOfficeHrs;
	}

	public String[] getDaysOpenTimeMon() {
		return daysOpenTimeMon;
	}

	public void setDaysOpenTimeMon(String[] daysOpenTimeMon) {
		this.daysOpenTimeMon = daysOpenTimeMon;
	}

	public String[] getDaysCloseTimeMon() {
		return daysCloseTimeMon;
	}

	public void setDaysCloseTimeMon(String[] daysCloseTimeMon) {
		this.daysCloseTimeMon = daysCloseTimeMon;
	}

	public String[] getDaysOpenTimeTue() {
		return daysOpenTimeTue;
	}

	public void setDaysOpenTimeTue(String[] daysOpenTimeTue) {
		this.daysOpenTimeTue = daysOpenTimeTue;
	}

	public String[] getDaysCloseTimeTue() {
		return daysCloseTimeTue;
	}

	public void setDaysCloseTimeTue(String[] daysCloseTimeTue) {
		this.daysCloseTimeTue = daysCloseTimeTue;
	}

	public String[] getDaysOpenTimeWed() {
		return daysOpenTimeWed;
	}
	public String getProvDir1() {
		return provDir1;
	}

	public void setProvDir1(String provDir1) {
		this. provDir1 = provDir1;
	}


	public void setDaysOpenTimeWed(String[] daysOpenTimeWed) {
		this.daysOpenTimeWed = daysOpenTimeWed;
	}

	public String[] getDaysCloseTimeWed() {
		return daysCloseTimeWed;
	}

	public void setDaysCloseTimeWed(String[] daysCloseTimeWed) {
		this.daysCloseTimeWed = daysCloseTimeWed;
	}

	public String[] getDaysOpenTimeThu() {
		return daysOpenTimeThu;
	}

	public void setDaysOpenTimeThu(String[] daysOpenTimeThu) {
		this.daysOpenTimeThu = daysOpenTimeThu;
	}

	public String[] getDaysCloseTimeThu() {
		return daysCloseTimeThu;
	}

	public void setDaysCloseTimeThu(String[] daysCloseTimeThu) {
		this.daysCloseTimeThu = daysCloseTimeThu;
	}

	public String[] getDaysOpenTimeFri() {
		return daysOpenTimeFri;
	}

	public void setDaysOpenTimeFri(String[] daysOpenTimeFri) {
		this.daysOpenTimeFri = daysOpenTimeFri;
	}

	public String[] getDaysCloseTimeFri() {
		return daysCloseTimeFri;
	}

	public void setDaysCloseTimeFri(String[] daysCloseTimeFri) {
		this.daysCloseTimeFri = daysCloseTimeFri;
	}

	public String[] getDaysOpenTimeSat() {
		return daysOpenTimeSat;
	}

	public void setDaysOpenTimeSat(String[] daysOpenTimeSat) {
		this.daysOpenTimeSat = daysOpenTimeSat;
	}

	public String[] getDaysCloseTimeSat() {
		return daysCloseTimeSat;
	}

	public void setDaysCloseTimeSat(String[] daysCloseTimeSat) {
		this.daysCloseTimeSat = daysCloseTimeSat;
	}

	public String[] getDaysOpenTimeSun() {
		return daysOpenTimeSun;
	}

	public void setDaysOpenTimeSun(String[] daysOpenTimeSun) {
		this.daysOpenTimeSun = daysOpenTimeSun;
	}

	public String[] getDaysCloseTimeSun() {
		return daysCloseTimeSun;
	}

	public void setDaysCloseTimeSun(String[] daysCloseTimeSun) {
		this.daysCloseTimeSun = daysCloseTimeSun;
	}
	private String[] daysOpenTimeMon = new String[6];
	private String[] daysCloseTimeMon = new String[6];
	private String[] daysOpenTimeTue = new String[6];
	private String[] daysCloseTimeTue = new String[6];
	private String[] daysOpenTimeWed = new String[6];
	private String[] daysCloseTimeWed = new String[6];
	private String[] daysOpenTimeThu = new String[6];
	private String[] daysCloseTimeThu = new String[6];
	private String[] daysOpenTimeFri = new String[6];
	private String[] daysCloseTimeFri = new String[6];
	private String[] daysOpenTimeSat = new String[6];
	private String[] daysCloseTimeSat = new String[6];
	private String[] daysOpenTimeSun = new String[6];
	private String[] daysCloseTimeSun = new String[6];
	
	private String daysOpenMon0 = "";
	private String daysOpenTue0 = "";
	private String daysOpenWed0 = "";
	private String daysOpenThu0 = "";
	private String daysOpenFri0 = "";
	private String daysOpenSat0 = "";
	private String daysOpenSun0 = "";

	private String daysOpenMon1 = "";
	private String daysOpenTue1 = "";
	private String daysOpenWed1 = "";
	private String daysOpenThu1 = "";
	private String daysOpenFri1 = "";
	private String daysOpenSat1 = "";
	private String daysOpenSun1 = "";

	private String daysOpenMon3 = "";
	private String daysOpenTue3 = "";
	private String daysOpenWed3 = "";
	private String daysOpenThu3 = "";
	private String daysOpenFri3 = "";
	private String daysOpenSat3 = "";
	private String daysOpenSun3 = "";

	private String daysOpenMon2 = "";
	private String daysOpenTue2 = "";
	private String daysOpenWed2 = "";
	private String daysOpenThu2 = "";
	private String daysOpenFri2 = "";
	private String daysOpenSat2 = "";
	private String daysOpenSun2 = "";

	private String daysOpenMon4 = "";
	private String daysOpenTue4 = "";
	private String daysOpenWed4 = "";
	private String daysOpenThu4 = "";
	private String daysOpenFri4 = "";
	private String daysOpenSat4 = "";
	private String daysOpenSun4 = "";

	private String daysOpenMon5 = "";
	private String daysOpenTue5 = "";
	private String daysOpenWed5 = "";
	private String daysOpenThu5 = "";
	private String daysOpenFri5 = "";
	private String daysOpenSat5 = "";
	private String daysOpenSun5 = "";
	
	private boolean isOfficeHoursOk = true;
	
	public String getDaysOpenMon0() {
		return daysOpenMon0;
	}

	public void setDaysOpenMon0(String daysOpenMon0) {
		this.daysOpenMon0 = daysOpenMon0;
	}

	public String getDaysOpenTue0() {
		return daysOpenTue0;
	}

	public void setDaysOpenTue0(String daysOpenTue0) {
		this.daysOpenTue0 = daysOpenTue0;
	}

	public String getDaysOpenWed0() {
		return daysOpenWed0;
	}

	public void setDaysOpenWed0(String daysOpenWed0) {
		this.daysOpenWed0 = daysOpenWed0;
	}

	public String getDaysOpenThu0() {
		return daysOpenThu0;
	}

	public void setDaysOpenThu0(String daysOpenThu0) {
		this.daysOpenThu0 = daysOpenThu0;
	}

	public String getDaysOpenFri0() {
		return daysOpenFri0;
	}

	public void setDaysOpenFri0(String daysOpenFri0) {
		this.daysOpenFri0 = daysOpenFri0;
	}

	public String getDaysOpenSat0() {
		return daysOpenSat0;
	}

	public void setDaysOpenSat0(String daysOpenSat0) {
		this.daysOpenSat0 = daysOpenSat0;
	}

	public String getDaysOpenSun0() {
		return daysOpenSun0;
	}

	public void setDaysOpenSun0(String daysOpenSun0) {
		this.daysOpenSun0 = daysOpenSun0;
	}

	public String getDaysOpenMon1() {
		return daysOpenMon1;
	}

	public void setDaysOpenMon1(String daysOpenMon1) {
		this.daysOpenMon1 = daysOpenMon1;
	}

	public String getDaysOpenTue1() {
		return daysOpenTue1;
	}

	public void setDaysOpenTue1(String daysOpenTue1) {
		this.daysOpenTue1 = daysOpenTue1;
	}

	public String getDaysOpenWed1() {
		return daysOpenWed1;
	}

	public void setDaysOpenWed1(String daysOpenWed1) {
		this.daysOpenWed1 = daysOpenWed1;
	}

	public String getDaysOpenThu1() {
		return daysOpenThu1;
	}

	public void setDaysOpenThu1(String daysOpenThu1) {
		this.daysOpenThu1 = daysOpenThu1;
	}

	public String getDaysOpenFri1() {
		return daysOpenFri1;
	}

	public void setDaysOpenFri1(String daysOpenFri1) {
		this.daysOpenFri1 = daysOpenFri1;
	}

	public String getDaysOpenSat1() {
		return daysOpenSat1;
	}

	public void setDaysOpenSat1(String daysOpenSat1) {
		this.daysOpenSat1 = daysOpenSat1;
	}

	public String getDaysOpenSun1() {
		return daysOpenSun1;
	}

	public void setDaysOpenSun1(String daysOpenSun1) {
		this.daysOpenSun1 = daysOpenSun1;
	}

	public String getDaysOpenMon3() {
		return daysOpenMon3;
	}

	public void setDaysOpenMon3(String daysOpenMon3) {
		this.daysOpenMon3 = daysOpenMon3;
	}

	public String getDaysOpenTue3() {
		return daysOpenTue3;
	}

	public void setDaysOpenTue3(String daysOpenTue3) {
		this.daysOpenTue3 = daysOpenTue3;
	}

	public String getDaysOpenWed3() {
		return daysOpenWed3;
	}

	public void setDaysOpenWed3(String daysOpenWed3) {
		this.daysOpenWed3 = daysOpenWed3;
	}

	public String getDaysOpenThu3() {
		return daysOpenThu3;
	}

	public void setDaysOpenThu3(String daysOpenThu3) {
		this.daysOpenThu3 = daysOpenThu3;
	}

	public String getDaysOpenFri3() {
		return daysOpenFri3;
	}

	public void setDaysOpenFri3(String daysOpenFri3) {
		this.daysOpenFri3 = daysOpenFri3;
	}

	public String getDaysOpenSat3() {
		return daysOpenSat3;
	}

	public void setDaysOpenSat3(String daysOpenSat3) {
		this.daysOpenSat3 = daysOpenSat3;
	}

	public String getDaysOpenSun3() {
		return daysOpenSun3;
	}

	public void setDaysOpenSun3(String daysOpenSun3) {
		this.daysOpenSun3 = daysOpenSun3;
	}

	public String getDaysOpenMon2() {
		return daysOpenMon2;
	}

	public void setDaysOpenMon2(String daysOpenMon2) {
		this.daysOpenMon2 = daysOpenMon2;
	}

	public String getDaysOpenTue2() {
		return daysOpenTue2;
	}

	public void setDaysOpenTue2(String daysOpenTue2) {
		this.daysOpenTue2 = daysOpenTue2;
	}

	public String getDaysOpenWed2() {
		return daysOpenWed2;
	}

	public void setDaysOpenWed2(String daysOpenWed2) {
		this.daysOpenWed2 = daysOpenWed2;
	}

	public String getDaysOpenThu2() {
		return daysOpenThu2;
	}

	public void setDaysOpenThu2(String daysOpenThu2) {
		this.daysOpenThu2 = daysOpenThu2;
	}

	public String getDaysOpenFri2() {
		return daysOpenFri2;
	}

	public void setDaysOpenFri2(String daysOpenFri2) {
		this.daysOpenFri2 = daysOpenFri2;
	}

	public String getDaysOpenSat2() {
		return daysOpenSat2;
	}

	public void setDaysOpenSat2(String daysOpenSat2) {
		this.daysOpenSat2 = daysOpenSat2;
	}

	public String getDaysOpenSun2() {
		return daysOpenSun2;
	}

	public void setDaysOpenSun2(String daysOpenSun2) {
		this.daysOpenSun2 = daysOpenSun2;
	}

	public String getDaysOpenMon4() {
		return daysOpenMon4;
	}

	public void setDaysOpenMon4(String daysOpenMon4) {
		this.daysOpenMon4 = daysOpenMon4;
	}

	public String getDaysOpenTue4() {
		return daysOpenTue4;
	}

	public void setDaysOpenTue4(String daysOpenTue4) {
		this.daysOpenTue4 = daysOpenTue4;
	}

	public String getDaysOpenWed4() {
		return daysOpenWed4;
	}

	public void setDaysOpenWed4(String daysOpenWed4) {
		this.daysOpenWed4 = daysOpenWed4;
	}

	public String getDaysOpenThu4() {
		return daysOpenThu4;
	}

	public void setDaysOpenThu4(String daysOpenThu4) {
		this.daysOpenThu4 = daysOpenThu4;
	}

	public String getDaysOpenFri4() {
		return daysOpenFri4;
	}

	public void setDaysOpenFri4(String daysOpenFri4) {
		this.daysOpenFri4 = daysOpenFri4;
	}

	public String getDaysOpenSat4() {
		return daysOpenSat4;
	}

	public void setDaysOpenSat4(String daysOpenSat4) {
		this.daysOpenSat4 = daysOpenSat4;
	}

	public String getDaysOpenSun4() {
		return daysOpenSun4;
	}

	public void setDaysOpenSun4(String daysOpenSun4) {
		this.daysOpenSun4 = daysOpenSun4;
	}

	public String getDaysOpenMon5() {
		return daysOpenMon5;
	}

	public void setDaysOpenMon5(String daysOpenMon5) {
		this.daysOpenMon5 = daysOpenMon5;
	}

	public String getDaysOpenTue5() {
		return daysOpenTue5;
	}

	public void setDaysOpenTue5(String daysOpenTue5) {
		this.daysOpenTue5 = daysOpenTue5;
	}

	public String getDaysOpenWed5() {
		return daysOpenWed5;
	}

	public void setDaysOpenWed5(String daysOpenWed5) {
		this.daysOpenWed5 = daysOpenWed5;
	}

	public String getDaysOpenThu5() {
		return daysOpenThu5;
	}

	public void setDaysOpenThu5(String daysOpenThu5) {
		this.daysOpenThu5 = daysOpenThu5;
	}

	public String getDaysOpenFri5() {
		return daysOpenFri5;
	}

	public void setDaysOpenFri5(String daysOpenFri5) {
		this.daysOpenFri5 = daysOpenFri5;
	}

	public String getDaysOpenSat5() {
		return daysOpenSat5;
	}

	public void setDaysOpenSat5(String daysOpenSat5) {
		this.daysOpenSat5 = daysOpenSat5;
	}

	public String getDaysOpenSun5() {
		return daysOpenSun5;
	}

	public void setDaysOpenSun5(String daysOpenSun5) {
		this.daysOpenSun5 = daysOpenSun5;
	}

	private String hipShow;
    
    public String getHipShow() {
		return hipShow;
	}

	public void setHipShow(String hipShow) {
		this.hipShow = hipShow;
	}

	
	//PMF Change SSCR 13503 15/6/2012 Start
	private transient Vector errorMessagesVectorAll = new Vector();
	private transient Vector errorMessagesVectorA = new Vector();
	private transient Vector errorMessagesVectorB = new Vector();
	private transient Vector errorMessagesVectorC = new Vector();
	private transient Vector errorMessagesVectorD = new Vector();
	private transient Vector errorMessagesVectorE = new Vector();
	private transient Vector errorMessagesVectorF = new Vector();
	private transient Vector errorMessagesVectorG = new Vector();
	private transient Vector errorMessagesVectorH = new Vector();
	private transient Vector errorMessagesVectorI = new Vector();
	private transient Vector errorMessagesVectorJ = new Vector();
	private transient Vector errorMessagesVectorK = new Vector();
	private transient Vector errorMessagesVectorL = new Vector();
	//PMF Change SSCR 13503 15/6/2012 End
    private static final String INVALID_ADDRESS_ERROR = "Address information cannot include 'same', 'same as practice address' or 'see above' in the ";
    
    public Provider()
    {
    	taxID = " ";
        anthemPIN = " ";
        pracName1 = " ";
        soloGroup = " ";
        numberInGroup = " ";
        rapidUpdate = " ";
        effectiveDate = " ";
        addProvider = " ";
        delProvider = " ";
        addLocation = " ";
        addProvToLocation = " ";
        chgSpecialty = " ";
        chgProvName = " ";
        delLocation = " ";
        delProvFromLocation = " ";
        chgPracName = " ";
        chgPracAddress = " ";
        chgPracPhone = " ";
        chgTaxID = " ";
        chgBillName = " ";
        chgBillAddress = " ";
        chgBillPhone = " ";
        delReason = " ";
        comments = " ";
        confirmation = " ";
        //PMF 2013 SSCR 13503change
        confProvAgreement = " ";
        confW2 = " ";
        //PMF Change SSCR 13503 End
        oldTaxID = " ";
        provFnm = " ";
        provMI = " ";
        provLnm = " ";
        title = " ";
        primSpecialtyPhy = " ";
        specialtyCarePhy = " ";
        other = " ";
        //PMF Change SSCR 13503 13/6/2012 Start
        specialityInfo = " ";
        clearSelection = " ";
        medicarePartTrad = " ";
        medicareApplDt = " ";
        medicareOptOut = " ";
        medicareOptOutDt = " ";
        npSupSpec= " ";
        npSupPMP= " ";
        retailHealthClinic= " ";
        walkInDrOffice= " ";
        urgentCare= " ";
        coveringPMP= " ";
        certMidwife= " ";
        prenatelCareCoord= " ";
        tribalHealthCtr= " ";
        clinic= " ";
        specialty = " ";
        medicationAssistedTreatment = " ";
        residentialTreatmentCenter = " ";
        substanceUseDisorderAdults = " ";
        substanceUseDisorderChild = " ";
        telehealthProv = " ";
        fwdHealthCertNPI1= " ";
        fwdHealthCertNPIBilled1= " ";
        fwdHealthCertEffDt1= " ";
        fwdHealthRecertEffDt1= " ";
        fwdHealthCertNPI2= " ";
        fwdHealthCertNPIBilled2= " ";
        fwdHealthCertEffDt2= " ";
        fwdHealthRecertEffDt2= " ";
        fwdHealthCertNPI3= " ";
        fwdHealthCertNPIBilled3= " ";
        fwdHealthCertEffDt3= " ";
        fwdHealthRecertEffDt3= " ";
       /* billingAndRendering = " ";
        renderingProvider = " ";
        groupProvider = " ";*/
        //PMF Change SSCR 13503 13/6/2012 End
        provSSN = " ";
        upinNumber = " ";
        profLicenseNumber = " ";
        caqhIDNumber = " ";
        provDOB = " ";
        provGender = " ";
        pracOfficeAddress1 = " ";
        pracOfficeCity1 = " ";
        pracOfficeState1 = " ";
        pracOfficeZip1 = " ";
        pracOfficeCounty1 = " ";
        pracOfficePhone1 = " ";
        pracOfficeFax1 = " ";
        pracOfficeEmail1 = " ";
        billAddressSame1 = " ";
        pracBillContactName1 = " ";
        pracBillAddress1 = " ";
        pracBillCity1 = " ";
        pracBillState1 = " ";
        pracBillZip1 = " ";
        pracBillCounty1 = " ";
        pracBillPhone1 = " ";
        pracBillFax1 = " ";
        pracBillContactEmail1 = " ";
        billMedicareGroup1 = " ";
        billMedicareIndividual1 = " ";
        pracName2 = " ";
        pracOfficeAddress2 = " ";
        pracOfficeCity2 = " ";
        pracOfficeState2 = " ";
        pracOfficeZip2 = " ";
        pracOfficeCounty2 = " ";
        pracOfficePhone2 = " ";
        pracOfficeFax2 = " ";
        pracOfficeEmail2 = " ";
        billAddressSame2 = " ";
        pracBillContactName2 = " ";
        pracBillAddress2 = " ";
        pracBillCity2 = " ";
        pracBillState2 = " ";
        pracBillZip2 = " ";
        pracBillCounty2 = " ";
        pracBillPhone2 = " ";
        pracBillFax2 = " ";
        pracBillContactEmail2= " ";
        billMedicareGroup2 = " ";
        billMedicareIndividual2 = " ";
        pracName3 = " ";
        pracOfficeAddress3 = " ";
        pracOfficeCity3 = " ";
        pracOfficeState3 = " ";
        pracOfficeZip3 = " ";
        pracOfficeCounty3 = " ";
        pracOfficePhone3 = " ";
        pracOfficeFax3 = " ";
        pracOfficeEmail3 = " ";
        billAddressSame3 = " ";
        pracBillContactName3 =" ";
        pracBillAddress3 = " ";
        pracBillCity3 = " ";
        pracBillState3 = " ";
        pracBillZip3 = " ";
        pracBillCounty3 = " ";
        pracBillPhone3 = " ";
        pracBillFax3 = " ";
        pracBillContactEmail3 =" ";
        billMedicareGroup3 = " ";
        billMedicareIndividual3 = " ";
        pracName4 = " ";
        pracOfficeAddress4 = " ";
        pracOfficeCity4 = " ";
        pracOfficeState4 = " ";
        pracOfficeZip4 = " ";
        pracOfficeCounty4 = " ";
        pracOfficePhone4 = " ";
        pracOfficeFax4 = " ";
        pracOfficeEmail4 = " ";
        billAddressSame4 = " ";
        pracBillContactName4 = " ";
        pracBillAddress4 = " ";
        pracBillCity4 = " ";
        pracBillState4 = " ";
        pracBillZip4 = " ";
        pracBillCounty4 = " ";
        pracBillPhone4 = " ";
        pracBillFax4 = " ";
        pracBillContactEmail4 = " ";
        billMedicareGroup4 = " ";
        billMedicareIndividual4 = " ";
        pracName5 = " ";
        pracOfficeAddress5 = " ";
        pracOfficeCity5 = " ";
        pracOfficeState5 = " ";
        pracOfficeZip5 = " ";
        pracOfficeCounty5 = " ";
        pracOfficePhone5 = " ";
        pracOfficeFax5 = " ";
        pracOfficeEmail5 = " ";
        billAddressSame5 = " ";
        pracBillContactName5 = " ";
        pracBillAddress5 = " ";
        pracBillCity5 = " ";
        pracBillState5 = " ";
        pracBillZip5 = " ";
        pracBillCounty5 = " ";
        pracBillPhone5 = " ";
        pracBillFax5 = " ";
        pracBillContactEmail5 = " ";
        billMedicareGroup5 = " ";
        billMedicareIndividual5 = " ";
        pracName6 = " ";
        pracOfficeAddress6 = " ";
        pracOfficeCity6 = " ";
        pracOfficeState6 = " ";
        pracOfficeZip6 = " ";
        pracOfficeCounty6 = " ";
        pracOfficePhone6 = " ";
        pracOfficeFax6 = " ";
        pracOfficeEmail6 = " ";
        billAddressSame6 = " ";
        pracBillContactName6 =" ";
        pracBillAddress6 = " ";
        pracBillCity6 = " ";
        pracBillState6 = " ";
        pracBillZip6 = " ";
        pracBillCounty6 = " ";
        pracBillPhone6 = " ";
        pracBillFax6 = " ";
        pracBillContactEmail6 = " ";
        billMedicareGroup6 = " ";
        billMedicareIndividual6 = " ";
        grpEntityName1 = " ";
        specialty1 = " ";
        taxID1 = " ";
        effectiveDate1 = " ";
        grpEntityName2 = " ";
        specialty2 = " ";
        taxID2 = " ";
        effectiveDate2 = " ";
        grpEntityName3 = " ";
        specialty3 = " ";
        taxID3 = " ";
        effectiveDate3 = " ";
        grpEntityName4 = " ";
        specialty4 = " ";
        taxID4 = " ";
        effectiveDate4 = " ";
        grpEntityName5 = " ";
        specialty5 = " ";
        taxID5 = " ";
        effectiveDate5 = " ";

       /* grpEntityAddress1 = " ";
        grpEntityCity1 = " ";
        grpEntityState1 =" " ;
        grpEntityZip1 = " ";
        grpEntityAddress2 = " ";
        grpEntityCity2 = " ";
        grpEntityState2 =" " ;
        grpEntityZip2 = " ";
        grpEntityAddress3 = " ";
        grpEntityCity3 = " ";
        grpEntityState3 =" " ;
        grpEntityZip3 = " ";
        grpEntityAddress4 = " ";
        grpEntityCity4 = " ";
        grpEntityState4 =" " ;
        grpEntityZip4 = " ";
        grpEntityAddress5 = " ";
        grpEntityCity5 = " ";
        grpEntityState5 =" " ;
        grpEntityZip5 = " ";*/
       // errorMessagesVector.clear();
        errorMessagesVectorAll.clear();
        errorMessagesVectorA.clear();
        errorMessagesVectorB.clear();
        errorMessagesVectorC.clear();
        errorMessagesVectorD.clear();
        errorMessagesVectorE.clear();
        errorMessagesVectorF.clear();
        errorMessagesVectorG.clear();
        errorMessagesVectorH.clear();
        errorMessagesVectorI.clear();
        errorMessagesVectorJ.clear();
        errorMessagesVectorK.clear();
        errorMessagesVectorL.clear();
        areasOfExpertise = new AreasOfExpertise();
        patientInfo = new ArrayList(); 
        
        //  Changes for the state mandate on 02/10/10 start
        medicaidIndicator = " ";
        medicaidPCP = " ";
        medicaidMaxPanel = " ";
        hipMaxPanel = " ";
        medicaidSpecialist = " ";
        hipIndicator = " ";
        hipPCP = " ";
        hipSpecialist = " ";
        indServTypes = " ";
        ssMedical = " ";
        ssDental = " ";
        ssVision = " ";
        ssOtherServType = " ";
        indivPractice = " ";
        groupPractice = " ";
        schoolBasedClinic = " ";
        tribalHealthCenter = " ";
        ruralHealthClinic = " ";
        fedQualHealthClinic = " ";
        communityHealthCenter = " ";
        deptOfHealth = " ";
        otherPractice = " ";
        radHospBased = " ";
        radFreeStandingCenter = " ";
        languagesSpoken1 = " ";
        offerECI1 = " ";
        offerEPSDT1 = " ";
        provideADB1 = " ";
        provideCSHCN1 = " ";
        languagesSpoken2 = " ";
        offerECI2 = " ";
        offerEPSDT2 = " ";
        provideADB2 = " ";
        provideCSHCN2 = " ";
        languagesSpoken3 = " ";
        offerECI3 = " ";
        offerEPSDT3 = " ";
        provideADB3 = " ";
        provideCSHCN3 = " ";
        languagesSpoken4 = " ";
        offerECI4 = " ";
        offerEPSDT4 = " ";
        provideADB4 = " ";
        provideCSHCN4 = " ";
        languagesSpoken5 = " ";
        offerECI5 = " ";
        offerEPSDT5 = " ";
        provideADB5 = " ";
        provideCSHCN5 = " ";
        languagesSpoken6 = " ";
        offerECI6 = " ";
        offerEPSDT6 = " ";
        provideADB6 = " ";
        provideCSHCN6 = " ";
        //  Changes for the state mandate on 02/10/10 end
        
        matWaiveredPrescriber1 = " ";
        certOpioidTreat1 = " ";
        matOpioid1 = " ";
        counselOpioid1 = " ";
        sudProv1 = " ";
        resTreatCtr1 = " ";
        matWaiveredPrescriber2 = " ";
        certOpioidTreat2 = " ";
        matOpioid2 = " ";
        counselOpioid2 = " ";
        sudProv2 = " ";
        resTreatCtr2 = " ";
        matWaiveredPrescriber3 = " ";
        certOpioidTreat3 = " ";
        matOpioid3 = " ";
        counselOpioid3 = " ";
        sudProv3 = " ";
        resTreatCtr3 = " ";
        matWaiveredPrescriber4 = " ";
        certOpioidTreat4 = " ";
        matOpioid4 = " ";
        counselOpioid4 = " ";
        sudProv4 = " ";
        resTreatCtr4 = " ";
        matWaiveredPrescriber5 = " ";
        certOpioidTreat5 = " ";
        matOpioid5 = " ";
        counselOpioid5 = " ";
        sudProv5 = " ";
        resTreatCtr5 = "";
        matWaiveredPrescriber6 = " ";
        certOpioidTreat6 = " ";
        matOpioid6 = " ";
        counselOpioid6 = " ";
        sudProv6 = " ";
        resTreatCtr6 = " ";
        
        provideTelehealth1 = " ";
        provideTelehealth2 = " ";
        provideTelehealth3 = " ";
        provideTelehealth4 = " ";
        provideTelehealth5 = " ";
        provideTelehealth6 = " ";
        
        mgdCareDisenroll=" ";
        ihcpProvNo=" ";
        pmp=" ";
        pmpSpecialty =" ";
        hospAdmitPriv =" ";
        relationshipPriv =" ";
        deliveryPriv=" ";
        pmpScopeOb=" ";
        pmpScopeAll=" ";
        genderScope=" ";
        medPanelStatus =" ";
        hipPanelStatus =" ";
        medNbrLocations=" ";
        hipNbrLocations=" ";
        medPldPanelDecrease=" ";
        hipPldPanelDecrease=" ";
        medPldPlacePanelAt=" ";
        hipPldPlacePanelAt=" ";
        medPldGrpMedicaidNo=" ";
        hipPldGrpMedicaidNo=" ";
        medPliPanelIncrease=" ";
        hipPliPanelIncrease=" ";
        medPliPlacePanelAt=" ";
        hipPliPlacePanelAt=" ";
        medPliGrpMedicaidNbr=" ";
        hipPliGrpMedicaidNbr=" ";
        medPanelHold=" ";
        hipPanelHold=" ";
        medPlrPanelHold = " ";
        hipPlrPanelHold = " ";
        medPanelHoldRemove = " ";
        hipPanelHoldRemove= " ";
        medPlrPanelHoldRemove=" ";
        hipPlrPanelHoldRemove=" ";
        deaNo=" ";
        csrNo=" ";
        enrollAs=" ";
        enrollClinicType=" ";
        locationType=" ";
        profLiabilityCarrierName=" ";
        profLiabilityCarrierLimit=" ";
        profLiabilityPolicyNo=" ";
        profLiabilityExpDate=" ";
        malPracInsRevoke= " ";
        underGovInvestigation =" ";
        expellMedPay = " ";
        ageRestriction="";
    	paPractice=" ";
    	npPractice=" ";
    	naPractice=" ";
    	stateLicIssueDt=" ";
    	stateLicExpDt=" ";
    	//2013 SSCR 13503
    	remitSamePrim2 = " ";
    	remitSamePrim3 = " ";
    	remitSamePrim4 = " ";
    	remitSamePrim5 = " ";
    	remitSamePrim6 = " ";

    	//PMF Change SSCR 13503 13/6/2012 Start
    	nonPsychEval = " ";
    	//PMF Change SSCR 13503 13/6/2012 End
    	
    	/* PMF Section C Changes -- AD21239 -- Start*/
    	//Sec C
    	paSupSpec = " ";
    	paSupPMP = " ";
    	//Sec E
        kyMedicaidId1=" ";
        //Sec F
        kyMedicaidId2=" ";
        //Sec G
        kyMedicaidId3=" ";
        kyMedicaidId4=" ";
        kyMedicaidId5=" ";
        kyMedicaidId6=" ";
        //Sec L
    	w2Comments = " ";
    	
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
        
        hipShow = "";
        
        
    }
    
    public String getNaPractice() {
		return naPractice;
	}

	public void setNaPractice(String naPractice) {
		this.naPractice = naPractice;
	}
    
    public String getPaPractice() {
		return paPractice;
	}

	public void setPaPractice(String paPractice) {
		this.paPractice = paPractice;
	}

	public String getNpPractice() {
		return npPractice;
	}

	public void setNpPractice(String npPractice) {
		this.npPractice = npPractice;
	}

	public String getStateLicIssueDt() {
		return stateLicIssueDt;
	}

	public void setStateLicIssueDt(String stateLicIssueDt) {
		this.stateLicIssueDt = stateLicIssueDt;
	}

	public String getStateLicExpDt() {
		return stateLicExpDt;
	}
	//PMF Change SSCR 13503 13/6/2012 Start
	public String getNonPsychEval() {
			return nonPsychEval;
		}
	//PMF Change SSCR 13503 13/6/2012 End
	public void setStateLicExpDt(String stateLicExpDt) {
		this.stateLicExpDt = stateLicExpDt;
	}
	//PMF Change SSCR 13503 13/6/2012 Start
	public void setNonPsychEval(String nonPsychEval) {
		this.nonPsychEval = nonPsychEval;
	}
	//PMF Change SSCR 13503 13/6/2012 End
	public String getAgeRestriction() {
		return ageRestriction;
	}

	public void setAgeRestriction(String ageRestriction) {
		this.ageRestriction = ageRestriction;
	}
	
    public String getProfLiabilityCarrierName() {
		return profLiabilityCarrierName;
	}

	public void setProfLiabilityCarrierName(String profLiabilityCarrierName) {
		this.profLiabilityCarrierName = profLiabilityCarrierName;
	}

	public String getProfLiabilityCarrierLimit() {
		return profLiabilityCarrierLimit;
	}

	public void setProfLiabilityCarrierLimit(String profLiabilityCarrierLimit) {
		this.profLiabilityCarrierLimit = profLiabilityCarrierLimit;
	}

	public String getProfLiabilityPolicyNo() {
		return profLiabilityPolicyNo;
	}

	public void setProfLiabilityPolicyNo(String profLiabilityPolicyNo) {
		this.profLiabilityPolicyNo = profLiabilityPolicyNo;
	}

	public String getProfLiabilityExpDate() {
		return profLiabilityExpDate;
	}

	public void setProfLiabilityExpDate(String profLiabilityExpDate) {
		this.profLiabilityExpDate = profLiabilityExpDate;
	}
	
	public String getMalPracInsRevoke() {
		return malPracInsRevoke;
	}

	public void setMalPracInsRevoke(String malPracInsRevoke) {
		this.malPracInsRevoke = malPracInsRevoke;
	}

	public String getUnderGovInvestigation() {
		return underGovInvestigation;
	}

	public void setUnderGovInvestigation(String underGovInvestigation) {
		this.underGovInvestigation = underGovInvestigation;
	}

	public String getExpellMedPay() {
		return expellMedPay;
	}

	public void setExpellMedPay(String expellMedPay) {
		this.expellMedPay = expellMedPay;
	}
    
    public String getMgdCareDisenroll() {
		return mgdCareDisenroll;
	}

	public void setMgdCareDisenroll(String mgdCareDisenroll) {
		this.mgdCareDisenroll = mgdCareDisenroll;
	}

	public String getIhcpProvNo() {
		return ihcpProvNo;
	}

	public void setIhcpProvNo(String ihcpProvNo) {
		this.ihcpProvNo = ihcpProvNo;
	}

	public String getPmp() {
		return pmp;
	}

	public void setPmp(String pmp) {
		this.pmp = pmp;
	}

	public String getPmpSpecialty() {
		return pmpSpecialty;
	}

	public void setPmpSpecialty(String pmpSpecialty) {
		this.pmpSpecialty = pmpSpecialty;
	}

	public String getHospAdmitPriv() {
		return hospAdmitPriv;
	}

	public void setHospAdmitPriv(String hospAdmitPriv) {
		this.hospAdmitPriv = hospAdmitPriv;
	}

	public String getRelationshipPriv() {
		return relationshipPriv;
	}

	public void setRelationshipPriv(String relationshipPriv) {
		this.relationshipPriv = relationshipPriv;
	}

	public String getDeliveryPriv() {
		return deliveryPriv;
	}

	public void setDeliveryPriv(String deliveryPriv) {
		this.deliveryPriv = deliveryPriv;
	}

	public String getPmpScopeOb() {
		return pmpScopeOb;
	}

	public void setPmpScopeOb(String pmpScopeOb) {
		this.pmpScopeOb = pmpScopeOb;
	}

	public String getPmpScopeAll() {
		return pmpScopeAll;
	}

	public void setPmpScopeAll(String pmpScopeAll) {
		this.pmpScopeAll = pmpScopeAll;
	}

	public String getGenderScope() {
		return genderScope;
	}

	public void setGenderScope(String genderScope) {
		this.genderScope = genderScope;
	}

	public String getMedPanelStatus() {
		return medPanelStatus;
	}

	public void setMedPanelStatus(String medPanelStatus) {
		this.medPanelStatus = medPanelStatus;
	}

	public String getHipPanelStatus() {
		return hipPanelStatus;
	}

	public void setHipPanelStatus(String hipPanelStatus) {
		this.hipPanelStatus = hipPanelStatus;
	}

	public String getMedNbrLocations() {
		return medNbrLocations;
	}

	public void setMedNbrLocations(String medNbrLocations) {
		this.medNbrLocations = medNbrLocations;
	}

	public String getHipNbrLocations() {
		return hipNbrLocations;
	}

	public void setHipNbrLocations(String hipNbrLocations) {
		this.hipNbrLocations = hipNbrLocations;
	}

	public String getHold() {
		return hold;
	}

	public void setHold(String hold) {
		this.hold = hold;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getMedPldPanelDecrease() {
		return medPldPanelDecrease;
	}

	public void setMedPldPanelDecrease(String medPldPanelDecrease) {
		this.medPldPanelDecrease = medPldPanelDecrease;
	}

	public String getHipPldPanelDecrease() {
		return hipPldPanelDecrease;
	}

	public void setHipPldPanelDecrease(String hipPldPanelDecrease) {
		this.hipPldPanelDecrease = hipPldPanelDecrease;
	}

	public String getMedPldPlacePanelAt() {
		return medPldPlacePanelAt;
	}

	public void setMedPldPlacePanelAt(String medPldPlacePanelAt) {
		this.medPldPlacePanelAt = medPldPlacePanelAt;
	}

	public String getHipPldPlacePanelAt() {
		return hipPldPlacePanelAt;
	}

	public void setHipPldPlacePanelAt(String hipPldPlacePanelAt) {
		this.hipPldPlacePanelAt = hipPldPlacePanelAt;
	}

	public String getMedPldGrpMedicaidNo() {
		return medPldGrpMedicaidNo;
	}

	public void setMedPldGrpMedicaidNo(String medPldGrpMedicaidNo) {
		this.medPldGrpMedicaidNo = medPldGrpMedicaidNo;
	}

	public String getHipPldGrpMedicaidNo() {
		return hipPldGrpMedicaidNo;
	}

	public void setHipPldGrpMedicaidNo(String hipPldGrpMedicaidNo) {
		this.hipPldGrpMedicaidNo = hipPldGrpMedicaidNo;
	}

	public String getMedPliPanelIncrease() {
		return medPliPanelIncrease;
	}

	public void setMedPliPanelIncrease(String medPliPanelIncrease) {
		this.medPliPanelIncrease = medPliPanelIncrease;
	}

	public String getHipPliPanelIncrease() {
		return hipPliPanelIncrease;
	}

	public void setHipPliPanelIncrease(String hipPliPanelIncrease) {
		this.hipPliPanelIncrease = hipPliPanelIncrease;
	}

	public String getMedPliPlacePanelAt() {
		return medPliPlacePanelAt;
	}

	public void setMedPliPlacePanelAt(String medPliPlacePanelAt) {
		this.medPliPlacePanelAt = medPliPlacePanelAt;
	}

	public String getHipPliPlacePanelAt() {
		return hipPliPlacePanelAt;
	}

	public void setHipPliPlacePanelAt(String hipPliPlacePanelAt) {
		this.hipPliPlacePanelAt = hipPliPlacePanelAt;
	}

	public String getMedPliGrpMedicaidNbr() {
		return medPliGrpMedicaidNbr;
	}

	public void setMedPliGrpMedicaidNbr(String medPliGrpMedicaidNbr) {
		this.medPliGrpMedicaidNbr = medPliGrpMedicaidNbr;
	}

	public String getHipPliGrpMedicaidNbr() {
		return hipPliGrpMedicaidNbr;
	}

	public void setHipPliGrpMedicaidNbr(String hipPliGrpMedicaidNbr) {
		this.hipPliGrpMedicaidNbr = hipPliGrpMedicaidNbr;
	}

	public String getMedPanelHold() {
		return medPanelHold;
	}

	public void setMedPanelHold(String medPanelHold) {
		this.medPanelHold = medPanelHold;
	}

	public String getHipPanelHold() {
		return hipPanelHold;
	}

	public void setHipPanelHold(String hipPanelHold) {
		this.hipPanelHold = hipPanelHold;
	}

	public String getMedPlrPanelHoldRemove() {
		return medPlrPanelHoldRemove;
	}

	public void setMedPlrPanelHoldRemove(String medPlrPanelHoldRemove) {
		this.medPlrPanelHoldRemove = medPlrPanelHoldRemove;
	}

	public String getHipPlrPanelHoldRemove() {
		return hipPlrPanelHoldRemove;
	}

	public void setHipPlrPanelHoldRemove(String hipPlrPanelHoldRemove) {
		this.hipPlrPanelHoldRemove = hipPlrPanelHoldRemove;
	}

	/**
	 * @return the medPlrPanelHold
	 */
	public String getMedPlrPanelHold() {
		return medPlrPanelHold;
	}

	/**
	 * @param medPlrPanelHold the medPlrPanelHold to set
	 */
	public void setMedPlrPanelHold(String medPlrPanelHold) {
		this.medPlrPanelHold = medPlrPanelHold;
	}

	/**
	 * @return the hipPlrPanelHold
	 */
	public String getHipPlrPanelHold() {
		return hipPlrPanelHold;
	}

	/**
	 * @param hipPlrPanelHold the hipPlrPanelHold to set
	 */
	public void setHipPlrPanelHold(String hipPlrPanelHold) {
		this.hipPlrPanelHold = hipPlrPanelHold;
	}

	/**
	 * @return the medPanelHoldRemove
	 */
	public String getMedPanelHoldRemove() {
		return medPanelHoldRemove;
	}

	/**
	 * @param medPanelHoldRemove the medPanelHoldRemove to set
	 */
	public void setMedPanelHoldRemove(String medPanelHoldRemove) {
		this.medPanelHoldRemove = medPanelHoldRemove;
	}

	/**
	 * @return the hipPanelHoldRemove
	 */
	public String getHipPanelHoldRemove() {
		return hipPanelHoldRemove;
	}

	/**
	 * @param hipPanelHoldRemove the hipPanelHoldRemove to set
	 */
	public void setHipPanelHoldRemove(String hipPanelHoldRemove) {
		this.hipPanelHoldRemove = hipPanelHoldRemove;
	}

	public String getDeaNo() {
		return deaNo;
	}

	public void setDeaNo(String deaNo) {
		this.deaNo = deaNo;
	}

	public String getCsrNo() {
		return csrNo;
	}

	public void setCsrNo(String crsNo) {
		this.csrNo = crsNo;
	}

	public String getEnrollAs() {
		return enrollAs;
	}

	public void setEnrollAs(String enrollAs) {
		this.enrollAs = enrollAs;
	}

	public String getEnrollClinicType() {
		return enrollClinicType;
	}

	public void setEnrollClinicType(String enrollClinicType) {
		this.enrollClinicType = enrollClinicType;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
    

	/**
	 * @return the indianaHospitalName
	 */
	public String[] getApHospitalName() {
		return apHospitalName;
	}

	/**
	 * @param indianaHospitalName the indianaHospitalName to set
	 */
	public void setApHospitalName(String[] indianaHospitalName) {
		this.apHospitalName = indianaHospitalName;
	}

	/**
	 * @return the indianaHospitalAddress
	 */
	public String[] getApHospitalAddress() {
			
		return apHospitalAddress;
	}

	/**
	 * @param indianaHospitalAddress the indianaHospitalAddress to set
	 */
	public void setApHospitalAddress(String[] indianaHospitalAddress) {
		this.apHospitalAddress = indianaHospitalAddress;
	}

	/**
	 * @return the indianaHospitalCity
	 */
	public String[] getApHospitalCity() {
		return apHospitalCity;
	}

	/**
	 * @param indianaHospitalCity the indianaHospitalCity to set
	 */
	public void setApHospitalCity(String[] indianaHospitalCity) {
		this.apHospitalCity = indianaHospitalCity;
	}

	/**
	 * @return the indianaHospitalState
	 */
	public String[] getApHospitalState() {
		return apHospitalState;
	}

	/**
	 * @param indianaHospitalState the indianaHospitalState to set
	 */
	public void setApHospitalState(String[] indianaHospitalState) {
		this.apHospitalState = indianaHospitalState;
	}

	/**
	 * @return the indianaHospitalZip
	 */
	public String[] getApHospitalZip() {
		return apHospitalZip;
	}

	/**
	 * @param indianaHospitalZip the indianaHospitalZip to set
	 */
	public void setApHospitalZip(String[] indianaHospitalZip) {
		this.apHospitalZip = indianaHospitalZip;
	}

	/**
	 * @return the dpHospitalName
	 */
	public String[] getDpHospitalName() {
		return dpHospitalName;
	}


	/**
	 * @param dpHospitalName the dpHospitalName to set
	 */
	public void setDpHospitalName(String[] dpHospitalName) {
		this.dpHospitalName = dpHospitalName;
	}


	/**
	 * @return the dpHospitalAddress
	 */
	public String[] getDpHospitalAddress() {
		return dpHospitalAddress;
	}


	/**
	 * @param dpHospitalAddress the dpHospitalAddress to set
	 */
	public void setDpHospitalAddress(String[] dpHospitalAddress) {
		this.dpHospitalAddress = dpHospitalAddress;
	}


	/**
	 * @return the dpHospitalCity
	 */
	public String[] getDpHospitalCity() {
		return dpHospitalCity;
	}


	/**
	 * @param dpHospitalCity the dpHospitalCity to set
	 */
	public void setDpHospitalCity(String[] dpHospitalCity) {
		this.dpHospitalCity = dpHospitalCity;
	}


	/**
	 * @return the dpHospitalState
	 */
	public String[] getDpHospitalState() {
		return dpHospitalState;
	}


	/**
	 * @param dpHospitalState the dpHospitalState to set
	 */
	public void setDpHospitalState(String[] dpHospitalState) {
		this.dpHospitalState = dpHospitalState;
	}


	/**
	 * @return the dpHospitalZip
	 */
	public String[] getDpHospitalZip() {
		return dpHospitalZip;
	}
	
	/**
	 * @param dpHospitalZip the dpHospitalZip to set
	 */
	public void setDpHospitalZip(String[] dpHospitalZip) {
		this.dpHospitalZip = dpHospitalZip;
	}
	
	public String[] getCpHospitalAddress() {
		return cpHospitalAddress;
	}

	public void setCpHospitalAddress(String[] cpHospitalAddress) {
		this.cpHospitalAddress = cpHospitalAddress;
	}

	public String[] getCpHospitalCity() {
		return cpHospitalCity;
	}

	public void setCpHospitalCity(String[] cpHospitalCity) {
		this.cpHospitalCity = cpHospitalCity;
	}

	public String[] getCpHospitalState() {
		return cpHospitalState;
	}

	public void setCpHospitalState(String[] cpHospitalState) {
		this.cpHospitalState = cpHospitalState;
	}

	public String[] getCpHospitalZip() {
		return cpHospitalZip;
	}

	public void setCpHospitalZip(String[] cpHospitalZip) {
		this.cpHospitalZip = cpHospitalZip;
	}


	public String getAgeLimitMax() {
		return ageLimitMax;
	}

	public void setAgeLimitMax(String ageLimitMax) {
		this.ageLimitMax = ageLimitMax;		
	}

	public String getAgeLimitMin() {
		return ageLimitMin;
	}

	public void setAgeLimitMin(String ageLimitMin) {
		this.ageLimitMin = ageLimitMin;				
	}

	public String getBoardCertified() {
		return boardCertified;
	}

	public void setBoardCertified(String boardCertified) {
		this.boardCertified = boardCertified;
	}

	public String getCaqhExplanation() {
		return caqhExplanation;
	}

	public void setCaqhExplanation(String caqhExplanation) {
		
		if(caqhExplanation!=null){
			this.caqhExplanation = caqhExplanation;
		}
		else
		{
			this.caqhExplanation = " ";
		}
		
	}

	public String getCertExamDT() {
		return certExamDT;
	}

	public void setCertExamDT(String certExamDT) {
		this.certExamDT = certExamDT;
	}

	public String getChgOfficeHours() {
		return chgOfficeHours;
	}

	public void setChgOfficeHours(String chgOfficeHours) {
		this.chgOfficeHours = chgOfficeHours;
	}

	public String getEveningHrs1() {
		return eveningHrs1;
	}

	public void setEveningHrs1(String eveningHrs1) {
		this.eveningHrs1 = eveningHrs1;
	}
	//2013 SSCR 13503 changes 
	public String getRemitSamePrim2() {
		return remitSamePrim2;
	}

	public void setRemitSamePrim2(String remitSamePrim2) {
		this. remitSamePrim2= remitSamePrim2;
	}
	public String getRemitSamePrim3() {
		return remitSamePrim3;
	}

	public void setRemitSamePrim3(String remitSamePrim3) {
		this. remitSamePrim3= remitSamePrim3;
	}
	public String getRemitSamePrim4() {
		return remitSamePrim4;
	}

	public void setRemitSamePrim4(String remitSamePrim4) {
		this. remitSamePrim4= remitSamePrim4;
	}
	public String getRemitSamePrim5() {
		return remitSamePrim5;
	}

	public void setRemitSamePrim5(String remitSamePrim5) {
		this. remitSamePrim5= remitSamePrim5;
	}
	public String getRemitSamePrim6() {
		return remitSamePrim6;
	}

	public void setRemitSamePrim6(String remitSamePrim6) {
		this. remitSamePrim6= remitSamePrim6;
	}

	public String getProvDir2() {
		return provDir2;
	}

	public void setProvDir2(String provDir2) {
		this. provDir2 = provDir2;
	}
	public String getProvDir3() {
		return provDir3;
	}

	public void setProvDir3(String provDir3) {
		this. provDir3 = provDir3;
	}
	public String getProvDir4() {
		return provDir4;
	}

	public void setProvDir4(String provDir4) {
		this. provDir4 = provDir4;
	}
	public String getProvDir5() {
		return provDir5;
	}

	public void setProvDir5(String provDir5) {
		this. provDir5 = provDir5;
	}
	public String getProvDir6() {
		return provDir6;
	}

	public void setProvDir6(String provDir6) {
		this. provDir6 = provDir6;
	}

	public String getEveningHrs2() {
		return eveningHrs2;
	}

	public void setEveningHrs2(String eveningHrs2) {
		this.eveningHrs2 = eveningHrs2;
	}

	public String getEveningHrs3() {
		return eveningHrs3;
	}

	public void setEveningHrs3(String eveningHrs3) {
		this.eveningHrs3 = eveningHrs3;
	}

	public String getEveningHrs4() {
		return eveningHrs4;
	}

	public void setEveningHrs4(String eveningHrs4) {
		this.eveningHrs4 = eveningHrs4;
	}

	public String getEveningHrs5() {
		return eveningHrs5;
	}

	public void setEveningHrs5(String eveningHrs5) {
		this.eveningHrs5 = eveningHrs5;
	}

	public String getEveningHrs6() {
		return eveningHrs6;
	}

	public void setEveningHrs6(String eveningHrs6) {
		this.eveningHrs6 = eveningHrs6;
	}

	public String getHandicapAccess1() {
		return handicapAccess1;
	}

	public void setHandicapAccess1(String handicapAccess1) {
		this.handicapAccess1 = handicapAccess1;
	}

	public String getHandicapAccess2() {
		return handicapAccess2;
	}

	public void setHandicapAccess2(String handicapAccess2) {
		this.handicapAccess2 = handicapAccess2;
	}

	public String getHandicapAccess3() {
		return handicapAccess3;
	}

	public void setHandicapAccess3(String handicapAccess3) {
		this.handicapAccess3 = handicapAccess3;
	}

	public String getHandicapAccess4() {
		return handicapAccess4;
	}

	public void setHandicapAccess4(String handicapAccess4) {
		this.handicapAccess4 = handicapAccess4;
	}

	public String getHandicapAccess5() {
		return handicapAccess5;
	}

	public void setHandicapAccess5(String handicapAccess5) {
		this.handicapAccess5 = handicapAccess5;
	}

	public String getHandicapAccess6() {
		return handicapAccess6;
	}

	public void setHandicapAccess6(String handicapAccess6) {
		this.handicapAccess6 = handicapAccess6;
	}

	public String getNewPatients() {
		return newPatients;
	}

	public void setNewPatients(String newPatients) {
		this.newPatients = newPatients;
	}

	public String getPubicTrans1() {
		return pubicTrans1;
	}

	public void setPubicTrans1(String pubicTrans1) {
		this.pubicTrans1 = pubicTrans1;
	}

	public String getPubicTrans2() {
		return pubicTrans2;
	}

	public void setPubicTrans2(String pubicTrans2) {
		this.pubicTrans2 = pubicTrans2;
	}

	public String getPubicTrans3() {
		return pubicTrans3;
	}

	public void setPubicTrans3(String pubicTrans3) {
		this.pubicTrans3 = pubicTrans3;
	}

	public String getPubicTrans4() {
		return pubicTrans4;
	}

	public void setPubicTrans4(String pubicTrans4) {
		this.pubicTrans4 = pubicTrans4;
	}

	public String getPubicTrans5() {
		return pubicTrans5;
	}

	public void setPubicTrans5(String pubicTrans5) {
		this.pubicTrans5 = pubicTrans5;
	}

	public String getPubicTrans6() {
		return pubicTrans6;
	}

	public void setPubicTrans6(String pubicTrans6) {
		this.pubicTrans6 = pubicTrans6;
	}

	/**
     * All required fields are validated and if the field is spaces an error
     * message is set.
     */
    public boolean validate()
    {
    	String errHeaderMessage = "";
    	boolean isEditBadChars = checkBadChars();
        boolean isValidTaxId = isValidTaxId(taxID);
        boolean validPin = isValidPIN();
        boolean isValidSoloGroup = isValidSoloGroup(soloGroup);
        boolean isValidEffectiveDate = isValidEffectiveDateForSecB(effectiveDate);
        boolean isValidEntries = isValidEntries(); // This part was not refactored due to time constraint.
        boolean isValidAddresses = isValidAddresses();
        /* PMF Section Changes -- AD21239 --*/
        boolean isValidKentuckyMedicaidId = true;
        isValidKentuckyMedicaidId  = isValidKentuckyMedicaidId();
        
        boolean allOk = validPin && isValidTaxId && isValidSoloGroup &&
            isValidEffectiveDate && isValidEntries && isValidAddresses && isEditBadChars && isOfficeHoursOk && isValidKentuckyMedicaidId;
        if(!allOk)
        {
        	errHeaderMessage = "Errors were detected on this form in Section(s)";
        	if(errorMessagesVectorA.size()>0)
        	{
        		errHeaderMessage = errHeaderMessage+" A,";
        	}
        	if(errorMessagesVectorB.size()>0)
        	{
        		errHeaderMessage = errHeaderMessage+" B,";
        	}
        	if(errorMessagesVectorC.size()>0)
        	{
        		errHeaderMessage = errHeaderMessage+" C,";
        	}
        	if(errorMessagesVectorD.size()>0)
        	{
        		errHeaderMessage = errHeaderMessage+" D,";
        	}
        	if(errorMessagesVectorE.size()>0)
        	{
        		errHeaderMessage = errHeaderMessage+" E,";
        	}
        	if(errorMessagesVectorF.size()>0)
        	{
        		errHeaderMessage = errHeaderMessage+" F,";
        	}
        	if(errorMessagesVectorG.size()>0)
        	{
        		errHeaderMessage = errHeaderMessage+" G,";
        	}
        	if(errorMessagesVectorH.size()>0)
        	{
        		errHeaderMessage = errHeaderMessage+" H,";
        	}
        	if(errorMessagesVectorI.size()>0)
        	{
        		errHeaderMessage = errHeaderMessage+" I,";
        	}
        	if(errorMessagesVectorJ.size()>0)
        	{
        		errHeaderMessage = errHeaderMessage+" J,";
        	}
        	if(errorMessagesVectorK.size()>0)
        	{
        		errHeaderMessage = errHeaderMessage+" K and";
        	}
        	if(errorMessagesVectorL.size()>0)
        	{
        		errHeaderMessage = errHeaderMessage+" L.";
        	}
    		errHeaderMessage = errHeaderMessage+" Please view the error message in these sections, correct form entries and resubmit form.";
        	errorMessagesVectorAll.add(errHeaderMessage);
        }
        return allOk;
    }
    private boolean isValidAddresses()
    {
        boolean isValid = true;
        final String invalidAddresses[] =
            {"same", "same as practice address", "see above"};

        for (int index = 0; index < invalidAddresses.length; index++)
        {
            String invalidAddress = toLower(invalidAddresses[index]);
            if (invalidAddress.equals(toLower(pracOfficeAddress1))){
            	
            	errorMessagesVectorE.add(INVALID_ADDRESS_ERROR +
                "office address. ");
            	isValid = false;
            	
            }
            if (invalidAddress.equals(toLower(pracOfficeAddress2))){
            	
            	errorMessagesVectorF.add(INVALID_ADDRESS_ERROR +
                "office address. ");
            	isValid = false;
            	
            } 
            if (invalidAddress.equals(toLower(pracOfficeAddress3)) ||
                invalidAddress.equals(toLower(pracOfficeAddress4)) ||
                invalidAddress.equals(toLower(pracOfficeAddress5)) ||
                invalidAddress.equals(toLower(pracOfficeAddress6))
                )
            {
                errorMessagesVectorG.add(INVALID_ADDRESS_ERROR +
                                        "office address. ");
                isValid = false;
            }
            if (invalidAddress.equals(toLower(pracBillAddress1))){
            	
                errorMessagesVectorE.add(INVALID_ADDRESS_ERROR +
                                        "billing address. ");
                isValid = false;
            }
            if (invalidAddress.equals(toLower(pracBillAddress2))){
                	
                    errorMessagesVectorF.add(INVALID_ADDRESS_ERROR +
                                            "billing address. ");
                    isValid = false;
                } 
            if (invalidAddress.equals(toLower(pracBillAddress3)) ||
                invalidAddress.equals(toLower(pracBillAddress4)) ||
                invalidAddress.equals(toLower(pracBillAddress5)) ||
                invalidAddress.equals(toLower(pracBillAddress6))
                )
            {
                errorMessagesVectorG.add(INVALID_ADDRESS_ERROR +
                                        "billing address. ");
                isValid = false;
            }
        }
        Address pracMainAddress = new Address("",pracOfficeAddress1,
                                              pracOfficeCity1, pracOfficeState1,
                                              pracOfficeZip1, pracOfficeCounty1,
                                              pracOfficePhone1,
                                              pracOfficeFax1, pracOfficeEmail1);
        Address pracMainBillingAddress = new Address(pracBillContactName1, pracBillAddress1,
            pracBillCity1, pracBillState1,
            pracBillZip1, pracBillCounty1, pracBillPhone1,
            pracBillFax1, pracBillContactEmail1);
        
        Address pracChangeAddress = new Address("",pracOfficeAddress2,
                                                pracOfficeCity2,
                                                pracOfficeState2,
                                                pracOfficeZip2,
                                                pracOfficeCounty2,
                                                pracOfficePhone2,
                                                pracOfficeFax2,
                                                pracOfficeEmail2);
        
        boolean isMainLocationAddressValid = isMainLocationAddressValid(
            addLocation, addProvider, billAddressSame1, pracMainAddress,
            pracMainBillingAddress);
        boolean isChangeAddressValid = isChangeAddressValid(chgPracAddress,
            pracChangeAddress);
        
        isValid = isValid && isChangeAddressValid && isMainLocationAddressValid;
        return isValid;
    }

    private boolean isMainLocationAddressValid(final String addLocation,
                                               final String addProvider,
                                               String billAddressSame1,
                                               final Address pracMainAddress,
                                               final Address pracBillingAddress)
    {
        boolean isMainAddressRequired = !isEmpty(addProvider) ||
            !isEmpty(addLocation);
        boolean isBillingAddressRequired = isEmpty(billAddressSame1);
        boolean isValid = true;
        if (isMainAddressRequired)
        {
          if (!isRequiredAddressFieldsEntered(pracMainAddress))
          {
            errorMessagesVectorE.add("If adding a new provider/adding a new location enter your office address in "+ Constants.sectionPracticeAddress + ".");
            isValid = false;
          }
        }
        else
        {
          if (isEmpty(pracMainAddress.getZipCode()))
          {
            errorMessagesVectorE.add(Constants.sectionPracticeAddress + " Practice zip code required.");
            isValid = false;
          }
        }
        if(boardCertified!=null && boardCertified.equalsIgnoreCase("N"))
        {
	        if(!isValidEffectiveDate(certExamDT)){
	        	errorMessagesVectorC.add(Constants.sectionProviderInfo + " when will you be sitting for the exam date invalid.");
	            isValid = false;
	        }
        }

        if (!isEmpty(pracMainAddress.getZipCode())) {
        	//PMF Change SSCR 13503 22/6/2012 Start
           	isValid = isValidZip(pracMainAddress.getZipCode(),Constants.sectionPracticeAddress,"Practice zip code",Constants.sectionPracticeAddress, isValid);
        	//PMF Change SSCR 13503 22/6/2012 Start
        }
        if (!isEmpty(pracBillingAddress.getZipCode())) {
        	//PMF Change SSCR 13503 22/6/2012 Start
           	isValid = isValidZip(pracBillingAddress.getZipCode(),Constants.sectionPracticeAddress,"zip code for payment",Constants.sectionPracticeAddress, isValid);
        	//PMF Change SSCR 13503 22/6/2012 Start
        }
        /*SSCR 9711-SSCR9724 change */
        if(isEmpty(pracMainAddress.getPhoneNumber()))
		{
			errorMessagesVectorE.add(Constants.sectionPracticeAddress + " office phone number is required.");
			isValid = false;
		}
		if(isEmpty(pracMainAddress.getFaxNumber()))
		{
			errorMessagesVectorE.add(Constants.sectionPracticeAddress + " office fax number is required.");
			isValid = false;
		}
		if(isEmpty(pracMainAddress.getEmai1()))
		{
			errorMessagesVectorE.add(Constants.sectionPracticeAddress + " group email address is required.");
			isValid = false;
		}
		if("9999999999".equals(pracMainAddress.getPhoneNumber()))
		{
			errorMessagesVectorE.add(Constants.sectionPracticeAddress + " office phone number is invalid.");
			isValid = false;
		}
		if("9999999999".equals(pracMainAddress.getFaxNumber()))
		{
			errorMessagesVectorE.add(Constants.sectionPracticeAddress + " office fax number is invalid.");
			isValid = false;
		}
		
		boolean isMandatory = StringUtils.isNotEmpty(hipIndicator) || StringUtils.isNotEmpty(medicaidIndicator);
		isOfficeHoursOk = editOfficeHours(isMandatory, Constants.sectionPracticeAddress, 0);
		
        /*SSCR 9711-SSCR9724 change end*/
        if (isMainAddressRequired && isBillingAddressRequired &&
            !isRequiredAddressFieldsEntered(pracBillingAddress))
        {
            errorMessagesVectorE.add("If adding a new provider enter your billing address or select 'Same as Practice Address' in "+ Constants.sectionPracticeAddress);
            isValid = false;
        }
        
        if(isEmpty(pracBillingAddress.getContactName()))
        {
        	errorMessagesVectorE.add(Constants.sectionPracticeAddress + " contact name for payment is required.");
        	isValid = false;
        }
        /*SSCR 9711-SSCR9724 change */
        if(isBillingAddressRequired)
        {
        	
	        if(isEmpty(pracBillingAddress.getPhoneNumber()))
	        {
	        	errorMessagesVectorE.add(Constants.sectionPracticeAddress + " phone number for payment is required.");
	        	isValid = false;
	        }
	        if(isEmpty(pracBillingAddress.getFaxNumber()))
	        {
	        	errorMessagesVectorE.add(Constants.sectionPracticeAddress + " fax number for payment is required.");
	        	isValid = false;
	        }
	        if(isEmpty(pracBillingAddress.getEmai1()))
	        {
	        	errorMessagesVectorE.add(Constants.sectionPracticeAddress + " email address for payment is required.");
	        	isValid = false;
	        }
	        if("9999999999".equals(pracBillingAddress.getPhoneNumber()))
	        {
	        	errorMessagesVectorE.add(Constants.sectionPracticeAddress + " billing phone number is invalid.");
	        	isValid = false;
	        }
	        if("9999999999".equals(pracBillingAddress.getFaxNumber()))
	        {
	        	errorMessagesVectorE.add(Constants.sectionPracticeAddress + " fax number for payment is invalid.");
	        	isValid = false;
	        }
        }
        /*SSCR 9711-SSCR9724 change end*/
        return isValid;
    }

    private boolean isChangeAddressValid(final String chgPracAddress,
                                         final Address pracChangeAddress)
    {
        boolean isValid = true;
        if (!isEmpty(chgPracAddress) &&
            !isRequiredAddressFieldsEntered(pracChangeAddress))
        {
            errorMessagesVectorF.add(
            		Constants.sectionAddressChange + " enter your new address since practice address change selected in  "+ Constants.sectionReasonSubmitting +".");
            isValid = false;
        }
        
        
        return isValid;
    }

    private boolean isRequiredAddressFieldsEntered(Address address)
    {
    	/* SCR 9711-SSCR9724 change-Added isEmpty checks for PhoneNumber and FaxNumber*/
        boolean isValid = true;
        if (isEmpty(address.getAddress1()) || isEmpty(address.getCity()) ||
            isEmpty(address.getState()) || isEmpty(address.getPhoneNumber())||
            isEmpty(address.getFaxNumber())||isEmpty(address.getZipCode()) ||
            isEmpty(address.getEmai1()) ||isEmpty(address.getCounty()))
        {
            isValid = false;
        }
        return isValid;
    }

    /**
     * Validates date
     * @param s
     * @return
     */
    private boolean validateDate(String s)
    {
        //format mmddyyyy.
        Integer monthNumber = null;
        Integer yearNumber = null;
        Integer dayNumber = null;
        int sLength = s.length();
        boolean isValid = false;
        if (sLength == 8)
        {
            if (validateNumeric(s.substring(0, 2)) &&
                validateNumeric(s.substring(2, 4)) &&
                validateNumeric(s.substring(4, 8)))
            {
                monthNumber = new Integer(s.substring(0, 2));
                dayNumber = new Integer(s.substring(2, 4));
                yearNumber = new Integer(s.substring(4, 8));
                if (dayNumber.intValue() <=
                    getNumDaysInMonth(yearNumber.intValue(),
                                      monthNumber.intValue()) &&
                    (dayNumber.intValue() > 0))
                {
                    isValid = true;
                }
            }
        }
        return isValid;
    }

    /**
     * Calcuates days in a month
     * @param yearNumber
     * @param monthNumber
     * @return
     */
    private int getNumDaysInMonth(int yearNumber, int monthNumber)
    {
        int numDaysInMonth = 0;

        switch (monthNumber)
        {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                numDaysInMonth = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                numDaysInMonth = 30;
                break;
            case 2:

                // Test for leap year
                if ( ( (yearNumber % 4 == 0) && (yearNumber % 100 != 0)) ||
                    (yearNumber % 400 == 0))
                {
                    numDaysInMonth = 29;
                }
                else
                {
                    numDaysInMonth = 28;
                }
                break;
            default:
                return 0;
        }
        return (numDaysInMonth);
    }

    /**
     * Validate if a numeric is entered
     * @param str
     * @return
     */
    private boolean validateNumeric(String str)
    {
        String validDigits = "0123456789";
        int sLength = str.length();

        for (int i = 0; i < sLength; i++)
        {
            if (validDigits.indexOf(str.substring(i, i + 1)) < 0)
            {
                return false;
            }
        }
        return true;
    }
    
    private boolean validatePhoneNumber(String str)
    {
    	if(str.length()==10 && validateNumeric(str))
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }

   
    /**
     * The getter methods for each property are defined below.
     */

    public String getTaxID()
    {
        return taxID;
    }

    public String getAnthemPIN()
    {
        return anthemPIN;
    }

    public String getPracName1()
    {
        return pracName1;
    }

    public String getSoloGroup()
    {
        return soloGroup;
    }

    public String getNumberInGroup()
    {
        return numberInGroup;
    }

    public String getRapidUpdate()
    {
        return rapidUpdate;
    }

    public String getEffectiveDate()
    {
        return effectiveDate;
    }

    public String getAddProvider()
    {
        return addProvider;
    }

    public String getDelProvider()
    {
        return delProvider;
    }

    public String getAddLocation()
    {
        return addLocation;
    }

    public String getAddNPI()
    {
        return addNPI;
    }

    public String getChgNPI()
    {
        return chgNPI;
    }


    public String getAddProvToLocation()
    {
        return addProvToLocation;
    }

    public String getChgSpecialty()
    {
        return chgSpecialty;
    }

    public String getChgProvName()
    {
        return chgProvName;
    }

    public String getDelLocation()
    {
        return delLocation;
    }

    public String getDelProvFromLocation()
    {
        return delProvFromLocation;
    }

    public String getChgPracName()
    {
        return chgPracName;
    }

    public String getChgPracAddress()
    {
        return chgPracAddress;
    }

    public String getChgPracPhone()
    {
        return chgPracPhone;
    }

    public String getChgTaxID()
    {
        return chgTaxID;
    }

    public String getChgBillName()
    {
        return chgBillName;
    }

    public String getChgBillAddress()
    {
        return chgBillAddress;
    }

    public String getChgBillPhone()
    {
        return chgBillPhone;
    }

    public String getDelReason()
    {
        return delReason;
    }

    public String getComments()
    {
        return comments;
    }

   	public String getConfirmation() {
		return confirmation;
	}
   	//PMF Change SSCR 13503  Start
	public String getConfProvAgreement() {
		return confProvAgreement;
	}
	public String getConfW2() {
		return confW2;
	}

	//PMF Change SSCR 13503 13/6/2012 End
	public String getOldTaxID()
    {
        return oldTaxID;
    }

    public String getProvFnm()
    {
        return provFnm;
    }

    public String getProvMI()
    {
        return provMI;
    }

    public String getProvLnm()
    {
        return provLnm;
    }

    public String getTitle()
    {
        return title;
    }

    public String getPrimSpecialtyPhy()
    {
        return primSpecialtyPhy;
    }

    public String getSpecialtyCarePhy()
    {
        return specialtyCarePhy;
    }

    public String getOther()
    {
        return other;
    }
    //PMF Change SSCR 13503 13/6/2012 Start
    public String getClearSelection()
    {
        return clearSelection;
    }
    public String getSpecialityInfo()
    {
        return specialityInfo;
    }
    public String getMedicarePartTrad()
    {
        return medicarePartTrad;
    }
    public String getMedicareApplDt()
    {
        return medicareApplDt;
    }
    public String getMedicareOptOut()
    {
        return medicareOptOut;
    }
    public String getMedicareOptOutDt()
    {
        return medicareOptOutDt;
    }
    public String getNpSupSpec()
    {
        return npSupSpec;
    }
    public String getNpSupPMP()
    {
        return npSupPMP;
    }
    public String getRetailHealthClinic()
    {
        return retailHealthClinic;
    }
    public String getWalkInDrOffice()
    {
        return walkInDrOffice;
    }
    public String getUrgentCare()
    {
        return urgentCare;
    }
    public String getCoveringPMP()
    {
        return coveringPMP;
    }
    public String getCertMidwife()
    {
        return certMidwife;
    }
    public String getPrenatelCareCoord()
    {
        return prenatelCareCoord;
    }
    public String getTribalHealthCtr()
    {
        return tribalHealthCtr;
    }
    public String getClinic()
    {
        return clinic;
    }
    public String getSpecialty()
    {
        return specialty;
    }
    public String getMedicationAssistedTreatment()
    {
        return medicationAssistedTreatment;
    }
    public String getResidentialTreatmentCenter()
    {
        return residentialTreatmentCenter;
    }
    public String getSubstanceUseDisorderAdults()
    {
        return substanceUseDisorderAdults;
    }
    public String getSubstanceUseDisorderChild()
    {
        return substanceUseDisorderChild;
    }
    public String getTelehealthProv()
    {
        return telehealthProv;
    }
    public String getFwdHealthCertNPI1()
    {
        return fwdHealthCertNPI1;
    }
    public String getFwdHealthCertNPIBilled1()
    {
        return fwdHealthCertNPIBilled1;
    }
    public String getFwdHealthCertEffDt1()
    {
        return fwdHealthCertEffDt1;
    }
    
    public String getFwdHealthRecertEffDt1()
    {
        return fwdHealthRecertEffDt1;
    }
    public String getFwdHealthCertNPI2()
    {
        return fwdHealthCertNPI2;
    }
    public String getFwdHealthCertNPIBilled2()
    {
        return fwdHealthCertNPIBilled2;
    }
    public String getFwdHealthCertEffDt2()
    {
        return fwdHealthCertEffDt2;
    }
    public String getFwdHealthRecertEffDt2()
    {
        return fwdHealthRecertEffDt2;
    }
    public String getFwdHealthCertNPI3()
    {
        return fwdHealthCertNPI3;
    }
    public String getFwdHealthCertNPIBilled3()
    {
        return fwdHealthCertNPIBilled3;
    }
    public String getFwdHealthCertEffDt3()
    {
        return fwdHealthCertEffDt3;
    }
    public String getFwdHealthRecertEffDt3()
    {
        return fwdHealthRecertEffDt3;
    }
    
    /*public String getBillingAndRendering()
    {
        return billingAndRendering;
    }
    public String getRenderingProvider()
    {
        return renderingProvider;
    }
    public String getGroupProvider()
    {
        return groupProvider;
    }*/
    //PMF Change SSCR 13503 13/6/2012 End
    public String getProvSSN()
    {
        return provSSN;
    }

    public String getUpinNumber()
    {
        return upinNumber;
    }

    public String getProfLicenseNumber()
    {
        return profLicenseNumber;
    }

    public String getCaqhIDNumber()
    {
        return caqhIDNumber;
    }

    public String getProvDOB()
    {
        return provDOB;
    }

    public String getProvGender()
    {
        return provGender;
    }

    public String getPracOfficeAddress1()
    {
        return pracOfficeAddress1;
    }

    public String getPracOfficeCity1()
    {
        return pracOfficeCity1;
    }

    public String getPracOfficeState1()
    {
        return pracOfficeState1;
    }

    public String getPracOfficeZip1()
    {
        return pracOfficeZip1;
    }

    public String getPracOfficeCounty1()
    {
        return pracOfficeCounty1;
    }

    public String getPracOfficePhone1()
    {
        return pracOfficePhone1;
    }

    public String getPracOfficeFax1()
    {
        return pracOfficeFax1;
    }

    public String getPracOfficeEmail1()
    {
        return pracOfficeEmail1;
    }

    public String getBillAddressSame1()
    {
        return billAddressSame1;
    }

	public String getPracBillContactName1() {
		return pracBillContactName1;
	}
	
    public String getPracBillAddress1()
    {
        return pracBillAddress1;
    }

    public String getPracBillCity1()
    {
        return pracBillCity1;
    }

    public String getPracBillState1()
    {
        return pracBillState1;
    }

    public String getPracBillZip1()
    {
        return pracBillZip1;
    }

    public String getPracBillCounty1()
    {
        return pracBillCounty1;
    }

    public String getPracBillPhone1()
    {
        return pracBillPhone1;
    }

    public String getPracBillFax1()
    {
        return pracBillFax1;
    }

   	public String getPracBillContactEmail1() {
		return pracBillContactEmail1;
	}


	public void setPracBillContactEmail1(String pracBillContactEmail1) {
		this.pracBillContactEmail1 = pracBillContactEmail1;
	}

	public void setPracBillContactEmail2(String pracBillContactEmail2) {
		this.pracBillContactEmail2 = pracBillContactEmail2;
	}

	public void setPracBillContactEmail3(String pracBillContactEmail3) {
		this.pracBillContactEmail3 = pracBillContactEmail3;
	}

	public void setPracBillContactEmail4(String pracBillContactEmail4) {
		this.pracBillContactEmail4 = pracBillContactEmail4;
	}

	public void setPracBillContactEmail5(String pracBillContactEmail5) {
		this.pracBillContactEmail5 = pracBillContactEmail5;
	}

	public void setPracBillContactEmail6(String pracBillContactEmail6) {
		this.pracBillContactEmail6 = pracBillContactEmail6;
	}

	public String getPracNPINo1()
    {
        return pracNPINo1;
    }

    public String getBillMedicareGroup1()
    {
        return billMedicareGroup1;
    }

    public String getBillMedicareIndividual1()
    {
        return billMedicareIndividual1;
    }

    public String getPracName2()
    {
        return pracName1;
    }

    public String getPracOfficeAddress2()
    {
        return pracOfficeAddress2;
    }

    public String getPracOfficeCity2()
    {
        return pracOfficeCity2;
    }

    public String getPracOfficeState2()
    {
        return pracOfficeState2;
    }

    public String getPracOfficeZip2()
    {
        return pracOfficeZip2;
    }

    public String getPracOfficeCounty2()
    {
        return pracOfficeCounty2;
    }

    public String getPracOfficePhone2()
    {
        return pracOfficePhone2;
    }

    public String getPracOfficeFax2()
    {
        return pracOfficeFax2;
    }

    public String getPracOfficeEmail2()
    {
        return pracOfficeEmail2;
    }

    public String getBillAddressSame2()
    {
        return billAddressSame2;
    }

    public String getPracBillAddress2()
    {
        return pracBillAddress2;
    }

    public String getPracBillCity2()
    {
        return pracBillCity2;
    }

    public String getPracBillState2()
    {
        return pracBillState2;
    }

    public String getPracBillZip2()
    {
        return pracBillZip2;
    }

    public String getPracBillCounty2()
    {
        return pracBillCounty2;
    }

    public String getPracBillPhone2()
    {
        return pracBillPhone2;
    }

    public String getPracBillFax2()
    {
        return pracBillFax2;
    }

    public String getPracNPINo2()
    {
        return pracNPINo2;
    }

    public String getBillMedicareGroup2()
    {
        return billMedicareGroup2;
    }

    public String getBillMedicareIndividual2()
    {
        return billMedicareIndividual2;
    }

    public String getPracName3()
    {
        return pracName3;
    }

    public String getPracOfficeAddress3()
    {
        return pracOfficeAddress3;
    }

    public String getPracOfficeCity3()
    {
        return pracOfficeCity3;
    }

    public String getPracOfficeState3()
    {
        return pracOfficeState3;
    }

    public String getPracOfficeZip3()
    {
        return pracOfficeZip3;
    }

    public String getPracOfficeCounty3()
    {
        return pracOfficeCounty3;
    }

    public String getPracOfficePhone3()
    {
        return pracOfficePhone3;
    }

    public String getPracOfficeFax3()
    {
        return pracOfficeFax3;
    }

    public String getPracOfficeEmail3()
    {
        return pracOfficeEmail3;
    }

    public String getBillAddressSame3()
    {
        return billAddressSame3;
    }

    public String getPracBillAddress3()
    {
        return pracBillAddress3;
    }

    public String getPracBillCity3()
    {
        return pracBillCity3;
    }

    public String getPracBillState3()
    {
        return pracBillState3;
    }

    public String getPracBillZip3()
    {
        return pracBillZip3;
    }

    public String getPracBillCounty3()
    {
        return pracBillCounty3;
    }

    public String getPracBillPhone3()
    {
        return pracBillPhone3;
    }

    public String getPracBillFax3()
    {
        return pracBillFax3;
    }

    public String getPracNPINo3()
    {
        return pracNPINo3;
    }

    public String getBillMedicareGroup3()
    {
        return billMedicareGroup3;
    }

    public String getBillMedicareIndividual3()
    {
        return billMedicareIndividual3;
    }

    public String getPracName4()
    {
        return pracName4;
    }

    public String getPracOfficeAddress4()
    {
        return pracOfficeAddress4;
    }

    public String getPracOfficeCity4()
    {
        return pracOfficeCity4;
    }

    public String getPracOfficeState4()
    {
        return pracOfficeState4;
    }

    public String getPracOfficeZip4()
    {
        return pracOfficeZip4;
    }

    public String getPracOfficeCounty4()
    {
        return pracOfficeCounty4;
    }

    public String getPracOfficePhone4()
    {
        return pracOfficePhone4;
    }

    public String getPracOfficeFax4()
    {
        return pracOfficeFax4;
    }

    public String getPracOfficeEmail4()
    {
        return pracOfficeEmail4;
    }

    public String getBillAddressSame4()
    {
        return billAddressSame4;
    }

    public String getPracBillAddress4()
    {
        return pracBillAddress4;
    }

    public String getPracBillCity4()
    {
        return pracBillCity4;
    }

    public String getPracBillState4()
    {
        return pracBillState4;
    }

    public String getPracBillZip4()
    {
        return pracBillZip4;
    }

    public String getPracBillCounty4()
    {
        return pracBillCounty4;
    }

    public String getPracBillPhone4()
    {
        return pracBillPhone4;
    }

    public String getPracBillFax4()
    {
        return pracBillFax4;
    }

    public String getPracNPINo4()
    {
        return pracNPINo4;
    }

    public String getBillMedicareGroup4()
    {
        return billMedicareGroup4;
    }

    public String getBillMedicareIndividual4()
    {
        return billMedicareIndividual4;
    }

    public String getPracName5()
    {
        return pracName5;
    }

    public String getPracOfficeAddress5()
    {
        return pracOfficeAddress5;
    }

    public String getPracOfficeCity5()
    {
        return pracOfficeCity5;
    }

    public String getPracOfficeState5()
    {
        return pracOfficeState5;
    }

    public String getPracOfficeZip5()
    {
        return pracOfficeZip5;
    }

    public String getPracOfficeCounty5()
    {
        return pracOfficeCounty5;
    }

    public String getPracOfficePhone5()
    {
        return pracOfficePhone5;
    }

    public String getPracOfficeFax5()
    {
        return pracOfficeFax5;
    }

    public String getPracOfficeEmail5()
    {
        return pracOfficeEmail5;
    }

    public String getBillAddressSame5()
    {
        return billAddressSame5;
    }

    public String getPracBillAddress5()
    {
        return pracBillAddress5;
    }

    public String getPracBillCity5()
    {
        return pracBillCity5;
    }

    public String getPracBillState5()
    {
        return pracBillState5;
    }

    public String getPracBillZip5()
    {
        return pracBillZip5;
    }

    public String getPracBillCounty5()
    {
        return pracBillCounty5;
    }

    public String getPracBillPhone5()
    {
        return pracBillPhone5;
    }

    public String getPracBillFax5()
    {
        return pracBillFax5;
    }

    public String getPracNPINo5()
    {
        return pracNPINo5;
    }

    public String getBillMedicareGroup5()
    {
        return billMedicareGroup5;
    }

    public String getBillMedicareIndividual5()
    {
        return billMedicareIndividual5;
    }

    public String getPracName6()
    {
        return pracName6;
    }

    public String getPracOfficeAddress6()
    {
        return pracOfficeAddress6;
    }

    public String getPracOfficeCity6()
    {
        return pracOfficeCity6;
    }

    public String getPracOfficeState6()
    {
        return pracOfficeState6;
    }

    public String getPracOfficeZip6()
    {
        return pracOfficeZip6;
    }

    public String getPracOfficeCounty6()
    {
        return pracOfficeCounty6;
    }

    public String getPracOfficePhone6()
    {
        return pracOfficePhone6;
    }

    public String getPracOfficeFax6()
    {
        return pracOfficeFax6;
    }

    public String getPracOfficeEmail6()
    {
        return pracOfficeEmail6;
    }

    public String getBillAddressSame6()
    {
        return billAddressSame6;
    }

    public String getPracBillAddress6()
    {
        return pracBillAddress6;
    }

    public String getPracBillCity6()
    {
        return pracBillCity6;
    }

    public String getPracBillState6()
    {
        return pracBillState6;
    }

    public String getPracBillZip6()
    {
        return pracBillZip6;
    }

    public String getPracBillCounty6()
    {
        return pracBillCounty6;
    }

    public String getPracBillPhone6()
    {
        return pracBillPhone6;
    }

    public String getPracBillFax6()
    {
        return pracBillFax6;
    }

    public String getPracNPINo6()
    {
        return pracNPINo6;
    }

    public String getBillMedicareGroup6()
    {
        return billMedicareGroup6;
    }

    public String getBillMedicareIndividual6()
    {
        return billMedicareIndividual6;
    }

    /**
	 * @return the pracBillContactName2
	 */
	public String getPracBillContactName2() {
		return pracBillContactName2;
	}

	/**
	 * @return the pracBillContactEmail2
	 */
	public String getPracBillContactEmail2() {
		return pracBillContactEmail2;
	}

	/**
	 * @return the pracBillContactName3
	 */
	public String getPracBillContactName3() {
		return pracBillContactName3;
	}

	/**
	 * @return the pracBillContactEmail3
	 */
	public String getPracBillContactEmail3() {
		return pracBillContactEmail3;
	}

	/**
	 * @return the pracBillContactName4
	 */
	public String getPracBillContactName4() {
		return pracBillContactName4;
	}

	/**
	 * @return the pracBillContactEmail4
	 */
	public String getPracBillContactEmail4() {
		return pracBillContactEmail4;
	}

	/**
	 * @return the pracBillContactName5
	 */
	public String getPracBillContactName5() {
		return pracBillContactName5;
	}

	/**
	 * @return the pracBillContactEmail5
	 */
	public String getPracBillContactEmail5() {
		return pracBillContactEmail5;
	}

	/**
	 * @return the pracBillContactName6
	 */
	public String getPracBillContactName6() {
		return pracBillContactName6;
	}

	/**
	 * @return the pracBillContactEmail6
	 */
	public String getPracBillContactEmail6() {
		return pracBillContactEmail6;
	}

	public String getGrpEntityName1()
    {
        return grpEntityName1;
    }

    public String getSpecialty1()
    {
        return specialty1;
    }
    

    public String getTaxID1()
    {
        return taxID1;
    }

    public String getEffectiveDate1()
    {
        return effectiveDate1;
    }

    public String getGrpEntityName2()
    {
        return grpEntityName2;
    }

    public String getSpecialty2()
    {
        return specialty2;
    }

    public String getTaxID2()
    {
        return taxID2;
    }

    public String getEffectiveDate2()
    {
        return effectiveDate2;
    }

    public String getGrpEntityName3()
    {
        return grpEntityName3;
    }

    public String getSpecialty3()
    {
        return specialty3;
    }

    public String getTaxID3()
    {
        return taxID3;
    }

    public String getEffectiveDate3()
    {
        return effectiveDate3;
    }

    public String getGrpEntityName4()
    {
        return grpEntityName4;
    }

    public String getSpecialty4()
    {
        return specialty4;
    }

    public String getTaxID4()
    {
        return taxID4;
    }

    public String getEffectiveDate4()
    {
        return effectiveDate4;
    }

    public String getGrpEntityName5()
    {
        return grpEntityName5;
    }

    public String getSpecialty5()
    {
        return specialty5;
    }

    public String getTaxID5()
    {
        return taxID5;
    }


	public String getMedicaidGroup1()
    {
      return medicaidGroup1;
    }

    public void setMedicaidGroup1(String medcaidGrpVal)
    {
        medicaidGroup1 = medcaidGrpVal;
    }


    public String getMedicaidIndividual1()
    {
      return medicaidIndividual1;
    }

    public void setMedicaidIndividual1(String medcaidIndvVal)
    {
        medicaidIndividual1 = medcaidIndvVal;
    }


    public String getMedicaidGroup2()
    {
      return medicaidGroup2;
    }

    public void setMedicaidGroup2(String medcaidGrpVal)
   {
       medicaidGroup2 = medcaidGrpVal;
   }


    public String getMedicaidIndividual2()
    {
      return medicaidIndividual2;
    }

    public void setMedicaidIndividual2(String medcaidIndvVal)
    {
        medicaidIndividual2 = medcaidIndvVal;
    }


    public String getMedicaidGroup3()
    {
      return medicaidGroup3;
    }

    public void setMedicaidGroup3(String medcaidGrpVal)
   {
       medicaidGroup3 = medcaidGrpVal;
   }

    public String getMedicaidIndividual3()
    {
      return medicaidIndividual3;
    }

    public void setMedicaidIndividual3(String medcaidIndvVal)
    {
        medicaidIndividual3 = medcaidIndvVal;
    }

    public String getMedicaidGroup4()
    {
      return medicaidGroup4;
    }

    public void setMedicaidGroup4(String medcaidGrpVal)
   {
       medicaidGroup4 = medcaidGrpVal;
   }

    public String getMedicaidIndividual4()
    {
      return medicaidIndividual4;
    }

    public void setMedicaidIndividual4(String medcaidIndvVal)
    {
        medicaidIndividual4 = medcaidIndvVal;
    }

    public String getMedicaidGroup5()
    {
    return medicaidGroup5;
    }

    public void setMedicaidGroup5(String medcaidGrpVal)
   {
       medicaidGroup5 = medcaidGrpVal;
   }

    public String getMedicaidIndividual5()
    {
      return medicaidIndividual5;
    }

    public void setMedicaidIndividual5(String medcaidIndvVal)
    {
        medicaidIndividual5 = medcaidIndvVal;
    }


    public String getMedicaidGroup6()
    {
      return medicaidGroup6;
    }

    public void setMedicaidGroup6(String medcaidGrpVal)
   {
       medicaidGroup6 = medcaidGrpVal;
   }


    public String getMedicaidIndividual6()
    {
    return medicaidIndividual6;
    }

    public void setMedicaidIndividual6(String medcaidIndvVal)
    {
        medicaidIndividual6 = medcaidIndvVal;
    }

    public String getEffectiveDate5()
    {
        return effectiveDate5;
    }

    /*public Vector getErrorMessagesVector()
    {
        return errorMessagesVector;
    }*/
    
    public Vector getErrorMessagesVectorAll()
    {
    	return errorMessagesVectorAll;
    }
    public Vector getErrorMessagesVectorA()
    {
    	return errorMessagesVectorA;
    }
    public Vector getErrorMessagesVectorB()
    {
    	return errorMessagesVectorB;
    }
    public Vector getErrorMessagesVectorC()
    {
    	return errorMessagesVectorC;
    }
    public Vector getErrorMessagesVectorD()
    {
    	return errorMessagesVectorD;
    }
    public Vector getErrorMessagesVectorE()
    {
    	return errorMessagesVectorE;
    }
    public Vector getErrorMessagesVectorF()
    {
    	return errorMessagesVectorF;
    }
    public Vector getErrorMessagesVectorG()
    {
    	return errorMessagesVectorG;
    }
    public Vector getErrorMessagesVectorH()
    {
    	return errorMessagesVectorH;
    }
    public Vector getErrorMessagesVectorI()
    {
    	return errorMessagesVectorI;
    }
    public Vector getErrorMessagesVectorJ()
    {
    	return errorMessagesVectorJ;
    }
    public Vector getErrorMessagesVectorK()
    {
    	return errorMessagesVectorK;
    }
    public Vector getErrorMessagesVectorL()
    {
    	return errorMessagesVectorL;
    }

    /**
     *public String getOfficeEmail1()
      {
      return pracOfficeEmail1;
      }
     * The setter methods for each property are defined below.
     */

    public void setTaxID(String sTaxID)
    {
        if (sTaxID.equals(""))
        {
            taxID = " ";
        }
        else
        {
            taxID = sTaxID;
        }
    }

    public void setAnthemPIN(String sAnthemPIN)
    {
        if (sAnthemPIN.equals(""))
        {
            anthemPIN = " ";
        }
        else
        {
            anthemPIN = sAnthemPIN;
        }
    }

    public void setPracName1(String sPracName1)
    {
        if (sPracName1.equals(""))
        {
            pracName1 = " ";
        }
        else
        {
            pracName1 = sPracName1;
        }
    }

    public void setSoloGroup(String sSoloGroup)
    {
        if (sSoloGroup.equals(""))
        {
            soloGroup = " ";
        }
        else
        {
            soloGroup = sSoloGroup;
        }
    }

    public void setNumberInGroup(String sNumberInGroup)
    {
        if (sNumberInGroup.equals(""))
        {
            numberInGroup = " ";
        }
        else
        {
            numberInGroup = sNumberInGroup;
        }
    }

    public void setRapidUpdate(String sRapidUpdate)
    {
        if (sRapidUpdate.equals(""))
        {
            rapidUpdate = " ";
        }
        else
        {
            rapidUpdate = sRapidUpdate;
        }
    }

    public void setEffectiveDate(String sEffectiveDate)
    {
        if (sEffectiveDate.equals(""))
        {
            effectiveDate = " ";
        }
        else
        {
            effectiveDate = sEffectiveDate;
        }
    }

    public void setAddProvider(String sAddProvider)
    {
        if (sAddProvider.equals(""))
        {
            addProvider = " ";
        }
        else
        {
            addProvider = sAddProvider;
        }
    }

    public void setDelProvider(String sDelProvider)
    {
        if (sDelProvider.equals(""))
        {
            delProvider = " ";
        }
        else
        {
            delProvider = sDelProvider;
        }
    }

    public void setAddLocation(String sAddLocation)
    {
        if (sAddLocation.equals(""))
        {
            addLocation = " ";
        }
        else
        {
            addLocation = sAddLocation;
        }
    }

    public void setAddNPI(String sAddNPI)
    {
        addNPI = sAddNPI;
    }

    public void setChgNPI(String sChgNPI)
    {
        chgNPI = sChgNPI;
    }


    public void setAddProvToLocation(String sAddProvToLocation)
    {
        if (sAddProvToLocation.equals(""))
        {
            addProvToLocation = " ";
        }
        else
        {
            addProvToLocation = sAddProvToLocation;
        }
    }

    public void setChgSpecialty(String sChgSpecialty)
    {
        if (sChgSpecialty.equals(""))
        {
            chgSpecialty = " ";
        }
        else
        {
            chgSpecialty = sChgSpecialty;
        }
    }

    public void setChgProvName(String sChgProvName)
    {
        if (sChgProvName.equals(""))
        {
            chgProvName = " ";
        }
        else
        {
            chgProvName = sChgProvName;
        }
    }

    public void setDelLocation(String sDelLocation)
    {
        if (sDelLocation.equals(""))
        {
            delLocation = " ";
        }
        else
        {
            delLocation = sDelLocation;
        }
    }

    public void setDelProvFromLocation(String sDelProvFromLocation)
    {
        if (sDelProvFromLocation.equals(""))
        {
            delProvFromLocation = " ";
        }
        else
        {
            delProvFromLocation = sDelProvFromLocation;
        }
    }

    public void setChgPracName(String sChgPracName)
    {
        if (sChgPracName.equals(""))
        {
            chgPracName = " ";
        }
        else
        {
            chgPracName = sChgPracName;
        }
    }

    public void setChgPracAddress(String sChgPracAddress)
    {
        if (sChgPracAddress.equals(""))
        {
            chgPracAddress = " ";
        }
        else
        {
            chgPracAddress = sChgPracAddress;
        }
    }

    public void setChgPracPhone(String sChgPracPhone)
    {
        if (sChgPracPhone.equals(""))
        {
            chgPracPhone = " ";
        }
        else
        {
            chgPracPhone = sChgPracPhone;
        }
    }

    public void setChgTaxID(String sChgTaxID)
    {
        if (sChgTaxID.equals(""))
        {
            chgTaxID = " ";
        }
        else
        {
            chgTaxID = sChgTaxID;
        }
    }

    public void setChgBillName(String sChgBillName)
    {
        if (sChgBillName.equals(""))
        {
            chgBillName = " ";
        }
        else
        {
            chgBillName = sChgBillName;
        }
    }

    public void setChgBillAddress(String sChgBillAddress)
    {
        if (sChgBillAddress.equals(""))
        {
            chgBillAddress = " ";
        }
        else
        {
            chgBillAddress = sChgBillAddress;
        }
    }

    public void setChgBillPhone(String sChgBillPhone)
    {
        if (sChgBillPhone.equals(""))
        {
            chgBillPhone = " ";
        }
        else
        {
            chgBillPhone = sChgBillPhone;
        }
    }

    public void setDelReason(String sDelReason)
    {
        if (sDelReason.equals(""))
        {
            delReason = " ";
        }
        else
        {
            delReason = sDelReason;
        }
    }

    public void setComments(String sComments)
    {
        if (sComments.equals(""))
        {
            comments = " ";
        }
        else
        {
            comments = sComments;
        }
    }

	public void setConfirmation(String sConfirmation) {
		if (sConfirmation.equals(""))
        {
            confirmation = " ";
        }
        else
        {
        	confirmation = sConfirmation;
        }
	}
	//PMF Change SSCR 13503 13/6/2012 Start
	public void setConfProvAgreement(String sConfProvAgreement) {
		if (sConfProvAgreement.equals(""))
        {
			confProvAgreement = " ";
        }
        else
        {
        	confProvAgreement = sConfProvAgreement;
        }
	}
	public void setConfW2(String sConfW2) {
		if (sConfW2.equals(""))
     {
			confW2 = " ";
     }
     else
     {
       confW2 = sConfW2;
     }
	 }

	//PMF Change SSCR 13503 13/6/2012 End
	public void setOldTaxID(String sOldTaxID)
    {
        if (sOldTaxID.equals(""))
        {
            oldTaxID = " ";
        }
        else
        {
            oldTaxID = sOldTaxID;
        }
    }

    public void setProvFnm(String sProvFnm)
    {
        if (sProvFnm.equals(""))
        {
            provFnm = " ";
        }
        else
        {
            provFnm = sProvFnm;
        }
    }

    public void setProvMI(String sProvMI)
    {
        if (sProvMI.equals(""))
        {
            provMI = " ";
        }
        else
        {
            provMI = sProvMI;
        }
    }

    public void setProvLnm(String sProvLnm)
    {
        if (sProvLnm.equals(""))
        {
            provLnm = " ";
        }
        else
        {
            provLnm = sProvLnm;
        }
    }

    public void setTitle(String sTitle)
    {
        if (sTitle.equals(""))
        {
            title = " ";
        }
        else
        {
            title = sTitle;
        }
    }

    public void setPrimSpecialtyPhy(String sPrimSpecialtyPhy)
    {
        if (sPrimSpecialtyPhy.equals(""))
        {
            primSpecialtyPhy = " ";
        }
        else
        {
            primSpecialtyPhy = sPrimSpecialtyPhy;
        }
    }

    public void setSpecialtyCarePhy(String sSpecialtyCarePhy)
    {
        if (sSpecialtyCarePhy.equals(""))
        {
            specialtyCarePhy = " ";
        }
        else
        {
            specialtyCarePhy = sSpecialtyCarePhy;
        }
    }

    public void setOther(String sOther)
    {
        if (sOther.equals(""))
        {
            other = " ";
        }
        else
        {
            other = sOther;
        }
    }
    //PMF Change SSCR 13503 13/6/2012 Start
    public void setSpecialityInfo(String sSpecialityInfo)
    {
        this.specialityInfo = sSpecialityInfo;
      
    }
    public void setClearSelection(String sClearSelection)
    {
        if (sClearSelection.equals(""))
        {
            clearSelection = " ";
        }
        else
        {
        	clearSelection = sClearSelection;
        }
    }
    
    public void setMedicarePartTrad(String sMedicarePartTrad)
    {
        if (sMedicarePartTrad.equals(""))
        {
        	medicarePartTrad = " ";
        }
        else
        {
        	medicarePartTrad = sMedicarePartTrad;
        }
    }
    public void setMedicareApplDt(String sMedicareApplDt)
    {
        if (sMedicareApplDt.equals(""))
        {
        	medicareApplDt = " ";
        }
        else
        {
        	medicareApplDt = sMedicareApplDt;
        }
    }
    public void setMedicareOptOut(String sMedicareOptOut)
    {
        if (sMedicareOptOut.equals(""))
        {
        	medicareOptOut = " ";
        }
        else
        {
        	medicareOptOut = sMedicareOptOut;
        }
    }
    public void setMedicareOptOutDt(String sMedicareOptOutDt)
    {
        if (sMedicareOptOutDt.equals(""))
        {
        	medicareOptOutDt = " ";
        }
        else
        {
        	medicareOptOutDt = sMedicareOptOutDt;
        }
    }
    public void setNpSupSpec(String sNpSupSpec)
    {
        if (sNpSupSpec.equals(""))
        {
        	npSupSpec = " ";
        }
        else
        {
        	npSupSpec = sNpSupSpec;
        }
    }
    public void setNpSupPMP(String sNpSupPMP)
    {
        if (sNpSupPMP.equals(""))
        {
        	npSupPMP = " ";
        }
        else
        {
        	npSupPMP = sNpSupPMP;
        }
    }
    public void setRetailHealthClinic(String sRetailHealthClinic)
    {
        if (sRetailHealthClinic.equals(""))
        {
        	retailHealthClinic = " ";
        }
        else
        {
        	retailHealthClinic = sRetailHealthClinic;
        }
    }
    public void setWalkInDrOffice(String sWalkInDrOffice)
    {
        if (sWalkInDrOffice.equals(""))
        {
        	walkInDrOffice = " ";
        }
        else
        {
        	walkInDrOffice = sWalkInDrOffice;
        }
    }
    public void setUrgentCare(String sUrgentCare)
    {
        if (sUrgentCare.equals(""))
        {
        	urgentCare = " ";
        }
        else
        {
        	urgentCare = sUrgentCare;
        }
    }
    public void setCoveringPMP(String sCoveringPMP)
    {
        if (sCoveringPMP.equals(""))
        {
        	coveringPMP = " ";
        }
        else
        {
        	coveringPMP = sCoveringPMP;
        }
    }
    public void setCertMidwife(String sCertMidwife)
    {
        if (sCertMidwife.equals(""))
        {
        	certMidwife = " ";
        }
        else
        {
        	certMidwife = sCertMidwife;
        }
    }
    public void setPrenatelCareCoord(String sPrenatelCareCoord)
    {
        if (sPrenatelCareCoord.equals(""))
        {
        	prenatelCareCoord = " ";
        }
        else
        {
        	prenatelCareCoord = sPrenatelCareCoord;
        }
    }
    public void setTribalHealthCtr(String sTribalHealthCtr)
    {
        if (sTribalHealthCtr.equals(""))
        {
        	tribalHealthCtr = " ";
        }
        else
        {
        	tribalHealthCtr = sTribalHealthCtr;
        }
    }
    public void setClinic(String sClinic)
    {
        if (sClinic.equals(""))
        {
        	clinic = " ";
        }
        else
        {
        	clinic = sClinic;
        }
    }
    /*public String getSpecialty()
    {
        return specialty5;
    }*/
    public void setSpecialty(String sSpecialty)
    {
        if (sSpecialty.equals(""))
        {
        	specialty = " ";
        }
        else
        {
        	specialty = sSpecialty;
        }
    }
    public void setMedicationAssistedTreatment(String sMedicationAssistedTreatment)
    {
        if (sMedicationAssistedTreatment.equals(""))
        {
        	medicationAssistedTreatment = " ";
        }
        else
        {
        	medicationAssistedTreatment = sMedicationAssistedTreatment;
        }
    }
    public void setResidentialTreatmentCenter(String sResidentialTreatmentCenter)
    {
        if (sResidentialTreatmentCenter.equals(""))
        {
        	residentialTreatmentCenter = " ";
        }
        else
        {
        	residentialTreatmentCenter = sResidentialTreatmentCenter;
        }
    }
    public void setSubstanceUseDisorderAdults(String sSubstanceUseDisorderAdults)
    {
        if (sSubstanceUseDisorderAdults.equals(""))
        {
        	substanceUseDisorderAdults = " ";
        }
        else
        {
        	substanceUseDisorderAdults = sSubstanceUseDisorderAdults;
        }
    }
    public void setSubstanceUseDisorderChild(String sSubstanceUseDisorderChild)
    {
        if (sSubstanceUseDisorderChild.equals(""))
        {
        	substanceUseDisorderChild = " ";
        }
        else
        {
        	substanceUseDisorderChild = sSubstanceUseDisorderChild;
        }
    }
    public void setTelehealthProv(String sTelehealthProv)
    {
        if (sTelehealthProv.equals(""))
        {
        	telehealthProv = " ";
        }
        else
        {
        	telehealthProv = sTelehealthProv;
        }
    }
     public void setFwdHealthCertNPI1(String sFwdHealthCertNPI1)
    {
        if (sFwdHealthCertNPI1.equals(""))
        {
        	fwdHealthCertNPI1 = " ";
        }
        else
        {
        	fwdHealthCertNPI1 = sFwdHealthCertNPI1;
        }
    }
    public void setFwdHealthCertNPIBilled1(String sFwdHealthCertNPIBilled1)
    {
        if (sFwdHealthCertNPIBilled1.equals(""))
        {
        	fwdHealthCertNPIBilled1 = " ";
        }
        else
        {
        	fwdHealthCertNPIBilled1 = sFwdHealthCertNPIBilled1;
        }
    }
    public void setFwdHealthCertEffDt1(String sFwdHealthCertEffDt1)
    {
        if (sFwdHealthCertEffDt1.equals(""))
        {
        	fwdHealthCertEffDt1 = " ";
        }
        else
        {
        	fwdHealthCertEffDt1 = sFwdHealthCertEffDt1;
        }
    }
    public void setFwdHealthRecertEffDt1(String sFwdHealthRecertEffDt1)
    {
        if (sFwdHealthRecertEffDt1.equals(""))
        {
        	fwdHealthRecertEffDt1 = " ";
        }
        else
        {
        	fwdHealthRecertEffDt1 = sFwdHealthRecertEffDt1;
        }
    }
    public void setFwdHealthCertNPI2(String sFwdHealthCertNPI2)
    {
        if (sFwdHealthCertNPI2.equals(""))
        {
        	fwdHealthCertNPI2 = " ";
        }
        else
        {
        	fwdHealthCertNPI2 = sFwdHealthCertNPI2;
        }
    }
    public void setFwdHealthCertNPIBilled2(String sFwdHealthCertNPIBilled2)
    {
        if (sFwdHealthCertNPIBilled2.equals(""))
        {
        	fwdHealthCertNPIBilled2 = " ";
        }
        else
        {
        	fwdHealthCertNPIBilled2 = sFwdHealthCertNPIBilled2;
        }
    }
    public void setFwdHealthCertEffDt2(String sFwdHealthCertEffDt2)
    {
        if (sFwdHealthCertEffDt2.equals(""))
        {
        	fwdHealthCertEffDt2 = " ";
        }
        else
        {
        	fwdHealthCertEffDt2 = sFwdHealthCertEffDt2;
        }
    }
    public void setFwdHealthRecertEffDt2(String sFwdHealthRecertEffDt2)
    {
        if (sFwdHealthRecertEffDt2.equals(""))
        {
        	fwdHealthRecertEffDt2 = " ";
        }
        else
        {
        	fwdHealthRecertEffDt2 = sFwdHealthRecertEffDt2;
        }
    }
    public void setFwdHealthCertNPI3(String sFwdHealthCertNPI3)
    {
        if (sFwdHealthCertNPI3.equals(""))
        {
        	fwdHealthCertNPI3 = " ";
        }
        else
        {
        	fwdHealthCertNPI3 = sFwdHealthCertNPI3;
        }
    }
    public void setFwdHealthCertNPIBilled3(String sFwdHealthCertNPIBilled3)
    {
        if (sFwdHealthCertNPIBilled3.equals(""))
        {
        	fwdHealthCertNPIBilled3 = " ";
        }
        else
        {
        	fwdHealthCertNPIBilled3 = sFwdHealthCertNPIBilled3;
        }
    }
    public void setFwdHealthCertEffDt3(String sFwdHealthCertEffDt3)
    {
        if (sFwdHealthCertEffDt3.equals(""))
        {
        	fwdHealthCertEffDt3 = " ";
        }
        else
        {
        	fwdHealthCertEffDt3 = sFwdHealthCertEffDt3;
        }
    }
    public void setFwdHealthRecertEffDt3(String sFwdHealthRecertEffDt3)
    {
        if (sFwdHealthRecertEffDt3.equals(""))
        {
        	fwdHealthRecertEffDt3 = " ";
        }
        else
        {
        	fwdHealthRecertEffDt3 = sFwdHealthRecertEffDt3;
        }
    }
    //PMF Change SSCR 13503 13/6/2012 End
    public void setProvSSN(String sProvSSN)
    {
        if (sProvSSN.equals(""))
        {
            provSSN = " ";
        }
        else
        {
            provSSN = sProvSSN;
        }
    }

    public void setUpinNumber(String sUpinNumber)
    {
        if(sUpinNumber!=null)
        {
	    	if (sUpinNumber.equals(""))
	        {
	            upinNumber = " ";
	        }
	        else
	        {
	            upinNumber = sUpinNumber;
	        }
        }
    }

    public void setProfLicenseNumber(String sProfLicenseNumber)
    {
        if (sProfLicenseNumber.equals(""))
        {
            profLicenseNumber = " ";
        }
        else
        {
            profLicenseNumber = sProfLicenseNumber;
        }
    }

    public void setCaqhIDNumber(String sCaqhIDNumber)
    {
        if (sCaqhIDNumber.equals(""))
        {
            caqhIDNumber = " ";
        }
        else
        {
            caqhIDNumber = sCaqhIDNumber;
        }
    }

    public void setProvDOB(String sProvDOB)
    {
        if (sProvDOB.equals(""))
        {
            provDOB = " ";
        }
        else
        {
            provDOB = sProvDOB;
        }
    }

    public void setProvGender(String sProvGender)
    {
        if (sProvGender.equals(""))
        {
            provGender = " ";
        }
        else
        {
            provGender = sProvGender;
        }
    }

    public void setPracOfficeAddress1(String sPracOfficeAddress1)
    {
        if (sPracOfficeAddress1.equals(""))
        {
            pracOfficeAddress1 = " ";
        }
        else
        {
            pracOfficeAddress1 = sPracOfficeAddress1;
        }
    }

    public void setPracOfficeCity1(String sPracOfficeCity1)
    {
        if (sPracOfficeCity1.equals(""))
        {
            pracOfficeCity1 = " ";
        }
        else
        {
            pracOfficeCity1 = sPracOfficeCity1;
        }
    }

    public void setPracOfficeState1(String sPracOfficeState1)
    {
        if (sPracOfficeState1.equals(""))
        {
            pracOfficeState1 = " ";
        }
        else
        {
            pracOfficeState1 = sPracOfficeState1;
        }
    }

    public void setPracOfficeZip1(String sPracOfficeZip1)
    {
        if (sPracOfficeZip1.equals(""))
        {
            pracOfficeZip1 = " ";
        }
        else
        {
            pracOfficeZip1 = sPracOfficeZip1;
        }
    }

    public void setPracOfficeCounty1(String sPracOfficeCounty1)
    {
        if (sPracOfficeCounty1.equals(""))
        {
            pracOfficeCounty1 = " ";
        }
        else
        {
            pracOfficeCounty1 = sPracOfficeCounty1;
        }
    }

    public void setPracOfficePhone1(String sPracOfficePhone1)
    {
        if (sPracOfficePhone1.equals(""))
        {
            pracOfficePhone1 = " ";
        }
        else
        {
            pracOfficePhone1 = sPracOfficePhone1;
        }
    }

    public void setPracOfficeFax1(String sPracOfficeFax1)
    {
        if (sPracOfficeFax1.equals(""))
        {
            pracOfficeFax1 = " ";
        }
        else
        {
            pracOfficeFax1 = sPracOfficeFax1;
        }
    }

    public void setPracOfficeEmail1(String sPracOfficeEmail1)
    {
        if (sPracOfficeEmail1.equals(""))
        {
            pracOfficeEmail1 = " ";
        }
        else
        {
            pracOfficeEmail1 = sPracOfficeEmail1;
        }
    }

    public void setBillAddressSame1(String sBillAddressSame1)
    {
        if (sBillAddressSame1.equals(""))
        {
            billAddressSame1 = " ";
        }
        else
        {
            billAddressSame1 = sBillAddressSame1;
        }
    }
    

	public void setPracBillContactName1(String sPracBillContactName1) {
		
		 if (sPracBillContactName1.equals(""))
	        {
			 pracBillContactName1 = " ";
	        }
	        else
	        {
	        	pracBillContactName1 = sPracBillContactName1;
	        }
	}
	
	

    /**
	 * @param pracBillContactName2 the pracBillContactName2 to set
	 */
	public void setPracBillContactName2(String sPracBillContactName2) {
		if (sPracBillContactName2.equals(""))
        {
		 pracBillContactName2 = " ";
        }
        else
        {
        	pracBillContactName2 = sPracBillContactName2;
        }
	}

	/**
	 * @param pracBillContactName3 the pracBillContactName3 to set
	 */
	public void setPracBillContactName3(String sPracBillContactName3) {
		if (sPracBillContactName3.equals(""))
        {
		 pracBillContactName3 = " ";
        }
        else
        {
        	pracBillContactName3 = sPracBillContactName3;
        }
	}

	/**
	 * @param pracBillContactName4 the pracBillContactName4 to set
	 */
	public void setPracBillContactName4(String sPracBillContactName4) {
		if (sPracBillContactName4.equals(""))
        {
		 pracBillContactName4 = " ";
        }
        else
        {
        	pracBillContactName4 = sPracBillContactName4;
        }
	}

	/**
	 * @param pracBillContactName5 the pracBillContactName5 to set
	 */
	public void setPracBillContactName5(String sPracBillContactName5) {
		if (sPracBillContactName5.equals(""))
        {
		 pracBillContactName5 = " ";
        }
        else
        {
        	pracBillContactName5 = sPracBillContactName5;
        }
	}

	/**
	 * @param pracBillContactName6 the pracBillContactName6 to set
	 */
	public void setPracBillContactName6(String sPracBillContactName6) {
		if (sPracBillContactName6.equals(""))
        {
		 pracBillContactName6 = " ";
        }
        else
        {
        	pracBillContactName6 = sPracBillContactName6;
        }
	}

	public void setPracBillAddress1(String sPracBillAddress1)
    {
        if (sPracBillAddress1.equals(""))
        {
            pracBillAddress1 = " ";
        }
        else
        {
            pracBillAddress1 = sPracBillAddress1;
        }
    }

    public void setPracBillCity1(String sPracBillCity1)
    {
        if (sPracBillCity1.equals(""))
        {
            pracBillCity1 = " ";
        }
        else
        {
            pracBillCity1 = sPracBillCity1;
        }
    }

    public void setPracBillState1(String sPracBillState1)
    {
        if (sPracBillState1.equals(""))
        {
            pracBillState1 = " ";
        }
        else
        {
            pracBillState1 = sPracBillState1;
        }
    }

    public void setPracBillZip1(String sPracBillZip1)
    {
        if (sPracBillZip1.equals(""))
        {
            pracBillZip1 = " ";
        }
        else
        {
            pracBillZip1 = sPracBillZip1;
        }
    }

    public void setPracBillCounty1(String sPracBillCounty1)
    {
        if (sPracBillCounty1.equals(""))
        {
            pracBillCounty1 = " ";
        }
        else
        {
            pracBillCounty1 = sPracBillCounty1;
        }
    }

    public void setPracBillPhone1(String sPracBillPhone1)
    {
        if (sPracBillPhone1.equals(""))
        {
            pracBillPhone1 = " ";
        }
        else
        {
            pracBillPhone1 = sPracBillPhone1;
        }
    }

    public void setPracBillFax1(String sPracBillFax1)
    {
        if (sPracBillFax1.equals(""))
        {
            pracBillFax1 = " ";
        }
        else
        {
            pracBillFax1 = sPracBillFax1;
        }
    }

    public void setPracNPINo1(String sPracNPINo1)
    {
        	pracNPINo1 = sPracNPINo1;
    }

    public void setBillMedicareGroup1(String sBillMedicareGroup1)
    {
        if (sBillMedicareGroup1.equals(""))
        {
            billMedicareGroup1 = " ";
        }
        else
        {
            billMedicareGroup1 = sBillMedicareGroup1;
        }
    }

    public void setBillMedicareIndividual1(String sBillMedicareIndividual1)
    {
        if (sBillMedicareIndividual1.equals(""))
        {
            billMedicareIndividual1 = " ";
        }
        else
        {
            billMedicareIndividual1 = sBillMedicareIndividual1;
        }
    }

    public void setPracName2(String sPracName2)
    {
        pracName2 = sPracName2;
    }

    public void setPracOfficeAddress2(String sPracOfficeAddress2)
    {
        pracOfficeAddress2 = sPracOfficeAddress2;
    }

    public void setPracOfficeCity2(String sPracOfficeCity2)
    {
        pracOfficeCity2 = sPracOfficeCity2;
    }

    public void setPracOfficeState2(String sPracOfficeState2)
    {
        pracOfficeState2 = sPracOfficeState2;
    }

    public void setPracOfficeZip2(String sPracOfficeZip2)
    {
        pracOfficeZip2 = sPracOfficeZip2;
    }

    public void setPracOfficeCounty2(String sPracOfficeCounty2)
    {
        pracOfficeCounty2 = sPracOfficeCounty2;
    }

    public void setPracOfficePhone2(String sPracOfficePhone2)
    {
        pracOfficePhone2 = sPracOfficePhone2;
    }

    public void setPracOfficeFax2(String sPracOfficeFax2)
    {
        pracOfficeFax2 = sPracOfficeFax2;
    }

    public void setPracOfficeEmail2(String sPracOfficeEmail2)
    {
        pracOfficeEmail2 = sPracOfficeEmail2;
    }

    public void setBillAddressSame2(String sBillAddressSame2)
    {
        billAddressSame2 = sBillAddressSame2;
    }

    public void setPracBillAddress2(String sPracBillAddress2)
    {
        pracBillAddress2 = sPracBillAddress2;
    }

    public void setPracBillCity2(String sPracBillCity2)
    {
        pracBillCity2 = sPracBillCity2;
    }

    public void setPracBillState2(String sPracBillState2)
    {
        pracBillState2 = sPracBillState2;
    }

    public void setPracBillZip2(String sPracBillZip2)
    {
        pracBillZip2 = sPracBillZip2;
    }

    public void setPracBillCounty2(String sPracBillCounty2)
    {
        pracBillCounty2 = sPracBillCounty2;
    }

    public void setPracBillPhone2(String sPracBillPhone2)
    {
        pracBillPhone2 = sPracBillPhone2;
    }

    public void setPracBillFax2(String sPracBillFax2)
    {
        pracBillFax2 = sPracBillFax2;
    }

    public void setPracNPINo2(String sPracNPINo2)
    {
        	pracNPINo2 = sPracNPINo2;
    }
    public void setBillMedicareGroup2(String sBillMedicareGroup2)
    {
        billMedicareGroup2 = sBillMedicareGroup2;
    }

    public void setBillMedicareIndividual2(String sBillMedicareIndividual2)
    {
        billMedicareIndividual2 = sBillMedicareIndividual2;
    }

    public void setPracName3(String sPracName3)
    {
        pracName3 = sPracName3;
    }

    public void setPracOfficeAddress3(String sPracOfficeAddress3)
    {
        pracOfficeAddress3 = sPracOfficeAddress3;
    }

    public void setPracOfficeCity3(String sPracOfficeCity3)
    {
        pracOfficeCity3 = sPracOfficeCity3;
    }

    public void setPracOfficeState3(String sPracOfficeState3)
    {
        pracOfficeState3 = sPracOfficeState3;
    }

    public void setPracOfficeZip3(String sPracOfficeZip3)
    {
        pracOfficeZip3 = sPracOfficeZip3;
    }

    public void setPracOfficeCounty3(String sPracOfficeCounty3)
    {
        pracOfficeCounty3 = sPracOfficeCounty3;
    }

    public void setPracOfficePhone3(String sPracOfficePhone3)
    {
        pracOfficePhone3 = sPracOfficePhone3;
    }

    public void setPracOfficeFax3(String sPracOfficeFax3)
    {
        pracOfficeFax3 = sPracOfficeFax3;
    }

    public void setPracOfficeEmail3(String sPracOfficeEmail3)
    {
        pracOfficeEmail3 = sPracOfficeEmail3;
    }

    public void setBillAddressSame3(String sBillAddressSame3)
    {
        billAddressSame3 = sBillAddressSame3;
    }

    public void setPracBillAddress3(String sPracBillAddress3)
    {
        pracBillAddress3 = sPracBillAddress3;
    }

    public void setPracBillCity3(String sPracBillCity3)
    {
        pracBillCity3 = sPracBillCity3;
    }

    public void setPracBillState3(String sPracBillState3)
    {
        pracBillState3 = sPracBillState3;
    }

    public void setPracBillZip3(String sPracBillZip3)
    {
        pracBillZip3 = sPracBillZip3;
    }

    public void setPracBillCounty3(String sPracBillCounty3)
    {
        pracBillCounty3 = sPracBillCounty3;
    }

    public void setPracBillPhone3(String sPracBillPhone3)
    {
        pracBillPhone3 = sPracBillPhone3;
    }

    public void setPracBillFax3(String sPracBillFax3)
    {
        pracBillFax3 = sPracBillFax3;
    }

    public void setPracNPINo3(String sPracNPINo3)
    {
        	pracNPINo3 = sPracNPINo3;
    }

    public void setBillMedicareGroup3(String sBillMedicareGroup3)
    {
        billMedicareGroup3 = sBillMedicareGroup3;
    }

    public void setBillMedicareIndividual3(String sBillMedicareIndividual3)
    {
        billMedicareIndividual3 = sBillMedicareIndividual3;
    }

    public void setPracName4(String sPracName4)
    {
        pracName4 = sPracName4;
    }

    public void setPracOfficeAddress4(String sPracOfficeAddress4)
    {
        pracOfficeAddress4 = sPracOfficeAddress4;
    }

    public void setPracOfficeCity4(String sPracOfficeCity4)
    {
        pracOfficeCity4 = sPracOfficeCity4;
    }

    public void setPracOfficeState4(String sPracOfficeState4)
    {
        pracOfficeState4 = sPracOfficeState4;
    }

    public void setPracOfficeZip4(String sPracOfficeZip4)
    {
        pracOfficeZip4 = sPracOfficeZip4;
    }

    public void setPracOfficeCounty4(String sPracOfficeCounty4)
    {
        pracOfficeCounty4 = sPracOfficeCounty4;
    }

    public void setPracOfficePhone4(String sPracOfficePhone4)
    {
        pracOfficePhone4 = sPracOfficePhone4;
    }

    public void setPracOfficeFax4(String sPracOfficeFax4)
    {
        pracOfficeFax4 = sPracOfficeFax4;
    }

    public void setPracOfficeEmail4(String sPracOfficeEmail4)
    {
        pracOfficeEmail4 = sPracOfficeEmail4;
    }

    public void setBillAddressSame4(String sBillAddressSame4)
    {
        billAddressSame4 = sBillAddressSame4;
    }

    public void setPracBillAddress4(String sPracBillAddress4)
    {
        pracBillAddress4 = sPracBillAddress4;
    }

    public void setPracBillCity4(String sPracBillCity4)
    {
        pracBillCity4 = sPracBillCity4;
    }

    public void setPracBillState4(String sPracBillState4)
    {
        pracBillState4 = sPracBillState4;
    }

    public void setPracBillZip4(String sPracBillZip4)
    {
        pracBillZip4 = sPracBillZip4;
    }

    public void setPracBillCounty4(String sPracBillCounty4)
    {
        pracBillCounty4 = sPracBillCounty4;
    }

    public void setPracBillPhone4(String sPracBillPhone4)
    {
        pracBillPhone4 = sPracBillPhone4;
    }

    public void setPracBillFax4(String sPracBillFax4)
    {
        pracBillFax4 = sPracBillFax4;
    }

    public void setPracNPINo4(String sPracNPINo4)
    {
        	pracNPINo4 = sPracNPINo4;
    }

    public void setBillMedicareGroup4(String sBillMedicareGroup4)
    {
        billMedicareGroup4 = sBillMedicareGroup4;
    }

    public void setBillMedicareIndividual4(String sBillMedicareIndividual4)
    {
        billMedicareIndividual4 = sBillMedicareIndividual4;
    }

    public void setPracName5(String sPracName5)
    {
        pracName5 = sPracName5;
    }

    public void setPracOfficeAddress5(String sPracOfficeAddress5)
    {
        pracOfficeAddress5 = sPracOfficeAddress5;
    }

    public void setPracOfficeCity5(String sPracOfficeCity5)
    {
        pracOfficeCity5 = sPracOfficeCity5;
    }

    public void setPracOfficeState5(String sPracOfficeState5)
    {
        pracOfficeState5 = sPracOfficeState5;
    }

    public void setPracOfficeZip5(String sPracOfficeZip5)
    {
        pracOfficeZip5 = sPracOfficeZip5;
    }

    public void setPracOfficeCounty5(String sPracOfficeCounty5)
    {
        pracOfficeCounty5 = sPracOfficeCounty5;
    }

    public void setPracOfficePhone5(String sPracOfficePhone5)
    {
        pracOfficePhone5 = sPracOfficePhone5;
    }

    public void setPracOfficeFax5(String sPracOfficeFax5)
    {
        pracOfficeFax5 = sPracOfficeFax5;
    }

    public void setPracOfficeEmail5(String sPracOfficeEmail5)
    {
        pracOfficeEmail5 = sPracOfficeEmail5;
    }

    public void setBillAddressSame5(String sBillAddressSame5)
    {
        billAddressSame5 = sBillAddressSame5;
    }

    public void setPracBillAddress5(String sPracBillAddress5)
    {
        pracBillAddress5 = sPracBillAddress5;
    }

    public void setPracBillCity5(String sPracBillCity5)
    {
        pracBillCity5 = sPracBillCity5;
    }

    public void setPracBillState5(String sPracBillState5)
    {
        pracBillState5 = sPracBillState5;
    }

    public void setPracBillZip5(String sPracBillZip5)
    {
        pracBillZip5 = sPracBillZip5;
    }

    public void setPracBillCounty5(String sPracBillCounty5)
    {
        pracBillCounty5 = sPracBillCounty5;
    }

    public void setPracBillPhone5(String sPracBillPhone5)
    {
        pracBillPhone5 = sPracBillPhone5;
    }

    public void setPracBillFax5(String sPracBillFax5)
    {
        pracBillFax5 = sPracBillFax5;
    }

    public void setPracNPINo5(String sPracNPINo5)
    {
        	pracNPINo5 = sPracNPINo5;
    }

    public void setBillMedicareGroup5(String sBillMedicareGroup5)
    {
        billMedicareGroup5 = sBillMedicareGroup5;
    }

    public void setBillMedicareIndividual5(String sBillMedicareIndividual5)
    {
        billMedicareIndividual5 = sBillMedicareIndividual5;
    }

    public void setPracName6(String sPracName6)
    {
        pracName6 = sPracName6;
    }

    public void setPracOfficeAddress6(String sPracOfficeAddress6)
    {
        pracOfficeAddress6 = sPracOfficeAddress6;
    }

    public void setPracOfficeCity6(String sPracOfficeCity6)
    {
        pracOfficeCity6 = sPracOfficeCity6;
    }

    public void setPracOfficeState6(String sPracOfficeState6)
    {
        pracOfficeState6 = sPracOfficeState6;
    }

    public void setPracOfficeZip6(String sPracOfficeZip6)
    {
        pracOfficeZip6 = sPracOfficeZip6;
    }

    public void setPracOfficeCounty6(String sPracOfficeCounty6)
    {
        pracOfficeCounty6 = sPracOfficeCounty6;
    }

    public void setPracOfficePhone6(String sPracOfficePhone6)
    {
        pracOfficePhone6 = sPracOfficePhone6;
    }

    public void setPracOfficeFax6(String sPracOfficeFax6)
    {
        pracOfficeFax6 = sPracOfficeFax6;
    }

    public void setPracOfficeEmail6(String sPracOfficeEmail6)
    {
        pracOfficeEmail6 = sPracOfficeEmail6;
    }

    public void setBillAddressSame6(String sBillAddressSame6)
    {
        billAddressSame6 = sBillAddressSame6;
    }

    public void setPracBillAddress6(String sPracBillAddress6)
    {
        pracBillAddress6 = sPracBillAddress6;
    }

    public void setPracBillCity6(String sPracBillCity6)
    {
        pracBillCity6 = sPracBillCity6;
    }

    public void setPracBillState6(String sPracBillState6)
    {
        pracBillState6 = sPracBillState6;
    }

    public void setPracBillZip6(String sPracBillZip6)
    {
        pracBillZip6 = sPracBillZip6;
    }

    public void setPracBillCounty6(String sPracBillCounty6)
    {
        pracBillCounty6 = sPracBillCounty6;
    }

    public void setPracBillPhone6(String sPracBillPhone6)
    {
        pracBillPhone6 = sPracBillPhone6;
    }

    public void setPracBillFax6(String sPracBillFax6)
    {
        pracBillFax6 = sPracBillFax6;
    }

    public void setPracNPINo6(String sPracNPINo6)
    {
        	pracNPINo6 = sPracNPINo6;
    }

    public void setBillMedicareGroup6(String sBillMedicareGroup6)
    {
        billMedicareGroup6 = sBillMedicareGroup6;
    }

    public void setBillMedicareIndividual6(String sBillMedicareIndividual6)
    {
        billMedicareIndividual6 = sBillMedicareIndividual6;
    }

    public void setGrpEntityName1(String sGrpEntityName1)
    {
        grpEntityName1 = sGrpEntityName1;
    }

    public void setSpecialty1(String sSpecialty1)
    {
        specialty1 = sSpecialty1;
    }

    public void setTaxID1(String sTaxID1)
    {
        taxID1 = sTaxID1;
    }

    public void setEffectiveDate1(String sEffectiveDate1)
    {
        effectiveDate1 = sEffectiveDate1;
    }

    public void setGrpEntityName2(String sGrpEntityName2)
    {
        grpEntityName2 = sGrpEntityName2;
    }

    public void setSpecialty2(String sSpecialty2)
    {
        specialty2 = sSpecialty2;
    }

    public void setTaxID2(String sTaxID2)
    {
        taxID2 = sTaxID2;
    }

    public void setEffectiveDate2(String sEffectiveDate2)
    {
        effectiveDate2 = sEffectiveDate2;
    }

    public void setGrpEntityName3(String sGrpEntityName3)
    {
        grpEntityName3 = sGrpEntityName3;
    }

    public void setSpecialty3(String sSpecialty3)
    {
        specialty3 = sSpecialty3;
    }

    public void setTaxID3(String sTaxID3)
    {
        taxID3 = sTaxID3;
    }

    public void setEffectiveDate3(String sEffectiveDate3)
    {
        effectiveDate3 = sEffectiveDate3;
    }

    public void setGrpEntityName4(String sGrpEntityName4)
    {
        grpEntityName4 = sGrpEntityName4;
    }

    public void setSpecialty4(String sSpecialty4)
    {
        specialty4 = sSpecialty4;
    }

    public void setTaxID4(String sTaxID4)
    {
        taxID4 = sTaxID4;
    }

    public void setEffectiveDate4(String sEffectiveDate4)
    {
        effectiveDate4 = sEffectiveDate4;
    }

    public void setGrpEntityName5(String sGrpEntityName5)
    {
        grpEntityName5 = sGrpEntityName5;
    }

    public void setSpecialty5(String sSpecialty5)
    {
        specialty5 = sSpecialty5;
    }

    public void setTaxID5(String sTaxID5)
    {
        taxID5 = sTaxID5;
    }

    public void setEffectiveDate5(String sEffectiveDate5)
    {
        effectiveDate5 = sEffectiveDate5;
    }

    

		//WI/MO changes
    public String getMOProviderId()
    {
        return m_moId;
    }

    public void setMOProviderId(String moId)
    {
        m_moId = moId;
    }

    public String getWIProviderId()
    {
        return m_wiId;
    }

    public void setWIProviderId(String wiId)
    {
        m_wiId = wiId;
    }

    public String getGrpMedicaidId()
    {
        return grpMedicaidId;
    }

    public void setGrpMedicaidId(String mcaidId)
    {
        grpMedicaidId = mcaidId;
    }


    public String getGrpNPINumber()
    {
        return m_grpNPINumber;
    }

    public void setGrpNPINumber(String npiNumber)
    {
        m_grpNPINumber = npiNumber;
    }

    public String getProvNPINumber()
    {
        return m_provNPINumber;
    }

    public void setProvNPINumber(String npiNumber)
    {
        m_provNPINumber = npiNumber;
    }

    public String getTaxonomyNumber()
    {
        return m_taxonomyNum;
    }

    public void setTaxonomyNumber(String taxonomyNum)
    {
        m_taxonomyNum = taxonomyNum;
    }

    public String getDBAnthemPIN(){
        String anthemPin ="";

        if (!isEmpty(anthemPIN) ){
            anthemPin = anthemPIN;
        }else if (!isEmpty(m_moId) ){
            anthemPin = m_moId;
        }else if (!isEmpty(m_wiId) ){
            anthemPin = m_wiId;
        }

        return anthemPin;
    }

    /*
      For some reason the code had excessive usage of check for " " .
      It was difficult to get rid of this everywhere without a lot of testing
      So included this as as step before getting rid of this -IK
     */
    public boolean isEmpty(String str)
    {
        //if (str==null || "".equals(str) || " ".equals(str) )   //TEW replaced with line below
        if (str == null || "".equals(str.trim()))
        {
            return true;
        }
        return false;
    }

    private String toLower(String str)
    {
        if (str == null)
        {
            return null;
        }
        else
        {
            return str.toLowerCase();
        }
    }

    
    /**
     * Checks if a valid tax id entered
     * @param taxID
     * @return
     */
    boolean isValidTaxId(String taxID)
    {
        boolean allOk = true;
        if (taxID.length() < 9)
        {
            errorMessagesVectorA.add(Constants.sectionGeneralInfo+
                " Practice Tax ID Number (EIN/SSN) must contain 9 digits.");
            allOk = false;
        }
        else
        {
            boolean numbersOk = validateNumeric(taxID);
            if (!numbersOk)
            {
                errorMessagesVectorA.add(Constants.sectionGeneralInfo+
                    " Practice Tax ID Number (EIN/SSN) must be numeric.");
                allOk = false;
            }
        }
        return allOk;
    }

    boolean isValidPIN()
    {
        boolean valid = true;
        int fieldsWithPin = 0;

        if (!isEmpty(anthemPIN) )
        {
            fieldsWithPin = fieldsWithPin + 1;
        }

        if (!isEmpty(m_moId))
        {
            fieldsWithPin = fieldsWithPin + 1;
        }

        if (!isEmpty(m_wiId))
        {
            fieldsWithPin = fieldsWithPin + 1;
        }

      /*  if (fieldsWithPin == 0)
        {
            errorMessagesVector.add("Provider Id Number is required. (IN, KY and OH or MO or WI)");
            valid = false;
        } else
       */

       if (fieldsWithPin > 1)
        {
            errorMessagesVectorA.add(Constants.sectionGeneralInfo+" Only one Provider Id Number is allowed. (IN, KY and OH or MO or WI)");
            valid = false;
        }

        if (!isEmpty(anthemPIN))
        {
        	if(!validateNumeric(anthemPIN)|| anthemPIN.length() != 12)
        	{
	            errorMessagesVectorA.add(Constants.sectionGeneralInfo+
	                " Anthem Provider Id Number must be numeric and 12 digits in length.");
	            valid = false;
        	}
        }
        else if (!isEmpty(m_moId))
        {
        	if(!validateNumeric(m_moId)|| m_moId.length() != 6)
        	{
	            errorMessagesVectorA.add(Constants.sectionGeneralInfo+
	                " Missouri Provider Id Number must be numeric and 6 digits in length.");
	            valid = false;
        	}
        }
        else if (!isEmpty(m_wiId))
        {
        	if(!validateNumeric(m_wiId)|| m_wiId.length() != 12)
        	{
	            errorMessagesVectorA.add(Constants.sectionGeneralInfo+
	                " Wisconsin Provider Id Number must be numeric and 12 digits in length.");
	            valid = false;
        	}
        }

        return valid;
    }

    /**
     *
     * @param soloGroup
     * @return
     */
    private boolean isValidSoloGroup(String soloGroup)
    {
        boolean allOk = true;
        if (isEmpty(soloGroup))
        {
            errorMessagesVectorA.add(
                "Please select Solo Practice or Group Practice.");
            allOk = false;
        }

        if (soloGroup.equals("G"))
        {
            if (isEmpty(numberInGroup))
            {
                errorMessagesVectorA.add(
                    "Please enter the # of physicians in practice.");
                allOk = false;
            }
            else
            {
                boolean numbersOk = validateNumeric(numberInGroup);
                if (!numbersOk)
                {
                    errorMessagesVectorA.add(Constants.sectionGeneralInfo+
                        " # of physicians in practice must be numeric.");
                    allOk = false;
                }
            }
        }
        else
        {
            if (soloGroup.equals("S"))
            {
                if (!numberInGroup.equals("") && !numberInGroup.equals(" "))
                {
                    errorMessagesVectorA.add(Constants.sectionGeneralInfo+
                        " If # of physicians in practice is greater than 1, must select Group practice.");
                    allOk = false;
                }
            }
        }
        return allOk;
    }

    /**
     *
     * @param effectiveDate
     * @return
     */
    private boolean isValidEffectiveDate(String effectiveDate)
    {
        boolean allOk = true;
        if (isEmpty(effectiveDate))
        {
            allOk = false;
        }
        else
        {
            if (effectiveDate.length() < 8)
            {
                allOk = false;
            }
            else
            {
                boolean dateOk = validateDate(effectiveDate);
                if (!dateOk)
                {
                    allOk = false;
                }
            }
        }
        return allOk;
    }
    
    private boolean isValidEffectiveDateForSecB(String effectiveDate)
    {
        boolean allOk = true;
        if (isEmpty(effectiveDate))
        {
            errorMessagesVectorB.add(Constants.sectionReasonSubmitting+
                " Please enter the effective date of add or change.");
            allOk = false;
        }
        else
        {
            if (effectiveDate.length() < 8)
            {
                errorMessagesVectorB.add(Constants.sectionReasonSubmitting+
                    " Effective date of add or change must contain 8 digits.");
                allOk = false;
            }
            else
            {
                boolean dateOk = validateDate(effectiveDate);
                if (!dateOk)
                {
                    errorMessagesVectorB.add(Constants.sectionReasonSubmitting+
                        " Invalid effective date of add or change.");
                    allOk = false;
                }
            }
        }
        return allOk;
    }
    

    /**
         * This huge function needs to be cleaned. Moved this part out of the main block
     * to start doing this.
     * @return
     */
    private boolean isValidEntries()
    {
        boolean allOk = true;
        boolean dateOk = true;
        boolean numbersOk = true;
        if (isEmpty(pracName1))
        {
            errorMessagesVectorA.add(Constants.sectionGeneralInfo+ " Please enter your Group/Practice Name.");
            allOk = false;
        }

        if (!isEmpty(m_grpNPINumber) ){
            if (m_grpNPINumber.length() != 10 ||
                !validateNumeric(m_grpNPINumber))
            {
                errorMessagesVectorA.add(Constants.sectionGeneralInfo+
                    " group national provider identification number must be numeric and 10 digits in length.");
                
                allOk = false;
            }
        }

        if (isEmpty(addProvider) &&
            isEmpty(delProvider) &&
            isEmpty(addLocation) &&
            isEmpty(addProvToLocation) &&
            isEmpty(chgSpecialty) &&
            isEmpty(chgProvName) &&
            isEmpty(delLocation) &&
            isEmpty(chgOfficeHours) &&
            isEmpty(delProvFromLocation) &&
            isEmpty(chgPracName) &&
            isEmpty(chgPracAddress) &&
            isEmpty(chgPracPhone) &&
            isEmpty(chgTaxID) &&
            isEmpty(chgBillName) &&
            isEmpty(chgBillAddress) &&
            isEmpty(chgBillPhone) &&
            isEmpty(addNPI) &&
            isEmpty(chgNPI)&&
            isEmpty(addAreasOfExpertise) &&
            isEmpty(addPatientInfo))
        {
        	errorMessagesVectorB.add(Constants.sectionReasonSubmitting+
            " Please select at least one reason for submitting this form.");
            allOk = false;
        }
        
        if (!isEmpty(addAreasOfExpertise) && this.getAreasOfExpertise().isEmpty())
            {
                errorMessagesVectorB.add(
                    "Information is incomplete.  You have checked a box in "+ Constants.sectionReasonSubmitting +" that requires you to complete " +Constants.sectionExpertise + ".");
                allOk = false;           
        }
        
        if (!this.getAreasOfExpertise().isEmpty() &&  isEmpty(addAreasOfExpertise))
        {
            errorMessagesVectorJ.add(Constants.sectionExpertise+
                    " Information is invalid.  If this is a change/update to Behavioral Health data, please check \"Add/Update Providers' Self-Reported Areas of Expertise\" in "+ Constants.sectionReasonSubmitting + " before updating this section.");
                allOk = false;           
        }       
        
        if (!isEmpty(addPatientInfo) && this.patientInfo.size()== 0)
            {
                errorMessagesVectorB.add(
                    "Information is incomplete.  You have checked a box in " + Constants.sectionReasonSubmitting + " that requires you to complete " +Constants.sectionPatientInfo + ".");
                allOk = false;
        }
        
        if (this.patientInfo.size()> 0 && isEmpty(addPatientInfo))
            {
                errorMessagesVectorB.add(Constants.sectionPatientInfo+
                    " Information is invalid:  If this is a change/update to Behavioral Health data, please check \"Add/Update Patient Information\" in " + Constants.sectionReasonSubmitting + " before updating this section." );
                allOk = false;
        }

        if (!isEmpty(delProvider))
        {
            if (isEmpty(delReason))
            {
            	errorMessagesVectorB.add(Constants.sectionReasonSubmitting+
                " Delete reason required when deleting provider.");
                allOk = false;
            }
        }

        if (!isEmpty(delReason))
        {
            if (isEmpty(delProvider))
            {
                errorMessagesVectorB.add(Constants.sectionReasonSubmitting+
                    " Must select Deleting Provider when supplying delete reason.");
                allOk = false;
            }
        }

        if (!isEmpty(chgTaxID))
        {
            if (isEmpty(oldTaxID))
            {
                errorMessagesVectorB.add(Constants.sectionReasonSubmitting+
                    " Old Tax ID Number required when selecting Tax ID Change.");
                allOk = false;
            }
        }

        if (!isEmpty(oldTaxID))
        {
            if (isEmpty(chgTaxID))
            {
                errorMessagesVectorB.add(Constants.sectionReasonSubmitting+
                    " Must select Tax ID Change when entering Old Tax ID.");
                allOk = false;
            }
        }

        if (!isEmpty(oldTaxID))
        {
            if (oldTaxID.length() < 9)
            {
                errorMessagesVectorB.add(Constants.sectionReasonSubmitting+ " Old Tax ID must contain 9 digits.");
                allOk = false;
            }
            else
            {
                numbersOk = validateNumeric(oldTaxID);
                if (!numbersOk)
                {
                    errorMessagesVectorB.add(Constants.sectionReasonSubmitting+ " Old Tax ID must be numeric.");
                    allOk = false;
                }
            }
        }

        if (!isEmpty(addProvider))
        {
            if (isEmpty(provFnm))
            {
                errorMessagesVectorC.add(Constants.sectionProviderInfo+
                    " Provider first name required when entering new provider.");
                allOk = false;
            }
            if (isEmpty(provLnm))
            {
                errorMessagesVectorC.add(Constants.sectionProviderInfo+
                    " Provider last name required when entering new provider.");
                allOk = false;
            }
            if (isEmpty(title))
            {
                errorMessagesVectorC.add(Constants.sectionProviderInfo+
                        " Provider title required when entering new provider.");
                allOk = false;
            }
            //PMF Change SSCR 13503 21/6/2012 Start
            if (isEmpty(primSpecialtyPhy) &&
                isEmpty(specialtyCarePhy) &&
                isEmpty(other))
            {
                errorMessagesVectorC.add(Constants.sectionProviderInfo+" please select one of the following specialties is required when adding a new provider.");
                allOk = false;
            }
            if (!isEmpty(primSpecialtyPhy) || !isEmpty(specialtyCarePhy) || !isEmpty(other))
                {
            		if(isEmpty(specialty)){
            			errorMessagesVectorC.add(Constants.sectionProviderInfo+" please choose your appropriate specialty is required.");
            			allOk = false;
            		}
                }
            //PMF Change SSCR 13503 21/6/2012 End
            if (isEmpty(provSSN))
            {
                errorMessagesVectorC.add(Constants.sectionProviderInfo+
                    " Provider Social Security Number required when entering new provider.");
                allOk = false;
            }

            //TEW if (profLicenseNumber.equals("") || profLicenseNumber.equals(" "))
            if (isEmpty(profLicenseNumber))
            {
                errorMessagesVectorC.add(Constants.sectionProviderInfo+
                    " Professional License Number required when entering new provider.");
                allOk = false;
            }
            //TEW if (provDOB.equals("") || provDOB.equals(" "))
            if (isEmpty(provDOB))
            {
                errorMessagesVectorC.add(Constants.sectionProviderInfo+
                    " Provider Date of Birth required when entering new provider.");
                allOk = false;
            }
            //TEW if (provGender.equals("") || provGender.equals(" "))
            if (isEmpty(provGender))
            {
                errorMessagesVectorC.add(
                    "Provider Gender required when entering new provider.");
                allOk = false;
            }
           /*2013 SSCR 13503 changes
            *  if (isEmpty(stateLicIssueDt))
            
            {
                errorMessagesVectorC.add(
                		Constants.sectionProviderInfo+
                        " state license number issue date is a required field.");
                allOk = false;
            }
            if (isEmpty(stateLicExpDt))
            {
            	errorMessagesVectorC.add(
                		Constants.sectionProviderInfo+
                        " state license expiration date is a required field.");
                allOk = false;
            }*/
        }
       
        //TEW added use of isEmpty below.
        

      /* 2013  SSCR 13503 changes
        if (!isEmpty(m_taxonomyNum) ){
            if (m_taxonomyNum.length() != 10 || !isAlphaNumeric(m_taxonomyNum))
            {
                errorMessagesVectorC.add(Constants.sectionProviderInfo+ 
                " Taxonomy Number should be 10 digit in length and Alpha/Numeric.");
                allOk = false;
            }
        }*/
        
        // Changes for the state mandate on 02/10/10 start
        if (!isEmpty(medicaidIndicator) && isEmpty(medicaidPCP) && isEmpty(medicaidSpecialist)){
        	errorMessagesVectorC.add("Information is incomplete.  You have checked the Anthem Medicaid box that requires you to select a provider type.");
        	allOk = false;  
        }
        if (!isEmpty(medicaidPCP)){
        	if (isEmpty(medicaidMaxPanel) ){
        		errorMessagesVectorC.add ("Maximum Panel Capacity is required for the Anthem Medicaid provider type that has been selected.");
        		allOk = false;  
        	}else if(!validateNumeric(medicaidMaxPanel) ){
        		errorMessagesVectorC.add ("Maximum Panel Capacity must be a numeric value.");
        		allOk = false;  
        	} else if (!isValidRange(medicaidMaxPanel, 0,10000)){
        		errorMessagesVectorC.add ("Maximum Panel Capacity may not be greater than 10,000.");
        		allOk = false;  
        	}
        }
        if (!isEmpty(medicaidMaxPanel) && isEmpty(medicaidPCP)){
        	errorMessagesVectorC.add ("In order to enter a value in the Maximum Panel Capacity field you must apply for Anthem Medicaid.");
        	allOk = false;  
        }

        /* SSCR 9711-SSCR9724 change*/
        if (!isEmpty(hipPCP)){
        	if (isEmpty(hipMaxPanel) ){
        		errorMessagesVectorC.add ("Maximum Panel Capacity is required for the Anthem Healthy Indiana Plan provider type that has been selected.");
        		allOk = false;  
        	}else if(!validateNumeric(hipMaxPanel) ){
        		errorMessagesVectorC.add ("Maximum Panel Capacity must be a numeric value.");
        		allOk = false;  
        	} else if (!isValidRange(hipMaxPanel, 0,10000)){
        		errorMessagesVectorC.add ("Maximum Panel Capacity may not be greater than 10,000.");
        		allOk = false;  
        	}
        }

        if (!isEmpty(hipMaxPanel) && isEmpty(hipPCP)){
        	errorMessagesVectorC.add ("In order to enter a value in the Maximum Panel Capacity field you must apply for Anthem Healthy Indiana Plan.");
        	allOk = false;  
        }
        /* SSCR 9711-SSCR9724 change end */
        if (!isEmpty(hipIndicator) && isEmpty(hipPCP) && isEmpty(hipSpecialist)){
        	errorMessagesVectorC.add("Information is incomplete.  You have checked the Anthem Healthy Indiana Plan box that requires you to select a provider type.");
        	allOk = false;  
        }
        // Changes for the state mandate on 02/10/10 end
        //PMF Change SSCR 13503 21/6/2012 Start
        /* PMF Section C Changes -- AD21239 -- Start*/
        if(!isEmpty(indServTypes)&&!isEmpty(ssMedical)){
        	if (isEmpty (indivPractice) && isEmpty(groupPractice) && isEmpty(schoolBasedClinic) && isEmpty(npSupSpec) 
    				&& isEmpty(tribalHealthCenter) && isEmpty(npSupPMP) && isEmpty(fedQualHealthClinic)
    				&& isEmpty (ruralHealthClinic) && isEmpty (retailHealthClinic) && isEmpty(walkInDrOffice)
    				&& isEmpty(urgentCare) && isEmpty(coveringPMP) && isEmpty(certMidwife) && isEmpty(communityHealthCenter) 
    				&& isEmpty(communityHealthCenter) && isEmpty(deptOfHealth) && isEmpty(prenatelCareCoord)
    				&& isEmpty(schoolBasedClinic) && isEmpty(tribalHealthCtr) && isEmpty(clinic) && isEmpty(paSupSpec) && isEmpty(paSupPMP)
    				&& isEmpty(medicationAssistedTreatment) && isEmpty(residentialTreatmentCenter) && isEmpty(substanceUseDisorderAdults) 
    				&& isEmpty(substanceUseDisorderChild) && isEmpty(telehealthProv)){
    			errorMessagesVectorC.add(Constants.sectionProviderInfo+" \"Indicate if you are a\" is required.");
    			allOk = false;  
    		}
        	
        }
        if(!isEmpty(clinic)){
        	if(isEmpty(enrollClinicType)){
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+" a clinic type is required since clinic (type) has been selected.");
    			allOk = false;  
        	}
        }
        //PMF Change SSCR 13503 21/6/2012 end
       
        
        if ((!isEmpty(medicaidIndicator) || !isEmpty(hipIndicator)) && isEmpty(mgdCareDisenroll))
        {
        	errorMessagesVectorC.add("You must answer the managed care entity question in "+ Constants.sectionProviderInfo+" since you selected Anthem Medicaid/Anthem Healthy Indiana in Section C.");
			allOk = false;
        }
        
        
        if ((!isEmpty(medicaidIndicator) || !isEmpty(hipIndicator)) && isEmpty(pmp))
        {
        	errorMessagesVectorC.add("You must answer the Is provider a PMP question in "+ Constants.sectionProviderInfo+" since you selected Anthem Medicaid/Anthem Healthy Indiana in "+Constants.sectionProviderInfo+".");
			allOk = false;
        }
        //PMF Change SSCR 13503 21/6/2012 Start
        if("Y".equals(medicarePartTrad)){
        	if(isEmpty(medicareApplDt)){
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+" Medicare opt out applied date is required.");
    			allOk = false;
        	}
        }
        if(medicareApplDt!=null && !medicareApplDt.equals(" ")){
        if(!(validateDate(medicareApplDt))){
        	if((!medicareApplDt.equalsIgnoreCase("N/A")) && (!medicareApplDt.equalsIgnoreCase("NA")))
        	{
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+" Medicare opt out applied date invalid.");
        		allOk = false;
        	}
        }
        }
        if("Y".equals(medicareOptOut)){
        	if(isEmpty(medicareOptOutDt)){
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+" effective date of medicare opt out is required.");
    			allOk = false;
        	}
        }
        if(medicareOptOutDt!=null && !medicareOptOutDt.equals(" ") ){
        if(!(validateDate(medicareOptOutDt))){
        	if((!medicareOptOutDt.equalsIgnoreCase("N/A")) && (!medicareOptOutDt.equalsIgnoreCase("NA")))
        	{
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+" effective date of medicare opt out invalid");
        		allOk = false;
        	}
        }
        }
        if (!isEmpty(primSpecialtyPhy))
        {
            if (!isEmpty(specialtyCarePhy) || !isEmpty(other))
            {
                errorMessagesVectorC.add(Constants.sectionProviderInfo+" Must enter one only:  Primary Specialty Physician, Specialty Care Physician or Other.");
                allOk = false;
            }
        }
        else
        {
            if (!isEmpty(specialtyCarePhy))
            {
                if (!isEmpty(primSpecialtyPhy) || !isEmpty(other))
                {
                    errorMessagesVectorC.add("Must enter one only:  Primary Specialty Physician, Specialty Care Physician or Other.");
                    allOk = false;
                }
            }
            else
            {
                if (!isEmpty(other))
                {
                    if (!isEmpty(primSpecialtyPhy) || !isEmpty(specialtyCarePhy))
                    {
                        errorMessagesVectorC.add("Must enter one only:  Primary Specialty Physician, Specialty Care Physician or Other.");
                        allOk = false;
                    }
                }
            }
        }
        //PMF Change SSCR 13503 13/6/2012 End
        if("Y".equals(pmp) && isEmpty(pmpSpecialty))
        {
        	errorMessagesVectorC.add("You  must select a PMP specialty in "+Constants.sectionProviderInfo+" since provider is a PMP.");
        	allOk = false;
        } 

        if("Y".equals(pmp) && isEmpty(hospAdmitPriv))
        {
        	errorMessagesVectorC.add("You must answer the hospital admitting privileges question in "+ Constants.sectionProviderInfo+" since provider is a PMP.");
        	
			allOk = false;
        }
        
        if("Y".equals(hospAdmitPriv))
        {
        	if (isEmpty(apHospitalName[0]))
        	{
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+" first admitting name of hospital is required.");
        		allOk = false;
        	}
        	if(isEmpty(apHospitalAddress[0]))
        	{
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+" first admitting hospital address is required.");
        		allOk = false;
        	}
        	if(isEmpty(apHospitalCity[0]))
        	{
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+" first admitting hospital city is required.");
        		allOk = false;
        	}
        	
        	if(isEmpty(apHospitalState[0]))
        	{
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+" first admitting hospital state is required.");
        		allOk = false;
        	}
        	
        	if(isEmpty(apHospitalZip[0]))
        	{
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+" first admitting hospital zip is required.");
        		allOk = false;
        	}
        	
        }
        
        for(int i=0; i<apHospitalZip.length ;i++)
    	{
    		if(!isEmpty(apHospitalZip[i]) )
    		{
    			//PMF Change SSCR 13503 22/6/2012 Start
    			System.out.println("Constants.sectionProviderInfo ::"+Constants.sectionProviderInfo);
    			allOk =	isValidZip(apHospitalZip[i],Constants.sectionProviderInfo+" "+Constants.ORDIANAL_NUMBERS_LOWERCASE[i],"admitting hospital zip",Constants.sectionProviderInfo, allOk);
    			//PMF Change SSCR 13503 22/6/2012 End
    		}
    	}
        //PMF Change SSCR 13503 22/6/2012 Start
        
        if(!isEmpty(fwdHealthCertEffDt1)){
        	if(!isValidEffectiveDate(fwdHealthCertEffDt1)){
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+" First Wisconsin ForwardHealth certification effective date invalid.");
    			allOk = false;
        	}
        }
        if(!isEmpty(fwdHealthCertEffDt2)){
        	if(!isValidEffectiveDate(fwdHealthCertEffDt2)){
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+" Second Wisconsin ForwardHealth certification effective date invalid.");
    			allOk = false;
        	}
        }
        if(!isEmpty(fwdHealthCertEffDt3)){
        	if(!isValidEffectiveDate(fwdHealthCertEffDt3)){
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+" Third Wisconsin ForwardHealth certification effective date invalid.");
    			allOk = false;
        	}
        }
        if(!isEmpty(fwdHealthRecertEffDt1)){
        	if(!isValidEffectiveDate(fwdHealthRecertEffDt1)){
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+" First Wisconsin ForwardHealth recertification effective date invalid.");
    			allOk = false;
        	}
        }
        if(!isEmpty(fwdHealthRecertEffDt2)){
        	if(!isValidEffectiveDate(fwdHealthRecertEffDt2)){
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+" Second Wisconsin ForwardHealth recertification effective date invalid.");
    			allOk = false;
        	}
        }
        if(!isEmpty(fwdHealthRecertEffDt3)){
        	if(!isValidEffectiveDate(fwdHealthRecertEffDt3)){
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+" Third Wisconsin ForwardHealth recertification effective date invalid.");
    			allOk = false;
        	}
        }
        if(!isEmpty(fwdHealthCertNPI1)){
        	if(!validateNumeric(fwdHealthCertNPI1)){
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+" First Wisconsin ForwardHealth certified NPI number must be numeric.");
    			allOk = false;
        	}
        }
        if(!isEmpty(fwdHealthCertNPI2)){
        	if(!validateNumeric(fwdHealthCertNPI2)){
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+" Second Wisconsin ForwardHealth certified NPI number must be numeric.");
    			allOk = false;
        	}
        }
        if(!isEmpty(fwdHealthCertNPI3)){
        	if(!validateNumeric(fwdHealthCertNPI3)){
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+" Third Wisconsin ForwardHealth certified NPI number must be numeric.");
    			allOk = false;
        	}
        }
        if(!isEmpty(fwdHealthCertNPI1)){
        	/* WLPRD00971518 | Fix for Clear Selection | UST Global | 2014 October*/
        	if(!isEmpty(fwdHealthCertNPIBilled1)&& !fwdHealthCertNPIBilled1.equalsIgnoreCase(" ")){
        		if(isEmpty(fwdHealthCertEffDt1) && isEmpty(fwdHealthRecertEffDt1)){
        			errorMessagesVectorC.add(Constants.sectionProviderInfo+" Wisconsin ForwardHealth certification effective date or the recertification date is required.");
        			allOk = false;
        		}else if(!isEmpty(fwdHealthCertEffDt1) && !isEmpty(fwdHealthRecertEffDt1)){
        			errorMessagesVectorC.add(Constants.sectionProviderInfo+
        					" you may not answer both the Wisconsin ForwardHealth certification effective date and recertification date in the same occurrence.");
        			allOk = false;
        		}
        	}
        		if(!isEmpty(fwdHealthCertEffDt1) || !isEmpty(fwdHealthRecertEffDt1)){
        			/* WLPRD00971518 | Fix for Clear Selection | UST Global | 2014 October*/
        			if(isEmpty(fwdHealthCertNPIBilled1)||fwdHealthCertNPIBilled1.equalsIgnoreCase(" ")){
        				errorMessagesVectorC.add(Constants.sectionProviderInfo+" First Wisconsin ForwardHealth how will this NPI be billed is required. ");
        				allOk = false;
        			}
        		}
        	
        	if(fwdHealthCertNPI1.length()!=10){
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+
        				" First Wisconsin ForwardHealth certified NPI number must contain 10 digits.");
        		allOk = false;
        	}
        }
        if(!isEmpty(fwdHealthCertNPI2)){
        	/* WLPRD00971518 | Fix for Clear Selection | UST Global | 2014 October*/
           	if(!isEmpty(fwdHealthCertNPIBilled2) && !fwdHealthCertNPIBilled2.equalsIgnoreCase(" ")){
           		if(isEmpty(fwdHealthCertEffDt2) && isEmpty(fwdHealthRecertEffDt2)){
           			errorMessagesVectorC.add(Constants.sectionProviderInfo+" Second Wisconsin ForwardHealth certification effective date or the recertification date is required.");
           			allOk = false;
           		}else if(!isEmpty(fwdHealthCertEffDt2) && !isEmpty(fwdHealthRecertEffDt2)){
           			errorMessagesVectorC.add(Constants.sectionProviderInfo+
					" you may not answer both the Wisconsin ForwardHealth certification effective date and recertification date in the same occurrence.");
           			allOk = false;
           		}
           	}
           	if(!isEmpty(fwdHealthCertEffDt2) || !isEmpty(fwdHealthRecertEffDt2)){
           		/* WLPRD00971518 | Fix for Clear Selection | UST Global | 2014 October*/
    			if(isEmpty(fwdHealthCertNPIBilled2)||fwdHealthCertNPIBilled2.equalsIgnoreCase(" ")){
    				errorMessagesVectorC.add(Constants.sectionProviderInfo+" Second Wisconsin ForwardHealth how will this NPI be billed is required. ");
    				allOk = false;
    			}
    		}
           	if(fwdHealthCertNPI2.length()!=10){
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+
        				" Second Wisconsin ForwardHealth certified NPI number must contain 10 digits.");
        		allOk = false;
        	}
        }
        if(!isEmpty(fwdHealthCertNPI3)){
        	/* WLPRD00971518 | Fix for Clear Selection | UST Global | 2014 October*/
           	if(!isEmpty(fwdHealthCertNPIBilled3)&& !fwdHealthCertNPIBilled3.equalsIgnoreCase(" ")){
           		if(isEmpty(fwdHealthCertEffDt3) && isEmpty(fwdHealthRecertEffDt3)){
           			errorMessagesVectorC.add(Constants.sectionProviderInfo+" Third Wisconsin ForwardHealth certification effective date or the recertification date is required.");
           			allOk = false;
           		}else if(!isEmpty(fwdHealthCertEffDt3) && !isEmpty(fwdHealthRecertEffDt3)){
           			errorMessagesVectorC.add(Constants.sectionProviderInfo+
					" you may not answer both the Wisconsin ForwardHealth certification effective date and recertification date in the same occurrence.");
           			allOk = false;
           		}
           	}if(!isEmpty(fwdHealthCertEffDt3) || !isEmpty(fwdHealthRecertEffDt3)){
           		/* WLPRD00971518 | Fix for Clear Selection | UST Global | 2014 October*/
    			if(isEmpty(fwdHealthCertNPIBilled3)||fwdHealthCertNPIBilled3.equalsIgnoreCase(" ")){
    				errorMessagesVectorC.add(Constants.sectionProviderInfo+" Third Wisconsin ForwardHealth how will this NPI be billed is required. ");
    				allOk = false;
    			}
    		}
           	if(fwdHealthCertNPI3.length()!=10){
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+
        				" Third Wisconsin ForwardHealth certified NPI number must contain 10 digits.");
        		allOk = false;
        	}
        }
        if(!isEmpty(fwdHealthCertNPI2)){
        	if(isEmpty(fwdHealthCertNPI1)){
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+" Second Wisconsin ForwardHealth information cannot be submitted without first completing the first occurrence.");
           		allOk = false;
        	}
        }
        if(!isEmpty(fwdHealthCertNPI3)){
           if(isEmpty(fwdHealthCertNPI2) || isEmpty(fwdHealthCertNPI1)){
        	   errorMessagesVectorC.add(Constants.sectionProviderInfo+" Third Wisconsin ForwardHealth information cannot be submitted without first completing the first and second occurrence.");
               allOk = false;
         	}
        }
        /* WLPRD00971518 | Fix for Clear Selection | UST Global | 2014 October*/
        if((!isEmpty(fwdHealthCertNPIBilled1)|| !isEmpty(fwdHealthCertEffDt1) || !isEmpty(fwdHealthRecertEffDt1))&& !fwdHealthCertNPIBilled1.equalsIgnoreCase(" ")){
        	if(isEmpty(fwdHealthCertNPI1)){
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+" First Wisconsin ForwardHealth certified NPI number is required.");
                allOk = false;
        	}
        }
        /* WLPRD00971518 | Fix for Clear Selection | UST Global | 2014 October*/
        if((!isEmpty(fwdHealthCertNPIBilled2) || !isEmpty(fwdHealthCertEffDt2) || !isEmpty(fwdHealthRecertEffDt2))&& !fwdHealthCertNPIBilled2.equalsIgnoreCase(" ")){
        	if(isEmpty(fwdHealthCertNPI2)){
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+" Second Wisconsin ForwardHealth certified NPI number is required.");
                allOk = false;
        	}
        }
        /* WLPRD00971518 | Fix for Clear Selection | UST Global | 2014 October*/
        if((!isEmpty(fwdHealthCertNPIBilled3) || !isEmpty(fwdHealthCertEffDt3) || !isEmpty(fwdHealthRecertEffDt3))&& !fwdHealthCertNPIBilled3.equalsIgnoreCase(" ")){
        	if(isEmpty(fwdHealthCertNPI3)){
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+" Third Wisconsin ForwardHealth certified NPI number is required.");
                allOk = false;
        	}
        }
        
        //PMF Change SSCR 13503 22/6/2012 End
        if("Y".equals(pmp) && isEmpty(relationshipPriv))
        {
        	errorMessagesVectorC.add("You must answer the relationship privileges question in "+Constants.sectionProviderInfo+" since provider is a PMP.");
			allOk = false;
        }
       
        
        if("Y".equals(relationshipPriv))
        {
        	
        	if(isEmpty(grpEntityName1))
        	{
        		errorMessagesVectorH.add(Constants.sectionPhysicians+" First Covering Physician group entity name is required.");
        		allOk = false;
        	}
        	if(isEmpty(specialty1))
        	{
        		errorMessagesVectorH.add(Constants.sectionPhysicians+" First Covering Physician specialty is required.");
        		allOk = false;
        	}
        	
        	if(isEmpty(effectiveDate1))
        	{
        		errorMessagesVectorH.add(Constants.sectionPhysicians+" First Covering Physician effective date is required.");
        		allOk = false;
        	}
        	
        	if (isEmpty(cpHospitalAddress[0]))
        	{
        		errorMessagesVectorH.add(Constants.sectionPhysicians+" First Covering Physician address is required.");
        		allOk = false;
        	}
        	if (isEmpty(cpHospitalCity[0])) 
        	{
        		errorMessagesVectorH.add(Constants.sectionPhysicians+" First Covering Physician city is required.");
        		allOk = false;
        	}
        	if (isEmpty(cpHospitalState[0])) 
        	{
        		errorMessagesVectorH.add(Constants.sectionPhysicians+" First Covering Physician state is required.");
        		allOk = false;
        	}
        	if (isEmpty(cpHospitalZip[0])) 
        	{
        		errorMessagesVectorH.add(Constants.sectionPhysicians+" First Covering Physician zip is required.");
        		allOk = false;
        	}
        	
        }
        for(int i=0; i<cpHospitalZip.length ;i++)
     	{
     		if( !isEmpty(cpHospitalZip[i]) )
     		{
     			//PMF Change SSCR 13503 22/6/2012 Start
     			allOk = isValidZip(cpHospitalZip[i],Constants.sectionPhysicians+" "+Constants.ORDIANAL_NUMBERS[i],"Covering Physician Zip",Constants.sectionPhysicians, allOk);
     			//PMF Change SSCR 13503 22/6/2012 End
     		}
     	}
    	
        
        if("Y".equals(pmp) && isEmpty(deliveryPriv) && ("OB/GYN".equals(pmpSpecialty)))
        {
        	errorMessagesVectorC.add("You must answer the delivery privileges question in "+Constants.sectionProviderInfo+" since provider is a PMP and specialty selected as OB/GYN.");
			allOk = false;
        }
        
        if("Y".equals(deliveryPriv))
        {
        	if (isEmpty(dpHospitalAddress[0]))
        	{
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+ " first delivery name of hospital is required.");
        		allOk = false;
        	}
        	if (isEmpty(dpHospitalAddress[0]))
        	{
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+ " first delivery hospital address is required.");
        		allOk = false;
        	}
        	if (isEmpty(dpHospitalCity[0]))
        	{
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+ " first delivery hospital city is required.");
        		allOk = false;
        	}
        	if (isEmpty(dpHospitalState[0]))
        	{
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+ " first delivery hospital state is required.");
        		allOk = false;
        	}
        	if (isEmpty(dpHospitalZip[0]))
        	{
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+ " first delivery hospital zip is required.");
        		allOk = false;
        	}

        }
        
        for(int i=0; i<dpHospitalZip.length ;i++)
    	{
    		if(!isEmpty(dpHospitalZip[i]))
    		{
    			//PMF Change SSCR 13503 22/6/2012 Start
    			allOk = isValidZip(dpHospitalZip[i],Constants.sectionProviderInfo+" "+Constants.ORDIANAL_NUMBERS_LOWERCASE[i],"delivery hospital zip",Constants.sectionProviderInfo, allOk);
    			//PMF Change SSCR 13503 22/6/2012 End
    		}
    	}
        
        if("Y".equals(pmp) && !isEmpty(medicaidIndicator))
        {
        	if (isEmpty(medPanelStatus))
        	{
	        	errorMessagesVectorC.add("You must answer the Anthem Medicaid panel status question in "+Constants.sectionProviderInfo+" since provider is a PMP.");
				allOk = false;
        	}
        	if (isEmpty(medNbrLocations))
        	{
        		errorMessagesVectorC.add("You must answer the Anthem Medicaid number of service locations question in "+Constants.sectionProviderInfo +" since provider is a PMP.");
				allOk = false;
        	}
        	
        }
       
        if("Y".equals(pmp) && !isEmpty(hipIndicator))
        {
        	if (isEmpty(hipPanelStatus))
        	{
	        	errorMessagesVectorC.add("You  must answer the Anthem Healthy Indiana Plan panel status question in "+Constants.sectionProviderInfo +" since provider is a PMP.");
				allOk = false;
        	}
        	
        	if (isEmpty(hipNbrLocations))
        	{
        		errorMessagesVectorC.add("You must answer the Anthem Healthy Indiana Plan number of service locations question in "+Constants.sectionProviderInfo +" since provider is a PMP.");
				allOk = false;
        	}
        }
        
        if (isEmpty(ageRestriction))
    	{
    		errorMessagesVectorC.add(Constants.sectionProviderInfo +" Age Restriction is required.");
			allOk = false;
    	}
        // error message changes 21/06/2012
        if (!isEmpty(medNbrLocations) && !validateNumeric(medNbrLocations))
        {
            errorMessagesVectorC.add(
            		Constants.sectionProviderInfo + " Anthem Medicaid number of service locations invalid.");
            allOk = false;
        }
        
        if (!isEmpty(hipNbrLocations) && !validateNumeric(hipNbrLocations))
        {
            errorMessagesVectorC.add(
            		Constants.sectionProviderInfo + " Anthem Healthy Indiana Plan number of service locations invalid.");
            allOk = false;
        }
        //TODO remove if no update recvd from business on range & validate numeric
        if("Y".equals(medPldPanelDecrease))
        {
        	if (isEmpty(medPldPlacePanelAt))
        	{
        		errorMessagesVectorC.add(Constants.sectionProviderInfo +" Anthem Medicaid place panel at question in the panel limit decrease section is required.");
				allOk = false;
        	}
        	else if (!validateNumeric(medPldPlacePanelAt))
            {
                errorMessagesVectorC.add(
                		Constants.sectionProviderInfo + " Anthem Medicaid place panel at question in the panel limit decrease must be numeric.");
                allOk = false;
            }
            
        	else if (!isValidRange(medPldPlacePanelAt, 1,10000))
            {
        		errorMessagesVectorC.add (Constants.sectionProviderInfo+" Anthem Medicaid place panel at must be 1 through 10000.");
        		allOk = false;  
        	}
        	
        	if (isEmpty(medPldGrpMedicaidNo))
        	{
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+" the Anthem Medicaid group medicaid number question in the panel limit decrease section is required.");
				allOk = false;
        	}
        }
        else{
	        if (StringUtils.isNotEmpty(medPldPlacePanelAt)&& ( !isNumeric(medPldPlacePanelAt)  || !isValidRange(medPldPlacePanelAt, 1,10000)))
	        {
	        	
	    		errorMessagesVectorC.add (Constants.sectionProviderInfo+" Anthem Medicaid place panel at must be 1 through 10000.");
	    		allOk = false;  
	    	}
        }
        
      //TODO remove if no update recvd from business on range & validate numeric
        if("Y".equals(hipPldPanelDecrease))
        {
        	if (isEmpty(hipPldPlacePanelAt))
        	{
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+" Anthem Healthy Indiana Plan place panel at question in the panel limit decrease section is required.");
				allOk = false;
        	}
        	else if (!validateNumeric(hipPldPlacePanelAt))
            {
                errorMessagesVectorC.add(
                		Constants.sectionProviderInfo +" Anthem Healthy Indiana Plan place panel at must be 1 through 10000.");
                allOk = false;
            }
            
        	else if (!isValidRange(hipPldPlacePanelAt, 1,10000))
            {
        		errorMessagesVectorC.add (Constants.sectionProviderInfo +"  Anthem Healthy Indiana Plan place panel at must be 1 through 10000.");
        		allOk = false;  
        	}
        	if (isEmpty(hipPldGrpMedicaidNo))
        	{
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+" the Anthem Healthy Indiana Plan group medicaid number question in the panel limit decrease section is required.");
				allOk = false;
        	}
        }
        else{
	        if (StringUtils.isNotEmpty(hipPldPlacePanelAt)&& ( !isNumeric(hipPldPlacePanelAt)  || !isValidRange(hipPldPlacePanelAt, 1,10000)))
	        {
	    		errorMessagesVectorC.add (Constants.sectionProviderInfo +"  Anthem Healthy Indiana Plan place panel at must be 1 through 10000.");
	    		allOk = false;  
	    	}
        }
        
        //TODO remove if no update recvd from business on range & validate numeric(t1.42)
        if("Y".equals(medPliPanelIncrease))
        {
        	if (isEmpty(medPliPlacePanelAt))
        	{
        		errorMessagesVectorC.add(
                		Constants.sectionProviderInfo +" Anthem Medicaid place panel at question in the panel limit increase section is required.");
				allOk = false;
        	}
        	else if (!validateNumeric(medPliPlacePanelAt))
            {
                errorMessagesVectorC.add(
                		Constants.sectionProviderInfo + " Anthem Medicaid place panel at question in the panel limit increase section must be numeric.");
                allOk = false;
            }
        	else if (!isValidRange(medPliPlacePanelAt, 1,10000))
            {
        		errorMessagesVectorC.add (Constants.sectionProviderInfo+" Anthem Medicaid place panel at must be 1 through 10000.");
        		allOk = false;  
        	}
        	if (isEmpty(medPliGrpMedicaidNbr))
        	{
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+" the Anthem Medicaid group medicaid number question in the panel limit increase section is required.");
				allOk = false;
        	}
        }
        else{
	        if (StringUtils.isNotEmpty(medPliPlacePanelAt) && (!isNumeric(medPliPlacePanelAt) || !isValidRange(medPliPlacePanelAt, 1,10000)))
		        {
		    		errorMessagesVectorC.add (Constants.sectionProviderInfo+" Anthem Medicaid place panel at must be 1 through 10000.");
		    		allOk = false;  
		    	}
        }
        
        if("Y".equals(hipPliPanelIncrease))
        {
        	if (isEmpty(hipPliPlacePanelAt))
        	{
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+" Anthem Healthy Indiana Plan place panel at question in the panel limit increase section is required.");
				allOk = false;
        	}
        	else if (!validateNumeric(hipPliPlacePanelAt))
            {
                errorMessagesVectorC.add(
                		Constants.sectionProviderInfo + " Anthem Healthy Indiana Plan place panel at question in the panel limit increase section must be numeric.");
                allOk = false;
            }
            
        	else if (!isValidRange(hipPliPlacePanelAt, 1,10000))
            {
        		errorMessagesVectorC.add (Constants.sectionProviderInfo+" Anthem Healthy Indiana Plan place panel at must be 1 through 10000.");
        		allOk = false;  
        	}
        	if (isEmpty(hipPliGrpMedicaidNbr))
        	{
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+" the Anthem Healthy Indiana Plan group medicaid number question in the panel limit increase section is required.");
				allOk = false;
        	}
        	
        }
        else{
	        if (StringUtils.isNotEmpty(hipPliPlacePanelAt) && (!isNumeric(hipPliPlacePanelAt)|| !isValidRange(hipPliPlacePanelAt, 1,10000)))
	        {
	    		errorMessagesVectorC.add (Constants.sectionProviderInfo+" Anthem Healthy Indiana Plan place panel at must be 1 through 10000.");
	    		allOk = false;  
	    	}
        }
        
        if("Y".equals(medPanelHold))
        {
        	if (isEmpty(medPlrPanelHold))
        	{
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+" the Anthem Medicaid group medicaid number question in the panel hold section is required.");
				allOk = false;
        	}
        }
        
        if("Y".equals(hipPanelHold))
        {
        	if (isEmpty(hipPlrPanelHold))
        	{
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+" the Anthem Healthy Indiana Plan group medicaid number question in the panel hold section is required.");
				allOk = false;
        	}
        }
        
        if("Y".equals(medPanelHoldRemove))
        {
        	if (isEmpty(medPlrPanelHoldRemove))
        	{
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+" the Anthem Medicaid group medicaid number question in the panel hold remove section is required.");
				allOk = false;
        	}
        }
        
        if("Y".equals(hipPanelHoldRemove))
        {
        	if (isEmpty(hipPlrPanelHoldRemove))
        	{
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+" the Anthem Healthy Indiana Plan group medicaid number question in the panel hold remove section is required.");
				allOk = false;
        	}
        }
        
        
        /* if (!isEmpty(clinic))
        {
        	if (isEmpty(enrollClinicType))
        	{
        		errorMessagesVectorC.add(Constants.sectionProviderInfo+" a clinic type is required since clinic (type) has been selected.");
				allOk = false;
        	}
        }
        2013  SSCR 13503 changes
        if (!isEmpty(stateLicIssueDt) && !validateDate(stateLicIssueDt))
		{
        	errorMessagesVectorC.add(Constants.sectionProviderInfo+" state license number issue date invalid.");
			allOk = false;
		}
        if (!isEmpty(stateLicExpDt) && !validateDate(stateLicExpDt))
		{
        	errorMessagesVectorC.add(Constants.sectionProviderInfo+" state license number expiration date invalid.");
			allOk = false;
		}*/
        if(!isEmpty(profLiabilityCarrierLimit) && profLiabilityCarrierLimit.indexOf('.') > 0)
        {
        	errorMessagesVectorC.add(Constants.sectionProviderInfo+" professional liability coverage limits contains invalid character.");
			allOk = false;
        }
        if (!isEmpty(profLiabilityExpDate) && !validateDate(profLiabilityExpDate))
		{
        	errorMessagesVectorC.add(Constants.sectionProviderInfo+" professional liability expiration date invalid.");
			allOk = false;
		}
         
        
        if (!provSSN.equals("") && !provSSN.equals(" "))
        {
            if (provSSN.length() < 9)
            {
                errorMessagesVectorC.add(
                    "Provider Social Security Number must contain 9 digits.");
                allOk = false;
            }
            else
            {
                numbersOk = validateNumeric(provSSN);
                if (!numbersOk)
                {
                    errorMessagesVectorC.add(Constants.sectionProviderInfo+
                        " Provider Social Security Number must be numeric.");
                    allOk = false;
                }
            }
        }
        
		if (isEmpty(m_provNPINumber)) {
			errorMessagesVectorC
					.add(Constants.sectionProviderInfo
							+ " national provider identification number is a required field.");
			allOk = false;
		}
		else if (!isEmpty(m_provNPINumber) ){
           if ( m_provNPINumber.length() != 10 ||
                !validateNumeric(m_provNPINumber))
           {
               errorMessagesVectorC.add(Constants.sectionProviderInfo+
                    " national provider identification number must be numeric and 10 digits in length.");
               allOk = false;
           }
        }


        if (!provDOB.equals("") && !provDOB.equals(" "))
        {
            if (provDOB.length() < 8)
            {
                errorMessagesVectorC.add(Constants.sectionProviderInfo+
                    " Provider Date of Birth must contain 8 digits.");
                allOk = false;
            }
            else
            {
                dateOk = validateDate(provDOB);
                if (!dateOk)
                {
                    errorMessagesVectorC.add(Constants.sectionProviderInfo+" Invalid provider Date of Birth.");
                    allOk = false;
                }
            }
        }

        if (!pracOfficePhone1.equals("") && !pracOfficePhone1.equals(" "))
        {
            numbersOk = validatePhoneNumber(pracOfficePhone1);
            if (!numbersOk)
            {
                errorMessagesVectorE.add(
                    Constants.sectionPracticeAddress+" office phone number must be numeric and 10 digit in length.");
                allOk = false;
            }
        }

        if (!pracOfficeFax1.equals("") && !pracOfficeFax1.equals(" "))
        {
            numbersOk = validatePhoneNumber(pracOfficeFax1);
            if (!numbersOk)
            {
                errorMessagesVectorE.add(
                    Constants.sectionPracticeAddress + " office fax number must be numeric and 10 digit in length.");
                allOk = false;
            }
        }

        if (!pracBillPhone1.equals("") && !pracBillPhone1.equals(" "))
        {
            numbersOk = validatePhoneNumber(pracBillPhone1);
            if (!numbersOk || pracBillPhone1.length()!=10)
            {
                errorMessagesVectorE.add(
                		Constants.sectionPracticeAddress+" phone number for payment must be numeric and 10 digit in length.");
                allOk = false;
            }
        }

        if (!pracBillFax1.equals("") && !pracBillFax1.equals(" "))
        {
            numbersOk = validatePhoneNumber(pracBillFax1);
            if (!numbersOk || pracBillFax1.length()!=10)
            {
                errorMessagesVectorE.add(
                		Constants.sectionPracticeAddress+" fax number for payment must be numeric and 10 digit in length.");
                allOk = false;
            }
        }
        //      Changes for the state mandate on 02/10/10 start
        if (!isEmpty(medicaidIndicator)){
        	if(!isEmpty(pracOfficeAddress1) || !isEmpty(pracBillAddress1)){
        		if((isEmpty(offerECI1) || isEmpty(offerEPSDT1) || 
        				isEmpty(provideADB1) || isEmpty(provideCSHCN1)) ) { 
        			errorMessagesVectorC.add("You must answer the IN Anthem Medicaid questions in " + Constants.sectionPracticeAddress + " since you selected Anthem Medicaid in " + Constants.sectionProviderInfo + ".");
        			allOk = false;  
        		}
        		if(isEmpty(medicaidGroup1) || isEmpty(medicaidIndividual1)) { 
        			errorMessagesVectorE.add("You must answer the Medicaid Group Number and Medicaid Individual Number questions in " + Constants.sectionPracticeAddress + " since you selected Anthem Medicaid in " + Constants.sectionProviderInfo + ".");
        			allOk = false;  
        		}
        	}
        }
        if (!isEmpty(hipIndicator) && (!isEmpty(pracOfficeAddress1) || !isEmpty(pracBillAddress1)) && (isEmpty(medicaidGroup1) || isEmpty(medicaidIndividual1))) { 
        		errorMessagesVectorC.add("You must answer the Medicaid Group Number and Medicaid Individual Number questions in " + Constants.sectionPracticeAddress + " since you selected Anthem Healthy Indiana Plan in " + Constants.sectionProviderInfo + ".");
        		allOk = false;  
        	
        }
        
       /*if (((!isEmpty(hipPCP)) || (!isEmpty(medicaidPCP))) && (isEmpty(timeZoneOfficeHrs[0])))
       	{
        		errorMessagesVector.add("You must answer the Office Hours questions in Section E since you selected Anthem Medicaid/Anthem Healthy Indiana in Section C.");
        		allOk = false;
        }*/
        //      Changes for the state mandate on 02/10/10 end
        if(!isEmpty(pracNPINo1)){
        	if(!validateNumeric(pracNPINo1) || pracNPINo1.length()!=10){
        		errorMessagesVectorE.add(
            		Constants.sectionPracticeAddress+" national provider identification number must be numeric and 10 digits in length.");
        		allOk = false;
        	}
        }
        if(!isEmpty(pracNPINo2)){
            if(!validateNumeric(pracNPINo2) || pracNPINo2.length()!=10){
            	errorMessagesVectorF.add(
                		Constants.sectionAddressChange+" national provider identification number must be numeric and 10 digits in length.");
                allOk = false;
            }
        }
        if(!isEmpty(pracNPINo3)){
            if(!validateNumeric(pracNPINo3) || pracNPINo3.length()!=10){
            	errorMessagesVectorG.add(
                		Constants.sectionOfficeLocation+" second practice national provider identification number must be numeric and 10 digits in length.");
                allOk = false;
            }
        }
        if(!isEmpty(pracNPINo4)){
            if(!validateNumeric(pracNPINo4) || pracNPINo4.length()!=10){
            	errorMessagesVectorG.add(
                		Constants.sectionOfficeLocation+" third practice national provider identification number must be numeric and 10 digits in length.");
                allOk = false;
            }
        }
        if(!isEmpty(pracNPINo5)){
            if(!validateNumeric(pracNPINo5) || pracNPINo5.length()!=10){
            	errorMessagesVectorG.add(
                		Constants.sectionOfficeLocation+" fourth practice national provider identification number must be numeric and 10 digits in length.");
                allOk = false;
            }
        }
        if(!isEmpty(pracNPINo6)){
            if(!validateNumeric(pracNPINo6) || pracNPINo6.length()!=10){
            	errorMessagesVectorG.add(
                		Constants.sectionOfficeLocation+" fifth practice national provider identification number must be numeric and 10 digits in length.");
                allOk = false;
            }
        }
        
        if (isEmpty(matWaiveredPrescriber1) || isEmpty(certOpioidTreat1) || isEmpty(matOpioid1) 
				 || isEmpty(counselOpioid1) || isEmpty(sudProv1) || isEmpty(resTreatCtr1)) {
			//from section C
			if (!isEmpty(medicationAssistedTreatment) && medicationAssistedTreatment.equals("Y")) {
				errorMessagesVectorE.add("If Medication Assisted Treatment (MAT) is selected in Section C, Medication Assisted Treatment (MAT) questions are required in "
						+ Constants.sectionPracticeAddress + ".");
				allOk = false;
			}
			//from section J
			if (areasOfExpertise.isCounselOpioid() || areasOfExpertise.isMatOpioid() 
					 || areasOfExpertise.isMatWaivered()) {
				errorMessagesVectorE.add("If Medication Assisted Treatment (MAT) is selected in Section J, Medication Assisted Treatment (MAT) questions are required in "
						+ Constants.sectionPracticeAddress + ".");
				allOk = false;
			}
		}
        
        if (isEmpty(provideTelehealth1)) {
			//from section C
        	if (!isEmpty(telehealthProv)) {
				errorMessagesVectorE.add("If Telehealth Provider is selected in Section C, the Telehealth Services question is required in "
						+ Constants.sectionPracticeAddress + ".");
				allOk = false;
			}
			//from section J
			if (areasOfExpertise.isTelehealthProv()) {
				errorMessagesVectorE.add("If Telehealth Provider is selected in Section J, the Telehealth Services question is required in "
						+ Constants.sectionPracticeAddress + ".");
				allOk = false;
			}
		}
        
        boolean  bPracOfficeInfoEntered = false;
        String msgPart1 = "If Practice Information is entered in " + Constants.sectionAddressChange + ",";
        if (!isEmpty(pracOfficeAddress2) || (!isEmpty(pracOfficeCity2) ||!isEmpty(pracOfficeState2) ||
        		!isEmpty(pracOfficeZip2) ||!isEmpty(pracOfficeCounty2) ||!isEmpty(pracOfficePhone2) ||
        		!isEmpty(pracOfficeFax2) ||!isEmpty(pracOfficeEmail2) ||!isEmpty(languagesSpoken2)  ||
        		/*!isEmpty(daysOpenMon[1]) ||!isEmpty(daysOpenTue[1]) || !isEmpty(daysOpenWed[1]) ||
        		!isEmpty(daysOpenThu[1]) ||!isEmpty(daysOpenFri[1]) || !isEmpty(daysOpenSat[1]) ||!isEmpty(daysOpenSun[1]) || */
        		!isEmpty(pracBillContactName2) || !isEmpty(pracBillAddress2) || !isEmpty(pracBillCity2) ||!isEmpty(pracBillState2) ||
        		!isEmpty(pracBillZip2) ||!isEmpty(pracBillCounty2) || !isEmpty(pracBillPhone2) ||
        		!isEmpty(pracBillFax2) ||!isEmpty(pracBillContactEmail2) ||!isEmpty(pracNPINo2) ||!isEmpty(billMedicareGroup2) ||
        		!isEmpty(billMedicareIndividual2) ||!isEmpty(medicaidGroup2) ||!isEmpty(medicaidIndividual2) ||
        		!isEmpty(kyMedicaidId2) ||!isEmpty(kyMedicaidPart2))) {
        	bPracOfficeInfoEntered = true;
        }
        /*SSCR 9711-SSCR9724 change start*/
		if (bPracOfficeInfoEntered && isEmpty(pracOfficeAddress2)) {
			errorMessagesVectorF.add(msgPart1+ " Practice Address is required.");
			allOk = false;
		}
		if (bPracOfficeInfoEntered && isEmpty(pracOfficePhone2)) {
			errorMessagesVectorF.add(msgPart1+ " office phone number is required.");
			allOk = false;
		}
		if (bPracOfficeInfoEntered && isEmpty(pracOfficeFax2)) {
			errorMessagesVectorF.add(msgPart1+ " office fax number is required.");
			allOk = false;
		}
		if (bPracOfficeInfoEntered && isEmpty(pracOfficeEmail2)) {
			errorMessagesVectorF.add(msgPart1+ " office email address is required.");
			allOk = false;
		}
		
		if (bPracOfficeInfoEntered && isEmpty(pracBillContactName2)) {
			errorMessagesVectorF.add(msgPart1+ " contact name for payment is required.");
			allOk = false;
		}
		if (bPracOfficeInfoEntered && isEmpty(pracBillPhone2)) {
			errorMessagesVectorF.add(msgPart1+ " phone number for payment is required.");
			allOk = false;
		}
		if (bPracOfficeInfoEntered && isEmpty(pracBillFax2)) {
			errorMessagesVectorF.add(msgPart1+ " fax number for payment is required.");
			allOk = false;
		}
		if (bPracOfficeInfoEntered && isEmpty(pracBillContactEmail2)) {
			errorMessagesVectorF.add(msgPart1+ " email address for payment is required.");
			allOk = false;
		}
		if(!isEmpty(pracOfficeZip2)){
        	allOk = isValidZip(pracOfficeZip2, Constants.sectionAddressChange, "Practice zip code", Constants.sectionAddressChange, allOk);
        }
		if(!isEmpty(pracBillZip2)){
        	allOk = isValidZip(pracBillZip2, Constants.sectionAddressChange, "zip code for payment", Constants.sectionAddressChange, allOk);
        }
	    /*SSCR 9711-SSCR9724 change end*/
	        
        if (!pracOfficePhone2.equals("") && !pracOfficePhone2.equals(" "))
        {
            numbersOk = validatePhoneNumber(pracOfficePhone2);
            if (!numbersOk)
            {
                errorMessagesVectorF.add(
                		Constants.sectionAddressChange + " office phone number must be numeric and 10 digit in length.");
                allOk = false;
            }
        }

        if (!pracOfficeFax2.equals("") && !pracOfficeFax2.equals(" "))
        {
            numbersOk = validatePhoneNumber(pracOfficeFax2);
            if (!numbersOk)
            {
                errorMessagesVectorF.add(
                		Constants.sectionAddressChange + " office fax number must be numeric and 10 digit in length.");
                allOk = false;
            }
        }

        if (!pracBillPhone2.equals("") && !pracBillPhone2.equals(" "))
        {
            numbersOk = validatePhoneNumber(pracBillPhone2);
            if (!numbersOk || pracBillPhone2.length()!=10)
            {
                errorMessagesVectorF.add(
                		Constants.sectionAddressChange + " phone number for payment must be numeric and 10 digit in length.");
                allOk = false;
            }
        }

        if (!pracBillFax2.equals("") && !pracBillFax2.equals(" "))
        {
            numbersOk = validatePhoneNumber(pracBillFax2);
            if (!numbersOk || pracBillFax2.length()!=10)
            {
                errorMessagesVectorF.add(
                		Constants.sectionAddressChange + " fax number for payment must be numeric and 10 digit in length.");
                allOk = false;
            }
        }
        /*SSCR 9711-SSCR9724 change */
		if (bPracOfficeInfoEntered && "9999999999".equals(pracOfficePhone2)) {
			errorMessagesVectorF.add(Constants.sectionAddressChange
					+ " office phone number is invalid.");
			allOk = false;
		}
		if (bPracOfficeInfoEntered && "9999999999".equals(pracOfficeFax2)) {
			errorMessagesVectorF.add(Constants.sectionAddressChange
					+ " office fax number is invalid.");
			allOk = false;
		}
		if (bPracOfficeInfoEntered && "9999999999".equals(pracBillPhone2)) {
			errorMessagesVectorF.add(Constants.sectionAddressChange
					+ " is invalid.");
			allOk = false;
		}
		if (bPracOfficeInfoEntered && "9999999999".equals(pracBillFax2)) {
			errorMessagesVectorF.add(Constants.sectionAddressChange
					+ " fax number for payment is invalid.");
			allOk = false;
		}
        /*SSCR 9711-SSCR9724 change end*/
		if (bPracOfficeInfoEntered && (isEmpty(matWaiveredPrescriber2) || isEmpty(certOpioidTreat2) || isEmpty(matOpioid2) 
				 || isEmpty(counselOpioid2) || isEmpty(sudProv2) || isEmpty(resTreatCtr2))) {
			//from section C
			if (!isEmpty(medicationAssistedTreatment) && medicationAssistedTreatment.equals("Y")) {
				errorMessagesVectorF.add("If Medication Assisted Treatment (MAT) is selected in Section C, Medication Assisted Treatment (MAT) questions are required in "
						+ Constants.sectionAddressChange + ".");
				allOk = false;
			}
			if (areasOfExpertise.isCounselOpioid() || areasOfExpertise.isMatOpioid() 
					 || areasOfExpertise.isMatWaivered()) {
				errorMessagesVectorF.add("If Medication Assisted Treatment (MAT) is selected in Section J, Medication Assisted Treatment (MAT) questions are required in "
						+ Constants.sectionAddressChange + ".");
				allOk = false;
			}
		}
		
		if (bPracOfficeInfoEntered && isEmpty(provideTelehealth2)) {
			//from section C
        	if (!isEmpty(telehealthProv)) {
				errorMessagesVectorF.add("If Telehealth Provider is selected in Section C, the Telehealth Services question is required in "
						+ Constants.sectionAddressChange + ".");
				allOk = false;
			}
			//from section J
			if (areasOfExpertise.isTelehealthProv()) {
				errorMessagesVectorF.add("If Telehealth Provider is selected in Section J, the Telehealth Services question is required in "
						+ Constants.sectionAddressChange + ".");
				allOk = false;
			}
		}

		//IN RFS2 Edits
		boolean isMandatory = (StringUtils.isNotEmpty(hipIndicator) || StringUtils.isNotEmpty(medicaidIndicator)) && bPracOfficeInfoEntered;
		isOfficeHoursOk = editOfficeHours(isMandatory, Constants.sectionAddressChange, 1);

        //      Changes for the state mandate on 02/10/10 start
        if (!isEmpty(medicaidIndicator)){
        	if(!isEmpty(pracOfficeAddress2) || !isEmpty(pracBillAddress2)){
        		if (isEmpty(offerECI2) || isEmpty(offerEPSDT2) 
        				|| isEmpty(provideADB2)	|| isEmpty(provideCSHCN2)) { 
        			errorMessagesVectorF.add("You must answer the IN Anthem Medicaid questions in "  + Constants.sectionAddressChange + " since you selected Anthem Medicaid in " + Constants.sectionProviderInfo + ".");
        			allOk = false; 
        		}
            	if(isEmpty(medicaidGroup2) || isEmpty(medicaidIndividual2)) { 
            		errorMessagesVectorF.add("You must answer the Medicaid Group Number and Medicaid Individual Number questions in " + Constants.sectionAddressChange + " since you selected Anthem Medicaid in " + Constants.sectionProviderInfo + ".");
            		allOk = false;  
            	}
        	}
        }
        if (!isEmpty(hipIndicator) && (!isEmpty(pracOfficeAddress2) || !isEmpty(pracBillAddress2)) && (isEmpty(medicaidGroup2) || isEmpty(medicaidIndividual2))) { 
    		errorMessagesVectorF.add("You must answer the Medicaid Group Number and Medicaid Individual Number questions in " + Constants.sectionAddressChange + " since you selected Anthem Healthy Indiana Plan in " + Constants.sectionProviderInfo + ".");
    		allOk = false;  
    	
        }
        //      Changes for the state mandate on 02/10/10 end
        /* SSCR 9711-SSCR9724 change */
        
        bPracOfficeInfoEntered = false;
        msgPart1 = "If Second Practice Information is entered in " + Constants.sectionOfficeLocation + ", Second Practice ";

        if ((!isEmpty(pracOfficeAddress3) || !isEmpty(pracOfficeCity3) ||!isEmpty(pracOfficeState3) ||
        		!isEmpty(pracOfficeZip3) ||!isEmpty(pracOfficeCounty3) ||!isEmpty(pracOfficePhone3) ||
        		!isEmpty(pracOfficeFax3) ||!isEmpty(pracOfficeEmail3) ||!isEmpty(languagesSpoken3)  ||
        		/*!isEmpty(daysOpenMon[2]) ||!isEmpty(daysOpenTue[2]) || !isEmpty(daysOpenWed[2]) ||
        		!isEmpty(daysOpenThu[2]) ||!isEmpty(daysOpenFri[2]) || !isEmpty(daysOpenSat[2]) ||!isEmpty(daysOpenSun[2]) ||*/  !isEmpty(billAddressSame3) || 
        		!isEmpty(pracBillContactName3) || !isEmpty(pracBillAddress3) || !isEmpty(pracBillCity3) ||!isEmpty(pracBillState3) ||
        		!isEmpty(pracBillZip3) ||!isEmpty(pracBillCounty3) || !isEmpty(pracBillPhone3) ||
        		!isEmpty(pracBillFax3) ||!isEmpty(pracBillContactEmail3) || !isEmpty(pracNPINo3) ||!isEmpty(billMedicareGroup3) ||
        		!isEmpty(billMedicareIndividual3) ||!isEmpty(medicaidGroup3) ||!isEmpty(medicaidIndividual3) ||
        		!isEmpty(kyMedicaidId3) ||!isEmpty(kyMedicaidPart3) || !isEmpty(pracName3))) {
        	    bPracOfficeInfoEntered = true;
        }
		if (bPracOfficeInfoEntered && isEmpty(pracName3)) {
			errorMessagesVectorG.add(msgPart1 + "Name is required.");
			allOk = false;
		}
		if (bPracOfficeInfoEntered) {
			
			allOk = isValidZip(pracOfficeZip3, Constants.sectionOfficeLocation+" - Location 2", "Practice zip code", Constants.sectionOfficeLocation, allOk);
		}
		if (bPracOfficeInfoEntered) {
			
			allOk = isValidZip(pracBillZip3, Constants.sectionOfficeLocation+" Second", "zip code for payment", Constants.sectionOfficeLocation, allOk);
		}
		if (bPracOfficeInfoEntered && isEmpty(pracOfficePhone3)) {
			errorMessagesVectorG.add(msgPart1
					+ " office phone number is required and 10 digit in length.");
			allOk = false;
		}
		if (bPracOfficeInfoEntered && isEmpty(pracOfficeFax3)) {
			errorMessagesVectorG
					.add(msgPart1 + " office fax number is required and 10 digit in length.");
			allOk = false;
		}
		if (bPracOfficeInfoEntered && isEmpty(pracOfficeEmail3)) {
			errorMessagesVectorG.add(msgPart1
					+ " office email address is required.");
			allOk = false;
		}
		if (bPracOfficeInfoEntered && isEmpty(pracBillContactName3)) {
			errorMessagesVectorG.add(msgPart1
					+ "contact name for payment is required.");
			allOk = false;
		}
		if (bPracOfficeInfoEntered && isEmpty(pracBillPhone3)) {
			errorMessagesVectorG.add(msgPart1
					+ " phone number is required.");
			allOk = false;
		}
		if (bPracOfficeInfoEntered && isEmpty(pracBillFax3)) {
			errorMessagesVectorG.add(msgPart1
					+ " fax number for payment is required.");
			allOk = false;
		}
        
		if (bPracOfficeInfoEntered && isEmpty(pracBillContactEmail3)) {
			errorMessagesVectorG.add(msgPart1+ " email address for payment is required.");
			allOk = false;
		}
        if (!pracOfficePhone3.equals("") && !pracOfficePhone3.equals(" "))
        {
            numbersOk = validatePhoneNumber(pracOfficePhone3);
            if (!numbersOk)
            {
            	errorMessagesVectorG.add(
                		Constants.sectionOfficeLocation + " -- Location 2 office phone number must be numeric and 10 digit in length.");
                allOk = false;
            }
        }

        if (!pracOfficeFax3.equals("") && !pracOfficeFax3.equals(" "))
        {
            numbersOk = validatePhoneNumber(pracOfficeFax3);
            if (!numbersOk)
            {
            	errorMessagesVectorG.add(
                		Constants.sectionOfficeLocation + " -- Location 2 office fax number must be numeric and 10 digit in length.");
                allOk = false;
            }
        }
        /*if (!pracBillPhone3.equals("") && !pracBillPhone3.equals(" "))
        {
            numbersOk = validatePhoneNumber(pracBillPhone3);
            if (!numbersOk && pracBillPhone3.length()!=10)
            {
                errorMessagesVectorG.add(
                		Constants.sectionOfficeLocation + " -- Location 2 phone number for payment must be numeric and 10 digit in length.");
                allOk = false;
            }
        }

        if (!pracBillFax3.equals("") && !pracBillFax3.equals(" "))
        {
            numbersOk = validatePhoneNumber(pracBillFax3);
            if (!numbersOk && pracBillFax3.length()!=10)
            {
                errorMessagesVectorG.add(
                		Constants.sectionOfficeLocation + " -- Location 2 fax number for payment must be numeric and 10 digit in length.");
                allOk = false;
            }
        }*/
        /* SSCR 9711-SSCR9724 change */
       
		if (bPracOfficeInfoEntered && "9999999999".equals(pracOfficePhone3)) {
			errorMessagesVectorG.add(Constants.sectionOfficeLocation
					+ " - Location 2" + " office phone number is invalid.");
			allOk = false;
		}
		if (bPracOfficeInfoEntered && "9999999999".equals(pracOfficeFax3)) {
			errorMessagesVectorG.add(Constants.sectionOfficeLocation
					+ " - Location 2" + " office fax number is invalid.");
			allOk = false;
		}
		if (bPracOfficeInfoEntered && "9999999999".equals(pracBillPhone3)) {
			errorMessagesVectorG.add(Constants.sectionOfficeLocation
					+ " - Location 2" + " phone number is invalid.");
			allOk = false;
		}
		if (bPracOfficeInfoEntered && "9999999999".equals(pracBillFax3)) {
			errorMessagesVectorG.add(Constants.sectionOfficeLocation
					+ " - Location 2" + " fax number for payment is invalid.");
			allOk = false;
		}
       
        /* SSCR 9711-SSCR9724 change end*/
        if (!pracBillPhone3.equals("") && !pracBillPhone3.equals(" "))
        {
            numbersOk = validatePhoneNumber(pracBillPhone3);
            if (!numbersOk || pracBillPhone3.length()!=10)
            {
            	errorMessagesVectorG.add(
                		Constants.sectionOfficeLocation + " -- Location 2 phone number for payment must be numeric and 10 digit in length.");
                allOk = false;
            }
        }

        if (!pracBillFax3.equals("") && !pracBillFax3.equals(" "))
        {
            numbersOk = validatePhoneNumber(pracBillFax3);
            if (!numbersOk || pracBillFax3.length()!=10)
            {
            	errorMessagesVectorG.add(
                		Constants.sectionOfficeLocation + " -- Location 2 fax number for payment must be numeric and 10 digit in length.");
                allOk = false;
            }
        }
        
        if (bPracOfficeInfoEntered && (isEmpty(matWaiveredPrescriber3) || isEmpty(certOpioidTreat3) || isEmpty(matOpioid3) 
				 || isEmpty(counselOpioid3) || isEmpty(sudProv3) || isEmpty(resTreatCtr3))) {
			//from section C
			if (!isEmpty(medicationAssistedTreatment) && medicationAssistedTreatment.equals("Y")) {
				errorMessagesVectorG.add("If Medication Assisted Treatment (MAT) is selected in Section C, Medication Assisted Treatment (MAT) questions are required in "
						+ Constants.sectionOfficeLocation + " Second Practice.");
				allOk = false;
			}
			//from section J
			if (areasOfExpertise.isCounselOpioid() || areasOfExpertise.isMatOpioid() 
					 || areasOfExpertise.isMatWaivered()) {
				errorMessagesVectorG.add("If Medication Assisted Treatment (MAT) is selected in Section J, Medication Assisted Treatment (MAT) questions are required in "
						+ Constants.sectionOfficeLocation + " Second Practice.");
				allOk = false;
			}
		}
        
        if (bPracOfficeInfoEntered && isEmpty(provideTelehealth3)) {
			//from section C
        	if (!isEmpty(telehealthProv)) {
				errorMessagesVectorG.add("If Telehealth Provider is selected in Section C, the Telehealth Services question is required in "
						+ Constants.sectionOfficeLocation + " Second Practice.");
				allOk = false;
			}
			//from section J
			if (areasOfExpertise.isTelehealthProv()) {
				errorMessagesVectorG.add("If Telehealth Provider is selected in Section J, the Telehealth Services question is required in "
						+ Constants.sectionOfficeLocation + " Second Practice.");
				allOk = false;
			}
		}
        
        /*if(!isEmpty(pracBillZip3)){
        	allOk = isValidZip(pracBillZip3, Constants.sectionOfficeLocation, "zip code for payment", Constants.sectionOfficeLocation);
        }*/
        //IN RFS2 Edits
        isMandatory = (StringUtils.isNotEmpty(hipIndicator) || StringUtils.isNotEmpty(medicaidIndicator)) && bPracOfficeInfoEntered;
        isOfficeHoursOk = editOfficeHours(isMandatory, Constants.sectionOfficeLocation, 2);
        
        //      Changes for the state mandate on 02/10/10 start
        if (!isEmpty(medicaidIndicator)){
        	if(!isEmpty(pracOfficeAddress3) || !isEmpty(pracBillAddress3)){
        		if (isEmpty(offerECI3) || isEmpty(offerEPSDT3) 
        				|| isEmpty(provideADB3)	|| isEmpty(provideCSHCN3)) { 
        			errorMessagesVectorC.add("You must answer the IN Anthem Medicaid questions in "  + Constants.sectionOfficeLocation + " since you selected Anthem Medicaid in " + Constants.sectionProviderInfo + ".");
        			allOk = false; 
        		}
            	if(isEmpty(medicaidGroup3) || isEmpty(medicaidIndividual3)) { 
            		errorMessagesVectorC.add("You must answer the Medicaid Group Number and Medicaid Individual Number questions in " + Constants.sectionOfficeLocation + " since you selected Anthem Medicaid in " + Constants.sectionProviderInfo + ".");
            		allOk = false;  
            	}
        	}
        }
        if (!isEmpty(hipIndicator) && (!isEmpty(pracOfficeAddress3) || !isEmpty(pracBillAddress3)) && (isEmpty(medicaidGroup3) || isEmpty(medicaidIndividual3))) { 
        	errorMessagesVectorC.add("You must answer the Medicaid Group Number and Medicaid Individual Number questions in " + Constants.sectionOfficeLocation + " since you selected Anthem Healthy Indiana Plan in " + Constants.sectionProviderInfo + ".");
    		allOk = false;  
    	
        }
        //      Changes for the state mandate on 02/10/10 end
        
        bPracOfficeInfoEntered = false;
        msgPart1 = "If Third Practice Information is entered in " + Constants.sectionOfficeLocation + ", Third Practice ";
        if ((!isEmpty(pracOfficeAddress4) || !isEmpty(pracOfficeCity4) ||!isEmpty(pracOfficeState4) ||
        		!isEmpty(pracOfficeZip4) ||!isEmpty(pracOfficeCounty4) ||!isEmpty(pracOfficePhone4) ||
        		!isEmpty(pracOfficeFax4) ||!isEmpty(pracOfficeEmail4) ||!isEmpty(languagesSpoken4) ||
        		/*!isEmpty(daysOpenMon[3]) ||!isEmpty(daysOpenTue[3]) || !isEmpty(daysOpenWed[3]) ||
        		!isEmpty(daysOpenThu[3]) ||!isEmpty(daysOpenFri[3]) || !isEmpty(daysOpenSat[3]) ||!isEmpty(daysOpenSun[3]) ||*/  !isEmpty(billAddressSame4) ||
        		!isEmpty(pracBillContactName4) || !isEmpty(pracBillAddress4) || !isEmpty(pracBillCity4) ||!isEmpty(pracBillState4) ||
        		!isEmpty(pracBillZip4) ||!isEmpty(pracBillCounty4) || !isEmpty(pracBillPhone4) ||
        		!isEmpty(pracBillFax4) || !isEmpty(pracBillContactEmail4) ||!isEmpty(pracNPINo4) ||!isEmpty(billMedicareGroup4) ||
        		!isEmpty(billMedicareIndividual4) ||!isEmpty(medicaidGroup4) ||!isEmpty(medicaidIndividual4) ||
        		!isEmpty(kyMedicaidId4) ||!isEmpty(kyMedicaidPart4) || !isEmpty(pracName4))) {
        	bPracOfficeInfoEntered = true;
        }
        
        /* SSCR 9711-SSCR9724 change */

		if (bPracOfficeInfoEntered && isEmpty(pracName4)) {
			errorMessagesVectorG.add(msgPart1 + "Name is required.");
			allOk = false;
		}
		if (bPracOfficeInfoEntered) {
			
			allOk = isValidZip(pracOfficeZip4, Constants.sectionOfficeLocation+" - Location 3", "Practice zip code", Constants.sectionOfficeLocation, allOk);
		}
		if (bPracOfficeInfoEntered) {
			
			allOk = isValidZip(pracBillZip4, Constants.sectionOfficeLocation+" Third", "zip code for payment", Constants.sectionOfficeLocation, allOk);
		}
		if (bPracOfficeInfoEntered && isEmpty(pracOfficePhone4)) {
			errorMessagesVectorG.add(msgPart1
					+ " office phone number is required and 10 digit in length.");
			allOk = false;
		}
		if (bPracOfficeInfoEntered && isEmpty(pracOfficeFax4)) {
			errorMessagesVectorG
					.add(msgPart1 + " office fax number is required and 10 digit in length.");
			allOk = false;
		}
		if (bPracOfficeInfoEntered && isEmpty(pracOfficeEmail4)) {
			errorMessagesVectorG.add(msgPart1
					+ " office email address is required.");
			allOk = false;
		}
		if (bPracOfficeInfoEntered && isEmpty(pracBillContactName4)) {
			errorMessagesVectorG.add(msgPart1
					+ "contact name for payment is required.");
			allOk = false;
		}
		
		if (bPracOfficeInfoEntered && isEmpty(pracBillPhone4)) {
			errorMessagesVectorG.add(msgPart1
					+ " phone number is required.");
			allOk = false;
		}
		if (bPracOfficeInfoEntered && isEmpty(pracBillFax4)) {
			errorMessagesVectorG.add(msgPart1
					+ " fax number for payment is required.");
			allOk = false;
		}
		if (bPracOfficeInfoEntered && isEmpty(pracBillContactEmail4)) {
			errorMessagesVectorG.add(msgPart1+ " email address for payment is required.");
			allOk = false;
		}
      
        /* SSCR 9711-SSCR9724 change end*/
        if (!pracOfficePhone4.equals("") && !pracOfficePhone4.equals(" "))
        {
            numbersOk = validatePhoneNumber(pracOfficePhone4);
            if (!numbersOk)
            {
            	errorMessagesVectorG.add(
                		Constants.sectionOfficeLocation + " -- Location 3 office phone number must be numeric and 10 digit in length.");
                allOk = false;
            }
        }

        if (!pracOfficeFax4.equals("") && !pracOfficeFax4.equals(" "))
        {
            numbersOk = validatePhoneNumber(pracOfficeFax4);
            if (!numbersOk)
            {
            	errorMessagesVectorG.add(
                		Constants.sectionOfficeLocation + " -- Location 3 office fax number must be numeric and 10 digit in length.");
                allOk = false;
            }
        }
       /* if (!pracBillPhone4.equals("") && !pracBillPhone4.equals(" "))
        {
            numbersOk = validatePhoneNumber(pracBillPhone4);
            if (!numbersOk && pracBillPhone4.length()!=10)
            {
                errorMessagesVectorF.add(
                		Constants.sectionOfficeLocation + " -- Location 3 phone number for payment must be numeric and 10 digit in length.");
                allOk = false;
            }
        }

        if (!pracBillFax4.equals("") && !pracBillFax4.equals(" "))
        {
            numbersOk = validatePhoneNumber(pracBillFax4);
            if (!numbersOk && pracBillFax4.length()!=10)
            {
                errorMessagesVectorF.add(
                		Constants.sectionOfficeLocation + " -- Location 3 fax number for payment must be numeric and 10 digit in length.");
                allOk = false;
            }
        }*/

        /* SSCR 9711-SSCR9724 change */
        
		if (bPracOfficeInfoEntered && "9999999999".equals(pracOfficePhone4)) {
			errorMessagesVectorG.add(Constants.sectionOfficeLocation
					+ " - Location 3" + " office phone number is invalid.");
			allOk = false;
		}
		if (bPracOfficeInfoEntered && "9999999999".equals(pracOfficeFax4)) {
			errorMessagesVectorG.add(Constants.sectionOfficeLocation
					+ " - Location 3" + " office fax number is invalid.");
			allOk = false;
		}
		if (bPracOfficeInfoEntered && "9999999999".equals(pracBillPhone4)) {
			errorMessagesVectorG.add(Constants.sectionOfficeLocation
					+ " - Location 3" + " phone number is invalid.");
			allOk = false;
		}
		if (bPracOfficeInfoEntered && "9999999999".equals(pracBillFax4)) {
			errorMessagesVectorG.add(Constants.sectionOfficeLocation
					+ " - Location 3" + " fax number for payment is invalid.");
			allOk = false;
		}

        /* SSCR 9711-SSCR9724 change end*/
        if (!pracBillPhone4.equals("") && !pracBillPhone4.equals(" "))
        {
            numbersOk = validatePhoneNumber(pracBillPhone4);
            if (!numbersOk || pracBillPhone4.length()!=10)
            {
            	errorMessagesVectorG.add(
                		Constants.sectionOfficeLocation + " -- Location 3 phone number for payment must be numeric and 10 digit in length.");
                allOk = false;
            }
        }

        if (!pracBillFax4.equals("") && !pracBillFax4.equals(" "))
        {
            numbersOk = validatePhoneNumber(pracBillFax4);
            if (!numbersOk || pracBillFax4.length()!=10)
            {
            	errorMessagesVectorG.add(
                		Constants.sectionOfficeLocation + " -- Location 3 fax number for payment must be numeric and 10 digit in length.");
                allOk = false;
            }
        }
        
        if (bPracOfficeInfoEntered && (isEmpty(matWaiveredPrescriber4) || isEmpty(certOpioidTreat4) || isEmpty(matOpioid4) 
				 || isEmpty(counselOpioid4) || isEmpty(sudProv4) || isEmpty(resTreatCtr4))) {
			//from section C
			if (!isEmpty(medicationAssistedTreatment) && medicationAssistedTreatment.equals("Y")) {
				errorMessagesVectorG.add("If Medication Assisted Treatment (MAT) is selected in Section C, Medication Assisted Treatment (MAT) questions are required in "
						+ Constants.sectionOfficeLocation + " Third Practice.");
				allOk = false;
			}
			//from section J
			if (areasOfExpertise.isCounselOpioid() || areasOfExpertise.isMatOpioid() 
					 || areasOfExpertise.isMatWaivered()) {
				errorMessagesVectorG.add("If Medication Assisted Treatment (MAT) is selected in Section J, Medication Assisted Treatment (MAT) questions are required in "
						+ Constants.sectionOfficeLocation + " Third Practice.");
				allOk = false;
			}
		}
        
        if (bPracOfficeInfoEntered && isEmpty(provideTelehealth4)) {
			//from section C
        	if (!isEmpty(telehealthProv)) {
				errorMessagesVectorG.add("If Telehealth Provider is selected in Section C, the Telehealth Services question is required in "
						+ Constants.sectionOfficeLocation + " Third Practice.");
				allOk = false;
			}
			//from section J
			if (areasOfExpertise.isTelehealthProv()) {
				errorMessagesVectorG.add("If Telehealth Provider is selected in Section J, the Telehealth Services question is required in "
						+ Constants.sectionOfficeLocation + " Third Practice.");
				allOk = false;
			}
		}
        
        //IN RFS2 Edits
        isMandatory = (StringUtils.isNotEmpty(hipIndicator) || StringUtils.isNotEmpty(medicaidIndicator)) && bPracOfficeInfoEntered;
        isOfficeHoursOk = editOfficeHours(isMandatory, Constants.sectionOfficeLocation, 3);
        
        //      Changes for the state mandate on 02/10/10 start
        if (!isEmpty(medicaidIndicator)){
        	if(!isEmpty(pracOfficeAddress4) || !isEmpty(pracBillAddress4)){
        		if (isEmpty(offerECI4) || isEmpty(offerEPSDT4) 
        				|| isEmpty(provideADB4)	|| isEmpty(provideCSHCN4)) { 
        			errorMessagesVectorG.add("You must answer the IN Anthem Medicaid questions in "  + Constants.sectionOfficeLocation + " since you selected Anthem Medicaid in " + Constants.sectionProviderInfo + ".");
        			allOk = false; 
        		}
            	if(isEmpty(medicaidGroup4) || isEmpty(medicaidIndividual4)) { 
            		errorMessagesVectorG.add("You must answer the Medicaid Group Number and Medicaid Individual Number questions in " + Constants.sectionOfficeLocation + " since you selected Anthem Medicaid in " + Constants.sectionProviderInfo + ".");
            		allOk = false;  
            	}
        	}
        }
        if (!isEmpty(hipIndicator) && (!isEmpty(pracOfficeAddress4) || !isEmpty(pracBillAddress4)) && (isEmpty(medicaidGroup4) || isEmpty(medicaidIndividual4))) { 
        	errorMessagesVectorG.add("You must answer the Medicaid Group Number and Medicaid Individual Number questions in " + Constants.sectionOfficeLocation + " since you selected Anthem Healthy Indiana Plan in " + Constants.sectionProviderInfo + ".");
    		allOk = false;  
    	
        }
        //      Changes for the state mandate on 02/10/10 end
        
        bPracOfficeInfoEntered = false;
        msgPart1 = "If Fourth Practice Information is entered in " + Constants.sectionOfficeLocation + ", Fourth Practice ";
        if ((!isEmpty(pracOfficeAddress5) || !isEmpty(pracOfficeCity5) ||!isEmpty(pracOfficeState5) ||
        		!isEmpty(pracOfficeZip5) ||!isEmpty(pracOfficeCounty5) ||!isEmpty(pracOfficePhone5) ||
        		!isEmpty(pracOfficeFax5) ||!isEmpty(pracOfficeEmail5) ||!isEmpty(languagesSpoken5) ||
        		/*!isEmpty(daysOpenMon[4]) ||!isEmpty(daysOpenTue[4]) || !isEmpty(daysOpenWed[4]) ||
        		!isEmpty(daysOpenThu[4]) ||!isEmpty(daysOpenFri[4]) || !isEmpty(daysOpenSat[4]) ||!isEmpty(daysOpenSun[4]) ||*/ !isEmpty(billAddressSame5) ||
        		!isEmpty(pracBillContactName5) || !isEmpty(pracBillAddress5) || !isEmpty(pracBillCity5) ||!isEmpty(pracBillState5) ||
        		!isEmpty(pracBillZip5) ||!isEmpty(pracBillCounty5) || !isEmpty(pracBillPhone5) ||
        		!isEmpty(pracBillFax5) || !isEmpty(pracBillContactEmail5) ||!isEmpty(pracNPINo5) ||!isEmpty(billMedicareGroup5) ||
        		!isEmpty(billMedicareIndividual5) ||!isEmpty(medicaidGroup5) ||!isEmpty(medicaidIndividual5) ||
        		!isEmpty(kyMedicaidId5) ||!isEmpty(kyMedicaidPart5) || !isEmpty(pracName5))) {
        	bPracOfficeInfoEntered = true;
        }
        /* SSCR 9711-SSCR9724 change */
	        if(bPracOfficeInfoEntered && isEmpty(pracName5)){
	        	errorMessagesVectorG.add(msgPart1+ "Name is required.");
	         	allOk = false;
	        }
	        if (bPracOfficeInfoEntered) {
				
				allOk = isValidZip(pracOfficeZip5, Constants.sectionOfficeLocation+" - Location 4", "Practice zip code", Constants.sectionOfficeLocation, allOk);
			}
	        if (bPracOfficeInfoEntered) {
				
				allOk = isValidZip(pracOfficeZip5, Constants.sectionOfficeLocation+" Fourth", "zip code for payment", Constants.sectionOfficeLocation, allOk);
			}
	        if (bPracOfficeInfoEntered && isEmpty(pracOfficePhone5))
	        {
	        	errorMessagesVectorG.add(msgPart1+ " office phone number is required and 10 digit in length.");
	        allOk = false;
	        }
	        if (bPracOfficeInfoEntered && isEmpty(pracOfficeFax5))
	        {
	        	errorMessagesVectorG.add(msgPart1+ " office fax number is required and 10 digit in length.");
	        allOk = false;
	        }
	        if (bPracOfficeInfoEntered && isEmpty(pracOfficeEmail5))
	        {
	        	errorMessagesVectorG.add(msgPart1+ " office email address is required.");
	        allOk = false;
	        }
	        if (bPracOfficeInfoEntered && isEmpty(pracBillContactName5)) {
	        	errorMessagesVectorG.add(msgPart1
						+ "contact name for payment is required.");
				allOk = false;
			}
	        if (bPracOfficeInfoEntered && isEmpty(pracBillPhone5))
	        {
	        	errorMessagesVectorG.add(msgPart1+ " phone number is required.");
	        allOk = false;
	        }
	        if (bPracOfficeInfoEntered && isEmpty(pracBillFax5))
	        {
	        	errorMessagesVectorG.add(msgPart1+ " fax number for payment is required.");
	        allOk = false;
	        }
	        if (bPracOfficeInfoEntered && isEmpty(pracBillContactEmail5))
	        {
	        	errorMessagesVectorG.add(msgPart1+ " email address for payment is required.");
	        allOk = false;
	        }
       
        /* SSCR 9711-SSCR9724 change end*/
        if (!pracOfficePhone5.equals("") && !pracOfficePhone5.equals(" "))
        {
            numbersOk = validatePhoneNumber(pracOfficePhone5);
            if (!numbersOk)
            {
            	errorMessagesVectorG.add(
                		Constants.sectionOfficeLocation + " -- Location 4 office phone number must be numeric and 10 digit in length.");
                allOk = false;
            }
        }

        if (!pracOfficeFax5.equals("") && !pracOfficeFax5.equals(" "))
        {
            numbersOk = validatePhoneNumber(pracOfficeFax5);
            if (!numbersOk)
            {
            	errorMessagesVectorG.add(
                		Constants.sectionOfficeLocation + " -- Location 4 office fax number must be numeric and 10 digit in length.");
                allOk = false;
            }
        }
        /*if (!pracBillPhone5.equals("") && !pracBillPhone5.equals(" "))
        {
            numbersOk = validatePhoneNumber(pracBillPhone5);
            if (!numbersOk && pracBillPhone5.length()!=10)
            {
                errorMessagesVectorF.add(
                		Constants.sectionOfficeLocation + " -- Location 4 phone number for payment must be numeric and 10 digit in length.");
                allOk = false;
            }
        }

        if (!pracBillFax5.equals("") && !pracBillFax5.equals(" "))
        {
            numbersOk = validatePhoneNumber(pracBillFax5);
            if (!numbersOk && pracBillFax5.length()!=10)
            {
                errorMessagesVectorF.add(
                		Constants.sectionOfficeLocation + " -- Location 4 fax number for payment must be numeric and 10 digit in length.");
                allOk = false;
            }
        }*/
        
        /* SSCR 9711-SSCR9724 change */
   
	        if(bPracOfficeInfoEntered && "9999999999".equals(pracOfficePhone5))
	        {
	        	errorMessagesVectorG.add(Constants. sectionOfficeLocation +" - Location 4"+ " office phone number is invalid.");
	        	allOk = false;
	        }
	        if(bPracOfficeInfoEntered && "9999999999".equals(pracOfficeFax5))
	        {
	        	errorMessagesVectorG.add(Constants. sectionOfficeLocation +" - Location 4"+ " office fax number is invalid.");
	        	allOk = false;
	        }
	        if(bPracOfficeInfoEntered && "9999999999".equals(pracBillPhone5))
	        {
	        	errorMessagesVectorG.add(Constants. sectionOfficeLocation +" - Location 4"+ " phone number is invalid.");
	        	allOk = false;
	        }
	        if(bPracOfficeInfoEntered && "9999999999".equals(pracBillFax5))
	        {
	        	errorMessagesVectorG.add(Constants. sectionOfficeLocation +" - Location 4"+ " fax number for payment is invalid.");
	        	allOk = false;
	        }
       
        /* SSCR 9711-SSCR9724 change end*/
        
        if (!pracBillPhone5.equals("") && !pracBillPhone5.equals(" "))
        {
            numbersOk = validatePhoneNumber(pracBillPhone5);
            if (!numbersOk || pracBillPhone5.length()!=10)
            {
            	errorMessagesVectorG.add(
                		Constants.sectionOfficeLocation + " -- Location 4 phone number for payment must be numeric and 10 digit in length.");
                allOk = false;
            }
        }

        if (!pracBillFax5.equals("") && !pracBillFax5.equals(" "))
        {
            numbersOk = validatePhoneNumber(pracBillFax5);
            if (!numbersOk || pracBillFax5.length()!=10)
            {
            	errorMessagesVectorG.add(
                		Constants.sectionOfficeLocation + " -- Location 4 fax number for payment must be numeric and 10 digit in length.");
                allOk = false;
            }
        }
        
        if (bPracOfficeInfoEntered && (isEmpty(matWaiveredPrescriber5) || isEmpty(certOpioidTreat5) || isEmpty(matOpioid5) 
				 || isEmpty(counselOpioid5) || isEmpty(sudProv5) || isEmpty(resTreatCtr5))) {
			//from section C
			if (!isEmpty(medicationAssistedTreatment) && medicationAssistedTreatment.equals("Y")) {
				errorMessagesVectorG.add("If Medication Assisted Treatment (MAT) is selected in Section C, Medication Assisted Treatment (MAT) questions are required in "
						+ Constants.sectionOfficeLocation + " Fourth Practice.");
				allOk = false;
			}
			//from section J
			if (areasOfExpertise.isCounselOpioid() || areasOfExpertise.isMatOpioid() 
					 || areasOfExpertise.isMatWaivered()) {
				errorMessagesVectorG.add("If Medication Assisted Treatment (MAT) is selected in Section J, Medication Assisted Treatment (MAT) questions are required in "
						+ Constants.sectionOfficeLocation + " Fourth Practice.");
				allOk = false;
			}
		}
        
        if (bPracOfficeInfoEntered && isEmpty(provideTelehealth5)) {
			//from section C
        	if (!isEmpty(telehealthProv)) {
				errorMessagesVectorG.add("If Telehealth Provider is selected in Section C, the Telehealth Services question is required in "
						+ Constants.sectionOfficeLocation + " Fourth Practice.");
				allOk = false;
			}
			//from section J
			if (areasOfExpertise.isTelehealthProv()) {
				errorMessagesVectorG.add("If Telehealth Provider is selected in Section J, the Telehealth Services question is required in "
						+ Constants.sectionOfficeLocation + " Fourth Practice.");
				allOk = false;
			}
		}
        
        //IN RFS2 Edits
        isMandatory = (StringUtils.isNotEmpty(hipIndicator) || StringUtils.isNotEmpty(medicaidIndicator)) && bPracOfficeInfoEntered;
        isOfficeHoursOk = editOfficeHours(isMandatory, Constants.sectionOfficeLocation, 4);

        //      Changes for the state mandate on 02/10/10 start
        if (!isEmpty(medicaidIndicator)){
        	if(!isEmpty(pracOfficeAddress5) || !isEmpty(pracBillAddress5)){
        		if (isEmpty(offerECI5) || isEmpty(offerEPSDT5) 
        				|| isEmpty(provideADB5)	|| isEmpty(provideCSHCN5)) { 
        			errorMessagesVectorG.add("You must answer the IN Anthem Medicaid questions in "  + Constants.sectionOfficeLocation + " since you selected Anthem Medicaid in " + Constants.sectionProviderInfo + ".");
        			allOk = false; 
        		}
            	if(isEmpty(medicaidGroup5) || isEmpty(medicaidIndividual5)) { 
            		errorMessagesVectorG.add("You must answer the Medicaid Group Number and Medicaid Individual Number questions in " + Constants.sectionOfficeLocation + " since you selected Anthem Medicaid in " + Constants.sectionProviderInfo + ".");
            		allOk = false;  
            	}
        	}
        }

        if (!isEmpty(hipIndicator) && (!isEmpty(pracOfficeAddress5) || !isEmpty(pracBillAddress5)) && (isEmpty(medicaidGroup5) || isEmpty(medicaidIndividual5))) { 
        	errorMessagesVectorG.add("You must answer the Medicaid Group Number and Medicaid Individual Number questions in " + Constants.sectionOfficeLocation + " since you selected Anthem Healthy Indiana Plan in " + Constants.sectionProviderInfo + ".");
    		allOk = false;  
    	
        }        
        //      Changes for the state mandate on 02/10/10 end
        
        bPracOfficeInfoEntered = false;
        msgPart1 = "If Fifth Practice Information is entered in " + Constants.sectionOfficeLocation + ", Fifth Practice ";
        if ((!isEmpty(pracOfficeAddress6) || !isEmpty(pracOfficeCity6) ||!isEmpty(pracOfficeState6) ||
        		!isEmpty(pracOfficeZip6) ||!isEmpty(pracOfficeCounty6) ||!isEmpty(pracOfficePhone6) ||
        		!isEmpty(pracOfficeFax6) ||!isEmpty(pracOfficeEmail6) ||!isEmpty(languagesSpoken6) ||
        		/*!isEmpty(daysOpenMon[5]) ||!isEmpty(daysOpenTue[5]) || !isEmpty(daysOpenWed[5]) ||
        		!isEmpty(daysOpenThu[5]) ||!isEmpty(daysOpenFri[5]) || !isEmpty(daysOpenSat[5]) ||!isEmpty(daysOpenSun[5]) ||*/  !isEmpty(billAddressSame6) || 
        		!isEmpty(pracBillContactName6) ||!isEmpty(pracBillAddress6) ||!isEmpty(pracBillCity6) ||!isEmpty(pracBillState6) ||
        		!isEmpty(pracBillZip6) ||!isEmpty(pracBillCounty6) || !isEmpty(pracBillPhone6) ||
        		!isEmpty(pracBillFax6)|| !isEmpty(pracBillContactEmail6)||!isEmpty(pracNPINo6) ||!isEmpty(billMedicareGroup6) ||
        		!isEmpty(billMedicareIndividual6) ||!isEmpty(medicaidGroup6) ||!isEmpty(medicaidIndividual6) ||
        		!isEmpty(kyMedicaidId6) ||!isEmpty(kyMedicaidPart6) || !isEmpty(pracName6))) {
        	bPracOfficeInfoEntered = true;
        }
        
        /* SSCR 9711-SSCR9724 change */
	        if(bPracOfficeInfoEntered && isEmpty(pracName6)){
	        	errorMessagesVectorG.add(msgPart1+ "Name is required.");
	         	allOk = false;
	        }
	        if (bPracOfficeInfoEntered) {
				allOk = isValidZip(pracOfficeZip6, Constants.sectionOfficeLocation+" - Location 5", "Practice zip code", Constants.sectionOfficeLocation, allOk);
			}
	        if (bPracOfficeInfoEntered) {
				allOk = isValidZip(pracOfficeZip6, Constants.sectionOfficeLocation+" Fifth", "zip code for payment", Constants.sectionOfficeLocation, allOk);
			}
	        if (bPracOfficeInfoEntered && isEmpty(pracOfficePhone6))
	        {
	        	errorMessagesVectorG.add(msgPart1+ " office phone number is required.");
	        allOk = false;
	        }
	        if (bPracOfficeInfoEntered && isEmpty(pracOfficeFax6))
	        {
	        	errorMessagesVectorG.add(msgPart1+ " office fax number is required.");
	        allOk = false;
	        }
	        if (bPracOfficeInfoEntered && isEmpty(pracOfficeEmail6))
	        {
	        	errorMessagesVectorG.add(msgPart1+ " office email address is required.");
	        allOk = false;
	        }
	        if (bPracOfficeInfoEntered && isEmpty(pracBillContactName6)) {
	        	errorMessagesVectorG.add(msgPart1
						+ "contact name for payment is required.");
				allOk = false;
			}
	        if (bPracOfficeInfoEntered && isEmpty(pracBillPhone6))
	        {
	        	errorMessagesVectorG.add(msgPart1+ " phone number is required.");
	        allOk = false;
	        }
	        if (bPracOfficeInfoEntered && isEmpty(pracBillFax6))
	        {
	        	errorMessagesVectorG.add(msgPart1+ " fax number for payment is required.");
	        allOk = false;
	        }
	        if (bPracOfficeInfoEntered && isEmpty(pracBillContactEmail6))
	        {
	        	errorMessagesVectorG.add(msgPart1+ " email address for payment is required.");
	        allOk = false;
	        }
        /* SSCR 9711-SSCR9724 change end*/
        if (!pracOfficePhone6.equals("") && !pracOfficePhone6.equals(" "))
        {
            numbersOk = validatePhoneNumber(pracOfficePhone6);
            if (!numbersOk)
            {
            	errorMessagesVectorG.add(
                		Constants.sectionOfficeLocation + " -- Location 5 office phone number must be numeric.");
                allOk = false;
            }
        }

        if (!pracOfficeFax6.equals("") && !pracOfficeFax6.equals(" "))
        {
            numbersOk = validatePhoneNumber(pracOfficeFax6);
            if (!numbersOk)
            {
            	errorMessagesVectorG.add(
                		Constants.sectionOfficeLocation + " -- Location 5 office fax number must be numeric.");
                allOk = false;
            }
        }
        /*if (!pracBillPhone6.equals("") && !pracBillPhone6.equals(" "))
        {
            numbersOk = validatePhoneNumber(pracBillPhone6);
            if (!numbersOk && pracBillPhone6.length()!=10)
            {
                errorMessagesVectorF.add(
                		Constants.sectionOfficeLocation + " -- Location 5 phone number for payment must be numeric and 10 digit in length.");
                allOk = false;
            }
        }

        if (!pracBillFax6.equals("") && !pracBillFax6.equals(" "))
        {
            numbersOk = validatePhoneNumber(pracBillFax6);
            if (!numbersOk && pracBillFax6.length()!=10)
            {
                errorMessagesVectorF.add(
                		Constants.sectionOfficeLocation + " -- Location 5 fax number for payment must be numeric and 10 digit in length.");
                allOk = false;
            }
        }*/

        /* SSCR 9711-SSCR9724 change */
	        if(bPracOfficeInfoEntered && "9999999999".equals(pracOfficePhone6))
	        {
	        	errorMessagesVectorG.add(Constants. sectionOfficeLocation +" - Location 5"+ " office phone number is invalid.");
	        	allOk = false;
	        }
	        if(bPracOfficeInfoEntered && "9999999999".equals(pracOfficeFax6))
	        {
	        	errorMessagesVectorG.add(Constants. sectionOfficeLocation +" - Location 5"+ " office fax number is invalid.");
	        	allOk = false;
	        }
	        if(bPracOfficeInfoEntered && "9999999999".equals(pracBillPhone6))
	        {
	        	errorMessagesVectorG.add(Constants. sectionOfficeLocation +" - Location 5"+ " phone number is invalid.");
	        	allOk = false;
	        }
	        if(bPracOfficeInfoEntered && "9999999999".equals(pracBillFax6))
	        {
	        	errorMessagesVectorG.add(Constants. sectionOfficeLocation +" - Location 5"+ " fax number for payment is invalid.");
	        	allOk = false;
	        }

        /* SSCR 9711-SSCR9724 change end*/
        if (!pracBillPhone6.equals("") && !pracBillPhone6.equals(" "))
        {
            numbersOk = validatePhoneNumber(pracBillPhone6);
            if (!numbersOk || pracBillPhone6.length()!=10)
            {
            	errorMessagesVectorG.add(
                		Constants.sectionOfficeLocation + " -- Location 5 phone number for payment must be numeric and 10 digit in length.");
                allOk = false;
            }
        }

        if (!pracBillFax6.equals("") && !pracBillFax6.equals(" "))
        {
            numbersOk = validatePhoneNumber(pracBillFax6);
            if (!numbersOk || pracBillFax6.length()!=10)
            {
            	errorMessagesVectorG.add(
                		Constants.sectionOfficeLocation + " -- Location 5 fax number for payment must be numeric and 10 digit in length.");
                allOk = false;
            }
        }
        
        if (bPracOfficeInfoEntered && (isEmpty(matWaiveredPrescriber6) || isEmpty(certOpioidTreat6) || isEmpty(matOpioid6) 
				 || isEmpty(counselOpioid6) || isEmpty(sudProv6) || isEmpty(resTreatCtr6))) {
			//from section C
			if (!isEmpty(medicationAssistedTreatment) && medicationAssistedTreatment.equals("Y")) {
				errorMessagesVectorG.add("If Medication Assisted Treatment (MAT) is selected in Section C, Medication Assisted Treatment (MAT) questions are required in "
						+ Constants.sectionOfficeLocation + " Fifth Practice.");
				allOk = false;
			}
			//from section J
			if (areasOfExpertise.isCounselOpioid() || areasOfExpertise.isMatOpioid() 
					 || areasOfExpertise.isMatWaivered()) {
				errorMessagesVectorG.add("If Medication Assisted Treatment (MAT) is selected in Section J, Medication Assisted Treatment (MAT) questions are required in "
						+ Constants.sectionOfficeLocation + " Fifth Practice.");
				allOk = false;
			}
		}
        
        if (bPracOfficeInfoEntered && isEmpty(provideTelehealth6)) {
			//from section C
        	if (!isEmpty(telehealthProv)) {
				errorMessagesVectorG.add("If Telehealth Provider is selected in Section C, the Telehealth Services question is required in "
						+ Constants.sectionOfficeLocation + " Fifth Practice.");
				allOk = false;
			}
			//from section J
			if (areasOfExpertise.isTelehealthProv()) {
				errorMessagesVectorG.add("If Telehealth Provider is selected in Section J, the Telehealth Services question is required in "
						+ Constants.sectionOfficeLocation + " Fifth Practice.");
				allOk = false;
			}
		}
        
        //IN RFS2 Edits
        isMandatory = (StringUtils.isNotEmpty(hipIndicator) || StringUtils.isNotEmpty(medicaidIndicator)) && bPracOfficeInfoEntered;
        isOfficeHoursOk = editOfficeHours(isMandatory, Constants.sectionOfficeLocation, 5);
		
        //      Changes for the state mandate on 02/10/10 start
        if (!isEmpty(medicaidIndicator)){
        	if(!isEmpty(pracOfficeAddress6) || !isEmpty(pracBillAddress6)){
        		if (isEmpty(offerECI6) || isEmpty(offerEPSDT6) 
        				|| isEmpty(provideADB6)	|| isEmpty(provideCSHCN6)) { 
        			errorMessagesVectorG.add("You must answer the IN Anthem Medicaid questions in "  
        					+ Constants.sectionOfficeLocation + " since you selected Anthem Medicaid in " 
        					+ Constants.sectionProviderInfo + ".");
        			allOk = false; 
        		}
            	if(isEmpty(medicaidGroup6) || isEmpty(medicaidIndividual6)) { 
            		errorMessagesVectorG.add("You must answer the Medicaid Group Number and Medicaid Individual Number questions in " 
            				+ Constants.sectionOfficeLocation + " since you selected Anthem Medicaid in " 
            				+ Constants.sectionProviderInfo + ".");
            		allOk = false;  
            	}
        	}
        }
        if (!isEmpty(hipIndicator) && (!isEmpty(pracOfficeAddress6) || !isEmpty(pracBillAddress6)) && (isEmpty(medicaidGroup6) || isEmpty(medicaidIndividual6))) { 
        	errorMessagesVectorG.add("You must answer the Medicaid Group Number and Medicaid Individual Number questions in " + Constants.sectionOfficeLocation + " since you selected Anthem Healthy Indiana Plan in " + Constants.sectionProviderInfo + ".");
    		allOk = false;  
    	
        }
        //      Changes for the state mandate on 02/10/10 end

        if (!taxID1.equals("") && !taxID1.equals(" "))
        {
            if (taxID1.length() < 9)
            {
                errorMessagesVectorH.add(Constants.sectionPhysicians+
                    " First Covering Physician Tax ID must contain 9 digits.");
                allOk = false;
            }
            else
            {
                numbersOk = validateNumeric(taxID1);
                if (!numbersOk)
                {
                    errorMessagesVectorH.add(
                        "First Covering Physician Tax ID (EIN/SSN) must be numeric.");
                    allOk = false;
                }
            }
        }

        if (!effectiveDate1.equals("") && !effectiveDate1.equals(" "))
        {
            dateOk = validateDate(effectiveDate1);
            if (!dateOk)
            {
                errorMessagesVectorH.add(Constants.sectionPhysicians+
                    " First Covering Physician Effective Date invalid.");
                allOk = false;
            }
        }

        if (!taxID2.equals("") && !taxID2.equals(" "))
        {
            if (taxID2.length() < 9)
            {
                errorMessagesVectorH.add(Constants.sectionPhysicians+
                     " Second Covering Physician Tax ID must contain 9 digits.");
                allOk = false;
            }
            else
            {
                numbersOk = validateNumeric(taxID2);
                if (!numbersOk)
                {
                    errorMessagesVectorH.add(
                        "Second Covering Physician Tax ID (EIN/SSN) must be numeric.");
                    allOk = false;
                }
            }
        }

        if (!effectiveDate2.equals("") && !effectiveDate2.equals(" "))
        {
            dateOk = validateDate(effectiveDate2);
            if (!dateOk)
            {
                errorMessagesVectorH.add(Constants.sectionPhysicians+
                    " Second Covering Physician Effective Date invalid.");
                allOk = false;
            }
        }

        if (!taxID3.equals("") && !taxID3.equals(" "))
        {
            if (taxID3.length() < 9)
            {
                errorMessagesVectorH.add(Constants.sectionPhysicians+
                    " Third Covering Physician Tax ID must contain 9 digits.");
                allOk = false;
            }
            else
            {
                numbersOk = validateNumeric(taxID3);
                if (!numbersOk)
                {
                    errorMessagesVectorH.add(
                        "Third Covering Physician Tax ID (EIN/SSN) must be numeric.");
                    allOk = false;
                }
            }
        }

        if (!effectiveDate3.equals("") && !effectiveDate3.equals(" "))
        {
            dateOk = validateDate(effectiveDate3);
            if (!dateOk)
            {
                errorMessagesVectorH.add(Constants.sectionPhysicians+
                    " Third Covering Physician Effective Date invalid.");
                allOk = false;
            }
        }

        if (!taxID4.equals("") && !taxID4.equals(" "))
        {
            if (taxID4.length() < 9)
            {
                errorMessagesVectorH.add(Constants.sectionPhysicians+
                    " Fourth Covering Physician Tax ID must contain 9 digits.");
                allOk = false;
            }
            else
            {
                numbersOk = validateNumeric(taxID4);
                if (!numbersOk)
                {
                    errorMessagesVectorH.add(
                        "Fifth Covering Physician Tax ID (EIN/SSN) must be numeric.");
                    allOk = false;
                }
            }
        }

        if (!effectiveDate4.equals("") && !effectiveDate4.equals(" "))
        {
            dateOk = validateDate(effectiveDate4);
            if (!dateOk)
            {
                errorMessagesVectorH.add(Constants.sectionPhysicians+
                        " Fourth Covering Physician Effective Date invalid.");
                allOk = false;
            }
        }

        if (!taxID5.equals("") && !taxID5.equals(" "))
        {
            if (taxID5.length() < 9)
            {
                errorMessagesVectorH.add(Constants.sectionPhysicians+
                    " Fifth Covering Physician Tax ID must contain 9 digits.");
                allOk = false;
            }
            else
            {
                numbersOk = validateNumeric(taxID5);
                if (!numbersOk)
                {
                    errorMessagesVectorH.add(
                        "Fifth Covering Physician Tax ID (EIN/SSN) must be numeric.");
                    allOk = false;
                }
            }
        }

        if (!effectiveDate5.equals("") && !effectiveDate5.equals(" "))
        {
            dateOk = validateDate(effectiveDate5);
            if (!dateOk)
            {
                errorMessagesVectorH.add(Constants.sectionPhysicians+
                       " Fifth Covering Physician Effective Date invalid.");
                allOk = false;
            }
        }
        if(!isEmpty(nonPsychEval)){
        if(nonPsychEval.equals("inpatient") || nonPsychEval.equals("outpatient")
        	|| nonPsychEval.equals("both")){
        	if(isEmpty(addPatientInfo)){
        	 errorMessagesVectorI.add(Constants.sectionPatientInfo+
             " Information is invalid: If this is a change/update to Behavioral Health data, please check \"Add/Update Patient Information\" in "+Constants.sectionReasonSubmitting+" before updating this section");
        	 allOk = false;
        	}
        }
        }
        /*if (isEmpty(comments))
        {
            errorMessagesVectorL.add(Constants.sectionComments+
                " Please enter your contact name and number in the space provided.");
            allOk = false;
        }*/
        if (isEmpty(confirmation))
        {
            errorMessagesVectorL.add(
                Constants.sectionComments+" confirmation checkbox is required.");
            allOk = false;
        }
        // change for PMF SSCR 13503 27/06/2012 start
        if(isEmpty(confProvAgreement)){
        	errorMessagesVectorL.add(
                    Constants.sectionComments+" for participating provider checkbox is required.");
            allOk = false;
        }
        if(isEmpty(confW2)){
        	  errorMessagesVectorL.add(Constants.sectionComments+" for W-2 employed practitioner is required.");
        	  allOk = false;
        }
        if(isEmpty(comments)){
        	errorMessagesVectorL.add(Constants.sectionComments+ " Please enter your contact name and number in the space provided.");
        	allOk = false;
        }
        // change for PMF SSCR 13503 27/06/2012 end
        
        
        /* PMF Section Changes -- AD21239 -- Start*/
        if("N".equals(confW2) && isEmpty(w2Comments)){
        	errorMessagesVectorL.add(Constants.sectionComments+ " for W-2 employed practitioner comment is required.");
        	allOk = false;
        }
        
        /* PMF Section Changes -- AD21239 -- End*/
        
        if (!isEmpty(uploadDocComment)) {
        	errorMessagesVectorK.add(Constants.sectionAttachment + " The Description of Attachment field must have an accompanying attachment.");
        	allOk = false;
        }
        
        return allOk;
    }

    public boolean isAlphaNumeric(String value)
    {
        boolean retVal = true;

        for (int index = 0; index < value.length(); index++)
        {
            if (!Character.isLetter(value.charAt(index)) &&
                !Character.isWhitespace(value.charAt(index)))
            {
                if (!isNumeric(String.valueOf(value.charAt(index)))){
                    retVal = false;
                    break;
                }
            }
        }
        return retVal;
    }

    public boolean isNumeric(String value)
    {
        boolean retVal = true;

        try
        {
            Float.parseFloat(value);
        }catch (NumberFormatException e)
        {
            retVal = false;
        }
        return retVal;
    }

    //PMF Change SSCR 13503 22/6/2012 Start
    private boolean isValidZip(String s,String sectionName, String sFieldDescription,String section, boolean allOk ){
        String sTempZip = s;
        
        if ((sTempZip.length() == 10)){
          if (sTempZip.substring(5, 6).equals("-") == false){
        	nonNumericErrorMessage(section,sFieldDescription,sectionName);
        	allOk = false;
            return allOk;
          }
          else{
            sTempZip = s.substring(0, 5) + s.substring(6, 10);
          }
        }
        
        int sLength = sTempZip.length();
        if ((sLength == 9)){
        	if(editBadZipChars(s, 1, sFieldDescription, sectionName, section,sTempZip))
        	{
	          if (validateNumeric(sTempZip)){
	              return allOk;
		      }else{
		          nonNumericErrorMessage(section,sFieldDescription,sectionName);
		          allOk = false;
		      }
            }
        	else{
        		 allOk = false;
        	}
        }else{
    	    nonNumericErrorMessage(section,sFieldDescription,sectionName);
    	    allOk = false;
        }
        return allOk;
    }	
    
    public void nonNumericErrorMessage(String sectionName,String sFieldDescription,String section){
    	if(sectionName.equalsIgnoreCase(Constants.sectionGeneralInfo)){
			errorMessagesVectorA.add(section+ " "+ sFieldDescription + " must be numeric and 9 digits in length.");
		}
		else if(sectionName.equalsIgnoreCase(Constants.sectionReasonSubmitting)){
			errorMessagesVectorB.add(section+ " "+ sFieldDescription + " must be numeric and 9 digits in length.");
		}
		else if(sectionName.equalsIgnoreCase(Constants.sectionProviderInfo)){
			errorMessagesVectorC.add(section+ " "+ sFieldDescription + " must be numeric and 9 digits in length.");
		}
		else if(sectionName.equalsIgnoreCase(Constants.sectionWisconsin)){
			errorMessagesVectorD.add(section+ " "+ sFieldDescription + " must be numeric and 9 digits in length.");
		}
		else if(sectionName.equalsIgnoreCase(Constants.sectionPracticeAddress)){
			errorMessagesVectorE.add(section+ " "+ sFieldDescription + " must be numeric and 9 digits in length.");
		}
		else if(sectionName.equalsIgnoreCase(Constants.sectionAddressChange)){
			errorMessagesVectorF.add(section+ " "+ sFieldDescription + " must be numeric and 9 digits in length.");
		}
		else if(sectionName.equalsIgnoreCase(Constants.sectionOfficeLocation)){
			errorMessagesVectorG.add(section+ " "+ sFieldDescription + " must be numeric and 9 digits in length.");
		}
		else if(sectionName.equalsIgnoreCase(Constants.sectionPhysicians)){
			errorMessagesVectorH.add(section+ " "+sFieldDescription + " must be numeric and 9 digits in length.");
		}
		else if(sectionName.equalsIgnoreCase(Constants.sectionPatientInfo)){
			errorMessagesVectorI.add(section+ " "+sFieldDescription + " must be numeric and 9 digits in length.");
		}
		else if(sectionName.equalsIgnoreCase(Constants.sectionExpertise)){
			errorMessagesVectorJ.add(section+ " "+ sFieldDescription + " must be numeric and 9 digits in length.");
		}
		else if(sectionName.equalsIgnoreCase(Constants.sectionAttachment)){
			errorMessagesVectorK.add(section+ " "+sFieldDescription + " must be numeric and 9 digits in length.");
		}
		else if(sectionName.equalsIgnoreCase(Constants.sectionComments)){
			errorMessagesVectorL.add(section+ " "+sFieldDescription + " must be numeric and 9 digits in length.");
		}
    }
    //PMF Change SSCR 13503 22/6/2012 End
    //  Changes for the state mandate on 02/10/10 start
    private boolean isValidRange(String strCheckValue, int minValue,int maxValue){
    	if (Integer.parseInt(strCheckValue) >= minValue && Integer.parseInt(strCheckValue) <= maxValue){
    		return true;
    	}
    	return false;
    }
    //  Changes for the state mandate on 02/10/10 end
    
	public String getEditAreasOfExpertise() {
		return addAreasOfExpertise;
	}

	public void setEditAreasOfExpertise(String addAreasOfExpertise) {
		this.addAreasOfExpertise = addAreasOfExpertise;
	}

	public String getEditPatientInfo() {
		return addPatientInfo;
	}

	public void setEditPatientInfo(String addPatientInfo) {
		this.addPatientInfo = addPatientInfo;
	}

	public List getPatientInfo() {
		return patientInfo;
	}

	public AreasOfExpertise getAreasOfExpertise() {
		return areasOfExpertise;
	}
	
	public void setAreasOfExpertise(AreasOfExpertise aoe) {
		this.areasOfExpertise = aoe;
	}

	public String getEthnicOrigin() {
		return ethnicOrigin;
	}

	public void setEthnicOrigin(String ethnicOrigin) {
		this.ethnicOrigin = ethnicOrigin;
	}
	
	public String getIndServTypes() {
		return indServTypes;
	}

	public void setIndServTypes(String indServTypes) {
		if(indServTypes!=null)
		{
			this.indServTypes = indServTypes;
		}
	}
	
	
	//  Changes for the state mandate on 02/10/10 start
	public String getCommunityHealthCenter() {
		return communityHealthCenter;
	}

	public void setCommunityHealthCenter(String communityHealthCenter) {
		this.communityHealthCenter = communityHealthCenter;
	}

	public String getDeptOfHealth() {
		return deptOfHealth;
	}

	public void setDeptOfHealth(String deptOfHealth) {
		this.deptOfHealth = deptOfHealth;
	}

	public String getFedQualHealthClinic() {
		return fedQualHealthClinic;
	}

	public void setFedQualHealthClinic(String fedQualHealthClinic) {
		this.fedQualHealthClinic = fedQualHealthClinic;
	}

	public String getGroupPractice() {
		return groupPractice;
	}

	public void setGroupPractice(String groupPractice) {
		this.groupPractice = groupPractice;
	}

	public String getHipIndicator() {
		return hipIndicator;
	}

	public void setHipIndicator(String hipIndicator) {
		this.hipIndicator = hipIndicator;
	}

	public String getHipPCP() {
		return hipPCP;
	}

	public void setHipPCP(String hipPCP) {
		this.hipPCP = hipPCP;
	}

	public String getHipSpecialist() {
		return hipSpecialist;
	}

	public void setHipSpecialist(String hipSpecialist) {
		this.hipSpecialist = hipSpecialist;
	}

	public String getIndivPractice() {
		return indivPractice;
	}

	public void setIndivPractice(String indivPractice) {
		this.indivPractice = indivPractice;
	}

	public String getLanguagesSpoken1() {
		return languagesSpoken1;
	}

	public void setLanguagesSpoken1(String languagesSpoken1) {
		this.languagesSpoken1 = languagesSpoken1;
	}

	public String getLanguagesSpoken2() {
		return languagesSpoken2;
	}

	public void setLanguagesSpoken2(String languagesSpoken2) {
		this.languagesSpoken2 = languagesSpoken2;
	}

	public String getLanguagesSpoken3() {
		return languagesSpoken3;
	}

	public void setLanguagesSpoken3(String languagesSpoken3) {
		this.languagesSpoken3 = languagesSpoken3;
	}

	public String getLanguagesSpoken4() {
		return languagesSpoken4;
	}

	public void setLanguagesSpoken4(String languagesSpoken4) {
		this.languagesSpoken4 = languagesSpoken4;
	}

	public String getLanguagesSpoken5() {
		return languagesSpoken5;
	}

	public void setLanguagesSpoken5(String languagesSpoken5) {
		this.languagesSpoken5 = languagesSpoken5;
	}

	public String getLanguagesSpoken6() {
		return languagesSpoken6;
	}

	public void setLanguagesSpoken6(String languagesSpoken6) {
		this.languagesSpoken6 = languagesSpoken6;
	}

	public String getMedicaidIndicator() {
		return medicaidIndicator;
	}

	public void setMedicaidIndicator(String medicaidIndicator) {
		this.medicaidIndicator = medicaidIndicator;
	}

	public String getMedicaidMaxPanel() {
		return medicaidMaxPanel;
	}

	public void setMedicaidMaxPanel(String medicaidMaxPanel) {
		this.medicaidMaxPanel = medicaidMaxPanel;
	}
	
	public String getHipMaxPanel() {
		return hipMaxPanel;
	}

	
	public void setHipMaxPanel(String hipMaxPanel) {
		this.hipMaxPanel = hipMaxPanel;
	}

	public String getMedicaidPCP() {
		return medicaidPCP;
	}

	public void setMedicaidPCP(String medicaidPCP) {
		this.medicaidPCP = medicaidPCP;
	}

	public String getMedicaidSpecialist() {
		return medicaidSpecialist;
	}

	public void setMedicaidSpecialist(String medicaidSpecialist) {
		this.medicaidSpecialist = medicaidSpecialist;
	}

	public String getOfferECI1() {
		return offerECI1;
	}

	public void setOfferECI1(String offerECI1) {
		this.offerECI1 = offerECI1;
	}

	public String getOfferECI2() {
		return offerECI2;
	}

	public void setOfferECI2(String offerECI2) {
		this.offerECI2 = offerECI2;
	}

	public String getOfferECI3() {
		return offerECI3;
	}

	public void setOfferECI3(String offerECI3) {
		this.offerECI3 = offerECI3;
	}

	public String getOfferECI4() {
		return offerECI4;
	}

	public void setOfferECI4(String offerECI4) {
		this.offerECI4 = offerECI4;
	}

	public String getOfferECI5() {
		return offerECI5;
	}

	public void setOfferECI5(String offerECI5) {
		this.offerECI5 = offerECI5;
	}

	public String getOfferECI6() {
		return offerECI6;
	}

	public void setOfferECI6(String offerECI6) {
		this.offerECI6 = offerECI6;
	}

	public String getOfferEPSDT1() {
		return offerEPSDT1;
	}

	public void setOfferEPSDT1(String offerEPSDT1) {
		this.offerEPSDT1 = offerEPSDT1;
	}

	public String getOfferEPSDT2() {
		return offerEPSDT2;
	}

	public void setOfferEPSDT2(String offerEPSDT2) {
		this.offerEPSDT2 = offerEPSDT2;
	}
	public String getOfferEPSDT3() {
		return offerEPSDT3;
	}

	public void setOfferEPSDT3(String offerEPSDT3) {
		this.offerEPSDT3 = offerEPSDT3;
	}

	public String getOfferEPSDT4() {
		return offerEPSDT4;
	}

	public void setOfferEPSDT4(String offerEPSDT4) {
		this.offerEPSDT4 = offerEPSDT4;
	}

	public String getOfferEPSDT5() {
		return offerEPSDT5;
	}

	public void setOfferEPSDT5(String offerEPSDT5) {
		this.offerEPSDT5 = offerEPSDT5;
	}

	public String getOfferEPSDT6() {
		return offerEPSDT6;
	}

	public void setOfferEPSDT6(String offerEPSDT6) {
		this.offerEPSDT6 = offerEPSDT6;
	}

	public String getOtherPractice() {
		return otherPractice;
	}

	public void setOtherPractice(String otherPractice) {
		this.otherPractice = otherPractice;
	}

	public String getProvideADB1() {
		return provideADB1;
	}

	public void setProvideADB1(String provideADB1) {
		this.provideADB1 = provideADB1;
	}

	public String getProvideADB2() {
		return provideADB2;
	}

	public void setProvideADB2(String provideADB2) {
		this.provideADB2 = provideADB2;
	}

	public String getProvideADB3() {
		return provideADB3;
	}

	public void setProvideADB3(String provideADB3) {
		this.provideADB3 = provideADB3;
	}

	public String getProvideADB4() {
		return provideADB4;
	}

	public void setProvideADB4(String provideADB4) {
		this.provideADB4 = provideADB4;
	}

	public String getProvideADB5() {
		return provideADB5;
	}

	public void setProvideADB5(String provideADB5) {
		this.provideADB5 = provideADB5;
	}

	public String getProvideADB6() {
		return provideADB6;
	}

	public void setProvideADB6(String provideADB6) {
		this.provideADB6 = provideADB6;
	}

	public String getProvideCSHCN1() {
		return provideCSHCN1;
	}

	public void setProvideCSHCN1(String provideCSHCN1) {
		this.provideCSHCN1 = provideCSHCN1;
	}

	public String getProvideCSHCN2() {
		return provideCSHCN2;
	}

	public void setProvideCSHCN2(String provideCSHCN2) {
		this.provideCSHCN2 = provideCSHCN2;
	}

	public String getProvideCSHCN3() {
		return provideCSHCN3;
	}

	public void setProvideCSHCN3(String provideCSHCN3) {
		this.provideCSHCN3 = provideCSHCN3;
	}

	public String getProvideCSHCN4() {
		return provideCSHCN4;
	}

	public void setProvideCSHCN4(String provideCSHCN4) {
		this.provideCSHCN4 = provideCSHCN4;
	}

	public String getProvideCSHCN5() {
		return provideCSHCN5;
	}

	public void setProvideCSHCN5(String provideCSHCN5) {
		this.provideCSHCN5 = provideCSHCN5;
	}

	public String getProvideCSHCN6() {
		return provideCSHCN6;
	}

	public void setProvideCSHCN6(String provideCSHCN6) {
		this.provideCSHCN6 = provideCSHCN6;
	}
	
	public String getMatWaiveredPrescriber1() {
		return matWaiveredPrescriber1;
	}
	
	public void setMatWaiveredPrescriber1(String matWaiveredPrescriber1) {
		this.matWaiveredPrescriber1 = matWaiveredPrescriber1;
	}
	
	public String getCertOpioidTreat1() {
		return certOpioidTreat1;
	}
	
	public void setCertOpioidTreat1(String certOpioidTreat1) {
		this.certOpioidTreat1 = certOpioidTreat1;
	}
	
    public String getMatOpioid1() {
    	return matOpioid1;
    }
    
    public void setMatOpioid1(String matOpioid1) {
    	this.matOpioid1 = matOpioid1;
    }
    
    public String getCounselOpioid1() {
    	return counselOpioid1;
    }
    
    public void setCounselOpioid1(String counselOpioid1) {
    	this.counselOpioid1 = counselOpioid1;
    }
    
    public String getSudProv1() {
    	return sudProv1;
    }
    
    public void setSudProv1(String sudProv1) {
    	this.sudProv1 = sudProv1;
    }
    
    public String getResTreatCtr1() {
    	return resTreatCtr1;
    }
    
    public void setResTreatCtr1(String resTreatCtr1) {
    	this.resTreatCtr1 = resTreatCtr1;
    }
    
    public String getMatWaiveredPrescriber2() {
		return matWaiveredPrescriber2;
	}
	
	public void setMatWaiveredPrescriber2(String matWaiveredPrescriber2) {
		this.matWaiveredPrescriber2 = matWaiveredPrescriber2;
	}
    
    public String getCertOpioidTreat2() {
		return certOpioidTreat2;
	}
	
	public void setCertOpioidTreat2(String certOpioidTreat2) {
		this.certOpioidTreat2 = certOpioidTreat2;
	}
	
    public String getMatOpioid2() {
    	return matOpioid2;
    }
    
    public void setMatOpioid2(String matOpioid2) {
    	this.matOpioid2 = matOpioid2;
    }
    
    public String getCounselOpioid2() {
    	return counselOpioid2;
    }
    
    public void setCounselOpioid2(String counselOpioid2) {
    	this.counselOpioid2 = counselOpioid2;
    }
    
    public String getSudProv2() {
    	return sudProv2;
    }
    
    public void setSudProv2(String sudProv2) {
    	this.sudProv2 = sudProv2;
    }
    
    public String getResTreatCtr2() {
    	return resTreatCtr2;
    }
    
    public void setResTreatCtr2(String resTreatCtr2) {
    	this.resTreatCtr2 = resTreatCtr2;
    }
    
    public String getMatWaiveredPrescriber3() {
		return matWaiveredPrescriber3;
	}
	
	public void setMatWaiveredPrescriber3(String matWaiveredPrescriber3) {
		this.matWaiveredPrescriber3 = matWaiveredPrescriber3;
	}
    
    public String getCertOpioidTreat3() {
		return certOpioidTreat3;
	}
	
	public void setCertOpioidTreat3(String certOpioidTreat3) {
		this.certOpioidTreat3 = certOpioidTreat3;
	}
	
    public String getMatOpioid3() {
    	return matOpioid3;
    }
    
    public void setMatOpioid3(String matOpioid3) {
    	this.matOpioid3 = matOpioid3;
    }
    
    public String getCounselOpioid3() {
    	return counselOpioid3;
    }
    
    public void setCounselOpioid3(String counselOpioid3) {
    	this.counselOpioid3 = counselOpioid3;
    }
    
    public String getSudProv3() {
    	return sudProv3;
    }
    
    public void setSudProv3(String sudProv3) {
    	this.sudProv3 = sudProv3;
    }
    
    public String getResTreatCtr3() {
    	return resTreatCtr3;
    }
    
    public void setResTreatCtr3(String resTreatCtr3) {
    	this.resTreatCtr3 = resTreatCtr3;
    }
    
    public String getMatWaiveredPrescriber4() {
		return matWaiveredPrescriber4;
	}
	
	public void setMatWaiveredPrescriber4(String matWaiveredPrescriber4) {
		this.matWaiveredPrescriber4 = matWaiveredPrescriber4;
	}
    public String getCertOpioidTreat4() {
		return certOpioidTreat4;
	}
	
	public void setCertOpioidTreat4(String certOpioidTreat4) {
		this.certOpioidTreat4 = certOpioidTreat4;
	}
	
    public String getMatOpioid4() {
    	return matOpioid4;
    }
    
    public void setMatOpioid4(String matOpioid4) {
    	this.matOpioid4 = matOpioid4;
    }
    
    public String getCounselOpioid4() {
    	return counselOpioid4;
    }
    
    public void setCounselOpioid4(String counselOpioid4) {
    	this.counselOpioid4 = counselOpioid4;
    }
    
    public String getSudProv4() {
    	return sudProv4;
    }
    
    public void setSudProv4(String sudProv4) {
    	this.sudProv4 = sudProv4;
    }
    
    public String getResTreatCtr4() {
    	return resTreatCtr4;
    }
    
    public void setResTreatCtr4(String resTreatCtr4) {
    	this.resTreatCtr4 = resTreatCtr4;
    }
    
    public String getMatWaiveredPrescriber5() {
		return matWaiveredPrescriber5;
	}
	
	public void setMatWaiveredPrescriber5(String matWaiveredPrescriber5) {
		this.matWaiveredPrescriber5 = matWaiveredPrescriber5;
	}
	
    public String getCertOpioidTreat5() {
		return certOpioidTreat5;
	}
	
	public void setCertOpioidTreat5(String certOpioidTreat5) {
		this.certOpioidTreat5 = certOpioidTreat5;
	}
	
    public String getMatOpioid5() {
    	return matOpioid5;
    }
    
    public void setMatOpioid5(String matOpioid5) {
    	this.matOpioid5 = matOpioid5;
    }
    
    public String getCounselOpioid5() {
    	return counselOpioid5;
    }
    
    public void setCounselOpioid5(String counselOpioid5) {
    	this.counselOpioid5 = counselOpioid5;
    }
    
    public String getSudProv5() {
    	return sudProv5;
    }
    
    public void setSudProv5(String sudProv5) {
    	this.sudProv5 = sudProv5;
    }
    
    public String getResTreatCtr5() {
    	return resTreatCtr5;
    }
    
    public void setResTreatCtr5(String resTreatCtr5) {
    	this.resTreatCtr5 = resTreatCtr5;
    }
    
    public String getMatWaiveredPrescriber6() {
		return matWaiveredPrescriber6;
	}
	
	public void setMatWaiveredPrescriber6(String matWaiveredPrescriber6) {
		this.matWaiveredPrescriber6 = matWaiveredPrescriber6;
	}
    
    public String getCertOpioidTreat6() {
		return certOpioidTreat6;
	}
	
	public void setCertOpioidTreat6(String certOpioidTreat6) {
		this.certOpioidTreat6 = certOpioidTreat6;
	}
	
    public String getMatOpioid6() {
    	return matOpioid6;
    }
    
    public void setMatOpioid6(String matOpioid6) {
    	this.matOpioid6 = matOpioid6;
    }
    
    public String getCounselOpioid6() {
    	return counselOpioid6;
    }
    
    public void setCounselOpioid6(String counselOpioid6) {
    	this.counselOpioid6 = counselOpioid6;
    }
    
    public String getSudProv6() {
    	return sudProv6;
    }
    
    public void setSudProv6(String sudProv6) {
    	this.sudProv6 = sudProv6;
    }
    
    public String getResTreatCtr6() {
    	return resTreatCtr6;
    }
    
    public void setResTreatCtr6(String resTreatCtr6) {
    	this.resTreatCtr6 = resTreatCtr6;
    }

	public String getRadFreeStandingCenter() {
		return radFreeStandingCenter;
	}

	public void setRadFreeStandingCenter(String radFreeStandingCenter) {
		this.radFreeStandingCenter = radFreeStandingCenter;
	}

	public String getRadHospBased() {
		return radHospBased;
	}

	public void setRadHospBased(String radHospBased) {
		this.radHospBased = radHospBased;
	}

	public String getRuralHealthClinic() {
		return ruralHealthClinic;
	}

	public void setRuralHealthClinic(String ruralHealthClinic) {
		this.ruralHealthClinic = ruralHealthClinic;
	}

	public String getSchoolBasedClinic() {
		return schoolBasedClinic;
	}

	public void setSchoolBasedClinic(String schoolBasedClinic) {
		this.schoolBasedClinic = schoolBasedClinic;
	}

	public String getSsDental() {
		return ssDental;
	}

	public void setSsDental(String ssDental) {
		this.ssDental = ssDental;
	}

	public String getSsMedical() {
		return ssMedical;
	}

	public void setSsMedical(String ssMedical) {
		this.ssMedical = ssMedical;
	}

	public String getSsOtherServType() {
		return ssOtherServType;
	}

	public void setSsOtherServType(String ssOtherServType) {
		this.ssOtherServType = ssOtherServType;
	}

	public String getSsVision() {
		return ssVision;
	}

	public void setSsVision(String ssVision) {
		this.ssVision = ssVision;
	}

	public String getTribalHealthCenter() {
		return tribalHealthCenter;
	}

	public void setTribalHealthCenter(String tribalHealthCenter) {
		this.tribalHealthCenter = tribalHealthCenter;
	}
	
	//  Changes for the state mandate on 02/10/10 end
	 
	
	//  Changes for the security scan on 03/05/10 start
	
	/** This method checks for the bad characters in all the input fields except numeric only fields
	 * 	@return true/false based on whether the input fields has bad characters or not
	 */
   private boolean checkBadChars() {
	   int badCharGroup1 = 1;
	   int badCharGroup2 = 2;
	   int badCharGroup3 = 3;
	   int badCharGroup5 = 5;
	   editBadChars(false, pracName1, badCharGroup2,Constants.sectionGeneralInfo+" Group practice name",Constants.sectionGeneralInfo);
	   editBadChars(false, anthemPIN, badCharGroup1,Constants.sectionGeneralInfo+" IN, KY and OH Provider Id Number",Constants.sectionGeneralInfo);
	   editBadChars(false, m_moId, badCharGroup1, Constants.sectionGeneralInfo+" Missouri Provider Id Number",Constants.sectionGeneralInfo);
	   editBadChars(false, m_wiId, badCharGroup1, Constants.sectionGeneralInfo+" Wisconsin Provider Id Number",Constants.sectionGeneralInfo);
	   //PMF changes for section B error messages
	   editBadChars(false, oldTaxID, badCharGroup1, Constants.sectionReasonSubmitting+" old tax id",Constants.sectionReasonSubmitting);
	 //PMF changes for section B error messages
	   /*2013 SSCR 13503 change
	   editBadChars(false, grpMedicaidId, badCharGroup1,Constants.sectionGeneralInfo+" Group Medicaid Id Number",Constants.sectionGeneralInfo);
	   */
	   editBadChars(false, m_grpNPINumber, badCharGroup1,Constants.sectionGeneralInfo+" Group National Provider Identification Number",Constants.sectionGeneralInfo);
	   editBadChars(false, delReason, badCharGroup3,Constants.sectionReasonSubmitting+ " Reason for Deleting Provider",Constants.sectionReasonSubmitting);
	   //changed boolean type as true to implement required
	   editBadChars(false, provFnm, badCharGroup2,Constants.sectionProviderInfo+" Provider First Name",Constants.sectionProviderInfo);
	   editBadChars(false, provMI, badCharGroup2,Constants.sectionProviderInfo+" Provider MI",Constants.sectionProviderInfo);
	   editBadChars(false, provLnm, badCharGroup2,Constants.sectionProviderInfo+" Provider Last Name",Constants.sectionProviderInfo);
	   editBadChars(false, provSSN, badCharGroup1,Constants.sectionProviderInfo+" social security number",Constants.sectionProviderInfo);
	   editBadChars(false, title, badCharGroup2,Constants.sectionProviderInfo+" Title",Constants.sectionProviderInfo);
	   editBadChars(false, primSpecialtyPhy, badCharGroup1,Constants.sectionProviderInfo+" Primary Specialty Physician",Constants.sectionProviderInfo);
	   editBadChars(false, specialtyCarePhy, badCharGroup1,Constants.sectionProviderInfo+" Specialty Care Physician",Constants.sectionProviderInfo);
	   editBadChars(false, other, badCharGroup1,Constants.sectionProviderInfo+" Other",Constants.sectionProviderInfo);
	   /*2013 SSCR 13503 changes
	   editBadChars(false, m_taxonomyNum, badCharGroup1,Constants.sectionProviderInfo+" Taxonomy Number",Constants.sectionProviderInfo);
	   */
	   //PMF Change SSCR 13503 21/6/2012 Start
	   editBadChars(false, fwdHealthCertNPI1, badCharGroup1,Constants.sectionProviderInfo+" First ForwardHealth Certified NPI",Constants.sectionProviderInfo);
	   editBadChars(false, fwdHealthCertNPI2, badCharGroup1, Constants.sectionProviderInfo+" Second ForwardHealth Certified NPI",Constants.sectionProviderInfo);
	   editBadChars(false, fwdHealthCertNPI3, badCharGroup1, Constants.sectionProviderInfo+" Third ForwardHealth Certified NPI",Constants.sectionProviderInfo);
	   editBadChars(false, provDOB, badCharGroup1, Constants.sectionProviderInfo+" Provider Date of Birth",Constants.sectionProviderInfo);
	   //PMF Change SSCR 13503 22/6/2012 End
	   /*editBadChars(false, m_provNPINumber, badCharGroup1, Constants.sectionProviderInfo+ " national provider identification number");*/
	   //editBadChars(false, upinNumber, badCharGroup1, "UPIN Number",Constants.sectionProviderInfo);
	   editBadChars(false, profLicenseNumber, badCharGroup1, Constants.sectionProviderInfo+" Professional License Number",Constants.sectionProviderInfo);
	   /*2013 SSCR 13503 change
	   editBadChars(false, stateLicIssueDt, badCharGroup1,Constants.sectionProviderInfo + " state license number issue date",Constants.sectionProviderInfo);
	   editBadChars(false, stateLicExpDt, badCharGroup1,Constants.sectionProviderInfo + " state license number expiration date",Constants.sectionProviderInfo);
	   */
	   editBadChars(false, caqhIDNumber, badCharGroup1,Constants.sectionProviderInfo+" CAQH ID Number",Constants.sectionProviderInfo);
	   editBadChars(false, caqhExplanation, badCharGroup1, Constants.sectionProviderInfo+" Current Status of CAQH",Constants.sectionProviderInfo);
	   editBadChars(false, mgdCareDisenroll, badCharGroup1, Constants.sectionProviderInfo + " Is provider disenrolling from one Managed Care Entity to enroll in another Managed Care Entity",Constants.sectionProviderInfo);
	   editBadChars(false, pmp, badCharGroup1, Constants.sectionProviderInfo + " Is provider a PMP",Constants.sectionProviderInfo);
	   editBadChars(false,pmpSpecialty,badCharGroup1,Constants.sectionProviderInfo + " PMP Speciality",Constants.sectionProviderInfo);
	   editBadChars(false,hospAdmitPriv,badCharGroup1,Constants.sectionProviderInfo + " Hospital Admitting Privileges",Constants.sectionProviderInfo);
	   editBadChars(false,deliveryPriv,badCharGroup1,Constants.sectionProviderInfo + " Delivery Privileges",Constants.sectionProviderInfo);
	   editBadChars(false,relationshipPriv,badCharGroup1,Constants.sectionProviderInfo + " Relationship Privileges",Constants.sectionProviderInfo);
	   editBadChars(false,ageRestriction,badCharGroup3,Constants.sectionProviderInfo + " Age Restriction",Constants.sectionProviderInfo);
	   editBadChars(false,pmpScopeOb,badCharGroup1,Constants.sectionProviderInfo + " PMP Scope OB",Constants.sectionProviderInfo);
	   editBadChars(false,pmpScopeAll,badCharGroup1,Constants.sectionProviderInfo + " PMP Scope All Women",Constants.sectionProviderInfo);
	   editBadChars(false,genderScope,badCharGroup1,Constants.sectionProviderInfo + " Gender Scope",Constants.sectionProviderInfo);
	   editBadChars(false,medPanelStatus,badCharGroup1,Constants.sectionProviderInfo + " Panel Status",Constants.sectionProviderInfo);
	   editBadChars(false,medPldPanelDecrease,badCharGroup1,Constants.sectionProviderInfo + " Panel Limit Decrease",Constants.sectionProviderInfo);
	   editBadChars(false,medPliPanelIncrease,badCharGroup1,Constants.sectionProviderInfo + " Panel Limit Increase",Constants.sectionProviderInfo);
	   editBadChars(false,medPanelHold,badCharGroup1,Constants.sectionProviderInfo + " Med Panel Hold",Constants.sectionProviderInfo);
	   editBadChars(false,	medPanelHoldRemove,	badCharGroup1,Constants.sectionProviderInfo + " Med Panel Hold Remove",Constants.sectionProviderInfo);
	   editBadChars(false,enrollAs,badCharGroup2,Constants.sectionProviderInfo + " Enroll As",Constants.sectionProviderInfo);
	   editBadChars(false,locationType,badCharGroup1,Constants.sectionProviderInfo + " Are you a..",Constants.sectionProviderInfo);
	   editBadChars(false,	npPractice,	badCharGroup1,Constants.sectionProviderInfo + " Do you utilize a Nurse Practitioner NP option",Constants.sectionProviderInfo);
	   editBadChars(false,paPractice,badCharGroup1,Constants.sectionProviderInfo + " Do you utilize a Nurse Practitioner PA option",Constants.sectionProviderInfo);
	   editBadChars(false,naPractice,badCharGroup1,Constants.sectionProviderInfo + " Do you utilize a Nurse Practitioner NA option",Constants.sectionProviderInfo);
	   editBadChars(false,malPracInsRevoke,badCharGroup1,Constants.sectionProviderInfo + " Has your malpractice insurance ever been terminated or revoked except with your consent or request",Constants.sectionProviderInfo);
	   editBadChars(false,underGovInvestigation,badCharGroup1,Constants.sectionProviderInfo + " Are you currently under investigation by any government agency?",Constants.sectionProviderInfo);
	   editBadChars(false,expellMedPay,badCharGroup1,Constants.sectionProviderInfo + " Have you been expelled or suspended from receiving payment under Medicare or Medicaid?",Constants.sectionProviderInfo);
	   editBadChars(false,confirmation,badCharGroup1,Constants.sectionComments + " Confirmation","SectionK");
	   editBadChars(false, pracOfficeAddress1, badCharGroup2, Constants.sectionPracticeAddress + " Practice Street Address", Constants.sectionPracticeAddress);
	   editBadChars(false, pracOfficeCity1, badCharGroup2, Constants.sectionPracticeAddress + " Practice City", Constants.sectionPracticeAddress);
	   editBadChars(false, pracOfficeState1, badCharGroup2, Constants.sectionPracticeAddress + " Practice State", Constants.sectionPracticeAddress);
	   editBadChars(false, pracOfficeCounty1, badCharGroup2, Constants.sectionPracticeAddress + " Practice County", Constants.sectionPracticeAddress);
	   editBadChars(false, pracOfficeEmail1, badCharGroup1, Constants.sectionPracticeAddress + " group email address", Constants.sectionPracticeAddress);
	   editBadChars(false, languagesSpoken1, badCharGroup1, Constants.sectionPracticeAddress + " Practice Languages Spoken", Constants.sectionPracticeAddress);
	   
	   editBadChars(false, pracBillAddress1, badCharGroup2, Constants.sectionPracticeAddress + " street address for payment", Constants.sectionPracticeAddress);
	   editBadChars(false, pracBillContactName1, badCharGroup2, Constants.sectionPracticeAddress + " contact name for payment", Constants.sectionPracticeAddress);
	   editBadChars(false, pracBillCity1, badCharGroup2, Constants.sectionPracticeAddress + " city for payment ", Constants.sectionPracticeAddress);
	   editBadChars(false, pracBillState1, badCharGroup2, Constants.sectionPracticeAddress + " state for payment ", Constants.sectionPracticeAddress);
	   editBadChars(false, pracBillCounty1, badCharGroup2, Constants.sectionPracticeAddress + " county for payment", Constants.sectionPracticeAddress);
	   editBadChars(false, pracBillContactEmail1, badCharGroup1, Constants.sectionPracticeAddress + " email address for payment ", Constants.sectionPracticeAddress);
	   editBadChars(false, billMedicareGroup1, badCharGroup1, Constants.sectionPracticeAddress + " Medicare Group Number", Constants.sectionPracticeAddress);
	   editBadChars(false, billMedicareIndividual1, badCharGroup1, Constants.sectionPracticeAddress + " Medicare Individual Number", Constants.sectionPracticeAddress);
	   editBadChars(false, medicaidGroup1, badCharGroup1, Constants.sectionPracticeAddress + " Medicaid Group Number", Constants.sectionPracticeAddress);
	   editBadChars(false, medicaidIndividual1, badCharGroup1, Constants.sectionPracticeAddress + " Medicaid Individual Number", Constants.sectionPracticeAddress);
	   editBadChars(false, kyMedicaidPart1, badCharGroup5, Constants.sectionPracticeAddress + " Kentucky Medicaid Program", Constants.sectionPracticeAddress);
	   //editBadChars(false, kyMedicaidId1, badCharGroup5, Constants.sectionPracticeAddress + " Kentucky Medicaid ID", Constants.sectionPracticeAddress);
	   
	   editBadChars(false, pracOfficeAddress2, badCharGroup2, Constants.sectionAddressChange + " Practice Street Address", Constants.sectionAddressChange);
	   editBadChars(false, pracOfficeCity2, badCharGroup2, Constants.sectionAddressChange + " Practice City", Constants.sectionAddressChange);
	   editBadChars(false, pracOfficeState2, badCharGroup2, Constants.sectionAddressChange + " Practice State", Constants.sectionAddressChange);
	   editBadChars(false, pracOfficeCounty2, badCharGroup2, Constants.sectionAddressChange + " Practice County", Constants.sectionAddressChange);
	   editBadChars(false, pracOfficeEmail2, badCharGroup1, Constants.sectionAddressChange + " group email address", Constants.sectionAddressChange);
	   editBadChars(false, languagesSpoken2, badCharGroup1, Constants.sectionAddressChange + " Practice Languages Spoken", Constants.sectionAddressChange);
	   editBadChars(false, pracBillAddress2, badCharGroup2, Constants.sectionAddressChange + " street address for payment", Constants.sectionAddressChange);
	   editBadChars(false, pracBillContactName2, badCharGroup2, Constants.sectionAddressChange + " contact name for payment", Constants.sectionAddressChange);
	   editBadChars(false, pracBillCity2, badCharGroup2, Constants.sectionAddressChange + " city for payment ", Constants.sectionAddressChange);
	   editBadChars(false, pracBillState2, badCharGroup2, Constants.sectionAddressChange + " state for payment ", Constants.sectionAddressChange);
	   editBadChars(false, pracBillCounty2, badCharGroup2, Constants.sectionAddressChange + " county for payment ", Constants.sectionAddressChange);
	   editBadChars(false, pracBillContactEmail2, badCharGroup1, Constants.sectionAddressChange + " email address for payment ", Constants.sectionAddressChange);
	   editBadChars(false, billMedicareGroup2, badCharGroup1, Constants.sectionAddressChange + " Medicare Group Number", Constants.sectionAddressChange);
	   editBadChars(false, billMedicareIndividual2, badCharGroup1, Constants.sectionAddressChange + " Medicare Individual Number", Constants.sectionAddressChange);
	   editBadChars(false, medicaidGroup2, badCharGroup1, Constants.sectionAddressChange + " Medicaid Group Number", Constants.sectionAddressChange);
	   editBadChars(false, medicaidIndividual2, badCharGroup1, Constants.sectionAddressChange + " Medicaid Individual Number", Constants.sectionAddressChange);
	   editBadChars(false, kyMedicaidPart2, badCharGroup5, Constants.sectionAddressChange + " Kentucky Medicaid Program", Constants.sectionAddressChange);
	   //editBadChars(false, kyMedicaidId2, badCharGroup5, Constants.sectionAddressChange + " Kentucky Medicaid ID", Constants.sectionAddressChange);
	   
	   editBadChars(false, pracOfficeAddress3, badCharGroup2, Constants.sectionOfficeLocation + " Second Practice Street Address", Constants.sectionOfficeLocation);
	   editBadChars(false, pracOfficeCity3, badCharGroup2, Constants.sectionOfficeLocation + " Second Practice City", Constants.sectionOfficeLocation);
	   editBadChars(false, pracOfficeState3, badCharGroup2, Constants.sectionOfficeLocation + " Second Practice State", Constants.sectionOfficeLocation);
	   editBadChars(false, pracOfficeCounty3, badCharGroup2, Constants.sectionOfficeLocation + " Second Practice County", Constants.sectionOfficeLocation);
	   editBadChars(false, pracOfficeEmail3, badCharGroup1, Constants.sectionOfficeLocation + " Second group email address", Constants.sectionOfficeLocation);
	   editBadChars(false, languagesSpoken3, badCharGroup1, Constants.sectionOfficeLocation + " Second Practice Languages Spoken", Constants.sectionOfficeLocation);
	   editBadChars(false, pracBillContactName3, badCharGroup2, Constants.sectionOfficeLocation + ", Second contact name for payment", Constants.sectionOfficeLocation);
	   editBadChars(false, pracBillAddress3, badCharGroup2, Constants.sectionOfficeLocation + " Second street address for payment", Constants.sectionOfficeLocation);
	   editBadChars(false, pracBillCity3, badCharGroup2, Constants.sectionOfficeLocation + " Second city for payment ", Constants.sectionOfficeLocation);
	   editBadChars(false, pracBillState3, badCharGroup2, Constants.sectionOfficeLocation + " Second state for payment ", Constants.sectionOfficeLocation);
	   editBadChars(false, pracBillCounty3, badCharGroup2, Constants.sectionOfficeLocation + " Second county for payment ", Constants.sectionOfficeLocation);
	   editBadChars(false, pracBillContactEmail3, badCharGroup1, Constants.sectionOfficeLocation + ", Second email address for payment ", Constants.sectionOfficeLocation);
	   editBadChars(false, billMedicareGroup3, badCharGroup1, Constants.sectionOfficeLocation + " Second Medicare Group Number", Constants.sectionOfficeLocation);
	   editBadChars(false, billMedicareIndividual3, badCharGroup1, Constants.sectionOfficeLocation + " Second Medicare Individual Number", Constants.sectionOfficeLocation);
	   editBadChars(false, medicaidGroup3, badCharGroup1, Constants.sectionOfficeLocation + " Second Medicaid Group Number", Constants.sectionOfficeLocation);
	   editBadChars(false, medicaidIndividual3, badCharGroup1, Constants.sectionOfficeLocation + " Second Medicaid Individual Number", Constants.sectionOfficeLocation);
	   editBadChars(false, kyMedicaidPart3, badCharGroup5, Constants.sectionOfficeLocation + " Kentucky Medicaid Program", Constants.sectionOfficeLocation);
	   //editBadChars(false, kyMedicaidId3, badCharGroup5, Constants.sectionOfficeLocation + "- Location 2 Kentucky Medicaid ID", Constants.sectionOfficeLocation);
	   
	   editBadChars(false, pracOfficeAddress4, badCharGroup2, Constants.sectionOfficeLocation + " Third Practice Street Address", Constants.sectionOfficeLocation);
	   editBadChars(false, pracOfficeCity4, badCharGroup2, Constants.sectionOfficeLocation + " Third Practice City", Constants.sectionOfficeLocation);
	   editBadChars(false, pracOfficeState4, badCharGroup2, Constants.sectionOfficeLocation + " Third Practice State", Constants.sectionOfficeLocation);
	   editBadChars(false, pracOfficeCounty4, badCharGroup2, Constants.sectionOfficeLocation + " Third Practice County", Constants.sectionOfficeLocation);
	   editBadChars(false, pracOfficeEmail4, badCharGroup1, Constants.sectionOfficeLocation + " Third group email address", Constants.sectionOfficeLocation);
	   editBadChars(false, languagesSpoken4, badCharGroup1, Constants.sectionOfficeLocation + " Third Practice Languages Spoken", Constants.sectionOfficeLocation);
	   editBadChars(false, pracBillContactName4, badCharGroup2, Constants.sectionOfficeLocation + ", Third contact name for payment", Constants.sectionOfficeLocation);
	   editBadChars(false, pracBillAddress4, badCharGroup2, Constants.sectionOfficeLocation + " Third street address for payment", Constants.sectionOfficeLocation);
	   editBadChars(false, pracBillCity4, badCharGroup2, Constants.sectionOfficeLocation + " Third city for payment ", Constants.sectionOfficeLocation);
	   editBadChars(false, pracBillState4, badCharGroup2, Constants.sectionOfficeLocation + " Third state for payment ", Constants.sectionOfficeLocation);
	   editBadChars(false, pracBillCounty4, badCharGroup2, Constants.sectionOfficeLocation + " Third county for payment ", Constants.sectionOfficeLocation);
	   editBadChars(false, pracBillContactEmail4, badCharGroup1, Constants.sectionOfficeLocation + ", Third email address for payment ", Constants.sectionOfficeLocation);
	   editBadChars(false, billMedicareGroup4, badCharGroup1, Constants.sectionOfficeLocation + " Third Medicare Group Number", Constants.sectionOfficeLocation);
	   editBadChars(false, billMedicareIndividual4, badCharGroup1, Constants.sectionOfficeLocation + " Third Medicare Individual Number", Constants.sectionOfficeLocation);
	   editBadChars(false, medicaidGroup4, badCharGroup1, Constants.sectionOfficeLocation + " Third Medicaid Group Number", Constants.sectionOfficeLocation);
	   editBadChars(false, medicaidIndividual4, badCharGroup1, Constants.sectionOfficeLocation + " Third Medicaid Individual Number", Constants.sectionOfficeLocation);
	   editBadChars(false, kyMedicaidPart4, badCharGroup5, Constants.sectionOfficeLocation + " Kentucky Medicaid Program", Constants.sectionOfficeLocation);
	   //editBadChars(false, kyMedicaidId4, badCharGroup5, Constants.sectionOfficeLocation + "- Location 3 Kentucky Medicaid ID", Constants.sectionOfficeLocation);
	   
	   editBadChars(false, pracOfficeAddress5, badCharGroup2, Constants.sectionOfficeLocation + " Fourth Practice Street Address", Constants.sectionOfficeLocation);
	   editBadChars(false, pracOfficeCity5, badCharGroup2, Constants.sectionOfficeLocation + " Fourth Practice City", Constants.sectionOfficeLocation);
	   editBadChars(false, pracOfficeState5, badCharGroup2, Constants.sectionOfficeLocation + " Fourth Practice State", Constants.sectionOfficeLocation);
	   editBadChars(false, pracOfficeCounty5, badCharGroup2, Constants.sectionOfficeLocation + " Fourth Practice County", Constants.sectionOfficeLocation);
	   editBadChars(false, pracOfficeEmail5, badCharGroup1, Constants.sectionOfficeLocation + " Fourth group email address", Constants.sectionOfficeLocation);
	   editBadChars(false, languagesSpoken5, badCharGroup1, Constants.sectionOfficeLocation + " Fourth Practice Languages Spoken", Constants.sectionOfficeLocation);
	   editBadChars(false, pracBillContactName5, badCharGroup2, Constants.sectionOfficeLocation + ", Fourth contact name for payment", Constants.sectionOfficeLocation);
	   editBadChars(false, pracBillAddress5, badCharGroup2, Constants.sectionOfficeLocation + " Fourth street address for payment", Constants.sectionOfficeLocation);
	   editBadChars(false, pracBillCity5, badCharGroup2, Constants.sectionOfficeLocation + " Fourth city for payment ", Constants.sectionOfficeLocation);
	   editBadChars(false, pracBillState5, badCharGroup2, Constants.sectionOfficeLocation + " Fourth state for payment ", Constants.sectionOfficeLocation);
	   editBadChars(false, pracBillCounty5, badCharGroup2, Constants.sectionOfficeLocation + " Fourth county for payment ", Constants.sectionOfficeLocation);
	   editBadChars(false, pracBillContactEmail5, badCharGroup1, Constants.sectionOfficeLocation + ", Fourth email address for payment ", Constants.sectionOfficeLocation);
	   editBadChars(false, billMedicareGroup5, badCharGroup1, Constants.sectionOfficeLocation + " Fourth Medicare Group Number", Constants.sectionOfficeLocation);
	   editBadChars(false, billMedicareIndividual5, badCharGroup1, Constants.sectionOfficeLocation + " Fourth Medicare Individual Number", Constants.sectionOfficeLocation);
	   editBadChars(false, medicaidGroup5, badCharGroup1, Constants.sectionOfficeLocation + " Fourth Medicaid Group Number", Constants.sectionOfficeLocation);
	   editBadChars(false, medicaidIndividual5, badCharGroup1, Constants.sectionOfficeLocation + " Fourth Medicaid Individual Number", Constants.sectionOfficeLocation);
	   editBadChars(false, kyMedicaidPart5, badCharGroup5, Constants.sectionOfficeLocation + " Kentucky Medicaid Program", Constants.sectionOfficeLocation);
	   //editBadChars(false, kyMedicaidId5, badCharGroup5, Constants.sectionOfficeLocation + "- Location 4 Kentucky Medicaid ID", Constants.sectionOfficeLocation);
	   
	   editBadChars(false, pracOfficeAddress6, badCharGroup2, Constants.sectionOfficeLocation + " Fifth Practice Street Address", Constants.sectionOfficeLocation);
	   editBadChars(false, pracOfficeCity6, badCharGroup2, Constants.sectionOfficeLocation + " Fifth Practice City", Constants.sectionOfficeLocation);
	   editBadChars(false, pracOfficeState6, badCharGroup2, Constants.sectionOfficeLocation + " Fifth Practice State", Constants.sectionOfficeLocation);
	   editBadChars(false, pracOfficeCounty6, badCharGroup2, Constants.sectionOfficeLocation + " Fifth Practice County", Constants.sectionOfficeLocation);
	   editBadChars(false, pracOfficeEmail6, badCharGroup1, Constants.sectionOfficeLocation + " Fifth group email address", Constants.sectionOfficeLocation);
	   editBadChars(false, languagesSpoken6, badCharGroup1, Constants.sectionOfficeLocation + " Fifth Practice Languages Spoken", Constants.sectionOfficeLocation);
	   editBadChars(false, pracBillContactName6, badCharGroup2, Constants.sectionOfficeLocation + ", Fifth contact name for payment", Constants.sectionOfficeLocation);
	   editBadChars(false, pracBillAddress6, badCharGroup2, Constants.sectionOfficeLocation + " Fifth street address for payment", Constants.sectionOfficeLocation);
	   editBadChars(false, pracBillCity6, badCharGroup2, Constants.sectionOfficeLocation + " Fifth city for payment ", Constants.sectionOfficeLocation);
	   editBadChars(false, pracBillState6, badCharGroup2, Constants.sectionOfficeLocation + " Fifth state for payment ", Constants.sectionOfficeLocation);
	   editBadChars(false, pracBillCounty6, badCharGroup2, Constants.sectionOfficeLocation + " Fifth county for payment ", Constants.sectionOfficeLocation);
	   editBadChars(false, pracBillContactEmail6, badCharGroup1, Constants.sectionOfficeLocation + ", Fifth email address for payment ", Constants.sectionOfficeLocation);
	   editBadChars(false, billMedicareGroup6, badCharGroup1, Constants.sectionOfficeLocation + " Fifth Medicare Group Number", Constants.sectionOfficeLocation);
	   editBadChars(false, billMedicareIndividual6, badCharGroup1, Constants.sectionOfficeLocation + " Fifth Medicare Individual Number", Constants.sectionOfficeLocation);
	   editBadChars(false, medicaidGroup6, badCharGroup1, Constants.sectionOfficeLocation + " Fifth Medicaid Group Number", Constants.sectionOfficeLocation);
	   editBadChars(false, medicaidIndividual6, badCharGroup1, Constants.sectionOfficeLocation + " Fifth Medicaid Individual Number", Constants.sectionOfficeLocation);
	   editBadChars(false, kyMedicaidPart6, badCharGroup5, Constants.sectionOfficeLocation + " Kentucky Medicaid Program", Constants.sectionOfficeLocation);
	   //editBadChars(false, kyMedicaidId6, badCharGroup5, Constants.sectionOfficeLocation + "- Location 5 Kentucky Medicaid ID", Constants.sectionOfficeLocation);
	   
	   editBadChars(false, grpEntityName1, badCharGroup2, Constants.sectionPhysicians + " First Covering Physician Group Entity Name", Constants.sectionPhysicians);
	   editBadChars(false, specialty1, badCharGroup1, Constants.sectionPhysicians + " First Covering Physician Specialty", Constants.sectionPhysicians);
	   editBadChars(false, grpEntityName2, badCharGroup2, Constants.sectionPhysicians + " Second Covering Physician Group Entity Name", Constants.sectionPhysicians);
	   editBadChars(false, specialty2, badCharGroup1, Constants.sectionPhysicians + " Second Covering Physician Specialty", Constants.sectionPhysicians);
	   editBadChars(false, grpEntityName3, badCharGroup2, Constants.sectionPhysicians + " Third Covering Physician Group Entity Name", Constants.sectionPhysicians);
	   editBadChars(false, specialty3, badCharGroup1, Constants.sectionPhysicians + " Third Covering Physician Specialty", Constants.sectionPhysicians);
	   editBadChars(false, grpEntityName4, badCharGroup2, Constants.sectionPhysicians + " Fourth Covering Physician Group Entity Name", Constants.sectionPhysicians);
	   editBadChars(false, specialty4, badCharGroup1, Constants.sectionPhysicians + " Fourth Covering Physician Specialty", Constants.sectionPhysicians);
	   editBadChars(false, grpEntityName5, badCharGroup2, Constants.sectionPhysicians + " Fifth Covering Physician Group Entity Name", Constants.sectionPhysicians);
	   editBadChars(false, specialty5, badCharGroup1, Constants.sectionPhysicians + " Fifth Covering Physician Specialty", Constants.sectionPhysicians);
	 
	   editBadChars(false, ihcpProvNo, badCharGroup2, Constants.sectionProviderInfo + " IHCP provider number", Constants.sectionProviderInfo);
	   
	   for (int i = 0; i < apHospitalName.length; i++)
	   {
		   editBadChars(false, apHospitalName[i], badCharGroup2,Constants.sectionProviderInfo + Constants.ORDIANAL_NUMBERS_LOWERCASE[i] + " admitting name of hospital", Constants.sectionProviderInfo);
		   if (!isEmpty(apHospitalAddress[i]))
			   	editBadChars(false, apHospitalAddress[i], badCharGroup2,Constants.sectionProviderInfo + Constants.ORDIANAL_NUMBERS_LOWERCASE[i] + " admitting address", Constants.sectionProviderInfo);						
		   if (!isEmpty(apHospitalCity[i]))
			   editBadChars(false, apHospitalCity[i], badCharGroup1,Constants.sectionProviderInfo + Constants.ORDIANAL_NUMBERS_LOWERCASE[i] + " admitting city", Constants.sectionProviderInfo);
		   if (!isEmpty(apHospitalState[i]))
			   editBadChars(false, apHospitalState[i], badCharGroup1,Constants.sectionProviderInfo + Constants.ORDIANAL_NUMBERS_LOWERCASE[i] + " admitting state", Constants.sectionProviderInfo);
		  /* if (!isEmpty(apHospitalZip[i]))
			   editBadChars(false, apHospitalZip[i], badCharGroup1,Constants.sectionProviderInfo + Constants.ORDIANAL_NUMBERS_LOWERCASE[i] + " admitting zip");*/
	   }
	   
	   for (int i = 0; i < dpHospitalName.length; i++)
	   {
		   editBadChars(false, dpHospitalName[i], badCharGroup2,Constants.sectionProviderInfo +  Constants.ORDIANAL_NUMBERS_LOWERCASE[i] + " delivery name of hospital", Constants.sectionProviderInfo);
		   if (!isEmpty(dpHospitalAddress[i]))
			   editBadChars(false, dpHospitalAddress[i], badCharGroup2,Constants.sectionProviderInfo +  Constants.ORDIANAL_NUMBERS_LOWERCASE[i] + " delivery hospital address", Constants.sectionProviderInfo);
		   if (!isEmpty(dpHospitalCity[i]))
			   editBadChars(false, dpHospitalCity[i], badCharGroup1,Constants.sectionProviderInfo +  Constants.ORDIANAL_NUMBERS_LOWERCASE[i] + " delivery hospital city", Constants.sectionProviderInfo);
		   if (!isEmpty(dpHospitalState[i]))
			   editBadChars(false, dpHospitalState[i], badCharGroup1,Constants.sectionProviderInfo +  Constants.ORDIANAL_NUMBERS_LOWERCASE[i] + " delivery hospital state", Constants.sectionProviderInfo);
		  /* if (!isEmpty(dpHospitalZip[i]))
			   editBadChars(false, dpHospitalZip[i], badCharGroup1,Constants.sectionProviderInfo +  Constants.ORDIANAL_NUMBERS_LOWERCASE[i] + " delivery hospital zip");*/
	   }
	   //	TODO remove section info from below error msgs of covering physicians.
	   for (int i = 0; i < cpHospitalAddress.length; i++)
	   {
		   editBadChars(false, cpHospitalAddress[i], badCharGroup2,Constants.sectionPhysicians + Constants.ORDIANAL_NUMBERS[i] + " Covering Physician address" ,Constants.sectionPhysicians);
		   if (!isEmpty(cpHospitalCity[i]))
			   editBadChars(false, cpHospitalCity[i], badCharGroup1,Constants.sectionPhysicians + Constants.ORDIANAL_NUMBERS[i] + " Covering Physician city" ,Constants.sectionPhysicians);
		   if (!isEmpty(cpHospitalState[i]))
			   editBadChars(false, cpHospitalState[i], badCharGroup1,Constants.sectionPhysicians + Constants.ORDIANAL_NUMBERS[i] + " Covering Physician state" ,Constants.sectionPhysicians);
		  /* if (!isEmpty(cpHospitalZip[i]))
			   editBadChars(false, cpHospitalZip[i], badCharGroup1,Constants.sectionPhysicians + Constants.ORDIANAL_NUMBERS[i] + " Covering Physician zip");*/
	   }
	   
	   /*editBadChars(false, medNbrLocations, badCharGroup1,Constants.sectionProviderInfo+ " Anthem Medicaid number of service locations");
		editBadChars(false, hipNbrLocations, badCharGroup1,Constants.sectionProviderInfo+ " Anthem Healthy Indiana Plan number of service locations");
		editBadChars(false, medPldPlacePanelAt, badCharGroup1,Constants.sectionProviderInfo + " Anthem Medicaid place panel at in the panel limit decrease section");
		editBadChars(false, hipPldPlacePanelAt, badCharGroup1,Constants.sectionProviderInfo + " Anthem Healthy Indiana Plan place panel at in the panel limit decrease section");*/

		editBadChars(false,medPldGrpMedicaidNo,badCharGroup1,Constants.sectionProviderInfo+ " the Anthem Medicaid group medicaid number question in the panel limit decrease section", Constants.sectionProviderInfo);
		editBadChars(false,hipPldGrpMedicaidNo,badCharGroup1,Constants.sectionProviderInfo+ " the Anthem Healthy Indiana Plan group medicaid number question in the panel limit decrease section", Constants.sectionProviderInfo);
		
		/*editBadChars(false, medPliPlacePanelAt, badCharGroup1,Constants.sectionProviderInfo + " Anthem Medicaid place panel at question in the panel limit increase section");
		editBadChars(false, hipPliPlacePanelAt, badCharGroup1,Constants.sectionProviderInfo + " Anthem Healthy Indiana Plan place panel at question in the panel limit increase section");*/
		
		editBadChars(false,	medPliGrpMedicaidNbr,badCharGroup1,Constants.sectionProviderInfo+ " the Anthem Medicaid group medicaid number question in the panel limit increase section", Constants.sectionProviderInfo);
		editBadChars(false,	hipPliGrpMedicaidNbr,badCharGroup1,Constants.sectionProviderInfo+ " the Anthem Healthy Indiana Plan group medicaid number question in the panel limit increase section", Constants.sectionProviderInfo);
		editBadChars(false,	medPlrPanelHold,badCharGroup1,Constants.sectionProviderInfo+ " the Anthem Medicaid group medicaid number question in the  panel hold section", Constants.sectionProviderInfo);
		editBadChars(false, hipPlrPanelHold, badCharGroup1,Constants.sectionProviderInfo+ " the Anthem Healthy Indiana Plan group medicaid number question in the  panel hold section", Constants.sectionProviderInfo);
		editBadChars(false,	medPlrPanelHoldRemove,badCharGroup1,Constants.sectionProviderInfo+ " the Anthem Medicaid group medicaid number question in the  panel hold remove section", Constants.sectionProviderInfo);
		editBadChars(false, hipPlrPanelHoldRemove, badCharGroup1,Constants.sectionProviderInfo+ " the Anthem Healthy Indiana Plan group medicaid number question in the  panel hold remove section", Constants.sectionProviderInfo);
		editBadChars(false, deaNo, badCharGroup2, Constants.sectionProviderInfo+ " DEA number", Constants.sectionProviderInfo);
		editBadChars(false, csrNo, badCharGroup2, Constants.sectionProviderInfo+ " CSR number", Constants.sectionProviderInfo);
		editBadChars(false, enrollClinicType, badCharGroup2,Constants.sectionProviderInfo + " clinic type", Constants.sectionProviderInfo);
		editBadChars(false, profLiabilityCarrierName, badCharGroup2,Constants.sectionProviderInfo + " professional liability carrier name", Constants.sectionProviderInfo);
		editBadChars(false, profLiabilityCarrierLimit , badCharGroup2,Constants.sectionProviderInfo + " professional liability coverage limits", Constants.sectionProviderInfo);
		editBadChars(false, profLiabilityPolicyNo, badCharGroup1,Constants.sectionProviderInfo + " professional  liability policy number", Constants.sectionProviderInfo);
		/*editBadChars(false, profLiabilityExpDate, badCharGroup1,Constants.sectionProviderInfo + " professional liability expiration Date");*/
		editBadChars(false, uploadDocComment, badCharGroup3, "The comments for selected file", Constants.sectionAttachment);
		editBadChars(false, comments, badCharGroup3, "Comments", Constants.sectionComments);
		/* PMF Section L Changes -- AD21239 --*/
		editBadChars(false, w2Comments, badCharGroup3, "W-2 Comment", Constants.sectionComments);
		
	    return (errorMessagesVectorA.size() == 0 && errorMessagesVectorB.size() == 0
    		   && errorMessagesVectorC.size() == 0 && errorMessagesVectorD.size() == 0
    		   && errorMessagesVectorE.size() == 0 && errorMessagesVectorF.size() == 0
    		   && errorMessagesVectorG.size() == 0 && errorMessagesVectorH.size() == 0
    		   && errorMessagesVectorI.size() == 0 && errorMessagesVectorJ.size() == 0
    		   && errorMessagesVectorK.size() == 0 && errorMessagesVectorL.size() == 0);
   }
   
    public void editBadChars (boolean bRequired, String strInputValue, int grpNumber, String sFieldDescription ,String sectionName){
        if (strInputValue == null || strInputValue.trim().equals("")){ 
        	if (bRequired){
        		if(sectionName.equalsIgnoreCase(Constants.sectionGeneralInfo)){
        			errorMessagesVectorA.add(sFieldDescription + " required.");
        		}
        		else if(sectionName.equalsIgnoreCase(Constants.sectionReasonSubmitting)){
        			errorMessagesVectorB.add(sFieldDescription + " required.");
        		}
        		else if(sectionName.equalsIgnoreCase(Constants.sectionProviderInfo)){
        			errorMessagesVectorC.add(sFieldDescription + " required.");
        		}
        		else if(sectionName.equalsIgnoreCase(Constants.sectionWisconsin)){
        			errorMessagesVectorD.add(sFieldDescription + " required.");
        		}
        		else if(sectionName.equalsIgnoreCase(Constants.sectionPracticeAddress)){
        			errorMessagesVectorE.add(sFieldDescription + " required.");
        		}
        		else if(sectionName.equalsIgnoreCase(Constants.sectionAddressChange)){
        			errorMessagesVectorF.add(sFieldDescription + " required.");
        		}
        		else if(sectionName.equalsIgnoreCase(Constants.sectionOfficeLocation)){
        			errorMessagesVectorG.add(sFieldDescription + " required.");
        		}
        		else if(sectionName.equalsIgnoreCase(Constants.sectionPhysicians)){
        			errorMessagesVectorH.add(sFieldDescription + " required.");
        		}
        		else if(sectionName.equalsIgnoreCase(Constants.sectionPatientInfo)){
        			errorMessagesVectorI.add(sFieldDescription + " required.");
        		}
        		else if(sectionName.equalsIgnoreCase(Constants.sectionExpertise)){
        			errorMessagesVectorJ.add(sFieldDescription + " required.");
        		}
        		else if(sectionName.equalsIgnoreCase(Constants.sectionAttachment)){
        			errorMessagesVectorK.add(sFieldDescription + " required.");
        		}
        		else if(sectionName.equalsIgnoreCase(Constants.sectionComments)){
        			errorMessagesVectorL.add(sFieldDescription + " required.");
        		}
        	}

        }
        else{  
        	if (PMFUtils.containsBadCharacter(strInputValue, grpNumber)){
        		if(sectionName.equalsIgnoreCase(Constants.sectionGeneralInfo)){
        			errorMessagesVectorA.add(sFieldDescription + " contains invalid characters.");
        		}if(sectionName.equalsIgnoreCase(Constants.sectionReasonSubmitting)){
        			errorMessagesVectorB.add(sFieldDescription + " contains invalid characters.");
        		}if(sectionName.equalsIgnoreCase(Constants.sectionProviderInfo)){
        			errorMessagesVectorC.add(sFieldDescription + " contains invalid characters.");
        		}if(sectionName.equalsIgnoreCase(Constants.sectionWisconsin)){
        			errorMessagesVectorD.add(sFieldDescription + " contains invalid characters.");
        		}if(sectionName.equalsIgnoreCase(Constants.sectionPracticeAddress)){
        			errorMessagesVectorE.add(sFieldDescription + " contains invalid characters.");
        		}if(sectionName.equalsIgnoreCase(Constants.sectionAddressChange)){
        			errorMessagesVectorF.add(sFieldDescription + " contains invalid characters.");
        		}if(sectionName.equalsIgnoreCase(Constants.sectionOfficeLocation)){
        			errorMessagesVectorG.add(sFieldDescription + " contains invalid characters.");
        		}if(sectionName.equalsIgnoreCase(Constants.sectionPhysicians)){
        			errorMessagesVectorH.add(sFieldDescription + " contains invalid characters.");
        		}if(sectionName.equalsIgnoreCase(Constants.sectionPatientInfo)){
        			errorMessagesVectorI.add(sFieldDescription + " contains invalid characters.");
        		}if(sectionName.equalsIgnoreCase(Constants.sectionExpertise)){
        			errorMessagesVectorJ.add(sFieldDescription + " contains invalid characters.");
        		}if(sectionName.equalsIgnoreCase(Constants.sectionAttachment)){
        			errorMessagesVectorK.add(Constants.sectionAttachment+" "+ sFieldDescription + " contains invalid characters.");
        		}if(sectionName.equalsIgnoreCase(Constants.sectionComments)){
        			errorMessagesVectorL.add(sFieldDescription + " contains invalid characters.");
        		}
        	}
        }
     }
    //Changes for the security scan on 03/05/10 end
    
    public boolean editBadChars (String strInputValue, int grpNumber, String sFieldsDescription ,String sectionName) {
    	boolean allOk = true;
    	if (strInputValue == null || strInputValue.trim().equals("")) { 
    		//Do Nothing
        }
        else{  
        	if (PMFUtils.containsBadCharacter(strInputValue, grpNumber)) {
        		System.out.println("Zip contain bad character");
        		if(sectionName.equalsIgnoreCase(Constants.sectionGeneralInfo)){
        			errorMessagesVectorA.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(sectionName.equalsIgnoreCase(Constants.sectionReasonSubmitting)){
        			errorMessagesVectorB.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(sectionName.equalsIgnoreCase(Constants.sectionProviderInfo)){
        			errorMessagesVectorC.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(sectionName.equalsIgnoreCase(Constants.sectionWisconsin)){
        			errorMessagesVectorD.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(sectionName.equalsIgnoreCase(Constants.sectionPracticeAddress)){
        			errorMessagesVectorE.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(sectionName.equalsIgnoreCase(Constants.sectionAddressChange)){
        			errorMessagesVectorF.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(sectionName.equalsIgnoreCase(Constants.sectionOfficeLocation)){
        			errorMessagesVectorG.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(sectionName.equalsIgnoreCase(Constants.sectionPhysicians)){
        			errorMessagesVectorH.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(sectionName.equalsIgnoreCase(Constants.sectionPatientInfo)){
        			errorMessagesVectorI.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(sectionName.equalsIgnoreCase(Constants.sectionExpertise)){
        			errorMessagesVectorJ.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(sectionName.equalsIgnoreCase(Constants.sectionComments)){
        			errorMessagesVectorK.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(sectionName.equalsIgnoreCase(Constants.sectionAttachment)){
        			errorMessagesVectorL.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}
        		allOk = false;
        	}
        }
        return allOk;
     }
	
    public boolean editBadChars (String strInputValue, int grpNumber, String sFieldsDescription ,String sectionName, String section) {
    	boolean allOk = true;
    	if (strInputValue == null || strInputValue.trim().equals("")) { 
    		//Do Nothing
        }
        else{  
        	if (PMFUtils.containsBadCharacter(strInputValue, grpNumber)) {
        		System.out.println("Zip contain bad character");
        		if(section.equalsIgnoreCase(Constants.sectionGeneralInfo)){
        			errorMessagesVectorA.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(section.equalsIgnoreCase(Constants.sectionReasonSubmitting)){
        			errorMessagesVectorB.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(section.equalsIgnoreCase(Constants.sectionProviderInfo)){
        			errorMessagesVectorC.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(section.equalsIgnoreCase(Constants.sectionWisconsin)){
        			errorMessagesVectorD.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(section.equalsIgnoreCase(Constants.sectionPracticeAddress)){
        			errorMessagesVectorE.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(section.equalsIgnoreCase(Constants.sectionAddressChange)){
        			errorMessagesVectorF.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(section.equalsIgnoreCase(Constants.sectionOfficeLocation)){
        			errorMessagesVectorG.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(section.equalsIgnoreCase(Constants.sectionPhysicians)){
        			errorMessagesVectorH.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(section.equalsIgnoreCase(Constants.sectionPatientInfo)){
        			errorMessagesVectorI.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(section.equalsIgnoreCase(Constants.sectionExpertise)){
        			errorMessagesVectorJ.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(section.equalsIgnoreCase(Constants.sectionComments)){
        			errorMessagesVectorK.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(section.equalsIgnoreCase(Constants.sectionAttachment)){
        			errorMessagesVectorL.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}
        		allOk = false;
        	}
        }
        return allOk;
     }
    public boolean editBadChars (String strInputValue, int grpNumber, String sFieldsDescription ,String sectionName, String section,String sTempZip) {
    	boolean allOk = true;
    	if (strInputValue == null || strInputValue.trim().equals("")) { 
    		//Do Nothing
        }
        else{  
        	if (PMFUtils.containsBadCharacter(strInputValue, grpNumber)) {
        		System.out.println("Zip contain bad character");
        		if(section.equalsIgnoreCase(Constants.sectionGeneralInfo)){
        			errorMessagesVectorA.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(section.equalsIgnoreCase(Constants.sectionReasonSubmitting)){
        			errorMessagesVectorB.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(section.equalsIgnoreCase(Constants.sectionProviderInfo)){
        			errorMessagesVectorC.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(section.equalsIgnoreCase(Constants.sectionWisconsin)){
        			errorMessagesVectorD.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(section.equalsIgnoreCase(Constants.sectionPracticeAddress)){
        			errorMessagesVectorE.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(section.equalsIgnoreCase(Constants.sectionAddressChange)){
        			errorMessagesVectorF.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(section.equalsIgnoreCase(Constants.sectionOfficeLocation)){
        			errorMessagesVectorG.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(section.equalsIgnoreCase(Constants.sectionPhysicians)){
        			errorMessagesVectorH.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(section.equalsIgnoreCase(Constants.sectionPatientInfo)){
        			errorMessagesVectorI.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(section.equalsIgnoreCase(Constants.sectionExpertise)){
        			errorMessagesVectorJ.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(section.equalsIgnoreCase(Constants.sectionComments)){
        			errorMessagesVectorK.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(section.equalsIgnoreCase(Constants.sectionAttachment)){
        			errorMessagesVectorL.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}
        		allOk = false;
        	}
        	else  if (sTempZip.substring(5,9).equals("0000")|| sTempZip.substring(5,9).equals("9999")){
        		System.out.println("Zip contain bad character");
        		if(section.equalsIgnoreCase(Constants.sectionGeneralInfo)){
        			errorMessagesVectorA.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(section.equalsIgnoreCase(Constants.sectionReasonSubmitting)){
        			errorMessagesVectorB.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(section.equalsIgnoreCase(Constants.sectionProviderInfo)){
        			errorMessagesVectorC.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(section.equalsIgnoreCase(Constants.sectionWisconsin)){
        			errorMessagesVectorD.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(section.equalsIgnoreCase(Constants.sectionPracticeAddress)){
        			errorMessagesVectorE.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(section.equalsIgnoreCase(Constants.sectionAddressChange)){
        			errorMessagesVectorF.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(section.equalsIgnoreCase(Constants.sectionOfficeLocation)){
        			errorMessagesVectorG.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(section.equalsIgnoreCase(Constants.sectionPhysicians)){
        			errorMessagesVectorH.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(section.equalsIgnoreCase(Constants.sectionPatientInfo)){
        			errorMessagesVectorI.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(section.equalsIgnoreCase(Constants.sectionExpertise)){
        			errorMessagesVectorJ.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(section.equalsIgnoreCase(Constants.sectionComments)){
        			errorMessagesVectorK.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}if(section.equalsIgnoreCase(Constants.sectionAttachment)){
        			errorMessagesVectorL.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
        		}
        		allOk = false;
        	}
        }
        return allOk;
     }
    
	private boolean editOfficeHours(boolean isRequired, String section, int index) {
		
		boolean flagAllDay = false, flagSetDay = false;
		boolean flagMon = false, flagTue = false, flagWed = false, flagThu = false, flagFri = false, flagSat = false, flagSun = false;
		
		boolean allOk = true;
		String sectionIndex = "";
		int grpNumber1 = 1;
		
		if (index >=2) 
		{
			sectionIndex = section + " --" + Constants.ORDIANAL_NUMBERS[index-1] + " Practice";
		}
		sectionIndex = sectionIndex.equals("")?section:sectionIndex;
		String daysOpenMon = "";
	    String daysOpenTue = "";
	    String daysOpenWed = "";
	    String daysOpenThu = "";
	    String daysOpenFri = "";
	    String daysOpenSat = "";
	    String daysOpenSun = "";
	    
	    //Populate the day check box values
	    if (index == 0) {
	    	daysOpenMon = daysOpenMon0;
	    	daysOpenTue = daysOpenTue0;
	    	daysOpenWed = daysOpenWed0;
	    	daysOpenThu = daysOpenThu0;
	    	daysOpenFri = daysOpenFri0;
	    	daysOpenSat = daysOpenSat0;
	    	daysOpenSun = daysOpenSun0;
	    } else if (index == 1) {
	    	daysOpenMon = daysOpenMon1;
	    	daysOpenTue = daysOpenTue1;
	    	daysOpenWed = daysOpenWed1;
	    	daysOpenThu = daysOpenThu1;
	    	daysOpenFri = daysOpenFri1;
	    	daysOpenSat = daysOpenSat1;
	    	daysOpenSun = daysOpenSun1;
	    } else if (index == 2) {
	    	daysOpenMon = daysOpenMon2;
	    	daysOpenTue = daysOpenTue2;
	    	daysOpenWed = daysOpenWed2;
	    	daysOpenThu = daysOpenThu2;
	    	daysOpenFri = daysOpenFri2;
	    	daysOpenSat = daysOpenSat2;
	    	daysOpenSun = daysOpenSun2;
	    } else if (index == 3) {
	    	daysOpenMon = daysOpenMon3;
	    	daysOpenTue = daysOpenTue3;
	    	daysOpenWed = daysOpenWed3;
	    	daysOpenThu = daysOpenThu3;
	    	daysOpenFri = daysOpenFri3;
	    	daysOpenSat = daysOpenSat3;
	    	daysOpenSun = daysOpenSun3;
	    } else if (index == 4) {
	    	daysOpenMon = daysOpenMon4;
	    	daysOpenTue = daysOpenTue4;
	    	daysOpenWed = daysOpenWed4;
	    	daysOpenThu = daysOpenThu4;
	    	daysOpenFri = daysOpenFri4;
	    	daysOpenSat = daysOpenSat4;
	    	daysOpenSun = daysOpenSun4;
	    } else if (index == 5) {
	    	daysOpenMon = daysOpenMon5;
	    	daysOpenTue = daysOpenTue5;
	    	daysOpenWed = daysOpenWed5;
	    	daysOpenThu = daysOpenThu5;
	    	daysOpenFri = daysOpenFri5;
	    	daysOpenSat = daysOpenSat5;
	    	daysOpenSun = daysOpenSun5;
	    }
	    
	    allOk = editBadChars(timeZoneOfficeHrs[index], grpNumber1, section + " Time Zone of Office Hours",section);
	    
	    allOk = editBadChars(daysOpenMon, grpNumber1, section + " Mon Office Open",section);
	    allOk = editBadChars(daysOpenTue, grpNumber1, section + " Tue Office Open",section);
	    allOk = editBadChars(daysOpenWed, grpNumber1, section + " Wed Office Open",section);
	    allOk = editBadChars(daysOpenThu, grpNumber1, section + " Thu Office Open",section);
	    allOk = editBadChars(daysOpenFri, grpNumber1, section + " Fri Office Open",section);
	    allOk = editBadChars(daysOpenSat, grpNumber1, section + " Sat Office Open",section);
	    allOk = editBadChars(daysOpenSun, grpNumber1, section + " Sun Office Open",section);
	    
	    allOk = editBadChars(daysOpenTimeMon[index], grpNumber1, section + " Mon Office Open Time",section);
	    allOk = editBadChars(daysOpenTimeTue[index], grpNumber1, section + " Tue Office Open Time",section);
	    allOk = editBadChars(daysOpenTimeWed[index], grpNumber1, section + " Wed Office Open Time",section);
	    allOk = editBadChars(daysOpenTimeThu[index], grpNumber1, section + " Thu Office Open Time",section);
	    allOk = editBadChars(daysOpenTimeFri[index], grpNumber1, section + " Fri Office Open Time",section);
	    allOk = editBadChars(daysOpenTimeSat[index], grpNumber1, section + " Sat Office Open Time",section);
	    allOk = editBadChars(daysOpenTimeSun[index], grpNumber1, section + " Sun Office Open Time",section);
	    
	    allOk = editBadChars(daysCloseTimeMon[index], grpNumber1, section + " Mon Office Close Time",section);
	    allOk = editBadChars(daysCloseTimeTue[index], grpNumber1, section + " Tue Office Close Time",section);
	    allOk = editBadChars(daysCloseTimeWed[index], grpNumber1, section + " Wed Office Close Time",section);
	    allOk = editBadChars(daysCloseTimeThu[index], grpNumber1, section + " Thu Office Close Time",section);
	    allOk = editBadChars(daysCloseTimeFri[index], grpNumber1, section + " Fri Office Close Time",section);
	    allOk = editBadChars(daysCloseTimeSat[index], grpNumber1, section + " Sat Office Close Time",section);
	    allOk = editBadChars(daysCloseTimeSun[index], grpNumber1, section + " Sun Office Close Time",section);
	    
	    List officeTimeList = Arrays.asList(Constants.OFFICE_TIMINGS_VALUE);
		
		int tempOpenMonIndex = officeTimeList.indexOf(daysOpenTimeMon[index]);
		int tempCloseMonIndex = officeTimeList.indexOf(daysCloseTimeMon[index]);
		int tempOpenTueIndex = officeTimeList.indexOf(daysOpenTimeTue[index]);
		int tempCloseTueIndex = officeTimeList.indexOf(daysCloseTimeTue[index]);
		int tempOpenWedIndex = officeTimeList.indexOf(daysOpenTimeWed[index]);
		int tempCloseWedIndex = officeTimeList.indexOf(daysCloseTimeWed[index]);
		int tempOpenThuIndex = officeTimeList.indexOf(daysOpenTimeThu[index]);
		int tempCloseThuIndex = officeTimeList.indexOf(daysCloseTimeThu[index]);
		int tempOpenFriIndex = officeTimeList.indexOf(daysOpenTimeFri[index]);
		int tempCloseFriIndex = officeTimeList.indexOf(daysCloseTimeFri[index]);
		int tempOpenSatIndex = officeTimeList.indexOf(daysOpenTimeSat[index]);
		int tempCloseSatIndex = officeTimeList.indexOf(daysCloseTimeSat[index]);
		int tempOpenSunIndex = officeTimeList.indexOf(daysOpenTimeSun[index]);
		int tempCloseSunIndex = officeTimeList.indexOf(daysCloseTimeSun[index]);
		
		if(tempOpenMonIndex > 0 && tempCloseMonIndex > 0) {
			
			if(tempCloseMonIndex <= tempOpenMonIndex) {
				
				insertValidMessage(section," Closed time for Mon is invalid.",sectionIndex);
				allOk = false;
				
			}
		}
		if(tempOpenTueIndex > 0 && tempCloseTueIndex > 0) {
			
			if(tempCloseTueIndex <= tempOpenTueIndex) {
				
				insertValidMessage(section," Closed time for Tue is invalid.",sectionIndex);
				
				allOk = false;
			}
		}
		if(tempOpenWedIndex > 0 && tempCloseWedIndex > 0) {
			
			if(tempCloseWedIndex <= tempOpenWedIndex) {
				
				insertValidMessage(section," Closed time for Wed is invalid.",sectionIndex);
				allOk = false;
			}
		}
		if(tempOpenThuIndex > 0 && tempCloseThuIndex > 0) {
			
			if(tempCloseThuIndex <= tempOpenThuIndex) {
				
				insertValidMessage(section," Closed time for Thu is invalid.",sectionIndex);
				allOk = false;
			}
		}
		if(tempOpenFriIndex > 0 && tempCloseFriIndex > 0) {
			
			if(tempCloseFriIndex <= tempOpenFriIndex) {
				
				insertValidMessage(section," Closed time for Fri is invalid.",sectionIndex);
				allOk = false;
			}
		}
		if(tempOpenSatIndex > 0 && tempCloseSatIndex > 0) {
			
			if(tempCloseSatIndex <= tempOpenSatIndex) {
				
				insertValidMessage(section," Closed time for Sat is invalid.",sectionIndex);
				allOk = false;
			}
		}
		if(tempOpenSunIndex > 0 && tempCloseSunIndex > 0) {
			
			if(tempCloseSunIndex <= tempOpenSunIndex) {
				
				insertValidMessage(section," Closed time for Sun is invalid.",sectionIndex);
				allOk = false;
			}
		}
	    
		if (isRequired) {
			
			if((StringUtils.isNotEmpty(daysOpenTimeMon[index]) && StringUtils.isEmpty(daysCloseTimeMon[index])) ||
					(StringUtils.isEmpty(daysOpenTimeMon[index]) && StringUtils.isNotEmpty(daysCloseTimeMon[index]))) {
				
				flagMon = true;		
				insertValidMessage(section," office hours for Mon must have both open and closed hours.",sectionIndex);
				allOk = false;
			}
			if((StringUtils.isNotEmpty(daysOpenTimeTue[index]) && StringUtils.isEmpty(daysCloseTimeTue[index])) ||
					(StringUtils.isEmpty(daysOpenTimeTue[index]) && StringUtils.isNotEmpty(daysCloseTimeTue[index]))) {
				
				flagTue = true;
				insertValidMessage(section," office hours for Tue must have both open and closed hours.",sectionIndex);
				allOk = false;
			}
			if((StringUtils.isNotEmpty(daysOpenTimeWed[index]) && StringUtils.isEmpty(daysCloseTimeWed[index])) ||
					(StringUtils.isEmpty(daysOpenTimeWed[index]) && StringUtils.isNotEmpty(daysCloseTimeWed[index]))) {
				
				flagWed = true;
				insertValidMessage(section," office hours for Wed must have both open and closed hours.",sectionIndex);
				allOk = false;
			}
			if((StringUtils.isNotEmpty(daysOpenTimeThu[index]) && StringUtils.isEmpty(daysCloseTimeThu[index])) ||
					(StringUtils.isEmpty(daysOpenTimeThu[index]) && StringUtils.isNotEmpty(daysCloseTimeThu[index]))) {
				
				flagThu = true;
				insertValidMessage(section," office hours for Thu must have both open and closed hours.",sectionIndex);
				allOk = false;
			}
			if((StringUtils.isNotEmpty(daysOpenTimeFri[index]) && StringUtils.isEmpty(daysCloseTimeFri[index])) ||
					(StringUtils.isEmpty(daysOpenTimeFri[index]) && StringUtils.isNotEmpty(daysCloseTimeFri[index]))) {
				
				flagFri = true;
				insertValidMessage(section," office hours for Fri must have both open and closed hours.",sectionIndex);
				allOk = false;
			}
			if((StringUtils.isNotEmpty(daysOpenTimeSat[index]) && StringUtils.isEmpty(daysCloseTimeSat[index])) ||
					(StringUtils.isEmpty(daysOpenTimeSat[index]) && StringUtils.isNotEmpty(daysCloseTimeSat[index]))) {
				
				flagSat = true;
				errorMessagesVectorE.add(section + " office hours for Sat must have both open and closed hours.");
				allOk = false;
			}
			if((StringUtils.isNotEmpty(daysOpenTimeSun[index]) && StringUtils.isEmpty(daysCloseTimeSun[index])) ||
					(StringUtils.isEmpty(daysOpenTimeSun[index]) && StringUtils.isNotEmpty(daysCloseTimeSun[index]))) {
				
				flagSun = true;
				insertValidMessage(section," office hours for Sat must have both open and closed hours.",sectionIndex);
				allOk = false;
			}
			
			if (!StringUtils.isEmpty(daysOpenMon) && !flagMon) {
				if(StringUtils.isEmpty(daysOpenTimeMon[index]) || StringUtils.isEmpty(daysCloseTimeMon[index])) {
					insertValidMessage(section," office hours for Mon must have both open and closed hours.",sectionIndex);
					allOk = false;
				}
			}
			
			if (!StringUtils.isEmpty(daysOpenTue) && !flagTue) {
				if(StringUtils.isEmpty(daysOpenTimeTue[index]) || StringUtils.isEmpty(daysCloseTimeTue[index])) {
					insertValidMessage(section," office hours for Tue must have both open and closed hours.",sectionIndex);
					allOk = false;
				}
			}
			
			if (!StringUtils.isEmpty(daysOpenWed) && !flagWed) {
				if(StringUtils.isEmpty(daysOpenTimeWed[index]) || StringUtils.isEmpty(daysCloseTimeWed[index])) {
					insertValidMessage(section," office hours for Wed must have both open and closed hours.",sectionIndex);
					allOk = false;
				}
			}
			if (!StringUtils.isEmpty(daysOpenThu) && !flagThu) {
				if(StringUtils.isEmpty(daysOpenTimeThu[index]) || StringUtils.isEmpty(daysCloseTimeThu[index])) {
					insertValidMessage(section," office hours for Thu must have both open and closed hours.",sectionIndex);
					allOk = false;
				}
			}
			if (!StringUtils.isEmpty(daysOpenFri) && !flagFri) {
				if(StringUtils.isEmpty(daysOpenTimeFri[index]) || StringUtils.isEmpty(daysCloseTimeFri[index])) {
					insertValidMessage(section," office hours for Fri must have both open and closed hours.",sectionIndex);
					allOk = false;
				}
			}
			if (!StringUtils.isEmpty(daysOpenSat) && !flagSat) {
				if(StringUtils.isEmpty(daysOpenTimeSat[index]) || StringUtils.isEmpty(daysCloseTimeSat[index])) {
					insertValidMessage(section," office hours for Sat must have both open and closed hours.",sectionIndex);
					allOk = false;
				}
			}
			if (!StringUtils.isEmpty(daysOpenSun) && !flagSun) {
				if(StringUtils.isEmpty(daysOpenTimeSun[index]) || StringUtils.isEmpty(daysCloseTimeSun[index])) {
					insertValidMessage(section," office hours for Sun must have both open and closed hours.",sectionIndex);
					allOk = false;
				}
			}
			
			if (!((StringUtils.isNotEmpty(daysOpenTimeMon[index]) && StringUtils.isNotEmpty(daysCloseTimeMon[index])) ||
					(StringUtils.isNotEmpty(daysOpenTimeTue[index]) && StringUtils.isNotEmpty(daysCloseTimeTue[index])) ||
					(StringUtils.isNotEmpty(daysOpenTimeWed[index]) && StringUtils.isNotEmpty(daysCloseTimeWed[index])) ||
					(StringUtils.isNotEmpty(daysOpenTimeThu[index]) && StringUtils.isNotEmpty(daysCloseTimeThu[index])) ||
					(StringUtils.isNotEmpty(daysOpenTimeFri[index]) && StringUtils.isNotEmpty(daysCloseTimeFri[index])) ||
					(StringUtils.isNotEmpty(daysOpenTimeSat[index]) && StringUtils.isNotEmpty(daysCloseTimeSat[index])) ||
					(StringUtils.isNotEmpty(daysOpenTimeSun[index]) && StringUtils.isNotEmpty(daysCloseTimeSun[index])))) {
				String optionsSelected = (StringUtils.isNotEmpty(hipIndicator) ? "Anthem Healthy Indiana Plan": "")
											+ (StringUtils.isNotEmpty(medicaidIndicator) && StringUtils.isNotEmpty(hipIndicator) ? " And " : "")
											+ (StringUtils.isNotEmpty(medicaidIndicator) ? "Anthem Medicaid":"");
				
				errorMessagesVectorE.add("You must answer the office hours question in " + section +
						" since you selected " + optionsSelected + " in " + Constants.sectionProviderInfo + ".");
				allOk = false;
			}
			
			if(StringUtils.isEmpty(daysOpenMon) && 
					StringUtils.isNotEmpty(daysOpenTimeMon[index]) && StringUtils.isNotEmpty(daysCloseTimeMon[index])) {
				daysOpenMon = "Y";
				flagSetDay = true;
			}

			if(StringUtils.isEmpty(daysOpenTue) &&
					StringUtils.isNotEmpty(daysOpenTimeTue[index]) && StringUtils.isNotEmpty(daysCloseTimeTue[index])) {
				daysOpenTue = "Y";
				flagSetDay = true;
			}

			if(StringUtils.isEmpty(daysOpenWed) &&
					StringUtils.isNotEmpty(daysOpenTimeWed[index]) && StringUtils.isNotEmpty(daysCloseTimeWed[index])) {
				 daysOpenWed = "Y";
				 flagSetDay = true;
			}

			if(StringUtils.isEmpty(daysOpenThu) &&
					StringUtils.isNotEmpty(daysOpenTimeThu[index]) && StringUtils.isNotEmpty(daysCloseTimeThu[index])) {
				daysOpenThu = "Y";
				flagSetDay = true;
			}

			if(StringUtils.isEmpty(daysOpenFri) && 
					StringUtils.isNotEmpty(daysOpenTimeFri[index]) && StringUtils.isNotEmpty(daysCloseTimeFri[index])) {
				daysOpenFri = "Y";
				flagSetDay = true;
			}

			if(StringUtils.isEmpty(daysOpenSat) &&
					StringUtils.isNotEmpty(daysOpenTimeSat[index]) && StringUtils.isNotEmpty(daysCloseTimeSat[index])) {
				daysOpenSat = "Y";
				flagSetDay = true;
			}

			if(StringUtils.isEmpty(daysOpenSun) &&
					StringUtils.isNotEmpty(daysOpenTimeSun[index]) && StringUtils.isNotEmpty(daysCloseTimeSun[index])) {
				daysOpenSun = "Y";
				flagSetDay = true;
			}
			//Set the day if Open and Close Time are selected
			if (index == 0 && flagSetDay) {
				daysOpenMon0 = daysOpenMon;
				daysOpenTue0 = daysOpenTue;
				daysOpenWed0 = daysOpenWed;
				daysOpenThu0 = daysOpenThu;
				daysOpenFri0 = daysOpenFri;
				daysOpenSat0 = daysOpenSat;
				daysOpenSun0 = daysOpenSun;
			} else if (index == 1 && flagSetDay) {
				daysOpenMon1 = daysOpenMon;
				daysOpenTue1 = daysOpenTue;
				daysOpenWed1 = daysOpenWed;
				daysOpenThu1 = daysOpenThu;
				daysOpenFri1 = daysOpenFri;
				daysOpenSat1 = daysOpenSat;
				daysOpenSun1 = daysOpenSun;
			} else if (index == 2 && flagSetDay) {
				daysOpenMon2 = daysOpenMon;
				daysOpenTue2 = daysOpenTue;
				daysOpenWed2 = daysOpenWed;
				daysOpenThu2 = daysOpenThu;
				daysOpenFri2 = daysOpenFri;
				daysOpenSat2 = daysOpenSat;
				daysOpenSun2 = daysOpenSun;
			} else if (index == 3 && flagSetDay) {
				daysOpenMon3 = daysOpenMon;
				daysOpenTue3 = daysOpenTue;
				daysOpenWed3 = daysOpenWed;
				daysOpenThu3 = daysOpenThu;
				daysOpenFri3 = daysOpenFri;
				daysOpenSat3 = daysOpenSat;
				daysOpenSun3 = daysOpenSun;
			} else if (index == 4 && flagSetDay) {
				daysOpenMon4 = daysOpenMon;
				daysOpenTue4 = daysOpenTue;
				daysOpenWed4 = daysOpenWed;
				daysOpenThu4 = daysOpenThu;
				daysOpenFri4 = daysOpenFri;
				daysOpenSat4 = daysOpenSat;
				daysOpenSun4 = daysOpenSun;
			} else if (index == 5 && flagSetDay) {
				daysOpenMon5 = daysOpenMon;
				daysOpenTue5 = daysOpenTue;
				daysOpenWed5 = daysOpenWed;
				daysOpenThu5 = daysOpenThu;
				daysOpenFri5 = daysOpenFri;
				daysOpenSat5 = daysOpenSat;
				daysOpenSun5 = daysOpenSun;
			}

			if (!((StringUtils.isNotEmpty(daysOpenMon) && StringUtils.isNotEmpty(daysOpenTimeMon[index]) && StringUtils.isNotEmpty(daysCloseTimeMon[index])) ||
					(StringUtils.isNotEmpty(daysOpenTue) && StringUtils.isNotEmpty(daysOpenTimeTue[index]) && StringUtils.isNotEmpty(daysCloseTimeTue[index])) ||
					(StringUtils.isNotEmpty(daysOpenWed) && StringUtils.isNotEmpty(daysOpenTimeWed[index]) && StringUtils.isNotEmpty(daysCloseTimeWed[index])) ||
					(StringUtils.isNotEmpty(daysOpenThu) && StringUtils.isNotEmpty(daysOpenTimeThu[index]) && StringUtils.isNotEmpty(daysCloseTimeThu[index])) ||
					(StringUtils.isNotEmpty(daysOpenFri) && StringUtils.isNotEmpty(daysOpenTimeFri[index]) && StringUtils.isNotEmpty(daysCloseTimeFri[index])) ||
					(StringUtils.isNotEmpty(daysOpenSat) && StringUtils.isNotEmpty(daysOpenTimeSat[index]) && StringUtils.isNotEmpty(daysCloseTimeSat[index])) ||
					(StringUtils.isNotEmpty(daysOpenSun) && StringUtils.isNotEmpty(daysOpenTimeSun[index]) && StringUtils.isNotEmpty(daysCloseTimeSun[index])))) {
				flagAllDay = true;
			}
			
			if (!flagAllDay && StringUtils.isEmpty(timeZoneOfficeHrs[index])) {
				
				insertValidMessage(section," time zone of office hours is missing.",sectionIndex);
				allOk = false;
			}
		} else {
			
			if(StringUtils.isEmpty(daysOpenMon) && 
					StringUtils.isNotEmpty(daysOpenTimeMon[index]) && StringUtils.isNotEmpty(daysCloseTimeMon[index])) {
				daysOpenMon = "Y";
				flagSetDay = true;
			}

			if(StringUtils.isEmpty(daysOpenTue) &&
					StringUtils.isNotEmpty(daysOpenTimeTue[index]) && StringUtils.isNotEmpty(daysCloseTimeTue[index])) {
				daysOpenTue = "Y";
				flagSetDay = true;
			}

			if(StringUtils.isEmpty(daysOpenWed) &&
					StringUtils.isNotEmpty(daysOpenTimeWed[index]) && StringUtils.isNotEmpty(daysCloseTimeWed[index])) {
				 daysOpenWed = "Y";
				 flagSetDay = true;
			}

			if(StringUtils.isEmpty(daysOpenThu) &&
					StringUtils.isNotEmpty(daysOpenTimeThu[index]) && StringUtils.isNotEmpty(daysCloseTimeThu[index])) {
				daysOpenThu = "Y";
				flagSetDay = true;
			}

			if(StringUtils.isEmpty(daysOpenFri) && 
					StringUtils.isNotEmpty(daysOpenTimeFri[index]) && StringUtils.isNotEmpty(daysCloseTimeFri[index])) {
				daysOpenFri = "Y";
				flagSetDay = true;
			}

			if(StringUtils.isEmpty(daysOpenSat) &&
					StringUtils.isNotEmpty(daysOpenTimeSat[index]) && StringUtils.isNotEmpty(daysCloseTimeSat[index])) {
				daysOpenSat = "Y";
				flagSetDay = true;
			}

			if(StringUtils.isEmpty(daysOpenSun) &&
					StringUtils.isNotEmpty(daysOpenTimeSun[index]) && StringUtils.isNotEmpty(daysCloseTimeSun[index])) {
				daysOpenSun = "Y";
				flagSetDay = true;
			}
			//Set the day if Open and Close Time are selected
			if (index == 0 && flagSetDay) {
				daysOpenMon0 = daysOpenMon;
				daysOpenTue0 = daysOpenTue;
				daysOpenWed0 = daysOpenWed;
				daysOpenThu0 = daysOpenThu;
				daysOpenFri0 = daysOpenFri;
				daysOpenSat0 = daysOpenSat;
				daysOpenSun0 = daysOpenSun;
			} else if (index == 1 && flagSetDay) {
				daysOpenMon1 = daysOpenMon;
				daysOpenTue1 = daysOpenTue;
				daysOpenWed1 = daysOpenWed;
				daysOpenThu1 = daysOpenThu;
				daysOpenFri1 = daysOpenFri;
				daysOpenSat1 = daysOpenSat;
				daysOpenSun1 = daysOpenSun;
			} else if (index == 2 && flagSetDay) {
				daysOpenMon2 = daysOpenMon;
				daysOpenTue2 = daysOpenTue;
				daysOpenWed2 = daysOpenWed;
				daysOpenThu2 = daysOpenThu;
				daysOpenFri2 = daysOpenFri;
				daysOpenSat2 = daysOpenSat;
				daysOpenSun2 = daysOpenSun;
			} else if (index == 3 && flagSetDay) {
				daysOpenMon3 = daysOpenMon;
				daysOpenTue3 = daysOpenTue;
				daysOpenWed3 = daysOpenWed;
				daysOpenThu3 = daysOpenThu;
				daysOpenFri3 = daysOpenFri;
				daysOpenSat3 = daysOpenSat;
				daysOpenSun3 = daysOpenSun;
			} else if (index == 4 && flagSetDay) {
				daysOpenMon4 = daysOpenMon;
				daysOpenTue4 = daysOpenTue;
				daysOpenWed4 = daysOpenWed;
				daysOpenThu4 = daysOpenThu;
				daysOpenFri4 = daysOpenFri;
				daysOpenSat4 = daysOpenSat;
				daysOpenSun4 = daysOpenSun;
			} else if (index == 5 && flagSetDay) {
				daysOpenMon5 = daysOpenMon;
				daysOpenTue5 = daysOpenTue;
				daysOpenWed5 = daysOpenWed;
				daysOpenThu5 = daysOpenThu;
				daysOpenFri5 = daysOpenFri;
				daysOpenSat5 = daysOpenSat;
				daysOpenSun5 = daysOpenSun;
			}
			
			if((StringUtils.isNotEmpty(daysOpenTimeMon[index]) && StringUtils.isEmpty(daysCloseTimeMon[index])) ||
					(StringUtils.isEmpty(daysOpenTimeMon[index]) && StringUtils.isNotEmpty(daysCloseTimeMon[index]))) {
				
				flagMon = true;	
				insertValidMessage(section," office hours for Mon must have both open and closed hours.",sectionIndex);
				allOk = false;
			}
			if((StringUtils.isNotEmpty(daysOpenTimeTue[index]) && StringUtils.isEmpty(daysCloseTimeTue[index])) ||
					(StringUtils.isEmpty(daysOpenTimeTue[index]) && StringUtils.isNotEmpty(daysCloseTimeTue[index]))) {
				
				flagTue = true;
				insertValidMessage(section," office hours for Tue must have both open and closed hours.",sectionIndex);
				allOk = false;
			}
			if((StringUtils.isNotEmpty(daysOpenTimeWed[index]) && StringUtils.isEmpty(daysCloseTimeWed[index])) ||
					(StringUtils.isEmpty(daysOpenTimeWed[index]) && StringUtils.isNotEmpty(daysCloseTimeWed[index]))) {
				
				flagWed = true;
				insertValidMessage(section," office hours for Wed must have both open and closed hours.",sectionIndex);
				allOk = false;
			}
			if((StringUtils.isNotEmpty(daysOpenTimeThu[index]) && StringUtils.isEmpty(daysCloseTimeThu[index])) ||
					(StringUtils.isEmpty(daysOpenTimeThu[index]) && StringUtils.isNotEmpty(daysCloseTimeThu[index]))) {
				
				flagThu = true;
				insertValidMessage(section," office hours for Thu must have both open and closed hours.",sectionIndex);
				allOk = false;
			}
			if((StringUtils.isNotEmpty(daysOpenTimeFri[index]) && StringUtils.isEmpty(daysCloseTimeFri[index])) ||
					(StringUtils.isEmpty(daysOpenTimeFri[index]) && StringUtils.isNotEmpty(daysCloseTimeFri[index]))) {
				
				flagFri = true;
				insertValidMessage(section," office hours for Fri must have both open and closed hours.",sectionIndex);
				allOk = false;
			}
			if((StringUtils.isNotEmpty(daysOpenTimeSat[index]) && StringUtils.isEmpty(daysCloseTimeSat[index])) ||
					(StringUtils.isEmpty(daysOpenTimeSat[index]) && StringUtils.isNotEmpty(daysCloseTimeSat[index]))) {
				
				flagSat = true;
				insertValidMessage(section," office hours for Sat must have both open and closed hours.",sectionIndex);
				allOk = false;
			}
			if((StringUtils.isNotEmpty(daysOpenTimeSun[index]) && StringUtils.isEmpty(daysCloseTimeSun[index])) ||
					(StringUtils.isEmpty(daysOpenTimeSun[index]) && StringUtils.isNotEmpty(daysCloseTimeSun[index]))) {
				
				flagSun = true;
				insertValidMessage(section," office hours for Sun must have both open and closed hours.",sectionIndex);
				allOk = false;
			}
			
			if (!((StringUtils.isNotEmpty(daysOpenMon) && StringUtils.isNotEmpty(daysOpenTimeMon[index]) && StringUtils.isNotEmpty(daysCloseTimeMon[index])) ||
					(StringUtils.isNotEmpty(daysOpenTue) && StringUtils.isNotEmpty(daysOpenTimeTue[index]) && StringUtils.isNotEmpty(daysCloseTimeTue[index])) ||
					(StringUtils.isNotEmpty(daysOpenWed) && StringUtils.isNotEmpty(daysOpenTimeWed[index]) && StringUtils.isNotEmpty(daysCloseTimeWed[index])) ||
					(StringUtils.isNotEmpty(daysOpenThu) && StringUtils.isNotEmpty(daysOpenTimeThu[index]) && StringUtils.isNotEmpty(daysCloseTimeThu[index])) ||
					(StringUtils.isNotEmpty(daysOpenFri) && StringUtils.isNotEmpty(daysOpenTimeFri[index]) && StringUtils.isNotEmpty(daysCloseTimeFri[index])) ||
					(StringUtils.isNotEmpty(daysOpenSat) && StringUtils.isNotEmpty(daysOpenTimeSat[index]) && StringUtils.isNotEmpty(daysCloseTimeSat[index])) ||
					(StringUtils.isNotEmpty(daysOpenSun) && StringUtils.isNotEmpty(daysOpenTimeSun[index]) && StringUtils.isNotEmpty(daysCloseTimeSun[index])))) {
				flagAllDay = true;
			}
			
			if (!flagAllDay && StringUtils.isEmpty(timeZoneOfficeHrs[index])) {
								
				insertValidMessage(section," time zone of office hours is missing.",sectionIndex);
        			allOk = false;
        		
			}
			
			if (!StringUtils.isEmpty(daysOpenMon) && !flagMon) {
				
				if(StringUtils.isEmpty(daysOpenTimeMon[index]) || StringUtils.isEmpty(daysCloseTimeMon[index])) {
					
					insertValidMessage(section," office hours for Mon must have both open and closed hours.",sectionIndex);
					 
					allOk = false;
				}
			}
			
			if (!StringUtils.isEmpty(daysOpenTue) && !flagTue) {
				
				if(StringUtils.isEmpty(daysOpenTimeTue[index]) || StringUtils.isEmpty(daysCloseTimeTue[index])) {
					
					insertValidMessage(section," office hours for Tue must have both open and closed hours.",sectionIndex);
					allOk = false;
				}
			}
			
			if (!StringUtils.isEmpty(daysOpenWed) && !flagWed) {
						
				if(StringUtils.isEmpty(daysOpenTimeWed[index]) || StringUtils.isEmpty(daysCloseTimeWed[index])) {
					
					insertValidMessage(section," office hours for Wed must have both open and closed hours.",sectionIndex);
					allOk = false;
				}
			}
			if (!StringUtils.isEmpty(daysOpenThu) && !flagThu) {
				
				if(StringUtils.isEmpty(daysOpenTimeThu[index]) || StringUtils.isEmpty(daysCloseTimeThu[index])) {
					
					insertValidMessage(section," office hours for Thu must have both open and closed hours.",sectionIndex); 
					allOk = false;
				}
			}
			if (!StringUtils.isEmpty(daysOpenFri) && !flagFri) {
				
				if(StringUtils.isEmpty(daysOpenTimeFri[index]) || StringUtils.isEmpty(daysCloseTimeFri[index])) {
					
					insertValidMessage(section," office hours for Fri must have both open and closed hours.",sectionIndex);
					allOk = false;
				}
			}
			if (!StringUtils.isEmpty(daysOpenSat) && !flagSat) {
				
				if(StringUtils.isEmpty(daysOpenTimeSat[index]) || StringUtils.isEmpty(daysCloseTimeSat[index])) {
					
					insertValidMessage(section," office hours for Sat must have both open and closed hours.",sectionIndex);
					allOk = false;
				}
			}
			if (!StringUtils.isEmpty(daysOpenSun) && !flagSun) {
				
				if(StringUtils.isEmpty(daysOpenTimeSun[index]) || StringUtils.isEmpty(daysCloseTimeSun[index])) {
					
					insertValidMessage(section," office hours for Sun must have both open and closed hours.",sectionIndex);
					allOk = false;
				}
			}
			
		}
		
		return allOk;
		 
	}
	public boolean isValidFile(String fileName,long fileSize,byte[] fileContent,double maxFileSize,String uploadComment)
	{
		
		Pattern pattern =null;  
		Matcher matcher =null; 
		boolean isValid = false;
		boolean isValidFileType =false;
		
	  try{
		   	final String FILE_PATTERN = "(^[A-Za-z0-9@#&$'\"_\\s\\S-]+(\\.(?i)(bmp|jpg|gif|png|doc|docx|xls|xlsx|pdf|txt))$)";  
		  	System.out.println("file name= "+fileName.substring(0, fileName.lastIndexOf(".")));
		  	System.out.println("file name= "+fileName.substring(0, fileName.lastIndexOf(".")).length());
		  	if(fileName.substring(0, fileName.lastIndexOf(".")).length()>200)
		  	{
		  		errorMessagesVectorK.add("Section K file name is too long.");
		  		isValid =false;
		  	}
		  	else if(editBadChars(fileName,3,"file name",Constants.sectionAttachment))
		  	{
		  		pattern = Pattern.compile(FILE_PATTERN);
			  	matcher = pattern.matcher(fileName);
			  	isValidFileType = matcher.matches();
			  	if(isValidFileType)
			  	{
					if(((double)((fileSize/1024)/1024)+maxFileSize)>10)
					{
						errorMessagesVectorK.add("Section K attachments exceed size limit.");
						isValid =false;
					}else
					{
						isValid =true;
					}
			  	}
			  	else
			  	{
			  		errorMessagesVectorK.add("Section K attachments file type not allowed.");
			  		isValid =false;
			  	}
		  	}
		  	
		  	if(uploadComment.length()>200)
		  	{
		  		errorMessagesVectorK.add("Section K Description of attachment is too long.");
		  		isValid =false;
		  	}
		  	else
		  	{
		  		editBadChars(false,uploadComment, 3, "comments", Constants.sectionAttachment);
		  	}
	  }
	  catch(Exception ex)
	  {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  pattern =null;  
		  matcher =null; 
	  }
		
		
		return isValid && (errorMessagesVectorK.size()==0);
	}	
	public void insertValidMessage(String sectionName,String sFieldsDescription,String sectionDesc)
	{
		if(sectionName.equalsIgnoreCase(Constants.sectionGeneralInfo)){
			errorMessagesVectorA.add(sectionDesc + sFieldsDescription);
		}if(sectionName.equalsIgnoreCase(Constants.sectionReasonSubmitting)){
			errorMessagesVectorB.add(sectionDesc + sFieldsDescription);
		}if(sectionName.equalsIgnoreCase(Constants.sectionProviderInfo)){
			errorMessagesVectorC.add(sectionDesc + sFieldsDescription);
		}if(sectionName.equalsIgnoreCase(Constants.sectionWisconsin)){
			errorMessagesVectorD.add(sectionDesc + sFieldsDescription);
		}if(sectionName.equalsIgnoreCase(Constants.sectionPracticeAddress)){
			errorMessagesVectorE.add(sectionDesc + sFieldsDescription);
		}if(sectionName.equalsIgnoreCase(Constants.sectionAddressChange)){
			errorMessagesVectorF.add(sectionDesc + sFieldsDescription);
		}if(sectionName.equalsIgnoreCase(Constants.sectionOfficeLocation)){
			errorMessagesVectorG.add(sectionDesc + sFieldsDescription);
		}if(sectionName.equalsIgnoreCase(Constants.sectionPhysicians)){
			errorMessagesVectorH.add(sectionDesc + sFieldsDescription);
		}if(sectionName.equalsIgnoreCase(Constants.sectionPatientInfo)){
			errorMessagesVectorI.add(sectionDesc + sFieldsDescription);
		}if(sectionName.equalsIgnoreCase(Constants.sectionExpertise)){
			errorMessagesVectorJ.add(sectionDesc + sFieldsDescription);
		}if(sectionName.equalsIgnoreCase(Constants.sectionComments)){
			errorMessagesVectorK.add(sectionDesc + sFieldsDescription);
		}if(sectionName.equalsIgnoreCase(Constants.sectionAttachment)){
			errorMessagesVectorL.add(sectionDesc + sFieldsDescription);
		}
	}
	 public boolean editBadZipChars (String strInputValue, int grpNumber, String sFieldsDescription ,String sectionName, String section,String sTempZip) {
	    	boolean allOk = true;
	    	if (strInputValue == null || strInputValue.trim().equals("")) { 
	    		//Do Nothing
	        }
	        else{  
	        	if (PMFUtils.containsBadCharacter(strInputValue, grpNumber)) {
	        		System.out.println("Zip contain bad character");
	        		if(section.equalsIgnoreCase(Constants.sectionGeneralInfo)){
	        			errorMessagesVectorA.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
	        		}if(section.equalsIgnoreCase(Constants.sectionReasonSubmitting)){
	        			errorMessagesVectorB.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
	        		}if(section.equalsIgnoreCase(Constants.sectionProviderInfo)){
	        			errorMessagesVectorC.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
	        		}if(section.equalsIgnoreCase(Constants.sectionWisconsin)){
	        			errorMessagesVectorD.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
	        		}if(section.equalsIgnoreCase(Constants.sectionPracticeAddress)){
	        			errorMessagesVectorE.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
	        		}if(section.equalsIgnoreCase(Constants.sectionAddressChange)){
	        			errorMessagesVectorF.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
	        		}if(section.equalsIgnoreCase(Constants.sectionOfficeLocation)){
	        			errorMessagesVectorG.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
	        		}if(section.equalsIgnoreCase(Constants.sectionPhysicians)){
	        			errorMessagesVectorH.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
	        		}if(section.equalsIgnoreCase(Constants.sectionPatientInfo)){
	        			errorMessagesVectorI.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
	        		}if(section.equalsIgnoreCase(Constants.sectionExpertise)){
	        			errorMessagesVectorJ.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
	        		}if(section.equalsIgnoreCase(Constants.sectionComments)){
	        			errorMessagesVectorK.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
	        		}if(section.equalsIgnoreCase(Constants.sectionAttachment)){
	        			errorMessagesVectorL.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
	        		}
	        		allOk = false;
	        	}
	        	else  if (sTempZip.substring(5,9).equals("0000")|| sTempZip.substring(5,9).equals("9999")){
	        		System.out.println("Zip contain bad character");
	        		if(section.equalsIgnoreCase(Constants.sectionGeneralInfo)){
	        			errorMessagesVectorA.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
	        		}if(section.equalsIgnoreCase(Constants.sectionReasonSubmitting)){
	        			errorMessagesVectorB.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
	        		}if(section.equalsIgnoreCase(Constants.sectionProviderInfo)){
	        			errorMessagesVectorC.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
	        		}if(section.equalsIgnoreCase(Constants.sectionWisconsin)){
	        			errorMessagesVectorD.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
	        		}if(section.equalsIgnoreCase(Constants.sectionPracticeAddress)){
	        			errorMessagesVectorE.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
	        		}if(section.equalsIgnoreCase(Constants.sectionAddressChange)){
	        			errorMessagesVectorF.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
	        		}if(section.equalsIgnoreCase(Constants.sectionOfficeLocation)){
	        			errorMessagesVectorG.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
	        		}if(section.equalsIgnoreCase(Constants.sectionPhysicians)){
	        			errorMessagesVectorH.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
	        		}if(section.equalsIgnoreCase(Constants.sectionPatientInfo)){
	        			errorMessagesVectorI.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
	        		}if(section.equalsIgnoreCase(Constants.sectionExpertise)){
	        			errorMessagesVectorJ.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
	        		}if(section.equalsIgnoreCase(Constants.sectionComments)){
	        			errorMessagesVectorK.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
	        		}if(section.equalsIgnoreCase(Constants.sectionAttachment)){
	        			errorMessagesVectorL.add(sectionName+" "+sFieldsDescription + " contains invalid characters.");
	        		}
	        		allOk = false;
	        	}
	        }
	        return allOk;
	     }
	 
	 /* PMF Section Changes -- AD21239 --*/ 
	private static final String KM_ID_REQURIED = "KM_ID_REQURIED";
	private static final String KM_ID_CONTAINS_INVALID_CHAR = "KM_ID_CONTAINS_INVALID_CHAR";
	private static final String KM_ID_RESPONSE_ISSUE = "KM_ID_RESPONSE_ISSUE";
	private static final String KM_ID_INVALID = "KM_ID_INVALID";
	private static final String SECG_LOCATION2 = "- Location 2";
	private static final String SECG_LOCATION3 = "- Location 3";
	private static final String SECG_LOCATION4 = "- Location 4";
	private static final String SECG_LOCATION5 = "- Location 5";
	
	private boolean isValidKentuckyMedicaidId() 
	{
		boolean isValid = true;
		
		// Section E
		if(!isEmpty(kyMedicaidId1) && isEmpty(kyMedicaidPart1))
		{
			isValid = validateKentuckyMedicaidId(errorMessagesVectorE, Constants.sectionPracticeAddress, KM_ID_RESPONSE_ISSUE, null);
		}
		if("Y".equals(kyMedicaidPart1) && isEmpty(kyMedicaidId1))
		{
			isValid = validateKentuckyMedicaidId(errorMessagesVectorE, Constants.sectionPracticeAddress, KM_ID_REQURIED, null);
		}
		if( !isEmpty(kyMedicaidId1) && !validateNumeric(kyMedicaidId1) )
		{
			isValid = validateKentuckyMedicaidId(errorMessagesVectorE, Constants.sectionPracticeAddress, KM_ID_CONTAINS_INVALID_CHAR, null);
		}
		else if(( !isEmpty(kyMedicaidId1) && (kyMedicaidId1.length() < 8 || kyMedicaidId1.length() > 10) ))
		{
			isValid = validateKentuckyMedicaidId(errorMessagesVectorE, Constants.sectionPracticeAddress, KM_ID_INVALID, null);
		}
		
		// Section F
		if(!isEmpty(kyMedicaidId2) && isEmpty(kyMedicaidPart2))
		{
			isValid = validateKentuckyMedicaidId(errorMessagesVectorF, Constants.sectionAddressChange, KM_ID_RESPONSE_ISSUE, null);
		}
		if("Y".equals(kyMedicaidPart2) && isEmpty(kyMedicaidId2))
		{
			isValid = validateKentuckyMedicaidId(errorMessagesVectorF, Constants.sectionAddressChange, KM_ID_REQURIED, null);
		}
		if( !isEmpty(kyMedicaidId2) && !validateNumeric(kyMedicaidId2) )
		{
			isValid = validateKentuckyMedicaidId(errorMessagesVectorF, Constants.sectionAddressChange, KM_ID_CONTAINS_INVALID_CHAR, null);
		}
		else if(( !isEmpty(kyMedicaidId2) && (kyMedicaidId2.length() < 8 || kyMedicaidId2.length() > 10) ))
		{
			isValid = validateKentuckyMedicaidId(errorMessagesVectorF, Constants.sectionAddressChange, KM_ID_INVALID, null);
		}
		
		// Section G 2
		if(!isEmpty(kyMedicaidId3) && isEmpty(kyMedicaidPart3))
		{
			isValid = validateKentuckyMedicaidId(errorMessagesVectorG, Constants.sectionOfficeLocation, KM_ID_RESPONSE_ISSUE, SECG_LOCATION2);
		}
		if("Y".equals(kyMedicaidPart3) && isEmpty(kyMedicaidId3))
		{
			isValid = validateKentuckyMedicaidId(errorMessagesVectorG, Constants.sectionOfficeLocation, KM_ID_REQURIED, SECG_LOCATION2);
		}
		if( !isEmpty(kyMedicaidId3) && !validateNumeric(kyMedicaidId3) )
		{
			isValid = validateKentuckyMedicaidId(errorMessagesVectorG, Constants.sectionOfficeLocation, KM_ID_CONTAINS_INVALID_CHAR, SECG_LOCATION2);
		}
		else if(( !isEmpty(kyMedicaidId3) && (kyMedicaidId3.length() < 8 || kyMedicaidId3.length() > 10) ))
		{
			isValid = validateKentuckyMedicaidId(errorMessagesVectorG, Constants.sectionOfficeLocation, KM_ID_INVALID, SECG_LOCATION2);
		}
		
		// Section G 3
		if(!isEmpty(kyMedicaidId4) && isEmpty(kyMedicaidPart4))
		{
			isValid = validateKentuckyMedicaidId(errorMessagesVectorG, Constants.sectionOfficeLocation, KM_ID_RESPONSE_ISSUE, SECG_LOCATION3);
		}
		if("Y".equals(kyMedicaidPart4) && isEmpty(kyMedicaidId4))
		{
			isValid = validateKentuckyMedicaidId(errorMessagesVectorG, Constants.sectionOfficeLocation, KM_ID_REQURIED, SECG_LOCATION3);
		}
		if( !isEmpty(kyMedicaidId4) && !validateNumeric(kyMedicaidId4) )
		{
			isValid = validateKentuckyMedicaidId(errorMessagesVectorG, Constants.sectionOfficeLocation, KM_ID_CONTAINS_INVALID_CHAR, SECG_LOCATION3);
		}
		else if(( !isEmpty(kyMedicaidId4) && (kyMedicaidId4.length() < 8 || kyMedicaidId4.length() > 10) ))
		{
			isValid = validateKentuckyMedicaidId(errorMessagesVectorG, Constants.sectionOfficeLocation, KM_ID_INVALID, SECG_LOCATION3);
		}
		
		// Section G 4
		if(!isEmpty(kyMedicaidId5) && isEmpty(kyMedicaidPart5))
		{
			isValid = validateKentuckyMedicaidId(errorMessagesVectorG, Constants.sectionOfficeLocation, KM_ID_RESPONSE_ISSUE, SECG_LOCATION4);
		}
		if("Y".equals(kyMedicaidPart5) && isEmpty(kyMedicaidId5))
		{
			isValid = validateKentuckyMedicaidId(errorMessagesVectorG, Constants.sectionOfficeLocation, KM_ID_REQURIED, SECG_LOCATION4);
		}
		if( !isEmpty(kyMedicaidId5) && !validateNumeric(kyMedicaidId5) )
		{
			isValid = validateKentuckyMedicaidId(errorMessagesVectorG, Constants.sectionOfficeLocation, KM_ID_CONTAINS_INVALID_CHAR, SECG_LOCATION4);
		}
		else if(( !isEmpty(kyMedicaidId5) && (kyMedicaidId5.length() < 8 || kyMedicaidId5.length() > 10) ))
		{
			isValid = validateKentuckyMedicaidId(errorMessagesVectorG, Constants.sectionOfficeLocation, KM_ID_INVALID, SECG_LOCATION4);
		}
		
		// Section G 5
		if(!isEmpty(kyMedicaidId6) && isEmpty(kyMedicaidPart6))
		{
			isValid = validateKentuckyMedicaidId(errorMessagesVectorG, Constants.sectionOfficeLocation, KM_ID_RESPONSE_ISSUE, SECG_LOCATION5);
		}
		if("Y".equals(kyMedicaidPart6) && isEmpty(kyMedicaidId6))
		{
			isValid = validateKentuckyMedicaidId(errorMessagesVectorG, Constants.sectionOfficeLocation, KM_ID_REQURIED, SECG_LOCATION5);
		}
		if( !isEmpty(kyMedicaidId6) && !validateNumeric(kyMedicaidId6) )
		{
			isValid = validateKentuckyMedicaidId(errorMessagesVectorG, Constants.sectionOfficeLocation, KM_ID_CONTAINS_INVALID_CHAR, SECG_LOCATION5);
		}
		else if(( !isEmpty(kyMedicaidId6) && (kyMedicaidId6.length() < 8 || kyMedicaidId6.length() > 10) ))
		{
			isValid = validateKentuckyMedicaidId(errorMessagesVectorG, Constants.sectionOfficeLocation, KM_ID_INVALID, SECG_LOCATION5);
		}
		return isValid;
	}
	
	
	private boolean validateKentuckyMedicaidId(Vector errorMessagesVectorTemp, String sectionTemp, String issueFlag, String secGLocation) 
	{
		logger.debug(" ValidateKentuckyMedicaidId -- sectionTemp: " + sectionTemp+", issueFlag: "+issueFlag+", secGLocation: "+secGLocation);
		boolean validateKentuckyMedicaidId = !isEmpty(sectionTemp) || !isEmpty(issueFlag);
		boolean isValid = true;
		
		if(sectionTemp.equals(Constants.sectionOfficeLocation)){
			sectionTemp = sectionTemp + secGLocation;
		}
		
		if (issueFlag.equals(KM_ID_REQURIED)) {
			errorMessagesVectorTemp.add(sectionTemp+ " Kentucky Medicaid ID is required.");
			isValid = false;
		}
		if (issueFlag.equals(KM_ID_CONTAINS_INVALID_CHAR)) {
			errorMessagesVectorTemp.add(sectionTemp+ " Kentucky Medicaid ID contains invalid characters.");
			isValid = false;
		}
		if (issueFlag.equals(KM_ID_RESPONSE_ISSUE)) {
			errorMessagesVectorTemp.add(sectionTemp+ " Are you currently participating in the Kentucky Medicaid Program is required.");
			isValid = false;
		}
		if (issueFlag.equals(KM_ID_INVALID)) {
			errorMessagesVectorTemp.add(sectionTemp+ " Kentucky Medicaid ID is invalid.");
			isValid = false;
		}
		logger.debug(" ValidateKentuckyMedicaidId return" + isValid);
		return isValid;
	}
	
	 /* PMF Section Changes -- AD21239 --*/ 
	    
}