// license-header java merge-point
package es.pode.administracion.presentacion.estructuraseducativas.tesauros.alta;

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

import es.pode.fuentestaxonomicas.negocio.servicio.ParamVdexVO;
import es.pode.fuentestaxonomicas.negocio.servicio.SrvEstructurasEducativasService;
import es.pode.fuentestaxonomicas.negocio.servicio.TipoVdex;
import es.pode.fuentestaxonomicas.negocio.servicio.VdexVO;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.i18n.LocalizacionIdiomaVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.administracion.presentacion.estructuraseducativas.tesauros.alta.AltaTesaurosController
 */
public class AltaTesaurosControllerImpl extends AltaTesaurosController
{
	private static Logger logger= Logger.getLogger(AltaTesaurosControllerImpl.class);

    /**
     * @see es.pode.administracion.presentacion.estructuraseducativas.tesauros.alta.AltaTesaurosController#nuevoTesauro(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.estructuraseducativas.tesauros.alta.NuevoTesauroForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void nuevoTesauro(
    		ActionMapping mapping, 
    		NuevoTesauroForm form, 
    		HttpServletRequest request, 
    		HttpServletResponse response) 
    throws Exception
    {
    	VdexVO[] resultado=null;
    	List<String> errorIdioma=new ArrayList<String>();
    	List<FormFile> ficheros=new ArrayList<FormFile>();
		String[] idiomasPlataforma = I18n.getInstance().obtenerIdiomasBuscables();
		String separador="::";

		if(form.getFichero0()!=null && form.getFichero0().getFileName()!=null && !form.getFichero0().getFileName().equals("") ) 
    	{
    		if(form.getFichero0().getFileName().toLowerCase().endsWith(idiomasPlataforma[0]+".xml"))
    			ficheros.add(form.getFichero0());
    		else
    			errorIdioma.add(form.getFichero0().getFileName() + separador + idiomasPlataforma[0]);
    	}
    	if(form.getFichero1()!=null && form.getFichero1().getFileName()!=null && !form.getFichero1().getFileName().equals("") ) 
    	{
    		if(form.getFichero1().getFileName().toLowerCase().endsWith(idiomasPlataforma[1]+".xml"))
    			ficheros.add(form.getFichero1());
    		else
    			errorIdioma.add(form.getFichero1().getFileName() + separador + idiomasPlataforma[1]);
    	}
    	if(form.getFichero2()!=null && form.getFichero2().getFileName()!=null && !form.getFichero2().getFileName().equals("") )
    	{
    		if(form.getFichero2().getFileName().toLowerCase().endsWith(idiomasPlataforma[2]+".xml"))
    			ficheros.add(form.getFichero2());
    		else
    			errorIdioma.add(form.getFichero2().getFileName() + separador + idiomasPlataforma[2]);
    	}
    	if(form.getFichero3()!=null && form.getFichero3().getFileName()!=null && !form.getFichero3().getFileName().equals("") )
    	{
    		if(form.getFichero3().getFileName().toLowerCase().endsWith(idiomasPlataforma[3]+".xml"))
    			ficheros.add(form.getFichero3());
    		else
    			errorIdioma.add(form.getFichero3().getFileName() + separador + idiomasPlataforma[3]);
    	}
    	if(form.getFichero4()!=null && form.getFichero4().getFileName()!=null && !form.getFichero4().getFileName().equals("") )
    	{
    		if(form.getFichero4().getFileName().toLowerCase().endsWith(idiomasPlataforma[4]+".xml"))
    			ficheros.add(form.getFichero4());
    		else
    			errorIdioma.add(form.getFichero4().getFileName() + separador + idiomasPlataforma[4]);
    	}
    	if(form.getFichero5()!=null && form.getFichero5().getFileName()!=null && !form.getFichero5().getFileName().equals("") )
    	{
    		if(form.getFichero5().getFileName().toLowerCase().endsWith(idiomasPlataforma[5]+".xml"))
    			ficheros.add(form.getFichero5());
    		else
    			errorIdioma.add(form.getFichero5().getFileName() + separador + idiomasPlataforma[5]);
    	}
    	if(form.getFichero6()!=null && form.getFichero6().getFileName()!=null && !form.getFichero6().getFileName().equals("") )
    	{
    		if(form.getFichero6().getFileName().toLowerCase().endsWith(idiomasPlataforma[6]+".xml"))
    			ficheros.add(form.getFichero6());
    		else
    			errorIdioma.add(form.getFichero6().getFileName() + separador + idiomasPlataforma[6]);
    	}


    	//verificar que el nombre del fichero se corresponda con el idioma.
		

		if(ficheros.size()==0 && errorIdioma.size()==0)
		{
			throw new ValidatorException("{estructuras.tesauros.error.fichero.vacio}");
		}else if(ficheros.size()==0 && errorIdioma.size()>0)
		{
			throw new ValidatorException("{estructuras.tesauros.error.fichero.idioma.general}" );
		}else if(errorIdioma.size()>0)
		{
			for (int i = 0; i < errorIdioma.size(); i++) {
				String[] lista= errorIdioma.get(i).split(separador);
				lista[1] = I18n.getInstance().obtenerIdiomaIso(lista[1] , LdapUserDetailsUtils.getIdioma());
				this.saveErrorMessage(request, "estructuras.tesauros.error.fichero.idioma" , lista );
			}
		}
		
		
		List<ParamVdexVO> arrayParam=new ArrayList<ParamVdexVO>();
		InternetHeaders ih = new InternetHeaders();
		MimeBodyPart mbp = null;
		DataSource source = null;
		DataHandler dh = null;
		for (int i = 0; i < ficheros.size(); i++) 
		{
			try
			{
				FormFile ff=ficheros.get(i);
				mbp = new MimeBodyPart(ih, ff.getFileData());
				source = new MimePartDataSource(mbp);
				dh = new DataHandler(source);
		    	arrayParam.add(new ParamVdexVO(dh , ff.getFileName()));
				
			}catch (Exception e) {
				if (logger.isDebugEnabled()) {logger.debug("error al cargar la lista de paramVDEXVO");}
			}
		}
		try{
	    	SrvEstructurasEducativasService servicio= this.getSrvEstructurasEducativasService();
	    	resultado = servicio.subirFicherosVdex(arrayParam.toArray(new ParamVdexVO[0]) , TipoVdex.TESAUROS_BACK);

	    	//guardar mensajes de error/exito
			for (int i = 0; i < resultado.length; i++) 
			{
				String[] nombreVdex= new String[1];
				nombreVdex[0]= ficheros.get(i).getFileName();
		    	if(resultado[i].getCodigoError()!=null && !resultado[i].getCodigoError().equals(""))
		    	{
		    		if(resultado[i].getCodigoError().equals("10"))
		    		{
		    			if(resultado[i].getErroresParseo()!=null && 
		    				resultado[i].getErroresParseo().length>0 )
		    			{
		    				this.saveErrorMessage(request, "estructuras.error.alta.parseo." + resultado[i].getErroresParseo()[0] ,nombreVdex);
		    			}else
		    				this.saveErrorMessage(request, "estructuras.error.alta." + resultado[i].getCodigoError() ,nombreVdex);
		    		}else if(resultado[i].getCodigoError().equals("5") || resultado[i].getCodigoError().equals("6"))
		    		{
		    			nombreVdex= new String[2];
						nombreVdex[0] = ficheros.get(i).getFileName();
						nombreVdex[1] = resultado[i].getVocabNameDuplicado();
		    			this.saveErrorMessage(request, "estructuras.error.alta." + resultado[i].getCodigoError(),nombreVdex);
		    		}else
		    		{
		    			this.saveErrorMessage(request, "estructuras.error.alta." + resultado[i].getCodigoError(),nombreVdex);
		    		}
		    	}else{
		    		this.saveSuccessMessage(request, "estructuras.tesauros.alta.exito",nombreVdex);
		    	}
			}
			

			
		}catch(Exception e)
		{
			if (logger.isDebugEnabled()) {logger.debug("error al dar de alta lista de taxonomias");}
			this.saveErrorMessage(request, "estructuras.error.alta.0");
		}
	

    }

	public void obtenerIdiomas(
			ActionMapping mapping, 
			ObtenerIdiomasForm form, 
			HttpServletRequest request, 
			HttpServletResponse response) 
	throws Exception 
	{
		form.setIdiomaBuscador("02");
		try{
			String idiomaSelected = LdapUserDetailsUtils.getIdioma();
			I18n i18n = I18n.getInstance();
			form.setIdiomasIso(i18n.obtenerIdiomasBuscables());
			LocalizacionIdiomaVO[] localizadorIdioma =i18n.obtenerIdiomasPlataformaLocalizados(idiomaSelected);
			form.setIdiomaBuscadorBackingList(Arrays.asList(localizadorIdioma), "idLocalizacion", "nombre");
		}catch(Exception e)
		{
			logger.error("error en AltaTesaurosControllerImpl.obtenerIdiomas");
		}
	}

	public String submit(
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


}