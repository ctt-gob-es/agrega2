/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.presentacion.exportar;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.entregar.negocio.servicios.PaquetePifVO;
import es.pode.entregar.negocio.servicios.SrvEntregarService;
import es.pode.entregar.negocio.servicios.TipoPifVO;
import es.pode.presentacion.OdeSession;
import es.pode.presentacion.VisualizadorSession;
import es.pode.presentacion.exceptions.NoAutenticadoException;
import es.pode.presentacion.utils.OdeSessionUtils;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.presentacion.exportar.ExportarController
 */
public class ExportarControllerImpl extends ExportarController
{
	private static final String FORMATO_DEFAULT="CONTENIDOS";
	protected static Logger logger = Logger.getLogger(ExportarControllerImpl.class);
	
	private java.util.Properties pSpringProperties = null;
	
	public final static String LITERAL_METADATOS_PDF = "visualizador.descargas.formato.metadatosPDF";
	
    public final void descargarFicheroOde(
    		ActionMapping mapping, 
    		DescargarFicheroOdeForm form, 
    		HttpServletRequest request, 
    		HttpServletResponse response)
    throws Exception
    {
    	VisualizadorSession sesion= this.getVisualizadorSession(request);
    	String identificador = form.getIdentificador();
    	logger.debug("Identificador vale "+identificador);
    	OdeSession odeSesion = OdeSessionUtils.getOdeSession(sesion, identificador);
    	
    	if(form.isTieneLicencia() && 
    			(form.getAceptaLicencia()==null || form.getAceptaLicencia().equals("")))
    	{
    		throw new ValidatorException("{descargar.formatos.aceptarObligatorio}");
    	}
	    	
	    try{	
		    logger.debug("DescargarControllerImpl - descargarFicheroODE: Descargar ODE con identificadorODE ["+sesion.getIdentificador()+"] y formato["+form.getFormato()+"]");	
		    
		    SrvEntregarService entregarService = this.getSrvEntregarService();
		    
		    if(form.getFormato().equals("descargar.formato.RECURSO_UNICO")) {
				String pathFicheroGenerado=entregarService.obtenerRecursoUnicoDelODE(identificador);
				logger.debug("DescargarControllerImpl - descargarFicheroODE: URL a descargar=[" +pathFicheroGenerado+"]" );
				String p[]=pathFicheroGenerado.split(File.separator);
				String nombreFichero = p[p.length-1];
				String extension = nombreFichero.substring(nombreFichero.lastIndexOf("."), nombreFichero.length()-1);
				response.setContentType("application/"+extension+"\"");	
				response.setHeader("Content-Disposition", "attachment;filename=\""+nombreFichero+"\"");
				response.setHeader("Cache-Control", "public");	
				response.addDateHeader("Expires", System.currentTimeMillis()+(24*60*60*1000));
				response.sendRedirect("http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+pathFicheroGenerado);
			
		    } else if(form.getFormato().equals(getPropertyValue(LITERAL_METADATOS_PDF))) {
				String pathFicheroGenerado=entregarService.generarPDF(identificador);
				logger.debug("DescargarControllerImpl - descargarFicheroODE: URL a descargar=[" +pathFicheroGenerado+"]" );
				String titulo = identificador;
				response.setContentType("application/pdf");	
				response.setHeader("Content-Disposition", "attachment;filename=\""+titulo+".pdf\"");
				response.setHeader("Cache-Control", "public");	
				response.addDateHeader("Expires", System.currentTimeMillis()+(24*60*60*1000));
				response.sendRedirect("http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+pathFicheroGenerado);
				
			} else {
			    PaquetePifVO resultadoEntregar = entregarService.generarPaquetePIFTipoPIF(new TipoPifVO(identificador, form.getFormato() , odeSesion.getIdiomaCat()));
			    logger.debug("DescargarControllerImpl - descargarFicheroODE: URL a descargar=["+resultadoEntregar.getHref()+"] del ODE=["+odeSesion.getTituloOde()+"]");	
			    String titulo = (odeSesion.getTituloOde()!=null)?odeSesion.getTituloOde().trim().replaceAll(" ", "_"):identificador;	
			    response.setContentType("application/zip");	
			    response.setHeader("Content-Disposition", "attachment;filename="+titulo+".zip");	
			    response.setHeader("Cache-Control", "public");	
			    response.sendRedirect(AgregaPropertiesImpl.getInstance().getUrlNodo()+resultadoEntregar.getHref());	
			}
		    
	    } catch (Exception e){	
		    logger.error("ExportarControllerImpl - Visualizador ERROR: Error al descargar el fichero del ODE",e);
		    throw new ValidatorException("{descargar.error.general}");	
	    }
    }


	@Override
	public void cargarDatos(
			ActionMapping mapping, 
			CargarDatosForm form,
			HttpServletRequest request, 
			HttpServletResponse response)
	throws Exception 
	{
		VisualizadorSession sesion= this.getVisualizadorSession(request);
    	
//        String identificador =sesion.getIdentificador();
		String identificador =form.getIdentificador();
    	OdeSession odeSesion = OdeSessionUtils.getOdeSession(sesion, identificador);
    	
    	form.setIdentificador(identificador);
		
    	if(!sesion.isVerExportar())
    	{
    		logger.error("El ODE no se puede visualizar porque el usuario no está logado ");
			request.setAttribute("codigo_error", "noautenticado"); 
    		throw new NoAutenticadoException("Usuario No Autenticado");
    	}
    	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////RETORNO			////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    	odeSesion.setRetorno("Exportar");

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    		
//////////////		EXPORTACIÓN
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    	
		form.setFormato(FORMATO_DEFAULT);
		form.setAceptaLicencia("");

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////// CARGA DESTACADO		////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        if(sesion.isVerFavorito())
        {
			boolean esFavorito= false;
			try{
				esFavorito = this.getSrvPerfilPublico().existeFavoritoEnUsuario(identificador, sesion.getUsuario());
			}catch (Exception e) {
				logger.error("error al acceder al servicio PerfilPublico ", e);
			}
			sesion.setFavorito(esFavorito);
        }
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
//		valoración
/////////////////////////////////////////////////////////////////////////////////////////////////////////////			
		
		
		if(sesion.isVerValorar())
		{
			String usuario="";
			if(sesion.isIdentidadFederada())
			{
	    		URL urlServer= new URL(sesion.getNodoOrigen());
				usuario = sesion.getUsuarioOrigen() + "@" + urlServer.getHost();
			}else
			{
				usuario= sesion.getUsuario();
			}
			
			try{
				odeSesion.setValorado(this.getSrvValoracionService().tieneValoracion(usuario, identificador, odeSesion.getIdiomaCat()));
				odeSesion.setValoracion("" + this.getSrvValoracionService().consultarValoracion(identificador));
			}catch (Exception e) {
				logger.error("error al acceder al servicio de valoración " , e);
				odeSesion.setValoracion("0.0");
				odeSesion.setValorado(true);
			}
		}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//		CARGAR VALORES EN EL FORMULARIO PARA MOSTRAR EL MENU
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
		form.setDatosSalida(odeSesion.getOrganizaciones());
		form.setLocalizacion(odeSesion.getLocalizadorCont());
		form.setIdSeleccionado(odeSesion.getIdSeleccionado());
		form.setRutaSeleccionado(odeSesion.getRutaSeleccionado());
		form.setIdentificador(identificador);
		form.setTituloOde(odeSesion.getTituloOde());
		form.setSecuencia(odeSesion.isSecuencia());
		form.setIdiomaCat(odeSesion.getIdiomaCat());
		form.setTextoLicencia(odeSesion.getTextoLicencia());
		form.setRetorno(odeSesion.getRetorno());
		
		form.setMostrarDescargaRecursoUnico(false);
		if(this.getSrvEntregarService().odeConRecursoDirectamenteDescargable(identificador)) {
			form.setMostrarDescargaRecursoUnico(true);
		}
	}



	private String getPropertyValue(String sKey) throws IOException {
		InputStream fIsSpringProperties = this.getClass().getResourceAsStream(
				"/visualizador.properties");
		if (this.pSpringProperties == null) {
			pSpringProperties = new java.util.Properties();
			pSpringProperties.load(fIsSpringProperties);
		}
		fIsSpringProperties.close();
		logger.debug("Propiedad recuperada: " + sKey + " : "
				+ pSpringProperties.getProperty(sKey));
		// devolvemos la propiedad
		return pSpringProperties.getProperty(sKey);
	}

}