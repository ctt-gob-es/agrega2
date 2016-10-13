package es.pode.parseadorXML.dozer;

import net.sf.dozer.util.mapping.BeanFactoryIF;

import org.apache.log4j.Logger;

import es.pode.parseadorXML.castor.types.ConditionCombinationType;

public class ConditionCombinationFactory implements BeanFactoryIF {

	private static Logger logger = Logger.getLogger(ConditionCombinationFactory.class);
	
	public Object createBean(Object srcObj, Class srcObjClass, String beanId) {
		
		logger.debug("Mapping object from class " + srcObjClass.getName() + " with id " + beanId );
		
		ConditionCombinationType type = (ConditionCombinationType) srcObj;
		
		return ConditionCombinationType.valueOf(type.toString());
	}

}
