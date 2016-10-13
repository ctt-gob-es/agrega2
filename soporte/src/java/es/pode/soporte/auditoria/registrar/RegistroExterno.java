package es.pode.soporte.auditoria.registrar;

import java.util.HashMap;

import org.apache.log4j.Logger;

/** 
 * Clase que se encarga del registro de los datos que no son interceptados
 * 
 * @author jsimon
 */

public class RegistroExterno {

	private static Logger log = Logger.getLogger(RegistroExterno.class);
	
	/**
	 *  M�todo gen�rico para poder registrar operaciones que no se interceptan  
	 * 	@param datos Datos a registrar 
	 *  @return registro  
	 */
	public static boolean operacionGenerica(DatosVO datos) {

		boolean registro = false;
		
		try {
			Registrar.operacion(datos);			
			registro = true;		
		}
		catch (Exception e) {
			log.error("Se ha producido un error al registrar una operaci�n externa " + e);			
		}
		
		return registro;		
	}
	
	/**
	 *  M�todo gen�rico para poder registrar operaciones que no se interceptan  
	 * 	@param valores Datos a registrar 
	 *  @param moduloDestino Operaci�n realizada
	 *  @return registro  
	 */
	public static boolean operacionGenerica(HashMap valores, String moduloDestino) {

		boolean registro = false;
		
		try {
			DatosVO datos = new DatosVO();
			datos.setValores(valores);
			datos.setModuloDestino(moduloDestino);			
			registro = operacionGenerica(datos);		
		}
		catch (Exception e) {
			log.error("Se ha producido un error al registrar una operaci�n externa " + e);			
		}
		
		return registro;		
	}
	
	/**
	 *  M�todo gen�rico para poder registrar operaciones que no se interceptan  
	 * 	@param idODE Identificador del ODE 
	 *  @param idioma Idioma del ODE
	 *  @param moduloDestino Operaci�n realizada
	 *  @return registro  
	 */
	public static boolean operacionGenerica(String idODE, String idioma, String moduloDestino) {

		boolean registro = false;
		
		try {
			HashMap<String,String> valores = new HashMap<String,String>();
			valores.put(RegistroCtes.PARAMETRO_IDENTIFICADOR_ODE, idODE);
			valores.put(RegistroCtes.PARAMETRO_IDIOMA, idioma);			
			registro = operacionGenerica(valores, moduloDestino);
		}
		catch (Exception e) {
			log.error("Se ha producido un error al realizar un registro externo (no interceptado) de una operaci�n  " + e);			
		}
		
		return registro;		
	}	
	
	/**
	 *  M�todo para poder registrar el env�o de un correo que no se intercepta porque esa operaci�n no llama a un web service  
	 * 	@param idODE Identificador del ODE 
	 *  @param idioma Idioma del ODE
	 *  @return registro  
	 */
	public static boolean operacionEnviarCorreo(String idODE, String idioma) {

		return operacionGenerica(idODE, idioma, RegistroCtes.OPERACION_ENVIAR_CORREO);		
	}
}
