/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.contenidos.negocio.descargas.servicio;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import org.apache.log4j.Logger;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.contenidos.negocio.descargas.dominio.CategoriaDescarga;
import es.pode.contenidos.negocio.descargas.dominio.CategoriaDescargaDao;
import es.pode.contenidos.negocio.descargas.dominio.DescDescarga;
import es.pode.contenidos.negocio.descargas.dominio.DescDescargaCriteria;
import es.pode.contenidos.negocio.descargas.dominio.DescDescargaDaoBase;
import es.pode.contenidos.negocio.descargas.dominio.Descarga;
import es.pode.contenidos.negocio.descargas.dominio.DescargaCriteria;
import es.pode.contenidos.negocio.descargas.dominio.DescargaDao;
import es.pode.contenidos.negocio.descargas.dominio.DescargaDaoBase;
import es.pode.contenidos.negocio.descargas.dominio.DescargasCategoriaCriteria;
import es.pode.soporte.utiles.ficheros.UtilesFicheros;


/**
 * @see es.pode.contenidos.negocio.descargas.servicio.SrvDescargas
 */

public class SrvDescargasImpl
    extends es.pode.contenidos.negocio.descargas.servicio.SrvDescargasBase
{
	private static final Logger logger = Logger.getLogger(SrvDescargasImpl.class);
	private static Properties props = new Properties();
	private static String UPLOADS="uploads/";
	private static String pathDescargas = "descargas/";
	private static String VACIA ="";

	
	
    public SrvDescargasImpl() {
		super();
		java.io.InputStream is = this.getClass().getResourceAsStream(
		"/descargas.properties");
		try {
			if (is != null)
				props.load(is);
		} catch (IOException e) {
			logger.error("No se pudo cargar el fichero descargas.properties");
		}
	}

    /**
     * Modifica o crea una Descarga. Si no se indica identificador, se
     * creará una nueva.
     * @param identificador Identificador de la Descarga
     * @param path Ruta en servidor del fichero a descargar
     * @param activa Si es true, la Descarga aparecerá en la lista de Descargas disponibles
     * @param descripciones Vector de DescDescargas, pares de título y descripción para cada idioma definido
     * @return identificador de la Descarga modificada.
     */
    protected java.lang.Long handleModificarDescarga(java.lang.Long identificador, java.lang.String path, java.lang.Boolean activa, DescDescargaVO[] descripciones)
        throws java.lang.Exception
    {
    	Long salida=null;
    	try {
			Descarga descarga;
			if(identificador!=null) {
				logger.debug("Se indico identificador, intentamos recuperar Descarga.");
				descarga=getDescargaDao().load(identificador);
			} else {
				logger.debug("No se indico identificador de Descarga, creamos una nueva.");
				if (path!=null&&!path.equals("")) {
					if (new File(path).exists()) {
//						descarga = getDescargaDao().create(path, Boolean.TRUE,
//								new Date(), 0L);
						descarga=getDescargaDao().create(Boolean.TRUE, getCategoriaDescargaDao().load(Long.parseLong(props.getProperty("categoria.por_defecto"))), new Date(), path, 0L);
						identificador = descarga.getIdentificador();
					} else {
						logger.debug("No se encontró fichero para Descarga nueva");
						return identificador;
					}
				} else {
					logger.debug("No se indicó ruta para Descarga nueva.");
					return identificador;
				}
			}
			
			if(activa!=null) {
				logger.debug("Cambiamos actividad a "+activa);
				descarga.setActiva(activa);
			}
			
			if(descripciones!=null&&descripciones.length>0) {
				logger.debug("Hay descripciones");
				DescDescarga[] descripcionesEntidades = new DescDescarga[descripciones.length];
				for (int i = 0; i < descripciones.length; i++) {
					descripcionesEntidades[i]=(DescDescarga)getBeanMapper().map(descripciones[i], DescDescarga.class);
					descripcionesEntidades[i].setDescarga(descarga);
					String idioma=descripcionesEntidades[i].getIdioma();
					DescDescarga descripcionPrevia=obtenerDescDescargaIdioma(descarga, idioma);
					if(descripcionPrevia!=null) {
						logger.debug("Existía descripción para idioma "+idioma);
						if ((descripcionesEntidades[i].getDescripcion()!=null&&!descripcionesEntidades[i].getDescripcion().equals(VACIA))
								&&(descripcionesEntidades[i].getTitulo()!=null)&&!descripcionesEntidades[i].getTitulo().equals(VACIA)) {
							//Actualización
							logger.debug("Actualizamos descripcion para "+idioma);
							descripcionPrevia
									.setDescripcion(descripcionesEntidades[i]
											.getDescripcion());
							descripcionPrevia
									.setTitulo(descripcionesEntidades[i]
											.getTitulo());
							getDescDescargaDao().update(descripcionPrevia);
						} else {
							logger.debug("Borramos descripcion para "+idioma);
							//Borrado de Descripción
							getDescDescargaDao().remove(descripcionPrevia);
						}
					} else {
						logger.debug("No existía descripción para idioma "+idioma);
						if ((descripcionesEntidades[i].getDescripcion()!=null&&!descripcionesEntidades[i].getDescripcion().equals(VACIA))
								&&(descripcionesEntidades[i].getTitulo()!=null)&&!descripcionesEntidades[i].getTitulo().equals(VACIA)) {
						//Creación
							logger.debug("Creamos descripción con desc= "+descripcionesEntidades[i].getDescripcion()+" y titulo= "+descripcionesEntidades[i].getTitulo());
							getDescDescargaDao().create(descripcionesEntidades[i]);
						} else logger.debug("No creamos descripcion para idioma "+idioma);
					}
				}
				
			}
			
			//Path no es el definitivo, se debe crear otro y copiar contenidos
			if(path!=null&&!path.equals("")) {
				String errorMsg="Error al copiar fichero";
				try {
					File fichero = new File(path);
					if(fichero.isDirectory()) {
						logger.error(path+" es una ruta, no un fichero.");
						return salida;
					}
					if (fichero.exists()) {
//						descarga.setPeso(new Long(fichero.length()));
						descarga.setPeso(Long.valueOf(fichero.length()));
						logger.debug("El fichero tiene un peso de "
								+ descarga.getPeso() + " bytes");
						String nuevaRuta = UPLOADS+pathDescargas
								+ identificador.toString();
						File nuevoFile = new File(nuevaRuta);
						if (!nuevoFile.exists()&&!nuevoFile.mkdirs()) {
							logger.error("No se pudo crear directorio "
									+ nuevaRuta);
							return salida;
						}
						logger.debug("Se creo la ruta " + nuevaRuta);
						UtilesFicheros.copiar(fichero, nuevoFile);
						logger.debug("Se copio " + path + " en " + nuevaRuta);
						path = pathDescargas+identificador.toString() + "/" + fichero.getName();
					} else {
						logger.error("No se encontró fichero "+fichero.getAbsolutePath());
					}
					descarga.setPath(path);
					descarga.setFecha(new Date());
					
				} catch (Exception e) {
					logger.error(errorMsg,e);
					return identificador;
				}
			}
			descarga.setFecha(new Date());
			getDescargaDao().update(descarga);
			logger.debug("Se modifico descarga "+identificador);
			return identificador;
		} catch (Exception e) {
			logger.debug("Se ha producido un error al modificar",e);
		}
        return identificador;
    }

    /**
     * Devuelve vector de Descargas cuyo estado sea activo
     * @return vector de Descargas cuyo estado sea activo
     */
    protected es.pode.contenidos.negocio.descargas.servicio.DescargaVO[] handleObtenerDescargasActivas()
        throws java.lang.Exception
    {
    	DescargaCriteria criteria = new DescargaCriteria();
    	criteria.setActiva(Boolean.TRUE);
    	criteria.setFecha(null);
    	Collection coleccion=getDescargaDao().obtenerDescargasPorEstado(DescargaDaoBase.TRANSFORM_DESCARGAVO,criteria);
        
    	return coleccion!=null?(DescargaVO[])coleccion.toArray(new DescargaVO[]{}):new DescargaVO[]{};
    }

    /**
     * Devuelve vector de Descargas cuyo estado sea no activo
     * @return vector de Descargas cuyo estado sea no activo
     */
    protected es.pode.contenidos.negocio.descargas.servicio.DescargaVO[] handleObtenerDescargasNoActivas()
        throws java.lang.Exception
    {
    	DescargaCriteria criteria = new DescargaCriteria();
    	criteria.setActiva(Boolean.FALSE);
    	criteria.setFecha(null);
    	Collection coleccion=getDescargaDao().obtenerDescargasPorEstado(DescargaDaoBase.TRANSFORM_DESCARGAVO,criteria);
        return coleccion!=null?(DescargaVO[])coleccion.toArray(new DescargaVO[]{}):new DescargaVO[]{};
    }

    /**
     * Devuelve Descarga correspondiente al identificador dado
     * @param identificador Identificador de la Descarga
     * @return Descarga correspondiente al identificador dado
     */
    protected es.pode.contenidos.negocio.descargas.servicio.DescargaVO handleObtenerDescarga(java.lang.Long identificador)
        throws java.lang.Exception
    {
    	return (DescargaVO)getDescargaDao().load(DescargaDaoBase.TRANSFORM_DESCARGAVO,identificador);
    }

    /**
     * Añade un par título/descripción nuevo a la Descarga indicada
     * @param identificador Identificador de la Descarga
     * @param idioma  Código ISO de idioma.
     * @param titulo Texto de título de la Descarga
     * @param desc Texto de la descripción de la Descarga
     */
	protected void handleInsertarDescDescarga(Long identificador,
			String idioma, String titulo, String desc) throws Exception {
		Collection descargas = getDescargaDao().load(identificador).getDescripcion();
		DescDescarga descargaNueva = getDescDescargaDao().create(idioma, titulo, desc);
		descargas.add(descargaNueva);
		getDescargaDao().load(identificador).setDescripcion(descargas);
	}

	/**
	 * Añade varios pares título/descripción nuevos a la Descarga indicada
	 * @param identificador Identificador de la Descarga
	 * @param descripciones Lista de títulos y descripciones para los idiomas indicados
	 */
	protected void handleInsertarDescDescargas(Long identificador,
			DescDescargaVO[] descripciones) throws Exception {
		Collection descargas = getDescargaDao().load(identificador).getDescripcion();
		for (int i = 0; i < descripciones.length; i++) {
			DescDescarga descargaNueva = getDescDescargaDao().fromDescDescargaVO(descripciones[i]);
			descargas.add(descargaNueva);
		}
		getDescargaDao().load(identificador).setDescripcion(descargas);
	}
	
	/**
	 * Devuelve vector de pares título/descripción para todos los idiomas definidos para la Descarga de identificador indicado
	 * @param identificador Identificador de la Descarga
	 * @return vector de pares título/descripción para todos los idiomas definidos para la Descarga de identificador indicado
	 */
	protected DescDescargaVO[] handleObtenerDescDescargas(Long identificador)
			throws Exception {
		DescDescargaCriteria criteria = new DescDescargaCriteria();
		criteria.setIdentificador(identificador);
		Collection coleccion = getDescDescargaDao().obtenerDescDescarga(DescDescargaDaoBase.TRANSFORM_DESCDESCARGAVO, criteria);
		return (DescDescargaVO[])coleccion.toArray(new DescDescargaVO[]{});
	}

	/**
	 * Devuelve DataHandler para descargar fichero indicado
	 * @param identificador Identificador de la Descarga
	 * @return DataHandler para descargar fichero indicado
	 */
	protected DataHandler handleDescargar(Long identificador) throws Exception {
		DataHandler salida=null;
		String error="No se pudo recuperar el DataHandler de la Descarga";
		try {
			String path = getDescargaDao().load(identificador).getPath();
			File fichero = new File(path);
			logger.debug("Preparando para descargar: El path absoluto es "+fichero.getAbsolutePath());
			if(fichero.canRead()) {
				salida = new DataHandler(new FileDataSource(path));
			} else {
				logger.debug(error);
			}
		} catch (Exception e) {
			logger.error(error,e);
		} 
		return salida;
	}

	/**
	 * Devuelve lista de pares título/descripción de todas las Descargas para un idioma concreto
	 * @param descargas Lista de identificadores de Descargas
	 * @param idioma Código ISO de idioma
	 * @return Lista de pares título/descripción de todas las Descargas para un idioma concreto
	 */
	protected DescDescargaVO[] handleObtenerDescDescargasIdioma(
			DescargaVO[] descargas, String idioma) throws Exception {
		DescDescargaCriteria criteria = new DescDescargaCriteria();
		Collection descripcionesPorDescarga, descripcionesTotales;
		descripcionesTotales = new ArrayList();
		for (int i = 0; i < descargas.length; i++) {
			criteria.setIdentificador(descargas[i].getIdentificador());
			descripcionesPorDescarga=getDescDescargaDao().obtenerDescDescarga(DescDescargaDaoBase.TRANSFORM_DESCDESCARGAVO, criteria);
			DescDescargaVO desc =null;
			for (Iterator iterator = descripcionesPorDescarga.iterator(); iterator
					.hasNext();) {
				desc = (DescDescargaVO) iterator.next();
				if(desc.getIdioma().equals(idioma)) {
					break;
				}
			}
			descripcionesTotales.add(desc);
		}
		return descripcionesTotales!=null?(DescDescargaVO[])descripcionesTotales.toArray(new DescDescargaVO[]{}):new DescDescargaVO[]{};
	}
	
	private DescDescarga obtenerDescDescargaIdioma(Descarga descarga, String idioma) {
		DescDescargaCriteria criteria = new DescDescargaCriteria();
		Collection descripcionesPorDescarga;
		DescDescarga descripcion=null;
		criteria.setIdentificador(descarga.getIdentificador());
		descripcionesPorDescarga=getDescDescargaDao().obtenerDescDescarga(criteria);
		for (Iterator iterator = descripcionesPorDescarga.iterator(); iterator
				.hasNext();) {
			DescDescarga desc = (DescDescarga) iterator.next();
			if(desc.getIdioma().equals(idioma)) {
				descripcion=desc;
			}
		}
		return descripcion;
	}

	/**
	 * Elimina Descargas correspondientes a los identificadores dados
	 * @param identificadores Lista de identificadores de Descarga
	 */
	protected void handleEliminarDescargas(Long[] identificadores)
			throws Exception {
		for (int i = 0; i < identificadores.length; i++) {
			try {
				//Elimino ruta
				Descarga descarga = getDescargaDao().load(identificadores[i]);
				logger.debug("Empezamos eliminación de Descarga "+descarga.getIdentificador());
				Collection descripciones =descarga.getDescripcion();
				getDescDescargaDao().remove(descripciones);
				logger.debug("Se eliminaron descripciones de Descarga "+descarga.getIdentificador()+" de tablas.");
				//Elimino instancia en BBDD
				getDescargaDao().remove(identificadores[i]);
				logger.info("Descarga "+descarga+" eliminada de tablas");
				
				File path = new File(descarga.getPath());
				UtilesFicheros.eliminar(path);
				if(!path.delete()) {
					logger.warn("No se ha podido eliminar la carpeta " + path.getName() + " ("+path.getPath()+")");
				}
				logger.debug("Eliminada ruta "+descarga.getPath()+" de Descarga "+descarga);

			} catch (Exception e) {
				logger.error("No se pudo eliminar descarga.",e);
				if(logger.isDebugEnabled()) logger.debug(e);
			}
		}

	}

	/**
	 * Añade una categoría nueva a la Descarga indicada
	 * 
	 * @param identificador Identificador de Descarga
	 * @param id identificador de categoria
	 * @see es.pode.contenidos.negocio.descargas.servicio.SrvDescargasBase#handleInsertarCategoriaDescarga(java.lang.Long, java.lang.Long)
	 */
	protected void handleInsertarCategoriaDescarga(Long identificador, Long id) throws Exception {
//		CategoriaDescargaVO categoria = (CategoriaDescargaVO)getCategoriaDescargaDao().load(CategoriaDescargaDao.TRANSFORM_CATEGORIADESCARGAVO, id);
//		handleInsertarCategoriasDescarga(identificador, new CategoriaDescargaVO[]{categoria});
		
		CategoriaDescarga categoria = getCategoriaDescargaDao().load(id);
		logger.debug("Recuperamos categoria "+categoria.getNombre());
		Descarga descarga = getDescargaDao().load(identificador);
//		Collection descargas=categoria.getDescarga();
//		logger.debug("Antes:");
//		for (Iterator iterator = descargas.iterator(); iterator.hasNext();) {
//			Descarga descar = (Descarga) iterator.next();
//			logger.debug("Descarga "+descar.getIdentificador());
//		}
//		descargas.add(descarga);
//		logger.debug("Despues");
//		for (Iterator iterator = descargas.iterator(); iterator.hasNext();) {
//			Descarga descar = (Descarga) iterator.next();
//			logger.debug("Descarga "+descar.getIdentificador());
//		}
//		categoria.setDescarga(descargas);
		descarga.setCategoria(categoria);
//		getCategoriaDescargaDao().update(categoria);
		getDescargaDao().update(descarga);
	}

	@Override
	protected CategoriaDescargaVO handleObtenerCategoriaDescarga(
			Long identificador) throws Exception {
		CategoriaDescarga categoria=getDescargaDao().load(identificador).getCategoria();
		return getCategoriaDescargaDao().toCategoriaDescargaVO(categoria);
	}

	@Override
	protected CategoriaDescargaVO[] handleObtenerCategorias() throws Exception {
		Collection categorias=getCategoriaDescargaDao().loadAll(CategoriaDescargaDao.TRANSFORM_CATEGORIADESCARGAVO);
		return (CategoriaDescargaVO[])categorias.toArray(new CategoriaDescargaVO[]{});
	}

	@Override
	protected DescargaVO[] handleObtenerDescargasPorCategoria(Long idCategoria)
			throws Exception {
		DescargasCategoriaCriteria criteria = new DescargasCategoriaCriteria();
		criteria.setId(idCategoria);
		criteria.setActiva(Boolean.TRUE);
		Collection descargas=getDescargaDao().obtenerDescargasPorCategoria(DescargaDao.TRANSFORM_DESCARGAVO, criteria);
		return (DescargaVO[])descargas.toArray(new DescargaVO[]{});
	}

	@Override
	protected String handleObtenerVersion() {
		return AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.VERSION);
	}

	@Override
	protected void handleEliminarCategoriaDesc(String titulo, String idioma,
			Long categoria) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void handleInsertarCategoriaDesc(String titulo, String idioma)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void handleModificarCategoriaDesc(String titulo, String idioma)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected CategoriaDescargaDescVO handleObtenerCategoriaDesc(String idioma,
			Long categoria) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}

