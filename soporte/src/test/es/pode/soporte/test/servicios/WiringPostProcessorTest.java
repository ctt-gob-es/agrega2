/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.soporte.test.servicios;

import junit.framework.Assert;
import junit.framework.TestCase;
import es.pode.soporte.test.TestSpringBootstrap;

public class WiringPostProcessorTest extends TestCase {

	
	private SimpleTestBean simpleTestBean = (SimpleTestBean) TestSpringBootstrap.getBean("simpleBean");
	private SimpleTestBean overridedSimpleTestBean = (SimpleTestBean) TestSpringBootstrap.getBean("overridedSimpleBean");

	public WiringPostProcessorTest() {
		super();
	}
	
	public void testSimpleWiring() {
		
		Assert.assertEquals( simpleTestBean.getSecondProperty(), TestSpringBootstrap.getBean("secondProperty") );
	}
	
	public void testSimpleNotOverridingWiring() {
		
		Assert.assertEquals(simpleTestBean.getFirstProperty(), "uno" );
	}
	
	public void testSimpleOverridingWiring() {
		
		Assert.assertEquals(overridedSimpleTestBean.getFirstProperty(), TestSpringBootstrap.getBean("firstPropertyOverride"));
	}
}
