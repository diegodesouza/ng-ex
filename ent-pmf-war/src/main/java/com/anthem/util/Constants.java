package com.anthem.util;

/**
 * <p>Title: </p>
 * <p>Description: The global constants are stored here</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Anthem Insurance</p>
 * @author unascribed
 * @version 1.0
 */

public interface Constants
{
    /**The constant BLANK was introduced since there was code all overwhich replaced "" with " ". Before removing this code
     and testing used this constant in case some of it has to be put back  **/
    final String BLANK = "";
    final int SUCCESS = 0;
    final int FAILURE = -1;
    
    final String STRING_A = "A";
    final String STRING_D = "D";
    
    final String[] ORDIANAL_NUMBERS = {" First", " Second", " Third", " Fourth", " Fifth", " Sixth"};
    
    final String[] ORDIANAL_NUMBERS_LOWERCASE = {" first", " second", " third"};
    
    final String[] THREE_LETTERED_WEEKDAYS = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
    
	final String[] OFFICE_TIMINGS_DISPLAY = { "", "12:00 AM", "12:30 AM",
			"01:00 AM", "01:30 AM", "02:00 AM", "02:30 AM", "03:00 AM",
			"03:30 AM", "04:00 AM", "04:30 AM", "05:00 AM", "05:30 AM",
			"06:00 AM", "06:30 AM", "07:00 AM", "07:30 AM", "08:00 AM",
			"08:30 AM", "09:00 AM", "09:30 AM", "10:00 AM", "10:30 AM",
			"11:00 AM", "11:30 AM", "12:00 PM", "12:30 PM", "01:00 PM",
			"01:30 PM", "02:00 PM", "02:30 PM", "03:00 PM", "03:30 PM",
			"04:00 PM", "04:30 PM", "05:00 PM", "05:30 PM", "06:00 PM",
			"06:30 PM", "07:00 PM", "07:30 PM", "08:00 PM", "08:30 PM",
			"09:00 PM", "09:30 PM", "10:00 PM", "10:30 PM", "11:00 PM",
			"11:30 PM" };

	final String[] OFFICE_TIMINGS_VALUE = { "", "12:00AM", "12:30AM",
			"01:00AM", "01:30AM", "02:00AM", "02:30AM", "03:00AM", "03:30AM",
			"04:00AM", "04:30AM", "05:00AM", "05:30AM", "06:00AM", "06:30AM",
			"07:00AM", "07:30AM", "08:00AM", "08:30AM", "09:00AM", "09:30AM",
			"10:00AM", "10:30AM", "11:00AM", "11:30AM", "12:00PM", "12:30PM",
			"01:00PM", "01:30PM", "02:00PM", "02:30PM", "03:00PM", "03:30PM",
			"04:00PM", "04:30PM", "05:00PM", "05:30PM", "06:00PM", "06:30PM",
			"07:00PM", "07:30PM", "08:00PM", "08:30PM", "09:00PM", "09:30PM",
			"10:00PM", "10:30PM", "11:00PM", "11:30PM" };
    
    //  Changes for the state mandate on 02/10/10 start
    final String sectionGeneralInfo = "Section A";
    final String sectionReasonSubmitting = "Section B";
    final String sectionProviderInfo = "Section C";
    final String sectionWisconsin = "Section D";
    final String sectionPracticeAddress = "Section E";
    final String sectionAddressChange = "Section F";
    final String sectionOfficeLocation = "Section G";
    final String sectionPhysicians = "Section H";
    final String sectionPatientInfo = "Section I";
    final String sectionExpertise = "Section J";
    final String sectionAttachment = "Section K";
    final String sectionComments = "Section L";
    //  Changes for the state mandate on 02/10/10 end
    
    //Change to display speciality list 14/06/2012
    final String[] SPECIALITY_LIST = {" ","Acupuncturist", "Addiction Medicine", "Addiction Psychiatry",
    		"Adolescent Medicine", "Advanced Nurse Practitioner", "Aerospace Medicine", 
    		"Allergy/ Immunology", "Ambulance Service", "Ambulatory Surgical Center Grp",
    		"Anatomic/Clinical Pathology" ,"Anesthesia Assistant", "Anesthesia Critical Care",
    	    "Anesthesiology", "Athletic Trainer", "Audiology" ,"Bloodbanking/Transfusion Med",
    	    "Board Cert Assit. Behavioral Analyst", "Board Cert Behavioral Analyst", 
    	    "Cardiac Electrophysiology", "Cardiovascular Disease", "Cert Reg Nurse Anesthetist",
    	    "Certified Nurse Midwife", "Certified Surgical Assistant", "Chemical Dependency Practition",
    	    "Chemical Pathology", "Child & Adolescent Psychiatry", "Child & Adolescent Psychology", 
    	    "Child Neurology", "Chiropractic","Chiropractic Radiology", "Christian Science Nurse", 
    	    "Christian Science Practioner", "Clinic/Multi-Specialty Group", "Clinical and Lab Immunology", 
    	    "Clinical Neurophysiology", "Clinical Psychology", "Clinical Social Worker",
    	    "Colon & Rectal Surgery", "Counselor", "Critical Care Medicine", "Cytopathology", 
    	    "Dermatologic Immunology", "Dermatology", "Dermatopathology", "Developmental Disorder Peds",
    	    "Diabetic Educators","Diagnostic Radiology", "Durable Med Equipment Supplier",
    	    "Emergency Medicine", "Endocrinology/Diabetes", "Endodontics", "Family Practice",
    	    "Forensic Pathology", "Gastroenterology", "General Practice", "General Practice Dentistry",
    	    "General Preventive Medicine", "General Surgery", "Geriatric Psychiatry", "Geriatrics", 
    	    "Gynecologic Oncology","Gynecology", "Hand Surgery", "Hearing Aid Supplier", "Hematology",
    	    "Hematology/Oncology", "Hemophilia", "Homeopathy", "Hospice/Palliative Medicine",
    	    "Immunopathology", "Independent Laboratory", "Infectious Disease", "Internal Medicine",
    	    "IV Therapy Provider", "Lic Marriage Cnslr Fam Thrpst","Mammography Center", "Massage Therapist",
    	    "Maternal & Fetal Medicine", "Mechanotherapy", "Medical Genetics", "Medical Microbiology", 
    	    "Medical Oncology", "Medical Toxicology", "Naturopath", "Neonatal-Perinatal Medicine",
    	    "Nephrology", "Neurological Surgery", "Neurology", "Neuropathology", "Neuropsychologist", 
    	    "Neuroradiology", "Nuclear Medicine", "Nuclear Radiology", "Ob/Gyn Critical Care",
    	    "Obstetrics/Gynecology", "Occupational Health Center", "Occupational Medicine",
    	    "Occupational Therapy", "Ophthalmology", "Optician", "Optometry", "Oral & Maxillofacial Surgery",
    	    "Oriental Medicine", "Orthodontics", "Orthopaedic Surgery", "Orthotics & Prosthetics", 
    	    "Osteopathic Manipulative Med", "Otolaryngology", "Pain Management", "Pathology Oral", 
    	    "Pediatric Allergy/Immunology", "Pediatric Cardiology", "Pediatric Child Abuse", 
    	    "Pediatric Critical Care Med", "Pediatric Emergency Medicine", "Pediatric Endocrinology", 
    	    "Pediatric Gastroenterology", "Pediatric Hematology-Oncology","Pediatric Infectious Disease",
    	    "Pediatric Nephrology", "Pediatric Ophthalmology", "Pediatric Orthopedic Surgery",
    	    "Pediatric Otolaryngology", "Pediatric Pathology", "Pediatric Pulmonology", "Pediatric Radiology",
    	    "Pediatric Rheumatology", "Pediatric Sports Medicine", "Pediatric Surgery", "Pediatric Urology", 
    	    "Pediatric/Internal Medicine", "Pediatrics","Pedodontics", "Periodontics", "Physical Medicine & Rehab",
    	    "Physician Assistant", "Plastic Surgery", "Podiatry", "Proctology", "Prosthodontics", "Psychiatric Nurse", 
    	    "Psychiatry", "Pulmonary Diseases", "Radiation Oncology", "Radiological Physics", 
    	    "Radiology Center-Free Standing", "Radiology Technician", "Reg Dietician/Nutritionist", 
    	    "Reg Nurse First Assistant", "Registered Physical Therapist", "Reproductive Endocrinology", 
    	    "Retail Health Clinic", "Rheumatology", "Sleep Labs", "Sleep Medicine","Specialty Pharmacy",
    	    "Speech Pathology", "Sports Medicine", "Surgery Assistant", "Surgical Critical Care", 
    	    "Thoracic Surgery", "Undersea Medicine", "Urgent Care", "Urology","Vascular Intervent Radiology",
    	    "Vascular Surgery"};
    
    
    public static final String RESOURCE_PROPERTIES = "pmfConfig.properties";
    public static final String UPLOAD_PATH = "wlp.pmf.upload.path";
    public static final String WLPECM_ENDPOINT = "wlp.pmf.wlpecm.endPoint";
    
    public static final String DATAPOWER_ENDPOINT = "wlp.pmf.datapower.endPoint";
    public static final String SECURITY_HEADER_USERNAME = "wlp.pmf.datapower.username";
    public static final String SECURITY_HEADER_PASSWORD = "wlp.pmf.datapower.password";
    
    
    public static final String CADENTAL_BRAND = "cadental";
    public static final String CADENTAL_GOLDENWEST_BRAND = "gwdental";
    public static final String ABCBSDENTAL_BRAND = "abcbsdental";
    public static final String BCBSGADENTAL_BRAND = "bcbsgadental";
    public static final String NYEBCDENTAL_BRAND = "nyebcdental";
    public static final String NYEBCBSDENTAL_BRAND = "nyebcbsdental";
    public static final String UNICAREDENTAL_BRAND = "unicaredental";
    
    public static final String CONTENT_APPLICATION_JSON = "application/json";
    public static final String PROVIDER_APP_API_KEY = "provider.app.api.key";
    public static final String PROVIDER_PLM_SERVICE_ENDPOINT = "provider.plm.service.endpoint";
    public static final String PLM_CONNECTION_POOL_TOTAL_MAX_ROUTES = "plm.connection.pool.max.total";
    public static final String PLM_CONNECTION_POOL_MAX_PER_ROUTE = "plm.connection.pool.max.per.route";
    public static final String PROVIDER_UPLOAD_DOC_SERVICE_ENDPOINT = "provider.upload.doc.service.endpoint";   
    public static final String PROVIDER_UPLOAD_DOC_SERVICE_PARAM_REPOSITORY = "repository";
    public static final String PROVIDER_UPLOAD_DOC_SERVICE_PARAM_APPLICATIONID = "applicationId";
    public static final String PROVIDER_UPLOAD_DOC_SERVICE_REPOSITORY = "FilenetCE";
    public static final String PROVIDER_UPLOAD_DOC_SERVICE_APPLICATIONID = "PROVPORT";
    		

    //Change to display speciality list 14/06/2012
}