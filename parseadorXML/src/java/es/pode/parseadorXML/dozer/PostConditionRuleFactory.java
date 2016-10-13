package es.pode.parseadorXML.dozer;

import net.sf.dozer.util.mapping.BeanFactoryIF;

import org.apache.log4j.Logger;

import es.pode.parseadorXML.castor.types.PostConditionRuleActionType;

public class PostConditionRuleFactory implements BeanFactoryIF {

	private static Logger logger = Logger.getLogger(PostConditionRuleFactory.class);
	
	public Object createBean(Object srcObj, Class srcObjClass, String beanId) {
		
		logger.debug("Mapping object from class " + srcObjClass.getName() + " with id " + beanId );
		
		PostConditionRuleActionType type = (PostConditionRuleActionType) srcObj;
		return PostConditionRuleActionType.valueOf(type.toString());
	}

}
