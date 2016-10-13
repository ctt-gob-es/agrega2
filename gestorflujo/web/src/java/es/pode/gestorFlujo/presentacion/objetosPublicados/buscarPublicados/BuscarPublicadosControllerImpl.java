/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.gestorFlujo.presentacion.objetosPublicados.buscarPublicados;

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
//import es.pode.localizador.negocio.servicios.SrvLocalizadorService;
import es.pode.publicacion.negocio.servicios.SrvPublicacionService;
import es.pode.publicacion.negocio.servicios.TransicionVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;

public class BuscarPublicadosControllerImpl extends BuscarPublicadosController
{
	private static Logger log = Logger.getLogger(BuscarPublicadosControllerImpl.class);
	protected final static String URL_SEPARATOR = "/";
	protected final static String URL_IMAGEN ="static/img/azul/";

	
	@Override
	public void buscarOdesPorTitulo(ActionMapping mapping,
			BuscarOdesPorTituloForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//Este metodo debe ser identico al BuscarCompartidosControllerImpl.buscarOdesPorTitulo
		//A excepcion de que la llamada que haga al publicador para obtener la lista de ODEs
		//debe ser un nuevo metodo del publicador que recupere los ODEs con estado PUBLICADOS por titulo

		String textoBusqueda = form.getTextoBusqueda();
		log.debug("El filtro de busqueda es:"  + textoBusqueda);
		
		TransicionVO[] odes =null;
		
		try{
		    		
			log.debug("Cargando objetos compartidos encontrados");
			SrvPublicacionService publi = this.getSrvPublicacionService();
			//SrvLocalizadorService localizador=this.getSrvLocalizadorService();
			SrvAdminUsuariosService admin=this.getSrvAdminUsuariosService();
			try {
				String[] todosUsuariosGrupos=admin.obtenerListaUsuariosGrupoTrabajo(LdapUserDetailsUtils.getUsuario());
				
				if(todosUsuariosGrupos!=null && todosUsuariosGrupos.length>0){
					log.info("Obtenidos lista de usuarios de los grupos pertenecientes de usuario:["+LdapUserDetailsUtils.getUsuario()+"Numero de usuarios:["+todosUsuariosGrupos.length);
					odes=publi.obtenODEsPublicadosPorTituloYUsuario(textoBusqueda, todosUsuariosGrupos);
					log.info("Obtenidos odes de esos usuarios, numero de odes pendientes de publicacion ["+odes.length);
				}else{
					log.info("Obtenidos lista de todos los ODES, pues el usuario:["+LdapUserDetailsUtils.getUsuario()+" es parte de todos los grupos");
					odes=publi.obtenODEsPublicadosPorTitulo(textoBusqueda);
					log.info("Obtenidos odes de todos los usuarios, numero de odes pendientes de publicacion["+odes.length);
				}
				
			} catch (Exception ex) {
				log.error("Imposible obtener los odes pendientes de publicacion", ex);
				throw new ValidatorException("{gestorFlujo.error.inesperado}");
			}
			
			if(odes!=null && odes.length>0){

			/*
				TransicionConTamainoVO[] odesTamaino=new TransicionConTamainoVO[odes.length];
				String[] identificadoresOdesCompartidos=new String[odes.length];
				log.debug("Entramos en el mapeo de "+ odes.length+ " elementos");
	
				for(int i=0;i<odes.length;i++){
					TransicionConTamainoVO conTamaino=new TransicionConTamainoVO();
					log.debug("Estamos en la posicion: "+i);
					conTamaino.setComentarios(odes[i].getComentarios());
					conTamaino.setFecha(odes[i].getFecha());
					conTamaino.setIdODE(odes[i].getIdODE());
					identificadoresOdesCompartidos[i]=odes[i].getIdODE();//Los guardamos para hacer la consulta al localizador
					conTamaino.setIdUsuario(odes[i].getIdUsuarioCreacion());//Nombre del creador!!!!!!!!!!!!
					conTamaino.setTitulo(odes[i].getTitulo());
					odesTamaino[i]=conTamaino;
				}
				
				Long[] tamainoOdes=null;
				
				if(identificadoresOdesCompartidos!=null && identificadoresOdesCompartidos.length>0)
					tamainoOdes=localizador.consultaEspacioLocalizadores(identificadoresOdesCompartidos);
				
				for(int i=0;i<odes.length;i++){
					//Vamos a insertar le tamaino en MB,no en bytes; 
					double mb = (double)tamainoOdes[i]/(1024*1024);
					String pattern="###.##";
					java.text.DecimalFormat myFormatter = new java.text.DecimalFormat(pattern);
					String output = myFormatter.format(mb);
					odesTamaino[i].setTamaino(output);
					log.debug("Tamaino del ODE encontrado con id:"+odesTamaino[i].getIdODE()+ ", su div["+mb+"] y su tamaino es:"+output);					
				}
				
				Arrays.sort(odesTamaino, new TransicionConTamainoVOComparator());
				form.setListadoOdesCompartidosTituloAsArray(odesTamaino);
				String urlImagen = crearUrlImagen();
				form.setUrlImagen(urlImagen);
				form.setNumMaxResultados(odesTamaino.length);
				//Abro la sesion para recoger el espacio libre que tiene el usuario.
				ObjetosPersonalesSession sesion = this.getObjetosPersonalesSession(request);
				form.setEspacioLibre(sesion.getEspacioLibre());
				*/

				//TODO es posible que falte ordenar los ODEs
				form.setListadoOdesPublicadosTituloAsArray(odes);
				String urlImagen = crearUrlImagen();
				form.setUrlImagen(urlImagen);
				form.setNumMaxResultados(odes.length);
				//Abro la sesion para recoger el espacio libre que tiene el usuario.
				ObjetosPersonalesSession sesion = this.getObjetosPersonalesSession(request);
				form.setEspacioLibre(sesion.getEspacioLibre());
			} else {
				log.debug("No se encontraron objetos compartidos que coincidan con esa búsqueda");
				form.setListadoOdesPublicadosTituloAsArray(odes);
			}
		
		} catch(Exception e) {
			log.error("Error al obtener los objetos publicados: " , e);
			throw new ValidatorException("{gestor.flujo.error.obtener.publicados}");
		}
	}
	
	
	private String crearUrlImagen() throws RemoteException, Exception{
		StringBuffer sb = new StringBuffer();
		sb.append(AgregaPropertiesImpl.getInstance().getUrlNodo());
		sb.append(URL_SEPARATOR);
		sb.append(URL_IMAGEN);
		sb.append("caja_compartido.jpg");
		return sb.toString();
	}

}
