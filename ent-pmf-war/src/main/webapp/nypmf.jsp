<!doctype html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" %>
<%@ page session="true" import="com.anthem.mwpmf.NYVAProvMaintFormBean" %>
<%@ page import="com.anthem.mwpmf.PDMControllerServlet" %>
<%@ page import="com.anthem.mwpmf.NYVAProvMaintFormBean" %>
<%@ page import="com.anthem.mwpmf.PMFUtils" %>
<%@ page import="com.anthem.util.StringUtils" %>
<%@ page import="java.util.*" %>
<%@ page import="org.owasp.esapi.ESAPI" %>

<!-- force recompile in test -->

<%
  String brand = (String)session.getAttribute("brand");

	boolean isNotSupported = false;
	String ua = request.getHeader("User-Agent"); 
	//check if IE is version 9 or 8 and if in compatibiilty view
	isNotSupported = (ua != null && 
			  ((ua.indexOf("MSIE 7.0") != -1) || (ua.indexOf("Trident/5.0") != -1) || (ua.indexOf("Trident/4.0") != -1)));

%>

<html lang="en">
<head>
	<title>Provider Demographic Maintenance Form</title>
	<link rel="stylesheet" type="text/css" href="css/ny/mp_pf_forms.css" />
	<link rel="stylesheet" type="text/css" href="css/ny/ui-lightness/jquery_ui_1.8.custom.css">  
	<link rel="stylesheet" type="text/css" href="css/ny/autocomplete.css">

	<script type="text/javascript" src="js/ny/html5.js"></script>
	<script type="text/javascript" src="js/ny/jqueryLib1.js"></script>
	<script type="text/javascript" src="js/ny/jquery.validate.min.js"></script>
	<script type="text/javascript" src="js/ny/jquery_ui_1.8.custom.min.js"></script>
	<script type="text/javascript" src="js/ny/pf_appScript.js"></script>	
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
		
		
		function fileUpload(form, action_url, div_id) {
	        var filepath = form.fileContentData.value;
	        
	        if (filepath == null || filepath.length == 0)
	        	alert('The Filename is required.');
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
	<% if (brand.equalsIgnoreCase("NYEBCBS")) { %>
		<h2 id="pf_l_mainHeaderEBCBS" name="#Anthem">
			<a href="#Anthem">Welcome to Empire BlueCross BlueShield</a>
		</h2>
	<% } else if (brand.equalsIgnoreCase("NYEBC")) { %>
		<h2 id="pf_l_mainHeaderEBC" name="#Anthem">
			<a href="#Anthem">Welcome to Empire BlueCross</a>
		</h2>
	<% } else { %>
		<h2 id="pf_l_mainHeader" name="#Anthem">
			<a href="#Anthem">Welcome to Anthem</a>
		</h2>
	<% } %>
			<div id="pf_l_content"> <!-- pf_l_content conatins the whole content/forms -->
			<h1>Provider Maintenance Form</h1>
          <p class="pf_bold"> The Provider Maintenance Form (PMF) is to be used by New York individual physicians, 
          practitioners, professionals and group practices to request changes to their practice profiles with 
          <% if (brand.equalsIgnoreCase("NYEBCBS")) { %>
          Empire BlueCross BlueShield
          <% } else if (brand.equalsIgnoreCase("NYEBC")) { %>
          Empire BlueCross.
          <% } %>
           </font></p>
            <p>It is critical that our members receive accurate and current data related to provider availability. 
            Changes to provider records that are affiliated with group contracts must be reported to and submitted by 
            the practice manager or other designated person of authority at the group. Changes to individual contracts 
            may be made at the direction of the contracted physician. 
            <font class="pf_bold">All requests must be received by 
            <% if (brand.equalsIgnoreCase("NYEBCBS")) { %>
            Empire BlueCross BlueShield
            <% } else if (brand.equalsIgnoreCase("NYEBC")) { %>
            Empire BlueCross 
            <% } %>
            45 days prior to the change/update unless you are providing notice of termination from our network, then requests 
            must be received 60 days prior to change. Any request received by 
            <% if (brand.equalsIgnoreCase("NYEBCBS")) { %>
            Empire BlueCross BlueShield
            <% } else if (brand.equalsIgnoreCase("NYEBC")) { %>
            Empire BlueCross 
            <% } %>
            less than 45 days prior to the change may be assigned a future effective date. Contract terms may also supersede 
            the requested effective date.</font> 
            Submit the PMF to 
            <% if (brand.equalsIgnoreCase("NYEBCBS")) { %>
            Empire BlueCross BlueShield
            <% } else if (brand.equalsIgnoreCase("NYEBC")) { %>
            Empire BlueCross 
            <% } %>
            of any changes to the provider/practice name, practice/mailing address, tax identification number, hospital 
            privileges, phone and fax numbers, practice office hours, provider leaving/retiring, provider joining the 
            practice, practice accepting new patients, handicapped accessibility, specialties or languages offered.</p>
            <p>If you are a HOSPITAL BASED PROVIDER please contact the Provider Maintenance Department to make changes to 
            your information.</p>
			<p>Please follow these instructions  when  submitting the PMF:</p>
	            <ol>
                	<li>Complete all applicable sections. The form has multiple options (+) for changes. Complete only the sections applicable to the requested change(s). NOTE: This form will time out after 30 minutes of activity or inactivity and all entries made but not yet submitted will be lost.</li>
        	        <li>Before clicking on the 'Submit' button at the bottom of the PMF, indicate if the change(s) require a valid W-9 (such as ALL Tax ID changes), as the W-9 must be submitted with the PMF through the attachment section along with any other attachments necessary to fulfill the request.</li>
                </ol>
            <p> </p>
			<div class="pf_l_msgContainer">
				<h2>Reason for Submitting this Form</h2>
				<div id="pf_msgOpt1" class="pf_msgOpt left cWidth60 pf_tab1">
                <span class="pf_bold">Option 1</span>
					<ul>
						<li>Change your practice address or phone number</li>
						<li>Add a new location to your practice</li>
						<li>Close a practice location</li>
                        <li>Provider is leaving a group</li>
                        <li>Remove a provider from a location</li>
						<li>Change your payment and remittance address</li>
						<li>Change your office hours or days of operation</li>
                        <li>Name change for individual physician/practitioner</li>
						<li>Change in your acceptance of new patients </li>
						<li>Change in your Medication Assisted Treatment (MAT)</li>
						<li>Update or add your Billing NPI </li>
						<li>Update or add your email address</li>
						<li>Change your practice or group name</li>
						<li>Add or change provider's hospital privilege(s)</li>
						<li>Add or change provider's language(s) spoken</li>
						
						</ul>
					<a href="#tab1" id="gotoTab1" class="gotoTab">CLICK HERE</a><span class="pf_bold"> to make one or more of the above changes.</span>
				</div>
				<div id="pf_msgOpt2" class="pf_msgOpt right cWidth30 pf_tab2">
                <span class="pf_bold">Option 2</span>
					<ul>
						<li>Change your Tax Identification Number (TIN) or ownership of group practice (W-9 Required)</li>					
						<li>Termination of your Provider Participation Agreement</li>
						<li>Add or terminate PT, OT, ST, or audiologist to or from existing ancillary contracted group (Ancillary providers only)</li>
						<li>Add or change provider's areas of expertise (behavioral health providers only)</li>
						<li>Add or change your provider specialty or type (change may require provider to be credentialed)</li>						
						<br /><br />
					</ul>
					<a href="#tab2" id="gotoTab2" class="gotoTab ">CLICK HERE</a><span class="pf_bold"> to make one or more of the above changes.</span>
				</div>
				<div id="pf_msgOpt3" class="pf_msgOpt clear pf_tab3">
                <p><span class="pf_bold">Option 3</span></p>
					<a href="#tab3" id="gotoTab3" class="gotoTab ">CLICK HERE</a><span class="pf_bold"> only if you need to make one or more changes in both Options 1 and 2</span>
					<div class="clear"></div>
				</div>
				
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
					<span class="requiredText">Required fields<em>*</em></span>
					<li id="form_slist0" class="pf_tab1 pf_tab2 pf_tab3  pf_section" > <!-- form_slist0 is one Section: hidden or shown for each mail box -->
						<h2 class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">General information <em>(mandatory fields – <font class="pf_bold">must</font> be completed to submit form)</em></span>
						</h2>
						<div class="toggler"> <!-- Start of the FORM elements for one Section -->
							
							
							<fieldset>
								<label>Request type<em>*</em></label>
									<select id="request_Type_form_slist0" name="request_Type_form_slist0" class="required" title="Please select your request type." >
										<option selected></option>
										<option value="Individual Provider">Individual Provider</option>
										<option value="Group Practice">Group Practice</option>
									</select> <br />							
								<label>Effective date for the add, change or delete<em>*</em></label>
                                <input type="text" name="Effective_Date"  class="required date" onKeyDown="limitTextArea(this.form.Effective_Date,20);" onKeyUp="limitTextArea(this.form.Effective_Date,20);"/> <br />
							 </fieldset>
							
                            <fieldset id="provider_details_fldset" >
								<legend>Provider details</legend>
								<label>Last name<em>*</em></label><input type="text" id="Provider_Last_Name_Gen_Individ"  name="Provider_Last_Name_Gen_Individ" class="required" onKeyDown="limitTextArea(this.form.Provider_Last_Name_Gen_Individ,35);" onKeyUp="limitTextArea(this.form.Provider_Last_Name_Gen_Individ,35);"/><br />
                                <label>First name<em>*</em></label><input type="text" id="Provider_First_Name_Gen_Individ" name="Provider_First_Name_Gen_Individ" class="required" onKeyDown="limitTextArea(this.form.Provider_First_Name_Gen_Individ,35);" onKeyUp="limitTextArea(this.form.Provider_First_Name_Gen_Individ,35);"/><br />
                                <label>Middle name</label><input type="text" id="Provider_Middle_Name_Gen_Individ" name="Provider_Middle_Name_Gen_Individ"  />(enter, if available)<br />
                                <label>Professional title<em>*</em></label><input type="text" id="Professional_Title_Gen_Individ"  name="Professional_Title_Gen_Individ" class="required" /><br />
                                <label>Individual Tax Identification Number (TIN)<em>*</em></label><input type="text" id="Individual_Tax_Identification_Number_Gen_Individ" name="Individual_Tax_Identification_Number_Gen_Individ" class="required TIN" /><br />
								<label>Individual National Provider Identifier (NPI)<em>*</em> </label><input type="text" id="Individual_Provider_Identification_Number_Gen_Individ"  name="Individual_Provider_Identification_Number_Gen_Individ" class="required NPI" /><br />
								<label>Empire Provider Identification Number (EPIN)</label><input type="text" id="Individual_Provider_EPIN_Gen_Individ" name="Individual_Provider_EPIN_Gen_Individ" class="EPIN" />(enter, if available)<br />
								<label>Do you provide Telehealth Services at this location? (If marked yes, Telehealth will be displayed in the directory.)<em>*</em></label>
								<select id="Telehealth_Services_Gen_Individ" name="Telehealth_Services_Gen_Individ" class="required">
									<option selected></option>
									<option value="Yes">Yes</option>
									<option value="No">No</option>
								</select> <br />
							</fieldset>
							
							<fieldset id="practice_details_fldset" >
								<legend>Practice details</legend>
								<label>Practice name<em>*</em></label><input type="text" id="Provider_Practice_Name_Gen_Prac" name="Provider_Practice_Name_Gen_Prac" class="required" onKeyDown="limitTextArea(this.form.Provider_Practice_Name_Gen_Prac,55);" onKeyUp="limitTextArea(this.form.Provider_Practice_Name_Gen_Prac,55);"/><br />
								<label>Practice Tax Identification Number (TIN)<em>*</em></label><input type="text" id="Practice_Tax_Identification_Number_Gen_Prac" name="Practice_Tax_Identification_Number_Gen_Prac" class="required TIN" /><br />
								<label>Organizational National Provider Identifier (NPI) <em>*</em></label><input type="text" id="Org_NPI_Gen_Prac"  name="Org_NPI_Gen_Prac" class="required NPI" /><br />
								<label>Provider List/Roster Attached</label>
                                <input type="checkbox" id="roster_Attached_Gen_Prac" name="roster_Attached_Gen_Prac" value="Yes" style="width:auto" class="requireAttachment"/><br />
                           </fieldset>
							
							<fieldset>
								<legend>Contact details</legend>
								<label>Contact person last name<em>*</em></label><input type="text" name="Contact_Person_Last_Name" class="required" onKeyDown="limitTextArea(this.form.Phone_Number,25);" onKeyUp="limitTextArea(this.form.Phone_Number,25);"e" class="required" onKeyDown="limitTextArea(this.form.Contact_Person_Last_Name,35);" onKeyUp="limitTextArea(this.form.Contact_Person_Last_Name,35);"/><br />
                                <label>Contact person first name<em>*</em></label><input type="text" name="Contact_Person_First_Name" class="required" onKeyDown="limitTextArea(this.form.Contact_Person_First_Name,35);" onKeyUp="limitTextArea(this.form.Contact_Person_First_Name,35);"/><br />		
								<label>Email address<em>*</em></label><input type="text" name="Email_Address" class="required email" onKeyDown="limitTextArea(this.form.Email_Address,40);" onKeyUp="limitTextArea(this.form.Email_Address,40);"/><br />
								<label>Phone number<em>*</em></label><input type="text" name="Phone_Number" class="required" onKeyDown="limitTextArea(this.form.Phone_Number,25);" onKeyUp="limitTextArea(this.form.Phone_Number,25);"/><br />
							</fieldset>
							
						</div>
						
					</li>
					<li id="form_slist0" class="pf_tab1 pf_tab3 pf_section"> <!-- form_slist0 is one Section: hidden or shown for each mail box -->
						<h2 id="form_tog1Handle" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Change your practice address or phone number</span>
						</h2>
						<div id="form_tog1" class="toggler"> <!-- Start of the FORM elements for one Section -->
							
							<fieldset>
							<legend>Previous/Existing practice office</legend>
								<label for="form_ac_s1_preAddrS1">Address line 1</label>
								<input type="text" id="form_ac_s1_preAddrS1" name="Address_Street1" class="" title="Please type your First Address" />  <br />
								<label for="form_ac_s1_preAddrS2">Address line 2</label>
								<input type="text" id="form_ac_s1_preAddrS2" name="Address Street2" />  <br />
								<label for="form_ac_s1_preCity">City</label>
								<input type="text" id="form_ac_s1_preCity" name="City" class="" title="Please type your City name" />  <br />
								<label for="form_ac_s1_preState">State</label>
								<input type="text" id="form_ac_s1_preState" name="State" class="" title="Please type your State" />  <br />
								<label for="form_ac_s1_preZip">Zip</label>
								<input type="text" id="form_ac_s1_preZip" name="Zip" class="" title="Please type your Zipcode" />  <br />
								<label for="form_ac_s1_preCounty">County</label>
								<input type="text" id="form_ac_s1_preCounty" name="County" />  <br />
                                
								<label for="form_ac_s1_teleno">Practice office phone number</label>
								<input type="text" id="form_ac_s1_teleno" name="Practice_Office_Phone_Number" />  <br />
								<label for="form_ac_s1_faxno">Practice office fax number</label>
								<input type="text" id="form_ac_s1_faxno" name="Practice_Office_Fax_Number" />  <br />
									
							</fieldset>
							<fieldset>
							<legend>Updated/New practice office</legend>	
								<label for="form_ac_s1_newAddrS1">Address line 1</label>
								<input type="text" id="form_ac_s1_newAddrS1" name="Address Street1" class="" title="Please type your First Address" />  <br />
								<label for="form_ac_s1_newAddrS2">Address line 2</label>
								<input type="text" id="form_ac_s1_newAddrS2" name="Address Street2" />  <br />
								<label for="form_ac_s1_newCity">City</label>
								<input type="text" id="form_ac_s1_newCity" name="City" class="" title="Please type your City name"/>  <br />
								<label for="form_ac_s1_newState">State</label>
								<input type="text" id="form_ac_s1_newState" name="State" class="" title="Please type your State" />  <br />
								<label for="form_ac_s1_newZip">Zip</label>
								<input type="text" id="form_ac_s1_newZip" name="Zip" class="" title="Please type your Zipcode" />  <br />
								<label for="form_ac_s1_newCounty">County</label>
								<input type="text" id="form_ac_s1_newCounty" name="County" />  <br />
								<label for="form_ac_s1_newPracticeofficenumber">Practice office phone number</label>
								<input type="text" id="form_ac_s1_newPracticeofficenumber" name="Office phone number" />  <br />
                                <label for="form_ac_s1_newPracticeofficefax">Practice office fax number</label>
								<input type="text" id="form_ac_s1_newPracticeofficefax" name="Office phone fax number" />  <br />
                                <label for="form_ac_s1_newpat">Accepting new patients</label>
								<select id="form_ac_s1_newpat" name="Accepting_New_Patients">
									<option selected></option>
									<option value="Yes">Yes</option>
									<option value="No">No</option>
								</select> <br />
                                <label for="form_ac_s1_accessQn">Does this office provide handicapped accessibility?</label>
								<select id="form_ac_s1_accessQn" name="Handicapped_Accessibility">
									<option selected></option>
									<option value="Yes">Yes</option>
									<option value="No">No</option>
								</select> <br />
								<label for="form_ac_s1_accessTrans">Is this office accessible by public transportation?</label>
								<select id="form_ac_s1_accessTrans" name="Public_Transportation_Accessibilty">
									<option selected></option>
									<option value="Yes">Yes</option>
									<option value="No">No</option>
								</select> <br />	
								<label for="form_ac_s1_accessPrint">Does this site profile need to be updated on www.empireblue.com and in the printed directories?</label>
								<select id="form_ac_s1_accessPrint" name="Public_Access_Print">
									<option selected></option>
									<option value="Yes">Yes</option>
									<option value="No">No</option>
								</select> <br />
								<label for="form_ac_s1_telehealthServices">Do you provide Telehealth Services at this location? (If marked yes, Telehealth will be displayed in the directory.)</label>
								<select id="form_ac_s1_telehealthServices" name="Telehealth_Services">
									<option selected></option>
									<option value="Yes">Yes</option>
									<option value="No">No</option>
								</select> <br />			
							</fieldset> 
						</div> <!-- END of First Toggling DIV Tog1 -->
					</li> <!-- END of First Section -->
					
					<li id="form_slist1" class="pf_tab1 pf_tab3 pf_section"> <!-- form_slist1 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle2" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Add a new location to your practice</span>
						</h2>
						<div id="form_tog2" class="toggler"> <!-- Start of the FORM elements for one Section -->
							<fieldset>
							<legend>Updated/New practice office</legend>
								<label for="form_ac_s2_newAddrs1">Address line 1</label>
								<input type="text" id="form_ac_s2_newAddrs1" name="Address_Street1" />  <br />
								<label for="form_ac_s2_newAddrS2">Address line 2</label>
								<input type="text" id="form_ac_s2_newAddrS2" name="Address_Street2" />  <br />
								<label for="form_ac_s2_newCity">City</label>
								<input type="text" id="form_ac_s2_newCity" name="City" />  <br />
								<label for="form_ac_s2_newState">State</label>
								<input type="text" id="form_ac_s2_newState" name="State" />  <br />
								<label for="form_ac_s2_newZip">Zip</label>
								<input type="text" id="form_ac_s2_newZip" name="Zip" />  <br />
								<label for="form_ac_s2_newCounty">County</label>
                                <input type="text" id="form_ac_s2_newCounty" name="County" />  <br />
								<label for="form_ac_s2_priEmail">Primary email address</label>
								<input type="text" id="form_ac_s2_priEmail" name="Primary_Email" class="email"/>  <br />
								<label for="form_ac_s2_teleno">Practice office phone number</label>
								<input type="text" id="form_ac_s2_teleno" name="Practice_Office_Phone_Number" />  <br />
                                <label for="form_ac_s2_pOffFax">Practice office fax number</label>
								<input type="text" id="form_ac_s2_pOffFax" name="Practice_Office_Fax_Number" /> <br />
								<label for="form_ac_s2_langStaff">Language spoken by office staff</label>
								<input type="text" id="form_ac_s2_langStaff" name="Language_Spoken_Office_Staff" /> <br />
								<label for="form_ac_s2_newpat">Accepting new patients</label>
								<select id="form_ac_s2_newpat" name="Accepting_New_Patients">
									<option selected></option>
									<option value="Yes">Yes</option>
									<option value="No">No</option>
								</select> <br />
								<div id="form_ind_pracmat">
									<label for="form_ac_s1_indPracAuth">Is the physician at this office authorized to dispense Medication Assisted Treatment (MAT) and accepting new patients seeking treatment?</label>
									<select id="form_ac_s1_indPracAuth" name="IndPrac_MAT_Authorized">
										<option selected></option>
										<option value="Yes">Yes</option>
										<option value="No">No</option>
									</select> <br />
									<label for="form_ac_s1_indPracCouns">Is counseling for Opioid Use Disorders provided at this office?</label>
									<select id="form_ac_s1_indPracCouns" name="IndPrac_MAT_Counseling">
										<option selected></option>
										<option value="Yes">Yes</option>
										<option value="No">No</option>
									</select> <br />
								</div>
								<div id="form_grp_pracmat">
									<label for="form_ac_s1_grpPracCert">Is this office a Certified Opioid Treatment Program?</label>
									<select id="form_ac_s1_grpPracCert" name="GrpPrac_MAT_CertOpiod">
										<option selected></option>
										<option value="Yes">Yes</option>
										<option value="No">No</option>
									</select> <br />
									<label for="form_ac_s1_grpPracOffer">Does this office offer Medication Assisted Treatment (MAT) for Opioid Use Disorders and accepting new patients seeking treatment?</label>
									<select id="form_ac_s1_grpPracOffer" name="GrpPrac_MAT_Offer">
										<option selected></option>
										<option value="Yes">Yes</option>
										<option value="No">No</option>
									</select> <br />
									<label for="form_ac_s1_grpPracCouns">Is counseling in conjunction with Medication Assisted Treatment (MAT) for Opioid Use Disorders provided at this office?</label>
									<select id="form_ac_s1_grpPracCouns" name="GrpPrac_MAT_Counseling">
										<option selected></option>
										<option value="Yes">Yes</option>
										<option value="No">No</option>
									</select> <br />
								</div>
                                <label for="form_ac_s2_accessQn">Does this office provide handicapped accessibility?</label>
								<select id="form_ac_s2_accessQn" name="Handicapped_Accessibility">
									<option selected></option>
									<option value="Yes">Yes</option>
									<option value="No">No</option>
								</select> <br />
								<label for="form_ac_s2_accessTrans">Is this office accessible by public transportation?</label>
								<select id="form_ac_s2_accessTrans" name="Public_Transportation_Accessibililty">
									<option selected></option>
									<option value="Yes">Yes</option>
									<option value="No">No</option>
								</select> <br />
                                <label for="form_ac_s2_telehealthServices">Do you provide Telehealth Services at this location? (If marked yes, Telehealth will be displayed in the directory.)</label>
								<select id="form_ac_s2_telehealthServices" name="Telehealth_Services">
									<option selected></option>
									<option value="Yes">Yes</option>
									<option value="No">No</option>
								</select> <br />	
							</fieldset>
							<fieldset class="form_cWidth50px">
                            	<legend>Office hours</legend>
									<label for="form_ac_s2_monSTime">Monday start time (hr:min)</label>
									<input type="text" id="form_ac_s2_monSTime" class="pf_cWidthTime time" name="Monday_Start_Time" />  <br />
									<label for="form_ac_s2_monETime" class="">Monday end time (hr:min)</label>
									<input type="text" id="form_ac_s2_monETime" class="pf_cWidthTime time" name="Monday_End_Time" />  <br />
									<label for="form_ac_s2_tueSTime">Tuesday start time (hr:min)</label>
									<input type="text" id="form_ac_s2_tueSTime" class="pf_cWidthTime time" name="Tuesday_Start_Time" />  <br />
									<label for="form_ac_s2_tueETime">Tuesday end time (hr:min)</label>
									<input type="text" id="form_ac_s2_tueETime" class="pf_cWidthTime time" name="Tuesday_End_Time" />  <br />
									<label for="form_ac_s2_wedSTime">Wednesday start time (hr:min)</label>
									<input type="text" id="form_ac_s2_wedSTime" class="pf_cWidthTime time" name="Wednesday_Start_Time" />  <br />
									<label for="form_ac_s2_wedETime">Wednesday end time (hr:min)</label>
									<input type="text" id="form_ac_s2_wedETime" class="pf_cWidthTime time" name="Wednesday_End_Time" />  <br />
									<label for="form_ac_s2_thuSTime">Thursday start time (hr:min)</label>
									<input type="text" id="form_ac_s2_thuSTime" class="pf_cWidthTime time" name="Thursday_Start_Time" />  <br />
									<label for="form_ac_s2_thuETime">Thursday end time (hr:min)</label>
									<input type="text" id="form_ac_s2_thuETime" class="pf_cWidthTime time" name="Thursday_End_Time" />  <br />
									<label for="form_ac_s2_friSTime">Friday start time (hr:min)</label>
									<input type="text" id="form_ac_s2_friSTime" class="pf_cWidthTime time" name="Friday_Start_Time" />  <br />
									<label for="form_ac_s2_friETime">Friday end time (hr:min)</label>
									<input type="text" id="form_ac_s2_friETime" class="pf_cWidthTime time" name="Friday_End_Time" />  <br />
									<label for="form_ac_s2_satSTime">Saturday start time (hr:min)</label>
									<input type="text" id="form_ac_s2_satSTime" class="pf_cWidthTime time" name="Saturday_Start_Time" />  <br />
									<label for="form_ac_s2_satETime">Saturday end time (hr:min)</label>
									<input type="text" id="form_ac_s2_satETime" class="pf_cWidthTime time" name="Saturday_End_Time" />  <br />
									<label for="form_ac_s2_sunSTime">Sunday start time (hr:min)</label>
									<input type="text" id="form_ac_s2_sunSTime" class="pf_cWidthTime time" name="Sunday_Start_Time" />  <br />
									<label for="form_ac_s2_sunETime">Sunday end time (hr:min)</label>
									<input type="text" id="form_ac_s2_sunETime" class="pf_cWidthTime time" name="Sunday_End_Time" />  <br />
							</fieldset>
                            <fieldset>
							<legend>Updated/New payment and remittance</legend>
								<label for="form_ac_s2_nprAddrs1">Pay to Address Line 1</label>
								<input type="text" id="form_ac_s2_nprAddrs1" name="Address_Street1" />  <br />
								<label for="form_ac_s2_nprAddrS2">Pay to Address Line 2</label>
								<input type="text" id="form_ac_s2_nprAddrS2" name="Address_Street2" />  <br />
								<label for="form_ac_s2_nprCity">City</label>
								<input type="text" id="form_ac_s2_nprCity" name="City" />  <br />
								<label for="form_ac_s2_nprState">State</label>
								<input type="text" id="form_ac_s2_nprState" name="State" />  <br />
								<label for="form_ac_s2_nprZip">Zip</label>
								<input type="text" id="form_ac_s2_nprZip" name="Zip" />  <br />
								<label for="form_ac_s2_nprCounty">County</label>
								<input type="text" id="form_ac_s2_nprCounty" name="County" />  <br />
								<label for="form_ac_s2_newPriEmail">Email address</label>
								<input type="text" id="form_ac_s2_newPriEmail" name="Primary_Email" class="email"/>  <br />
                                <label for="form_ac_s2_nprPhone">Phone number</label>
								<input type="text" id="form_ac_s2_nprPhone" name="Phone" />  <br />
								<label for="form_ac_s2_nprFax">Fax number</label>
								<input type="text" id="form_ac_s2_nprFax" name="Fax" />  <br />
								
							</fieldset>
						</div> <!-- END of form_tog2: the second Section Body -->
					</li> <!-- END of Second Form Section -->
					
					
					
					<li id="form_slist2" class="pf_tab1 pf_tab3 pf_section"> <!-- form_slist2 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle3" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Close a practice location</span>
						</h2>
						<div class="toggler"> <!-- Start of the FORM elements for one Section -->
							<fieldset>
							<label for="s3_tReason">Reason for closing location</label>
							<input type="text" id="s3_tReason" name="s3_tReason" class="conditionalCheckClosePractice"  />  <br />
                            </fieldset>
							<fieldset>
							<legend>Previous/Existing practice office</legend>
								<label for="form_ac_s3_preAddrs1">Address line 1 </label>
								<input type="text" id="form_ac_s3_preAddrs1" name="form_ac_s3_preAddrs1" class="conditionalCheckClosePractice"  />  <br />
								<label for="form_ac_s3_preAddrS2">Address line 2</label>
								<input type="text" id="form_ac_s3_preAddrS2" name="form_ac_s3_preAddrS2"  />  <br />
								<label for="form_ac_s3_preCity">City</label>
								<input type="text" id="form_ac_s3_preCity" name="form_ac_s3_preCity" class="conditionalCheckClosePractice" />  <br />
								<label for="form_ac_s3_preState">State</label>
								<input type="text" id="form_ac_s3_preState" name="form_ac_s3_preState" class="conditionalCheckClosePractice"  />  <br />
								<label for="form_ac_s3_preZip">Zip</label>
								<input type="text" id="form_ac_s3_preZip" name="form_ac_s3_preZip" class="conditionalCheckClosePractice"  />  <br />
								<label for="form_ac_s3_preCounty">County</label>
								<input type="text" id="form_ac_s3_preCounty" name="form_ac_s3_preCounty" class="conditionalCheckClosePractice"  />  <br />
								<label>By performing this action, I acknowledge all providers within the practice will be termed from this location.<em>*</em></label>
                                <input type="checkbox" id="per" name="per" value="Yes" style="width:auto"  class="conditionalCheckClosePractice"  /><br />
								
							</fieldset>

						</div> <!-- END of form_tog3: the Third Section Body -->
					</li> <!-- END of Third Form Section -->
                    <li id="form_slist3" class="pf_tab1 pf_tab3 pf_section"> <!-- form_slist3 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle4" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Provider is leaving a group</span>
						</h2>
						<div class="toggler"> <!-- Start of the FORM elements for one Section -->
							<fieldset>
                            	<legend>Previous/Existing practice office</legend>
									<label for="form_ac_s3_Reason">Reason for leaving</label>
    	                            <input type="text" id="form_ac_s3_Reason"name="Reason" /><br />
        	                        <label for="form_ac_s3_Addrs1">Address line 1</label>
									<input type="text" id="form_ac_s3_Addrs1" name="Address1" />  <br />
									<label for="form_ac_s3_AddrS2">Address line 2</label>
									<input type="text" id="form_ac_s3_AddrS2" name="Address2" />  <br />
									<label for="form_ac_s3_City">City</label>
									<input type="text" id="form_ac_s3_City" name="City" />  <br />
									<label for="form_ac_s3_State">State</label>
									<input type="text" id="form_ac_s3_State" name="State" />  <br />
									<label for="form_ac_s3_Zip">Zip</label>
									<input type="text" id="form_ac_s3_Zip" name="Zip" />  <br />  
                	                <label for="form_ac_s3_PCP">Are you a Primary Care Provider (PCP)? </label>
									<select id="form_ac_s3_PCP" name="PCP">
										<option selected></option>
										<option value="Yes">Yes</option>
										<option value="No">No</option>
									</select> <br />
							</fieldset>	
                         </div><!-- END of form_tog4: the fourth Section Body -->
                     </li><!-- END of Fourth Form Section -->         
                    
                    
                    <li id="form_slist4" class="pf_tab1 pf_tab3 pf_section"> <!-- form_slist4 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle5" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Remove a provider from a location</span>
						</h2>
						<div class="toggler"> <!-- Start of the FORM elements for one Section -->
							
							<fieldset>
								<label for="form_ac_s4_Reason">Reason for removal of provider</label>
                                <input type="text" id="form_ac_s4_Reason"name="Reason" /><br />
                                <label for="form_ac_s4_Addrs1">Address line 1</label>
								<input type="text" id="form_ac_s4_Addrs1" name="Address1" />  <br />
								<label for="form_ac_s4_AddrS2">Address line 2</label>
								<input type="text" id="form_ac_s4_AddrS2" name="Address2" />  <br />
								<label for="form_ac_s4_City">City</label>
								<input type="text" id="form_ac_s4_City" name="City" />  <br />
								<label for="form_ac_s4_State">State</label>
								<input type="text" id="form_ac_s4_State" name="State" />  <br />
								<label for="form_ac_s4_Zip">Zip</label>
								<input type="text" id="form_ac_s4_Zip" name="Zip" />  <br />  
                                <label for="form_ac_s4_PCP">Are you a Primary Care Provider (PCP)? </label>
								<select id="form_ac_s4_PCP" name="PCP">
									<option selected></option>
									<option value="Yes">Yes</option>
									<option value="No">No</option>
								</select> <br />
							</fieldset>	
						</div> <!-- END of form_tog5: the fifth Section Body -->	
					</li> <!-- END of Fifth Form Section -->
					<li id="form_slist5" class="pf_tab1 pf_tab3 pf_section"> <!-- form_slist3 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle6" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Change your payment and remittance address</span>
						</h2>
						<div class="toggler"> <!-- Start of the FORM elements for one Section -->
							
							<fieldset>
							<legend>Previous/Existing payment and remittance</legend>
								<label for="form_ac_s5_preAddrs1">Pay to Address line 1</label>
								<input type="text" id="form_ac_s5_preAddrs1" name="Address_Street1" />  <br />
								<label for="form_ac_s5_preAddrS2">Pay to Address line 2</label>
								<input type="text" id="form_ac_s5_preAddrS2" name="Address_Street2" />  <br />
								<label for="form_ac_s5_preCity">City</label>
								<input type="text" id="form_ac_s5_preCity" name="City" />  <br />
								<label for="form_ac_s5_preState">State</label>
								<input type="text" id="form_ac_s5_preState" name="State" />  <br />
								<label for="form_ac_s5_preZip">Zip</label>
								<input type="text" id="form_ac_s5_preZip" name="Zip" />  <br />
                                <label for="form_ac_s5_preCounty">County</label>
								<input type="text" id="form_ac_s5_preCounty" name="County" />  <br />
								<label for="form_ac_s5_preEmail">Email address</label>
								<input type="text" id="form_ac_s5_preEmail" name="Primary_Email" class="email"/>  <br />								
                                <label for="form_ac_s5_prePhone">Phone number</label>
								<input type="text" id="form_ac_s5_prePhone" name="Phone" />  <br />
                                <label for="form_ac_s5_preFax">Fax number</label>
								<input type="text" id="form_ac_s5_preFax" name="Fax" />  <br />
							</fieldset>
							<fieldset>
							<legend>Updated/New payment and remittance</legend>
								<label for="form_ac_s5_newAddrs1">Pay to Address line 1</label>
								<input type="text" id="form_ac_s5_newAddrs1" name="Address_Street1" />  <br />
								<label for="form_ac_s5_newAddrS2">Pay to Address line 2</label>
								<input type="text" id="form_ac_s5_newAddrS2" name="Address_Street2" />  <br />
								<label for="form_ac_s5_newCity">City</label>
								<input type="text" id="form_ac_s5_newCity" name="City" />  <br />
								<label for="form_ac_s5_newState">State</label>
								<input type="text" id="form_ac_s5_newState" name="State" />  <br />
								<label for="form_ac_s5_newZip">Zip</label>
								<input type="text" id="form_ac_s5_newZip" name="Zip" />  <br />
                                <label for="form_ac_s5_newCounty">County</label>
								<input type="text" id="form_ac_s5_newCounty" name="County" />  <br />
								<label for="form_ac_s5_remitEmail">Email address</label>
								<input type="text" id="form_ac_s5_remitEmail" name="Primary_Email" class="email"/>  <br />
                                <label for="form_ac_s5_newPhone">Phone number</label>
								<input type="text" id="form_ac_s5_newPhone" name="Phone" />  <br />
                                <label for="form_ac_s5_newFax">Fax number</label>
								<input type="text" id="form_ac_s5_newFax" name="Fax" />  <br />
								<p>(Note:  Changes to the payment and remittance address will not apply to the electronic funds transfer (EFT) address.)</p>
							</fieldset>
						</div> <!-- END of form_tog4: the sixth Section Body -->
					</li> <!-- END of sixth Form Section -->

					<li id="form_slist6" class="pf_tab1 pf_tab3 pf_section"> <!-- form_slist5 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle7" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Change your office hours or days of operation</span>
						</h2>
						<div class="toggler"> <!-- Start of the FORM elements for one Section -->
							
							<fieldset>
                            	<legend>Office Hours</legend>
									<label for="form_ac_s6_monSTime">Monday start time (hr:min)</label>
									<input type="text" id="form_ac_s6_monSTime" class="pf_cWidthTime time" name="Monday_Start_Time" />  <br />
									<label for="form_ac_s6_monETime">Monday end time (hr:min)</label>
									<input type="text" id="form_ac_s6_monETime" class="pf_cWidthTime time" name="Monday_End_Time" />  <br />
									<label for="form_ac_s6_tueSTime">Tuesday start time (hr:min)</label>
									<input type="text" id="form_ac_s6_tueSTime" class="pf_cWidthTime time" name="Tuesday_Start_Time" />  <br />
									<label for="form_ac_s6_tueETime">Tuesday end time (hr:min)</label>
									<input type="text" id="form_ac_s6_tueETime" class="pf_cWidthTime time" name="Tuesday_End_Time" />  <br />
									<label for="form_ac_s6_wedSTime">Wednesday start time (hr:min)</label>
									<input type="text" id="form_ac_s6_wedSTime" class="pf_cWidthTime time" name="Wednesday_Start_Time" />  <br />
									<label for="form_ac_s6_wedETime">Wednesday end time (hr:min)</label>
									<input type="text" id="form_ac_s6_wedETime" class="pf_cWidthTime time" name="Wednesday_End_Time" />  <br />
									<label for="form_ac_s6_thuSTime">Thursday start time (hr:min)</label>
									<input type="text" id="form_ac_s6_thuSTime" class="pf_cWidthTime time" name="Thursday_Start_Time" />  <br />
									<label for="form_ac_s6_thuETime">Thursday end time (hr:min)</label>
									<input type="text" id="form_ac_s6_thuETime" class="pf_cWidthTime time" name="Thursday_End_Time" />  <br />
									<label for="form_ac_s6_friSTime">Friday start time (hr:min)</label>
									<input type="text" id="form_ac_s6_friSTime" class="pf_cWidthTime time" name="Friday_Start_Time" />  <br />
									<label for="form_ac_s6_friETime">Friday end time (hr:min)</label>
									<input type="text" id="form_ac_s6_friETime" class="pf_cWidthTime time" name="Friday_End_Time" />  <br />
									<label for="form_ac_s6_satSTime">Saturday start time (hr:min)</label>
									<input type="text" id="form_ac_s6_satSTime" class="pf_cWidthTime time" name="Saturday_Start_Time" />  <br />
									<label for="form_ac_s6_satETime">Saturday end time (hr:min)</label>
									<input type="text" id="form_ac_s6_satETime" class="pf_cWidthTime time" name="Saturday_End_Time" />  <br />
									<label for="form_ac_s6_sunSTime">Sunday start time (hr:min)</label>
									<input type="text" id="form_ac_s6_sunSTime" class="pf_cWidthTime time" name="Sunday_Start_Time" />  <br />
									<label for="form_ac_s6_sunETime">Sunday end time (hr:min)</label>
									<input type="text" id="form_ac_s6_sunETime" class="pf_cWidthTime time" name="Sunday_End_Time" />  <br />
									<p>(Note:  Office hours will be displayed in the Provider Directory.)</p>
							</fieldset>
						</div> <!-- END of form_tog6: the Seventh Section Body -->	
					</li> <!-- END of Seventh Form Section -->
                    
                    
					<li id="form_slist7" class="pf_tab1 pf_tab3 pf_section"> <!-- form_slist6 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle8" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Name change for individual physician/practitioner</span>
						</h2>
						<div class="toggler"> <!-- Start of the FORM elements for one Section -->
							
							<fieldset>
							<legend>Provider name (former)</legend>
							  <label for="f3s7_flastname">Last name</label>
							  <input type="text" id="f3s7_flastname" name="Provider_LastNameFormer" title="Please type your Last name" />  <br />
							  <label for="f3s7_ffirstname">First name</label>
							  <input type="text" id="f3s7_ffirstname" name="Provider_FirstNameFormer" title="Please type your First name" />  <br />
							  <label for="f3s7_fmidname">Middle name</label>
							  <input type="text" id="f3s7_fmidname" name="Provider_MiddleNameFormer" />  <br />
							  <label for="f3s7fsuffix">Suffix</label>
							  <input type="text" id="f3s7fsuffix" name="SuffixFormer" />  <br />
                              <label for="f3s7ftitle">Professional title</label>
							  <input type="text" id="f3s7ftitle" name="Professional_TitleFormer" />  <br />
							</fieldset>	
                            <fieldset>
							<legend>Provider name (updated)</legend>
							  <label for="f3s7_lastname">Last name</label>
							  <input type="text" id="f3s7_lastname" name="Provider_LastNameUpdated" title="Please type your Last name" />  <br />
							  <label for="f3s7_firstname">First name</label>
							  <input type="text" id="f3s7_firstname" name="Provider_FirstNameUpdated" title="Please type your First name" />  <br />
							  <label for="f3s7_midname">Middle name</label>
							  <input type="text" id="f3s7_midname" name="Provider_MiddleNameUpdated" />  <br />
							  <label for="f3s7suffix">Suffix</label>
							  <input type="text" id="f3s7suffix" name="SuffixUpdated" />  <br />
                              <label for="f3s7title">Professional title</label>
							  <input type="text" id="f3s7title" name="Professional_TitleUpdated" />  <br />
                              <label for="f3s7license">State license or certification number</label>
							  <input type="text" id="f3s7license" name="License" />  <br />
							  <p><em>*Your information must be updated with the state of New York prior to submission of this request.</em></p><br/>
							</fieldset>							
							<fieldset>
						</div> <!-- END of form_tog7: the Eighth Section Body -->	
					</li> <!-- END of Eighth Form Section -->
					<li id="form_slist9" class="pf_tab1 pf_tab3 pf_section"> <!-- form_slist7 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle10" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Change in your acceptance of new patients </span>
						</h2>
						<div class="toggler"> <!-- Start of the FORM elements for one Section -->
							<fieldset>
								<label for="form_ac_s9_newpat">Accepting new patients<em>*</em></label>
								<select id="form_ac_s9_newpat" name="Accepting_New_Patients" class="conditionalCheckChangeAccept" >
									<option selected></option>
									<option value="Yes">Yes</option>
									<option value="No">No</option>
								</select> <br />
							</fieldset>
							<fieldset>
							<legend>Previous/Existing practice office</legend>
								<label for="form_ac_s9_preAddrs1">Address line 1</label>
								<input type="text" id="form_ac_s9_preAddrs1" name="Address_Street1" class="conditionalCheckChangeAccept" />  <br />
								<label for="form_ac_s9_preAddrS2">Address line 2</label>
								<input type="text" id="form_ac_s9_preAddrS2" name="Address_Street2" />  <br />
								<label for="form_ac_s9_preCity">City</label>
								<input type="text" id="form_ac_s9_preCity" name="City" class="conditionalCheckChangeAccept" />  <br />
								<label for="form_ac_s9_preState">State</label>
								<input type="text" id="form_ac_s9_preState" name="State" class="conditionalCheckChangeAccept" />  <br />
								<label for="form_ac_s9_preZip">Zip</label>
								<input type="text" id="form_ac_s9_preZip" name="Zip" class="conditionalCheckChangeAccept" />  <br />
							</fieldset>
						</div> <!-- END of form_tog8: the tenth Section Body -->	
					</li> <!-- END of tenth Form Section -->
					<li id="form_slist20" class="pf_tab1 pf_tab3 pf_section"> <!-- form_slist20 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle20" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Change in your Medication Assisted Treatment (MAT) </span>
						</h2>
						<div class="toggler"> <!-- Start of the FORM elements for one Section -->
							<fieldset id="ind_mat_fldset">
								<label for="form_ac_s20_indAuth">Is the physician at this office authorized to dispense Medication Assisted Treatment (MAT) and accepting new patients seeking treatment?<em>*</em></label>
								<select id="form_ac_s20_indAuth" name="IndProv_MAT_Authorized" class="conditionalCheckChangeMAT" >
									<option selected></option>
									<option value="Yes">Yes</option>
									<option value="No">No</option>
								</select> <br />
								<label for="form_ac_s20_indCouns">Is counseling for Opioid Use Disorders provided at this office?<em>*</em></label>
								<select id="form_ac_s20_indCouns" name="IndProv_MAT_Counseling" class="conditionalCheckChangeMAT" >
									<option selected></option>
									<option value="Yes">Yes</option>
									<option value="No">No</option>
								</select> <br />
							</fieldset>
							<fieldset id="grp_mat_fldset">
								<label for="form_ac_s20_grpCert">Is this office a Certified Opioid Treatment Program?<em>*</em></label>
								<select id="form_ac_s20_grpCert" name="GrpProv_MAT_CertOpiod" class="conditionalCheckChangeMAT" >
									<option selected></option>
									<option value="Yes">Yes</option>
									<option value="No">No</option>
								</select> <br />
								<label for="form_ac_s20_grpOffer">Does this office offer Medication Assisted Treatment (MAT) for Opioid Use Disorders and accepting new patients seeking treatment?<em>*</em></label>
								<select id="form_ac_s20_grpOffer" name="GrpProv_MAT_Offer" class="conditionalCheckChangeMAT" >
									<option selected></option>
									<option value="Yes">Yes</option>
									<option value="No">No</option>
								</select> <br />
								<label for="form_ac_s20_grpCouns">Is counseling in conjunction with Medication Assisted Treatment (MAT) for Opioid Use Disorders provided at this office?<em>*</em></label>
								<select id="form_ac_s20_grpCouns" name="GrpProv_MAT_Counseling" class="conditionalCheckChangeMAT" >
									<option selected></option>
									<option value="Yes">Yes</option>
									<option value="No">No</option>
								</select> <br />
							</fieldset>
							<fieldset>
							<legend>Previous/Existing practice office</legend>
								<label for="form_ac_s20_preAddrs1">Address line 1</label>
								<input type="text" id="form_ac_s20_preAddrs1" name="MAT_Address_Street1" class="conditionalCheckChangeMAT" />  <br />
								<label for="form_ac_s20_preAddrS2">Address line 2</label>
								<input type="text" id="form_ac_s20_preAddrS2" name="MAT_Address_Street2" />  <br />
								<label for="form_ac_s20_preCity">City</label>
								<input type="text" id="form_ac_s20_preCity" name="MAT_City" class="conditionalCheckChangeMAT" />  <br />
								<label for="form_ac_s20_preState">State</label>
								<input type="text" id="form_ac_s20_preState" name="MAT_State" class="conditionalCheckChangeMAT" />  <br />
								<label for="form_ac_s20_preZip">Zip</label>
								<input type="text" id="form_ac_s20_preZip" name="MAT_Zip" class="conditionalCheckChangeMAT" />  <br />
							</fieldset>
						</div> <!-- END of form_tog20: the twentieth Section Body -->	
					</li> <!-- END of tenth Form Section -->
					<li id="form_slist8" class="pf_tab2 pf_tab3 pf_section"> <!-- form_slist8 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle9" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Change your Tax Identification Number (TIN) or ownership of group practice <em>(W-9 required)</em> </span>
						</h2>
						<div class="toggler"> <!-- Start of the FORM elements for one Section -->
							<fieldset>
							<legend>Practice details</legend>
								<label for="form_ac_s8_newpracname">Updated/New practice name</label>
								<input type="text" id="form_ac_s8_newpracname" name="Updated_Practice_Name" />  <br />
								<label for="form_ac_s8_newTIN">Updated/New individual Tax Identification Number (TIN)</label>
								<input type="text" id="form_ac_s8_newTIN" name="Individual_Tax_Identification_Number" class="TIN"/>  <br />
								<label for="form_ac_s8_TIN">Updated/New organizational Tax Identification Number (TIN)</label>
								<input type="text" id="form_ac_s8_TIN"  name="Organizational_Tax_Identification_Number" class="TIN"/>  <br />
								<label for="form_ac_s8_preNPI">Updated/New individual National Provider Identifier (NPI) Number</label>
								<input type="text" id="form_ac_s8_preNPI"  name="New_Ind_National_Provider_Identification_Number" class="NPI"/>  <br />
								<label for="form_ac_s8_newNPI">Updated/New organizational National Provider Identifier (NPI) Number</label>
								<input type="text" id="form_ac_s8_newNPI" name="New_Org_National_Provider_Identification_Number" class="NPI"/>  <br />
								<label for="form_ac_s8_newOfficeFax">Practice office fax number</label>
								<input type="text" id="form_ac_s8_newOfficeFax" name="New_Office_Fax"/>  <br />
                                <label for="form_ac_s8_newOfficePhone">Practice office phone number</label>
								<input type="text" id="form_ac_s8_newOfficePhone" name="New_Office_Phone_Number"/>  <br />
                                <label for="form_ac_s8_priEmail">Primary email address</label>
								<input type="text" id="form_ac_s8_priEmail" name="Primary_Email_Address" class="email"/>  <br />
							<!--	<label for="form_ac_s8_pAffiliation">IPA or PHO affiliation</label>
								<input type="text" id="form_ac_s8_pAffiliation" name="IPA_PHO_Affiliation" /> <br /> -->
                                <label>Attachments</label>
                                <input type="checkbox" id="Attachments" name="Attachments" value="Yes" style="width:auto" class="requireAttachment"/>
								<p><em>*If your change requires a new W-9 form, please attach the completed W-9 to this electronic form.</em></p>
							</fieldset>				
						</div> <!-- END of form_tog9: the Nineth Section Body -->	
					</li> <!-- END of Nineth Form Section -->
					<li id="form_slist10" class="pf_tab1 pf_tab3 pf_section"> <!-- form_slist10 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle11" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Update or add your Billing NPI</span>
						</h2>
						<div class="toggler"> <!-- Start of the FORM elements for one Section -->
							
							<fieldset>
								<label for="form_ac_s10_pGNPI">Type 2 or Organizational National Provider Identifier (NPI)</label>
								<input type="text" id="form_ac_s10_pGNPI" name="s10_Group_NPI" class="NPI"/>  <br />
							</fieldset>
							<fieldset>
							<legend>Previous/Existing practice office</legend>
								<label for="form_ac_s10_preAddrs1">Address line 1</label>
								<input type="text" id="form_ac_s10_preAddrs1" name="Address_Street1" />  <br />
								<label for="form_ac_s10_preAddrS2">Address line 2</label>
								<input type="text" id="form_ac_s10_preAddrS2" name="Address_Street2" />  <br />
								<label for="form_ac_s10_preCity">City</label>
								<input type="text" id="form_ac_s10_preCity" name="City" />  <br />
								<label for="form_ac_s10_preState">State</label>
								<input type="text" id="form_ac_s10_preState" name="State" />  <br />
                                <label for="form_ac_s10_preCounty">County</label>
								<input type="text" id="form_ac_s10_preCounty" name="County" />  <br />
								<label for="form_ac_s10_preZip">Zip</label>
								<input type="text" id="form_ac_s10_preZip" name="Zip" />  <br />
							</fieldset>
						</div> <!-- END of form_tog10: the eleventh Section Body -->	
					</li> <!-- END of eleventh Form Section -->
					
					<li id="form_slist11" class="pf_tab1 pf_tab3 pf_section"> <!-- form_slist10 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle12" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Update or add your email address</span>
						</h2>
						<div class="toggler"> <!-- Start of the FORM elements for one Section -->
							
							<fieldset>
								<legend>Primary email address</legend>
								
								<label for="form_ac_s11_priEmail">Email address</label>
								<input type="text" id="form_ac_s11_priEmail" name="Primary_Email_Address" class="email" value=""/>  <br />
								<label for="form_ac_s11_preAddrs1">Address line 1</label>
								<input type="text" id="form_ac_s11_preAddrs1" name="Address_Street1" />  <br />
								<label for="form_ac_s11_preAddrS2">Address line 2</label>
								<input type="text" id="form_ac_s11_preAddrS2" name="Address_Street2" />  <br />
								<label for="form_ac_s11_preCity">City</label>
								<input type="text" id="form_ac_s11_preCity" name="City" />  <br />
								<label for="form_ac_s11_preState">State</label>
								<input type="text" id="form_ac_s11_preState" name="State" />  <br />
                                <label for="form_ac_s11_preCounty">County</label>
								<input type="text" id="form_ac_s11_preCounty" name="County" />  <br />
								<label for="form_ac_s11_preZip">Zip</label>
								<input type="text" id="form_ac_s11_preZip" name="Zip" />  <br />
							</fieldset>
						</div> <!-- END of form_tog11: the twelveth Section Body -->	
					</li> <!-- END of twelveth Form Section -->	
					
					<li id="form_slist15" class="pf_tab2 pf_tab3 pf_section"> <!-- form_slist14 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle16" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Termination of your Provider Participation Agreement</span> 
						</h2>
						<div class="toggler"> <!-- Start of the FORM elements for one Section -->
							
							<fieldset>
								<label for="form_ac_s15_priEmail">Primary email address</label>
								<input type="text" id="form_ac_s15_priEmail" name="Primary_Email_Address" class="email"/>  <br />
								<label for="s15_tReason">Reason for termination</label>
								<input type="text" id="s15_tReason" name="Reason_For_Termination" />  <br />
								<p><em>“Empire requires 60 days prior notification if a provider chooses to terminate from Empire’s networks. Please be aware that your Empire Participating Practitioner Agreement requires you to continue to provide medically appropriate care to those Empire members who are in an active course of treatment on the date your network participation terminates.  Specifically, you are required to continue to treat these members until medically appropriate discharge or transfer, or completion of the course of treatment, or 90 days of continuation of care,  whichever occurs first.  This is to ensure that the member’s care is not compromised as a result of the change in your participation status.”</em></p>
							</fieldset>
						</div> <!-- END of form_tog15: the Sixteenth Section Body -->	
					</li> <!-- END of Sixteenth Form Section -->
					<li id="form_slist13" class="pf_tab2 pf_tab3 pf_section"> <!-- form_slist15 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle14" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Add or terminate PT, OT, ST, or audiologist to or from existing ancillary contracted group (Ancillary providers only)</span>
						</h2>
						<div class="toggler"> <!-- Start of the FORM elements for one Section -->
							
							<fieldset>
							<legend>Provider details</legend>
								<label>Request Type</label>
									<input type="radio" name="s13RequestType" value="Add" id="s13RequestAdd" style="width:auto" /><label for="s13RequestAdd"  style="width:auto;margin-right:5px;" class="exclude">Add</label>
									<input type="radio" name="s13RequestType" value="Delete"  id="s13RequestDelete" style="width:auto"/><label for="s13RequestDelete" style="width:auto;margin-right:5px;" class="exclude">Delete</label><br />

                                <label for="form_ac_s13_ppspecialty">Provider primary specialty</label>
								<select id="form_ac_s13_ppspecialty" name="s13Primary_Specialty">
									<option selected></option>
									<option value="Acupuncture">Acupuncture</option>
									<option value="Occupational Therapy">Occupational Therapy</option>
									<option value="Physical Therapy">Physical Therapy</option>
									<option value="Registered Dietitian">Registered Dietitian</option>
									<option value="Speech Therapy">Speech Therapy</option>
								</select> <br />
								<label for="form_ac_s13_psspecialty">Provider secondary specialty </label>
								<select id="form_ac_s13_psspecialty" name="s13Secondary_Specialty">
									<option selected></option>
									<option value="Acupuncture">Acupuncture</option>
									<option value="Occupational Therapy">Occupational Therapy</option>
									<option value="Physical Therapy">Physical Therapy</option>
									<option value="Registered Dietitian">Registered Dietitian</option>
									<option value="Speech Therapy">Speech Therapy</option>
								</select> <br />
								<label for="form_ac_s13_license">Do you hold a current, active and unrestricted  license in the state in which you are applying for participation?</label>
								<select id="form_ac_s13_license" name="Current_Active_Unrestricted_License">
									<option selected></option>
									<option value="Yes">Yes</option>
									<option value="No">No</option>
								</select> <br />
							</fieldset>
							<fieldset>
								<legend>Previous/Existing practice office</legend>
								<label for="form_ac_s13_preAddrS1">Address line 1</label>
								<input type="text" id="form_ac_s13_preAddrS1" name="Address_Street1" />  <br />
								<label for="form_ac_s13_preAddrS2">Address line 2</label>
								<input type="text" id="form_ac_s13_preAddrS2" name="Address_Street2" />  <br />
								<label for="form_ac_s13_preCity">City</label>
								<input type="text" id="form_ac_s13_preCity" name="City" />  <br />
								<label for="form_ac_s13_preState">State</label>
								<input type="text" id="form_ac_s13_preState" name="State" />  <br />
								<label for="form_ac_s13_preZip">Zip</label>
								<input type="text" id="form_ac_s13_preZip" name="Zip" />  <br />
								<label for="form_ac_s13_preCounty">County</label>
								<input type="text" id="form_ac_s13_preCounty" name="County" />  <br />
                                <label for="form_ac_s13_prePhone">Office phone number</label>
								<input type="text" id="form_ac_s13_prePhone" name="Phone" />  <br />
                                <label for="form_ac_s13_prefax">Office fax number</label>
								<input type="text" id="form_ac_s13_prefax" name="Fax" />  <br />
                                <label for="form_ac_s13_preemail">Email</label>
								<input type="text" id="form_ac_s13_preemail" name="Email" />  <br />
							</fieldset>
							<fieldset>
								<label for="s13_tReason">Reason for termination</label>
								<input type="text" id="s13_tReason" name="Reason_For_Termination" />  <br />
							</fieldset>
						</div> <!-- END of form_tog16: the fourteenth Section Body -->	
					</li> <!-- END of fourteenth Form Section -->
					<li id="form_slist12" class="pf_tab2 pf_tab3 pf_section"> <!-- form_slist11 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle13" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Add or change provider's areas of expertise (behavioral health providers only)</span>
						</h2>
						<div class="toggler"> <!-- Start of the FORM elements for one Section -->
							<fieldset>
							<legend>Provider Expertise</legend>
								<label>Request Type</label>
								<input type="radio" name="s12RequestType" value="Add" id="s12RequestAdd" style="width:auto" /><label for="RequestAdd"  style="width:auto;margin-right:5px;" class="exclude">Add</label>
								<input type="radio" name="s12RequestType" value="Delete"  id="s12RequestDelete" style="width:auto"/><label for="RequestDelete" style="width:auto;margin-right:5px;" class="exclude">Delete</label><br />
                                <label for="form_ac_s12pExpertise">Provider's self-reported area of expertise</label>
								<select id="form_ac_s12pExpertise" name="Provider_Self_Reported_Expertise_Area">
									<option selected></option>
									<option value="Adoption">Adoption</option>
									<option value="Anxiety and Panic Disorders">Anxiety and Panic Disorders</option>
									<option value="Attention Deficit Disorder/ADHD">Attention Deficit Disorder/ADHD</option>
									<option value="Autism Spectrum Disorder (Autism/PDD/Asperger’s)">Autism Spectrum Disorder (Autism/PDD/Asperger’s)</option>
									<option value="Bariatric Assessment">Bariatric Assessment</option>
									<option value="Behavior Modification">Behavior Modification</option>
                                    <option value="Behavioral Therapy for Autism Spectrum Disorders">Behavioral Therapy for Autism Spectrum Disorders</option>
									<option value="Bipolar Disorder/Manic Depressive">Bipolar Disorder/Manic Depressive</option>
									<option value="Brief Solution Focused">Brief Solution Focused</option>
									<option value="Chemical Dependency">Chemical Dependency</option>
									<option value="Christian Counseling">Christian Counseling</option>
                                    <option value="Cognitive Behavioral Therapy (CBT)">Cognitive Behavioral Therapy (CBT)</option>
									<option value="Compulsive Gambling Therapy">Compulsive Gambling</option>
									<option value="Counseling for Opioid Use Disorders">Counseling for Opioid Use Disorders</option>
									<option value="Cultural/Ethnic Issues">Cultural/Ethnic Issues</option>
									<option value="Depression">Depression</option>
									<option value="Dialectical Behavioral Therapy (DBT)">Dialectical Behavioral Therapy (DBT)</option>
									<option value="Divorce/Blended Family Issues">Divorce/Blended Family Issues</option>
									<option value="Eating Disorders">Eating Disorders</option>
									<option value="Electroconvulsive Therapy">Electroconvulsive Therapy</option>
                                    <option value="End of Life Issues">End of Life Issues</option>
									<option value="Family Therapy">Family Therapy</option>
                                    <option value="Gay/Lesbian/Bisexual Issues">Gay/Lesbian/Bisexual Issues</option>
                                    <option value="Geriatrics">Geriatrics</option>
                                    <option value="Group Therapy">Group Therapy</option>
									<option value="HIV/AIDS Related Issues">HIV/AIDS Related Issues</option>
                                    <option value="Infertility">Infertility</option>
                                    <option value="MAT for Opioid Use Disorders">MAT for Opioid Use Disorders</option>
									<option value="MAT Waivered Prescriber">MAT Waivered Prescriber</option>
									<option value="Medication Management">Medication Management</option>
                                    <option value="Men Issues">Men Issues</option>
									<option value="Neuropsychological Testing">Neuropsychological Testing</option>
                                    <option value="Obsessive Compulsive Disorder">Obsessive Compulsive Disorder</option>
									<option value="Pain Management">Pain Management</option>
                                    <option value="Personality Disorders">Personality Disorders</option>
									<option value="Post Traumatic Stress Disorder (PTSD)">Post Traumatic Stress Disorder (PTSD)</option>
									<option value="Postpartum Issues">Postpartum Issues</option>
                                    <option value="Prenatal Issues">Prenatal Issues</option>
									<option value="Psychological Testing ">Psychological Testing </option>
                                    <option value="Schizophrenic Disorders">Schizophrenic Disorders</option>
									<option value="Sexual Disorders">Sexual Disorders</option>
                                    <option value="Transgender Issues">Transgender Issues</option>
									<option value="Women Issues">Women Issues</option>
								<select> <br />
								<label>Request Type</label>
								<input type="radio" name="s12bRequestType" value="Add" id="s12bRequestAdd" style="width:auto" /><label for="RequestAdd"  style="width:auto;margin-right:5px;" class="exclude">Add</label>
								<input type="radio" name="s12bRequestType" value="Delete"  id="s12bRequestDelete" style="width:auto"/><label for="RequestDelete" style="width:auto;margin-right:5px;" class="exclude">Delete</label><br />
                                <label for="form_ac_s12bpExpertise">Provider's self-reported area of expertise</label>
								<select id="form_ac_s12bpExpertise" name="Provider_Self_Reported_Expertise_Area">
									<option selected></option>
									<option value="Adoption">Adoption</option>
									<option value="Anxiety and Panic Disorders">Anxiety and Panic Disorders</option>
									<option value="Attention Deficit Disorder/ADHD">Attention Deficit Disorder/ADHD</option>
									<option value="Autism Spectrum Disorder (Autism/PDD/Asperger’s)">Autism Spectrum Disorder (Autism/PDD/Asperger’s)</option>
									<option value="Bariatric Assessment">Bariatric Assessment</option>
									<option value="Behavior Modification">Behavior Modification</option>
                                    <option value="Behavioral Therapy for Autism Spectrum Disorders">Behavioral Therapy for Autism Spectrum Disorders</option>
									<option value="Bipolar Disorder/Manic Depressive">Bipolar Disorder/Manic Depressive</option>
									<option value="Brief Solution Focused">Brief Solution Focused</option>
									<option value="Chemical Dependency">Chemical Dependency</option>
									<option value="Christian Counseling">Christian Counseling</option>
                                    <option value="Cognitive Behavioral Therapy (CBT)">Cognitive Behavioral Therapy (CBT)</option>
									<option value="Compulsive Gambling Therapy">Compulsive Gambling</option>
									<option value="Counseling for Opioid Use Disorders">Counseling for Opioid Use Disorders</option>
									<option value="Cultural/Ethnic Issues">Cultural/Ethnic Issues</option>
									<option value="Depression">Depression</option>
									<option value="Dialectical Behavioral Therapy (DBT)">Dialectical Behavioral Therapy (DBT)</option>
									<option value="Divorce/Blended Family Issues">Divorce/Blended Family Issues</option>
									<option value="Eating Disorders">Eating Disorders</option>
									<option value="Electroconvulsive Therapy">Electroconvulsive Therapy</option>
                                    <option value="End of Life Issues">End of Life Issues</option>
									<option value="Family Therapy">Family Therapy</option>
                                    <option value="Gay/Lesbian/Bisexual Issues">Gay/Lesbian/Bisexual Issues</option>
                                    <option value="Geriatrics">Geriatrics</option>
                                    <option value="Group Therapy">Group Therapy</option>
									<option value="HIV/AIDS Related Issues">HIV/AIDS Related Issues</option>
                                    <option value="Infertility">Infertility</option>
                                    <option value="MAT for Opioid Use Disorders">MAT for Opioid Use Disorders</option>
                                    <option value="MAT Waivered Prescriber">MAT Waivered Prescriber</option>
									<option value="Medication Management">Medication Management</option>
                                    <option value="Men Issues">Men Issues</option>
									<option value="Neuropsychological Testing">Neuropsychological Testing</option>
                                    <option value="Obsessive Compulsive Disorder">Obsessive Compulsive Disorder</option>
									<option value="Pain Management">Pain Management</option>
                                    <option value="Personality Disorders">Personality Disorders</option>
									<option value="Post Traumatic Stress Disorder (PTSD)">Post Traumatic Stress Disorder (PTSD)</option>
									<option value="Postpartum Issues">Postpartum Issues</option>
                                    <option value="Prenatal Issues">Prenatal Issues</option>
									<option value="Psychological Testing ">Psychological Testing </option>
                                    <option value="Schizophrenic Disorders">Schizophrenic Disorders</option>
									<option value="Sexual Disorders">Sexual Disorders</option>
                                    <option value="Transgender Issues">Transgender Issues</option>
									<option value="Women Issues">Women Issues</option>
								<select> <br />
							</fieldset>
						</div> <!-- END of form_tog12: the thirteenth Section Body -->	
					</li> <!-- END of thirteenth Form Section -->
					
					<li id="form_slist14" class="pf_tab2 pf_tab3 pf_section"> <!-- form_slist12 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle15" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Add or change your provider specialty or type (change may require provider to be credentialed)</span>
						</h2>
						<div class="toggler"> <!-- Start of the FORM elements for one Section -->
							<fieldset>
                            <label>Request Type</label>
								<input type="radio" name="s14RequestType" value="Add" id="s14pRequestAdd" style="width:auto" /><label for="RequestAdd"  style="width:auto;margin-right:5px;" class="exclude">Add</label>
								<input type="radio" name="s14RequestType" value="Change"  id="s14RequestChange" style="width:auto"/><label for="RequestDelete" style="width:auto;margin-right:5px;" class="exclude">Change</label><br />
                            </fieldset>   
							<fieldset>
							<legend>Provider details</legend>
								<label for="form_ac_s14pType">Provider type</label>
								<select id="form_ac_s14pType" name="s14Provider_Type">
									<option selected></option>
									<option value="Acupuncturist">Acupuncturist</option>
									<option value="Audiologist">Audiologist</option>
									<option value="Certified Registered Nurse Anesthetist">Certified Registered Nurse Anesthetist</option>
									<option value="Clinical Nurse Specialist">Clinical Nurse Specialist</option>
									<option value="Clinical Psychologist">Clinical Psychologist</option>
									<option value="Clinical Social Worker">Clinical Social Worker</option>
									<option value="Dietitian">Dietitian</option>
									<option value="Doctor of Chiropractic (DC)">Doctor of Chiropractic (DC)</option>
									<option value="Doctor of Dental Medicine (DMD)">Doctor of Dental Medicine (DMD)</option>
									<option value="Doctor of Dental Surgery (DDS)">Doctor of Dental Surgery (DDS)</option>
									<option value="Doctor of Podiatric Medicine (DPM)">Doctor of Podiatric Medicine (DPM)</option>
									<option value="Licensed Practical Nurse">Licensed Practical Nurse</option>
									<option value="Marriage/Family Therapist">Marriage/Family Therapist</option>
									<option value="Medical Doctor (MD)">Medical Doctor (MD)</option>
									<option value="Midwife">Midwife</option>
									<option value="Naturopath">Naturopath</option>
									<option value="Nurse Midwife">Nurse Midwife</option>
									<option value="Nurse Practitioner">Nurse Practitioner</option>
									<option value="Nutritionist">Nutritionist</option>
									<option value="Occupational Therapist">Occupational Therapist</option>
									<option value="Optometrist">Optometrist</option>
									<option value="Osteopathic Doctor (DO)">Osteopathic Doctor (DO)</option>
									<option value="Physical Therapist">Physical Therapist</option>
									<option value="Physician Assistant">Physician Assistant</option>
									<option value="Professional Counselor">Professional Counselor</option>
									<option value="Registered Nurse">Registered Nurse</option>
									<option value="Registered Nurse First Assistant">Registered Nurse First Assistant</option>
									<option value="Respiratory Care Practitioner">Respiratory Care Practitioner</option>
									<option value="Speech Therapist">Speech Therapist</option>
									<option value="Other">Other (please enter below)</option>
								</select><br />
								<label>Other</label><input type="text" name="s14Provider_Type_Other" /><br />

								<label for="form_ac_s14_ppspecialty">Provider primary specialty </label>
								<select id="form_ac_s14_ppspecialty" name="s14Primary_Specialty">
									<option selected></option>
									<option value="Adolescent Gynecology">Adolescent Gynecology</option>
									<option value="Adolescent Psychiatry">Adolescent Psychiatry</option>
									<option value="Allergy & Immunology">Allergy & Immunology</option>
									<option value="Ambulance">Ambulance</option>
									<option value="Ambulatory Surgery">Ambulatory Surgery</option>
									<option value="Anorectal Surgery">Anorectal Surgery</option>
									<option value="Audiology">Audiology</option>
									<option value="Breast Surgery">Breast Surgery</option>
									<option value="Cardiac Catheterization">Cardiac Catheterization</option>
									<option value="Cardiac Surgery">Cardiac Surgery</option>
									<option value="Cardiology">Cardiology</option>
									<option value="Cardiovascular Thoracic Surgery">Cardiovascular Thoracic Surgery</option>
									<option value="Cataracts Specialty">Cataracts Specialty</option>
									<option value="Certified Diabetes Educator">Certified Diabetes Educator</option>
									<option value="Certified Laboratory">Certified Laboratory</option>
									<option value="Certified Licensed Acupuncturist">Certified Licensed Acupuncturist</option>
									<option value="Certified Nurse Midwife">Certified Nurse Midwife</option>
									<option value="Certified Orthotics and Prosthetics Co">Certified Orthotics and Prosthetics Co</option>
									<option value="Child Psychiatry">Child Psychiatry</option>
									<option value="Chiropractic">Chiropractic</option>
									<option value="Clinical Psychologist">Clinical Psychologist</option>
									<option value="Colon and Rectal Surgery">Colon and Rectal Surgery</option>
									<option value="Corneal Disorders">Corneal Disorders</option>
									<option value="Dermatological Surgery">Dermatological Surgery</option>
									<option value="Dermatology">Dermatology</option>
									<option value="Diagnostic Radiology">Diagnostic Radiology</option>
									<option value="Dietitian">Dietitian</option>
									<option value="Diplomate in Podiatric Surgery">Diplomate in Podiatric Surgery</option>
									<option value="Durable Medical Equipment">Durable Medical Equipment</option>
									<option value="Echocardiography">Echocardiography</option>
									<option value="EKG Interpretation">EKG Interpretation</option>
									<option value="Endocrinology">Endocrinology</option>
									<option value="Endocrinology Obstetrics">Endocrinology Obstetrics</option>
									<option value="Family Practice - Board Certified">Family Practice - Board Certified</option>
									<option value="Family Practice - Board Eligible">Family Practice - Board Eligible</option>
									<option value="Gastroenterology">Gastroenterology</option>
									<option value="General Practice">General Practice</option>
									<option value="General Surgery">General Surgery</option>
									<option value="Genetics Adults">Genetics Adults</option>
									<option value="Geriatrics">Geriatrics</option>
									<option value="Glaucoma">Glaucoma</option>
									<option value="Gynecologic Oncology">Gynecologic Oncology</option>
									<option value="Gynecology">Gynecology</option>
									<option value="Hand Surgery">Hand Surgery</option>
									<option value="Head & Neck Surgery">Head & Neck Surgery</option>
									<option value="Hematology">Hematology</option>
									<option value="High Risk Obstetrics">High Risk Obstetrics</option>
									<option value="Home Infusion">Home Infusion</option>
									<option value="Infectious Diseases">Infectious Diseases</option>
									<option value="Internal Medicine Board Certified">Internal Medicine Board Certified</option>
									<option value="Internal Medicine Board Eligible">Internal Medicine Board Eligible</option>
									<option value="Internal Medicine Geriatrics">Internal Medicine Geriatrics</option>
									<option value="Interventional Radiology">Interventional Radiology</option>
									<option value="Licensed Marriage and Family Therapist">Licensed Marriage and Family Therapist</option>
									<option value="Licensed Chemical Dependency Counselor">Licensed Chemical Dependency Counselor</option>
									<option value="Licensed Professional Counselor">Licensed Professional Counselor</option>
									<option value="Licensed Psychiatric Agency">Licensed Psychiatric Agency</option>
									<option value="Mammography">Mammography</option>
									<option value="Maxillofacial Surgery">Maxillofacial Surgery</option>
									<option value="Medical Oncology">Medical Oncology</option>
									<option value="Multispecialty Group">Multispecialty Group</option>
									<option value="Neonatology">Neonatology</option>
									<option value="Nephrology">Nephrology</option>
									<option value="Neuro Ophthalmology">Neuro Ophthalmology</option>
									<option value="Neurology">Neurology</option>
									<option value="Neuroradiology">Neuroradiology</option>
									<option value="Neurosurgery">Neurosurgery</option>
									<option value="Nuclear Cardiology">Nuclear Cardiology</option>
									<option value="Nuclear Medicine Radiology">Nuclear Medicine Radiology</option>
									<option value="Nurse Practitioner">Nurse Practitioner</option>
									<option value="Nutritional Medicine">Nutritional Medicine</option>
									<option value="Obstetrics & Gynecology">Obstetrics & Gynecology</option>
									<option value="Occupational Therapy">Occupational Therapy</option>
									<option value="Ocular & Orbital Tumors">Ocular & Orbital Tumors</option>
									<option value="Oncology">Oncology</option>
									<option value="Ophthalmology">Ophthalmology</option>
									<option value="Ophthalmic Plastic">Ophthalmic Plastic</option>
									<option value="Oral and Maxillofacial Surgery">Oral and Maxillofacial Surgery</option>
									<option value="Oral Surgery">Oral Surgery</option>
									<option value="Orthopedic Surgery">Orthopedic Surgery</option>
									<option value="Otolaryngology">Otolaryngology</option>
									<option value="Pain Management">Pain Management</option>
									<option value="Pastoral Counselor">Pastoral Counselor</option>
									<option value="Pediatric Adolescent Medicine">Pediatric Adolescent Medicine</option>
									<option value="Pediatric Allergy & Immunology">Pediatric Allergy & Immunology</option>
									<option value="Pediatric Behavioral Disorders">Pediatric Behavioral Disorders</option>
									<option value="Pediatric Cardiology">Pediatric Cardiology</option>
									<option value="Pediatric Cardiothoracic Surgery">Pediatric Cardiothoracic Surgery</option>
									<option value="Pediatric Critical Care ">Pediatric Critical Care </option>
									<option value="Pediatric Dermatology">Pediatric Dermatology</option>
									<option value="Pediatric Develop Disorders">Pediatric Develop Disorders</option>
									<option value="Pediatric Endocrinology">Pediatric Endocrinology</option>
									<option value="Pediatric Gastroenterology">Pediatric Gastroenterology</option>
									<option value="Pediatric Genetics">Pediatric Genetics</option>
									<option value="Pediatric Hematology Oncology">Pediatric Hematology Oncology</option>
									<option value="Pediatric Infectious Diseases">Pediatric Infectious Diseases</option>
									<option value="Pediatric Neonatal">Pediatric Neonatal</option>
									<option value="Pediatric Nephrology">Pediatric Nephrology</option>
									<option value="Pediatric Neurology">Pediatric Neurology</option>
									<option value="Pediatric Neurosurgery">Pediatric Neurosurgery</option>
									<option value="Pediatric Oncology">Pediatric Oncology</option>
									<option value="Pediatric Ophthalmology">Pediatric Ophthalmology</option>
									<option value="Pediatrics Orthopedics">Pediatrics Orthopedics</option>
									<option value="Pediatric Otolaryngology">Pediatric Otolaryngology</option>
									<option value="Pediatric Pulmonary Medicine">Pediatric Pulmonary Medicine</option>
									<option value="Pediatric Radiology">Pediatric Radiology</option>
									<option value="Pediatric Rheumatology">Pediatric Rheumatology</option>
									<option value="Pediatric Surgery">Pediatric Surgery</option>
									<option value="Pediatric Urology">Pediatric Urology</option>
									<option value="Pediatrics">Pediatrics</option>
									<option value="Perinatology High Risk Obstetrics">Perinatology High Risk Obstetrics</option>
									<option value="Peripheral Vascular Disease">Peripheral Vascular Disease</option>
									<option value="Physical Medicine & Rehabilitation">Physical Medicine & Rehabilitation</option>
									<option value="Physical Therapy">Physical Therapy</option>
									<option value="Plastic Reconstructive Surgery">Plastic Reconstructive Surgery</option>
									<option value="Podiatric Orthopedics">Podiatric Orthopedics</option>
									<option value="Podiatry">Podiatry</option>
									<option value="Proctology">Proctology</option>
									<option value="Psychiatric Neurology">Psychiatric Neurology</option>
									<option value="Psychiatric Nurse">Psychiatric Nurse</option>
									<option value="Psychiatry">Psychiatry</option>
									<option value="Psychologist Individual or Group">Psychologist Individual or Group</option>
									<option value="Pulmonary Medicine">Pulmonary Medicine</option>
									<option value="Radiation Oncology">Radiation Oncology</option>
									<option value="Radiology">Radiology</option>
									<option value="Reconstructive Surgery">Reconstructive Surgery</option>
									<option value="Registered Physical Occupational Therapist">Registered Physical Occupational Therapist</option>
									<option value="Reproductive Endocrinology">Reproductive Endocrinology</option>
									<option value="Retail Health Clinic">Retail Health Clinic</option>
									<option value="Rheumatology">Rheumatology</option>
									<option value="Roentgenology">Roentgenology</option>
									<option value="Sleep Disorders">Sleep Disorders</option>
									<option value="Social Worker">Social Worker</option>
									<option value="Speech Therapy">Speech Therapy</option>
									<option value="Sports Medicine">Sports Medicine</option>
									<option value="Surgical Critical Care">Surgical Critical Care</option>
									<option value="Surgical Oncology">Surgical Oncology</option>
									<option value="Therapeutic Roentgenology">Therapeutic Roentgenology</option>
									<option value="Therapeutically Certified Optometrist">Therapeutically Certified Optometrist</option>
									<option value="Transplant Surgery Liver Kidney">Transplant Surgery Liver Kidney</option>
									<option value="Urgent Care Center">Urgent Care Center</option>
									<option value="Urogynecology">Urogynecology</option>
									<option value="Urology">Urology</option>
									<option value="Vascular Surgery">Vascular Surgery</option>
									<option value="Other">Other (please enter below)</option>
								</select> <br />
								<label>Other</label><input type="text" name="s14_ppspecialty_Other" /><br />
								<label for="form_ac_s16_boardcert">Are you Board Certified ?</label>
								<select id="form_ac_s16_boardcert" name="Board_Certified">
									<option selected></option>
									<option value="Yes">Yes</option>
									<option value="No">No</option>
								</select> <br> 
								<label for="form_ac_s14_psspecialty">Provider secondary specialty </label>
								<select id="form_ac_s14_psspecialty" name="s14Secondary_Specialty">
									<option selected></option>
									<option value="Adolescent Gynecology">Adolescent Gynecology</option>
									<option value="Adolescent Psychiatry">Adolescent Psychiatry</option>
									<option value="Allergy & Immunology">Allergy & Immunology</option>
									<option value="Ambulance">Ambulance</option>
									<option value="Ambulatory Surgery">Ambulatory Surgery</option>
									<option value="Anorectal Surgery">Anorectal Surgery</option>
									<option value="Audiology">Audiology</option>
									<option value="Breast Surgery">Breast Surgery</option>
									<option value="Cardiac Catheterization">Cardiac Catheterization</option>
									<option value="Cardiac Surgery">Cardiac Surgery</option>
									<option value="Cardiology">Cardiology</option>
									<option value="Cardiovascular Thoracic Surgery">Cardiovascular Thoracic Surgery</option>
									<option value="Cataracts Specialty">Cataracts Specialty</option>
									<option value="Certified Diabetes Educator">Certified Diabetes Educator</option>
									<option value="Certified Laboratory">Certified Laboratory</option>
									<option value="Certified Licensed Acupuncturist">Certified Licensed Acupuncturist</option>
									<option value="Certified Nurse Midwife">Certified Nurse Midwife</option>
									<option value="Certified Orthotics and Prosthetics Co">Certified Orthotics and Prosthetics Co</option>
									<option value="Child Psychiatry">Child Psychiatry</option>
									<option value="Chiropractic">Chiropractic</option>
									<option value="Clinical Psychologist">Clinical Psychologist</option>
									<option value="Colon and Rectal Surgery">Colon and Rectal Surgery</option>
									<option value="Corneal Disorders">Corneal Disorders</option>
									<option value="Dermatological Surgery">Dermatological Surgery</option>
									<option value="Dermatology">Dermatology</option>
									<option value="Diagnostic Radiology">Diagnostic Radiology</option>
									<option value="Dietitian">Dietitian</option>
									<option value="Diplomate in Podiatric Surgery">Diplomate in Podiatric Surgery</option>
									<option value="Durable Medical Equipment">Durable Medical Equipment</option>
									<option value="Echocardiography">Echocardiography</option>
									<option value="EKG Interpretation">EKG Interpretation</option>
									<option value="Endocrinology">Endocrinology</option>
									<option value="Endocrinology Obstetrics">Endocrinology Obstetrics</option>
									<option value="Family Practice - Board Certified">Family Practice - Board Certified</option>
									<option value="Family Practice - Board Eligible">Family Practice - Board Eligible</option>
									<option value="Gastroenterology">Gastroenterology</option>
									<option value="General Practice">General Practice</option>
									<option value="General Surgery">General Surgery</option>
									<option value="Genetics Adults">Genetics Adults</option>
									<option value="Geriatrics">Geriatrics</option>
									<option value="Glaucoma">Glaucoma</option>
									<option value="Gynecologic Oncology">Gynecologic Oncology</option>
									<option value="Gynecology">Gynecology</option>
									<option value="Hand Surgery">Hand Surgery</option>
									<option value="Head & Neck Surgery">Head & Neck Surgery</option>
									<option value="Hematology">Hematology</option>
									<option value="High Risk Obstetrics">High Risk Obstetrics</option>
									<option value="Home Infusion">Home Infusion</option>
									<option value="Infectious Diseases">Infectious Diseases</option>
									<option value="Internal Medicine Board Certified">Internal Medicine Board Certified</option>
									<option value="Internal Medicine Board Eligible">Internal Medicine Board Eligible</option>
									<option value="Internal Medicine Geriatrics">Internal Medicine Geriatrics</option>
									<option value="Interventional Radiology">Interventional Radiology</option>
									<option value="Licensed Marriage and Family Therapist">Licensed Marriage and Family Therapist</option>
									<option value="Licensed Chemical Dependency Counselor">Licensed Chemical Dependency Counselor</option>
									<option value="Licensed Professional Counselor">Licensed Professional Counselor</option>
									<option value="Licensed Psychiatric Agency">Licensed Psychiatric Agency</option>
									<option value="Mammography">Mammography</option>
									<option value="Maxillofacial Surgery">Maxillofacial Surgery</option>
									<option value="Medical Oncology">Medical Oncology</option>
									<option value="Multispecialty Group">Multispecialty Group</option>
									<option value="Neonatology">Neonatology</option>
									<option value="Nephrology">Nephrology</option>
									<option value="Neuro Ophthalmology">Neuro Ophthalmology</option>
									<option value="Neurology">Neurology</option>
									<option value="Neuroradiology">Neuroradiology</option>
									<option value="Neurosurgery">Neurosurgery</option>
									<option value="Nuclear Cardiology">Nuclear Cardiology</option>
									<option value="Nuclear Medicine Radiology">Nuclear Medicine Radiology</option>
									<option value="Nurse Practitioner">Nurse Practitioner</option>
									<option value="Nutritional Medicine">Nutritional Medicine</option>
									<option value="Obstetrics & Gynecology">Obstetrics & Gynecology</option>
									<option value="Occupational Therapy">Occupational Therapy</option>
									<option value="Ocular & Orbital Tumors">Ocular & Orbital Tumors</option>
									<option value="Oncology">Oncology</option>
									<option value="Ophthalmology">Ophthalmology</option>
									<option value="Ophthalmic Plastic">Ophthalmic Plastic</option>
									<option value="Oral and Maxillofacial Surgery">Oral and Maxillofacial Surgery</option>
									<option value="Oral Surgery">Oral Surgery</option>
									<option value="Orthopedic Surgery">Orthopedic Surgery</option>
									<option value="Otolaryngology">Otolaryngology</option>
									<option value="Pain Management">Pain Management</option>
									<option value="Pastoral Counselor">Pastoral Counselor</option>
									<option value="Pediatric Adolescent Medicine">Pediatric Adolescent Medicine</option>
									<option value="Pediatric Allergy & Immunology">Pediatric Allergy & Immunology</option>
									<option value="Pediatric Behavioral Disorders">Pediatric Behavioral Disorders</option>
									<option value="Pediatric Cardiology">Pediatric Cardiology</option>
									<option value="Pediatric Cardiothoracic Surgery">Pediatric Cardiothoracic Surgery</option>
									<option value="Pediatric Critical Care ">Pediatric Critical Care </option>
									<option value="Pediatric Dermatology">Pediatric Dermatology</option>
									<option value="Pediatric Develop Disorders">Pediatric Develop Disorders</option>
									<option value="Pediatric Endocrinology">Pediatric Endocrinology</option>
									<option value="Pediatric Gastroenterology">Pediatric Gastroenterology</option>
									<option value="Pediatric Genetics">Pediatric Genetics</option>
									<option value="Pediatric Hematology Oncology">Pediatric Hematology Oncology</option>
									<option value="Pediatric Infectious Diseases">Pediatric Infectious Diseases</option>
									<option value="Pediatric Neonatal">Pediatric Neonatal</option>
									<option value="Pediatric Nephrology">Pediatric Nephrology</option>
									<option value="Pediatric Neurology">Pediatric Neurology</option>
									<option value="Pediatric Neurosurgery">Pediatric Neurosurgery</option>
									<option value="Pediatric Oncology">Pediatric Oncology</option>
									<option value="Pediatric Ophthalmology">Pediatric Ophthalmology</option>
									<option value="Pediatrics Orthopedics">Pediatrics Orthopedics</option>
									<option value="Pediatric Otolaryngology">Pediatric Otolaryngology</option>
									<option value="Pediatric Pulmonary Medicine">Pediatric Pulmonary Medicine</option>
									<option value="Pediatric Radiology">Pediatric Radiology</option>
									<option value="Pediatric Rheumatology">Pediatric Rheumatology</option>
									<option value="Pediatric Surgery">Pediatric Surgery</option>
									<option value="Pediatric Urology">Pediatric Urology</option>
									<option value="Pediatrics">Pediatrics</option>
									<option value="Perinatology High Risk Obstetrics">Perinatology High Risk Obstetrics</option>
									<option value="Peripheral Vascular Disease">Peripheral Vascular Disease</option>
									<option value="Physical Medicine & Rehabilitation">Physical Medicine & Rehabilitation</option>
									<option value="Physical Therapy">Physical Therapy</option>
									<option value="Plastic Reconstructive Surgery">Plastic Reconstructive Surgery</option>
									<option value="Podiatric Orthopedics">Podiatric Orthopedics</option>
									<option value="Podiatry">Podiatry</option>
									<option value="Proctology">Proctology</option>
									<option value="Psychiatric Neurology">Psychiatric Neurology</option>
									<option value="Psychiatric Nurse">Psychiatric Nurse</option>
									<option value="Psychiatry">Psychiatry</option>
									<option value="Psychologist Individual or Group">Psychologist Individual or Group</option>
									<option value="Pulmonary Medicine">Pulmonary Medicine</option>
									<option value="Radiation Oncology">Radiation Oncology</option>
									<option value="Radiology">Radiology</option>
									<option value="Reconstructive Surgery">Reconstructive Surgery</option>
									<option value="Registered Physical Occupational Therapist">Registered Physical Occupational Therapist</option>
									<option value="Reproductive Endocrinology">Reproductive Endocrinology</option>
									<option value="Retail Health Clinic">Retail Health Clinic</option>
									<option value="Rheumatology">Rheumatology</option>
									<option value="Roentgenology">Roentgenology</option>
									<option value="Sleep Disorders">Sleep Disorders</option>
									<option value="Social Worker">Social Worker</option>
									<option value="Speech Therapy">Speech Therapy</option>
									<option value="Sports Medicine">Sports Medicine</option>
									<option value="Surgical Critical Care">Surgical Critical Care</option>
									<option value="Surgical Oncology">Surgical Oncology</option>
									<option value="Therapeutic Roentgenology">Therapeutic Roentgenology</option>
									<option value="Therapeutically Certified Optometrist">Therapeutically Certified Optometrist</option>
									<option value="Transplant Surgery Liver Kidney">Transplant Surgery Liver Kidney</option>
									<option value="Urgent Care Center">Urgent Care Center</option>
									<option value="Urogynecology">Urogynecology</option>
									<option value="Urology">Urology</option>
									<option value="Vascular Surgery">Vascular Surgery</option>
									<option value="Other">Other (please enter below)</option>
								</select> <br />
								<label>Other</label><input type="text" name="s14_psspecialty_Other" /><br />
								<label for="form_ac_s15_boardcert">Are you Board Certified ?</label>
								<select id="form_ac_s15_boardcert" name="Board_Certified">
									<option selected></option>
									<option value="Yes">Yes</option>
									<option value="No">No</option>
								</select> <br> 
								<label for="form_ac_s14_pcp">Are you a Primary Care Provider(PCP)?</label>
								<select id="form_ac_s14_pcp" name="Primary_Care_Provider">
									<option selected></option>
									<option value="Yes">Yes</option>
									<option value="No">No</option>
								</select> <br />
								<p><em>*CAQH must be updated prior to these changes being requested.</em><p>
							</fieldset>
						</div> <!-- END of form_tog13: the fifteenth Section Body -->	
					</li> <!-- END of fifteenth Form Section -->
					
					<li id="form_slist17" class="pf_tab1 pf_tab3 pf_section"> <!-- form_slist17 is one Section: hidden or shown for each mail box -->
							<h2 id="form_togHandle18" class="pf_sectionHead" title="Click to Toggle Forms View">
								<span class="label">Add or change provider's language(s) spoken</span>
							</h2>
							<div class="toggler"> <!-- Start of the FORM elements for one Section -->
								<fieldset>
									<legend>Provider details</legend>
									<label>Request Type</label>
										<input type="radio" name="s17RequestType" value="Add" id="s17pRequestAdd" style="width:auto" /><label for="RequestAdd"  style="width:auto;margin-right:5px;" class="exclude">Add</label>
										<input type="radio" name="s17RequestType" value="Delete"  id="s17RequestDelete" style="width:auto"/><label for="RequestDelete" style="width:auto;margin-right:5px;" class="exclude">Delete</label><br />
									<label>Provider primary language spoken</label><input type="text" name="Provider_Language_Spoken"  /><br />
									<label>Request Type</label>
										<input type="radio" name="s17bRequestType" value="Add" id="s17bpRequestAdd" style="width:auto" /><label for="RequestAdd"  style="width:auto;margin-right:5px;" class="exclude">Add</label>
										<input type="radio" name="s17bRequestType" value="Delete"  id="s17bRequestDelete" style="width:auto"/><label for="RequestDelete" style="width:auto;margin-right:5px;" class="exclude">Delete</label><br />
									<label>Provider secondary language spoken</label><input type="text" name="Provider_Secondarty_Language_Spoken"  /><br />
									<label>Request Type</label>
										<input type="radio" name="s17cRequestType" value="Add" id="s17cpRequestAdd" style="width:auto" /><label for="RequestAdd"  style="width:auto;margin-right:5px;" class="exclude">Add</label>
										<input type="radio" name="s17cRequestType" value="Delete"  id="s17cRequestDelete" style="width:auto"/><label for="RequestDelete" style="width:auto;margin-right:5px;" class="exclude">Delete</label><br />
									<label>Provider other language spoken</label><input type="text" name="Provider_Other_Language_Spoken"  /><br />								
							</fieldset>
							
							<fieldset>
								<legend>Previous/Existing practice office</legend>
									<label for="form_s17_newAddrs1">Address line 1</label>
									<input type="text" id="form_s17_newAddrs1" name="Address_Street1" />  <br />
									<label for="form_s17_newAddrS2">Address line 2</label>
									<input type="text" id="form_s17_newAddrS2" name="Address_Street2" />  <br />
									<label for="form_s17newCity">City</label>
									<input type="text" id="form_s17newCity" name="City" />  <br />
									<label for="form_s17_newState">State</label>
									<input type="text" id="form_s17_newState" name="State" />  <br />
									<label for="form_s17_newZip">Zip</label>
									<input type="text" id="form_s17_newZip" name="Zip" />  <br />
									<label for="form_s17_newCounty">County</label>
									<input type="text" id="form_s17_newCounty" name="County" />  <br />
									<label for="form_s17_Email">Email address</label>
									<input type="text" id="form_s17_Email" name="Email" class="email"/>  <br />								
									<label for="form_s17_teleno">Office phone number</label>
									<input type="text" id="form_s17_teleno" name="Office_Phone_Number" />  <br />
									<label for="form_s17_pOffFax">Office fax number</label>
									<input type="text" id="form_s17_OffFax" name="Office_Fax_Number" /> <br />
								</fieldset>
														
							</div> <!-- END of form_tog18: the seventeenth Section Body -->	
					</li> <!-- END of seventeenth Form Section -->					
					
					<li id="form_slist18" class="pf_tab1 pf_tab3 pf_section"> <!-- form_slist18 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle19" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Add or change provider's hospital privilege(s)</span>
						</h2>
						<div class="toggler"> <!-- Start of the FORM elements for one Section -->
							<fieldset>
								<legend>Provider details</legend>
								<label>Request Type</label>
									<input type="radio" name="s18RequestType" value="Add" id="s18pRequestAdd" style="width:auto" /><label for="RequestAdd"  style="width:auto;margin-right:5px;" class="exclude">Add</label>
									<input type="radio" name="s18RequestType" value="Delete"  id="s18RequestDelete" style="width:auto"/><label for="RequestDelete" style="width:auto;margin-right:5px;" class="exclude">Delete</label><br />
								<label>Hospital privilege 1 </label><input type="text" name="Hospital_Prvilege_1"  /><br />
								<label>Request Type</label>
									<input type="radio" name="s18bRequestType" value="Add" id="s18bpRequestAdd" style="width:auto" /><label for="RequestAdd"  style="width:auto;margin-right:5px;" class="exclude">Add</label>
									<input type="radio" name="s18bRequestType" value="Delete"  id="s18bRequestDelete" style="width:auto"/><label for="RequestDelete" style="width:auto;margin-right:5px;" class="exclude">Delete</label><br />
								<label>Hospital privilege 2 </label><input type="text" name="Hospital_Prvilege_2"  /><br />
								<label>Request Type</label>
									<input type="radio" name="s18cRequestType" value="Add" id="s18cpRequestAdd" style="width:auto" /><label for="RequestAdd"  style="width:auto;margin-right:5px;" class="exclude">Add</label>
									<input type="radio" name="s18cRequestType" value="Delete"  id="s18cRequestDelete" style="width:auto"/><label for="RequestDelete" style="width:auto;margin-right:5px;" class="exclude">Delete</label><br />
								<label>Hospital privilege 3 </label><input type="text" name="Hospital_Prvilege_3"  /><br />								
								<label>Request Type</label>
									<input type="radio" name="s18dRequestType" value="Add" id="s18dpRequestAdd" style="width:auto" /><label for="RequestAdd"  style="width:auto;margin-right:5px;" class="exclude">Add</label>
									<input type="radio" name="s18dRequestType" value="Delete"  id="s18dRequestDelete" style="width:auto"/><label for="RequestDelete" style="width:auto;margin-right:5px;" class="exclude">Delete</label><br />
								<label>Hospital privilege 4 </label><input type="text" name="Hospital_Prvilege_4"  /><br />
								<p>(Note:  Only participating Empire hospitals will be added to the provider's file.)</p>														
						</fieldset>
						
						<fieldset>
							<legend>Previous/Existing practice office</legend>
								<label for="form_s18_newAddrs1">Address line 1</label>
								<input type="text" id="form_s18_newAddrs1" name="Address_Street1" />  <br />
								<label for="form_s18_newAddrS2">Address line 2</label>
								<input type="text" id="form_s18_newAddrS2" name="Address_Street2" />  <br />
								<label for="form_s18newCity">City</label>
								<input type="text" id="form_s18newCity" name="City" />  <br />
								<label for="form_s18_newState">State</label>
								<input type="text" id="form_s18_newState" name="State" />  <br />
								<label for="form_s18_newZip">Zip</label>
								<input type="text" id="form_s18_newZip" name="Zip" />  <br />
								<label for="form_s18_newCounty">County</label>
                                <input type="text" id="form_s18_newCounty" name="County" />  <br />
								<label for="form_s18_Email">Email address</label>
								<input type="text" id="form_s18_Email" name="Email" class="email"/>  <br />								
								<label for="form_s18_teleno">Office phone number</label>
								<input type="text" id="form_s18_teleno" name="Office_Phone_Number" />  <br />
                                <label for="form_s18_pOffFax">Office fax number</label>
								<input type="text" id="form_s18_OffFax" name="Office_Fax_Number" /> <br />
							</fieldset>
													
						</div> <!-- END of form_tog19: the eighteent Section Body -->	
					</li> <!-- END of eighteent Form Section -->
					
					
					<li id="form_slist16" class="pf_tab1 pf_tab3 pf_section"> <!-- form_slist17 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle17" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Change your practice or group name</span>
						</h2>
						<div class="toggler"> <!-- Start of the FORM elements for one Section -->
							<fieldset>
							<legend>Practice details</legend>
								<label for="form_ac_s16_newpracname">Updated/New practice name</label>
								<input type="text" id="form_ac_s16_newpracname" name="Updated_Practice_Name" />  <br />
								<label for="form_ac_s16_Addrs1">Address line 1</label>
								<input type="text" id="form_ac_s16_Addrs1" name="Address1" />  <br />
								<label for="form_ac_s16_AddrS2">Address line 2</label>
								<input type="text" id="form_ac_s16_AddrS2" name="Address2" />  <br />
								<label for="form_ac_s16_City">City</label>
								<input type="text" id="form_ac_s16_City" name="City" />  <br />
								<label for="form_ac_s16_State">State</label>
								<input type="text" id="form_ac_s16_State" name="State" />  <br />
								<label for="form_ac_s16_County">County</label>
								<input type="text" id="form_ac_s16_County" name="County" />  <br />
								<label for="form_ac_s16_Zip">Zip</label>
								<input type="text" id="form_ac_s16_Zip" name="Zip" />  <br />
							</fieldset>		
						</div> <!-- END of form_tog18: the seveneenth Section Body -->	
					</li> <!-- END of Seventeenth Form Section -->
				</ul> <!-- END of Form Section List -->
				</form>
				
				
				<form CLASS="provider_upload_form" METHOD="POST" action="/mwpmf/message/PDMControllerServlet" >
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
						<table WIDTH="100%">

						<p class="pf_bold">Please upload any documentation you wish to send to
						<% if (brand.equalsIgnoreCase("NYEBCBS")) { %>
						Empire BlueCross BlueShield
						<% } else if (brand.equalsIgnoreCase("NYEBC")) { %> 
						Empire BlueCross 
						<% } %>
						to support information entered on this form. You will be allowed to attach 
						MS Word, MS Excel, 'jpg', 'pdf', 'gif', 'txt' or 'csv' file types. There is 
						a combined limit of 10 MB for your attachments.</p>
		
						<tr>
						<td colspan="2">
							<table  width="100%" border="1"  bordercolor="" id="fileListTable">
								<input type="hidden" id="dltRowCounter" name="dltRowCounter">
								<input type="hidden" id="fileListSize" name="fileListSize" value="0">
								   <tr bgcolor="#A0A0A0">
										 <td >File Name</td>
										 <td >Size</td>
										 <td >Description of Attachment</td>
								   </tr>
							</table>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<table>
								<tr>
									<td width="20%" align = "right">Upload File</td>
									<td align = "left"><input type="file" name="fileContentData" id="fileContentData" class="required fileNameLength fileType" value="Browse" /></td>
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
						
						</div>						
					</li> <!-- END of ninteenth Form Section -->					
					
				</ul> <!-- END of Form Section List -->
				<p>By clicking on the tab marked "SUBMIT" below, I hereby request the above changes and certify that the foregoing 
				information is true and correct and that I am the named professional or am otherwise authorized to make this request 
				and certification on behalf of the named professional. </p>
				<p class="pf_bold">Please note: To avoid processing delays, please review this form before submitting to ensure that 
				all required areas have been completed and any attachments (W-9, Provider roster) have been uploaded.</p>
				<br />
				<div id="upload"></div>
				</form>

				
				<form CLASS="provider_submit_form" METHOD="POST" action="PDMControllerServlet">
					<input type="hidden" name="formUpdateAction" id="formUpdateAction" value="nysubmit"/> 
					<input type="hidden" name="toemail" value=""/>
				    <input type="hidden" name="body" id="body" value="body of email"/>     	    
				    <input type="hidden" name="Effective_Date" id="Effective_Date" value=""/>
				    <input type="hidden" name="Provider_Last_Name_Gen_Individ" id="Provider_Last_Name_Gen_Individ" value=""/>
					<input type="hidden" name="Provider_First_Name_Gen_Individ" id="Provider_First_Name_Gen_Individ" value=""/>
					<input type="hidden" name="Individual_Tax_Identification_Number_Gen_Individ" id="Individual_Tax_Identification_Number_Gen_Individ" value=""/>
					<input type="hidden" name="Individual_Provider_Identification_Number_Gen_Individ" id="Individual_Provider_Identification_Number_Gen_Individ" value=""/>
					<input type="hidden" name="Provider_Practice_Name_Gen_Prac" id="Provider_Practice_Name_Gen_Prac" value=""/>
					<input type="hidden" name="Practice_Tax_Identification_Number_Gen_Prac" id="Practice_Tax_Identification_Number_Gen_Prac" value=""/>
					<input type="hidden" name="Org_NPI_Gen_Prac" id="Org_NPI_Gen_Prac" value=""/>
					<input type="hidden" name="Contact_Person_Last_Name" id="Contact_Person_Last_Name" value=""/>
					<input type="hidden" name="Contact_Person_First_Name" id="Contact_Person_First_Name" value=""/>
					<input type="hidden" name="Email_Address" id="Email_Address" value=""/>
					<input type="hidden" name="Phone_Number" id="Phone_Number" value=""/>
					<input type="hidden" id="uploadFileName" name="uploadFileName" value=""/>
					<input type="hidden" id="uploadFileSize" name="uploadFileSize" value=""/>
					<input type="hidden" id="uploadFileComment" name="uploadFileComment" value=""/>
					<input id="submitFormBtn" name="submitFormBtn" class="pf_submit" type="submit" WIDTH="100" HEIGHT="25" ALT="Submit" value="Submit" />
					
			    </form>
				<div class="result"></div>
				
				</div> <!-- END of Tab Body -->
			</div> <!-- END of TabContainer -->
			
		</div> <!-- END of Content -->
		<div id="pf_footer">
		Services provided by Empire HealthChoice HMO, Inc. and/or Empire HealthChoice Assurance, Inc., licensees of the Blue Cross 
		and Blue Shield Association, an association of independent Blue Cross and Blue Shield plans. The Blue Cross and Blue Shield 
		names and symbols are registered marks of the Blue Cross and Blue Shield Association.
		</div>

	</div><!-- END of Wrapper -->

</body>
</html>
