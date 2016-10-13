/**
 * 
 */
package es.indra.agrega.jmx.notifications;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.management.Notification;

import org.apache.commons.collections.Buffer;
import org.apache.commons.collections.BufferUtils;
import org.apache.commons.collections.buffer.UnboundedFifoBuffer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

import com.thoughtworks.xstream.XStream;


/**
 * @author jlhuertas
 *
 */
public class PersistedNotificationQueue implements InitializingBean {
	
	private static Logger logger = Logger.getLogger(PersistedNotificationQueue.class);
	
	private Buffer queue = BufferUtils.synchronizedBuffer(new UnboundedFifoBuffer());
	//private Buffer queue = new UnboundedFifoBuffer();

	private boolean unsavedChanges = false;
	
	private String persistedFileName;
	
	private DeliveryThread deliveryThread;
	
	private SaveThread saveThread;
	
    public PersistedNotificationQueue(Deliverer deliverer, String fileName){
    	persistedFileName = fileName;
        // read from file
        read();
        //start the deliver and save threads.
        deliveryThread = new DeliveryThread(deliverer);
        saveThread = new SaveThread();
    }	

    public void setDeliveryThreadSleepTime(int millis){
    	deliveryThread.setSleepTime(millis);
    }
    
    public void setSaveThreadSleepTime(int millis){
    	saveThread.setSleepTime(millis);
    }
    
    private void read(){
        try {
            File file = new File(getPersistedFileName());
            if(file.exists()){
                //XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(getPersistedFileName())));
            	//queue = (Buffer)decoder.readObject();
                //decoder.close();
            	InputStream fileStream = new BufferedInputStream(new FileInputStream(getPersistedFileName()));
            	XStream xstream = new XStream();
            	queue = (Buffer) xstream.fromXML(fileStream);
            	fileStream.close();
            }
        } catch (FileNotFoundException e) {
        	logger.warn("Cannot read persistent notifications.", e);
        }
    	catch (IOException e) {
    		logger.warn("Cannot read persistent notifications.", e);
    	}
    }

    private void save(){
        try {
            //XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(getPersistedFileName())));
            //encoder.writeObject(queue);
        	//encoder.close();
        	if (queue.isEmpty()){
        		//if queue is empty delete the file
        		File file = new File(getPersistedFileName());
        		if (file.exists()){
        			file.delete();
        		}
        	} else {
            	OutputStream fileStream = new BufferedOutputStream(new FileOutputStream(getPersistedFileName()));        	
            	XStream xstream = new XStream();
            	xstream.toXML(queue, fileStream);
            	fileStream.close();
        	}
            unsavedChanges = false;
        } catch (FileNotFoundException e) {
        	logger.warn("Cannot make notifications persistent.", e);
        }
    	catch (IOException e) {
    		logger.warn("Cannot make notifications persistent.", e);
    	}
    }    
    
    public void add(Notification alertInfo){
    	queue.add(alertInfo);
		unsavedChanges = true;
    }    
    
    public Notification get(){
    	return queue.isEmpty() ? null : (Notification) queue.get();   
    }
    
    public Notification remove(){
    	if (queue.isEmpty()){
    		return null;
    	}else{
    		unsavedChanges = true;
    		return (Notification) queue.remove();
    	}
    }

	public String getPersistedFileName() {
		return persistedFileName;
	}

	public void setPersistedFileName(String persistedFileName) {
		this.persistedFileName = persistedFileName;
	}
    
    /**
     * 
     * @author jlhuertas
     *
     */
    private class DeliveryThread extends Thread{

    	private Deliverer deliverer;
    	
    	private int sleepTime = 30 * 1000;
    	
        public DeliveryThread(Deliverer deliverer){
        	this.deliverer = deliverer;
        }

        public void run(){
            while(true){
            	//get a possible queued notification
            	Notification notification = PersistedNotificationQueue.this.get();
                 
                if(notification != null){
                    if (deliverer.deliver(notification)){
                    	//notification delivered, remove from the queue
                    	PersistedNotificationQueue.this.remove();
                    }
                }
                try {
                    // sleep the specified time
                    sleep(sleepTime);
                } catch (InterruptedException e) {
                }
            }
        }

		public int getSleepTime() {
			return sleepTime;
		}

		public void setSleepTime(int sleepTime) {
			this.sleepTime = sleepTime;
		}
    }
    
    /**
     * 
     * @author jlhuertas
     *
     */
    private class SaveThread extends Thread{
    	private int sleepTime = 5 * 1000;
        public void run(){
            while(true){
            	
            	if (PersistedNotificationQueue.this.unsavedChanges){
            		PersistedNotificationQueue.this.save();
            	}
            	
                try {
                    // sleep the specified time
                    sleep(sleepTime);
                } catch (InterruptedException e) {
                }
            }
        }

        public int getSleepTime() {
			return sleepTime;
		}

		public void setSleepTime(int sleepTime) {
			this.sleepTime = sleepTime;
		}
    }

	public void afterPropertiesSet() throws Exception {
        //start the deliver and save threads.
        deliveryThread.start();
        saveThread.start();
	}

}
