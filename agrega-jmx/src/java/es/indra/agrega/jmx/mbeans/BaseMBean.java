/**
 * 
 */
package es.indra.agrega.jmx.mbeans;

/**
 * @author jlhuertas
 *
 */
public interface BaseMBean {

	/**
	 * Returns the name of the managed object.
	 * It follows a simple name convention: [domainName]:property=value[,property=value]*
	 * @return
	 */
	public String getObjectName();
	
	public void start();
	
	public void stop();
	
	public boolean isStopped();
	
}
