<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page language="java" %>
<%@page import="java.sql.*" %>
<%@page import="javax.naming.*" %>
<%@page import="javax.sql.*" %>
<%@page import="java.io.*" %>
<%@page import="java.util.*"%>
<%@ page import="java.util.Date, java.text.*"  %>
<%@ page import="com.anthem.mwpmf.PMFUtils" %>
<%@ page import="org.owasp.esapi.ESAPI" %>
<%!
	String getDate()
	{
		java.text.SimpleDateFormat dateFormatter = new SimpleDateFormat("MMMM dd, yyyy");
		java.util.Date date = new java.util.Date();
		return dateFormatter.format(date);
    }
 %>
<%  String status = (String)request.getAttribute("Status");
	if ((status == null) || status.equals(""))
		status = "F";
	String providerName = "";	
	String middleName = "";	
	String lastName = "";	
	String title = "";	
	String groupPracticeName = "";	
	int PGIID = 0;	
%>
<html>
<head>
<title>Anthem Blue Cross and Blue Shield Provider Maintenance Form</title>
<link REL="stylesheet" HREF="anthemcssv1.css">
<meta http-equiv="Expires" content="Mon, 18 Jun 2000 00:01:00 GMT">
<meta http=equiv="Pragma" content="no-cache">
</head>
<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0" bgcolor="#FFFFFF">
<!doctype html public "-//w3c//dtd html 4.0 transitional//en">
<!--BEGIN MAIN TABLE-->
<TABLE WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
	<TR VALIGN="TOP" ALIGN="LEFT">
	<br>
	<br><br><br>
<%	if (status.equals("F"))
	{ %>
		<center><font size="2"><b>&nbsp;&nbsp;Your provider maintenance form was not successfully submitted.<br>Press your browser's back button to return to Provider Maintenance Form page and resubmit.</b></font></center>
<%	}
	else
	{  
	providerName = (String)request.getAttribute("providerName");
	
	middleName = (String)request.getAttribute("middleName");
	
	lastName = (String)request.getAttribute("lastName");
	
	title = (String)request.getAttribute("title");
	
	groupPracticeName = (String)request.getAttribute("groupPracticeName");
	
	PGIID = (Integer)request.getAttribute("PGIID");
	
	status = (String)request.getAttribute("Status");
	
%>
		<center><font size="2"><b>&nbsp;&nbsp;Your provider maintenance form has been successfully submitted.</b></font></center>
		</tr>
		<tr>
	   	
			<FORM  NAME="Confirm" METHOD="POST" ACTION="PMFControllerServlet">
			<table align="center">
			<tr><td>&nbsp;</td></tr>
			<tr><td>&nbsp;</td></tr>
			<%if(providerName.equalsIgnoreCase(" ")||providerName.trim().equalsIgnoreCase("")){ %>
			<tr><td>Thank you for your submission for <%=ESAPI.encoder().encodeForHTMLAttribute(groupPracticeName)%> today </td></tr>
			<tr><td><%=getDate()%>. Your submission number is <%= PGIID %> </td></tr>
			<%}else{ %>
			<tr><td>Thank you for your submission for <%=ESAPI.encoder().encodeForHTMLAttribute(providerName) %>  <%=ESAPI.encoder().encodeForHTMLAttribute(middleName) %> <%=ESAPI.encoder().encodeForHTMLAttribute(lastName) %>,  <%=ESAPI.encoder().encodeForHTMLAttribute(title) %></td></tr>
			<tr><td><%=ESAPI.encoder().encodeForHTMLAttribute(groupPracticeName)%> today <%= getDate() %>.</td></tr>
			<tr><td>Your confirmation number is <%= PGIID %> </td></tr>
			<%} %>
			<tr><td>&nbsp;</td></tr>
			<tr><td>&nbsp;</td></tr>
			</table>
			<center><input type="submit" name="submit" value="Click to return to Provider Maintenance Form entry page"></center>
			</FORM>
		
<%	} %>
	</tr>
</table>
</body>
</html>
