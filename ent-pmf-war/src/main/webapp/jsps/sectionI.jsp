<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!--BEGIN FORM SECTION I-->
				<table BORDER="1" CELLSPACING=0 CELLPADDING=7 WIDTH="100%">
					<tr>
						<td valign="top" align="center" colspan="2" BGCOLOR="#000080"><font size="2" COLOR="#ffffff"><b><%=Constants.sectionPatientInfo%> - Patient Information (Note:
						For Behavioral Health Providers Only)</b></font></td>
					</tr>
					
					<%	Iterator theDataI = errorMessagesVectorI.iterator();
							if (!errorMessagesVectorI.isEmpty())
							{%>
								<tr>
									<td valign="top" align="center" colspan="2" BGCOLOR="#FF0000"><font size="2" COLOR="#ffffff"><b>Error Messages</b></font></td>
								</tr>
								<tr>
									<td colspan="2" BGCOLOR="#ffffff"><font size="2" COLOR="#000080"><b>The following errors were detected.  Please correct the form entries and resubmit form.</b></font></td>
								</tr>
							<%	while(theDataI.hasNext())
								{
									String value = (String)theDataI.next(); %>
									<tr>
										<TD colspan="2" style="FONT-SIZE: large;"><font size="2" COLOR="#FF0000"><b><%=value%></b></font></TD>
									</tr>
							<%	}  %>
						<%	} %>
						
					<tr>
						<td colspan="2">
							<table width="100%">
							<%List pilist = providerData.getPatientInfo();
							  if(pilist == null){
								  pilist = new ArrayList();           
							  }
							  
							  %>
								<tr>
									<TD><input type="checkbox" name=children value=""   <%if(pilist.contains("children")){%>CHECKED <%}%>>Children</TD>
					                <TD><input type="checkbox" name=adolescents value=""   <%if(pilist.contains("adolescents")){%>CHECKED <%}%>>Adolescents (Ages 13-17)</TD>    											
					                <TD><input type="checkbox" name=adults value=""   <%if(pilist.contains("adults")){%>CHECKED <%}%>>Adults (Ages 18+)</TD>
								</tr>
								<tr>
									<TD><input type="checkbox" name=senior value=""   <%if(pilist.contains("senior")){%>CHECKED <%}%>>Senior Adults (Ages 60+)</TD>
					                <TD><input type="checkbox" name=deaf value=""   <%if(pilist.contains("deaf")){%>CHECKED <%}%>>Deaf/Hearing Impaired</TD>    											
					                <TD><input type="checkbox" name=disabled value=""   <%if(pilist.contains("disabled")){%>CHECKED <%}%>>Disabled Persons</TD>
								</tr>
								
								<tr>
									<td colspan="4">Do you require patients be evaluated by a non-Psychiatrist (psychologist, counselor or social worker) prior to scheduling an appointment for the in-office appointment?</td>
								</tr>
								<tr>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name=nonPsychEval value="<%=inPatient%>" <%if(nonPsychEval.equals("inpatient")){%>CHECKED <%}%>>Do you see patients on an inpatient basis</td>
								</tr>
								<tr>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name=nonPsychEval value="<%=outPatient%>" <%if(nonPsychEval.equals("outpatient")){%>CHECKED <%}%>>Do you see patients on an outpatient basis</td> 
								</tr>
								<tr>	
									<td>&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name=nonPsychEval value="<%=both%>" <%if(nonPsychEval.equals("both")){%>CHECKED <%}%>>Both</td> 
								</tr>
								<tr>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name=nonPsychEval value="<%=clearSelection%>" <%if(nonPsychEval.equals(clearSelection)){%>CHECKED <%}%>>Clear Selection</td>  
								</tr>
							</table>
						
						</td>
					</tr>
			</table>
</body>
</html>