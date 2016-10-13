/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * 
 */
package es.pode.soporte.zip;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import de.schlichtherle.io.ArchiveDetector;
import de.schlichtherle.io.File;

/**
 * @author fjespino y mcmegia
 * 
 */
public  class TrueZipDaoImpl implements ZipDao {

	private static Logger logger = Logger.getLogger(TrueZipDaoImpl.class);
	private static ZipDao zipDao; 

	private void umount() {
		try {
			File.umount(true);
		} catch (Exception e) {
			logger.error("Error al actualizar true-zip VFS", e);
		}
	}

	// FUNCIONA
	public boolean chequearEntrada(String entrada, String archivo) {
		boolean chequeoCorrecto = false;
		File entrad = new File(archivo, entrada);

		if (entrad.exists()) {
			chequeoCorrecto = true;
		}
		umount();
		return chequeoCorrecto;
	}

	// FUNCIONA
	public void comprimir(String rutaArchivo, String rutaOrigen)
			throws ZipDaoException {
		logger.debug("Nos disponemos a comprimir " + rutaOrigen);
		File archivo = new File(rutaArchivo);
		boolean resultado = archivo.copyAllFrom(new java.io.File(rutaOrigen),ArchiveDetector.NULL,ArchiveDetector.NULL);
		if (!resultado)
			logger.warn("No se pudo comprimir " + rutaOrigen + " en " + rutaArchivo);
		umount();
	}

	// FUNCIONA
	public void descomprimir(String rutaArchivo, String rutaDestino)
			throws ZipDaoException {
		File comprimido = new File(rutaArchivo);
		java.io.File destino = new java.io.File(rutaDestino);
		if (!destino.isDirectory()) {
			logger.warn("El destino <" + rutaDestino + "> no es un directorio");
			throw new ZipDaoException("El destino " + rutaDestino + " no es un directorio");
		}
		if (comprimido.exists()) {
			boolean resultado = comprimido
					.archiveCopyAllTo(new File(rutaDestino),
							ArchiveDetector.NULL, ArchiveDetector.NULL);
			if (!resultado) {
				logger.error("No se pudo descomprimir el archivo " + rutaArchivo + " en el directorio " + rutaDestino);
				throw new ZipDaoException("No se pudo descomprimir el archivo " + rutaArchivo + " en el directorio " + rutaDestino);
			}
		} else {
			logger.debug("El archivo <" + rutaArchivo + "> no existe");
			throw new ZipDaoException("El archivo <" + rutaArchivo + "> no existe");
		}
		umount();
	}

	// FUNCIONA
	public boolean esZip(String archivo) {
		boolean zip = false;
		File File1 = new File(archivo);

		if ((File1.isArchive() && File1.isDirectory() && !File1.isFile()
				&& File1.exists() && File1.length() == 0)) {
			zip = true;
		}
		umount();
		return zip;
	}

	// FUNCIONA
	public void extraerFichero(String fichero, String archivo, String destino) {
		String path = archivo + "/" + fichero;
		File entradaZip = new File(path);
		java.io.File ficheroDescomprimido = new java.io.File(destino + "/"
				+ fichero);

		try {
			if (entradaZip.isFile()) {
				logger.debug("El fichero " + path
						+ " NO ES CARPETA y lo vamos a descomprimir");
				File.cp_p(entradaZip, ficheroDescomprimido);
			} else if (entradaZip.isDirectory()) {
				logger.debug("El fichero " + path
						+ " ES CARPETA y lo vamos a descomprimir");
				destino = destino + "/" + fichero;
				new File(path).archiveCopyAllTo(new File(destino),
						ArchiveDetector.DEFAULT, ArchiveDetector.NULL);
			}

		} catch (IOException e) {
			logger.error(e);
		}

	}

	// FUNCIONA
	public void comprimir(String rutaArchivo, String rutaOrigen,
			String[] exclude) throws Exception 
	{

		logger.debug("Se crea el comprimido del que se quitaran los ficheros indicados en <"	+ rutaArchivo+">");
		comprimir(rutaArchivo, rutaOrigen);

		// Si hay ficheros a excluir de la compresión
		if (exclude != null && exclude.length>0) {
			excluirFichero(rutaArchivo, exclude);
		}
		umount();
	}

	private void excluirFichero(String ruta, String[] exclude)
	{
		try{
			// compruebo que la ruta no esta vacia o nula
			if(ruta!=null && !ruta.equals(""))
			{
				File ficheroFile= new File(ruta);
				File[] listaEliminar;
				// si es un directorio.. compruebo la lista de excluidos
				// si es un fichero.. no hago nada
				if(ficheroFile.exists() && ficheroFile.isDirectory() )
				{
					// obtengo una lista de ficheros a eliminar del zip utilizando un filtro
					listaEliminar= (File[]) ficheroFile.listFiles(new FiltroNombres(exclude));
					//elimino los ficheros listados por el filtro
					for (int j = 0; j < listaEliminar.length; j++) {
						// elimino el fichero del zip
						logger.debug("elimino el fichero: " + listaEliminar[j].getCanOrAbsPath());
						listaEliminar[j].delete();
					}
					// verifico si el la ruta actual tiene subdirectorios
					String[] listaSubdirectorios = ficheroFile.list();
					for (int i = 0; i < listaSubdirectorios.length; i++) 
					{
						// realizo una llamada recursiva para los subdirectorios
						excluirFichero(ficheroFile.getCanOrAbsPath()+ "/" + listaSubdirectorios[i], exclude);
					}
					// si el directorio actual está vacío, lo elimino
					if(ficheroFile.list().length==0 )
						ficheroFile.deleteAll();
				}
			}
		}catch (Exception e) {
			logger.error("Error en método TrueZipDaoImpl:excluirFichero:  ", e);
		}
		
	}
	
	/**
	 * Habilito posibilidad de singleton
	 * @return Implementación de ZipDao
	 * @author dgonzalezd
	 * //TODO Hacer privado el constructor por defecto 
	 */
	public static ZipDao getInstance() {
		if (zipDao==null) zipDao= new TrueZipDaoImpl();
		return zipDao;
	}
}
