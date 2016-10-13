/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * 
 */
package es.indra.agrega.jmx.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import es.indra.agrega.jmx.exceptions.OperationDisabledException;
import es.indra.agrega.jmx.mbeans.BaseBean;
import es.indra.agrega.jmx.mbeans.MonitorizableBean;


/**
 * @author jlhuertas
 *
 */
public class MethodCallMonitor extends AbstractRequestMonitor implements MethodInterceptor {


	public Object invoke(MethodInvocation invocation) throws Throwable {

		Object result = null;
		
		boolean serviceStopped = false;
		boolean serviceStatsEnabled = false;
		
		boolean operationStopped = false;
		boolean operationStatsEnabled = false;
		
		//lookup the service MBean and operation MBean (if they exist)
		//Service MBean
		Object serviceMBean = this.lookupMBean(invocation.getThis(), null);
		if(serviceMBean != null && (serviceMBean instanceof BaseBean)) {
			serviceStopped = ((BaseBean)serviceMBean).isStopped();
		}
		if(serviceMBean != null && (serviceMBean instanceof MonitorizableBean)) {
			serviceStatsEnabled = ((MonitorizableBean)serviceMBean).isStatsEnabled();
		}

		//Operation MBean
		Object operationMBean = this.lookupMBean(invocation.getThis(), invocation.getMethod().getName());
		if(operationMBean != null && (operationMBean instanceof BaseBean)) {
			operationStopped = ((BaseBean)operationMBean).isStopped();
		}	
		if(operationMBean != null && (operationMBean instanceof MonitorizableBean)) {		
			operationStatsEnabled = ((MonitorizableBean)operationMBean).isStatsEnabled();
		}
		
		
		if (serviceStopped || operationStopped){
			throw new OperationDisabledException("The operation or service you are trying to invoke has been disabled by the application administrator.");
		}else{
			
			long startTime = System.currentTimeMillis();
			long endTime = startTime;
			try{
				//call the service method.
				result = invocation.proceed();
				//stop the clock. The invocation has succeeded
				endTime = System.currentTimeMillis();
			}catch (Throwable ex){
				//stop the clock. The invocation has thown an exception
				endTime = System.currentTimeMillis();

				//an exception has been thrown, register it.
				boolean isFailure = this.getFailureDetectionStrategy().isFailure(ex);				 
				if (serviceStatsEnabled){
					if (isFailure){
						((MonitorizableBean)serviceMBean).registerFailure(ex);
					} else {
						((MonitorizableBean)serviceMBean).registerBusinessException(ex);
					}
				}
				
				if (operationStatsEnabled){
					if (isFailure){
						((MonitorizableBean)operationMBean).registerFailure(ex);
					} else {
						((MonitorizableBean)operationMBean).registerBusinessException(ex);
					}
				}
				//re-throw the exception
				throw ex;
			}finally{
				
				//collect statistics
				if (serviceStatsEnabled){
					((MonitorizableBean)serviceMBean).registerInvocation(startTime, endTime);
				}
				if (operationStatsEnabled){
					((MonitorizableBean)operationMBean).registerInvocation(startTime, endTime);
				}
			}
				
			//return the result
			return result;
		}
	}

}
