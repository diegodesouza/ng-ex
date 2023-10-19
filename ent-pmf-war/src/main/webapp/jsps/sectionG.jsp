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
<!--BEGIN SECTION G-->
		<table BORDER="1" CELLSPACING=0 CELLPADDING=7 WIDTH="100%">
					<tr>
						<td valign="top" align="center" colspan="2" BGCOLOR="#000080"><font size="2" COLOR="#ffffff"><b><%=Constants.sectionOfficeLocation%> -- Additional Office Locations</b></font></td>
					</tr>
					
					<%	Iterator theDataG = errorMessagesVectorG.iterator();
							if (!errorMessagesVectorG.isEmpty())
							{%>
								<tr>
									<td valign="top" align="center" colspan="2" BGCOLOR="#FF0000"><font size="2" COLOR="#ffffff"><b>Error Messages</b></font></td>
								</tr>
								<tr>
									<td colspan="2" BGCOLOR="#ffffff"><font size="2" COLOR="#000080"><b>The following errors were detected.  Please correct the form entries and resubmit form.</b></font></td>
								</tr>
							<%	while(theDataG.hasNext())
								{
									String value = (String)theDataG.next(); %>
									<tr>
										<TD colspan="2" style="FONT-SIZE: large;"><font size="2" COLOR="#FF0000"><b><%=value%></b></font></TD>
									</tr>
							<%	}  %>
						<%	} %>
					
					<tr>
						<td valign="top" BGCOLOR="#ffffff" width="5%"><font size="2" COLOR="#000080"><b>1.&nbsp;&nbsp;&nbsp;</b></td>
						<td BGCOLOR="#ffffff" width="95%"><font size="2" COLOR="#000080"><b>Include any additional office locations.</b></font></td>
					</tr>

					<tr>
						<td valign="top" BGCOLOR="#ffffff" width="5%"><font size="2" COLOR="#000080"><b>2.&nbsp;&nbsp;&nbsp;</b></td>
						<td BGCOLOR="#ffffff" width="95%"><font size="2" COLOR="#000080"><b>Submit as many forms as needed to include additional addresses that do not fit in this form.</b></font></td>
					</tr>

					<tr>
						<td valign="top" BGCOLOR="#ffffff" width="5%"><font size="2" COLOR="#000080"><b>3.&nbsp;&nbsp;&nbsp;</b></td>
						<td BGCOLOR="#ffffff" width="95%"><font size="2" COLOR="#000080"><b>It is unacceptable to put "same", "same as practice address" or "see above".&nbsp;&nbsp;  Any of these entries will be rejected.</b></font></td>
					</tr>
					
					<tr>
						<td valign="top" BGCOLOR="#ffffff" width="5%"><font size="2" COLOR="#000080"><b>4.&nbsp;&nbsp;&nbsp;</b></font></td>
						<td BGCOLOR="#ffffff" width="95%"><font size="2" COLOR="#000080"><b>If you need help finding your 9 digit Zip Code, go to <a href= "https://tools.usps.com/go/ZipLookupAction!input.action" target="_blank">USPS.com&reg; - ZIP Code&trade; Lookup</a> for additional information.  </b></font></td>						
					</tr>
					
					<tr>
						<td valign="top" BGCOLOR="#ffffff" width="5%"><font size="2" COLOR="#000080"><b>5.&nbsp;&nbsp;&nbsp;</b></font></td>
						<td BGCOLOR="#ffffff" width="95%"><font size="2" COLOR="#000080"><b>Phone numbers for Practice address should be the phone number that patients would call to make an appointment. </b></font></td>						
					</tr>
					
					
					<tr>
						<td colspan="2">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr><td colspan="3"><b>Second Practice Information:</b></td></tr>
								<tr><td>&nbsp;</td>
								<td><b>Practice Address Information:</b></td>
								<td><b>Address for Payment Information **Required</b></td></tr>
								
								<tr>
								<td colspan="2">&nbsp;</td>
								<TD valign="bottom">Same as Practice Address
                						<%	String billingAddressSame3 = ESAPI.encoder().encodeForHTMLAttribute(billAddressSame3);
										if (billAddressSame3.equalsIgnoreCase("Y"))
											{ %>
													<input type="checkbox" name=billAddressSame3 value="<%=billingAddressSame3%>" checked>
										<%	}
											else
											{%>
                            						<input type="checkbox" name=billAddressSame3 value="<%=billingAddressSame3%>">
										<%	} %>
								</TD>
								</tr>
								<!-- 2013 SSCR 13503 changes-->
								<tr>
								<td colspan="2">
								<TD valign="bottom">Remit for this Practice Address is same</TD>
  								</tr>
  								<tr>
								<td colspan="2">
								<TD valign="bottom">as Section E &nbsp;&nbsp;
      							<%	if (remitSamePrim3.equalsIgnoreCase("Y")) 	{ %>
								<input type="checkbox" name=remitSamePrim3 value="<%= remitSamePrim3 %>" checked>
	 							<%	}
								else
								{%>
           						<input type="checkbox" name= remitSamePrim3 value="<%= remitSamePrim3 %>">
								<%	} %>
								</TD>
    							</tr>
								<!-- 2013 SSCR 13503 changes-->	
								<tr>
									<TD valign="bottom">Second Practice Name:</TD>
									<TD><input type="text" size="55" maxlength="55" name="pracName3" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracName3)%>"></TD>
									<TD>&nbsp;</TD>
								</tr>

								<tr>
									<TD valign="bottom">Street Address:</TD>
									<TD><input type="text" size="40" maxlength="70" name="pracOfficeAddress3" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeAddress3)%>"></TD>
									<TD><input type="text" size="40" maxlength="70" name="pracBillAddress3" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillAddress3)%>"></TD>
								</tr>

								<tr>
									<TD valign="bottom">City:</TD>
									<TD><input type="text" size="19" maxlength="19" name="pracOfficeCity3" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeCity3)%>"></TD>
									<TD><input type="text" size="19" maxlength="19" name="pracBillCity3" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillCity3)%>"></TD>
								</tr>

								<tr>
									<TD valign="bottom">State:</TD>
									<TD><input type="text" size="2" maxlength="2" name="pracOfficeState3" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeState3)%>"></TD>
									<TD><input type="text" size="2" maxlength="2" name="pracBillState3" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillState3)%>"></TD>
								</tr>

								<tr>
									<TD valign="bottom">Zip:</TD>
									<TD><input type="text" size="11" maxlength="10" name="pracOfficeZip3" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeZip3)%>">(99999-9999)</TD>
									<TD><input type="text" size="10" maxlength="10" name="pracBillZip3" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillZip3)%>">(99999-9999)</TD>
								</tr>


								<tr>
									<TD valign="bottom">County:</TD>
									<TD><input type="text" size="20" maxlength="20" name="pracOfficeCounty3" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeCounty3)%>"></TD>
									<TD><input type="text" size="20" maxlength="20" name="pracBillCounty3" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillCounty3)%>"></TD>
								</tr>


								<tr>
									<TD valign="bottom">Phone Number(for patient appointments):</TD>
									<TD><input type="text" size="10" maxlength="10" name="pracOfficePhone3" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficePhone3)%>">(9999999999)</TD>
									<TD><input type="text" size="10" maxlength="10" name="pracBillPhone3" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillPhone3)%>">(9999999999)</TD>
								</tr>


								<tr>
									<TD valign="bottom">Fax Number:</TD>
									<TD><input type="text" size="10" maxlength="10" name="pracOfficeFax3" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeFax3)%>">(9999999999)</TD>
									<TD><input type="text" size="10" maxlength="10" name="pracBillFax3" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillFax3)%>">(9999999999)</TD>
								</tr>
								<tr>
									<TD valign="bottom">Group Email Address:</TD>
									<TD><input type="text" size="40" maxlength="40" name="pracOfficeEmail3" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeEmail3)%>"></TD>
									<td>&nbsp;</td>
								</tr>
								
								<!-- //Changes for the state mandate on 02/10/10 start -->
								<tr>
									<TD valign="bottom">Languages spoken by Office Staff:</TD>
									<TD><input type="text" size="40" maxlength="70" name="languagesSpoken3" value="<%=ESAPI.encoder().encodeForHTMLAttribute(languagesSpoken3)%>"></TD>
									<td>&nbsp;</td>
								</tr>	
								
								<tr>
									<TD valign="bottom">Email Address for Payment</TD>
									<td>&nbsp;</td>
									<TD><input type="text" size="40" maxlength="40" name="pracBillContactEmail3" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillContactEmail3)%>"></TD>
								</tr>
								
								<tr>
									<TD valign="bottom">Contact Name for Payment:</TD>
									<td>&nbsp;</td>
									<TD><input type="text" size="40" maxlength="40" name="pracBillContactName3" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillContactName3)%>"></TD>
								</tr>	
														
								<!-- //Changes for the state mandate on 02/10/10 end -->
								
								<tr><td colspan="3">&nbsp;</td></tr>
								</table>
								<table width="100%" cellpadding="0" cellspacing="0">
								<tr>
										<TD valign="bottom">Access to Public Transportation?</TD>
										<TD><input type="radio" name=pubicTrans3 value="<%=Yes%>"   <%if(pubicTrans3.equals(Yes)){%>CHECKED <%}%>>Yes
                                            <input type="radio" name=pubicTrans3 value="<%=No%>" <%if(pubicTrans3.equals(No)) {%>CHECKED <%}%>>No</TD>
                                        <TD>Days office is open?</TD>
										<td>&nbsp;</td>
										<td align="center"> Open</td>
										<td align="center">Closed</td>
								</tr>
								<tr>
										<TD valign="bottom">Handicap Accessible?</TD>
										<TD><input type="radio" name=handicapAccess3 value="<%=Yes%>"<%if(handicapAccess3.equals(Yes)){%>CHECKED <%}%>>Yes
                                            <input type="radio" name=handicapAccess3 value="<%=No%>"<%if(handicapAccess3.equals(No)) {%>CHECKED <%}%>>No</TD>
                                         <td>&nbsp;</td>
                                         <td>
											<input type="checkbox" name="daysOpenMon2" id="daysOpenMon2" value="<%=daysOpenMon2%>" <%if(Yes.equals(daysOpenMon2)){%>CHECKED <%}%>/>Mon
										</td>
										<td>
											<select name="daysOpenTimeMon" id="daysOpenTimeMon">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeMon[2])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
										</td>
										<td>
											<select name="daysCloseTimeMon" id="daysCloseTimeMon">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeMon[2])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
										</td>
								</tr>
								<tr>
										<TD valign="bottom">Evening Hours?</TD>
										<TD><input type="radio" name=eveningHrs3 value="<%=Yes%>"   <%if(eveningHrs3.equals(Yes)){%>CHECKED <%}%>>Yes
                                            <input type="radio" name=eveningHrs3 value="<%=No%>" <%if(eveningHrs3.equals(No)) {%>CHECKED <%}%>>No</TD>
										<td>&nbsp;</td>
										<td>                                            
                                           	 <input type="checkbox" name="daysOpenTue2" id="daysOpenTue2" value="<%=daysOpenTue2%>" <%if(daysOpenTue2.equals(Yes)){%>CHECKED <%}%>/>Tue
										</td>
										<td>
											<select name="daysOpenTimeTue" id="daysOpenTimeTue">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeTue[2])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
											</td>
											<td>
											<select name="daysCloseTimeTue" id="daysCloseTimeTue">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeTue[2])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
											</td>
								</tr>
								<tr><!-- 2013  SSCR 13503 changes 
									<td colspan="2">&nbsp;</td>
									<td>&nbsp;</td>-->
									<TD valign="bottom">List this address in the Provider Directory?</TD>
									<TD><input type="radio" name=provDir3 value="<%=Yes%>" <%if(provDir3.equals(Yes)){%>CHECKED <%}%>>Yes
            						<input type="radio" name=provDir3 value="<%=No%>" <%if(provDir3.equals(No)) {%>CHECKED <%}%>>No</TD>
     								<td>&nbsp;</td>
     								<!-- 2013 SSCR 13503 changes -->
									
									<td>
											<input type="checkbox" name="daysOpenWed2" id="daysOpenWed2" value="<%=daysOpenWed2%>" <%if(daysOpenWed2.equals(Yes)){%>CHECKED <%}%>/>Wed
											</td>
											<td>
											<select name="daysOpenTimeWed" id="daysOpenTimeWed">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeWed[2])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
											</td>
											<td><select name="daysCloseTimeWed" id="daysCloseTimeWed">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeWed[2])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
											</td>
								</tr>
								<tr>
								<!-- 2013 SSCR 13503 changes 
								   <td> Time Zone of Office Hours?</td>
								   <td colspan ="2">
											<select name="timeZoneOfficeHrs">
												<option value=""></option>
												<option value="EST" <%if("EST".equals(timeZoneOfficeHrs[2])){ %> selected <%} %>>Eastern Time</option>
												<option value="CST" <%if("CST".equals(timeZoneOfficeHrs[2])){ %> selected <%} %>>Central Time</option>
											</select>
									</td>
									-->
									<td colspan="3">&nbsp;</td>
									<td>
                                           <input type="checkbox" name="daysOpenThu2" id="daysOpenThu2" value="<%=daysOpenThu2%>" <%if(daysOpenThu2.equals(Yes)){%>CHECKED <%}%>/>Thu </td>
											<td>
											<select name="daysOpenTimeThu" id="daysOpenTimeThu">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeThu[2])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
											</td>
											<td><select name="daysCloseTimeThu" id="daysCloseTimeThu">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeThu[2])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
											</td>
								</tr>
								<tr><!-- 2013 SSCR 13503 changes 
									<td colspan="3"></td>-->
									<td> Time Zone of Office Hours?</td>
								   <td colspan ="2">
											<select name="timeZoneOfficeHrs">
												<option value=""></option>
												<option value="EST" <%if("EST".equals(timeZoneOfficeHrs[2])){ %> selected <%} %>>Eastern Time</option>
												<option value="CST" <%if("CST".equals(timeZoneOfficeHrs[2])){ %> selected <%} %>>Central Time</option>
											</select>
									</td>
									<!-- 2013 SSCR 13503 changes -->
									<td>
                                            <input type="checkbox" name="daysOpenFri2" id="daysOpenFri2"  value="<%=daysOpenFri2%>" <%if(daysOpenFri2.equals(Yes)){%>CHECKED <%}%>/>Fri
											</td>
											<td>
											<select name="daysOpenTimeFri" id="daysOpenTimeFri">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeFri[2])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
											</td>
											<td><select name="daysCloseTimeFri" id="daysCloseTimeFri">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeFri[2])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
											</td>
								</tr>
								<tr>
									<td colspan="3">&nbsp;</td>
									<td>
											<input type="checkbox" name="daysOpenSat2" id="daysOpenSat2" value="<%=daysOpenSat2%>" <%if(daysOpenSat2.equals(Yes)){%>CHECKED <%}%>/>Sat
											</td>
											<td>
											<select name="daysOpenTimeSat" id="daysOpenTimeSat">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeSat[2])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
											</td>
											<td>
											<select name="daysCloseTimeSat" id="daysCloseTimeSat">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeSat[2])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
											</td>
								</tr>
								<tr>
										<td colspan="3">&nbsp;</td>
										<td>
											<input type="checkbox" name="daysOpenSun2" id="daysOpenSun2" value="<%=daysOpenSun2%>" <%if(daysOpenSun2.equals(Yes)){%>CHECKED <%}%>/>Sun
											</td>
											<td>
											<select name="daysOpenTimeSun" id="daysOpenTimeSun">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeSun[2])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
											</td>
											<td>
											<select name="daysCloseTimeSun" id="daysCloseTimeSun">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeSun[2])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
											</td>
								</tr>
								<tr><td colspan="6">&nbsp;</td></tr>
								</table>
								
								<!-- //Changes for the state mandate on 02/10/10 start -->
								
								<table width="100%" cellpadding="0" cellspacing="0">
								<tr><td colspan="2">
									<b>Does This Site Offer:</b>  
									</td>
								</tr>
								<tr>
									<TD valign="bottom">ECI (Early Childhood Intervention)</TD>
									<TD><input type="radio" name=offerECI3 value="<%=Yes%>" <%if(offerECI3.equals(Yes)){%>CHECKED <%}%>>Yes 
					                	<input type="radio" name=offerECI3 value="<%=No%>" <%if(offerECI3.equals(No)) {%>CHECKED <%}%>>No (Answer required for Indiana Anthem Medicaid)
					            	</TD>
								</tr>

								<tr>
									<TD valign="bottom">EPSDT (Early and Periodic Screening, Diagnosis and Treatment)</TD>
									<TD><input type="radio" name=offerEPSDT3 value="<%=Yes%>" <%if(offerEPSDT3.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=offerEPSDT3 value="<%=No%>" <%if(offerEPSDT3.equals(No)) {%>CHECKED <%}%>>No (Answer required for Indiana Anthem Medicaid)</TD>
								</tr>
								<tr><td colspan="2">&nbsp;</td></tr>
								<tr><td colspan="2">
									<b>Do You Provide Care for:</b> 
									</td> 
								</tr>
								<tr>
									<TD valign="bottom">ABD (Aged, Blind and Disabled)</TD>
									<TD><input type="radio" name=provideADB3 value="<%=Yes%>" <%if(provideADB3.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=provideADB3 value="<%=No%>" <%if(provideADB3.equals(No)) {%>CHECKED <%}%>>No (Answer required for Indiana Anthem Medicaid)</TD>
								</tr>

								<tr>
									<TD valign="bottom">CSHCN (Children w/Special Healthcare Needs)</TD>
									<TD><input type="radio" name=provideCSHCN3 value="<%=Yes%>" <%if(provideCSHCN3.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=provideCSHCN3 value="<%=No%>" <%if(provideCSHCN3.equals(No)) {%>CHECKED <%}%>>No (Answer required for Indiana Anthem Medicaid)</TD>
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
									<TD><input type="radio" name=matWaiveredPrescriber3 value="<%=Yes%>" <%if(matWaiveredPrescriber3.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=matWaiveredPrescriber3 value="<%=No%>" <%if(matWaiveredPrescriber3.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								<tr>
									<TD valign="bottom">This office location is designated as a Certified Opioid Treatment Program</TD>
									<TD><input type="radio" name=certOpioidTreat3 value="<%=Yes%>" <%if(certOpioidTreat3.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=certOpioidTreat3 value="<%=No%>" <%if(certOpioidTreat3.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								<tr>
									<TD valign="bottom">This office location offers Medication Assisted Treatment (MAT) for Opioid Use Disorders</TD>
									<TD><input type="radio" name=matOpioid3 value="<%=Yes%>" <%if(matOpioid3.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=matOpioid3 value="<%=No%>" <%if(matOpioid3.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								<tr>
									<TD valign="bottom">Counseling in conjunction with Medication Assisted Treatment (MAT) for Opioid Use Disorders is provided at this location</TD>
									<TD><input type="radio" name=counselOpioid3 value="<%=Yes%>" <%if(counselOpioid3.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=counselOpioid3 value="<%=No%>" <%if(counselOpioid3.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								<tr>
									<TD valign="bottom">This office location is designated as a Substance Use Disorder (SUD) provider</TD>
									<TD><input type="radio" name=sudProv3 value="<%=Yes%>" <%if(sudProv3.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=sudProv3 value="<%=No%>" <%if(sudProv3.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								<tr>
									<TD valign="bottom">This office location is a Residential Treatment Center</TD>
									<TD><input type="radio" name=resTreatCtr3 value="<%=Yes%>" <%if(resTreatCtr3.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=resTreatCtr3 value="<%=No%>" <%if(resTreatCtr3.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								
								<tr><td colspan="2">&nbsp;</td></tr>
								<tr><td colspan="2">
									<b>Telehealth Services:</b> 
									</td> 
								</tr>
								<tr>
									<TD valign="bottom">Do you provide Telehealth Services at this location? (If marked yes, Telehealth will be displayed in the directory.)</TD>
									<TD><input type="radio" name=provideTelehealth3 value="<%=Yes%>" <%if(provideTelehealth3.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=provideTelehealth3 value="<%=No%>" <%if(provideTelehealth3.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								
								<tr><td colspan="2">&nbsp;</td></tr>
								<tr>
								<TD valign="bottom"><b>National Provider Identification Number:</b></TD>
									<TD><input type="text" size="10" maxlength="10" name="pracNPINo3" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracNPINo3)%>">(9999999999)</TD>
								</tr>
								
								<tr><td colspan="2">&nbsp;</td></tr>

								<tr><td colspan="2"><b>Please enter Medicare Information:</b></td></tr>
								<tr>
									<td valign="bottom">Medicare Group Number:</td>
									<TD><input type="text" size="10" maxlength="10" name="billMedicareGroup3" value="<%=ESAPI.encoder().encodeForHTMLAttribute(billMedicareGroup3)%>"></TD>
								</tr>

								<tr>
									<td valign="bottom">Medicare Individual Number:</td>
									<TD><input type="text" size="10" maxlength="10" name="billMedicareIndividual3" value="<%=ESAPI.encoder().encodeForHTMLAttribute(billMedicareIndividual3)%>"></TD>
								</tr>
								<tr><td colspan="2">&nbsp;</td></tr>

								<tr><td colspan="2"><b>Please enter Medicaid Information:</b></td></tr>
								<tr>
									<td valign="bottom">Medicaid Group Number plus alpha:</td>
									<TD><input type="text" size="10" maxlength="10" name="medicaidGroup3" value="<%=ESAPI.encoder().encodeForHTMLAttribute(medicaidGroup3)%>"></TD>
								</tr>

								<tr>
									<td valign="bottom">Medicaid Individual Number:</td>
									<TD><input type="text" size="10" maxlength="10" name="medicaidIndividual3" value="<%=ESAPI.encoder().encodeForHTMLAttribute(medicaidIndividual3)%>"></TD>
								</tr>
								
								<!-- PMF Section G Changes -- AD21239 -->
								<tr><td colspan="2">&nbsp;</td></tr>
								<tr><td colspan="2"><b>Please enter Kentucky Medicaid Program Information:</b></td></tr>
								<tr>
									<td valign="bottom">Are you currently participating in the Kentucky Medicaid Program? </td>
									<TD><input type="radio" name=kyMedicaidPart3 value="<%=Yes%>"   <%if(kyMedicaidPart3.equals(Yes)){%>CHECKED <%}%>>Yes
                                        <input type="radio" name=kyMedicaidPart3 value="<%=No%>" <%if(kyMedicaidPart3.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								<%-- <%if(kentuckyMediProgSecG.equals(No)){%> --%>
								<tr>
									<td valign="bottom">Kentucky Medicaid ID:</td>
									<TD><input type="text" size="10" maxlength="10" name="kyMedicaidId3" value="<%=ESAPI.encoder().encodeForHTMLAttribute(kyMedicaidId3)%>"></TD>
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
								<!-- PMF Section G Changes -- AD21239 -->
							</table>

						</td>
					</tr>
				<tr>
						<td colspan="2">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr><td colspan="2"><b>Third Practice Information:</b></td></tr>
								<tr><td>&nbsp;</td>
								<td><b>Practice Address Information:</b></td>
								<td><b>Address for Payment Information **Required</b></td></tr>

								<tr>
									<td colspan="2">&nbsp;</td>
									<TD valign="bottom">Same as Practice Address
	                						<%	String billingAddressSame4 = ESAPI.encoder().encodeForHTMLAttribute(billAddressSame4);
													if (billAddressSame4.equalsIgnoreCase("Y"))
												{ %>
														<input type="checkbox" name=billAddressSame4 value="<%=billingAddressSame4%>" checked>
											<%	}
												else
												{%>
	                            						<input type="checkbox" name=billAddressSame4 value="<%=billingAddressSame4%>">
											<%	} %>
									</TD>
								</tr>
								<!-- 2013 SSCR 13503 changes-->
								<tr>
								<td colspan="2">
								<TD valign="bottom">Remit for this Practice Address is same</TD>
  								</tr>
  								<tr>
								<td colspan="2">
								<TD valign="bottom">as Section E &nbsp;&nbsp;
      							<%	if (remitSamePrim4.equalsIgnoreCase("Y")) 	{ %>
								<input type="checkbox" name=remitSamePrim4 value="<%= remitSamePrim4 %>" checked>
	 							<%	}
								else
								{%>
           						<input type="checkbox" name= remitSamePrim4 value="<%= remitSamePrim4 %>">
								<%	} %>
								</TD>
    							</tr>
    							<!-- 2013 SSCR 13503 changes-->
								<tr>
									<TD valign="bottom">Third Practice Name:</TD>
									<TD><input type="text" size="55" maxlength="55" name="pracName4" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracName4)%>"></TD>
									<TD>&nbsp;</TD>
								</tr>
								<tr>
									<TD valign="bottom">Street Address:</TD>
									<TD><input type="text" size="40" maxlength="70" name="pracOfficeAddress4" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeAddress4)%>"></TD>
									<TD><input type="text" size="40" maxlength="70" name="pracBillAddress4" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillAddress4)%>"></TD>
								</tr>

								<tr>
									<TD valign="bottom">City:</TD>
									<TD><input type="text" size="19" maxlength="19" name="pracOfficeCity4" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeCity4)%>"></TD>
									<TD><input type="text" size="19" maxlength="19" name="pracBillCity4" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillCity4)%>"></TD>
								</tr>

								<tr>
									<TD valign="bottom">State:</TD>
									<TD><input type="text" size="2" maxlength="2" name="pracOfficeState4" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeState4)%>"></TD>
									<TD><input type="text" size="2" maxlength="2" name="pracBillState4" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillState4)%>"></TD>
								</tr>

								<tr>
									<TD valign="bottom">Zip:</TD>
									<TD><input type="text" size="10" maxlength="10" name="pracOfficeZip4" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeZip4)%>">(99999-9999)</TD>
									<TD><input type="text" size="11" maxlength="10" name="pracBillZip4" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillZip4)%>">(99999-9999)</TD>
								</tr>


								<tr>
									<TD valign="bottom">County:</TD>
									<TD><input type="text" size="20" maxlength="20" name="pracOfficeCounty4" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeCounty4)%>"></TD>
									<TD><input type="text" size="20" maxlength="20" name="pracBillCounty4" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillCounty4)%>"></TD>
								</tr>


								<tr>
									<TD valign="bottom">Phone Number(for patient appointments):</TD>
									<TD><input type="text" size="10" maxlength="10" name="pracOfficePhone4" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficePhone4)%>">(9999999999)</TD>
									<TD><input type="text" size="10" maxlength="10" name="pracBillPhone4" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillPhone4)%>">(9999999999)</TD>
								</tr>


								<tr>
									<TD valign="bottom">Fax Number:</TD>
									<TD><input type="text" size="10" maxlength="10" name="pracOfficeFax4" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeFax4)%>">(9999999999)</TD>
									<TD><input type="text" size="10" maxlength="10" name="pracBillFax4" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillFax4)%>">(9999999999)</TD>
								</tr>


								<tr>
									<TD valign="bottom">Group Email Address:</TD>
									<TD><input type="text" size="40" maxlength="40" name="pracOfficeEmail4" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeEmail4)%>"></TD>
								</tr>
								
								<!-- //Changes for the state mandate on 02/10/10 start -->
								<tr>
									<TD valign="bottom">Languages spoken by Office Staff:</TD>
									<TD><input type="text" size="40" maxlength="70" name="languagesSpoken4" value="<%=ESAPI.encoder().encodeForHTMLAttribute(languagesSpoken4)%>"></TD>
								</tr>								
								<!-- //Changes for the state mandate on 02/10/10 end -->
								<tr>
									<TD valign="bottom">Email Address for Payment:</TD>
									<td>&nbsp;</td>
									<TD><input type="text" size="40" maxlength="40" name="pracBillContactEmail4" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillContactEmail4)%>"></TD>
								</tr>
								<tr>
									<TD valign="bottom">Contact Name for Payment:</TD>
									<td>&nbsp;</td>
									<TD><input type="text" size="40" maxlength="40" name="pracBillContactName4" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillContactName4)%>"></TD>
								</tr>	
								<tr><td colspan="3">&nbsp;</td></tr>
								</table>
								
								<table width="100%" cellpadding="0" cellspacing="0">
								<tr>
										<TD valign="bottom">Access to Public Transportation?</TD>
										<TD><input type="radio" name=pubicTrans4 value="<%=Yes%>"   <%if(pubicTrans4.equals(Yes)){%>CHECKED <%}%>>Yes
                                            <input type="radio" name=pubicTrans4 value="<%=No%>" <%if(pubicTrans4.equals(No)) {%>CHECKED <%}%>>No</TD>
                                        <TD>Days office is open?</TD>
										<td>&nbsp;</td>
										<td align="center"> Open</td>
										<td align="center">Closed</td>
								</tr>
								<tr>
										<TD valign="bottom">Handicap Accessible?</TD>
										<TD><input type="radio" name=handicapAccess4 value="<%=Yes%>"   <%if(handicapAccess4.equals(Yes)){%>CHECKED <%}%>>Yes
                                            <input type="radio" name=handicapAccess4 value="<%=No%>" <%if(handicapAccess4.equals(No)) {%>CHECKED <%}%>>No</TD>
                                         <td></td>
                                         <td>
											<input type="checkbox" name="daysOpenMon3" id="daysOpenMon3" value="<%=daysOpenMon3%>" <%if(Yes.equals(daysOpenMon3)){%>CHECKED <%}%>/>Mon
											</td>
											<td>
											<select name="daysOpenTimeMon" id="daysOpenTimeMon">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeMon[3])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
											</td>
											<td>
											<select name="daysCloseTimeMon" id="daysCloseTimeMon">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeMon[3])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
											</td>
								</tr>
								<tr>
										<TD valign="bottom">Evening Hours?</TD>
										<TD><input type="radio" name=eveningHrs4 value="<%=Yes%>"   <%if(eveningHrs4.equals(Yes)){%>CHECKED <%}%>>Yes
                                            <input type="radio" name=eveningHrs4 value="<%=No%>" <%if(eveningHrs4.equals(No)) {%>CHECKED <%}%>>No</TD>
                                        <td></td>
                                        <td>                                            
                                           								 	<input type="checkbox" name="daysOpenTue3" id="daysOpenTue3" value="<%=daysOpenTue3%>" <%if(daysOpenTue3.equals(Yes)){%>CHECKED <%}%>/>Tue
											</td>
											<td>
											<select name="daysOpenTimeTue" id="daysOpenTimeTue">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeTue[3])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
											</td>
											<td>
											<select name="daysCloseTimeTue" id="daysCloseTimeTue">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeTue[3])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
											</td>
								</tr>
								<tr>
									<!-- 2013 SSCR 13503 changes 
									<td colspan="3">&nbsp;</td>-->
									<TD valign="bottom">List this address in the Provider Directory?</TD>
									<TD><input type="radio" name=provDir4 value="<%=Yes%>" <%if(provDir4.equals(Yes)){%>CHECKED <%}%>>Yes
            						<input type="radio" name=provDir4 value="<%=No%>" <%if(provDir4.equals(No)) {%>CHECKED <%}%>>No</TD>
     								<td>&nbsp;</td>
     								<!-- 2013 SSCR 13503 changes -->
									
									<td>
											<input type="checkbox" name="daysOpenWed3" id="daysOpenWed3" value="<%=daysOpenWed3%>" <%if(daysOpenWed3.equals(Yes)){%>CHECKED <%}%>/>Wed
											</td>
											<td>
											<select name="daysOpenTimeWed" id="daysOpenTimeWed">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeWed[3])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
											</td>
											<td><select name="daysCloseTimeWed" id="daysCloseTimeWed">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeWed[3])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
											</td>
								</tr>
								<tr>
								   <!-- 2013 SSCR 13503 changes<td> Time Zone of Office Hours?</td>
								   <td colspan ="2">
											<select name="timeZoneOfficeHrs">
												<option value=""></option>
												<option value="EST" <%if("EST".equals(timeZoneOfficeHrs[3])){ %> selected <%} %>>Eastern Time</option>
												<option value="CST" <%if("CST".equals(timeZoneOfficeHrs[3])){ %> selected <%} %>>Central Time</option>
											</select>
									</td>-->
									<td colspan="3">&nbsp;</td>
									<td>
                                            <input type="checkbox" name="daysOpenThu3" id="daysOpenThu3" value="<%=daysOpenThu3%>" <%if(daysOpenThu3.equals(Yes)){%>CHECKED <%}%>/>Thu </td>
											<td>
											<select name="daysOpenTimeThu" id="daysOpenTimeThu">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeThu[3])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
											</td>
											<td><select name="daysCloseTimeThu" id="daysCloseTimeThu">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeThu[3])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
											</td>

								</tr>
								<tr>
								<!-- 2013 SSCR 13503 changes 
									<td colspan="3">&nbsp;</td>-->
									<td> Time Zone of Office Hours?</td>
								   	<td colspan ="2">
											<select name="timeZoneOfficeHrs">
												<option value=""></option>
												<option value="EST" <%if("EST".equals(timeZoneOfficeHrs[3])){ %> selected <%} %>>Eastern Time</option>
												<option value="CST" <%if("CST".equals(timeZoneOfficeHrs[3])){ %> selected <%} %>>Central Time</option>
											</select>
									</td>
									<!-- 2013 SSCR 13503 changes -->
									<td>
                                            <input type="checkbox" name="daysOpenFri3" id="daysOpenFri3"  value="<%=daysOpenFri3%>" <%if(daysOpenFri3.equals(Yes)){%>CHECKED <%}%>/>Fri
									</td>
									<td>
											<select name="daysOpenTimeFri" id="daysOpenTimeFri">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeFri[3])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
									</td>
									<td>
											<select name="daysCloseTimeFri" id="daysCloseTimeFri">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeFri[3])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
									</td>
								</tr>
								<tr>
									<td colspan="3"></td>
									<td>
											<input type="checkbox" name="daysOpenSat3" id="daysOpenSat3" value="<%=daysOpenSat3%>" <%if(daysOpenSat3.equals(Yes)){%>CHECKED <%}%>/>Sat
									</td>
									<td>
											<select name="daysOpenTimeSat" id="daysOpenTimeSat">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeSat[3])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
									</td>
											<td>
											<select name="daysCloseTimeSat" id="daysCloseTimeSat">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeSat[3])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
									</td>
								</tr>
								<tr>
									<td colspan="3"></td>
									<td>
											<input type="checkbox" name="daysOpenSun3" id="daysOpenSun3" value="<%=daysOpenSun3%>" <%if(daysOpenSun3.equals(Yes)){%>CHECKED <%}%>/>Sun
									</td>
									<td>
											<select name="daysOpenTimeSun" id="daysOpenTimeSun">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeSun[3])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
									</td>
									<td>
											<select name="daysCloseTimeSun" id="daysCloseTimeSun">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeSun[3])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
									</td>
								</tr>
								<tr><td colspan="6">&nbsp;</td></tr>
								</table>

								<!-- //Changes for the state mandate on 02/10/10 start -->
								
								<table width="100%" cellpadding="0" cellspacing="0">
								<tr><td colspan="2">
									<b>Does This Site Offer:</b>  
									</td>
								</tr>
								<tr>
									<TD valign="bottom">ECI (Early Childhood Intervention)</TD>
									<TD><input type="radio" name=offerECI4 value="<%=Yes%>" <%if(offerECI4.equals(Yes)){%>CHECKED <%}%>>Yes 
					                	<input type="radio" name=offerECI4 value="<%=No%>" <%if(offerECI4.equals(No)) {%>CHECKED <%}%>>No (Answer required for Indiana Anthem Medicaid)
					            	</TD>
								</tr>

								<tr>
									<TD valign="bottom">EPSDT (Early and Periodic Screening, Diagnosis and Treatment)</TD>
									<TD><input type="radio" name=offerEPSDT4 value="<%=Yes%>" <%if(offerEPSDT4.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=offerEPSDT4 value="<%=No%>" <%if(offerEPSDT4.equals(No)) {%>CHECKED <%}%>>No (Answer required for Indiana Anthem Medicaid)</TD>
								</tr>
								<tr><td colspan="2">&nbsp;</td></tr>
								<tr><td colspan="2">
									<b>Do You Provide Care for:</b> 
									</td> 
								</tr>
								<tr>
									<TD valign="bottom">ABD (Aged, Blind and Disabled)</TD>
									<TD><input type="radio" name=provideADB4 value="<%=Yes%>" <%if(provideADB4.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=provideADB4 value="<%=No%>" <%if(provideADB4.equals(No)) {%>CHECKED <%}%>>No (Answer required for Indiana Anthem Medicaid)</TD>
								</tr>

								<tr>
									<TD valign="bottom">CSHCN (Children w/Special Healthcare Needs)</TD>
									<TD><input type="radio" name=provideCSHCN4 value="<%=Yes%>" <%if(provideCSHCN4.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=provideCSHCN4 value="<%=No%>" <%if(provideCSHCN4.equals(No)) {%>CHECKED <%}%>>No (Answer required for Indiana Anthem Medicaid)</TD>
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
									<TD><input type="radio" name=matWaiveredPrescriber4 value="<%=Yes%>" <%if(matWaiveredPrescriber4.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=matWaiveredPrescriber4 value="<%=No%>" <%if(matWaiveredPrescriber4.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								<tr>
									<TD valign="bottom">This office location is designated as a Certified Opioid Treatment Program</TD>
									<TD><input type="radio" name=certOpioidTreat4 value="<%=Yes%>" <%if(certOpioidTreat4.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=certOpioidTreat4 value="<%=No%>" <%if(certOpioidTreat4.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								<tr>
									<TD valign="bottom">This office location offers Medication Assisted Treatment (MAT) for Opioid Use Disorders</TD>
									<TD><input type="radio" name=matOpioid4 value="<%=Yes%>" <%if(matOpioid4.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=matOpioid4 value="<%=No%>" <%if(matOpioid4.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								<tr>
									<TD valign="bottom">Counseling in conjunction with Medication Assisted Treatment (MAT) for Opioid Use Disorders is provided at this location</TD>
									<TD><input type="radio" name=counselOpioid4 value="<%=Yes%>" <%if(counselOpioid4.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=counselOpioid4 value="<%=No%>" <%if(counselOpioid4.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								<tr>
									<TD valign="bottom">This office location is designated as a Substance Use Disorder (SUD) provider</TD>
									<TD><input type="radio" name=sudProv4 value="<%=Yes%>" <%if(sudProv4.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=sudProv4 value="<%=No%>" <%if(sudProv4.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								<tr>
									<TD valign="bottom">This office location is a Residential Treatment Center</TD>
									<TD><input type="radio" name=resTreatCtr4 value="<%=Yes%>" <%if(resTreatCtr4.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=resTreatCtr4 value="<%=No%>" <%if(resTreatCtr4.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								
								<tr><td colspan="2">&nbsp;</td></tr>
								<tr><td colspan="2">
									<b>Telehealth Services:</b> 
									</td> 
								</tr>
								<tr>
									<TD valign="bottom">Do you provide Telehealth Services at this location? (If marked yes, Telehealth will be displayed in the directory.)</TD>
									<TD><input type="radio" name=provideTelehealth4 value="<%=Yes%>" <%if(provideTelehealth4.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=provideTelehealth4 value="<%=No%>" <%if(provideTelehealth4.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>

								<tr><td colspan="2">&nbsp;</td></tr>
								<tr>
								<TD valign="bottom"><b>National Provider Identification Number:</b></TD>
									<TD><input type="text" size="10" maxlength="10" name="pracNPINo4" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracNPINo4)%>">(9999999999)</TD>
								</tr>
								
								<tr><td colspan="2">&nbsp;</td></tr>

								<tr><td colspan="2"><b>Please enter Medicare Information:</b></td></tr>
								<tr>
									<td valign="bottom">Medicare Group Number:</td>
									<TD><input type="text" size="10" maxlength="10" name="billMedicareGroup4" value="<%=ESAPI.encoder().encodeForHTMLAttribute(billMedicareGroup4)%>"></TD>
								</tr>

								<tr>
									<td valign="bottom">Medicare Individual Number:</td>
									<TD><input type="text" size="10" maxlength="10" name="billMedicareIndividual4" value="<%=ESAPI.encoder().encodeForHTMLAttribute(billMedicareIndividual4)%>"></TD>
								</tr>
                                 <tr><td colspan="2">&nbsp;</td></tr>

								<tr><td colspan="2"><b>Please enter Medicaid Information:</b></td></tr>
								<tr>
									<td valign="bottom">Medicaid Group Number plus alpha:</td>
									<TD><input type="text" size="10" maxlength="10" name="medicaidGroup4" value="<%=ESAPI.encoder().encodeForHTMLAttribute(medicaidGroup4)%>"></TD>
								</tr>

								<tr>
									<td valign="bottom">Medicaid Individual Number:</td>
									<TD><input type="text" size="10" maxlength="10" name="medicaidIndividual4" value="<%=ESAPI.encoder().encodeForHTMLAttribute(medicaidIndividual4)%>"></TD>
								</tr>
								
								<!-- PMF Section G Changes -- AD21239 -->
								<tr><td colspan="2">&nbsp;</td></tr>
								<tr><td colspan="2"><b>Please enter Kentucky Medicaid Program Information:</b></td></tr>
								<tr>
									<td valign="bottom">Are you currently participating in the Kentucky Medicaid Program? </td>
									<TD><input type="radio" name=kyMedicaidPart4 value="<%=Yes%>"   <%if(kyMedicaidPart4.equals(Yes)){%>CHECKED <%}%>>Yes
                                        <input type="radio" name=kyMedicaidPart4 value="<%=No%>" <%if(kyMedicaidPart4.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								<%-- <%if(kentuckyMediProgSecG.equals(No)){%> --%>
								<tr>
									<td valign="bottom">Kentucky Medicaid ID:</td>
									<TD><input type="text" size="10" maxlength="10" name="kyMedicaidId4" value="<%=ESAPI.encoder().encodeForHTMLAttribute(kyMedicaidId4)%>"></TD>
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
								<!-- PMF Section G Changes -- AD21239 -->
							</table>

						</td>
					</tr>
				<tr>
						<td colspan="2">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr><td colspan="2"><b>Fourth Practice Information:</b></td></tr>
								<tr><td>&nbsp;</td>
								<td><b>Practice Address Information:</b></td>
								<td><b>Address for Payment Information **Required</b></td></tr>
								
								<tr>
								<td colspan="2">&nbsp;</td>
								<TD valign="bottom">Same as Practice Address
                						<%	String billingAddressSame5 = ESAPI.encoder().encodeForHTMLAttribute(billAddressSame5);
												if (billAddressSame5.equalsIgnoreCase("Y"))
											{ %>
													<input type="checkbox" name=billAddressSame5 value="<%=billingAddressSame5%>" checked>
										<%	}
											else
											{%>
                            						<input type="checkbox" name=billAddressSame5 value="<%=billingAddressSame5%>">
										<%	} %>
								</TD>
								</tr>
								<!-- 2013 SSCR 13503 changes-->
								<tr>
								<td colspan="2">
								<TD valign="bottom">Remit for this Practice Address is same</TD>
  								</tr>
  								<tr>
								<td colspan="2">
								<TD valign="bottom">as Section E &nbsp;&nbsp;
      							<%	if (remitSamePrim5.equalsIgnoreCase("Y")) 	{ %>
								<input type="checkbox" name=remitSamePrim5 value="<%= remitSamePrim5 %>" checked>
	 							<%	}
								else
								{%>
           						<input type="checkbox" name= remitSamePrim5 value="<%= remitSamePrim5 %>">
								<%	} %>
								</TD>
    							</tr>
    							<!-- 2013 SSCR 13503 changes-->
								<tr>
									<TD valign="bottom">Fourth Practice Name:</TD>
									<TD><input type="text" size="55" maxlength="55" name="pracName5" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracName5)%>"></TD>
									<TD>&nbsp;</TD>
								</tr>
								
								<tr>
									<TD valign="bottom">Street Address:</TD>
									<TD><input type="text" size="40" maxlength="70" name="pracOfficeAddress5" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeAddress5)%>"></TD>
									<TD><input type="text" size="40" maxlength="70" name="pracBillAddress5" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillAddress5)%>"></TD>
								</tr>

								<tr>
									<TD valign="bottom">City:</TD>
									<TD><input type="text" size="19" maxlength="19" name="pracOfficeCity5" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeCity5)%>"></TD>
									<TD><input type="text" size="19" maxlength="19" name="pracBillCity5" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillCity5)%>"></TD>
								</tr>

								<tr>
									<TD valign="bottom">State:</TD>
									<TD><input type="text" size="2" maxlength="2" name="pracOfficeState5" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeState5)%>"></TD>
									<TD><input type="text" size="2" maxlength="2" name="pracBillState5" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillState5)%>"></TD>
								</tr>

								<tr>
									<TD valign="bottom">Zip:</TD>
									<TD><input type="text" size="10" maxlength="10" name="pracOfficeZip5" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeZip5)%>">(99999-9999)</TD>
									<TD><input type="text" size="11" maxlength="10" name="pracBillZip5" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillZip5)%>">(99999-9999)</TD>
								</tr>


								<tr>
									<TD valign="bottom">County:</TD>
									<TD><input type="text" size="20" maxlength="20" name="pracOfficeCounty5" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeCounty5)%>"></TD>
									<TD><input type="text" size="20" maxlength="20" name="pracBillCounty5" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillCounty5)%>"></TD>
								</tr>


								<tr>
									<TD valign="bottom">Phone Number(for patient appointments):</TD>
									<TD><input type="text" size="10" maxlength="10" name="pracOfficePhone5" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficePhone5)%>">(9999999999)</TD>
									<TD><input type="text" size="10" maxlength="10" name="pracBillPhone5" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillPhone5)%>">(9999999999)</TD>
									
								</tr>


								<tr>
									<TD valign="bottom">Fax Number:</TD>
									<TD><input type="text" size="10" maxlength="10" name="pracOfficeFax5" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeFax5)%>">(9999999999)</TD>
									<TD><input type="text" size="10" maxlength="10" name="pracBillFax5" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillFax5)%>">(9999999999)</TD>
								</tr>


								<tr>
									<TD valign="bottom">Group Email Address:</TD>
									<TD><input type="text" size="40" maxlength="40" name="pracOfficeEmail5" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeEmail5)%>"></TD>
								</tr>
								
								<!-- //Changes for the state mandate on 02/10/10 start -->
								<tr>
									<TD valign="bottom">Languages spoken by Office Staff:</TD>
									<TD><input type="text" size="40" maxlength="70" name="languagesSpoken5" value="<%=ESAPI.encoder().encodeForHTMLAttribute(languagesSpoken5)%>"></TD>
								</tr>
								
								<tr>
									<TD valign="bottom">Email Address for Payment:</TD>
									<td>&nbsp;</td>
									<TD><input type="text" size="40" maxlength="40" name="pracBillContactEmail5" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillContactEmail5)%>"></TD>
								</tr>	
								
								<tr>
									<TD valign="bottom">Contact Name for Payment:</TD>
									<td>&nbsp;</td>
									<TD><input type="text" size="40" maxlength="40" name="pracBillContactName5" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillContactName5)%>"></TD>
								</tr>	
								<tr><td colspan="3">&nbsp;</td></tr>
								</table>						
								<!-- //Changes for the state mandate on 02/10/10 end -->
																
								<table width="100%" cellspacing="0" cellpadding="0">
								<tr>
										<TD valign="bottom">Access to Public Transportation?</TD>
										<TD><input type="radio" name=pubicTrans5 value="<%=Yes%>"   <%if(pubicTrans5.equals(Yes)){%>CHECKED <%}%>>Yes
					                        <input type="radio" name=pubicTrans5 value="<%=No%>" <%if(pubicTrans5.equals(No)) {%>CHECKED <%}%>>No</TD>
					                    <TD>Days office is open?</TD>
										<td>&nbsp;</td>
										<td align="center"> Open</td>
										<td align="center">Closed</td> 
								</tr>
								<tr>
										<TD valign="bottom">Handicap Accessible?</TD>
										<TD><input type="radio" name=handicapAccess5 value="<%=Yes%>"   <%if(handicapAccess5.equals(Yes)){%>CHECKED <%}%>>Yes
					                        <input type="radio" name=handicapAccess5 value="<%=No%>" <%if(handicapAccess5.equals(No)) {%>CHECKED <%}%>>No</TD>
					                    <td>&nbsp;</td>
					                    <td>
											<input type="checkbox" name="daysOpenMon4" id="daysOpenMon4" value="<%=daysOpenMon4%>" <%if(Yes.equals(daysOpenMon4)){%>CHECKED <%}%>/>Mon
										</td>
										<td>
											<select name="daysOpenTimeMon" id="daysOpenTimeMon">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeMon[4])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
										</td>
										<td>
											<select name="daysCloseTimeMon" id="daysCloseTimeMon">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeMon[4])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
										</td>
								</tr>
								<tr>
										<TD valign="bottom">Evening Hours?</TD>
										<TD><input type="radio" name=eveningHrs5 value="<%=Yes%>"   <%if(eveningHrs5.equals(Yes)){%>CHECKED <%}%>>Yes
					                        <input type="radio" name=eveningHrs5 value="<%=No%>" <%if(eveningHrs5.equals(No)) {%>CHECKED <%}%>>No</TD>
					                     <td>&nbsp;</td>
					                     <td>                                            
                                            	<input type="checkbox" name="daysOpenTue4" id="daysOpenTue4" value="<%=daysOpenTue4%>" <%if(daysOpenTue4.equals(Yes)){%>CHECKED <%}%>/>Tue
										</td>
										<td>
											<select name="daysOpenTimeTue" id="daysOpenTimeTue">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeTue[4])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
										</td>
										<td>
											<select name="daysCloseTimeTue" id="daysCloseTimeTue">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeTue[4])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
										</td>
								</tr>
								<tr>
								<!-- 2013 SSCR 13503 changes 
									<td colspan="3">&nbsp;</td>-->
									<TD valign="bottom">List this address in the Provider Directory?</TD>
									<TD><input type="radio" name=provDir5 value="<%=Yes%>" <%if(provDir5.equals(Yes)){%>CHECKED <%}%>>Yes
            						<input type="radio" name=provDir5 value="<%=No%>" <%if(provDir5.equals(No)) {%>CHECKED <%}%>>No</TD>
     								<td>&nbsp;</td>
     								<!-- 2013 SSCR 13503 changes -->
									<td>
											<input type="checkbox" name="daysOpenWed4" id="daysOpenWed4" value="<%=daysOpenWed4%>" <%if(daysOpenWed4.equals(Yes)){%>CHECKED <%}%>/>Wed
											</td>
											<td>
											<select name="daysOpenTimeWed" id="daysOpenTimeWed">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeWed[4])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
											</td>
											<td><select name="daysCloseTimeWed" id="daysCloseTimeWed">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeWed[4])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
											</td>
								</tr>
								<tr>
								<!-- 2013 SSCR 13503 changes 
								   <td> Time Zone of Office Hours?</td>
								   <td>
											<select name="timeZoneOfficeHrs">
												<option value=""></option>
												<option value="EST" <%if("EST".equals(timeZoneOfficeHrs[4])){ %> selected <%} %>>Eastern Time</option>
												<option value="CST" <%if("CST".equals(timeZoneOfficeHrs[4])){ %> selected <%} %>>Central Time</option>
											</select>
									</td>-->
									<td colspan="3">&nbsp;</td>
									<td>
                                          	<input type="checkbox" name="daysOpenThu4" id="daysOpenThu4" value="<%=daysOpenThu4%>" <%if(daysOpenThu4.equals(Yes)){%>CHECKED <%}%>/>Thu </td>
											<td>
											<select name="daysOpenTimeThu" id="daysOpenTimeThu">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeThu[4])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
											</td>
											<td><select name="daysCloseTimeThu" id="daysCloseTimeThu">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeThu[4])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
											</td>

								</tr>
								<tr>
								<!-- 2013 SSCR 13503 changes -->
								   <td> Time Zone of Office Hours?</td>
								   <td colspan="2">
											<select name="timeZoneOfficeHrs">
												<option value=""></option>
												<option value="EST" <%if("EST".equals(timeZoneOfficeHrs[4])){ %> selected <%} %>>Eastern Time</option>
												<option value="CST" <%if("CST".equals(timeZoneOfficeHrs[4])){ %> selected <%} %>>Central Time</option>
											</select>
									</td>
									<!--  <td colspan="3">&nbsp;</td>-->
									<td>
                                            <input type="checkbox" name="daysOpenFri4" id="daysOpenFri4"  value="<%=daysOpenFri4%>" <%if(daysOpenFri4.equals(Yes)){%>CHECKED <%}%>/>Fri
											</td>
											<td>
											<select name="daysOpenTimeFri" id="daysOpenTimeFri">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeFri[4])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
											</td>
											<td><select name="daysCloseTimeFri" id="daysCloseTimeFri">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeFri[4])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
											</td>
								</tr>
								<tr>
									<td colspan="3">&nbsp;</td>
									<td>
											<input type="checkbox" name="daysOpenSat4" id="daysOpenSat4" value="<%=daysOpenSat4%>" <%if(daysOpenSat4.equals(Yes)){%>CHECKED <%}%>/>Sat
											</td>
											<td>
											<select name="daysOpenTimeSat" id="daysOpenTimeSat">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeSat[4])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
											</td>
											<td>
											<select name="daysCloseTimeSat" id="daysCloseTimeSat">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeSat[4])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
											</td>
										</tr>
										<tr>
										<td colspan="3">&nbsp;</td>
										<td>
											<input type="checkbox" name="daysOpenSun4" id="daysOpenSun4" value="<%=daysOpenSun4%>" <%if(daysOpenSun4.equals(Yes)){%>CHECKED <%}%>/>Sun
											</td>
											<td>
											<select name="daysOpenTimeSun" id="daysOpenTimeSun">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeSun[4])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
											</td>
											<td>
											<select name="daysCloseTimeSun" id="daysCloseTimeSun">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeSun[4])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
											</td>
											</tr>
											<tr><td colspan="6">&nbsp;</td></tr>
											</table>
								
								
								<!-- //Changes for the state mandate on 02/10/10 start -->
								<table width="100%" cellpadding="0" cellspacing="0">
								<tr><td colspan="2">
									<b>Does This Site Offer:</b> 
									</td> 
								</tr>
								<tr>
									<TD valign="bottom">ECI (Early Childhood Intervention)</TD>
									<TD><input type="radio" name=offerECI5 value="<%=Yes%>" <%if(offerECI5.equals(Yes)){%>CHECKED <%}%>>Yes 
					                	<input type="radio" name=offerECI5 value="<%=No%>" <%if(offerECI5.equals(No)) {%>CHECKED <%}%>>No (Answer required for Indiana Anthem Medicaid)
					            	</TD>
								</tr>

								<tr>
									<TD valign="bottom">EPSDT (Early and Periodic Screening, Diagnosis and Treatment)</TD>
									<TD><input type="radio" name=offerEPSDT5 value="<%=Yes%>" <%if(offerEPSDT5.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=offerEPSDT5 value="<%=No%>" <%if(offerEPSDT5.equals(No)) {%>CHECKED <%}%>>No (Answer required for Indiana Anthem Medicaid)</TD>
								</tr>
								<tr><td colspan="2">&nbsp;</td></tr>
								<tr><td colspan="2">
									<b>Do You Provide Care for:</b>  
									</td>
								</tr>
								<tr>
									<TD valign="bottom">ABD (Aged, Blind and Disabled)</TD>
									<TD><input type="radio" name=provideADB5 value="<%=Yes%>" <%if(provideADB5.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=provideADB5 value="<%=No%>" <%if(provideADB5.equals(No)) {%>CHECKED <%}%>>No (Answer required for Indiana Anthem Medicaid)</TD>
								</tr>

								<tr>
									<TD valign="bottom">CSHCN (Children w/Special Healthcare Needs)</TD>
									<TD><input type="radio" name=provideCSHCN5 value="<%=Yes%>" <%if(provideCSHCN5.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=provideCSHCN5 value="<%=No%>" <%if(provideCSHCN5.equals(No)) {%>CHECKED <%}%>>No (Answer required for Indiana Anthem Medicaid)</TD>
								</tr>
								<!-- //Changes for the state mandate on 02/10/10 end -->
								<tr><td colspan="2">&nbsp;</td></tr>
								
								<tr><td colspan="2">
									<b>Medication Assisted Treatment (MAT):</b> 
									</td> 
								</tr>
								<tr>
									<TD valign="bottom">The provider at this location is a waivered Prescriber for Medication Assisted Treatment (MAT) for 
									Opioid Use Disorders (DATA 2000) and is accepting patients for MAT.<br>
									(NOTE:  If you are submitting for more than one individual, please attach a list 
									indicating which individuals are waivered Prescribers for MAT.)</TD>
									<TD><input type="radio" name=matWaiveredPrescriber5 value="<%=Yes%>" <%if(matWaiveredPrescriber5.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=matWaiveredPrescriber5 value="<%=No%>" <%if(matWaiveredPrescriber5.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								<tr>
									<TD valign="bottom">This office location is designated as a Certified Opioid Treatment Program</TD>
									<TD><input type="radio" name=certOpioidTreat5 value="<%=Yes%>" <%if(certOpioidTreat5.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=certOpioidTreat5 value="<%=No%>" <%if(certOpioidTreat5.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								<tr>
									<TD valign="bottom">This office location offers Medication Assisted Treatment (MAT) for Opioid Use Disorders</TD>
									<TD><input type="radio" name=matOpioid5 value="<%=Yes%>" <%if(matOpioid5.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=matOpioid5 value="<%=No%>" <%if(matOpioid5.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								<tr>
									<TD valign="bottom">Counseling in conjunction with Medication Assisted Treatment (MAT) for Opioid Use Disorders is provided at this location</TD>
									<TD><input type="radio" name=counselOpioid5 value="<%=Yes%>" <%if(counselOpioid5.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=counselOpioid5 value="<%=No%>" <%if(counselOpioid5.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								<tr>
									<TD valign="bottom">This office location is designated as a Substance Use Disorder (SUD) provider</TD>
									<TD><input type="radio" name=sudProv5 value="<%=Yes%>" <%if(sudProv5.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=sudProv5 value="<%=No%>" <%if(sudProv5.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								<tr>
									<TD valign="bottom">This office location is a Residential Treatment Center</TD>
									<TD><input type="radio" name=resTreatCtr5 value="<%=Yes%>" <%if(resTreatCtr5.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=resTreatCtr5 value="<%=No%>" <%if(resTreatCtr5.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								
								<tr><td colspan="2">&nbsp;</td></tr>
								<tr><td colspan="2">
									<b>Telehealth Services:</b> 
									</td> 
								</tr>
								<tr>
									<TD valign="bottom">Do you provide Telehealth Services at this location? (If marked yes, Telehealth will be displayed in the directory.)</TD>
									<TD><input type="radio" name=provideTelehealth5 value="<%=Yes%>" <%if(provideTelehealth5.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=provideTelehealth5 value="<%=No%>" <%if(provideTelehealth5.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								
								<tr><td colspan="2">&nbsp;</td></tr>
								<tr>
								<TD valign="bottom"><b>National Provider Identification Number:</b></TD>
									<TD><input type="text" size="10" maxlength="10" name="pracNPINo5" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracNPINo5)%>">(9999999999)</TD>
								</tr>
								
								<tr><td colspan="2">&nbsp;</td></tr>

								<tr><td colspan="2"><b>Please enter Medicare Information:</b></td></tr>
								<tr>
									<td valign="bottom">Medicare Group Number:</td>
									<TD><input type="text" size="10" maxlength="10" name="billMedicareGroup5" value="<%=ESAPI.encoder().encodeForHTMLAttribute(billMedicareGroup5)%>"></TD>
								</tr>

								<tr>
									<td valign="bottom">Medicare Individual Number:</td>
									<TD><input type="text" size="10" maxlength="10" name="billMedicareIndividual5" value="<%=ESAPI.encoder().encodeForHTMLAttribute(billMedicareIndividual5)%>"></TD>
								</tr>
								<tr><td colspan="2">&nbsp;</td></tr>

								<tr><td colspan="2"><b>Please enter Medicaid Information:</b></td></tr>
								<tr>
									<td valign="bottom">Medicaid Group Number plus alpha:</td>
									<TD><input type="text" size="10" maxlength="10" name="medicaidGroup5" value="<%=ESAPI.encoder().encodeForHTMLAttribute(medicaidGroup5)%>"></TD>
								</tr>

								<tr>
									<td valign="bottom">Medicaid Individual Number:</td>
									<TD><input type="text" size="10" maxlength="10" name="medicaidIndividual5" value="<%=ESAPI.encoder().encodeForHTMLAttribute(medicaidIndividual5)%>"></TD>
								</tr>
								
								<!-- PMF Section G Changes -- AD21239 -->
								<tr><td colspan="2">&nbsp;</td></tr>
								<tr><td colspan="2"><b>Please enter Kentucky Medicaid Program Information:</b></td></tr>
								<tr>
									<td valign="bottom">Are you currently participating in the Kentucky Medicaid Program? </td>
									<TD><input type="radio" name=kyMedicaidPart5 value="<%=Yes%>"   <%if(kyMedicaidPart5.equals(Yes)){%>CHECKED <%}%>>Yes
                                        <input type="radio" name=kyMedicaidPart5 value="<%=No%>" <%if(kyMedicaidPart5.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								<%-- <%if(kentuckyMediProgSecG.equals(No)){%> --%>
								<tr>
									<td valign="bottom">Kentucky Medicaid ID:</td>
									<TD><input type="text" size="10" maxlength="10" name="kyMedicaidId5" value="<%=ESAPI.encoder().encodeForHTMLAttribute(kyMedicaidId5)%>"></TD>
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
								<!-- PMF Section G Changes -- AD21239 -->
							</table>

						</td>
					</tr>
				<tr>
						<td colspan="2">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr><td colspan="3"><b>Fifth Practice Information:</b></td></tr>
								<tr><td>&nbsp;</td>
								<td><b>Practice Address Information:</b></td>
								<td><b>Address for Payment Information **Required</b></td></tr>

								<tr>
								<td colspan="2">&nbsp;</td>
								<TD valign="bottom">Same as Practice Address
                						<%	String billingAddressSame6 = ESAPI.encoder().encodeForHTMLAttribute(billAddressSame6);
											if (billAddressSame6.equalsIgnoreCase("Y"))
											{ %>
													<input type="checkbox" name=billAddressSame6 value="<%=billingAddressSame6%>" checked>
										<%	}
											else
											{%>
                           	 						<input type="checkbox" name=billAddressSame6 value="<%=billingAddressSame6%>">
										<%	} %>
									</TD>
								</tr>
								<!-- 2013 SSCR 13503 changes-->
								<tr>
								<td colspan="2">
								<TD valign="bottom">Remit for this Practice Address is same</TD>
  								</tr>
  								<tr>
								<td colspan="2">
								<TD valign="bottom">as Section E &nbsp;&nbsp;
      							<%	if (remitSamePrim6.equalsIgnoreCase("Y")) 	{ %>
								<input type="checkbox" name=remitSamePrim6 value="<%= remitSamePrim6 %>" checked>
	 							<%	}
								else
								{%>
           						<input type="checkbox" name= remitSamePrim6 value="<%= remitSamePrim6 %>">
								<%	} %>
								</TD>
    							</tr>
    							<!-- 2013 SSCR 13503 changes-->
								<tr>
									<TD valign="bottom">Fifth Practice Name:</TD>
									<TD><input type="text" size="55" maxlength="55" name="pracName6" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracName6)%>"></TD>
									<TD>&nbsp;</TD>
								</tr>
								<tr>
									<TD valign="bottom">Street Address:</TD>
									<TD><input type="text" size="40" maxlength="70" name="pracOfficeAddress6" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeAddress6)%>"></TD>
									<TD><input type="text" size="40" maxlength="70" name="pracBillAddress6" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillAddress6)%>"></TD>
								</tr>

								<tr>
									<TD valign="bottom">City:</TD>
									<TD><input type="text" size="19" maxlength="19" name="pracOfficeCity6" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeCity6)%>"></TD>
									<TD><input type="text" size="19" maxlength="19" name="pracBillCity6" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillCity6)%>"></TD>
								</tr>

								<tr>
									<TD valign="bottom">State:</TD>
									<TD><input type="text" size="2" maxlength="2" name="pracOfficeState6" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeState6)%>"></TD>
									<TD><input type="text" size="2" maxlength="2" name="pracBillState6" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillState6)%>"></TD>
								</tr>

								<tr>
									<TD valign="bottom">Zip:</TD>
									<TD><input type="text" size="11" maxlength="10" name="pracOfficeZip6" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeZip6)%>">(99999-9999)</TD>
									<TD><input type="text" size="11" maxlength="10" name="pracBillZip6" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillZip6)%>">(99999-9999)</TD>
								</tr>


								<tr>
									<TD valign="bottom">County:</TD>
									<TD><input type="text" size="20" maxlength="20" name="pracOfficeCounty6" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeCounty6)%>"></TD>
									<TD><input type="text" size="20" maxlength="20" name="pracBillCounty6" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillCounty6)%>"></TD>
								</tr>


								<tr>
									<TD valign="bottom">Phone Number(for patient appointments):</TD>
									<TD><input type="text" size="10" maxlength="10" name="pracOfficePhone6" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficePhone6)%>">(9999999999)</TD>
									<TD><input type="text" size="10" maxlength="10" name="pracBillPhone6" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillPhone6)%>">(9999999999)</TD>
								</tr>


								<tr>
									<TD valign="bottom">Fax Number:</TD>
									<TD><input type="text" size="10" maxlength="10" name="pracOfficeFax6" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeFax6)%>">(9999999999)</TD>
									<TD><input type="text" size="10" maxlength="10" name="pracBillFax6" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillFax6)%>">(9999999999)</TD>
								</tr>


								<tr>
									<TD valign="bottom">Group Email Address:</TD>
									<TD><input type="text" size="40" maxlength="40" name="pracOfficeEmail6" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeEmail6)%>"></TD>
								
								</tr>
								
								<!-- //Changes for the state mandate on 02/10/10 start -->
								<tr>
									<TD valign="bottom">Languages spoken by Office Staff:</TD>
									<TD><input type="text" size="40" maxlength="70" name="languagesSpoken6" value="<%=ESAPI.encoder().encodeForHTMLAttribute(languagesSpoken6)%>"></TD>
								</tr>								
								<!-- //Changes for the state mandate on 02/10/10 end -->
								
								<tr>
									<TD valign="bottom">Email Address for Payment:</TD>
									<td>&nbsp;</td>
									<TD><input type="text" size="40" maxlength="40" name="pracBillContactEmail6" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillContactEmail6)%>"></TD>
								</tr>
								
								<tr>
									<TD valign="bottom">Contact Name for Payment:</TD>
									<td>&nbsp;</td>
									<TD><input type="text" size="40" maxlength="40" name="pracBillContactName6" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillContactName6)%>"></TD>
								</tr>
																
								<tr><td colspan="3">&nbsp;</td></tr>
								</table>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
										<TD valign="bottom">Access to Public Transportation?</TD>
										<TD><input type="radio" name=pubicTrans6 value="<%=Yes%>"   <%if(pubicTrans6.equals(Yes)){%>CHECKED <%}%>>Yes
					                        <input type="radio" name=pubicTrans6 value="<%=No%>" <%if(pubicTrans6.equals(No)) {%>CHECKED <%}%>>No</TD>
					                    <TD>Days office is open?</TD>
										<td>&nbsp;</td>
										<td align="center">Open</td>
										<td align="center">Closed</td>        
								</tr>
								<tr>
										<TD valign="bottom">Handicap Accessible?</TD>
										<TD><input type="radio" name=handicapAccess6 value="<%=Yes%>"   <%if(handicapAccess6.equals(Yes)){%>CHECKED <%}%>>Yes
					                        <input type="radio" name=handicapAccess6 value="<%=No%>" <%if(handicapAccess6.equals(No)) {%>CHECKED <%}%>>No</TD>
					                    <td>&nbsp;</td>
					                    <td>
											<input type="checkbox" name="daysOpenMon5" id="daysOpenMon5" value="<%=daysOpenMon5%>" <%if(Yes.equals(daysOpenMon5)){%>CHECKED <%}%>/>Mon
										</td>
										<td>
											<select name="daysOpenTimeMon" id="daysOpenTimeMon">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeMon[5])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
										</td>
										<td>
											<select name="daysCloseTimeMon" id="daysCloseTimeMon">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeMon[5])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
										</td>
								</tr>
								<tr>
										<TD valign="bottom">Evening Hours?</TD>
										<TD><input type="radio" name=eveningHrs6 value="<%=Yes%>"   <%if(eveningHrs6.equals(Yes)){%>CHECKED <%}%>>Yes
					                        <input type="radio" name=eveningHrs6 value="<%=No%>" <%if(eveningHrs6.equals(No)) {%>CHECKED <%}%>>No</TD>
					                    <td>&nbsp;</td>
					                    <td>                                            
                                            <input type="checkbox" name="daysOpenTue5" id="daysOpenTue5" value="<%=daysOpenTue5%>" <%if(daysOpenTue5.equals(Yes)){%>CHECKED <%}%>/>Tue
										</td>
										<td>
											<select name="daysOpenTimeTue" id="daysOpenTimeTue">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeTue[5])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
										</td>
										<td>
											<select name="daysCloseTimeTue" id="daysCloseTimeTue">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeTue[5])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
										</td>
								</tr>
								<tr><!-- 2013 SSCR 13503 changes
									<td colspan="3">&nbsp;</td>-->
									<TD valign="bottom">List this address in the Provider Directory?</TD>
									<TD><input type="radio" name=provDir6 value="<%=Yes%>" <%if(provDir6.equals(Yes)){%>CHECKED <%}%>>Yes
            						<input type="radio" name=provDir6 value="<%=No%>" <%if(provDir6.equals(No)) {%>CHECKED <%}%>>No</TD>
     								<td>&nbsp;</td>
     								<!-- 2013 SSCR 13503 changes -->
									<td>
										<input type="checkbox" name="daysOpenWed5" id="daysOpenWed5" value="<%=daysOpenWed5%>" <%if(daysOpenWed5.equals(Yes)){%>CHECKED <%}%>/>Wed
									</td>
									<td>
										<select name="daysOpenTimeWed" id="daysOpenTimeWed">
										<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
										<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeWed[5])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
										<%} %>
										</select>
									</td>
									<td>
										<select name="daysCloseTimeWed" id="daysCloseTimeWed">
										<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
										<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeWed[5])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
										<%} %>
										</select>
									</td>
								</tr>
								<tr>
								<!-- 2013 SSCR 13503 changes 
								   <td> Time Zone of Office Hours?</td>
								   <td>
											<select name="timeZoneOfficeHrs">
												<option value=""></option>
												<option value="EST" <%if("EST".equals(timeZoneOfficeHrs[5])){ %> selected <%} %>>Eastern Time</option>
												<option value="CST" <%if("CST".equals(timeZoneOfficeHrs[5])){ %> selected <%} %>>Central Time</option>
											</select>
									</td>
									-->
									<td colspan="3">&nbsp;</td>
									<td>
                                        <input type="checkbox" name="daysOpenThu5" id="daysOpenThu5" value="<%=daysOpenThu5%>" <%if(daysOpenThu5.equals(Yes)){%>CHECKED <%}%>/>Thu </td>
									<td>
										<select name="daysOpenTimeThu" id="daysOpenTimeThu">
										<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
										<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeThu[5])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
										<%} %>
										</select>
									</td>
									<td>
										<select name="daysCloseTimeThu" id="daysCloseTimeThu">
										<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
										<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeThu[5])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
										<%} %>
										</select>
									</td>
								</tr>
								<tr>
								<!-- 2013 SSCR 13503 changes -->
									<td> Time Zone of Office Hours?</td>
								   <td colspan="2">
											<select name="timeZoneOfficeHrs">
												<option value=""></option>
												<option value="EST" <%if("EST".equals(timeZoneOfficeHrs[5])){ %> selected <%} %>>Eastern Time</option>
												<option value="CST" <%if("CST".equals(timeZoneOfficeHrs[5])){ %> selected <%} %>>Central Time</option>
											</select>
									</td>
									<!--  <td colspan="3">&nbsp;</td>-->
									<!-- 2013 SSCR 13503 changes -->
									<td>
                                        <input type="checkbox" name="daysOpenFri5" id="daysOpenFri5"  value="<%=daysOpenFri5%>" <%if(daysOpenFri5.equals(Yes)){%>CHECKED <%}%>/>Fri
									</td>
									<td>
										<select name="daysOpenTimeFri" id="daysOpenTimeFri">
										<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
										<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeFri[5])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
										<%} %>
										</select>
									</td>
										<td><select name="daysCloseTimeFri" id="daysCloseTimeFri">
										<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
										<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeFri[5])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
										<%} %>
										</select>
									</td>
								</tr>
								
								<tr>
								<td colspan="3">&nbsp;</td>
								<td>
									<input type="checkbox" name="daysOpenSat5" id="daysOpenSat5" value="<%=daysOpenSat5%>" <%if(daysOpenSat5.equals(Yes)){%>CHECKED <%}%>/>Sat
								</td>
								<td>
									<select name="daysOpenTimeSat" id="daysOpenTimeSat">
									<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
									<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeSat[5])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
									<%} %>
									</select>
								</td>
								<td>
									<select name="daysCloseTimeSat" id="daysCloseTimeSat">
									<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
									<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeSat[5])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
									<%} %>
									</select>
								</td>
								
								<tr>
								<td colspan="3">&nbsp;</td>
								<td>
									<input type="checkbox" name="daysOpenSun5" id="daysOpenSun5" value="<%=daysOpenSun5%>" <%if(daysOpenSun5.equals(Yes)){%>CHECKED <%}%>/>Sun
								</td>
								<td>
									<select name="daysOpenTimeSun" id="daysOpenTimeSun">
									<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
									<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeSun[5])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
											</td>
											<td>
											<select name="daysCloseTimeSun" id="daysCloseTimeSun">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeSun[5])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
											</td>
										</tr>
										<tr><td colspan="6">&nbsp;</td></tr>
										</table>
								<!-- //Changes for the state mandate on 02/10/10 start -->
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr><td colspan="2">
									<b>Does This Site Offer:</b>
									</td>  
								</tr>
								<tr>
									<TD valign="bottom">ECI (Early Childhood Intervention)</TD>
									<TD><input type="radio" name=offerECI6 value="<%=Yes%>" <%if(offerECI6.equals(Yes)){%>CHECKED <%}%>>Yes 
					                	<input type="radio" name=offerECI6 value="<%=No%>" <%if(offerECI6.equals(No)) {%>CHECKED <%}%>>No (Answer required for Indiana Anthem Medicaid)
					            	</TD>
								</tr>

								<tr>
									<TD valign="bottom">EPSDT (Early and Periodic Screening, Diagnosis and Treatment)</TD>
									<TD><input type="radio" name=offerEPSDT6 value="<%=Yes%>" <%if(offerEPSDT6.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=offerEPSDT6 value="<%=No%>" <%if(offerEPSDT6.equals(No)) {%>CHECKED <%}%>>No (Answer required for Indiana Anthem Medicaid)</TD>
								</tr>
								<tr><td colspan="2">&nbsp;</td></tr>
								<tr><td colspan="2">
									<b>Do You Provide Care for:</b> 
									</td> 
								</tr>
								<tr>
									<TD valign="bottom">ABD (Aged, Blind and Disabled)</TD>
									<TD><input type="radio" name=provideADB6 value="<%=Yes%>" <%if(provideADB6.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=provideADB6 value="<%=No%>" <%if(provideADB6.equals(No)) {%>CHECKED <%}%>>No (Answer required for Indiana Anthem Medicaid)</TD>
								</tr>

								<tr>
									<TD valign="bottom">CSHCN (Children w/Special Healthcare Needs)</TD>
									<TD><input type="radio" name=provideCSHCN6 value="<%=Yes%>" <%if(provideCSHCN6.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=provideCSHCN6 value="<%=No%>" <%if(provideCSHCN6.equals(No)) {%>CHECKED <%}%>>No (Answer required for Indiana Anthem Medicaid)</TD>
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
									<TD><input type="radio" name=matWaiveredPrescriber6 value="<%=Yes%>" <%if(matWaiveredPrescriber6.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=matWaiveredPrescriber6 value="<%=No%>" <%if(matWaiveredPrescriber6.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								<tr>
									<TD valign="bottom">This office location is designated as a Certified Opioid Treatment Program</TD>
									<TD><input type="radio" name=certOpioidTreat6 value="<%=Yes%>" <%if(certOpioidTreat6.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=certOpioidTreat6 value="<%=No%>" <%if(certOpioidTreat6.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								<tr>
									<TD valign="bottom">This office location offers Medication Assisted Treatment (MAT) for Opioid Use Disorders</TD>
									<TD><input type="radio" name=matOpioid6 value="<%=Yes%>" <%if(matOpioid6.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=matOpioid6 value="<%=No%>" <%if(matOpioid6.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								<tr>
									<TD valign="bottom">Counseling in conjunction with Medication Assisted Treatment (MAT) for Opioid Use Disorders is provided at this location</TD>
									<TD><input type="radio" name=counselOpioid6 value="<%=Yes%>" <%if(counselOpioid6.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=counselOpioid6 value="<%=No%>" <%if(counselOpioid6.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								<tr>
									<TD valign="bottom">This office location is designated as a Substance Use Disorder (SUD) provider</TD>
									<TD><input type="radio" name=sudProv6 value="<%=Yes%>" <%if(sudProv6.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=sudProv6 value="<%=No%>" <%if(sudProv6.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								<tr>
									<TD valign="bottom">This office location is a Residential Treatment Center</TD>
									<TD><input type="radio" name=resTreatCtr6 value="<%=Yes%>" <%if(resTreatCtr6.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=resTreatCtr6 value="<%=No%>" <%if(resTreatCtr6.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								
								<tr><td colspan="2">&nbsp;</td></tr>
								<tr><td colspan="2">
									<b>Telehealth Services:</b> 
									</td> 
								</tr>
								<tr>
									<TD valign="bottom">Do you provide Telehealth Services at this location? (If marked yes, Telehealth will be displayed in the directory.)</TD>
									<TD><input type="radio" name=provideTelehealth6 value="<%=Yes%>" <%if(provideTelehealth6.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=provideTelehealth6 value="<%=No%>" <%if(provideTelehealth6.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								
								<tr><td colspan="2">&nbsp;</td></tr>
								<tr>
								
								<TD valign="bottom"><b>National Provider Identification Number:</b></TD>
									<TD><input type="text" size="10" maxlength="10" name="pracNPINo6" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracNPINo6)%>">(9999999999)</TD>
								</tr>
								
								<tr><td colspan="2">&nbsp;</td></tr>

								<tr><td colspan="2"><b>Please enter Medicare Information:</b></td></tr>
								<tr>
									<td valign="bottom">Medicare Group Number:</td>
									<TD><input type="text" size="10" maxlength="10" name="billMedicareGroup6" value="<%=ESAPI.encoder().encodeForHTMLAttribute(billMedicareGroup6)%>"></TD>
								</tr>

								<tr>
									<td valign="bottom">Medicare Individual Number:</td>
									<TD><input type="text" size="10" maxlength="10" name="billMedicareIndividual6" value="<%=ESAPI.encoder().encodeForHTMLAttribute(billMedicareIndividual6)%>"></TD>
								</tr>
								<tr><td colspan="2">&nbsp;</td></tr>

								<tr><td colspan="2"><b>Please enter Medicaid Information:</b></td></tr>
								<tr>
									<td valign="bottom">Medicaid Group Number plus alpha:</td>
									<TD><input type="text" size="10" maxlength="10" name="medicaidGroup6" value="<%=ESAPI.encoder().encodeForHTMLAttribute(medicaidGroup6)%>"></TD>
								</tr>

								<tr>
									<td valign="bottom">Medicaid Individual Number:</td>
									<TD><input type="text" size="10" maxlength="10" name="medicaidIndividual6" value="<%=ESAPI.encoder().encodeForHTMLAttribute(medicaidIndividual6)%>"></TD>
								</tr>
								
								<!-- PMF Section G Changes -- AD21239 -->
								<tr><td colspan="2">&nbsp;</td></tr>
								<tr><td colspan="2"><b>Please enter Kentucky Medicaid Program Information:</b></td></tr>
								<tr>
									<td valign="bottom">Are you currently participating in the Kentucky Medicaid Program? </td>
									<TD><input type="radio" name=kyMedicaidPart6 value="<%=Yes%>"   <%if(kyMedicaidPart6.equals(Yes)){%>CHECKED <%}%>>Yes
                                        <input type="radio" name=kyMedicaidPart6 value="<%=No%>" <%if(kyMedicaidPart6.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								<%-- <%if(kentuckyMediProgSecG.equals(No)){%> --%>
								<tr>
									<td valign="bottom">Kentucky Medicaid ID:</td>
									<TD><input type="text" size="10" maxlength="10" name="kyMedicaidId6" value="<%=ESAPI.encoder().encodeForHTMLAttribute(kyMedicaidId6)%>"></TD>
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
								<!-- PMF Section G Changes -- AD21239 -->
							</table>
								</td>
					</tr>
				</table>
</body>
</html>