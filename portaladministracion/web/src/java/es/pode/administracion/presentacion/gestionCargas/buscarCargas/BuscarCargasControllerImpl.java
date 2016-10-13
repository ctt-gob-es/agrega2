// license-header java merge-point
package es.pode.administracion.presentacion.gestionCargas.buscarCargas;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.planificador.negocio.servicios.InformacionCargaVO;
import es.pode.planificador.negocio.servicios.RegistroTareaCargaEjecutadaVO;
import es.pode.planificador.negocio.servicios.SrvPlanificadorService;
import es.pode.planificador.negocio.servicios.TareaEjecutadaVO;

/**
 * @see es.pode.administracion.presentacion.gestionCargas.buscarCargas.BuscarCargasController
 */
public class BuscarCargasControllerImpl extends BuscarCargasController
{


	private static Logger logger = Logger.getLogger(BuscarCargasControllerImpl.class);



    /**
     * @see es.pode.administracion.presentacion.gestionCargas.buscarCargas.BuscarCargasController#obtenerTipoBusqueda(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.gestionCargas.buscarCargas.ObtenerTipoBusquedaForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String obtenerTipoBusqueda(ActionMapping mapping, es.pode.administracion.presentacion.gestionCargas.buscarCargas.ObtenerTipoBusquedaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	if(logger.isDebugEnabled())logger.debug("Entramos en obtenerTipoBusqueda con el tipo "+form.getTipoBusqueda()+" y el texto "+form.getTextoBusqueda());
    	String tipo="";
    	if(form.getTipoBusqueda()!=null){
    		if(form.getTipoBusqueda().equals("NombreZip")){
    			tipo= form.getTipoBusqueda();
    		}else if(form.getTipoBusqueda().equals("NombreLote")){
    			tipo= form.getTipoBusqueda();
    		}else{
    			throw new ValidatorException ("{errors.combo.incorrecto}");
    		}
    	}
         return tipo;
    }

    /**
     * @see es.pode.administracion.presentacion.gestionCargas.buscarCargas.BuscarCargasController#buscarPorNombreZip(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.gestionCargas.buscarCargas.BuscarPorNombreZipForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void buscarPorNombreZip(ActionMapping mapping, es.pode.administracion.presentacion.gestionCargas.buscarCargas.BuscarPorNombreZipForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	if(logger.isDebugEnabled())logger.debug("Vamos a hacer una busqueda sobre los nombres de los zip, con texto "+form.getTextoBusqueda());
    	try{
    		SrvPlanificadorService planificadorService = this.getSrvPlanificadorService();
    		RegistroTareaCargaEjecutadaVO[] recibido = planificadorService.buscarPorZip(form.getTextoBusqueda());
    		if(recibido!=null)logger.info("Hemos recibido "+recibido.length+" elementos en la busqueda por zip");
    		else logger.info("No se ha encontrado ningun ode que se asemeje a ese nombre de zip");
    		BusquedasGestionVO[] busquedas=new BusquedasGestionVO[recibido.length];
    		for(int i=0;i< recibido.length;i++){
    			BusquedasGestionVO buscado=new BusquedasGestionVO();
    			buscado.setDescripcionTarea(recibido[i].getTarea_carga_ejecutada().getDescripcionTarea());
    			if (recibido[i].getTarea_carga_ejecutada().getFechaFin() != null){
    	    		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy, HH:mm");
    	    		String fech=sdf.format(recibido[i].getTarea_carga_ejecutada().getFechaFin().getTime());
    	    		if(logger.isDebugEnabled())logger.debug("Tenemos la siguiente fecha "+fech);
    	    		buscado.setFecha(fech);	
    	    	}
    			buscado.setId(recibido[i].getTarea_carga_ejecutada().getId());
    			buscado.setNombreLote(recibido[i].getTarea_carga_ejecutada().getNombreLote());
    			buscado.setNombreTarea(recibido[i].getTarea_carga_ejecutada().getDescripcion());
    			buscado.setNombreZip(recibido[i].getNombreZip());
    			buscado.setDespublicado(true);
    			buscado.setId_mec(recibido[i].getId_mec());
    			busquedas[i]=buscado;
    		}
    		
    		form.setZipsEncontradosAsArray(busquedas);
    	}catch(Exception e){
    		logger.error("Se ha producido un error al listar los odes publicados: " ,e);
        	throw new ValidatorException ("{errors.listarCargasEjecutadasZip} ");
    		
    	}
    }

    /**
     * @see es.pode.administracion.presentacion.gestionCargas.buscarCargas.BuscarCargasController#buscarPorNombreLote(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.gestionCargas.buscarCargas.BuscarPorNombreLoteForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void buscarPorNombreLote(ActionMapping mapping, es.pode.administracion.presentacion.gestionCargas.buscarCargas.BuscarPorNombreLoteForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	if(logger.isDebugEnabled())logger.debug("Vamos a hacer una busqueda sobre los nombres de los zip, con texto "+form.getTextoBusqueda());
    	try{
    		SrvPlanificadorService planificadorService = this.getSrvPlanificadorService();
    		TareaEjecutadaVO[] recibido = planificadorService.buscarPorLote(form.getTextoBusqueda());
    		if(recibido!=null)logger.info("Hemos recibido "+recibido.length+" elementos en la busqueda por lote");
    		else logger.info("No se ha encontrado ninguna tarea que se asemeje a ese nombre de lote");
    		BusquedasGestionVO[] busquedas=new BusquedasGestionVO[recibido.length];
    		for(int i=0;i< recibido.length;i++){
    			BusquedasGestionVO buscado=new BusquedasGestionVO();
    			InformacionCargaVO[] vuelta = planificadorService.obtenerCarpetasDeRegistro(recibido[i].getId());
    			buscado.setDescripcionTarea(recibido[i].getDescripcionTarea());
    			if (recibido[i].getFechaFin() != null){
    	    		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy, HH:mm");
    	    		String fech=sdf.format(recibido[i].getFechaFin().getTime());
    	    		if(logger.isDebugEnabled())logger.debug("Tenemos la siguiente fecha "+fech);
    	    		buscado.setFecha(fech);	
    	    	}
    			buscado.setId(recibido[i].getId());
    			buscado.setNombreLote(recibido[i].getNombreLote());
    			buscado.setNombreTarea(recibido[i].getDescripcion());
    			if(vuelta!=null && vuelta.length>1){
    				logger.info("Tenemos "+vuelta.length+" carpetas");
    				buscado.setCarpeta(true);	
    			}
    			int cont=0;
				for(int j=0;j<vuelta.length;j++){
					cont=+vuelta[j].getIdentificadores().length;
				}
//				if(cont>0){
//					buscado.setDespublicado(Boolean.TRUE);
//				}else{
//					buscado.setDespublicado(Boolean.FALSE);
//				}
				buscado.setDespublicado(cont>0);
    			busquedas[i]=buscado;
    		}
    		form.setLotesEncontradosAsArray(busquedas);
    	}catch(Exception e){
    		logger.error("Se ha producido un error al listar las tareas de carga ejecutadas: " ,e);
        	throw new ValidatorException ("{errors.listarCargasEjecutadasLote} ");
    		
    	}
    }

    /**
     * @see es.pode.administracion.presentacion.gestionCargas.buscarCargas.BuscarCargasController#recibirTipoBusqueda(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.gestionCargas.buscarCargas.RecibirTipoBusquedaForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String recibirTipoBusqueda(ActionMapping mapping, es.pode.administracion.presentacion.gestionCargas.buscarCargas.RecibirTipoBusquedaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	if(logger.isDebugEnabled())logger.debug("REcibimos el tipo de busqueda "+form.getTipoBusqueda()+" y el texto "+form.getTextoBusqueda());
    	
         return form.getTipoBusqueda();
    }



    public static class BusquedasGestionVO  implements java.io.Serializable {
    	
    	private java.lang.Long id;

        public java.lang.Long getId() {
			return id;
		}


		public void setId(java.lang.Long id) {
			this.id = id;
		}


		private java.lang.String nombreTarea;

        private java.lang.String nombreLote;

        private java.lang.String descripcionTarea;

        private java.lang.String fecha;

        private java.lang.Boolean despublicado;

        private java.lang.Boolean carpeta;
        
        private java.lang.String nombreZip;
        
        private java.lang.String id_mec;


        public java.lang.String getId_mec() {
			return id_mec;
		}


		public void setId_mec(java.lang.String id_mec) {
			this.id_mec = id_mec;
		}


		public java.lang.String getNombreZip() {
			return nombreZip;
		}


		public void setNombreZip(java.lang.String nombreZip) {
			this.nombreZip = nombreZip;
		}


		public BusquedasGestionVO() {
        }


		public java.lang.String getNombreTarea() {
			return nombreTarea;
		}


		public void setNombreTarea(java.lang.String nombreTarea) {
			this.nombreTarea = nombreTarea;
		}

		public java.lang.String getNombreLote() {
			return nombreLote;
		}

		public void setNombreLote(java.lang.String nombreLote) {
			this.nombreLote = nombreLote;
		}

		public java.lang.String getDescripcionTarea() {
			return descripcionTarea;
		}

		public void setDescripcionTarea(java.lang.String descripcionTarea) {
			this.descripcionTarea = descripcionTarea;
		}

		public java.lang.String getFecha() {
			return fecha;
		}

		public void setFecha(java.lang.String fecha) {
			this.fecha = fecha;
		}

		public java.lang.Boolean getDespublicado() {
			return despublicado;
		}

		public void setDespublicado(java.lang.Boolean despublicado) {
			this.despublicado = despublicado;
		}

		public java.lang.Boolean getCarpeta() {
			return carpeta;
		}

		public void setCarpeta(java.lang.Boolean carpeta) {
			this.carpeta = carpeta;
		}
    }

}