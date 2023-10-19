<!doctype html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" %>
<%@ page session="true" import="com.anthem.mwpmf.NYVAProvMaintFormBean" %>
<%@ page import="com.anthem.mwpmf.NYVAProvMaintFormBean" %>
<%@ page import="java.util.*" %>
<%@ page import="org.owasp.esapi.ESAPI" %>

<!-- force recompile in test -->

<%!
	String getDate()
	{
		java.text.SimpleDateFormat dateFormatter = new java.text.SimpleDateFormat("MMMM dd, yyyy");
		java.util.Date date = new java.util.Date();
		return dateFormatter.format(date);
    }
 %>
<%  String status = (String)request.getAttribute("Status");
	if ((status == null) || status.equals(""))
		status = "F";
	NYVAProvMaintFormBean pdfForm = (NYVAProvMaintFormBean)request.getAttribute("pdfform");
	String firstName = "";
	String lastName = "";
	String groupPracticeName = "";
	
	if (pdfForm != null) {
		firstName = pdfForm.getProvFirstName();
		lastName = pdfForm.getProvLastName();
		groupPracticeName = pdfForm.getPracName();
	}
	
	System.out.println("before getting PGIID");
	String PGIID = (String)request.getAttribute("PGIID");
	System.out.println("after getting PGIID");
  	String brand = (String)session.getAttribute("brand");
%>

<html lang="en">
<head>
	<title>Provider Demographic Maintenance Form</title>
	<link rel="stylesheet" type="text/css" href="css/va/mp_pf_forms.css" />
	<link rel="stylesheet" type="text/css" href="css/va/ui-lightness/jquery_ui_1.8.custom.css">  
	<link rel="stylesheet" type="text/css" href="css/va/autocomplete.css">

	<script type="text/javascript" src="js/va/html5.js"></script>
	<script type="text/javascript" src="js/va/jqueryLib.js"></script>
	<script type="text/javascript" src="js/va/jquery.validate.min.js"></script>
	<script type="text/javascript" src="js/va/jquery_ui_1.8.custom.min.js"></script>
	<script type="text/javascript" src="js/va/pf_appScript.js"></script>	

</head>
<body class="pf_l_body">
	<div id="pf_l_wrapper"> <!-- pf_l_wrapper conatins the whole application -->
		<h2 id="pf_l_mainHeader" name="#Anthem">
			<a href="#Anthem">Welcome to Anthem Blue Cross and Blue Shield</a>
		</h2>
			<div id="pf_l_content"> <!-- pf_l_content conatins the whole content/forms -->
			<h1>Provider Maintenance Form</h1>
			
		<%	if (status.equals("F"))
			{ %>
				<p class="pf_bold"> Your provider maintenance form was not successfully submitted.</p>
				<p>Press your browser's back button to return to Provider Maintenance Form page and resubmit.</p>
		<%	}
			else
			{ %>
          <p class="pf_bold"> Your Provider Maintenance Form has been successfully submitted.
            <%if((firstName!=null) && (firstName.equalsIgnoreCase(" ") || firstName.trim().equalsIgnoreCase(""))){ %>
			Thank you for your submission for <%=ESAPI.encoder().encodeForHTMLAttribute(groupPracticeName)%> on <%=getDate()%>.
			<%}else{ %>
			Thank you for your submission for <%=ESAPI.encoder().encodeForHTMLAttribute(firstName) %> <%=ESAPI.encoder().encodeForHTMLAttribute(lastName) %> on <%= getDate() %>.
			<%} %>
			</p>
			<p></p>
			<p></p>
            <p>Your submission number is <%= PGIID %>.</p>
            
           <% } %>
            <p></p>
            <p></p>
            <p></p>
            <p></p>
	<!--  		<input class="pf_submit" type="button" WIDTH="100" HEIGHT="25" ALT="Close" value="Close" onclick="return self.close();"/>  -->
					
		</div> <!-- END of Content -->
		<div id="pf_footer">
			Anthem Health Plans of Virginia, Inc. trades as Anthem Blue Cross and Blue Shield in Virginia, and 
			its service area is all of Virginia except for the City of Fairfax, the Town of Vienna, and the area 
			east of State Route 123. Anthem Blue Cross and Blue Shield and its affiliate HealthKeepers, Inc. are 
			independent licensees of the Blue Cross Blue Shield Association. &reg;ANTHEM is a registered 
			trademark of Anthem Insurance Companies, Inc. The Blue Cross and Blue Shield names and symbols are 
			registered marks of the Blue Cross and Blue Shield Association.
		</div>

	</div><!-- END of Wrapper -->

</body>
</html>