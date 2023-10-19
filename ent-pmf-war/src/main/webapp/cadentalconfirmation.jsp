<!doctype html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" %>
<%@ page session="true" import="com.anthem.mwpmf.NYVAProvMaintFormBean" %>
<%@ page import="com.anthem.util.Constants" %>
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
	<link rel="stylesheet" type="text/css" href="css/cadental/mp_pf_forms.css" />
	<link rel="stylesheet" type="text/css" href="css/cadental/ui-lightness/jquery_ui_1.8.custom.css">  
	<link rel="stylesheet" type="text/css" href="css/cadental/autocomplete.css">

	<script type="text/javascript" src="js/cadental/html5.js"></script>
	<script type="text/javascript" src="js/cadental/jqueryLib.js"></script>
	<script type="text/javascript" src="js/cadental/jquery.validate.min.js"></script>
	<script type="text/javascript" src="js/cadental/jquery_ui_1.8.custom.min.js"></script>
	<script type="text/javascript" src="js/cadental/pf_appScript.js"></script>	

</head>
<body class="pf_l_body">
	<div id="pf_l_wrapper"> <!-- pf_l_wrapper conatins the whole application -->
		<% if (brand.equalsIgnoreCase(Constants.CADENTAL_GOLDENWEST_BRAND)) { %>
			<h2 id="pf_l_mainHeaderGolden" name="#GoldenWest">
				<a href="#GoldenWest">Welcome to GOLDEN WEST</a>
			</h2>
		<% } else { %>
			<h2 id="pf_l_mainHeader" name="#Anthem">
				<a href="#Anthem">Welcome to Anthem BlueCross BuleShield</a>
			</h2>
		<% } %>		
		<div id="pf_l_content"> <!-- pf_l_content conatins the whole content/forms -->
			<h1>Provider Maintenance Form</h1>
			
		<%	if (status.equals("F"))
			{ %>
				<p class="pf_bold"> Your provider maintenance form was not successfully submitted.</p>
				<p>Press your browser's back button to return to Provider Maintenance Form page and resubmit.</p>
		<%	}
			else
			{ %>
         	<p class="pf_bold"> 
          	Thank You. Your information has been submitted successfully. If you need to submit changes for another provider please click <a a href="#" onClick="javascript:document.getElementById('provider_reload_form').submit();">here</a>, other wise click the close window
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
		
		<form id="provider_reload_form" METHOD="POST" action="PDMControllerServlet">
			<input type="hidden" name="brand" id="brand" value="<%=ESAPI.encoder().encodeForHTMLAttribute(brand)%>" /> 
		</form>
		
	
		<% if (brand.equalsIgnoreCase(Constants.CADENTAL_GOLDENWEST_BRAND)) { %>
			<div id="pf_footer">
			HMO Dental coverage is provided by Golden West Dental & Vision, a Anthem, Inc., company  &copy; 2013 Anthem, Inc.
			</div>
		<% } else { %>
			<div id="pf_footer">
			&reg; Anthem is a registered trademark. &reg; The Blue Cross name and symbol are registered marks of the Blue Cross Association &copy; 2015 Anthem Blue Cross. Serving California. 
			</div>
		<% } %>		

		<% session.invalidate(); %>		
	</div><!-- END of Wrapper -->

</body>
</html>