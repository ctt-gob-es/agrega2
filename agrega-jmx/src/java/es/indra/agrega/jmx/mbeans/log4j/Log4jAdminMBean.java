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
