/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.parseadorXML;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBootstrapContextLoader {

	private static SpringBootstrapContextLoader INSTANCE = new SpringBootstrapContextLoader();

	private SCORM2004Dao dao = null;
	
	private LomESDao lomesDao = null;

	private ClassPathXmlApplicationContext context = null;
	
	private SpringBootstrapContextLoader() {
		super();
		try {
		this.context = new ClassPathXmlApplicationContext(
				"parseadorXML-applicationContext.xml");
		this.dao = (SCORM2004Dao) this.context.getBean("scorm2004Dao",
				SCORM2004Dao.class);
		this.lomesDao = (LomESDao) this.context.getBean("lomesDao",
				LomESDao.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static SCORM2004Dao getScormDao() {
		return INSTANCE.dao;
	}
	
	public static LomESDao getLomesDao() {
		return INSTANCE.lomesDao;
	}
}
