// license-header java merge-point
package es.pode.gestorFlujo.presentacion.objetosPublicados;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.gestorFlujo.presentacion.ObjetosPersonalesSession;
import es.pode.gestorFlujo.presentacion.utilidades.TransicionUrlVOComparator;
import es.pode.publicacion.negocio.servicios.SrvPublicacionService;
import es.pode.publicacion.negocio.servicios.TransicionVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.url.ImagenODE;
import es.pode.soporte.url.Proxy;

/**
 * @see es.pode.gestorFlujo.presentacion.objetosPublicados.ObjetosPublicadosController
 */
public class ObjetosPublicadosControllerImpl extends ObjetosPublicadosController {

	private Logger logger = Logger.getLogger(ObjetosPublicadosController.class);

	/**
	 * @see es.pode.gestorFlujo.presentacion.objetosPublicados.ObjetosPublicadosController#cargarODESPublicados(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.objetosPublicados.CargarODESPublicadosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public final void cargarODESPublicados(ActionMapping mapping,
			es.pode.gestorFlujo.presentacion.objetosPublicados.CargarODESPublicadosForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SrvPublicacionService publi = this.getSrvPublicacionService();

		logger.info("Cargando odes publicados para: [" + LdapUserDetailsUtils.getUsuario()+"]");

		try {
			TransicionVO[] odesPublicados = publi.obtenODEsPublicadosPorUsuario(LdapUserDetailsUtils.getUsuario());
			TransicionUrlVO[] transicionUrl = new TransicionUrlVO[odesPublicados.length];
			logger.info("Hay [" + odesPublicados.length + "] Odes publicados cargados correctamente para: [" + LdapUserDetailsUtils.getUsuario()+ "]");
			for (int i = 0; i < odesPublicados.length; i++) {
				transicionUrl[i] = new TransicionUrlVO();
				if(logger.isDebugEnabled())logger.debug("Para el elemento: ["+ i +"] el titulo del ode es ["+odesPublicados[i].getTitulo()+"]");
				transicionUrl[i].setTitulo(odesPublicados[i].getTitulo());
				transicionUrl[i].setIdODE(odesPublicados[i].getIdODE());
				transicionUrl[i].setFecha(odesPublicados[i].getFecha());
				String urlImagen = ImagenODE.urlImagenODE(odesPublicados[i].getIdODE());
				//Comprobamos si el ODE tiene imagen, y en caso de no ser asi se ajusta a la imagen por defecto
				urlImagen = comprobarImagen(urlImagen);
				transicionUrl[i].setUrlImagen(urlImagen);
			}
			Arrays.sort(transicionUrl, new TransicionUrlVOComparator());
//			UtilesODEs.quicksort(transicionUrl, 0, transicionUrl.length-1);
			form.setListaODESAsArray(transicionUrl);
			//Abro la sesion para recoger el espacio libre que tiene el usuario.
			ObjetosPersonalesSession sesion = this.getObjetosPersonalesSession(request);
			form.setEspacioLibre(sesion.getEspacioLibre());
			logger.info("Odes publicados cargados correctamente para: " + LdapUserDetailsUtils.getUsuario()+" que tiene un espacio libre de ["+ form.getEspacioLibre()+"]");
		
		} catch (Exception ex) {
			logger.error("Error inesperado cargando objetos publicados para: " + LdapUserDetailsUtils.getUsuario(), ex);
			throw new ValidatorException("{gestorFlujo.error.inesperado}");
		}
	}

	
	private String comprobarImagen(String urlImagen){
		try{
			new InputStreamReader(Proxy.getInputStream(new URL(urlImagen)));
			return urlImagen;
		}catch(Exception ex){
			if(logger.isDebugEnabled())logger.debug("comprobarImagen="+urlImagen);
			return "http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+"/"+AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.URL_IMAGEN_DEFECTO_100x100);
		}
	}
	

	@Override
	public void getTextoBusqueda(ActionMapping mapping,
			GetTextoBusquedaForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//Este metodo debe seri identico al ObjetosCompartidosControllerImpl.getTextoBusqueda
		String textBusqueda = form.getTextoBusqueda();
    	String textoBusqueda = textBusqueda.trim();
    	logger.info("La longitud es: " + textoBusqueda.length());
    	if (textoBusqueda.length()==0)
    	{
    		logger.error("Hay que introducir algún criterio de búsqueda");
    		throw new ValidatorException("{gestorFlujo.error.titulo.vacio}");
    	}
		form.setTextoBusqueda(textoBusqueda);
	}
	

}