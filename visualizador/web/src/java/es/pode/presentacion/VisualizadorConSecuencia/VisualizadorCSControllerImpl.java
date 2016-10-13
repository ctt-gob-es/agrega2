/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.presentacion.VisualizadorConSecuencia;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.agrega.soporte.agregaProperties.DecisorOffline;
import es.agrega.soporte.tag.url.TransformarURL;
import es.pode.entregar.negocio.servicios.ItemVO;
import es.pode.entregar.negocio.servicios.OrganizacionVO;
import es.pode.entregar.negocio.servicios.SecuenciaVO;
import es.pode.presentacion.OdeSession;
import es.pode.presentacion.VisualizadorSession;
import es.pode.presentacion.utils.OdeSessionUtils;
import es.pode.publicacion.negocio.servicios.ContenidoInapropiadoVO;



/**
 * @see es.pode.presentacion.VisualizadorConSecuencia.VisualizadorCSController
 */
public class VisualizadorCSControllerImpl extends VisualizadorCSController
{
	protected static Logger logger = Logger.getLogger(VisualizadorCSControllerImpl.class);
	
//	private HashMap<String, ItemVO[]> hOrganizaciones = null;
	private static final String PROTOCOLO_HTTP="http://";
	private static final String PROTOCOLO_HTTPS="https://";
	private static final String HOST="host";
	private static final String SUBDOMINIO="subdominio";

	
	
	
	private ItemVO[] recorreArbolPreorden(OrganizacionVO organizacionVO){
		
		ArrayList<ItemVO> itemPreorden=new ArrayList<ItemVO>();
		
		if (organizacionVO !=null){
			ItemVO[] itemVO=organizacionVO.getItems();
			if(itemVO!=null){
				 for( int j=0;j<itemVO.length;j++){
					 consigueItems(itemVO[j], itemPreorden);
							 
				 }
			}
		}
		return itemPreorden.toArray(new ItemVO[itemPreorden.size()]);
	}

	private ArrayList<ItemVO> consigueItems(ItemVO itemVO, ArrayList<ItemVO> item){

		ItemVO[] itemVO1=itemVO.getItemHijos();
		if(itemVO1!=null){
			for(int k=0;k<itemVO1.length;k++){
			consigueItems(itemVO1[k],  item);

			}
			item.add(itemVO);
			
		}else{
			item.add(itemVO);
		}
		return item;
	}

	private ArrayList<Integer> getIndexPrimerHijo(ItemVO[] items , int posPadre, ArrayList<Integer> l) {
		int index=-1;
		boolean encontrado=false;
		int cont=0;
		
		while ((cont<items.length) && (!encontrado)) {
			if (items[cont].getIdPadre().equals(items[posPadre].getIdItem())) {
				encontrado=true;
				l.add(cont);
				getIndexPrimerHijo(items, cont, l);
				
			} else {
				cont++;
			}
		}
		return l;
	}

	
	private ItemVO[] interpretaChoice (ItemVO[] items) {
		int indicePrimerHijo=-1;
		for (int i=0; i<items.length; i++) {
			if ((items[i].getSecuenciaItem()!=null) && (items[i].getSecuenciaItem().getControlMode()!=null)) {
				if (items[i].getSecuenciaItem().getControlMode().getChoice()) {
					for (int ji=0; ji<items.length; ji++) {
						if (items[ji].getIdPadre().equals(items[i].getIdItem())) {
							//clicables
							items[ji].setClicable(true);
						}
					}
				} else  {
					//sus descendientes no visibles
					ArrayList<Integer> l = new ArrayList<Integer>();
					l=getIndexPrimerHijo(items,i, l);
					if (!l.isEmpty()) {
						indicePrimerHijo = Integer.parseInt(l.get(l.size() -1).toString());
						if (indicePrimerHijo>-1) 
							for (int ind=indicePrimerHijo; ind<i; ind++) {
								items[ind].setClicable(false);
							}	
					}
					
				}
			} else { //ponemos a sus hijos lo que el tenga (clicable/no clicable)
				for (int ji=0; ji<items.length; ji++) {
					if (items[ji].getIdPadre().equals(items[i].getIdItem())) {
						items[ji].setClicable(items[i].getClicable());
					}
				}
			}
		}
		return items;
	}
	

	// funcion que recorre el arbol en preorden y guarda los items que tienen contenido
	private void contenidosArbolPre (ItemVO[] items, ArrayList<ItemVO> lista) {
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


    public final void cargarDatos(
    		ActionMapping mapping, 
    		CargarDatosForm form, 
    		HttpServletRequest request, 
    		HttpServletResponse response) 
    throws Exception
    {
		try{			
    		//PARAMETROS
	    	VisualizadorSession sesion=this.getVisualizadorSession(request);
	    	if (sesion==null) {
	    		logger.error("sesion es null asi que no se devolveran datos.");
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
	            return;}

	    	String identificador=form.getIdentificador() != null ? form.getIdentificador() : sesion.getIdentificador();
	    	form.setIdentificador(identificador);
	    	form.setUsuario(sesion.getUsuario());
	    	
	    	OdeSession odeSesion = OdeSessionUtils.getOdeSession(sesion, identificador);
	    	if (odeSesion==null) {
	    		logger.error("odeSesion es null asi que no se devolveran datos.");
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
	            return;}
	    	
	    	//todo lo demás debería tomarse del hashmap
//	    	form.setDatosSalida(sesion.getOrganizaciones());
//			form.setLocalizacion(sesion.getLocalizadorCont());
//	    	form.setIdSeleccionado(sesion.getIdSeleccionado());
//	    	form.setRutaSeleccionado(sesion.getRutaSeleccionado());
//	    	form.setUsuario(sesion.getUsuario());
	    	
	    	form.setDatosSalida(odeSesion.getOrganizaciones());
			form.setLocalizacion(odeSesion.getLocalizadorCont());
	    	form.setIdSeleccionado(odeSesion.getIdSeleccionado());
	    	form.setRutaSeleccionado(odeSesion.getRutaSeleccionado());
	    	form.setTituloOde(odeSesion.getTituloOde());

	    	HashMap<String, ItemVO[]> hOrganizaciones = odeSesion.gethOrganizaciones();
	    	
			OrganizacionVO[] orgs=(OrganizacionVO[]) odeSesion.getOrganizaciones().toArray(new OrganizacionVO[0]);
			if(odeSesion.getIdSeleccionado()==null || odeSesion.getIdSeleccionado().equals(""))
			{
		    	// --- tratamiento para la secuencia ---
		    	hOrganizaciones=new HashMap<String, ItemVO[]>();
		    		for (int se=0; se <orgs.length; se++ ) {
		    			
		    			ItemVO[] items= recorreArbolPreorden(orgs[se]);
		    			//metodo para sequencia
		    			ItemVO[] itemsChoice = interpretaChoice (items);
		    			
		    			if ((orgs[se].getSecuenciaOrg()!= null) && (orgs[se].getSecuenciaOrg().getControlMode()!= null)){
		    				if (!orgs[se].getSecuenciaOrg().getControlMode().getChoice()){
		    					for (int i= 0; i<itemsChoice.length; i++){
		    						itemsChoice[i].setClicable(false);
		    					}	
		    				} else{
		    					for (int i= 0; i<itemsChoice.length; i++){
		    						if (itemsChoice[i].getIdPadre().equals(orgs[se].getIdOrg())){
		    							itemsChoice[i].setClicable(true);
		    						}
		    					}
		    				}
		    				//si tiene secuencia Flow y Forward Only para poner las flechas Solo para la primera!!
		    				if (se==0) {
				    				if (orgs[se].getSecuenciaOrg().getControlMode().getFlow()) {
				    					odeSesion.setBtnDerecho(true);
				    					if (orgs[se].getSecuenciaOrg().getControlMode().getForwardOnly()) {
				    						odeSesion.setBtnIzquierdo(false);
				    					} else {
				    						odeSesion.setBtnIzquierdo(true);
				    					}
				    					//En caso de entrar aqui tenemos que coger sus hijos
				    					ArrayList<ItemVO> listaHijos = new ArrayList<ItemVO>();
										for (int ih=0; ih<items.length;ih++) {
											if (orgs[se].getIdOrg().equals(items[ih].getIdPadre())) {
												listaHijos.add(items[ih]);
											}
										}
										ItemVO[] itemHijos = listaHijos.toArray(new ItemVO[listaHijos.size()]);
										//metemos itemHIjos en session
										odeSesion.setItemsFlow(listaHijos);
										odeSesion.setIdSeleccionado(orgs[se].getIdOrg());
										
				    				} else {
				    					odeSesion.setBtnDerecho(false);
				    					odeSesion.setBtnIzquierdo(false);
				    				}
		    				}//fin flechas primera Org
		    			}
		    			
		    			hOrganizaciones.put(orgs[se].getIdOrg(), itemsChoice);
		    	    	odeSesion.sethOrganizaciones(hOrganizaciones);
		    		}
				}
			
		        form.setDatosSalidaAsArray(orgs);
		        
		        // RELLENAMOS LOS DATOS DE LA SESION
		        if(odeSesion.getDatosSalida()==null){
		        	odeSesion.setDatosSalida(form.getDatosSalida());
		        	
		            
		        	//Insertamos la página en blanco
		        	odeSesion.setUrlContenido( "" );
		        	//Insertamos URL del primer item de la primera organizacion, si sólo tiene una organizacion y un solo item
		        	if(odeSesion.getDatosSalida()!=null && odeSesion.getDatosSalida().size()>0){
		        		OrganizacionVO[] organizacionLista=(OrganizacionVO[])odeSesion.getDatosSalida().toArray(new OrganizacionVO[odeSesion.getDatosSalida().size()]);
		        		OrganizacionVO organizacion=organizacionLista[0];

		        		if(organizacion.getItems()!=null && organizacion.getItems().length>0){
				    		//cargamos el primer item visible
		        			
		        			ArrayList<ItemVO> lista = new ArrayList<ItemVO>();
		        			String refRecurso="";
		        			contenidosArbolPre (organizacion.getItems(), lista);
		        			if ((lista!=null) && (lista.size()>0)) {
		        				ItemVO it = new ItemVO();
		        				boolean enc=false;
		        				for(int i=0; i < lista.size()&& !enc;i++){	
			        				it=lista.get(i);
			        				if ((it!=null) && (it.getRecurso()!=null) && (it.getVisible()!=null)) { // || it.getVisible() cargamos el primer item
			        					enc = true;
			        					refRecurso=it.getRecurso().getHrefRec();
			        					String urlContenido = odeSesion.getLocalizadorCont() + "/" + refRecurso;
				    					int co = -1;
				    					if( refRecurso.toLowerCase().indexOf(PROTOCOLO_HTTP) >=0)
				    						co = refRecurso.toLowerCase().indexOf(PROTOCOLO_HTTP);
				    					else if( refRecurso.toLowerCase().indexOf(PROTOCOLO_HTTPS) >=0)
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
				    					
				    					if (!((urlContenido.toLowerCase().indexOf(PROTOCOLO_HTTP) >=0) || (urlContenido.toLowerCase().indexOf(PROTOCOLO_HTTPS) >=0))) {
				    						String encoding = response.getCharacterEncoding();
				    						urlContenido = TransformarURL.transformaUrl(encoding, urlContenido);
				    					}				    				
//			    						urlContenido = TransformarURL.transformaUrl(response.getCharacterEncoding(), urlContenido);

			    						odeSesion.setUrlContenido(urlContenido);
			    						odeSesion.setIdSeleccionado(it.getIdItem());
			    						odeSesion.setContador(odeSesion.getContador() + 1);
	
				        				if( logger.isDebugEnabled() ){
	
				        		            logger.debug("VISUALIZADOR, URLCONTENIDO:"+urlContenido);
				        				}
			        				}
		        				}
		        			}
			        		if(organizacion.getItems().length<=1){
			        			odeSesion.setBtnDerecho(false);
			        			odeSesion.setBtnIzquierdo(false);
			        		}
		        			
		        		}
		        	}

		        }
		        form.setUrlContenido(odeSesion.getUrlContenido());
		        form.setIdSeleccionado(odeSesion.getIdSeleccionado());
		        
		        //Fin Datos de Sesion
		        if( logger.isDebugEnabled() ){
					logger.debug("VISUALIZADOR, CARGARDATOS_NAV, USUARIO AUTENTICADO " + sesion.getUsuario());
					logger.debug("VISUALIZADOR cargarDatos_NAV localizacionURL " + odeSesion.getLocalizadorCont());
				}
		        
		        
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////	CARGA DE RETORNO Y LAYOUT								////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		       
		        odeSesion.setRetorno("ConSecuencia");
		        
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////CARGA DESTACADO		////////////////////////////
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
//				valoración
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
//////////////	RUTA SELECCIONADA
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
		        form.setRutaSeleccionado(odeSesion.getRutaSeleccionado());
		        
   
		        //Sets en form
		    	form.setContador(odeSesion.getContador());
		    	form.setBtnIzquierdo(odeSesion.getBtnIzquierdo());
		    	form.setBtnDerecho(odeSesion.getBtnDerecho());
		    	form.setItemsFlow(odeSesion.getItemsFlow());
		    	// 29012013 El menú se mostrará siempre desplegado		    	
		    	form.setMenuDesplegado(true);
		    	form.setSecuencia(odeSesion.isSecuencia());
		    	form.setIdiomaCat(odeSesion.getIdiomaCat());
		    	form.setRetorno(odeSesion.getRetorno());
	            form.setEstaValorado(odeSesion.isValorado());
	            form.setValoracion(odeSesion.getValoracion());
		    	
//		    	logger.debug("Ya cargados datos, odeSesion vale "+odeSesion);
//		    	logger.debug("Ya cargados datos, form vale "+form);
	    }
	    catch (Exception ex)
	    {
				logger.error("Error en Servicio de visualizador de contenidos, metodo cargarDatosNav ", ex);
	    }
		
	}

	public final void nodoOnClick(
    		ActionMapping mapping, 
    		NodoOnClickForm form, 
    		HttpServletRequest request, 
    		HttpServletResponse response) 
    throws Exception
    {

		try{
			OrganizacionVO[] organizaciones= null;
			
			List<Object> lista=null;
			ItemVO[] items=null;
			int posItem=-1;
//			Recogemos el nodo que hemos pulsado
			String idNodo=form.getIdSeleccionado();
			VisualizadorSession sesion = this.getVisualizadorSession(request);		
	    	String identificador = form.getIdentificador();
	    	OdeSession odeSesion = OdeSessionUtils.getOdeSession(sesion, identificador);
	    	
	    	HashMap<String, ItemVO[]> hOrganizaciones = odeSesion.gethOrganizaciones();
	    	if(hOrganizaciones==null) {
	    		hOrganizaciones= new HashMap<String, ItemVO[]>();
	    	}

//			url de los contenidos en principio en blanco
			odeSesion.setUrlContenido( "" );
			if(form.getDatosSalida()!=null)
				lista= Arrays.asList(form.getDatosSalida().toArray());
			if(lista==null){
				lista=Arrays.asList(odeSesion.getDatosSalida().toArray());
			}
			
//			odeSesion.setMenuDesplegado(form.isMenuDesplegado());
			
			//inicializamos los botones
			//No Botones
			odeSesion.setBtnIzquierdo(false);
			odeSesion.setBtnDerecho(false);
			odeSesion.setContador(-1); // lo inicializamos a -1
			odeSesion.setIdSeleccionado(idNodo); //nodo seleccionado
			
			boolean encontradoNodo=false;
			int j=0;
			organizaciones=lista.toArray(new OrganizacionVO[lista.size()]);
			while ((j<organizaciones.length) && (!encontradoNodo)){
				if(idNodo.equals(organizaciones[j].getIdOrg())) {
					encontradoNodo=true;
					items = hOrganizaciones.get(organizaciones[j].getIdOrg());

					if ((organizaciones[j].getSecuenciaOrg()!=null) && 
						(organizaciones[j].getSecuenciaOrg().getControlMode()!=null)) {
							//Almacenamos su forwardOnly en sesion
							//visualizadorSession.setSecPadreForwardOnly(secPadreForwardOnly);
							items = hOrganizaciones.get(organizaciones[j].getIdOrg());
							
							//aplicamos secuencia choice a los hijos de primer nivel para reiniciar secuencia
							if (!organizaciones[j].getSecuenciaOrg().getControlMode().getChoice()){
			    				for (int i= 0; i<items.length; i++){
			    					items[i].setClicable(false);
			    				}	
			    			} else{
			    				for (int i= 0; i<items.length; i++){
			    					if (items[i].getIdPadre().equals(organizaciones[j].getIdOrg())){
			    						items[i].setClicable(true);
			    					}
			    				}
			    			}
//							Aplicamos secuencia choice a los hijos
							items=interpretaChoice(items);	

							//Secuencia Flow y Forward Only
							if (organizaciones[j].getSecuenciaOrg().getControlMode().getFlow()) {
								//Tenemos Botones Siempre Derecho, Si Forward Only es true tb boton Iquierdo
								ItemVO[] itemHijos = null;
								ArrayList<ItemVO> listaHijos = new ArrayList<ItemVO>();
								for (int ih=0; ih<items.length;ih++) {
									if (organizaciones[j].getIdOrg().equals(items[ih].getIdPadre())) {
										listaHijos.add(items[ih]);
									}
								}
								itemHijos = listaHijos.toArray(new ItemVO[listaHijos.size()]);
								//metemos itemHijos en session
								odeSesion.setItemsFlow(listaHijos);
								
								if (!organizaciones[j].getSecuenciaOrg().getControlMode().getForwardOnly()) {
									//Poner Btn Iquierdo en Sesion
									odeSesion.setBtnDerecho(true);
									odeSesion.setBtnIzquierdo(true);
								} else {
									odeSesion.setBtnDerecho(true);
								}
								
								
							}  else {
								//No Botones
								odeSesion.setBtnIzquierdo(false);
								odeSesion.setBtnDerecho(false);
								
							}
							//Ponemos los items modificados en la hasth!! 
							hOrganizaciones.put(organizaciones[j].getIdOrg(), items);
							odeSesion.sethOrganizaciones(hOrganizaciones);
					} else {
//						No Botones
						odeSesion.setBtnIzquierdo(false);
						odeSesion.setBtnDerecho(false);
					}
				}
				j++;

			}
//			logger.debug("VISUALIZADOR, nodoOnclick nodo pulsado idNodo: " + idNodo + " localizacion cont " + odeSesion.getLocalizadorCont());
			//Buscamos si el nodo pulsado es un Item
			if (!encontradoNodo) {
				int io=0;
				while ((io<organizaciones.length) && (!encontradoNodo)) {
					String idOrg=organizaciones[io].getIdOrg();
					if (!hOrganizaciones.isEmpty()) {
						items= hOrganizaciones.get(idOrg);
						int k=0;
						while ((k<items.length) && (!encontradoNodo)){
							if(idNodo.equals(items[k].getIdItem())) {
								encontradoNodo=true;
								posItem=k;
							}
							k++;
						}
					}
					io++;
				}
			}
			if ((encontradoNodo) && (items!=null) && (items.length>0) ) {
				if ((posItem >-1) && (items[posItem].getSecuenciaItem()!=null) 
					&& (items[posItem].getSecuenciaItem().getControlMode()!=null)) {
					//secuencia choice
					items=interpretaChoice(items);
					if (!items[posItem].getSecuenciaItem().getControlMode().getChoiceExit()) {
						// Choice Exit: ponemos los hermanos a false y todos los descendientes de los hermanos 
						// menos el mismo
						int indicePrimerHijo=-1; 
						for (int h=0; h<items.length; h++){
							if (items[h].getIdPadre().equals(items[posItem].getIdPadre()))  {
								if ( !(items[h].getIdItem().equals(items[posItem].getIdItem()))) {
									items[h].setClicable(false);
									ArrayList<Integer> l = new ArrayList<Integer>();
									l=getIndexPrimerHijo(items,h, l);
									if (!l.isEmpty()) {
										indicePrimerHijo = l.get(l.size() -1);
										if (indicePrimerHijo >-1)
											for (int ind=indicePrimerHijo; ind<h; ind++) {
												items[ind].setClicable(false);
											}
									}
								}
								
							}
						}
					}//Fin Choice Exit
					
					//Secuencia Flow y Forward Only
					if (odeSesion.getItemsFlow()==null) {
						Collection<ItemVO> miItem = new ArrayList<ItemVO>();
						odeSesion.setItemsFlow(miItem);
					}
					ItemVO[] itemsFlows = (ItemVO[])odeSesion.getItemsFlow().toArray(new ItemVO[odeSesion.getItemsFlow().size()]);
					if (items[posItem].getSecuenciaItem().getControlMode().getFlow()) {
						//Tenemos Botones Siempre Derecho, Si Forward Only es false tb boton Iquierdo
						
						//si no ha sido encontrado, miro quien es el padre y meto en el vector a los hermanos del
						//pulsado y los hijos
						ArrayList<ItemVO> rutaSel = new ArrayList<ItemVO>();
						//boolean rutaEnc = this.rutaIdSeleccionado(items[posItem].getIdItem(), items, rutaSel);
						//el mismo no esta en el vector de itemsFlow así que lo metemos el primero
						boolean estaitem=false;
						ItemVO unPadre=null;
						if ((itemsFlows.length>0) && (!(estaItemDVector(items[posItem].getIdItem(), itemsFlows)))) {
							rutaSel.add(items[posItem]);
							// buscamos a su padre y vemos si está en el vector
							//tenemos que seguir metiendo padres hasta que encontremos un padre que si esté en itemsFlows
							unPadre=buscaPadreItem (items[posItem].getIdPadre(), items);
							if ((unPadre!=null) && (!unPadre.equals(""))) {
								estaitem=estaItemDVector(unPadre.getIdItem(), itemsFlows);
								while ((unPadre!=null) && (!estaitem)) {							
									rutaSel.add(unPadre);
									unPadre=buscaPadreItem(unPadre.getIdPadre(), items);
									estaitem=estaItemDVector(unPadre.getIdItem(), itemsFlows);
								}
							}
						}else{
							//el item esta en itemsFlows o itemsFlows esta vacio pero inicializado
							estaitem=true;
							unPadre=items[posItem];//lo ponemos a el mismo
							if (itemsFlows.length==0){//para que no haya error en merge, metemos ya el padre en itemsFlows q tiene q estar el primero
								ArrayList<ItemVO> elMismo = new ArrayList<ItemVO>();
								elMismo.add(items[posItem]);
								itemsFlows= elMismo.toArray(new ItemVO[elMismo.size()]);
							}
						}
						//
						if (estaitem) { //hemos encontrado un padre que esta en itemsFlows
							//ahora miramos si tienen hijos y si están metidos, si no lo estan, los metemos
							//en unPadre se queda el padre encontrado!!!!!! buscamos su posicion en itemsFlows
							int posiPadreSi=damePosItem(unPadre.getIdItem(),itemsFlows);
							ItemVO[] nVectorItems=null;
							//si el padre ademas de hijos tiene flow a true
							if ((unPadre.getSecuenciaItem()!=null) && (unPadre.getSecuenciaItem().getControlMode()!=null) 
								&& (unPadre.getSecuenciaItem().getControlMode().getFlow()) && (unPadre.getItemHijos()!=null) && (unPadre.getItemHijos().length>0)) {
								if (!(estaItemDVector(unPadre.getItemHijos()[0].getIdItem(), itemsFlows))) {
									ArrayList<ItemVO> losHijos = new ArrayList<ItemVO>();
									losHijos=dameHIjos(unPadre.getIdItem(), items);
									nVectorItems=mergeElementos(itemsFlows, losHijos, posiPadreSi);
									itemsFlows=nVectorItems;
								}
								
							}
							//ahora los elementos que estan en el arraylist
							//le damos la vuelta
							Collections.reverse(rutaSel);
							for (int ia=0; ia<rutaSel.size(); ia++) {
								unPadre=rutaSel.get(ia);
								if ((!(estaItemDVector(unPadre.getIdItem(), itemsFlows))) && (estaItemDVector(unPadre.getIdPadre(), itemsFlows))){
									posiPadreSi=damePosItem(unPadre.getIdPadre(),itemsFlows);
									ArrayList<ItemVO> elMismo=new ArrayList<ItemVO>();
									elMismo.add(unPadre);
									nVectorItems=mergeElementos(itemsFlows,elMismo, posiPadreSi);
								}
								if ((unPadre.getSecuenciaItem()!=null) && (unPadre.getSecuenciaItem().getControlMode()!=null) 
									&& (unPadre.getSecuenciaItem().getControlMode().getFlow()) && (unPadre.getItemHijos()!=null) && (unPadre.getItemHijos().length>0)) {
									if (!(estaItemDVector(unPadre.getItemHijos()[0].getIdItem(), itemsFlows))) {
										//busco y meto los hijos
										ArrayList<ItemVO> losHijos = new ArrayList<ItemVO>();
										losHijos=dameHIjos(unPadre.getIdItem(), items);
										posiPadreSi=damePosItem(unPadre.getIdItem(),itemsFlows);
										itemsFlows=mergeElementos(nVectorItems,losHijos, posiPadreSi);
									}
								}
										//a lo mejor hay que actualizar aqi itemsFlows
							}//end for
						}						
															    
						
					  //itemHijos = (ItemVO[]) listaHijos.toArray(new ItemVO[listaHijos.size()]);
						 //itemHijos = (ItemVO[]) losItemsMez.toArray(new ItemVO[losItemsMez.size()]);
						//metemos itemHIjos en session
						//visualizadorSession.setItemsFlow(listaHijos);
						odeSesion.setItemsFlow(Arrays.asList(itemsFlows));
						 //ojo
						 int idv= damePosItem(items[posItem].getIdItem(), itemsFlows);
						 //TODO ¿Y si no lo encuentra?
						 odeSesion.setContador(idv); // lo modificamos
						 odeSesion.setIdSeleccionado(items[posItem].getIdItem());
						if (!items[posItem].getSecuenciaItem().getControlMode().getForwardOnly()) {
							//Poner Btn Iquierdo en Sesion
							odeSesion.setBtnDerecho(true);
							odeSesion.setBtnIzquierdo(true);
						} else {
							//solo Boton Derecho
							odeSesion.setBtnDerecho(true);
							odeSesion.setBtnIzquierdo(false);
						}
						
						
					}  else {
						//No Botones
						odeSesion.setBtnIzquierdo(false);
						odeSesion.setBtnDerecho(false);
						
					}
					
					
				}
				if ((posItem < items.length) && (posItem > -1)){
					String idPadre=items[posItem].getIdPadre();
					int posPadre=-1;
					int or=0;
					boolean encontradoPadre= false;
					SecuenciaVO seqPadre = null;
					while ((or<items.length) && (!encontradoPadre)) {
						if (items[or].getIdItem().equals(idPadre)) {
							encontradoPadre=true;
							seqPadre= items[or].getSecuenciaItem();
							posPadre=or;
						}
						or++;
					}
					if (!encontradoPadre) {
						int ir=0;
						while ((ir<organizaciones.length) && (!encontradoPadre)) {
							if (organizaciones[ir].getIdOrg().equals(idPadre)) {
								encontradoPadre=true;
								seqPadre= organizaciones[ir].getSecuenciaOrg();
								posPadre=ir;
							}
							ir++;
						}
					}
					boolean orgEncontradoAb=false;
					
					if ((encontradoPadre) && (seqPadre!=null)) {
						int indicePrimerHijo=-1;
						if ((seqPadre.getControlMode()!=null) 
							&& (seqPadre.getControlMode().getForwardOnly())) {
							for (int it=0; it<posItem;it++) {
								if (items[it].getIdPadre().equals(idPadre)) {
									items[it].setClicable(false);
									// creo que este estaba fuera pq si un item tiene hijos ylo pulsamos el es visible
									//pero los hijos no!! ejem Actividads 1 !!!!
									ArrayList<Integer> l = new ArrayList<Integer>();
									l=getIndexPrimerHijo(items,it, l);
									if (!l.isEmpty()) {
										indicePrimerHijo = l.get(l.size() -1);
										if (indicePrimerHijo>-1)
										for (int ind=indicePrimerHijo; ind<it; ind++) {
											items[ind].setClicable(false);
										}
									} 
									
								}
							} //for
						}
					}
					
				}
				if ((posItem < items.length) && (posItem > -1)){
					//Temina secuencia, volvemos a actualizar el hashmap con la secuencia correcta
					hOrganizaciones.put(items[posItem].getIdOrg(), items);
					odeSesion.sethOrganizaciones(hOrganizaciones);
				}
			}

			//Comprobamos que no sea una Organizacion (no tiene contenido)
			if ((posItem < items.length) && (posItem > -1)){
	//			Tb debemos mostrar el contenido del item que estamos clicando
				if ((items[posItem].getClicable()) 
						&& (items[posItem].getRecurso()!=null) && 
						(items[posItem].getRecurso().getHrefRec()!=null)) {
					//Comprobamos q no sea una url sino la tratamos y no le concatenamos el localizador
					String contenido = items[posItem].getRecurso().getHrefRec();
					//si el contenido es ya una url, no le concatenamos el localizador
					String urlContenido ="";
					if ((contenido.toLowerCase().indexOf(PROTOCOLO_HTTP) >=0) || (contenido.toLowerCase().indexOf(PROTOCOLO_HTTPS) >=0)) {						
						urlContenido = contenido;
					} else {
						urlContenido = odeSesion.getLocalizadorCont() + "/" + contenido;
					}
					int co = -1;
					if( urlContenido.toLowerCase().indexOf(PROTOCOLO_HTTP) >=0)
						co = urlContenido.toLowerCase().indexOf(PROTOCOLO_HTTP);
					else if( urlContenido.toLowerCase().indexOf(PROTOCOLO_HTTPS) >=0)
						co = urlContenido.toLowerCase().indexOf(PROTOCOLO_HTTPS);
						
					if (co < 0) {//pner tb en botones
						String server = "";
						if (DecisorOffline.esOffline())
							server =  request.getServerName() + ":" + request.getServerPort();
						else
							server = AgregaPropertiesImpl.getInstance().getProperty(HOST) + AgregaPropertiesImpl.getInstance().getProperty(SUBDOMINIO);
						
    					urlContenido = PROTOCOLO_HTTP + server + urlContenido;
    				
					}else{
						urlContenido =contenido.substring(co);
					}
					//Vamos a controlar que si el contenido es una url, estará bien y no le vamos a quitar los caracteres ?, &...
					if (!((contenido.toLowerCase().indexOf(PROTOCOLO_HTTP) >=0) || (contenido.toLowerCase().indexOf(PROTOCOLO_HTTPS) >=0))) {
						String encoding = response.getCharacterEncoding();
						urlContenido = TransformarURL.transformaUrl(encoding, urlContenido);
					}
//					urlContenido = TransformarURL.transformaUrl(response.getCharacterEncoding(), urlContenido);
					odeSesion.setUrlContenido(urlContenido);
					
				} else {
					odeSesion.setUrlContenido( "" );
				}
			}
			if( logger.isDebugEnabled() ){
				logger.debug("URL localizacion contenido "+ odeSesion.getLocalizadorCont());
			}
			//meto datos en el form				
			form.setDatosSalida(lista);
			form.setIdentificador(identificador);
			form.setUsuario(sesion.getUsuario());
			form.setLocalizacion(odeSesion.getLocalizadorCont());
			form.setUrlContenido(odeSesion.getUrlContenido());
            form.setIdSeleccionado(odeSesion.getIdSeleccionado());

			OrganizacionVO[] orgs= (OrganizacionVO[]) odeSesion.getOrganizaciones().toArray(new OrganizacionVO[0]);
	        
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////	CARGA DE RETORNO Y LAYOUT								////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 
			odeSesion.setRetorno("ConSecuencia");
  
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////CARGA DESTACADO		////////////////////////////
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
//////////////RUTA SELECCIONADA
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
			  form.setRutaSeleccionado(odeSesion.getRutaSeleccionado());
			  // 29012013 El menú se mostrará siempre desplegado
			  form.setMenuDesplegado(true);
			  form.setTituloOde(odeSesion.getTituloOde());
			  form.setContador(odeSesion.getContador());
			  form.setBtnDerecho(odeSesion.getBtnDerecho());
			  form.setBtnIzquierdo(odeSesion.getBtnIzquierdo());
			  form.setUrlContenido(odeSesion.getUrlContenido());
			  form.setItemsFlow(odeSesion.getItemsFlow());
			  form.setSecuencia(odeSesion.isSecuencia());
			  form.setIdiomaCat(odeSesion.getIdiomaCat());
		}
		catch (Exception ex)
    	{

			logger.error("Error en Servicio de visualizador de contenidos, metodo nodoOnClick :"+ex.getMessage(), ex);
			//logger.error(ex);
    	}

    }

    public final void nodoOnClickBoton(
    		ActionMapping mapping, 
    		NodoOnClickBotonForm form, 
    		HttpServletRequest request, 
    		HttpServletResponse response) 
    throws Exception
    {

		VisualizadorSession sesion = this.getVisualizadorSession(request);
    	String identificador = form.getIdentificador();
    	OdeSession odeSesion = OdeSessionUtils.getOdeSession(sesion, identificador);
    	// 29012013 El menú se mostrará siempre desplegado    	
    	odeSesion.setMenuDesplegado(true);
    	
    	HashMap<String, ItemVO[]> hOrganizaciones = odeSesion.gethOrganizaciones();
    	if(hOrganizaciones==null) {
    		hOrganizaciones= new HashMap<String, ItemVO[]>();
    	}
    	
		OrganizacionVO[] orgs= (OrganizacionVO[]) odeSesion.getOrganizaciones().toArray(new OrganizacionVO[0]);
		String urlContenido=null;
		ArrayList lClicables = new ArrayList();
		int contador=form.getContador();
		ItemVO[] itemsHermanos = (ItemVO[])odeSesion.getItemsFlow().toArray(new ItemVO[odeSesion.getItemsFlow().size()]);
		
		if (contador>=0) {
			if (contador <= itemsHermanos.length-1) {
				// nos quedamos con itemsHermanos[contador]
				if ((itemsHermanos[contador].getSecuenciaItem()!= null) 
					&& (itemsHermanos[contador].getSecuenciaItem().getControlMode()!=null)) {
					//Comprobamos su secuencia Choice Exit
//					if (!itemsHermanos[contador].getSecuenciaItem().getControlMode().getChoiceExit().booleanValue()) {
//						
//						for (int ci=0; ci< itemsHermanos.length; ci++) {
//							if (!(itemsHermanos[contador].getIdItem().equals(itemsHermanos[ci].getIdItem()))) {
//								//ponemos todos los demas a false
//								itemsHermanos[ci].setClicable(new Boolean(false));
//							}
//						}
//					}
					// Aplicamos el flow y forward only si lo tiene
					String idOrg=itemsHermanos[contador].getIdOrg();
					ItemVO[] items= hOrganizaciones.get(idOrg);
					if (!itemsHermanos[contador].getSecuenciaItem().getControlMode().getChoiceExit()) {
						// Choice Exit: ponemos los hermanso a false y todos los descendientes de los hermanos 
						int indicePrimerHijo=-1;
						for (int h=0; h<items.length; h++){						  
						   if (items[h].getIdPadre().equals(itemsHermanos[contador].getIdPadre())) {
							 if ( !(items[h].getIdItem().equals(itemsHermanos[contador].getIdItem()))) {//siempre qe no sea el mismo!!
								items[h].setClicable(false);
								ArrayList<Integer> l = new ArrayList<Integer>();
								l=getIndexPrimerHijo(items,h, l);
								if (!l.isEmpty()) {
									indicePrimerHijo = l.get(l.size() -1);
									if (indicePrimerHijo >-1) {
										for (int ind=indicePrimerHijo; ind<h; ind++) {
											items[ind].setClicable(false);
										}
									}
								}
							 }//fin !if..								
						  }
						}
					}//Fin Choice Exit
					
					// Vuelvo a meter el array en la Hash
					hOrganizaciones.put(idOrg, items);
					odeSesion.sethOrganizaciones(hOrganizaciones);

					//ajustamos el vector de itemsFlow, para que contenga los valores que deben llevar los botones!!
					if ((itemsHermanos[contador].getSecuenciaItem()!=null) && (itemsHermanos[contador].getSecuenciaItem().getControlMode()!=null)
						&& (itemsHermanos[contador].getSecuenciaItem().getControlMode().getFlow())) {
								ArrayList<ItemVO> mezclaItems = new ArrayList<ItemVO>();								
								//miramos los hijos directos 
								if ((itemsHermanos[contador].getItemHijos()!=null) && (itemsHermanos[contador].getItemHijos().length>0)) {
									ItemVO[] hijosDirvo=itemsHermanos[contador].getItemHijos();
									//monto el array con la mezcla explosiva!!
									for (int li=0; li<=contador;li++){
									  mezclaItems.add(itemsHermanos[li]);
									}
									// miro si el primer hijo está ya en el array si no esta lo meto
									if (!(estaItemDVector(hijosDirvo[0].getIdItem(),itemsHermanos))) {
									  //meto los hijos del elemento de contador
									  for (int hi=0;hi<hijosDirvo.length;hi++) {
										mezclaItems.add(hijosDirvo[hi]);
									  }
									}
									//meto el resto
									if (contador+1 <=itemsHermanos.length) {
									   for (int ji=contador+1; ji<itemsHermanos.length;ji++) {
										 mezclaItems.add(itemsHermanos[ji]);
									   }
									}
									itemsHermanos =mezclaItems.toArray(new ItemVO[mezclaItems.size()]);
								}
								// metemos todo en sesion, como mas abajo utilizamos itemsHeramos, lo renombramos con los datos
								//actuales para que se le apliquen los cambios
							   
								odeSesion.setItemsFlow(Arrays.asList(itemsHermanos));
							   // en este caso que tiene hijos y flow a true, ponemos los botones izq y drcho a true
							   //BOTONES ACTUALIZADOS
								odeSesion.setBtnIzquierdo(true);
								odeSesion.setBtnDerecho(true);
							} else {
								odeSesion.setItemsFlow(Arrays.asList(itemsHermanos));
							}
					//////
					//visualizadorSession.setItemsFlow(lClicables);
				}
				//Si es Forward
				List<Object> lista=null;
				if(form.getDatosSalida()!=null)
					lista= Arrays.asList(form.getDatosSalida().toArray());
				if(lista==null){
					lista=Arrays.asList(odeSesion.getDatosSalida().toArray());
				}
				OrganizacionVO[] organizaciones=lista.toArray(new OrganizacionVO[lista.size()]);
				String idPadre=itemsHermanos[contador].getIdPadre();
				//Miramos si es una organizacion el padre 
				int posiOrg=-1;
				boolean padreEsOrgYForward=false;
				//Miramos si el padre es una organizacion
				for(int i=0;i<organizaciones.length;i++){
					if(organizaciones[i].getIdOrg().equals(idPadre)){
						SecuenciaVO secuenciaPadre=organizaciones[i].getSecuenciaOrg();
						//Miramos si el padre es un forwardOnly
						if ((secuenciaPadre!=null) && (secuenciaPadre.getControlMode().getForwardOnly())){
							for(int j=0;j<contador;j++){//Ponemos todos los items anteriores a no clicables
								itemsHermanos[j].setClicable(false);
								
							}
							padreEsOrgYForward=true;
							//ponemos a false la flecha izquierda
							odeSesion.setBtnIzquierdo(false);
						}
						posiOrg=i;
					}
				}
				
					
					ItemVO[] enPreorden =null;
					int k=0;
					if(posiOrg>=0){//Si lo hemos encontrado en las organizaciones lo ponemos enPreorden
						enPreorden =recorreArbolPreorden(orgs[posiOrg]);
						
					}else{//Si no lo hemos encontrado en las organizaciones, lo buscamos en la variable global y lo ponemos enPreorden
						
						boolean orgEncontrada=false;
						while(k<orgs.length && !orgEncontrada){
							enPreorden = recorreArbolPreorden(orgs[k]);
							for(int l=0;l<enPreorden.length && !orgEncontrada;l++){
								if(enPreorden[l].getIdPadre().equals(idPadre)){
									orgEncontrada=true;									
								}else{
									k++;
								}
							}
						}
					}
					boolean encontrado=false;
					if(padreEsOrgYForward){//Si el padre es organizacion y forwardOnly buscamos en el preorden el item correspondiente
						for(int i=0;i<enPreorden.length&& !encontrado;i++){
//							enPreorden[i].setClicable(new Boolean(false));//Lo que está mal
							if(enPreorden[i].getIdItem().equals(itemsHermanos[contador].getIdItem())){
								encontrado=true;
								if(contador-1>=0){
									ItemVO itemAnterior = itemsHermanos[contador-1];
//									int n=0;
									for(int j=0;j<enPreorden.length&& !enPreorden[j].getIdItem().equals(itemAnterior.getIdItem());j++){
//										n++;
										enPreorden[j].setClicable(false);
									}
//									if(n>=0){
//										for(int l=0;l<n;l++){
//											enPreorden[l].setClicable(new Boolean(false));
//										}
//									}
								}
							}
						}
					}else{// Si el padre no es organizacion buscamos el padre en el preorden, pues será un item
					/////////////////////////////////////////////
						for(int m=0;m<enPreorden.length;m++){
							if(enPreorden[m].getIdItem().equals(idPadre)){
								SecuenciaVO secuenciaItem=enPreorden[m].getSecuenciaItem();//Miramos si al encontrarlo es un forwardOnly
								if(secuenciaItem!=null && secuenciaItem.getControlMode()!=null && secuenciaItem.getControlMode().getForwardOnly()){
									//ponemos al padre no clicable
									enPreorden[m].setClicable(false);
									//buscamos al hijo! q sera el itemsHermanos[contador]
									for(int i=0;i<enPreorden.length&& !encontrado;i++){
										if(enPreorden[i].getIdItem().equals(itemsHermanos[contador].getIdItem())){
										  enPreorden[i].setClicable(false);
										  encontrado=true;
										}
									}
									// como forward only es true se invalida la flecha izquierda
									odeSesion.setBtnIzquierdo(false);
								}
							}//end for
						}
					    /////////////////////////////////////////////
					}
					String contenido ="";
				if ((itemsHermanos[contador].getRecurso()!=null) && 
						itemsHermanos[contador].getRecurso().getHrefRec()!= null) {
					contenido = itemsHermanos[contador].getRecurso().getHrefRec();
//					si el contenido es ya una url, no le concatenamos el localizador
					if ((contenido.toLowerCase().indexOf(PROTOCOLO_HTTP) >=0) || (contenido.toLowerCase().indexOf(PROTOCOLO_HTTPS) >=0)) {
						urlContenido = contenido;
					} else {
						urlContenido = odeSesion.getLocalizadorCont() + "/" + contenido;
					}
					int co = -1;
					if( urlContenido.toLowerCase().indexOf(PROTOCOLO_HTTP) >=0)
						co = urlContenido.toLowerCase().indexOf(PROTOCOLO_HTTP);
					else if( urlContenido.toLowerCase().indexOf(PROTOCOLO_HTTPS) >=0)
						co = urlContenido.toLowerCase().indexOf(PROTOCOLO_HTTPS);
						
					
					if (co < 0) {//pner tb en botones
						String server = "";
						if (DecisorOffline.esOffline())
							server =  request.getServerName() + ":" + request.getServerPort();
						else
							server = AgregaPropertiesImpl.getInstance().getProperty(HOST) + AgregaPropertiesImpl.getInstance().getProperty(SUBDOMINIO);
						
    					urlContenido = PROTOCOLO_HTTP + server + urlContenido;
    					
					}else
					{
						urlContenido =contenido.substring(co);
					}

				} else {
					urlContenido= "";
				}
				//contenido a mostrar

//				Vamos a controlar que si el contenido es una url, estará bien y no le vamos a quitar los caracteres ?, &...
				if (!((contenido.toLowerCase().indexOf(PROTOCOLO_HTTP) >=0) || (contenido.toLowerCase().indexOf(PROTOCOLO_HTTPS) >=0))) {
					String encoding = response.getCharacterEncoding();
					urlContenido = TransformarURL.transformaUrl(encoding, urlContenido);
				}				
//				urlContenido = TransformarURL.transformaUrl(response.getCharacterEncoding(), urlContenido);
				odeSesion.setUrlContenido(urlContenido);
				odeSesion.setContador(contador); // lo modificamos
				odeSesion.setIdSeleccionado(itemsHermanos[contador].getIdItem());
				
				form.setIdSeleccionado(odeSesion.getIdSeleccionado());
				form.setUrlContenido(odeSesion.getUrlContenido());
				// 29012013 El menú se mostrará siempre desplegado				
				form.setMenuDesplegado(true);
				form.setItemsFlow(odeSesion.getItemsFlow());
				form.setContador(odeSesion.getContador());
			}
			
			
		}
		// si tiene contenido hay que mostrarlo
		
		form.setDatosSalida(odeSesion.getDatosSalida());
//		String identificador=request.getParameter("identificador");
//		if (identificador==null) {
//			identificador= sesion.getIdentificador();
//		}
		form.setIdentificador(identificador);
		form.setUsuario(sesion.getUsuario());
		form.setTituloOde(odeSesion.getTituloOde());
		form.setBtnDerecho(odeSesion.getBtnDerecho());
		form.setBtnIzquierdo(odeSesion.getBtnIzquierdo());
		// 29012013 El menú se mostrará siempre desplegado		
		form.setMenuDesplegado(true);
		

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////	CARGA DE RETORNO Y LAYOUT								////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		odeSesion.setRetorno("ConSecuencia");

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////	CARGA DESTACADO		////////////////////////////
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
//		valoración
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
//////////////RUTA SELECCIONADA
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

			form.setRutaSeleccionado(odeSesion.getRutaSeleccionado());
			form.setSecuencia(odeSesion.isSecuencia());
			form.setIdiomaCat(odeSesion.getIdiomaCat());
			
//			logger.debug("onClikBoton, antes de salida, odeSesion vale "+odeSesion);
//			logger.debug("onClikBoton, antes de salida, form vale "+form);
    }






//////////////////////////////////////
////met para buscar el padre de un nodo
//le pasamos el idItem del padre    
public ItemVO buscaPadreItem (
		String idItem, 
		ItemVO[] items) 
{
	ItemVO myItem= null;
	if ((items!=null) && (items.length>0)) {
		int indi=0;
		boolean es=false;
		while ((!es) && (indi<=items.length-1)) {
			if (idItem.equals(items[indi].getIdItem())) {
				myItem=items[indi];
				es=true;
			}
				indi++;
		}
	}
	return myItem;
}

private int damePosItem(
		String idItem,
		ItemVO[] items) 
{
//	int indi=0;
//	if ((items!=null) && (items.length>0)) {
//		boolean es=false;
//		while ((!es) && (indi<=items.length)) {
//			logger.debug("indi vale "+indi);
//			if (idItem.equals(items[indi].getIdItem())) {
//				es=true;
//			}
//			indi++;
//		}
//		if ((es) && (indi<=items.length) && (indi>0)) {
//			indi=indi-1;
//		}
//	}
//	return indi;
	
	for (int i = 0; i < items.length; i++) {
		ItemVO itemVO = items[i];
		if(itemVO.getIdItem().equals(idItem)) return i;
	}
	return 0;
}


public ArrayList<ItemVO> dameHIjos(
		String idItem,
		ItemVO[] items) 
{
	ArrayList<ItemVO> listaHijos = new ArrayList<ItemVO>();
	if ((items!=null) && (items.length>0)) {
		for (int ih=0; ih<items.length;ih++) {
			if (idItem.equals(items[ih].getIdPadre())) {
				listaHijos.add(items[ih]);
			}
		}/// fin meto hijos
	}
	return listaHijos;
}


public ItemVO[] mergeElementos(
		ItemVO[] itemsFlows, 
		ArrayList<ItemVO> losHijos, 
		int posiPadreSi) 
{
	ArrayList<ItemVO> salidaItem= new ArrayList<ItemVO>();
	for (int i=0; i<itemsFlows.length&&i<=posiPadreSi; i++) {
		salidaItem.add(itemsFlows[i]);
	}
	for (int a=0; a<losHijos.size(); a++) {
		salidaItem.add(losHijos.get(a));
	}
	if (posiPadreSi+1<= itemsFlows.length) {
		for (int r=posiPadreSi+1; r<itemsFlows.length; r++) {
			salidaItem.add(itemsFlows[r]);
		}
	}
	return salidaItem.toArray(new ItemVO[salidaItem.size()]); 
}


//metodo de prueba: vamos a borrar del array el ItemVO que tenga ese identificador
  public boolean estaItemDVector(String idItem, ItemVO[] items) {
  	boolean enc=false;
  	if ((items!=null) && (items.length>0)) {
  		int id=0;
  		while ((!enc) && ( id<items.length)) {
  			if (idItem.equals(items[id].getIdItem())) {
  				enc=true;
  			}
  			id++;
  		}
  	}
		return enc;
 }
//////////////////////////////////

//@Override
//public void cargaId(ActionMapping mapping, CargaIdForm form,
//		HttpServletRequest request, HttpServletResponse response)
//		throws Exception {
//	form.setIdentificador(form.getIdentificador());
//	
//}


}