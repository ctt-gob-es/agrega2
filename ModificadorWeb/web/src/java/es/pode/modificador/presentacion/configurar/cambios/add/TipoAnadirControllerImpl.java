/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.modificador.presentacion.configurar.cambios.add;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.modificador.negocio.servicio.FormularioTaxonomias;
import es.pode.modificador.negocio.servicio.LabelValueVO;
import es.pode.modificador.presentacion.configurar.cambios.CambioSession;
import es.pode.modificador.presentacion.idiomasBuscador.IdiomasBuscadorSingleton;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.modificador.presentacion.configurar.cambios.add.TipoAnadirController
 */
public class TipoAnadirControllerImpl extends TipoAnadirController
{
	protected static Logger logger = Logger.getLogger(TipoAnadirControllerImpl.class); 

    public void obtenerTipo(ActionMapping mapping, ObtenerTipoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
    {
        this.getCambioSession(request).setValorNuevo(null);
        this.getCambioSession(request).setLomTerm(null);
        this.getCambioSession(request).setIdLomTerm(null);
        
        //Cargamos los combos
        try{
	        FormularioTaxonomias combos = this.getSrvHerramientaModificacion().obtenerCombosTaxonomias(LdapUserDetailsUtils.getIdioma());
	        LabelValueVO[] proposito = combos.getProposito();
	        LabelValueVO[] taxonomias=combos.getTaxonomias();
	        
			Collection idProp2 = Arrays.asList(proposito);
			Collection idProp= new ArrayList();
			Iterator zProp = idProp2.iterator();
			while (zProp.hasNext()) {
				idProp.add(zProp.next());
			}
			form.setPropositoBackingList(idProp, "value", "label");
			if(logger.isDebugEnabled())logger.debug("Cargados los propositos en el combo, longitud= "+form.getPropositoBackingList().length);
			Collection idTax2 = Arrays.asList(taxonomias);
			Collection idTax= new ArrayList();
			Iterator zTax = idTax2.iterator();
			while (zTax.hasNext()) {
				idTax.add(zTax.next());
			}
			form.setTaxonomiaBackingList(idTax, "value", "label");
			if(logger.isDebugEnabled())logger.debug("Cargados las taxonomias en el combo, longitud= "+form.getTaxonomiaBackingList().length);
        }catch(Exception e){
        	logger.error("Error al caragar los combos"+e);
        }
        if(logger.isDebugEnabled())logger.debug("El tipo cambio es "+this.getCambioSession(request).getCambioTipo());
        if(this.getCambioSession(request).getCambioTipo()!=null)
        {
        	form.setCambiotipos(this.getCambioSession(request).getCambioTipo());
        }
        else{
        	form.setCambiotipos("taxonomia");
        }
        if(logger.isDebugEnabled())logger.debug("El tipo a añadir es "+this.getCambioSession(request).getTipoAniadir());

    	if(this.getCambioSession(request).getTipoAniadir()!=null)
    	{
    		form.setTipoAniadir(this.getCambioSession(request).getTipoAniadir());
    		if(this.getCambioSession(request).getProposito()!=null)
        	{
        		form.setProposito(this.getCambioSession(request).getProposito());
        	}
    	}
    	
    	
    	if(this.getCambioSession(request).getAlcance()!=null)
    	{
    		form.setAlcance(this.getCambioSession(request).getAlcance());
    	}
    	else
    	{
    		form.setAlcance(Boolean.TRUE);
    	}

    	 form.setIdiomaBuscadorBackingList(IdiomasBuscadorSingleton.getInstance().obtenerIdiomas(LdapUserDetailsUtils.getIdioma()), "idLocalizacion", "nombre");

	}
    
	/**
     * @see es.pode.modificador.presentacion.configurar.cambios.add.TipoAnadirController#selectAction(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.cambios.add.SelectActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String selectAction(ActionMapping mapping, es.pode.modificador.presentacion.configurar.cambios.add.SelectActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	
    	
    	String tipoAniadir = form.getTipoAniadir();
    	String proposito=form.getProposito();
    	Boolean alcance=form.getAlcance();
    	String action=form.getAction();
    	String cambio=form.getCambiotipos();
		String result="";
		String CONTINUAR="Continuar";
		String CANCELAR="Cancelar";
		String VOLVER="VOLVER";
		java.util.Locale locale = (java.util.Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);

		if(action.equals(i18n.getString("tipoAnadirAlcance.continuar")))
		{
			if(cambio==null){
				throw new ValidatorException("{tipoAnadirTipo.exception}");
			}else{
				this.getCambioSession(request).setCambioTipo(form.getCambiotipos());
				
				if (tipoAniadir==null)
				{
					throw new ValidatorException("{tipoAnadirTipo.exception}");
				}
				else
				{
					if(!tipoAniadir.equals("otro")){
						this.getCambioSession(request).setTipoAniadir((form.getTipoAniadir()));
					}else{
						this.getCambioSession(request).setTipoAniadir((form.getTipoAniadir()));
						if(proposito==null){
							throw new ValidatorException("{tipoAnadirProposito.exception}");
						}
						else{
							this.getCambioSession(request).setProposito(form.getProposito());
						}
					}
				}
			}
			
			if (alcance==null)
			{
				throw new ValidatorException("{tipoAnadirAlcance.exception}");
			}
			else
			{
				this.getCambioSession(request).setAlcance(form.getAlcance());
			}
			result=CONTINUAR;
		}else if(action.equals(i18n.getString("comun.volver"))){
			result=VOLVER;
		}
		
		else 
		{
			result=CANCELAR;
		}
		if(logger.isDebugEnabled())logger.debug("La accion es "+result);

      return result;
    }







    /**
     * @see es.pode.modificador.presentacion.configurar.cambios.add.TipoAnadirController#selectTipo(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.cambios.add.SelectTipoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String selectTipo(ActionMapping mapping, es.pode.modificador.presentacion.configurar.cambios.add.SelectTipoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String tipoAniadir = form.getTipoAniadir();//Tipo de taxonomia o tesauro
    	String tipo=form.getCambiotipos();//Taxonomia u otro
    	if(logger.isDebugEnabled())logger.debug("el tipo para añadir es "+tipoAniadir+" y el tipo elegido "+tipo);
    	CambioSession sesion = this.getCambioSession(request);
    	
		String result="";
		if(tipo!=null && !tipo.equals("")){
			if(tipo.equals("otro")){
					result = "Otro";
					form.setIdTermino(this.getCambioSession(request).getIdLomTerm());
					sesion.setTipoAniadir("otro");
					sesion.setCambioTipo("otro");
			}else{
				if(tipoAniadir !=null && !tipoAniadir.equals("")){

		    		if(form.getProposito()==null || form.getProposito().equals("")){
		    			throw new ValidatorException("{tipoAnadirProposito.exception}");
		    		}else{
		    			
		    			if(logger.isDebugEnabled())logger.debug("El texto del proposito elegido en ingles es "+form.getProposito());
		    			sesion.setProposito(form.getProposito());
		    			
		    		}
				
				
					if (tipoAniadir.startsWith("TES_") )
					{	
						String tipoA=tipoAniadir.substring(4, tipoAniadir.length());
						if(logger.isDebugEnabled())logger.debug("El nombre de la taxonomia es "+tipoA);
						sesion.setTipoAniadir(tipoA);
		    			sesion.setTipoTaxonomia(tipoA);//Le insertamos el nombre de la taxonomia
						result="ETB";
					}else{
						sesion.setTipoAniadir(form.getTipoAniadir());
		    			sesion.setTipoTaxonomia(form.getTipoAniadir());//Le insertamos el nombre de la taxonomia
						result="ArbolCurricular";
					}
	 

					
				}else{
					throw new ValidatorException("{tipoAnadirTipoTaxonomia.exception}");
				}
			}
		}else{
			throw new ValidatorException("{tipoAnadirTipo.exception}");
		}
        return result;
    }









}