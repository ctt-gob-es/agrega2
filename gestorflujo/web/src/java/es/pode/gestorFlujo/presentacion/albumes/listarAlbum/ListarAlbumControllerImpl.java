/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.gestorFlujo.presentacion.albumes.listarAlbum;

import java.rmi.RemoteException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.gestorFlujo.presentacion.ObjetosPersonalesSession;
import es.pode.gestorFlujo.presentacion.objetosPersonales.AlbumConTamainoVO;
import es.pode.gestorFlujo.presentacion.objetosPersonales.ObjetosPersonalesControllerImpl;
import es.pode.gestorFlujo.presentacion.objetosPersonales.TransicionConTamainoVO;
import es.pode.gestorFlujo.presentacion.utilidades.TransicionConTamainoVOComparator;
import es.pode.gestorFlujo.presentacion.utilidades.UtilesCuota;
import es.pode.publicacion.negocio.servicios.AlbumVO;
import es.pode.publicacion.negocio.servicios.TransicionVO;
import es.pode.soporte.cuota.Cuota;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.gestorFlujo.presentacion.albumes.listarAlbum.ListarAlbumController
 */
public class ListarAlbumControllerImpl extends ListarAlbumController
{
	private static final long serialVersionUID = 1L;
	
	protected final static String URL_SEPARATOR = "/";
	protected final static String URL_IMAGEN ="static/img/azul/";
	private Logger logger = Logger.getLogger(ListarAlbumController.class);
	public final static String ODES_POR_ALBUM = "odesPorAlbum";
	private static final String PUNTOS_SUSPENSIVOS="..";

    public void cargarOdesAlbum(ActionMapping mapping, CargarOdesAlbumForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		Primero me traigo todos los albumes del usuario, sin ellos, no pinto la pantalla.
		AlbumVO[] albumes = null;
    	try{
			  albumes = this.getSrvAlbumService().obtenAlbumesPorUsuario(LdapUserDetailsUtils.getUsuario());
			  if(albumes != null && albumes.length>0) {
				  logger.debug("Se han cargado ["+ albumes.length+ "] albumes del usuario["+ LdapUserDetailsUtils.getUsuario()+"]");
				  AlbumConTamainoVO[] albumesConTamaino = new AlbumConTamainoVO[albumes.length];
				  for (int i = 0; i < albumesConTamaino.length; i++) {
					  albumesConTamaino[i] = new AlbumConTamainoVO();
					  albumesConTamaino[i].setDescripcion(albumes[i].getDescripcion());
					  albumesConTamaino[i].setFechaCreacion(albumes[i].getFechaCreacion());
					  albumesConTamaino[i].setId(albumes[i].getId());
					  albumesConTamaino[i].setNumeroOdes(albumes[i].getOdes().length);
					  if(albumes[i].getTitulo().length()>20){
						  String tituloCorto =albumes[i].getTitulo().substring(0, 18)+PUNTOS_SUSPENSIVOS;
					  	  albumesConTamaino[i].setTitulo(tituloCorto);
					  }
					  else{
						  albumesConTamaino[i].setTitulo(albumes[i].getTitulo());
					  }
					  albumesConTamaino[i].setUsuario(albumes[i].getUsuario());
					}
				 form.setListaAlbumesAsArray(albumesConTamaino);
				 form.setIdUsuario(LdapUserDetailsUtils.getUsuario());
	  		}
			  String urlImagen = crearUrlImagen();
			  form.setUrlImagen(urlImagen);
//			  Abro la sesion para recoger el espacio libre que tiene el usuario.
			ObjetosPersonalesSession sesion = this.getObjetosPersonalesSession(request);
			form.setEspacioLibre(sesion.getEspacioLibre());
			//TransicionVO[] transicion = this.getSrvAlbumService().obtenOdesSinAlbum(LdapUserDetailsUtils.getUsuario());
		} catch (Exception ex) {
			logger.error("Error inesperado cargando los albumes del usuario: " + LdapUserDetailsUtils.getUsuario(), ex);
			throw new ValidatorException("{gestorFlujo.error.inesperado}");
		}
    	
    	
    	
    	try{

			ObjetosPersonalesSession sesion = this.getObjetosPersonalesSession(request);
//			Si el formulario tiene el idAlbum, es que vengo de un pinchazo en uno de los links del menu de albumes
//			Si el formulario no tiene idAlbum, es que vengo algun otro sitio, consulto la variable de sesion de idAlbum por si hay algun album que
//			listar, y si no, casco (no me queda mas remedio, no tengo album que listar)
			Long idAlbum = form.getIdAlbum();
			if (idAlbum == null) {
				if (sesion.getIdAlbum()== null)
					throw new Exception("Error listando albumes con idAlbum vacio en sesion y formulario["+form+"]");
				idAlbum = sesion.getIdAlbum();
			}
				
        	sesion.setIdAlbum(idAlbum);
        	sesion.setRetorno(ODES_POR_ALBUM);
						
			if(logger.isDebugEnabled()) logger.debug("Cargando los odes del album["+idAlbum+"]");
			TransicionVO[] odesAlbum = this.getSrvAlbumService().obtenOdesPorAlbum(idAlbum);
			TransicionConTamainoVO[] odesAlbumTamanio = new TransicionConTamainoVO[odesAlbum.length];
			
			//Obtenemos los odes autopublicados y personales para calcular el porcentaje de memoria cubierto.
			TransicionVO[] odesPers = this.getSrvPublicacionService().obtenODEsCreadosPorUsuario(LdapUserDetailsUtils.getUsuario()); 
			TransicionVO[] odesPublicadosAutonomos=this.getSrvPublicacionService().obtenODEsPublicadosAutonomoPorUsuario(LdapUserDetailsUtils.getUsuario());
			
			String[] idODESPublicadosAutonomos = new String[odesPublicadosAutonomos.length];
			for (int i = 0; i < odesPublicadosAutonomos.length; i++) {
				idODESPublicadosAutonomos[i] = odesPublicadosAutonomos[i].getIdODE();
			}
			//Calculamos lo que pesan los odes publicados Autonomos.
			Long[] tamanioOdesPublicadosAutonomos= this.getSrvLocalizadorService().consultaEspacioLocalizadores(idODESPublicadosAutonomos);
			
			if(odesPers !=null && odesPers.length>0){
				TransicionConTamainoVO[] odesTamaino=new TransicionConTamainoVO[odesPers.length];
				String[] identificadoresOdesPers=new String[odesPers.length];
				for(int i=0;i<odesPers.length;i++){
					odesTamaino[i]=new TransicionConTamainoVO();
					identificadoresOdesPers[i]=odesPers[i].getIdODE();//Los guardamos para hacer la consulta al localizador
				}
				Long[] tamainoOdes=new Long[identificadoresOdesPers.length];
				if(identificadoresOdesPers!=null && identificadoresOdesPers.length>0){
					tamainoOdes=this.getSrvLocalizadorService().consultaEspacioLocalizadores(identificadoresOdesPers);
				}
				for(int i=0;i<odesTamaino.length;i++){
					if(tamainoOdes[i]!=null){
						String output = UtilesCuota.parseaCuota(tamainoOdes[i]);
						odesTamaino[i].setTamaino(output);
						if(logger.isDebugEnabled())
							logger.debug("Tamaino del ODE con id:"+odesTamaino[i].getIdODE()+ "] y su tamaino es:"+output);
					}else{
						odesTamaino[i].setTamaino("0");
						if(logger.isDebugEnabled())
							logger.debug("Tamaino del ODE con id:"+odesTamaino[i].getIdODE()+ "  su tamaino es: 0");
					}
				}
				//Sumamos el peso de los personales y de los publicados Autonomos
				form.setTotalSuma(UtilesCuota.suma(tamainoOdes, tamanioOdesPublicadosAutonomos));
				//form.setTotalSuma(Cuota.totalTamainoOdes(tamainoOdes));
				long cuota=LdapUserDetailsUtils.getCuota()!=null?LdapUserDetailsUtils.getCuota():0L; //Lo tendremos que recoger de soporte
				form.setPorcentajeMemoriaCubierta(Cuota.porcentajeCubierto(form.getTotalSuma(),cuota));
//				La cuota irá en MB, no en bytes
				long divCuota = cuota/(1024*1024);
				form.setCuotaUsuario(divCuota);
				form.setEspacioLibre(Cuota.restanteTamaino(form.getTotalSuma(),cuota));
				if(logger.isDebugEnabled())	logger.debug("Insertamos usuario: ["+ form.getIdUsuario()+"], el total de la suma es: ["+form.getTotalSuma()+"] y la cuota de soporte: ["+cuota+"]");
			}else{
				form.setTotalSuma(Cuota.totalTamainoOdes(tamanioOdesPublicadosAutonomos));
				long cuota=LdapUserDetailsUtils.getCuota()!=null?LdapUserDetailsUtils.getCuota():0L;
				form.setPorcentajeMemoriaCubierta(Cuota.porcentajeCubierto(form.getTotalSuma(),cuota));
				//La cuota debe ir en MB
				long divCuota = LdapUserDetailsUtils.getCuota()/(1024*1024);
				form.setCuotaUsuario(divCuota);
				form.setEspacioLibre(LdapUserDetailsUtils.getCuota());
			}
			
			
			
			//Mapeo los odes del album, desde transicionVO, hasta TransicionConTamanioVO para pasar el tamaino de los odes.
			String[] identificadoresOdesAlbum=new String[odesAlbum.length];
			for (int i = 0; i < odesAlbum.length; i++) {
				odesAlbumTamanio[i]=new TransicionConTamainoVO();
				odesAlbumTamanio[i].setComentarios(odesAlbum[i].getComentarios());
				odesAlbumTamanio[i].setCompartir(odesAlbum[i].getCompartido());
				odesAlbumTamanio[i].setFecha(odesAlbum[i].getFecha());
				odesAlbumTamanio[i].setIdODE(odesAlbum[i].getIdODE());
				odesAlbumTamanio[i].setIdUsuario(odesAlbum[i].getIdUsuario());
				odesAlbumTamanio[i].setTitulo(odesAlbum[i].getTitulo());
				odesAlbumTamanio[i].setAlbum(odesAlbum[i].getAlbum().getTitulo()==null?"":odesAlbum[i].getAlbum().getTitulo());
//				Calculo el tamanio de cada uno de los odes del album
				identificadoresOdesAlbum[i]=odesAlbum[i].getIdODE();//Los guardamos para hacer la consulta al localizador
				Long[] tamanioOdesAlbum=new Long[odesAlbum.length];
				if(identificadoresOdesAlbum!=null && identificadoresOdesAlbum.length>0){
					tamanioOdesAlbum=this.getSrvLocalizadorService().consultaEspacioLocalizadores(identificadoresOdesAlbum);
				}
				if(tamanioOdesAlbum[i]!=null){
					if(logger.isDebugEnabled())	logger.debug("tamanioOdesAlbum[i]:"+ tamanioOdesAlbum[i]);
					String output = UtilesCuota.parseaCuota(tamanioOdesAlbum[i]);
					odesAlbumTamanio[i].setTamaino(output);
					if(logger.isDebugEnabled())	logger.debug("Tamaino del ODE con id:"+odesAlbumTamanio[i].getIdODE()+ "] que esta en el album: ["+idAlbum+"]y su tamaino es:"+output);
				}else{
					odesAlbumTamanio[i].setTamaino("0");
					if(logger.isDebugEnabled())	logger.debug("Tamaino del ODE con id:"+odesAlbumTamanio[i].getIdODE()+ "]que esta en el album: ["+idAlbum+"] y su tamaino es: 0");
				}
			}
			Arrays.sort(odesAlbumTamanio, new TransicionConTamainoVOComparator());
//			UtilesODEs.quicksort(odesAlbumTamanio, 0, odesAlbumTamanio.length-1);
			form.setListaODESAsArray(odesAlbumTamanio);
			form.setIdUsuario(LdapUserDetailsUtils.getUsuario());
			try {
				form.setNombreAlbum(this.getSrvAlbumService().obtenerAlbumPorId(idAlbum).getTitulo());	
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}catch(Exception e){
			logger.error("Error al obtener los objetos personales: " + e);
			throw new ValidatorException("{gestor.flujo.error.obtener.personales}");
		}
		
    }


	public void compartirDescompartirODE(ActionMapping mapping, CompartirDescompartirODEForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		ResultadoOperacionVO resultado;
		logger.debug("La variable que nos llega es:"+form.getIdODE()+" "+form.getCompartidoSN());
		if(!(Boolean.valueOf(form.getCompartidoSN()).booleanValue())){
			logger.debug("Vamos a COMPARTIRLO");
			this.getSrvPublicacionService().compartirODE(form.getIdODE());
		}else{
			this.getSrvPublicacionService().descompartirODE(form.getIdODE());
			logger.debug("Esta compartido y lo DESCOMPARTIREMOS");
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
	
	public String crearUrlImagen() throws RemoteException, Exception{
		StringBuffer sb = new StringBuffer();
		sb.append(AgregaPropertiesImpl.getInstance().getUrlNodo());
		sb.append(URL_SEPARATOR);
		sb.append(URL_IMAGEN);
		sb.append("caja_personal.jpg");
		return sb.toString();
		
	}
	
}