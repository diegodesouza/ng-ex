function terms_of_use() {
  popupWin = window.open('http://www.anthem.com/wps/portal/ahpfooter?content_path=shared/noapplication/f6/s0/t0/pw_ad070873.htm&label=Terms of Use', 'pop_up', 'width=530,height=550,left=0,top=0,scrollbars=yes');
  }
function privacy_stmt() {
  popupWin = window.open('http://www.anthem.com/wps/portal/ahpfooter?content_path=shared/noapplication/f6/s0/t0/pw_ad070870.htm&label=Privacy Statement', 'pop_up', 'width=530,height=550,left=0,top=0,scrollbars=yes');
  }
function about_site() {
  popupWin = window.open('http://www.anthem.com/wps/portal/ahpculdesac?content_path=shared/noapplication/f6/s0/t0/pw_a037974.htm&na=aboutanthem&label=About Us', 'pop_up', 'width=530,height=550,left=0,top=0,scrollbars=yes');
  }
function hipaa_info() {
  popupWin = window.open('http://www.anthem.com/wps/portal/ahpculdesac?content_path=shared/noapplication/f1/s0/t0/pw_ad069722.htm&na=hipaa&label=HIPAA Information', 'pop_up', 'width=530,height=550,left=0,top=0,scrollbars=yes');
}

function limitTextArea(field, maxlimit)
{
	if (field.value.length > maxlimit) // if too long...trim it!
		field.value = field.value.substring(0, maxlimit);

}

