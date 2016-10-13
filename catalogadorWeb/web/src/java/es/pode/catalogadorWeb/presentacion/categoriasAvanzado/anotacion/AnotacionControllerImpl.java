// license-header java merge-point
package es.pode.catalogadorWeb.presentacion.categoriasAvanzado.anotacion;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.catalogacion.negocio.servicios.AvAnnotationVO;
import es.pode.catalogadorWeb.presentacion.CatalogadorAvSession;



/**
 * @see es.pode.catalogadorWeb.presentacion.categoriasAvanzado.anotacion.AnotacionController
 */
public class AnotacionControllerImpl extends AnotacionController
{
	private static Logger logger = Logger.getLogger(AnotacionControllerImpl.class);
	
    /**
     * @see es.pode.catalogadorWeb.presentacion.categoriasAvanzado.anotacion.AnotacionController#cargarAnotaciones(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.anotacion.CargarAnotacionesForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void cargarAnotaciones(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.anotacion.CargarAnotacionesForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
     
    	CatalogadorAvSession catalogadorAvSession=this.getCatalogadorAvSession(request);
    	try {
    		//si expira la sesión, debemos dar error de sesión, comprobamos si el objeto de sesión contiene el identificador
    		if ((catalogadorAvSession==null) || (catalogadorAvSession.getIdentificador()==null)) {
    			throw new Exception();
    		}
	    	if (catalogadorAvSession.getMDSesion()!=null) {
	    		AvAnnotationVO[] anotaciones =catalogadorAvSession.getMDSesion().getAnotaciones();
	    		
	    		form.setListadoAnotacionesAsArray(anotaciones);
	    		form.setIdentificadorOde(catalogadorAvSession.getIdentificador());
	    	}
    	}catch (Exception e) {
			logger.error("Error AnotacionController, método cargarAnotaciones "+ e);
			//throw new java.lang.Exception("anotacion", e);
			saveErrorMessage(request, " Error AnotacionController, método cargarAnotaciones ");
		}
    	
     }



    /**
     * @see es.pode.catalogadorWeb.presentacion.categoriasAvanzado.anotacion.AnotacionController#eliminarAnotacion(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.anotacion.EliminarAnotacionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void eliminarAnotacion(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.anotacion.EliminarAnotacionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	boolean esta=false;
    	Object valor = request.getSession().getAttribute("form");
    	CatalogadorAvSession catalogadorAvSession=this.getCatalogadorAvSession(request);
    	boolean seleccion=false;
    	
    	try {
    		
//    		si expira la sesión, debemos dar error de sesión, comprobamos si el objeto de sesión contiene el identificador
    		if ((catalogadorAvSession==null) || (catalogadorAvSession.getIdentificador()==null)) {
    			throw new Exception();
    		}
	    	//recupero la coleccion de Elementos para borrarle el que coincida con ese id
	    	if (valor instanceof AnotacionFormImpl) {
	    		form.setListadoAnotacionesAsArray(((AnotacionFormImpl) valor).getListadoAnotacionesAsArray());
				
			} else if (valor instanceof ConfirmacionEliminarCancelarEliminarFormImpl) {
				form.setListadoAnotacionesAsArray(((ConfirmacionEliminarCancelarEliminarFormImpl) valor).getListadoAnotacionesAsArray());
			} else if (valor instanceof ConfirmacionEliminarDatosAsesionFormImpl) {
				form.setListadoAnotacionesAsArray(((ConfirmacionEliminarDatosAsesionFormImpl) valor).getListadoAnotacionesAsArray());
			}
	    	
	    	
	    	if ((form.getTituloAnotacionRowSelection() != null) && (form.getListadoAnotacionesAsArray()!=null) 
	    			&& (form.getListadoAnotacionesAsArray().length>0) ) {
	    		AvAnnotationVO[] anotaciones= (AvAnnotationVO[]) form.getListadoAnotacionesAsArray();
	    		ArrayList listaRec= new ArrayList();
	    		for (int indi=0; indi<anotaciones.length; indi++) {
	    			int indiTitu=0;
	    			while ((indiTitu<form.getTituloAnotacionRowSelection().size() && (!esta))) {
	        			int valorTitu=Integer.parseInt(form.getTituloAnotacionRowSelection().get(indiTitu).toString()) -1;
	        			if (valorTitu == indi) {
	        				esta=true;
	        				seleccion=true;
	        			}
	        			indiTitu++;
	    			}
	    			if (!esta) {
	    				listaRec.add(anotaciones[indi]);
	    			}
	    			esta=false; //Iniciliazamos para la siguiente iteracion
	    		}
	    		AvAnnotationVO[] anotaAux = (AvAnnotationVO[]) listaRec.toArray(new AvAnnotationVO[listaRec.size()]);
	    		
	    		form.setListadoAnotacionesAsArray(anotaAux);
			  
	    	}
			
	    	
    	
    	}catch (Exception e) {
			logger.error("Error AnotacionController, método EliminarAnotacion "+ e);
			//throw new java.lang.Exception("anotacion", e);
			saveErrorMessage(request, " Error AnotacionController, método EliminarAnotacion ");
		}
    	//System.out.print("  ");
		//cambiamos la comprobación de Error Validacion, para que esta excepcion no sea capturada por java.lang.Exception
		if(!seleccion)
    		throw new ValidatorException("{error.EliminarNoSeleccion}");
    }


	public void guardarEnSesion(ActionMapping mapping, GuardarEnSesionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Object valor = request.getSession().getAttribute("form");
		try {
			if (valor instanceof ListadoAnotacionesFormEliminarFormImpl) {
				form.setListadoAnotacionesAsArray(((ListadoAnotacionesFormEliminarFormImpl) valor).getListadoAnotacionesAsArray());
			} else if (valor instanceof ConfirmacionEliminarCancelarEliminarFormImpl) {
				form.setListadoAnotacionesAsArray(((ConfirmacionEliminarCancelarEliminarFormImpl) valor).getListadoAnotacionesAsArray());
			}
			AvAnnotationVO[] anotaciones= (AvAnnotationVO[]) form.getListadoAnotacionesAsArray();
			CatalogadorAvSession catalogadorAvSession=this.getCatalogadorAvSession(request);
	    	
	    	if (catalogadorAvSession.getMDSesion()!=null) {
	    		catalogadorAvSession.getMDSesion().setAnotaciones(anotaciones);
	    	}
		}catch (Exception e) {
			logger.error("Error AnotacionController, método guardarEnSession "+ e);
			//throw new java.lang.Exception("anotacion", e);
			saveErrorMessage(request, " Error AnotacionController, método guardarEnSession ");
		}
	}


	public void cancelar(ActionMapping mapping, CancelarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//Vuelve a Inicio Catalogador
//		System.out.print("");
	}


	public boolean hayAnotaciones(ActionMapping mapping, HayAnotacionesForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	boolean result = false;
		CatalogadorAvSession catalogadorAvSession=this.getCatalogadorAvSession(request);
    	try {
	    	if (catalogadorAvSession.getMDSesion()!=null) {
	    		AvAnnotationVO[] anotaciones =catalogadorAvSession.getMDSesion().getAnotaciones();
	    		if (anotaciones == null || anotaciones.length == 0 ){
	    			result = false;
	    		}
	    		else 
	    			result = true;
	   
	    	}
    	}catch (Exception e) {
			logger.error("Error AnotacionController, método hayAnotaciones "+ e);
			//throw new java.lang.Exception("anotacion", e);
			saveErrorMessage(request, " Error AnotacionController, método hayAnotaciones ");
		}
		return result;
	}






}