/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.gestorFlujo.presentacion.objetosCompartidos;

import java.rmi.RemoteException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.adminusuarios.negocio.servicios.SrvAdminUsuariosService;
import es.pode.gestorFlujo.presentacion.ObjetosPersonalesSession;
import es.pode.gestorFlujo.presentacion.objetosPersonales.TransicionConTamainoVO;
import es.pode.gestorFlujo.presentacion.utilidades.TransicionConTamainoVOComparator;
import es.pode.localizador.negocio.servicios.SrvLocalizadorService;
import es.pode.publicacion.negocio.servicios.SrvPublicacionService;
import es.pode.publicacion.negocio.servicios.TransicionAutorVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;

/**
 * @see es.pode.gestorFlujo.presentacion.objetosCompartidos.ObjetosCompartidosController
 */
public class ObjetosCompartidosControllerImpl extends ObjetosCompartidosController
{
	private static final long serialVersionUID = -7983418627691727258L;
	
	protected final static String URL_SEPARATOR = "/";
	protected final static String URL_IMAGEN ="static/img/azul/";
	private Logger logger = Logger.getLogger(ObjetosCompartidosController.class);

    /**
     * @see es.pode.gestorFlujo.presentacion.objetosCompartidos.ObjetosCompartidosController#cargarODESCompartidos(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.objetosCompartidos.CargarODESCompartidosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void cargarODESCompartidos(ActionMapping mapping, es.pode.gestorFlujo.presentacion.objetosCompartidos.CargarODESCompartidosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
			if(logger.isDebugEnabled())
				logger.debug("Cargando objetos compartidos");
			SrvPublicacionService publi = this.getSrvPublicacionService();
			SrvLocalizadorService localizador=this.getSrvLocalizadorService();
			SrvAdminUsuariosService admin=this.getSrvAdminUsuariosService();
			TransicionAutorVO[] odes =null;
			try {
				
				String[] todosUsuariosGrupos=admin.obtenerListaUsuariosGrupoTrabajo(LdapUserDetailsUtils.getUsuario());
//				
//				String[] todosUsuariosGrupos=new String[] {LdapUserDetailsUtils.getUsuario()};
				if(todosUsuariosGrupos!=null && todosUsuariosGrupos.length>0){
					logger.info("Obtenidos lista de usuarios de los grupos pertenecientes de usuario:["+LdapUserDetailsUtils.getUsuario()+"Numero de usuarios:["+todosUsuariosGrupos.length);
					 odes = publi.obtenODEsCompartidosPorUsuarios(todosUsuariosGrupos);
					logger.info("Obtenidos odes de esos usuarios, numero de odes pendientes de publicacion ["+odes.length);
				}else{
					logger.info("Obtenidos lista de todos los ODES, pues el usuario:["+LdapUserDetailsUtils.getUsuario()+" es parte de todos los grupos");
					odes=publi.obtenODESCompartidos();
					logger.info("Obtenidos odes de todos los usuarios, numero de odes pendientes de publicacion["+odes.length);
				}
				
			} catch (Exception ex) {
				logger.error("Imposible obtener los odes pendientes de publicacion", ex);
				throw new ValidatorException("{gestorFlujo.error.inesperado}");
			}
			 
			if(odes !=null && odes.length>0){
				TransicionConTamainoVO[] odesTamaino=new TransicionConTamainoVO[odes.length];

				String[] identificadoresOdesCompartidos=new String[odes.length];
				if(logger.isDebugEnabled()){
					logger.debug("Entramos en el mapeo de "+ odes.length+ " elementos");
				}
				for(int i=0;i<odes.length;i++){
					
					TransicionConTamainoVO conTamaino=new TransicionConTamainoVO();
					if(logger.isDebugEnabled())logger.debug("Estamos en la posicion: "+i);
					conTamaino.setComentarios(odes[i].getComentarios());
					conTamaino.setFecha(odes[i].getFecha());
					conTamaino.setIdODE(odes[i].getIdODE());
					identificadoresOdesCompartidos[i]=odes[i].getIdODE();//Los guardamos para hacer la consulta al localizador
					conTamaino.setIdUsuario(odes[i].getIdUsuarioCreacion());//Nombre del creador!!!!!!!!!!!!
					conTamaino.setTitulo(odes[i].getTitulo());
					odesTamaino[i]=conTamaino;
				}
				Long[] tamainoOdes={0l,};
				if(identificadoresOdesCompartidos!=null && identificadoresOdesCompartidos.length>0){
					tamainoOdes=localizador.consultaEspacioLocalizadores(identificadoresOdesCompartidos);
				}
				for(int i=0;i<odes.length;i++){
//					Vamos a insertar le tamaino en MB,no en bytes; 
					double mb = (double)tamainoOdes[i]/(1024*1024);
					String pattern="###.##";
					java.text.DecimalFormat myFormatter = new java.text.DecimalFormat(pattern);
					String output = myFormatter.format(mb);
					odesTamaino[i].setTamaino(output);
					if(logger.isDebugEnabled())
						logger.debug("Tamaino del ODE compartido con id:"+odesTamaino[i].getIdODE()+ ", su div["+mb+"] y su tamaino es:"+output);
				}
				Arrays.sort(odesTamaino, new TransicionConTamainoVOComparator());
//				UtilesODEs.quicksort(odesTamaino, 0, odesTamaino.length-1);
				form.setListaODESAsArray(odesTamaino);
				String urlImagen = crearUrlImagen();
				form.setUrlImagen(urlImagen);
//				Abro la sesion para recoger el espacio libre que tiene el usuario.
				ObjetosPersonalesSession sesion = this.getObjetosPersonalesSession(request);
				form.setEspacioLibre(sesion.getEspacioLibre());
			}else{
				if(logger.isDebugEnabled())
					logger.debug("No tiene objetos compartidos");
				form.setListaODESAsArray(odes);
			}
		}catch(Exception e){
			logger.error("Error al obtener los objetos compartidos: " , e);
			throw new ValidatorException("{gestor.flujo.error.obtener.compartidos}");
		}
     }

	@Override
	public void getTextoBusqueda(ActionMapping mapping, GetTextoBusquedaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String textBusqueda =form.getTextoBusqueda();
    	String textoBusqueda = textBusqueda.trim();
    	logger.info("La longitud es: " + textoBusqueda.length());
    	if (textoBusqueda.length()==0)
    	{
    		logger.error("Hay que introducir algún criterio de búsqueda");
    		throw new ValidatorException("{gestorFlujo.error.titulo.vacio}");
    	}
		form.setTextoBusqueda(textoBusqueda);
	}
	
	public String crearUrlImagen() throws RemoteException, Exception{
		StringBuffer sb = new StringBuffer();
		sb.append(AgregaPropertiesImpl.getInstance().getUrlNodo());
		sb.append(URL_SEPARATOR);
		sb.append(URL_IMAGEN);
		sb.append("caja_compartido.jpg");
		return sb.toString();
		
	}

}