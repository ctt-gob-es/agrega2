// license-header java merge-point
package es.pode.catalogadorWeb.presentacion.categoriasAvanzado.usoeducativo.detalleUsoEducativo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
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
import es.pode.catalogacion.negocio.servicios.AvEducationalVO;
import es.pode.catalogacion.negocio.servicios.DescripcionVO;
import es.pode.catalogacion.negocio.servicios.DuracionVO;
import es.pode.catalogacion.negocio.servicios.IdiomaVO;
import es.pode.catalogacion.negocio.servicios.LangStringVO;
import es.pode.catalogacion.negocio.servicios.LomAvanzadoVO;
import es.pode.catalogacion.negocio.servicios.RangoEdadVO;
import es.pode.catalogacion.negocio.servicios.SourceValueVO;
import es.pode.catalogacion.soporte.DescripcionUsoEdu;
import es.pode.catalogacion.soporte.UtilidadesOrdenarCombos;
import es.pode.catalogadorWeb.presentacion.CatalogadorAvSession;
import es.pode.catalogadorWeb.presentacion.UsoEducativoSession;
import es.pode.fuentestaxonomicas.negocio.servicio.TerminoVO;
import es.pode.fuentestaxonomicas.negocio.servicio.TerminoYPadreVO;
import es.pode.fuentestaxonomicas.negocio.servicio.VocabularioVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.catalogadorWeb.presentacion.categoriasAvanzado.usoeducativo.detalleUsoEducativo.DetalleUsoEduController
 */
public class DetalleUsoEduControllerImpl extends DetalleUsoEduController
{ 

	protected static Logger logger = Logger.getLogger(DetalleUsoEduControllerImpl.class); 
	
  	private int iEdu;
	
  	Map<String, String> listaIdiomas;
  	
    public final void cargarDetalleUsoEdu(
    		ActionMapping mapping, 
    		CargarDetalleUsoEduForm form, 
    		HttpServletRequest request, 
    		HttpServletResponse response)
    throws Exception
    {
		InputStream is = null;
    	Properties prop = new Properties();
        try{
		
        	int longVocabulario = 0;
        	CatalogadorAvSession catalogadorAvSession=this.getCatalogadorAvSession(request);
        	
			String idiomaLdap=((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();

	        is = this.getClass().getResourceAsStream("/catalogadorAvanzado.properties");
			prop.load(is);
			
			String source=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES);//esquemaDeMetadatos
	        
			
			//comprobamos que el objeto de sesion no se ha null, si es null nos creamos uno	
			if (catalogadorAvSession.getMDSesion() == null){
				LomAvanzadoVO mdSession = new LomAvanzadoVO();
				catalogadorAvSession.setMDSesion(mdSession);
			} 
			
			
			String botonPulsado = form.getBotonPulsado();

			//Carga del formulario (ahora desde el objeto de session)(Los combos y lo que debe pintarse)
			String[] l_id = { prop.getProperty("idiomaDestinatario"),prop.getProperty("tipoRecursoAprendizaje"),prop.getProperty("rolEdu"),
							prop.getProperty("contexto"),prop.getProperty("procesoCognitivo"),prop.getProperty("tipoInteractividad"),
							prop.getProperty("nivelInteractividad"),prop.getProperty("densidadSemantica"),prop.getProperty("dificultad"),
							prop.getProperty("usoedu.tipoConocimiento")};
			VocabularioVO[] vocabulario = this.getSrvVocabulariosControladosService().obtenerCombos(l_id,idiomaLdap);
			//guardo una lista de los idiomas con sus correspondientes codigos
			listaIdiomas = new HashMap<String, String>();
			for (int i = 0; i < vocabulario[0].getTerminos().length; i++) {
				listaIdiomas.put(vocabulario[0].getTerminos()[i].getIdTermino(), vocabulario[0].getTerminos()[i].getNomTermino());
			}

			
			//vamos a ordenar vocabulario
			UtilidadesOrdenarCombos vocabulariosOrdDest1 = new UtilidadesOrdenarCombos();
			VocabularioVO[] vocabularioOrdenado = vocabulariosOrdDest1.ordenarVocabulariosVO(vocabulario);			

			//Cargamos los combos
			longVocabulario = vocabularioOrdenado.length;
	

			
			for (int x = 0; x < longVocabulario; x++) {
				TerminoVO terminoVO = new TerminoVO();
				terminoVO.setIdiomaTermino("");
				terminoVO.setIdTermino("");
				terminoVO.setNomTermino("");
				
				switch (x) {
				case 0://IDIOMA DESTINATARIO
					Collection<TerminoVO> id = new ArrayList<TerminoVO>();
					
					id.add(terminoVO);
//					modificamos las cadenas de idiomas
					TerminoVO[] terminos = vocabularioOrdenado[x].getTerminos();
					for (int li=0; li<terminos.length;li++){
						TerminoVO idAux = new TerminoVO();
						idAux=terminos[li];
						String textoIdioma= idAux.getNomTermino();
						String idiomasTra=I18n.getInstance().obtenerIdiomaTraducido(textoIdioma, idiomaLdap);
						idAux.setNomTermino(idiomasTra);
					}
					UtilidadesOrdenarCombos terminosOrdDest1 = new UtilidadesOrdenarCombos();
					TerminoVO[] terminosOrd = terminosOrdDest1.ordenarTerminosVO(terminos, idiomaLdap);					
					Collection<TerminoVO> id2 = Arrays.asList(terminosOrd);
					
					Iterator<TerminoVO> z = id2.iterator();
					while (z.hasNext()) {
						id.add(z.next());
					}

					form.setIdiomasBackingList(id, "idTermino", "nomTermino");//combo idiomas
					form.setComboIdiomaBackingList(id, "idTermino", "nomTermino");//combo idioma de lo campos de texto
					break;
					
				case 1://TIPO RECURSO EDUCATIVO
					Collection<TerminoVO> cTipoRecurso = new ArrayList<TerminoVO>();
					cTipoRecurso.add(terminoVO);
					Collection<TerminoVO> cTipoRecursoAux = Arrays.asList(vocabularioOrdenado[x].getTerminos());
					Iterator<TerminoVO> cont2 = cTipoRecursoAux.iterator();
					while (cont2.hasNext()) {
						cTipoRecurso.add(cont2.next());
					}
					form.setTipoRecursoBackingList(cTipoRecurso, "idTermino", "nomTermino");
					break;
				case 2://DESTINATARIO O ROL DEL USUARIO FINAL
					Collection<TerminoVO> cDestinatrio = new ArrayList<TerminoVO>();
					cDestinatrio.add(terminoVO);
					Collection<TerminoVO> cDesctinatarioAux = Arrays.asList(vocabularioOrdenado[x].getTerminos());
					Iterator<TerminoVO> contR = cDesctinatarioAux.iterator();
					while (contR.hasNext()) {
						cDestinatrio.add(contR.next());
					}
					form.setRolUsuarioBackingList(cDestinatrio, "idTermino", "nomTermino");
					break;
				case 3://CONTEXTO
					Collection<TerminoVO> cContexto = new ArrayList<TerminoVO>();
					cContexto.add(terminoVO);
					Collection<TerminoVO> cContextoAux = Arrays.asList(vocabularioOrdenado[x].getTerminos());
					Iterator<TerminoVO> contC = cContextoAux.iterator();
					while (contC.hasNext()) {
						cContexto.add(contC.next());
					}
					form.setComboContextoBackingList(cContexto, "idTermino", "nomTermino");
					break;
				case 4://PROCESO COGNITIVO 
					Collection<TerminoVO> cProcesoCog = new ArrayList<TerminoVO>();
					cProcesoCog.add(terminoVO);
					Collection<TerminoVO> cProcesoCogAux = Arrays.asList(vocabularioOrdenado[x].getTerminos());
					Iterator<TerminoVO> contP = cProcesoCogAux.iterator();
					while (contP.hasNext()) {
						cProcesoCog.add(contP.next());
					}
					form.setComboProcesoBackingList(cProcesoCog, "idTermino", "nomTermino");
					break;
				case 5://TIPO INTERACTIVIDAD
					Collection<TerminoVO> cTipoInteract = new ArrayList<TerminoVO>();
					cTipoInteract.add(terminoVO);
					Collection<TerminoVO> cTipoInteractAux = Arrays.asList(vocabularioOrdenado[x].getTerminos());
					Iterator<TerminoVO> cont = cTipoInteractAux.iterator();
					while (cont.hasNext()) {
						cTipoInteract.add(cont.next());
					}
					form.setTipoInteractividadBackingList(cTipoInteract, "idTermino", "nomTermino");
					break;

				case 6://NIVEL INTERACTIVIDAD
					Collection<TerminoVO> cNivelInteract = new ArrayList<TerminoVO>();
					cNivelInteract.add(terminoVO);
					Collection<TerminoVO> cNivelInteractAux = Arrays.asList(vocabularioOrdenado[x].getTerminos());
					Iterator<TerminoVO> contN = cNivelInteractAux.iterator();
					while (contN.hasNext()) {
						cNivelInteract.add(contN.next());
					}
					form.setNivelInteractividadBackingList(cNivelInteract, "idTermino", "nomTermino");		

					break;
				case 7://DENSIDAD SEMANTICA
					Collection<TerminoVO> cDensidad = new ArrayList<TerminoVO>();
					cDensidad.add(terminoVO);
					Collection<TerminoVO> cDensidadAux = Arrays.asList(vocabularioOrdenado[x].getTerminos());
					Iterator<TerminoVO> contD = cDensidadAux.iterator();
					while (contD.hasNext()) {
						cDensidad.add(contD.next());
					}
					form.setDensidadSemanticaBackingList(cDensidad, "idTermino", "nomTermino");
					break;
				case 8://DIFICULTAD
					Collection<TerminoVO> cDificultad= new ArrayList<TerminoVO>();
					cDificultad.add(terminoVO);
					Collection<TerminoVO> cDificultadAux = Arrays.asList(vocabularioOrdenado[x].getTerminos());
					Iterator<TerminoVO> contDif = cDificultadAux.iterator();
					while (contDif.hasNext()) {
						cDificultad.add(contDif.next());
					}
					form.setDificultadBackingList(cDificultad, "idTermino", "nomTermino");
					break;
				case 9://TIPO CONOCMIENTO
					Collection<TerminoVO> cTipoConocimiento = Arrays.asList(vocabularioOrdenado[x].getTerminos());
					form.setDescripcionTipoConocimiento(cTipoConocimiento);
					break;
				}
				
				
				if( logger.isDebugEnabled() ){

	                logger.debug("Cargados el combo de : " +  l_id[x]);
				}

			}
			logger.debug("Cargados los combos");
			
			I18n i18n= I18n.getInstance();
			
			String botonModificar=prop.getProperty("botonModificar")!=null?prop.getProperty("botonModificar"):"";
			//Caso Modificacion
			AvEducationalVO[] educaciones =catalogadorAvSession.getMDSesion().getEducational();
			if (botonModificar.equals(botonPulsado)) { //Modificacion
				TerminoYPadreVO terminoypadreVO = new TerminoYPadreVO();
				ArrayList<TerminoYPadreVO> terminoypadrear=new ArrayList<TerminoYPadreVO>();
			
				iEdu = Integer.parseInt(form.getTituloUsoEducativo()) - 1;
				//comprobamos que educational no se ha null, si es null nos creamos uno	
				boolean educacionEsNull = false;
				if (educaciones[iEdu] == null){
					AvEducationalVO educational = new AvEducationalVO();
					educaciones[iEdu] = educational;
					educacionEsNull=true;
				}
				
				for (int x = 0; x < longVocabulario; x++) {
					switch (x) {
					case 0://IDIOMA DESTINATARIO
						//recogemos los idiomas (idioma destinatario)
						if ((educaciones[iEdu] != null)&&(educaciones[iEdu].getIdiomas() !=null)) {
							IdiomaVO[] idiomasVO =educaciones[iEdu].getIdiomas();
							for(int j=0;j<idiomasVO.length;j++){
								terminoypadreVO = new TerminoYPadreVO();
								terminoypadreVO.setIdiomaTermino("en");
								terminoypadreVO.setIdVocabulario(l_id[x]);
								terminoypadreVO.setIdTermino("");
								terminoypadreVO.setNomTermino(idiomasVO[j].getTexto());
								terminoypadrear.add(terminoypadreVO);
							}
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
						
					case 1://TIPO RECURSO EDUCATIVO
						if ((educaciones[iEdu] != null)&&(educaciones[iEdu].getTiposrecursoedu()!=null)) {
							SourceValueVO[] tiposRecursoVO =educaciones[iEdu].getTiposrecursoedu();
							for(int j=0;j<tiposRecursoVO.length;j++){
								terminoypadreVO = new TerminoYPadreVO();
								terminoypadreVO.setIdiomaTermino("en");
								terminoypadreVO.setIdVocabulario(l_id[x]);
								terminoypadreVO.setIdTermino("");
								terminoypadreVO.setNomTermino(tiposRecursoVO[j].getValor());
								terminoypadrear.add(terminoypadreVO);
							}
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
					case 2://DESTINATARIO O ROL DEL USUARIO FINAL
						if ((educaciones[iEdu] != null)&&(educaciones[iEdu].getDestinatarios()!=null)) {
							SourceValueVO[] destinatariosVO =educaciones[iEdu].getDestinatarios();
							for(int j=0;j<destinatariosVO.length;j++){
								terminoypadreVO = new TerminoYPadreVO();
								terminoypadreVO.setIdiomaTermino("en");
								terminoypadreVO.setIdVocabulario(l_id[x]);
								terminoypadreVO.setIdTermino("");
								terminoypadreVO.setNomTermino(destinatariosVO[j].getValor());
								terminoypadrear.add(terminoypadreVO);
							}
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
					case 3://CONTEXTO
						if ((educaciones[iEdu] != null)&&(educaciones[iEdu].getContextos()!=null)) {
							SourceValueVO[] contextosVO =educaciones[iEdu].getContextos();
							for(int j=0;j<contextosVO.length;j++){
								terminoypadreVO = new TerminoYPadreVO();
								terminoypadreVO.setIdiomaTermino("en");
								terminoypadreVO.setIdVocabulario(l_id[x]);
								terminoypadreVO.setIdTermino("");
								terminoypadreVO.setNomTermino(contextosVO[j].getValor());
								terminoypadrear.add(terminoypadreVO);
							}
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
					case 4://PROCESO COGNITIVO 
						if ((educaciones[iEdu] != null)&&(educaciones[iEdu].getProcesoscognitivos()!=null)) {
							SourceValueVO[] procesosCogVO =educaciones[iEdu].getProcesoscognitivos();
							for(int j=0;j<procesosCogVO.length;j++){
								terminoypadreVO = new TerminoYPadreVO();
								terminoypadreVO.setIdiomaTermino("en");
								terminoypadreVO.setIdVocabulario(l_id[x]);
								terminoypadreVO.setIdTermino("");
								terminoypadreVO.setNomTermino(procesosCogVO[j].getValor());
								terminoypadrear.add(terminoypadreVO);
							}
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
					case 5://TIPO INTERACTIVIDAD
						terminoypadreVO = new TerminoYPadreVO();
						terminoypadreVO.setIdiomaTermino("en");
						terminoypadreVO.setIdVocabulario(l_id[x]);
						terminoypadreVO.setIdTermino("");
						if ((educaciones[iEdu] != null)&&(educaciones[iEdu].getTipoDeInteractividad() !=null)) {
							terminoypadreVO.setNomTermino(educaciones[iEdu].getTipoDeInteractividad().getValor());
							terminoypadrear.add(terminoypadreVO);
						} else {
							terminoypadreVO.setNomTermino("");//terminoYPadre rellenado con estructura desde el formulario
							terminoypadrear.add(terminoypadreVO);
						}
						
						break;

					case 6://NIVEL INTERACTIVIDAD	
						terminoypadreVO = new TerminoYPadreVO();
						terminoypadreVO.setIdiomaTermino("en");
						terminoypadreVO.setIdVocabulario(l_id[x]);
						terminoypadreVO.setIdTermino("");
						if ((educaciones[iEdu] != null)&&(educaciones[iEdu].getNivelInteractividad() !=null)) {
							terminoypadreVO.setNomTermino(educaciones[iEdu].getNivelInteractividad().getValor());
							terminoypadrear.add(terminoypadreVO);
						} else {
							terminoypadreVO.setNomTermino("");
							terminoypadrear.add(terminoypadreVO);
						}
						break;
					case 7://DENSIDAD SEMANTICA
						terminoypadreVO = new TerminoYPadreVO();
						terminoypadreVO.setIdiomaTermino("en");
						terminoypadreVO.setIdVocabulario(l_id[x]);
						terminoypadreVO.setIdTermino("");
						if ((educaciones[iEdu] != null)&&(educaciones[iEdu].getDensidadSemantica() !=null)) {
							terminoypadreVO.setNomTermino(educaciones[iEdu].getDensidadSemantica().getValor());
							terminoypadrear.add(terminoypadreVO);
						} else {
							terminoypadreVO.setNomTermino("");
							terminoypadrear.add(terminoypadreVO);
						}
						break;
					case 8://DIFICULTAD
						terminoypadreVO = new TerminoYPadreVO();
						terminoypadreVO.setIdiomaTermino("en");
						terminoypadreVO.setIdVocabulario(l_id[x]);
						terminoypadreVO.setIdTermino("");
						if ((educaciones[iEdu] != null)&&(educaciones[iEdu].getDificultad() !=null)) {
							terminoypadreVO.setNomTermino(educaciones[iEdu].getDificultad().getValor());
							terminoypadrear.add(terminoypadreVO);
						} else {
							terminoypadreVO.setNomTermino("");
							terminoypadrear.add(terminoypadreVO);
						}
						break;
					}// fin switch	
				}//fin for
				
				
				TerminoYPadreVO[] arrayTerminoYPadre=terminoypadrear.toArray(new TerminoYPadreVO[terminoypadrear.size()]);
				TerminoYPadreVO[] terminoYPadreVuelta = this.getSrvVocabulariosControladosService().obtenerIdTermino(arrayTerminoYPadre);
				//Dividimos el array
				ArrayList<TerminoYPadreVO> arrayIdioma=new ArrayList<TerminoYPadreVO>();
				ArrayList<TerminoYPadreVO> arrayTipoRecurso=new ArrayList<TerminoYPadreVO>();
				ArrayList<TerminoYPadreVO> arrayRol=new ArrayList<TerminoYPadreVO>();
				ArrayList<TerminoYPadreVO> arrayContexto=new ArrayList<TerminoYPadreVO>();
				ArrayList<TerminoYPadreVO> arrayProcesoCog=new ArrayList<TerminoYPadreVO>();
				ArrayList<TerminoYPadreVO> arrays=new ArrayList<TerminoYPadreVO>();
				for(int k=0;k<terminoYPadreVuelta.length;k++){
					String idPadre=terminoYPadreVuelta[k].getIdVocabulario();
					if(idPadre.equals(l_id[0])){//IDIOMA DESTINATARIO
						arrayIdioma.add(terminoYPadreVuelta[k]);
					}
					else if(idPadre.equals(l_id[1])){//TIPO RECURSO EDUCATIVO
						arrayTipoRecurso.add(terminoYPadreVuelta[k]);
					}
					else if(idPadre.equals(l_id[2])){//DESTINATARIO O ROL DEL USUARIO FINAL
						arrayRol.add(terminoYPadreVuelta[k]);
					}
					else if(idPadre.equals(l_id[3])){//CONTEXTO
						arrayContexto.add(terminoYPadreVuelta[k]);
					}
					else if(idPadre.equals(l_id[4])){//PROCESO COGNITIVO 
						arrayProcesoCog.add(terminoYPadreVuelta[k]);
					}
					else {//RESTO DE LOS COMBOS
						arrays.add(terminoYPadreVuelta[k]);
					}
				}
				TerminoYPadreVO[] idiomasTP=arrayIdioma.toArray(new TerminoYPadreVO[arrayIdioma.size()]);
				TerminoYPadreVO[] tipoRecursoTP=arrayTipoRecurso.toArray(new TerminoYPadreVO[arrayTipoRecurso.size()]);
				TerminoYPadreVO[] rolTP=arrayRol.toArray(new TerminoYPadreVO[arrayRol.size()]);
				TerminoYPadreVO[] contextoTP=arrayContexto.toArray(new TerminoYPadreVO[arrayContexto.size()]);
				TerminoYPadreVO[] procesoCogTP=arrayProcesoCog.toArray(new TerminoYPadreVO[arrayProcesoCog.size()]);
				TerminoYPadreVO[] restoCombos=arrays.toArray(new TerminoYPadreVO[arrays.size()]);
				
				//IDIOMAS DESTINATARIO
				IdiomaVO[] aNuevoIdiomasVO =null;
				IdiomaVO[] aIdiomasVO =educaciones[iEdu].getIdiomas();
				if (aIdiomasVO != null && aIdiomasVO.length!=0 && !(aIdiomasVO.length==1 && aIdiomasVO[0].getTexto().equals(""))){
					aNuevoIdiomasVO = new IdiomaVO[educaciones[iEdu].getIdiomas().length];
					for (int idi = 0; idi<idiomasTP.length; idi++) {
						IdiomaVO idioma = new IdiomaVO();
						idioma.setTexto(idiomasTP[idi].getIdTermino());
						aNuevoIdiomasVO[idi]=idioma;
		            }
				}
				else{
					aNuevoIdiomasVO = new IdiomaVO[1];
					IdiomaVO idioma = new IdiomaVO();
					idioma.setTexto("");
					aNuevoIdiomasVO[0]=idioma;
				}
	            form.setIdiomasAsArray(aNuevoIdiomasVO);
	            
	            //TIPO RECURSO EDUCATIVO
				SourceValueVO[] aNuevoTiposRecursoVO =null;
				SourceValueVO[] aTiposRecursoVO =educaciones[iEdu].getTiposrecursoedu();
				if (aTiposRecursoVO != null && aTiposRecursoVO.length!=0 && !(aTiposRecursoVO.length==1 && aTiposRecursoVO[0].getValor().equals(""))){
					aNuevoTiposRecursoVO = new SourceValueVO[educaciones[iEdu].getTiposrecursoedu().length];
					for (int idi = 0; idi<tipoRecursoTP.length; idi++) {
						SourceValueVO tipoRecurso = new SourceValueVO();
						tipoRecurso.setSource(source);
						tipoRecurso.setValor(tipoRecursoTP[idi].getIdTermino());
						aNuevoTiposRecursoVO[idi]=tipoRecurso;
		            }
				}
				else{
					aNuevoTiposRecursoVO = new SourceValueVO[1];
					SourceValueVO tipoRecurso = new SourceValueVO();
					tipoRecurso.setSource("");
					tipoRecurso.setValor("");
					aNuevoTiposRecursoVO[0]=tipoRecurso;
				}
	            form.setTipoRecursoAsArray(aNuevoTiposRecursoVO);
	            
	            //DESTINATARIO O ROL DEL USUARIO FINAL
				SourceValueVO[] aNuevoDestinatariosVO =null;
				SourceValueVO[] aDestinatariosVO =educaciones[iEdu].getDestinatarios();
				if (aDestinatariosVO != null && aDestinatariosVO.length!=0 &&!(aDestinatariosVO.length==1 && aDestinatariosVO[0].getValor().equals(""))){
					aNuevoDestinatariosVO = new SourceValueVO[educaciones[iEdu].getDestinatarios().length];
					for (int idi = 0; idi<rolTP.length; idi++) {
						SourceValueVO destinatario = new SourceValueVO();
						destinatario.setSource(source);
						destinatario.setValor(rolTP[idi].getIdTermino());
						aNuevoDestinatariosVO[idi]=destinatario;
		            }
				}
				else{
					aNuevoDestinatariosVO = new SourceValueVO[1];
					SourceValueVO destinatario = new SourceValueVO();
					destinatario.setSource("");
					destinatario.setValor("");
					aNuevoDestinatariosVO[0]=destinatario;
				}
	            form.setRolUsuarioAsArray(aNuevoDestinatariosVO);
	            
	            //CONTEXTO
				SourceValueVO[] aNuevoContextosVO =null;
				SourceValueVO[] aContextosVO =educaciones[iEdu].getContextos();
				if (aContextosVO != null && aContextosVO.length!=0 && !(aContextosVO.length==1 && aContextosVO[0].getValor().equals(""))){
					aNuevoContextosVO = new SourceValueVO[educaciones[iEdu].getContextos().length];
					for (int idi = 0; idi<contextoTP.length; idi++) {
						SourceValueVO contexto = new SourceValueVO();
						contexto.setSource(source);
						contexto.setValor(contextoTP[idi].getIdTermino());
						aNuevoContextosVO[idi]=contexto;
		            }
				}
				else{
					aNuevoContextosVO = new SourceValueVO[1];
					SourceValueVO contexto = new SourceValueVO();
					contexto.setSource("");
					contexto.setValor("");
					aNuevoContextosVO[0]=contexto;
				}
	            form.setContextoAsArray(aNuevoContextosVO);
	            
	            //PROCESO COGNITIVO
				SourceValueVO[] aNuevoProcesosVO =null;
				SourceValueVO[] aProcesosVO =educaciones[iEdu].getProcesoscognitivos();
				if (aProcesosVO != null && aProcesosVO.length!=0 && !(aProcesosVO.length==1 && aProcesosVO[0].getValor().equals(""))){
					aNuevoProcesosVO = new SourceValueVO[educaciones[iEdu].getProcesoscognitivos().length];
					for (int idi = 0; idi<procesoCogTP.length; idi++) {
						SourceValueVO proceso = new SourceValueVO();
						proceso.setSource(source);
						proceso.setValor(procesoCogTP[idi].getIdTermino());
						aNuevoProcesosVO[idi]=proceso;
		            }
				}
				else{
					aNuevoProcesosVO = new SourceValueVO[1];
					SourceValueVO proceso = new SourceValueVO();
					proceso.setSource("");
					proceso.setValor("");
					aNuevoProcesosVO[0]=proceso;
				}
	            form.setProcesoCognitivoAsArray(aNuevoProcesosVO);
	            
	            //TIPO INTERACTIVIDAD
				String idTI = restoCombos[0].getIdTermino();	
				if (idTI == null) {
					idTI = "";//No hay ningún tipo se recurso seleccionado por defecto
				}
				form.setTipoInteractividad(idTI);
				
				//NIVEL INTERACTIVIDAD
				String idNI = restoCombos[1].getIdTermino();	
				if (idNI == null) {
					idNI = "";//No hay ningún tipo se recurso seleccionado por defecto
				}
				form.setNivelInteractividad(idNI);
				//DENSIDAD SEMANTICA
				String idDS = restoCombos[2].getIdTermino();	
				if (idDS == null) {
					idDS = "";//No hay ningún tipo se recurso seleccionado por defecto
				}
				form.setDensidadSemantica(idDS);
				//DIFICULTAD
				String idD = restoCombos[3].getIdTermino();	
				if (idD == null) {
					idD = "";//No hay ningún tipo se recurso seleccionado por defecto
				}
				form.setDificultad(idD);
				
	//				if( logger.isDebugEnabled() ){
	//
	//	                logger.debug("Cargados en el formulario los idiomas, la estructura y el nivel de agregacion" );
	//				}	
	
				//TIEMPO DE APRENDIZAJE
				//Duracion   //expresion regular P[yY][mM][dD][T[hH][nM][s[.s]S]]
				//anyos="";meses="";dias="";horas="";minutos="";segundosP1="";segundosP2="";
				UsoEducativoSession usoeducativoSession = this.getUsoEducativoSession(request);
				if ((educaciones[iEdu] != null) && (educaciones[iEdu].getTiempoAprendizaje() !=null)){
					//duracion = educaciones[iEdu].getTiempoAprendizaje().getDuracion();
					usoeducativoSession.setDuracion(educaciones[iEdu].getTiempoAprendizaje().getDuracion());
					if (usoeducativoSession.getDuracion()!=null )
						descifrarDuration(usoeducativoSession);
					}
				form.setAnyo(usoeducativoSession.getAnyos());
				form.setMes(usoeducativoSession.getMeses());
				form.setDia(usoeducativoSession.getDias());
				form.setHora(usoeducativoSession.getHoras());
				form.setMinutos(usoeducativoSession.getMinutos());
				form.setSegundosP1(usoeducativoSession.getSegundosP1());
				form.setSegundosP2(usoeducativoSession.getSegundosP2());
				
				//Descripcion tiempo de aprendizaje
				ArrayList<TerminoYPadreVO> terminoYPadreDescripTA = new  ArrayList<TerminoYPadreVO>();
				LangStringVO[] langTextosDescripTA = null;
				LangStringVO[] nuevolangTextosDescripTA =null;
				DescripcionVO[] aDescripcionesTA_VO = new DescripcionVO[1];
				DescripcionVO descripcionTA_VO= new DescripcionVO();
				if ((educaciones[iEdu] != null) && (educaciones[iEdu].getTiempoAprendizaje() !=null)
					&& (educaciones[iEdu].getTiempoAprendizaje().getDescripcion() !=null)
					&& (educaciones[iEdu].getTiempoAprendizaje().getDescripcion().getTextos().length)> 0) {
					langTextosDescripTA =educaciones[iEdu].getTiempoAprendizaje().getDescripcion().getTextos();
					for(int j=0;j<langTextosDescripTA.length;j++){
						terminoypadreVO = new TerminoYPadreVO();
						terminoypadreVO.setIdiomaTermino("en");
						terminoypadreVO.setIdVocabulario("5.11");
						terminoypadreVO.setIdTermino("");
						if(langTextosDescripTA[j].getIdioma()!=null)
							terminoypadreVO.setNomTermino(langTextosDescripTA[j].getIdioma());
						else
							terminoypadreVO.setNomTermino("");
						terminoYPadreDescripTA.add(terminoypadreVO);
					}
					
					TerminoYPadreVO[] arrayTerminoYPadreDescTA=terminoYPadreDescripTA.toArray(new TerminoYPadreVO[terminoYPadreDescripTA.size()]);
					TerminoYPadreVO[] terminoYPadreDescTAVuelta = this.getSrvVocabulariosControladosService().obtenerIdTermino(arrayTerminoYPadreDescTA);
					
					nuevolangTextosDescripTA = new LangStringVO[terminoYPadreDescTAVuelta.length];
					for (int i = 0; i<terminoYPadreDescTAVuelta.length; i++) {
						LangStringVO langDescTA = new LangStringVO();
						langDescTA.setTexto(langTextosDescripTA[i].getTexto().trim());
						langDescTA.setIdioma(terminoYPadreDescTAVuelta[i].getIdTermino());
						nuevolangTextosDescripTA[i] = langDescTA;
		            }
				} 
				else{
					nuevolangTextosDescripTA = new LangStringVO[1];
					LangStringVO lang = new LangStringVO();
					lang.setIdioma("");
					lang.setTexto("");
					nuevolangTextosDescripTA[0] = lang;
				}
				descripcionTA_VO.setTextos(nuevolangTextosDescripTA);
				aDescripcionesTA_VO[0]=descripcionTA_VO;
	            form.setTiempoApDescripcionAsArray(aDescripcionesTA_VO);   
	            
				
	            //EDAD TIPICA O RANGO DE EDAD
	            ArrayList<ArrayList<TerminoYPadreVO>> lrangosEdad = new ArrayList<ArrayList<TerminoYPadreVO>>();
				RangoEdadVO[] rangosEdadVO =null;
				RangoEdadVO[] nuevoRangosEdadVO =null;
				if ((educaciones[iEdu] != null)&&(educaciones[iEdu].getRangoedades() !=null)
						&& (educaciones[iEdu].getRangoedades().length)> 0) {
					rangosEdadVO = educaciones[iEdu].getRangoedades();
					nuevoRangosEdadVO = new RangoEdadVO[rangosEdadVO.length];
					if(rangosEdadVO.length == 1 && rangosEdadVO[0].getTextos().length== 0){
						LangStringVO[] aLangString =new LangStringVO[1];
						LangStringVO lang = new LangStringVO();
						lang.setIdioma("");
						lang.setTexto("");
						aLangString[0]=lang;
						nuevoRangosEdadVO[0].setTextos(aLangString);
					}
					else{
						for (int i = 0; i<rangosEdadVO.length; i++){
							LangStringVO[] langTextosRangos = rangosEdadVO[i].getTextos();
							ArrayList<TerminoYPadreVO> terminoYPadreRangos = new  ArrayList<TerminoYPadreVO>();
							if(langTextosRangos!=null && langTextosRangos.length>0){
								
								for(int j=0;j<langTextosRangos.length;j++){
									terminoypadreVO = new TerminoYPadreVO();
									terminoypadreVO.setIdiomaTermino("en");
									terminoypadreVO.setIdVocabulario("5.11");
									terminoypadreVO.setIdTermino("");
									if(langTextosRangos[j].getIdioma()!=null)
										terminoypadreVO.setNomTermino(langTextosRangos[j].getIdioma());
									else
										terminoypadreVO.setNomTermino("");
									terminoYPadreRangos.add(terminoypadreVO);							
								}
								
							}else{
								terminoypadreVO = new TerminoYPadreVO();
								terminoypadreVO.setIdiomaTermino("en");
								terminoypadreVO.setIdVocabulario("5.11");
								terminoypadreVO.setIdTermino("");
								terminoypadreVO.setNomTermino("");
								terminoYPadreRangos.add(terminoypadreVO);
							}
							lrangosEdad.add(terminoYPadreRangos);
						}
	
						for (int i = 0; i<lrangosEdad.size();i++) {
							RangoEdadVO rangoVO= new RangoEdadVO();
							ArrayList<?> terminoYPadreRango = lrangosEdad.get(i);
							TerminoYPadreVO[] arrayTerminoYPadreRango=terminoYPadreRango.toArray(new TerminoYPadreVO[terminoYPadreRango.size()]);
							TerminoYPadreVO[] terminoYPadreRangoVuelta = this.getSrvVocabulariosControladosService().obtenerIdTermino(arrayTerminoYPadreRango);
							LangStringVO[] langTextosRango = rangosEdadVO[i].getTextos();
							if(langTextosRango!=null && langTextosRango.length>0){
								LangStringVO[] nuevoLangTextosRango = new LangStringVO[langTextosRango.length];
								for (int j = 0; j<terminoYPadreRangoVuelta.length; j++) {
									LangStringVO nuevoLangRango = new LangStringVO();
									nuevoLangRango.setIdioma(terminoYPadreRangoVuelta[j].getIdTermino());
									if(langTextosRango!=null && langTextosRango.length>0){
										nuevoLangRango.setTexto(langTextosRango[j].getTexto().trim());
									}else
										nuevoLangRango.setTexto("");
									nuevoLangTextosRango[j] = nuevoLangRango;
								}
								rangoVO.setTextos(nuevoLangTextosRango);
								nuevoRangosEdadVO[i]=rangoVO;
							}else{
								langTextosRango=new LangStringVO[1];
								LangStringVO nuevoLangTextoRango=new LangStringVO();
								nuevoLangTextoRango.setIdioma("");
								nuevoLangTextoRango.setTexto("");
								langTextosRango[0]=nuevoLangTextoRango;
								rangoVO.setTextos(langTextosRango);
								nuevoRangosEdadVO[i]=rangoVO;
							}
						}	
					}
				}
				else{
					nuevoRangosEdadVO = new RangoEdadVO[1];
					LangStringVO[] aLangString =new LangStringVO[1];
					LangStringVO lang = new LangStringVO();
					lang.setIdioma("");
					lang.setTexto("");
					aLangString[0]=lang;
					RangoEdadVO rangoVO= new RangoEdadVO();
					rangoVO.setTextos(aLangString);
					nuevoRangosEdadVO[0] = rangoVO;
				}
	
	            form.setEdadTipicaAsArray(nuevoRangosEdadVO);
	            
	            
	            
	            //DESCRIPCION
	            ArrayList<ArrayList<TerminoYPadreVO>> lDescripciones = new ArrayList<ArrayList<TerminoYPadreVO>>();
				DescripcionVO[] descripcionVO =null;
				DescripcionVO[] nuevoDescripcionVO =null;
				
				if ((educaciones[iEdu] != null) &&
					(educaciones[iEdu].getDescripciones() !=null) && 
					(educaciones[iEdu].getDescripciones().length)> 0) 
				{
					descripcionVO = educaciones[iEdu].getDescripciones();
					nuevoDescripcionVO = new DescripcionVO[descripcionVO.length];
					if(descripcionVO.length == 1 && descripcionVO[0].getTextos().length== 0){
						LangStringVO[] aLangString =new LangStringVO[1];
						LangStringVO lang = new LangStringVO();
						lang.setIdioma("");
						lang.setTexto("");
						aLangString[0]=lang;
						nuevoDescripcionVO[0].setTextos(aLangString);
					}
					else{
						for (int i = 0; i<descripcionVO.length; i++){
							LangStringVO[] langTextosDesc = descripcionVO[i].getTextos();
							ArrayList<TerminoYPadreVO> terminoYPadreDescripcion = new  ArrayList<TerminoYPadreVO>();
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
							ArrayList<?> terminoYPadreDesc = lDescripciones.get(i);
							TerminoYPadreVO[] arrayTerminoYPadreDescripcion=terminoYPadreDesc.toArray(new TerminoYPadreVO[terminoYPadreDesc.size()]);
							TerminoYPadreVO[] terminoYPadreDescripcionVuelta = this.getSrvVocabulariosControladosService().obtenerIdTermino(arrayTerminoYPadreDescripcion);
							LangStringVO[] langTextosDesc = descripcionVO[i].getTextos();
							
							
							if(langTextosDesc!= null && langTextosDesc.length>0){
								LangStringVO[] nuevoLangTextosDesc = new LangStringVO[langTextosDesc.length];
								for (int j = 0; j<terminoYPadreDescripcionVuelta.length; j++) {
									LangStringVO nuevoLangDesc = new LangStringVO();
									nuevoLangDesc.setIdioma(terminoYPadreDescripcionVuelta[j].getIdTermino());
									nuevoLangDesc.setTexto(langTextosDesc[j].getTexto().trim());
									nuevoLangTextosDesc[j] = nuevoLangDesc;
									descVO.setTextos(nuevoLangTextosDesc);
									
								}
							}else{
								LangStringVO[] nuevoLangTextosDesc = new LangStringVO[1];
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
	
	            form.setDescripcionesAsArray(nuevoDescripcionVO);

	            DescripcionUsoEdu[][] nuevaDescripcionUsoEdu=new DescripcionUsoEdu[nuevoDescripcionVO.length][];
	            
	            for (int i = 0; i < nuevaDescripcionUsoEdu.length; i++) {
					LangStringVO[] textos= nuevoDescripcionVO[i].getTextos();
					DescripcionUsoEdu[] arrayDescripciones= new DescripcionUsoEdu[textos.length];
					for (int j = 0; j < arrayDescripciones.length; j++) {
						String idioma=listaIdiomas.get(textos[j].getIdioma() );
						if(idioma!=null)
						{
							String[] captions = i18n.getResource("catalogadorAvanzado.descripcionesUsoEdu.titulos", "application-resources" , new Locale(idioma)).split(",");
							DescripcionUsoEdu aux=DescripcionUsoEdu.getInstance(textos[j].getTexto(), captions);
							
							if(aux.getTipoConocimiento()!=null && aux.getTipoConocimiento().size()>0)
							{
								TerminoYPadreVO[] arraytyp= new TerminoYPadreVO[aux.getTipoConocimiento().size()];
								
								for(int k=0; k<aux.getTipoConocimiento().size(); k++)
								{
									terminoypadreVO = new TerminoYPadreVO();
									terminoypadreVO.setIdiomaTermino(idioma);
									terminoypadreVO.setIdVocabulario("5.10.1");
									terminoypadreVO.setIdTermino("");
									terminoypadreVO.setNomTermino((String) aux.getTipoConocimiento().get(k));
									arraytyp[k]= terminoypadreVO;
								}
								
								TerminoYPadreVO[] typVuelta = this.getSrvVocabulariosControladosService().obtenerIdTermino(arraytyp);
								ArrayList<String> listaCodigos= new ArrayList<String>();
								for (int k = 0; k < typVuelta.length; k++) {
									listaCodigos.add(typVuelta[k].getIdTermino());
								}
								
								aux.setTipoConocimiento(listaCodigos);
							}
							arrayDescripciones[j]= aux; 
							
						}else
							arrayDescripciones[j]= DescripcionUsoEdu.getInstance();
					}
					nuevaDescripcionUsoEdu[i]= arrayDescripciones;
				}
	            
	            
	            form.setDescripcionesCatAsArray(nuevaDescripcionUsoEdu);
	            //si educational[i] era null en el objeto de session lo dejamos a null
	            if(educacionEsNull)
	            	catalogadorAvSession.getMDSesion().getEducational()[iEdu]=null;
			
			}
			else{
				// Caso CREAR
				
				//posicion en la que vamos a guardar la educacion
				String titulo ="1";
				if(educaciones == null || educaciones.length == 0){//si no hay educaciones lo guardamos en la primera
					iEdu = 0;
					form.setTituloUsoEducativo(titulo); //el numero que se muestra junto con Uso educativo		
				}
				else{//si ya habia alguna la guardamos en al final habra que añadirle una posición al array de educaciones
					iEdu = educaciones.length;
					titulo = String.valueOf(educaciones.length + 1);
					form.setTituloUsoEducativo(titulo); //el numero	
					}

				//IDIOMAS
				IdiomaVO[] aNuevoIdiomasVO = new IdiomaVO[1];
				IdiomaVO idioma = new IdiomaVO();
				idioma.setTexto("");
				aNuevoIdiomasVO[0]=idioma;
				form.setIdiomasAsArray(aNuevoIdiomasVO);
				//TIPOS RECURSOS
				SourceValueVO[] aNuevoTiposRecursoVO = new SourceValueVO[1];
				SourceValueVO tipoRecurso = new SourceValueVO();
				tipoRecurso.setSource("");
				tipoRecurso.setValor("");
				aNuevoTiposRecursoVO[0]=tipoRecurso;
				form.setTipoRecursoAsArray(aNuevoTiposRecursoVO);
				//ROL O DESTINATARIOS
				SourceValueVO[] aNuevoDestinatariosVO = new SourceValueVO[1];
				SourceValueVO destinatario = new SourceValueVO();
				destinatario.setSource("");
				destinatario.setValor("");
				aNuevoDestinatariosVO[0]=destinatario;
				form.setRolUsuarioAsArray(aNuevoDestinatariosVO);
				//CONTEXTOS
				SourceValueVO[] aNuevoContextosVO = new SourceValueVO[1];
				SourceValueVO contexto = new SourceValueVO();
				contexto.setSource("");
				contexto.setValor("");
				aNuevoContextosVO[0]=contexto;
				form.setContextoAsArray(aNuevoContextosVO);
				//PROCESOS COGNITIVOS
				SourceValueVO[] aNuevoProcesosVO = new SourceValueVO[1];
				SourceValueVO proceso = new SourceValueVO();
				proceso.setSource("");
				proceso.setValor("");
				aNuevoProcesosVO[0]=proceso;
				form.setProcesoCognitivoAsArray(aNuevoProcesosVO);
				//TIPO INTERACTIVIDAD
				form.setTipoInteractividad("");
				//NIVEL INTERACTIVIDAD
				form.setNivelInteractividad("");
				//DENSIDAD SEMANTICA
				form.setDensidadSemantica("");
				//DIFICULTAD
				form.setDificultad("");
				//TIEMPO APRENDIZAJE
				form.setAnyo("");
				form.setMes("");
				form.setDia("");
				form.setHora("");
				form.setMinutos("");
				form.setSegundosP1("");
				form.setSegundosP2("");
				//DESCRIPCION TIEMPO APRENDIZAJE
				DescripcionVO descripcionTA_VO = new DescripcionVO();
				DescripcionVO[] aDescripcionesTA_VO = new DescripcionVO[1];
				LangStringVO[] nuevolangTextosDescripTA = new LangStringVO[1];
				LangStringVO langTA = new LangStringVO();
				langTA.setIdioma("");
				langTA.setTexto("");
				nuevolangTextosDescripTA[0] = langTA;
				descripcionTA_VO.setTextos(nuevolangTextosDescripTA);
				aDescripcionesTA_VO[0]=descripcionTA_VO;
				form.setTiempoApDescripcionAsArray(aDescripcionesTA_VO); 
				//EDAD TIPICA O RANGO DE EDAD
				RangoEdadVO[] nuevoRangosEdadVO = new RangoEdadVO[1];
				LangStringVO[] aLangString =new LangStringVO[1];
				LangStringVO lang = new LangStringVO();
				lang.setIdioma("");
				lang.setTexto("");
				aLangString[0]=lang;
				RangoEdadVO rangoVO= new RangoEdadVO();
				rangoVO.setTextos(aLangString);
				nuevoRangosEdadVO[0] = rangoVO;
				form.setEdadTipicaAsArray(nuevoRangosEdadVO);
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
				form.setDescripcionesAsArray(nuevoDescripcionVO);
				// caracteristicas de la descripcion
				DescripcionUsoEdu[][] nuevaDescripcionUsoEdu= new DescripcionUsoEdu[1][1];
				nuevaDescripcionUsoEdu[0][0]=DescripcionUsoEdu.getInstance();
				form.setDescripcionesCatAsArray(nuevaDescripcionUsoEdu);
				
			}
	        //cargar ayuda contextual    
			UsoEducativoSession usoeducativoSession = this.getUsoEducativoSession(request);
			cargarAyuda(usoeducativoSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
	            
        }catch (Exception e){
			logger.error("Error en catalogadorWeb, categoría Detall Uso Educativo, metodo cargarDetalleUsoEducativo " + e);
//			throw new java.lang.Exception("detalle.uso.edu", e);
			saveErrorMessage(request, " Error en catalogadorWeb, categoría Detall Uso Educativo, metodo cargarDetalleUsoEducativo ");
		
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



    public final java.lang.String accionSubmit(
    		ActionMapping mapping, 
    		AccionSubmitForm form, 
    		HttpServletRequest request, 
    		HttpServletResponse response) 
    throws Exception
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
        
        ResourceBundle datosResources = I18n.getInstance().getResource("application-resources", (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));

        if (datosResources.getString("detalleusoedu.Aceptar").equals(accion)) {
        	resAction ="Aceptar";
        }else if (datosResources.getString("detalleusoedu.Validar").equals(accion)) {
        	resAction ="Validar";
        }else if (datosResources.getString("detalleusoedu.Cancelar").equals(accion)) {
        	resAction ="Cancelar";
        }else if (datosResources.getString("detalleusoedu.Reset").equals(accion)) {
        	resAction ="Reset";
        }else
        	resAction = accion;
        return resAction;
    }



    public final void anadirTipoRecurso(
    		ActionMapping mapping, 
    		AnadirTipoRecursoForm form, 
    		HttpServletRequest request, 
    		HttpServletResponse response)
    throws Exception
    {
    	try{
       		String source =AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES) ;//esquemaDeMetadatos
    		
    		Object valor = request.getSession().getAttribute("form");
    		
    		if (valor instanceof DetalleUsoEduFormImpl) {
    			DetalleUsoEduFormImpl formulario= (DetalleUsoEduFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof DetalleUsoEducativoSubmitFormImpl){
    			DetalleUsoEducativoSubmitFormImpl formulario= (DetalleUsoEducativoSubmitFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoValidoVolverFormImpl){
    			UsoEducativoValidoVolverFormImpl formulario= (UsoEducativoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoNoValidoVolverFormImpl){
    			UsoEducativoNoValidoVolverFormImpl formulario= (UsoEducativoNoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		
    		int[] longitudTextosDesc = new int[form.getDescripciones().size()];
    		for(int i= 0;i< form.getDescripciones().size();i++){
    			longitudTextosDesc[i]=((DescripcionVO)(form.getDescripcionesAsArray()[i])).getTextos().length;
    		}
    		int[] longitudTextosRangosEdad = new int[form.getEdadTipica().size()];
    		for(int i= 0;i< form.getEdadTipica().size();i++){
    			longitudTextosRangosEdad[i]=((RangoEdadVO)(form.getEdadTipicaAsArray()[i])).getTextos().length;
    		}

    		int longitudDescripciones=form.getDescripciones().size();
    		int longitudRangos=form.getEdadTipica().size();
    		int longitudIdiomas=form.getIdiomas().size();
    		int longitudTipoRecurso=form.getTipoRecurso().size();
    		int longitudRoles=form.getRolUsuario().size();
    		int longitudContextos=form.getContexto().size();
    		int longitudProcesos=form.getProcesoCognitivo().size();
    		int longitudTADesc=(((DescripcionVO[])form.getTiempoApDescripcionAsArray())[0]).getTextos().length;
    		
    		//borramos session
    		request.getSession().removeAttribute(UsoEducativoSession.SESSION_KEY);
    		cambioFormulario(request,longitudTextosDesc,longitudTextosRangosEdad,
    				longitudDescripciones,longitudRangos,longitudIdiomas,longitudTipoRecurso,longitudRoles,
    				longitudContextos,longitudProcesos,longitudTADesc,source);
    		//recuperamos session
    		UsoEducativoSession usoeducativoSession = this.getUsoEducativoSession(request);
    		
    		//tipo recurso educativo
    		//form.setTipoRecursoAsArray(tiposRecurso);
    		form.setTipoRecurso(usoeducativoSession.getTiposRecurso());
    		//rol o destinatario
    		//form.setRolUsuarioAsArray(roles);
    		form.setRolUsuarioAsArray(usoeducativoSession.getRoles().toArray());
    		//contextos
    		//form.setContextoAsArray(contextos);
    		form.setContextoAsArray(usoeducativoSession.getContextos().toArray());
    		//procesos cognitivos
    		//form.setProcesoCognitivoAsArray(procesosCognitivos);
    		form.setProcesoCognitivoAsArray(usoeducativoSession.getProcesosCognitivos().toArray());
    		//rangos de edad
    		//form.setEdadTipicaAsArray(rangosEdad);
    		form.setEdadTipicaAsArray(usoeducativoSession.getRangosEdad().toArray());
    		//idioma
    		//form.setIdiomasAsArray(idiomas);
    		form.setIdiomasAsArray(usoeducativoSession.getIdiomas().toArray());
    		//descripciones
    		//form.setDescripcionesAsArray(descripciones);
    		form.setDescripcionesAsArray(usoeducativoSession.getDescripciones().toArray());
    		//form.setDescripcionesCatAsArray(descripcionesCat);
    		form.setDescripcionesCatAsArray(usoeducativoSession.getDescripcionesCat().toArray());
    		//descripcion tiempo de aprendizaje
    		//form.setTiempoApDescripcionAsArray(tiempoApDescripcion);
    		form.setTiempoApDescripcionAsArray(usoeducativoSession.getTiempoApDescripcion().toArray());
    		
 		  	//form.setAnyo(anyos);
    		form.setAnyo(usoeducativoSession.getAnyos());
		  	//form.setMes(meses);
    		form.setMes(usoeducativoSession.getMeses());
		  	//form.setDia(dias);
    		form.setDia(usoeducativoSession.getDias());
		  	//form.setHora(horas);
    		form.setHora(usoeducativoSession.getHoras());
		  	//form.setMinutos(minutos);
    		form.setMinutos(usoeducativoSession.getMinutos());
		  	//form.setSegundosP1(segundosP1);
    		form.setSegundosP1(usoeducativoSession.getSegundosP1());
		  	//form.setSegundosP2(segundosP2);
    		form.setSegundosP2(usoeducativoSession.getSegundosP2());
    		
    		//añadimos un campo de tipo recurso vacio
    		Object[] aTipoRecurso=form.getTipoRecursoAsArray();
    		int nuevoTamano = aTipoRecurso.length +1;
    		Object[] newATipoRecurso = new Object[nuevoTamano];
    		SourceValueVO tipoRecursoVO=new SourceValueVO();
    		tipoRecursoVO.setValor("");
    		tipoRecursoVO.setSource("");
    		for(int i = 0; i<aTipoRecurso.length;i++)
    			newATipoRecurso[i]=aTipoRecurso[i];
    		newATipoRecurso[nuevoTamano-1]= tipoRecursoVO;
 	   
    		form.setTipoRecursoAsArray(newATipoRecurso);
    		//actualizamos session
    		usoeducativoSession.setTiposRecurso(Arrays.asList(newATipoRecurso));
    		
    		 //cargar ayuda contextual    
			 cargarAyuda(usoeducativoSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
 	   
		}catch (ArrayIndexOutOfBoundsException ai) {
			logger
			.warn("Warning en catalogadorWeb, categoría Tecnica, metodo anadirTipoRecurso " + ai);
		}catch (Exception e) {
			logger
			.error("Error en Servicio de catalogacionWeb, categoría Tecnica, metodo anadirTipoRecurso " + e);
//			throw new Exception("tecnica",e);
			saveErrorMessage(request, " Error en Servicio de catalogacionWeb, categoría Tecnica, metodo anadirTipoRecurso ");
		}     
     }




    public final void eliminarTipoRecurso(
    		ActionMapping mapping, 
    		EliminarTipoRecursoForm form, 
    		HttpServletRequest request, 
    		HttpServletResponse response) 
    throws Exception
    {
    	try{
    		String source=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES) ;//esquemaDeMetadatos
    		
    		Object valor = request.getSession().getAttribute("form");
    		
    		if (valor instanceof DetalleUsoEduFormImpl) {
    			DetalleUsoEduFormImpl formulario= (DetalleUsoEduFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof DetalleUsoEducativoSubmitFormImpl){
    			DetalleUsoEducativoSubmitFormImpl formulario= (DetalleUsoEducativoSubmitFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoValidoVolverFormImpl){
    			UsoEducativoValidoVolverFormImpl formulario= (UsoEducativoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoNoValidoVolverFormImpl){
    			UsoEducativoNoValidoVolverFormImpl formulario= (UsoEducativoNoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
     		
    		int[] longitudTextosDesc = new int[form.getDescripciones().size()];
    		for(int i= 0;i< form.getDescripciones().size();i++){
    			longitudTextosDesc[i]=((DescripcionVO)(form.getDescripcionesAsArray()[i])).getTextos().length;
    		}
    		int[] longitudTextosRangosEdad = new int[form.getEdadTipica().size()];
    		for(int i= 0;i< form.getEdadTipica().size();i++){
    			longitudTextosRangosEdad[i]=((RangoEdadVO)(form.getEdadTipicaAsArray()[i])).getTextos().length;
    		}

    		int longitudDescripciones=form.getDescripciones().size();
    		int longitudRangos=form.getEdadTipica().size();
    		int longitudIdiomas=form.getIdiomas().size();
    		int longitudTipoRecurso=form.getTipoRecurso().size();
    		int longitudRoles=form.getRolUsuario().size();
    		int longitudContextos=form.getContexto().size();
    		int longitudProcesos=form.getProcesoCognitivo().size();
    		int longitudTADesc=(((DescripcionVO[])form.getTiempoApDescripcionAsArray())[0]).getTextos().length;
    		
    		//borrar session
    		request.getSession().removeAttribute(UsoEducativoSession.SESSION_KEY);
    		cambioFormulario(request,longitudTextosDesc,longitudTextosRangosEdad,
    				longitudDescripciones,longitudRangos,longitudIdiomas,longitudTipoRecurso,longitudRoles,
    				longitudContextos,longitudProcesos,longitudTADesc,source);
    		//recuperamos session
    		UsoEducativoSession usoeducativoSession = this.getUsoEducativoSession(request);
    		
    		//tipo recurso educativo
    		 //form.setTipoRecursoAsArray(tiposRecurso);
    		form.setTipoRecurso(usoeducativoSession.getTiposRecurso());
    		//rol o destinatario
    		//form.setRolUsuarioAsArray(roles);
    		form.setRolUsuarioAsArray(usoeducativoSession.getRoles().toArray());
    		//contextos
    		//form.setContextoAsArray(contextos);
    		form.setContextoAsArray(usoeducativoSession.getContextos().toArray());
    		//procesos cognitivos
    		//form.setProcesoCognitivoAsArray(procesosCognitivos);
    		form.setProcesoCognitivoAsArray(usoeducativoSession.getProcesosCognitivos().toArray());
    		//rangos de edad
    		//form.setEdadTipicaAsArray(rangosEdad);
    		form.setEdadTipicaAsArray(usoeducativoSession.getRangosEdad().toArray());
    		//idioma
    		//form.setIdiomasAsArray(idiomas);
    		form.setIdiomasAsArray(usoeducativoSession.getIdiomas().toArray());
    		//descripciones
    		//form.setDescripcionesAsArray(descripciones);
    		form.setDescripcionesAsArray(usoeducativoSession.getDescripciones().toArray());
    		//form.setDescripcionesCatAsArray(descripcionesCat);
    		form.setDescripcionesCatAsArray(usoeducativoSession.getDescripcionesCat().toArray());
    		//descripcion tiempo de aprendizaje
    		//form.setTiempoApDescripcionAsArray(tiempoApDescripcion);
    		form.setTiempoApDescripcionAsArray(usoeducativoSession.getTiempoApDescripcion().toArray());
    		
 		  	//form.setAnyo(anyos);
    		form.setAnyo(usoeducativoSession.getAnyos());
		  	//form.setMes(meses);
    		form.setMes(usoeducativoSession.getMeses());
		  	//form.setDia(dias);
    		form.setDia(usoeducativoSession.getDias());
		  	//form.setHora(horas);
    		form.setHora(usoeducativoSession.getHoras());
		  	//form.setMinutos(minutos);
    		form.setMinutos(usoeducativoSession.getMinutos());
		  	//form.setSegundosP1(segundosP1);
    		form.setSegundosP1(usoeducativoSession.getSegundosP1());
		  	//form.setSegundosP2(segundosP2);
    		form.setSegundosP2(usoeducativoSession.getSegundosP2());
    		
	        //posicion del tipo de recurso que se quiere eliminar   
	        String posicion = request.getAttribute("posicion").toString();
	        int posicionInt = Integer.parseInt(posicion);
	    	
	        SourceValueVO[] aTipoRecurso=(SourceValueVO[]) form.getTipoRecursoAsArray();
	        SourceValueVO[] nuevoTipoRecurso = new SourceValueVO[aTipoRecurso.length -1];
	     	//añado al nuevo array de tipos de recurso con todos los tipos de recurso menos el que 
	     	//queremos eliminar
	     	for (int i = 0; i<nuevoTipoRecurso.length;i++){
	     		if (i < posicionInt)
	     			nuevoTipoRecurso[i] = aTipoRecurso[i];
	     		else
	     			nuevoTipoRecurso[i] = aTipoRecurso[i+1]; 
	     	}
    	   
	     	form.setTipoRecursoAsArray(nuevoTipoRecurso);
	     	//actualizamos la session
	     	usoeducativoSession.setTiposRecurso(Arrays.asList(nuevoTipoRecurso));
	     	
	     	 //cargar ayuda contextual    
			 cargarAyuda(usoeducativoSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
	     	
		}catch (ArrayIndexOutOfBoundsException ai) {
			logger
			.warn("Warning en catalogadorWeb, categoría Tecnica, metodo eliminarTipoRecurso " + ai);
		}catch (Exception e) {
			logger
			.error("Error en Servicio de catalogacionWeb, categoría Tecnica, metodo eliminarTipoRecurso " + e);
//			throw new Exception("tecnica",e);
			saveErrorMessage(request, " Error en Servicio de catalogacionWeb, categoría Tecnica, metodo eliminarTipoRecurso ");
		}     
     }







     public final void anadirRolUsuario(
    		 ActionMapping mapping, 
    		 AnadirRolUsuarioForm form, 
    		 HttpServletRequest request, 
    		 HttpServletResponse response) 
     throws Exception
    {
    	try{
    	
    		String source=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES) ;//esquemaDeMetadatos
    		
    		Object valor = request.getSession().getAttribute("form");
    		
    		if (valor instanceof DetalleUsoEduFormImpl) {
    			DetalleUsoEduFormImpl formulario= (DetalleUsoEduFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof DetalleUsoEducativoSubmitFormImpl){
    			DetalleUsoEducativoSubmitFormImpl formulario= (DetalleUsoEducativoSubmitFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoValidoVolverFormImpl){
    			UsoEducativoValidoVolverFormImpl formulario= (UsoEducativoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoNoValidoVolverFormImpl){
    			UsoEducativoNoValidoVolverFormImpl formulario= (UsoEducativoNoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
     		
    		int[] longitudTextosDesc = new int[form.getDescripciones().size()];
    		for(int i= 0;i< form.getDescripciones().size();i++){
    			longitudTextosDesc[i]=((DescripcionVO)(form.getDescripcionesAsArray()[i])).getTextos().length;
    		}
    		int[] longitudTextosRangosEdad = new int[form.getEdadTipica().size()];
    		for(int i= 0;i< form.getEdadTipica().size();i++){
    			longitudTextosRangosEdad[i]=((RangoEdadVO)(form.getEdadTipicaAsArray()[i])).getTextos().length;
    		}

    		int longitudDescripciones=form.getDescripciones().size();
    		int longitudRangos=form.getEdadTipica().size();
    		int longitudIdiomas=form.getIdiomas().size();
    		int longitudTipoRecurso=form.getTipoRecurso().size();
    		int longitudRoles=form.getRolUsuario().size();
    		int longitudContextos=form.getContexto().size();
    		int longitudProcesos=form.getProcesoCognitivo().size();
    		int longitudTADesc=(((DescripcionVO[])form.getTiempoApDescripcionAsArray())[0]).getTextos().length;
    		
    		//borrar session
    		request.getSession().removeAttribute(UsoEducativoSession.SESSION_KEY);
    		cambioFormulario(request,longitudTextosDesc,longitudTextosRangosEdad,
    				longitudDescripciones,longitudRangos,longitudIdiomas,longitudTipoRecurso,longitudRoles,
    				longitudContextos,longitudProcesos,longitudTADesc,source);
    		UsoEducativoSession usoeducativoSession = this.getUsoEducativoSession(request);
    		
    		//tipo recurso educativo
//    		form.setTipoRecursoAsArray(tiposRecurso);
    		form.setTipoRecurso(usoeducativoSession.getTiposRecurso());
    		//rol o destinatario
    		//form.setRolUsuarioAsArray(roles);
    		form.setRolUsuarioAsArray(usoeducativoSession.getRoles().toArray());
    		//contextos
    		//form.setContextoAsArray(contextos);
    		form.setContextoAsArray(usoeducativoSession.getContextos().toArray());
    		//procesos cognitivos
    		//form.setProcesoCognitivoAsArray(procesosCognitivos);
    		form.setProcesoCognitivoAsArray(usoeducativoSession.getProcesosCognitivos().toArray());
    		//rangos de edad
    		//form.setEdadTipicaAsArray(rangosEdad);
    		form.setEdadTipicaAsArray(usoeducativoSession.getRangosEdad().toArray());
    		//idioma
    		//form.setIdiomasAsArray(idiomas);
    		form.setIdiomasAsArray(usoeducativoSession.getIdiomas().toArray());
    		//descripciones
    		//form.setDescripcionesAsArray(descripciones);
    		form.setDescripcionesAsArray(usoeducativoSession.getDescripciones().toArray());
    		//form.setDescripcionesCatAsArray(descripcionesCat);
    		form.setDescripcionesCatAsArray(usoeducativoSession.getDescripcionesCat().toArray());
    		//descripcion tiempo de aprendizaje
    		//form.setTiempoApDescripcionAsArray(tiempoApDescripcion);
    		form.setTiempoApDescripcionAsArray(usoeducativoSession.getTiempoApDescripcion().toArray());
    		
 		  	//form.setAnyo(anyos);
    		form.setAnyo(usoeducativoSession.getAnyos());
		  	//form.setMes(meses);
    		form.setMes(usoeducativoSession.getMeses());
		  	//form.setDia(dias);
    		form.setDia(usoeducativoSession.getDias());
		  	//form.setHora(horas);
    		form.setHora(usoeducativoSession.getHoras());
		  	//form.setMinutos(minutos);
    		form.setMinutos(usoeducativoSession.getMinutos());
		  	//form.setSegundosP1(segundosP1);
    		form.setSegundosP1(usoeducativoSession.getSegundosP1());
		  	//form.setSegundosP2(segundosP2);
    		form.setSegundosP2(usoeducativoSession.getSegundosP2());
    		
    		//añadimos un campo de rol de usuario final vacio
    		Object[] aRolUsuario=form.getRolUsuarioAsArray();
    		int nuevoTamano = aRolUsuario.length +1;
    		Object[] newARolUsuario = new Object[nuevoTamano];
    		SourceValueVO rolUsuarioVO=new SourceValueVO();
    		rolUsuarioVO.setValor("");
    		rolUsuarioVO.setSource("");
    		for(int i = 0; i<aRolUsuario.length;i++)
    			newARolUsuario[i]=aRolUsuario[i];
    		newARolUsuario[nuevoTamano-1]= rolUsuarioVO;
 	   
    		form.setRolUsuarioAsArray(newARolUsuario);
    		//actualizamos session
    		usoeducativoSession.setRoles(Arrays.asList(newARolUsuario));
 	   
    		 //cargar ayuda contextual    
			 cargarAyuda(usoeducativoSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
    		
		}catch (ArrayIndexOutOfBoundsException ai) {
			logger
			.warn("Warning en catalogadorWeb, categoría Tecnica, metodo anadirRolUsuario " + ai);
		}catch (Exception e) {
			logger
			.error("Error en Servicio de catalogacionWeb, categoría Tecnica, metodo anadirRolUsuario " + e);
			//throw new Exception("tecnica",e);
			saveErrorMessage(request, " Error en Servicio de catalogacionWeb, categoría Tecnica, metodo anadirRolUsuario ");
		}     
     }






    public final void eliminarRolUsuario(
    		ActionMapping mapping, 
    		EliminarRolUsuarioForm form, 
    		HttpServletRequest request, 
    		HttpServletResponse response)
    throws Exception
    {
    	try{

    		String source=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES) ;//esquemaDeMetadatos
    		
    		Object valor = request.getSession().getAttribute("form");
    		
    		if (valor instanceof DetalleUsoEduFormImpl) {
    			DetalleUsoEduFormImpl formulario= (DetalleUsoEduFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof DetalleUsoEducativoSubmitFormImpl){
    			DetalleUsoEducativoSubmitFormImpl formulario= (DetalleUsoEducativoSubmitFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoValidoVolverFormImpl){
    			UsoEducativoValidoVolverFormImpl formulario= (UsoEducativoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoNoValidoVolverFormImpl){
    			UsoEducativoNoValidoVolverFormImpl formulario= (UsoEducativoNoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		
    		int[] longitudTextosDesc = new int[form.getDescripciones().size()];
    		for(int i= 0;i< form.getDescripciones().size();i++){
    			longitudTextosDesc[i]=((DescripcionVO)(form.getDescripcionesAsArray()[i])).getTextos().length;
    		}
    		int[] longitudTextosRangosEdad = new int[form.getEdadTipica().size()];
    		for(int i= 0;i< form.getEdadTipica().size();i++){
    			longitudTextosRangosEdad[i]=((RangoEdadVO)(form.getEdadTipicaAsArray()[i])).getTextos().length;
    		}

    		int longitudDescripciones=form.getDescripciones().size();
    		int longitudRangos=form.getEdadTipica().size();
    		int longitudIdiomas=form.getIdiomas().size();
    		int longitudTipoRecurso=form.getTipoRecurso().size();
    		int longitudRoles=form.getRolUsuario().size();
    		int longitudContextos=form.getContexto().size();
    		int longitudProcesos=form.getProcesoCognitivo().size();
    		int longitudTADesc=(((DescripcionVO[])form.getTiempoApDescripcionAsArray())[0]).getTextos().length;
    		
    		//borrar session
    		request.getSession().removeAttribute(UsoEducativoSession.SESSION_KEY);
    		cambioFormulario(request,longitudTextosDesc,longitudTextosRangosEdad,
    				longitudDescripciones,longitudRangos,longitudIdiomas,longitudTipoRecurso,longitudRoles,
    				longitudContextos,longitudProcesos,longitudTADesc,source);
    		//recoger session
    		UsoEducativoSession usoeducativoSession = this.getUsoEducativoSession(request);
    		
    		//tipo recurso educativo
    		 //form.setTipoRecursoAsArray(tiposRecurso);
    		form.setTipoRecurso(usoeducativoSession.getTiposRecurso());
    		//rol o destinatario
    		//form.setRolUsuarioAsArray(roles);
    		form.setRolUsuarioAsArray(usoeducativoSession.getRoles().toArray());
    		//contextos
    		//form.setContextoAsArray(contextos);
    		form.setContextoAsArray(usoeducativoSession.getContextos().toArray());
    		//procesos cognitivos
    		//form.setProcesoCognitivoAsArray(procesosCognitivos);
    		form.setProcesoCognitivoAsArray(usoeducativoSession.getProcesosCognitivos().toArray());
    		//rangos de edad
    		//form.setEdadTipicaAsArray(rangosEdad);
    		form.setEdadTipicaAsArray(usoeducativoSession.getRangosEdad().toArray());
    		//idioma
    		//form.setIdiomasAsArray(idiomas);
    		form.setIdiomasAsArray(usoeducativoSession.getIdiomas().toArray());
    		//descripciones
    		//form.setDescripcionesAsArray(descripciones);
    		form.setDescripcionesAsArray(usoeducativoSession.getDescripciones().toArray());
    		//form.setDescripcionesCatAsArray(descripcionesCat);
    		form.setDescripcionesCatAsArray(usoeducativoSession.getDescripcionesCat().toArray());
    		//descripcion tiempo de aprendizaje
    		//form.setTiempoApDescripcionAsArray(tiempoApDescripcion);
    		form.setTiempoApDescripcionAsArray(usoeducativoSession.getTiempoApDescripcion().toArray());
    		
 		  	//form.setAnyo(anyos);
    		form.setAnyo(usoeducativoSession.getAnyos());
		  	//form.setMes(meses);
    		form.setMes(usoeducativoSession.getMeses());
		  	//form.setDia(dias);
    		form.setDia(usoeducativoSession.getDias());
		  	//form.setHora(horas);
    		form.setHora(usoeducativoSession.getHoras());
		  	//form.setMinutos(minutos);
    		form.setMinutos(usoeducativoSession.getMinutos());
		  	//form.setSegundosP1(segundosP1);
    		form.setSegundosP1(usoeducativoSession.getSegundosP1());
		  	//form.setSegundosP2(segundosP2);
    		form.setSegundosP2(usoeducativoSession.getSegundosP2());        
    		
	        //posicion del rol de usuario que se quiere eliminar   
	        String posicion = request.getAttribute("posicion").toString();
	        int posicionInt = Integer.parseInt(posicion);
	    	
	        SourceValueVO[] aRolUsuario=(SourceValueVO[]) form.getRolUsuarioAsArray();
	        SourceValueVO[] nuevoRolUsuario = new SourceValueVO[aRolUsuario.length -1];
	     	//añado al nuevo array de roles de usuario con todos los roles menos el que 
	     	//queremos eliminar
	     	for (int i = 0; i<nuevoRolUsuario.length;i++){
	     		if (i < posicionInt)
	     			nuevoRolUsuario[i] = aRolUsuario[i];
	     		else
	     			nuevoRolUsuario[i] = aRolUsuario[i+1]; 
	     	}
    	   
	     	form.setRolUsuarioAsArray(nuevoRolUsuario);
	     	//act session
	     	usoeducativoSession.setRoles(Arrays.asList(nuevoRolUsuario));
	     	
	     	 //cargar ayuda contextual    
			 cargarAyuda(usoeducativoSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
	    
		}catch (ArrayIndexOutOfBoundsException ai) {
			logger
			.warn("Warning en catalogadorWeb, categoría Tecnica, metodo eliminarRolUsuario " + ai);
		}catch (Exception e) {
			logger
			.error("Error en Servicio de catalogacionWeb, categoría Tecnica, metodo eliminarRolUsuario " + e);
//			throw new Exception("tecnica",e);
			saveErrorMessage(request, " Error en Servicio de catalogacionWeb, categoría Tecnica, metodo eliminarRolUsuario ");
		}      
     }



     public final void anadirContexto(
    		 ActionMapping mapping,
    		 AnadirContextoForm form, 
    		 HttpServletRequest request, 
    		 HttpServletResponse response) 
     throws Exception
    {
    	try{
    	
    		String source=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES) ;//esquemaDeMetadatos
    		
    		Object valor = request.getSession().getAttribute("form");
    		
    		if (valor instanceof DetalleUsoEduFormImpl) {
    			DetalleUsoEduFormImpl formulario= (DetalleUsoEduFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof DetalleUsoEducativoSubmitFormImpl){
    			DetalleUsoEducativoSubmitFormImpl formulario= (DetalleUsoEducativoSubmitFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoValidoVolverFormImpl){
    			UsoEducativoValidoVolverFormImpl formulario= (UsoEducativoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoNoValidoVolverFormImpl){
    			UsoEducativoNoValidoVolverFormImpl formulario= (UsoEducativoNoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		
    		int[] longitudTextosDesc = new int[form.getDescripciones().size()];
    		for(int i= 0;i< form.getDescripciones().size();i++){
    			longitudTextosDesc[i]=((DescripcionVO)(form.getDescripcionesAsArray()[i])).getTextos().length;
    		}
    		int[] longitudTextosRangosEdad = new int[form.getEdadTipica().size()];
    		for(int i= 0;i< form.getEdadTipica().size();i++){
    			longitudTextosRangosEdad[i]=((RangoEdadVO)(form.getEdadTipicaAsArray()[i])).getTextos().length;
    		}

    		int longitudDescripciones=form.getDescripciones().size();
    		int longitudRangos=form.getEdadTipica().size();
    		int longitudIdiomas=form.getIdiomas().size();
    		int longitudTipoRecurso=form.getTipoRecurso().size();
    		int longitudRoles=form.getRolUsuario().size();
    		int longitudContextos=form.getContexto().size();
    		int longitudProcesos=form.getProcesoCognitivo().size();
    		int longitudTADesc=(((DescripcionVO[])form.getTiempoApDescripcionAsArray())[0]).getTextos().length;
    		
    		//borramos session
    		request.getSession().removeAttribute(UsoEducativoSession.SESSION_KEY);
    		cambioFormulario(request,longitudTextosDesc,longitudTextosRangosEdad,
    				longitudDescripciones,longitudRangos,longitudIdiomas,longitudTipoRecurso,longitudRoles,
    				longitudContextos,longitudProcesos,longitudTADesc,source);
    		//recuperamos session
    		UsoEducativoSession usoeducativoSession = this.getUsoEducativoSession(request);
    		
    		//tipo recurso educativo
//    		form.setTipoRecursoAsArray(tiposRecurso);
    		form.setTipoRecurso(usoeducativoSession.getTiposRecurso());
    		//rol o destinatario
    		//form.setRolUsuarioAsArray(roles);
    		form.setRolUsuarioAsArray(usoeducativoSession.getRoles().toArray());
    		//contextos
    		//form.setContextoAsArray(contextos);
    		form.setContextoAsArray(usoeducativoSession.getContextos().toArray());
    		//procesos cognitivos
    		//form.setProcesoCognitivoAsArray(procesosCognitivos);
    		form.setProcesoCognitivoAsArray(usoeducativoSession.getProcesosCognitivos().toArray());
    		//rangos de edad
    		//form.setEdadTipicaAsArray(rangosEdad);
    		form.setEdadTipicaAsArray(usoeducativoSession.getRangosEdad().toArray());
    		//idioma
    		//form.setIdiomasAsArray(idiomas);
    		form.setIdiomasAsArray(usoeducativoSession.getIdiomas().toArray());
    		//descripciones
    		//form.setDescripcionesAsArray(descripciones);
    		form.setDescripcionesAsArray(usoeducativoSession.getDescripciones().toArray());
    		//form.setDescripcionesCatAsArray(descripcionesCat);
    		form.setDescripcionesCatAsArray(usoeducativoSession.getDescripcionesCat().toArray());
    		//descripcion tiempo de aprendizaje
    		//form.setTiempoApDescripcionAsArray(tiempoApDescripcion);
    		form.setTiempoApDescripcionAsArray(usoeducativoSession.getTiempoApDescripcion().toArray());
    		
 		  	//form.setAnyo(anyos);
    		form.setAnyo(usoeducativoSession.getAnyos());
		  	//form.setMes(meses);
    		form.setMes(usoeducativoSession.getMeses());
		  	//form.setDia(dias);
    		form.setDia(usoeducativoSession.getDias());
		  	//form.setHora(horas);
    		form.setHora(usoeducativoSession.getHoras());
		  	//form.setMinutos(minutos);
    		form.setMinutos(usoeducativoSession.getMinutos());
		  	//form.setSegundosP1(segundosP1);
    		form.setSegundosP1(usoeducativoSession.getSegundosP1());
		  	//form.setSegundosP2(segundosP2);
    		form.setSegundosP2(usoeducativoSession.getSegundosP2());
    		
    		//añadimos un campo de contexto vacio
    		Object[] aContexto=form.getContextoAsArray();
    		int nuevoTamano = aContexto.length +1;
    		Object[] newAContexto = new Object[nuevoTamano];
    		SourceValueVO contextoVO=new SourceValueVO();
    		contextoVO.setValor("");
    		contextoVO.setSource("");
    		for(int i = 0; i<aContexto.length;i++)
    			newAContexto[i]=aContexto[i];
    		newAContexto[nuevoTamano-1]= contextoVO;
 	   
    		form.setContextoAsArray(newAContexto);
    		//act session
    		usoeducativoSession.setContextos(Arrays.asList(newAContexto));
    		
    		 //cargar ayuda contextual    
			 cargarAyuda(usoeducativoSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
 	   
		}catch (ArrayIndexOutOfBoundsException ai) {
			logger
			.warn("Warning en catalogadorWeb, categoría Tecnica, metodo anadirContexto " + ai);
		}catch (Exception e) {
			logger
			.error("Error en Servicio de catalogacionWeb, categoría Tecnica, metodo anadirContexto " + e);
//			throw new Exception("tecnica",e);
			saveErrorMessage(request, " Error en Servicio de catalogacionWeb, categoría Tecnica, metodo anadirContexto ");
		}     
     }



    public final void eliminarContexto(
    		ActionMapping mapping, 
    		EliminarContextoForm form, 
    		HttpServletRequest request, 
    		HttpServletResponse response) 
    throws Exception
    {
    	try{
   
    		String source=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES) ;//esquemaDeMetadatos
    		
    		Object valor = request.getSession().getAttribute("form");
    		
    		if (valor instanceof DetalleUsoEduFormImpl) {
    			DetalleUsoEduFormImpl formulario= (DetalleUsoEduFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof DetalleUsoEducativoSubmitFormImpl){
    			DetalleUsoEducativoSubmitFormImpl formulario= (DetalleUsoEducativoSubmitFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoValidoVolverFormImpl){
    			UsoEducativoValidoVolverFormImpl formulario= (UsoEducativoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoNoValidoVolverFormImpl){
    			UsoEducativoNoValidoVolverFormImpl formulario= (UsoEducativoNoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		
    		int[] longitudTextosDesc = new int[form.getDescripciones().size()];
    		for(int i= 0;i< form.getDescripciones().size();i++){
    			longitudTextosDesc[i]=((DescripcionVO)(form.getDescripcionesAsArray()[i])).getTextos().length;
    		}
    		int[] longitudTextosRangosEdad = new int[form.getEdadTipica().size()];
    		for(int i= 0;i< form.getEdadTipica().size();i++){
    			longitudTextosRangosEdad[i]=((RangoEdadVO)(form.getEdadTipicaAsArray()[i])).getTextos().length;
    		}

    		int longitudDescripciones=form.getDescripciones().size();
    		int longitudRangos=form.getEdadTipica().size();
    		int longitudIdiomas=form.getIdiomas().size();
    		int longitudTipoRecurso=form.getTipoRecurso().size();
    		int longitudRoles=form.getRolUsuario().size();
    		int longitudContextos=form.getContexto().size();
    		int longitudProcesos=form.getProcesoCognitivo().size();
    		int longitudTADesc=(((DescripcionVO[])form.getTiempoApDescripcionAsArray())[0]).getTextos().length;
    		
    		//borrar session
    		request.getSession().removeAttribute(UsoEducativoSession.SESSION_KEY);
    		cambioFormulario(request,longitudTextosDesc,longitudTextosRangosEdad,
    				longitudDescripciones,longitudRangos,longitudIdiomas,longitudTipoRecurso,longitudRoles,
    				longitudContextos,longitudProcesos,longitudTADesc,source);
    		//recuperar session
    		UsoEducativoSession usoeducativoSession = this.getUsoEducativoSession(request);
    		
    		//tipo recurso educativo
    		 //form.setTipoRecursoAsArray(tiposRecurso);
    		form.setTipoRecurso(usoeducativoSession.getTiposRecurso());
    		//rol o destinatario
    		//form.setRolUsuarioAsArray(roles);
    		form.setRolUsuarioAsArray(usoeducativoSession.getRoles().toArray());
    		//contextos
    		//form.setContextoAsArray(contextos);
    		form.setContextoAsArray(usoeducativoSession.getContextos().toArray());
    		//procesos cognitivos
    		//form.setProcesoCognitivoAsArray(procesosCognitivos);
    		form.setProcesoCognitivoAsArray(usoeducativoSession.getProcesosCognitivos().toArray());
    		//rangos de edad
    		//form.setEdadTipicaAsArray(rangosEdad);
    		form.setEdadTipicaAsArray(usoeducativoSession.getRangosEdad().toArray());
    		//idioma
    		//form.setIdiomasAsArray(idiomas);
    		form.setIdiomasAsArray(usoeducativoSession.getIdiomas().toArray());
    		//descripciones
    		//form.setDescripcionesAsArray(descripciones);
    		form.setDescripcionesAsArray(usoeducativoSession.getDescripciones().toArray());
    		//form.setDescripcionesCatAsArray(descripcionesCat);
    		form.setDescripcionesCatAsArray(usoeducativoSession.getDescripcionesCat().toArray());
    		//descripcion tiempo de aprendizaje
    		//form.setTiempoApDescripcionAsArray(tiempoApDescripcion);
    		form.setTiempoApDescripcionAsArray(usoeducativoSession.getTiempoApDescripcion().toArray());
    		
 		  	//form.setAnyo(anyos);
    		form.setAnyo(usoeducativoSession.getAnyos());
		  	//form.setMes(meses);
    		form.setMes(usoeducativoSession.getMeses());
		  	//form.setDia(dias);
    		form.setDia(usoeducativoSession.getDias());
		  	//form.setHora(horas);
    		form.setHora(usoeducativoSession.getHoras());
		  	//form.setMinutos(minutos);
    		form.setMinutos(usoeducativoSession.getMinutos());
		  	//form.setSegundosP1(segundosP1);
    		form.setSegundosP1(usoeducativoSession.getSegundosP1());
		  	//form.setSegundosP2(segundosP2);
    		form.setSegundosP2(usoeducativoSession.getSegundosP2());
    		
	        //posicion del contexto que se quiere eliminar   
	        String posicion = request.getAttribute("posicion").toString();
	        int posicionInt = Integer.parseInt(posicion);
	    	
	        SourceValueVO[] aContexto=(SourceValueVO[]) form.getContextoAsArray();
	        SourceValueVO[] nuevoContexto = new SourceValueVO[aContexto.length -1];
	     	//añado al nuevo array de contextos con todos los contextos menos el que 
	     	//queremos eliminar
	     	for (int i = 0; i<nuevoContexto.length;i++){
	     		if (i < posicionInt)
	     			nuevoContexto[i] = aContexto[i];
	     		else
	     			nuevoContexto[i] = aContexto[i+1]; 
	     	}
    	   
	     	form.setContextoAsArray(nuevoContexto);
	     	//act session
	     	usoeducativoSession.setContextos(Arrays.asList(nuevoContexto));
	    
	     	 //cargar ayuda contextual    
			 cargarAyuda(usoeducativoSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
	     	
		}catch (ArrayIndexOutOfBoundsException ai) {
			logger
			.warn("Warning en catalogadorWeb, categoría Tecnica, metodo eliminarContexto " + ai);
		}catch (Exception e) {
			logger
			.error("Error en Servicio de catalogacionWeb, categoría Tecnica, metodo eliminarContexto " + e);
//			throw new Exception("tecnica",e);
			saveErrorMessage(request, " Error en Servicio de catalogacionWeb, categoría Tecnica, metodo eliminarContexto ");
		}     
     }



    public final void anadirEdadTipica(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.usoeducativo.detalleUsoEducativo.AnadirEdadTipicaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
	 
			String source=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES) ;//esquemaDeMetadatos
			
			Object valor = request.getSession().getAttribute("form");
			
    		if (valor instanceof DetalleUsoEduFormImpl) {
    			DetalleUsoEduFormImpl formulario= (DetalleUsoEduFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof DetalleUsoEducativoSubmitFormImpl){
    			DetalleUsoEducativoSubmitFormImpl formulario= (DetalleUsoEducativoSubmitFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoValidoVolverFormImpl){
    			UsoEducativoValidoVolverFormImpl formulario= (UsoEducativoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoNoValidoVolverFormImpl){
    			UsoEducativoNoValidoVolverFormImpl formulario= (UsoEducativoNoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
 			
			int[] longitudTextosDesc = new int[form.getDescripciones().size()];
			for(int i= 0;i< form.getDescripciones().size();i++){
				longitudTextosDesc[i]=((DescripcionVO)(form.getDescripcionesAsArray()[i])).getTextos().length;
			}
			int[] longitudTextosRangosEdad = new int[form.getEdadTipica().size()];
			for(int i= 0;i< form.getEdadTipica().size();i++){
				longitudTextosRangosEdad[i]=((RangoEdadVO)(form.getEdadTipicaAsArray()[i])).getTextos().length;
			}
	
			int longitudDescripciones=form.getDescripciones().size();
			int longitudRangos=form.getEdadTipica().size();
			int longitudIdiomas=form.getIdiomas().size();
			int longitudTipoRecurso=form.getTipoRecurso().size();
			int longitudRoles=form.getRolUsuario().size();
			int longitudContextos=form.getContexto().size();
			int longitudProcesos=form.getProcesoCognitivo().size();
			int longitudTADesc=(((DescripcionVO[])form.getTiempoApDescripcionAsArray())[0]).getTextos().length;
			
			//borrar session
			request.getSession().removeAttribute(UsoEducativoSession.SESSION_KEY);
			cambioFormulario(request,longitudTextosDesc,longitudTextosRangosEdad,
					longitudDescripciones,longitudRangos,longitudIdiomas,longitudTipoRecurso,longitudRoles,
					longitudContextos,longitudProcesos,longitudTADesc,source);
			//recuperar session
			UsoEducativoSession usoeducativoSession = this.getUsoEducativoSession(request);
			
			//tipo recurso educativo
//			form.setTipoRecursoAsArray(tiposRecurso);
    		form.setTipoRecurso(usoeducativoSession.getTiposRecurso());
    		//rol o destinatario
    		//form.setRolUsuarioAsArray(roles);
    		form.setRolUsuarioAsArray(usoeducativoSession.getRoles().toArray());
    		//contextos
    		//form.setContextoAsArray(contextos);
    		form.setContextoAsArray(usoeducativoSession.getContextos().toArray());
    		//procesos cognitivos
    		//form.setProcesoCognitivoAsArray(procesosCognitivos);
    		form.setProcesoCognitivoAsArray(usoeducativoSession.getProcesosCognitivos().toArray());
    		//rangos de edad
    		//form.setEdadTipicaAsArray(rangosEdad);
    		form.setEdadTipicaAsArray(usoeducativoSession.getRangosEdad().toArray());
    		//idioma
    		//form.setIdiomasAsArray(idiomas);
    		form.setIdiomasAsArray(usoeducativoSession.getIdiomas().toArray());
    		//descripciones
    		//form.setDescripcionesAsArray(descripciones);
    		form.setDescripcionesAsArray(usoeducativoSession.getDescripciones().toArray());
    		//form.setDescripcionesCatAsArray(descripcionesCat);
    		form.setDescripcionesCatAsArray(usoeducativoSession.getDescripcionesCat().toArray());
    		//descripcion tiempo de aprendizaje
    		//form.setTiempoApDescripcionAsArray(tiempoApDescripcion);
    		form.setTiempoApDescripcionAsArray(usoeducativoSession.getTiempoApDescripcion().toArray());
    		
 		  	//form.setAnyo(anyos);
    		form.setAnyo(usoeducativoSession.getAnyos());
		  	//form.setMes(meses);
    		form.setMes(usoeducativoSession.getMeses());
		  	//form.setDia(dias);
    		form.setDia(usoeducativoSession.getDias());
		  	//form.setHora(horas);
    		form.setHora(usoeducativoSession.getHoras());
		  	//form.setMinutos(minutos);
    		form.setMinutos(usoeducativoSession.getMinutos());
		  	//form.setSegundosP1(segundosP1);
    		form.setSegundosP1(usoeducativoSession.getSegundosP1());
		  	//form.setSegundosP2(segundosP2);
    		form.setSegundosP2(usoeducativoSession.getSegundosP2());
	
			
			//añadimos un campo de idioma vacio
			Object[] aEdadTipica=form.getEdadTipicaAsArray();
			int nuevoTamano = aEdadTipica.length +1;
			Object[] newAEdadTipica = new Object[nuevoTamano];
			RangoEdadVO edadTipicaVO=new RangoEdadVO();
			LangStringVO[] aLangString=new LangStringVO[1];
			LangStringVO langString = new LangStringVO();
			langString.setIdioma("");
			langString.setTexto("");
			aLangString[0]= langString;
			edadTipicaVO.setTextos(aLangString);
			for(int i = 0; i<aEdadTipica.length;i++)
				newAEdadTipica[i]=aEdadTipica[i];
			newAEdadTipica[nuevoTamano-1]= edadTipicaVO;
			   
			form.setEdadTipicaAsArray(newAEdadTipica);
		
			 //cargar ayuda contextual    
			 cargarAyuda(usoeducativoSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
			
		}catch (ArrayIndexOutOfBoundsException ai) {
			logger
			.warn("Warning en catalogadorWeb, categoría Tecnica, metodo anadirEdadTipica " + ai);
		}catch (Exception e) {
			logger
			.error("Error en Servicio de catalogacionWeb, categoría Tecnica, metodo anadirEdadTipica " + e);
//			throw new Exception("tecnica",e);
			saveErrorMessage(request, " Error en Servicio de catalogacionWeb, categoría Tecnica, metodo anadirEdadTipica ");
		}     
     }




    /**
     * @see es.pode.catalogadorWeb.presentacion.categoriasAvanzado.usoeducativo.detalleUsoEducativo.DetalleUsoEduController#eliminarEdadTipica(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.usoeducativo.detalleUsoEducativo.EliminarEdadTipicaForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void eliminarEdadTipica(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.usoeducativo.detalleUsoEducativo.EliminarEdadTipicaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
    		
    		String source=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES) ;//esquemaDeMetadatos
    		
    		Object valor = request.getSession().getAttribute("form");
    		
    		if (valor instanceof DetalleUsoEduFormImpl) {
    			DetalleUsoEduFormImpl formulario= (DetalleUsoEduFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof DetalleUsoEducativoSubmitFormImpl){
    			DetalleUsoEducativoSubmitFormImpl formulario= (DetalleUsoEducativoSubmitFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoValidoVolverFormImpl){
    			UsoEducativoValidoVolverFormImpl formulario= (UsoEducativoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoNoValidoVolverFormImpl){
    			UsoEducativoNoValidoVolverFormImpl formulario= (UsoEducativoNoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
     		
    		int[] longitudTextosDesc = new int[form.getDescripciones().size()];
    		for(int i= 0;i< form.getDescripciones().size();i++){
    			longitudTextosDesc[i]=((DescripcionVO)(form.getDescripcionesAsArray()[i])).getTextos().length;
    		}
    		int[] longitudTextosRangosEdad = new int[form.getEdadTipica().size()];
    		for(int i= 0;i< form.getEdadTipica().size();i++){
    			longitudTextosRangosEdad[i]=((RangoEdadVO)(form.getEdadTipicaAsArray()[i])).getTextos().length;
    		}

    		int longitudDescripciones=form.getDescripciones().size();
    		int longitudRangos=form.getEdadTipica().size();
    		int longitudIdiomas=form.getIdiomas().size();
    		int longitudTipoRecurso=form.getTipoRecurso().size();
    		int longitudRoles=form.getRolUsuario().size();
    		int longitudContextos=form.getContexto().size();
    		int longitudProcesos=form.getProcesoCognitivo().size();
    		int longitudTADesc=(((DescripcionVO[])form.getTiempoApDescripcionAsArray())[0]).getTextos().length;
    		
    		//borrar session
    		request.getSession().removeAttribute(UsoEducativoSession.SESSION_KEY);
    		cambioFormulario(request,longitudTextosDesc,longitudTextosRangosEdad,
    				longitudDescripciones,longitudRangos,longitudIdiomas,longitudTipoRecurso,longitudRoles,
    				longitudContextos,longitudProcesos,longitudTADesc,source);
    		//recoger session
    		UsoEducativoSession usoeducativoSession = this.getUsoEducativoSession(request);

    		//tipo recurso educativo
    		 //form.setTipoRecursoAsArray(tiposRecurso);
    		form.setTipoRecurso(usoeducativoSession.getTiposRecurso());
    		//rol o destinatario
    		//form.setRolUsuarioAsArray(roles);
    		form.setRolUsuarioAsArray(usoeducativoSession.getRoles().toArray());
    		//contextos
    		//form.setContextoAsArray(contextos);
    		form.setContextoAsArray(usoeducativoSession.getContextos().toArray());
    		//procesos cognitivos
    		//form.setProcesoCognitivoAsArray(procesosCognitivos);
    		form.setProcesoCognitivoAsArray(usoeducativoSession.getProcesosCognitivos().toArray());
    		//rangos de edad
    		//form.setEdadTipicaAsArray(rangosEdad);
    		form.setEdadTipicaAsArray(usoeducativoSession.getRangosEdad().toArray());
    		//idioma
    		//form.setIdiomasAsArray(idiomas);
    		form.setIdiomasAsArray(usoeducativoSession.getIdiomas().toArray());
    		//descripciones
    		//form.setDescripcionesAsArray(descripciones);
    		form.setDescripcionesAsArray(usoeducativoSession.getDescripciones().toArray());
    		//form.setDescripcionesCatAsArray(descripcionesCat);
    		form.setDescripcionesCatAsArray(usoeducativoSession.getDescripcionesCat().toArray());
    		//descripcion tiempo de aprendizaje
    		//form.setTiempoApDescripcionAsArray(tiempoApDescripcion);
    		form.setTiempoApDescripcionAsArray(usoeducativoSession.getTiempoApDescripcion().toArray());
    		
 		  	//form.setAnyo(anyos);
    		form.setAnyo(usoeducativoSession.getAnyos());
		  	//form.setMes(meses);
    		form.setMes(usoeducativoSession.getMeses());
		  	//form.setDia(dias);
    		form.setDia(usoeducativoSession.getDias());
		  	//form.setHora(horas);
    		form.setHora(usoeducativoSession.getHoras());
		  	//form.setMinutos(minutos);
    		form.setMinutos(usoeducativoSession.getMinutos());
		  	//form.setSegundosP1(segundosP1);
    		form.setSegundosP1(usoeducativoSession.getSegundosP1());
		  	//form.setSegundosP2(segundosP2);
    		form.setSegundosP2(usoeducativoSession.getSegundosP2());
    		
            //posicion del identificador que se quiere eliminar   
            String posicion = request.getAttribute("posicion").toString();
            int posicionInt = Integer.parseInt(posicion);
            RangoEdadVO[] aEdadTipica = (RangoEdadVO[]) form.getEdadTipicaAsArray();
            RangoEdadVO[] nuevoEdadTipica = new RangoEdadVO[aEdadTipica.length -1];
	    	//añado al nuevo array de edadTipica o rangos de edad todos los rangos menos el que 
	    	//queremos eliminar
	    	for (int i = 0; i<nuevoEdadTipica.length;i++){
	    		if (i < posicionInt)
	    			nuevoEdadTipica[i] = aEdadTipica[i];
	    		else
	    			nuevoEdadTipica[i] = aEdadTipica[i+1]; 
	    	}
	    	form.setEdadTipicaAsArray(nuevoEdadTipica);
	    	//act session
	    	usoeducativoSession.setRangosEdad(Arrays.asList(nuevoEdadTipica));
    	
	    	 //cargar ayuda contextual    
			 cargarAyuda(usoeducativoSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
	    	
		}catch (ArrayIndexOutOfBoundsException ai) {
			logger
			.warn("Warning en catalogadorWeb, categoría Tecnica, metodo eliminarEdadTipica " + ai);
		}catch (Exception e) {
			logger
			.error("Error en Servicio de catalogacionWeb, categoría Tecnica, metodo eliminarEdadTipica " + e);
//			throw new Exception("tecnica",e);
			saveErrorMessage(request, " Error en Servicio de catalogacionWeb, categoría Tecnica, metodo eliminarEdadTipica ");
		}     
     }




    public final void anadirContenidoEdadTipica(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.usoeducativo.detalleUsoEducativo.AnadirContenidoEdadTipicaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
//    		AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES) ;//esquemaDeMetadatos
    		String source=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES) ;//esquemaDeMetadatos
    		
    		Object valor = request.getSession().getAttribute("form");
    		
    		if (valor instanceof DetalleUsoEduFormImpl) {
    			DetalleUsoEduFormImpl formulario= (DetalleUsoEduFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof DetalleUsoEducativoSubmitFormImpl){
    			DetalleUsoEducativoSubmitFormImpl formulario= (DetalleUsoEducativoSubmitFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoValidoVolverFormImpl){
    			UsoEducativoValidoVolverFormImpl formulario= (UsoEducativoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoNoValidoVolverFormImpl){
    			UsoEducativoNoValidoVolverFormImpl formulario= (UsoEducativoNoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
     		
    		int[] longitudTextosDesc = new int[form.getDescripciones().size()];
    		for(int i= 0;i< form.getDescripciones().size();i++){
    			longitudTextosDesc[i]=((DescripcionVO)(form.getDescripcionesAsArray()[i])).getTextos().length;
    		}
    		int[] longitudTextosRangosEdad = new int[form.getEdadTipica().size()];
    		for(int i= 0;i< form.getEdadTipica().size();i++){
    			longitudTextosRangosEdad[i]=((RangoEdadVO)(form.getEdadTipicaAsArray()[i])).getTextos().length;
    		}

    		int longitudDescripciones=form.getDescripciones().size();
    		int longitudRangos=form.getEdadTipica().size();
    		int longitudIdiomas=form.getIdiomas().size();
    		int longitudTipoRecurso=form.getTipoRecurso().size();
    		int longitudRoles=form.getRolUsuario().size();
    		int longitudContextos=form.getContexto().size();
    		int longitudProcesos=form.getProcesoCognitivo().size();
    		int longitudTADesc=(((DescripcionVO[])form.getTiempoApDescripcionAsArray())[0]).getTextos().length;
    		
    		//borrar session
    		request.getSession().removeAttribute(UsoEducativoSession.SESSION_KEY);
    		cambioFormulario(request,longitudTextosDesc,longitudTextosRangosEdad,
    				longitudDescripciones,longitudRangos,longitudIdiomas,longitudTipoRecurso,longitudRoles,
    				longitudContextos,longitudProcesos,longitudTADesc,source);
    		//recoger session
    		UsoEducativoSession usoeducativoSession = this.getUsoEducativoSession(request);
    		
    		//tipo recurso educativo
    		 //form.setTipoRecursoAsArray(tiposRecurso);
    		form.setTipoRecurso(usoeducativoSession.getTiposRecurso());
    		//rol o destinatario
    		//form.setRolUsuarioAsArray(roles);
    		form.setRolUsuarioAsArray(usoeducativoSession.getRoles().toArray());
    		//contextos
    		//form.setContextoAsArray(contextos);
    		form.setContextoAsArray(usoeducativoSession.getContextos().toArray());
    		//procesos cognitivos
    		//form.setProcesoCognitivoAsArray(procesosCognitivos);
    		form.setProcesoCognitivoAsArray(usoeducativoSession.getProcesosCognitivos().toArray());
    		//rangos de edad
    		//form.setEdadTipicaAsArray(rangosEdad);
    		form.setEdadTipicaAsArray(usoeducativoSession.getRangosEdad().toArray());
    		//idioma
    		//form.setIdiomasAsArray(idiomas);
    		form.setIdiomasAsArray(usoeducativoSession.getIdiomas().toArray());
    		//descripciones
    		//form.setDescripcionesAsArray(descripciones);
    		form.setDescripcionesAsArray(usoeducativoSession.getDescripciones().toArray());
    		//form.setDescripcionesCatAsArray(descripcionesCat);
    		form.setDescripcionesCatAsArray(usoeducativoSession.getDescripcionesCat().toArray());
    		//descripcion tiempo de aprendizaje
    		//form.setTiempoApDescripcionAsArray(tiempoApDescripcion);
    		form.setTiempoApDescripcionAsArray(usoeducativoSession.getTiempoApDescripcion().toArray());
    		
 		  	//form.setAnyo(anyos);
    		form.setAnyo(usoeducativoSession.getAnyos());
		  	//form.setMes(meses);
    		form.setMes(usoeducativoSession.getMeses());
		  	//form.setDia(dias);
    		form.setDia(usoeducativoSession.getDias());
		  	//form.setHora(horas);
    		form.setHora(usoeducativoSession.getHoras());
		  	//form.setMinutos(minutos);
    		form.setMinutos(usoeducativoSession.getMinutos());
		  	//form.setSegundosP1(segundosP1);
    		form.setSegundosP1(usoeducativoSession.getSegundosP1());
		  	//form.setSegundosP2(segundosP2);
    		form.setSegundosP2(usoeducativoSession.getSegundosP2());
    		
    		String posicion = request.getAttribute("posicion").toString();
    		int posicionInt = Integer.parseInt(posicion);
	    	//se recogen todos los rangos de edad
	    	RangoEdadVO[] edadArray = (RangoEdadVO[]) form.getEdadTipicaAsArray();
	    	//recogo los textos del rango de edad indicado y le añado un nuevo texto
	    	LangStringVO[] textos = edadArray[posicionInt].getTextos();
	    	LangStringVO[] nuevoTextos = new LangStringVO[textos.length+1];
	    	for(int i = 0 ; i < textos.length;i++){
	    		nuevoTextos[i]= textos[i];
	    	}
	    	LangStringVO nuevoTexto = new LangStringVO();
	    	nuevoTexto.setIdioma("");
	    	nuevoTexto.setTexto("");
	    	nuevoTextos[nuevoTextos.length-1] = nuevoTexto;
	    	edadArray[posicionInt].setTextos(nuevoTextos);
	    	form.setEdadTipicaAsArray(edadArray);
	    	//act session
	    	usoeducativoSession.setRangosEdad(Arrays.asList(edadArray));
    	
	    	 //cargar ayuda contextual    
			 cargarAyuda(usoeducativoSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
	    	
		}catch (ArrayIndexOutOfBoundsException ai) {
			logger
			.warn("Warning en catalogadorWeb, categoría Tecnica, metodo anadirContenidoEdadTipica " + ai);
		}catch (Exception e) {
			logger
			.error("Error en Servicio de catalogacionWeb, categoría Tecnica, metodo anadirContenidoEdadTipica " + e);
//			throw new Exception("tecnica",e);
			saveErrorMessage(request, " Error en Servicio de catalogacionWeb, categoría Tecnica, metodo anadirContenidoEdadTipica ");
		}     
     }


    
    /**
     * @see es.pode.catalogadorWeb.presentacion.categoriasAvanzado.usoeducativo.detalleUsoEducativo.DetalleUsoEduController#eliminarContenidoEdadTipica(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.usoeducativo.detalleUsoEducativo.EliminarContenidoEdadTipicaForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void eliminarContenidoEdadTipica(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.usoeducativo.detalleUsoEducativo.EliminarContenidoEdadTipicaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{

    		String source=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES) ;//esquemaDeMetadatos
    		
    		Object valor = request.getSession().getAttribute("form");
    		
    		if (valor instanceof DetalleUsoEduFormImpl) {
    			DetalleUsoEduFormImpl formulario= (DetalleUsoEduFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof DetalleUsoEducativoSubmitFormImpl){
    			DetalleUsoEducativoSubmitFormImpl formulario= (DetalleUsoEducativoSubmitFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoValidoVolverFormImpl){
    			UsoEducativoValidoVolverFormImpl formulario= (UsoEducativoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoNoValidoVolverFormImpl){
    			UsoEducativoNoValidoVolverFormImpl formulario= (UsoEducativoNoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
     		
    		int[] longitudTextosDesc = new int[form.getDescripciones().size()];
    		for(int i= 0;i< form.getDescripciones().size();i++){
    			longitudTextosDesc[i]=((DescripcionVO)(form.getDescripcionesAsArray()[i])).getTextos().length;
    		}
    		int[] longitudTextosRangosEdad = new int[form.getEdadTipica().size()];
    		for(int i= 0;i< form.getEdadTipica().size();i++){
    			longitudTextosRangosEdad[i]=((RangoEdadVO)(form.getEdadTipicaAsArray()[i])).getTextos().length;
    		}

    		int longitudDescripciones=form.getDescripciones().size();
    		int longitudRangos=form.getEdadTipica().size();
    		int longitudIdiomas=form.getIdiomas().size();
    		int longitudTipoRecurso=form.getTipoRecurso().size();
    		int longitudRoles=form.getRolUsuario().size();
    		int longitudContextos=form.getContexto().size();
    		int longitudProcesos=form.getProcesoCognitivo().size();
    		int longitudTADesc=(((DescripcionVO[])form.getTiempoApDescripcionAsArray())[0]).getTextos().length;
    		
    		//borrar session
    		request.getSession().removeAttribute(UsoEducativoSession.SESSION_KEY);
    		cambioFormulario(request,longitudTextosDesc,longitudTextosRangosEdad,
    				longitudDescripciones,longitudRangos,longitudIdiomas,longitudTipoRecurso,longitudRoles,
    				longitudContextos,longitudProcesos,longitudTADesc,source);
    		//recoger session
    		UsoEducativoSession usoeducativoSession = this.getUsoEducativoSession(request);
    		
    		//tipo recurso educativo
    		 //form.setTipoRecursoAsArray(tiposRecurso);
    		form.setTipoRecurso(usoeducativoSession.getTiposRecurso());
    		//rol o destinatario
    		//form.setRolUsuarioAsArray(roles);
    		form.setRolUsuarioAsArray(usoeducativoSession.getRoles().toArray());
    		//contextos
    		//form.setContextoAsArray(contextos);
    		form.setContextoAsArray(usoeducativoSession.getContextos().toArray());
    		//procesos cognitivos
    		//form.setProcesoCognitivoAsArray(procesosCognitivos);
    		form.setProcesoCognitivoAsArray(usoeducativoSession.getProcesosCognitivos().toArray());
    		//rangos de edad
    		//form.setEdadTipicaAsArray(rangosEdad);
    		form.setEdadTipicaAsArray(usoeducativoSession.getRangosEdad().toArray());
    		//idioma
    		//form.setIdiomasAsArray(idiomas);
    		form.setIdiomasAsArray(usoeducativoSession.getIdiomas().toArray());
    		//descripciones
    		//form.setDescripcionesAsArray(descripciones);
    		form.setDescripcionesAsArray(usoeducativoSession.getDescripciones().toArray());
    		//form.setDescripcionesCatAsArray(descripcionesCat);
    		form.setDescripcionesCatAsArray(usoeducativoSession.getDescripcionesCat().toArray());
    		//descripcion tiempo de aprendizaje
    		//form.setTiempoApDescripcionAsArray(tiempoApDescripcion);
    		form.setTiempoApDescripcionAsArray(usoeducativoSession.getTiempoApDescripcion().toArray());
    		
 		  	//form.setAnyo(anyos);
    		form.setAnyo(usoeducativoSession.getAnyos());
		  	//form.setMes(meses);
    		form.setMes(usoeducativoSession.getMeses());
		  	//form.setDia(dias);
    		form.setDia(usoeducativoSession.getDias());
		  	//form.setHora(horas);
    		form.setHora(usoeducativoSession.getHoras());
		  	//form.setMinutos(minutos);
    		form.setMinutos(usoeducativoSession.getMinutos());
		  	//form.setSegundosP1(segundosP1);
    		form.setSegundosP1(usoeducativoSession.getSegundosP1());
		  	//form.setSegundosP2(segundosP2);
    		form.setSegundosP2(usoeducativoSession.getSegundosP2());
    		
    		//posicion de contenido a eliminar   
	        String posicion = request.getAttribute("posicion").toString();
	        int posicionInt = Integer.parseInt(posicion);
			
			//posicion del rango de edad del que se quiere eliminar parte de su contenido
	        String posicionExt = request.getAttribute("posicionExterior").toString();
	        int posicionExtInt = Integer.parseInt(posicionExt);
	    	
	    	RangoEdadVO[] aEdadTipica = (RangoEdadVO[]) form.getEdadTipicaAsArray();
	    	RangoEdadVO[] rangosEdad = (RangoEdadVO[])usoeducativoSession.getRangosEdad()
	    			.toArray(new RangoEdadVO[usoeducativoSession.getRangosEdad().size()]);
	    	LangStringVO[] contenido = rangosEdad[posicionExtInt].getTextos();
	    	LangStringVO[] nuevoContenido = new LangStringVO[contenido.length -1];
	    	//añado al nuevo array de contenidos todos los contenidos menos el que 
	    	//queremos eliminar
	    	for (int i = 0; i<nuevoContenido.length;i++){
	    		if (i < posicionInt)
	    			nuevoContenido[i] = contenido[i];
	    		else
	    			nuevoContenido[i] = contenido[i+1]; 
	    	}
	    	aEdadTipica[posicionExtInt].setTextos(nuevoContenido);
	    	form.setEdadTipicaAsArray(aEdadTipica);
	    	usoeducativoSession.setRangosEdad(Arrays.asList(aEdadTipica));
		
	    	 //cargar ayuda contextual    
			 cargarAyuda(usoeducativoSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
	    	
		}catch (ArrayIndexOutOfBoundsException ai) {
			logger
			.warn("Warning en catalogadorWeb, categoría Tecnica, metodo eliminarContenidoEdadTipica " + ai);
		}catch (Exception e) {
			logger
			.error("Error en Servicio de catalogacionWeb, categoría Tecnica, metodo eliminarContenidoEdadTipica " + e);
//			throw new Exception("tecnica",e);
			saveErrorMessage(request, " Error en Servicio de catalogacionWeb, categoría Tecnica, metodo eliminarContenidoEdadTipica ");
		}     
     }



    /**
     * @see es.pode.catalogadorWeb.presentacion.categoriasAvanzado.usoeducativo.detalleUsoEducativo.DetalleUsoEduController#anadirDescTiempoAp(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.usoeducativo.detalleUsoEducativo.AnadirDescTiempoApForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void anadirDescTiempoAp(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.usoeducativo.detalleUsoEducativo.AnadirDescTiempoApForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
    	
    		String source=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES) ;//esquemaDeMetadatos
    		
    		Object valor = request.getSession().getAttribute("form");
    		
    		if (valor instanceof DetalleUsoEduFormImpl) {
    			DetalleUsoEduFormImpl formulario= (DetalleUsoEduFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof DetalleUsoEducativoSubmitFormImpl){
    			DetalleUsoEducativoSubmitFormImpl formulario= (DetalleUsoEducativoSubmitFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoValidoVolverFormImpl){
    			UsoEducativoValidoVolverFormImpl formulario= (UsoEducativoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoNoValidoVolverFormImpl){
    			UsoEducativoNoValidoVolverFormImpl formulario= (UsoEducativoNoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}

    		int[] longitudTextosDesc = new int[form.getDescripciones().size()];
    		for(int i= 0;i< form.getDescripciones().size();i++){
    			longitudTextosDesc[i]=((DescripcionVO)(form.getDescripcionesAsArray()[i])).getTextos().length;
    		}
    		int[] longitudTextosRangosEdad = new int[form.getEdadTipica().size()];
    		for(int i= 0;i< form.getEdadTipica().size();i++){
    			longitudTextosRangosEdad[i]=((RangoEdadVO)(form.getEdadTipicaAsArray()[i])).getTextos().length;
    		}

    		int longitudDescripciones=form.getDescripciones().size();
    		int longitudRangos=form.getEdadTipica().size();
    		int longitudIdiomas=form.getIdiomas().size();
    		int longitudTipoRecurso=form.getTipoRecurso().size();
    		int longitudRoles=form.getRolUsuario().size();
    		int longitudContextos=form.getContexto().size();
    		int longitudProcesos=form.getProcesoCognitivo().size();
    		int longitudTADesc=(((DescripcionVO[])form.getTiempoApDescripcionAsArray())[0]).getTextos().length;
    		
    		//borramos session
    		request.getSession().removeAttribute(UsoEducativoSession.SESSION_KEY);
    		cambioFormulario(request,longitudTextosDesc,longitudTextosRangosEdad,
    				longitudDescripciones,longitudRangos,longitudIdiomas,longitudTipoRecurso,longitudRoles,
    				longitudContextos,longitudProcesos,longitudTADesc,source);
    		//recogemos session
    		UsoEducativoSession usoeducativoSession = this.getUsoEducativoSession(request);
    		
    		//tipo recurso educativo
//    		form.setTipoRecursoAsArray(tiposRecurso);
    		form.setTipoRecurso(usoeducativoSession.getTiposRecurso());
    		//rol o destinatario
    		//form.setRolUsuarioAsArray(roles);
    		form.setRolUsuarioAsArray(usoeducativoSession.getRoles().toArray());
    		//contextos
    		//form.setContextoAsArray(contextos);
    		form.setContextoAsArray(usoeducativoSession.getContextos().toArray());
    		//procesos cognitivos
    		//form.setProcesoCognitivoAsArray(procesosCognitivos);
    		form.setProcesoCognitivoAsArray(usoeducativoSession.getProcesosCognitivos().toArray());
    		//rangos de edad
    		//form.setEdadTipicaAsArray(rangosEdad);
    		form.setEdadTipicaAsArray(usoeducativoSession.getRangosEdad().toArray());
    		//idioma
    		//form.setIdiomasAsArray(idiomas);
    		form.setIdiomasAsArray(usoeducativoSession.getIdiomas().toArray());
    		//descripciones
    		//form.setDescripcionesAsArray(descripciones);
    		form.setDescripcionesAsArray(usoeducativoSession.getDescripciones().toArray());
    		//form.setDescripcionesCatAsArray(descripcionesCat);
    		form.setDescripcionesCatAsArray(usoeducativoSession.getDescripcionesCat().toArray());
    		//descripcion tiempo de aprendizaje
    		//form.setTiempoApDescripcionAsArray(tiempoApDescripcion);
    		form.setTiempoApDescripcionAsArray(usoeducativoSession.getTiempoApDescripcion().toArray());
    		
 		  	//form.setAnyo(anyos);
    		form.setAnyo(usoeducativoSession.getAnyos());
		  	//form.setMes(meses);
    		form.setMes(usoeducativoSession.getMeses());
		  	//form.setDia(dias);
    		form.setDia(usoeducativoSession.getDias());
		  	//form.setHora(horas);
    		form.setHora(usoeducativoSession.getHoras());
		  	//form.setMinutos(minutos);
    		form.setMinutos(usoeducativoSession.getMinutos());
		  	//form.setSegundosP1(segundosP1);
    		form.setSegundosP1(usoeducativoSession.getSegundosP1());
		  	//form.setSegundosP2(segundosP2);
    		form.setSegundosP2(usoeducativoSession.getSegundosP2());
    		
			Object[] aTADesc=form.getTiempoApDescripcionAsArray();
			DescripcionVO taDescVO = (DescripcionVO)aTADesc[0];
			LangStringVO[] textos = taDescVO.getTextos();
			int nuevoTamano = textos.length +1;
			LangStringVO[] newTextos = new LangStringVO[nuevoTamano];
			for(int i=0;i<textos.length;i++)
				newTextos[i]=textos[i];
			LangStringVO langTADesc=new LangStringVO();
			langTADesc.setIdioma("");
			langTADesc.setTexto("");
			newTextos[nuevoTamano-1]=langTADesc;
			
			taDescVO.setTextos(newTextos);
			aTADesc[0]=taDescVO;
			form.setTiempoApDescripcionAsArray(aTADesc);
			//act session
			usoeducativoSession.setTiempoApDescripcion(Arrays.asList(aTADesc));
    	
			 //cargar ayuda contextual    
			 cargarAyuda(usoeducativoSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
			
		}catch (ArrayIndexOutOfBoundsException ai) {
			logger
			.warn("Warning en catalogadorWeb, categoría Tecnica, metodo anadirDescTiempoAp " + ai);
		}catch (Exception e) {
			logger
			.error("Error en Servicio de catalogacionWeb, categoría Tecnica, metodo anadirDescTiempoAp " + e);
//			throw new Exception("tecnica",e);
			saveErrorMessage(request, " Error en Servicio de catalogacionWeb, categoría Tecnica, metodo anadirDescTiempoAp ");
		}     
     }



    /**
     * @see es.pode.catalogadorWeb.presentacion.categoriasAvanzado.usoeducativo.detalleUsoEducativo.DetalleUsoEduController#eliminarDescTiempoAp(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.usoeducativo.detalleUsoEducativo.EliminarDescTiempoApForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void eliminarDescTiempoAp(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.usoeducativo.detalleUsoEducativo.EliminarDescTiempoApForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
    	
    		String source=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES) ;//esquemaDeMetadatos
    		
    		Object valor = request.getSession().getAttribute("form");
    		
    		if (valor instanceof DetalleUsoEduFormImpl) {
    			DetalleUsoEduFormImpl formulario= (DetalleUsoEduFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof DetalleUsoEducativoSubmitFormImpl){
    			DetalleUsoEducativoSubmitFormImpl formulario= (DetalleUsoEducativoSubmitFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoValidoVolverFormImpl){
    			UsoEducativoValidoVolverFormImpl formulario= (UsoEducativoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoNoValidoVolverFormImpl){
    			UsoEducativoNoValidoVolverFormImpl formulario= (UsoEducativoNoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}

    		
    		int[] longitudTextosDesc = new int[form.getDescripciones().size()];
    		for(int i= 0;i< form.getDescripciones().size();i++){
    			longitudTextosDesc[i]=((DescripcionVO)(form.getDescripcionesAsArray()[i])).getTextos().length;
    		}
    		int[] longitudTextosRangosEdad = new int[form.getEdadTipica().size()];
    		for(int i= 0;i< form.getEdadTipica().size();i++){
    			longitudTextosRangosEdad[i]=((RangoEdadVO)(form.getEdadTipicaAsArray()[i])).getTextos().length;
    		}

    		int longitudDescripciones=form.getDescripciones().size();
    		int longitudRangos=form.getEdadTipica().size();
    		int longitudIdiomas=form.getIdiomas().size();
    		int longitudTipoRecurso=form.getTipoRecurso().size();
    		int longitudRoles=form.getRolUsuario().size();
    		int longitudContextos=form.getContexto().size();
    		int longitudProcesos=form.getProcesoCognitivo().size();
    		int longitudTADesc=(((DescripcionVO[])form.getTiempoApDescripcionAsArray())[0]).getTextos().length;
    		
    		//borrar session
    		request.getSession().removeAttribute(UsoEducativoSession.SESSION_KEY);
    		cambioFormulario(request,longitudTextosDesc,longitudTextosRangosEdad,
    				longitudDescripciones,longitudRangos,longitudIdiomas,longitudTipoRecurso,longitudRoles,
    				longitudContextos,longitudProcesos,longitudTADesc,source);
    		//recuperar session
    		UsoEducativoSession usoeducativoSession = this.getUsoEducativoSession(request);
    		
    		//tipo recurso educativo
    		 //form.setTipoRecursoAsArray(tiposRecurso);
    		form.setTipoRecurso(usoeducativoSession.getTiposRecurso());
    		//rol o destinatario
    		//form.setRolUsuarioAsArray(roles);
    		form.setRolUsuarioAsArray(usoeducativoSession.getRoles().toArray());
    		//contextos
    		//form.setContextoAsArray(contextos);
    		form.setContextoAsArray(usoeducativoSession.getContextos().toArray());
    		//procesos cognitivos
    		//form.setProcesoCognitivoAsArray(procesosCognitivos);
    		form.setProcesoCognitivoAsArray(usoeducativoSession.getProcesosCognitivos().toArray());
    		//rangos de edad
    		//form.setEdadTipicaAsArray(rangosEdad);
    		form.setEdadTipicaAsArray(usoeducativoSession.getRangosEdad().toArray());
    		//idioma
    		//form.setIdiomasAsArray(idiomas);
    		form.setIdiomasAsArray(usoeducativoSession.getIdiomas().toArray());
    		//descripciones
    		//form.setDescripcionesAsArray(descripciones);
    		form.setDescripcionesAsArray(usoeducativoSession.getDescripciones().toArray());
    		//form.setDescripcionesCatAsArray(descripcionesCat);
    		form.setDescripcionesCatAsArray(usoeducativoSession.getDescripcionesCat().toArray());
    		//descripcion tiempo de aprendizaje
    		//form.setTiempoApDescripcionAsArray(tiempoApDescripcion);
    		form.setTiempoApDescripcionAsArray(usoeducativoSession.getTiempoApDescripcion().toArray());
    		
 		  	//form.setAnyo(anyos);
    		form.setAnyo(usoeducativoSession.getAnyos());
		  	//form.setMes(meses);
    		form.setMes(usoeducativoSession.getMeses());
		  	//form.setDia(dias);
    		form.setDia(usoeducativoSession.getDias());
		  	//form.setHora(horas);
    		form.setHora(usoeducativoSession.getHoras());
		  	//form.setMinutos(minutos);
    		form.setMinutos(usoeducativoSession.getMinutos());
		  	//form.setSegundosP1(segundosP1);
    		form.setSegundosP1(usoeducativoSession.getSegundosP1());
		  	//form.setSegundosP2(segundosP2);
    		form.setSegundosP2(usoeducativoSession.getSegundosP2());
    		
	        //posicion de la descripcion que se quiere eliminar   
	       	String posicion = request.getAttribute("posicion").toString();
	       	int posicionInt = Integer.parseInt(posicion);
	    	DescripcionVO[] aTADesc=(DescripcionVO[]) form.getTiempoApDescripcionAsArray();
	    	LangStringVO[] langString = aTADesc[0].getTextos();
	    	LangStringVO[] nuevolangString = new LangStringVO[langString.length -1];
	     	//añado al nuevo array de descripcion con todas las descripciones menos la que 
	     	//queremos eliminar
	     	for (int i = 0; i<nuevolangString.length;i++){
	     		if (i < posicionInt)
	     			nuevolangString[i] = langString[i];
	     		else
	     			nuevolangString[i] = langString[i+1]; 
	     	}
	     	DescripcionVO taDescVO = new DescripcionVO();
	     	taDescVO.setTextos(nuevolangString);
	     	aTADesc[0]= taDescVO;
	     	form.setTiempoApDescripcionAsArray(aTADesc);
	     	//act session
	     	usoeducativoSession.setTiempoApDescripcion(Arrays.asList(aTADesc));
	     	
	     	 //cargar ayuda contextual    
			 cargarAyuda(usoeducativoSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
	     	
		}catch (ArrayIndexOutOfBoundsException ai) {
			logger
			.warn("Warning en catalogadorWeb, categoría Tecnica, metodo eliminarDescTiempoAp " + ai);
		}catch (Exception e) {
			logger
			.error("Error en Servicio de catalogacionWeb, categoría Tecnica, metodo eliminarDescTiempoAp " + e);
//			throw new Exception("tecnica",e);
			saveErrorMessage(request, " Error en Servicio de catalogacionWeb, categoría Tecnica, metodo eliminarDescTiempoAp ");
		}   
     }



    /**
     * @see es.pode.catalogadorWeb.presentacion.categoriasAvanzado.usoeducativo.detalleUsoEducativo.DetalleUsoEduController#anadirDescripcion(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.usoeducativo.detalleUsoEducativo.AnadirDescripcionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void anadirDescripcion(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.usoeducativo.detalleUsoEducativo.AnadirDescripcionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
	
		String source=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES) ;//esquemaDeMetadatos
		Object valor = request.getSession().getAttribute("form");
		
		try{
    		if (valor instanceof DetalleUsoEduFormImpl) {
    			DetalleUsoEduFormImpl formulario= (DetalleUsoEduFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof DetalleUsoEducativoSubmitFormImpl){
    			DetalleUsoEducativoSubmitFormImpl formulario= (DetalleUsoEducativoSubmitFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoValidoVolverFormImpl){
    			UsoEducativoValidoVolverFormImpl formulario= (UsoEducativoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoNoValidoVolverFormImpl){
    			UsoEducativoNoValidoVolverFormImpl formulario= (UsoEducativoNoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
			
			int[] longitudTextosDesc = new int[form.getDescripciones().size()];
			for(int i= 0;i< form.getDescripciones().size();i++){
				longitudTextosDesc[i]=((DescripcionVO)(form.getDescripcionesAsArray()[i])).getTextos().length;
			}
			int[] longitudTextosRangosEdad = new int[form.getEdadTipica().size()];
			for(int i= 0;i< form.getEdadTipica().size();i++){
				longitudTextosRangosEdad[i]=((RangoEdadVO)(form.getEdadTipicaAsArray()[i])).getTextos().length;
			}
	
			int longitudDescripciones=form.getDescripciones().size();
			int longitudRangos=form.getEdadTipica().size();
			int longitudIdiomas=form.getIdiomas().size();
			int longitudTipoRecurso=form.getTipoRecurso().size();
			int longitudRoles=form.getRolUsuario().size();
			int longitudContextos=form.getContexto().size();
			int longitudProcesos=form.getProcesoCognitivo().size();
			int longitudTADesc=(((DescripcionVO[])form.getTiempoApDescripcionAsArray())[0]).getTextos().length;
			
			//borrar session
			request.getSession().removeAttribute(UsoEducativoSession.SESSION_KEY);
			cambioFormulario(request,longitudTextosDesc,longitudTextosRangosEdad,
					longitudDescripciones,longitudRangos,longitudIdiomas,longitudTipoRecurso,longitudRoles,
					longitudContextos,longitudProcesos,longitudTADesc,source);
			//recuperar session
			UsoEducativoSession usoeducativoSession = this.getUsoEducativoSession(request);
			
			//tipo recurso educativo
			 //form.setTipoRecursoAsArray(tiposRecurso);
    		form.setTipoRecurso(usoeducativoSession.getTiposRecurso());
    		//rol o destinatario
    		//form.setRolUsuarioAsArray(roles);
    		form.setRolUsuarioAsArray(usoeducativoSession.getRoles().toArray());
    		//contextos
    		//form.setContextoAsArray(contextos);
    		form.setContextoAsArray(usoeducativoSession.getContextos().toArray());
    		//procesos cognitivos
    		//form.setProcesoCognitivoAsArray(procesosCognitivos);
    		form.setProcesoCognitivoAsArray(usoeducativoSession.getProcesosCognitivos().toArray());
    		//rangos de edad
    		//form.setEdadTipicaAsArray(rangosEdad);
    		form.setEdadTipicaAsArray(usoeducativoSession.getRangosEdad().toArray());
    		//idioma
    		//form.setIdiomasAsArray(idiomas);
    		form.setIdiomasAsArray(usoeducativoSession.getIdiomas().toArray());
    		//descripciones
    		//form.setDescripcionesAsArray(descripciones);
    		form.setDescripcionesAsArray(usoeducativoSession.getDescripciones().toArray());
    		//form.setDescripcionesCatAsArray(descripcionesCat);
    		form.setDescripcionesCatAsArray(usoeducativoSession.getDescripcionesCat().toArray());
    		//descripcion tiempo de aprendizaje
    		//form.setTiempoApDescripcionAsArray(tiempoApDescripcion);
    		form.setTiempoApDescripcionAsArray(usoeducativoSession.getTiempoApDescripcion().toArray());
    		
 		  	//form.setAnyo(anyos);
    		form.setAnyo(usoeducativoSession.getAnyos());
		  	//form.setMes(meses);
    		form.setMes(usoeducativoSession.getMeses());
		  	//form.setDia(dias);
    		form.setDia(usoeducativoSession.getDias());
		  	//form.setHora(horas);
    		form.setHora(usoeducativoSession.getHoras());
		  	//form.setMinutos(minutos);
    		form.setMinutos(usoeducativoSession.getMinutos());
		  	//form.setSegundosP1(segundosP1);
    		form.setSegundosP1(usoeducativoSession.getSegundosP1());
		  	//form.setSegundosP2(segundosP2);
    		form.setSegundosP2(usoeducativoSession.getSegundosP2());
			
			//añadimos un campo de idioma vacio
			Object[] aDescripciones=form.getDescripcionesAsArray();
			int nuevoTamano = aDescripciones.length +1;
			Object[] newADescripciones = new Object[nuevoTamano];
			DescripcionVO descripcionVO=new DescripcionVO();
			LangStringVO[] aLangString=new LangStringVO[1];
			LangStringVO langString = new LangStringVO();
			langString.setIdioma("");
			langString.setTexto("");
			aLangString[0]= langString;
			descripcionVO.setTextos(aLangString);
			
			DescripcionUsoEdu[][] aDescripcionUE= (DescripcionUsoEdu[][]) form.getDescripcionesCatAsArray();
			DescripcionUsoEdu[][] aux= new DescripcionUsoEdu[nuevoTamano][];
			
			for(int i = 0; i<aDescripciones.length;i++)
			{
				newADescripciones[i]=aDescripciones[i];
				aux[i]= aDescripcionUE[i];
			}
			newADescripciones[nuevoTamano-1]= descripcionVO;
			form.setDescripcionesAsArray(newADescripciones);
			//act session
			usoeducativoSession.setDescripciones(Arrays.asList(newADescripciones));
			
			aux[nuevoTamano-1]= new DescripcionUsoEdu[1];
			aux[nuevoTamano-1][0]= DescripcionUsoEdu.getInstance();
			form.setDescripcionesCatAsArray(aux);
			//act session
			usoeducativoSession.setDescripcionesCat(Arrays.asList(aux));

			 //cargar ayuda contextual    
			 cargarAyuda(usoeducativoSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
			
		}catch (ArrayIndexOutOfBoundsException ai) {
			logger
			.warn("Warning en catalogadorWeb, categoría Tecnica, metodo anadirDescripcion " + ai);
		}catch (Exception e) {
			logger
			.error("Error en Servicio de catalogacionWeb, categoría Tecnica, metodo anadirDescripcion " + e);
//			throw new Exception("tecnica",e);
			saveErrorMessage(request, " Error en Servicio de catalogacionWeb, categoría Tecnica, metodo anadirDescripcion ");
		}   
	 }



    /**
     * @see es.pode.catalogadorWeb.presentacion.categoriasAvanzado.usoeducativo.detalleUsoEducativo.DetalleUsoEduController#eliminarDescripcion(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.usoeducativo.detalleUsoEducativo.EliminarDescripcionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void eliminarDescripcion(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.usoeducativo.detalleUsoEducativo.EliminarDescripcionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
    		
    		String source=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES) ;//esquemaDeMetadatos
    		
    		Object valor = request.getSession().getAttribute("form");
    		
    		if (valor instanceof DetalleUsoEduFormImpl) {
    			DetalleUsoEduFormImpl formulario= (DetalleUsoEduFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof DetalleUsoEducativoSubmitFormImpl){
    			DetalleUsoEducativoSubmitFormImpl formulario= (DetalleUsoEducativoSubmitFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoValidoVolverFormImpl){
    			UsoEducativoValidoVolverFormImpl formulario= (UsoEducativoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoNoValidoVolverFormImpl){
    			UsoEducativoNoValidoVolverFormImpl formulario= (UsoEducativoNoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		
    		int[] longitudTextosDesc = new int[form.getDescripciones().size()];
    		for(int i= 0;i< form.getDescripciones().size();i++){
    			longitudTextosDesc[i]=((DescripcionVO)(form.getDescripcionesAsArray()[i])).getTextos().length;
    		}
    		int[] longitudTextosRangosEdad = new int[form.getEdadTipica().size()];
    		for(int i= 0;i< form.getEdadTipica().size();i++){
    			longitudTextosRangosEdad[i]=((RangoEdadVO)(form.getEdadTipicaAsArray()[i])).getTextos().length;
    		}

    		int longitudDescripciones=form.getDescripciones().size();
    		int longitudRangos=form.getEdadTipica().size();
    		int longitudIdiomas=form.getIdiomas().size();
    		int longitudTipoRecurso=form.getTipoRecurso().size();
    		int longitudRoles=form.getRolUsuario().size();
    		int longitudContextos=form.getContexto().size();
    		int longitudProcesos=form.getProcesoCognitivo().size();
    		int longitudTADesc=(((DescripcionVO[])form.getTiempoApDescripcionAsArray())[0]).getTextos().length;
    		
    		//borrar session
    		request.getSession().removeAttribute(UsoEducativoSession.SESSION_KEY);
    		cambioFormulario(request,longitudTextosDesc,longitudTextosRangosEdad,
    				longitudDescripciones,longitudRangos,longitudIdiomas,longitudTipoRecurso,longitudRoles,
    				longitudContextos,longitudProcesos,longitudTADesc,source);
    		//recoger session
    		UsoEducativoSession usoeducativoSession = this.getUsoEducativoSession(request);
    		
    		//tipo recurso educativo
    		 //form.setTipoRecursoAsArray(tiposRecurso);
    		form.setTipoRecurso(usoeducativoSession.getTiposRecurso());
    		//rol o destinatario
    		//form.setRolUsuarioAsArray(roles);
    		form.setRolUsuarioAsArray(usoeducativoSession.getRoles().toArray());
    		//contextos
    		//form.setContextoAsArray(contextos);
    		form.setContextoAsArray(usoeducativoSession.getContextos().toArray());
    		//procesos cognitivos
    		//form.setProcesoCognitivoAsArray(procesosCognitivos);
    		form.setProcesoCognitivoAsArray(usoeducativoSession.getProcesosCognitivos().toArray());
    		//rangos de edad
    		//form.setEdadTipicaAsArray(rangosEdad);
    		form.setEdadTipicaAsArray(usoeducativoSession.getRangosEdad().toArray());
    		//idioma
    		//form.setIdiomasAsArray(idiomas);
    		form.setIdiomasAsArray(usoeducativoSession.getIdiomas().toArray());
    		//descripciones
    		//form.setDescripcionesAsArray(descripciones);
    		form.setDescripcionesAsArray(usoeducativoSession.getDescripciones().toArray());
    		//form.setDescripcionesCatAsArray(descripcionesCat);
    		form.setDescripcionesCatAsArray(usoeducativoSession.getDescripcionesCat().toArray());
    		//descripcion tiempo de aprendizaje
    		//form.setTiempoApDescripcionAsArray(tiempoApDescripcion);
    		form.setTiempoApDescripcionAsArray(usoeducativoSession.getTiempoApDescripcion().toArray());
    		
 		  	//form.setAnyo(anyos);
    		form.setAnyo(usoeducativoSession.getAnyos());
		  	//form.setMes(meses);
    		form.setMes(usoeducativoSession.getMeses());
		  	//form.setDia(dias);
    		form.setDia(usoeducativoSession.getDias());
		  	//form.setHora(horas);
    		form.setHora(usoeducativoSession.getHoras());
		  	//form.setMinutos(minutos);
    		form.setMinutos(usoeducativoSession.getMinutos());
		  	//form.setSegundosP1(segundosP1);
    		form.setSegundosP1(usoeducativoSession.getSegundosP1());
		  	//form.setSegundosP2(segundosP2);
    		form.setSegundosP2(usoeducativoSession.getSegundosP2());
    		
            //posicion del identificador que se quiere eliminar   
            String posicion = request.getAttribute("posicion").toString();
            int posicionInt = Integer.parseInt(posicion);
	    	DescripcionVO[] aDescripciones = (DescripcionVO[]) form.getDescripcionesAsArray();
	    	DescripcionVO[] nuevoDescripciones = new DescripcionVO[aDescripciones.length -1];
	    	
	    	DescripcionUsoEdu[][] aDescripcionesUE = (DescripcionUsoEdu[][]) form.getDescripcionesCatAsArray(); 
	    	DescripcionUsoEdu[][] auxUE = new DescripcionUsoEdu[aDescripciones.length -1][];
	    	
	    	//añado al nuevo array de descripciones todas las descripciones menos la que 
	    	//queremos eliminar
	    	for (int i = 0; i<nuevoDescripciones.length;i++){
	    		if (i < posicionInt)
	    		{
	    			nuevoDescripciones[i] = aDescripciones[i];
	    			auxUE[i]= aDescripcionesUE[i];
	    		}
	    		else
	    		{
	    			nuevoDescripciones[i] = aDescripciones[i+1];
	    			auxUE[i]= aDescripcionesUE[i+1];
	    		}
	    	}
	    	form.setDescripcionesAsArray(nuevoDescripciones);
	    	//act session
	    	usoeducativoSession.setDescripciones(Arrays.asList(nuevoDescripciones));
    	
	    	 //cargar ayuda contextual    
			 cargarAyuda(usoeducativoSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
	    	
		}catch (ArrayIndexOutOfBoundsException ai) {
			logger
			.warn("Warning en catalogadorWeb, categoría Tecnica, metodo eliminarDescripcion " + ai);
		}catch (Exception e) {
			logger
			.error("Error en Servicio de catalogacionWeb, categoría Tecnica, metodo eliminarDescripcion " + e);
//			throw new Exception("tecnica",e);
			saveErrorMessage(request, " Error en Servicio de catalogacionWeb, categoría Tecnica, metodo eliminarDescripcion ");
		}
     }




    /**
     * @see es.pode.catalogadorWeb.presentacion.categoriasAvanzado.usoeducativo.detalleUsoEducativo.DetalleUsoEduController#anadirContenidoDescripcion(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.usoeducativo.detalleUsoEducativo.AnadirContenidoDescripcionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void anadirContenidoDescripcion(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.usoeducativo.detalleUsoEducativo.AnadirContenidoDescripcionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
    	
    		String source=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES) ;//esquemaDeMetadatos
    		
    		Object valor = request.getSession().getAttribute("form");
    		
    		if (valor instanceof DetalleUsoEduFormImpl) {
    			DetalleUsoEduFormImpl formulario= (DetalleUsoEduFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof DetalleUsoEducativoSubmitFormImpl){
    			DetalleUsoEducativoSubmitFormImpl formulario= (DetalleUsoEducativoSubmitFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoValidoVolverFormImpl){
    			UsoEducativoValidoVolverFormImpl formulario= (UsoEducativoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoNoValidoVolverFormImpl){
    			UsoEducativoNoValidoVolverFormImpl formulario= (UsoEducativoNoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}

    		
    		int[] longitudTextosDesc = new int[form.getDescripciones().size()];
    		for(int i= 0;i< form.getDescripciones().size();i++){
    			longitudTextosDesc[i]=((DescripcionVO)(form.getDescripcionesAsArray()[i])).getTextos().length;
    		}
    		int[] longitudTextosRangosEdad = new int[form.getEdadTipica().size()];
    		for(int i= 0;i< form.getEdadTipica().size();i++){
    			longitudTextosRangosEdad[i]=((RangoEdadVO)(form.getEdadTipicaAsArray()[i])).getTextos().length;
    		}

    		int longitudDescripciones=form.getDescripciones().size();
    		int longitudRangos=form.getEdadTipica().size();
    		int longitudIdiomas=form.getIdiomas().size();
    		int longitudTipoRecurso=form.getTipoRecurso().size();
    		int longitudRoles=form.getRolUsuario().size();
    		int longitudContextos=form.getContexto().size();
    		int longitudProcesos=form.getProcesoCognitivo().size();
    		int longitudTADesc=(((DescripcionVO[])form.getTiempoApDescripcionAsArray())[0]).getTextos().length;
    		
    		//borrar session
    		request.getSession().removeAttribute(UsoEducativoSession.SESSION_KEY);
    		cambioFormulario(request,longitudTextosDesc,longitudTextosRangosEdad,
    				longitudDescripciones,longitudRangos,longitudIdiomas,longitudTipoRecurso,longitudRoles,
    				longitudContextos,longitudProcesos,longitudTADesc,source);
    		//recoger session
    		UsoEducativoSession usoeducativoSession = this.getUsoEducativoSession(request);
    		
    		//tipo recurso educativo
//    		form.setTipoRecursoAsArray(tiposRecurso);
    		form.setTipoRecurso(usoeducativoSession.getTiposRecurso());
    		//rol o destinatario
    		//form.setRolUsuarioAsArray(roles);
    		form.setRolUsuarioAsArray(usoeducativoSession.getRoles().toArray());
    		//contextos
    		//form.setContextoAsArray(contextos);
    		form.setContextoAsArray(usoeducativoSession.getContextos().toArray());
    		//procesos cognitivos
    		//form.setProcesoCognitivoAsArray(procesosCognitivos);
    		form.setProcesoCognitivoAsArray(usoeducativoSession.getProcesosCognitivos().toArray());
    		//rangos de edad
    		//form.setEdadTipicaAsArray(rangosEdad);
    		form.setEdadTipicaAsArray(usoeducativoSession.getRangosEdad().toArray());
    		//idioma
    		//form.setIdiomasAsArray(idiomas);
    		form.setIdiomasAsArray(usoeducativoSession.getIdiomas().toArray());
    		//descripciones
    		//form.setDescripcionesAsArray(descripciones);
    		form.setDescripcionesAsArray(usoeducativoSession.getDescripciones().toArray());
    		//form.setDescripcionesCatAsArray(descripcionesCat);
    		form.setDescripcionesCatAsArray(usoeducativoSession.getDescripcionesCat().toArray());
    		//descripcion tiempo de aprendizaje
    		//form.setTiempoApDescripcionAsArray(tiempoApDescripcion);
    		form.setTiempoApDescripcionAsArray(usoeducativoSession.getTiempoApDescripcion().toArray());
    		
 		  	//form.setAnyo(anyos);
    		form.setAnyo(usoeducativoSession.getAnyos());
		  	//form.setMes(meses);
    		form.setMes(usoeducativoSession.getMeses());
		  	//form.setDia(dias);
    		form.setDia(usoeducativoSession.getDias());
		  	//form.setHora(horas);
    		form.setHora(usoeducativoSession.getHoras());
		  	//form.setMinutos(minutos);
    		form.setMinutos(usoeducativoSession.getMinutos());
		  	//form.setSegundosP1(segundosP1);
    		form.setSegundosP1(usoeducativoSession.getSegundosP1());
		  	//form.setSegundosP2(segundosP2);
    		form.setSegundosP2(usoeducativoSession.getSegundosP2());
    		
    		String posicion = request.getAttribute("posicion").toString();
    		int posicionInt = Integer.parseInt(posicion);
	    	//se recogen todas las descripciones
	    	DescripcionVO[] descArray = (DescripcionVO[]) form.getDescripcionesAsArray();
	    	//recogo los textos de la descripcion indicada y le añado un nuevo texto
	    	LangStringVO[] textos = descArray[posicionInt].getTextos();
	    	LangStringVO[] nuevoTextos = new LangStringVO[textos.length+1];
	    	
	    	DescripcionUsoEdu[][] descUEArray = (DescripcionUsoEdu[][]) form.getDescripcionesCatAsArray();
	    	DescripcionUsoEdu[] descUEinterior= descUEArray[posicionInt];
	    	DescripcionUsoEdu[] auxUE= new DescripcionUsoEdu[textos.length+1];
	    	
	    	for(int i = 0 ; i < textos.length;i++){
	    		nuevoTextos[i]= textos[i];
	    		auxUE[i]= descUEinterior[i];
	    	}
	    	LangStringVO nuevoTexto = new LangStringVO();
	    	nuevoTexto.setIdioma("");
	    	nuevoTexto.setTexto("");
	    	nuevoTextos[nuevoTextos.length-1] = nuevoTexto;
	    	descArray[posicionInt].setTextos(nuevoTextos);
	    	form.setDescripcionesAsArray(descArray);
	    	
	    	//act session
	    	usoeducativoSession.setDescripciones(Arrays.asList(descArray));
	    	
	    	auxUE[nuevoTextos.length-1]= DescripcionUsoEdu.getInstance();
	    	descUEArray[posicionInt]=auxUE;
	    	form.setDescripcionesCatAsArray(descUEArray);
	    	//act session
	    	usoeducativoSession.setDescripcionesCat(Arrays.asList(descUEArray));
	    	
	    	 //cargar ayuda contextual    
			 cargarAyuda(usoeducativoSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
	    	
		}catch (ArrayIndexOutOfBoundsException ai) {
			logger
			.warn("Warning en catalogadorWeb, categoría Tecnica, metodo anadirContenidoDescripcion " + ai);
		}catch (Exception e) {
			logger
			.error("Error en Servicio de catalogacionWeb, categoría Tecnica, metodo anadirContenidoDescripcion " + e);
//			throw new Exception("tecnica",e);
			saveErrorMessage(request, " Error en Servicio de catalogacionWeb, categoría Tecnica, metodo anadirContenidoDescripcion ");
		}
     }


    /**
     * @see es.pode.catalogadorWeb.presentacion.categoriasAvanzado.usoeducativo.detalleUsoEducativo.DetalleUsoEduController#eliminarContenidoDescripcion(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.usoeducativo.detalleUsoEducativo.EliminarContenidoDescripcionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void eliminarContenidoDescripcion(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.usoeducativo.detalleUsoEducativo.EliminarContenidoDescripcionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{

    		String source=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES) ;//esquemaDeMetadatos
    		
    		Object valor = request.getSession().getAttribute("form");
    		
    		if (valor instanceof DetalleUsoEduFormImpl) {
    			DetalleUsoEduFormImpl formulario= (DetalleUsoEduFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof DetalleUsoEducativoSubmitFormImpl){
    			DetalleUsoEducativoSubmitFormImpl formulario= (DetalleUsoEducativoSubmitFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoValidoVolverFormImpl){
    			UsoEducativoValidoVolverFormImpl formulario= (UsoEducativoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoNoValidoVolverFormImpl){
    			UsoEducativoNoValidoVolverFormImpl formulario= (UsoEducativoNoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		
    		int[] longitudTextosDesc = new int[form.getDescripciones().size()];
    		for(int i= 0;i< form.getDescripciones().size();i++){
    			longitudTextosDesc[i]=((DescripcionVO)(form.getDescripcionesAsArray()[i])).getTextos().length;
    		}
    		int[] longitudTextosRangosEdad = new int[form.getEdadTipica().size()];
    		for(int i= 0;i< form.getEdadTipica().size();i++){
    			longitudTextosRangosEdad[i]=((RangoEdadVO)(form.getEdadTipicaAsArray()[i])).getTextos().length;
    		}

    		int longitudDescripciones=form.getDescripciones().size();
    		int longitudRangos=form.getEdadTipica().size();
    		int longitudIdiomas=form.getIdiomas().size();
    		int longitudTipoRecurso=form.getTipoRecurso().size();
    		int longitudRoles=form.getRolUsuario().size();
    		int longitudContextos=form.getContexto().size();
    		int longitudProcesos=form.getProcesoCognitivo().size();
    		int longitudTADesc=(((DescripcionVO[])form.getTiempoApDescripcionAsArray())[0]).getTextos().length;
    		
    		//borrar session
    		request.getSession().removeAttribute(UsoEducativoSession.SESSION_KEY);
    		cambioFormulario(request,longitudTextosDesc,longitudTextosRangosEdad,
    				longitudDescripciones,longitudRangos,longitudIdiomas,longitudTipoRecurso,longitudRoles,
    				longitudContextos,longitudProcesos,longitudTADesc,source);
    		//recoger session
    		UsoEducativoSession usoeducativoSession = this.getUsoEducativoSession(request);
    		
    		//tipo recurso educativo
//    		form.setTipoRecursoAsArray(tiposRecurso);
    		form.setTipoRecurso(usoeducativoSession.getTiposRecurso());
    		//rol o destinatario
    		//form.setRolUsuarioAsArray(roles);
    		form.setRolUsuarioAsArray(usoeducativoSession.getRoles().toArray());
    		//contextos
    		//form.setContextoAsArray(contextos);
    		form.setContextoAsArray(usoeducativoSession.getContextos().toArray());
    		//procesos cognitivos
    		//form.setProcesoCognitivoAsArray(procesosCognitivos);
    		form.setProcesoCognitivoAsArray(usoeducativoSession.getProcesosCognitivos().toArray());
    		//rangos de edad
    		//form.setEdadTipicaAsArray(rangosEdad);
    		form.setEdadTipicaAsArray(usoeducativoSession.getRangosEdad().toArray());
    		//idioma
    		//form.setIdiomasAsArray(idiomas);
    		form.setIdiomasAsArray(usoeducativoSession.getIdiomas().toArray());
    		//descripciones
    		//form.setDescripcionesAsArray(descripciones);
    		form.setDescripcionesAsArray(usoeducativoSession.getDescripciones().toArray());
    		//form.setDescripcionesCatAsArray(descripcionesCat);
    		form.setDescripcionesCatAsArray(usoeducativoSession.getDescripcionesCat().toArray());
    		//descripcion tiempo de aprendizaje
    		//form.setTiempoApDescripcionAsArray(tiempoApDescripcion);
    		form.setTiempoApDescripcionAsArray(usoeducativoSession.getTiempoApDescripcion().toArray());
    		
 		  	//form.setAnyo(anyos);
    		form.setAnyo(usoeducativoSession.getAnyos());
		  	//form.setMes(meses);
    		form.setMes(usoeducativoSession.getMeses());
		  	//form.setDia(dias);
    		form.setDia(usoeducativoSession.getDias());
		  	//form.setHora(horas);
    		form.setHora(usoeducativoSession.getHoras());
		  	//form.setMinutos(minutos);
    		form.setMinutos(usoeducativoSession.getMinutos());
		  	//form.setSegundosP1(segundosP1);
    		form.setSegundosP1(usoeducativoSession.getSegundosP1());
		  	//form.setSegundosP2(segundosP2);
    		form.setSegundosP2(usoeducativoSession.getSegundosP2());
    		
	        //posicion de contenido a eliminar   
	        String posicion = request.getAttribute("posicion").toString();
	        int posicionInt = Integer.parseInt(posicion);
			
			//posicion de la descripcion de la que se quiere eliminar parte de su contenido
	        String posicionExt = request.getAttribute("posicionExterior").toString();
	        int posicionExtInt = Integer.parseInt(posicionExt);
	    	
	    	DescripcionVO[] aDescripciones = (DescripcionVO[]) form.getDescripcionesAsArray();
	    	DescripcionVO[] descripciones = (DescripcionVO[]) usoeducativoSession.getDescripciones()
	    				.toArray(new DescripcionVO[usoeducativoSession.getDescripciones().size()]);
	    	LangStringVO[] contenido = descripciones[posicionExtInt].getTextos();
	    	LangStringVO[] nuevoContenido = new LangStringVO[contenido.length -1];
	    	
	    	DescripcionUsoEdu[][] aDescripcionesUE = (DescripcionUsoEdu[][]) form.getDescripcionesCatAsArray();
	    	
	    	DescripcionUsoEdu[][] descripcionesCat =(DescripcionUsoEdu[][])usoeducativoSession.getDescripcionesCat()
	    				.toArray(new DescripcionUsoEdu[usoeducativoSession.getDescripcionesCat().size()][]);
	    	DescripcionUsoEdu[] contenidoUE = descripcionesCat[posicionExtInt];
	    	DescripcionUsoEdu[] auxUE = new DescripcionUsoEdu[contenidoUE.length -1];
	    	
	    	//añado al nuevo array de contenidos todos los contenidos menos el que 
	    	//queremos eliminar
	    	for (int i = 0; i<nuevoContenido.length;i++){
	    		if (i < posicionInt)
	    		{
	    			nuevoContenido[i] = contenido[i];
	    			auxUE[i]= contenidoUE[i];
	    		}
	    		else
	    		{
	    			nuevoContenido[i] = contenido[i+1];
	    			auxUE[i]= contenidoUE[i+1];
	    		}
	    	}
	    	
	    	if(nuevoContenido.length==0)
	    	{
	    		nuevoContenido = new LangStringVO[1];
	    		LangStringVO vacio= new LangStringVO();
	    		vacio.setIdioma("");
	    		vacio.setTexto("");
	    		nuevoContenido[0]= vacio;
	    		
	    		auxUE= new DescripcionUsoEdu[1] ;
	    		DescripcionUsoEdu vacia= DescripcionUsoEdu.getInstance();
	    		auxUE[0]= vacia;
	    	}

	    	
	    	aDescripciones[posicionExtInt].setTextos(nuevoContenido);
	    	form.setDescripcionesAsArray(aDescripciones);
	    	//act session
	    	usoeducativoSession.setDescripciones(Arrays.asList(aDescripciones));
	    	
	    	aDescripcionesUE[posicionExtInt]= auxUE;
	    	form.setDescripcionesCatAsArray(aDescripcionesUE);
	    	//act session
	    	usoeducativoSession.setDescripcionesCat(Arrays.asList(aDescripcionesUE));
		
	    	 //cargar ayuda contextual    
			 cargarAyuda(usoeducativoSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
	    	
		}catch (ArrayIndexOutOfBoundsException ai) {
			logger
			.warn("Warning en catalogadorWeb, categoría Tecnica, metodo eliminarContenidoDescripcion " + ai);
		}catch (Exception e) {
			logger
			.error("Error en Servicio de catalogacionWeb, categoría Tecnica, metodo eliminarContenidoDescripcion " + e);
//			throw new Exception("tecnica",e);
			saveErrorMessage(request, " Error en Servicio de catalogacionWeb, categoría Tecnica, metodo eliminarContenidoDescripcion ");
		}
     }



    /**
     * @see es.pode.catalogadorWeb.presentacion.categoriasAvanzado.usoeducativo.detalleUsoEducativo.DetalleUsoEduController#anadirIdioma(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.usoeducativo.detalleUsoEducativo.AnadirIdiomaForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void anadirIdioma(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.usoeducativo.detalleUsoEducativo.AnadirIdiomaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
  
    		String source=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES) ;//esquemaDeMetadatos
    		
    		Object valor = request.getSession().getAttribute("form");
    		
    		if (valor instanceof DetalleUsoEduFormImpl) {
    			DetalleUsoEduFormImpl formulario= (DetalleUsoEduFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof DetalleUsoEducativoSubmitFormImpl){
    			DetalleUsoEducativoSubmitFormImpl formulario= (DetalleUsoEducativoSubmitFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoValidoVolverFormImpl){
    			UsoEducativoValidoVolverFormImpl formulario= (UsoEducativoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoNoValidoVolverFormImpl){
    			UsoEducativoNoValidoVolverFormImpl formulario= (UsoEducativoNoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		
    		int[] longitudTextosDesc = new int[form.getDescripciones().size()];
    		for(int i= 0;i< form.getDescripciones().size();i++){
    			longitudTextosDesc[i]=((DescripcionVO)(form.getDescripcionesAsArray()[i])).getTextos().length;
    		}
    		int[] longitudTextosRangosEdad = new int[form.getEdadTipica().size()];
    		for(int i= 0;i< form.getEdadTipica().size();i++){
    			longitudTextosRangosEdad[i]=((RangoEdadVO)(form.getEdadTipicaAsArray()[i])).getTextos().length;
    		}

    		int longitudDescripciones=form.getDescripciones().size();
    		int longitudRangos=form.getEdadTipica().size();
    		int longitudIdiomas=form.getIdiomas().size();
    		int longitudTipoRecurso=form.getTipoRecurso().size();
    		int longitudRoles=form.getRolUsuario().size();
    		int longitudContextos=form.getContexto().size();
    		int longitudProcesos=form.getProcesoCognitivo().size();
    		int longitudTADesc=(((DescripcionVO[])form.getTiempoApDescripcionAsArray())[0]).getTextos().length;
    		
    		//borrar session
    		request.getSession().removeAttribute(UsoEducativoSession.SESSION_KEY);
    		cambioFormulario(request,longitudTextosDesc,longitudTextosRangosEdad,
    				longitudDescripciones,longitudRangos,longitudIdiomas,longitudTipoRecurso,longitudRoles,
    				longitudContextos,longitudProcesos,longitudTADesc,source);
    		//recoger session
    		UsoEducativoSession usoeducativoSession = this.getUsoEducativoSession(request);
    		
    		//tipo recurso educativo
    		 //form.setTipoRecursoAsArray(tiposRecurso);
    		form.setTipoRecurso(usoeducativoSession.getTiposRecurso());
    		//rol o destinatario
    		//form.setRolUsuarioAsArray(roles);
    		form.setRolUsuarioAsArray(usoeducativoSession.getRoles().toArray());
    		//contextos
    		//form.setContextoAsArray(contextos);
    		form.setContextoAsArray(usoeducativoSession.getContextos().toArray());
    		//procesos cognitivos
    		//form.setProcesoCognitivoAsArray(procesosCognitivos);
    		form.setProcesoCognitivoAsArray(usoeducativoSession.getProcesosCognitivos().toArray());
    		//rangos de edad
    		//form.setEdadTipicaAsArray(rangosEdad);
    		form.setEdadTipicaAsArray(usoeducativoSession.getRangosEdad().toArray());
    		//idioma
    		//form.setIdiomasAsArray(idiomas);
    		form.setIdiomasAsArray(usoeducativoSession.getIdiomas().toArray());
    		//descripciones
    		//form.setDescripcionesAsArray(descripciones);
    		form.setDescripcionesAsArray(usoeducativoSession.getDescripciones().toArray());
    		//form.setDescripcionesCatAsArray(descripcionesCat);
    		form.setDescripcionesCatAsArray(usoeducativoSession.getDescripcionesCat().toArray());
    		//descripcion tiempo de aprendizaje
    		//form.setTiempoApDescripcionAsArray(tiempoApDescripcion);
    		form.setTiempoApDescripcionAsArray(usoeducativoSession.getTiempoApDescripcion().toArray());
    		
 		  	//form.setAnyo(anyos);
    		form.setAnyo(usoeducativoSession.getAnyos());
		  	//form.setMes(meses);
    		form.setMes(usoeducativoSession.getMeses());
		  	//form.setDia(dias);
    		form.setDia(usoeducativoSession.getDias());
		  	//form.setHora(horas);
    		form.setHora(usoeducativoSession.getHoras());
		  	//form.setMinutos(minutos);
    		form.setMinutos(usoeducativoSession.getMinutos());
		  	//form.setSegundosP1(segundosP1);
    		form.setSegundosP1(usoeducativoSession.getSegundosP1());
		  	//form.setSegundosP2(segundosP2);
    		form.setSegundosP2(usoeducativoSession.getSegundosP2());
    		
    		//añadimos un campo de idioma vacio
    		Object[] aIdiomas=form.getIdiomasAsArray();
    		int nuevoTamano = aIdiomas.length +1;
    		Object[] newAIdiomas = new Object[nuevoTamano];
    		IdiomaVO idiomaVO=new IdiomaVO();
    		idiomaVO.setTexto("");
    		for(int i = 0; i<aIdiomas.length;i++)
    			newAIdiomas[i]=aIdiomas[i];
    		newAIdiomas[nuevoTamano-1]= idiomaVO;
 	   
    		form.setIdiomasAsArray(newAIdiomas);
    		//act session
    		usoeducativoSession.setIdiomas(Arrays.asList(newAIdiomas));
    		 //cargar ayuda contextual    
			 cargarAyuda(usoeducativoSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
 	   
		}catch (ArrayIndexOutOfBoundsException ai) {
			logger
			.warn("Warning en catalogadorWeb, categoría Tecnica, metodo anadirIdioma " + ai);
		}catch (Exception e) {
			logger
			.error("Error en Servicio de catalogacionWeb, categoría Tecnica, metodo anadirIdioma " + e);
//			throw new Exception("tecnica",e);
			saveErrorMessage(request, " Error en Servicio de catalogacionWeb, categoría Tecnica, metodo anadirIdioma ");
		}
     }



    /**
     * @see es.pode.catalogadorWeb.presentacion.categoriasAvanzado.usoeducativo.detalleUsoEducativo.DetalleUsoEduController#eliminarIdioma(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.usoeducativo.detalleUsoEducativo.EliminarIdiomaForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void eliminarIdioma(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.usoeducativo.detalleUsoEducativo.EliminarIdiomaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
    		
    		String source=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES) ;//esquemaDeMetadatos
    		
    		Object valor = request.getSession().getAttribute("form");
    		
    		if (valor instanceof DetalleUsoEduFormImpl) {
    			DetalleUsoEduFormImpl formulario= (DetalleUsoEduFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof DetalleUsoEducativoSubmitFormImpl){
    			DetalleUsoEducativoSubmitFormImpl formulario= (DetalleUsoEducativoSubmitFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoValidoVolverFormImpl){
    			UsoEducativoValidoVolverFormImpl formulario= (UsoEducativoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoNoValidoVolverFormImpl){
    			UsoEducativoNoValidoVolverFormImpl formulario= (UsoEducativoNoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
     		
    		int[] longitudTextosDesc = new int[form.getDescripciones().size()];
    		for(int i= 0;i< form.getDescripciones().size();i++){
    			longitudTextosDesc[i]=((DescripcionVO)(form.getDescripcionesAsArray()[i])).getTextos().length;
    		}
    		int[] longitudTextosRangosEdad = new int[form.getEdadTipica().size()];
    		for(int i= 0;i< form.getEdadTipica().size();i++){
    			longitudTextosRangosEdad[i]=((RangoEdadVO)(form.getEdadTipicaAsArray()[i])).getTextos().length;
    		}

    		int longitudDescripciones=form.getDescripciones().size();
    		int longitudRangos=form.getEdadTipica().size();
    		int longitudIdiomas=form.getIdiomas().size();
    		int longitudTipoRecurso=form.getTipoRecurso().size();
    		int longitudRoles=form.getRolUsuario().size();
    		int longitudContextos=form.getContexto().size();
    		int longitudProcesos=form.getProcesoCognitivo().size();
    		int longitudTADesc=(((DescripcionVO[])form.getTiempoApDescripcionAsArray())[0]).getTextos().length;
    		
    		//borrar session
    		request.getSession().removeAttribute(UsoEducativoSession.SESSION_KEY);
    		cambioFormulario(request,longitudTextosDesc,longitudTextosRangosEdad,
    				longitudDescripciones,longitudRangos,longitudIdiomas,longitudTipoRecurso,longitudRoles,
    				longitudContextos,longitudProcesos,longitudTADesc,source);
    		//recoger session
    		UsoEducativoSession usoeducativoSession = this.getUsoEducativoSession(request);
    		
    		//tipo recurso educativo
//    		form.setTipoRecursoAsArray(tiposRecurso);
    		form.setTipoRecurso(usoeducativoSession.getTiposRecurso());
    		//rol o destinatario
    		//form.setRolUsuarioAsArray(roles);
    		form.setRolUsuarioAsArray(usoeducativoSession.getRoles().toArray());
    		//contextos
    		//form.setContextoAsArray(contextos);
    		form.setContextoAsArray(usoeducativoSession.getContextos().toArray());
    		//procesos cognitivos
    		//form.setProcesoCognitivoAsArray(procesosCognitivos);
    		form.setProcesoCognitivoAsArray(usoeducativoSession.getProcesosCognitivos().toArray());
    		//rangos de edad
    		//form.setEdadTipicaAsArray(rangosEdad);
    		form.setEdadTipicaAsArray(usoeducativoSession.getRangosEdad().toArray());
    		//idioma
    		//form.setIdiomasAsArray(idiomas);
    		form.setIdiomasAsArray(usoeducativoSession.getIdiomas().toArray());
    		//descripciones
    		//form.setDescripcionesAsArray(descripciones);
    		form.setDescripcionesAsArray(usoeducativoSession.getDescripciones().toArray());
    		//form.setDescripcionesCatAsArray(descripcionesCat);
    		form.setDescripcionesCatAsArray(usoeducativoSession.getDescripcionesCat().toArray());
    		//descripcion tiempo de aprendizaje
    		//form.setTiempoApDescripcionAsArray(tiempoApDescripcion);
    		form.setTiempoApDescripcionAsArray(usoeducativoSession.getTiempoApDescripcion().toArray());
    		
 		  	//form.setAnyo(anyos);
    		form.setAnyo(usoeducativoSession.getAnyos());
		  	//form.setMes(meses);
    		form.setMes(usoeducativoSession.getMeses());
		  	//form.setDia(dias);
    		form.setDia(usoeducativoSession.getDias());
		  	//form.setHora(horas);
    		form.setHora(usoeducativoSession.getHoras());
		  	//form.setMinutos(minutos);
    		form.setMinutos(usoeducativoSession.getMinutos());
		  	//form.setSegundosP1(segundosP1);
    		form.setSegundosP1(usoeducativoSession.getSegundosP1());
		  	//form.setSegundosP2(segundosP2);
    		form.setSegundosP2(usoeducativoSession.getSegundosP2());
    		
	        //posicion del idioma que se quiere eliminar   
	        String posicion = request.getAttribute("posicion").toString();
	        int posicionInt = Integer.parseInt(posicion);
	    	
	        IdiomaVO[] aIdiomas=(IdiomaVO[]) form.getIdiomasAsArray();
	    	IdiomaVO[] nuevoIdiomas = new IdiomaVO[aIdiomas.length -1];
	     	//añado al nuevo array de idiomas todos los idimas menos el que 
	     	//queremos eliminar
	     	for (int i = 0; i<nuevoIdiomas.length;i++){
	     		if (i < posicionInt)
	     			nuevoIdiomas[i] = aIdiomas[i];
	     		else
	     			nuevoIdiomas[i] = aIdiomas[i+1]; 
	     	}
    	   
	     	form.setIdiomasAsArray(nuevoIdiomas);
	     	//act session
	     	usoeducativoSession.setIdiomas(Arrays.asList(nuevoIdiomas));
	     	
	     	 //cargar ayuda contextual    
			 cargarAyuda(usoeducativoSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
	    
		}catch (ArrayIndexOutOfBoundsException ai) {
			logger
			.warn("Warning en catalogadorWeb, categoría Tecnica, metodo eliminarIdioma " + ai);
		}catch (Exception e) {
			logger
			.error("Error en Servicio de catalogacionWeb, categoría Tecnica, metodo eliminarIdioma " + e);
//			throw new Exception("tecnica",e);
			saveErrorMessage(request, " Error en Servicio de catalogacionWeb, categoría Tecnica, metodo eliminarIdioma ");
		}
    	
     }


    /**
     * @see es.pode.catalogadorWeb.presentacion.categoriasAvanzado.usoeducativo.detalleUsoEducativo.DetalleUsoEduController#anadirProcesoCognitivo(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.usoeducativo.detalleUsoEducativo.AnadirProcesoCognitivoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void anadirProcesoCognitivo(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.usoeducativo.detalleUsoEducativo.AnadirProcesoCognitivoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
    	
    		String source=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES) ;//esquemaDeMetadatos
    		
    		Object valor = request.getSession().getAttribute("form");
    		
    		if (valor instanceof DetalleUsoEduFormImpl) {
    			DetalleUsoEduFormImpl formulario= (DetalleUsoEduFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof DetalleUsoEducativoSubmitFormImpl){
    			DetalleUsoEducativoSubmitFormImpl formulario= (DetalleUsoEducativoSubmitFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoValidoVolverFormImpl){
    			UsoEducativoValidoVolverFormImpl formulario= (UsoEducativoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoNoValidoVolverFormImpl){
    			UsoEducativoNoValidoVolverFormImpl formulario= (UsoEducativoNoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
     		
    		int[] longitudTextosDesc = new int[form.getDescripciones().size()];
    		for(int i= 0;i< form.getDescripciones().size();i++){
    			longitudTextosDesc[i]=((DescripcionVO)(form.getDescripcionesAsArray()[i])).getTextos().length;
    		}
    		int[] longitudTextosRangosEdad = new int[form.getEdadTipica().size()];
    		for(int i= 0;i< form.getEdadTipica().size();i++){
    			longitudTextosRangosEdad[i]=((RangoEdadVO)(form.getEdadTipicaAsArray()[i])).getTextos().length;
    		}

    		int longitudDescripciones=form.getDescripciones().size();
    		int longitudRangos=form.getEdadTipica().size();
    		int longitudIdiomas=form.getIdiomas().size();
    		int longitudTipoRecurso=form.getTipoRecurso().size();
    		int longitudRoles=form.getRolUsuario().size();
    		int longitudContextos=form.getContexto().size();
    		int longitudProcesos=form.getProcesoCognitivo().size();
    		int longitudTADesc=(((DescripcionVO[])form.getTiempoApDescripcionAsArray())[0]).getTextos().length;
    		
    		//borrar session
    		request.getSession().removeAttribute(UsoEducativoSession.SESSION_KEY);
    		cambioFormulario(request,longitudTextosDesc,longitudTextosRangosEdad,
    				longitudDescripciones,longitudRangos,longitudIdiomas,longitudTipoRecurso,longitudRoles,
    				longitudContextos,longitudProcesos,longitudTADesc,source);
    		//recoger session
    		UsoEducativoSession usoeducativoSession = this.getUsoEducativoSession(request);
    		
    		//tipo recurso educativo
    		 //form.setTipoRecursoAsArray(tiposRecurso);
    		form.setTipoRecurso(usoeducativoSession.getTiposRecurso());
    		//rol o destinatario
    		//form.setRolUsuarioAsArray(roles);
    		form.setRolUsuarioAsArray(usoeducativoSession.getRoles().toArray());
    		//contextos
    		//form.setContextoAsArray(contextos);
    		form.setContextoAsArray(usoeducativoSession.getContextos().toArray());
    		//procesos cognitivos
    		//form.setProcesoCognitivoAsArray(procesosCognitivos);
    		form.setProcesoCognitivoAsArray(usoeducativoSession.getProcesosCognitivos().toArray());
    		//rangos de edad
    		//form.setEdadTipicaAsArray(rangosEdad);
    		form.setEdadTipicaAsArray(usoeducativoSession.getRangosEdad().toArray());
    		//idioma
    		//form.setIdiomasAsArray(idiomas);
    		form.setIdiomasAsArray(usoeducativoSession.getIdiomas().toArray());
    		//descripciones
    		//form.setDescripcionesAsArray(descripciones);
    		form.setDescripcionesAsArray(usoeducativoSession.getDescripciones().toArray());
    		//form.setDescripcionesCatAsArray(descripcionesCat);
    		form.setDescripcionesCatAsArray(usoeducativoSession.getDescripcionesCat().toArray());
    		//descripcion tiempo de aprendizaje
    		//form.setTiempoApDescripcionAsArray(tiempoApDescripcion);
    		form.setTiempoApDescripcionAsArray(usoeducativoSession.getTiempoApDescripcion().toArray());
    		
 		  	//form.setAnyo(anyos);
    		form.setAnyo(usoeducativoSession.getAnyos());
		  	//form.setMes(meses);
    		form.setMes(usoeducativoSession.getMeses());
		  	//form.setDia(dias);
    		form.setDia(usoeducativoSession.getDias());
		  	//form.setHora(horas);
    		form.setHora(usoeducativoSession.getHoras());
		  	//form.setMinutos(minutos);
    		form.setMinutos(usoeducativoSession.getMinutos());
		  	//form.setSegundosP1(segundosP1);
    		form.setSegundosP1(usoeducativoSession.getSegundosP1());
		  	//form.setSegundosP2(segundosP2);
    		form.setSegundosP2(usoeducativoSession.getSegundosP2());
    		
    		//añadimos un campo de proceso cognitivo vacio
    		Object[] aProcesoCognitivo=form.getProcesoCognitivoAsArray();
    		int nuevoTamano = aProcesoCognitivo.length +1;
    		Object[] newAProcesoCognitivo = new Object[nuevoTamano];
    		SourceValueVO procesoVO=new SourceValueVO();
    		procesoVO.setValor("");
    		procesoVO.setSource("");
    		for(int i = 0; i<aProcesoCognitivo.length;i++)
    			newAProcesoCognitivo[i]=aProcesoCognitivo[i];
    		newAProcesoCognitivo[nuevoTamano-1]= procesoVO;
 	   
    		form.setProcesoCognitivoAsArray(newAProcesoCognitivo);
    		//act session
    		usoeducativoSession.setProcesosCognitivos(Arrays.asList(newAProcesoCognitivo));
 	   
    		 //cargar ayuda contextual    
			 cargarAyuda(usoeducativoSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
    		
		}catch (ArrayIndexOutOfBoundsException ai) {
			logger
			.warn("Warning en catalogadorWeb, categoría Tecnica, metodo anadirProcesoCognitivo " + ai);
		}catch (Exception e) {
			logger
			.error("Error en Servicio de catalogacionWeb, categoría Tecnica, metodo anadirProcesoCognitivo " + e);
//			throw new Exception("tecnica",e);
			saveErrorMessage(request, " Error en Servicio de catalogacionWeb, categoría Tecnica, metodo anadirProcesoCognitivo ");
		}
     
   
     }




    /**
     * @see es.pode.catalogadorWeb.presentacion.categoriasAvanzado.usoeducativo.detalleUsoEducativo.DetalleUsoEduController#eliminarProcesoCognitivo(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.usoeducativo.detalleUsoEducativo.EliminarProcesoCognitivoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void eliminarProcesoCognitivo(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.usoeducativo.detalleUsoEducativo.EliminarProcesoCognitivoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
    
    		String source=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES) ;//esquemaDeMetadatos
    		
    		Object valor = request.getSession().getAttribute("form");
    		
    		if (valor instanceof DetalleUsoEduFormImpl) {
    			DetalleUsoEduFormImpl formulario= (DetalleUsoEduFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof DetalleUsoEducativoSubmitFormImpl){
    			DetalleUsoEducativoSubmitFormImpl formulario= (DetalleUsoEducativoSubmitFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoValidoVolverFormImpl){
    			UsoEducativoValidoVolverFormImpl formulario= (UsoEducativoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoNoValidoVolverFormImpl){
    			UsoEducativoNoValidoVolverFormImpl formulario= (UsoEducativoNoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		
    		int[] longitudTextosDesc = new int[form.getDescripciones().size()];
    		for(int i= 0;i< form.getDescripciones().size();i++){
    			longitudTextosDesc[i]=((DescripcionVO)(form.getDescripcionesAsArray()[i])).getTextos().length;
    		}
    		int[] longitudTextosRangosEdad = new int[form.getEdadTipica().size()];
    		for(int i= 0;i< form.getEdadTipica().size();i++){
    			longitudTextosRangosEdad[i]=((RangoEdadVO)(form.getEdadTipicaAsArray()[i])).getTextos().length;
    		}

    		int longitudDescripciones=form.getDescripciones().size();
    		int longitudRangos=form.getEdadTipica().size();
    		int longitudIdiomas=form.getIdiomas().size();
    		int longitudTipoRecurso=form.getTipoRecurso().size();
    		int longitudRoles=form.getRolUsuario().size();
    		int longitudContextos=form.getContexto().size();
    		int longitudProcesos=form.getProcesoCognitivo().size();
    		int longitudTADesc=(((DescripcionVO[])form.getTiempoApDescripcionAsArray())[0]).getTextos().length;
    		
    		//borrar session
    		request.getSession().removeAttribute(UsoEducativoSession.SESSION_KEY);
    		cambioFormulario(request,longitudTextosDesc,longitudTextosRangosEdad,
    				longitudDescripciones,longitudRangos,longitudIdiomas,longitudTipoRecurso,longitudRoles,
    				longitudContextos,longitudProcesos,longitudTADesc,source);
    		//recoger session
    		UsoEducativoSession usoeducativoSession = this.getUsoEducativoSession(request);
    		
    		//tipo recurso educativo
    		 //form.setTipoRecursoAsArray(tiposRecurso);
    		form.setTipoRecurso(usoeducativoSession.getTiposRecurso());
    		//rol o destinatario
    		//form.setRolUsuarioAsArray(roles);
    		form.setRolUsuarioAsArray(usoeducativoSession.getRoles().toArray());
    		//contextos
    		//form.setContextoAsArray(contextos);
    		form.setContextoAsArray(usoeducativoSession.getContextos().toArray());
    		//procesos cognitivos
    		//form.setProcesoCognitivoAsArray(procesosCognitivos);
    		form.setProcesoCognitivoAsArray(usoeducativoSession.getProcesosCognitivos().toArray());
    		//rangos de edad
    		//form.setEdadTipicaAsArray(rangosEdad);
    		form.setEdadTipicaAsArray(usoeducativoSession.getRangosEdad().toArray());
    		//idioma
    		//form.setIdiomasAsArray(idiomas);
    		form.setIdiomasAsArray(usoeducativoSession.getIdiomas().toArray());
    		//descripciones
    		//form.setDescripcionesAsArray(descripciones);
    		form.setDescripcionesAsArray(usoeducativoSession.getDescripciones().toArray());
    		//form.setDescripcionesCatAsArray(descripcionesCat);
    		form.setDescripcionesCatAsArray(usoeducativoSession.getDescripcionesCat().toArray());
    		//descripcion tiempo de aprendizaje
    		//form.setTiempoApDescripcionAsArray(tiempoApDescripcion);
    		form.setTiempoApDescripcionAsArray(usoeducativoSession.getTiempoApDescripcion().toArray());
    		
 		  	//form.setAnyo(anyos);
    		form.setAnyo(usoeducativoSession.getAnyos());
		  	//form.setMes(meses);
    		form.setMes(usoeducativoSession.getMeses());
		  	//form.setDia(dias);
    		form.setDia(usoeducativoSession.getDias());
		  	//form.setHora(horas);
    		form.setHora(usoeducativoSession.getHoras());
		  	//form.setMinutos(minutos);
    		form.setMinutos(usoeducativoSession.getMinutos());
		  	//form.setSegundosP1(segundosP1);
    		form.setSegundosP1(usoeducativoSession.getSegundosP1());
		  	//form.setSegundosP2(segundosP2);
    		form.setSegundosP2(usoeducativoSession.getSegundosP2());
    		
	        //posicion del proceso cognitivo que se quiere eliminar   
	        String posicion = request.getAttribute("posicion").toString();
	        int posicionInt = Integer.parseInt(posicion);
	    	
	        SourceValueVO[] aProcesoCognitivo=(SourceValueVO[]) form.getProcesoCognitivoAsArray();
	        SourceValueVO[] nuevoProcesoCognitivo = new SourceValueVO[aProcesoCognitivo.length -1];
	     	//añado al nuevo array de procesos cognitivos con todos los procesos menos el que 
	     	//queremos eliminar
	     	for (int i = 0; i<nuevoProcesoCognitivo.length;i++){
	     		if (i < posicionInt)
	     			nuevoProcesoCognitivo[i] = aProcesoCognitivo[i];
	     		else
	     			nuevoProcesoCognitivo[i] = aProcesoCognitivo[i+1]; 
	     	}
    	   
	     	form.setProcesoCognitivoAsArray(nuevoProcesoCognitivo);
	     	//act session
	     	usoeducativoSession.setProcesosCognitivos(Arrays.asList(nuevoProcesoCognitivo));
	     	 //cargar ayuda contextual    
			 cargarAyuda(usoeducativoSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
	    
		}catch (IndexOutOfBoundsException ai) {
			logger
			.warn("Warning en catalogadorWeb, categoría Tecnica, metodo eliminarProcesoCognitivo " + ai);
		}catch (Exception e) {
			logger
			.error("Error en Servicio de catalogacionWeb, categoría Tecnica, metodo eliminarProcesoCognitivo " + e);
//			throw new Exception("tecnica",e);
			saveErrorMessage(request, " Error en Servicio de catalogacionWeb, categoría Tecnica, metodo eliminarProcesoCognitivo ");
		} 
     }

     public final boolean esValidoUsoEdu(
    		ActionMapping mapping, 
    		EsValidoUsoEduForm form, 
    		HttpServletRequest request, 
    		HttpServletResponse response) 
    throws Exception
    {

		String source=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES) ;//esquemaDeMetadatos

    	Object valor = request.getSession().getAttribute("form");

    	//Obtenemos la longitudes de los VOs, que pasamos a cambioFormulario para que se obtengan del request los cambios que hemos hecho
    	//Pues en el form que nos viene como parametro no los guarda.
    	int longitudDescripciones=0;
    	int longitudIdiomas=0;
		int longitudTipoRecurso=0;
		int longitudRoles=0;
		int longitudContextos=0;
		int longitudProcesos=0;
		int longitudTADesc=0;
		int longitudRangos=0;
    	int[] longitudTextosDesc = new int[0];
    	int[] longitudTextosRangosEdad = new int[0];
   
    	if (valor instanceof DetalleUsoEduFormImpl) {
    		DetalleUsoEduFormImpl formulario = ((DetalleUsoEduFormImpl)valor);
    		Object[] desc = formulario.getDescripcionesAsArray();
			longitudTextosDesc = new int[desc.length];
			for(int i= 0;i< desc.length;i++){
				longitudTextosDesc[i]=((DescripcionVO)(desc[i])).getTextos().length;
			}
			Object[] eTipica = formulario.getEdadTipicaAsArray();
			longitudTextosRangosEdad= new int[eTipica.length];
			for(int i= 0;i< eTipica.length;i++){
				longitudTextosRangosEdad[i]=((RangoEdadVO)eTipica[i]).getTextos().length;
			}
			longitudDescripciones=desc.length;
		  	longitudRangos=formulario.getEdadTipicaAsArray().length;
		  	longitudTipoRecurso=formulario.getTipoRecursoAsArray().length;
		  	longitudRoles=formulario.getRolUsuarioAsArray().length;
		  	longitudIdiomas=formulario.getIdiomasAsArray().length;
			longitudContextos=formulario.getContextoAsArray().length;
			longitudProcesos=formulario.getProcesoCognitivoAsArray().length;
		  	longitudTADesc=(((DescripcionVO[])formulario.getTiempoApDescripcionAsArray())[0]).getTextos().length; 		
    	}
    	else if(valor instanceof DetalleUsoEducativoSubmitFormImpl){
    		DetalleUsoEducativoSubmitFormImpl formulario = ((DetalleUsoEducativoSubmitFormImpl)valor);
    		Object[] desc = formulario.getDescripcionesAsArray();
			longitudTextosDesc = new int[desc.length];
			for(int i= 0;i< desc.length;i++){
				longitudTextosDesc[i]=((DescripcionVO)(desc[i])).getTextos().length;
			}
			Object[] eTipica = formulario.getEdadTipicaAsArray();
			longitudTextosRangosEdad= new int[eTipica.length];
			for(int i= 0;i< eTipica.length;i++){
				longitudTextosRangosEdad[i]=((RangoEdadVO)eTipica[i]).getTextos().length;
			}
			longitudDescripciones=desc.length;
		  	longitudRangos=formulario.getEdadTipicaAsArray().length;
		  	longitudTipoRecurso=formulario.getTipoRecursoAsArray().length;
		  	longitudRoles=formulario.getRolUsuarioAsArray().length;
		  	longitudIdiomas=formulario.getIdiomasAsArray().length;
			longitudContextos=formulario.getContextoAsArray().length;
			longitudProcesos=formulario.getProcesoCognitivoAsArray().length;
		  	longitudTADesc=(((DescripcionVO[])formulario.getTiempoApDescripcionAsArray())[0]).getTextos().length; 		
    	}
    	else if(valor instanceof UsoEducativoValidoVolverFormImpl){
    		UsoEducativoValidoVolverFormImpl formulario = ((UsoEducativoValidoVolverFormImpl)valor);
    		Object[] desc = formulario.getDescripcionesAsArray();
			longitudTextosDesc = new int[desc.length];
			for(int i= 0;i< desc.length;i++){
				longitudTextosDesc[i]=((DescripcionVO)(desc[i])).getTextos().length;
			}
			Object[] eTipica = formulario.getEdadTipicaAsArray();
			longitudTextosRangosEdad= new int[eTipica.length];
			for(int i= 0;i< eTipica.length;i++){
				longitudTextosRangosEdad[i]=((RangoEdadVO)eTipica[i]).getTextos().length;
			}
			longitudDescripciones=desc.length;
		  	longitudRangos=formulario.getEdadTipicaAsArray().length;
		  	longitudTipoRecurso=formulario.getTipoRecursoAsArray().length;
		  	longitudRoles=formulario.getRolUsuarioAsArray().length;
		  	longitudIdiomas=formulario.getIdiomasAsArray().length;
			longitudContextos=formulario.getContextoAsArray().length;
			longitudProcesos=formulario.getProcesoCognitivoAsArray().length;
		  	longitudTADesc=(((DescripcionVO[])formulario.getTiempoApDescripcionAsArray())[0]).getTextos().length; 		
    	}
    	else if(valor instanceof UsoEducativoNoValidoVolverFormImpl){
    		UsoEducativoNoValidoVolverFormImpl formulario = ((UsoEducativoNoValidoVolverFormImpl)valor);
    		Object[] desc = formulario.getDescripcionesAsArray();
			longitudTextosDesc = new int[desc.length];
			for(int i= 0;i< desc.length;i++){
				longitudTextosDesc[i]=((DescripcionVO)(desc[i])).getTextos().length;
			}
			Object[] eTipica = formulario.getEdadTipicaAsArray();
			longitudTextosRangosEdad= new int[eTipica.length];
			for(int i= 0;i< eTipica.length;i++){
				longitudTextosRangosEdad[i]=((RangoEdadVO)eTipica[i]).getTextos().length;
			}
			longitudDescripciones=desc.length;
		  	longitudRangos=formulario.getEdadTipicaAsArray().length;
		  	longitudTipoRecurso=formulario.getTipoRecursoAsArray().length;
		  	longitudRoles=formulario.getRolUsuarioAsArray().length;
		  	longitudIdiomas=formulario.getIdiomasAsArray().length;
			longitudContextos=formulario.getContextoAsArray().length;
			longitudProcesos=formulario.getProcesoCognitivoAsArray().length;
		  	longitudTADesc=(((DescripcionVO[])formulario.getTiempoApDescripcionAsArray())[0]).getTextos().length; 		
    	}
    	
    	//borramos session
    	request.getSession().removeAttribute(UsoEducativoSession.SESSION_KEY);
    	//Recogemos los cambio del request mediante parametros
    	cambioFormulario(request,longitudTextosDesc,longitudTextosRangosEdad,longitudDescripciones,longitudRangos,longitudIdiomas,
    			longitudTipoRecurso,longitudRoles,longitudContextos,longitudProcesos,longitudTADesc,source);
	  	//recogemos session
    	UsoEducativoSession usoeducativoSession = this.getUsoEducativoSession(request);
    	
    	//tipoInteractividad =form.getTipoInteractividad();
    	usoeducativoSession.setTipoInteractividad(form.getTipoInteractividad());
	  	//nivelInteractividad=form.getNivelInteractividad();
    	usoeducativoSession.setNivelInteractividad(form.getNivelInteractividad());
	  	//densidadSemantica=form.getDensidadSemantica();
    	usoeducativoSession.setDensidadSemantica(form.getDensidadSemantica());
	  	//dificultad=form.getDificultad();
    	usoeducativoSession.setDificultad(form.getDificultad());
	  	
	  	
	  	//inicio validacion
	  	Collection<String> mensajes= new ArrayList<String>();
	  	
	  	//String idiomaLocale=((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
	  	ResourceBundle datosResources = I18n.getInstance().getResource("application-resources", (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
		boolean todosVacios=true;
		int i;
		String error = "";

	  	//TIPO DE RECURSO DE APRENDIZAJE

		String recurso;
		SourceValueVO[] tiposRecurso = (SourceValueVO[]) usoeducativoSession.getTiposRecurso()
				.toArray(new SourceValueVO[usoeducativoSession.getTiposRecurso().size()]);
		for(i=0;i<tiposRecurso.length && todosVacios;i++)
		{
			recurso= tiposRecurso[i].getValor();
			if(recurso!=null && !recurso.equals(""))
			{
				todosVacios=false;
			}
		}
		error = datosResources.getString("CAV.5.2");
		if(todosVacios && !mensajes.contains(error))
			mensajes.add(datosResources.getString("CAV.5.2"));//El campo Tipo de recurso es obligatorio
		//IDIOMA
		todosVacios=true;
		String idioma;
		IdiomaVO[] idiomas = (IdiomaVO[])usoeducativoSession.getIdiomas()
				.toArray(new IdiomaVO[usoeducativoSession.getIdiomas().size()]);
		for(i=0;i<idiomas.length && todosVacios;i++)
		{
			idioma= idiomas[i].getTexto();
			if(idioma!=null && !idioma.equals(""))
			{
				todosVacios=false;
			}
		}
		error = datosResources.getString("CAV.5.11");
		if(todosVacios && !mensajes.contains(error))
			mensajes.add(datosResources.getString("CAV.5.11"));//El campo Idioma es obligatorio

		//EDAD DEL DESTINATARIO
		RangoEdadVO[] rangos = (RangoEdadVO[]) usoeducativoSession.getRangosEdad()
			.toArray(new RangoEdadVO[usoeducativoSession.getRangosEdad().size()]);
		if(rangos != null && rangos.length > 0){
			for(i = 0; i < rangos.length; i++){
				if(rangos[i].getTextos() != null && rangos[i].getTextos().length > 0){
					for(int j = 0; j < rangos[i].getTextos().length; j++){
						String texto = rangos[i].getTextos()[j].getTexto();
						String idiom = rangos[i].getTextos()[j].getIdioma();
						if(texto != null && !texto.equals("") && (idiom == null || idiom.equals(""))){
							error = datosResources.getString("CAV.5.6.1");
							if(!mensajes.contains(error))
								mensajes.add(error); //si descripción está relleno, el idioma también debe estarlo
						}
						if((texto == null || texto.equals("")) && idiom != null && !idiom.equals("")){
							error = datosResources.getString("CAV.5.6.2");
							if(!mensajes.contains(error))
								mensajes.add(error); //si idioma está relleno, descripción también debe estarlo
						}
					}
				}
			}
		}
		
		
	  	//TIEMPO DE APRENDIZAJE
		//DESCRIPCIONES
		todosVacios=true;
		String descripcion;
		DescripcionVO[] tiempoApDescripcion = (DescripcionVO[])usoeducativoSession.getTiempoApDescripcion()
				.toArray(new DescripcionVO[usoeducativoSession.getTiempoApDescripcion().size()]);
		for(i=0;i<tiempoApDescripcion.length && todosVacios;i++)
		{
			for(int j=0;j<tiempoApDescripcion[i].getTextos().length && todosVacios;j++)
			{
				descripcion=tiempoApDescripcion[i].getTextos()[j].getTexto();
				String idiom = tiempoApDescripcion[i].getTextos()[j].getIdioma();
				if((descripcion!=null && !descripcion.trim().equals("")) || (idiom != null && !idiom.trim().equals("")))
				{
					todosVacios=false;
				}
			}
		}
		
		if(tiempoApDescripcion != null && tiempoApDescripcion.length > 0){
			for(i = 0; i < tiempoApDescripcion.length; i++){
				if(tiempoApDescripcion[i].getTextos() != null && tiempoApDescripcion[i].getTextos().length > 0){
					for(int j = 0; j < tiempoApDescripcion[i].getTextos().length; j++){
						String texto = tiempoApDescripcion[i].getTextos()[j].getTexto();
						String idiom = tiempoApDescripcion[i].getTextos()[j].getIdioma();
						if(texto != null && !texto.equals("") && (idiom == null || idiom.equals(""))){
							error = datosResources.getString("CAV.5.9.3");
							if(!mensajes.contains(error))
								mensajes.add(error); //si descripción está relleno, el idioma también debe estarlo
						}
						if((texto == null || texto.equals("")) && idiom != null && !idiom.equals("")){
							error = datosResources.getString("CAV.5.9.4");
							if(!mensajes.contains(error))
								mensajes.add(error); //si idioma está relleno, descripción también debe estarlo
						}
					}
				}
			}
		}
		
		//DURACION
		boolean duracionVacia=false;
		boolean duracionConLetras=false;
		
		String fechaTemp=usoeducativoSession.getAnyos()+usoeducativoSession.getMeses()+usoeducativoSession.getDias()+
		usoeducativoSession.getHoras()+usoeducativoSession.getMinutos()+usoeducativoSession.getSegundosP1()+usoeducativoSession.getSegundosP2();

		if(fechaTemp.trim().equals("")){
			duracionVacia=true;
		}
//		if(	(usoeducativoSession.getAnyos()!=null && usoeducativoSession.getAnyos().trim().equals("")) && 
//			(usoeducativoSession.getMeses()!=null && usoeducativoSession.getMeses().trim().equals("")) &&
//			(usoeducativoSession.getDias()!=null && usoeducativoSession.getDias().trim().equals("")) &&
//			(usoeducativoSession.getHoras()!=null && usoeducativoSession.getHoras().trim().equals("")) &&
//			(usoeducativoSession.getMinutos()!=null && usoeducativoSession.getMinutos().trim().equals("")) &&
//			(usoeducativoSession.getSegundosP1()!=null && usoeducativoSession.getSegundosP1().trim().equals("")) &&
//			(usoeducativoSession.getSegundosP2()!=null && usoeducativoSession.getSegundosP2().trim().equals("")))
//		{
//			//si todos los campos dia, mes o anyo esta vacio, fecha esta vacio
//			duracionVacia=true;
//		}	
		
		if(!fechaTemp.trim().matches("[0-9]*")) {
			duracionConLetras=true;
		}
//		if(	(usoeducativoSession.getAnyos()!=null && !usoeducativoSession.getAnyos().trim().equals("")&&!usoeducativoSession.getAnyos().trim().matches("[0-9]*")) || 
//				(usoeducativoSession.getMeses()!=null &&!usoeducativoSession.getMeses().trim().equals("")&& !usoeducativoSession.getMeses().trim().matches("[0-9]*")) ||
//				(usoeducativoSession.getDias()!=null &&!usoeducativoSession.getDias().trim().equals("") &&!usoeducativoSession.getDias().trim().matches("[0-9]*")) ||
//				(usoeducativoSession.getHoras()!=null &&!usoeducativoSession.getHoras().trim().equals("") &&!usoeducativoSession.getHoras().trim().matches("[0-9]*")) ||
//				(usoeducativoSession.getMinutos()!=null &&!usoeducativoSession.getMinutos().trim().equals("") &&!usoeducativoSession.getMinutos().trim().matches("[0-9]*")) ||
//				(usoeducativoSession.getSegundosP1()!=null &&!usoeducativoSession.getSegundosP1().trim().equals("")&&!usoeducativoSession.getSegundosP1().trim().matches("[0-9]*")) ||
//				(usoeducativoSession.getSegundosP2()!=null &&!usoeducativoSession.getSegundosP2().trim().equals("") &&!usoeducativoSession.getSegundosP2().trim().matches("[0-9]*")))
//			{
//				//si alguno de los campos dia, mes o anyo no es un número, la fecha es incorrecta
//				duracionConLetras=true;
//			}
		error = datosResources.getString("CAV.5.9.2");
		if(todosVacios && !duracionVacia && !mensajes.contains(error))
			mensajes.add(datosResources.getString("CAV.5.9.2"));//La descripci&oacute;n dela duraci&oacute;n es obligatoria si existe una duraci&oacute;n
		error = datosResources.getString("CAV.5.9.1");
		if(!todosVacios && duracionVacia && !mensajes.contains(error))
			mensajes.add(datosResources.getString("CAV.5.9.1"));//La duraci&oacute;n es obligatoria si existe una descripci&oacute;n de duraci&oacute;n
	  if(duracionConLetras){
       	mensajes.add(datosResources.getString("CAV.5.9.1.2"));//La duraci&oacute;n debe indicarse con n&uacute;meros
    }
		
		//ORIENTACIONES DIDACTICAS
		DescripcionVO[] descripciones = (DescripcionVO[]) usoeducativoSession.getDescripciones()
			.toArray(new DescripcionVO[usoeducativoSession.getDescripciones().size()]);
		if(descripciones != null && descripciones.length > 0){
			for(i = 0; i < descripciones.length; i++){
				if(descripciones[i].getTextos() != null && descripciones[i].getTextos().length > 0){
					for(int j = 0; j < descripciones[i].getTextos().length; j++){
						String texto = descripciones[i].getTextos()[j].getTexto();
						String idiom = descripciones[i].getTextos()[j].getIdioma();
						if(texto != null && !texto.equals("") && (idiom == null || idiom.equals(""))){
							error = datosResources.getString("CAV.5.10.1");
							if(!mensajes.contains(error))
								mensajes.add(error); //si descripción está relleno, el idioma también debe estarlo
						}
						if((texto == null || texto.equals("")) && idiom != null && !idiom.equals("")){
							error = datosResources.getString("CAV.5.10.2");
							if(!mensajes.contains(error))
								mensajes.add(error); //si idioma está relleno, descripción también debe estarlo
						}
					}
				}
			}
		}
	  	
	  	//añadir mensajesError en el form
		if(mensajes.size()>0)
		{
			form.setMensajesError(mensajes);
			return false;
		}else{
			return true;
		}
    }



    public final void guardarUsoEducativo(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.categoriasAvanzado.usoeducativo.detalleUsoEducativo.GuardarUsoEducativoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		boolean errorFaltaIdioma = false;
		boolean errorFaltaTexto = false;
		boolean errorNoNumero = false;
		boolean errorSegundos = false;

		LomAvanzadoVO auxAvanzado=null;
		AvEducationalVO educacion = null;
		AvEducationalVO[] arrayEducacion = null;
		CatalogadorAvSession catalogadorAvSession=this.getCatalogadorAvSession(request);
		
		try{
			auxAvanzado=new LomAvanzadoVO();
			 
			educacion = new AvEducationalVO();
			arrayEducacion = new AvEducationalVO[1];
			arrayEducacion[0] = educacion;
			auxAvanzado.setEducational(arrayEducacion);
			
			String source=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES) ;//esquemaDeMetadatos
			
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
	
	    	//Obtenemos la longitudes de los VOs, que pasamos a cambioFormulario para que se obtengan del request los cambios que hemos hecho
	    	//Pues en el form que nos viene como parametro no los guarda.
	    	int longitudDescripciones=0;
	    	int longitudIdiomas=0;
			int longitudTipoRecurso=0;
			int longitudRoles=0;
			int longitudContextos=0;
			int longitudProcesos=0;
			int longitudTADesc=0;
			int longitudRangos=0;
	    	int[] longitudTextosDesc = new int[0];
	    	int[] longitudTextosRangosEdad = new int[0];
	   
	    	if (valor instanceof DetalleUsoEduFormImpl) {
	    		DetalleUsoEduFormImpl formulario = ((DetalleUsoEduFormImpl)valor);
	    		Object[] desc = formulario.getDescripcionesAsArray();
				longitudTextosDesc = new int[desc.length];
				for(int i= 0;i< desc.length;i++){
					longitudTextosDesc[i]=((DescripcionVO)(desc[i])).getTextos().length;
				}
				Object[] eTipica = formulario.getEdadTipicaAsArray();
				longitudTextosRangosEdad= new int[eTipica.length];
				for(int i= 0;i< eTipica.length;i++){
					longitudTextosRangosEdad[i]=((RangoEdadVO)eTipica[i]).getTextos().length;
				}
				longitudDescripciones=desc.length;
			  	longitudRangos=formulario.getEdadTipicaAsArray().length;
			  	longitudTipoRecurso=formulario.getTipoRecursoAsArray().length;
			  	longitudRoles=formulario.getRolUsuarioAsArray().length;
			  	longitudIdiomas=formulario.getIdiomasAsArray().length;
				longitudContextos=formulario.getContextoAsArray().length;
				longitudProcesos=formulario.getProcesoCognitivoAsArray().length;
			  	longitudTADesc=(((DescripcionVO[])formulario.getTiempoApDescripcionAsArray())[0]).getTextos().length; 		
	    	}
	    	else if(valor instanceof DetalleUsoEducativoSubmitFormImpl){
	    		DetalleUsoEducativoSubmitFormImpl formulario = ((DetalleUsoEducativoSubmitFormImpl)valor);
	    		Object[] desc = formulario.getDescripcionesAsArray();
				longitudTextosDesc = new int[desc.length];
				for(int i= 0;i< desc.length;i++){
					longitudTextosDesc[i]=((DescripcionVO)(desc[i])).getTextos().length;
				}
				Object[] eTipica = formulario.getEdadTipicaAsArray();
				longitudTextosRangosEdad= new int[eTipica.length];
				for(int i= 0;i< eTipica.length;i++){
					longitudTextosRangosEdad[i]=((RangoEdadVO)eTipica[i]).getTextos().length;
				}
				longitudDescripciones=desc.length;
			  	longitudRangos=formulario.getEdadTipicaAsArray().length;
			  	longitudTipoRecurso=formulario.getTipoRecursoAsArray().length;
			  	longitudRoles=formulario.getRolUsuarioAsArray().length;
			  	longitudIdiomas=formulario.getIdiomasAsArray().length;
				longitudContextos=formulario.getContextoAsArray().length;
				longitudProcesos=formulario.getProcesoCognitivoAsArray().length;
			  	longitudTADesc=(((DescripcionVO[])formulario.getTiempoApDescripcionAsArray())[0]).getTextos().length; 		
	    	}
	    	else if(valor instanceof UsoEducativoValidoVolverFormImpl){
	    		UsoEducativoValidoVolverFormImpl formulario = ((UsoEducativoValidoVolverFormImpl)valor);
	    		Object[] desc = formulario.getDescripcionesAsArray();
				longitudTextosDesc = new int[desc.length];
				for(int i= 0;i< desc.length;i++){
					longitudTextosDesc[i]=((DescripcionVO)(desc[i])).getTextos().length;
				}
				Object[] eTipica = formulario.getEdadTipicaAsArray();
				longitudTextosRangosEdad= new int[eTipica.length];
				for(int i= 0;i< eTipica.length;i++){
					longitudTextosRangosEdad[i]=((RangoEdadVO)eTipica[i]).getTextos().length;
				}
				longitudDescripciones=desc.length;
			  	longitudRangos=formulario.getEdadTipicaAsArray().length;
			  	longitudTipoRecurso=formulario.getTipoRecursoAsArray().length;
			  	longitudRoles=formulario.getRolUsuarioAsArray().length;
			  	longitudIdiomas=formulario.getIdiomasAsArray().length;
				longitudContextos=formulario.getContextoAsArray().length;
				longitudProcesos=formulario.getProcesoCognitivoAsArray().length;
			  	longitudTADesc=(((DescripcionVO[])formulario.getTiempoApDescripcionAsArray())[0]).getTextos().length; 		
	    	}
	    	else if(valor instanceof UsoEducativoNoValidoVolverFormImpl){
	    		UsoEducativoNoValidoVolverFormImpl formulario = ((UsoEducativoNoValidoVolverFormImpl)valor);
	    		Object[] desc = formulario.getDescripcionesAsArray();
				longitudTextosDesc = new int[desc.length];
				for(int i= 0;i< desc.length;i++){
					longitudTextosDesc[i]=((DescripcionVO)(desc[i])).getTextos().length;
				}
				Object[] eTipica = formulario.getEdadTipicaAsArray();
				longitudTextosRangosEdad= new int[eTipica.length];
				for(int i= 0;i< eTipica.length;i++){
					longitudTextosRangosEdad[i]=((RangoEdadVO)eTipica[i]).getTextos().length;
				}
				longitudDescripciones=desc.length;
			  	longitudRangos=formulario.getEdadTipicaAsArray().length;
			  	longitudTipoRecurso=formulario.getTipoRecursoAsArray().length;
			  	longitudRoles=formulario.getRolUsuarioAsArray().length;
			  	longitudIdiomas=formulario.getIdiomasAsArray().length;
				longitudContextos=formulario.getContextoAsArray().length;
				longitudProcesos=formulario.getProcesoCognitivoAsArray().length;
			  	longitudTADesc=(((DescripcionVO[])formulario.getTiempoApDescripcionAsArray())[0]).getTextos().length; 		
	    	}
	    	
	    	//borrar session
	    	request.getSession().removeAttribute(UsoEducativoSession.SESSION_KEY);
	    	//Recogemos los cambio del request mediante parametros
	    	cambioFormulario(request,longitudTextosDesc,longitudTextosRangosEdad,longitudDescripciones,longitudRangos,longitudIdiomas,
	    			longitudTipoRecurso,longitudRoles,longitudContextos,longitudProcesos,longitudTADesc,source);
	    	//recoger session
	    	UsoEducativoSession usoeducativoSession = this.getUsoEducativoSession(request);
	    	
	    	//Guardamos los cambios en el form obtenido de request.getSession.getAttribute("form") para que en caso de faltar
		  	//algun idioma o texto sin rellenar cargue el formulario con lo cambio y no perdamos todo lo modificado hasta ese
		  	//momento, si no lo hicieramos cargaría el formulario anterior. 
		  	
		  	//IDIOMAS
	    	IdiomaVO[] idiomas = (IdiomaVO[])usoeducativoSession.getIdiomas()
				.toArray(new IdiomaVO[usoeducativoSession.getIdiomas().size()]);
		  	IdiomaVO[] 	idiomasAux = new IdiomaVO[idiomas.length];
	    	for(int i=0;i<idiomas.length;i++){
	    		IdiomaVO auxIdioma = new IdiomaVO();
	    		auxIdioma.setTexto(idiomas[i].getTexto());
	    		idiomasAux[i] = auxIdioma;
	    	}
	    	
		  	//TIPOS RECURSOS
	    	SourceValueVO[] tiposRecurso = (SourceValueVO[]) usoeducativoSession.getTiposRecurso()
				.toArray(new SourceValueVO[usoeducativoSession.getTiposRecurso().size()]);
		  	SourceValueVO[] tiposRecursoAux = new SourceValueVO[tiposRecurso.length];
	    	for(int i=0;i<tiposRecurso.length;i++){
	    		SourceValueVO auxTipoRecurso = new SourceValueVO();
	    		auxTipoRecurso.setValor(tiposRecurso[i].getValor());
	    		auxTipoRecurso.setSource(source);
	    		tiposRecursoAux[i] = auxTipoRecurso;
	    	}
	    	
		  	//ROLES O DESTINATARIOS
	    	SourceValueVO[] roles = (SourceValueVO[]) usoeducativoSession.getRoles()
				.toArray(new SourceValueVO[usoeducativoSession.getRoles().size()]);
		  	SourceValueVO[] rolesAux = new SourceValueVO[roles.length];
	    	for(int i=0;i<roles.length;i++){
	    		SourceValueVO auxRoles = new SourceValueVO();
	    		auxRoles.setValor(roles[i].getValor());
	    		auxRoles.setSource(source);
	    		rolesAux[i] = auxRoles;
	    	}
	    	
		  	//CONTEXTOS
	    	SourceValueVO[] contextos = (SourceValueVO[]) usoeducativoSession.getContextos()
				.toArray(new SourceValueVO[usoeducativoSession.getContextos().size()]);
		  	SourceValueVO[] contextosAux = new SourceValueVO[contextos.length];
	    	for(int i=0;i<contextos.length;i++){
	    		SourceValueVO auxContexto = new SourceValueVO();
	    		auxContexto.setValor(contextos[i].getValor());
	    		auxContexto.setSource(source);
	    		contextosAux[i] = auxContexto;
	    	}
	    	
		  	//PROCESOS COGNITIVOS
	    	SourceValueVO[] procesosCognitivos = (SourceValueVO[]) usoeducativoSession.getProcesosCognitivos()
				.toArray(new SourceValueVO[usoeducativoSession.getProcesosCognitivos().size()]);
		  	SourceValueVO[] procesosAux = new SourceValueVO[procesosCognitivos.length];
	    	for(int i=0;i<procesosCognitivos.length;i++){
	    		SourceValueVO auxProceso = new SourceValueVO();
	    		auxProceso.setValor(procesosCognitivos[i].getValor());
	    		auxProceso.setSource(source);
	    		procesosAux[i] = auxProceso;
	    	}
		  	
	    	//DESCRIPCION TIEMPO APRENDIZAJE
	    	DescripcionVO[] taDescripcionAux = new DescripcionVO[1];
	    	DescripcionVO[] tiempoApDescripcion = (DescripcionVO[]) usoeducativoSession.getTiempoApDescripcion()
				.toArray(new DescripcionVO[usoeducativoSession.getTiempoApDescripcion().size()]);
	    	LangStringVO[] langTADescAux = new LangStringVO[tiempoApDescripcion[0].getTextos().length];
	    	for(int i=0;i<tiempoApDescripcion[0].getTextos().length;i++){
	    		LangStringVO nuevoLang = new LangStringVO();
	    		nuevoLang.setIdioma(tiempoApDescripcion[0].getTextos()[i].getIdioma());
	    		nuevoLang.setTexto(tiempoApDescripcion[0].getTextos()[i].getTexto().trim());
	    		langTADescAux[i] = nuevoLang;
	    	}
	    	DescripcionVO taDescAux= new DescripcionVO();
	    	taDescAux.setTextos(langTADescAux);
	    	taDescripcionAux[0]=taDescAux;
	    	
	    	//DESCRIPCIONES
	    	DescripcionVO[] descripciones = (DescripcionVO[]) usoeducativoSession.getDescripciones()
				.toArray(new DescripcionVO[usoeducativoSession.getDescripciones().size()]);
	    	DescripcionVO[]	descripcionesAux = new DescripcionVO[descripciones.length];
	    	for(int i=0;i<descripciones.length;i++){
	    		DescripcionVO descripAux= new DescripcionVO();
	    		LangStringVO[] langDescrip = descripciones[i].getTextos();
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
	
	    	//RANGOS EDAD
	    	RangoEdadVO[] rangosEdad = (RangoEdadVO[]) usoeducativoSession.getRangosEdad()
	    			.toArray(new RangoEdadVO[usoeducativoSession.getRangosEdad().size()]);
	    	RangoEdadVO[] rangosEdadAux = new RangoEdadVO[rangosEdad.length];
	    	for(int i=0;i<rangosEdad.length;i++){
	    		RangoEdadVO rEdadAux= new RangoEdadVO();
	    		LangStringVO[] langREdad= rangosEdad[i].getTextos();
	    		LangStringVO[] langREdadAux = new LangStringVO[langREdad.length];
	    		for(int j=0;j<langREdad.length;j++){
	    			LangStringVO nuevoLang = new LangStringVO();
	    			nuevoLang.setIdioma(langREdad[j].getIdioma());
	    			nuevoLang.setTexto(langREdad[j].getTexto().trim());
	    			langREdadAux[j] = nuevoLang;
	    		}
	    		rEdadAux.setTextos(langREdadAux);
	    		rangosEdadAux[i]=rEdadAux;
	    	}
	    	
	    	String anyos = usoeducativoSession.getAnyos();
	    	String meses = usoeducativoSession.getMeses();
	    	String dias = usoeducativoSession.getDias();
	    	String horas = usoeducativoSession.getHoras();
	    	String minutos = usoeducativoSession.getMinutos();
	    	String segundosP1 = usoeducativoSession.getSegundosP1();
	    	String segundosP2 = usoeducativoSession.getSegundosP2();
	    	
	    	if (valor instanceof DetalleUsoEduFormImpl) {
	    		DetalleUsoEduFormImpl formulario =(DetalleUsoEduFormImpl)valor;
	    		formulario.setIdiomasAsArray(idiomasAux);
	    		formulario.setTiempoApDescripcionAsArray(taDescripcionAux);
	    		formulario.setDescripcionesAsArray(descripcionesAux);
	    		formulario.setEdadTipicaAsArray(rangosEdadAux);
	    		formulario.setTipoRecursoAsArray(tiposRecursoAux);
	    		formulario.setRolUsuarioAsArray(rolesAux);
	    		formulario.setContextoAsArray(contextosAux);
	    		formulario.setProcesoCognitivoAsArray(procesosAux);
	    		formulario.setAnyo(anyos);
	    		formulario.setMes(meses);
	    		formulario.setDia(dias);
	    		formulario.setHora(horas);
	    		formulario.setMinutos(minutos);
	    		formulario.setSegundosP1(segundosP1);
	    		formulario.setSegundosP2(segundosP2);
			}
	    	else if(valor instanceof DetalleUsoEducativoSubmitFormImpl){
	    		DetalleUsoEducativoSubmitFormImpl formulario =(DetalleUsoEducativoSubmitFormImpl)valor;
	    		formulario.setIdiomasAsArray(idiomasAux);
	    		formulario.setTiempoApDescripcionAsArray(taDescripcionAux);
	    		formulario.setDescripcionesAsArray(descripcionesAux);
	    		formulario.setEdadTipicaAsArray(rangosEdadAux);
	    		formulario.setTipoRecursoAsArray(tiposRecursoAux);
	    		formulario.setRolUsuarioAsArray(rolesAux);
	    		formulario.setContextoAsArray(contextosAux);
	    		formulario.setProcesoCognitivoAsArray(procesosAux);
	    		formulario.setAnyo(anyos);
	    		formulario.setMes(meses);
	    		formulario.setDia(dias);
	    		formulario.setHora(horas);
	    		formulario.setMinutos(minutos);
	    		formulario.setSegundosP1(segundosP1);
	    		formulario.setSegundosP2(segundosP2);
	      	}
	    	else if(valor instanceof UsoEducativoValidoVolverFormImpl){
	    		UsoEducativoValidoVolverFormImpl formulario =(UsoEducativoValidoVolverFormImpl)valor;
	    		formulario.setIdiomasAsArray(idiomasAux);
	    		formulario.setTiempoApDescripcionAsArray(taDescripcionAux);
	    		formulario.setDescripcionesAsArray(descripcionesAux);
	    		formulario.setEdadTipicaAsArray(rangosEdadAux);
	    		formulario.setTipoRecursoAsArray(tiposRecursoAux);
	    		formulario.setRolUsuarioAsArray(rolesAux);
	    		formulario.setContextoAsArray(contextosAux);
	    		formulario.setProcesoCognitivoAsArray(procesosAux);
	    		formulario.setAnyo(anyos);
	    		formulario.setMes(meses);
	    		formulario.setDia(dias);
	    		formulario.setHora(horas);
	    		formulario.setMinutos(minutos);
	    		formulario.setSegundosP1(segundosP1);
	    		formulario.setSegundosP2(segundosP2);
	    	}
	    	else if(valor instanceof UsoEducativoNoValidoVolverFormImpl){
	    		UsoEducativoNoValidoVolverFormImpl formulario =(UsoEducativoNoValidoVolverFormImpl)valor;
	    		formulario.setIdiomasAsArray(idiomasAux);
	    		formulario.setTiempoApDescripcionAsArray(taDescripcionAux);
	    		formulario.setDescripcionesAsArray(descripcionesAux);
	    		formulario.setEdadTipicaAsArray(rangosEdadAux);
	    		formulario.setTipoRecursoAsArray(tiposRecursoAux);
	    		formulario.setRolUsuarioAsArray(rolesAux);
	    		formulario.setContextoAsArray(contextosAux);
	    		formulario.setProcesoCognitivoAsArray(procesosAux);
	    		formulario.setAnyo(anyos);
	    		formulario.setMes(meses);
	    		formulario.setDia(dias);
	    		formulario.setHora(horas);
	    		formulario.setMinutos(minutos);
	    		formulario.setSegundosP1(segundosP1);
	    		formulario.setSegundosP2(segundosP2);
	    	}
			
		  	//tipoInteractividad =form.getTipoInteractividad();
	    	usoeducativoSession.setTipoInteractividad(form.getTipoInteractividad());
		  	//nivelInteractividad=form.getNivelInteractividad();
	    	usoeducativoSession.setNivelInteractividad(form.getNivelInteractividad());
		  	//densidadSemantica=form.getDensidadSemantica();
	    	usoeducativoSession.setDensidadSemantica(form.getDensidadSemantica());
		  	//dificultad=form.getDificultad();
	    	usoeducativoSession.setDificultad(form.getDificultad());
		  	
			//Obtnemos los terminos de los ids seleccionados en todos los combos, y los guarda en los VO 
			dameTerminoId(usoeducativoSession);         
			 
			 //cargar ayuda contextual    
			 cargarAyuda(usoeducativoSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
			
			//Comprobamos si los campos ha sido rellenados adecuadamente
			
			//TIEMPO DE APRENDIZAJE
			DuracionVO duracionVO = new DuracionVO();
			String duracion="";
			//TODO
			logger.error("Dia es "+dias+", meses es "+meses+", anyos es "+anyos);
			logger.error("Horas es "+horas+", minutos es "+minutos+", segundosP1 es "+segundosP1+", segundosP2 es "+segundosP2);
			if (!dias.equals("") || !meses.equals("") || !anyos.equals("")){
				duracion="P";
				usoeducativoSession.setDuracion(duracion);
	
				if(anyos.equals("")) {
					anyos="0";
				}
					if (!anyos.matches("\\d{1,}"))
						errorNoNumero = true;
					else{
						duracion = duracion + anyos + "Y";
						usoeducativoSession.setDuracion(duracion);
					}
				
				if(meses.equals("")) {
					meses="0";
				}
					if (!meses.matches("\\d{1,}"))
						errorNoNumero = true;
					else{
						duracion = duracion + meses + "M";
						usoeducativoSession.setDuracion(duracion);
					}
				
				if(dias.equals("")){
					dias="0";
				}
					if (!dias.matches("\\d{1,}"))
						errorNoNumero = true;
					else{	
						duracion = duracion + dias + "D";
						usoeducativoSession.setDuracion(duracion);
					}
			}
			if (!horas.equals("") || !minutos.equals("") || !segundosP1.equals("") || !segundosP2.equals("")){
				if (duracion.equals("")) {
					duracion = "PT";
					usoeducativoSession.setDuracion(duracion);
				}
				else{
					duracion = duracion + "T";
					usoeducativoSession.setDuracion(duracion);
				}
				if (horas.equals("")) {
					horas="0";
				}
					if (!horas.matches("\\d{1,}"))
						errorNoNumero = true;
					else{
						duracion = duracion + horas + "H";
						usoeducativoSession.setDuracion(duracion);
					}
				if (minutos.equals("")) {
					minutos="0";
				}
					if (!minutos.matches("\\d{1,}"))
						errorNoNumero = true;
					else {
						duracion = duracion + minutos + "M";
						usoeducativoSession.setDuracion(duracion);
					}
				if (segundosP1.equals("")) {
					segundosP1="0";
				}
					if (!segundosP1.matches("\\d{1,}"))
						errorNoNumero = true;
					else
						if (segundosP2.equals("")) {
							duracion = duracion + segundosP1 + "S";
							usoeducativoSession.setDuracion(duracion);
						}
						else
							if (!segundosP2.matches("\\d{1,}"))
								errorNoNumero = true;
							else{
								duracion = duracion + segundosP1 + "." + segundosP2 + "S";
								usoeducativoSession.setDuracion(duracion);
							}
//				if (segundosP1.equals("") && !segundosP2.equals(""))
//					errorSegundos = true;
			}
			duracionVO.setDuracion(duracion);
			//DESCRIPCIONES TIEMPO APRENDIZAJE
			DescripcionVO taDescripcionVO=new DescripcionVO();
			if (tiempoApDescripcion!= null && tiempoApDescripcion.length != 0){
				DescripcionVO taDescripcion = tiempoApDescripcion[0];
				LangStringVO[] textos=taDescripcion.getTextos();
				ArrayList<LangStringVO> lista=new ArrayList<LangStringVO>();
				for(int j=0;j<textos.length;j++){
					LangStringVO lTADesc=textos[j];
						 
					if(lTADesc !=null){
						LangStringVO lLang=new LangStringVO();
						String idioma=lTADesc.getIdioma();
						String texto=lTADesc.getTexto();
						if(((idioma!=null)&&(!idioma.equals(""))) && ((texto!=null)&&(!texto.trim().equals("")))){
							lLang.setIdioma(idioma);
							lLang.setTexto(texto.trim());
							lista.add(lLang);
						}
						else{
							if (idioma.equals("") && !texto.equals("") )
								errorFaltaIdioma = true;
							if (!idioma.equals("") && texto.equals(""))
								errorFaltaTexto = true;
						}
					}						 						 	
				}
				LangStringVO[] lLangS=lista.toArray(new LangStringVO[lista.size()]);
				if(lLangS.length == 0)
					taDescripcionVO = null;
				else
					taDescripcionVO.setTextos(lLangS); 
			}
			else 
				taDescripcionVO = null;
			if((duracion==null || duracion.equals("")) && taDescripcionVO==null )
				duracionVO=null;
			else
				duracionVO.setDescripcion(taDescripcionVO);
			
			auxAvanzado.getEducational()[0].setTiempoAprendizaje(duracionVO);
			 
			//IDIOMAS
			ArrayList<IdiomaVO> lTextos=new ArrayList<IdiomaVO>();
			//if(!((idiomas.length == 1) && (idiomas[0].getTexto().equals("")))){
			if(idiomas != null && !((idiomas.length == 1) && (idiomas[0].getTexto().equals("")))){
				for(int i=0;i<idiomas.length;i++){
					if(idiomas[i]!=null){
						IdiomaVO idiomaVO=new IdiomaVO();
						IdiomaVO idiVO=idiomas[i];
						if((idiVO.getTexto()!=null)&&(!idiVO.getTexto().trim().equals(""))){
							idiomaVO.setTexto(idiVO.getTexto().trim());
							lTextos.add(idiomaVO);
						}
					}
					else
						idiomas =null;
				}
				idiomas=lTextos.toArray(new IdiomaVO[lTextos.size()]);
				if(idiomas.length == 0)
					idiomas = null;
			}
			else{
				idiomas=null;
			}
			
			auxAvanzado.getEducational()[0].setIdiomas(idiomas);
			
			//TIPOS RECURSOS
			ArrayList<SourceValueVO> lTextosTR=new ArrayList<SourceValueVO>();
			if(tiposRecurso != null && !((tiposRecurso.length == 1) && (tiposRecurso[0].getValor().equals("")))){
				for(int i=0;i<tiposRecurso.length;i++){
					if(tiposRecurso[i]!=null){
						SourceValueVO tipoRecursoVO=new SourceValueVO();
						SourceValueVO tRecursoVO=tiposRecurso[i];
						if((tRecursoVO.getValor()!=null)&&(!tRecursoVO.getValor().equals(""))){
							tipoRecursoVO.setValor(tRecursoVO.getValor());
							tipoRecursoVO.setSource(source);
							lTextosTR.add(tipoRecursoVO);
						}
					}
					else
						tiposRecurso =null;
				}
				tiposRecurso=lTextosTR.toArray(new SourceValueVO[lTextosTR.size()]);
				if(tiposRecurso.length == 0)
					tiposRecurso = null;
			}
			else{
				tiposRecurso=null;
			}		
			auxAvanzado.getEducational()[0].setTiposrecursoedu(tiposRecurso);
			
			//ROLES
			ArrayList<SourceValueVO> lTextosR=new ArrayList<SourceValueVO>();
			if(roles != null && !((roles.length == 1) && (roles[0].getValor().equals("")))){
				for(int i=0;i<roles.length;i++){
					if(roles[i]!=null){
						SourceValueVO rolesVO=new SourceValueVO();
						SourceValueVO auxRolesVO=roles[i];
						if((auxRolesVO.getValor()!=null)&&(!auxRolesVO.getValor().equals(""))){
							rolesVO.setValor(auxRolesVO.getValor());
							rolesVO.setSource(source);
							lTextosR.add(rolesVO);
						}
					}
					else
						roles =null;
				}
				roles=lTextosR.toArray(new SourceValueVO[lTextosR.size()]);
				if(roles.length == 0)
					roles = null;
			}
			else{
				roles=null;
			}	
			auxAvanzado.getEducational()[0].setDestinatarios(roles);
			
			//CONTEXTOS
			ArrayList<SourceValueVO> lTextosC=new ArrayList<SourceValueVO>();
			if(contextos != null && !((contextos.length == 1) && (contextos[0].getValor().equals("")))){
				for(int i=0;i<contextos.length;i++){
					if(contextos[i]!=null){
						SourceValueVO contextoVO=new SourceValueVO();
						SourceValueVO auxContextoVO=contextos[i];
						if((auxContextoVO.getValor()!=null)&&(!auxContextoVO.getValor().equals(""))){
							contextoVO.setValor(auxContextoVO.getValor());
							contextoVO.setSource(source);
							lTextosC.add(contextoVO);
						}
					}
					else
						contextos =null;
				}
				contextos=lTextosC.toArray(new SourceValueVO[lTextosC.size()]);
				if(contextos.length == 0)
					contextos = null;
			}
			else{
				contextos=null;
			}	
			auxAvanzado.getEducational()[0].setContextos(contextos);
			
			//PROCESOS COGNITIVOS
			ArrayList<SourceValueVO> lTextosPC=new ArrayList<SourceValueVO>();
			if(procesosCognitivos != null && !((procesosCognitivos.length == 1) && (procesosCognitivos[0].getValor().equals("")))){
				for(int i=0;i<procesosCognitivos.length;i++){
					if(procesosCognitivos[i]!=null){
						SourceValueVO procesoVO=new SourceValueVO();
						SourceValueVO auxProcesoVO=procesosCognitivos[i];
						if((auxProcesoVO.getValor()!=null)&&(!auxProcesoVO.getValor().equals(""))){
							procesoVO.setValor(auxProcesoVO.getValor());
							procesoVO.setSource(source);
							lTextosPC.add(procesoVO);
						}
					}
					else
						procesosCognitivos =null;
				}
				procesosCognitivos=lTextosPC.toArray(new SourceValueVO[lTextosPC.size()]);
				if(procesosCognitivos.length == 0)
					procesosCognitivos = null;
			}
			else{
				procesosCognitivos=null;
			}	
			auxAvanzado.getEducational()[0].setProcesoscognitivos(procesosCognitivos);
			
			//DESCRIPCIONES
			ArrayList<DescripcionVO> listaDesc=new ArrayList<DescripcionVO>();
			if(descripciones!=null && descripciones.length != 0){
				LangStringVO[] textos = descripciones[0].getTextos();
				if(descripciones.length == 1 && textos.length == 1 && textos[0].getIdioma().equals("") 
						&& textos[0].getTexto().trim().equals("")){
					descripciones = null;
				}
				else{
					for(int i=0;i<descripciones.length;i++){
						if(descripciones[i]!=null){
							ArrayList<LangStringVO> listDesc=new ArrayList<LangStringVO>();
							DescripcionVO lDesc=descripciones[i];
							DescripcionVO lDescripciones=new DescripcionVO();
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
									if (idioma.equals("") && !texto.equals("") )
										errorFaltaIdioma = true;
									if (!idioma.equals("") && texto.equals("") )
										errorFaltaTexto = true;
								} 
							}
							if (listDesc.size() != 0){
								LangStringVO[] langs=listDesc.toArray(new LangStringVO[listDesc.size()]); 
								lDescripciones.setTextos(langs);   
								listaDesc.add(lDescripciones);
							}
						}
					}
					if(listaDesc.size()== 0)
						descripciones = null;
					else
						descripciones=listaDesc.toArray(new DescripcionVO[listaDesc.size()]);
				}
			}
			else{
				descripciones =null;
			}
			auxAvanzado.getEducational()[0].setDescripciones(descripciones);
			
			//RANGOS DE EDAD O EDAD TIPICA
			ArrayList<RangoEdadVO> listaRangos=null;
			if(rangosEdad!=null && rangosEdad.length !=0){
				LangStringVO[] textos = rangosEdad[0].getTextos();
				if (rangosEdad.length== 1 && textos.length == 1 && textos[0].getIdioma().equals("") 
						&& textos[0].getTexto().trim().equals("")){
					rangosEdad= null;		
				}
				else{
					listaRangos=new ArrayList<RangoEdadVO>();
					for(int i=0;i<rangosEdad.length;i++){
						if(rangosEdad[i]!=null){
							ArrayList<LangStringVO> listRangos=new ArrayList<LangStringVO>();
							RangoEdadVO lRangos=rangosEdad[i];
							RangoEdadVO lRangosEdad=new RangoEdadVO();
							for(int j=0;j<lRangos.getTextos().length;j++){
								LangStringVO lang=lRangos.getTextos()[j];
								LangStringVO lLang=new LangStringVO();
								String idioma=lang.getIdioma();
								String texto=lang.getTexto();
								if(((idioma!=null)&&(!idioma.equals("")))&&((texto!=null)&&(!texto.trim().equals("")))){
									 lLang.setIdioma(idioma);
									 lLang.setTexto(texto.trim());
									 listRangos.add(lLang);
								}
								else{
									if (idioma.equals("") && !texto.equals("") )
										errorFaltaIdioma = true;
									if (!idioma.equals("") && texto.equals("") )
										errorFaltaTexto = true;
									
								}
							}
							if (listRangos.size() != 0){
								LangStringVO[] langs=listRangos.toArray(new LangStringVO[listRangos.size()]); 
								lRangosEdad.setTextos(langs);  
								listaRangos.add(lRangosEdad);
							}
						}
					}
					if (listaRangos.size() == 0)
						rangosEdad = null;
					else
						rangosEdad=listaRangos.toArray(new RangoEdadVO[listaRangos.size()]);
				}
			}
			else
				rangosEdad =null;
			
			auxAvanzado.getEducational()[0].setRangoedades(rangosEdad);
			
			
			//TIPO INTERACTIVIDAD
			SourceValueVO tipoInteractividadSV=new SourceValueVO();
			
			if((usoeducativoSession.getTipoInteractividad()!=null)&&(!usoeducativoSession.getTipoInteractividad().equals(""))){
				tipoInteractividadSV.setValor(usoeducativoSession.getTipoInteractividad());
				tipoInteractividadSV.setSource(source);
			}
			else{
				tipoInteractividadSV = null;
			}
			auxAvanzado.getEducational()[0].setTipoDeInteractividad(tipoInteractividadSV);
			
			//NIVEL AGREGACION
			SourceValueVO nivel=new SourceValueVO();
			if((usoeducativoSession.getNivelInteractividad()!=null)&&(!usoeducativoSession.getNivelInteractividad().equals(""))){
				nivel.setSource(source);
				nivel.setValor(usoeducativoSession.getNivelInteractividad());
			}
			else{
				nivel = null;
			}
			auxAvanzado.getEducational()[0].setNivelInteractividad(nivel);
				
	
			//DENSIDAD SEMANTICA
			SourceValueVO densidad=new SourceValueVO();
			if((usoeducativoSession.getDensidadSemantica()!=null)&&(!usoeducativoSession.getDensidadSemantica().equals(""))){
				densidad.setSource(source);
				densidad.setValor(usoeducativoSession.getDensidadSemantica());
			}
			else{
				densidad = null;
			}
			auxAvanzado.getEducational()[0].setDensidadSemantica(densidad);
			
			//DIFICULTAD
			SourceValueVO dificultadSV=new SourceValueVO();
			if((usoeducativoSession.getDificultad()!=null)&&(!usoeducativoSession.getDificultad().equals(""))){
				dificultadSV.setSource(source);
				dificultadSV.setValor(usoeducativoSession.getDificultad());
			}
			else{
				dificultadSV = null;
			}
			auxAvanzado.getEducational()[0].setDificultad(dificultadSV);
			
			AvEducationalVO eduAux= auxAvanzado.getEducational()[0];
			
			if(eduAux.getContextos()==null &&
			   eduAux.getDensidadSemantica()==null && 
			   eduAux.getDescripciones()==null && 
			   eduAux.getDestinatarios()==null && 
			   eduAux.getDificultad()==null &&
			   eduAux.getIdiomas()==null && 
			   eduAux.getNivelInteractividad()==null && 
			   eduAux.getProcesoscognitivos()==null && 
			   eduAux.getRangoedades()==null &&
			   eduAux.getTiempoAprendizaje() == null &&
			   eduAux.getTipoDeInteractividad() ==null &&
			   eduAux.getTiposrecursoedu() ==null)
				auxAvanzado=null;
			
		} catch (Exception e){
			logger.error("Error en catalogadorWeb, categoría Detall Uso Educativo, metodo guardarUsoEducativo " + e);
			//throw new java.lang.Exception("detalle.uso.edu", e);
			saveErrorMessage(request, " Error en catalogadorWeb, categoría Detall Uso Educativo, metodo guardarUsoEducativo ");
		
		}	
		if(!errorFaltaIdioma && !errorFaltaTexto && !errorNoNumero && !errorSegundos){	
			try{
				//			Cargamos el objeto de sesion
				if(catalogadorAvSession.getMDSesion()==null){
					if(auxAvanzado != null)
						catalogadorAvSession.setMDSesion(auxAvanzado);
					
				}else{
					AvEducationalVO[] educaciones=catalogadorAvSession.getMDSesion().getEducational();
			       
					//		    	 comprobamos que educaciones no sea null, si es null nos creamos uno	
						 if (educaciones== null){
							 if(auxAvanzado!=null)
								 catalogadorAvSession.getMDSesion().setEducational(auxAvanzado.getEducational());
						 } 
						 else{ 
				    		 //creamos uno mas (cogemos la longitud de las educaciones pues el nuevo indice, si teniamos 4 educaciones los indices
							 //iban de 0 a 3 el indice para la nueva educacion seria 4 y ahora tendriamos 5 educaciones con indices de 0 a 4)
							 if (educaciones.length == iEdu){//GUARDAR CUANDO HEMOS PULSADO BOTON CREAR
								 ArrayList<AvEducationalVO> listaEdu= new ArrayList<AvEducationalVO>();
								 for(int i = 0; i<educaciones.length;i++)
									 listaEdu.add( educaciones[i]);
								 
								 if(auxAvanzado != null && auxAvanzado.getEducational()[0]!=null )
								 {
									 listaEdu.add( auxAvanzado.getEducational()[0]);
								 }
								 
								 AvEducationalVO[] educacionesAux=listaEdu.toArray(new AvEducationalVO[0]);
								catalogadorAvSession.getMDSesion().setEducational(educacionesAux);
							 }
							 else{//GUARDAR CUANDO HEMOS PULSADO BOTON MODIFICAR
								 ArrayList<AvEducationalVO> listaEdu= new ArrayList<AvEducationalVO>();
								 for(int i = 0; i<educaciones.length;i++)
								 {
									 if(iEdu==i)
									 {
										 if(auxAvanzado != null && auxAvanzado.getEducational()[0]!=null)
											 listaEdu.add(auxAvanzado.getEducational()[0]);
									 }else
										 listaEdu.add(educaciones[i]);
								 }

								 AvEducationalVO[] educacionesAux=listaEdu.toArray(new AvEducationalVO[0]);
								 catalogadorAvSession.getMDSesion().setEducational(educacionesAux);
							 }
						 }
				}
			}catch (Exception e){
				logger.error("Error en catalogadorWeb, categoría Detall Uso Educativo, metodo guardarUsoEducativo " + e);
//				throw new java.lang.Exception("detalle.uso.edu", e);
				saveErrorMessage(request, " Error en catalogadorWeb, categoría Detall Uso Educativo, metodo guardarUsoEducativo ");
			}

		}else{
			if (errorFaltaIdioma && errorFaltaTexto)
				throw new ValidatorException("{general.error.idioma_texto}");
			else if (!errorFaltaIdioma && errorFaltaTexto)
				throw new ValidatorException("{general.error.texto}");
			else if (errorFaltaIdioma && !errorFaltaTexto)
				throw new ValidatorException("{general.error.idioma}");
			else if (errorNoNumero)
				throw new ValidatorException("{educacion.error.NoNumero}");
			else if(errorSegundos)
				throw new ValidatorException("{educacion.error.Segundos}");
		}

 }
	
	private void cambioFormulario(HttpServletRequest request,int[] longitudTextosDesc,int[] longitudTextosRangoEdad,int longitudDescripciones,
			int longitudRangos, int longitudIdiomas,int longitudTipoRecurso,int longitudRoles,int longitudContextos,
			int longitudProcesos,int longitudTADesc,String source) throws Exception{
	    	
			IdiomaVO[] idiomas = new IdiomaVO[longitudIdiomas];
			DescripcionVO[] descripciones = new DescripcionVO[longitudDescripciones];
			DescripcionUsoEdu[][] descripcionesCat = new DescripcionUsoEdu[longitudDescripciones][]; //categorias descripciones
			RangoEdadVO[] rangosEdad=new RangoEdadVO[longitudRangos];
			SourceValueVO[] tiposRecurso = new SourceValueVO[longitudTipoRecurso];
			SourceValueVO[] roles = new SourceValueVO[longitudRoles];
			SourceValueVO[] contextos = new SourceValueVO[longitudContextos];
			SourceValueVO[] procesosCognitivos = new SourceValueVO[longitudProcesos];
			DescripcionVO[] tiempoApDescripcion = new DescripcionVO[1];

			UsoEducativoSession usoeducativoSession = this.getUsoEducativoSession(request);
			
	      	ArrayList[] textoDescripciones = new ArrayList[longitudDescripciones];
	      	ArrayList[] listaDescripcionesCat = new ArrayList[longitudDescripciones]; //categorias descripciones
	      	ArrayList[] idiomaDescripciones = new ArrayList[longitudDescripciones];
	      	ArrayList[] textoRangoEdad = new ArrayList[longitudRangos];
	      	ArrayList[] idiomaRangoEdad = new ArrayList[longitudRangos];
	      	String[] idiomaValor = new String[longitudIdiomas];
	      	String[] tipoRecursoValor = new String[longitudTipoRecurso];
	      	String[] rolesValor = new String[longitudRoles];
	      	String[] contextosValor = new String[longitudContextos];
	      	String[] procesosValor = new String[longitudProcesos];
	      	String[] textoTADesc = new String[longitudTADesc];
	      	String[] idiomaTADesc = new String[longitudTADesc];
	       for (Enumeration names = request.getParameterNames(); names.hasMoreElements();)
	       {
	    	   String name = String.valueOf(names.nextElement());
	    	   if (name.equals("Anyos")){
	    		   //anyos = request.getParameter(name);
	    		   usoeducativoSession.setAnyos(request.getParameter(name));
	    		   
	    	   }
	    	   if (name.equals("Meses"))
	    		   usoeducativoSession.setMeses(request.getParameter(name));//meses = request.getParameter(name);
	    	   if (name.equals("Dias"))
	    		   usoeducativoSession.setDias(request.getParameter(name));//dias = request.getParameter(name);
	    	   if (name.equals("Horas"))
	    		   usoeducativoSession.setHoras(request.getParameter(name));//horas = request.getParameter(name);
	    	   if (name.equals("Minutos"))
	    		   usoeducativoSession.setMinutos(request.getParameter(name));//minutos = request.getParameter(name);
	    	   if (name.equals("SegundosP1"))
	    		   usoeducativoSession.setSegundosP1(request.getParameter(name));//segundosP1 = request.getParameter(name);
	    	   if (name.equals("SegundosP2"))
	    		   usoeducativoSession.setSegundosP2(request.getParameter(name));//segundosP2 = request.getParameter(name);
	           if (name.startsWith("Idio")){//idiomas
	      		 int i = Integer.parseInt(name.substring(4, name.length()));
	      		 idiomaValor[i]=request.getParameter(name);
	           }
	           if (name.startsWith("PCog")){//proceso cognitvos
		      		 int i = Integer.parseInt(name.substring(4, name.length()));
		      		procesosValor[i]=request.getParameter(name);
	           }
	           if (name.startsWith("TRecur")){//tipo recurso
		      		 int i = Integer.parseInt(name.substring(6, name.length()));
		      		tipoRecursoValor[i]=request.getParameter(name);
	           }
	           if (name.startsWith("Contxt")){//contexto
		      		 int i = Integer.parseInt(name.substring(6, name.length()));
		      		contextosValor[i]=request.getParameter(name);
	           }
	           if (name.startsWith("Rol")){//rol
		      		 int i = Integer.parseInt(name.substring(3, name.length()));
		      		rolesValor[i]=request.getParameter(name);
	           }
	           if (name.startsWith("TADes")){
	        	   if(name.startsWith("TADesTex")){
	        		   int i = Integer.parseInt(name.substring(8, name.length()));
	        		   textoTADesc[i]=request.getParameter(name);
	        	   }	 
	        	   
	        	   if (name.startsWith("TADesIdio")){
	        		   int i = Integer.parseInt(name.substring(9, name.length()));
	        		   idiomaTADesc[i]=request.getParameter(name);
	        	   }
	           }
	           if (name.startsWith("Des")){//descripciones       	 
	          	 String[] namePartido = name.split("_");
	          	 int i = Integer.parseInt(namePartido[0].substring(3, namePartido[0].length()));
	          	 if (namePartido[1].startsWith("Tex")){
	          		 int j = Integer.parseInt(namePartido[1].substring(3, namePartido[1].length()));
	          		 ArrayList<String> lDesc = textoDescripciones[i];
	          		 if(lDesc == null){
	          			 lDesc= new ArrayList<String>();
	          			 for (int k=0;k<longitudTextosDesc[i];k++)
	          				 lDesc.add("");
	          		 }
	          		 
	          		 lDesc.set(j, request.getParameter(name));
	          		 textoDescripciones[i]=lDesc;
	          	 }
	          	 else if(namePartido[1].startsWith("Idio")){//Idio
	          		 int j = Integer.parseInt(namePartido[1].substring(4, namePartido[1].length()));
	          		 ArrayList<String> lDesc = idiomaDescripciones[i];
	          		 if(lDesc == null){
	          			 lDesc= new ArrayList<String>();
	          			 for (int k=0;k<longitudTextosDesc[i];k++)
	          				 lDesc.add("");
	          		 }
	          		 
	          		 lDesc.set(j, request.getParameter(name));
	          		 idiomaDescripciones[i]=lDesc;
	          	 }
	          	 else if(namePartido[1].startsWith("Sel")){ // seleccion de tipo de descripcion [0,1,2,3]
	          		 int j = Integer.parseInt(namePartido[1].substring("Sel".length(), namePartido[1].length()));
	          		 ArrayList<DescripcionUsoEdu> lDesc = listaDescripcionesCat[i];
	          		 if(lDesc == null){
	          			 lDesc= new ArrayList<DescripcionUsoEdu>();
	          			 for (int k=0;k<longitudTextosDesc[i];k++)
	          				 lDesc.add(DescripcionUsoEdu.getInstance());
	          		 }
	          		 
	          		 DescripcionUsoEdu aux=lDesc.get(j);
	          		 aux.setSeleccionado(Integer.parseInt(request.getParameter(name)));
	          		 lDesc.set(j, aux);
	          		 listaDescripcionesCat[i]=lDesc;
	          		 
	          	 }
	          	 else if(namePartido[1].startsWith("CPre")){ // Conocimiento previo
	          		 int j = Integer.parseInt(namePartido[1].substring("CPre".length(), namePartido[1].length()));
	          		 ArrayList<DescripcionUsoEdu> lDesc = listaDescripcionesCat[i];
	          		 if(lDesc == null){
	          			 lDesc= new ArrayList<DescripcionUsoEdu>();
	          			 for (int k=0;k<longitudTextosDesc[i];k++)
	          				 lDesc.add(DescripcionUsoEdu.getInstance());
	          		 }
	          		 
	          		 DescripcionUsoEdu aux=lDesc.get(j);
	          		 aux.setConocimientoPrevio(request.getParameter(name));
	          		 lDesc.set(j, aux);
	          		 listaDescripcionesCat[i]=lDesc;
	          		 
	          	 }
	          	 else if(namePartido[1].startsWith("ODid")){ // Objetivos didácticos
	          		 int j = Integer.parseInt(namePartido[1].substring("ODid".length(), namePartido[1].length()));
	          		 ArrayList<DescripcionUsoEdu> lDesc = listaDescripcionesCat[i];
	          		 if(lDesc == null){
	          			 lDesc= new ArrayList<DescripcionUsoEdu>();
	          			 for (int k=0;k<longitudTextosDesc[i];k++)
	          				 lDesc.add(DescripcionUsoEdu.getInstance());
	          		 }
	          		 
	          		 DescripcionUsoEdu aux=lDesc.get(j);
	          		 aux.setObjetivosDidacticos(request.getParameter(name));
	          		 lDesc.set(j, aux);
	          		 listaDescripcionesCat[i]=lDesc;
	          		 
	          	 }
	          	 else if(namePartido[1].startsWith("TCon")){ // Tipo de Conocimiento
	          		 int j = Integer.parseInt(namePartido[1].substring("TCon".length(), namePartido[1].length()));
	          		 ArrayList<DescripcionUsoEdu> lDesc = listaDescripcionesCat[i];
	          		 if(lDesc == null){
	          			 lDesc= new ArrayList<DescripcionUsoEdu>();
	          			 for (int k=0;k<longitudTextosDesc[i];k++)
	          				 lDesc.add(DescripcionUsoEdu.getInstance());
	          		 }
	          		 
	          		 DescripcionUsoEdu aux=lDesc.get(j);
	          		 aux.setTipoConocimiento(Arrays.asList(request.getParameterValues(name)) );
	          		 lDesc.set(j, aux);
	          		 listaDescripcionesCat[i]=lDesc;
	          		 
	          	 }
	           }
	           else if (name.startsWith("Edad")){//rangos edad
	          	 String[] namePartido = name.split("_");
	          	 int i = Integer.parseInt(namePartido[0].substring(4, namePartido[0].length()));
	          	 if (namePartido[1].startsWith("Tex")){
	          		 int j = Integer.parseInt(namePartido[1].substring(3, namePartido[1].length()));
	          		 ArrayList<String> lRango= textoRangoEdad[i];
	          		 if(lRango == null){
	          			lRango= new ArrayList<String>();
	          			 
	          			 for (int k=0;k<longitudTextosRangoEdad[i];k++)
	          				lRango.add("");
	          		 }
	          		 
	          		lRango.set(j, request.getParameter(name));
	          		textoRangoEdad[i]=lRango;
	          	 }
	          	 else{//Idio
	          		 int j = Integer.parseInt(namePartido[1].substring(4, namePartido[1].length()));
	          		 ArrayList<String> lRango = idiomaRangoEdad[i];
	          		 if(lRango == null){
	          			lRango= new ArrayList<String>();
	          			 for (int k=0;k<longitudTextosRangoEdad[i];k++)
	          				lRango.add("");
	          		 }
	          		 
	          		lRango.set(j, request.getParameter(name));
	          		idiomaRangoEdad[i]=lRango;
	          	 }
	           }             
	       }
	            
	       //idioma
	       for (int i = 0; i<idiomaValor.length;i++){
	      	 IdiomaVO idioVO = new IdiomaVO();
	      	 idioVO.setTexto(idiomaValor[i]);
	      	 idiomas[i] = idioVO;
	       }
	       //act session
	       usoeducativoSession.setIdiomas(Arrays.asList(idiomas));
	       
	       //procesos cognitivos
	       for (int i = 0; i<procesosValor.length;i++){
	      	 SourceValueVO procesoVO = new SourceValueVO();
	      	 procesoVO.setValor(procesosValor[i]);
	      	 procesoVO.setSource(source);
	      	 procesosCognitivos[i] = procesoVO;
	       }
	       //act session
	       usoeducativoSession.setProcesosCognitivos(Arrays.asList(procesosCognitivos));
	       
	       //contextos
	       for (int i = 0; i<contextosValor.length;i++){
	      	 SourceValueVO contextoVO = new SourceValueVO();
	      	 contextoVO.setValor(contextosValor[i]);
	      	 contextoVO.setSource(source);
	      	 contextos[i] = contextoVO;
	       }
	       //act session
	       usoeducativoSession.setContextos(Arrays.asList(contextos));
	       
	       //roles
	       for (int i = 0; i<rolesValor.length;i++){
	      	 SourceValueVO rolVO = new SourceValueVO();
	      	 rolVO.setValor(rolesValor[i]);
	      	 rolVO.setSource(source);
	      	 roles[i] = rolVO;
	       }
	       //act session 
	       usoeducativoSession.setRoles(Arrays.asList(roles));
	       
	       //tipo recurso
	       for (int i = 0; i<tipoRecursoValor.length;i++){
	      	 SourceValueVO tipoRecrusoVO = new SourceValueVO();
	      	 tipoRecrusoVO.setValor(tipoRecursoValor[i]);
	      	 tipoRecrusoVO.setSource(source);
	      	 tiposRecurso[i] = tipoRecrusoVO;
	       }
	       //act session
	       usoeducativoSession.setTiposRecurso(Arrays.asList(tiposRecurso));
	       
	       //descripcion tiempo de aprendizaje
	       DescripcionVO taDescVO = new DescripcionVO();
	       LangStringVO[] aLangTADesc = new LangStringVO[textoTADesc.length];
	       for (int i = 0; i<textoTADesc.length;i++){
	    	   LangStringVO langTADesc= new LangStringVO();
	    	   String textoTADescI=textoTADesc[i]!=null?textoTADesc[i]:"";
	    	   langTADesc.setTexto(textoTADescI.trim());
	    	   langTADesc.setIdioma(idiomaTADesc[i]);
	    	   aLangTADesc[i] = langTADesc;
	       }
	       taDescVO.setTextos(aLangTADesc);
	       tiempoApDescripcion[0]=taDescVO;
	       //act session
	       usoeducativoSession.setTiempoApDescripcion(Arrays.asList(tiempoApDescripcion));
	       //descripciones categorias
	       for (int i = 0; i < listaDescripcionesCat.length; i++) {
	    	   descripcionesCat[i]= (DescripcionUsoEdu[]) listaDescripcionesCat[i].toArray(new DescripcionUsoEdu[0]);
	       }
	       //act session
	       usoeducativoSession.setDescripcionesCat(Arrays.asList(descripcionesCat));
	       
	       //descripciones
	       I18n i18n = I18n.getInstance();

	       for(int i=0;i<textoDescripciones.length;i++){
	      	 DescripcionVO descVO = new DescripcionVO();
	      	DescripcionUsoEdu[] aDescripcionesUsoEdu=null;
	  	  	 if (textoDescripciones[i]!=null) {
	         LangStringVO[] aLangDesc = new LangStringVO[textoDescripciones[i].size()];
	         aDescripcionesUsoEdu = new DescripcionUsoEdu[textoDescripciones[i].size()]; // caracteristica descripcion
	         
	         for (int j = 0; j<textoDescripciones[i].size();j++){
	        	 LangStringVO langDesc= new LangStringVO();
	        	 String textoDescIJ="";
	        	 String idiomaDescIJ=(idiomaDescripciones[i].get(j)!=null?idiomaDescripciones[i].get(j):"").toString();

  		    	 String idioma=listaIdiomas.get( idiomaDescIJ);
  		    	 if(idioma==null)
  		    		 idioma= this.getCatalogadorAvSession(request).getIdioma();
  		    	 
  		    	 if(descripcionesCat[i][j]!=null && !descripcionesCat[i][j].getVacio() && idioma!=null)
  		    	 {
  					 String[] captions = i18n.getResource("catalogadorAvanzado.descripcionesUsoEdu.titulos", "application-resources" , new Locale(idioma)).split(",");
  		    		 DescripcionUsoEdu descEUAux = descripcionesCat[i][j]; 
  		    		 StringBuffer sb = new StringBuffer();
  		    		if(!descEUAux.getVacio() && descEUAux.getSeleccionado()==1)
  		    		{
  		    			// completar la descripcion con conocimiento previo...
  		    			sb.append(captions[0].toUpperCase()).append(descEUAux.getConocimientoPrevio());
  		    			descEUAux.setObjetivosDidacticos("");
  		    			descEUAux.setTipoConocimiento(new ArrayList<String>());
  		    			aDescripcionesUsoEdu[j]=descEUAux; 
  		    		}
  		    		else if(!descEUAux.getVacio() && descEUAux.getSeleccionado()==2) 
  		    		{
  		    			// 	completar la descripcion con objetivos didacticos...
  		    			sb.append(captions[1].toUpperCase()).append(descEUAux.getObjetivosDidacticos());
  		    			descEUAux.setConocimientoPrevio("");
  		    			descEUAux.setTipoConocimiento(new ArrayList<String>());
  		    			aDescripcionesUsoEdu[j]=descEUAux; 
  		    		}else if(!descEUAux.getVacio() && descEUAux.getSeleccionado()==3)
  		    		{
  		    			// completar con las traducciones de los tipos de conocimientos
  		    			
  		    			String[] lista=(String[]) descEUAux.getTipoConocimiento().toArray(new String[0]);
  		    			TerminoVO[] terminos= this.getSrvVocabulariosControladosService().crearTraduccionAIdioma(lista, idioma);
  		    			if(terminos!=null && terminos.length>0)
  		    			{
	  		    			sb.append(captions[2].toUpperCase()).append(" ");
	  		    			for (int k = 0; k < terminos.length; k++) {
								sb.append(terminos[k].getNomTermino());
								if(terminos.length - k > 1)
								{
									sb.append(", ");
								}
							}
  		    			}
  		    			descEUAux.setObjetivosDidacticos("");
  		    			descEUAux.setConocimientoPrevio("");
  		    			aDescripcionesUsoEdu[j]=descEUAux; 

  		    		}else{
  		    			listaDescripcionesCat[i].set(j,DescripcionUsoEdu.getInstance());
  		    			sb.append((textoDescripciones[i].get(j)!=null?textoDescripciones[i].get(j):"").toString());
  		    		}
  		    		 textoDescIJ= sb.toString();
  		    	 }else
  		    	 {
  		    		 if(descripcionesCat[i][j]!=null && descripcionesCat[i][j].getVacio() && descripcionesCat[i][j].getSeleccionado()>0 )
  		    			 textoDescIJ="";
  		    		 else
  		    			 textoDescIJ=(textoDescripciones[i].get(j)!=null?textoDescripciones[i].get(j):"").toString();

  		    	 }

  		    		 
	        	 langDesc.setTexto(textoDescIJ.trim());
	        	 langDesc.setIdioma(idiomaDescIJ.trim());
	        	 aLangDesc[j] = langDesc;
	        	 
	        	 aDescripcionesUsoEdu[j]=(DescripcionUsoEdu) listaDescripcionesCat[i].get(j);
	         }
	         descVO.setTextos(aLangDesc);
	         
	  	  	 }
	  	  	 else{
	  	  		 LangStringVO[] aLangString=new LangStringVO[1];
	  	  		 LangStringVO langString = new LangStringVO();
	  	  		 langString.setIdioma("");
	  	  		 langString.setTexto("");
	  	  		 aLangString[0]= langString;
	  	  		 descVO.setTextos(aLangString);
	  	  		 
	  	  		aDescripcionesUsoEdu= new DescripcionUsoEdu[1];
	  	  		aDescripcionesUsoEdu[0]= DescripcionUsoEdu.getInstance();
	  	  	 }
	         descripciones[i]=descVO;    
	         descripcionesCat[i]= aDescripcionesUsoEdu;
	       }
	       //act session
	       usoeducativoSession.setDescripciones(Arrays.asList(descripciones));
	       usoeducativoSession.setDescripcionesCat(Arrays.asList(descripcionesCat));
	       

	       //rangos de edad
	       for(int i=0;i<textoRangoEdad.length;i++){
	      	 RangoEdadVO rangoEdadVO = new RangoEdadVO();
	      	if (textoRangoEdad[i]!=null) {
	         LangStringVO[] aLangRango = new LangStringVO[textoRangoEdad[i].size()];
	         for (int j = 0; j<textoRangoEdad[i].size();j++){
	        	 LangStringVO langRango= new LangStringVO();
	        	 String textoRangoEdadIJ=(textoRangoEdad[i].get(j)!=null?textoRangoEdad[i].get(j):"").toString();
	        	 String idiomaRangoEdadIJ=(idiomaRangoEdad[i].get(j)!=null?idiomaRangoEdad[i].get(j):"").toString();
	        	 langRango.setTexto(textoRangoEdadIJ.trim());
	        	 langRango.setIdioma(idiomaRangoEdadIJ.trim());
	        	 aLangRango[j] = langRango;
	         }
	         rangoEdadVO.setTextos(aLangRango);
	      	}
	      	else{
	      		LangStringVO[] aLangString=new LangStringVO[1];
	      		LangStringVO langString = new LangStringVO();
	      		langString.setIdioma("");
	      		langString.setTexto("");
	      		aLangString[0]= langString;
	      		rangoEdadVO.setTextos(aLangString);
	      	}
	         rangosEdad[i]=rangoEdadVO;     
	       }
	       //act session
	       usoeducativoSession.setRangosEdad(Arrays.asList(rangosEdad)); 
	    }
		
	private void dameTerminoId(UsoEducativoSession usoeducativoSession) throws Exception{
		    
	    //descripciones
//		ArrayList idsDesc = new ArrayList();
	    DescripcionVO[] descripciones = (DescripcionVO[])usoeducativoSession.getDescripciones()
	    			.toArray(new DescripcionVO[usoeducativoSession.getDescripciones().size()]);
	    for ( int i=0;i<descripciones.length;i++ ){
	    	LangStringVO[] langDescripcion = descripciones[i].getTextos();
	    	for ( int j=0;j<langDescripcion.length;j++){
	    		String idioma = listaIdiomas.get(langDescripcion[j].getIdioma());
	    		if (idioma!=null)
	    		{
	    			langDescripcion[j].setIdioma(idioma);
	    		}
//		    	idsDesc.add(langDescripcion[j].getIdioma());
//		    	
//		        String[] arrayIdsDesc = (String[])idsDesc.toArray(new String[idsDesc.size()]);
//		        TerminoYPadreVO[] terminosTraducDesc = this.getSrvVocabulariosControladosService().crearTraduccionAIngles(arrayIdsDesc);
//		        
//		        int jDesc= 0;
//		        for (int cont=0;cont<terminosTraducDesc.length;cont++){//Cambiado el identificador del termino al nombre del termino en ingles
//		        	
//		    		boolean enc=false; 		
//		    		while(!enc && jDesc<langDescripcion.length){
//		    			if (langDescripcion[jDesc].getIdioma().equals("")){
//		    				jDesc++; 
//		    			}
//		    			else{
//		    				enc = true;
//		    				langDescripcion[jDesc].setIdioma(terminosTraducDesc[cont].getNomTermino());
//		    				jDesc++;
//		    			}
//		    		}	
//		        }
	        }
	        descripciones[i].setTextos(langDescripcion);
	    }
	    //actualizamos session
	    usoeducativoSession.setDescripciones(Arrays.asList(descripciones));
	    
	    //rangos edad
//	    ArrayList idsRangos = new ArrayList();
	    RangoEdadVO[] rangosEdad = (RangoEdadVO[]) usoeducativoSession.getRangosEdad()
			.toArray(new RangoEdadVO[usoeducativoSession.getRangosEdad().size()]);
	    for ( int i=0;i<rangosEdad.length;i++ ){
	    	LangStringVO[] langRangos = rangosEdad[i].getTextos();
	    	for ( int j=0;j<langRangos.length;j++){
	    		String idioma = listaIdiomas.get(langRangos[j].getIdioma());
	    		if (idioma!=null)
	    		{
	    			langRangos[j].setIdioma(idioma);
	    		}
//	    		idsRangos.add(langRangos[j].getIdioma());
//	    	
//	    		String[] arrayIdsAmb = (String[])idsRangos.toArray(new String[idsRangos.size()]);
//	    		TerminoYPadreVO[] terminosTraducAmb = this.getSrvVocabulariosControladosService().crearTraduccionAIngles(arrayIdsAmb);
//	        
//	    		int jRang= 0;
//	    		for (int cont=0;cont<terminosTraducAmb.length;cont++){//Cambiado el identificador del termino al nombre del termino en ingles
//	        	
//	    			boolean enc=false; 		
//	    			while(!enc && jRang<langRangos.length){
//	    				if (langRangos[jRang].getIdioma().equals("")){
//	    					jRang++; 
//	    				}
//	    				else{
//	    					enc = true;
//	    					langRangos[jRang].setIdioma(terminosTraducAmb[cont].getNomTermino());
//	    					jRang++;
//	    				}
//	    			}	
//	    		}
	    	}
	    	rangosEdad[i].setTextos(langRangos);
	    }
	    //act session
	    usoeducativoSession.setRangosEdad(Arrays.asList(rangosEdad));
	  
	    //descripcion tiempo aprendizaje
		ArrayList<String> idsTADesc = new ArrayList<String>();
		DescripcionVO[] tiempoApDescripcion = (DescripcionVO[]) usoeducativoSession.getTiempoApDescripcion()
			.toArray(new DescripcionVO[usoeducativoSession.getTiempoApDescripcion().size()]);
	    LangStringVO[] langTADesc =tiempoApDescripcion[0].getTextos();
	    for ( int i=0;i<langTADesc.length;i++){
	    	idsTADesc.add(langTADesc[i].getIdioma());
	    }
	    
	    String[] arrayIdsTADesc = idsTADesc.toArray(new String[idsTADesc.size()]);
	    TerminoYPadreVO[] terminosTraducTADesc = this.getSrvVocabulariosControladosService().crearTraduccionAIngles(arrayIdsTADesc);
	    int jTADesc= 0;
	    for (int cont=0;cont<terminosTraducTADesc.length;cont++){//Cambiado el identificador del termino al nombre del termino en ingles
	    	
			boolean enc=false; 		
			while(!enc && jTADesc<langTADesc.length){
				if (langTADesc[jTADesc].getIdioma().equals("")){
					jTADesc++; 
				}
				else{
					enc = true;
					langTADesc[jTADesc].setIdioma(terminosTraducTADesc[cont].getNomTermino());
					jTADesc++;
				}
			}	
	    }
	    //act session
	    tiempoApDescripcion[0].setTextos(langTADesc);
	    usoeducativoSession.setTiempoApDescripcion(Arrays.asList(tiempoApDescripcion));
	    
	    //roles
	    ArrayList<String> idsRoles = new ArrayList<String>();
	    SourceValueVO[] roles = (SourceValueVO[]) usoeducativoSession.getRoles()
		  .toArray(new SourceValueVO[usoeducativoSession.getRoles().size()]);
		for ( int i=0;i<roles.length;i++){
			idsRoles.add(roles[i].getValor());
		}

	    String[] arrayIdsRoles = idsRoles.toArray(new String[idsRoles.size()]);
	    TerminoYPadreVO[] terminosTraducRoles = this.getSrvVocabulariosControladosService().crearTraduccionAIngles(arrayIdsRoles);
	    int jRoles= 0;
	    for (int cont=0;cont<terminosTraducRoles.length;cont++){//Cambiado el identificador del termino al nombre del termino en ingles
	    	
			boolean enc=false; 		
			while(!enc && jRoles<roles.length){
				if (roles[jRoles].getValor().equals("")){
					jRoles++; 
				}
				else{
					enc = true;
					roles[jRoles].setValor(terminosTraducRoles[cont].getNomTermino());
					jRoles++;
				}
			}	
	    }
	    usoeducativoSession.setRoles(Arrays.asList(roles));
	    
	    //proceso cognitivo
	    ArrayList<String> idsProceso = new ArrayList<String>();
	    SourceValueVO[] procesosCognitivos = (SourceValueVO[]) usoeducativoSession.getProcesosCognitivos()
		  	.toArray(new SourceValueVO[usoeducativoSession.getProcesosCognitivos().size()]); 
		for ( int i=0;i<procesosCognitivos.length;i++){
			idsProceso.add(procesosCognitivos[i].getValor());
		}

	    String[] arrayIdsProceso = idsProceso.toArray(new String[idsProceso.size()]);
	    TerminoYPadreVO[] terminosTraducProceso = this.getSrvVocabulariosControladosService().crearTraduccionAIngles(arrayIdsProceso);
	    int jProceso= 0;
	    for (int cont=0;cont<terminosTraducProceso.length;cont++){//Cambiado el identificador del termino al nombre del termino en ingles
	    	
			boolean enc=false; 		
			while(!enc && jProceso<procesosCognitivos.length){
				if (procesosCognitivos[jProceso].getValor().equals("")){
					jProceso++; 
				}
				else{
					enc = true;
					procesosCognitivos[jProceso].setValor(terminosTraducProceso[cont].getNomTermino());
					jProceso++;
				}
			}	
	    }
	    //act session
	    usoeducativoSession.setProcesosCognitivos(Arrays.asList(procesosCognitivos));
	    //contextos
	    ArrayList<String> idsContexto = new ArrayList<String>();
	    SourceValueVO[] contextos = (SourceValueVO[]) usoeducativoSession.getContextos()
		  	.toArray(new SourceValueVO[usoeducativoSession.getContextos().size()]);
		for ( int i=0;i<contextos.length;i++){
			idsContexto.add(contextos[i].getValor());
		}

	    String[] arrayIdsContexto = idsContexto.toArray(new String[idsContexto.size()]);
	    TerminoYPadreVO[] terminosTraducContexto = this.getSrvVocabulariosControladosService().crearTraduccionAIngles(arrayIdsContexto);
	    int jContexto= 0;
	    for (int cont=0;cont<terminosTraducContexto.length;cont++){//Cambiado el identificador del termino al nombre del termino en ingles
	    	
			boolean enc=false; 		
			while(!enc && jContexto<contextos.length){
				if (contextos[jContexto].getValor().equals("")){
					jContexto++; 
				}
				else{
					enc = true;
					contextos[jContexto].setValor(terminosTraducContexto[cont].getNomTermino());
					jContexto++;
				}
			}	
	    }
	    //act session
	    usoeducativoSession.setContextos(Arrays.asList(contextos));
	    
	    //tipo recurso
	    ArrayList<String> idsTRecurso = new ArrayList<String>();
	    SourceValueVO[] tiposRecurso = (SourceValueVO[]) usoeducativoSession.getTiposRecurso()
			.toArray(new SourceValueVO[usoeducativoSession.getTiposRecurso().size()]);
		for ( int i=0;i<tiposRecurso.length;i++){
			idsTRecurso.add(tiposRecurso[i].getValor());
		}

	    String[] arrayIdsTRecurso = idsTRecurso.toArray(new String[idsTRecurso.size()]);
	    TerminoYPadreVO[] terminosTraducTRecurso = this.getSrvVocabulariosControladosService().crearTraduccionAIngles(arrayIdsTRecurso);
	    int jTRecur= 0;
	    for (int cont=0;cont<terminosTraducTRecurso.length;cont++){//Cambiado el identificador del termino al nombre del termino en ingles
	    	
			boolean enc=false; 		
			while(!enc && jTRecur<tiposRecurso.length){
				if (tiposRecurso[jTRecur].getValor().equals("")){
					jTRecur++; 
				}
				else{
					enc = true;
					tiposRecurso[jTRecur].setValor(terminosTraducTRecurso[cont].getNomTermino());
					jTRecur++;
				}
			}	
	    }
	    //act session
	    usoeducativoSession.setTiposRecurso(Arrays.asList(tiposRecurso));
	    //idiomas
	    ArrayList<String> idsIdio = new ArrayList<String>();
	    IdiomaVO[] idiomas = (IdiomaVO[])usoeducativoSession.getIdiomas()
			.toArray(new IdiomaVO[usoeducativoSession.getIdiomas().size()]);
		for ( int i=0;i<idiomas.length;i++){
			idsIdio.add(idiomas[i].getTexto());
		}

	    String[] arrayIdsIdio = idsIdio.toArray(new String[idsIdio.size()]);
	    TerminoYPadreVO[] terminosTraducIdio = this.getSrvVocabulariosControladosService().crearTraduccionAIngles(arrayIdsIdio);
	    int jIdio= 0;
	    for (int cont=0;cont<terminosTraducIdio.length;cont++){//Cambiado el identificador del termino al nombre del termino en ingles
	    	
			boolean enc=false; 		
			while(!enc && jIdio<idiomas.length){
				if (idiomas[jIdio].getTexto().equals("")){
					jIdio++; 
				}
				else{
					enc = true;
					idiomas[jIdio].setTexto(terminosTraducIdio[cont].getNomTermino());
					jIdio++;
				}
			}	
	    }
	    //act session
	    usoeducativoSession.setIdiomas(Arrays.asList(idiomas));
	    
	    ArrayList<String> ids = new ArrayList<String>();
	    ids.add(usoeducativoSession.getTipoInteractividad());
	    ids.add(usoeducativoSession.getNivelInteractividad());
	    ids.add(usoeducativoSession.getDensidadSemantica());
	    ids.add(usoeducativoSession.getDificultad());
	    

	    String[] arrayIds = ids.toArray(new String[ids.size()]);
	    TerminoYPadreVO[] terminosTraduc = this.getSrvVocabulariosControladosService().crearTraduccionAIngles(arrayIds);
	    for (int i = 0;i<terminosTraduc.length;i++){
		    if(terminosTraduc[i].getIdVocabulario().equals("5.1")){
			    if (!usoeducativoSession.getTipoInteractividad().equals("")){
			    	//tipoInteractividad=terminosTraduc[i].getNomTermino();
			    	usoeducativoSession.setTipoInteractividad(terminosTraduc[i].getNomTermino());
			    }
		    }
		    else if(terminosTraduc[i].getIdVocabulario().equals("5.3")){
			    if (!usoeducativoSession.getNivelInteractividad().equals("")){
			    	//nivelInteractividad=terminosTraduc[i].getNomTermino();
			    	usoeducativoSession.setNivelInteractividad(terminosTraduc[i].getNomTermino());
			    }
		    }
		    else if(terminosTraduc[i].getIdVocabulario().equals("5.4")){
			    if (!usoeducativoSession.getDensidadSemantica().equals("")){
			    	//densidadSemantica=terminosTraduc[i].getNomTermino();
			    	usoeducativoSession.setDensidadSemantica(terminosTraduc[i].getNomTermino());
			    }
		    }
		    else if(terminosTraduc[i].getIdVocabulario().equals("5.8")){
			    if (!usoeducativoSession.getDificultad().equals("")){
			    	//dificultad=terminosTraduc[i].getNomTermino();
			    	usoeducativoSession.setDificultad(terminosTraduc[i].getNomTermino());
			    }
		    }	    	
	    }

	}


	private void descifrarDuration(UsoEducativoSession usoeducativoSession){
		//expresion regular duracion = P[yY][mM][dD][T[hH][nM][s[.s]S]]
		
		 Pattern mask=Pattern.compile("^P([0-9]+Y){0,1}([0-9]+M){0,1}([0-9]+D){0,1}(T([0-9]+H){0,1}([0-9]+M){0,1}([0-9]+(.[0-9]+){0,1}S){0,1}){0,1}$"); //ejm ee-zz
		 Matcher matcher = mask.matcher(usoeducativoSession.getDuracion());
		 boolean correcto = matcher.matches();
		
		if(usoeducativoSession.getDuracion() != null && !usoeducativoSession.getDuracion().equals("") && correcto && !usoeducativoSession.getDuracion().equals("P") && !usoeducativoSession.getDuracion().equals("PT")){
			//duracionAux = [yY][mM][dD][T[hH][nM][s[.s]S]]
			String duracionAux= usoeducativoSession.getDuracion().substring(1, usoeducativoSession.getDuracion().length());
			int posiT = duracionAux.indexOf("T");
			//duracionParteT = [hH][nM][s[.s]S]
			if (posiT < 0)
				posiT = duracionAux.length();
			else 
			{
				//duracionParteT = [hH][nM][s[.s]S]
				String duracionParteT=duracionAux.substring(posiT+1,duracionAux.length());
				int posiH=duracionParteT.indexOf("H");
				if (posiH > -1) {
					//horas = duracionParteT.substring(0, posiH);
					usoeducativoSession.setHoras(duracionParteT.substring(0, posiH));
				}
				//duracionParteT = [nM][s[.s]S]
				duracionParteT = duracionParteT.substring(posiH+1, duracionParteT.length());
				int posiMi=duracionParteT.indexOf("M");
				if(posiMi > -1){
					//minutos= duracionParteT.substring(0,posiMi);
					usoeducativoSession.setMinutos(duracionParteT.substring(0,posiMi));
				}
				//duracionParteT = [s[.s]S]
				duracionParteT = duracionParteT.substring(posiMi+1, duracionParteT.length());
				int posiPunto = duracionParteT.indexOf(".");
				int posiS = duracionParteT.indexOf("S");
				if(posiS>-1){
					if (posiPunto < 0 && !duracionParteT.equals("")){
						//segundosP1 = duracionParteT.substring(0, duracionParteT.length()-1);
						usoeducativoSession.setSegundosP1(duracionParteT.substring(0, duracionParteT.length()-1));
					}
					else{
						//segundosP1 = duracionParteT.substring(0, posiPunto);
						usoeducativoSession.setSegundosP1(duracionParteT.substring(0, posiPunto));
						//segundosP2 = duracionParteT.substring(posiPunto+1, duracionParteT.length()-1);
						usoeducativoSession.setSegundosP2(duracionParteT.substring(posiPunto+1, duracionParteT.length()-1));
					}
				}
				
			}
			//duracionResto = [yY][mM][dD]
			String duracionResto=duracionAux.substring(0,posiT);
			int posiY=duracionResto.indexOf("Y");
			if (posiY > -1)
				//anyos=duracionResto.substring(0,posiY);
				usoeducativoSession.setAnyos(duracionResto.substring(0,posiY));
			//duracionResto = [mM][dD]
			duracionResto= duracionResto.substring(posiY+1,duracionResto.length());
			int posiM= duracionResto.indexOf("M");
			if (posiM > -1){
				//meses=duracionResto.substring(0,posiM);
				usoeducativoSession.setMeses(duracionResto.substring(0,posiM));
			}
			//duracionResto = [dD]
			duracionResto = duracionResto.substring(posiM+1,duracionResto.length());
			int posiD= duracionResto.indexOf("D");
			if (posiD > -1){
				//dias=duracionResto.substring(0,duracionResto.length()-1);
				usoeducativoSession.setDias(duracionResto.substring(0,duracionResto.length()-1));
			}
		}
		else {
			//anyos="";meses="";dias="";horas="";minutos="";segundosP1="";segundosP2="";
			usoeducativoSession.setAnyos("");
			usoeducativoSession.setMeses("");
			usoeducativoSession.setDias("");
			usoeducativoSession.setHoras("");
			usoeducativoSession.setMinutos("");
			usoeducativoSession.setSegundosP1("");
			usoeducativoSession.setSegundosP2("");
		}
	}



	public void cancelar(
			ActionMapping mapping, 
			CancelarForm form, 
			HttpServletRequest request, 
			HttpServletResponse response)
	throws Exception 
	{
		
	}



	public void cargarUsoEducativoValidar(
			ActionMapping mapping, 
			CargarUsoEducativoValidarForm form, 
			HttpServletRequest request, 
			HttpServletResponse response) 
	throws Exception 
	{
		try{
			cargarCombos(form, request);
			UsoEducativoSession usoeducativoSession = this.getUsoEducativoSession(request);
			
			form.setTipoInteractividad(usoeducativoSession.getTipoInteractividad());
			form.setNivelInteractividad(usoeducativoSession.getNivelInteractividad());
			form.setDensidadSemantica(usoeducativoSession.getDensidadSemantica());
			form.setDificultad(usoeducativoSession.getDificultad());
			form.setTipoRecurso(usoeducativoSession.getTiposRecurso());
    		form.setRolUsuarioAsArray(usoeducativoSession.getRoles().toArray());
    		form.setContextoAsArray(usoeducativoSession.getContextos().toArray());
    		form.setProcesoCognitivoAsArray(usoeducativoSession.getProcesosCognitivos().toArray());
    		form.setEdadTipicaAsArray(usoeducativoSession.getRangosEdad().toArray());
    		form.setIdiomasAsArray(usoeducativoSession.getIdiomas().toArray());
    		form.setDescripcionesAsArray(usoeducativoSession.getDescripciones().toArray());
    		form.setDescripcionesCatAsArray(usoeducativoSession.getDescripcionesCat().toArray());
    		form.setTiempoApDescripcionAsArray(usoeducativoSession.getTiempoApDescripcion().toArray());
    		
 		  	form.setAnyo(usoeducativoSession.getAnyos());
		  	form.setMes(usoeducativoSession.getMeses());
		  	form.setDia(usoeducativoSession.getDias());
		  	form.setHora(usoeducativoSession.getHoras());
		  	form.setMinutos(usoeducativoSession.getMinutos());
		  	form.setSegundosP1(usoeducativoSession.getSegundosP1());
		  	form.setSegundosP2(usoeducativoSession.getSegundosP2());
		  	
		  	 //cargar ayuda contextual    
			 cargarAyuda(usoeducativoSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
			
		} catch (Exception e){
			logger.error("Error en catalogadorWeb, categoría Detall Uso Educativo, metodo cargarUsoEducativoValidar " + e);
//			throw new java.lang.Exception("detalle.uso.edu", e);
			saveErrorMessage(request, " Error en catalogadorWeb, categoría Detall Uso Educativo, metodo cargarUsoEducativoValidar ");
		
		}	
		
	}
	
	
	private void cargarCombos(
			CargarUsoEducativoValidarForm form, 
			HttpServletRequest request)
	throws Exception
	{
		InputStream is = null;
    	Properties prop = new Properties();
		boolean bandera=false;
		
        try{
		
        	int longVocabulario = 0;
        	is = this.getClass().getResourceAsStream("/catalogadorAvanzado.properties");
			prop.load(is);
			String idiomaLdap=((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
			
			
			//Carga del formulario (ahora desde el objeto de session)(Los combos y lo que debe pintarse)
			String[] l_id = { prop.getProperty("idiomaDestinatario"),prop.getProperty("tipoRecursoAprendizaje"),prop.getProperty("rolEdu"),
							prop.getProperty("contexto"),prop.getProperty("procesoCognitivo"),prop.getProperty("tipoInteractividad"),
							prop.getProperty("nivelInteractividad"),prop.getProperty("densidadSemantica"),prop.getProperty("dificultad"),
							prop.getProperty("usoedu.tipoConocimiento")};
			VocabularioVO[] vocabulario = this.getSrvVocabulariosControladosService().obtenerCombos(l_id,idiomaLdap);
			//guardo una lista de los idiomas con sus correspondientes codigos
			listaIdiomas = new HashMap<String, String>();
			for (int i = 0; i < vocabulario[0].getTerminos().length; i++) {
				listaIdiomas.put(vocabulario[0].getTerminos()[i].getIdTermino(), vocabulario[0].getTerminos()[i].getNomTermino());
			}
			//vamos a ordenar vocabulario
			UtilidadesOrdenarCombos vocabulariosOrdDest2 = new UtilidadesOrdenarCombos();
			VocabularioVO[] vocabularioOrdenado = vocabulariosOrdDest2.ordenarVocabulariosVO(vocabulario);			

			//Cargamos los combos
			longVocabulario = vocabularioOrdenado.length;
			for (int x = 0; x < longVocabulario; x++) {
				TerminoVO terminoVO = new TerminoVO();
				terminoVO.setIdiomaTermino("");
				terminoVO.setIdTermino("");
				terminoVO.setNomTermino("");
				
				switch (x) {
				case 0://IDIOMA DESTINATARIO
					Collection<TerminoVO> id = new ArrayList<TerminoVO>();
					
					id.add(terminoVO);
//					modificamos las cadenas de idiomas
					TerminoVO[] terminos = vocabularioOrdenado[x].getTerminos();
					for (int li=0; li<terminos.length;li++){
						TerminoVO idAux = new TerminoVO();
						idAux=terminos[li];
						String textoIdioma= idAux.getNomTermino();
						String idiomasTra=I18n.getInstance().obtenerIdiomaTraducido(textoIdioma, idiomaLdap);
						idAux.setNomTermino(idiomasTra);
					}
					UtilidadesOrdenarCombos terminosOrdDest2 = new UtilidadesOrdenarCombos();
					TerminoVO[] terminosOrd = terminosOrdDest2.ordenarTerminosVO(terminos, idiomaLdap);					
					Collection<TerminoVO> id2 = Arrays.asList(terminosOrd);
					
					Iterator<TerminoVO> z = id2.iterator();
					while (z.hasNext()) {
						id.add(z.next());
					}

					form.setIdiomasBackingList(id, "idTermino", "nomTermino");//combo idiomas
					form.setComboIdiomaBackingList(id, "idTermino", "nomTermino");//combo idioma de lo campos de texto
					break;
					
				case 1://TIPO RECURSO EDUCATIVO
					Collection<TerminoVO> cTipoRecurso = new ArrayList<TerminoVO>();
					cTipoRecurso.add(terminoVO);
					Collection<TerminoVO> cTipoRecursoAux = Arrays.asList(vocabularioOrdenado[x].getTerminos());
					Iterator<TerminoVO> cont2 = cTipoRecursoAux.iterator();
					while (cont2.hasNext()) {
						cTipoRecurso.add(cont2.next());
					}
					form.setTipoRecursoBackingList(cTipoRecurso, "idTermino", "nomTermino");
					break;
				case 2://DESTINATARIO O ROL DEL USUARIO FINAL
					Collection<TerminoVO> cDestinatrio = new ArrayList<TerminoVO>();
					cDestinatrio.add(terminoVO);
					Collection<TerminoVO> cDesctinatarioAux = Arrays.asList(vocabularioOrdenado[x].getTerminos());
					Iterator<TerminoVO> contR = cDesctinatarioAux.iterator();
					while (contR.hasNext()) {
						cDestinatrio.add(contR.next());
					}
					form.setRolUsuarioBackingList(cDestinatrio, "idTermino", "nomTermino");
					break;
				case 3://CONTEXTO
					Collection<TerminoVO> cContexto = new ArrayList<TerminoVO>();
					cContexto.add(terminoVO);
					Collection<TerminoVO> cContextoAux = Arrays.asList(vocabularioOrdenado[x].getTerminos());
					Iterator<TerminoVO> contC = cContextoAux.iterator();
					while (contC.hasNext()) {
						cContexto.add(contC.next());
					}
					form.setComboContextoBackingList(cContexto, "idTermino", "nomTermino");
					break;
				case 4://PROCESO COGNITIVO 
					Collection<TerminoVO> cProcesoCog = new ArrayList<TerminoVO>();
					cProcesoCog.add(terminoVO);
					Collection<TerminoVO> cProcesoCogAux = Arrays.asList(vocabularioOrdenado[x].getTerminos());
					Iterator<TerminoVO> contP = cProcesoCogAux.iterator();
					while (contP.hasNext()) {
						cProcesoCog.add(contP.next());
					}
					form.setComboProcesoBackingList(cProcesoCog, "idTermino", "nomTermino");
					break;
				case 5://TIPO INTERACTIVIDAD
					Collection<TerminoVO> cTipoInteract = new ArrayList<TerminoVO>();
					cTipoInteract.add(terminoVO);
					Collection<TerminoVO> cTipoInteractAux = Arrays.asList(vocabularioOrdenado[x].getTerminos());
					Iterator<TerminoVO> cont = cTipoInteractAux.iterator();
					while (cont.hasNext()) {
						cTipoInteract.add(cont.next());
					}
					form.setTipoInteractividadBackingList(cTipoInteract, "idTermino", "nomTermino");
					break;

				case 6://NIVEL INTERACTIVIDAD
					Collection<TerminoVO> cNivelInteract = new ArrayList<TerminoVO>();
					cNivelInteract.add(terminoVO);
					Collection<TerminoVO> cNivelInteractAux = Arrays.asList(vocabularioOrdenado[x].getTerminos());
					Iterator<TerminoVO> contN = cNivelInteractAux.iterator();
					while (contN.hasNext()) {
						cNivelInteract.add(contN.next());
					}
					form.setNivelInteractividadBackingList(cNivelInteract, "idTermino", "nomTermino");		

					break;
				case 7://DENSIDAD SEMANTICA
					Collection<TerminoVO> cDensidad = new ArrayList<TerminoVO>();
					cDensidad.add(terminoVO);
					Collection<TerminoVO> cDensidadAux = Arrays.asList(vocabularioOrdenado[x].getTerminos());
					Iterator<TerminoVO> contD = cDensidadAux.iterator();
					while (contD.hasNext()) {
						cDensidad.add(contD.next());
					}
					form.setDensidadSemanticaBackingList(cDensidad, "idTermino", "nomTermino");
					break;
				case 8://DIFICULTAD
					Collection<TerminoVO> cDificultad= new ArrayList<TerminoVO>();
					cDificultad.add(terminoVO);
					Collection<TerminoVO> cDificultadAux = Arrays.asList(vocabularioOrdenado[x].getTerminos());
					Iterator<TerminoVO> contDif = cDificultadAux.iterator();
					while (contDif.hasNext()) {
						cDificultad.add(contDif.next());
					}
					form.setDificultadBackingList(cDificultad, "idTermino", "nomTermino");
					break;
				case 9://TIPO CONOCMIENTO
					Collection<TerminoVO> cTipoConocimiento = Arrays.asList(vocabularioOrdenado[x].getTerminos());
					form.setDescripcionTipoConocimiento(cTipoConocimiento);
					break;
				}
				
				
				if( logger.isDebugEnabled() ){

					logger.debug("Cargados el combo de : " +  l_id[x]);
				}

			}

			logger.debug("Cargados los combos");

        }catch (Exception e) {
			logger.warn("Error cargando combos " + e);
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
    		if (valor instanceof DetalleUsoEduFormImpl) {
    			DetalleUsoEduFormImpl formulario= (DetalleUsoEduFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof DetalleUsoEducativoSubmitFormImpl){
    			DetalleUsoEducativoSubmitFormImpl formulario= (DetalleUsoEducativoSubmitFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoValidoVolverFormImpl){
    			UsoEducativoValidoVolverFormImpl formulario= (UsoEducativoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoNoValidoVolverFormImpl){
    			UsoEducativoNoValidoVolverFormImpl formulario= (UsoEducativoNoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
			
			
			//IDIOMAS
			IdiomaVO[] aNuevoIdiomasVO = new IdiomaVO[1];
			IdiomaVO idioma = new IdiomaVO();
			idioma.setTexto("");
			aNuevoIdiomasVO[0]=idioma;
			form.setIdiomasAsArray(aNuevoIdiomasVO);
			//TIPOS RECURSOS
			SourceValueVO[] aNuevoTiposRecursoVO = new SourceValueVO[1];
			SourceValueVO tipoRecurso = new SourceValueVO();
			tipoRecurso.setSource("");
			tipoRecurso.setValor("");
			aNuevoTiposRecursoVO[0]=tipoRecurso;
			form.setTipoRecursoAsArray(aNuevoTiposRecursoVO);
			//ROL O DESTINATARIOS
			SourceValueVO[] aNuevoDestinatariosVO = new SourceValueVO[1];
			SourceValueVO destinatario = new SourceValueVO();
			destinatario.setSource("");
			destinatario.setValor("");
			aNuevoDestinatariosVO[0]=destinatario;
			form.setRolUsuarioAsArray(aNuevoDestinatariosVO);
			//CONTEXTOS
			SourceValueVO[] aNuevoContextosVO = new SourceValueVO[1];
			SourceValueVO contexto = new SourceValueVO();
			contexto.setSource("");
			contexto.setValor("");
			aNuevoContextosVO[0]=contexto;
			form.setContextoAsArray(aNuevoContextosVO);
			//PROCESOS COGNITIVOS
			SourceValueVO[] aNuevoProcesosVO = new SourceValueVO[1];
			SourceValueVO proceso = new SourceValueVO();
			proceso.setSource("");
			proceso.setValor("");
			aNuevoProcesosVO[0]=proceso;
			form.setProcesoCognitivoAsArray(aNuevoProcesosVO);
			//TIPO INTERACTIVIDAD
			form.setTipoInteractividad("");
			//NIVEL INTERACTIVIDAD
			form.setNivelInteractividad("");
			//DENSIDAD SEMANTICA
			form.setDensidadSemantica("");
			//DIFICULTAD
			form.setDificultad("");
			//TIEMPO APRENDIZAJE
			form.setAnyo("");
			form.setMes("");
			form.setDia("");
			form.setHora("");
			form.setMinutos("");
			form.setSegundosP1("");
			form.setSegundosP2("");
			//DESCRIPCION TIEMPO APRENDIZAJE
			DescripcionVO descripcionTA_VO = new DescripcionVO();
			DescripcionVO[] aDescripcionesTA_VO = new DescripcionVO[1];
			LangStringVO[] nuevolangTextosDescripTA = new LangStringVO[1];
			LangStringVO langTA = new LangStringVO();
			langTA.setIdioma("");
			langTA.setTexto("");
			nuevolangTextosDescripTA[0] = langTA;
			descripcionTA_VO.setTextos(nuevolangTextosDescripTA);
			aDescripcionesTA_VO[0]=descripcionTA_VO;
			form.setTiempoApDescripcionAsArray(aDescripcionesTA_VO); 
			//EDAD TIPICA O RANGO DE EDAD
			RangoEdadVO[] nuevoRangosEdadVO = new RangoEdadVO[1];
			LangStringVO[] aLangString =new LangStringVO[1];
			LangStringVO lang = new LangStringVO();
			lang.setIdioma("");
			lang.setTexto("");
			aLangString[0]=lang;
			RangoEdadVO rangoVO= new RangoEdadVO();
			rangoVO.setTextos(aLangString);
			nuevoRangosEdadVO[0] = rangoVO;
			form.setEdadTipicaAsArray(nuevoRangosEdadVO);
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
			form.setDescripcionesAsArray(nuevoDescripcionVO);
			
			DescripcionUsoEdu[][] descripcionesUsoEdu= new DescripcionUsoEdu[1][1];
			descripcionesUsoEdu[0][0]= DescripcionUsoEdu.getInstance();
			form.setDescripcionesCatAsArray(descripcionesUsoEdu);
			
			UsoEducativoSession usoeducativoSession = this.getUsoEducativoSession(request);
			 //cargar ayuda contextual    
			 cargarAyuda(usoeducativoSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
			
		}catch (Exception e){
			logger.error("Error en catalogadorWeb, categoría Detalle Uso Educativo, metodo reset " + e);
//			throw new java.lang.Exception("detalle.uso.edu", e);
			saveErrorMessage(request, " Error en catalogadorWeb, categoría Detalle Uso Educativo, metodo reset ");
		
		}
	}





	public void anadirDescripcionCat(
			ActionMapping mapping,
			AnadirDescripcionCatForm form, 
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {

    	try{
    	
    		String source=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES) ;//esquemaDeMetadatos
    		
    		Object valor = request.getSession().getAttribute("form");
    		
    		if (valor instanceof DetalleUsoEduFormImpl) {
    			DetalleUsoEduFormImpl formulario= (DetalleUsoEduFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof DetalleUsoEducativoSubmitFormImpl){
    			DetalleUsoEducativoSubmitFormImpl formulario= (DetalleUsoEducativoSubmitFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoValidoVolverFormImpl){
    			UsoEducativoValidoVolverFormImpl formulario= (UsoEducativoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoNoValidoVolverFormImpl){
    			UsoEducativoNoValidoVolverFormImpl formulario= (UsoEducativoNoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}

    		
    		int[] longitudTextosDesc = new int[form.getDescripciones().size()];
    		for(int i= 0;i< form.getDescripciones().size();i++){
    			longitudTextosDesc[i]=((DescripcionVO)(form.getDescripcionesAsArray()[i])).getTextos().length;
    		}
    		int[] longitudTextosRangosEdad = new int[form.getEdadTipica().size()];
    		for(int i= 0;i< form.getEdadTipica().size();i++){
    			longitudTextosRangosEdad[i]=((RangoEdadVO)(form.getEdadTipicaAsArray()[i])).getTextos().length;
    		}

    		int longitudDescripciones=form.getDescripciones().size();
    		int longitudRangos=form.getEdadTipica().size();
    		int longitudIdiomas=form.getIdiomas().size();
    		int longitudTipoRecurso=form.getTipoRecurso().size();
    		int longitudRoles=form.getRolUsuario().size();
    		int longitudContextos=form.getContexto().size();
    		int longitudProcesos=form.getProcesoCognitivo().size();
    		int longitudTADesc=(((DescripcionVO[])form.getTiempoApDescripcionAsArray())[0]).getTextos().length;
    		
    		//borrar session
    		 request.getSession().removeAttribute(UsoEducativoSession.SESSION_KEY);
    		cambioFormulario(request,longitudTextosDesc,longitudTextosRangosEdad,
    				longitudDescripciones,longitudRangos,longitudIdiomas,longitudTipoRecurso,longitudRoles,
    				longitudContextos,longitudProcesos,longitudTADesc,source);
    		
    		UsoEducativoSession usoeducativoSession = this.getUsoEducativoSession(request);
    		//tipo recurso educativo
    		form.setTipoRecursoAsArray(usoeducativoSession.getTiposRecurso().toArray());
    		//rol o destinatario
    		form.setRolUsuarioAsArray(usoeducativoSession.getRoles().toArray());
    		//contextos
    		form.setContextoAsArray(usoeducativoSession.getContextos().toArray());
    		//procesos cognitivos
    		form.setProcesoCognitivoAsArray(usoeducativoSession.getProcesosCognitivos().toArray());
    		//rangos de edad
    		form.setEdadTipicaAsArray(usoeducativoSession.getRangosEdad().toArray());       
    		//idioma
    		form.setIdiomasAsArray(usoeducativoSession.getIdiomas().toArray());
    		//descripciones
    		form.setDescripcionesAsArray(usoeducativoSession.getDescripciones().toArray());
    		form.setDescripcionesCatAsArray(usoeducativoSession.getDescripcionesCat().toArray());
    		//descripcion tiempo de aprendizaje
    		form.setTiempoApDescripcionAsArray(usoeducativoSession.getTiempoApDescripcion().toArray());
    		
 		  	form.setAnyo(usoeducativoSession.getAnyos());
		  	form.setMes(usoeducativoSession.getMeses());
		  	form.setDia(usoeducativoSession.getDias());
		  	form.setHora(usoeducativoSession.getHoras());
		  	form.setMinutos(usoeducativoSession.getMinutos());
		  	form.setSegundosP1(usoeducativoSession.getSegundosP1());
		  	form.setSegundosP2(usoeducativoSession.getSegundosP2());
    		
		  	 //cargar ayuda contextual    
			 cargarAyuda(usoeducativoSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
			
		}catch (Exception e) {
			logger
			.error("Error en Servicio de catalogacionWeb, categoría Tecnica, metodo anadirContenidoDescripcion " + e);
//			throw new Exception("tecnica",e);
			saveErrorMessage(request, " Error en Servicio de catalogacionWeb, categoría Tecnica, metodo anadirContenidoDescripcion ");
		}
     		
	}




	public void eliminarDescripcionCat(
			ActionMapping mapping,
			EliminarDescripcionCatForm form, 
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception
	{
    	try{
    		
    		String source=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES) ;//esquemaDeMetadatos
    		
    		Object valor = request.getSession().getAttribute("form");
    		
    		if (valor instanceof DetalleUsoEduFormImpl) {
    			DetalleUsoEduFormImpl formulario= (DetalleUsoEduFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof DetalleUsoEducativoSubmitFormImpl){
    			DetalleUsoEducativoSubmitFormImpl formulario= (DetalleUsoEducativoSubmitFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoValidoVolverFormImpl){
    			UsoEducativoValidoVolverFormImpl formulario= (UsoEducativoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		else if(valor instanceof UsoEducativoNoValidoVolverFormImpl){
    			UsoEducativoNoValidoVolverFormImpl formulario= (UsoEducativoNoValidoVolverFormImpl)valor;
    		  	form.setDescripciones(formulario.getDescripciones());
    		  	form.setEdadTipica(formulario.getEdadTipica());
    		  	form.setIdiomas(formulario.getIdiomas());
    		  	form.setTiempoApDescripcion(formulario.getTiempoApDescripcion());
    		  	form.setContexto(formulario.getContexto());
    		  	form.setProcesoCognitivo(formulario.getProcesoCognitivo());
    		  	form.setRolUsuario(formulario.getRolUsuario());
    		  	form.setTipoRecurso(formulario.getTipoRecurso());
    		  	form.setIdiomasLabelList(formulario.getIdiomasLabelList());
    		  	form.setIdiomasValueList(formulario.getIdiomasValueList());
    		  	form.setComboIdiomaLabelList(formulario.getComboIdiomaLabelList());
    		  	form.setComboIdiomaValueList(formulario.getComboIdiomaValueList());
    		  	form.setComboContextoLabelList(formulario.getComboContextoLabelList());
    		  	form.setComboContextoValueList(formulario.getComboContextoValueList());
    		  	form.setComboProcesoLabelList(formulario.getComboProcesoLabelList());
    		  	form.setComboProcesoValueList(formulario.getComboProcesoValueList());
    		  	form.setTipoRecursoLabelList(formulario.getTipoRecursoLabelList());
    		  	form.setTipoRecursoValueList(formulario.getTipoRecursoValueList());
    		  	form.setRolUsuarioLabelList(formulario.getRolUsuarioLabelList());
    		  	form.setRolUsuarioValueList(formulario.getRolUsuarioValueList());
    		  	form.setTipoInteractividadLabelList(formulario.getTipoInteractividadLabelList());
    		  	form.setTipoInteractividadValueList(formulario.getTipoInteractividadValueList());
    		  	form.setNivelInteractividadLabelList(formulario.getNivelInteractividadLabelList());
    		  	form.setNivelInteractividadValueList(formulario.getNivelInteractividadValueList());
    		  	form.setDensidadSemanticaLabelList(formulario.getDensidadSemanticaLabelList());
    		  	form.setDensidadSemanticaValueList(formulario.getDensidadSemanticaValueList());
    		  	form.setDificultadLabelList(formulario.getDificultadLabelList());
    		  	form.setDificultadValueList(formulario.getDificultadValueList());
    		  	form.setDescripcionTipoConocimiento(formulario.getDescripcionTipoConocimiento());
    		}
    		
    		int[] longitudTextosDesc = new int[form.getDescripciones().size()];
    		for(int i= 0;i< form.getDescripciones().size();i++){
    			longitudTextosDesc[i]=((DescripcionVO)(form.getDescripcionesAsArray()[i])).getTextos().length;
    		}
    		int[] longitudTextosRangosEdad = new int[form.getEdadTipica().size()];
    		for(int i= 0;i< form.getEdadTipica().size();i++){
    			longitudTextosRangosEdad[i]=((RangoEdadVO)(form.getEdadTipicaAsArray()[i])).getTextos().length;
    		}

    		int longitudDescripciones=form.getDescripciones().size();
    		int longitudRangos=form.getEdadTipica().size();
    		int longitudIdiomas=form.getIdiomas().size();
    		int longitudTipoRecurso=form.getTipoRecurso().size();
    		int longitudRoles=form.getRolUsuario().size();
    		int longitudContextos=form.getContexto().size();
    		int longitudProcesos=form.getProcesoCognitivo().size();
    		int longitudTADesc=(((DescripcionVO[])form.getTiempoApDescripcionAsArray())[0]).getTextos().length;
    		
    		//borrar session
    		request.getSession().removeAttribute(UsoEducativoSession.SESSION_KEY);
    		cambioFormulario(request,longitudTextosDesc,longitudTextosRangosEdad,
    				longitudDescripciones,longitudRangos,longitudIdiomas,longitudTipoRecurso,longitudRoles,
    				longitudContextos,longitudProcesos,longitudTADesc,source);
    		//recoger session
    		UsoEducativoSession usoeducativoSession = this.getUsoEducativoSession(request);
    		//tipo recurso educativo
    		form.setTipoRecursoAsArray(usoeducativoSession.getTiposRecurso().toArray());
    		//rol o destinatario
    		form.setRolUsuarioAsArray(usoeducativoSession.getRoles().toArray());
    		//contextos
    		form.setContextoAsArray(usoeducativoSession.getContextos().toArray());
    		//procesos cognitivos
    		form.setProcesoCognitivoAsArray(usoeducativoSession.getProcesosCognitivos().toArray());
    		//rangos de edad
    		form.setEdadTipicaAsArray(usoeducativoSession.getRangosEdad().toArray());       
    		//idioma
    		form.setIdiomasAsArray(usoeducativoSession.getIdiomas().toArray());
    		//descripciones
    		form.setDescripcionesAsArray(usoeducativoSession.getDescripciones().toArray());
    		//descripcion tiempo de aprendizaje
    		form.setTiempoApDescripcionAsArray(usoeducativoSession.getTiempoApDescripcion().toArray());
    		
 		  	form.setAnyo(usoeducativoSession.getAnyos());
		  	form.setMes(usoeducativoSession.getMeses());
		  	form.setDia(usoeducativoSession.getDias());
		  	form.setHora(usoeducativoSession.getHoras());
		  	form.setMinutos(usoeducativoSession.getMinutos());
		  	form.setSegundosP1(usoeducativoSession.getSegundosP1());
		  	form.setSegundosP2(usoeducativoSession.getSegundosP2());
    		

            //posicion de contenido a eliminar   
            String posicion = request.getAttribute("posicion").toString();
            int posicionInt = Integer.parseInt(posicion);
    		
    		//posicion de la descripcion de la que se quiere eliminar parte de su contenido
            String posicionExt = request.getAttribute("posicionExterior").toString();
            int posicionExtInt = Integer.parseInt(posicionExt);

	    	//se recogen todas las descripciones
	    	DescripcionVO[] descArray = (DescripcionVO[]) form.getDescripcionesAsArray();
	    	//recogo los textos de la descripcion indicada y le añado un nuevo texto
	    	LangStringVO[] textos = descArray[posicionInt].getTextos();

	    	LangStringVO nuevoTexto = new LangStringVO();
	    	nuevoTexto.setIdioma("");
	    	nuevoTexto.setTexto("");
	    	textos[posicionExtInt]= nuevoTexto;
	    	descArray[posicionInt].setTextos(textos);
	    	form.setDescripcionesAsArray(descArray);
	    	
	    	DescripcionUsoEdu[][] descripcionesCat =(DescripcionUsoEdu[][])usoeducativoSession.getDescripcionesCat()
					.toArray(new DescripcionUsoEdu[usoeducativoSession.getDescripcionesCat().size()][]);
	    	descripcionesCat[posicionInt][posicionExtInt]= DescripcionUsoEdu.getInstance();
	    	form.setDescripcionesCatAsArray(descripcionesCat);
	    	usoeducativoSession.setDescripcionesCat(Arrays.asList(descripcionesCat));
    	
	    	 //cargar ayuda contextual    
			 cargarAyuda(usoeducativoSession, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
	    	
		}catch (ArrayIndexOutOfBoundsException ai) {
			logger
			.warn("Warning en catalogadorWeb, categoría Tecnica, metodo eliminarDescripcion " + ai);
		}catch (Exception e) {
			logger
			.error("Error en Servicio de catalogacionWeb, categoría Tecnica, metodo eliminarDescripcion " + e);
//			throw new Exception("tecnica",e);
			saveErrorMessage(request, " Error en Servicio de catalogacionWeb, categoría Tecnica, metodo eliminarDescripcion ");
		}
     }



	public boolean hayUsosEducativos(
			ActionMapping mapping, 
			HayUsosEducativosForm form, 
			HttpServletRequest request, 
			HttpServletResponse response) 
	throws Exception 
	{
		boolean result = false;
		CatalogadorAvSession catalogadorAvSession=this.getCatalogadorAvSession(request);
		try {
	    	if (catalogadorAvSession.getMDSesion()!=null) {
	    		AvEducationalVO[] usosEducativos =catalogadorAvSession.getMDSesion().getEducational();
	    		if (usosEducativos == null || usosEducativos.length == 0 ){
	    			result = false;
	    		}
	    		else 
	    			result = true;
	   
	    	}
		}catch (Exception e) {
			logger.error("Error AnotacionController, método hayUsosEducativos "+ e);
//			throw new java.lang.Exception("usoEducativo", e);
			saveErrorMessage(request, " Error AnotacionController, método hayUsosEducativos ");
		}
		return result;
	}


	public boolean hayUsosEducativosGuardar(
			ActionMapping mapping,
			HayUsosEducativosGuardarForm form, 
			HttpServletRequest request,
			HttpServletResponse response) 
	throws Exception 
	{
		boolean result = false;
		CatalogadorAvSession catalogadorAvSession=this.getCatalogadorAvSession(request);
		try {
	    	if (catalogadorAvSession.getMDSesion()!=null) {
	    		AvEducationalVO[] usosEducativos =catalogadorAvSession.getMDSesion().getEducational();
	    		if (usosEducativos == null || usosEducativos.length == 0 ){
	    			result = false;
	    		}
	    		else 
	    			result = true;
	   
	    	}
		}catch (Exception e) {
			logger.error("Error AnotacionController, método hayUsosEducativosGuardar "+ e);
//			throw new java.lang.Exception("usoEducativo", e);
			saveErrorMessage(request, " Error AnotacionController, método hayUsosEducativosGuardar ");
		}
		return result;
	}

//	metodo que devuelve la ayuda contextual	
	private void cargarAyuda(UsoEducativoSession uso, Locale idioma) {	
		
		ResourceBundle datosResources = I18n.getInstance().getResource("application-resources", idioma);
		//resultado = prop.getProperty(salidaAyuda);
		uso.setAyudaContexto(datosResources.getString("cat.ayuda.usoeducativo.contexto"));
		uso.setAyudaDensidadSem(datosResources.getString("cat.ayuda.usoeducativo.densidad"));
		uso.setAyudaDest(datosResources.getString("cat.ayuda.usoeducativo.destinatario"));
		uso.setAyudaDificultad(datosResources.getString("cat.ayuda.usoeducativo.dificultad"));
		uso.setAyudaEdadDest(datosResources.getString("cat.ayuda.usoeducativo.rangoedad"));
		uso.setAyudaIdiomaDest(datosResources.getString("cat.ayuda.usoeducativo.idioma"));
		uso.setAyudaNivelInteractividad(datosResources.getString("cat.ayuda.usoeducativo.tipointer"));
		uso.setAyudaOrientaciones(datosResources.getString("cat.ayuda.usoeducativo.descripcion")); 
		uso.setAyudaProcesoCognitivo(datosResources.getString("cat.ayuda.usoeducativo.procesocog"));
		uso.setAyudaTiempoAprendizaje(datosResources.getString("cat.ayuda.usoeducativo.tiempoaprendizaje"));
		uso.setAyudaTipoInteractividad(datosResources.getString("cat.ayuda.usoeducativo.tipointer"));
		uso.setAyudaTipoRecEducativo(datosResources.getString("cat.ayuda.usoeducativo.tiporec"));
		
		logger.debug("USO EDUCATIVO CONTROLLER, CARGADA AYUDA CONTEXTUAL idioma " + idioma);
	}

}