package es.pode.parseadorXML.dozer;

import net.sf.dozer.util.mapping.BeanFactoryIF;

import org.apache.log4j.Logger;

import es.pode.parseadorXML.castor.types.RollupActionType;

public class RuleActionFactory implements BeanFactoryIF {

	private static Logger logger = Logger.getLogger(RuleActionFactory.class);
	
	public Object createBean(Object srcObj, Class srcObjClass, String beanId) {
		
		logger.debug("Mapping object from class " + srcObjClass.getName() + " with id " + beanId );
		
		RollupActionType type = (RollupActionType)srcObj;
		return RollupActionType.valueOf(type.toString());
	}

}
