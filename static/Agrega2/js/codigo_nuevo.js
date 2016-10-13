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



function expandirCaja (i) {

if (document.getElementById(i).className=='caja_abierta') {
document.getElementById('p' + i).className = 'caja_cerrada';
document.getElementById('d'+i).innerHTML="Ver M&aacute;s";
document.getElementById(i).className = 'caja_cerrada';
}
        else {
document.getElementById('p' + i).className = 'caja_abierta';
document.getElementById('d'+i).innerHTML="Ver Menos";
document.getElementById(i).className = 'caja_abierta';
}
}


$(function() {
 $('#licencia_a').tipsy({fade: true});
  });
  
  $(function() {
 $('#licencia_b').tipsy({fade: true});
  });
  
 $(function() {
 $('#licencia_c').tipsy({fade: true});
  }); 
  
  
   $(function() {
 $('#cc_by').tipsy({fade: true});
  });
  
    $(function() {
 $('#cc_by_sa').tipsy({fade: true});
  });     
   $(function() {
 $('#cc_pd').tipsy({fade: true});
  }); 
  $(function() {
 $('#cc_by_nd').tipsy({fade: true});
  });     
  $(function() {
 $('#cc_by_nc_sa').tipsy({fade: true});
  });  
  $(function() {
 $('#cc_by_nc_nd').tipsy({fade: true});
  });      
  $(function() {
 $('#cc_by_nc').tipsy({fade: true});
  });     


  
  
  