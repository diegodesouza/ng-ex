<!doctype html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" %>
<%@ page session="true" import="com.anthem.mwpmf.NYVAProvMaintFormBean" %>
<%@ page import="com.anthem.mwpmf.PDMControllerServlet" %>
<%@ page import="com.anthem.util.Constants" %>
<%@ page import="com.anthem.mwpmf.NYVAProvMaintFormBean" %>
<%@ page import="com.anthem.mwpmf.PMFUtils" %>
<%@ page import="com.anthem.util.StringUtils" %>
<%@ page import="java.util.*" %>
<%@ page import="org.owasp.esapi.ESAPI" %>

<!-- force recompile in test -->

<%
  String brand = (String)session.getAttribute("brand");

  String action = "dentalsubmit";

  boolean isNotSupported = false;
  String ua = request.getHeader("User-Agent"); 
  //check if IE is version 9 or 8 and if in compatibiilty view
  isNotSupported = (ua != null && 
		  ((ua.indexOf("MSIE 7.0") != -1) || (ua.indexOf("Trident/5.0") != -1) || (ua.indexOf("Trident/4.0") != -1)));
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
    <script language="JavaScript">
		function limitTextNPI(obj)
		  {if (obj.value.length = null || obj.value.length != 10) {
          alert("The National Provider  is 10 characters in length.  Please enter all 10 characters.");
                obj.focus();
        	}
		}
		
		function limitTextTIN(obj)
		  {if (obj.value.length = null || obj.value.length != 9) {
          alert("The Tax ID Number is 9 characters in length.  Please enter all 9 characters.");
                obj.focus();
        	}
		}
		
		function limitTextArea(field, maxlimit)
		{
			if (field.value.length > maxlimit) // if too long...trim it!
				field.value = field.value.substring(0, maxlimit);
		}
		
		function openPopup(url) {
			window.open(url,'Content','left=40,top=40,width=780,height=400,toolbar=0,resizable=1,location=0,menubar=0,statusbar=0,scrollbars=yes');
		}
		
		function deleteRow(rowNum) {
        	document.forms[1].formUpdateAction.value='deleteRow';
        	document.forms[1].dltRowCounter.value=rowNum;
        	document.forms[1].submit();
        }
		
		
		function fileUpload(form, action_url, div_id) {
	        var filepath = form.fileContentData.value;
	        
	        if (filepath == null || filepath.length == 0)
	        	alert('The Filename is requireOption.');
	        else {
	 
	        var myFSO = new ActiveXObject("Scripting.FileSystemObject");
	        var thefile = myFSO.getFile(filepath);
	        var filesize = thefile.size;
	        var filename = thefile.name;
	        var comment = document.getElementById("uploadDocComment").value;
	        if ((comment==null) || (comment==''))
	        	comment = ' ';
	        comment = isValidTextInput(comment) ? sanitizeHTML(comment) : '';
	        var patt = new RegExp("^[A-Za-z0-9@#&$'\"_\\s\\S-]+(\.(jpg|gif|doc|docx|xls|xlsx|pdf|txt|csv))$", "i");
	        var pattCom = new RegExp("[^<>\]*");
	        
	        if (form.uploadFileName.value.indexOf(filename) != -1) {
	        	alert('The Filename chosen is already Uploaded.');        	
	        }
	        else if (convertNumberToMB(Number(form.totalFileSize.value)+Number(filesize)) > 10) {
	        	alert('The total file size of '+convertNumberToMB(Number(document.forms[1].totalFileSize.value)+Number(filesize))+' MB has exceeded the limit.');        				        	
	        }
	        else if (filename.length > 200) {
	        	alert('The Filename cannot be greater than 200 characters.');
	        }
	        else if (!patt.test(filename)) {
	        	alert('The file is an invalid type or the name contains invalid characters.');
	        }
	        else if (!pattCom.test(comment)) {
	        	alert('The Description of Attachment contains invalid characters.');
	        }
	        else
	        {
			    // Create the iframe...
			    var iframe = document.createElement("iframe");
			    iframe.setAttribute("id", "upload_iframe");
			    iframe.setAttribute("name", "upload_iframe");
			    iframe.setAttribute("width", "0");
			    iframe.setAttribute("height", "0");
			    iframe.setAttribute("border", "0");
			    iframe.setAttribute("style", "width: 0; height: 0; border: none;");
			 
			    // Add to document...
			    form.parentNode.appendChild(iframe);
			    window.frames['upload_iframe'].name = "upload_iframe";
			 
			    iframeId = document.getElementById("upload_iframe");
			 
			    // Add event...
			    var eventHandler = function () {
			 
		            if (iframeId.detachEvent) iframeId.detachEvent("onload", eventHandler);
		            else iframeId.removeEventListener("load", eventHandler, false);
		 
		            // Message from server...
		            if (iframeId.contentDocument) {
		                content = iframeId.contentDocument.body.innerHTML;
		            } else if (iframeId.contentWindow) {
		                content = iframeId.contentWindow.document.body.innerHTML;
		            } else if (iframeId.document) {
		                content = iframeId.document.body.innerHTML;
		            }
		 
		            document.getElementById(div_id).innerHTML = content;
		 
		            // Del the iframe...
		            setTimeout('iframeId.parentNode.removeChild(iframeId)', 250);
		        }
			 
			    if (iframeId.addEventListener) iframeId.addEventListener("load", eventHandler, true);
			    if (iframeId.attachEvent) iframeId.attachEvent("onload", eventHandler);
			 
			    // Set properties of form...
			    form.setAttribute("target", "upload_iframe");
			    form.setAttribute("action", action_url);
			    form.setAttribute("method", "post");
			    form.setAttribute("enctype", "multipart/form-data");
			    form.setAttribute("encoding", "multipart/form-data");
			 
			    // Submit the form...
			    form.formUpdateAction.value='uploadFile';
			    form.submit();
			    
			    form.uploadFileName.value = addNewRecord(document.forms[1].uploadFileName.value,filename);
			    form.uploadFileSize.value = addNewRecord(document.forms[1].uploadFileSize.value,filesize);
			    form.uploadFileComment.value = addNewRecord(document.forms[1].uploadFileComment.value,comment);
			    form.totalFileSize.value = Number(filesize) + Number(document.forms[1].totalFileSize.value);
		        $('#fileListTable').append('<tr><td>'+filename+'</td>'
		        		+'<td>'+convertNumberToMB(filesize)+'</td>'
		        		+'<td>'+comment+'</td>'
		        		+'<td><a href="" class="delete">Remove</a></td></tr>');
		        clearFileInput(document.getElementById("fileContentData"));
		        document.getElementById("uploadDocComment").value = '';
			 
			    document.getElementById(div_id).innerHTML = "Uploading...";
	        }
	        }
		}
		
		function addNewRecord(text, addtext) {
			if (text == null || text == ''){
				text = addtext;
			} else {
				text = text + '::' + addtext;
			}
			return text;
		}			
		
		function clearFileInput(ctrl) {
		  try {
		    ctrl.value = null;
		  } catch(ex) { }
		  if (ctrl.value) {
		    ctrl.parentNode.replaceChild(ctrl.cloneNode(true), ctrl);
		  }
		}
		
		function convertNumberToMB(number){
			var mb = (number/1024)/1024;
			return (Math.round(mb*100)/100).toFixed(2);
		}
		
	    var sanitizeHTML = function (str) {
	        var temp = document.createElement('div');
	        temp.textContent = str;
	        //return temp.innerHTML;
	        return temp.textContent;
        };
       
        function isValidTextInput(inputValue){
	        return null != inputValue && (inputValue.indexOf("<script>") >=-1 
	            || inputValue.indexOf("<\/script>") >=-1 || inputValue.indexOf("<script") >=-1) ? true : false;
	    }
		
	</script>
    
	<noscript>Your browser does not support JavaScript!</noscript> 
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
				<a href="#BlueCrossBlueShieldGA">Welcome to BlueCross BlueShield of Georgia </a>
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
          <p class="pf_boldital"> The Provider Maintenance Form (PMF) is to be used by dental practitioners to request changes to their practice profiles.</font></p>
            <p>It is critical that our members receive accurate and current data related to provider availability. Changes to provider records that are affiliated with group contracts must be reported to and submitted by the practice manager or other designated person of authority at the group. Changes to individual contracts may be made at the direction of the contracted dental practitioner. All requests must be received 30 days prior to the change/update. Submit the PMF to notify of any changes to the provider/practice name, practice/mailing address, tax identification number, phone and fax numbers, practice office hours, provider leaving/retiring, provider joining the practice, practice accepting new patients, handicapped accessibility, specialties or languages offered. </p>
            <p class="pf_boldital">Please follow these instructions when submitting the PMF:</p>
            <p>Complete all applicable sections. This form has multiple options (+) for changes. Complete only the sections applicable to the requested change(s). NOTE: This form will time out after 30 minutes of activity or inactivity and all entries made but not yet submitted will be lost.</p>
            <p>
			<p>Please note: A Network Representative will contact you if your change requires a W-9, or any additional documentation is required to complete the update.</p>
			<p>Contact Information: </p>
			<p>Email:  <a href="mailto:DentalNetworkSubmit@Anthem.com">DentalNetworkSubmit@Anthem.com</a> </p>
			<p>Fax: (877) 283-1331</p>
			<p>Mail:</p>
			<p>P.O. Box 640</p>
			<p>Minneapolis, MN 55440-0640</p>			
			<div class="pf_l_msgContainer">
				<h2>Reason for Submitting this Form</h2>
				<div id="pf_msgOpt1" class="pf_msgOpt left cWidth60 pf_tab1">
                <span class="pf_bold">Option 1 - Provider Level Changes</span>
					<ul>
						<li>Remove a provider from a location</li>
						<li>Name change for individual dental practitioner</li>
						<li>License number change for individual dental practitioner</li>
						<li>Add or change provider's degree</li>
						<li>Update or add your Individual NPI</li>
						<li>Add or change provider's language(s) spoken (other than English)</li>
						<li>Add or change your provider specialty or type</li>
					</ul>
					<span class="label">Please note: Specialty changes may require additional follow-up if not already approved by credentialing.</span><br>
					<a href="#tab1" id="gotoTab1" class="gotoTab">CLICK HERE</a><span class="pf_bold"> to make one or more of the above changes.</span>
					<div class="clear"></div>
				</div>
				<div id="pf_msgOpt2" class="pf_msgOpt right cWidth30 pf_tab2">
                <span class="pf_bold">Option 2</span>
					<ul>
						<font color="#FF0000">Practice changes (these changes apply to all providers currently practicing under the Tax Identification (TIN) provided). </font><br /><br>
						
						<font color="#FF0000">Note:  If additional information is required you will be contacted by a Network Representative.</font><br />

						<li>Change your Tax Identification Number/IRS Name (TIN)</li>
						<li>Change in ownership for an existing practice</li>
						<li>Change your practice address or phone/fax number</li>
						<li>Change your practice name</li>
						<li>Add a new location</li>                        
						<li>Close a practice location</li>
						<li>Change your billing or correspondence address</li>
						<li>Change your office hours or days of operation</li>
						<li>Change in your acceptance of new patients</li>
						<li>Update or add your Organizational Clinic or Corporate NPI</li>
						<li>Update or add your email address(es)</li>
						<li>Add or change language(s) spoken by qualified medical interpreter</li>
					</ul>
					<a href="#tab1" id="gotoTab2" class="gotoTab">CLICK HERE</a><span class="pf_bold"> to make one or more of the above changes.</span>
					<div class="clear"></div>
				</div>
				<div class="clear"></div>
			</div>
			<div class="pf_l_tabContainer" style="display:none"> <!-- pf_l_tabContainer contains the Tabbed Forms -->
				<h3 class="pf_tab" id="pf_tab1" name="tab1"> <!-- pf_tabsel highlights the selected tab -->
					Option 1
					<input type="hidden" name="toemail" value="option1" />
				</h3>
				<h3 class="pf_tab" id="pf_tab2" name="tab2">
					Option 2
					<input type="hidden" name="toemail" value="option2" />
				</h3>
				<h3 class="pf_tab" id="pf_tab3"name="tab3">
					Option 3
					<input type="hidden" name="toemail" value="option3" />
				</h3>
				
				<div class="tabSeparator"> </div><!-- This Div separates the Tab Btns with Tab Body -->
				<div class="pf_tabBody">
			
				
				<!-- START of FORM 3 -->
				
				<form CLASS="provider_detail_form" METHOD="POST" action="PDMControllerServlet">
			
			    
				<ul class="pf_formSection"> <!-- pf_formSection contains the whole Forms -->
					<span class="requireOptionText">Required fields<em>*</em></span>
					<li id="form_slist0" class="pf_tab1 pf_section" > <!-- form_slist0 is one Section: hidden or shown for each mail box -->
						<h2 class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">General information <em>(mandatory fields – <font class="pf_bold">must</font> be completed to submit form)</em></span>
						</h2>
						<div class="toggler"> <!-- Start of the FORM elements for one Section -->
							
							
							<fieldset>
								<label>Effective date for the add, change or delete<em>*</em></label>
                                <input type="text" name="Effective_Date"  class="date requireOption"  onKeyDown="limitTextArea(this.form.Effective_Date,20);" onKeyUp="limitTextArea(this.form.Effective_Date,20);"/> <br />
                                <span class="label">Please note: Effective date may be adjusted based on policy and procedures.</span><br>
							 </fieldset>
							
                            <fieldset>
								<legend>Provider details</legend>
								<label>Last name<em>*</em></label><input type="text" id="Gen_Provider_Last_Name" name="Gen_Provider_Last_Name" class="requireOption" onKeyDown="limitTextArea(this.form.Gen_Provider_Last_Name,35);" onKeyUp="limitTextArea(this.form.Gen_Provider_Last_Name,35);" /><br />
                                <label>First name<em>*</em></label><input type="text" id="Gen_Provider_First_Name" name="Gen_Provider_First_Name" class="requireOption" onKeyDown="limitTextArea(this.form.Gen_Provider_First_Name,35);" onKeyUp="limitTextArea(this.form.Gen_Provider_First_Name,35);" /><br />
                                <label>Middle name</label><input type="text" id="Gen_Provider_Middle_Name" name="Gen_Provider_Middle_Name" class=""  /><br />
                                <label>Professional title</label><input type="text" id="Gen_Professional_Title" name="Gen_Professional_Title" class="" /><br />
                                <label>Suffix</label><input type="text" id="Provider_Suffix" name="Provider_Suffix" class=""  /><br />
                                <label>Practice Tax Identification Number (TIN)<em>*</em></label><input type="text" id="Gen_Ind_TIN" name="Gen_Ind_TIN" class="TIN requireOption"/><br />
								<label>Individual National Provider Identifier (NPI)</label><input type="text" id="GenSec_Prov_Ded_NPI" name="GenSec_Prov_Ded_NPI" class="NPI" /><br />
								<label>License #<em>*</em></label><input type="text" id="Provider_License" name="Provider_License" class="requireOption"  /><br />
							</fieldset>
							
				
							<fieldset>
								<legend>Contact details</legend>
								<label>Contact person last name<em>*</em></label><input type="text" name="Contact_Person_Last_Name" class="requireOption" onKeyDown="limitTextArea(this.form.Contact_Person_Last_Name,35);" onKeyUp="limitTextArea(this.form.Contact_Person_Last_Name,35);"/><br />
                                <label>Contact person first name<em>*</em></label><input type="text" name="Contact_Person_First_Name" class="requireOption" onKeyDown="limitTextArea(this.form.Contact_Person_First_Name,35);" onKeyUp="limitTextArea(this.form.Contact_Person_First_Name,35);"/><br />
								
								<label>Email address<em>*</em></label><input type="text" name="Email_Address" class="requireOption email" onKeyDown="limitTextArea(this.form.Email_Address,40);" onKeyUp="limitTextArea(this.form.Email_Address,40);"/><br />
								<label>Phone number<em>*</em></label><input type="text" name="Phone_Number" class="requireOption" onKeyDown="limitTextArea(this.form.Phone_Number,25);" onKeyUp="limitTextArea(this.form.Phone_Number,25);"/><br />
							</fieldset>
							
						</div>
						
					</li>
					
					<li id="form_slist9" class="pf_tab2 pf_section" > <!-- form_slist9 is one Section: hidden or shown for each mail box -->
						<h2 class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">General information <em>(mandatory fields – <font class="pf_bold">must</font> be completed to submit form)</em></span>
						</h2>
						<div class="toggler"> <!-- Start of the FORM elements for one Section -->
							
							
							<fieldset>
								<label>Effective date for the add, change or delete<em>*</em></label>
                                <input type="text" name="Effective_Date_2"  class="requireOption date"  onKeyDown="limitTextArea(this.form.Effective_Date_2,20);" onKeyUp="limitTextArea(this.form.Effective_Date_2,20);"/> <br />
							 </fieldset>
							

                            <fieldset>
								<legend>Practice details</legend>
								<label>Practice name<em>*</em></label><input type="text" id="Gen_Practice_Name_2" name="Gen_Practice_Name_2" class="requireOption" onKeyDown="limitTextArea(this.form.Gen_Practice_Name_2,55);" onKeyUp="limitTextArea(this.form.Gen_Practice_Name_2,55);" /><br />
								<label>Practice Tax Identification Number (TIN)<em>*</em></label><input type="text" id="Gen_Pract_TIN_2" name="Gen_Pract_TIN_2" class="requireOption TIN"/><br />
                                <label>Practice address line 1<em>*</em></label><input type="text" id="Gen_Provider_Pract_Address_Line1" name="Gen_Provider_Pract_Address_Line1" class="requireOption" "/><br />
                                <label>Practice address line 2</label><input type="text" id="Gen_Provider_Pract_Address_Line2" name="Gen_Provider_Pract_Address_Line2" class="" /><br />
								<label>City<em>*</em></label><input type="text" id="Gen_Provider_Pract_City" name="Gen_Provider_Pract_City" class="requireOption" />  <br />
								<label>State<em>*</em></label><input type="text" id="Gen_Provider_Pract_State" name="Gen_Provider_Pract_State" class="requireOption" />  <br />
								<label>Zip<em>*</em></label><input type="text" id="Gen_Provider_Pract_Zip" name="Gen_Provider_Pract_Zip" class="requireOption" />  <br />
								<label>Organizational Clinic National Provider Identifier (NPI)</label><input type="text" id="GenSec_Org_Clinic_NPI" name="GenSec_Org_Clinic_NPI" class="NPI" /><br />
								<label>Organizational Corporate National Provider Identifier (NPI)</label><input type="text" id="GenSec_Org_Corporate_NPI" name="GenSec_Org_Corporate_NPI" class="NPI" /><br />
							</fieldset>

                            <fieldset>
								<legend>Provider details</legend>
								<font color="#FF0000">Provider’s information below should be the name of who owns the Tax Identification Number for this practice location.</font><br />
								<label>Last name</label><input type="text" id="Gen_Provider_Last_Name_2" name="Gen_Provider_Last_Name_2" onKeyDown="limitTextArea(this.form.Gen_Provider_Last_Name_2,35);" onKeyUp="limitTextArea(this.form.Gen_Provider_Last_Name_2,35);" /><br />
                                <label>First name</label><input type="text" id="Gen_Provider_First_Name_2" name="Gen_Provider_First_Name_2" onKeyDown="limitTextArea(this.form.Gen_Provider_First_Name_2,35);" onKeyUp="limitTextArea(this.form.Gen_Provider_First_Name_2,35);" /><br />
                                <label>Middle name</label><input type="text" id="Gen_Provider_Middle_Name" name="Gen_Provider_Middle_Name" class=""  /><br />
								<label>License #</label><input type="text" id="Provider_License" name="Provider_License" class=""  /><br />
							</fieldset>
											
							<fieldset>
								<legend>Contact details</legend>
								<label>Contact person last name<em>*</em></label><input type="text" name="Contact_Person_Last_Name_2" class="requireOption" onKeyDown="limitTextArea(this.form.Contact_Person_Last_Name_2,35);" onKeyUp="limitTextArea(this.form.Contact_Person_Last_Name_2,35);" /><br />
                                <label>Contact person first name<em>*</em></label><input type="text" name="Contact_Person_First_Name_2" class="requireOption" onKeyDown="limitTextArea(this.form.Contact_Person_First_Name_2,35);" onKeyUp="limitTextArea(this.form.Contact_Person_First_Name_2,35);" /><br />
								<label>Email address<em>*</em></label><input type="text" name="Email_Address_2" class="requireOption email" onKeyDown="limitTextArea(this.form.Email_Address_2,40);" onKeyUp="limitTextArea(this.form.Email_Address_2,40);" /><br />
								<label>Phone number<em>*</em></label><input type="text" name="Phone_Number_2" class="requireOption" onKeyDown="limitTextArea(this.form.Phone_Number_2,25);" onKeyUp="limitTextArea(this.form.Phone_Number_2,25);" /><br />
							</fieldset>
							
						</div>
						
					</li>
									
					<li id="form_slist0" class="pf_tab1 pf_section"> <!-- form_slist0 is one Section: hidden or shown for each mail box -->
						<h2 id="form_tog1Handle" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Remove a provider from a location</span>
						</h2>
						<div id="form_tog1" class="toggler"> <!-- Start of the FORM elements for one Section -->
			
							<fieldset>
							<legend>Remove a provider from a location</legend>
								<label for="form_ac_s1_reason_removal">Reason for removal of provider</label>
								<input type="text" id="form_ac_s1_reason_removal" name="form_ac_s1_reason_removal" title="" />  <br />
								<label for="form_ac_s1_remAddr1">Practice address line 1<em>*</em></label>
								<input type="text" id="form_ac_s1_remAddr1" name="Practice address line 1" class="requiredSection" />  <br />
								<label for="form_ac_s1_remAddr2">Practice address line 2</label>
								<input type="text" id="form_ac_s1_remAddr2" name="Practice address line 2" />  <br />								
								<label for="form_ac_s1_remCity">City<em>*</em></label>
								<input type="text" id="form_ac_s1_remCity" name="form_ac_s1_remCity" class="requiredSection" title="Please type your City name" />  <br />
								<label for="form_ac_s1_remState">State<em>*</em></label>
								<input type="text" id="form_ac_s1_remState" name="form_ac_s1_remState" class="requiredSection" title="Please type your State" />  <br />
								<label for="form_ac_s1_remZip">Zip<em>*</em></label>
								<input type="text" id="form_ac_s1_remZip" name="form_ac_s1_remZip" class="requiredSection" title="Please type your Zipcode" />  <br />
								<label>Effective date<em>*</em></label>
                                <input type="text" id="form_ac_s1_remEffDate" name="form_ac_s1_remEffDate"  class="requiredSection date"  onKeyDown="limitTextArea(this.form.form_ac_s1_remEffDate,20);" onKeyUp="limitTextArea(this.form.form_ac_s1_remEffDate,20);"/> <br />								
							</fieldset>
						</div> <!-- END of First Toggling DIV Tog1 -->
					</li> <!-- END of First Section -->
					<li id="form_slist1" class="pf_tab1 pf_section"> <!-- form_slist1 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle2" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Name change for individual dental practitioner</span>
						</h2>
						<div id="form_tog2" class="toggler"> <!-- Start of the FORM elements for one Section -->
							<font color="#FF0000">Please note: In order to process your change(s), your name must match what is listed with the state board.</font><br />								
							<fieldset>
							<legend>Provider name (former)</legend>
								<label for="form_ac_s2_provLastNameFormer">Last name<em>*</em></label>
								<input type="text" id="form_ac_s2_provLastNameFormer" name="form_ac_s2_provLastNameFormer" class="requiredSection" />  <br />
								<label for="form_ac_s2_provFirstNameFormer">First name<em>*</em></label>
								<input type="text" id="form_ac_s2_provFirstNameFormer" name="form_ac_s2_provFirstNameFormer" class="requiredSection" />  <br />
								<label for="form_ac_s2_provMiddleNameFormer">Middle name</label>
								<input type="text" id="form_ac_s2_provMiddleNameFormer" name="form_ac_s2_provMiddleNameFormer" />  <br />
								<label for="form_ac_s2_provSuffixFormer">Suffix</label>
								<input type="text" id="form_ac_s2_provSuffixFormer" name="Suffix" />  <br />
								<label for="form_ac_s2_proffTitle">Professional Title</label>
								<input type="text" id="form_ac_s2_proffTitle" name="Professional Title" />  <br />								
							</fieldset>
							<fieldset>
							<legend>Provider name (updated)</legend>
								<label for="form_ac_s2_provLastNameUpdated">Last name<em>*</em></label>
								<input type="text" id="form_ac_s2_provLastNameUpdated" name="form_ac_s2_provLastNameUpdated" class="requiredSection" />  <br />
								<label for="form_ac_s2_provFirstNameUpdated">First name<em>*</em></label>
								<input type="text" id="form_ac_s2_provFirstNameUpdated" name="form_ac_s2_provFirstNameUpdated" class="requiredSection" />  <br />
								<label for="form_ac_s2_provMiddleNameUpdated">Middle name</label>
								<input type="text" id="form_ac_s2_provMiddleNameUpdated" name="form_ac_s2_provMiddleNameUpdated"  />  <br />
								<label for="form_ac_s2_provSuffixUpdated">Suffix</label>
								<input type="text" id="form_ac_s2_provSuffixUpdated" name="Suffix" />  <br />
								<label for="form_ac_s2_proffTitleUpdated">Professional Title</label>
								<input type="text" id="form_ac_s2_proffTitleUpdated" name="Professional Title" />  <br />		
								<label for="form_ac_s2_state_linense">State License<em>*</em></label>
								<input type="text" id="form_ac_s2_state_linense" name="State Licnese" class="requiredSection" />  <br />								
														
							</fieldset>
						
						</div> <!-- END of form_tog2: the second Section Body -->
					</li> <!-- END of Second Form Section -->
					
					<li id="form_slist" class="pf_tab1 pf_section"> <!-- form_slist1 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">License number change for individual dental practitioner</span>
						</h2>
						<div id="form_tog2" class="toggler"> <!-- Start of the FORM elements for one Section -->
							<fieldset>
							<legend>Provider name</legend>
								<label for="form_ac_s22_provLastNameUpdated">Last name<em>*</em></label>
								<input type="text" id="form_ac_s22_provLastNameUpdated" name="form_ac_s22_provLastNameUpdated" class="requiredSection" />  <br />
								<label for="form_ac_s22_provFirstNameUpdated">First name<em>*</em></label>
								<input type="text" id="form_ac_s22_provFirstNameUpdated" name="form_ac_s22_provFirstNameUpdated" class="requiredSection" />  <br />
								<label for="form_ac_s22_provMiddleNameUpdated">Middle name</label>
								<input type="text" id="form_ac_s22_provMiddleNameUpdated" name="form_ac_s22_provMiddleNameUpdated"  />  <br />
								<label for="form_ac_s22_provSuffixUpdated">Suffix</label>
								<input type="text" id="form_ac_s22_provSuffixUpdated" name="form_ac_s22_provSuffixUpdated" />  <br />
								<label for="form_ac_s22_proffTitleUpdated">Professional Title</label>
								<input type="text" id="form_ac_s22_proffTitleUpdated" name="form_ac_s22_proffTitleUpdated" />  <br />		
								<label for="form_ac_s22_license_number">State license Number<em>*</em>(Updated)</label>
								<input type="text" id="form_ac_s22_license_number" name="form_ac_s22_license_number" class="requiredSection" />  <br />								
								<label for="form_ac_s22_licensing_state">Licensing State</label>
								<input type="text" id="form_ac_s22_licensing_state" name="form_ac_s22_licensing_state" />  <br />																						
							</fieldset>
						
						</div> <!-- END of form_tog2: the second Section Body -->
					</li> <!-- END of Second Form Section -->
					
					<li id="form_slist1" class="pf_tab1 pf_section"> <!-- form_slist1 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle2" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Add or Change Provider's Degree</span>
						</h2>
						<div id="form_tog2" class="toggler"> <!-- Start of the FORM elements for one Section -->
						
							<fieldset>
							<legend>Add or Change Degree</legend>
								<%--  this hidden field is added as work around. with single field validation has some issues. --%>
								<input type="hidden" id="dummy" name="dummy" /> 
								<label for="form_ac_s2_DegChangeproffTitle">Professional Title</label>
								<input type="text" id="form_ac_s2_DegChangeproffTitle" name="form_ac_s2_DegChangeproffTitle" />  <br />								
							</fieldset>
						</div> <!-- END of form_tog2: the second Section Body -->
					</li> <!-- END of Second Form Section -->
                    
					<li id="form_slist5" class="pf_tab1 pf_section"> <!-- form_slist3 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle6" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Update or add your Individual NPI</span>
						</h2>
						<div class="toggler"> <!-- Start of the FORM elements for one Section -->
							<fieldset>
							<legend>Update or add your Individual NPI</legend>
								<label>Request Type</label>
								<input type="radio" name="s10RequestType" value="Add" id="s10RequestAdd" style="width:auto" /><label for="RequestAdd"  style="width:auto;margin-right:5px;" class="exclude">Add</label>
								<input type="radio" name="s10RequestType" value="Change" id="s10RequestChange" style="width:auto" /><label for="RequestChange"  style="width:auto;margin-right:5px;" class="exclude">Change</label>								
								<input type="radio" name="s10RequestType" value="Delete"  id="s10RequestDelete" style="width:auto"/><label for="RequestDelete" style="width:auto;margin-right:5px;" class="exclude">Delete</label><br />
								<label for="form_ac_s10_indNPI">Individual National Provider Identifier (NPI)</label>
								<input type="text" id="form_ac_s10_indNPI" name="form_ac_s10_indNPI" class="NPI"/>  <br />		
							</fieldset>
						</div> <!-- END of form_tog4: the sixth Section Body -->
					</li> <!-- END of sixth Form Section -->
					<li id="form_slist6" class="pf_tab1 pf_tab3 pf_section"> <!-- form_slist3 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle7" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Add or change provider's language(s) spoken</span>
						</h2>
						<div class="toggler"> <!-- Start of the FORM elements for one Section -->
							<fieldset>
							<legend>Provider details</legend>
								<label>Request Type</label>
								<input type="radio" name="s11RequestType" value="Add" id="s11RequestAdd" style="width:auto" /><label for="RequestAdd"  style="width:auto;margin-right:5px;" class="exclude">Add</label>
								<input type="radio" name="s11RequestType" value="Delete"  id="s11RequestDelete" style="width:auto"/><label for="RequestDelete" style="width:auto;margin-right:5px;" class="exclude">Delete</label><br />
                                <label for="form_ac_s12pOtherLang">Provider language(s) spoken (other than English)</label>
								<select id="form_ac_s12pOtherLang" name="Provider_Lang_Othe_Than_English"  multiple >
									<option selected></option>
									<option value="AFGHAN">AFGHAN</option>
									<option value="AFRIKAANS">AFRIKAANS</option>
									<option value="ALBANIAN">ALBANIAN</option>
									<option value="AMHARIC">AMHARIC</option>
									<option value="ARABIC">ARABIC</option>
									<option value="ARAMAIC">ARAMAIC</option>
									<option value="ARMENIAN">ARMENIAN</option>
									<option value="ASSAMESE">ASSAMESE</option>
									<option value="ASSYRIAN">ASSYRIAN</option>
									<option value="AZERBAIJANI">AZERBAIJANI</option>
									<option value="BANGLADESHI">BANGLADESHI</option>
									<option value="BEMBA">BEMBA</option>
									<option value="BENGALI">BENGALI</option>
									<option value="BHOJPURI">BHOJPURI</option>
									<option value="BIKOL">BIKOL</option>
									<option value="BOSNIAN">BOSNIAN</option>
									<option value="BULGARIAN">BULGARIAN</option>
									<option value="BURMESE">BURMESE</option>
									<option value="CALDEON">CALDEON</option>
									<option value="CAMBODIAN">CAMBODIAN</option>
									<option value="CANTONESE">CANTONESE</option>
									<option value="CATALAN">CATALAN</option>
									<option value="CEBUANO">CEBUANO</option>
									<option value="CHICHEWA">CHICHEWA</option>
									<option value="CHINESE">CHINESE</option>
									<option value="CREOLE">CREOLE</option>
									<option value="CROATIAN">CROATIAN</option>
									<option value="CZECH">CZECH</option>
									<option value="DANISH">DANISH</option>
									<option value="DUTCH">DUTCH</option>
									<option value="EGYPTIAN">EGYPTIAN</option>
									<option value="ENGLISH">ENGLISH</option>
									<option value="FAROESE">FAROESE</option>
									<option value="FARSI">FARSI</option>
									<option value="FILIPINO">FILIPINO</option>
									<option value="FINNISH">FINNISH</option>
									<option value="FLEMISH">FLEMISH</option>
									<option value="FOOKIEN">FOOKIEN</option>
									<option value="FRENCH">FRENCH</option>
									<option value="GEORGIAN">GEORGIAN</option>
									<option value="GERMAN">GERMAN</option>
									<option value="GREEK">GREEK</option>
									<option value="GUJARATI">GUJARATI</option>
									<option value="HAITIAN">HAITIAN</option>
									<option value="HAITIAN CREOLE">HAITIAN CREOLE</option>
									<option value="HAUSA">HAUSA</option>
									<option value="HAWAIIAN">HAWAIIAN</option>
									<option value="HEBREW">HEBREW</option>
									<option value="HINDI">HINDI</option>
									<option value="HMONG">HMONG</option>
									<option value="HUNGARIAN">HUNGARIAN</option>
									<option value="IBU">IBU</option>
									<option value="ICELANDIC">ICELANDIC</option>
									<option value="IGBO">IGBO</option>
									<option value="ILOCANO">ILOCANO</option>
									<option value="INDONESIAN">INDONESIAN</option>
									<option value="IRANIAN">IRANIAN</option>
									<option value="ITALIAN">ITALIAN</option>
									<option value="JAPANESE">JAPANESE</option>
									<option value="KANNADA">KANNADA</option>
									<option value="KAPAMPANGAN">KAPAMPANGAN</option>
									<option value="KASHMIRI">KASHMIRI</option>
									<option value="KHMER">KHMER</option>
									<option value="KONKANI">KONKANI</option>
									<option value="KOREAN">KOREAN</option>
									<option value="KURDISH">KURDISH</option>
									<option value="LAOTIAN">LAOTIAN</option>
									<option value="LATIN">LATIN</option>
									<option value="LATVIAN">LATVIAN</option>
									<option value="LEBANESE">LEBANESE</option>
									<option value="LITHUNIAN">LITHUNIAN</option>
									<option value="MACEDONIAN">MACEDONIAN</option>
									<option value="MADAGASHI">MADAGASHI</option>
									<option value="MAITHILI">MAITHILI</option>
									<option value="MALAYALAM">MALAYALAM</option>
									<option value="MALAYSIAN">MALAYSIAN</option>
									<option value="MANDARIN">MANDARIN</option>
									<option value="MARATHI">MARATHI</option>
									<option value="MOHAWIK">MOHAWIK</option>
									<option value="MONGOLIAN">MONGOLIAN</option>
									<option value="NAVAJO">NAVAJO</option>
									<option value="NEPALI">NEPALI</option>
									<option value="NORWEGIAN">NORWEGIAN</option>
									<option value="OJIBWE">OJIBWE</option>
									<option value="ORADAU">ORADAU</option>
									<option value="PAMPANGA">PAMPANGA</option>
									<option value="PERSIAN">PERSIAN</option>
									<option value="POLISH">POLISH</option>
									<option value="PORTUGUESE">PORTUGUESE</option>
									<option value="PUNJABI">PUNJABI</option>
									<option value="ROMANIAN">ROMANIAN</option>
									<option value="RUSSIAN">RUSSIAN</option>
									<option value="SAMOAN">SAMOAN</option>
									<option value="SANSKRIT">SANSKRIT</option>
									<option value="SERBIAN">SERBIAN</option>
									<option value="SERBOCROATIAN">SERBOCROATIAN</option>
									<option value="SHONA">SHONA</option>
									<option value="SIGN">SIGN</option>
									<option value="SINDHI">SINDHI</option>
									<option value="SINHALESE">SINHALESE</option>
									<option value="SLOVAK">SLOVAK</option>
									<option value="SOMALI">SOMALI</option>
									<option value="SPANISH">SPANISH</option>
									<option value="SWAHILI">SWAHILI</option>
									<option value="SWEDISH">SWEDISH</option>
									<option value="SWISS">SWISS</option>
									<option value="SYRIAN">SYRIAN</option>
									<option value="TAGALOG">TAGALOG</option>
									<option value="TAIWANESE">TAIWANESE</option>
									<option value="TAMIL">TAMIL</option>
									<option value="TELUGU">TELUGU</option>
									<option value="THAI">THAI</option>
									<option value="TIGRIYINI">TIGRIYINI</option>
									<option value="TURKISH">TURKISH</option>
									<option value="TWI">TWI</option>
									<option value="UKRAINIAN">UKRAINIAN</option>
									<option value="URDU">URDU</option>
									<option value="UZBEK">UZBEK</option>
									<option value="VIETNAMESE">VIETNAMESE</option>
									<option value="VISAYAN">VISAYAN</option>
									<option value="YIDDISH">YIDDISH</option>
									<option value="YORUBA">YORUBA</option>
									<option value="ZHUANG">ZHUANG</option>
									<option value="ZULU">ZULU</option>
								</select> <br />
								<font color="#FF0000">Note:  Hold the Ctrl key to select multiple languages.</font><br />
							</fieldset>
						</div> <!-- END of form_tog4: the sixth Section Body -->
					</li> <!-- END of sixth Form Section -->
					
					<li id="form_slist7" class="pf_tab1 pf_section"> <!-- form_slist3 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle8" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Add or change your provider specialty or type</span>
						</h2>
						<div class="toggler"> <!-- Start of the FORM elements for one Section -->
							<fieldset>
							<legend>Add or change your provider specialty or type</legend>
								<label>Request Type</label>
								<input type="radio" name="s7RequestType" value="Add" id="s7RequestAdd" style="width:auto" /><label for="RequestAdd"  style="width:auto;margin-right:5px;" class="exclude">Add</label>
								<input type="radio" name="s7RequestType" value="Change"  id="s7RequestDelete" style="width:auto"/><label for="RequestChange" style="width:auto;margin-right:5px;" class="exclude">Change</label><br />
								<font color="#FF0000">Please note: If you are adding or changing a specialty, it may require additional follow-up if not already approved by credentialing.</font><br />
							</fieldset>						
							<fieldset>
							<legend>Provider details</legend>
                                <label for="form_ac_s7ProviderType">Provider Type</label>
								<select id="form_ac_s7ProviderType" name="form_ac_s7ProviderType">
									<option selected></option>
									<option value="Endodontist">Endodontist</option>
									<option value="Oral Surgeon">Oral Surgeon</option>
									<option value="Orthodontist">Orthodontist</option>
									<option value="Pediatric Dentist">Pediatric Dentist</option>
									<option value="Periodontist">Periodontist</option>
									<option value="Prosthodontist">Prosthodontist</option>
									<option value="Hygienist">Hygienist</option>
									<option value="Denturist">Denturist</option>
									<option value="General Dentist">General Dentist</option>
								</select> <br />
                                <label for="form_ac_s7ProviderType_Other">Other</label>
								<select id="form_ac_s7ProviderType_Other" name="form_ac_s7ProviderType_Other">
									<option selected></option>
									<option value="Endodontist">Endodontist</option>
									<option value="Oral Surgeon">Oral Surgeon</option>
									<option value="Orthodontist">Orthodontist</option>
									<option value="Pediatric Dentist">Pediatric Dentist</option>
									<option value="Periodontist">Periodontist</option>
									<option value="Prosthodontist">Prosthodontist</option>									
									<option value="Hygienist">Hygienist</option>
									<option value="Denturist">Denturist</option>
									<option value="General Dentist">General Dentist</option>
								</select> <br />
                                <label for="form_ac_s7ProviderPrimary">Provider primary</label>
								<select id="form_ac_s7ProviderPrimary" name="form_ac_s7ProviderPrimary">
									<option selected></option>
									<option value="Endodontist">Endodontist</option>
									<option value="Oral Surgeon">Oral Surgeon</option>
									<option value="Orthodontist">Orthodontist</option>
									<option value="Pediatric Dentist">Pediatric Dentist</option>
									<option value="Periodontist">Periodontist</option>
									<option value="Prosthodontist">Prosthodontist</option>									
									<option value="Hygienist">Hygienist</option>
									<option value="Denturist">Denturist</option>
									<option value="General Dentist">General Dentist</option>
								</select> <br />	
                                <label for="form_ac_s7ProviderPrimary_Other">Other</label>
								<select id="form_ac_s7ProviderPrimary_Other" name="form_ac_s7ProviderPrimary_Other">
									<option selected></option>
									<option value="Endodontist">Endodontist</option>
									<option value="Oral Surgeon">Oral Surgeon</option>
									<option value="Orthodontist">Orthodontist</option>
									<option value="Pediatric Dentist">Pediatric Dentist</option>
									<option value="Periodontist">Periodontist</option>
									<option value="Prosthodontist">Prosthodontist</option>									
									<option value="Hygienist">Hygienist</option>
									<option value="Denturist">Denturist</option>
									<option value="General Dentist">General Dentist</option>
								</select> <br />
                                <label for="form_ac_s7ProviderSecondary">Provider secondary specialty</label>
								<select id="form_ac_s7ProviderSecondary" name="form_ac_s7ProviderSecondary">
									<option selected></option>
									<option value="Endodontist">Endodontist</option>
									<option value="Oral Surgeon">Oral Surgeon</option>
									<option value="Orthodontist">Orthodontist</option>
									<option value="Pediatric Dentist">Pediatric Dentist</option>
									<option value="Periodontist">Periodontist</option>
									<option value="Prosthodontist">Prosthodontist</option>									
									<option value="Hygienist">Hygienist</option>
									<option value="Denturist">Denturist</option>
									<option value="General Dentist">General Dentist</option>
								</select> <br />																
								<label for="form_ac_s7ProviderSecondary_Other">Other</label>
								<select id="form_ac_s7ProviderSecondary_Other" name="form_ac_s7ProviderSecondary_Other">
									<option selected></option>
									<option value="Endodontist">Endodontist</option>
									<option value="Oral Surgeon">Oral Surgeon</option>
									<option value="Orthodontist">Orthodontist</option>
									<option value="Pediatric Dentist">Pediatric Dentist</option>
									<option value="Periodontist">Periodontist</option>
									<option value="Prosthodontist">Prosthodontist</option>									
									<option value="Hygienist">Hygienist</option>
									<option value="Denturist">Denturist</option>
									<option value="General Dentist">General Dentist</option>
								</select> <br />
							</fieldset>
							<fieldset>
							<label>Documentation Attached</label>
						    <input type="checkbox" id="form_ac_s7_attached" name="form_ac_s7_attached" value="Yes" style="width:auto" class="requireAttachment" /> <br />
						    <font color="#FF0000">Please attach a copy of your specialty certificate(American Board Certification or Educationally Qualified)</font><br />
						    </fieldset>		
							
						</div> <!-- END of form_tog4: the sixth Section Body -->
					</li> <!-- END of sixth Form Section -->

					
											
					<li id="form_slist8" class="pf_tab2 pf_section"> <!-- form_slist8 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle9" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Change your Tax Identification Number/IRS Name (TIN)</span>
						</h2>
						<div class="toggler"> 							
							<fieldset>
							<legend>Practice details</legend>
								<label for="form_ac_s8_newpracname">Updated/New practice name</label>
								<input type="text" id="form_ac_s8_newpracname" name="Updated_Practice_Name" />  <br />
								<label for="form_ac_s8_IRS">Updated/New IRS Name</label>
								<input type="text" id="form_ac_s8_IRS"  name="update_irs_name" class=""/>  <br />								
								<label for="form_ac_s8_prevTIN">Previous Tax Identification Number (TIN)<em>*</em></label>
								<input type="text" id="form_ac_s8_prevTIN" name="form_ac_s8_prevTIN" class="TIN requiredSection"/>  <br />
								<label for="form_ac_s8_newTIN">Updated/New practice Tax Identification Number (TIN)<em>*</em></label>
								<input type="text" id="form_ac_s8_newTIN" name="form_ac_s8_newTIN" class="TIN requiredSection"/>  <br />								
                                <label for="form_ac_s8_newOfficePhone">Practice office phone number</label>
                                <input type="text" id="form_ac_s8_newOfficePhone" name="form_ac_s8_newOfficePhone" class=""/>  <br />	
								<font color="#FF0000">*Please attach a W9 as it is required for this change.</font><br />								
							</fieldset>				
						</div> <!-- END of form_tog9: the Nineth Section Body -->	
					</li> <!-- END of Nineth Form Section -->

					<li id="form_slist9" class="pf_tab2 pf_section"> <!-- form_slist8 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle10" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Change in ownership for an existing practice</span>
						</h2>
						<div class="toggler"> 							
							<fieldset>
							<legend>Practice details</legend>
								<label for="form_ac_s9_newpracname">Updated/New practice name</label>
								<input type="text" id="form_ac_s9_newpracname" name="Updated_Practice_Name" />  <br />
								<label for="form_ac_s9_IRS">Updated/New IRS Name</label>
								<input type="text" id="form_ac_s9_IRS"  name="update_irs_name" class=""/>  <br />								
								<label for="form_ac_s9_prevTIN">Previous Tax Identification Number (TIN)<em>*</em></label>
								<input type="text" id="form_ac_s9_prevTIN" name="form_ac_s9_prevTIN" class="TIN requiredSection"/>  <br />
								<label for="form_ac_s9_newTIN">Updated/New practice Tax Identification Number (TIN)<em>*</em></label>
								<input type="text" id="form_ac_s9_newTIN" name="form_ac_s9_newTIN" class="TIN requiredSection"/>  <br />								
                                <label for="form_ac_s9_newOfficePhone">Practice office phone number<em>*</em></label>		
								<input type="text" id="form_ac_s9_newOfficePhone" name="form_ac_s9_newOfficePhone" class="requiredSection" />  <br />								
							</fieldset>	
							
							<fieldset>
							<legend>Previous Provider's Information</legend>
								<label for="form_ac_s9_provprev_last_name">Last name<em>*</em></label>
								<input type="text" id="form_ac_s9_provprev_last_name" name="form_ac_s9_provprev_last_name" class="requiredSection" />  <br />
								<label for="form_ac_s9_provprev_first_name">First name<em>*</em></label>
								<input type="text" id="form_ac_s9_provprev_first_name" name="form_ac_s9_provprev_first_name" class="requiredSection" />  <br />
								<label for="form_ac_s9_provprev_middle_name">Middle name<em></em></label>
								<input type="text" id="form_ac_s9_provprev_middle_name" name="form_ac_s9_provprev_middle_name"  />  <br />								
								<label for="form_ac_s9_provprev_Provider_License">License #<em>*</em></label>
								<input type="text" id="form_ac_s9_provprev_Provider_License" name="form_ac_s9_provprev_Provider_License" class="requiredSection"  /><br />		
							</fieldset>		
								
							<fieldset>
							<legend>New Providers Information</legend>
								<label for="form_ac_s9_prov_last_name">Last name<em>*</em></label>
								<input type="text" id="form_ac_s9_prov_last_name" name="form_ac_s9_prov_last_name" class="requiredSection" />  <br />
								<label for="form_ac_s9_prov_first_name">First name<em>*</em></label>
								<input type="text" id="form_ac_s9_prov_first_name" name="form_ac_s9_prov_first_name" class="requiredSection" />  <br />
								<label for="form_ac_s9_prov_middle_name">Middle name<em></em></label>
								<input type="text" id="form_ac_s9_prov_middle_name" name="form_ac_s9_prov_middle_name"  />  <br />								
								<label for="form_ac_s9Provider_License">License #<em>*</em></label>
								<input type="text" id="form_ac_s9Provider_License" name="form_ac_s9Provider_License" class="requiredSection"  /><br />		
							</fieldset>	
							<fieldset>
							<label>Documentation Attached</label>
						    <input type="checkbox" id="form_ac_s9_attached" name="form_ac_s9_attached" value="Yes" style="width:auto" class="requireAttachment" /> <br />
						    <font color="#FF0000">*Please attach a W9 as it is required for this change. These changes may require additional documentation and you will be contacted by a Network Representative.</font><br />
						    </fieldset>		
															
						</div> <!-- END of form_tog9: the Nineth Section Body -->	
					</li> <!-- END of Nineth Form Section -->

					<li id="form_slist10" class="pf_tab2 pf_section"> <!-- form_slist8 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle11" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Change your practice address or phone/fax number</span>
						</h2>
						<div class="toggler"> 							
							
							<fieldset>
							<legend>Previous/Existing practice office</legend>
								<label for="form_ac_s10_prePractAddrS1">Practice Address line 1<em>*</em></label>
								<input type="text" id="form_ac_s10_prePractAddrS1" name="form_ac_s10_prePractAddrS1" class="requiredSection" />  <br />
								<label for="form_ac_s10_prePractAddrS2">Practice Address line 2</label>
								<input type="text" id="form_ac_s10_prePractAddrS2" name="Address_Street2" />  <br />
								<label for="form_ac_s10_prePractCity">City<em>*</em></label>
								<input type="text" id="form_ac_s10_prePractCity" name="form_ac_s10_prePractCity" class="requiredSection" />  <br />
								<label for="form_ac_s10_prePractState">State<em>*</em></label>
								<input type="text" id="form_ac_s10_prePractState" name="form_ac_s10_prePractState" class="requiredSection" />  <br />
								<label for="form_ac_s10_prePractZip">Zip</label>
								<input type="text" id="form_ac_s10_prePractZip" name="Zip" />  <br />
                                <label for="form_ac_s10_prePractPhone">Practice office phone number</label>
								<input type="text" id="form_ac_s10_prePractPhone" name="Phone" />  <br />
                                <label for="form_ac_s10_prePractfax">Practice office fax number</label>
								<input type="text" id="form_ac_s10_prePractfax" name="Fax" />  <br />
							</fieldset>		
								
							<fieldset>
							<legend>Updated/New practice office</legend>
								<label for="form_ac_s10_PractAddrS1">Practice Address line 1<em>*</em></label>
								<input type="text" id="form_ac_s10_PractAddrS1" name="form_ac_s10_PractAddrS1" class="requiredSection" />  <br />
								<label for="form_ac_s10_PractAddrS2">Practice Address line 2</label>
								<input type="text" id="form_ac_s10_PractAddrS2" name="Address_Street2" />  <br />
								<label for="form_ac_s10_PractCity">City<em>*</em></label>
								<input type="text" id="form_ac_s10_PractCity" name="form_ac_s10_PractCity" class="requiredSection" />  <br />
								<label for="form_ac_s10_PractState">State<em>*</em></label>
								<input type="text" id="form_ac_s10_PractState" name="form_ac_s10_PractState" class="requiredSection" />  <br />
								<label for="form_ac_s10_PractZip">Zip<em>*</em></label>
								<input type="text" id="form_ac_s10_PractZip" name="Zip" class="requiredSection" />  <br />
                                <label for="form_ac_s10_PractPhone">Practice office phone number</label>
								<input type="text" id="form_ac_s10_PractPhone" name="Phone" />  <br />
                                <label for="form_ac_s10_Practfax">Practice office fax number</label>
								<input type="text" id="form_ac_s10_Practfax" name="Fax" />  <br />
						        <label for="form_ac_s10_PractPlanEmail">Practice office email for plan to correspond with you</label>
								<input type="text" id="form_ac_s10_PractPlanEmail" name="form_ac_s10_PractPlanEmail" class="email" />  <br />
								<label for="form_ac_s10_PractPatCorresEmail">Practice office email for Patients to correspond with you    </label>
								<input type="text" id="form_ac_s10_PractPatCorresEmail" name="form_ac_s10_PractPatCorresEmail" class="email"  />  <br />

                				<label for="form_ac_s10_pract_pat_hand_access">Does this office provide handicapped accessibility? <em>*</em></label>
                				<select id="form_ac_s10_pract_pat_hand_access" name="form_ac_s10_pract_pat_hand_access" class="requiredSection" >
                                	<option value="" selected></option>
                                	<option value="Yes">Yes</option>
                                	<option value="No">No</option>
                				</select> <br />      

                				<label for="form_ac_s10_pract_pat_wheelchair_access">Does this office have wheelchair accessibility? <em>*</em> </label>
                				<select id="form_ac_s10_pract_pat_wheelchair_access" name="form_ac_s10_pract_pat_wheelchair_access" class="requiredSection" >
                                	<option value="" selected></option>
                                	<option value="Yes">Yes</option>
                                	<option value="No">No</option>
                				</select> <br />                                  
																
							</fieldset>																	
						</div> <!-- END of form_tog9: the Nineth Section Body -->	
					</li> <!-- END of Nineth Form Section -->
					
					
					<li id="form_slist11" class="pf_tab2 pf_section"> <!-- form_slist8 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle12" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Change your practice name</span>
						</h2>
						<div class="toggler"> 							
							
							<fieldset>
							<legend>Practice details</legend>
                                <label for="form_ac_s11_change_pract_name">Updated/New practice name</label>
								<input type="text" id="form_ac_s11_change_pract_name" name="form_ac_s11_change_pract_name" />  <br />							
								<label for="form_ac_s11_change_pract_addrs1">Practice Address line 1<em>*</em></label>
								<input type="text" id="form_ac_s11_change_pract_addrs1" name="form_ac_s11_change_pract_addrs1" class="requiredSection" />  <br />
								<label for="form_ac_s11_change_pract_addrs2">Practice Address line 2</label>
								<input type="text" id="form_ac_s11_change_pract_addrs2" name="Address_Street2" />  <br />
								<label for="form_ac_s11_change_pract_city">City<em>*</em></label>
								<input type="text" id="form_ac_s11_change_pract_city" name="form_ac_s11_change_pract_city" class="requiredSection" />  <br />
								<label for="form_ac_s11_change_pract_state">State<em>*</em></label>
								<input type="text" id="form_ac_s11_change_pract_state" name="form_ac_s11_change_pract_state" class="requiredSection" />  <br />
								<label for="form_ac_s11_change_pract_zip">Zip <em>*</em></label>
								<input type="text" id="form_ac_s11_change_pract_zip" name="form_ac_s11_change_pract_zip" class="requiredSection" />  <br />  					
							</fieldset>		

						</div> <!-- END of form_tog9: the Nineth Section Body -->	
					</li> <!-- END of Nineth Form Section -->
					

					<li id="form_slist12" class="pf_tab2 pf_section"> <!-- form_slist8 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle12" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Add a new location</span>
						</h2>
						<div class="toggler"> 							
							
							<fieldset>
							<legend>New practice office</legend>
								<label for="form_ac_s12_PractAddrS1">Practice Address line 1<em>*</em></label>
								<input type="text" id="form_ac_s12_PractAddrS1" name="form_ac_s12_PractAddrS1" class="requiredSection" />  <br />
								<label for="form_ac_s12_PractAddrS2">Practice Address line 2</label>
								<input type="text" id="form_ac_s12_PractAddrS2" name="Address_Street2" />  <br />
								<label for="form_ac_s12_PractCity">City<em>*</em></label>
								<input type="text" id="form_ac_s12_PractCity" name="form_ac_s12_PractCity" class="requiredSection" />  <br />
								<label for="form_ac_s12_PractState">State<em>*</em></label>
								<input type="text" id="form_ac_s12_PractState" name="form_ac_s12_PractState" class="requiredSection" />  <br />
								<label for="form_ac_s12_PractZip">Zip<em>*</em></label>
								<input type="text" id="form_ac_s12_PractZip" name="form_ac_s12_PractZip" class="requiredSection" />  <br />
						        <label for="form_ac_s12_PractPlanEmail">Practice office email for plan to correspond with you</label>
								<input type="text" id="form_ac_s12_PractPlanEmail" name="form_ac_s12_PractPlanEmail" class="email" />  <br />
								<label for="form_ac_s12_PractPatCorresEmail">Practice office email for Patients to correspond with you    </label>
								<input type="text" id="form_ac_s12_PractPatCorresEmail" name="form_ac_s12_PractPatCorresEmail" class="email"  />  <br />								
                                <label for="form_ac_s12_prePractPhone">Practice office phone number<em>*</em></label>
								<input type="text" id="form_ac_s12_prePractPhone" name="form_ac_s12_prePractPhone" class="requiredSection" />  <br />
                                <label for="form_ac_s12_prePractfax">Practice office fax number</label>
								<input type="text" id="form_ac_s12_prePractfax" name="Fax" />  <br />
								<label for="form_ac_s12_lang_spok_inter">Language spoken by office staff by your qualified medical interpreter</label>
								<select id="form_ac_s12_lang_spok_inter" name="form_ac_s12_lang_spok_inter">
									<option selected></option>
									<option value="AFGHAN">AFGHAN</option>
									<option value="AFRIKAANS">AFRIKAANS</option>
									<option value="ALBANIAN">ALBANIAN</option>
									<option value="AMHARIC">AMHARIC</option>
									<option value="ARABIC">ARABIC</option>
									<option value="ARAMAIC">ARAMAIC</option>
									<option value="ARMENIAN">ARMENIAN</option>
									<option value="ASSAMESE">ASSAMESE</option>
									<option value="ASSYRIAN">ASSYRIAN</option>
									<option value="AZERBAIJANI">AZERBAIJANI</option>
									<option value="BANGLADESHI">BANGLADESHI</option>
									<option value="BEMBA">BEMBA</option>
									<option value="BENGALI">BENGALI</option>
									<option value="BHOJPURI">BHOJPURI</option>
									<option value="BIKOL">BIKOL</option>
									<option value="BOSNIAN">BOSNIAN</option>
									<option value="BULGARIAN">BULGARIAN</option>
									<option value="BURMESE">BURMESE</option>
									<option value="CALDEON">CALDEON</option>
									<option value="CAMBODIAN">CAMBODIAN</option>
									<option value="CANTONESE">CANTONESE</option>
									<option value="CATALAN">CATALAN</option>
									<option value="CEBUANO">CEBUANO</option>
									<option value="CHICHEWA">CHICHEWA</option>
									<option value="CHINESE">CHINESE</option>
									<option value="CREOLE">CREOLE</option>
									<option value="CROATIAN">CROATIAN</option>
									<option value="CZECH">CZECH</option>
									<option value="DANISH">DANISH</option>
									<option value="DUTCH">DUTCH</option>
									<option value="EGYPTIAN">EGYPTIAN</option>
									<option value="ENGLISH">ENGLISH</option>
									<option value="FAROESE">FAROESE</option>
									<option value="FARSI">FARSI</option>
									<option value="FILIPINO">FILIPINO</option>
									<option value="FINNISH">FINNISH</option>
									<option value="FLEMISH">FLEMISH</option>
									<option value="FOOKIEN">FOOKIEN</option>
									<option value="FRENCH">FRENCH</option>
									<option value="GEORGIAN">GEORGIAN</option>
									<option value="GERMAN">GERMAN</option>
									<option value="GREEK">GREEK</option>
									<option value="GUJARATI">GUJARATI</option>
									<option value="HAITIAN">HAITIAN</option>
									<option value="HAITIAN CREOLE">HAITIAN CREOLE</option>
									<option value="HAUSA">HAUSA</option>
									<option value="HAWAIIAN">HAWAIIAN</option>
									<option value="HEBREW">HEBREW</option>
									<option value="HINDI">HINDI</option>
									<option value="HMONG">HMONG</option>
									<option value="HUNGARIAN">HUNGARIAN</option>
									<option value="IBU">IBU</option>
									<option value="ICELANDIC">ICELANDIC</option>
									<option value="IGBO">IGBO</option>
									<option value="ILOCANO">ILOCANO</option>
									<option value="INDONESIAN">INDONESIAN</option>
									<option value="IRANIAN">IRANIAN</option>
									<option value="ITALIAN">ITALIAN</option>
									<option value="JAPANESE">JAPANESE</option>
									<option value="KANNADA">KANNADA</option>
									<option value="KAPAMPANGAN">KAPAMPANGAN</option>
									<option value="KASHMIRI">KASHMIRI</option>
									<option value="KHMER">KHMER</option>
									<option value="KONKANI">KONKANI</option>
									<option value="KOREAN">KOREAN</option>
									<option value="KURDISH">KURDISH</option>
									<option value="LAOTIAN">LAOTIAN</option>
									<option value="LATIN">LATIN</option>
									<option value="LATVIAN">LATVIAN</option>
									<option value="LEBANESE">LEBANESE</option>
									<option value="LITHUNIAN">LITHUNIAN</option>
									<option value="MACEDONIAN">MACEDONIAN</option>
									<option value="MADAGASHI">MADAGASHI</option>
									<option value="MAITHILI">MAITHILI</option>
									<option value="MALAYALAM">MALAYALAM</option>
									<option value="MALAYSIAN">MALAYSIAN</option>
									<option value="MANDARIN">MANDARIN</option>
									<option value="MARATHI">MARATHI</option>
									<option value="MOHAWIK">MOHAWIK</option>
									<option value="MONGOLIAN">MONGOLIAN</option>
									<option value="NAVAJO">NAVAJO</option>
									<option value="NEPALI">NEPALI</option>
									<option value="NORWEGIAN">NORWEGIAN</option>
									<option value="OJIBWE">OJIBWE</option>
									<option value="ORADAU">ORADAU</option>
									<option value="PAMPANGA">PAMPANGA</option>
									<option value="PERSIAN">PERSIAN</option>
									<option value="POLISH">POLISH</option>
									<option value="PORTUGUESE">PORTUGUESE</option>
									<option value="PUNJABI">PUNJABI</option>
									<option value="ROMANIAN">ROMANIAN</option>
									<option value="RUSSIAN">RUSSIAN</option>
									<option value="SAMOAN">SAMOAN</option>
									<option value="SANSKRIT">SANSKRIT</option>
									<option value="SERBIAN">SERBIAN</option>
									<option value="SERBOCROATIAN">SERBOCROATIAN</option>
									<option value="SHONA">SHONA</option>
									<option value="SIGN">SIGN</option>
									<option value="SINDHI">SINDHI</option>
									<option value="SINHALESE">SINHALESE</option>
									<option value="SLOVAK">SLOVAK</option>
									<option value="SOMALI">SOMALI</option>
									<option value="SPANISH">SPANISH</option>
									<option value="SWAHILI">SWAHILI</option>
									<option value="SWEDISH">SWEDISH</option>
									<option value="SWISS">SWISS</option>
									<option value="SYRIAN">SYRIAN</option>
									<option value="TAGALOG">TAGALOG</option>
									<option value="TAIWANESE">TAIWANESE</option>
									<option value="TAMIL">TAMIL</option>
									<option value="TELUGU">TELUGU</option>
									<option value="THAI">THAI</option>
									<option value="TIGRIYINI">TIGRIYINI</option>
									<option value="TURKISH">TURKISH</option>
									<option value="TWI">TWI</option>
									<option value="UKRAINIAN">UKRAINIAN</option>
									<option value="URDU">URDU</option>
									<option value="UZBEK">UZBEK</option>
									<option value="VIETNAMESE">VIETNAMESE</option>
									<option value="VISAYAN">VISAYAN</option>
									<option value="YIDDISH">YIDDISH</option>
									<option value="YORUBA">YORUBA</option>
									<option value="ZHUANG">ZHUANG</option>
									<option value="ZULU">ZULU</option>
								</select> <br />
								
                				<label for="form_ac_s12_pract_pat_hand_access">Does this office provide handicapped accessibility?<em>*</em> </label>
                				<select id="form_ac_s12_pract_pat_hand_access" name="form_ac_s10_pract_pat_hand_access" class="requiredSection" >
                                	<option value="" selected></option>
                                	<option value="Yes">Yes</option>
                                	<option value="No">No</option>
                				</select> <br />       

                				<label for="form_ac_s12_pract_pat_wheelchair_access">Does this office have wheelchair accessibility?<em>*</em>  </label>
                				<select id="form_ac_s12_pract_pat_wheelchair_access" name="form_ac_s10_pract_pat_wheelchair_access" class="requiredSection" >
                                	<option value="" selected></option>
                                	<option value="Yes">Yes</option>
                                	<option value="No">No</option>
                				</select> <br />      								
								
							</fieldset>		
							
							<fieldset>
							<legend>List all providers that will practice at this location</legend>
								<font color="#FF0000"> *If there are more than 3 providers that need to be added to this location, please attach a separate location list with the following details : Providers first name, last name, license # and state of license. These changes may require additional documentation and you will be contacted by a Network Representative.
								</font><br /><br/>
								<font color="#FF0000">Complete the below for each provider:</font><br />
								
								<fieldset>
								<legend id="showInMail" >Provider 1</legend>
									<label for="form_ac_s12_lastName">Last name</label>
									<input type="text" id="form_ac_s12_lastName" name="form_ac_s12_lastName" />  <br />
									<label for="form_ac_s12_firstName">First name</label>
									<input type="text" id="form_ac_s12_firstName" name="form_ac_s12_firstName" />  <br />
									<label for="form_ac_s12_middleName">Middle name</label>
									<input type="text" id="form_ac_s12_middleName" name="form_ac_s12_middleName"  />  <br />
									<label for="form_ac_s12_license">License #</label>
									<input type="text" id="form_ac_s12_license" name="form_ac_s12_license" />  <br />
									<label for="form_ac_s12_license_state">License State</label>
									<input type="text" id="form_ac_s12_license_state" name="form_ac_s12_license_state" />  <br />									
								</fieldset>	

								<fieldset>
								<legend id="showInMail" >Provider 2</legend>
									<label for="form_ac_s12_1_lastName">Last name</label>
									<input type="text" id="form_ac_s12_1_lastName" name="form_ac_s12_1_lastName" />  <br />
									<label for="form_ac_s12_1_firstName">First name</label>
									<input type="text" id="form_ac_s12_1_firstName" name="form_ac_s12_1_firstName" />  <br />
									<label for="form_ac_s12_1_middleName">Middle name</label>
									<input type="text" id="form_ac_s12_1_middleName" name="form_ac_s12_1_middleName"  />  <br />
									<label for="form_ac_s12_1_license">License #</label>
									<input type="text" id="form_ac_s12_1_license" name="form_ac_s12_1_license" />  <br />
									<label for="form_ac_s12_1_license_state">License State</label>
									<input type="text" id="form_ac_s12_1_license_state" name="form_ac_s12_1_license_state" />  <br />																		
								</fieldset>	

								<fieldset>
								<legend id="showInMail" >Provider 3</legend>
									<label for="form_ac_s12_2_lastName">Last name</label>
									<input type="text" id="form_ac_s12_2_lastName" name="form_ac_s12_2_lastName" />  <br />
									<label for="form_ac_s12_2_firstName">First name</label>
									<input type="text" id="form_ac_s12_2_firstName" name="form_ac_s12_2_firstName" />  <br />
									<label for="form_ac_s12_2_middleName">Middle name</label>
									<input type="text" id="form_ac_s12_2_middleName" name="form_ac_s12_2_middleName"  />  <br />
									<label for="form_ac_s12_2_license">License #</label>
									<input type="text" id="form_ac_s12_2_license" name="form_ac_s12_2_license" />  <br />
									<label for="form_ac_s12_2_license_state">License State</label>
									<input type="text" id="form_ac_s12_2_license_state" name="form_ac_s12_2_license_state" />  <br />																											
								</fieldset>	
																							
							</fieldset>			

							<fieldset>
                            	<legend>Office Hours</legend>
									<label for="form_ac_s12_monSTime">Monday start time (hr:min)</label>
									<input type="text" id="form_ac_s12_monSTime" class="pf_cWidthTime time" name="Monday_Start_Time" />  <br />
									<label for="form_ac_s12_monETime">Monday end time (hr:min)</label>
									<input type="text" id="form_ac_s12_monETime" class="pf_cWidthTime time" name="Monday_End_Time" />  <br />
									<label for="form_ac_s12_tueSTime">Tuesday start time (hr:min)</label>
									<input type="text" id="form_ac_s12_tueSTime" class="pf_cWidthTime time" name="Tuesday_Start_Time" />  <br />
									<label for="form_ac_s12_tueETime">Tuesday end time (hr:min)</label>
									<input type="text" id="form_ac_s12_tueETime" class="pf_cWidthTime time" name="Tuesday_End_Time" />  <br />
									<label for="form_ac_s12_wedSTime">Wednesday start time (hr:min)</label>
									<input type="text" id="form_ac_s12_wedSTime" class="pf_cWidthTime time" name="Wednesday_Start_Time" />  <br />
									<label for="form_ac_s12_wedETime">Wednesday end time (hr:min)</label>
									<input type="text" id="form_ac_s12_wedETime" class="pf_cWidthTime time" name="Wednesday_End_Time" />  <br />
									<label for="form_ac_s12_thuSTime">Thursday start time (hr:min)</label>
									<input type="text" id="form_ac_s12_thuSTime" class="pf_cWidthTime time" name="Thursday_Start_Time" />  <br />
									<label for="form_ac_s12_thuETime">Thursday end time (hr:min)</label>
									<input type="text" id="form_ac_s12_thuETime" class="pf_cWidthTime time" name="Thursday_End_Time" />  <br />
									<label for="form_ac_s12_friSTime">Friday start time (hr:min)</label>
									<input type="text" id="form_ac_s12_friSTime" class="pf_cWidthTime time" name="Friday_Start_Time" />  <br />
									<label for="form_ac_s12_friETime">Friday end time (hr:min)</label>
									<input type="text" id="form_ac_s12_friETime" class="pf_cWidthTime time" name="Friday_End_Time" />  <br />
									<label for="form_ac_s12_satSTime">Saturday start time (hr:min)</label>
									<input type="text" id="form_ac_s12_satSTime" class="pf_cWidthTime time" name="Saturday_Start_Time" />  <br />
									<label for="form_ac_s12_satETime">Saturday end time (hr:min)</label>
									<input type="text" id="form_ac_s12_satETime" class="pf_cWidthTime time" name="Saturday_End_Time" />  <br />
									<label for="form_ac_s12_sunSTime">Sunday start time (hr:min)</label>
									<input type="text" id="form_ac_s12_sunSTime" class="pf_cWidthTime time" name="Sunday_Start_Time" />  <br />
									<label for="form_ac_s12_sunETime">Sunday end time (hr:min)</label>
									<input type="text" id="form_ac_s12_sunETime" class="pf_cWidthTime time" name="Sunday_End_Time" />  <br />
							</fieldset>
							
								
							<fieldset>
							<legend>Billing Address</legend>
								<label for="form_ac_s12_billAddr1">Address line 1</label>
								<input type="text" id="form_ac_s12_billAddr1" name="Address_Street1"  />  <br />
								<label for="form_ac_s12_billAddr2">Address line 2</label>
								<input type="text" id="form_ac_s12_billAddr2" name="Address_Street2" />  <br />
								<label for="form_ac_s12_billCity">City</label>
								<input type="text" id="form_ac_s12_billCity" name="City"  />  <br />
								<label for="form_ac_s12_billState">State</label>
								<input type="text" id="form_ac_s12_billState" name="State" />  <br />
								<label for="form_ac_s12_billZip">Zip</label>
								<input type="text" id="form_ac_s12_billZip" name="Zip" />  <br />
							</fieldset>																	
							
							<fieldset>
							<legend>Correspondence Address</legend>
								<label for="form_ac_s12_corrAddr1">Address line 1</label>
								<input type="text" id="form_ac_s12_corrAddr1" name="Address_Street1"  />  <br />
								<label for="form_ac_s12_corrAddr2">Address line 2</label>
								<input type="text" id="form_ac_s12_corrAddr2" name="Address_Street2" />  <br />
								<label for="form_ac_s12_corrCity">City</label>
								<input type="text" id="form_ac_s12_corrCity" name="City"  />  <br />
								<label for="form_ac_s12_corrState">State</label>
								<input type="text" id="form_ac_s12_corrState" name="State" />  <br />
								<label for="form_ac_s12_corrZip">Zip</label>
								<input type="text" id="form_ac_s12_corrZip" name="Zip" />  <br />
							</fieldset>																	
							
						</div> <!-- END of form_tog9: the Nineth Section Body -->	
					</li> <!-- END of Nineth Form Section -->
					
					<li id="form_slist13" class="pf_tab2 pf_section"> <!-- form_slist2 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle14" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Close a practice location</span>
						</h2>
						<div class="toggler"> <!-- Start of the FORM elements for one Section -->
							
							<fieldset>
							<label for="s13_tReason">Reason for closing location</label>
							<input type="text" id="s13_tReason" name="s13_tReason" />  <br />
                            </fieldset>
							<fieldset>
							<legend>Practice office Information</legend>
								<label for="form_ac_s13_preAddrs1">Practice Address line 1<em>*</em></label>
								<input type="text" id="form_ac_s13_preAddrs1" name="Address_Street1" class="requiredSection" />  <br />
								<label for="form_ac_s13_preAddrS2">Practice Address line 2</label>
								<input type="text" id="form_ac_s13_preAddrS2" name="Address_Street2" />  <br />
								<label for="form_ac_s13_preCity">City<em>*</em></label>
								<input type="text" id="form_ac_s13_preCity" name="form_ac_s13_preCity" class="requiredSection" />  <br />
								<label for="form_ac_s13_preState">State<em>*</em></label>
								<input type="text" id="form_ac_s13_preState" name="form_ac_s13_preState" class="requiredSection" />  <br />
								<label for="form_ac_s13_preZip">Zip<em>*</em></label>
								<input type="text" id="form_ac_s13_preZip" name="form_ac_s13_preZip" class="requiredSection" />  <br />
							</fieldset>
						</div> <!-- END of form_tog3: the Third Section Body -->
					</li> <!-- END of Third Form Section -->
					
					
					<li id="form_slist14" class="pf_tab2 pf_section"> <!-- form_slist2 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle15" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Change your billing or correspondence address</span>
						</h2>
						<div class="toggler"> <!-- Start of the FORM elements for one Section -->
							
							<fieldset>
							<legend>Existing Practice office Information</legend>
								<label for="form_ac_s14_preAddrs1">Practice Address line 1<em>*</em></label>
								<input type="text" id="form_ac_s14_preAddrs1" name="form_ac_s14_preAddrs1" class="requiredSection" />  <br />
								<label for="form_ac_s14_preAddrS2">Practice Address line 2</label>
								<input type="text" id="form_ac_s14_preAddrS2" name="Address_Street2" />  <br />
								<label for="form_ac_s14_preCity">City<em>*</em></label>
								<input type="text" id="form_ac_s14_preCity" name="form_ac_s14_preCity" class="requiredSection" />  <br />
								<label for="form_ac_s14_preState">State<em>*</em></label>
								<input type="text" id="form_ac_s14_preState" name="form_ac_s14_preState" class="requiredSection" />  <br />
								<label for="form_ac_s14_preZip">Zip<em>*</em></label>
								<input type="text" id="form_ac_s14_preZip" name="form_ac_s14_preZip" class="requiredSection" />  <br />
								<font color="#FF0000">*Billing and correspondence address will default to the practice office address if the below addresses are left blank.</font><br />
							</fieldset>
							
							<fieldset>
							<legend>Updated/New billing Address</legend>
								<label for="form_ac_s14_Addrs1">Address line 1</label>
								<input type="text" id="form_ac_s14_Addrs1" name="Address_Street1" />  <br />
								<label for="form_ac_s14_AddrS2">Address line 2</label>
								<input type="text" id="form_ac_s14_AddrS2" name="Address_Street2" />  <br />
								<label for="form_ac_s14_City">City</label>
								<input type="text" id="form_ac_s14_City" name="City"  />  <br />
								<label for="form_ac_s14_State">State</label>
								<input type="text" id="form_ac_s14_State" name="State"  />  <br />
								<label for="form_ac_s14_Zip">Zip</label>
								<input type="text" id="form_ac_s14_Zip" name="Zip" />  <br />
							</fieldset>
	
							<fieldset>
							<legend>Updated/New Correspondence Address</legend>
								<label for="form_ac_s14_corrAddrs1">Address line 1</label>
								<input type="text" id="form_ac_s14_corrAddrs1" name="Address_Street1" />  <br />
								<label for="form_ac_s14_corrAddrS2">Address line 2</label>
								<input type="text" id="form_ac_s14_corrAddrS2" name="Address_Street2" />  <br />
								<label for="form_ac_s14_corrCity">City</label>
								<input type="text" id="form_ac_s14_corrCity" name="City"  />  <br />
								<label for="form_ac_s14_corrState">State</label>
								<input type="text" id="form_ac_s14_corrState" name="State"  />  <br />
								<label for="form_ac_s14_corrZip">Zip</label>
								<input type="text" id="form_ac_s14_corrZip" name="Zip" />  <br />
							</fieldset>

							
						</div> <!-- END of form_tog3: the Third Section Body -->
					</li> <!-- END of Third Form Section -->
					
					<li id="form_slist15" class="pf_tab2 pf_section"> <!-- form_slist2 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle16" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Change your office hours or days of operation</span>
						</h2>
						<div class="toggler"> <!-- Start of the FORM elements for one Section -->

							<fieldset>
                            	<legend>Office Hours</legend>
									<label for="form_ac_s12_monSTime">Monday start time (hr:min)</label>
									<input type="text" id="form_ac_s12_monSTime" class="pf_cWidthTime time" name="Monday_Start_Time" />  <br />
									<label for="form_ac_s12_monETime">Monday end time (hr:min)</label>
									<input type="text" id="form_ac_s12_monETime" class="pf_cWidthTime time" name="Monday_End_Time" />  <br />
									<label for="form_ac_s12_tueSTime">Tuesday start time (hr:min)</label>
									<input type="text" id="form_ac_s12_tueSTime" class="pf_cWidthTime time" name="Tuesday_Start_Time" />  <br />
									<label for="form_ac_s12_tueETime">Tuesday end time (hr:min)</label>
									<input type="text" id="form_ac_s12_tueETime" class="pf_cWidthTime time" name="Tuesday_End_Time" />  <br />
									<label for="form_ac_s12_wedSTime">Wednesday start time (hr:min)</label>
									<input type="text" id="form_ac_s12_wedSTime" class="pf_cWidthTime time" name="Wednesday_Start_Time" />  <br />
									<label for="form_ac_s12_wedETime">Wednesday end time (hr:min)</label>
									<input type="text" id="form_ac_s12_wedETime" class="pf_cWidthTime time" name="Wednesday_End_Time" />  <br />
									<label for="form_ac_s12_thuSTime">Thursday start time (hr:min)</label>
									<input type="text" id="form_ac_s12_thuSTime" class="pf_cWidthTime time" name="Thursday_Start_Time" />  <br />
									<label for="form_ac_s12_thuETime">Thursday end time (hr:min)</label>
									<input type="text" id="form_ac_s12_thuETime" class="pf_cWidthTime time" name="Thursday_End_Time" />  <br />
									<label for="form_ac_s12_friSTime">Friday start time (hr:min)</label>
									<input type="text" id="form_ac_s12_friSTime" class="pf_cWidthTime time" name="Friday_Start_Time" />  <br />
									<label for="form_ac_s12_friETime">Friday end time (hr:min)</label>
									<input type="text" id="form_ac_s12_friETime" class="pf_cWidthTime time" name="Friday_End_Time" />  <br />
									<label for="form_ac_s12_satSTime">Saturday start time (hr:min)</label>
									<input type="text" id="form_ac_s12_satSTime" class="pf_cWidthTime time" name="Saturday_Start_Time" />  <br />
									<label for="form_ac_s12_satETime">Saturday end time (hr:min)</label>
									<input type="text" id="form_ac_s12_satETime" class="pf_cWidthTime time" name="Saturday_End_Time" />  <br />
									<label for="form_ac_s12_sunSTime">Sunday start time (hr:min)</label>
									<input type="text" id="form_ac_s12_sunSTime" class="pf_cWidthTime time" name="Sunday_Start_Time" />  <br />
									<label for="form_ac_s12_sunETime">Sunday end time (hr:min)</label>
									<input type="text" id="form_ac_s12_sunETime" class="pf_cWidthTime time" name="Sunday_End_Time" />  <br />
							</fieldset>						
							
						</div> <!-- END of form_tog3: the Third Section Body -->
					</li> <!-- END of Third Form Section -->

					<li id="form_slist16" class="pf_tab2 pf_section"> <!-- form_slist2 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle17" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Change in your acceptance of new patients</span>
						</h2>
						<div class="toggler"> <!-- Start of the FORM elements for one Section -->
							
							<fieldset>
							<legend>Practice office Information</legend>
								<label for="form_ac_s16_practAddrs1">Practice Address line 1<em>*</em></label>
								<input type="text" id="form_ac_s16_practAddrs1" name="form_ac_s16_practAddrs1" class="requiredSection" />  <br />
								<label for="form_ac_s16_practAddrs2">Practice Address line 2</label>
								<input type="text" id="form_ac_s16_practAddrs2" name="Address_Street2" />  <br />
								<label for="form_ac_s16_practCity">City<em>*</em></label>
								<input type="text" id="form_ac_s16_practCity" name="form_ac_s16_practCity" class="requiredSection" />  <br />
								<label for="form_ac_s16_practState">State<em>*</em></label>
								<input type="text" id="form_ac_s16_practState" name="form_ac_s16_practState" class="requiredSection" />  <br />
								<label for="form_ac_s16_practZip">Zip<em>*</em></label>
								<input type="text" id="form_ac_s16_practZip" name="form_ac_s16_practZip" class="requiredSection" />  <br />
                				<label for="form_ac_s16_closing_newpat">Are you closing to new patients?</label>
                				<select id="form_ac_s16_closing_newpat" name="form_ac_s16_closing_newpat">
                                	<option selected></option>
                                	<option value="Yes">Yes</option>
                                	<option value="No">No</option>
                				</select> <br />    
								<label for="form_ac_s16_reopen_newpat">Are you re-opening to new patients? </label>
                				<select id="form_ac_s16_reopen_newpat" name="form_ac_s16_reopen_newpat">
                                	<option selected></option>
                                	<option value="Yes">Yes</option>
                                	<option value="No">No</option>
                				</select> <br />      
							</fieldset>
														
						</div> <!-- END of form_tog3: the Third Section Body -->
					</li> <!-- END of Third Form Section -->

					<li id="form_slist17" class="pf_tab2 pf_section"> <!-- form_slist2 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle18" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Update or add your Organizational Clinic or Corporate NPI</span>
						</h2>
						<div class="toggler"> <!-- Start of the FORM elements for one Section -->
							
							<fieldset>
							<legend>Organizational Details</legend>
								<label>Organizational Clinic National Provider Identifier (NPI)</label>
								<input type="text" id="org_clinic_NPI" name="org_clinic_NPI" class="NPI" /><br />
								<font color="#FF0000"> *This NPI will be applied to the TIN identified with your general information and the practice location identified below.</font>
							</fieldset>
							
							<fieldset>
							<legend>Practice office Information</legend>
								<label for="form_ac_s17_practAddrs1">Practice Address line 1<em>*</em></label>
								<input type="text" id="form_ac_s17_practAddrs1" name="form_ac_s17_practAddrs1" class="requiredSection" />  <br />
								<label for="form_ac_s17_practAddrs2">Practice Address line 2</label>
								<input type="text" id="form_ac_s17_practAddrs2" name="Address_Street2" />  <br />
								<label for="form_ac_s17_practCity">City<em>*</em></label>
								<input type="text" id="form_ac_s17_practCity" name="form_ac_s17_practCity" class="requiredSection" />  <br />
								<label for="form_ac_s17_practState">State<em>*</em></label>
								<input type="text" id="form_ac_s17_practState" name="form_ac_s17_practState" class="requiredSection" />  <br />
								<label for="form_ac_s17_practCounty">County<em>*</em></label>
								<input type="text" id="form_ac_s17_practCounty" name="form_ac_s17_practCounty" class="requiredSection" />  <br />																
								<label for="form_ac_s17_practZip">Zip<em>*</em></label>
								<input type="text" id="form_ac_s17_practZip" name="form_ac_s17_practZip" class="requiredSection" />  <br />
								<label>Updated or Added Organizational Corporate National Provider Identifier (NPI)</label>
								<input type="text" id="updated_org_corp_NPI" name="updated_org_corp_NPI" class="NPI" /><br />
								<font color="#FF0000">*This NPI will be applied to all practice locations under the TIN identified below.</font><br>
								<label>TIN<em>*</em></label><input type="text" id="practice_office_TIN" name="practice_office_TIN" class="requiredSection TIN"/><br />
							</fieldset>
														
						</div> <!-- END of form_tog3: the Third Section Body -->
					</li> <!-- END of Third Form Section -->

					<li id="form_slist18" class="pf_tab2 pf_section"> <!-- form_slist2 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle19" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Update or add your email address(es)</span>
						</h2>
						<div class="toggler"> <!-- Start of the FORM elements for one Section -->
							
							<fieldset>
							<legend>Primary email address</legend>
						        <label for="form_ac_s18_PractPlanEmail">Practice office email for plan to correspond with you</label>
								<input type="text" id="form_ac_s18_PractPlanEmail" name="form_ac_s18_PractPlanEmail" class="email" />  <br />
								<label for="form_ac_s18_PractPatCorresEmail">Practice office email for Patients to correspond with you    </label>
								<input type="text" id="form_ac_s18_PractPatCorresEmail" name="form_ac_s18_PractPatCorresEmail" class="email" />  <br />
							</fieldset>
														
						</div> <!-- END of form_tog3: the Third Section Body -->
					</li> <!-- END of Third Form Section -->

					<li id="form_slist19" class="pf_tab2 pf_section"> <!-- form_slist2 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle20" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Add or change language(s) spoken by qualified medical interpreter</span>
						</h2>
						<div class="toggler"> <!-- Start of the FORM elements for one Section -->
							
							<fieldset>
							<legend>Provider details</legend>
								<label>Request Type</label>
								<input type="radio" name="s19RequestType" value="Add" id="s19RequestAdd" style="width:auto" /><label for="RequestAdd"  style="width:auto;margin-right:5px;" class="exclude">Add</label>
								<input type="radio" name="s19RequestType" value="Delete"  id="s19RequestDelete" style="width:auto"/><label for="RequestDelete" style="width:auto;margin-right:5px;" class="exclude">Delete</label><br />								
                                <label for="form_ac_s19pOtherLang">Provider language(s) spoken (other than English)</label>
								<select id="form_ac_s19pOtherLang" name="Provider_Lang_Othe_Than_English"  multiple >
									<option selected></option>
									<option value="AFGHAN">AFGHAN</option>
									<option value="AFRIKAANS">AFRIKAANS</option>
									<option value="ALBANIAN">ALBANIAN</option>
									<option value="AMHARIC">AMHARIC</option>
									<option value="ARABIC">ARABIC</option>
									<option value="ARAMAIC">ARAMAIC</option>
									<option value="ARMENIAN">ARMENIAN</option>
									<option value="ASSAMESE">ASSAMESE</option>
									<option value="ASSYRIAN">ASSYRIAN</option>
									<option value="AZERBAIJANI">AZERBAIJANI</option>
									<option value="BANGLADESHI">BANGLADESHI</option>
									<option value="BEMBA">BEMBA</option>
									<option value="BENGALI">BENGALI</option>
									<option value="BHOJPURI">BHOJPURI</option>
									<option value="BIKOL">BIKOL</option>
									<option value="BOSNIAN">BOSNIAN</option>
									<option value="BULGARIAN">BULGARIAN</option>
									<option value="BURMESE">BURMESE</option>
									<option value="CALDEON">CALDEON</option>
									<option value="CAMBODIAN">CAMBODIAN</option>
									<option value="CANTONESE">CANTONESE</option>
									<option value="CATALAN">CATALAN</option>
									<option value="CEBUANO">CEBUANO</option>
									<option value="CHICHEWA">CHICHEWA</option>
									<option value="CHINESE">CHINESE</option>
									<option value="CREOLE">CREOLE</option>
									<option value="CROATIAN">CROATIAN</option>
									<option value="CZECH">CZECH</option>
									<option value="DANISH">DANISH</option>
									<option value="DUTCH">DUTCH</option>
									<option value="EGYPTIAN">EGYPTIAN</option>
									<option value="ENGLISH">ENGLISH</option>
									<option value="FAROESE">FAROESE</option>
									<option value="FARSI">FARSI</option>
									<option value="FILIPINO">FILIPINO</option>
									<option value="FINNISH">FINNISH</option>
									<option value="FLEMISH">FLEMISH</option>
									<option value="FOOKIEN">FOOKIEN</option>
									<option value="FRENCH">FRENCH</option>
									<option value="GEORGIAN">GEORGIAN</option>
									<option value="GERMAN">GERMAN</option>
									<option value="GREEK">GREEK</option>
									<option value="GUJARATI">GUJARATI</option>
									<option value="HAITIAN">HAITIAN</option>
									<option value="HAITIAN CREOLE">HAITIAN CREOLE</option>
									<option value="HAUSA">HAUSA</option>
									<option value="HAWAIIAN">HAWAIIAN</option>
									<option value="HEBREW">HEBREW</option>
									<option value="HINDI">HINDI</option>
									<option value="HMONG">HMONG</option>
									<option value="HUNGARIAN">HUNGARIAN</option>
									<option value="IBU">IBU</option>
									<option value="ICELANDIC">ICELANDIC</option>
									<option value="IGBO">IGBO</option>
									<option value="ILOCANO">ILOCANO</option>
									<option value="INDONESIAN">INDONESIAN</option>
									<option value="IRANIAN">IRANIAN</option>
									<option value="ITALIAN">ITALIAN</option>
									<option value="JAPANESE">JAPANESE</option>
									<option value="KANNADA">KANNADA</option>
									<option value="KAPAMPANGAN">KAPAMPANGAN</option>
									<option value="KASHMIRI">KASHMIRI</option>
									<option value="KHMER">KHMER</option>
									<option value="KONKANI">KONKANI</option>
									<option value="KOREAN">KOREAN</option>
									<option value="KURDISH">KURDISH</option>
									<option value="LAOTIAN">LAOTIAN</option>
									<option value="LATIN">LATIN</option>
									<option value="LATVIAN">LATVIAN</option>
									<option value="LEBANESE">LEBANESE</option>
									<option value="LITHUNIAN">LITHUNIAN</option>
									<option value="MACEDONIAN">MACEDONIAN</option>
									<option value="MADAGASHI">MADAGASHI</option>
									<option value="MAITHILI">MAITHILI</option>
									<option value="MALAYALAM">MALAYALAM</option>
									<option value="MALAYSIAN">MALAYSIAN</option>
									<option value="MANDARIN">MANDARIN</option>
									<option value="MARATHI">MARATHI</option>
									<option value="MOHAWIK">MOHAWIK</option>
									<option value="MONGOLIAN">MONGOLIAN</option>
									<option value="NAVAJO">NAVAJO</option>
									<option value="NEPALI">NEPALI</option>
									<option value="NORWEGIAN">NORWEGIAN</option>
									<option value="OJIBWE">OJIBWE</option>
									<option value="ORADAU">ORADAU</option>
									<option value="PAMPANGA">PAMPANGA</option>
									<option value="PERSIAN">PERSIAN</option>
									<option value="POLISH">POLISH</option>
									<option value="PORTUGUESE">PORTUGUESE</option>
									<option value="PUNJABI">PUNJABI</option>
									<option value="ROMANIAN">ROMANIAN</option>
									<option value="RUSSIAN">RUSSIAN</option>
									<option value="SAMOAN">SAMOAN</option>
									<option value="SANSKRIT">SANSKRIT</option>
									<option value="SERBIAN">SERBIAN</option>
									<option value="SERBOCROATIAN">SERBOCROATIAN</option>
									<option value="SHONA">SHONA</option>
									<option value="SIGN">SIGN</option>
									<option value="SINDHI">SINDHI</option>
									<option value="SINHALESE">SINHALESE</option>
									<option value="SLOVAK">SLOVAK</option>
									<option value="SOMALI">SOMALI</option>
									<option value="SPANISH">SPANISH</option>
									<option value="SWAHILI">SWAHILI</option>
									<option value="SWEDISH">SWEDISH</option>
									<option value="SWISS">SWISS</option>
									<option value="SYRIAN">SYRIAN</option>
									<option value="TAGALOG">TAGALOG</option>
									<option value="TAIWANESE">TAIWANESE</option>
									<option value="TAMIL">TAMIL</option>
									<option value="TELUGU">TELUGU</option>
									<option value="THAI">THAI</option>
									<option value="TIGRIYINI">TIGRIYINI</option>
									<option value="TURKISH">TURKISH</option>
									<option value="TWI">TWI</option>
									<option value="UKRAINIAN">UKRAINIAN</option>
									<option value="URDU">URDU</option>
									<option value="UZBEK">UZBEK</option>
									<option value="VIETNAMESE">VIETNAMESE</option>
									<option value="VISAYAN">VISAYAN</option>
									<option value="YIDDISH">YIDDISH</option>
									<option value="YORUBA">YORUBA</option>
									<option value="ZHUANG">ZHUANG</option>
									<option value="ZULU">ZULU</option>
								</select> <br />	
								<font color="#FF0000">Note:  Hold the Ctrl key to select multiple languages.</font><br />							
							</fieldset>
														
						</div> <!-- END of form_tog3: the Third Section Body -->
					</li> <!-- END of Third Form Section -->
					
				
				</ul> <!-- END of Form Section List -->
				</form>
				
				
				<form CLASS="provider_upload_form" METHOD="POST" action="/mwpmf/message/PDMControllerServlet" > <!-- enctype="multipart/form-data"> --> 
				<input type="hidden" id="formUpdateAction" name="formUpdateAction" value="uploadFile">
				<input type="hidden" id="uploadFileName" name="uploadFileName" value=""/>
				<input type="hidden" id="uploadFileSize" name="uploadFileSize" value=""/>
				<input type="hidden" id="uploadFileComment" name="uploadFileComment" value=""/>
				<input type="hidden" id="totalFileSize" name="totalFileSize" value="0"/>
				
				<ul>
					<li id="form_slist19" class="pf_tab1 pf_tab2 pf_tab3 pf_section"> <!-- form_slist19 is one Section: hidden or shown for each mail box -->
						<h2 id="form_Attachment" class="pf_sectionHeadNoToggle">
							<span class="label">Attachments</span>
						</h2>
						<div><!-- Start of the FORM elements for one Section -->
						<!--<fieldset>-->
						<table WIDTH="100%">

						<p class="pf_bold">Please upload any documentation you wish to send to Anthem to support 
						information entered on this form. You will be allowed to attach MS Word, MS Excel, 'jpg', 
						'pdf', 'gif', 'txt' or 'csv' file types. There is a combined limit of 10 MB for your 
						attachments.</p>
		
						<tr>
						<td colspan="2">
							<table  width="100%" border="1"  bordercolor="" id="fileListTable">
								<input type="hidden" id="dltRowCounter" name="dltRowCounter">
								<input type="hidden" id="fileListSize" name="fileListSize" value="0">
								   <tr bgcolor="#A0A0A0">
										 <td >File Name</td>
										 <td >Size</td>
										 <td >Description of Attachment</td>
										 <td >&nbsp;</td>
								   </tr>
							</table>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<table>
								<tr>
									<td width="20%" align = "right">Upload File</td>
									<td align = "left"><input type="file" name="fileContentData" id="fileContentData" class="requireOption fileNameLength fileType" value="Browse" /></td>
									<td width=1%></td>
								</tr>
								<tr>
									<td width="20%" align = "right">Description ofAttachment</td>
									<td align ="left"><TextArea NAME="uploadDocComment" id="uploadDocComment" class="commentLength"  rows=3 cols=50 ></TextArea></td>
									<td width=1%></td>
								</tr>
								<tr>
									<td>
										<table>
										<tr>
											<td width="50%">&nbsp;&nbsp;&nbsp;&nbsp;</td>
								<% if (isNotSupported) { %>
										  	<td align = "right"><input type="button" name="uploadFormBtn" id="uploadFormBtn" value="Upload File" onClick="fileUpload(this.form,'PDMControllerServlet','upload');"></td>
								<% } else { %>  
									    	<td align = "right"><input type="submit" name="uploadFormBtn" id="uploadFormBtn" value="Upload File" /></td>
								<% } %>
										</tr>
										</table>
									</td>
								</tr>
								</table>
							</td>
						</tr>		
						</table>
						
							<!--</fieldset>		-->
						</div>						
					</li> <!-- END of ninteenth Form Section -->					
				

				</ul> <!-- END of Form Section List -->
				<p>By clicking on the tab marked "SUBMIT" below, I hereby request the above changes and certify that 
				the foregoing information is true and correct and that I am the named professional or am otherwise 
				authorized to make this request and certification on behalf of the named professional. </p>               
				<br />
				<div id="upload"></div>
				</form>
				
				<form CLASS="provider_submit_form" METHOD="POST" action="PDMControllerServlet">
				    <input type="hidden" name="formUpdateAction" id="formUpdateAction" value="<%=action%>"/> 
				    <input type="hidden" name="toemail" value=""/>
				    <input type="hidden" name="body" id="body" value="body of email"/>     	    
				    <input type="hidden" name="Effective_Date" id="Effective_Date" value=""/>
				    <input type="hidden" name="Effective_Date_2" id="Effective_Date_2" value=""/>
				    <input type="hidden" name="Gen_Provider_Last_Name" id="Gen_Provider_Last_Name" value=""/>
					<input type="hidden" name="Gen_Provider_Last_Name_2" id="Gen_Provider_Last_Name_2" value=""/>				    
					<input type="hidden" name="Gen_Provider_First_Name" id="Gen_Provider_First_Name" value=""/>
					<input type="hidden" name="Gen_Provider_First_Name_2" id="Gen_Provider_First_Name_2" value=""/>
					<input type="hidden" name="Gen_Ind_TIN" id="Gen_Ind_TIN" value=""/>
					<input type="hidden" name="GenSec_Prov_Ded_NPI" id="GenSec_Prov_Ded_NPI" value=""/>
					<input type="hidden" name="Gen_Practice_Name_2" id="Gen_Practice_Name_2" value=""/>
					<input type="hidden" name="Gen_Pract_TIN_2" id="Gen_Pract_TIN_2" value=""/>
					<input type="hidden" name="Contact_Person_Last_Name" id="Contact_Person_Last_Name" value=""/>
					<input type="hidden" name="Contact_Person_Last_Name_2" id="Contact_Person_Last_Name_2" value=""/>					
					<input type="hidden" name="Contact_Person_First_Name" id="Contact_Person_First_Name" value=""/>
					<input type="hidden" name="Contact_Person_First_Name_2" id="Contact_Person_First_Name_2" value=""/>
					<input type="hidden" name="Email_Address" id="Email_Address" value=""/>
					<input type="hidden" name="Email_Address_2" id="Email_Address_2" value=""/>
					<input type="hidden" name="Phone_Number" id="Phone_Number" value=""/>
					<input type="hidden" name="Phone_Number_2" id="Phone_Number_2" value=""/>
					<input type="hidden" id="uploadFileName" name="uploadFileName" value=""/>
					<input type="hidden" id="uploadFileSize" name="uploadFileSize" value=""/>
					<input type="hidden" id="uploadFileComment" name="uploadFileComment" value=""/>
					<input id="submitFormBtn" name="submitFormBtn" class="pf_submit" type="submit" WIDTH="100" HEIGHT="25" ALT="Submit" value="Submit" />
			    </form>
				<div class="result"></div>
				
				</div> <!-- END of Tab Body -->
			</div> <!-- END of TabContainer -->
			
		</div> <!-- END of Content -->
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
			<a href="javascript:openPopup('https://www.anthem.com/wps/portal/ahpfooter?content_path=shared/noapplication/f6/s0/t0/pw_ad070873.htm&label=Terms of Use');">Terms of Use</a>	
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
			
	</div><!-- END of Wrapper -->

</body>
</html>