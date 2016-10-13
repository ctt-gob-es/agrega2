/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.modificador.presentacion.configurar.objetos.buscar.navegarTesauro;

import java.util.ArrayList;
import java.util.Collection;
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
import es.pode.modificador.presentacion.configurar.objetos.buscar.BusquedaSession;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.modificador.presentacion.configurar.objetos.buscar.navegarTesauro.NavegarTesauroController
 */
public class NavegarTesauroControllerImpl extends NavegarTesauroController
{


	private Logger logger = Logger.getLogger(NavegarTesauroControllerImpl.class);

	private static final MessageResources resources = MessageResources.getMessageResources("application-resources");

    /**
     * @see es.pode.modificador.presentacion.configurar.objetos.buscar.navegarTesauro.NavegarTesauroController#navegarTesauro(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.objetos.buscar.navegarTesauro.NavegarTesauroForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void navegarTesauro(ActionMapping mapping, es.pode.modificador.presentacion.configurar.objetos.buscar.navegarTesauro.NavegarTesauroForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	/*
    	 * Se contemplan tres casos:
    	 *   - idTermino != "0.0" y taxonomiaSession.taxonomia.jerarquia==null: Navegacion normal
    	 *   - idTermino != "0.0" y taxonomiaSession.taxonomia.jerarquia!=null y taxonomiaSession.taxonomia.taxonPath == null: Seleccion de ruta padre
    	 *   - idTermino == "0.0": preparar la seleccion de ruta padre 
    	 */
    	String idTermino = form.getIdTermino();
    	TaxonomiaVO taxonomiaSession = this.getTesauroSession(request).getTaxonomia();
    	BusquedaSession sesion=getBusquedaSession(request);
    	String nomTesauro=sesion.getNombreTaxonomia();;
    	form.setNombreTesauro(nomTesauro);
    	if(logger.isDebugEnabled()) logger.debug("NavegarTesauro con parametros idTermino = " + idTermino + " y taxonomia (session) = "  + taxonomiaSession+" y nombre del tesauro "+ nomTesauro);
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
    			taxonomia = this.getSrvHerramientaModificacion().navegarTesauro(idRecuperar, form.getNombreTesauro(), LdapUserDetailsUtils.getIdioma());
    			taxonomia.setTaxonPath(rutaElegida);
    			taxonomia.setJerarquia(null);
    		} else {
    			// Navegacion normal
    			if(logger.isDebugEnabled())logger.debug("Navegamos en la taxonomia con sesionTaxonomia nula y tipo de taxonomia "+form.getNombreTesauro());
    			taxonomia = this.getSrvHerramientaModificacion().navegarTesauro(idTermino, form.getNombreTesauro(), LdapUserDetailsUtils.getIdioma());
    		}
    	}
    	// Compruebo el codigo 0.0 en el el primer taxon de taxonPath. Si lo encuentro, lo sustituyo por el mensaje i18n correspondiente.
    	if(taxonomia.getTaxonPath()!=null && taxonomia.getTaxonPath()[0].getValorTax().equals("0.0")) {
    		taxonomia.getTaxonPath()[0].setValorTax(resources.getMessage((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE),"tesauros.varias.rutas"));
    	}
    	this.getTesauroSession(request).setTaxonomia(taxonomia);
    	form.setTaxonomia(taxonomia);
    	form.setIdTermino(idTermino);
    	form.setIdioma(form.getIdioma());
    	
     }







    /**
     * @see es.pode.modificador.presentacion.configurar.objetos.buscar.navegarTesauro.NavegarTesauroController#selectAction(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.objetos.buscar.navegarTesauro.SelectActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String selectAction(ActionMapping mapping, es.pode.modificador.presentacion.configurar.objetos.buscar.navegarTesauro.SelectActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String action = form.getAction();
    	String identificador=form.getIdTermino();
		if (logger.isDebugEnabled())
			logger.debug("action = " + action+" con identificador "+ identificador);
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
     * @see es.pode.modificador.presentacion.configurar.objetos.buscar.navegarTesauro.NavegarTesauroController#prepararSeleccion(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.objetos.buscar.navegarTesauro.PrepararSeleccionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void prepararSeleccion(ActionMapping mapping, es.pode.modificador.presentacion.configurar.objetos.buscar.navegarTesauro.PrepararSeleccionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	TaxonomiaVO taxonomia = new TaxonomiaVO();
    	if(logger.isDebugEnabled())logger.debug("Tenemos varios padres");
    	taxonomia.setTaxonPath(null);
    	taxonomia.setIdioma(this.getTesauroSession(request).getTaxonomia().getIdioma());
    	JerarquiaVO[] jerarquia = this.getTesauroSession(request).getTaxonomia().getJerarquia();
    	// Componemos un array de taxones con las rutas padres
    	TaxonVO[] hijos = componerRutasDisponibles(jerarquia);
    	taxonomia.setHijos(hijos);
    	form.setTaxonomia(taxonomia);
    	form.setIdioma(form.getIdioma());
    	
     }







    /**
     * @see es.pode.modificador.presentacion.configurar.objetos.buscar.navegarTesauro.NavegarTesauroController#guardarTermino(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.objetos.buscar.navegarTesauro.GuardarTerminoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final TaxonomiaVO guardarTermino(ActionMapping mapping, es.pode.modificador.presentacion.configurar.objetos.buscar.navegarTesauro.GuardarTerminoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	TaxonomiaVO taxon=new TaxonomiaVO();
    	try{
	    	TaxonomiaVO taxonomia = this.getTesauroSession(request).getTaxonomia();
	        this.removeTesauroSession(request);
	        BusquedaSession sesion=getBusquedaSession(request);
	       
	        if(logger.isDebugEnabled())logger.debug("el nombre del tesauro es "+sesion.getNombreTaxonomia()+" y identificador del taxon "+form.getIdTermino());
	        taxon = this.getSrvHerramientaModificacion().navegarTesauro(form.getIdTermino(), sesion.getNombreTaxonomia(), LdapUserDetailsUtils.getIdioma());
	        
			Collection<TaxonomiaVO> coleccionTaxones=sesion.getTaxonesSeleccionados();
			
			if(coleccionTaxones==null){
				if(logger.isDebugEnabled())logger.debug("Es la primera ruta que se añade");
				coleccionTaxones=new ArrayList<TaxonomiaVO>();
				coleccionTaxones.add(taxon);
			}else{
				coleccionTaxones.add(taxon);
			}
			sesion.setTaxonesSeleccionados(coleccionTaxones);
    	}catch (RuntimeException e) {

			logger.error("Excepcion en el servicio de herramienta modificacion al guardar termino: ",e);
		}
        return taxon;
 
        
     }







    /**
     * @see es.pode.modificador.presentacion.configurar.objetos.buscar.navegarTesauro.NavegarTesauroController#selectActionRutas(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.objetos.buscar.navegarTesauro.SelectActionRutasForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String selectActionRutas(ActionMapping mapping, es.pode.modificador.presentacion.configurar.objetos.buscar.navegarTesauro.SelectActionRutasForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String result = null;
		String posicion = form.getPosicion();
		String idTermino = form.getIdTermino();
		String action = form.getAction();
		BusquedaSession sesion=getBusquedaSession(request);
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

			Collection<TaxonomiaVO> coleccionTaxones=sesion.getTaxonesSeleccionados();
			if(coleccionTaxones==null){
				coleccionTaxones=new ArrayList<TaxonomiaVO>();
				coleccionTaxones.add(taxonomia);
			}else{
				coleccionTaxones.add(taxonomia);
			}

			sesion.setTaxonesSeleccionados(coleccionTaxones);
			result="Aceptar";
		} else {
			result = "Cancelar";
		}
		return result;
    }







    /**
     * @see es.pode.modificador.presentacion.configurar.objetos.buscar.navegarTesauro.NavegarTesauroController#comprobarJerarquias(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.objetos.buscar.navegarTesauro.ComprobarJerarquiasForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String comprobarJerarquias(ActionMapping mapping, es.pode.modificador.presentacion.configurar.objetos.buscar.navegarTesauro.ComprobarJerarquiasForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {// Comprobar si el nodo seleccionado tiene varias rutas padre
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

    private String componerRuta(TaxonVO[] taxones) {
    	StringBuffer sb = new StringBuffer();
    	for(int i=0;i<taxones.length;i++) {
    		sb.append(taxones[i].getValorTax());
    		if(i<taxones.length-1) sb.append(" -> ");
    	}
    	return sb.toString();
    }




}