// license-header java merge-point
package es.pode.administracion.presentacion.gestionCargas.despublicarCargasEjecutadas;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.planificador.negocio.servicios.InformacionCargaVO;
import es.pode.planificador.negocio.servicios.OdeCargaVO;
import es.pode.planificador.negocio.servicios.TareaDespublicarODEsVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.administracion.presentacion.gestionCargas.despublicarCargasEjecutadas.DespublicarCargasEjecutadasController
 */
public class DespublicarCargasEjecutadasControllerImpl extends DespublicarCargasEjecutadasController
{


	private static Logger logger = Logger.getLogger(DespublicarCargasEjecutadasControllerImpl.class);

	private java.util.Properties pSpringProperties = null;

    /**
     * @see es.pode.administracion.presentacion.gestionCargas.despublicarCargasEjecutadas.DespublicarCargasEjecutadasController#obtenerTexto(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.gestionCargas.despublicarCargasEjecutadas.ObtenerTextoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerTexto(ActionMapping mapping, es.pode.administracion.presentacion.gestionCargas.despublicarCargasEjecutadas.ObtenerTextoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String entrada=form.getEntrada();
    	 try{
	    	logger.debug("Obtenemos los datos");
	    	Long id=form.getId();
	    	
	    	String nombreTarea=form.getNombreTarea();
	    	String nombreCarpeta=form.getNombreCarpeta();
//	    	String nombreLote=form.getNombreLote();
	    	form.setFechaFin(form.getFechaFin());
//	    	String nombreZip= form.getNombreZip();
//	    	String id_mec=form.getId_mec();
	    	form.setId_mec(form.getId_mec());
	    	form.setNombreZip(form.getNombreZip());
	    	
	    	logger.debug("Obtenemos  el identificador de la tarea "+id+" , hemos entrado desde "+entrada+" el nombre de la tarea "+nombreTarea+" y el nombre de carpeta "+nombreCarpeta);
    	 }catch (Exception e) {
 	        	logger.error("Se ha producido un error al obtener los datos: " + e);
 	        	if(entrada!=null && !entrada.equals(""))
 	        		form.setEntrada(entrada);
 	        	else
 	        		form.setEntrada("Lote");
 	        	
 		}
    	
     }
    /**
     * @see es.pode.administracion.presentacion.gestionCargas.despublicarCargasEjecutadas.DespublicarCargasEjecutadasController#obtenerEntrada(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.gestionCargas.despublicarCargasEjecutadas.ObtenerEntradaForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String obtenerEntrada(ActionMapping mapping, es.pode.administracion.presentacion.gestionCargas.despublicarCargasEjecutadas.ObtenerEntradaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String vuelta="Carpeta";
    	String entrada=form.getEntrada();
    	form.setNombreCarpeta(form.getNombreCarpeta());
		form.setNombreLote(form.getNombreLote());
		form.setNombreTarea(form.getNombreTarea());
		form.setFechaFin(form.getFechaFin());
		form.setNombreZip(form.getNombreZip());
    	form.setId_mec(form.getId_mec());
    	 try{
    		 if(entrada!=null && !entrada.equals("") && entrada.equals("Lote")){
    			 form.setId(form.getId());
    			 return "Lote";
    		 }
    	 }catch (Exception e) {
 	        	logger.error("Se ha producido un error al obtener la entrada: " + e);
 	        	
 	        	return "Carpeta";
 		}

    	 return vuelta;
    }

    /**
     * @see es.pode.administracion.presentacion.gestionCargas.despublicarCargasEjecutadas.DespublicarCargasEjecutadasController#despublicarIdentificadores(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.gestionCargas.despublicarCargasEjecutadas.DespublicarIdentificadoresForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.Boolean despublicarIdentificadores(ActionMapping mapping, es.pode.administracion.presentacion.gestionCargas.despublicarCargasEjecutadas.DespublicarIdentificadoresForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
	    	Long id=form.getId();
	    	logger.debug("Identificador de la tarea "+id);//InformacionCargaVO[] carpetas = servicio.obtenerCarpetasDeRegistro(id);
	    	
	    	
	    //	Object[] identificadoresObjetos=form.getIdentificadoresAsArray();
	    //	logger.debug("identificadoresObjetos "+identificadoresObjetos);
//	    	String[] identificadores =new String[identificadoresObjetos.length];
//	    	logger.debug("identificadores "+identificadores);
//	    	for(int i=0;i<identificadoresObjetos.length;i++){
//	    		identificadores[i]=(String)identificadoresObjetos.toString();
//	    		logger.debug("creacion tarea!!! "+identificadores[i]);
//	    	}
	    	TareaDespublicarODEsVO tarea=new TareaDespublicarODEsVO();
	    	logger.debug("creo TareaDespublicarODEsVO");
	    	tarea.setIdTareaCarga(id);
	    	tarea.setDesdePortal(Boolean.TRUE);
	    	Calendar calendarioInicio = new GregorianCalendar(2008, Calendar.JANUARY, 1);
	    	calendarioInicio.set(Calendar.HOUR_OF_DAY, 1);
	   	 	calendarioInicio.set(Calendar.MINUTE, 1);
	        calendarioInicio.set(Calendar.SECOND, 1);
	        calendarioInicio.set(Calendar.MILLISECOND, 1);
	   	 	tarea.setFechaInicio(calendarioInicio);
	   	 	tarea.setPeriodicidad("N");
	   	    tarea.setUsuario(LdapUserDetailsUtils.getUsuario());
	   	 	
	   	 //	logger.debug("tarea.getIdentificadores().length "+tarea.getIdentificadores().length);
	   	 	tarea.setGrupoTrabajo("GrupoTareas");
	   	 	String nombreTarea=form.getNombreTarea();
	   	 	String nombreCarpeta=form.getNombreCarpeta();
	   	 	String nombreZip=form.getNombreZip();
	   	 	String id_mec=form.getId_mec();
	   	 	Collection<String> identificadores =new ArrayList<String>();
	   	 	if(nombreZip !=null && ! nombreZip.equals("")){
	   	 		tarea.setTrabajo(nombreZip+"!!"+System.currentTimeMillis());
	   	 		identificadores.add(id_mec);
	   	 	}
	   	 	else if(nombreCarpeta!=null && !nombreCarpeta.equals("")){
	   	 		tarea.setTrabajo(nombreCarpeta+"!!"+System.currentTimeMillis());
	   	 		InformacionCargaVO[] carpetas = this.getSrvPlanificadorService().obtenerCarpetasDeRegistro(id);
		       boolean encontrado=false;
		       for(int j=0;j<carpetas.length && !encontrado;j++){
	    		   String nombrePath=carpetas[j].getPathOde();
	    		   if(nombrePath.equals(nombreCarpeta)){
	    			   encontrado=true;
	    			   String[] identificadoresTodos=carpetas[j].getIdentificadores();
	    			   for(int i=0;i< identificadoresTodos.length;i++){
	    		    	   String identificador=identificadoresTodos[i].toString();
	    		    	   identificadores.add(identificador);
	    		       }
	    		   }
	    	   }
	   	 	}else{
	   	 		tarea.setTrabajo(nombreTarea+"!!"+System.currentTimeMillis());
		   	 	OdeCargaVO[] odes = this.getSrvPlanificadorService().obtenerODEsPublicadosEnCarga(id);
		    	logger.debug("Hemos obtenido "+odes.length+" identificadores para  despublicar");
		       for(int i=0;i< odes.length;i++){
		    	  identificadores.add(odes[i].getId());
//		    	  logger.debug("identificadores[i] "+identificadores[i]);
		       }
	   	 	}
	   	 	String[] identificadoresArray=identificadores.toArray(new String[0]);
	   	 	tarea.setIdentificadores(identificadoresArray);
	   	 	tarea.setTrigger("Disp-"+System.currentTimeMillis());
	   	 	tarea.setGrupoTrigger("GrupoProg");
	   	   	tarea.setTipoTarea("DespublicarODEs");
	   	 	TareaDespublicarODEsVO creada = this.getSrvPlanificadorService().crearTareaDespublicarODEs(tarea);
	    	String entrada=form.getEntrada();
	    	 logger.debug("entrada "+entrada);
	    	return Boolean.TRUE;
    	} catch (Exception e) {
	        	logger.error("Error al despublicar los identificadores ",e);
	        	return Boolean.FALSE;

    	}
    }

    /**
     * @see es.pode.administracion.presentacion.gestionCargas.despublicarCargasEjecutadas.DespublicarCargasEjecutadasController#obtenerAccion(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.gestionCargas.despublicarCargasEjecutadas.ObtenerAccionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String obtenerAccion(ActionMapping mapping, es.pode.administracion.presentacion.gestionCargas.despublicarCargasEjecutadas.ObtenerAccionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
//    	String vuelta="Cancelar";
    	String entrada=form.getEntrada();
    	 try{
	    	String accionAceptar=form.getAccionAceptar();
//	    	String accionCancelar=form.getAccionCancelar();
	    	
	    	if(accionAceptar!=null && !accionAceptar.equals("")){
	    		
	    		logger.debug("Obtenemos los datos");
	        	Long id=form.getId();
	        	
	        	String nombreTarea=form.getNombreTarea();
	        	String nombreCarpeta=form.getNombreCarpeta();
	        	String nombreZip= form.getNombreZip();
		    	String id_mec=form.getId_mec();
	        	logger.debug("Obtenemos  el identificador de la tarea "+id+" , hemos entrado desde "+entrada+" el nombre de la tarea "+nombreTarea+" y el nombre de carpeta "+nombreCarpeta+" el nombre del zip "+nombreZip+" y el identificador mec "+id_mec);
	        	
	        	return  "Aceptar";
	    		
	    	}
			form.setNombreCarpeta(form.getNombreCarpeta());
			form.setNombreLote(form.getNombreLote());
			form.setNombreTarea(form.getNombreTarea());
			form.setFechaFin(form.getFechaFin());
			form.setEntrada(entrada);
			return "Cancelar";
    	 }catch (Exception e) {
 	        	logger.error("Se ha producido un error al obtener la accion: " + e);
 	        	if(entrada!=null && !entrada.equals(""))
 	        		form.setEntrada(entrada);
 	        	else
 	        		form.setEntrada("Lote");
 	        	return "Cancelar";
 		}
        
         
    }







    /**
     * @see es.pode.administracion.presentacion.gestionCargas.despublicarCargasEjecutadas.DespublicarCargasEjecutadasController#obtenerVuelta(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.gestionCargas.despublicarCargasEjecutadas.ObtenerVueltaForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String obtenerVuelta(ActionMapping mapping, es.pode.administracion.presentacion.gestionCargas.despublicarCargasEjecutadas.ObtenerVueltaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String vuelta="Lote";
    	String entrada=form.getEntrada();
    	 try{
    		 if(entrada!=null && !entrada.equals("") && entrada.equals("Carpeta")){
    			 form.setId(form.getId());
    			 form.setNombreTarea(form.getNombreTarea());
//        		 String nombreTarea = request.getParameter("nombreTarea");
        		 form.setNombreLote(form.getNombreLote());
//        		 String nombreLote = request.getParameter("nombreLote");
        		 form.setFechaFin(form.getFechaFin());
    			 return "Carpeta";
    		 }
    	 }catch (Exception e) {
 	        	logger.error("Se ha producido un error al obtener la vuelta: " + e);
 	        	
 	        	return "Lote";
 		}

    	 return vuelta;
	    	
        
    }

	public String getPropertyValue(String sKey) {
		try{
    		InputStream fIsSpringProperties = this.getClass().getResourceAsStream("/spring_portaladministracion.properties");
			if (this.pSpringProperties == null) {
				pSpringProperties = new java.util.Properties();
				pSpringProperties.load(fIsSpringProperties);
			}
			fIsSpringProperties.close();
			logger.debug("VerUsuarioPublico - getPropertyValue: Propiedad recuperada: " + sKey + " : "+ pSpringProperties.getProperty(sKey));
//				 devolvemos la propiedad
            
        }catch (Exception e){
 		logger.error("VerUsuarioPublico - Error recuperando propiedad de spring_portaladministracion.properties=",e);
        }
		
		return pSpringProperties.getProperty(sKey);
	}





}