/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.presentacion.visualizadorSinSecuencia;

import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.agrega.soporte.agregaProperties.DecisorOffline;
import es.agrega.soporte.tag.url.TransformarURL;
import es.pode.entregar.negocio.servicios.ItemVO;
import es.pode.entregar.negocio.servicios.OrganizacionVO;
import es.pode.presentacion.OdeSession;
import es.pode.presentacion.VisualizadorSession;
import es.pode.presentacion.exceptions.OdeNoEncontradoException;
import es.pode.presentacion.utils.OdeSessionUtils;
import es.pode.publicacion.negocio.servicios.ContenidoInapropiadoVO;



/**
 * @see es.pode.presentacion.visualizadorSinSecuencia.VisualizadorSSController
 */
public class VisualizadorSSControllerImpl extends VisualizadorSSController
{
	private static final String PROTOCOLO_HTTP="http://";
	private static final String PROTOCOLO_HTTPS="https://";
	private static final String HOST="host";
	private static final String SUBDOMINIO="subdominio";

	private static Logger logger = Logger.getLogger(VisualizadorSSControllerImpl.class);


    public final void cargarDatos(
    		ActionMapping mapping, 
    		CargarDatosForm form, 
    		HttpServletRequest request, 
    		HttpServletResponse response) 
    throws Exception
    {
      	try{
            VisualizadorSession sesion = this.getVisualizadorSession(request);	
            
            String identificador="";
            if (form.getIdentificador()!=null)
            	identificador = form.getIdentificador();
            
            //sesion puede ser null!
            if (sesion==null) {
				logger.debug("sesion es null asi que no se devolveran datos.");
	            form.setDatosSalidaAsArray(null);
	            form.setUsuario("");
	            //form.setIdentificador("");
	            form.setLocalizacion("");
	            form.setTituloOde("");
	            form.setIdiomaCat("");
	            form.setRetorno("");
	            form.setValoracion("");
	            form.setRutaSeleccionado(null);
	            form.setEstaValorado(false);
	            form.setSecuencia(false);
	            // 29012013 El menú se mostrará siempre desplegado	            
	            form.setMenuDesplegado(true);	    	
	        	form.setUrlContenido("");
	            form.setIdSeleccionado("");
	            return;
            }
       		
            identificador = form.getIdentificador()!=null?form.getIdentificador():sesion.getIdentificador();
	    	OdeSession odeSesion = OdeSessionUtils.getOdeSession(sesion, identificador);
	    	
            //sesion puede ser null!
            if (odeSesion==null) {
				logger.debug("odeSesion es null asi que no se devolveran datos.");
	            form.setDatosSalidaAsArray(null);
	            form.setUsuario("");
	            //form.setIdentificador("");
	            form.setLocalizacion("");
	            form.setTituloOde("");
	            form.setIdiomaCat("");
	            form.setRetorno("");
	            form.setValoracion("");
	            form.setRutaSeleccionado(null);
	            form.setEstaValorado(false);
	            form.setSecuencia(false);
	            // 29012013 El menú se mostrará siempre desplegado	            
	            form.setMenuDesplegado(true);	    	
	        	form.setUrlContenido("");
	            form.setIdSeleccionado("");
	            return;
            }
            
            OrganizacionVO[] orgs = null;
            if (odeSesion.getOrganizaciones()!=null)
            	orgs = (OrganizacionVO[]) odeSesion.getOrganizaciones().toArray(new OrganizacionVO[0]);
            else orgs=null;
       		
            form.setDatosSalidaAsArray(orgs);
            form.setUsuario(sesion.getUsuario());
            form.setIdentificador(identificador);

            if (odeSesion.getLocalizadorCont()!=null)
            	form.setLocalizacion(odeSesion.getLocalizadorCont());
            else form.setLocalizacion("");
            
            if(form.getIdSeleccionado()==null ) {
            	
            	if(odeSesion.getIdSeleccionado()==null || odeSesion.getIdSeleccionado().equals("") ) {

            		//Insertamos la página en blanco
            		odeSesion.setUrlContenido( "" );
    
            		//Añadido para cargar el primer item, si solo tiene un item en una sola organizacion        
            		//Insertamos URL del primer item de la primera organizacion, si sólo tiene una organizacion y un solo item
	    	    	if(form.getDatosSalida()!=null && form.getDatosSalida().size()>0){
	    	    		OrganizacionVO[] organizacionLista=(OrganizacionVO[])form.getDatosSalida().toArray(new OrganizacionVO[form.getDatosSalida().size()]);
	    	    		OrganizacionVO organizacion=organizacionLista[0];
	
	    	    		if(organizacion.getItems()!=null && organizacion.getItems().length>0){
	
	    	    			//cargamos el primer item visible
	    	    			ArrayList lista = new ArrayList();
	            			String refRecurso="";
	            			contenidosArbolPre(organizacion.getItems(), lista);
	            			
	            			if ((lista!=null) && (lista.size()>0)) {
	            				ItemVO it;// = new ItemVO();
	            				boolean enc=false;
	            				
	            				for(int i=0; i < lista.size()&& !enc;i++){
	    	        				it=(ItemVO)lista.get(i);
	    	        				
	    	        				if ((it!=null) && (it.getRecurso()!=null) && (it.getVisible()!=null)) { // || it.getVisible() mostramos el primero sea visible o no
	    	        					enc=true;
	    	        					refRecurso=it.getRecurso().getHrefRec();
	    		    					String urlContenido = form.getLocalizacion() + "/" + refRecurso;
	    		    					if( logger.isDebugEnabled() )
	    		    						logger.debug("VISUALIZADOR CargarDatos urlContenido " + urlContenido);
	    		    					int co = -1;
	    		    					if( refRecurso.toLowerCase().indexOf(PROTOCOLO_HTTP) >= 0)
	    		    						co = refRecurso.toLowerCase().indexOf(PROTOCOLO_HTTP);
	    		    					else if( refRecurso.toLowerCase().indexOf(PROTOCOLO_HTTPS) >= 0 )
	    		    						co = refRecurso.toLowerCase().indexOf(PROTOCOLO_HTTPS);
	    		    						
	    		    					if (co < 0) {//pner tb en botones
	    		    						String server = "";
	    		    						if (DecisorOffline.esOffline())
	    		    							server =  request.getServerName() + ":" + request.getServerPort();
	    		    						else
	    		    							server = AgregaPropertiesImpl.getInstance().getProperty(HOST) + AgregaPropertiesImpl.getInstance().getProperty(SUBDOMINIO);
	    		    						
	    			    					urlContenido = PROTOCOLO_HTTP + server + urlContenido;
	    		    					}else{
	    		    						urlContenido =refRecurso.substring(co);
	    		    					}
	    		    					
//	    		    					Vamos a controlar que si el contenido es una url, estará bien y no le vamos a quitar los caracteres ?, &...
	    		    					if (!((urlContenido.toLowerCase().indexOf(PROTOCOLO_HTTP) >=0) || (urlContenido.toLowerCase().indexOf(PROTOCOLO_HTTPS) >=0))) {
	    		    						String encoding = response.getCharacterEncoding();
	    		    						urlContenido = TransformarURL.transformaUrl(encoding, urlContenido);
	    		    					}	
	    		    					
//	    	    						urlContenido = TransformarURL.transformaUrl(response.getCharacterEncoding(), urlContenido);
	    	    						odeSesion.setUrlContenido(urlContenido);
	    	    						odeSesion.setIdSeleccionado(it.getIdItem());
	    		        				if( logger.isDebugEnabled() )	    	
	    		        		            logger.debug("VISUALIZADOR, URLCONTENIDO:"+urlContenido);
	    		           			}
	            				}
	    	    			}
	    	    		}
	    	    	}
            	}
            	
        	} else{
//				Vamos a controlar que si el contenido es una url, estará bien y no le vamos a quitar los caracteres ?, &...
        		String urlContenido = form.getUrlContenido()!=null?form.getUrlContenido():odeSesion.getUrlContenido();
				if (!((urlContenido.toLowerCase().indexOf(PROTOCOLO_HTTP) >=0) || (urlContenido.toLowerCase().indexOf(PROTOCOLO_HTTPS) >=0))) {
					String encoding = response.getCharacterEncoding();
					urlContenido = TransformarURL.transformaUrl(encoding, urlContenido);
				}
        		odeSesion.setUrlContenido(urlContenido);
        		odeSesion.setIdSeleccionado(form.getIdSeleccionado());
        		
        	}

            if (odeSesion.getUrlContenido()!=null)
            	form.setUrlContenido(odeSesion.getUrlContenido());
            else form.setUrlContenido("");
            
            if (odeSesion.getIdSeleccionado()!=null)
            	form.setIdSeleccionado(odeSesion.getIdSeleccionado());
            else form.setIdSeleccionado("");

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////	CARGA DE RETORNO Y LAYOUT								////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 
            odeSesion.setRetorno("SinSecuencia");
            
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////				CARGA DESTACADO		////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            
            if(odeSesion.isVerFavorito())
            {
    			boolean esFavorito= false;
    			try{
    				esFavorito = this.getSrvPerfilPublico().existeFavoritoEnUsuario(identificador, sesion.getUsuario());
    			}catch (Exception e) {
    				logger.error("error al acceder al servicio PerfilPublico ", e);
				}
    			odeSesion.setFavorito(esFavorito);
            }
    		
    		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
//			valoración
/////////////////////////////////////////////////////////////////////////////////////////////////////////////			
			
			
			if(odeSesion.isVerValorar())
			{
				String usuario="";
				if(sesion.isIdentidadFederada())
				{
		    		URL urlServer= new URL(sesion.getNodoOrigen());
					usuario = sesion.getUsuarioOrigen() + "@" + urlServer.getHost();
				}else
				{
					usuario= sesion.getUsuario();
				}
				
				try{
					odeSesion.setValorado(this.getSrvValoracionService().tieneValoracion(usuario, identificador, odeSesion.getIdiomaCat()));
					odeSesion.setValoracion("" + this.getSrvValoracionService().consultarValoracion(identificador));
				}catch (Exception e) {
					logger.error("error al acceder al servicio de valoración " , e);
					odeSesion.setValoracion("0.0");
					odeSesion.setValorado(true);
				}
			}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    		
//////////////				RUTA SELECCIONADA
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    	

	    	if(orgs !=null){
	        	boolean encontrada = false;
	        	ArrayList<String> rutaSeleccionado= null;
	        	for(int i = 0; i < orgs.length && !encontrada; i++){
		        	OrganizacionVO organizacion = orgs[i];
	        		rutaSeleccionado = new ArrayList<String>();
	        		rutaSeleccionado.add(organizacion.getIdOrg());
		        	if(!organizacion.getIdOrg().equals(odeSesion.getIdSeleccionado()))
		        	{
			        	if(organizacion != null && organizacion.getItems()!=null && organizacion.getItems().length>0){
			        		encontrada = this.rutaIdSeleccionado(odeSesion.getIdSeleccionado(), orgs[i].getItems(), rutaSeleccionado);
			        	}
		        	}else{
		        		encontrada=true;
		        	}
				}
		        if (encontrada)
		        	odeSesion.setRutaSeleccionado(rutaSeleccionado);
		        else
		        	odeSesion.setRutaSeleccionado(new ArrayList<String>());
	        }
	
	    	if (odeSesion.getTituloOde()!=null)
	    		form.setTituloOde(odeSesion.getTituloOde());
	    	else form.setTituloOde("");
	    	
	    	if (odeSesion.getIdiomaCat()!=null)
	    		form.setIdiomaCat(odeSesion.getIdiomaCat());
	    	else form.setIdiomaCat("");
	    	
	    	if (odeSesion.getRetorno()!=null)
	    		form.setRetorno(odeSesion.getRetorno());
	    	else form.setRetorno("");
	    	
	    	if (odeSesion.getValoracion()!=null)
	    		form.setValoracion(odeSesion.getValoracion());
	    	else form.setValoracion("");

    		form.setRutaSeleccionado(odeSesion.getRutaSeleccionado());
	    	form.setEstaValorado(odeSesion.isValorado());
	    	form.setSecuencia(odeSesion.isSecuencia());
	    	// 29012013 El menú se mostrará siempre desplegado	    	
	    	form.setMenuDesplegado(true);
            
//	        logger.debug("VISUALIZADOR cargarDatos localizacionURL " + odeSesion.getLocalizadorCont());
    	}
    	catch (Exception one)
    	{
			logger.error("Error en Servicio de visualizador de contenidos, metodo cargarDatos ", one);
			throw new OdeNoEncontradoException("Ode no Encontrado");
    	}
    }


	// funcion que recorre el arbol en preorden y guarda los items que tienen contenido
	private void contenidosArbolPre (ItemVO[] items, ArrayList lista) {
		if ((items!=null) && items.length>0) {
			for (int j=0; j<items.length;j++) {
				if ((items[j].getRecurso()!=null) && (items[j].getRecurso().getHrefRec()!=null)) {
					lista.add(items[j]);
				}else if ((items[j].getItemHijos()!=null) && (items[j].getItemHijos().length>0)) {
					contenidosArbolPre(items[j].getItemHijos(), lista);
				}
			}
		}
	}
	////////////////////////////////////////////////////////////////

	private boolean rutaIdSeleccionado(
			String idSelec, 
			ItemVO[] items, 
			ArrayList<String> 
			rutaActual)
	{
		boolean enc = false;
		for(int i=0; i < items.length && !enc ; i++){
			ItemVO item = items[i];
			rutaActual.add(item.getIdItem());
			if(idSelec.equals(item.getIdItem())){
				enc = true;
			}
			else if (item!=null && item.getItemHijos()!=null && item.getItemHijos().length >0){
				enc = rutaIdSeleccionado(idSelec, item.getItemHijos(), rutaActual);
				if(!enc){
					rutaActual.remove(rutaActual.size() -1);
				}
			}else{
				if(!enc){
					rutaActual.remove(rutaActual.size() -1);
				}
			}
		}
		return enc;
	}




}