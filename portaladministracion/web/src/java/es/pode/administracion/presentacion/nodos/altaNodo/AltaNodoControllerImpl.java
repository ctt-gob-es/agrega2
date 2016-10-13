// license-header java merge-point
package es.pode.administracion.presentacion.nodos.altaNodo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;

import es.pode.buscar.negocio.administrar.servicio.CcaaVO;
import es.pode.buscar.negocio.administrar.servicio.NodoVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;

/**
 * @see es.pode.administracion.presentacion.nodos.altaNodo.AltaNodoController
 */
public class AltaNodoControllerImpl extends AltaNodoController {

	private static Logger log = Logger.getLogger(AltaNodoControllerImpl.class);
	ResourceBundle ficheroRecursos = null;

	/**
	 * @see es.pode.administracion.presentacion.nodos.altaNodo.AltaNodoController#altaNodo(org.apache.struts.action.ActionMapping,
	 *      es.pode.administracion.presentacion.nodos.altaNodo.AltaNodoForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public final void altaNodo(ActionMapping mapping, es.pode.administracion.presentacion.nodos.altaNodo.AltaNodoForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
		
		try
		{
			NodoVO nodoVO = new NodoVO();
			String nodo = form.getNodo().trim();
			String url = form.getUrl().trim();
			String puerto = form.getPuerto();
			Long ccaaId = form.getComunidad();
			String urlWS = form.getUrlWS().trim();
			
		    // Validaciones de los campos que recogemos del formulario
		    Pattern mask = Pattern.compile("[^\\¿\\?\\¡\\!\\>\\#\\&\\<\\@\\$\\\'\\\"]+");
		    Matcher matcher = null;

		    
			//Validacion de nombre de nodo
		    
			if (nodo == null || nodo.equals("")) 
			{
				log.error("el nombre del nodo no puede estar vacio");
				throw new ValidatorException("{errors.altanodo.nodo}");
			}
			
		    matcher = mask.matcher(nodo);
		    if (!matcher.matches()) {
		    	if(log.isDebugEnabled())log.debug("nodo caracter ilegal");
		    	throw new ValidatorException("{errors.altanodo.nodo.caracterIlegal}");
		    }
		    

		    
		    //Validacion de la url del nodo
		    
			if (url == null || url.equals("")) 
			{
				log.error("la url del nodo no puede estar vacio");
				throw new ValidatorException("{errors.altanodo.url}");
			}

		    matcher = mask.matcher(url);
		    if (!matcher.matches()) {
		    	if(log.isDebugEnabled())log.debug("nodo caracter ilegal");
		    	throw new ValidatorException(
							"{errors.altanodo.url.caracterIlegal}");
		    }

		    
		    //Validacion de la url de Webservice
		    
			if (urlWS == null || urlWS.equals("")) 
			{
				log.error("la url del WebService no puede estar vacio");
				throw new ValidatorException("{errors.altanodo.urlWS}");
			}

		    matcher = mask.matcher(urlWS);
		    if (!matcher.matches()) {
		    	if(log.isDebugEnabled())log.debug("nodo caracter ilegal");
		    	throw new ValidatorException(
							"{errors.altanodo.urlWS.caracterIlegal}");
		    }
		    
		    
		    //Comprobamos que no existe ningun nodo con el nombre introducido para dar de alta
		    
		    if(this.getSrvNodoService().existeNombreNodo(nodo).booleanValue())
		    	throw new ValidatorException("{errors.altanodo.nombreNodoYaExiste}");
		    
		    
		    //Comprobamos la url del nodo para evitar que un nodo se federe consigo mismo
		    
		    String urlHost=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.HOST);
		    if(log.isDebugEnabled())log.debug("El valor de urlHost es "+urlHost);
		    if(urlHost.trim().equalsIgnoreCase(url.trim()))
		    {
		    	if(log.isDebugEnabled())log.debug("Se esta intentando federar un nodo consigo mismo");
		    	throw new ValidatorException(
				"{errors.altanodo.url.federadoConsigoMismo}");
		    }
		    
		    
		    //Compruebo si ya existen otro nodo en la BD con los mismos valores de url y urlWS
		    
		    Boolean estaDadoAlta = this.getSrvNodoService().estaDadoAlta(url, urlWS);
		    if(log.isDebugEnabled())log.debug("estaDadoAlta "+estaDadoAlta);
		    if(estaDadoAlta.booleanValue())
		    {
		    	if(log.isDebugEnabled())log.debug("esta dado de alta");
		    	throw new ValidatorException("{errors.altanodo.url.yaEstaDadoAlta}");
		    }
		    
		    
		    //Comprobamos si se introduce puerto que este sea numerico
		    if(puerto != null && !puerto.matches("[0-9]*"))
		    	throw new ValidatorException("{errors.altanodo.puerto.NoNumerico}");
		    
		    
			nodoVO.setNodo(nodo);
			nodoVO.setUrl(url);
			nodoVO.setPuerto(puerto);
			nodoVO.setUrlWS(urlWS);
			if (ccaaId != null)
			{
				CcaaVO ccaa = new CcaaVO();
				ccaa.setId(ccaaId);
				nodoVO.setCcaa(ccaa);
			}
			
			//Comprobamos si al crear el nodo todo ha ido bien o se ha producido algun error//No, recogemos el identificador del nodo creado
			Long codigo_devuelto = this.getSrvNodoService().crearNodo(nodoVO);
			log.debug("El codigo recibido es"+codigo_devuelto);
			if(codigo_devuelto.longValue() == (-1)){
				log.debug("El codigo de error"+codigo_devuelto);
				throw new ValidatorException("{errors.altanodo.urlWS.timeout}");
			}
			form.setIdModificado(codigo_devuelto);
			if(log.isDebugEnabled())log.debug("El identificador del nodo dado de alta es "+codigo_devuelto);
			
		} 
		catch (ValidatorException e)
		{
			throw e;
		} 
		catch (Exception e)
		{
			log.error("Error!! " + e);
			throw new ValidatorException("{errors.altanodo}");
		}
	}


	public void obtenerCCAA(ActionMapping mapping, ObtenerCCAAForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try
		{	
			//recogemos las ccaa de la bbdd y las internacionalizamos desde el properties
			Locale locale = new Locale(LdapUserDetailsUtils.getIdioma());
			ficheroRecursos = ResourceBundle.getBundle("application-resources", request.getLocale());
			
			List ccaa = Arrays.asList(this.getSrvNodoService().obtenerCCAAs());
			CcaaVO[] ccaaVOArray = (CcaaVO[])ccaa.toArray();
			List ccaaList = new ArrayList();
			for(int i=0; i<ccaaVOArray.length; i++)
			{
				if(ccaaVOArray[i].getCodigo() != null)
				{
					String descripcion = null;
					try {
						descripcion = ficheroRecursos.getString(ccaaVOArray[i].getCodigo());
					}
					catch (Exception ex)
					{
						log.warn("El codigo de la comunidad " + ccaaVOArray[i].getCodigo() + " no esta en el fichero de internacionalizacion");
						descripcion = ccaaVOArray[i].getDescripcion(); 
					}
					ccaaList.add(descripcion.toLowerCase());
					ccaaVOArray[i].setDescripcion(descripcion);
				}
				else
					ccaaVOArray[i].setCodigo(""); 
				
			}
			Collections.sort(ccaaList);
			ccaa = ordenarLista(ccaaList, ccaaVOArray);
			
			// Rellena el combo de ccaa
			((AltaNodoFormImpl)form).setCcaaBackingList(ccaa, "id", "descripcion");
			
		}
		catch(Exception e)
		{
			log.error("Error: " + e);
			throw new ValidatorException("{errors.altanodo}");
		}
	}
	
	private List ordenarLista (List listaOrdenada, CcaaVO[] array){
		CcaaVO[] ccaaOrdenado = new CcaaVO[array.length];
		int posicion=0;
		for (int i=0; i<array.length;i++){
			posicion = listaOrdenada.indexOf(array[i].getDescripcion().toLowerCase());
			ccaaOrdenado[posicion]= array[i];
		}
		return Arrays.asList(ccaaOrdenado);

	}



}