// license-header java merge-point
package es.pode.gestorFlujo.presentacion.objetosPersonales.importarURL;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.catalogacion.soporte.UtilidadesOrdenarCombos;
import es.pode.fuentestaxonomicas.negocio.servicio.TerminoVO;
import es.pode.fuentestaxonomicas.negocio.servicio.TerminoYPadreVO;
import es.pode.fuentestaxonomicas.negocio.servicio.VocabularioVO;
import es.pode.publicacion.negocio.servicios.ResultadoOperacionVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.gestorFlujo.presentacion.objetosPersonales.importarURL.ImportarURLController
 */
public class ImportarURLControllerImpl extends ImportarURLController
{
	private static Logger logger = Logger.getLogger(ImportarURLControllerImpl.class);
	
    /**
     * @see es.pode.gestorFlujo.presentacion.objetosPersonales.importarURL.ImportarURLController#crear(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.objetosPersonales.importarURL.CrearForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void crear(ActionMapping mapping, es.pode.gestorFlujo.presentacion.objetosPersonales.importarURL.CrearForm form, HttpServletRequest request, HttpServletResponse response) throws ValidatorException
    {
    	String VACIA="";
    	
		java.util.Locale locale = (java.util.Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
		String idioma = getIdioma(request);
		InputStream is = null;
    	String url=VACIA;
    	String comentarios=VACIA;
    	String idiomaPpal=VACIA;
    	String idiomaDestinatario=VACIA;
    	String titulo=VACIA;
    	String idUsuario = LdapUserDetailsUtils.getUsuario();;
    	String action=VACIA;
    	String tipo="5.2.23";
    	Properties prop = new Properties();
		if (form!=null) {
			logger.debug("Recuperamos campos del formulario");
			url = form.getUrl()!= null?form.getUrl():VACIA;
			comentarios = form.getComentarios()!=null?form.getComentarios():VACIA;
			idiomaPpal = form.getIdioma()!=null?form.getIdioma():VACIA;

			idiomaDestinatario = form.getIdiomaDest()!=null?form.getIdiomaDest():VACIA;

			try {
				titulo = form.getTitulo()!=null?form.getTitulo():VACIA;
				action=form.getAction()!=null?form.getAction():VACIA;
				tipo=form.getTipo()!=null?form.getTipo():tipo;
				
				
				 String[] idxatraduc = new String[3];
		            for ( int cont=0;cont<idxatraduc.length;cont++){
		                  switch (cont){
		                       case 0: idxatraduc[0]=idiomaPpal;
		                                   break;
		                       case 1: idxatraduc[1]=tipo;
		                                   break;
		                       case 2: idxatraduc[1]=idiomaDestinatario;
		                                   break;
		                  }
		
		            }
		            
		            
		            is= this.getClass().getResourceAsStream("/gestorFlujo.properties");
		           
					prop.load(is);
				
		            String[] l_id = { prop.getProperty("idioma"),
					prop.getProperty("tipoDeRecurso"),
					prop.getProperty("idiomaDestinatario")};
			
		            TerminoYPadreVO[] terminosTraduc;
					
						terminosTraduc = this.getSrvVocabulariosControladosService().crearTraduccionAIngles(idxatraduc);
					
		            for (int cont=0;cont<terminosTraduc.length;cont++){//Cambiado el identificador del termino al nombre del termino en ingles
		            	if (l_id[0].equals(terminosTraduc[cont].getIdVocabulario())){
		            		idioma=terminosTraduc[cont].getNomTermino();
		            	}
		            	else if (l_id[1].equals(terminosTraduc[cont].getIdVocabulario())){
		            		tipo=terminosTraduc[cont].getNomTermino();
		            	}
		            	else if (l_id[2].equals(terminosTraduc[cont].getIdVocabulario())){
		            		idiomaDestinatario=terminosTraduc[cont].getNomTermino();
		            	}
		
		            }
			
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				logger.error("No se ha encontrado el fichero de propiedades");
			}catch (Exception e1) {
				// TODO Auto-generated catch block
				logger.error("Error traduciendo los términos a inglés");
			}
		}
    	//Comprobamos entrada
		if(url.equals(VACIA)||comentarios.equals(VACIA)||idioma.equals(VACIA)||
				idiomaDestinatario.equals(VACIA)||titulo.equals(VACIA)) {
			logger.debug("No se introdujeron campos");
			throw new ValidatorException("{gestorFlujos.error.camposVacios}");
		}
		
    	if (action.equals(i18n.getString("gestorFlujo.importarURL.guardar"))) {
			try {
				logger.debug("Llamamos a crearDesdeURL con url="+url+", usuario="+
						idUsuario+", comentarios="+comentarios+", titulo="+
						titulo+", idioma="+idioma+", idiomaDestinatario="+idiomaDestinatario);
				ResultadoOperacionVO result = getSrvPublicacionService().crearDesdeURL(url, idUsuario,
						comentarios, titulo, idioma, idiomaDestinatario, tipo);
				logger.debug("Resultado de crearDesdeURL = "+result.getIdResultado()+", "+result.getDescripcion());
			} catch (Exception e) {
				logger.error("Excepción al importar url: "+url+" ,error: "+e);
				throw new ValidatorException("{gestorFlujo.error.inesperado}");
			}
		}
     }

	/**
	 * @param request
	 * @return
	 */
	private String getIdioma(HttpServletRequest request) {
		String idioma=((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
		try {
			if(!I18n.getInstance().idiomaSoportadoPorPlataforma(idioma)){
				idioma=I18n.getInstance().obtenerIdiomaDefectoPlataforma();
			}
		} catch (Exception e) {
			logger.debug("Error al comprobar idioma, "+e);
			idioma="en";
		}
		return idioma;
	}

    /**
     * @see es.pode.gestorFlujo.presentacion.objetosPersonales.importarURL.ImportarURLController#capturarURL(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.objetosPersonales.importarURL.CapturarURLForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void capturarURL(ActionMapping mapping, es.pode.gestorFlujo.presentacion.objetosPersonales.importarURL.CapturarURLForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        String url=null;
    	url=request.getParameter("url");
    	if(url==null) {
    		throw new ValidatorException("{gestorFlujos.error.url}");
    	} else {
    		logger.debug("Recibida URL:"+url);
    		form.setUrl(url);
    	}
    	
    	//Recuperamos valores para idioma, idiomaDestinatario y tipo de Recurso, 
    	//copiado de es.pode.catalogadorWeb.presentacion.catalogadorBasico.CatBasicoControllerImpl
		InputStream is = null;
		try {
///////////////////////////////////////////////////////////////////////////////////////////
			ResourceBundle datosResources = I18n.getInstance().getResource(
						"application-resources", 
						(Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
			String idioma = getIdioma(request);
			String ficheroProperties = "/gestorFlujo.properties";
			is= this.getClass().getResourceAsStream(ficheroProperties);
			Properties prop = new Properties();
			prop.load(is);
////////////////////////////////////////////////////////////////////////////////////////////
			
			int longVocabulario = 0;

			String[] l_id = { prop.getProperty("idioma"),
					prop.getProperty("tipoDeRecurso"),
					prop.getProperty("idiomaDestinatario")};
			VocabularioVO[] vocabulario = this
					.getSrvVocabulariosControladosService().obtenerCombos(l_id,
							idioma);
			//vamos a ordenar vocabulario
			UtilidadesOrdenarCombos vocabularioOrdenado1 = new UtilidadesOrdenarCombos();
			VocabularioVO[] vocabularioOrdenado = vocabularioOrdenado1.ordenarVocabulariosVO(vocabulario);				
			longVocabulario = vocabularioOrdenado.length;
			for (int x = 0; x < longVocabulario; x++) {
				TerminoVO terminoVO = new TerminoVO();
				terminoVO.setIdiomaTermino("");
				terminoVO.setIdTermino("");
				terminoVO.setNomTermino("");
				switch (x) {
				case 0:
					Collection<TerminoVO> id = new ArrayList<TerminoVO>();
					id.add(terminoVO);
//					modificamos las cadenas de idiomas
					TerminoVO[] terminos = vocabularioOrdenado[x].getTerminos();
					for (int li=0; li<terminos.length;li++){
						TerminoVO idAux = new TerminoVO();
						idAux=terminos[li];
						String textoIdioma= idAux.getNomTermino();
						String idiomasTra="";
						if(!textoIdioma.equals("x-none")){
							
							idiomasTra=I18n.getInstance().obtenerIdiomaTraducido(textoIdioma, idioma);
						}else{
							idiomasTra=datosResources.getString("x-none");
						}
						idAux.setNomTermino(idiomasTra);
					}
					UtilidadesOrdenarCombos terminosOrd2 = new UtilidadesOrdenarCombos();
					TerminoVO[] terminosOrd = terminosOrd2.ordenarTerminosVO(terminos, idioma);						
					Collection<TerminoVO> id2 = Arrays
							.asList(terminosOrd);
					
					Iterator<TerminoVO> z = id2.iterator();
					while (z.hasNext()) {
						id.add(z.next());
					}
					
					form.setIdiomaBackingList(id, "idTermino", "nomTermino");
					break;
				case 1:
					Collection<TerminoVO> tRecurso = new ArrayList<TerminoVO>();
					tRecurso.add(terminoVO);
					Collection<TerminoVO> tRecurso2 = Arrays.asList(vocabularioOrdenado[x]
							.getTerminos());
					Iterator<TerminoVO> cont = tRecurso2.iterator();
					while (cont.hasNext()) {
						TerminoVO termino = cont.next();
						tRecurso.add(termino);
					}
//					form.setTipoBackingList(tRecurso, "idTermino", "nomTermino");
//					form.setListaTipoAsArray((TerminoVO[]) tRecurso.toArray(new TerminoVO[tRecurso.size()]));
					break;
				case 2:
//					modificamos las cadenas de idiomas
					TerminoVO[] terminosIds = vocabularioOrdenado[x].getTerminos();
					for (int li=0; li<terminosIds.length;li++){
						TerminoVO idAux = new TerminoVO();
						idAux=terminosIds[li];
						String textoIdioma= idAux.getNomTermino();
						String idiomasTra=I18n.getInstance().obtenerIdiomaTraducido(textoIdioma, idioma);
						idAux.setNomTermino(idiomasTra);
					}
					UtilidadesOrdenarCombos terminosIdsOrd1 = new UtilidadesOrdenarCombos();
					TerminoVO[] terminosIdsOrd = terminosIdsOrd1.ordenarTerminosVO(terminosIds, idioma);					
					Collection<TerminoVO> idDest = new ArrayList<TerminoVO>();
					idDest.add(terminoVO);
					Collection<TerminoVO> idDest2 = Arrays.asList(terminosIdsOrd);
					Iterator<TerminoVO> cont2 = idDest2.iterator();
					while (cont2.hasNext()) {
						idDest.add(cont2.next());
					}
					form.setIdiomaDestBackingList(idDest, "idTermino", "nomTermino");
					break;
				}
				

			}
			if( logger.isDebugEnabled() ){

                logger.debug("Cargados los combos del formulario" );
			}
		} catch (Exception e) {
			logger.error("Error en importarURL, metodo capturarURL: "+e);
			throw e;
		}
     }









}