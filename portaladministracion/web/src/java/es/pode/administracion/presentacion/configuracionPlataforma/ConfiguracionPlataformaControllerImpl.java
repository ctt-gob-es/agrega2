/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.administracion.presentacion.configuracionPlataforma;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;
import org.apache.commons.validator.ValidatorException;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.configuracionPlataforma.servicios.SrvPropiedadService;
import es.pode.configuracionPlataforma.servicios.PropiedadVO;


/**
 * @see es.pode.administracion.presentacion.configuracionPlataforma.ConfiguracionPlataformaController
 */
public class ConfiguracionPlataformaControllerImpl extends ConfiguracionPlataformaController
{
	private static Logger logger = Logger.getLogger(ConfiguracionPlataformaControllerImpl.class);
	private SrvPropiedadService srvPropiedad = null;
	
	//Lista de propiedades/variables que se podran configurar
	public static String[] propiedades = new String[]{
		AgregaProperties.CATALOGO_AGREGA,
		AgregaProperties.CATALOGO_MEC,
		AgregaProperties.CHECK_PASSWORD,
		AgregaProperties.EMAIL_ADMIN_REPOSITORIO,
		AgregaProperties.INDEX_SERVER_PORT,
		AgregaProperties.INDEX_SERVER_URL,	
		AgregaProperties.ADMINLDAPEXTERNO,
		AgregaProperties.NUM_DESCARGAS_MOSTRADAS_EN_RESUMEN,
		AgregaProperties.NUM_NOTICIAS_MOSTRADAS_EN_RESUMEN,
		AgregaProperties.REST_RESULTADOS_POR_PAGINA,
		AgregaProperties.SECUENCIA_SIN_LOGAR,
		AgregaProperties.VALOR_CUOTA_DEFECTO,
		AgregaProperties.VISTA_PREVIA_AGREGA
	};
	
	
	private SrvPropiedadService obtieneSrvPropiedad() throws Exception {
		if (srvPropiedad==null) srvPropiedad = this.getSrvPropiedadService();
		return srvPropiedad;
	}
	
	
    /**
     * Metodo que cambia el nombre de las variables sustituyendo los caracteres que sean punto
     * por barras bajas. Esto es necesario debido a que el nombre de las variables se usa como 
     * identificador y sirven para extraer las variables y sus valores del form. 
     * El caracter punto daria problemas en la parte de la vista (en el JSP).
     * @param props
     * @return
     */
    private ArrayList<PropiedadVO> reemplazarPuntosPorBarrasBajas(ArrayList<PropiedadVO> props) {
    	if (props==null || props.size()==0) return props;
    	
    	for(int i=0; i<props.size(); i++) {
    		String nombre = props.get(i).getNombre();
    		if(nombre.contains("."))
    			props.get(i).setNombre(nombre.replace(".", "_"));
    	}
    	return props;
    }
	

	@Override
	public void cargarDatos(ActionMapping mapping, CargarDatosForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HashMap<String ,String> propsMap = new HashMap<String ,String>();
		ArrayList<PropiedadVO> props = new ArrayList<PropiedadVO>();
		
		try {
			for(int i=0; i<propiedades.length; i++) {
				props.add(obtieneSrvPropiedad().getPropiedad(propiedades[i]));
				propsMap.put(propiedades[i], props.get(i).getValor());
			}
		} catch (Exception e) {
			logger.error("Error al obtener los parametros de configuracion de la plataforma",e);
			throw new ValidatorException("{error.obtencion.parametros}");			
		}
		form.setListaVariables					(reemplazarPuntosPorBarrasBajas(props));
		form.setCatalogo_agrega					(propsMap.get(AgregaProperties.CATALOGO_AGREGA));
		form.setCatalogo_mec					(propsMap.get(AgregaProperties.CATALOGO_MEC));
		form.setCheck_password					(propsMap.get(AgregaProperties.CHECK_PASSWORD));
		form.setEmailAdmin						(propsMap.get(AgregaProperties.EMAIL_ADMIN_REPOSITORIO));
		form.setIndexServer_port				(propsMap.get(AgregaProperties.INDEX_SERVER_PORT));
		form.setIndexServer_url					(propsMap.get(AgregaProperties.INDEX_SERVER_URL));	
		form.setLdap_external_admin				(propsMap.get(AgregaProperties.ADMINLDAPEXTERNO));
		form.setNumDescargasMostradasEnResumen	(propsMap.get(AgregaProperties.NUM_DESCARGAS_MOSTRADAS_EN_RESUMEN));
		form.setNumNoticiasMostradasEnResumen	(propsMap.get(AgregaProperties.NUM_NOTICIAS_MOSTRADAS_EN_RESUMEN));
		form.setRest_resultados_por_pagina		(propsMap.get(AgregaProperties.REST_RESULTADOS_POR_PAGINA));
		form.setSecuencia_sin_logar				(propsMap.get(AgregaProperties.SECUENCIA_SIN_LOGAR));
		form.setValorCuotaDefecto				(propsMap.get(AgregaProperties.VALOR_CUOTA_DEFECTO));
		form.setVistaPreviaAgrega				(propsMap.get(AgregaProperties.VISTA_PREVIA_AGREGA));
	}

	
	private boolean esValido (String s) {
		if(s==null || s.equals(""))	return false;
		return true;
	}
	
	
	private boolean esUnNumero (String num) {
		if(!esValido(num)) return false;
		int puerto;
		try {
			puerto = Integer.valueOf(num);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	

	private boolean esUnPuerto (String port) {
		if(!esValido(port)) return false;
		int puerto;
		try {
			puerto = Integer.valueOf(port);
		} catch (Exception e) {
			return false;
		}
		if(puerto>19 && puerto<65537) 
			return true;
		return false;
	}
	
	
	private boolean esUnMail (String mail) {
		if(!esValido(mail))	return false;
		if(mail.contains("@")) {
			String[] email = mail.split("@");
			if(email.length!=2) return false;
			if(!email[1].contains(".")) return false;
			String[] emailDomain = email[1].split("\\.");
			if(emailDomain.length<2) return false;
			return true;
		}
		return false;
	}
	
	
	private boolean esUnBooleano (String bool) {
		if(!esValido(bool)) return false;
		if(bool.equalsIgnoreCase("true") || bool.equalsIgnoreCase("false"))
			return true;
		return false;
	}

	
	private void validarDatos(ActualizarDatosForm form) throws Exception {

		if(!esValido(form.getCatalogo_agrega())) 	
			throw new ValidatorException("{error.catalogo_agrega}");		
		
		if(!esValido(form.getCatalogo_mec())) 	
			throw new ValidatorException("{error.catalogo_mec}");	
		
		if(!esUnBooleano(form.getCheck_password())) 	
			throw new ValidatorException("{error.check_password}");		
		
		if(!esUnMail(form.getEmailAdmin())) 	
			throw new ValidatorException("{error.email_admin_repositorio}");		
		
		if(!esUnPuerto(form.getIndexServer_port())) 	
			throw new ValidatorException("{error.index_server_port}");		
		
		if(!esValido(form.getIndexServer_url())) 	
			throw new ValidatorException("{error.index_server_url}");		

		if(!esUnMail(form.getLdap_external_admin())) 	
			throw new ValidatorException("{error.ldap_external_admin}");	
		
		if(!esUnNumero(form.getNumDescargasMostradasEnResumen())) 	
			throw new ValidatorException("{error.num_descargas_mostradas_en_resumen}");		
		
		if(!esUnNumero(form.getNumNoticiasMostradasEnResumen())) 	
			throw new ValidatorException("{error.num_noticias_mostradas_en_resumen}");		
		
		if(!esUnNumero(form.getRest_resultados_por_pagina())) 	
			throw new ValidatorException("{error.rest_resultados_por_pagina}");		
		
		if(!esUnBooleano(form.getSecuencia_sin_logar())) 	
			throw new ValidatorException("{error.secuencia_sin_logar}");		
		
		if(!esUnNumero(form.getValorCuotaDefecto())) 	
			throw new ValidatorException("{error.valor_cuota_por_defecto}");			
		
		if(!esValido(form.getVistaPreviaAgrega())) 	
			throw new ValidatorException("{error.vista_previa_agrega}");	
	}
	

	@Override
	public void actualizarDatos(ActionMapping mapping,
			ActualizarDatosForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		validarDatos(form);
		
		String tmp = "";
		
		tmp=form.getCatalogo_agrega();	
		try {
			if(!obtieneSrvPropiedad().set(AgregaProperties.CATALOGO_AGREGA, tmp)) {
				logger.error("Error al ajustar el valor del parametro "+AgregaProperties.CATALOGO_AGREGA);
				throw new ValidatorException("{error.servicio.catalogo_agrega}");		
			}
		} catch (Exception e) {
			logger.error("Error al ajustar el valor del parametro "+AgregaProperties.CATALOGO_AGREGA,e);
			throw new ValidatorException("{error.servicio.catalogo_agrega}");				
		}
		
		tmp=form.getCatalogo_mec();		
		try {
			if(!obtieneSrvPropiedad().set(AgregaProperties.CATALOGO_MEC, tmp)){
				logger.error("Error al ajustar el valor del parametro "+AgregaProperties.CATALOGO_MEC);
				throw new ValidatorException("{error.servicio.catalogo_mec}");					
			}
		} catch (Exception e) {
			logger.error("Error al ajustar el valor del parametro "+AgregaProperties.CATALOGO_MEC,e);
			throw new ValidatorException("{error.servicio.catalogo_mec}");			
		}
		
		tmp=form.getCheck_password();	
		try {
			if(!obtieneSrvPropiedad().set(AgregaProperties.CHECK_PASSWORD, tmp)) {
				logger.error("Error al ajustar el valor del parametro "+AgregaProperties.CHECK_PASSWORD);
				throw new ValidatorException("{error.servicio.check_password}");					
			}
		} catch (Exception e) {
			logger.error("Error al ajustar el valor del parametro "+AgregaProperties.CHECK_PASSWORD,e);
			throw new ValidatorException("{error.servicio.check_password}");			
		}
		
		tmp=form.getEmailAdmin();		
		try {
			if(!obtieneSrvPropiedad().set(AgregaProperties.EMAIL_ADMIN_REPOSITORIO, tmp)) {
				logger.error("Error al ajustar el valor del parametro "+AgregaProperties.EMAIL_ADMIN_REPOSITORIO);
				throw new ValidatorException("{error.servicio.email_admin_repositorio}");					
			}
		} catch (Exception e) {
			logger.error("Error al ajustar el valor del parametro "+AgregaProperties.EMAIL_ADMIN_REPOSITORIO,e);
			throw new ValidatorException("{error.servicio.email_admin_repositorio}");			
		}
		
		tmp=form.getIndexServer_port();	
		try {
			if(!obtieneSrvPropiedad().set(AgregaProperties.INDEX_SERVER_PORT, tmp)) {
				logger.error("Error al ajustar el valor del parametro "+AgregaProperties.INDEX_SERVER_PORT);
				throw new ValidatorException("{error.servicio.index_server_port}");
			}
		} catch (Exception e) {
			logger.error("Error al ajustar el valor del parametro "+AgregaProperties.INDEX_SERVER_PORT,e);
			throw new ValidatorException("{error.servicio.index_server_port}");			
		}
		
		tmp=form.getIndexServer_url();		
		try {
			if(!obtieneSrvPropiedad().set(AgregaProperties.INDEX_SERVER_URL, tmp)) {
				logger.error("Error al ajustar el valor del parametro "+AgregaProperties.INDEX_SERVER_URL);
				throw new ValidatorException("{error.servicio.index_server_url}");	
			}
		} catch (Exception e) {
			logger.error("Error al ajustar el valor del parametro "+AgregaProperties.INDEX_SERVER_URL,e);
			throw new ValidatorException("{error.servicio.index_server_url}");			
		}

		tmp=form.getLdap_external_admin();		
		try {
			if(!obtieneSrvPropiedad().set(AgregaProperties.ADMINLDAPEXTERNO, tmp)) {
				logger.error("Error al ajustar el valor del parametro "+AgregaProperties.ADMINLDAPEXTERNO);
				throw new ValidatorException("{error.servicio.ldap_external_admin}");					
			}
		} catch (Exception e) {
			logger.error("Error al ajustar el valor del parametro "+AgregaProperties.ADMINLDAPEXTERNO,e);
			throw new ValidatorException("{error.servicio.ldap_external_admin}");			
		}
		
		tmp=form.getNumDescargasMostradasEnResumen();		
		try {
			if(!obtieneSrvPropiedad().set(AgregaProperties.NUM_DESCARGAS_MOSTRADAS_EN_RESUMEN, tmp)) {
				logger.error("Error al ajustar el valor del parametro "+AgregaProperties.NUM_DESCARGAS_MOSTRADAS_EN_RESUMEN);
				throw new ValidatorException("{error.servicio.num_descargas_mostradas_en_resumen}");	
			}
		} catch (Exception e) {
			logger.error("Error al ajustar el valor del parametro "+AgregaProperties.NUM_DESCARGAS_MOSTRADAS_EN_RESUMEN,e);
			throw new ValidatorException("{error.servicio.num_descargas_mostradas_en_resumen}");			
		}
		
		tmp=form.getNumNoticiasMostradasEnResumen();		
		try {
			if(!obtieneSrvPropiedad().set(AgregaProperties.NUM_NOTICIAS_MOSTRADAS_EN_RESUMEN, tmp)) {
				logger.error("Error al ajustar el valor del parametro "+AgregaProperties.NUM_NOTICIAS_MOSTRADAS_EN_RESUMEN);
				throw new ValidatorException("{error.servicio.num_noticias_mostradas_en_resumen}");	
			}
		} catch (Exception e) {
			logger.error("Error al ajustar el valor del parametro "+AgregaProperties.NUM_NOTICIAS_MOSTRADAS_EN_RESUMEN,e);
			throw new ValidatorException("{error.servicio.num_noticias_mostradas_en_resumen}");			
		}
		
		tmp=form.getRest_resultados_por_pagina();		
		try {
			if(!obtieneSrvPropiedad().set(AgregaProperties.REST_RESULTADOS_POR_PAGINA, tmp)) {
				logger.error("Error al ajustar el valor del parametro "+AgregaProperties.REST_RESULTADOS_POR_PAGINA);
				throw new ValidatorException("{error.servicio.rest_resultados_por_pagina}");		
			}
		} catch (Exception e) {
			logger.error("Error al ajustar el valor del parametro "+AgregaProperties.REST_RESULTADOS_POR_PAGINA,e);
			throw new ValidatorException("{error.servicio.rest_resultados_por_pagina}");			
		}
		
		tmp=form.getSecuencia_sin_logar();		
		try {
			if(!obtieneSrvPropiedad().set(AgregaProperties.SECUENCIA_SIN_LOGAR, tmp)) {
				logger.error("Error al ajustar el valor del parametro "+AgregaProperties.SECUENCIA_SIN_LOGAR);
				throw new ValidatorException("{error.servicio.secuencia_sin_logar}");		
			}
		} catch (Exception e) {
			logger.error("Error al ajustar el valor del parametro "+AgregaProperties.SECUENCIA_SIN_LOGAR,e);
			throw new ValidatorException("{error.servicio.secuencia_sin_logar}");			
		}
		
		tmp=form.getValorCuotaDefecto();		
		try {
			if(!obtieneSrvPropiedad().set(AgregaProperties.VALOR_CUOTA_DEFECTO, tmp)) {
				logger.error("Error al ajustar el valor del parametro "+AgregaProperties.VALOR_CUOTA_DEFECTO);
				throw new ValidatorException("{error.servicio.valor_cuota_por_defecto}");	
			}
		} catch (Exception e) {
			logger.error("Error al ajustar el valor del parametro "+AgregaProperties.VALOR_CUOTA_DEFECTO,e);
			throw new ValidatorException("{error.servicio.valor_cuota_por_defecto}");			
		}
		
		tmp=form.getVistaPreviaAgrega();		
		try {
			if(!obtieneSrvPropiedad().set(AgregaProperties.VISTA_PREVIA_AGREGA, tmp)) {
				logger.error("Error al ajustar el valor del parametro "+AgregaProperties.VISTA_PREVIA_AGREGA);
				throw new ValidatorException("{error.servicio.vista_previa_agrega}");	
			}
		} catch (Exception e) {
			logger.error("Error al ajustar el valor del parametro "+AgregaProperties.VISTA_PREVIA_AGREGA,e);
			throw new ValidatorException("{error.servicio.vista_previa_agrega}");			
		}
	}

}