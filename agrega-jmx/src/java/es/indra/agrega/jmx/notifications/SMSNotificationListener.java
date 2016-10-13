/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.indra.agrega.jmx.notifications;



import javax.management.Notification;
import javax.management.NotificationFilter;
import javax.management.NotificationListener;

import org.apache.log4j.Logger;
import org.smslib.COutgoingMessage;
import org.smslib.CService;
import org.smslib.IMessage;
import org.smslib.IOutgoingMessage;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import es.indra.agrega.jmx.JmxUtils;

public class SMSNotificationListener 
				implements NotificationListener, NotificationFilter, InitializingBean, Deliverer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7711297127147093333L;

	private boolean ready = false;
	
	private static Logger logger = Logger.getLogger(SMSNotificationListener.class);
	
	private String port;
	
	private int baudRate = -1;
	
	private String deviceManufacturer = "";
	
	private String deviceModel = "";
	
	private String simPin;
	
	private String destinationNumber;
	
	private PersistedNotificationQueue deliveryQueue;
	
	public void handleNotification(Notification notification, Object handback) {
		//add the notification to the delivery queue
		if (deliveryQueue != null){
			deliveryQueue.add(notification);			
		}
	}

	public boolean isNotificationEnabled(Notification notification) {
		return Notification.class.isAssignableFrom(notification.getClass());
	}

	public void afterPropertiesSet() throws Exception {
		Assert.notNull(deliveryQueue, "The field 'deliveryQueue' is mandatory");
		Assert.hasText(port, "The field 'port' is mandatory.");
		Assert.isTrue((baudRate != -1), "The field 'baudRate' is mandatory.");
		Assert.hasText(destinationNumber, "The field 'destinationNumber' is mandatory.");
		ready = true;
	}

	private void sendSMS(String text) throws Exception{

		// Define the CService object. The parameters show the Comm Port used, the Baudrate,
		// the Manufacturer and Model strings. Manufacturer and Model strings define which of
		// the available AT Handlers will be used.
		CService srv = new CService(port, baudRate, deviceManufacturer, deviceModel);

		// If the GSM device is PIN protected, enter the PIN here.
		// PIN information will be used only when the GSM device reports that it needs
		// a PIN in order to continue.
		if (simPin != null){
			srv.setSimPin(simPin);
		}
		
		// Normally, you would want to set the SMSC number to blank. GSM devices
		// get the SMSC number information from their SIM card.
		srv.setSmscNumber("");

		// OK, let connect and see what happens... Exceptions may be thrown here!
		srv.connect();
		// Lets get info about the GSM device...
		if (logger.isDebugEnabled()){
			StringBuffer info = new StringBuffer();
			info.append("Connected to a mobile device to send a SMS.\nMobile Device Information: \n");
			info.append("	Manufacturer  : " + srv.getDeviceInfo().getManufacturer() + "\n");
			info.append("	Model         : " + srv.getDeviceInfo().getModel() + "\n");
			info.append("	Serial No     : " + srv.getDeviceInfo().getSerialNo() + "\n");
			info.append("	IMSI          : " + srv.getDeviceInfo().getImsi() + "\n");
			info.append("	S/W Version   : " + srv.getDeviceInfo().getSwVersion() + "\n");
			info.append("	Battery Level : " + srv.getDeviceInfo().getBatteryLevel() + "%\n");
			info.append("	Signal Level  : " + srv.getDeviceInfo().getSignalLevel() + "%\n");
			logger.debug(info.toString());
		}

		// Lets create a message for dispatch.
		// A message needs the recipient's number and the text. Recipient's number should always
		// be defined in international format.
		IOutgoingMessage msg = new COutgoingMessage(destinationNumber, text);

		// Set the message encoding.
		// We can use 7bit, 8bit and Unicode. 7bit should be enough for most cases. Unicode
		// is necessary for Far-East countries.
		msg.setMessageEncoding(IMessage.MESSAGE_ENCODING_7BIT);

		// Do we require a Delivery Status Report?
		msg.setStatusReport(true);

		// We can also define the validity period.
		// Validity period is always defined in hours.
		// The following statement sets the validity period to 8 hours.
		msg.setValidityPeriod(8);

		// Ok, finished with the message parameters, now send it!
		srv.sendMessage(msg);

		// Disconnect
		srv.disconnect();
			

	}

	public int getBaudRate() {
		return baudRate;
	}

	public void setBaudRate(int baudRate) {
		this.baudRate = baudRate;
	}

	public String getDeviceManufacturer() {
		return deviceManufacturer;
	}

	public void setDeviceManufacturer(String deviceManufacturer) {
		this.deviceManufacturer = deviceManufacturer;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDestinationNumber() {
		return destinationNumber;
	}

	public void setDestinationNumber(String destinationNumber) {
		this.destinationNumber = destinationNumber;
	}

	public String getSimPin() {
		return simPin;
	}

	public void setSimPin(String simPin) {
		this.simPin = simPin;
	}

	public boolean deliver(Notification notification) {
		if (!ready){
			return false;
		}
		boolean result = true;
		try{	
			this.sendSMS(JmxUtils.notificationToReducedString(notification));
		} catch (Exception e) {
			result = false;
			logger.warn("Error sending notification SMS.", e);
		}
		return result;
	}

	public PersistedNotificationQueue getDeliveryQueue() {
		return deliveryQueue;
	}

	public void setDeliveryQueue(PersistedNotificationQueue deliveryQueue) {
		this.deliveryQueue = deliveryQueue;
	}
}
