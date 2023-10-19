<%@ page import="org.owasp.esapi.ESAPI" %>
<html>
<head>
<title>Anthem Blue Cross and Blue Shield Provider Maintenance Form</title>
<link REL="stylesheet" type="text/css" HREF="anthemcssv1.css">
<meta http-equiv="Expires" content="Mon, 18 Jun 2000 00:01:00 GMT">
<meta http=equiv="Pragma" content="no-cache">
<script language="javascript">
	<%@ include file="mwpmf_javascript.js" %>
</script>
</head>
<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0" bgcolor="#FFFFFF">
<%
    response.addHeader("X-Frame-Options", "DENY");

    String message = "";
	if(request.getParameter("isSubmitted") != null) {
		request.setAttribute("hipShow", "y");
		request.getRequestDispatcher("mwpmf.jsp").forward(request,response);
		/*
		String hipShow = ESAPI.validator().getValidInput("hipShow", request.getParameter("hipShow"), "YN", 1, false);
		if("n".equalsIgnoreCase(hipShow) ||
				"y".equalsIgnoreCase(hipShow)) {
			request.setAttribute("hipShow", hipShow);
			request.getRequestDispatcher("mwpmf.jsp").forward(request,response);
		} else {
			message = "You must select Yes or No before continuing.";
		}
		*/
	}
%>
<%@ include file="anthem_header.jsp" %>

<table width="80%" align="center">
	<tr><td>&nbsp;</td></tr>
	<tr>
		<td class="instructions-text">
			If you are a Practitioner or Facility already participating with us and would like to make changes to your 
			participation status or demographic record, please complete this Anthem [
			<a href="https://www.anthem.com/provider/provider-maintenance-form/">https://www.anthem.com/provider/provider-maintenance-form/</a>].
		</td>
	</tr>
	<tr><td>&nbsp;</td></tr>
	<tr>
		<td class="instructions-text">
			<b>Note:</b> To launch the provider maintenance form, you must use Google Chrome or Microsoft Edge.
		</td>
	</tr>
	<tr><td>&nbsp;</td></tr>
	<tr>
		<td class="instructions-text">
			If you are currently not participating with us, please read and follow the instructions below to apply.
		</td>
	</tr>
	<tr><td>&nbsp;</td></tr>
	<tr>
		<td class="instructions-header1">
			Practitioner Network Participation
		</td>
	</tr>
	<tr><td>&nbsp;</td></tr>
	<tr>
		<td class="instructions-text">
			If you wish to become a contracted provider or add a provider to your existing group, please complete an 
			Anthem digital provider enrollment application hosted on [<a href="https://availity.com">https://availity.com</a>]. 
		</td>
	</tr>
	<tr><td>&nbsp;</td></tr>
	<tr>
		<td class="instructions-header2">
			Before you begin: 
		</td>
	</tr>
	<tr>
		<td class="instructions-text">
			If your organization is not currently registered with Availity, the person designated as the administrator 
			should go to [<a href="https://availity.com">https://availity.com</a>] and select Register. Availity administrator(s) 
			will automatically be granted access to Anthem's digital provider enrollment in <b>Payer Spaces</b>.
		</td>
	</tr>
	<tr><td>&nbsp;</td></tr>
	<tr>
		<td class="instructions-text">
			If you are not an administrator and already using Availity, go to <b>My Account Dashboard > My Account > 
			Organization(s) > Administrator Information</b> to find one. Your administrator will need to add Provider 
			Enrollment to your user role in order to access Anthem's digital provider enrollment in Payer Spaces.
		</td>
	</tr>
	<tr><td>&nbsp;</td></tr>
	<tr>
		<td class="instructions-header2">
			Apply online: 
		</td>
	</tr>
	<tr>
		<td class="instructions-text">
			Log onto [<a href="https://availity.com">https://availity.com</a>] and select <b>Payer Spaces > Anthem Blue Cross 
			and Blue Shield > Applications > Provider Enrollment</b> to begin.
		</td>
	</tr>
	<tr><td>&nbsp;</td></tr>
	<tr>
		<td class="instructions-header2">
			After you apply: 
		</td>
	</tr>
	<tr>
		<td class="instructions-text">
			All the information provided in Anthem's digital provider enrollment application will be used to determine 
			contract eligibility. You will receive an application ID upon successful submission. Please use this 
			application ID if you need to [<a href="https://www.anthem.com/provider/contact-us/">https://www.anthem.com/provider/contact-us/</a>].
		</td>
	</tr>
	<tr><td>&nbsp;</td></tr>
	<tr>
		<td class="instructions-header2">
			Need assistance with Availity? 
		</td>
	</tr>
	<tr>
		<td class="instructions-text">
			Log onto [<a href="https://availity.com/contact-us">https://availity.com/Contact-Us</a>] or call Availity 
			Client Services <b>800-AVAILITY (800-282-4548)</b>.
		</td>
	</tr>
	<tr><td>&nbsp;</td></tr>
	<tr>
		<td class="instructions-header1">
			Ancillary or Facility Network Participation 
		</td>
	</tr>
	<tr><td>&nbsp;</td></tr>
	<tr>
		<td class="instructions-text">
			Are you an ancillary provider or a Healthcare Delivery Organization (HDO)?
		</td>
	</tr>
	<tr><td>&nbsp;</td></tr>
</table>

<form name="landingForm" method="post">
<input type="hidden" id="isSubmitted" name="isSubmitted" value="y"/>
<table width="80%" align="center">
	<!-- PMF Section C Changes -- AD21239 -->
	<tr><td>Do you currently participate in and want to update information or wish to apply for participation in the Medicaid State Sponsored networks and/or Healthy Indiana Plan (HIP) managed by Anthem?</td><td></td></tr>
	<tr><td>&nbsp;</td></tr>
	<tr><td>Attention Anthem Blue Cross and Blue Shield of Kentucky Providers:  If you currently participate in the state of Kentucky&#39;s Medicaid program or would like to enroll, please select &quot;No&quot;.</td><td></td></tr>
	<tr><td>&nbsp;</td></tr>
	<tr><td align="center">&nbsp;<input type="radio" id="hipShow" name="hipShow" value="y"/>Yes</td><td></td></tr>
	<tr><td align="center"><input type="radio" id="hipShow" name="hipShow" value="n"/>No</td><td></td></tr>
	<tr><td width="90%">&nbsp;</td></tr>
	<tr><td align="center"><input type="submit" id="continue" value="Continue" onClick="submit()"></input></td><td></td></tr>
</table>
</form>
<%@ include file="anthem_footer.htm" %>
</body>
</html>