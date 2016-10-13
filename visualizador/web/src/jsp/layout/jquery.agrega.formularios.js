
(function($){

$.fn.tabform = function(tabFormSettings, validationSettings , formSettings){

/**
* 	valores de seteo del plugin
*/
	var settings = $.extend({
			validationEnabled : false,
			formPluginEnabled: false,
			aceptar: '',
			limpiar: '',
			recargar:''
	}, tabFormSettings);

// referencia al formulario
	var form= $(this);
// variable que permite controlar que se lanze solo una vez el evento aceptar
// para enviar el formulario
	var enviado= false;

/**
 * 	valores por defecto de la validacion del formulario 
 */
	var defaultValidationSettings = $.extend({
		separadorMails: '',
		idEmails:'',
		idNombres:'',
		separadorTags: ' ',
		maxLengthTags: '50',
		showErrors: function(errorMap, errorList) {
			var err = this.numberOfInvalids();
			if(err)
			{
				
				var errorMensajes ="";
				for(var i=0; i<errorList.length ; i++)
				{
					errorMensajes = errorMensajes + "<p>"+ errorList[i].message + "</p>";
				}
				
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
	}, validationSettings);
	
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

			
				if (data.indexOf("Error") >-1)
				{
					mostrarErrorServidor();
				}else
				{ 
					mostrarExito();
				}
				return false;
			},
		error: function(data){
				mostrarErrorServidor();
				return false;
		},
		mensajeError: 'Hubo un error en el envío de los datos al servidor.'
	},formSettings);
	
	function mostrarErrorServidor()
	{
		form.find('#panel_exito').hide();
		form.find('#ContenedorErrores').html("<li>" + defaultFormSettings.mensajeError +"</li>");
		form.find('#ContenedorErrores').show();
		form.find('#panel_error').show();
		return false;		
	}

	function mostrarExito()
	{
		form.find('#panel_error').hide();
		form.find('#panel_exito').show();
		return false;
	}

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
			if(settings.formPluginEnabled){
				form.ajaxSubmit(defaultFormSettings);
				enviado=true;
				return true;
			}
		}
		return false;
	});	


	var botonLimpiar = $(this).find(settings.limpiar);
	botonLimpiar.click(function(){

		if(settings.formPluginEnabled){
			var d = new Date();
			var newCapchta = "jcaptcha"+d.getTime()+".jcaptcha"
			form.find("#captchaImage").attr("src",newCapchta);
			form.clearForm();
			form.find('#panel_error').hide();
			form.find('#panel_exito').hide();
			enviado=false;
			return true;
		}
		return false;
	});	


	var botonRecargarCaptcha = $(this).find(settings.recargar);
	botonRecargarCaptcha.click(function(){

		if(settings.formPluginEnabled){
			var d = new Date();
			var newCapchta = "jcaptcha"+d.getTime()+".jcaptcha"
			form.find("#captchaImage").attr("src",newCapchta);
			form.find('#panel_error').hide();
			form.find('#panel_exito').hide();
			enviado=false;
			return true;
		}
		return false;
	});	
	
	
	if(defaultValidationSettings.idEmails!='' && defaultValidationSettings.idNombres!='')
	{
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
				var emails= form.find(defaultValidationSettings.idEmails);
				var nombres= form.find(defaultValidationSettings.idNombres);
				if(emails && nombres)
				{
					var a_emails = new Array();
					if(emails.val()!='')
					{
						a_emails = emails.val().split(defaultValidationSettings.separadorMails);
					}
					
					var a_nombres = new Array();
					if(nombres.val()!='')
					{
						a_nombres = nombres.val().split(defaultValidationSettings.separadorMails);
					}
					
					if(a_emails.length == a_nombres.length)
					{
						valido=true;
					}
				}
				return valido;
			},
			"el listado de nombres y direcciones de correo no se corresponden."
		);
	};
	
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