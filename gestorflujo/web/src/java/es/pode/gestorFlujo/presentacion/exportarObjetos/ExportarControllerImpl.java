/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.gestorFlujo.presentacion.exportarObjetos;


import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.entregar.negocio.servicios.PaquetePifVO;
import es.pode.entregar.negocio.servicios.SrvEntregarService;
import es.pode.entregar.negocio.servicios.TipoPifVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;

/**
 * @see es.pode.gestorFlujo.presentacion.exportarObjetos.ExportarController
 */
public class ExportarControllerImpl extends ExportarController {

	private static final long serialVersionUID = -7776819828205446700L;
	
	private Logger logger = Logger.getLogger(ExportarControllerImpl.class);
	private final String SPLITTER = ";";
	private final String mal = "mal";
	private final String cancelar = "Cancelar";
	
	private final String OBJETOS_DESPUBLICADOS = "objetosDespublicados";
	private final String OBJETOS_PERSONALES = "objetosPersonales";
	private final String OBJETOS_COMPARTIDOS = "objetosCompartidos";

	/**
	 * @see es.pode.gestorFlujo.presentacion.exportarObjetos.ExportarController#exportaODEFichero(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.exportarObjetos.ExportaODEFicheroForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public final String exportaODEFichero(ActionMapping mapping,
			es.pode.gestorFlujo.presentacion.exportarObjetos.ExportaODEFicheroForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		SrvEntregarService entregar = this.getSrvEntregarService();

		// para esta entrega todavía no se utilizan el tipo de formato, pero ya lo tenemos hecho
		// para cuando sea necesario aquí y en el jsp (a fuego)
		//entregar.generarPaquetePIFTipoPIF(form.getIdODE(), form.getFormatoExportacion());

//		final int BUFFER_SIZE = 2048;
		//logger.debug( " es compartido? "+form.getCompartido());
		logger.debug( " es compartido2? "+form.getRetorno());
		PaquetePifVO resultadoEntregar=null; 
		String accion = obtenerAccion(request);
		 String[] accionPartes = accion.split("-");
			accion = accionPartes[0];
			if (accionPartes.length  > 1){
				request.setAttribute("posicion", accionPartes[1]);
			}
		if(!accion.equals(cancelar)){
			try {
				
				if(logger.isDebugEnabled())
				logger.debug("Generando paquete pif tipo: " + form.getFormatoExportacion() + " de ide ode: "
						+ form.getIdODE());
				TipoPifVO tpifVO = new TipoPifVO();
				tpifVO.setIdODE(form.getIdODE());
				tpifVO.setTipoPif(form.getFormatoExportacion());
				resultadoEntregar = entregar.generarPaquetePIFTipoPIF(tpifVO);
	
			} catch (Exception ex) {
				logger
						.error("Error en la obtencion del fichero ZIP del ODE con identificadorODE[" + form.getIdODE()
								+ "] ");
				logger.error(ex);
				throw new ValidatorException("{gestorFlujo.error.exportar.descargar}");
			}
	
			
			if (!resultadoEntregar.getResultadoValidacion().getEsValidoManifest().booleanValue())
			{
				String href = LdapUserDetailsUtils.getSubdominio() + "/" + resultadoEntregar.getHref();
				href = href.replaceAll("/{2,}", "/");
				form.setMensajes(resultadoEntregar.getResultadoValidacion().getResultadoValidacion().substring(0,resultadoEntregar.getResultadoValidacion().getResultadoValidacion().length()-1).split(SPLITTER));
				form.setHref(href);
				return mal;
			}
			if (resultadoEntregar.getHref() == null) {//Nos devuelve el href de donde se encuentra el objeto
				logger
						.error("Fichero ZIP del ODE con identificadorODE[" + form.getIdODE()
								+ "] vacio. Abortamos descarga.");
				throw new ValidatorException("{gestorFlujo.error.exportar.descargar}");
			}
			
			response.sendRedirect("http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+"/"+resultadoEntregar.getHref());//Redireccionamos la URL que nos ha llegado
		}
		
		return accion;

	}
	
	private String obtenerAccion(HttpServletRequest request) 
	throws Exception
	{
		String accion="";
		String[] partes;
		for (Enumeration names = request.getParameterNames();accion.equals("") && names.hasMoreElements();)
	       {
	      	 String name = String.valueOf(names.nextElement());
	           if(name.startsWith("accion"))
	           {
	        	   partes= name.split("_");
		        	 if(partes.length>0){
		        		 StringBuilder anadidos=new StringBuilder(partes[1]);
		        		 for(int i=2;i<partes.length;i++){
		        			 anadidos.append("_"+partes[i]);
		        		 }
		        	 accion=anadidos.toString();
		        	 }
	        	}
		
	        }
		return accion;
	}

	/**
	 * @see es.pode.gestorFlujo.presentacion.exportarObjetos.ExportarController#cargarFormatosExportacion(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.exportarObjetos.CargarFormatosExportacionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public final void cargarFormatosExportacion(ActionMapping mapping,
			es.pode.gestorFlujo.presentacion.exportarObjetos.CargarFormatosExportacionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		//logger.debug( " es compartido2? "+form.getCompartido());
		logger.debug( " es compartido2? "+form.getRetorno());
		if(logger.isDebugEnabled())
		logger.debug("cargando formatos de exportacion para ode: " + form.getIdODE() + "de titulo: " + form.getTitulo());
	}
	
	//Este método comprueba el origen, mira si es de personales, de compartidos o de los objetos despublicados.
	public String comprobarOrigen(ActionMapping mapping, ComprobarOrigenForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String retorno="";
		logger.debug("El origen es "+form.getRetorno());
		if(form.getRetorno()!= null && form.getRetorno().equals(this.OBJETOS_DESPUBLICADOS)){
			retorno ="objetosDespublicados";
		}
		if(form.getRetorno()!= null && form.getRetorno().equals(this.OBJETOS_PERSONALES)){
			retorno = "objetosPersonales";
		}
		if(form.getRetorno()!= null && form.getRetorno().equals(this.OBJETOS_COMPARTIDOS)){
			retorno = "objetosCompartidos";
		}
		logger.debug("Devolvemos "+ retorno);
		return retorno;
	}

	@Override
	public void irAObjetosPersonales(ActionMapping mapping,
			IrAObjetosPersonalesForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String url=request.getSession().getServletContext().getInitParameter("url_gestorFlujo");
	    response.sendRedirect("../../" + url);
	}
	

}