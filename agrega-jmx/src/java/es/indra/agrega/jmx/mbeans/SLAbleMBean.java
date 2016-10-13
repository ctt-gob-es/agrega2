/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
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
