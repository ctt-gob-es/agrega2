/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.modificador.negocio.cambios;

public class CodigosMensajesError {

	// La tarea se ha ejecutado correctamente
	public final static String TAREA_EJECUTADA_CORRECTAMENTE = "msg.tarea.ejecutada.con.exito";
		
	// Conflicto de backup: Este ODE ha sido modificador por una tarea anterior.
	// Elimine o restaure el backup de la anterior tarea.
	public final static String ODE_MODIFICADO_POR_OTRA_TAREA = "msg.ode.modificado.por.otra.tarea";
	
	public final static String ERROR_PARSEO_ODE = "msg.error.parseo.ode";
	
	public final static String ERROR_PREPARACION_ODE = "msg.error.preparacion.ode";
	
	public final static String ERROR_ESCRITURA_ODE = "msg.error.escritura.ode";
	
	public final static String ERROR_VALIDACION_ODE = "msg.error.validacion.ode";
	
	public final static String ERROR_FINALIZACION_ODE = "msg.error.finalizacion.ode";
	
	public final static String WARNING = "msg.warning.ode";
	
	public final static String ERROR_PUBLICACION = "msg.error.publicacion.ode";
	
	public final static String TAREA_WARNING = "msg.tarea.warning";
	
	public final static String TAREA_ERROR = "msg.tarea.error";
	
	public final static String ODE_RESTAURADO = "msg.ode.restaurado";
	
	public final static String SIN_ODES = "msg.sin.odes";
	
	public final static String ZIPS_NO_ODE="msg.tarea.warning.zips";
	
//	 La tarea se ha ejecutado correctamente pero queda pendiente de confirmacion

	public final static String TAREA_EJECUTADA_PENDIENTE = "msg.tarea.ejecutada.con.exito.pendiente";
	
	//TODO Crear propiedad
	public final static String ERROR_GENERACION_INFORME ="msg.error.generacion.informe";
}
