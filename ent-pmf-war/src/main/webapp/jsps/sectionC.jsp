<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page language="java" %>
<%@ page import="com.anthem.mwpmf.PMFUtils" %>
<%@ page import="org.owasp.esapi.ESAPI" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!--BEGIN FORM SECTION C -->
<table BORDER="1" CELLSPACING=0 CELLPADDING=7 WIDTH="100%">
						<tr>
							<td valign="top" align="center" colspan="2" BGCOLOR="#000080"><font size="2" COLOR="#ffffff"><b><%=Constants.sectionProviderInfo%> -- Provider Information <i>(Most fields required)</i></b></font></td>
						</tr>
						<%	Iterator theDataC = errorMessagesVectorC.iterator();
							if (!errorMessagesVectorC.isEmpty())
							{%>
								<tr>
									<td valign="top" align="center" colspan="2" BGCOLOR="#FF0000"><font size="2" COLOR="#ffffff"><b>Error Messages</b></font></td>
								</tr>
								<tr>
									<td colspan="2" BGCOLOR="#ffffff"><font size="2" COLOR="#000080"><b>The following errors were detected.  Please correct the form entries and resubmit form.</b></font></td>
								</tr>
							<%	while(theDataC.hasNext())
								{
									String value = (String)theDataC.next(); %>
									<tr>
										<TD colspan="2" style="FONT-SIZE: large;"><font size="2" COLOR="#FF0000"><b><%=value%></b></font></TD>
									</tr>
							<%	}  %>
						<%	} %>
						<tr>
							<td valign="top" BGCOLOR="#ffffff" width="5%"><font size="2" COLOR="#000080"><b>1.&nbsp;&nbsp;&nbsp;</b></td>
							<td BGCOLOR="#ffffff" width="95%"><font size="2" COLOR="#000080"><b>Include provider name (name submitted must match name format on 1500 HCFA form), title, Social Security number, date of birth, gender, specialty, professional license number and CAQH id (specific to Credentialing).</b></font></td>
						</tr>

						<tr>
							<td valign="top" BGCOLOR="#ffffff" width="5%"><font size="2" COLOR="#000080"><b>2.&nbsp;&nbsp;&nbsp;</b></td>
							<td BGCOLOR="#ffffff" width="95%"><font size="2" COLOR="#000080"><b>To update multiple providers, complete and submit this form for each provider.</b></font></td>
						</tr>

						<tr>
							<td valign="top" BGCOLOR="#ffffff" width="5%"><font size="2" COLOR="#000080"><b>3.&nbsp;&nbsp;&nbsp;</b></td>
							<td BGCOLOR="#ffffff" width="95%"><font size="2" COLOR="#000080"><b>* indicates required fields for physician update.</b></font></td>
						</tr>

						<tr>
							<td colspan="2">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<TD valign="bottom">* Provider First Name:</TD>
										<TD><input type="text" size="35" maxlength="35" name="provFnm" value="<%=ESAPI.encoder().encodeForHTMLAttribute(provFnm)%>"></TD>
									</tr>

									<tr>
										<TD valign="bottom">  Provider MI:</TD>
										<TD><input type="text" size="1" maxlength="1" name="provMI" value="<%=ESAPI.encoder().encodeForHTMLAttribute(provMI)%>"></TD>
									</tr>

									<tr>
										<TD valign="bottom">* Provider Last Name:</TD>
										<TD><input type="text" size="35" maxlength="35" name="provLnm" value="<%=ESAPI.encoder().encodeForHTMLAttribute(provLnm)%>"></TD>
									</tr>

									<tr>
										<TD valign="bottom">* Title (MD/DO/etc.):</TD>
										<TD><input type="text" size="10" maxlength="10" name="title" value="<%=ESAPI.encoder().encodeForHTMLAttribute(title)%>"></TD>
									</tr>
								</table>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td colspan="2">&nbsp;</td>
									</tr>
									<tr>
										<TD valign="bottom">Please select one of the following specialties:</TD>
										<TD>&nbsp;</TD>
									</tr>

									<tr>
										<TD>&nbsp;&nbsp;&nbsp;&nbsp;<input type= "radio"  name="specialityInfo" value="primSpecialtyPhy"
										
										<%if (providerData.getPrimSpecialtyPhy().equals("primSpecialtyPhy")) {%>CHECKED <%}%>/>Primary Specialty Physician (i.e. FPR, INM)	
										</TD>
										<TD><input type="radio"  name="specialityInfo" value="specialtyCarePhy"
										 <%if (providerData.getSpecialtyCarePhy().equals("specialtyCarePhy")) {%>CHECKED <%}%>/> Specialty Care Physician (i.e. Cardiology, Gen. Surg.) </TD>
									</tr>
									<!-- added clear celection option -->
									<tr>
										<TD>&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"   name="specialityInfo" value="other"
										<%if (providerData.getOther().equals("other")) {%>CHECKED <%}%>/>Other (i.e. PA, CRNA, CNM) </TD>

										<!-- WLPRD00971518 | Fix for Clear Selection | UST Global | 2014 October -->
										<TD><input type="radio"   name="specialityInfo" value="<%=clearSelection%>"
										<%if (providerData.getClearSelection().equals(clearSelection)) {%>CHECKED <%}%>/> Clear Selection </TD>
									</tr>
									<!-- added clear celection option -->
								</table>
								<table width =100%>
								<tr><td width =35%> Please choose your appropriate specialty:</td>
								<td >
									<select name="specialty" id="specialty">
										<%for(int cntrSpeciality=0;cntrSpeciality<Constants.SPECIALITY_LIST.length;cntrSpeciality++) { %>
										<option value="<%=Constants.SPECIALITY_LIST[cntrSpeciality] %>" <%if(Constants.SPECIALITY_LIST[cntrSpeciality].equals(specialty)){ %> selected <%} %>><%=Constants.SPECIALITY_LIST[cntrSpeciality] %></option>
										<%} %>
									</select>
								</td>
												<td>&nbsp;</td></tr>
								<!-- changing chek box to radio button -->
								<tr>
									<td>Are you a:</td>
									<td colspan="2">
									<table width="100%" cellspacing="10">
										<tr>
											<td width="20%" nowrap="nowrap"><input type="radio" name=locationType value="Locum Tenen"
												<%if (providerData.getLocationType().equals("Locum Tenen")) {%>
												CHECKED <%}%>/>Locum Tenen</TD>
											<td width="30%" nowrap="nowrap"><input type="radio" name=locationType
												value="Hospital Based Physician"
												<%if (providerData.getLocationType().equals(
												"Hospital Based Physician")) {%>
												CHECKED <%}%>/>Hospital Based Physician</TD>
											<TD width="15%"><input type="radio" name=locationType value="Hospitalist"
												<%if (providerData.getLocationType().equals("Hospitalist")) {%>
												CHECKED <%}%>/>Hospitalist</TD>
											<!-- WLPRD00971518 | Fix for Clear Selection | UST Global | 2014 October -->	
											<TD width="25%"><input type="radio" name=locationType value="<%=clearSelection%>"
												<%if (providerData.getLocationType().equals(clearSelection)) {%>
												CHECKED <%}%>/>Clear Selection</TD>
										</tr>
									</table>
									</td>
								</tr>
								<!-- changing chek box to radio button -->
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
								</table>
								<!-- 2013 SSCR 13503 changes
								<table width="85%" border="0" cellspacing="0" cellpadding="0">
								    <tr>
									  <TD align="bottom">Taxonomy Number:</TD>
									  <TD> <input type="text" size="10" maxlength="10" name="taxonomyNum" value="<%=taxonomyNum%>"> </TD>
								    </tr>
									<tr>
										<td colspan="2">&nbsp;</td>
									</tr>
								 //Changes for the state mandate on 02/10/10 start 
								</table>     -->
									
    								<table width="85%" border="0" cellspacing="0" cellpadding="0">
								    <!-- //Changes for the state mandate on 02/10/10 end -->
								    
								    <tr>
										<TD valign="bottom">Is the provider Board Certified for the specialty listed?</TD>
										<TD><input type="radio" name=boardCertified value="<%=Yes%>"   <%if(boardCertified.equals(Yes)){%>CHECKED <%}%>>Yes
                                            <input type="radio" name=boardCertified value="<%=No%>" <%if(boardCertified.equals(No)) {%>CHECKED <%}%>>No
                                            <input type="radio" name=boardCertified value="<%=NA%>" <%if(boardCertified.equals(NA)) {%>CHECKED <%}%>>Not applicable to specialty</TD>
								   </tr>
								   <tr>
										<td colspan="2">&nbsp;</td>
									</tr>
								<tr>
									<TD valign="bottom">If no, when will you be sitting for the exam?</TD>
									<TD><input type="text" size="8" maxlength="8" name="certExamDT" value="<%=ESAPI.encoder().encodeForHTMLAttribute(certExamDT)%>">(mmddyyyy)</TD>
								</tr>
								
								<!-- //Changes for the state mandate on 02/10/10 start -->								
								<table width="40%" border="0" cellspacing="0" cellpadding="0">
									<tr>
									<br>
										Radiology Office Based Setting if applicable:
									</tr>
									<tr>
										<td>&nbsp;&nbsp;<input type="checkbox" name=radHospBased value="" <%if(providerData.getRadHospBased().equals("Y")){%>CHECKED <%}%>>Hospital Based Setting </td>
										<td>&nbsp;&nbsp;<input type="checkbox" name=radFreeStandingCenter value="" <%if(providerData.getRadFreeStandingCenter().equals("Y")){%>CHECKED <%}%>>Free Standing Center </td>
									</tr>
								
								</table>
								
								<!-- //Changes for the state mandate on 02/10/10 end -->
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
										<TD valign="bottom">* Social Security Number:</TD>
										<TD><input type="text" size="9" maxlength="9" name="provNumber" value="<%=ESAPI.encoder().encodeForHTMLAttribute(provNumber)%>">(999999999)</TD>
								</tr>
								<tr>
									<TD align="bottom">* National Provider Identification Number:</TD>
									<TD> <input type="text" size="10" maxlength="10" name="provNpiNumber" value="<%=ESAPI.encoder().encodeForHTMLAttribute(provNpiNumber)%>"></TD>
								</tr>
								<!--<tr>
									<TD valign="bottom">UPIN Number:</TD>
									<TD><input type="text" size="7" maxlength="7" name="upinNumber" value="<%//=upinNumber%>"></TD>
								</tr>-->

								<tr>
									<TD valign="bottom">* Professional License Number:</TD>
									<TD><input type="text" size="15" maxlength="15" name="profLicenseNumber" value="<%=ESAPI.encoder().encodeForHTMLAttribute(profLicenseNumber)%>"></TD>
								</tr>
								<!-- 2013 SSCR 13503 changes
								<tr>
										<TD valign="bottom">State License Number Issue Date:</TD>
										<TD><input type="text" size="8" maxlength="8" name="stateLicIssueDt" value="<%=ESAPI.encoder().encodeForHTMLAttribute(stateLicIssueDt)%>">(mmddyyyy)</TD> 
								</tr>
								<tr>
										<TD valign="bottom">State License Number Expiration Date:</TD>
										<TD><input type="text" size="8" maxlength="8" name="stateLicExpDt" value="<%=ESAPI.encoder().encodeForHTMLAttribute(stateLicExpDt)%>">(mmddyyyy)</TD> 
								</tr>  -->
								<!-- for medicare opt out -->
								<tr>
								<td>&nbsp;</td>
								</tr>
								<tr>
									<TD valign="bottom"> Medicare Opt Out:</TD>
								</tr>
								<tr>
									<TD valign="bottom">&nbsp;&nbsp;&nbsp;&nbsp; Does this provider participate in Traditional Medicare? </TD>
									<TD><input type="radio" name=medicarePartTrad value="<%=Yes%>" <%if(medicarePartTrad.equals(Yes)){%>CHECKED <%}%>>Yes
									<input type="radio" name=medicarePartTrad value="<%=No%>" <%if(!medicarePartTrad.equals(Yes)){%>CHECKED <%}%>>No</TD>
								</tr>
								<tr>
									<TD valign="bottom">&nbsp;&nbsp;&nbsp;&nbsp;Applied Date:</TD>
									<TD><input type="text" size="8" maxlength="8" name="medicareApplDt" value="<%=ESAPI.encoder().encodeForHTMLAttribute(medicareApplDt) %>">(mmddyyyy or N/A)</TD> 
								</tr>
								<tr>
									<TD valign="bottom">&nbsp;&nbsp;&nbsp;&nbsp; Have they opted out of Medicare? </TD>
									<TD><input type="radio" name=medicareOptOut value="<%=Yes%>"   <%if(medicareOptOut.equals(Yes)){%>CHECKED <%}%>>Yes
										<input type="radio" name=medicareOptOut value="<%=No%>"   <%if(!medicareOptOut.equals(Yes)){%>CHECKED <%}%>>No</TD>
								</tr>
								<tr>
									<TD valign="bottom"> &nbsp;&nbsp;&nbsp;&nbsp;Effective Date of Medicare opt out:</TD>
									<TD><input type="text" size="8" maxlength="8" name="medicareOptOutDt" value="<%=ESAPI.encoder().encodeForHTMLAttribute(medicareOptOutDt) %>">(mmddyyyy or N/A)</TD> 
								</tr>
								<!-- End of medicare opt out -->
								<tr>
									<TD valign="bottom">CAQH ID Number:</TD>
									<TD><input type="text" size="12" maxlength="12" name="caqhIDNumber" value="<%=ESAPI.encoder().encodeForHTMLAttribute(caqhIDNumber)%>"></TD>
								</tr>
								<!--<tr>
									<TD valign="bottom">Current Status of CAQH application: briefly explain:</TD>
									<TD><input type="text" size="55" maxlength="55" name="caqhExplanation" value="<%//=caqhExplanation%>"></TD>
								</tr>-->

								<tr>
									<TD valign="bottom">* Date of Birth:</TD>
									<TD><input type="text" size="8" maxlength="8" name="provDOB" value="<%=ESAPI.encoder().encodeForHTMLAttribute(provDOB)%>">(mmddyyyy)</TD>
								</tr>

								<tr>
									<td height="23" valign="bottom">* Gender:</td>
									<td height="23"><input type="radio" name=provGender value="<%=genderMale%>"   <%if(!provGender.equals(genderFemale)){%>CHECKED <%}%>>Male
                                    				<input type="radio" name=provGender value="<%=genderFemale%>" <%if(provGender.equals(genderFemale)) {%>CHECKED <%}%>>Female</td>

								</tr>
								<tr>
										<TD valign="bottom">Accepting new patients?</TD>
										<TD><input type="radio" name=newPatients value="<%=Yes%>"   <%if(!newPatients.equals(No)){%>CHECKED <%}%>>Yes
                                            <input type="radio" name=newPatients value="<%=No%>" <%if(newPatients.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								<!-- moving fields from hip medicaid -->
								<tr>
									<td >*Age Restriction:</td>
									<td colspan="2"><select name="ageRestriction">
										<option value=""></option>
										<option value="None" <%if ("None".equals(ageRestriction)) {%>
											selected <%}%>>None</option>
										<option value="0-2" <%if ("0-2".equals(ageRestriction)) {%> selected
											<%}%>>0-2</option>
										<option value="0-12" <%if ("0-12".equals(ageRestriction)) {%>
											selected <%}%>>0-12</option>
										<option value="0-17" <%if ("0-17".equals(ageRestriction)) {%>
											selected <%}%>>0-17</option>
										<option value="0-20" <%if ("0-20".equals(ageRestriction)) {%>
											selected <%}%>>0-20</option>
										<option value="13-17" <%if ("13-17".equals(ageRestriction)) {%>
											selected <%}%>>13-17</option>
										<option value="13-20" <%if ("13-20".equals(ageRestriction)) {%>
											selected <%}%>>13-20</option>
										<option value="3+" <%if ("3+".equals(ageRestriction)) {%> selected
											<%}%>>3+</option>
										<option value="13+" <%if ("13+".equals(ageRestriction)) {%> selected
											<%}%>>13+</option>
										<option value="17+" <%if ("17+".equals(ageRestriction)) {%> selected
											<%}%>>17+</option>
										<option value="21+" <%if ("21+".equals(ageRestriction)) {%> selected
											<%}%>>21+</option>
										<option value="65+" <%if ("65+".equals(ageRestriction)) {%> selected
											<%}%>>65+</option>
									</select></td>

								</tr>
								<tr>
									<TD valign="top">Indicate service type provided:</TD>
									<TD></TD>
								</tr>
								<tr>
									<td><table width="100%"><tr><td width="25%"><input type="radio" name="indServTypes" value="ssMedical"
										<%if (providerData.getSsMedical().equals("Y")) {%> CHECKED <%}%>/>Medical&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td width="25%"><input type="radio" name="indServTypes" value="ssDental"
										<%if (providerData.getSsDental().equals("Y")) {%> CHECKED <%}%>/>Dental&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td width="25%"><input type="radio" name="indServTypes" value="ssVision"
										<%if (providerData.getSsVision().equals("Y")) {%> CHECKED <%}%>/>Vision&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td width="25%"><input type="radio" name="indServTypes" value="other"
										<%if (providerData.getSsOtherServType().equals("Y")) {%> CHECKED
													<%}%>/>Other</td></tr></table>
									</td>	
									<TD></td>
								</tr>
								<tr>
									<td colspan=2>Do you utilize a Nurse Practitioner or
									Physician Assistant in your practice?
									<input type="checkbox" name=npPractice
												value="<%=npPractice%>" <%if (npPractice.equals(Yes)) {%> CHECKED <%}%>/>NP&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="checkbox" name=paPractice value="<%=paPractice%>"
												<%if (paPractice.equals(Yes)) {%> CHECKED <%}%>/>PA&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="checkbox" name=naPractice value="<%=naPractice%>"
												<%if (naPractice.equals(Yes)) {%> CHECKED <%}%>/>N/A</td>
										</tr>
								
									
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
								
								<tr>
									<td colspan="4">Indicate if you are a: (If you checked Medical above, this section required)</td>
									</tr>
									<tr>
									<td colspan="4"><table width="100%"><tr>
									<td width="25%">&nbsp;&nbsp;<input type="checkbox" name=indivPractice
										value="<%=indivPractice%>" <%if (providerData.getIndivPractice().equals("Y")) {%>
										CHECKED <%}%>/>Individual Practice (solo provider)</td>
									<td width="25%"><input type="checkbox" name=groupPractice value="<%=groupPractice%>"
										<%if (providerData.getGroupPractice().equals("Y")) {%> CHECKED <%}%>/>Group
									Practice (more than one provider)</td>
									<td width="25%"><input
										type="checkbox" name=npSupSpec value="<%=npSupSpec%>"
										<%if (providerData.getNpSupSpec().equals("Y")) {%> CHECKED
										<%}%>/>NP Supporting a Specialist</td>
									<td width="25%"><input type="checkbox" name=npSupPMP value="<%=npSupPMP%>"
										<%if (providerData.getNpSupPMP().equals("Y")) {%> CHECKED
										<%}%>/>NP Supporting a PMP/PCP</td>
										</tr>
										</table>
									</td>	
								</tr>
								<tr>
									<td colspan="4"><table width="100%"><tr>
									<td width="25%">&nbsp;&nbsp;<input type="checkbox" name=fedQualHealthClinic value="<%=fedQualHealthClinic%>"
										<%if (providerData.getFedQualHealthClinic().equals("Y")) {%> CHECKED<%}%>/>Federally Qualified Health Center</td>
									<td width="25%"><input type="checkbox" name=ruralHealthClinic value="<%=ruralHealthClinic%>"
										<%if (providerData.getRuralHealthClinic().equals("Y")) {%> CHECKED <%}%>/>Rural Health Clinic</td>
									<%-- <td width="25%"><input
										type="checkbox" name=retailHealthClinic value="<%=retailHealthClinic%>"
										<%if (providerData.getRetailHealthClinic().equals("Y")) {%> CHECKED
										<%}%>/>Retail Health Clinic</td>
									<td width="25%"><input type="checkbox" name=walkInDrOffice value="<%=walkInDrOffice%>"
										<%if (providerData.getWalkInDrOffice().equals("Y")) {%> CHECKED
										<%}%>/>Walk in Doctor’s Office</td> --%>
									<!-- PMF Section C Changes -- AD21239 -->
									<td width="25%"><input
										type="checkbox" name=paSupSpec value="<%=paSupSpec%>"
										<%if (providerData.getPaSupSpec().equals("Y")) {%> CHECKED
										<%}%>/>PA Supporting a Specialist</td>
									<td width="25%"><input type="checkbox" name=paSupPMP value="<%=paSupPMP%>"
										<%if (providerData.getPaSupPMP().equals("Y")) {%> CHECKED
										<%}%>/>PA Supporting a PMP/PCP</td>
										<!-- PMF Section C Changes -- AD21239 -->
										</tr>
										</table>
									</td>	
								</tr>
								<tr>
									<td colspan="4"><table width="100%"><tr>
									<td width="25%">&nbsp;&nbsp;<input type="checkbox" name=urgentCare
										value="<%=urgentCare%>" <%if (providerData.getUrgentCare().equals("Y")) {%>
										CHECKED <%}%>/>Urgent Care</td>
									<td width="25%"><input type="checkbox" name=coveringPMP value="<%=coveringPMP%>"
										<%if (providerData.getCoveringPMP().equals("Y")) {%> CHECKED <%}%>/>Covering PMP/PCP</td>
									<td width="25%"><input
										type="checkbox" name=certMidwife value="<%=certMidwife%>"
										<%if (providerData.getCertMidwife().equals("Y")) {%> CHECKED
										<%}%>/>Certified Midwife</td>
									<td width="25%"><input
										type="checkbox" name=communityHealthCenter value="<%=communityHealthCenter%>"
										<%if (providerData.getCommunityHealthCenter().equals("Y")) {%>
										CHECKED <%}%>/>Community Mental Health Center</td><!-- new coding -->
										</tr>
										</table>
									</td>	
								</tr>
								<tr>
									<td colspan="4"><table width="100%" ><tr>
									<td width="25%" >&nbsp;&nbsp;<input type="checkbox" name=deptOfHealth
										value="<%=deptOfHealth%>" <%if (providerData.getDeptOfHealth().equals("Y")) {%>
										CHECKED <%}%>/>Department of Health</td>
									<td width="25%"><input type="checkbox" name=prenatelCareCoord 
										value="<%=prenatelCareCoord%>" <%if (providerData.getPrenatelCareCoord().equals("Y")) {%> 
										CHECKED<%}%>/>Prenatal Care Coordinator</td>
									<td width="25%"><input type="checkbox" name=schoolBasedClinic value="<%=schoolBasedClinic%>"
										<%if (providerData.getSchoolBasedClinic().equals("Y")) {%>
										CHECKED <%}%>/>ECP –Essential Community Provider</td>
									<td width="25%" ><input type="checkbox" name=tribalHealthCenter
										value="<%=tribalHealthCenter%>" <%if (providerData.getTribalHealthCenter().equals("Y")) {%>
										CHECKED <%}%>/>Tribal Health Center</td>
										</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
									<table width="100%"><tr>
									<td width="25%">&nbsp;&nbsp;<input type="checkbox" name=retailHealthClinic value="<%=retailHealthClinic%>"
										<%if (providerData.getRetailHealthClinic().equals("Y")) {%> CHECKED
										<%}%>/>Retail Health Clinic</td>
									<td width="25%"><input type="checkbox" name=walkInDrOffice value="<%=walkInDrOffice%>"
										<%if (providerData.getWalkInDrOffice().equals("Y")) {%> CHECKED
										<%}%>/>Walk in Doctor’s Office</td> 
									<td width="25%" ><input type="checkbox" name=medicationAssistedTreatment
										value="<%=medicationAssistedTreatment%>" <%if (providerData.getMedicationAssistedTreatment().equals("Y")) {%>CHECKED 
										<%}%>/>Medication Assisted Treatment (MAT)
										</td>
									<td width="25%" ><input type="checkbox" name=residentialTreatmentCenter
										value="<%=residentialTreatmentCenter%>" <%if (providerData.getResidentialTreatmentCenter().equals("Y")) {%>CHECKED 
										<%}%>/>Residential Treatment Center
										</td>
									</tr>
									</table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
									<table width="100%"><tr>
									<td width="25%" >&nbsp;&nbsp;<input type="checkbox" name=clinic
										value="<%=clinic%>" <%if (providerData.getClinic().equals("Y")) {%>CHECKED <%}%>/>Clinic (Type)
										</td>
									<td width="25%"><input type="checkbox" name=telehealthProv value="<%=telehealthProv%>"
										<%if (providerData.getTelehealthProv().equals("Y")) {%> CHECKED
										<%}%>/>Telehealth Provider</td>
									<td width="25%"><input type="checkbox" name=substanceUseDisorderAdults value="<%=substanceUseDisorderAdults%>"
										<%if (providerData.getSubstanceUseDisorderAdults().equals("Y")) {%> CHECKED
										<%}%>/>Substance Use Disorder provider (SUD) for Adults</td>
									<td width="25%"><input type="checkbox" name=substanceUseDisorderChild value="<%=substanceUseDisorderChild%>"
										<%if (providerData.getSubstanceUseDisorderChild().equals("Y")) {%> CHECKED
										<%}%>/>Substance Use Disorder provider (SUD) for Adolescents/Child</td>
									</tr>
									</table>
									</td>
								</tr>
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
								<tr>
								<td> If Clinic selected, please specify Clinic Type</td>
									<td>&nbsp;&nbsp;&nbsp;<input type="text" maxlength="30" name=enrollClinicType
										value="<%=ESAPI.encoder().encodeForHTMLAttribute(enrollClinicType)%>" /></td>
								</tr>
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
								
								<!-- moving fields from hip medicaid -->
								<tr>
										<TD colspan="2">
										    <table width="100%">
										     <tr>
										     	<td colspan="2">Please select your ethnic origin (**Optional)<BR> <BR>
													**Note: If provided, this information will be utilized in online provider directories available to our customers. </td>										     	
										     </tr>
										     <tr>
										     	<TD><input type="radio" name=ethnicOrigin value="Asian Indian"   <%if(providerData.getEthnicOrigin().equals("Asian Indian")){%>CHECKED <%}%>>Asian Indian</TD>
					                			<TD><input type="radio" name=ethnicOrigin value="Pacific Asian"   <%if(providerData.getEthnicOrigin().equals("Pacific Asian")){%>CHECKED <%}%>>Pacific Asian</TD>    
										     </tr>
										     <tr>
										     	<TD><input type="radio" name=ethnicOrigin value="Native American (i.e. American Indian, Eskimo, Aleut, or Native Hawaiian)"   <%if(providerData.getEthnicOrigin().equals("Native American (i.e. American Indian, Eskimo, Aleut, or Native Hawaiian)")){%>CHECKED <%}%>>Native American (i.e. American Indian, Eskimo, Aleut, or Native Hawaiian)</TD>
					                			<TD><input type="radio" name=ethnicOrigin value="Hispanic"   <%if(providerData.getEthnicOrigin().equals("Hispanic")){%>CHECKED <%}%>>Hispanic</TD>    
										     </tr>
										     <tr>
										     	<TD><input type="radio" name=ethnicOrigin value="African-American"   <%if(providerData.getEthnicOrigin().equals("African-American")){%>CHECKED <%}%>>African-American</TD>
					                			<TD><input type="radio" name=ethnicOrigin value="White, Non-Hispanic"   <%if(providerData.getEthnicOrigin().equals("White, Non-Hispanic")){%>CHECKED <%}%>>White, Non-Hispanic</TD>    
										     </tr>
										     <tr>
										     	<!-- WLPRD00971518 | Fix for Clear Selection | UST Global | 2014 October -->
										     	<TD><input type="radio" name=ethnicOrigin value="<%=clearSelection%>"  <%if(providerData.getEthnicOrigin().equals(clearSelection)){%>CHECKED <%}%>>Clear Selection</TD>
										     </tr>
										    </table>
										</TD>
								</tr>
								
							</table>
							
						<tr <%=indianaStyle%>>
							<td valign="top" align="center" colspan="2" BGCOLOR="#000080"><font size="2" COLOR="#ffffff">
							<b>Healthy
							Indiana Plan (HIP) and/or Medicaid State Sponsored Providers</b><br>
							<i>(For Wisconsin providers, proceed to the section marked 'Wisconsin Medicaid BadgerCare Plus Providers')</i></font></td>
						</tr>
						<tr <%=indianaStyle%>>
							<td valign="top" BGCOLOR="#ffffff"><font size="2" COLOR="#000080"><b>1.&nbsp;&nbsp;&nbsp;</b></td>
							<td BGCOLOR="#ffffff"><font size="2" COLOR="#000080"><b>Do you wish to use this online application to apply for
							participation in one or more of the following State Sponsored
							networks managed by Anthem? If so, please mark the appropriate
							networks below.</b></font></td>
						</tr>
						<tr <%=indianaStyle%>>
							<td valign="top" BGCOLOR="#ffffff"><font size="2" COLOR="#000080"><b>2.&nbsp;&nbsp;&nbsp;</b></td>
							<td BGCOLOR="#ffffff"><font size="2" COLOR="#000080"><b>If you need help finding your 9 digit Zip Code, go to 
							<a href= "https://tools.usps.com/go/ZipLookupAction!input.action" target="_blank">USPS.com&reg; - ZIP Code&trade; Lookup
							</a> for additional information.</b></font></td>			
						</tr>
								
						
						
						<tr <%=indianaStyle%>>
						<td colspan="2">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td><input type="checkbox" name=medicaidIndicator
											value="" <%if (medicaidIndicator.equals("Y")) {%> CHECKED <%}%>>Anthem
										Medicaid</td>
									</tr>


									<tr><td colspan="3"><table><tr>
										<td width="20%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"
											name=medicaidPCP value=""
											<%if (medicaidPCP.equals("Y") && !medicaidSpecialist.equals("Y")) {%>
											CHECKED <%}%>/>PCP</td>
										<td  width="20%"><input type="checkbox" name=medicaidSpecialist value=""
											<%if (!medicaidPCP.equals("Y") && medicaidSpecialist.equals("Y")) {%>
											CHECKED <%}%>/>Specialist</td>
										<td  width="20%"><input type="checkbox" name=medicaidBoth value=""
											<%if (medicaidPCP.equals("Y") && medicaidSpecialist.equals("Y")) {%>
											CHECKED <%}%>/>Both</td>
											</tr></table></td>
									</tr>

									<tr>
										<td valign="bottom"><nobr>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*Maximum
										Panel Capacity <input type="text" size="4" maxlength="5"
											name="medicaidMaxPanel" value="<%=ESAPI.encoder().encodeForHTMLAttribute(medicaidMaxPanel)%>"/> </nobr></td>
									</tr>

									<!--  Panel realted changes Layout need to be worked out again -->
									
									<tr>
										<TD colspan="2" valign="bottom">Panel Status:</TD>
										<TD >
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name=medPanelStatus value="<%=hold%>"
											<%if (medPanelStatus.equals(hold)) {%> CHECKED <%}%>/>Hold&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="radio" name=medPanelStatus value="<%=open%>"
											<%if (medPanelStatus.equals(open)) {%> CHECKED <%}%>/>Open
											<input type="radio" name=medPanelStatus value="<%=clearSelection%>"
											<%if (medPanelStatus.equals(clearSelection)) {%> CHECKED <%}%>/>Clear Selection
										
										</TD>

									</tr>
									<tr>
										<TD colspan="2" valign="bottom">Number of service locations:</TD>
										<TD>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" size="1" maxlength="1"
											name="medNbrLocations" value="<%=ESAPI.encoder().encodeForHTMLAttribute(medNbrLocations)%>"/></TD>
									</tr>
									<tr>
										<TD colspan="2" valign="bottom">Panel Limit Decrease:</TD>
										<TD>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name=medPldPanelDecrease
											value="<%=Yes%>" <%if (medPldPanelDecrease.equals(Yes)) {%> CHECKED
											<%}%>/>Yes&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name=medPldPanelDecrease
											value="<%=No%>" <%if (medPldPanelDecrease.equals(No)) {%> CHECKED
											<%}%>/>No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name=medPldPanelDecrease
											value="<%=clearSelection%>" <%if (medPldPanelDecrease.equals(clearSelection)) {%> CHECKED
											<%}%>/>Clear Selection
										</TD>
									</tr>

									<tr>
										<TD colspan="2" valign="bottom">&nbsp;&nbsp;&nbsp;&nbsp;Place Panel at:</TD>
										<TD>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" size="5" maxlength="5"
											name="medPldPlacePanelAt" value="<%=ESAPI.encoder().encodeForHTMLAttribute(medPldPlacePanelAt)%>"/>
										</TD>
									</tr>

									<tr>
										<TD colspan="2" valign="bottom">&nbsp;&nbsp;&nbsp;&nbsp;Group Medicaid Number and alpha
										location for Hold Remove:</TD>
										<TD>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" size="10" maxlength="10"
											name="medPldGrpMedicaidNo" value="<%=ESAPI.encoder().encodeForHTMLAttribute(medPldGrpMedicaidNo)%>"/>
										</TD>
									</tr>

									<tr>
										<TD colspan="2" valign="bottom">Panel Limit Increase:</TD>
										<TD>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name=medPliPanelIncrease
											value="<%=Yes%>" <%if (medPliPanelIncrease.equals(Yes)) {%> CHECKED
											<%}%>/>Yes&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name=medPliPanelIncrease
											value="<%=No%>" <%if (medPliPanelIncrease.equals(No)) {%> CHECKED
											<%}%>/>No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name=medPliPanelIncrease
											value="<%=clearSelection%>" <%if (medPliPanelIncrease.equals(clearSelection)) {%> CHECKED
											<%}%>/>Clear Selection
										</TD>
									</tr>

									<tr>
										<TD colspan="2" valign="bottom">&nbsp;&nbsp;&nbsp;&nbsp;Place Panel at:</TD>
										<TD>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" size="5" maxlength="5"
											name="medPliPlacePanelAt" value="<%=ESAPI.encoder().encodeForHTMLAttribute(medPliPlacePanelAt)%>"/>
										</TD>
									</tr>

									<tr>
										<TD colspan="2" valign="bottom">&nbsp;&nbsp;&nbsp;&nbsp;Group Medicaid Number and alpha
										location for Hold Remove:</TD>
										<TD>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" size="10" maxlength="10"
											name="medPliGrpMedicaidNbr" value="<%=ESAPI.encoder().encodeForHTMLAttribute(medPliGrpMedicaidNbr)%>"/>
										</TD>
									</tr>

									<tr>
										<TD colspan="2" valign="bottom">Panel Hold:</TD>
										<TD>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name=medPanelHold value="<%=Yes%>"
											<%if (medPanelHold.equals(Yes)) {%> CHECKED <%}%>/>Yes&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
											type="radio" name=medPanelHold value="<%=No%>"
											<%if (medPanelHold.equals(No)) {%> CHECKED <%}%>/>No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
											type="radio" name=medPanelHold value="<%=clearSelection%>"
											<%if (medPanelHold.equals(clearSelection)) {%> CHECKED <%}%>/>Clear Selection
										</TD>
									</tr>

									<tr>
										<TD colspan="2" valign="bottom">&nbsp;&nbsp;&nbsp;&nbsp;Group Medicaid Number and alpha
										location for Hold:</TD>
										<TD>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" size="10" maxlength="10"
											name="medPlrPanelHold" value="<%=ESAPI.encoder().encodeForHTMLAttribute(medPlrPanelHold)%>"/></TD>
									</tr>

									<tr>
										<TD colspan="2" valign="bottom">Panel Hold Remove:</TD>
										<TD>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name=medPanelHoldRemove
											value="<%=Yes%>" <%if (medPanelHoldRemove.equals(Yes)) {%> CHECKED
											<%}%>/>Yes&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name=medPanelHoldRemove
											value="<%=No%>" <%if (medPanelHoldRemove.equals(No)) {%> CHECKED
											<%}%>/>No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name=medPanelHoldRemove
											value="<%=clearSelection%>" <%if (medPanelHoldRemove.equals(clearSelection)) {%> CHECKED
											<%}%>/>Clear Selection
											
										</TD>
									</tr>

									<tr>
										<TD colspan="2" valign="bottom">&nbsp;&nbsp;&nbsp;&nbsp;Group Medicaid Number and alpha
										location for Hold Remove:</TD>
										<TD>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" size="10" maxlength="10"
											name="medPlrPanelHoldRemove" value="<%=ESAPI.encoder().encodeForHTMLAttribute(medPlrPanelHoldRemove)%>"/>
										</TD>
									</tr>
									<tr>
										<td colspan="3">&nbsp;	<hr width="200%" NOSHADE></hr></td>
										
									</tr>
									<tr>
										<td><input type="checkbox" name=hipIndicator value=""
											<%if (hipIndicator.equals("Y")) {%> CHECKED <%}%>/>Anthem
										Healthy Indiana Plan</td>
									</tr>
									<tr>
										<td colspan="3"><table><tr>
										<td width="20%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"
											name=hipPCP value=""
											<%if (hipPCP.equals("Y") && !hipSpecialist.equals("Y")) {%> CHECKED
											<%}%>/>PCP</td>
										<td width="20%"><input type="checkbox" name=hipSpecialist value=""
											<%if (!hipPCP.equals("Y") && hipSpecialist.equals("Y")) {%> CHECKED
											<%}%>/>Specialist</td>
										<td width="20%"><input type="checkbox" name=hipBoth value=""
											<%if (hipPCP.equals("Y") && hipSpecialist.equals("Y")) {%> CHECKED
											<%}%>/>Both</td>
											</tr></table></td>
									</tr>
									<tr>
										<td valign="bottom">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*Maximum
										Panel Capacity <input align="right" type="text" size="4"
											maxlength="5" name="hipMaxPanel" value="<%=ESAPI.encoder().encodeForHTMLAttribute(hipMaxPanel)%>"/></td>
									</tr>
									<tr>
										<TD colspan="2" valign="bottom">Panel Status:</TD>
										<TD>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name=hipPanelStatus value="<%=hold%>"
											<%if (hipPanelStatus.equals(hold)) {%> CHECKED <%}%>/>Hold&nbsp;&nbsp;&nbsp;&nbsp; <input
											type="radio" name=hipPanelStatus value="<%=open%>"
											<%if (hipPanelStatus.equals(open)) {%> CHECKED <%}%>/>Open <input
											type="radio" name=hipPanelStatus value="<%=clearSelection%>"
											<%if (hipPanelStatus.equals(clearSelection)) {%> CHECKED <%}%>/>Clear Selection
										</TD>

									</tr>
									
									<tr>
										<TD colspan="2" valign="bottom">Number of service locations:</TD>
										<TD>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" size="1" maxlength="1"
											name="hipNbrLocations" value="<%=ESAPI.encoder().encodeForHTMLAttribute(hipNbrLocations)%>"/></TD>
									</tr>
									<tr>
										<TD colspan="2" valign="bottom">Panel Limit Decrease:</TD>
										<TD>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name=hipPldPanelDecrease
											value="<%=Yes%>" <%if (hipPldPanelDecrease.equals(Yes)) {%> CHECKED
											<%}%>/>Yes&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name=hipPldPanelDecrease
											value="<%=No%>" <%if (hipPldPanelDecrease.equals(No)) {%> CHECKED
											<%}%>/>No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name=hipPldPanelDecrease
											value="<%=clearSelection%>" <%if (hipPldPanelDecrease.equals(clearSelection)) {%> CHECKED
											<%}%>/>Clear Selection
										</TD>
									</tr>

									<tr>
										<TD colspan="2" valign="bottom">&nbsp;&nbsp;&nbsp;&nbsp;Place Panel at:</TD>
										<TD>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" size="5" maxlength="5"
											name="hipPldPlacePanelAt" value="<%=ESAPI.encoder().encodeForHTMLAttribute(hipPldPlacePanelAt)%>"/>
										</TD>
									</tr>

									<tr>
										<TD colspan="2" valign="bottom">&nbsp;&nbsp;&nbsp;&nbsp;Group Medicaid Number and alpha
										location for Hold Remove:</TD>
										<TD>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" size="10" maxlength="10"
											name="hipPldGrpMedicaidNo" value="<%=ESAPI.encoder().encodeForHTMLAttribute(hipPldGrpMedicaidNo)%>"/>
										</TD>
									</tr>

									<tr>
										<TD colspan="2" valign="bottom">Panel Limit Increase:</TD>
										<TD>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name=hipPliPanelIncrease
											value="<%=Yes%>" <%if (hipPliPanelIncrease.equals(Yes)) {%> CHECKED
											<%}%>/>Yes&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name=hipPliPanelIncrease
											value="<%=No%>" <%if (hipPliPanelIncrease.equals(No)) {%> CHECKED
											<%}%>/>No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name=hipPliPanelIncrease
											value="<%=clearSelection%>" <%if (hipPldPanelDecrease.equals(clearSelection)) {%> CHECKED
											<%}%>/>Clear Selection
										</TD>
									</tr>

									<tr>
										<TD colspan="2" valign="bottom">&nbsp;&nbsp;&nbsp;&nbsp;Place Panel at:</TD>
										<TD>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" size="5" maxlength="5"
											name="hipPliPlacePanelAt" value="<%=ESAPI.encoder().encodeForHTMLAttribute(hipPliPlacePanelAt)%>"/>
										</TD>
									</tr>

									<tr>
										<TD colspan="2" valign="bottom">&nbsp;&nbsp;&nbsp;&nbsp;Group Medicaid Number and alpha
										location for Hold Remove:</TD>
										<TD>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" size="10" maxlength="10"
											name="hipPliGrpMedicaidNbr" value="<%=ESAPI.encoder().encodeForHTMLAttribute(hipPliGrpMedicaidNbr)%>"/>
										</TD>
									</tr>

									<tr>
										<TD colspan="2" valign="bottom">Panel Hold:</TD>
										<TD>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name=hipPanelHold value="<%=Yes%>"
											<%if (hipPanelHold.equals(Yes)) {%> CHECKED <%}%>/>Yes &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
											type="radio" name=hipPanelHold value="<%=No%>"
											<%if (hipPanelHold.equals(No)) {%> CHECKED <%}%>/>No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name=hipPanelHold
											value="<%=clearSelection%>" <%if (hipPldPanelDecrease.equals(clearSelection)) {%> CHECKED
											<%}%>/>Clear Selection
										</TD>
									</tr>

									<tr>
										<TD colspan="2" valign="bottom">&nbsp;&nbsp;&nbsp;&nbsp;Group Medicaid Number and alpha
										location for Hold:</TD>
										<TD>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" size="10" maxlength="10"
											name="hipPlrPanelHold" value="<%=ESAPI.encoder().encodeForHTMLAttribute(hipPlrPanelHold)%>"/></TD>
									</tr>

									<tr>
										<TD colspan="2" valign="bottom">Panel Hold Remove:</TD>
										<TD>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name=hipPanelHoldRemove
											value="<%=Yes%>" <%if (hipPanelHoldRemove.equals(Yes)) {%> CHECKED
											<%}%>/>Yes &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name=hipPanelHoldRemove
											value="<%=No%>" <%if (hipPanelHoldRemove.equals(No)) {%> CHECKED
											<%}%>/>No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name=hipPanelHoldRemove
											value="<%=clearSelection%>" <%if (hipPldPanelDecrease.equals(clearSelection)) {%> CHECKED
											<%}%>/>Clear Selection
										</TD>
									</tr>

									<tr>
										<TD colspan="2" valign="bottom">&nbsp;&nbsp;&nbsp;&nbsp;Group Medicaid Number and alpha
										location for Hold Remove:</TD>
										<TD>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" size="10" maxlength="10"
											name="hipPlrPanelHoldRemove" value="<%=ESAPI.encoder().encodeForHTMLAttribute(hipPlrPanelHoldRemove)%>"/>
										</TD>
									</tr>
									<tr>
										<td colspan="3">&nbsp;	<hr width="200%" NOSHADE></hr></td>
										
									</tr>
									
									
								<!--  february Changes for new fields inclusion -->

							<tr>
									<TD colspan="2" valign="bottom">Is provider disenrolling from
									one Managed Care Entity <br />
									to enroll in another Managed Care Entity?</TD>
									<TD>
									<table>
										<tr>
											<td><input type="radio" name=mgdCareDisenroll
												value="<%=Yes%>" <%if (mgdCareDisenroll.equals(Yes)) {%> CHECKED
												<%}%>/>Yes&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
											<td><input type="radio" name=mgdCareDisenroll value="<%=No%>"
												<%if (mgdCareDisenroll.equals(No)) {%> CHECKED <%}%>/>No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
											<td><input type="radio" name=mgdCareDisenroll value="<%=NA%>"
												<%if (mgdCareDisenroll.equals(NA)) {%> CHECKED <%}%>/>Unknown</td>
										</tr>
									</table>
									</TD>
							</tr>
								<tr>
									<TD colspan="2" valign="bottom">IHCP Provider Number -
									including alpha suffix:</TD>
									<TD><input type="text" size="20" maxlength="20"
										name="ihcpProvNo" value="<%=ESAPI.encoder().encodeForHTMLAttribute(ihcpProvNo)%>"></TD>
								</tr>
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
								<tr>
									<TD colspan="2" valign="bottom">Is provider a PMP?</TD>
									<TD><input type="radio" name=pmp value="<%=Yes%>"
										<%if (pmp.equals(Yes)) {%> CHECKED <%}%>/>Yes&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
										type="radio" name=pmp value="<%=No%>" <%if (pmp.equals(No)) {%>
										CHECKED <%}%>>No</TD>
								</tr>
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
								<tr>
									<td colspan="2">If a PMP, which specialty:</td>
									<td colspan="2"><select name="pmpSpecialty">
										<option value=""></option>
										<option value="Family Practice"
											<%if ("Family Practice".equals(pmpSpecialty)) {%> selected <%}%>>Family
										Practice</option>
										<option value="Pediatrics"
											<%if ("Pediatrics".equals(pmpSpecialty)) {%> selected <%}%>>Pediatrics</option>
										<option value="Gynecologist"
											<%if ("Gynecologist".equals(pmpSpecialty)) {%> selected <%}%>>Gynecologist</option>
										<option value="General Practice"
											<%if ("General Practice".equals(pmpSpecialty)) {%> selected <%}%>>General
										Practice</option>
										<option value="Internist"
											<%if ("Internist".equals(pmpSpecialty)) {%> selected <%}%>>Internist</option>
										<option value="OB/GYN"
											<%if ("OB/GYN".equals(pmpSpecialty)) {%> selected <%}%>>OB/GYN</option>	
									</select></td>
								</tr>

								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
								<tr>
									<TD colspan="2" valign="bottom">Hospital Admitting Privileges:
									</TD>
									<TD><input type="radio" name=hospAdmitPriv value="<%=Yes%>"
										<%if (hospAdmitPriv.equals(Yes)) {%> CHECKED <%}%>/>Yes&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
										type="radio" name=hospAdmitPriv value="<%=No%>"
										<%if (hospAdmitPriv.equals(No)) {%> CHECKED <%}%>/>No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
										<input type="radio" name=hospAdmitPriv
										value="<%=clearSelection%>" <%if (hospAdmitPriv.equals(clearSelection)) {%> CHECKED
										<%}%>/>Clear Selection</TD>
								</tr>
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
								<tr>
									<TD colspan="2" valign="bottom">Name of Hospital:</TD>
									<TD><input type="text" size="40" maxlength="55"
										name="apHospitalName" value="<%=ESAPI.encoder().encodeForHTMLAttribute(apHospitalName[0])%>"/></TD>
								</tr>

								<tr>
									<TD colspan="2" valign="bottom">Street Address:</TD>
									<TD><input type="text" size="40" maxlength="35"
										name="apHospitalAddress" value="<%=ESAPI.encoder().encodeForHTMLAttribute(apHospitalAddress[0])%>"/></TD>
								</tr>

								<tr>
									<TD colspan="2" valign="bottom">City:</TD>
									<TD><input type="text" size="19" maxlength="19"
										name="apHospitalCity" value="<%=ESAPI.encoder().encodeForHTMLAttribute(apHospitalCity[0])%>"/></TD>
								</tr>

								<tr>
									<TD colspan="2" valign="bottom">State:</TD>
									<TD><input type="text" size="2" maxlength="2"
										name="apHospitalState" value="<%=ESAPI.encoder().encodeForHTMLAttribute(apHospitalState[0])%>"/></TD>
								</tr>

								<tr>
									<TD colspan="2" valign="bottom">Zip:</TD>
									<TD><input type="text" size="11" maxlength="10"
										name="apHospitalZip" value="<%=ESAPI.encoder().encodeForHTMLAttribute(apHospitalZip[0])%>"/>(99999-9999)</TD>
								</tr>
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
								<tr>
									<TD colspan="2" valign="bottom">Name of Hospital:</TD>
									<TD><input type="text" size="40" maxlength="55"
										name="apHospitalName" value="<%=ESAPI.encoder().encodeForHTMLAttribute(apHospitalName[1])%>"/></TD>
								</tr>

								<tr>
									<TD colspan="2" valign="bottom">Street Address:</TD>
									<TD><input type="text" size="40" maxlength="35"
										name="apHospitalAddress" value="<%=ESAPI.encoder().encodeForHTMLAttribute(apHospitalAddress[1])%>"/></TD>
								</tr>

								<tr>
									<TD colspan="2" valign="bottom">City:</TD>
									<TD><input type="text" size="19" maxlength="19"
										name="apHospitalCity" value="<%=ESAPI.encoder().encodeForHTMLAttribute(apHospitalCity[1])%>"/></TD>
								</tr>

								<tr>
									<TD colspan="2" valign="bottom">State:</TD>
									<TD><input type="text" size="2" maxlength="2"
										name="apHospitalState" value="<%=ESAPI.encoder().encodeForHTMLAttribute(apHospitalState[1])%>"/></TD>
								</tr>

								<tr>
									<TD colspan="2" valign="bottom">Zip:</TD>
									<TD><input type="text" size="11" maxlength="10"
										name="apHospitalZip" value="<%=ESAPI.encoder().encodeForHTMLAttribute(apHospitalZip[1])%>"/>(99999-9999)</TD>
								</tr>
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
								<tr>
									<TD colspan="2" valign="bottom">Name of Hospital:</TD>
									<TD><input type="text" size="40" maxlength="55"
										name="apHospitalName" value="<%=ESAPI.encoder().encodeForHTMLAttribute(apHospitalName[2])%>"/></TD>
								</tr>

								<tr>
									<TD colspan="2" valign="bottom">Street Address:</TD>
									<TD><input type="text" size="40" maxlength="35"
										name="apHospitalAddress" value="<%=ESAPI.encoder().encodeForHTMLAttribute(apHospitalAddress[2])%>"/></TD>
								</tr>

								<tr>
									<TD colspan="2" valign="bottom">City:</TD>
									<TD><input type="text" size="19" maxlength="19"
										name="apHospitalCity" value="<%=ESAPI.encoder().encodeForHTMLAttribute(apHospitalCity[2])%>"/></TD>
								</tr>

								<tr>
									<TD colspan="2" valign="bottom">State:</TD>
									<TD><input type="text" size="2" maxlength="2"
										name="apHospitalState" value="<%=ESAPI.encoder().encodeForHTMLAttribute(apHospitalState[2])%>"/></TD>
								</tr>

								<tr>
									<TD colspan="2" valign="bottom">Zip:</TD>
									<TD><input type="text" size="11" maxlength="10"
										name="apHospitalZip" value="<%=ESAPI.encoder().encodeForHTMLAttribute(apHospitalZip[2])%>"/>(99999-9999)</TD>
								</tr>
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
								<tr>
									<TD colspan="2" valign="bottom">Relationship Privileges:</TD>
									<TD><input type="radio" name=relationshipPriv value="<%=Yes%>"
										<%if (relationshipPriv.equals(Yes)) {%> CHECKED <%}%>/>Yes&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
										type="radio" name=relationshipPriv value="<%=No%>"
										<%if (relationshipPriv.equals(No)) {%> CHECKED <%}%>/>No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
										<input type="radio" name=relationshipPriv
										value="<%=clearSelection%>" <%if (relationshipPriv.equals(clearSelection)) {%> CHECKED
										<%}%>/>Clear Selection</TD>
								</tr>
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
								<tr>
									<TD colspan="4" width="100%" valign="bottom">If you answered 'Yes' to the Relationship
									Privileges question, you will need to complete the Covering<br/>
									Physicians information in section H.</TD>
								</tr>
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
								<tr>
									<TD colspan="2" valign="bottom">Delivery Privileges (Any PMP
									who renders OB services must have delivery privileges and/or
									relationship privileges to deliver.)</TD>
									<TD>
									<table>
										<tr>
											<td><input type="radio" name=deliveryPriv value="<%=Yes%>"
												<%if (deliveryPriv.equals(Yes)) {%> CHECKED <%}%>/>Yes&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>
											<TD><input type="radio" name=deliveryPriv value="<%=No%>"
											<%if (deliveryPriv.equals(No)) {%> CHECKED <%}%>/>No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
											<input type="radio" name=deliveryPriv
											value="<%=clearSelection%>" <%if (deliveryPriv.equals(clearSelection)) {%> CHECKED
											<%}%>/>Clear Selection</TD>
										</tr>
									</table>
									</TD>
								</tr>
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
								<tr>
									<TD colspan="2" valign="bottom">Name of Hospital:</TD>
									<TD><input type="text" size="40" maxlength="55"
										name="dpHospitalName" value="<%=ESAPI.encoder().encodeForHTMLAttribute(dpHospitalName[0])%>"/></TD>
								</tr>

								<tr>
									<TD colspan="2" valign="bottom">Street Address:</TD>
									<TD><input type="text" size="40" maxlength="35"
										name="dpHospitalAddress" value="<%=ESAPI.encoder().encodeForHTMLAttribute(dpHospitalAddress[0])%>"/></TD>
								</tr>

								<tr>
									<TD colspan="2" valign="bottom">City:</TD>
									<TD><input type="text" size="19" maxlength="19"
										name="dpHospitalCity" value="<%=ESAPI.encoder().encodeForHTMLAttribute(dpHospitalCity[0])%>"/></TD>
								</tr>

								<tr>
									<TD colspan="2" valign="bottom">State:</TD>
									<TD><input type="text" size="2" maxlength="2"
										name="dpHospitalState" value="<%=ESAPI.encoder().encodeForHTMLAttribute(dpHospitalState[0])%>"/></TD>
								</tr>

								<tr>
									<TD colspan="2" valign="bottom">Zip:</TD>
									<TD><input type="text" size="11" maxlength="10"
										name="dpHospitalZip" value="<%=ESAPI.encoder().encodeForHTMLAttribute(dpHospitalZip[0])%>"/>(99999-9999)</TD>
								</tr>
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
								<tr>
									<TD colspan="2" valign="bottom">Name of Hospital:</TD>
									<TD><input type="text" size="40" maxlength="55"
										name="dpHospitalName" value="<%=ESAPI.encoder().encodeForHTMLAttribute(dpHospitalName[1])%>"/></TD>
								</tr>

								<tr>
									<TD colspan="2" valign="bottom">Street Address:</TD>
									<TD><input type="text" size="40" maxlength="35"
										name="dpHospitalAddress" value="<%=ESAPI.encoder().encodeForHTMLAttribute(dpHospitalAddress[1])%>"/></TD>
								</tr>

								<tr>
									<TD colspan="2" valign="bottom">City:</TD>
									<TD><input type="text" size="19" maxlength="19"
										name="dpHospitalCity" value="<%=ESAPI.encoder().encodeForHTMLAttribute(dpHospitalCity[1])%>"/></TD>
								</tr>

								<tr>
									<TD colspan="2" valign="bottom">State:</TD>
									<TD><input type="text" size="2" maxlength="2"
										name="dpHospitalState" value="<%=ESAPI.encoder().encodeForHTMLAttribute(dpHospitalState[1])%>"/></TD>
								</tr>

								<tr>
									<TD colspan="2" valign="bottom">Zip:</TD>
									<TD><input type="text" size="11" maxlength="10"
										name="dpHospitalZip" value="<%=ESAPI.encoder().encodeForHTMLAttribute(dpHospitalZip[1])%>"/>(99999-9999)</TD>
								</tr>
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
								<tr>
									<TD colspan="2" valign="bottom">Name of Hospital:</TD>
									<TD><input type="text" size="40" maxlength="55"
										name="dpHospitalName" value="<%=ESAPI.encoder().encodeForHTMLAttribute(dpHospitalName[2])%>"/></TD>
								</tr>

								<tr>
									<TD colspan="2" valign="bottom">Street Address:</TD>
									<TD><input type="text" size="40" maxlength="35"
										name="dpHospitalAddress" value="<%=ESAPI.encoder().encodeForHTMLAttribute(dpHospitalAddress[2])%>"/></TD>
								</tr>

								<tr>
									<TD colspan="2" valign="bottom">City:</TD>
									<TD><input type="text" size="19" maxlength="19"
										name="dpHospitalCity" value="<%=ESAPI.encoder().encodeForHTMLAttribute(dpHospitalCity[2])%>"/></TD>
								</tr>

								<tr>
									<TD colspan="2" valign="bottom">State:</TD>
									<TD><input type="text" size="2" maxlength="2"
										name="dpHospitalState" value="<%=ESAPI.encoder().encodeForHTMLAttribute(dpHospitalState[2])%>"/></TD>
								</tr>

								<tr>
									<TD colspan="2" valign="bottom">Zip:</TD>
									<TD><input type="text" size="11" maxlength="10"
										name="dpHospitalZip" value="<%=ESAPI.encoder().encodeForHTMLAttribute(dpHospitalZip[2])%>"/>(99999-9999)</TD>
								</tr>
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
								<tr>
									<td colspan="2">Scope of Practice (OB/GYN PMP's Only):</td>
								</tr>
								<tr>
									<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;All Women (OB/GYN) includes pregnant and non pregnant</td>
								</tr>
								<tr>
									<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;member.<u>Family Practitioners</u> cannot render to All Women. </td>
									<TD><input type="radio" name=pmpScopeAll value="<%=Yes%>"
										<%if (pmpScopeAll.equals(Yes)) {%> CHECKED <%}%>/>Yes&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
										type="radio" name=pmpScopeAll value="<%=No%>"
										<%if (pmpScopeAll.equals(No)) {%> CHECKED <%}%>/>No
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name=pmpScopeAll value="<%=clearSelection%>"
										<%if (pmpScopeAll.equals(clearSelection)) {%> CHECKED <%}%>/>Clear Selection
									</TD>
								</tr>	
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
								<tr>
									<TD colspan="3" valign="bottom">OB Only:</TD>
								</tr>
								<tr>
									<TD colspan="2" valign="bottom">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>
									<TD><input type="radio" name=pmpScopeOb value="<%=Yes%>"
										<%if (pmpScopeOb.equals(Yes)) {%> CHECKED <%}%>/>Yes&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
										type="radio" name=pmpScopeOb value="<%=No%>"
										<%if (pmpScopeOb.equals(No)) {%> CHECKED <%}%>/>No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
										type="radio" name=pmpScopeOb value="<%=clearSelection%>"
										<%if (pmpScopeOb.equals(clearSelection)) {%> CHECKED <%}%>/>Clear Selection
									</TD>
								</tr>
								
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
								<tr>
									<TD colspan="2" valign="bottom">Gender Scope:</TD>

									<TD><input type="radio" name=genderScope
										value="<%=genderMale%>" <%if (genderScope.equals(genderMale)) {%>
										CHECKED <%}%>/>Male&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name=genderScope
										value="<%=genderFemale%>" <%if (genderScope.equals(genderFemale)) {%>
										CHECKED <%}%>/>Female&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name=genderScope
										value="<%=genderMaleOrFemale%>"
										<%if (genderScope.equals(genderMaleOrFemale)) {%> CHECKED <%}%>/>Male/Female
									</TD>
								</tr>
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>


								<tr>
									<TD colspan="2" valign="bottom">DEA Number:</TD>
									<TD><input type="text" size="20" maxlength="20" name="deaNo"
										value="<%=ESAPI.encoder().encodeForHTMLAttribute(deaNo)%>"/></TD>
								</tr>
								<tr>
									<TD colspan="2" valign="bottom">CSR Number:</TD>
									<TD><input type="text" size="20" maxlength="20" name="csrNo"
										value="<%=ESAPI.encoder().encodeForHTMLAttribute(csrNo)%>"/></TD>
								</tr>
								
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
								
								<tr>
									<TD colspan="2" valign="bottom">Professional Liability Carrier
									name:</TD>
									<TD><input type="text" size="40" maxlength="55"
										name="profLiabCarrier" value="<%=ESAPI.encoder().encodeForHTMLAttribute(profLiabCarrier)%>"/></TD>
								</tr>
								<tr>
									<TD colspan="2" valign="bottom">Professional Liability Coverage
									Limits:</TD>
									<TD><input type="text" size="30" maxlength="30"
										name="profLiabCovgLimit" value="<%=ESAPI.encoder().encodeForHTMLAttribute(profLiabCovgLimit)%>"/></TD>
								</tr>
								<tr>
									<TD colspan="2" valign="bottom">Professional Liability Policy Number:</TD>
									<TD><input type="text" size="20" maxlength="20"
										name="profLiabPolicy" value="<%=ESAPI.encoder().encodeForHTMLAttribute(profLiabPolicy)%>"/></TD>
								</tr>
								<tr>
									<TD colspan="2" valign="bottom">Professional Liability
									Expiration Date:</TD>
									<TD><input type="text" size="8" maxlength="8"
										name="profLiabExtDt" value="<%=ESAPI.encoder().encodeForHTMLAttribute(profLiabExtDt)%>"/>(mmddyyyy)</TD>
								</tr>
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
								<tr>
									<TD colspan="2" valign="bottom">Has your malpractice insurance
									ever been terminated <br />
									or revoked except with your consent or request?</TD>
									<TD><input type="radio" name=malPracInsRevoke value="<%=Yes%>"
										<%if (malPracInsRevoke.equals(Yes)) {%> CHECKED <%}%>/>Yes &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
										type="radio" name=malPracInsRevoke value="<%=No%>"
										<%if (malPracInsRevoke.equals(No)) {%> CHECKED <%}%>/>No</TD>

								</tr>
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
								<tr>
									<TD colspan="2" valign="bottom">Are you currently under
									investigation by any government <br/> agency?</TD>
									<TD><input type="radio" name=underGovInvestigation
										value="<%=Yes%>" <%if (underGovInvestigation.equals(Yes)) {%> CHECKED
										<%}%>/>Yes &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name=underGovInvestigation
										value="<%=No%>" <%if (underGovInvestigation.equals(No)) {%> CHECKED
										<%}%>/>No</TD>

								</tr>
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
								<tr>
									<TD colspan="2" valign="bottom">Have you been expelled or
									suspended from receiving <br/> payment under Medicare or Medicaid?</TD>
									<TD><input type="radio" name=expellMedPay value="<%=Yes%>"
										<%if (expellMedPay.equals(Yes)) {%> CHECKED <%}%>/>Yes&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
										type="radio" name=expellMedPay value="<%=No%>"
										<%if (expellMedPay.equals(No)) {%> CHECKED <%}%>/>No</TD>
								</tr>
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
								<tr>
									<TD colspan="3" valign="bottom">If you are a physical therapy
									provider/facility you are required to submit copies of:</TD>
								</tr>
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
								<tr>
									<td colspan="2">&nbsp;&nbsp;&nbsp;State License (Including NPI - may be written on copy of license)</td>
								</tr>
								<tr>
									<td colspan="2">&nbsp;&nbsp;&nbsp;HCFA Site visit (if
									applicable)</td>
								</tr>
								<tr>
									<td colspan="2">&nbsp;&nbsp;&nbsp;Facility Accreditation
									Certificate(s)</td>
								</tr>
								<tr>
									<td colspan="2">&nbsp;&nbsp;&nbsp;Copy of Medicare
									certification letter (if applicable)</td>
								</tr>
								<tr>
									<td colspan="2">&nbsp;&nbsp;&nbsp;Certification letter from
									Medicaid (if applicable)</td>
								</tr>
								<tr>
									<td colspan="2">&nbsp;&nbsp;&nbsp;Liability Coverage Face Sheet</td>
								</tr>
								<tr>
									<td colspan="2">&nbsp;&nbsp;&nbsp;Tax Identification
									Information Form W-9</td>
									
								</tr>
								<tr>
									<td colspan="3">
									&nbsp;&nbsp;&nbsp;If PT/OT/ST/Audiology Provider, please submit list of individual practitioner NPI's</td>
								</tr>
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
													
							</table>
						</td>
						</tr>
						<!-- Section C Wisconsin New -->
						<tr <%=indianaStyle%>>
							<td valign="top" height="50%" align="center" colspan="2"
							BGCOLOR="#000080"><font size="2" COLOR="#ffffff">
							<b> Wisconsin Medicaid BadgerCare Plus Providers </b></font>
							</td>
						</tr>
						<tr <%=indianaStyle%>>
							<td colspan="4">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td colspan="4" valign="bottom"> List Provider's Medicaid ForwardHealth certified NPI's and indicate how each will be billed.</td>
							</tr>
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td valign="bottom" colspan="2"> ForwardHealth Certified NPI: </td>
								<TD colspan="2"><input type="text" size="10" maxlength="10" name="fwdHealthCertNPI1" value="<%=ESAPI.encoder().encodeForHTMLAttribute(fwdHealthCertNPI1)%>"> (9999999999)</TD>   
							</tr>
							<tr>
								<td valign="bottom"> How will this NPI be billed?</td>
							</tr>
							<tr>
								<td>
								<input type="radio" name=fwdHealthCertNPIBilled1 value="As both billing and rendering"   
								<%if(providerData.getFwdHealthCertNPIBilled1().equals("As both billing and rendering")){%>CHECKED <%}%>>As both billing and rendering
								</td>
								<td>
								<input type="radio" name=fwdHealthCertNPIBilled1 value="As rendering provider only"   
								<%if(providerData.getFwdHealthCertNPIBilled1().equals("As rendering provider only")){%>CHECKED <%}%>>As rendering provider only
								
								</td>
								<td>
								<input type="radio" name=fwdHealthCertNPIBilled1 value="As group provider only"   
								<%if(providerData.getFwdHealthCertNPIBilled1().equals("As group provider only")){%>CHECKED <%}%>>As group provider only
								
								</td>
								<td>
								<!-- WLPRD00971518 | Fix for Clear Selection | UST Global | 2014 October -->
								<input type="radio" name=fwdHealthCertNPIBilled1 value="<%=clearSelection%>"  
								<%if(providerData.getFwdHealthCertNPIBilled1().equals(clearSelection)){%>CHECKED <%}%>>Clear Selection
								
								</td>
							</tr>
							<tr>
									<td colspan="2">&nbsp;</td>
							</tr>
							<tr>
								<TD valign="bottom" colspan="2"> ForwardHealth certification effective date:</TD>
								<TD colspan="2"><input type="text" size="8" maxlength="8" name="fwdHealthCertEffDt1" value="<%=ESAPI.encoder().encodeForHTMLAttribute(fwdHealthCertEffDt1)%>">(mmddyyyy)</TD>
							</tr>
							<tr>
								<TD valign="bottom" colspan="2"> ForwardHealth recertification date:</TD>
								<TD colspan="2"><input type="text" size="8" maxlength="8" name="fwdHealthRecertEffDt1" value="<%=ESAPI.encoder().encodeForHTMLAttribute(fwdHealthRecertEffDt1)%>">(mmddyyyy)</TD>
							</tr>
							<tr><td>&nbsp;</td></tr>
							
							<tr>
								<td valign="bottom" colspan="2"> ForwardHealth Certified NPI: </td>
								<TD colspan="2"><input type="text" size="10" maxlength="10" name="fwdHealthCertNPI2" value="<%=ESAPI.encoder().encodeForHTMLAttribute(fwdHealthCertNPI2)%>"> (9999999999)</TD>   
							</tr>
							<tr>
								<td valign="bottom"> How will this NPI be billed?</td>
							</tr>
							<tr>
								<td>
								<input type="radio" name=fwdHealthCertNPIBilled2 value="As both billing and rendering"   
								<%if(providerData.getFwdHealthCertNPIBilled2().equals("As both billing and rendering")){%>CHECKED <%}%>>As both billing and rendering
								</td>
								<td>
								<input type="radio" name=fwdHealthCertNPIBilled2 value="As rendering provider only"   
								<%if(providerData.getFwdHealthCertNPIBilled2().equals("As rendering provider only")){%>CHECKED <%}%>>As rendering provider only
								</td>
								<td>
								<input type="radio" name=fwdHealthCertNPIBilled2 value="As group provider only"   
								<%if(providerData.getFwdHealthCertNPIBilled2().equals("As group provider only")){%>CHECKED <%}%>>As group provider only
								
								</td>
								<td>
								<!-- WLPRD00971518 | Fix for Clear Selection | UST Global | 2014 October -->
								<input type="radio" name=fwdHealthCertNPIBilled2 value="<%=clearSelection%>"   
								<%if(providerData.getFwdHealthCertNPIBilled2().equals(clearSelection)){%>CHECKED <%}%>>Clear Selection
								
								</td>
							</tr>
							<tr>
									<td colspan="2">&nbsp;</td>
							</tr>
							<tr>
								<TD valign="bottom" colspan="2"> ForwardHealth certification effective date:</TD>
								<TD colspan="2"><input type="text" size="8" maxlength="8" name="fwdHealthCertEffDt2" value="<%=ESAPI.encoder().encodeForHTMLAttribute(fwdHealthCertEffDt2)%>">(mmddyyyy)</TD>
							</tr>
							<tr>
								<TD valign="bottom" colspan="2"> ForwardHealth recertification date:</TD>
								<TD colspan="2"><input type="text" size="8" maxlength="8" name="fwdHealthRecertEffDt2" value="<%=ESAPI.encoder().encodeForHTMLAttribute(fwdHealthRecertEffDt2)%>">(mmddyyyy)</TD>
							</tr>
							<tr><td>&nbsp;</td></tr>
							
							<tr>
								<td valign="bottom" colspan="2"> ForwardHealth Certified NPI: </td>
								<TD colspan="2"><input type="text" size="10" maxlength="10" name="fwdHealthCertNPI3" value="<%=ESAPI.encoder().encodeForHTMLAttribute(fwdHealthCertNPI3)%>"> (9999999999)</TD>   
							</tr>
							<tr>
								<td valign="bottom"> How will this NPI be billed?</td>
							</tr>
							<tr>
								<td>
								<input type="radio" name=fwdHealthCertNPIBilled3 value="As both billing and rendering"   
								<%if(providerData.getFwdHealthCertNPIBilled3().equals("As both billing and rendering")){%>CHECKED <%}%>>As both billing and rendering
								</td>
								<td>
								<input type="radio" name=fwdHealthCertNPIBilled3 value="As rendering provider only"   
								<%if(providerData.getFwdHealthCertNPIBilled3().equals("As rendering provider only")){%>CHECKED <%}%>>As rendering provider only
								
								</td>
								<td>
								<input type="radio" name=fwdHealthCertNPIBilled3 value="As group provider only"   
								<%if(providerData.getFwdHealthCertNPIBilled3().equals("As group provider only")){%>CHECKED <%}%>>As group provider only
								
								</td>
								<td>
								<!-- WLPRD00971518 | Fix for Clear Selection | UST Global | 2014 October -->
								<input type="radio" name=fwdHealthCertNPIBilled3 value="<%=clearSelection%>" 
								<%if(providerData.getFwdHealthCertNPIBilled3().equals(clearSelection)){%>CHECKED <%}%>>Clear Selection
								
								</td>
							</tr>
							<tr>
									<td colspan="2">&nbsp;</td>
							</tr>
							<tr>
								<TD valign="bottom" colspan="2"> ForwardHealth certification effective date:</TD>
								<TD colspan="2"><input type="text" size="8" maxlength="8" name="fwdHealthCertEffDt3" value="<%=ESAPI.encoder().encodeForHTMLAttribute(fwdHealthCertEffDt3)%>">(mmddyyyy)</TD>
							</tr>
							<tr>
								<TD valign="bottom" colspan="2"> ForwardHealth recertification date:</TD>
								<TD colspan="2"><input type="text" size="8" maxlength="8" name="fwdHealthRecertEffDt3" value="<%=ESAPI.encoder().encodeForHTMLAttribute(fwdHealthRecertEffDt3)%>">(mmddyyyy)</TD>
							</tr>
							<tr><td>&nbsp;</td></tr>	
							
							
							</table>
							</td>
							</tr>						
						</table>
						</td>
							</tr>						
						</table>

</body>
</html>