$.fn.clearForm = function() { //Custom Function for Reset Form
	return this.each(function() {
		var type = this.type, tag = this.tagName.toLowerCase();
		if (tag == 'form')
			return $(':input', this).clearForm();
		if (type == 'text' || type == 'password' || tag == 'textarea')
			this.value = '';
		else if (type == 'checkbox' || type == 'radio')
			this.checked = false;
		else if (tag == 'select')
			this.selectedIndex = -1;
	});
};

$(document).ready(function() {
	// ******** DATA SOURCE *************
	//var providerType = ["Medical Doctor (MD)", "Doctor of Dental Surgery (DDS)", "Doctor of Dental Medicine (DMD)", "Doctor of Podiatric Medicine (DPM)", "Doctor of Chiropractic (DC)", "Osteopathic Doctor (DO)", "Acupuncturist", "Alcohol/Drug Counselor", "Audiologist", "Biofeedback Technician", "Certified Registered Nurse", "Anesthetist", "Christian Science Practitioner", "Clinical Nurse Specialist", "Clinical Psychologist", "Clinical Social Worker", "Dietician", "Licensed Practical Nurse", "Locum Tenen", "Marriage/Family Therapist", "Massage Therapist", "Naturopath", "Neuropsychologist", "Midwife", "Nurse Midwife", "Nurse Practitioner", "Nutritionist", "Occupational Therapist", "Optician", "Optometrist", "Pharmacist", "Physical Therapist", "Physician Assistant", "Professional Counselor", "Registered Nurse", "Registered Nurse First Assistant", "Respiratory Therapist", "Speech Therapist"];
	//var specialty = ["Adolescent Gynecology","Adolescent Psychiatry","Allergy & Immunology","Ambulance","Ambulatory Surgery","Anorectal Surgery","Audiology","Breast Surgery","Cardiac Catheterization","Cardiac Surgery","Cardiology","Cardiovascular Thoracic Surgery","Cataracts Specialty","Certified Diabetes Educator","Certified Laboratory","Certified Licensed Acupuncturist","Certified Nurse Midwife","Certified Orthotics and Prosthetics Co","Child Psychiatry","Chiropractic Medicine","Clinical Psychologist","Colon and Rectal Surgery","Corneal Disorders","Dermatological Surgery","Dermatology","Diagnostic Radiology","Dietician","Diplomate in Podiatric Surgery","Durable Medical Equipment","Echocardiography","EKG Interpretation","Endocrinology","Endocrinology Obstetrics","Family Practice - Board Certified","Family Practice - Board Eligible","Gastroenterology","General Practice","General Surgery","Genetics Adults","Geriatrics","Glaucoma","Gynecologic Oncology","Gynecology","Hand Surgery","Head & Neck Surgery","Hematology","High Risk Obstetrics","Home Infusion","Infectious Disease","Internal Medicine Board Certified","Internal Medicine Board Eligible","Internal Medicine Geriatrics","Interventional Radiology","Licensed Marriage and Family Therapist","Licensed Chemical Dependency Counselor","Licensed Professional Counselor","Licensed Psychiatric Agency","Mammography","Maxillofacial Surgery","Medical Oncology","Multispecialty Group","Neonatology","Nephrology","Neuro Opthalmology","Neurology","Neuroradiology","Neurosurgery","Nuclear Cardiology","Nuclear Medicine Radiology","Nurse Practitioner","Nutritional Medicine","Obstetrics & Gynecology","Occupational Therapy","Ocular & Orbital Tumors","Oncology","Ophthalmology","Opthalmic Plastic","Oral and Maxillofacial Surgery","Oral Surgery","Orthopedic Surgery","Otolaryngology","Pain Management","Pastoral Counselor","Pediatric Adolescent Medicine","Pediatric Allergy & Immunology","Pediatric Behavioral Disorders","Pediatric Cardiology","Pediatric Cardiothoracic Surgery","Pediatric Critical Care ","Pediatric Dermatology","Pediatric Develop Disorders","Pediatric Endocrinology","Pediatric Gastroenterology","Pediatric Genetics","Pediatric Hematology Oncology","Pediatric Infectious Diseases","Pediatric Neonatal","Pediatric Nephrology","Pediatric Neurology","Pediatric Neurosurgery","Pediatric Oncology","Pediatric Ophthalmology","Pediatrics Orthopedics","Pediatric Otolaryngology","Pediatric Pulmonary Medicine","Pediatric Radiology","Pediatric Rheumatology","Pediatric Surgery","Pediatric Urology","Pediatrics","Perinatology High Risk Obstetrics","Peripheral Vascular Disease","Physical Medicine & Rehabilitation","Physical Therapy","Plastic Reconstructive Surgery","Podiatric Orthopedics","Podiatry","Proctology","Psychiatric Neurology","Psychiatric Nurse","Psychiatry","Psychologist Individual or Group","Pulmonary Medicine","Radiation Oncology","Radiology","Radiology, Nuclear","Reconstructive Surgery","Registered Physical Occupational Therapist","Reproductive Endocrinology","Retail Health Clinic","Rheumatology","Roentgenology","Sleep Disorders","Social Worker","Speech Therapy","Sports Medicine","Surgery, Orthopedic, Pediatric","Surgical Critical Care","Surgical Oncology","Therapeutic Roentgenology","Therapeutically Certified Optometrist","Transplant Surgery Liver Kidney","Urgent Care Center","Urogynecology","Urology","Vascular Surgery"];
	//var areasOfExpertise = ["Amputation Rehabilitation",  "Aqua", "Autism (If yes provide individual name, individual license number, and  NPI number)",  "ABA (If yes provide individual name, individual license number, and  NPI number)", "Geriatric Therapy", "Hand Therapy", "Lymphatic Therapy", "Neurological Therapy", "Occupational Therapy", "Pediatric Occupational Therapy", "Physical Therapy", "Pediatric Physical Therapy", "Speech Therapy", "Pediatric Speech Therapy", "Sports Injury", "Women's Health", "Other"];

	//			$("input.pf_provType").live("focus", function() { 
	//				$(this).autocomplete(
	//					{
	//					  source: function( request, response ) {
	//						var matches = $.map( providerType, function(tag) {
	//						  if ( tag.toUpperCase().indexOf(request.term.toUpperCase()) === 0 ) {
	//							return tag;
	//						  }
	//						});
	//						response(matches);
	//					  },minLength:0
	//					}
	//				);
	//				
	//				$(this).autocomplete("search",$(this).val());
	//			});

	//			$("input.pf_specialty").live("focus", function(){
	//				$(this).autocomplete(
	//					{
	//					  source: function( request, response ) {
	//						var matches = $.map( specialty, function(tag) {
	//						  if ( tag.toUpperCase().indexOf(request.term.toUpperCase()) === 0 ) {
	//							return tag;
	//						  }
	//						});
	//						response(matches);
	//					  },minLength:0
	//					}
	//				);
	//				$(this).autocomplete("search",$(this).val());
	//			});

	//			$("input.pf_ancExpertise").live("focus",function(){
	//				$(this).autocomplete(
	//					{
	//					  source: function( request, response ) {
	//						var matches = $.map( areasOfExpertise, function(tag) {
	//						  if ( tag.toUpperCase().indexOf(request.term.toUpperCase()) === 0 ) {
	//							return tag;
	//						  }
	//						});
	//						response(matches);
	//					  },minLength:0
	//					}
	//				);
	//				$(this).autocomplete("search",$(this).val());
	//			});

	// ******** DATA SOURCE ENDS *************

	$.validator.addMethod("time", function(value, element) {
		return this.optional(element) || /^(([0-1]?[0-9])|([2][0-3])):([0-5]?[0-9])(:([0-5]?[0-9]))?$/i.test(value);
	}, "Please enter a valid time.");

	$.validator.addMethod("requireYes", function(value, element) {
		return this.optional(element) || jQuery.trim(value) == "" || value.toUpperCase() == "YES";

	}, "Only Yes is accepted.");

	$.validator.addMethod("requireOption", function(value, element) {
		if ($(element).closest(".pf_section").is(":visible")) {
			return jQuery.trim(value) != "";
		}
		return true;

	}, "This field is required.");

	$.validator.addMethod("TIN", function(value, element) {
		return this.optional(element) || (value.length == 9 && !isNaN(value) && value.indexOf(".") == -1 && value.indexOf("-") == -1);
	}, "The Tax ID Number is 9 numbers in length.  Please enter all 9 numbers.");

	$.validator.addMethod("NPI", function(value, element) {
		return this.optional(element) || (value.length == 10 && !isNaN(value) && value.indexOf(".") == -1 && value.indexOf("-") == -1);
	}, "The National Provider Identifier is 10 numbers in length.  Please enter all 10 numbers.");

	$.validator.addMethod("CID", function(value, element) {
		return this.optional(element) || value.length == 8;
	}, "The CAQH Number is 8 numbers in length.  Please enter all 8 numbers.");

	$.validator.addMethod("requireAttachment", function(value, element) {
		if (($(element).is(':checked') == false) ||
			(($(element).is(':checked') == true) &&
				(jQuery.trim($("#uploadFileName").val()) != "")))
			return true;
	}, "Please upload attachment.");

	$.validator.addMethod("requiredSection", function(value, element) {
		if ($(element).closest(".toggler").is(":visible")) {
			return jQuery.trim(value) != "";
		}
		else return true;
	}, "This field is required.");
	// Conditional Validation for "Name change for individual dental practitioner"
	$.validator.addMethod("conditionalCheckIndDentalPract", function(value, element) {
		if ($(element).closest(".toggler").is(":visible")) {

			// current field is having value then conditional check not required
			if (jQuery.trim(value) != "") {
				return true;
			}

			//any of the below field is having value then current field is required
			if ((jQuery.trim($("#form_ac_s2_provLastNameFormer").val()) != "")
				|| (jQuery.trim($("#form_ac_s2_provFirstNameFormer").val()) != "")
				|| (jQuery.trim($("#form_ac_s2_provMiddleNameFormer").val()) != "")
				|| (jQuery.trim($("#form_ac_s2_provSuffixFormer").val()) != "")
				|| (jQuery.trim($("#form_ac_s2_proffTitle").val()) != "")
				|| (jQuery.trim($("#form_ac_s2_provLastNameUpdated").val()) != "")
				|| (jQuery.trim($("#form_ac_s2_provFirstNameUpdated").val()) != "")
				|| (jQuery.trim($("#form_ac_s2_provMiddleNameUpdated").val()) != "")
				|| (jQuery.trim($("#form_ac_s2_provSuffixUpdated").val()) != "")
				|| (jQuery.trim($("#form_ac_s2_proffTitleUpdated").val()) != "")
				|| (jQuery.trim($('#form_ac_s2_state_linense').val()) != "")) {
				return false;
			}
			else return true;
		}
		else return true;
	}, "This field is required.");
	// Conditional Validation for " Remove a provider from a location"
	$.validator.addMethod("conditionalCheckRemoveProvider", function(value, element) {
		if ($(element).closest(".toggler").is(":visible")) {
			// current field is having value then conditional check not required
			if (jQuery.trim(value) != "") {
				return true;
			}

			//any of the below field is having value then current field is required
			if ((jQuery.trim($("#form_ac_s1_reason_removal").val()) != "")
				|| (jQuery.trim($("#form_ac_s1_remAddr1").val()) != "")
				|| (jQuery.trim($('#form_ac_s1_remAddr2').val()) != "")
				|| (jQuery.trim($('#form_ac_s1_remCity').val()) != "")
				|| (jQuery.trim($('#form_ac_s1_remState').val()) != "")
				|| (jQuery.trim($('#form_ac_s1_remZip').val()) != "")
				|| (jQuery.trim($('#form_ac_s1_remEffDate').val()) != "")) {
				return false;
			}
			else return true;
		}
		else return true;
	}, "This field is required.");
	// Conditional Validation for " Change in ownership of a practice"
	$.validator.addMethod("conditionalCheckChangeOwnership", function(value, element) {
		if ($(element).closest(".toggler").is(":visible")) {
			// current field is having value then conditional check not required
			if (jQuery.trim(value) != "") {
				return true;
			}
			// current field is having value then conditional check not required
			if (element.type == 'checkbox' && $('#form_ac_s9_attached').is(':checked') == true) {
				return true;
			}

			//any of the below field is having value then current field is required
			if ((jQuery.trim($("#form_ac_s9_newpracname").val()) != "")
				|| (jQuery.trim($("#form_ac_s9_IRS").val()) != "")
				|| (jQuery.trim($('#form_ac_s9_prevTIN').val()) != "")
				|| (jQuery.trim($('#form_ac_s9_newTIN').val()) != "")
				|| (jQuery.trim($('#form_ac_s9_newOfficePhone').val()) != "")
				|| (jQuery.trim($('#form_ac_s9_provprev_last_name').val()) != "")
				|| (jQuery.trim($('#form_ac_s9_provprev_first_name').val()) != "")
				|| (jQuery.trim($('#form_ac_s9_provprev_middle_name').val()) != "")
				|| (jQuery.trim($('#form_ac_s9_provprev_Provider_License').val()) != "")
				|| (jQuery.trim($('#form_ac_s9_prov_last_name').val()) != "")
				|| (jQuery.trim($('#form_ac_s9_prov_first_name').val()) != "")
				|| (jQuery.trim($('#form_ac_s9_prov_middle_name').val()) != "")
				|| (jQuery.trim($('#form_ac_s9Provider_License').val()) != "")
				|| $('#form_ac_s9_attached').is(':checked') == true) {
				return false;
			}
			else return true;
		}
		else return true;
	}, "This field is required.");
	// Conditional Validation for " Change your practice address or phone/fax number"
	$.validator.addMethod("conditionalCheckChangePractAddress", function(value, element) {
		if ($(element).closest(".toggler").is(":visible")) {
			// current field is having value then conditional check not required
			if (jQuery.trim(value) != "") {
				return true;
			}

			//any of the below field is having value then current field is required
			if ((jQuery.trim($("#form_ac_s10_prePractAddrS1").val()) != "")
				|| (jQuery.trim($("#form_ac_s10_prePractAddrS2").val()) != "")
				|| (jQuery.trim($('#form_ac_s10_prePractCity').val()) != "")
				|| (jQuery.trim($('#form_ac_s10_prePractState').val()) != "")
				|| (jQuery.trim($('#form_ac_s10_prePractZip').val()) != "")
				|| (jQuery.trim($('#form_ac_s10_prePractPhone').val()) != "")
				|| (jQuery.trim($('#form_ac_s10_prePractfax').val()) != "")
				|| (jQuery.trim($('#form_ac_s10_PractAddrS1').val()) != "")
				|| (jQuery.trim($('#form_ac_s10_PractAddrS2').val()) != "")
				|| (jQuery.trim($('#form_ac_s10_PractCity').val()) != "")
				|| (jQuery.trim($('#form_ac_s10_PractState').val()) != "")
				|| (jQuery.trim($('#form_ac_s10_PractZip').val()) != "")
				|| (jQuery.trim($('#form_ac_s10_PractPhone').val()) != "")
				|| (jQuery.trim($('#form_ac_s10_Practfax').val()) != "")
				|| (jQuery.trim($('#form_ac_s10_PractPlanEmail').val()) != "")
				|| (jQuery.trim($('#form_ac_s10_PractPatCorresEmail').val()) != "")
				|| (jQuery.trim($('#form_ac_s10_pract_pat_hand_access').val()) != "")
				|| (jQuery.trim($('#form_ac_s10_pract_pat_wheelchair_access').val()) != "")) {
				return false;
			}
			else return true;
		}
		else return true;
	}, "This field is required.");

	// Conditional Validation for " Change your practice name"
	$.validator.addMethod("conditionalCheckChangePracName", function(value, element) {
		if ($(element).closest(".toggler").is(":visible")) {
			// current field is having value then conditional check not required
			if (jQuery.trim(value) != "") {
				return true;
			}

			//any of the below field is having value then current field is required
			if ((jQuery.trim($("#form_ac_s11_change_pract_name").val()) != "")
				|| (jQuery.trim($("#form_ac_s11_change_pract_addrs1").val()) != "")
				|| (jQuery.trim($('#form_ac_s11_change_pract_addrs2').val()) != "")
				|| (jQuery.trim($('#form_ac_s11_change_pract_city').val()) != "")
				|| (jQuery.trim($('#form_ac_s11_change_pract_state').val()) != "")
				|| (jQuery.trim($('#form_ac_s11_change_pract_zip').val()) != "")) {
				return false;
			}
			else return true;
		}
		else return true;
	}, "This field is required.");


	// Conditional Validation for " Add a new location"
	$.validator.addMethod("conditionalCheckAddNewLocat", function(value, element) {
		if ($(element).closest(".toggler").is(":visible")) {
			// current field is having value then conditional check not required
			if (jQuery.trim(value) != "") {
				return true;
			}

			//any of the below field is having value then current field is required
			if ((jQuery.trim($("#form_ac_s12_PractAddrS1").val()) != "")
				|| (jQuery.trim($("#form_ac_s12_PractAddrS2").val()) != "")
				|| (jQuery.trim($('#form_ac_s12_PractCity').val()) != "")
				|| (jQuery.trim($('#form_ac_s12_PractState').val()) != "")
				|| (jQuery.trim($('#form_ac_s12_PractZip').val()) != "")
				|| (jQuery.trim($('#form_ac_s12_PractPlanEmail').val()) != "")
				|| (jQuery.trim($('#form_ac_s12_PractPatCorresEmail').val()) != "")
				|| (jQuery.trim($('#form_ac_s12_prePractPhone').val()) != "")
				|| (jQuery.trim($('#form_ac_s12_prePractfax').val()) != "")
				|| (jQuery.trim($('#form_ac_s12_lang_spok_inter').val()) != "")
				|| (jQuery.trim($('#form_ac_s12_pract_pat_hand_access').val()) != "")
				|| (jQuery.trim($('#form_ac_s12_pract_pat_wheelchair_access').val()) != "")) {
				return false;
			}
			else return true;
		}
		else return true;
	}, "This field is required.");

	// Conditional Validation for " Update or add your Organizational Clinic or Corporate NPI"
	$.validator.addMethod("conditionalCheckOrgClinicCorp", function(value, element) {
		if ($(element).closest(".toggler").is(":visible")) {
			// current field is having value then conditional check not required
			if (jQuery.trim(value) != "") {
				return true;
			}

			//any of the below field is having value then current field is required
			if ((jQuery.trim($("#form_ac_s17_practAddrs1").val()) != "")
				|| (jQuery.trim($("#form_ac_s17_practAddrs1").val()) != "")
				|| (jQuery.trim($('#form_ac_s17_practAddrs2').val()) != "")
				|| (jQuery.trim($('#form_ac_s17_practCity').val()) != "")
				|| (jQuery.trim($('#form_ac_s17_practState').val()) != "")
				|| (jQuery.trim($('#form_ac_s17_practZip').val()) != "")
				|| (jQuery.trim($('#updated_org_corp_NPI').val()) != "")
				|| (jQuery.trim($('#practice_office_TIN').val()) != "")) {
				return false;
			}
			else return true;
		}
		else return true;
	}, "This field is required.");
	// Conditional Validation for " Close a practice location"
	$.validator.addMethod("conditionalCheckClosePracLoc", function(value, element) {
		if ($(element).closest(".toggler").is(":visible")) {
			// current field is having value then conditional check not required
			if (jQuery.trim(value) != "") {
				return true;
			}

			//any of the below field is having value then current field is required
			if ((jQuery.trim($("#form_ac_s13_preAddrs1").val()) != "")
				|| (jQuery.trim($("#form_ac_s13_preAddrS2").val()) != "")
				|| (jQuery.trim($('#form_ac_s13_preCity').val()) != "")
				|| (jQuery.trim($('#form_ac_s13_preState').val()) != "")
				|| (jQuery.trim($('#form_ac_s13_preZip').val()) != "")) {
				return false;
			}
			else return true;
		}
		else return true;
	}, "This field is required.");
	// Conditional Validation for " Change your billing or correspondence address"
	$.validator.addMethod("conditionalCheckBillCorrAdd", function(value, element) {
		if ($(element).closest(".toggler").is(":visible")) {
			// current field is having value then conditional check not required
			if (jQuery.trim(value) != "") {
				return true;
			}

			//any of the below field is having value then current field is required
			if ((jQuery.trim($("#form_ac_s14_preAddrs1").val()) != "")
				|| (jQuery.trim($("#form_ac_s14_preAddrS2").val()) != "")
				|| (jQuery.trim($('#form_ac_s14_preCity').val()) != "")
				|| (jQuery.trim($('#form_ac_s14_preState').val()) != "")
				|| (jQuery.trim($('#form_ac_s14_preZip').val()) != "")
				|| (jQuery.trim($('#form_ac_s14_Addrs1').val()) != "")
				|| (jQuery.trim($('#form_ac_s14_AddrS2').val()) != "")
				|| (jQuery.trim($('#form_ac_s14_City').val()) != "")
				|| (jQuery.trim($('#form_ac_s14_State').val()) != "")
				|| (jQuery.trim($('#form_ac_s14_Zip').val()) != "")
				|| (jQuery.trim($('#form_ac_s14_corrAddrs1').val()) != "")
				|| (jQuery.trim($('#form_ac_s14_corrAddrS2').val()) != "")
				|| (jQuery.trim($('#form_ac_s14_corrCity').val()) != "")
				|| (jQuery.trim($('#form_ac_s14_preState').val()) != "")
				|| (jQuery.trim($('#form_ac_s14_corrState').val()) != "")
				|| (jQuery.trim($('#form_ac_s14_corrZip').val()) != "")) {
				return false;
			}
			else return true;
		}
		else return true;
	}, "This field is required.");
	// Conditional Validation for " Change in your acceptance of new patients"
	$.validator.addMethod("conditionalCheckAcceptPatients", function(value, element) {
		if ($(element).closest(".toggler").is(":visible")) {
			// current field is having value then conditional check not required
			if (jQuery.trim(value) != "") {
				return true;
			}

			//any of the below field is having value then current field is required
			if ((jQuery.trim($("#form_ac_s16_practAddrs1").val()) != "")
				|| (jQuery.trim($("#form_ac_s16_practAddrs2").val()) != "")
				|| (jQuery.trim($('#form_ac_s16_practCity').val()) != "")
				|| (jQuery.trim($('#form_ac_s16_practState').val()) != "")
				|| (jQuery.trim($('#form_ac_s16_practZip').val()) != "")
				|| (jQuery.trim($('#form_ac_s16_closing_newpat').val()) != "")
				|| (jQuery.trim($('#form_ac_s16_reopen_newpat').val()) != "")) {
				return false;
			}
			else return true;
		}
		else return true;
	}, "This field is required.");





	$.validator.addMethod("fileNameLength", function(value, element) {
		var l = element.files[0].name.length;
		return l < 201;
	}, "The Filename cannot be greater than 200 characters.");

	$.validator.addMethod("fileType", function(value, element) {
		var name = element.files[0].name;
		var patt = new RegExp("^[A-Za-z0-9@#&$'\"_\\s\\S-]+(\.(jpg|gif|doc|docx|xls|xlsx|pdf|txt|csv))$", "i");
		return patt.test(name);
	}, "The file is an invalid type or the name contains invalid characters.");

	$.validator.addMethod("commentLength", function(value, element) {
		return value.length < 201;
	}, "The Description of Attachment cannot be greater than 200 characters.");

	/** DEFAULT VALUES **/


	/** END DAFAULT VALUES **/

	/** PROVIDER MAINTENANCE FORM **/

	$('.pf_formSection .toggler').hide();

	$(".pf_sectionHead").live("click", function() {

		$(this).siblings(".toggler").toggle();
		$(this).toggleClass("pf_expand");
	});


	// $(".provider_form .pf_submit").live("click", function(){
	// $(this).closest("form").submit();
	// });

	$(".pf_tab").click(function() {

		if ($('.pf_tabsel').length > 0) {
			if (confirm('This action will clear entries in this form and open a new form.')) {
				if (!$('.pf_l_tabContainer').is(':visible')) { $('.pf_l_tabContainer').show(); }
				$(this).addClass("pf_tabsel");
				$(this).siblings('.pf_tab').removeClass("pf_tabsel");
				$('form').clearForm();
				// for clearing the error messages
				$(".provider_detail_form").last().validate().resetForm();

				$('.pf_l_msgContainer').children('div').not('.' + $(this).attr("id")).removeClass('pf_selHighlight');
				$('.pf_l_msgContainer').children('div.' + $(this).attr("id")).addClass('pf_selHighlight');

				$('.pf_formSection').children('li').not('.' + $(this).attr("id")).hide();
				$('.pf_formSection').children('li.' + $(this).attr("id")).show();

				$(this).siblings('.pf_tab').hide();
				$(".provider_submit_form").find("input[name='toemail']").val($(this).find("input[name='toemail']").val());
				$(this).show();

			}
		}
		else {
			if (!$('.pf_l_tabContainer').is(':visible')) { $('.pf_l_tabContainer').show(); }
			$(this).addClass("pf_tabsel");
			$(this).siblings('.pf_tab').removeClass("pf_tabsel");
			$('form').clearForm();

			$('.pf_l_msgContainer').children('div').not('.' + $(this).attr("id")).removeClass('pf_selHighlight');
			$('.pf_l_msgContainer').children('div.' + $(this).attr("id")).addClass('pf_selHighlight');

			$('.pf_formSection').children('li').not('.' + $(this).attr("id")).hide();
			$('.pf_formSection').children('li.' + $(this).attr("id")).show();

			$(this).siblings('.pf_tab').hide();
			$(".provider_submit_form").find("input[name='toemail']").val($(this).find("input[name='toemail']").val());

			$(this).show();
		}

		$('#RequestAdd').attr('checked', true);

	});

	$("#gotoTab1").click(function() { $("#pf_tab1").click(); });
	$("#gotoTab2").click(function() { $("#pf_tab2").click(); });
	$("#gotoTab3").click(function() { $("#pf_tab3").click(); });



	/** initialize form **/
	$('.pf_formSection').children('li').not('.nohide').hide();
	/** end intialization **/

	// $('.provider_form').validate({
	// ignore: "", 
	// invalidHandler: function(form, validator) {
	// var errors = validator.numberOfInvalids();
	// alert('There are '+errors +' Errors');
	// },
	// highlight: function(element, errorClass) { // This will expand the corresponding Section
	// $(element).parent().parent().show();
	// $(element).parent().parent().siblings('h2').toggleClass('pf_bold');//toggling Header
	// }	
	// });

	var LiveValidate = function() {


		$('.provider_detail_form').each(function() {

			$(this).validate({
				ignore: "",
				invalidHandler: function(form, validator) {

				},
				highlight: function(element, errorClass) { // This will expand the corresponding Section
					$(element).parent().parent().show();
					$(element).parent().parent().closest('li').children('h2').toggleClass('pf_expand', true);
				}
			});
		});

		$('.provider_detail_form').submit(function(e) {
			e.preventDefault();
		});

	};

	LiveValidate();

	// $('.provider_form').submit(function(){
	// var str = "";
	// $(this).children(".pf_formSection").children("li:visible").find('input, select').not('[value=""], [value="0"], [value="DESC"], [name="toemail"], [type="submit"], [name="toname"], [name="subject"], [name="body"]').each(function(){

	// if ($(this).attr("type") == 'hidden')
	// {
	// str = str + "\n" + jQuery.trim($(this).closest('li').children('.pf_sectionHead').children(".label").clone().children().remove().end().text()).toUpperCase() + "\n";
	// }
	// else
	// {
	// str = str + "\t" + $(this).prev("label").clone().children().remove().end().text() + " : "+ $(this).val() + "\n";
	// }

	// });

	// $(this).children('input[name="body"]').val(str);
	// //$(this).children('input[name="toemail"]').val($('.pf_tabsel').children('.toemail').val());
	// return true;
	// });

	$('.provider_submit_form').live("submit", function(e) {

		var errors = 0;
		var str = "";
		var sectionheader = "";

		$('.provider_detail_form').each(function() {
			$(this).validate().form();
			errors = errors + $(this).validate().numberOfInvalids();
		});

		if (errors > 0) {
			$('.provider_detail_form').each(function() {
				if (!$(this).validate().valid()) {
					return false;
				}
			});

			alert('There are ' + errors + ' Errors');
		}
		else {
			// Billing and correspondence address will default to the practice office address if those addresses are left blank in Change your billing or correspondence address
			if ((jQuery.trim($("#form_ac_s14_Addrs1").val()) == "")
				&& (jQuery.trim($("#form_ac_s14_AddrS2").val()) == "")
				&& (jQuery.trim($('#form_ac_s14_City').val()) == "")
				&& (jQuery.trim($('#form_ac_s14_State').val()) == "")
				&& (jQuery.trim($('#form_ac_s14_Zip').val()) == "")) {
				$("#form_ac_s14_Addrs1").val($("#form_ac_s14_preAddrs1").val());
				$("#form_ac_s14_AddrS2").val($("#form_ac_s14_preAddrS2").val());
				$('#form_ac_s14_City').val($("#form_ac_s14_preCity").val());
				$('#form_ac_s14_State').val($("#form_ac_s14_preState").val());
				$('#form_ac_s14_Zip').val($("#form_ac_s14_preZip").val());
			}
			if ((jQuery.trim($("#form_ac_s14_corrAddrs1").val()) == "")
				&& (jQuery.trim($("#form_ac_s14_corrAddrS2").val()) == "")
				&& (jQuery.trim($('#form_ac_s14_corrCity').val()) == "")
				&& (jQuery.trim($('#form_ac_s14_corrState').val()) == "")
				&& (jQuery.trim($('#form_ac_s14_corrZip').val()) == "")) {
				$("#form_ac_s14_corrAddrs1").val($("#form_ac_s14_preAddrs1").val());
				$("#form_ac_s14_corrAddrS2").val($("#form_ac_s14_preAddrS2").val());
				$('#form_ac_s14_corrCity').val($("#form_ac_s14_preCity").val());
				$('#form_ac_s14_corrState').val($("#form_ac_s14_preState").val());
				$('#form_ac_s14_corrZip').val($("#form_ac_s14_preZip").val());
			}


			$('.provider_detail_form').each(function() {

				str = str + jQuery.trim($(".pf_data_tab_content").first().text().toUpperCase()) + "\n";

				$(this).children(".pf_formSection").children(".pf_section").each(function() {

					if (($(this).find('fieldset').find('input,select').not('[value=""], [value="0"], [value="DESC"],[value=" "], [type="submit"], [type="radio"], [type="checkbox"]').length > 0) || ($(this).find('input[type="radio"]:checked,input[type="checkbox"]:checked').length > 0)) {
						/* test */

						/* end test*/
						sectionheader = $(this).children('.pf_sectionHead').children('.label').clone().children().remove().end().text().replace(/'/g, "\'");
						//alert(sectionheader);

						str = str + "\n\t" + jQuery.trim((sectionheader).toUpperCase()) + "\n";

						//alert(str);

						$(this).children('.toggler').children('fieldset').each(function() {
							if ($(this).children('legend').length > 0) {
								str = str + "\t\t" + $(this).children('legend').text().replace(/'/g, "\'") + "\n";
							}

							$(this).find('legend, input, select').not('[value=""], [legth="0"], [value="0"], [value="DESC"],[value=" "], [type="submit"]').each(function() {
								var tagLegend = $(this).prop('tagName').toLowerCase();
								if (tagLegend == 'legend') {
									if ($(this).prop('id') == 'showInMail') { // to print the legend inside legend
										str = str + "\t\t  " + $(this).text().replace(/'/g, "\'") + "\n";
									}
								} else if (($(this).attr("type") == "radio" || $(this).attr("type") == "checkbox") && $(this).is(':checked') === false) {
								}
								else {
									str = str + "\t\t\t" + $(this).prevAll("label").not(".exclude").first().clone().children().remove().end().text() + " : " + $(this).val() + "\n";
								}
							});

						});
					}
				});
			});



			$('input[name="body"]').val(str);
			$(".provider_submit_form").find("input[name='toemail']").val($('.pf_tabsel').find("input[name='toemail']").val());
			$(".provider_submit_form").find("input[name='Contact_Person_Last_Name']").val($('.provider_detail_form').find("input[name='Contact_Person_Last_Name']").val());
			$(".provider_submit_form").find("input[name='Contact_Person_Last_Name_2']").val($('.provider_detail_form').find("input[name='Contact_Person_Last_Name_2']").val());
			$(".provider_submit_form").find("input[name='Contact_Person_First_Name']").val($('.provider_detail_form').find("input[name='Contact_Person_First_Name']").val());
			$(".provider_submit_form").find("input[name='Contact_Person_First_Name_2']").val($('.provider_detail_form').find("input[name='Contact_Person_First_Name_2']").val());
			$(".provider_submit_form").find("input[name='Email_Address']").val($('.provider_detail_form').find("input[name='Email_Address']").val());
			$(".provider_submit_form").find("input[name='Email_Address_2']").val($('.provider_detail_form').find("input[name='Email_Address_2']").val());
			$(".provider_submit_form").find("input[name='Phone_Number']").val($('.provider_detail_form').find("input[name='Phone_Number']").val());
			$(".provider_submit_form").find("input[name='Phone_Number_2']").val($('.provider_detail_form').find("input[name='Phone_Number_2']").val());
			$(".provider_submit_form").find("input[name='Effective_Date']").val($('.provider_detail_form').find("input[name='Effective_Date']").val());
			$(".provider_submit_form").find("input[name='Effective_Date_2']").val($('.provider_detail_form').find("input[name='Effective_Date_2']").val());
			$(".provider_submit_form").find("input[name='Gen_Provider_Last_Name']").val($('.provider_detail_form').find("input[name='Gen_Provider_Last_Name']").val());
			$(".provider_submit_form").find("input[name='Gen_Provider_Last_Name_2']").val($('.provider_detail_form').find("input[name='Gen_Provider_Last_Name_2']").val());
			$(".provider_submit_form").find("input[name='Gen_Provider_First_Name']").val($('.provider_detail_form').find("input[name='Gen_Provider_First_Name']").val());
			$(".provider_submit_form").find("input[name='Gen_Provider_First_Name_2']").val($('.provider_detail_form').find("input[name='Gen_Provider_First_Name_2']").val());
			$(".provider_submit_form").find("input[name='Gen_Ind_TIN']").val($('.provider_detail_form').find("input[name='Gen_Ind_TIN']").val());
			$(".provider_submit_form").find("input[name='GenSec_Prov_Ded_NPI']").val($('.provider_detail_form').find("input[name='GenSec_Prov_Ded_NPI']").val());
			$(".provider_submit_form").find("input[name='Gen_Practice_Name_2']").val($('.provider_detail_form').find("input[name='Gen_Practice_Name_2']").val());
			$(".provider_submit_form").find("input[name='Gen_Pract_TIN_2']").val($('.provider_detail_form').find("input[name='Gen_Pract_TIN_2']").val());

			$(".provider_submit_form").find("input[name='uploadFileName']").val($('.provider_upload_form').find("input[name='uploadFileName']").val());
			$(".provider_submit_form").find("input[name='uploadFileSize']").val($('.provider_upload_form').find("input[name='uploadFileSize']").val());
			$(".provider_submit_form").find("input[name='uploadFileComment']").val($('.provider_upload_form').find("input[name='uploadFileComment']").val());

			return true;

		}

		e.preventDefault();
	});

	$(".delete").live('click', function(e) {
		if (!Array.prototype.indexOf) {
			Array.prototype.indexOf = function(val) {
				return jQuery.inArray(val, this);
			};
		}

		var filename = $(this).parent().parent().find("td:first").html();
		var fileArray = document.forms[1].uploadFileName.value.split('::');
		var index = fileArray.indexOf(filename);
		if (index > -1) {
			var sizeArray = document.forms[1].uploadFileSize.value.split('::');
			var fileSize = sizeArray[index];
			var commentArray = document.forms[1].uploadFileComment.value.split('::');
			fileArray.splice(index, 1);
			sizeArray.splice(index, 1);
			commentArray.splice(index, 1);
			document.forms[1].uploadFileName.value = fileArray.join('::');
			document.forms[1].uploadFileSize.value = sizeArray.join('::');
			document.forms[1].uploadFileComment.value = commentArray.join('::');
			document.forms[1].totalFileSize.value = Number(document.forms[1].totalFileSize.value) - Number(fileSize);
		}
		$(this).parent().parent().remove();
		e.preventDefault();
	});

	$('.provider_upload_form').live("submit", function(e) {

		var errors = 0;
		var str = "";

		$('.provider_upload_form').each(function() {
			$(this).validate().form();
			errors = errors + $(this).validate().numberOfInvalids();
		});

		if (errors > 0) {
			$(this).each(function() {
				if (!$(this).validate().valid()) {
					$('.pf_data_tab_content:eq(' + $(this).prevAll('.provider_upload_form').length + ')').click();
					return false;
				}
			});

			alert('There are ' + errors + ' Errors');
		}
		else {
			var formData = new FormData();
			var file = document.getElementById("fileContentData").files[0];
          	var temp = document.createElement('div');
          	temp.textContent = document.getElementById("uploadDocComment").value;
          	var comment = temp.textContent;
			if ((comment == null) || (comment == ''))
				comment = ' ';
			var action = document.getElementById("formUpdateAction").value;
			var pattCom = new RegExp("[^<>\]*");

			if (document.forms[1].uploadFileName.value.indexOf(file.name) != -1) {
				alert('The File Name chosen is already Uploaded.');
			}
			else if (convertNumberToMB(Number(document.forms[1].totalFileSize.value) + Number(file.size)) > 10) {
				alert('The total file size of ' + convertNumberToMB(Number(document.forms[1].totalFileSize.value) + Number(file.size)) + ' MB has exceeded the limit.');
			}
			else if (!pattCom.test(comment)) {
				alert('The Description of Attachment contains invalid characters.');
			}
			else {

				formData.append("fileContentData", file);
				formData.append("uploadDocComment", comment);
				formData.append("formUpdateAction", action);

				$.ajax({
					type: "POST",
					url: "/mwpmf/message/PDMControllerServlet",
					data: formData,
					processData: false,
					contentType: false,
					success: function(response) {
						document.forms[1].uploadFileName.value = addNewRecord(document.forms[1].uploadFileName.value, file.name);
						document.forms[1].uploadFileSize.value = addNewRecord(document.forms[1].uploadFileSize.value, file.size);
						document.forms[1].uploadFileComment.value = addNewRecord(document.forms[1].uploadFileComment.value, comment);
						document.forms[1].totalFileSize.value = Number(file.size) + Number(document.forms[1].totalFileSize.value);
						$('#fileListTable').append('<tr><td>' + file.name + '</td>'
							+ '<td>' + convertNumberToMB(file.size) + '</td>'
							+ '<td>' + comment + '</td>'
							+ '<td><a href="" class="delete">Remove</a></td></tr>');
						clearFileInput(document.getElementById("fileContentData"));
						document.getElementById("uploadDocComment").value = '';
					},
					error: function(jqXHR, textStatus, errorThrown) {
						console.log(textStatus);
						if (jqXHR.status == 403) {
							alert('Selected file does not pass the virus scan.The file has not been uploaded.');
						} else if (jqXHR.responseText !== '') {
							alert(jqXHR.responseText);
						} else {
							alert(errorThrown);
						}
					}
				});

				//var xhr = new XMLHttpRequest();
				//xhr.open("POST", "PDMControllerServlet");
				//xhr.send(formData);

			}



			function addNewRecord(text, addtext) {
				if (text == null || text == '') {
					text = addtext;
				} else {
					text = text + '::' + addtext;
				}
				return text;
			}

			function clearFileInput(ctrl) {
				try {
					ctrl.value = null;
				} catch (ex) { }
				if (ctrl.value) {
					ctrl.parentNode.replaceChild(ctrl.cloneNode(true), ctrl);
				}
			}


			e.preventDefault();

		}

		e.preventDefault();
	});

	function convertNumberToMB(number) {
		var mb = (number / 1024) / 1024;
		return (Math.round(mb * 100) / 100).toFixed(2);
	}

	/** END OF PROVIDER MAINTENANCE FORM **/

	/** NEW PROVIDER FORM **/
	var switchtab = function() {
		var index = $(".pf_tabsel").prevAll(".pf_data_tab_content").length;
		$(".provider_detail_form:visible").hide();
		$(".provider_detail_form:eq(" + index + ")").show();
	};

	$(".pf_data_tab_new").click(function() {
		if (!$(".pf_close_tab").first().is(":visible")) {
			$(".pf_close_tab").show();
		}
		$(this).before($(".pf_data_tab").first().clone().removeClass("pf_tabsel"));

		$(".pf_data_tab_content").removeClass("pf_tabsel");
		$(".pf_data_tab_content").last().addClass("pf_tabsel");

		$(".provider_detail_form").last().after($(".provider_detail_form").first().clone().clearForm());

		$(".provider_detail_form").last().children('.pf_formSection').children('.pf_section').children('.toggler').hide();
		$(".provider_detail_form").last().children('.pf_formSection').children('.pf_section').children('.pf_sectionHead').removeClass('pf_expand');




		if ($(".pf_data_tab_content").length >= 10) {
			$(this).hide();
		}

		LiveValidate();
		switchtab();
		$(".provider_detail_form").last().validate().resetForm();
	});

	$(".pf_data_tab_content").live("click", function() {

		$(this).addClass("pf_tabsel");
		$(this).siblings('.pf_data_tab').removeClass("pf_tabsel");
		$(".provider_submit_form").find("input[name='toemail']").val($(this).find("input[name='toemail']").val());

		switchtab();
	});

	$(".pf_close_tab").live("click", function() {

		if (confirm('Close current tab?')) {

			var index = $(this).parent(".pf_data_tab_content").prevAll(".pf_data_tab_content").length;

			$(this).closest(".pf_data_tab_content").remove();
			$(".provider_detail_form:eq(" + index + ")").remove();

			if (index < ($(".pf_data_tab_content").length - 1)) {

				$(".pf_data_tab_content:eq(" + index + ")").click();
			}
			else {
				$(".pf_data_tab_content").last().click();
			}

			$(".pf_data_tab_new").show();

			if ($(".pf_close_tab").length <= 1) $(".pf_close_tab").hide();
			LiveValidate();

		}
	});


	/** END OF NEW PROVIDER FORM **/

});	//END of Jquery