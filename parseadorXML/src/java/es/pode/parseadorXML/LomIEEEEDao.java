package es.pode.parseadorXML;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import net.sf.dozer.util.mapping.MapperIF;

import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.InputSource;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.parseadorXML.castor.Educational;
import es.pode.parseadorXML.castor.General;
import es.pode.parseadorXML.castor.MetaMetadata;
import es.pode.parseadorXML.castor.Rights;
import es.pode.parseadorXML.castor.lomieee.Lom;
import es.pode.parseadorXML.lomes.lomesAgrega.ClassificationAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.EducationalAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.GeneralAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.IdentificadorAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.LifeCycleAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.LomAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.MetaMetadataAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.RightsAgrega;


public class LomIEEEEDao {

	private MapperIF beanMapper = null;

	public MapperIF getBeanMapper() {
		return beanMapper;
	}

	public void setBeanMapper(MapperIF beanMapper) {
		this.beanMapper = beanMapper;
	}
	
	private static Properties props = null;
	
	
	public String getProperty(String key) throws ParseadorException {
		if(props==null) {
			InputStream is = this.getClass().getResourceAsStream("/parseadorXML.properties");
			props = new Properties();
			try {
				props.load(is);
			} catch (Exception e) {
				throw new ParseadorException(e.getMessage(),e);
			}
		}
		String value = props.getProperty(key);
		return value;
	}
	

	public Lom parsearLom(InputStream is) throws ParseadorException {
		InputSource isrc = null;
		isrc = new InputSource(is);
		return parsearLom(isrc);
	}
	
	
	public Lom parsearLom(File file) throws ParseadorException {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			return parsearLom(fis);
		} catch (IOException e) {
			throw new ParseadorException(e.getMessage(),e);
		} finally {
			try {
				if(fis!=null) fis.close();
			} catch (Exception e){
				// No hago nada con esta excepcion
			}
		}
		
	}
	
	
	public Lom parsearLom(Reader is) throws ParseadorException {
		InputSource isrc = null;
		isrc = new InputSource(is);
		return parsearLom(isrc);
	}
	
	
	public Lom parsearLom(String path) throws ParseadorException {
		File file = new File(path);
		Lom result = null;
		if(file.isFile()) {
			result = parsearLom(file);
		} else {
			throw new ParseadorException("El path " + path + " no apunta a un descriptor de metadato LOM-IEEE");
		}
		return result;
	}
	
	
	/**
	 * @param lomfile
	 * @return
	 */
	public Lom parsearLom(InputSource input) throws ParseadorException {
		// Abrir fichero xml para su lectura.
		
		Lom lomCastor = null;		
		try {
			// Parsear XML
			Unmarshaller unmarshaller = new Unmarshaller(Lom.class);
			unmarshaller.setValidation(false);
			lomCastor = (Lom)unmarshaller.unmarshal(input);
			// Cerrar fichero
			
		} catch (MarshalException e) {
			throw new ParseadorException("Error en el parseo del fichero lom-ieee", e);
		} catch (ValidationException e) {
			throw new ValidacionException("Error de validacion en el parseo del fichero lom-ieee", e);
		} 
		return lomCastor;
	}		
	
	
	/*
	 * Metodo que desde un objeto de tipo lom-ieee genera un lom-es
	 */
	private es.pode.parseadorXML.castor.Lom generarLomEs(es.pode.parseadorXML.castor.lomieee.Lom lomieee, String catalogoAgrega, String idCatalogoAgrega) throws Exception {

		es.pode.parseadorXML.castor.Lom lomes = new es.pode.parseadorXML.castor.Lom();
		LomAgrega la = new LomAgrega(lomes);		
		
		// LOM 1 - General
		GeneralAgrega ga = new GeneralAgrega(new General());
		// Titulo		
		String titulo = lomieee.getGeneral().getTitle().getLangstring(0).getContent();
		String idioma = lomieee.getGeneral().getLanguage(0);
		ga.addTituloIdioma(idioma, titulo);
		// Identificador de agrega
		IdentificadorAgrega idAgrega = new IdentificadorAgrega();
		idAgrega.setCatalogo(catalogoAgrega);
		idAgrega.setEntrada(idCatalogoAgrega);
		ArrayList<IdentificadorAgrega> lista = new ArrayList<IdentificadorAgrega>();
		lista.add(idAgrega);
		// Identificador dado
		String catalogo = lomieee.getGeneral().getCatalogentry(0).getContent();
		String idCatalogo = lomieee.getGeneral().getCatalogentry(0).getEntry().getLangstring(0).getContent();
		if(catalogo!=null && !catalogo.isEmpty() && idCatalogo!=null && !idCatalogo.isEmpty()) {
			IdentificadorAgrega id = new IdentificadorAgrega();
			id.setCatalogo(catalogoAgrega);
			id.setEntrada(idCatalogoAgrega);
			lista.add(id);
		}
		ga.setIdentificadoresAv(lista);
		// Idioma
		ga.setIdioma(0, idioma);
		// Descripcion
		String descripcion = lomieee.getGeneral().getDescription(0).getLangstring(0).getContent();
		ga.setDescripcion(0, descripcion, idioma);
		// Nivel agregación
		String nivelAgregacion = lomieee.getGeneral().getAggregationlevel().getValue().getLangstring().getContent();
		ga.setNivelDeAgregacion(nivelAgregacion);		
				
		la.setGeneralAgrega(ga);
		
		// LOM 2 - contribucion
		//TODO
		
		// LOM 3 - metaMetadata
		MetaMetadataAgrega metaA = new MetaMetadataAgrega(new MetaMetadata());
		String schema = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES);
		metaA.addEsquemaDeMetadatos(schema);
		metaA.setIdioma(idioma);
		la.setMetaMetadataAgrega(metaA);
		
		// LOM 5 - Educational
		// TODO: se esta dejando por defecto para que siempre sea integrated media
		EducationalAgrega edu = new EducationalAgrega(new Educational());
		edu.setTipoDeRecurso(0, "integrated media");
		edu.setIdiomaDestinatario(0, idioma);
		EducationalAgrega[] eduAgrega = new EducationalAgrega[1];				
		eduAgrega[0]=edu;
		la.setEducationalsAgrega(eduAgrega);
		
		// LOM 6 - Rights		
		RightsAgrega rights= new RightsAgrega(new Rights());
		rights.setDerechosDeAutor(lomieee.getRights().getDescription().getLangstring(0).getContent());
		rights.setAcceso("universal", "mec");
		la.setRightsAgrega(rights);
		
		// LOM 9 - Classification
		// TODO
		
		return la.getLom();
	}
		
}
