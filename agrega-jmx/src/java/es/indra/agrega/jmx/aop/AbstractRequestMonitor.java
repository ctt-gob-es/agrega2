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
