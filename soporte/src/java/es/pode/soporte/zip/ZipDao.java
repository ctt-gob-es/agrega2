/**
 * 
 */
package es.pode.soporte.zip;

/**
 * Interfaz que ofrece los m�todos necesarios para la gesti�n de archivos
 * comprimidos.
 * 
 * @author fjespino
 * 
 */
public interface ZipDao {

	/**
	 * Descomprime el archivo <em>rutaArchivo</em> en la localizacion definida
	 * por <em>rutaDestino</em>.
	 * 
	 * @param rutaArchivo
	 * @param rutaDestino
	 */
	public void descomprimir(String rutaArchivo, String rutaDestino)
			throws ZipDaoException;

	/**
	 * Comprime todos los ficheros / carpetas bajo <em>rutaOrigen</em> en el
	 * archivo comprimido <em>rutaArchivo</em>. Las entradas se crean con
	 * rutas relativas a <em>rutaOrigen</em>.
	 * 
	 * @param rutaArchivo
	 * @param rutaOrigen
	 */
	public void comprimir(String rutaArchivo, String rutaOrigen)
			throws ZipDaoException;

	/**
	 * 
	 * Comprime todos los ficheros / carpetas bajo <em>rutaOrigen</em> en el
	 * archivo comprimido <em>rutaArchivo</em> excluyendo los ficheros /
	 * carpetas del array <em>exclude</em>. Los fichero a excluir son rutas
	 * relativas a <em>rutaOrigen</em>.
	 * 
	 * @param rutaArchivo
	 * @param rutaOrigen
	 * @param exclude
	 * @throws ZipDaoException
	 * @throws Exception 
	 */
	public void comprimir(String rutaArchivo, String rutaOrigen,
			String[] exclude) throws ZipDaoException, Exception;

	/**
	 * Comprueba la existencia del fichero / carpeta <em>entrada</em> en el
	 * archivo comprimido <em>archivo</em>.
	 * 
	 * @param entrada
	 * @param archivo
	 * @return True si la entrada existe en el archivo, False en caso contrario.
	 */
	public boolean chequearEntrada(String entrada, String archivo);

	/**
	 * Comprueba si el fichero <em>archivo</em> es un archivo comprimido que
	 * pueda ser gestionado por este DAO.
	 * 
	 * @param archivo
	 * @return True si el archivo es un archivo comprimido compatible, False en
	 *         caso contrario.
	 */
	public boolean esZip(String archivo);

	/**
	 * Extrae un fichero contenido en un archivo a la carpeta destino
	 * especificada. Si la entrada del fichero especifica una ruta relativa, se
	 * crear� la ruta bajo la carpeta destino.
	 * 
	 * @param fichero
	 *            Fichero a extraer.
	 * @param archivo
	 *            Archivo comprimido.
	 * @param destino
	 *            Carpeta destino donde extraer el fichero (si la entrada del
	 *            fichero especifica una ruta relativa, se crear� la ruta bajo
	 *            la carpeta destino).
	 */
	public void extraerFichero(String fichero, String archivo, String destino);
}
