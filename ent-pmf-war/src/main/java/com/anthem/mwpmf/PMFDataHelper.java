// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   PMFDataHelper.java

package com.anthem.mwpmf;

import java.util.ArrayList;
import java.util.List;

import com.anthem.enterprise.client.model.Attachment;
import com.anthem.enterprise.client.model.PMFPayload;
import com.anthem.enterprise.client.model.ServiceRequest;
import com.anthem.util.Constants;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javazoom.upload.MultipartFormDataRequest;

// Referenced classes of package com.anthem.mwpmf:
//            Provider

public class PMFDataHelper implements Constants {
	private final static Logger logger = LogManager.getLogger(PMFDataHelper.class);

	public PMFDataHelper() {
	}

	public static void setVAFormData(NYVAProvMaintFormBean vaprovform, HttpServletRequest httpservletrequest) {
		vaprovform.setEffDate(httpservletrequest.getParameter("Effective_Date"));
		vaprovform.setProvLastName(httpservletrequest.getParameter("Gen_Provider_Last_Name"));
		vaprovform.setProvFirstName(httpservletrequest.getParameter("Gen_Provider_First_Name"));
		vaprovform.setProvTaxId(httpservletrequest.getParameter("Gen_Ind_TIN"));
		vaprovform.setProvNPI(httpservletrequest.getParameter("GenSec_Prov_Ded_NPI"));
		vaprovform.setPracName(httpservletrequest.getParameter("Gem_Provider_Practice_Name"));
		vaprovform.setPracTaxId(httpservletrequest.getParameter("Gen_Pract_TIN"));
		vaprovform.setPracNPI(httpservletrequest.getParameter("GenSec_Pract_Ded_NPI"));
		vaprovform.setContactLastName(httpservletrequest.getParameter("Contact_Person_Last_Name"));
		vaprovform.setContactFirstName(httpservletrequest.getParameter("Contact_Person_First_Name"));
		vaprovform.setContactEmail(httpservletrequest.getParameter("Email_Address"));
		vaprovform.setContactPhone(httpservletrequest.getParameter("Phone_Number"));
		vaprovform.setEmailBody(httpservletrequest.getParameter("body"));
		vaprovform.setToEmail(httpservletrequest.getParameter("toemail"));

		String allFileNames = httpservletrequest.getParameter("uploadFileName");
		String allFileSizes = httpservletrequest.getParameter("uploadFileSize");
		String allFileComments = httpservletrequest.getParameter("uploadFileComment");

		// vaprovform.setAllFileNames(allFileNames);
		// vaprovform.setAllFileSizes(allFileSizes);
		// vaprovform.setAllFileComments(allFileComments);

		if ((allFileNames != null) && !allFileNames.equals("") && (allFileSizes != null) && !allFileSizes.equals("")
				&& (allFileComments != null) && !allFileComments.equals("")) {
			String[] fileNameArray = allFileNames.split("::");
			String[] fileSizeArray = allFileSizes.split("::");
			String[] fileCommentArray = allFileComments.split("::");

			if ((fileNameArray.length == fileSizeArray.length) && (fileNameArray.length == fileCommentArray.length)) {
				List docLst = new ArrayList();
				for (int i = 0; i < fileNameArray.length; i++) {
					UploadDocListBean doc = new UploadDocListBean();
					doc.setFileName(fileNameArray[i]);
					doc.setFileSize(String.format("%.2f", ((double) (Integer.parseInt(fileSizeArray[i]) / 1024) / 1024)));
					doc.setDocComment(fileCommentArray[i]);
					docLst.add(doc);
				}
				vaprovform.setUploadFileListDtls(docLst);
			} else {
				// TODO: handle error
			}
		}
	}

	public static void setNYFormData(NYVAProvMaintFormBean nyprovform, HttpServletRequest httpservletrequest) {
		nyprovform.setEffDate(httpservletrequest.getParameter("Effective_Date"));
		nyprovform.setProvLastName(httpservletrequest.getParameter("Provider_Last_Name_Gen_Individ"));
		nyprovform.setProvFirstName(httpservletrequest.getParameter("Provider_First_Name_Gen_Individ"));
		nyprovform.setProvTaxId(httpservletrequest.getParameter("Individual_Tax_Identification_Number_Gen_Individ"));
		nyprovform.setProvNPI(httpservletrequest.getParameter("Individual_Provider_Identification_Number_Gen_Individ"));
		nyprovform.setPracName(httpservletrequest.getParameter("Provider_Practice_Name_Gen_Prac"));
		nyprovform.setPracTaxId(httpservletrequest.getParameter("Practice_Tax_Identification_Number_Gen_Prac"));
		nyprovform.setPracNPI(httpservletrequest.getParameter("Org_NPI_Gen_Prac"));
		nyprovform.setContactLastName(httpservletrequest.getParameter("Contact_Person_Last_Name"));
		nyprovform.setContactFirstName(httpservletrequest.getParameter("Contact_Person_First_Name"));
		nyprovform.setContactEmail(httpservletrequest.getParameter("Email_Address"));
		nyprovform.setContactPhone(httpservletrequest.getParameter("Phone_Number"));
		nyprovform.setEmailBody(httpservletrequest.getParameter("body"));
		nyprovform.setToEmail(httpservletrequest.getParameter("toemail"));

		String allFileNames = httpservletrequest.getParameter("uploadFileName");
		String allFileSizes = httpservletrequest.getParameter("uploadFileSize");
		String allFileComments = httpservletrequest.getParameter("uploadFileComment");

		if ((allFileNames != null) && !allFileNames.equals("") && (allFileSizes != null) && !allFileSizes.equals("")
				&& (allFileComments != null) && !allFileComments.equals("")) {
			String[] fileNameArray = allFileNames.split("::");
			String[] fileSizeArray = allFileSizes.split("::");
			String[] fileCommentArray = allFileComments.split("::");

			if ((fileNameArray.length == fileSizeArray.length) && (fileNameArray.length == fileCommentArray.length)) {
				List docLst = new ArrayList();
				for (int i = 0; i < fileNameArray.length; i++) {
					UploadDocListBean doc = new UploadDocListBean();
					doc.setFileName(fileNameArray[i]);
					doc.setFileSize(String.format("%.2f", ((double) (Integer.parseInt(fileSizeArray[i]) / 1024) / 1024)));
					doc.setDocComment(fileCommentArray[i]);
					docLst.add(doc);
				}
				nyprovform.setUploadFileListDtls(docLst);
			} else {
				// TODO: handle error
			}
		}
	}

	public static void setDentalFormData(NYVAProvMaintFormBean dentalprovform, HttpServletRequest httpservletrequest) {
		String effectiveDate = httpservletrequest.getParameter("Effective_Date");
		// If Effective_Date is not selected then Option 2
		if (StringUtils.isEmpty(effectiveDate)) {
			dentalprovform.setEffDate(httpservletrequest.getParameter("Effective_Date_2"));
			dentalprovform.setProvLastName(httpservletrequest.getParameter("Gen_Provider_Last_Name_2"));
			dentalprovform.setProvFirstName(httpservletrequest.getParameter("Gen_Provider_First_Name_2"));
			dentalprovform.setPracName(httpservletrequest.getParameter("Gen_Practice_Name_2"));
			dentalprovform.setPracTaxId(httpservletrequest.getParameter("Gen_Pract_TIN_2"));
			dentalprovform.setContactLastName(httpservletrequest.getParameter("Contact_Person_Last_Name_2"));
			dentalprovform.setContactFirstName(httpservletrequest.getParameter("Contact_Person_First_Name_2"));
			dentalprovform.setContactEmail(httpservletrequest.getParameter("Email_Address_2"));
			dentalprovform.setContactPhone(httpservletrequest.getParameter("Phone_Number_2"));
		} else { // Option 1
			dentalprovform.setEffDate(effectiveDate);
			dentalprovform.setProvLastName(httpservletrequest.getParameter("Gen_Provider_Last_Name"));
			dentalprovform.setProvFirstName(httpservletrequest.getParameter("Gen_Provider_First_Name"));
			dentalprovform.setProvTaxId(httpservletrequest.getParameter("Gen_Ind_TIN"));
			dentalprovform.setProvNPI(httpservletrequest.getParameter("GenSec_Prov_Ded_NPI"));
			dentalprovform.setContactLastName(httpservletrequest.getParameter("Contact_Person_Last_Name"));
			dentalprovform.setContactFirstName(httpservletrequest.getParameter("Contact_Person_First_Name"));
			dentalprovform.setContactEmail(httpservletrequest.getParameter("Email_Address"));
			dentalprovform.setContactPhone(httpservletrequest.getParameter("Phone_Number"));
		}
		dentalprovform.setEmailBody(httpservletrequest.getParameter("body"));
		dentalprovform.setToEmail(httpservletrequest.getParameter("toemail"));

		String allFileNames = httpservletrequest.getParameter("uploadFileName");
		String allFileSizes = httpservletrequest.getParameter("uploadFileSize");
		String allFileComments = httpservletrequest.getParameter("uploadFileComment");

		// dentalprovform.setAllFileNames(allFileNames);
		// dentalprovform.setAllFileSizes(allFileSizes);
		// dentalprovform.setAllFileComments(allFileComments);

		if ((allFileNames != null) && !allFileNames.equals("") && (allFileSizes != null) && !allFileSizes.equals("")
				&& (allFileComments != null) && !allFileComments.equals("")) {
			String[] fileNameArray = allFileNames.split("::");
			String[] fileSizeArray = allFileSizes.split("::");
			String[] fileCommentArray = allFileComments.split("::");

			if ((fileNameArray.length == fileSizeArray.length) && (fileNameArray.length == fileCommentArray.length)) {
				List docLst = new ArrayList();
				for (int i = 0; i < fileNameArray.length; i++) {
					UploadDocListBean doc = new UploadDocListBean();
					doc.setFileName(fileNameArray[i]);
					doc.setFileSize(String.format("%.2f", ((double) (Integer.parseInt(fileSizeArray[i]) / 1024) / 1024)));
					doc.setDocComment(fileCommentArray[i]);
					docLst.add(doc);
				}
				dentalprovform.setUploadFileListDtls(docLst);
			} else {
				// TODO: handle error
			}
		}
	}

	public static void setProviderData(Provider provider, HttpServletRequest httpservletrequest) {
		provider.setTaxID(httpservletrequest.getParameter("taxID"));

		provider.setAnthemPIN(httpservletrequest.getParameter("anthemPIN"));
		// WI/MO changes
		provider.setMOProviderId(httpservletrequest.getParameter("moId"));
		provider.setWIProviderId(httpservletrequest.getParameter("wiId"));
		/*
		 * 2013 SSCR 13503 change
		 * provider.setGrpMedicaidId(httpservletrequest.getParameter
		 * ("grpmedicaidId"));
		 */
		provider.setGrpNPINumber(httpservletrequest.getParameter("grpNpiNumber"));
		provider.setProvNPINumber(httpservletrequest.getParameter("provNpiNumber"));
		/*
		 * 2013 SSCR 13503 changes
		 * provider.setTaxonomyNumber(httpservletrequest.
		 * getParameter("taxonomyNum"));
		 */

		provider.setSoloGroup(initialNullValues(httpservletrequest.getParameter("soloGroup")));

		provider.setNumberInGroup(httpservletrequest.getParameter("numberInGroup"));
		provider.setRapidUpdate(getSelectedValue(httpservletrequest, "rapidUpdate"));
		provider.setEffectiveDate(httpservletrequest.getParameter("effectiveDate"));
		provider.setAddProvider(getSelectedValue(httpservletrequest, "addProvider"));
		provider.setDelProvider(getSelectedValue(httpservletrequest, "delProvider"));
		provider.setAddLocation(getSelectedValue(httpservletrequest, "addLocation"));
		provider.setAddProvToLocation(getSelectedValue(httpservletrequest, "addProvToLocation"));
		provider.setChgSpecialty(getSelectedValue(httpservletrequest, "chgSpecialty"));
		provider.setChgProvName(getSelectedValue(httpservletrequest, "chgProvName"));
		provider.setDelLocation(getSelectedValue(httpservletrequest, "delLocation"));
		provider.setDelProvFromLocation(getSelectedValue(httpservletrequest, "delProvFromLocation"));
		provider.setChgPracName(getSelectedValue(httpservletrequest, "chgPracName"));
		provider.setChgPracAddress(getSelectedValue(httpservletrequest, "chgPracAddress"));
		provider.setChgPracPhone(getSelectedValue(httpservletrequest, "chgPracPhone"));
		provider.setChgTaxID(getSelectedValue(httpservletrequest, "chgTaxID"));
		provider.setChgBillName(getSelectedValue(httpservletrequest, "chgBillName"));
		provider.setChgBillAddress(getSelectedValue(httpservletrequest, "chgBillAddress"));
		provider.setChgBillPhone(getSelectedValue(httpservletrequest, "chgBillPhone"));
		provider.setChgOfficeHours(getSelectedValue(httpservletrequest, "chgOfficeHours"));
		provider.setAddNPI(getSelectedValue(httpservletrequest, "addNPI"));
		provider.setChgNPI(getSelectedValue(httpservletrequest, "chgNPI"));
		provider.setEditAreasOfExpertise(getSelectedValue(httpservletrequest, "addAreasOfExpertise"));
		provider.setEditPatientInfo(getSelectedValue(httpservletrequest, "addPatientInfo"));
		provider.setProvDir2(initialNullValues(httpservletrequest.getParameter("provDir2")));

		if (httpservletrequest.getParameter("delReason").length() > 200)
			provider.setDelReason(httpservletrequest.getParameter("delReason").substring(0, 200));
		else
			provider.setDelReason(httpservletrequest.getParameter("delReason"));
		if (httpservletrequest.getParameter("comments").length() > 750)
			provider.setComments(httpservletrequest.getParameter("comments").substring(0, 750));
		else
			provider.setComments(httpservletrequest.getParameter("comments"));
		provider.setConfirmation(getSelectedValue(httpservletrequest, "confirmation"));
		provider.setOldTaxID(httpservletrequest.getParameter("oldTaxID"));
		provider.setProvFnm(httpservletrequest.getParameter("provFnm"));
		provider.setProvMI(httpservletrequest.getParameter("provMI"));
		provider.setProvLnm(httpservletrequest.getParameter("provLnm"));
		provider.setTitle(httpservletrequest.getParameter("title"));
		provider.setSpecialityInfo(initialNullValues(httpservletrequest.getParameter("specialityInfo")));
		if ((provider.getSpecialityInfo()).equalsIgnoreCase("primSpecialtyPhy")) {
			provider.setPrimSpecialtyPhy("primSpecialtyPhy");
		}
		if ((provider.getSpecialityInfo()).equalsIgnoreCase("specialtyCarePhy")) {
			provider.setSpecialtyCarePhy("specialtyCarePhy");
		}
		if ((provider.getSpecialityInfo()).equalsIgnoreCase("other")) {
			provider.setOther("other");
		}
		/* WLPRD00971518 | Fix for Clear Selection | UST Global | 2014 October */
		if ((provider.getSpecialityInfo()).equalsIgnoreCase(" ")) {
			provider.setClearSelection(" ");
		}
		provider.setSpecialty(initialNullValues(httpservletrequest.getParameter("specialty")));

		provider.setIndServTypes(initialNullValues(httpservletrequest.getParameter("indServTypes")));

		if (provider.getIndServTypes().equalsIgnoreCase("ssMedical")) {
			provider.setSsMedical("Y");
		}
		if (provider.getIndServTypes().equalsIgnoreCase("ssDental")) {
			provider.setSsDental("Y");
		}
		if (provider.getIndServTypes().equalsIgnoreCase("ssVision")) {
			provider.setSsVision("Y");
		}
		if (provider.getIndServTypes().equalsIgnoreCase("other")) {
			provider.setSsOtherServType("Y");
		}
		/*
		 * System.out.println("Before setting specialty info");
		 * System.out.println
		 * ("Before setting specialty info @"+httpservletrequest
		 * .getParameter("specialtyInfo")+"@");
		 * provider.setSpecialtyInfo(initialNullValues
		 * (httpservletrequest.getParameter("specialtyInfo")));
		 */
		// change for section c 13/6/2012
		// provider.setClearSelection(httpservletrequest.getParameter("clearSelection"));
		provider.setMedicarePartTrad(initialNullValues(httpservletrequest.getParameter("medicarePartTrad")));
		provider.setMedicareApplDt(httpservletrequest.getParameter("medicareApplDt"));
		provider.setMedicareOptOut(initialNullValues(httpservletrequest.getParameter("medicareOptOut")));
		provider.setMedicareOptOutDt(httpservletrequest.getParameter("medicareOptOutDt"));

		provider.setNpSupSpec(getSelectedValue(httpservletrequest, "npSupSpec"));
		provider.setNpSupPMP(getSelectedValue(httpservletrequest, "npSupPMP"));
		provider.setRetailHealthClinic(getSelectedValue(httpservletrequest, "retailHealthClinic"));
		provider.setWalkInDrOffice(getSelectedValue(httpservletrequest, "walkInDrOffice"));
		provider.setUrgentCare(getSelectedValue(httpservletrequest, "urgentCare"));
		provider.setCoveringPMP(getSelectedValue(httpservletrequest, "coveringPMP"));
		provider.setCertMidwife(getSelectedValue(httpservletrequest, "certMidwife"));
		provider.setPrenatelCareCoord(getSelectedValue(httpservletrequest, "prenatelCareCoord"));
		provider.setTribalHealthCtr(getSelectedValue(httpservletrequest, "tribalHealthCtr"));
		provider.setClinic(getSelectedValue(httpservletrequest, "clinic"));
		provider.setMedicationAssistedTreatment(getSelectedValue(httpservletrequest, "medicationAssistedTreatment"));
		provider.setResidentialTreatmentCenter(getSelectedValue(httpservletrequest, "residentialTreatmentCenter"));
		provider.setSubstanceUseDisorderAdults(getSelectedValue(httpservletrequest, "substanceUseDisorderAdults"));
		provider.setSubstanceUseDisorderChild(getSelectedValue(httpservletrequest, "substanceUseDisorderChild"));
		provider.setTelehealthProv(getSelectedValue(httpservletrequest, "telehealthProv"));
		provider.setFwdHealthCertNPI1(httpservletrequest.getParameter("fwdHealthCertNPI1"));
		provider.setFwdHealthCertNPIBilled1(initialNullValues(httpservletrequest
				.getParameter("fwdHealthCertNPIBilled1")));
		provider.setFwdHealthCertEffDt1(httpservletrequest.getParameter("fwdHealthCertEffDt1"));
		provider.setFwdHealthRecertEffDt1(httpservletrequest.getParameter("fwdHealthRecertEffDt1"));
		provider.setFwdHealthCertNPI2(httpservletrequest.getParameter("fwdHealthCertNPI2"));
		provider.setFwdHealthCertNPIBilled2(initialNullValues(httpservletrequest
				.getParameter("fwdHealthCertNPIBilled2")));
		provider.setFwdHealthCertEffDt2(httpservletrequest.getParameter("fwdHealthCertEffDt2"));
		provider.setFwdHealthRecertEffDt2(httpservletrequest.getParameter("fwdHealthRecertEffDt2"));
		provider.setFwdHealthCertNPI3(httpservletrequest.getParameter("fwdHealthCertNPI3"));
		provider.setFwdHealthCertNPIBilled3(initialNullValues(httpservletrequest
				.getParameter("fwdHealthCertNPIBilled3")));
		provider.setFwdHealthCertEffDt3(httpservletrequest.getParameter("fwdHealthCertEffDt3"));
		provider.setFwdHealthRecertEffDt3(httpservletrequest.getParameter("fwdHealthRecertEffDt3"));

		// change for section c 13/6/2012

		provider.setBoardCertified(initialNullValues(httpservletrequest.getParameter("boardCertified")));
		provider.setCertExamDT(httpservletrequest.getParameter("certExamDT"));
		provider.setProvSSN(httpservletrequest.getParameter("provNumber"));
		provider.setUpinNumber(httpservletrequest.getParameter("upinNumber"));
		provider.setProfLicenseNumber(httpservletrequest.getParameter("profLicenseNumber"));
		provider.setCaqhIDNumber(httpservletrequest.getParameter("caqhIDNumber"));
		provider.setCaqhExplanation(httpservletrequest.getParameter("caqhExplanation"));
		provider.setProvDOB(httpservletrequest.getParameter("provDOB"));
		provider.setProvGender(initialNullValues(httpservletrequest.getParameter("provGender")));
		provider.setNewPatients(initialNullValues(httpservletrequest.getParameter("newPatients")));
		provider.setAgeLimitMin(httpservletrequest.getParameter("ageLimitMin"));
		provider.setAgeLimitMax(httpservletrequest.getParameter("ageLimitMax"));

		provider.setEthnicOrigin(initialNullValues(httpservletrequest.getParameter("ethnicOrigin")));

		provider.setPracName1(httpservletrequest.getParameter("pracName1"));
		provider.setBillMedicareGroup6(httpservletrequest.getParameter("billMedicareGroup6"));
		provider.setBillMedicareIndividual6(httpservletrequest.getParameter("billMedicareIndividual6"));
		provider.setGrpEntityName1(httpservletrequest.getParameter("grpEntityName1"));
		provider.setSpecialty1(httpservletrequest.getParameter("specialty1"));
		provider.setTaxID1(httpservletrequest.getParameter("taxID1"));
		provider.setEffectiveDate1(httpservletrequest.getParameter("effectiveDate1"));
		provider.setGrpEntityName2(httpservletrequest.getParameter("grpEntityName2"));
		provider.setSpecialty2(httpservletrequest.getParameter("specialty2"));
		provider.setTaxID2(httpservletrequest.getParameter("taxID2"));
		provider.setEffectiveDate2(httpservletrequest.getParameter("effectiveDate2"));
		provider.setGrpEntityName3(httpservletrequest.getParameter("grpEntityName3"));
		provider.setSpecialty3(httpservletrequest.getParameter("specialty3"));
		provider.setTaxID3(httpservletrequest.getParameter("taxID3"));
		provider.setEffectiveDate3(httpservletrequest.getParameter("effectiveDate3"));
		provider.setGrpEntityName4(httpservletrequest.getParameter("grpEntityName4"));
		provider.setSpecialty4(httpservletrequest.getParameter("specialty4"));
		provider.setTaxID4(httpservletrequest.getParameter("taxID4"));
		provider.setEffectiveDate4(httpservletrequest.getParameter("effectiveDate4"));
		provider.setGrpEntityName5(httpservletrequest.getParameter("grpEntityName5"));
		provider.setSpecialty5(httpservletrequest.getParameter("specialty5"));
		provider.setTaxID5(httpservletrequest.getParameter("taxID5"));
		provider.setEffectiveDate5(httpservletrequest.getParameter("effectiveDate5"));

		provider.setCpHospitalAddress(httpservletrequest.getParameterValues("cpHospitalAddress"));
		provider.setCpHospitalCity(httpservletrequest.getParameterValues("cpHospitalCity"));
		provider.setCpHospitalState(httpservletrequest.getParameterValues("cpHospitalState"));
		provider.setCpHospitalZip(httpservletrequest.getParameterValues("cpHospitalZip"));

		provider.setPubicTrans1(initialNullValues(httpservletrequest.getParameter("pubicTrans1")));
		provider.setHandicapAccess1(initialNullValues(httpservletrequest.getParameter("handicapAccess1")));
		// 2013 SSCR 13503 change
		provider.setProvDir1(initialNullValues(httpservletrequest.getParameter("provDir1")));
		provider.setProvDir2(initialNullValues(httpservletrequest.getParameter("provDir2")));
		provider.setProvDir3(initialNullValues(httpservletrequest.getParameter("provDir3")));
		provider.setProvDir4(initialNullValues(httpservletrequest.getParameter("provDir4")));
		provider.setProvDir5(initialNullValues(httpservletrequest.getParameter("provDir5")));
		provider.setProvDir6(initialNullValues(httpservletrequest.getParameter("provDir6")));
		// 2013 SSCR 13503change end

		// System.out.println("test provdir "+initialNullValues(httpservletrequest.getParameter("provDir1")));
		provider.setEveningHrs1(initialNullValues(httpservletrequest.getParameter("eveningHrs1")));

		provider.setTimeZoneOfficeHrs(httpservletrequest.getParameterValues("timeZoneOfficeHrs"));

		provider.setDaysOpenMon0(getSelectedValue(httpservletrequest, "daysOpenMon0"));
		provider.setDaysOpenTue0(getSelectedValue(httpservletrequest, "daysOpenTue0"));
		provider.setDaysOpenWed0(getSelectedValue(httpservletrequest, "daysOpenWed0"));
		provider.setDaysOpenThu0(getSelectedValue(httpservletrequest, "daysOpenThu0"));
		provider.setDaysOpenFri0(getSelectedValue(httpservletrequest, "daysOpenFri0"));
		provider.setDaysOpenSat0(getSelectedValue(httpservletrequest, "daysOpenSat0"));
		provider.setDaysOpenSun0(getSelectedValue(httpservletrequest, "daysOpenSun0"));

		provider.setDaysOpenMon1(getSelectedValue(httpservletrequest, "daysOpenMon1"));
		provider.setDaysOpenTue1(getSelectedValue(httpservletrequest, "daysOpenTue1"));
		provider.setDaysOpenWed1(getSelectedValue(httpservletrequest, "daysOpenWed1"));
		provider.setDaysOpenThu1(getSelectedValue(httpservletrequest, "daysOpenThu1"));
		provider.setDaysOpenFri1(getSelectedValue(httpservletrequest, "daysOpenFri1"));
		provider.setDaysOpenSat1(getSelectedValue(httpservletrequest, "daysOpenSat1"));
		provider.setDaysOpenSun1(getSelectedValue(httpservletrequest, "daysOpenSun1"));

		provider.setDaysOpenMon2(getSelectedValue(httpservletrequest, "daysOpenMon2"));
		provider.setDaysOpenTue2(getSelectedValue(httpservletrequest, "daysOpenTue2"));
		provider.setDaysOpenWed2(getSelectedValue(httpservletrequest, "daysOpenWed2"));
		provider.setDaysOpenThu2(getSelectedValue(httpservletrequest, "daysOpenThu2"));
		provider.setDaysOpenFri2(getSelectedValue(httpservletrequest, "daysOpenFri2"));
		provider.setDaysOpenSat2(getSelectedValue(httpservletrequest, "daysOpenSat2"));
		provider.setDaysOpenSun2(getSelectedValue(httpservletrequest, "daysOpenSun2"));

		provider.setDaysOpenMon3(getSelectedValue(httpservletrequest, "daysOpenMon3"));
		provider.setDaysOpenTue3(getSelectedValue(httpservletrequest, "daysOpenTue3"));
		provider.setDaysOpenWed3(getSelectedValue(httpservletrequest, "daysOpenWed3"));
		provider.setDaysOpenThu3(getSelectedValue(httpservletrequest, "daysOpenThu3"));
		provider.setDaysOpenFri3(getSelectedValue(httpservletrequest, "daysOpenFri3"));
		provider.setDaysOpenSat3(getSelectedValue(httpservletrequest, "daysOpenSat3"));
		provider.setDaysOpenSun3(getSelectedValue(httpservletrequest, "daysOpenSun3"));

		provider.setDaysOpenMon4(getSelectedValue(httpservletrequest, "daysOpenMon4"));
		provider.setDaysOpenTue4(getSelectedValue(httpservletrequest, "daysOpenTue4"));
		provider.setDaysOpenWed4(getSelectedValue(httpservletrequest, "daysOpenWed4"));
		provider.setDaysOpenThu4(getSelectedValue(httpservletrequest, "daysOpenThu4"));
		provider.setDaysOpenFri4(getSelectedValue(httpservletrequest, "daysOpenFri4"));
		provider.setDaysOpenSat4(getSelectedValue(httpservletrequest, "daysOpenSat4"));
		provider.setDaysOpenSun4(getSelectedValue(httpservletrequest, "daysOpenSun4"));

		provider.setDaysOpenMon5(getSelectedValue(httpservletrequest, "daysOpenMon5"));
		provider.setDaysOpenTue5(getSelectedValue(httpservletrequest, "daysOpenTue5"));
		provider.setDaysOpenWed5(getSelectedValue(httpservletrequest, "daysOpenWed5"));
		provider.setDaysOpenThu5(getSelectedValue(httpservletrequest, "daysOpenThu5"));
		provider.setDaysOpenFri5(getSelectedValue(httpservletrequest, "daysOpenFri5"));
		provider.setDaysOpenSat5(getSelectedValue(httpservletrequest, "daysOpenSat5"));
		provider.setDaysOpenSun5(getSelectedValue(httpservletrequest, "daysOpenSun5"));

		provider.setHipShow(httpservletrequest.getParameter("hipShow"));
		provider.setDaysOpenTimeMon(httpservletrequest.getParameterValues("daysOpenTimeMon"));
		provider.setDaysOpenTimeTue(httpservletrequest.getParameterValues("daysOpenTimeTue"));
		provider.setDaysOpenTimeWed(httpservletrequest.getParameterValues("daysOpenTimeWed"));
		provider.setDaysOpenTimeThu(httpservletrequest.getParameterValues("daysOpenTimeThu"));
		provider.setDaysOpenTimeFri(httpservletrequest.getParameterValues("daysOpenTimeFri"));
		provider.setDaysOpenTimeSat(httpservletrequest.getParameterValues("daysOpenTimeSat"));
		provider.setDaysOpenTimeSun(httpservletrequest.getParameterValues("daysOpenTimeSun"));

		provider.setDaysCloseTimeMon(httpservletrequest.getParameterValues("daysCloseTimeMon"));
		provider.setDaysCloseTimeTue(httpservletrequest.getParameterValues("daysCloseTimeTue"));
		provider.setDaysCloseTimeWed(httpservletrequest.getParameterValues("daysCloseTimeWed"));
		provider.setDaysCloseTimeThu(httpservletrequest.getParameterValues("daysCloseTimeThu"));
		provider.setDaysCloseTimeFri(httpservletrequest.getParameterValues("daysCloseTimeFri"));
		provider.setDaysCloseTimeSat(httpservletrequest.getParameterValues("daysCloseTimeSat"));
		provider.setDaysCloseTimeSun(httpservletrequest.getParameterValues("daysCloseTimeSun"));

		provider.setPubicTrans2(initialNullValues(httpservletrequest.getParameter("pubicTrans2")));
		provider.setHandicapAccess2(initialNullValues(httpservletrequest.getParameter("handicapAccess2")));
		provider.setEveningHrs2(initialNullValues(httpservletrequest.getParameter("eveningHrs2")));

		provider.setPubicTrans3(initialNullValues(httpservletrequest.getParameter("pubicTrans3")));
		provider.setHandicapAccess3(initialNullValues(httpservletrequest.getParameter("handicapAccess3")));
		provider.setEveningHrs3(initialNullValues(httpservletrequest.getParameter("eveningHrs3")));

		provider.setPubicTrans4(initialNullValues(httpservletrequest.getParameter("pubicTrans4")));
		provider.setHandicapAccess4(initialNullValues(httpservletrequest.getParameter("handicapAccess4")));
		provider.setEveningHrs4(initialNullValues(httpservletrequest.getParameter("eveningHrs4")));

		provider.setPubicTrans5(initialNullValues(httpservletrequest.getParameter("pubicTrans5")));
		provider.setHandicapAccess5(initialNullValues(httpservletrequest.getParameter("handicapAccess5")));
		provider.setEveningHrs5(initialNullValues(httpservletrequest.getParameter("eveningHrs5")));

		provider.setPubicTrans6(initialNullValues(httpservletrequest.getParameter("pubicTrans6")));
		provider.setHandicapAccess6(initialNullValues(httpservletrequest.getParameter("handicapAccess6")));
		provider.setEveningHrs6(initialNullValues(httpservletrequest.getParameter("eveningHrs6")));

		setAddresses(provider, httpservletrequest);

		// Changes for the state mandate on 02/10/10 start
		provider.setMedicaidIndicator(getSelectedValue(httpservletrequest, "medicaidIndicator"));
		provider.setMedicaidPCP(getSelectedValue(httpservletrequest, "medicaidPCP"));
		provider.setMedicaidMaxPanel(httpservletrequest.getParameter("medicaidMaxPanel"));
		provider.setMedicaidSpecialist(getSelectedValue(httpservletrequest, "medicaidSpecialist"));
		if (getSelectedValue(httpservletrequest, "medicaidBoth").equals("Y")) {
			provider.setMedicaidPCP("Y");
			provider.setMedicaidSpecialist("Y");
		}
		// This logic is to auto select the medicaid indicator if either pcp or
		// specialist is selected. BTRD T1.1 Rule 11
		if (provider.getMedicaidIndicator().equals("")
				&& (!provider.getMedicaidPCP().equals("") || !provider.getMedicaidSpecialist().equals(""))) {
			provider.setMedicaidIndicator("Y");
		}

		provider.setHipIndicator(getSelectedValue(httpservletrequest, "hipIndicator"));
		provider.setHipPCP(getSelectedValue(httpservletrequest, "hipPCP"));
		provider.setHipMaxPanel(httpservletrequest.getParameter("hipMaxPanel"));
		provider.setHipSpecialist(getSelectedValue(httpservletrequest, "hipSpecialist"));
		if (getSelectedValue(httpservletrequest, "hipBoth").equals("Y")) {
			provider.setHipPCP("Y");
			provider.setHipSpecialist("Y");
		}
		if (provider.getHipIndicator().equals("")
				&& (!provider.getHipPCP().equals("") || !provider.getHipSpecialist().equals(""))) {
			provider.setHipIndicator("Y");
		}
		// provider.setSsMedical(getSelectedValue(httpservletrequest,
		// "ssMedical"));
		// System.out.println("ssMedical "+provider.getSsMedical());
		// provider.setSsDental(getSelectedValue(httpservletrequest,
		// "ssDental"));
		// provider.setSsVision(getSelectedValue(httpservletrequest,
		// "ssVision"));
		// provider.setSsOtherServType(getSelectedValue(httpservletrequest,
		// "ssOtherServType"));
		provider.setIndivPractice(getSelectedValue(httpservletrequest, "indivPractice"));
		provider.setGroupPractice(getSelectedValue(httpservletrequest, "groupPractice"));
		provider.setSchoolBasedClinic(getSelectedValue(httpservletrequest, "schoolBasedClinic"));
		provider.setTribalHealthCenter(getSelectedValue(httpservletrequest, "tribalHealthCenter"));
		provider.setRuralHealthClinic(getSelectedValue(httpservletrequest, "ruralHealthClinic"));
		provider.setFedQualHealthClinic(getSelectedValue(httpservletrequest, "fedQualHealthClinic"));
		provider.setCommunityHealthCenter(getSelectedValue(httpservletrequest, "communityHealthCenter"));
		provider.setDeptOfHealth(getSelectedValue(httpservletrequest, "deptOfHealth"));
		provider.setOtherPractice(getSelectedValue(httpservletrequest, "otherPractice"));
		provider.setRadHospBased(getSelectedValue(httpservletrequest, "radHospBased"));
		provider.setRadFreeStandingCenter(getSelectedValue(httpservletrequest, "radFreeStandingCenter"));

		provider.setApHospitalName(httpservletrequest.getParameterValues("apHospitalName"));
		provider.setApHospitalAddress(httpservletrequest.getParameterValues("apHospitalAddress"));
		provider.setApHospitalCity(httpservletrequest.getParameterValues("apHospitalCity"));
		provider.setApHospitalState(httpservletrequest.getParameterValues("apHospitalState"));
		provider.setApHospitalZip(httpservletrequest.getParameterValues("apHospitalZip"));
		provider.setDpHospitalName(httpservletrequest.getParameterValues("dpHospitalName"));
		provider.setDpHospitalAddress(httpservletrequest.getParameterValues("dpHospitalAddress"));
		provider.setDpHospitalCity(httpservletrequest.getParameterValues("dpHospitalCity"));
		provider.setDpHospitalState(httpservletrequest.getParameterValues("dpHospitalState"));
		provider.setDpHospitalZip(httpservletrequest.getParameterValues("dpHospitalZip"));
		// Changes for the state mandate on 02/10/10 end

		provider.setMgdCareDisenroll(initialNullValues(httpservletrequest.getParameter("mgdCareDisenroll")));
		provider.setIhcpProvNo(httpservletrequest.getParameter("ihcpProvNo"));
		provider.setPmp(initialNullValues(httpservletrequest.getParameter("pmp")));
		provider.setPmpSpecialty(httpservletrequest.getParameter("pmpSpecialty"));
		provider.setHospAdmitPriv(initialNullValues(httpservletrequest.getParameter("hospAdmitPriv")));
		provider.setRelationshipPriv(initialNullValues(httpservletrequest.getParameter("relationshipPriv")));
		provider.setDeliveryPriv(initialNullValues(httpservletrequest.getParameter("deliveryPriv")));
		provider.setAgeRestriction(httpservletrequest.getParameter("ageRestriction"));
		provider.setPmpScopeOb(initialNullValues(httpservletrequest.getParameter("pmpScopeOb")));
		provider.setPmpScopeAll(initialNullValues(httpservletrequest.getParameter("pmpScopeAll")));
		provider.setGenderScope(initialNullValues(httpservletrequest.getParameter("genderScope")));
		provider.setMedPanelStatus(initialNullValues(httpservletrequest.getParameter("medPanelStatus")));
		provider.setHipPanelStatus(initialNullValues(httpservletrequest.getParameter("hipPanelStatus")));
		provider.setMedNbrLocations(httpservletrequest.getParameter("medNbrLocations"));
		provider.setHipNbrLocations(httpservletrequest.getParameter("hipNbrLocations"));
		provider.setMedPldPanelDecrease(initialNullValues(httpservletrequest.getParameter("medPldPanelDecrease")));
		provider.setHipPldPanelDecrease(initialNullValues(httpservletrequest.getParameter("hipPldPanelDecrease")));
		provider.setMedPldPlacePanelAt(httpservletrequest.getParameter("medPldPlacePanelAt"));
		provider.setHipPldPlacePanelAt(httpservletrequest.getParameter("hipPldPlacePanelAt"));
		provider.setMedPldGrpMedicaidNo(httpservletrequest.getParameter("medPldGrpMedicaidNo"));
		provider.setHipPldGrpMedicaidNo(httpservletrequest.getParameter("hipPldGrpMedicaidNo"));
		provider.setMedPliPanelIncrease(initialNullValues(httpservletrequest.getParameter("medPliPanelIncrease")));
		provider.setHipPliPanelIncrease(initialNullValues(httpservletrequest.getParameter("hipPliPanelIncrease")));
		provider.setMedPliPlacePanelAt(httpservletrequest.getParameter("medPliPlacePanelAt"));
		provider.setHipPliPlacePanelAt(httpservletrequest.getParameter("hipPliPlacePanelAt"));
		provider.setMedPliGrpMedicaidNbr(httpservletrequest.getParameter("medPliGrpMedicaidNbr"));
		provider.setHipPliGrpMedicaidNbr(httpservletrequest.getParameter("hipPliGrpMedicaidNbr"));
		provider.setMedPanelHold(initialNullValues(httpservletrequest.getParameter("medPanelHold")));
		provider.setHipPanelHold(initialNullValues(httpservletrequest.getParameter("hipPanelHold")));

		provider.setMedPlrPanelHold(httpservletrequest.getParameter("medPlrPanelHold"));
		provider.setHipPlrPanelHold(httpservletrequest.getParameter("hipPlrPanelHold"));

		provider.setMedPanelHoldRemove(initialNullValues(httpservletrequest.getParameter("medPanelHoldRemove")));
		provider.setHipPanelHoldRemove(initialNullValues(httpservletrequest.getParameter("hipPanelHoldRemove")));

		provider.setMedPlrPanelHoldRemove(httpservletrequest.getParameter("medPlrPanelHoldRemove"));
		provider.setHipPlrPanelHoldRemove(httpservletrequest.getParameter("hipPlrPanelHoldRemove"));

		provider.setDeaNo(httpservletrequest.getParameter("deaNo"));
		provider.setCsrNo(httpservletrequest.getParameter("csrNo"));
		provider.setEnrollAs(initialNullValues(httpservletrequest.getParameter("enrollas")));
		provider.setEnrollClinicType(httpservletrequest.getParameter("enrollClinicType"));
		provider.setLocationType(initialNullValues(httpservletrequest.getParameter("locationType")));
		provider.setNpPractice(getSelectedValue(httpservletrequest, "npPractice"));
		provider.setPaPractice(getSelectedValue(httpservletrequest, "paPractice"));
		provider.setNaPractice(getSelectedValue(httpservletrequest, "naPractice"));
		provider.setStateLicIssueDt(httpservletrequest.getParameter("stateLicIssueDt"));
		provider.setStateLicExpDt(httpservletrequest.getParameter("stateLicExpDt"));
		provider.setProfLiabilityCarrierName(httpservletrequest.getParameter("profLiabCarrier"));
		provider.setProfLiabilityCarrierLimit(httpservletrequest.getParameter("profLiabCovgLimit"));
		provider.setProfLiabilityPolicyNo(httpservletrequest.getParameter("profLiabPolicy"));
		provider.setProfLiabilityExpDate(httpservletrequest.getParameter("profLiabExtDt"));
		provider.setMalPracInsRevoke(initialNullValues(httpservletrequest.getParameter("malPracInsRevoke")));
		provider.setUnderGovInvestigation(initialNullValues(httpservletrequest.getParameter("underGovInvestigation")));
		provider.setExpellMedPay(initialNullValues(httpservletrequest.getParameter("expellMedPay")));
		provider.setConfProvAgreement(getSelectedValue(httpservletrequest, "confProvAgreement"));
		provider.setConfW2(initialNullValues(httpservletrequest.getParameter("confW2")));
		// Section K
		provider.setUploadFileName(httpservletrequest.getParameter("uploadFileName"));
		provider.setUploadFileSize(httpservletrequest.getParameter("uploadFileSize"));
		provider.setUploadedFileComment(initialNullValues(httpservletrequest.getParameter("uploadedFileComment")));
		provider.setUploadDocComment(initialNullValues(httpservletrequest.getParameter("uploadDocComment")));
		provider.setDltRowCounter(initialNullValues(httpservletrequest.getParameter("dltRowCounter")));
		provider.setFileListSize(initialNullValues(httpservletrequest.getParameter("fileListSize")));

		/* PMF Section Changes -- AD21239 -- */
		provider.setPaSupSpec(getSelectedValue(httpservletrequest, "paSupSpec"));
		provider.setPaSupPMP(getSelectedValue(httpservletrequest, "paSupPMP"));
		provider.setW2Comments(initialNullValues(httpservletrequest.getParameter("w2Comments")));
		/* PMF Section Changes -- AD21239 -- */

		setUploadedFileData(httpservletrequest, provider);
	}

	private static void setUploadedFileData(HttpServletRequest httpservletrequest, Provider provider) {
		String[] uploadFileComment = null;
		String[] uploadFileNames = null;
		String[] uploadFileSize = null;
		UploadDocListBean UploadDocListBean = null;
		List<UploadDocListBean> fileList = null;
		try {
			fileList = new ArrayList<UploadDocListBean>();
			if (httpservletrequest.getParameter("uploadFileName") != null) {
				uploadFileNames = httpservletrequest.getParameter("uploadFileName").split("::");
				uploadFileSize = httpservletrequest.getParameter("uploadFileSize").split("::");
				if (httpservletrequest.getParameter("uploadedFileComment") != null) {
					uploadFileComment = httpservletrequest.getParameter("uploadedFileComment").split("::");
				}
				for (int fileCount = 0; fileCount < uploadFileNames.length; fileCount++) {
					if (!uploadFileNames[fileCount].equals("")) {
						UploadDocListBean = new UploadDocListBean();
						UploadDocListBean.setFileName(uploadFileNames[fileCount]);
						UploadDocListBean.setFileSize(uploadFileSize[fileCount]);
						UploadDocListBean.setDocComment(uploadFileComment[fileCount]);
						fileList.add(UploadDocListBean);
					}
				}
				provider.setUploadFileListDtls(fileList);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
	}

	public static void setProviderDataForMultiPart(Provider provider, MultipartFormDataRequest httpservletrequest) {
		provider.setTaxID(httpservletrequest.getParameter("taxID"));

		provider.setAnthemPIN(httpservletrequest.getParameter("anthemPIN"));
		// WI/MO changes
		provider.setMOProviderId(httpservletrequest.getParameter("moId"));
		provider.setWIProviderId(httpservletrequest.getParameter("wiId"));
		/*
		 * 2013 SSCR 13503 change
		 * provider.setGrpMedicaidId(httpservletrequest.getParameter
		 * ("grpmedicaidId"));
		 */
		provider.setGrpNPINumber(httpservletrequest.getParameter("grpNpiNumber"));
		provider.setProvNPINumber(httpservletrequest.getParameter("provNpiNumber"));
		/*
		 * 2013 SSCR 13503 changes
		 * provider.setTaxonomyNumber(httpservletrequest.
		 * getParameter("taxonomyNum"));
		 */

		provider.setSoloGroup(initialNullValues(httpservletrequest.getParameter("soloGroup")));

		provider.setNumberInGroup(httpservletrequest.getParameter("numberInGroup"));
		provider.setRapidUpdate(getSelectedValueForMultipart(httpservletrequest, "rapidUpdate"));
		provider.setEffectiveDate(httpservletrequest.getParameter("effectiveDate"));
		provider.setAddProvider(getSelectedValueForMultipart(httpservletrequest, "addProvider"));
		provider.setDelProvider(getSelectedValueForMultipart(httpservletrequest, "delProvider"));
		provider.setAddLocation(getSelectedValueForMultipart(httpservletrequest, "addLocation"));
		provider.setAddProvToLocation(getSelectedValueForMultipart(httpservletrequest, "addProvToLocation"));
		provider.setChgSpecialty(getSelectedValueForMultipart(httpservletrequest, "chgSpecialty"));
		provider.setChgProvName(getSelectedValueForMultipart(httpservletrequest, "chgProvName"));
		provider.setDelLocation(getSelectedValueForMultipart(httpservletrequest, "delLocation"));
		provider.setDelProvFromLocation(getSelectedValueForMultipart(httpservletrequest, "delProvFromLocation"));
		provider.setChgPracName(getSelectedValueForMultipart(httpservletrequest, "chgPracName"));
		provider.setChgPracAddress(getSelectedValueForMultipart(httpservletrequest, "chgPracAddress"));
		provider.setChgPracPhone(getSelectedValueForMultipart(httpservletrequest, "chgPracPhone"));
		provider.setChgTaxID(getSelectedValueForMultipart(httpservletrequest, "chgTaxID"));
		provider.setChgBillName(getSelectedValueForMultipart(httpservletrequest, "chgBillName"));
		provider.setChgBillAddress(getSelectedValueForMultipart(httpservletrequest, "chgBillAddress"));
		provider.setChgBillPhone(getSelectedValueForMultipart(httpservletrequest, "chgBillPhone"));
		provider.setChgOfficeHours(getSelectedValueForMultipart(httpservletrequest, "chgOfficeHours"));
		provider.setAddNPI(getSelectedValueForMultipart(httpservletrequest, "addNPI"));
		provider.setChgNPI(getSelectedValueForMultipart(httpservletrequest, "chgNPI"));
		provider.setEditAreasOfExpertise(getSelectedValueForMultipart(httpservletrequest, "addAreasOfExpertise"));
		provider.setEditPatientInfo(getSelectedValueForMultipart(httpservletrequest, "addPatientInfo"));
		provider.setProvDir2(initialNullValues(httpservletrequest.getParameter("provDir2")));

		if (httpservletrequest.getParameter("delReason").length() > 200)
			provider.setDelReason(httpservletrequest.getParameter("delReason").substring(0, 200));
		else
			provider.setDelReason(httpservletrequest.getParameter("delReason"));
		if (httpservletrequest.getParameter("comments").length() > 750)
			provider.setComments(httpservletrequest.getParameter("comments").substring(0, 750));
		else
			provider.setComments(httpservletrequest.getParameter("comments"));
		provider.setConfirmation(getSelectedValueForMultipart(httpservletrequest, "confirmation"));
		provider.setOldTaxID(httpservletrequest.getParameter("oldTaxID"));
		provider.setProvFnm(httpservletrequest.getParameter("provFnm"));
		provider.setProvMI(httpservletrequest.getParameter("provMI"));
		provider.setProvLnm(httpservletrequest.getParameter("provLnm"));
		provider.setTitle(httpservletrequest.getParameter("title"));
		provider.setSpecialityInfo(initialNullValues(httpservletrequest.getParameter("specialityInfo")));
		if ((provider.getSpecialityInfo()).equalsIgnoreCase("primSpecialtyPhy")) {
			provider.setPrimSpecialtyPhy("primSpecialtyPhy");
		}
		if ((provider.getSpecialityInfo()).equalsIgnoreCase("specialtyCarePhy")) {
			provider.setSpecialtyCarePhy("specialtyCarePhy");
		}
		if ((provider.getSpecialityInfo()).equalsIgnoreCase("other")) {
			provider.setOther("other");
		}
		/* WLPRD00971518 | Fix for Clear Selection | UST Global | 2014 October */
		if ((provider.getSpecialityInfo()).equalsIgnoreCase(" ")) {
			provider.setClearSelection(" ");
		}
		provider.setSpecialty(initialNullValues(httpservletrequest.getParameter("specialty")));

		provider.setIndServTypes(initialNullValues(httpservletrequest.getParameter("indServTypes")));

		if (provider.getIndServTypes().equalsIgnoreCase("ssMedical")) {
			provider.setSsMedical("Y");
		}
		if (provider.getIndServTypes().equalsIgnoreCase("ssDental")) {
			provider.setSsDental("Y");
		}
		if (provider.getIndServTypes().equalsIgnoreCase("ssVision")) {
			provider.setSsVision("Y");
		}
		if (provider.getIndServTypes().equalsIgnoreCase("other")) {
			provider.setSsOtherServType("Y");
		}
		/*
		 * System.out.println("Before setting specialty info");
		 * System.out.println
		 * ("Before setting specialty info @"+httpservletrequest
		 * .getParameter("specialtyInfo")+"@");
		 * provider.setSpecialtyInfo(initialNullValues
		 * (httpservletrequest.getParameter("specialtyInfo")));
		 */
		// change for section c 13/6/2012
		// provider.setClearSelection(httpservletrequest.getParameter("clearSelection"));
		provider.setMedicarePartTrad(initialNullValues(httpservletrequest.getParameter("medicarePartTrad")));
		provider.setMedicareApplDt(httpservletrequest.getParameter("medicareApplDt"));
		provider.setMedicareOptOut(initialNullValues(httpservletrequest.getParameter("medicareOptOut")));
		provider.setMedicareOptOutDt(httpservletrequest.getParameter("medicareOptOutDt"));

		provider.setNpSupSpec(getSelectedValueForMultipart(httpservletrequest, "npSupSpec"));
		provider.setNpSupPMP(getSelectedValueForMultipart(httpservletrequest, "npSupPMP"));
		provider.setRetailHealthClinic(getSelectedValueForMultipart(httpservletrequest, "retailHealthClinic"));
		provider.setWalkInDrOffice(getSelectedValueForMultipart(httpservletrequest, "walkInDrOffice"));
		provider.setUrgentCare(getSelectedValueForMultipart(httpservletrequest, "urgentCare"));
		provider.setCoveringPMP(getSelectedValueForMultipart(httpservletrequest, "coveringPMP"));
		provider.setCertMidwife(getSelectedValueForMultipart(httpservletrequest, "certMidwife"));
		provider.setPrenatelCareCoord(getSelectedValueForMultipart(httpservletrequest, "prenatelCareCoord"));
		provider.setTribalHealthCtr(getSelectedValueForMultipart(httpservletrequest, "tribalHealthCtr"));
		provider.setClinic(getSelectedValueForMultipart(httpservletrequest, "clinic"));
		provider.setMedicationAssistedTreatment(getSelectedValueForMultipart(httpservletrequest, "medicationAssistedTreatment"));
		provider.setResidentialTreatmentCenter(getSelectedValueForMultipart(httpservletrequest, "residentialTreatmentCenter"));
		provider.setSubstanceUseDisorderAdults(getSelectedValueForMultipart(httpservletrequest, "substanceUseDisorderAdults"));
		provider.setSubstanceUseDisorderChild(getSelectedValueForMultipart(httpservletrequest, "substanceUseDisorderChild"));
		provider.setTelehealthProv(getSelectedValueForMultipart(httpservletrequest, "telehealthProv"));
		provider.setFwdHealthCertNPI1(httpservletrequest.getParameter("fwdHealthCertNPI1"));
		provider.setFwdHealthCertNPIBilled1(initialNullValues(httpservletrequest
				.getParameter("fwdHealthCertNPIBilled1")));
		provider.setFwdHealthCertEffDt1(httpservletrequest.getParameter("fwdHealthCertEffDt1"));
		provider.setFwdHealthRecertEffDt1(httpservletrequest.getParameter("fwdHealthRecertEffDt1"));
		provider.setFwdHealthCertNPI2(httpservletrequest.getParameter("fwdHealthCertNPI2"));
		provider.setFwdHealthCertNPIBilled2(initialNullValues(httpservletrequest
				.getParameter("fwdHealthCertNPIBilled2")));
		provider.setFwdHealthCertEffDt2(httpservletrequest.getParameter("fwdHealthCertEffDt2"));
		provider.setFwdHealthRecertEffDt2(httpservletrequest.getParameter("fwdHealthRecertEffDt2"));
		provider.setFwdHealthCertNPI3(httpservletrequest.getParameter("fwdHealthCertNPI3"));
		provider.setFwdHealthCertNPIBilled3(initialNullValues(httpservletrequest
				.getParameter("fwdHealthCertNPIBilled3")));
		provider.setFwdHealthCertEffDt3(httpservletrequest.getParameter("fwdHealthCertEffDt3"));
		provider.setFwdHealthRecertEffDt3(httpservletrequest.getParameter("fwdHealthRecertEffDt3"));
		// change for section c 13/6/2012

		provider.setBoardCertified(initialNullValues(httpservletrequest.getParameter("boardCertified")));
		provider.setCertExamDT(httpservletrequest.getParameter("certExamDT"));
		provider.setProvSSN(httpservletrequest.getParameter("provNumber"));
		provider.setUpinNumber(httpservletrequest.getParameter("upinNumber"));
		provider.setProfLicenseNumber(httpservletrequest.getParameter("profLicenseNumber"));
		provider.setCaqhIDNumber(httpservletrequest.getParameter("caqhIDNumber"));
		provider.setCaqhExplanation(httpservletrequest.getParameter("caqhExplanation"));
		provider.setProvDOB(httpservletrequest.getParameter("provDOB"));
		provider.setProvGender(initialNullValues(httpservletrequest.getParameter("provGender")));
		provider.setNewPatients(initialNullValues(httpservletrequest.getParameter("newPatients")));
		provider.setAgeLimitMin(httpservletrequest.getParameter("ageLimitMin"));
		provider.setAgeLimitMax(httpservletrequest.getParameter("ageLimitMax"));

		provider.setEthnicOrigin(initialNullValues(httpservletrequest.getParameter("ethnicOrigin")));

		provider.setPracName1(httpservletrequest.getParameter("pracName1"));
		provider.setBillMedicareGroup6(httpservletrequest.getParameter("billMedicareGroup6"));
		provider.setBillMedicareIndividual6(httpservletrequest.getParameter("billMedicareIndividual6"));
		provider.setGrpEntityName1(httpservletrequest.getParameter("grpEntityName1"));
		provider.setSpecialty1(httpservletrequest.getParameter("specialty1"));
		provider.setTaxID1(httpservletrequest.getParameter("taxID1"));
		provider.setEffectiveDate1(httpservletrequest.getParameter("effectiveDate1"));
		provider.setGrpEntityName2(httpservletrequest.getParameter("grpEntityName2"));
		provider.setSpecialty2(httpservletrequest.getParameter("specialty2"));
		provider.setTaxID2(httpservletrequest.getParameter("taxID2"));
		provider.setEffectiveDate2(httpservletrequest.getParameter("effectiveDate2"));
		provider.setGrpEntityName3(httpservletrequest.getParameter("grpEntityName3"));
		provider.setSpecialty3(httpservletrequest.getParameter("specialty3"));
		provider.setTaxID3(httpservletrequest.getParameter("taxID3"));
		provider.setEffectiveDate3(httpservletrequest.getParameter("effectiveDate3"));
		provider.setGrpEntityName4(httpservletrequest.getParameter("grpEntityName4"));
		provider.setSpecialty4(httpservletrequest.getParameter("specialty4"));
		provider.setTaxID4(httpservletrequest.getParameter("taxID4"));
		provider.setEffectiveDate4(httpservletrequest.getParameter("effectiveDate4"));
		provider.setGrpEntityName5(httpservletrequest.getParameter("grpEntityName5"));
		provider.setSpecialty5(httpservletrequest.getParameter("specialty5"));
		provider.setTaxID5(httpservletrequest.getParameter("taxID5"));
		provider.setEffectiveDate5(httpservletrequest.getParameter("effectiveDate5"));

		provider.setCpHospitalAddress(httpservletrequest.getParameterValues("cpHospitalAddress"));
		provider.setCpHospitalCity(httpservletrequest.getParameterValues("cpHospitalCity"));
		provider.setCpHospitalState(httpservletrequest.getParameterValues("cpHospitalState"));
		provider.setCpHospitalZip(httpservletrequest.getParameterValues("cpHospitalZip"));

		provider.setPubicTrans1(initialNullValues(httpservletrequest.getParameter("pubicTrans1")));
		provider.setHandicapAccess1(initialNullValues(httpservletrequest.getParameter("handicapAccess1")));
		// 2013 SSCR 13503 change
		provider.setProvDir1(initialNullValues(httpservletrequest.getParameter("provDir1")));
		provider.setProvDir2(initialNullValues(httpservletrequest.getParameter("provDir2")));
		provider.setProvDir3(initialNullValues(httpservletrequest.getParameter("provDir3")));
		provider.setProvDir4(initialNullValues(httpservletrequest.getParameter("provDir4")));
		provider.setProvDir5(initialNullValues(httpservletrequest.getParameter("provDir5")));
		provider.setProvDir6(initialNullValues(httpservletrequest.getParameter("provDir6")));
		// 2013 SSCR 13503change end

		// System.out.println("test provdir "+initialNullValues(httpservletrequest.getParameter("provDir1")));
		provider.setEveningHrs1(initialNullValues(httpservletrequest.getParameter("eveningHrs1")));

		provider.setTimeZoneOfficeHrs(httpservletrequest.getParameterValues("timeZoneOfficeHrs"));

		provider.setDaysOpenMon0(getSelectedValueForMultipart(httpservletrequest, "daysOpenMon0"));
		provider.setDaysOpenTue0(getSelectedValueForMultipart(httpservletrequest, "daysOpenTue0"));
		provider.setDaysOpenWed0(getSelectedValueForMultipart(httpservletrequest, "daysOpenWed0"));
		provider.setDaysOpenThu0(getSelectedValueForMultipart(httpservletrequest, "daysOpenThu0"));
		provider.setDaysOpenFri0(getSelectedValueForMultipart(httpservletrequest, "daysOpenFri0"));
		provider.setDaysOpenSat0(getSelectedValueForMultipart(httpservletrequest, "daysOpenSat0"));
		provider.setDaysOpenSun0(getSelectedValueForMultipart(httpservletrequest, "daysOpenSun0"));

		provider.setDaysOpenMon1(getSelectedValueForMultipart(httpservletrequest, "daysOpenMon1"));
		provider.setDaysOpenTue1(getSelectedValueForMultipart(httpservletrequest, "daysOpenTue1"));
		provider.setDaysOpenWed1(getSelectedValueForMultipart(httpservletrequest, "daysOpenWed1"));
		provider.setDaysOpenThu1(getSelectedValueForMultipart(httpservletrequest, "daysOpenThu1"));
		provider.setDaysOpenFri1(getSelectedValueForMultipart(httpservletrequest, "daysOpenFri1"));
		provider.setDaysOpenSat1(getSelectedValueForMultipart(httpservletrequest, "daysOpenSat1"));
		provider.setDaysOpenSun1(getSelectedValueForMultipart(httpservletrequest, "daysOpenSun1"));

		provider.setDaysOpenMon2(getSelectedValueForMultipart(httpservletrequest, "daysOpenMon2"));
		provider.setDaysOpenTue2(getSelectedValueForMultipart(httpservletrequest, "daysOpenTue2"));
		provider.setDaysOpenWed2(getSelectedValueForMultipart(httpservletrequest, "daysOpenWed2"));
		provider.setDaysOpenThu2(getSelectedValueForMultipart(httpservletrequest, "daysOpenThu2"));
		provider.setDaysOpenFri2(getSelectedValueForMultipart(httpservletrequest, "daysOpenFri2"));
		provider.setDaysOpenSat2(getSelectedValueForMultipart(httpservletrequest, "daysOpenSat2"));
		provider.setDaysOpenSun2(getSelectedValueForMultipart(httpservletrequest, "daysOpenSun2"));

		provider.setDaysOpenMon3(getSelectedValueForMultipart(httpservletrequest, "daysOpenMon3"));
		provider.setDaysOpenTue3(getSelectedValueForMultipart(httpservletrequest, "daysOpenTue3"));
		provider.setDaysOpenWed3(getSelectedValueForMultipart(httpservletrequest, "daysOpenWed3"));
		provider.setDaysOpenThu3(getSelectedValueForMultipart(httpservletrequest, "daysOpenThu3"));
		provider.setDaysOpenFri3(getSelectedValueForMultipart(httpservletrequest, "daysOpenFri3"));
		provider.setDaysOpenSat3(getSelectedValueForMultipart(httpservletrequest, "daysOpenSat3"));
		provider.setDaysOpenSun3(getSelectedValueForMultipart(httpservletrequest, "daysOpenSun3"));

		provider.setDaysOpenMon4(getSelectedValueForMultipart(httpservletrequest, "daysOpenMon4"));
		provider.setDaysOpenTue4(getSelectedValueForMultipart(httpservletrequest, "daysOpenTue4"));
		provider.setDaysOpenWed4(getSelectedValueForMultipart(httpservletrequest, "daysOpenWed4"));
		provider.setDaysOpenThu4(getSelectedValueForMultipart(httpservletrequest, "daysOpenThu4"));
		provider.setDaysOpenFri4(getSelectedValueForMultipart(httpservletrequest, "daysOpenFri4"));
		provider.setDaysOpenSat4(getSelectedValueForMultipart(httpservletrequest, "daysOpenSat4"));
		provider.setDaysOpenSun4(getSelectedValueForMultipart(httpservletrequest, "daysOpenSun4"));

		provider.setDaysOpenMon5(getSelectedValueForMultipart(httpservletrequest, "daysOpenMon5"));
		provider.setDaysOpenTue5(getSelectedValueForMultipart(httpservletrequest, "daysOpenTue5"));
		provider.setDaysOpenWed5(getSelectedValueForMultipart(httpservletrequest, "daysOpenWed5"));
		provider.setDaysOpenThu5(getSelectedValueForMultipart(httpservletrequest, "daysOpenThu5"));
		provider.setDaysOpenFri5(getSelectedValueForMultipart(httpservletrequest, "daysOpenFri5"));
		provider.setDaysOpenSat5(getSelectedValueForMultipart(httpservletrequest, "daysOpenSat5"));
		provider.setDaysOpenSun5(getSelectedValueForMultipart(httpservletrequest, "daysOpenSun5"));

		provider.setHipShow(httpservletrequest.getParameter("hipShow"));
		provider.setDaysOpenTimeMon(httpservletrequest.getParameterValues("daysOpenTimeMon"));
		provider.setDaysOpenTimeTue(httpservletrequest.getParameterValues("daysOpenTimeTue"));
		provider.setDaysOpenTimeWed(httpservletrequest.getParameterValues("daysOpenTimeWed"));
		provider.setDaysOpenTimeThu(httpservletrequest.getParameterValues("daysOpenTimeThu"));
		provider.setDaysOpenTimeFri(httpservletrequest.getParameterValues("daysOpenTimeFri"));
		provider.setDaysOpenTimeSat(httpservletrequest.getParameterValues("daysOpenTimeSat"));
		provider.setDaysOpenTimeSun(httpservletrequest.getParameterValues("daysOpenTimeSun"));

		provider.setDaysCloseTimeMon(httpservletrequest.getParameterValues("daysCloseTimeMon"));
		provider.setDaysCloseTimeTue(httpservletrequest.getParameterValues("daysCloseTimeTue"));
		provider.setDaysCloseTimeWed(httpservletrequest.getParameterValues("daysCloseTimeWed"));
		provider.setDaysCloseTimeThu(httpservletrequest.getParameterValues("daysCloseTimeThu"));
		provider.setDaysCloseTimeFri(httpservletrequest.getParameterValues("daysCloseTimeFri"));
		provider.setDaysCloseTimeSat(httpservletrequest.getParameterValues("daysCloseTimeSat"));
		provider.setDaysCloseTimeSun(httpservletrequest.getParameterValues("daysCloseTimeSun"));

		provider.setPubicTrans2(initialNullValues(httpservletrequest.getParameter("pubicTrans2")));
		provider.setHandicapAccess2(initialNullValues(httpservletrequest.getParameter("handicapAccess2")));
		provider.setEveningHrs2(initialNullValues(httpservletrequest.getParameter("eveningHrs2")));

		provider.setPubicTrans3(initialNullValues(httpservletrequest.getParameter("pubicTrans3")));
		provider.setHandicapAccess3(initialNullValues(httpservletrequest.getParameter("handicapAccess3")));
		provider.setEveningHrs3(initialNullValues(httpservletrequest.getParameter("eveningHrs3")));

		provider.setPubicTrans4(initialNullValues(httpservletrequest.getParameter("pubicTrans4")));
		provider.setHandicapAccess4(initialNullValues(httpservletrequest.getParameter("handicapAccess4")));
		provider.setEveningHrs4(initialNullValues(httpservletrequest.getParameter("eveningHrs4")));

		provider.setPubicTrans5(initialNullValues(httpservletrequest.getParameter("pubicTrans5")));
		provider.setHandicapAccess5(initialNullValues(httpservletrequest.getParameter("handicapAccess5")));
		provider.setEveningHrs5(initialNullValues(httpservletrequest.getParameter("eveningHrs5")));

		provider.setPubicTrans6(initialNullValues(httpservletrequest.getParameter("pubicTrans6")));
		provider.setHandicapAccess6(initialNullValues(httpservletrequest.getParameter("handicapAccess6")));
		provider.setEveningHrs6(initialNullValues(httpservletrequest.getParameter("eveningHrs6")));

		/* PMF Section C Changes -- AD21239 -- */
		provider.setPaSupSpec(getSelectedValueForMultipart(httpservletrequest, "paSupSpec"));
		provider.setPaSupPMP(getSelectedValueForMultipart(httpservletrequest, "paSupPMP"));

		setAddressesForMultiPart(provider, httpservletrequest);

		// Changes for the state mandate on 02/10/10 start
		provider.setMedicaidIndicator(getSelectedValueForMultipart(httpservletrequest, "medicaidIndicator"));
		provider.setMedicaidPCP(getSelectedValueForMultipart(httpservletrequest, "medicaidPCP"));
		provider.setMedicaidMaxPanel(httpservletrequest.getParameter("medicaidMaxPanel"));
		provider.setMedicaidSpecialist(getSelectedValueForMultipart(httpservletrequest, "medicaidSpecialist"));
		if (getSelectedValueForMultipart(httpservletrequest, "medicaidBoth").equals("Y")) {
			provider.setMedicaidPCP("Y");
			provider.setMedicaidSpecialist("Y");
		}
		// This logic is to auto select the medicaid indicator if either pcp or
		// specialist is selected. BTRD T1.1 Rule 11
		if (provider.getMedicaidIndicator().equals("")
				&& (!provider.getMedicaidPCP().equals("") || !provider.getMedicaidSpecialist().equals(""))) {
			provider.setMedicaidIndicator("Y");
		}

		provider.setHipIndicator(getSelectedValueForMultipart(httpservletrequest, "hipIndicator"));
		provider.setHipPCP(getSelectedValueForMultipart(httpservletrequest, "hipPCP"));
		provider.setHipMaxPanel(httpservletrequest.getParameter("hipMaxPanel"));
		provider.setHipSpecialist(getSelectedValueForMultipart(httpservletrequest, "hipSpecialist"));
		if (getSelectedValueForMultipart(httpservletrequest, "hipBoth").equals("Y")) {
			provider.setHipPCP("Y");
			provider.setHipSpecialist("Y");
		}
		if (provider.getHipIndicator().equals("")
				&& (!provider.getHipPCP().equals("") || !provider.getHipSpecialist().equals(""))) {
			provider.setHipIndicator("Y");
		}
		// provider.setSsMedical(getSelectedValueForMultipart(httpservletrequest,
		// "ssMedical"));
		// System.out.println("ssMedical "+provider.getSsMedical());
		// provider.setSsDental(getSelectedValueForMultipart(httpservletrequest,
		// "ssDental"));
		// provider.setSsVision(getSelectedValueForMultipart(httpservletrequest,
		// "ssVision"));
		// provider.setSsOtherServType(getSelectedValueForMultipart(httpservletrequest,
		// "ssOtherServType"));
		provider.setIndivPractice(getSelectedValueForMultipart(httpservletrequest, "indivPractice"));
		provider.setGroupPractice(getSelectedValueForMultipart(httpservletrequest, "groupPractice"));
		provider.setSchoolBasedClinic(getSelectedValueForMultipart(httpservletrequest, "schoolBasedClinic"));
		provider.setTribalHealthCenter(getSelectedValueForMultipart(httpservletrequest, "tribalHealthCenter"));
		provider.setRuralHealthClinic(getSelectedValueForMultipart(httpservletrequest, "ruralHealthClinic"));
		provider.setFedQualHealthClinic(getSelectedValueForMultipart(httpservletrequest, "fedQualHealthClinic"));
		provider.setCommunityHealthCenter(getSelectedValueForMultipart(httpservletrequest, "communityHealthCenter"));
		provider.setDeptOfHealth(getSelectedValueForMultipart(httpservletrequest, "deptOfHealth"));
		provider.setOtherPractice(getSelectedValueForMultipart(httpservletrequest, "otherPractice"));
		provider.setRadHospBased(getSelectedValueForMultipart(httpservletrequest, "radHospBased"));
		provider.setRadFreeStandingCenter(getSelectedValueForMultipart(httpservletrequest, "radFreeStandingCenter"));

		provider.setApHospitalName(httpservletrequest.getParameterValues("apHospitalName"));
		provider.setApHospitalAddress(httpservletrequest.getParameterValues("apHospitalAddress"));
		provider.setApHospitalCity(httpservletrequest.getParameterValues("apHospitalCity"));
		provider.setApHospitalState(httpservletrequest.getParameterValues("apHospitalState"));
		provider.setApHospitalZip(httpservletrequest.getParameterValues("apHospitalZip"));
		provider.setDpHospitalName(httpservletrequest.getParameterValues("dpHospitalName"));
		provider.setDpHospitalAddress(httpservletrequest.getParameterValues("dpHospitalAddress"));
		provider.setDpHospitalCity(httpservletrequest.getParameterValues("dpHospitalCity"));
		provider.setDpHospitalState(httpservletrequest.getParameterValues("dpHospitalState"));
		provider.setDpHospitalZip(httpservletrequest.getParameterValues("dpHospitalZip"));
		// Changes for the state mandate on 02/10/10 end

		provider.setMgdCareDisenroll(initialNullValues(httpservletrequest.getParameter("mgdCareDisenroll")));
		provider.setIhcpProvNo(httpservletrequest.getParameter("ihcpProvNo"));
		provider.setPmp(initialNullValues(httpservletrequest.getParameter("pmp")));
		provider.setPmpSpecialty(httpservletrequest.getParameter("pmpSpecialty"));
		provider.setHospAdmitPriv(initialNullValues(httpservletrequest.getParameter("hospAdmitPriv")));
		provider.setRelationshipPriv(initialNullValues(httpservletrequest.getParameter("relationshipPriv")));
		provider.setDeliveryPriv(initialNullValues(httpservletrequest.getParameter("deliveryPriv")));
		provider.setAgeRestriction(httpservletrequest.getParameter("ageRestriction"));
		provider.setPmpScopeOb(initialNullValues(httpservletrequest.getParameter("pmpScopeOb")));
		provider.setPmpScopeAll(initialNullValues(httpservletrequest.getParameter("pmpScopeAll")));
		provider.setGenderScope(initialNullValues(httpservletrequest.getParameter("genderScope")));
		provider.setMedPanelStatus(initialNullValues(httpservletrequest.getParameter("medPanelStatus")));
		provider.setHipPanelStatus(initialNullValues(httpservletrequest.getParameter("hipPanelStatus")));
		provider.setMedNbrLocations(httpservletrequest.getParameter("medNbrLocations"));
		provider.setHipNbrLocations(httpservletrequest.getParameter("hipNbrLocations"));
		provider.setMedPldPanelDecrease(initialNullValues(httpservletrequest.getParameter("medPldPanelDecrease")));
		provider.setHipPldPanelDecrease(initialNullValues(httpservletrequest.getParameter("hipPldPanelDecrease")));
		provider.setMedPldPlacePanelAt(httpservletrequest.getParameter("medPldPlacePanelAt"));
		provider.setHipPldPlacePanelAt(httpservletrequest.getParameter("hipPldPlacePanelAt"));
		provider.setMedPldGrpMedicaidNo(httpservletrequest.getParameter("medPldGrpMedicaidNo"));
		provider.setHipPldGrpMedicaidNo(httpservletrequest.getParameter("hipPldGrpMedicaidNo"));
		provider.setMedPliPanelIncrease(initialNullValues(httpservletrequest.getParameter("medPliPanelIncrease")));
		provider.setHipPliPanelIncrease(initialNullValues(httpservletrequest.getParameter("hipPliPanelIncrease")));
		provider.setMedPliPlacePanelAt(httpservletrequest.getParameter("medPliPlacePanelAt"));
		provider.setHipPliPlacePanelAt(httpservletrequest.getParameter("hipPliPlacePanelAt"));
		provider.setMedPliGrpMedicaidNbr(httpservletrequest.getParameter("medPliGrpMedicaidNbr"));
		provider.setHipPliGrpMedicaidNbr(httpservletrequest.getParameter("hipPliGrpMedicaidNbr"));
		provider.setMedPanelHold(initialNullValues(httpservletrequest.getParameter("medPanelHold")));
		provider.setHipPanelHold(initialNullValues(httpservletrequest.getParameter("hipPanelHold")));

		provider.setMedPlrPanelHold(httpservletrequest.getParameter("medPlrPanelHold"));
		provider.setHipPlrPanelHold(httpservletrequest.getParameter("hipPlrPanelHold"));

		provider.setMedPanelHoldRemove(initialNullValues(httpservletrequest.getParameter("medPanelHoldRemove")));
		provider.setHipPanelHoldRemove(initialNullValues(httpservletrequest.getParameter("hipPanelHoldRemove")));

		provider.setMedPlrPanelHoldRemove(httpservletrequest.getParameter("medPlrPanelHoldRemove"));
		provider.setHipPlrPanelHoldRemove(httpservletrequest.getParameter("hipPlrPanelHoldRemove"));

		provider.setDeaNo(httpservletrequest.getParameter("deaNo"));
		provider.setCsrNo(httpservletrequest.getParameter("csrNo"));
		provider.setEnrollAs(initialNullValues(httpservletrequest.getParameter("enrollas")));
		provider.setEnrollClinicType(httpservletrequest.getParameter("enrollClinicType"));
		provider.setLocationType(initialNullValues(httpservletrequest.getParameter("locationType")));
		provider.setNpPractice(getSelectedValueForMultipart(httpservletrequest, "npPractice"));
		provider.setPaPractice(getSelectedValueForMultipart(httpservletrequest, "paPractice"));
		provider.setNaPractice(getSelectedValueForMultipart(httpservletrequest, "naPractice"));
		provider.setStateLicIssueDt(httpservletrequest.getParameter("stateLicIssueDt"));
		provider.setStateLicExpDt(httpservletrequest.getParameter("stateLicExpDt"));
		provider.setProfLiabilityCarrierName(httpservletrequest.getParameter("profLiabCarrier"));
		provider.setProfLiabilityCarrierLimit(httpservletrequest.getParameter("profLiabCovgLimit"));
		provider.setProfLiabilityPolicyNo(httpservletrequest.getParameter("profLiabPolicy"));
		provider.setProfLiabilityExpDate(httpservletrequest.getParameter("profLiabExtDt"));
		provider.setMalPracInsRevoke(initialNullValues(httpservletrequest.getParameter("malPracInsRevoke")));
		provider.setUnderGovInvestigation(initialNullValues(httpservletrequest.getParameter("underGovInvestigation")));
		provider.setExpellMedPay(initialNullValues(httpservletrequest.getParameter("expellMedPay")));
		provider.setConfProvAgreement(getSelectedValueForMultipart(httpservletrequest, "confProvAgreement"));
		provider.setConfW2(initialNullValues(httpservletrequest.getParameter("confW2")));
		// for section k
		provider.setUploadFileName(httpservletrequest.getParameter("uploadFileName"));
		provider.setUploadFileSize(httpservletrequest.getParameter("uploadFileSize"));
		provider.setUploadDocComment(initialNullValues(httpservletrequest.getParameter("uploadDocComment")));
		provider.setDltRowCounter(initialNullValues(httpservletrequest.getParameter("dltRowCounter")));
		provider.setFileListSize(initialNullValues(httpservletrequest.getParameter("fileListSize")));

		/* PMF Section Changes -- AD21239 -- */
		provider.setPaSupSpec(getSelectedValueForMultipart(httpservletrequest, "paSupSpec"));
		provider.setPaSupPMP(getSelectedValueForMultipart(httpservletrequest, "paSupPMP"));
		provider.setW2Comments(initialNullValues(httpservletrequest.getParameter("w2Comments")));
		/* PMF Section Changes -- AD21239 -- */

	}

	private static void setAddresses(Provider provider, HttpServletRequest httpservletrequest) {
		provider.setPracOfficeAddress1(httpservletrequest.getParameter("pracOfficeAddress1"));
		provider.setPracOfficeCity1(httpservletrequest.getParameter("pracOfficeCity1"));
		provider.setPracOfficeState1(httpservletrequest.getParameter("pracOfficeState1"));
		provider.setPracOfficeZip1(httpservletrequest.getParameter("pracOfficeZip1"));
		provider.setPracOfficeCounty1(httpservletrequest.getParameter("pracOfficeCounty1"));
		provider.setPracOfficePhone1(httpservletrequest.getParameter("pracOfficePhone1"));
		provider.setPracOfficeFax1(httpservletrequest.getParameter("pracOfficeFax1"));
		provider.setPracOfficeEmail1(httpservletrequest.getParameter("pracOfficeEmail1"));
		String as[] = httpservletrequest.getParameterValues("billAddressSame1");
		String s = getSelected(as);
		provider.setBillAddressSame1(s);

		if (s.equals("Y")) {
			provider.setPracBillAddress1(httpservletrequest.getParameter("pracOfficeAddress1"));
			provider.setPracBillCity1(httpservletrequest.getParameter("pracOfficeCity1"));
			provider.setPracBillState1(httpservletrequest.getParameter("pracOfficeState1"));
			provider.setPracBillZip1(httpservletrequest.getParameter("pracOfficeZip1"));
			provider.setPracBillCounty1(httpservletrequest.getParameter("pracOfficeCounty1"));
			provider.setPracBillPhone1(httpservletrequest.getParameter("pracOfficePhone1"));
			provider.setPracBillFax1(httpservletrequest.getParameter("pracOfficeFax1"));
			provider.setPracBillContactEmail1(httpservletrequest.getParameter("pracOfficeEmail1"));
		} else {
			provider.setPracBillAddress1(httpservletrequest.getParameter("pracBillAddress1"));
			provider.setPracBillCity1(httpservletrequest.getParameter("pracBillCity1"));
			provider.setPracBillState1(httpservletrequest.getParameter("pracBillState1"));
			provider.setPracBillZip1(httpservletrequest.getParameter("pracBillZip1"));
			provider.setPracBillCounty1(httpservletrequest.getParameter("pracBillCounty1"));
			provider.setPracBillPhone1(httpservletrequest.getParameter("pracBillPhone1"));
			provider.setPracBillFax1(httpservletrequest.getParameter("pracBillFax1"));
			provider.setPracBillContactEmail1(httpservletrequest.getParameter("pracBillContactEmail1"));
		}
		provider.setPracNPINo1(httpservletrequest.getParameter("pracNPINo1"));
		provider.setBillMedicareGroup1(httpservletrequest.getParameter("billMedicareGroup1"));
		provider.setBillMedicareIndividual1(httpservletrequest.getParameter("billMedicareIndividual1"));

		// Medicaid Changes
		provider.setMedicaidGroup1(httpservletrequest.getParameter("medicaidGroup1"));
		provider.setMedicaidIndividual1(httpservletrequest.getParameter("medicaidIndividual1"));

		// KentuckyMedicaid Changes
		provider.setKyMedicaidPart1(initialNullValues(httpservletrequest.getParameter("kyMedicaidPart1")));
		provider.setKyMedicaidId1(httpservletrequest.getParameter("kyMedicaidId1"));
		
		provider.setProvideTelehealth1(initialNullValues(httpservletrequest.getParameter("provideTelehealth1")));

		// Changes for the state mandate on 02/10/10 start
		provider.setLanguagesSpoken1(httpservletrequest.getParameter("languagesSpoken1"));
		provider.setOfferECI1(initialNullValues(httpservletrequest.getParameter("offerECI1")));
		provider.setOfferEPSDT1(initialNullValues(httpservletrequest.getParameter("offerEPSDT1")));
		provider.setProvideADB1(initialNullValues(httpservletrequest.getParameter("provideADB1")));
		provider.setProvideCSHCN1(initialNullValues(httpservletrequest.getParameter("provideCSHCN1")));
		// Changes for the state mandate on 02/10/10 end
		
		provider.setMatWaiveredPrescriber1(initialNullValues(httpservletrequest.getParameter("matWaiveredPrescriber1")));
		provider.setCertOpioidTreat1(initialNullValues(httpservletrequest.getParameter("certOpioidTreat1")));
		provider.setMatOpioid1(initialNullValues(httpservletrequest.getParameter("matOpioid1")));
		provider.setCounselOpioid1(initialNullValues(httpservletrequest.getParameter("counselOpioid1")));
		provider.setSudProv1(initialNullValues(httpservletrequest.getParameter("sudProv1")));
		provider.setResTreatCtr1(initialNullValues(httpservletrequest.getParameter("resTreatCtr1")));

		provider.setPracName2(httpservletrequest.getParameter("pracOfficeName1"));
		provider.setPracOfficeAddress2(httpservletrequest.getParameter("pracOfficeAddress2"));
		provider.setPracOfficeCity2(httpservletrequest.getParameter("pracOfficeCity2"));
		provider.setPracOfficeState2(httpservletrequest.getParameter("pracOfficeState2"));
		provider.setPracOfficeZip2(httpservletrequest.getParameter("pracOfficeZip2"));
		provider.setPracOfficeCounty2(httpservletrequest.getParameter("pracOfficeCounty2"));
		provider.setPracOfficePhone2(httpservletrequest.getParameter("pracOfficePhone2"));
		provider.setPracOfficeFax2(httpservletrequest.getParameter("pracOfficeFax2"));
		provider.setPracOfficeEmail2(httpservletrequest.getParameter("pracOfficeEmail2"));
		as = httpservletrequest.getParameterValues("billAddressSame2");
		s = getSelected(as);
		provider.setBillAddressSame2(s);
		provider.setPracBillContactName2(httpservletrequest.getParameter("pracBillContactName2"));
		if (s.equals("Y")) {
			provider.setPracBillAddress2(httpservletrequest.getParameter("pracOfficeAddress2"));
			provider.setPracBillCity2(httpservletrequest.getParameter("pracOfficeCity2"));
			provider.setPracBillState2(httpservletrequest.getParameter("pracOfficeState2"));
			provider.setPracBillZip2(httpservletrequest.getParameter("pracOfficeZip2"));
			provider.setPracBillCounty2(httpservletrequest.getParameter("pracOfficeCounty2"));
			provider.setPracBillPhone2(httpservletrequest.getParameter("pracOfficePhone2"));
			provider.setPracBillFax2(httpservletrequest.getParameter("pracOfficeFax2"));
			provider.setPracBillContactEmail2(httpservletrequest.getParameter("pracOfficeEmail2"));
		} else {
			provider.setPracBillAddress2(httpservletrequest.getParameter("pracBillAddress2"));
			provider.setPracBillCity2(httpservletrequest.getParameter("pracBillCity2"));
			provider.setPracBillState2(httpservletrequest.getParameter("pracBillState2"));
			provider.setPracBillZip2(httpservletrequest.getParameter("pracBillZip2"));
			provider.setPracBillCounty2(httpservletrequest.getParameter("pracBillCounty2"));
			provider.setPracBillPhone2(httpservletrequest.getParameter("pracBillPhone2"));
			provider.setPracBillFax2(httpservletrequest.getParameter("pracBillFax2"));
			provider.setPracBillContactEmail2(httpservletrequest.getParameter("pracBillContactEmail2"));
		}
		// 2013 SSCR 13503 changes
		provider.setPracBillContactName1(httpservletrequest.getParameter("pracBillContactName1"));
		String rsa2[] = httpservletrequest.getParameterValues("remitSamePrim2");
		String rsa2Val = getSelected(rsa2);
		provider.setRemitSamePrim2(rsa2Val);
		if (rsa2Val.equals("Y")) {
			provider.setPracBillAddress2(provider.getPracBillAddress1());
			provider.setPracBillCity2(provider.getPracBillCity1());
			provider.setPracBillState2(provider.getPracBillState1());
			provider.setPracBillZip2(provider.getPracBillZip1());
			provider.setPracBillCounty2(provider.getPracBillCounty1());
			provider.setPracBillPhone2(provider.getPracBillPhone1());
			provider.setPracBillFax2(provider.getPracBillFax1());
			provider.setPracBillContactEmail2(provider.getPracBillContactEmail1());
			provider.setPracBillContactName2(provider.getPracBillContactName1());
		}
		/*
		 * else { provider.setPracBillAddress2(httpservletrequest.getParameter(
		 * "pracBillAddress2"));
		 * provider.setPracBillCity2(httpservletrequest.getParameter
		 * ("pracBillCity2"));
		 * provider.setPracBillState2(httpservletrequest.getParameter
		 * ("pracBillState2"));
		 * provider.setPracBillZip2(httpservletrequest.getParameter
		 * ("pracBillZip2"));
		 * provider.setPracBillCounty2(httpservletrequest.getParameter
		 * ("pracBillCounty2"));
		 * provider.setPracBillPhone2(httpservletrequest.getParameter
		 * ("pracBillPhone2"));
		 * provider.setPracBillFax2(httpservletrequest.getParameter
		 * ("pracBillFax2"));
		 * provider.setPracBillContactEmail2(httpservletrequest
		 * .getParameter("pracBillContactEmail2"));
		 * provider.setPracBillContactName2
		 * (httpservletrequest.getParameter("pracBillContactName2")); }
		 */
		// 2013 SSCR 13503 changes end
		provider.setPracNPINo2(httpservletrequest.getParameter("pracNPINo2"));
		provider.setBillMedicareGroup2(httpservletrequest.getParameter("billMedicareGroup2"));
		provider.setBillMedicareIndividual2(httpservletrequest.getParameter("billMedicareIndividual2"));
		// Medicaid Changes
		provider.setMedicaidGroup2(httpservletrequest.getParameter("medicaidGroup2"));
		provider.setMedicaidIndividual2(httpservletrequest.getParameter("medicaidIndividual2"));

		// KentuckyMedicaid Changes
		provider.setKyMedicaidPart2(initialNullValues(httpservletrequest.getParameter("kyMedicaidPart2")));
		provider.setKyMedicaidId2(httpservletrequest.getParameter("kyMedicaidId2"));
		
		provider.setProvideTelehealth2(initialNullValues(httpservletrequest.getParameter("provideTelehealth2")));

		// Changes for the state mandate on 02/10/10 start
		provider.setLanguagesSpoken2(httpservletrequest.getParameter("languagesSpoken2"));
		provider.setOfferECI2(initialNullValues(httpservletrequest.getParameter("offerECI2")));
		provider.setOfferEPSDT2(initialNullValues(httpservletrequest.getParameter("offerEPSDT2")));
		provider.setProvideADB2(initialNullValues(httpservletrequest.getParameter("provideADB2")));
		provider.setProvideCSHCN2(initialNullValues(httpservletrequest.getParameter("provideCSHCN2")));
		// Changes for the state mandate on 02/10/10 end
		
		provider.setMatWaiveredPrescriber2(initialNullValues(httpservletrequest.getParameter("matWaiveredPrescriber2")));
		provider.setCertOpioidTreat2(initialNullValues(httpservletrequest.getParameter("certOpioidTreat2")));
		provider.setMatOpioid2(initialNullValues(httpservletrequest.getParameter("matOpioid2")));
		provider.setCounselOpioid2(initialNullValues(httpservletrequest.getParameter("counselOpioid2")));
		provider.setSudProv2(initialNullValues(httpservletrequest.getParameter("sudProv2")));
		provider.setResTreatCtr2(initialNullValues(httpservletrequest.getParameter("resTreatCtr2")));

		provider.setPracName3(httpservletrequest.getParameter("pracName3"));
		provider.setPracOfficeAddress3(httpservletrequest.getParameter("pracOfficeAddress3"));
		provider.setPracOfficeCity3(httpservletrequest.getParameter("pracOfficeCity3"));
		provider.setPracOfficeState3(httpservletrequest.getParameter("pracOfficeState3"));
		provider.setPracOfficeZip3(httpservletrequest.getParameter("pracOfficeZip3"));
		provider.setPracOfficeCounty3(httpservletrequest.getParameter("pracOfficeCounty3"));
		provider.setPracOfficePhone3(httpservletrequest.getParameter("pracOfficePhone3"));
		provider.setPracOfficeFax3(httpservletrequest.getParameter("pracOfficeFax3"));
		provider.setPracOfficeEmail3(httpservletrequest.getParameter("pracOfficeEmail3"));
		as = httpservletrequest.getParameterValues("billAddressSame3");
		s = getSelected(as);
		provider.setBillAddressSame3(s);
		provider.setPracBillContactName3(httpservletrequest.getParameter("pracBillContactName3"));
		if (s.equals("Y")) {
			provider.setPracBillAddress3(httpservletrequest.getParameter("pracOfficeAddress3"));
			provider.setPracBillCity3(httpservletrequest.getParameter("pracOfficeCity3"));
			provider.setPracBillState3(httpservletrequest.getParameter("pracOfficeState3"));
			provider.setPracBillZip3(httpservletrequest.getParameter("pracOfficeZip3"));
			provider.setPracBillCounty3(httpservletrequest.getParameter("pracOfficeCounty3"));
			provider.setPracBillPhone3(httpservletrequest.getParameter("pracOfficePhone3"));
			provider.setPracBillFax3(httpservletrequest.getParameter("pracOfficeFax3"));
			provider.setPracBillContactEmail3(httpservletrequest.getParameter("pracOfficeEmail3"));
		} else {
			provider.setPracBillAddress3(httpservletrequest.getParameter("pracBillAddress3"));
			provider.setPracBillCity3(httpservletrequest.getParameter("pracBillCity3"));
			provider.setPracBillState3(httpservletrequest.getParameter("pracBillState3"));
			provider.setPracBillZip3(httpservletrequest.getParameter("pracBillZip3"));
			provider.setPracBillCounty3(httpservletrequest.getParameter("pracBillCounty3"));
			provider.setPracBillPhone3(httpservletrequest.getParameter("pracBillPhone3"));
			provider.setPracBillFax3(httpservletrequest.getParameter("pracBillFax3"));
			provider.setPracBillContactEmail3(httpservletrequest.getParameter("pracBillContactEmail3"));
		}
		// 2013 SSCR 13503 change
		String rsa3[] = httpservletrequest.getParameterValues("remitSamePrim3");
		String rsa3Val = getSelected(rsa3);
		provider.setRemitSamePrim3(rsa3Val);
		if (rsa3Val.equals("Y")) {
			provider.setPracBillAddress3(provider.getPracBillAddress1());
			provider.setPracBillCity3(provider.getPracBillCity1());
			provider.setPracBillState3(provider.getPracBillState1());
			provider.setPracBillZip3(provider.getPracBillZip1());
			provider.setPracBillCounty3(provider.getPracBillCounty1());
			provider.setPracBillPhone3(provider.getPracBillPhone1());
			provider.setPracBillFax3(provider.getPracBillFax1());
			provider.setPracBillContactEmail3(provider.getPracBillContactEmail1());
			provider.setPracBillContactName3(provider.getPracBillContactName1());
		}
		/*
		 * else { provider.setPracBillAddress3(httpservletrequest.getParameter(
		 * "pracBillAddress3"));
		 * provider.setPracBillCity3(httpservletrequest.getParameter
		 * ("pracBillCity3"));
		 * provider.setPracBillState3(httpservletrequest.getParameter
		 * ("pracBillState3"));
		 * provider.setPracBillZip3(httpservletrequest.getParameter
		 * ("pracBillZip3"));
		 * provider.setPracBillCounty3(httpservletrequest.getParameter
		 * ("pracBillCounty3"));
		 * provider.setPracBillPhone3(httpservletrequest.getParameter
		 * ("pracBillPhone3"));
		 * provider.setPracBillFax3(httpservletrequest.getParameter
		 * ("pracBillFax3"));
		 * provider.setPracBillContactEmail3(httpservletrequest
		 * .getParameter("pracBillContactEmail3"));
		 * provider.setPracBillContactName3
		 * (httpservletrequest.getParameter("pracBillContactName3")); }
		 */// 2013 SSCR 13503 change end
		provider.setPracNPINo3(httpservletrequest.getParameter("pracNPINo3"));
		provider.setBillMedicareGroup3(httpservletrequest.getParameter("billMedicareGroup3"));
		provider.setBillMedicareIndividual3(httpservletrequest.getParameter("billMedicareIndividual3"));
		// Medicaid Changes
		provider.setMedicaidGroup3(httpservletrequest.getParameter("medicaidGroup3"));
		provider.setMedicaidIndividual3(httpservletrequest.getParameter("medicaidIndividual3"));

		// KentuckyMedicaid Changes
		provider.setKyMedicaidPart3(initialNullValues(httpservletrequest.getParameter("kyMedicaidPart3")));
		provider.setKyMedicaidId3(httpservletrequest.getParameter("kyMedicaidId3"));
		
		provider.setProvideTelehealth3(initialNullValues(httpservletrequest.getParameter("provideTelehealth3")));

		// Changes for the state mandate on 02/10/10 start
		provider.setLanguagesSpoken3(httpservletrequest.getParameter("languagesSpoken3"));
		provider.setOfferECI3(initialNullValues(httpservletrequest.getParameter("offerECI3")));
		provider.setOfferEPSDT3(initialNullValues(httpservletrequest.getParameter("offerEPSDT3")));
		provider.setProvideADB3(initialNullValues(httpservletrequest.getParameter("provideADB3")));
		provider.setProvideCSHCN3(initialNullValues(httpservletrequest.getParameter("provideCSHCN3")));
		// Changes for the state mandate on 02/10/10 end
		
		provider.setMatWaiveredPrescriber3(initialNullValues(httpservletrequest.getParameter("matWaiveredPrescriber3")));
		provider.setCertOpioidTreat3(initialNullValues(httpservletrequest.getParameter("certOpioidTreat3")));
		provider.setMatOpioid3(initialNullValues(httpservletrequest.getParameter("matOpioid3")));
		provider.setCounselOpioid3(initialNullValues(httpservletrequest.getParameter("counselOpioid3")));
		provider.setSudProv3(initialNullValues(httpservletrequest.getParameter("sudProv3")));
		provider.setResTreatCtr3(initialNullValues(httpservletrequest.getParameter("resTreatCtr3")));

		provider.setPracName4(httpservletrequest.getParameter("pracName4"));
		provider.setPracOfficeAddress4(httpservletrequest.getParameter("pracOfficeAddress4"));
		provider.setPracOfficeCity4(httpservletrequest.getParameter("pracOfficeCity4"));
		provider.setPracOfficeState4(httpservletrequest.getParameter("pracOfficeState4"));
		provider.setPracOfficeZip4(httpservletrequest.getParameter("pracOfficeZip4"));
		provider.setPracOfficeCounty4(httpservletrequest.getParameter("pracOfficeCounty4"));
		provider.setPracOfficePhone4(httpservletrequest.getParameter("pracOfficePhone4"));
		provider.setPracOfficeFax4(httpservletrequest.getParameter("pracOfficeFax4"));
		provider.setPracOfficeEmail4(httpservletrequest.getParameter("pracOfficeEmail4"));
		as = httpservletrequest.getParameterValues("billAddressSame4");
		s = getSelected(as);
		provider.setBillAddressSame4(s);
		provider.setPracBillContactName4(httpservletrequest.getParameter("pracBillContactName4"));
		if (s.equals("Y")) {
			provider.setPracBillAddress4(httpservletrequest.getParameter("pracOfficeAddress4"));
			provider.setPracBillCity4(httpservletrequest.getParameter("pracOfficeCity4"));
			provider.setPracBillState4(httpservletrequest.getParameter("pracOfficeState4"));
			provider.setPracBillZip4(httpservletrequest.getParameter("pracOfficeZip4"));
			provider.setPracBillCounty4(httpservletrequest.getParameter("pracOfficeCounty4"));
			provider.setPracBillPhone4(httpservletrequest.getParameter("pracOfficePhone4"));
			provider.setPracBillFax4(httpservletrequest.getParameter("pracOfficeFax4"));
			provider.setPracBillContactEmail4(httpservletrequest.getParameter("pracOfficeEmail4"));
		} else {
			provider.setPracBillAddress4(httpservletrequest.getParameter("pracBillAddress4"));
			provider.setPracBillCity4(httpservletrequest.getParameter("pracBillCity4"));
			provider.setPracBillState4(httpservletrequest.getParameter("pracBillState4"));
			provider.setPracBillZip4(httpservletrequest.getParameter("pracBillZip4"));
			provider.setPracBillCounty4(httpservletrequest.getParameter("pracBillCounty4"));
			provider.setPracBillPhone4(httpservletrequest.getParameter("pracBillPhone4"));
			provider.setPracBillFax4(httpservletrequest.getParameter("pracBillFax4"));
			provider.setPracBillContactEmail4(httpservletrequest.getParameter("pracBillContactEmail4"));
		}
		// 2013 SSCR 13503 change
		String rsa4[] = httpservletrequest.getParameterValues("remitSamePrim4");
		String rsa4Val = getSelected(rsa4);
		provider.setRemitSamePrim4(rsa4Val);
		if (rsa4Val.equals("Y")) {
			provider.setPracBillAddress4(provider.getPracBillAddress1());
			provider.setPracBillCity4(provider.getPracBillCity1());
			provider.setPracBillState4(provider.getPracBillState1());
			provider.setPracBillZip4(provider.getPracBillZip1());
			provider.setPracBillCounty4(provider.getPracBillCounty1());
			provider.setPracBillPhone4(provider.getPracBillPhone1());
			provider.setPracBillFax4(provider.getPracBillFax1());
			provider.setPracBillContactEmail4(provider.getPracBillContactEmail1());
			provider.setPracBillContactName4(provider.getPracBillContactName1());
		}
		/*
		 * else { provider.setPracBillAddress4(httpservletrequest.getParameter(
		 * "pracBillAddress4"));
		 * provider.setPracBillCity4(httpservletrequest.getParameter
		 * ("pracBillCity4"));
		 * provider.setPracBillState4(httpservletrequest.getParameter
		 * ("pracBillState4"));
		 * provider.setPracBillZip4(httpservletrequest.getParameter
		 * ("pracBillZip4"));
		 * provider.setPracBillCounty4(httpservletrequest.getParameter
		 * ("pracBillCounty4"));
		 * provider.setPracBillPhone4(httpservletrequest.getParameter
		 * ("pracBillPhone4"));
		 * provider.setPracBillFax4(httpservletrequest.getParameter
		 * ("pracBillFax4"));
		 * provider.setPracBillContactEmail4(httpservletrequest
		 * .getParameter("pracBillContactEmail4"));
		 * provider.setPracBillContactName4
		 * (httpservletrequest.getParameter("pracBillContactName4")); }
		 */// 2013 SSCR 13503 change end
		provider.setPracNPINo4(httpservletrequest.getParameter("pracNPINo4"));
		provider.setBillMedicareGroup4(httpservletrequest.getParameter("billMedicareGroup4"));
		provider.setBillMedicareIndividual4(httpservletrequest.getParameter("billMedicareIndividual4"));
		// Medicaid Changes
		provider.setMedicaidGroup4(httpservletrequest.getParameter("medicaidGroup4"));
		provider.setMedicaidIndividual4(httpservletrequest.getParameter("medicaidIndividual4"));

		// KentuckyMedicaid Changes
		provider.setKyMedicaidPart4(initialNullValues(httpservletrequest.getParameter("kyMedicaidPart4")));
		provider.setKyMedicaidId4(httpservletrequest.getParameter("kyMedicaidId4"));
		
		provider.setProvideTelehealth4(initialNullValues(httpservletrequest.getParameter("provideTelehealth4")));

		// Changes for the state mandate on 02/10/10 start
		provider.setLanguagesSpoken4(httpservletrequest.getParameter("languagesSpoken4"));
		provider.setOfferECI4(initialNullValues(httpservletrequest.getParameter("offerECI4")));
		provider.setOfferEPSDT4(initialNullValues(httpservletrequest.getParameter("offerEPSDT4")));
		provider.setProvideADB4(initialNullValues(httpservletrequest.getParameter("provideADB4")));
		provider.setProvideCSHCN4(initialNullValues(httpservletrequest.getParameter("provideCSHCN4")));
		// Changes for the state mandate on 02/10/10 end

		provider.setMatWaiveredPrescriber4(initialNullValues(httpservletrequest.getParameter("matWaiveredPrescriber4")));
		provider.setCertOpioidTreat4(initialNullValues(httpservletrequest.getParameter("certOpioidTreat4")));
		provider.setMatOpioid4(initialNullValues(httpservletrequest.getParameter("matOpioid4")));
		provider.setCounselOpioid4(initialNullValues(httpservletrequest.getParameter("counselOpioid4")));
		provider.setSudProv4(initialNullValues(httpservletrequest.getParameter("sudProv4")));
		provider.setResTreatCtr4(initialNullValues(httpservletrequest.getParameter("resTreatCtr4")));
		
		provider.setPracName5(httpservletrequest.getParameter("pracName5"));
		provider.setPracOfficeAddress5(httpservletrequest.getParameter("pracOfficeAddress5"));
		provider.setPracOfficeCity5(httpservletrequest.getParameter("pracOfficeCity5"));
		provider.setPracOfficeState5(httpservletrequest.getParameter("pracOfficeState5"));
		provider.setPracOfficeZip5(httpservletrequest.getParameter("pracOfficeZip5"));
		provider.setPracOfficeCounty5(httpservletrequest.getParameter("pracOfficeCounty5"));
		provider.setPracOfficePhone5(httpservletrequest.getParameter("pracOfficePhone5"));
		provider.setPracOfficeFax5(httpservletrequest.getParameter("pracOfficeFax5"));
		provider.setPracOfficeEmail5(httpservletrequest.getParameter("pracOfficeEmail5"));
		as = httpservletrequest.getParameterValues("billAddressSame5");
		s = getSelected(as);
		provider.setBillAddressSame5(s);
		provider.setPracBillContactName5(httpservletrequest.getParameter("pracBillContactName5"));
		if (s.equals("Y")) {
			provider.setPracBillAddress5(httpservletrequest.getParameter("pracOfficeAddress5"));
			provider.setPracBillCity5(httpservletrequest.getParameter("pracOfficeCity5"));
			provider.setPracBillState5(httpservletrequest.getParameter("pracOfficeState5"));
			provider.setPracBillZip5(httpservletrequest.getParameter("pracOfficeZip5"));
			provider.setPracBillCounty5(httpservletrequest.getParameter("pracOfficeCounty5"));
			provider.setPracBillPhone5(httpservletrequest.getParameter("pracOfficePhone5"));
			provider.setPracBillFax5(httpservletrequest.getParameter("pracOfficeFax5"));
			provider.setPracBillContactEmail5(httpservletrequest.getParameter("pracOfficeEmail5"));
		} else {
			provider.setPracBillAddress5(httpservletrequest.getParameter("pracBillAddress5"));
			provider.setPracBillCity5(httpservletrequest.getParameter("pracBillCity5"));
			provider.setPracBillState5(httpservletrequest.getParameter("pracBillState5"));
			provider.setPracBillZip5(httpservletrequest.getParameter("pracBillZip5"));
			provider.setPracBillCounty5(httpservletrequest.getParameter("pracBillCounty5"));
			provider.setPracBillPhone5(httpservletrequest.getParameter("pracBillPhone5"));
			provider.setPracBillFax5(httpservletrequest.getParameter("pracBillFax5"));
			provider.setPracBillContactEmail5(httpservletrequest.getParameter("pracBillContactEmail5"));
		}
		// 2013 SSCR 13503 change
		String rsa5[] = httpservletrequest.getParameterValues("remitSamePrim5");
		String rsa5Val = getSelected(rsa5);
		provider.setRemitSamePrim5(rsa5Val);
		if (rsa5Val.equals("Y")) {
			provider.setPracBillAddress5(provider.getPracBillAddress1());
			provider.setPracBillCity5(provider.getPracBillCity1());
			provider.setPracBillState5(provider.getPracBillState1());
			provider.setPracBillZip5(provider.getPracBillZip1());
			provider.setPracBillCounty5(provider.getPracBillCounty1());
			provider.setPracBillPhone5(provider.getPracBillPhone1());
			provider.setPracBillFax5(provider.getPracBillFax1());
			provider.setPracBillContactEmail5(provider.getPracBillContactEmail1());
			provider.setPracBillContactName5(provider.getPracBillContactName1());
		}
		/*
		 * else { provider.setPracBillAddress5(httpservletrequest.getParameter(
		 * "pracBillAddress5"));
		 * provider.setPracBillCity5(httpservletrequest.getParameter
		 * ("pracBillCity5"));
		 * provider.setPracBillState5(httpservletrequest.getParameter
		 * ("pracBillState5"));
		 * provider.setPracBillZip5(httpservletrequest.getParameter
		 * ("pracBillZip5"));
		 * provider.setPracBillCounty5(httpservletrequest.getParameter
		 * ("pracBillCounty5"));
		 * provider.setPracBillPhone5(httpservletrequest.getParameter
		 * ("pracBillPhone5"));
		 * provider.setPracBillFax5(httpservletrequest.getParameter
		 * ("pracBillFax5"));
		 * provider.setPracBillContactEmail5(httpservletrequest
		 * .getParameter("pracBillContactEmail5"));
		 * provider.setPracBillContactName5
		 * (httpservletrequest.getParameter("pracBillContactName5")); }
		 */
		// 2013 SSCR 13503 change end
		provider.setPracNPINo5(httpservletrequest.getParameter("pracNPINo5"));
		provider.setBillMedicareGroup5(httpservletrequest.getParameter("billMedicareGroup5"));
		provider.setBillMedicareIndividual5(httpservletrequest.getParameter("billMedicareIndividual5"));

		// Medicaid Changes
		provider.setMedicaidGroup5(httpservletrequest.getParameter("medicaidGroup5"));
		provider.setMedicaidIndividual5(httpservletrequest.getParameter("medicaidIndividual5"));

		// KentuckyMedicaid Changes
		provider.setKyMedicaidPart5(initialNullValues(httpservletrequest.getParameter("kyMedicaidPart5")));
		provider.setKyMedicaidId5(httpservletrequest.getParameter("kyMedicaidId5"));
		
		provider.setProvideTelehealth5(initialNullValues(httpservletrequest.getParameter("provideTelehealth5")));

		// Changes for the state mandate on 02/10/10 start
		provider.setLanguagesSpoken5(httpservletrequest.getParameter("languagesSpoken5"));
		provider.setOfferECI5(initialNullValues(httpservletrequest.getParameter("offerECI5")));
		provider.setOfferEPSDT5(initialNullValues(httpservletrequest.getParameter("offerEPSDT5")));
		provider.setProvideADB5(initialNullValues(httpservletrequest.getParameter("provideADB5")));
		provider.setProvideCSHCN5(initialNullValues(httpservletrequest.getParameter("provideCSHCN5")));
		// Changes for the state mandate on 02/10/10 end
		
		provider.setMatWaiveredPrescriber5(initialNullValues(httpservletrequest.getParameter("matWaiveredPrescriber5")));
		provider.setCertOpioidTreat5(initialNullValues(httpservletrequest.getParameter("certOpioidTreat5")));
		provider.setMatOpioid5(initialNullValues(httpservletrequest.getParameter("matOpioid5")));
		provider.setCounselOpioid5(initialNullValues(httpservletrequest.getParameter("counselOpioid5")));
		provider.setSudProv5(initialNullValues(httpservletrequest.getParameter("sudProv5")));
		provider.setResTreatCtr5(initialNullValues(httpservletrequest.getParameter("resTreatCtr5")));

		provider.setPracName6(httpservletrequest.getParameter("pracName6"));
		provider.setPracOfficeAddress6(httpservletrequest.getParameter("pracOfficeAddress6"));
		provider.setPracOfficeCity6(httpservletrequest.getParameter("pracOfficeCity6"));
		provider.setPracOfficeState6(httpservletrequest.getParameter("pracOfficeState6"));
		provider.setPracOfficeZip6(httpservletrequest.getParameter("pracOfficeZip6"));
		provider.setPracOfficeCounty6(httpservletrequest.getParameter("pracOfficeCounty6"));
		provider.setPracOfficePhone6(httpservletrequest.getParameter("pracOfficePhone6"));
		provider.setPracOfficeFax6(httpservletrequest.getParameter("pracOfficeFax6"));
		provider.setPracOfficeEmail6(httpservletrequest.getParameter("pracOfficeEmail6"));
		as = httpservletrequest.getParameterValues("billAddressSame6");
		s = getSelected(as);
		provider.setBillAddressSame6(s);
		provider.setPracBillContactName6(httpservletrequest.getParameter("pracBillContactName6"));
		if (s.equals("Y")) {
			provider.setPracBillAddress6(httpservletrequest.getParameter("pracOfficeAddress6"));
			provider.setPracBillCity6(httpservletrequest.getParameter("pracOfficeCity6"));
			provider.setPracBillState6(httpservletrequest.getParameter("pracOfficeState6"));
			provider.setPracBillZip6(httpservletrequest.getParameter("pracOfficeZip6"));
			provider.setPracBillCounty6(httpservletrequest.getParameter("pracOfficeCounty6"));
			provider.setPracBillPhone6(httpservletrequest.getParameter("pracOfficePhone6"));
			provider.setPracBillFax6(httpservletrequest.getParameter("pracOfficeFax6"));
			provider.setPracBillContactEmail6(httpservletrequest.getParameter("pracOfficeEmail6"));
		} else {
			// provider.setPracBillContactName6(httpservletrequest.getParameter("pracBillContactName6"));
			provider.setPracBillAddress6(httpservletrequest.getParameter("pracBillAddress6"));
			provider.setPracBillCity6(httpservletrequest.getParameter("pracBillCity6"));
			provider.setPracBillState6(httpservletrequest.getParameter("pracBillState6"));
			provider.setPracBillZip6(httpservletrequest.getParameter("pracBillZip6"));
			provider.setPracBillCounty6(httpservletrequest.getParameter("pracBillCounty6"));
			provider.setPracBillPhone6(httpservletrequest.getParameter("pracBillPhone6"));
			provider.setPracBillFax6(httpservletrequest.getParameter("pracBillFax6"));
			provider.setPracBillContactEmail6(httpservletrequest.getParameter("pracBillContactEmail6"));
		}
		// 2013 SSCR 13503 change
		String rsa6[] = httpservletrequest.getParameterValues("remitSamePrim6");
		String rsa6Val = getSelected(rsa6);
		provider.setRemitSamePrim6(rsa6Val);
		if (rsa6Val.equals("Y")) {
			provider.setPracBillAddress6(provider.getPracBillAddress1());
			provider.setPracBillCity6(provider.getPracBillCity1());
			provider.setPracBillState6(provider.getPracBillState1());
			provider.setPracBillZip6(provider.getPracBillZip1());
			provider.setPracBillCounty6(provider.getPracBillCounty1());
			provider.setPracBillPhone6(provider.getPracBillPhone1());
			provider.setPracBillFax6(provider.getPracBillFax1());
			provider.setPracBillContactEmail6(provider.getPracBillContactEmail1());
			provider.setPracBillContactName6(provider.getPracBillContactName1());
		}
		/*
		 * else { provider.setPracBillAddress6(httpservletrequest.getParameter(
		 * "pracBillAddress6"));
		 * provider.setPracBillCity6(httpservletrequest.getParameter
		 * ("pracBillCity6"));
		 * provider.setPracBillState6(httpservletrequest.getParameter
		 * ("pracBillState6"));
		 * provider.setPracBillZip6(httpservletrequest.getParameter
		 * ("pracBillZip6"));
		 * provider.setPracBillCounty6(httpservletrequest.getParameter
		 * ("pracBillCounty6"));
		 * provider.setPracBillPhone6(httpservletrequest.getParameter
		 * ("pracBillPhone6"));
		 * provider.setPracBillFax6(httpservletrequest.getParameter
		 * ("pracBillFax6"));
		 * provider.setPracBillContactEmail6(httpservletrequest
		 * .getParameter("pracBillContactEmail6"));
		 * provider.setPracBillContactName6
		 * (httpservletrequest.getParameter("pracBillContactName6")); }
		 */// 2013 SSCR 13503 change end
		provider.setPracNPINo6(httpservletrequest.getParameter("pracNPINo6"));

		// Medicaid Changes
		provider.setMedicaidGroup6(httpservletrequest.getParameter("medicaidGroup6"));
		provider.setMedicaidIndividual6(httpservletrequest.getParameter("medicaidIndividual6"));

		// KentuckyMedicaid Changes
		provider.setKyMedicaidPart6(initialNullValues(httpservletrequest.getParameter("kyMedicaidPart6")));
		provider.setKyMedicaidId6(httpservletrequest.getParameter("kyMedicaidId6"));
		
		provider.setProvideTelehealth6(initialNullValues(httpservletrequest.getParameter("provideTelehealth6")));

		// Changes for the state mandate on 02/10/10 start
		provider.setLanguagesSpoken6(httpservletrequest.getParameter("languagesSpoken6"));
		provider.setOfferECI6(initialNullValues(httpservletrequest.getParameter("offerECI6")));
		provider.setOfferEPSDT6(initialNullValues(httpservletrequest.getParameter("offerEPSDT6")));
		provider.setProvideADB6(initialNullValues(httpservletrequest.getParameter("provideADB6")));
		provider.setProvideCSHCN6(initialNullValues(httpservletrequest.getParameter("provideCSHCN6")));
		// Changes for the state mandate on 02/10/10 end

		provider.setMatWaiveredPrescriber6(initialNullValues(httpservletrequest.getParameter("matWaiveredPrescriber6")));
		provider.setCertOpioidTreat6(initialNullValues(httpservletrequest.getParameter("certOpioidTreat6")));
		provider.setMatOpioid6(initialNullValues(httpservletrequest.getParameter("matOpioid6")));
		provider.setCounselOpioid6(initialNullValues(httpservletrequest.getParameter("counselOpioid6")));
		provider.setSudProv6(initialNullValues(httpservletrequest.getParameter("sudProv6")));
		provider.setResTreatCtr6(initialNullValues(httpservletrequest.getParameter("resTreatCtr6")));
		
		if (getSelectedValue(httpservletrequest, "children").equals("Y"))
			provider.getPatientInfo().add("children");
		if (getSelectedValue(httpservletrequest, "adolescents").equals("Y"))
			provider.getPatientInfo().add("adolescents");
		if (getSelectedValue(httpservletrequest, "adults").equals("Y"))
			provider.getPatientInfo().add("adults");
		if (getSelectedValue(httpservletrequest, "senior").equals("Y"))
			provider.getPatientInfo().add("senior");
		if (getSelectedValue(httpservletrequest, "deaf").equals("Y"))
			provider.getPatientInfo().add("deaf");
		if (getSelectedValue(httpservletrequest, "disabled").equals("Y"))
			provider.getPatientInfo().add("disabled");

		provider.setNonPsychEval(initialNullValues(httpservletrequest.getParameter("nonPsychEval")));

		AreasOfExpertise expertise = provider.getAreasOfExpertise();
		expertise.setAdd(getSelectedBooleanValue(httpservletrequest, "add"));
		expertise.setDialectical(getSelectedBooleanValue(httpservletrequest, "dialectical"));
		expertise.setNeuropsychological(getSelectedBooleanValue(httpservletrequest, "neuropsychological"));
		expertise.setAdoption(getSelectedBooleanValue(httpservletrequest, "adoption"));
		expertise.setDivorce(getSelectedBooleanValue(httpservletrequest, "divorce"));
		expertise.setAnxiety(getSelectedBooleanValue(httpservletrequest, "anxiety"));
		expertise.setDomViolence(getSelectedBooleanValue(httpservletrequest, "domViolence"));
		expertise.setObsessiveComp(getSelectedBooleanValue(httpservletrequest, "obsessiveComp"));
		expertise.setAutism(getSelectedBooleanValue(httpservletrequest, "autism"));
		expertise.setEatingDisorder(getSelectedBooleanValue(httpservletrequest, "eatingDisorder"));
		expertise.setPainMgmt(getSelectedBooleanValue(httpservletrequest, "painMgmt"));
		expertise.setBariatric(getSelectedBooleanValue(httpservletrequest, "bariatric"));
		expertise.setElectroconvulsive(getSelectedBooleanValue(httpservletrequest, "electroconvulsive"));
		expertise.setPersonalityDis(getSelectedBooleanValue(httpservletrequest, "personalityDis"));
		expertise.setBehaviorMod(getSelectedBooleanValue(httpservletrequest, "behaviorMod"));
		expertise.setEndofLifeIssues(getSelectedBooleanValue(httpservletrequest, "endofLifeIssues"));
		expertise.setPostpartum(getSelectedBooleanValue(httpservletrequest, "postpartum"));
		expertise.setBipolar(getSelectedBooleanValue(httpservletrequest, "bipolar"));
		expertise.setFamilyTherapy(getSelectedBooleanValue(httpservletrequest, "familyTherapy"));
		expertise.setPTSD(getSelectedBooleanValue(httpservletrequest, "PTSD"));
		expertise.setBriefSolution(getSelectedBooleanValue(httpservletrequest, "briefSolution"));
		expertise.setGayIssues(getSelectedBooleanValue(httpservletrequest, "gayIssues"));
		expertise.setPrenatalIssues(getSelectedBooleanValue(httpservletrequest, "prenatalIssues"));
		expertise.setChemicalDep(getSelectedBooleanValue(httpservletrequest, "chemicalDep"));
		expertise.setGroupTherapy(getSelectedBooleanValue(httpservletrequest, "groupTherapy"));
		expertise.setPsychologicalTest(getSelectedBooleanValue(httpservletrequest, "psychologicalTest"));
		expertise.setChemicalDepAssessment(getSelectedBooleanValue(httpservletrequest, "chemicalDepAssessment"));
		expertise.setHIVRelatedIssues(getSelectedBooleanValue(httpservletrequest, "HIVRelatedIssues"));
		expertise.setChristianCounseling(getSelectedBooleanValue(httpservletrequest, "christianCounseling"));
		expertise.setInfertility(getSelectedBooleanValue(httpservletrequest, "infertility"));
		expertise.setSchizophrenia(getSelectedBooleanValue(httpservletrequest, "schizophrenia"));
		expertise.setCompulsiveGambling(getSelectedBooleanValue(httpservletrequest, "compulsiveGambling"));
		expertise.setMedicationMgmt(getSelectedBooleanValue(httpservletrequest, "medicationMgmt"));
		expertise.setSexualAbuse(getSelectedBooleanValue(httpservletrequest, "sexualAbuse"));
		expertise.setEthnicIssues(getSelectedBooleanValue(httpservletrequest, "ethnicIssues"));
		expertise.setSexualDisorder(getSelectedBooleanValue(httpservletrequest, "sexualDisorder"));
		expertise.setDepressiveDisorder(getSelectedBooleanValue(httpservletrequest, "depressiveDisorder"));
		expertise.setMenIssues(getSelectedBooleanValue(httpservletrequest, "menIssues"));
		expertise.setVictims(getSelectedBooleanValue(httpservletrequest, "victims"));
		expertise.setWomenIssues(getSelectedBooleanValue(httpservletrequest, "womenIssues"));
		expertise.setCounselOpioid(getSelectedBooleanValue(httpservletrequest, "counselOpioid"));
		expertise.setMatOpioid(getSelectedBooleanValue(httpservletrequest, "matOpioid"));
		expertise.setMatWaivered(getSelectedBooleanValue(httpservletrequest, "matWaivered"));
		expertise.setTelehealth(getSelectedBooleanValue(httpservletrequest, "telehealthProvAOE"));

		provider.setAreasOfExpertise(expertise);

	}

	private static void setAddressesForMultiPart(Provider provider, MultipartFormDataRequest httpservletrequest) {
		provider.setPracOfficeAddress1(httpservletrequest.getParameter("pracOfficeAddress1"));
		provider.setPracOfficeCity1(httpservletrequest.getParameter("pracOfficeCity1"));
		provider.setPracOfficeState1(httpservletrequest.getParameter("pracOfficeState1"));
		provider.setPracOfficeZip1(httpservletrequest.getParameter("pracOfficeZip1"));
		provider.setPracOfficeCounty1(httpservletrequest.getParameter("pracOfficeCounty1"));
		provider.setPracOfficePhone1(httpservletrequest.getParameter("pracOfficePhone1"));
		provider.setPracOfficeFax1(httpservletrequest.getParameter("pracOfficeFax1"));
		provider.setPracOfficeEmail1(httpservletrequest.getParameter("pracOfficeEmail1"));
		String as[] = httpservletrequest.getParameterValues("billAddressSame1");
		String s = getSelected(as);
		provider.setBillAddressSame1(s);

		if (s.equals("Y")) {
			provider.setPracBillAddress1(httpservletrequest.getParameter("pracOfficeAddress1"));
			provider.setPracBillCity1(httpservletrequest.getParameter("pracOfficeCity1"));
			provider.setPracBillState1(httpservletrequest.getParameter("pracOfficeState1"));
			provider.setPracBillZip1(httpservletrequest.getParameter("pracOfficeZip1"));
			provider.setPracBillCounty1(httpservletrequest.getParameter("pracOfficeCounty1"));
			provider.setPracBillPhone1(httpservletrequest.getParameter("pracOfficePhone1"));
			provider.setPracBillFax1(httpservletrequest.getParameter("pracOfficeFax1"));
			provider.setPracBillContactEmail1(httpservletrequest.getParameter("pracOfficeEmail1"));
		} else {
			provider.setPracBillAddress1(httpservletrequest.getParameter("pracBillAddress1"));
			provider.setPracBillCity1(httpservletrequest.getParameter("pracBillCity1"));
			provider.setPracBillState1(httpservletrequest.getParameter("pracBillState1"));
			provider.setPracBillZip1(httpservletrequest.getParameter("pracBillZip1"));
			provider.setPracBillCounty1(httpservletrequest.getParameter("pracBillCounty1"));
			provider.setPracBillPhone1(httpservletrequest.getParameter("pracBillPhone1"));
			provider.setPracBillFax1(httpservletrequest.getParameter("pracBillFax1"));
			provider.setPracBillContactEmail1(httpservletrequest.getParameter("pracBillContactEmail1"));
		}
		provider.setPracNPINo1(httpservletrequest.getParameter("pracNPINo1"));
		provider.setBillMedicareGroup1(httpservletrequest.getParameter("billMedicareGroup1"));
		provider.setBillMedicareIndividual1(httpservletrequest.getParameter("billMedicareIndividual1"));

		// Medicaid Changes
		provider.setMedicaidGroup1(httpservletrequest.getParameter("medicaidGroup1"));
		provider.setMedicaidIndividual1(httpservletrequest.getParameter("medicaidIndividual1"));

		// KentuckyMedicaid Changes
		provider.setKyMedicaidPart1(initialNullValues(httpservletrequest.getParameter("kyMedicaidPart1")));
		provider.setKyMedicaidId1(httpservletrequest.getParameter("kyMedicaidId1"));
		
		provider.setProvideTelehealth1(initialNullValues(httpservletrequest.getParameter("provideTelehealth1")));

		// Changes for the state mandate on 02/10/10 start
		provider.setLanguagesSpoken1(httpservletrequest.getParameter("languagesSpoken1"));
		provider.setOfferECI1(initialNullValues(httpservletrequest.getParameter("offerECI1")));
		provider.setOfferEPSDT1(initialNullValues(httpservletrequest.getParameter("offerEPSDT1")));
		provider.setProvideADB1(initialNullValues(httpservletrequest.getParameter("provideADB1")));
		provider.setProvideCSHCN1(initialNullValues(httpservletrequest.getParameter("provideCSHCN1")));
		// Changes for the state mandate on 02/10/10 end

		provider.setMatWaiveredPrescriber1(initialNullValues(httpservletrequest.getParameter("matWaiveredPrescriber1")));
		provider.setCertOpioidTreat1(initialNullValues(httpservletrequest.getParameter("certOpioidTreat1")));
		provider.setMatOpioid1(initialNullValues(httpservletrequest.getParameter("matOpioid1")));
		provider.setCounselOpioid1(initialNullValues(httpservletrequest.getParameter("counselOpioid1")));
		provider.setSudProv1(initialNullValues(httpservletrequest.getParameter("sudProv1")));
		provider.setResTreatCtr1(initialNullValues(httpservletrequest.getParameter("resTreatCtr1")));
		
		provider.setPracName2(httpservletrequest.getParameter("pracOfficeName1"));
		provider.setPracOfficeAddress2(httpservletrequest.getParameter("pracOfficeAddress2"));
		provider.setPracOfficeCity2(httpservletrequest.getParameter("pracOfficeCity2"));
		provider.setPracOfficeState2(httpservletrequest.getParameter("pracOfficeState2"));
		provider.setPracOfficeZip2(httpservletrequest.getParameter("pracOfficeZip2"));
		provider.setPracOfficeCounty2(httpservletrequest.getParameter("pracOfficeCounty2"));
		provider.setPracOfficePhone2(httpservletrequest.getParameter("pracOfficePhone2"));
		provider.setPracOfficeFax2(httpservletrequest.getParameter("pracOfficeFax2"));
		provider.setPracOfficeEmail2(httpservletrequest.getParameter("pracOfficeEmail2"));
		as = httpservletrequest.getParameterValues("billAddressSame2");
		s = getSelected(as);
		provider.setBillAddressSame2(s);
		provider.setPracBillContactName2(httpservletrequest.getParameter("pracBillContactName2"));
		if (s.equals("Y")) {
			provider.setPracBillAddress2(httpservletrequest.getParameter("pracOfficeAddress2"));
			provider.setPracBillCity2(httpservletrequest.getParameter("pracOfficeCity2"));
			provider.setPracBillState2(httpservletrequest.getParameter("pracOfficeState2"));
			provider.setPracBillZip2(httpservletrequest.getParameter("pracOfficeZip2"));
			provider.setPracBillCounty2(httpservletrequest.getParameter("pracOfficeCounty2"));
			provider.setPracBillPhone2(httpservletrequest.getParameter("pracOfficePhone2"));
			provider.setPracBillFax2(httpservletrequest.getParameter("pracOfficeFax2"));
			provider.setPracBillContactEmail2(httpservletrequest.getParameter("pracOfficeEmail2"));
		} else {
			provider.setPracBillAddress2(httpservletrequest.getParameter("pracBillAddress2"));
			provider.setPracBillCity2(httpservletrequest.getParameter("pracBillCity2"));
			provider.setPracBillState2(httpservletrequest.getParameter("pracBillState2"));
			provider.setPracBillZip2(httpservletrequest.getParameter("pracBillZip2"));
			provider.setPracBillCounty2(httpservletrequest.getParameter("pracBillCounty2"));
			provider.setPracBillPhone2(httpservletrequest.getParameter("pracBillPhone2"));
			provider.setPracBillFax2(httpservletrequest.getParameter("pracBillFax2"));
			provider.setPracBillContactEmail2(httpservletrequest.getParameter("pracBillContactEmail2"));
		}
		// 2013 SSCR 13503 changes
		provider.setPracBillContactName1(httpservletrequest.getParameter("pracBillContactName1"));
		String rsa2[] = httpservletrequest.getParameterValues("remitSamePrim2");
		String rsa2Val = getSelected(rsa2);
		provider.setRemitSamePrim2(rsa2Val);
		if (rsa2Val.equals("Y")) {
			provider.setPracBillAddress2(provider.getPracBillAddress1());
			provider.setPracBillCity2(provider.getPracBillCity1());
			provider.setPracBillState2(provider.getPracBillState1());
			provider.setPracBillZip2(provider.getPracBillZip1());
			provider.setPracBillCounty2(provider.getPracBillCounty1());
			provider.setPracBillPhone2(provider.getPracBillPhone1());
			provider.setPracBillFax2(provider.getPracBillFax1());
			provider.setPracBillContactEmail2(provider.getPracBillContactEmail1());
			provider.setPracBillContactName2(provider.getPracBillContactName1());
		}
		/*
		 * else { provider.setPracBillAddress2(httpservletrequest.getParameter(
		 * "pracBillAddress2"));
		 * provider.setPracBillCity2(httpservletrequest.getParameter
		 * ("pracBillCity2"));
		 * provider.setPracBillState2(httpservletrequest.getParameter
		 * ("pracBillState2"));
		 * provider.setPracBillZip2(httpservletrequest.getParameter
		 * ("pracBillZip2"));
		 * provider.setPracBillCounty2(httpservletrequest.getParameter
		 * ("pracBillCounty2"));
		 * provider.setPracBillPhone2(httpservletrequest.getParameter
		 * ("pracBillPhone2"));
		 * provider.setPracBillFax2(httpservletrequest.getParameter
		 * ("pracBillFax2"));
		 * provider.setPracBillContactEmail2(httpservletrequest
		 * .getParameter("pracBillContactEmail2"));
		 * provider.setPracBillContactName2
		 * (httpservletrequest.getParameter("pracBillContactName2")); }
		 */// 2013 SSCR 13503 changes end
		provider.setPracNPINo2(httpservletrequest.getParameter("pracNPINo2"));
		provider.setBillMedicareGroup2(httpservletrequest.getParameter("billMedicareGroup2"));
		provider.setBillMedicareIndividual2(httpservletrequest.getParameter("billMedicareIndividual2"));
		// Medicaid Changes
		provider.setMedicaidGroup2(httpservletrequest.getParameter("medicaidGroup2"));
		provider.setMedicaidIndividual2(httpservletrequest.getParameter("medicaidIndividual2"));

		// KentuckyMedicaid Changes
		provider.setKyMedicaidPart2(initialNullValues(httpservletrequest.getParameter("kyMedicaidPart2")));
		provider.setKyMedicaidId2(httpservletrequest.getParameter("kyMedicaidId2"));
		
		provider.setProvideTelehealth2(initialNullValues(httpservletrequest.getParameter("provideTelehealth2")));

		// Changes for the state mandate on 02/10/10 start
		provider.setLanguagesSpoken2(httpservletrequest.getParameter("languagesSpoken2"));
		provider.setOfferECI2(initialNullValues(httpservletrequest.getParameter("offerECI2")));
		provider.setOfferEPSDT2(initialNullValues(httpservletrequest.getParameter("offerEPSDT2")));
		provider.setProvideADB2(initialNullValues(httpservletrequest.getParameter("provideADB2")));
		provider.setProvideCSHCN2(initialNullValues(httpservletrequest.getParameter("provideCSHCN2")));
		// Changes for the state mandate on 02/10/10 end
		
		provider.setMatWaiveredPrescriber2(initialNullValues(httpservletrequest.getParameter("matWaiveredPrescriber2")));
		provider.setCertOpioidTreat2(initialNullValues(httpservletrequest.getParameter("certOpioidTreat2")));
		provider.setMatOpioid2(initialNullValues(httpservletrequest.getParameter("matOpioid2")));
		provider.setCounselOpioid2(initialNullValues(httpservletrequest.getParameter("counselOpioid2")));
		provider.setSudProv2(initialNullValues(httpservletrequest.getParameter("sudProv2")));
		provider.setResTreatCtr2(initialNullValues(httpservletrequest.getParameter("resTreatCtr2")));

		provider.setPracName3(httpservletrequest.getParameter("pracName3"));
		provider.setPracOfficeAddress3(httpservletrequest.getParameter("pracOfficeAddress3"));
		provider.setPracOfficeCity3(httpservletrequest.getParameter("pracOfficeCity3"));
		provider.setPracOfficeState3(httpservletrequest.getParameter("pracOfficeState3"));
		provider.setPracOfficeZip3(httpservletrequest.getParameter("pracOfficeZip3"));
		provider.setPracOfficeCounty3(httpservletrequest.getParameter("pracOfficeCounty3"));
		provider.setPracOfficePhone3(httpservletrequest.getParameter("pracOfficePhone3"));
		provider.setPracOfficeFax3(httpservletrequest.getParameter("pracOfficeFax3"));
		provider.setPracOfficeEmail3(httpservletrequest.getParameter("pracOfficeEmail3"));
		as = httpservletrequest.getParameterValues("billAddressSame3");
		s = getSelected(as);
		provider.setBillAddressSame3(s);
		provider.setPracBillContactName3(httpservletrequest.getParameter("pracBillContactName3"));
		if (s.equals("Y")) {
			provider.setPracBillAddress3(httpservletrequest.getParameter("pracOfficeAddress3"));
			provider.setPracBillCity3(httpservletrequest.getParameter("pracOfficeCity3"));
			provider.setPracBillState3(httpservletrequest.getParameter("pracOfficeState3"));
			provider.setPracBillZip3(httpservletrequest.getParameter("pracOfficeZip3"));
			provider.setPracBillCounty3(httpservletrequest.getParameter("pracOfficeCounty3"));
			provider.setPracBillPhone3(httpservletrequest.getParameter("pracOfficePhone3"));
			provider.setPracBillFax3(httpservletrequest.getParameter("pracOfficeFax3"));
			provider.setPracBillContactEmail3(httpservletrequest.getParameter("pracOfficeEmail3"));
		} else {
			provider.setPracBillAddress3(httpservletrequest.getParameter("pracBillAddress3"));
			provider.setPracBillCity3(httpservletrequest.getParameter("pracBillCity3"));
			provider.setPracBillState3(httpservletrequest.getParameter("pracBillState3"));
			provider.setPracBillZip3(httpservletrequest.getParameter("pracBillZip3"));
			provider.setPracBillCounty3(httpservletrequest.getParameter("pracBillCounty3"));
			provider.setPracBillPhone3(httpservletrequest.getParameter("pracBillPhone3"));
			provider.setPracBillFax3(httpservletrequest.getParameter("pracBillFax3"));
			provider.setPracBillContactEmail3(httpservletrequest.getParameter("pracBillContactEmail3"));
		}
		// 2013 SSCR 13503 change
		String rsa3[] = httpservletrequest.getParameterValues("remitSamePrim3");
		String rsa3Val = getSelected(rsa3);
		provider.setRemitSamePrim3(rsa3Val);
		if (rsa3Val.equals("Y")) {
			provider.setPracBillAddress3(provider.getPracBillAddress1());
			provider.setPracBillCity3(provider.getPracBillCity1());
			provider.setPracBillState3(provider.getPracBillState1());
			provider.setPracBillZip3(provider.getPracBillZip1());
			provider.setPracBillCounty3(provider.getPracBillCounty1());
			provider.setPracBillPhone3(provider.getPracBillPhone1());
			provider.setPracBillFax3(provider.getPracBillFax1());
			provider.setPracBillContactEmail3(provider.getPracBillContactEmail1());
			provider.setPracBillContactName3(provider.getPracBillContactName1());
		}
		/*
		 * else { provider.setPracBillAddress3(httpservletrequest.getParameter(
		 * "pracBillAddress3"));
		 * provider.setPracBillCity3(httpservletrequest.getParameter
		 * ("pracBillCity3"));
		 * provider.setPracBillState3(httpservletrequest.getParameter
		 * ("pracBillState3"));
		 * provider.setPracBillZip3(httpservletrequest.getParameter
		 * ("pracBillZip3"));
		 * provider.setPracBillCounty3(httpservletrequest.getParameter
		 * ("pracBillCounty3"));
		 * provider.setPracBillPhone3(httpservletrequest.getParameter
		 * ("pracBillPhone3"));
		 * provider.setPracBillFax3(httpservletrequest.getParameter
		 * ("pracBillFax3"));
		 * provider.setPracBillContactEmail3(httpservletrequest
		 * .getParameter("pracBillContactEmail3"));
		 * provider.setPracBillContactName3
		 * (httpservletrequest.getParameter("pracBillContactName3")); }
		 */// 2013 SSCR 13503 change end
		provider.setPracNPINo3(httpservletrequest.getParameter("pracNPINo3"));
		provider.setBillMedicareGroup3(httpservletrequest.getParameter("billMedicareGroup3"));
		provider.setBillMedicareIndividual3(httpservletrequest.getParameter("billMedicareIndividual3"));
		// Medicaid Changes
		provider.setMedicaidGroup3(httpservletrequest.getParameter("medicaidGroup3"));
		provider.setMedicaidIndividual3(httpservletrequest.getParameter("medicaidIndividual3"));

		// KentuckyMedicaid Changes
		provider.setKyMedicaidPart3(initialNullValues(httpservletrequest.getParameter("kyMedicaidPart3")));
		provider.setKyMedicaidId3(httpservletrequest.getParameter("kyMedicaidId3"));
		
		provider.setProvideTelehealth3(initialNullValues(httpservletrequest.getParameter("provideTelehealth3")));

		// Changes for the state mandate on 02/10/10 start
		provider.setLanguagesSpoken3(httpservletrequest.getParameter("languagesSpoken3"));
		provider.setOfferECI3(initialNullValues(httpservletrequest.getParameter("offerECI3")));
		provider.setOfferEPSDT3(initialNullValues(httpservletrequest.getParameter("offerEPSDT3")));
		provider.setProvideADB3(initialNullValues(httpservletrequest.getParameter("provideADB3")));
		provider.setProvideCSHCN3(initialNullValues(httpservletrequest.getParameter("provideCSHCN3")));
		// Changes for the state mandate on 02/10/10 end
		
		provider.setMatWaiveredPrescriber3(initialNullValues(httpservletrequest.getParameter("matWaiveredPrescriber3")));
		provider.setCertOpioidTreat3(initialNullValues(httpservletrequest.getParameter("certOpioidTreat3")));
		provider.setMatOpioid3(initialNullValues(httpservletrequest.getParameter("matOpioid3")));
		provider.setCounselOpioid3(initialNullValues(httpservletrequest.getParameter("counselOpioid3")));
		provider.setSudProv3(initialNullValues(httpservletrequest.getParameter("sudProv3")));
		provider.setResTreatCtr3(initialNullValues(httpservletrequest.getParameter("resTreatCtr3")));

		provider.setPracName4(httpservletrequest.getParameter("pracName4"));
		provider.setPracOfficeAddress4(httpservletrequest.getParameter("pracOfficeAddress4"));
		provider.setPracOfficeCity4(httpservletrequest.getParameter("pracOfficeCity4"));
		provider.setPracOfficeState4(httpservletrequest.getParameter("pracOfficeState4"));
		provider.setPracOfficeZip4(httpservletrequest.getParameter("pracOfficeZip4"));
		provider.setPracOfficeCounty4(httpservletrequest.getParameter("pracOfficeCounty4"));
		provider.setPracOfficePhone4(httpservletrequest.getParameter("pracOfficePhone4"));
		provider.setPracOfficeFax4(httpservletrequest.getParameter("pracOfficeFax4"));
		provider.setPracOfficeEmail4(httpservletrequest.getParameter("pracOfficeEmail4"));
		as = httpservletrequest.getParameterValues("billAddressSame4");
		s = getSelected(as);
		provider.setBillAddressSame4(s);
		provider.setPracBillContactName4(httpservletrequest.getParameter("pracBillContactName4"));
		if (s.equals("Y")) {
			provider.setPracBillAddress4(httpservletrequest.getParameter("pracOfficeAddress4"));
			provider.setPracBillCity4(httpservletrequest.getParameter("pracOfficeCity4"));
			provider.setPracBillState4(httpservletrequest.getParameter("pracOfficeState4"));
			provider.setPracBillZip4(httpservletrequest.getParameter("pracOfficeZip4"));
			provider.setPracBillCounty4(httpservletrequest.getParameter("pracOfficeCounty4"));
			provider.setPracBillPhone4(httpservletrequest.getParameter("pracOfficePhone4"));
			provider.setPracBillFax4(httpservletrequest.getParameter("pracOfficeFax4"));
			provider.setPracBillContactEmail4(httpservletrequest.getParameter("pracOfficeEmail4"));
		} else {
			provider.setPracBillAddress4(httpservletrequest.getParameter("pracBillAddress4"));
			provider.setPracBillCity4(httpservletrequest.getParameter("pracBillCity4"));
			provider.setPracBillState4(httpservletrequest.getParameter("pracBillState4"));
			provider.setPracBillZip4(httpservletrequest.getParameter("pracBillZip4"));
			provider.setPracBillCounty4(httpservletrequest.getParameter("pracBillCounty4"));
			provider.setPracBillPhone4(httpservletrequest.getParameter("pracBillPhone4"));
			provider.setPracBillFax4(httpservletrequest.getParameter("pracBillFax4"));
			provider.setPracBillContactEmail4(httpservletrequest.getParameter("pracBillContactEmail4"));
		}
		// 2013 SSCR 13503 change
		String rsa4[] = httpservletrequest.getParameterValues("remitSamePrim4");
		String rsa4Val = getSelected(rsa4);
		provider.setRemitSamePrim4(rsa4Val);
		if (rsa4Val.equals("Y")) {
			provider.setPracBillAddress4(provider.getPracBillAddress1());
			provider.setPracBillCity4(provider.getPracBillCity1());
			provider.setPracBillState4(provider.getPracBillState1());
			provider.setPracBillZip4(provider.getPracBillZip1());
			provider.setPracBillCounty4(provider.getPracBillCounty1());
			provider.setPracBillPhone4(provider.getPracBillPhone1());
			provider.setPracBillFax4(provider.getPracBillFax1());
			provider.setPracBillContactEmail4(provider.getPracBillContactEmail1());
			provider.setPracBillContactName4(provider.getPracBillContactName1());
		}
		/*
		 * else { provider.setPracBillAddress4(httpservletrequest.getParameter(
		 * "pracBillAddress4"));
		 * provider.setPracBillCity4(httpservletrequest.getParameter
		 * ("pracBillCity4"));
		 * provider.setPracBillState4(httpservletrequest.getParameter
		 * ("pracBillState4"));
		 * provider.setPracBillZip4(httpservletrequest.getParameter
		 * ("pracBillZip4"));
		 * provider.setPracBillCounty4(httpservletrequest.getParameter
		 * ("pracBillCounty4"));
		 * provider.setPracBillPhone4(httpservletrequest.getParameter
		 * ("pracBillPhone4"));
		 * provider.setPracBillFax4(httpservletrequest.getParameter
		 * ("pracBillFax4"));
		 * provider.setPracBillContactEmail4(httpservletrequest
		 * .getParameter("pracBillContactEmail4"));
		 * provider.setPracBillContactName4
		 * (httpservletrequest.getParameter("pracBillContactName4")); }
		 */// 2013 SSCR 13503 change end
		provider.setPracNPINo4(httpservletrequest.getParameter("pracNPINo4"));
		provider.setBillMedicareGroup4(httpservletrequest.getParameter("billMedicareGroup4"));
		provider.setBillMedicareIndividual4(httpservletrequest.getParameter("billMedicareIndividual4"));
		// Medicaid Changes
		provider.setMedicaidGroup4(httpservletrequest.getParameter("medicaidGroup4"));
		provider.setMedicaidIndividual4(httpservletrequest.getParameter("medicaidIndividual4"));

		// KentuckyMedicaid Changes
		provider.setKyMedicaidPart4(initialNullValues(httpservletrequest.getParameter("kyMedicaidPart4")));
		provider.setKyMedicaidId4(httpservletrequest.getParameter("kyMedicaidId4"));
		
		provider.setProvideTelehealth4(initialNullValues(httpservletrequest.getParameter("provideTelehealth4")));

		// Changes for the state mandate on 02/10/10 start
		provider.setLanguagesSpoken4(httpservletrequest.getParameter("languagesSpoken4"));
		provider.setOfferECI4(initialNullValues(httpservletrequest.getParameter("offerECI4")));
		provider.setOfferEPSDT4(initialNullValues(httpservletrequest.getParameter("offerEPSDT4")));
		provider.setProvideADB4(initialNullValues(httpservletrequest.getParameter("provideADB4")));
		provider.setProvideCSHCN4(initialNullValues(httpservletrequest.getParameter("provideCSHCN4")));
		// Changes for the state mandate on 02/10/10 end
		
		provider.setMatWaiveredPrescriber4(initialNullValues(httpservletrequest.getParameter("matWaiveredPrescriber4")));
		provider.setCertOpioidTreat4(initialNullValues(httpservletrequest.getParameter("certOpioidTreat4")));
		provider.setMatOpioid4(initialNullValues(httpservletrequest.getParameter("matOpioid4")));
		provider.setCounselOpioid4(initialNullValues(httpservletrequest.getParameter("counselOpioid4")));
		provider.setSudProv4(initialNullValues(httpservletrequest.getParameter("sudProv4")));
		provider.setResTreatCtr4(initialNullValues(httpservletrequest.getParameter("resTreatCtr4")));

		provider.setPracName5(httpservletrequest.getParameter("pracName5"));
		provider.setPracOfficeAddress5(httpservletrequest.getParameter("pracOfficeAddress5"));
		provider.setPracOfficeCity5(httpservletrequest.getParameter("pracOfficeCity5"));
		provider.setPracOfficeState5(httpservletrequest.getParameter("pracOfficeState5"));
		provider.setPracOfficeZip5(httpservletrequest.getParameter("pracOfficeZip5"));
		provider.setPracOfficeCounty5(httpservletrequest.getParameter("pracOfficeCounty5"));
		provider.setPracOfficePhone5(httpservletrequest.getParameter("pracOfficePhone5"));
		provider.setPracOfficeFax5(httpservletrequest.getParameter("pracOfficeFax5"));
		provider.setPracOfficeEmail5(httpservletrequest.getParameter("pracOfficeEmail5"));
		as = httpservletrequest.getParameterValues("billAddressSame5");
		s = getSelected(as);
		provider.setBillAddressSame5(s);
		provider.setPracBillContactName5(httpservletrequest.getParameter("pracBillContactName5"));
		if (s.equals("Y")) {
			provider.setPracBillAddress5(httpservletrequest.getParameter("pracOfficeAddress5"));
			provider.setPracBillCity5(httpservletrequest.getParameter("pracOfficeCity5"));
			provider.setPracBillState5(httpservletrequest.getParameter("pracOfficeState5"));
			provider.setPracBillZip5(httpservletrequest.getParameter("pracOfficeZip5"));
			provider.setPracBillCounty5(httpservletrequest.getParameter("pracOfficeCounty5"));
			provider.setPracBillPhone5(httpservletrequest.getParameter("pracOfficePhone5"));
			provider.setPracBillFax5(httpservletrequest.getParameter("pracOfficeFax5"));
			provider.setPracBillContactEmail5(httpservletrequest.getParameter("pracOfficeEmail5"));
		} else {
			provider.setPracBillAddress5(httpservletrequest.getParameter("pracBillAddress5"));
			provider.setPracBillCity5(httpservletrequest.getParameter("pracBillCity5"));
			provider.setPracBillState5(httpservletrequest.getParameter("pracBillState5"));
			provider.setPracBillZip5(httpservletrequest.getParameter("pracBillZip5"));
			provider.setPracBillCounty5(httpservletrequest.getParameter("pracBillCounty5"));
			provider.setPracBillPhone5(httpservletrequest.getParameter("pracBillPhone5"));
			provider.setPracBillFax5(httpservletrequest.getParameter("pracBillFax5"));
			provider.setPracBillContactEmail5(httpservletrequest.getParameter("pracBillContactEmail5"));
		}
		// 2013 SSCR 13503 change
		String rsa5[] = httpservletrequest.getParameterValues("remitSamePrim5");
		String rsa5Val = getSelected(rsa5);
		provider.setRemitSamePrim5(rsa5Val);
		if (rsa5Val.equals("Y")) {
			provider.setPracBillAddress5(provider.getPracBillAddress1());
			provider.setPracBillCity5(provider.getPracBillCity1());
			provider.setPracBillState5(provider.getPracBillState1());
			provider.setPracBillZip5(provider.getPracBillZip1());
			provider.setPracBillCounty5(provider.getPracBillCounty1());
			provider.setPracBillPhone5(provider.getPracBillPhone1());
			provider.setPracBillFax5(provider.getPracBillFax1());
			provider.setPracBillContactEmail5(provider.getPracBillContactEmail1());
			provider.setPracBillContactName5(provider.getPracBillContactName1());
		}
		/*
		 * else { provider.setPracBillAddress5(httpservletrequest.getParameter(
		 * "pracBillAddress5"));
		 * provider.setPracBillCity5(httpservletrequest.getParameter
		 * ("pracBillCity5"));
		 * provider.setPracBillState5(httpservletrequest.getParameter
		 * ("pracBillState5"));
		 * provider.setPracBillZip5(httpservletrequest.getParameter
		 * ("pracBillZip5"));
		 * provider.setPracBillCounty5(httpservletrequest.getParameter
		 * ("pracBillCounty5"));
		 * provider.setPracBillPhone5(httpservletrequest.getParameter
		 * ("pracBillPhone5"));
		 * provider.setPracBillFax5(httpservletrequest.getParameter
		 * ("pracBillFax5"));
		 * provider.setPracBillContactEmail5(httpservletrequest
		 * .getParameter("pracBillContactEmail5"));
		 * provider.setPracBillContactName5
		 * (httpservletrequest.getParameter("pracBillContactName5")); }
		 */// 2013 SSCR 13503 change end
		provider.setPracNPINo5(httpservletrequest.getParameter("pracNPINo5"));
		provider.setBillMedicareGroup5(httpservletrequest.getParameter("billMedicareGroup5"));
		provider.setBillMedicareIndividual5(httpservletrequest.getParameter("billMedicareIndividual5"));

		// Medicaid Changes
		provider.setMedicaidGroup5(httpservletrequest.getParameter("medicaidGroup5"));
		provider.setMedicaidIndividual5(httpservletrequest.getParameter("medicaidIndividual5"));

		// KentuckyMedicaid Changes
		provider.setKyMedicaidPart5(initialNullValues(httpservletrequest.getParameter("kyMedicaidPart5")));
		provider.setKyMedicaidId5(httpservletrequest.getParameter("kyMedicaidId5"));
		
		provider.setProvideTelehealth5(initialNullValues(httpservletrequest.getParameter("provideTelehealth5")));

		// Changes for the state mandate on 02/10/10 start
		provider.setLanguagesSpoken5(httpservletrequest.getParameter("languagesSpoken5"));
		provider.setOfferECI5(initialNullValues(httpservletrequest.getParameter("offerECI5")));
		provider.setOfferEPSDT5(initialNullValues(httpservletrequest.getParameter("offerEPSDT5")));
		provider.setProvideADB5(initialNullValues(httpservletrequest.getParameter("provideADB5")));
		provider.setProvideCSHCN5(initialNullValues(httpservletrequest.getParameter("provideCSHCN5")));
		// Changes for the state mandate on 02/10/10 end
		
		provider.setMatWaiveredPrescriber5(initialNullValues(httpservletrequest.getParameter("matWaiveredPrescriber5")));
		provider.setCertOpioidTreat5(initialNullValues(httpservletrequest.getParameter("certOpioidTreat5")));
		provider.setMatOpioid5(initialNullValues(httpservletrequest.getParameter("matOpioid5")));
		provider.setCounselOpioid5(initialNullValues(httpservletrequest.getParameter("counselOpioid5")));
		provider.setSudProv5(initialNullValues(httpservletrequest.getParameter("sudProv5")));
		provider.setResTreatCtr5(initialNullValues(httpservletrequest.getParameter("resTreatCtr5")));

		provider.setPracName6(httpservletrequest.getParameter("pracName6"));
		provider.setPracOfficeAddress6(httpservletrequest.getParameter("pracOfficeAddress6"));
		provider.setPracOfficeCity6(httpservletrequest.getParameter("pracOfficeCity6"));
		provider.setPracOfficeState6(httpservletrequest.getParameter("pracOfficeState6"));
		provider.setPracOfficeZip6(httpservletrequest.getParameter("pracOfficeZip6"));
		provider.setPracOfficeCounty6(httpservletrequest.getParameter("pracOfficeCounty6"));
		provider.setPracOfficePhone6(httpservletrequest.getParameter("pracOfficePhone6"));
		provider.setPracOfficeFax6(httpservletrequest.getParameter("pracOfficeFax6"));
		provider.setPracOfficeEmail6(httpservletrequest.getParameter("pracOfficeEmail6"));
		as = httpservletrequest.getParameterValues("billAddressSame6");
		s = getSelected(as);
		provider.setBillAddressSame6(s);
		provider.setPracBillContactName6(httpservletrequest.getParameter("pracBillContactName6"));
		if (s.equals("Y")) {
			provider.setPracBillAddress6(httpservletrequest.getParameter("pracOfficeAddress6"));
			provider.setPracBillCity6(httpservletrequest.getParameter("pracOfficeCity6"));
			provider.setPracBillState6(httpservletrequest.getParameter("pracOfficeState6"));
			provider.setPracBillZip6(httpservletrequest.getParameter("pracOfficeZip6"));
			provider.setPracBillCounty6(httpservletrequest.getParameter("pracOfficeCounty6"));
			provider.setPracBillPhone6(httpservletrequest.getParameter("pracOfficePhone6"));
			provider.setPracBillFax6(httpservletrequest.getParameter("pracOfficeFax6"));
			provider.setPracBillContactEmail6(httpservletrequest.getParameter("pracOfficeEmail6"));
		} else {
			// provider.setPracBillContactName6(httpservletrequest.getParameter("pracBillContactName6"));
			provider.setPracBillAddress6(httpservletrequest.getParameter("pracBillAddress6"));
			provider.setPracBillCity6(httpservletrequest.getParameter("pracBillCity6"));
			provider.setPracBillState6(httpservletrequest.getParameter("pracBillState6"));
			provider.setPracBillZip6(httpservletrequest.getParameter("pracBillZip6"));
			provider.setPracBillCounty6(httpservletrequest.getParameter("pracBillCounty6"));
			provider.setPracBillPhone6(httpservletrequest.getParameter("pracBillPhone6"));
			provider.setPracBillFax6(httpservletrequest.getParameter("pracBillFax6"));
			provider.setPracBillContactEmail6(httpservletrequest.getParameter("pracBillContactEmail6"));
		}
		// 2013 SSCR 13503 change
		String rsa6[] = httpservletrequest.getParameterValues("remitSamePrim6");
		String rsa6Val = getSelected(rsa6);
		provider.setRemitSamePrim6(rsa6Val);
		if (rsa6Val.equals("Y")) {
			provider.setPracBillAddress6(provider.getPracBillAddress1());
			provider.setPracBillCity6(provider.getPracBillCity1());
			provider.setPracBillState6(provider.getPracBillState1());
			provider.setPracBillZip6(provider.getPracBillZip1());
			provider.setPracBillCounty6(provider.getPracBillCounty1());
			provider.setPracBillPhone6(provider.getPracBillPhone1());
			provider.setPracBillFax6(provider.getPracBillFax1());
			provider.setPracBillContactEmail6(provider.getPracBillContactEmail1());
			provider.setPracBillContactName6(provider.getPracBillContactName1());
		}
		/*
		 * else { provider.setPracBillAddress6(httpservletrequest.getParameter(
		 * "pracBillAddress6"));
		 * provider.setPracBillCity6(httpservletrequest.getParameter
		 * ("pracBillCity6"));
		 * provider.setPracBillState6(httpservletrequest.getParameter
		 * ("pracBillState6"));
		 * provider.setPracBillZip6(httpservletrequest.getParameter
		 * ("pracBillZip6"));
		 * provider.setPracBillCounty6(httpservletrequest.getParameter
		 * ("pracBillCounty6"));
		 * provider.setPracBillPhone6(httpservletrequest.getParameter
		 * ("pracBillPhone6"));
		 * provider.setPracBillFax6(httpservletrequest.getParameter
		 * ("pracBillFax6"));
		 * provider.setPracBillContactEmail6(httpservletrequest
		 * .getParameter("pracBillContactEmail6"));
		 * provider.setPracBillContactName6
		 * (httpservletrequest.getParameter("pracBillContactName6")); }
		 */// 2013 SSCR 13503 change end
		provider.setPracNPINo6(httpservletrequest.getParameter("pracNPINo6"));

		// Medicaid Changes
		provider.setMedicaidGroup6(httpservletrequest.getParameter("medicaidGroup6"));
		provider.setMedicaidIndividual6(httpservletrequest.getParameter("medicaidIndividual6"));

		// KentuckyMedicaid Changes
		provider.setKyMedicaidPart6(initialNullValues(httpservletrequest.getParameter("kyMedicaidPart6")));
		provider.setKyMedicaidId6(httpservletrequest.getParameter("kyMedicaidId6"));

		provider.setProvideTelehealth6(initialNullValues(httpservletrequest.getParameter("provideTelehealth6")));
		
		// Changes for the state mandate on 02/10/10 start
		provider.setLanguagesSpoken6(httpservletrequest.getParameter("languagesSpoken6"));
		provider.setOfferECI6(initialNullValues(httpservletrequest.getParameter("offerECI6")));
		provider.setOfferEPSDT6(initialNullValues(httpservletrequest.getParameter("offerEPSDT6")));
		provider.setProvideADB6(initialNullValues(httpservletrequest.getParameter("provideADB6")));
		provider.setProvideCSHCN6(initialNullValues(httpservletrequest.getParameter("provideCSHCN6")));
		// Changes for the state mandate on 02/10/10 end
		
		provider.setMatWaiveredPrescriber6(initialNullValues(httpservletrequest.getParameter("matWaiveredPrescriber6")));
		provider.setCertOpioidTreat6(initialNullValues(httpservletrequest.getParameter("certOpioidTreat6")));
		provider.setMatOpioid6(initialNullValues(httpservletrequest.getParameter("matOpioid6")));
		provider.setCounselOpioid6(initialNullValues(httpservletrequest.getParameter("counselOpioid6")));
		provider.setSudProv6(initialNullValues(httpservletrequest.getParameter("sudProv6")));
		provider.setResTreatCtr6(initialNullValues(httpservletrequest.getParameter("resTreatCtr6")));

		if (getSelectedValueForMultipart(httpservletrequest, "children").equals("Y"))
			provider.getPatientInfo().add("children");
		if (getSelectedValueForMultipart(httpservletrequest, "adolescents").equals("Y"))
			provider.getPatientInfo().add("adolescents");
		if (getSelectedValueForMultipart(httpservletrequest, "adults").equals("Y"))
			provider.getPatientInfo().add("adults");
		if (getSelectedValueForMultipart(httpservletrequest, "senior").equals("Y"))
			provider.getPatientInfo().add("senior");
		if (getSelectedValueForMultipart(httpservletrequest, "deaf").equals("Y"))
			provider.getPatientInfo().add("deaf");
		if (getSelectedValueForMultipart(httpservletrequest, "disabled").equals("Y"))
			provider.getPatientInfo().add("disabled");

		provider.setNonPsychEval(initialNullValues(httpservletrequest.getParameter("nonPsychEval")));

		AreasOfExpertise expertise = provider.getAreasOfExpertise();
		expertise.setAdd(getSelectedBooleanValueForMultipart(httpservletrequest, "add"));
		expertise.setDialectical(getSelectedBooleanValueForMultipart(httpservletrequest, "dialectical"));
		expertise.setNeuropsychological(getSelectedBooleanValueForMultipart(httpservletrequest, "neuropsychological"));
		expertise.setAdoption(getSelectedBooleanValueForMultipart(httpservletrequest, "adoption"));
		expertise.setDivorce(getSelectedBooleanValueForMultipart(httpservletrequest, "divorce"));
		expertise.setAnxiety(getSelectedBooleanValueForMultipart(httpservletrequest, "anxiety"));
		expertise.setDomViolence(getSelectedBooleanValueForMultipart(httpservletrequest, "domViolence"));
		expertise.setObsessiveComp(getSelectedBooleanValueForMultipart(httpservletrequest, "obsessiveComp"));
		expertise.setAutism(getSelectedBooleanValueForMultipart(httpservletrequest, "autism"));
		expertise.setEatingDisorder(getSelectedBooleanValueForMultipart(httpservletrequest, "eatingDisorder"));
		expertise.setPainMgmt(getSelectedBooleanValueForMultipart(httpservletrequest, "painMgmt"));
		expertise.setBariatric(getSelectedBooleanValueForMultipart(httpservletrequest, "bariatric"));
		expertise.setElectroconvulsive(getSelectedBooleanValueForMultipart(httpservletrequest, "electroconvulsive"));
		expertise.setPersonalityDis(getSelectedBooleanValueForMultipart(httpservletrequest, "personalityDis"));
		expertise.setBehaviorMod(getSelectedBooleanValueForMultipart(httpservletrequest, "behaviorMod"));
		expertise.setEndofLifeIssues(getSelectedBooleanValueForMultipart(httpservletrequest, "endofLifeIssues"));
		expertise.setPostpartum(getSelectedBooleanValueForMultipart(httpservletrequest, "postpartum"));
		expertise.setBipolar(getSelectedBooleanValueForMultipart(httpservletrequest, "bipolar"));
		expertise.setFamilyTherapy(getSelectedBooleanValueForMultipart(httpservletrequest, "familyTherapy"));
		expertise.setPTSD(getSelectedBooleanValueForMultipart(httpservletrequest, "PTSD"));
		expertise.setBriefSolution(getSelectedBooleanValueForMultipart(httpservletrequest, "briefSolution"));
		expertise.setGayIssues(getSelectedBooleanValueForMultipart(httpservletrequest, "gayIssues"));
		expertise.setPrenatalIssues(getSelectedBooleanValueForMultipart(httpservletrequest, "prenatalIssues"));
		expertise.setChemicalDep(getSelectedBooleanValueForMultipart(httpservletrequest, "chemicalDep"));
		expertise.setGroupTherapy(getSelectedBooleanValueForMultipart(httpservletrequest, "groupTherapy"));
		expertise.setPsychologicalTest(getSelectedBooleanValueForMultipart(httpservletrequest, "psychologicalTest"));
		expertise.setChemicalDepAssessment(getSelectedBooleanValueForMultipart(httpservletrequest,
				"chemicalDepAssessment"));
		expertise.setHIVRelatedIssues(getSelectedBooleanValueForMultipart(httpservletrequest, "HIVRelatedIssues"));
		expertise
				.setChristianCounseling(getSelectedBooleanValueForMultipart(httpservletrequest, "christianCounseling"));
		expertise.setInfertility(getSelectedBooleanValueForMultipart(httpservletrequest, "infertility"));
		expertise.setSchizophrenia(getSelectedBooleanValueForMultipart(httpservletrequest, "schizophrenia"));
		expertise.setCompulsiveGambling(getSelectedBooleanValueForMultipart(httpservletrequest, "compulsiveGambling"));
		expertise.setMedicationMgmt(getSelectedBooleanValueForMultipart(httpservletrequest, "medicationMgmt"));
		expertise.setSexualAbuse(getSelectedBooleanValueForMultipart(httpservletrequest, "sexualAbuse"));
		expertise.setEthnicIssues(getSelectedBooleanValueForMultipart(httpservletrequest, "ethnicIssues"));
		expertise.setSexualDisorder(getSelectedBooleanValueForMultipart(httpservletrequest, "sexualDisorder"));
		expertise.setDepressiveDisorder(getSelectedBooleanValueForMultipart(httpservletrequest, "depressiveDisorder"));
		expertise.setMenIssues(getSelectedBooleanValueForMultipart(httpservletrequest, "menIssues"));
		expertise.setVictims(getSelectedBooleanValueForMultipart(httpservletrequest, "victims"));
		expertise.setWomenIssues(getSelectedBooleanValueForMultipart(httpservletrequest, "womenIssues"));
		expertise.setCounselOpioid(getSelectedBooleanValueForMultipart(httpservletrequest, "counselOpioid"));
		expertise.setMatOpioid(getSelectedBooleanValueForMultipart(httpservletrequest, "matOpioid"));
		expertise.setMatWaivered(getSelectedBooleanValueForMultipart(httpservletrequest, "matWaivered"));
		expertise.setTelehealth(getSelectedBooleanValueForMultipart(httpservletrequest, "telehealthProvAOE"));

		provider.setAreasOfExpertise(expertise);

	}

	private static boolean getSelectedBooleanValue(HttpServletRequest httpservletrequest, String s) {
		String as[] = httpservletrequest.getParameterValues(s);
		String val = getSelected(as);
		if (val.equals("Y"))
			return true;
		else
			return false;
	}

	private static boolean getSelectedBooleanValueForMultipart(MultipartFormDataRequest httpservletrequest, String s) {
		String as[] = httpservletrequest.getParameterValues(s);
		String val = getSelected(as);
		if (val.equals("Y"))
			return true;
		else
			return false;
	}

	private static String getSelected(String as[]) {
		String s = "";
		if (as != null && as.length != 0) {
			for (int i = 0; i < as.length; i++)
				if (!as[i].equals("") || !as[i].equals(" "))
					s = "Y";
				else
					s = "";

		}
		return s;
	}

	private static String getSelectedValue(HttpServletRequest httpservletrequest, String s) {

		String as[] = httpservletrequest.getParameterValues(s);
		return getSelected(as);
	}

	private static String getSelectedValueForMultipart(MultipartFormDataRequest httpservletrequest, String s) {

		String as[] = httpservletrequest.getParameterValues(s);
		return getSelected(as);
	}

	public static void setCAPMFFormData(NYVAProvMaintFormBean caPmfForm, ServiceRequest serviceRequest) {
		// No email body for CA PMF
		caPmfForm.setEmailBody("NA");
		if (serviceRequest.getGeneralInformationIndividualProvider() != null) {
			if (serviceRequest.getGeneralInformationIndividualProvider().getProviderDetails() != null) {
				if (!StringUtils.isEmpty(serviceRequest.getGeneralInformationIndividualProvider().getProviderDetails()
						.getEffectiveDate())) {
					caPmfForm.setEffDate(serviceRequest.getGeneralInformationIndividualProvider().getProviderDetails()
							.getEffectiveDate());
				}
				if (!StringUtils.isEmpty(serviceRequest.getGeneralInformationIndividualProvider().getProviderDetails()
						.getFirstName())) {
					caPmfForm.setProvFirstName(serviceRequest.getGeneralInformationIndividualProvider()
							.getProviderDetails().getFirstName());
				}
				if (!StringUtils.isEmpty(serviceRequest.getGeneralInformationIndividualProvider().getProviderDetails()
						.getLastName())) {
					caPmfForm.setProvLastName(serviceRequest.getGeneralInformationIndividualProvider()
							.getProviderDetails().getLastName());
				}
				if (!StringUtils.isEmpty(serviceRequest.getGeneralInformationIndividualProvider().getProviderDetails()
						.getIndividualNPI())) {
					caPmfForm.setProvNPI(serviceRequest.getGeneralInformationIndividualProvider().getProviderDetails()
							.getIndividualNPI());
				}
				if (!StringUtils.isEmpty(serviceRequest.getGeneralInformationIndividualProvider().getProviderDetails()
						.getIndividualTaxId())) {
					caPmfForm.setProvTaxId(serviceRequest.getGeneralInformationIndividualProvider()
							.getProviderDetails().getIndividualTaxId());
				}
			}
			if (serviceRequest.getGeneralInformationIndividualProvider().getContactInfo() != null) {
				if (!StringUtils.isEmpty(serviceRequest.getGeneralInformationIndividualProvider().getContactInfo()
						.getFirstName())) {
					caPmfForm.setContactFirstName(serviceRequest.getGeneralInformationIndividualProvider()
							.getContactInfo().getFirstName());
				}
				if (!StringUtils.isEmpty(serviceRequest.getGeneralInformationIndividualProvider().getContactInfo()
						.getLastName())) {
					caPmfForm.setContactLastName(serviceRequest.getGeneralInformationIndividualProvider()
							.getContactInfo().getLastName());
				}
				if (!StringUtils.isEmpty(serviceRequest.getGeneralInformationIndividualProvider().getContactInfo()
						.getEmail())) {
					caPmfForm.setContactEmail(serviceRequest.getGeneralInformationIndividualProvider().getContactInfo()
							.getEmail());
				}
				if (!StringUtils.isEmpty(serviceRequest.getGeneralInformationIndividualProvider().getContactInfo()
						.getPhoneNumber())) {
					caPmfForm.setContactPhone(serviceRequest.getGeneralInformationIndividualProvider().getContactInfo()
							.getPhoneNumber());
				}
			}

		} else if (serviceRequest.getGeneralInformationOrganizationProvider() != null) {

			if (serviceRequest.getGeneralInformationOrganizationProvider().getOrganizationDetails() != null) {
				if (!StringUtils.isEmpty(serviceRequest.getGeneralInformationOrganizationProvider()
						.getOrganizationDetails().getEffectiveDate())) {
					caPmfForm.setEffDate(serviceRequest.getGeneralInformationOrganizationProvider()
							.getOrganizationDetails().getEffectiveDate());
				}
				if (!StringUtils.isEmpty(serviceRequest.getGeneralInformationOrganizationProvider()
						.getOrganizationDetails().getOrganizationName())) {
					caPmfForm.setPracName(serviceRequest.getGeneralInformationOrganizationProvider()
							.getOrganizationDetails().getOrganizationName());
				}
				if (!StringUtils.isEmpty(serviceRequest.getGeneralInformationOrganizationProvider()
						.getOrganizationDetails().getOrganizationNPI())) {
					caPmfForm.setPracNPI(serviceRequest.getGeneralInformationOrganizationProvider()
							.getOrganizationDetails().getOrganizationNPI());
				}
				if (!StringUtils.isEmpty(serviceRequest.getGeneralInformationOrganizationProvider()
						.getOrganizationDetails().getOrganizationTaxId())) {
					caPmfForm.setPracTaxId(serviceRequest.getGeneralInformationOrganizationProvider()
							.getOrganizationDetails().getOrganizationTaxId());
				}
			}
			if (serviceRequest.getGeneralInformationOrganizationProvider().getContactInfo() != null) {
				if (!StringUtils.isEmpty(serviceRequest.getGeneralInformationOrganizationProvider().getContactInfo()
						.getFirstName())) {
					caPmfForm.setContactFirstName(serviceRequest.getGeneralInformationOrganizationProvider()
							.getContactInfo().getFirstName());
				}
				if (!StringUtils.isEmpty(serviceRequest.getGeneralInformationOrganizationProvider().getContactInfo()
						.getLastName())) {
					caPmfForm.setContactLastName(serviceRequest.getGeneralInformationOrganizationProvider()
							.getContactInfo().getLastName());
				}
				if (!StringUtils.isEmpty(serviceRequest.getGeneralInformationOrganizationProvider().getContactInfo()
						.getEmail())) {
					caPmfForm.setContactEmail(serviceRequest.getGeneralInformationOrganizationProvider()
							.getContactInfo().getEmail());
				}
				if (!StringUtils.isEmpty(serviceRequest.getGeneralInformationOrganizationProvider().getContactInfo()
						.getPhoneNumber())) {
					caPmfForm.setContactPhone(serviceRequest.getGeneralInformationOrganizationProvider()
							.getContactInfo().getPhoneNumber());
				}
			}
		}

		if (serviceRequest.getAttachments() != null) {
			List<UploadDocListBean> attachments = new ArrayList<UploadDocListBean>();
			for (Attachment attachment : serviceRequest.getAttachments()) {
				UploadDocListBean doc = new UploadDocListBean();
				doc.setFileName(attachment.getFileName());
				doc.setFileSize(attachment.getFileSize());
				doc.setDocComment(attachment.getFileComments());
				attachments.add(doc);
			}
			caPmfForm.setUploadFileListDtls(attachments);
		}
	}

	public static void setCAPMFFormAttachments(PMFPayload pmfPayload, NYVAProvMaintFormBean caPmfForm) {
		if (caPmfForm.getUploadFileListDtls() != null) {
			for (UploadDocListBean attachment : caPmfForm.getUploadFileListDtls()) {
				if (!StringUtils.isEmpty(attachment.getFileName()) && !StringUtils.isEmpty(attachment.getDcn())) {
					logger.info("Populating DCN :: ");
					for (Attachment formAttachment : pmfPayload.getServicePayload().getServiceRequest()
							.getAttachments()) {
						if (formAttachment.getFileName().equalsIgnoreCase(attachment.getFileName())) {
							formAttachment.setDocumentContentKey(attachment.getDcn());
							break;
						}
					}
				}
			}
		}
	}

	private static String initialNullValues(String s) {
		if (s == null) {
			s = "";
		}
		return s;
	}
}
