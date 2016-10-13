/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.catalogacion.negocio.servicios.AvMetametadataVO;
import es.pode.catalogacion.negocio.servicios.ContribucionVO;
import es.pode.catalogacion.negocio.servicios.DescripcionVO;
import es.pode.catalogacion.negocio.servicios.EntidadVO;
import es.pode.catalogacion.negocio.servicios.EsquemaDeMetadatosVO;
import es.pode.catalogacion.negocio.servicios.FechaVO;
import es.pode.catalogacion.negocio.servicios.IdentificadorVO;
import es.pode.catalogacion.negocio.servicios.IdiomaVO;
import es.pode.catalogacion.negocio.servicios.LangStringVO;
import es.pode.catalogacion.negocio.servicios.LomAvanzadoVO;
import es.pode.catalogacion.negocio.servicios.SourceValueVO;
import es.pode.catalogacion.soporte.Contribucion;
import es.pode.catalogacion.soporte.Entidad;
import es.pode.catalogacion.soporte.Fecha;
import es.pode.catalogacion.soporte.UtilidadesOrdenarCombos;
import es.pode.catalogadorWeb.presentacion.CatalogadorAvSession;
import es.pode.catalogadorWeb.presentacion.MetametadatoSession;
import es.pode.fuentestaxonomicas.negocio.servicio.TerminoVO;
import es.pode.fuentestaxonomicas.negocio.servicio.TerminoYPadreVO;
import es.pode.fuentestaxonomicas.negocio.servicio.VocabularioVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.MetametadatosController
 */
@SuppressWarnings("all")
public class MetametadatosControllerImpl extends MetametadatosController
{  

	protected static Logger logger = Logger.getLogger(MetametadatosControllerImpl.class); 

//	private IdentificadorVO[] identificadores;
//	private EsquemaDeMetadatosVO[] esquemasMeta;
//  	private String idioma;
//  	
//	private DescripcionVO[] descripciones;//cada posicion del array es la descripcion que lleva la fecha de una contribucion
//	private Contribucion[] contribuciones;

	
    /**
     * @see es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.MetametadatosController#cargarDatosMetadatos(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.CargarDatosMetadatosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void cargarDatosMetadatos(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.CargarDatosMetadatosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		InputStream is = null;
    	Properties prop = new Properties();
		boolean bandera=false;
		
        try{
        	int longVocabulario = 0;
        	CatalogadorAvSession catalogadorAvSession=this.getCatalogadorAvSession(request);
        	is = this.getClass().getResourceAsStream("/catalogadorAvanzado.properties");
			prop.load(is);
			String idiomaLdap=((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
			
			//comprobamos que el objeto de sesion no se ha null, si es null nos creamos uno	
			if (catalogadorAvSession.getMDSesion() == null){
				LomAvanzadoVO mdSession = new LomAvanzadoVO();
				catalogadorAvSession.setMDSesion(mdSession);
			}	
			//comprobamos que metaMetadata no se ha null, si es null nos creamos uno
			AvMetametadataVO metadatos = catalogadorAvSession.getMDSesion().getMetaMetadata();
			boolean metaMetadataEsNull = false;
			if (catalogadorAvSession.getMDSesion().getMetaMetadata() == null){
				metadatos = new AvMetametadataVO();
				catalogadorAvSession.getMDSesion().setMetaMetadata(metadatos);
				metaMetadataEsNull=true;
			}	
		
			//Carga del formulario (ahora desde el objeto de session)(Los combos y lo que debe pintarse)
			String[] l_id = { prop.getProperty("idiomaDestinatario"),prop.getProperty("rolMetadatos")};
			VocabularioVO[] vocabulario = this.getSrvVocabulariosControladosService().obtenerCombos(l_id,idiomaLdap);
			//vamos a ordenar vocabulario
			UtilidadesOrdenarCombos vocabulariosOrdDest1 = new UtilidadesOrdenarCombos();
			VocabularioVO[] vocabularioOrdenado = vocabulariosOrdDest1.ordenarVocabulariosVO(vocabulario);			

			longVocabulario = vocabularioOrdenado.length;//Cargamos los combos de idioma, la estructura y el nivel de agregacion
			TerminoYPadreVO terminoypadreVO = new TerminoYPadreVO();
			ArrayList terminoypadrear=new ArrayList();

			Collection idDest = new ArrayList();
			
			for (int x = 0; x < longVocabulario; x++) {
				TerminoVO terminoVO = new TerminoVO();
				terminoVO.setIdiomaTermino("");
				terminoVO.setIdTermino("");
				terminoVO.setNomTermino("");

				switch (x) {
					case 0://IDIOMADEST
						idDest.add(terminoVO);
	//					modificamos las cadenas de idiomas
						TerminoVO[] terminosDest = vocabularioOrdenado[x].getTerminos();
						for (int li=0; li<terminosDest.length;li++){
							TerminoVO idAux = new TerminoVO();
							idAux=terminosDest[li];
							String textoIdioma= idAux.getNomTermino();
							String idiomasTra=I18n.getInstance().obtenerIdiomaTraducido(textoIdioma, idiomaLdap);
							idAux.setNomTermino(idiomasTra);
						}
						UtilidadesOrdenarCombos terminosOrdDest1 = new UtilidadesOrdenarCombos();
						TerminoVO[] terminosOrdDest = terminosOrdDest1.ordenarTerminosVO(terminosDest, idiomaLdap);						
						Collection idDest2 = Arrays.asList(terminosOrdDest);
						
						Iterator zDest = idDest2.iterator();
						while (zDest.hasNext()) {
							idDest.add(zDest.next());
						}
						form.setComboIdiomaMetaBackingList(idDest, "idTermino", "nomTermino");
						form.setComboIdiomaBackingList(idDest, "idTermino", "nomTermino");
						if ((metadatos != null)&&(metadatos.getIdioma() !=null)) {
							IdiomaVO idiomasVO =metadatos.getIdioma();
							terminoypadreVO = new TerminoYPadreVO();
							terminoypadreVO.setIdiomaTermino("en");
							terminoypadreVO.setIdVocabulario(l_id[x]);
							terminoypadreVO.setIdTermino("");
							terminoypadreVO.setNomTermino(idiomasVO.getTexto());
							terminoypadrear.add(terminoypadreVO);
						}
						else {
							terminoypadreVO = new TerminoYPadreVO();
							terminoypadreVO.setIdiomaTermino("en");
							terminoypadreVO.setIdVocabulario(l_id[x]);
							terminoypadreVO.setIdTermino("");
							terminoypadreVO.setNomTermino("");
							terminoypadrear.add(terminoypadreVO);
						}	
						break;
						
					case 1://ROL
						Collection tRol = new ArrayList();
						tRol.add(terminoVO);
						Collection tRolAux = Arrays.asList(vocabularioOrdenado[x].getTerminos());
						Iterator cont = tRolAux.iterator();
						while (cont.hasNext()) {
							tRol.add(cont.next());
						}
						form.setCrolBackingList(tRol, "idTermino", "nomTermino");
						if ((metadatos != null)&&(metadatos.getContribuciones() !=null)
								&&(metadatos.getContribuciones().length !=0)) {
							ContribucionVO[] contribucionesVO =metadatos.getContribuciones();
							for(int j=0;j<contribucionesVO.length;j++){
								terminoypadreVO = new TerminoYPadreVO();
								terminoypadreVO.setIdiomaTermino("en");
								terminoypadreVO.setIdVocabulario(l_id[x]);
								terminoypadreVO.setIdTermino("");
								if (contribucionesVO!= null && contribucionesVO[j].getTipo()!=null)
									terminoypadreVO.setNomTermino(contribucionesVO[j].getTipo().getValor());
								else
									terminoypadreVO.setNomTermino("");
								terminoypadrear.add(terminoypadreVO);
							}//terminoYPadreVO rellenado con los idiomas desde el lom avanzado
						}
						else {
							terminoypadreVO = new TerminoYPadreVO();
							terminoypadreVO.setIdiomaTermino("en");
							terminoypadreVO.setIdVocabulario(l_id[x]);
							terminoypadreVO.setIdTermino("");
							terminoypadreVO.setNomTermino("");
							terminoypadrear.add(terminoypadreVO);
						}
						
						break;

				}
				
	            //si general era null en el objeto de session lo dejamos a null
	            if(metaMetadataEsNull)
	            	catalogadorAvSession.getMDSesion().setMetaMetadata(null);
				
				if( logger.isDebugEnabled() ){

	                logger.debug("Cargados los combos del formulario" );
				}

			}
			logger.debug("Cargados los combos");

				
			TerminoYPadreVO[] arrayTerminoYPadre=(TerminoYPadreVO[])terminoypadrear.toArray(new TerminoYPadreVO[terminoypadrear.size()]);
			TerminoYPadreVO[] terminoYPadreVuelta = this.getSrvVocabulariosControladosService().obtenerIdTermino(arrayTerminoYPadre);
			
			if( logger.isDebugEnabled() ){

                logger.debug("Cargados en el formulario los idiomas, la estructura y el nivel de agregacion" );
			}	
			//IDIOMA
			String idioma = terminoYPadreVuelta[0].getIdTermino();
			if (idioma == null) {
				idioma = "";//No hay ningún estado seleccionado
			}
			form.setComboIdiomaMeta(idioma);
			
			//IDENTIFICADOR
			if(catalogadorAvSession.getMDSesion().getMetaMetadata()!=null && catalogadorAvSession.getMDSesion().getMetaMetadata().getIdentificadores() != null&& (catalogadorAvSession.getMDSesion().getMetaMetadata().getIdentificadores().length)> 0){
				IdentificadorVO[] identVO= catalogadorAvSession.getMDSesion().getMetaMetadata().getIdentificadores();
				Collection identVOAux = new ArrayList();
				for(int i=0;i<identVO.length;i++){
					IdentificadorVO ident = new IdentificadorVO();
					if((identVO[i].getCatalogo()!=null && !identVO[i].getCatalogo().trim().equals("")) || (identVO[i].getEntrada()!=null && !identVO[i].getEntrada().trim().equals(""))){
						ident.setCatalogo(identVO[i].getCatalogo().trim());
						ident.setEntrada(identVO[i].getEntrada().trim());
						identVOAux.add(ident);
					}
				}
				if(identVOAux.size()==0){
					IdentificadorVO[] aIdentificadores = new IdentificadorVO[1];
			    	IdentificadorVO identificadorVO=new IdentificadorVO();
			    	identificadorVO.setCatalogo("");
			    	identificadorVO.setEntrada("");
			    	aIdentificadores[0]=identificadorVO;
			    	form.setIdentificadoresAsArray(aIdentificadores);
				}
				else
					form.setIdentificadores(identVOAux);
			}
			else{
				IdentificadorVO[] aIdentificadores = new IdentificadorVO[1];
		    	IdentificadorVO identificadorVO=new IdentificadorVO();
		    	identificadorVO.setCatalogo("");
		    	identificadorVO.setEntrada("");
		    	aIdentificadores[0]=identificadorVO;
		    	form.setIdentificadoresAsArray(aIdentificadores); 
			}
			
			//ESQUEMA
			AvMetametadataVO metaMetadata = catalogadorAvSession.getMDSesion().getMetaMetadata();
			if(metaMetadata!=null && metaMetadata.getEsquemas()!=null && (metaMetadata.getEsquemas().length)> 0){
				//form.setEsquemasMetaAsArray(metaMetadata.getEsquemas());
				EsquemaDeMetadatosVO[] esqVO= metaMetadata.getEsquemas();
				Collection esqVOAux = new ArrayList();
				for(int i=0;i<esqVO.length;i++){
					EsquemaDeMetadatosVO esq = new EsquemaDeMetadatosVO();
					if(esqVO[i].getTexto()!=null && !esqVO[i].getTexto().trim().equals("")){
						esq.setTexto(esqVO[i].getTexto().trim());
						esqVOAux.add(esq);
					}
				}
				if(esqVOAux.size()==0){
					EsquemaDeMetadatosVO[] aEsquemas = new EsquemaDeMetadatosVO[1];
					EsquemaDeMetadatosVO esquemaVO=new EsquemaDeMetadatosVO();
					esquemaVO.setTexto("");
					aEsquemas[0]=esquemaVO;
			    	form.setEsquemasMetaAsArray(aEsquemas);
				}
				else
					form.setEsquemasMeta(esqVOAux);
			}
			else{
				EsquemaDeMetadatosVO[] aEsquemas = new EsquemaDeMetadatosVO[1];
				EsquemaDeMetadatosVO esquemaVO=new EsquemaDeMetadatosVO();
				esquemaVO.setTexto("");
				aEsquemas[0]=esquemaVO;
		    	form.setEsquemasMetaAsArray(aEsquemas);
			}
			
			//CONTRIBUCIONES
			//ArrayList terminoYPadreDescrip = new  ArrayList();
			LangStringVO[] langTextosDescrip = null;
			LangStringVO[] nuevolangTextosDescrip =null;
			
			
			if ((metadatos != null)&&(metadatos.getContribuciones() !=null)
					&& (metadatos.getContribuciones().length)> 0) {
				ContribucionVO[] contribuciones = metadatos.getContribuciones();
				Contribucion[] contribucionesAux=new Contribucion[contribuciones.length];
				DescripcionVO[] aDescripcionesVO = new DescripcionVO[contribuciones.length];
				for(int i = 0;i<contribuciones.length;i++){
					ArrayList terminoYPadreDescrip = new  ArrayList();
					Contribucion contribucionAux=new Contribucion();
					//2.3.3 Fecha
					/************************/
					Fecha auxFecha=new Fecha();
					auxFecha.setIdioma(idiomaLdap);
					//Descripcion de la fecha
					if(contribuciones[i].getFecha()!=null && contribuciones[i].getFecha().getDescripcion() !=null && contribuciones[i].getFecha().getDescripcion().getTextos()!= null){
						langTextosDescrip =contribuciones[i].getFecha().getDescripcion().getTextos();
						for(int j=0;j<langTextosDescrip.length;j++){
							terminoypadreVO = new TerminoYPadreVO();
							terminoypadreVO.setIdiomaTermino("en");
							terminoypadreVO.setIdVocabulario(prop.getProperty("idiomaDestinatario"));
							terminoypadreVO.setIdTermino("");
							if(langTextosDescrip[j].getIdioma()!=null)
								terminoypadreVO.setNomTermino(langTextosDescrip[j].getIdioma());
							else
								terminoypadreVO.setNomTermino("");
							terminoYPadreDescrip.add(terminoypadreVO);
						}
					
						TerminoYPadreVO[] arrayTerminoYPadreDesc=(TerminoYPadreVO[])terminoYPadreDescrip.toArray(new TerminoYPadreVO[terminoYPadreDescrip.size()]);
						TerminoYPadreVO[] terminoYPadreDescVuelta=null;
						if(arrayTerminoYPadreDesc!= null && arrayTerminoYPadreDesc.length>0){
							terminoYPadreDescVuelta=new TerminoYPadreVO[arrayTerminoYPadreDesc.length];
							terminoYPadreDescVuelta = this.getSrvVocabulariosControladosService().obtenerIdTermino(arrayTerminoYPadreDesc);
						}else{
							terminoYPadreDescVuelta=new TerminoYPadreVO[0];
						}
						
						//nuevolangTextosDescrip = new LangStringVO[terminoYPadreDescVuelta.length];
						ArrayList aNuevolangTextosDescrip =new ArrayList();
						if(terminoYPadreDescVuelta!=null && terminoYPadreDescVuelta.length>0 ){
							for (int k = 0; k<terminoYPadreDescVuelta.length; k++) {
								LangStringVO langDesc = new LangStringVO();
								langDesc.setTexto(langTextosDescrip[k].getTexto());
								langDesc.setIdioma(terminoYPadreDescVuelta[k].getIdTermino());
								if(!langDesc.getTexto().trim().equals(""))
									aNuevolangTextosDescrip.add(langDesc);
								//nuevolangTextosDescrip[k] = langDesc;
				            }
						}else{
							LangStringVO langDesc = new LangStringVO();
							langDesc.setTexto("");
							langDesc.setIdioma("");
							if(!langDesc.getTexto().trim().equals(""))
								aNuevolangTextosDescrip.add(langDesc);
						}
						if(aNuevolangTextosDescrip.size()==0){
							nuevolangTextosDescrip = new LangStringVO[1];
							LangStringVO lang = new LangStringVO();
							lang.setIdioma("");
							lang.setTexto("");
							nuevolangTextosDescrip[0] = lang;
						}
						else 
							nuevolangTextosDescrip = (LangStringVO[])aNuevolangTextosDescrip.toArray(new LangStringVO[aNuevolangTextosDescrip.size()]);
						DescripcionVO descripcionVO= new DescripcionVO();
						descripcionVO.setTextos(nuevolangTextosDescrip);
						aDescripcionesVO[i]=descripcionVO;
					}
					else{
						//Descripcion vacia
						DescripcionVO descripcionVO = new DescripcionVO();
						nuevolangTextosDescrip = new LangStringVO[1];
						LangStringVO lang = new LangStringVO();
						lang.setIdioma("");
						lang.setTexto("");
						nuevolangTextosDescrip[0] = lang;
						descripcionVO.setTextos(nuevolangTextosDescrip);
						aDescripcionesVO[i]=descripcionVO;
					} 
	    			//FECHA --> 2007-06-09... Formato completo-->yyyy-MM-ddThh:mm:ss.S(+-)00:00 <--

					
					if( contribuciones[i].getFecha()!=null && 
							contribuciones[i].getFecha().getFecha()!=null)
					{
						String fvo=  contribuciones[i].getFecha().getFecha();
						auxFecha.setFechaLomes(fvo );
					}
	    			 contribucionAux.setFecha(auxFecha);

					
					/************************/
					//2.3.1 Tipo (ROL)
					/************************/
					String rol = terminoYPadreVuelta[i+1].getIdTermino();
					if (rol == null) {
						rol = "";//No hay ningún estado seleccionado
					}
					SourceValueVO tipo = new SourceValueVO();
					tipo.setValor(rol);
					contribucionAux.setRol(tipo);
					/************************/
					//2.3.2 Entidades
					/************************/
//					BEGIN:VCARD VERSION 3.0 FN:GT8/SC36/CT71 EMAIL;TYPE=INTERNET:GT8@aenor.es ORG:AENOR END:VCARD
					Entidad[] entidadesAux = null;
					ArrayList aEntidades= new ArrayList();
					if(contribuciones[i].getEntidades()!=null){
	    				EntidadVO[] entidades=metadatos.getContribuciones()[i].getEntidades();
	    				//entidadesAux = new Entidad[entidades.length];
	    				
	    				for(int j=0;j<entidades.length;j++){
	    					String nombre="";
	    					String organizacion="";
	    					String email="";
	    					String sEntidad = entidades[j].getTexto();
	    					if(sEntidad !=null && !sEntidad.trim().equals("")){
			    				String[] trozos = sEntidad.split(":");
			    				for (int k=0; k<trozos.length; k++) {
			    					String elem=trozos[k];
			    					if (elem.toUpperCase().endsWith("EMAIL;TYPE=INTERNET")){
			    						int finNombre = elem.toUpperCase().indexOf("EMAIL;TYPE=INTERNET");
			    						nombre = elem.substring(0, finNombre);
			    					}
			    					else if (elem.toUpperCase().endsWith("ORG")){
			    						int finEmail = elem.toUpperCase().indexOf("ORG");
			    						email = elem.substring(0, finEmail);
			    						if(!validaEmail(email.trim()))
			    							email="";
			    					}
			    					else if (elem.toUpperCase().endsWith("END")){
			    						int finOrg = elem.toUpperCase().indexOf("END");
			    						organizacion = elem.substring(0, finOrg);
			    					} 
			    				}//end for
			    				
			    				Entidad entidad = new Entidad();
			    				entidad.setCorreo(email!=null?email:"");
			    				entidad.setNombre(nombre!=null?nombre:"");
			    				entidad.setOrganizacion(organizacion!=null?organizacion:"");
			    				aEntidades.add(entidad);
			    				//entidadesAux[j]=entidad;
	    					}		    				
	    				}
	    				entidadesAux = (Entidad[])aEntidades.toArray(new Entidad[aEntidades.size()]);
	    				if(entidadesAux.length ==0){
							entidadesAux = new Entidad[1];
		    				Entidad entidad = new Entidad();
		    				entidad.setCorreo("");
		    				entidad.setNombre("");
		    				entidad.setOrganizacion("");
		    				entidadesAux[0]=entidad;
	    				}
    				}
					else{
						entidadesAux = new Entidad[1];
	    				Entidad entidad = new Entidad();
	    				entidad.setCorreo("");
	    				entidad.setNombre("");
	    				entidad.setOrganizacion("");
	    				entidadesAux[0]=entidad;
					}
    				contribucionAux.setEntidades(entidadesAux);
					/************************/
    				contribucionesAux[i]=contribucionAux;
				}
				form.setContribucionAsArray(contribucionesAux);
				form.setDescripcionesAsArray(aDescripcionesVO);
			} 
			else{
				Contribucion[] contribucionesAux=new Contribucion[1];
				Contribucion contribucionAux = new Contribucion();
				//Descripcion vacia
				DescripcionVO[] aDescripcionesVO = new DescripcionVO[1];
				DescripcionVO descripcionVO = new DescripcionVO();
				nuevolangTextosDescrip = new LangStringVO[1];
				LangStringVO lang = new LangStringVO();
				lang.setIdioma("");
				lang.setTexto("");
				nuevolangTextosDescrip[0] = lang;
				descripcionVO.setTextos(nuevolangTextosDescrip);
				aDescripcionesVO[0]=descripcionVO;
				//Fecha vacia
				Fecha auxFecha = new Fecha();
				auxFecha.setIdioma(idiomaLdap);
				contribucionAux.setFecha(auxFecha);

				/************************/
				//Tipo (ROL)
				SourceValueVO tipo = new SourceValueVO();
				tipo.setSource("");
				tipo.setValor("");
				contribucionAux.setRol(tipo);
				/************************/
				//Entidades
				Entidad[] entidades = new Entidad[1];
				Entidad entidad = new Entidad();
				entidad.setCorreo("");
				entidad.setNombre("");
				entidad.setOrganizacion("");
				entidades[0]=entidad;
				contribucionAux.setEntidades(entidades);
				/************************/
				contribucionesAux[0]=contribucionAux;
				form.setContribucionAsArray(contribucionesAux);
				form.setDescripcionesAsArray(aDescripcionesVO) ;
			}
			
			
	           //si metaMetadata era null en el objeto de session lo dejamos a null
            if (metaMetadataEsNull)
            	catalogadorAvSession.getMDSesion().setMetaMetadata(null);
            
            MetametadatoSession metadatoSession = this.getMetametadatoSession(request);
            cargarAyuda(metadatoSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
            	
        }catch (Exception e) {
			logger
			.error("Error en catalogadorWeb verMetadatos, metodo cargarMetametadatos " + e);
			//throw new Exception("ver.metadatos", e);
			saveErrorMessage(request, " Error en catalogadorWeb verMetadatos, metodo cargarMetametadatos ");
        }finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
        }
		
	
     }


    /**
     * @see es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.MetametadatosController#accionSubmit(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.AccionSubmitForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String accionSubmit(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.AccionSubmitForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		String accion;
		if(form.getAccion()==null || form.getAccion().equals("")){
			accion= UtilidadesOrdenarCombos.obtenerAccion(request);
			form.setAccion(accion);
		}
		else
			accion=form.getAccion();

    	String[] accionPartes = accion.split("-");
    	accion = accionPartes[0];
    	if (accionPartes.length  > 1){
    		request.setAttribute("posicion", accionPartes[1]);
    		if (accionPartes.length == 3)
    			request.setAttribute("posicionExterior", accionPartes[2]);
    	}
    	String resAction = "";
    	
    	//String idioma=((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
		ResourceBundle datosResources = I18n.getInstance().getResource("application-resources", (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
        
//        if (datosResources.getString("metadatos.AñadirIdentificador").equals(accion)) {
//              resAction = "Añadir Identificador";
//        } else if (datosResources.getString("metadatos.EliminarIdentificador").equals(accion)) {
//        	  resAction = "Eliminar Identificador";
//        } else if (datosResources.getString("metadatos.AñadirContribucion").equals(accion)) {
//     	   resAction = "Añadir Contribucion";
//        } else if (datosResources.getString("metadatos.EliminarContribucion").equals(accion)) {
//       	   resAction = "Eliminar Contribucion";
//        } else if (datosResources.getString("metadatos.AñadirIdentidad").equals(accion)) {
//       	   resAction = "Añadir Identidad";
//        } else if (datosResources.getString("metadatos.EliminarIdentidad").equals(accion)) {
//       	   resAction = "Eliminar Identidad";
//        } else if (datosResources.getString("metadatos.AñadirDescripcion").equals(accion)) {
//       	   resAction = "Añadir Descripcion";
//        } else if (datosResources.getString("metadatos.EliminarDescripcion").equals(accion)) {
//       	   resAction = "Eliminar Descripcion";
//        } else if (datosResources.getString("metadatos.AñadirEsquemas").equals(accion)) {
//           resAction = "Añadir Esquemas";
//        } else 
//		if (datosResources.getString("metadatos.EliminarEsquemas").equals(accion)) {
//           resAction = "Eliminar Esquemas";
//        } else 
		if (datosResources.getString("metadatos.Validar").equals(accion)) {
       	   resAction = "Validar";
        } else if (datosResources.getString("metadatos.Aceptar").equals(accion)) {
       	   resAction = "Aceptar";
        } else if (datosResources.getString("metadatos.Cancelar").equals(accion)) {
     	   resAction = "Cancelar";
        } else if (datosResources.getString("metadatos.Reset").equals(accion)) {
       	   resAction = "Reset";
          }
        else
        	resAction= accion;
		
        return resAction;
    }




    /**
     * @see es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.MetametadatosController#anadirIdentificador(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.AnadirIdentificadorForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void anadirIdentificador(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.AnadirIdentificadorForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
       	
        try{

        	Object valor = request.getSession().getAttribute("form");
  		  
        	if (valor instanceof MetaMetadatosFormImpl) {
        		MetaMetadatosFormImpl formulario=(MetaMetadatosFormImpl) valor;
    	      	form.setDescripciones(formulario.getDescripciones());  
       	      	form.setIdentificadores(formulario.getIdentificadores());
       	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
       	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
       	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
       	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
       	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
    	      	form.setCrolLabelList(formulario.getCrolLabelList());
    	      	form.setCrolValueList(formulario.getCrolValueList());
    	      	form.setContribucion(formulario.getContribucion());
    		}
    		else if (valor instanceof MetaMetadatosFormSubmitFormImpl) {
    			MetaMetadatosFormSubmitFormImpl formulario=(MetaMetadatosFormSubmitFormImpl) valor;
    	      	form.setDescripciones(formulario.getDescripciones());  
       	      	form.setIdentificadores(formulario.getIdentificadores());
       	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
       	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
       	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
       	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
       	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
    	      	form.setCrolLabelList(formulario.getCrolLabelList());
    	      	form.setCrolValueList(formulario.getCrolValueList());
    	      	form.setContribucion(formulario.getContribucion());
    		}
    		else if (valor instanceof MetadatosValidoVolverFormImpl) {
    			MetadatosValidoVolverFormImpl formulario=(MetadatosValidoVolverFormImpl) valor;
    	      	form.setDescripciones(formulario.getDescripciones());  
       	      	form.setIdentificadores(formulario.getIdentificadores());
       	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
       	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
       	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
       	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
       	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
    	      	form.setCrolLabelList(formulario.getCrolLabelList());
    	      	form.setCrolValueList(formulario.getCrolValueList());
    	      	form.setContribucion(formulario.getContribucion());
    		}
    		else if (valor instanceof MetadatosNoValidoVolverFormImpl) {
    			MetadatosNoValidoVolverFormImpl formulario=(MetadatosNoValidoVolverFormImpl) valor;
    	      	form.setDescripciones(formulario.getDescripciones());  
       	      	form.setIdentificadores(formulario.getIdentificadores());
       	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
       	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
       	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
       	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
       	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
    	      	form.setCrolLabelList(formulario.getCrolLabelList());
    	      	form.setCrolValueList(formulario.getCrolValueList());
    	      	form.setContribucion(formulario.getContribucion());
    		}
      	
//    		hay tantas descripciones como contribuciones pues en una contribucion solo a un campo fecha con descripcionVO correspondiente
    	  	int longitudDescripciones=((DescripcionVO[])(form.getDescripcionesAsArray())).length;
    	  	int longitudContribuciones=((Contribucion[])(form.getContribucionAsArray())).length;
    		
       		int longitudEsquemas=form.getEsquemasMeta().size();
       	  	int longitudIdentificadores=form.getIdentificadores().size();
    	  	
    	  	int[] longitudTextosDesc = new int[form.getDescripciones().size()];
    		for(int i= 0;i< form.getDescripciones().size();i++){
    			longitudTextosDesc[i]=((DescripcionVO)(form.getDescripcionesAsArray()[i])).getTextos().length;
    		}
    		
    	  	int[] longitudEntidadesContrib = new int[form.getContribucion().size()];
    		for(int i= 0;i< form.getContribucion().size();i++){
    			longitudEntidadesContrib[i]=((Contribucion)(form.getContribucionAsArray()[i])).getEntidades().length;
    		}
    		
    		//borramos de la session
    		request.getSession().removeAttribute(MetametadatoSession.SESSION_KEY);
    	  	cambioFormulario(request,longitudIdentificadores,longitudEsquemas,longitudDescripciones,longitudContribuciones
    	  			,longitudTextosDesc,longitudEntidadesContrib);
    	  	//recogemos de session
    	  	MetametadatoSession metametadatoSession = this.getMetametadatoSession(request);
    	  	//identificadores
       	  	//form.setIdentificadoresAsArray(identificadores);
    	  	form.setIdentificadoresAsArray(metametadatoSession.getIdentificadores().toArray());
       	  	//esquemas
       	  	//form.setEsquemasMetaAsArray(esquemasMeta);
    	  	form.setEsquemasMetaAsArray(metametadatoSession.getEsquemasMeta().toArray());
    	  	//descripciones de la fecha
    	  	//form.setDescripcionesAsArray(descripciones);
    	  	form.setDescripcionesAsArray(metametadatoSession.getDescripciones().toArray());
    	  	//contribuciones
    	  	//form.setContribucionAsArray(contribuciones);
    	  	form.setContribucionAsArray(metametadatoSession.getContribuciones().toArray());
	
   
	   	  	Object[] aIdentificadores=form.getIdentificadoresAsArray();
	   	  	int nuevoTamano = aIdentificadores.length +1;
	   	  	Object[] newAIdentificadores = new Object[nuevoTamano];
	   	  	IdentificadorVO identificadorVO=new IdentificadorVO();
	   	  	identificadorVO.setCatalogo("");
	   	  	identificadorVO.setEntrada("");
	   	  	for(int i = 0; i<aIdentificadores.length;i++)
	   	  		newAIdentificadores[i]=aIdentificadores[i];
	   	  	newAIdentificadores[nuevoTamano-1]= identificadorVO;
	   	   
	   	  	form.setIdentificadoresAsArray(newAIdentificadores);
	   	  	//actualizamos la session
	   	  	metametadatoSession.setIdentificadores(Arrays.asList(newAIdentificadores));
	   	  	
	   	  	cargarAyuda(metametadatoSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
	   	 
	   	  	
		}catch (IndexOutOfBoundsException ai) {
			logger
			.warn("Warning en catalogadorWeb meta-metadatos, metodo anadirIdentificador " + ai);
		}catch (Exception e) {
			logger
			.error("Error en Servicio de catalogacionWeb meta-metadatos, metodo anadirIdentificador " + e);
			//throw new Exception("ver.metadatos",e);
			saveErrorMessage(request, " Error en Servicio de catalogacionWeb meta-metadatos, metodo anadirIdentificador ");
		}	       	   
     }




    /**
     * @see es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.MetametadatosController#eliminarIdentificador(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.EliminarIdentificadorForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void eliminarIdentificador(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.EliminarIdentificadorForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
        	Object valor = request.getSession().getAttribute("form");
  		  
        	if (valor instanceof MetaMetadatosFormImpl) {
        		MetaMetadatosFormImpl formulario=(MetaMetadatosFormImpl) valor;
    	      	form.setDescripciones(formulario.getDescripciones());  
       	      	form.setIdentificadores(formulario.getIdentificadores());
       	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
       	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
       	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
       	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
       	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
    	      	form.setCrolLabelList(formulario.getCrolLabelList());
    	      	form.setCrolValueList(formulario.getCrolValueList());
    	      	form.setContribucion(formulario.getContribucion());
    		}
    		else if (valor instanceof MetaMetadatosFormSubmitFormImpl) {
    			MetaMetadatosFormSubmitFormImpl formulario=(MetaMetadatosFormSubmitFormImpl) valor;
    	      	form.setDescripciones(formulario.getDescripciones());  
       	      	form.setIdentificadores(formulario.getIdentificadores());
       	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
       	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
       	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
       	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
       	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
    	      	form.setCrolLabelList(formulario.getCrolLabelList());
    	      	form.setCrolValueList(formulario.getCrolValueList());
    	      	form.setContribucion(formulario.getContribucion());
    		}
    		else if (valor instanceof MetadatosValidoVolverFormImpl) {
    			MetadatosValidoVolverFormImpl formulario=(MetadatosValidoVolverFormImpl) valor;
    	      	form.setDescripciones(formulario.getDescripciones());  
       	      	form.setIdentificadores(formulario.getIdentificadores());
       	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
       	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
       	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
       	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
       	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
    	      	form.setCrolLabelList(formulario.getCrolLabelList());
    	      	form.setCrolValueList(formulario.getCrolValueList());
    	      	form.setContribucion(formulario.getContribucion());
    		}
    		else if (valor instanceof MetadatosNoValidoVolverFormImpl) {
    			MetadatosNoValidoVolverFormImpl formulario=(MetadatosNoValidoVolverFormImpl) valor;
    	      	form.setDescripciones(formulario.getDescripciones());  
       	      	form.setIdentificadores(formulario.getIdentificadores());
       	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
       	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
       	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
       	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
       	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
    	      	form.setCrolLabelList(formulario.getCrolLabelList());
    	      	form.setCrolValueList(formulario.getCrolValueList());
    	      	form.setContribucion(formulario.getContribucion());
    		}
     	
//    		hay tantas descripciones como contribuciones pues en una contribucion solo a un campo fecha con descripcionVO correspondiente
    	  	int longitudDescripciones=((DescripcionVO[])(form.getDescripcionesAsArray())).length;
    	  	int longitudContribuciones=((Contribucion[])(form.getContribucionAsArray())).length;
    		
       		int longitudEsquemas=form.getEsquemasMeta().size();
       	  	int longitudIdentificadores=form.getIdentificadores().size();
    	  	
    	  	int[] longitudTextosDesc = new int[form.getDescripciones().size()];
    		for(int i= 0;i< form.getDescripciones().size();i++){
    			longitudTextosDesc[i]=((DescripcionVO)(form.getDescripcionesAsArray()[i])).getTextos().length;
    		}
    		
    	  	int[] longitudEntidadesContrib = new int[form.getContribucion().size()];
    		for(int i= 0;i< form.getContribucion().size();i++){
    			longitudEntidadesContrib[i]=((Contribucion)(form.getContribucionAsArray()[i])).getEntidades().length;
    		}
    		
    		//eliminamos session
    		request.getSession().removeAttribute(MetametadatoSession.SESSION_KEY);
    	  	cambioFormulario(request,longitudIdentificadores,longitudEsquemas,longitudDescripciones,longitudContribuciones
    	  			,longitudTextosDesc,longitudEntidadesContrib);
    	  	
    	  	// recogemos de session
    	  	MetametadatoSession metametadatoSession = this.getMetametadatoSession(request);
    	  	//identificadores
       	  	//form.setIdentificadoresAsArray(identificadores);
    	  	form.setIdentificadoresAsArray(metametadatoSession.getIdentificadores().toArray());
       	  	//esquemas
       	  	//form.setEsquemasMetaAsArray(esquemasMeta);
    	  	form.setEsquemasMetaAsArray(metametadatoSession.getEsquemasMeta().toArray());
    	  	//descripciones de la fecha
    	  	//form.setDescripcionesAsArray(descripciones);
    	  	form.setDescripcionesAsArray(metametadatoSession.getDescripciones().toArray());
    	  	//contribuciones
    	  	//form.setContribucionAsArray(contribuciones);
    	  	form.setContribucionAsArray(metametadatoSession.getContribuciones().toArray());
    		
    		//posicion del identificador que se quiere eliminar   
	   	  	String posicion = request.getAttribute("posicion").toString();
	   	  	int posicionInt = Integer.parseInt(posicion);
	   	  	IdentificadorVO[] aIdentificadores=(IdentificadorVO[]) form.getIdentificadoresAsArray();
	   	  	IdentificadorVO[] nuevoIdentificadores = new IdentificadorVO[metametadatoSession.getIdentificadores().toArray().length -1];
	   	  	//añado al nuevo array de identificadores todos los identificadores menos el que 
	   	  	//queremos eliminar
	   	  	for (int i = 0; i<nuevoIdentificadores.length;i++){
	   	  		if (i < posicionInt)
	   	  			nuevoIdentificadores[i] = aIdentificadores[i];
	   	  		else
	   	  			nuevoIdentificadores[i] = aIdentificadores[i+1]; 
	   	  	}
		   
	   	  	form.setIdentificadoresAsArray(nuevoIdentificadores);
        	//actualizamos la session
	   	  	metametadatoSession.setIdentificadores(Arrays.asList(nuevoIdentificadores));
        	
	   	  	cargarAyuda(metametadatoSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
	   	 
        	
		}catch (IndexOutOfBoundsException ai) {
			logger
			.warn("Warning en catalogadorWeb meta-metadatos, metodo eliminarIdentificador " + ai);
		}catch (Exception e) {
			logger
			.error("Error en Servicio de catalogacionWeb meta-metadatos, metodo eliminarIdentificador " + e);
			//throw new Exception("ver.metadatos",e);
			saveErrorMessage(request, " Error en Servicio de catalogacionWeb meta-metadatos, metodo eliminarIdentificador ");
		}
    }
    





    /**
     * @see es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.MetametadatosController#anadirContribucion(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.AnadirContribucionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void anadirContribucion(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.AnadirContribucionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
			Object valor = request.getSession().getAttribute("form");
			  
        	if (valor instanceof MetaMetadatosFormImpl) {
        		MetaMetadatosFormImpl formulario=(MetaMetadatosFormImpl) valor;
    	      	form.setDescripciones(formulario.getDescripciones());  
       	      	form.setIdentificadores(formulario.getIdentificadores());
       	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
       	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
       	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
       	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
       	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
    	      	form.setCrolLabelList(formulario.getCrolLabelList());
    	      	form.setCrolValueList(formulario.getCrolValueList());
    	      	form.setContribucion(formulario.getContribucion());
    		}
    		else if (valor instanceof MetaMetadatosFormSubmitFormImpl) {
    			MetaMetadatosFormSubmitFormImpl formulario=(MetaMetadatosFormSubmitFormImpl) valor;
    	      	form.setDescripciones(formulario.getDescripciones());  
       	      	form.setIdentificadores(formulario.getIdentificadores());
       	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
       	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
       	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
       	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
       	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
    	      	form.setCrolLabelList(formulario.getCrolLabelList());
    	      	form.setCrolValueList(formulario.getCrolValueList());
    	      	form.setContribucion(formulario.getContribucion());
    		}
    		else if (valor instanceof MetadatosValidoVolverFormImpl) {
    			MetadatosValidoVolverFormImpl formulario=(MetadatosValidoVolverFormImpl) valor;
    	      	form.setDescripciones(formulario.getDescripciones());  
       	      	form.setIdentificadores(formulario.getIdentificadores());
       	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
       	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
       	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
       	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
       	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
    	      	form.setCrolLabelList(formulario.getCrolLabelList());
    	      	form.setCrolValueList(formulario.getCrolValueList());
    	      	form.setContribucion(formulario.getContribucion());
    		}
    		else if (valor instanceof MetadatosNoValidoVolverFormImpl) {
    			MetadatosNoValidoVolverFormImpl formulario=(MetadatosNoValidoVolverFormImpl) valor;
    	      	form.setDescripciones(formulario.getDescripciones());  
       	      	form.setIdentificadores(formulario.getIdentificadores());
       	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
       	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
       	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
       	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
       	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
    	      	form.setCrolLabelList(formulario.getCrolLabelList());
    	      	form.setCrolValueList(formulario.getCrolValueList());
    	      	form.setContribucion(formulario.getContribucion());
    		}
		
		//		hay tantas descripciones como contribuciones pues en una contribucion solo a un campo fecha con descripcionVO correspondiente
			  	int longitudDescripciones=((DescripcionVO[])(form.getDescripcionesAsArray())).length;
			  	int longitudContribuciones=((Contribucion[])(form.getContribucionAsArray())).length;
				
		   		int longitudEsquemas=form.getEsquemasMeta().size();
		   	  	int longitudIdentificadores=form.getIdentificadores().size();
			  	
			  	int[] longitudTextosDesc = new int[form.getDescripciones().size()];
				for(int i= 0;i< form.getDescripciones().size();i++){
					longitudTextosDesc[i]=((DescripcionVO)(form.getDescripcionesAsArray()[i])).getTextos().length;
				}
				
			  	int[] longitudEntidadesContrib = new int[form.getContribucion().size()];
				for(int i= 0;i< form.getContribucion().size();i++){
					longitudEntidadesContrib[i]=((Contribucion)(form.getContribucionAsArray()[i])).getEntidades().length;
				}
				//borramos de la session
	    		request.getSession().removeAttribute(MetametadatoSession.SESSION_KEY);
			  	cambioFormulario(request,longitudIdentificadores,longitudEsquemas,longitudDescripciones,longitudContribuciones
			  			,longitudTextosDesc,longitudEntidadesContrib);
			  	//recogemos de session
	    	  	MetametadatoSession metametadatoSession = this.getMetametadatoSession(request);
		 
	    	  	//identificadores
	       	  	//form.setIdentificadoresAsArray(identificadores);
	    	  	form.setIdentificadoresAsArray(metametadatoSession.getIdentificadores().toArray());
	       	  	//esquemas
	       	  	//form.setEsquemasMetaAsArray(esquemasMeta);
	    	  	form.setEsquemasMetaAsArray(metametadatoSession.getEsquemasMeta().toArray());
	    	  	//descripciones de la fecha
	    	  	//form.setDescripcionesAsArray(descripciones);
	    	  	form.setDescripcionesAsArray(metametadatoSession.getDescripciones().toArray());
	    	  	//contribuciones
	    	  	//form.setContribucionAsArray(contribuciones);
	    	  	form.setContribucionAsArray(metametadatoSession.getContribuciones().toArray());
	 
			  	Contribucion[] aContribuciones=(Contribucion[])form.getContribucionAsArray();
	
			  	int nuevoTamano = aContribuciones.length +1;
			  	Contribucion[] newContribuciones= new Contribucion[nuevoTamano];
	
			  	for(int i = 0; i<aContribuciones.length;i++)
			  		newContribuciones[i]=aContribuciones[i];
			  	Contribucion contribucion = new Contribucion();
			  	//tipo
			  	SourceValueVO tipo = new SourceValueVO();
			  	tipo.setSource("");
			  	tipo.setValor("");
			  	//fecha
			  	Fecha fecha = new Fecha();
			  	fecha.setIdioma(this.getCatalogadorAvSession(request).getIdioma());
			  	//entidades
			  	Entidad[] entidades =new Entidad[1];
			  	Entidad entidad = new Entidad();
			  	entidad.setCorreo("");
			  	entidad.setNombre("");
			  	entidad.setOrganizacion("");
			  	entidades[0]=entidad;
			  	contribucion.setRol(tipo);
			  	contribucion.setFecha(fecha);
			  	contribucion.setEntidades(entidades);
			  	newContribuciones[aContribuciones.length]=contribucion;
			  	form.setContribucionAsArray(newContribuciones);
			  	//actualizamos la session
			  	metametadatoSession.setContribuciones(Arrays.asList(newContribuciones));
			  	
		    	//se añade la descripcion de la fecha
			  	
		    	DescripcionVO[] descArray = (DescripcionVO[]) form.getDescripcionesAsArray();
		    	DescripcionVO[] newDescArray = new DescripcionVO[descArray.length+1];
		    	for(int i = 0 ; i < descArray.length;i++){
		    		newDescArray[i]= descArray[i];
		    	}
		    	DescripcionVO newDesc= new DescripcionVO();
		    	LangStringVO nuevoTexto = new LangStringVO();
		    	LangStringVO[] nuevoTextos = new LangStringVO[1];
		    	nuevoTexto.setIdioma("");
		    	nuevoTexto.setTexto("");
		    	nuevoTextos[0]=nuevoTexto;
		    	newDesc.setTextos(nuevoTextos);
		    	newDescArray[aContribuciones.length]=newDesc;
		    	form.setDescripcionesAsArray(newDescArray);
		    	//actualizamos tb las descripciones
		    	metametadatoSession.setDescripciones(Arrays.asList(newDescArray));
		    	
		    	cargarAyuda(metametadatoSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
		    	
		}catch (IndexOutOfBoundsException ai) {
			logger
			.warn("Warning en catalogadorWeb meta-metadatos, metodo anadirContribucion " + ai);
		}catch (Exception e) {
			logger
			.error("Error en Servicio de catalogacionWeb meta-metadatos, metodo anadirContribucion " + e);
			//throw new Exception("ver.metadatos",e);
			saveErrorMessage(request, " Error en Servicio de catalogacionWeb meta-metadatos, metodo anadirContribucion ");
		}
     }




    /**
     * @see es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.MetametadatosController#eliminarContribucion(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.EliminarContribucionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void eliminarContribucion(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.EliminarContribucionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
 
    	try{
			Object valor = request.getSession().getAttribute("form");
			  
        	if (valor instanceof MetaMetadatosFormImpl) {
        		MetaMetadatosFormImpl formulario=(MetaMetadatosFormImpl) valor;
    	      	form.setDescripciones(formulario.getDescripciones());  
       	      	form.setIdentificadores(formulario.getIdentificadores());
       	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
       	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
       	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
       	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
       	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
    	      	form.setCrolLabelList(formulario.getCrolLabelList());
    	      	form.setCrolValueList(formulario.getCrolValueList());
    	      	form.setContribucion(formulario.getContribucion());
    		}
    		else if (valor instanceof MetaMetadatosFormSubmitFormImpl) {
    			MetaMetadatosFormSubmitFormImpl formulario=(MetaMetadatosFormSubmitFormImpl) valor;
    	      	form.setDescripciones(formulario.getDescripciones());  
       	      	form.setIdentificadores(formulario.getIdentificadores());
       	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
       	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
       	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
       	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
       	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
    	      	form.setCrolLabelList(formulario.getCrolLabelList());
    	      	form.setCrolValueList(formulario.getCrolValueList());
    	      	form.setContribucion(formulario.getContribucion());
    		}
    		else if (valor instanceof MetadatosValidoVolverFormImpl) {
    			MetadatosValidoVolverFormImpl formulario=(MetadatosValidoVolverFormImpl) valor;
    	      	form.setDescripciones(formulario.getDescripciones());  
       	      	form.setIdentificadores(formulario.getIdentificadores());
       	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
       	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
       	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
       	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
       	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
    	      	form.setCrolLabelList(formulario.getCrolLabelList());
    	      	form.setCrolValueList(formulario.getCrolValueList());
    	      	form.setContribucion(formulario.getContribucion());
    		}
    		else if (valor instanceof MetadatosNoValidoVolverFormImpl) {
    			MetadatosNoValidoVolverFormImpl formulario=(MetadatosNoValidoVolverFormImpl) valor;
    	      	form.setDescripciones(formulario.getDescripciones());  
       	      	form.setIdentificadores(formulario.getIdentificadores());
       	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
       	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
       	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
       	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
       	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
    	      	form.setCrolLabelList(formulario.getCrolLabelList());
    	      	form.setCrolValueList(formulario.getCrolValueList());
    	      	form.setContribucion(formulario.getContribucion());
    		}
		
		//		hay tantas descripciones como contribuciones pues en una contribucion solo a un campo fecha con descripcionVO correspondiente
			  	int longitudDescripciones=((DescripcionVO[])(form.getDescripcionesAsArray())).length;
			  	int longitudContribuciones=((Contribucion[])(form.getContribucionAsArray())).length;
				
		   		int longitudEsquemas=form.getEsquemasMeta().size();
		   	  	int longitudIdentificadores=form.getIdentificadores().size();
			  	
			  	int[] longitudTextosDesc = new int[form.getDescripciones().size()];
				for(int i= 0;i< form.getDescripciones().size();i++){
					longitudTextosDesc[i]=((DescripcionVO)(form.getDescripcionesAsArray()[i])).getTextos().length;
				}
				
			  	int[] longitudEntidadesContrib = new int[form.getContribucion().size()];
				for(int i= 0;i< form.getContribucion().size();i++){
					longitudEntidadesContrib[i]=((Contribucion)(form.getContribucionAsArray()[i])).getEntidades().length;
				}
				
				//borramos de la session
	    		request.getSession().removeAttribute(MetametadatoSession.SESSION_KEY);
			  	cambioFormulario(request,longitudIdentificadores,longitudEsquemas,longitudDescripciones,longitudContribuciones
			  			,longitudTextosDesc,longitudEntidadesContrib);
			  	//recogemos de session
	    	  	MetametadatoSession metametadatoSession = this.getMetametadatoSession(request);
		 
	    	  	//identificadores
	       	  	//form.setIdentificadoresAsArray(identificadores);
	    	  	form.setIdentificadoresAsArray(metametadatoSession.getIdentificadores().toArray());
	       	  	//esquemas
	       	  	//form.setEsquemasMetaAsArray(esquemasMeta);
	    	  	form.setEsquemasMetaAsArray(metametadatoSession.getEsquemasMeta().toArray());
	    	  	//descripciones de la fecha
	    	  	//form.setDescripcionesAsArray(descripciones);
	    	  	form.setDescripcionesAsArray(metametadatoSession.getDescripciones().toArray());
	    	  	//contribuciones
	    	  	//form.setContribucionAsArray(contribuciones);
	    	  	form.setContribucionAsArray(metametadatoSession.getContribuciones().toArray());
		  
	    	  	//posicion de la contribucion que se quiere eliminar   
		        String posicion = request.getAttribute("posicion").toString();
		        int posicionInt = Integer.parseInt(posicion);
		        Contribucion[] aContribuciones=(Contribucion[])form.getContribucionAsArray();
		        Contribucion[] nuevoContribuciones=new Contribucion[aContribuciones.length-1];
			   
		     	//añado al nuevo array de contribuciones todas las contribuciones menos el que 
		     	//queremos eliminar
		     	for (int i = 0; i<nuevoContribuciones.length;i++){
		     		if (i < posicionInt)
		     			nuevoContribuciones[i] = aContribuciones[i];
		     		else
		     			nuevoContribuciones[i] = aContribuciones[i+1]; 
		     	}
		     	form.setContribucionAsArray(nuevoContribuciones);
		     	//actualizamos la session
		     	metametadatoSession.setContribuciones(Arrays.asList(nuevoContribuciones));
		     	
		    	//eliminamos la descripcion correspondiente a la fecha
		    	DescripcionVO[] aDescripciones = (DescripcionVO[]) form.getDescripcionesAsArray();
		    	DescripcionVO[] newDescripciones = new DescripcionVO[aDescripciones.length-1];
	
		    	for (int i = 0; i<newDescripciones.length;i++){
		    		if (i < posicionInt)
		    			newDescripciones[i] = aDescripciones[i];
		    		else
		    			newDescripciones[i] = aDescripciones[i+1]; 
		    	}
		    	form.setDescripcionesAsArray(newDescripciones);
		    	//actualizamos session
		    	metametadatoSession.setDescripciones(Arrays.asList(newDescripciones));
		    	
		    	cargarAyuda(metametadatoSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
		  	
		}catch (IndexOutOfBoundsException ai) {
			logger
			.warn("Warning en catalogadorWeb meta-metadatos, metodo eliminarContribucion " + ai);
		}catch (Exception e) {
			logger
			.error("Error en Servicio de catalogacionWeb meta-metadatos, metodo eliminarContribucion " + e);
			//throw new Exception("ver.metadatos",e);
			saveErrorMessage(request, " Error en Servicio de catalogacionWeb meta-metadatos, metodo eliminarContribucion ");
		}
    	
     }




    /**
     * @see es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.MetametadatosController#anadirIdentidad(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.AnadirIdentidadForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void anadirIdentidad(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.AnadirIdentidadForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
			Object valor = request.getSession().getAttribute("form");
			  
        	if (valor instanceof MetaMetadatosFormImpl) {
        		MetaMetadatosFormImpl formulario=(MetaMetadatosFormImpl) valor;
    	      	form.setDescripciones(formulario.getDescripciones());  
       	      	form.setIdentificadores(formulario.getIdentificadores());
       	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
       	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
       	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
       	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
       	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
    	      	form.setCrolLabelList(formulario.getCrolLabelList());
    	      	form.setCrolValueList(formulario.getCrolValueList());
    	      	form.setContribucion(formulario.getContribucion());
    		}
    		else if (valor instanceof MetaMetadatosFormSubmitFormImpl) {
    			MetaMetadatosFormSubmitFormImpl formulario=(MetaMetadatosFormSubmitFormImpl) valor;
    	      	form.setDescripciones(formulario.getDescripciones());  
       	      	form.setIdentificadores(formulario.getIdentificadores());
       	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
       	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
       	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
       	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
       	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
    	      	form.setCrolLabelList(formulario.getCrolLabelList());
    	      	form.setCrolValueList(formulario.getCrolValueList());
    	      	form.setContribucion(formulario.getContribucion());
    		}
    		else if (valor instanceof MetadatosValidoVolverFormImpl) {
    			MetadatosValidoVolverFormImpl formulario=(MetadatosValidoVolverFormImpl) valor;
    	      	form.setDescripciones(formulario.getDescripciones());  
       	      	form.setIdentificadores(formulario.getIdentificadores());
       	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
       	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
       	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
       	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
       	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
    	      	form.setCrolLabelList(formulario.getCrolLabelList());
    	      	form.setCrolValueList(formulario.getCrolValueList());
    	      	form.setContribucion(formulario.getContribucion());
    		}
    		else if (valor instanceof MetadatosNoValidoVolverFormImpl) {
    			MetadatosNoValidoVolverFormImpl formulario=(MetadatosNoValidoVolverFormImpl) valor;
    	      	form.setDescripciones(formulario.getDescripciones());  
       	      	form.setIdentificadores(formulario.getIdentificadores());
       	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
       	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
       	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
       	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
       	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
    	      	form.setCrolLabelList(formulario.getCrolLabelList());
    	      	form.setCrolValueList(formulario.getCrolValueList());
    	      	form.setContribucion(formulario.getContribucion());
    		}
		
		//		hay tantas descripciones como contribuciones pues en una contribucion solo a un campo fecha con descripcionVO correspondiente
			  	int longitudDescripciones=((DescripcionVO[])(form.getDescripcionesAsArray())).length;
			  	int longitudContribuciones=((Contribucion[])(form.getContribucionAsArray())).length;
				
		   		int longitudEsquemas=form.getEsquemasMeta().size();
		   	  	int longitudIdentificadores=form.getIdentificadores().size();
			  	
			  	int[] longitudTextosDesc = new int[form.getDescripciones().size()];
				for(int i= 0;i< form.getDescripciones().size();i++){
					longitudTextosDesc[i]=((DescripcionVO)(form.getDescripcionesAsArray()[i])).getTextos().length;
				}
				
			  	int[] longitudEntidadesContrib = new int[form.getContribucion().size()];
				for(int i= 0;i< form.getContribucion().size();i++){
					longitudEntidadesContrib[i]=((Contribucion)(form.getContribucionAsArray()[i])).getEntidades().length;
				}
				
				//borramos de la session
	    		request.getSession().removeAttribute(MetametadatoSession.SESSION_KEY);
			  	cambioFormulario(request,longitudIdentificadores,longitudEsquemas,longitudDescripciones,longitudContribuciones
			  			,longitudTextosDesc,longitudEntidadesContrib);
			  	//recogemos de session
	    	  	MetametadatoSession metametadatoSession = this.getMetametadatoSession(request);
		 
		        //identificadores
	       	  	//form.setIdentificadoresAsArray(identificadores);
	    	  	form.setIdentificadoresAsArray(metametadatoSession.getIdentificadores().toArray());
	       	  	//esquemas
	       	  	//form.setEsquemasMetaAsArray(esquemasMeta);
	    	  	form.setEsquemasMetaAsArray(metametadatoSession.getEsquemasMeta().toArray());
	    	  	//descripciones de la fecha
	    	  	//form.setDescripcionesAsArray(descripciones);
	    	  	form.setDescripcionesAsArray(metametadatoSession.getDescripciones().toArray());
	    	  	//contribuciones
	    	  	//form.setContribucionAsArray(contribuciones);
	    	  	form.setContribucionAsArray(metametadatoSession.getContribuciones().toArray());
	 
	    	  	String posicion = request.getAttribute("posicion").toString();
				int posicionInt = Integer.parseInt(posicion);//indica la contribucion donde se añade la entidad
		    	//se recogen todas las descripciones
				Contribucion[] contribArray = (Contribucion[]) form.getContribucionAsArray();
		    	//recogo los textos de la descripcion indicada y le añado un nuevo texto
		    	Entidad[] entidades = contribArray[posicionInt].getEntidades();
		    	Entidad[] nuevoEntidades = new Entidad[entidades.length+1];
		    	for(int i = 0 ; i < entidades.length;i++){
		    		nuevoEntidades[i]= entidades[i];
		    	}
		    	Entidad nuevaEntidad = new Entidad();
		    	nuevaEntidad.setCorreo("");
		    	nuevaEntidad.setNombre("");
		    	nuevaEntidad.setOrganizacion("");
		    	nuevoEntidades[nuevoEntidades.length-1] = nuevaEntidad;
		    	contribArray[posicionInt].setEntidades(nuevoEntidades);
		    	form.setContribucionAsArray(contribArray);
		    	//actualizamos session
		    	metametadatoSession.setContribuciones(Arrays.asList(contribArray));
		    	
		    	cargarAyuda(metametadatoSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
		    	
		}catch (IndexOutOfBoundsException ai) {
			logger
			.warn("Warning en catalogadorWeb meta-metadatos, metodo anadirIdentidad " + ai);
		}catch (Exception e) {
			logger
			.error("Error en Servicio de catalogacionWeb meta-metadatos, metodo anadirIdentidad " + e);
			//throw new Exception("ver.metadatos",e);
			saveErrorMessage(request, " Error en Servicio de catalogacionWeb meta-metadatos, metodo anadirIdentidad ");
		}
    	
     }



    /**
     * @see es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.MetametadatosController#eliminarIdentidad(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.EliminarIdentidadForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void eliminarIdentidad(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.EliminarIdentidadForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
			Object valor = request.getSession().getAttribute("form");
			  
        	if (valor instanceof MetaMetadatosFormImpl) {
        		MetaMetadatosFormImpl formulario=(MetaMetadatosFormImpl) valor;
    	      	form.setDescripciones(formulario.getDescripciones());  
       	      	form.setIdentificadores(formulario.getIdentificadores());
       	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
       	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
       	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
       	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
       	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
    	      	form.setCrolLabelList(formulario.getCrolLabelList());
    	      	form.setCrolValueList(formulario.getCrolValueList());
    	      	form.setContribucion(formulario.getContribucion());
    		}
    		else if (valor instanceof MetaMetadatosFormSubmitFormImpl) {
    			MetaMetadatosFormSubmitFormImpl formulario=(MetaMetadatosFormSubmitFormImpl) valor;
    	      	form.setDescripciones(formulario.getDescripciones());  
       	      	form.setIdentificadores(formulario.getIdentificadores());
       	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
       	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
       	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
       	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
       	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
    	      	form.setCrolLabelList(formulario.getCrolLabelList());
    	      	form.setCrolValueList(formulario.getCrolValueList());
    	      	form.setContribucion(formulario.getContribucion());
    		}
    		else if (valor instanceof MetadatosValidoVolverFormImpl) {
    			MetadatosValidoVolverFormImpl formulario=(MetadatosValidoVolverFormImpl) valor;
    	      	form.setDescripciones(formulario.getDescripciones());  
       	      	form.setIdentificadores(formulario.getIdentificadores());
       	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
       	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
       	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
       	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
       	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
    	      	form.setCrolLabelList(formulario.getCrolLabelList());
    	      	form.setCrolValueList(formulario.getCrolValueList());
    	      	form.setContribucion(formulario.getContribucion());
    		}
    		else if (valor instanceof MetadatosNoValidoVolverFormImpl) {
    			MetadatosNoValidoVolverFormImpl formulario=(MetadatosNoValidoVolverFormImpl) valor;
    	      	form.setDescripciones(formulario.getDescripciones());  
       	      	form.setIdentificadores(formulario.getIdentificadores());
       	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
       	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
       	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
       	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
       	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
    	      	form.setCrolLabelList(formulario.getCrolLabelList());
    	      	form.setCrolValueList(formulario.getCrolValueList());
    	      	form.setContribucion(formulario.getContribucion());
    		}
		
		//		hay tantas descripciones como contribuciones pues en una contribucion solo a un campo fecha con descripcionVO correspondiente
			  	int longitudDescripciones=((DescripcionVO[])(form.getDescripcionesAsArray())).length;
			  	int longitudContribuciones=((Contribucion[])(form.getContribucionAsArray())).length;
				
		   		int longitudEsquemas=form.getEsquemasMeta().size();
		   	  	int longitudIdentificadores=form.getIdentificadores().size();
			  	
			  	int[] longitudTextosDesc = new int[form.getDescripciones().size()];
				for(int i= 0;i< form.getDescripciones().size();i++){
					longitudTextosDesc[i]=((DescripcionVO)(form.getDescripcionesAsArray()[i])).getTextos().length;
				}
				
			  	int[] longitudEntidadesContrib = new int[form.getContribucion().size()];
				for(int i= 0;i< form.getContribucion().size();i++){
					longitudEntidadesContrib[i]=((Contribucion)(form.getContribucionAsArray()[i])).getEntidades().length;
				}
				//borramos de la session
	    		request.getSession().removeAttribute(MetametadatoSession.SESSION_KEY);
			  	cambioFormulario(request,longitudIdentificadores,longitudEsquemas,longitudDescripciones,longitudContribuciones
			  			,longitudTextosDesc,longitudEntidadesContrib);
			  	
			  	//recogemos de session
	    	  	MetametadatoSession metametadatoSession = this.getMetametadatoSession(request);
		        //identificadores
	    		//identificadores
	       	  	//form.setIdentificadoresAsArray(identificadores);
	    	  	form.setIdentificadoresAsArray(metametadatoSession.getIdentificadores().toArray());
	       	  	//esquemas
	       	  	//form.setEsquemasMetaAsArray(esquemasMeta);
	    	  	form.setEsquemasMetaAsArray(metametadatoSession.getEsquemasMeta().toArray());
	    	  	//descripciones de la fecha
	    	  	//form.setDescripcionesAsArray(descripciones);
	    	  	form.setDescripcionesAsArray(metametadatoSession.getDescripciones().toArray());
	    	  	//contribuciones
	    	  	//form.setContribucionAsArray(contribuciones);
	    	  	form.setContribucionAsArray(metametadatoSession.getContribuciones().toArray());
	 
		        //posicion de contenido a eliminar   
		        String posicion = request.getAttribute("posicion").toString();
		        int posicionInt = Integer.parseInt(posicion);
				
				//posicion de la contribucion de la que se quiere eliminar una entidad
		        String posicionContrib = request.getAttribute("posicionExterior").toString();
		        int posicionContribInt = Integer.parseInt(posicionContrib);
		    	
		    	Contribucion[] aContribuciones = (Contribucion[]) form.getContribucionAsArray();
		    	Contribucion[] contribuciones = (Contribucion[])metametadatoSession.getContribuciones()
    				.toArray(new Contribucion[metametadatoSession.getContribuciones().size()]);
		    	Entidad[] entidades = contribuciones[posicionContribInt].getEntidades();
		    	Entidad[] nuevoEntidades = new Entidad[entidades.length -1];
		    	//añado al nuevo array de entidades todos los entidades menos la que 
		    	//queremos eliminar
		    	for (int i = 0; i<nuevoEntidades.length;i++){
		    		if (i < posicionInt)
		    			nuevoEntidades[i] = entidades[i];
		    		else
		    			nuevoEntidades[i] = entidades[i+1]; 
		    	}
		    	aContribuciones[posicionContribInt].setEntidades(nuevoEntidades);
		    	form.setContribucionAsArray(aContribuciones);
		    	//actualizamos session
		    	metametadatoSession.setContribuciones(Arrays.asList(aContribuciones));
		    	
		    	cargarAyuda(metametadatoSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
		    	
		}catch (IndexOutOfBoundsException ai) {
			logger
			.warn("Warning en catalogadorWeb meta-metadatos, metodo eliminarIdentidad " + ai);
		}catch (Exception e) {
			logger
			.error("Error en Servicio de catalogacionWeb meta-metadatos, metodo elimnarIdentidad " + e);
			//throw new Exception("ver.metadatos",e);
			saveErrorMessage(request, " Error en Servicio de catalogacionWeb meta-metadatos, metodo elimnarIdentidad ");
		}
    	
     }



    /**
     * @see es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.MetametadatosController#anadirDescripcion(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.AnadirDescripcionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void anadirDescripcion(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.AnadirDescripcionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
			Object valor = request.getSession().getAttribute("form");
			  
        	if (valor instanceof MetaMetadatosFormImpl) {
        		MetaMetadatosFormImpl formulario=(MetaMetadatosFormImpl) valor;
    	      	form.setDescripciones(formulario.getDescripciones());  
       	      	form.setIdentificadores(formulario.getIdentificadores());
       	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
       	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
       	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
       	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
       	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
    	      	form.setCrolLabelList(formulario.getCrolLabelList());
    	      	form.setCrolValueList(formulario.getCrolValueList());
    	      	form.setContribucion(formulario.getContribucion());
    		}
    		else if (valor instanceof MetaMetadatosFormSubmitFormImpl) {
    			MetaMetadatosFormSubmitFormImpl formulario=(MetaMetadatosFormSubmitFormImpl) valor;
    	      	form.setDescripciones(formulario.getDescripciones());  
       	      	form.setIdentificadores(formulario.getIdentificadores());
       	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
       	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
       	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
       	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
       	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
    	      	form.setCrolLabelList(formulario.getCrolLabelList());
    	      	form.setCrolValueList(formulario.getCrolValueList());
    	      	form.setContribucion(formulario.getContribucion());
    		}
    		else if (valor instanceof MetadatosValidoVolverFormImpl) {
    			MetadatosValidoVolverFormImpl formulario=(MetadatosValidoVolverFormImpl) valor;
    	      	form.setDescripciones(formulario.getDescripciones());  
       	      	form.setIdentificadores(formulario.getIdentificadores());
       	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
       	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
       	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
       	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
       	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
    	      	form.setCrolLabelList(formulario.getCrolLabelList());
    	      	form.setCrolValueList(formulario.getCrolValueList());
    	      	form.setContribucion(formulario.getContribucion());
    		}
    		else if (valor instanceof MetadatosNoValidoVolverFormImpl) {
    			MetadatosNoValidoVolverFormImpl formulario=(MetadatosNoValidoVolverFormImpl) valor;
    	      	form.setDescripciones(formulario.getDescripciones());  
       	      	form.setIdentificadores(formulario.getIdentificadores());
       	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
       	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
       	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
       	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
       	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
    	      	form.setCrolLabelList(formulario.getCrolLabelList());
    	      	form.setCrolValueList(formulario.getCrolValueList());
    	      	form.setContribucion(formulario.getContribucion());
    		}
		
		//		hay tantas descripciones como contribuciones pues en una contribucion solo a un campo fecha con descripcionVO correspondiente
			  	int longitudDescripciones=((DescripcionVO[])(form.getDescripcionesAsArray())).length;
			  	int longitudContribuciones=((Contribucion[])(form.getContribucionAsArray())).length;
				
		   		int longitudEsquemas=form.getEsquemasMeta().size();
		   	  	int longitudIdentificadores=form.getIdentificadores().size();
			  	
			  	int[] longitudTextosDesc = new int[form.getDescripciones().size()];
				for(int i= 0;i< form.getDescripciones().size();i++){
					longitudTextosDesc[i]=((DescripcionVO)(form.getDescripcionesAsArray()[i])).getTextos().length;
				}
				
			  	int[] longitudEntidadesContrib = new int[form.getContribucion().size()];
				for(int i= 0;i< form.getContribucion().size();i++){
					longitudEntidadesContrib[i]=((Contribucion)(form.getContribucionAsArray()[i])).getEntidades().length;
				}
				
				//borramos de la session
	    		request.getSession().removeAttribute(MetametadatoSession.SESSION_KEY);
			  	cambioFormulario(request,longitudIdentificadores,longitudEsquemas,longitudDescripciones,longitudContribuciones
			  			,longitudTextosDesc,longitudEntidadesContrib);
			  	// recogemos de session
	    	  	MetametadatoSession metametadatoSession = this.getMetametadatoSession(request);
		 
		        //identificadores
	       	  	//form.setIdentificadoresAsArray(identificadores);
	    	  	form.setIdentificadoresAsArray(metametadatoSession.getIdentificadores().toArray());
	       	  	//esquemas
	       	  	//form.setEsquemasMetaAsArray(esquemasMeta);
	    	  	form.setEsquemasMetaAsArray(metametadatoSession.getEsquemasMeta().toArray());
	    	  	//descripciones de la fecha
	    	  	//form.setDescripcionesAsArray(descripciones);
	    	  	form.setDescripcionesAsArray(metametadatoSession.getDescripciones().toArray());
	    	  	//contribuciones
	    	  	//form.setContribucionAsArray(contribuciones);
	    	  	form.setContribucionAsArray(metametadatoSession.getContribuciones().toArray());
		  	
				String posicion = request.getAttribute("posicion").toString();
				int posicionInt = Integer.parseInt(posicion);//indica la contribucion donde se añade la descripcion
				//se recogen todas las descripciones
				DescripcionVO[] descArray = (DescripcionVO[]) form.getDescripcionesAsArray();
				//recogo los textos de la descripcion indicada y le añado un nuevo texto
				LangStringVO[] textos = descArray[posicionInt].getTextos();
				LangStringVO[] nuevoTextos = new LangStringVO[textos.length+1];
				for(int i = 0 ; i < textos.length;i++){
					nuevoTextos[i]= textos[i];
				}
				LangStringVO nuevoTexto = new LangStringVO();
				nuevoTexto.setIdioma("");
				nuevoTexto.setTexto("");
				nuevoTextos[nuevoTextos.length-1] = nuevoTexto;
				descArray[posicionInt].setTextos(nuevoTextos);
				form.setDescripcionesAsArray(descArray);
				//actualizamos la session
				metametadatoSession.setDescripciones(Arrays.asList(descArray));
    		
				cargarAyuda(metametadatoSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
				
		}catch (IndexOutOfBoundsException ai) {
			logger
			.warn("Warning en catalogadorWeb meta-metadatos, metodo anadirDescripcion " + ai);
		}catch (Exception e) {
			logger
			.error("Error en Servicio de catalogacionWeb meta-metadatos, metodo anadirDescripcion " + e);
			//throw new Exception("ver.metadatos",e);
			saveErrorMessage(request, " Error en Servicio de catalogacionWeb meta-metadatos, metodo anadirDescripcion ");
		}
     }



    /**
     * @see es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.MetametadatosController#eliminarDescripcion(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.EliminarDescripcionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void eliminarDescripcion(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.EliminarDescripcionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
			Object valor = request.getSession().getAttribute("form");
			  
        	if (valor instanceof MetaMetadatosFormImpl) {
        		MetaMetadatosFormImpl formulario=(MetaMetadatosFormImpl) valor;
    	      	form.setDescripciones(formulario.getDescripciones());  
       	      	form.setIdentificadores(formulario.getIdentificadores());
       	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
       	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
       	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
       	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
       	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
    	      	form.setCrolLabelList(formulario.getCrolLabelList());
    	      	form.setCrolValueList(formulario.getCrolValueList());
    	      	form.setContribucion(formulario.getContribucion());
    		}
    		else if (valor instanceof MetaMetadatosFormSubmitFormImpl) {
    			MetaMetadatosFormSubmitFormImpl formulario=(MetaMetadatosFormSubmitFormImpl) valor;
    	      	form.setDescripciones(formulario.getDescripciones());  
       	      	form.setIdentificadores(formulario.getIdentificadores());
       	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
       	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
       	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
       	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
       	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
    	      	form.setCrolLabelList(formulario.getCrolLabelList());
    	      	form.setCrolValueList(formulario.getCrolValueList());
    	      	form.setContribucion(formulario.getContribucion());
    		}
    		else if (valor instanceof MetadatosValidoVolverFormImpl) {
    			MetadatosValidoVolverFormImpl formulario=(MetadatosValidoVolverFormImpl) valor;
    	      	form.setDescripciones(formulario.getDescripciones());  
       	      	form.setIdentificadores(formulario.getIdentificadores());
       	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
       	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
       	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
       	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
       	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
    	      	form.setCrolLabelList(formulario.getCrolLabelList());
    	      	form.setCrolValueList(formulario.getCrolValueList());
    	      	form.setContribucion(formulario.getContribucion());
    		}
    		else if (valor instanceof MetadatosNoValidoVolverFormImpl) {
    			MetadatosNoValidoVolverFormImpl formulario=(MetadatosNoValidoVolverFormImpl) valor;
    	      	form.setDescripciones(formulario.getDescripciones());  
       	      	form.setIdentificadores(formulario.getIdentificadores());
       	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
       	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
       	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
       	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
       	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
    	      	form.setCrolLabelList(formulario.getCrolLabelList());
    	      	form.setCrolValueList(formulario.getCrolValueList());
    	      	form.setContribucion(formulario.getContribucion());
    		}
		
		//		hay tantas descripciones como contribuciones pues en una contribucion solo a un campo fecha con descripcionVO correspondiente
			  	int longitudDescripciones=((DescripcionVO[])(form.getDescripcionesAsArray())).length;
			  	int longitudContribuciones=((Contribucion[])(form.getContribucionAsArray())).length;
				
		   		int longitudEsquemas=form.getEsquemasMeta().size();
		   	  	int longitudIdentificadores=form.getIdentificadores().size();
			  	
			  	int[] longitudTextosDesc = new int[form.getDescripciones().size()];
				for(int i= 0;i< form.getDescripciones().size();i++){
					longitudTextosDesc[i]=((DescripcionVO)(form.getDescripcionesAsArray()[i])).getTextos().length;
				}
				
			  	int[] longitudEntidadesContrib = new int[form.getContribucion().size()];
				for(int i= 0;i< form.getContribucion().size();i++){
					longitudEntidadesContrib[i]=((Contribucion)(form.getContribucionAsArray()[i])).getEntidades().length;
				}
				//borramos de la session
	    		request.getSession().removeAttribute(MetametadatoSession.SESSION_KEY);
			  	cambioFormulario(request,longitudIdentificadores,longitudEsquemas,longitudDescripciones,longitudContribuciones
			  			,longitudTextosDesc,longitudEntidadesContrib);
			  	// recogemos de session
	    	  	MetametadatoSession metametadatoSession = this.getMetametadatoSession(request);
	    	  	 
		        //identificadores
	       	  	//form.setIdentificadoresAsArray(identificadores);
	    	  	form.setIdentificadoresAsArray(metametadatoSession.getIdentificadores().toArray());
	       	  	//esquemas
	       	  	//form.setEsquemasMetaAsArray(esquemasMeta);
	    	  	form.setEsquemasMetaAsArray(metametadatoSession.getEsquemasMeta().toArray());
	    	  	//descripciones de la fecha
	    	  	//form.setDescripcionesAsArray(descripciones);
	    	  	form.setDescripcionesAsArray(metametadatoSession.getDescripciones().toArray());
	    	  	//contribuciones
	    	  	//form.setContribucionAsArray(contribuciones);
	    	  	form.setContribucionAsArray(metametadatoSession.getContribuciones().toArray());
		  	
		        //posicion de contenido a eliminar   
		        String posicion = request.getAttribute("posicion").toString();
		        int posicionInt = Integer.parseInt(posicion);
				
				//posicion de la contribucion de la que se quiere eliminar una descripcion
		        String posicionContrib = request.getAttribute("posicionExterior").toString();
		        int posicionContribtInt = Integer.parseInt(posicionContrib);
		    	
		    	DescripcionVO[] aDescripciones = (DescripcionVO[]) form.getDescripcionesAsArray();
		    	DescripcionVO[] descripciones = (DescripcionVO[]) metametadatoSession.getDescripciones()
    	  			.toArray(new DescripcionVO[metametadatoSession.getDescripciones().size()]);
		    	LangStringVO[] contenido = descripciones[posicionContribtInt].getTextos();
		    	LangStringVO[] nuevoContenido = new LangStringVO[contenido.length -1];
		    	//añado al nuevo array de contenidos todos los contenidos menos el que 
		    	//queremos eliminar
		    	for (int i = 0; i<nuevoContenido.length;i++){
		    		if (i < posicionInt)
		    			nuevoContenido[i] = contenido[i];
		    		else
		    			nuevoContenido[i] = contenido[i+1]; 
		    	}
		    	aDescripciones[posicionContribtInt].setTextos(nuevoContenido);
		    	form.setDescripcionesAsArray(aDescripciones);
		    	//actualizamos la session
		    	metametadatoSession.setDescripciones(Arrays.asList(aDescripciones));
		    	
		    	cargarAyuda(metametadatoSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
    		
		}catch (IndexOutOfBoundsException ai) {
			logger
			.warn("Warning en catalogadorWeb meta-metadatos, metodo eliminarDescripcion " + ai);
		}catch (Exception e) {
			logger
			.error("Error en Servicio de catalogacionWeb meta-metadatos, metodo eliminarDescripcion " + e);
			//throw new Exception("ver.metadatos",e);
			saveErrorMessage(request, " Error en Servicio de catalogacionWeb meta-metadatos, metodo eliminarDescripcion ");
		}
    	
     }


    /**
     * @see es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.MetametadatosController#anadirEsquemaMeta(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.AnadirEsquemaMetaForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void anadirEsquemaMeta(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.AnadirEsquemaMetaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        try{
        	Object valor = request.getSession().getAttribute("form");
    		  
        	if (valor instanceof MetaMetadatosFormImpl) {
        		MetaMetadatosFormImpl formulario=(MetaMetadatosFormImpl) valor;
    	      	form.setDescripciones(formulario.getDescripciones());  
       	      	form.setIdentificadores(formulario.getIdentificadores());
       	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
       	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
       	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
       	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
       	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
    	      	form.setCrolLabelList(formulario.getCrolLabelList());
    	      	form.setCrolValueList(formulario.getCrolValueList());
    	      	form.setContribucion(formulario.getContribucion());
    		}
    		else if (valor instanceof MetaMetadatosFormSubmitFormImpl) {
    			MetaMetadatosFormSubmitFormImpl formulario=(MetaMetadatosFormSubmitFormImpl) valor;
    	      	form.setDescripciones(formulario.getDescripciones());  
       	      	form.setIdentificadores(formulario.getIdentificadores());
       	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
       	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
       	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
       	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
       	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
    	      	form.setCrolLabelList(formulario.getCrolLabelList());
    	      	form.setCrolValueList(formulario.getCrolValueList());
    	      	form.setContribucion(formulario.getContribucion());
    		}
    		else if (valor instanceof MetadatosValidoVolverFormImpl) {
    			MetadatosValidoVolverFormImpl formulario=(MetadatosValidoVolverFormImpl) valor;
    	      	form.setDescripciones(formulario.getDescripciones());  
       	      	form.setIdentificadores(formulario.getIdentificadores());
       	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
       	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
       	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
       	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
       	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
    	      	form.setCrolLabelList(formulario.getCrolLabelList());
    	      	form.setCrolValueList(formulario.getCrolValueList());
    	      	form.setContribucion(formulario.getContribucion());
    		}
    		else if (valor instanceof MetadatosNoValidoVolverFormImpl) {
    			MetadatosNoValidoVolverFormImpl formulario=(MetadatosNoValidoVolverFormImpl) valor;
    	      	form.setDescripciones(formulario.getDescripciones());  
       	      	form.setIdentificadores(formulario.getIdentificadores());
       	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
       	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
       	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
       	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
       	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
    	      	form.setCrolLabelList(formulario.getCrolLabelList());
    	      	form.setCrolValueList(formulario.getCrolValueList());
    	      	form.setContribucion(formulario.getContribucion());
    		}
      	
//    		hay tantas descripciones como contribuciones pues en una contribucion solo a un campo fecha con descripcionVO correspondiente
    	  	int longitudDescripciones=((DescripcionVO[])(form.getDescripcionesAsArray())).length;
    	  	int longitudContribuciones=((Contribucion[])(form.getContribucionAsArray())).length;
    		
       		int longitudEsquemas=form.getEsquemasMeta().size();
       	  	int longitudIdentificadores=form.getIdentificadores().size();
    	  	
    	  	int[] longitudTextosDesc = new int[form.getDescripciones().size()];
    		for(int i= 0;i< form.getDescripciones().size();i++){
    			longitudTextosDesc[i]=((DescripcionVO)(form.getDescripcionesAsArray()[i])).getTextos().length;
    		}
    		
    	  	int[] longitudEntidadesContrib = new int[form.getContribucion().size()];
    		for(int i= 0;i< form.getContribucion().size();i++){
    			longitudEntidadesContrib[i]=((Contribucion)(form.getContribucionAsArray()[i])).getEntidades().length;
    		}
    		
    		//borramos de la session
    		request.getSession().removeAttribute(MetametadatoSession.SESSION_KEY);
    	  	cambioFormulario(request,longitudIdentificadores,longitudEsquemas,longitudDescripciones,longitudContribuciones
    	  			,longitudTextosDesc,longitudEntidadesContrib);
    	  	//recogemos de session
    	  	MetametadatoSession metametadatoSession = this.getMetametadatoSession(request);
     
            //identificadores
    		//form.setIdentificadoresAsArray(identificadores);
    	  	form.setIdentificadoresAsArray(metametadatoSession.getIdentificadores().toArray());
       	  	//esquemas
       	  	//form.setEsquemasMetaAsArray(esquemasMeta);
    	  	form.setEsquemasMetaAsArray(metametadatoSession.getEsquemasMeta().toArray());
    	  	//descripciones de la fecha
    	  	//form.setDescripcionesAsArray(descripciones);
    	  	form.setDescripcionesAsArray(metametadatoSession.getDescripciones().toArray());
    	  	//contribuciones
    	  	//form.setContribucionAsArray(contribuciones);
    	  	form.setContribucionAsArray(metametadatoSession.getContribuciones().toArray());
	              
	   	  	Object[] aEsquemas=form.getEsquemasMetaAsArray();
	   	  	int nuevoTamano = aEsquemas.length +1;
	   	  	Object[] newAEsquemas = new Object[nuevoTamano];
	   	  	EsquemaDeMetadatosVO esquemaVO=new EsquemaDeMetadatosVO();
	   	  	esquemaVO.setTexto("");
	   	  	for(int i = 0; i<aEsquemas.length;i++)
	   	  		newAEsquemas[i]=aEsquemas[i];
	   	  	newAEsquemas[nuevoTamano-1]= esquemaVO;
	   	   
	   	  	form.setEsquemasMetaAsArray(newAEsquemas);
	   	  	//actualizamos la session
	   	  	metametadatoSession.setEsquemasMeta(Arrays.asList(newAEsquemas));
	   	  	
	   	  	cargarAyuda(metametadatoSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
       	   
		}catch (IndexOutOfBoundsException ai) {
			logger
			.warn("Warning en catalogadorWeb meta-metadatos, metodo anadirEsquemaMeta " + ai);
		}catch (Exception e) {
			logger
			.error("Error en Servicio de catalogacionWeb meta-metadatos, metodo anadirEsquemaMeta " + e);
			//throw new Exception("ver.metadatos",e);
			saveErrorMessage(request, " Error en Servicio de catalogacionWeb meta-metadatos, metodo anadirEsquemaMeta ");
		}
     }




    /**
     * @see es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.MetametadatosController#eliminarEsquemaMeta(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.EliminarEsquemaMetaForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void eliminarEsquemaMeta(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.EliminarEsquemaMetaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
        	Object valor = request.getSession().getAttribute("form");
    		  
        	if (valor instanceof MetaMetadatosFormImpl) {
        		MetaMetadatosFormImpl formulario=(MetaMetadatosFormImpl) valor;
    	      	form.setDescripciones(formulario.getDescripciones());  
       	      	form.setIdentificadores(formulario.getIdentificadores());
       	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
       	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
       	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
       	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
       	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
    	      	form.setCrolLabelList(formulario.getCrolLabelList());
    	      	form.setCrolValueList(formulario.getCrolValueList());
    	      	form.setContribucion(formulario.getContribucion());
    		}
    		else if (valor instanceof MetaMetadatosFormSubmitFormImpl) {
    			MetaMetadatosFormSubmitFormImpl formulario=(MetaMetadatosFormSubmitFormImpl) valor;
    	      	form.setDescripciones(formulario.getDescripciones());  
       	      	form.setIdentificadores(formulario.getIdentificadores());
       	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
       	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
       	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
       	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
       	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
    	      	form.setCrolLabelList(formulario.getCrolLabelList());
    	      	form.setCrolValueList(formulario.getCrolValueList());
    	      	form.setContribucion(formulario.getContribucion());
    		}
    		else if (valor instanceof MetadatosValidoVolverFormImpl) {
    			MetadatosValidoVolverFormImpl formulario=(MetadatosValidoVolverFormImpl) valor;
    	      	form.setDescripciones(formulario.getDescripciones());  
       	      	form.setIdentificadores(formulario.getIdentificadores());
       	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
       	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
       	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
       	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
       	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
    	      	form.setCrolLabelList(formulario.getCrolLabelList());
    	      	form.setCrolValueList(formulario.getCrolValueList());
    	      	form.setContribucion(formulario.getContribucion());
    		}
    		else if (valor instanceof MetadatosNoValidoVolverFormImpl) {
    			MetadatosNoValidoVolverFormImpl formulario=(MetadatosNoValidoVolverFormImpl) valor;
    	      	form.setDescripciones(formulario.getDescripciones());  
       	      	form.setIdentificadores(formulario.getIdentificadores());
       	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
       	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
       	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
       	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
       	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
    	      	form.setCrolLabelList(formulario.getCrolLabelList());
    	      	form.setCrolValueList(formulario.getCrolValueList());
    	      	form.setContribucion(formulario.getContribucion());
    		}
   	
//    		hay tantas descripciones como contribuciones pues en una contribucion solo a un campo fecha con descripcionVO correspondiente
    	  	int longitudDescripciones=((DescripcionVO[])(form.getDescripcionesAsArray())).length;
    	  	int longitudContribuciones=((Contribucion[])(form.getContribucionAsArray())).length;
    		
       		int longitudEsquemas=form.getEsquemasMeta().size();
       	  	int longitudIdentificadores=form.getIdentificadores().size();
    	  	
    	  	int[] longitudTextosDesc = new int[form.getDescripciones().size()];
    		for(int i= 0;i< form.getDescripciones().size();i++){
    			longitudTextosDesc[i]=((DescripcionVO)(form.getDescripcionesAsArray()[i])).getTextos().length;
    		}
    		
    	  	int[] longitudEntidadesContrib = new int[form.getContribucion().size()];
    		for(int i= 0;i< form.getContribucion().size();i++){
    			longitudEntidadesContrib[i]=((Contribucion)(form.getContribucionAsArray()[i])).getEntidades().length;
    		}
    		//borramos de la session
    		request.getSession().removeAttribute(MetametadatoSession.SESSION_KEY);
    	  	cambioFormulario(request,longitudIdentificadores,longitudEsquemas,longitudDescripciones,longitudContribuciones
    	  			,longitudTextosDesc,longitudEntidadesContrib);
    	  	//recogemos de session
    	  	MetametadatoSession metametadatoSession = this.getMetametadatoSession(request);
     
            //identificadores
    		//form.setIdentificadoresAsArray(identificadores);
    	  	form.setIdentificadoresAsArray(metametadatoSession.getIdentificadores().toArray());
       	  	//esquemas
       	  	//form.setEsquemasMetaAsArray(esquemasMeta);
    	  	form.setEsquemasMetaAsArray(metametadatoSession.getEsquemasMeta().toArray());
    	  	//descripciones de la fecha
    	  	//form.setDescripcionesAsArray(descripciones);
    	  	form.setDescripcionesAsArray(metametadatoSession.getDescripciones().toArray());
    	  	//contribuciones
    	  	//form.setContribucionAsArray(contribuciones);
    	  	form.setContribucionAsArray(metametadatoSession.getContribuciones().toArray());
    		
    		//posicion del esquema que se quiere eliminar   
	   	  	String posicion = request.getAttribute("posicion").toString();
	   	  	int posicionInt = Integer.parseInt(posicion);
	   	  	EsquemaDeMetadatosVO[] aEsquemas=(EsquemaDeMetadatosVO[]) form.getEsquemasMetaAsArray();
	   	  	EsquemaDeMetadatosVO[] nuevoEsquemas = new EsquemaDeMetadatosVO[metametadatoSession.getEsquemasMeta().toArray().length -1];
	   	  	//añado al nuevo array de esquemas todos los esquemas menos el que 
	   	  	//queremos eliminar
	   	  	for (int i = 0; i<nuevoEsquemas.length;i++){
	   	  		if (i < posicionInt)
	   	  			nuevoEsquemas[i] = aEsquemas[i];
	   	  		else
	   	  			nuevoEsquemas[i] = aEsquemas[i+1]; 
	   	  	}
		   
	   	  	form.setEsquemasMetaAsArray(nuevoEsquemas);
	   	  	//actualizamos session
	   	  	metametadatoSession.setEsquemasMeta(Arrays.asList(nuevoEsquemas));
	   	  	
	   	  	cargarAyuda(metametadatoSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
        	
		}catch (IndexOutOfBoundsException ai) {
			logger
			.warn("Warning en catalogadorWeb meta-metadatos, metodo eliminarEsquemaMeta " + ai);
		}catch (Exception e) {
			logger
			.error("Error en Servicio de catalogacionWeb meta-metadatos, metodo eliminarEsquemaMeta " + e);
			//throw new Exception("ver.metadatos",e);
			saveErrorMessage(request, " Error en Servicio de catalogacionWeb meta-metadatos, metodo eliminarEsquemaMeta ");
		}
    }




//    /**
//     * @see es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.MetametadatosController#esValidoEsquemaMeta(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.EsValidoEsquemaMetaForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
//     */
//    public final boolean esValidoEsquemaMeta(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.EsValidoMetaMetadataForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
//    {
// 
//         return false;
//    }



    /**
     * @see es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.MetametadatosController#guardarMetadatos(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.GuardarMetadatosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void guardarMetadatos(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.metametadatos.GuardarMetadatosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		boolean errorFaltaIdioma = false;
		boolean errorFaltaTexto = false;
		boolean errorFechaObli= false;
		boolean errorFechaObliHorMin = false;
		boolean errorFechaZonaHoraria =false;
		boolean errorFechaComboZH =false;
		boolean errorFecha = false;
		boolean errorEmail=false;
		boolean errorNoNumero=false;
		boolean errorZHFueraRango =false;
		boolean errorFechaFueraRango=false;
		boolean errorFormatoFecha=false;
		
		CatalogadorAvSession catalogadorAvSession=this.getCatalogadorAvSession(request);
		LomAvanzadoVO auxAvanzado=null;
		ContribucionVO[] contribucionesVO = null;
		IdiomaVO idiomaVO=null;
		
		try{
			auxAvanzado=new LomAvanzadoVO();
			 
			AvMetametadataVO metadatos = new AvMetametadataVO();
			auxAvanzado.setMetaMetadata(metadatos);

			String source=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES);//esquemaDeMetadatos
			String usuario =LdapUserDetailsUtils.getUsuario();
			//String usuario="empaquetador";
			String identificador =request.getParameter("identificador");
			if(identificador==null){
			   identificador=catalogadorAvSession.getIdentificador();
			}
			String returnURL=request.getParameter("returnURL");
			if(returnURL==null)
				returnURL=catalogadorAvSession.getReturnURL();
			
			catalogadorAvSession.setIdioma(this.getCatalogadorAvSession(request).getIdioma());
			catalogadorAvSession.setIdentificador(identificador);
			catalogadorAvSession.setUsuario(usuario);
			//metemos en la sesion el parametro de vuelta al empaquetador
			catalogadorAvSession.setReturnURL(returnURL);
		
		
	    	Object valor = request.getSession().getAttribute("form");
	
	    	//Obtenemos la longitudes de los VOs, que pasamos a cambioFormulario para que se obtengan del request los cambios que hemos hecho
	    	//Pues en el form que nos viene como parametro no los guarda.
	
	    	int longitudIdentificadores=0;
	    	int longitudEsquemas=0;
	    	int longitudDescripciones=0;
	    	int longitudContribuciones=0;
	    	int[] longitudTextosDesc = new int[0];
	    	int[] longitudEntidadesContrib = new int[0];
	    	if (valor instanceof MetaMetadatosFormImpl) {
	    		
	    		MetaMetadatosFormImpl valorMeta = ((MetaMetadatosFormImpl)valor);
	
	    		Object[] desc = valorMeta.getDescripcionesAsArray();
				longitudTextosDesc = new int[desc.length];
				for(int i= 0;i< desc.length;i++){
					longitudTextosDesc[i]=((DescripcionVO)(desc[i])).getTextos().length;
				}
	    		
			  	longitudEntidadesContrib = new int[valorMeta.getContribucion().size()];
				for(int i= 0;i< valorMeta.getContribucion().size();i++){
					longitudEntidadesContrib[i]=((Contribucion)(valorMeta.getContribucionAsArray()[i])).getEntidades().length;
				}
				
			  	longitudIdentificadores=valorMeta.getIdentificadoresAsArray().length;
			  	longitudEsquemas=valorMeta.getEsquemasMetaAsArray().length;
				longitudContribuciones=valorMeta.getContribucion().size();
				longitudDescripciones=desc.length;
			  	
	    	}
	    	else if (valor instanceof MetaMetadatosFormSubmitFormImpl){
	    		MetaMetadatosFormSubmitFormImpl valorMeta = ((MetaMetadatosFormSubmitFormImpl)valor);
	    		Object[] desc = valorMeta.getDescripcionesAsArray();
				longitudTextosDesc = new int[desc.length];
				for(int i= 0;i< desc.length;i++){
					longitudTextosDesc[i]=((DescripcionVO)(desc[i])).getTextos().length;
				}
	    		
			  	longitudEntidadesContrib = new int[valorMeta.getContribucion().size()];
				for(int i= 0;i< valorMeta.getContribucion().size();i++){
					longitudEntidadesContrib[i]=((Contribucion)(valorMeta.getContribucionAsArray()[i])).getEntidades().length;
				}
				
			  	longitudIdentificadores=valorMeta.getIdentificadoresAsArray().length;
			  	longitudEsquemas=valorMeta.getEsquemasMetaAsArray().length;
				longitudContribuciones=valorMeta.getContribucion().size();
				longitudDescripciones=desc.length;
	    	}
	    	else if (valor instanceof MetadatosNoValidoVolverFormImpl){
	    		MetadatosNoValidoVolverFormImpl valorMeta = ((MetadatosNoValidoVolverFormImpl)valor);
	    		Object[] desc = valorMeta.getDescripcionesAsArray();
				longitudTextosDesc = new int[desc.length];
				for(int i= 0;i< desc.length;i++){
					longitudTextosDesc[i]=((DescripcionVO)(desc[i])).getTextos().length;
				}
	    		
			  	longitudEntidadesContrib = new int[valorMeta.getContribucion().size()];
				for(int i= 0;i< valorMeta.getContribucion().size();i++){
					longitudEntidadesContrib[i]=((Contribucion)(valorMeta.getContribucionAsArray()[i])).getEntidades().length;
				}
				
			  	longitudIdentificadores=valorMeta.getIdentificadoresAsArray().length;
			  	longitudEsquemas=valorMeta.getEsquemasMetaAsArray().length;
				longitudContribuciones=valorMeta.getContribucion().size();
				longitudDescripciones=desc.length;
	    	}
	    	else if (valor instanceof MetadatosValidoVolverFormImpl){
	    		MetadatosValidoVolverFormImpl valorMeta = ((MetadatosValidoVolverFormImpl)valor);
	    		Object[] desc = valorMeta.getDescripcionesAsArray();
				longitudTextosDesc = new int[desc.length];
				for(int i= 0;i< desc.length;i++){
					longitudTextosDesc[i]=((DescripcionVO)(desc[i])).getTextos().length;
				}
	    		
			  	longitudEntidadesContrib = new int[valorMeta.getContribucion().size()];
				for(int i= 0;i< valorMeta.getContribucion().size();i++){
					longitudEntidadesContrib[i]=((Contribucion)(valorMeta.getContribucionAsArray()[i])).getEntidades().length;
				}
				
			  	longitudIdentificadores=valorMeta.getIdentificadoresAsArray().length;
			  	longitudEsquemas=valorMeta.getEsquemasMetaAsArray().length;
				longitudContribuciones=valorMeta.getContribucion().size();
				longitudDescripciones=desc.length;
	    	}
	    	
	    	//borramos session
    		request.getSession().removeAttribute(MetametadatoSession.SESSION_KEY);
	    	//Recogemos los cambio del request mediante parametro
		  	cambioFormulario(request,longitudIdentificadores,longitudEsquemas,longitudDescripciones,longitudContribuciones
		  			,longitudTextosDesc,longitudEntidadesContrib);
	    	
		  	//recogemos de session
    	  	MetametadatoSession metametadatoSession = this.getMetametadatoSession(request);
    	  	
	    	//Guardamos los cambios en el form obtenido de request.getSession.getAttribute("form") para que en caso de faltar
		  	//algun idioma o texto sin rellenar cargue el formulario con lo cambio y no perdamos todo lo modificado hasta ese
		  	//momento, si no lo hicieramos cargaría el formulario anterior. 
		  	
			//DESCRIPCIONES fecha
    	  	DescripcionVO[] descripciones = (DescripcionVO[]) metametadatoSession.getDescripciones()
    	  		.toArray(new DescripcionVO[metametadatoSession.getDescripciones().size()]);
	    	DescripcionVO[]	descripcionesAux = new DescripcionVO[descripciones.length];
	    	for(int i=0;i<descripciones.length;i++){
	    		DescripcionVO descripAux= new DescripcionVO();
	    		LangStringVO[] langDescrip = descripciones[i].getTextos();
	    		LangStringVO[] langDescripAux = new LangStringVO[langDescrip.length];
	    		for(int j=0;j<langDescrip.length;j++){
	    			LangStringVO nuevoLang = new LangStringVO();
	    			nuevoLang.setIdioma(langDescrip[j].getIdioma());
	    			nuevoLang.setTexto(langDescrip[j].getTexto());
	    			langDescripAux[j] = nuevoLang;
	    		}
	    		descripAux.setTextos(langDescripAux);
	    		descripcionesAux[i]=descripAux;
	    	}
	    	
	    	//CONTRIBUCIONES
	    	Contribucion[] contribuciones = (Contribucion[])metametadatoSession.getContribuciones()
	    			.toArray(new Contribucion[metametadatoSession.getContribuciones().size()]);
	    	Contribucion[]	contribucionesAux = new Contribucion[contribuciones.length];
	        for(int i=0;i<contribuciones.length;i++){
	        	Contribucion contribucionAux = new Contribucion();
	        	//rol
	        	SourceValueVO rolAux = new SourceValueVO();
	        	rolAux.setValor(contribuciones[i].getRol().getValor());
	        	//entidad
	        	Entidad[] entidades = contribuciones[i].getEntidades() ;
	        	Entidad[] entidadesAux = new Entidad[entidades.length];
	        	for(int j=0;j< entidadesAux.length;j++){
	        		Entidad entidad = new Entidad();
	        		entidad.setCorreo(entidades[j].getCorreo());
	        		entidad.setNombre(entidades[j].getNombre());
	        		entidad.setOrganizacion(entidades[j].getOrganizacion());
	        		entidadesAux[j]=entidad;
	        	}
	        	//fecha
	        	Fecha fechaAux =contribuciones[i].getFecha();

	        	//contribucion
	        	contribucionAux.setRol(rolAux);
	        	contribucionAux.setEntidades(entidadesAux);
	        	contribucionAux.setFecha(fechaAux);
	        	contribucionesAux[i]=contribucionAux;
	        }
		  	
		  	
		  	//IDENTIFICADORES
	        IdentificadorVO[] identificadores = (IdentificadorVO[])metametadatoSession.getIdentificadores()
	        		.toArray(new IdentificadorVO[metametadatoSession.getIdentificadores().size()]);
		  	IdentificadorVO[] identificadoresAux = new IdentificadorVO[identificadores.length];
	    	for(int i=0;i<identificadores.length;i++){
	    		IdentificadorVO auxIdentificador = new IdentificadorVO();
	    		auxIdentificador.setCatalogo(identificadores[i].getCatalogo());
	    		auxIdentificador.setEntrada(identificadores[i].getEntrada());
	    		identificadoresAux[i] = auxIdentificador;
	    	}
		  	//ESQUEMAS
	    	EsquemaDeMetadatosVO[] esquemasMeta = (EsquemaDeMetadatosVO[]) metametadatoSession.getEsquemasMeta()
	    		.toArray(new EsquemaDeMetadatosVO[metametadatoSession.getEsquemasMeta().size()]);
		  	EsquemaDeMetadatosVO[] esquemasMetaAux = new EsquemaDeMetadatosVO[esquemasMeta.length];
	    	for(int i=0;i<esquemasMeta.length;i++){
	    		EsquemaDeMetadatosVO auxEsquema = new EsquemaDeMetadatosVO();
	    		auxEsquema.setTexto(esquemasMeta[i].getTexto());
	    		esquemasMetaAux[i] = auxEsquema;
	    	}
		  	    	
	    	if (valor instanceof MetaMetadatosFormImpl) {
	    		MetaMetadatosFormImpl formulario=(MetaMetadatosFormImpl) valor;
	    		formulario.setIdentificadoresAsArray(identificadoresAux);
	    		formulario.setEsquemasMetaAsArray(esquemasMetaAux);
	    		formulario.setContribucionAsArray(contribucionesAux);
	    		formulario.setDescripcionesAsArray(descripcionesAux);
	    		
			}
	    	else if (valor instanceof MetaMetadatosFormSubmitFormImpl) {
	    		MetaMetadatosFormSubmitFormImpl formulario=(MetaMetadatosFormSubmitFormImpl) valor;
	    		formulario.setIdentificadoresAsArray(identificadoresAux);
	    		formulario.setEsquemasMetaAsArray(esquemasMetaAux);
	    		formulario.setContribucionAsArray(contribucionesAux);
	    		formulario.setDescripcionesAsArray(descripcionesAux);
	      	}
	    	else if (valor instanceof MetadatosNoValidoVolverFormImpl) {
	    		MetadatosNoValidoVolverFormImpl formulario=(MetadatosNoValidoVolverFormImpl) valor;
	    		formulario.setIdentificadoresAsArray(identificadoresAux);
	    		formulario.setEsquemasMetaAsArray(esquemasMetaAux);
	    		formulario.setContribucionAsArray(contribucionesAux);
	    		formulario.setDescripcionesAsArray(descripcionesAux);
	      	}
	    	else if (valor instanceof MetadatosValidoVolverFormImpl) {
	    		MetadatosValidoVolverFormImpl formulario=(MetadatosValidoVolverFormImpl) valor;
	    		formulario.setIdentificadoresAsArray(identificadoresAux);
	    		formulario.setEsquemasMetaAsArray(esquemasMetaAux);
	    		formulario.setContribucionAsArray(contribucionesAux);
	    		formulario.setDescripcionesAsArray(descripcionesAux);
	      	}
	
			//idioma = form.getComboIdiomaMeta();
	    	//ojo con este!!!!!
	    	metametadatoSession.setIdioma(form.getComboIdiomaMeta());
		  	
			//Obtnemos los terminos de los ids seleccionados en todos los combos, y los guarda en los VO 
			dameTerminoId(metametadatoSession);         
			 
			cargarAyuda(metametadatoSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
			
			//Comprobamos si los campos ha sido rellenados adecuadamente
			
			
			//IDENTIFICADORES		 
			if (identificadores!= null && identificadores.length != 0){
				ArrayList lista=new ArrayList();
				for(int j=0;j<identificadores.length;j++){
					IdentificadorVO ident=identificadores[j];
						 
					if(ident !=null){
						IdentificadorVO identAux=new IdentificadorVO();
						String catalogo=ident.getCatalogo();
						String entrada=ident.getEntrada();
						if(((catalogo!=null)&&(!catalogo.trim().equals(""))) || ((entrada!=null)&&(!entrada.trim().equals("")))){
							identAux.setCatalogo(catalogo.trim());
							identAux.setEntrada(entrada.trim());
							lista.add(identAux);
						}
					}						 						 
				}
				identificadores=(IdentificadorVO[])lista.toArray(new IdentificadorVO[lista.size()]);
				if(identificadores.length == 0)
					identificadores = null; 
			}
			else 
				identificadores = null;
			 
			auxAvanzado.getMetaMetadata().setIdentificadores(identificadores);
			
			//ESQUEMAS		 
			if (esquemasMeta!= null && esquemasMeta.length != 0){
				ArrayList lista=new ArrayList();
				for(int j=0;j<esquemasMeta.length;j++){
					EsquemaDeMetadatosVO esq=esquemasMeta[j];
						 
					if(esq !=null){
						EsquemaDeMetadatosVO esqAux=new EsquemaDeMetadatosVO();
						String texto=esq.getTexto();
						if(((texto!=null)&&(!texto.trim().equals("")))){
							esqAux.setTexto(texto.trim());
							lista.add(esqAux);
						}
					}						 						 
				}
				esquemasMeta=(EsquemaDeMetadatosVO[])lista.toArray(new EsquemaDeMetadatosVO[lista.size()]);
				if(esquemasMeta.length == 0)
					esquemasMeta = null; 
			}
			else 
				esquemasMeta = null;
			 
			auxAvanzado.getMetaMetadata().setEsquemas(esquemasMeta);  
			
			//IDIOMA
			idiomaVO=new IdiomaVO();
			if((metametadatoSession.getIdioma()!=null)&&(!metametadatoSession.getIdioma().equals(""))){
				idiomaVO.setTexto(metametadatoSession.getIdioma());
			}
			else{
				idiomaVO = null;
			}
			auxAvanzado.getMetaMetadata().setIdioma(idiomaVO);
			
			
			//CONTRIBUCIONES
			contribucionesVO = null;
			//actualizamos de nuevo las contribuciones despues de dameTerminoId
			contribuciones = (Contribucion[])metametadatoSession.getContribuciones()
				.toArray(new Contribucion[metametadatoSession.getContribuciones().size()]);
			if (contribuciones!= null && contribuciones.length != 0){
				contribucionesVO = new ContribucionVO[contribuciones.length];
				ArrayList listaContribuciones=new ArrayList();
				for(int k=0;k<contribuciones.length;k++){
					ContribucionVO contribucionVO = new ContribucionVO();
					//FECHA
					FechaVO fechaVO = new FechaVO();
					//Descripciones
					/**********************************************/
					//actualizamos de nuevo las desc despues de dameTerminoId
					descripciones = (DescripcionVO[]) metametadatoSession.getDescripciones()
	    	  			.toArray(new DescripcionVO[metametadatoSession.getDescripciones().size()]);
					if(descripciones!=null && descripciones.length != 0){
						if(descripciones[k]!=null){
							ArrayList listDesc=new ArrayList();
							DescripcionVO lDesc=descripciones[k];
							DescripcionVO lDescripciones=new DescripcionVO();
							for(int j=0;j<lDesc.getTextos().length;j++){
								LangStringVO lang=lDesc.getTextos()[j];
								LangStringVO lLang=new LangStringVO();
								String idioma=lang.getIdioma();
								String texto=lang.getTexto();
								if(((idioma!=null)&&(!idioma.trim().equals(""))) && ((texto!=null)&&(!texto.trim().equals("")))){
									lLang.setIdioma(idioma.trim());
									lLang.setTexto(texto.trim());
									listDesc.add(lLang);
								}
								else{
									if (idioma.equals("") && !texto.trim().equals("") )
										errorFaltaIdioma = true;
									if (!idioma.equals("") && texto.trim().equals("") )
										errorFaltaTexto = true;
								} 
							}
							
							if (listDesc.size() != 0){
								LangStringVO[] langs=(LangStringVO[])listDesc.toArray(new LangStringVO[listDesc.size()]); 
								lDescripciones.setTextos(langs); 
								fechaVO.setDescripcion(lDescripciones);
							}else{//meter una vacia???	
								fechaVO.setDescripcion(null);
							}
						}
						
					}
					/**********************************************/
					//fecha
				/**********************************************/
				// es obl al menos yyyy-MM-dd

				
					Fecha fechaAux = contribuciones[k].getFecha();
					Integer[] errores= fechaAux.validar();
					if(errores.length>0)
					{
						for (int i = 0; i < errores.length; i++) {
							if(errores[i].equals(Fecha.ErrorNoNumero) && !errorNoNumero)
									errorNoNumero= true;
							else if(errores[i].equals(Fecha.ErrorFechaFueraRango)&& !errorFechaFueraRango)
								errorFechaFueraRango= true;
							else if(errores[i].equals(Fecha.ErrorZHFueraRango) && !errorZHFueraRango)
								errorZHFueraRango= true;
							else if(errores[i].equals(Fecha.ErrorFechaComboZH) && !errorFechaComboZH)
								errorFechaComboZH= true;
							else if(errores[i].equals(Fecha.ErrorFechaZonaHoraria) && !errorFechaZonaHoraria)
								errorFechaZonaHoraria= true;
							else if(errores[i].equals(Fecha.ErrorFechaObliHorMin) && !errorFechaObliHorMin)
								errorFechaObliHorMin= true;
							else if(errores[i].equals(Fecha.ErrorFechaObli) && !errorFechaObli)
								errorFechaObli= true;
							else if(errores[i].equals(Fecha.ErrorFormatoFecha) && !errorFormatoFecha)
								errorFormatoFecha= true;
						}
					}

					if(errores.length==0 )
					{
						fechaVO.setFecha(fechaAux.getFechaLomes());
					}else
					{
						fechaVO.setFecha(null);
					}
					
					if(fechaVO.getDescripcion()==null && fechaVO.getFecha()==null )
						fechaVO=null;
					
					contribucionVO.setFecha(fechaVO);
						
					/**********************************************/
					//rol	
					/**********************************************/
					SourceValueVO rolVO=new SourceValueVO();
					String rol = contribuciones[k].getRol().getValor();
					if((rol!=null)&&(!rol.equals(""))){
						rolVO.setSource(source);
						rolVO.setValor(rol);
					}
					else{
						rolVO = null;
					}
					contribucionVO.setTipo(rolVO);
					/**********************************************/
					//entidad
					/**********************************************/
					EntidadVO[] entidadesVO = null;
					Entidad[] entidades = contribuciones[k].getEntidades();
					ArrayList listaEntidades= new ArrayList();
					for (int i=0;i<entidades.length;i++){
						Entidad entidad = entidades[i];
						EntidadVO entidadVO = new EntidadVO();
						String textoEnt="";
						if (!entidad.getOrganizacion().trim().equals("") || !entidad.getCorreo().trim().equals("") || !entidad.getNombre().trim().equals("")){
							if(!entidad.getCorreo().trim().equals("")){
								if(validaEmail(entidad.getCorreo())){	
									textoEnt = new StringBuffer("BEGIN:VCARD VERSION:3.0 FN:").append(entidad.getNombre().trim())
													  .append(" EMAIL;TYPE=INTERNET:").append(entidad.getCorreo().trim())
													  .append(" ORG:").append(entidad.getOrganizacion().trim()).append(" END:VCARD").toString();
									entidadVO.setTexto(textoEnt);
									listaEntidades.add(entidadVO);
								}
								else errorEmail=true;}
							else{
								textoEnt = new StringBuffer("BEGIN:VCARD VERSION:3.0 FN:").append(entidad.getNombre().trim())
													.append(" EMAIL;TYPE=INTERNET:").append(entidad.getCorreo().trim())
													.append(" ORG:").append(entidad.getOrganizacion().trim()).append(" END:VCARD").toString();
								entidadVO.setTexto(textoEnt);
								listaEntidades.add(entidadVO);
							}
						}
					}
					if (listaEntidades.size() == 0)
						entidadesVO = null;
					else
						entidadesVO=(EntidadVO[])listaEntidades.toArray(new EntidadVO[listaEntidades.size()]);
					contribucionVO.setEntidades(entidadesVO);
					/**********************************************/
					
					//añado contribucionVO si tiene algo
					if (contribucionVO.getEntidades() != null || contribucionVO.getFecha() != null || contribucionVO.getTipo() != null)
						listaContribuciones.add(contribucionVO);
					//contribucionesVO[k]=contribucionVO;
				}
				if (listaContribuciones.size() == 0)
					contribucionesVO = null;
				else
					contribucionesVO=(ContribucionVO[])listaContribuciones.toArray(new ContribucionVO[listaContribuciones.size()]);
			}
			else{
				contribucionesVO =null;
			}
			auxAvanzado.getMetaMetadata().setContribuciones(contribucionesVO);
		
		}catch (Exception e) {
			logger
			.error("Error en catalogadorWeb verMetadatos, metodo guardarMetametadatos " + e);
			//throw new Exception("ver.metadatos", e);
			saveErrorMessage(request, " Error en catalogadorWeb verMetadatos, metodo guardarMetametadatos ");
        }
		
		if(!errorFaltaIdioma && !errorFaltaTexto  && !errorFechaObli && !errorFechaObliHorMin && !errorFechaZonaHoraria && !errorFechaComboZH
				&& !errorEmail && !errorNoNumero && !errorFechaFueraRango && !errorZHFueraRango && !errorFormatoFecha){					
			  try{
				// Cargamos el objeto de sesion
				if(catalogadorAvSession.getMDSesion()==null){
					catalogadorAvSession.setMDSesion(auxAvanzado);		
				}else{
//					MetametadatoSession metametadatoSession = this.getMetametadatoSession(request);
//					EsquemaDeMetadatosVO[] esquemasMeta = (EsquemaDeMetadatosVO[]) metametadatoSession.getEsquemasMeta()
//		    			.toArray(new EsquemaDeMetadatosVO[metametadatoSession.getEsquemasMeta().size()]);
//					IdentificadorVO[] identificadores = (IdentificadorVO[])metametadatoSession.getIdentificadores()
//	        			.toArray(new IdentificadorVO[metametadatoSession.getIdentificadores().size()]);
					AvMetametadataVO metametadatoSession = auxAvanzado.getMetaMetadata();
					EsquemaDeMetadatosVO[] esquemasMeta = metametadatoSession.getEsquemas();
				IdentificadorVO[] identificadores = metametadatoSession.getIdentificadores();
			       if (contribucionesVO == null && esquemasMeta == null && identificadores == null && idiomaVO == null)
			    	   catalogadorAvSession.getMDSesion().setMetaMetadata(null);
					else
						catalogadorAvSession.getMDSesion().setMetaMetadata(auxAvanzado.getMetaMetadata());
				}
			  }catch (Exception e) {
					logger
					.error("Error en catalogadorWeb verMetadatos, metodo guardarMetametadatos " + e);
					//throw new Exception("ver.metadatos", e);
					saveErrorMessage(request, " Error en catalogadorWeb verMetadatos, metodo guardarMetametadatos ");
		        }

		}else{
			if (errorFormatoFecha)
				throw new ValidatorException("{error.FormatoFecha}");
			else if (errorNoNumero)
				throw new ValidatorException("{error.NoNumero}");
			else if(errorFechaFueraRango)
				throw new ValidatorException("{error.fechaFueraRango}");
			else if(errorFechaObli)
				throw new ValidatorException("{error.fechaIncompleta}");
			else if (errorFechaObliHorMin)
				throw new ValidatorException("{error.fechaIncompletaHorMin}");
			else if (errorFechaZonaHoraria)
				throw new ValidatorException("{error.fechaIncompletaZonaHora}");
			else if (errorFechaComboZH)
				throw new ValidatorException("{error.fechaSelecionaCombo}");
			else if (errorZHFueraRango)
				throw new ValidatorException("{error.ZonaHoraria}");
			else if (errorEmail)
				throw new ValidatorException("{error.email}");
			else if (errorFaltaIdioma && errorFaltaTexto)
				throw new ValidatorException("{general.error.idioma_texto}");
			else if (!errorFaltaIdioma && errorFaltaTexto)
				throw new ValidatorException("{general.error.texto}");
			else 
				throw new ValidatorException("{general.error.idioma}");

		}

 
     }
    
    
    private void cambioFormulario(HttpServletRequest request,int longitudIdentificadores,int longitudEsquemas,int longitudDescripciones,
    		int longitudContribuciones	,int[] longitudTextosDesc,int[] longitudEntidadesContrib) throws Exception{
    	IdentificadorVO[] identificadores=new IdentificadorVO[longitudIdentificadores];
    	EsquemaDeMetadatosVO[] esquemasMeta=new EsquemaDeMetadatosVO[longitudEsquemas];
    	DescripcionVO[] descripciones = new DescripcionVO[longitudDescripciones];
    	Contribucion[] contribuciones = new Contribucion[longitudContribuciones];
	  	String[] catalogos = new String[longitudIdentificadores];
	  	String[] entradas = new String[longitudIdentificadores];
	  	String[] esquemas = new String[longitudEsquemas];
	  	
      	ArrayList[] textoDescripciones = new ArrayList[longitudDescripciones];
      	ArrayList[] idiomaDescripciones = new ArrayList[longitudDescripciones];
      	ArrayList[] nombresEntidades = new ArrayList[longitudContribuciones];
      	ArrayList[] orgsEntidades = new ArrayList[longitudContribuciones];
      	ArrayList[] correoEntidades = new ArrayList[longitudContribuciones];
	  	String[] fechasCorta = new String[longitudContribuciones];
//      	String[] anyos = new String[longitudContribuciones];
//      	String[] meses = new String[longitudContribuciones];
      	String[] horas = new String[longitudContribuciones];
      	String[] minutos = new String[longitudContribuciones];
      	String[] segundos1 = new String[longitudContribuciones];
      	String[] segundos2 = new String[longitudContribuciones];
      	String[] zhHoras = new String[longitudContribuciones];
      	String[] zhMinutos = new String[longitudContribuciones];
      	String[] roles = new String[longitudContribuciones];
      	String[] zonaH = new String[longitudContribuciones];
      	String[] meridianosCero= new String[longitudContribuciones];
      	
      	MetametadatoSession metametadatoSession = this.getMetametadatoSession(request);
      	
           for (Enumeration names = request.getParameterNames(); names.hasMoreElements();)
           {
          	 String name = String.valueOf(names.nextElement());
       		if (name.startsWith("Cont")){//descripciones       	 
	          	 String[] namePartido = name.split("_");
	          	 int i = Integer.parseInt(namePartido[0].substring(4, namePartido[0].length()));
	          	 //Descripciones fechas
	          	 if (namePartido[1].startsWith("DesFecTex")){
	          		 int j = Integer.parseInt(namePartido[1].substring(9, namePartido[1].length()));
	          		 ArrayList lDesc = textoDescripciones[i];
	          		 if(lDesc == null){
	          			 lDesc= new ArrayList();
	          			 for (int k=0;k<longitudTextosDesc[i];k++)
	          				 lDesc.add("");
	          		 }
	          		 
	          		 lDesc.set(j, request.getParameter(name));
	          		 textoDescripciones[i]=lDesc;
	          	 }
	          	 else if (namePartido[1].startsWith("DesFecIdio")){//Idio
	          		 int j = Integer.parseInt(namePartido[1].substring(10, namePartido[1].length()));
	          		 ArrayList lDesc = idiomaDescripciones[i];
	          		 if(lDesc == null){
	          			 lDesc= new ArrayList();
	          			 for (int k=0;k<longitudTextosDesc[i];k++)
	          				 lDesc.add("");
	          		 }
	          		 
	          		 lDesc.set(j, request.getParameter(name));
	          		 idiomaDescripciones[i]=lDesc;
	          	 }
	          	 // partes de la fecha
	          	 else if (namePartido[1].startsWith("FechaCorta"))
			      	 fechasCorta[i]=request.getParameter(name);
	          	 else if (namePartido[1].startsWith("FechaHora"))
			      	 horas[i]=request.getParameter(name);
	          	 else if (namePartido[1].startsWith("FechaMin"))
			      	 minutos[i]=request.getParameter(name);
	          	 else if (namePartido[1].startsWith("FechaSeg1"))
			      	 segundos1[i]=request.getParameter(name);	          	 
	          	 else if (namePartido[1].startsWith("FechaSeg2"))
			      	 segundos2[i]=request.getParameter(name);
	          	 else if (namePartido[1].startsWith("FechaZHHora"))
			      	 zhHoras[i]=request.getParameter(name);
	          	 else if (namePartido[1].startsWith("FechaZHMinutos"))
			      	 zhMinutos[i]=request.getParameter(name);
	          	 else if (namePartido[1].startsWith("FechaZonaH"))
			      	 zonaH[i]=request.getParameter(name);
	          	 else if (namePartido[1].startsWith("Rol"))
	          		 roles[i]=request.getParameter(name);
	          	 //entidades
	          	 else if (namePartido[1].startsWith("Ent")){
	          		 int j = Integer.parseInt(namePartido[1].substring(3, namePartido[1].length()));
	          		 if(namePartido[2].startsWith("Nom")){
	              		 ArrayList lNom = nombresEntidades[i];
		          		 if(lNom == null){
		          			lNom= new ArrayList();
		          			 for (int k=0;k<longitudEntidadesContrib[i];k++)
		          				lNom.add("");
		          		 }
		          		 
		          		lNom.set(j, request.getParameter(name));
		          		nombresEntidades[i]=lNom;
	          		 }
	          		 if(namePartido[2].startsWith("Org")){
	              		 ArrayList lOrg = orgsEntidades[i];
		          		 if(lOrg == null){
		          			lOrg= new ArrayList();
		          			 for (int k=0;k<longitudEntidadesContrib[i];k++)
		          				lOrg.add("");
		          		 }
		          		 
		          		lOrg.set(j, request.getParameter(name));
		          		orgsEntidades[i]=lOrg;
	          		 }
	          		 if(namePartido[2].startsWith("Cor")){
	              		 ArrayList lCor = correoEntidades[i];
		          		 if(lCor == null){
		          			lCor= new ArrayList();
		          			 for (int k=0;k<longitudEntidadesContrib[i];k++)
		          				lCor.add("");
		          		 }
		          		 
		          		lCor.set(j, request.getParameter(name));
		          		correoEntidades[i]=lCor;
	          		 }
	
	          	 }
	          	else if (namePartido[1].startsWith("meridianoCero")){
	          		meridianosCero[i]=request.getParameter(name);
	          	}
    		}
          	 
          	 if(name.startsWith("Id")){//identificadores
          		 if(name.startsWith("IdCat")){
          			 int i = Integer.parseInt(name.substring(5, name.length()));
          			 catalogos[i]=request.getParameter(name);
          		 }	  
          		 if (name.startsWith("IdEnt")){
          			 int i = Integer.parseInt(name.substring(5, name.length()));
          			 entradas[i]=request.getParameter(name);
          		 }
          	 }
          	 if(name.startsWith("Esq")){//esquemas
          		 int i = Integer.parseInt(name.substring(3, name.length()));
          		 esquemas[i]=request.getParameter(name);
          	 } 
           }
           
           //identificadores
           for (int i = 0; i<catalogos.length;i++){
          	 IdentificadorVO idVO = new IdentificadorVO();
          	 idVO.setCatalogo(catalogos[i]);
          	 idVO.setEntrada(entradas[i]); 
          	 identificadores[i] = idVO;
           }
           //metemos en session identificadores
           metametadatoSession.setIdentificadores(Arrays.asList(identificadores));
           //esquemas
           for (int i = 0; i<esquemas.length;i++){
            	 EsquemaDeMetadatosVO esqVO = new EsquemaDeMetadatosVO();
            	 esqVO.setTexto(esquemas[i]);
            	 esquemasMeta[i] = esqVO;
           }
           //metemos en session los esquemas
           metametadatoSession.setEsquemasMeta(Arrays.asList(esquemasMeta));
           
           //descripciones
           for(int i=0;i<textoDescripciones.length;i++){
           	DescripcionVO descVO = new DescripcionVO();
           	if (textoDescripciones[i]!=null) {
	           	LangStringVO[] aLangDesc = new LangStringVO[textoDescripciones[i].size()];
	           	for (int j = 0; j<textoDescripciones[i].size();j++){
	           		LangStringVO langDesc= new LangStringVO();
	           		langDesc.setTexto(textoDescripciones[i].get(j).toString());
	           		langDesc.setIdioma(idiomaDescripciones[i].get(j).toString());
	           		aLangDesc[j] = langDesc;
	           	}
	           	descVO.setTextos(aLangDesc);
           	}else {
     	  		 LangStringVO[] aLangString=new LangStringVO[1];
     	  		 LangStringVO langString = new LangStringVO();
     	  		 langString.setIdioma("");
     	  		 langString.setTexto("");
     	  		 aLangString[0]= langString;
     	  		 descVO.setTextos(aLangString);
     	  	 }
           	descripciones[i]=descVO;     
          }
          //metemos en session las descripciones
           metametadatoSession.setDescripciones(Arrays.asList(descripciones));
           
           //contribuciones
           for(int i=0;i<longitudContribuciones;i++){
				Contribucion contribucionAux = new Contribucion();
				//rol
				SourceValueVO rolAux = new SourceValueVO();
				rolAux.setValor(roles[i]);
				//entidad
				Entidad[] entidadesAux = new Entidad[longitudEntidadesContrib[i]];
				for(int j=0;j< entidadesAux.length;j++){
					Entidad entidad = new Entidad();
	        		String correoEntIJ="";
	        		if ((correoEntidades[i]!=null) && (correoEntidades[i].get(j))!=null) {
	        			correoEntIJ=correoEntidades[i].get(j).toString();
	        		}
	        		entidad.setCorreo(correoEntIJ);
	        		String nombresEntIJ="";
	        		if ((nombresEntidades[i]!=null) && (nombresEntidades[i].get(j)!=null)) {
	        			nombresEntIJ=nombresEntidades[i].get(j).toString();
	        		}
	        		entidad.setNombre(nombresEntIJ);
	        		String orgsEntIJ="";
	        		if ((orgsEntidades[i]!=null) && (orgsEntidades[i].get(j)!=null)) {
	        			orgsEntIJ=orgsEntidades[i].get(j).toString();
	        		}
	        		entidad.setOrganizacion(orgsEntIJ);
					entidadesAux[j]=entidad;
				}
				//fecha
				Fecha fechaAux = new Fecha();
				fechaAux.setIdioma(this.getCatalogadorAvSession(request).getIdioma());
				ResourceBundle datosResources = I18n.getInstance().getResource("application-resources", (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
				String fechaapplicaton=datosResources.getString("catalogadorAvanzado.formatoFecha");        	
	        	fechaAux.setFechaCorta(fechasCorta[i].equals(fechaapplicaton)?"":fechasCorta[i]);
				fechaAux.setHora(horas[i]);
				fechaAux.setMinutos(minutos[i]);
				fechaAux.setSegundoP1(segundos1[i]);
				fechaAux.setSegundoP2(segundos2[i]);
	        	fechaAux.setZhHora(zhHoras[i]==null?"":zhHoras[i]);
	        	fechaAux.setZhMinutos(zhMinutos[i]==null?"":zhMinutos[i]);
	        	fechaAux.setMasOmenos(zonaH[i]==null?"":zonaH[i]);
				fechaAux.setMeridianoCero(meridianosCero[i]);	
				//contribucion
				contribucionAux.setRol(rolAux);
				contribucionAux.setEntidades(entidadesAux);
				contribucionAux.setFecha(fechaAux);
				contribuciones[i]=contribucionAux;
           }
           //metemos en session las contribuciones
           metametadatoSession.setContribuciones(Arrays.asList(contribuciones));
           
        }
    	
    private void dameTerminoId(MetametadatoSession metametadatoSession) throws Exception{
        ArrayList ids = new ArrayList();
        ids.add(metametadatoSession.getIdioma());
        
        if (metametadatoSession.getIdioma()!=null && !metametadatoSession.getIdioma().equals("")){
        	String[] arrayIdsEstructuraYNivel = (String[])ids.toArray(new String[ids.size()]);
            TerminoYPadreVO[] terminosTraducEstructuraYNivel = this.getSrvVocabulariosControladosService().crearTraduccionAIngles(arrayIdsEstructuraYNivel);
            metametadatoSession.setIdioma(terminosTraducEstructuraYNivel[0].getNomTermino());

        }
        
	    //CONTRIBUCION
	    ArrayList idsRol = new ArrayList();
	    //sacamos las contribuciones de la session y luego las actualizamos
	    Contribucion[] contribuciones = (Contribucion[])metametadatoSession.getContribuciones()
			.toArray(new Contribucion[metametadatoSession.getContribuciones().size()]);
	    for (int i = 0;i<contribuciones.length;i++){
	    	idsRol.add(contribuciones[i].getRol().getValor());
	    }
	    
        String[] arrayIdsRol = (String[])idsRol.toArray(new String[idsRol.size()]);
        TerminoYPadreVO[] terminosTraducRol = this.getSrvVocabulariosControladosService().crearTraduccionAIngles(arrayIdsRol);
	    
        int contC =0;
        for (int i = 0;i<contribuciones.length;i++){
			if (!contribuciones[i].getRol().getValor().equals("")){
				contribuciones[i].getRol().setValor(terminosTraducRol[contC].getNomTermino());
				contC++;
			}
        }
        //ya estan actualizadas, ahora en session
        metametadatoSession.setContribuciones(Arrays.asList(contribuciones));
        
	    //descripciones de la fecha
		ArrayList idsDesc = new ArrayList();
	    
		DescripcionVO[] descripciones = (DescripcionVO[]) metametadatoSession.getDescripciones()
  			.toArray(new DescripcionVO[metametadatoSession.getDescripciones().size()]);
	    for ( int i=0;i<descripciones.length;i++ ){
	    	LangStringVO[] langDescripcion = descripciones[i].getTextos();
	    	idsDesc.clear();
	    	for ( int j=0;j<langDescripcion.length;j++){
		    	idsDesc.add(langDescripcion[j].getIdioma());
	    	}
		        String[] arrayIdsDesc = (String[])idsDesc.toArray(new String[idsDesc.size()]);
		        TerminoYPadreVO[] terminosTraducDesc = this.getSrvVocabulariosControladosService().crearTraduccionAIngles(arrayIdsDesc);
		        
		        int jDesc= 0;
		        for (int cont=0;cont<terminosTraducDesc.length;cont++){//Cambiado el identificador del termino al nombre del termino en ingles
		        	
		    		boolean enc=false; 		
		    		while(!enc && jDesc<langDescripcion.length){
		    			if (langDescripcion[jDesc].getIdioma().equals("")){
		    				jDesc++; 
		    			}
		    			else{
		    				enc = true;
		    				langDescripcion[jDesc].setIdioma(terminosTraducDesc[cont].getNomTermino());
		    				jDesc++;
		    			}
		    		}	
		        }
	        
	        descripciones[i].setTextos(langDescripcion);
	    }
	    //actualizo ahora en session las descripciones
	    metametadatoSession.setDescripciones(Arrays.asList(descripciones));
        
    }

	
    private boolean validaEmail(String email){
        boolean resultado = true;

        Pattern pattern = Pattern.compile("^[A-Za-z0-9](\\.?[A-Za-z0-9_-])*@(([A-Za-z0-9](([A-Za-z0-9-])+)\\.){1,2})([A-Za-z0-9]{2,4})(\\.[A-Za-z0-9]{2,4})*$");

        Matcher matcher = pattern.matcher(email.trim());

        if (!matcher.find()) {
              resultado = false;
        }

        return resultado;
  }

	public void cancelar(ActionMapping mapping, CancelarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// nada
		
	}


	public void cargarMetaMetadataValidar(
			ActionMapping mapping, 
			CargarMetaMetadataValidarForm form, 
			HttpServletRequest request, 
			HttpServletResponse response) 
	throws Exception 
	{

		InputStream is = null;
    	Properties prop = new Properties();
		boolean bandera=false;
		
        try{
        	int longVocabulario = 0;
        	CatalogadorAvSession catalogadorAvSession=this.getCatalogadorAvSession(request);
        	is = this.getClass().getResourceAsStream("/catalogadorAvanzado.properties");
			prop.load(is);
			String idiomaLdap=this.getCatalogadorAvSession(request).getIdioma();

			
			//comprobamos que el objeto de sesion no se ha null, si es null nos creamos uno	
			if (catalogadorAvSession.getMDSesion() == null){
				LomAvanzadoVO mdSession = new LomAvanzadoVO();
				catalogadorAvSession.setMDSesion(mdSession);
			}	
			//comprobamos que metaMetadata no se ha null, si es null nos creamos uno
			AvMetametadataVO metadatos = catalogadorAvSession.getMDSesion().getMetaMetadata();
			boolean metaMetadataEsNull = false;
			if (catalogadorAvSession.getMDSesion().getMetaMetadata() == null){
				metadatos = new AvMetametadataVO();
				catalogadorAvSession.getMDSesion().setMetaMetadata(metadatos);
				metaMetadataEsNull=true;
			}	
		
			//Carga del formulario (ahora desde el objeto de session)(Los combos y lo que debe pintarse)
			String[] l_id = { prop.getProperty("idiomaDestinatario"),prop.getProperty("rolMetadatos")};
			VocabularioVO[] vocabulario = this.getSrvVocabulariosControladosService().obtenerCombos(l_id,idiomaLdap);
			//vamos a ordenar vocabulario
			UtilidadesOrdenarCombos vocabulariosOrdDest2 = new UtilidadesOrdenarCombos();
			VocabularioVO[] vocabularioOrdenado = vocabulariosOrdDest2.ordenarVocabulariosVO(vocabulario);			

			longVocabulario = vocabularioOrdenado.length;//Cargamos los combos de idioma, la estructura y el nivel de agregacion
			TerminoYPadreVO terminoypadreVO = new TerminoYPadreVO();
			ArrayList terminoypadrear=new ArrayList();

			Collection idDest = new ArrayList();
			
			for (int x = 0; x < longVocabulario; x++) {
				TerminoVO terminoVO = new TerminoVO();
				terminoVO.setIdiomaTermino("");
				terminoVO.setIdTermino("");
				terminoVO.setNomTermino("");

				switch (x) {
					case 0://IDIOMADEST
						idDest.add(terminoVO);
	//					modificamos las cadenas de idiomas
						TerminoVO[] terminosDest = vocabularioOrdenado[x].getTerminos();
						for (int li=0; li<terminosDest.length;li++){
							TerminoVO idAux = new TerminoVO();
							idAux=terminosDest[li];
							String textoIdioma= idAux.getNomTermino();
							String idiomasTra=I18n.getInstance().obtenerIdiomaTraducido(textoIdioma, idiomaLdap);
							idAux.setNomTermino(idiomasTra);
						}
						UtilidadesOrdenarCombos terminosOrdDest2 = new UtilidadesOrdenarCombos();
						TerminoVO[] terminosOrdDest = terminosOrdDest2.ordenarTerminosVO(terminosDest, idiomaLdap);						
						Collection idDest2 = Arrays.asList(terminosOrdDest);
						
						Iterator zDest = idDest2.iterator();
						while (zDest.hasNext()) {
							idDest.add(zDest.next());
						}
						form.setComboIdiomaMetaBackingList(idDest, "idTermino", "nomTermino");
						form.setComboIdiomaBackingList(idDest, "idTermino", "nomTermino");
						if ((metadatos != null)&&(metadatos.getIdioma() !=null)) {
							IdiomaVO idiomasVO =metadatos.getIdioma();
							terminoypadreVO = new TerminoYPadreVO();
							terminoypadreVO.setIdiomaTermino("en");
							terminoypadreVO.setIdVocabulario(l_id[x]);
							terminoypadreVO.setIdTermino("");
							terminoypadreVO.setNomTermino(idiomasVO.getTexto());
							terminoypadrear.add(terminoypadreVO);
						}
						else {
							terminoypadreVO = new TerminoYPadreVO();
							terminoypadreVO.setIdiomaTermino("en");
							terminoypadreVO.setIdVocabulario(l_id[x]);
							terminoypadreVO.setIdTermino("");
							terminoypadreVO.setNomTermino("");
							terminoypadrear.add(terminoypadreVO);
						}	
						break;
						
					case 1://ROL
						Collection tRol = new ArrayList();
						tRol.add(terminoVO);
						Collection tRolAux = Arrays.asList(vocabularioOrdenado[x].getTerminos());
						Iterator cont = tRolAux.iterator();
						while (cont.hasNext()) {
							tRol.add(cont.next());
						}
						form.setCrolBackingList(tRol, "idTermino", "nomTermino");
						if ((metadatos != null)&&(metadatos.getContribuciones() !=null)
								&&(metadatos.getContribuciones().length !=0)) {
							ContribucionVO[] contribucionesVO =metadatos.getContribuciones();
							for(int j=0;j<contribucionesVO.length;j++){
								terminoypadreVO = new TerminoYPadreVO();
								terminoypadreVO.setIdiomaTermino("en");
								terminoypadreVO.setIdVocabulario(l_id[x]);
								terminoypadreVO.setIdTermino("");
								if (contribucionesVO!= null && contribucionesVO[j].getTipo()!=null)
									terminoypadreVO.setNomTermino(contribucionesVO[j].getTipo().getValor());
								else
									terminoypadreVO.setNomTermino("");
								terminoypadrear.add(terminoypadreVO);
							}//terminoYPadreVO rellenado con los idiomas desde el lom avanzado
						}
						else {
							terminoypadreVO = new TerminoYPadreVO();
							terminoypadreVO.setIdiomaTermino("en");
							terminoypadreVO.setIdVocabulario(l_id[x]);
							terminoypadreVO.setIdTermino("");
							terminoypadreVO.setNomTermino("");
							terminoypadrear.add(terminoypadreVO);
						}
						
						break;

				}
				

			}
			if( logger.isDebugEnabled() ){

                logger.debug("Cargados los combos del formulario" );
			}

			logger.debug("Cargados los combos");
			
			//recogemos la session
			MetametadatoSession metametadatoSession = this.getMetametadatoSession(request);
			//form.setIdentificadoresAsArray(identificadores);
			form.setIdentificadoresAsArray(metametadatoSession.getIdentificadores().toArray());
			//form.setEsquemasMetaAsArray(esquemasMeta);
			form.setEsquemasMetaAsArray(metametadatoSession.getEsquemasMeta().toArray());
			//form.setContribucionAsArray(contribuciones);
			form.setContribucionAsArray(metametadatoSession.getContribuciones().toArray());
			//form.setDescripcionesAsArray(descripciones);
			form.setDescripcionesAsArray(metametadatoSession.getDescripciones().toArray());
			//form.setComboIdiomaMeta(idioma);
			form.setComboIdiomaMeta(metametadatoSession.getIdioma());

			cargarAyuda(metametadatoSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
			
	        //si metaMetadata era null en el objeto de session lo dejamos a null
            if (metaMetadataEsNull)
            	catalogadorAvSession.getMDSesion().setMetaMetadata(null);
			
        }catch (Exception e) {
			logger
			.error("Error en catalogadorWeb verMetadatos, metodo cargarMetaMetadataValidar " + e);
			//throw new Exception("ver.metadatos", e);
			saveErrorMessage(request, " Error en catalogadorWeb verMetadatos, metodo cargarMetaMetadataValidar ");
        }finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
        }
		

		
	}


	public boolean esValidoMetaMetadata(
			ActionMapping mapping, 
			EsValidoMetaMetadataForm form, 
			HttpServletRequest request, 
			HttpServletResponse response) 
	throws Exception 
	{
		InputStream is = null;
		Properties prop = new Properties();
		 
		LomAvanzadoVO auxAvanzado=new LomAvanzadoVO();
		 
		AvMetametadataVO metadatos = new AvMetametadataVO();
		auxAvanzado.setMetaMetadata(metadatos);
		
		is = this.getClass().getResourceAsStream("/catalogadorAvanzado.properties");
		prop.load(is);
//		String source=prop.getProperty("source").toString();
		CatalogadorAvSession catalogadorAvSession=this.getCatalogadorAvSession(request);
		
		
		String usuario =LdapUserDetailsUtils.getUsuario();
		//String usuario="empaquetador";
		String identificador =request.getParameter("identificador");
		if(identificador==null){
		   identificador=catalogadorAvSession.getIdentificador();
		}
		String returnURL=request.getParameter("returnURL");
		if(returnURL==null)
			returnURL=catalogadorAvSession.getReturnURL();
		
		catalogadorAvSession.setIdioma(this.getCatalogadorAvSession(request).getIdioma());
		catalogadorAvSession.setIdentificador(identificador);
		catalogadorAvSession.setUsuario(usuario);
		//metemos en la sesion el parametro de vuelta al empaquetador
		catalogadorAvSession.setReturnURL(returnURL);
	
	
    	Object valor = request.getSession().getAttribute("form");

    	//Obtenemos la longitudes de los VOs, que pasamos a cambioFormulario para que se obtengan del request los cambios que hemos hecho
    	//Pues en el form que nos viene como parametro no los guarda.

    	int longitudIdentificadores=0;
    	int longitudEsquemas=0;
    	int longitudDescripciones=0;
    	int longitudContribuciones=0;
    	int[] longitudTextosDesc = new int[0];
    	int[] longitudEntidadesContrib = new int[0];
    	if (valor instanceof MetaMetadatosFormImpl) {
    		
    		MetaMetadatosFormImpl valorMeta = ((MetaMetadatosFormImpl)valor);

    		Object[] desc = valorMeta.getDescripcionesAsArray();
			longitudTextosDesc = new int[desc.length];
			for(int i= 0;i< desc.length;i++){
				longitudTextosDesc[i]=((DescripcionVO)(desc[i])).getTextos().length;
			}
    		
		  	longitudEntidadesContrib = new int[valorMeta.getContribucion().size()];
			for(int i= 0;i< valorMeta.getContribucion().size();i++){
				longitudEntidadesContrib[i]=((Contribucion)(valorMeta.getContribucionAsArray()[i])).getEntidades().length;
			}
			
		  	longitudIdentificadores=valorMeta.getIdentificadoresAsArray().length;
		  	longitudEsquemas=valorMeta.getEsquemasMetaAsArray().length;
			longitudContribuciones=valorMeta.getContribucion().size();
			longitudDescripciones=desc.length;
		  	
    	}
    	else if (valor instanceof MetaMetadatosFormSubmitFormImpl){
    		MetaMetadatosFormSubmitFormImpl valorMeta = ((MetaMetadatosFormSubmitFormImpl)valor);
    		Object[] desc = valorMeta.getDescripcionesAsArray();
			longitudTextosDesc = new int[desc.length];
			for(int i= 0;i< desc.length;i++){
				longitudTextosDesc[i]=((DescripcionVO)(desc[i])).getTextos().length;
			}
    		
		  	longitudEntidadesContrib = new int[valorMeta.getContribucion().size()];
			for(int i= 0;i< valorMeta.getContribucion().size();i++){
				longitudEntidadesContrib[i]=((Contribucion)(valorMeta.getContribucionAsArray()[i])).getEntidades().length;
			}
			
		  	longitudIdentificadores=valorMeta.getIdentificadoresAsArray().length;
		  	longitudEsquemas=valorMeta.getEsquemasMetaAsArray().length;
			longitudContribuciones=valorMeta.getContribucion().size();
			longitudDescripciones=desc.length;
    	}
    	else if (valor instanceof MetadatosNoValidoVolverFormImpl){
    		MetadatosNoValidoVolverFormImpl valorMeta = ((MetadatosNoValidoVolverFormImpl)valor);
    		Object[] desc = valorMeta.getDescripcionesAsArray();
			longitudTextosDesc = new int[desc.length];
			for(int i= 0;i< desc.length;i++){
				longitudTextosDesc[i]=((DescripcionVO)(desc[i])).getTextos().length;
			}
    		
		  	longitudEntidadesContrib = new int[valorMeta.getContribucion().size()];
			for(int i= 0;i< valorMeta.getContribucion().size();i++){
				longitudEntidadesContrib[i]=((Contribucion)(valorMeta.getContribucionAsArray()[i])).getEntidades().length;
			}
			
		  	longitudIdentificadores=valorMeta.getIdentificadoresAsArray().length;
		  	longitudEsquemas=valorMeta.getEsquemasMetaAsArray().length;
			longitudContribuciones=valorMeta.getContribucion().size();
			longitudDescripciones=desc.length;
    	}
    	else if (valor instanceof MetadatosValidoVolverFormImpl){
    		MetadatosValidoVolverFormImpl valorMeta = ((MetadatosValidoVolverFormImpl)valor);
    		Object[] desc = valorMeta.getDescripcionesAsArray();
			longitudTextosDesc = new int[desc.length];
			for(int i= 0;i< desc.length;i++){
				longitudTextosDesc[i]=((DescripcionVO)(desc[i])).getTextos().length;
			}
    		
		  	longitudEntidadesContrib = new int[valorMeta.getContribucion().size()];
			for(int i= 0;i< valorMeta.getContribucion().size();i++){
				longitudEntidadesContrib[i]=((Contribucion)(valorMeta.getContribucionAsArray()[i])).getEntidades().length;
			}
			
		  	longitudIdentificadores=valorMeta.getIdentificadoresAsArray().length;
		  	longitudEsquemas=valorMeta.getEsquemasMetaAsArray().length;
			longitudContribuciones=valorMeta.getContribucion().size();
			longitudDescripciones=desc.length;
    	}
    	
    	//borramos de la session
		request.getSession().removeAttribute(MetametadatoSession.SESSION_KEY);
    	//Recogemos los cambio del request mediante parametro
	  	cambioFormulario(request,longitudIdentificadores,longitudEsquemas,longitudDescripciones,longitudContribuciones
	  			,longitudTextosDesc,longitudEntidadesContrib);
	  	//recogemos de session
	  	MetametadatoSession metametadatoSession = this.getMetametadatoSession(request);
	  	
	  	//idioma = form.getComboIdiomaMeta();
	  	metametadatoSession.setIdioma(form.getComboIdiomaMeta());
	  	
	  	
	  	//String idiomaLocale=((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
	  	ResourceBundle datosResources =I18n.getInstance().getResource("application-resources", (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));

		Collection mensajes = new ArrayList();
		int i= 0;
		boolean completo=true;
		boolean todosVacios=true;
		String error = "";
		
		//IDENTIFICADORES
		IdentificadorVO[] identificadores = (IdentificadorVO[])metametadatoSession.getIdentificadores()
			.toArray(new IdentificadorVO[metametadatoSession.getIdentificadores().size()]);
		for(i=0;i<identificadores.length && completo;i++)
		{
			if((identificadores[i].getCatalogo()!=null && identificadores[i].getCatalogo().trim().equals("")) &&
			   (identificadores[i].getEntrada()!=null && !identificadores[i].getEntrada().trim().equals("")) )
			{
				completo=false;
				error = datosResources.getString("CAV.3.1.1");//Si existe una entrada para un identificador, un cat&aacute;logo es obligatorio
				if(!mensajes.contains(error))
					mensajes.add(error);
			}
		}
		
		completo=true;
		for(i=0;i<identificadores.length && completo;i++)
		{
			if((identificadores[i].getCatalogo()!=null && !identificadores[i].getCatalogo().trim().equals("")) &&
			   (identificadores[i].getEntrada()!=null && identificadores[i].getEntrada().trim().equals("")) )
			{
				completo=false;
				error = datosResources.getString("CAV.3.1.2");//Si existe un cat&aacute;logo para un identificador, una entrada es obligatoria
				if(!mensajes.contains(error))
					mensajes.add(error);
			}
		}
		
		
		//CONTRIBUCION
		
		String rol;
//		boolean errorRol=false;
		boolean errorContRol=false;
		boolean errorContEntidad=false;
		boolean errorContFecha=false;
		
		boolean errorFecha=false;
		boolean errorFechaVacia=false;
		boolean errorDescVacia=false;
		boolean errorMinutos=false;

		boolean entidadesVacia;
		boolean fechaVacia;
		boolean horasVacia;
		boolean descVacios;
		
		Contribucion[] contribuciones = (Contribucion[])metametadatoSession.getContribuciones()
			.toArray(new Contribucion[metametadatoSession.getContribuciones().size()]);
		DescripcionVO[] descripciones = (DescripcionVO[]) metametadatoSession.getDescripciones()
  			.toArray(new DescripcionVO[metametadatoSession.getDescripciones().size()]);
		for(i=0;i<contribuciones.length && (!errorContRol || !errorContEntidad || !errorContFecha) ;i++ )
		{
//			ROL
				rol=contribuciones[i].getRol().getValor();
//		//entidad
				entidadesVacia=false;
				for(int j=0;j<contribuciones[i].getEntidades().length && !entidadesVacia;j++)
				{
					Entidad ent=contribuciones[i].getEntidades()[j];
					
					if((ent.getNombre()!=null && !ent.getNombre().trim().equals("")) ||
					   (ent.getOrganizacion()!=null && !ent.getOrganizacion().trim().equals("")) || 
					   (ent.getCorreo()!=null && !ent.getCorreo().trim().equals(""))
							){
//						validamos el correo
						if(!ent.getCorreo().trim().equals("")){
							if(!validaEmail(ent.getCorreo())){	
							
								//Añadimos el error a la colección
								error = datosResources.getString("CAV.3.4.1");
								if(!mensajes.contains(error)){
									mensajes.add(error); //si email está relleno, debe ser correcto
								}
							}
							
						}
						
					}else{
						entidadesVacia=true;
					}
					
				}
				
		//fecha
				Fecha fechaCont= contribuciones[i].getFecha();
				DescripcionVO descCont=descripciones[i];
				
				fechaVacia=false;
				
				if(fechaCont.getFechaCorta()!=null && fechaCont.getFechaCorta().equals(""))
				{
					
					fechaVacia=true;
				}
				horasVacia=false;
				if((fechaCont.getHora()!=null && fechaCont.getHora().trim().equals("")) && 
				(fechaCont.getMinutos()!=null && fechaCont.getMinutos().trim().equals("")))
				{
					horasVacia=true;
				}
				
				descVacios=true;
				String descFec;
				for(int j=0;j<descCont.getTextos().length && descVacios;j++)
				{
					descFec= descCont.getTextos()[j].getTexto();
					if(descFec!=null && !descFec.equals(""))
					{
						descVacios=false;// si todos los campos descripcion son vacios
					}
				}
				
				
				
			if( !((rol==null || rol.trim().equals("")) && //rol vacio
				(entidadesVacia) && //entidades vacia
				 fechaVacia && descVacios && horasVacia)//fechavacia
				)
			{
				if(rol==null || rol.trim().equals(""))
				{
					errorContRol =true;
					error = datosResources.getString("CAV.3.2.1");//El campo Tipo es obligatorio si se va a incluir una contribuci&oacute;n
					if(!mensajes.contains(error))
						mensajes.add(error);
				}
				
				if(entidadesVacia)
				{
					errorContEntidad=true;
					error = datosResources.getString("CAV.3.2.2");//El campo Entidad es obligatorio si se va a incluir una contribuci&oacute;n
					if(!mensajes.contains(error))
						mensajes.add(error);
				}

				//IDIOMA Y DESCRIPCION
				if(descripciones != null && descripciones.length > 0){
					for(int k = 0; k < descripciones.length; k++){
						if(descripciones[k].getTextos() != null && descripciones[k].getTextos().length > 0){
							for(int j = 0; j < descripciones[k].getTextos().length; j++){
								String texto = descripciones[k].getTextos()[j].getTexto();
								String idioma = descripciones[k].getTextos()[j].getIdioma();
								if(texto != null && !texto.equals("") && (idioma == null || idioma.equals(""))){
									error = datosResources.getString("CAV.3.3.3.3");
									if(!mensajes.contains(error))
										mensajes.add(error); //si descripción está relleno, el idioma también debe estarlo
								}
								if((texto == null || texto.equals("")) && idioma != null && !idioma.equals("")){
									error = datosResources.getString("CAV.3.3.3.4");
									if(!mensajes.contains(error))
										mensajes.add(error); //si idioma está relleno, descripción también debe estarlo
								}
							}
						}
					}
				}
				
				if(descVacios && fechaVacia && !errorFecha)
				{
					error = datosResources.getString("CAV.3.2.3");//El campo FECHA	 es obligatorio
					if(!mensajes.contains(error) && !mensajes.contains(datosResources.getString("CAV.3.3.3.4"))){
						errorContFecha=true;
						errorFecha=true;
						mensajes.add(error);
					}
					else if(!mensajes.contains(datosResources.getString("CAV.3.2.3.1")) && mensajes.contains(datosResources.getString("CAV.3.3.3.4"))){
						errorContFecha=true;
						errorFecha=true;
						mensajes.add(datosResources.getString("CAV.3.2.3.1"));
					}
				}
				else if(descVacios && !fechaVacia && !errorFechaVacia)
				{
					error = datosResources.getString("CAV.3.2.3.2");//La descripci&oacute;n de la fecha es obligatoria si existe una fecha
				if(!mensajes.contains(datosResources.getString("CAV.3.3.3.4"))&&!mensajes.contains(error)){
					errorContFecha=true;
					errorFechaVacia=true;
					mensajes.add(error);
				}
				}else if(!descVacios && fechaVacia && !errorDescVacia)
				{
					errorContFecha=true;
					errorDescVacia=true;
					error = datosResources.getString("CAV.3.2.3.1");//La fecha es obligatoria si existe una descripci&oacuete;n de fecha
					if(!mensajes.contains(error))
						mensajes.add(error);
				}
				if(	fechaVacia && !horasVacia && !errorMinutos )
				{
					errorContFecha=true;
					errorMinutos=true;
					error = datosResources.getString("CAV.3.2.3.8");//La fecha es obligatoria si el tiempo est&aacute; definido
					if(!mensajes.contains(error))
						mensajes.add(error);
				}
	
				if(errorFecha && errorFechaVacia && errorDescVacia && errorMinutos)
					errorContFecha=true;
			}

//			}
		
		}
		
		//ESQUEMA METADATOS No es obligatorio
//		todosVacios=true;
//		String esquema;
//		for(i=0;i<esquemasMeta.length && todosVacios;i++)
//		{
//			esquema=esquemasMeta[i].getTexto();
//			if(esquema!=null && !esquema.equals(""))
//			{
//				todosVacios=false;
//			}
//		}
//		if(todosVacios)
//			mensajes.add(datosResources.getString("CAV.3.3"));//El campo Esquema de metadatos es obligatorio
		
		
		//IDIOMA
		
		if(metametadatoSession.getIdioma()==null || metametadatoSession.getIdioma().equals("")){
			error = datosResources.getString("CAV.3.4");//El campo Idioma es obligatorio
			if(!mensajes.contains(error))
				mensajes.add(error);
		}
			  	
		if(mensajes.size()>0)
		{
			form.setMensajesError(mensajes);
			return false;
		}else{
			return true;
		}
	}

	
	
//	private String obtenerAccion(HttpServletRequest request) 
//	throws Exception
//	{
//		String accion="";
//		String[] partes;
//		for (Enumeration names = request.getParameterNames();accion.equals("") && names.hasMoreElements();)
//	       {
//	      	 String name = String.valueOf(names.nextElement());
//	           if(name.startsWith("accion"))
//	           {
//	        	   partes= name.split("_");
//		        	 if(partes.length>0){
//		        		 String anadidos=partes[1];
//		        		 for(int i=2;i<partes.length;i++){
//		        			 anadidos=anadidos+"_"+partes[i];
//		        		 }
//		        	 accion=anadidos;
//		        	 }
//	        	}
//		
//	        }
//		return accion;
//	}



	public void reset(
			ActionMapping mapping, 
			ResetForm form, 
			HttpServletRequest request, 
			HttpServletResponse response) 
	throws Exception 
	{
    	try{
			Object valor = request.getSession().getAttribute("form");
			CatalogadorAvSession catalogadorAvSession=this.getCatalogadorAvSession(request);
			//si el identificador de la sesion es null, entonces lanzamos la excepcion de fin de sesion, 
			//ya que todos los combos se quedan vacios y da error 
			if ((catalogadorAvSession == null) || (catalogadorAvSession.getIdentificador()==null)) {
				throw new Exception();
			}
			
	    	if (valor instanceof MetaMetadatosFormImpl) {
	    		MetaMetadatosFormImpl formulario=(MetaMetadatosFormImpl) valor;
		      	form.setDescripciones(formulario.getDescripciones());  
	   	      	form.setIdentificadores(formulario.getIdentificadores());
	   	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
	   	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
	   	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
	   	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
	   	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
		      	form.setCrolLabelList(formulario.getCrolLabelList());
		      	form.setCrolValueList(formulario.getCrolValueList());
		      	form.setContribucion(formulario.getContribucion());
			}
			else if (valor instanceof MetaMetadatosFormSubmitFormImpl) {
				MetaMetadatosFormSubmitFormImpl formulario=(MetaMetadatosFormSubmitFormImpl) valor;
		      	form.setDescripciones(formulario.getDescripciones());  
	   	      	form.setIdentificadores(formulario.getIdentificadores());
	   	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
	   	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
	   	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
	   	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
	   	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
		      	form.setCrolLabelList(formulario.getCrolLabelList());
		      	form.setCrolValueList(formulario.getCrolValueList());
		      	form.setContribucion(formulario.getContribucion());
			}
			else if (valor instanceof MetadatosValidoVolverFormImpl) {
				MetadatosValidoVolverFormImpl formulario=(MetadatosValidoVolverFormImpl) valor;
		      	form.setDescripciones(formulario.getDescripciones());  
	   	      	form.setIdentificadores(formulario.getIdentificadores());
	   	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
	   	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
	   	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
	   	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
	   	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
		      	form.setCrolLabelList(formulario.getCrolLabelList());
		      	form.setCrolValueList(formulario.getCrolValueList());
		      	form.setContribucion(formulario.getContribucion());
			}
			else if (valor instanceof MetadatosNoValidoVolverFormImpl) {
				MetadatosNoValidoVolverFormImpl formulario=(MetadatosNoValidoVolverFormImpl) valor;
		      	form.setDescripciones(formulario.getDescripciones());  
	   	      	form.setIdentificadores(formulario.getIdentificadores());
	   	      	form.setEsquemasMeta(formulario.getEsquemasMeta());
	   	      	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
	   	      	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
	   	      	form.setComboIdiomaMetaLabelList(formulario.getComboIdiomaMetaLabelList());
	   	      	form.setComboIdiomaMetaValueList(formulario.getComboIdiomaMetaValueList());
		      	form.setCrolLabelList(formulario.getCrolLabelList());
		      	form.setCrolValueList(formulario.getCrolValueList());
		      	form.setContribucion(formulario.getContribucion());
			}
	    	
	    	//Identificadores
			IdentificadorVO[] aIdentificadores = new IdentificadorVO[1];
	    	IdentificadorVO identificadorVO=new IdentificadorVO();
	    	identificadorVO.setCatalogo("");
	    	identificadorVO.setEntrada("");
	    	aIdentificadores[0]=identificadorVO;
	    	form.setIdentificadoresAsArray(aIdentificadores); 
	    	
	    	//CONTRIBUCIONES
			Contribucion[] contribucionesAux=new Contribucion[1];
			Contribucion contribucionAux = new Contribucion();
			//Descripcion vacia
			DescripcionVO[] aDescripcionesVO = new DescripcionVO[1];
			DescripcionVO descripcionVO = new DescripcionVO();
			LangStringVO[] nuevolangTextosDescrip = new LangStringVO[1];
			nuevolangTextosDescrip = new LangStringVO[1];
			LangStringVO lang = new LangStringVO();
			lang.setIdioma("");
			lang.setTexto("");
			nuevolangTextosDescrip[0] = lang;
			descripcionVO.setTextos(nuevolangTextosDescrip);
			aDescripcionesVO[0]=descripcionVO;
			//Fecha vacia
			Fecha auxFecha = new Fecha();
			auxFecha.setIdioma(this.getCatalogadorAvSession(request).getIdioma());
			contribucionAux.setFecha(auxFecha);
			/************************/
			//Tipo (ROL)
			SourceValueVO tipo = new SourceValueVO();
			tipo.setSource("");
			tipo.setValor("");
			contribucionAux.setRol(tipo);
			/************************/
			//Entidades
			Entidad[] entidades = new Entidad[1];
			Entidad entidad = new Entidad();
			entidad.setCorreo("");
			entidad.setNombre("");
			entidad.setOrganizacion("");
			entidades[0]=entidad;
			contribucionAux.setEntidades(entidades);
			/************************/
			contribucionesAux[0]=contribucionAux;
			form.setContribucionAsArray(contribucionesAux);
			form.setDescripcionesAsArray(aDescripcionesVO) ;
	    	
	    	//ESQUEMAS
			EsquemaDeMetadatosVO[] aEsquemas = new EsquemaDeMetadatosVO[1];
			EsquemaDeMetadatosVO esquemaVO=new EsquemaDeMetadatosVO();
			esquemaVO.setTexto("");
			aEsquemas[0]=esquemaVO;
	    	form.setEsquemasMetaAsArray(aEsquemas);
	    	
	    	//IDIOMAS
			form.setComboIdiomaMeta("");

			MetametadatoSession metametadatoSession = this.getMetametadatoSession(request);
			cargarAyuda(metametadatoSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
			
    	}catch (Exception e) {
			logger
			.error("Error en catalogadorWeb MetaMetadatos, metodo reset " + e);
			//throw new Exception("ver.metadatos", e);
			saveErrorMessage(request, " Error en catalogadorWeb MetaMetadatos, metodo reset ");
        }
	}
	
//	metodo que devuelve la ayuda contextual	
	private void cargarAyuda(MetametadatoSession metadato, Locale idioma) {	
		
		ResourceBundle datosResources = I18n.getInstance().getResource("application-resources", idioma);
		//resultado = prop.getProperty(salidaAyuda);
		metadato.setAyudaContribucion(datosResources.getString("cat.ayuda.metameta.contribucion"));
		metadato.setAyudaEntidad(datosResources.getString("cat.ayuda.metameta.contri.entidad"));
		metadato.setAyudaEsqMeta(datosResources.getString("cat.ayuda.metameta.esquema"));
		metadato.setAyudaFecha(datosResources.getString("cat.ayuda.metameta.contri.fecha"));
		metadato.setAyudaIdiomaCat(datosResources.getString("cat.ayuda.metameta.idioma"));
		metadato.setAyudaIdMeta(datosResources.getString("cat.ayuda.metameta.id"));
		metadato.setAyudaTipo(datosResources.getString("cat.ayuda.metameta.contri.tipo"));
				
		logger.debug("METAMETADATOS CONTROLLER, CARGADA AYUDA CONTEXTUAL idioma " + idioma);
       
	}   

}