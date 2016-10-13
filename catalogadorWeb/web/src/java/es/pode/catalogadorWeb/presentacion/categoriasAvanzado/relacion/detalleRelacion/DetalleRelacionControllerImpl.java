// license-header java merge-point
package es.pode.catalogadorWeb.presentacion.categoriasAvanzado.relacion.detalleRelacion;

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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.catalogacion.negocio.servicios.AvRelationVO;
import es.pode.catalogacion.negocio.servicios.DescripcionVO;
import es.pode.catalogacion.negocio.servicios.IdentificadorVO;
import es.pode.catalogacion.negocio.servicios.LangStringVO;
import es.pode.catalogacion.negocio.servicios.LomAvanzadoVO;
import es.pode.catalogacion.negocio.servicios.RecursoVO;
import es.pode.catalogacion.negocio.servicios.SourceValueVO;
import es.pode.catalogacion.soporte.UtilidadesOrdenarCombos;
import es.pode.catalogadorWeb.presentacion.CatalogadorAvSession;
import es.pode.catalogadorWeb.presentacion.RelacionSession;
import es.pode.fuentestaxonomicas.negocio.servicio.TerminoVO;
import es.pode.fuentestaxonomicas.negocio.servicio.TerminoYPadreVO;
import es.pode.fuentestaxonomicas.negocio.servicio.VocabularioVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.catalogadorWeb.presentacion.categoriasAvanzado.relacion.detalleRelacion.DetalleRelacionController
 */

@SuppressWarnings("all")
public class DetalleRelacionControllerImpl extends DetalleRelacionController
{  
	protected static Logger logger = Logger.getLogger(DetalleRelacionControllerImpl.class); 

//	private DescripcionVO[] lDescripciones=null;
//	private SourceValueVO tipo;
//	String catalogo,entrada;
	private int iRel;


 
    public final void cargarDetalle(
    		ActionMapping mapping, 
    		CargarDetalleForm form,
    		HttpServletRequest request,
    		HttpServletResponse response) 
    throws Exception
    {
    	InputStream is = null;
    	Properties prop = new Properties();
    	String catalogo="";
    	String entrada="";
    	
    	
    	try {
    		
    		CatalogadorAvSession catalogadorAvSession=this.getCatalogadorAvSession(request);
			
//			comprobamos que el objeto de sesion no se ha null, si es null nos creamos uno	
			if (catalogadorAvSession.getMDSesion() == null){
				LomAvanzadoVO mdSession = new LomAvanzadoVO();
				catalogadorAvSession.setMDSesion(mdSession);
			} 
			
//			String botonPulsado = request.getParameter("botonPulsado");
//			form.setBotonPulsado(botonPulsado); //actualizamos si es modificar o crear
//			String titulo = request.getParameter("titulo");
//			form.setTituloRelacion(titulo); //el numero
			//Rellenamos combos
			is = this.getClass().getResourceAsStream("/catalogadorAvanzado.properties");
			prop.load(is);
			String idiomaLdap=((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();

			
			String[] l_id = {prop.getProperty("kind"), prop.getProperty("idiomaDestinatario")}; //es el idioma pero sin "ninguno"
			VocabularioVO[] vocaCombos = this.getSrvVocabulariosControladosService().obtenerCombos(l_id,idiomaLdap);
			UtilidadesOrdenarCombos vocabulariosOrdDest1 = new UtilidadesOrdenarCombos();
			VocabularioVO[] vocabularioOrdenado = vocabulariosOrdDest1.ordenarVocabulariosVO(vocaCombos);			

			int longVocabulario = vocabularioOrdenado.length;//Cargamos los combos de idioma, la estructura y el nivel de agregacion
			
			
			Collection idTipo = new ArrayList();
			Collection idDest=new ArrayList();
			
			for (int x = 0; x < longVocabulario; x++) {
				TerminoVO terminoVO = new TerminoVO();
				terminoVO.setIdiomaTermino("");
				terminoVO.setIdTermino("");
				terminoVO.setNomTermino("");

				switch (x) {

				case 0:
					
					idTipo.add(terminoVO);
//					modificamos las cadenas de idiomas
					TerminoVO[] terminosTipo = vocabularioOrdenado[x].getTerminos();
					for (int li=0; li<terminosTipo.length;li++){
						TerminoVO idAux = new TerminoVO();
						idAux=terminosTipo[li];
						idAux.setNomTermino(terminosTipo[li].getNomTermino());
					}
					UtilidadesOrdenarCombos terminosOrdDest1 = new UtilidadesOrdenarCombos();
					TerminoVO[] terminosOrdTipo = terminosOrdDest1.ordenarTerminosVO(terminosTipo, idiomaLdap);					
					Collection idTipo2 = Arrays.asList(terminosOrdTipo);
					
					Iterator zTipo = idTipo2.iterator();
					while (zTipo.hasNext()) {
						idTipo.add(zTipo.next());
					}
					form.setTipoBackingList(idTipo, "idTermino", "nomTermino");
					
					break;
				
				case 1:
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
					form.setComboIdiomaBackingList(idDest, "idTermino", "nomTermino");
					
					break;
				}
				
				
				if( logger.isDebugEnabled() ){

	                logger.debug("Cargados los combos del formulario" );
				}

			}
			logger.debug("Cargados los combos");
			
			String botonModificar=prop.getProperty("botonModificar")!=null?prop.getProperty("botonModificar"):"";
			//Caso Modificacion
			 AvRelationVO[] relaciones =catalogadorAvSession.getMDSesion().getRelaciones();
			 if (botonModificar.equals(form.getBotonPulsado())) { //Modificacion
				 TerminoYPadreVO terminoypadreVO = new TerminoYPadreVO();
				 ArrayList terminoypadrear=new ArrayList();
				 iRel = Integer.parseInt(form.getTituloRelacion()) - 1;
				 //comprobamos que relation no se ha null, si es null nos creamos uno	
				 boolean relacionEsNull = false;
					if (relaciones[iRel] == null){
						AvRelationVO relation = new AvRelationVO();
						relaciones[iRel] = relation;
						relacionEsNull=true;
					}
				 
				
					for (int x = 0; x < longVocabulario; x++) {
						TerminoVO terminoVO = new TerminoVO();
						terminoVO.setIdiomaTermino("");
						terminoVO.setIdTermino("");
						terminoVO.setNomTermino("");
					
						switch (x) {
							case 0://KIND
								terminoypadreVO = new TerminoYPadreVO();
								terminoypadreVO.setIdiomaTermino("en");
								terminoypadreVO.setIdVocabulario(l_id[x]);
								terminoypadreVO.setIdTermino("");
								if ((relaciones[iRel] != null)&&(relaciones[iRel].getTipo() !=null) && (relaciones[iRel].getTipo().getValor()!=null)) {
									terminoypadreVO.setNomTermino(relaciones[iRel].getTipo().getValor());
									terminoypadrear.add(terminoypadreVO);
								} else {
									terminoypadreVO.setNomTermino("");//terminoYPadre rellenado con estructura desde el formulario
									terminoypadrear.add(terminoypadreVO);
								}
								
								break;
						}
					}
					TerminoYPadreVO[] arrayTerminoYPadre=(TerminoYPadreVO[])terminoypadrear.toArray(new TerminoYPadreVO[terminoypadrear.size()]);
					TerminoYPadreVO[] terminoYPadreVuelta = this.getSrvVocabulariosControladosService().obtenerIdTermino(arrayTerminoYPadre);
//					TIPO 
					String idTI = terminoYPadreVuelta[0].getIdTermino();	
					if (idTI == null) {
						idTI = "";//No hay ningún tipo se recurso seleccionado por defecto
					}
					form.setTipo(idTI);
					
//					RECURSO
		            ArrayList lDescripciones = new ArrayList();
					DescripcionVO[] descripcionVO =null;
					DescripcionVO[] nuevoDescripcionVO =null;
					if (relaciones.length>0 && (relaciones[iRel] != null)&&(relaciones[iRel].getRecurso() !=null) ) {
						IdentificadorVO identificador=relaciones[iRel].getRecurso().getIdentificador();
						if(identificador==null)
						{
							catalogo="";
							entrada="";
						}else
						{
							catalogo=identificador.getCatalogo();
							entrada=identificador.getEntrada();
						}
						form.setCatalogo(catalogo);
						form.setEntrada(entrada);
						if(relaciones[iRel].getRecurso().getDescripciones()!=null &&relaciones[iRel].getRecurso().getDescripciones().length>0){
							descripcionVO = relaciones[iRel].getRecurso().getDescripciones();
							nuevoDescripcionVO = new DescripcionVO[descripcionVO.length];
							if(descripcionVO.length == 1 && descripcionVO[0].getTextos().length== 0){
								LangStringVO[] aLangString =new LangStringVO[1];
								LangStringVO lang = new LangStringVO();
								lang.setIdioma("");
								lang.setTexto("");
								aLangString[0]=lang;
								nuevoDescripcionVO = new DescripcionVO[1];
								DescripcionVO nuevo=new DescripcionVO();
								nuevo.setTextos(aLangString);
								nuevoDescripcionVO[0]=nuevo;
							}
							else{
								for (int i = 0; i<descripcionVO.length; i++){
									LangStringVO[] langTextosDesc = descripcionVO[i].getTextos();
									ArrayList terminoYPadreDescripcion = new  ArrayList();
									if(langTextosDesc!=null && langTextosDesc.length>0){
										
										for(int j=0;j<langTextosDesc.length;j++){
											terminoypadreVO = new TerminoYPadreVO();
											terminoypadreVO.setIdiomaTermino("en");
											terminoypadreVO.setIdVocabulario("5.11");
											terminoypadreVO.setIdTermino("");
											if(langTextosDesc[j].getIdioma()!=null)
												terminoypadreVO.setNomTermino(langTextosDesc[j].getIdioma());
											else
												terminoypadreVO.setNomTermino("");
											terminoYPadreDescripcion.add(terminoypadreVO);							
										}
									}else{
										terminoypadreVO = new TerminoYPadreVO();
										terminoypadreVO.setIdiomaTermino("en");
										terminoypadreVO.setIdVocabulario("5.11");
										terminoypadreVO.setIdTermino("");
										terminoypadreVO.setNomTermino("");
										terminoYPadreDescripcion.add(terminoypadreVO);
									}
									lDescripciones.add(terminoYPadreDescripcion);
								}
			
								for (int i = 0; i<lDescripciones.size();i++) {
									DescripcionVO descVO= new DescripcionVO();
									ArrayList terminoYPadreDesc = (ArrayList) lDescripciones.get(i);
									TerminoYPadreVO[] arrayTerminoYPadreDescripcion=(TerminoYPadreVO[])terminoYPadreDesc.toArray(new TerminoYPadreVO[terminoYPadreDesc.size()]);
									TerminoYPadreVO[] terminoYPadreDescripcionVuelta = this.getSrvVocabulariosControladosService().obtenerIdTermino(arrayTerminoYPadreDescripcion);
									LangStringVO[] langTextosDesc = descripcionVO[i].getTextos();
									LangStringVO[] nuevoLangTextosDesc = new LangStringVO[langTextosDesc.length];
									if(langTextosDesc!=null && langTextosDesc.length>0){
										for (int j = 0; j<terminoYPadreDescripcionVuelta.length; j++) {
											LangStringVO nuevoLangDesc = new LangStringVO();
											nuevoLangDesc.setIdioma(terminoYPadreDescripcionVuelta[j].getIdTermino());
											nuevoLangDesc.setTexto(langTextosDesc[j].getTexto().trim());
											nuevoLangTextosDesc[j] = nuevoLangDesc;
										}
										descVO.setTextos(nuevoLangTextosDesc);
									}else{
										nuevoLangTextosDesc=new LangStringVO[1];
										LangStringVO nuevoLangDesc = new LangStringVO();
										nuevoLangDesc.setIdioma("");
										nuevoLangDesc.setTexto("");
										nuevoLangTextosDesc[0] = nuevoLangDesc;
										descVO.setTextos(nuevoLangTextosDesc);
									}
									nuevoDescripcionVO[i]=descVO;
								}	
							}
						}
						else{
							nuevoDescripcionVO = new DescripcionVO[1];
							LangStringVO[] aLangString =new LangStringVO[1];
							LangStringVO lang = new LangStringVO();
							lang.setIdioma("");
							lang.setTexto("");
							aLangString[0]=lang;
							DescripcionVO descVO= new DescripcionVO();
							descVO.setTextos(aLangString);
							nuevoDescripcionVO[0] = descVO;
						}
			
			            form.setDescripcionAsArray(nuevoDescripcionVO);
					}else{
						//RECURSO
						
						form.setCatalogo("");
						form.setEntrada("");
						//DESCRIPCION
						nuevoDescripcionVO = new DescripcionVO[1];
						LangStringVO[] aLangStringD =new LangStringVO[1];
						LangStringVO langD = new LangStringVO();
						langD.setIdioma("");
						langD.setTexto("");
						aLangStringD[0]=langD;
						DescripcionVO descVO= new DescripcionVO();
						descVO.setTextos(aLangStringD);
						nuevoDescripcionVO[0] = descVO;
						form.setDescripcionAsArray(nuevoDescripcionVO);

						
					}
//					si relacion[i] era null en el objeto de session lo dejamos a null
		            if(relacionEsNull)
		            {
		            	catalogadorAvSession.getMDSesion().getRelaciones()[iRel]=null;
		            }
			 }else{
					// Caso CREAR
					
					//posicion en la que vamos a guardar la relacion
				 
				 String titulo ="1";
				 if(relaciones == null || relaciones.length == 0){//si no hay relaciones lo guardamos en la primera
						iRel = 0;
						form.setTituloRelacion(titulo); //el numero que se muestra junto con Relacion
					}
					else{//si ya habia alguna la guardamos en al final habra que añadirle una posición al array de relaciones
						iRel = relaciones.length;
						titulo = String.valueOf(relaciones.length + 1);
						form.setTituloRelacion(titulo); //el numero	
					}
				 
//				TIPO
					form.setTipo("");
					
				//RECURSO
					
					form.setCatalogo("");
					form.setEntrada("");
					//DESCRIPCION
					DescripcionVO[] nuevoDescripcionVO = new DescripcionVO[1];
					LangStringVO[] aLangStringD =new LangStringVO[1];
					LangStringVO langD = new LangStringVO();
					langD.setIdioma("");
					langD.setTexto("");
					aLangStringD[0]=langD;
					DescripcionVO descVO= new DescripcionVO();
					descVO.setTextos(aLangStringD);
					nuevoDescripcionVO[0] = descVO;
					form.setDescripcionAsArray(nuevoDescripcionVO);
					
			 }
			 
			RelacionSession relacionSession = this.getRelacionSession(request);
			//cargamos la ayuda contextual
			cargarAyuda(relacionSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
            
			
			
    	}catch (Exception e){
			logger.error("Error en catalogadorWeb, categoría DetalleRelación, metodo cargarDetalle " + e);
			//throw new java.lang.Exception("detalle.relacion.cu", e);
			saveErrorMessage(request, " Error en catalogadorWeb, categoría DetalleRelación, metodo cargarDetalle ");
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
     * @see es.pode.catalogadorWeb.presentacion.categoriasAvanzado.relacion.detalleRelacion.DetalleRelacionController#anadirContDescripcion(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.relacion.detalleRelacion.AnadirContDescripcionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void anadirContDescripcion(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.relacion.detalleRelacion.AnadirContDescripcionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
		
		Object valor = request.getSession().getAttribute("form");
		
		if (valor instanceof DetalleRelacionCUFormImpl) {
//			form.setTipo(((DetalleRelacionCUFormImpl)valor).getTipo());
			form.setTipoLabelList(((DetalleRelacionCUFormImpl)valor).getTipoLabelList());
			form.setTipoValueList(((DetalleRelacionCUFormImpl)valor).getTipoValueList());
			form.setCatalogo(((DetalleRelacionCUFormImpl)valor).getCatalogo());
			form.setEntrada(((DetalleRelacionCUFormImpl)valor).getEntrada());
			form.setDescripcion(((DetalleRelacionCUFormImpl)valor).getDescripcion());
			form.setComboIdiomaLabelList(((DetalleRelacionCUFormImpl)valor).getComboIdiomaLabelList());
			form.setComboIdiomaValueList(((DetalleRelacionCUFormImpl)valor).getComboIdiomaValueList());
		}else if(valor instanceof DetalleRelacionFormSubmitFormFormImpl){
//			form.setTipo(((DetalleRelacionFormSubmitFormFormImpl)valor).getTipo());
			form.setTipoLabelList(((DetalleRelacionFormSubmitFormFormImpl)valor).getTipoLabelList());
			form.setTipoValueList(((DetalleRelacionFormSubmitFormFormImpl)valor).getTipoValueList());
			form.setCatalogo(((DetalleRelacionFormSubmitFormFormImpl)valor).getCatalogo());
			form.setEntrada(((DetalleRelacionFormSubmitFormFormImpl)valor).getEntrada());
			form.setDescripcion(((DetalleRelacionFormSubmitFormFormImpl)valor).getDescripcion());
			form.setComboIdiomaLabelList(((DetalleRelacionFormSubmitFormFormImpl)valor).getComboIdiomaLabelList());
			form.setComboIdiomaValueList(((DetalleRelacionFormSubmitFormFormImpl)valor).getComboIdiomaValueList());
		}
		else if(valor instanceof RelacionValidaVolverFormImpl){
//			form.setTipo(((RelacionValidaVolverFormImpl)valor).getTipo());
			form.setTipoLabelList(((RelacionValidaVolverFormImpl)valor).getTipoLabelList());
			form.setTipoValueList(((RelacionValidaVolverFormImpl)valor).getTipoValueList());
			form.setCatalogo(((RelacionValidaVolverFormImpl)valor).getCatalogo());
			form.setEntrada(((RelacionValidaVolverFormImpl)valor).getEntrada());
			form.setDescripcion(((RelacionValidaVolverFormImpl)valor).getDescripcion());
			form.setComboIdiomaLabelList(((RelacionValidaVolverFormImpl)valor).getComboIdiomaLabelList());
			form.setComboIdiomaValueList(((RelacionValidaVolverFormImpl)valor).getComboIdiomaValueList());
		}else if(valor instanceof RelacionNoValidaVolverFormImpl){
//			form.setTipo(((RelacionNoValidaVolverFormImpl)valor).getTipo());
			form.setTipoLabelList(((RelacionNoValidaVolverFormImpl)valor).getTipoLabelList());
			form.setTipoValueList(((RelacionNoValidaVolverFormImpl)valor).getTipoValueList());
			form.setCatalogo(((RelacionNoValidaVolverFormImpl)valor).getCatalogo());
			form.setEntrada(((RelacionNoValidaVolverFormImpl)valor).getEntrada());
			form.setDescripcion(((RelacionNoValidaVolverFormImpl)valor).getDescripcion());
			form.setComboIdiomaLabelList(((RelacionNoValidaVolverFormImpl)valor).getComboIdiomaLabelList());
			form.setComboIdiomaValueList(((RelacionNoValidaVolverFormImpl)valor).getComboIdiomaValueList());
		}
		
	
		int[] longitudTextosDesc = new int[form.getDescripcion().size()];
		for(int i= 0;i< form.getDescripcion().size();i++){
			longitudTextosDesc[i]=((DescripcionVO)(form.getDescripcionAsArray()[i])).getTextos().length;
		}

		//borramos session 
		request.getSession().removeAttribute(RelacionSession.SESSION_KEY);
		cambioFormulario(request,longitudTextosDesc,null);
		//recuperams session
		RelacionSession relacionSession = this.getRelacionSession(request);
		
		//form.setTipo(tipo.getValor());
		form.setTipo(relacionSession.getTipo().getValor());
		//form.setCatalogo(catalogo);
		form.setCatalogo(relacionSession.getCatalogo());
		//form.setEntrada(entrada);
		form.setEntrada(relacionSession.getEntrada());
		//form.setDescripcionAsArray(lDescripciones);
		form.setDescripcionAsArray(relacionSession.getDescripciones().toArray());
		
		String posicion = request.getAttribute("posicion").toString();
		int posicionInt = Integer.parseInt(posicion);
    	//se recogen todas las descripciones
    	DescripcionVO[] descArray = (DescripcionVO[]) form.getDescripcionAsArray();
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
    	form.setDescripcionAsArray(descArray);
    	//actualizamos session
    	relacionSession.setDescripciones(Arrays.asList(descArray));
    	
    		cargarAyuda(relacionSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
    	
		}catch (IndexOutOfBoundsException ai) {
			logger
			.warn("Warning en catalogadorWeb, categoría DetalleRelacion, metodo anadirContDescripcion" + ai);
		}catch (Exception e) {
			logger
			.error("Error en Servicio de catalogacionWeb, categoría DetalleRelacion, metodo anadirContDescripcion " + e);
			//throw new Exception("detalle.relacion.cu",e);
			saveErrorMessage(request, " Error en Servicio de catalogacionWeb, categoría DetalleRelacion, metodo anadirContDescripcion ");
		}
     }




    /**
     * @see es.pode.catalogadorWeb.presentacion.categoriasAvanzado.relacion.detalleRelacion.DetalleRelacionController#anadirDescripcion(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.relacion.detalleRelacion.AnadirDescripcionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void anadirDescripcion(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.relacion.detalleRelacion.AnadirDescripcionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		try{
		
		Object valor = request.getSession().getAttribute("form");
		
		if (valor instanceof DetalleRelacionCUFormImpl) {
//			form.setTipo(((DetalleRelacionCUFormImpl)valor).getTipo());
			form.setTipoLabelList(((DetalleRelacionCUFormImpl)valor).getTipoLabelList());
			form.setTipoValueList(((DetalleRelacionCUFormImpl)valor).getTipoValueList());
			form.setCatalogo(((DetalleRelacionCUFormImpl)valor).getCatalogo());
			form.setEntrada(((DetalleRelacionCUFormImpl)valor).getEntrada());
			form.setDescripcion(((DetalleRelacionCUFormImpl)valor).getDescripcion());
			form.setComboIdiomaLabelList(((DetalleRelacionCUFormImpl)valor).getComboIdiomaLabelList());
			form.setComboIdiomaValueList(((DetalleRelacionCUFormImpl)valor).getComboIdiomaValueList());
		}else if(valor instanceof DetalleRelacionFormSubmitFormFormImpl){
//			form.setTipo(((DetalleRelacionFormSubmitFormFormImpl)valor).getTipo());
			form.setTipoLabelList(((DetalleRelacionFormSubmitFormFormImpl)valor).getTipoLabelList());
			form.setTipoValueList(((DetalleRelacionFormSubmitFormFormImpl)valor).getTipoValueList());
			form.setCatalogo(((DetalleRelacionFormSubmitFormFormImpl)valor).getCatalogo());
			form.setEntrada(((DetalleRelacionFormSubmitFormFormImpl)valor).getEntrada());
			form.setDescripcion(((DetalleRelacionFormSubmitFormFormImpl)valor).getDescripcion());
			form.setComboIdiomaLabelList(((DetalleRelacionFormSubmitFormFormImpl)valor).getComboIdiomaLabelList());
			form.setComboIdiomaValueList(((DetalleRelacionFormSubmitFormFormImpl)valor).getComboIdiomaValueList());
		}
		else if(valor instanceof RelacionValidaVolverFormImpl){
//			form.setTipo(((RelacionValidaVolverFormImpl)valor).getTipo());
			form.setTipoLabelList(((RelacionValidaVolverFormImpl)valor).getTipoLabelList());
			form.setTipoValueList(((RelacionValidaVolverFormImpl)valor).getTipoValueList());
			form.setCatalogo(((RelacionValidaVolverFormImpl)valor).getCatalogo());
			form.setEntrada(((RelacionValidaVolverFormImpl)valor).getEntrada());
			form.setDescripcion(((RelacionValidaVolverFormImpl)valor).getDescripcion());
			form.setComboIdiomaLabelList(((RelacionValidaVolverFormImpl)valor).getComboIdiomaLabelList());
			form.setComboIdiomaValueList(((RelacionValidaVolverFormImpl)valor).getComboIdiomaValueList());
		}else if(valor instanceof RelacionNoValidaVolverFormImpl){
//			form.setTipo(((RelacionNoValidaVolverFormImpl)valor).getTipo());
			form.setTipoLabelList(((RelacionNoValidaVolverFormImpl)valor).getTipoLabelList());
			form.setTipoValueList(((RelacionNoValidaVolverFormImpl)valor).getTipoValueList());
			form.setCatalogo(((RelacionNoValidaVolverFormImpl)valor).getCatalogo());
			form.setEntrada(((RelacionNoValidaVolverFormImpl)valor).getEntrada());
			form.setDescripcion(((RelacionNoValidaVolverFormImpl)valor).getDescripcion());
			form.setComboIdiomaLabelList(((RelacionNoValidaVolverFormImpl)valor).getComboIdiomaLabelList());
			form.setComboIdiomaValueList(((RelacionNoValidaVolverFormImpl)valor).getComboIdiomaValueList());
		}
		
	
		int[] longitudTextosDesc = new int[form.getDescripcion().size()];
		for(int i= 0;i< form.getDescripcion().size();i++){
			longitudTextosDesc[i]=((DescripcionVO)(form.getDescripcionAsArray()[i])).getTextos().length;
		}

		//borramos session 
		request.getSession().removeAttribute(RelacionSession.SESSION_KEY);
		cambioFormulario(request,longitudTextosDesc,null);
		//recuperams session
		RelacionSession relacionSession = this.getRelacionSession(request);
		
//		form.setTipo(tipo.getValor());
		form.setTipo(relacionSession.getTipo().getValor());
		//form.setCatalogo(catalogo);
		form.setCatalogo(relacionSession.getCatalogo());
		//form.setEntrada(entrada);
		form.setEntrada(relacionSession.getEntrada());
		//form.setDescripcionAsArray(lDescripciones);
		form.setDescripcionAsArray(relacionSession.getDescripciones().toArray());

		
//		añadimos un campo de idioma vacio
		Object[] aDescripciones=form.getDescripcionAsArray();
		int nuevoTamano = aDescripciones.length +1;
		Object[] newADescripciones = new Object[nuevoTamano];
		DescripcionVO descripcionVO=new DescripcionVO();
		LangStringVO[] aLangString=new LangStringVO[1];
		LangStringVO langString = new LangStringVO();
		langString.setIdioma("");
		langString.setTexto("");
		aLangString[0]= langString;
		descripcionVO.setTextos(aLangString);
		for(int i = 0; i<aDescripciones.length;i++)
			newADescripciones[i]=aDescripciones[i];
		newADescripciones[nuevoTamano-1]= descripcionVO;
		   
		form.setDescripcionAsArray(newADescripciones);
		//actualizamos session
		relacionSession.setDescripciones(Arrays.asList(newADescripciones));
		
		cargarAyuda(relacionSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
		
		}catch (IndexOutOfBoundsException ai) {
			logger
			.warn("Warning en catalogadorWeb, categoría DetalleRelacion, metodo anadirDescripcion" + ai);
		}catch (Exception e) {
			logger
			.error("Error en Servicio de catalogacionWeb, categoría DetalleRelacion, metodo anadirDescripcion " + e);
			//throw new Exception("detalle.relacion.cu",e);
			saveErrorMessage(request, " Error en Servicio de catalogacionWeb, categoría DetalleRelacion, metodo anadirDescripcion ");
		}
     }



    /**
     * @see es.pode.catalogadorWeb.presentacion.categoriasAvanzado.relacion.detalleRelacion.DetalleRelacionController#accionSubmit(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.relacion.detalleRelacion.AccionSubmitForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String accionSubmit(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.relacion.detalleRelacion.AccionSubmitForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
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
        
//        if (datosResources.getString("detallerelacion.AñadirDescripcion").equals(accion)) {
//              resAction = "Añadir Descripcion";
//        }else if (datosResources.getString("detallerelacion.EliminarDescripcion").equals(accion)) {
//        	resAction ="Eliminar Descripcion";
//        }else if (datosResources.getString("detallerelacion.AñadirContenidoDescripcion").equals(accion)) {
//        	resAction ="Añadir ContDescripcion";
//        }else if (datosResources.getString("detallerelacion.EliminarContenidoDescripcion").equals(accion)) {
//        	resAction ="Eliminar ContDescripcion";
//        }else 
        if (datosResources.getString("detallerelacion.Aceptar").equals(accion)) {
        	resAction ="Aceptar";
        }else if (datosResources.getString("detallerelacion.Validar").equals(accion)) {
        	resAction ="Validar";
        }else if (datosResources.getString("detallerelacion.Cancelar").equals(accion)) {
        	resAction ="Cancelar";
        }else if (datosResources.getString("detallerelacion.Reset").equals(accion)) {
        	resAction ="Reset";
        }else
        	resAction = accion;
        
        return resAction;
    }



    public final void guardarRelacion(
    		ActionMapping mapping, 
    		GuardarRelacionForm form, 
    		HttpServletRequest request, 
    		HttpServletResponse response) 
    throws Exception
    {
    	boolean errorFaltaIdioma = false;
		boolean errorFaltaTexto = false;

		CatalogadorAvSession catalogadorAvSession=this.getCatalogadorAvSession(request);
		LomAvanzadoVO auxAvanzado=null;
		
		auxAvanzado=new LomAvanzadoVO();
		 
		AvRelationVO relacion = new AvRelationVO();
		AvRelationVO[] arrayRelacion = new AvRelationVO[1];
		arrayRelacion[0] = relacion;
		auxAvanzado.setRelaciones(arrayRelacion);
		
		try{

			String source=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES);//esquemaDeMetadatos
			
			String idiomaLdap=((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
			
			String usuario =LdapUserDetailsUtils.getUsuario();
			//String usuario="empaquetador";
			String identificador =request.getParameter("identificador");
			if(identificador==null){
			   identificador=catalogadorAvSession.getIdentificador();
			}
			String returnURL=request.getParameter("returnURL");
			if(returnURL==null)
				returnURL=catalogadorAvSession.getReturnURL();
			
			catalogadorAvSession.setIdioma(idiomaLdap);
			catalogadorAvSession.setIdentificador(identificador);
			catalogadorAvSession.setUsuario(usuario);
			//			 metemos en la sesion el parametro de vuelta al empaquetador
			catalogadorAvSession.setReturnURL(returnURL);
			
	    	Object valor = request.getSession().getAttribute("form");
	    	
	//    	Obtenemos la longitudes de los VOs, que pasamos a cambioFormulario para que se obtengan del request los cambios que hemos hecho
	    	//Pues en el form que nos viene como parametro no los guarda.
	    	int longitudDescripciones=0;
	
	    	int[] longitudTextosDesc = new int[0];
	    	
	    	if (valor instanceof DetalleRelacionCUFormImpl) {
	    		DetalleRelacionCUFormImpl valorDe = ((DetalleRelacionCUFormImpl)valor);
	    		Object[] desc = valorDe.getDescripcionAsArray();
				longitudTextosDesc = new int[desc.length];
				for(int i= 0;i< desc.length;i++){
					longitudTextosDesc[i]=((DescripcionVO)(desc[i])).getTextos().length;
				}
				
	
				longitudDescripciones=desc.length;
			  	 		
	    	}
	    	else if(valor instanceof DetalleRelacionFormSubmitFormFormImpl ){
	    		DetalleRelacionFormSubmitFormFormImpl valorGen = ((DetalleRelacionFormSubmitFormFormImpl)valor);
	    		Object[] desc = valorGen.getDescripcionAsArray();
				longitudTextosDesc = new int[desc.length];
				for(int i= 0;i< desc.length;i++){
					longitudTextosDesc[i]=((DescripcionVO)(desc[i])).getTextos().length;
				}
	
				
				
			  	longitudDescripciones=desc.length;
			  	
	    	}
	    	else if(valor instanceof RelacionValidaVolverFormImpl ){
	    		RelacionValidaVolverFormImpl valorGen = ((RelacionValidaVolverFormImpl)valor);
	    		Object[] desc = valorGen.getDescripcionAsArray();
				longitudTextosDesc = new int[desc.length];
				for(int i= 0;i< desc.length;i++){
					longitudTextosDesc[i]=((DescripcionVO)(desc[i])).getTextos().length;
				}
	
				
				
			  	longitudDescripciones=desc.length;
			  	
	    	}
	    	else if(valor instanceof RelacionNoValidaVolverFormImpl ){
	    		RelacionNoValidaVolverFormImpl valorGen = ((RelacionNoValidaVolverFormImpl)valor);
	    		Object[] desc = valorGen.getDescripcionAsArray();
				longitudTextosDesc = new int[desc.length];
				for(int i= 0;i< desc.length;i++){
					longitudTextosDesc[i]=((DescripcionVO)(desc[i])).getTextos().length;
				}
	
			  	longitudDescripciones=desc.length;
			  	
	    	}
	    	
	    	//borramos session 
			request.getSession().removeAttribute(RelacionSession.SESSION_KEY);
			// actualizamos los datos que hemos cambiado con este metodo
			cambioFormulario(request, longitudTextosDesc, null);
			//recuperams session
			RelacionSession relacionSession = this.getRelacionSession(request);
			
//			form.setTipo(tipo.getValor());
			form.setTipo(relacionSession.getTipo().getValor());
			//form.setCatalogo(catalogo);
			form.setCatalogo(relacionSession.getCatalogo());
			//form.setEntrada(entrada);
			form.setEntrada(relacionSession.getEntrada());
			//form.setDescripcionAsArray(lDescripciones);
			form.setDescripcionAsArray(relacionSession.getDescripciones().toArray());
			
	//		TIPOS 
		  	SourceValueVO tipoAux = new SourceValueVO();
	    	
	    		SourceValueVO auxTipoRecurso = new SourceValueVO();
	    		auxTipoRecurso.setValor(relacionSession.getTipo().getValor());
	    		auxTipoRecurso.setSource(source);
	    		tipoAux = auxTipoRecurso;
	
	//    		DESCRIPCIONES
	    		DescripcionVO[] lDescripciones = (DescripcionVO[])relacionSession.getDescripciones()
	    				.toArray(new DescripcionVO[relacionSession.getDescripciones().size()]);
	        	DescripcionVO[]	descripcionesAux = new DescripcionVO[lDescripciones.length];
	        	for(int i=0;i<lDescripciones.length;i++){
	        		DescripcionVO descripAux= new DescripcionVO();
	        		LangStringVO[] langDescrip = lDescripciones[i].getTextos();
	        		LangStringVO[] langDescripAux = new LangStringVO[langDescrip.length];
	        		for(int j=0;j<langDescrip.length;j++){
	        			LangStringVO nuevoLang = new LangStringVO();
	        			nuevoLang.setIdioma(langDescrip[j].getIdioma());
	        			nuevoLang.setTexto(langDescrip[j].getTexto().trim());
	        			langDescripAux[j] = nuevoLang;
	        		}
	        		descripAux.setTextos(langDescripAux);
	        		descripcionesAux[i]=descripAux;
	        	}
	        	
	        	String catalogo = relacionSession.getCatalogo();
	        	String entrada = relacionSession.getEntrada();
	        	if (valor instanceof DetalleRelacionCUFormImpl) {
	        		((DetalleRelacionCUFormImpl)valor).setTipo(tipoAux.getValor());
	        		((DetalleRelacionCUFormImpl)valor).setCatalogo(catalogo);
	        		((DetalleRelacionCUFormImpl)valor).setEntrada(entrada);
	        		((DetalleRelacionCUFormImpl)valor).setDescripcionAsArray(descripcionesAux);
	        		
	        		
	    		}
	        	else if(valor instanceof DetalleRelacionFormSubmitFormFormImpl){
	        		((DetalleRelacionFormSubmitFormFormImpl)valor).setTipo(tipoAux.getValor());
	        		((DetalleRelacionFormSubmitFormFormImpl)valor).setCatalogo(catalogo);
	        		((DetalleRelacionFormSubmitFormFormImpl)valor).setEntrada(entrada);
	        		((DetalleRelacionFormSubmitFormFormImpl)valor).setDescripcionAsArray(descripcionesAux);
	        		
	          	}
	        	else if(valor instanceof RelacionValidaVolverFormImpl){
	        		((RelacionValidaVolverFormImpl)valor).setTipo(tipoAux.getValor());
	        		((RelacionValidaVolverFormImpl)valor).setCatalogo(catalogo);
	        		((RelacionValidaVolverFormImpl)valor).setEntrada(entrada);
	        		((RelacionValidaVolverFormImpl)valor).setDescripcionAsArray(descripcionesAux);
	        		
	          	}
	        	else if(valor instanceof RelacionNoValidaVolverFormImpl){
	        		((RelacionNoValidaVolverFormImpl)valor).setTipo(tipoAux.getValor());
	        		((RelacionNoValidaVolverFormImpl)valor).setCatalogo(catalogo);
	        		((RelacionNoValidaVolverFormImpl)valor).setEntrada(entrada);
	        		((RelacionNoValidaVolverFormImpl)valor).setDescripcionAsArray(descripcionesAux);
	        		
	          	}
	        	
	    	  	//catalogo=form.getCatalogo();
	        	relacionSession.setCatalogo(form.getCatalogo());
	    	  	//entrada=form.getEntrada();
	        	relacionSession.setEntrada(form.getEntrada());
	    	  	
	    	  	dameTerminoId(relacionSession);  
	    	  	
	    	  	//ayuda contextual
	    		cargarAyuda(relacionSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
				
	//    	  Comprobamos si los campos ha sido rellenados adecuadamente
	//    	  DESCRIPCIONES
	    		ArrayList listaDesc=new ArrayList();
	    		//ojo!!!
	    		lDescripciones = (DescripcionVO[])relacionSession.getDescripciones()
					.toArray(new DescripcionVO[relacionSession.getDescripciones().size()]);
	    		if(lDescripciones!=null && lDescripciones.length != 0){

	    			LangStringVO[] textos = lDescripciones[0].getTextos();
	    			if(lDescripciones.length == 1 && textos.length == 1 && textos[0].getIdioma().equals("") 
	    					&& textos[0].getTexto().equals("")){
	    				lDescripciones = null;
	    			}
	    			else{
	    				for(int i=0;i<lDescripciones.length;i++){
	    					if(lDescripciones[i]!=null){
	    						ArrayList listDesc=new ArrayList();
	    						DescripcionVO lDesc=lDescripciones[i];
	    						DescripcionVO lDescrip=new DescripcionVO();
	    						for(int j=0;j<lDesc.getTextos().length;j++){
	    							LangStringVO lang=lDesc.getTextos()[j];
	    							LangStringVO lLang=new LangStringVO();
	    							String idioma=lang.getIdioma();
	    							String texto=lang.getTexto();
	    							if(((idioma!=null)&&(!idioma.equals(""))) && ((texto!=null)&&(!texto.trim().equals("")))){
	    								lLang.setIdioma(idioma);
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
	    							lDescrip.setTextos(langs);   
	    							listaDesc.add(lDescrip);
	    						}
	    					}
	    				}
	    				if(listaDesc.size()== 0)
	    					lDescripciones = null;
	    				else
	    					lDescripciones=(DescripcionVO[])listaDesc.toArray(new DescripcionVO[listaDesc.size()]);
	    			}
	    			
	    		}
	    		else{
	    			lDescripciones =null;
	    		}
	    		
	    		RecursoVO recurso=null;
	    		if((catalogo!=null && !catalogo.equals("")) ||
	    			(entrada!=null && !entrada.equals("")) || 
	    			(lDescripciones!=null && lDescripciones.length>0))
	    		{
	    			recurso = new RecursoVO();
		    		IdentificadorVO identifica=new IdentificadorVO();
		    		identifica.setCatalogo(catalogo);
		    		identifica.setEntrada(entrada);
		    		recurso.setIdentificador(identifica);
		    		recurso.setDescripciones(lDescripciones);
	    		}
	    		
	    		if(recurso!=null || relacionSession.getTipo().getValor()!=null && !relacionSession.getTipo().getValor().equals(""))
	    		{
		    		auxAvanzado.getRelaciones()[0].setRecurso(recurso);
		    		auxAvanzado.getRelaciones()[0].setTipo(relacionSession.getTipo());
	    		}else
	    			auxAvanzado=null;

	    		
	    		
		}catch (Exception e){
			logger.error("Error en catalogadorWeb, categoría DetalleRelación, metodo anadirContDescripcion " + e);
			//throw new java.lang.Exception("detalle.relacion.cu", e);
			saveErrorMessage(request, " Error en catalogadorWeb, categoría DetalleRelación, metodo anadirContDescripcion ");
		}
    	if(!errorFaltaIdioma && !errorFaltaTexto ){				
    		try{
    			// Cargamos el objeto de sesion
    			if(catalogadorAvSession.getMDSesion()==null ){
    				if(auxAvanzado!=null){
    					catalogadorAvSession.setMDSesion(auxAvanzado);
    				}
    				
    			}else{
    				AvRelationVO[] relaciones=catalogadorAvSession.getMDSesion().getRelaciones();
    				// comprobamos que educaciones no sea null, si es null nos creamos uno	
    					 if (relaciones== null ){
    						 if(auxAvanzado!=null)
    							 catalogadorAvSession.getMDSesion().setRelaciones(auxAvanzado.getRelaciones());
    					 } 
    					 else{ 
    			    		 //creamos uno mas (cogemos la longitud de las educaciones pues el nuevo indice, si teniamos 4 educaciones los indices
    						 //iban de 0 a 3 el indice para la nueva educacion seria 4 y ahora tendriamos 5 educaciones con indices de 0 a 4)
    						 if (relaciones.length == iRel)//GUARDAR CUANDO HEMOS PULSADO BOTON CREAR
    						 {
								 ArrayList listaRel= new ArrayList();
								 for(int i = 0; i<relaciones.length;i++)
									 listaRel.add( relaciones[i]);
								 
								 if(auxAvanzado != null && auxAvanzado.getRelaciones()[0]!=null )
								 {
									 listaRel.add( auxAvanzado.getRelaciones()[0]);
								 }
								 
								 AvRelationVO[] relacionesAux = (AvRelationVO[]) listaRel.toArray(new AvRelationVO[0]);
								 catalogadorAvSession.getMDSesion().setRelaciones(relacionesAux);

    						 }
    						 else{//GUARDAR CUANDO HEMOS PULSADO BOTON MODIFICAR
								 ArrayList listaRel= new ArrayList();
								 for(int i = 0; i<relaciones.length;i++)
								 {
									 if(iRel==i)
									 {
										 if(auxAvanzado != null && auxAvanzado.getRelaciones()[0]!=null)
											 listaRel.add(auxAvanzado.getRelaciones()[0]);
									 }else
										 listaRel.add(relaciones[i]);
								 }
								 AvRelationVO[] relacionesAux = (AvRelationVO[]) listaRel.toArray(new AvRelationVO[0]);
								 catalogadorAvSession.getMDSesion().setRelaciones(relacionesAux);
    						 }
    					 }
    		      }
    			
    			}catch (Exception e){
    				logger.error("Error en catalogadorWeb, categoría DetalleRelación, metodo anadirContDescripcion " + e);
    				//throw new java.lang.Exception("detalle.relacion.cu", e);
    				saveErrorMessage(request, " Error en catalogadorWeb, categoría DetalleRelación, metodo anadirContDescripcion ");
    			}
    		}else{
    			if (errorFaltaIdioma && errorFaltaTexto)
    				throw new ValidatorException("{general.error.idioma_texto}");
    			else if (!errorFaltaIdioma && errorFaltaTexto)
    				throw new ValidatorException("{general.error.texto}");
    			else if (errorFaltaIdioma && !errorFaltaTexto)
    				throw new ValidatorException("{general.error.idioma}");
    			
    		}
    }





    /**
     * @see es.pode.catalogadorWeb.presentacion.categoriasAvanzado.relacion.detalleRelacion.DetalleRelacionController#esValidaRelacion(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.relacion.detalleRelacion.EsValidaRelacionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final boolean esValidaRelacion(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.relacion.detalleRelacion.EsValidaRelacionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	//String idiomaLocale=((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
		ResourceBundle datosResources = I18n.getInstance().getResource("application-resources", (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
		
		Collection mensajes = new ArrayList();
		AvRelationVO relacionAux= new AvRelationVO();
		//Recoge los valores y los cambios en el formulario
		Object valor = request.getSession().getAttribute("form");
		//rellenamos los datos del formulario dependiendo del tipo de formulario que nos venga
		
		int longitudDescripciones=0;

    	int[] longitudTextosDesc = new int[0];
    	
    	if (valor instanceof DetalleRelacionCUFormImpl) {
    		DetalleRelacionCUFormImpl valorDe = ((DetalleRelacionCUFormImpl)valor);
    		Object[] desc = valorDe.getDescripcionAsArray();
			longitudTextosDesc = new int[desc.length];
			for(int i= 0;i< desc.length;i++){
				longitudTextosDesc[i]=((DescripcionVO)(desc[i])).getTextos().length;
			}
			

			longitudDescripciones=desc.length;
		  	 		
    	}
    	else if(valor instanceof DetalleRelacionFormSubmitFormFormImpl ){
    		DetalleRelacionFormSubmitFormFormImpl valorGen = ((DetalleRelacionFormSubmitFormFormImpl)valor);
    		Object[] desc = valorGen.getDescripcionAsArray();
			longitudTextosDesc = new int[desc.length];
			for(int i= 0;i< desc.length;i++){
				longitudTextosDesc[i]=((DescripcionVO)(desc[i])).getTextos().length;
			}

			
			
		  	longitudDescripciones=desc.length;
		  	
    	}
    	else if(valor instanceof RelacionValidaVolverFormImpl ){
    		RelacionValidaVolverFormImpl valorGen = ((RelacionValidaVolverFormImpl)valor);
    		Object[] desc = valorGen.getDescripcionAsArray();
			longitudTextosDesc = new int[desc.length];
			for(int i= 0;i< desc.length;i++){
				longitudTextosDesc[i]=((DescripcionVO)(desc[i])).getTextos().length;
			}

			
			
		  	longitudDescripciones=desc.length;
		  	
    	}
    	else if(valor instanceof RelacionNoValidaVolverFormImpl ){
    		RelacionNoValidaVolverFormImpl valorGen = ((RelacionNoValidaVolverFormImpl)valor);
    		Object[] desc = valorGen.getDescripcionAsArray();
			longitudTextosDesc = new int[desc.length];
			for(int i= 0;i< desc.length;i++){
				longitudTextosDesc[i]=((DescripcionVO)(desc[i])).getTextos().length;
			}

			
			
		  	longitudDescripciones=desc.length;
		  	
    	}
    	
    	//borramos session
    	request.getSession().removeAttribute(RelacionSession.SESSION_KEY);
//    	actualizamos los datos que hemos cambiado con este metodo
		cambioFormulario(request, longitudTextosDesc, null);
		//recogemos la session
		RelacionSession relacionSession = this.getRelacionSession(request);
		
		relacionAux.setTipo(relacionSession.getTipo());
		RecursoVO recurso=new RecursoVO();
		DescripcionVO[] lDescripciones = (DescripcionVO[])relacionSession.getDescripciones()
			.toArray(new DescripcionVO[relacionSession.getDescripciones().size()]);
		recurso.setDescripciones(lDescripciones);
		IdentificadorVO identificador=new IdentificadorVO();
		identificador.setCatalogo(relacionSession.getCatalogo());
		identificador.setEntrada(relacionSession.getEntrada());
		recurso.setIdentificador(identificador);
		relacionAux.setRecurso(recurso);
		
		
		String resultado= "";
		boolean ok= true;
		String error = "";
		//TIPO
		
		if((relacionAux.getTipo()!=null)){
			String tip=relacionAux.getTipo().getValor();
			if((tip==null)||(tip.equals(""))){
				error = datosResources.getString("CAV.7.1");
				if(!mensajes.contains(error))
					mensajes.add(error);
			}
		}
		
		//IDENTIFICADOR
		
		if((ok)&&(relacionAux.getRecurso()!=null)&&(relacionAux.getRecurso().getIdentificador()!=null)&&(relacionAux.getTipo()!=null)){
			String cat=relacionAux.getRecurso().getIdentificador().getCatalogo();
			String ent=relacionAux.getRecurso().getIdentificador().getEntrada();
			String tip=relacionAux.getTipo().getValor();
			if(((cat==null)||(cat.trim().equals("")))&& ((ent==null)||(ent.trim().equals("")))){//Si existe una entrada, un catálogo es obligatorio
				error = datosResources.getString("CAV.7.3");
				if(!mensajes.contains(error))
					mensajes.add(error);
			}
			if (!resultado.equals("")){
				ok= false;
			}
			
		}
//		CATALOGOS
		ok=true;
		if((ok)&&(relacionAux.getRecurso()!=null)&&(relacionAux.getRecurso().getIdentificador()!=null)&&(relacionAux.getTipo()!=null)){
			String cat=relacionAux.getRecurso().getIdentificador().getCatalogo();
			String ent=relacionAux.getRecurso().getIdentificador().getEntrada();
			String tip=relacionAux.getTipo().getValor();
			if(((cat==null)||(cat.trim().equals("")))&& ((ent!=null)&&(!ent.trim().equals("")))){//Si existe una entrada, un catálogo es obligatorio
				error = datosResources.getString("CAV.7.3.1");
				if(!mensajes.contains(error))
					mensajes.add(error);
			}
			
			if (!resultado.equals("")){
				ok= false;
			}
			
		}
		
		//ENTRADAS

		if((ok)&&(relacionAux.getRecurso()!=null)&&(relacionAux.getRecurso().getIdentificador()!=null)){
			String cat=relacionAux.getRecurso().getIdentificador().getCatalogo();
			String ent=relacionAux.getRecurso().getIdentificador().getEntrada();
			
			if (((ent==null)||(ent.trim().equals("")))&& ((cat!=null)&&(!cat.trim().equals("")))){//Si existe un catálogo, una entrada es obligatoria
				error = datosResources.getString("CAV.7.3.2");
				if(!mensajes.contains(error))
					mensajes.add(error);
			}
			
			if (!resultado.equals("")){
				ok= false;
			}
			
		}
		
		//DESCRIPCIONES
		if(relacionAux != null && relacionAux.getRecurso() != null){
			if(relacionAux.getRecurso().getDescripciones() != null && relacionAux.getRecurso().getDescripciones().length > 0){
				for(int i = 0; i < relacionAux.getRecurso().getDescripciones().length; i++){
					if(relacionAux.getRecurso().getDescripciones()[i].getTextos() != null && relacionAux.getRecurso().getDescripciones()[i].getTextos().length > 0){
						for(int j = 0; j < relacionAux.getRecurso().getDescripciones()[i].getTextos().length; j++){
							String texto = relacionAux.getRecurso().getDescripciones()[i].getTextos()[j].getTexto();
							String idioma = relacionAux.getRecurso().getDescripciones()[i].getTextos()[j].getIdioma();
							if(texto != null && !texto.equals("") && (idioma == null || idioma.equals(""))){
								error = datosResources.getString("CAV.7.3.3");
								if(!mensajes.contains(error))
									mensajes.add(error); //si descripción está relleno, el idioma también debe estarlo
							}
							if((texto == null || texto.equals("")) && idioma != null && !idioma.equals("")){
								error = datosResources.getString("CAV.7.3.4");
								if(!mensajes.contains(error))
									mensajes.add(error); //si idioma está relleno, descripción también debe estarlo
							}
						}
					}
				}
			}
		}
		
//		//DESCRIPCIONES
//		boolean datos=true;
//		if(datos && relacionAux.getRecurso()!=null && relacionAux.getRecurso().getDescripciones()!=null && relacionAux.getRecurso().getDescripciones().length>0){
//			DescripcionVO[] lDesc=relacionAux.getRecurso().getDescripciones();
//			IdentificadorVO ident=relacionAux.getRecurso().getIdentificador();
//			String cata="";
//			String entr="";
//			String tip="";
//			if(relacionAux.getTipo()!=null){
//				tip=relacionAux.getTipo().getValor();
//			}
//			if(ident!=null){
//				cata=ident.getCatalogo();
//				entr=ident.getEntrada();
//			}
//			for(int i=0;i<lDesc.length && datos;i++){
//				LangStringVO[] textos=lDesc[i].getTextos();
//				for(int j=0;j<textos.length &&datos;j++){
//					
//					String idioma=textos[j].getIdioma();
//					String texto=textos[j].getTexto();
//					
//					
//					
//					if((datos &&((idioma!=null)&&(!idioma.equals("")))||((texto!=null)&&(!texto.equals(""))))&&((tip.equals(""))&&(cata.equals(""))&&(entr.equals("")))){
//						mensajes.add(datosResources.getString("CAV.7.4"));
//						datos=false;
//					}
//					
//				}
//			}
//		}
		
		if(mensajes.size()>0)
		{
			form.setMensajesError(mensajes);
			return false;
		}else{
			return true;
		}
    }


    /**
     * @see es.pode.catalogadorWeb.presentacion.categoriasAvanzado.relacion.detalleRelacion.DetalleRelacionController#eliminarContDescripcion(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.relacion.detalleRelacion.EliminarContDescripcionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void eliminarContDescripcion(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.relacion.detalleRelacion.EliminarContDescripcionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
       try{
		
		Object valor = request.getSession().getAttribute("form");
		
		if (valor instanceof DetalleRelacionCUFormImpl) {
//			form.setTipo(((DetalleRelacionCUFormImpl)valor).getTipo());
			form.setTipoLabelList(((DetalleRelacionCUFormImpl)valor).getTipoLabelList());
			form.setTipoValueList(((DetalleRelacionCUFormImpl)valor).getTipoValueList());
			form.setCatalogo(((DetalleRelacionCUFormImpl)valor).getCatalogo());
			form.setEntrada(((DetalleRelacionCUFormImpl)valor).getEntrada());
			form.setDescripcion(((DetalleRelacionCUFormImpl)valor).getDescripcion());
			form.setComboIdiomaLabelList(((DetalleRelacionCUFormImpl)valor).getComboIdiomaLabelList());
			form.setComboIdiomaValueList(((DetalleRelacionCUFormImpl)valor).getComboIdiomaValueList());
		}else if(valor instanceof DetalleRelacionFormSubmitFormFormImpl){
//			form.setTipo(((DetalleRelacionFormSubmitFormFormImpl)valor).getTipo());
			form.setTipoLabelList(((DetalleRelacionFormSubmitFormFormImpl)valor).getTipoLabelList());
			form.setTipoValueList(((DetalleRelacionFormSubmitFormFormImpl)valor).getTipoValueList());
			form.setCatalogo(((DetalleRelacionFormSubmitFormFormImpl)valor).getCatalogo());
			form.setEntrada(((DetalleRelacionFormSubmitFormFormImpl)valor).getEntrada());
			form.setDescripcion(((DetalleRelacionFormSubmitFormFormImpl)valor).getDescripcion());
			form.setComboIdiomaLabelList(((DetalleRelacionFormSubmitFormFormImpl)valor).getComboIdiomaLabelList());
			form.setComboIdiomaValueList(((DetalleRelacionFormSubmitFormFormImpl)valor).getComboIdiomaValueList());
		}else if(valor instanceof RelacionValidaVolverFormImpl){
//			form.setTipo(((RelacionValidaVolverFormImpl)valor).getTipo());
			form.setTipoLabelList(((RelacionValidaVolverFormImpl)valor).getTipoLabelList());
			form.setTipoValueList(((RelacionValidaVolverFormImpl)valor).getTipoValueList());
			form.setCatalogo(((RelacionValidaVolverFormImpl)valor).getCatalogo());
			form.setEntrada(((RelacionValidaVolverFormImpl)valor).getEntrada());
			form.setDescripcion(((RelacionValidaVolverFormImpl)valor).getDescripcion());
			form.setComboIdiomaLabelList(((RelacionValidaVolverFormImpl)valor).getComboIdiomaLabelList());
			form.setComboIdiomaValueList(((RelacionValidaVolverFormImpl)valor).getComboIdiomaValueList());
		}else if(valor instanceof RelacionNoValidaVolverFormImpl){
//			form.setTipo(((RelacionNoValidaVolverFormImpl)valor).getTipo());
			form.setTipoLabelList(((RelacionNoValidaVolverFormImpl)valor).getTipoLabelList());
			form.setTipoValueList(((RelacionNoValidaVolverFormImpl)valor).getTipoValueList());
			form.setCatalogo(((RelacionNoValidaVolverFormImpl)valor).getCatalogo());
			form.setEntrada(((RelacionNoValidaVolverFormImpl)valor).getEntrada());
			form.setDescripcion(((RelacionNoValidaVolverFormImpl)valor).getDescripcion());
			form.setComboIdiomaLabelList(((RelacionNoValidaVolverFormImpl)valor).getComboIdiomaLabelList());
			form.setComboIdiomaValueList(((RelacionNoValidaVolverFormImpl)valor).getComboIdiomaValueList());
		}
		
	
		int[] longitudTextosDesc = new int[form.getDescripcion().size()];
		for(int i= 0;i< form.getDescripcion().size();i++){
			longitudTextosDesc[i]=((DescripcionVO)(form.getDescripcionAsArray()[i])).getTextos().length;
		}

		//borramos session 
		request.getSession().removeAttribute(RelacionSession.SESSION_KEY);
		cambioFormulario(request,longitudTextosDesc,null);
		//recuperams session
		RelacionSession relacionSession = this.getRelacionSession(request);
		
		form.setTipo(relacionSession.getTipo().getValor());
		form.setCatalogo(relacionSession.getCatalogo());
		form.setEntrada(relacionSession.getEntrada());
		DescripcionVO[] lDescripciones = (DescripcionVO[])relacionSession.getDescripciones()
			.toArray(new DescripcionVO[relacionSession.getDescripciones().size()]);
		form.setDescripcionAsArray(lDescripciones);
		
		 //posicion de contenido a eliminar   
        String posicion = request.getAttribute("posicion").toString();//Descripcion
        int posicionInt = Integer.parseInt(posicion);
		
		//posicion de la descripcion de la que se quiere eliminar parte de su contenido
        String posicionExt = request.getAttribute("posicionExterior").toString();
        int posicionExtInt = Integer.parseInt(posicionExt);//LangStringvo[]
    	
    	DescripcionVO[] aDescripciones = (DescripcionVO[]) form.getDescripcionAsArray();
    	LangStringVO[] contenido = lDescripciones[posicionExtInt].getTextos();
    	LangStringVO[] nuevoContenido = new LangStringVO[contenido.length -1];
    	//añado al nuevo array de contenidos todos los contenidos menos el que 
    	//queremos eliminar
    	for (int i = 0; i<nuevoContenido.length;i++){
    		if (i < posicionInt)
    			nuevoContenido[i] = contenido[i];
    		else
    			nuevoContenido[i] = contenido[i+1]; 
    	}
    	aDescripciones[posicionExtInt].setTextos(nuevoContenido);
    	form.setDescripcionAsArray(aDescripciones);
    	//actualizamos session
    	relacionSession.setDescripciones(Arrays.asList(aDescripciones));
    	
    	//ayuda contextual
		cargarAyuda(relacionSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
		
       
		}catch (IndexOutOfBoundsException ai) {
			logger
			.warn("Warning en catalogadorWeb, categoría DetalleRelacion, metodo eliminarContDescripcion" + ai);
		}catch (Exception e) {
			logger
			.error("Error en Servicio de catalogacionWeb, categoría DetalleRelacion, metodo eliminarContDescripcion " + e);
			//throw new Exception("detalle.relacion.cu",e);
			saveErrorMessage(request, " Error en Servicio de catalogacionWeb, categoría DetalleRelacion, metodo eliminarContDescripcion ");
		}
}







    /**
     * @see es.pode.catalogadorWeb.presentacion.categoriasAvanzado.relacion.detalleRelacion.DetalleRelacionController#eliminarDescripcion(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.relacion.detalleRelacion.EliminarDescripcionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void eliminarDescripcion(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.relacion.detalleRelacion.EliminarDescripcionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
		
		Object valor = request.getSession().getAttribute("form");
		
		if (valor instanceof DetalleRelacionCUFormImpl) {
//			form.setTipo(((DetalleRelacionCUFormImpl)valor).getTipo());
			form.setTipoLabelList(((DetalleRelacionCUFormImpl)valor).getTipoLabelList());
			form.setTipoValueList(((DetalleRelacionCUFormImpl)valor).getTipoValueList());
			form.setCatalogo(((DetalleRelacionCUFormImpl)valor).getCatalogo());
			form.setEntrada(((DetalleRelacionCUFormImpl)valor).getEntrada());
			form.setDescripcion(((DetalleRelacionCUFormImpl)valor).getDescripcion());
			form.setComboIdiomaLabelList(((DetalleRelacionCUFormImpl)valor).getComboIdiomaLabelList());
			form.setComboIdiomaValueList(((DetalleRelacionCUFormImpl)valor).getComboIdiomaValueList());
		}else if(valor instanceof DetalleRelacionFormSubmitFormFormImpl){
//			form.setTipo(((DetalleRelacionFormSubmitFormFormImpl)valor).getTipo());
			form.setTipoLabelList(((DetalleRelacionFormSubmitFormFormImpl)valor).getTipoLabelList());
			form.setTipoValueList(((DetalleRelacionFormSubmitFormFormImpl)valor).getTipoValueList());
			form.setCatalogo(((DetalleRelacionFormSubmitFormFormImpl)valor).getCatalogo());
			form.setEntrada(((DetalleRelacionFormSubmitFormFormImpl)valor).getEntrada());
			form.setDescripcion(((DetalleRelacionFormSubmitFormFormImpl)valor).getDescripcion());
			form.setComboIdiomaLabelList(((DetalleRelacionFormSubmitFormFormImpl)valor).getComboIdiomaLabelList());
			form.setComboIdiomaValueList(((DetalleRelacionFormSubmitFormFormImpl)valor).getComboIdiomaValueList());
		}else if(valor instanceof RelacionValidaVolverFormImpl){
//			form.setTipo(((RelacionValidaVolverFormImpl)valor).getTipo());
			form.setTipoLabelList(((RelacionValidaVolverFormImpl)valor).getTipoLabelList());
			form.setTipoValueList(((RelacionValidaVolverFormImpl)valor).getTipoValueList());
			form.setCatalogo(((RelacionValidaVolverFormImpl)valor).getCatalogo());
			form.setEntrada(((RelacionValidaVolverFormImpl)valor).getEntrada());
			form.setDescripcion(((RelacionValidaVolverFormImpl)valor).getDescripcion());
			form.setComboIdiomaLabelList(((RelacionValidaVolverFormImpl)valor).getComboIdiomaLabelList());
			form.setComboIdiomaValueList(((RelacionValidaVolverFormImpl)valor).getComboIdiomaValueList());
		}else if(valor instanceof RelacionNoValidaVolverFormImpl){
//			form.setTipo(((RelacionNoValidaVolverFormImpl)valor).getTipo());
			form.setTipoLabelList(((RelacionNoValidaVolverFormImpl)valor).getTipoLabelList());
			form.setTipoValueList(((RelacionNoValidaVolverFormImpl)valor).getTipoValueList());
			form.setCatalogo(((RelacionNoValidaVolverFormImpl)valor).getCatalogo());
			form.setEntrada(((RelacionNoValidaVolverFormImpl)valor).getEntrada());
			form.setDescripcion(((RelacionNoValidaVolverFormImpl)valor).getDescripcion());
			form.setComboIdiomaLabelList(((RelacionNoValidaVolverFormImpl)valor).getComboIdiomaLabelList());
			form.setComboIdiomaValueList(((RelacionNoValidaVolverFormImpl)valor).getComboIdiomaValueList());
		}
		
	
		int[] longitudTextosDesc = new int[form.getDescripcion().size()];
		for(int i= 0;i< form.getDescripcion().size();i++){
			longitudTextosDesc[i]=((DescripcionVO)(form.getDescripcionAsArray()[i])).getTextos().length;
		}

		//borramos session 
		request.getSession().removeAttribute(RelacionSession.SESSION_KEY);
		cambioFormulario(request,longitudTextosDesc,null);
		//recuperams session
		RelacionSession relacionSession = this.getRelacionSession(request);
		
		form.setTipo(relacionSession.getTipo().getValor());
		form.setCatalogo(relacionSession.getCatalogo());
		form.setEntrada(relacionSession.getEntrada());
		DescripcionVO[] lDescripciones = (DescripcionVO[])relacionSession.getDescripciones()
			.toArray(new DescripcionVO[relacionSession.getDescripciones().size()]);
		form.setDescripcionAsArray(lDescripciones);
		
//		posicion del identificador que se quiere eliminar   
        String posicion = request.getAttribute("posicion").toString();//Descripcion
        int posicionInt = Integer.parseInt(posicion);
    	DescripcionVO[] aDescripciones = (DescripcionVO[]) form.getDescripcionAsArray();
    	DescripcionVO[] nuevoDescripciones = new DescripcionVO[aDescripciones.length -1];
    	//añado al nuevo array de descripciones todas las descripciones menos la que 
    	//queremos eliminar
    	for (int i = 0; i<nuevoDescripciones.length;i++){
    		if (i < posicionInt)
    			nuevoDescripciones[i] = aDescripciones[i];
    		else
    			nuevoDescripciones[i] = aDescripciones[i+1]; 
    	}
    	form.setDescripcionAsArray(nuevoDescripciones);
    	//actualizamos el objeto de session
    	relacionSession.setDescripciones(Arrays.asList(nuevoDescripciones));
    	
    	//ayuda contextual
		cargarAyuda(relacionSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
    	
		}catch (IndexOutOfBoundsException ai) {
			logger
			.warn("Warning en catalogadorWeb, categoría DetalleRelacion, metodo eliminarDescripcion" + ai);
		}catch (Exception e) {
			logger
			.error("Error en Servicio de catalogacionWeb, categoría DetalleRelacion, metodo eliminarContDescripcion " + e);
			//throw new Exception("detalle.relacion.cu",e);
			saveErrorMessage(request, " Error en Servicio de catalogacionWeb, categoría DetalleRelacion, metodo eliminarContDescripcion ");
		}
     }





	public void cancelar(ActionMapping mapping, CancelarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
	}




	public void cargarRelacionValidar(ActionMapping mapping, CargarRelacionValidarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		InputStream is = null;
    	Properties prop = new Properties();
		try{
			is = this.getClass().getResourceAsStream("/catalogadorAvanzado.properties");
			prop.load(is);
			String idiomaLdap=((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
			
			String[] l_id = {prop.getProperty("kind"), prop.getProperty("idiomaDestinatario")}; //es el idioma pero sin "ninguno"
			VocabularioVO[] vocaCombos = this.getSrvVocabulariosControladosService().obtenerCombos(l_id,idiomaLdap);
			UtilidadesOrdenarCombos vocabulariosOrdDest2 = new UtilidadesOrdenarCombos();
			VocabularioVO[] vocabularioOrdenado = vocabulariosOrdDest2.ordenarVocabulariosVO(vocaCombos);		
	
			int longVocabulario = vocabularioOrdenado.length;//Cargamos los combos de idioma, la estructura y el nivel de agregacion
			
			
			Collection idTipo = new ArrayList();
			Collection idDest=new ArrayList();
			
			for (int x = 0; x < longVocabulario; x++) {
				TerminoVO terminoVO = new TerminoVO();
				terminoVO.setIdiomaTermino("");
				terminoVO.setIdTermino("");
				terminoVO.setNomTermino("");
	
				switch (x) {
	
				case 0:
					
					idTipo.add(terminoVO);
	//				modificamos las cadenas de idiomas
					TerminoVO[] terminosTipo = vocabularioOrdenado[x].getTerminos();
					for (int li=0; li<terminosTipo.length;li++){
						TerminoVO idAux = new TerminoVO();
						idAux=terminosTipo[li];
						idAux.setNomTermino(terminosTipo[li].getNomTermino());
					}
					UtilidadesOrdenarCombos terminosOrdDest3 = new UtilidadesOrdenarCombos();
					TerminoVO[] terminosOrdTipo = terminosOrdDest3.ordenarTerminosVO(terminosTipo, idiomaLdap);				
					Collection idTipo2 = Arrays.asList(terminosOrdTipo);
					
					Iterator zTipo = idTipo2.iterator();
					while (zTipo.hasNext()) {
						idTipo.add(zTipo.next());
					}
					form.setTipoBackingList(idTipo, "idTermino", "nomTermino");
					
					break;
				
				case 1:
					idDest.add(terminoVO);
	//				modificamos las cadenas de idiomas
					TerminoVO[] terminosDest = vocabularioOrdenado[x].getTerminos();
					for (int li=0; li<terminosDest.length;li++){
						TerminoVO idAux = new TerminoVO();
						idAux=terminosDest[li];
						String textoIdioma= idAux.getNomTermino();
						String idiomasTra=I18n.getInstance().obtenerIdiomaTraducido(textoIdioma, idiomaLdap);
						idAux.setNomTermino(idiomasTra);
					}
					UtilidadesOrdenarCombos terminosOrdDest4 = new UtilidadesOrdenarCombos();
					TerminoVO[] terminosOrdDest = terminosOrdDest4.ordenarTerminosVO(terminosDest, idiomaLdap);				
					Collection idDest2 = Arrays.asList(terminosOrdDest);
					
					Iterator zDest = idDest2.iterator();
					while (zDest.hasNext()) {
						idDest.add(zDest.next());
					}
					form.setComboIdiomaBackingList(idDest, "idTermino", "nomTermino");
					
					break;
				}
				
				
				if( logger.isDebugEnabled() ){
	
	                logger.debug("Cargados los combos del formulario" );
				}
	
			}
			//recogemos el obj de session
			RelacionSession relacionSession = this.getRelacionSession(request);
			form.setTipo(relacionSession.getTipo().getValor());
			form.setCatalogo(relacionSession.getCatalogo());
			form.setEntrada(relacionSession.getEntrada());
			DescripcionVO[] lDescripciones = (DescripcionVO[])relacionSession.getDescripciones()
				.toArray(new DescripcionVO[relacionSession.getDescripciones().size()]);
			form.setDescripcionAsArray(lDescripciones);
			
			//ayuda contextual
    		cargarAyuda(relacionSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
		
		}catch (Exception e){
			logger.error("Error en catalogadorWeb, categoría DetalleRelación, metodo cargarRelacionValidar " + e);
			//throw new java.lang.Exception("detalle.relacion.cu", e);
			saveErrorMessage(request, " Error en catalogadorWeb, categoría DetalleRelación, metodo cargarRelacionValidar ");
		}
		
	}	
  	
	private void cambioFormulario(HttpServletRequest request,int[] longitudTextosDesc,Object formRequestSession) throws IOException{

		try {
			String source = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES);// esquemaDeMetadatos
		
		DescripcionVO[] lDescripciones = new DescripcionVO[longitudTextosDesc.length];
	  	String tipoTexto="";


	  	ArrayList[] textoDescripciones = new ArrayList[longitudTextosDesc.length];
      	ArrayList[] idiomaDescripciones = new ArrayList[longitudTextosDesc.length];
      	
      	RelacionSession relacionSession = this.getRelacionSession(request);
      	
      	for (Enumeration names = request.getParameterNames(); names.hasMoreElements();)
      	{
      		String name = String.valueOf(names.nextElement());
      	   //DesFecTex DesFecIdio Des DesIdio
      		if (name.startsWith("Des")){//descripciones       	 
	          	 String[] namePartido = name.split("_");
	          	 int i = Integer.parseInt(namePartido[0].substring(3, namePartido[0].length()));
	          	 if (namePartido[1].startsWith("Tex")){
	          		 int j = Integer.parseInt(namePartido[1].substring(3, namePartido[1].length()));
	          		 ArrayList lDesc = textoDescripciones[i];
	          		 if(lDesc == null){
	          			 lDesc= new ArrayList();
	          			 for (int k=0;k<longitudTextosDesc[i];k++)
	          				 lDesc.add("");
	          		 }
	          		 
	          		 lDesc.set(j, request.getParameter(name));
	          		 textoDescripciones[i]=lDesc;
	          	 }
	          	 else{//Idio
	          		 int j = Integer.parseInt(namePartido[1].substring(4, namePartido[1].length()));
	          		 ArrayList lDesc = idiomaDescripciones[i];
	          		 if(lDesc == null){
	          			 lDesc= new ArrayList();
	          			 for (int k=0;k<longitudTextosDesc[i];k++)
	          				 lDesc.add("");
	          		 }
	          		 
	          		 lDesc.set(j, request.getParameter(name));
	          		 idiomaDescripciones[i]=lDesc;
	          	 } 	 
	           
		  	}else if (name.startsWith("tip")) {
		  		tipoTexto=request.getParameter(name);
		  	}else if (name.startsWith("Cat")) {
		  		String catalogo=request.getParameter(name);
		  		//metemos en session
		  		relacionSession.setCatalogo(catalogo);
		  	}else if (name.startsWith("Ent")) {
		  		String entrada=request.getParameter(name);
		  		//metemos en Session
		  		relacionSession.setEntrada(entrada);
		  	} 
       }
      	
      	//tipo
	      
	      	 SourceValueVO tipoRecrusoVO = new SourceValueVO();
	      	 tipoRecrusoVO.setValor(tipoTexto);
	      	 tipoRecrusoVO.setSource(source);
	      	 //tipo = tipoRecrusoVO;
	      	 //metemos en session el tipo
	      	 relacionSession.setTipo(tipoRecrusoVO);
      	
       //descripcion
//       DescripcionVO[] descVO = new DescripcionVO[textoDescripciones.length];
       
       for (int i = 0; i<textoDescripciones.length;i++){
    	   
    	   DescripcionVO descr=new DescripcionVO();
    	   if (textoDescripciones[i]!=null) {
    	   LangStringVO[] aLangDescripciones = new LangStringVO[textoDescripciones[i].size()];
    	   for(int j=0;j<textoDescripciones[i].size();j++){
    		   String texto=(textoDescripciones[i].get(j)!=null?textoDescripciones[i].get(j):"").toString();
    		   String idioma=(String)idiomaDescripciones[i].get(j);
    		   LangStringVO langDescripciones= new LangStringVO();
    		   langDescripciones.setTexto(texto.trim());
    		   langDescripciones.setIdioma(idioma);
    		   aLangDescripciones[j] = langDescripciones;
    	   }
    	   
    	   descr.setTextos(aLangDescripciones);
    	   }
    	   else{
    	  		 LangStringVO[] aLangString=new LangStringVO[1];
      	  		 LangStringVO langString = new LangStringVO();
      	  		 langString.setIdioma("");
      	  		 langString.setTexto("");
      	  		 aLangString[0]= langString;
      	  		 descr.setTextos(aLangString);
    	   }
    	   lDescripciones[i]=descr;
       }
       //metemos descripciones en session
       relacionSession.setDescripciones(Arrays.asList(lDescripciones));
		} 
		catch (Exception e) {
			logger.warn("Se ha producido un fallo al acceder al servicio de variables");
		}
	}
	
	
private void dameTerminoId(RelacionSession relacionSession) throws Exception{
	
//	Tipo
	ArrayList ids = new ArrayList();
	if(relacionSession.getTipo()!=null){
	    ids.add(relacionSession.getTipo().getValor());
	    String[] arrayIds = (String[])ids.toArray(new String[ids.size()]);
	    
	    TerminoYPadreVO[] terminosTraduc = this.getSrvVocabulariosControladosService().crearTraduccionAIngles(arrayIds);
	    if((terminosTraduc.length>0)&&(terminosTraduc[0].getIdVocabulario().equals("7.1"))){
	    	String texTipo=relacionSession.getTipo().getValor();
		    if (!texTipo.equals("")){
		    	texTipo=terminosTraduc[0].getNomTermino();
		    }
		    relacionSession.getTipo().setValor(texTipo);
	    }
	}
		
	 ArrayList idsDesc = new ArrayList();
	 DescripcionVO[] lDescripciones = (DescripcionVO[])relacionSession.getDescripciones()
			.toArray(new DescripcionVO[relacionSession.getDescripciones().size()]);
	    for ( int i=0;i<lDescripciones.length;i++ ){
	    	LangStringVO[] langDescripcion = lDescripciones[i].getTextos();
	    	for ( int j=0;j<langDescripcion.length;j++){
	    		idsDesc.clear();
		    	idsDesc.add(langDescripcion[j].getIdioma());
		    	
		        String[] arrayIdsDesc = (String[])idsDesc.toArray(new String[idsDesc.size()]);
		        TerminoYPadreVO[] terminosTraducDesc = this.getSrvVocabulariosControladosService().crearTraduccionAIngles(arrayIdsDesc);
		        // para cada elemento te va a dev un Terminoypadrevo		       
		        for (int cont=0;cont<terminosTraducDesc.length;cont++){//Cambiado el identificador del termino al nombre del termino en ingles
		        	//para asegurarnos de que modifica el correcto!!
		        	if (langDescripcion[j].getIdioma().equals(terminosTraducDesc[cont].getIdTermino())) {
		        		langDescripcion[j].setIdioma(terminosTraducDesc[cont].getNomTermino());
		        	}
		        }
	        }
	        lDescripciones[i].setTextos(langDescripcion);
	    }
	    //actualizamos la session
	    relacionSession.setDescripciones(Arrays.asList(lDescripciones));
}
       
//private String obtenerAccion(HttpServletRequest request) 
//throws Exception
//{
//	String accion="";
//	String[] partes;
//	for (Enumeration names = request.getParameterNames();accion.equals("") && names.hasMoreElements();)
//       {
//      	 String name = String.valueOf(names.nextElement());
//           if(name.startsWith("accion"))
//           {
//        	   partes= name.split("_");
//	        	 if(partes.length>0){
//	        		 String anadidos=partes[1];
//	        		 for(int i=2;i<partes.length;i++){
//	        			 anadidos=anadidos+"_"+partes[i];
//	        		 }
//	        	 accion=anadidos;
//	        	 }
//        	}
//	
//        }
//	return accion;
//}







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
		
		if (valor instanceof DetalleRelacionCUFormImpl) {
			DetalleRelacionCUFormImpl formulario = (DetalleRelacionCUFormImpl) valor;
			form.setTipoLabelList(formulario.getTipoLabelList());
			form.setTipoValueList(formulario.getTipoValueList());
			form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
			form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
			form.setTituloRelacion(formulario.getTituloRelacion());
		}else if(valor instanceof DetalleRelacionFormSubmitFormFormImpl){
			DetalleRelacionFormSubmitFormFormImpl formulario = (DetalleRelacionFormSubmitFormFormImpl) valor;
			form.setTipoLabelList(formulario.getTipoLabelList());
			form.setTipoValueList(formulario.getTipoValueList());
			form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
			form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
			form.setTituloRelacion(formulario.getTituloRelacion());
		}else if(valor instanceof RelacionValidaVolverFormImpl){
			RelacionValidaVolverFormImpl formulario = (RelacionValidaVolverFormImpl) valor;
			form.setTipoLabelList(formulario.getTipoLabelList());
			form.setTipoValueList(formulario.getTipoValueList());
			form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
			form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
			form.setTituloRelacion(formulario.getTituloRelacion());
		}else if(valor instanceof RelacionNoValidaVolverFormImpl){
			RelacionNoValidaVolverFormImpl formulario = (RelacionNoValidaVolverFormImpl) valor;
			form.setTipoLabelList(formulario.getTipoLabelList());
			form.setTipoValueList(formulario.getTipoValueList());
			form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
			form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
			form.setTituloRelacion(formulario.getTituloRelacion());
		}
		
		//TIPO
		form.setTipo("");
		//RECURSO
		form.setCatalogo("");
		form.setEntrada("");
		//DESCRIPCION
		DescripcionVO[] nuevoDescripcionVO = new DescripcionVO[1];
		LangStringVO[] aLangStringD =new LangStringVO[1];
		LangStringVO langD = new LangStringVO();
		langD.setIdioma("");
		langD.setTexto("");
		aLangStringD[0]=langD;
		DescripcionVO descVO= new DescripcionVO();
		descVO.setTextos(aLangStringD);
		nuevoDescripcionVO[0] = descVO;
		form.setDescripcionAsArray(nuevoDescripcionVO);
		
		RelacionSession relacionSession = this.getRelacionSession(request);
		//ayuda contextual
		cargarAyuda(relacionSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));

	}catch (Exception e){
		logger.error("Error en catalogadorWeb, categoría DetalleRelación, metodo reset " + e);
		//throw new java.lang.Exception("detalle.relacion.cu", e);
		saveErrorMessage(request, " Error en catalogadorWeb, categoría DetalleRelación, metodo reset ");
	}
}



public boolean hayRelaciones(
		ActionMapping mapping, 
		HayRelacionesForm form, 
		HttpServletRequest request, 
		HttpServletResponse response) 
throws Exception 
{
	boolean result = false;
	CatalogadorAvSession catalogadorAvSession=this.getCatalogadorAvSession(request);
	try {
    	if (catalogadorAvSession.getMDSesion()!=null) {
    		AvRelationVO[] relaciones =catalogadorAvSession.getMDSesion().getRelaciones();
    		if (relaciones == null || relaciones.length == 0 ){
    			result = false;
    		}
    		else 
    			result = true;
   
    	}
	}catch (Exception e) {
		logger.error("Error AnotacionController, método hayRelaciones "+ e);
		//throw new java.lang.Exception("relacion", e);
		saveErrorMessage(request, " Error AnotacionController, método hayRelaciones ");
	}
	return result;
}





public boolean hayRelacionesGuardar(
		ActionMapping mapping,
		HayRelacionesGuardarForm form,
		HttpServletRequest request,
		HttpServletResponse response)
throws Exception 
{
	boolean result = false;
	CatalogadorAvSession catalogadorAvSession=this.getCatalogadorAvSession(request);
	try {
    	if (catalogadorAvSession.getMDSesion()!=null) {
    		AvRelationVO[] relaciones =catalogadorAvSession.getMDSesion().getRelaciones();
    		if (relaciones == null || relaciones.length == 0 ){
    			result = false;
    		}
    		else 
    			result = true;
   
    	}
	}catch (Exception e) {
		logger.error("Error AnotacionController, método hayRelacionesGuardar "+ e);
		//throw new java.lang.Exception("relacion", e);
		saveErrorMessage(request, " Error AnotacionController, método hayRelacionesGuardar ");
	}
	return result;
}

//metodo que devuelve la ayuda contextual	
private void cargarAyuda(RelacionSession rel, Locale idioma) {	
	
	ResourceBundle datosResources = I18n.getInstance().getResource("application-resources", idioma);
	//resultado = prop.getProperty(salidaAyuda);
	rel.setAyudaDescripcion(datosResources.getString("cat.ayuda.relacion.descripcion"));
	rel.setAyudaIdentificador(datosResources.getString("cat.ayuda.relacion.recurso.id"));
	rel.setAyudaRecurso(datosResources.getString("cat.ayuda.relacion.recurso"));
	rel.setAyudaTipo(datosResources.getString("cat.ayuda.relacion.tipo"));
	
	logger.debug("GENERAL CONTROLLER, CARGADA AYUDA CONTEXTUAL idioma " + idioma);
   
}  

}