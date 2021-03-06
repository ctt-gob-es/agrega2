package es.pode.soporte.servicios;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class WiringPostProcessor implements BeanPostProcessor,
		BeanFactoryAware, InitializingBean {

	private static class PropertyMapping {
		
		private Pattern beanName;
		
		private Pattern beanProperty;
		
		private Pattern propertyBeanName;
		
	}
	
	private boolean allowPropertyOverride = false;
	
	private String[] propertyMappings = new String[0];
	
	private PropertyMapping[] mapping = new PropertyMapping[0];
	
	private ListableBeanFactory beanFactory;
	
	private String separator = ";";
	
	private static Logger logger = Logger.getLogger(WiringPostProcessor.class);

	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = (ListableBeanFactory) beanFactory;

	}

	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
				
		for (int i = 0; i < mapping.length; i++) {
			PropertyMapping pm = mapping[i];
			
			Pattern pattern = pm.beanName;
			
			if( pattern.matcher(beanName).matches() ) {
				if( logger.isDebugEnabled() )
					logger.debug("Procesando bean " + beanName + " para el establecimiento de la propiedad " + pm.beanProperty );
				wireProperty( bean, beanName, pm );
				
			}
		}
		return bean;
	}

	private void wireProperty(Object bean, String beanName, PropertyMapping pm) {
		
		BeanWrapper wrapper = new BeanWrapperImpl( bean );
		
		PropertyDescriptor[] descriptors = wrapper.getPropertyDescriptors();
		
		boolean anyMatch = false;
		
		for (int i = 0; i < descriptors.length; i++) {
			PropertyDescriptor pd = descriptors[i];
		
			String propertyName = pd.getName();
			
			if( pm.beanProperty.matcher(propertyName).matches() ) {
			
				if( pd.getWriteMethod() == null ) {
					logger.warn("La propiedad " + propertyName + " del bean " + beanName + " no se puede escribir" );
					
				} else {
					
					if( pd.getReadMethod() == null ) {
						if( logger.isInfoEnabled() )
							logger.info("La propiedad " + propertyName + " del bean " + beanName + " no se puede leer" );
						
					}
					
					if( ! allowPropertyOverride ) {
						//No se permite reescribir la propiedad
						//por lo que comprobamos que se puede leer y que est� vac�a
						if( !wrapper.isReadableProperty(propertyName) || wrapper.getPropertyValue( propertyName ) != null ) {
							logger.warn("La propiedad allowPropertyOverride no est� activada, se ignorar� el establecimiento de la propiedad " + propertyName
									+ " del bean " + beanName );
							
							continue;
						}
					}
					
					if( allowPropertyOverride && ( !wrapper.isReadableProperty(propertyName) || wrapper.getPropertyValue(propertyName) != null ) )
						logger.warn("La propiedad " + propertyName + " del bean " + beanName + " no se puede leer o ya ha sido establecida y se va a cambiar su valor" );
					
					Object value = null;
					String chosenBeanName = null;
					anyMatch = true;
					
					if( logger.isDebugEnabled() ) {
						logger.debug( "Buscando beans que cumplan el patr�n " + pm.propertyBeanName.pattern() + "para la propiedad " + propertyName + " del bean " + beanName + " que cumplan el tipo especificado "+ pd.getPropertyType().getName() );
					}
					
					String candidates[] = BeanFactoryUtils.beanNamesForTypeIncludingAncestors(beanFactory, pd.getPropertyType() );
					
					if( logger.isDebugEnabled() )
						logger.debug("Se han encontrado los siguientes candidatos para la propiedad " + propertyName + " del bean " + beanName + ": " + Arrays.toString(candidates));
					
					List matchingCandidates = new ArrayList();
					for (int j = 0; j < candidates.length; j++) {
						String candidateName = candidates[j];
						
						if( pm.propertyBeanName.matcher(candidateName).matches() )
							matchingCandidates.add(candidateName);
					}
					
					if( logger.isDebugEnabled() )
						logger.debug("Se han encontrado los siguientes candidatos para la propiedad " + propertyName + " del bean " + beanName + " que cumplen el patr�n " + pm.propertyBeanName.pattern() 
								+ ": " + matchingCandidates );
					
					
					if( matchingCandidates.size() == 0 ) {
						logger.warn("No se ha encontrado ning�n bean que cumpla el patr�n " + pm.propertyBeanName.pattern() + " de tipo " + pd.getPropertyType().getName() );
					} else {
						chosenBeanName = (String) matchingCandidates.get(0);
						if( matchingCandidates.size() > 1 ) {
							logger.warn("Eligiendo el bean " + chosenBeanName + " para la propiedad " + propertyName + " del bean " 
									+ beanName + " entre los candidatos " + matchingCandidates );
						}

						value = beanFactory.getBean(chosenBeanName);
					}
					
					if( logger.isInfoEnabled() )
						logger.info("Asignando la propiedad " + propertyName + " del bean " + beanName + " con el bean " + chosenBeanName );
					
					wrapper.setPropertyValue(propertyName, value);
					
				}
			} else {
				if( logger.isDebugEnabled() ) 
					logger.debug("La propiedad " + propertyName + " del bean " + beanName + " no cumple el patr�n asignado " + pm.beanProperty.pattern() );
			}
		}
		
		if( ! anyMatch ) {
			logger.warn("El bean " + beanName + " no tiene ninguna propiedad que cumpla el patr�n " + pm.beanProperty.pattern() );
		}
	}

	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		
		return bean;
	}

	public void afterPropertiesSet() throws Exception {
		
		if( propertyMappings!= null && propertyMappings.length > 0 ) {

			List mappingList = new ArrayList(propertyMappings.length ); 
			
			for (int i = 0; i < propertyMappings.length; i++) {
				PropertyMapping mapping = processMapping( propertyMappings[i] );
				
				if( mapping != null ) {
					mappingList.add( mapping );
				} else {
					logger.warn("No se pudo procesar el mapeo " + propertyMappings[i] );
				}
			}
			
			this.mapping = (PropertyMapping[]) mappingList.toArray( new PropertyMapping[ mappingList.size() ]);
		}
		
	}

	private PropertyMapping processMapping(String string) {
		
		String[] parts = string.split( separator );
		
		if( parts.length > 3 || parts.length  < 2 ) {
			logger.warn("La cadena " + string + " no es una cadena v�lida de definici�n de mapeo de propiedad con el separador " + separator );

			return null;
		}
		
		PropertyMapping pm = new PropertyMapping();
		pm.beanName = Pattern.compile(parts[0]);
		pm.beanProperty = Pattern.compile(parts[1]);
		
		String propertyBeanName = parts[1];
		if( parts.length > 2 )
			propertyBeanName = parts[2];
		
		pm.propertyBeanName = Pattern.compile(propertyBeanName);
		
		return pm;
		
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	public void setPropertyMappings(String[] propertyMappings) {
		this.propertyMappings = propertyMappings;
	}

	public void setAllowPropertyOverride(boolean allowWriteOnlyMaps) {
		this.allowPropertyOverride = allowWriteOnlyMaps;
	}

}
