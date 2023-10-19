<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page language="java" %>
<%@ page import="com.anthem.mwpmf.PMFUtils" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!--BEGIN FORM SECTION H-->
		<table BORDER="1" CELLSPACING=0 CELLPADDING=7 WIDTH="100%">
					<tr>
						<td valign="top" align="center" colspan="2" BGCOLOR="#000080"><font size="2" COLOR="#ffffff"><b><%=Constants.sectionPhysicians%> -- Covering Physicians&nbsp;&nbsp;<i>  (Note:  For PCPs and OB/GYNs in HMO Networks only)</b></font></td>
					</tr>
					
					<%	Iterator theDataH = errorMessagesVectorH.iterator();
							if (!errorMessagesVectorH.isEmpty())
							{%>
								<tr>
									<td valign="top" align="center" colspan="2" BGCOLOR="#FF0000"><font size="2" COLOR="#ffffff"><b>Error Messages</b></font></td>
								</tr>
								<tr>
									<td colspan="2" BGCOLOR="#ffffff"><font size="2" COLOR="#000080"><b>The following errors were detected.  Please correct the form entries and resubmit form.</b></font></td>
								</tr>
							<%	while(theDataH.hasNext())
								{
									String value = (String)theDataH.next(); %>
									<tr>
										<TD colspan="2" style="FONT-SIZE: large;"><font size="2" COLOR="#FF0000"><b><%=value%></b></font></TD>
									</tr>
							<%	}  %>
						<%	} %>
					
					<tr>
						<td valign="top" BGCOLOR="#ffffff" width="5%"><font size="2" COLOR="#000080"><b>1.&nbsp;&nbsp;&nbsp;</b></td>
						<td BGCOLOR="#ffffff" width="95%"><font size="2" COLOR="#000080"><b>List all physicians that cover for you.</b></font></td>
					</tr>

					<tr>
						<td valign="top" BGCOLOR="#ffffff" width="5%"><font size="2" COLOR="#000080"><b>2.&nbsp;&nbsp;&nbsp;</b></td>
						<td BGCOLOR="#ffffff" width="95%"><font size="2" COLOR="#000080"><b>Submit as many forms as needed to include additional covering physicians that do not fit in this form.</b></font></td>
					</tr>
					<tr>
						<td valign="top" BGCOLOR="#ffffff" width="5%"><font size="2" COLOR="#000080"><b>3.&nbsp;&nbsp;&nbsp;</b></font></td>
						<td BGCOLOR="#ffffff" width="95%"><font size="2" COLOR="#000080"><b>If you need help finding your 9 digit Zip Code, go to <a href= "https://tools.usps.com/go/ZipLookupAction!input.action" target="_blank">USPS.com&reg; - ZIP Code&trade; Lookup</a> for additional information.</b></font></td>						
					</tr>
					

					<tr>
						<td colspan="2">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr><td colspan="2"><b>First Covering Physician</b></td></tr>
								<tr><td colspan="2">&nbsp;</td></tr>

								<tr>
									<TD valign="bottom">Group Entity Name</TD>
									<TD><input type="text" size="55" maxlength="55" name="grpEntityName1" value="<%=ESAPI.encoder().encodeForHTMLAttribute(grpEntityName1)%>"></TD>
								</tr>
								
								<tr>
									<TD valign="bottom">Address:</TD>
									<TD><input type="text" size="35" maxlength="35" name="cpHospitalAddress" value="<%=ESAPI.encoder().encodeForHTMLAttribute(cpHospitalAddress[0])%>"></TD>
								</tr>

								<tr>
									<TD valign="bottom">City:</TD>
									<TD><input type="text" size="19" maxlength="19" name="cpHospitalCity" value="<%=ESAPI.encoder().encodeForHTMLAttribute(cpHospitalCity[0])%>"></TD>
								</tr>

								<tr>
									<TD valign="bottom">State:</TD>
									<TD><input type="text" size="2" maxlength="2" name="cpHospitalState" value="<%=ESAPI.encoder().encodeForHTMLAttribute(cpHospitalState[0])%>"></TD>
								</tr>

								<tr>
									<TD valign="bottom">Zip:</TD>
									<TD><input type="text" size="11" maxlength="10" name="cpHospitalZip" value="<%=ESAPI.encoder().encodeForHTMLAttribute(cpHospitalZip[0])%>">(99999-9999)</TD>
								</tr>
																							
								<tr>
									<TD valign="bottom">Specialty</TD>
									<TD><input type="text" size="35" maxlength="35" name="specialty1" value="<%=ESAPI.encoder().encodeForHTMLAttribute(specialty1)%>"></TD>
								</tr>

								<tr>
									<TD valign="bottom">Nine(9) Digit Tax ID</TD>
									<TD><input type="text" size="9" maxlength="9" name="taxID1" value="<%=ESAPI.encoder().encodeForHTMLAttribute(taxID1)%>">(999999999)</TD>
								</tr>

								<tr>
									<TD valign="bottom">Effective Date</TD>
									<TD><input type="text" size="8" maxlength="8" name="effectiveDate1" value="<%=ESAPI.encoder().encodeForHTMLAttribute(effectiveDate1)%>">(mmddyyyy)</TD>
								</tr>

							</table>
						</td>
							</tr>
					<tr>
						<td colspan="2">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr><td colspan="2"><b>Second Covering Physician</b></td></tr>
								<tr><td colspan="2">&nbsp;</td></tr>

								<tr>
									<TD valign="bottom">Group Entity Name</TD>
									<TD><input type="text" size="55" maxlength="55" name="grpEntityName2" value="<%=ESAPI.encoder().encodeForHTMLAttribute(grpEntityName2)%>"></TD>
								</tr>
								<tr>
									<TD valign="bottom">Address:</TD>
									<TD><input type="text" size="35" maxlength="35" name="cpHospitalAddress" value="<%=ESAPI.encoder().encodeForHTMLAttribute(cpHospitalAddress[1])%>"></TD>
								</tr>

								<tr>
									<TD valign="bottom">City:</TD>
									<TD><input type="text" size="19" maxlength="19" name="cpHospitalCity" value="<%=ESAPI.encoder().encodeForHTMLAttribute(cpHospitalCity[1])%>"></TD>
								</tr>

								<tr>
									<TD valign="bottom">State:</TD>
									<TD><input type="text" size="2" maxlength="2" name="cpHospitalState" value="<%=ESAPI.encoder().encodeForHTMLAttribute(cpHospitalState[1])%>"></TD>
								</tr>

								<tr>
									<TD valign="bottom">Zip:</TD>
									<TD><input type="text" size="11" maxlength="10" name="cpHospitalZip" value="<%=ESAPI.encoder().encodeForHTMLAttribute(cpHospitalZip[1])%>">(99999-9999)</TD>
								</tr>

								<tr>
									<TD valign="bottom">Specialty</TD>
									<TD><input type="text" size="35" maxlength="35" name="specialty2" value="<%=ESAPI.encoder().encodeForHTMLAttribute(specialty2)%>"></TD>
								</tr>

								<tr>
									<TD valign="bottom">Nine(9) Digit Tax ID</TD>
									<TD><input type="text" size="9" maxlength="9" name="taxID2" value="<%=ESAPI.encoder().encodeForHTMLAttribute(taxID2)%>">(999999999)</TD>
								</tr>

								<tr>
									<TD valign="bottom">Effective Date</TD>
									<TD><input type="text" size="8" maxlength="8" name="effectiveDate2" value="<%=ESAPI.encoder().encodeForHTMLAttribute(effectiveDate2)%>">(mmddyyyy)</TD>
								</tr>
							</table>
							</td>
							</tr>
							<tr>
								<td colspan="2">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr><td colspan="2"><b>Third Covering Physician</b></td></tr>
										<tr><td colspan="2">&nbsp;</td></tr>

								<tr>
									<TD valign="bottom">Group Entity Name</TD>
									<TD><input type="text" size="55" maxlength="55" name="grpEntityName3" value="<%=ESAPI.encoder().encodeForHTMLAttribute(grpEntityName3)%>"></TD>
								</tr>
								<tr>
									<TD valign="bottom">Address:</TD>
									<TD><input type="text" size="35" maxlength="35" name="cpHospitalAddress" value="<%=ESAPI.encoder().encodeForHTMLAttribute(cpHospitalAddress[2])%>"></TD>
								</tr>

								<tr>
									<TD valign="bottom">City:</TD>
									<TD><input type="text" size="19" maxlength="19" name="cpHospitalCity" value="<%=ESAPI.encoder().encodeForHTMLAttribute(cpHospitalCity[2])%>"></TD>
								</tr>

								<tr>
									<TD valign="bottom">State:</TD>
									<TD><input type="text" size="2" maxlength="2" name="cpHospitalState" value="<%=ESAPI.encoder().encodeForHTMLAttribute(cpHospitalState[2])%>"></TD>
								</tr>

								<tr>
									<TD valign="bottom">Zip:</TD>
									<TD><input type="text" size="11" maxlength="10" name="cpHospitalZip" value="<%=ESAPI.encoder().encodeForHTMLAttribute(cpHospitalZip[2])%>">(99999-9999)</TD>
								</tr>

								<tr>
									<TD valign="bottom">Specialty</TD>
									<TD><input type="text" size="35" maxlength="35" name="specialty3" value="<%=ESAPI.encoder().encodeForHTMLAttribute(specialty3)%>"></TD>
								</tr>

								<tr>
									<TD valign="bottom">Nine(9) Digit Tax ID</TD>
									<TD><input type="text" size="9" maxlength="9" name="taxID3" value="<%=ESAPI.encoder().encodeForHTMLAttribute(taxID3)%>">(999999999)</TD>
								</tr>

								<tr>
									<TD valign="bottom">Effective Date</TD>
									<TD><input type="text" size="8" maxlength="8" name="effectiveDate3" value="<%=ESAPI.encoder().encodeForHTMLAttribute(effectiveDate3)%>">(mmddyyyy)</TD>
								</tr>

							</table>
							</td>
							</tr>
							<tr>
								<td colspan="2">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr><td colspan="2"><b>Fourth Covering Physician</b></td></tr>
										<tr><td colspan="2">&nbsp;</td></tr>

								<tr>
									<TD valign="bottom">Group Entity Name</TD>
									<TD><input type="text" size="55" maxlength="55" name="grpEntityName4" value="<%=ESAPI.encoder().encodeForHTMLAttribute(grpEntityName4)%>"></TD>
								</tr>
								
								<tr>
									<TD valign="bottom">Address:</TD>
									<TD><input type="text" size="35" maxlength="35" name="cpHospitalAddress" value="<%=ESAPI.encoder().encodeForHTMLAttribute(cpHospitalAddress[3])%>"></TD>
								</tr>

								<tr>
									<TD valign="bottom">City:</TD>
									<TD><input type="text" size="19" maxlength="19" name="cpHospitalCity" value="<%=ESAPI.encoder().encodeForHTMLAttribute(cpHospitalCity[3])%>"></TD>
								</tr>

								<tr>
									<TD valign="bottom">State:</TD>
									<TD><input type="text" size="2" maxlength="2" name="cpHospitalState" value="<%=ESAPI.encoder().encodeForHTMLAttribute(cpHospitalState[3])%>"></TD>
								</tr>

								<tr>
									<TD valign="bottom">Zip:</TD>
									<TD><input type="text" size="11" maxlength="10" name="cpHospitalZip" value="<%=ESAPI.encoder().encodeForHTMLAttribute(cpHospitalZip[3])%>">(99999-9999)</TD>
								</tr>

								<tr>
									<TD valign="bottom">Specialty</TD>
									<TD><input type="text" size="35" maxlength="35" name="specialty4" value="<%=ESAPI.encoder().encodeForHTMLAttribute(specialty4)%>"></TD>
								</tr>

								<tr>
									<TD valign="bottom">Nine(9) Digit Tax ID</TD>
									<TD><input type="text" size="9" maxlength="9" name="taxID4" value="<%=ESAPI.encoder().encodeForHTMLAttribute(taxID4)%>">(999999999)</TD>
								</tr>

								<tr>
									<TD valign="bottom">Effective Date</TD>
									<TD><input type="text" size="8" maxlength="8" name="effectiveDate4" value="<%=ESAPI.encoder().encodeForHTMLAttribute(effectiveDate4)%>">(mmddyyyy)</TD>
								</tr>

							</table>
							</td>
							</tr>
							<tr>
								<td colspan="2">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr><td colspan="2"><b>Fifth Covering Physician</b></td></tr>
										<tr><td colspan="2">&nbsp;</td></tr>

								<tr>
									<TD valign="bottom">Group Entity Name</TD>
									<TD><input type="text" size="55" maxlength="55" name="grpEntityName5" value="<%=ESAPI.encoder().encodeForHTMLAttribute(grpEntityName5)%>"></TD>
								</tr>
								<tr>
									<TD valign="bottom">Address:</TD>
									<TD><input type="text" size="35" maxlength="35" name="cpHospitalAddress" value="<%=ESAPI.encoder().encodeForHTMLAttribute(cpHospitalAddress[4])%>"></TD>
								</tr>

								<tr>
									<TD valign="bottom">City:</TD>
									<TD><input type="text" size="19" maxlength="19" name="cpHospitalCity" value="<%=ESAPI.encoder().encodeForHTMLAttribute(cpHospitalCity[4])%>"></TD>
								</tr>

								<tr>
									<TD valign="bottom">State:</TD>
									<TD><input type="text" size="2" maxlength="2" name="cpHospitalState" value="<%=ESAPI.encoder().encodeForHTMLAttribute(cpHospitalState[4])%>"></TD>
								</tr>

								<tr>
									<TD valign="bottom">Zip:</TD>
									<TD><input type="text" size="11" maxlength="10" name="cpHospitalZip" value="<%=ESAPI.encoder().encodeForHTMLAttribute(cpHospitalZip[4])%>">(99999-9999)</TD>
								</tr>

								<tr>
									<TD valign="bottom">Specialty</TD>
									<TD><input type="text" size="35" maxlength="35" name="specialty5" value="<%=ESAPI.encoder().encodeForHTMLAttribute(specialty5)%>"></TD>
								</tr>

								<tr>
									<TD valign="bottom">Nine(9) Digit Tax ID</TD>
									<TD><input type="text" size="9" maxlength="9" name="taxID5" value="<%=ESAPI.encoder().encodeForHTMLAttribute(taxID5)%>">(999999999)</TD>
								</tr>

								<tr>
									<TD valign="bottom">Effective Date</TD>
									<TD><input type="text" size="8" maxlength="8" name="effectiveDate5" value="<%=ESAPI.encoder().encodeForHTMLAttribute(effectiveDate5)%>">(mmddyyyy)</TD>
								</tr>

							</table>
							</td>
							</tr>
							</table>
</body>
</html>