/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.presentacion.visualizadorAdl;

import java.io.File;
import java.net.URLDecoder;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.adl.server.LMSManifestHandler;
import es.pode.adl.util.ADLUtils;
import es.pode.adl.validator.ADLValidatorOutcome;
import es.pode.adl.validator.contentpackage.LaunchData;
import es.pode.entregar.negocio.servicios.LocalizadorAdlVO;
import es.pode.soporte.utiles.ficheros.UtilesFicheros;

/**
 * @see es.pode.presentacion.visualizadorAdl.VisualizadorAdlController
 */
public class VisualizadorAdlControllerImpl extends VisualizadorAdlController
{
	protected static Logger logger = Logger.getLogger(VisualizadorAdlControllerImpl.class);

    /**
     * @see es.pode.presentacion.visualizadorAdl.VisualizadorAdlController#cargarDatosAdl(org.apache.struts.action.ActionMapping, es.pode.presentacion.visualizadorAdl.CargarDatosAdlForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void cargarDatosAdl(ActionMapping mapping, es.pode.presentacion.visualizadorAdl.CargarDatosAdlForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	
    try{
    	String identificador = request.getParameter("identificador");
    	String idioma= request.getParameter("idioma");
    	LocalizadorAdlVO localAdl = this.getSrvEntregarService().localizacionPaquetePIF(identificador);
    	
    	request.getSession().setAttribute("rutaodes", localAdl.getPath());
			
		LMSManifestHandler lmsManifestHandler = new LMSManifestHandler(localAdl.getPath());
   		
		//rellenamos el webpath con la ruta de la clase
		String adlutilpath = ADLUtils.getADLUserDir();
   		lmsManifestHandler.setWebPath(adlutilpath);
   		lmsManifestHandler.setCourseID(identificador);
   		//identificador de la session
   		String probandoHost = AgregaPropertiesImpl.getInstance().getUrlNodo();
   		
   		String miUrl= probandoHost + (localAdl.getUrl().startsWith("/")?localAdl.getUrl():"/"+localAdl.getUrl())+ "/";
   		lmsManifestHandler.setIdSession(request.getSession().getId());
   		ADLValidatorOutcome adlvaloutcome= lmsManifestHandler.processPackage(localAdl.getPath(), false);
   		if (adlvaloutcome.getIsValidRoot()) {
   			logger.info("El Root es Valido! ");
   	        request.getSession().setAttribute("COURSEID", lmsManifestHandler.getCourseID());
   	        request.getSession().setAttribute("USERID", "admin");
   	        request.getSession().setAttribute("TOC", "true");
   	        request.getSession().setAttribute("activityID", null);
   	        request.getSession().setAttribute("launchdataList", lmsManifestHandler.getLaunchDataList());
   	        request.getSession().setAttribute("idSession", lmsManifestHandler.getIdSession().toString());
   	    	request.getSession().setAttribute("webPath", lmsManifestHandler.getWebPath());
   	        
   	        //Conversion de launchdataList
   	        Vector vLD = new Vector();
   	        vLD = (Vector)request.getSession().getAttribute("launchdataList");
   	        Vector newVLD = new Vector();
   	        int iv=0;
   	        while (iv<vLD.size()) {
   	        	LaunchData ld = (LaunchData)vLD.get(iv);
   	        	String alteredLocation = "";
                    //If it's blank or it's external, don't concatenate to the 
                    //local Web root.
                    if ((ld.getLocation().equals(""))|| (ld.getLocation().startsWith("http://"))||
                        (ld.getLocation().startsWith("https://")))
                    {
                   	alteredLocation = URLDecoder.decode(ld.getLocation(), "UTF-8" ); 
                       if ( !(ld.getParameters().equals("")) && 
                             !(ld.getParameters() == null ) )
                       {
                          alteredLocation = lmsManifestHandler.addParameters(alteredLocation,ld.getParameters());
                       }
                    } else
                    {                    	                    	
                       // Create the altered location (with decoded url)
                    	alteredLocation=miUrl +  URLDecoder.decode(ld.getLocation(),"UTF-8" );
                   	 	if ( !(ld.getParameters().equals("")) && 
                   	 		!(ld.getParameters() == null ) )
                   	 	{
                          alteredLocation = lmsManifestHandler.addParameters(alteredLocation,ld.getParameters());
                   	 	}
                    }	
   	         ld.setLocation(alteredLocation);
   	         newVLD.add(ld);
   	         iv++;
   	        }
   	        //lo metemos en session
   	        request.getSession().setAttribute("launchdataList", newVLD);   	        
   			
   		}else {
   			logger.info(" El Root no es Valido! ");
   		}
   		
   }
   catch (Exception ex)
   {
			logger
			.error("Error en Servicio de visualizador Adl, metodo cargarDatosAdl " + ex);
			logger.error(ex);
   }
	
   }

	@Override
	public void borrarDatosAdl(ActionMapping mapping, BorrarDatosAdlForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		//de ahí hay que borrar 
		String rutaAux= session.getAttribute("webPath") + File.separator + session.getAttribute("idSession");
        File fileAux = new File(rutaAux);
		try {
			UtilesFicheros.eliminar(fileAux);
			logger.info("eliminada la ruta de ficheros!!");
			session.invalidate();
		}catch (Exception e) {
			logger.error("Error en Servicio visualizador, método borrarDatosAdl " + e);
		}
	}

}



