/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.buscador.presentacion.seleccionTipoBusqueda;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.buscador.presentacion.BuscarSession;
import es.pode.buscador.presentacion.basico.detallar.DetallarControllerImpl;
import es.pode.buscador.presentacion.basico.listar.ListarControllerImpl;
import es.pode.buscar.negocio.administrar.servicio.NodoVO;
import es.pode.buscar.negocio.administrar.servicio.SrvNodoService;
import es.pode.fuentestaxonomicas.negocio.servicio.EstructuraVdexVO;
import es.pode.soporte.constantes.ConstantesAgrega;



/**
 * @see es.pode.buscador.presentacion.seleccionTipoBusqueda.seleccionTipoBusquedaController
 */
public class seleccionTipoBusquedaControllerImpl extends seleccionTipoBusquedaController
{

	private static Logger logger = Logger.getLogger(ListarControllerImpl.class);
//	private java.util.Properties pSpringProperties = null;	
	public static final String CARGAR_TESAURO = "CARGAR_TESAURO";	
	public static final String CARGAR_ARBOL = "CARGAR_ARBOL";
	public final static String TITULOTESAURO = "navegar.tesauro.nombre";
	
	private static final String FICHERO = "application-resources";
	private static final String EUSKERA = "eu";
	private static final String INGLES = "en";
	private static final String SPACE = " ";
	private static final String MENSAJE_NO_SELEC_BUSQ="seleccionTipoBusqueda.exception.noSelecBusqueda";
	

    /**
     * @see es.pode.buscador.presentacion.seleccionTipoBusqueda.seleccionTipoBusquedaController#prepararConsulta(org.apache.struts.action.ActionMapping, es.pode.buscador.presentacion.seleccionTipoBusqueda.PrepararConsultaForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void prepararConsulta(ActionMapping mapping, es.pode.buscador.presentacion.seleccionTipoBusqueda.PrepararConsultaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	this.setBuscarSession(request, new BuscarSession());
    	form.setTipoBusquedaArbol("01");
    	form.setTipoNavegacion("01");
    	form.setTipoLayoutBuscador(DetallarControllerImpl.LAYOUT_BUSCADOR);    	
//		llamada traer nodos
    	try {
			SrvNodoService nodo = this.getSrvNodoService();
			NodoVO[] listaNodos = nodo.listarNodos();
			if(listaNodos == null || listaNodos.length == 0) form.setMostrarAmbito(false);
			else form.setMostrarAmbito(true);
    	} catch (Exception e) {
			logger.error("seleccionTipoBusquedaControllerImpl - prepararConsulta:Error recuperando el locale",e);
		}
    	
    	String idiomaNavegacion = ((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
    	
//    	String nomTesauro = this.getPropertyValue("nombreFichTesauros");
    	log("Se obtiene el nombre del tesauro con el idioma de navegacion ["+idiomaNavegacion+"]");
		Locale locale = null;
		try {
			locale = devuelveLocale(request);
		} catch (Exception e) {
			logger.error("seleccionTipoBusquedaControllerImpl -prepararConsulta:Error recuperando el locale",e);
		}
//		String tituloTesauro = "";
//		String vocabNameTesauro = this.getSrvTesaurosServices().obtenerVocabName(nomTesauro, idiomaNavegacion);
////		Se forma el titulo del tesauro. El orden cambia dependiendo del idioma		 
		String literalTituloTesauro = seleccionTipoBusquedaControllerImpl.getResource(TITULOTESAURO, FICHERO, locale);	
//		if (EUSKERA.equals(idiomaNavegacion) || INGLES.equals(idiomaNavegacion))tituloTesauro = vocabNameTesauro + SPACE + literalTituloTesauro;
//		else tituloTesauro = literalTituloTesauro + SPACE + vocabNameTesauro;
//		form.setTituloTesauro(tituloTesauro);
		
		
//		Combo tipo navegación 
     	try{
     		//Obtenemos las taxonomias y tesauros presentes en la plataforma
     		EstructuraVdexVO[] taxonomias = this.getSrvTaxonomiaService().obtenerTaxonomias(idiomaNavegacion);
     		EstructuraVdexVO[] tesauros = this.getSrvTesaurosServices().obtenerTesauros(idiomaNavegacion);
     		
     		int tamañoTax = taxonomias == null?0:taxonomias.length;
     		int tamañoTes = tesauros == null?0:tesauros.length;
     		int tamañoCombo = tamañoTax + tamañoTes;//tamaño es número de taxonomias mas número de tesauro
     		String[] tipoNavegacionLabel = new String[tamañoCombo]; 
     		String[] tipoNavegacionValue = new String[tamañoCombo];
     		//Añadimos taxonomias
     		for(int i=0;i<tamañoTax;i++){
     			tipoNavegacionLabel[i]=taxonomias[i].getVocabName();
     			tipoNavegacionValue[i]=taxonomias[i].getVocabIdentifier();
     			
     		}
     		
     		//Añadimos tesauros
     		for(int i=0;i<tamañoTes;i++){
     			String vocabNameTes = tesauros[i].getVocabName();
     			if (EUSKERA.equals(idiomaNavegacion) || INGLES.equals(idiomaNavegacion)) tipoNavegacionLabel[tamañoTax + i]= vocabNameTes + SPACE + literalTituloTesauro;
     			else tipoNavegacionLabel[tamañoTax + i]= literalTituloTesauro + SPACE + vocabNameTes;
     			 
     			tipoNavegacionValue[tamañoTax + i]="TES_" + tesauros[i].getVocabIdentifier();  			
     		}
     		
     		//SE CARGA EL COMBO CON LAS TAXONOMIAS Y TESAUROS OBTENIDOS
     		form.setTipoNavegacionLabelList(tipoNavegacionLabel);
     		form.setTipoNavegacionValueList(tipoNavegacionValue);

     	}catch(Exception ex){
     		logger.error("seleccionTipoBusquedaControllerImpl - prepararConsulta: Error obteniendo valores para el combo de TipoNavegacion", ex);
     		form.setTipoNavegacionLabelList(new String[0]);
     		form.setTipoNavegacionValueList(new String[0]);
     	}

     }

    /**
     * @see es.pode.buscador.presentacion.seleccionTipoBusqueda.seleccionTipoBusquedaController#decidirSalida(org.apache.struts.action.ActionMapping, es.pode.buscador.presentacion.seleccionTipoBusqueda.DecidirSalidaForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String decidirSalida(ActionMapping mapping, es.pode.buscador.presentacion.seleccionTipoBusqueda.DecidirSalidaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    		if(form.getTipoBusquedaArbol()==null || form.getTipoBusquedaArbol().equals(""))form.setTipoBusquedaArbol("02");
    		BuscarSession sesion = this.getBuscarSession(request);
    	//	sesion.setTipoBusquedaArbol(form.getTipoBusquedaArbol());
    		sesion.setTituloTesauro(form.getTituloTesauro());
    		String tipoNavegacion = form.getTipoNavegacion();
    		if (tipoNavegacion!=null && tipoNavegacion.startsWith("TES_")){
    			form.setTipoNavegacion(tipoNavegacion.replace("TES_", ""));
    			if(form.getTipoBusquedaArbol().equalsIgnoreCase("01"))
    			{
    				sesion.setTipoBusqueda("07");
    			}
    			else
    				sesion.setTipoBusqueda("08");
    			return CARGAR_TESAURO;
    		}else if (tipoNavegacion == null || tipoNavegacion.trim().equals("")){
    			this.saveErrorMessage(request, MENSAJE_NO_SELEC_BUSQ);
    			return "NO_SELEC";
    		}else 
    			if(form.getTipoBusquedaArbol().equalsIgnoreCase("01"))
    			{
    			sesion.setTipoBusqueda("05");	
    			}
    			else
    			{
    				sesion.setTipoBusqueda("06");
    			}
    			return CARGAR_ARBOL;    	
    }
    
    //	MÉTODOS PARA PINTAR LOS MENSAJES INTERNACIONALIZADOS
	public static String getResource(String key, String baseName, Locale locale)
	{
        String recurso = "";
        ResourceBundle theResourceBundle = getResource(baseName,locale);
        try{
        	if (theResourceBundle!=null){
               recurso = theResourceBundle.getString(key);
           }
        }catch (MissingResourceException mre){
        	recurso = key;
        }
        return recurso;
    }
	
	public static ResourceBundle getResource(String baseName, Locale locale)
	{
        try{
        	return ResourceBundle.getBundle(baseName,locale);
            
        }catch (MissingResourceException mre){
        		locale = new Locale("","");
        		return ResourceBundle.getBundle(baseName,locale);
             
        }
    }
	
	private Locale devuelveLocale(HttpServletRequest request) throws Exception
	{	
		
		Locale locale=null;
//		if (request instanceof HttpServletRequest) {
			locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
			
//		}
//		else{ 	    					
//			try {
//				locale=new Locale(I18n.getInstance().obtenerIdiomaDefectoPlataforma());
//			} catch (Exception e) {								
//				logger.error("DevuelveLocale-- Error recuperando el locale del request", e);
//			}
//		}
		return locale;
	}

	private void log(String traza){
		if (logger.isDebugEnabled())logger.debug(traza);
	}
}