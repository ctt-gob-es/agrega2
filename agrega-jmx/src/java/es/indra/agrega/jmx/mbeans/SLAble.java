/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.indra.agrega.jmx.mbeans;

import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.monitor.MonitorNotification;

/**
 * @author jlhuertas
 *
 */
public class SLAble extends Monitorizable implements SLAbleBean, SLAbleMBean {

	//private NotificationPublisher publisher;
	
	private long notificationSequenceNumber = 0;
	
	private long avgResponseTimeThreshold = NO_THRESHOLD;
	
	private long responseTimeThreshold = NO_THRESHOLD;
	
	private long responsesUnderThreshold = 0L;
	
	private float percentUnderThreshold = 100;
	
	private long failuresThreshold = NO_THRESHOLD;
	
	private boolean alertsEnabled = true;

	
	public void setAvgResponseTimeThreshold(long maxResponseTime) {
		this.avgResponseTimeThreshold = maxResponseTime;
		if (isAlertsEnabled()){
			checkSLAAvgResponseTime();
		}
	}

	public void setResponseTimeThreshold(long maxResponseTime) {
		this.responseTimeThreshold = maxResponseTime;
		//we have to reset the responsesUnderThreshold counter
		responsesUnderThreshold = 0L;
	}

	public void setPercentUnderThreshold(float percent) {
		this.percentUnderThreshold = percent;
		//we have to reset the responsesUnderThreshold counter
		responsesUnderThreshold = 0L;
	}

	public void setFailuresThreshold(long maxFailures) {
		this.failuresThreshold = maxFailures;
	}

	public long getAvgResponseTimeThreshold() {
		return avgResponseTimeThreshold;
	}

	public long getFailuresThreshold() {
		return failuresThreshold;
	}

	public float getPercentUnderThreshold() {
		return percentUnderThreshold;
	}

	public long getResponseTimeThreshold() {
		return responseTimeThreshold;
	}

	
	public void registerFailure(Throwable ex) {
		super.registerFailure(ex);
		if (alertsEnabled){
			checkSLANumFailures();	
		}
	}

	public void registerInvocation(long startTime, long endTime) {
		super.registerInvocation(startTime, endTime);
		//check if the invocation is above the threshold
		if ((endTime - startTime) <= responseTimeThreshold){
			responsesUnderThreshold++;
		}
		if (alertsEnabled){
			checkSLAPercentOfResponses();
			checkSLAAvgResponseTime();
		}
	}

	protected void checkSLANumFailures(){
		if (this.getNumFailures() > failuresThreshold){
			//send notification
			sendNotification("Failures threshold exceeded. Threshold:" + failuresThreshold	
					+ ", Current Value:" + this.getNumFailures());
		}
	}
	
	protected void checkSLAPercentOfResponses(){
		//check if we are breaking the SLA "percent of responses under a limit"
		float calculatedPercentUnderThreshold = this.calculatePercentUnderThreshold();
		if (calculatedPercentUnderThreshold < percentUnderThreshold){
			//send notification
			sendNotification("Percent of invocations with high response time exceeded. Max. Resp. Time: " 
					+ responseTimeThreshold + " ms., Desired %:" + percentUnderThreshold 
					+ " %, Current %:" + calculatedPercentUnderThreshold +" %.");
		}
	}
	
	protected void checkSLAAvgResponseTime(){
		//check if we are breking the average response time threshold
		if (this.getAvgResponseTime() > this.getAvgResponseTimeThreshold()){
			//send notification
			sendNotification("Average response time threshold exceeded. Threshold:"	+ this.getAvgResponseTimeThreshold() 
					+ ", Current Value:" + this.getAvgResponseTime());
		}
	}
	
	protected void checkAllSLAs(){
		checkSLANumFailures();
		checkSLAPercentOfResponses();
		checkSLAAvgResponseTime();
	}
	
/*	public void setNotificationPublisher(NotificationPublisher notificationPublisher) {
		this.publisher = notificationPublisher;
	}*/
	
	private float calculatePercentUnderThreshold(){
		return (float)(((float)this.responsesUnderThreshold / (float)this.getNumInvocations()) * 100.0); 
	}
	
	private void sendNotification(String message){
		//if (this.publisher != null){
/*			SLANotification notification = new SLANotification(
					MonitorNotification.THRESHOLD_VALUE_EXCEEDED, 
					this.getObjectNameBean(),
					this.notificationSequenceNumber++,
					System.currentTimeMillis(), message);*/
			Notification notification = new Notification(
					MonitorNotification.THRESHOLD_VALUE_EXCEEDED, 
					this.getObjectNameBean(),
					this.notificationSequenceNumber++,
					System.currentTimeMillis(), message);

			this.sendNotification(notification);
		//}
	}

	public void setAlertsEnabled(boolean alertsEnabled) {
		this.alertsEnabled = alertsEnabled;
		if (alertsEnabled){
			checkAllSLAs();
		}
	}

	public boolean isAlertsEnabled() {
		return this.alertsEnabled;
	}

	public MBeanNotificationInfo[] getNotificationInfo() {
		MBeanNotificationInfo[] notificationTypes = new MBeanNotificationInfo[1];
		String[] types = new String[1];
		types[0] = MonitorNotification.THRESHOLD_ERROR;
		notificationTypes[0] = new MBeanNotificationInfo(types,"javax.management.Notification","SLANotification");
		//notificationTypes[0] = new MBeanNotificationInfo(types,"es.indra.ibuilder.jmx.notifications.SLANotification","SLANotification");
		return notificationTypes;
	}
	
}
