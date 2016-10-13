package es.pode.parseadorXML.dozer;

import net.sf.dozer.util.mapping.BeanFactoryIF;

import org.apache.log4j.Logger;

import es.pode.parseadorXML.castor.types.PreConditionRuleActionType;

public class PreConditionRuleFactory implements BeanFactoryIF {


	private static Logger logger = Logger.getLogger(PreConditionRuleFactory.class);
	
	public Object createBean(Object srcObj, Class srcObjClass, String beanId) {
		
		logger.debug("Mapping object from class " + srcObjClass.getName() + " with id " + beanId );
		
		PreConditionRuleActionType type = (PreConditionRuleActionType) srcObj;
		return PreConditionRuleActionType.valueOf(type.toString());
	}

}
