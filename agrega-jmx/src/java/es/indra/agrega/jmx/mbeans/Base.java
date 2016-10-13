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
