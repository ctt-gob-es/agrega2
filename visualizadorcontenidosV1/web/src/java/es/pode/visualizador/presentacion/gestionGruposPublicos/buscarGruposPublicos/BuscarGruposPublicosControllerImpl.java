/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.visualizador.presentacion.gestionGruposPublicos.buscarGruposPublicos;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.adminusuarios.negocio.servicios.GrupoPublicoAsociadoVO;
import es.pode.adminusuarios.negocio.servicios.SolicitudGrupoVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.url.ImagenesAgrega;



/**
 * @see es.pode.visualizador.presentacion.gestionGruposPublicos.buscarGruposPublicos.buscarGruposPublicosController
 */
public class BuscarGruposPublicosControllerImpl extends BuscarGruposPublicosController
{



	private static Logger logger = Logger.getLogger(BuscarGruposPublicosControllerImpl.class);


    /**
     * @see es.pode.visualizador.presentacion.gestionGruposPublicos.buscarGruposPublicos.buscarGruposPublicosController#listarGruposPublicosPorNombre(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionGruposPublicos.buscarGruposPublicos.ListarGruposPublicosPorNombreForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void listarGruposPublicosPorNombre(ActionMapping mapping, es.pode.visualizador.presentacion.gestionGruposPublicos.buscarGruposPublicos.ListarGruposPublicosPorNombreForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String nombre=form.getTextoBusqueda();
    	if(nombre!=null && nombre.equals("")){
    		nombre=form.getTextoBuscado();
    	}
    	logger.info("Vamos a buscar grupos de nombre "+nombre);
    	form.setTextoBuscado(nombre);
    	try{
    		String usuario=LdapUserDetailsUtils.getUsuario();
    		form.setUsuario(usuario);
    		GrupoPublicoAsociadoVO[] gruposBusqueda = this.getSrvPerfilPublico().buscarGruposPorNombre(nombre, usuario);
    		SolicitudGrupoVO[] solicitudes = this.getSrvPerfilPublico().listarSolicitudesRealizadasPorUsuario(usuario);
    		int k=0;
    		for(int i=0;i<gruposBusqueda.length;i++){
    			if(gruposBusqueda[i].getAsociado().equals(Boolean.TRUE)){
    					k++;
    			}else if(gruposBusqueda[i].getAsociado().equals(Boolean.FALSE)){//Aunque no estén asociados tienen una petición de asociación hecha!!
    				String[] nombres=new String[solicitudes.length];
    	    		for(int j=0;j<solicitudes.length;j++){
    	    			String nombreGrupo=solicitudes[j].getGrupo();
    	    			nombres[j]=nombreGrupo;
    	    		}
    	    		List list =Arrays.asList(nombres);
    	    		if(list.contains(gruposBusqueda[i].getNombre())){
    	    			gruposBusqueda[i].setAsociado(Boolean.TRUE);
        				k++;
        			}else{
        				gruposBusqueda[i].setAsociado(Boolean.FALSE);
        			}
    	    		logger.info("En el caso de la asociacion pedida para el grupo "+gruposBusqueda[i].getNombre()+ " es "+gruposBusqueda[i].getAsociado());
    			}
    			 String imagenGrupo=gruposBusqueda[i].getImagenGrupo();
    		       if(imagenGrupo.equals("ImagenDefectoGrupo1"))
    		    	   gruposBusqueda[i].setImagenGrupo(ImagenesAgrega.urlImagenDefectoGrupo(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_GRUPO_1)));
    	    	   else if(imagenGrupo.equals("ImagenDefectoGrupo2"))
    	    		   gruposBusqueda[i].setImagenGrupo(ImagenesAgrega.urlImagenDefectoGrupo(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_GRUPO_2)));
    	    	   else if(imagenGrupo.equals("ImagenDefectoGrupo3"))
    	    		   gruposBusqueda[i].setImagenGrupo(ImagenesAgrega.urlImagenDefectoGrupo(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_GRUPO_3)));
    	    	   else if(imagenGrupo.equals("ImagenDefectoGrupo4"))
    	    		   gruposBusqueda[i].setImagenGrupo(ImagenesAgrega.urlImagenDefectoGrupo(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_GRUPO_4)));
    	    	   else
    	    		   gruposBusqueda[i].setImagenGrupo(ImagenesAgrega.urlImagenDefectoGrupo(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NOMBRE_IMAGEN_DEFECTO_GRUPO_5)));
    			 
    		}
    		if(k==gruposBusqueda.length){
    			form.setTodosAsociados(Boolean.TRUE);
    		}else{
    			form.setTodosAsociados(Boolean.FALSE);
    		}

    		form.setGruposPublicosAsArray(gruposBusqueda);
    		form.setTextoBusqueda(null);
    	}catch(Exception e){
    		logger.error("Error: "+e);
    		throw new ValidatorException("{grupos.publicos.buscar.error}");
    	}

     }







//    /**
//     * @see es.pode.visualizador.presentacion.gestionGruposPublicos.buscarGruposPublicos.buscarGruposPublicosController#getIds(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionGruposPublicos.buscarGruposPublicos.GetIdsForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
//     */
//    public final void getIds(ActionMapping mapping, es.pode.visualizador.presentacion.gestionGruposPublicos.buscarGruposPublicos.GetIdsForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
//    {
//    	try {
//		    List lista = ((ListarGruposPublicosAsociarFormImpl) form).getIdsRowSelection();
//		    if (lista == null) {
//	
//		    	throw new ValidatorException("{errors.asociarGrupos.idNulo}");
//		    } 
//		    form.setIdCollection(lista);
//       
//       } catch (ValidatorException e){
//    	   throw e;
//	    	   
//       } catch (Exception e){
//    	   
//    	   logger.error("Se ha producido un error al intentar recuperar los ids para asociar: " +e);
//    	   throw new ValidatorException ("{errors.asociarGrupos.idError}");
//    	   
//		       }
//    }






    /**
     * This dummy variable is used to populate the "gruposPublicos" table.
     * You may delete it when you add you own code in this controller.
     */
    private static final java.util.Collection gruposPublicosDummyList =
        java.util.Arrays.asList( new Object[] {
            new GruposPublicosDummy("gruposPublicos-1"),
            new GruposPublicosDummy("gruposPublicos-2"),
            new GruposPublicosDummy("gruposPublicos-3"),
            new GruposPublicosDummy("gruposPublicos-4"),
            new GruposPublicosDummy("gruposPublicos-5")
        } );

    /**
     * This inner class is used in the dummy implementation in order to get the web application
     * running without any manual programming.
     * You may delete this class when you add you own code in this controller.
     */
    public static final class GruposPublicosDummy implements java.io.Serializable
    {
        private String gruposPublicos = null;

        public GruposPublicosDummy(String gruposPublicos)
        {
            this.gruposPublicos = gruposPublicos;
        }
        
        public void setGruposPublicos(String gruposPublicos)
        {
            this.gruposPublicos = gruposPublicos;
        }

        public String getGruposPublicos()
        {
            return this.gruposPublicos;
        }
        
    }


	public void buscarTextoBusquedaGrupos(ActionMapping mapping,
			BuscarTextoBusquedaGruposForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String texto = form.getTextoBusqueda();
		String textoBusqueda = texto.trim();
	    	logger.info("La longitud es: " + texto.length());
	    	if (textoBusqueda.length()==0 && textoBusqueda!=null )
	    	{
	    		logger.error("Hay que introducir algún criterio de búsqueda");
	    		throw new ValidatorException("{grupos.error.grupo.vacio}");
	    	}else{
	    		form.setTextoBusqueda(textoBusqueda);
	    	}
	}

	



}