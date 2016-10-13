/**
 * 
 * @autor Pablo Lasso
 */


(function($){

$.fn.desplegable = function(desplegablesSettings, desplegablesValidation , desplegablesForm){

/*
* 	valores de seteo del plugin
*/
	var settings = $.extend({
			validationEnabled : false,
			formPluginEnabled: false,
			replegar: ' ',
			desplegar: ' ',
			nombrePanelDesplegable: ' ',
			altoPopup: ' ',
			aceptar: ' ',
			volver:' ',
			copiar:' ',
			cajaTextoACopiar: ' '
	}, desplegablesSettings);

// referencia al formulario
	var form= $(this);
// referencia al panel que va a ser desplegado
	var desplegarPanel = $(settings.nombrePanelDesplegable)[0];
// variable que permite controlar que se lanze solo una vez el evento aceptar
// para enviar el formulario
	var enviado= false;

/**
 * 	valores por defecto de la validacion del formulario 
 */
	var defaultValidationSettings = $.extend({
		separadorMails: ',',
		idEmails:'',
		idNombres:'',
		separadorTags: ' ',
		maxLengthTags: '50',
		showErrors: function(errorMap, errorList) {
			var err = this.numberOfInvalids();
			if(err)
			{
				
				var errorMensajes ="";
				for(var i=0; i<errorList.length && i<3 ; i++)
				{
					errorMensajes = errorMensajes + "<li>"+ errorList[i].message + "</li>";
				}
				
				form.find('#panel_interno').hide();
				form.find('#panel_exito').hide();

				form.find('#ContenedorErrores').html(errorMensajes);
				form.find('#ContenedorErrores').show();
				form.find('#panel_error').show();
				return false;
			}else
			{
				form.find('#ContenedorErrores').hide();
			}
		}
	}, desplegablesValidation);
	
	if(settings.validationEnabled && jQuery().validate  == undefined){
		settings.validationEnabled = false;
		alert("the validation plugin needs to be included");
	}else if(settings.validationEnabled){
		form.validate(defaultValidationSettings);
	}
	
/**
 * 	valores por defecto del plugin form para el envío de datos por medio de 
 * 	ajax.
 */
	var defaultFormSettings=$.extend({
		success: function(data){
				mostrarExito();
				return false;
			},
		error: function(data){
				mostrarErrorServidor();
				return false;
		},
		mensajeError: 'Hubo un error en el envío de los datos al servidor.'
	},desplegablesForm);
	
	function mostrarErrorServidor()
	{
		form.find('#panel_interno').hide();
		form.find('#panel_exito').hide();
		form.find('#ContenedorErrores').html("<li>" + defaultFormSettings.mensajeError +"</li>");
		form.find('#ContenedorErrores').show();
		form.find('#panel_error').show();
		return false;		
	}

	function mostrarExito()
	{
		form.find('#panel_interno').hide();
		form.find('#panel_error').hide();
		form.find('#panel_exito').show();
		return false;
	}
/**
 * función para desplegar la ventana
 */
	var desplegarButton = $(settings.desplegar);
	desplegarButton.click(function(){
		enviado = false;
		form.find('#panel_interno').show();
		form.find('#panel_error').hide();
		form.find('#panel_exito').hide();

		if(settings.validationEnabled)
		{
			form.resetForm();
		}
		
		$(desplegarPanel).animate({"top": 0, "opacity": 0.9}, { duration: "fast" });
		
		return false;
    });

/**
 * función para replegar la ventana
 */
	var replegarButton = $(this).find(settings.replegar);
	replegarButton.click(function(){
		
		form.find('#panel_interno').hide();
		form.find('#panel_error').hide();
		form.find('#panel_exito').hide();
        $(desplegarPanel).animate({"top": settings.altoPopup, "opacity": 0.9}, { duration: "fast" });
		return false;
	});	


/**
 * Evento click del boton aceptar
 */
	var botonAceptar = $(this).find(settings.aceptar);
	botonAceptar.click(function(){
	
		if(settings.validationEnabled && !form.valid()){
			return false;
		}

		if(!enviado)
		{
			enviado = true;

			if(settings.formPluginEnabled){
				form.ajaxSubmit(defaultFormSettings);
				return false;
			}

			form.submit();
		}
		return false;
	});	


/**
 * Evento click del boton Volver
 */
	var botonVolver = $(this).find(settings.volver);
	botonVolver.click(function(){

		enviado = false;
		form.find('#panel_interno').show();
		form.find('#panel_error').hide();
		form.find('#panel_exito').hide();
		
		return false;
	});	

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
	

	cajaTexto.click(function(){
		cajaTexto.focus();
		cajaTexto.select();
		return false;
	});




	
/**
*	validacion para lista de emails 
*/	
	$.validator.addMethod("listaEmails",
		function(value, element) { 
			var a_emails = new Array();
			var email_valido=true;
			a_emails = value.split(defaultValidationSettings.separadorMails);
    		var regex = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
			
			for(var i = 0; i< a_emails.length && email_valido ;i++){
				if (regex.test( $.trim(a_emails[i])))
					email_valido = true;
				else email_valido =false;
			  }
			  return email_valido;
		  },
		  "dirección de correo electrónico no válida dentro de la lista"
	);
	
	$.validator.addMethod("lista_emails_nombres",
		function() {
			var valido=false;
			var emails= $(form).find(defaultValidationSettings.idEmails);
			var nombres= $(form).find(defaultValidationSettings.idNombres);
			if(emails && nombres)
			{
				var a_emails = new Array();
				a_emails = emails.val().split(defaultValidationSettings.separadorMails);
				
				var a_nombres = new Array();
				a_nombres = nombres.val().split(defaultValidationSettings.separadorMails);
				
				if(a_emails.length == a_nombres.length)
				{
					valido=true;
				}
			}
			return valido;
		},
		"el listado de nombres y direcciones de correo no se corresponden."
	);

	$.validator.addMethod("max_length_tags",
		function(value, element) {
			var valido=true;
			
				var a_tags = new Array();
				a_tags = value.split(defaultValidationSettings.separadorTags);
				
				for(var i=0;i< a_tags.length && valido; i++)
				{
					if($.trim(a_tags[i]).length > defaultValidationSettings.maxLengthTags)
					{
						valido=false;
					}
				}
			return valido;
		},
		"uno de los tags ingresados es demasiado largo."
	);

	return $(this);
};


})(jQuery);