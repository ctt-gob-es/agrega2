/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.presentacion.favoritos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.adminusuarios.negocio.servicios.FavoritoVO;
import es.pode.presentacion.OdeSession;
import es.pode.presentacion.VisualizadorSession;
import es.pode.presentacion.exceptions.NoAutenticadoException;
import es.pode.presentacion.utils.OdeSessionUtils;



/**
 * @see es.pode.presentacion.favoritos.FavoritosController
 */
public class FavoritosControllerImpl extends FavoritosController
{
	protected static Logger logger = Logger.getLogger(FavoritosControllerImpl.class);



    public final void asociarAFavoritos(
    		ActionMapping mapping, 
    		AsociarAFavoritosForm form,
    		HttpServletRequest request,
    		HttpServletResponse response) 
    throws Exception
    {
    	try {
	        VisualizadorSession sesion = this.getVisualizadorSession(request);
	    	String identificador = form.getIdentificador();
	    	OdeSession odeSesion = OdeSessionUtils.getOdeSession(sesion, identificador);
	        
	        
	    	if(	!sesion.isVerFavorito())
	    	{
	    		logger.error("El ODE no se puede visualizar porque el usuario no est� logado ");
				request.setAttribute("codigo_error", "noautenticado"); 
	    		throw new NoAutenticadoException("Usuario No Autenticado");
	    	}
	    	
	        FavoritoVO favorito= new FavoritoVO();
	        favorito.setId_mec(identificador);
	        favorito.setIdioma(odeSesion.getIdiomaCat());
	        favorito.setTitulo(odeSesion.getTituloOde());
	        
	        this.getSrvPerfilPublico().anadirFavoritoAUsuario(favorito, sesion.getUsuario());
		} catch (Exception e) {
			logger.error("error al incluir Destacado: " , e);
		}
   
     }







    public final String retorno(
    		ActionMapping mapping, 
    		RetornoForm form, 
    		HttpServletRequest request, 
    		HttpServletResponse response) 
    throws Exception
    {
        return form.getRetorno();
    }









}