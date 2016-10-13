/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.catalogadorWeb.presentacion.catalogadorAvanzado;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.catalogacion.negocio.servicios.AgregadorORVO;
import es.pode.catalogacion.negocio.servicios.AvAnnotationVO;
import es.pode.catalogacion.negocio.servicios.AvClassificationVO;
import es.pode.catalogacion.negocio.servicios.AvEducationalVO;
import es.pode.catalogacion.negocio.servicios.AvGeneralVO;
import es.pode.catalogacion.negocio.servicios.AvLifeCycleVO;
import es.pode.catalogacion.negocio.servicios.AvMetametadataVO;
import es.pode.catalogacion.negocio.servicios.AvRelationVO;
import es.pode.catalogacion.negocio.servicios.AvRightsVO;
import es.pode.catalogacion.negocio.servicios.AvTechnicalVO;
import es.pode.catalogacion.negocio.servicios.ContribucionVO;
import es.pode.catalogacion.negocio.servicios.DescripcionVO;
import es.pode.catalogacion.negocio.servicios.EntidadVO;
import es.pode.catalogacion.negocio.servicios.EntryVO;
import es.pode.catalogacion.negocio.servicios.FechaVO;
import es.pode.catalogacion.negocio.servicios.IdVO;
import es.pode.catalogacion.negocio.servicios.IdentificadorVO;
import es.pode.catalogacion.negocio.servicios.LangStringVO;
import es.pode.catalogacion.negocio.servicios.LomAvanzadoVO;
import es.pode.catalogacion.negocio.servicios.SourceValueVO;
import es.pode.catalogacion.negocio.servicios.TaxonVO;
import es.pode.catalogacion.negocio.servicios.VersionMaxVO;
import es.pode.catalogacion.negocio.servicios.VersionMinVO;
import es.pode.catalogacion.soporte.DerechosDeAutor;
import es.pode.catalogacion.soporte.TiposDeRecursos;
import es.pode.catalogacion.soporte.UtilidadesOrdenarCombos;
import es.pode.catalogacion.soporte.ValidarMec;
import es.pode.catalogadorWeb.presentacion.CatalogadorAvSession;
import es.pode.catalogadorWeb.presentacion.ValidarTaxo;
import es.pode.fuentestaxonomicas.negocio.servicio.TerminoVO;
import es.pode.fuentestaxonomicas.negocio.servicio.TerminoYPadreVO;
import es.pode.fuentestaxonomicas.negocio.servicio.VocabularioVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.catalogadorWeb.presentacion.catalogadorAvanzado.CatAvanzadoController
 */
public class CatAvanzadoControllerImpl extends CatAvanzadoController
{ 


	protected static Logger logger = Logger.getLogger(CatAvanzadoControllerImpl.class); 

	private static final String HTTP ="http://";
	private static final String BARRA ="/";
	private static final String SEPARADOR =":";
	
    /**
     * @see es.pode.catalogadorWeb.presentacion.catalogadorAvanzado.CatAvanzadoController#cargarDatosAv(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.catalogadorAvanzado.CargarDatosAvForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void cargarDatosAv(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.catalogadorAvanzado.CargarDatosAvForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	InputStream is = null;
    	Properties prop = new Properties();
		boolean bandera=false;
		try{
	        
			javax.servlet.http.HttpSession session = request.getSession(true);
			Object attribute = session.getAttribute(es.pode.catalogadorWeb.presentacion.CatalogadorAvSession.SESSION_KEY);
			//Cogemos el idioma de LanguageFilter de Soporte, que si estas logado coge el idioma de LDAP
			Locale idio=(Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
			String idiomaLdap=idio.getLanguage();
			
	        CatalogadorAvSession catalogadorAvSession=this.getCatalogadorAvSession(request);
	        catalogadorAvSession.setMensajeAsistente("");
	        //String idiomaLdap=LdapUserDetailsUtils.getIdioma();//"es";
	        String usuario =LdapUserDetailsUtils.getUsuario();//"empaquetador";//
	        String identificador =request.getParameter("identificador");
	        if(identificador==null){
	        	identificador=catalogadorAvSession.getIdentificador();
	        }
//	        is = this.getClass().getResourceAsStream("/catalogadorAvanzado.properties");
//			prop.load(is);
//			if (idiomaLdap==null || idiomaLdap.equals("")){
//				idiomaLdap=I18n.getInstance().obtenerIdiomaDefectoPlataforma();
//			}
//	        String idiomas=prop.getProperty("idiomas.properties").toString();
//			idiomas="es,en,eu";//Redefinido hasta que tengamos las traducciones de los vocabularios en catalán y gallego
//			StringTokenizer token = new StringTokenizer(idiomas, ",");
//			while (token.hasMoreElements()&&(!bandera)) {
//				String idiomaLeido=token.nextElement().toString();
//				if(idiomaLdap.equals(idiomaLeido)){
//					bandera=true;
//				}
//			}
//			
//			if(bandera==false){
//				idiomaLdap= "es";
//			}

			LomAvanzadoVO lomAvanzado= null;
			
			if (attribute == null || ((CatalogadorAvSession)attribute).getMDSesion() == null) {
				//si aqi llega el identificador a null qiere decir que se ha ido la session, para evitar error de servicio
				// lanzamos directamente la excepcion
				if (identificador!= null) {
					lomAvanzado= this.getSrvCatalogacionAvanzadaService().obtenerLomAvanzado(identificador,usuario,idiomaLdap);//Engancharselo al objeto de session
				}else{
					throw new Exception();
				}
			}else{
				lomAvanzado = catalogadorAvSession.getMDSesion();
			}
//			Cargamos el objeto de session desde el lom avanzado
//			introducido nuevo parametro para vuelta al empaquetador
			String returnURL=request.getParameter("returnURL");
			if(returnURL==null)
				returnURL=catalogadorAvSession.getReturnURL();
			catalogadorAvSession.setIdioma(idiomaLdap);
			catalogadorAvSession.setIdentificador(identificador);
			catalogadorAvSession.setUsuario(usuario);
//			metemos en la sesion el parametro de vuelta al empaquetador
			catalogadorAvSession.setReturnURL(returnURL);			
			
			
//			Cargamos el objeto de sesion
			if(catalogadorAvSession.getMDSesion()==null){
				LomAvanzadoVO lom=new LomAvanzadoVO();
				//cargamos el general
				
				if(lomAvanzado.getGeneral()!=null){
					AvGeneralVO gen=new AvGeneralVO();
					gen.setIdentificadores(lomAvanzado.getGeneral().getIdentificadores());
					gen.setTitulo(lomAvanzado.getGeneral().getTitulo());
					gen.setIdiomas(lomAvanzado.getGeneral().getIdiomas());
					gen.setDescripciones(lomAvanzado.getGeneral().getDescripciones());
					gen.setPalabrasClave(lomAvanzado.getGeneral().getPalabrasClave());
					gen.setAmbitos(lomAvanzado.getGeneral().getAmbitos());
					gen.setEstructura(lomAvanzado.getGeneral().getEstructura());
					gen.setNivelAgregacion(lomAvanzado.getGeneral().getNivelAgregacion());
					lom.setGeneral(gen);
				}
				
				//cargamos el ciclo de vida
				
				if(lomAvanzado.getLifeCycle()!=null){
					AvLifeCycleVO life=new AvLifeCycleVO();
					life.setVersion(lomAvanzado.getLifeCycle().getVersion());
					life.setEstado(lomAvanzado.getLifeCycle().getEstado());
					life.setContribuciones(lomAvanzado.getLifeCycle().getContribuciones());
					lom.setLifeCycle(life);
				}
				
				//cargamos los meta-metadatos
				
				if(lomAvanzado.getMetaMetadata()!=null){
					AvMetametadataVO meta=new AvMetametadataVO();
					meta.setIdentificadores(lomAvanzado.getMetaMetadata().getIdentificadores());
					meta.setContribuciones(lomAvanzado.getMetaMetadata().getContribuciones());
					meta.setEsquemas(lomAvanzado.getMetaMetadata().getEsquemas());
					meta.setIdioma(lomAvanzado.getMetaMetadata().getIdioma());
					lom.setMetaMetadata(meta);
				}
				
				//cargamos tecnica
				
				if(lomAvanzado.getTechnical()!=null){
					AvTechnicalVO tech=new AvTechnicalVO();
					tech.setFormatos(lomAvanzado.getTechnical().getFormatos());
					tech.setTamanio(lomAvanzado.getTechnical().getTamanio());
					tech.setLocalizaciones(lomAvanzado.getTechnical().getLocalizaciones());
					tech.setRequisitos(lomAvanzado.getTechnical().getRequisitos());
					tech.setPautasInstalacion(lomAvanzado.getTechnical().getPautasInstalacion());
					tech.setOtrosRequisitos(lomAvanzado.getTechnical().getOtrosRequisitos());
					tech.setDuracion(lomAvanzado.getTechnical().getDuracion());
					lom.setTechnical(tech);
				}
				
				//cargamos usos educativos
				
				if((lomAvanzado.getEducational()!=null)&&(lomAvanzado.getEducational().length>0)){
					ArrayList educationals=new ArrayList();
					for (int i=0; i<lomAvanzado.getEducational().length;i++){
						AvEducationalVO edu=new AvEducationalVO();
						edu.setTipoDeInteractividad(lomAvanzado.getEducational()[i].getTipoDeInteractividad());
						edu.setTiposrecursoedu(lomAvanzado.getEducational()[i].getTiposrecursoedu());
						edu.setNivelInteractividad(lomAvanzado.getEducational()[i].getNivelInteractividad());
						edu.setDensidadSemantica(lomAvanzado.getEducational()[i].getDensidadSemantica());
						edu.setDestinatarios(lomAvanzado.getEducational()[i].getDestinatarios());
						edu.setContextos(lomAvanzado.getEducational()[i].getContextos());
						edu.setRangoedades(lomAvanzado.getEducational()[i].getRangoedades());
						edu.setDificultad(lomAvanzado.getEducational()[i].getDificultad());
						edu.setTiempoAprendizaje(lomAvanzado.getEducational()[i].getTiempoAprendizaje());
						edu.setDescripciones(lomAvanzado.getEducational()[i].getDescripciones());
						edu.setIdiomas(lomAvanzado.getEducational()[i].getIdiomas());
						edu.setProcesoscognitivos(lomAvanzado.getEducational()[i].getProcesoscognitivos());
						educationals.add(edu);
					}
					AvEducationalVO[] educational=(AvEducationalVO[])educationals.toArray(new AvEducationalVO[educationals.size()]);
					lom.setEducational(educational);
				}
				
				
				//cargamos derechos
				
				if(lomAvanzado.getDerechos()!=null){
					AvRightsVO derechos=new AvRightsVO();
					derechos.setCoste(lomAvanzado.getDerechos().getCoste());
					derechos.setDerechosDeAutor(lomAvanzado.getDerechos().getDerechosDeAutor());
					derechos.setDescripcion(lomAvanzado.getDerechos().getDescripcion());
					derechos.setAcceso(lomAvanzado.getDerechos().getAcceso());
					lom.setDerechos(derechos);
				}
				
				
				//cargamos relaciones
				
				if((lomAvanzado.getRelaciones()!=null)&&(lomAvanzado.getRelaciones().length>0)){
					ArrayList relation=new ArrayList();
					for(int i=0;i<lomAvanzado.getRelaciones().length;i++){
						AvRelationVO relacion=new AvRelationVO();
						relacion.setTipo(lomAvanzado.getRelaciones()[i].getTipo());
						relacion.setRecurso(lomAvanzado.getRelaciones()[i].getRecurso());
						relation.add(relacion);
					}
					AvRelationVO[] relaciones=(AvRelationVO[])relation.toArray(new AvRelationVO[relation.size()]);
					lom.setRelaciones(relaciones);
				}
				
				//cargamos anotaciones
				
				if((lomAvanzado.getAnotaciones()!=null)&&(lomAvanzado.getAnotaciones().length>0)){
					ArrayList annotation=new ArrayList();
					for(int i=0;i<lomAvanzado.getAnotaciones().length;i++){
						AvAnnotationVO anotacion=new AvAnnotationVO();
						anotacion.setEntidad(lomAvanzado.getAnotaciones()[i].getEntidad());
						anotacion.setFecha(lomAvanzado.getAnotaciones()[i].getFecha());
						anotacion.setDescripcion(lomAvanzado.getAnotaciones()[i].getDescripcion());
						annotation.add(anotacion);
					}
					AvAnnotationVO[] anotaciones=(AvAnnotationVO[])annotation.toArray(new AvAnnotationVO[annotation.size()]);
					lom.setAnotaciones(anotaciones);
				}
				
				//cargamos clasificaciones
				
				if((lomAvanzado.getClasificaciones()!=null)&&(lomAvanzado.getClasificaciones().length>0)){
					ArrayList classification=new ArrayList();
					for(int i=0;i<lomAvanzado.getClasificaciones().length;i++){
						AvClassificationVO clasificacion=new AvClassificationVO();
						clasificacion.setProposito(lomAvanzado.getClasificaciones()[i].getProposito());
						clasificacion.setRutasTaxonomicas(lomAvanzado.getClasificaciones()[i].getRutasTaxonomicas());
						clasificacion.setDescripcion(lomAvanzado.getClasificaciones()[i].getDescripcion());
						clasificacion.setPalabrasClave(lomAvanzado.getClasificaciones()[i].getPalabrasClave());
						classification.add(clasificacion);
					}
					AvClassificationVO[] clasificaciones=(AvClassificationVO[])classification.toArray(new AvClassificationVO[classification.size()]);
					lom.setClasificaciones(clasificaciones);
				}
				
				catalogadorAvSession.setMDSesion(lom);
				
			}else{
				//cargamos general
				if(lomAvanzado.getGeneral()!=null){
			       catalogadorAvSession.getMDSesion().getGeneral().setIdentificadores(lomAvanzado.getGeneral().getIdentificadores());
			       catalogadorAvSession.getMDSesion().getGeneral().setTitulo(lomAvanzado.getGeneral().getTitulo());
			       catalogadorAvSession.getMDSesion().getGeneral().setIdiomas(lomAvanzado.getGeneral().getIdiomas());
			       catalogadorAvSession.getMDSesion().getGeneral().setDescripciones(lomAvanzado.getGeneral().getDescripciones());
			       catalogadorAvSession.getMDSesion().getGeneral().setPalabrasClave(lomAvanzado.getGeneral().getPalabrasClave());
			       catalogadorAvSession.getMDSesion().getGeneral().setAmbitos(lomAvanzado.getGeneral().getAmbitos());
			       catalogadorAvSession.getMDSesion().getGeneral().setEstructura(lomAvanzado.getGeneral().getEstructura());
			       catalogadorAvSession.getMDSesion().getGeneral().setNivelAgregacion(lomAvanzado.getGeneral().getNivelAgregacion());
				}
		       //cargamos el ciclo de vida
				if(lomAvanzado.getLifeCycle()!=null){
			       catalogadorAvSession.getMDSesion().getLifeCycle().setVersion(lomAvanzado.getLifeCycle().getVersion());
			       catalogadorAvSession.getMDSesion().getLifeCycle().setEstado(lomAvanzado.getLifeCycle().getEstado());
			       catalogadorAvSession.getMDSesion().getLifeCycle().setContribuciones(lomAvanzado.getLifeCycle().getContribuciones());
				}
		       //cargamos el metametadata
				if(lomAvanzado.getMetaMetadata()!=null){
			       catalogadorAvSession.getMDSesion().getMetaMetadata().setIdentificadores(lomAvanzado.getMetaMetadata().getIdentificadores());
			       catalogadorAvSession.getMDSesion().getMetaMetadata().setContribuciones(lomAvanzado.getMetaMetadata().getContribuciones());
			       catalogadorAvSession.getMDSesion().getMetaMetadata().setEsquemas(lomAvanzado.getMetaMetadata().getEsquemas());
			       catalogadorAvSession.getMDSesion().getMetaMetadata().setIdioma(lomAvanzado.getMetaMetadata().getIdioma());
				}
		       //cargamos technical
				if(lomAvanzado.getTechnical()!=null){
			       catalogadorAvSession.getMDSesion().getTechnical().setFormatos(lomAvanzado.getTechnical().getFormatos());
			       catalogadorAvSession.getMDSesion().getTechnical().setTamanio(lomAvanzado.getTechnical().getTamanio());
			       catalogadorAvSession.getMDSesion().getTechnical().setLocalizaciones(lomAvanzado.getTechnical().getLocalizaciones());
			       catalogadorAvSession.getMDSesion().getTechnical().setRequisitos(lomAvanzado.getTechnical().getRequisitos());
			       catalogadorAvSession.getMDSesion().getTechnical().setPautasInstalacion(lomAvanzado.getTechnical().getPautasInstalacion());
			       catalogadorAvSession.getMDSesion().getTechnical().setOtrosRequisitos(lomAvanzado.getTechnical().getOtrosRequisitos());
			       catalogadorAvSession.getMDSesion().getTechnical().setDuracion(lomAvanzado.getTechnical().getDuracion());
				}
		       
		       //cargamos educationales
				if((lomAvanzado.getEducational()!=null)&&(lomAvanzado.getEducational().length>0)){
			       for(int i=0;i<lomAvanzado.getEducational().length;i++){
			    	   catalogadorAvSession.getMDSesion().getEducational()[i].setTipoDeInteractividad(lomAvanzado.getEducational()[i].getTipoDeInteractividad());
			    	   catalogadorAvSession.getMDSesion().getEducational()[i].setTiposrecursoedu(lomAvanzado.getEducational()[i].getTiposrecursoedu());
			    	   catalogadorAvSession.getMDSesion().getEducational()[i].setNivelInteractividad(lomAvanzado.getEducational()[i].getNivelInteractividad());
			    	   catalogadorAvSession.getMDSesion().getEducational()[i].setDensidadSemantica(lomAvanzado.getEducational()[i].getDensidadSemantica());
			    	   catalogadorAvSession.getMDSesion().getEducational()[i].setDestinatarios(lomAvanzado.getEducational()[i].getDestinatarios());
			    	   catalogadorAvSession.getMDSesion().getEducational()[i].setContextos(lomAvanzado.getEducational()[i].getContextos());
			    	   catalogadorAvSession.getMDSesion().getEducational()[i].setRangoedades(lomAvanzado.getEducational()[i].getRangoedades());
			    	   catalogadorAvSession.getMDSesion().getEducational()[i].setDificultad(lomAvanzado.getEducational()[i].getDificultad());
			    	   catalogadorAvSession.getMDSesion().getEducational()[i].setTiempoAprendizaje(lomAvanzado.getEducational()[i].getTiempoAprendizaje());
			    	   catalogadorAvSession.getMDSesion().getEducational()[i].setDescripciones(lomAvanzado.getEducational()[i].getDescripciones());
			    	   catalogadorAvSession.getMDSesion().getEducational()[i].setIdiomas(lomAvanzado.getEducational()[i].getIdiomas());
			    	   catalogadorAvSession.getMDSesion().getEducational()[i].setProcesoscognitivos(lomAvanzado.getEducational()[i].getProcesoscognitivos());
			       }
				}
		       //cargamos derechos
				if(lomAvanzado.getDerechos()!=null){
			       catalogadorAvSession.getMDSesion().getDerechos().setCoste(lomAvanzado.getDerechos().getCoste());
			       catalogadorAvSession.getMDSesion().getDerechos().setDerechosDeAutor(lomAvanzado.getDerechos().getDerechosDeAutor());
			       catalogadorAvSession.getMDSesion().getDerechos().setDescripcion(lomAvanzado.getDerechos().getDescripcion());
			       catalogadorAvSession.getMDSesion().getDerechos().setAcceso(lomAvanzado.getDerechos().getAcceso());
				}
		       //cargamos relaciones
				if((lomAvanzado.getRelaciones()!=null)&&(lomAvanzado.getRelaciones().length>0)){
			       for(int i=0;i<lomAvanzado.getRelaciones().length;i++){
			    	   catalogadorAvSession.getMDSesion().getRelaciones()[i].setTipo(lomAvanzado.getRelaciones()[i].getTipo());
			    	   catalogadorAvSession.getMDSesion().getRelaciones()[i].setRecurso(lomAvanzado.getRelaciones()[i].getRecurso());
			       }
				}
		       //cargamos anotaciones
				if((lomAvanzado.getAnotaciones()!=null)&&(lomAvanzado.getAnotaciones().length>0)){
			       for(int i=0;i<lomAvanzado.getAnotaciones().length;i++){
			    	   catalogadorAvSession.getMDSesion().getAnotaciones()[i].setEntidad(lomAvanzado.getAnotaciones()[i].getEntidad());
			    	   catalogadorAvSession.getMDSesion().getAnotaciones()[i].setFecha(lomAvanzado.getAnotaciones()[i].getFecha());
			    	   catalogadorAvSession.getMDSesion().getAnotaciones()[i].setDescripcion(lomAvanzado.getAnotaciones()[i].getDescripcion());
			       }
				}
		       
		       //cargamos clasificaciones
				if((lomAvanzado.getClasificaciones()!=null)&&(lomAvanzado.getClasificaciones().length>0)){
			       for(int i=0;i<lomAvanzado.getClasificaciones().length;i++){
			    	   catalogadorAvSession.getMDSesion().getClasificaciones()[i].setProposito(lomAvanzado.getClasificaciones()[i].getProposito());
			    	   catalogadorAvSession.getMDSesion().getClasificaciones()[i].setRutasTaxonomicas(lomAvanzado.getClasificaciones()[i].getRutasTaxonomicas());
			    	   catalogadorAvSession.getMDSesion().getClasificaciones()[i].setDescripcion(lomAvanzado.getClasificaciones()[i].getDescripcion());
			    	   catalogadorAvSession.getMDSesion().getClasificaciones()[i].setPalabrasClave(lomAvanzado.getClasificaciones()[i].getPalabrasClave());
			       }
				}
			}
		}catch (Exception e){
			logger
			.error("Error en Servicio de catalogacion avanzado, metodo cargarDatosAv" + e);
    		//throw new java.lang.Exception("catalogador.avanzado", e);
			saveErrorMessage(request, "Error catAvanzadoController al cargarDatosAv");
    		
    	} finally {
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
     * @see es.pode.catalogadorWeb.presentacion.catalogadorAvanzado.CatAvanzadoController#categoriaSeleccionada(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.catalogadorAvanzado.CategoriaSeleccionadaForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String categoriaSeleccionada(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.catalogadorAvanzado.CategoriaSeleccionadaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        // this property receives a default value, just to have the application running on dummy data
        form.setCategoria("categoria-test");
         return null;
    }



    public void volverAEmpaquetador(
			ActionMapping mapping, 
			VolverAEmpaquetadorForm form, 
			HttpServletRequest request, 
			HttpServletResponse response) 
	throws Exception 
	{
		//Recogemos el parametro para ir a PortalEmpaquetador
		String returnUrl = this.getCatalogadorAvSession(request).getReturnURL();
		//Parametro de catalogador Directo desde el empaquetador si no vacio, vuelta diferente
		String catalogadorDirecto = this.getCatalogadorAvSession(request).getCatalogadorDirecto();
		//Parametro crear Metadato, desde gestor flujo directamente
    	String crearMetadato = this.getCatalogadorAvSession(request).getCrearMetadato();
		String identificador = this.getCatalogadorAvSession(request).getIdentificador();    	
//		borramos datos de sesion
		request.getSession().removeAttribute(es.pode.catalogadorWeb.presentacion.CatalogadorAvSession.SESSION_KEY);
		
		//Por si no nos llaman con el parametro returnURL, ponemos esta comprobacion que
		//debemos quitar en un futuro!!
		
		String url = HTTP + LdapUserDetailsUtils.getHost();
		if(returnUrl==null){
			returnUrl = BARRA + request.getSession().getServletContext().getInitParameter("url_portada");
		}
		if (returnUrl!=null && !returnUrl.startsWith(BARRA)){
			returnUrl = BARRA + returnUrl;
		}
		if(LdapUserDetailsUtils.getSubdominio()!=null && 
		   !LdapUserDetailsUtils.getSubdominio().equals("") && 
		   !returnUrl.startsWith(LdapUserDetailsUtils.getSubdominio()))
		{
			returnUrl = LdapUserDetailsUtils.getSubdominio() + returnUrl;
		}
		
		url= url + returnUrl;
//		si hemos trabajado con catalogador Directo, vamos a poner en la url el parámetro de "Volver Catalogador"
		if ((catalogadorDirecto!=null) && (!catalogadorDirecto.equals(""))){
			url= url +  "?volverDirecto=VD" + "&identificador=" + identificador; // y ya no llamamos con catalogadorDirecto=CD					
		} else if (crearMetadato!=null && !crearMetadato.equals("")){
			//si viene con el parametro de crear metadato, la url la dejams igual, pero
		    //borramos el elemento de la hash
			String [] lisIdentif= {identificador};
			this.getSrvCatalogacionAvanzadaService().eliminarObjLoms(lisIdentif);
		}
		this.getCatalogadorAvSession(request).setMensajeAsistente("");
		logger.info("TENEMOS LA URL DE VUELTA AL EMPAQUETADOR: " + url);
		response.sendRedirect(url);
	}







	public String accionSubmit(ActionMapping mapping, AccionSubmitForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String accion= form.getAccion();
		String accionCombo = form.getAccionSubmitCombo();
		String resAction = "";
		
		String idioma=((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
		//ResourceBundle datosResources = ResourceBundle.getBundle("application-resources_"+idioma);
		ResourceBundle datosResources = I18n.getInstance().getResource("application-resources", (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
		
		if(accion == null){
			if(datosResources.getString("avanzado.Traducir").equals(accionCombo)) {
				resAction = "Traducir";
			}else if(datosResources.getString("avanzado.Exportar").equals(accionCombo)){
				resAction = "Exportar";
			}else if(datosResources.getString("avanzado.Importar").equals(accionCombo)){
				resAction = "Importar";
			}else{
				resAction = "Recargar";
			}
		}
		else{
			if (datosResources.getString("catalogadorAvanzado.Aceptar").equals(accion)) {
				resAction = "GuardarDatos";
			} else if (datosResources.getString("catalogadorAvanzado.Validar").equals(accion)) {
				resAction = "Validar";
			}else if (datosResources.getString("catalogadorAvanzado.Cancelar").equals(accion)) {
				resAction = "Cancelar";
			}else{
				resAction = "Recargar";
			}
		}
		
		return resAction;
	}







	public void guardarMetadatos(ActionMapping mapping, GuardarMetadatosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		InputStream is = null;
		String identificador= "";
		String usuario= "";
		String idioma= "";
		
		try {
			
			if (this.getCatalogadorAvSession(request) == null) {
				identificador = request.getParameter("identificador");
			} else {
				identificador = this.getCatalogadorAvSession(request)
						.getIdentificador();
			}
			usuario =LdapUserDetailsUtils.getUsuario(); 
			//Recogemos el idioma del navegador
//			idioma=LdapUserDetailsUtils.getIdioma();
//			if (idioma==null || idioma.equals("")){
//				idioma=I18n.getInstance().obtenerIdiomaDefectoPlataforma();
//			}
			javax.servlet.http.HttpSession session = request.getSession(true);
//			Cogemos el idioma de LanguageFilter de Soporte, que si estas logado coge el idioma de LDAP
			Locale idio=(Locale)session.getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
			idioma=idio.getLanguage();
			
			LomAvanzadoVO lomAv= this.getCatalogadorAvSession(request).getMDSesion();
			
			if (lomAv == null){
				lomAv= new LomAvanzadoVO();
			}
			logger.debug("ASC - antes de entrar a generarMetadatos, IDENTIFICADOR " + identificador + " USUARIO " + usuario + " IDIOMA " + idioma);
	        this.getSrvCatalogacionAvanzadaService().generarMetadatos(identificador, usuario, lomAv, idioma);
			
	
		}catch (Exception e){
			logger.error("Error Catalogacion, metodo guardarMetadatos: " + identificador + "; " + usuario + "; " +idioma);
			//throw new java.lang.Exception("catalogador.avanzado", e);
			saveErrorMessage(request, "Error en catAvanzadoController al guardar metadatos.");
		} 
		
		
	}




	public boolean sonValidosMetadatos(ActionMapping mapping, SonValidosMetadatosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

//		String resultado= ""; 
		boolean ok= true;
		
		//String idioma=((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
		ResourceBundle datosResources = I18n.getInstance().getResource("application-resources", (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
		
		List mensajes = new ArrayList();
		
		LomAvanzadoVO lomAv= this.getCatalogadorAvSession(request).getMDSesion();
		
		if (lomAv == null){
			lomAv= new LomAvanzadoVO();
		}
		logger.debug("Método de validacion de metadatos avanzados");
		
		String error = "";
		// -- GENERAL --
		if(lomAv.getGeneral()==null){
			error = datosResources.getString("CAV.1");
			if(!mensajes.contains(error))
				mensajes.add(error);
		}
		else{
			
			AvGeneralVO gn = lomAv.getGeneral();
			if(gn.getIdentificadores()!=null && gn.getIdentificadores().length>0){
	//			CATALOGOS
				int i= 0;
				while ((ok)&&(i<gn.getIdentificadores().length)){
					
					if (gn.getIdentificadores()[i]!=null){
						String cat= gn.getIdentificadores()[i].getCatalogo();
						String ent= gn.getIdentificadores()[i].getEntrada();
						
						if(((cat==null)||(cat.trim().equals("")))&& ((ent!=null)&&(!ent.trim().equals("")))){//Si existe una entrada, un catálogo es obligatorio
							error = datosResources.getString("CAV.1.1.1");
							if(!mensajes.contains(error))
								mensajes.add(error);
							ok= false;
						}
					}
					
					i++;
				}
				//ENTRADAS
				i= 0;
				ok=true;
				while ((ok)&&(i<gn.getIdentificadores().length)){
					
					if (gn.getIdentificadores()[i]!=null){
						String cat= gn.getIdentificadores()[i].getCatalogo();
						String ent= gn.getIdentificadores()[i].getEntrada();
						
						if (((ent==null)||(ent.trim().equals("")))&& ((cat!=null)&&(!cat.trim().equals("")))){//Si existe un catálogo, una entrada es obligatoria
							error = datosResources.getString("CAV.1.1.2");
							if(!mensajes.contains(error))
								mensajes.add(error);
							ok= false;
						}
					}
						
					i++;
				}
				//Validamos el MEC, si no tiene es válido pero si tiene, ha de ser 1 y cumplir el patrón
				ValidarMec validoMec = new ValidarMec();
				//le pasamos el nivel de agregación
				String nivelAgregacion="";
				if (gn.getNivelAgregacion()!=null)
					nivelAgregacion=gn.getNivelAgregacion().getValor()!=null?gn.getNivelAgregacion().getValor():"";
				//vamos a ver si tiene idioma en metametadata
				AvMetametadataVO metametadataSes=lomAv.getMetaMetadata();
				String idiomameta="";
				if ((metametadataSes!=null) && (metametadataSes.getIdioma()!=null) ){
					idiomameta=metametadataSes.getIdioma().getTexto()!=null?metametadataSes.getIdioma().getTexto():"";
				}
				String idiomaMens=((Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage().toString();
				boolean esValidoMec = validoMec.esValidoMEC(gn.getIdentificadores(),nivelAgregacion,idiomameta,mensajes, idiomaMens);				
				if (!esValidoMec) {
					logger.info("El mec no es válido o existen varios mec's");					
				}
				////////////////////////////////////////////////////////////////////////
			
			} else { //al guardar si los identificadores son vacios, los quita, y se quedaran a null
				logger.info("No hay identificadores ");
				error = datosResources.getString("CAV.1.9");
				if(!mensajes.contains(error))
					mensajes.add(error);
			}
			//TITULOS
			boolean todosVacios=true;
			String texto= null;
			if(gn.getTitulo()!=null){
				for(int i=0;i<gn.getTitulo().getTextos().length && todosVacios ;i++)
				{
					if (gn.getTitulo().getTextos()[i]!=null){
						texto=gn.getTitulo().getTextos()[i].getTexto();
					}
					
					if(texto!=null && !texto.trim().equals(""))
					{
						todosVacios=false;
					}
				}
				if(todosVacios){
					error = datosResources.getString("CAV.1.2");
					if(!mensajes.contains(error))
						mensajes.add(error);//debe tener por lo menos un titulo
				}
			}else{
				error = datosResources.getString("CAV.1.2");
				if(!mensajes.contains(error))
					mensajes.add(error);//debe tener por lo menos un titulo
			}
//			if(gn != null && gn.getTitulo() != null){
//				if(gn.getTitulo().getTextos() != null && gn.getTitulo().getTextos().length > 0){
//					for(int i = 0; i < gn.getTitulo().getTextos().length; i++){
//						String text = gn.getTitulo().getTextos()[i].getTexto();
//						String idioma = gn.getTitulo().getTextos()[i].getIdioma();
//						if(text != null && !text.equals("") && (idioma == null || idioma.equals(""))){
//							error = datosResources.getString("CAV.1.2.1");
//							if(!mensajes.contains(error))
//								mensajes.add(error); //si texto está relleno, el idioma también debe estarlo
//						}
//						if((text == null || text.equals("")) && idioma != null && !idioma.equals("")){
//							error = datosResources.getString("CAV.1.2.2");
//							if(!mensajes.contains(error))
//								mensajes.add(error); //si idioma está relleno, el texto también debe estarlo
//						}
//					}
//				}
//			}
			
			//IDIOMA
			todosVacios=true;
			String idioma= null;
			if(gn.getIdiomas()!=null && gn.getIdiomas().length>0){
				for(int i=0;i<gn.getIdiomas().length && todosVacios;i++)
				{
					if (gn.getIdiomas()[i]!=null){
						idioma= gn.getIdiomas()[i].getTexto();
					}		
					if(idioma!=null && !idioma.equals(""))
					{
						todosVacios=false;
					}
				}
				if(todosVacios){
					error = datosResources.getString("CAV.1.3");
					if(!mensajes.contains(error))
						mensajes.add(error);//debe tener por lo menos un idioma
				}
			}else{
				error = datosResources.getString("CAV.1.3");
				if(!mensajes.contains(error))
					mensajes.add(error);//debe tener por lo menos un idioma
			}
			//DESCRIPCION
			todosVacios=true;
			String descripcion= null;
			String idio = null;
			if(gn.getDescripciones()!=null && gn.getDescripciones().length>0){
				for(int i=0;i<gn.getDescripciones().length && todosVacios;i++)
				{
					if (gn.getDescripciones()[i]!=null){
						for(int j=0;j<gn.getDescripciones()[i].getTextos().length && todosVacios;j++)
						{
							if (gn.getDescripciones()[i].getTextos()[j]!=null){
								descripcion=gn.getDescripciones()[i].getTextos()[j].getTexto();
								idio = gn.getDescripciones()[i].getTextos()[j].getIdioma();
							}

							if((descripcion!=null && !descripcion.equals("")) || (idio != null && !idio.equals("")))
							{
								todosVacios=false;
							}
						}
					}
					
				}
				if(todosVacios){
					error = datosResources.getString("CAV.1.4");
					if(!mensajes.contains(error))
						mensajes.add(error);//al menos una descripcion
				}
			}else{
				error = datosResources.getString("CAV.1.4");
				if(!mensajes.contains(error))
					mensajes.add(error);//al menos una descripcion
			}
			if(gn != null){
//				if(gn.getDescripciones() != null && gn.getDescripciones().length > 0){
//					for(int i = 0; i < gn.getDescripciones().length; i++){
//						if(gn.getDescripciones()[i].getTextos() != null && gn.getDescripciones()[i].getTextos().length > 0){
//							for(int j = 0; j < gn.getDescripciones()[i].getTextos().length; j++){
//								String text = gn.getDescripciones()[i].getTextos()[j].getTexto();
//								String idiom = gn.getDescripciones()[i].getTextos()[j].getIdioma();
//								if(text != null && !text.equals("") && (idiom == null || idiom.equals(""))){
//									error = datosResources.getString("CAV.1.4.1");
//									if(!mensajes.contains(error))
//										mensajes.add(error);//si texto está relleno, el idioma también debe estarlo
//								}
//								if((text == null || text.equals("")) && idiom != null && !idiom.equals("")){
//									error = datosResources.getString("CAV.1.4.2");
//									if(!mensajes.contains(error))
//										mensajes.add(error);//si idioma está relleno, el texto también debe estarlo
//								}
//							}
//						}
//					}
//				}
			
				//PALABRA CLAVE
//				if(gn.getPalabrasClave() != null && gn.getPalabrasClave().length > 0){
//					for(int i = 0; i < gn.getPalabrasClave().length; i++){
//						if(gn.getPalabrasClave()[i].getTextos() != null && gn.getPalabrasClave()[i].getTextos().length > 0){
//							for(int j = 0; j < gn.getPalabrasClave()[i].getTextos().length; j++){
//								String text = gn.getPalabrasClave()[i].getTextos()[j].getTexto();
//								String idiom = gn.getPalabrasClave()[i].getTextos()[j].getIdioma();
//								if(text != null && !text.equals("") && (idiom == null || idiom.equals(""))){
//									error = datosResources.getString("CAV.1.5.1");
//									if(!mensajes.contains(error))
//										mensajes.add(error);//si clave está relleno, el idioma también debe estarlo
//								}
//								if((text == null || text.equals("")) && idiom != null && !idiom.equals("")){
//									error = datosResources.getString("CAV.1.5.2");
//									if(!mensajes.contains(error))
//										mensajes.add(error);//si idioma está relleno, clave también debe estarlo
//								}
//							}
//						}
//					}
//				}
				
				//AMBITO
//				if(gn.getAmbitos() != null && gn.getAmbitos().length > 0){
//					for(int i = 0; i < gn.getAmbitos().length; i++){
//						if(gn.getAmbitos()[i].getTextos() != null && gn.getAmbitos()[i].getTextos().length > 0){
//							for(int j = 0; j < gn.getAmbitos()[i].getTextos().length; j++){
//								String text = gn.getAmbitos()[i].getTextos()[j].getTexto();
//								String idiom = gn.getAmbitos()[i].getTextos()[j].getIdioma();
//								if(text != null && !text.equals("") && (idiom == null || idiom.equals(""))){
//									error = datosResources.getString("CAV.1.6.1");
//									if(!mensajes.contains(error))
//										mensajes.add(error);//si ambito está relleno, el idioma también debe estarlo
//								}
//								if((text == null || text.equals("")) && idiom != null && !idiom.equals("")){
//									error = datosResources.getString("CAV.1.6.2");
//									if(!mensajes.contains(error))
//										mensajes.add(error);//si idioma está relleno, ambito también debe estarlo
//								}
//							}
//						}
//					}
//				}
			}
			
			//NIVEL DE AGREGACION
			if(gn.getNivelAgregacion()!=null){
				if(gn.getNivelAgregacion().getValor()==null || gn.getNivelAgregacion().getValor().equals("")){
					error = datosResources.getString("CAV.1.8");
					if(!mensajes.contains(error))
						mensajes.add(error);
				}
			}else{
				error = datosResources.getString("CAV.1.8");
				if(!mensajes.contains(error))
					mensajes.add(error);
			}
		}
		
		//		 -- CICLO DE VIDA --
		if(lomAv.getLifeCycle()!=null){
			AvLifeCycleVO life = lomAv.getLifeCycle();
			
			//VERSION
//			VersionVO vss = life.getVersion();
//			if(vss != null){
//				if(vss.getTextos() != null){
//					for(int j = 0; j < vss.getTextos().length; j++){
//						String texto = vss.getTextos()[j].getTexto();
//						String idioma = vss.getTextos()[j].getIdioma();
//						if(texto != null && !texto.equals("") && (idioma == null || idioma.equals(""))){
//							error = datosResources.getString("CAV.2.1.1");
//							if(!mensajes.contains(error))
//								mensajes.add(error); //si texto está relleno, el idioma también debe estarlo
//						}
//						if((texto == null || texto.equals("")) && idioma != null && !idioma.equals("")){
//							error = datosResources.getString("CAV.2.1.2");
//							if(!mensajes.contains(error))
//								mensajes.add(error); //si idioma está relleno, el texto también debe estarlo
//						}
//					}
//				}
//			}
//			CONTRIBUCION
			String rol;
			boolean errorContRol=false;
			boolean errorContEntidad=false;
			boolean errorContFecha=false;
			boolean errorFecha=true;
			boolean errorFechaVacia=false;
			boolean errorDescVacia=false;
			boolean errorMinutos=false;
			
			boolean todosVacios=true;

			ContribucionVO[] contribuciones= life.getContribuciones();
			

			if(contribuciones!=null && contribuciones.length>0){
				for(int i=0;i<contribuciones.length && (!errorContRol || !errorContEntidad || !errorContFecha) ;i++ )
				{
	//				ROL
					if(!errorContRol)
					{
						if(contribuciones[i].getTipo()!=null){
							rol=contribuciones[i].getTipo().getValor();
							if(rol==null || rol.trim().equals(""))
							{
								errorContRol =true;
								error = datosResources.getString("CAV.2.3.1");
								if(!mensajes.contains(error))
									mensajes.add(error);//El campo Tipo es obligatorio si se va a incluir una contribuci&oacute;n
							}
						}else{
							errorContRol =true;
							error = datosResources.getString("CAV.2.3.1");
							if(!mensajes.contains(error))
								mensajes.add(error);//El campo Tipo es obligatorio si se va a incluir una contribuci&oacute;n
						}
					}	
	//			//entidad
					if(!errorContEntidad)
					{
						todosVacios=true;
						if(contribuciones[i].getEntidades()!=null && contribuciones[i].getEntidades().length>0){
							for(int j=0;j<contribuciones[i].getEntidades().length && todosVacios;j++)
							{
								EntidadVO ent=contribuciones[i].getEntidades()[j];
								
								if (ent != null){
									if ((ent.getTexto()==null)||(ent.getTexto().trim().equals(""))){
										errorContEntidad=true;
										error = datosResources.getString("CAV.2.3.2");
										if(!mensajes.contains(error))
											mensajes.add(error);//El campo Entidad es obligatorio si se va a incluir una contribuci&oacute;n
									}
								}
								
							}
						}else{
							errorContEntidad=true;
							error = datosResources.getString("CAV.2.3.2");
							if(!mensajes.contains(error))
								mensajes.add(error);//El campo Entidad es obligatorio si se va a incluir una contribuci&oacute;n
						}
					}
					
						
					//fecha
					if(!errorContFecha)
					{
						if(contribuciones[i].getFecha()!=null){
							FechaVO fechaCont= contribuciones[i].getFecha();
							DescripcionVO descCont=fechaCont.getDescripcion();
							
							boolean fechaVacia=true;
							
							if (fechaCont!=null && fechaVacia){
								if ((fechaCont.getFecha()==null)||(fechaCont.getFecha().equals(""))){
									fechaVacia=false;
								}
							}else{
								fechaVacia=false;
							}
							
							
							boolean descVacios=true;
							String descFec= null;
							if (descCont!= null && descCont.getTextos()!=null && descCont.getTextos().length>0){
								for(int j=0;j<descCont.getTextos().length && descVacios;j++)
								{
									descFec= descCont.getTextos()[j].getTexto();
									if(descFec==null || descFec.trim().equals(""))
									{
										descVacios=false;// si todos los campos descripcion son vacios
									}
								}
							}else{
								descVacios=false;
							}
							
							if(descVacios && fechaVacia && !errorFecha)
							{
//								errorContFecha=true;
								errorFecha=false;
								error = datosResources.getString("CAV.2.3.3");
								if(!mensajes.contains(error))
									mensajes.add(error);//El campo FECHA	 es obligatorio
							}else if(!descVacios && fechaVacia && !errorFechaVacia && errorFecha ) 
							{
//								errorContFecha=true;
								errorFechaVacia=true;
								error = datosResources.getString("CAV.2.3.3.2");
								if(!mensajes.contains(error))
									mensajes.add(error);//La descripci&oacute;n de la fecha es obligatoria si existe una fecha
							}else if(descVacios && !fechaVacia && !errorDescVacia && errorFecha)
							{
//								errorContFecha=true;
								errorDescVacia=true;
								error = datosResources.getString("CAV.2.3.3.1");
								if(!mensajes.contains(error))
									mensajes.add(error);//La fecha es obligatoria si existe una descripci&oacuete;n de fecha
							}
				
//							if(errorFecha && errorFechaVacia && errorDescVacia && errorMinutos)
//								errorContFecha=true;
						}else{
							errorContFecha=true;
							errorFecha=false;
							error = datosResources.getString("CAV.2.3.3");
							if(!mensajes.contains(error))
								mensajes.add(error);//El campo FECHA	 es obligatorio
						}
					}
				}
			}
			
			//IDIOMA Y DESCRIPCION
//			if(contribuciones != null && contribuciones.length > 0){
//				for(int i = 0; i < contribuciones.length; i++){
//					if(contribuciones[i] != null && contribuciones[i].getFecha() != null && contribuciones[i].getFecha().getDescripcion() != null){
//						if(contribuciones[i].getFecha().getDescripcion().getTextos() != null && contribuciones[i].getFecha().getDescripcion().getTextos().length > 0){
//							for(int j = 0; j < contribuciones[i].getFecha().getDescripcion().getTextos().length; j++){
//								String texto = contribuciones[i].getFecha().getDescripcion().getTextos()[j].getTexto();
//								String idioma = contribuciones[i].getFecha().getDescripcion().getTextos()[j].getIdioma();
//								if(texto != null && !texto.equals("") && (idioma == null || idioma.equals(""))){
//									error = datosResources.getString("CAV.2.3.3.3");
//									if(!mensajes.contains(error))
//										mensajes.add(error); //si descripción está relleno, el idioma también debe estarlo
//								}
//								if((texto == null || texto.equals("")) && idioma != null && !idioma.equals("")){
//									error = datosResources.getString("CAV.2.3.3.4");
//									if(!mensajes.contains(error))
//										mensajes.add(error); //si idioma está relleno, descripción también debe estarlo
//								}
//							}
//						}
//					}
//				}
//			}
		}
		
		//		 -- METAMETADATA --
		if(lomAv.getMetaMetadata()==null){
			error = datosResources.getString("CAV.3");
			if(!mensajes.contains(error))
				mensajes.add(error);
		}
		else{
			AvMetametadataVO mm = lomAv.getMetaMetadata();
			
			int i= 0;
			boolean completo=true;
			boolean todosVacios=true;
			
			//IDENTIFICADORES
			IdentificadorVO[] identificadores= mm.getIdentificadores();
			if(identificadores!=null && identificadores.length>0){
				for(i=0;i<identificadores.length && completo;i++)
				{
					if((identificadores[i].getCatalogo()!=null && identificadores[i].getCatalogo().trim().equals("")) &&
					   (identificadores[i].getEntrada()!=null && !identificadores[i].getEntrada().trim().equals("")) )
					{
						completo=false;
						error = datosResources.getString("CAV.3.1.1");
						if(!mensajes.contains(error))
							mensajes.add(error);//Si existe una entrada para un identificador, un cat&aacute;logo es obligatorio
					}
				}
				completo=true;
				for(i=0;i<identificadores.length && completo;i++)
				{
					if((identificadores[i].getCatalogo()!=null && !identificadores[i].getCatalogo().trim().equals("")) &&
					   (identificadores[i].getEntrada()!=null && identificadores[i].getEntrada().trim().equals("")) )
					{
						completo=false;
						error = datosResources.getString("CAV.3.1.2");
						if(!mensajes.contains(error))
							mensajes.add(error);//Si existe un cat&aacute;logo para un identificador, una entrada es obligatoria
					}
				}
			}
			
			
			//CONTRIBUCION
			String rol;
			boolean errorContRol=false;
			boolean errorContEntidad=false;
			boolean errorContFecha=false;
			boolean errorFecha=true;
			boolean errorFechaVacia=false;
			boolean errorDescVacia=false;
			boolean errorMinutos=false;

			ContribucionVO[] contribuciones= mm.getContribuciones();
			if(contribuciones!=null && contribuciones.length>0){
				for(i=0;i<contribuciones.length && (!errorContRol || !errorContEntidad || !errorContFecha) ;i++ )
				{
	//				ROL
					if(!errorContRol)
					{
						if(contribuciones[i].getTipo()!=null){
							rol=contribuciones[i].getTipo().getValor();
							if(rol==null || rol.trim().equals(""))
							{
								errorContRol =true;
								error = datosResources.getString("CAV.3.2.1");
								if(!mensajes.contains(error))
									mensajes.add(error);//El campo Tipo es obligatorio si se va a incluir una contribuci&oacute;n
							}
						}else{
							errorContRol =true;
							error = datosResources.getString("CAV.3.2.1");
							if(!mensajes.contains(error))
								mensajes.add(error);//El campo Tipo es obligatorio si se va a incluir una contribuci&oacute;n
						}
					}	
	//			//entidad
					if(!errorContEntidad)
					{
						todosVacios=true;
						if(contribuciones[i].getEntidades()!=null && contribuciones[i].getEntidades().length>0){
							for(int j=0;j<contribuciones[i].getEntidades().length && todosVacios;j++)
							{
								EntidadVO ent=contribuciones[i].getEntidades()[j];
								
								if (ent != null){
									if ((ent.getTexto()==null)||(ent.getTexto().trim().equals(""))){
										errorContEntidad=true;
										error = datosResources.getString("CAV.3.2.2");
										if(!mensajes.contains(error))
											mensajes.add(error);//El campo Entidad es obligatorio si se va a incluir una contribuci&oacute;n
									}
								}
								
							}
						}else{
							errorContEntidad=true;
							error = datosResources.getString("CAV.3.2.2");
							if(!mensajes.contains(error))
								mensajes.add(error);//El campo Entidad es obligatorio si se va a incluir una contribuci&oacute;n
						}
					}
						
					//fecha
					if(!errorContFecha)
					{
						if(contribuciones[i].getFecha()!=null){
							FechaVO fechaCont= contribuciones[i].getFecha();
							DescripcionVO descCont=fechaCont.getDescripcion();
							
							boolean fechaVacia=true;
							
							if (fechaCont!=null && fechaVacia){
								if ((fechaCont.getFecha()==null)||(fechaCont.getFecha().equals(""))){
									fechaVacia=false;
								}
							}else{
								fechaVacia=false;
							}
							
							boolean descVacios=true;
							String descFec= null;
							if (descCont!= null){
								for(int j=0;j<descCont.getTextos().length && descVacios;j++)
								{
									descFec= descCont.getTextos()[j].getTexto();
									if(descFec==null && descFec.trim().equals(""))
									{
										descVacios=false;// si todos los campos descripcion son vacios
									}
								}
							}else{
								descVacios=false;
							}
							
							if(descVacios && fechaVacia && !errorFecha)
							{
//								errorContFecha=true;
								errorFecha=false;
								error = datosResources.getString("CAV.3.2.3");
								if(!mensajes.contains(error))
									mensajes.add(error);//El campo FECHA	 es obligatorio
							}else if(!descVacios && fechaVacia && !errorFechaVacia && errorFecha) 
							{
//								errorContFecha=true;
								errorFechaVacia=true;
								error = datosResources.getString("CAV.3.2.3.2");
								if(!mensajes.contains(error))
									mensajes.add(error);//La descripci&oacute;n de la fecha es obligatoria si existe una fecha
							}else if(descVacios && !fechaVacia && !errorDescVacia && errorFecha)
							{
//								errorContFecha=true;
								errorDescVacia=true;
								error = datosResources.getString("CAV.3.2.3.1");
								if(!mensajes.contains(error))
									mensajes.add(error);//La fecha es obligatoria si existe una descripci&oacuete;n de fecha
							}
				
//							if(errorFecha && errorFechaVacia && errorDescVacia && errorMinutos)
//								errorContFecha=true;
						}else{
							errorContFecha=true;
							errorFecha=true;
							error = datosResources.getString("CAV.3.2.3");
							if(!mensajes.contains(error))
								mensajes.add(error);//El campo FECHA	 es obligatorio
						}
					}
				}
				//IDIOMA Y DESCRIPCION
//				if(contribuciones != null && contribuciones.length > 0){
//					for(i = 0; i < contribuciones.length; i++){
//						if(contribuciones[i] != null && contribuciones[i].getFecha() != null && contribuciones[i].getFecha().getDescripcion() != null){
//							if(contribuciones[i].getFecha().getDescripcion().getTextos() != null && contribuciones[i].getFecha().getDescripcion().getTextos().length > 0){
//								for(int j = 0; j < contribuciones[i].getFecha().getDescripcion().getTextos().length; j++){
//									String texto = contribuciones[i].getFecha().getDescripcion().getTextos()[j].getTexto();
//									String idioma = contribuciones[i].getFecha().getDescripcion().getTextos()[j].getIdioma();
//									if(texto != null && !texto.equals("") && (idioma == null || idioma.equals(""))){
//										error = datosResources.getString("CAV.3.3.3.3");
//										if(!mensajes.contains(error))
//											mensajes.add(error); //si descripción está relleno, el idioma también debe estarlo
//									}
//									if((texto == null || texto.equals("")) && idioma != null && !idioma.equals("")){
//										error = datosResources.getString("CAV.3.3.3.4");
//										if(!mensajes.contains(error))
//											mensajes.add(error); //si idioma está relleno, descripción también debe estarlo
//									}
//								}
//							}
//						}
//					}
//				}
			}

//			EsquemaDeMetadatosVO[] esquemasMeta= mm.getEsquemas(); No es obligatorio
//			//ESQUEMA METADATOS
//			todosVacios=true;
//			String esquema;
//			if(esquemasMeta!=null && esquemasMeta.length>0){
//				for(i=0;i<esquemasMeta.length && todosVacios;i++)
//				{
//					esquema=esquemasMeta[i].getTexto();
//					if(esquema!=null && !esquema.trim().equals(""))
//					{
//						todosVacios=false;
//					}
//				}
//				if(todosVacios)
//					mensajes.add(datosResources.getString("CAV.3.3"));//El campo Esquema de metadatos es obligatorio
//			}else{
//				mensajes.add(datosResources.getString("CAV.3.3"));//El campo Esquema de metadatos es obligatorio
//			}
			
			
			//IDIOMA
			if(mm.getIdioma()!=null){
				if(mm.getIdioma().getTexto()==null || mm.getIdioma().getTexto().equals("")){
					error = datosResources.getString("CAV.3.4");
					if(!mensajes.contains(error))
						mensajes.add(error);//El campo Idioma es obligatorio
				}
			}else{
				error = datosResources.getString("CAV.3.4");
				if(!mensajes.contains(error))
					mensajes.add(error);//El campo Idioma es obligatorio
			}
		}
		
		//		 -- TECHNICAL --
		if (lomAv.getTechnical()!=null){
			
			AvTechnicalVO tec= lomAv.getTechnical();
			
//			NOMBRE

			
			if(tec.getRequisitos()!=null && tec.getRequisitos().length>0){
				
				ok=true;
				int i= 0;
				while ((ok)&&(i<tec.getRequisitos().length)){
					AgregadorORVO[] cat= tec.getRequisitos()[i].getAgregadoresOR();
					if(cat!=null && cat.length>0){
						int j=0;
						while((ok)&&(j<cat.length)){
							String nombre="";
							String tipo="";
							String maxi="";
							String mini="";
							SourceValueVO sNombre= cat[j].getNombre();
							SourceValueVO sTipo=cat[j].getTipo();
							if(sNombre!=null){
								nombre=sNombre.getValor();
							}
							if(sTipo!=null){
								tipo=sTipo.getValor();
							}
							VersionMinVO min=cat[j].getVersionMin();
							if(min!=null && min.getTexto()!=null){
								mini=min.getTexto().trim();
							}
							
							VersionMaxVO max=cat[j].getVersionMax();
							if(max!=null && max.getTexto()!=null){
								maxi=max.getTexto().trim();
							}

							if(((nombre==null)||(nombre.equals("")))&&(((!tipo.equals("")))||((!mini.equals("")))||((!maxi.equals(""))))){
								error = datosResources.getString("CAV.4.4.1.2");
								if(!mensajes.contains(error))
									mensajes.add(error);
								ok= false;
							}
							
							j++;		
						}
						
					}
						
					i++;
				}
				
				//TIPO
				ok=true;
				i= 0;
				while ((ok)&&(i<tec.getRequisitos().length)){
					AgregadorORVO[] cat= tec.getRequisitos()[i].getAgregadoresOR();
					if(cat!=null && cat.length>0){
						int j=0;
						while((ok)&&(j<cat.length)){
							String nombre="";
							String tipo="";
							String maxi="";
							String mini="";
							SourceValueVO sNombre= cat[j].getNombre();
							SourceValueVO sTipo=cat[j].getTipo();
							if(sNombre!=null){
								nombre=sNombre.getValor();
							}
							if(sTipo!=null){
								tipo=sTipo.getValor();
							}
							VersionMinVO min=cat[j].getVersionMin();
							if(min!=null && min.getTexto()!=null){
								mini=min.getTexto().trim();
							}
							
							VersionMaxVO max=cat[j].getVersionMax();
							if(max!=null && max.getTexto()!=null){
								maxi=max.getTexto().trim();
							}
							if(((tipo==null)||(tipo.equals("")))&&(((!nombre.equals("")))||((!mini.equals("")))||((!maxi.equals(""))))){
								error = datosResources.getString("CAV.4.4.1.1");
								if(!mensajes.contains(error))
									mensajes.add(error);
								ok= false;
							}
							
							j++;		
						}
					}
						i++;
					
				}
			}
				
			//DURACION
			boolean todosVacios=true;

	        String descripcion;
	        if(tec.getDuracion()!=null){
	        	if((tec.getDuracion().getDescripcion()!=null)&&(tec.getDuracion().getDescripcion().getTextos()!=null)&&(tec.getDuracion().getDescripcion().getTextos().length>0)){
			        for(int i=0;i<tec.getDuracion().getDescripcion().getTextos().length && todosVacios;i++)
			        {
	                    descripcion=tec.getDuracion().getDescripcion().getTextos()[i].getTexto();
	                    if(descripcion!=null && !descripcion.trim().equals(""))
	                    {
	                         todosVacios=false;
	                    }
			        }
	        	}
	        }
	        boolean duracionVacia=true;
	        if(tec.getDuracion()!=null){
		        if ((tec.getDuracion().getDuracion() != null)&&(!tec.getDuracion().getDuracion().equals(""))){
		        	duracionVacia=false;
		        }
	        }

	        if(todosVacios && !duracionVacia){
	        	error = datosResources.getString("CAV.4.7.2");
	        	if(!mensajes.contains(error))
	        		mensajes.add(error);//La descripci&oacute;n dela duraci&oacute;n es obligatoria si existe una duraci&oacute;n
	        }
	        if(!todosVacios && duracionVacia){
	        	error = datosResources.getString("CAV.4.7.1");
	        	if(!mensajes.contains(error))
	        		mensajes.add(error);//La duraci&oacute;n es obligatoria si existe una descripci&oacute;n de duraci&oacute;n
	        }
//		        if(tec != null && tec.getDuracion() != null && tec.getDuracion().getDescripcion() != null){
//			        if(tec.getDuracion().getDescripcion().getTextos() != null && tec.getDuracion().getDescripcion().getTextos().length > 0){
//				        for(i = 0; i < tec.getDuracion().getDescripcion().getTextos().length; i++){
//				        	String texto = tec.getDuracion().getDescripcion().getTextos()[i].getTexto();
//				        	String idioma = tec.getDuracion().getDescripcion().getTextos()[i].getIdioma();
//				        	if(texto != null && !texto.equals("") && (idioma == null || idioma.equals(""))){
//				        		error = datosResources.getString("CAV.4.7.3");
//				        		if(!mensajes.contains(error))
//				        			mensajes.add(error); //si descripción está relleno, el idioma también debe estarlo
//				        	}
//				        	if((texto == null || texto.equals("")) && idioma != null && !idioma.equals("")){
//				        		error = datosResources.getString("CAV.4.7.4");
//				        		if(!mensajes.contains(error))
//				        			mensajes.add(error); //si idioma está relleno, descripción también debe estarlo
//				        	}
//				        }
//			        }
//		        }
	        
	      //PAUTAS DE INSTALACION
//		        if(tec != null && tec.getPautasInstalacion() != null){
//			        if(tec.getPautasInstalacion().getTextos() != null && tec.getPautasInstalacion().getTextos().length > 0){
//				        for(i = 0; i < tec.getPautasInstalacion().getTextos().length; i++){
//				        	String texto = tec.getPautasInstalacion().getTextos()[i].getTexto();
//				        	String idioma = tec.getPautasInstalacion().getTextos()[i].getIdioma();
//				        	if(texto != null && !texto.equals("") && (idioma == null || idioma.equals(""))){
//				        		error = datosResources.getString("CAV.4.5.1");
//				        		if(!mensajes.contains(error))
//				        			mensajes.add(error); //si descripción está relleno, el idioma también debe estarlo
//				        	}
//				        	if((texto == null || texto.equals("")) && idioma != null && !idioma.equals("")){
//				        		error = datosResources.getString("CAV.4.5.2");
//				        		if(!mensajes.contains(error))
//				        			mensajes.add(error); //si idioma está relleno, descripción también debe estarlo
//				        	}
//				        }
//			        }
//		        }
	        
	      //OTROS REQUISITOS DE PLATAFORMA
//		        if(tec != null && tec.getOtrosRequisitos() != null){
//			        if(tec.getOtrosRequisitos().getTextos() != null && tec.getOtrosRequisitos().getTextos().length > 0){
//				        for(i = 0; i < tec.getOtrosRequisitos().getTextos().length; i++){
//				        	String texto = tec.getOtrosRequisitos().getTextos()[i].getTexto();
//				        	String idioma = tec.getOtrosRequisitos().getTextos()[i].getIdioma();
//				        	if(texto != null && !texto.equals("") && (idioma == null || idioma.equals(""))){
//				        		error = datosResources.getString("CAV.4.6.1");
//				        		if(!mensajes.contains(error))
//				        			mensajes.add(error); //si descripción está relleno, el idioma también debe estarlo
//				        	}
//				        	if((texto == null || texto.equals("")) && idioma != null && !idioma.equals("")){
//				        		error = datosResources.getString("CAV.4.6.2");
//				        		if(!mensajes.contains(error))
//				        			mensajes.add(error); //si idioma está relleno, descripción también debe estarlo
//				        	}
//				        }
//			        }
//		        }
		}
		
		//USO EDUCATIVO
		if ((lomAv.getEducational()==null)||(lomAv.getEducational().length==0)){
			mensajes.add(datosResources.getString("CAV.5")); //Al menos debe existir una categor&iacute;a Uso Educativo
		}
		else{
			for (int indexEdu= 0; indexEdu<lomAv.getEducational().length; indexEdu++){
				AvEducationalVO edu= lomAv.getEducational()[indexEdu];
				
				boolean todosVacios=true;
				int i;

			  	//TIPO DE RECURSO DE APRENDIZAJE
				String recurso= null;
				if(edu.getTiposrecursoedu()!=null && edu.getTiposrecursoedu().length>0){
					for(i=0;i<edu.getTiposrecursoedu().length && todosVacios;i++)
					{
						recurso= edu.getTiposrecursoedu()[i].getValor();
						if(recurso!=null && !recurso.equals(""))
						{
							todosVacios=false;
						}
					}
					if(todosVacios){
						error = datosResources.getString("CAV.5.2.1")+" "+ (indexEdu+1);
						if(!mensajes.contains(error))
							mensajes.add(error);//El campo Tipo de recurso es obligatorio
					}
				}else{
					error = datosResources.getString("CAV.5.2.1")+" "+ (indexEdu+1);
					if(!mensajes.contains(error))
						mensajes.add(error);//El campo Tipo de recurso es obligatorio
				}
				//IDIOMA
				todosVacios=true;
				String idioma= null;
				if(edu.getIdiomas()!=null && edu.getIdiomas().length>0){
					for(i=0;i<edu.getIdiomas().length && todosVacios;i++)
					{
						idioma= edu.getIdiomas()[i].getTexto();
						if(idioma!=null && !idioma.equals(""))
						{
							todosVacios=false;
						}
					}
					if(todosVacios){
						error = datosResources.getString("CAV.5.11.1")+" "+(indexEdu+1);
						if(!mensajes.contains(error))
							mensajes.add(error);//El campo Idioma es obligatorio
					}
				}else{
					error = datosResources.getString("CAV.5.11.1")+" "+(indexEdu+1);
					if(!mensajes.contains(error))
						mensajes.add(error);//El campo Idioma es obligatorio
				}
				
				//EDAD DEL DESTINATARIO
//				RangoEdadVO[] rangos = (RangoEdadVO[]) edu.getRangoedades();
//				if(rangos != null && rangos.length > 0){
//					for(i = 0; i < rangos.length; i++){
//						if(rangos[i].getTextos() != null && rangos[i].getTextos().length > 0){
//							for(int j = 0; j < rangos[i].getTextos().length; j++){
//								String texto = rangos[i].getTextos()[j].getTexto();
//								String idiom = rangos[i].getTextos()[j].getIdioma();
//								if(texto != null && !texto.equals("") && (idiom == null || idiom.equals(""))){
//									error = datosResources.getString("CAV.5.6.1") + " " + (indexEdu + 1);
//									if(!mensajes.contains(error))
//										mensajes.add(error); //si descripción está relleno, el idioma también debe estarlo
//								}
//								if((texto == null || texto.equals("")) && idiom != null && !idiom.equals("")){
//									error = datosResources.getString("CAV.5.6.2") + " " + (indexEdu + 1);
//									if(!mensajes.contains(error))
//										mensajes.add(error); //si idioma está relleno, descripción también debe estarlo
//								}
//							}
//						}
//					}
//				}
				
			  	//TIEMPO DE APRENDIZAJE
				//DESCRIPCIONES
				todosVacios=true;
				String descripcion;
				if(edu.getTiempoAprendizaje()!=null && edu.getTiempoAprendizaje().getDescripcion()!=null && edu.getTiempoAprendizaje().getDescripcion().getTextos()!=null && edu.getTiempoAprendizaje().getDescripcion().getTextos().length>0){
					for(i=0;i<edu.getTiempoAprendizaje().getDescripcion().getTextos().length && todosVacios;i++)
					{
							descripcion=edu.getTiempoAprendizaje().getDescripcion().getTextos()[i].getTexto();
							if(descripcion!=null && !descripcion.trim().equals(""))
							{
								todosVacios=false;
							}
					}
				}
				
//				if(edu != null && edu.getTiempoAprendizaje() != null && edu.getTiempoAprendizaje().getDescripcion() != null){
//					if(edu.getTiempoAprendizaje().getDescripcion().getTextos() != null && edu.getTiempoAprendizaje().getDescripcion().getTextos().length > 0){
//						for(i = 0; i < edu.getTiempoAprendizaje().getDescripcion().getTextos().length; i++){
//							String texto = edu.getTiempoAprendizaje().getDescripcion().getTextos()[i].getTexto();
//							String idiom = edu.getTiempoAprendizaje().getDescripcion().getTextos()[i].getIdioma();
//							if(texto != null && !texto.equals("") && (idiom == null || idiom.equals(""))){
//								error = datosResources.getString("CAV.5.9.3") + " " + (indexEdu + 1);
//								if(!mensajes.contains(error))
//									mensajes.add(error); //si descripción está relleno, el idioma también debe estarlo
//							}
//							if((texto == null || texto.equals("")) && idiom != null && !idiom.equals("")){
//								error = datosResources.getString("CAV.5.9.4") + " " + (indexEdu + 1);
//									mensajes.add(error); //si idioma está relleno, descripción también debe estarlo
//							}
//						}
//					}
//				}
				
				//DURACION
				boolean duracionVacia=true;
				if(edu.getTiempoAprendizaje()!=null){
					if ((edu.getTiempoAprendizaje().getDuracion()!=null)&&(!edu.getTiempoAprendizaje().getDuracion().equals(""))){
						duracionVacia=false;
					}
				}
							
				if(todosVacios && !duracionVacia){
					error = datosResources.getString("CAV.5.9.2.1")+" "+(indexEdu+1);
					if(!mensajes.contains(error))
						mensajes.add(error);//La descripci&oacute;n dela duraci&oacute;n es obligatoria si existe una duraci&oacute;n
				}
				if(!todosVacios && duracionVacia){
					error = datosResources.getString("CAV.5.9.1.1")+" "+(indexEdu+1);
					if(!mensajes.contains(error))
						mensajes.add(error);//La duraci&oacute;n es obligatoria si existe una descripci&oacute;n de duraci&oacute;n
				}
				
				//ORIENTACIONES DIDACTICAS
//				DescripcionVO[] descripciones = (DescripcionVO[]) edu.getDescripciones();
//				if(descripciones != null && descripciones.length > 0){
//					for(i = 0; i < descripciones.length; i++){
//						if(descripciones[i].getTextos() != null && descripciones[i].getTextos().length > 0){
//							for(int j = 0; j < descripciones[i].getTextos().length; j++){
//								String texto = descripciones[i].getTextos()[j].getTexto();
//								String idiom = descripciones[i].getTextos()[j].getIdioma();
//								if(texto != null && !texto.equals("") && (idiom == null || idiom.equals(""))){
//									error = datosResources.getString("CAV.5.10.1") + " " + (indexEdu + 1);
//									if(!mensajes.contains(error))
//										mensajes.add(error); //si descripción está relleno, el idioma también debe estarlo
//								}
//								if((texto == null || texto.equals("")) && idiom != null && !idiom.equals("")){
//									error = datosResources.getString("CAV.5.10.2") + " " + (indexEdu + 1);
//									if(!mensajes.contains(error))
//										mensajes.add(error); //si idioma está relleno, descripción también debe estarlo
//								}
//							}
//						}
//					}
//				}
			}
			
		}
		
		// -- DERECHOS --
		if(lomAv.getDerechos()==null){
			error = datosResources.getString("CAV.6");
			if(!mensajes.contains(error))
				mensajes.add(error);
		}
		else{
//			COPYRIGHT 
			AvRightsVO de= lomAv.getDerechos();
			if(de!=null && de.getDerechosDeAutor()!=null){
				String copyright= de.getDerechosDeAutor().getValor();
		    	if(copyright==null || copyright.equals(""))
		    	{
		    		error = datosResources.getString("CAV.6.2");
		    		if(!mensajes.contains(error))
		    			mensajes.add(error);
		    	}
			}else{
				error = datosResources.getString("CAV.6.2");
				if(!mensajes.contains(error))
					mensajes.add(error);
			}
			
			//DESCRIPCION DE CONDICIONES DE UTILIZACION
//			if(lomAv.getDerechos() != null && lomAv.getDerechos().getDescripcion() != null){
//				if(lomAv.getDerechos().getDescripcion().getTextos() != null && lomAv.getDerechos().getDescripcion().getTextos().length > 0){
//					for(int i = 0; i < lomAv.getDerechos().getDescripcion().getTextos().length; i++){
//						String texto = lomAv.getDerechos().getDescripcion().getTextos()[i].getTexto();
//						String idiom = lomAv.getDerechos().getDescripcion().getTextos()[i].getIdioma();
//						if(texto != null && !texto.equals("") && (idiom == null || idiom.equals(""))){
//							error = datosResources.getString("CAV.6.3.1");
//							if(!mensajes.contains(error))
//								mensajes.add(error); //si descripción está relleno, el idioma también debe estarlo
//						}
//						if((texto == null || texto.equals("")) && idiom != null && !idiom.equals("")){
//							error = datosResources.getString("CAV.6.3.2");
//							if(!mensajes.contains(error))
//								mensajes.add(error); //si idioma está relleno, descripción también debe estarlo
//						}
//			    	}
//				}
//			}
	    	
	    	//ACCESO
			if(de!=null && de.getAcceso()!=null && de.getAcceso().getTipo()!=null){
		    	String tipoAcceso= de.getAcceso().getTipo().getValor();
		    	if(tipoAcceso==null || tipoAcceso.equals(""))
		    	{
		    		error = datosResources.getString("CAV.6.4.1");
		    		if(!mensajes.contains(error))
		    			mensajes.add(error);
		    	}
			}else{
				error = datosResources.getString("CAV.6.4.1");
				if(!mensajes.contains(error))
					mensajes.add(error);
			}
	    	
	    	//AMBITO
			if(de!=null && 
			   de.getAcceso()!=null && de.getAcceso().getDescripcion()!=null && 
			   de.getAcceso().getDescripcion().getTextos()!=null && de.getAcceso().getDescripcion().getTextos().length>0)
			{
				if(de.getAcceso().getTipo()!=null && de.getAcceso().getTipo().getValor() !=null && !de.getAcceso().getTipo().getValor().equalsIgnoreCase("universal"))
				{
			    	String comunidades= de.getAcceso().getDescripcion().getTextos()[0].getTexto();
			    	if(comunidades==null || comunidades.trim().equals(""))
			    	{
			    		error = datosResources.getString("CAV.6.4.2");
			    		if(!mensajes.contains(error))
			    			mensajes.add(error);//Es necesario seleccionar al menos un &aacute;mbito
			    	}
				}
			}
		}
		
		// -- RELACION --
		if ((lomAv.getRelaciones()!=null)&&(lomAv.getRelaciones().length>0)){
			
			for (int indexRelacion= 0; indexRelacion<lomAv.getRelaciones().length; indexRelacion++){
				AvRelationVO re= lomAv.getRelaciones()[indexRelacion];

				ok= true;
				
				//TIPO
				if((re.getTipo()!=null)){
					String tip=re.getTipo().getValor();
					if((tip==null)||(tip.equals(""))){
						error = datosResources.getString("CAV.7.1.7")+" "+(indexRelacion+1);
						if(!mensajes.contains(error))
							mensajes.add(error);
					}
				}
				
				//IDENTIFICADOR
				if (ok && ((re.getRecurso() == null) ||(re.getRecurso().getIdentificador()== null))&&(re.getTipo()!=null))
					mensajes.add(datosResources.getString("CAV.7.3.7")+" "+(indexRelacion+1));
				if((ok)&&(re.getRecurso()!=null)&&(re.getRecurso().getIdentificador()!=null)&&(re.getTipo()!=null)){
					String cat=re.getRecurso().getIdentificador().getCatalogo();
					String ent=re.getRecurso().getIdentificador().getEntrada();
					
					if(((cat==null)||(cat.trim().equals("")))&& ((ent==null)||(ent.trim().equals("")))){//Si existe una entrada, un catálogo es obligatorio
						error = datosResources.getString("CAV.7.3.7")+" "+(indexRelacion+1);
						if(!mensajes.contains(error))
							mensajes.add(error);
						ok= false;
					}
					
				}
	//			CATALOGOS
				ok=true;
				if((ok)&&(re.getRecurso()!=null)&&(re.getRecurso().getIdentificador()!=null)&&(re.getTipo()!=null)){
					String cat=re.getRecurso().getIdentificador().getCatalogo();
					String ent=re.getRecurso().getIdentificador().getEntrada();
					
					if(((cat==null)||(cat.trim().equals("")))&& ((ent!=null)&&(!ent.trim().equals("")))){//Si existe una entrada, un catálogo es obligatorio
						error = datosResources.getString("CAV.7.3.1.7")+" "+(indexRelacion+1);
						if(!mensajes.contains(error))
							mensajes.add(error);
						ok= false;
					}
					
				}
				
				//ENTRADAS
				if((ok)&&(re.getRecurso()!=null)&&(re.getRecurso().getIdentificador()!=null)){
					String cat=re.getRecurso().getIdentificador().getCatalogo();
					String ent=re.getRecurso().getIdentificador().getEntrada();
					
					if (((ent==null)||(ent.trim().equals("")))&& ((cat!=null)&&(!cat.trim().equals("")))){//Si existe un catálogo, una entrada es obligatoria
						error = datosResources.getString("CAV.7.3.2.7")+ " "+(indexRelacion+1);
						if(!mensajes.contains(error))
							mensajes.add(error);
						ok= false;
					}
					
				}
				//DESCRIPCIONES
//				if(re != null && re.getRecurso() != null){
//					if(re.getRecurso().getDescripciones() != null && re.getRecurso().getDescripciones().length > 0){
//						for(int i = 0; i < re.getRecurso().getDescripciones().length; i++){
//							if(re.getRecurso().getDescripciones()[i].getTextos() != null && re.getRecurso().getDescripciones()[i].getTextos().length > 0){
//								for(int j = 0; j < re.getRecurso().getDescripciones()[i].getTextos().length; j++){
//									String texto = re.getRecurso().getDescripciones()[i].getTextos()[j].getTexto();
//									String idioma = re.getRecurso().getDescripciones()[i].getTextos()[j].getIdioma();
//									if(texto != null && !texto.equals("") && (idioma == null || idioma.equals(""))){
//										error = datosResources.getString("CAV.7.3.3") + " " + (indexRelacion + 1);
//										if(!mensajes.contains(error))
//											mensajes.add(error); //si descripción está relleno, el idioma también debe estarlo
//									}
//									if((texto == null || texto.equals("")) && idioma != null && !idioma.equals("")){
//										error = datosResources.getString("CAV.7.3.4") + " " + (indexRelacion + 1);
//										if(!mensajes.contains(error))
//											mensajes.add(error); //si idioma está relleno, descripción también debe estarlo
//									}
//								}
//							}
//						}
//					}
//				}
			}
		}
		
		// -- ANOTACION --
		if ((lomAv.getAnotaciones()!=null)&&(lomAv.getAnotaciones().length>0)){
			int i;
			boolean todosVacios=true;
			
			for (int indexAnotacion= 0; indexAnotacion<lomAv.getAnotaciones().length; indexAnotacion++){
				
				AvAnnotationVO an= lomAv.getAnotaciones()[indexAnotacion];
				//ENTIDAD
				if(an.getEntidad()!=null){
					String entidad= an.getEntidad().getTexto();
					if ((entidad==null)||(entidad.trim().equals(""))){
						error = datosResources.getString("CAV.8.1.8")+" "+(indexAnotacion+1);
						if(!mensajes.contains(error))
							mensajes.add(error);//El campo Entidad es obligatorio
					}
				}
				
				//FECHA
				boolean fechaVacia=false;
				if(an.getFecha()!=null){
					String fecha= an.getFecha().getFecha();
					if ((fecha==null)||(fecha.equals(""))){
						fechaVacia=true;
					}
				}else{
					fechaVacia=true;
				}
				
				boolean descVacios=false;
				if(an.getFecha()!=null && an.getFecha().getDescripcion()!=null && an.getFecha().getDescripcion().getTextos()!=null && an.getFecha().getDescripcion().getTextos().length>0){
					String descFec= an.getFecha().getDescripcion().getTextos()[0].getTexto();
					if ((descFec==null)||(descFec.trim().equals(""))){
						descVacios=true;
					}
				}else{
					descVacios=true;
				}
	
	
				if(descVacios && fechaVacia)
				{
					error = datosResources.getString("CAV.8.2.8")+" "+(indexAnotacion+1);
					if(!mensajes.contains(error))
						mensajes.add(error);//El campo FECHA es obligatorio
				}else if(descVacios && !fechaVacia)
				{
					error = datosResources.getString("CAV.8.2.2.8")+" "+(indexAnotacion+1);
					if(!mensajes.contains(error))
						mensajes.add(error);//La descripci&oacute;n de la fecha es obligatoria si existe una fecha
				}else if(!descVacios && fechaVacia)
				{
					error = datosResources.getString("CAV.8.2.1.8")+" "+(indexAnotacion+1);
					if(!mensajes.contains(error))
						mensajes.add(error);//La fecha es obligatoria si existe una descripci&oacuete;n de fecha
				}
				
//				if(an != null && an.getFecha() != null && an.getFecha().getDescripcion() != null){
//					if(an.getFecha().getDescripcion().getTextos() != null && an.getFecha().getDescripcion().getTextos().length > 0){
//						for(i = 0; i < an.getFecha().getDescripcion().getTextos().length; i++){
//							String texto = an.getFecha().getDescripcion().getTextos()[i].getTexto();
//							String idioma = an.getFecha().getDescripcion().getTextos()[i].getIdioma();
//							if(texto != null && !texto.equals("") && (idioma == null || idioma.equals(""))){
//								error = datosResources.getString("CAV.8.2.9") + " " + (indexAnotacion + 1);
//								if(!mensajes.contains(error))
//									mensajes.add(error); //si descripción está relleno, el idioma también debe estarlo
//							}
//							if((texto == null || texto.equals("")) && idioma != null && !idioma.equals("")){
//								error = datosResources.getString("CAV.8.2.10") + " " + (indexAnotacion + 1);
//								if(!mensajes.contains(error))
//									mensajes.add(error); //si idioma está relleno, descripción también debe estarlo
//							}
//						}
//					}
//				}
				
				//DESCRIPCION
				String desc;
				if(an.getDescripcion()!=null && an.getDescripcion().getTextos()!=null && an.getDescripcion().getTextos().length>0){
					for(i=0;i<an.getDescripcion().getTextos().length && todosVacios;i++)
					{
						desc= an.getDescripcion().getTextos()[i].getTexto();
						if(desc!=null && !desc.trim().equals(""))
						{
							todosVacios=false;
						}
					}
					if(todosVacios){
						error = datosResources.getString("CAV.8.3.8")+" "+(indexAnotacion+1);
						if(!mensajes.contains(error))
							mensajes.add(error);//El campo Descripci&oacute;n es obligatorio
					}
				}else{
					error = datosResources.getString("CAV.8.3.8")+" "+(indexAnotacion+1);
					if(!mensajes.contains(error))
						mensajes.add(error);//El campo Descripci&oacute;n es obligatorio
				}
				
//				if(an != null && an.getDescripcion() != null){
//					if(an.getDescripcion().getTextos() != null && an.getDescripcion().getTextos().length > 0){
//						for(i = 0; i < an.getDescripcion().getTextos().length; i++){
//							String texto = an.getDescripcion().getTextos()[i].getTexto();
//							String idioma = an.getDescripcion().getTextos()[i].getIdioma();
//							if(texto != null && !texto.equals("") && (idioma == null || idioma.equals(""))){
//								error = datosResources.getString("CAV.8.3.9") + " " + (indexAnotacion + 1);
//								if(!mensajes.contains(error))
//									mensajes.add(error); //si descripción está relleno, el idioma también debe estarlo
//							}
//							if((texto == null || texto.equals("")) && idioma != null && !idioma.equals("")){
//								error = datosResources.getString("CAV.8.3.10") + " " + (indexAnotacion + 1);
//								if(!mensajes.contains(error))
//									mensajes.add(error); //si idioma está relleno, descripción también debe estarlo
//							}
//						}
//					}
//				}
			}
		}
		// --CLASIFICACION--
		
		if ((lomAv.getClasificaciones()!=null)&&(lomAv.getClasificaciones().length>0)){
			
			//comprobamos las clasificaciones
			String idioma = datosResources.getLocale().getLanguage();
			AvClassificationVO[]cl1 = lomAv.getClasificaciones();
//			//para pasar el idioma al validador
//		  	String idioma = os.getIdioma();
		  	if ((cl1!=null)&&(cl1.length>0)){
		  		
		  		ValidarTaxo validar = new ValidarTaxo();
				boolean validado = validar.validaTaxo(cl1, mensajes, datosResources, idioma);
		  	}
		  	
			for (int indexClasificacion= 0; indexClasificacion<lomAv.getClasificaciones().length; indexClasificacion++){
				AvClassificationVO cl= lomAv.getClasificaciones()[indexClasificacion];
				
			
				if(cl.getProposito()!=null){
					if ((cl.getProposito().getValor()==null)||(cl.getProposito().getValor().equals(""))){
						error = datosResources.getString("CAV.9.1.9")+" "+(indexClasificacion+1);
						if(!mensajes.contains(error))
							mensajes.add(error);
					}
				}
				else{
					error = datosResources.getString("CAV.9.1.9")+" "+(indexClasificacion+1);
					if(!mensajes.contains(error))
						mensajes.add(error);
				}
			  	boolean errorDado=true;
			  	
			  	
				if(cl.getRutasTaxonomicas()!=null && cl.getRutasTaxonomicas().length>0 && errorDado){
					for (int i= 0; i<cl.getRutasTaxonomicas().length; i++){
						boolean vacio=false;
						boolean propVacio=false;
						boolean taxonVacio=false;
						boolean todoVacio=true;
						if(cl.getRutasTaxonomicas().length==1){
							if(cl.getRutasTaxonomicas()[0].getFuente()!=null && cl.getRutasTaxonomicas()[0].getFuente().getTextos()!=null && cl.getRutasTaxonomicas()[0].getFuente().getTextos().length>0){
								String sce= cl.getRutasTaxonomicas()[i].getFuente().getTextos()[0].getTexto();
								if ((sce==null)||(sce.equals(""))){
									propVacio=true;
								}
							}
							if(cl.getRutasTaxonomicas()[0].getTaxones()!=null && cl.getRutasTaxonomicas()[0].getTaxones().length>0 ){
								if(cl.getRutasTaxonomicas()[0].getTaxones().length==1){
									if(cl.getRutasTaxonomicas()[0].getTaxones()[0].getId()!=null &&  cl.getRutasTaxonomicas()[0].getTaxones()[0].getEntry()!=null &&
											cl.getRutasTaxonomicas()[0].getTaxones()[0].getEntry().getTextos()!=null && cl.getRutasTaxonomicas()[0].getTaxones()[0].getEntry().getTextos().length>0){ 
											String id= cl.getRutasTaxonomicas()[0].getTaxones()[0].getId().getTexto();
											String entry= cl.getRutasTaxonomicas()[0].getTaxones()[0].getEntry().getTextos()[0].getTexto();
											if ((id==null)||(id.equals(""))||((entry==null)||(entry.equals("")))){
												taxonVacio=true;
											}
									}
								}
								
							}
							if(propVacio && taxonVacio){
								error = datosResources.getString("CAV.9.2")+" "+(indexClasificacion+1);
								if(!mensajes.contains(error))
									mensajes.add(error);
								todoVacio=false;
							}
						}
						
						if(cl.getRutasTaxonomicas()[i].getFuente()!=null && cl.getRutasTaxonomicas()[i].getFuente().getTextos()!=null && cl.getRutasTaxonomicas()[i].getFuente().getTextos().length>0 && todoVacio){
							String sce= cl.getRutasTaxonomicas()[i].getFuente().getTextos()[0].getTexto();
							if ((sce==null)||(sce.equals(""))){
								error = datosResources.getString("CAV.9.2.1")+" "+(indexClasificacion+1);
								if(!mensajes.contains(error))
									mensajes.add(error);
								errorDado=false;
							}	
						}
						else{
							error = datosResources.getString("CAV.9.2.1")+" "+(indexClasificacion+1);
							if(!mensajes.contains(error))
								mensajes.add(error);
							errorDado=false;
						}
						
						
						if(cl.getRutasTaxonomicas()[i].getTaxones()!=null && cl.getRutasTaxonomicas()[i].getTaxones().length>0){
							if(cl.getRutasTaxonomicas()[i].getTaxones().length==1){
								TaxonVO[] taxones=cl.getRutasTaxonomicas()[i].getTaxones();
								boolean identVacio=false;
								if(taxones!=null && taxones.length>0){
									EntryVO entrada=taxones[0].getEntry();
									IdVO identif=taxones[0].getId();
									
									if(identif!=null && !identif.equals("")){
										String identifiTexto=identif.getTexto();
										if(identifiTexto!=null && !identifiTexto.equals("")){
											identVacio=false;
										}else{
											identVacio=true;
										}
									}else{
										identVacio=true;
									}
									boolean entradaLlena=false;
									if(entrada!=null && entrada.getTextos()!=null && entrada.getTextos().length>0){
										int k=0;
										
										while(k<entrada.getTextos().length && !entradaLlena){
											LangStringVO[] entradaTexto = entrada.getTextos();
											String textoEntrada=entradaTexto[k].getTexto();
											if(textoEntrada!=null && !textoEntrada.equals("")){
												entradaLlena=true;
											}
											k++;
										}
									}else{
										entradaLlena=true;
									}
									if(!entradaLlena && identVacio){
										error = datosResources.getString("CAV.9.2.2.9")+" "+(indexClasificacion+1);
										if(!mensajes.contains(error))
											mensajes.add(error);
										errorDado=false;
									}
								}else{
									error = datosResources.getString("CAV.9.2.2.9")+" "+(indexClasificacion+1);
									if(!mensajes.contains(error))
										mensajes.add(error);
									errorDado=false;
								}
							}
						}else{
							error = datosResources.getString("CAV.9.2.2.9")+" "+(indexClasificacion+1);
							if(!mensajes.contains(error))
								mensajes.add(error);
							errorDado=false;
						}
					}
				}
				else{
					error = datosResources.getString("CAV.9.2.2.9")+" "+(indexClasificacion+1);
					if(!mensajes.contains(error))
						mensajes.add(error);
					errorDado=false;
				}
				//DESCRIPCION DE LA CLASIFICACION
//				if(cl != null && cl.getDescripcion() != null){
//					if(cl.getDescripcion().getTextos() != null && cl.getDescripcion().getTextos().length > 0){
//						for(int i = 0; i < cl.getDescripcion().getTextos().length; i++){
//							String texto = cl.getDescripcion().getTextos()[i].getTexto();
//							String idiom = cl.getDescripcion().getTextos()[i].getIdioma();
//							if(texto != null && !texto.equals("") && (idiom == null || idiom.equals(""))){
//								error = datosResources.getString("CAV.9.3.1") + " " + (indexClasificacion + 1);
//								if(!mensajes.contains(error))
//									mensajes.add(error); //si descripción está relleno, el idioma también debe estarlo
//							}
//							if((texto == null || texto.equals("")) && idiom != null && !idiom.equals("")){
//								error = datosResources.getString("CAV.9.3.2") + " " + (indexClasificacion + 1);
//								if(!mensajes.contains(error))
//									mensajes.add(error); //si idioma está relleno, descripción también debe estarlo
//							}
//						}
//					}
//				}
				
				//PALABRA CLAVE
//				if(cl != null){
//					if(cl.getPalabrasClave() != null && cl.getPalabrasClave().length > 0){
//						for(int i = 0; i < cl.getPalabrasClave().length; i++){
//							if(cl.getPalabrasClave()[i].getTextos() != null && cl.getPalabrasClave()[i].getTextos().length > 0){
//								for(int j = 0; j < cl.getPalabrasClave()[i].getTextos().length; j++){
//									String texto = cl.getPalabrasClave()[i].getTextos()[j].getTexto();
//									String idiom = cl.getPalabrasClave()[i].getTextos()[j].getIdioma();
//									if(texto != null && !texto.equals("") && (idiom == null || idiom.equals(""))){
//										error = datosResources.getString("CAV.9.4.1") + " " + (indexClasificacion + 1);
//										if(!mensajes.contains(error))
//											mensajes.add(error); //si descripción está relleno, el idioma también debe estarlo
//									}
//									if((texto == null || texto.equals("")) && idiom != null && !idiom.equals("")){
//										error = datosResources.getString("CAV.9.4.2") + " " + (indexClasificacion + 1);
//										if(!mensajes.contains(error))
//											mensajes.add(error); //si idioma está relleno, descripción también debe estarlo
//									}
//								}
//							}
//						}
//					}
//				}
			}
		}
	
		////////////////////////////////////////////////////////////////////////////////////////////////////
		//Comprobamos que las licencias de los recursos son aplicables a TODOS los recursos.
		//Para ello reusaremos el metodo filtrarDerechos del fichero DerechosControllerImpl.
		//Este metodo devuelve las licencias aplicables a un conjunto de usos educativos.
		//La idea es la siguiente; como al usar este metodo obtenemos el conjunto de licencias que podemos 
		//asignar, comprobaremos que la licencia aplicada al recurso en cuestion, esta dentro de 
		//este conjunto. Si es asi no hay problemas de licencias, eoc informaremos de error de validacion
		//en las licencias.
		///////////////////////////////////////////////////////////////////////////////////////////////////
		InputStream is = null;
    	Properties prop = new Properties();
    	CatalogadorAvSession catalogadorAvSession=this.getCatalogadorAvSession(request);
    	is = this.getClass().getResourceAsStream("/catalogadorAvanzado.properties");
		prop.load(is);
		
		String[] l_id = {prop.getProperty("copyrightAndOtherRestrictions")};
		VocabularioVO[] vocabulario = null;
		try {
			vocabulario = this.getSrvVocabulariosControladosService().obtenerCombos(l_id,"en");
		} catch (Exception e) {
			logger.debug("Problema al obtener vocabularios", e);
		}
		
		//Los vocabularios pueden ser null si el usuario no ajusto derechos
		if(vocabulario==null || vocabulario.length==0){
			if(mensajes.size()>0) { 
				if (form!=null) form.setMensajesError(mensajes);
				return false;
			}
			return true;  
		}
		
		//vamos a ordenar vocabulario
		UtilidadesOrdenarCombos vocabulariosOrdDest1 = new UtilidadesOrdenarCombos();
		VocabularioVO[] vocabularioOrdenado = vocabulariosOrdDest1.ordenarVocabulariosVO(vocabulario);

		//obtenemos todos derechos de autor que luego filtraremos segun los tipos de recursos
		Collection<TerminoVO> tCopyright = Arrays.asList(vocabularioOrdenado[0].getTerminos());
		
		//obtenemos los usos educativos
		//El usos educativo pueden ser null si el usuario no los ajusto		
		AvEducationalVO[] usosEducativos = catalogadorAvSession.getMDSesion().getEducational();
		if(usosEducativos==null || usosEducativos.length==0) {
			if(mensajes.size()>0) { 
				if (form!=null) form.setMensajesError(mensajes);
				return false;
			}
			return true;  
		}

		tCopyright = this.filtrarDerechos(tCopyright,usosEducativos,prop.getProperty("tipoRecursoAprendizaje"));
		
		//Como ultimo paso vemos si los derechos que estan asignados a los recursos aparecen en el conjunto 
		//que hemos obtenido previamente
		if (catalogadorAvSession.getMDSesion()==null ||
				catalogadorAvSession.getMDSesion().getDerechos()==null ||
				catalogadorAvSession.getMDSesion().getDerechos().getDerechosDeAutor()==null ||
				catalogadorAvSession.getMDSesion().getDerechos().getDerechosDeAutor().getValor()==null) {
			if(mensajes.size()>0) { 
				if (form!=null) form.setMensajesError(mensajes);
				return false;
			}
			return true;  
		}
		String licenciaAsignada = catalogadorAvSession.getMDSesion().getDerechos().getDerechosDeAutor().getValor();
		Iterator<TerminoVO> itLicenciasPermitidas = tCopyright.iterator();
		boolean esLicPermitida = false;	

		while (itLicenciasPermitidas.hasNext()) {
			String nombreLicPermitida=itLicenciasPermitidas.next().getNomTermino();
			if (nombreLicPermitida!=null) {
				if (licenciaAsignada.equals(nombreLicPermitida)) {
					esLicPermitida = true;
					break;
				}
			} 
		}
		if (esLicPermitida==false) {
			logger.debug("La licencia "+licenciaAsignada+" no es asignable a los usos educativos");
			error = datosResources.getString("CAV.11.1");
			mensajes.add(error);
		}
		////////////////////////////////////////////////////////////////////////////////////////////////////
		//Fin comprobacion licencias
		///////////////////////////////////////////////////////////////////////////////////////////////////

		if(mensajes.size()>0) { 
			if (form!=null) form.setMensajesError(mensajes);
			return false;
		}
		return true;  
	}


	/**
	 * 
	 * @param fichManifest: Objeto Manifest serializado.
	 * @param usuario: usuario.
	 * @return String: el identificador.
	 */
	private Collection<TerminoVO> filtrarDerechos(Collection<TerminoVO> copyRights, AvEducationalVO[] usosEducativos,String vocabulario) throws Exception{
		Collection<TerminoVO> derechos = new ArrayList<TerminoVO>();//Guarda los derechos de autor que cargaremos en el combo (resultado del filtro)
		
		boolean esMedia = false;
		boolean esSistemaDeRepresentacion = false;
		boolean esAplicacionInformatica = false;
		boolean esContenidoDidactico = false;
		boolean esServicio = false;
		TiposDeRecursos tiposRecursos = new TiposDeRecursos();
		DerechosDeAutor derechosAutor = new DerechosDeAutor();
		
		//obtenemos todos los tipos de recursos que tenemos en los usos educativos
		ArrayList<TerminoYPadreVO> terminos = new ArrayList<TerminoYPadreVO>(); 
		for (int i=0; i<usosEducativos.length;i++){
			SourceValueVO[] tipoDeRecurso= usosEducativos[i].getTiposrecursoedu();
			if(tipoDeRecurso !=null){
				for(int j = 0; j< tipoDeRecurso.length;j++){
					TerminoYPadreVO term = new TerminoYPadreVO();
					term.setNomTermino(tipoDeRecurso[j].getValor());	
					term.setIdVocabulario(vocabulario);
					term.setIdiomaTermino("en");
					term.setIdTermino("");
					terminos.add(term);
				}
			}
		}
		
		TerminoYPadreVO[] arrayTerminos=terminos.toArray(new TerminoYPadreVO[terminos.size()]);
		
		if(arrayTerminos!=null && arrayTerminos.length>0){
			
			//obtenemos los identificadores de los tipos de recurso
			TerminoYPadreVO[] terminosId = this.getSrvVocabulariosControladosService().obtenerIdTermino(arrayTerminos);
			
			//clasificamos los tipos de recursos que tenemos para ver que derechos tenemos que mostrar
			for (int i = 0; i<terminosId.length;i++){
				String idRecurso = terminosId[i].getIdTermino();
	
				if(tiposRecursos.esAplicacionInformatica(idRecurso)){
					esAplicacionInformatica = true;
				}else if(tiposRecursos.esServicio(idRecurso)){	
					esServicio = true;
				}else if(tiposRecursos.esMedia(idRecurso)){
					esMedia =true;
				}else if(tiposRecursos.esContenidoDidactico(idRecurso)){
					esContenidoDidactico =true;
				}else if(tiposRecursos.esSistemaDeRepresentacion(idRecurso)){
					esSistemaDeRepresentacion =true;
				}	
			}
			
			if (esServicio){//Si hay algun tipo de recurso de este grupo añadimos los derechos que le corresponden
				String[]derechosServicio = derechosAutor.dameDerechosServicio();
				for(int i=0; i<derechosServicio.length;i++){
					String id = derechosServicio[i];
					boolean enc = false;
					Iterator<TerminoVO> iteCopyRights = copyRights.iterator();
					while (iteCopyRights.hasNext() && !enc){
						TerminoVO term = iteCopyRights.next();
						if(term.getIdTermino().equals(id) && !derechos.contains(term)){
							derechos.add(term);
							enc = true;
						}
					}
				}
			}
	
			if(esAplicacionInformatica){//Si hay algun tipo de recurso de este grupo añadimos los derechos que le corresponden
				String[]derechosAplicaciones = derechosAutor.dameDerechosAplicacionInformatica();
				for(int i=0; i<derechosAplicaciones.length;i++){
					String id = derechosAplicaciones[i];
					boolean enc = false;
					Iterator<TerminoVO> iteCopyRights = copyRights.iterator();
					while (iteCopyRights.hasNext() && !enc){
						TerminoVO term = iteCopyRights.next();
						if(term.getIdTermino().equals(id) && !derechos.contains(term)){
							derechos.add(term);
							enc=true;
						}
					}
				}
			}
			if(esSistemaDeRepresentacion || esContenidoDidactico || esMedia){//Si hay algun tipo de recurso de este grupo añadimos los derechos que le corresponden
				String[]derechosResto = derechosAutor.dameDerechosNoServicioNoAplicacionInformatica();
				for(int i=0; i<derechosResto.length;i++){
					String id = derechosResto[i];
					boolean enc = false;
					Iterator<TerminoVO> iteCopyRights = copyRights.iterator();
					while (iteCopyRights.hasNext() && !enc){
						TerminoVO term = iteCopyRights.next();
						if(term.getIdTermino().equals(id) && !derechos.contains(term)){
							derechos.add(term);
							enc = true;
						}
					}
				}
			}
		}
		return derechos;
	}



	public void borrarSesion(
			ActionMapping mapping, 
			BorrarSesionForm form, 
			HttpServletRequest request, 
			HttpServletResponse response) 
	throws Exception 
	{
		String returnUrl = this.getCatalogadorAvSession(request).getReturnURL();
//		Parametro de catalogador Directo desde el empaquetador si no vacio, vuelta diferente
		String catalogadorDirecto = this.getCatalogadorAvSession(request).getCatalogadorDirecto();
		//Parametro crear Metadato, desde gestor flujo directamente
    	String crearMetadato = this.getCatalogadorAvSession(request).getCrearMetadato();
		String identificador = this.getCatalogadorAvSession(request).getIdentificador();    
		
    	//	borramos datos de sesion
		request.getSession().removeAttribute(es.pode.catalogadorWeb.presentacion.CatalogadorAvSession.SESSION_KEY);
		//Por si no nos llaman con el parametro returnURL, ponemos esta comprobacion que
		//si hemos perdido la sesion vamos a la portada del visualizador de contenidos
		
		String url = HTTP + LdapUserDetailsUtils.getHost();
		if(returnUrl==null){
			returnUrl = BARRA + request.getSession().getServletContext().getInitParameter("url_portada");
		}
		
		if (returnUrl!=null && !returnUrl.startsWith(BARRA)){
			returnUrl = BARRA + returnUrl;
		}
		
		if(LdapUserDetailsUtils.getSubdominio()!=null && 
		   !LdapUserDetailsUtils.getSubdominio().equals("") && 
		   !returnUrl.startsWith(LdapUserDetailsUtils.getSubdominio()))
		{
			returnUrl = LdapUserDetailsUtils.getSubdominio() + returnUrl;
		}
		url= url + returnUrl;
		//si hemos trabajado con catalogador Directo, vamos a poner en la url el parámetro de "Volver Catalogador"
		if ((catalogadorDirecto!=null) && (!catalogadorDirecto.equals(""))){
			url= url +  "?volverDirecto=VD" + "&identificador=" + identificador; // y ya no llamamos con catalogadorDirecto=CD					
		} else if (crearMetadato!=null && !crearMetadato.equals("")){
			//si viene con el parametro de crear metadato, la url la dejams igual, pero
		    //borramos el elemento de la hash
			String [] lisIdentif= {identificador};
			this.getSrvCatalogacionAvanzadaService().eliminarObjLoms(lisIdentif);
		}
		logger.info("METODO BORRAR SESSION; TENEMOS LA URL DE VUELTA: " + url);
		response.sendRedirect(url);//Redirigimos a Empaquetador
	}

	public void consultarAsistente(ActionMapping mapping, ConsultarAsistenteForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ResourceBundle datosResources = I18n.getInstance().getResource("application-resources", (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
		
//		List mensajes = new ArrayList();
		
		LomAvanzadoVO lomAv= this.getCatalogadorAvSession(request).getMDSesion();
		
		if (lomAv == null){
			lomAv= new LomAvanzadoVO();
		}
		logger.debug("Método consultarAsistente");
		
		String ayuda = "";
		// -- GENERAL --
		if((lomAv.getGeneral()==null)||(lomAv.getGeneral().getIdiomas()==null)||(lomAv.getGeneral().getIdiomas().length==0) ||
				(lomAv.getGeneral().getDescripciones()==null)|| (lomAv.getGeneral().getDescripciones().length==0) ||
				(lomAv.getGeneral().getNivelAgregacion()==null)){
			this.getCatalogadorAvSession(request).setMensajeAsistente(datosResources.getString("cat.asistente.general"));
			
		}
		else{
			if ((lomAv.getMetaMetadata()==null)||(lomAv.getMetaMetadata().getIdioma()==null)){
				this.getCatalogadorAvSession(request).setMensajeAsistente(datosResources.getString("cat.asistente.metametadatos"));
			}
			else{
				if ((lomAv.getEducational()==null)||(lomAv.getEducational().length==0)||(lomAv.getEducational()[0].getTiposrecursoedu()==null)||(lomAv.getEducational()[0].getTiposrecursoedu()[0].getValor()==null)){
					this.getCatalogadorAvSession(request).setMensajeAsistente(datosResources.getString("cat.asistente.usoEducativo"));
				}
				else{
					if ((lomAv.getDerechos()==null)||(lomAv.getDerechos().getDerechosDeAutor()==null)||(lomAv.getDerechos().getDerechosDeAutor().getValor()==null)){
						this.getCatalogadorAvSession(request).setMensajeAsistente(datosResources.getString("cat.asistente.derechos"));
					}
					else{
						
						boolean metadatosValidos= false;
						metadatosValidos= sonValidosMetadatos(mapping, null,  request, response);
						if (metadatosValidos== false){
							this.getCatalogadorAvSession(request).setMensajeAsistente(datosResources.getString("cat.asistente.noValido"));
						}
//						 comprobar si catalogación directa
						else{
							String catalogacion= this.getCatalogadorAvSession(request).getCatalogadorDirecto();
							if ((metadatosValidos== true) && ("CD".equals(catalogacion))){
								this.getCatalogadorAvSession(request).setMensajeAsistente(datosResources.getString("cat.asistente.catDirecta"));
							}
							else{
								String crearMetadato= this.getCatalogadorAvSession(request).getCrearMetadato();
								if ((metadatosValidos== true) && ("CM".equals(crearMetadato))){
									this.getCatalogadorAvSession(request).setMensajeAsistente(datosResources.getString("cat.asistente.catMetadato"));
								}
								else{
									this.getCatalogadorAvSession(request).setMensajeAsistente(datosResources.getString("cat.asistente.catNoDirecta"));
								}
								
							}
						}
						
					}
				}
			}
		}
		
	}

	public boolean comprobarIdioma(ActionMapping mapping, ComprobarIdiomaForm form, HttpServletRequest request, HttpServletResponse response)throws Exception {
		InputStream is =null;
        boolean errorValidacion=true;
    	
        try {
        	is = this.getClass().getResourceAsStream("/catalogadorAvanzado.properties");
        	Properties prop = new Properties();
        	prop.load(is);
        	//idioma de Catalogación!!!
        	String idiomaCat="";
        	CatalogadorAvSession catalogadoravSession = this.getCatalogadorAvSession(request);
	    	if (catalogadoravSession!=null){
	    		if (catalogadoravSession.getMDSesion()==null){
	    			catalogadoravSession.setMDSesion(new LomAvanzadoVO());
	    		}
	    		//cogemos el idioma de catalogacion; idioma de metametadata!!!

	    		if ((catalogadoravSession.getMDSesion().getMetaMetadata()!=null) && (catalogadoravSession.getMDSesion().getMetaMetadata().getIdioma()!=null) 
		    			&& (catalogadoravSession.getMDSesion().getMetaMetadata().getIdioma().getTexto()!=null)) {
		    			idiomaCat=catalogadoravSession.getMDSesion().getMetaMetadata().getIdioma().getTexto();
		    			
		    		}
	    	}
	    	
	    	//En caso de que idiomaCat sea vacio damos un error
	    	if (idiomaCat.equals("")){
	    		//String idiomaNav=((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
	    		ResourceBundle datosResources = I18n.getInstance().getResource("application-resources", (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
	    		form.setMensajeFin(datosResources.getString("error.traduccion.idiomaCat"));
	    		errorValidacion=false;
	    		form.setDatosIdiomasAsArray(new TerminoVO[0]);//inicializamos
	    	}
        	
        }catch (Exception e) {
			is.close();
			logger.error("Se ha producido un error al comprobar el idioma de catalogacion " + e);
		}  
        return errorValidacion;
	}


}