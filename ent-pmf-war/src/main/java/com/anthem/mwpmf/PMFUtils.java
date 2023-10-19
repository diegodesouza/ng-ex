package com.anthem.mwpmf;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.anthem.common.Address;
import com.anthem.docUpload.AttachDocUploadHelper;
import com.anthem.exception.PMFException;
import com.anthem.util.Constants;
import com.anthem.util.LogUtils;
import com.anthem.util.StringUtils;

/**
 * <p>Title: PMFUtils</p>
 * <p>Description: This calls the methods to edit the data stored in the provider object.</p>
 * This also has the database update functions used by the
 * PMFController Servlet.
 * (The database update functions need to be moved to a separate class)
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Anthem, Inc.</p>
 * @author Vicki Crim
 * @version 1.0
 */

public class PMFUtils implements Constants
{
    //private static Logger logger = ConfigManager.getInstance().getErrorLoggerInstance();
    private final static Logger logger = LogManager.getLogger(PMFUtils.class);

    public PMFUtils()
    {
    }

    static boolean editData(Provider providerData)
    {
       return providerData.validate();
    }
    public static boolean isValidFile(Provider providerData,String fileName,long fileSize,byte[] fileContent,double maxFileSize)
    {
    	return providerData.isValidFile(fileName,fileSize,fileContent,maxFileSize,providerData.getUploadDocComment());
    }

    //  Changes for the security scan on 03/05/10 start
	/**	This method checks for the hazordous characters in input fields.
	 *  there are 3 sets of conditions based on the business reasons which are
	 *  Group2 - Name and address fields - allow & ( ) and prevent < > " \ %  ; +
	 *	Group3 - Comments and reason for deleting only prevent < >  \
	 *	Group1 - All others  - prevent < > " \ %  ; ( ) &  +
	 */
	public static boolean containsBadCharacter(String value, int grpNumber) {
		boolean isBad = false;
		if (grpNumber == 1
				&& ((value.indexOf('<')!= -1)
		        || (value.indexOf('>') != -1)
		        || (value.indexOf('"') != -1)
		        || (value.indexOf('\\')!= -1)
		        || (value.indexOf('%') != -1)
		        || (value.indexOf(';') != -1)
		        || (value.indexOf('(') != -1)
		        || (value.indexOf(')') != -1)
		        || (value.indexOf('&') != -1)
		        || (value.indexOf('+') != -1)
		        || (value.indexOf('|') != -1))) {
			isBad = true;
		} else if (grpNumber == 2
				&& ((value.indexOf('<')!= -1)
				|| (value.indexOf('>') != -1)
				|| (value.indexOf('"') != -1)
				|| (value.indexOf('\\')!= -1)
				|| (value.indexOf('%') != -1)
				|| (value.indexOf(';') != -1)
				|| (value.indexOf('+') != -1)
				|| (value.indexOf('|') != -1))) {
			isBad = true;
		} else if (grpNumber == 3
				&& ((value.indexOf('<')!= -1)
		        || (value.indexOf('>') != -1)
		        || (value.indexOf('\\')!= -1))) {
			isBad = true;
		}else if (grpNumber == 4 // A filename cannot contain any of the following characters:	\ / : * ? " < > |
				&& ((value.indexOf('<')!= -1)
				        || (value.indexOf('>') != -1)
				        || (value.indexOf('\\')!= -1)
				        || (value.indexOf('/')!= -1)
				        || (value.indexOf('*')!= -1)
				        || (value.indexOf(':') != -1)
						|| (value.indexOf('?') != -1)
						|| (value.indexOf('\"') != -1)
						|| (value.indexOf('|') != -1) ) ) {
			isBad = true;
		}
		/* PMF Section Changes -- AD21239 --*/
		else if (grpNumber == 5
				&& ((value.indexOf('<')!= -1)
				        || (value.indexOf('>') != -1)
				        || (value.indexOf('"') != -1)
				        || (value.indexOf('\\')!= -1)
				        || (value.indexOf('%') != -1)
				        || (value.indexOf(';') != -1)
				        || (value.indexOf('(') != -1)
				        || (value.indexOf(')') != -1)
				        || (value.indexOf('&') != -1)
				        || (value.indexOf('+') != -1))) {
					isBad = true;
		}

		return isBad;
	}
	//  Changes for the security scan on 03/05/10 end

	/**  This method converts the special characters to html number coded value
	 * to escape hazardous characters before returning the page to the browser.
	 *
	 */

	 public static String convertSplChar (String value){
		 if (value == null || value.equals("")){
			 return "";
		 }
		 StringBuffer result = new StringBuffer(value.length());

		 for (int i = 0; i < value.length(); i++)
		 {
		      switch (value.charAt(i)) {
		      case '<':
		        result.append("&lt;");
		        break;
		      case '>':
		        result.append("&gt;");
		        break;
		      case '"':
		        result.append("&quot;");
		        break;
		      case '\'':
		        result.append("&#39;");
		        break;
		      case '%':
		        result.append("&#37;");
		        break;
		      case ';':
		        result.append("&#59;");
		        break;
		      case '(':
		        result.append("&#40;");
		        break;
		      case ')':
		        result.append("&#41;");
		        break;
		      case '&':
		        result.append("&amp;");
		        break;
		      case '+':
		        result.append("&#43;");
		        break;
		      case '|':
		        result.append("&#124;");
		        break;
		      default:
		        result.append(value.charAt(i));
		        break;
		      }
		 }
		 return result.toString();
	 }
	/**
	 * The main function where the transaction for updating the Provider
	 * Maintenenance data. It manages the connection and the call to insertProviderMaintenance
	 * as a transaction.
	 * @param providerData
	 * @return boolean
	 */
    static int updateDatabaseWithFile(Provider providerData,String sessionID) 
    		throws SQLException, PMFException
    {
        Connection con = null;
        int seqNo=0;
        AttachDocUploadHelper docUploadHelper =null;
        String dcnVal=null;
        try
        {
        	docUploadHelper = new AttachDocUploadHelper();
            DataSource ds =  getDataSource();
            con = ds.getConnection();
			disableAutoCommit(con);
			// change to display PGI id in the confirmation page
			//int seqNo = getPGISeqNo(con);
			seqNo = getPGISeqNo(con);
			logger.debug("seqno "+seqNo);
            insertProviderMaintenance(con, providerData, seqNo, logger);
            logger.debug("after insert provider maintenance");
            if(providerData.getUploadFileName()!=null && !providerData.getUploadFileName().equals(""))
            {
	            logger.debug("File Upload start.");
	            dcnVal = docUploadHelper.uploadDocuments(providerData, sessionID,con,seqNo, logger);
	            //insertAttachmentsDetails(con, providerData, seqNo ,dcnVal );
            }
            logger.debug("successfully executed insertAreasOfExpertise"); 
			commitTransaction(con);
        }
        catch(PMFException pmfe)
        {
            if (con != null){
            	rollBackTransaction(con);
            }
            logger.error("Error in PMFUtils, transaction rolled back: " + pmfe.getMessage());
            logger.error(stack2string(pmfe));
			throw pmfe;
        }
        catch(Exception sqle)
        {
            //TEW added logic to see of connection is null before rolling back.
            if (con != null){
            	rollBackTransaction(con);
            }
            logger.error("Error in PMFUtils: " + sqle.getMessage());
            logger.error(stack2string(sqle));
			throw new SQLException("Exception in updating PMF Data" + sqle);
        }
        finally
        {        	
                if (con != null){
                	closeConnection(con);
                	con=null;
                }
        }
        return seqNo;
    }
    
    /**
	 * The main function where the transaction for updating the VA/NY Provider
	 * Maintenenance data. It manages the connection and the call to insertPDMForm
	 * as a transaction.
	 * @param providerData
	 * @return boolean
	 */
    static int updateDatabasePDM(NYVAProvMaintFormBean provMaintForm, String sessionID) 
    		throws SQLException, PMFException
    {
        Connection con = null;
        int seqNo=0;
        AttachDocUploadHelper docUploadHelper =null;
        String dcnVal=null;
        List<String> dcnLst = null;
        try
        {
        	
        	docUploadHelper = new AttachDocUploadHelper();
            DataSource ds =  getDataSource();
            con = ds.getConnection();
			disableAutoCommit(con);
			seqNo = getPGISeqNo(con);
			logger.info("seqno "+seqNo);
            if(provMaintForm.getUploadFileListDtls()!=null && provMaintForm.getUploadFileListDtls().size() > 0)
            {
	            logger.debug("File Upload start.");
	            dcnLst = docUploadHelper.uploadDocuments(provMaintForm, sessionID,con,seqNo, logger);
	            updateEmailBodyForAttachments(provMaintForm);
	            //insertAttachmentsDetails(con, providerData, seqNo ,dcnVal );
            }
            insertPDMForm(con, provMaintForm, seqNo);
            logger.debug("after insert PDM form");

			commitTransaction(con);
			logger.debug("After PDM Commit.");
        }
        catch(PMFException pmfe)
        {
            if (con != null){
            	rollBackTransaction(con);
            }
            logger.error("Error in PMFUtils, transaction rolled back: " + pmfe.getMessage());
            logger.error(stack2string(pmfe));
			throw pmfe;
        }
        catch(Exception sqle)
        {
            //TEW added logic to see of connection is null before rolling back.
            if (con != null){
            	rollBackTransaction(con);
            }
            logger.error("Error in PMFUtils Here: " + sqle.getMessage());
            logger.error(stack2string(sqle));
			throw new SQLException("Exception in updating PMF Data" + sqle);
        }
        finally
        {
            if (con != null){
            	closeConnection(con); //why is this commented out of the other?!!!
            	logger.debug("connection closed");
            	con=null;
            }
        }
        return seqNo;
    }
    
    
    private static void insertPDMForm(Connection con, NYVAProvMaintFormBean provMaintForm, int seqNo) 
    		throws SQLException, IOException
    {
    	PreparedStatement ps = null;
    	PreparedStatement pstmt = null;
    	PreparedStatement teststmt = null;
    	ResultSet rset = null;
        String sql = "INSERT INTO PDM_FORM " +
        		"(PGI_ID, CREATE_DATE, BRAND, EFF_DATE, EMAIL_TXT, PROV_LAST_NAME, PROV_FIRST_NAME, PROV_TAX_ID, PROV_NPI, " +
        		"    PRAC_NAME, PRAC_TAX_ID, PRAC_NPI, CONTACT_LAST_NAME, CONTACT_FIRST_NAME, CONTACT_EMAIL, " +
        		"    CONTACT_PHONE) " +
        		"values (?, sysdate, UPPER(?), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try
        {
    		ps = con.prepareStatement(sql);
	        ps.setInt(1, seqNo);
	        ps.setString(2, getSafeString(provMaintForm.getBrand()));
	        ps.setString(3, getSafeString(provMaintForm.getEffDate()));
	  ps.setString(4, getSafeString(provMaintForm.getEmailBody()));
	        ps.setString(5, getSafeString(provMaintForm.getProvLastName()));
	        ps.setString(6, getSafeString(provMaintForm.getProvFirstName()));
	        ps.setString(7, getSafeString(provMaintForm.getProvTaxId()));
	        ps.setString(8, getSafeString(provMaintForm.getProvNPI()));
	        ps.setString(9, getSafeString(provMaintForm.getPracName()));
	        ps.setString(10, getSafeString(provMaintForm.getPracTaxId()));
	        ps.setString(11, getSafeString(provMaintForm.getPracNPI()));
	        ps.setString(12, getSafeString(provMaintForm.getContactLastName()));
	        ps.setString(13, getSafeString(provMaintForm.getContactFirstName()));
	        ps.setString(14, getSafeString(provMaintForm.getContactEmail()));
	        ps.setString(15, getSafeString(provMaintForm.getContactPhone()));
	        ps.executeUpdate();

	        String insertclobsql = new StringBuffer("SELECT EMAIL_TXT FROM PDM_FORM" + 
	        		" WHERE PGI_ID = " + seqNo + " FOR UPDATE").toString();
	
	        pstmt = con.prepareStatement(insertclobsql);
	
	        logger.debug("insertclobsql: " + insertclobsql);
	/*
	        rset = pstmt.executeQuery();
	        java.sql.Clob clob = null;
	        if (rset.next())
	        {
	          // Get CLOB locator
	          clob = rset.getClob("EMAIL_TXT");
	          java.io.Writer wr = ((java.sql.Clob) clob).getCharacterOutputStream();
	          char[] b = provMaintForm.getEmailBody().toCharArray();
	          wr.write(b);
	          wr.flush();
	          // Write data into CLOB
	          String query = "UPDATE PDM_FORM SET EMAIL_TXT = ? WHERE PGI_ID = " + seqNo;
	          logger.debug("email text clob update query = " + query);
	          teststmt = con.prepareStatement(query);
	          teststmt.setClob(1, clob);
	          teststmt.executeUpdate();
	        }
	        */
        }
        catch(SQLException sqle)
        {
          logException ("Error in PMFUtils: Insert into PDM_FORM " + sql, sqle);
		  throw new SQLException(sqle.getMessage());
        }
		finally
		{
			closeStatement(ps);
			closeStatement(teststmt);
			closeResultSet(rset, pstmt);
		}
    }
    
    private static void updateEmailBodyForAttachments(NYVAProvMaintFormBean pdmForm) {
		StringBuffer sbEmailBody = new StringBuffer(pdmForm.getEmailBody());
		sbEmailBody.append("\n\tATTACHMENTS\n");
		int counter = 0;
		Iterator itFiles = pdmForm.getUploadFileListDtls().iterator();
		while (itFiles.hasNext()) {
			UploadDocListBean doc = (UploadDocListBean)itFiles.next();
			counter++;
			sbEmailBody.append("\t\tFile #" + counter + "\n");
			sbEmailBody.append("\t\t\tDCN : " + doc.getDcn() + "\n");
			sbEmailBody.append("\t\t\tFilename : " + doc.getFileName() + "\n");
			sbEmailBody.append("\t\t\tDescription : " + doc.getDocComment() + "\n");
		}
		
		pdmForm.setEmailBody(sbEmailBody.toString());
	}
    
    
    public int updateDatabaseWithFile1(Provider providerData,String sessionID)
    {
        Connection con = null;
        int seqNo=0;
        AttachDocUploadHelper docUploadHelper =null;
        String dcnVal=null;
        try
        {
        	docUploadHelper = new AttachDocUploadHelper();
            DataSource ds =  getDataSource();
            con = ds.getConnection();
			disableAutoCommit(con);
			// change to display PGI id in the confirmation page
			//int seqNo = getPGISeqNo(con);
			seqNo = getPGISeqNo(con);
			logger.debug("seqno "+seqNo);
            insertProviderMaintenance(con, providerData, seqNo, logger);
            if(providerData.getUploadFileName()!=null && !providerData.getUploadFileName().equals(""))
            {
	            dcnVal = docUploadHelper.uploadDocuments(providerData, sessionID,con,seqNo,logger);
	            //insertAttachmentsDetails(con, providerData, seqNo ,dcnVal );
            }
            logger.debug("successfully executed insertAreasOfExpertise"); 
			commitTransaction(con);
        }
        catch(Exception sqle)
        {
            //TEW added logic to see of connection is null before rolling back.
            if (con != null){
		rollBackTransaction(con);
            }
            logger.error("Error in PMFUtils: " + sqle.getMessage());
            logger.error(stack2string(sqle));
        }
        finally
        {
                if (con != null){
			closeConnection(con);
                }
        }
        return seqNo;
    }

	/**
	 * The main function where the transaction for updating the Provider
	 * Maintenance data. The int return value was used to allow for different
	 * Error Codes if required for the inserts.
	 * @param providerData
	 * @return boolean
	 */
    private static void insertProviderMaintenance(Connection con, Provider providerData, int seqNo, Logger logger) throws SQLException
    {
		try
		{
              insertPracticeGeneralInfo(con, providerData, seqNo);
              logger.debug("successfully executed insertPracticeGeneralInfo"); 	
		      insertProviderInfo(con, providerData, seqNo);
		      logger.debug("successfully executed insertProviderInfo"); 
		      insertPracticeInfo(con, providerData, seqNo);
		      logger.debug("successfully executed insertPracticeInfo"); 	
              insertCoveringPhysicians( con, providerData, seqNo);
              logger.debug("successfully executed insertCoveringPhysicians"); 
              insertPatientInfo(con, providerData.getPatientInfo(),providerData.getNonPsychEval(), seqNo );
              logger.debug("successfully executed insertPatientInfo"); 
              insertAreasOfExpertise(con, providerData.getAreasOfExpertise(), seqNo );
              logger.debug("successfully executed insertAreasOfExpertise"); 
              //insertAttachmentsDetails(con, providerData, seqNo );
              //logger.debug("successfully executed insertAreasOfExpertise"); 
		}
		catch (SQLException sqle)
		{
			logger.error("Exception creating Provider Maintenance record" + sqle);
			throw new SQLException("Exception creating Provider Maintenance record" + sqle);
		}
    }
    
    public static void insertAttachmentsDetails(Connection con, Provider providerData, int seqNo, String dcnVal) throws SQLException
    {
    	PreparedStatement ps = null;
    	String[] uploadFileNames =null;
    	String[] uploadFileSizes =null;
    	String[] uploadFileComments =null;
        String sql = "INSERT INTO ATTACHMENTS (PGI_ID, DCN, FILE_NAME, FILE_SIZE, FILE_COMMENT) values (?,?,?,?,?)";
        try{
        	
        	if( providerData.getUploadFileName()!=null)
  		   {
  			uploadFileNames = providerData.getUploadFileName().split("::");
  			uploadFileSizes = providerData.getUploadFileSize().split("::");
  			uploadFileComments = providerData.getUploadedFileComment().split("::");
	   		   for(int fileCount=0;fileCount<uploadFileNames.length;fileCount++)
	   		   {
	   			ps = con.prepareStatement(sql);
		        ps.setInt(1, seqNo);
		        ps.setString(2, dcnVal);
		        ps.setString(3, uploadFileNames[fileCount]);
		        ps.setDouble(4, Double.parseDouble(uploadFileSizes[fileCount]));
		        if(!uploadFileComments[fileCount].equals("NA"))
		        {
		        	ps.setString(5, uploadFileComments[fileCount]);
		        }
		        else
		        {
		        	ps.setString(5, "");
		        }
		        ps.executeUpdate(); 
	   		   }
  		   }
        }catch(SQLException sqle)
        {
        	logException ("Error in PMFUtils: Insert into ADDRESS " + sql, sqle);
  		  throw new SQLException(sqle.getMessage());
        }
    }
    public static void insertAttachmentsDetails(Connection con, String fileName,String fileSize,String fileComments, int seqNo, String dcnVal) throws SQLException
    {
    	PreparedStatement ps = null;
        String sql = "INSERT INTO ATTACHMENTS (PGI_ID, DCN, FILE_NAME, FILE_SIZE, FILE_COMMENT) values (?,?,?,?,?)";
        try{
	   			ps = con.prepareStatement(sql);
		        ps.setInt(1, seqNo);
		        ps.setString(2, dcnVal);
		        ps.setString(3, fileName);
		        ps.setDouble(4, Double.parseDouble(fileSize));
		        if(!fileComments.equals("NA"))
		        {
		        	ps.setString(5, fileComments);
		        }
		        else
		        {
		        	ps.setString(5, "");
		        }
		        ps.executeUpdate(); 
        }catch(SQLException sqle)
        {
        	logException ("Error in PMFUtils: Insert into ADDRESS " + sql, sqle);
  		  throw new SQLException(sqle.getMessage());
        }
        finally
        {
        	closeStatement(ps); // ps=null;
        }
    }
    private static void insertAddressInfo(Connection con, Provider providerData, int seqNo, String addressType) throws SQLException
    {
    	PreparedStatement ps = null;
        String sql = "INSERT INTO ADDRESS_INFO  (PGI_ID, SEQUENCE_NO, ENTITY_NAME, ADDRESS, CITY, STATE, ZIP) values (?,?,?,?,?,?,?)";
        boolean isSectionRequired = false;
        int cntr = 1;
        try
        {
        	if("AdmitPrivilege".equalsIgnoreCase(addressType))
        	{
	        	for(int i = 0; i < providerData.getApHospitalName().length; i++)
	        	{
	        		isSectionRequired = (StringUtils.isNotEmpty(providerData.getApHospitalName()[i]) ||
	        								StringUtils.isNotEmpty(providerData.getApHospitalAddress()[i]) ||
	        								StringUtils.isNotEmpty(providerData.getApHospitalCity()[i]) ||
	        								StringUtils.isNotEmpty(providerData.getApHospitalState()[i]) ||
	        								StringUtils.isNotEmpty(formatZipCode(providerData.getApHospitalZip()[i])));
	        		
		            if(isSectionRequired) 
		            {
		        		ps = con.prepareStatement(sql);
				        ps.setInt(1, seqNo);
				        ps.setString(2, STRING_A + cntr++);
				        ps.setString(3, getSafeString(providerData.getApHospitalName()[i]));
				        ps.setString(4, getSafeString(providerData.getApHospitalAddress()[i]));
				        ps.setString(5, getSafeString(providerData.getApHospitalCity()[i]));
				        ps.setString(6, getSafeString(providerData.getApHospitalState()[i]));
				        ps.setString(7, getSafeString(formatZipCode(providerData.getApHospitalZip()[i])));
				        ps.executeUpdate();
		            }
	        	}
	        	
        	}
        	else if("DeliveryPrivilege".equalsIgnoreCase(addressType)) 
        	{
	        	for(int i = 0; i < providerData.getDpHospitalName().length; i++)
	        	{
	        		isSectionRequired = (StringUtils.isNotEmpty(providerData.getDpHospitalName()[i]) ||
											StringUtils.isNotEmpty(providerData.getDpHospitalAddress()[i]) ||
											StringUtils.isNotEmpty(providerData.getDpHospitalCity()[i]) ||
											StringUtils.isNotEmpty(providerData.getDpHospitalState()[i]) ||
											StringUtils.isNotEmpty(formatZipCode(providerData.getDpHospitalZip()[i])));
	
				    if(isSectionRequired) 
				    {
			            ps = con.prepareStatement(sql);
				        ps.setInt(1, seqNo);
				        ps.setString(2, STRING_D + cntr++);
				        ps.setString(3, getSafeString(providerData.getDpHospitalName()[i]));
				        ps.setString(4, getSafeString(providerData.getDpHospitalAddress()[i]));
				        ps.setString(5, getSafeString(providerData.getDpHospitalCity()[i]));
				        ps.setString(6, getSafeString(providerData.getDpHospitalState()[i]));
				        ps.setString(7, getSafeString(formatZipCode(providerData.getDpHospitalZip()[i])));
				        ps.executeUpdate();
				    }
	        	}
        	}

        }
        catch(SQLException sqle)
        {
          logException ("Error in PMFUtils: Insert into ADDRESS " + sql, sqle);
		  throw new SQLException(sqle.getMessage());
        }
		finally
		{
			closeStatement(ps);
		}
    }
    

	/**
	 * Inserts into the PROVIDER_INFO table
	 * @param con
	 * @param providerData
	 * @param seqNo
	 * @throws SQLException
	 */
    private static void insertProviderInfo(Connection con, Provider providerData, int seqNo) throws SQLException
    {
    	PreparedStatement ps = null;
        String sql ="";
        StringBuffer strBuf = new StringBuffer();
        try
        {
        	/* SSCR9711-SSCR9724 -HIP – PV 19371 Added HIP_MAX_PANEL*/
            // Changes for the security scan considerations on 02/01/10 start
            strBuf.append("INSERT INTO PROVIDER_INFO (PGI_ID, FIRST_NAME, MI, LAST_NAME, TITLE, PRIM_SPC_PHY, ");
            strBuf.append("SPC_CARE_PHY, OTHER, SSN, UPIN_NO, PROF_LIC_NO, CAQH_ID_NO, DOB, GENDER, PROV_NPI_NO, ");
            strBuf.append("TAXONOMY_NO, MEDICAID_GRP_NO, BOARD_CERT, CERT_EXAM_DT, CAQH_EXPLAIN, ACCEPT_NEW_PATIENT, ");
            strBuf.append("AGE_LIMIT_MIN, AGE_LIMIT_MAX, MEDICAID_INDICATOR, MEDICAID_PCP, MEDICAID_MAX_PANEL, ");
            strBuf.append("MEDICAID_SPECIALIST, HIP_INDICATOR, HIP_PCP, HIP_MAX_PANEL, HIP_SPECIALIST, SS_MEDICAL, SS_DENTAL, ");
            strBuf.append("SS_VISION, SS_OTHER_SERV_TYPE, INDIV_PRACTICE, GROUP_PRACTICE, SCHOOL_BASED_CLINIC, ");
            strBuf.append("TRIBAL_HEALTH_CTR, RURAL_HEALTH_CLINIC, FED_QUAL_HEALTH_CLINIC, COMMUNITY_HEALTH_CTR, ");
            strBuf.append("DEPT_OF_HEALTH, OTHER_PRACTICE, RAD_HOSP_BASED, RAD_FREE_STANDING_CENTER, MGD_CARE_DISENROLL, IHCP_PROV_NO, ");
            strBuf.append("PMP, PMP_SPECIALTY, HOSP_ADMIT_PRIV, RELATIONSHIP_PRIV, DELIVERY_PRIV, AGE_RESTRICTION, PMP_SCOPE_OB,PMP_SCOPE_ALL, ");
            strBuf.append("GENDER_SCOPE, MED_PANEL_STATUS, MED_NBR_LOCATIONS, MED_PLD_PANEL_DECREASE, MED_PLD_PLACE_PANEL_AT, ");
            strBuf.append("MED_PLD_GRP_MEDICAID_NO, HIP_PANEL_STATUS, HIP_NBR_LOCATIONS, HIP_PLD_PANEL_DECREASE, HIP_PLD_PLACE_PANEL_AT, ");
            strBuf.append("HIP_PLD_GRP_MEDICAID_NO, MED_PLI_PANEL_INCREASE, MED_PLI_PLACE_PANEL_AT, ");
            strBuf.append("MED_PLI_GRP_MEDICAID_NBR, HIP_PLI_PANEL_INCREASE, HIP_PLI_PLACE_PANEL_AT, HIP_PLI_GRP_MEDICAID_NBR, ");
            strBuf.append("MED_PANEL_HOLD, HIP_PANEL_HOLD, MED_PANEL_GRP_HOLD_NBR, HIP_PANEL_GRP_HOLD_NBR, MED_PLR_PANEL_HOLD_REMOVE, ");
            strBuf.append("HIP_PLR_PANEL_HOLD_REMOVE, MED_PLR_GRP_MEDICAID_NO, HIP_PLR_GRP_MEDICAID_NO, ");
            strBuf.append("DEA_NO, CSR_NO, ENROLL_AS, ENROLL_CLINIC_TYPE, LOCATION_TYPE, UTILIZE_NP, UTILIZE_PA, ");
            strBuf.append("STATE_LIC_ISSUE_DT, STATE_LIC_EXP_DT, PROF_LIAB_CARRIER, PROF_LIAB_COVG_LIMIT, ");
            strBuf.append("PROF_LIAB_POLICY, PROF_LIAB_EXP_DT, MALPRACTICE_INS_REVOKED, UNDER_GOV_INVESTIGATION, EXPELLED_FROM_MED_PMT, UTILIZE_NA, ");
            strBuf.append("NP_SUP_SPEC, NP_SUP_PMP, RETAIL_HEALTH_CLINIC , WALK_IN_DR_OFFICE, CLINIC , URGENT_CARE, ");
            strBuf.append("COVERING_PMP, CERT_MIDWIFE , PRENATAL_CARE_COORD,MEDICARE_PART_TRAD,MEDICARE_APPL_DT, ");
            strBuf.append("MEDICARE_OPT_OUT, MEDICARE_OPT_OUT_DT, SPECIALTY,FWDHEALTH_CERT_NPI,SPECIALTY_TYPE,PA_SUP_SPEC,PA_SUP_PMP, ");
            strBuf.append("MAT, RES_TREAT_CTR, SUD_PROV_ADULTS, SUD_PROV_CHILD, TELEHEALTH_PROV ) ");
            strBuf.append("values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,");
            strBuf.append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,");
            strBuf.append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            sql = strBuf.toString();
            ps = con.prepareStatement(sql);
	        ps.setInt(1,seqNo);
	        ps.setString(2,getSafeString(providerData.getProvFnm()));
	        ps.setString(3,getSafeString(providerData.getProvMI()));
	        ps.setString(4,getSafeString(providerData.getProvLnm()));
	        ps.setString(5,getSafeString(providerData.getTitle()));
	        ps.setString(6,getSafeString(providerData.getPrimSpecialtyPhy()));
	        ps.setString(7,getSafeString(providerData.getSpecialtyCarePhy()));
	        ps.setString(8,getSafeString(providerData.getOther()));
	        ps.setString(9,getSafeString(providerData.getProvSSN()));
	        ps.setString(10,getSafeString(providerData.getUpinNumber()));
	        ps.setString(11,getSafeString(providerData.getProfLicenseNumber()));
	        ps.setString(12,getSafeString(providerData.getCaqhIDNumber()));
	        ps.setString(13,getSafeString(providerData.getProvDOB()));
	        ps.setString(14,getSafeString(providerData.getProvGender()));
	        ps.setString(15,getSafeString(providerData.getProvNPINumber()));
	        ps.setString(16,getSafeString(providerData.getTaxonomyNumber()));
	        ps.setString(17,getSafeString(providerData.getGrpMedicaidId()));
	        ps.setString(18,getSafeString(providerData.getBoardCertified()));
	        ps.setString(19,getSafeString(providerData.getCertExamDT()));
	        ps.setString(20,getSafeString(providerData.getCaqhExplanation()));
	        ps.setString(21,getSafeString(providerData.getNewPatients()));
	        ps.setString(22,getSafeString(providerData.getAgeLimitMin()));
	        ps.setString(23,getSafeString(providerData.getAgeLimitMax()));
	        ps.setString(24,getSafeString(providerData.getMedicaidIndicator()));
	        ps.setString(25,getSafeString(providerData.getMedicaidPCP()));
	        ps.setString(26,getSafeString(providerData.getMedicaidMaxPanel()));
	        ps.setString(27,getSafeString(providerData.getMedicaidSpecialist()));
	        ps.setString(28,getSafeString(providerData.getHipIndicator()));
	        ps.setString(29,getSafeString(providerData.getHipPCP()));
	        ps.setString(30,getSafeString(providerData.getHipMaxPanel()));
	        ps.setString(31,getSafeString(providerData.getHipSpecialist()));
	        ps.setString(32,getSafeString(providerData.getSsMedical()));
	        ps.setString(33,getSafeString(providerData.getSsDental()));
	        ps.setString(34,getSafeString(providerData.getSsVision()));
	        ps.setString(35,getSafeString(providerData.getSsOtherServType()));
	        ps.setString(36,getSafeString(providerData.getIndivPractice()));
	        ps.setString(37,getSafeString(providerData.getGroupPractice()));
	        ps.setString(38,getSafeString(providerData.getSchoolBasedClinic()));
	        ps.setString(39,getSafeString(providerData.getTribalHealthCenter()));
	        ps.setString(40,getSafeString(providerData.getRuralHealthClinic()));
	        ps.setString(41,getSafeString(providerData.getFedQualHealthClinic()));
	        ps.setString(42,getSafeString(providerData.getCommunityHealthCenter()));
	        ps.setString(43,getSafeString(providerData.getDeptOfHealth()));
	        ps.setString(44,getSafeString(providerData.getOtherPractice()));
	        ps.setString(45,getSafeString(providerData.getRadHospBased()));
	        ps.setString(46,getSafeString(providerData.getRadFreeStandingCenter()));
	        ps.setString(47,getSafeString(providerData.getMgdCareDisenroll()));
	        ps.setString(48,getSafeString(providerData.getIhcpProvNo()));
	        ps.setString(49,getSafeString(providerData.getPmp()));
	        ps.setString(50,getSafeString(providerData.getPmpSpecialty()));
	        ps.setString(51,getSafeString(providerData.getHospAdmitPriv()));
	        ps.setString(52,getSafeString(providerData.getRelationshipPriv()));
	        ps.setString(53,getSafeString(providerData.getDeliveryPriv()));
	        ps.setString(54,getSafeString(providerData.getAgeRestriction()));
	        ps.setString(55,getSafeString(providerData.getPmpScopeOb()));
	        ps.setString(56,getSafeString(providerData.getPmpScopeAll()));
	        ps.setString(57,getSafeString(providerData.getGenderScope()));
	        ps.setString(58,getSafeString(providerData.getMedPanelStatus()));
	        ps.setString(59,getSafeString(providerData.getMedNbrLocations()));
	        ps.setString(60,getSafeString(providerData.getMedPldPanelDecrease()));
	        ps.setString(61,getSafeString(providerData.getMedPldPlacePanelAt()));
	        ps.setString(62,getSafeString(providerData.getMedPldGrpMedicaidNo()));
	        ps.setString(63,getSafeString(providerData.getHipPanelStatus()));
	        ps.setString(64,getSafeString(providerData.getHipNbrLocations()));
	        ps.setString(65,getSafeString(providerData.getHipPldPanelDecrease()));
	        ps.setString(66,getSafeString(providerData.getHipPldPlacePanelAt()));
	        ps.setString(67,getSafeString(providerData.getHipPldGrpMedicaidNo()));
	        ps.setString(68,getSafeString(providerData.getMedPliPanelIncrease()));
	        ps.setString(69,getSafeString(providerData.getMedPliPlacePanelAt()));
	        ps.setString(70,getSafeString(providerData.getMedPliGrpMedicaidNbr()));
	        ps.setString(71,getSafeString(providerData.getHipPliPanelIncrease()));
	        ps.setString(72,getSafeString(providerData.getHipPliPlacePanelAt()));
	        ps.setString(73,getSafeString(providerData.getHipPliGrpMedicaidNbr()));
	        ps.setString(74,getSafeString(providerData.getMedPanelHold()));
	        ps.setString(75,getSafeString(providerData.getHipPanelHold()));
	        ps.setString(76,getSafeString(providerData.getMedPlrPanelHold()));
	        ps.setString(77,getSafeString(providerData.getHipPlrPanelHold()));
	        ps.setString(78,getSafeString(providerData.getMedPanelHoldRemove()));
	        ps.setString(79,getSafeString(providerData.getHipPanelHoldRemove()));
	        ps.setString(80,getSafeString(providerData.getMedPlrPanelHoldRemove()));
	        ps.setString(81,getSafeString(providerData.getHipPlrPanelHoldRemove()));
	        ps.setString(82,getSafeString(providerData.getDeaNo()));
	        ps.setString(83,getSafeString(providerData.getCsrNo()));
	        ps.setString(84,getSafeString(providerData.getEnrollAs()));
	        ps.setString(85,getSafeString(providerData.getEnrollClinicType()));
	        ps.setString(86,getSafeString(providerData.getLocationType()));
	        ps.setString(87,getSafeString(providerData.getNpPractice()));
	        ps.setString(88,getSafeString(providerData.getPaPractice()));
	        ps.setString(89,getSafeString(providerData.getStateLicIssueDt()));
	        ps.setString(90,getSafeString(providerData.getStateLicExpDt()));
	        ps.setString(91,getSafeString(providerData.getProfLiabilityCarrierName()));
	        ps.setString(92,getSafeString(providerData.getProfLiabilityCarrierLimit()));
	        ps.setString(93,getSafeString(providerData.getProfLiabilityPolicyNo()));
	        ps.setString(94,getSafeString(providerData.getProfLiabilityExpDate()));
	        ps.setString(95,getSafeString(providerData.getMalPracInsRevoke()));
	        ps.setString(96,getSafeString(providerData.getUnderGovInvestigation()));
	        ps.setString(97,getSafeString(providerData.getExpellMedPay()));
	        ps.setString(98,getSafeString(providerData.getNaPractice()));
	        ps.setString(99,getSafeString(providerData.getNpSupSpec()));
	        ps.setString(100,getSafeString(providerData.getNpSupPMP()));
	        ps.setString(101,getSafeString(providerData.getRetailHealthClinic()));
	        ps.setString(102,getSafeString(providerData.getWalkInDrOffice()));
	        ps.setString(103,getSafeString(providerData.getClinic()));
	        ps.setString(104,getSafeString(providerData.getUrgentCare()));
	        ps.setString(105,getSafeString(providerData.getCoveringPMP()));
	        ps.setString(106,getSafeString(providerData.getCertMidwife()));
	        ps.setString(107,getSafeString(providerData.getPrenatelCareCoord()));
	        ps.setString(108,getSafeString(providerData.getMedicarePartTrad()));
	        ps.setString(109,getSafeString(providerData.getMedicareApplDt()));
	        ps.setString(110,getSafeString(providerData.getMedicareOptOut()));
	        ps.setString(111,getSafeString(providerData.getMedicareOptOutDt()));
	        ps.setString(112,getSafeString(providerData.getSpecialty()));
	        ps.setString(113,getSafeString(providerData.getFwdHealthCertNPI1()));
	        ps.setString(114,getSafeString(providerData.getSpecialityInfo()));
	        ps.setString(115,getSafeString(providerData.getPaSupSpec()));
	        ps.setString(116,getSafeString(providerData.getPaSupPMP()));
	        ps.setString(117,getSafeString(providerData.getMedicationAssistedTreatment()));
	        ps.setString(118,getSafeString(providerData.getResidentialTreatmentCenter()));
	        ps.setString(119,getSafeString(providerData.getSubstanceUseDisorderAdults()));
	        ps.setString(120,getSafeString(providerData.getSubstanceUseDisorderChild()));
	        ps.setString(121,getSafeString(providerData.getTelehealthProv()));
	        ps.executeUpdate();
	        closeStatement(ps);
            // Changes for the security scan considerations on 02/01/10 end
	        insertFwdHealthInfo(con, providerData, seqNo);
	        //HIP fields
	        insertAddressInfo(con, providerData, seqNo, "AdmitPrivilege");
			insertAddressInfo(con, providerData, seqNo, "DeliveryPrivilege");
        }
        catch(SQLException sqle)
        {
          logException ("Error in PMFUtils: Insert into Provider Info " + sql, sqle);
		  throw new SQLException(sqle.getMessage());
        }
		finally
		{
			closeStatement(ps);
		}
    }
    private static void insertPracticeInfo(Connection con, final PracticeInfo practiceInfo, int seqNo, String pracSeqNo) throws SQLException
    {
    	PreparedStatement ps = null;
        try
        {
	        String sql = getPracticeInfoSQL();
	        ps = con.prepareStatement(sql);
	        ps.setInt(1, seqNo);
	        ps.setString(2, pracSeqNo);
            ps.setString(3, getSafeString(practiceInfo.getPracName()));
            ps.setString(4, getSafeString(practiceInfo.getPracOfficeAddress()));
            ps.setString(5, getSafeString(practiceInfo.getPracOfficeCity()));
            ps.setString(6, getSafeString(practiceInfo.getPracOfficeState()));
            ps.setString(7, getSafeString(formatZipCode(practiceInfo.getPracOfficeZip())));
            ps.setString(8, getSafeString(practiceInfo.getPracOfficeCounty()));
            ps.setString(9, getSafeString(practiceInfo.getPracOfficePhone()));
            ps.setString(10, getSafeString(practiceInfo.getPracOfficeFax()));
            ps.setString(11, getSafeString(practiceInfo.getPracOfficeEmail()));
            ps.setString(12, getSafeString(practiceInfo.getPracBillAddress()));
            ps.setString(13, getSafeString(practiceInfo.getPracBillCity()));
            ps.setString(14, getSafeString(practiceInfo.getPracBillState()));
            ps.setString(15, getSafeString(formatZipCode(practiceInfo.getPracBillZip())));
            ps.setString(16, getSafeString(practiceInfo.getPracBillCounty()));
            ps.setString(17, getSafeString(practiceInfo.getPracBillPhone()));
            ps.setString(18, getSafeString(practiceInfo.getPracBillFax()));
            ps.setString(19, getSafeString(practiceInfo.getBillMedicareGroup()));
            ps.setString(20, getSafeString(practiceInfo.getBillMedicareIndividual()));
            ps.setString(21, getSafeString(practiceInfo.getMedicaidGroup()));
            ps.setString(22, getSafeString(practiceInfo.getMedicaidIndividual()));
            ps.setString(23, getSafeString(practiceInfo.getPracNPINo()));
            ps.setString(24, getSafeString(practiceInfo.getPubicTrans()));
            ps.setString(25, getSafeString(practiceInfo.getHandicapAccess()));
            ps.setString(26, getSafeString(practiceInfo.getEveningHrs()));
            ps.setString(27, getSafeString(practiceInfo.getWeekendHrs()));
            ps.setString(28, getSafeString(practiceInfo.getLanguagesSpoken()));
            ps.setString(29, getSafeString(practiceInfo.getOfferECI()));
            ps.setString(30, getSafeString(practiceInfo.getOfferEPSDT()));
            ps.setString(31, getSafeString(practiceInfo.getProvideADB()));
            ps.setString(32, getSafeString(practiceInfo.getProvideCSHCN()));
            ps.setString(33, getSafeString(practiceInfo.getPracBillContactName()));
            ps.setString(34, getSafeString(practiceInfo.getPracBillContactEmail()));
            ps.setString(35, getSafeString(practiceInfo.getDaysOpenTimeMon()));
            ps.setString(36, getSafeString(practiceInfo.getDaysCloseTimeMon()));
            ps.setString(37, getSafeString(practiceInfo.getDaysOpenTimeTue()));
            ps.setString(38, getSafeString(practiceInfo.getDaysCloseTimeTue()));
            ps.setString(39, getSafeString(practiceInfo.getDaysOpenTimeWed()));
            ps.setString(40, getSafeString(practiceInfo.getDaysCloseTimeWed()));
            ps.setString(41, getSafeString(practiceInfo.getDaysOpenTimeThu()));
            ps.setString(42, getSafeString(practiceInfo.getDaysCloseTimeThu()));
            ps.setString(43, getSafeString(practiceInfo.getDaysOpenTimeFri()));
            ps.setString(44, getSafeString(practiceInfo.getDaysCloseTimeFri()));
            ps.setString(45, getSafeString(practiceInfo.getDaysOpenTimeSat()));
            ps.setString(46, getSafeString(practiceInfo.getDaysCloseTimeSat()));
            ps.setString(47, getSafeString(practiceInfo.getDaysOpenTimeSun()));
            ps.setString(48, getSafeString(practiceInfo.getDaysCloseTimeSun()));
            ps.setString(49, getSafeString(practiceInfo.getDaysOpenMon()));
            ps.setString(50, getSafeString(practiceInfo.getDaysOpenTue()));
            ps.setString(51, getSafeString(practiceInfo.getDaysOpenWed()));
            ps.setString(52, getSafeString(practiceInfo.getDaysOpenThu()));
            ps.setString(53, getSafeString(practiceInfo.getDaysOpenFri()));
            ps.setString(54, getSafeString(practiceInfo.getDaysOpenSat()));
            ps.setString(55, getSafeString(practiceInfo.getDaysOpenSun()));
            ps.setString(56, getSafeString(practiceInfo.getTimeZone()));
            //2013 SSCR 13503 change
            ps.setString(57, getSafeString(practiceInfo.getProvDir()));
            //System.out.println("getProvdir  "+getSafeString(practiceInfo.getProvDir()));
            /* PMF Section Changes -- AD21239 --*/
            ps.setString(58, getSafeString(practiceInfo.getKyMedicaidPart()));
            ps.setString(59, getSafeString(practiceInfo.getKyMedicaidId()));
            ps.setString(60, getSafeString(practiceInfo.getCertOpioidTreat()));
            ps.setString(61, getSafeString(practiceInfo.getMatOpioid()));
            ps.setString(62, getSafeString(practiceInfo.getCounselOpioid()));
            ps.setString(63, getSafeString(practiceInfo.getSudProv()));
            ps.setString(64, getSafeString(practiceInfo.getResTreatCtr()));
            ps.setString(65, getSafeString(practiceInfo.getMatWaiveredPrescriber()));
            ps.setString(66, getSafeString(practiceInfo.getProvideTelehealth()));
            ps.executeUpdate();
        }
        catch(SQLException sqle)
        {
			logger.info("Error in PMFUtils: Insert into Practice Info " + sqle);
            logException ("Error in PMFUtils: Insert into Practice Info " , sqle);
			throw new SQLException(sqle.getMessage());
        }
		finally
		{
			closeStatement(ps);
		}
    }

	private static String getPracticeInfoSQL()
	{
        StringBuffer sql = new StringBuffer();
        sql.append("INSERT INTO PRACTICE_INFO(PGI_ID, SEQUENCE_NO, PRAC_NAME, PRAC_ADDR, PRAC_CITY, ");
        sql.append("PRAC_STATE, PRAC_ZIP, PRAC_COUNTY, PRAC_PHONE, PRAC_FAX, PRAC_EMAIL, BILL_ADDR, ");
        sql.append("BILL_CITY, BILL_STATE, BILL_ZIP, BILL_COUNTY, BILL_PHONE, BILL_FAX, MED_GROUP_NO, ");
        sql.append("MED_INDV_NO, MEDICAID_GRP_NO, MEDICAID_INDV_NO, PRAC_NPI_NO, PUBLIC_TRANS, ");
        sql.append("HANDICAP_ACCESS, EVENING_HRS, WEEKEND_HRS, LANGUAGES_SPOKEN, OFFER_ECI, ");
        sql.append("OFFER_EPSDT, PROVIDE_ABD, PROVIDE_CSHCN, BILL_CONTACT_NAME,BILL_EMAIL,");
        sql.append("OFFICE_OPEN_HR_MON, OFFICE_CLOS_HR_MON, OFFICE_OPEN_HR_TUE, OFFICE_CLOS_HR_TUE, OFFICE_OPEN_HR_WED,");
        sql.append("OFFICE_CLOS_HR_WED, OFFICE_OPEN_HR_THU, OFFICE_CLOS_HR_THU, OFFICE_OPEN_HR_FRI, OFFICE_CLOS_HR_FRI,");
        sql.append("OFFICE_OPEN_HR_SAT, OFFICE_CLOS_HR_SAT, OFFICE_OPEN_HR_SUN, OFFICE_CLOS_HR_SUN, OFFICE_OPEN_MON,");
        sql.append("OFFICE_OPEN_TUE, OFFICE_OPEN_WED, OFFICE_OPEN_THU, OFFICE_OPEN_FRI, OFFICE_OPEN_SAT,");
        sql.append("OFFICE_OPEN_SUN, OFFICE_TIMEZONE, PROV_DIR, KY_MEDICAID_PART, KY_MEDICAID_ID,");
        sql.append("CERT_OPIOID_TREAT, MAT_OPIOID, COUNSEL_OPIOID, SUD_PROV, RES_TREAT_CTR, MAT_WAIVERED_RX, PROVIDE_TELEHEALTH) ");
        sql.append("values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,");
        sql.append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        return sql.toString();

	}

    private static void insertCoveringPhysicians(Connection con, CoveringPhysicians coveringPhysicians, int seqNo, String covPhySeqNo) throws SQLException
    {
        PreparedStatement ps = null;
        try
        {
            String sql =  getCoveringPhysiciansSQL();
            ps = con.prepareStatement(sql);
            ps.setInt(1,seqNo);
            ps.setString(2, covPhySeqNo);
            ps.setString(3, getSafeString(coveringPhysicians.getGrpEntityName()));
            ps.setString(4, getSafeString(coveringPhysicians.getSpecialty()));
            ps.setString(5, getSafeString(coveringPhysicians.getTaxID()));
            ps.setString(6, getSafeString(coveringPhysicians.getEffectiveDate()));
            ps.setString(7, getSafeString(coveringPhysicians.getAddress().getAddress1()));
            ps.setString(8, getSafeString(coveringPhysicians.getAddress().getCity()));
            ps.setString(9, getSafeString(coveringPhysicians.getAddress().getState()));
            ps.setString(10, getSafeString(formatZipCode(coveringPhysicians.getAddress().getZipCode())));
            ps.executeUpdate();
        }
        catch(SQLException sqle)
        {
            logException ("Error in PMFUtils: Insert into Covering Physicians " , sqle);
			throw new SQLException(sqle.getMessage());
        }
        finally
        {
            closeStatement(ps);
        }
    }

   private static String getCoveringPhysiciansSQL()
   {
        StringBuffer sql = new StringBuffer(512);
        sql.append("INSERT INTO COVERING_PHYSICIANS(PGI_ID,SEQUENCE_NO, ENTITY_NAME, SPECIALTY, TAX_ID, EFFECTIVE_DATE, ADDRESS, CITY, STATE, ZIP)");
        sql.append(" values (?,?,?,?,?,?,?,?,?,?)" );
        return sql.toString();
   }

    private static String getInsertPracticeGeneralSQL()
    {
       StringBuffer  sql = new StringBuffer(512);

       sql.append( "INSERT INTO PRACTICE_GENERAL_INFO (" );
       sql.append( "PGI_ID,PRAC_TAX_ID_NO, ANTHEM_PIN,  SOLO_GROUP, NUMBER_IN_GROUP,");
       sql.append( "EFF_DATE,RES_ADD_PROV, RES_DEL_PROV, RES_ADD_LOC, RES_ADD_PROV_TO_LOC, RES_SPECIALTY_CHG, ");
       sql.append( "RES_PROV_NAME_CHG, RES_DEL_LOC, RES_DEL_PROV_FROM_LOC, RES_PRAC_NAME_CHG, RES_PRAC_ADDRESS_CHG, ");
       sql.append( "RES_PRAC_PHONE_CHG, RES_TAX_ID_CHG, RES_BILL_NAME_CHG, RES_BILL_ADDR_CHG, RES_BILL_PHONE_CHG, ");
       sql.append( "RES_DESC, OLD_TAX_ID, COMMENTS, EXTRACT_FLAG, RAPID_UPDATE, EXTRACT_DATE, CREATE_DATE, ");
       sql.append( "GRP_NPI_NO, RES_ADD_NPI, RES_NPI_CHG, RES_OFFICE_HRS_CHG, "  );
       sql.append( "res_add_patient_info, res_add_areas_of_expertise, ethnic_origin, CONFIRMATION, ");
       sql.append( "CONF_PROV_AGREEMENT,CONF_W2, W2_COMMENTS )");
       sql.append( " VALUES (");
       sql.append( "?,?,?,?,?,?,?,?,?,");
       sql.append( "?,?,?,?,?,?,?,?,?,");
       sql.append( "?,?,?,?,?,?,?,?,");
       sql.append( "SysDate,SysDate,");
       sql.append( "?,?,?,?,");
       sql.append( "?,?,?,?,?,?,? )");

       return sql.toString();
    }

    private static String formatZipCode(String s)
    {
        return StringUtils.formatZipCode(s);
    }

    private static String stack2string(Exception e)
    {
        return StringUtils.stack2string(e);
    }

    static int getPGISeqNo(Connection con)
    {
        ResultSet rs = null;
        Statement statement = null;
        int seqNo = -1;

        try
        {
            statement = con.createStatement();
			String sql = "SELECT PGI_SEQ.NEXTVAL as SEQ_NO from DUAL";
            rs = statement.executeQuery(sql);
            rs.next();
            seqNo = Integer.parseInt(rs.getString("SEQ_NO"));
        }
        catch(SQLException sqle)
        {
            logger.error("Error in PMFUtils: " + sqle.getMessage());
            logger.error(stack2string(sqle));
        }
        finally
        {
        	 closeResultSet( rs, statement);
        }
        return seqNo;
    }

    private  static DataSource getDataSource()
    {
        DataSource ds = null;
        try
        {
      Context ctx = getInitialContext();
      logger.debug("Before Creating the datasource");  //Remove this line
      logger.info("Before Creating the datasource"); 
        ds  = (DataSource) ctx.lookup ("ProvMaintFormPool");
      logger.debug("Successfully created the datasource");  //Remove this line
      logger.info("Successfully created the datasource");  //Remove this line
       }
        catch(javax.naming.NamingException ne)
        {
            logException ( "Error locating DataSource", ne);
        }
        catch (Exception excp)
        {
            logException ( "Unhandled error getting DataSource", excp);
        }
        return ds;
    }
  
	private static Context getInitialContext() throws NamingException
	{
		/*
	        Hashtable ht = new Hashtable();
            Properties connProperties = ConfigManager.getInstance().getPMFConnectionProperties();
            String url = connProperties.getProperty("PROVIDER_URL") ;
            String dataSourceName = connProperties.getProperty("PROVIDER_MAINTENANCE_DATASOURCE");

            String contextFactory = connProperties.getProperty("CONTEXT_FACTORY"); ;
            ht.put(Context.INITIAL_CONTEXT_FACTORY, contextFactory);
            ht.put(Context.PROVIDER_URL, url);                      // "t3://localhost:7001"
            ctx = new InitialContext(ht); 
			*/
		    Context ctx = null;
			logger.debug("Initialising context");
			ctx =  new InitialContext();
			return ctx;
	}

    private static void logException(String message, Exception excp)
    {
        LogUtils.logException(logger, message, excp);
    }

    private static String getSafeString(String str)
    {
       return StringUtils.getSafeString(str);
    }

    private static void closeConnection(Connection con)
    {
        try
        {
            if (con != null)
			{
				 con.close();
			}
        }
         catch(SQLException excp)
        {
             logger.error("Error closing connection" + excp);
        }
    }

    private static void closeResultSet(ResultSet rs, Statement statement)
    {
        try
        {
            if (statement!=null)
			{
				statement.close();
			}
            if (rs != null)
			{
				rs.close();
			}
        }
         catch(SQLException excp)
        {
            logger.error("Error closing result set" + excp);
        }
    }

	private static void setPracticeAddress1(Provider providerData, PracticeInfo practiceInfo)
	{
		String pracName = providerData.getPracName1();
		practiceInfo.setPracName(pracName);
		practiceInfo.setPracOfficeAddress(providerData.getPracOfficeAddress1());
		practiceInfo.setPracOfficeCity(providerData.getPracOfficeCity1());
		practiceInfo.setPracOfficeState(providerData.getPracOfficeState1());
		practiceInfo.setPracOfficeZip(formatZipCode(providerData.getPracOfficeZip1()));
		practiceInfo.setPracOfficeCounty(providerData.getPracOfficeCounty1());
		practiceInfo.setPracOfficePhone(providerData.getPracOfficePhone1());
		practiceInfo.setPracOfficeFax(providerData.getPracOfficeFax1());
		practiceInfo.setPracOfficeEmail(providerData.getPracOfficeEmail1());
		practiceInfo.setPracBillContactName(providerData.getPracBillContactName1());
		practiceInfo.setPracBillAddress(providerData.getPracBillAddress1());
		practiceInfo.setPracBillCity(providerData.getPracBillCity1());
		practiceInfo.setPracBillState(providerData.getPracBillState1());
		practiceInfo.setPracBillZip(formatZipCode(providerData.getPracBillZip1()));
		practiceInfo.setPracBillCounty(providerData.getPracBillCounty1());
		practiceInfo.setPracBillPhone(providerData.getPracBillPhone1());
		practiceInfo.setPracBillFax(providerData.getPracBillFax1());
		practiceInfo.setPracBillContactEmail(providerData.getPracBillContactEmail1());
		practiceInfo.setPracNPINo(providerData.getPracNPINo1());
		practiceInfo.setBillMedicareGroup(providerData.getBillMedicareGroup1());
		practiceInfo.setBillMedicareIndividual(providerData.getBillMedicareIndividual1());
        practiceInfo.setMedicaidGroup(providerData.getMedicaidGroup1());
		practiceInfo.setMedicaidIndividual(providerData.getMedicaidIndividual1());

		practiceInfo.setPubicTrans(providerData.getPubicTrans1());
		practiceInfo.setHandicapAccess(providerData.getHandicapAccess1());
		practiceInfo.setEveningHrs(providerData.getEveningHrs1());
		practiceInfo.setProvDir(providerData.getProvDir1());
		
		practiceInfo.setDaysOpenMon(providerData.getDaysOpenMon0());
		practiceInfo.setDaysOpenTue(providerData.getDaysOpenTue0());
		practiceInfo.setDaysOpenWed(providerData.getDaysOpenWed0());
		practiceInfo.setDaysOpenThu(providerData.getDaysOpenThu0());
		practiceInfo.setDaysOpenFri(providerData.getDaysOpenFri0());
		practiceInfo.setDaysOpenSat(providerData.getDaysOpenSat0());
		practiceInfo.setDaysOpenSun(providerData.getDaysOpenSun0());
		
		practiceInfo.setTimeZone(providerData.getTimeZoneOfficeHrs()[0]);
		
		practiceInfo.setDaysOpenTimeMon(providerData.getDaysOpenTimeMon()[0]);
		practiceInfo.setDaysOpenTimeTue(providerData.getDaysOpenTimeTue()[0]);
		practiceInfo.setDaysOpenTimeWed(providerData.getDaysOpenTimeWed()[0]);
		practiceInfo.setDaysOpenTimeThu(providerData.getDaysOpenTimeThu()[0]);
		practiceInfo.setDaysOpenTimeFri(providerData.getDaysOpenTimeFri()[0]);
		practiceInfo.setDaysOpenTimeSat(providerData.getDaysOpenTimeSat()[0]);
		practiceInfo.setDaysOpenTimeSun(providerData.getDaysOpenTimeSun()[0]);
		
		practiceInfo.setDaysCloseTimeMon(providerData.getDaysCloseTimeMon()[0]);
		practiceInfo.setDaysCloseTimeTue(providerData.getDaysCloseTimeTue()[0]);
		practiceInfo.setDaysCloseTimeWed(providerData.getDaysCloseTimeWed()[0]);
		practiceInfo.setDaysCloseTimeThu(providerData.getDaysCloseTimeThu()[0]);
		practiceInfo.setDaysCloseTimeFri(providerData.getDaysCloseTimeFri()[0]);
		practiceInfo.setDaysCloseTimeSat(providerData.getDaysCloseTimeSat()[0]);
		practiceInfo.setDaysCloseTimeSun(providerData.getDaysCloseTimeSun()[0]);

		//Changes for the state mandate on 02/10/10 start
		practiceInfo.setLanguagesSpoken(providerData.getLanguagesSpoken1());
		practiceInfo.setOfferECI(providerData.getOfferECI1());
		practiceInfo.setOfferEPSDT(providerData.getOfferEPSDT1());
		practiceInfo.setProvideADB(providerData.getProvideADB1());
		practiceInfo.setProvideCSHCN(providerData.getProvideCSHCN1());
       //Changes for the state mandate on 02/10/10 end
		
		practiceInfo.setMatWaiveredPrescriber(providerData.getMatWaiveredPrescriber1());
		practiceInfo.setCertOpioidTreat(providerData.getCertOpioidTreat1());
		practiceInfo.setMatOpioid(providerData.getMatOpioid1());
		practiceInfo.setCounselOpioid(providerData.getCounselOpioid1());
		practiceInfo.setSudProv(providerData.getSudProv1());
		practiceInfo.setResTreatCtr(providerData.getResTreatCtr1());
		
		/* PMF Section Changes -- AD21239 --*/
		practiceInfo.setKyMedicaidId(providerData.getKyMedicaidId1());
		practiceInfo.setKyMedicaidPart(providerData.getKyMedicaidPart1());
		
		practiceInfo.setProvideTelehealth(providerData.getProvideTelehealth1());
	}

	private static void setPracticeAddress2(Provider providerData, PracticeInfo practiceInfo)
	{
	    String pracName = providerData.getPracName2();
 		practiceInfo.setPracName(pracName);
		practiceInfo.setPracOfficeAddress(providerData.getPracOfficeAddress2());
		practiceInfo.setPracOfficeCity(providerData.getPracOfficeCity2());
		practiceInfo.setPracOfficeState(providerData.getPracOfficeState2());
		practiceInfo.setPracOfficeZip(formatZipCode(providerData.getPracOfficeZip2()));
		practiceInfo.setPracOfficeCounty(providerData.getPracOfficeCounty2());
		practiceInfo.setPracOfficePhone(providerData.getPracOfficePhone2());
		practiceInfo.setPracOfficeFax(providerData.getPracOfficeFax2());
		practiceInfo.setPracOfficeEmail(providerData.getPracOfficeEmail2());
		practiceInfo.setPracBillContactName(providerData.getPracBillContactName2());
		practiceInfo.setPracBillAddress(providerData.getPracBillAddress2());
		practiceInfo.setPracBillCity(providerData.getPracBillCity2());
		practiceInfo.setPracBillState(providerData.getPracBillState2());
		practiceInfo.setPracBillZip(formatZipCode(providerData.getPracBillZip2()));
		practiceInfo.setPracBillCounty(providerData.getPracBillCounty2());
		practiceInfo.setPracBillPhone(providerData.getPracBillPhone2());
		practiceInfo.setPracBillFax(providerData.getPracBillFax2());
		practiceInfo.setPracBillContactEmail(providerData.getPracBillContactEmail2());
		practiceInfo.setPracNPINo(providerData.getPracNPINo2());

		practiceInfo.setBillMedicareGroup(providerData.getBillMedicareGroup2());
		practiceInfo.setBillMedicareIndividual(providerData.getBillMedicareIndividual2());
        practiceInfo.setMedicaidGroup(providerData.getMedicaidGroup2());
		practiceInfo.setMedicaidIndividual(providerData.getMedicaidIndividual2());
		practiceInfo.setPubicTrans(providerData.getPubicTrans2());
		practiceInfo.setHandicapAccess(providerData.getHandicapAccess2());
		practiceInfo.setEveningHrs(providerData.getEveningHrs2());
		//2013 SSCR 13503 change
		practiceInfo.setProvDir(providerData.getProvDir2());
		
		practiceInfo.setDaysOpenMon(providerData.getDaysOpenMon1());
		practiceInfo.setDaysOpenTue(providerData.getDaysOpenTue1());
		practiceInfo.setDaysOpenWed(providerData.getDaysOpenWed1());
		practiceInfo.setDaysOpenThu(providerData.getDaysOpenThu1());
		practiceInfo.setDaysOpenFri(providerData.getDaysOpenFri1());
		practiceInfo.setDaysOpenSat(providerData.getDaysOpenSat1());
		practiceInfo.setDaysOpenSun(providerData.getDaysOpenSun1());
		
		practiceInfo.setTimeZone(providerData.getTimeZoneOfficeHrs()[1]);
		
		practiceInfo.setDaysOpenTimeMon(providerData.getDaysOpenTimeMon()[1]);
		practiceInfo.setDaysOpenTimeTue(providerData.getDaysOpenTimeTue()[1]);
		practiceInfo.setDaysOpenTimeWed(providerData.getDaysOpenTimeWed()[1]);
		practiceInfo.setDaysOpenTimeThu(providerData.getDaysOpenTimeThu()[1]);
		practiceInfo.setDaysOpenTimeFri(providerData.getDaysOpenTimeFri()[1]);
		practiceInfo.setDaysOpenTimeSat(providerData.getDaysOpenTimeSat()[1]);
		practiceInfo.setDaysOpenTimeSun(providerData.getDaysOpenTimeSun()[1]);
		
		practiceInfo.setDaysCloseTimeMon(providerData.getDaysCloseTimeMon()[1]);
		practiceInfo.setDaysCloseTimeTue(providerData.getDaysCloseTimeTue()[1]);
		practiceInfo.setDaysCloseTimeWed(providerData.getDaysCloseTimeWed()[1]);
		practiceInfo.setDaysCloseTimeThu(providerData.getDaysCloseTimeThu()[1]);
		practiceInfo.setDaysCloseTimeFri(providerData.getDaysCloseTimeFri()[1]);
		practiceInfo.setDaysCloseTimeSat(providerData.getDaysCloseTimeSat()[1]);
		practiceInfo.setDaysCloseTimeSun(providerData.getDaysCloseTimeSun()[1]);
		
	    //Changes for the state mandate on 02/10/10 start
		practiceInfo.setLanguagesSpoken(providerData.getLanguagesSpoken2());
		practiceInfo.setOfferECI(providerData.getOfferECI2());
		practiceInfo.setOfferEPSDT(providerData.getOfferEPSDT2());
		practiceInfo.setProvideADB(providerData.getProvideADB2());
		practiceInfo.setProvideCSHCN(providerData.getProvideCSHCN2());
       //Changes for the state mandate on 02/10/10 end
		
		practiceInfo.setMatWaiveredPrescriber(providerData.getMatWaiveredPrescriber2());
		practiceInfo.setCertOpioidTreat(providerData.getCertOpioidTreat2());
		practiceInfo.setMatOpioid(providerData.getMatOpioid2());
		practiceInfo.setCounselOpioid(providerData.getCounselOpioid2());
		practiceInfo.setSudProv(providerData.getSudProv2());
		practiceInfo.setResTreatCtr(providerData.getResTreatCtr2());
		
		/* PMF Section Changes -- AD21239 --*/
		practiceInfo.setKyMedicaidId(providerData.getKyMedicaidId2());
		practiceInfo.setKyMedicaidPart(providerData.getKyMedicaidPart2());
		
		practiceInfo.setProvideTelehealth(providerData.getProvideTelehealth2());
	}

	private static void setPracticeAddress3(Provider providerData, PracticeInfo practiceInfo)
	{
	    String pracName = providerData.getPracName3();
		practiceInfo.setPracName(pracName);
		practiceInfo.setPracOfficeAddress(providerData.getPracOfficeAddress3());
		practiceInfo.setPracOfficeCity(providerData.getPracOfficeCity3());
		practiceInfo.setPracOfficeState(providerData.getPracOfficeState3());
		practiceInfo.setPracOfficeZip(formatZipCode(providerData.getPracOfficeZip3()));
		practiceInfo.setPracOfficeCounty(providerData.getPracOfficeCounty3());
		practiceInfo.setPracOfficePhone(providerData.getPracOfficePhone3());
		practiceInfo.setPracOfficeFax(providerData.getPracOfficeFax3());
		practiceInfo.setPracOfficeEmail(providerData.getPracOfficeEmail3());
		practiceInfo.setPracBillContactName(providerData.getPracBillContactName3());
		practiceInfo.setPracBillAddress(providerData.getPracBillAddress3());
		practiceInfo.setPracBillCity(providerData.getPracBillCity3());
		practiceInfo.setPracBillState(providerData.getPracBillState3());
		practiceInfo.setPracBillZip(formatZipCode(providerData.getPracBillZip3()));
		practiceInfo.setPracBillCounty(providerData.getPracBillCounty3());
		practiceInfo.setPracBillPhone(providerData.getPracBillPhone3());
		practiceInfo.setPracBillFax(providerData.getPracBillFax3());
		practiceInfo.setPracBillContactEmail(providerData.getPracBillContactEmail3());
		practiceInfo.setPracNPINo(providerData.getPracNPINo3());
		practiceInfo.setBillMedicareGroup(providerData.getBillMedicareGroup3());
		practiceInfo.setBillMedicareIndividual(providerData.getBillMedicareIndividual3());
        practiceInfo.setMedicaidGroup(providerData.getMedicaidGroup3());
		practiceInfo.setMedicaidIndividual(providerData.getMedicaidIndividual3());
		practiceInfo.setPubicTrans(providerData.getPubicTrans3());
		practiceInfo.setHandicapAccess(providerData.getHandicapAccess3());
		practiceInfo.setEveningHrs(providerData.getEveningHrs3());
		//2013 SSCR 13503 change
		practiceInfo.setProvDir(providerData.getProvDir3());

		practiceInfo.setDaysOpenMon(providerData.getDaysOpenMon2());
		practiceInfo.setDaysOpenTue(providerData.getDaysOpenTue2());
		practiceInfo.setDaysOpenWed(providerData.getDaysOpenWed2());
		practiceInfo.setDaysOpenThu(providerData.getDaysOpenThu2());
		practiceInfo.setDaysOpenFri(providerData.getDaysOpenFri2());
		practiceInfo.setDaysOpenSat(providerData.getDaysOpenSat2());
		practiceInfo.setDaysOpenSun(providerData.getDaysOpenSun2());
		
		practiceInfo.setTimeZone(providerData.getTimeZoneOfficeHrs()[2]);
		
		practiceInfo.setDaysOpenTimeMon(providerData.getDaysOpenTimeMon()[2]);
		practiceInfo.setDaysOpenTimeTue(providerData.getDaysOpenTimeTue()[2]);
		practiceInfo.setDaysOpenTimeWed(providerData.getDaysOpenTimeWed()[2]);
		practiceInfo.setDaysOpenTimeThu(providerData.getDaysOpenTimeThu()[2]);
		practiceInfo.setDaysOpenTimeFri(providerData.getDaysOpenTimeFri()[2]);
		practiceInfo.setDaysOpenTimeSat(providerData.getDaysOpenTimeSat()[2]);
		practiceInfo.setDaysOpenTimeSun(providerData.getDaysOpenTimeSun()[2]);
		
		practiceInfo.setDaysCloseTimeMon(providerData.getDaysCloseTimeMon()[2]);
		practiceInfo.setDaysCloseTimeTue(providerData.getDaysCloseTimeTue()[2]);
		practiceInfo.setDaysCloseTimeWed(providerData.getDaysCloseTimeWed()[2]);
		practiceInfo.setDaysCloseTimeThu(providerData.getDaysCloseTimeThu()[2]);
		practiceInfo.setDaysCloseTimeFri(providerData.getDaysCloseTimeFri()[2]);
		practiceInfo.setDaysCloseTimeSat(providerData.getDaysCloseTimeSat()[2]);
		practiceInfo.setDaysCloseTimeSun(providerData.getDaysCloseTimeSun()[2]);
	    //Changes for the state mandate on 02/10/10 start
		practiceInfo.setLanguagesSpoken(providerData.getLanguagesSpoken3());
		practiceInfo.setOfferECI(providerData.getOfferECI3());
		practiceInfo.setOfferEPSDT(providerData.getOfferEPSDT3());
		practiceInfo.setProvideADB(providerData.getProvideADB3());
		practiceInfo.setProvideCSHCN(providerData.getProvideCSHCN3());
       //Changes for the state mandate on 02/10/10 end
		
		practiceInfo.setMatWaiveredPrescriber(providerData.getMatWaiveredPrescriber3());
		practiceInfo.setCertOpioidTreat(providerData.getCertOpioidTreat3());
		practiceInfo.setMatOpioid(providerData.getMatOpioid3());
		practiceInfo.setCounselOpioid(providerData.getCounselOpioid3());
		practiceInfo.setSudProv(providerData.getSudProv3());
		practiceInfo.setResTreatCtr(providerData.getResTreatCtr3());
		
		/* PMF Section Changes -- AD21239 --*/
		practiceInfo.setKyMedicaidId(providerData.getKyMedicaidId3());
		practiceInfo.setKyMedicaidPart(providerData.getKyMedicaidPart3());
		
		practiceInfo.setProvideTelehealth(providerData.getProvideTelehealth3());
	}

	private static void setPracticeAddress4(Provider providerData, PracticeInfo practiceInfo)
	{
		String pracName = providerData.getPracName4();
		practiceInfo.setPracName(pracName);
		practiceInfo.setPracOfficeAddress(providerData.getPracOfficeAddress4());
		practiceInfo.setPracOfficeCity(providerData.getPracOfficeCity4());
		practiceInfo.setPracOfficeState(providerData.getPracOfficeState4());
		practiceInfo.setPracOfficeZip(formatZipCode(providerData.getPracOfficeZip4()));
		practiceInfo.setPracOfficeCounty(providerData.getPracOfficeCounty4());
		practiceInfo.setPracOfficePhone(providerData.getPracOfficePhone4());
		practiceInfo.setPracOfficeFax(providerData.getPracOfficeFax4());
		practiceInfo.setPracOfficeEmail(providerData.getPracOfficeEmail4());
		practiceInfo.setPracBillContactName(providerData.getPracBillContactName4());
		practiceInfo.setPracBillAddress(providerData.getPracBillAddress4());
		practiceInfo.setPracBillCity(providerData.getPracBillCity4());
		practiceInfo.setPracBillState(providerData.getPracBillState4());
		practiceInfo.setPracBillZip(formatZipCode(providerData.getPracBillZip4()));
		practiceInfo.setPracBillCounty(providerData.getPracBillCounty4());
		practiceInfo.setPracBillPhone(providerData.getPracBillPhone4());
		practiceInfo.setPracBillFax(providerData.getPracBillFax4());
		practiceInfo.setPracBillContactEmail(providerData.getPracBillContactEmail4());
		practiceInfo.setPracNPINo(providerData.getPracNPINo4());
		practiceInfo.setBillMedicareGroup(providerData.getBillMedicareGroup4());
		practiceInfo.setBillMedicareIndividual(providerData.getBillMedicareIndividual4());
        practiceInfo.setMedicaidGroup(providerData.getMedicaidGroup4());
		practiceInfo.setMedicaidIndividual(providerData.getMedicaidIndividual4());
		practiceInfo.setPubicTrans(providerData.getPubicTrans4());
		practiceInfo.setHandicapAccess(providerData.getHandicapAccess4());
		practiceInfo.setEveningHrs(providerData.getEveningHrs4());
		//2013 SSCR 13503 change
		practiceInfo.setProvDir(providerData.getProvDir4());

		practiceInfo.setDaysOpenMon(providerData.getDaysOpenMon3());
		practiceInfo.setDaysOpenTue(providerData.getDaysOpenTue3());
		practiceInfo.setDaysOpenWed(providerData.getDaysOpenWed3());
		practiceInfo.setDaysOpenThu(providerData.getDaysOpenThu3());
		practiceInfo.setDaysOpenFri(providerData.getDaysOpenFri3());
		practiceInfo.setDaysOpenSat(providerData.getDaysOpenSat3());
		practiceInfo.setDaysOpenSun(providerData.getDaysOpenSun3());
		
		practiceInfo.setTimeZone(providerData.getTimeZoneOfficeHrs()[3]);
		
		practiceInfo.setDaysOpenTimeMon(providerData.getDaysOpenTimeMon()[3]);
		practiceInfo.setDaysOpenTimeTue(providerData.getDaysOpenTimeTue()[3]);
		practiceInfo.setDaysOpenTimeWed(providerData.getDaysOpenTimeWed()[3]);
		practiceInfo.setDaysOpenTimeThu(providerData.getDaysOpenTimeThu()[3]);
		practiceInfo.setDaysOpenTimeFri(providerData.getDaysOpenTimeFri()[3]);
		practiceInfo.setDaysOpenTimeSat(providerData.getDaysOpenTimeSat()[3]);
		practiceInfo.setDaysOpenTimeSun(providerData.getDaysOpenTimeSun()[3]);
		
		practiceInfo.setDaysCloseTimeMon(providerData.getDaysCloseTimeMon()[3]);
		practiceInfo.setDaysCloseTimeTue(providerData.getDaysCloseTimeTue()[3]);
		practiceInfo.setDaysCloseTimeWed(providerData.getDaysCloseTimeWed()[3]);
		practiceInfo.setDaysCloseTimeThu(providerData.getDaysCloseTimeThu()[3]);
		practiceInfo.setDaysCloseTimeFri(providerData.getDaysCloseTimeFri()[3]);
		practiceInfo.setDaysCloseTimeSat(providerData.getDaysCloseTimeSat()[3]);
		practiceInfo.setDaysCloseTimeSun(providerData.getDaysCloseTimeSun()[3]);
	    //Changes for the state mandate on 02/10/10 start
		practiceInfo.setLanguagesSpoken(providerData.getLanguagesSpoken4());
		practiceInfo.setOfferECI(providerData.getOfferECI4());
		practiceInfo.setOfferEPSDT(providerData.getOfferEPSDT4());
		practiceInfo.setProvideADB(providerData.getProvideADB4());
		practiceInfo.setProvideCSHCN(providerData.getProvideCSHCN4());
       //Changes for the state mandate on 02/10/10 end
		
		practiceInfo.setMatWaiveredPrescriber(providerData.getMatWaiveredPrescriber4());
		practiceInfo.setCertOpioidTreat(providerData.getCertOpioidTreat4());
		practiceInfo.setMatOpioid(providerData.getMatOpioid4());
		practiceInfo.setCounselOpioid(providerData.getCounselOpioid4());
		practiceInfo.setSudProv(providerData.getSudProv4());
		practiceInfo.setResTreatCtr(providerData.getResTreatCtr4());
		
		/* PMF Section Changes -- AD21239 --*/
		practiceInfo.setKyMedicaidId(providerData.getKyMedicaidId4());
		practiceInfo.setKyMedicaidPart(providerData.getKyMedicaidPart4());
		
		practiceInfo.setProvideTelehealth(providerData.getProvideTelehealth4());

	}

	private static void setPracticeAddress5(Provider providerData, PracticeInfo practiceInfo)
	{
		String pracName = providerData.getPracName5();
		practiceInfo.setPracName(pracName);
		practiceInfo.setPracOfficeAddress(providerData.getPracOfficeAddress5());
		practiceInfo.setPracOfficeCity(providerData.getPracOfficeCity5());
		practiceInfo.setPracOfficeState(providerData.getPracOfficeState5());
		practiceInfo.setPracOfficeZip(formatZipCode(providerData.getPracOfficeZip5()));
		practiceInfo.setPracOfficeCounty(providerData.getPracOfficeCounty5());
		practiceInfo.setPracOfficePhone(providerData.getPracOfficePhone5());
		practiceInfo.setPracOfficeFax(providerData.getPracOfficeFax5());
		practiceInfo.setPracOfficeEmail(providerData.getPracOfficeEmail5());
		practiceInfo.setPracBillContactName(providerData.getPracBillContactName5());
		practiceInfo.setPracBillAddress(providerData.getPracBillAddress5());
		practiceInfo.setPracBillCity(providerData.getPracBillCity5());
		practiceInfo.setPracBillState(providerData.getPracBillState5());
		practiceInfo.setPracBillZip(formatZipCode(providerData.getPracBillZip5()));
		practiceInfo.setPracBillCounty(providerData.getPracBillCounty5());
		practiceInfo.setPracBillPhone(providerData.getPracBillPhone5());
		practiceInfo.setPracBillFax(providerData.getPracBillFax5());
		practiceInfo.setPracBillContactEmail(providerData.getPracBillContactEmail5());
		practiceInfo.setPracNPINo(providerData.getPracNPINo5());
		practiceInfo.setBillMedicareGroup(providerData.getBillMedicareGroup5());
		practiceInfo.setBillMedicareIndividual(providerData.getBillMedicareIndividual5());
        practiceInfo.setMedicaidGroup(providerData.getMedicaidGroup5());
		practiceInfo.setMedicaidIndividual(providerData.getMedicaidIndividual5());
		practiceInfo.setPubicTrans(providerData.getPubicTrans5());
		practiceInfo.setHandicapAccess(providerData.getHandicapAccess5());
		practiceInfo.setEveningHrs(providerData.getEveningHrs5());
		//2013 SSCR 13503 change
		practiceInfo.setProvDir(providerData.getProvDir5());

		practiceInfo.setDaysOpenMon(providerData.getDaysOpenMon4());
		practiceInfo.setDaysOpenTue(providerData.getDaysOpenTue4());
		practiceInfo.setDaysOpenWed(providerData.getDaysOpenWed4());
		practiceInfo.setDaysOpenThu(providerData.getDaysOpenThu4());
		practiceInfo.setDaysOpenFri(providerData.getDaysOpenFri4());
		practiceInfo.setDaysOpenSat(providerData.getDaysOpenSat4());
		practiceInfo.setDaysOpenSun(providerData.getDaysOpenSun4());
		
		practiceInfo.setTimeZone(providerData.getTimeZoneOfficeHrs()[4]);
		
		practiceInfo.setDaysOpenTimeMon(providerData.getDaysOpenTimeMon()[4]);
		practiceInfo.setDaysOpenTimeTue(providerData.getDaysOpenTimeTue()[4]);
		practiceInfo.setDaysOpenTimeWed(providerData.getDaysOpenTimeWed()[4]);
		practiceInfo.setDaysOpenTimeThu(providerData.getDaysOpenTimeThu()[4]);
		practiceInfo.setDaysOpenTimeFri(providerData.getDaysOpenTimeFri()[4]);
		practiceInfo.setDaysOpenTimeSat(providerData.getDaysOpenTimeSat()[4]);
		practiceInfo.setDaysOpenTimeSun(providerData.getDaysOpenTimeSun()[4]);
		
		practiceInfo.setDaysCloseTimeMon(providerData.getDaysCloseTimeMon()[4]);
		practiceInfo.setDaysCloseTimeTue(providerData.getDaysCloseTimeTue()[4]);
		practiceInfo.setDaysCloseTimeWed(providerData.getDaysCloseTimeWed()[4]);
		practiceInfo.setDaysCloseTimeThu(providerData.getDaysCloseTimeThu()[4]);
		practiceInfo.setDaysCloseTimeFri(providerData.getDaysCloseTimeFri()[4]);
		practiceInfo.setDaysCloseTimeSat(providerData.getDaysCloseTimeSat()[4]);
		practiceInfo.setDaysCloseTimeSun(providerData.getDaysCloseTimeSun()[4]);
	    //Changes for the state mandate on 02/10/10 start
		practiceInfo.setLanguagesSpoken(providerData.getLanguagesSpoken5());
		practiceInfo.setOfferECI(providerData.getOfferECI5());
		practiceInfo.setOfferEPSDT(providerData.getOfferEPSDT5());
		practiceInfo.setProvideADB(providerData.getProvideADB5());
		practiceInfo.setProvideCSHCN(providerData.getProvideCSHCN5());
       //Changes for the state mandate on 02/10/10 end
		
		practiceInfo.setMatWaiveredPrescriber(providerData.getMatWaiveredPrescriber5());
		practiceInfo.setCertOpioidTreat(providerData.getCertOpioidTreat5());
		practiceInfo.setMatOpioid(providerData.getMatOpioid5());
		practiceInfo.setCounselOpioid(providerData.getCounselOpioid5());
		practiceInfo.setSudProv(providerData.getSudProv5());
		practiceInfo.setResTreatCtr(providerData.getResTreatCtr5());
		
		/* PMF Section Changes -- AD21239 --*/
		practiceInfo.setKyMedicaidId(providerData.getKyMedicaidId5());
		practiceInfo.setKyMedicaidPart(providerData.getKyMedicaidPart5());
		
		practiceInfo.setProvideTelehealth(providerData.getProvideTelehealth5());
	}

	private static void setPracticeAddress6(Provider providerData, PracticeInfo practiceInfo)
	{
		String pracName = providerData.getPracName6();
		practiceInfo.setPracName(pracName);
		practiceInfo.setPracOfficeAddress(providerData.getPracOfficeAddress6());
		practiceInfo.setPracOfficeCity(providerData.getPracOfficeCity6());
		practiceInfo.setPracOfficeState(providerData.getPracOfficeState6());
		practiceInfo.setPracOfficeZip(formatZipCode(providerData.getPracOfficeZip6()));
		practiceInfo.setPracOfficeCounty(providerData.getPracOfficeCounty6());
		practiceInfo.setPracOfficePhone(providerData.getPracOfficePhone6());
		practiceInfo.setPracOfficeFax(providerData.getPracOfficeFax6());
		practiceInfo.setPracOfficeEmail(providerData.getPracOfficeEmail6());
		practiceInfo.setPracBillContactName(providerData.getPracBillContactName6());
		practiceInfo.setPracBillAddress(providerData.getPracBillAddress6());
		practiceInfo.setPracBillCity(providerData.getPracBillCity6());
		practiceInfo.setPracBillState(providerData.getPracBillState6());
		practiceInfo.setPracBillZip(formatZipCode(providerData.getPracBillZip6()));
		practiceInfo.setPracBillCounty(providerData.getPracBillCounty6());
		practiceInfo.setPracBillPhone(providerData.getPracBillPhone6());
		practiceInfo.setPracBillFax(providerData.getPracBillFax6());
		practiceInfo.setPracBillContactEmail(providerData.getPracBillContactEmail6());
		practiceInfo.setPracNPINo(providerData.getPracNPINo6());
		practiceInfo.setBillMedicareGroup(providerData.getBillMedicareGroup6());
		practiceInfo.setBillMedicareIndividual(providerData.getBillMedicareIndividual6());
        practiceInfo.setMedicaidGroup(providerData.getMedicaidGroup6());
		practiceInfo.setMedicaidIndividual(providerData.getMedicaidIndividual6());
		practiceInfo.setPubicTrans(providerData.getPubicTrans6());
		practiceInfo.setHandicapAccess(providerData.getHandicapAccess6());
		practiceInfo.setEveningHrs(providerData.getEveningHrs6());
		//2013 SSCR 13503 change
		practiceInfo.setProvDir(providerData.getProvDir6());

		practiceInfo.setDaysOpenMon(providerData.getDaysOpenMon5());
		practiceInfo.setDaysOpenTue(providerData.getDaysOpenTue5());
		practiceInfo.setDaysOpenWed(providerData.getDaysOpenWed5());
		practiceInfo.setDaysOpenThu(providerData.getDaysOpenThu5());
		practiceInfo.setDaysOpenFri(providerData.getDaysOpenFri5());
		practiceInfo.setDaysOpenSat(providerData.getDaysOpenSat5());
		practiceInfo.setDaysOpenSun(providerData.getDaysOpenSun5());
		
		practiceInfo.setTimeZone(providerData.getTimeZoneOfficeHrs()[5]);
		
		practiceInfo.setDaysOpenTimeMon(providerData.getDaysOpenTimeMon()[5]);
		practiceInfo.setDaysOpenTimeTue(providerData.getDaysOpenTimeTue()[5]);
		practiceInfo.setDaysOpenTimeWed(providerData.getDaysOpenTimeWed()[5]);
		practiceInfo.setDaysOpenTimeThu(providerData.getDaysOpenTimeThu()[5]);
		practiceInfo.setDaysOpenTimeFri(providerData.getDaysOpenTimeFri()[5]);
		practiceInfo.setDaysOpenTimeSat(providerData.getDaysOpenTimeSat()[5]);
		practiceInfo.setDaysOpenTimeSun(providerData.getDaysOpenTimeSun()[5]);
		
		practiceInfo.setDaysCloseTimeMon(providerData.getDaysCloseTimeMon()[5]);
		practiceInfo.setDaysCloseTimeTue(providerData.getDaysCloseTimeTue()[5]);
		practiceInfo.setDaysCloseTimeWed(providerData.getDaysCloseTimeWed()[5]);
		practiceInfo.setDaysCloseTimeThu(providerData.getDaysCloseTimeThu()[5]);
		practiceInfo.setDaysCloseTimeFri(providerData.getDaysCloseTimeFri()[5]);
		practiceInfo.setDaysCloseTimeSat(providerData.getDaysCloseTimeSat()[5]);
		practiceInfo.setDaysCloseTimeSun(providerData.getDaysCloseTimeSun()[5]);
	    //Changes for the state mandate on 02/10/10 start
		practiceInfo.setLanguagesSpoken(providerData.getLanguagesSpoken6());
		practiceInfo.setOfferECI(providerData.getOfferECI6());
		practiceInfo.setOfferEPSDT(providerData.getOfferEPSDT6());
		practiceInfo.setProvideADB(providerData.getProvideADB6());
		practiceInfo.setProvideCSHCN(providerData.getProvideCSHCN6());
       //Changes for the state mandate on 02/10/10 end
		
		practiceInfo.setMatWaiveredPrescriber(providerData.getMatWaiveredPrescriber6());
		practiceInfo.setCertOpioidTreat(providerData.getCertOpioidTreat6());
		practiceInfo.setMatOpioid(providerData.getMatOpioid6());
		practiceInfo.setCounselOpioid(providerData.getCounselOpioid6());
		practiceInfo.setSudProv(providerData.getSudProv6());
		practiceInfo.setResTreatCtr(providerData.getResTreatCtr6());
		
		/* PMF Section Changes -- AD21239 --*/
		practiceInfo.setKyMedicaidId(providerData.getKyMedicaidId6());
		practiceInfo.setKyMedicaidPart(providerData.getKyMedicaidPart6());
		
		practiceInfo.setProvideTelehealth(providerData.getProvideTelehealth6());
	}

	private static void insertPracticeInfo(Connection con, Provider providerData, int seqNo) throws SQLException
	{
	    PracticeInfo practiceInfo = new PracticeInfo();
		String pracSeqNo = "0";
		String pracName = providerData.getPracName1();
        if (pracName!=null && !pracName.equals(""))
        {
        	//System.out.println("inside pracName");
			setPracticeAddress1(providerData, practiceInfo);
  			pracSeqNo = "1";
            insertPracticeInfo(con, practiceInfo, seqNo, pracSeqNo);
            //System.out.println("after inside pracName");
        }

		String pracOfficeAddress = providerData.getPracOfficeAddress2();
		//System.out.println("prac"+pracOfficeAddress);
        if (pracOfficeAddress!=null && !pracOfficeAddress.equals("") )
        {
        	//System.out.println("inside pracOfficeAddress");
			setPracticeAddress2(providerData, practiceInfo);
			pracSeqNo = "2";
            insertPracticeInfo(con, practiceInfo, seqNo, pracSeqNo);
        }

        pracName = providerData.getPracName3();
        //System.out.println("prac1"+pracName);
        if (pracName!=null && !pracName.equals("") )
        {
        	//System.out.println("inside pracName2");
			setPracticeAddress3(providerData, practiceInfo);
			pracSeqNo = "3";
            insertPracticeInfo(con, practiceInfo, seqNo, pracSeqNo);
        }
        pracName = providerData.getPracName4();
        //System.out.println("prac2"+pracName);
        if (pracName!=null && !pracName.equals("") )
        {
        	//System.out.println("inside pracName3");
			setPracticeAddress4(providerData, practiceInfo);
			pracSeqNo = "4";
            insertPracticeInfo(con, practiceInfo, seqNo, pracSeqNo);
        }

        pracName = providerData.getPracName5();
        //System.out.println("prac3"+pracName);
        if (pracName!=null && !pracName.equals("") )
        {
        	//System.out.println("inside pracName4");
		   setPracticeAddress5(providerData, practiceInfo);
		   pracSeqNo = "5";
           insertPracticeInfo(con, practiceInfo, seqNo, pracSeqNo);
        }

        pracName = providerData.getPracName6();
        //System.out.println("prac3"+pracName);
        if (pracName!=null && !pracName.equals("") )
        {
        	//System.out.println("inside pracName5");
			setPracticeAddress6(providerData, practiceInfo);
			pracSeqNo = "6";
            insertPracticeInfo(con, practiceInfo, seqNo, pracSeqNo);
        }
	}


	private static void insertCoveringPhysicians(Connection con, Provider providerData, int seqNo) throws SQLException
	{
		String covPhySeqNo = "0";
		String grpEntityName = providerData.getGrpEntityName1();
		CoveringPhysicians coveringPhysicians = new CoveringPhysicians();
		if (!grpEntityName.equals("") )
		{
			covPhySeqNo = "1";
			coveringPhysicians.setGrpEntityName(grpEntityName);
			coveringPhysicians.setSpecialty(providerData.getSpecialty1());
			coveringPhysicians.setTaxID(providerData.getTaxID1());
			coveringPhysicians.setEffectiveDate(providerData.getEffectiveDate1());
			coveringPhysicians.setAddress(new Address(providerData.getCpHospitalAddress()[0],providerData.getCpHospitalCity()[0],providerData.getCpHospitalState()[0],providerData.getCpHospitalZip()[0]));

			insertCoveringPhysicians(con, coveringPhysicians, seqNo, covPhySeqNo);
		}

		grpEntityName = providerData.getGrpEntityName2();
		if (!grpEntityName.equals("") && !grpEntityName.equals(BLANK))
		{
			covPhySeqNo = "2";
			coveringPhysicians.setGrpEntityName(grpEntityName);
			coveringPhysicians.setSpecialty(providerData.getSpecialty2());
			coveringPhysicians.setTaxID(providerData.getTaxID2());
			coveringPhysicians.setEffectiveDate(providerData.getEffectiveDate2());
			coveringPhysicians.setAddress(new Address(providerData.getCpHospitalAddress()[1],providerData.getCpHospitalCity()[1],providerData.getCpHospitalState()[1],providerData.getCpHospitalZip()[1]));
			insertCoveringPhysicians(con, coveringPhysicians, seqNo, covPhySeqNo);
		}

		grpEntityName = providerData.getGrpEntityName3();
		if (!grpEntityName.equals("") && !grpEntityName.equals(BLANK))
		{
			covPhySeqNo = "3";
			coveringPhysicians.setGrpEntityName(grpEntityName);
			coveringPhysicians.setSpecialty(providerData.getSpecialty3());
			coveringPhysicians.setTaxID(providerData.getTaxID3());
			coveringPhysicians.setEffectiveDate(providerData.getEffectiveDate3());
			coveringPhysicians.setAddress(new Address(providerData.getCpHospitalAddress()[2],providerData.getCpHospitalCity()[2],providerData.getCpHospitalState()[2],providerData.getCpHospitalZip()[2]));
			insertCoveringPhysicians(con, coveringPhysicians, seqNo, covPhySeqNo);
		}

		grpEntityName = providerData.getGrpEntityName4();
		if (!grpEntityName.equals("") && !grpEntityName.equals(BLANK))
		{
			covPhySeqNo = "4";
			coveringPhysicians.setGrpEntityName(grpEntityName);
			coveringPhysicians.setSpecialty(providerData.getSpecialty4());
			coveringPhysicians.setTaxID(providerData.getTaxID4());
			coveringPhysicians.setEffectiveDate(providerData.getEffectiveDate4());
			coveringPhysicians.setAddress(new Address(providerData.getCpHospitalAddress()[3],providerData.getCpHospitalCity()[3],providerData.getCpHospitalState()[3],providerData.getCpHospitalZip()[3]));
			insertCoveringPhysicians(con, coveringPhysicians, seqNo, covPhySeqNo);
		}

		grpEntityName = providerData.getGrpEntityName5();
		if (!grpEntityName.equals("") && !grpEntityName.equals(BLANK))
		{
			covPhySeqNo = "5";
			coveringPhysicians.setGrpEntityName(grpEntityName);
			coveringPhysicians.setSpecialty(providerData.getSpecialty5());
			coveringPhysicians.setTaxID(providerData.getTaxID5());
			coveringPhysicians.setEffectiveDate(providerData.getEffectiveDate5());
			coveringPhysicians.setAddress(new Address(providerData.getCpHospitalAddress()[4],providerData.getCpHospitalCity()[4],providerData.getCpHospitalState()[4],providerData.getCpHospitalZip()[4]));
			insertCoveringPhysicians(con, coveringPhysicians, seqNo, covPhySeqNo);
		}
	}

	private static void insertPracticeGeneralInfo(Connection con, Provider providerData, int seqNo) throws SQLException
	{
		PreparedStatement ps = null;
		String extractFlag ="N";
		String taxID =  getSafeString(providerData.getTaxID());
		String anthemPIN = getSafeString(providerData.getDBAnthemPIN());
		String soloGroup =  getSafeString(providerData.getSoloGroup());
		String numberInGroup = getSafeString(providerData.getNumberInGroup());
		String rapidUpdate =  getSafeString(providerData.getRapidUpdate());
		String effectiveDate =  getSafeString(providerData.getEffectiveDate());
		String addProvider =  getSafeString(providerData.getAddProvider());
		String delProvider =   getSafeString(providerData.getDelProvider());
		String addLocation = getSafeString(providerData.getAddLocation());
		String addProvToLocation = getSafeString(providerData.getAddProvToLocation());
		String chgSpecialty = getSafeString(providerData.getChgSpecialty());
		String chgProvName = getSafeString(providerData.getChgProvName());
		String delLocation = getSafeString(providerData.getDelLocation());
		String  delProvFromLocation = getSafeString(providerData.getDelProvFromLocation());
		String  chgPracName =getSafeString(providerData.getChgPracName());
		String chgPracAddress =   getSafeString(providerData.getChgPracAddress());
		String chgPracPhone = getSafeString(providerData.getChgPracPhone());
		String chgTaxID = getSafeString(providerData.getChgTaxID());
		String chgBillName = getSafeString(providerData.getChgBillName());
		String chgBillAddress = getSafeString(providerData.getChgBillAddress());
		String chgBillPhone = getSafeString(providerData.getChgBillPhone());
		String delReason = getSafeString(providerData.getDelReason());
		String addNPI = getSafeString(providerData.getAddNPI());
		String chgNPI = getSafeString(providerData.getChgNPI());
		String comments = getSafeString(providerData.getComments());
		String oldTaxID = getSafeString(providerData.getOldTaxID());
		String w2Comments = getSafeString(providerData.getW2Comments());

		try
		{
			String sql = getInsertPracticeGeneralSQL();
			ps = con.prepareStatement(sql);
			ps.setInt(1,seqNo);
			ps.setString(2,taxID);
			ps.setString(3,anthemPIN);
			ps.setString(4,soloGroup);
			ps.setString(5,numberInGroup);
			ps.setString(6,effectiveDate);
			ps.setString(7,addProvider);
			ps.setString(8,delProvider);
			ps.setString(9,addLocation );
			ps.setString(10,addProvToLocation);
			ps.setString(11,chgSpecialty);
			ps.setString(12,chgProvName);
			ps.setString(13,delLocation);
			ps.setString(14,delProvFromLocation);
			ps.setString(15,chgPracName);
			ps.setString(16,chgPracAddress);
			ps.setString(17,chgPracPhone);
			ps.setString(18,chgTaxID);
			ps.setString(19,chgBillName );
			ps.setString(20,chgBillAddress );
			ps.setString(21,chgBillPhone);
			ps.setString(22,delReason);
			ps.setString(23,oldTaxID);
			ps.setString(24,comments);
			ps.setString(25,extractFlag);
			ps.setString(26,rapidUpdate );
            ps.setString(27, getSafeString(providerData.getGrpNPINumber()) );
            ps.setString(28,addNPI);
            ps.setString(29,chgNPI);
            ps.setString(30,getSafeString(providerData.getChgOfficeHours()));
            ps.setString(31,getSafeString(providerData.getEditPatientInfo()));
            ps.setString(32,getSafeString(providerData.getEditAreasOfExpertise()));
            ps.setString(33,getSafeString(providerData.getEthnicOrigin()));
            ps.setString(34,getSafeString(providerData.getConfirmation()));
            ps.setString(35, getSafeString(providerData.getConfProvAgreement()));
            ps.setString(36, getSafeString(providerData.getConfW2()));
            ps.setString(37, getSafeString(w2Comments));
			ps.executeUpdate();
			logger.info("InsertPracticeGeneral executed successfully");
			System.out.println("InsertPracticeGeneral executed successfully");
		}
		catch(SQLException sqle)
		{
			logger.info("Error excuting Practice general sql" + sqle);
			logger.error("Error in PMFUtils: Insert into Practice General Info " + sqle.getMessage());
			logger.error(stack2string(sqle));
			throw new SQLException(sqle.getMessage());
		}
		finally {
			closeStatement(ps);
		}
	}

	private static void insertPatientInfo(Connection con, List pa_info,String nonPsychEval, int seqNo) throws SQLException
	{
		PreparedStatement ps = null;
		String sql = "INSERT INTO PATIENT_INFO (PGI_ID, CHILDREN, ADOLESCENTS, ADULTS, SENIOR, DEAF, DISABLED,NON_PSYCH_EVAL) values (?,?,?,?,?,?,?,?)";

		try{
			ps = con.prepareStatement(sql);

			ps.setInt(1, seqNo);
			ps.setString(2, getListValue(pa_info, "children"));
			ps.setString(3, getListValue(pa_info, "adolescents"));
			ps.setString(4, getListValue(pa_info, "adults"));
			ps.setString(5, getListValue(pa_info, "senior"));
			ps.setString(6, getListValue(pa_info, "deaf"));
			ps.setString(7, getListValue(pa_info, "disabled"));
			ps.setString(8, nonPsychEval);
			ps.executeUpdate();

		}
		catch(SQLException sqle){
			logger.info("Error excuting Patient info sql" + sqle);
			logger.error("Error in PMFUtils: Insert into Patient Info " + sqle.getMessage());
			logger.error(stack2string(sqle));
			throw new SQLException(sqle.getMessage());
		}
		finally {
			closeStatement(ps);
		}
	}


	private static void insertAreasOfExpertise(Connection con, AreasOfExpertise expertiseInfo, int seqNo) throws SQLException
	{
		StringBuffer strbuf = new StringBuffer();
		PreparedStatement ps = null;
		strbuf.append("INSERT INTO AREAS_OF_EXPERTISE (PGI_ID, ADD_ADHD, DIALECTICAL, NEUROPSYCHOLOGICAL, ADOPTION, DIVORCE, ");
		strbuf.append("ANXIETY, DOM_VIOLENCE, OBSESSIVE_COMP, AUTISM, EATING_DISORDER, PAIN_MGMT, BARIATRIC, ELECTRO_CONVULSIVE, ");
		strbuf.append("PERSONALITY_DIS, BEHAVIOR_MOD, ENDOFLIFE_ISSUES, POSTPARTUM, BIPOLAR, FAMILY_THERAPY, PTSD, BRIEF_SOLUTION, ");
		strbuf.append("GAY_ISSUES, PRENATAL_ISSUES, CHEMICAL_DEP, GROUP_THERAPY, PSYCHOLOGICAL_TEST, CHEMICAL_DEP_ASSESSMENT, ");
		strbuf.append("HIV__ISSUES, CHRISTIAN_COUNSELING, INFERTILITY, SCHIZOPHRENIA, COMPULSIVE_GAMBLING, MEDICATION_MGMT, ");
		strbuf.append("SEXUAL_ABUSE, ETHNIC_ISSUES, SEXUAL_DISORDER, DEPRESSIVE_DISORDER, MEN_ISSUES, VICTIMS, WOMEN_ISSUES, ");
		strbuf.append("COUNSEL_OPIOID, MAT_WAIVERED, MAT_OPIOID, TELEHEALTH_PROV_AOE)");
		strbuf.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		String sql = strbuf.toString();
		try{
			ps = con.prepareStatement(sql);
			ps.setInt(1, seqNo);
			ps.setString(2, expertiseInfo.getStringValue(expertiseInfo.isAdd()));
			ps.setString(3, expertiseInfo.getStringValue(expertiseInfo.isDialectical()));
			ps.setString(4, expertiseInfo.getStringValue(expertiseInfo.isNeuropsychological()));
			ps.setString(5, expertiseInfo.getStringValue(expertiseInfo.isAdoption()));
			ps.setString(6, expertiseInfo.getStringValue(expertiseInfo.isDivorce()));
			ps.setString(7, expertiseInfo.getStringValue(expertiseInfo.isAnxiety()));
			ps.setString(8, expertiseInfo.getStringValue(expertiseInfo.isDomViolence()));
			ps.setString(9, expertiseInfo.getStringValue(expertiseInfo.isObsessiveComp()));
			ps.setString(10, expertiseInfo.getStringValue(expertiseInfo.isAutism()));
			ps.setString(11, expertiseInfo.getStringValue(expertiseInfo.isEatingDisorder()));
			ps.setString(12, expertiseInfo.getStringValue(expertiseInfo.isPainMgmt()));
			ps.setString(13, expertiseInfo.getStringValue(expertiseInfo.isBariatric()));
			ps.setString(14, expertiseInfo.getStringValue(expertiseInfo.isElectroconvulsive()));
			ps.setString(15, expertiseInfo.getStringValue(expertiseInfo.isPersonalityDis()));
			ps.setString(16, expertiseInfo.getStringValue(expertiseInfo.isBehaviorMod()));
			ps.setString(17, expertiseInfo.getStringValue(expertiseInfo.isEndofLifeIssues()));
			ps.setString(18, expertiseInfo.getStringValue(expertiseInfo.isPostpartum()));
			ps.setString(19, expertiseInfo.getStringValue(expertiseInfo.isBipolar()));
			ps.setString(20, expertiseInfo.getStringValue(expertiseInfo.isFamilyTherapy()));
			ps.setString(21, expertiseInfo.getStringValue(expertiseInfo.isPTSD()));
			ps.setString(22, expertiseInfo.getStringValue(expertiseInfo.isBriefSolution()));
			ps.setString(23, expertiseInfo.getStringValue(expertiseInfo.isGayIssues()));
			ps.setString(24, expertiseInfo.getStringValue(expertiseInfo.isPrenatalIssues()));
			ps.setString(25, expertiseInfo.getStringValue(expertiseInfo.isChemicalDep()));
			ps.setString(26, expertiseInfo.getStringValue(expertiseInfo.isGroupTherapy()));
			ps.setString(27, expertiseInfo.getStringValue(expertiseInfo.isPsychologicalTest()));
			ps.setString(28, expertiseInfo.getStringValue(expertiseInfo.isChemicalDepAssessment()));
			ps.setString(29, expertiseInfo.getStringValue(expertiseInfo.isHIVRelatedIssues()));
			ps.setString(30, expertiseInfo.getStringValue(expertiseInfo.isChristianCounseling()));
			ps.setString(31, expertiseInfo.getStringValue(expertiseInfo.isInfertility()));
			ps.setString(32, expertiseInfo.getStringValue(expertiseInfo.isSchizophrenia()));
			ps.setString(33, expertiseInfo.getStringValue(expertiseInfo.isCompulsiveGambling()));
			ps.setString(34, expertiseInfo.getStringValue(expertiseInfo.isMedicationMgmt()));
			ps.setString(35, expertiseInfo.getStringValue(expertiseInfo.isSexualAbuse()));
			ps.setString(36, expertiseInfo.getStringValue(expertiseInfo.isEthnicIssues()));
			ps.setString(37, expertiseInfo.getStringValue(expertiseInfo.isSexualDisorder()));
			ps.setString(38, expertiseInfo.getStringValue(expertiseInfo.isDepressiveDisorder()));
			ps.setString(39, expertiseInfo.getStringValue(expertiseInfo.isMenIssues()));
			ps.setString(40, expertiseInfo.getStringValue(expertiseInfo.isVictims()));
			ps.setString(41, expertiseInfo.getStringValue(expertiseInfo.isWomenIssues()));
			ps.setString(42, expertiseInfo.getStringValue(expertiseInfo.isCounselOpioid()));
			ps.setString(43, expertiseInfo.getStringValue(expertiseInfo.isMatWaivered()));
			ps.setString(44, expertiseInfo.getStringValue(expertiseInfo.isMatOpioid()));
			ps.setString(45, expertiseInfo.getStringValue(expertiseInfo.isTelehealthProv()));

            ps.executeUpdate();
		}
		catch(SQLException sqle){
			logger.info("Error excuting Areas Of Expertise sql" + sqle);
			logger.error("Error in PMFUtils: Insert into Areas Of Expertise  " + sqle.getMessage());
			logger.error(stack2string(sqle));
			throw new SQLException(sqle.getMessage());
		}
		finally {
			closeStatement(ps);
		}
	}
	private static void insertFwdHealthInfo(Connection con,Provider providerData, int seqNo ) throws SQLException
	{
		StringBuffer strbuf = new StringBuffer();
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		strbuf.append("INSERT INTO FWDHEALTH_INFO (PGI_ID, SEQUENCE_NO, FWDHEALTH_CERT_NPI, FWDHEALTH_NPI_BILLED, FWDHEALTH_CERT_EFF_DT, FWDHEALTH_RECERT_DT )");
		strbuf.append(" values (?,?,?,?,?,?)");
		String sql = strbuf.toString();
		try{
			if(!providerData.getFwdHealthCertNPI1().equals(" ") ){
				ps1 = con.prepareStatement(sql);
				ps1.setInt(1, seqNo);
				ps1.setInt(2,1);
				ps1.setString(3, getSafeString(providerData.getFwdHealthCertNPI1()));
				ps1.setString(4, getSafeString(providerData.getFwdHealthCertNPIBilled1()));
				ps1.setString(5, getSafeString(providerData.getFwdHealthCertEffDt1()));
				ps1.setString(6, getSafeString(providerData.getFwdHealthRecertEffDt1()));
				ps1.executeUpdate();
			}
			if(!providerData.getFwdHealthCertNPI2().equals(" ") ){
				ps2 = con.prepareStatement(sql);
				ps2.setInt(1, seqNo);
				ps2.setInt(2,2);
				ps2.setString(3, getSafeString(providerData.getFwdHealthCertNPI2()));
				ps2.setString(4, getSafeString(providerData.getFwdHealthCertNPIBilled2()));
				ps2.setString(5, getSafeString(providerData.getFwdHealthCertEffDt2()));
				ps2.setString(6, getSafeString(providerData.getFwdHealthRecertEffDt2()));
				ps2.executeUpdate();
			}
			if(!providerData.getFwdHealthCertNPI3().equals(" ") ){
				ps3 = con.prepareStatement(sql);
				ps3.setInt(1, seqNo);
				ps3.setInt(2,3);
				ps3.setString(3, getSafeString(providerData.getFwdHealthCertNPI3()));
				ps3.setString(4, getSafeString(providerData.getFwdHealthCertNPIBilled3()));
				ps3.setString(5, getSafeString(providerData.getFwdHealthCertEffDt3()));
				ps3.setString(6, getSafeString(providerData.getFwdHealthRecertEffDt3()));
				ps3.executeUpdate();
			}
		}
		catch(SQLException sqle){
			logger.info("Error excuting Areas Of Expertise sql" + sqle);
			logger.error("Error in PMFUtils: Insert into Areas Of Expertise  " + sqle.getMessage());
			logger.error(stack2string(sqle));
			throw new SQLException(sqle.getMessage());
		}
		finally {
			closeStatement(ps1);
			closeStatement(ps2);
			closeStatement(ps3);
		}
	}

	private static void commitTransaction(Connection con) throws SQLException
	{
		//logger.info("Transaction is successfully committed");
		con.commit();
	}

	private static void closeStatement(Statement ps)
	{
		try
		{
			if (ps !=null)
			{
				ps.close();
			}
		}
		catch (SQLException excp)
		{
			logException ("Exception closing statement", excp);
		}
	}

	private static void disableAutoCommit(Connection con) throws SQLException
	{
		if (con.getAutoCommit())
		{
		    con.setAutoCommit(false);
		}
	}

	private static void rollBackTransaction(Connection con)
	{
		try
		{
		    con.rollback();
		}
		catch (SQLException ignore)
		{
			logger.info("Error rolling back transaction");
		}
	}

	private static String getListValue(List list, String val){
		if(list.contains(val))
			return "Y";
		else
			return " ";
	}
	
	/**
	   * Converts special HTML characters (such as <,>,",&) into the
	   * HTML encoded representations.
	   * @param value the string to encode
	   * @param returnNBSPIfEmpty if true and the value String is empty a string of "&nbsp;" will
	   *                          be returned
	   * @return HTML encoded version of the string
	   */
	  public static String htmlEncode(String value, boolean returnNBSPIfEmpty)
	  {
	    StringBuffer sbuf = new StringBuffer();
	
	    if (value != null && value.length() > 0)
	    {
	      for(int index = 0; index < value.length(); index++)
	      {
	        char c = value.charAt(index);
	        if (c == '<')
	          sbuf.append("&lt;");
	        else if (c == '>')
	          sbuf.append("&gt;");
	        else if (c == '"')
	          sbuf.append("&quot;");
	        else if (c == '\'')
	            sbuf.append("&#39;");
	        else if (c == '&')
	          sbuf.append("&amp;");
	        else if (c == '\n')
	          sbuf.append("<BR>");
	        else if (c == '\t')
	          sbuf.append("&nbsp;&nbsp;&nbsp;&nbsp;");
	        else
	          sbuf.append(c);
	      }
	    }
	    else if (returnNBSPIfEmpty)
	      sbuf.append("&nbsp;");
	
	    return sbuf.toString();
	  }

	  /**
	   * Converts special HTML characters (such as <,>,",&) into the
	   * in the into HTML encoded representations.
	   * @param value the string to encode
	   * @return HTML encoded version of the string
	   */
	  public static String htmlEncode(String value)
	  {
	    return htmlEncode(value, false);
	  }
	  
	  
}
