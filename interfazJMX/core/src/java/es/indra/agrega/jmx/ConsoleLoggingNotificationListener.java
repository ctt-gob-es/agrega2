package es.indra.agrega.jmx;


import javax.management.AttributeChangeNotification;
import javax.management.Notification;
import javax.management.NotificationFilter;
import javax.management.NotificationListener;

public class ConsoleLoggingNotificationListener
               implements NotificationListener, NotificationFilter {

	
/**
 * Metodo que permite recibir una notificación, e interacturar con ella.	
 */
    public void handleNotification(Notification notification, Object handback) {
    	System.out.println("==================================================================");
        System.out.println("TIPO: " + notification.getType()+"\n");
        System.out.println("MENSAJE: " + notification.getMessage()+"\n");
        System.out.println("SOURCE: " + notification.getSource().toString()+"\n");        
        System.out.println("==================================================================");
        
    }

    public boolean isNotificationEnabled(Notification notification) {
        return AttributeChangeNotification.class.isAssignableFrom(notification.getClass());
    }
}