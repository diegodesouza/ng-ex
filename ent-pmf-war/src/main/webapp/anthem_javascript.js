function terms_of_use() {
  popupWin = window.open('http://www.anthem.com/jsp/antiphona/bcbs/popup.jsp?content_id=PC_GLOB_TUSE', 'pop_up', 'width=530,height=550,left=0,top=0,scrollbars=yes');
  }
function privacy_stmt() {
  popupWin = window.open('http://www.anthem.com/jsp/antiphona/bcbs/popup.jsp?content_id=PC_GLOB_PSTM', 'pop_up', 'width=530,height=550,left=0,top=0,scrollbars=yes');
  }
function about_site() {
  popupWin = window.open('http://www.anthem.com/jsp/antiphona/bcbs/popup.jsp?content_id=PC_GLOB_ASTE', 'pop_up', 'width=530,height=550,left=0,top=0,scrollbars=yes');
  }
function hipaa_info() {
  popupWin = window.open('http://www.anthem.com/jsp/antiphona/bcbs/popup.jsp?content_id=PC_GLOB_HIPA', 'pop_up', 'width=530,height=550,left=0,top=0,scrollbars=yes');
}

function limitTextArea(field, maxlimit) 
{
	if (field.value.length > maxlimit) // if too long...trim it!
		field.value = field.value.substring(0, maxlimit);

}

