/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.buscador.presentacion.basico.enviarMail;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.gestorCorreo.negocio.servicios.CorreoOdeVO;
import es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO;
import es.pode.soporte.auditoria.registrar.RegistroExterno;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.url.ImagenesAgrega;


/**
 * @see es.pode.buscador.presentacion.basico.enviarMail.EnviarMailController
 */
public class EnviarMailControllerImpl extends EnviarMailController
{

	private final static int MAX_RESUMEN = 400;
	private final static String FORMULARIO_VALIDO = "FORMULARIO_VALIDO";
	private final static String FORMULARIO_INVALIDO = "FORMULARIO_INVALIDO";

	public final static String ERROR_REGISTRAR_BBDD = "enviar.email.amigo.error.registrar.baseDatos";
	public final static String MENSAJE_CORREOS_DESTINATARIO_OBLIGATORIO = "enviar.email.amigo.error.destinatario.campo.obligatorio";
	public final static String MENSAJE_NOMBRES_DESTINATARIO_OBLIGATORIO = "enviar.email.amigo.error.nombre.destinatario.campo.obligatorio";
	public final static String MENSAJE_TU_CORREO_OBLIGATORIO = "enviar.email.amigo.error.tu.correo.campo.obligatorio";
	public final static String MENSAJE_TU_NOMBRE_OBLIGATORIO = "enviar.email.amigo.error.tu.nombre.campo.obligatorio";
	public final static String MENSAJE_FORMATO_EMAIL_INCORRECTO_DESTINATARIO = "enviar.email.amigo.error.formato.incorrecto.destinatario";
	public final static String MENSAJE_FORMATO_EMAIL_INCORRECTO_REMITENTE = "enviar.email.amigo.error.formato.incorrecto.remitente";
	public final static String MENSAJE_ERROR_DISTINTO_TAMANYO="enviar.email.amigo.error.distinto.tamanyo";
	public final static String MENSAJE_ERROR_MAX_CARACTERES_RESUMEN="enviar.email.amigo.error.max.caracteres.resumen";
	public final static String MENSAJE_ERROR_ENVIO_FALLIDO="enviar.email.amigo.error.fallo.envio";
	
	public final static String MENSAJE_IDIOMAS_BUSQUEDA = "listar.odecu.mostrar.resultados.consulta.cabecera.errorIdiomasBusqueda";	
	
	private static Logger logger = Logger.getLogger(EnviarMailControllerImpl.class);


    /**
     * @see es.pode.buscador.presentacion.basico.enviarMail.EnviarMailController#enviarMail(org.apache.struts.action.ActionMapping, es.pode.buscador.presentacion.basico.enviarMail.EnviarMailForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void enviarMail(ActionMapping mapping, es.pode.buscador.presentacion.basico.enviarMail.EnviarMailForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {       	
    	List errores = new ArrayList();	
    	List erroresValidacion = new ArrayList();	
    	String idiomaNavegacion = ((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
    	String identificadorODE= form.getIdentificadorODE();
    	form.setResultadoValidacion(FORMULARIO_VALIDO);
    	log("EnviarMailControllerImpl - enviarMail.");
        //Validamos los campos del formulario
        erroresValidacion = validaFormulario(form, request);
        if(erroresValidacion!=null && erroresValidacion.size()>0){
        	form.setResultadoValidacion(FORMULARIO_INVALIDO);
        	errores.addAll(erroresValidacion);
        } else{
//        	//Formamos la url de la imagen         	
        	String hrefLogo = ImagenesAgrega.urlHrefLogo();
        	String imagen =form.getUrlImagen();	
        	String imagenLogoAgrega = ImagenesAgrega.urlImagenLogoAgrega();
        	String nombreDestinatarios = form.getNombresHasta();
        	String correoDestinatarios = form.getCorreosHasta();
        	if(form.getEnviarRemitente()!=null && form.getEnviarRemitente().booleanValue()){
        		log("Enviamos email para el remitente ["+form.getCorreoDesde()+"] con nombre ["+ form.getNombreDesde()+"]");
        		nombreDestinatarios += ";" + form.getNombreDesde(); 
        		correoDestinatarios += ";" + form.getCorreoDesde();	
        	}
        	String[] destinatariosArray = correoDestinatarios.split(";");
        	
//        	Campos a rellenar
            //to: emails de los destinatarios del correo
    		//from: quién envía el correo, este valor se recogerá del fichero agrega.properties 
    		//idiomaCorreo:idioma en el que se envia el correo. Si no se rellena el correo se envío en el idioma por defecto de la plataforma
    		//hrefLogo: enlace del logo
    		//urlImagenLogo: url de la imagen del logo
    		//urlFicha: url de la imagen de la ficha
    		//tituloOde: el titulo del ODE
    		//urlImagen: enlace de la imagen:
    		//NombreDestinatario: nombre de los destinatarios separados por punto y coma. Este valor tiene que coincidir con los email de los destinatarios.
    		//NombreRemitente: nombre del remitente que envía el correo
    		//emailRemitente: email del que envia el correo
    		//comentario: comentario del correo. Opcional si el usuario no ha introducido comentario este campo aparecerá vacío.
        	CorreoOdeVO correoOdeVO = new CorreoOdeVO();
        	correoOdeVO.setTo(destinatariosArray);
        	correoOdeVO.setFrom(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER));
        	correoOdeVO.setIdiomaCorreo(idiomaNavegacion);
        	correoOdeVO.setHrefLogo(hrefLogo);
        	correoOdeVO.setUrlImagenLogo(imagenLogoAgrega);
        	correoOdeVO.setUrlFicha(form.getUrlODE());
        	correoOdeVO.setTituloOde(form.getTitulo());
        	correoOdeVO.setUrlImagen(imagen);
        	correoOdeVO.setNombreDestinatario(nombreDestinatarios);
        	correoOdeVO.setNombreRemitente(form.getNombreDesde());
        	correoOdeVO.setEmailRemitente(form.getCorreoDesde());
        	correoOdeVO.setComentario(form.getResumen());
        	ResultadoEnvioCorreoVO resultado = this.getSrvCorreo().correoEnviarAmigo(correoOdeVO);
        	if(resultado.getResultado().equals("KO")){
        		errores.add(MENSAJE_ERROR_ENVIO_FALLIDO);
        		form.setResultadoValidacion(FORMULARIO_INVALIDO);
        	} else {
                //Se almacena en la base de datos el envio para que este accesible en auditoria. Los odes mas enviados.
                log("Se almacena en la BBDD el envio del ode con identificador en el idioma["+form.getIdioma()+"]");
                RegistroExterno registro = new RegistroExterno();  
               
                if (!registro.operacionEnviarCorreo(identificadorODE, form.getIdioma()))
                	saveErrorMessage(request, ERROR_REGISTRAR_BBDD);
                else
                	log("El envio del correo se ha registrado correctamente en la base de datos");
        	}
        	log("Resultado del envío de correo --> " +resultado.getResultado());
        }
        if(errores!=null && errores.size()>0)
        	saveErrorMessage(request, errores);
     }

    
    public String analizaValidacion(ActionMapping mapping, AnalizaValidacionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return form.getResultadoValidacion();
	}		
    
    private List validaFormulario(es.pode.buscador.presentacion.basico.enviarMail.EnviarMailForm form, HttpServletRequest request) throws Exception 
    {
    	List errores = new ArrayList();	    	
    	String[] destinatariosArray = form.getCorreosHasta().split(";");
    	String[] nombresDestinatariosArray = form.getNombresHasta().split(";");
    	
    	//Validamos si todos los campos estan rellenos
    	if(!isChangeable(form.getCorreosHasta()))
    		errores.add(MENSAJE_CORREOS_DESTINATARIO_OBLIGATORIO);  		
    	if(!isChangeable(form.getNombresHasta()))    	
    		errores.add(MENSAJE_NOMBRES_DESTINATARIO_OBLIGATORIO);    	
    	if(!isChangeable(form.getCorreoDesde()))    	
    		errores.add(MENSAJE_TU_CORREO_OBLIGATORIO);    	
    	if(!isChangeable(form.getNombreDesde()))    	
    		errores.add(MENSAJE_TU_NOMBRE_OBLIGATORIO);    	
    	
    	//Validamos si se ha excedido en el numero de caracteres del resumen
    	if(form.getResumen().length()>MAX_RESUMEN)    	
    		errores.add(MENSAJE_ERROR_MAX_CARACTERES_RESUMEN);    	
    		
    	//Validamos los emails de los destinatorios
    	for (int i=0; destinatariosArray!=null && i<destinatariosArray.length; i++)
    	{
    		if(isChangeable(destinatariosArray[i])){
	    		if(!validaTodosEmailsDestino(form.getCorreosHasta()).booleanValue())	    		
	    			errores.add(MENSAJE_FORMATO_EMAIL_INCORRECTO_DESTINATARIO);	    		
    		}
    	}
    	
    	//validamos el email del remitente
    	if(isChangeable(form.getCorreoDesde()))
    		if(!validaEmail(form.getCorreoDesde()).booleanValue())    		
    			errores.add(MENSAJE_FORMATO_EMAIL_INCORRECTO_REMITENTE);    		
    	
    	//Comprobamos que el numero de email destino y nombre destino es el mismo
    	if(isChangeable(form.getCorreosHasta()) && isChangeable(form.getNombresHasta()))
    		if(destinatariosArray.length != nombresDestinatariosArray.length)    		
    			errores.add(MENSAJE_ERROR_DISTINTO_TAMANYO);   		
    	
    	return errores;
    }
    
    private boolean isChangeable(Object oValue){
		return (oValue != null && !("").equals(oValue.toString().trim()));
	}
       
	private Boolean validaEmail(String email) throws Exception{
		Boolean resultado = Boolean.TRUE;
			
		//Pattern pattern = Pattern.compile("[^A-Za-z0-9\\.\\@_\\-~#]+");
		Pattern pattern = Pattern.compile("^[A-Za-z0-9]([A-Za-z0-9.-_])*@[A-Za-z0-9]([A-Za-z0-9\\.\\-\\_])*.([A-Za-z0-9])+$");
		Matcher matcher = pattern.matcher(email);			
			
		if (matcher.find()) {
			log("El email coincide con el patron es correcto el campo email");
		} else {
			resultado = Boolean.FALSE;
		}
		return resultado;	
	}	  	   
	    
	private Boolean validaTodosEmailsDestino (String emailsDestino) throws Exception{	    	
		Boolean resultado = Boolean.TRUE;
		Boolean flagEmailIncorrecto = Boolean.FALSE;	    	
	    	
		String[] emailsDestinoArray = emailsDestino.split(";");
		if(emailsDestinoArray!=null && emailsDestinoArray.length>0){
			for(int i=0; !flagEmailIncorrecto.booleanValue() && i<emailsDestinoArray.length; i++){
				if(!validaEmail(emailsDestinoArray[i].trim()).booleanValue()){
					flagEmailIncorrecto = Boolean.TRUE;	    				
				}
			}	
		}
	    	
		if(flagEmailIncorrecto.booleanValue())
			//Algun email incorrecto
			resultado = Boolean.FALSE;
	    	
		return resultado;
	    	
	}
	    
    private void saveErrorMessage(HttpServletRequest request, List messages) throws Exception{
		log("EnviarCorreoControllerImpl - saveErrorMessage: Formulario incorrecto------------.");
		boolean encontrado;		
		if(request!=null && messages!=null && messages.size()>0)
		{
			//Pintamos la posición cero de la lista
			saveErrorMessage(request, messages.get(0).toString());
			//Recorremos el vector comprobando si el mensaje de error ha sido pintado anteriormente
			for(int i = 1; messages.size() > i ; i++){ 
				encontrado = false;
				for(int j=i; j>0 ;j--){
					if(messages.get(j-1).equals(messages.get(i)))
						encontrado = true;
				}
				if(!encontrado)
					saveErrorMessage(request, messages.get(i).toString());
			}
		}
	}

    private void log(String traza){
		if (logger.isDebugEnabled())logger.debug(traza);
	}

}