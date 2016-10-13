/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.cambios;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

import org.apache.log4j.Logger;

import es.pode.parseadorXML.SCORM2004Dao;
import es.pode.parseadorXML.castor.General;
import es.pode.parseadorXML.castor.Lom;
import es.pode.parseadorXML.castor.Manifest;
import es.pode.parseadorXML.castor.MetaMetadata;
import es.pode.parseadorXML.lomes.lomesAgrega.GeneralAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.IdentificadorAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.LomAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.MetaMetadataAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.RelationAgrega;
import es.pode.parseadorXML.scorm2004.agrega.ManifestAgrega;
import es.pode.soporte.utiles.ficheros.UtilesFicheros;


public class Cambio
{
//	private static java.util.Properties pAuditoriaProperties = null;
	
	private static Logger logger = Logger.getLogger(Cambio.class);
	
	private static int lomesProcesados = 0;
	
	private static String datos = "";
	
	private static boolean descomprimido = false;
	
	private static PrintWriter pw;
	
	private static boolean lomModificado = false;
	
	public static boolean isLomModificado() {
		return lomModificado;
	}

	public static void setLomModificado(boolean lomModificado) {
		Cambio.lomModificado = lomModificado;
	}

	public static PrintWriter getPw() {
		return pw;
	}

	public static void setPw(PrintWriter pw) {
		Cambio.pw = pw;
	}
	
	public static boolean isDescomprimido() {
		return descomprimido;
	}

	public static void setDescomprimido(boolean descomprimido) {
		Cambio.descomprimido = descomprimido;
	}

	public static String getDatos() {
		return datos;
	}

	public static void setDatos(String datos) {
		Cambio.datos = datos;
	}

	public static int getLomesProcesados() {
		return lomesProcesados;
	}

	public static void setLomesProcesados(int lomesProcesados) {
		Cambio.lomesProcesados = lomesProcesados;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception
	{
		System.out.println("Migracion de Mec odes");
	
		logger.info("Inicio Migracion de odes");
		///////////PARA probar en local vm Arguments :-Drepositorio=C:/repositorio
		String repositorio = System.getProperty("repositorio");
		logger.info("Path del repositorio de odes "+repositorio);
		//Se recorren las carpetas del repositorio si existe el fichero licencias.txt no hacemos nada en caso contrario
		//sacamos del fichero imsmanifest.xml la licencia y copiamos de uploads el fichero licencia.txt
		//que se corresponda con la licencia seleccionada
		//Para que se encuentren las licencias tengo q quitar los  blancos puedo hacer un split por blanco

		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMdd");
		Random random = new Random();
		int num = random.nextInt();
		if(num < 0)
			num = -num;
		
		File fileOut = new File("informeOdesIdentificador_" + sdf.format(new java.util.Date()) + "_" + num + ".csv" );
		
		if (!fileOut.exists()) {
			fileOut.createNewFile();
		}
		FileWriter fw = new FileWriter(fileOut, true);
		PrintWriter pwr = new PrintWriter(fw);
		setPw(pwr);
		
		logger.info("Comenzamos a explorar la carpeta repositorio.... ");
		validarRutas(repositorio);
		logger.info("Fin migración de Odes");
		System.out.println("Finalización migracion de odes");
		fw.close();
		
	}
	
	@SuppressWarnings("unchecked")
	private static Lom procesarLom(Lom lomOut,LomAgrega lomAgrega, ManifestAgrega manAgrega) throws Exception{

		//Nombre del vocabName con el que vamos a hacer las comparaciones y sino, lo sobreescribimos
		
//		Properties prop = new Properties();
//		InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("migracion_param.properties");
//		prop.load(is);
		
		Lom lomNuevo= new Lom();
		//Lom lom = manAgrega.obtenerLom(manAgrega.getManifest().getIdentifier(), null);
		if (lomOut!=null) {
			
			//Creamos el General
			String newIdOde = "";
			String informe = "";
			try {
				GeneralAgrega general = lomAgrega.getGeneralAgrega();
				GeneralAgrega generalOut = new GeneralAgrega(new General());

				//Generamos el contenido de Identificador: Catalogo y Entrada
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String odeDate = sdf.format(new Date());
				Random random = new Random();
				Integer startNum = random.nextInt(10000000);
				if(startNum < 0)
					startNum = -startNum;
				String numStr = startNum.toString();
				boolean sigue = true;
				while(sigue){
					if(numStr.length() < 7)
						numStr = "0" + numStr;
					else
						sigue = false;
				}
				newIdOde = null;
				
				if(general.getNivelDeAgregacion() != null)
					newIdOde = "es_" + odeDate + "_" + general.getNivelDeAgregacion() + "_" + startNum.toString();
				else
					newIdOde = "es_" + odeDate + "_1_" + startNum.toString();
				generalOut.setIdentificadorPrimeraPosicion("Catálogo unificado mec-red.es-ccaa de identificación de ODE", newIdOde);

				//Cogemos los datos para el informe de cambios
				//Mec antiguo
				setLomModificado(false);
				
				Iterator<IdentificadorAgrega> it= general.getIdentificadoresAv().iterator();
				while(it.hasNext()){
					IdentificadorAgrega mecAntiguo = it.next();
					String catalogo = mecAntiguo.getCatalogo();
					if(catalogo != null && catalogo.equals("Catálogo unificado mec-red.es-ccaa de identificación de ODE") &&
							mecAntiguo.getEntrada() != null &&
							!mecAntiguo.getEntrada().equals(newIdOde)){
						informe += mecAntiguo.getEntrada() + "/";
						setLomModificado(true);
					}	informe += mecAntiguo.getEntrada() + "/";
					
				}
				if(isLomModificado()){
					informe = informe.substring(0, informe.length() - 1) + ";" + newIdOde;
				}
				else
					informe = ";";
				
				//Titulo
				informe += ";" + general.getTitulo(null);
				setDatos(informe);
				
				//Copiamos el resto de contenidos del original
				generalOut.setAmbitosAv(general.getAmbitos());
				generalOut.setDescripcionesAv(general.getDescripcionesAv());
				generalOut.setEstructuraAv(general.getEstructuraAv());
				generalOut.setIdiomasAv((ArrayList) general.getIdiomasAv());
				generalOut.setNivelDeAgregacionAv(general.getNivelDeAgregacionAv());
				generalOut.setPalabrasClaveAv(general.getPalabrasClaveAv());
				generalOut.setTituloAv(general.getTitulosAv());	
					
				lomAgrega.setGeneralAgrega(generalOut);
				
			} catch (Exception e) {
				logger.error("No se ha podido modificar la ficha General correctamente");
				logger.error(e);
			}	
			
			//Generamos el Meta-metadata
			try {
				MetaMetadataAgrega mmdAgrega = lomAgrega.getMetaMetadataAgrega();
				MetaMetadataAgrega mmdOut = new MetaMetadataAgrega(new MetaMetadata());
				
				//Generamos el contenido de Identificador: Catalogo y Entrada			
				mmdOut.setIdentificadorMEC("Catálogo unificado mec-red.es-ccaa de identificación de ODE", newIdOde + "-meta");
				
				//Copiamos el resto de contenidos del original
				mmdOut.setContribucionesAv(mmdAgrega.getContribucionesAv());
				mmdOut.setEsquemasDeMetadatosAv(mmdAgrega.getEsquemasDeMetadatosAv());
				mmdOut.setIdiomasAv(mmdAgrega.getIdiomaAv());
				
				lomAgrega.setMetaMetadataAgrega(mmdOut);
			} catch (Exception e) {
				logger.error("No se ha podido modificar la ficha Meta-metadata correctamente");
				logger.error(e);
			}
			
			//Generamos Relación
//			try {
//				RelationAgrega[] listRel = new RelationAgrega[0];
//				lomAgrega.setRelationsAgrega(listRel);
//			} catch (Exception e) {
//				logger.error("No se ha podido modificar la ficha Relacion correctamente");
//				logger.error(e);
//			}			
			lomNuevo = lomOut;			
		}
			return lomNuevo;
	}
	
	
	private static void validarRutas(String repositorio)throws Exception
	{
		java.io.File fPath = null;
		java.io.File[] subPaths = null;
		setDescomprimido(false);
		
		if(repositorio==null)
			{
				logger.error("El path repositorio de odes es nulo");
				throw new NullPointerException("path==null");
			}
		
		fPath = new java.io.File(repositorio);
		if(!fPath.isDirectory()) {
			logger.error("El path repositorio " + repositorio + " no es un directorio. Por favor chequee la ruta y vuelva a ejecutar el script");
		} else {
			
			subPaths = fPath.listFiles();			
			//logger.debug("subPaths.length " + subPaths.length);
			for(int i=0;i<subPaths.length;i++) {
				if(isDescomprimido())
					setDescomprimido(false);
				//logger.debug("subPaths[i].getName() " + subPaths[i].getName());
				//PREGUNTO PRIMERO SI ES ZIP
				es.pode.soporte.zip.TrueZipDaoImpl zipdao = new es.pode.soporte.zip.TrueZipDaoImpl();
				try {	
					if ( zipdao.esZip(subPaths[i].toString())) {
						String archivo = subPaths[i].toString();
						de.schlichtherle.io.File myFile = new de.schlichtherle.io.File(archivo);
						if ((myFile.isArchive() && myFile.isDirectory() && !myFile.isFile()
								&& myFile.exists() && myFile.length() == 0)) {
							logger.info("Es zip");
							//lo descomprimimos
							//es.pode.soporte.zip.TrueZipDaoImpl zipdao = new es.pode.soporte.zip.TrueZipDaoImpl();
							String ruta_fin = archivo.substring(0, archivo.length()-4);
							File fileDesc = new File(ruta_fin);
							if(!fileDesc.exists()){
								fileDesc.mkdirs(); 
							} else {
								//renombramos el fichero para evitar problemas
								ruta_fin= ruta_fin + "_aux";
								fileDesc = new File(ruta_fin);
								fileDesc.mkdirs();
							}
							
							zipdao.descomprimir(archivo, ruta_fin);
							logger.info("Descomprimiendo fichero zip");
							setDescomprimido(true);
							//ahora volveremos a llamar al validar licencia con esa ruta
							//tenemos que borrar el zip original
							boolean borrado = subPaths[i].delete();
							if (borrado)
								subPaths[i]=new File(ruta_fin);
						}
					}	
					//ES DIRECTORIO
					if(subPaths[i].isDirectory()) {
						// Comprobar si esta entrada tiene un imsmanifest.xml. En
						// caso contrario, internarse si recursivo=true
						String[] manifiestos = subPaths[i].list(new FilenameFilter() {
							public boolean accept(File path, String name) {
								return "imsmanifest.xml".equals(name);
							}
						});
						//logger.debug("manifiestos " + manifiestos);
						if(manifiestos!=null && manifiestos.length > 0 && isDescomprimido()) {
							//señal para reescribir el ode						
	
							File extraeSubmanifest = new File(subPaths[i], "imsmanifest.xml");
							//logger.debug("extraeSubmanifest!!! "+extraeSubmanifest);
							try {
								//inicializamos los datos para que respete los espacios
								setDatos(";;");
								SCORM2004Dao scorm2004Dao = new SCORM2004Dao();
								scorm2004Dao.getBeanMapper();
								Manifest imsmanifest = scorm2004Dao.parsearODELazy(extraeSubmanifest);
								logger.info("el manifest ha sido parseado ");
								ManifestAgrega manAgrega = new ManifestAgrega(imsmanifest);
								String id = imsmanifest.getIdentifier();
								Lom lomOut = manAgrega.obtenerLom(id, null);
		
								if (lomOut!=null) {
									LomAgrega lomAgrega = new LomAgrega(lomOut);
									if (lomAgrega!=null){
										Lom lomMod =procesarLom(lomOut,lomAgrega, manAgrega);
										manAgrega.setLom(id, null, lomMod);
									}//lom no es vacio
								}							
							
								//al final de todo escribimos el ode si se ha modificado algún lom
								scorm2004Dao.escribirODE(imsmanifest, new File(subPaths[i] + "/imsmanifest.xml"));
								logger.info("Sobrescrito manifest");
							} catch (Exception e) {								
								logger.error("error " + e);
								getPw().println(getDatos() +"Error en el fichero;" + subPaths[i]);
							   if (!zipdao.esZip(subPaths[i].toString())) {
								    String ruta_final = subPaths[i].toString();
									String[] zipFinal = ruta_final.split("\\"+File.separator);//ruta_final.split("\\\\");
									String nomFich = zipFinal[zipFinal.length-1];
									if (nomFich.endsWith("_aux")) {
										String frin= nomFich.substring(0, nomFich.length()-4);
										nomFich=frin;
									}
									nomFich = nomFich + ".zip";
									logger.info("el nombre del fichero sera: " + nomFich);
									String rFin= subPaths[i].getParent() + File.separator + nomFich;						
									String datoSubPaths= subPaths[i].toString();
								    if (!zipdao.esZip(rFin))
								    	zipdao.comprimir(rFin, subPaths[i].toString());
									//borrar carpeta
									UtilesFicheros.eliminar(subPaths[i]);
							   }
								continue;
							}
							
							//el proceso final es comprimir el fichero y borrar la carpeta
							//es.pode.soporte.zip.TrueZipDaoImpl zipdao = new es.pode.soporte.zip.TrueZipDaoImpl();
							String ruta_final = subPaths[i].toString();
							String[] zipFinal = ruta_final.split("\\"+File.separator);//ruta_final.split("\\\\");
							String nomFich = zipFinal[zipFinal.length-1];
							if (nomFich.endsWith("_aux")) {
								String frin= nomFich.substring(0, nomFich.length()-4);
								nomFich=frin;
							}
							nomFich = nomFich + ".zip";
	//						Generamos el archivo de informe
							getPw().println(getDatos() + ";" + subPaths[i] + ".zip");
	
							logger.info("el nombre del fichero sera: " + nomFich);
							String rFin= subPaths[i].getParent() + File.separator + nomFich;						
							String datoSubPaths= subPaths[i].toString();
							zipdao.comprimir(rFin, subPaths[i].toString());
							//borrar carpeta
							
							UtilesFicheros.eliminar(subPaths[i]);
							System.out.println("Borrando la carpeta " + subPaths[i]);
							logger.info("Borrando la carpeta " + subPaths[i]);
							
							
					} else {
							//si el manifest es null llamamos recursivamente
							validarRutas(subPaths[i].getPath());
						}
						 
					} //Fin es directorio 
				}catch (Exception e) {
						//si se produce un error intentamos seguir
						getPw().println(getDatos() + "Error en el fichero;"+ subPaths[i]);
					    if (!zipdao.esZip(subPaths[i].toString())) {
						    String ruta_final = subPaths[i].toString();
							String[] zipFinal = ruta_final.split("\\"+File.separator);//ruta_final.split("\\\\");
							String nomFich = zipFinal[zipFinal.length-1];
							if (nomFich.endsWith("_aux")) {
								String frin= nomFich.substring(0, nomFich.length()-4);
								nomFich=frin;
							}
							nomFich = nomFich + ".zip";
							logger.info("el nombre del fichero sera: " + nomFich);
							String rFin= subPaths[i].getParent() + File.separator + nomFich;						
							String datoSubPaths= subPaths[i].toString();
						    if (!zipdao.esZip(rFin))
						    	zipdao.comprimir(rFin, subPaths[i].toString());
							//borrar carpeta
							UtilesFicheros.eliminar(subPaths[i]);
					   }
					continue;
				}
			  } //Fin for
			}
		}
}
