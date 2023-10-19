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
<!--BEGIN FORM SECTION F-->
		<table BORDER="1" CELLSPACING=0 CELLPADDING=7 WIDTH="100%">
					<tr>
						<td valign="top" align="center" colspan="2" BGCOLOR="#000080"><font size="2" COLOR="#ffffff"><b><%=Constants.sectionAddressChange%> -- Address Information Change <i>(Complete only if changing address.)</i></b></font></td>
					</tr>

					<%	Iterator theDataF = errorMessagesVectorF.iterator();
							if (!errorMessagesVectorF.isEmpty())
							{%>
								<tr>
									<td valign="top" align="center" colspan="2" BGCOLOR="#FF0000"><font size="2" COLOR="#ffffff"><b>Error Messages</b></font></td>
								</tr>
								<tr>
									<td colspan="2" BGCOLOR="#ffffff"><font size="2" COLOR="#000080"><b>The following errors were detected.  Please correct the form entries and resubmit form.</b></font></td>
								</tr>
							<%	while(theDataF.hasNext())
								{
									String value = (String)theDataF.next(); %>
									<tr>
										<TD colspan="2" style="FONT-SIZE: large;"><font size="2" COLOR="#FF0000"><b><%=value%></b></font></TD>
									</tr>
							<%	}  %>
						<%	} %>
					
					<tr>
						<td valign="top" BGCOLOR="#ffffff" width="5%"><font size="2" COLOR="#000080"><b>1.&nbsp;&nbsp;&nbsp;</b></font></td>
						<td BGCOLOR="#ffffff" width="95%"><font size="2" COLOR="#000080"><b>Enter your new practice address and address for payment here. (Both are required.)</b></font></td>
					</tr>

					<tr>
						<td valign="top" BGCOLOR="#ffffff" width="5%"><font size="2" COLOR="#000080"><b>2.&nbsp;&nbsp;&nbsp;</b></font></td>
						<td BGCOLOR="#ffffff" width="95%"><font size="2" COLOR="#000080"><b>It is unacceptable to put "same", "same as practice address" or "see above".  Any of these entries will be rejected.</b></font></td>
					</tr>
					<tr>
						<td valign="top" BGCOLOR="#ffffff" width="5%"><font size="2" COLOR="#000080"><b>3.&nbsp;&nbsp;&nbsp;</b></td>
						<td BGCOLOR="#ffffff" width="95%"><font size="2" COLOR="#000080"><b>If you need help finding your 9 digit Zip Code, go to <a href= "https://tools.usps.com/go/ZipLookupAction!input.action" target="_blank">USPS.com&reg; - ZIP Code&trade; Lookup</a> for additional information.</b></font></td>			
					</tr>
					<tr>
						<td valign="top" BGCOLOR="#ffffff" width="5%"><font size="2" COLOR="#000080"><b>4.&nbsp;&nbsp;&nbsp;</b></td>
						<td BGCOLOR="#ffffff" width="95%"><font size="2" COLOR="#000080"><b>Phone numbers for Practice address should be the phone number that patients would call to make an appointment.</b></font></td>						
					</tr>
					
					<tr>
						<td colspan="2">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td>&nbsp;</td>
									<td><b>Practice Address Information:</b></td>
									<td><b>Address for Payment Information **Required</b></td>
								</tr>
								
								<tr>
									<td colspan="2">
									<TD valign="bottom">Same as Practice Address
                						<%	String billingAddressSame2 = ESAPI.encoder().encodeForHTMLAttribute(billAddressSame2);
											if (billAddressSame2.equalsIgnoreCase("Y"))                							
											{ %>
													<input type="checkbox" name=billAddressSame2 value="<%=billingAddressSame2%>" checked>
										<%	}
											else
											{%>
                            						<input type="checkbox" name=billAddressSame2 value="<%=billingAddressSame2%>">
										<%	} %>
									</TD>
								</tr>
								<!-- 2013 SSCR 13503 changes -->
								<tr>
								<td colspan="2">
								<TD valign="bottom">Remit for this Practice Address is same</TD>
 								 </tr>
 								 <tr>
								<td colspan="2">
								<TD valign="bottom">as Section E &nbsp;&nbsp;
      								<%	if (remitSamePrim2.equalsIgnoreCase("Y")) 	{ %>
								<input type="checkbox" name=remitSamePrim2 value="<%= remitSamePrim2 %>" checked>
	 								<%	}
								else
								{%>
           							<input type="checkbox" name= remitSamePrim2 value="<%= remitSamePrim2 %>">
								<%	} %>
								</TD>
    							</tr>
								<!-- 2013 SSCR 13503 changes -->
								<tr>
									<TD valign="bottom">Street Address:</TD>
									<TD><input type="text" size="40" maxlength="70" name="pracOfficeAddress2" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeAddress2)%>"></TD>
									<TD><input type="text" size="40" maxlength="70" name="pracBillAddress2" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillAddress2)%>"></TD>
								</tr>

								<tr>
									<TD valign="bottom">City:</TD>
									<TD><input type="text" size="19" maxlength="19" name="pracOfficeCity2" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeCity2)%>"></TD>
									<TD><input type="text" size="19" maxlength="19" name="pracBillCity2" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillCity2)%>"></TD>
								</tr>

								<tr>
									<TD valign="bottom">State:</TD>
									<TD><input type="text" size="2" maxlength="2" name="pracOfficeState2" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeState2)%>"></TD>
									<TD><input type="text" size="2" maxlength="2" name="pracBillState2" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillState2)%>"></TD>
								</tr>

								<tr>
									<TD valign="bottom">Zip:</TD>
									<TD><input type="text" size="11" maxlength="10" name="pracOfficeZip2" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeZip2)%>">(99999-9999)</TD>
									<TD><input type="text" size="11" maxlength="10" name="pracBillZip2" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillZip2)%>">(99999-9999)</TD>
								</tr>


								<tr>
									<TD valign="bottom">County:</TD>
									<TD><input type="text" size="20" maxlength="20" name="pracOfficeCounty2" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeCounty2)%>"></TD>
									<TD><input type="text" size="20" maxlength="20" name="pracBillCounty2" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillCounty2)%>"></TD>
								</tr>


								<tr>
									<TD valign="bottom">Phone Number(for patient appointments):</TD>
									<TD><input type="text" size="10" maxlength="10" name="pracOfficePhone2" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficePhone2)%>">(9999999999)</TD>
									<TD><input type="text" size="10" maxlength="10" name="pracBillPhone2" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillPhone2)%>">(9999999999)</TD>
								</tr>


								<tr>
									<TD valign="bottom">Fax Number:</TD>
									<TD><input type="text" size="10" maxlength="10" name="pracOfficeFax2" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeFax2)%>">(9999999999)</TD>
									<TD><input type="text" size="10" maxlength="10" name="pracBillFax2" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillFax2)%>">(9999999999)</TD>
								</tr>


								<tr>
									<TD valign="bottom">Group Email Address:</TD>
									<TD><input type="text" size="40" maxlength="40" name="pracOfficeEmail2" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracOfficeEmail2)%>"></TD>
								</tr>
								
								<!-- //Changes for the state mandate on 02/10/10 start -->
								<tr>
									<TD valign="bottom">Languages spoken by Office Staff:</TD>
									<TD><input type="text" size="40" maxlength="70" name="languagesSpoken2" value="<%=ESAPI.encoder().encodeForHTMLAttribute(languagesSpoken2)%>"></TD>
								</tr>	
								
								<tr>
									<TD valign="bottom" width="298">Email Address for Payment:</TD>
									<td></td>
									<TD><input type="text" size="40" maxlength="40" name="pracBillContactEmail2" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillContactEmail2)%>"></TD>
								</tr>
									
								<tr>
									<TD valign="bottom" width="298">Contact Name for Payment:</TD>
									<td></td>
									<TD><input type="text" size="40" maxlength="40" name="pracBillContactName2" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracBillContactName2)%>"></TD>
								</tr>
								<tr><td colspan="6">&nbsp;</td></tr>
								</table>							
								<!-- //Changes for the state mandate on 02/10/10 end -->
								
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
										<TD valign="bottom">Access to Public Transportation?</TD>
										<TD><input type="radio" name=pubicTrans2 value="<%=Yes%>"   <%if(pubicTrans2.equals(Yes)){%>CHECKED <%}%>>Yes
                                            <input type="radio" name=pubicTrans2 value="<%=No%>" <%if(pubicTrans2.equals(No)) {%>CHECKED <%}%>>No</TD>
                                        <TD valign="Top">Days office is open?</TD>
										<td>&nbsp;</td>
										<td align="center"> Open</td>
										<td align="center">Closed</td>
                                       
								</tr>
								<tr>
										<TD valign="bottom">Handicap Accessible?</TD>
										<TD><input type="radio" name=handicapAccess2 value="<%=Yes%>"   <%if(handicapAccess2.equals(Yes)){%>CHECKED <%}%>>Yes
                                            <input type="radio" name=handicapAccess2 value="<%=No%>" <%if(handicapAccess2.equals(No)) {%>CHECKED <%}%>>No</TD>
                                        <td>&nbsp;</td>
										<td>
										<input type="checkbox" name="daysOpenMon1" id="daysOpenMon1" value="<%=daysOpenMon1%>" <%if(Yes.equals(daysOpenMon1)){%>CHECKED <%}%>/>Mon
										</td>
										<td>
										<select name="daysOpenTimeMon" id="daysOpenTimeMon">
										<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
										<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeMon[1])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
										<%} %>
										</select>
										</td>
										<td>
										<select name="daysCloseTimeMon" id="daysCloseTimeMon">
										<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
										<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeMon[1])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
										<%} %>
										</select>
										</td>
                                        
								</tr>
								<tr>
										<TD valign="bottom">Evening Hours?</TD>
										<TD><input type="radio" name=eveningHrs2 value="<%=Yes%>"   <%if(eveningHrs2.equals(Yes)){%>CHECKED <%}%>>Yes
                                            <input type="radio" name=eveningHrs2 value="<%=No%>" <%if(eveningHrs2.equals(No)) {%>CHECKED <%}%>>No</TD>
										<td>&nbsp;</td>
										<td>                                            
											<input type="checkbox" name="daysOpenTue1" id="daysOpenTue1" value="<%=daysOpenTue1%>" <%if(daysOpenTue1.equals(Yes)){%>CHECKED <%}%>/>Tue
										</td>
										<td>
											<select name="daysOpenTimeTue" id="daysOpenTimeTue">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeTue[1])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
										</td>
										<td>
											<select name="daysCloseTimeTue" id="daysCloseTimeTue">
											<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
											<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeTue[1])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
											<%} %>
											</select>
										</td>
								</tr>
								<tr>
								<!-- 2013 SSCR 13503 changes 
									<td colspan="3">&nbsp;</td> -->
									<TD valign="bottom">List this address in the Provider Directory?</TD>
									<TD><input type="radio" name=provDir2 value="<%=Yes%>" <%if(provDir2.equals(Yes)){%>CHECKED <%}%>>Yes
            						<input type="radio" name=provDir2 value="<%=No%>" <%if(provDir2.equals(No)) {%>CHECKED <%}%>>No</TD>
    								<td>&nbsp;</td>	
    								<!-- 2013 SSCR 13503 changes -->								
									<td>
										<input type="checkbox" name="daysOpenWed1" id="daysOpenWed1" value="<%=daysOpenWed1%>" <%if(daysOpenWed1.equals(Yes)){%>CHECKED <%}%>/>Wed
									</td>
									<td>
										<select name="daysOpenTimeWed" id="daysOpenTimeWed">
										<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
										<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeWed[1])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
										<%} %>
										</select>
									</td>
									<td><select name="daysCloseTimeWed" id="daysCloseTimeWed">
										<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
										<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeWed[1])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
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
												<option value="EST" <%if("EST".equals(timeZoneOfficeHrs[1])){ %> selected <%} %>>Eastern Time</option>
												<option value="CST" <%if("CST".equals(timeZoneOfficeHrs[1])){ %> selected <%} %>>Central Time</option>
											</select>
									</td>-->
									<td colspan="3">&nbsp;</td>
									<td>
										<input type="checkbox" name="daysOpenThu1" id="daysOpenThu1" value="<%=daysOpenThu1%>" <%if(daysOpenThu1.equals(Yes)){%>CHECKED <%}%>/>Thu </td>
									<td>
										<select name="daysOpenTimeThu" id="daysOpenTimeThu">
										<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
										<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeThu[1])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
										<%} %>
										</select>
									</td>
									<td>
										<select name="daysCloseTimeThu" id="daysCloseTimeThu">
										<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
										<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeThu[1])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
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
									<option value="EST" <%if("EST".equals(timeZoneOfficeHrs[1])){ %> selected <%} %>>Eastern Time</option>
									<option value="CST" <%if("CST".equals(timeZoneOfficeHrs[1])){ %> selected <%} %>>Central Time</option>
									</select>											
									</td>
									<!-- 2013 SSCR 13503 changes -->
									
									<td>
										<input type="checkbox" name="daysOpenFri1" id="daysOpenFri1"  value="<%=daysOpenFri1%>" <%if(daysOpenFri1.equals(Yes)){%>CHECKED <%}%>/>Fri
									</td>
									<td>
										<select name="daysOpenTimeFri" id="daysOpenTimeFri">
										<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
										<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeFri[1])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
										<%} %>
										</select>
									</td>
									<td>
										<select name="daysCloseTimeFri" id="daysCloseTimeFri">
										<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
										<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeFri[1])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
										<%} %>
										</select>
									</td>
								</tr>
								
								<tr>
									<td colspan="3">&nbsp;</td>
									<td>
										<input type="checkbox" name="daysOpenSat1" id="daysOpenSat1" value="<%=daysOpenSat1%>" <%if(daysOpenSat1.equals(Yes)){%>CHECKED <%}%>/>Sat
									</td>
									<td>
										<select name="daysOpenTimeSat" id="daysOpenTimeSat">
										<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
										<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeSat[1])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
										<%} %>
										</select>
									</td>
									<td>
										<select name="daysCloseTimeSat" id="daysCloseTimeSat">
										<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
										<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeSat[1])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
										<%} %>
										</select>
									</td>
								</tr>
								
								<tr>
									<td colspan="3">&nbsp;</td>
									<td>
										<input type="checkbox" name="daysOpenSun1" id="daysOpenSun1" value="<%=daysOpenSun1%>" <%if(daysOpenSun1.equals(Yes)){%>CHECKED <%}%>/>Sun
									</td>
									<td>
										<select name="daysOpenTimeSun" id="daysOpenTimeSun">
										<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
										<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysOpenTimeSun[1])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
										<%} %>
										</select>
									</td>
									<td>
										<select name="daysCloseTimeSun" id="daysCloseTimeSun">
										<%for(int cntrTimes=0;cntrTimes<Constants.OFFICE_TIMINGS_DISPLAY.length;cntrTimes++) { %>
										<option value="<%=Constants.OFFICE_TIMINGS_VALUE[cntrTimes] %>" <%if(Constants.OFFICE_TIMINGS_VALUE[cntrTimes].equals(daysCloseTimeSun[1])){ %> selected <%} %>><%=Constants.OFFICE_TIMINGS_DISPLAY[cntrTimes] %></option>
										<%} %>
										</select>
									</td>							
								</tr>
								<tr><td colspan="6">&nbsp;</td></tr>
								</table>
								<table width="100%"  border="0" cellpadding="0" cellspacing="0">
								<!-- //Changes for the state mandate on 02/10/10 start -->
								<tr><td colspan="2">
									<b>Does This Site Offer:</b> 
									</td> 
								</tr>
								<tr>
									<TD valign="bottom">ECI (Early Childhood Intervention)</TD>
									<TD><input type="radio" name=offerECI2 value="<%=Yes%>" <%if(offerECI2.equals(Yes)){%>CHECKED <%}%>>Yes 
					                	<input type="radio" name=offerECI2 value="<%=No%>" <%if(offerECI2.equals(No)) {%>CHECKED <%}%>>No (Answer required for Indiana Anthem Medicaid)
					            	</TD>
								</tr>

								<tr>
									<TD valign="bottom">EPSDT (Early and Periodic Screening, Diagnosis and Treatment)</TD>
									<TD><input type="radio" name=offerEPSDT2 value="<%=Yes%>" <%if(offerEPSDT2.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=offerEPSDT2 value="<%=No%>" <%if(offerEPSDT2.equals(No)) {%>CHECKED <%}%>>No (Answer required for Indiana Anthem Medicaid)</TD>
								</tr>
								<tr><td colspan="2">&nbsp;</td></tr>
								<tr><td colspan="2">
									<b>Do You Provide Care for:</b>  
									</td>
								</tr>
								<tr>
									<TD valign="bottom">ABD (Aged, Blind and Disabled)</TD>
									<TD><input type="radio" name=provideADB2 value="<%=Yes%>" <%if(provideADB2.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=provideADB2 value="<%=No%>" <%if(provideADB2.equals(No)) {%>CHECKED <%}%>>No (Answer required for Indiana Anthem Medicaid)</TD>
								</tr>

								<tr>
									<TD valign="bottom">CSHCN (Children w/Special Healthcare Needs)</TD>
									<TD><input type="radio" name=provideCSHCN2 value="<%=Yes%>" <%if(provideCSHCN2.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=provideCSHCN2 value="<%=No%>" <%if(provideCSHCN2.equals(No)) {%>CHECKED <%}%>>No (Answer required for Indiana Anthem Medicaid)</TD>
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
									<TD><input type="radio" name=matWaiveredPrescriber2 value="<%=Yes%>" <%if(matWaiveredPrescriber2.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=matWaiveredPrescriber2 value="<%=No%>" <%if(matWaiveredPrescriber2.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								<tr>
									<TD valign="bottom">This office location is designated as a Certified Opioid Treatment Program</TD>
									<TD><input type="radio" name=certOpioidTreat2 value="<%=Yes%>" <%if(certOpioidTreat2.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=certOpioidTreat2 value="<%=No%>" <%if(certOpioidTreat2.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								<tr>
									<TD valign="bottom">This office location offers Medication Assisted Treatment (MAT) for Opioid Use Disorders</TD>
									<TD><input type="radio" name=matOpioid2 value="<%=Yes%>" <%if(matOpioid2.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=matOpioid2 value="<%=No%>" <%if(matOpioid2.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								<tr>
									<TD valign="bottom">Counseling in conjunction with Medication Assisted Treatment (MAT) for Opioid Use Disorders is provided at this location</TD>
									<TD><input type="radio" name=counselOpioid2 value="<%=Yes%>" <%if(counselOpioid2.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=counselOpioid2 value="<%=No%>" <%if(counselOpioid2.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								<tr>
									<TD valign="bottom">This office location is designated as a Substance Use Disorder (SUD) provider</TD>
									<TD><input type="radio" name=sudProv2 value="<%=Yes%>" <%if(sudProv2.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=sudProv2 value="<%=No%>" <%if(sudProv2.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								<tr>
									<TD valign="bottom">This office location is a Residential Treatment Center</TD>
									<TD><input type="radio" name=resTreatCtr2 value="<%=Yes%>" <%if(resTreatCtr2.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=resTreatCtr2 value="<%=No%>" <%if(resTreatCtr2.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								
								<tr><td colspan="2">&nbsp;</td></tr>
								<tr><td colspan="2">
									<b>Telehealth Services:</b> 
									</td> 
								</tr>
								<tr>
									<TD valign="bottom">Do you provide Telehealth Services at this location? (If marked yes, Telehealth will be displayed in the directory.)</TD>
									<TD><input type="radio" name=provideTelehealth2 value="<%=Yes%>" <%if(provideTelehealth2.equals(Yes)){%>CHECKED <%}%>>Yes 
				                        <input type="radio" name=provideTelehealth2 value="<%=No%>" <%if(provideTelehealth2.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								
								<tr><td colspan="2">&nbsp;</td></tr>
								<tr>
								<TD valign="bottom"><b>National Provider Identification Number:</b></TD>
									<TD><input type="text" size="10" maxlength="10" name="pracNPINo2" value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracNPINo2)%>">(9999999999)</TD>
								</tr>
								
								<tr><td colspan="2">&nbsp;</td></tr>

								<tr><td colspan="2"><b>Please enter Medicare Information:</b></td></tr>
								<tr>
									<td valign="bottom">Medicare Group Number:</td>
									<TD><input type="text" size="10" maxlength="10" name="billMedicareGroup2" value="<%=ESAPI.encoder().encodeForHTMLAttribute(billMedicareGroup2)%>"></TD>
								</tr>

								<tr>
									<td valign="bottom">Medicare Individual Number:</td>
									<TD><input type="text" size="10" maxlength="10" name="billMedicareIndividual2" value="<%=ESAPI.encoder().encodeForHTMLAttribute(billMedicareIndividual2)%>"></TD>
								</tr>
								<tr><td colspan="2">&nbsp;</td></tr>

								<tr><td colspan="2"><b>Please enter Medicaid Information:</b></td></tr>
								<tr>
									<td valign="bottom">Medicaid Group Number plus alpha:</td>
									<TD><input type="text" size="10" maxlength="10" name="medicaidGroup2" value="<%=ESAPI.encoder().encodeForHTMLAttribute(medicaidGroup2)%>"></TD>
								</tr>
								<tr>
									<td valign="bottom">Medicaid Individual Number:</td>
									<TD><input type="text" size="10" maxlength="10" name="medicaidIndividual2" value="<%=ESAPI.encoder().encodeForHTMLAttribute(medicaidIndividual2)%>"></TD>
								</tr>
								<!-- PMF Section C Changes -- AD21239 -->
								<tr><td colspan="2">&nbsp;</td></tr>
								<tr><td colspan="2"><b>Please enter Kentucky Medicaid Program Information:</b></td></tr>
								<tr>
									<td valign="bottom">Are you currently participating in the Kentucky Medicaid Program? </td>
									<TD><input type="radio" name=kyMedicaidPart2 value="<%=Yes%>"   <%if(kyMedicaidPart2.equals(Yes)){%>CHECKED <%}%>>Yes
                                        <input type="radio" name=kyMedicaidPart2 value="<%=No%>" <%if(kyMedicaidPart2.equals(No)) {%>CHECKED <%}%>>No</TD>
								</tr>
								<%-- <%if(kentuckyMediProgSecF.equals(No)){%> --%>
								<tr>
									<td valign="bottom">Kentucky Medicaid ID:</td>
									<TD><input type="text" size="10" maxlength="10" name="kyMedicaidId2" value="<%=ESAPI.encoder().encodeForHTMLAttribute(kyMedicaidId2)%>"></TD>
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
								<!-- PMF Section C Changes -- AD21239 -->
							</table></td></tr></table>
</body>
</html>