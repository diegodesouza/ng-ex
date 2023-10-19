<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page language="java" %>
<%@page import="java.util.*" %>
<%@ page import="com.anthem.mwpmf.PMFUtils" %>
<%@page import="com.anthem.mwpmf.UploadDocListBean" %>
<%@ page import="org.owasp.esapi.ESAPI" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!-- Beginning of section K -->		
<%
int fileListSize =0;
String uploadFileName = "";
String uploadFileSize = "";
String uploadFileComment = "";
String uploadDocComment = "";
if (providerData.getUploadDocComment() != null) {
    uploadDocComment = providerData.getUploadDocComment();
    uploadDocComment = uploadDocComment.trim();
}

List<UploadDocListBean> uploadListBean=null;
	uploadListBean = (List<UploadDocListBean>)providerData.getUploadFileListDtls(); 
		if(uploadListBean!=null )
		{
			fileListSize = uploadListBean.size();
		}
if(uploadListBean!=null )
{
	fileListSize = uploadListBean.size();
}

%>
<script language="javascript">
	 function deleteRow(rowNum) {
        	document.forms[0].formUpdateAction.value='deleteRow';
        	document.forms[0].dltRowCounter.value=rowNum;
        	document.forms[0].enctype= "multipart/form-data";
        	document.forms[0].encoding= "multipart/form-data";
        	document.forms[0].submit();
        }
	 function uploadFile() {
           
           if(document.forms[0].fileContentData.value=='')
           {
           	alert("Section K, Please select a file for uploading.");
			document.forms[0].fileContentData.focus();
           	return;
           }
           	document.forms[0].formUpdateAction.value='uploadFile';
        	document.forms[0].enctype= "multipart/form-data";
        	document.forms[0].encoding= "multipart/form-data";
        	document.forms[0].submit();
        }
	
</script>
<table BORDER="1" CELLSPACING=0 CELLPADDING=7 WIDTH="100%">
<tr>
	<td valign="top" align="center" colspan="2" BGCOLOR="#000080"><font size="2" COLOR="#ffffff"><b>Section K - Attachments</b></font></td>
</tr>
<%	Iterator theDataK = errorMessagesVectorK.iterator();
		if (!errorMessagesVectorK.isEmpty())
	{%>
<tr>
	<td valign="top" align="center" colspan="2" BGCOLOR="#FF0000"><font size="2" COLOR="#ffffff"><b>Error Messages</b></font></td>
</tr>
<tr>
	<td colspan="2" BGCOLOR="#ffffff"><font size="2" COLOR="#000080"><b>The following errors were detected.  Please correct the form entries and resubmit form.</b></font></td>
</tr>
		<%	while(theDataK.hasNext())
			{
			String value = (String)theDataK.next(); %>
<tr>
				<TD colspan="2" style="FONT-SIZE: large;"><font size="2" COLOR="#FF0000"><b><%=value%></b></font></TD>
</tr>
		<%	}  %>
		<%	} %>
<tr>
	<td valign="top" BGCOLOR="#ffffff" width="5%"><font size="2" COLOR="#000080"/><b>1.&nbsp;&nbsp;&nbsp;</b></font></td>
	<td BGCOLOR="#ffffff" width="95%"><font size="2" COLOR="#000080"><b>Please upload any documentation you wish to send to Anthem to support information entered on this form. You will be allowed to attach MS Word, MS Excel, 'jpg', 'pdf', 'gif' or 'txt' file types. There is a combined limit of 10 MB for your attachments.</b></font></td>
</tr>
<tr>
<td colspan="2">
	Attachments
	<table  width="100%" border="1"  bordercolor="" id="fileListTable">
    	<input type="hidden" id="dltRowCounter" name="dltRowCounter">
    	<input type="hidden" id="fileListSize" name="fileListSize" value="<%=fileListSize%>">
           <tr bgcolor="#A0A0A0">
           		 <td >File Name</td>
                 <td >Size</td>
                 <td >Description of Attachment</td>
                 <td >&nbsp;</td>
           </tr>
           <%for(int fileCount=1;fileCount<=fileListSize;fileCount++){
        	   if(fileCount==1)
        	   {
        		   uploadFileName = uploadListBean.get(fileCount-1).getFileName();
            	   uploadFileSize = uploadListBean.get(fileCount-1).getFileSize()+"";
            	   uploadFileComment = uploadListBean.get(fileCount-1).getDocComment(); 
        	   }
        	   else
        	   {
        		   uploadFileName = uploadFileName+"::"+uploadListBean.get(fileCount-1).getFileName();
            	   uploadFileSize = uploadFileSize+ "::"+uploadListBean.get(fileCount-1).getFileSize();
            	   uploadFileComment =uploadFileComment +"::"+uploadListBean.get(fileCount-1).getDocComment();
        	   }
        	   
        	%>
            <tr>
            
           		 <td ><%=ESAPI.encoder().encodeForHTMLAttribute(uploadListBean.get(fileCount-1).getFileName())%></td>
                 <td ><%=ESAPI.encoder().encodeForHTMLAttribute(uploadListBean.get(fileCount-1).getFileSize())%></td>
                 <td ><%if(!uploadListBean.get(fileCount-1).getDocComment().equals("NA")) {%><%=ESAPI.encoder().encodeForHTMLAttribute(uploadListBean.get(fileCount-1).getDocComment())%><%}else{ %><%=" "%><%}%></td>
                 <td ><a href="javascript:deleteRow(<%=fileCount%>);">Remove</a></td>
           </tr>
           <%} %>
           <input type="hidden" id="uploadFileName" name="uploadFileName" value="<%=ESAPI.encoder().encodeForHTMLAttribute(uploadFileName)%>">
            <input type="hidden" id="uploadFileSize" name="uploadFileSize" value="<%=ESAPI.encoder().encodeForHTMLAttribute(uploadFileSize)%>">
            <input type="hidden" id="uploadedFileComment" name="uploadedFileComment" value="<%=ESAPI.encoder().encodeForHTMLAttribute(uploadFileComment)%>">
	</table>
    </td>
</tr>
<tr>
	<td colspan="2">
		<table>
		<tr>
			<td width="20%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Upload File</td>
			<td align = "left"><input type="file" name="fileContentData" id="fileContentData" value="Browse" /></td>
			<td width=1%></td>
		</tr>
		<tr>
			<td width="20%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Description of<br>
			                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Attachment</td>
			<td align ="left"><TextArea NAME="uploadDocComment" id="uploadDocComment"  rows=3 cols=50 onKeyDown="limitTextArea(this.form.comments,200);" onKeyUp="limitTextArea(this.form.comments,200);"><%=ESAPI.encoder().encodeForHTML(uploadDocComment)%></TextArea></td>
			<td width=1%></td>
		</tr>
		<tr>
			<td>
				<table>
				<tr>
					<td width="50%">&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td align = "right"><input type="button" name="" value="Upload File" onClick="uploadFile()"></td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>		
</table>		
</body>
</html>