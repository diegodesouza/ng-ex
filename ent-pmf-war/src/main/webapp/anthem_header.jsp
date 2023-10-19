<!--Anthem Logo and Branding -->
<%@ page import="java.util.Date, java.text.*"  %>

<%!
	String getDate()
	{
		java.text.SimpleDateFormat dateFormatter = new SimpleDateFormat("MMMM dd, yyyy");
		java.util.Date date = new java.util.Date();
		return dateFormatter.format(date);
    }
 %>
<TABLE CELLSPACING="0" CELLPADDING="0" WIDTH="100%" BORDER="0">
  <TR>
  	<TD colspan="5"><IMG SRC="images/dotclear.gif" width="1" height="15" alt=""/></TD>
  </TR>
  <TR>
    <TD><IMG SRC="images/dotclear.gif" width="10" height="1" alt=""/></TD>
    <TD><A HREF="http://www.anthem.com"><IMG SRC="images/AnthemLogo.jpg" width="194" height="37" border="0" alt="Anthem Logo"></a></TD>
    <TD><IMG SRC="images/dotclear.gif" WIDTH="15" HEIGHT="1" ALT=""></TD>
    <TD nowrap><span class=Orange12>Provider Maintenance Form</span></TD>
    <TD> <%= getDate() %> </TD>
  </TR>
  <TR>
  	<TD colspan="5"><IMG SRC="images/dotclear.gif" width="1" height="5" alt=""/></TD>
  </TR>
</TABLE>

<BR>
