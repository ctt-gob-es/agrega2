/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.validador.negocio.servicio;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.mail.internet.MimeUtility;

import org.apache.log4j.Logger;
import org.apache.xerces.parsers.DOMParser;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.pode.fuentestaxonomicas.negocio.servicio.EstructuraVdexVO;
import es.pode.fuentestaxonomicas.negocio.servicio.JerarquiaVO;
import es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO;
import es.pode.parseadorXML.ParseadorException;
import es.pode.parseadorXML.SCORM2004Dao;
import es.pode.parseadorXML.castor.Identifier;
import es.pode.parseadorXML.castor.Lom;
import es.pode.parseadorXML.castor.Manifest;
import es.pode.parseadorXML.lomes.lomesAgrega.ClassificationAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.LangStringAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.LomAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.RelacionAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.RutaTaxonomicaAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.TaxonAgrega;
import es.pode.parseadorXML.scorm2004.agrega.ManifestAgrega;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.validador.NamespaceYEsquema;
import es.pode.soporte.validador.TipoOde;
import es.pode.validador.negocio.dominio.ManifestEntityDaoImpl;
import es.pode.validador.negocio.soporte.XMLErrorHandler;


/**
 * 
 * @see es.pode.validador.negocio.servicio.SrvValidadorService
 */

public class SrvValidadorServiceImpl
    extends es.pode.validador.negocio.servicio.SrvValidadorServiceBase
{
	
	private static  Logger logger = Logger.getLogger(SrvValidadorServiceImpl.class);
	  
    /**
	 * Validación Binaria: Este tipo de Validación chequea que el fichero esté bien formado, que todas sus etiquetas sean correctas,
	 * que contenga los datos requeridos por los esquemas establecidos y los contenidos a los que se hace referencia
	 * estén en su ruta correcta 
	 * 
	 * @param rutaManifest
	 * 		 ruta donde se encuentra el fichero a validar
	 * @return ValidaVO. Contiene el resultado de la validación; este objeto esta compuesto de resultadoValidación, 
	 * esValidoManifest y la rutaManifest
	 */
	
	 protected es.pode.validador.negocio.servicio.ValidaVO handleObtenerValidacionBin(java.lang.String rutaManifest)
     throws java.lang.Exception {
		 
		 SCORM2004Dao dao = this.getScormDao(); 
		 
		String imsManifest = new StringBuffer(rutaManifest).append("/imsmanifest.xml").toString();
		//cambiamos las barras de directorio
		imsManifest = imsManifest.replace( '\\', '/');
		File fOde= new File(imsManifest);
		ValidaVO valida= new ValidaVO();
		ManifestEntityDaoImpl maniEntity = new ManifestEntityDaoImpl();
		maniEntity.setBaseDirectory(rutaManifest); 
		Boolean resultado =  true;
		Boolean resultadoMasLomes =  true;
		Boolean resultadoSimple =  true;
		Boolean resultadoEsquemas =  true;
		Boolean resultadoLomes =  true;
		Boolean resultadoContenido= true;
		Boolean resultadoMEC= true;

		InputStream is = null;
    	Properties prop = new Properties();
//	    	Necesitamos el idioma para saber en que idioma tenemos que hacer las traducciones
    	String idioma="es";
	   	if (LdapUserDetailsUtils.estaAutenticado()) { 
	   		idioma = LdapUserDetailsUtils.getIdioma();
	   		if(idioma==null ||idioma.equals("")){
	   			idioma=I18n.getInstance().obtenerIdiomaDefectoPlataforma();
	   		}
	   	}else{
	   		idioma=I18n.getInstance().obtenerIdiomaDefectoPlataforma();
	   	}

    	
		String nombreFich="/erroresValidacion_"+idioma+".properties";
		is = this.getClass().getResourceAsStream(nombreFich);
		prop.load(is);
    	
    	StringBuilder resultadoValidacion = new StringBuilder();
    	List<ErrorParseoVO> errores = null;
		if (fOde.exists())
		{			
			try {
				
				NamespaceYEsquema nye= new NamespaceYEsquema();
				TipoOde tipo= new TipoOde();
				tipo.obtieneNameSpaceYEsquema (imsManifest, nye);
				
				if (nye.getNamespace()==null){
					valida.setEsValidoManifest(false);
					errores=insertarErrorParseo(errores,prop.getProperty("8.52"),0,0);
				}
				else{
					if (("").equals(nye.getNamespace())){
						valida.setEsValidoManifest(false);
						errores=insertarErrorParseo(errores,prop.getProperty("8.48"),0,0);
					}
					else{
//					PARSEO SIMPLE parseo sin esquemas (ademas elimina metadatos que no sean LOM-ES)
					resultadoSimple = maniEntity.obtenerParseoSimple(imsManifest,"CA");			
		    		errores = maniEntity.getErroresParseo();//ERRORES GENERERADOS EN EL PARSEO SIMPLE. El resto de errores se iran añadiendo a este array	
		    		if(maniEntity.getDocument()!=null){	//El document es null cuando falla parseoSimple y es imposible 
		    											//montar el Document para pasarlo al metodo esNodoCorrecto
		    			
			    		//PARSEO DE TODOS LOS LOMES Se parsean por separado todos los lomes que contenga el manifest
						//se quitan del mDocument todos los lomes y se guardan en una array para parsearlos por separado
						//y hacer el parseo con esquema del manifest sin lomes, solo SCORM	
		    			boolean soloLocation = false;//Esto implica que va recuperar tantos los lomes referenciados mediante location como
													//como los lomes que tenga el manifest
			    		maniEntity.buscarLomes(maniEntity.getDocument(),soloLocation);
			    		
			    		//si hemos obtenido errores de más de un lom
			    		if (!maniEntity.getLomesBien()){
			    			
			    			errores=insertarErrorParseo(errores,prop.getProperty("4.9"),0,0);
			    			//al tener más de un lom ya no podemos validar el manifest
			    			resultadoMasLomes = false;
			    		}else{
			    			//Se parsea los lomes obtenidos anteriormente
			    			//resultadoLomes = maniEntity.validarTodosLomes();
			    			//PARSEO DE MANIFEST SIN LOMES Se parsea el manifest libre metadatos
			    			resultadoEsquemas = maniEntity.obtenerParseoConEsquemasSinLomes(imsManifest,"CA");
			    			
			    			//tratamos los MEC
			    			Manifest manifest = dao.parsearODELazy(new File(imsManifest));//cambio de rutaManifest a imsManifest
			    			ManifestAgrega manifestAgrega = new ManifestAgrega(manifest);
			    			Lom[] obj = manifestAgrega.recuperarLomesAsArray();
			    			List<ErrorParseoVO> mens= new ArrayList<ErrorParseoVO>();
			    			
			    			if ((obj!=null)&& (obj.length>0)){
			    				
			    				Lom lomMeta = obj[0];
			    				String cat = "basico";
				    			String mensaje = mecBien(cat,lomMeta,idioma,mens);
//				    			
//				    			if (mensaje.equals("MECKO")){
//				    				//mostrar el mensaje "Encontrado MEC no válido"
//				    				errores=insertarErrorParseo(mens,prop.getProperty("CAV.11.2"),0,0);
//				    			}
				    			if ((cat.equals("avanzado")) && (mensaje==null) && (!buscarMensaje(mens, prop.getProperty("CAV.11.1"))) ){
				    				errores.addAll(mens);
				    			}
			    			}
			    			
			    			
			    			//CHEQUEO DE CONTENIDOS
			    			Boolean rootOK = maniEntity.esRootValido(rutaManifest);
			    			if (rootOK.booleanValue()) {
			    				maniEntity.crearListaFicherosManifest(imsManifest);					
			    				resultadoContenido = maniEntity.chequearContenido(maniEntity.getDocument(), ""); //Chequeo contenidos
			    			} else {
			    				// el root no es valido//Error: La etiqueta principal debe ser manifest o lom
			    				resultado = false;
			    				errores=insertarErrorParseo(errores,prop.getProperty("1.3"),0,0);
			    			}
			    		}

		    		}
		    		else{//Ha fallado el parseo simple por que el fichero imsmanifest no esta bien formado, 
		    			 //hay alguna etiqueta incorrecta,falta alguna etiqueta,etc...
		    			errores=insertarErrorParseo(errores,prop.getProperty("1.2"),0,0);
		    		}
		    		
					resultado = resultadoSimple&&resultadoEsquemas&&resultadoLomes&&resultadoMEC&&resultadoContenido&&resultadoMasLomes;
							
					if(!resultadoLomes.booleanValue()){//Error en alguno de los lomes del ODE
						errores=insertarErrorParseo(errores, prop.getProperty("1.5"),0,0);
					}
					if(!resultadoEsquemas.booleanValue()){//Error Scorm
						errores=insertarErrorParseo(errores, prop.getProperty("1.6"),0,0);
					}
						
					valida.setEsValidoManifest(resultado);
				}
				
				}
				
	 		} catch (Exception e) {
	 			
	 			valida.setEsValidoManifest(false);
	 			//	Error en la Estructura del imsmanifest
	 			logger.error("Error en el parseo - ",e);
	 			if(errores==null)
	 				errores = new ArrayList<ErrorParseoVO>();
	 			errores=insertarErrorParseo(errores,prop.getProperty("1.2"),0,0);
			}
		} else {//Si el fichero imsmanifest.xml del ode no existe en la raiz
			valida.setEsValidoManifest(false);
			//No Existe el ODE.
			errores = insertarErrorParseo(errores, prop.getProperty("1.1"),0,0);
		}
		
		//ERRORES GENERERADOS EN EL PARSEO SIMPLE Y AQUI
		ErrorParseoVO[] erroresPar=errores.toArray(new ErrorParseoVO[errores.size()]);
		
		for(int i=0;i<erroresPar.length;i++){
			String textoError=erroresPar[i].getMensaje();
			int posicion=textoError.indexOf("*");
			if(posicion>0){
				String inicioMensaje=textoError.substring(0, posicion-1);
				String finMensaje=textoError.substring(posicion+1);
				String traducido=prop.getProperty(inicioMensaje);
				textoError=traducido+finMensaje;
			}	
			int posi = resultadoValidacion.indexOf(textoError);
			//si posi es -1 no hemos añadido este error entonces lo añadimos, si posi es distinto 
			//de -1 ya hemos añadido este mismo texto por tanto no lo añadimos
			if (posi<0)
				resultadoValidacion.append(textoError + ";");
		}
		
		valida.setResultadoValidacion(resultadoValidacion.toString());
		rellenaErroresParseo(valida, maniEntity);

		return valida;
 }
	 
    /**
	 * Validación Ligera: Este tipo de Validación chequea que el fichero esté bien formado y que todas sus etiquetas sean correctas
	 * 
	 * @param rutaManifest Ruta donde se encuentra el fichero a validar
	 * @param tipoOde CA(contentAggregation) Si es obligatorio que tenga al menos una organización
	 * 				   RCP(ResourceContentPackage) No puede llevar ninguna organización
	 * @return ValidaVO. Contiene el resultado de la validación; este objeto esta compuesto de resultadoValidación, 
	 * esValidoManifest y la rutaManifest
	 */
	 
	 protected es.pode.validador.negocio.servicio.ValidaVO handleObtenervalidacionLigera(java.lang.String rutaManifest, String tipoOde)
	 throws java.lang.Exception{
				 
		   SCORM2004Dao dao = this.getScormDao();  
		 
		   ValidaVO validavo = new ValidaVO();
		   ManifestEntityDaoImpl manEntity = new ManifestEntityDaoImpl();
		   manEntity.setBaseDirectory(rutaManifest);
		   String imsManifest = new StringBuffer(rutaManifest).append("/imsmanifest.xml").toString();
		   //cambiamos las barras de directorio
		   imsManifest = imsManifest.replace( '\\', '/');
		   File fOde= new File(rutaManifest);
		   validavo.setRutaManifest(imsManifest);
		   
//		   Boolean manifestOk = manEntity.buscarManifest(rutaManifest);
		   Boolean resultado= true;
		   Boolean resultadoMasLomes = true;
		   Boolean resultadoSimple= true;
		   Boolean resultadoEsquemas = true;
		   Boolean resultadoLomes = true;
		   InputStream is = null;
		   Properties prop = new Properties();
		   //Necesitamos el idioma para saber en que idioma tenemos que hacer las traducciones
		   String idioma="es";
		   if (LdapUserDetailsUtils.estaAutenticado()) { 
			   idioma = LdapUserDetailsUtils.getIdioma();
			   if(idioma==null || idioma.equals("")){
				   idioma=I18n.getInstance().obtenerIdiomaDefectoPlataforma();
			   }
		   }else{
			   idioma=I18n.getInstance().obtenerIdiomaDefectoPlataforma();
		   }

	
		   String nombreFich="/erroresValidacion_"+idioma+".properties";
		   is = this.getClass().getResourceAsStream(nombreFich);
		   prop.load(is);	 
	
		   StringBuilder resultadoValidacion=new StringBuilder();
		   List<ErrorParseoVO> errores = new ArrayList<ErrorParseoVO>();//lo inicializamos para el trataLom
		   if (fOde.exists()) {

//				 logger.debug("AOV: Parseamos el fichero "+ imsManifest +" mediante castor(parseadorXML)");

				 File fRutaManifest = new File(imsManifest);
				 boolean parseoOK = true;
				 Manifest manifestFile = null;
				 if (fRutaManifest.exists()) {
					 try{
						 manifestFile = this.getScormDao().parsearODEEager(fRutaManifest);
//						 logger.debug("AOV: El parseo castor es correcto.");
					 }
					 catch(Exception e){
						 logger.error("El parseo castor ha fallado, el ode no es correcto - ",e);
						 parseoOK = false;
					 }
				 }
				 
			   try {
				   
				   NamespaceYEsquema nye= new NamespaceYEsquema();
				   TipoOde tipo= new TipoOde();
				   tipo.obtieneNameSpaceYEsquema (imsManifest, nye);
					
					if (nye.getNamespace()==null){
						resultado = false;
						validavo.setEsValidoManifest(resultado);
						errores=insertarErrorParseo(errores,prop.getProperty("8.52"),0,0);
					}
					else{
						if (("").equals(nye.getNamespace())){
							resultado = false;
							validavo.setEsValidoManifest(resultado);
							errores=insertarErrorParseo(errores,prop.getProperty("8.48"),0,0);
						}
						else{
//						PARSEO SIMPLE parseo sin esquemas (ademas elimina metadatos que no sean LOM-ES)
						   resultadoSimple = manEntity.obtenerParseoSimple(imsManifest,tipoOde);			
						   errores = manEntity.getErroresParseo();//ERRORES GENERERADOS EN EL PARSEO SIMPLE. El resto de errores se iran añadiendo a este array
					
						   if(manEntity.getDocument()!=null){//El document es null cuando falla parseoSimple y es imposible 
								//montar el Document para pasarlo al metodo esNodoCorrecto
							   if(parseoOK){					    
									//PARSEO DE LOS LOMES REFERENCIADOS MEDIANTE LOCATION Se parsean por separado todos los lomes 
									//se guardan en una array para parsearlos por separado
									boolean soloLocation = false;//true;//Esto implica que va recuperar solo los lomes referenciados mediante location
									manEntity.buscarLomes(manEntity.getDocument(),soloLocation);
									
									//si hemos obtenido errores de más de un lom
						    		if (!manEntity.getLomesBien()){
						    			
						    			errores=insertarErrorParseo(errores,prop.getProperty("4.9"),0,0);
						    			//al tener más de un lom ya no podemos validar el manifest
						    			resultadoMasLomes = false;
						    		}else{
						    			
						    				resultadoEsquemas = manEntity.obtenerParseoConEsquemasSinLomes(imsManifest,tipoOde);
							    			
							    			Manifest manifest = dao.parsearODELazy(new File(imsManifest));
							    			ManifestAgrega manifestAgrega = new ManifestAgrega(manifest);
							    				
							    			Lom[] obj = manifestAgrega.recuperarLomesAsArray();
							    			if ((obj!=null) && (obj.length>0)){
							    				//tratamos los MEC						    			
								    			Lom lomMeta = obj[0];
								    			List<ErrorParseoVO> mens = new ArrayList<ErrorParseoVO>();
								    			String cat = LdapUserDetailsUtils.getCatalogador();
								    			String mensaje = mecBien(cat,lomMeta,idioma, mens);
								    			if ((cat.equals("avanzado")) && (mensaje==null) && (!buscarMensaje(mens, prop.getProperty("CAV.11.1"))) ) {
								    				errores.addAll(mens);
								    			}
								    			trataLom("avanzado",obj, errores, prop, idioma);
							    			}						    			
						    		}
									//Se parsea los lomes obtenidos anteriormente
										//quitado temporalmente!!!!!
									
									//resultadoLomes = manEntity.validarTodosLomes();
									
									////PARSEO DEL MANIFEST (INCLUIDOS TODOS LOS LOMES) 
									//resultadoEsquemas = manEntity.obtenerParseoConEsquemas(imsManifest,tipoOde);
									
									//////////-------------------------- ATENCION!!!! --------------------------------
									// CAMBIADO POR PROBLEMAS AL IMPORTAR UN ODE CON LOMES INCOMPLETO (CREADO, GUARDADO, VALIDADO BINARIO OK PERO AL IMPORTAR ---->>>>)
									//// VALIDACION LIGERA ERROR PQ SOLO TIENE TITULO, CATALOGO Y METAMETADATA (LOMES-V1.0)
									
									/////////////// CAMBIO TEMPORAL!!!!
									
									
									
							   }else{ 
								   boolean soloLocation = true;
								   manEntity.buscarLomes(manEntity.getDocument(),soloLocation);
								   resultadoLomes = manEntity.validarTodosLomes();
								
								   // si hemos obtenido errores de más de un lom
						    		if (!manEntity.getLomesBien()){
						    			
						    			errores=insertarErrorParseo(errores,prop.getProperty("4.9"),0,0);
						    			//al tener más de un lom ya no podemos validar el manifest
						    			resultadoMasLomes = false;
						    		}else{
						    			////PARSEO DEL MANIFEST (INCLUIDOS TODOS LOS LOMES) 
						    			resultadoEsquemas = manEntity.obtenerParseoConEsquemas(imsManifest,tipoOde);
						    			//comprobacion tipo ode
						    			tipo.obtenerTipoOde(imsManifest);
						    			String tipoEstandar= tipo.getTipo();
						    			//Se parsea si no es Scorm12 ni ims_cp
						    			if ((tipoEstandar!=null) && (!tipoEstandar.equals(ConstantesAgrega.SCORM_12)) 
						    				&& (!tipoEstandar.equals(ConstantesAgrega.IMS_CP))){
						    				Manifest manifest = dao.parsearODELazy(new File(imsManifest));
							    			ManifestAgrega manifestAgrega = new ManifestAgrega(manifest);
							    				
							    			Lom[] obj = manifestAgrega.recuperarLomesAsArray();
							    			
							    			if ((obj!=null) && (obj.length>0)){
							    				//tratamos los MEC						    			
								    			Lom lomMeta = obj[0];
								    			List<ErrorParseoVO> mens = new ArrayList<ErrorParseoVO>();
								    			String cat = LdapUserDetailsUtils.getCatalogador();
								    			String mensaje = mecBien(cat,lomMeta,idioma, mens);
//								    			if (mensaje.equals("MECKO")){
//								    				//mostrar el mensaje "Encontrado MEC no válido"
//								    				errores=insertarErrorParseo(mens,prop.getProperty("CAV.11.2"),0,0);
//								    			}
								    			if ((cat.equals("avanzado")) && (mensaje==null) && (!buscarMensaje(mens, prop.getProperty("CAV.11.1"))) ) {
								    				errores.addAll(mens);
								    			}
								    			trataLom("avanzado",obj, errores, prop, idioma);
								    		}
						    			}//fin comprobacion tipoOde
						    		}
							   }
							}
				    		else{//Ha fallado el parseo simple por que el fichero imsmanifest no esta bien formado, 
				    			 //hay alguna etiqueta incorrecta,falta alguna etiqueta,etc...
				    			errores=insertarErrorParseo(errores,prop.getProperty("1.2"),0,0);
				    		}
					
						   resultado = resultadoSimple&&resultadoEsquemas&&resultadoLomes&&resultadoMasLomes;
					
						   List<ErrorParseoVO> lErroresXerces = manEntity.getErroresXerces();//ERRORES GENERADOS EN EL PARSEO CON ESQUEMAS
						   if(lErroresXerces !=null && lErroresXerces.size()>0){
							   lErroresXerces = this.traduceErroresXerces(lErroresXerces);
							   ErrorParseoVO[] erroresXerces=lErroresXerces.toArray(new ErrorParseoVO[lErroresXerces.size()]);
									
							   for(int i=0;i<erroresXerces.length;i++){
								   String textoError= erroresXerces[i].getMensaje();
								   int posi = resultadoValidacion.indexOf(textoError);
								   //si posi es -1 no hemos añadido este error entonces lo añadimos, si posi es distinto 
								   //de -1 ya hemos añadido este mismo texto por tanto no lo añadimos
								   if (posi<0)
									   resultadoValidacion.append(textoError + ";");
							   }    
						   }		
					
						   validavo.setEsValidoManifest(resultado);
					}
				   
					}  
			
			   } catch (Exception e) {
				   resultado = false;
				   //Error en la Estructura del imsmanifest
				   logger.error("Error en el parseo - ",e);
				   if(errores==null)
					   errores = new ArrayList<ErrorParseoVO>();
				   errores=insertarErrorParseo(errores,prop.getProperty("1.2"),0,0);	
			   }
		   
		   }
		   else {//Si el fichero imsmanifest.xml del ode no existe en la raiz
			   resultado = false;
			   errores=insertarErrorParseo(errores,prop.getProperty("1.1"),0,0);
		   }
	
		   ErrorParseoVO[] erroresPar=errores.toArray(new ErrorParseoVO[errores.size()]);//ERRORES GENERERADOS EN EL PARSEO SIMPLE Y AQUI
	
		   for(int i=0;i<erroresPar.length;i++){
			   String textoError=erroresPar[i].getMensaje();
			   int posicion=textoError.indexOf("*");
			   if(posicion>0){
				   String inicioMensaje=textoError.substring(0, posicion-1);
				   String finMensaje=textoError.substring(posicion+1);
				   String traducido=prop.getProperty(inicioMensaje);
				   textoError=traducido+finMensaje;
			   }	
				int posi = resultadoValidacion.indexOf(textoError);
				//si posi es -1 no hemos añadido este error entonces lo añadimos, si posi es distinto 
				//de -1 ya hemos añadido este mismo texto por tanto no lo añadimos
				if (posi<0)
					resultadoValidacion.append(textoError + ";");
		   }
	
		   validavo.setResultadoValidacion(resultadoValidacion.toString());
		   rellenaErroresParseo(validavo, manEntity);
		   validavo.setEsValidoManifest(resultado);
	
		   return validavo;
	 }

    /**
	 * Validación CargaOde: Es la Validación más completa; chequea que el fichero esté bien formado, que todas sus etiquetas sean correctas,
	 *  que contenga en sus metadatos al menos un LOM-ES además de chequear que los contenidos referenciados estén en su ubicación correcta 
	 * 
	 * @param rutaOde Ruta donde se encuentra el fichero a validar
	 * @return ValidaVO. Contiene el resultado de la validación; este objeto esta compuesto de resultadoValidación, 
	 * esValidoManifest y la rutaManifest
	 */
	protected ValidaVO handleValidarCargaOde(String rutaOde) throws Exception {
		
		SCORM2004Dao dao = this.getScormDao();  
		
		String rutaManifest = new StringBuffer(rutaOde).append("/imsmanifest.xml").toString();
		//cambiamos las barras de directorio
		rutaManifest = rutaManifest.replace( '\\', '/');
		File fOde= new File(rutaManifest);
		ValidaVO valida= new ValidaVO();
		ManifestEntityDaoImpl maniEntity = new ManifestEntityDaoImpl();
		maniEntity.setBaseDirectory(rutaOde); 
		Boolean resultado =  true;
		Boolean resultadoMasLomes =  true;
		Boolean resultadoSimple =  true;
		Boolean resultadoEsquemas =  true;
		Boolean resultadoLomes =  true;
		Boolean resultadoTieneLomes =  true;
		Boolean resultadoContenido= true;
		InputStream is = null;
    	Properties prop = new Properties();
//    	Necesitamos el idioma para saber en que idioma tenemos que hacer las traducciones
    	String idioma="es";
    	if (LdapUserDetailsUtils.estaAutenticado()) { 
    		idioma = LdapUserDetailsUtils.getIdioma();
    		if(idioma==null ||idioma.equals("")){
    			idioma=I18n.getInstance().obtenerIdiomaDefectoPlataforma();
    		}
    	}else{
    		idioma=I18n.getInstance().obtenerIdiomaDefectoPlataforma();
    	}

    	
		String nombreFich="/erroresValidacion_"+idioma+".properties";
		is = this.getClass().getResourceAsStream(nombreFich);
		prop.load(is);
    	
		boolean resultadoMEC = true;
		boolean resultadoTaxonomias = true;
    	StringBuilder resultadoValidacion = new StringBuilder();
    	List<ErrorParseoVO> errores = new ArrayList<ErrorParseoVO>();
		if (fOde.exists())
		{			
			try {
				
				NamespaceYEsquema nye= new NamespaceYEsquema();
				TipoOde tipo= new TipoOde();
				tipo.obtieneNameSpaceYEsquema (rutaManifest, nye);
				
				if (nye.getNamespace()==null){
					valida.setEsValidoManifest(false);
					errores=insertarErrorParseo(errores,prop.getProperty("8.52"),0,0);
				}
				else{
					if (("").equals(nye.getNamespace())){
						valida.setEsValidoManifest(false);
						errores=insertarErrorParseo(errores,prop.getProperty("8.48"),0,0);
					}
					else{
	//					PARSEO SIMPLE parseo sin esquemas (ademas elimina metadatos que no sean LOM-ES)
						resultadoSimple = maniEntity.obtenerParseoSimple(rutaManifest,"CA");			
			    		errores = maniEntity.getErroresParseo();//ERRORES GENERERADOS EN EL PARSEO SIMPLE. El resto de errores se iran añadiendo a este array	
			    		if(maniEntity.getDocument()!=null){	//El document es null cuando falla parseoSimple y es imposible 
			    											//montar el Document para pasarlo al metodo esNodoCorrecto
			    			    			
			    			//PARSEO DE LOS LOMES REFERENCIADOS MEDIANTE LOCATION Se parsean por separado todos los lomes 
			    			//se guardan en una array para parsearlos por separado
			    			boolean soloLocation = true;//Esto implica que va recuperar solo los lomes referenciados mediante location
				    		maniEntity.buscarLomes(maniEntity.getDocument(),soloLocation);
				    		
				    		//si hemos obtenido errores de más de un lom
				    		if (!maniEntity.getLomesBien()){
				    			
				    			errores=insertarErrorParseo(errores,prop.getProperty("4.9"),0,0);
				    			//al tener más de un lom ya no podemos validar el manifest
				    			resultadoMasLomes = false;
				    		}else{
				    			//Se parsea los lomes obtenidos anteriormente
				    			resultadoLomes = maniEntity.validarTodosLomes();
			    				//Se comprueba que en el manifest principal existe un lom (tambien si esta referenciado por adlcp:location)
			    				resultadoTieneLomes = maniEntity.tieneLomes(maniEntity.getDocument());
			    				//CHEQUEO DE CONTENIDOS
			    				//Eliminamos esRootValido porque está sujeto al namespace ismcpv1p0
			    				//Boolean rootOK = maniEntity.esRootValido(rutaOde);
			    				Boolean rootOK= true;
								if (rootOK.booleanValue()) {
									maniEntity.crearListaFicherosManifest(rutaManifest);	
									resultadoContenido = maniEntity.chequearContenido(maniEntity.getDocument(), ""); //Chequeo contenidos
								} else {
									// el root no es valido//Error: La etiqueta principal debe ser manifest o lom
									resultado = false;
									errores=insertarErrorParseo(errores,prop.getProperty("1.3"),0,0);
								}
				    			//PARSEO DEL MANIFEST (INCLUIDOS TODOS LOS LOMES)
							
								//String namespace=null;
								//String esquema= null;
							
								resultadoEsquemas = maniEntity.obtenerParseoConEsquemas(rutaManifest,"CA");
							
								if (resultadoEsquemas){
				    			///////////////////////////////////
									Manifest manifest = dao.parsearODELazy(new File(rutaManifest));
					    			ManifestAgrega manifestAgrega = new ManifestAgrega(manifest);
					    				
					    			Lom[] obj = manifestAgrega.recuperarLomesAsArray();
					    			
					    			if ((obj!=null) && (obj.length>0)){
					    				
					    				//tratamos los MEC
						    			Lom lomMeta = obj[0];
						    			List<ErrorParseoVO> mens = new ArrayList<ErrorParseoVO>();
						    			String mensaje = mecBien("avanzado",lomMeta, idioma, mens);
						    			
//						    			//según el string obtenido
//						    			if (mensaje.equals("NOMEC")){
//						    				
//						    				//mostrar el mensaje "no hay MECs"
//						    				errores=insertarErrorParseo(errores,prop.getProperty("CAV.11.1"),0,0);
//						    				//no podemos validar
//						    				resultadoMEC = false;
//						    			}else if (mensaje.equals("MECKO")){
//						    				
//						    				//mostrar el mensaje "Encontrado MEC no válido"
//						    				errores=insertarErrorParseo(errores,prop.getProperty("CAV.11.2"),0,0);
//						    				//no podemos validar
//						    				resultadoMEC = false;
//						    			}
						    			if (mensaje==null) {
						    				resultadoMEC = false;
						    				errores.addAll(mens);
						    			}
					    			}
					    			
					    			trataLom("avanzado",obj, errores, prop, idioma);
									
									if ((errores != null) && (errores.size()>0)){
										resultadoTaxonomias = false;
									}
								///////////////////////////////////
				    			}
				    		}
					}
	
		    		else{//Ha fallado el parseo simple por que el fichero imsmanifest no esta bien formado, 
		    			 //hay alguna etiqueta incorrecta,falta alguna etiqueta,etc...
		    			errores=insertarErrorParseo(errores,prop.getProperty("1.2"),0,0);
		    		}
		    			    		
					resultado = resultadoSimple&&resultadoEsquemas&&resultadoContenido&&resultadoLomes
								&&resultadoTieneLomes&&resultadoTaxonomias&&resultadoMEC&&resultadoMasLomes;
					
					List<ErrorParseoVO> lErroresXerces = maniEntity.getErroresXerces();//ERRORES GENERADOS EN EL PARSEO CON ESQUEMAS
					if(lErroresXerces !=null && lErroresXerces.size()>0){
	 					lErroresXerces = this.traduceErroresXerces(lErroresXerces);
	 					
	 					ErrorParseoVO[] erroresXerces=lErroresXerces.toArray(new ErrorParseoVO[lErroresXerces.size()]);
	 								
	 					for(int i=0;i<erroresXerces.length;i++){
	 						String textoError= erroresXerces[i].getMensaje();
	 						int posi = resultadoValidacion.indexOf(textoError);
	 						//si posi es -1 no hemos añadido este error entonces lo añadimos, si posi es distinto 
	 						//de -1 ya hemos añadido este mismo texto por tanto no lo añadimos
	 						if (posi<0)
	 							resultadoValidacion.append(textoError + ";");
	 					}    
					}		
					
					valida.setEsValidoManifest(resultado);
				}
			}
	 		} catch (Exception e) {
	 			
	 			valida.setEsValidoManifest(false);
	 			//	Error en la Estructura del imsmanifest
	 			logger.error("Error en el parseo - ",e);
	 			if(errores==null)
	 				errores = new ArrayList<ErrorParseoVO>();
	 			errores=insertarErrorParseo(errores,prop.getProperty("1.2"),0,0);
			}
		} else {//Si el fichero imsmanifest.xml del ode no existe en la raiz
			valida.setEsValidoManifest(false);
			//No Existe el ODE.
			errores = insertarErrorParseo(errores, prop.getProperty("1.1"),0,0);
		}
		
		//ERRORES GENERERADOS EN EL PARSEO SIMPLE Y AQUI
		ErrorParseoVO[] erroresPar=errores.toArray(new ErrorParseoVO[errores.size()]);
		
		for(int i=0;i<erroresPar.length;i++){
			String textoError=erroresPar[i].getMensaje();
			int posicion=textoError.indexOf("*");
			if(posicion>0){
				String inicioMensaje=textoError.substring(0, posicion-1);
				String finMensaje=textoError.substring(posicion+1);
				String traducido=prop.getProperty(inicioMensaje);
				textoError=traducido+finMensaje;
			}
			int posi = resultadoValidacion.indexOf(textoError);
			//si posi es -1 no hemos añadido este error entonces lo añadimos, si posi es distinto 
			//de -1 ya hemos añadido este mismo texto por tanto no lo añadimos
			if (posi<0)
				resultadoValidacion.append(textoError + ";");
		}
		
		valida.setResultadoValidacion(resultadoValidacion.toString());
		rellenaErroresParseo(valida, maniEntity);

		return valida;
	}
	
	 /**
	 * Validación : Chequea que el fichero esté bien formado, que todas sus etiquetas sean correctas,
	 * además de chequear que los contenidos referenciados estén en su ubicación correcta y que contenga 
	 * en sus metadatos al menos un LOM-ES
	 * 
	 * @param rutaOde Ruta donde se encuentra el fichero a validar
	 * @return ValidaVO. Contiene el resultado de la validación; este objeto esta compuesto de resultadoValidación, 
	 * esValidoManifest y la rutaManifest
	 */
	protected ValidaVO handleObtenerValidacion(String rutaOde) throws Exception {
		
		String rutaManifest = new StringBuffer(rutaOde).append("/imsmanifest.xml").toString();
		//cambiamos las barras de directorio
		rutaManifest = rutaManifest.replace( '\\', '/');
		File fOde= new File(rutaManifest);
		return obtenerValidacionInterno(fOde, rutaOde, rutaManifest,true);
	}
    /**
	 * Lógica interna del método ObtenerValidacion. 
	 * Se ha creado este método interno para aislar la lógica de la validación y permitir que se invoque desde otros métodos 
	 * con diferentes parámetros.  
	 * 
	 * @param fOde Fichero imsmanifest a validar
	 * @param rutaOde Ruta donde se encuentra el fichero a validar
	 * @param rutaManifest Ruta donde se encuentra el fichero a validar
	 * @param validarContenido Indica si debe validarse el contenido del ODE (recursos referenciados dentro del imsmanifest).
	 * 
	 * @return ValidaVO. Contiene el resultado de la validación; este objeto esta compuesto de resultadoValidación, 
	 * esValidoManifest y la rutaManifest
	 */
	protected ValidaVO obtenerValidacionInterno(File fOde, String rutaOde, String rutaManifest, boolean validarContenido) throws Exception {

		SCORM2004Dao dao = this.getScormDao();  
		
		rutaManifest = new StringBuffer(rutaOde).append("/imsmanifest.xml").toString();
		//cambiamos las barras de directorio
		rutaManifest = rutaManifest.replace( '\\', '/');
		fOde= new File(rutaManifest);
		ValidaVO valida= new ValidaVO();
		ManifestEntityDaoImpl maniEntity = new ManifestEntityDaoImpl();
		maniEntity.setBaseDirectory(rutaOde); 
		Boolean resultado =  true;
		Boolean resultadoMasLomes =  true;
		Boolean resultadoSimple =  true;
		Boolean resultadoEsquemas =  true;
		Boolean resultadoLomes =  true;
		Boolean resultadoContenido= true;
		Boolean resultadoTieneLomes = true;
		
		InputStream is = null;
    	Properties prop = new Properties();
//    	Necesitamos el idioma para saber en que idioma tenemos que hacer las traducciones
    	String idioma="es";
    	if (LdapUserDetailsUtils.estaAutenticado()) { 
    		idioma = LdapUserDetailsUtils.getIdioma();
    		if(idioma==null ||idioma.equals("")){
    			idioma=I18n.getInstance().obtenerIdiomaDefectoPlataforma();
    		}
    	}else{
    		idioma=I18n.getInstance().obtenerIdiomaDefectoPlataforma();
    	}

    	
		String nombreFich="/erroresValidacion_"+idioma+".properties";
		is = this.getClass().getResourceAsStream(nombreFich);
		prop.load(is);
    	
		boolean resultadoMEC = true;//para mostrar la validación de los MEC
		boolean resultadoTaxonomias = true;//para mostrar el resultado del trataLom
    	StringBuilder resultadoValidacion = new StringBuilder();
    	List<ErrorParseoVO> errores = new ArrayList<ErrorParseoVO>();//se inicializa para poder pasarlo al trataLom
		if (fOde.exists())
		{			
			try {
				NamespaceYEsquema nye= new NamespaceYEsquema();
				TipoOde tipo= new TipoOde();
				tipo.obtieneNameSpaceYEsquema (rutaManifest, nye);
					
				if (nye.getNamespace()==null) {
					valida.setEsValidoManifest(false);
					errores=insertarErrorParseo(errores,prop.getProperty("8.52"),0,0);
					
				} else {
					if (("").equals(nye.getNamespace())){
						valida.setEsValidoManifest(false);
						errores=insertarErrorParseo(errores,prop.getProperty("8.48"),0,0);
						
					} else {
	//					PARSEO SIMPLE parseo sin esquemas (ademas elimina metadatos que no sean LOM-ES)
						resultadoSimple = maniEntity.obtenerParseoSimple(rutaManifest,"CA");
	//					ERRORES GENERERADOS EN EL PARSEO SIMPLE. El resto de errores se iran añadiendo a este array
			    		errores = maniEntity.getErroresParseo();
						
			    		if(maniEntity.getDocument()!=null) {
							//Se comprueba que en el manifest principal existe un lom (tambien si esta referenciado por adlcp:location)
			    			resultadoTieneLomes = maniEntity.tieneLomes(maniEntity.getDocument());
				    		//PARSEO DE TODOS LOS LOMES Se parsean por separado todos los lomes que contenga el manifest
							//se quitan del mDocument todos los lomes y se guardan en una array para parsearlos por separado
							//y hacer el parseo con esquema del manifest sin lomes, solo SCORM	
			    			boolean soloLocation = false;//Esto implica que va recuperar tantos los lomes referenciados mediante location como
			    										//como los lomes que tenga el manifest	
				    		maniEntity.buscarLomes(maniEntity.getDocument(),soloLocation);
				    		
				    		//si hemos obtenido errores de más de un lom
				    		if (!maniEntity.getLomesBien()) {
				    			errores=insertarErrorParseo(errores,prop.getProperty("4.9"),0,0);
				    			//al tener más de un lom ya no podemos validar el manifest
				    			resultadoMasLomes = false;
				    			if(logger.isInfoEnabled())logger.info("JABF: ResultadoMasLomes: <"+resultadoMasLomes+">");
				    		
				    		} else {
								//Una vez que tenemos todos los lomes se parsean uno a uno
								resultadoLomes = maniEntity.validarTodosLomes();
								//PARSEO DE MANIFEST SIN LOMES Se parsea el manifest libre metadatos
								resultadoEsquemas = maniEntity.obtenerParseoConEsquemasSinLomes(rutaManifest,"CA");
								
								//CHEQUEO DE CONTENIDOS
								if (validarContenido)
								{
									Boolean rootOK = maniEntity.esRootValido(rutaOde);
									if (rootOK.booleanValue()) {
										maniEntity.crearListaFicherosManifest(rutaManifest);
										resultadoContenido = maniEntity.chequearContenido(maniEntity.getDocument(), ""); //Chequeo contenidos
									
									} else {
										// el root no es valido//Error: La etiqueta principal debe ser manifest o lom
										resultado = false;
										errores=insertarErrorParseo(errores,prop.getProperty("1.3"),0,0);
									}
								}
				    		}
				    		
			    		} else {
			    			//Ha fallado el parseo simple por que el fichero imsmanifest no esta bien formado, 
			    			//hay alguna etiqueta incorrecta,falta alguna etiqueta,etc...
			    			errores=insertarErrorParseo(errores,prop.getProperty("1.2"),0,0);
			    		}
								
						if(!resultadoLomes.booleanValue()){
							errores=insertarErrorParseo(errores, prop.getProperty("1.5"),0,0);
						}
						
						if(!resultadoEsquemas.booleanValue()){
							errores=insertarErrorParseo(errores, prop.getProperty("1.6"),0,0);
						
						} else {	
							///////////////////////////////////Para comprobar los MEC también tenemos que haber comprobado que sólo hay 1 lom
							if ((resultadoLomes) && (resultadoMasLomes)) {//llamamos al método si no hay errores de parseo contra esquemas
								
								//logger.info("Llamada a trataLom con tipo de catalogador: " + "avanzado");
								
								//parseamos el manifest y obtenemos el array de Lomes para el trataLom 
								Manifest manifest = dao.parsearODELazy(new File(rutaManifest));
				    			ManifestAgrega manifestAgrega = new ManifestAgrega(manifest);
				    				
				    			Lom[] obj = manifestAgrega.recuperarLomesAsArray();
				    			
				    			if ((obj!=null) && (obj.length>0)){
				    				
	//			    				tratamos los MEC
					    			Lom lomMeta = obj[0];
					    			List<ErrorParseoVO> mens = new ArrayList<ErrorParseoVO>();
					    			//Lom lomMeta = manifestAgrega.obtenerLom(manifest.getIdentifier(), "");
					    			String cat = LdapUserDetailsUtils.getCatalogador();
					    			String mensaje = mecBien(cat, lomMeta, idioma, mens);
	//				    			if (mensaje.equals("MECKO")){
	//				    				//mostrar el mensaje "Encontrado MEC no válido"
	//				    				errores=insertarErrorParseo(errores,prop.getProperty("CAV.11.2"),0,0);
	//				    				//no podemos validar
	//				    				resultadoMEC = false;
	//				    			}
					    			if ((cat.equals("avanzado")) && (mensaje==null) && (!buscarMensaje(mens, prop.getProperty("CAV.11.1")))) {
					    				//mostramos error
					    				resultadoMEC = false;
					    				errores.addAll(mens);
					    			}
				    			}
				    			//trataLom("avanzado",obj, errores, prop, idioma);
								//trataLom("basico", rutaManifest, errores, prop, idioma);
				    			trataLom(LdapUserDetailsUtils.getCatalogador(),obj, errores, prop, idioma);
								if ((errores != null) && (errores.size()>0)){
										resultadoTaxonomias = false;
								}
								
							}
							///////////////////////////////////
						}
						
						resultado = resultadoSimple&&resultadoEsquemas&&resultadoLomes&&resultadoContenido
								&&resultadoTieneLomes&&resultadoTaxonomias&&resultadoMEC&&resultadoMasLomes;
						
						valida.setEsValidoManifest(resultado);
					}
				}
				
			} catch (Exception e) {
				valida.setEsValidoManifest(false);
				//	Error en la Estructura del imsmanifest
				logger.error("Error en el parseo: Falla parseoSimple - ",e);
				if(errores==null)
					errores = new ArrayList<ErrorParseoVO>();
				errores=insertarErrorParseo(errores,prop.getProperty("1.2"),0,0);	
			}

		} else {//Si el fichero imsmanifest.xml del ode no existe en la raiz 
			valida.setEsValidoManifest(false);
			//No Existe el ODE.
			errores = insertarErrorParseo(errores, prop.getProperty("1.1"),0,0);
		}
		
		//ERRORES GENERERADOS EN EL PARSEO SIMPLE Y AQUI
		ErrorParseoVO[] erroresPar=errores.toArray(new ErrorParseoVO[errores.size()]);
		
		for(int i=0;i<erroresPar.length;i++) {
			String textoError=erroresPar[i].getMensaje();
			int posicion=textoError.indexOf("*");
			if(posicion>0){
				String inicioMensaje=textoError.substring(0, posicion-1);
				String finMensaje=textoError.substring(posicion+1);
				String traducido=prop.getProperty(inicioMensaje);
				textoError=traducido+finMensaje;
			}	
			int posi = resultadoValidacion.indexOf(textoError);
			//si posi es -1 no hemos añadido este error entonces lo añadimos, si posi es distinto 
			//de -1 ya hemos añadido este mismo texto por tanto no lo añadimos
			if (posi<0)
				resultadoValidacion.append(textoError + ";");
		}
		
		valida.setResultadoValidacion(resultadoValidacion.toString());
		rellenaErroresParseo(valida, maniEntity);

		return valida;
	}
	

	  /**
	 * Validación Metadatos Básicos Obligatorios: Valida exclusivamente que estén rellenos los metadatos básicos obligatorios
	 * 
	 * @param mDBasicos Ruta donde se encuentra el fichero a validar
	 * @return ValidaVO. Contiene el resultado de la validación; este objeto esta compuesto de resultadoValidación, 
	 * esValidoManifest y la rutaManifest
	 */
	protected ValidaVO handleValidarMDBasicosObl(MDBasicosOblVO mDBasicos) throws Exception {
		ValidaVO valida= new ValidaVO();
		valida.setRutaManifest(null);
		
		String resultado= "";
		InputStream is = null;
    	Properties prop = new Properties();
    	String idioma="es";
    	if (LdapUserDetailsUtils.estaAutenticado()) { 
    		idioma = LdapUserDetailsUtils.getIdioma();
    		if(idioma==null ||idioma.equals("")){
    			idioma=I18n.getInstance().obtenerIdiomaDefectoPlataforma();
    		}
    	}else{
    		idioma=I18n.getInstance().obtenerIdiomaDefectoPlataforma();
    	}
		String nombreFich="/erroresValidacion_"+idioma+".properties";
		is = this.getClass().getResourceAsStream(nombreFich);
		prop.load(is);
		// Estos mensajes hay que pasarlos a un fichero de properties
		if (mDBasicos != null)
		{
			resultado= ((mDBasicos.getTitulo()==null)||(mDBasicos.getTitulo().equals("")))?prop.getProperty("4.2")+";":""; //Titulo es obligatorio\n
		
			resultado = new StringBuffer(resultado)
            .append(((mDBasicos.getIdioma() ==null) ||(mDBasicos.getIdioma().equals(""))) ? prop.getProperty("4.3")+";":"") //Idioma es obligatorio\n
            .append(((mDBasicos.getDescripcion()==null) ||(mDBasicos.getDescripcion().equals(""))) ? prop.getProperty("4.4")+";":"") //Descripción es obligatorio\n
            .append(((mDBasicos.getTipoRecurso() ==null) ||(mDBasicos.getTipoRecurso().equals(""))) ? prop.getProperty("4.5")+";":"") //Tipo es obligatorio\n
            .append(((mDBasicos.getIdiomaDest()==null) ||(mDBasicos.getIdiomaDest().equals(""))) ? prop.getProperty("4.8")+";":"") //El idioma destino es obligatorio\n 
            .toString();
			
			if (!resultado.equals("")) 
			{
				valida.setEsValidoManifest(false);
				String resAux = resultado.substring(0, resultado.length()-1);
				valida.setResultadoValidacion(resAux);
			} else {
				valida.setEsValidoManifest(true);
				valida.setResultadoValidacion(prop.getProperty("0.0"));
				
			}
			
		} else {
			//la entrada es nula
			valida.setEsValidoManifest(false);
			valida.setResultadoValidacion(prop.getProperty("4.1")); //Los datos del form son obligatorios
			
		}
		return valida;
	}


	 /**
	 * Validación MEC: Valida que el MEC cumpla con el patrón establecido; para ello parsea el fichero
	 * y lo recoge de su posición correspondiente
	 * 
	 * @param rutaManifest Ruta donde se encuentra el fichero a validar
	 * @return String: Su retorno es el MEC si este es Válido y null en caso contrario
	 */
	protected String handleValidarMec(String rutaManifest) throws Exception {
		//cambiamos las barras de directorio
		rutaManifest = new StringBuffer(rutaManifest).append("/imsmanifest.xml").toString();
		rutaManifest = rutaManifest.replace( '\\', '/');
		File fOde= new File(rutaManifest);
		Manifest manifest= null;
//		String entri= null;
		String salida=null;
		List<ErrorParseoVO> mensajes = new ArrayList<ErrorParseoVO>();
		
		String idioma="es";
    	//este parametro idioma es necesario para el metodo mecBien pero no necesitarems us mensajes sólo el mec
		if (fOde.exists()){
			try {
				manifest = this.getScormDao().parsearODELazy(fOde);
				if ((manifest.getMetadata().getGrp_any() != null) && 
						 (manifest.getMetadata().getGrp_any().getAnyObject()!=null)){
					//buscamos objetos LOM en groupAny de Metadata
					int longmeta =manifest.getMetadata().getGrp_any().getAnyObject().length;
					for (int indmeta=0; indmeta< longmeta; indmeta++) {
						//es.pode.parseadorXML.scorm2004.Lom lomMeta = (es.pode.parseadorXML.scorm2004.Lom)manifest.getMetadata().getGrp_any().getAnyObject(indmeta);
						Object anyobject= manifest.getMetadata().getGrp_any().getAnyObject(indmeta);
						 if (anyobject instanceof es.pode.parseadorXML.castor.Lom) {
							 es.pode.parseadorXML.castor.Lom lomMeta = (es.pode.parseadorXML.castor.Lom) anyobject;
							 
							 String mensaje = mecBien("avanzado",lomMeta, idioma, mensajes);
							 
							 
//							 if (lomMeta != null){
//								 es.pode.parseadorXML.lomes.lomesAgrega.LomAgrega lomAgrega= new es.pode.parseadorXML.lomes.lomesAgrega.LomAgrega(lomMeta);
//								 es.pode.parseadorXML.lomes.lomesAgrega.GeneralAgrega gen= lomAgrega.getGeneralAgrega();
//								 if ((gen != null ) ){
//									
////									validamos el mec
//									
//										 int nid = gen.getCountIdentifier();
//										 if(nid>0){
//											 String catalogo = gen.getCatalogo(0)!=null?gen.getCatalogo(0):"" ;
//											 entri = gen.getEntry(0)!=null?gen.getEntry(0):"";
//											 if ((!catalogo.equals("")) && (!entri.equals(""))) {
//												 //miramos que el entry sea valido debe ser de la forma
//												 //es-ex_20061017_2_1234567 siendo -ex opcional
//												 Pattern mask=Pattern.compile("^([A-Za-z]{2})(\\-[A-Za-z][A-Za-z])?\\_((?:19|20)\\d\\d)(?:0\\d|1[0-2])(3[01]|[012]\\d)\\_([0-9]{1})\\_([0-9]{7}$)"); //ejm ee-zz
//												 Matcher matcher = mask.matcher(entri);
//												 
//												 //o de la forma es_ex_2006101722_1300009 siendo _ex opcional
//												 Pattern mask2=Pattern.compile("^([A-Za-z]{2})(\\-[A-Za-z][A-Za-z])?\\_((?:19|20)\\d\\d)(?:0\\d|1[0-2])(3[01]|[012]\\d)([0-9]{1})([0-9]{1})\\_([0-9]{7}$)");
//												 Matcher matcher2 = mask2.matcher(entri);
//												 
//												 boolean m1 = mecValido && matcher.matches();
//												 boolean m2 = mecValido && matcher2.matches();
//												 mecValido = (mecValido && matcher.matches()) || (mecValido && matcher2.matches()); //true--> ok uno de los matches
//												
//											 } else {
//												 mecValido = mecValido && false;
//											 }
//										 }
//									
//									 ///// fin valida mec
							 
							 			//comprobamos el mensaje recibido
										 if(mensaje!=null){
											 salida=mensaje;
										 }else
											 salida=null;
										 
								 }
									 
							}
							
//						 }
//					}
				}
			}catch (Exception e) { 
				logger.error(e);
//	    		throw e;
			}

		}
		return salida;
	}


	protected boolean handleEstoyActivo() throws Exception {
		
		return true;
	}

	  /**
	 * Validación Metadatos Lomes: Valida que el metadato sea un metadato LOM-ES, que esté bien formado
	 * y que todas sus etiquetas sean correctas
	 * 
	 * @param dh Contiene los metadatos LOM-ES que queremos validar
	 * @return Boolean. Resultado de la validación
	 */
	protected Boolean handleObtenerValidacionLomes(
			DataHandler dh)
	throws Exception 
	{			
    	boolean validadorRes = true;
    	String ficheroProperties = "/validador.properties";
		InputStream is= null;
//		logger.info("ASC- METODO: obtenerValidacionLomes!!!! ");
    	DOMParser mParser = new DOMParser();
    	
		if (mParser != null)
		 {
		    try
		      {
		    	if( logger.isDebugEnabled() )
		    		logger.debug( "URL del parseador: " + mParser.getClass().getResource( mParser.getClass().getName().replace('.','/') + ".class") );
		    	
		    	is= this.getClass().getResourceAsStream(ficheroProperties);
				Properties fproperties = new Properties();
				fproperties.load(is);
				String lomCustom = fproperties.getProperty("lomCustom");
				
				File fileLomCustom=new File(lomCustom);
				String sLomCustom=fileLomCustom.getCanonicalPath();
				
//				logger.info("ASC- METODO: obtenerValidacionLomes Namespaces: " + sLomCustom + "; ");
				
			     mParser.setFeature("http://xml.org/sax/features/validation", true);
			     mParser.setFeature("http://xml.org/sax/features/namespaces", true);
			      
//			      estas "feature" son para la validación del manifest contra los esquemas
			      mParser.setFeature("http://apache.org/xml/features/validation/schema",true);
			      
//			      logger.info("METODO: obtenerValidacionLomes!!!! ANTES DE IGNORE-XSI-TYPE-UNTIL-ELEMDECL");
			      mParser.setFeature("http://apache.org/xml/features/validation/schema/ignore-xsi-type-until-elemdecl", true);
			     
//			      logger.info("METODO: obtenerValidacionLomes!!!! DESPUES DE IGNORE-XSI-TYPE-UNTIL-ELEMDECL");
			      String namespacesFull = " http://ltsc.ieee.org/xsd/LOM "+ sLomCustom;
//			      logger.info("METODO: obtenerValidacionLomes!!!! NAMESPACESFULL -> " + namespacesFull.replace( '\\', '/'));
				
			      mParser.setProperty("http://apache.org/xml/properties/schema/external-schemaLocation", namespacesFull);
			      
			      mParser.setFeature("http://apache.org/xml/features/continue-after-fatal-error", false );
			      mParser.setErrorHandler(new XMLErrorHandler());
			   }
			   catch (SAXException se )
			   {
			      String mensaje = "ERROR SAXException " + se;
			      logger.warn(mensaje);
			      validadorRes=false;
			   }
			   
			   try{
				   InputSource istr= new InputSource(dh.getInputStream());
				   mParser.parse(istr);
			   }catch ( SAXException se )
				{
					String mensaje = "Excepcion SAXException durante el parseo " + se;
					logger.error(mensaje);
					validadorRes=false;
				}
		 }
    	
		 return validadorRes;
    
	}
	
	private List<ErrorParseoVO> insertarErrorParseo(List<ErrorParseoVO> errores,String mensaje, Integer linea, Integer columna){
		if(errores ==null){
			errores = new ArrayList<ErrorParseoVO>();
		}
		ErrorParseoVO error = new ErrorParseoVO();
		error.setMensaje(mensaje);
		error.setLinea(linea);
		error.setColumna(columna);
		errores.add(error);
		return errores;
	}
	
	private void rellenaErroresParseo(ValidaVO validavo, ManifestEntityDaoImpl manEntity) {
		if(manEntity.getErroresParseo()!=null) {
			validavo.setErrores((ErrorParseoVO[])manEntity.getErroresXerces().toArray(new ErrorParseoVO[manEntity.getErroresXerces().size()]));
		} else validavo.setErrores(new ErrorParseoVO[0]);
	}
	
	private List<ErrorParseoVO> traduceErroresXerces(List<ErrorParseoVO> erroresXerces) throws Exception{
		List<ErrorParseoVO> erroresTraducido = new ArrayList<ErrorParseoVO>();
		
	   InputStream is = null;
	   Properties prop = new Properties();
	   //Necesitamos el idioma para saber en que idioma tenemos que hacer las traducciones
	   String idioma="es";
	   if (LdapUserDetailsUtils.estaAutenticado()) { 
		   idioma = LdapUserDetailsUtils.getIdioma();
		   if(idioma==null || idioma.equals("")){
			   idioma=I18n.getInstance().obtenerIdiomaDefectoPlataforma();
		   }
	   }else{
		   idioma=I18n.getInstance().obtenerIdiomaDefectoPlataforma();
	   }


	   String nombreFich="/erroresValidacion_"+idioma+".properties";
	   is = this.getClass().getResourceAsStream(nombreFich);
	   try {
		   prop.load(is);

			if(erroresXerces!=null){
				for(int i=0;i<erroresXerces.size();i++){
					ErrorParseoVO error =  erroresXerces.get(i);
					String mensaje = error.getMensaje();
					String fichero = mensaje.substring(mensaje.lastIndexOf(";")+1);
					mensaje = mensaje.substring(0, mensaje.lastIndexOf(";"));
					
					if(mensaje.endsWith("is expected.")){
						//Invalid content was found starting with element 'lomes:language'. One of '{"http://ltsc.ieee.org/xsd/LOM":identifier, "http://ltsc.ieee.org/xsd/LOM":title}' is expected.
						int inicio = mensaje.indexOf("{");
						int fin = mensaje.indexOf("}");
						String texto = mensaje.substring(inicio+1, fin);
						String[] aux = texto.split(",");
						String etiquetas="";
						for(int j=0;j<aux.length;j++){
							String[] aux2 = aux[j].split(":");
							if (etiquetas.equals(""))
								etiquetas = aux2[aux2.length-1];
							else etiquetas = etiquetas + ", " + aux2[aux2.length-1];
						}
						
						
						String traduccion = prop.getProperty("esperabaEtiquetas")+": '" + etiquetas + "'. "+ prop.getProperty("fichero")
											+": '" + fichero + "'. "+ prop.getProperty("linea")+": " + error.getLinea();
						insertarErrorParseo(erroresTraducido,traduccion,error.getLinea(),error.getColumna());
					}
					else if (mensaje.indexOf("must appear on element")>0){
						//Attribute 'identifier' must appear on element 'resource'.
						int x = mensaje.indexOf("'");
						mensaje = mensaje.substring(x,mensaje.length()-1);
						String[] valores= mensaje.split("must appear on element");
						String traduccion = prop.getProperty("faltaAtributo")+": " + valores[1].trim() + ". " + prop.getProperty("esperabaAtributo")+ ": "
											+ valores[0] + ". "+ prop.getProperty("fichero")+": '" + fichero + "'. "+ prop.getProperty("linea")+": " + error.getLinea();
						insertarErrorParseo(erroresTraducido,traduccion,error.getLinea(),error.getColumna());
					}
					else if (mensaje.indexOf("is not a valid value of union type")>0){
						//'fia0l' is not a valid value of union type 'status'.
						int x = mensaje.indexOf("'");
						mensaje = mensaje.substring(x,mensaje.length()-1);
						String[] valores= mensaje.split("is not a valid value of union type");
						String traduccion = prop.getProperty("valorNoValido")+": " + valores[1].trim() + ". "+ prop.getProperty("valorInc")
											+ ": " + valores[0].trim() + ". "+ prop.getProperty("fichero")+": '" + fichero + "'. "+ prop.getProperty("linea")+": " + error.getLinea();
						insertarErrorParseo(erroresTraducido,traduccion,error.getLinea(),error.getColumna());
					}
					else if (mensaje.indexOf("must be terminated by the matching end-tag")>0){
						//The element type "schema" must be terminated by the matching end-tag "</schema>"
						int x = mensaje.indexOf("\"");
						mensaje = mensaje.substring(x,mensaje.length()-1);
						String[] valores= mensaje.split("must be terminated by the matching end-tag");
						String traduccion = prop.getProperty("etiquetaMal") + ": '" + valores[0] 
						                    + "'. "+ prop.getProperty("fichero")+": '" + fichero + "'. "+ prop.getProperty("linea")+": " + error.getLinea();
						insertarErrorParseo(erroresTraducido,traduccion,error.getLinea(),error.getColumna());
					}
					else if (mensaje.indexOf("is not facet-valid with respect to pattern")>0){
						//Value 'dfjañlskf' is not facet-valid with respect to pattern '([a-zA-Z]{1,8})(-[a-zA-Z0-9]{1,8})*' for type 'language'.
						String[] valores= mensaje.split("'");
						String traduccion = prop.getProperty("valorExpresion") + ": '" + valores[3] + "'. "+prop.getProperty("valorInc")+": '" + valores[1] 
											+ "'. "+ prop.getProperty("fichero")+": '" + fichero + "'. "+ prop.getProperty("linea")+": " + error.getLinea();
						insertarErrorParseo(erroresTraducido,traduccion,error.getLinea(),error.getColumna());
					}
					else if (mensaje.indexOf("is not valid with respect to its type")>0 && mensaje.indexOf("on element")>0 
							&& mensaje.indexOf("of attribute")>0){
						//The value 'dfjañlskf' of attribute 'language' on element 'lomes:string' is not valid with respect to its type, 'language'.
						String[] valores= mensaje.split("'");
						String traduccion = prop.getProperty("errorElemento")+": '" + valores[5] + "'. "+ prop.getProperty("valorAtributo")+": '"+ valores[3]
						                    +"'. "+prop.getProperty("valorInc")+": '" + valores[1] + "'. "+ prop.getProperty("fichero")+": '" + fichero 
						                    + "'. "+ prop.getProperty("linea")+": " + error.getLinea();
						insertarErrorParseo(erroresTraducido,traduccion,error.getLinea(),error.getColumna());
					}
					else if(mensaje.indexOf("for element")>0 && mensaje.indexOf("is not bound")>0){
						//The prefix "loes" for element "loes:lom" is not bound			
						String[] valores= mensaje.split("\"");
						String traduccion = prop.getProperty("errorElemento")+": '" + valores[3] + "'. " + prop.getProperty("prefijo")+ ": '"
											+ valores[1]+ "'. "+ prop.getProperty("fichero")+": '" + fichero + "'. "+ prop.getProperty("linea")
											+": " + error.getLinea();
						insertarErrorParseo(erroresTraducido,traduccion,error.getLinea(),error.getColumna());
					}
					else if(mensaje.indexOf("Cannot find the declaration of element")>0 
							|| mensaje.indexOf("The matching wildcard is strict, but no declaration can be found for element")>0){
						String[] valores= mensaje.split("'");
						String traduccion = prop.getProperty("nameSpace")+ ": '" + valores[1]
											+ "'. "+ prop.getProperty("fichero")+": '" + fichero + "'";
						insertarErrorParseo(erroresTraducido,traduccion,error.getLinea(),error.getColumna());
					}
					else if(mensaje.indexOf("There is no ID/IDREF binding for IDREF")>0){//NUEVO
						//There is no ID/IDREF binding for IDREF 'EP18'
						String[] valores= mensaje.split("'");
						String traduccion = prop.getProperty("idIncorrecto") + ": '" + valores[1]
						                    + "'. "+ prop.getProperty("fichero")+": '" + fichero + "'. "+ prop.getProperty("linea")
						         			+": " + error.getLinea();
						insertarErrorParseo(erroresTraducido,traduccion,error.getLinea(),error.getColumna());
					}
					else if(mensaje.indexOf("There are multiple occurrences of ID value")>0){//NUEVO
						//There are multiple occurrences of ID value 'ORG-C4406F5C268B1F88D0586FA62ADDEC65'
						String[] valores= mensaje.split("'");
						String traduccion = prop.getProperty("valorIdRepetido")+": '" + valores[1]
						                    + "'. "+ prop.getProperty("fichero")+": '" + fichero + "'. "+ prop.getProperty("linea")
						                    +": " + error.getLinea();
						insertarErrorParseo(erroresTraducido,traduccion,error.getLinea(),error.getColumna());
					}
					else if(mensaje.startsWith("The prefix") && mensaje.indexOf("for attribute")>0//NUEVO
							&& mensaje.indexOf("associated with an element type")>0 && mensaje.endsWith("is not bound.")){
						//The prefix "adlp" for attribute "adlp:scormType" associated with an element type "resource" is not bound.			
						String[] valores= mensaje.split("\"");
						String traduccion = prop.getProperty("errorElemento")+": '" + valores[5] + "'. "+ prop.getProperty("valorAtributo")+": '"+ valores[3]
						                    + "'. " + prop.getProperty("prefijo")+ ": '"+ valores[1]
						                    + "'. "+ prop.getProperty("fichero")+": '" + fichero + "'. "+ prop.getProperty("linea")
						                    +": " + error.getLinea();
						insertarErrorParseo(erroresTraducido,traduccion,error.getLinea(),error.getColumna());
					}
					else if(mensaje.indexOf("is not allowed to appear in element")>0 ){//NUEVO
						//Attribute 'scormType' is not allowed to appear in element 'resource'.	¿¿¿???	
						String[] valores= mensaje.split("'");
						String traduccion = prop.getProperty("errorElemento")+": '" + valores[3] + "'. "+ prop.getProperty("atributoNoPermitido") +": '"+ valores[1]
						                    + "'. "+ prop.getProperty("fichero")+": '" + fichero + "'. "+ prop.getProperty("linea")
						                    +": " + error.getLinea();
						insertarErrorParseo(erroresTraducido,traduccion,error.getLinea(),error.getColumna());
					}
					else if (mensaje.indexOf("must have no element [children], and the value must be valid.")>0
							|| mensaje.indexOf("is not a valid value for")>0
							|| mensaje.indexOf("is not allowed to appear in element")>0//creo que deberiamos mostrar el error
							|| mensaje.indexOf("is not facet-valid with respect to enumeration")>0){
						
					}
					else{
						String traduccion = prop.getProperty("errorLinea")+": " + error.getLinea() + ". "+ prop.getProperty("fichero")+": '" + fichero + "'. ";
						insertarErrorParseo(erroresTraducido,traduccion,error.getLinea(),error.getColumna());
					}
				}
			}
	   } catch (IOException e) {

		   String mensaje = "ERROR en la traducción de errores Xerces " + e;
		   logger.error(mensaje);
	   }
	   return erroresTraducido;
	}
	
//	private void obtieneNameSpaceYEsquema (String rutaManifest, NamespaceYEsquema nye) throws Exception{
//		
////		String namespace= null;
//		
////		String imsManifest = new StringBuffer(rutaManifest).append("/imsmanifest.xml").toString();
////		//cambiamos las barras de directorio
////		imsManifest = imsManifest.replace( '\\', '/');
//		
//		File docFile = new File(rutaManifest);
//    	Document doc = null;
//    	try{
//      		SAXBuilder builder = new SAXBuilder(false);
//    		doc = builder.build(docFile);
//    		Element manifest = doc.getRootElement();
//    		Namespace nameSpace = manifest.getNamespace();
//    		nye.setNamespace(nameSpace.getURI());
//    		
//    		Element metadata=manifest.getChild("metadata", nameSpace);
//    		if (metadata != null){
//    			nye.setEsquema(metadata.getChildText("schema", nameSpace));
//    		}
//    		else{
//    			nye.setEsquema("");
//    		}
//    	}
//    	catch (Exception e){
//    		
//    		String mensaje = "ERROR en la construcción Jdom para el manifest " + e;
// 		   logger.error(mensaje);
//
//    	}
//
////    		Element manifest=raiz.getElement("manifest", nameSpace);
////    		String vocabName=vocab.getChildText("langstring", nameSpace);
////    		Element ElVvocabIdentifier=raiz.getChild("vocabIdentifier", nameSpace);
////    		String strVocabIdentifier="";
//	}
	
	
	
	

	/**
	 * Tratar lom: Se extrae uno de los campos del lom; para ello parsea el fichero
	 * y lo recoge de su posición correspondiente
	 * 
	 * @param rutaManifest:ruta donde se encuentra el fichero a tratar
	 * @param errores:lista donde se insertan los errores
	 * @param prop:archivo de propiedades
	 * @param idioma:idioma actual
	 * 
	 * @return errores encontrados insertados en la lista errores
	 */

	
private void trataLom (String cat, Lom[] obj, List<ErrorParseoVO> errores, Properties prop, String idioma){
		
		String proPs = "/validador.properties";
		InputStream is = null;
		
		Properties proP = new Properties();
		is = this.getClass().getResourceAsStream(proPs);
		boolean existeIdioma= true;
		String idiomaAux="es";
		
		try {
			
			proP.load(is);
			
			//para tratar si el catalogador es o no avanzado e insertar el error correspondiente
			boolean errorAvanzado = false;
			
			if (obj!=null){
				
				//obtenemos todas las taxonomías
				EstructuraVdexVO[] taxes = this.getSrvTaxonomiaService().obtenerTaxonomias("");
				//obtenemos todos los tesauros
				EstructuraVdexVO[] vocabt = this.getSrvTesaurosServices().obtenerTesauros("");
				//obtener arbol taxonomías vigente
				EstructuraVdexVO taxVigente = this.getSrvTaxonomiaService().obtenerArbolVigente();
				//obtener arbol tesauros vigente
				EstructuraVdexVO tesVigente = this.getSrvTesaurosServices().obtenerTesauroVigente();
				
				for(int i=0;i<obj.length;i++){
					Lom lo = obj[i];
					LomAgrega loma = new LomAgrega(lo);
					
					try {
					
						
						//para cada taxonomía, obtenemos un ClassificationAgrega y lo recorremos
						for(int d = 0; d< loma.getClassificationsAgrega().size();d++){
							ClassificationAgrega claloma = loma.getClassificationAgrega(d);
							
							//Ahora recorremos los taxonPaths del ClassificationAgrega
							for(int d1 = 0;d1<claloma.getTaxonPathsCount();d1++){
								
								//Para los identificadores de las taxonomías o los tesauros
								ArrayList<?> rutax = new ArrayList<Object>();
								//obtenemos las rutas taxonómicas
								rutax = claloma.getRutaTaxonomicasAv();
								//y la pasamos a RutaTaxonómicaAgrega
								RutaTaxonomicaAgrega taxru = (RutaTaxonomicaAgrega)rutax.get(d1);
								
								String fuente = claloma.getFuente(d1, idioma);
								String propos = claloma.getProposito();
								String fuentegui = fuente.replace(' ', '_');
								
								//obtenemos el propósito asignado a la fuente
								String propo = proP.getProperty(fuentegui);
									
								//si hemos obtenido un propósito asignado a la fuente y es igual al nuestro, lo tratamos
								if (propo != null){ 
									
									if (propo.equals(propos)){
									
									if(logger.isDebugEnabled())logger.debug("La fuente que buscamos es <"+fuente+">");
									
									//para controlar el VocabName
									boolean encontrado = false;
									boolean noCorrecto =false;
									
									int id = 0;
									
									//Iteramos sobre las taxonomías para buscar el VocabName, es decir, la fuente
									while((id<taxes.length)&&(!encontrado)){
									
										if (taxes[id].getVocabName().equals(fuente)){
											encontrado = true;

											//una vez comparada la fuente, extraemos el índice del último taxón
											int ult = claloma.getIndiceUltimoTaxon(d1);
										
											//y obtenemos su identificador
											String taxid = claloma.getIdentificador(d1, ult);
											//logger.debug("El identificador del último taxón es: "+taxid);
											
											//comprobamos si existe la taxonomia en el idioma correspondiente, sino en castellano
											existeIdioma = this.getSrvTaxonomiaService().chequearIdiomaTaxonomia(fuente, idioma);
//											if (!existeIdioma) {
//												idiomaAux="es";
//											} else {
//												idiomaAux=idioma; //así no se sobre-escribe el idioma
//											}
											
											if (!existeIdioma) {
												//si no existe la taxonomia en el idioma correspondiente" buscamos un idioma que exista
												//logger.debug("TAXONOMIA con idioma no encontrado en plataforma");
												String[] idiomasEstructuras= I18n.getInstance().obtenerIdiomasBuscables();
												if ((idiomasEstructuras!=null)&&(idiomasEstructuras.length>0)){
													for (int contIdi=0; (contIdi<idiomasEstructuras.length)&&(!existeIdioma); contIdi++){
														existeIdioma = this.getSrvTaxonomiaService().chequearIdiomaTaxonomia(fuente, idiomasEstructuras[contIdi]);
														idioma= idiomasEstructuras[contIdi];
													}
													if (existeIdioma){
														
														idiomaAux=idioma;
													}
													else{ // si no existe el idioma, añadimos error
														errores=insertarErrorParseo(errores, prop.getProperty("CAV.10")+" "+prop.getProperty("CAV.10.2")+"\""+taxid+"\" "+prop.getProperty("CAV.10.3.1")+" "+fuente+".",0,0);
													}
												}
												
											} else {
												idiomaAux=idioma;
											}
											
											
											if (existeIdioma){
//												logger.debug("ASC - Taxonomias - existeIdioma es: " + existeIdioma + " y el idiomaAux es: " + idiomaAux);
												//hallamos el TaxonPath del último taxón para recorrerlo
												//ERROR si no es correcto el identificador del último taxón 
												TaxonVO[] taxoi = this.getSrvTaxonomiaService().obtenerTaxonPath(
														taxid, fuente, idiomaAux);
												
												//ahora le damos la vuelta a los valores del array
												TaxonVO[] taxoio = new TaxonVO[taxoi.length];//array ordenado
												for (int v=0; v<taxoi.length; v++){
													int indice = (taxoi.length)-v;
													TaxonVO tpr = taxoi[indice-1];
													taxoio[v]= tpr;
													//logger.info("El valor almacenado es: "+taxoio[v].getId());									
												}
												
												//Comprobamos que existe el identificador en nuestras fuentes taxonómicas
												int r=0;
												noCorrecto = false;
												if ((taxoio!=null)&& (taxoio.length>0)){
													while((r<=ult) && (!noCorrecto)){
													
														//obtenemos cada identificador del taxonpath
														taxid =claloma.getIdentificador(d1, r);
													
														//logger.debug("El identificador del taxón que comprobamos es: "+taxid);
														
														if(!taxid.equals(taxoio[r].getId())){
															
															noCorrecto = true;
														}	
														r++;
													}
													
													if (noCorrecto){
													
														//logger.debug("El identificador del taxón no es correcto.");
														if(cat.equals("avanzado")){
															
//															comprobamos que no existe el mensaje en la lista de los errores
															if (!buscarMensaje(errores, prop.getProperty("CAV.10")+" "+prop.getProperty("CAV.10.2")+"\""+taxid+"\" "+prop.getProperty("CAV.10.3.1")+" "+fuente+".")){
															
																errores=insertarErrorParseo(errores, prop.getProperty("CAV.10")+" "+prop.getProperty("CAV.10.2")+"\""+taxid+"\" "+prop.getProperty("CAV.10.3.1")+" "+fuente+".",0,0);
															}
														}else{
															
															errorAvanzado = true;
														}
														
													}
												}else{
													//logger.debug("La ruta taxonómica no es válida.");
													
													if (cat.equals("avanzado")){
														
//														comprobamos que no existe el mensaje en la lista de los errores
														if (!buscarMensaje(errores, prop.getProperty("CAV.10")+" "+prop.getProperty("CAV.10.3")+"\""+taxid+"\" "+prop.getProperty("CAV.10.3.1")+" "+fuente+".")){
														
															errores=insertarErrorParseo(errores, prop.getProperty("CAV.10")+" "+prop.getProperty("CAV.10.3")+"\""+taxid+"\" "+prop.getProperty("CAV.10.3.1")+" "+fuente+".",0,0);
														}
													}else{
														
														errorAvanzado = true;
													}
													
												}
											}
											
										}
										
										//seguimos iterando en las taxonomías
										id++;
									}
									
									//buscamos en los tesauros
									if (!encontrado){
										
										//para obtener ja jerarquía de taxones, otenemos el último índice
										int tam = taxru.getTaxones().size();
										//y el taxón correspondiente al índice
										TaxonAgrega taxag = (TaxonAgrega)taxru.getTaxones().get(tam-1);
										//y su identificador
										String idtaxag = taxag.getIdentificador();
																		
										int q = 0;
										while((!encontrado)&& (q <vocabt.length) ){
											if (fuente.equals(vocabt[q].getVocabName())){
											
												//logger.debug("La fuente se ha encontrado como Tesauro.");
												
												encontrado = true;
											}
											
											q++;
										}
									
										//hemos encontrado la fuente y comprobamos los identificadores
										if (encontrado){
										
											JerarquiaVO[] je = null;
											try{
												existeIdioma = this.getSrvTesaurosServices().chequearIdiomaTesauro(fuente, idioma);
//												if (!existeIdioma) {
//													idiomaAux="es";
//												} else {
//													idiomaAux=idioma; //así no se sobre-escribe el idioma
//												}
												
												if (!existeIdioma) {
//													si no existe el tesauro en el idioma correspondiente buscamos un idioma que exista
													String[] idiomasEstructuras= I18n.getInstance().obtenerIdiomasBuscables();
													if ((idiomasEstructuras!=null)&&(idiomasEstructuras.length>0)){
														for (int contIdi=0; (contIdi<idiomasEstructuras.length)&&(!existeIdioma); contIdi++){
															existeIdioma = this.getSrvTesaurosServices().chequearIdiomaTesauro(fuente, idiomasEstructuras[contIdi]);
															idioma= idiomasEstructuras[contIdi];
														}
														if (existeIdioma){
															idiomaAux=idioma;
														}
														else{ // si no existe el idioma, añadimos error
									
															errores=insertarErrorParseo(errores, prop.getProperty("CAV.10")+" "+prop.getProperty("CAV.10.1")+"\""+propos+"\" "+prop.getProperty("CAV.10.1.1")+"\""+fuente+"\".", 0,0);
														}
													}
			
												} else {
													idiomaAux=idioma;
												}
												
												if (existeIdioma){
//													logger.debug("ASC - Tesauros - existeIdioma es: " + existeIdioma + " y el idiomaAux es: " + idiomaAux);
													//obtenemos las jerarquías utilizando el identificador del último taxón, la fuente y el idioma
													je = this.getSrvTesaurosServices().obtenerJerarquia(idtaxag, fuente, idiomaAux);
													
													if ((je!=null)&&(je.length>0)){
														
														//recorremos las jerarquías obtenidas
														int ts = 0;
														boolean jerar = false;
														while (!jerar && (ts<je.length)){
															
															TaxonVO[] taxte = je[ts].getJerarquia();
															
															//si los tamaños coinciden, comprobamos los elementos
															if (taxte.length == tam){
																
																int c = 0;
																boolean cadenaok = true;
																while ((c<taxte.length)&&(cadenaok)){
																	
																	//obtenemos el taxón de la posición c
																	TaxonAgrega taxagr = (TaxonAgrega)taxru.getTaxones().get(c);
																	
																	//y comparamos el identificador con el hallado en la jerarquía
																	if (!(taxte[c].getId().equals(taxagr.getIdentificador()))){
																		if(logger.isInfoEnabled())logger.info("El identificador <"+ taxagr.getIdentificador()+"> no es correcto");
																		cadenaok = false;
																		
																	}
																	
																	c++;
																}
																
																//hemos comprobado toda la ruta
																if (cadenaok){
																	jerar = true;
																}
															}
															ts++;
														}
														
														if (!jerar){
															
															if (cat.equals("avanzado")){
																
																//comprobamos que no existe el mensaje en la lista de los errores
																if (!buscarMensaje(errores, prop.getProperty("CAV.10")+" "+prop.getProperty("CAV.10.4.1")+"\""+fuente+"\" "+prop.getProperty("CAV.10.4.2"))){
																	
																	errores=insertarErrorParseo(errores,prop.getProperty("CAV.10")+" "+prop.getProperty("CAV.10.4.1")+"\""+fuente+"\" "+prop.getProperty("CAV.10.4.2"),0,0);	
																}
															}else{
																
																errorAvanzado = true;
															}
															
														}
														
													}else{
														
														if (cat.equals("avanzado")){
															
//															comprobamos que no existe el mensaje en la lista de los errores
															if (!buscarMensaje(errores, prop.getProperty("CAV.10")+" "+prop.getProperty("CAV.10.4.1")+"\""+fuente+"\" "+prop.getProperty("CAV.10.4.2"))){
																
																errores=insertarErrorParseo(errores, prop.getProperty("CAV.10")+" "+prop.getProperty("CAV.10.4.1")+"\""+fuente+"\" "+prop.getProperty("CAV.10.4.2"),0,0);
																logger.warn("El identificador no proporciona una ruta correcta.");	
															}
														}else{
															
															errorAvanzado = true;
														}
														
													}
												}
												
												
											}catch (Exception e){
												
												if (cat.equals("avanzado")){
														
													//comprobamos que no existe el mensaje en la lista de los errores
													if (!buscarMensaje(errores, prop.getProperty("CAV.10")+" "+prop.getProperty("CAV.10.4"))){
															
															errores=insertarErrorParseo(errores,prop.getProperty("CAV.10")+" "+prop.getProperty("CAV.10.4"),0,0);
															logger.error("El identificador parseado no es valido - ",e);
														}
													}else{
														
														errorAvanzado = true;
													}
													
												}
												
												
											}
//											else{
//												
//												//hemos comprobado que la fuente no es igual al propósito
//												if (cat.equals("avanzado")){
//													
////													comprobamos que no existe el mensaje en la lista de los errores
//													if (!buscarMensaje(errores, prop.getProperty("CAV.10")+" "+prop.getProperty("CAV.10.1")+"\""+propos+"\" "+prop.getProperty("CAV.10.1.1")+"\""+
//															fuente+"\".")){
//													
//														errores=insertarErrorParseo(errores, prop.getProperty("CAV.10")+" "+prop.getProperty("CAV.10.1")+"\""+propos+"\" "+prop.getProperty("CAV.10.1.1")+"\""+
//																fuente+"\".", 0,0);
//													}
//												}else{
//													
//													errorAvanzado = true;
//												}
//											}
										}
										
									
//										if (!encontrado){
//											
////											hemos comprobado que la fuente no es igual al propósito
//											logger.info("La fuente no es igual al propósito");
//									
//											if (cat.equals("avanzado")){
//											
//												//comprobamos que no existe el mensaje en la lista de los errores
//												if (!buscarMensaje(errores, prop.getProperty("CAV.10")+" "+prop.getProperty("CAV.10.1")+"\""+propos+"\" "+prop.getProperty("CAV.10.1.1")+"\""+
//														fuente+"\".")){
//												
//													errores=insertarErrorParseo(errores, prop.getProperty("CAV.10")+" "+prop.getProperty("CAV.10.1")+"\""+propos+"\" "+prop.getProperty("CAV.10.1.1")+"\""+
//															fuente+"\".", 0,0);
//												}
//											}else{
//											
//												errorAvanzado = true;
//											}
//										}
									}
									
									else{
										
										if (cat.equals("avanzado")){
											
											//comprobamos que no existe el mensaje en la lista de los errores
											if (!buscarMensaje(errores, prop.getProperty("CAV.10")+" "+prop.getProperty("CAV.10.1")+"\""+propos+"\" "+prop.getProperty("CAV.10.1.1")+"\""+
													fuente+"\".")){
											
												errores=insertarErrorParseo(errores, prop.getProperty("CAV.10")+" "+prop.getProperty("CAV.10.1")+"\""+propos+"\" "+prop.getProperty("CAV.10.1.1")+"\""+
														fuente+"\".", 0,0);
											}
										}else{
										
											errorAvanzado = true;
										}
									}
								}else{
									

									String voctax = "";
									String voctes = "";
									
									if (taxVigente!= null){
										
										voctax = taxVigente.getVocabName();
									}
									
									if (tesVigente!= null){
										
										voctes = tesVigente.getVocabName();
									}
									
//									logger.debug("JABF: Los VocabName del árbol vigente son: Tax: "+ voctax + "; Tes: "+ voctes );
//									logger.debug("JABF: La fuente es: "+ fuente + " y el propósito a comparar es : "+propos);
									if (!propos.equals(proP.getProperty("Árbol_curricular_LOE_2006")) &&
											((fuente.equals(voctax)) || (fuente.equals(voctes)))){
										
										if (cat.equals("avanzado")){
										
											//comprobamos que no existe el mensaje en la lista de los errores
											if (!buscarMensaje(errores, prop.getProperty("CAV.10")+" "+prop.getProperty("CAV.10.1")+"\""+propos+"\" "+prop.getProperty("CAV.10.1.1")+"\""+
													fuente+"\".")){
										
												errores=insertarErrorParseo(errores, prop.getProperty("CAV.10")+" "+prop.getProperty("CAV.10.1")+"\""+propos+"\" "+prop.getProperty("CAV.10.1.1")+"\""+
														fuente+"\".", 0,0);
											}
										}else{
									
											errorAvanzado = true;
										}
									}
								}	
							}
						}	
											    
					} catch (Exception e) {
						
						if (cat.equals("avanzado")){
							
//							comprobamos que no existe el mensaje en la lista de los errores
							if (!buscarMensaje(errores, prop.getProperty("CAV.10")+" "+prop.getProperty("CAV.10.5"))){
							
								errores=insertarErrorParseo(errores,prop.getProperty("CAV.10")+" "+prop.getProperty("CAV.10.5"),0,0);
								logger.error("Error al obtener las taxonomias - ",e);
							}
						}else{
							
							errorAvanzado = true;
						}
					}
				}
			}
			
			if (errorAvanzado){
				
				errores=insertarErrorParseo(errores,prop.getProperty("CAV.10.6"),0,0);
			}
	} catch (Exception e) {
		// TODO Auto-generated catch block
			logger.error(e);
	}
}
	
	
	/**
	 * Buscar mensaje: Método para buscar un mensaje de error dentro de la lista de errores
	 *
	 * @param errores:lista de objetos ErrorParseoVO donde se van insertando los errores
	 * @param mensaje:string con el mensaje a comprobar
	 * 
	 * @return contiene: falso si no está el mensaje dentro de la lista de mensajes de error
	 */
	
	private boolean buscarMensaje(List<ErrorParseoVO> errores, String mensaje){
		
		boolean contiene = false;
		boolean encontrado = false;
		ErrorParseoVO error = new ErrorParseoVO();
		Iterator<ErrorParseoVO> it = errores.iterator();
		
		while ((it.hasNext()) && (!encontrado)){
			error = it.next();
			if (error.getMensaje().equals(mensaje)){
				encontrado = true;
				contiene = true;
			}
		}
		return contiene;
	}
	
		
	/**
	 * Método para validar los MEC
	 * 
	 */
	private String mecBien (String cat,Lom lomMeta,String idioma, List<ErrorParseoVO> mensajes){
		
		boolean mecCorrecto = true;
		String entri=null;
		int cuentaMec=0;
		String resultado = null;
		String iden = null;
		
		InputStream is = null;
    	Properties prop = new Properties();
    	Properties propIdioma = new Properties();
    	String nombreFich="/validador.properties";
		is = this.getClass().getResourceAsStream(nombreFich);
		
		String nombreFichIdioma="/erroresValidacion_"+idioma+".properties";
		InputStream in = this.getClass().getResourceAsStream(nombreFichIdioma);
		
		try{

			String catalogoMec=this.getSrvPropiedadService().get(AgregaProperties.CATALOGO_MEC);
			
			prop.load(is);
			propIdioma.load(in);
			is.close();
			in.close();
			
			if (cat.equals("avanzado") && lomMeta != null) {
				
				Identifier[] ide = lomMeta.getGeneral().getGroupGeneralGeneral().getIdentifier();
				es.pode.parseadorXML.lomes.lomesAgrega.LomAgrega lomAgrega= new es.pode.parseadorXML.lomes.lomesAgrega.LomAgrega(lomMeta);
				es.pode.parseadorXML.lomes.lomesAgrega.GeneralAgrega gen=lomAgrega.getGeneralAgrega();
				 
				if (gen==null) return null;
					
				//validamos el mec
				int nid = gen.getCountIdentifier();
				if (nid<1) return null;
							 
				//recorremos la estructura de Identificadores
				for (int indi=0;indi<nid; indi++) {
					 
					String catalogo = gen.getCatalogo(indi)!=null?gen.getCatalogo(indi):"" ;
					entri = gen.getEntry(indi)!=null?gen.getEntry(indi):"";
					
					if ((!catalogo.equals("")) && (!entri.equals("")) && (catalogo.equals(catalogoMec))) {
						//miramos que el entry sea valido debe ser de la forma
						//es-ex_20061017_2_1234567 siendo -ex opcional
						Pattern mask=Pattern.compile("^([A-Za-z]{2})(\\-[A-Za-z][A-Za-z])?\\_((?:19|20)\\d\\d)(?:0\\d|1[0-2])(3[01]|[012]\\d)\\_([0-9]{1})\\_([0-9]{7}$)"); //ejm ee-zz
						Matcher matcher = mask.matcher(entri);
						 
						//o de la forma es_ex_2006101722_1300009 siendo _ex opcional
						Pattern mask2=Pattern.compile("^([A-Za-z]{2})(\\-[A-Za-z][A-Za-z])?\\_((?:19|20)\\d\\d)(?:0\\d|1[0-2])(3[01]|[012]\\d)([0-9]{1})([0-9]{1})\\_([0-9]{7}$)");
						Matcher matcher2 = mask2.matcher(entri);
						 
						mecCorrecto = (mecCorrecto && matcher.matches()) || (mecCorrecto && matcher2.matches()); //true--> ok uno de los matches
						 
						if (mecCorrecto) {
							cuentaMec++; //lo hemos encontrado
							//obtenemos el identificador para tratarlo más abajo en el caso de que hayamos obtenido un sólo MEC
							//y sea correcto
							iden = ide[indi].getGroupIdentifierIdentifier().getEntry().getGroupEntryEntry().getContent();
							
						} else {
							//MEC no válido
							mecCorrecto = false; 
							resultado = null; //prop.getProperty(MECKO);
							//mensajes.add(propIdioma.getProperty("CAV.11.2")); //Formato incorrecto
							mensajes=insertarErrorParseo(mensajes,propIdioma.getProperty("CAV.11.2"),0,0);
						}
					}							 
				}
	 
				if ((cuentaMec == 1) && (mecCorrecto)) {
					
					boolean corresponde = true;
					String nivelAg="";
					String idiometa="";
					//Vemos si tiene comunidad autonoma -ex...
					String[] guiones=iden.split("\\_");
					
					if (guiones.length<=1) {
						return resultado;
					}
					
					// Si la longitud es 3 es el MEC nuevo sino, el antiguo
					if (guiones.length==3) {
						
						int posi=guiones.length-2;
						String digitos =guiones[posi];
						//cogemos las dos ultimas posiciones penultima es idioma y ult nivelAgregacion 20061017 2 2
						int longidigi =digitos.length();
						nivelAg=digitos.substring(longidigi-1, longidigi); //nivel agregacion
						idiometa=digitos.substring(longidigi-2, longidigi-1); // idioma metametadatos
						
						//extraemos el nivel de agregación para compararlo
						String nivelAgregacion = lomMeta.getGeneral().getGroupGeneralGeneral().getAggregationLevel().getGroupAggregationLevelAggregationLevel().getComplexTypeAggregationLevelVocabValue().getContent();
						//extraemos el idioma para compararlo
						String idiomaMeta = lomMeta.getMetaMetadata().getGroupMetaMetadataMetaMetadata().getLanguage().getGroupLanguageLanguage().getContent();
						//sacamos su valor del properties
						String idiometaProp = prop.getProperty(idiomaMeta)!=null?prop.getProperty(idiomaMeta):"";
						
						//comparamos valores solo si no es un ODE que esta siendo publicado como una nueva version
						//sustituyendo asi el ODE original
						if(!lomAgrega.esOdeVersionado(catalogoMec)) {
							if (!nivelAgregacion.equals("") && (!nivelAgregacion.equals(nivelAg))) {
								//mensajes.add(propIdioma.getProperty("CAV.11.3"));
								mensajes=insertarErrorParseo(mensajes,propIdioma.getProperty("CAV.11.3"),0,0);
								//error de nivel de agregacion
								corresponde = false;
							}
							if ((!idiometaProp.equals("")) && (!idiometaProp.equals(idiometa))) {
								//mensajes.add(propIdioma.getProperty("CAV.11.4"));
								mensajes=insertarErrorParseo(mensajes,propIdioma.getProperty("CAV.11.4"),0,0);
								//error de idioma incorrecto
								corresponde = false;
							}
						}
					   
					} else {
						//en esa posicion tenemos un solo elemento
						int posi=guiones.length-2;
						nivelAg=guiones[posi];
						
						//sacamos el valor a comparar
						String nivelAgregacion= lomMeta.getGeneral().getGroupGeneralGeneral().getAggregationLevel().getGroupAggregationLevelAggregationLevel().getComplexTypeAggregationLevelVocabValue().getContent();
						
						idiometa="";//nos da igual
						if (!nivelAgregacion.equals("") && !nivelAgregacion.equals(nivelAg) && !lomAgrega.esOdeVersionado(catalogoMec)) {
							//mensajes.add(propIdioma.getProperty("CAV.11.3"));
							mensajes=insertarErrorParseo(mensajes,propIdioma.getProperty("CAV.11.3"),0,0);
							//error de nivel de agregacion
							corresponde = false;
						}
					}	
					if (corresponde) {
						//tenemos MEC válido
						resultado = iden;//prop.getProperty(MECOK); //ahora dev el identificador
						// entrada.add(iden);
					} else {
						resultado = null;//prop.getProperty(MECKO);
					}
					
				} else if ((cuentaMec == 0) && (mecCorrecto)) {
					//no tenemos ni un MEC, luego NOMEC
					//mensajes.add(propIdioma.getProperty("CAV.11.1"));
					mensajes=insertarErrorParseo(mensajes,propIdioma.getProperty("CAV.11.1"),0,0);
					resultado = null;//prop.getProperty(NOMEC);
					
				} else if (cuentaMec > 1) {
					//mensajes.add(propIdioma.getProperty("CAV.11.5"));
					mensajes=insertarErrorParseo(mensajes,propIdioma.getProperty("CAV.11.5"),0,0);
					//tenemos más de un MEC
					resultado = null;//prop.getProperty(MECKO);
				}						 
			}

		} catch (Exception e) {
			logger.error(e);
			// throw e;
		}
		return resultado;
	}

	
	public static byte[] decode(byte[] b) throws Exception {
		ByteArrayInputStream bais = new ByteArrayInputStream(b);
		InputStream b64is = MimeUtility.decode(bais, "base64");
		byte[] tmp = new byte[b.length];
		int n = b64is.read(tmp);
		byte[] res = new byte[n];
		System.arraycopy(tmp, 0, res, 0, n);
		return res;
	}
	 

	/**
	 * Este método se encarga de validar el fichero imsmanifest que se pasa como parámetro.
	 * Es un método específico para ser usado por la Web Semántica para que valide sus ODEs
	 *
	 * @param DataHandler Fichero a validar	
	 * 
	 * @return ValidaVO Información del resultado de la validación
	 */
 	@Override
	protected ValidaVO handleValidarCatalogacionWebSemantica(
			byte[] ficheroCatalogacion, byte[] ficheroCatalogacionAuxiliar) throws Exception {

		if (logger.isDebugEnabled())
			logger.debug("handleValidarCatalogacionWebSemantica. Se solicita validación desde Web Semantica");

 		ValidaVO resultado = null;
		File ficTemp = null;
		File ficTempAuxiliar = null;
		File dirTemporal = null;
		
		try{
			
			// Creamos un directorio en el temporal de la máquina con el currentTimeMilis para que pueda invocarse 
			// a la validación desde web semantica de manera simultanea
			String nombreDirTemporal = System.getProperty("java.io.tmpdir")+ System.getProperty("file.separator") + System.currentTimeMillis();									
			dirTemporal = new File(nombreDirTemporal);			
			dirTemporal.mkdir();
			
			if (logger.isDebugEnabled())
				logger.debug("handleValidarCatalogacionWebSemantica. Se ha creado el directorio temporal" + nombreDirTemporal);
			
						
			// Volcamos el imsmanifest que nos pasan al directorio temporal. Tiene que llamarse imsmanifest porque posteriormente
			// la validación lo va a buscar con ese nombre fijo
			ficTemp = new File(nombreDirTemporal + System.getProperty("file.separator") +"imsmanifest.xml");							

			if (logger.isDebugEnabled())
				logger.debug("handleValidarCatalogacionWebSemantica. Se ha creado el fichero temporal" + ficTemp.getAbsolutePath());

			OutputStream os = new FileOutputStream(ficTemp);					
			//ficheroCatalogacion.writeTo(os);
			os.write(decode(ficheroCatalogacion));

			if (ficheroCatalogacionAuxiliar!=null && ficheroCatalogacionAuxiliar.length>0)
			{
				ficTempAuxiliar = new File(nombreDirTemporal + System.getProperty("file.separator") +"imslrm.xml");
				
				OutputStream osTemp = new FileOutputStream(ficTempAuxiliar);					

				osTemp.write(decode(ficheroCatalogacionAuxiliar));
			}

			
			resultado =  obtenerValidacionInterno(ficTemp, nombreDirTemporal, ficTemp.getAbsolutePath(),false);
			
			if (logger.isDebugEnabled())
				logger.debug("handleValidarCatalogacionWebSemantica. Validación correcta: " + resultado.getEsValidoManifest());
		
		}catch (Exception e) {
			logger.error("Error al validar catalogación desde Web Semantica ", e);			
		}
		finally
		{
			try
			{
				ficTemp.delete();
				if (ficTempAuxiliar!=null)
					ficTempAuxiliar.delete();
				dirTemporal.delete();
			}catch (Exception e) {
				ficTemp=null;
				dirTemporal=null;
			}
		}
		return resultado;
	}
}