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
<!--BEGIN FORM SECTION E -->
		<table BORDER="1" CELLSPACING=0 CELLPADDING=7 WIDTH="100%">

					<tr>
						<td valign="top" align="center" colspan="2" BGCOLOR="#000080"><font size="2" COLOR="#ffffff"><b><%=Constants.sectionPracticeAddress%> -- Practice Address <i>(Required)</i></b></font></td>
					</tr>
					
					<%	Iterator theDataE = errorMessagesVectorE.iterator();
							if (!errorMessagesVectorE.isEmpty())
							{%>
								<tr>
									<td valign="top" align="center" colspan="2" BGCOLOR="#FF0000"><font size="2" COLOR="#ffffff"><b>Error Messages</b></font></td>
								</tr>
								<tr>
									<td colspan="2" BGCOLOR="#ffffff"><font size="2" COLOR="#000080"><b>The following errors were detected.  Please correct the form entries and resubmit form.</b></font></td>
								</tr>
							<%	while(theDataE.hasNext())
								{
									String value = (String)theDataE.next(); %>
									<tr>
										<TD colspan="2" style="FONT-SIZE: large;"><font size="2" COLOR="#FF0000"><b><%=value%></b></font></TD>
									</tr>
							<%	}  %>
						<%	} %>
					<tr>
						<td valign="top" BGCOLOR="#ffffff" width="5%"><font size="2" COLOR="#000080"><b>1.&nbsp;&nbsp;&nbsp;</b></font></td>
						<td BGCOLOR="#ffffff" width="95%"><font size="2" COLOR="#000080"><b>If practice address or address for payment change, enter your old practice address and address for 
 Payment here and your new practice address and address for payment in <%=Constants.sectionAddressChange%>.</b></font></td>
					</tr>

					<tr>
						<td valign="top" BGCOLOR="#ffffff" width="5%"><font size="2" COLOR="#000080"><b>or</b></font></td>
						<td BGCOLOR="#ffffff" width="95%"><font size="2" COLOR="#000080"><b>If adding practice, enter your office location and your address for payment (if different from practice address).</b></font></td>
					</tr>

					<tr>
						<td valign="top" BGCOLOR="#ffffff" width="5%"><font size="2" COLOR="#000080"><b>2.&nbsp;&nbsp;&nbsp;</b></font></td>
						<td BGCOLOR="#ffffff" width="95%"><font size="2" COLOR="#000080"><b>It is unacceptable to put "same", "same as practice address" or "see above" in the address for payment.  Any of these entries will be rejected.</b></font></td>
					</tr>
					
					<tr>
						<td valign="top" BGCOLOR="#ffffff" width="5%"><font size="2" COLOR="#000080"><b>3.&nbsp;&nbsp;&nbsp;</b></font></td>
						<td BGCOLOR="#ffffff" width="95%"><font size="2" COLOR="#000080"><b>Missouri House Bill 1498 requires the health plan, when requesting additional information for the purpose of determining if all or part of a claim will be reimbursed, to request such information via electronic or facsimile notice.  Please provide the fax and email for the appropriate department within your organization that would handle these types of requests.</b></font></td>
					</tr>
					
					<tr>
						<td valign="top" BGCOLOR="#ffffff" width="5%"><font size="2" COLOR="#000080"><b>4.&nbsp;&nbsp;&nbsp;</b></font></td>
						<td BGCOLOR="#ffffff" width="95%"><font size="2" COLOR="#000080"><b>If you need help finding your 9 digit Zip Code, go to <a href= "https://tools.usps.com/go/ZipLookupAction!input.action" target="_blank">USPS.com&reg; - ZIP Code&trade; Lookup</a> for additional information.</b></font></td>						
					</tr>
					<tr>
						<td valign="top" BGCOLOR="#ffffff" width="5%"><font size="2" COLOR="#000080"><b>5.&nbsp;&nbsp;&nbsp;</b></font></td>
						<td BGCOLOR="#ffffff" width="95%"><font size="2" COLOR="#000080"><b>Phone numbers for Practice address should be the phone number that patients would call to make an appointment.</b></font></td>
					</tr>
					<tr>
						<td colspan="2">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr><td>&nbsp;</td>
								<td><b>Practice Address Information:</b></td>
								<td><b>Address for Payment Information **Required</b></td></tr>
								
								<tr>
								<td colspan="2">&nbsp;</td>
								<td>Same as Practice Address
                						<%	String billingAddressSame1 = ESAPI.encoder().encodeForHTMLAttribute(billAddressSame1);
										if (billAddressSame1.equalsIgnoreCase("Y"))
											{ %>
													<input type="checkbox" name=billAddressSame1 value="<%=billingAddressSame1%>" checked>
										<%	}
											else
											{%>
                            						<input type="checkbox" name=billAddressSame1 value="<%=billingAddressSame1%>">
										<%	} %>
								</td>
								</tr>
								<tr>
									<TD valign="bottom">Street Address:</TD>
									<TD><input type="text" size="40" maxlength="70" name="pracOfficeAddress1" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeAddress1)%>"></TD>
									<td><input type="text" size="40" maxlength="70" name="pracBillAddress1" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillAddress1)%>"></td>
								</tr>

								<tr>
									<TD valign="bottom">City:</TD>
									<TD><input type="text" size="19" maxlength="19" name="pracOfficeCity1" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeCity1)%>"></TD>
									<td><input type="text" size="19" maxlength="19" name="pracBillCity1" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillCity1)%>"></td>
								</tr>

								<tr>
									<TD valign="bottom">State:</TD>
									<TD><input type="text" size="2" maxlength="2" name="pracOfficeState1" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeState1)%>"></TD>
									<td><input type="text" size="2" maxlength="2" name="pracBillState1" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillState1)%>"></td>
								</tr>

								<tr>
									<TD valign="bottom">Zip:</TD>
									<TD><input type="text" size="11" maxlength="10" name="pracOfficeZip1" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeZip1)%>">(99999-9999)</TD>
									<td><input type="text" size="11" maxlength="10" name="pracBillZip1" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillZip1)%>">(99999-9999)</td>
								</tr>


								<tr>
									<TD valign="bottom">County:</TD>
									<TD><input type="text" size="20" maxlength="20" name="pracOfficeCounty1" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeCounty1)%>"></TD>
									<td><input type="text" size="20" maxlength="20" name="pracBillCounty1" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillCounty1)%>"></td>
								</tr>


								<tr>
									<TD valign="bottom">Phone Number(for patient appointments):</TD>
									<TD><input type="text" size="10" maxlength="10" name="pracOfficePhone1" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficePhone1)%>">(9999999999)</TD>
									<td><input type="text" size="10" maxlength="10" name="pracBillPhone1" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillPhone1)%>">(9999999999)</td>
								</tr>


								<tr>
									<TD valign="bottom">Fax Number:</TD>
									<TD><input type="text" size="10" maxlength="10" name="pracOfficeFax1" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeFax1)%>">(9999999999)</TD>
									<td><input type="text" size="10" maxlength="10" name="pracBillFax1" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillFax1)%>">(9999999999)</td>
								</tr>


								<tr>
									<TD valign="bottom">Group Email Address:</TD>
									<TD><input type="text" size="40" maxlength="40" name="pracOfficeEmail1" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeEmail1)%>"></TD>
								</tr>
								<!-- //Changes for the state mandate on 02/10/10 start -->
								<tr>
									<TD valign="bottom">Languages spoken by Office Staff:</TD>
									<TD><input type="text" size="40" maxlength="70" name="languagesSpoken1" value="<%=ESAPI.encoder().encodeForHTMLAttribute(languagesSpoken1)%>"></TD>
								</tr>	
								<tr>
									<TD valign="bottom" width="298">Email Address for Payment:</TD>
									<td>&nbsp;</td>
									<TD><input type="text" size="40" maxlength="40" name="pracBillContactEmail1" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillContactEmail1)%>"></TD>
								</tr>
									
									<tr>
									<TD valign="bottom" width="298">Contact Name for Payment:</TD>
									<td>&nbsp;</td>
									<TD><input type="text" size="40" maxlength="40" name="pracBillContactName1" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillContactName1)%>"></TD>
									</tr>
														
								<!-- //Changes for the state mandate on 02/10/10 end -->
								<tr><td colspan="3">&nbsp;</td></tr>
								</table>
								<table width="100%"  border="0" cellpadding="0" cellspacing="0">
								<tr>
										<TD valign="bottom">Access to Public Transportation?</TD>
										<TD><input type="radio" name=pubicTrans1 value="<%=Yes%>"   <%if(pubicTrans1.equals(Yes)){%>CHECKED <%}%>>Yes
                                            <input type="radio" name=pubicTrans1 value="<%=No%>" <%if(pubicTrans1.equals(No)) {%>CHECKED <%}%>>No</TD>
                                        <TD>Days office is open?</TD>
										<td>&nbsp;</td>
										<td align="center"> Open</td>
										<td align="center">Closed</td>                                      
								</tr>
								<tr>
										<TD valign="bottom">Handicap Accessible?</TD>
										<TD><input type="radio" name=handicapAccess1 value="<%=Yes%>"   <%if(handicapAccess1.equals(Yes)){%>CHECKED <%}%>>Yes
                                            <input type="radio" name=handicapAccess1 value="<%=No%>" <%if(handicapAccess1.equals(No)) {%>CHECKED <%}%>>No</TD>
                                        <td>&nbsp;</td>
										<td>
										<input type="checkbox" name="daysOpenMon0" id="daysOpenMon0" value="<%=daysOpenMon0%>" <%if(Yes.equals(daysOpenMon0)){%>CHECKED <%}%>/>Mon
										</td>
										<td>
										<select name="daysOpenTimeMon" id="daysOpenTimeMon">
										<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
										<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeMon[0])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
										<%} %>
										</select>
										</td>
										<td>
										<select name="daysCloseTimeMon" id="daysCloseTimeMon">
										<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
										<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeMon[0])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
										<%} %>
										</select>
										</td>                                       
								</tr>
								<tr>
										<TD valign="bottom">Evening Hours?</TD>
										<TD><input type="radio" name=eveningHrs1 value="<%=Yes%>"   <%if(eveningHrs1.equals(Yes)){%>CHECKED <%}%>>Yes
                                            <input type="radio" name=eveningHrs1 value="<%=No%>" <%if(eveningHrs1.equals(No)) {%>CHECKED <%}%>>No</TD>
                                        <td>&nbsp;</td>
										<td>                                            
											<input type="checkbox" name="daysOpenTue0" id="daysOpenTue0" value="<%=daysOpenTue0%>" <%if(daysOpenTue0.equals(Yes)){%>CHECKED <%}%>/>Tue
										</td>
										<td>
											<select name="daysOpenTimeTue" id="daysOpenTimeTue">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeTue[0])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
										</td>
										<td>
											<select name="daysCloseTimeTue" id="daysCloseTimeTue">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeTue[0])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
										</td>
                                        
								</tr>
								<tr>
									<!--  2013 SSCR 13503 changes
									<td colspan="3">&nbsp;</td>-->
									<TD valign="bottom">List this address in the Provider Directory?</TD>
									<TD><input type="radio" name=provDir1 value="<%=Yes%>" <%if(provDir1.equals(Yes)){%>CHECKED <%}%>>Yes
            						<input type="radio" name=provDir1 value="<%=No%>" <%if(provDir1.equals(No)) {%>CHECKED <%}%>>No</TD>
    								 <td>&nbsp;</td>
									<!--  2013 SSCR 13503 changes-->
									<td>
									<input type="checkbox" name="daysOpenWed0" id="daysOpenWed0" value="<%=daysOpenWed0%>" <%if(daysOpenWed0.equals(Yes)){%>CHECKED <%}%>/>Wed
									</td>
									<td>
									<select name="daysOpenTimeWed" id="daysOpenTimeWed">
									<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
									<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeWed[0])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
									<%} %>
									</select>
									</td>
									<td><select name="daysCloseTimeWed" id="daysCloseTimeWed">
									<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
									<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeWed[0])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
									<%} %>
									</select>
									</td>									
								</tr>
								
								<tr>
								  <!-- 2013 SSCR 13503 changes  <td> Time Zone of Office Hours?</td>
								   <td colspan ="2">
											<select name="timeZoneOfficeHrs">
												<option value=""></option>
												<option value="EST" <%if("EST".equals(timeZoneOfficeHrs[0])){ %> selected <%} %>>Eastern Time</option>
												<option value="CST" <%if("CST".equals(timeZoneOfficeHrs[0])){ %> selected <%} %>>Central Time</option>
											</select>											
									</td>-->
									<td colspan="3">&nbsp;</td>
									<!-- 2013 SSCR 13503 change -->
									<td>
										<input type="checkbox" name="daysOpenThu0" id="daysOpenThu0" value="<%=daysOpenThu0%>" <%if(daysOpenThu0.equals(Yes)){%>CHECKED <%}%>/>Thu </td>
									<td>
										<select name="daysOpenTimeThu" id="daysOpenTimeThu">
										<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
										<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeThu[0])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
										<%} %>
										</select>
									</td>
										<td><select name="daysCloseTimeThu" id="daysCloseTimeThu">
										<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
										<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeThu[0])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
										<%} %>
										</select>
									</td>									
								</tr>
								<tr><!--  2013 SSCR 13503 changes
									<td colspan="3">&nbsp;</td>
									-->
									<td> Time Zone of Office Hours?</td>
									<td colspan ="2">
									<select name="timeZoneOfficeHrs">
									<option value=""></option>
									<option value="EST" <%if("EST".equals(timeZoneOfficeHrs[0])){ %> selected <%} %>>Eastern Time</option>
									<option value="CST" <%if("CST".equals(timeZoneOfficeHrs[0])){ %> selected <%} %>>Central Time</option>
									</select>											
									</td>
									<!-- 2013 SSCR 13503 changes -->
									<td>
										<input type="checkbox" name="daysOpenFri0" id="daysOpenFri0"  value="<%=daysOpenFri0%>" <%if(daysOpenFri0.equals(Yes)){%>CHECKED <%}%>/>Fri
									</td>
									<td>
										<select name="daysOpenTimeFri" id="daysOpenTimeFri">
										<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
										<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeFri[0])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
										<%} %>
										</select>
									</td>
									<td>
										<select name="daysCloseTimeFri" id="daysCloseTimeFri">
										<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
										<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeFri[0])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
										<%} %>
										</select>
									</td>
								</tr>
									
								<tr>
									<td colspan="3">&nbsp;</td>
									<td>
										<input type="checkbox" name="daysOpenSat0" id="daysOpenSat0" value="<%=daysOpenSat0%>" <%if(daysOpenSat0.equals(Yes)){%>CHECKED <%}%>/>Sat
									</td>
									<td>
										<select name="daysOpenTimeSat" id="daysOpenTimeSat">
										<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
										<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeSat[0])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
										<%} %>
										</select>
									</td>
									<td>
										<select name="daysCloseTimeSat" id="daysCloseTimeSat">
										<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
										<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeSat[0])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
										<%} %>
										</select>
									</td>
								</tr>
								
								<tr>
									<td colspan="3">&nbsp;</td>
									<td>
										<input type="checkbox" name="daysOpenSun0" id="daysOpenSun0" value="<%=daysOpenSun0%>" <%if(daysOpenSun0.equals(Yes)){%>CHECKED <%}%>/>Sun
									</td>
									<td>
										<select name="daysOpenTimeSun" id="daysOpenTimeSun">
										<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
										<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeSun[0])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
										<%} %>
										</select>
									</td>
									<td>
										<select name="daysCloseTimeSun" id="daysCloseTimeSun">
										<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
										<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeSun[0])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
										<%} %>
										</select>
									</td>							
								</tr>
									
								<tr><td colspan="6">&nbsp;</td></tr>
								
								</table>
										
								<!-- //Changes for the state mandate on 02/10/10 start -->
								
								<table width="100%"  border="0" cellpadding="0" cellspacing="0">							
								<tr> 
								<td colspan="2">
									<b>Does This Site Offer:</b>  
								</td>	
								</tr>
								<tr>
									<TD valign="bottom">ECI (Early Childhood Intervention)</TD>
									<TD><input type="radio" name=offerECI1 value="<%=Yes%>" <%if(offerECI1.equals(Yes)){%>CHECKED <%}%>>Yes 
					                	<input type="radio" name=offerECI1 value="<%=No%>" <%if(offerECI1.equals(No)) {%>CHECKED <%}%>>No (Answer required for Indiana Anthem Medicaid)
					            	</TD>
								</tr>

								<tr>
									<TD valign="bottom">EPSDT (Early and Periodic Screening, Diagnosis and Treatment)</TD>
									<TD><input type="radio" name=offerEPSDT1 value="<%=Yes%>" <%if(offerEPSDT1.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=offerEPSDT1 value="<%=No%>" <%if(offerEPSDT1.equals(No)) {%>CHECKED <%}%>>No (Answer required for Indiana Anthem Medicaid)</TD>
								</tr>
								<tr><td colspan="2">&nbsp;</td></tr>
								<tr><td colspan="2">
									<b>Do You Provide Care for:</b> 
									</td> 
								</tr>
								<tr>
									<TD valign="bottom">ABD (Aged, Blind and Disabled)</TD>
									<TD><input type="radio" name=provideADB1 value="<%=Yes%>" <%if(provideADB1.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=provideADB1 value="<%=No%>" <%if(provideADB1.equals(No)) {%>CHECKED <%}%>>No (Answer required for Indiana Anthem Medicaid)</TD>
								</tr>

								<tr>
									<TD valign="bottom">CSHCN (Children w/Special Healthcare Needs)</TD>
									<TD><input type="radio" name=provideCSHCN1 value="<%=Yes%>" <%if(provideCSHCN1.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=provideCSHCN1 value="<%=No%>" <%if(provideCSHCN1.equals(No)) {%>CHECKED <%}%>>No (Answer required for Indiana Anthem Medicaid)</TD>
								</tr>
								<tr><td colspan="2">&nbsp;</td></tr>
								<!-- //Changes for the state mandate on 02/10/10 end -->
								
								<tr><td colspan="2">
									<b>Medication Assisted Treatment (MAT):</b> 
									</td> 
								</tr>
								<tr>
									<TD valign="bottom">The provider at this location is a waivered Prescriber for Medication Assisted Treatment (MAT) for 
									Opioid Use Disorders (DATA 2000) and is accepting patients for MAT.<br>
									(NOTE:  If you are submitting for more than one individual, please attach a list 
									indicating which individuals are waivered Prescribers for MAT.)</TD>
									<TD><input type="radio" name=matWaiveredPrescriber1 value="<%=Yes%>" <%if(matWaiveredPrescriber1.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=matWaiveredPrescriber1 value="<%=No%>" <%if(matWaiveredPrescriber1.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								<tr>
									<TD valign="bottom">This office location is designated as a Certified Opioid Treatment Program</TD>
									<TD><input type="radio" name=certOpioidTreat1 value="<%=Yes%>" <%if(certOpioidTreat1.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=certOpioidTreat1 value="<%=No%>" <%if(certOpioidTreat1.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								<tr>
									<TD valign="bottom">This office location offers Medication Assisted Treatment (MAT) for Opioid Use Disorders</TD>
									<TD><input type="radio" name=matOpioid1 value="<%=Yes%>" <%if(matOpioid1.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=matOpioid1 value="<%=No%>" <%if(matOpioid1.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								<tr>
									<TD valign="bottom">Counseling in conjunction with Medication Assisted Treatment (MAT) for Opioid Use Disorders is provided at this location</TD>
									<TD><input type="radio" name=counselOpioid1 value="<%=Yes%>" <%if(counselOpioid1.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=counselOpioid1 value="<%=No%>" <%if(counselOpioid1.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								<tr>
									<TD valign="bottom">This office location is designated as a Substance Use Disorder (SUD) provider</TD>
									<TD><input type="radio" name=sudProv1 value="<%=Yes%>" <%if(sudProv1.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=sudProv1 value="<%=No%>" <%if(sudProv1.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								<tr>
									<TD valign="bottom">This office location is a Residential Treatment Center</TD>
									<TD><input type="radio" name=resTreatCtr1 value="<%=Yes%>" <%if(resTreatCtr1.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=resTreatCtr1 value="<%=No%>" <%if(resTreatCtr1.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								
								<tr><td colspan="2">&nbsp;</td></tr>
								<tr><td colspan="2">
									<b>Telehealth Services:</b> 
									</td> 
								</tr>
								<tr>
									<TD valign="bottom">Do you provide Telehealth Services at this location? (If marked yes, Telehealth will be displayed in the directory.)</TD>
									<TD><input type="radio" name=provideTelehealth1 value="<%=Yes%>" <%if(provideTelehealth1.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=provideTelehealth1 value="<%=No%>" <%if(provideTelehealth1.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>

								<tr><td colspan="2">&nbsp;</td></tr>
								<tr>
								<TD valign="bottom"><b>National Provider Identification Number:</b></TD>
								<TD><input type="text" size="10" maxlength="10" name="pracNPINo1" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracNPINo1)%>">(9999999999)</TD>
								</tr>
								
								<tr><td colspan="2">&nbsp;</td></tr>

								<tr><td colspan="2"><b>Please enter Medicare Information:</b></td></tr>
								<tr>
									<td valign="bottom">Medicare Group Number:</td>
									<TD><input type="text" size="10" maxlength="10" name="billMedicareGroup1" value="<%=ESAPI.encoder().encodeForHTMLAttribute(billMedicareGroup1)%>"></TD>
								</tr>

								<tr>
									<td valign="bottom">Medicare Individual Number:</td>
									<TD><input type="text" size="10" maxlength="10" name="billMedicareIndividual1" value="<%=ESAPI.encoder().encodeForHTMLAttribute(billMedicareIndividual1)%>"></TD>
								</tr>
								<tr><td colspan="2">&nbsp;</td></tr>

								<tr><td colspan="2"><b>Please enter Medicaid Information:</b></td></tr>
								<tr>
									<td valign="bottom">Medicaid Group Number plus alpha:</td>
									<TD><input type="text" size="10" maxlength="10" name="medicaidGroup1" value="<%=ESAPI.encoder().encodeForHTMLAttribute(medicaidGroup1)%>"></TD>
								</tr>
								<tr>
									<td valign="bottom">Medicaid Individual Number:</td>
									<TD><input type="text" size="10" maxlength="10" name="medicaidIndividual1" value="<%=ESAPI.encoder().encodeForHTMLAttribute(medicaidIndividual1)%>"></TD>
								</tr>
								
								<!-- PMF Section E Changes -- AD21239 -->
								<tr><td colspan="2">&nbsp;</td></tr>
								<tr><td colspan="2"><b>Please enter Kentucky Medicaid Program Information:</b></td></tr>
								<tr>
									<td valign="bottom">Are you currently participating in the Kentucky Medicaid Program? </td>
									<TD><input type="radio" name=kyMedicaidPart1 value="<%=Yes%>"   <%if(kyMedicaidPart1.equals(Yes)){%>CHECKED <%}%>>Yes
                                        <input type="radio" name=kyMedicaidPart1 value="<%=No%>" <%if(kyMedicaidPart1.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								<%-- <%if(kentuckyMediProgSecE.equals(No)){%> --%>
								<tr>
									<td valign="bottom">Kentucky Medicaid ID:</td>
									<TD><input type="text" size="10" maxlength="10" name="kyMedicaidId1" value="<%=ESAPI.encoder().encodeForHTMLAttribute(kyMedicaidId1)%>"></TD>
								</tr>
								<tr><td colspan="2">&nbsp;</td></tr>
								<tr>
									<td valign="bottom">For more information on the Kentucky Medicaid ID enrollment <br>
									process and for specific enrollment requirements by provider type, <br>
									please visit the provider enrollment site for the Kentucky Cabinet for <br>
									Health and Family Services, located <a href="./disclaimer.html" target="#" name = "Disclaimer Page">here</a>. These forms may be <br>
									submitted to your local Medicaid Provider Relations team to facilitate <br>
									the enrollment process. 
									</td>
									<TD></TD>
								</tr>
								<%-- <%}%> --%>
								<!-- PMF Section E Changes -- AD21239 -->
							</table></td></tr>
						</table>
</body>
</html>