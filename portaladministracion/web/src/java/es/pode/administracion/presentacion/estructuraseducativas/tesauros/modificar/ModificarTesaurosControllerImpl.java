/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.administracion.presentacion.estructuraseducativas.tesauros.modificar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimePartDataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import es.pode.administracion.presentacion.estructuraseducativas.EstructurasSession;
import es.pode.administracion.presentacion.estructuraseducativas.taxonomias.modificar.ModificarTaxonomiasControllerImpl;
import es.pode.fuentestaxonomicas.negocio.servicio.ParamVdexVO;
import es.pode.fuentestaxonomicas.negocio.servicio.SrvEstructurasEducativasService;
import es.pode.fuentestaxonomicas.negocio.servicio.TipoVdex;
import es.pode.fuentestaxonomicas.negocio.servicio.VdexListaVO;
import es.pode.fuentestaxonomicas.negocio.servicio.VdexVO;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.i18n.LocalizacionIdiomaVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.administracion.presentacion.estructuraseducativas.tesauros.modificar.ModificarTesaurosController
 */
public class ModificarTesaurosControllerImpl extends ModificarTesaurosController
{

	private static Logger logger= Logger.getLogger(ModificarTesaurosControllerImpl.class);


    public final void modificar(
    		ActionMapping mapping, 
    		ModificarForm form, 
    		HttpServletRequest request, 
    		HttpServletResponse response) 
    throws Exception
    {
    	EstructurasSession sesion = getEstructurasSession(request);
    	List ficheros = sesion.getVdexSubir();
		VdexVO[] resultado;
		
    	//generar los datahandlers
		List<ParamVdexVO> arrayParam=new ArrayList<ParamVdexVO>();
		InternetHeaders ih = new InternetHeaders();
		MimeBodyPart mbp = null;
		DataSource source = null;
		DataHandler dh = null;
		for (int i = 0; i < ficheros.size(); i++) 
		{
			try
			{
				FormFile ff=(FormFile) ficheros.get(i);
				mbp = new MimeBodyPart(ih, ff.getFileData());
				source = new MimePartDataSource(mbp);
				dh = new DataHandler(source);
		    	arrayParam.add(new ParamVdexVO(dh , ff.getFileName()));
				
			}catch (Exception e) {
				logger.error("error al cargar la lista de paramVDEXVO", e);
			}
		}

		// llamar al servicio
		try{
	    	SrvEstructurasEducativasService servicio= this.getSrvEstructurasEducativasService();
	    	
			if(TipoVdex.TESAURO.toString().equals(form.getTipo()))
			{
				if(form.isActualizarVigente())
				{
					resultado= servicio.actualizarVigente(
										arrayParam.toArray(new ParamVdexVO[0]),
		    							TipoVdex.TESAURO );
				}else{
					resultado= servicio.actualizarFicherosVdex(
						    			arrayParam.toArray(new ParamVdexVO[0]),
										TipoVdex.TESAURO ,
										form.getIdentificadorVdex());
				}
			}else
				resultado = servicio.actualizarFicherosVdex(arrayParam.toArray(new ParamVdexVO[0]), 
													TipoVdex.TESAUROS_BACK , 
													form.getIdentificadorVdex());
			
			// 16042013 
			// Soicitamos al otro nodo del cluster que actualice sus taxonomias y tesauros
			try
			{
				if (logger.isDebugEnabled())
					logger.debug("Vamos a llamar al otro nodo para recargar Taxanomías y Tesauros");
								
				boolean bHayNodos=true;
				SrvEstructurasEducativasService servicioRemoto;
				
				int nNodo=0;
				while (bHayNodos) {
					servicioRemoto = this.getSrvEstructurasEducativasClusterService(nNodo);
					
					if (servicioRemoto!=null)
					{
						servicioRemoto.recargarTaxonomiasTesauros();
					}
					else
					{
						bHayNodos=false;
					}
					nNodo++;
				}								
			}catch (Exception e) {				
				logger.error("Error al llamar al otro nodo para recargar Taxanomías y Tesauros :",e);
			}
			
	
	    	//guardar mensajes de error/exito
			for (int i = 0; i < resultado.length; i++) 
			{
				String[] nombreVdex= new String[1];
				nombreVdex[0]= ((FormFile) ficheros.get(i)).getFileName();
		    	if(resultado[i].getCodigoError()!=null && !resultado[i].getCodigoError().equals(""))
		    	{
		    		
		    		if(resultado[i].getCodigoError().equals("10"))
		    		{
		    			if(resultado[i].getErroresParseo()!=null && 
		    				resultado[i].getErroresParseo().length>0 )
		    			{
		    				this.saveErrorMessage(request, "estructuras.error.alta.parseo." + resultado[i].getErroresParseo()[0] ,nombreVdex);
		    			}else
		    				this.saveErrorMessage(request, "estructuras.error.alta.parseo.0",nombreVdex);
		    		}else if(resultado[i].getCodigoError().equals("5") || resultado[i].getCodigoError().equals("6"))
		    		{
		    			nombreVdex= new String[2];
						nombreVdex[0] = ((FormFile) ficheros.get(i)).getFileName();
						nombreVdex[1] = resultado[i].getVocabNameDuplicado();
		    			this.saveErrorMessage(request, "estructuras.error.alta." + resultado[i].getCodigoError(),nombreVdex);
		    		}else
		    		{
		    			this.saveErrorMessage(request, "estructuras.error.alta.parseo.0" ,nombreVdex);
		    		}
		    	}else{
		    		this.saveSuccessMessage(request, "estructuras.tesauros.alta.exito",nombreVdex);
		    	}
			}
		}catch (Exception e) {
			logger.error("error al dar de alta lista de tesauros",e);
			this.saveErrorMessage(request, "estructuras.error.alta.0");
		}
		
    }




    public final void guardarEnSesion(
    		ActionMapping mapping, 
    		GuardarEnSesionForm form, 
    		HttpServletRequest request, 
    		HttpServletResponse response) 
    throws Exception
    {
		//verificar que exista un fichero en cada casilla
    	List<String> errorIdioma=new ArrayList<String>();
    	List<FormFile> ficheros=new ArrayList<FormFile>();
    	
    	boolean existeIdiomaEs= false;
		String[] idiomasPlataforma = I18n.getInstance().obtenerIdiomasBuscables();
		if(form.getFichero0()!=null && form.getFichero0().getFileName()!=null && !form.getFichero0().getFileName().equals("") ) 
    	{
    		if(form.getFichero0().getFileName().toLowerCase().endsWith(idiomasPlataforma[0]+".xml"))
    		{
    			ficheros.add(form.getFichero0());
    			if(form.getFichero0().getFileName().toLowerCase().endsWith("es.xml"))
    				existeIdiomaEs=true;
    		}else
    			errorIdioma.add(form.getFichero0().getFileName());
    	}
    	if(form.getFichero1()!=null && form.getFichero1().getFileName()!=null && !form.getFichero1().getFileName().equals("") ) 
    	{
    		if(form.getFichero1().getFileName().toLowerCase().endsWith(idiomasPlataforma[1]+".xml"))
    		{
    			ficheros.add(form.getFichero1());
    			if(form.getFichero1().getFileName().toLowerCase().endsWith("es.xml"))
    				existeIdiomaEs=true;
    		}else
    			errorIdioma.add(form.getFichero0().getFileName());
    	}
    	if(form.getFichero2()!=null && form.getFichero2().getFileName()!=null && !form.getFichero2().getFileName().equals("") )
    	{
    		if(form.getFichero2().getFileName().toLowerCase().endsWith(idiomasPlataforma[2]+".xml"))
    		{
    			ficheros.add(form.getFichero2());
    			if(form.getFichero2().getFileName().toLowerCase().endsWith("es.xml"))
    				existeIdiomaEs=true;
    		}else
    			errorIdioma.add(form.getFichero0().getFileName());
    	}
    	if(form.getFichero3()!=null && form.getFichero3().getFileName()!=null && !form.getFichero3().getFileName().equals("") )
    	{
    		if(form.getFichero3().getFileName().toLowerCase().endsWith(idiomasPlataforma[3]+".xml"))
    		{
    			ficheros.add(form.getFichero3());
    			if(form.getFichero3().getFileName().toLowerCase().endsWith("es.xml"))
    				existeIdiomaEs=true;
    		}else
    			errorIdioma.add(form.getFichero0().getFileName());
    	}
    	if(form.getFichero4()!=null && form.getFichero4().getFileName()!=null && !form.getFichero4().getFileName().equals("") )
    	{
    		if(form.getFichero4().getFileName().toLowerCase().endsWith(idiomasPlataforma[4]+".xml"))
    		{
    			ficheros.add(form.getFichero4());
    			if(form.getFichero4().getFileName().toLowerCase().endsWith("es.xml"))
    				existeIdiomaEs=true;
    		}else
    			errorIdioma.add(form.getFichero0().getFileName());
    	}
    	if(form.getFichero5()!=null && form.getFichero5().getFileName()!=null && !form.getFichero5().getFileName().equals("") )
    	{
    		if(form.getFichero5().getFileName().toLowerCase().endsWith(idiomasPlataforma[5]+".xml"))
    		{
    			ficheros.add(form.getFichero5());
    			if(form.getFichero5().getFileName().toLowerCase().endsWith("es.xml"))
    				existeIdiomaEs=true;
    		}else
    			errorIdioma.add(form.getFichero0().getFileName());
    	}
    	if(form.getFichero6()!=null && form.getFichero6().getFileName()!=null && !form.getFichero6().getFileName().equals("") )
    	{
    		if(form.getFichero6().getFileName().toLowerCase().endsWith(idiomasPlataforma[6]+".xml"))
    		{
    			ficheros.add(form.getFichero6());
    			if(form.getFichero6().getFileName().toLowerCase().endsWith("es.xml"))
    				existeIdiomaEs=true;
    		}else
    			errorIdioma.add(form.getFichero0().getFileName());
    	}


		if(form.isActualizarVigente() && !existeIdiomaEs)
		{
				throw new ValidatorException("{estructuras.taxonomias.error.fichero.idioma.es}");
		}
    	//verificar que el nombre del fichero se corresponda con el idioma.
		if(ficheros.size()==0 && errorIdioma.size()==0)
		{
			throw new ValidatorException("{estructuras.tesauros.error.fichero.vacio}");
		}else if(errorIdioma.size()>0)
		{
			throw new ValidatorException("{estructuras.tesauros.error.fichero.idioma.general}" );
		}

		getEstructurasSession(request).setVdexSubir(ficheros);
    }







    public final void cargarDatos(
    		ActionMapping mapping, 
    		CargarDatosForm form, 
    		HttpServletRequest request, 
    		HttpServletResponse response) 
    throws Exception
    {
		form.setIdiomaBuscador("02");
		try{
			String idiomaSelected = LdapUserDetailsUtils.getIdioma();
			I18n i18n = I18n.getInstance();
			LocalizacionIdiomaVO[] localizadorIdioma =i18n.obtenerIdiomasPlataformaLocalizados(idiomaSelected);
			form.setIdiomaBuscadorBackingList(Arrays.asList(localizadorIdioma), "idLocalizacion", "nombre");
		}catch(Exception e)
		{
			logger.error("error en ModificarTesaurosControllerImpl.cargarDatos" ,e );
		}
		
		try{
			form.setIdiomasIso(I18n.getInstance().obtenerIdiomasBuscables());
			String id= form.getIdentificadorVdex();
			VdexListaVO vdex= this.getSrvEstructurasEducativasService().listarFicherosTesauro(id);
			
			if(vdex!=null)
			{
				form.setListaFicherosAsArray(vdex.getListaFicheros());
				form.setNombre(vdex.getNombre());
				form.setTipo(vdex.getTipo());
			}
			
		}catch (Exception e) {
			logger.error("error al cargar los tesauros a modificar " , e);
		}

     }





    public final String submitAdvertencia(
    		ActionMapping mapping, 
    		SubmitAdvertenciaForm form, 
    		HttpServletRequest request,
    		HttpServletResponse response) 
    throws Exception
    {
		String resultado="";
		String action=form.getAction();
		Locale locale =(Locale)request.getSession().getAttribute("org.apache.struts.action.LOCALE");
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources", locale);

		if(action!=null)
		{
			if(action.equals(i18n.getString("estructuras.aceptar")))
			{
				resultado= "ACEPTAR";
			}else if(action.equals(i18n.getString("estructuras.cancelar")))
				resultado= "CANCELAR";
		}
		return resultado;
    }





    public final String submit(
    		ActionMapping mapping, 
    		SubmitForm form,
    		HttpServletRequest request, 
    		HttpServletResponse response) 
    throws Exception
    {
		String resultado="";
		String action=form.getAction();
		Locale locale =(Locale)request.getSession().getAttribute("org.apache.struts.action.LOCALE");
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources", locale);

		if(action!=null)
		{
			if(action.equals(i18n.getString("estructuras.aceptar")))
			{
				resultado= "ACEPTAR";
			}else if(action.equals(i18n.getString("estructuras.cancelar")))
				resultado= "CANCELAR";
		}
		return resultado;
    }

    /**
     * Returns a reference to the srvEstructurasEducativasService imported service.
     * @return es.pode.fuentestaxonomicas.negocio.servicio.SrvEstructurasEducativasService  
     * @throws java.lang.Exception Exception
     */
    protected final es.pode.fuentestaxonomicas.negocio.servicio.SrvEstructurasEducativasService getSrvEstructurasEducativasClusterService(int nNodo)
        throws java.lang.Exception
    {    	
        String srvEstructurasEducativasServiceFile="importedServices.properties";	    
        java.io.InputStream srvEstructurasEducativasServiceInputStream=ModificarTaxonomiasControllerImpl.class.getClassLoader().getResourceAsStream(srvEstructurasEducativasServiceFile);  
        java.util.Properties srvEstructurasEducativasServiceProperties = new java.util.Properties();
        srvEstructurasEducativasServiceProperties.load(srvEstructurasEducativasServiceInputStream);
        String srvEstructurasEducativasServiceEndPointAddress="";
        srvEstructurasEducativasServiceEndPointAddress=(String) srvEstructurasEducativasServiceProperties.get("srvEstructurasEducativasClusterServicePort" +nNodo);
        if (srvEstructurasEducativasServiceEndPointAddress==null)
        {
        	if (logger.isDebugEnabled())
				logger.debug("No está configurado para acceder a otro nodo. Debe configurarse correctamente el valor de srvEstructurasEducativasClusterServicePort");
        	return null;	
        }        
		es.pode.fuentestaxonomicas.negocio.servicio.SrvEstructurasEducativasServiceService srvEstructurasEducativasService = new es.pode.fuentestaxonomicas.negocio.servicio.SrvEstructurasEducativasServiceServiceLocator();			
        if (srvEstructurasEducativasServiceEndPointAddress.length()>0) 
			((es.pode.fuentestaxonomicas.negocio.servicio.SrvEstructurasEducativasServiceServiceLocator)srvEstructurasEducativasService).setSrvEstructurasEducativasServiceEndpointAddress(srvEstructurasEducativasServiceEndPointAddress);				
    	es.pode.fuentestaxonomicas.negocio.servicio.SrvEstructurasEducativasService port = srvEstructurasEducativasService.getSrvEstructurasEducativasService();	    
        return port;
    }   
}