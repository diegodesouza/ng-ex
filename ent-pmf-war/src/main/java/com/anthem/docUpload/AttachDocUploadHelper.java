package com.anthem.docUpload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.activation.MimetypesFileTypeMap;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.owasp.esapi.ESAPI;

import com.anthem.common.PMFResourceUtils;
import com.anthem.enterprise.client.PMFServiceClient;
import com.anthem.enterprise.client.model.ContentMetadata;
import com.anthem.enterprise.client.model.UploadDocRequest;
import com.anthem.exception.PMFException;
import com.anthem.mwpmf.NYVAProvMaintFormBean;
import com.anthem.mwpmf.PMFUtils;
import com.anthem.mwpmf.Provider;
import com.anthem.mwpmf.UploadDocListBean;
import com.anthem.util.Constants;

public class AttachDocUploadHelper {
	private static final Logger logger = LogManager.getLogger(PMFServiceClient.class);

	public static String uploadDocuments(Provider providerData, String sessionID, Connection con, int seqNo,
			Logger logger) throws PMFException {
		String[] uploadFileNames = null;
		String[] uploadFileSizes = null;
		String[] uploadFileComments = null;
		File uploadedFile = null;
		String filePath = "";
		FileInputStream uploadedFileStream = null;
		// FileOutputStream uploadedFileStream =null;
		byte fileContent[] = null;
		String dcnVal = null;
		Properties webConfigProp = null;

		try {
			webConfigProp = PMFResourceUtils.getProperties();
			providerData.getUploadFileName();
			if (providerData.getUploadFileName() != null) {
				uploadFileNames = providerData.getUploadFileName().split("::");
				uploadFileSizes = providerData.getUploadFileSize().split("::");
				uploadFileComments = providerData.getUploadedFileComment().split("::");
				for (int fileCount = 0; fileCount < uploadFileNames.length; fileCount++) {
					if (!uploadFileNames[fileCount].equals("")
							&& !(PMFUtils.containsBadCharacter(uploadFileNames[fileCount], 4))) {
						filePath = webConfigProp.getProperty(Constants.UPLOAD_PATH);
						uploadedFile = new File(filePath + "/" + sessionID + "_" + uploadFileNames[fileCount]);
						uploadedFileStream = new FileInputStream(uploadedFile);
						String mimeType = URLConnection.guessContentTypeFromStream(uploadedFileStream);
						if (mimeType == null || mimeType.equals(""))
							mimeType = URLConnection.guessContentTypeFromName(uploadFileNames[fileCount]);
						if (mimeType == null || mimeType.equals(""))
							mimeType = MimetypesFileTypeMap.getDefaultFileTypeMap()
									.getContentType(uploadFileNames[fileCount]);
						logger.info("Mime-type: " + mimeType + "  Filename: " + uploadFileNames[fileCount]);
						fileContent = new byte[(int) uploadedFile.length()];
						uploadedFileStream.read(fileContent);
						dcnVal = uploadDocsRestFul(uploadFileNames[fileCount], mimeType, uploadedFile, logger);

						System.out.println("DCN VAL= " + dcnVal);
						if (dcnVal != null) {
							PMFUtils.insertAttachmentsDetails(con, uploadFileNames[fileCount],
									uploadFileSizes[fileCount], uploadFileComments[fileCount], seqNo, dcnVal);
							deleteTempDocs(uploadedFile);
						}
					}
				}
			}
		} catch (SQLException sqle) {
			logger.error(sqle.getStackTrace());
			throw new PMFException("SQLException has occurred saving attachment details to the database", sqle);
		} catch (PMFException pmfe) {
			throw pmfe;
		} catch (Exception exception) {
			logger.error(exception.getStackTrace());
			throw new PMFException("An exception occurred uploading a document", exception);
		} finally {
			uploadedFile = null;
			fileContent = null;
			filePath = null;
			uploadFileNames = null;
			uploadFileSizes = null;
			uploadFileComments = null;
		}

		return dcnVal;
	}

	public static List<String> uploadDocuments(NYVAProvMaintFormBean provMaintForm, String sessionID, Connection con,
			int seqNo, Logger logger) throws PMFException {
		File uploadedFile = null;
		String filePath = "";
		FileInputStream uploadedFileStream = null;
		// FileOutputStream uploadedFileStream =null;
		byte fileContent[] = null;
		String dcnVal = null;
		List<String> dcnLst = new ArrayList<String>();
		List<String> extensions = Arrays.asList(".pdf", ".doc", ".docx", ".xls", ".xlsx", ".txt", ".gif", ".csv",
				".jpg");
		Properties webConfigProp = null;
		logger.debug("Inside AttachDocUploadHelper");
		try {
			webConfigProp = PMFResourceUtils.getProperties();
			Iterator itFiles = provMaintForm.getUploadFileListDtls().iterator();
			while (itFiles.hasNext()) {
				UploadDocListBean doc = (UploadDocListBean) itFiles.next();
				filePath = webConfigProp.getProperty(Constants.UPLOAD_PATH);
				logger.debug("filePath :: " + filePath);
				logger.info("filePath :: " + filePath);
				uploadedFile = new File(filePath + "/" + sessionID + "_"
						+ ESAPI.validator().getValidFileName("FileName", doc.getFileName(), extensions, false));
				uploadedFileStream = new FileInputStream(uploadedFile);
				String mimeType = URLConnection.guessContentTypeFromStream(uploadedFileStream);
				if (mimeType == null || mimeType.equals(""))
					mimeType = URLConnection.guessContentTypeFromName(doc.getFileName());
				if (mimeType == null || mimeType.equals(""))
					mimeType = MimetypesFileTypeMap.getDefaultFileTypeMap().getContentType(doc.getFileName());
				logger.info("Mime-type: " + mimeType + "  Filename: "
						+ ESAPI.validator().getValidFileName("FileName", doc.getFileName(), extensions, false));
				fileContent = new byte[(int) uploadedFile.length()];
				uploadedFileStream.read(fileContent);
				logger.debug("Before Upload ::");
				dcnVal = uploadDocsRestFul(doc.getFileName(), mimeType, uploadedFile, logger);
				logger.debug("DCN VAL= " + dcnVal);
				if (dcnVal != null) {
					PMFUtils.insertAttachmentsDetails(con, doc.getFileName(), doc.getFileSize(), doc.getDocComment(),
							seqNo, dcnVal);
					deleteTempDocs(uploadedFile);
					dcnLst.add(dcnVal);
					doc.setDcn(dcnVal);
				}
			}

		} catch (SQLException sqle) {
			logger.error(sqle.getStackTrace());
			throw new PMFException("SQLException has occurred saving attachment details to the database", sqle);
		} catch (PMFException pmfe) {
			throw pmfe;
		} catch (Exception exception) {
			logger.error(exception.getStackTrace());
			throw new PMFException("An exception occurred uploading a document", exception);
		} finally {
			uploadedFile = null;
			fileContent = null;
			filePath = null;
		}

		return dcnLst;
	}

	public static String uploadDocsRestFul(String fileName, String mimeType, File file, Logger logger)
			throws PMFException {
		String dcnVal = "";
		try {
			
			byte[] encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(file));	
			UploadDocRequest uploadDoc = new UploadDocRequest();
			uploadDoc.setContentData(new String(encoded));
			List<ContentMetadata> contentMetaData = new ArrayList<ContentMetadata>();
			ContentMetadata metaData = new ContentMetadata();
			metaData.setName("FileName");
			metaData.setValue(fileName);
			contentMetaData.add(metaData);
			metaData = new ContentMetadata();
			metaData.setName("MimeType");
			metaData.setValue(mimeType);
			contentMetaData.add(metaData);
			uploadDoc.setContentMetadata(contentMetaData);
			PMFServiceClient pmfServiceClient = new PMFServiceClient();
			dcnVal = pmfServiceClient.postDoc(uploadDoc);
			logger.info("DCN Value:" + dcnVal);
		} catch (Exception exception) {
			logger.error(exception.getStackTrace());
			throw new PMFException("An exception occurred uploading a document", exception);
		}

		return dcnVal;
	}

	public static void deleteTempDocs(File uploadedFile) throws IOException {
		if (uploadedFile != null && uploadedFile.exists()) {
			uploadedFile.delete();
		}
	}

}
