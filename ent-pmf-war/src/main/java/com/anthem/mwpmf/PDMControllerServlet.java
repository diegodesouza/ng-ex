package com.anthem.mwpmf;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.reference.DefaultHTTPUtilities;

import com.anthem.common.PMFResourceUtils;
import com.anthem.enterprise.client.PMFServiceClient;
import com.anthem.enterprise.client.model.PMFPayload;
import com.anthem.exception.PMFException;
import com.anthem.util.Constants;
import com.anthem.util.EmailUtil;
import com.anthem.util.StringUtils;
import com.google.gson.Gson;

/**
 * <p>
 * Title: PDMControllerServlet
 * </p>
 * <p>
 * Description: Controlling servlet for the Provider Maintenance Form processing
 * </p>
 * <p>
 * Copyright: Copyright (c) 2002
 * </p>
 * <p>
 * Company: Anthem, Inc.
 * </p>
 * 
 * @author Vicki Crim
 * @version 1.0
 */

public class PDMControllerServlet extends HttpServlet implements Constants {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final static Logger logger = LogManager.getLogger(PDMControllerServlet.class);
	public static final String MWPMF_PAGE = "mwpmf.jsp";
	public static final String NYPMF_PAGE = "nypmf.jsp";
	public static final String VAPMF_PAGE = "vapmf.jsp";
	public static final String DENTALPMF_PAGE = "dentalpmf.jsp";
	public static final String ERROR_PAGE = "pdmerror.jsp";
	public static final String SESSION_ERROR_PAGE = "pdmsessionerror.jsp";
	public static final String PROVIDER_MAINTENANCE_LANDING_PAGE = "pmfLanding.jsp";
	public static final String CONFIRMATION_PAGE = "Confirmation.jsp";
	public static final String NY_CONFIRMATION_PAGE = "nyconfirmation.jsp";
	public static final String VA_CONFIRMATION_PAGE = "vaconfirmation.jsp";
	public static final String DENTAL_CONFIRMATION_PAGE = "dentalconfirmation.jsp";
	public static final String SUBMIT_ACTION = "1";
	public static final String VA_SUBMIT_ACTION = "vasubmit";
	public static final String NY_SUBMIT_ACTION = "nysubmit";
	public static final String DENTAL_SUBMIT_ACTION = "dentalsubmit";
	public static final String CLEAR_FORM_BUTTON = "Clear Form";
	public static final String EXIT_BUTTON = "Exit";
	public static final String UPLOAD_ACTION = "uploadFile";
	public static final String UPLOAD_NY_ACTION = "uploadNYFile";
	public static final String UPLOAD_VA_ACTION = "uploadVAFile";
	public static final String DELETE_ACTION = "deleteRow";
	public static final String UPLOAD_PATH = "wlp.pmf.upload.path";
	public static final String CAABC_BRAND = "CAABC";
	public static final String COABCBS_BRAND = "COABCBS";
	public static final String CTABCBS_BRAND = "CTABCBS";
	public static final String GAABCBS_BRAND = "GAABCBS";
	public static final String INABCBS_BRAND = "INABCBS";
	public static final String KYABCBS_BRAND = "KYABCBS";
	public static final String MEABCBS_BRAND = "MEABCBS";
	public static final String MOABCBS_BRAND = "MOABCBS";
	public static final String NHABCBS_BRAND = "NHABCBS";
	public static final String NVABCBS_BRAND = "NVABCBS";
	public static final String OHABCBS_BRAND = "OHABCBS";
	public static final String VAABCBS_BRAND = "VAABCBS";
	public static final String WIABCBS_BRAND = "WIABCBS";
	public static final String WVUNC_BRAND = "WVUNC";
	public static final String ENTPMF_PAGE = "entpmf";

	/**
	 * Configures the Error log
	 * 
	 * @param config
	 * @throws ServletException
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		RequestDispatcher dispatcher = null;
		FileOutputStream fileOuputStream = null;
		byte[] contentData = null;
		String formAction = "";
		String submitButton = "";
		Properties webConfigProp = null;
		HttpSession session = request.getSession(true);

		try {
			webConfigProp = PMFResourceUtils.getProperties();

			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			// isMultipart =
			// MultipartFormDataRequest.isMultipartFormData(request);

			// formAction = (String)request.getParameter("formUpdateAction");
			if (isMultipart) {
				String fileName = "";
				InputStream fileContent = null;
				try {
					List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
					logger.info("Items after parse :: " + items);
					for (FileItem item : items) {
						if (item.isFormField()) {
							// Process regular form field (input
							// type="text|radio|checkbox|etc", select, etc).
							logger.info("Item is form Field :: " + item);
							String fieldName = item.getFieldName();
							String fieldValue = item.getString();
							if (fieldName.equals("formUpdateAction")) {
								formAction = fieldValue;
							}
						} else {
							// Process form file field (input type="file").
							logger.info("Item is not form Field :: " + item);
							String fieldName = item.getFieldName();
							if (fieldName.equals("fileContentData")) {
								fileName = FilenameUtils.getName(item.getName());
								fileContent = item.getInputStream();
							}
						}
					}
				} catch (FileUploadException e) {
					logger.error("Exception occured while parsing :: " + e);
					throw new ServletException("Cannot parse multipart request.", e);
				}
				logger.info("formAction :: " + formAction + " fileName :: " + fileName);
				if (formAction.equals(UPLOAD_ACTION) && (fileName != null) && (fileContent != null)) {

					String dirFilename = fileName;
					if (fileName.contains("\\")) {
						fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
					}

					// validate the filename and contents using ESAPI
					List<String> extensions = Arrays.asList(".pdf", ".doc", ".docx", ".xls", ".xlsx", ".txt", ".gif",
							".csv", ".jpg");
					if (!ESAPI.validator().isValidFileName("FileName", fileName, extensions, false)) {
						logger.debug("Invalid file attemped for upload");
						response.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains");
						response.addHeader("X-Frame-Options", "DENY");
						response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
						response.getWriter().write("The filename includes invalid characters or an invalid extension.");
						response.flushBuffer();
					}

					byte[] buffer = IOUtils.toByteArray(fileContent);

					ByteArrayOutputStream objByteArrayOutputStream = new ByteArrayOutputStream();
					;
					try {
						objByteArrayOutputStream.write(buffer);
						contentData = objByteArrayOutputStream.toByteArray();
						objByteArrayOutputStream.close();
						objByteArrayOutputStream.close();
						fileContent.close();
						logger.info("File uploaded successfully to temp location ::");
					} catch (IOException exception) {
						exception.printStackTrace();
						logger.error("Error occurred in temporary file save of upload file : "
								+ ESAPI.validator().getValidFileName("FileName", fileName, extensions, false));
						response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
						response.getWriter().write(
								"An error occurred attempting to upload your file. Please verify your file and try again.");
						response.flushBuffer();
					} finally {
						objByteArrayOutputStream = null;
						buffer = null;
					}

					String root = webConfigProp.getProperty(UPLOAD_PATH);
					logger.info("File would be uploaded to :: " + root);
					File path = new File(root);
					if (!path.exists()) {
						path.mkdirs();
					}
					File uploadFilePath = new File(root,
							"/" + request.getSession().getId() + "_" + FilenameUtils.getName(fileName));
					if (uploadFilePath.getCanonicalPath().startsWith(root)) {
						fileOuputStream = new FileOutputStream(uploadFilePath);
						fileOuputStream.write(contentData);
						fileOuputStream.close();
					} else {
						logger.error("Error occurred in temporary file save of upload file : "
								+ ESAPI.validator().getValidFileName("FileName", fileName, extensions, false));
						response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
						response.getWriter().write(
								"An error occurred attempting to upload your file. Please verify your file and try again.");
						response.flushBuffer();
					}

				} else if (formAction.equals(DELETE_ACTION)) {

				}

			} else if (request.getContentType() != null
					&& request.getContentType().equalsIgnoreCase(Constants.CONTENT_APPLICATION_JSON)) {
				PMFServiceClient pmfServiceClient = new PMFServiceClient();
				BufferedReader br = null;
				PMFPayload pmfPayload = new PMFPayload();
				Gson gson = new Gson();
				try {
					br = new BufferedReader(new InputStreamReader(request.getInputStream()));

					if (br != null) {
						String json = br.readLine();
						pmfPayload = gson.fromJson(json, PMFPayload.class);
					}
				} catch (Exception e) {
					logger.error("IOException: Error loading PMF payload ::" + e);
				} finally {
					if (br != null) {
						br.close();
					}
				}

				if (pmfPayload != null) {
					if (pmfPayload.getServicePayload() != null) {
						if (pmfPayload.getServicePayload().getServiceRequest() != null) {
							NYVAProvMaintFormBean entPmfForm = new NYVAProvMaintFormBean();
							if (pmfPayload.getServicePayload().getServiceRequest().getFormState() != null
									&& !pmfPayload.getServicePayload().getServiceRequest().getFormState().isEmpty()) {
								entPmfForm.setBrand(ESAPI.encoder().encodeForHTMLAttribute(
										pmfPayload.getServicePayload().getServiceRequest().getFormState()));
							}
							PMFDataHelper.setCAPMFFormData(entPmfForm,
									pmfPayload.getServicePayload().getServiceRequest());
							int PGI_ID = updateDatabase(entPmfForm, request.getSession().getId());
							if (PGI_ID != -1) {
								logger.info("Populating DCN :: ");
								PMFDataHelper.setCAPMFFormAttachments(pmfPayload, entPmfForm);

								logger.info("Posting the json :: ");
								String responseFromPLM = pmfServiceClient.postDataToPLM(gson.toJson(pmfPayload),
										PGI_ID);
								if (responseFromPLM != null && responseFromPLM.length() > 0) {
									OutputStream responseOutputStream = response.getOutputStream();
									ByteArrayInputStream bais = new ByteArrayInputStream(responseFromPLM.getBytes());
									IOUtils.copy(bais, responseOutputStream);
									responseOutputStream.flush();
									response.flushBuffer();
									responseOutputStream.close();
									bais.close();
								}
							} else {
								logger.error("Failed in Updating Database :: ");
							}
						}
					}
				} else {
					logger.error("PMF Payload is null :: ");
				}
			} else {
				formAction = (String) request.getParameter("formUpdateAction");
				String brand = ESAPI.validator().getValidInput("brand", (String) request.getParameter("brand"), "Brand",
						20, true);
				if ((brand != null) && (brand.equalsIgnoreCase("NYEBC") || brand.equalsIgnoreCase("NYEBCBS"))) {
					dispatcher = request.getRequestDispatcher(NYPMF_PAGE);
					formAction = "NY";
					if (brand.matches("^[A-z]+$")) {
						session.setAttribute("brand",
								ESAPI.encoder().encodeForHTML(ESAPI.encoder().canonicalize(brand)));
					}

				} else if ((brand != null) && brand.equalsIgnoreCase("VA")) {
					dispatcher = request.getRequestDispatcher(VAPMF_PAGE);
					formAction = "VA";
					if (brand.matches("^[A-z]+$")) {
						session.setAttribute("brand",
								ESAPI.encoder().encodeForHTML(ESAPI.encoder().canonicalize(brand)));
					}
				} else if ((brand != null) && ((brand.equalsIgnoreCase(CADENTAL_BRAND)
						|| brand.equalsIgnoreCase(CADENTAL_GOLDENWEST_BRAND)
						|| brand.equalsIgnoreCase(ABCBSDENTAL_BRAND) || brand.equalsIgnoreCase(BCBSGADENTAL_BRAND)
						|| brand.equalsIgnoreCase(NYEBCDENTAL_BRAND) || brand.equalsIgnoreCase(NYEBCBSDENTAL_BRAND)
						|| brand.equalsIgnoreCase(UNICAREDENTAL_BRAND)))) {
					dispatcher = request.getRequestDispatcher(DENTALPMF_PAGE);
					formAction = "DENTAL";
					if (brand.matches("^[A-z]+$")) {
						session.setAttribute("brand",
								ESAPI.encoder().encodeForHTML(ESAPI.encoder().canonicalize(brand)));
					}
				} else if ((brand != null)
						&& (brand.equalsIgnoreCase(CAABC_BRAND) || brand.equalsIgnoreCase(COABCBS_BRAND)
								|| brand.equalsIgnoreCase(CTABCBS_BRAND) || brand.equalsIgnoreCase(GAABCBS_BRAND)
								|| brand.equalsIgnoreCase(INABCBS_BRAND) || brand.equalsIgnoreCase(KYABCBS_BRAND)
								|| brand.equalsIgnoreCase(MEABCBS_BRAND) || brand.equalsIgnoreCase(MOABCBS_BRAND)
								|| brand.equalsIgnoreCase(NHABCBS_BRAND) || brand.equalsIgnoreCase(NVABCBS_BRAND)
								|| brand.equalsIgnoreCase(OHABCBS_BRAND) || brand.equalsIgnoreCase(VAABCBS_BRAND)
								|| brand.equalsIgnoreCase(WIABCBS_BRAND) || brand.equalsIgnoreCase(WVUNC_BRAND))) {
					logger.info("Redirecting to Enterprise PMF :: ");
					if (brand.matches("^[A-z]+$")) {
						DefaultHTTPUtilities utilities = new DefaultHTTPUtilities();
						utilities.sendRedirect(response,
								ENTPMF_PAGE + "/landingpage?brand=" + ESAPI.encoder().encodeForHTMLAttribute(brand.toLowerCase()));
					}
				} else {
					// invalid or missing brand code
					dispatcher = request.getRequestDispatcher(ERROR_PAGE);
				}

				submitButton = request.getParameter("submitFormBtn");
				if (submitButton == null) {
					// Error page to display
					dispatchRequest(dispatcher, request, response, logger);
				} else {
					if (formAction.equals(VA_SUBMIT_ACTION)) {
						NYVAProvMaintFormBean vaProvMaintForm = new NYVAProvMaintFormBean();
						PMFDataHelper.setVAFormData(vaProvMaintForm, request);

						brand = (String) session.getAttribute("brand");
						if ((brand == null) || brand.equals("")) {
							// no session or not in session so error
							logger.debug("Session expired or no brand in session");
							dispatcher = request.getRequestDispatcher(SESSION_ERROR_PAGE);
							dispatchRequest(dispatcher, request, response, logger);
						} else {
							vaProvMaintForm.setBrand(brand);

							int PGI_ID;
							dispatcher = request.getRequestDispatcher(VA_CONFIRMATION_PAGE);
							PGI_ID = updateDatabase(vaProvMaintForm, request.getSession().getId());
							if (PGI_ID == -1) {
								request.setAttribute("Status", "F");
								dispatchRequest(dispatcher, request, response, logger);
							} else {
								String emailAddress = webConfigProp.getProperty("va.email.address.option1");
								EmailUtil.sendEmail(emailAddress, "SYSTEM@internal.anthem.com",
										"Anthem - VA Provider Demographic Maintenance Form_" + Integer.toString(PGI_ID),
										vaProvMaintForm.getEmailBody());

								request.setAttribute("Status", "S");
								request.setAttribute("pdfform", vaProvMaintForm);
								request.setAttribute("PGIID", String.valueOf(PGI_ID));
								dispatchRequest(dispatcher, request, response, logger);
							}
						}
					}

					else if (formAction.equals(NY_SUBMIT_ACTION)) {
						NYVAProvMaintFormBean nyProvMaintForm = new NYVAProvMaintFormBean();
						PMFDataHelper.setNYFormData(nyProvMaintForm, request);
						brand = (String) session.getAttribute("brand");
						if ((brand == null) || brand.equals("")) {
							// no session or not in session so error
							logger.debug("Session expired or no brand in session");
							dispatcher = request.getRequestDispatcher(SESSION_ERROR_PAGE);
							dispatchRequest(dispatcher, request, response, logger);
						} else {
							nyProvMaintForm.setBrand(brand);

							int PGI_ID;
							dispatcher = request.getRequestDispatcher(NY_CONFIRMATION_PAGE);
							PGI_ID = updateDatabase(nyProvMaintForm, request.getSession().getId());
							if (PGI_ID == -1) {
								request.setAttribute("Status", "F");
								dispatchRequest(dispatcher, request, response, logger);
							} else {
								String emailAddress = "";
								String subject = "";
								if (brand.equalsIgnoreCase("nyebcbs"))
									subject = "Empire BCBS - New York Provider Demographic Maintenance Form_"
											+ Integer.toString(PGI_ID);
								else if (brand.equalsIgnoreCase("nyebc"))
									subject = "Empire BC - New York Provider Demographic Maintenance Form_"
											+ Integer.toString(PGI_ID);
								else
									// problem - no brand
									subject = "New York Provider Demographic Maintenance Form_"
											+ Integer.toString(PGI_ID);

								if (nyProvMaintForm.getToEmail().equalsIgnoreCase("option1")) {
									if (brand.equalsIgnoreCase("nyebcbs"))
										emailAddress = webConfigProp.getProperty("nyebcbs.email.address.option1");
									else if (brand.equalsIgnoreCase("nyebc"))
										emailAddress = webConfigProp.getProperty("nyebc.email.address.option1");
								} else if (nyProvMaintForm.getToEmail().equalsIgnoreCase("option2")) {
									if (brand.equalsIgnoreCase("nyebcbs"))
										emailAddress = webConfigProp.getProperty("nyebcbs.email.address.option2");
									else if (brand.equalsIgnoreCase("nyebc"))
										emailAddress = webConfigProp.getProperty("nyebc.email.address.option2");
								} else if (nyProvMaintForm.getToEmail().equalsIgnoreCase("option3")) {
									if (brand.equalsIgnoreCase("nyebcbs"))
										emailAddress = webConfigProp.getProperty("nyebcbs.email.address.option3");
									else if (brand.equalsIgnoreCase("nyebc"))
										emailAddress = webConfigProp.getProperty("nyebc.email.address.option3");
								} else {
									logger.error("NY Option Email not loaded");
									request.setAttribute("Status", "F");
									dispatchRequest(dispatcher, request, response, logger);
								}

								EmailUtil.sendEmail(emailAddress, "SYSTEM@internal.anthem.com", subject,
										nyProvMaintForm.getEmailBody());

								request.setAttribute("Status", "S");
								request.setAttribute("pdfform", nyProvMaintForm);
								request.setAttribute("PGIID", String.valueOf(PGI_ID));
								dispatchRequest(dispatcher, request, response, logger);
							}
						}
					}

					else if (formAction.equals(DENTAL_SUBMIT_ACTION)) {
						NYVAProvMaintFormBean dentalProvMaintForm = new NYVAProvMaintFormBean();
						PMFDataHelper.setDentalFormData(dentalProvMaintForm, request);

						brand = (String) session.getAttribute("brand");
						if ((brand == null) || brand.equals("")) {
							// no session or not in session so error
							logger.debug("Session expired or no brand in session");
							dispatcher = request.getRequestDispatcher(SESSION_ERROR_PAGE);
							dispatchRequest(dispatcher, request, response, logger);
						} else {
							dentalProvMaintForm.setBrand(brand);

							int PGI_ID;
							dispatcher = request.getRequestDispatcher(DENTAL_CONFIRMATION_PAGE);
							PGI_ID = updateDatabase(dentalProvMaintForm, request.getSession().getId());
							if (PGI_ID == -1) {
								request.setAttribute("Status", "F");
								dispatchRequest(dispatcher, request, response, logger);
							} else {
								// default values for email address and subject
								String emailAddress = webConfigProp.getProperty("abcbsdental.email.address.option1");
								;
								String subject = "Dental Provider Demographic Maintenance Form_";

								if (brand.equalsIgnoreCase(ABCBSDENTAL_BRAND)) {
									emailAddress = webConfigProp.getProperty("abcbsdental.email.address.option1");
									subject = "Anthem BCBS - Dental Provider Demographic Maintenance Form_";
								} else if (brand.equalsIgnoreCase(BCBSGADENTAL_BRAND)) {
									emailAddress = webConfigProp.getProperty("bcbsgadental.email.address.option1");
									subject = "BCBS of Georgia - Dental Provider Demographic Maintenance Form_";
								} else if (brand.equalsIgnoreCase(NYEBCDENTAL_BRAND)) {
									emailAddress = webConfigProp.getProperty("nyebcdental.email.address.option1");
									subject = "Empire BC - Dental Provider Demographic Maintenance Form_";
								} else if (brand.equalsIgnoreCase(NYEBCBSDENTAL_BRAND)) {
									emailAddress = webConfigProp.getProperty("nyebcbsdental.email.address.option1");
									subject = "Empire BCBS - Dental Provider Demographic Maintenance Form_";
								} else if (brand.equalsIgnoreCase(UNICAREDENTAL_BRAND)) {
									emailAddress = webConfigProp.getProperty("unicaredental.email.address.option1");
									subject = "Unicare - Dental Provider Demographic Maintenance Form_";
								} else if (brand.equalsIgnoreCase(CADENTAL_GOLDENWEST_BRAND)) {
									emailAddress = webConfigProp.getProperty("cadentalgolden.email.address.option1");
									subject = "Golden West - CA Dental Provider Demographic Maintenance Form_";
								} else if (brand.equalsIgnoreCase(CADENTAL_BRAND)) {
									emailAddress = webConfigProp.getProperty("cadental.email.address.option1");
									subject = "Anthem - CA Dental Provider Demographic Maintenance Form_";
								}
								EmailUtil.sendEmail(emailAddress, "SYSTEM@internal.anthem.com",
										subject + Integer.toString(PGI_ID), dentalProvMaintForm.getEmailBody());

								request.setAttribute("Status", "S");
								request.setAttribute("pdfform", dentalProvMaintForm);
								request.setAttribute("PGIID", String.valueOf(PGI_ID));
								dispatchRequest(dispatcher, request, response, logger);
							}
						}
					}
				}
			}
		} catch (Exception ex) {
			logger.error("PDMControllerServlet: Error occured for NY/VA/Enterprise PDM Form :: " + ex);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			try {
				response.getWriter().write("An error occurred with your Provider Maintenance Form. Please try again.");
				response.flushBuffer();
			} catch (IOException ioe) {
				logger.error("PDMControllerServlet: Could not send proper error response to the uesr :: " + ioe);
				dispatcher = request.getRequestDispatcher(ERROR_PAGE);
				dispatchRequest(dispatcher, request, response, logger);
			}
		} finally {
			dispatcher = null;
			fileOuputStream = null;
			contentData = null;
			formAction = null;
			submitButton = null;
			webConfigProp = null;
		}
	}

	/**
	 * @param dispatcher
	 * @param request
	 * @param response
	 * @param logger
	 * @throws ServletException
	 **/
	private void dispatchRequest(RequestDispatcher dispatcher, HttpServletRequest request, HttpServletResponse response,
			Logger logger) throws ServletException {
		try {
			dispatcher.forward(request, response);
		} catch (java.io.IOException df) {
			logger.error("Error in PDMControllerServlet: " + df.getMessage());
			logger.error(StringUtils.stack2string(df));
		}
	}

	/**
	 * Delegates the call to update the database
	 * 
	 * @param providerData
	 * @return
	 */
	private synchronized int updateDatabase(NYVAProvMaintFormBean provMaintForm, String sessionID) {
		int seqNo = -1;
		try {
			logger.info("start of db ");
			seqNo = PMFUtils.updateDatabasePDM(provMaintForm, sessionID);
			logger.info("seq no" + seqNo);
		} catch (PMFException pmfe) {
			logger.error("Error Updating database" + pmfe);
		} catch (SQLException excp) {
			logger.error("Error Updating database" + excp);
		}
		return seqNo;
	}

}
