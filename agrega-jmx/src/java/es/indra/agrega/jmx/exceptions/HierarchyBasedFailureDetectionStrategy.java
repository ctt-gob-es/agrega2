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
