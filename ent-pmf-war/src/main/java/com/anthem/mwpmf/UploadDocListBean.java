package com.anthem.mwpmf;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

import javazoom.upload.UploadBean;
import javazoom.upload.UploadFile;


public class UploadDocListBean implements Serializable{
	
	private List fileList = null;
	private String fileName = "";
	private String docComment = "";
	private FileItem document;
	private String fileListSize =null;
	private String dltRowCounter =null;
	private String uploadFileName1 = "";
	private String uploadFileSize1 = "";
	private String uploadFileComment1 = "";
	private String uploadFileName2 = "";
	private String uploadFileSize2 = "";
	private String uploadFileComment2 = "";
	private String uploadFileName3 = "";
	private String uploadFileSize3 = "";
	private String uploadFileComment3 = "";
	private String uploadFileName = "";
	private String uploadFileSize = "";
	private String uploadedFileComment = "";
	private String uploadDocComment = "";
	private String fileUpdateAction="";
	private String fileSize = "";
	private String dcn = "";

	

	
	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileUpdateAction() {
		return fileUpdateAction;
	}

	public void setFileUpdateAction(String fileUpdateAction) {
		this.fileUpdateAction = fileUpdateAction;
	}

	public String getUploadedFileComment() {
		return uploadedFileComment;
	}

	public void setUploadedFileComment(String uploadedFileComment) {
		if(uploadedFileComment==null)
		{
			this.uploadedFileComment = "";
		}
		else
		{
			this.uploadedFileComment = uploadedFileComment;
		}
		
	}


	public String getUploadDocComment() {
		return uploadDocComment;
	}

	public void setUploadDocComment(String uploadDocComment) {
		this.uploadDocComment = uploadDocComment;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadFileSize() {
		return uploadFileSize;
	}

	public void setUploadFileSize(String uploadFileSize) {
		this.uploadFileSize = uploadFileSize;
	}

	public String getUploadFileName1() {
		return uploadFileName1;
	}

	public void setUploadFileName1(String uploadFileName1) {
		this.uploadFileName1 = uploadFileName1;
	}

	public String getUploadFileSize1() {
		return uploadFileSize1;
	}

	public void setUploadFileSize1(String uploadFileSize1) {
		this.uploadFileSize1 = uploadFileSize1;
	}

	public String getUploadFileComment1() {
		return uploadFileComment1;
	}

	public void setUploadFileComment1(String uploadFileComment1) {
		this.uploadFileComment1 = uploadFileComment1;
	}

	public String getUploadFileName2() {
		return uploadFileName2;
	}

	public void setUploadFileName2(String uploadFileName2) {
		this.uploadFileName2 = uploadFileName2;
	}

	public String getUploadFileSize2() {
		return uploadFileSize2;
	}

	public void setUploadFileSize2(String uploadFileSize2) {
		this.uploadFileSize2 = uploadFileSize2;
	}

	public String getUploadFileComment2() {
		return uploadFileComment2;
	}

	public void setUploadFileComment2(String uploadFileComment2) {
		this.uploadFileComment2 = uploadFileComment2;
	}

	public String getUploadFileName3() {
		return uploadFileName3;
	}

	public void setUploadFileName3(String uploadFileName3) {
		this.uploadFileName3 = uploadFileName3;
	}

	public String getUploadFileSize3() {
		return uploadFileSize3;
	}

	public void setUploadFileSize3(String uploadFileSize3) {
		this.uploadFileSize3 = uploadFileSize3;
	}

	public String getUploadFileComment3() {
		return uploadFileComment3;
	}

	public void setUploadFileComment3(String uploadFileComment3) {
		this.uploadFileComment3 = uploadFileComment3;
	}

	public String getFileListSize() {
		return fileListSize;
	}

	public void setFileListSize(String fileListSize) {
		this.fileListSize = fileListSize;
	}

	public String getDltRowCounter() {
		return dltRowCounter;
	}

	public void setDltRowCounter(String dltRowCounter) {
		this.dltRowCounter = dltRowCounter;
	}

	public FileItem getDocument() {
		return document;
	}

	public void setDocument(FileItem document) {
		this.document = document;
	}

	public String getDocComment() {
		return docComment;
	}

	public void setDocComment(String docComment) {
		if(docComment==null)
		{
			this.docComment = "";
		}
		else{
			this.docComment = docComment;
		}
		
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List getFileList() {
		return fileList;
	}

	public void setFileList(List fileList) {
		this.fileList = fileList;
	}

	/**
	 * @return the dcn
	 */
	public String getDcn() {
		return dcn;
	}

	/**
	 * @param dcn the dcn to set
	 */
	public void setDcn(String dcn) {
		this.dcn = dcn;
	}
	
}
