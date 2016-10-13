/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.publicacion.negocio.servicios;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.pode.publicacion.negocio.dominio.ContenidoInapropiado;
import es.pode.publicacion.negocio.soporte.I18nModuloPublicacion;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;


/**
 * @see es.pode.publicacion.negocio.servicios.SrvContenidoInapropiadoService
 */

public class SrvContenidoInapropiadoServiceImpl
    extends es.pode.publicacion.negocio.servicios.SrvContenidoInapropiadoServiceBase
{

	protected final static String COMENTARIO_RESULTADO_CORRECTO_CI="COM_OK_CI";
	protected final static String COMENTARIO_RECHAZO_CI="COM_REZ_CI";
	
	/**
     * @see es.pode.publicacion.negocio.servicios.SrvContenidoInapropiadoService#crearContenidoInapropiado(java.lang.String, java.lang.String, java.util.Calendar, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
	
	protected boolean handleCrearContenidoInapropiado(String idOde, String titulo, Calendar fecha, String usuario, String comentario, String estado, String idioma_cat) throws Exception {
		  boolean resultadoCreacion=false;
    	  
		  try{
        	//FALTA COMPROBAR QUE REALMENTE EL ODE ESTA PUBLICADO!!!
        	//El mismo usuario no puede hacer dos reportes de ci para el mismo ode.
        	//si no está inactivado!!!!!!!!
        	//////////////////////////////
        	EstadoVO estvo = this.getSrvPublicacionService().obtenEstadoPorIdODE(idOde, idioma_cat);//cuidado con el idioma despues!!!
	        //si el ode tiene estado publicado!! podremos despublicarlo sino no!!
	        if (estvo !=null && estvo.getClave()!=null && estvo.getClave().equals(AgregaProperties.PUBLICADO)) {
	        	CriteriaCIUsuarioOdeVO criteriavo = new CriteriaCIUsuarioOdeVO();
		    	criteriavo.setIdOde(idOde);
		    	criteriavo.setEstado(estado);
		    	criteriavo.setUsuario(usuario);
		    	criteriavo.setFecha_inactividad(null);
		    	//comprobamos si existe alguna entrada para ese ode con ese usuario
		    	Collection<?> datos=this.getContenidoInapropiadoDao().obtenerCIdeUsuarioOdeHQL(criteriavo);
		    	
		    	ContenidoInapropiadoVO[] continaUsu= datos.toArray(new ContenidoInapropiadoVO[datos.size()]);
		    	boolean estaActivo=false;
	    		
		    	//tengo q recorrerla estructura y si veo alguno activo, estado true y fecha_inactividad null  entonces no se puede crear!!!
		    	if (continaUsu!=null && continaUsu.length>0){
		    		int ici=0;
		    		while (ici<continaUsu.length && !estaActivo){
		    			if (continaUsu[ici]!=null && continaUsu[ici].isEstado_ci() && continaUsu[ici].getFecha_inactividad()==null){
		    				estaActivo=true;
		    			}
		    			ici++;
		    		}		    		
		    	}
		    	if (!estaActivo){
	    			//no se ha encontrado y puedo crearlo
	    			logger.info("Este usuario no tiene datos, insertamos los datos en la tabla ");
		    		ContenidoInapropiado contina=this.getContenidoInapropiadoDao().create(idOde, titulo, fecha, usuario, comentario, estado, true, null, idioma_cat);
		    		resultadoCreacion=true;
		    		
	    		} else {
	    			logger.info("el usuario ya existe ");
	    			resultadoCreacion=false;
	    		}
	        } else {
	        	//no existe el ode en transicion, error!!
	        	logger.error("El Ode no existe en la tabla de Transición. ");
	        	resultadoCreacion=false;
	        }
	    	
        } catch (Exception e) {
			logger.error("Se ha producido un error en la creación del contenido inapropiado: " + e);
			resultadoCreacion=false;
		}
        
		return resultadoCreacion;
	}

	
    /**
     * @see es.pode.publicacion.negocio.servicios.SrvContenidoInapropiadoService#obtenerContenidosInapropiados()
     */
    protected es.pode.publicacion.negocio.servicios.ContenidoInapropiadoVO[] handleObtenerContenidosInapropiados()
			throws java.lang.Exception {
    	
    	ContenidoInapropiadoVO[] resCIarr = null;
		try {
			resCIarr = null;
			CriteriaContenidoInapropiadoVO criteriavo = new CriteriaContenidoInapropiadoVO();
			criteriavo.setAgruparPoridOde("");
			criteriavo.setEstado("");
	
			Collection<?> datos = this.getContenidoInapropiadoDao().obtenerTodosCIXOdeHQL(criteriavo);
			if(datos!=null) {
				resCIarr = datos.toArray(new ContenidoInapropiadoVO[datos.size()]);
				logger.debug("este es el resultado " + datos );
			} else {
				resCIarr=new ContenidoInapropiadoVO[0];
			}
		} catch (Exception e) {
			throw new Exception("Error al obtener los contenidos inapropiados " + e);
		}
		return resCIarr;
	}
    
    public static String formato = "yyyy-MM-dd HH:mm:ss";//"yyyy-MM-dd'T'HH:mm"; // 2008-09-18T16:20:35+0200
    public static String formatoSinHoras = "yyyy-MM-dd";
    
    static public Calendar calendarFromStringSinHoras(String fecha) {
		//SimpleDateFormat sdf = new SimpleDateFormat(formatoSinHoras);
    	SimpleDateFormat sdf = new SimpleDateFormat(formato);
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.HOUR_OF_DAY,0);
		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.SECOND, 0);
		ca.set(Calendar.MILLISECOND, 0);
		try{
			ca.setTime(sdf.parse(fecha));
		} catch (Exception e) {

		}
	return ca;
    }
    
    /**
	 * @see es.pode.publicacion.negocio.servicios.SrvContenidoInapropiadoService#obtenerContenidoInapropiadodeOde(java.lang.String)
	 */
    protected ContenidoInapropiadoVO[] handleObtenerContenidoInapropiadodeOde(String idOde, String estado, boolean estado_ci, String fecha_inactividad, String idioma_cat) throws Exception {
    	ContenidoInapropiadoVO[] resCIarr = null;
        //@todo implement protected es.pode.publicacion.negocio.servicios.ContenidoInapropiadoVO[] handleObtenerContenidoInapropiadodeOde(java.lang.String idOde)
    	try {
    		CriteriaCIDetalleOdeVO detalleidOde = new CriteriaCIDetalleOdeVO();
    		detalleidOde.setAgruparPorUsuario("");
    		detalleidOde.setIdOde(idOde);
    		detalleidOde.setEstado(estado);
    		detalleidOde.setEstado_ci(estado_ci);   
    		detalleidOde.setIdioma_cat(idioma_cat);
    	
    		if (fecha_inactividad!=null && !fecha_inactividad.equals("")){
    			Calendar calendar= calendarFromStringSinHoras(fecha_inactividad);
        		detalleidOde.setFecha_inactividad(calendar);
    		} else {
    			detalleidOde.setFecha_inactividad(null);
    		}
    		
    		//detalleidOde.setFecha_inactividad(null); SI ES NULL NO FALLA
    		Collection<?> datos = this.getContenidoInapropiadoDao().obtenerDetalleCIdeOdeHQL(detalleidOde);
    		resCIarr= datos.toArray(new ContenidoInapropiadoVO[datos.size()]);
    		logger.info(" ESTE ES EL RESULTADO " + Arrays.toString(resCIarr));
    	} catch (Exception e) {
    		logger.error(e);
		}
        return resCIarr;
    }

    /**
     * @see es.pode.publicacion.negocio.servicios.SrvContenidoInapropiadoService#eliminarContenidoInapropiado(java.lang.String)
     */
    protected void handleEliminarContenidoInapropiado(String idOde, String fecha_inactividad, boolean estado_ci, String estado, String idioma_cat) throws Exception {        
    
    	try {
//    		tengo que traerme una consulta con todos los reportes de CI para ese idOde
	        EstadoVO estvo = this.getSrvPublicacionService().obtenEstadoPorIdODE(idOde, idioma_cat);//cuidado con el idioma despues!!!
	        
	        if (estvo!=null && (estvo.getClave().equals(AgregaProperties.PUBLICADO) || (estvo.getClave().equals(AgregaProperties.NO_DISPONIBLE)))) {
	        	CriteriaCIIdOdeVO criteriavo = new CriteriaCIIdOdeVO();
		        criteriavo.setIdOde(idOde);
		        criteriavo.setEstado(estado);//Odes q tengan estado 3--PUBLICADO
		        criteriavo.setEstado_ci(estado_ci);
		        if (fecha_inactividad!=null && !fecha_inactividad.equals("")) {
		        	Calendar calendar= calendarFromStringSinHoras(fecha_inactividad);    		
	 	    		criteriavo.setFecha_inactividad(calendar);
		        } else {
		        	criteriavo.setFecha_inactividad(null);
		        }
		       
	    		// En este caso solo vamos a eliminar la entrade de la tabla contenidos inapropiados
	        	this.getContenidoInapropiadoDao().eliminarCIPoridOde(criteriavo);
	    		logger.info("eliminación de la tabla de contenido inapropiado, despues de borrar");
	    		
	        }else {
	        	throw new Exception("Error Eliminando el reporte de Contenido Inapropiado. ");
	        }
    		
  
    	} catch (Exception e) {
    		//modificar despues para q lance la excepción
			logger.error("Se ha producido una excepción al eliminar el reporte con idOde " + idOde);
			throw new Exception(e);
		}
    	
    }
   
    /**
     * @see es.pode.publicacion.negocio.servicios.SrvContenidoInapropiadoService#rechazarContenidoInapropiado(java.lang.String)
     */
    protected void handleRechazarContenidoInapropiado(String idOde, String idioma_cat, String estado) throws Exception {
        
    	try{
    		
 			EstadoVO estvo = this.getSrvPublicacionService().obtenEstadoPorIdODE(idOde, idioma_cat);//cuidado con el idioma despues!!!
 	        if (estvo!=null && (estvo.getClave().equals(AgregaProperties.PUBLICADO) || (estvo.getClave().equals(AgregaProperties.NO_DISPONIBLE)))) {
 	        	CriteriaCIDetalleOdeVO detalleidOde = new CriteriaCIDetalleOdeVO();
 	    		detalleidOde.setAgruparPorUsuario("");
 	    		detalleidOde.setIdOde(idOde);
 	    		detalleidOde.setEstado(estado);
 	    		detalleidOde.setEstado_ci(true);   
 	    		detalleidOde.setFecha_inactividad(null);
 	    		detalleidOde.setIdioma_cat(idioma_cat);
 	    		 logger.debug("Estamos en rechazar contenido inapropiado. El idOde " + idOde + " idioma_cat " + idioma_cat + " estado " + estado);
 	    		 
 	    		Collection<?> datos = this.getContenidoInapropiadoDao().obtenerDetalleCIdeOdeHQL(detalleidOde);
 	    		ContenidoInapropiadoVO[] resCIarr = datos.toArray(new ContenidoInapropiadoVO[datos.size()]);
 	    		////////////// RECORREMOS Y MODIFICAMOS LOS DATOS /////////////////////////////
 	    		if (resCIarr!=null && resCIarr.length>=0){
 	    			logger.debug("Rechazar contenidos inapropiados vamos a modificar " + resCIarr.length + " elementos.");
 	    			Calendar ca = Calendar.getInstance();//fecha actual
 	    			for (int jr=0; jr<resCIarr.length; jr++){
 	    				ContenidoInapropiado contInap= this.getContenidoInapropiadoDao().load(resCIarr[jr].getId());
 	   				//Modificamos el campo ESTADO  al que que tiene el ode realmente y la fecha de inactividad
 	    			if ((estvo.getClave().equals(AgregaProperties.NO_DISPONIBLE) && resCIarr[jr].getEstado().equals(AgregaProperties.PUBLICADO)) ||
 	    				(estvo.getClave().equals(AgregaProperties.PUBLICADO) && resCIarr[jr].getEstado().equals(AgregaProperties.NO_DISPONIBLE))) {
 	    				resCIarr[jr].setEstado(estvo.getClave()); //cambiamos el estado dependiendo de donde nos llamen
 	    			}
 	    			resCIarr[jr].setEstado_ci(false);
 	    			resCIarr[jr].setFecha_inactividad(ca);
 	   				this.getContenidoInapropiadoDao().fromContenidoInapropiadoVO(resCIarr[jr], contInap);
 	   				this.getContenidoInapropiadoDao().update(contInap);								
 	   				//Se ha modificado el reporte		
 	    			}
 	    		}
 	        } 
 	      
    	}catch (Exception e) {
    		throw new Exception("Se ha producido un error al rechazar el Contenido Inapropiado. ");
		}
    }

    /**
     * @see es.pode.publicacion.negocio.servicios.SrvContenidoInapropiadoService#despublicarContenidoInapropiado(java.lang.String)
     */
    protected void handleDespublicarContenidoInapropiado(java.lang.String idOde, String idioma_cat)
        throws java.lang.Exception
    {
        
    	try {
	        //tengo que traerme una consulta con todos los reportes de CI para ese idOde
	        EstadoVO estvo = this.getSrvPublicacionService().obtenEstadoPorIdODE(idOde, idioma_cat);//cuidado con el idioma despues!!!
	        //si el ode tiene estado publicado!! podremos despublicarlo sino no!!	        
		    CriteriaCIDetalleOdeVO criteriavo = new CriteriaCIDetalleOdeVO();
		    criteriavo.setIdOde(idOde);
		    criteriavo.setEstado(estvo.getClave());//Odes q tengan estado 3--PUBLICADO
		    criteriavo.setAgruparPorUsuario("");
		    criteriavo.setEstado_ci(true);
		    criteriavo.setFecha_inactividad(null);
		    criteriavo.setIdioma_cat(idioma_cat);
		    
		    Collection<?> reportesIdOde=this.getContenidoInapropiadoDao().obtenerDetalleCIdeOdeHQL(criteriavo);
		    ContenidoInapropiadoVO[] contInvo = reportesIdOde.toArray(new ContenidoInapropiadoVO[reportesIdOde.size()]);
		    String titulo="";
			if (contInvo!=null && contInvo.length>0){
				titulo= contInvo[0].getTitulo();
			}
			if (estvo !=null && estvo.getClave()!=null && estvo.getClave().equals(AgregaProperties.PUBLICADO)) {    
				logger.debug("Vamos a despublicar el ode: idODE: " + idOde + "   Titulo: " + titulo);
				//este comentario tendra la referencia a que nos referimos a un reporte de CI
				String comentarios=I18nModuloPublicacion.getPropertyValueI18n(COMENTARIO_RESULTADO_CORRECTO_CI);
				//this.getResource(COMENTARIO_RESULTADO_CORRECTO_CI, BuscarAvanzadoControllerImpl.APPLICATION_PROPERTIES, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
				
				String usuario = LdapUserDetailsUtils.getUsuario(); //Se va a meter el usuario q esté solicitando la despublicación Adminitrador/Publicador
				
				//Pasamos el ode a estado no disponible
				ResultadoOperacionVO resultado = this.getSrvPublicacionService().noDisponible(idOde, usuario, comentarios, titulo);
				/////////////////////////////////////
				if (resultado!=null && resultado.getIdResultado()!=null && !resultado.getIdResultado().equals(""))
				{	
					logger.debug("Hemos despublicado el ode.");
					//ahora modificamos tb nuestra tabla!!!! para ponerle el estado despublicado
					EstadoVO cambioESt= this.getSrvPublicacionService().obtenEstadoPorIdODE(idOde, idioma_cat);//cuidado con el idioma despues!!!
					if (cambioESt!=null && cambioESt.getClave().equals(AgregaProperties.NO_DISPONIBLE)){
						//hacemos un update a nuesta tabla con este estado
						if (contInvo !=null && contInvo.length>0) {
							for (int ic=0; ic< contInvo.length; ic++) {							
								ContenidoInapropiado contInap= this.getContenidoInapropiadoDao().load(contInvo[ic].getId());
								//Modificamos el estado a no disponible
								contInvo[ic].setEstado(cambioESt.getClave());
								this.getContenidoInapropiadoDao().fromContenidoInapropiadoVO(contInvo[ic], contInap);
								this.getContenidoInapropiadoDao().update(contInap);								
							}
						}
					}
				}
	        } 

	        else {
	        	//el Ode no está publicado y no podemos modificarlo
	        	logger.debug("No se puede despublicar el ode");
	        	throw new Exception("El ode no existe en Publicados. ");
	        	// devolver un erro4r!!!! arreglar
	        }
    	} catch (Exception e) {
    		//modificar el mensaje
			logger.error("Se ha producido un error ");
			throw new Exception(e);
		}
    }
        

    /**
     * @see es.pode.publicacion.negocio.servicios.SrvContenidoInapropiadoService#despublicarContenidosInapropiados(java.lang.String[])
     */
    protected void handleDespublicarContenidosInapropiados(java.lang.String[] ids, String idioma_cat)
        throws java.lang.Exception
    {
       //para este metodo, ver si podems ut. el metodo de despublicar masivo!!!!
    	try {
    		if (ids!=null && ids.length>0){
    		  for (int id=0; id<ids.length; id++) {
		        //tengo que traerme una consulta con todos los reportes de CI para ese idOde
		        EstadoVO estvo = this.getSrvPublicacionService().obtenEstadoPorIdODE(ids[id], idioma_cat);//cuidado con el idioma despues!!!
		        //si el ode tiene estado publicado!! podremos despublicarlo sino no!!
		        if (estvo !=null && estvo.getClave()!=null && estvo.getClave().equals(AgregaProperties.PUBLICADO)) {
			        CriteriaCIDetalleOdeVO criteriavo = new CriteriaCIDetalleOdeVO();
			        criteriavo.setIdOde(ids[id]);
			        criteriavo.setEstado(estvo.getClave());//Odes q tengan estado 3--PUBLICADO
			        criteriavo.setAgruparPorUsuario("");
			        criteriavo.setEstado_ci(true);
			        criteriavo.setFecha_inactividad(null);
			        criteriavo.setIdioma_cat(idioma_cat);
			        
			        Collection<?> reportesIdOde=this.getContenidoInapropiadoDao().obtenerDetalleCIdeOdeHQL(criteriavo);
			        ContenidoInapropiadoVO[] contInvo = reportesIdOde.toArray(new ContenidoInapropiadoVO[reportesIdOde.size()]);
			        String titulo="";
					if (contInvo!=null && contInvo.length>0){
						titulo= contInvo[0].getTitulo();
					}
			        
					logger.debug("Vamos a despublicar el ode: idODE: " + ids[id] + "   Titulo: " + titulo);
					//este comentario tendra la referencia a que nos referimos a un reporte de CI
					String comentarios=I18nModuloPublicacion.getPropertyValueI18n(COMENTARIO_RESULTADO_CORRECTO_CI);
					//this.getResource(COMENTARIO_RESULTADO_CORRECTO_CI, BuscarAvanzadoControllerImpl.APPLICATION_PROPERTIES, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
					
					String usuario = /*"administrador";//*/LdapUserDetailsUtils.getUsuario(); //Se va a meter el usuario q esté solicitando la despublicación Adminitrador/Publicador
					
					//Pasamos el ode a estado no disponible
					ResultadoOperacionVO resultado = this.getSrvPublicacionService().noDisponible(ids[id], usuario, comentarios, titulo);
					if (resultado!=null && resultado.getIdResultado()!=null && !resultado.getIdResultado().equals(""))
					{	
						logger.debug("Hemos despublicado el ode.");
						//ahora modificamos tb nuestra tabla!!!! para ponerle el estado despublicado
						EstadoVO cambioESt= this.getSrvPublicacionService().obtenEstadoPorIdODE(ids[id], idioma_cat);//cuidado con el idioma despues!!!
						if (cambioESt!=null && cambioESt.getClave().equals(AgregaProperties.NO_DISPONIBLE)){
							//hacemos un update a nuesta tabla con este estado
							if (contInvo !=null && contInvo.length>0) {
								for (int ic=0; ic< contInvo.length; ic++) {
									ContenidoInapropiado contInap= this.getContenidoInapropiadoDao().load(contInvo[ic].getId());
									//Modificamos el estado al que queremos nosotros y la fecha de inactividad
									contInvo[ic].setEstado(cambioESt.getClave());
									this.getContenidoInapropiadoDao().fromContenidoInapropiadoVO(contInvo[ic], contInap);
									this.getContenidoInapropiadoDao().update(contInap);								
								}
							}
						}
					}
		        } 
    		  }//end for
    		}
    	} catch (Exception e) {
    		//modificar el mensaje
			logger.error("Se ha producido un error ");
			throw new Exception(e);
		}
    }

	@Override
	protected void handleEliminarContenidosInapropiados(ContenidoInapropiadoVO[] contenidos) throws Exception {
		
		logger.debug("Vamos a eliminar contenidos inapropiados ");
		if (contenidos != null && contenidos.length>0) {
			try {
				for (int ic=0;ic<contenidos.length;ic++){
					CriteriaCIIdOdeVO criteriavo = new CriteriaCIIdOdeVO();
					criteriavo.setIdOde(contenidos[ic].getIdOde());
					criteriavo.setEstado_ci(contenidos[ic].isEstado_ci());
					criteriavo.setFecha_inactividad(contenidos[ic].getFecha_inactividad());
					criteriavo.setIdioma_cat(contenidos[ic].getIdioma_cat());
					logger.debug("Borramos el id de la tabla " + contenidos[ic].getId().toString());
					
					this.getContenidoInapropiadoDao().eliminarCIPoridOde(criteriavo);
					
				}
			} catch (Exception e) {
				logger.info("Se ha producido un error al eliminar " + e);
				throw new Exception(e);
			}	
		}
	}

	
	protected boolean handleModificarEstadoContenidoInapropiado(String idioma_cat, String fecha_inactividad, boolean estado_ci, String estado, String idOde, String estadoNuevo) throws Exception {
		boolean modificado=false;
    	try {
//        	tengo que traerme una consulta con todos los reportes de CI para ese idOde
           // EstadoVO estvo = this.getSrvPublicacionService().obtenEstadoPorIdODE(idOde, idioma_cat);//cuidado con el idioma despues!!!
            //si el ode tiene estado publicado!! podremos despublicarlo sino no!!	        
    	    CriteriaCIDetalleOdeVO criteriavo = new CriteriaCIDetalleOdeVO();
    	    criteriavo.setIdOde(idOde);
    	    criteriavo.setEstado(estado);
    	    criteriavo.setAgruparPorUsuario("");
    	    criteriavo.setEstado_ci(estado_ci);
    	    criteriavo.setFecha_inactividad(null);
    	    criteriavo.setIdioma_cat(idioma_cat);
    	    
    	    logger.debug("srvcontenidoinapropiado - modificarEstadoContenidoInapropiado. Datos: idOde " + idOde + " fecha_inactividad " + 
    	    		fecha_inactividad + " estado_ci " + estado_ci + " estado " + estado + " idioma_cat " + idioma_cat );
    	    Collection<?> reportesIdOde=this.getContenidoInapropiadoDao().obtenerDetalleCIdeOdeHQL(criteriavo);
    	    ContenidoInapropiadoVO[] contInvo =null;
    	    if (reportesIdOde.size()>0) {
    	    	contInvo=reportesIdOde.toArray(new ContenidoInapropiadoVO[reportesIdOde.size()]);
    	    }
    	    
    	   // if (contInvo!= null && estvo !=null && estvo.getClave()!=null && estvo.getClave().equals(AgregaPropertiesImpl.NO_DISPONIBLE)){
            	//Si el Ode ha sido Despublicado en algun momento, lo ponemos como NO Disponible en la tabla de contenido inapropiado
            	if (contInvo !=null && contInvo.length>0) {
            		logger.debug("Se van a modificar " + contInvo.length + " elementos. ");
    				for (int ic=0; ic< contInvo.length; ic++) {							
    					ContenidoInapropiado contInap= this.getContenidoInapropiadoDao().load(contInvo[ic].getId());
    					//Modificamos el estado a no disponible
    					//contInvo[ic].setEstado(AgregaPropertiesImpl.NO_DISPONIBLE);
    					contInvo[ic].setEstado(estadoNuevo);
    					this.getContenidoInapropiadoDao().fromContenidoInapropiadoVO(contInvo[ic], contInap);
    					this.getContenidoInapropiadoDao().update(contInap);
    					//se ha modificado
    					modificado=true;    					
    				}
    			}
        	//}
    	} catch (Exception e) {
    		logger.error("El reporte no ha sido modificado " + e);
    		modificado=false;
		}

		return modificado;
	}


	protected boolean handleModificarTituloContenidoInapropiado(String idioma_cat, boolean estado_ci, String estado, String idOde, String tituloNuevo) throws Exception {
		boolean modificado=false;
		try{
			CriteriaCIDetalleOdeVO criteriavo = new CriteriaCIDetalleOdeVO();
		    criteriavo.setIdOde(idOde);
		    criteriavo.setEstado(estado);
		    criteriavo.setAgruparPorUsuario("");
		    criteriavo.setEstado_ci(estado_ci);
		    criteriavo.setFecha_inactividad(null);
		    criteriavo.setIdioma_cat(idioma_cat);
		    
		    logger.debug("srvcontenidoinapropiado - modificarEstadoContenidoInapropiado. Datos: idOde " + idOde + " estado_ci " + estado_ci + " estado " + estado + " idioma_cat " + idioma_cat );
		    Collection<?> reportesIdOde=this.getContenidoInapropiadoDao().obtenerDetalleCIdeOdeHQL(criteriavo);
    	    ContenidoInapropiadoVO[] contInvo =null;
    	    if (reportesIdOde.size()>0) {
    	    	contInvo=reportesIdOde.toArray(new ContenidoInapropiadoVO[reportesIdOde.size()]);
    	    }
    	    if (contInvo !=null && contInvo.length>0) {
        		logger.debug("Se van a modificar " + contInvo.length + " elementos. ");
				for (int ic=0; ic< contInvo.length; ic++) {							
					ContenidoInapropiado contInap= this.getContenidoInapropiadoDao().load(contInvo[ic].getId());
					contInvo[ic].setTitulo(tituloNuevo);
					this.getContenidoInapropiadoDao().fromContenidoInapropiadoVO(contInvo[ic], contInap);
					this.getContenidoInapropiadoDao().update(contInap);
					//se ha modificado
					modificado=true;    					
				}
			}
		    
		} catch (Exception e) {
    		logger.error("El reporte no ha sido modificado " + e);
    		modificado=false;
		}
		return modificado;
	}

}