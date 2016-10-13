/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.gestorFlujo.presentacion.objetosPublicadosAutonomos;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.gestorFlujo.presentacion.ObjetosPersonalesSession;
import es.pode.gestorFlujo.presentacion.utilidades.TransicionAutopublicadosVOComparator;
import es.pode.gestorFlujo.presentacion.utilidades.UtilesCuota;
import es.pode.publicacion.negocio.servicios.TransicionVO;
import es.pode.soporte.cuota.Cuota;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.url.ImagenesAgrega;
import es.pode.soporte.url.ODEPublico;



/**
 * @see es.pode.gestorFlujo.presentacion.objetosPublicadosAutonomos.ObjetosPublicadosAutonomosController
 */
public class ObjetosPublicadosAutonomosControllerImpl extends ObjetosPublicadosAutonomosController {
	private Logger logger = Logger.getLogger(ObjetosPublicadosAutonomosController.class);
	
    /**
     * @see es.pode.gestorFlujo.presentacion.objetosPublicadosAutonomos.ObjetosPublicadosAutonomosController#cargarODESPublicadosAutonomos(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.objetosPublicadosAutonomos.CargarODESPublicadosAutonomosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void cargarODESPublicadosAutonomos(ActionMapping mapping, es.pode.gestorFlujo.presentacion.objetosPublicadosAutonomos.CargarODESPublicadosAutonomosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
    		if(logger.isDebugEnabled())	logger.debug("Cargando objetos publicados autonomos del usuario: [" + form.getIdUsuario()+ "]");
    		//Obtengo todos los odes publicados autonomos del usuario.
    		TransicionVO[] odesPublicadosAutonomos= this.getSrvPublicacionService().obtenODEsPublicadosAutonomoPorUsuario(LdapUserDetailsUtils.getUsuario());
    		
    		//Obtenemos los odes personales asociados al mismo usuario que tiene estos odes Publicados Autonomos. 
			TransicionVO[] odesPersonales=this.getSrvPublicacionService().obtenODEsCreadosPorUsuario(LdapUserDetailsUtils.getUsuario());
			String[] idODESPersonales = new String[odesPersonales.length];
			for (int i = 0; i < odesPersonales.length; i++) {
				idODESPersonales[i] = odesPersonales[i].getIdODE();
			}
			//Calculamos lo que pesan los odes personales.
			Long[] tamanioOdesPersonales = this.getSrvLocalizadorService().consultaEspacioLocalizadores(idODESPersonales);
			
			
			if(odesPublicadosAutonomos !=null && odesPublicadosAutonomos.length>0){
    			//Comentarios - fecha - idODE - idUsuario - tamanio - titulo - compartir
    			TransicionAutopublicadosVO[] odesAutopublicados = new TransicionAutopublicadosVO[odesPublicadosAutonomos.length];
    			String[] idOdesPublicadosAutonomos=new String[odesPublicadosAutonomos.length];
    			    			
				for(int i=0;i<odesPublicadosAutonomos.length;i++){
					odesAutopublicados[i]=new TransicionAutopublicadosVO();
					odesAutopublicados[i].setComentarios(odesPublicadosAutonomos[i].getComentarios());
					odesAutopublicados[i].setFecha(odesPublicadosAutonomos[i].getFecha());
					odesAutopublicados[i].setIdODE(odesPublicadosAutonomos[i].getIdODE());
					if(logger.isDebugEnabled())	logger.debug("El identificador del ODE:"+ odesAutopublicados[i].getIdODE());
					idOdesPublicadosAutonomos[i]=odesPublicadosAutonomos[i].getIdODE();//Los guardamos para hacer la consulta al localizador
					odesAutopublicados[i].setIdUsuario(odesPublicadosAutonomos[i].getIdUsuario());
					odesAutopublicados[i].setTitulo(odesPublicadosAutonomos[i].getTitulo());
					odesAutopublicados[i].setCompartir(false); //Los Publicados autónomos no se pueden compartir con otros usuarios.
					odesAutopublicados[i].setUrlPrevisualizar(ODEPublico.urlPrevisualizaODEPublicadoAutonomo(odesPublicadosAutonomos[i].getIdODE(), LdapUserDetailsUtils.getIdioma()));
				}
				Long[] tamainoOdes=new Long[idOdesPublicadosAutonomos.length];
				if(idOdesPublicadosAutonomos!=null && idOdesPublicadosAutonomos.length>0){
					tamainoOdes=this.getSrvLocalizadorService().consultaEspacioLocalizadores(idOdesPublicadosAutonomos);
				}
				
				for(int i=0;i<odesAutopublicados.length;i++){
					if(tamainoOdes[i]!=null){
						String output = UtilesCuota.parseaCuota(tamainoOdes[i]);
						odesAutopublicados[i].setTamaino(output);
						if(logger.isDebugEnabled())
							logger.debug("Tamaino del ODE con id:"+odesAutopublicados[i].getIdODE()+ "] y su tamaino es:"+output);
					}else{
						odesAutopublicados[i].setTamaino("0");
						if(logger.isDebugEnabled())
							logger.debug("Tamaino del ODE con id:"+odesAutopublicados[i].getIdODE()+ "  su tamaino es: 0");
					}
				}
				Arrays.sort(odesAutopublicados, new TransicionAutopublicadosVOComparator());
//				UtilesODEs.quicksort(odesAutopublicados, 0, odesAutopublicados.length-1);
				form.setListaODESAsArray(odesAutopublicados);
				form.setIdUsuario(LdapUserDetailsUtils.getUsuario());
				form.setUrlImagen(ImagenesAgrega.urlImagenDefectoAutopublicado());
				
				 
				form.setTotalSuma(UtilesCuota.suma(tamanioOdesPersonales, tamainoOdes));
//				
				//form.setTotalSuma(Cuota.totalTamainoOdes(tamainoOdes));
				if(logger.isDebugEnabled())	logger.debug("Hacemos la suma ["+ form.getTotalSuma()+"]");
				long cuota=LdapUserDetailsUtils.getCuota()!=null?LdapUserDetailsUtils.getCuota():0L; //Lo tendremos que recoger de soporte
				if(logger.isDebugEnabled())	logger.debug("Recogemos la cuota de soporte "+ cuota);
				form.setPorcentajeMemoriaCubierta(Cuota.porcentajeCubierto(form.getTotalSuma(),cuota));
				//La cuota irá en MB, no en bytes
				long divCuota = cuota/(1024*1024);
				
				form.setCuotaUsuario(divCuota);
//				form.setEspacioLibre(Cuota.restanteTamaino(form.getTotalSuma(),cuota));
//				Abro la sesion para recoger el espacio libre que tiene el usuario.
				ObjetosPersonalesSession sesion = this.getObjetosPersonalesSession(request);
				form.setEspacioLibre(sesion.getEspacioLibre());
				
			}else{
				if(logger.isDebugEnabled())	logger.debug("No hay objetos publicados autonomos para el usuario: ["+ LdapUserDetailsUtils.getUsuario()+"]");
				form.setListaODESAsArray(odesPublicadosAutonomos);
				form.setIdUsuario(LdapUserDetailsUtils.getUsuario());
				//Paso a la suma total lo que pesan los odes personales.
				form.setTotalSuma(Cuota.totalTamainoOdes(tamanioOdesPersonales));
				long cuota=LdapUserDetailsUtils.getCuota()!=null?LdapUserDetailsUtils.getCuota():0L; //Lo tendremos que recoger de soporte
				form.setPorcentajeMemoriaCubierta(Cuota.porcentajeCubierto(form.getTotalSuma(),cuota));
				//La cuota debe ir en MB
				long divCuota = LdapUserDetailsUtils.getCuota()/(1024*1024);
				form.setCuotaUsuario(divCuota);
				form.setEspacioLibre(LdapUserDetailsUtils.getCuota());
			}
    	}catch(Exception e){
			logger.error("Error al obtener los objetos publicados autonomos: " + e);
			throw new ValidatorException("{gestor.flujo.error.obtener.publicadosAutonomos}");
		}
     }




    /**
     * @see es.pode.gestorFlujo.presentacion.objetosPublicadosAutonomos.ObjetosPublicadosAutonomosController#cargaFormDespublicarAutonomos(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.objetosPublicadosAutonomos.CargaFormDespublicarAutonomosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void cargaFormDespublicarAutonomos(ActionMapping mapping, es.pode.gestorFlujo.presentacion.objetosPublicadosAutonomos.CargaFormDespublicarAutonomosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
       
     }









}