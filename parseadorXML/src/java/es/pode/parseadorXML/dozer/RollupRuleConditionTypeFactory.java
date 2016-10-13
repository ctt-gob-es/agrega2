package es.pode.parseadorXML.dozer;

import net.sf.dozer.util.mapping.BeanFactoryIF;

import org.apache.log4j.Logger;

import es.pode.parseadorXML.castor.types.RollupRuleConditionType;

public class RollupRuleConditionTypeFactory implements BeanFactoryIF {

	private static Logger logger = Logger.getLogger(RollupRuleConditionTypeFactory.class);
	
	public Object createBean(Object srcObj, Class srcObjClass, String beanId) {
		
		logger.debug("Mapping object from class " + srcObjClass.getName() + " with id " + beanId );
		
		RollupRuleConditionType type = (RollupRuleConditionType)srcObj;
		return RollupRuleConditionType.valueOf(type.toString());
	}

}
