package es.pode.modificador.negocio.cambios.configuracion.dozer.converters;

import net.sf.dozer.util.mapping.converters.CustomConverter;

import org.apache.log4j.Logger;

import es.pode.fuentestaxonomicas.negocio.servicio.EstructuraVdexVO;
import es.pode.modificador.negocio.cambios.EspecialTermTypes;
import es.pode.modificador.negocio.cambios.configuracion.castor.Addition;
import es.pode.modificador.negocio.cambios.configuracion.castor.ChangesTypeItem;
import es.pode.modificador.negocio.cambios.configuracion.castor.Check;
import es.pode.modificador.negocio.cambios.configuracion.castor.Export;
import es.pode.modificador.negocio.cambios.configuracion.castor.Metadata;
import es.pode.modificador.negocio.cambios.configuracion.castor.Modification;
import es.pode.modificador.negocio.cambios.configuracion.castor.Removal;
import es.pode.modificador.negocio.cambios.configuracion.castor.Transformation;
import es.pode.modificador.negocio.cambios.configuracion.castor.Validation;
import es.pode.modificador.negocio.cambios.configuracion.castor.types.FormatType;
import es.pode.modificador.negocio.cambios.configuracion.castor.types.TermType;
import es.pode.modificador.negocio.servicio.SrvHerramientaModificacionBase;
import es.pode.modificador.negocio.servicio.vo.CambioTypes;
import es.pode.modificador.negocio.servicio.vo.Change;
import es.pode.modificador.negocio.servicio.vo.TransformacionTypes;

public class Changes2VOConverter implements CustomConverter {

	private static final Logger logger = Logger.getLogger(Changes2VOConverter.class);
	
	private static final String DEFAULT_PURPOSE = "discipline";
	
	public Object convert(Object existingDestinationFieldValue,
			Object sourceFieldValue, Class destClass, Class sourceClass) {
		Object result = null;
		if(logger.isDebugEnabled()) logger.debug("Mapeando de " + sourceClass + " a " + destClass);
		if(sourceFieldValue instanceof Object[] ) {
			if(logger.isDebugEnabled()) logger.debug("Recorriendo el array");
			Object[] srcArray = (Object[])sourceFieldValue; 
			Object[] destArray = null;
			if(srcArray instanceof ChangesTypeItem[]) destArray = new Change[srcArray.length];
			else if(srcArray instanceof Change[]) destArray = new ChangesTypeItem[srcArray.length];
			if(destArray!=null) {
				for(int i=0;i<srcArray.length;i++) {
					Object item = srcArray[i];
					if(item instanceof ChangesTypeItem) {
						if(logger.isDebugEnabled()) logger.debug("Mapeando de ChangesTypeItem a VO");
						destArray[i] = convertCastor2VO((ChangesTypeItem)item);	
					} else if (item instanceof Change) {
						if(logger.isDebugEnabled()) logger.debug("Mapeando de VO a ChangeTypeItem");
						destArray[i] = convertVO2Castor((Change)item);	
					} else {
						if(logger.isDebugEnabled()) logger.debug("El array contiene objetos no esperados. Ignorando el mapeo.");
					}
				}
			}
			result = destArray;
		}
		
		return result;
	}

	private Object convertCastor2VO(ChangesTypeItem src) {
		Change vo = new Change();
		if(src.getChoiceValue() instanceof Validation) {
			vo.setType(CambioTypes.VALIDAR);
		} else if(src.getChoiceValue() instanceof Modification) {
			vo.setType(CambioTypes.MODIFICAR_LOMES);
			Modification change = (Modification)src.getChoiceValue();
			vo.setLomTerm(change.getLomTerm());
			vo.setValue(change.getValue());
			vo.setNewValue(change.getNewValue());
			vo.setRegExp(Boolean.valueOf(change.getRegExp()));
			vo.setReplaceAll(Boolean.valueOf(change.getReplaceAll()));
			vo.setMainLomOnly(Boolean.valueOf(change.getMainLomOnly()));
			vo.setLanguage(change.getLanguage());
		} else if(src.getChoiceValue() instanceof Addition) {
			vo.setType(CambioTypes.ADD_LOMES);
			Addition change = (Addition)src.getChoiceValue();
			vo.setLomTerm(change.getLomTerm());
			vo.setNewValue(change.getNewValue());
			vo.setMainLomOnly(Boolean.valueOf(change.getMainLomOnly()));
			EspecialTermTypes type = null;
			if(TermType.TAXONOMIA.equals(change.getTermType())) {
				type = EspecialTermTypes.TAXONOMIA;
				vo.setPurpose(change.getPurpose());
				vo.setSource(change.getSource());
			} else if(TermType.ETB.equals(change.getTermType())) {
				/*
				 * Compatibilidad con plantillas XML previas a v1.2: En el
				 * parseo de XMLs transformamos la información de añadir ETB a
				 * añadir taxonomía de tesauro vigente.
				 */
				if(logger.isDebugEnabled()) logger.debug("Leida plantilla de versión anterior a 1.2: mapeo los datos de arbol-curricular a taxonomia");
				type = EspecialTermTypes.TAXONOMIA;
				vo.setPurpose(DEFAULT_PURPOSE);
				vo.setSource(obtenerTesauroVigente());
			} else if(TermType.ARBOL_CURRICULAR.equals(change.getTermType())) {
				/*
				 * Compatibilidad con plantillas XML previas a v1.2: En el
				 * parseo de XMLs transformamos la información de añadir arbol curricular a
				 * añadir taxonomía de arbol curricular vigente.
				 */
				if(logger.isDebugEnabled()) logger.debug("Leida plantilla de versión anterior a 1.2: mapeo los datos de etb a taxonomia");
				type = EspecialTermTypes.TAXONOMIA;
				vo.setPurpose(DEFAULT_PURPOSE);
				vo.setSource(obtenerACVigente());
			} else {
				type = EspecialTermTypes.OTRO;
			}
			vo.setTermType(type);
		} else if(src.getChoiceValue() instanceof Check) {
			vo.setType(CambioTypes.CHECK_LOMES);
			Check change = (Check)src.getChoiceValue();
			vo.setLomTerm(change.getLomTerm());
			vo.setValue(change.getValue());
			vo.setRegExp(Boolean.valueOf(change.getRegExp()));
			vo.setMainLomOnly(Boolean.valueOf(change.getMainLomOnly()));
			vo.setLanguage(change.getLanguage());
		} else if(src.getChoiceValue() instanceof Removal) {
			vo.setType(CambioTypes.ELIMINAR_LOMES);
			Removal change = (Removal)src.getChoiceValue();
			vo.setLomTerm(change.getLomTerm());
			vo.setValue(change.getValue());
			vo.setRegExp(Boolean.valueOf(change.getRegExp()));
			vo.setMainLomOnly(Boolean.valueOf(change.getMainLomOnly()));
			vo.setLanguage(change.getLanguage());
		} else if(src.getChoiceValue() instanceof Export) {
			vo.setType(CambioTypes.EXPORT);
			Export export = (Export)src.getChoiceValue();
			vo.setExportFormat(TransformacionTypes.fromString(export.getFormat().toString()));
			vo.setExportPath(export.getTargetPath());
		} 
		else if(src.getChoiceValue() instanceof Transformation ) {
			vo.setType(CambioTypes.TRANSFORMAR);
			Transformation transformation = (Transformation)src.getChoiceValue();
			vo.setExportFormat(TransformacionTypes.fromString(transformation.getTargetFormat().toString()));
			vo.setExportPath(transformation.getTargetPath());
		}
		else if(src.getChoiceValue() instanceof Metadata ) {
			vo.setType(CambioTypes.INFORME_METADATOS);
		}
		return vo;
	}
	
	private Object convertVO2Castor(Change src) {
		ChangesTypeItem item = new ChangesTypeItem();
		if(CambioTypes.VALIDAR.equals(src.getType())) {
			Validation choice = new Validation();
			item.setValidation(choice);
		} else if(CambioTypes.ADD_LOMES.equals(src.getType())) {
			Addition choice = new Addition();
			choice.setLomTerm(src.getLomTerm());
			choice.setNewValue(src.getNewValue());
			choice.setMainLomOnly(src.getMainLomOnly().booleanValue());
			TermType type = null;
			if(EspecialTermTypes.TAXONOMIA.equals(src.getTermType())) {
				type = TermType.TAXONOMIA;
				choice.setPurpose(src.getPurpose());
				choice.setSource(src.getSource());
			} else {
				type = TermType.OTRO;
			}
			choice.setTermType(type);
			item.setAddition(choice);
		} else if(CambioTypes.CHECK_LOMES.equals(src.getType())) {
			Check choice = new Check();
			choice.setLomTerm(src.getLomTerm());
			choice.setMainLomOnly(src.getMainLomOnly().booleanValue());
			choice.setLanguage(src.getLanguage());
			choice.setRegExp(src.getRegExp().booleanValue());
			choice.setValue(src.getValue());
			item.setCheck(choice);
		} else if(CambioTypes.MODIFICAR_LOMES.equals(src.getType())) {
			Modification choice = new Modification();
			choice.setLomTerm(src.getLomTerm());
			choice.setMainLomOnly(src.getMainLomOnly().booleanValue());
			choice.setLanguage(src.getLanguage());
			choice.setRegExp(src.getRegExp().booleanValue());
			choice.setValue(src.getValue());
			choice.setNewValue(src.getNewValue());
			choice.setReplaceAll(src.getReplaceAll().booleanValue());
			item.setModification(choice);
		} else if(CambioTypes.ELIMINAR_LOMES.equals(src.getType())) {
			Removal choice = new Removal();
			choice.setLomTerm(src.getLomTerm());
			choice.setMainLomOnly(src.getMainLomOnly().booleanValue());
			choice.setLanguage(src.getLanguage());
			choice.setRegExp(src.getRegExp().booleanValue());
			choice.setValue(src.getValue());
			item.setRemoval(choice);
		} else if(CambioTypes.EXPORT.equals(src.getType())) {
			Export choice = new Export();
			try {
				choice.setFormat(FormatType.valueOf(src.getExportFormat().toString()));
			} catch (IllegalArgumentException e) {
				logger.error("Valor incorrecto de formato en tarea de exportación : " + src.getExportFormat(),e);
				choice.setFormat(null);
			}
			choice.setTargetPath(src.getExportPath());
			item.setExport(choice);
		} else if(CambioTypes.TRANSFORMAR.equals(src.getType())) {
			Transformation choice = new Transformation();
			try {
				choice.setTargetFormat(FormatType.valueOf(src.getExportFormat().toString()));
			} catch (IllegalArgumentException e) {
				logger.error("Valor incorrecto de formato en tarea de Transformacion : " + src.getExportFormat(),e);
				choice.setTargetFormat(null);
			}
			choice.setTargetPath(src.getExportPath());
			item.setTransformation(choice);
		} else 	if(CambioTypes.INFORME_METADATOS.equals(src.getType())) {
			Metadata choice = new Metadata();
			item.setMetadata(choice);
		}
		return item;
	}
	
	private String obtenerACVigente() {
		String result = null;
		String defaultAC = "ACLOE2006";
		try {
			if(logger.isDebugEnabled()) logger.debug("Recuperando arbol curricular vigente");
			EstructuraVdexVO arbolVigente = getSrvTaxonomiaService().obtenerArbolVigente();
			result = arbolVigente.getVocabIdentifier();
			if(logger.isDebugEnabled()) logger.debug("Arbol curricular vigente recuperado: " + result);
		} catch (Exception e) {
			logger.error("No se ha podido obtener el arbol curricular vigente. Uso " + defaultAC + " por defecto",e);
			result = defaultAC;
		}
		return result;
	}
	private String obtenerTesauroVigente() {
		String defaultETB="ETBLREMC001";
		String result = null;
		try {
			if(logger.isDebugEnabled()) logger.debug("Recuperando tesauro vigente");
			EstructuraVdexVO arbolVigente = getSrvTesaurosServices().obtenerTesauroVigente();
			result = arbolVigente.getVocabIdentifier();
			if(logger.isDebugEnabled()) logger.debug("tesauro vigente recuperado: " + result);
		} catch (Exception e) {
			logger.error("No se ha podido obtener el tesauro vigente. Uso " + defaultETB + " por defecto",e);
			result = defaultETB;
		}
		return result;
	}
	
    protected final es.pode.fuentestaxonomicas.negocio.servicio.SrvTaxonomiaService getSrvTaxonomiaService()
	        throws java.lang.Exception
	    {
            String srvTaxonomiaServiceFile="importedServices.properties";	    
	          java.io.InputStream srvTaxonomiaServiceInputStream=SrvHerramientaModificacionBase.class.getClassLoader().getResourceAsStream(srvTaxonomiaServiceFile);
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
	          java.io.InputStream srvTesaurosServicesInputStream=SrvHerramientaModificacionBase.class.getClassLoader().getResourceAsStream(srvTesaurosServicesFile);
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
	
}
