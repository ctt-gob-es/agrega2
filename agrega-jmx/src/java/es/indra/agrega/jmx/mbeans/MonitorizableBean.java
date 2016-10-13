/**
 * 
 */
package es.indra.agrega.jmx.mbeans;

/**
 * @author jlhuertas
 *
 */
public interface MonitorizableBean extends BaseBean, MonitorizableMBean {

	public void registerInvocation(long startTime, long endTime);
	
	public void registerBusinessException(Throwable ex);
	
	public void registerFailure(Throwable ex);
	
	//public void registerException(Throwable ex);
	
}
