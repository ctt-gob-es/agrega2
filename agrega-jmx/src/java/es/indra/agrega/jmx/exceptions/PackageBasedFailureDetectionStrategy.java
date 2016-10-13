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
public class PackageBasedFailureDetectionStrategy implements
		FailureDetectionStrategy {

	private Set excludedPackages = new HashSet();
	
	
	public Set getExcludedPackages() {
		return excludedPackages;
	}


	public void setExcludedPackages(Set excludedPackages) {
		this.excludedPackages = excludedPackages;
	}

	public void addExcludedPackage(String pack){
		this.excludedPackages.add(pack);
	}
	
	public void removeExcludedPackage(String pack){
		this.excludedPackages.remove(pack);
	}


	/**
	 * 
	 * @see es.indra.ibuilder.jmx.exceptions.FailureDetectionStrategy#isFailure(java.lang.Throwable)
	 */
	public boolean isFailure(Throwable exception) {
		boolean isFailure = true;
		String excpClass = exception.getClass().getName();
		
		Iterator it = excludedPackages.iterator();
		
		while (isFailure && it.hasNext()){
			String currentPackage = (String)it.next();
			if (excpClass.startsWith(currentPackage)){
				isFailure = false;
			}
		}
		return isFailure;
	}

}
