// license-header java merge-point
package es.pode.catalogadorWeb.presentacion.categoriasAvanzado.relacion;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.catalogacion.negocio.servicios.AvRelationVO;
import es.pode.catalogadorWeb.presentacion.CatalogadorAvSession;



/**
 * @see es.pode.catalogadorWeb.presentacion.categoriasAvanzado.relacion.RelacionController
 */
public class RelacionControllerImpl extends RelacionController
{


	protected static Logger logger = Logger.getLogger(RelacionControllerImpl.class); 



    /**
     * @see es.pode.catalogadorWeb.presentacion.categoriasAvanzado.relacion.RelacionController#cargarRelaciones(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.relacion.CargarRelacionesForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void cargarRelaciones(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.relacion.CargarRelacionesForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	CatalogadorAvSession catalogadorAvSession=this.getCatalogadorAvSession(request);
//		si expira la sesi�n, debemos dar error de sesi�n, comprobamos si el objeto de sesi�n contiene el identificador
		if ((catalogadorAvSession==null) || (catalogadorAvSession.getIdentificador()==null)) {
			throw new Exception();
		}

    	try{
	    	if (catalogadorAvSession.getMDSesion()!=null) {
	    		AvRelationVO[] relaciones =catalogadorAvSession.getMDSesion().getRelaciones();
	    		
	    		form.setListadoRelacionesAsArray(relaciones);
	    		form.setIdentificadorOde(catalogadorAvSession.getIdentificador());
	    	}
    	}catch (Exception e){
			logger.error("Error en Servicio de catalogacion, metodo cargarDatos " + e);
			//throw new java.lang.Exception("relacion", e);
			saveErrorMessage(request, " Error en Servicio de catalogacion, metodo cargarDatos ");
		}
		
     }




    /**
     * @see es.pode.catalogadorWeb.presentacion.categoriasAvanzado.relacion.RelacionController#eliminarRelacion(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.relacion.EliminarRelacionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void eliminarRelacion(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.relacion.EliminarRelacionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	boolean esta=false;
    	boolean seleccion =false;
    	Object valor = request.getSession().getAttribute("form");
    	//recupero la coleccion de Elementos para borrarle el que coincida con ese id
    	CatalogadorAvSession catalogadorAvSession=this.getCatalogadorAvSession(request);
//		si expira la sesi�n, debemos dar error de sesi�n, comprobamos si el objeto de sesi�n contiene el identificador
		if ((catalogadorAvSession==null) || (catalogadorAvSession.getIdentificador()==null)) {
			throw new Exception();
		}
    	try{
	    	if (valor instanceof RelacionFormImpl) {
	    		form.setListadoRelacionesAsArray(((RelacionFormImpl) valor).getListadoRelacionesAsArray());	
			} else if (valor instanceof ConfirmacionEliminarCancelarEliminarFormImpl) {
				form.setListadoRelacionesAsArray(((ConfirmacionEliminarCancelarEliminarFormImpl) valor).getListadoRelacionesAsArray());
			}  else if (valor instanceof ConfirmacionEliminarDatosASesionFormImpl) {
				form.setListadoRelacionesAsArray(((ConfirmacionEliminarDatosASesionFormImpl) valor).getListadoRelacionesAsArray());
			}  	
	    	if ((form.getTituloRelacionRowSelection() != null) && (form.getListadoRelacionesAsArray()!=null) && (form.getListadoRelacionesAsArray().length>0) ) {
	    		AvRelationVO[] relaciones= (AvRelationVO[]) form.getListadoRelacionesAsArray();
	    		ArrayList listaRec= new ArrayList();
	    		for (int indi=0; indi<relaciones.length; indi++) {
	    			int indiTitu=0;
	    			while ((indiTitu<form.getTituloRelacionRowSelection().size() && (!esta))) {
	        			int valorTitu=Integer.parseInt(form.getTituloRelacionRowSelection().get(indiTitu).toString()) -1;
	        			if (valorTitu == indi) {
	        				esta=true;
	        				seleccion=true;
	        			}
	        			indiTitu++;
	    			}
	    			if (!esta) {
	    				listaRec.add(relaciones[indi]);
	    			}
	    			esta=false; //Iniciliazamos para la siguiente iteracion
	    		}
	    		AvRelationVO[] reAux = (AvRelationVO[]) listaRec.toArray(new AvRelationVO[listaRec.size()]);
	    		form.setListadoRelacionesAsArray(reAux);
			  
	    	}
    	
    	}catch (Exception e){
			logger.error("Error en catalogadorWeb, categor�a Relaci�n, metodo eliminarRelacion " + e);
			//throw new java.lang.Exception("relacion", e);
			saveErrorMessage(request, " Error en catalogadorWeb, categor�a Relaci�n, metodo eliminarRelacion ");
		}
    	
    	if(!seleccion)
    		throw new ValidatorException("{error.EliminarNoSeleccion}");
    		
     }






	public void cancelar(ActionMapping mapping, CancelarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void guardarEnSesion(ActionMapping mapping, GuardarEnSesionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Object valor = request.getSession().getAttribute("form");
		try{	
			if (valor instanceof ListadoRelacionesFormEliminarFormImpl) {
				form.setListadoRelacionesAsArray(((ListadoRelacionesFormEliminarFormImpl) valor).getListadoRelacionesAsArray());
			} else if (valor instanceof ConfirmacionEliminarCancelarEliminarFormImpl) {
				form.setListadoRelacionesAsArray(((ConfirmacionEliminarCancelarEliminarFormImpl) valor).getListadoRelacionesAsArray());
			}
			AvRelationVO[] relaciones= (AvRelationVO[]) form.getListadoRelacionesAsArray();
			CatalogadorAvSession catalogadorAvSession=this.getCatalogadorAvSession(request);
	    	
	    	if (catalogadorAvSession.getMDSesion()!=null) {
	    		catalogadorAvSession.getMDSesion().setRelaciones(relaciones);
	    	}
	    	
		}catch (Exception e){
			logger.error("Error en catalogadorWeb, categor�a Relaci�n, metodo guardarEnSesion " + e);
			//throw new java.lang.Exception("relacion", e);
			saveErrorMessage(request, " Error en catalogadorWeb, categor�a Relaci�n, metodo guardarEnSesion ");
		}
		
	}

	public boolean hayRelaciones(ActionMapping mapping, HayRelacionesForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean result = false;
		CatalogadorAvSession catalogadorAvSession=this.getCatalogadorAvSession(request);
    	try {
	    	if (catalogadorAvSession.getMDSesion()!=null) {
	    		AvRelationVO[] relaciones =catalogadorAvSession.getMDSesion().getRelaciones();
	    		if (relaciones == null || relaciones.length == 0 ){
	    			result = false;
	    		}
	    		else 
	    			result = true;
	   
	    	}
    	}catch (Exception e) {
			logger.error("Error AnotacionController, m�todo hayRelaciones "+ e);
			//throw new java.lang.Exception("relacion", e);
			saveErrorMessage(request, " Error AnotacionController, m�todo hayRelaciones ");
		}
		return result;
	}

}