package es.pode.parseadorXML.dozer;

import net.sf.dozer.util.mapping.BeanFactoryIF;

import org.apache.log4j.Logger;

import es.pode.parseadorXML.castor.types.SequencingRuleConditionType;

public class SequencingRuleConditionFactory implements BeanFactoryIF {

	private static Logger logger = Logger.getLogger(SequencingRuleConditionFactory.class);
	
	public Object createBean(Object srcObj, Class srcObjClass, String beanId) {
		
		logger.debug("Mapping object from class " + srcObjClass.getName() + " with id " + beanId );
		
		SequencingRuleConditionType type = (SequencingRuleConditionType) srcObj;
		
		return SequencingRuleConditionType.valueOf(type.toString());
	}

}
