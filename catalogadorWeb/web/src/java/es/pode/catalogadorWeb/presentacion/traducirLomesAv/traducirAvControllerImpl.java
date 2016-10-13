/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.catalogadorWeb.presentacion.traducirLomesAv;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.catalogacion.negocio.servicios.AmbitoVO;
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
import es.pode.catalogacion.negocio.servicios.EntryVO;
import es.pode.catalogacion.negocio.servicios.FechaVO;
import es.pode.catalogacion.negocio.servicios.FuenteVO;
import es.pode.catalogacion.negocio.servicios.IdVO;
import es.pode.catalogacion.negocio.servicios.LangStringVO;
import es.pode.catalogacion.negocio.servicios.LomAvanzadoVO;
import es.pode.catalogacion.negocio.servicios.PalabraClaveVO;
import es.pode.catalogacion.negocio.servicios.RangoEdadVO;
import es.pode.catalogacion.negocio.servicios.RutaTaxonomicaVO;
import es.pode.catalogacion.negocio.servicios.TaxonVO;
import es.pode.catalogacion.soporte.Traducir;
import es.pode.catalogacion.soporte.UtilidadesOrdenarCombos;
import es.pode.catalogadorWeb.presentacion.CatalogadorAvSession;
import es.pode.fuentestaxonomicas.negocio.servicio.EstructuraVdexVO;
import es.pode.fuentestaxonomicas.negocio.servicio.JerarquiaVO;
import es.pode.fuentestaxonomicas.negocio.servicio.TerminoVO;
import es.pode.fuentestaxonomicas.negocio.servicio.TerminoYPadreVO;
import es.pode.fuentestaxonomicas.negocio.servicio.VocabularioVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.i18n.I18n;



/**
 * @see es.pode.catalogadorWeb.presentacion.traducirLomesAv.traducirAvController
 */
public class traducirAvControllerImpl extends traducirAvController
{
 
	protected static Logger logger = Logger.getLogger(traducirAvControllerImpl.class);
	private static final String TODOS="1";	
	private static final String NINGUNO="2";
	private static final String COINCIDENTES="3";

    /**
     * @see es.pode.catalogadorWeb.presentacion.traducirLomesAv.traducirAvController#validarIdiomasCat(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.traducirLomesAv.ValidarIdiomasCatForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
  



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

///////////////////////
	private boolean comprobarIdiomaCatalogacion(LomAvanzadoVO lomAvanzadovo, String idiomaCatalogacion) throws Exception {
		boolean resultado= true;
		//recorremos la estructura de catalogacion, comprobando que todos los idiomas coincidan con el idioma de catalogacion
		//si encontramos algun idioma que no coincide con el de catalogacion resultado --> false
		try{	
			//Recorremos categoria General
			if (lomAvanzadovo.getGeneral()!=null) {
				int indiGen =0;
				int indiAux=0;
				AvGeneralVO general = lomAvanzadovo.getGeneral();
				//General - Titulos
				if (general.getTitulo().getTextos()!=null) {
					LangStringVO[] titulos = general.getTitulo().getTextos();
									
					while ((indiGen<titulos.length) && (resultado)) {
						if ((titulos[indiGen].getIdioma()!=null) && (!titulos[indiGen].getIdioma().equals("")) && !titulos[indiGen].getIdioma().equals(idiomaCatalogacion)) {
							//no coinciden entonces resultado es false
							resultado=false;					
						}
						indiGen++;
					}
				}//fin titulos
				//General - Idiomas
//				if ((resultado) && (general.getIdiomas()!=null)) {
//					indiGen=0;//inicializamos el indice
//					IdiomaVO[] idiomas = general.getIdiomas();
//					while ((indiGen<idiomas.length) && (resultado)) {
//						if ((idiomas[indiGen].getTexto()!=null) && (!idiomas[indiGen].getTexto().equals("")) && (!idiomas[indiGen].getTexto().equals(idiomaCatalogacion))) {
//							//no coinciden, resultado es false
//							resultado=false;
//						}
//						indiGen++;
//					}
//				}//fin idiomas
				//General - Descripcion
				if ((resultado) && (general.getDescripciones()!=null)) {
					DescripcionVO[] descripciones=general.getDescripciones();
					indiGen=0;
					while ((indiGen<descripciones.length) && (resultado)){
						if (descripciones[indiGen].getTextos()!=null) {
							LangStringVO[] contDesc=descripciones[indiGen].getTextos();
							indiAux=0;
							while ((indiAux<contDesc.length) && (resultado)) {
								if ((contDesc[indiAux].getIdioma()!=null) && (!contDesc[indiAux].getIdioma().equals("")) && (!contDesc[indiAux].getIdioma().equals(idiomaCatalogacion))) {
									//no coinciden, resultado es false
									resultado=false;
								}
								indiAux++;
							}
						}
						indiGen++;
					}
				}//fin descripcion
				//General - Palabras Clave
				if ((resultado) && (general.getPalabrasClave()!=null)) {
					PalabraClaveVO[] palclave = general.getPalabrasClave();
					indiGen=0;
					while ((indiGen<palclave.length) && (resultado)) {
						if (palclave[indiGen].getTextos()!=null) {
							LangStringVO[] contPalclave = palclave[indiGen].getTextos();
							indiAux=0;
							while ((indiAux<contPalclave.length) && (resultado)) {
								if ((contPalclave[indiAux].getIdioma()!=null) && (!contPalclave[indiAux].getIdioma().equals("")) && (!contPalclave[indiAux].getIdioma().equals(idiomaCatalogacion))) {
									//no coinciden los resultados
									resultado=false;
								}
								indiAux++;
							}
						}
						indiGen++;
					}
				}//fin Palabras Clave
				//General - Ambito
				if ((resultado) && (general.getAmbitos()!=null)) {
					AmbitoVO[] ambitos = general.getAmbitos();
					indiGen=0;
					while ((indiGen<ambitos.length) && (resultado)) {
						if (ambitos[indiGen].getTextos()!=null) {
							LangStringVO[] contAmbitos = ambitos[indiGen].getTextos();
							indiAux=0;
							while ((indiAux<contAmbitos.length) && (resultado)) {
								if ((contAmbitos[indiAux].getIdioma()!=null) && (!contAmbitos[indiAux].getIdioma().equals("")) && (!contAmbitos[indiAux].getIdioma().equals(idiomaCatalogacion))) {
									resultado=false; //no coinciden								
								}
								indiAux++;
							}
						}
						indiGen++;
					}
				}//fin ambitos
				//Fin Categoria General
				
				//Recorremos categoria Ciclo de Vida
				if ((resultado) && (lomAvanzadovo.getLifeCycle()!=null)) {
					AvLifeCycleVO lifecyclevo=lomAvanzadovo.getLifeCycle();
					int indicv=0;
					int indicvAux=0;
					//Ciclo de vida - Version
					if ((resultado) && (lifecyclevo.getVersion()!=null)){
						if (lifecyclevo.getVersion().getTextos()!= null) {
							LangStringVO[] langVersion=lifecyclevo.getVersion().getTextos();
							if (langVersion!=null) {
								while ((indicv<langVersion.length) && (resultado)){
									if ((langVersion[indicv].getIdioma()!=null) && (!langVersion[indicv].getIdioma().equals("")) && (!langVersion[indicv].getIdioma().equals(idiomaCatalogacion))) {
										resultado=false; //no coinciden los idiomas
									}
									indicv++;
								}
							}
						}
					}//fin Version
					// Ciclo de vida - Contribuciones - Comprobamos las descripciones
					if ((resultado) && (lifecyclevo.getContribuciones()!=null)) {
						ContribucionVO[] contribuciones = lifecyclevo.getContribuciones();
						if (contribuciones!=null){
							indicv=0;
							while ((indicv<contribuciones.length) && (resultado)) {
								if (contribuciones[indicv].getFecha()!=null) {
								   FechaVO fechavo = contribuciones[indicv].getFecha();
								   if ((fechavo.getDescripcion() !=null) && (fechavo.getDescripcion().getTextos()!=null)) {
									   LangStringVO[] descText = fechavo.getDescripcion().getTextos();
									   if (descText!=null) {
										   indicvAux=0;
										   while ((indicvAux<descText.length) && (resultado)) {
											   if ((descText[indicvAux].getIdioma()!=null) && (!descText[indicvAux].getIdioma().equals("")) && (!descText[indicvAux].getIdioma().equals(idiomaCatalogacion))) {
												   resultado=false; //no coinciden los idiomas
											   }
											   indicvAux++;
										   }
									   }//fin if descTex
								   }						   
								}
								indicv++;
							}
						}
					}//fin Contribucion				
					
				}
				//Fin Categoria Ciclo de Vida
				
				//Recorremos Categoria MetaMetadatos
				if ((resultado) &&(lomAvanzadovo.getMetaMetadata()!=null)) {
					AvMetametadataVO metameta=lomAvanzadovo.getMetaMetadata();
					int indimt=0;
					int indimtAux=0;
					//Metametadatos - Contribuciones
					if ((resultado) && (metameta.getContribuciones()!=null)) {
						ContribucionVO[] contribuciones = metameta.getContribuciones();
						if (contribuciones!= null){
							indimt=0;
							while ((indimt<contribuciones.length) && (resultado)) {
								if (contribuciones[indimt].getFecha()!=null) {
								   FechaVO fechavo = contribuciones[indimt].getFecha();
								   if ((fechavo!=null) && (fechavo.getDescripcion() !=null) && (fechavo.getDescripcion().getTextos()!=null)) {
									   LangStringVO[] descText = fechavo.getDescripcion().getTextos();
									   if (descText != null) {
										   indimtAux=0;
										   while ((indimtAux<descText.length) && (resultado)) {
											   if ((descText[indimtAux].getIdioma()!=null) && (!descText[indimtAux].getIdioma().equals("")) && (!descText[indimtAux].getIdioma().equals(idiomaCatalogacion))) {
												   resultado=false; //no coinciden los idiomas
											   }
											   indimtAux++;
										   }//fin while
									   }
								   }						   
								}
								indimt++;
							}//fin while
						}
					}//fin Contribucion
					
				}//Fin Categoria MetaMetadatos
				
				//Recorremos Categoria Tecnica 
				if ((resultado) &&(lomAvanzadovo.getTechnical()!=null)){
					AvTechnicalVO tecnica=lomAvanzadovo.getTechnical();
					int indit=0;
					//Tecnica - Pautas de instalacion
					if ((resultado) &&(tecnica.getPautasInstalacion()!=null) && (tecnica.getPautasInstalacion().getTextos()!=null)){
						LangStringVO[] textos = tecnica.getPautasInstalacion().getTextos();
						if (textos!=null) {
							while ((indit<textos.length) && (resultado)) {
								if ((textos[indit].getIdioma()!=null) && (!textos[indit].getIdioma().equals("")) && (!textos[indit].getIdioma().equals(idiomaCatalogacion))) {
									resultado=false; //no coinciden los idiomas
								}
								indit++;
							}
						}
					}//fin pautas instalacion
					//Tecnica - Otros requisitos de la plataforma
					if ((resultado)&&(tecnica.getOtrosRequisitos()!=null) && (tecnica.getOtrosRequisitos().getTextos()!=null)) {
						LangStringVO[] textoOtrosReq = tecnica.getOtrosRequisitos().getTextos();
						if (textoOtrosReq != null){
							indit=0;
							while ((indit<textoOtrosReq.length) && (resultado)) {
								if ((textoOtrosReq[indit].getIdioma()!=null) && (!textoOtrosReq[indit].getIdioma().equals("")) && (!textoOtrosReq[indit].getIdioma().equals(idiomaCatalogacion))) {
									resultado=false; //no coinciden los idiomas
								}
								indit++;
							}
						}
					}//fin otros Requisitos
					//Tecnica - Duracion
					if ((resultado) && (tecnica.getDuracion()!=null) && (tecnica.getDuracion().getDescripcion()!=null) && 
						(tecnica.getDuracion().getDescripcion().getTextos()!=null)) {
						LangStringVO[] textoDesc=tecnica.getDuracion().getDescripcion().getTextos();
						if (textoDesc!= null){
							indit=0;
							while ((indit<textoDesc.length) && (resultado)) {
								if ((textoDesc[indit].getIdioma()!=null) && (!textoDesc[indit].getIdioma().equals("")) && (!textoDesc[indit].getIdioma().equals(idiomaCatalogacion))) {
									resultado=false; //no coinciden los idiomas
								}
								indit++;
							}
						}
					}//fin Duracion
				}//Fin Tecnica
				//Categoria Uso Educativo
				if ((resultado) && (lomAvanzadovo.getEducational()!=null)) {
					AvEducationalVO[] educational=lomAvanzadovo.getEducational();
					int indie=0;
					int indieAux=0;
					int indieAux2=0;
					while ((indie<educational.length) && (resultado)) {
						
						// Uso educativo - Descripciones
						if ((resultado) && (educational[indie].getDescripciones()!=null)){
							DescripcionVO[] descrDest=educational[indie].getDescripciones();
							if (descrDest!=null){
								indieAux = 0;
								while ((indieAux<descrDest.length) && (resultado)) {
									if (descrDest[indieAux].getTextos()!=null) {
										LangStringVO[] langText = descrDest[indieAux].getTextos();
										if (langText!=null){
											indieAux2 = 0;
											while ((indieAux2<langText.length) && (resultado)) {
												if ((langText[indieAux2].getIdioma()!=null) && (!langText[indieAux2].getIdioma().equals("")) && (!langText[indieAux2].getIdioma().equals(idiomaCatalogacion))) {
													resultado=false;//no coinciden los idiomas
												}
												indieAux2++;
											}
										}
									}
									indieAux++;
								}
							}
						}//fin descripciones
						// Uso educativo - edad del destinatario
						if ((resultado) && (educational[indie].getRangoedades()!=null)){
							RangoEdadVO[] edadDest=educational[indie].getRangoedades();
							if (edadDest!=null){	
								indieAux=0;
								while ((indieAux< edadDest.length) && (resultado)) {
									if (edadDest[indieAux].getTextos()!=null){
										LangStringVO[] langEdad= edadDest[indieAux].getTextos();
										if (langEdad!=null){
											indieAux2=0;
											while ((indieAux2<langEdad.length) && (resultado)) {
												if ((langEdad[indieAux2].getIdioma()!=null) && (!langEdad[indieAux2].getIdioma().equals("")) && (!langEdad[indieAux2].getIdioma().equals(idiomaCatalogacion))) {
													resultado=false; //no coinciden los idiomas
												}
												indieAux2++;
											}
										}
									}
									indieAux++;
								}
							}
						}//fin edad destinatario
						//Uso educativo - Tiempo de Aprendizaje - Descripcion
						if ((resultado) &&(educational[indie].getTiempoAprendizaje()!=null) && (educational[indie].getTiempoAprendizaje().getDescripcion()!=null) 
							&& (educational[indie].getTiempoAprendizaje().getDescripcion().getTextos()!=null)) {
							LangStringVO[]langTiempo=educational[indie].getTiempoAprendizaje().getDescripcion().getTextos();
							indieAux=0;
							while ((indieAux<langTiempo.length) && (resultado)){
								if ((langTiempo[indieAux].getIdioma()!=null) && (!langTiempo[indieAux].getIdioma().equals("")) && (!langTiempo[indieAux].getIdioma().equals(idiomaCatalogacion))) {
									resultado=false; //no coinciden los idiomas
								}
								indieAux++;
							}
						}//fin descripcion tiempo aprendizaje
						
						indie++;
					}
				}// fin categoria Uso Educativo
				
				//Categoria Derechos
				if ((resultado) &&(lomAvanzadovo.getDerechos()!=null)){
					int indid=0;
					AvRightsVO derechos = lomAvanzadovo.getDerechos();
					if ((derechos.getDescripcion() !=null) && (derechos.getDescripcion().getTextos()!=null)){
						LangStringVO[] langDerecho=derechos.getDescripcion().getTextos();
						while ((indid<langDerecho.length) && (resultado)){
							if ((langDerecho[indid].getIdioma() != null) && (!langDerecho[indid].getIdioma().equals("")) && (!langDerecho[indid].getIdioma().equals(idiomaCatalogacion))){
								resultado=false; //no coinciden los idiomas
							}
							indid++;
						}
					}
				}//fin categoria Derechos
				
				//Categoria Relacion
				if ((resultado) && (lomAvanzadovo.getRelaciones()!=null)){
					int indir=0;
					AvRelationVO[] relaciones = lomAvanzadovo.getRelaciones();
					while ((indir<relaciones.length) && (resultado)){
						if ((relaciones[indir].getRecurso()!=null) && (relaciones[indir].getRecurso().getDescripciones()!=null)){
							DescripcionVO[] descRel = relaciones[indir].getRecurso().getDescripciones();
							int indidr=0;
							while ((indidr<descRel.length) && (resultado)){
								if (descRel[indidr].getTextos()!=null){
									LangStringVO[] langDescRel= descRel[indidr].getTextos();
									int indidrAux=0;
									while ((indidrAux<langDescRel.length) && (resultado)){
										if ((langDescRel[indidrAux].getIdioma()!=null) && (!langDescRel[indidrAux].getIdioma().equals("")) && (!langDescRel[indidrAux].getIdioma().equals(idiomaCatalogacion))){
											resultado=false;//no coinciden los idiomas
										}
										indidrAux++;
									}
								}
								indidr++;
							}
						}
						indir++;
					}
				}// fin categoria Relacion
				//Categoria Anotacion
				if ((resultado) &&(lomAvanzadovo.getAnotaciones()!=null)){
					AvAnnotationVO[] anotaciones = lomAvanzadovo.getAnotaciones();
					int indian=0;
					int indianD=0;
					while ((indian<anotaciones.length) && (resultado)){
						//Anotacion - Descripciones
						if ((anotaciones[indian].getDescripcion()!=null) && (anotaciones[indian].getDescripcion().getTextos()!=null)){						
							LangStringVO[] langAnotaDesc = anotaciones[indian].getDescripcion().getTextos();						
							indianD = 0;
							while ((indianD<langAnotaDesc.length) && (resultado)){
								if ((langAnotaDesc[indianD].getIdioma()!=null) && (!langAnotaDesc[indianD].getIdioma().equals("")) && (!langAnotaDesc[indianD].getIdioma().equals(idiomaCatalogacion))){
									resultado=false; //no coinciden los idiomas								
								}
								indianD++;
							}
						}//fin Descripciones
						//Anotacion - Fecha - Descripcion
						if ((resultado) && (anotaciones[indian].getFecha()!=null) && (anotaciones[indian].getFecha().getDescripcion()!=null) 
							&& (anotaciones[indian].getFecha().getDescripcion().getTextos()!=null)) {
							LangStringVO[] langFechaDesc = anotaciones[indian].getFecha().getDescripcion().getTextos();
							indianD=0;
							while ((indianD<langFechaDesc.length) && (resultado)) {
								if ((langFechaDesc[indianD].getIdioma()!=null) && (!langFechaDesc[indianD].getIdioma().equals("")) && (!langFechaDesc[indianD].getIdioma().equals(idiomaCatalogacion))){
									resultado=false; //no coinciden los idiomas
								}
								indianD++;
							}
						}//fin Fecha-Descripcion					
						indian++;
					}
				}//fin Categoria Anotacion
				//Categoria Clasificacion
				if ((resultado) && (lomAvanzadovo.getClasificaciones()!=null)){
					AvClassificationVO[] clasificacion=lomAvanzadovo.getClasificaciones();
					int indicl=0;
					while ((indicl<clasificacion.length) && (resultado)){
						if ((clasificacion[indicl].getRutasTaxonomicas()!=null)){
							int indiclAux=0;
							int indiclAux2=0;
							RutaTaxonomicaVO[] rutas=clasificacion[indicl].getRutasTaxonomicas();
							while ((indiclAux<rutas.length) && (resultado)){
								//fuente
								if ((rutas[indiclAux].getFuente()!=null) && (rutas[indiclAux].getFuente().getTextos()!=null)){
									indiclAux2=0;
									LangStringVO[] langclFu = rutas[indiclAux].getFuente().getTextos();
									while ((indiclAux2<langclFu.length) && (resultado)){
										if ((langclFu[indiclAux2].getIdioma()!=null) && (!langclFu[indiclAux2].getIdioma().equals("")) && (!langclFu[indiclAux2].getIdioma().equals(idiomaCatalogacion))){
											resultado=false; //no coinciden los idiomas
										}
										indiclAux2++;
									}
								}//fin fuente
								//taxones
								if ((resultado) &&(rutas[indiclAux].getTaxones()!=null)) {
									TaxonVO[] taxones = rutas[indiclAux].getTaxones();
									indiclAux2=0;
									while ((indiclAux2<taxones.length) && (resultado)) {
										if ((taxones[indiclAux2].getEntry()!=null) && (taxones[indiclAux2].getEntry().getTextos()!=null)){
											int indiclAux3=0;
											LangStringVO [] langtax=taxones[indiclAux2].getEntry().getTextos();
											while ((indiclAux3<langtax.length) && (resultado)){
												if ((langtax[indiclAux3].getIdioma()!=null) && (!langtax[indiclAux3].getIdioma().equals("")) && (!langtax[indiclAux3].getIdioma().equals(idiomaCatalogacion))){
													resultado=false; //no coinciden los idiomas
												}
												indiclAux3++;
											}
										}
										indiclAux2++;
									}
								}
								indiclAux++;
							}
						}
						//Descripcion de la clasificacion
						if((resultado) && (clasificacion[indicl].getDescripcion() != null) && clasificacion[indicl].getDescripcion().getTextos() != null){
							LangStringVO[] descripciones = clasificacion[indicl].getDescripcion().getTextos();
							int indice = 0;
							while ((indice < descripciones.length) && (resultado)) {
								if ((descripciones[indice].getIdioma()!=null) && (!descripciones[indice].getIdioma().equals("")) && (!descripciones[indice].getIdioma().equals(idiomaCatalogacion))){
									resultado=false; //no coinciden los idiomas
								}
								indice++;
							}
						}//fin descripcion de la clasificacion
						
						//Palabra Clave
						if((resultado) && (clasificacion[indicl].getPalabrasClave() != null)){
							PalabraClaveVO[] palCla = clasificacion[indicl].getPalabrasClave();
							int indipal = 0;
							while((indipal < palCla.length) && (resultado)){
								if(palCla[indipal].getTextos() != null){
									LangStringVO[] descripciones = palCla[indipal].getTextos();
									int indice = 0;
									while ((indice < descripciones.length) && (resultado)) {
										if ((descripciones[indice].getIdioma()!=null) && (!descripciones[indice].getIdioma().equals("")) && (!descripciones[indice].getIdioma().equals(idiomaCatalogacion))){
											resultado=false; //no coinciden los idiomas
										}
										indice++;
									}
								}
							}
						}//fin palabra clave
						indicl++;
					}
				}//fin clasificacion
							
			}
		} catch (Exception e) {
			//Si se produce alguna excepcion dev false
			resultado=false;
			throw new Exception("Error comprobando idioma de Catalogacion " + e);
		}
		return resultado;
	}

    /**
     * @see es.pode.catalogadorWeb.presentacion.traducirLomesAv.traducirAvController#tipoTraduccion(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.traducirLomesAv.TipoTraduccionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String tipoTraduccion(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.traducirLomesAv.TipoTraduccionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        // this property receives a default value, just to have the application running on dummy data
    	String idiomaSeleccionado = request.getParameter("idiomaSeleccionado");
    	
    	String accion= UtilidadesOrdenarCombos.obtenerAccion(request);
    	//Collection termDestOrd=form.getDatosIdiomas();
    	String resultado="";
    	
    	//recupero los datos de la colección
    	Object valor = request.getSession().getAttribute("form");
    	if (valor instanceof TraducirLomesAvFormImpl) {
    		TraducirLomesAvFormImpl datosForm = (TraducirLomesAvFormImpl) valor;
			form.setDatosIdiomas(datosForm.getDatosIdiomas());
			form.setDatosTraduccion(datosForm.getDatosTraduccion());
			form.setIdiomaSeleccionado(idiomaSeleccionado);
    	} else if (valor instanceof IdiomasTipoTraduccionFormImpl){
    		IdiomasTipoTraduccionFormImpl datosForm = (IdiomasTipoTraduccionFormImpl)valor;
    		form.setDatosIdiomas(datosForm.getDatosIdiomas());
    		form.setDatosTraduccion(datosForm.getDatosTraduccion());
    		form.setIdiomaSeleccionado(datosForm.getIdiomaSeleccionado());
    	}
    	//ahora tengo que ver el tipo de idioma de catalogación que tiene el ode
    	CatalogadorAvSession catalogadorAvSession = this.getCatalogadorAvSession(request);
    	if (catalogadorAvSession!=null){
    		if (catalogadorAvSession.getMDSesion()==null) {
    			catalogadorAvSession.setMDSesion(new LomAvanzadoVO());
    		}
    		ResourceBundle datosResources = I18n.getInstance().getResource("application-resources", (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
    		//Le damos a cancelar
    		if ((accion!=null) && accion.equals(datosResources.getString("catalogadorAvanzado.Cancelar"))) {
    			resultado="Cancelar";
    		}else if ((!form.getDatosTraduccion().isEmpty()) && (form.getTipoTraduccion()!=null) && (!form.getTipoTraduccion().equals(""))){
    			//Refinar pero para q funcione en principio...
    			if (form.getTipoTraduccion().equals(TODOS)){
    				resultado="Todos";
    			}else if (form.getTipoTraduccion().equals(NINGUNO)){
    				resultado="Ninguno";
    			}else if (form.getTipoTraduccion().equals(COINCIDENTES)){
    				resultado="Coincidentes";
    			}
    		} else {
	    		String idiomaCatalogacion= catalogadorAvSession.getIdioma();//((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage().toString();
	    		boolean tieneIdiomaCat = comprobarIdiomaCatalogacion(catalogadorAvSession.getMDSesion(),idiomaCatalogacion );
	    		//dependiendo de si el resultado es true o false, haremos la bifurcacion hacia un metodo de controller o a otro...
	    		//y tb dependiendo de si el dato de traduccion viene vacio o no.
	    		    		
	    		if (tieneIdiomaCat) {
	    			resultado = "Todos";
	    		} else{
	    			if ((form.getTipoTraduccion()==null) || (form.getTipoTraduccion().equals(""))){
	    				resultado = "Recargar";
	    				//////////////////////////////
	    				////// Inicializamos los valores de las posibles traducciones
	    				String idiomaNav = ((Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).toString();
	    				Traducir[] trtrad =cargaRadiosTipoTraduccion(datosResources, idiomaCatalogacion, idiomaNav);	    					    				
	    				form.setDatosTraduccionAsArray(trtrad);
	    				
	    			}else {
	    				resultado = "Coincidentes";
	    			}
	    		}
    		}
    	}
        form.setTipoTraduccion(resultado);
        form.setMensajeFin("");
        //quitar esto!!
        //form.setIdiomaSeleccionado(idiomaSeleccionado);
         return resultado;
    }
    //Cargamos los datos para el tipo de traduccion
    private Traducir[] cargaRadiosTipoTraduccion(ResourceBundle datosResources, String idiomaCatalogacion, String idiomaNav) throws Exception{
		
    	String idiomaTra = I18n.getInstance().obtenerIdiomaTraducido(idiomaCatalogacion, idiomaNav);
		
    	ArrayList arrTrad = new ArrayList();
		Traducir trad = new Traducir();
		trad.setIdentificador(TODOS);
		trad.setNombre(datosResources.getString("tipo.traduccion.todo"));    				
		arrTrad.add(trad);
		trad=new Traducir();
		trad.setIdentificador(NINGUNO);
		trad.setNombre(datosResources.getString("tipo.traduccion.nada"));
		arrTrad.add(trad);
		trad=new Traducir();
		trad.setIdentificador(COINCIDENTES);
		trad.setNombre(datosResources.getString("tipo.traduccion.coincidentes") + " \"" + idiomaTra.substring(0, 1).toUpperCase() + idiomaTra.substring(1) + "\"");
		arrTrad.add(trad);
		Traducir[] trtrad = (Traducir[]) arrTrad.toArray(new Traducir[arrTrad.size()]);
    	
    	return trtrad;
    }





    /**
     * @see es.pode.catalogadorWeb.presentacion.traducirLomesAv.traducirAvController#traducirTodo(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.traducirLomesAv.TraducirTodoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void traducirTodo(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.traducirLomesAv.TraducirTodoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {    	
    	String idiomaCatalogacion= "";
		String idiomaTrad = "";
		LomAvanzadoVO lomAvanzado=null;
		LomAvanzadoVO lomAv = null;
		CatalogadorAvSession catalogadoravSession = this.getCatalogadorAvSession(request);
		ResourceBundle datosResources = I18n.getInstance().getResource("application-resources", (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
    	try {        	
	    	if (catalogadoravSession!=null){
	    		if (catalogadoravSession.getMDSesion()==null){
	    			catalogadoravSession.setMDSesion(new LomAvanzadoVO());
	    		}
	    		//realizamos la traduccion
	    		//Cambiar este idioma!!!!!
	    		idiomaCatalogacion= catalogadoravSession.getIdioma();//((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).toString();
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
	    		   try{
	    			   	lomAv=this.getSrvCatalogacionAvanzadaService().traducirLomAvanzado(catalogadoravSession.getIdentificador(), 
	    			   			catalogadoravSession.getUsuario(), idiomaTrad, catalogadoravSession.getMDSesion(), idiomaCatalogacion, false);
	    			   	lomAvanzado = this.cambiarTaxonomias(lomAv, idiomaCatalogacion, idiomaTrad, false);
	    			   	if(lomAvanzado == null){ //Si no se han podido traducir las taxonomias, se dejan sin traducir
	    			   		lomAvanzado = lomAv;
	    			   	}
	    		   } catch (Exception e) {
					lomAvanzado=null;
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
    	if (idiomaCatalogacion.equals(idiomaTrad)){
		   //aviso de q el dioma de origen y el de traduccion no pueden ser el mismo
		   throw new ValidatorException("{error.traduccion.idiomaCoincidente}"); 
		}
    	if(lomAvanzado != null){
    		catalogadoravSession.setMDSesion(lomAvanzado);
    	}
		   
    }







    /**
     * @see es.pode.catalogadorWeb.presentacion.traducirLomesAv.traducirAvController#cargarDatos(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.traducirLomesAv.CargarDatosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void cargarDatos(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.traducirLomesAv.CargarDatosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
      
        InputStream is =null;
        boolean coinciden = true;
    	
        try {
        	is = this.getClass().getResourceAsStream("/catalogadorAvanzado.properties");
        	Properties prop = new Properties();
        	prop.load(is);
       
        	CatalogadorAvSession catalogadoravSession = this.getCatalogadorAvSession(request);
	    	if (catalogadoravSession!=null){
	    		//Metemos el idioma de catalogacion en sesion
	    		String idiomaCat = catalogadoravSession.getMDSesion().getMetaMetadata().getIdioma().getTexto();
				catalogadoravSession.setIdioma(idiomaCat);
				
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
	        	TerminoVO[] termDestOrd=terminosOrd.ordenarTerminosVO(terminos, idiomaNav);
	        	for(int k = 0; k < termDestOrd.length; k++){
	        		String idioma = termDestOrd[k].getNomTermino();
	        		termDestOrd[k].setNomTermino(idioma.substring(0, 1).toUpperCase() + idioma.substring(1));
	        	}
	        	//Collection destOrd= Arrays.asList(termDestOrd);
	        	form.setDatosIdiomasAsArray(termDestOrd);
	        	coinciden = comprobarIdiomaCatalogacion(catalogadoravSession.getMDSesion(), idiomaCat);
	        	if(coinciden){
	        		form.setDatosTraduccionAsArray(new Traducir[0]);
	        	}
	        	else{
	        		ResourceBundle datosResources = I18n.getInstance().getResource("application-resources", (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
    				Traducir[] trtrad =cargaRadiosTipoTraduccion(datosResources, idiomaCat, idiomaNav);	    					    				
    				form.setDatosTraduccionAsArray(trtrad);
	        	}
	        	is.close();
	    	}
        	
        }catch (Exception e) {
			is.close();
			logger.error("Se ha producido un Error al Cargar los Datos " + e);
		}  
     }







    /**
     * @see es.pode.catalogadorWeb.presentacion.traducirLomesAv.traducirAvController#traducirCoincidentes(org.apache.struts.action.ActionMapping, es.pode.catalogadorWeb.presentacion.traducirLomesAv.TraducirCoincidentesForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void traducirCoincidentes(ActionMapping mapping, es.pode.catalogadorWeb.presentacion.traducirLomesAv.TraducirCoincidentesForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        // this property receives a default value, just to have the application running on dummy data
    	logger.debug("VEMOS Q ENTRA");
    	String idiomaCatalogacion= "";
		String idiomaTrad = "";
		LomAvanzadoVO lomAvanzado=null;
		LomAvanzadoVO lomAv = null;
		CatalogadorAvSession catalogadoravSession = this.getCatalogadorAvSession(request);
		ResourceBundle datosResources = I18n.getInstance().getResource("application-resources", (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
    	try {
	    	if (catalogadoravSession!=null){
	    		if (catalogadoravSession.getMDSesion()==null){
	    			catalogadoravSession.setMDSesion(new LomAvanzadoVO());
	    		}
	    		//realizamos la traduccion
	    		//Cambiar este idioma!!!!!
	    		idiomaCatalogacion= catalogadoravSession.getIdioma();//((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).toString();
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
	    		   try{
	    			   lomAv=this.getSrvCatalogacionAvanzadaService().traducirLomAvanzado(catalogadoravSession.getIdentificador(), 
			    				catalogadoravSession.getUsuario(), idiomaTrad, catalogadoravSession.getMDSesion(), idiomaCatalogacion, true);
	    			   lomAvanzado = this.cambiarTaxonomias(lomAv, idiomaCatalogacion, idiomaTrad, true);
	    			   if(lomAvanzado == null){
	    				   lomAvanzado = lomAv;
	    			   }
	    		   }catch (Exception e) {
	    			   lomAvanzado=null;
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
    	if (idiomaCatalogacion.equals(idiomaTrad)){
 		   //aviso de q el dioma de origen y el de traduccion no pueden ser el mismo
 		   throw new ValidatorException("{error.traduccion.idiomaCoincidente}");
 		}
    	if(lomAvanzado != null){
    		catalogadoravSession.setMDSesion(lomAvanzado);
    	}
    }

	@Override
	public void volverInicio(ActionMapping mapping, VolverInicioForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("Vamos a volver");
		String idiomaNavegacion=((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
		CatalogadorAvSession catalogadoravSession = this.getCatalogadorAvSession(request);
    	if (catalogadoravSession!=null){
    		if (catalogadoravSession.getMDSesion()==null){
    			catalogadoravSession.setMDSesion(new LomAvanzadoVO());
    		}
    		catalogadoravSession.setIdioma(idiomaNavegacion);//la ponemos bien otra vez
    	}
		
	}


	private LomAvanzadoVO cambiarTaxonomias(LomAvanzadoVO lomAv, String idiomaOri, String idiomaTrad, boolean coincidentes) throws Exception{
    	LomAvanzadoVO lomAvTrad = null;
    	try {
			if(lomAv != null){
				lomAvTrad = new LomAvanzadoVO();
				if(lomAv.getClasificaciones() != null){
					//Obtenemos las rutas taxonomicas de cada clasificacion del lom
					AvClassificationVO[] clasif = lomAv.getClasificaciones();
					ArrayList arrClasif = new ArrayList();
					AvClassificationVO cadaClasif = null;
					for(int i = 0; i < clasif.length; i++){//Cada clasificacion
						cadaClasif = new AvClassificationVO();
						if(clasif[i].getRutasTaxonomicas() != null){
							RutaTaxonomicaVO[] rutas = clasif[i].getRutasTaxonomicas();
							ArrayList arrRutas = new ArrayList();
							for(int j = 0; j < rutas.length; j++){//Cada ruta taxonomica
								if(rutas[j].getFuente() != null){
									FuenteVO fuente = rutas[j].getFuente();
									String nomFuente = "";
									String idiom = "";
									if(fuente.getTextos() != null && fuente.getTextos().length > 0){
										nomFuente = fuente.getTextos()[0].getTexto();
										idiom = fuente.getTextos()[0].getIdioma();
									}
									boolean traducir = false;
									if(coincidentes){
										if(idiom == null || idiom.equals("") || idiom.equals(idiomaOri))
											traducir = true;
										else
											traducir = false;
									}
									else
										traducir = true;
									
									//Obtenemos las taxonomias que hay disponibles en el idioma de traduccion
					    			EstructuraVdexVO[] estrVdex = this.getSrvTaxonomiaService().obtenerTaxonomias(idiomaTrad);
					    			
									if(traducir){
										//Comprobamos que la taxonomia del lom esta disponible en el idioma de traduccion
										boolean tax = false;
										boolean tes = false;
										for(int z = 0; z < estrVdex.length; z++){
											if(estrVdex[z].getVocabName().equals(nomFuente)){
													tax = true;
													break;
											}
										}
										if(!tax){
											//Si no lo encontramos en taxonomias, probamos en tesauros
											estrVdex = this.getSrvTesaurosServices().obtenerTesauros(idiomaTrad);
											for(int z = 0; z < estrVdex.length; z++){
												if(estrVdex[z].getVocabName().equals(nomFuente)){
														tes = true;
														break;
												}
											}
										}
										//Cargo los que esten disponibles
										es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[] taxonPath = null;
										
										//Si es taxonomia
										TaxonVO[] taxones = rutas[j].getTaxones();
										if(tax && !tes){
											if(taxones != null && taxones.length > 0 && taxones[taxones.length - 1].getId() != null){
												es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[] taxonPathRev = this.getSrvTaxonomiaService().obtenerTaxonPath(taxones[taxones.length - 1].getId().getTexto(), nomFuente, idiomaTrad);
												taxonPath = new es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[taxonPathRev.length];
												//Invierto taxonPathRev
												for(int n = 0; n < taxonPathRev.length; n++){
													taxonPath[n] = taxonPathRev[taxonPathRev.length - n - 1];
												}
											}
										}
										//Si es tesauro
										if(!tax && tes){
											if(taxones != null && taxones.length > 0 && taxones[taxones.length - 1].getId() != null){
												JerarquiaVO[] jerarquia = this.getSrvTesaurosServices().obtenerJerarquia(taxones[taxones.length - 1].getId().getTexto(), nomFuente, idiomaTrad);
												if(jerarquia != null){
													for(int k = 0; k < jerarquia.length; k++){ 
			    										taxonPath = jerarquia[k].getJerarquia();
													}
												}
											}
										}
										if(taxonPath != null){
											//Formo los TaxonVO de catalogacion
											ArrayList arrTaxon = new ArrayList();
											for(int z = 0; z < taxonPath.length; z++){
												TaxonVO unTaxon = new TaxonVO();
												//ID
												unTaxon.setId(new IdVO(taxonPath[z].getId()));
												//ENTRY
												ArrayList arrLang= new ArrayList();
												LangStringVO langStringvo = new LangStringVO();
												langStringvo.setIdioma(idiomaTrad);
												langStringvo.setTexto(taxonPath[z].getValorTax());
												arrLang.add(langStringvo);
												LangStringVO[] lang = (LangStringVO[])arrLang.toArray(new LangStringVO[arrLang.size()]);
												unTaxon.setEntry(new EntryVO(lang));
												arrTaxon.add(unTaxon);
											}
											//Meto los taxones en un array
											TaxonVO[] arrTaxones = (TaxonVO[])arrTaxon.toArray(new TaxonVO[arrTaxon.size()]);
											//Formo la ruta taxonomica
											RutaTaxonomicaVO rutaTax = new RutaTaxonomicaVO();
											//TAXONES
											rutaTax.setTaxones(arrTaxones);
											//FUENTE
											ArrayList arrLang= new ArrayList();
											LangStringVO langStringvo = new LangStringVO();
											langStringvo.setIdioma(idiomaTrad);
											langStringvo.setTexto(nomFuente);
											arrLang.add(langStringvo);
											LangStringVO[] lang = (LangStringVO[])arrLang.toArray(new LangStringVO[arrLang.size()]);
											rutaTax.setFuente(new FuenteVO(lang));
											arrRutas.add(rutaTax);
										}
										if(!tax && !tes){
											arrRutas.add(rutas[j]);
										}
									}//fin traducir
									else{
										arrRutas.add(rutas[j]);
									}
								}
							}//fin cada ruta taxonomica
							RutaTaxonomicaVO[] arrRutasTaxon = (RutaTaxonomicaVO[])arrRutas.toArray(new RutaTaxonomicaVO[arrRutas.size()]);
							cadaClasif.setRutasTaxonomicas(arrRutasTaxon);
						}
						//Resto de campos de Clasificacion
						cadaClasif.setDescripcion(lomAv.getClasificaciones()[i].getDescripcion());
						cadaClasif.setPalabrasClave(lomAv.getClasificaciones()[i].getPalabrasClave());
						cadaClasif.setProposito(lomAv.getClasificaciones()[i].getProposito());
						arrClasif.add(cadaClasif);
					}
					AvClassificationVO[] clasificaciones = (AvClassificationVO[])arrClasif.toArray(new AvClassificationVO[arrClasif.size()]);
					lomAvTrad.setClasificaciones(clasificaciones);
				}///Fin Clasificaciones
				
				//Copiamos el resto de campos del lom
				lomAvTrad.setGeneral(lomAv.getGeneral());
				lomAvTrad.setLifeCycle(lomAv.getLifeCycle());
				lomAvTrad.setMetaMetadata(lomAv.getMetaMetadata());
				lomAvTrad.setTechnical(lomAv.getTechnical());
				lomAvTrad.setEducational(lomAv.getEducational());
				lomAvTrad.setDerechos(lomAv.getDerechos());
				lomAvTrad.setRelaciones(lomAv.getRelaciones());
				lomAvTrad.setAnotaciones(lomAv.getAnotaciones());
    		}
		} catch (Exception e) {
			logger.error("Error en la traduccion de taxonomias " + e);
			throw e;
		}

    	return lomAvTrad;
    }

}