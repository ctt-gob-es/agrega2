package es.indra.agrega.jmx.mbeans;

/**
 * Interface with the methods to be exposed by a "monitorizable" MBean.
 * A "monitorizable" MBean keeps several statistics about number of invocations,
 * response times, errors, etc.
 * 
 * @author jlhuertas
 *
 */
public interface MonitorizableMBean extends BaseMBean {

	/**
	 * Enable/disable statistic recollection for this MBean
	 * @param statsEnabled
	 */
	public void setStatsEnabled(boolean statsEnabled);

	/**
	 * Returns <code>true</code> if the statistics are enabled for this MBean, <code>false</code> otherwise.
	 *
	 * @param statsEnabled
	 */
	public boolean isStatsEnabled();
	
	/**
	 * Reset all the statistic counters.
	 *
	 */
	public void resetStats();
	
	public void globalResetStats();
	
	public long getAccumulatedTime();
	
	public long getMaxResponseTime();
	
	public long getAvgResponseTime();
	
	public long getNumInvocations();
	
	public long getNumBusinessExceptions();
	
	public long getNumFailures();
	
	public long getNumExceptionsByType(String clazz, boolean includeSubTypes);
	
}
