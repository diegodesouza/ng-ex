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
<!--BEGIN FORM SECTION A-->
	<table BORDER="1" CELLSPACING=0 CELLPADDING=7 WIDTH="100%">
					<tr>
						<td valign="top" align="center" colspan="2" BGCOLOR="#000080"><font size="2" COLOR="#ffffff"><b><%=Constants.sectionGeneralInfo%> -- General Information</b></font></td>
					</tr>
					<%	Iterator theDataA = errorMessagesVectorA.iterator();
							if (!errorMessagesVectorA.isEmpty())
							{%>
								<tr>
									<td valign="top" align="center" colspan="2" BGCOLOR="#FF0000"><font size="2" COLOR="#ffffff"><b>Error Messages</b></font></td>
								</tr>
								<tr>
									<td colspan="2" BGCOLOR="#ffffff"><font size="2" COLOR="#000080"><b>The following errors were detected.  Please correct the form entries and resubmit form.</b></font></td>
								</tr>
							<%	while(theDataA.hasNext())
								{
									String value = (String)theDataA.next(); %>
									<tr>
										<TD colspan="2" style="FONT-SIZE: large;"><font size="2" COLOR="#FF0000"><b><%=value%></b></font></TD>
									</tr>
							<%	}  %>
						<%	} %>
					<tr>
						<td valign="top" BGCOLOR="#ffffff" width="5%"><font size="2" COLOR="#000080"><b>1.&nbsp;&nbsp;&nbsp;</b></font></td>
						<td BGCOLOR="#ffffff"width="95%"><font size="2" COLOR="#000080"><b>Complete required fields for tax identification number and the practice name.</b></font></td>
					</tr>
					<tr>
						<td valign="top" BGCOLOR="#ffffff" width="5%"><font size="2" COLOR="#000080"/><b>2.&nbsp;&nbsp;&nbsp;</b></td>
						<td BGCOLOR="#ffffff" width="95%"><font size="2" COLOR="#000080"><b>Specify solo or group practice. If group practice, indicate the number of physicians in the group.</b></font></td>
					</tr>
					<tr>
						<td valign="top" BGCOLOR="#ffffff" width="5%"><font size="2" COLOR="#000080"><b>3.&nbsp;&nbsp;&nbsp;</b></font></td>
						<td BGCOLOR="#ffffff" width="95%"><font size="2" COLOR="#000080"><b>Specify if you would like to receive Rapid Updates from Anthem via email/fax.</b></font></td>
					</tr>
					<tr>
						<td colspan="2">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<TD align="bottom">Practice Tax ID Number  (EIN/SSN):</TD>
									<TD><input type="text" size="9" maxlength="9" name="taxID" value="<%=ESAPI.encoder().encodeForHTMLAttribute(taxID)%>"> (999999999)</TD>
								</tr>	
								<tr>
									<td align="bottom">Group/Practice Name:</td>
									<td><input type="text" size="55" maxlength="55" name=pracName1 value="<%=ESAPI.encoder().encodeForHTMLAttribute(pracName1)%>"></td>
								</tr>							
								<tr>
									<TD align="bottom" colspan="2">If paper claim submission or exempt from NPI fill out Legacy ID <BR>
									 or Anthem PIN number.</TD>
								</tr>
								<tr>
									<TD align="bottom">&nbsp;&nbsp;&nbsp;&nbsp;IN, KY and OH Provider Id Number/PIN:</TD>
									<TD> <input type="text" size="12" maxlength="12" name="anthemPIN" value="<%=ESAPI.encoder().encodeForHTMLAttribute(anthemPIN)%>"> (999999999999)</TD>
								</tr>
								
								<tr>
									<TD align="bottom">&nbsp;&nbsp;&nbsp;&nbsp;Missouri Provider Id Number:</TD>
									<TD> <input type="text" size="6" maxlength="6" name="moId" value="<%=ESAPI.encoder().encodeForHTMLAttribute(moId)%>"> (999999)</TD>
								</tr>
								
								<tr>
									<TD align="bottom">&nbsp;&nbsp;&nbsp;&nbsp;Wisconsin Provider Id Number:</TD>
									<TD> <input type="text" size="12" maxlength="12" name="wiId" value="<%=ESAPI.encoder().encodeForHTMLAttribute(wiId)%>"> (999999999999)</TD>
								</tr>
								<!-- 2013 SSCR 13503 changes 
								<tr>
									<TD align="bottom">Group Medicaid Id Number:</TD>
									<TD> <input type="text" size="10" maxlength="10" name="grpmedicaidId" value="<%=ESAPI.encoder().encodeForHTMLAttribute(grpmedicaidId)%>"></TD>
								</tr>-->

								<tr>
									<TD align="bottom">Group National Provider Identification Number:</TD>
									<TD> <input type="text" size="10" maxlength="10" name="grpNpiNumber" value="<%=ESAPI.encoder().encodeForHTMLAttribute(grpNpiNumber)%>"> </TD>
								</tr>
								

								<tr>
									<td height="23"  align="bottom">Solo or Group Practice:</td>
									
									<% if (soloGroup.equals("G"))
									   {%>
											<td height="23"><input type="radio" value="<%=solo%>" name=soloGroup>Solo
											<input type="radio" value="<%=group%>" name=soloGroup checked>Group</td>
									<% }
									   else
									   {%>
											<td height="23"><input type="radio" value="<%=solo%>" name=soloGroup checked>Solo
											<input type="radio" value="<%=group%>" name=soloGroup>Group</td>
									<% } %>
								</tr>

								<tr>
									<td align="bottom">If Group Practice, # of physicians in practice:</td>
									<td><input type="text" size="4" maxlength="4" name=numberInGroup value="<%=ESAPI.encoder().encodeForHTMLAttribute(numberInGroup)%>"></td>
								</tr>
								<tr>
									<td align="bottom">Would you like to receive Rapid Updates from Anthem via email/fax?</td>
									<% if (rapidUpdate.equalsIgnoreCase("Y")) 
								       { %>
											<td><input type="checkbox" name=rapidUpdate value="<%=rapidUpdate%>" checked></td>
                  				   <%  }
									   else
									   {%>
											<td><input type="checkbox" name=rapidUpdate value="<%=rapidUpdate%>"></td>
                   					 <%} %>
								</tr>
							</table>
						</td>
					</tr>
				</table>
</body>
</html>