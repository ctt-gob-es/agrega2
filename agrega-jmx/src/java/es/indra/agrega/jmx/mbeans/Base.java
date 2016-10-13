/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * 
 */
package es.indra.agrega.jmx.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.management.MalformedObjectNameException;
import javax.management.NotificationBroadcasterSupport;
import javax.management.ObjectName;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;



/**
 * @author jlhuertas
 *
 */
public class Base extends NotificationBroadcasterSupport implements BaseBean, BaseMBean, InitializingBean {

	private List childrenMBeans = new ArrayList();
	
	private boolean started = true;
	
	private String objectName;
	
	public String getObjectName() {
		return this.objectName;
	}

	public void afterPropertiesSet() throws Exception {
		Assert.hasText(this.objectName);
	}

	public void start() {
		started = true;
	}

	public void stop() {
		started = false;
	}

	public boolean isStopped() {
		return !started;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public void setChildrenMBeans(List childrenMBeans) {
		this.childrenMBeans = childrenMBeans;
	}

	public List getChildrenMBeans() {
		return childrenMBeans;
	}

	public ObjectName getObjectNameBean() {
		ObjectName objectNameBean = null;
		
		try {
			objectNameBean = new ObjectName(this.getObjectName());
		} catch (MalformedObjectNameException e) {
			// TODO Log this exception
			
		} catch (NullPointerException e) {
			// TODO Log this exception
		}

		return objectNameBean;
		
	}

}
