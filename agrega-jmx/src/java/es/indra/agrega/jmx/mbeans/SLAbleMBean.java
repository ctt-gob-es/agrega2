/**
 * 
 */
package es.indra.agrega.jmx.mbeans;

/**
 * @author jlhuertas
 *
 */
public interface SLAbleMBean extends MonitorizableMBean {
	
	public static long NO_THRESHOLD = Long.MAX_VALUE;
	
	public void setAvgResponseTimeThreshold(long maxResponseTime);
	
	public void setResponseTimeThreshold(long maxResponseTime);
	
	public void setPercentUnderThreshold(float percent);
	
	public void setFailuresThreshold(long maxFailures);
	
	public long getAvgResponseTimeThreshold();

	public long getFailuresThreshold();

	public float getPercentUnderThreshold();

	public long getResponseTimeThreshold();
	
	public void setAlertsEnabled(boolean alertsEnabled);
	
	public boolean isAlertsEnabled();

}
