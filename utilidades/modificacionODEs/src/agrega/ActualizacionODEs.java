/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package agrega;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class ActualizacionODEs {
	
	private static String RUTA_FICHERO_LOCALIZADORES;
	private static String RUTA_BACKUP_ODES;
	
	/* Ruta donde se encuentra la carpeta uploads */
	private static String RUTA_REPOSITORIO_ODES;
	
	/* Javascript de descartes que se incluira dentro de los ODEs */
	static String descartesJavascript="descartes-min.js";
	
	/* Para imprimir saltos de linea */
	static String newline=System.getProperty("line.separator");

	/* Contadores que sirven como resumen para ver las operaciones realizadas sobre un ODE */
	/* Se ponen a 0 cuando se comienza a procesar un ODE */
	static int numAppletHTMLFiles;
	static int numCopiasJs;
	static int numActualizacionesHTML;
	static int numActualizacionesTXT;
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
						
		if (args==null || args.length<3)
		{
			System.out.println("Este programa se encarga de modificar, solo si es necesario, los ODEs de tipo Newton y Descartes.");
			System.out.println("Tambien se puede usar para actualizar el javascript de descartes en los ODES.");
			System.out.println("En ambos casos se necesita una copia del javascript "+descartesJavascript+" en la misma ruta en la que se situe este jar.");
			System.out.println("Necesita que se le pasen como parametros lo siguiente:");
			System.out.println("- Un fichero de localizadores de un tipo de ODE: el fichero de localizadores debe ser unos de los generados por comprobacionManifest.jar; ODEsDescartes.txt o ODEsNewton.txt");
			System.out.println("- La ruta del repositorio: es la ruta absoluta en el HD de la carpeta UPLOADS de Agrega");
			System.out.println("Sintaxis correcta:    java -jar actualizacionODEs.jar ficheroLocalizadores.txt rutaBackup rutaRepositorio");
			System.out.println("Ejemplo de ejecución: java -jar actualizacionODEs.jar ./localizadoresODEs.txt ./backupODEs /export/ccaa/ENTORNO/");
			return;
		}

		RUTA_FICHERO_LOCALIZADORES=args[0];
		RUTA_BACKUP_ODES=args[1];
		RUTA_REPOSITORIO_ODES=args[2];

		if(!compruebaFichero(descartesJavascript)) return;
		if(!compruebaFichero(RUTA_FICHERO_LOCALIZADORES)) return;
		if(!compruebaDirectorio(RUTA_BACKUP_ODES)) return;
		if(!compruebaDirectorio(RUTA_REPOSITORIO_ODES)) return;
		
		procesarFicheroLocalizadores();
	}
	
	
	public static boolean compruebaFichero (String path) {
		File fichero=new File(path);
		if(!fichero.exists()) {
			System.out.println("No se ha encontrado el fichero "+path);
			return false;
		}
		if(!fichero.isFile()) {
			System.out.println("La ruta "+path+" no corresponde a un fichero");
			return false;
		}
		if(!fichero.canRead()) {
			System.out.println("No se tienen permisos de lectura para el fichero "+path);
			return false;
		}
		return true;
	}
	
	
	public static boolean compruebaDirectorio (String path) {
		File fichero=new File(path);
		if(!fichero.exists()) {
			System.out.println("No se ha encontrado el directorio "+path);
			return false;
		}
		if(!fichero.isDirectory()) {
			System.out.println("La ruta "+path+" no corresponde a un directorio");
			return false;
		}
		if(!fichero.canRead()) {
			System.out.println("No se tienen permisos de lectura para el directorio "+path);
			return false;
		}
		return true;
	}
	
	
	public static void procesarFicheroLocalizadores() {
			
		int numODEsProcesados=0;	
		int numODEsTotales=0;		
		BufferedReader entrada;

		File f = new File(RUTA_FICHERO_LOCALIZADORES);
		
		try {					
			//Recorremos el fichero de localizadores
			System.out.println("Procesando fichero : " + f.getAbsolutePath());
			entrada = new BufferedReader(new FileReader(f));	
			
			String dirODE;
			
			while(entrada.ready()){
				
				dirODE = entrada.readLine();
				numODEsTotales++;
				String sIdODE = dirODE.replace("/", "_");
				String sNombreFicZip = RUTA_BACKUP_ODES +"/" + sIdODE + ".zip"; 

				System.out.println(newline+"==============================================================");
				System.out.println("Procesando ODE: " + dirODE);
				
				//Realizamos un backup del ODE
				if(backupODE(RUTA_REPOSITORIO_ODES+ dirODE, sNombreFicZip)) { 

					numAppletHTMLFiles=0;
					numCopiasJs=0;
					numActualizacionesHTML=0;
					numActualizacionesTXT=0;
					
					//Recorremos la estructura del ODE para actualizar los html
					File fLocalizador = new File(RUTA_REPOSITORIO_ODES+ dirODE);
					recorrerDirectorioRecursos(fLocalizador);		
					numODEsProcesados++;
					
				} else {
					System.out.println("No se pudo crear el backup");					
				}

				System.out.println("Resumen ejecucion:");
				System.out.println("Num de ficheros HTML/HTM con applets:\t\t"+numAppletHTMLFiles);
				System.out.println("Num de veces copiadas el JS en el ODE:\t\t"+numCopiasJs);
				System.out.println("Num de ficheros TXT modificados:\t\t"+numActualizacionesTXT);	
			}					
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(newline+newline+"Resumen tras procesar fichero: " + f.getAbsolutePath());
		System.out.println("Numero ODEs modificados:\t\t"+numODEsProcesados);
		System.out.println("Numero ODEs totales:\t\t"+numODEsTotales);
	}	

	
	/**
	 * Este método recorre de manera recursiva un directorio y para cada fichero html o htm 
	 * invoca al método que verifica si contiene la definición de un 
	 * applet del tipo Descartes o Newton. Si encuentra una carpeta de 'datos' invoca
	 * a otro metodo que se encarga de recorrer recursivamente este tipo de carpetas.
	 * @param fDirectorio Fichero que apunta al directorio a recorrer  
	 */	
	public static void recorrerDirectorioRecursos(File fDirectorio) {
		
		try {
			if (!fDirectorio.exists()) {
				System.out.println("No existe el directorio "+fDirectorio.getAbsolutePath());
				return;
			}
			
			File[] ficheros = fDirectorio.listFiles();
			for (int x = 0; x < ficheros.length; x++) {
				
				// Si es un directorio lo recorremos
				if (ficheros[x].isDirectory()) {			
					
					if(ficheros[x].getName().contentEquals("datos")) {
						if(!procesarCarpetaDeDatos(ficheros[x])) {
							System.out.println("Error al procesar carpeta datos situada en: " + ficheros[x].getAbsolutePath());
							return;
						}
					}
					
					recorrerDirectorioRecursos(ficheros[x]);
				
				} else if (ficheros[x].getName().endsWith(".html") || ficheros[x].getName().endsWith(".htm")) {
					// Si es un html debemos procesarlo para verificar si
					// tiene un applet de Descartes o Newton.
					// Si es Descartes o Newton se actualiza para eliminar dependencia de applet
					if (verificarAppletHTML(ficheros[x].getAbsolutePath())) {
						numAppletHTMLFiles++;
						if(!copiarFicheroJs(ficheros[x])) return;
						numCopiasJs++;
						if(actualizarFichero(ficheros[x])) numActualizacionesHTML++;
					}
				}
			}		

		} catch (Exception e) {
			System.out.println("Error recorriendo el directorio de recursos: " + e.getMessage());
		}
	}

	
	/**
	 * Metodo que procesa una carpeta 'datos'. Estas carpetas estan llenas de ficheros txt
	 * con datos para el applet de descartes. Cada uno de estos ficheros se procesan de forma espacial.
	 * @param rutaFichero
	 */
	public static boolean procesarCarpetaDeDatos (File carpetaDatos) {

		try {
			if (!carpetaDatos.exists()) {
				System.out.println("No existe la carpeta de datos "+carpetaDatos.getAbsolutePath());
				return false;
			}
			
			File[] ficheros = carpetaDatos.listFiles();
			for (int x = 0; x < ficheros.length; x++) {
				
				if (ficheros[x].isFile())
				if (ficheros[x].getName().endsWith(".txt") || ficheros[x].getName().endsWith(".TXT")) {
				
					System.out.println("\tSe revisa TXT " + ficheros[x].getAbsolutePath() + ":");
					if (procesarFicheroDatos(ficheros[x])) {
						numActualizacionesTXT++;
						System.out.println("\t\tSe ha modificado");
					} else {
						System.out.println("\t\tNo se ha modificado");
					}
				}
			}		

		} catch (Exception e) {
			System.out.println("Error al procesar carpeta datos situada en: " + carpetaDatos.getAbsolutePath());
			System.out.println(e);
			return false;
		}
		return true;
	}
	

	/**
	 * Metodo que procesa una fichero 'datos'. Estos son ficheros txt 
	 * con datos para el applet de descartes. Abriremos todos los txt buscando lineas que 
	 * especifiquen rutas a recursos. Estas lineas seran del tipo '/bla/bla.bla'.
	 * Se modificaran las rutas para que sean relativas añadiendoles un punto al inico de
	 * tal forma que queden './bla/bla.bla'
	 * Devuelve true si modifico el fichero dado, false eoc.
	 */
	public static boolean procesarFicheroDatos(File ficheroDatos) throws Exception {
		boolean ficheroModificado = false;

		StringBuilder contenido = new StringBuilder();

		//Leemos el fichero y almacenamos modificando el contenido
		BufferedReader entrada = new BufferedReader(new FileReader(ficheroDatos));
		try {
			String line = null;
			while ((line = entrada.readLine()) != null) {
				if(line.startsWith("'/")) {
					line=line.replaceFirst("'/", "'./");
					ficheroModificado=true;
				}
				contenido.append(line);
				contenido.append(newline);
			}		
			entrada.close();
			
		} catch (Exception ex) {
			entrada.close();
			System.out.println("\tError al leer del fichero "+ficheroDatos.getAbsolutePath()+": " + ex.getMessage());
			ficheroModificado=false;
			throw ex;
		}
		
		//Escribimos sobre el fichero
		Writer output = null;
		try {
			output = new BufferedWriter(new FileWriter(ficheroDatos));
			output.write(contenido.toString());
			output.close();
			
		} catch (Exception e) {
			output.close();
			System.out.println("\tError al escribir en el fichero "+ficheroDatos.getAbsolutePath()+": " + e.getMessage());
			ficheroModificado=false;
			throw e;
		} 
		
		return ficheroModificado;		
	}
	
	
	/**
	 * Este método verifica si en el fichero que se pasa como parámetro se incluye un applet del 
	 * tipo Descartes o Newton. 
	 * @param rutaFichero Ruta del fichero a evaluar si contiene un applet del tipo Descartes  
	 * @return boolean Devolverá true si contiene un applet y false si no lo contiene.
	 */
	public static boolean verificarAppletHTML(String rutaFichero) {
		
		boolean bTieneApplet=false;
		BufferedReader bfFichero = null;
		
		try {
			File fichero = new File(rutaFichero);
		
			//System.out.println("Vamos a procesar el html : " + fichero.getAbsolutePath()); 
			
			FileReader frFichero = new FileReader (fichero);
		    bfFichero = new BufferedReader(frFichero);
		    
		    String textoLinea=null;	        
		
		    while((textoLinea = bfFichero.readLine())!=null)  {
	            if (textoLinea.contains("descinst.Descartes.class"))  {
	            	System.out.println( "\tContiene applet Descartes :" + fichero.getName() );
	            	bTieneApplet=true;
	            	break;
	            }	
		    }
		    bfFichero.close();
		
		} catch (Exception e) {
			try {
				if(bfFichero!=null) bfFichero.close();
			} catch (IOException e1) {
				System.out.println("\tError al cerrar el fichero : " +e.getMessage());
				e1.printStackTrace();
			}
			System.out.println("\tError al procesar el fichero : " +e.getMessage());
			e.printStackTrace();
		}
		return bTieneApplet;
	}

	
	public static String obtieneContenidoArchivo(File archivoAbierto) {

		StringBuilder contenido = new StringBuilder();

		try {
			BufferedReader entrada = new BufferedReader(new FileReader(archivoAbierto));
			try {
				String line = null;
				while ((line = entrada.readLine()) != null) {
					contenido.append(line);
					contenido.append(newline);
				}
			} finally {
				entrada.close();
			}
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return contenido.toString();
	}
	
	
	/**
	 * Funcion auxiliar de actualizarFichero.
	 * Se encarga de examinar un fichero contenido en un string modificarlo 
	 * de tal forma que si encuentra un tag del tipo <param...>
	 * examina su interior en busca de un atributo del tipo archivo='RUTA' o archivo="RUTA",
	 * o del tipo imagen='RUTA' o imagen="RUTA". 
	 * El atributo a modificar (archivo o imagen) le viene dado como parametro de entrada.
	 * Si la RUTA especificada es absoluta y existe en esa ruta le pone un punto al inicio.
	 * Devuelve true si modifica de alguna forma el string de entrada dado en contentFile.
	 */
	public static boolean modificarTagsParam(StringBuilder contentFile, String pathFile, String atributo) {
				
		Boolean ficheroModificado=false;
		
		if(!atributo.contentEquals("archivo") && !atributo.contentEquals("imagen")) {
			System.out.println("Error al pasar el parametro atributo a modificarTagsParam. Valor pasado: "+atributo);
			return ficheroModificado;
		}
			
		int posAperturaTag=0;
		int posCierreTag=0;
		int posAux=0;
		int posAtributoArchivo1=0;
		int posAtributoArchivo2=0;
		int posIniValorAtributo=0;
		int posFinValorAtributo=0;
		
		File pathFichero = new File(pathFile);
		String path = pathFichero.getParent();
		
		posAperturaTag = contentFile.indexOf("<param", posAperturaTag);
		
		while(posAperturaTag!=-1) {
			
			//Ahora que hemos encontrado donde empieza la apertura de un 
			//tag, buscamos la posicion donde se cierra
			posCierreTag = contentFile.indexOf(">", posAperturaTag);
			
			//Ahora revisamos si el cirre de tag es realmente un cierre y no algo especificado como un atributo 
			posAux = contentFile.indexOf(">\"", posAperturaTag);
			
			//Seguimos buscando mas adelante para encontrar el verdadero cierre del tag
			while(posAux==posCierreTag) {
				if(posAux==-1) {
					System.out.println("\tError al procesar el fichero : No se encontro el cierre del tag "+contentFile.substring(posAperturaTag));
					return ficheroModificado;
				}
				posCierreTag = contentFile.indexOf(">", posCierreTag+1);			
				posAux = contentFile.indexOf(">\"", posCierreTag+1);
			}
			//System.out.println("Encontrado tag param completo: "+contentFile.substring(posAperturaTag, posCierreTag+1));
			
			//ahora que ya se en que posicion se abre y cierra una tag param, analizo su interior en busca 
			//del atributo archivo. Solo habra un atributo archivo por cada tag.
			//posAtributoArchivo1 = contentFile.indexOf("archivo='", posAperturaTag);
			posAtributoArchivo1 = contentFile.indexOf(atributo+"='", posAperturaTag);
			
			//Revisamos si el atributo archivo encontrado pertenece a nuestro parametro
			if(posAtributoArchivo1<posCierreTag && posAtributoArchivo1!=-1) {
				//Se ha encontrado el atributo. Ahora analizaremos su valor
				//En caso de ser una ruta absoluta y que esta exista fisicamente en
				//la misma carpeta del archivo que estamos analizando, la modificaremos
				//agregando un . al principio de la misma
				
				//Lo primero es buscar los limites del string valor
				posIniValorAtributo = contentFile.indexOf("'", posAtributoArchivo1);
				posFinValorAtributo = contentFile.indexOf("'", posIniValorAtributo+1);
				
				if(posFinValorAtributo>posCierreTag || posFinValorAtributo==-1){
					System.out.println("\tError al procesar el fichero : No se encontro la posicion de final del atributo "+contentFile.substring(posIniValorAtributo));
					return ficheroModificado;
				}
				
				//Extraemos el valor del atributo
				posIniValorAtributo++;
				String valorAtributoArchivo = contentFile.substring(posIniValorAtributo, posFinValorAtributo);

				//System.out.println("Encontrado valor atributo del tag param: "+valorAtributoArchivo);
						
				if(valorAtributoArchivo!=null && !valorAtributoArchivo.contentEquals("") && valorAtributoArchivo.startsWith("/")) {
				
					//System.out.println("Comprobamos si existe la ruta: "+path+valorAtributoArchivo);
					
					File f = new File(path+valorAtributoArchivo);
					if(f.exists()) {
						//Cambiamos el valor del atributo
						contentFile.replace(posIniValorAtributo, posFinValorAtributo, "."+valorAtributoArchivo);
						ficheroModificado=true;
					}
				}
				
			} else {
				
				//Probamos a buscarlo con otra sintaxis
				//posAtributoArchivo2 = contentFile.indexOf("archivo=\"", posAperturaTag);
				posAtributoArchivo2 = contentFile.indexOf(atributo+"=\"", posAperturaTag);
				
				//Revisamos si el atributo archivo encontrado pertenece a nuestro parametro
				if(posAtributoArchivo2<posCierreTag && posAtributoArchivo2!=-1) {
					
					//Se ha encontrado el atributo. Ahora analizaremos su valor
					//En caso de ser una ruta absoluta y que esta exista fisicamente en
					//la misma carpeta del archivo que estamos analizando, la modificaremos
					//agregando un . al principio de la misma
					
					//Lo primero es buscar los limites del string valor
					posIniValorAtributo = contentFile.indexOf("\"", posAtributoArchivo2);
					posFinValorAtributo = contentFile.indexOf("\"", posIniValorAtributo+1);
					
					if(posFinValorAtributo>posCierreTag || posFinValorAtributo==-1){
						System.out.println("\tError al procesar el fichero : No se encontro la posicion de final del atributo "+contentFile.substring(posIniValorAtributo));
						return ficheroModificado;
					}
					
					//Extraemos el valor del atributo
					posIniValorAtributo++;
					String valorAtributoArchivo = contentFile.substring(posIniValorAtributo, posFinValorAtributo);
					
					if(valorAtributoArchivo!=null && !valorAtributoArchivo.contentEquals("") && valorAtributoArchivo.startsWith("/")) {
											
						File f = new File(path+valorAtributoArchivo);
						if(f.exists()) {
							//Cambiamos el valor del atributo
							contentFile.replace(posIniValorAtributo, posFinValorAtributo, "."+valorAtributoArchivo);
							ficheroModificado=true;
						}
					}
					
				} 
				/*else {
					//Legados a este punto podemos asegurar que el tag param no contiene el 
					//atributo archivo asi que a otra cosa mariposa					
				}*/
			}
			
			if(posAtributoArchivo1==-1 && posAtributoArchivo2==-1) {
				//Llegados a este punto podemos asegurar que no hay nada que cambiar en el resto del fichero
				return ficheroModificado;
			}
			
			//Buscamos la apertura del siguiente tag param
			posAperturaTag = contentFile.indexOf("<param", posAperturaTag+1);
		}
		return ficheroModificado;
	}
	
	
	/**
	 * Metodo que se encarga de modificar fichero HTM/HTML para añadir referencias al javascript correcto
	 * y que ademas corrige las referencias a los tags <param> que determinan los parametros de dicho
	 * javascript.
	 * Devuelve true si ha modificado de algina forma el HTML
	 */
	public static boolean actualizarFichero(File fichero) {

		Boolean HTMLModificado=false;
		String textoIncluir = "<script type=\"text/javascript\" src=\"./"+descartesJavascript+"\"></script>";
		String contenido = obtieneContenidoArchivo(fichero);
		
		System.out.println("\tSe revisa HTML/HTM " + fichero.getAbsolutePath() + ":");
				
		//Eliminamos referencias antiguas
		contenido.replace(textoIncluir, "");

		//if (!contenido.contains(textoIncluir)) {
			if (contenido.contains("</head>")) {
				contenido = contenido.replace("</head>", textoIncluir+"</head>");
				System.out.println("\t\tSe ha modificado introduciendole la referencia al js antes del tag </head>");
				
			} else if (contenido.contains("</HEAD>")) {
				contenido = contenido.replace("</HEAD>", textoIncluir+"</HEAD>");
				System.out.println("\t\tSe ha modificado introduciendole la referencia al js antes del tag </HEAD>");
				
			} else if (contenido.contains("</html>")) {
				contenido = contenido.replace("</html>", textoIncluir+"</html>");
				System.out.println("\t\tSe ha modificado introduciendole la referencia al js antes del tag </html>");
					
			} else if (contenido.contains("</HTML>")) {
				contenido = contenido.replace("</HTML>", textoIncluir+"</HTML>");
				System.out.println("\t\tSe ha modificado introduciendole la referencia al js antes del tag </HTML>");
				
			} else {
				StringBuilder sb = new StringBuilder(contenido);
				sb.append(textoIncluir);
				contenido=sb.toString();
				System.out.println("\t\tSe ha modificado introduciendole la referencia al js al final del fichero");
			}
			HTMLModificado=true;
		//}

		StringBuilder sb2 = new StringBuilder(contenido);
		if(modificarTagsParam(sb2, fichero.getAbsolutePath(), "archivo")) { 
			HTMLModificado=true;
			System.out.println("\t\tSe ha modificado el atributo 'archivo' algun tag <param>");
		}
		if(modificarTagsParam(sb2, fichero.getAbsolutePath(), "imagen")) { 
			HTMLModificado=true;
			System.out.println("\t\tSe ha modificado el atributo 'imagen' algun tag <param>");
		}
		contenido=sb2.toString();
		
		Writer output = null;
		try {
			output = new BufferedWriter(new FileWriter(fichero));
			output.write(contenido);

		} catch (Exception e) {
			System.out.println("\tError al procesar el fichero : " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				output.close();
			} catch (Exception e) {
				output = null;
			}
		}
		if(!HTMLModificado)
			System.out.println("\t\tNo se ha modificado el fichero");
		return HTMLModificado;
	}
	
	
	/**
	 * Devuelve true si consigue copiar el js
	 */
	public static boolean copiarFicheroJs(File fichero) {
		
		System.out.println("\tSe copia el fichero js a:" + fichero.getParent());
		
//		String dirRecurso = fichero.getAbsolutePath().replace(fichero.get, arg1)
		
		File ficheroJs=new File(descartesJavascript);
		if(!ficheroJs.exists() || !ficheroJs.isFile()) {
			System.out.println("\tNo se ha encontrado el fichero "+descartesJavascript);
			return false;
		}
		String sSentencia = "cp " + descartesJavascript + " " + fichero.getParent() + System.getProperty("file.separator");

		try {
			Runtime.getRuntime().exec(sSentencia);
		} catch (Exception e) {
			System.out.println("\tError al ejecutar : " + e.getMessage());
			return false;
		}
		return true;
	}
	
	
	public static boolean backupODE(String sRutaLocalizador, String sFicheroZipBackup) {
		
		Boolean backupHecho=false;

		if(!compruebaDirectorio(sRutaLocalizador)) return false;
		
		try {
			FileOutputStream fout = new FileOutputStream(sFicheroZipBackup);
			ZipOutputStream zout = new ZipOutputStream(fout);			
			File dirComprimir = new File(sRutaLocalizador);	
			comprimirDirectorio(zout, dirComprimir,dirComprimir);			
			zout.close();			
			System.out.println("\tSe ha creado el zip de backup en "+sFicheroZipBackup);
			backupHecho=true;
			
		} catch(IOException ioe) {
			System.out.println("\tIOException backupODE :" + ioe);     
		}
		return backupHecho;
	}


	private static void comprimirDirectorio(ZipOutputStream zout,
			File fileSource, File dirODE) {

		File[] files = fileSource.listFiles();

		//System.out.println("Añadiendo directorio " + fileSource.getName());

		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				comprimirDirectorio(zout, files[i], dirODE);
				continue;
			}
			try {
				//System.out.println("Añadiendo fichero " + files[i].getName());

				byte[] buffer = new byte[1024];

				FileInputStream fin = new FileInputStream(files[i]);
				String nombreEntrada = files[i].getAbsolutePath().replace(dirODE.getAbsolutePath() + "\\", "");
				
				//System.out.println("Entrada : " + nombreEntrada);
				zout.putNextEntry(new ZipEntry(nombreEntrada));

				int length;

				while ((length = fin.read(buffer)) > 0) {
					zout.write(buffer, 0, length);
				}

				zout.closeEntry();
				fin.close();
				
			} catch (IOException ioe) {
				System.out.println("\tIOException comprimirDirectorio :" + ioe);
			}
		}
	}
	
}
