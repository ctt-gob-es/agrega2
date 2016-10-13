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
