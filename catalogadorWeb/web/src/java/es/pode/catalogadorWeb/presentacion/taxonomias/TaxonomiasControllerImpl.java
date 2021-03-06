// license-header java merge-point
package es.pode.catalogadorWeb.presentacion.taxonomias;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.catalogacion.negocio.servicios.ArbolCurricularVO;
import es.pode.catalogacion.negocio.servicios.AvClassificationVO;
import es.pode.catalogacion.negocio.servicios.CBTaxonVO;
import es.pode.catalogacion.negocio.servicios.EntryVO;
import es.pode.catalogacion.negocio.servicios.IdVO;
import es.pode.catalogacion.negocio.servicios.LangStringVO;
import es.pode.catalogacion.negocio.servicios.LomBasicoVO;
import es.pode.catalogacion.negocio.servicios.RutaTaxonomicaVO;
import es.pode.catalogadorWeb.presentacion.CatalogadorAvSession;
import es.pode.catalogadorWeb.presentacion.CatalogadorBSession;
import es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO;
import es.pode.soporte.constantes.ConstantesAgrega;



/**
 * @see es.pode.catalogadorWeb.presentacion.taxonomias.TaxonomiasController
 */
public class TaxonomiasControllerImpl extends TaxonomiasController
{
	// ArrayList rutaTotal = new ArrayList();

	protected static Logger logger = Logger
			.getLogger(TaxonomiasControllerImpl.class);

	/**
	 * @see es.pode.catalogadorBasico.presentacion.taxonomias.TaxonomiasController#cargaTaxonomias(org.apache.struts.action.ActionMapping,
	 *      es.pode.catalogadorBasico.presentacion.taxonomias.CargaTaxonomiasForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public final void cargaTaxonomias(
			ActionMapping mapping,
			es.pode.catalogadorWeb.presentacion.taxonomias.CargaTaxonomiasForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			
			boolean bandera=false;
			boolean bandera2=false;
			String idioma=((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
			
			//REcoger del combo!!!!!!!!!!!!!
			 
			String nomTaxonomia= null;
			String vocabName=null;
			CatalogadorBSession osb = this.getCatalogadorBSession(request);
			if ((osb!=null)&&(osb.getMBOSesion()!=null)){
				String ficheroProperties = "/catalogadorBasico.properties";
				InputStream iss=null;
				iss= this.getClass().getResourceAsStream(ficheroProperties);
				Properties fprop = new Properties();
				fprop.load(iss);
				iss.close();
				nomTaxonomia=fprop.getProperty("arbolCurricular").toString();
				vocabName=this.getSrvTaxonomiaService().obtenerVocabName(nomTaxonomia, idioma);
			}
			else{
				CatalogadorAvSession osa= this.getCatalogadorAvSession(request);
				if ((osa!=null)&&(osa.getMDSesion()!=null)){
					nomTaxonomia= request.getParameter("nomTaxonomia");
					idioma=request.getParameter("idioma");//valor que se ha recogido del combo del formulario
					vocabName=request.getParameter("vocabName");
				}
			}
			
			if (nomTaxonomia!=null){
				TaxonVO[] taxVO = this.getSrvTaxonomiaService().obtenerTaxonomia(
						nomTaxonomia, idioma);
			
				form.setIdioma(idioma);
				
				form.setNomTaxonomia(nomTaxonomia);
				form.setTaxonesvoAsArray(taxVO);
				form.setVocabName(vocabName);
				TaxonVO[] taxVORutaPadre = new TaxonVO[0];
				form.setRutaPadrevoAsArray(taxVORutaPadre);
			}

		}catch (Exception e){
			logger.error("Error en catalogadorWeb, Taxonomias, metodo cargarTaxonomias " + e);
			//throw new java.lang.Exception("taxonomias", e);
			saveErrorMessage(request, "Error TaxonomiasController met cargar");
		
		}
	}

	/**
	 * @see es.pode.catalogadorBasico.presentacion.taxonomias.TaxonomiasController#consultaTaxId(org.apache.struts.action.ActionMapping,
	 *      es.pode.catalogadorBasico.presentacion.taxonomias.ConsultaTaxIdForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public final void consultaTaxId(
			ActionMapping mapping,
			es.pode.catalogadorWeb.presentacion.taxonomias.ConsultaTaxIdForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			try{
				//Recogemos el idioma del navegador
				String idioma = ((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
				
				InputStream iss=null;
				boolean bandera=false;
				
				String ficheroProperties = "/catalogadorBasico.properties";
				
				iss= this.getClass().getResourceAsStream(ficheroProperties);
				Properties fprop = new Properties();
				fprop.load(iss);
				iss.close();
				String nomTaxonomia = request.getParameter("nomTaxonomia");
				String id = request.getParameter("id");
				String vocabName=request.getParameter("vocabName");
				if(form.getIdioma()!=null && !form.getIdioma().equals(""))
					idioma=form.getIdioma();
		
				form.setIdioma(idioma);
				form.setNomTaxonomia(nomTaxonomia);
				form.setId(id);
				form.setVocabName(vocabName);
				TaxonVO[] taxVO = this.getSrvTaxonomiaService().obtenerNodos(id,
						nomTaxonomia, idioma);
				form.setTaxonesvoAsArray(taxVO);
				List rutaPadrevo = Arrays.asList(this.getSrvTaxonomiaService()
						.obtenerTaxonPath(id, nomTaxonomia, idioma));
				Collections.reverse(rutaPadrevo);
				form.setRutaPadrevo(rutaPadrevo);
			
			}catch (Exception e){
				logger.error("Error en catalogadorWeb, Taxonomias, metodo consultaTaxId " + e);
				//throw new java.lang.Exception("taxonomias", e);
				saveErrorMessage(request, "Error en catalogadorWeb, TaxonomiasController, met consultaTaxId");
			
			}
		
	}

	/**
	 * @see es.pode.catalogadorBasico.presentacion.taxonomias.TaxonomiasController#consultaRuta(org.apache.struts.action.ActionMapping,
	 *      es.pode.catalogadorBasico.presentacion.taxonomias.ConsultaRutaForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public final void consultaRuta(
			ActionMapping mapping,
			es.pode.catalogadorWeb.presentacion.taxonomias.ConsultaRutaForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		try{
			String idioma = request.getParameter("idioma");
			if (idioma==null){
				idioma=((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
			}
			String nomTaxonomia = request.getParameter("nomTaxonomia");
			String id = request.getParameter("id");
			String vocabName=request.getParameter("vocabName");
			if(form.getIdioma()!=null && !form.getIdioma().equals(""))
				idioma=form.getIdioma();
	
			form.setId(id);
			form.setIdioma(idioma);
			form.setVocabName(vocabName);
			form.setNomTaxonomia(nomTaxonomia);
			List rutaPadrevo = Arrays.asList(this.getSrvTaxonomiaService()
					.obtenerTaxonPath(id, nomTaxonomia, idioma));
			Collections.reverse(rutaPadrevo);
			form.setRutaPadrevo(rutaPadrevo);
			TaxonVO[] taxVO = this.getSrvTaxonomiaService().obtenerNodos(id,
					nomTaxonomia, idioma);
			form.setTaxonesvoAsArray(taxVO);
		
		}catch (Exception e){
			logger.error("Error en catalogadorWeb, Taxonomias, metodo consultaRutas " + e);
			//throw new java.lang.Exception("taxonomias", e);
			saveErrorMessage(request, "Error en catalogadorWeb, TaxonomiasController metodo consultaRutas");
		
		}
	}

	/**
	 * @see es.pode.catalogadorBasico.presentacion.taxonomias.TaxonomiasController#AsociarArbol(org.apache.struts.action.ActionMapping,
	 *      es.pode.catalogadorBasico.presentacion.taxonomias.AsociarArbolForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	private CBTaxonVO[] transformarTaxonVO2CBTaxonVO(TaxonVO[] taxonList) {
		CBTaxonVO[] cbtaxonList = new CBTaxonVO[taxonList.length];
		for (int i = 0; i < taxonList.length; i++) {
			CBTaxonVO cbtaxonVO = new CBTaxonVO();
			cbtaxonVO.setId(taxonList[i].getId());
			cbtaxonVO.setValorTax(taxonList[i].getValorTax());
			cbtaxonVO.setEsHoja(taxonList[i].getEsHoja());
			cbtaxonList[i] = cbtaxonVO;
		}
		return cbtaxonList;
	}

	public final void AsociarArbol(
			ActionMapping mapping,
			es.pode.catalogadorWeb.presentacion.taxonomias.AsociarArbolForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String idioma = form.getIdioma();// this.getCatalogadorBSession(request).getIdioma();
		String nomTaxonomia = form.getNomTaxonomia();// request.getParameter("nomTaxonomia");
		String id = form.getId(); // request.getParameter("id");
		boolean activaValidator=false;
		CatalogadorBSession osb = this.getCatalogadorBSession(request);
		CBTaxonVO[] taxonList = null;
		TaxonVO[] relmismovo = null;
		
//		controlamos que el identificador a Asociar no este vacio ni sea null!!
		if ((id!=null) && (!id.equals(""))) {
			
			try {
				relmismovo = this.getSrvTaxonomiaService().obtenerTaxonPath(
						id, nomTaxonomia, idioma);
				taxonList = transformarTaxonVO2CBTaxonVO(relmismovo);
				
				
			
			}catch (Exception e){
				logger.error("Error en catalogadorWeb, Taxonomias, metodo consultaRutas " + e);
				//throw new java.lang.Exception("taxonomias", e);
				saveErrorMessage(request, "Error catalogadorWeb TaxonomiasController metodo AsociarArbol, obtenerTaxonPath");
			}
			
			if ((osb!=null)&&(osb.getMBOSesion()!=null)){
				try{
					LomBasicoVO lAux = new LomBasicoVO();
					String identificador;
					String returnURL;
					
					osb.getMBOSesion().getEducational().setContexto("");
					osb.getMBOSesion().getEducational().setEdad("");
					osb.getMBOSesion().getEducational().setProcesoCog("");
					
						if (this.getCatalogadorBSession(request) == null) {
							identificador = request.getParameter("identificador");
							returnURL = request.getParameter("returnURL");
						} else {
							identificador = this.getCatalogadorBSession(request)
									.getIdentificador();
							returnURL = this.getCatalogadorBSession(request)
									.getReturnURL();
						}
						
						if (osb.getMBOSesion().getClassification().getArbolesCurriculares() == null) {
							osb.getMBOSesion().getClassification().setArbolesCurriculares(
									new ArbolCurricularVO[] {});
						}
						ArbolCurricularVO[] rutas = osb.getMBOSesion().getClassification().getArbolesCurriculares();
						int re=0;
						boolean estaEnRuta= false;
						while ((re< rutas.length) && (!estaEnRuta)) {
							CBTaxonVO[] taxs = rutas[re].getTaxones(); //cogemos los taxones
							if ((taxs.length>0) && id.equals(taxs[0].getId())) { //comparamos con el primero
								estaEnRuta=true;
							}
							re++;
						}
						if (!estaEnRuta) {
							lAux = this.getSrvCatalogacionBasicaService()
										.asociarTaxonomiaEnLomBasico(identificador, osb.getMBOSesion(),
										taxonList);
						} else {
							lAux = osb.getMBOSesion();
							activaValidator=true;
							//throw new ValidatorException("{taxonomia.error.asociar}");
						}
						if (!activaValidator) {
							//Actualizamos la sesion y los arboles de sesion
							osb.setMBOSesion(lAux);
							List arboles =Arrays.asList(lAux.getClassification().getArbolesCurriculares());
							osb.setArbolesController(arboles);
							osb.setArbolesTrad(arboles);
							if( logger.isDebugEnabled() ){
								logger.debug("Actualizados la sesion y los arboles" );
							}
						}
					 }catch (Exception e){
						logger.error("Error en catalogadorWeb, Taxonomias, metodo consultaRutas " + e);
						//throw new java.lang.Exception("taxonomias", e);
						saveErrorMessage(request, "Error en catalogadorWeb TaxonomiasController obj sesion!= null");
					 }
					if (activaValidator==true) {
						//lanzamos el error de validacion
						throw new ValidatorException("{taxonomia.error.asociar}");
					}
						
				}//fin if
				else{
					try{
						 CatalogadorAvSession osa = this.getCatalogadorAvSession(request);
						 if ((osa!=null)&&(osa.getMDSesion()!=null)&&(osa.getClasificacionAux()!=null)){
							AvClassificationVO clasAux= osa.getClasificacionAux();
							
							RutaTaxonomicaVO rt= clasAux.getRutasTaxonomicas()[clasAux.getRutasTaxonomicas().length -1];
							if (rt!=null){
								ArrayList taxonesL= new ArrayList();
								for (int i= 0; i<relmismovo.length; i++){
									es.pode.catalogacion.negocio.servicios.TaxonVO t= new es.pode.catalogacion.negocio.servicios.TaxonVO();
									IdVO idvo= new IdVO(relmismovo[i].getId());
									idvo.setTexto(relmismovo[i].getId());
									LangStringVO[] ls= new LangStringVO[1];
									ls[0]=new LangStringVO();
									ls[0].setTexto(relmismovo[i].getValorTax());
									ls[0].setIdioma(idioma);
									EntryVO e= new EntryVO();
									e.setTextos(ls);
									t.setId(idvo);
									t.setEntry(e);
									
									taxonesL.add(t);
								}
								
								Collections.reverse(taxonesL);
								es.pode.catalogacion.negocio.servicios.TaxonVO[] taxonesA= (es.pode.catalogacion.negocio.servicios.TaxonVO[]) taxonesL.toArray(new es.pode.catalogacion.negocio.servicios.TaxonVO[taxonesL.size()]);
								rt.setTaxones(taxonesA); // Por referencia modificamos clasAux �?
							}
							
						}
					}catch (Exception e){
						logger.error("Error en catalogadorWeb, Taxonomias, metodo AsociarArbol " + e);
						//throw new java.lang.Exception("taxonomias", e);
						saveErrorMessage(request, "Error TaxonomiasController metodo AsociarArbol");
					}
				}
		}else{
			throw new ValidatorException("{taxonomia.error.asociarUnId}");
		}
		
		
		
		
	}

	/**
	 * This dummy variable is used to populate the "taxonesvo" table. You may
	 * delete it when you add you own code in this controller.
	 */
	private static final java.util.Collection taxonesvoDummyList = java.util.Arrays
			.asList(new Object[] { new TaxonesvoDummy("id-1", "valorTax-1"),
					new TaxonesvoDummy("id-2", "valorTax-2"),
					new TaxonesvoDummy("id-3", "valorTax-3"),
					new TaxonesvoDummy("id-4", "valorTax-4"),
					new TaxonesvoDummy("id-5", "valorTax-5") });

	/**
	 * This inner class is used in the dummy implementation in order to get the
	 * web application running without any manual programming. You may delete
	 * this class when you add you own code in this controller.
	 */
	public static final class TaxonesvoDummy implements java.io.Serializable {
		private String id = null;

		private String valorTax = null;

		public TaxonesvoDummy(String id, String valorTax) {
			this.id = id;
			this.valorTax = valorTax;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getId() {
			return this.id;
		}

		public void setValorTax(String valorTax) {
			this.valorTax = valorTax;
		}

		public String getValorTax() {
			return this.valorTax;
		}

	}

	/**
	 * This dummy variable is used to populate the "rutaPadrevo" table. You may
	 * delete it when you add you own code in this controller.
	 */
	private static final java.util.Collection rutaPadrevoDummyList = java.util.Arrays
			.asList(new Object[] { new RutaPadrevoDummy("id-1", "valorTax-1"),
					new RutaPadrevoDummy("id-2", "valorTax-2"),
					new RutaPadrevoDummy("id-3", "valorTax-3"),
					new RutaPadrevoDummy("id-4", "valorTax-4"),
					new RutaPadrevoDummy("id-5", "valorTax-5") });

	/**
	 * This inner class is used in the dummy implementation in order to get the
	 * web application running without any manual programming. You may delete
	 * this class when you add you own code in this controller.
	 */
	public static final class RutaPadrevoDummy implements java.io.Serializable {
		private String id = null;

		private String valorTax = null;

		public RutaPadrevoDummy(String id, String valorTax) {
			this.id = id;
			this.valorTax = valorTax;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getId() {
			return this.id;
		}

		public void setValorTax(String valorTax) {
			this.valorTax = valorTax;
		}

		public String getValorTax() {
			return this.valorTax;
		}

	}

	public String decidirCatalogador(ActionMapping mapping, DecidirCatalogadorForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String tipoC = "";
		try{
			CatalogadorBSession osb = this.getCatalogadorBSession(request);
			if ((osb!=null)&&(osb.getMBOSesion()!=null)){
					tipoC = "Basico";
			}
			else{
				CatalogadorAvSession osa= this.getCatalogadorAvSession(request);
				if ((osa!=null)&&(osa.getMDSesion()!=null)){
					tipoC = "Avanzado";
				}
			}
			
			return tipoC;
		
		}catch (Exception e){
			logger.error("Error en catalogadorWeb, Taxonomias, metodo decidirCatalogador " + e);
			//throw new java.lang.Exception("taxonomias", e);
			saveErrorMessage(request, "Error en catalogadorWeb, Taxonomias, metodo decidirCatalogador");
			return tipoC;
		}
		
	}

	
}