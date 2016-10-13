/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.soporte.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringBootstrap {

	private TestSpringBootstrap() {
		super();
		
		context = new ClassPathXmlApplicationContext("test-applicationContext.xml");
		
	}
	
	private static TestSpringBootstrap instance = new TestSpringBootstrap();
	private ClassPathXmlApplicationContext context;
	
	public static Object getBean( String beanName ) {
		return instance.context.getBean(beanName);
	}
}
