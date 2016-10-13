// license-header java merge-point
package es.pode.buscador.presentacion.avanzado.tesauros;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.pode.buscador.presentacion.BuscarSession;
import es.pode.contenidos.negocio.descargas.servicio.DescDescargaVO;
import es.pode.contenidos.negocio.descargas.servicio.DescargaVO;
import es.pode.contenidos.negocio.noticias.servicio.NoticiaTraducidaVO;
import es.pode.fuentestaxonomicas.negocio.servicio.EstructuraVdexVO;
import es.pode.fuentestaxonomicas.negocio.servicio.JerarquiaVO;
import es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.visualizador.presentacion.descargas.DescargaInfo;
import es.pode.visualizador.presentacion.noticia.NoticiaCodex;



/**
 * @see es.pode.buscador.presentacion.avanzado.tesauros.TesaurosController
 */
public class TesaurosControllerImpl extends TesaurosController
{

	private static Logger logger = Logger.getLogger(TesaurosControllerImpl.class);
	private static String VARIAS_RUTAS_DISPONIBLES = "0.0";
	private java.util.Properties pSpringProperties = null;
	
	private final static String TITULOTESAURO = "tesauro.NomTesauro";
	
	private static final String FICHERO = "application-resources";
	private static final String EUSKERA = "eu";
	private static final String INGLES = "en";
	private static final String SPACE = " ";

    /**
     * @see es.pode.buscador.presentacion.avanzado.tesauros.TesaurosController#cargarTesauros(org.apache.struts.action.ActionMapping, es.pode.buscador.presentacion.avanzado.tesauros.CargarTesaurosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void cargarTesauros(ActionMapping mapping, es.pode.buscador.presentacion.avanzado.tesauros.CargarTesaurosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try {
    		if(logger.isDebugEnabled())logger.debug("TesaurosControllerImpl - cargarTesauros.");
    		String idioma=((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();

			EstructuraVdexVO tesauroVigente = this.getSrvTesaurosServices().obtenerTesauroVigente();
			String vocabIdentifierTesauro = tesauroVigente.getVocabIdentifier();
			form.setTesauro(vocabIdentifierTesauro);
			String vocabNameTesauro = tesauroVigente.getVocabName();
			
			TaxonVO[] taxVO =this.getSrvTesaurosServices().obtenerPrimerNivelTesauro(vocabIdentifierTesauro, idioma);//Se carga el primer nivel del tesauro	
			form.setIdioma(idioma);
			form.setNomTesauros("");
			form.setTaxonesVOAsArray(taxVO);
			TaxonVO[] taxVORutaPadre = new TaxonVO[0];//En la cabecera sólo pondrá Inicio
			form.setRutaPadreVOAsArray(taxVORutaPadre);
			BuscarSession os = this.getBuscarSession(request);
			os.setJerarquias(null);
			
//			Se obtiene el nombre del tesauro en el idioma de navegacion
			Locale locale = null;
			try {
				locale = devuelveLocale(request);
			} catch (Exception e) {
				logger.error("Error recuperando el locale",e);
			}
			if(logger.isDebugEnabled())logger.debug("TesaurosControllerImpl - cargarTesauros:Se obtiene el nombre del tesauro en el idioma de navegacion ["+idioma+"]");
			String tituloTesauro = "";
//			String vocabNameTesauro = this.getSrvTesaurosServices().obtenerVocabName(nomTesauro, idioma);
//			Se forma el titulo del tesauro. El orden cambia dependiendo del idioma		 
			String literalTituloTesauro = this.getResource(TITULOTESAURO, FICHERO, locale);	
			if (EUSKERA.equals(idioma) || INGLES.equals(idioma))tituloTesauro = vocabNameTesauro + SPACE + literalTituloTesauro;
			else tituloTesauro = literalTituloTesauro + SPACE + vocabNameTesauro;
			form.setTituloTesauro(tituloTesauro);
		} catch (Exception e) {
			logger.error("TesaurosControllerImpl cargarTesauros: Error en CargarTesauro", e);
		}
    	
    	//Obtenemos la lista de noticias
    			try{		
    				NoticiaTraducidaVO[]	arrayNoticias = this.getSrvNoticiasService().listarNoticiasActivasPorIdioma(((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage());
    				
    				if (arrayNoticias!=null && arrayNoticias.length>0) {
    					
    					int numNoticiasMostradas = Integer.parseInt(this.getSrvPropiedadService().getValorPropiedad(AgregaProperties.NUM_NOTICIAS_MOSTRADAS_EN_RESUMEN));
    					NoticiaTraducidaVO[] array_noticias = new NoticiaTraducidaVO[numNoticiasMostradas];
    					
    					for(int h=0; h<numNoticiasMostradas && h < arrayNoticias.length; h++)
    					{
    						//sustituimos los retorno de carro por <br> en el resumen
    						arrayNoticias[h].setResumen(arrayNoticias[h].getResumen().replaceAll("\n\r", "<br/>"));
    						arrayNoticias[h].setResumen(arrayNoticias[h].getResumen().replaceAll("\r\n", "<br/>"));
    						arrayNoticias[h].setResumen(arrayNoticias[h].getResumen().replaceAll("\n", "<br/>"));
    						arrayNoticias[h].setResumen(arrayNoticias[h].getResumen().replaceAll("\r", "<br/>"));
    						
    						//sustituimos @,",:,%,+,-,' por su codigo correspondiente en ASCII
    						arrayNoticias[h].setTitulo(arrayNoticias[h].getTitulo().replaceAll("@","&#64"));
    						arrayNoticias[h].setTitulo(arrayNoticias[h].getTitulo().replaceAll("\\\"","&#34"));
    						arrayNoticias[h].setTitulo(arrayNoticias[h].getTitulo().replaceAll(":","&#58"));
    						arrayNoticias[h].setTitulo(arrayNoticias[h].getTitulo().replaceAll("%","&#37"));
    						arrayNoticias[h].setTitulo(arrayNoticias[h].getTitulo().replaceAll("\\+","&#43"));
    						arrayNoticias[h].setTitulo(arrayNoticias[h].getTitulo().replaceAll("-","&#45"));
    						arrayNoticias[h].setTitulo(arrayNoticias[h].getTitulo().replaceAll("'","&#39"));
    						
    						array_noticias[h]=arrayNoticias[h];
    					}
    					//Cojo solo las que debo
    					//int iTotal = Integer.parseInt(this.getPropertyValue("portada.noticias.total"));
    					//form.setNumNoticias(iTotal);
    					
    					NoticiaCodex[] noticiaCodex=NoticiaCodex.mapToCodexArray(array_noticias);
    					form.setNoticiasAsArray(noticiaCodex);
    					
    					//logger.debug("Noticias de portada obtenidas correctamente("+iTotal+").");
    					logger.debug("Noticias de portada obtenidas correctamente. Se mostraran "+numNoticiasMostradas+" noticias.");
    				} else {	
    					NoticiaCodex[] noticiaCodex=NoticiaCodex.mapToCodexArray(arrayNoticias);
    					form.setNoticiasAsArray(noticiaCodex);
    					logger.debug("Noticias de portada obtenidas correctamente. Se mostraran 0 noticias.");
    				}
    				// Ficheros OPML
//    		        String idioma = ((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
//    				form.setIdiomaNavegacion(idioma);
    				
    			} catch (Exception e) {
    				logger.error("Excepcion inesperada obteniendo noticias: "+ e);
    				throw e;
    			}
    			
    			
    			
    			//Obtenemos la lista de descargas
    			try {
    	    		Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
    				String idioma = locale.getLanguage();
    				logger.debug("Recuperado idioma :"+idioma);
    				DescargaVO descargas[] = getSrvDescargas().obtenerDescargasActivas();
    				logger.debug("Recuperadas "+descargas.length!=null?descargas.length:0+" descargas");
    				ArrayList<DescargaInfo> listaDescargas = new ArrayList<DescargaInfo>();
    				
    				if (descargas!=null && descargas.length>0) {	
    					DescDescargaVO[] descs = getSrvDescargas().obtenerDescDescargasIdioma(descargas, idioma);
    					logger.debug("Recuperadas " + descs.length + " descripciones de descargas");
    					int numDescargasMostradas = Integer.parseInt(this.getSrvPropiedadService().getValorPropiedad(AgregaProperties.NUM_DESCARGAS_MOSTRADAS_EN_RESUMEN));
    					
    					for (int i = 0; i<numDescargasMostradas && i<descargas.length && i<descs.length; i++) {    					
    						DescargaInfo info = new DescargaInfo();
    						info.setTitulo(descs[i]!=null&&descs[i].getTitulo()!=null?descs[i].getTitulo():"");
    						info.setDescripcion(descs[i]!=null&&descs[i].getDescripcion()!=null?descs[i].getDescripcion():"");
    						info.setIdentificador(descargas[i]!=null&&descargas[i].getIdentificador()!=null?descargas[i].getIdentificador().toString():"");
    						info.setPeso(descargas[i]!=null&&descargas[i].getPeso()!=null?descargas[i].getPeso():0L,locale);
    						info.setRuta(descargas[i]!=null&&descargas[i].getPath()!=null?request.getServerName()+"/"+descargas[i].getPath():"");
    						listaDescargas.add(info);
    					}
    					form.setDescargas(listaDescargas);
    					logger.debug("Lista de descargas de portada obtenidas correctamente. Se mostraran "+numDescargasMostradas+" descargas.");
    				}
    			} catch (Exception e) {
    				logger.debug("Error al recuperar descargas.",e);
    				throw new ValidatorException("{listaDescargas.error}");
    			}
    			
     }

    /**
     * @see es.pode.buscador.presentacion.avanzado.tesauros.TesaurosController#consultarId(org.apache.struts.action.ActionMapping, es.pode.buscador.presentacion.avanzado.tesauros.ConsultarIdForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void consultarId(ActionMapping mapping, es.pode.buscador.presentacion.avanzado.tesauros.ConsultarIdForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	if(logger.isDebugEnabled())logger.debug("TesaurosControllerImpl - consultarId.");
    	String idioma = ((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
		form.setIdioma(idioma);
		
		String vocabIdentifierTesauro = form.getTesauro();
		
		try{
			BuscarSession os = this.getBuscarSession(request);
			if(os.getJerarquias()!=null && form.isEsRuta()){//Si en el objeto de sesion las rutas jerarquicas no son nulas
				if(logger.isDebugEnabled())logger.debug("TesaurosControllerImpl - consultarId:Si en el objeto de sesion las rutas jerarquicas no son nulas");
				TaxonVO[] taxones = ((JerarquiaVO)os.getJerarquias().toArray()[Integer.parseInt(form.getIdTesauro())]).getJerarquia();
				JerarquiaVO jerar=new JerarquiaVO();
				jerar.setJerarquia(taxones);
				//Los terminos relacionados del ultimo taxon del array
				TaxonVO ultimoTaxon=taxones[taxones.length-1];
				TaxonVO[] terminosRelacionados=this.getSrvTesaurosServices().obtenerTerminosRelacionadosPorId(ultimoTaxon.getId(), vocabIdentifierTesauro, idioma);
				if(terminosRelacionados!=null)for(int i = 0; i < terminosRelacionados.length;i++)terminosRelacionados[i].setValorTax("("+terminosRelacionados[i].getTipoRelacion()+") "+terminosRelacionados[i].getValorTax());
				//Insertamos en el formulario los taxones relacionados y la rutaPadre
				form.setTaxonesVO(Arrays.asList(terminosRelacionados));
				form.setRutaPadreVO(Arrays.asList(((JerarquiaVO)os.getJerarquias().toArray()[Integer.parseInt(form.getIdTesauro())]).getJerarquia()));
				form.setNomTesauros(ultimoTaxon.getValorTax());
				form.setIdTesauro(ultimoTaxon.getId());//Cambiamos el identificador de la posicion por la del ultimo taxon
				//os.setJerarquias(null);
				form.setEsRuta(false);
			}else{//Si en el objeto de sesion las rutas jerarquicas son nulas
				//Accedemos y recogemos el array de taxones de la posicion id; que sera la rutaPadre
				if(logger.isDebugEnabled())logger.debug("TesaurosControllerImpl - consultarId:Si en el objeto de sesion las rutas jerarquicas son nulas, accedemos y recogemos el array de taxones de la posicion id; que sera la rutaPadre");
				TaxonVO[] taxonesRelacionados=this.getSrvTesaurosServices().obtenerTerminosRelacionadosPorId(form.getIdTesauro(), vocabIdentifierTesauro, idioma);
				if(taxonesRelacionados!=null)for(int i = 0; i < taxonesRelacionados.length;i++)taxonesRelacionados[i].setValorTax("("+taxonesRelacionados[i].getTipoRelacion()+") "+taxonesRelacionados[i].getValorTax());
				form.setTaxonesVO(Arrays.asList(taxonesRelacionados));//insertamos los taxones en el formulario
				JerarquiaVO[] jerarquia=this.getSrvTesaurosServices().obtenerJerarquia(form.getIdTesauro(), vocabIdentifierTesauro, idioma);//Obtenemos las jerarquias
				form.setRutaPadreVO(obtenerJerarquias(jerarquia, os , idioma));
			}
		}catch (Exception e) {
			logger.error("TesaurosControllerImpl - consultarId: Error en consulta Id", e);
		}
     }

     private List obtenerJerarquias(JerarquiaVO[] jerarquia, BuscarSession os, String idioma){
    	TaxonVO[] lTaxones = null;
	    if(jerarquia.length>1){//Si hay más de una jerarquia
			
			os.setJerarquias(Arrays.asList(jerarquia));
			TaxonVO taxonVariosDisponibles=new TaxonVO();				
			taxonVariosDisponibles.setEsHoja(new Boolean("false"));
			taxonVariosDisponibles.setId(VARIAS_RUTAS_DISPONIBLES);
			ResourceBundle datosResources = ResourceBundle.getBundle("application-resources", new Locale(idioma));
			taxonVariosDisponibles.setValorTax(datosResources.getString("tesauro.variasRutasDisponibles"));
			lTaxones = new TaxonVO[2];
			lTaxones[0]=taxonVariosDisponibles;
			lTaxones[1]=jerarquia[0].getJerarquia()[jerarquia[0].getJerarquia().length-1];	
		}else{
			lTaxones = jerarquia[0].getJerarquia();//a la colecion rutaPadre le insertamos la única jerarquia que tenemos y lo metemos en el formulario
		}
	    return Arrays.asList(lTaxones);
	}

    /**
     * @see es.pode.buscador.presentacion.avanzado.tesauros.TesaurosController#consultarPadreTaxon(org.apache.struts.action.ActionMapping, es.pode.buscador.presentacion.avanzado.tesauros.ConsultarPadreTaxonForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void consultarPadreTaxon(ActionMapping mapping, es.pode.buscador.presentacion.avanzado.tesauros.ConsultarPadreTaxonForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	if(logger.isDebugEnabled())logger.debug("TesaurosControllerImpl - consultarPadreTaxon");
    	String idioma = ((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
		BuscarSession os = this.getBuscarSession(request);
		try{
			
			
			String vocabIdentifierTesauro = form.getTesauro();
			
			
			if(form.getIdTesauro()!=null && !form.getIdTesauro().equals(VARIAS_RUTAS_DISPONIBLES)){//Cuando viene con un identificador que no le hemos insertado nosotros
				if(logger.isDebugEnabled())logger.debug("TesaurosControllerImpl - consultarPadreTaxon:Cuando viene con un identificador que no le hemos insertado nosotros");
				TaxonVO[] taxonesRelacionados=this.getSrvTesaurosServices().obtenerTerminosRelacionadosPorId(form.getIdTesauro(), vocabIdentifierTesauro, idioma);
				if(taxonesRelacionados!=null)for(int i = 0; i < taxonesRelacionados.length;i++)taxonesRelacionados[i].setValorTax("("+taxonesRelacionados[i].getTipoRelacion()+") "+taxonesRelacionados[i].getValorTax());
				form.setTaxonesVO(Arrays.asList(taxonesRelacionados));//insertamos los taxones en el formulario
				JerarquiaVO[] jerarquia=this.getSrvTesaurosServices().obtenerJerarquia(form.getIdTesauro(), vocabIdentifierTesauro, idioma);//Obtenemos las jerarquias
				form.setRutaPadreVO(obtenerJerarquias(jerarquia, os , idioma));
			}else{//Si viene con varias rutas disponibles, por eso lleva el identificador 0.0
				if(logger.isDebugEnabled())logger.debug("TesaurosControllerImpl - consultarPadreTaxon:Si viene con varias rutas disponibles, por eso lleva el identificador 0.0");
				Collection coleccionRutas=os.getJerarquias();
			    Collection taxones=new ArrayList();
				for(int i=0;i<coleccionRutas.size();i++){//Recorremos la coleccion de jerarquias en sesion
					TaxonVO nuevoTaxon=new TaxonVO();//Generamos un nuevo taxon
					nuevoTaxon.setEsHoja(Boolean.FALSE);	
					form.setEsRuta(true);
					nuevoTaxon.setId(new Integer(i).toString());//Llevara por identificador la i(de la posicion en las rutas)					
					JerarquiaVO jerarquias=(JerarquiaVO)coleccionRutas.toArray()[i];
					TaxonVO[] jerarTax=jerarquias.getJerarquia();			
					StringBuilder texto = taxonVOtoString(jerarTax);
					nuevoTaxon.setValorTax(texto.toString());
					taxones.add(nuevoTaxon);//Array de taxones			
				}
				TaxonVO[] taxVORutaPadre = new TaxonVO[0];
				JerarquiaVO jerarquia=new JerarquiaVO();
				jerarquia.setJerarquia(taxVORutaPadre);//En la cabecera sólo aparecera Inicio
				form.setRutaPadreVOAsArray(taxVORutaPadre);
				form.setTaxonesVO(taxones);//insertamos los taxones relacionados en el formulario
			}	
		}catch (Exception e) {
			logger.error("TesaurosControllerImpl - consultarPadreTaxon: Error en consultaPadreTaxon", e);
		}
     }

	private StringBuilder taxonVOtoString(TaxonVO[] jerarTax) {
		StringBuilder texto =new StringBuilder("");
		for(int j=0;j<jerarTax.length;j++){	
				texto.append(jerarTax[j].getValorTax());
				if(j<jerarTax.length-1)
					texto.append(" -> ");
		}//El texto sera la concatenacion de todos los textos del array de taxones
		return texto;
	}
    

    private String getPropertyValue(String sKey) throws IOException {
		InputStream fIsSpringProperties = this.getClass().getResourceAsStream("/spring_buscador2.properties");
		if (this.pSpringProperties == null) {
			pSpringProperties = new java.util.Properties();
			pSpringProperties.load(fIsSpringProperties);
		}
		fIsSpringProperties.close();
		logger.debug("TesaurosControllerImpl - getPropertyValue: Propiedad recuperada: " + sKey + " : "+ pSpringProperties.getProperty(sKey));
//		 devolvemos la propiedad
		return pSpringProperties.getProperty(sKey);
	}

	

	public void buscarTerminos(ActionMapping mapping, es.pode.buscador.presentacion.avanzado.tesauros.BuscarTerminosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			if(logger.isDebugEnabled())logger.debug("TesaurosControllerImpl - buscarTerminos");
			String idioma=((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
//			String nomTesauro = this.getPropertyValue("nombreFichTesauros");
			String vocabIdentifierTesauro = form.getTesauro();
			form.setIdioma(idioma);
			String textoBusqueda = form.getTextoBusqueda();
			if(textoBusqueda==null)textoBusqueda=""; 
			JerarquiaVO[] jerarquias=this.getSrvTesaurosServices().obtenerTerminos(textoBusqueda,vocabIdentifierTesauro, idioma);
			BuscarSession os = this.getBuscarSession(request);
	    	Collection rutas=new ArrayList();
	    	if (jerarquias.length==1){
	    		String id = jerarquias[0].getJerarquia()[jerarquias[0].getJerarquia().length-1].getId();
				TaxonVO[] taxonesRelacionados=this.getSrvTesaurosServices().obtenerTerminosRelacionadosPorId(id, vocabIdentifierTesauro, idioma);
				if(taxonesRelacionados!=null)for(int i = 0; i < taxonesRelacionados.length;i++)taxonesRelacionados[i].setValorTax("("+taxonesRelacionados[i].getTipoRelacion()+") "+taxonesRelacionados[i].getValorTax());
				form.setTaxonesVOAsArray(taxonesRelacionados);
				form.setNomTesauros(jerarquias[0].getJerarquia()[jerarquias[0].getJerarquia().length-1].getValorTax());
				form.setIdTesauro(jerarquias[0].getJerarquia()[jerarquias[0].getJerarquia().length-1].getId());
				form.setRutaPadreVOAsArray(jerarquias[0].getJerarquia());
				
	    	}else{
	    		rutas=rutasBusqueda(jerarquias);
		    	form.setRutaPadreVO(null);
		    	form.setTaxonesVO(rutas);
		    	form.setEsRuta(true);
		    	Collection cJerarquias = new ArrayList();
		    	for(int i=0;i<jerarquias.length;i++){
		    		cJerarquias.add(jerarquias[i]);
		    	}
		    	os.setJerarquias(cJerarquias);
	    	}
		}catch (Exception e) {
			logger.error("TesaurosControllerImpl buscarTerminos: Error en consultaPadreTaxon", e);
		}
	}
	
	
	public Collection rutasBusqueda(JerarquiaVO[] jerarquias){
    	Collection aux=new ArrayList();
    	for(int i=0;i<jerarquias.length;i++){
    		
			TaxonVO nuevoTaxon=new TaxonVO();//Generamos un nuevo taxon que tenga como identificador la i y como texto la concatenacion de los textos del array de taxones
			nuevoTaxon.setEsHoja(Boolean.FALSE);
			Integer entero=null;
			entero=new Integer(i);
			
			nuevoTaxon.setId(entero.toString());
			JerarquiaVO jerarquia=jerarquias[i];
			TaxonVO[] jerarTax=jerarquia.getJerarquia();
			StringBuilder valorTax = taxonVOtoString(jerarTax);
			nuevoTaxon.setValorTax(valorTax.toString());
			aux.add(nuevoTaxon);
		}
    	return aux;
	}
	
//	MÉTODOS PARA PINTAR LOS MENSAJES INTERNACIONALIZADOS
	public static String getResource(String key, String baseName, Locale locale)
	{
        String recurso = "";
        ResourceBundle theResourceBundle = getResource(baseName,locale);
        try{
        	if (theResourceBundle!=null){
               recurso = theResourceBundle.getString(key);
           }
        }catch (MissingResourceException mre){
        	recurso = key;
        }
        return recurso;
    }
	
	public static ResourceBundle getResource(String baseName, Locale locale)
	{
        try{
        	return ResourceBundle.getBundle(baseName,locale);
            
        }catch (MissingResourceException mre){
        		locale = new Locale("","");
        		return ResourceBundle.getBundle(baseName,locale);
             
        }
    }
	private Locale devuelveLocale(HttpServletRequest request) throws Exception
	{	
		
		Locale locale=null;
//		if (request instanceof HttpServletRequest) {
			locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
			
//		}
//		else{ 	    					
//			try {
//				locale=new Locale(I18n.getInstance().obtenerIdiomaDefectoPlataforma());
//			} catch (Exception e) {								
//				logger.error("DevuelveLocale-- Error recuperando el locale del request", e);
//			}
//		}
		return locale;
	}
	
}