/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.indra.agrega.jmx.mbeans;

import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

public class Monitorizable extends Base implements MonitorizableBean, MonitorizableMBean {

	private long numBusinessExceptions = 0L;
	
	private long numFailures = 0L;
	
	private Map exceptionMap = new HashMap();
	
	private boolean statsEnabled = true;

	private long accumulatedTime = 0L;
	
	private long maxResponseTime = 0L;
	
	private long numInvocations = 0L;
	
	public void registerInvocation(long startTime, long endTime) {
		long time = endTime - startTime;
		accumulatedTime += time;
		maxResponseTime = Math.max(time, maxResponseTime);
		numInvocations++;
	}

	public void resetStats() {
		accumulatedTime = 0L;
		maxResponseTime = 0L;
		numInvocations = 0L;
		numBusinessExceptions = 0L;
		numFailures = 0L;
		exceptionMap.clear();
	}

	public long getAccumulatedTime() {
		return accumulatedTime;
	}

	public long getMaxResponseTime() {
		return maxResponseTime;
	}

	public long getAvgResponseTime() {
		if (numInvocations == 0 ){
			return 0;
		}else{
			return (long)(accumulatedTime / numInvocations);
		}
	}

	public long getNumInvocations() {
		return numInvocations;
	}

	public void setStatsEnabled(boolean statsEnabled) {
		this.statsEnabled = statsEnabled;
	}

	public boolean isStatsEnabled() {
		return this.statsEnabled;
	}

	public void registerBusinessException(Throwable ex) {
		this.numBusinessExceptions++;
		this.addException(ex);
		
	}

	public void registerFailure(Throwable ex) {
		this.numFailures++;
		this.addException(ex);		
	}

	public long getNumBusinessExceptions() {
		return this.numBusinessExceptions;
	}

	public long getNumFailures() {
		return this.numFailures;
	}

	public long getNumExceptionsByType(String clazz, boolean includeSubTypes) {
		
		long counter = 0;
	
		if (includeSubTypes) {
			try {
				//get the class
				Class superClass = Class.forName(clazz);
				//iterate the map trying to look for subtypes
				Iterator it = this.exceptionMap.keySet().iterator();
				while (it.hasNext()){
					try{
						String key = (String)it.next();
						Class subClass = Class.forName(key);
						if (superClass.isAssignableFrom(subClass)){
							//found subtype, increment the counter.
							counter += ((Long)this.exceptionMap.get(key)).longValue();
						}
					} catch (ClassNotFoundException e) {
						//just ignore it
					}
				}
			} catch (ClassNotFoundException e) {
				//just ignore it
			} 
		}else {
			//try to see if we have a counter for the exception.
			Object entry = this.exceptionMap.get(clazz);	
			if (entry != null){
				counter = ((Long)entry).longValue();
			}
		}
	
		return counter;
	}
	
	private void addException(Throwable ex){
		String clazz = ex.getClass().getName();
		
		Object counter = this.exceptionMap.get(clazz);
		if (counter == null){
			//first exception, initialize counter
			this.exceptionMap.put(clazz, new Long(1));
		}else{
			//existing exception, increment counter
			long value = ((Long)counter).longValue();
			this.exceptionMap.put(clazz, new Long(++value));
		}
	}

	public void globalResetStats() {
		this.resetStats();
		Iterator it = this.getChildrenMBeans().iterator();
		while (it.hasNext()){
			Object bean = it.next();
			if (bean instanceof MonitorizableMBean){
				((MonitorizableMBean)bean).globalResetStats();
			}
		}
		
	}
}
