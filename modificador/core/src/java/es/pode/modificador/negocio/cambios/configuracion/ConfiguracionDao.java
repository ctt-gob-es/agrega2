/**
 * 
 */
package es.pode.modificador.negocio.cambios.configuracion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import org.apache.log4j.Logger;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.InputSource;

import es.pode.modificador.negocio.cambios.configuracion.castor.ModificationTask;

/**
 * @author fjespino
 *
 */
public class ConfiguracionDao {

	private static final String defaultEcoding = "UTF-8";
	private static final Logger logger = Logger.getLogger(ConfiguracionDao.class);
	
	public ConfiguracionDao() {
		
	}
	
	public ModificationTask leerConfiguracion(String file) throws ConfiguracionDaoException {
		File fFile = new File(file);
		ModificationTask task = null;
		if(fFile.isFile()) {
			task = leerConfiguracion(fFile);
		} else {
			throw new ConfiguracionDaoException("El fichero " + file + " no existe o no es un fichero");
		}
		return task;
	}
	
	public ModificationTask leerConfiguracion(File file) throws ConfiguracionDaoException {
		ModificationTask task = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			task = leerConfiguracion(fis);
		} catch (IOException e) {
			throw new ConfiguracionDaoException(e.getMessage(),e);
		} finally {
			try {
				if(fis!=null) fis.close();
			} catch (Exception e) {
				// No hacer nada
			}
		}
		return task;
	}
	
	public ModificationTask leerConfiguracion(Reader reader) throws ConfiguracionDaoException {
		ModificationTask task = null;
		InputSource isrc = new InputSource(reader);
		task = leerConfiguracion(isrc);
		return task;
	}
	
	public ModificationTask leerConfiguracion(InputStream is) throws ConfiguracionDaoException {
		ModificationTask task = null;
		InputSource isrc = new InputSource(is);
		task = leerConfiguracion(isrc);
		return task;
	}
	
	public ModificationTask leerConfiguracion(InputSource iSrc) throws ConfiguracionDaoException {
		ModificationTask task = null;
		
		Unmarshaller parser = new Unmarshaller(ModificationTask.class);
		parser.setValidation(false);
		try {
			task = (ModificationTask)parser.unmarshal(iSrc);
		} catch (MarshalException e) {
			throw new ConfiguracionDaoException(e.getMessage(),e);
		} catch (ValidationException e) {
			throw new ConfiguracionDaoException(e.getMessage(),e);
		}
		return task;
	}
	
	public void guardarConfiguracion(ModificationTask task, String file) throws ConfiguracionDaoException {
		chequearGuardar(task, file);
		File fFile = new File(file);
		
		if(fFile.isDirectory()) {
			String msg = "No se puede crear el fichero " + file + ". Ya existe una carpeta con el mismo nombre.";
			logger.error(msg);
			throw new ConfiguracionDaoException(msg); 
		} else if(fFile.exists()) {
			try {
				fFile.createNewFile();
			} catch (IOException e) {
				String msg = "No se puede crear el fichero " + file;
				logger.error(msg, e);
				throw new ConfiguracionDaoException(msg,e);
			}
		}
		
		guardarConfiguracion(task, fFile);
	}
	
	private void chequearGuardar(ModificationTask task, java.lang.Object dest) throws ConfiguracionDaoException {
		if(task == null) {
			logger.error("El objeto ModificationTask no puede ser null");
			throw new ConfiguracionDaoException("El objeto ModificationTask no puede ser null");
		}
		if(dest == null) {
			logger.error("El destino no puede ser null");
			throw new ConfiguracionDaoException("El destino no puede ser null");
		}
	}
	
	public void guardarConfiguracion(ModificationTask task, File file) throws ConfiguracionDaoException {
		chequearGuardar(task, file);
		OutputStreamWriter osw = null;
		OutputStream os = null;
		try {
			os = new FileOutputStream(file);
			osw = new OutputStreamWriter(os,defaultEcoding);
			guardarConfiguracion(task, osw);
		} catch (IOException e) {
			String msg = "No se puede crear el fichero " + file;
			logger.error(msg,e);
			throw new ConfiguracionDaoException(msg,e);
		} finally {
			try {
				if(osw!=null) {
					osw.close();
				}
				if(os!=null) {
					os.close();
				}
			} catch (Exception e) {
				// No hacer nada
			}
		}
		
	}
	
	public void guardarConfiguracion(ModificationTask task, Writer writer) throws ConfiguracionDaoException {
		
		chequearGuardar(task, writer);

		try {
			Marshaller marshaller = new Marshaller(writer);
			marshaller.setEncoding(defaultEcoding);
			// TODO: incluir schemaLocation
			marshaller.marshal(task);
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
			throw new ConfiguracionDaoException(e.getMessage(),e);
		} catch (ValidationException e) {
			logger.error(e.getMessage(),e);
			throw new ConfiguracionDaoException(e.getMessage(),e);
		} catch (MarshalException e) {
			logger.error(e.getMessage(),e);
			throw new ConfiguracionDaoException(e.getMessage(),e);
		}
	}
	
}
