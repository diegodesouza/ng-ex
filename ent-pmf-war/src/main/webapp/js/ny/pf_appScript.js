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
	var providerType = ["Medical Doctor (MD)", "Doctor of Dental Surgery (DDS)", "Doctor of Dental Medicine (DMD)", "Doctor of Podiatric Medicine (DPM)", "Doctor of Chiropractic (DC)", "Osteopathic Doctor (DO)", "Acupuncturist", "Alcohol/Drug Counselor", "Audiologist", "Biofeedback Technician", "Certified Registered Nurse", "Anesthetist", "Christian Science Practitioner", "Clinical Nurse Specialist", "Clinical Psychologist", "Clinical Social Worker", "Dietitian", "Licensed Practical Nurse", "Locum Tenen", "Marriage/Family Therapist", "Naturopath", "Neuropsychologist", "Midwife", "Nurse Midwife", "Nurse Practitioner", "Nutritionist", "Occupational Therapist", "Optician", "Optometrist", "Pharmacist", "Physical Therapist", "Physician Assistant", "Professional Counselor", "Registered Nurse", "Registered Nurse First Assistant", "Respiratory Therapist", "Speech Therapist"];
	var specialty = ["Adolescent Gynecology", "Adolescent Psychiatry", "Allergy & Immunology", "Ambulance", "Ambulatory Surgery", "Anorectal Surgery", "Audiology", "Breast Surgery", "Cardiac Catheterization", "Cardiac Surgery", "Cardiology", "Cardiovascular Thoracic Surgery", "Cataracts Specialty", "Certified Diabetes Educator", "Certified Laboratory", "Certified Licensed Acupuncturist", "Certified Nurse Midwife", "Certified Orthotics and Prosthetics Co", "Child Psychiatry", "Chiropractic", "Clinical Psychologist", "Colon and Rectal Surgery", "Corneal Disorders", "Dermatological Surgery", "Dermatology", "Diagnostic Radiology", "Dietitian", "Diplomate in Podiatric Surgery", "Durable Medical Equipment", "Echocardiography", "EKG Interpretation", "Endocrinology", "Endocrinology Obstetrics", "Family Practice - Board Certified", "Family Practice - Board Eligible", "Gastroenterology", "General Practice", "General Surgery", "Genetics Adults", "Geriatrics", "Glaucoma", "Gynecologic Oncology", "Gynecology", "Hand Surgery", "Head & Neck Surgery", "Hematology", "High Risk Obstetrics", "Home Infusion", "Infectious Diseases", "Internal Medicine Board Certified", "Internal Medicine Board Eligible", "Internal Medicine Geriatrics", "Interventional Radiology", "Licensed Marriage and Family Therapist", "Licensed Chemical Dependency Counselor", "Licensed Professional Counselor", "Licensed Psychiatric Agency", "Mammography", "Maxillofacial Surgery", "Medical Oncology", "Multispecialty Group", "Neonatology", "Nephrology", "Neuro Opthalmology", "Neurology", "Neuroradiology", "Neurosurgery", "Nuclear Cardiology", "Nuclear Medicine Radiology", "Nurse Practitioner", "Nutritional Medicine", "Obstetrics & Gynecology", "Occupational Therapy", "Ocular & Orbital Tumors", "Oncology", "Ophthalmology", "Opthalmic Plastic", "Oral and Maxillofacial Surgery", "Oral Surgery", "Orthopedic Surgery", "Otolaryngology", "Pain Management", "Pastoral Counselor", "Pediatric Adolescent Medicine", "Pediatric Allergy & Immunology", "Pediatric Behavioral Disorders", "Pediatric Cardiology", "Pediatric Cardiothoracic Surgery", "Pediatric Critical Care ", "Pediatric Dermatology", "Pediatric Develop Disorders", "Pediatric Endocrinology", "Pediatric Gastroenterology", "Pediatric Genetics", "Pediatric Hematology Oncology", "Pediatric Infectious Diseases", "Pediatric Neonatal", "Pediatric Nephrology", "Pediatric Neurology", "Pediatric Neurosurgery", "Pediatric Oncology", "Pediatric Ophthalmology", "Pediatrics Orthopedics", "Pediatric Otolaryngology", "Pediatric Pulmonary Medicine", "Pediatric Radiology", "Pediatric Rheumatology", "Pediatric Surgery", "Pediatric Urology", "Pediatrics", "Perinatology High Risk Obstetrics", "Peripheral Vascular Disease", "Physical Medicine & Rehabilitation", "Physical Therapy", "Plastic Reconstructive Surgery", "Podiatric Orthopedics", "Podiatry", "Proctology", "Psychiatric Neurology", "Psychiatric Nurse", "Psychiatry", "Psychologist Individual or Group", "Pulmonary Medicine", "Radiation Oncology", "Radiology", "Reconstructive Surgery", "Registered Physical Occupational Therapist", "Reproductive Endocrinology", "Retail Health Clinic", "Rheumatology", "Roentgenology", "Sleep Disorders", "Social Worker", "Speech Therapy", "Sports Medicine", "Surgical Critical Care", "Surgical Oncology", "Therapeutic Roentgenology", "Therapeutically Certified Optometrist", "Transplant Surgery Liver Kidney", "Urgent Care Center", "Urogynecology", "Urology", "Vascular Surgery"];
	var areasOfExpertise = ["Amputation Rehabilitation", "Aqua", "Autism (If yes provide individual name, individual license number, and  NPI number)", "ABA (If yes provide individual name, individual license number, and  NPI number)", "Geriatric Therapy", "Hand Therapy", "Lymphatic Therapy", "Neurological Therapy", "Occupational Therapy", "Pediatric Occupational Therapy", "Physical Therapy", "Pediatric Physical Therapy", "Speech Therapy", "Pediatric Speech Therapy", "Sports Injury", "Women's Health", "Other"];

	$("input.pf_provType").live("focus", function() {
		$(this).autocomplete(
			{
				source: function(request, response) {
					var matches = $.map(providerType, function(tag) {
						if (tag.toUpperCase().indexOf(request.term.toUpperCase()) === 0) {
							return tag;
						}
					});
					response(matches);
				}, minLength: 0
			}
		);

		$(this).autocomplete("search", $(this).val());
	});

	$("input.pf_specialty").live("focus", function() {
		$(this).autocomplete(
			{
				source: function(request, response) {
					var matches = $.map(specialty, function(tag) {
						if (tag.toUpperCase().indexOf(request.term.toUpperCase()) === 0) {
							return tag;
						}
					});
					response(matches);
				}, minLength: 0
			}
		);
		$(this).autocomplete("search", $(this).val());
	});

	$("input.pf_ancExpertise").live("focus", function() {
		$(this).autocomplete(
			{
				source: function(request, response) {
					var matches = $.map(areasOfExpertise, function(tag) {
						if (tag.toUpperCase().indexOf(request.term.toUpperCase()) === 0) {
							return tag;
						}
					});
					response(matches);
				}, minLength: 0
			}
		);
		$(this).autocomplete("search", $(this).val());
	});

	// ******** DATA SOURCE ENDS *************

	$.validator.addMethod("time", function(value, element) {
		return this.optional(element) || /^(([0-1]?[0-9])|([2][0-3])):([0-5]?[0-9])(:([0-5]?[0-9]))?$/i.test(value);
	}, "Please enter a valid time.");

	$.validator.addMethod("requireYes", function(value, element) {
		return this.optional(element) || jQuery.trim(value) == "" || value.toUpperCase() == "YES";

	}, "Only Yes is accepted.");

	$.validator.addMethod("requireOption", function(value, element) {

		return jQuery.trim(value) != "";

	}, "This field is required.");

	$.validator.addMethod("TIN", function(value, element) {
		return this.optional(element) || value.length == 9;
	}, "The Tax ID Number is 9 characters in length.  Please enter all 9 characters.");

	$.validator.addMethod("NPI", function(value, element) {
		return this.optional(element) || value.length == 10;
	}, "The National Provider Identifier is 10 characters in length.  Please enter all 10 characters.");

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
	// Conditional Validation for "Close a practice location"
	$.validator.addMethod("conditionalCheckClosePractice", function(value, element) {
		// current field is having value then conditional check not required
		if (element.type == 'checkbox' && $('#per').is(':checked') == true) {
			return true;
		}
		if (element.type != 'checkbox' && jQuery.trim(value) != "") {
			return true;
		}

		//any of the below field is having value then current field is required
		if ((jQuery.trim($("#form_ac_s3_preAddrs1").val()) != "")
			|| (jQuery.trim($("#form_ac_s3_preAddrS2").val()) != "")
			|| (jQuery.trim($('#form_ac_s3_preCity').val()) != "")
			|| (jQuery.trim($('#form_ac_s3_preState').val()) != "")
			|| (jQuery.trim($('#form_ac_s3_preZip').val()) != "")
			|| (jQuery.trim($('#form_ac_s3_preCounty').val()) != "")
			|| (jQuery.trim($('#s3_tReason').val()) != "")
			|| $('#per').is(':checked') == true) {
			return false;
		}
		else return true;
	}, "This field is required.");
	// Conditional Validation for "Accepting new patients"
	$.validator.addMethod("conditionalCheckChangeAccept", function(value, element) {
		// current field is having value then conditional check not required
		if (jQuery.trim(value) != "") {
			return true;
		}

		//any of the below field is having value then current field is required
		if ((jQuery.trim($("#form_ac_s9_newpat").val()) != "")
			|| (jQuery.trim($("#form_ac_s9_preAddrs1").val()) != "")
			|| (jQuery.trim($('#form_ac_s9_preAddrS2').val()) != "")
			|| (jQuery.trim($('#form_ac_s9_preCity').val()) != "")
			|| (jQuery.trim($('#form_ac_s9_preState').val()) != "")
			|| (jQuery.trim($('#form_ac_s9_preZip').val()) != "")) {
			return false;
		}
		else return true;
	}, "This field is required.");

	// Conditional Validation for "Change in MAT"
	$.validator.addMethod("conditionalCheckChangeMAT", function(value, element) {
		// current field is having value then conditional check not required
		if (jQuery.trim(value) != "") {
			return true;
		}

		//any of the below field is having value then current field is required
		if (($("#form_ac_s20_indAuth").prop('disabled') == false && jQuery.trim($("#form_ac_s20_indAuth").val()) != "")
			|| ($("#form_ac_s20_indCouns").prop('disabled') == false && jQuery.trim($("#form_ac_s20_indCouns").val()) != "")
			|| ($("#form_ac_s20_grpCert").prop('disabled') == false && jQuery.trim($("#form_ac_s20_grpCert").val()) != "")
			|| ($("#form_ac_s20_grpOffer").prop('disabled') == false && jQuery.trim($("#form_ac_s20_grpOffer").val()) != "")
			|| ($("#form_ac_s20_grpCouns").prop('disabled') == false && jQuery.trim($("#form_ac_s20_grpCouns").val()) != "")
			|| (jQuery.trim($("#form_ac_s20_preAddrs1").val()) != "")
			|| (jQuery.trim($('#form_ac_s20_preAddrS2').val()) != "")
			|| (jQuery.trim($('#form_ac_s20_preCity').val()) != "")
			|| (jQuery.trim($('#form_ac_s20_preState').val()) != "")
			|| (jQuery.trim($('#form_ac_s20_preZip').val()) != "")) {
			return false;
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

	var loadGenSec = function() {
		if (this.value == "Group Practice") {
			$("#Provider_Last_Name_Gen_Individ").prop('disabled', true);
			$("#Provider_First_Name_Gen_Individ").prop('disabled', true);
			$("#Provider_Middle_Name_Gen_Individ").prop('disabled', true);
			$("#Professional_Title_Gen_Individ").prop('disabled', true);
			$("#Individual_Tax_Identification_Number_Gen_Individ").prop('disabled', true);
			$("#Individual_Provider_Identification_Number_Gen_Individ").prop('disabled', true);
			$("#Individual_Provider_EPIN_Gen_Individ").prop('disabled', true);
			$("#Telehealth_Services_Gen_Individ").prop('disabled', true);
			$("#Provider_Practice_Name_Gen_Prac").prop('disabled', false);
			$("#Practice_Tax_Identification_Number_Gen_Prac").prop('disabled', false);
			$("#Org_NPI_Gen_Prac").prop('disabled', false);
			$("#roster_Attached_Gen_Prac").prop('disabled', false);
			$("#form_ac_s20_indAuth").prop('disabled', true);
			$("#form_ac_s20_indCouns").prop('disabled', true);
			$("#form_ac_s20_grpCert").prop('disabled', false);
			$("#form_ac_s20_grpOffer").prop('disabled', false);
			$("#form_ac_s20_grpCouns").prop('disabled', false);
			$("#form_ac_s1_indPracAuth").prop('disabled', true);
			$("#form_ac_s1_indPracCouns").prop('disabled', true);
			$("#form_ac_s1_grpPracCert").prop('disabled', false);
			$("#form_ac_s1_grpPracOffer").prop('disabled', false);
			$("#form_ac_s1_grpPracCouns").prop('disabled', false);
			$("#provider_details_fldset").fadeOut();
			$("#practice_details_fldset").fadeIn();
			$("#ind_mat_fldset").fadeOut();
			$("#grp_mat_fldset").fadeIn();
			$("#form_ind_pracmat").fadeOut();
			$("#form_grp_pracmat").fadeIn();
		} else if (this.value == "Individual Provider") {
			$("#Provider_Practice_Name_Gen_Prac").prop('disabled', true);
			$("#Practice_Tax_Identification_Number_Gen_Prac").prop('disabled', true);
			$("#Org_NPI_Gen_Prac").prop('disabled', true);
			$("#roster_Attached_Gen_Prac").prop('disabled', true);
			$("#Provider_Last_Name_Gen_Individ").prop('disabled', false);
			$("#Provider_First_Name_Gen_Individ").prop('disabled', false);
			$("#Provider_Middle_Name_Gen_Individ").prop('disabled', false);
			$("#Professional_Title_Gen_Individ").prop('disabled', false);
			$("#Individual_Tax_Identification_Number_Gen_Individ").prop('disabled', false);
			$("#Individual_Provider_Identification_Number_Gen_Individ").prop('disabled', false);
			$("#Individual_Provider_EPIN_Gen_Individ").prop('disabled', false);
			$("#Telehealth_Services_Gen_Individ").prop('disabled', false);
			$("#form_ac_s20_indAuth").prop('disabled', false);
			$("#form_ac_s20_indCouns").prop('disabled', false);
			$("#form_ac_s20_grpCert").prop('disabled', true);
			$("#form_ac_s20_grpOffer").prop('disabled', true);
			$("#form_ac_s20_grpCouns").prop('disabled', true);
			$("#form_ac_s1_indPracAuth").prop('disabled', false);
			$("#form_ac_s1_indPracCouns").prop('disabled', false);
			$("#form_ac_s1_grpPracCert").prop('disabled', true);
			$("#form_ac_s1_grpPracOffer").prop('disabled', true);
			$("#form_ac_s1_grpPracCouns").prop('disabled', true);
			$("#practice_details_fldset").fadeOut();
			$("#provider_details_fldset").fadeIn();
			$("#ind_mat_fldset").fadeIn();
			$("#grp_mat_fldset").fadeOut();
			$("#form_ind_pracmat").fadeIn();
			$("#form_grp_pracmat").fadeOut();
		} else {
			$("#practice_details_fldset").fadeOut();
			$("#provider_details_fldset").fadeOut();
			$("#ind_mat_fldset").fadeOut();
			$("#grp_mat_fldset").fadeOut();
			$("#form_ind_pracmat").fadeOut();
			$("#form_grp_pracmat").fadeOut();
			$("#Provider_Last_Name_Gen_Individ").prop('disabled', true);
			$("#Provider_First_Name_Gen_Individ").prop('disabled', true);
			$("#Provider_Middle_Name_Gen_Individ").prop('disabled', true);
			$("#Professional_Title_Gen_Individ").prop('disabled', true);
			$("#Individual_Tax_Identification_Number_Gen_Individ").prop('disabled', true);
			$("#Individual_Provider_Identification_Number_Gen_Individ").prop('disabled', true);
			$("#Individual_Provider_EPIN_Gen_Individ").prop('disabled', true);
			$("#Telehealth_Services_Gen_Individ").prop('disabled', true);
			$("#Provider_Practice_Name_Gen_Prac").prop('disabled', true);
			$("#Practice_Tax_Identification_Number_Gen_Prac").prop('disabled', true);
			$("#Org_NPI_Gen_Prac").prop('disabled', true);
			$("#roster_Attached_Gen_Prac").prop('disabled', true);
			$("#form_ac_s20_indAuth").prop('disabled', true);
			$("#form_ac_s20_indCouns").prop('disabled', true);
			$("#form_ac_s20_grpCert").prop('disabled', true);
			$("#form_ac_s20_grpOffer").prop('disabled', true);
			$("#form_ac_s20_grpCouns").prop('disabled', true);
			$("#form_ac_s1_indPracAuth").prop('disabled', true);
			$("#form_ac_s1_indPracCouns").prop('disabled', true);
			$("#form_ac_s1_grpPracCert").prop('disabled', true);
			$("#form_ac_s1_grpPracOffer").prop('disabled', true);
			$("#form_ac_s1_grpPracCouns").prop('disabled', true);
		}

	};

	$("#request_Type_form_slist0").live("change", loadGenSec);

	loadGenSec();

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

		$('.provider_detail_form').each(function() {
			$(this).validate().form();
			errors = errors + $(this).validate().numberOfInvalids();
		});

		if (errors > 0) {
			$('.provider_detail_form').each(function() {
				if (!$(this).validate().valid()) {
					$('.pf_data_tab_content:eq(' + $(this).prevAll('.provider_detail_form').length + ')').click();
					return false;
				}
			});

			alert('There are ' + errors + ' Errors');
		}
		else {
			$('.provider_detail_form').each(function() {

				str = str + jQuery.trim($('.pf_data_tab_content').first().text().toUpperCase()) + "\n";

				$(this).children(".pf_formSection").children('.pf_section').each(function() {

					if (($(this).find('fieldset').find('input,select').not('[value=""], [value="0"], [value="DESC"],[value=" "], [type="submit"], [type="radio"], [type="checkbox"]').length > 0) || ($(this).find('input[type="radio"]:checked,input[type="checkbox"]:checked').length > 0)) {
						/* test */

						/* end test*/
						str = str + "\n\t" + jQuery.trim(($(this).children('.pf_sectionHead').children('.label').clone().children().remove().end().text()).toUpperCase()) + "\n";

						$(this).children('.toggler').children('fieldset').each(function() {
							if ($(this).children('legend').length > 0) {
								str = str + "\t\t" + $(this).children('legend').text() + "\n";
							}

							$(this).find('input, select').not('[value=""], [legth="0"], [value="0"], [value="DESC"],[value=" "], [type="submit"]').each(function() {
								if (($(this).attr("type") == "radio" || $(this).attr("type") == "checkbox") && $(this).is(':checked') === false) {
								}
								else
									str = str + "\t\t\t" + $(this).prevAll("label").not(".exclude").first().clone().children().remove().end().text() + " : " + $(this).val() + "\n";
							});

						});
					}
				});
			});



			$('input[name="body"]').val(str);
			$(".provider_submit_form").find("input[name='toemail']").val($('.pf_tabsel').find("input[name='toemail']").val());
			$(".provider_submit_form").find("input[name='Contact_Person_Last_Name']").val($('.provider_detail_form').find("input[name='Contact_Person_Last_Name']").val());
			$(".provider_submit_form").find("input[name='Contact_Person_First_Name']").val($('.provider_detail_form').find("input[name='Contact_Person_First_Name']").val());
			$(".provider_submit_form").find("input[name='Email_Address']").val($('.provider_detail_form').find("input[name='Email_Address']").val());
			$(".provider_submit_form").find("input[name='Phone_Number']").val($('.provider_detail_form').find("input[name='Phone_Number']").val());
			$(".provider_submit_form").find("input[name='Effective_Date']").val($('.provider_detail_form').find("input[name='Effective_Date']").val());
			$(".provider_submit_form").find("input[name='Provider_Last_Name_Gen_Individ']").val($('.provider_detail_form').find("input[name='Provider_Last_Name_Gen_Individ']").val());
			$(".provider_submit_form").find("input[name='Provider_First_Name_Gen_Individ']").val($('.provider_detail_form').find("input[name='Provider_First_Name_Gen_Individ']").val());
			$(".provider_submit_form").find("input[name='Individual_Tax_Identification_Number_Gen_Individ']").val($('.provider_detail_form').find("input[name='Individual_Tax_Identification_Number_Gen_Individ']").val());
			$(".provider_submit_form").find("input[name='Individual_Provider_Identification_Number_Gen_Individ']").val($('.provider_detail_form').find("input[name='Individual_Provider_Identification_Number_Gen_Individ']").val());
			$(".provider_submit_form").find("input[name='Provider_Practice_Name_Gen_Prac']").val($('.provider_detail_form').find("input[name='Provider_Practice_Name_Gen_Prac']").val());
			$(".provider_submit_form").find("input[name='Practice_Tax_Identification_Number_Gen_Prac']").val($('.provider_detail_form').find("input[name='Practice_Tax_Identification_Number_Gen_Prac']").val());
			$(".provider_submit_form").find("input[name='Org_NPI_Gen_Prac']").val($('.provider_detail_form').find("input[name='Org_NPI_Gen_Prac']").val());

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

});	//END of the Jquery
