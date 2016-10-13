$(document).ready(function(){	

	if (!$.browser.opera) {

		// select element styling
		$('select.select').each(function(){
			var title = $(this).attr('title');
			if( $('option:selected', this).val() != ''  ) title = $('option:selected',this).text();
			$(this)
				.css({'z-index':10,'opacity':0,'-khtml-appearance':'none'})
				.after('<span class="select">' + title + '</span>')
				.change(function(){
					val = $('option:selected',this).text();
					$(this).next().text(val);
					})
		});
	};
});


function clearText(thefield){
if (thefield.defaultValue==thefield.value)
thefield.value = ""
} 


function MM_jumpMenu(targ,selObj,restore){ //v3.0
  eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
  if (restore) selObj.selectedIndex=0;}	

function test () {
	//jQuery("input[@type='checkbox'] + label", this).each(function() {
	jQuery("input[type='checkbox'] + label", this).each(function() {
		//$(this)[0].checked = $(this)[0].checked != true;
		$(this).cssCheckboxToggle();
	});
}
 
$(document).ready( function () {
	//for JQuery versions 1.2, 1.1 and 1.0. For more info see http://www.electrictoolbox.com/jquery-form-elements-by-name/
	//$("form[@name='Registration']").cssCheckbox(); 
	
	//for JQuery versions 1.3 and above. For more info see http://www.electrictoolbox.com/jquery-form-elements-by-name/
	//$("form[name='Registration']").cssCheckbox(); 
});
 
// Si descomentamos la linea de abajo se aplicaran los estilos de los checkbos del filtro de busqueda
// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//$('input[type="checkbox"]').ezMark({checkboxCls: 'ez-checkbox', checkedCls: 'ez-checked'});
// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 
//http://www.neuroticweb.com/blog/123/csscheckbox-mejorado-ligeramente/
//http://kawika.org/jquery/cssCheckbox/
//http://www.itsalif.info/content/ezmark-jquery-checkbox-radiobutton-plugin

jQuery.fn.cssCheckboxToggle = function () {
	jQuery(this).toggleClass("checked");
	jQuery(this).prev()[0].checked = !jQuery(this).prev()[0].checked;
	console.log(jQuery(this).val());
}
 
jQuery.fn.cssCheckbox = function () {
 
	//jQuery("input[@type='checkbox'] + label", this)
	jQuery("input[type='checkbox'] + label", this)
		.ezMark({
			//checkboxCls: 'your-default-checkbox-class-name' ,
			checkedCls: 'checked'
		});
/*		.each( function(){
			if ( jQuery(this).prev()[0].checked )
				jQuery(this).addClass("checked");
			})
		.hover( 
			function() { jQuery(this).addClass("over"); },
			function() { jQuery(this).removeClass("over"); }
			)
		.click( function() {
			jQuery(this).cssCheckboxToggle();
				//.toggleClass("checked")
				//.prev()[0].checked = !jQuery(this).prev()[0].checked;
			})
		.prev().hide();
*/
}


function expandirCaja (i) {
	if (document.getElementById(i).className=='caja_abierta') {
		document.getElementById('p' + i).className = 'caja_cerrada';
		document.getElementById('d'+i).innerHTML="Ver M&aacute;s";
		document.getElementById(i).className = 'caja_cerrada';
	} else {
		document.getElementById('p' + i).className = 'caja_abierta';
		document.getElementById('d'+i).innerHTML="Ver Menos";
		document.getElementById(i).className = 'caja_abierta';
	}
}