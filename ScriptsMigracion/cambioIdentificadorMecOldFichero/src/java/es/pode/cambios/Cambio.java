/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.cambios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
import es.pode.parseadorXML.lomes.lomesAgrega.LomAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.MetaMetadataAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.RelationAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.IdentificadorAgrega;
import es.pode.parseadorXML.scorm2004.agrega.ManifestAgrega;
import es.pode.soporte.utiles.ficheros.UtilesFicheros;


public class Cambio
{
//	private static java.util.Properties pAuditoriaProperties = null;
	
	private static Logger logger = Logger.getLogger(Cambio.class);
	
	private static String datos = "";
	
	private static boolean descomprimido = false;
	
	private static PrintWriter pw;
	
	private static boolean lomModificado = false;
	
	private static String finalMec = "";
	
	public static boolean isLomModificado() {
		return lomModificado;
	}

	public static void setLomModificado(boolean lomModificado) {
		Cambio.lomModificado = lomModificado;
	}

	public static boolean isDescomprimido() {
		return descomprimido;
	}
	
	public static PrintWriter getPw() {
		return pw;
	}

	public static void setPw(PrintWriter pw) {
		Cambio.pw = pw;
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

	public static void setFinalMec(String finalMec) {
		Cambio.finalMec= finalMec;
	}
	
	public static String getFinalMec() {
		return finalMec;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception
	{
		System.out.println("Migracion de odes");
	
		logger.info("Inicio Migracion de odes");
		///////////PARA probar en local vm Arguments :-Drepositorio=C:/repositorio
		String archivoRutas = System.getProperty("archivo");
		logger.info("Archivo de odes "+archivoRutas);
		
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
		//sacamos el nº aleatorio de 7 digitos para componer el final del mec
		int min=1000000;
		int max=9999999;
		int start = (int)(java.lang.Math.random()*(max-min)) +min;
		if (start>=9000000) {
			start =start - 8000000;
		}
		String startNum = Integer.toString(start);
		//inicializdo final de mec y tendremos que aumentarlo por cada ode
		setFinalMec(startNum);
		validarRutas(archivoRutas);
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
				MetaMetadataAgrega mmdAgrega = lomAgrega.getMetaMetadataAgrega();
				
				GeneralAgrega general = lomAgrega.getGeneralAgrega();
				GeneralAgrega generalOut = new GeneralAgrega(new General());

				//Generamos el contenido de Identificador: Catalogo y Entrada
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String odeDate = sdf.format(new Date());
//				return (int)(Math.random()*(max-min))+min
//					int min=1000000;
//					int max=9999999;
//					int start = (int)(java.lang.Math.random()*(max-min)) +min;
//					String startNum = Integer.toString(start);				
				//Random random = new Random();
//				Integer startNum = random.nextInt(10000000);
//				if(startNum < 0)
//					startNum = -startNum;
//				String numStr = startNum.toString();
//				boolean sigue = true;
//				while(sigue){
//					if(numStr.length() < 7)
//						numStr = "0" + numStr;
//					else
//						sigue = false;
//				}
				
				// recogemos el final de mec
				String startNum = getFinalMec();
				//System.out.println("El final de mec es " + startNum + " su long es " + startNum.length());
				
				newIdOde = null;
				
				if(general.getNivelDeAgregacion() != null)
					newIdOde = "es_" + odeDate + "_" + general.getNivelDeAgregacion() + "_" + startNum;
				else
					newIdOde = "es_" + odeDate + "_1_" + startNum;
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
					}
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
			
//			//Generamos Relación
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
	
	
	private static void validarRutas(String archivoRutas)throws Exception
	{
		java.io.File fPath = null;
		java.io.File subPath = null;
		setDescomprimido(false);
		
		if(archivoRutas==null)
			{
				logger.error("El path repositorio de odes es nulo");
				throw new NullPointerException("path==null");
			}
		
		fPath = new java.io.File(archivoRutas);
		if(fPath != null && !fPath.isFile()) {
			logger.error("El archivo " + archivoRutas + " no es un archivo válido. Por favor chequee la ruta y vuelva a ejecutar el script");
		} else {
			BufferedReader br = new BufferedReader(new FileReader(fPath));
			if(br != null){
				String linea = "";
				while((linea = br.readLine()) != null){
					if(isDescomprimido())
						setDescomprimido(false);
					//PREGUNTO PRIMERO SI ES ZIP
					es.pode.soporte.zip.TrueZipDaoImpl zipdao = new es.pode.soporte.zip.TrueZipDaoImpl();
					if ( zipdao.esZip(linea.toString())) {
						String archivo = linea.toString();
						de.schlichtherle.io.File myFile = new de.schlichtherle.io.File(linea);
						if ((myFile.isArchive() && myFile.isDirectory() && !myFile.isFile()
								&& myFile.exists() && myFile.length() == 0)) {
							logger.info("Es zip");
							//lo descomprimimos							
							Random random = new Random();
							int num = random.nextInt();
							if(num < 0)
								num = -num;
							String ruta = linea.substring(0, linea.length()-4);
							String ruta_fin = ruta + "_" + num;
							File fileDesc = new File(ruta_fin);
							fileDesc.mkdirs();
							zipdao.descomprimir(archivo, ruta_fin);
							logger.info("Descomprimiendo fichero zip");
							setDescomprimido(true);
							//ahora volveremos a llamar al validar licencia con esa ruta
							//tenemos que borrar el zip original
							boolean borrado = new File(linea).delete();
							if(borrado)
								subPath = new File(ruta_fin);
					
							String[] manifiestos = subPath.list(new FilenameFilter() {
								public boolean accept(File path, String name) {
									return "imsmanifest.xml".equals(name);
								}
							});
					
							if(manifiestos!=null && manifiestos.length > 0 && isDescomprimido()) {
								//señal para reescribir el ode						

								File extraeSubmanifest = new File(subPath, "imsmanifest.xml");
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
								scorm2004Dao.escribirODE(imsmanifest, new File(subPath + "/imsmanifest.xml"));
								logger.info("Sobrescrito manifest");
		
								//el proceso final es comprimir el fichero y borrar la carpeta
								String ruta_final = subPath.toString();
								String[] zipFinal = ruta_final.split("\\"+File.separator);//ruta_final.split("\\\\");
								String nomFich = zipFinal[zipFinal.length-1];
								nomFich = nomFich.substring(0, nomFich.indexOf("_" + num));
								nomFich = nomFich + ".zip";
								logger.info("el nombre del fichero sera: " + nomFich);
								String rFin= subPath.getParent() + File.separator + nomFich;						
								String datoSubPaths= subPath.toString();
								zipdao.comprimir(rFin, subPath.toString());
								//borrar carpeta
								
								UtilesFicheros.eliminar(subPath);
								System.out.println("Borrando la carpeta " + subPath);
								logger.info("Borrando la carpeta " + subPath);
								
								//Generamos el archivo de informe
								setDatos(getDatos() + ";" + ruta + ".zip");
							}
						}
						
						if(isLomModificado()){
							getPw().println("Se ha modificado;" + getDatos());
						}
						else
							getPw().println("No se ha modificado;" + getDatos());
			
					}else 
						logger.error("El archivo " + archivoRutas + " no es un zip válido.");
					//al final de cada iteracion se incrementa el aleatorio
					int otroStart= Integer.parseInt(getFinalMec()) + 1;
					setFinalMec(Integer.toString(otroStart));
				}
			}
		}
	}
}

