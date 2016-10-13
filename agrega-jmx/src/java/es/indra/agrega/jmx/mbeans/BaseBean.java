/**
 * 
 */
package es.indra.agrega.jmx.mbeans;

import java.util.List;

import javax.management.ObjectName;

/**
 * @author jlhuertas
 *
 */
public interface BaseBean extends BaseMBean {

	public void setObjectName(String objectName);
	
	public ObjectName getObjectNameBean();
	
	public void setChildrenMBeans(List childrenMBeans);
}
