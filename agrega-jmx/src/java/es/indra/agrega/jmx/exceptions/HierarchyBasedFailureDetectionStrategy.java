/**
 * 
 */
package es.indra.agrega.jmx.exceptions;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author jlhuertas
 *
 */
public class HierarchyBasedFailureDetectionStrategy implements
		FailureDetectionStrategy {

	private Set excludedExceptions = new HashSet() ;

	
	public Set getExcludedExceptions() {
		return excludedExceptions;
	}


	public void setExcludedExceptions(Set excludedExceptions) {
		this.excludedExceptions = excludedExceptions;
	}

	public void addExcludedException(Class ex){
		this.excludedExceptions.add(ex);
	}

	public void removeExcludedException(Class ex){
		this.excludedExceptions.remove(ex);
	}
	
	/**
	 * 
	 */
	public boolean isFailure(Throwable exception) {
		boolean isFailure = true;
		Class excpClass = exception.getClass();
		
		Iterator it = excludedExceptions.iterator();

		while (isFailure && it.hasNext()){
			String currentExcStr = (String) it.next();
			try {
				Class currentClass = Class.forName(currentExcStr);
				if (currentClass.isAssignableFrom(excpClass)){
					isFailure = false;
				}
			} catch (ClassNotFoundException e) {
				//TODO: log exception
			}
		}
		return isFailure;
	}

}
