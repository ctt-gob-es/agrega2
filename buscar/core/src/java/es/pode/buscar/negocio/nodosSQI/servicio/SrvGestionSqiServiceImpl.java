// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.buscar.negocio.nodosSQI.servicio;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;

import org.apache.log4j.Logger;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.buscar.negocio.nodosSQI.dominio.NodoSQI;
import es.pode.buscar.negocio.nodosSQI.dominio.NodoSQIDao;
import es.pode.buscar.negocio.nodosSQI.dominio.NodosSQIPorIDCriteria;
import es.pode.soporte.utiles.ficheros.UtilesFicheros;

/**
 * @see es.pode.buscar.negocio.nodosSQI.servicio.SrvGestionSQI
 */

public class SrvGestionSqiServiceImpl
    extends SrvGestionSqiServiceBase
{
	
	private static Logger logger = Logger.getLogger(SrvGestionSqiServiceImpl.class);
	private static final String LENGUAJE_CONSULTA_VSQL="VSQL";
//	private static final String LENGUAJE_CONSULTA_QEL="QEL";//Seguramente seran solo VSQL y LOM (Corregir la validacion de alta)
//	private static final String LENGUAJE_CONSULTA_FIRE="FIRE";
	private static final String LENGUAJE_RESPUESTA_LOM="LOM";
//	private static final String LENGUAJE_RESPUESTA_RDF="RDF";
	private static final String LENGUAJE_RESPUESTA_LOMES="LOM-ES";
	private static Properties props = null;
	public static final String FILE_NAME_PROPERTIES = "/buscar.properties";
	/**
	 * Metodo para dar de alta un nodo de tipo SQI. 
	 * 
	 * @param nodoSQI
	 *                NodoSQIVO con los datos del nodo de tipo SQI que se dara de alta
	 * @return Long
	 * @throws Exception
	 */
	protected Long handleAltaNodoSQI(NodoSQIVO nodoSQI) throws Exception {
		
		Long identificador=null;
		try{
			String nombre=nodoSQI.getNombreNodo();
			if(nombre==null || nombre.equals("")){				
				logger.warn("El nombre del nodo SQI es obligatorio ["+nombre+"]");
				throw new Exception("El nombre del nodo es obligatorio["+nombre+"]");
			}
			nombre=nombre.trim();
			String url=nodoSQI.getUrlServicio();
			if(url==null || url.equals("")){
				logger.warn("La URL del servicio es obligatoria ["+url+"]");
				throw new Exception("La URL del servicio es obligatorio ["+url+"]");
			}
			String desc=nodoSQI.getDescripcionNodo();
			if(desc==null || desc.equals("")){				
				logger.warn("La descripcion del nodo SQI es obligatoria ["+desc+"]");
				throw new Exception("La descripcion del nodo es obligatorio["+desc+"]");
			}
			String consulta=nodoSQI.getLenguajeConsulta().toUpperCase();//Constantes
			String respuesta=nodoSQI.getLenguajeRespuesta().toUpperCase();//Constantes
			if(consulta !=null && !consulta.equals("")){
				if(!consulta.equalsIgnoreCase(LENGUAJE_CONSULTA_VSQL )){
					logger.warn("El lenguaje de consulta debe ser de tipo "+ LENGUAJE_CONSULTA_VSQL +", ["+consulta+"]");
					throw new Exception("El lenguaje de consulta debe ser de tipo "+ LENGUAJE_CONSULTA_VSQL+ ", ["+consulta+"]");
				}
			}else{
				logger.error("El lenguaje de consulta es obligatorio ["+consulta+"]");
				throw new Exception("El lenguaje de consulta es obligatorio ["+consulta+"]");
			}
			if(respuesta !=null && !respuesta.equals("")){
				if(!respuesta.equalsIgnoreCase(LENGUAJE_RESPUESTA_LOM)&&!respuesta.equalsIgnoreCase(LENGUAJE_RESPUESTA_LOMES) ){
					logger.warn("El lenguaje de respuesta debe ser de tipo "+ LENGUAJE_RESPUESTA_LOM+ "o tipo "+LENGUAJE_RESPUESTA_LOMES+"["+respuesta+"]");
					throw new Exception("{errors.altanodoSQI.lenguajeRespuesta} ");
				}
			}else{
				logger.warn("El lenguaje de respuesta es obligatorio ["+respuesta+"]");
				throw new Exception("El respuesta de consulta es obligatorio["+respuesta+"]");
			}
			
			//Para guardarlos en la base de datos en mayuscula
			nodoSQI.setLenguajeConsulta(nodoSQI.getLenguajeConsulta().toUpperCase());
			nodoSQI.setLenguajeRespuesta(nodoSQI.getLenguajeRespuesta().toUpperCase());
			String idSesion=nodoSQI.getIdentificadorSesion();
			if(idSesion==null || idSesion.equals("")){
				String gestor=nodoSQI.getGestorSesion();
				if(gestor==null || gestor.equals("")){
					logger.warn("La URL del gestor de sesiones o el identificador de la sesion es obligatorio, identificadorSesion ["+idSesion+"] el gestor de sesiones ["+gestor+"]");
					throw new Exception("La URL del gestor de sesiones o el identificador de la sesion es obligatorio,identificadorSesion ["+idSesion+"] el gestor de sesiones  ["+gestor+"]");
				}
			}
						
			String usuario=nodoSQI.getUsuario();
			String clave=nodoSQI.getClave();
			if(usuario!=null && !usuario.equals("")){
				if(clave==null || clave.equals("")){
					logger.warn("Si existe usuario,"+usuario+", es obligatoria la clave ["+clave+"]");
					throw new Exception("Si existe usuario,"+usuario+", es obligatoria la clave ["+clave+"]");
				}
			}
			if(clave!=null && !clave.equals("")){
				if(usuario==null || usuario.equals("")){
					logger.warn("Si existe clave,"+clave+", es obligatorio el usuario ["+usuario+"]");
					throw new Exception("Si existe clave,"+clave+", es obligatorio el usuario ["+usuario+"]");
				}
			}
			
			String rutaImagen=nodoSQI.getImagenNodo();
			if(logger.isDebugEnabled())logger.debug("La imagen es <"+rutaImagen+"> y recogemos el DataHandler");
			DataHandler contenidoImagen = nodoSQI.getContenidoImagenNodo();
			
			Boolean resultado=null;
			if (contenidoImagen==null){
				nodoSQI.setImagenNodo(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.IMAGEN_DEFECTO_NODO_SQI));
			}else{
			//Vamos a generar la imagen, en el caso de que no se haya generado bien deberemos insertar un null en la base de datos de ImagenNodo 
				if(rutaImagen != null && !rutaImagen.equals("") && rutaImagen.length()>0){
					String nombreNodo=SrvGestionSqiServiceImpl.borrarAcentosYEspacios(nombre);
					
					if(logger.isDebugEnabled())logger.debug("Le quitamos los espacios y acentos a la rutaImagen "+rutaImagen+" y al nombre "+nombre);
					String nombreDeImagen=SrvGestionSqiServiceImpl.borrarAcentosYEspacios(rutaImagen);
	
					if(nombreDeImagen!=null && !nombreDeImagen.equals("") && nombreDeImagen.length()>0){
						resultado=this.guardarImagenNodoSQI(nombreNodo, contenidoImagen,nombreDeImagen);
						
						if(resultado.booleanValue()){
							logger.info("Se ha guardado correctamente la imagen");
								nodoSQI.setImagenNodo(nombreNodo+"/"+nombreDeImagen);
						}else{
							logger.warn("Ha habido algun error y no hemos podido guardar la imagen");
							nodoSQI.setImagenNodo(null);
						}
					}else{
						nodoSQI.setImagenNodo(null);
					}
				}else{
					nodoSQI.setImagenNodo(null);
				}
			}
			
			
			
			
			NodoSQI nodo = this.getNodoSQIDao().fromNodoSQIVO(nodoSQI);
			if(logger.isDebugEnabled()) {
					logger.debug("Se introduce el nodo en la base de datos");
			}
			NodoSQI nodoS = this.getNodoSQIDao().create(nodo);
			identificador=nodoS.getId();
			
		}catch(Exception e){
			logger.error("Error al dar de alta al nodo de tipo SQI con identificador [ "+nodoSQI.getId()+"]", e);
			throw new Exception("Error al dar de alta al nodo de tipo SQI con identificador [ "+nodoSQI.getId()+"]", e);
		}
		return identificador;
	}


	/**
	 * Metodo para listar los nodos de tipo SQI. 
	 * 
	 * @return NodoCortoSQIVO[]
	 * 				El NodoCortoSQIVO es un 2-tupla de identificador y la descripcion del nodo de tipo SQI
	 * @throws java.lang.Exception
	 */
    protected NodoDescripcionSQIVO[] handleListarNodosSQI()
        throws java.lang.Exception
    { 
    	try {
//    		NodoSQIVO[] nodo=(NodoSQIVO[])(this.getNodoSQIDao().listarNodosSQI(this.getNodoSQIDao().TRANSFORM_NODOSQIVO)).toArray(new NodoSQIVO[this.getNodoSQIDao().listarNodosSQI(this.getNodoSQIDao().TRANSFORM_NODOSQIVO).size()]);//Listar nodos da error
////    		NodoSQIVO[] nodo=(NodoSQIVO[])lista.toArray(new NodoSQIVO[lista.size()]);
//    		for (int i = 0; i < nodo.length; i++) {
//				System.out.println("Identificador: [" + nodo[i].getId()+"]");
//				System.out.println("Nodo: [" + nodo[i].getNombreNodo()+"]");			
//			}
    		NodoDescripcionSQIVO[] nodos = ((NodoDescripcionSQIVO[]) (this.getNodoSQIDao().listarNodosSQI(this.getNodoSQIDao().TRANSFORM_NODODESCRIPCIONSQIVO)).toArray(new NodoDescripcionSQIVO[0]));

    		if (logger.isDebugEnabled()) {		
    			for (int i = 0; i < nodos.length; i++) {
    				logger.debug("Identificador: [" + nodos[i].getId()+"] Nodo: [" + nodos[i].getNombreNodo()+"]");			
    			}
    		}
     	   
    	    return nodos;
    	    
    	} catch (java.lang.Exception e) {
    	    logger.error("Error obteniendo la lista de los nodos de tipo SQI: " + e);
    	    throw  new java.lang.Exception("Error obteniendo la lista de los nodos de tipo SQI: " ,e);
    	}
    }
    /**
	 * Metodo para dar de baja los nodos de tipo SQI con identificadores que corresponden con los del array de Long. 
	 * 
	 * @param ids un array de Long
	 * @throws Exception
	 */
	protected void handleBajaNodosSQI(Long[] ids) throws Exception {
		
    	try{
    		NodoSQI nodoV=null;
    		NodosSQIPorIDCriteria criterio=new NodosSQIPorIDCriteria(ids);
    		
    		List lista=this.getNodoSQIDao().consultarNodosSQI(criterio);
    		if(logger.isDebugEnabled())logger.debug("La lista es de longitud "+lista.size());
    		String[] imagenesNodos=new String[lista.size()];
    		for(int i=0;i<lista.size();i++){
    			nodoV=(NodoSQI)lista.get(i);
    			if(logger.isDebugEnabled())logger.debug("El nombre del nodo es "+nodoV.getId()+" de nombre "+nodoV.getNombreNodo()+" y la imagen es "+nodoV.getImagenNodo());
    			imagenesNodos[i]=nodoV.getImagenNodo();
    		}
    		
    		this.getNodoSQIDao().remove(lista);
    		
    		//Eliminamos las imagenes correspondientes a los nodos eliminados
    		for(int j=0;j<imagenesNodos.length;j++){
    			if(logger.isDebugEnabled())logger.debug("Vamos a borrar las imagenes <"+imagenesNodos[j]+">");
    			if(imagenesNodos[j]!=null && !imagenesNodos[j].equals("")){
    				String nombreNodo="";
    				int posicion=imagenesNodos[j].lastIndexOf("/");
    				if(posicion>1){//Vamos a recoger el nombre del nodo sin espacios ni tildes
    					nombreNodo=imagenesNodos[j].substring(0, posicion);
    				}
    				String rutaImagenAntiguo=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_IMAGENES_NODOS_SQI)+nombreNodo;
    				if(logger.isDebugEnabled())logger.debug("Vamos a borrar la ruta de las imagenes <"+rutaImagenAntiguo+">");
    				File rutaBorrar=new File(rutaImagenAntiguo);
    				UtilesFicheros.eliminar(rutaBorrar);
    				logger.info("Hemos borrado todas las rutas");
    			}
    		}
//    		}
	    	
    	} catch (Exception e){
    	    logger.error("Error al eliminar["+ids.length+"] nodos SQI", e);
    	    throw new Exception("Error al eliminar["+ids.length+"] nodos SQI", e);
    	}
		
	}
	/**
	 * Metodo para modificar el nodo de tipo SQI. 
	 * @param nodo Nodo de tipo NodoSQIVO
	 * @return Long
	 * 				El identificador del nodo que se modifica
	 * @throws Exception
	 */
	protected Long handleModificarNodoSQI(NodoSQIVO nodo) throws Exception {
		NodoSQI nodoV=null;
		try{
			NodosSQIPorIDCriteria criterio=new NodosSQIPorIDCriteria();
			Long[] identi=new Long[1];
			identi[0]=nodo.getId();
			criterio.setIds(identi);
			List nodos = this.getNodoSQIDao().consultarNodosSQI(criterio);
			nodoV=(NodoSQI)nodos.get(0);
			Boolean cambioNombre=Boolean.FALSE;
			String propiedadRuta=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_IMAGENES_NODOS_SQI);
	    	if(nodoV!=null){
	    		String nombre=nodo.getNombreNodo();
		    	if(nombre==null || nombre.equals("")){				
					logger.warn("El nombre del nodo SQI es obligatorio ["+nombre+"]");
					throw new Exception("El nombre del nodo es obligatorio["+nombre+"]");
				}
		    	nombre=nombre.trim();
		    	if(!nodoV.getNombreNodo().equals(nombre)){
		    		cambioNombre=Boolean.TRUE;
		    	}
		    	nodoV.setNombreNodo(nombre);
		    	String url=nodo.getUrlServicio();
				if(url==null || url.equals("")){
					logger.warn("La URL del servicio es obligatorio ["+url+"]");
					throw new Exception("La URL del servicio es obligatorio ["+url+"]");
				}
		    	nodoV.setUrlServicio(url);
		    	
		    	String desc=nodo.getDescripcionNodo();
				if(desc==null || desc.equals("")){				
					logger.warn("La descripcion del nodo SQI es obligatorio ["+desc+"]");
					throw new Exception("La descripcion del nodo es obligatorio["+desc+"]");
				}
		    	nodoV.setDescripcionNodo(desc);
		    	
		    	String consulta=nodo.getLenguajeConsulta().toUpperCase();//Constantes
				String respuesta=nodo.getLenguajeRespuesta().toUpperCase();//Constantes
				if(consulta !=null && !consulta.equals("")){
					if(!consulta.equalsIgnoreCase(LENGUAJE_CONSULTA_VSQL)){
						logger.warn("El lenguaje de consulta debe ser de tipo "+ LENGUAJE_CONSULTA_VSQL+ ", ["+consulta+"]");
						throw new Exception("El lenguaje de consulta debe ser de tipo "+ LENGUAJE_CONSULTA_VSQL+ ", ["+consulta+"]");
					}
				}else{
					logger.warn("El lenguaje de consulta es obligatorio ["+consulta+"]");
					throw new Exception("El lenguaje de consulta es obligatorio ["+consulta+"]");
				}
				if(respuesta !=null && !respuesta.equals("")){
					if(!respuesta.equalsIgnoreCase(LENGUAJE_RESPUESTA_LOM)&&!respuesta.equalsIgnoreCase(LENGUAJE_RESPUESTA_LOMES) ){
						logger.warn("El lenguaje de respuesta debe ser de tipo "+ LENGUAJE_RESPUESTA_LOM+ "o tipo "+LENGUAJE_RESPUESTA_LOMES+"["+respuesta+"]");
						throw new Exception("{errors.altanodoSQI.lenguajeRespuesta} ");
					}
				}else{
					logger.warn("El lenguaje de respuesta es obligatorio ["+respuesta+"]");
					throw new Exception("El respuesta de consulta es obligatorio["+respuesta+"]");
				}
				nodoV.setLenguajeConsulta(consulta);
		    	nodoV.setLenguajeRespuesta(respuesta);
				String idSesion=nodo.getIdentificadorSesion();
				String gestor=nodo.getGestorSesion();
				if(idSesion==null || idSesion.equals("")){
					gestor=nodo.getGestorSesion();
					if(gestor==null || gestor.equals("")){
						logger.warn("La URL del gestor de sesiones o el identificador de la sesion es obligatorio, identificadorSesion ["+idSesion+"] el gestor de sesiones ["+gestor+"]");
						throw new Exception("La URL del gestor de sesiones o el identificador de la sesion es obligatorio,identificadorSesion ["+idSesion+"] el gestor de sesiones  ["+gestor+"]");
					}
				}
		    	nodoV.setIdentificadorSesion(idSesion);
		    	nodoV.setGestorSesion(gestor);
		    	
	    		String clave=nodo.getClave();
	    		String usuario=nodo.getUsuario();
	    		if(clave!=null && !clave.equals("")){
					if(usuario==null || usuario.equals("")){
						logger.warn("Si existe clave,"+clave+", es obligatorio el usuario ["+usuario+"]");
						throw new Exception("Si existe clave,"+clave+", es obligatorio el usuario ["+usuario+"]");
					}
				}
	    		if(usuario!=null && !usuario.equals("")){
					if(clave==null || clave.equals("")){
						logger.warn("Si existe usuario,"+usuario+", es obligatoria la clave ["+clave+"]");
						throw new Exception("Si existe usuario,"+usuario+", es obligatoria la clave ["+clave+"]");
					}
				}
	    		nodoV.setClave(clave);
	    		nodoV.setUsuario(usuario); 
	    		logger.info("La imagen se cambia a <"+nodo.getImagenNodo()+">");
	    		String rutaImagen=nodo.getImagenNodo();
	    		DataHandler contenidoImagen = nodo.getContenidoImagenNodo();
	    		logger.info("El datahandler que recibimos es <"+contenidoImagen+">");
	    		String imagenAntiguo=nodoV.getImagenNodo();
	    		String nombreNodo=SrvGestionSqiServiceImpl.borrarAcentosYEspacios(nombre);
	    		Boolean resultado=Boolean.FALSE;
	    		if(imagenAntiguo==null || imagenAntiguo.equals("") || imagenAntiguo.length()==0){//No tenia imagen
	    			if(logger.isDebugEnabled())logger.debug("No tenia imagen");
	    			if(rutaImagen==null || rutaImagen.equals("")|| rutaImagen.length()==0){//No le han metido una imagen nueva
	    				if(logger.isDebugEnabled())logger.debug("No le han insertado una imagen nueva");
	    				nodoV.setImagenNodo(null);
	    			}else{//Le han metido imagen nueva;Tenemos que crearmos la carpeta y la imagen
	    				logger.info("Le han insertado una imagen nueva, antes no tenia ninguna");
	    				String nombreDeImagen=SrvGestionSqiServiceImpl.borrarAcentosYEspacios(rutaImagen);
	    				if(logger.isDebugEnabled())logger.debug("Le vamos a crear la carpeta con la imagen con nombre del nodo <"+nombreNodo+" y el nombreDeImagen "+nombreDeImagen+">");
	    				boolean creado=guardarImagenNodoSQI(nombreNodo, contenidoImagen, nombreDeImagen);
	    				if(!creado){
	    					logger.warn("No se ha podido generar la imagen nueva");
	    					nodoV.setImagenNodo(null);
	    				}else{
	    					logger.info("Se ha creado la imagen nueva");
	    					nodoV.setImagenNodo(nombreNodo+"/"+nombreDeImagen);
	    				}
	    				
	    			}
	    		}else{//TenÌa imagen
	    			if(logger.isDebugEnabled())logger.debug("ya tenia imagen");
	    			if(rutaImagen==null || rutaImagen.equals("")|| rutaImagen.length()==0){//No le han metido una imagen nueva
	    				if(logger.isDebugEnabled())logger.debug("No le han insertado una nueva imagen");
	    				if(cambioNombre){
//	    					logger.debug("Ha cambiado el nombre del nodo");
	    					int posicion=imagenAntiguo.lastIndexOf("/");
	    					String nombreDeImagen="";
	    					String nombreDeCarpeta="";
	    					if(posicion>0){//Vamos a guardar la ruta son el nombre de nodo+nombreImagen
	    						nombreDeCarpeta=imagenAntiguo.substring(0, posicion);
	    						nombreDeImagen=imagenAntiguo.substring(posicion+1, imagenAntiguo.length());
	    						if(logger.isDebugEnabled())logger.debug("El nombre de la imagen sigue siendo "+nombreDeImagen+" el de la carpeta "+nombreDeCarpeta);
	    					}
//	    					String rutaNueva=nombreNodo+"/"+nombreDeImagen;//Solo el nombre del a carpeta
	    					String rutaImagenAntiguo=propiedadRuta+nombreDeCarpeta;
	    					String rutaImagenNuevo=propiedadRuta+nombreNodo;
	    					File fileRutaImagenAntiguo=new File(rutaImagenAntiguo);
	    					File fileRutaImagenNuevo=new File(rutaImagenNuevo);
	    					logger.info("Renombramos el nodo viejo "+rutaImagenAntiguo+" por el nuevo "+rutaImagenNuevo);
	    					boolean renombrar=fileRutaImagenAntiguo.renameTo(fileRutaImagenNuevo);
	    					if(!renombrar){
	    						logger.warn("Ha fallado el renombramiento de la carpeta vieja");
	    					}else{
	    						logger.info("Se ha renombrado correctamente la carpeta antigua: <"+ nombreDeImagen +">");
		    					nodoV.setImagenNodo(nombreNodo+"/"+nombreDeImagen);
	    					}
	    				}else{
	    					if(logger.isDebugEnabled())logger.debug("Mantenemos los datos que tenia antes");
	    				}
	    			}else{
//	    				logger.debug("Le han insertado una NUEVA IMAGEN para sustituir al antiguo");
	    				String nombreDeImagen=SrvGestionSqiServiceImpl.borrarAcentosYEspacios(rutaImagen);
	    				if(logger.isDebugEnabled()) logger.debug("Se le inserta una nueva imagen: <"+nombreNodo+"/"+nombreDeImagen+">");
	    				if(cambioNombre){
	    					int posicion=imagenAntiguo.lastIndexOf("/");
	    					String nombreDeCarpeta="";
	    					String nombreDeImagenAntiguo="";
	    					if(posicion>0){//Vamos a guardar la ruta son el nombre de nodo+nombreImagen
	    						nombreDeCarpeta=imagenAntiguo.substring(0, posicion);
	    						nombreDeImagenAntiguo=imagenAntiguo.substring(posicion+1,imagenAntiguo.length());
	    						if(logger.isDebugEnabled())logger.debug("El nombre de la carpeta es <"+nombreDeCarpeta+">");
	    					}
	    					logger.info("Tambien han cambiado el nombre del nodo");
	    					String rutaNueva=nombreNodo+"/"+nombreDeImagen;
	    					String rutaImagenAntiguo=propiedadRuta+nombreDeCarpeta;
	    					String rutaImagenNuevo=propiedadRuta+nombreNodo;
	    					File fileRutaImagenAntiguo=new File(rutaImagenAntiguo);
	    					File fileRutaImagenNuevo=new File(rutaImagenNuevo);
	    					logger.info("Renombramos el nodo viejo <"+rutaImagenAntiguo+"> por el nuevo <"+rutaImagenNuevo+">");
	    					boolean renombrar=fileRutaImagenAntiguo.renameTo(fileRutaImagenNuevo);
	    					if(!renombrar){
	    						logger.warn("Ha fallado el renombramiento de la carpeta vieja");
	    					}else{
	    						if(logger.isDebugEnabled())logger.debug("Se efectuado correctamente el renombramiento de la carpeta y vamos a crear la nueva imagen en el nodo "+nombreNodo+" con el nuevo nombre de la imagen "+nombreDeImagen);
		    					if(nombreDeImagen!=null && !nombreDeImagen.equals("") && nombreDeImagen.length()>0){
		    						resultado=this.guardarImagenNodoSQI(nombreNodo, contenidoImagen,nombreDeImagen);
		    						
		    						if(resultado.booleanValue()){
		    							logger.info("SE HA GUARDADO CORRECTAMENTE LA NUEVA IMAGEN");
		    							//Tenemos que borrar la imagen nueva
		    							String rutaImagenAntiguoBorrar=rutaImagenNuevo+"/"+nombreDeImagenAntiguo;
		    							File fileRutaImagenAntiguoBorrar=new File(rutaImagenAntiguoBorrar);
		    							logger.info("Borramos la imagen antigua <"+rutaImagenAntiguoBorrar+">");
		    							UtilesFicheros.eliminar(fileRutaImagenAntiguoBorrar);
//		    							boolean borrado=fileRutaImagenAntiguo.delete();
		    							if(fileRutaImagenAntiguo.exists()){
		    								logger.warn("No se ha podido eliminar la imagen antigua "+rutaImagenAntiguoBorrar);
		    							}
		    							nodoV.setImagenNodo(nombreNodo+"/"+nombreDeImagen);
		    							
		    						}else{
		    							logger.warn("No se ha podido guardar la imagen nueva");
		    						}
		    					}else{
		    						nodoV.setImagenNodo(null);
		    					}
	    					}
	    					
	    					
	    				}else{//Solo cambiamos la imagen
	    					if(logger.isDebugEnabled())logger.debug("No han cambiado el nombre del nodo, cambiamos la imagen a "+nombreDeImagen);
	    					if(nombreDeImagen!=null && !nombreDeImagen.equals("") && nombreDeImagen.length()>0){
	    						resultado=this.guardarImagenNodoSQI(nombreNodo, contenidoImagen,nombreDeImagen);
	    						
	    						if(resultado.booleanValue()){
	    							//Tenemos que borrar la imagen nueva
	    							logger.info("SE HA GUARDADO CORRECTAMENTE LA NUEVA IMAGEN EN LA CARPETA ANTIGUA, BORRAMOS LA IMAGEN ANTIGUA");
	    							String rutaImagenAntiguo=propiedadRuta+imagenAntiguo;
	    							logger.debug("Intentamos borrar la imagen obsoleta <"+rutaImagenAntiguo+">");
	    							File fileRutaImagenAntiguo=new File(rutaImagenAntiguo);
	    							UtilesFicheros.eliminar(fileRutaImagenAntiguo);
//	    							boolean borrado=fileRutaImagenAntiguo.delete();
	    							if(fileRutaImagenAntiguo.exists()){
	    								logger.warn("No se ha podido eliminar la imagen antigua "+rutaImagenAntiguo);
	    							}
	    							nodoV.setImagenNodo(nombreNodo+"/"+nombreDeImagen);
	    							
	    						}else{
	    							logger.warn("No se ha podido guardar la imagen nueva");
	    						}
	    					}else{
	    						nodoV.setImagenNodo(null);
	    					}
	    				}
	    				
	    			}
	    		}
		    	//actualizamos los datos del nodo
		    	this.getNodoSQIDao().update(nodoV);
	    	}else{
	    		logger.warn("El nodo con identificador ["+nodo.getId()+"] que se quiere modificar no existe en la base de datos");
	    		throw new Exception("El nodo con identificador ["+nodo.getId()+"] que se quiere modificar no existe en la base de datos");
	    	}
	    	
		} catch (Exception e){
			String exc=("Error al actualizar el nodo SQI con identificador ["+nodo.getId()+"],nombre del nodo ["+nodo.getNombreNodo()+"], descripcion del nodo ["+nodo.getDescripcionNodo()+"]" +
    		",lenguaje de consulta ["+nodo.getLenguajeConsulta()+"], lenguaje de respuesta ["+nodo.getLenguajeRespuesta()+"], " +
    		"la URL del servicio ["+nodo.getUrlServicio()+"],el gestor de sesiones ["+nodo.getGestorSesion()+"], " +
    		"el identificador de la sesion ["+ nodo.getIdentificadorSesion()+"], la clave ["+nodo.getClave()+"]," +
    		" y el usuario ["+nodo.getUsuario()+"]") ;
		    logger.error(exc,e);
		    throw new Exception(exc,e);
		}
		
		return nodoV.getId();
	}

	/**
	 * Metodo para consultar los nodos de tipo SQI que coincidan con los identificadores del array de Long. 
	 * @param ids un array de Long
	 * @return NodoSQIVO[]
	 * 				Un array de NodoSQIVO
	 * @throws Exception
	 */

	protected NodoSQIVO[] handleConsultaNodosSQI(Long[] ids) throws Exception {
		NodoSQIVO[] todosNodos=null;
		try{
			NodosSQIPorIDCriteria criterio=new NodosSQIPorIDCriteria();
			criterio.setIds(ids);
			List listaNodos=new ArrayList();
			listaNodos =this.getNodoSQIDao().consultarNodosSQI(this.getNodoSQIDao().TRANSFORM_NODOSQIVO,criterio);
			todosNodos=(NodoSQIVO[])(listaNodos.toArray(new NodoSQIVO[listaNodos.size()]));
			if(logger.isDebugEnabled())logger.debug("Obtenemos todos los nodos de longitud "+todosNodos.length);
			for(int i=0;i<todosNodos.length;i++){
				if(todosNodos[i].getImagenNodo()!=null && !todosNodos[i].getImagenNodo().equals("")){
				String imagenCorta=todosNodos[i].getImagenNodo();
				
				String imagen=AgregaPropertiesImpl.getInstance().getUrlNodo()+AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.IMAGENES_NODOS_SQI_URL)+"/"+imagenCorta;
				todosNodos[i].setImagenNodo(imagen);
				}else{
					todosNodos[i].setImagenNodo(null);
				}
			}
		}catch(Exception e){
			logger.error("Error al hacer la consulta para los nodos ["+ Arrays.toString(ids)+"]",e);
			throw new Exception("Error al hacer la consulta para los nodos ["+ Arrays.toString(ids)+"]");
		}
		return todosNodos;
	}


	/**
	 * Este metodo resuelve la b˙squeda SQI sobre los nodos federados en la plataforma que implementan el interfaz SQI. 
	 * @return NodoSQIVO[]
	 * 				Un array de NodoSQIVO con todos los nodos dados de alta en la plataforma.
	 * @throws Exception
	 */
	protected NodoSQIVO[] handleObtenerNodosSQI() throws Exception {
		return (NodoSQIVO[])this.getNodoSQIDao().loadAll(this.getNodoSQIDao().TRANSFORM_NODOSQIVO).toArray(new NodoSQIVO[0]);
	}


	 /**
     * Metodo que comprueba si un nodoSQI ya creado tiene un nombre de nodo que se intenta dar de alta.
     * 
     * @param nombreNodo String. Nombre del nodo
     * @param id Long. Nos indica si estamos dando de alta a un nuevo nodo, nos llegar· el -1, o si estamos modificando un nodo, en este caso nos llegar· el identificador del nodo que queremos modificar
     * @return Boolean se devuelve true si existe un nodo con ese nombre y false si no existe
     * @throws java.lang.Exception
     */
	protected Boolean handleExisteNombreNodoSQI(String nombreNodo,Long id)
			throws Exception {
		Boolean resultado = false;

		if (id.compareTo(-1L) == 0)
		{
			//Comprobamos si nombre de nodo ya esta dado de alta en la BD
			NombreNodoSQICriteria criteria = new NombreNodoSQICriteria();
			criteria.setNombreNodo(nombreNodo);
			
			if(logger.isDebugEnabled())logger.debug("Se ha creado el criterio de NombreNodo para hacer la comprobacion");
			logger.info("Nombre NodoSQI = " + nombreNodo);
			
			List listaNodos = this.getNodoSQIDao().listarNodosSQIMismoNombre(criteria);
			if(logger.isDebugEnabled())logger.debug("tamaÒo de listar nodos = " + listaNodos.size());
			
			if(listaNodos != null && listaNodos.size() != 0)
			{
				resultado = true;
			}
		}else{
			Long[] ids=new Long[1];
			ids[0]=id;
			NodosSQIPorIDCriteria criterio=new NodosSQIPorIDCriteria();
			criterio.setIds(ids);
			this.getNodoSQIDao();
			List listaNodosIdentificador = this.getNodoSQIDao().consultarNodosSQI(NodoSQIDao.TRANSFORM_NODOSQIVO,criterio);
			NodoSQIVO[] todosNodos = (NodoSQIVO[])(listaNodosIdentificador.toArray(new NodoSQIVO[listaNodosIdentificador.size()]));
			NodoSQIVO nodo = todosNodos[0];
			String nombre=nodo.getNombreNodo();
			if(!nombre.equalsIgnoreCase(nombreNodo)){
				logger.debug("Ha cambiado el nombre del nodo, se verificara que no coincida con uno que ya estaba");
				//Comprobamos si nombre de nodo ya esta dado de alta en la BD
				NombreNodoSQICriteria criteria = new NombreNodoSQICriteria();
				criteria.setNombreNodo(nombreNodo);
				
				//if(logger.isDebugEnabled())logger.debug("Se ha creado el criterio de NombreNodo para hacer la comprobacion");
				logger.debug("Nombre NodoSQI = " + nombreNodo);
				
				List listaNodos = this.getNodoSQIDao().listarNodosSQIMismoNombre(criteria);
				if(logger.isDebugEnabled())logger.debug("tamaÒo de listar nodos = <" + listaNodos.size()+">");
				
				if(listaNodos != null && listaNodos.size() != 0)
				{
					resultado = true;
				}
			}else{
				logger.info("No se ha cambiado el nombre del nodo");
			}
			
		}
		
		
		
		return resultado;
	}
	 
	private Boolean guardarImagenNodoSQI(String nombreNodo, DataHandler contenidoImagen, String nombreDeImagen) throws IOException{
		Boolean resultado=true;

//		int posicion=nombreDeImagen.lastIndexOf(".");
//		String extensionImagen="";
//		if(posicion>0){//Vamos a guardar la ruta son el nombre de nodo+nombreImagen
//			extensionImagen=nombreDeImagen.substring(posicion, nombreDeImagen.length());
//		}

		DataHandler dataHandler =null;
		try{


			dataHandler = contenidoImagen;
			logger.debug("El dataHandler "+dataHandler);
		}catch(Exception e){
			logger.error("Error en la creacion del datahandler",e);
			resultado=false;
		}
		String tamaÒo=getPropertyValue("imagen.tamanioPequenio");
		String pathFileTarget="";
		try{
			String pathFileTargetUno=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_IMAGENES_NODOS_SQI)+nombreNodo;
			pathFileTarget=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_IMAGENES_NODOS_SQI)+nombreNodo+"/"+nombreDeImagen;
			//+extensionImagen;
			logger.debug("Vamos a dejar la imagen redimensionada al final en <"+pathFileTarget+">");
			File pathFileUno=new File(pathFileTargetUno);
			boolean creado=false;
			if(!pathFileUno.exists()){
				creado=pathFileUno.mkdir();
			}else{
				creado=true;
				logger.debug("La carpeta existia <"+pathFileUno+">");
			}
			if(creado){
				creado=false;
				File pathFile=new File(pathFileTarget);
				pathFile.createNewFile();
				if(!pathFile.exists()){
					logger.warn("No se ha creado el archivo "+pathFile);
					return false;
				}
				creado=true;
				logger.debug("La carpeta ya existia <"+pathFile+">");
			}else{
				logger.warn("Error en la creacion de la carpeta <"+pathFileUno+">");
				return false;
			}
			
		}catch(Exception ei){
			logger.error("Error en la creacion de la carpeta final"+ei);
			return false;
		}
		File fDestinoUno=null;
		File fDestino=null;
		String carpetaTemporal="";
		String temporal=getPropertyValue("carpeta.temporal");
		
		try{
			boolean crear=false;
			String nombreCarpetaTemporal=temporal+System.currentTimeMillis();
			String carpetaTemp=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_IMAGENES_NODOS_SQI)+nombreNodo+"/"+nombreCarpetaTemporal;
			if(logger.isDebugEnabled())logger.debug("carpetaTemp <"+carpetaTemp+">");
			fDestinoUno=new File(carpetaTemp);
			if(!fDestinoUno.exists()){
				
				crear=fDestinoUno.mkdir();
				if(!crear){
					logger.warn("Error en la creacion de la carpeta temporal <"+fDestinoUno+">");
					return false;
				}
//				crear=false;
			}else{
				logger.debug("La carpeta temporal <"+fDestinoUno+"> existia");
			}
			carpetaTemporal=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_IMAGENES_NODOS_SQI)+nombreNodo+"/"+nombreCarpetaTemporal+"/"+nombreDeImagen;
			fDestino = new File(carpetaTemporal);
		
			
			fDestino.createNewFile();
			if(!fDestino.exists()){
//				crear=fDestino.mkdir();
//				if(!crear){
					logger.error("Error creando la carpeta temporal <"+fDestino+">");
					return false;
//				}
			}
			if(logger.isDebugEnabled())logger.debug("La carpeta temporal <"+fDestino+ "> ya existia");

			if(logger.isDebugEnabled())logger.debug("Hemos creado el archivo temporal "+fDestino.getAbsolutePath());
		}catch(Exception ea){
			logger.error("Error en la creacion de la carpeta temporal",ea);
			return false;
		}
		try{
			//if (dataHandler!=null){
				FileOutputStream fos = new FileOutputStream(fDestino);
				//logger.debug("Copiamos lo que hay en "+fDestino.getAbsolutePath()+" al datahandler");
				dataHandler.writeTo(fos);
				//logger.debug("escribo el contenidos del datahandler");
				// liberamos recursos
				fos.close();
			//}
		}catch(Exception eoa){
			logger.error("Error al copiar el datahandler en el temporal - "+eoa);
			return false;
		}
		logger.debug("Hemos escrito el datahandler");
		String  comando = "convert -resize " + tamaÒo + " " + carpetaTemporal + " " + pathFileTarget;
		try
		{		
			if(logger.isDebugEnabled())logger.debug("GuardarImagenNodoSQI redimensionarImagen - Vamos a ejecutar el comando " +comando);
	
			Process process = Runtime.getRuntime().exec(comando);
			
			if(logger.isDebugEnabled())logger.debug("CambiarImagenControllerImpl redimensionarImagen - Hemos ejecutado el proceso ["+process.getClass()+"]");
       
            // check for ls failure
          if (process.waitFor() != 0) {
            	
            	 logger.warn("la salida tiene como valor <" + process.exitValue()+">"); 
            	 return false;
            }

		}
		catch (Exception ex)
		{
			logger.error("Se ha producido un error al ejecutar el comando [" + comando + "]", ex);
			return false;
		}	
		logger.debug("subirImagen - Si existe borramos el fichero temporal "+fDestinoUno);
		
		if(fDestinoUno!=null && fDestinoUno.exists()){
			try {

			    UtilesFicheros.eliminar(fDestinoUno);
//				logger.info("subirImagen - Fichero borrado");
			} catch (Exception e) {
				logger.error("subirImagen - El fichero no ha sido borrado");
			}
				
		}else{
			logger.info("subirImagen - Fichero no existe");
		}
		return resultado;
	}
	/**Este metodo recoge los properties
	 * 
	 * @param sKey
	 * 			La clave de la propiedad
	 * @return
	 * 		Devuelve la propiedad como un String
	 */
	public String getPropertyValue(String sKey) {
		String sReturn = "";
		try {
			if (props == null) {
				InputStream fIsSpringProperties = SrvGestionSqiServiceBase.class
						.getResourceAsStream(FILE_NAME_PROPERTIES);
				Properties properties = new java.util.Properties();
				properties.load(fIsSpringProperties);
				props=properties;
			}
			sReturn = props.getProperty(sKey);
			if (logger.isDebugEnabled())
				logger.debug("propiedad obtenida: " + sReturn.toString());
		} catch (IOException e) {
			logger.warn("Excepcion intentando obtener propiedad [" + sKey
					+ "] del fichero de propiedades del buscar[" + e.getCause() + "] - ",e);
		}
		// devolvemos la propiedad
		return sReturn;
	}
	
	private static String borrarAcentosYEspacios(String s){
		try {
			if (logger.isDebugEnabled())logger.debug("borrarAcentosYEspacios: Eliminar acentos para="+s);
			s = s.replaceAll( "[¬¿ƒ¡√]", "A" );
			s = s.replaceAll( "[ »À…]", "E" );
			s = s.replaceAll( "[ŒÃœÕ]", "I" );
			s = s.replaceAll( "[‘“÷”’]", "O" );
			s = s.replaceAll( "[€Ÿ‹⁄]", "U" );
			s = s.replaceAll( "«", "C" );
			s = s.replaceAll( "[‡‚‰·]", "a" );
			s = s.replaceAll( "[ÈËÍÎ]", "e" );
			s = s.replaceAll( "[ÔÓÏÌ]", "i" );
			s = s.replaceAll( "[ÙˆÚÛı]", "o" );
			s = s.replaceAll( "[˚¸˘˙]", "u" ); 
			s = s.replaceAll( "Á", "c" );
			s = s.replaceAll( "ë", "'");
			if (logger.isDebugEnabled())logger.debug("Eliminados las tildes: <"+s+"> Ahora se eliminaran los espacios.");
			s= s.replaceAll(" ", "_");
			if (logger.isDebugEnabled())logger.debug("Espacios eliminados: <"+s+">");
			return s;
		} catch ( Exception ex ) {
			logger.error("Error generando claves con Normalizer, para cadena <"+s+"> - ", ex);
			return "";
		}
	}
}