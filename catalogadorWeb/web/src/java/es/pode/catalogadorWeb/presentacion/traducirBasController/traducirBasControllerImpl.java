/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.catalogadorWeb.presentacion.traducirBasController;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.catalogacion.negocio.servicios.ArbolCurricularVO;
import es.pode.catalogacion.negocio.servicios.CBTaxonVO;
import es.pode.catalogacion.negocio.servicios.ClassificationVO;
import es.pode.catalogacion.negocio.servicios.EducationalVO;
import es.pode.catalogacion.negocio.servicios.GeneralVO;
import es.pode.catalogacion.negocio.servicios.LomBasicoVO;
import es.pode.catalogacion.soporte.Traducir;
import es.pode.catalogacion.soporte.UtilidadesOrdenarCombos;
import es.pode.catalogadorWeb.presentacion.CatalogadorBSession;
import es.pode.fuentestaxonomicas.negocio.servicio.EstructuraVdexVO;
import es.pode.fuentestaxonomicas.negocio.servicio.TerminoVO;
import es.pode.fuentestaxonomicas.negocio.servicio.TerminoYPadreVO;
import es.pode.fuentestaxonomicas.negocio.servicio.VocabularioVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.i18n.I18n;



/**
 * @see es.pode.catalogadorWeb.presentacion.traducirBasController.traducirBasController
 */
public class traducirBasControllerImpl extends traducirBasController
{
	protected static Logger logger = Logger.getLogger(traducirBasControllerImpl.class);
	
    /**
     * @see es.pode.catalogadorWeb.presentacion.traducirBasController.traducirBasController#traducirTodo(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.traducirBasController.TraducirTodoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void traducirTodo(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.traducirBasController.TraducirTodoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String idiomaCat = "";
		String idiomaTrad = "";
		LomBasicoVO lomBas=null;
		LomBasicoVO lomBasico = null;
		CatalogadorBSession catalogadorbSession = this.getCatalogadorBSession(request);
		ResourceBundle datosResources = I18n.getInstance().getResource("application-resources", (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
    	try {
	    	if (catalogadorbSession!=null){
	    		//realizamos la traduccion
	    		idiomaTrad = form.getIdiomaSeleccionado();
	    		idiomaCat = catalogadorbSession.getMBOSesion().getIdiomaMetameta();
	    		//pasamos los idiomas de identificador 5.11.... a código iso
	    		 String[] arrayIdsVer = {idiomaTrad};
	    		 TerminoYPadreVO[] terminosTraducVer = this.getSrvVocabulariosControladosService().crearTraduccionAIngles(arrayIdsVer);
	    		   if ((terminosTraducVer!=null) && (terminosTraducVer.length == 1)){
	    			  idiomaTrad = terminosTraducVer[0].getNomTermino();
	    		   }
	    		   
	    		   try{
	    			   //Recojo el lom basico de sesion
	    			   LomBasicoVO lomSesion = null;
	    			   if(catalogadorbSession.getMBOSesion() != null){
	    				   lomSesion = catalogadorbSession.getMBOSesion();
	    			   }
	    			   else{
	    				   lomSesion = new LomBasicoVO();
	    			   }
	    			   //Monto un lom basico con los datos del formulario metidos en sesion
	    			   LomBasicoVO lomSesionTrad = new LomBasicoVO();
	    			   lomSesionTrad.setGeneral(new GeneralVO(catalogadorbSession.getTituloTrad(),
	    					   							  catalogadorbSession.getIdiomaTrad(),
	    					   							  catalogadorbSession.getDescripcionTrad()));
	    			   if(lomSesion != null){
	    				   EducationalVO edu = null;
	    				   if(lomSesion.getEducational() != null){
	    					   edu = new EducationalVO();
	    					   edu.setTipo(catalogadorbSession.getTipoRecursoTrad());
	    					   if(edu.getTipo() == null){
	    						   edu.setTipo("");
	    					   }
	    					   edu.setContexto(lomSesion.getEducational().getContexto());
	    					   if(edu.getContexto() == null){
	    						   edu.setContexto("");
	    					   }
	    					   edu.setEdad(lomSesion.getEducational().getEdad());
	    					   if(edu.getEdad() == null){
	    						   edu.setEdad("");
	    					   }
	    					   edu.setIdiomaDest(catalogadorbSession.getIdiomaDestTrad());
	    					   if(edu.getIdiomaDest() == null){
	    						   edu.setIdiomaDest("");
	    					   }
	    					   edu.setProcesoCog(lomSesion.getEducational().getProcesoCog());
	    					   if(edu.getProcesoCog() == null){
	    						   edu.setProcesoCog("");
	    					   }
	    				   }
	    				   lomSesionTrad.setEducational(edu);
	    				   
	    				   ClassificationVO clas = null;
	    				   if(catalogadorbSession.getArbolesTrad() != null){
	    					   clas = new ClassificationVO();
	    					   Collection arbolesTrad = catalogadorbSession.getArbolesTrad();
	    					   ArbolCurricularVO[] arboles = (ArbolCurricularVO[])arbolesTrad.toArray(new ArbolCurricularVO[arbolesTrad.size()]);
	    					   clas.setArbolesCurriculares(arboles);
	    				   }
	    				   lomSesionTrad.setClassification(clas);
	    			   }
	    			   
	    			   lomSesionTrad.setIdiomaMetameta(catalogadorbSession.getMBOSesion().getIdiomaMetameta());
	    			  
	    			   lomBas=this.getSrvCatalogacionBasicaService().traducirLomes(catalogadorbSession.getIdentificador(), 
	    			   			catalogadorbSession.getUsuario(), idiomaTrad, lomSesionTrad, idiomaCat, false);
	    			   lomBasico = this.cambiarTaxonomias(lomBas, idiomaCat, idiomaTrad, false);
	    			   if(lomBasico == null){ //Si no se han podido traducir las taxonomias, las dejamos sin traducir
	    				   lomBasico = lomBas;
	    			   }
	    		   } catch (Exception e) {
					lomBasico=null;
					form.setDatosIdiomasAsArray(new TerminoVO[0]);
					if(e.getMessage().contains("google-api-translate-java")){
						form.setMensajeFin(datosResources.getString("error.traduccion.sinConexion"));
					}
					else{
						form.setMensajeFin("");
					}
				}		    	
	    	}
    	} catch (Exception e) {
			logger.error("Se ha producido un error ");
		}
//    	if (idiomaCat.equals(idiomaTrad)){
//		   //aviso de q el dioma de origen y el de traduccion no pueden ser el mismo
//		   throw new ValidatorException("{error.traduccion.idiomaCoincidente}"); 
//		}
    	if(lomBasico != null){
			 if(lomBasico.getGeneral() != null){
	    		 catalogadorbSession.setTituloTrad(lomBasico.getGeneral().getTitulo());
	    		 catalogadorbSession.setIdiomaTrad(lomBasico.getGeneral().getIdioma());
	    		 catalogadorbSession.setDescripcionTrad(lomBasico.getGeneral().getDesc());
			 }
			 if(lomBasico.getEducational() != null){
				 catalogadorbSession.setTipoRecursoTrad(lomBasico.getEducational().getTipo());
				 catalogadorbSession.setIdiomaDestTrad(lomBasico.getEducational().getIdiomaDest());
			 }
			 if(lomBasico.getClassification() != null){
				 ArbolCurricularVO[] arboles = lomBasico.getClassification().getArbolesCurriculares();
				 ArrayList arrArboles = new ArrayList();
				 for(int i = 0; i < arboles.length; i++){
					 arrArboles.add(arboles[i]);
				 }
				 
				 catalogadorbSession.setArbolesTrad(arrArboles);
				 catalogadorbSession.setArbolesController(arrArboles);
				 catalogadorbSession.getMBOSesion().setIdiomaMetameta(idiomaTrad);
			 }
    		
    	}
    }


    /**
     * @see es.pode.catalogadorWeb.presentacion.traducirBasController.traducirBasController#traducirCoincidentes(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.traducirBasController.TraducirCoincidentesForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void traducirCoincidentes(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.traducirBasController.TraducirCoincidentesForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        // this property receives a default value, just to have the application running on dummy data
    	String idiomaCatalogacion= "";
		String idiomaTrad = "";
		LomBasicoVO lomBasico=null;
		LomBasicoVO lomBas = null;
		CatalogadorBSession catalogadorbSession = this.getCatalogadorBSession(request);
		ResourceBundle datosResources = I18n.getInstance().getResource("application-resources", (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
    	try {
	    	if (catalogadorbSession!=null){
	    		if (catalogadorbSession.getMBOSesion()==null){
	    			catalogadorbSession.setMBOSesion(new LomBasicoVO());
	    		}
	    		//realizamos la traduccion
	    		//Cambiar este idioma!!!!!
	    		idiomaCatalogacion= catalogadorbSession.getIdioma();//((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).toString();
	    		idiomaTrad = form.getIdiomaSeleccionado();
	    		//pasamos el idioma seleccionado de identificador 5.11.... a código iso
	    		 String idioma= "";
	    		 String[] arrayIdsVer = {idiomaTrad};
	    		 TerminoYPadreVO[] terminosTraducVer = this.getSrvVocabulariosControladosService().crearTraduccionAIngles(arrayIdsVer);
	    		   if ((terminosTraducVer!=null) && (terminosTraducVer.length>0)){
	    			  idioma= terminosTraducVer[0].getNomTermino();
	    		   }
	    		   if (!idioma.equals("")) 
	    			   idiomaTrad=idioma;
	    		   
	    		   String[] arrayIds = {idiomaCatalogacion};
	    		   terminosTraducVer = this.getSrvVocabulariosControladosService().crearTraduccionAIngles(arrayIds);
	    		   if ((terminosTraducVer!=null) && (terminosTraducVer.length>0)){
		    			  idioma= terminosTraducVer[0].getNomTermino();
		    		   }
	    		   if (!idioma.equals("")) 
		    			   idiomaCatalogacion=idioma;
	    		   try{
	    			   lomBas=this.getSrvCatalogacionBasicaService().traducirLomes(catalogadorbSession.getIdentificador(), 
			    				catalogadorbSession.getUsuario(), idiomaTrad, catalogadorbSession.getMBOSesion(), idiomaCatalogacion, true);
	    			   lomBasico = this.cambiarTaxonomias(lomBas, idiomaCatalogacion, idiomaTrad, true);
	    		   }catch (Exception e) {
	    			   lomBasico=null;
	    			   form.setDatosIdiomasAsArray(new TerminoVO[0]);
	    			   if(e.getMessage().contains("google-api-translate-java")){
							form.setMensajeFin(datosResources.getString("error.traduccion.sinConexion"));
						}
						else{
							form.setMensajeFin("");
						}
				}	    		
	    	}
    	} catch (Exception e) {
			// TODO: handle exception    		
    		logger.error("Se ha producido un error " + e);
		}
//    	if (idiomaCatalogacion.equals(idiomaTrad)){
// 		   //aviso de q el dioma de origen y el de traduccion no pueden ser el mismo
// 		   throw new ValidatorException("{error.traduccion.idiomaCoincidente}");
// 		}
    	if(lomBasico != null){
    		catalogadorbSession.setMBOSesion(lomBasico);
    	}
         
    }



//    private String obtenerAccion(HttpServletRequest request) throws Exception
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



    /**
     * @see es.pode.catalogadorWeb.presentacion.traducirBasController.traducirBasController#tipoTraduccion(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.traducirBasController.TipoTraduccionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String tipoTraduccion(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.traducirBasController.TipoTraduccionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        // this property receives a default value, just to have the application running on dummy data
    	String idiomaSeleccionado = request.getParameter("idiomaSeleccionado");
    	
    	String accion= UtilidadesOrdenarCombos.obtenerAccion(request);
    	//Collection termDestOrd=form.getDatosIdiomas();
    	String resultado="";
    	
    	//recupero los datos de la colección
    	Object valor = request.getSession().getAttribute("form");
    	if (valor instanceof TraducirLomesBasFormImpl) {
    		TraducirLomesBasFormImpl datosForm = (TraducirLomesBasFormImpl) valor;
			form.setDatosIdiomas(datosForm.getDatosIdiomas());
			form.setDatosTraduccion(datosForm.getDatosTraduccion());
			form.setIdiomaSeleccionado(idiomaSeleccionado);
    	} else if (valor instanceof TraducirMBasicosComprobarIdiomasFormImpl){
    		TraducirMBasicosComprobarIdiomasFormImpl datosForm = (TraducirMBasicosComprobarIdiomasFormImpl)valor;
    		form.setDatosIdiomas(datosForm.getDatosIdiomas());
    		form.setDatosTraduccion(datosForm.getDatosTraduccion());
    		form.setIdiomaSeleccionado(datosForm.getIdiomaSeleccionado());
    	}
    	//ahora tengo que ver el tipo de idioma de catalogación que tiene el ode
    	CatalogadorBSession catalogadorBSession = this.getCatalogadorBSession(request);
    	if (catalogadorBSession!=null){
    		if (catalogadorBSession.getMBOSesion()==null) {
    			catalogadorBSession.setMBOSesion(new LomBasicoVO());
    		}
    		ResourceBundle datosResources = I18n.getInstance().getResource("application-resources", (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
    		//Le damos a cancelar
    		if ((accion!=null) && accion.equals(datosResources.getString("catalogadorBasico.Cancelar"))) {
    			resultado="Cancelar";
//    		}else if ((!form.getDatosTraduccion().isEmpty()) && (form.getTipoTraduccion()!=null) && (!form.getTipoTraduccion().equals(""))){
//    			//Refinar pero para q funcione en principio...
//    			if (form.getTipoTraduccion().equals(TODOS)){
//    				resultado="Todos";
//    			}else if (form.getTipoTraduccion().equals(NINGUNO)){
//    				resultado="Ninguno";
//    			}else if (form.getTipoTraduccion().equals(COINCIDENTES)){
//    				resultado="Coincidentes";
//    			}
    		}else{
    			resultado = "Todos";
    		}
    	}
        form.setTipoTraduccion(resultado);
        form.setMensajeFin("");
        //quitar esto!!
        //form.setIdiomaSeleccionado(idiomaSeleccionado);
         return resultado;
    }







    /**
     * @see es.pode.catalogadorWeb.presentacion.traducirBasController.traducirBasController#cargarDatos(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.traducirBasController.CargarDatosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final boolean cargarDatos(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.traducirBasController.CargarDatosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	 InputStream is =null;
         boolean errorValidacion=true;
     	
         try {
         	is = this.getClass().getResourceAsStream("/catalogadorBasico.properties");
         	Properties prop = new Properties();
         	prop.load(is);
         	//idioma de Catalogación!!!
         	String idiomaCat="";
         	CatalogadorBSession catalogadorbSession = this.getCatalogadorBSession(request);
         	if (catalogadorbSession!=null){
 	    		if (catalogadorbSession.getMBOSesion()==null){
 	    			catalogadorbSession.setMBOSesion(new LomBasicoVO());
 	    		}
 	    		//cogemos el idioma de catalogacion; idioma de metametadata!!!
 	    		idiomaCat = catalogadorbSession.getMBOSesion().getIdiomaMetameta();
 	    	}
 	    	//En caso de que idiomaCat sea vacio cogemos el de navegacion
 	    	if (idiomaCat == null || idiomaCat.equals("")){
 	    		idiomaCat = ((Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).toString();
 	    		catalogadorbSession.getMBOSesion().setIdiomaMetameta(idiomaCat);
 	    	}
    		
			String idiomaNav = ((Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).toString();
        	String[] propIdioma = {prop.getProperty("idiomaDestinatario")};
        	VocabularioVO[] comboIngles=this.getSrvVocabulariosControladosService().obtenerCombos(propIdioma, idiomaNav);
        	//formo una colección para mostrar los idiomas con radio buttons
        	TerminoVO[] terminosDest = comboIngles[0].getTerminos();
			for (int k=0; k<terminosDest.length;k++){
				TerminoVO idAux = new TerminoVO();
				idAux=terminosDest[k];
				String textoIdioma= idAux.getNomTermino();
				String idiomasTra=I18n.getInstance().obtenerIdiomaTraducido(textoIdioma, idiomaNav);
				idAux.setNomTermino(idiomasTra);
			}
			//Quitamos el euskera y el valenciano de la lista
			ArrayList arrTerminos = new ArrayList();
			for(int k = 0; k < terminosDest.length; k++){
				if(!terminosDest[k].getIdTermino().equals("5.11.4") && !terminosDest[k].getIdTermino().equals("5.11.6")){
					arrTerminos.add(terminosDest[k]);
				}
			}
			TerminoVO[] terminos = (TerminoVO[])arrTerminos.toArray(new TerminoVO[arrTerminos.size()]);
        	UtilidadesOrdenarCombos terminosOrd = new UtilidadesOrdenarCombos();
        	TerminoVO[] termDestOrd=terminosOrd.ordenarTerminosVO(terminos, idiomaCat);
        	for(int k = 0; k < termDestOrd.length; k++){
        		String idioma = termDestOrd[k].getNomTermino();
        		termDestOrd[k].setNomTermino(idioma.substring(0, 1).toUpperCase() + idioma.substring(1));
        	}
        	//Collection destOrd= Arrays.asList(termDestOrd);
        	form.setDatosIdiomasAsArray(termDestOrd);
        	form.setDatosTraduccionAsArray(new Traducir[0]);
        	is.close();
         	
         }catch (Exception e) {
 			is.close();
 			logger.error("Se ha producido un Error al Cargar los Datos " + e);
 			errorValidacion = false;
 		}  
         return errorValidacion;
     }







	@Override
	public void volverInicioBas(ActionMapping mapping, VolverInicioBasForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("Vamos a volver");
		String idiomaNavegacion=((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
		CatalogadorBSession catalogadorbSession = this.getCatalogadorBSession(request);
    	if (catalogadorbSession!=null){
    		if (catalogadorbSession.getMBOSesion()==null){
    			catalogadorbSession.setMBOSesion(new LomBasicoVO());
    		}
    		catalogadorbSession.setIdioma(idiomaNavegacion);//la ponemos bien otra vez
    	}
		
	}


	private LomBasicoVO cambiarTaxonomias(LomBasicoVO lomBas, String idiomaOri, String idiomaTrad, boolean coincidentes) throws Exception{		
		LomBasicoVO lomBasTrad = null;
    	if(lomBas != null){
    		String nombreArbol = this.getSrvTaxonomiaService().obtenerArbolVigente().getVocabName();
    		if(this.getSrvTaxonomiaService().chequearIdiomaTaxonomia(nombreArbol, idiomaTrad)){
	    		lomBasTrad = new LomBasicoVO();
	    		ClassificationVO unaClasif = null;
	    		if(lomBas.getClassification() != null){
	    			//Obtenemos la clasificacion
	    			ClassificationVO clasificacion = lomBas.getClassification();
	    			unaClasif = new ClassificationVO();
	    			if(clasificacion.getArbolesCurriculares() != null){
	    				ArbolCurricularVO[] arboles = clasificacion.getArbolesCurriculares();
	    				ArrayList arbolesTrad = new ArrayList();
	    				for(int i = 0; i < arboles.length; i++){ //Cada arbol curricular
	    					//Obtenemos el id del arbol y los taxones que contiene
	    					String idArbol = arboles[i].getIdArbol();
	    					CBTaxonVO[] taxones = arboles[i].getTaxones();
	    					//Obtenemos las taxonomias (Arbol_Curricular en este caso) que hay disponibles en el idioma de traduccion
			    			EstructuraVdexVO[] estrVdex = this.getSrvTaxonomiaService().obtenerTaxonomias(idiomaTrad);
			    			//Comprobamos que la taxonomia del lom esta disponible en el idioma de traduccion
			    			es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[] taxDisponibles = this.getSrvTaxonomiaService().obtenerTaxonomia(nombreArbol, idiomaTrad);
			    			if(taxDisponibles != null && taxDisponibles.length > 0){
			    				//Obtenemos los taxones traducidos
			    				es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[] taxonesTrad = this.getSrvTaxonomiaService().obtenerTaxonPath(taxones[0].getId(), nombreArbol, idiomaTrad);
			    				//Formamos los taxones de catalogacion
			    				if(taxonesTrad != null){
									//Formo los TaxonVO de catalogacion
									ArrayList arrTaxon = new ArrayList();
									for(int j = 0; j < taxonesTrad.length; j++){
										CBTaxonVO unTaxon = new CBTaxonVO();
										unTaxon.setId(taxones[j].getId());
										unTaxon.setValorTax(taxonesTrad[j].getValorTax());
										unTaxon.setEsHoja(taxones[j].getEsHoja());
										arrTaxon.add(unTaxon);
									}
									//Meto los taxones en un array
									CBTaxonVO[] arrTaxones = (CBTaxonVO[])arrTaxon.toArray(new CBTaxonVO[arrTaxon.size()]);
									//Formo el arbol curricular
									ArbolCurricularVO arbolCurr = new ArbolCurricularVO();
									//TAXONES e ID
									arbolCurr.setTaxones(arrTaxones);
									arbolCurr.setIdArbol(idArbol);
									arbolesTrad.add(arbolCurr);
								}
			    			}
			    			else{
			    				arbolesTrad.add(arboles[i]);
			    			}
	    				}
	    				ArbolCurricularVO[] arbolesCurr = (ArbolCurricularVO[])arbolesTrad.toArray(new ArbolCurricularVO[arbolesTrad.size()]);
	    				unaClasif.setArbolesCurriculares(arbolesCurr);
	    			}
	    		}
	    		lomBasTrad.setClassification(unaClasif);
	    		lomBasTrad.setEducational(lomBas.getEducational());
	    		lomBasTrad.setGeneral(lomBas.getGeneral());
	    		lomBasTrad.setIdiomaMetameta(idiomaTrad);
    		}
    	}
    	return lomBasTrad;
	}







}