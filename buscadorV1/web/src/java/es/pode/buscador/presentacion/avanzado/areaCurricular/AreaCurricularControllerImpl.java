// license-header java merge-point
package es.pode.buscador.presentacion.avanzado.areaCurricular;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.buscador.presentacion.BuscarSession;
import es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO;
import es.pode.soporte.constantes.ConstantesAgrega;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @see es.pode.buscador.presentacion.avanzado.areaCurricular.AreaCurricularController
 */
public class AreaCurricularControllerImpl extends AreaCurricularController{

	private static final long serialVersionUID = -8190956967834999990L;
	
	private java.util.Properties pSpringProperties = null;

	private static Logger logger = Logger.getLogger(AreaCurricularControllerImpl.class);
	
	public final static String MENSAJE_GENERICO_BUSQUEDA = "listar.odecu.mostrar.resultados.consulta.cabecera.errorBusqueda";
	public final static String MENSAJE_NO_SELEC_TAXON="{taxonomias.noSelec.taxon}";
	
    /**
     * @see es.pode.buscador.presentacion.avanzado.areaCurricular.AreaCurricularController#obtenerAreasCurriculares(org.apache.struts.action.ActionMapping, es.pode.buscador.presentacion.avanzado.areaCurricular.ObtenerAreasCurricularesForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerTaxonomias(ActionMapping mapping, es.pode.buscador.presentacion.avanzado.areaCurricular.ObtenerTaxonomiasForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
    	try {
    		TaxonVO[] taxVO = null;
    		Object[] taxVORutaPadre = null;
    		String idioma=((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
    		
    		String taxonomia = form.getTaxonomias();//taxonomia seleccionada
    		
//  		Detectamos si se ha pulsado sobre un identificador de la lista de taxones desplegada
    		if (form.getId() == null || form.getId().equals("")){
    			logger.debug("Cargando taxones de vacio en busqueda avanzada.");
    			taxVO = this.getSrvTaxonomiaService().obtenerTaxonomia(taxonomia, idioma);
    			taxVORutaPadre = new TaxonVO[0];
    			if(logger.isDebugEnabled())logger.debug("Recuperados["+taxVO.length+"] taxones de vacio en busqueda avanzada.");
    		}else{
    			if(logger.isDebugEnabled())logger.debug("Cargando taxones del identificador["+form.getId()+"] en busqueda avanzada.");
    			taxVO = this.getSrvTaxonomiaService().obtenerNodos(form.getId(),taxonomia, idioma);
    			List rutaPadrevo = Arrays.asList(this.getSrvTaxonomiaService().obtenerTaxonPath(form.getId(), taxonomia, idioma));
    			if(logger.isDebugEnabled())logger.debug("Recuperados["+taxVO.length+"] taxones de del identificador["+form.getId()+"] en busqueda avanzada.");
    			Collections.reverse(rutaPadrevo);
    			taxVORutaPadre = rutaPadrevo.toArray();
    		}
    		if(logger.isDebugEnabled())logger.debug("Cargados["+taxVO.length+"] taxones del identificador ["+(form.getId() == null?"null":form.getId())+"] en busqueda avanzada.");
			form.setNodosAsArray(taxVO);
			form.setRutaArbolAsArray(taxVORutaPadre);
			//se obtiene el nombre de la taxonomia seleccionada
			if(taxVO!=null && taxVO.length>0){
				String taxNombre = taxVO[0].getVocabName();
				form.setTaxNombre(taxNombre);
			}
			
			
    	} catch (Exception e) {
			logger.error("ERROR: Error cargando taxonomias de ["+this.getPropertyValue("nombreAreaCurricularTax")+"] en busqueda avanzada. - ", e);
			saveErrorMessage(request, MENSAJE_GENERICO_BUSQUEDA);
		}
     }

    /**
     * @see es.pode.buscador.presentacion.avanzado.areaCurricular.AreaCurricularController#volcarAreaCurricularSesion(org.apache.struts.action.ActionMapping, es.pode.buscador.presentacion.avanzado.areaCurricular.VolcarAreaCurricularSesionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void volcarTaxonomiaSesion(ActionMapping mapping, es.pode.buscador.presentacion.avanzado.areaCurricular.VolcarTaxonomiaSesionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{

    		
    		if(form.getTaxonSelec()!=null && !form.getTaxonSelec().trim().equals("")){
    	    	try{
	        		BuscarSession sesion = this.getBuscarSession(request);
	        		if ( sesion == null ) // si la sesion no existe (raro) la creamos y metemos el dato.
	        		sesion = new BuscarSession();

	        		//Metemos en la sesion las rutas taxonómicas seleccionadas hasta el momento
	        		//mas la que acabamos de seleccionar
		    		String newRuta = form.getTaxonomias() + "$" + form.getTaxonSelec();
		    		String rutas = sesion.getEnlaceTaxSelec()==null?"":sesion.getEnlaceTaxSelec();
		       		rutas = rutas.equals("")?newRuta:rutas + "*" + newRuta;
		       		sesion.setEnlaceTaxSelec(rutas);
	        	}catch (Exception e){
	        		logger.error("ERROR: Error al volcar el area curricular en sesion - ", e);
	        	} 
       		}else{
       			throw new ValidatorException(MENSAJE_NO_SELEC_TAXON);
       		}

   	
     }

    private String getPropertyValue(String sKey) throws IOException {
		InputStream fIsSpringProperties = this.getClass().getResourceAsStream("/spring_buscador.properties");
		if (this.pSpringProperties == null) {
			pSpringProperties = new java.util.Properties();
			pSpringProperties.load(fIsSpringProperties);
		}
		fIsSpringProperties.close();
		logger.debug("Propiedad recuperada: " + sKey + " : "+ pSpringProperties.getProperty(sKey));
		// devolvemos la propiedad
		return pSpringProperties.getProperty(sKey);
	}
  }