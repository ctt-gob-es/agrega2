/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
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
