package es.pode.parseadorXML.dozer;

import net.sf.dozer.util.mapping.BeanFactoryIF;

import org.apache.log4j.Logger;

import es.pode.parseadorXML.castor.types.RandomTimingType;

public class RandomTimingTypeFactory implements BeanFactoryIF {

	private static Logger logger = Logger.getLogger(RandomTimingTypeFactory.class);
	
	public Object createBean(Object srcObj, Class srcObjClass, String beanId) {
		
		logger.debug("Mapping object from class " + srcObjClass.getName() + " with id " + beanId );
		
		RandomTimingType type = (RandomTimingType)srcObj;
		return RandomTimingType.valueOf(type.toString());
	}


}
