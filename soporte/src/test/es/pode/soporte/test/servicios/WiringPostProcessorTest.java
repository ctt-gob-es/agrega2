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
