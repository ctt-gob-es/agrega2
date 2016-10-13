/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.buscador.presentacion.basico.descargar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
//import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.buscador.presentacion.BuscarSession;
import es.pode.entregar.negocio.servicios.PaquetePifVO;
import es.pode.entregar.negocio.servicios.SrvEntregarService;
import es.pode.entregar.negocio.servicios.TipoPifVO;
import es.pode.localizador.negocio.servicios.LocalizadorVO;
import es.pode.localizador.negocio.servicios.SrvLocalizadorService;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.buscador.presentacion.basico.descargar.DescargarController
 */
public class DescargarControllerImpl extends DescargarController
{ 
  
	private java.util.Properties pSpringProperties = null;

	private static Logger logger = Logger.getLogger(DescargarControllerImpl.class);

	private final static String MENSAJE_ERROR_DESCARGA = "descargar.odecu.errorDescargandoFicheros";
	
//    private static final int BUFFER_SIZE = 2048;
    
    public final static String FORMATO_POR_DEFECTO = "descargar.formatos.CONTENIDOS_VALUE";
    
	private final String FILE_SEPARATOR="/";
	
	private final String LICENCIA_NAME="licencia.txt";
    
    private static String LOCALIZADOR;

    
	/**
     * @see es.pode.buscador.presentacion.basico.descargar.DescargarController#descargarFicheroODE(org.apache.struts.action.ActionMapping, es.pode.buscador.presentacion.basico.descargar.DescargarFicheroODEForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void descargarFicheroODE(ActionMapping mapping, es.pode.buscador.presentacion.basico.descargar.DescargarFicheroODEForm form, HttpServletRequest request, HttpServletResponse response) throws Exception

    {
    	BuscarSession sesion = this.getBuscarSession(request);
    	if(sesion.getTextoLicencia()!=null && !sesion.getTextoLicencia().equals("")){
    		Boolean aceptar=form.getLicenciaAceptar();
    		log("La licencia aceptada es "+aceptar);
    		if(aceptar==null){
    			throw new ValidatorException("{descargar.formatos.aceptarObligatorio}");
    		}
    	}
    	sesion.setTextoLicencia("");
	    PaquetePifVO resultadoEntregar = new PaquetePifVO();	
	//     REALIZAMOS EL TRATAMIENTO DE LOS LITERALES PARA PODER INTRODUCIRLOS EN EL CÓDIGO HTML
	
	    //SimpleDateFormat curFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        //java.util.Date dateObj = null;
	    
	    try{	
		    String literalFormato = getPropertyValue(form.getFormato());	
		    log("DescargarControllerImpl - descargarFicheroODE: Descargar ODE con identificadorODE ["+form.getIdentificadorODE()+"] y formato["+literalFormato+"]");	
		    SrvEntregarService entregarService = this.getSrvEntregarService();
		    
		    if(literalFormato.equals("IMG")) {
				String pathFicheroGenerado=entregarService.obtenerRecursoUnicoDelODE(form.getIdentificadorODE());
				log("DescargarControllerImpl - descargarFicheroODE: URL a descargar=[" +pathFicheroGenerado+"]" );
				String p[]=pathFicheroGenerado.split(File.separator);
				String nombreFichero = p[p.length-1];
				String extension = nombreFichero.substring(nombreFichero.lastIndexOf("."), nombreFichero.length()-1);
				response.setContentType("application/"+extension+"\"");	
				response.setHeader("Content-Disposition", "attachment;filename=\""+nombreFichero+"\"");
				response.setHeader("Cache-Control", "public");	
				response.addDateHeader("Expires", System.currentTimeMillis()+(24*60*60*1000));
				response.sendRedirect("http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+pathFicheroGenerado);
			
		    } else if(literalFormato.equals("PDF")) {
				String pathFicheroGenerado=entregarService.generarPDF(form.getIdentificadorODE());
				log("DescargarControllerImpl - descargarFicheroODE: URL a descargar=[" +pathFicheroGenerado+"]" );
				String titulo = form.getIdentificadorODE();
				response.setContentType("application/pdf");	
				response.setHeader("Content-Disposition", "attachment;filename=\""+titulo+".pdf\"");
				response.setHeader("Cache-Control", "public");	
				response.addDateHeader("Expires", System.currentTimeMillis()+(24*60*60*1000));
				response.sendRedirect("http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+pathFicheroGenerado);
			
		    } else {
				resultadoEntregar = entregarService.generarPaquetePIFTipoPIF(new TipoPifVO(form.getIdentificadorODE(), literalFormato, form.getIdioma()));
				log("DescargarControllerImpl - descargarFicheroODE: URL a descargar=["+resultadoEntregar.getHref()+"] del ODE=["+form.getTitulo()+"]");	
				String titulo = (form.getTitulo()!=null)?form.getTitulo().trim().replaceAll(" ", "_"):form.getIdentificadorODE();	
				response.setContentType("application/zip");	
				response.setHeader("Content-Disposition", "attachment;filename=\""+titulo+".zip\"");	
				response.setHeader("Cache-Control", "public");	
				response.addDateHeader("Expires", System.currentTimeMillis()+(24*60*60*1000));	
				response.sendRedirect("http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+resultadoEntregar.getHref());	
			}

	    } catch (Exception e) {
	    	logger.error("DescargarController Exce" + e.getMessage());
	        logger.error("DescargarControllerImpl - descargarFicheroODE ERROR: Error generando el fichero ZIP con identificador["+form.getIdentificadorODE()+"] error devuelto ResultadoValidacion="+resultadoEntregar.getResultadoValidacion().getResultadoValidacion()+" Ruta manifest="+resultadoEntregar.getResultadoValidacion().getRutaManifest()+" Es valido manifest="+resultadoEntregar.getResultadoValidacion().getEsValidoManifest());	
		    saveErrorMessage(request,MENSAJE_ERROR_DESCARGA);	
		    
	    } catch (Throwable te) {
	    	logger.error("DescargarControllerImpl - Throwable " );
	        logger.error("DescargarControllerImpl - Throwable " + te.getMessage()	);
		    	
	    }
	    
    }

    
	/* (non-Javadoc)
	 * @see es.pode.buscador.presentacion.basico.descargar.DescargarController#cargarTiposFormato(org.apache.struts.action.ActionMapping, es.pode.buscador.presentacion.basico.descargar.CargarTiposFormatoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void cargarTiposFormato(ActionMapping mapping, CargarTiposFormatoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			//TODO Este metodo tiene que preguntar al servicio de entregar los formatos que tiene disponibles para la
			//descarga de ODEs
			String idioma = "";
			try{
				idioma=LdapUserDetailsUtils.getIdioma();
				if (idioma == null)	idioma = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
			} catch (Exception e) {
				logger.debug("DescargarControllerImpl - cargarTiposFormato: Error en al obtener idioma usuario LDAP, se recoge idioma de fichero propiedades.");
				idioma = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
			}
			//Recuperamos el valor del titulo de la sesion
			BuscarSession sesion = this.getBuscarSession(request);
			if (sesion == null) {
				sesion = new BuscarSession();
				this.setBuscarSession(request, sesion);
				log("Sesion de busqueda creada en busqueda avanzada. La sesion estaba vacia.");
			}
			form.setBusquedaSimpleAvanzada(sesion.getBusquedaSimpleAvanzada());
			form.setTitulo(sesion.getTitulo());
			form.setFormato(FORMATO_POR_DEFECTO);
			
			// 17102012 
			// Se modifica para que prevalezcan las licencias que están en el servidor sobre la del ODE
			// Si no puede localizar la licencia en el servidor muestra la del ODE
			String textoLicencia="";
			try	{
				if(form.getIdLicencia()!=null || !form.getIdLicencia().isEmpty())
					textoLicencia=this.cargarTextoLicenciaServidor(form.getIdLicencia());
			} catch (Exception e) {
				logger.info("No es posible localizar la licencia en el servidor : " + e.getMessage());
			}
			
			// Si está vacia es que no se ha podido cargar la del servidor.
			// Se procede a cargar la del ODE.
			if (textoLicencia==null || textoLicencia.equals("")) {			
				Boolean existeLicencia=this.existeLicencia(form.getIdentificadorODE());
				if(existeLicencia.booleanValue()){
					textoLicencia=this.cargarTextoLicencia();
				}
			}
			sesion.setTextoLicencia(textoLicencia.replaceAll("ï»¿", ""));
			
			form.setMostrarDescargaRecursoUnico(false);
			if(this.getSrvEntregarService().odeConRecursoDirectamenteDescargable(form.getIdentificadorODE())) {
				form.setMostrarDescargaRecursoUnico(true);
			}
			
		}catch (Exception e){
			logger.error("DescargarControllerImpl - cargarTiposFormato ERROR: Error al cargar los tipos del formato",e);
		}
	}

	
	private String getPropertyValue(String sKey) throws IOException {
		InputStream fIsSpringProperties = this.getClass().getResourceAsStream(
				"/spring_buscador2.properties");
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
	
	
	private void log(String traza){
		if (logger.isDebugEnabled())logger.debug(traza);
	}


	private String cargarTextoLicencia() throws Exception {
		String[] encodingTypes = {"ISO-8859-1",
                "ISO-8859-2",
                "ISO-8859-3",
                "ISO-8859-4",
                "ISO-8859-5",
                "ISO-8859-6",
                "ISO-8859-7",
                "ISO-8859-8",
                "ISO-8859-9",
                "ISO-8859-13",
                "ISO-8859-15",
                "UTF-16",
                "UTF-8",
                "windows-1250",
                "windows-1251",
                "windows-1252",
                "windows-1253",
                "windows-1254",
                "windows-1255",
                "windows-1256",
                "windows-1257",
                "windows-1258"};
		try {
	        for (String encType : encodingTypes) {
	            String licencia = decodeFile(LOCALIZADOR, encType);
	            if (licencia.contains("á")
	                    || licencia.contains("é")
	                    || licencia.contains("í")
	                    || licencia.contains("ó")
	                    || licencia.contains("ú")) {
	                return licencia;
	            }
	        }
	        return decodeFile(LOCALIZADOR, null);
        } catch (FileNotFoundException fnfEx) {
        	logger.error("El fichero de licencia del ODE no se encuentra");
        	return "";
        } catch (IOException ioEx) {
        	logger.error("Error de entrada salida al intentar leer el fichero de licencia");
        	return "";
        }
	}

	
	private String cargarTextoLicenciaServidor(String identificadorLicencia) throws Exception {
		
		String textoLicencia=null;
		try {
			if (logger.isDebugEnabled()) logger.debug("DescargarControllerImpl - cargarTextoLicencias. IdLicencia :" + identificadorLicencia);
			textoLicencia =  obtenerTextoLicencia(identificadorLicencia);
			if (logger.isDebugEnabled()) logger.debug("DescargarControllerImpl - cargarTextoLicencias. Licencia texto : " + textoLicencia);
		} catch (Exception ex) {
			logger.error("DescargarControllerImpl - Error al obtener la licencia ",ex);
			throw new Exception("Error al obtener la licencia", ex);
		}
		return textoLicencia;
	}
	

	private String obtenerTextoLicencia(String identificadorLicencia) throws Exception{
		String urlLicencias=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.URL_LICENCIAS);
		String urlEntero=urlLicencias+this.FILE_SEPARATOR+identificadorLicencia+this.FILE_SEPARATOR+this.LICENCIA_NAME;
		
		if (logger.isDebugEnabled()) logger.debug("El url entero es "+urlEntero);
		File localizador=new File(urlEntero);
		String texto="";
		String texto2="";
		try{
			BufferedReader br = new BufferedReader(new FileReader(localizador));
			while ((texto=br.readLine()) != null) {
				if(texto2!=null && !texto2.equals("")){
					String lineSep = System.getProperty("line.separator");//Asi obtiene el salto de linea dependiendo la plataforma.
				//						 String lineSep="\n";
					texto2 = texto2+lineSep+texto;
				}else{
					texto2=texto.trim();
				}
			}	
	 		br.close();
		}catch(IOException ex){
			logger.error("Error  obteniendo el texto de la licencia " +ex);
		}
		return texto2;
	}
	
	
    private String decodeFile(String filePath, String encType) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(filePath);
        InputStreamReader isr = null;
        if (encType == null) { 
        	isr = new InputStreamReader(fis);
        }
        else {
        	isr = new InputStreamReader(fis, encType);
        }
        Reader in = new BufferedReader(isr);
        int ch;
        StringBuffer buffer = new StringBuffer();
        while ((ch = in.read()) > -1) {
            buffer.append((char) ch);
        }
        in.close();
        return buffer.toString();
    }	

    
	private Boolean existeLicencia(String identificadorODE) throws Exception {
		Boolean vuelta=false;
		try{
			 SrvLocalizadorService localizadorService = this.getSrvLocalizadorService();
			 LocalizadorVO localizar=localizadorService.consultaLocalizador(identificadorODE);
			 String pathLocalizador = localizar.getPath();
			 logger.debug("El localizador del ode es "+ pathLocalizador);
			 LOCALIZADOR=pathLocalizador+"/licencia.txt";
			 File fileLicencia=new File(LOCALIZADOR);
			 
			 if(fileLicencia.exists()){
				 logger.debug("Existe el fichero de texto de la licencia "+fileLicencia.getPath());
	
		    	vuelta=Boolean.TRUE;
			 }
		}catch(Exception e){
			logger.error("Error al obtener el texto de la licencia");
		}
		return vuelta;
	}
}