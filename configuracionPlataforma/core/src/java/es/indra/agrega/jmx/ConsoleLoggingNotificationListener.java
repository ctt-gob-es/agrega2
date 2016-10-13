/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
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