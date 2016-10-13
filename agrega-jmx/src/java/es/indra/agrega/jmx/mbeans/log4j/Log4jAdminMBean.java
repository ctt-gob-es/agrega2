/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.indra.agrega.jmx.mbeans.log4j;

public interface Log4jAdminMBean {

	public void setupAppenderLogLevel(String appender, String level);
	
	public String obtainAppenderLogLevel(String appender);
	
	public void setupCategoryLogLevel(String category, String level);
	
	public String obtainCategoryLogLevel(String category);
	
	public void setRootLoggerLevel(String level);
	
	public String getRootLoggerLevel();

	public void setRepositoryLevel(String level);
	
	public String getRepositoryLevel();
	
	public void addAppenderToCategory(String category, String appenderName);
	
	public void addAppenderToRootLogger(String appenderName);
	
	public void removeAppenderFromCategory(String category, String appenderStr);
	
	public void removeAppenderFromRootLogger(String appenderStr);
	
}
