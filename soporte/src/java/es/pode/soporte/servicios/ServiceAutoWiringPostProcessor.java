package es.pode.soporte.servicios;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;


/**
 * @author fjarrieta
 * 
 * Clase de utilidad que nos permite realizar autowiring en beans ya definidos en el contexto
 * y que no son editables por estar pregenerados
 *
 */
public class ServiceAutoWiringPostProcessor implements BeanPostProcessor, BeanFactoryAware {

	private static Logger logger = Logger.getLogger(ServiceAutoWiringPostProcessor.class);
	
	private ListableBeanFactory beanFactory;
	
	private Pattern[] patterns = new Pattern[0];

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessAfterInitialization(java.lang.Object, java.lang.String)
	 */
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

		if(!isMatch(beanName) ) {
			if( logger.isDebugEnabled() )
				logger.debug("El bean de tipo " + bean.getClass().getName() + " con el nombre "
						+ beanName + " no cumple los criterios para la asignación automática de propiedades");
		} else {
			BeanWrapper wrapper = new BeanWrapperImpl(bean);
			
			PropertyDescriptor[] pds = wrapper.getPropertyDescriptors();
			
			for (int i = 0; i < pds.length; i++) {
				PropertyDescriptor pd = pds[i];
				String propertyName = pd.getName();
				if( wrapper.isReadableProperty(propertyName) && wrapper.isWritableProperty(propertyName) ) {
					
					if( wrapper.getPropertyValue(propertyName) != null ) {
						if(logger.isInfoEnabled() ) {
							logger.info("La propiedad " + propertyName + " del bean " + beanName 
									+ " ya ha sido establecida, ignorandola" );
						}
					} else {
						autowire( wrapper, pd );
					}
					
 				} else {
 					if( logger.isInfoEnabled() )
 						logger.info("La propiedad " + propertyName + " del bean " + beanName 
 								+ " no es de lectura/escritura");
 					
 				}
			}
		}
		
		return bean;
	}

	private void autowire(BeanWrapper wrapper, PropertyDescriptor pd) {
		
		Class propertyType = pd.getPropertyType();
		String propertyName = pd.getName();
		
		if( logger.isDebugEnabled() ) {
			logger.debug("Buscando implementaciones en el contexto para propiedad <" 
					+ propertyName + "> de tipo <" + propertyType.getName()+">" );
		}
		
		Map candidates = BeanFactoryUtils.beansOfTypeIncludingAncestors(beanFactory, propertyType );
		
		if( candidates == null || candidates.size() == 0 ) {
			logger.info("No existe ningún bean definido de tipo <" + propertyType.getName() + "> para la propiedad <" + propertyName +">");
		} else {
			
			if( candidates.size() > 1 ) {
				logger.warn("Hay " + candidates.size() + " beans de tipo " + propertyType.getName() + "> definidas en el contexto: " + candidates.keySet().toString() );
				
			}
			
			String beanName = (String) candidates.keySet().iterator().next();
			Object value = candidates.get(beanName);
			
			if( logger.isInfoEnabled() ) 
				logger.info("Estableciendo la propiedad <" + propertyName + "> de tipo <" + propertyType.getName() + "> con el bean <" + beanName + ">: <" + value +">");
			
			wrapper.setPropertyValue(propertyName, value);
			
		}
		
	}

	private boolean isMatch(String beanName) {
		
		for (int i = 0; i < patterns.length; i++) {
			
			if( patterns[i].matcher(beanName).matches() )
				return true;
		}
		
		return false;
	}

	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		
		return bean;
	}

	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = (ListableBeanFactory) beanFactory;
		
	}


	public void setPatterns( String[] patterns ) {
		
		Collection patternList = new ArrayList();
		
		for (int i = 0; i < patterns.length; i++) {
			String pattern = patterns[i];
			
			patternList.add( Pattern.compile( pattern ) ); 
		}
		
		this.patterns = (Pattern[])patternList.toArray( new Pattern[ patternList.size() ] );
		
	}

}
