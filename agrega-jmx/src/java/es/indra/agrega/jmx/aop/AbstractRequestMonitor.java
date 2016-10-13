/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.indra.agrega.jmx.aop;

import java.util.Map;
import java.util.TreeMap;

import es.indra.agrega.jmx.JmxUtils;
import es.indra.agrega.jmx.exceptions.FailureDetectionStrategy;
import es.indra.agrega.jmx.exceptions.HierarchyBasedFailureDetectionStrategy;
import es.indra.agrega.jmx.mbeans.BaseMBean;

/**
 * @author jlhuertas
 *
 */
public abstract class AbstractRequestMonitor {

	private FailureDetectionStrategy failureDetectionStrategy = new HierarchyBasedFailureDetectionStrategy();
	
	private String appName = JmxUtils.DEFAULT_APP_NAME;
	
	/** The beans to be exposed as managed resources */
	private Map beans = new TreeMap();
	
	

	/**
	 * Perform a search based in the operation to monitor and returning
	 * the corresponding managed bean.
	 * @param operation
	 * @return
	 */
	protected BaseMBean lookupMBean(Object bean, String operation){
		return (BaseMBean) beans.get(JmxUtils.buildObjectName(appName, bean, operation));
	}

	/**
	 * Supply a <code>Map</code> of beans to be monitored by this aspect.
	 * <p>The String keys are the the JMX <code>ObjectName</code>.
	 * <p>Bean instances and are the allowed values.
	 * Bean instances are typically linked in through bean references.
	 * @param beans Map with bean instances as values
	 * @see javax.management.ObjectName#ObjectName(String)
	 */
	public void setBeans(Map beans) {
		this.beans = beans;
		//register beans
	}

	public Map getBeans() {
		return beans;
	}	
	
	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public FailureDetectionStrategy getFailureDetectionStrategy() {
		return failureDetectionStrategy;
	}

	public void setFailureDetectionStrategy(
			FailureDetectionStrategy failureDetectionStrategy) {
		this.failureDetectionStrategy = failureDetectionStrategy;
	}

}
