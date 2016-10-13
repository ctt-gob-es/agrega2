/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
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
