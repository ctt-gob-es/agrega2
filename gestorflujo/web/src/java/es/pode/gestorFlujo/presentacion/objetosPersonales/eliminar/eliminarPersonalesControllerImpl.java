/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.gestorFlujo.presentacion.objetosPersonales.eliminar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.gestorFlujo.presentacion.ObjetosPersonalesSession;
import es.pode.gestorFlujo.presentacion.albumes.listarAlbum.ListarAlbumControllerImpl;
import es.pode.gestorFlujo.presentacion.objetosPersonales.ObjetosPersonalesControllerImpl;
import es.pode.publicacion.negocio.servicios.ResultadoOperacionVO;
import es.pode.publicacion.negocio.servicios.SrvPublicacionService;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.gestorFlujo.presentacion.objetosPersonales.eliminar.eliminarController
 */
public class eliminarPersonalesControllerImpl extends eliminarPersonalesController
{

	private final  String ORIGEN_DE_LA_LLAMADA = "origen_de_la_llamada_eliminar_gestorFlujo"; 
	private final  String IDS_ELIMINAR_GF = "ids_eliminar_gestorFlujo";
	private final  String TITULOS_ELIMINAR_GF = "titulos_eliminar_gestorFlujo";
	private Logger logger = Logger.getLogger(eliminarPersonalesController.class);



		public final ResultadoOperacionVO eliminarODEs(ActionMapping mapping, es.pode.gestorFlujo.presentacion.objetosPersonales.eliminar.EliminarODEsForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
			SrvPublicacionService publi = this.getSrvPublicacionService();
	    	if(logger.isDebugEnabled())	logger.debug("Eliminando el objeto personal["+ form.getTitulo()+"]");
			 	ResultadoOperacionVO resultado = new ResultadoOperacionVO();
				try {
					resultado = publi.eliminar(form.getIdentificador(), LdapUserDetailsUtils.getUsuario());
					//Abro la session, para recoger el espacio libre 
					ObjetosPersonalesSession sesion = this.getObjetosPersonalesSession(request);
					Long espacioLibre = sesion.getEspacioLibre();
					//Consulto el espacio que ocupaba el ODE que acabo de eliminar. 
					Long espacioODE = this.getSrvLocalizadorService().consultaEspacioLocalizador(form.getIdentificador());
					//Se lo añado al espacio libre y se lo vuelvo a pasar a la sesion.
					espacioLibre = espacioLibre+espacioODE;
					sesion.setEspacioLibre(espacioLibre);
				} catch (Exception ex) {
					logger.error("Excepcion eliminando el ODE con IdODE[" + form.getIdentificador() + "] usuario["
							+ LdapUserDetailsUtils.getUsuario() + "]");
					resultado.setDescripcion("gestorFlujo.error.inesperado");
					resultado.setIdResultado("-1");
				}
				return resultado;
		}

    /**
     * @see es.pode.gestorFlujo.presentacion.objetosPersonales.eliminar.eliminarController#cargarDatos(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.objetosPersonales.eliminar.CargarDatosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
//    public final void cargarDatos(ActionMapping mapping, es.pode.gestorFlujo.presentacion.objetosPersonales.eliminar.CargarDatosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
//    {
//    	request.getSession().setAttribute(ORIGEN_DE_LA_LLAMADA, ((EliminarPersonalesCUFormImpl) form).getOrigen());
//		try {
//			String[] idODEs = (String[])((EliminarPersonalesCUFormImpl) form).getListODEs();
//			if (idODEs != null && idODEs.length > 0) {
//				// obtenemos los ids y los titulos de los odes que vamos a
//				// eliminar,
//				// que vienen concatenados en el mismo string
//
//				String[] titulos = new String[idODEs.length];
//				String[] ids = new String[idODEs.length];
//				desconcatena(idODEs, ids, titulos);
//
//				request.getSession().setAttribute(IDS_ELIMINAR_GF, ids);
//				request.getSession().setAttribute(TITULOS_ELIMINAR_GF, titulos);
//				form.setTitulos(titulos);
//				form.setSeleccion("ALGUN_ODE_SELEC");
//				logger.info("carga de datos eliminar correcta");
//
//			} else {
//				logger.warn("No se han seleccionado elementos a eliminar");
//				form.setSeleccion("NINGUN_ODE_SELEC");
//				saveErrorMessage(request, "gestorFlujo.error.eliminar.seleccione");
//			}
//		} catch (Exception ex) {
//			logger.error("Excepcion cargando datos eliminar", ex);
//			throw new ValidatorException("{gestorFlujo.error.inesperado}");
//		}
//     }


		public final void cargarDatos(ActionMapping mapping, es.pode.gestorFlujo.presentacion.objetosPersonales.eliminar.CargarDatosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception   
		{
			if(logger.isDebugEnabled())
				logger.debug("Cargando los datos de eliminación para ode: " + form.getIdentificador() + "de titulo: " + form.getTitulo());	
		}







    // Esta funcion nos ayuda a separar la cadena que le enviamos en titulo e
	// id, de esta
    // menera mejoramos el rendimiento de la aplicación, no hace falta llamadas
	// a servicios
    private void desconcatena(String[] sInicial, String[] sIds, String[] sTitulos)
    {
    	for (int i=0;i<sInicial.length;i++)
    	{
    		sIds [i] = sInicial[i].substring(0,sInicial[i].indexOf(','));
    		sTitulos[i]  = sInicial[i].substring(sInicial[i].indexOf(',')+1);
    	}
    }
    
    
	public String comprobarOrigen(ActionMapping mapping, ComprobarOrigenForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ObjetosPersonalesSession sesion = this.getObjetosPersonalesSession(request);
		Long idAlbum = sesion.getIdAlbum();
		String retorno = sesion.getRetorno();
		
		
		logger.debug("El origen es ["+ retorno +"], y el idAlbum ["+ idAlbum +"]");
		if(retorno != null && retorno.equals(ObjetosPersonalesControllerImpl.OBJETOS_PERSONALES)){
			retorno = ObjetosPersonalesControllerImpl.OBJETOS_PERSONALES;
		}
		if(retorno!= null && retorno.equals(ListarAlbumControllerImpl.ODES_POR_ALBUM)){
			retorno = ListarAlbumControllerImpl.ODES_POR_ALBUM;
		}
		logger.debug("Creamos el album, y volvemos a: ["+ retorno + "]");
		return retorno;
	}


}