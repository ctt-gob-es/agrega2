// license-header java merge-point
package es.pode.administracion.presentacion.estructuraseducativas.taxonomias.baja;

import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.administracion.presentacion.estructuraseducativas.EstructurasSession;
import es.pode.fuentestaxonomicas.negocio.servicio.ParamEliminarVO;
import es.pode.fuentestaxonomicas.negocio.servicio.SrvEstructurasEducativasService;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.i18n.LocalizacionIdiomaVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



public class BajaTaxonomiasControllerImpl extends BajaTaxonomiasController
{
	private static Logger logger= Logger.getLogger(BajaTaxonomiasControllerImpl.class);
	
	
	
    public final void obtenerIdiomas(
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
			LocalizacionIdiomaVO[] localizadorIdioma =i18n.obtenerIdiomasPlataformaLocalizados(idiomaSelected);
			form.setIdiomaBuscadorBackingList(Arrays.asList(localizadorIdioma), "idLocalizacion", "nombre");
		}catch (Exception e) {
			logger.error("error en BajaTaxonomiasController.obtenerIdiomas ");
		}
		

    }


    public final void bajaTaxonomia(
    		ActionMapping mapping, 
    		BajaTaxonomiaForm form, 
    		HttpServletRequest request, 
    		HttpServletResponse response) 
    throws Exception
    {
    	EstructurasSession estructurasSession=getEstructurasSession(request);
    	SrvEstructurasEducativasService servicio=this.getSrvEstructurasEducativasService();
    	
    	int[] resultado= servicio.eliminarFicherosVdex((ParamEliminarVO[])estructurasSession.getVdexEliminar().toArray(new ParamEliminarVO[0]));
    	boolean eliminados=true;
    	
    	
    	for (int i = 0; i < resultado.length; i++) {
			if(resultado[i]==0)
				eliminados=false;
		}
    	
    	if(eliminados)
    	{
    		this.saveSuccessMessage(request, "estructuras.taxonomias.baja.exito");
    	}else
    	{
   			this.saveSuccessMessage(request, "estructuras.taxonomias.baja.fallo");	
    	}

    	
    	estructurasSession.setVdexEliminar(null);
    }







    public final java.lang.String submit(
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