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
<!--BEGIN FORM SECTION B -->
		<table BORDER="1" CELLSPACING=0 CELLPADDING=7 WIDTH="100%">
					 <tr>
						<td valign="top" align="center" colspan="2" BGCOLOR="#000080"><font size="2" COLOR="#ffffff"><b><%=Constants.sectionReasonSubmitting%> -- Reason for Submitting</b></font></td>
					</tr>
					<%	Iterator theDataB = errorMessagesVectorB.iterator();
							if (!errorMessagesVectorB.isEmpty())
							{%>
								<tr>
									<td valign="top" align="center" colspan="2" BGCOLOR="#FF0000"><font size="2" COLOR="#ffffff"><b>Error Messages</b></font></td>
								</tr>
								<tr>
									<td colspan="2" BGCOLOR="#ffffff"><font size="2" COLOR="#000080"><b>The following errors were detected.  Please correct the form entries and resubmit form.</b></font></td>
								</tr>
							<%	while(theDataB.hasNext())
								{
									String value = (String)theDataB.next(); %>
									<tr>
										<TD colspan="2" style="FONT-SIZE: large;"><font size="2" COLOR="#FF0000"><b><%=value%></b></font></TD>
									</tr>
							<%	}  %>
						<%	} %>
					<tr>
						<td valign="top" BGCOLOR="#ffffff" width="5%"><font size="2" COLOR="#000080"><b>1.&nbsp;&nbsp;&nbsp;</b></font></td>
						<td BGCOLOR="#ffffff" width="95%"><font size="2" COLOR="#000080"><b>Select all applicable reasons for submitting this form.</b></font></td>
					</tr>

					<tr>
						<td valign="top" BGCOLOR="#ffffff" width="5%"><font size="2" COLOR="#000080"/><b>2.&nbsp;&nbsp;&nbsp;</b></td>
						<td BGCOLOR="#ffffff" width="95%"><font size="2" COLOR="#000080"><b>Specify the effective date of all changes.</b></font></td>
					</tr>

					<tr>
						<td colspan="2">
							<table width="100%" border="0" cellspacing="0" cellpadding="0" style="FONT-SIZE: small;">
								<tr>
									<TD valign="bottom">*Effective Date of Add, Change or Delete:</TD>
									<TD><input type="text" size="8" maxlength="8" name="effectiveDate" value="<%=ESAPI.encoder().encodeForHTMLAttribute(effectiveDate)%>">(mmddyyyy)</TD>
								</tr>

								<tr><td colspan="2">&nbsp;</td></tr>

								<tr>
									<td colspan="2">*Reason for Submitting Form (Select all applicable):</td>
								</tr>

								<tr>
									<td colspan="2">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
									        <tr>
                           						<%	if (addProvider.equalsIgnoreCase("Y"))
												    { %>
													      <td><input type="checkbox" name=addProvider value="<%=addProvider%>" checked>Adding Provider</td>
                            					  <%}
												    else
												    {%>
													      <td><input type="checkbox" name=addProvider value="<%=addProvider%>">Adding Provider</td>
                            					  <%} %>

											     <% if (chgSpecialty.equalsIgnoreCase("Y"))
											        { %>
													      <td><input type="checkbox" name=chgSpecialty value="<%=chgSpecialty%>" checked>Specialty Change</td>
                            				      <%}
												    else
												    {%>
													      <td><input type="checkbox" name=chgSpecialty value="<%=chgSpecialty%>">Specialty Change</td>
                            					  <%}%>

											     <% if (chgPracName.equalsIgnoreCase("Y"))
												    { %>
													       <td><input type="checkbox" name=chgPracName value="<%=chgPracName%>" checked>Practice Name Change</td>
                             					  <%}
												    else
												     {%>
													      <td><input type="checkbox" name=chgPracName value="<%=chgPracName%>">Practice Name Change</td>
                                 				   <%} %>
 												<% if (chgBillName.equalsIgnoreCase("Y"))
												     { %>
													       <td valign="bottom"><input type="checkbox" name=chgBillName value="<%=chgBillName%>" checked>Name for Payment Change</td>
                             					   <%}
												     else
												     {%>
                               								<td valign="bottom"><input type="checkbox" name=chgBillName value="<%=chgBillName%>">Name for Payment Change</td>
                            					  <% } %>
                        					  </tr>
											  <tr>
											  	  <% if (delProvider.equalsIgnoreCase("Y"))
												     { %>
													   		<td><input type="checkbox" name=delProvider value="<%=delProvider%>" checked> Deleting Provider <i>(Supply reason below)</td>
                        						  <% }
												     else
												     {%>
													  		<td><input type="checkbox" name=delProvider value="<%=delProvider%>">Deleting Provider <i>(Supply reason below)</i></td>
                        						  <% } %>

											      <% if (chgProvName.equalsIgnoreCase("Y"))
                           						     { %>
													  		<td><input type="checkbox" name=chgProvName value="<%=chgProvName%>" checked>Provider Name Change</td>
                        						  <% }
												     else
												     {%>
													  		<td><input type="checkbox" name=chgProvName value="<%=chgProvName%>">Provider Name Change</td>
                        						  <% } %>

											  	  <% if (chgPracAddress.equalsIgnoreCase("Y"))
												     { %>
													   		<td><input type="checkbox" name=chgPracAddress value="<%=chgPracAddress%>" checked>Practice Address Change</td>
                        						  <% }
												     else
												     {%>
													  		<td><input type="checkbox" name=chgPracAddress value="<%=chgPracAddress%>">Practice Address Change</td>
                        						  <% } %>

											  	  <% String chgBillingAddress = ESAPI.encoder().encodeForHTMLAttribute(chgBillAddress);
											  	  		if (chgBillAddress.equalsIgnoreCase("Y"))
												     { %>
													   		<td><input type="checkbox" name=chgBillAddress value="<%=chgBillingAddress%>" checked>Address for Payment Change</td>
                        						  <% }
												     else
												     {%>
                            								<td><input type="checkbox" name=chgBillAddress value="<%=chgBillingAddress%>">Address for Payment Change</td>
                        						  <% } %>
											</tr>
											<tr>
											      <% if (addLocation.equalsIgnoreCase("Y"))
												     { %>
													  		<td><input type="checkbox" name=addLocation value="<%=addLocation%>" checked>Adding Location</td>
												  <% }
												     else
												     {%>
                            								<td><input type="checkbox" name=addLocation value="<%=addLocation%>">Adding Location</td>
												 <%  } %>

												<%	 if (delLocation.equalsIgnoreCase("Y"))
												     { %>
													  		<td><input type="checkbox" name=delLocation value="<%=delLocation%>" checked>Deleting Location</td>
												 <%	 }
												     else
												     {%>
													  		<td><input type="checkbox" name=delLocation value="<%=delLocation%>">Deleting Location</td>
												<%	 } %>

												<%	 String chgPracPhoneNew = ESAPI.encoder().encodeForHTMLAttribute(chgPracPhone);
														if (chgPracPhone.equalsIgnoreCase("Y"))
												     { %>
													  		<td><input type="checkbox" name=chgPracPhone value="<%=chgPracPhoneNew%>" checked>Practice Phone # Change</td>
												<%	 }
												     else
												     {%>
													  		<td><input type="checkbox" name=chgPracPhone value="<%=chgPracPhoneNew%>">Practice Phone # Change</td>
												<%	 } %>

												<%	 String chgBillPhoneNew = ESAPI.encoder().encodeForHTMLAttribute(chgBillPhone);
														if (chgBillPhone.equalsIgnoreCase("Y"))
												     { %>
													  		<td><input type="checkbox" name=chgBillPhone value="<%=chgBillPhoneNew%>" checked>Phone # for Payment Change</td>
												<%	 }
												     else
												     {%>
													  		<td><input type="checkbox" name=chgBillPhone value="<%=chgBillPhoneNew%>">Phone # for Payment Change</td>
												<%	 } %>
											</tr>
											<tr>
												<%	 if (addProvToLocation.equalsIgnoreCase("Y"))
												     { %>
													  		<td><input type="checkbox" name=addProvToLocation value="<%=addProvToLocation%>" checked>Adding Provider To Location</td>
												<%	 }
												     else
												     {%>
													  		<td><input type="checkbox" name=addProvToLocation value="<%=addProvToLocation%>">Adding Provider To Location</td>
												<%	 } %>
												<%	 if (chgOfficeHours.equalsIgnoreCase("Y"))
												     { %>
													  		<td><input type="checkbox" name=chgOfficeHours value="<%=chgOfficeHours%>" checked>Changing Office Hours</td>
												<%	 }
												     else
												     {%>
													  		<td><input type="checkbox" name=chgOfficeHours value="<%=chgOfficeHours%>">Changing Office Hours</td>
												<%	 } %>

												<%	 if (delProvFromLocation.equalsIgnoreCase("Y"))
												     { %>
													  		<td><input type="checkbox" name=delProvFromLocation value="<%=delProvFromLocation%>" checked>Deleting Provider from Location</td>
												<%	 }
												     else
												     {%>
													  		<td><input type="checkbox" name=delProvFromLocation value="<%=delProvFromLocation%>">Deleting Provider from Location</td>
												<%	 } %>

												<%	 if (chgTaxID.equalsIgnoreCase("Y"))
												     { %>
													  		<td><input type="checkbox" name=chgTaxID value="<%=chgTaxID%>" checked>Tax ID Change (Supply Old Tax ID below.)</td>
												<%   }
												     else
												     {%>
													  		<td><input type="checkbox" name=chgTaxID value="<%=chgTaxID%>">Tax ID Change (Supply Old Tax ID below.)</td>
											 	<%	 } %>
												  			<td>&nbsp;</td>
											</tr>
											<tr>
												<%	 if (addNPI.equalsIgnoreCase("Y"))
												     { %>
													  		<td><input type="checkbox" name=addNPI value="<%=addNPI%>" checked>Add NPI</td>
												<%	 }
												     else
												     {%>
													  		<td><input type="checkbox" name=addNPI value="<%=addNPI%>">Add NPI</td>
												<%	 } %>

												<%	 if (chgNPI.equalsIgnoreCase("Y"))
												     { %>
													  		<td><input type="checkbox" name=chgNPI value="<%=chgNPI%>" checked>Change NPI</td>
												<%	 }
												     else
												     {%>
													  		<td><input type="checkbox" name=chgNPI value="<%=chgNPI%>">Change NPI</td>
												<%	 } %>												
												 <td><input type="checkbox" name=addAreasOfExpertise value="" <%if(providerData.getEditAreasOfExpertise().equals("Y")){%>CHECKED <%}%>>Add/Update Providers' Self-Reported Areas of Expertise</td>
												 <td><input type="checkbox" name=addPatientInfo value="" <%if(providerData.getEditPatientInfo().equals("Y")){%>CHECKED <%}%>>Add/Update Patient Information</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td colspan="2">&nbsp;</td>
											</tr>

											<tr>
												<TD valign="top">Reason for Deleting Provider:</TD>
												<TD><TextArea NAME="delReason" Rows=4 Cols=50  onKeyDown="limitTextArea(this.form.delReason,200);" onKeyUp="limitTextArea(this.form.delReason,200);"><%=ESAPI.encoder().encodeForHTMLAttribute(delReason)%></TextArea></TD>
											</tr>

											<tr>
												<TD valign="bottom">Old Tax ID:</TD>
												<TD><input type="text" size="9" maxlength="9" name="oldTaxID" value="<%=ESAPI.encoder().encodeForHTMLAttribute(oldTaxID)%>">(999999999)</TD>
											</tr>
										</table>
									</td>
								</tr>
              		</table> 
             	 </td>
             	 </tr>
              </table>            
</body>
</html>