/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.modificador.presentacion.configurar.cambios.cerrar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.modificador.negocio.servicio.CambioTypes;
import es.pode.modificador.negocio.servicio.Change;
import es.pode.modificador.negocio.servicio.Changes;
import es.pode.modificador.negocio.servicio.ConfiguracionTarea;
import es.pode.modificador.negocio.servicio.EspecialTermTypes;
import es.pode.modificador.negocio.servicio.FormularioTaxonomias;
import es.pode.modificador.negocio.servicio.LabelValueVO;
import es.pode.modificador.negocio.servicio.TransformacionTypes;
import es.pode.modificador.presentacion.configurar.cambios.CambioSession;



/**
 * @see es.pode.modificador.presentacion.configurar.cambios.cerrar.CerrarController
 */
public class CerrarControllerImpl extends CerrarController
{
	private static final String VACIA = "";
	private Logger logger = Logger.getLogger(CerrarControllerImpl.class);

    /**
     * @see es.pode.modificador.presentacion.configurar.cambios.cerrar.CerrarController#cerrarSesion(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.cambios.cerrar.CerrarSesionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void cerrarSesion(ActionMapping mapping, es.pode.modificador.presentacion.configurar.cambios.cerrar.CerrarSesionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
        Change change= new Change();
        CambioSession sesion = this.getCambioSession(request);
        if(sesion.getIdioma()!=null && !VACIA.equals(sesion.getIdioma())) {
        	change.setLanguage(sesion.getIdioma());
        }
        change.setLomTerm(sesion.getLomTerm());
        change.setIdLomTerm(sesion.getIdLomTerm());
        change.setNewValue(sesion.getValorNuevo());
        change.setRegExp(sesion.getExprReg());
        change.setReplaceAll(sesion.getReplaceAll());
        
        if(sesion.getTipoTaxonomia()!=null){
        	change.setSource(sesion.getTipoTaxonomia());
        }
        if(logger.isDebugEnabled())logger.debug("El tipo a anadir va a ser "+sesion.getTipoAniadir()+"y el otro "+sesion.getTipoTaxonomia());
        if(sesion.getTipoAniadir()!=null)
        {
        	change.setTermType(EspecialTermTypes.fromString(sesion.getCambioTipo()));
        }
        if(sesion.getProposito()!=null)
        {
        	if(logger.isDebugEnabled())logger.debug("Insertamos el proposito seleccionado "+sesion.getProposito());
        	String textoProposito=VACIA;
			FormularioTaxonomias combos = this.getSrvHerramientaModificacion().obtenerCombosTaxonomias("en");
			LabelValueVO[] proposito = combos.getProposito();
			boolean encontrado=false;
			for(int i=0;i<proposito.length && !encontrado;i++){
				String valor=proposito[i].getValue();
				if(valor.equals(sesion.getProposito())){
					textoProposito=proposito[i].getLabel();
					encontrado=true;
				}
			}
        	
        	change.setPurpose(textoProposito);
        }
        change.setMainLomOnly(sesion.getAlcance());
        change.setType(CambioTypes.fromValue(sesion.getTipo()));       
		if(sesion.getValor()!=null && !VACIA.equals(sesion.getValor())) {
        	change.setValue(sesion.getValor());
        }

		if(CambioTypes.export.equals(change.getType()) ) {
			change.setExportFormat(TransformacionTypes.fromString(sesion.getTipoTransformacion()));
			change.setExportPath(sesion.getPathDestino());
		}
		if(CambioTypes.transformation.equals(change.getType()) ) {
			change.setExportFormat(TransformacionTypes.fromString(sesion.getTipoTransformacion()));
			change.setExportPath(sesion.getPathDestino());
		}
		

        if(sesion.getPosicion()!=null)
        {
        	Integer posicion=this.getCambioSession(request).getPosicion();
        	ConfiguracionTarea config = this.getConfigurarModificacionSession(request).getConfiguracion();
        	Changes cambios = config.getCambios();
        	Change[] arrayCambios = cambios.getCambios();
        	arrayCambios[posicion.intValue()]=change;
        	cambios.setCambios(arrayCambios);
        	config.setCambios(cambios);
        	this.getConfigurarModificacionSession(request).setConfiguracion(config);
        }
        else
        {

        	//desde el cambios.getCambios();
        	ConfiguracionTarea config = this.getConfigurarModificacionSession(request).getConfiguracion();
        	Changes cambios = config.getCambios();
        	Change[] arrayCambios = cambios.getCambios();
        	List arrayCamb=new ArrayList();	
        	
        	arrayCamb.addAll(Arrays.asList(arrayCambios));
        	arrayCamb.add(change);
        	arrayCambios =(Change[])arrayCamb.toArray(new Change[arrayCamb.size()]);
        	cambios.setCambios(arrayCambios);
        	config.setCambios(cambios);
        	this.getConfigurarModificacionSession(request).setConfiguracion(config);
        }
        
    	}catch(Exception e){
    		logger.error("Error inesperado", e);
    	}
        this.removeCambioSession(request);
        
    }









}