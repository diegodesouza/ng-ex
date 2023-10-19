package com.anthem.mwpmf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javazoom.upload.MultipartFormDataRequest;
import javazoom.upload.UploadFile;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.owasp.esapi.ESAPI;

import com.anthem.common.PMFResourceUtils;
import com.anthem.docUpload.AttachDocUploadHelper;
import com.anthem.exception.PMFException;
import com.anthem.util.Constants;
import com.anthem.util.StringUtils;

/**
 * <p>Title: PMFControllerServlet</p>
 * <p>Description: Controlling servlet for the Provider Maintenance Form processing</p>
 * <p>Copyright: Copyright (c) 2002</p> 
 * <p>Company: Anthem, Inc.</p>
 * @author Vicki Crim
 * @version 1.0
 */

public class PMFControllerServlet extends HttpServlet implements Constants
{
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final static Logger logger = LogManager.getLogger(PMFControllerServlet.class);
    public static final String PROVIDER_MAINTENANCE_PAGE = "mwpmf.jsp";
    public static final String PROVIDER_MAINTENANCE_LANDING_PAGE = "pmfLanding.jsp";
    public static final String CONFIRMATION_PAGE = "Confirmation.jsp";
    public static final String SUBMIT_ACTION = "1";
    public static final String CLEAR_FORM_BUTTON = "Clear Form";
    public static final String EXIT_BUTTON = "Exit";
    public static final String UPLOAD_ACTION = "uploadFile";
    public static final String DELETE_ACTION = "deleteRow";
    public static final String UPLOAD_PATH = "wlp.pmf.upload.path";
    
    //private static final Log logger = LogFactory.getLog(PMFControllerServlet.class);

	/**
	 * Configures the Error log
	 * @param config
	 * @throws ServletException
	 */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException
    {
        doPost(request,response);
    }

	public void doPost (HttpServletRequest request, HttpServletResponse response)  throws ServletException
	{
	    Provider providerData = null;
	    List<UploadDocListBean> fileList = null;
	    UploadDocListBean uploadListBean = null;
	    RequestDispatcher dispatcher = null;
	    RequestDispatcher landingDispatcher = null;
	    MultipartFormDataRequest multipartRequest = null;
	    FileOutputStream fileOuputStream =null;
	    byte[] objContentData =null;
	    byte[] contentData =null;
		UploadFile file = null;
		Hashtable files=null;
	    String formAction = "";
	    String submitButton = "";
	    String hipShowFlag = "y";
	    int dltCounter= 0;
	    String[] uploadFileComment =null;
	    String[] uploadFileNames =null;
	    String[] uploadFileSize =null;
	    double maxFileSize= 0;
	    String docComment ="";
	    Properties webConfigProp = null;
	    File uploadedFile = null;
	    List<String> extensions = Arrays.asList(".pdf", ".doc", ".docx", ".xls", ".xlsx", ".txt", ".gif",
				".csv", ".jpg");
	    try{
	    	webConfigProp = PMFResourceUtils.getProperties();
	    	boolean isMultipart = MultipartFormDataRequest.isMultipartFormData(request);
	    	 fileList = new ArrayList<UploadDocListBean>();
		        if (isMultipart) 
		        {
		        	if(logger.isDebugEnabled())
		        	{
		        		logger.debug("Inside upload doc method.");
		        	}
		        	providerData = new Provider();
		        	multipartRequest=new MultipartFormDataRequest(request);			
						formAction = multipartRequest.getParameter("formUpdateAction");
						
						/*   WLPRD00779021 - April 2014  */
						if(multipartRequest.getParameter("hipShow") != null)
						{
							hipShowFlag = 
									ESAPI.validator().getValidInput("hipShow", multipartRequest.getParameter("hipShow"), "YN", 1, false);
						}
						
						dispatcher = request.getRequestDispatcher(PROVIDER_MAINTENANCE_PAGE);
						landingDispatcher = request.getRequestDispatcher(PROVIDER_MAINTENANCE_LANDING_PAGE);
						PMFDataHelper.setProviderDataForMultiPart(providerData,  multipartRequest);
			            if(formAction.equals(UPLOAD_ACTION))
						    {
					       		   if(multipartRequest.getParameter("uploadFileName")!=null)
					       		   {
					       			   uploadFileNames = multipartRequest.getParameter("uploadFileName").split("::");
					       			   uploadFileSize = multipartRequest.getParameter("uploadFileSize").split("::");
					        		   if(multipartRequest.getParameter("uploadedFileComment")!=null)
					        		   {
					        			   uploadFileComment = multipartRequest.getParameter("uploadedFileComment").split("::");
					        		   }
					        		   for(int fileCount=0;fileCount<uploadFileNames.length;fileCount++)
					        		   {
					        			   if(!uploadFileNames[fileCount].equals(""))
					        			   {
						        			   uploadListBean =  new UploadDocListBean();
						    			  	   uploadListBean.setFileName(
						    			  			 ESAPI.validator().getValidFileName("FileName", uploadFileNames[fileCount], extensions, false));
						    			  	   maxFileSize=maxFileSize+Double.parseDouble(uploadFileSize[fileCount]);
						    				   uploadListBean.setFileSize(
						    						   ESAPI.validator().getValidInput("FileSize", uploadFileSize[fileCount],  "FileSize", 11, true));
						    				   uploadListBean.setDocComment(
						    						   ESAPI.validator().getValidInput("FileComment", uploadFileComment[fileCount],  "FileComment", 200, true)); 
						    				   fileList.add(uploadListBean);    
					        			   }
					        		   }
				        		   }
				        		   uploadListBean =  new UploadDocListBean();
				        		   files = multipartRequest.getFiles();
						                  
				        		   if ( (files != null) && (!files.isEmpty()) )
						         	{
				        			   file = (UploadFile) files.get("fileContentData");
				        			   
				        			   String fileName = file.getFileName();
				        			   if(logger.isInfoEnabled())
				        			   {
				        				   logger.debug("The selected file name "+fileName);
				        			   }
				        			   if(PMFUtils.isValidFile(providerData,fileName,file.getFileSize(),file.getData(),maxFileSize))
				        			   {
					                       if(fileName.contains("\\"))
					                       {
					                    	   fileName = fileName.substring(fileName.lastIndexOf("\\")+1) ;
					                       }
							         		objContentData = file.getData();
							         		ByteArrayOutputStream objByteArrayOutputStream = null;
							        	    byte[] buffer = null;
								        	    if(objContentData != null) 
								        	    {	    
									        	    	try 
									        	    	{
									        	    		objByteArrayOutputStream = new ByteArrayOutputStream();
									        	    		buffer = objContentData;
									        	    		contentData = null;
									        	   		 	objByteArrayOutputStream.write(buffer);
									        	   		 	contentData = objByteArrayOutputStream.toByteArray();
									        	   		 	objByteArrayOutputStream.close();		        	   		 	   
									        	   		} 
									        	    	catch (IOException exception) 
									        	    	{
									        	    		exception.printStackTrace();
									        	   		}
									        	    	finally
									        	    	{
									        	    		objByteArrayOutputStream =null;
									        	    		buffer =null;
									        	    	}
								        	    }
						                       uploadListBean.setFileName(fileName);
						                       uploadListBean.setFileSize(String.format( "%.2f", ((double)(file.getFileSize()/1024)/1024)));
						                       docComment = ESAPI.validator().getValidInput("FileComment", docComment, "FileComment", 200, true);
						                       if(docComment!=null && !docComment.trim().equals(""))
						                       {
						                    	   uploadListBean.setDocComment(docComment);
						                       }
						                       else
						                       {
						                    	   uploadListBean.setDocComment("NA");
						                       }
						                       String root = webConfigProp.getProperty(UPLOAD_PATH);//getServletConfig().getInitParameter("docUploadDev")+"/";
						                       File path = new File(root);
						                       if (!path.exists()) {
						                           boolean status = path.mkdirs();
						                       }
						                       String fileNameSanitized = new File(path + "/" + request.getSession().getId()+"_"+fileName).getCanonicalPath();
						                       fileOuputStream  = new FileOutputStream(fileNameSanitized);
								        	   fileOuputStream.write(contentData);
								        	   fileOuputStream.close();
						                       fileList.add(uploadListBean);
						                       providerData.setUploadDocComment("");
				        			   }
					                       providerData.setUploadFileListDtls(fileList);
						            }
						    }
						    else if(formAction.equals(DELETE_ACTION))
						    {
						    	 if(providerData.getDltRowCounter()!=null)
							   {
								   dltCounter = Integer.parseInt(providerData.getDltRowCounter());
							   }
						    	 uploadFileNames = multipartRequest.getParameter("uploadFileName").split("::");
				        		 uploadFileSize = multipartRequest.getParameter("uploadFileSize").split("::");
				        		 if(multipartRequest.getParameter("uploadedFileComment")!=null)
				        		   {
				        			   uploadFileComment = multipartRequest.getParameter("uploadedFileComment").split("::");
				        		   }
						   for(int fileCount=1;fileCount<=uploadFileNames.length;fileCount++)
						   {
							   if((!uploadFileNames[fileCount-1].equals(""))&&(fileCount!=dltCounter))
							   {
									   uploadListBean =  new UploadDocListBean();
								  	   uploadListBean.setFileName(
								  			 ESAPI.validator().getValidFileName("FileName", uploadFileNames[fileCount-1], extensions, false)); 
								  	   uploadListBean.setFileSize(
								  		   ESAPI.validator().getValidInput("FileSize", uploadFileSize[fileCount-1],  "FileSize", 11, true));
								       uploadListBean.setDocComment(
								  		   ESAPI.validator().getValidInput("FileComment", uploadFileComment[fileCount-1],  "FileComment", 200, true)); 
									   fileList.add(uploadListBean);    
							   }
							   else
							   {
								   String filePath = webConfigProp.getProperty(Constants.UPLOAD_PATH);
								   String uploadFileNameSanitized = new File(filePath + "/" + request.getSession().getId()+"_"+uploadFileNames[fileCount-1]).getCanonicalPath();
								   uploadedFile = new File(uploadFileNameSanitized);
								   AttachDocUploadHelper.deleteTempDocs(uploadedFile);
							   }
						   }
						     providerData.setUploadFileListDtls(fileList);
						}
			            request.setAttribute("fileList", fileList);
			            providerData.setUploadFileStat("Y");
			            dispatcher = request.getRequestDispatcher(PROVIDER_MAINTENANCE_PAGE);
			            request.setAttribute("hipShow", hipShowFlag); /*   WLPRD00779021 - April 2014 */
					    request.setAttribute("Provider", providerData);
					    dispatchRequest(dispatcher,request, response, logger);
		        }
		        else
		        {
		        	providerData = new Provider();
		            formAction = (String)request.getParameter("formUpdateAction");
		            dispatcher = request.getRequestDispatcher(PROVIDER_MAINTENANCE_PAGE);
		            landingDispatcher = request.getRequestDispatcher(PROVIDER_MAINTENANCE_LANDING_PAGE);
		            submitButton = request.getParameter("submitFormBtn");
		            if (submitButton == null )
		            {
		                if (formAction == null)
		                {
		                	if (request.getParameter("isSubmitted") != null) {
		                		String hipShow = ESAPI.validator().getValidInput("hipShow", request.getParameter("hipShow"), "YN", 1, true);
		                		if (hipShow != null && (hipShow.equalsIgnoreCase("n") || hipShow.equalsIgnoreCase("y"))) {
		                			request.setAttribute("hipShow", hipShow);
		                			dispatchRequest(dispatcher,request, response, logger);
		                		}
		                	}
		                	dispatchRequest(landingDispatcher,request, response, logger);
		                	
		                }
		            }
		            else
		            {	           
			            if (submitButton.equals(CLEAR_FORM_BUTTON) || submitButton.equals(EXIT_BUTTON)|| (formAction == null))
		                {
			            	uploadFileNames = request.getParameter("uploadFileName").split("::");
			        		 uploadFileSize = request.getParameter("uploadFileSize").split("::");
			        		 if(request.getParameter("uploadedFileComment")!=null)
			        		   {
			        			   uploadFileComment = request.getParameter("uploadedFileComment").split("::");
			        		   }
						   for(int fileCount=1;fileCount<=uploadFileNames.length;fileCount++)
						   {
							   /* Veracode Scan Fixes - 2014 */
							   if((!uploadFileNames[fileCount-1].equals("")) && 
									   !(PMFUtils.containsBadCharacter(uploadFileNames[fileCount-1],4)))
							   {  
								   String filePath = webConfigProp.getProperty(Constants.UPLOAD_PATH);
								   String uploadFileNameSanitized = new File(filePath + "/" + request.getSession().getId()+"_"+uploadFileNames[fileCount-1]).getCanonicalPath();
								   uploadedFile = new File(uploadFileNameSanitized);
								   AttachDocUploadHelper.deleteTempDocs(uploadedFile);
							   }
						   }
						   request.getSession().invalidate(); /* Veracode Scan Fixes - 2014 (Session Fixation) */
		                     dispatchRequest(dispatcher,request, response, logger);
		                }
		               else if (formAction.equals(SUBMIT_ACTION))
		               {
		            	   PMFDataHelper.setProviderData(providerData,  request);                    
		                    boolean isValidData = PMFUtils.editData(providerData);
		                    int PGI_ID ;
		                    if (isValidData)
		                    {
			                        dispatcher = request.getRequestDispatcher(CONFIRMATION_PAGE);
			                        PGI_ID = updateDatabase(providerData,request.getSession().getId());
			                        if (PGI_ID == -1)
			                        {
			                            request.setAttribute("Status","F");
			                            dispatchRequest(dispatcher,request, response, logger);
			                        }
			                        else
			                        {
			                            request.setAttribute("Status","S");
			                            request.setAttribute("providerName", providerData.getProvFnm());
			                            request.setAttribute("middleName", providerData.getProvMI());
			                            request.setAttribute("lastName", providerData.getProvLnm());
			                            request.setAttribute("title", providerData.getTitle());
			                            request.setAttribute("groupPracticeName", providerData.getPracName1());
			                            request.setAttribute("PGIID", PGI_ID);
			                            dispatchRequest(dispatcher,request, response, logger);
			                        }
		                    }
		                    else
		                    {
		                        dispatcher = request.getRequestDispatcher(PROVIDER_MAINTENANCE_PAGE);
		                        request.setAttribute("Error", "Y");
		                        request.setAttribute("Provider", providerData);
		                        dispatchRequest(dispatcher,request, response, logger);
		                    }
		                }
		            }  
		        }
	        }
	        catch(Exception ex)
	        {
	        	ex.printStackTrace();
	        }
	        finally
	        {
	        	providerData = null;
	     	    fileList = null;
	     	    uploadListBean = null;
	     	    dispatcher = null;
	     	    landingDispatcher = null;
	     	    multipartRequest = null;
	     	    fileOuputStream =null;
	     	    objContentData =null;
	     	    contentData =null;
	     		file = null;
	     		files=null;
	     	    formAction = null;
	     	    submitButton = null;
	     	    uploadFileComment =null;
	     	    uploadFileNames =null;
	     	    uploadFileSize =null;
	     	    webConfigProp = null;
	        }
	}
	
		/**
		 * Delegates the call to configure the error log
		 * @return
		 */
	    /*
	    private Logger configureErrorLog()
	    {
	        Logger logger = ConfigManager.getInstance().getErrorLoggerInstance();
	        return logger;
	    }
	    */
		/**
		 * @param dispatcher
		 * @param request
		 * @param response
		 * @param logger
		 * @throws ServletException
		 **/
	    private void dispatchRequest(RequestDispatcher dispatcher, HttpServletRequest request, HttpServletResponse response, Logger logger) throws ServletException
	    {
	        try
	        {
	        	if(!response.isCommitted()) {
	                dispatcher.forward(request, response);
	        	}
	        }
	        catch(java.io.IOException df)
	        {
	            logger.error("Error in PMFControllerServlet: " + df.getMessage());
	            logger.error(StringUtils.stack2string(df));
	        }
	    }
	
		/**
		 * Delegates the call to update the database
		 * @param providerData
		 * @return
		 */
		private synchronized int updateDatabase(Provider providerData,String sessionID)
		{
			boolean isUpdateSuccess = true;
			int seqNo = -1;
			try
			{
				logger.info("start of db ");
				 seqNo = PMFUtils.updateDatabaseWithFile(providerData,sessionID);
				 System.out.println("seqno");
				 logger.info("seq no"+seqNo);
			}
			catch (PMFException pmfe) {
				logger.error("Error Updating database" + pmfe);
				isUpdateSuccess = false;
			}
			catch (SQLException excp)
			{
				logger.error("Error Updating database" + excp);
				isUpdateSuccess = false;
			}
			return seqNo;
		}
	}
