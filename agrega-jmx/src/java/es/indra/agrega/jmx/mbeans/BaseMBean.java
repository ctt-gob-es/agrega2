/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
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
