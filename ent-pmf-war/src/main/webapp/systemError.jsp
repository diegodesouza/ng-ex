<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.wellpoint.ssouserapp.Constants" %>
<%@ page import="com.wellpoint.ssouserapp.util.JspUtil" %>

<%
  String errorCode = (String)request.getAttribute(Constants.ERROR_CODE_ATTRIBUTE);
%>

<TABLE BORDER="0" WIDTH="100%" CELLSPACING="0" CELLPADDING="0">
<TR>
<TD class=BannerTitleText width="100%" height="20%" align="center">
<br><br><br>Error
</TD>
</TR>
<TR>
<TD align="center">	
	<br>A system error has occurred. (<%= JspUtil.htmlEncode(errorCode, true) %>)  If this problem continues, please call 866-755-2680 for assistance.
	<br><br>Monday through Friday
	<br>8:00am-8:00pm Eastern, 7:00am-7:00pm Central, 6:00am-6:00pm Mountain, 5:00am-5:00pm Pacific
</TD>
</TR>
</TABLE>