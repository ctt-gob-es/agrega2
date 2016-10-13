// license-header java merge-point
package es.pode.modificador.presentacion.configurar.cambios.navegarETB;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import es.pode.modificador.negocio.servicio.JerarquiaVO;
import es.pode.modificador.negocio.servicio.TaxonVO;
import es.pode.modificador.negocio.servicio.TaxonomiaVO;
import es.pode.modificador.presentacion.configurar.cambios.CambioSession;
import es.pode.modificador.presentacion.idiomasBuscador.IdiomasBuscadorSingleton;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.modificador.presentacion.configurar.cambios.navegarETB.NavegarETBController
 */
public class NavegarETBControllerImpl extends NavegarETBController
{
	private static final Logger logger = Logger.getLogger(NavegarETBControllerImpl.class);
	private static final MessageResources resources = MessageResources.getMessageResources("application-resources");
    /**
     * @see es.pode.modificador.presentacion.configurar.cambios.navegarETB.NavegarETBController#navegarETB(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.cambios.navegarETB.NavegarETBForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void navegarETB(
			ActionMapping mapping,
			es.pode.modificador.presentacion.configurar.cambios.navegarETB.NavegarETBForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
    	/*
    	 * Se contemplan tres casos:
    	 *   - idTermino != "0.0" y taxonomiaSession.taxonomia.jerarquia==null: Navegacion normal
    	 *   - idTermino != "0.0" y taxonomiaSession.taxonomia.jerarquia!=null y taxonomiaSession.taxonomia.taxonPath == null: Seleccion de ruta padre
    	 *   - idTermino == "0.0": preparar la seleccion de ruta padre 
    	 */
    	String idTermino = form.getIdTermino();
    	TaxonomiaVO taxonomiaSession = this.getTesauroSession(request).getTaxonomia();
    	CambioSession sesion = this.getCambioSession(request);
    	if(logger.isDebugEnabled()) logger.debug("NavegarTesauro con parametros idTermino = " + idTermino + " y taxonomia (session) = "  + taxonomiaSession);
    	TaxonomiaVO taxonomia = null;
    	if("0.0".equals(idTermino)) {
    		if(taxonomiaSession==null) throw new ValidatorException("Error de sesion: tesauroSession == null");
    		form.setIdTermino(null);
    		taxonomia = new TaxonomiaVO();
    		taxonomia.setTaxonPath(null);
    		TaxonVO[] rutasDisponibles = componerRutasDisponibles(taxonomiaSession.getJerarquia());
    		taxonomia.setHijos(rutasDisponibles);
    		taxonomia.setIdioma(taxonomiaSession.getIdioma());
    		taxonomia.setNombreTaxonomia(taxonomiaSession.getNombreTaxonomia());
    		taxonomia.setTaxonomias(taxonomiaSession.getTaxonomias());
    		taxonomia.setJerarquia(taxonomiaSession.getJerarquia());
    	} else {
    		if(taxonomiaSession !=null && taxonomiaSession.getJerarquia()!=null && taxonomiaSession.getTaxonPath()==null) {
    			// Seleccion de ruta padre entre disponibles: idTermino es la posicion en el array de jerarquia
    			int posicion = Integer.parseInt(idTermino);
    			if(logger.isDebugEnabled()) logger.debug("Elegida la ruta " + posicion + " de las rutas padres disponibles");
    			TaxonVO[] rutaElegida = taxonomiaSession.getJerarquia()[posicion].getJerarquia();
    			String idRecuperar = rutaElegida[rutaElegida.length-1].getId();
    			taxonomia = this.getSrvHerramientaModificacion().navegarTesauro(idRecuperar, sesion.getTipoTaxonomia(), LdapUserDetailsUtils.getIdioma());
    			taxonomia.setTaxonPath(rutaElegida);
    			taxonomia.setJerarquia(null);
    		} else {
    			// Navegacion normal
    			if(logger.isDebugEnabled())logger.debug("Navegamos en la taxonomia con sesionTaxonomia nula y tipo de taxonomia "+sesion.getTipoTaxonomia());
    			taxonomia = this.getSrvHerramientaModificacion().navegarTesauro(idTermino, sesion.getTipoTaxonomia(), LdapUserDetailsUtils.getIdioma());
    		}
    	}
    	// Compruebo el codigo 0.0 en el el primer taxon de taxonPath. Si lo encuentro, lo sustituyo por el mensaje i18n correspondiente.
    	if(taxonomia.getTaxonPath()!=null && taxonomia.getTaxonPath()[0].getValorTax().equals("0.0")) {
    		taxonomia.getTaxonPath()[0].setValorTax(resources.getMessage((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE),"tesauros.varias.rutas"));
    	}
    	this.getTesauroSession(request).setTaxonomia(taxonomia);
    	form.setTaxonomia(taxonomia);
    	form.setIdTermino(form.getIdTermino());
    	form.setIdiomaBuscadorBackingList(IdiomasBuscadorSingleton.getInstance().obtenerIdiomas(LdapUserDetailsUtils.getIdioma()), "idLocalizacion", "nombre");
	}

    private TaxonVO[] componerRutasDisponibles(JerarquiaVO[] jerarquias) {
    	TaxonVO[] hijos = new TaxonVO[jerarquias.length];
    	for(int i=0;i<jerarquias.length;i++) {
    		TaxonVO hijo = new TaxonVO();
    		hijo.setId(String.valueOf(i));
    		hijo.setEsHoja(Boolean.TRUE);
    		hijo.setValorTax(componerRuta(jerarquias[i].getJerarquia()));
    		hijos[i]=hijo;
    	}
    	return hijos;
    }
    
    
    /**
     * @see es.pode.modificador.presentacion.configurar.cambios.navegarETB.NavegarETBController#selectAction(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.cambios.navegarETB.SelectActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String selectAction(
			ActionMapping mapping,
			es.pode.modificador.presentacion.configurar.cambios.navegarETB.SelectActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String action = form.getAction();
		if (logger.isDebugEnabled())
			logger.debug("action = " + action);
		String result = null;
		if(resources.getMessage((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE),"tesauro.navegar.aceptar").equals(action)) {
			result = "Aceptar";
		} else {
			this.removeTesauroSession(request);
			result = "Volver";
		}
		return result;
	}

    /**
	 * @see es.pode.modificador.presentacion.configurar.cambios.navegarETB.NavegarETBController#guardarTermino(org.apache.struts.action.ActionMapping,
	 *      es.pode.modificador.presentacion.configurar.cambios.navegarETB.GuardarTerminoForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
    public final void guardarTermino(ActionMapping mapping, es.pode.modificador.presentacion.configurar.cambios.navegarETB.GuardarTerminoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        TaxonomiaVO taxonomia = this.getTesauroSession(request).getTaxonomia();
        this.removeTesauroSession(request);
        String xml = this.getSrvHerramientaModificacion().generarETB(taxonomia);
        if(logger.isDebugEnabled()) logger.debug("Taxonomia generada para añadir tesauro: " + xml);
        this.getCambioSession(request).setValorNuevo(xml);
        this.getCambioSession(request).setLomTerm("lom.classification.taxonPath");
        this.getCambioSession(request).setIdLomTerm("9.2");
    }

    /**
     * @see es.pode.modificador.presentacion.configurar.cambios.navegarETB.NavegarETBController#comprobarJerarquias(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.cambios.navegarETB.ComprobarJerarquiasForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String comprobarJerarquias(
			ActionMapping mapping,
			es.pode.modificador.presentacion.configurar.cambios.navegarETB.ComprobarJerarquiasForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// Comprobar si el nodo seleccionado tiene varias rutas padre
		String result = null;
		TaxonomiaVO taxonomia = this.getTesauroSession(request).getTaxonomia();
		if(taxonomia!=null && taxonomia.getJerarquia()!=null && taxonomia.getJerarquia().length>1) {
			// Varios padres disponibles
			result = "VariosPadres";
		} else {
			result = "UnPadre";
		}
		return result;
	}

    /**
     * @see es.pode.modificador.presentacion.configurar.cambios.navegarETB.NavegarETBController#selectActionRutas(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.cambios.navegarETB.SelectActionRutasForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String selectActionRutas(
			ActionMapping mapping,
			es.pode.modificador.presentacion.configurar.cambios.navegarETB.SelectActionRutasForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String result = null;
		String posicion = form.getPosicion();
		String idTermino = form.getIdTermino();
		String action = form.getAction();
		if(logger.isDebugEnabled()) logger.debug("selectActionRutas con idTermino = " + idTermino + " : posicion = " + posicion);
		if(resources.getMessage((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE),"tesauro.seleccionar.ruta.padre.aceptar").equals(action)) {
			// Modifico el objeto en sesion para que quede con la ruta padre elegida
			TaxonomiaVO taxonomia = this.getTesauroSession(request).getTaxonomia();
			JerarquiaVO[] jerarquia = taxonomia.getJerarquia();
			taxonomia.setJerarquia(null);
			int iPosicion = Integer.parseInt(posicion);
			TaxonVO[] taxonPath = jerarquia[iPosicion].getJerarquia();
			taxonomia.setTaxonPath(taxonPath);
			this.getTesauroSession(request).setTaxonomia(taxonomia);
			result="Aceptar";
		} else {
			result = "Cancelar";
		}
		return result;
	}

    /**
     * @see es.pode.modificador.presentacion.configurar.cambios.navegarETB.NavegarETBController#prepararSeleccion(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.cambios.navegarETB.PrepararSeleccionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void prepararSeleccion(
			ActionMapping mapping,
			es.pode.modificador.presentacion.configurar.cambios.navegarETB.PrepararSeleccionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
    	TaxonomiaVO taxonomia = new TaxonomiaVO();
    	taxonomia.setTaxonPath(null);
    	taxonomia.setIdioma(this.getTesauroSession(request).getTaxonomia().getIdioma());
    	JerarquiaVO[] jerarquia = this.getTesauroSession(request).getTaxonomia().getJerarquia();
    	// Componemos un array de taxones con las rutas padres
    	TaxonVO[] hijos = componerRutasDisponibles(jerarquia);
    	taxonomia.setHijos(hijos);
    	form.setTaxonomia(taxonomia);
    	form.setIdiomaBuscadorBackingList(IdiomasBuscadorSingleton.getInstance().obtenerIdiomas(LdapUserDetailsUtils.getIdioma()), "idLocalizacion", "nombre");
	}

    private String componerRuta(TaxonVO[] taxones) {
    	StringBuffer sb = new StringBuffer();
    	for(int i=0;i<taxones.length;i++) {
    		sb.append(taxones[i].getValorTax());
    		if(i<taxones.length-1) sb.append(" -> ");
    	}
    	return sb.toString();
    }


}