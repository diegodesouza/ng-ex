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

<%
  String action = "vasubmit";

  boolean isNotSupported = false;
  String ua = request.getHeader("User-Agent"); 
  //check if IE is version 9 or 8 and if in compatibiilty view
  isNotSupported = (ua != null && 
		  ((ua.indexOf("MSIE 7.0") != -1) || (ua.indexOf("Trident/5.0") != -1) || (ua.indexOf("Trident/4.0") != -1)));
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
		<h2 id="pf_l_mainHeader" name="#Anthem">
			<a href="#Anthem">Welcome to Anthem Blue Cross and Blue Shield</a>
		</h2>
		<div id="pf_l_content"> <!-- pf_l_content conatins the whole content/forms -->
			<h1>Provider Maintenance Form</h1>
          <p class="pf_boldital"> The Provider Maintenance Form (PMF) is to be used by contracted Virginia physicians, practitioners, professionals, ancillary and behavioral health providers to request changes to their practice profiles with Anthem Blue Cross and Blue Shield.</font></p>
            <p>It is critical that our members receive accurate and current data related to provider availability. Changes to provider records that are affiliated with group contracts must be reported to and submitted by the practice manager or other designated person of authority at the group. Changes to individual contracts may be made at the direction of the contracted physician. All requests must be received by Anthem 30 days prior to the change/update. Any request received by Anthem less than 30 days prior to the change may be assigned a future effective date. Contract terms may also supersede the requested effective date. Submit the PMF to notify Anthem Blue Cross and Blue Shield of any changes to the provider/practice name, mailing/physical/remittance address, phone and fax numbers, practice office hours, provider joining the practice, practice accepting new patients, handicapped accessibility, specialties or languages offered.  </p>
            <p class="pf_boldital">Please follow these instructions when submitting the PMF:</p>
            <p>Complete all applicable sections.  The form has multiple options (+) for changes.  
                Complete only the sections applicable to the requested<br>change(s).  NOTE:  This form 
                will time out after 30 minutes of activity or inactivity and all entries made but not yet 
                submitted will be lost.</p>
            <p>
			<p>NOTE:  If other information such as your practice status, group affiliation, legal entity name or 
			federal taxpayer identification number has changed and/or a provider is joining or resigning, please 
			submit “current” and “after” details in writing on office letterhead. Send information to Provider 
			Network Manager for the practice location.  A listing of provider network managers and their 
			corresponding territories can be found  
			<a a href="#" onClick="javascript:openPopup('http://www.anthem.com/wps/portal/ahpprovider?content_path=provider/va/f5/s2/t0/pw_b147890.htm&label=Provider%20Representatives&state=va&rootLevel=3&state=va&rootLevel=3');return false;">here</a>.</p>			
			
			<div class="pf_l_msgContainer">
				<h2>Reason for Submitting this Form</h2>
				<div id="pf_msgOpt1" class="pf_msgOpt left cWidth60 pf_tab1">
                <span class="pf_bold">Option 1</span>
					<ul>
						<li>Change your Physical and Mailing  practice address or phone number</li>
						<li>Add a new location to your practice</li>
						<li>Close a practice location (only applicable if closing an entire building/site)</li>                        
						<li>Change your payment and remittance address</li>
						<li>Change your office hours or days of operation</li>                        
						<li>Update or add your NPI </li>
						<li>Update or add your practice email address</li>
						<li>Add or change provider's areas of expertise (Behavioral Health providers only)</li>
						<li>Add or remove PT, OT, ST or Audiology services from an existing location</li>
						<li>Add or change provider's language(s) spoken</li>						
						<li>Add a new location to an existing contracted PT, OT, ST therapy group (ancillary providers only)</li>
					</ul>
					<a href="#tab1" id="gotoTab1" class="gotoTab">CLICK HERE</a><span class="pf_bold"> to make one or more of the above changes.</span>
					<div class="clear"></div>
				</div>
				<div id="pf_msgOpt2" class="pf_msgOpt2 right cWidth30 pf_tab2">
                <span class="pf_bold">Option 2</span>
					<ul>
						<li>Add or change your provider specialty or type</li>
						<li>Change in your acceptance of new patients </li>
						<li>Change your practice or group name</li>
						<li>Change your Tax Identification Number (TIN) or ownership of group practice (W-9 Required)</li>
						<li>Name change for individual physician/practitioner</li>
						<li>Provider is leaving a group</li>                        
						<li>Remove a provider from a location</li>
						<li>Termination of your Provider Participation Agreement</li>
					</ul>
					<span class="pf_bold">To request Option 2 changes please contact the Anthem provider network manager for the practice location.  A listing of provider network managers and their corresponding territories can be found </span> <a a href="#" onClick="javascript:openPopup('http://www.anthem.com/wps/portal/ahpprovider?content_path=provider/va/f5/s2/t0/pw_b147890.htm&label=Provider%20Representatives&state=va&rootLevel=3&state=va&rootLevel=3');return false;">here</a>.
					<div class="clear"></div>
				</div>
				<div class="clear"></div>
				<!--
				<div id="pf_msgOpt3" class="pf_msgOpt clear pf_tab3">
				
                <p><span class="pf_bold">Option 3</span></p>
					<a href="#tab3" id="gotoTab3" class="gotoTab ">CLICK HERE</a><span class="pf_bold"> only if you need to make one or more changes in both Options 1 and 2</span>
				</div>
				-->
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
					<li id="form_slist0" class="pf_tab1 pf_section" > <!-- form_slist0 is one Section: hidden or shown for each mail box -->
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
                                <input type="text" name="Effective_Date"  class="required date"  onKeyDown="limitTextArea(this.form.Effective_Date,20);" onKeyUp="limitTextArea(this.form.Effective_Date,20);"/> <br />
							 </fieldset>
							
                            <fieldset id="provider_details_fldset" >
								<legend>Provider details</legend>
								<label>Last name<em>*</em></label><input type="text" id="Gen_Provider_Last_Name" name="Gen_Provider_Last_Name" class="required conditionalCheckProviderDetails" onKeyDown="limitTextArea(this.form.Gen_Provider_Last_Name,35);" onKeyUp="limitTextArea(this.form.Gen_Provider_Last_Name,35);"/><br />
                                <label>First name<em>*</em></label><input type="text" id="Gen_Provider_First_Name" name="Gen_Provider_First_Name" class="required conditionalCheckProviderDetails" onKeyDown="limitTextArea(this.form.Gen_Provider_First_Name,35);" onKeyUp="limitTextArea(this.form.Gen_Provider_First_Name,35);"/><br />
                                <label>Middle name</label><input type="text" id="Gen_Provider_Middle_Name" name="Gen_Provider_Middle_Name" class=""  /><br />
                                <label>Professional title</label><input type="text" id="Gen_Professional_Title" name="Gen_Professional_Title" class="" /><br />
                                <label>Suffix</label><input type="text" id="Provider_Suffix" name="Provider_Suffix" class=""  /><br />
                                <label>Individual Tax Identification Number (TIN)<em>*</em></label><input type="text" id="Gen_Ind_TIN" name="Gen_Ind_TIN" class="required TIN"/><br />
								<label>Individual National Provider Identifier (NPI)<em>*</em></label><input type="text" id="GenSec_Prov_Ded_NPI" name="GenSec_Prov_Ded_NPI" class="required NPI" /><br />
							</fieldset>
							
							<fieldset id="practice_details_fldset" >
								<legend>Practice details</legend>
								<label>Practice name<em>*</em></label><input type="text" id="Gem_Provider_Practice_Name"  name="Gem_Provider_Practice_Name" class="required" onKeyDown="limitTextArea(this.form.Gem_Provider_Practice_Name,55);" onKeyUp="limitTextArea(this.form.Gem_Provider_Practice_Name,55);" /><br />
								<label>Practice Tax Identification Number (TIN)<em>*</em></label><input type="text" id="Gen_Pract_TIN" name="Gen_Pract_TIN" class="required TIN" /><br />
								<label>Organizational National Provider Identifier (NPI)<em>*</em></label><input type="text" id="GenSec_Pract_Ded_NPI" name="GenSec_Pract_Ded_NPI" class="required NPI" /><br />
							</fieldset>
							
							<fieldset>
								<legend>Contact details</legend>
								<label>Contact person last name<em>*</em></label><input type="text" name="Contact_Person_Last_Name" class="required" onKeyDown="limitTextArea(this.form.Contact_Person_Last_Name,35);" onKeyUp="limitTextArea(this.form.Contact_Person_Last_Name,35);"/><br />
                                <label>Contact person first name<em>*</em></label><input type="text" name="Contact_Person_First_Name" class="required" onKeyDown="limitTextArea(this.form.Contact_Person_First_Name,35);" onKeyUp="limitTextArea(this.form.Contact_Person_First_Name,35);"/><br />
								
								<label>Email address<em>*</em></label><input type="text" name="Email_Address" class="required email" onKeyDown="limitTextArea(this.form.Email_Address,40);" onKeyUp="limitTextArea(this.form.Email_Address,40);"/><br />
								<label>Phone number<em>*</em></label><input type="text" name="Phone_Number" class="required" onKeyDown="limitTextArea(this.form.Phone_Number,25);" onKeyUp="limitTextArea(this.form.Phone_Number,25);"/><br />
							</fieldset>
							
						</div>
						
					</li>
					<li id="form_slist0" class="pf_tab1 pf_section"> <!-- form_slist0 is one Section: hidden or shown for each mail box -->
						<h2 id="form_tog1Handle" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Change your Physical and Mailing  practice address or phone number</span>
						</h2>
						<div id="form_tog1" class="toggler"> <!-- Start of the FORM elements for one Section -->
							<font> Definitions : OFFICE=Physical Location / MAILING=Where correspondence is sent if different than the physical office location</font>						
							<fieldset>
							<legend>Previous/Existing Physical practice office</legend>
								<label for="form_ac_s1_preAddrS1">Physical Address line 1</label>
								<input type="text" id="form_ac_s1_preAddrS1" name="Address_Street1" title="" />  <br />
								<label for="form_ac_s1_preAddrS2">Physical Address line 2</label>
								<input type="text" id="form_ac_s1_preAddrS2_2" name="Address Street2" />  <br />
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
							<legend>Updated/New Physical practice office</legend>	
								<label for="form_ac_s1_newAddrS1">Physical Address line 1</label>
								<input type="text" id="form_ac_s1_newAddrS1" name="Address Street1" class="" title="Please type your First Address" />  <br />
								<label for="form_ac_s1_newAddrS2">Physical Address line 2</label>
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
								<label for="form_ac_s1_newPracticeofficeWebsite">Practice office Website</label>
								<input type="text" id="form_ac_s1_newPracticeofficeWebsite" name="Practice office Website" />  <br />
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
							</fieldset>							
							
							<fieldset>
							<legend>Previous/Existing Mailing address</legend>
								<label for="form_ac_s2_preAddrS1">Mailing Address line 1</label>
								<input type="text" id="form_ac_s2_preAddrS1" name="Address_Street1" title="" />  <br />
								<label for="form_ac_s2_preAddrS2">Mailing Address line 2</label>
								<input type="text" id="form_ac_s2_preAddrS2_2" name="Address Street2" />  <br />
								<label for="form_ac_s2_preCity">City</label>
								<input type="text" id="form_ac_s2_preCity" name="City" class="" title="Please type your City name" />  <br />
								<label for="form_ac_s2_preState">State</label>
								<input type="text" id="form_ac_s2_preState" name="State" class="" title="Please type your State" />  <br />
								<label for="form_ac_s2_preZip">Zip</label>
								<input type="text" id="form_ac_s2_preZip" name="Zip" class="" title="Please type your Zipcode" />  <br />
								<label for="form_ac_s2_preCounty">County</label>
								<input type="text" id="form_ac_s2_preCounty" name="County" />  <br />
							</fieldset>
							<fieldset>
							<legend>Updated/New Mailing address</legend>
								<label for="form_ac_s3_preAddrS1">Mailing Address line 1</label>
								<input type="text" id="form_ac_s3_preAddrS1" name="Address_Street1" title="" />  <br />
								<label for="form_ac_s3_preAddrS2">Mailing Address line 2</label>
								<input type="text" id="form_ac_s3_preAddrS2_2" name="Address Street2" />  <br />
								<label for="form_ac_s3_preCity">City</label>
								<input type="text" id="form_ac_s3_preCity" name="City" class="" title="Please type your City name" />  <br />
								<label for="form_ac_s3_preState">State</label>
								<input type="text" id="form_ac_s3_preState" name="State" class="" title="Please type your State" />  <br />
								<label for="form_ac_s3_preZip">Zip</label>
								<input type="text" id="form_ac_s3_preZip" name="Zip" class="" title="Please type your Zipcode" />  <br />
								<label for="form_ac_s3_preCounty">County</label>
								<input type="text" id="form_ac_s3_preCounty" name="County" />  <br />
								<label for="form_ac_s3_newOfficeWebsite">Practice office Website</label>
								<input type="text" id="form_ac_s3_newOfficeWebsite" name="Practice office Website" />  <br />								
							</fieldset>
						</div> <!-- END of First Toggling DIV Tog1 -->
					</li> <!-- END of First Section -->
					<li id="form_slist1" class="pf_tab1 pf_tab3 pf_section"> <!-- form_slist1 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle2" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Add a new location to your practice</span>
						</h2>
						<div id="form_tog2" class="toggler"> <!-- Start of the FORM elements for one Section -->
							<font color="#FF0000">Note:  To add a new location to an existing contracted PT, OT, ST therapy group, please refer to the section below.</font><br />								
							<fieldset>
								<label for="form_ac_s1_newpracticesamelocation">Will all providers practice at this location ?</label>
								<select id="form_ac_s1_newpracticesamelocation" name="Newpracticesamelocation">
									<option selected></option>
									<option value="Yes">Yes</option>
									<option value="No">No</option>
								</select> <br />
								<label for="form_ac_s1_listofproviders"> If no, list the providers <font class="pf_bold">first, last name and NPI</font> that will practice at this location.</label>
								<textarea id="form_ac_s1_listofproviders" name="List_of_providers" /> </textarea> 
								<br />
								<br />
							</fieldset>						
							<fieldset>
							<legend>Updated/New Physical practice Office</legend>
								<label for="form_ac_s2_newAddrs1">Physical Address line 1</label>
								<input type="text" id="form_ac_s2_newAddrs1" name="Address_Street1" />  <br />
								<label for="form_ac_s2_newAddrS2">Physical Address line 2</label>
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
							<legend>Updated/New Mailing Correspondence address</legend>
								<label for="form_ac_s3_newAddrs1">Address line 1</label>
								<input type="text" id="form_ac_s3_newAddrs1" name="Address_Street1" />  <br />
								<label for="form_ac_s3_newAddrS2">Address line 2</label>
								<input type="text" id="form_ac_s3_newAddrS2" name="Address_Street2" />  <br />
								<label for="form_ac_s3_newCity">City</label>
								<input type="text" id="form_ac_s3_newCity" name="City" />  <br />
								<label for="form_ac_s3_newState">State</label>
								<input type="text" id="form_ac_s3_newState" name="State" />  <br />
								<label for="form_ac_s3_newZip">Zip</label>
								<input type="text" id="form_ac_s3_newZip" name="Zip" />  <br />
								<label for="form_ac_s3_newCounty">County</label>
                                <input type="text" id="form_ac_s3_newCounty" name="County" />  <br />
								<label for="form_ac_s3_teleno">Phone number</label>
								<input type="text" id="form_ac_s3_teleno" name="Practice_Office_Phone_Number" />  <br />
                                <label for="form_ac_s3_pOffFax">Fax number</label>
								<input type="text" id="form_ac_s3_pOffFax" name="Practice_Office_Fax_Number" /> <br />
							</fieldset>			
							<fieldset>
							<legend>Updated/New Remittance address</legend>
								<label for="form_ac_s4_newAddrs1">Address line 1</label>
								<input type="text" id="form_ac_s4_newAddrs1" name="Address_Street1" />  <br />
								<label for="form_ac_s4_newAddrS2">Address line 2</label>
								<input type="text" id="form_ac_s4_newAddrS2" name="Address_Street2" />  <br />
								<label for="form_ac_s4_newCity">City</label>
								<input type="text" id="form_ac_s4_newCity" name="City" />  <br />
								<label for="form_ac_s4_newState">State</label>
								<input type="text" id="form_ac_s4_newState" name="State" />  <br />
								<label for="form_ac_s4_newZip">Zip</label>
								<input type="text" id="form_ac_s4_newZip" name="Zip" />  <br />
								<label for="form_ac_s4_newCounty">County</label>
                                <input type="text" id="form_ac_s4_newCounty" name="County" />  <br />
								<label for="form_ac_s4_teleno">Phone number</label>
								<input type="text" id="form_ac_s4_teleno" name="Practice_Office_Phone_Number" />  <br />
                                <label for="form_ac_s4_pOffFax">Fax number</label>
								<input type="text" id="form_ac_s4_pOffFax" name="Practice_Office_Fax_Number" /> <br />
							</fieldset>					
						
						</div> <!-- END of form_tog2: the second Section Body -->
					</li> <!-- END of Second Form Section -->
					<li id="form_slist2" class="pf_tab1 pf_tab3 pf_section"> <!-- form_slist2 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle3" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Close a practice location (only applicable if closing an entire building/site)</span>
						</h2>
						<div class="toggler"> <!-- Start of the FORM elements for one Section -->
							<font color="#FF0000">Note:  If you are moving, please use the Change your Physical 
							and Mailing address or phone number section.  This section, Close a practice 
							location, will <font class="pf_bold">not terminate your provider agreement.</font></font><br />
							<fieldset>
							<label for="s3_tReason">Reason for closing location</label>
							<input type="text" id="s3_tReason" name="s3_tReason" />  <br />
                            </fieldset>
							<fieldset>
							<legend>Previous/Existing Physical practice office</legend>
								<label for="form_ac_s3_preAddrs1">Physical Address line 1</label>
								<input type="text" id="form_ac_s3_preAddrs1" name="Address_Street1" />  <br />
								<label for="form_ac_s3_preAddrS2">Physical Address line 2</label>
								<input type="text" id="form_ac_s3_preAddrS2" name="Address_Street2" />  <br />
								<label for="form_ac_s3_preCity">City</label>
								<input type="text" id="form_ac_s3_preCity" name="City" />  <br />
								<label for="form_ac_s3_preState">State</label>
								<input type="text" id="form_ac_s3_preState" name="State" />  <br />
								<label for="form_ac_s3_preZip">Zip</label>
								<input type="text" id="form_ac_s3_preZip" name="Zip" />  <br />
								<label for="form_ac_s3_preCounty">County</label>
								<input type="text" id="form_ac_s3_preCounty" name="County" />  <br />
							</fieldset>
						</div> <!-- END of form_tog3: the Third Section Body -->
					</li> <!-- END of Third Form Section -->
                    
					<li id="form_slist5" class="pf_tab1 pf_tab3 pf_section"> <!-- form_slist3 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle6" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Change your payment and remittance address</span>
						</h2>
						<div class="toggler"> <!-- Start of the FORM elements for one Section -->
					        <font>Definitions: FINANCIAL = Where Remittances/checks are sent.</font><br />									
							<fieldset>
							<legend>Previous/Existing payment and remittance</legend>
								<label for="form_ac_s5_preAddrs1">Address line 1</label>
								<input type="text" id="form_ac_s5_preAddrs1" name="Address_Street1" />  <br />
								<label for="form_ac_s5_preAddrS2">Address line 2</label>
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
								<input type="text" id="form_ac_s5_preEmail" name="Email" class="email"/>  <br />								
                                <label for="form_ac_s5_prePhone">Phone number</label>
								<input type="text" id="form_ac_s5_prePhone" name="Phone" />  <br />
                                <label for="form_ac_s5_preFax">Fax number</label>
								<input type="text" id="form_ac_s5_preFax" name="Fax" />  <br />
							</fieldset>
							<fieldset>
							<legend>Updated/New payment and remittance</legend>
								<label for="form_ac_s5_newAddrs1">Address line 1</label>
								<input type="text" id="form_ac_s5_newAddrs1" name="Address_Street1" />  <br />
								<label for="form_ac_s5_newAddrS2">Address line 2</label>
								<input type="text" id="form_ac_s5_newAddrS2" name="Address_Street2" />  <br />
								<label for="form_ac_s5_newCity">City</label>
								<input type="text" id="form_ac_s5_newCity" name="City" />  <br />
								<label for="form_ac_s5_newState">State</label>
								<input type="text" id="form_ac_s5_newState" name="State" />  <br />
								<label for="form_ac_s5_newZip">Zip</label>
								<input type="text" id="form_ac_s5_newZip" name="Zip" />  <br />
                                <label for="form_ac_s5_newCounty">County</label>
								<input type="text" id="form_ac_s5_newCounty" name="County" />  <br />
								<label for="form_ac_s5_newEmail">Email address</label>
								<input type="text" id="form_ac_s5_newEmail" name="Email" class="email"/>  <br />																
                                <label for="form_ac_s5_newPhone">Phone number</label>
								<input type="text" id="form_ac_s5_newPhone" name="Phone" />  <br />
                                <label for="form_ac_s5_newFax">Fax number</label>
								<input type="text" id="form_ac_s5_newFax" name="Fax" />  <br />
							</fieldset>
						</div> <!-- END of form_tog4: the sixth Section Body -->
					</li> <!-- END of sixth Form Section -->

					<li id="form_slist6" class="pf_tab1 pf_tab3 pf_section"> <!-- form_slist5 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle7" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Change your office hours or days of operation</span>
						</h2>
						<div class="toggler"> <!-- Start of the FORM elements for one Section -->
							
							<fieldset>
							<legend>Previous/Existing Physical practice office</legend>
								<label for="form_ac_s6_preAddrs1">Physical Address line 1</label>
								<input type="text" id="form_ac_s6_preAddrs1" name="Address_Street1" />  <br />
								<label for="form_ac_s6_preAddrS2">Physical Address line 2</label>
								<input type="text" id="form_ac_s6_preAddrS2" name="Address_Street2" />  <br />
								<label for="form_ac_s6_preCity">City</label>
								<input type="text" id="form_ac_s6_preCity" name="City" />  <br />
								<label for="form_ac_s6_preState">State</label>
								<input type="text" id="form_ac_s6_preState" name="State" />  <br />
								<label for="form_ac_s6_preZip">Zip</label>
								<input type="text" id="form_ac_s6_preZip" name="Zip" />  <br />
							</fieldset>
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
							</fieldset>
						</div> <!-- END of form_tog6: the Seventh Section Body -->	
					</li> <!-- END of Seventh Form Section -->
                    					
					<li id="form_slist10" class="pf_tab1 pf_tab3 pf_section"> <!-- form_slist10 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle11" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Update or add your NPI</span>
						</h2>
						<div class="toggler"> <!-- Start of the FORM elements for one Section -->
							<fieldset>
							<legend>Previous/Existing Physical practice office</legend>
								<label for="form_ac_s10_preAddrs1">Physical Address line 1</label>
								<input type="text" id="form_ac_s10_preAddrs1" name="Address_Street1" />  <br />
								<label for="form_ac_s10_preAddrS2">Physical Address line 2</label>
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
							<fieldset>
								<label for="form_ac_s10_indNPI">Individual National Provider Identifier (NPI)</label>
								<input type="text" id="form_ac_s10_indNPI" name="form_ac_s10_indNPI" class="NPI"/>  <br />
							</fieldset>							
							<fieldset>
								<label for="form_ac_s10_pGNPI">Organizational National Provider Identifier (NPI)</label>
								<input type="text" id="form_ac_s10_pGNPI" name="s10_Group_NPI" class="NPI"/>  <br />
							</fieldset>
						</div> <!-- END of form_tog10: the eleventh Section Body -->	
					</li> <!-- END of eleventh Form Section -->
					
					<li id="form_slist11" class="pf_tab1 pf_tab3 pf_section"> <!-- form_slist10 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle12" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Update or add your practice email address</span>
						</h2>
						<div class="toggler"> <!-- Start of the FORM elements for one Section -->
							
							<fieldset>
								<legend>Practice email address</legend>
								
								<label for="form_ac_s11_priEmail">Practice Email address</label>
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
					
					<li id="form_slist12" class="pf_tab1 pf_tab3 pf_section"> <!-- form_slist11 is one Section: hidden or shown for each mail box -->
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
									<option value="Child Welfare">Child Welfare</option>
									<option value="Chronic Illness">Chronic Illness</option>
									<option value="Hearing Impaired">Hearing Impaired</option>
									<option value="HIV/AIDS">HIV/AIDS</option>									
									<option value="Homelessness">Homelessness</option>
									<option value="Physical Disability">Physical Disability</option>
									<option value="Substance Abuse">Substance Abuse</option>
									<option value="Trauma">Trauma</option>
									<option value="Visually Impaired">Visually Impaired</option>
								</select> <br />
								<label>Request Type</label>
								<input type="radio" name="s12bRequestType" value="Add" id="s12bRequestAdd" style="width:auto" /><label for="RequestAdd"  style="width:auto;margin-right:5px;" class="exclude">Add</label>
								<input type="radio" name="s12bRequestType" value="Delete"  id="s12bRequestDelete" style="width:auto"/><label for="RequestDelete" style="width:auto;margin-right:5px;" class="exclude">Delete</label><br />
                                <label for="form_ac_s12bpExpertise">Provider's self-reported area of expertise</label>
								<select id="form_ac_s12bpExpertise" name="Provider_Self_Reported_Expertise_Area">
									<option selected></option>
									<option value="Child Welfare">Child Welfare</option>
									<option value="Chronic Illness">Chronic Illness</option>
									<option value="Hearing Impaired">Hearing Impaired</option>
									<option value="HIV/AIDS">HIV/AIDS</option>									
									<option value="Homelessness">Homelessness</option>
									<option value="Physical Disability">Physical Disability</option>
									<option value="Substance Abuse">Substance Abuse</option>
									<option value="Trauma">Trauma</option>
									<option value="Visually Impaired">Visually Impaired</option>
								</select> <br />
							</fieldset>
						</div> <!-- END of form_tog12: the thirteenth Section Body -->	
					</li> <!-- END of thirteenth Form Section -->
					
								
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
								<legend>Previous/Existing Physical practice office</legend>
									<label for="form_s17_newAddrs1">Physical Address line 1</label>
									<input type="text" id="form_s17_newAddrs1" name="Address_Street1" />  <br />
									<label for="form_s17_newAddrS2">Physical Address line 2</label>
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
					
					<li id="form_slist8" class="pf_tab2 pf_tab3 pf_section"> <!-- form_slist8 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle9" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Change your Tax Identification Number (TIN) or ownership of group practice <em>(W-9 required)</em> </span>
						</h2>
						<div class="toggler"> <!-- Start of the FORM elements for one Section -->
							<!--<fieldset>
								<label for="form_ac_s8_faxno">Practice office fax number</label>
								<input type="text" id="form_ac_s8_faxno" name="Practice_Office_Fax_Number" />  <br />
								<label for="form_ac_s8_langStaff">Language spoken by office staff</label>
								<input type="text" id="form_ac_s8_langStaff" name="Language_Spoken_Office_Staff" /> <br />
								<label for="form_ac_s8_newpat">Accepting new patients</label>
								<select id="form_ac_s8_newpat" name="Accepting_New_Patients">
									<option selected></option>
									<option value="Yes">Yes</option>
									<option value="No">No</option>
								</select> <br />
							</fieldset>-->
							
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
                                <label>Attachments</label>
                                <input type="checkbox" name="Attachments" value="Yes" style="width:auto" /><label style="width:auto;margin-right:5px;">I have sent attachments separately.</label><br /><br/>
								<font color="#FF0000">*If your change requires a new W-9 form, this electronic form must be completed AND a W-9 submitted by fax, email, or Postal Service.</font><br />								
							</fieldset>				
						</div> <!-- END of form_tog9: the Nineth Section Body -->	
					</li> <!-- END of Nineth Form Section -->
					
					<li id="form_slist13" class="pf_tab1 pf_tab3 pf_section"> <!-- form_slist15 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle14" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Add or remove PT, OT, ST or Audiology services from an existing location</span>
						</h2>
						<div class="toggler"> <!-- Start of the FORM elements for one Section -->
							<font color="#FF0000">Note:  If you are <font class="pf_bold">not</font> a PT, OT, ST or Audiologist, 
							please refer to appropriate section.</font><br />															
							<fieldset>
								<legend>Provider details</legend>
                				<label>Request Type</label>
                				<input type="radio" name="s13RequestType" value="Add" id="s13RequestAdd" style="width:auto" /><label for="s13RequestAdd"  style="width:auto;margin-right:5px;" class="exclude">Add</label>
                				<input type="radio" name="s13RequestType" value="Delete"  id="s13RequestDelete" style="width:auto"/><label for="s13RequestDelete" style="width:auto;margin-right:5px;" class="exclude">Delete</label><br />
								<label for="form_ac_s13_ppspecialty">Primary specialty</label>
                				<select id="form_ac_s13_ppspecialty" name="s13Primary_Specialty">
                                	<option selected></option>
                                	<option value="Audiology">Audiology</option>
                                	<option value="Physical Therapy">Physical Therapy</option>
                                	<option value="Occupational Therapy">Occupational Therapy</option>
                                	<option value="Speech Therapy">Speech Therapy</option>
                				</select> <br />
                				<label for="form_ac_s13_psspecialty">Secondary specialty </label>
                				<select id="form_ac_s13_psspecialty" name="s13Secondary_Specialty">
                                	<option selected></option>
                                	<option value="Audiology">Audiology</option>
                                	<option value="Physical Therapy">Physical Therapy</option>
                                	<option value="Occupational Therapy">Occupational Therapy</option>
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
								<legend>Previous/Existing Physical practice office</legend>
								<label for="form_ac_s13_preAddrS1">Physical Address line 1</label>
								<input type="text" id="form_ac_s13_preAddrS1" name="Address_Street1" />  <br />
								<label for="form_ac_s13_preAddrS2">Physical Address line 2</label>
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
										
					
					<li id="form_slist20" class="pf_tab1 pf_section"> <!-- form_slist15 is one Section: hidden or shown for each mail box -->
						<h2 id="form_togHandle21" class="pf_sectionHead" title="Click to Toggle Forms View">
							<span class="label">Add a new location to an existing contracted PT, OT, ST therapy group (ancillary providers only)</span>
						</h2>
						<div class="toggler"> <!-- Start of the FORM elements for one Section -->
							<font color="#FF0000">Note:  If you are <font class="pf_bold">not</font> a PT, OT, ST therapy group, please refer to appropriate section.</font><br />															
							<fieldset>
								<legend>Provider details</legend>
								<label for="form_ac_s13_ppspecialty">Primary specialty</label>
								<select id="form_ac_s13_ppspecialty" name="s13Primary_Specialty">
                         	      	<option selected></option>
                           	    	<option value="Physical Therapy">Physical Therapy</option>
                                	<option value="Occupational Therapy">Occupational Therapy</option>
                                	<option value="Speech Therapy">Speech Therapy</option>
                				</select> <br />
                				<label>Primary specialty National Provider Identifier (NPI)</label><input type="text" id="ancil_primary_spec_npi" name="ancil_primary_spec_npi" class="NPI"/><br />
                            	<label for="form_ac_s13_psspecialty">Secondary specialty</label>
                            	<select id="form_ac_s13_psspecialty" name="s13Secondary_Specialty">
                            		<option selected></option>
                                	<option value="Physical Therapy">Physical Therapy</option>
                                	<option value="Occupational Therapy">Occupational Therapy</option>
                                	<option value="Speech Therapy">Speech Therapy</option>
                            	</select> <br />
                				<label>Secondary specialty National Provider Identifier (NPI)</label><input type="text" id="ancil_secondary_spec_npi" name="ancil_secondary_spec_npi" class="NPI"/><br />
                				<label for="form_ac_s14_psspecialty">Tertiary specialty</label>
                				<select id="form_ac_s14_psspecialty" name="s14Tertiary_Specialty">
                                	<option selected></option>
                                	<option value="Physical Therapy">Physical Therapy</option>
                                	<option value="Occupational Therapy">Occupational Therapy</option>
                                	<option value="Speech Therapy">Speech Therapy</option>
                				</select> <br />
                				<label>Tertiary specialty National Provider Identifier (NPI)</label><input type="text" id="ancil_tertiary_spec_npi" name="ancil_tertiary_spec_npi" class="NPI"/><br />
                				<label>Tax Identification Number (TIN)</label><input type="text" id="Ancillary_Tax_Identification_Number" name="Ancillary_Tax_Identification_Number" class="TIN" /><br />
                				<label for="form_ac_s13_license">Do you hold a current, active and unrestricted  license in the state in which you are applying for participation?</label>
                				<select id="form_ac_s13_license" name="Current_Active_Unrestricted_License">
                                	<option selected></option>
                                	<option value="Yes">Yes</option>
                                	<option value="No">No</option>
                				</select> <br />                                  
							</fieldset> 
							
							<fieldset>
								<legend>Mailing address</legend>
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
								<legend>Financial address</legend>
								<label for="form_ac_s14_preAddrS1">Address line 1</label>
								<input type="text" id="form_ac_s14_preAddrS1" name="Address_Street1" />  <br />
								<label for="form_ac_s14_preAddrS2">Address line 2</label>
								<input type="text" id="form_ac_s14_preAddrS2" name="Address_Street2" />  <br />
								<label for="form_ac_s14_preCity">City</label>
								<input type="text" id="form_ac_s14_preCity" name="City" />  <br />
								<label for="form_ac_s14_preState">State</label>
								<input type="text" id="form_ac_s14_preState" name="State" />  <br />
								<label for="form_ac_s14_preZip">Zip</label>
								<input type="text" id="form_ac_s14_preZip" name="Zip" />  <br />
								<label for="form_ac_s14_preCounty">County</label>
								<input type="text" id="form_ac_s14_preCounty" name="County" />  <br />
                                <label for="form_ac_s14_prePhone">Office phone number</label>
								<input type="text" id="form_ac_s14prePhone" name="Phone" />  <br />
                                <label for="form_ac_s14_prefax">Office fax number</label>
								<input type="text" id="form_ac_s14_prefax" name="Fax" />  <br />
                                <label for="form_ac_s14_preemail">Email</label>
								<input type="text" id="form_ac_s14preemail" name="Email" />  <br />
							</fieldset>							
						</div> <!-- END of form_tog16: the fourteenth Section Body -->	
					</li> <!-- END of fourteenth Form Section -->					
				
				</ul> <!-- END of Form Section List -->
				</form>
				
				
				<form CLASS="provider_upload_form" METHOD="POST" action="/mwpmf/message/PDMControllerServlet" > <!-- enctype="multipart/form-data"> --> 
				<input type="hidden" id="formUpdateAction" name="formUpdateAction" value="uploadFile">
		<!--    <input type="text" id="uploadFileName" name="uploadFileName" value=""/>
				<input type="text" id="uploadFileSize" name="uploadFileSize" value=""/>
				<input type="text" id="uploadFileComment" name="uploadFileComment" value=""/>
				<input type="text" id="totalFileSize" name="totalFileSize" value="0"/>  
		-->
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
				    <input type="hidden" name="Gen_Provider_Last_Name" id="Gen_Provider_Last_Name" value=""/>
					<input type="hidden" name="Gen_Provider_First_Name" id="Gen_Provider_First_Name" value=""/>
					<input type="hidden" name="Gen_Ind_TIN" id="Gen_Ind_TIN" value=""/>
					<input type="hidden" name="GenSec_Prov_Ded_NPI" id="GenSec_Prov_Ded_NPI" value=""/>
					<input type="hidden" name="Gem_Provider_Practice_Name" id="Gem_Provider_Practice_Name" value=""/>
					<input type="hidden" name="Gen_Pract_TIN" id="Gen_Pract_TIN" value=""/>
					<input type="hidden" name="GenSec_Pract_Ded_NPI" id="GenSec_Pract_Ded_NPI" value=""/>
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