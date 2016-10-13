package es.pode.parseadorXML.dozer;

import net.sf.dozer.util.mapping.BeanFactoryIF;

import org.apache.log4j.Logger;

import es.pode.parseadorXML.castor.types.ChildActivityType;

public class ChildActivityTypeFactory implements BeanFactoryIF {

	private static Logger logger = Logger.getLogger(ChildActivityTypeFactory.class);
	
	public Object createBean(Object srcObj, Class srcObjClass, String beanId) {
		
		logger.debug("Mapping object from class " + srcObjClass.getName() + " with id " + beanId );
		
		ChildActivityType type = (ChildActivityType)srcObj;
		return ChildActivityType.valueOf(type.toString());
	}

}
