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
