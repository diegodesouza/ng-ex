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
<!--BEGIN FORM SECTION L-->
			<table BORDER="1" CELLSPACING=0 CELLPADDING=7 WIDTH="100%">
					<tr>
						<td valign="top" align="center" colspan="2" BGCOLOR="#000080"><font size="2" COLOR="#ffffff"><b><%=Constants.sectionComments%>- Comments</b></font></td>
					</tr>

					<%	Iterator theDataL = errorMessagesVectorL.iterator();
							if (!errorMessagesVectorL.isEmpty())
							{%>
								<tr>
									<td valign="top" align="center" colspan="2" BGCOLOR="#FF0000"><font size="2" COLOR="#ffffff"><b>Error Messages</b></font></td>
								</tr>
								<tr>
									<td colspan="2" BGCOLOR="#ffffff"><font size="2" COLOR="#000080"><b>The following errors were detected.  Please correct the form entries and resubmit form.</b></font></td>
								</tr>
							<%	while(theDataL.hasNext())
								{
									String value = (String)theDataL.next(); %>
									<tr>
										<TD colspan="2" style="FONT-SIZE: large;"><font size="2" COLOR="#FF0000"><b><%=value%></b></font></TD>
									</tr>
							<%	}  %>
						<%	} %>
				
					<tr>
						<td valign="top" BGCOLOR="#ffffff" width="5%"><font size="2" COLOR="#000080"/><b>1.&nbsp;&nbsp;&nbsp;</b></font></td>
						<td BGCOLOR="#ffffff" width="95%"><b><font size="2" COLOR="#000080"/>In the space provided below,
                                                  <font size="2" COLOR="#0000F0"/> <i>please provide a contact name, phone number and email address</i>.</font>
                                                  <font size="2" COLOR="#000080"/><br>&nbsp;&nbsp;&nbsp;&nbsp;You may also provide any additional comments, notes or specific instructions.</font></b></td>
					</tr>
					<tr>
						<td valign="top" BGCOLOR="#ffffff" width="5%"><font size="2" COLOR="#000080"/><b>2.&nbsp;&nbsp;&nbsp;</b></font></td>
						<td BGCOLOR="#ffffff" width="95%"><b><font size="2" COLOR="#000080"/>The below checkbox must be checked before the form can be submitted.
												  By checking this checkbox you are confirming the data entered on this form is correct.</font>
                                                 </b></td>
					</tr>
					<!-- 2013 SSCR 13503 changes -->
                 	<tr>
					<% if (confProvAgreement.equalsIgnoreCase("Y"))
					{ %>
					<td colspan ="2"><input type="checkbox" name="confProvAgreement" value="<%=confProvAgreement%>" checked>
					For participating providers, this is a reminder that the Anthem Professional Provider Agreement requires you to provide at least 30 days' prior notice when adding a new practitioner to your group, making changes to your demographic information, or changing your Tax Identification Number (TIN).  If your TIN is new and a new Provider Agreement is required, the process may take longer.  If the new provider requires credentialing an addition may take longer than 30 days.
           			</td>
         			<%}
		 			else
	      			{%>
					<td colspan ="2"><input type="checkbox" name="confProvAgreement" value="<%=Yes%>">
					For participating providers, this is a reminder that the Anthem Professional Provider Agreement 
					requires you to provide at least 30 days' prior notice when adding a new practitioner to
					your group, making changes to your demographic information, or changing your 
					Tax Identification Number (TIN).  If your TIN is new and a new Provider Agreement is required, 
					the process may take longer.  If the new provider requires credentialing an addition may 
					take longer than 30 days.
          			</td>
          			<%} %>
					</tr>	
					<tr>
					<td colspan ="2">
					<input type="radio" name=confW2 value="<%=Yes%>" <%if(confW2.equals(Yes)){%>CHECKED <%}%>>Yes
     				<input type="radio" name=confW2 value="<%=No%>" <%if(confW2.equals(No)){%>CHECKED <%}%>>No
     				&nbsp;&nbsp;
     				<b>Provider certifies that the information submitted is for W-2 employed practitioners only.</b>
					<br>
					<b>If no, please provide details in the comment section below.</b>
					<!-- PMF Section Changes -- AD21239 -->
					<br><TextArea name="w2Comments"  id="w2Comments" rows=5 cols=75 onKeyDown="limitTextArea(this.form.w2Comments,200);" 
					onKeyUp="limitTextArea(this.form.w2Comments,200);"><%=ESAPI.encoder().encodeForHTML(w2Comments)%></TextArea>
					</td>
					</tr>
					<!-- 2013 SSCR 13503 changes -->
                 	<!-- 
                 	<tr>
					<% if (confProvAgreement.equalsIgnoreCase("Y"))
						   { %>
						     <td colspan ="2"><input type="checkbox" name="confProvAgreement" value="<%=confProvAgreement%>" checked>
					For participating providers, this is a reminder that the Anthem Professional Provider Agreement requires you to 
					provide at least 30 days' 
					prior notice when adding a new practitioner to your group, making changes to your demographic information, or 
					changing your Tax Identification Number (TIN).  If your TIN is new and a new Provider Agreement is required, 
					the process may take longer.  If the new provider requires credentialing an addition may take longer than 30 days.
					</td>
                    <%}
					else
					{%>
					<td colspan ="2"><input type="checkbox" name="confProvAgreement" value="<%=Yes%>">
					For participating providers, this is a reminder that the Anthem Professional Provider Agreement 
					requires you to provide at least 30 days' prior notice when adding a new practitioner to your 
					group, making changes to your demographic information, or changing your Tax Identification Number
					(TIN).  If your TIN is new and a new Provider Agreement is required, the process may take longer.  
					If the new provider requires credentialing an addition may take longer than 30 days.
					</td>
                 	<%} %>
                 	</tr>
                 	 -->	
                    <tr>
					<% if ( "Y".equalsIgnoreCase(confirmation))
					{ %>
					<td colspan ="2"><input type="checkbox" name="confirmation" value="<%=confirmation%>" checked>
					The undersigned certifies and attests that the forgoing is truthful, correct and complete in 
					all respects, and the undersigned further understands the intentional submission of false or
					misleading information or the withholding of relevant information is grounds for denial or 
					termination. </td>
                    <%}
					else
					{%>
					<td colspan ="2"><input type="checkbox" name="confirmation" value="<%=Yes%>">
					The undersigned  certifies and attests that the forgoing is truthful, correct and complete in 
					all respects, and the undersigned further understands the intentional submission of false or 
					misleading information or the withholding of relevant information is grounds for denial or 
					termination. </td>
                 	<%} %>
                 	</tr>	
					<tr>
						<td colspan ="2">
						<b>Please enter a contact name, phone number and email address:</b><br>
							<TextArea NAME="comments"  rows=10 cols=75 onKeyDown="limitTextArea(this.form.comments,750);" 
							onKeyUp="limitTextArea(this.form.comments,750);"><%=ESAPI.encoder().encodeForHTML(comments)%></TextArea>
						</td>
					</tr>
			</table>
</body>
</html>