package es.pode.catalogadorWeb.presentacion;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.pode.catalogacion.negocio.servicios.AvClassificationVO;
import es.pode.catalogacion.negocio.servicios.FuenteVO;
import es.pode.catalogacion.negocio.servicios.LangStringVO;
import es.pode.catalogacion.negocio.servicios.RutaTaxonomicaVO;
import es.pode.catalogadorWeb.presentacion.categoriasAvanzado.clasificacion.detalleClasificacion.DetalleClasificacionControllerImpl;
import es.pode.fuentestaxonomicas.negocio.servicio.EstructuraVdexVO;
import es.pode.fuentestaxonomicas.negocio.servicio.JerarquiaVO;
import es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO;
import es.pode.fuentestaxonomicas.negocio.servicio.TerminoVO;
import es.pode.fuentestaxonomicas.negocio.servicio.TerminoYPadreVO;
import es.pode.fuentestaxonomicas.negocio.servicio.VocabularioVO;
import es.pode.soporte.i18n.I18n;

public class ValidarTaxo {
	
	private static final String CAV_10_2 = "CAV.10.2";
	private static final String CAV_10_3 = "CAV.10.3";
	private static final String CAV_10_3_1 = "CAV.10.3.1";
	private static final String CAV_10_1_1 = "CAV.10.1.1";
	private static final String CAV_10_1 = "CAV.10.1";
	private static final String CAV_10_4 = "CAV.10.4";
	private static final String NUEVE = "9";
	private static final String CAV_10 = "CAV.10";
	private static final String CAV_10_4_1 = "CAV.10.4.1";
	private  Logger logger = Logger.getLogger(this.getClass());
	/**
	 * Método constructor para poder crear un objeto de la clase ValidaTaxo 
	 *
	 */
	public ValidarTaxo()
	{
		
	}
	
	
	/**
	 * Método para validar las taxonomías de un array con elementos de tipo AVClassificationVO.
	 *  
	 * @param clasi
	 * @param mensaje
	 * @return boolean valida: es correcto si todas las clasificaciones son correctas
	 */
	
	public boolean validaTaxo(AvClassificationVO[] clasi, Collection mensajes, ResourceBundle datosResources, String idioma){
		
		boolean valida = true;
		InputStream is = null;
		Properties prop = new Properties();
		boolean existeIdioma=true;
		String idiomaAux="es";
		
		is = this.getClass().getResourceAsStream("/catalogadorAvanzado.properties");
		
		try {
			
			prop.load(is);
			is.close();
			//obtenemos todas las taxonomías
			EstructuraVdexVO[] taxes = this.getSrvTaxonomiaService().obtenerTaxonomias(""); //idioma
			//obtenemos todos los tesauros
			EstructuraVdexVO[] vocabt = this.getSrvTesaurosServices().obtenerTesauros("");//idioma
			//obtener arbol taxonomías vigente
			EstructuraVdexVO taxVigente = this.getSrvTaxonomiaService().obtenerArbolVigente();
			//obtener arbol tesauros vigente
			EstructuraVdexVO tesVigente = this.getSrvTesaurosServices().obtenerTesauroVigente();
			
			//tratamos las rutas taxonómicas de cada Classification
			int i = 0;
			
			while ((i<clasi.length)){
				
				int i2 = i+1;
				RutaTaxonomicaVO[] rutax = clasi[i].getRutasTaxonomicas();
			
				//obtenemos el propósito
				String propo = clasi[i].getProposito().getValor();
				TerminoYPadreVO[] terp = {};
				String elProp = null;
				String propostra = null;
				String propostrad = null;
				TerminoVO[] trad = null;
				
				TerminoVO[] tvo = null;
				
				//obtenemos un array de VocabularioVO con todos los propósitos aceptados
				String[] propost1 = {"9.1"};
				VocabularioVO[] vo = this.getSrvVocabulariosControladosService().obtenerCombos(propost1, "en");
					
				tvo = vo[0].getTerminos();
				
				//y comprobamos si es la referencia o el nombre ya traducido
				//según se ejecute desde Validar Metadatos o Validar Clasificación
				if (propo.startsWith(NUEVE)){
					
					String[] propost = {propo};
					
					//para traducir el propósito al inglés
					terp = this.getSrvVocabulariosControladosService().crearTraduccionAIngles(propost);
					
					if ((terp!=null) && (terp.length>0)){
						
						//obtenemos el nombre del propósito que estamos tratando
						propostra = terp[0].getNomTermino();
						//para traducir el propósito al idioma actual
						trad = this.getSrvVocabulariosControladosService().crearTraduccionAIdioma(propost, idioma);
						propostrad = trad[0].getNomTermino();		
						
					}else{
						
						//no existe el propósito 
						
						if (propo.startsWith(NUEVE)){
							
							//comprobamos que no existe el mensaje en la lista de los errores
							if (!buscarMensaje(mensajes, datosResources.getString("CAV.10.5"))){
							
								mensajes.add(datosResources.getString("CAV.10.5"));
							}
						}else{
							
							//comprobamos que no existe el mensaje en la lista de los errores
							if (!buscarMensaje(mensajes, datosResources.getString(CAV_10)+i2+" - "+propo+": "+datosResources.getString("CAV.10.5"))){
							
								mensajes.add(datosResources.getString(CAV_10)+i2+" - "+propo+": "+datosResources.getString("CAV.10.5"));
							}
						}
					}
				}else{
					
					//ya teníamos el nombre del propósito
					propostra = propo;
					
					//lo obtenemos traducido al idioma actual
					int i1 = 0;
					boolean idt = false;
					while ((tvo.length>0) && (!idt) && (i1<tvo.length)){
						
						if (tvo[i1].getNomTermino().equals(propostra)){
							
							//obtengo el identificador del propósito
							String idt1 = tvo[i1].getIdTermino();
							
							String[] propost = {idt1};
							
							//para traducir el propósito al idioma actual
							trad = this.getSrvVocabulariosControladosService().crearTraduccionAIdioma(propost, idioma);
							propostrad = trad[0].getNomTermino();
							
							idt = true;
							
						}
						
						i1++;
					}
					
					if (propostrad==null) {
						propostrad="";
					}
							
					
				}
								
				//recorremos las rutas taxonómicas
				int j = 0;
				while ((j<rutax.length)){
					
					FuenteVO fuente = rutax[j].getFuente();
					LangStringVO[] lafuente = fuente.getTextos();
					//obtenemos la fuente con un string para compararla
					String fue = lafuente[0].getTexto();
					logger.info("ASC - LA FUENTE DENTRO DE RUTAS TAXONOMICAS " + fue);
					
					//comprobamos que la fuente está registrada en el properties
					String fuegui = fue.replace(" ", "_");
					String propn = prop.getProperty(fuegui);
					
					
					if (propn!=null){//si no es cierto, la fuente no estaría en el properties y la dejamos pasar

						//20141015 Se modifica para permitir múltiples propósitos para una fuente
						String[] listaPropFuente = propn.split(",");				

						boolean bPropCorrecto=false;
						for (int k = 0; k < listaPropFuente.length; k++) {
							if (listaPropFuente[k].equals(propostra))
								bPropCorrecto = true;
						}
						if (bPropCorrecto){
							
							//el propósito y la fuente coinciden
							//buscamos si la fuente es una taxonomía o un tesauro
							
							//para controlar el VocabName
							boolean encontrado = false;
							
							int d1 = 0;
							logger.info("ASC - EL PROPOSITO NO ES NUEVO ");
							//Iteramos sobre las taxonomías para buscar el VocabName, es decir, la fuente
							while((d1<taxes.length) && (!encontrado)){
								//comparamos la fuente con los VocabNames
								if (fue.equals(taxes[d1].getVocabName())){
									
									encontrado = true;
									
									//La fuente se ha encontrado como taxonomía, el propósito concuerda y buscamos 
									//comparar identificadores, para lo cual, obtenemos los taxones de la ruta de entrada
									es.pode.catalogacion.negocio.servicios.TaxonVO[] taxnu = rutax[j].getTaxones();
									String taxid = taxnu[taxnu.length-1].getId().getTexto();
									
									//comprobamos la existencia del idioma
									existeIdioma = this.getSrvTaxonomiaService().chequearIdiomaTaxonomia(fue, idioma);
									//hallamos el TaxonPath del último taxón para recorrerlo
									//ERROR si no es correcto el identificador del último taxón 
									if (!existeIdioma) {
										//si no existe la taxonomia en el idioma correspondiente" buscamos un idioma que exista
										logger.debug("TAXONOMIA con idioma no encontrado en plataforma");
										String[] idiomasEstructuras= I18n.getInstance().obtenerIdiomasBuscables();
										if ((idiomasEstructuras!=null)&&(idiomasEstructuras.length>0)){
											for (int contIdi=0; (contIdi<idiomasEstructuras.length)&&(!existeIdioma); contIdi++){
												existeIdioma = this.getSrvTaxonomiaService().chequearIdiomaTaxonomia(fue, idiomasEstructuras[contIdi]);
												idioma= idiomasEstructuras[contIdi];
											}
											if (existeIdioma){
												
												idiomaAux=idioma;
											}
											else{ // si no existe el idioma, añadimos error
						
												mensajes.add(datosResources.getString(CAV_10_4));
											}
										}
										
									} else {
										idiomaAux=idioma;
									}
									
									if (existeIdioma){
										TaxonVO[] taxoi = this.getSrvTaxonomiaService().obtenerTaxonPath(
												taxid, fue, idiomaAux);
										
										//ahora le damos la vuelta a los valores del array
										TaxonVO[] taxoio = new TaxonVO[taxoi.length];//array ordenado
										for (int v=0; v<taxoi.length; v++){
											int indice = (taxoi.length)-v;
											TaxonVO tpr = taxoi[indice-1];
											taxoio[v]= tpr;					
										}
										
										//Comprobamos que existen los identificadores en nuestras fuentes taxonómicas
										int r=0;
										if ((taxoio!=null) && (taxoio.length>0)){
											
											//obtenemos el índice del último taxón a tratar
											int ult = taxoio.length - 1; 
											
											boolean taok = true;
											//recorremos los taxones comparando los identificadores
											while((r<=ult) && (taok)){
											
												//obtenemos cada identificador del taxonpath
												taxid = taxnu[r].getId().getTexto();
												
												//y lo comparamos
												if(!taxid.equals(taxoio[r].getId())){
													
													//hemos encontrado un error
													taok = false;
													valida = false;
													
													if (propo.startsWith(NUEVE)){
														
														//comprobamos que no existe el mensaje en la lista de los errores
														if (!buscarMensaje(mensajes, datosResources.getString(CAV_10_2)+"\""+taxid+"\" "+datosResources.getString(CAV_10_3_1)+" "+fue+".")){
															
															mensajes.add(datosResources.getString(CAV_10_2)+"\""+taxid+"\" "+datosResources.getString(CAV_10_3_1)+" "+fue+".");
														}
													}else{
														
														//comprobamos que no existe el mensaje en la lista de los errores
														if (!buscarMensaje(mensajes, datosResources.getString(CAV_10)+i2+" - "+propostrad+": "+datosResources.getString(CAV_10_2)+"\""+taxid+"\" "+datosResources.getString(CAV_10_3_1)+" "+fue+".")){
															
															mensajes.add(datosResources.getString(CAV_10)+i2+" - "+propostrad+": "+datosResources.getString(CAV_10_2)+"\""+taxid+"\" "+datosResources.getString(CAV_10_3_1)+" "+fue+".");
														}
													}
												}	
												
												//seguimos recorriendo taxones
												r++;
											}
										}else{
											
											//array de taxones incorrecto
											valida = false;
											
											if (propo.startsWith(NUEVE)){
												
												//comprobamos que no existe el mensaje en la lista de los errores
												if (!buscarMensaje(mensajes, datosResources.getString(CAV_10_3)+"\""+taxid+"\" "+datosResources.getString(CAV_10_3_1)+" "+fue+".")){
												
													mensajes.add(datosResources.getString(CAV_10_3)+"\""+taxid+"\" "+datosResources.getString(CAV_10_3_1)+" "+fue+".");
												}
											}else{
												
												//comprobamos que no existe el mensaje en la lista de los errores
												if (!buscarMensaje(mensajes, datosResources.getString(CAV_10)+i2+" - "+propostrad+": "+datosResources.getString(CAV_10_3)+"\""+taxid+"\" "+datosResources.getString(CAV_10_3_1)+" "+fue+".")){
												
													mensajes.add(datosResources.getString(CAV_10)+i2+" - "+propostrad+": "+datosResources.getString(CAV_10_3)+"\""+taxid+"\" "+datosResources.getString(CAV_10_3_1)+" "+fue+".");
												}
											}
										}
									}
									
								}				
								//seguimos recorriendo taxonomías para encontrar la fuente
								d1++;
							}
							
							//no está registrado como taxonomía y lo buscamos como tesauro
							if (!encontrado){
								
								//recorremos las rutas taxonómicas
								int k = 0;
								while ((k<vocabt.length) && (!encontrado)){
									
									elProp=propostra;
									logger.info(" ASC - TRATAM TESAUROS FUENTE " + fue + " vocabt[k].getVocabName() " + vocabt[k].getVocabName() + " elProp " +elProp );
									//comprobamos el vocabName
									if (fue.equals(vocabt[k].getVocabName())){
										
										encontrado = true;
										
										logger.info("ASC - ELPROP ES " + elProp);
												
										//el propósito es correcto, comprobamos los identificadores
										//obteniendo primero los taxones de la ruta
										es.pode.catalogacion.negocio.servicios.TaxonVO[] taxnu = rutax[j].getTaxones();
										//el identificador del último taxón a tratar
										String taxid = taxnu[taxnu.length-1].getId().getTexto();
																					
										JerarquiaVO[] je = null;
										existeIdioma = this.getSrvTesaurosServices().chequearIdiomaTesauro(fue, idioma);
										if (!existeIdioma) {
//											si no existe el tesauro en el idioma correspondiente buscamos un idioma que exista
											String[] idiomasEstructuras= I18n.getInstance().obtenerIdiomasBuscables();
											if ((idiomasEstructuras!=null)&&(idiomasEstructuras.length>0)){
												for (int contIdi=0; (contIdi<idiomasEstructuras.length)&&(!existeIdioma); contIdi++){
													existeIdioma = this.getSrvTesaurosServices().chequearIdiomaTesauro(fue, idiomasEstructuras[contIdi]);
													idioma= idiomasEstructuras[contIdi];
												}
												if (existeIdioma){
													idiomaAux=idioma;
												}
												else{ // si no existe el idioma, añadimos error
							
													mensajes.add(datosResources.getString(CAV_10_4));
												}
											}
	
										} else {
											idiomaAux=idioma;
										}
										
										if (existeIdioma){
											je = this.getSrvTesaurosServices().obtenerJerarquia(taxid, fue, idiomaAux);
											
											if ((je!=null)&&(je.length>0)){
													
												//recorremos las jerarquías obtenidas
												int ts = 0;
												boolean jerar = false;
												while ((ts<je.length) && (!jerar)){
													
												TaxonVO[] taxte = je[ts].getJerarquia();
														
													//si los tamaños coinciden, comprobamos los elementos
													if (taxte.length == taxnu.length){
														
														int c = 0;
														boolean cadenaok = true;
														while ((c<taxte.length) && (cadenaok)){
															
															//comparamos los identificadores de los taxones
															//con el identificador hallado en la jerarquía
															if (!(taxte[c].getId().equals(taxnu[c].getId().getTexto()))){
																		
																//hemos encontrado un error 
																//se actualiza el valor de salida
																
																cadenaok = false;
															}
																
															//seguimos probando identificadoes de tesauros
															c++;
														}
															
														//hemos comprobado toda la ruta
														if (cadenaok){
															jerar = true;
														}
													}
														
													//seguimos recorriendo jerarquias
													ts++;
												}
												//se han comprobado todas las jerarquías y ninguna coincide 
												if (!jerar){
													
													valida = false;
													
													if (propo.startsWith(NUEVE)){
															//comprobamos que no existe el mensaje en la lista de los errores
														if (!buscarMensaje(mensajes, datosResources.getString(CAV_10_4_1)+"\""+fue+"\" "+datosResources.getString("CAV.10.4.2"))){
														
															mensajes.add(datosResources.getString(CAV_10_4_1)+"\""+fue+"\" "+datosResources.getString("CAV.10.4.2"));
														}
													}else{
														
														//comprobamos que no existe el mensaje en la lista de los errores
														if (!buscarMensaje(mensajes, datosResources.getString(CAV_10)+i2+" - "+propostrad+": "+datosResources.getString(CAV_10_4_1)+"\""+fue+"\" "+datosResources.getString("CAV.10.4.2"))){
														
															mensajes.add(datosResources.getString(CAV_10)+i2+" - "+propostrad+": "+datosResources.getString(CAV_10_4_1)+"\""+fue+"\" "+datosResources.getString("CAV.10.4.2"));
														}
													}
												}
											}else{
												//jerarquia correcta ?
												valida = false;
														
												if (propo.startsWith(NUEVE)){

													//comprobamos que no existe el mensaje en la lista de los errores
													if (!buscarMensaje(mensajes, datosResources.getString(CAV_10_4))){
													
														mensajes.add(datosResources.getString(CAV_10_4));
													}
												}else{
													
													//comprobamos que no existe el mensaje en la lista de los errores
													if (!buscarMensaje(mensajes, datosResources.getString(CAV_10)+i2+" - "+propostrad+": "+datosResources.getString(CAV_10_4))){
													
														mensajes.add(datosResources.getString(CAV_10)+i2+" - "+propostrad+": "+datosResources.getString(CAV_10_4));
													}
												}
											}
										}
									}
									
									//seguimos recorriendo tesauros 
									k++;
								}
								
								//no se ha encontrado como tesauro ni como taxonomía
//								if (!encontrado){
//									
//									valida = false;
//									
//									if (propo.startsWith(NUEVE)){
//
//										//comprobamos que no existe el mensaje en la lista de los errores
//										if (!buscarMensaje(mensajes, datosResources.getString(CAV_10_1)+"\""+propostrad+"\""+
//												" "+datosResources.getString(CAV_10_1_1)+"\""+fue+"\".")){
//										
//											mensajes.add(datosResources.getString(CAV_10_1)+"\""+propostrad+"\""+
//													" "+datosResources.getString(CAV_10_1_1)+"\""+fue+"\".");										
//										}
//									}else{
//										
//										//comprobamos que no existe el mensaje en la lista de los errores
//										if (!buscarMensaje(mensajes, datosResources.getString(CAV_10)+i2+" - "+propostrad+": "+datosResources.getString(CAV_10_1)+"\""+propostra+"\""+
//												" "+datosResources.getString(CAV_10_1_1)+"\""+fue+"\".")){
//										
//											mensajes.add(datosResources.getString(CAV_10)+i2+" - "+propostrad+": "+datosResources.getString(CAV_10_1)+"\""+propostra+"\""+
//													" "+datosResources.getString(CAV_10_1_1)+"\""+fue+"\".");										
//										}
//									}
//								}
							}
						}else{
							
							//la fuente y el propósito asignado en el properties no coinciden
							valida = false;
							if ((!propo.equals("")) && (!propostrad.equals(""))) { //si estan "" no muestra errores 
								if (propo.startsWith(NUEVE)){
	
									//comprobamos que no existe el mensaje en la lista de los errores
									if (!buscarMensaje(mensajes, datosResources.getString(CAV_10_1)+"\""+propostrad+"\""+
											" "+datosResources.getString(CAV_10_1_1)+"\""+fue+"\".")){
									
										mensajes.add(datosResources.getString(CAV_10_1)+"\""+propostrad+"\""+
												" "+datosResources.getString(CAV_10_1_1)+"\""+fue+"\".");										
									}
								}else{
									
									//comprobamos que no existe el mensaje en la lista de los errores
									if (!buscarMensaje(mensajes, datosResources.getString(CAV_10)+i2+" - "+propostrad+": "+datosResources.getString(CAV_10_1)+"\""+propostra+"\""+
											" "+datosResources.getString(CAV_10_1_1)+"\""+fue+"\".")){
									
										mensajes.add(datosResources.getString(CAV_10)+i2+" - "+propostrad+": "+datosResources.getString(CAV_10_1)+"\""+propostra+"\""+
												" "+datosResources.getString(CAV_10_1_1)+"\""+fue+"\".");										
									}
								}//fin else
							}//fin if comprobacion ""
						}//fin else
					}else{
						
						String voctax = "";
						String voctes = "";
						
						if (taxVigente!= null){
							
							voctax = taxVigente.getVocabName();
						}
						
						if (tesVigente!= null){
							
							voctes = tesVigente.getVocabName();
						}

						logger.info("JABF: Los VocabName del árbol vigente son: Tax: "+ voctax + "; Tes: "+ voctes );
						logger.info("JABF: La fuente es: "+ fue + " y el propósito a comparar es : "+propostra);
						if(!propostra.equals(prop.getProperty("Árbol_curricular_LOE_2006")) &&
								((fue.equals(voctax)) || (fue.equals(voctes)))){
						
//							la fuente y el propósito asignado en el properties no coinciden
							valida = false;
						
							if (propo.startsWith(NUEVE)){

								//comprobamos que no existe el mensaje en la lista de los errores
								if (!buscarMensaje(mensajes, datosResources.getString(CAV_10_1)+"\""+propostrad+"\""+
										" "+datosResources.getString(CAV_10_1_1)+"\""+fue+"\".")){
									
									mensajes.add(datosResources.getString(CAV_10_1)+"\""+propostrad+"\""+
											" "+datosResources.getString(CAV_10_1_1)+"\""+fue+"\".");										
								}
							}else{
							
								//comprobamos que no existe el mensaje en la lista de los errores
								if (!buscarMensaje(mensajes, datosResources.getString(CAV_10)+i2+" - "+propostrad+": "+datosResources.getString(CAV_10_1)+"\""+propostra+"\""+
										" "+datosResources.getString(CAV_10_1_1)+"\""+fue+"\".")){
							
									mensajes.add(datosResources.getString(CAV_10)+i2+" - "+propostrad+": "+datosResources.getString(CAV_10_1)+"\""+propostra+"\""+
											" "+datosResources.getString(CAV_10_1_1)+"\""+fue+"\".");										
								}
							}
						}	
					}
					//seguimos recorriendo rutas taxonómicas
					j++;
				}
			
			
				//seguimos recorriendo classifications
				i++;
			}
		}catch (IOException e){
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
		
		//devolvemos el valor obtenido
		return valida;
	}

	 protected final es.pode.fuentestaxonomicas.negocio.servicio.SrvTaxonomiaService getSrvTaxonomiaService()
     throws java.lang.Exception
 {
     String srvTaxonomiaServiceFile="importedServices.properties";	    
     java.io.InputStream srvTaxonomiaServiceInputStream=DetalleClasificacionControllerImpl.class.getClassLoader().getResourceAsStream(srvTaxonomiaServiceFile);
     java.util.Properties srvTaxonomiaServiceProperties = new java.util.Properties();
     srvTaxonomiaServiceProperties.load(srvTaxonomiaServiceInputStream);
     String srvTaxonomiaServiceEndPointAddress="";
     srvTaxonomiaServiceEndPointAddress=(String) srvTaxonomiaServiceProperties.get("srvTaxonomiaServicePort");
     logger.debug("srvTaxonomiaServiceEndPointAddress del fichero --> " + srvTaxonomiaServiceEndPointAddress);
		es.pode.fuentestaxonomicas.negocio.servicio.SrvTaxonomiaServiceService srvTaxonomiaService = new es.pode.fuentestaxonomicas.negocio.servicio.SrvTaxonomiaServiceServiceLocator();                                                                                                                                                                                                                                                    
     if (srvTaxonomiaServiceEndPointAddress.length()>0) 
			((es.pode.fuentestaxonomicas.negocio.servicio.SrvTaxonomiaServiceServiceLocator)srvTaxonomiaService).setSrvTaxonomiaServiceEndpointAddress(srvTaxonomiaServiceEndPointAddress);				
 	es.pode.fuentestaxonomicas.negocio.servicio.SrvTaxonomiaService port = srvTaxonomiaService.getSrvTaxonomiaService();	    
     return port;
 }

	 protected final es.pode.fuentestaxonomicas.negocio.servicio.SrvTesaurosServices getSrvTesaurosServices()
     throws java.lang.Exception
 {
     String srvTesaurosServicesFile="importedServices.properties";	    
     java.io.InputStream srvTesaurosServicesInputStream=DetalleClasificacionControllerImpl.class.getClassLoader().getResourceAsStream(srvTesaurosServicesFile);
     java.util.Properties srvTesaurosServicesProperties = new java.util.Properties();
     srvTesaurosServicesProperties.load(srvTesaurosServicesInputStream);
     String srvTesaurosServicesEndPointAddress="";
     srvTesaurosServicesEndPointAddress=(String) srvTesaurosServicesProperties.get("srvTesaurosServicesPort");
     logger.debug("srvTesaurosServicesEndPointAddress del fichero --> " + srvTesaurosServicesEndPointAddress);
		es.pode.fuentestaxonomicas.negocio.servicio.SrvTesaurosServicesService srvTesaurosServices = new es.pode.fuentestaxonomicas.negocio.servicio.SrvTesaurosServicesServiceLocator();                                                                                                                                                                                                                                                    
     if (srvTesaurosServicesEndPointAddress.length()>0) 
			((es.pode.fuentestaxonomicas.negocio.servicio.SrvTesaurosServicesServiceLocator)srvTesaurosServices).setSrvTesaurosServicesEndpointAddress(srvTesaurosServicesEndPointAddress);				
 	es.pode.fuentestaxonomicas.negocio.servicio.SrvTesaurosServices port = srvTesaurosServices.getSrvTesaurosServices();	    
     return port;
 }
	 
	 protected final es.pode.fuentestaxonomicas.negocio.servicio.SrvVocabulariosControladosService getSrvVocabulariosControladosService()
     throws java.lang.Exception
 {
     String srvVocabulariosControladosServiceFile="importedServices.properties";	    
     java.io.InputStream srvVocabulariosControladosServiceInputStream=DetalleClasificacionControllerImpl.class.getClassLoader().getResourceAsStream(srvVocabulariosControladosServiceFile);
     java.util.Properties srvVocabulariosControladosServiceProperties = new java.util.Properties();
     srvVocabulariosControladosServiceProperties.load(srvVocabulariosControladosServiceInputStream);
     String srvVocabulariosControladosServiceEndPointAddress="";
     srvVocabulariosControladosServiceEndPointAddress=(String) srvVocabulariosControladosServiceProperties.get("srvVocabulariosControladosServicePort");
     logger.debug("srvVocabulariosControladosServiceEndPointAddress del fichero --> " + srvVocabulariosControladosServiceEndPointAddress);
		es.pode.fuentestaxonomicas.negocio.servicio.SrvVocabulariosControladosServiceService srvVocabulariosControladosService = new es.pode.fuentestaxonomicas.negocio.servicio.SrvVocabulariosControladosServiceServiceLocator();                                                                                                                                                                                                                                                    
     if (srvVocabulariosControladosServiceEndPointAddress.length()>0) 
			((es.pode.fuentestaxonomicas.negocio.servicio.SrvVocabulariosControladosServiceServiceLocator)srvVocabulariosControladosService).setSrvVocabulariosControladosServiceEndpointAddress(srvVocabulariosControladosServiceEndPointAddress);				
 	es.pode.fuentestaxonomicas.negocio.servicio.SrvVocabulariosControladosService port = srvVocabulariosControladosService.getSrvVocabulariosControladosService();	    
     return port;
 }
	 
	 /**
		 * Buscar mensaje: Método para buscar un mensaje de error dentro de la colección de errores
		 *
		 * @param errores:lista de objetos ErrorParseoVO donde se van insertando los errores
		 * @param mensaje:string con el mensaje a comprobar
		 * 
		 * @return contiene: falso si no está el mensaje dentro de la lista de mensajes de error
		 */
		
		private boolean buscarMensaje(Collection mensajes, String mensaje){
			
			boolean contiene = false;
			boolean encontrado = false;
			//ErrorParseoVO error = new ErrorParseoVO();
			
			Iterator it = mensajes.iterator();
			
			while ((it.hasNext()) && (!encontrado)){
				
				if (it.next().toString().equals(mensaje)){
					encontrado = true;
					contiene = true;
				}
			}
			
			return contiene;
			
		}	 
}