(function($){

$.fn.embebido = function(embebidoSettings){

/**
* 	valores de seteo del plugin
*/
	var settings = $.extend({
			copiar:' ',
			cajaTextoACopiar: ' '
	}, embebidoSettings);

// referencia al formulario
	var form= $(this);
	
/**
 * Evento click del boton Copiar
 */
	var botonCopiar = $(this).find(settings.copiar);
	var cajaTexto= $(this).find(settings.cajaTextoACopiar);
	botonCopiar.click(function(){
		cajaTexto.focus();
		cajaTexto.select();
		$.clipboardReady(function(){
			$.clipboard( cajaTexto.val());
			return false; 
	    }); 
		return false;
	});	
		

/**
 * Evento click de la caja de texto embed
 */
	cajaTexto.click(function(){
		cajaTexto.focus();
		cajaTexto.select();
		return false;
	});
	
	
	return $(this);
};


})(jQuery);	