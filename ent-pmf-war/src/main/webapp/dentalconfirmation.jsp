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
	<link rel="stylesheet" type="text/css" href="css/dental/mp_pf_forms.css" />
	<link rel="stylesheet" type="text/css" href="css/dental/ui-lightness/jquery_ui_1.8.custom.css">  
	<link rel="stylesheet" type="text/css" href="css/dental/autocomplete.css">

	<script type="text/javascript" src="js/dental/html5.js"></script>
	<script type="text/javascript" src="js/dental/jqueryLib.js"></script>
	<script type="text/javascript" src="js/dental/jquery.validate.min.js"></script>
	<script type="text/javascript" src="js/dental/jquery_ui_1.8.custom.min.js"></script>
	<script type="text/javascript" src="js/dental/pf_appScript.js"></script>	

</head>
<body class="pf_l_body">
	<div id="pf_l_wrapper"> <!-- pf_l_wrapper conatins the whole application -->
		<% if (brand.equalsIgnoreCase(Constants.CADENTAL_GOLDENWEST_BRAND)) { %>
			<h2 id="pf_l_mainHeaderGolden" name="#GoldenWest">
				<a href="#Anthem">Welcome to GOLDEN WEST</a>
			</h2>
		<% } else if (brand.equalsIgnoreCase(Constants.ABCBSDENTAL_BRAND)) { %>
			<h2 id="pf_l_mainHeaderABCBS" name="#AnthemBlueCrossBlueShield">
				<a href="#AnthemBlueCrossBlueShield">Welcome to Anthem BlueCross BlueShield</a>
			</h2>	
		<% } else if (brand.equalsIgnoreCase(Constants.BCBSGADENTAL_BRAND)) { %>
			<h2 id="pf_l_mainHeaderBCBSGA" name="#BlueCrossBlueShieldGA">
				<a href="#BlueCrossBlueShieldGA">Welcome to BlueCross BlueShield of Georgia</a>
			</h2>
		<% } else if (brand.equalsIgnoreCase(Constants.NYEBCDENTAL_BRAND)) { %>
			<h2 id="pf_l_mainHeaderNYEBC" name="#EmpireBlueCross">
				<a href="#EmpireBlueCross">Welcome to Empire BlueCross</a>
			</h2>
		<% } else if (brand.equalsIgnoreCase(Constants.NYEBCBSDENTAL_BRAND)) { %>
			<h2 id="pf_l_mainHeaderNYEBCBS" name="#EmpireBlueCrossBlueShield">
				<a href="#EmpireBlueCross">Welcome to Empire BlueCross</a>
			</h2>
		<% } else if (brand.equalsIgnoreCase(Constants.UNICAREDENTAL_BRAND)) { %>
			<h2 id="pf_l_mainHeaderUnicare" name="#Unicare">
				<a href="#Unicare">Welcome to Unicare</a>
			</h2>
		<% } else { %>
			<h2 id="pf_l_mainHeaderCA" name="#AnthemBlueCross">
				<a href="#AnthemBlueCross">Welcome to Anthem BlueCross</a>
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
		
	
		<% if (brand.equalsIgnoreCase(Constants.ABCBSDENTAL_BRAND)) { %>
			<div id="pf_footer">  
			&copy; 2005-2008 copyright of Anthem Insurance Companies, Inc.<br>
			Anthem Blue Cross and Blue Shield is the trade name of: In Colorado: Rocky Mountain Hospital and 
			Medical Service, Inc. In Connecticut: Anthem Health Plans, Inc. In Indiana: Anthem Insurance Companies, 
			Inc. In Kentucky: Anthem Health Plans of Kentucky, Inc. In Maine: Anthem Health Plans of Maine, Inc. 
			In Missouri: RightCHOICE&reg; Managed Care, Inc. (RIT), Healthy Alliance&reg; Life Insurance Company 
			(HALIC), and HMO Missouri, Inc. RIT and certain affiliates administer non-HMO benefits underwritten by 
			HALIC and HMO benefits underwritten by HMO Missouri, Inc. RIT and certain affiliates only provide 
			administrative services for self-funded plans and do not underwrite benefits. In Nevada: Rocky Mountain 
			Hospital and Medical Service, Inc. In New Hampshire: Anthem Health Plans of New Hampshire, Inc. In Ohio: 
			Community Insurance Company. In Virginia: Anthem Health Plans of Virginia, Inc. In Wisconsin: Blue Cross 
			Blue Shield of Wisconsin ("BCBSWi") underwrites or administers the PPO and indemnity policies; Compcare 
			Health Services Insurance Corporation ("Compcare") underwrites or administers the HMO policies; and 
			Compcare and BCBSWi collectively underwrite or administer the POS policies. Independent licensees of the 
			Blue Cross Blue Shield Association. &reg; ANTHEM is a registered trademark. The Blue Cross and Blue Shield 
			names and symbols are registered marks of the Blue Cross and Blue Shield Association. Serving residents 
			and businesses in Indiana, Kentucky, Missouri (excluding 30 counties in the Kansas City area), Ohio, 
			Wisconsin, Colorado, Nevada, Connecticut, Maine, New Hampshire and Virginia (excluding the city of 
			Fairfax, the town of Vienna and the area east of State Route 123). Use of the Anthem Web sites 
			constitutes your agreement with our 
			<a href="javascript:openNewWindow('https://www.anthem.com/wps/portal/ahpfooter?content_path=shared/noapplication/f6/s0/t0/pw_ad070873.htm&label=Terms of Use');">Terms of Use</a>	
			</div>
		<% } else if (brand.equalsIgnoreCase(Constants.BCBSGADENTAL_BRAND)) { %>
			<div id="pf_footer">
			&copy; 2017 BlueCross BlueShield of Georgia<br>
			Blue Cross and Blue Shield of Georgia, Inc. and Blue Cross Blue Shield Healthcare Plan of Georgia, Inc. 
			are independent licensees of the Blue Cross and Blue Shield Association. The Blue Cross and Blue Shield 
			names and symbols are registered marks of the Blue Cross and Blue Shield Association.
			</div>
		<% } else if (brand.equalsIgnoreCase(Constants.NYEBCDENTAL_BRAND)) { %>
			<div id="pf_footer">
			&copy; 2017 Empire BlueCross<br>
			Services provided by Empire HealthChoice Assurance, Inc., licensee of the Blue Cross and 
			Blue Shield Association, an association of independent Blue Cross and Blue Shield plans.
			</div>
		<% } else if (brand.equalsIgnoreCase(Constants.NYEBCBSDENTAL_BRAND)) { %>
			<div id="pf_footer">
			&copy; 2017 Empire BlueCross BlueShield<br>
			Services provided by Empire HealthChoice HMO, Inc. and/or Empire HealthChoice Assurance, Inc., and/or 
			HealthPlus, LLC., licensees of the Blue Cross and Blue Shield Association, an association of independent 
			Blue Cross and Blue Shield Plans, serving residents and businesses in the 28 eastern and southeastern 
			counties of New York State. All external sites will open in a new browser window. Please view our Website 
			Privacy Policy for more information.
			</div>
		<% } else if (brand.equalsIgnoreCase(Constants.UNICAREDENTAL_BRAND)) { %>
			<div id="pf_footer">
			For self funded plans, claims are administered by UniCare Life & Health Insurance Company. 
			Insurance coverage is provided by UniCare Life & Health Insurance Company.
			</div>
		<% } else if (brand.equalsIgnoreCase(Constants.CADENTAL_GOLDENWEST_BRAND)) { %>
			<div id="pf_footer">
			HMO Dental coverage is provided by Golden West Dental & Vision, a Anthem, Inc., company  
			&copy; 2013 Anthem, Inc.
			</div>
		<% } else { %>
			<div id="pf_footer">
			&reg; Anthem is a registered trademark. &reg; The Blue Cross name and symbol are registered 
			marks of the Blue Cross Association &copy; 2015 Anthem Blue Cross. Serving California. 
			</div>
		<% } %>			

		<% session.invalidate(); %>		
	</div><!-- END of Wrapper -->

</body>
</html>