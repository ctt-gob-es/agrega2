/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.herramientaOffline.presentacion.sincronizar;

import java.io.File;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.entregar.negocio.servicios.PaquetePifDriVO;
import es.pode.herramientaOffline.negocio.soporte.DescomprimeYvalida;
import es.pode.herramientaOffline.negocio.soporte.DescompriveYvalidaVO;
import es.pode.herramientaOffline.negocio.soporte.OdeVO;
import es.pode.herramientaOffline.negocio.soporte.ResultadoUploaderVO;
import es.pode.herramientaOffline.negocio.soporte.Utilidades;
import es.pode.herramientaOffline.negocio.soporte.ZipDao;
import es.pode.localizador.negocio.servicios.LocalizadorVO;
import es.pode.publicacion.negocio.servicios.ODESyncVO;
import es.pode.publicacion.negocio.servicios.ResultadoOperacionVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.utiles.ficheros.UtilesFicheros;



/**
 * @see es.pode.herramientaOffline.presentacion.sincronizar.SincronizarController
 */
public class SincronizarControllerImpl extends SincronizarController
{
	private static Logger logger = Logger.getLogger(SincronizarControllerImpl.class);

    /**
     * @see es.pode.herramientaOffline.presentacion.sincronizar.SincronizarController#crearSesion(org.apache.struts.action.ActionMapping, es.pode.herramientaOffline.presentacion.sincronizar.CrearSesionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void crearSesion(ActionMapping mapping, es.pode.herramientaOffline.presentacion.sincronizar.CrearSesionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	
    	SincronizarSession sesion=getSincronizarSession(request);
    	
    	//String puerto = props.getProperty("iniciar.puerto");
    	//String url = props.getProperty("iniciar.servidor");
    	//Almaceno los ODEs a subir en la sesion para recuperarlos despues de la confirmacion
//    	if (logger.isDebugEnabled()) logger.debug("Lista de identificadores a subir : " + form.getIdentificadores() + (form.getIdentificadores() == null ? "" : " con " + form.getIdentificadores().size() + " elementos"));
//    	CarpetaPersonalSession sesion=getCarpetaPersonalSession(request);
//    	if(form.getIdentificadores()!=null && form.getIdentificadores().size()>0){
//    		sesion.setIdentificadores(form.getIdentificadores());
    		form.setPuerto(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SERVIDOR_SINCRONIZACION_PORT));
    		form.setUrl(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SERVIDOR_SINCRONIZACION));
//    	}else{
//    		logger.error("La lista de identificadores no puede ser nula o vacía ["+form.getIdentificadores()+"]");
//			throw new ValidatorException("{subir.identificadores.error}");
//    	}
     }

    /**
     * @see es.pode.herramientaOffline.presentacion.sincronizar.SincronizarController#submitNodo(org.apache.struts.action.ActionMapping, es.pode.herramientaOffline.presentacion.sincronizar.SubmitNodoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String submitNodo(ActionMapping mapping, es.pode.herramientaOffline.presentacion.sincronizar.SubmitNodoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	//Obtener datos del formulario y Validar
    	java.util.Locale locale = (java.util.Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
        ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
        String result = "Cancelar";
        
        String action = form.getAction();
        boolean subida = form.isSubida(); 
        SincronizarSession sesion=getSincronizarSession(request);
      
        if(logger.isDebugEnabled()) logger.debug("Datos recogidos del formulario: action = " + action + " ; subida = "+subida+" ;");
        if((i18n.getString("sincronizarOdes.Aceptar").equals(action)) ) {
        	
        	    result = action;
        		String clave=form.getPasswd();
        		String url=form.getUrl();
        		String usuario=form.getUsuario();
        		
        		String puerto=form.getPuerto();
        		logger.debug("Los valores obtenidos del formulario son url ["+url+"],puerto ["+puerto+"] y usuario ["+usuario+" ]"); 
        		
        		sesion.setClave(clave);
        		sesion.setUrl(url);
        		sesion.setPuerto(puerto);
        		sesion.setUsuario(usuario);
        		logger.debug("Hemos seleccionado que subida es "+form.isSubida());
        		sesion.setSubida(subida);
        		logger.debug("La subida esta ahora a "+sesion.isSubida());
        		
        		if(usuario==null || usuario.equals("")){
        			logger.error("El nombre del usuario es obligatorio ["+usuario+"]");
        			throw new ValidatorException("{subir.usuario.error}");
        		}
        		if(clave==null || clave.equals("")){
        			logger.error("La clave del usuario es obligatorio ["+clave+"]");
        			throw new ValidatorException("{subir.clave.error}");
        		}
        		if(url==null || url.equals("")){
        			logger.error("La url del nodo es obligatorio ["+url+"]");
        			throw new ValidatorException("{subir.url.error}");
        		}
        		if(puerto==null || puerto.equals("")){
        			logger.error("El puerto del nodo es obligatorio ["+puerto+"]");
        			throw new ValidatorException("{subir.puerto.error}");
        		}
        		try{
        			Long puertoLong=Long.parseLong(puerto.trim());
        			if(puertoLong<=0){
        				logger.error("El puerto debe ser un número entero positivo");
            			throw new ValidatorException("{subir.puerto.entero.error}");
        			}
        		}catch(Exception e){
        			logger.error("El puerto debe ser un número entero positivo");
        			throw new ValidatorException("{subir.puerto.entero.error}");
        		}
        }
        
        if(logger.isDebugEnabled()) logger.debug("Retorno para punto de decision : " + result);
        return result;
    }

    /**
     * @see es.pode.herramientaOffline.presentacion.sincronizar.SincronizarController#obtenerOdes(org.apache.struts.action.ActionMapping, es.pode.herramientaOffline.presentacion.sincronizar.ObtenerOdesForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerOdes(ActionMapping mapping, es.pode.herramientaOffline.presentacion.sincronizar.ObtenerOdesForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	logger.debug("Estamos en obtenerOdes");
    	SincronizarSession sesion=getSincronizarSession(request);
    	String usuario = sesion.getUsuario();
    	String clave = sesion.getClave();
    	String url = sesion.getUrl();
    	String puerto = sesion.getPuerto();
    	boolean subida = sesion.isSubida();
    	
    	logger.debug("Los valores obtenidos del objeto sesión son clave ["+clave+"], usuario ["+usuario+"] y puerto ["+puerto+" ]");
    	
    	if (!subida){
    		
    		//hemos elegido bajar
    		ODESyncVO[] odes;
    		try {
    			odes = Utilidades.obtenerConexionSrvSync(url, puerto).obtenerODEs(usuario, clave);
        	
	        	logger.debug("El número de odes obtenidos es: "+ odes.length);
	
	        	List<ODESyncVO> odesList = Arrays.asList(odes);
	       	
	        	logger.debug("El numero de odes pasado a la lista es de "+ odesList.size());
	        	sesion.setOdes(odesList);
	        	form.setOdes(odes);
    		} catch (Exception e) {
    			logger.error("Se ha producido un error: ",e);
    		}
    		
    	}else{
    		
    		//hemos elegido subir
    		OdeVO[] odes = null;
    		
        	try {
	        	odes = getDescomprimeYvalida().listarOdes();
	        	
	        	logger.debug("El número de odes obtenidos es: "+ odes.length);
	        	
	        	List<ODESyncVO> odesList = new ArrayList<ODESyncVO>();
	        	
	        	for (int i=0;i<odes.length;i++){
	        		ODESyncVO odesSy = new ODESyncVO();
	        		
	        		odesSy.setIdODE(odes[i].getIdODE());
	        		odesSy.setTitulo(odes[i].getTitulo());
	        		
	        		odesList.add(odesSy);
	        		
	        	}
	       	
	        	logger.debug("El numero de odes pasado a la lista es de "+ odesList.size());
	        	sesion.setOdes(odesList);
	        	ODESyncVO[] odesS = odesList.toArray(new ODESyncVO[0]);
	        	logger.debug("El tamaño del array de odes pasado al formulario es "+odes.length);
	        	form.setOdes(odesS);
    		} catch (Exception e) {
    			logger.error("Se ha producido un error: ",e);
    		}
    	}
    	
     }

    /**
     * @see es.pode.herramientaOffline.presentacion.sincronizar.SincronizarController#sincronizar(org.apache.struts.action.ActionMapping, es.pode.herramientaOffline.presentacion.sincronizar.SincronizarForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void sincronizar(ActionMapping mapping, es.pode.herramientaOffline.presentacion.sincronizar.SincronizarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	logger.debug("Estamos en sincronizar");
    	
    	java.util.Locale locale = (java.util.Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
        ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
        
        //para obtener los datos de sesión
		SincronizarSession sesion=getSincronizarSession(request);
        String url = sesion.getUrl();
		String puerto = sesion.getPuerto();
		String usuario = sesion.getUsuario();
		String clave = sesion.getClave();
		String accion = form.getAction();
		Boolean subida = sesion.isSubida();
		
		logger.debug("Ahora en sincronizar la accion es "+accion+" y subida es "+subida);
		
    	//Primero comprobamos el action, si es Cancelar, salimos
    	if(!accion.equals(i18n.getString("comun.Cancelar")) && (form.getOdes().length!=0)) {
			
		//para comprobar que se ha seleccionado algún ode 
		logger.debug("Hemos seleccionado "+form.getOdes().length+" odes");
		
			
			//En odes tenemos la lista de ODEs seleccionados
			ODESyncVO[] odes = form.getOdes();
			logger.debug("Hemos obtenido los "+odes.length+" odes seleccionados");
			OdeVO[] odes2 = getDescomprimeYvalida().listarOdes();
			logger.debug("Hemos obtenido los "+odes2.length+" odes del servidor");
			//Declaramos el array resultado
			ResultadoUploaderVO[] resultados = new ResultadoUploaderVO[odes.length];
			
			logger.debug("El array de resultados tiene el tamaño: "+resultados.length);
			
			//Ahora separamos entre Subir o Bajar, que lo tenemos en la sesión
			if (subida){
				
				logger.debug("Se subiran los odes");
				
				boolean resultado[] =subir(odes, usuario, clave, url, puerto);
				
				for (int i=0; i<odes.length; i++){
					
					//tratamos los resultados de la subida
					logger.debug("Resultado es "+resultado[i]);
					resultados[i]= new ResultadoUploaderVO();
					resultados[i].setSubido(resultado[i]);
					logger.debug("Titulo es "+odes[i].getTitulo());
					resultados[i].setTitulo(odes[i].getTitulo());
					logger.debug("La i vale: "+i+" y el número de odes es de "+resultado.length+" y resultado[i] es: "+resultado[i]+" y devolvemos "+resultados.length);
					if (resultado[i]){
						
						logger.debug("Se ha subido correctamente");
						String[] mensajes = new String[1];
						mensajes[0] = i18n.getString("sincronizarOdes.correcta");						
						logger.debug("El mensaje a mostrar es: "+mensajes[0]);
						resultados[i].setMensajesError(mensajes);
						
					}else{
						
						logger.debug("se ha subido incorrectamente");
						String[] mensajes = new String[1];
						mensajes[0] = i18n.getString("sincronizarOdes.error"); 
						resultados[i].setMensajesError(mensajes);
						logger.debug("El mensaje a mostrar es: "+mensajes[0]);
					}
				}
				
		    	
			}else /*if (!subida)*/{
				
				//Y si es bajar, si es nuevo o no
				boolean esNuevo = false;
				boolean encontrado = false;
				
				//ordenamos los odes para ahorrar búsquedas
				Arrays.sort(odes, new OdesComparador());
				Arrays.sort(odes2, new OdesVOComparador());
				
				//Sacamos las urls de descarga
				String[] urls = Utilidades.obtenerConexionSrvSync(url, puerto).descargarODEs(odes, clave, usuario);
				
				//comprobamos que el ode no existe entre los nuestros para ver si es nuevo
				int indice = 0;
				
				//para tratar la url de los ODEs
				String urlCompleta = url;
				if (!urlCompleta.startsWith("http://")){
					
					urlCompleta = "http://"+url;
				}
				
				for (int i=0; i<odes.length; i++){
					
					while ((indice < odes2.length) && !encontrado){
						
						if (odes[i].getIdODE().equals(odes2[indice].getIdODE())){
							
							encontrado = true;
						}
						
						indice ++;
					}
					

					if (!encontrado){
						
						logger.debug("Es nuevo y pasamos los parámetros con la url: "+urls[i]);
						//completamos la url
						url = urlCompleta+urls[i];
						
						boolean resultado =bajarNuevo(odes[i], url);
						logger.debug("Resultado es "+resultado);
						resultados[i]= new ResultadoUploaderVO();
						resultados[i].setSubido(resultado);
						logger.debug("Titulo es "+odes[i].getTitulo());
						resultados[i].setTitulo(odes[i].getTitulo());
						
						if (!resultado){
							
							logger.debug("No se ha bajado correctamente");
							String[] mensajes = new String[1];
							mensajes[0]= i18n.getString("sincronizarOdes.error"); 
							logger.debug("El mensaje a mostrar es: "+mensajes[0]);
							resultados[i].setMensajesError(mensajes);
							
						}else{
							
							logger.debug("Se ha bajado correctamente");
							String[] mensajes = new String[1];
							mensajes[0] = i18n.getString("sincronizarOdes.correcta"); 
							logger.debug("El mensaje a mostrar es: "+mensajes[0]);
							resultados[i].setMensajesError(mensajes);
							
						}
						
						
						
					}else{
						
						logger.debug("No es nuevo y pasamos los parámetros");
						//completamos la url
						url = urlCompleta+urls[i];
						
						boolean resultado = bajarNoNuevo(odes[i], url);
						resultados[i]= new ResultadoUploaderVO();
						resultados[i].setSubido(resultado);
						resultados[i].setTitulo(odes[i].getTitulo());
						
						if (!resultado){
							
							//no se ha bajado correctamente
							String[] mensajes = new String[1];
							mensajes[0]= i18n.getString("sincronizarOdes.error"); 
							logger.debug("El mensaje a mostrar es: "+mensajes[0]);
							resultados[i].setMensajesError(mensajes);
							
						}else{
							
							//se ha bajado correctamente
							String[] mensajes = new String[1];
							mensajes[0] = i18n.getString("sincronizarOdes.correcta"); 
							logger.debug("El mensaje a mostrar es: "+mensajes[0]);
							resultados[i].setMensajesError(mensajes);
							
						}
					}
						
				
					
				}
	    	
			}
			
			//obtenemos los odes para pasarlos como resultado al form
	    	
	    	form.setResultados(resultados);
    	}
    	
     }

	@Override
	public void submitODEs(ActionMapping mapping, SubmitODEsForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
	   	java.util.Locale locale = (java.util.Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
        ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
		
		//para obtener los datos de sesión
		SincronizarSession sesion=getSincronizarSession(request);
		logger.debug("La accion seleccionada es " +form.getAction());
		//obtenemos la lista de odes de la sesion
		List<ODESyncVO> odesList = sesion.getOdes();
		logger.debug("Hemos obtenido "+odesList.size()+" odes de sesión");
				
		//comprobamos que la acción seleccionada no es Cancelar
		if (!form.getAction().equals(i18n.getString("comun.Cancelar"))){
			
			//la pasamos a un array
			ODESyncVO[] odesArray = odesList.toArray(new ODESyncVO[0]);
			
			//creamos una lista para añadir los resultados
			List<ODESyncVO> odesResult = new ArrayList<ODESyncVO>();
			
			logger.debug("Y hemos seleccionado "+form.getIdentificadorRowSelectionAsArray()+" odes");
					
			if (form.getIdentificadorRowSelectionAsArray()!=null) {
				
				//obtenemos el array de identificadores
				String[] ids = form.getIdentificadorRowSelectionAsArray();
				logger.debug("IDs: "+Arrays.toString(ids));
				logger.debug("ODEsArray: "+Arrays.toString(odesArray));
				
				//ordenamos el array
				Arrays.sort(ids);
				Arrays.sort(odesArray, new OdesComparador());
				
				//buscamos las id
				int indice=0;
				boolean encontrado = false;
				//recorremos el array de odes seleccionados
				for (int i=0; i < ids.length; i++) {
					
					encontrado = false;
					//los buscamos en el array de odes disponibles
					while ((indice < odesArray.length)&& !encontrado){
						
						//comparamos los índices
						if (ids[i].equals(odesArray[indice].getIdODE())){
							
							logger.debug("Añadimos el ode "+ids[i]+" a la lista de resultados");
							odesResult.add(odesArray[indice]);
							encontrado = true;
							
						}
						//incrementamos el índice para que la búsqueda parta desde el último ode encontrado
						indice ++;
					}
					
				}
				
				logger.debug("En la lista tenemos los "+odesResult.size()+" odes seleccionados");
				//ODESyncVO[] odesResultA = (ODESyncVO[]) odesResult.toArray();
				ODESyncVO[] odesResultA = odesResult.toArray(new ODESyncVO[0]);
				logger.debug("En el array tenemos los "+odesResultA.length+" odes seleccionados");
				form.setOdes(odesResultA);
				logger.debug("Añadimos el array de odes obtenido al form con "+ odesResult.size()+" odes");
				
			}else{
				//pasamos el array vacío
				ODESyncVO[] odesResultA = odesResult.toArray(new ODESyncVO[0]);
				logger.debug("En el array tenemos los "+odesResultA.length+" odes seleccionados");
				form.setOdes(odesResultA);
			}
		}
	}
	
	private boolean bajarNuevo(ODESyncVO ode, String urlRemota) {
		String titulo = urlRemota.substring(urlRemota.lastIndexOf("/")+1);
		try {
			DescomprimeYvalida servicio = getDescomprimeYvalida();
			logger.debug("Vamos a bajarnos "+urlRemota);
//			String titulo = urlRemota.substring(urlRemota.lastIndexOf("/"));
			DescompriveYvalidaVO resultado = servicio.importarOdeConId(
					new DataHandler(new URL(urlRemota)), titulo, ode.getIdODE());
			if(resultado==null) {
				logger.error("Error al bajar fichero " + titulo);
				return false;
			}
			return resultado.getValido();
		} catch (Exception e) {
			// Algo salió mal
			logger.error("Error al bajar fichero " + titulo + ": "+ e);
			return false;
		}
	}
	
	private boolean bajarNoNuevo(ODESyncVO ode,String urlRemota) {
		try {
			//Bajamos remoto a ruta temporal
			String carpetaTemporal = Utilidades.getPropertyValue("carpeta.temporal");
			String ficheroLocal = carpetaTemporal+Utilidades.BARRA+ode.getIdODE()+".zip";
			
			File carpetaTemporalFile= new File(carpetaTemporal);
			if(!carpetaTemporalFile.exists()) {
				carpetaTemporalFile.mkdirs();
				logger.debug("Creo ruta "+carpetaTemporal);
			}
			logger.debug("Intento copiar zip remoto "+urlRemota+" a "+ficheroLocal);
			
			UtilesFicheros.copiarFileFormURI(urlRemota, ficheroLocal);
			logger.debug("Zip remoto "+urlRemota+" copiado a "+ficheroLocal);
			//Descomprimimos
			String rutaODETemporal = carpetaTemporal+Utilidades.BARRA+ode.getIdODE()+Utilidades.BARRA;
			File rutaODETemporalFile = new File(rutaODETemporal);
			if(!rutaODETemporalFile.exists()) {
				rutaODETemporalFile.mkdirs();
			}
			new ZipDao().descomprimir(ficheroLocal, rutaODETemporal);
			logger.debug("Fichero descomprimido en ruta temporal "+rutaODETemporal);
			//Averiguamos ruta local
			LocalizadorVO localizador = this.getSrvLocalizadorService()
					.consultaLocalizador(ode.getIdODE());
			String carpetaLocal = localizador.getPath();
			//Copiamos remoto en ruta local
			File rutaTemporal = new File(rutaODETemporal);
			if(!rutaTemporal.exists()) {
				rutaTemporal.mkdirs();
			}
			UtilesFicheros.copiar(rutaTemporal, new File(carpetaLocal));
			//Borramos temporal
			UtilesFicheros.eliminar(rutaTemporal);
		} catch (Exception e) {
			//Algo salió mal
			logger.error("Error al bajar fichero "+ode.getTitulo()+": "+e);
			return false;
		}
		return true;
	}
	
	private boolean[] subir(ODESyncVO[] odes, String usuario, String clave, String urlRemota, String puertoRemoto) {
		boolean[] resultados= new boolean[odes.length];
		String[] identificadores = new String[odes.length];		
		
		logger.debug("Desde subir vamos a tratar los "+odes.length+" seleccionados");
		try {
			
			for (int i = 0; i < odes.length; i++) {
				identificadores[i]=odes[i].getIdODE();
				logger.debug("El identificador del ode a subir es: "+identificadores[i]);
				PaquetePifDriVO paquete = this.getSrvEntregarService().generarPaquetePIF(identificadores[i]);
				ResultadoOperacionVO resultado=Utilidades.obtenerConexionSrvSync(urlRemota, puertoRemoto).subirODE(usuario, clave, paquete.getPaquetePIF(),odes[i].getTitulo(),LdapUserDetailsUtils.getIdioma(),identificadores[i]);
				resultados[i]=resultado.getIdResultado().equals("0.0");
			}
			
		} catch (Exception e) {
			logger.error("Se ha encontrado un error: ",e);
		}
		return resultados;
	}

	@Override
	public String submitAction(ActionMapping mapping, SubmitActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	   	java.util.Locale locale = (java.util.Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
        ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
        
        logger.debug("Estamos en submitAction y la accion es "+form.getAction());
		if (form.getAction().equals(i18n.getString("comun.Aceptar"))){
			return "Aceptar";
		} else {
			return "Cancelar";
		}
		
	}	
}

class OdesComparador implements Comparator, Serializable {

	public int compare(Object arg0, Object arg1) {

		//obtenemos los odes de los argumentos
		ODESyncVO ob1 = (ODESyncVO)arg0;
		ODESyncVO ob2 = (ODESyncVO)arg1;
		
		//sacamos los identificadores
		String id1 = ob1.getIdODE();
		String id2 = ob2.getIdODE();
		
		//se devuelve la comparación
		return id1.compareTo(id2);
		
	}
}

class OdesVOComparador implements Comparator, Serializable{

	public int compare(Object arg0, Object arg1) {

		//obtenemos los odes de los argumentos
		OdeVO ob1 = (OdeVO)arg0;
		OdeVO ob2 = (OdeVO)arg1;
		
		//sacamos los identificadores
		String id1 = ob1.getIdODE();
		String id2 = ob2.getIdODE();
		
		//se devuelve la comparación
		return id1.compareTo(id2);
		
	}
}