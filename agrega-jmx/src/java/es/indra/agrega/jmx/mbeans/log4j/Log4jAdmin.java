package es.indra.agrega.jmx.mbeans.log4j;

import java.util.Enumeration;

import org.apache.log4j.Appender;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Log4jAdmin implements Log4jAdminMBean {

	public void setupCategoryLogLevel(String category, String level) {
		Logger logger = LogManager.getLogger(category);
		logger.setLevel(Level.toLevel(level));

	}

	public void setRootLoggerLevel(String level) {
		Logger rootLogger = LogManager.getRootLogger();
		rootLogger.setLevel(Level.toLevel(level));
	}

	public String obtainCategoryLogLevel(String category) {
		Logger logger = LogManager.getLogger(category);
		return logger.getEffectiveLevel().toString();
	}

	public String getRootLoggerLevel() {
		Logger rootLogger = LogManager.getRootLogger();
		return rootLogger.getEffectiveLevel().toString();
	}
	
	public void addAppenderToCategory(String category, String appenderName){
		Appender foundAppender = findAppender(appenderName);

		if (foundAppender != null){
			Logger logger = LogManager.getLogger(category);
			logger.addAppender(foundAppender);
		}
	}

	public void addAppenderToRootLogger(String appenderName){
		Appender foundAppender = findAppender(appenderName);

		if (foundAppender != null){
			Logger logger = LogManager.getRootLogger();
			logger.addAppender(foundAppender);
		}
	}
	
	public void removeAppenderFromCategory(String category, String appenderStr){
		Logger logger = LogManager.getLogger(category);
		logger.removeAppender(appenderStr);
	}

	public void removeAppenderFromRootLogger(String appenderStr){
		Logger rootLogger = LogManager.getRootLogger();
		rootLogger.removeAppender(appenderStr);
	}
	
	
	public void setRepositoryLevel(String level) {
		LogManager.getLoggerRepository().setThreshold(level);
	}

	public String getRepositoryLevel() {
		return LogManager.getLoggerRepository().getThreshold().toString();
	}	
	
    private Appender findAppender( String appenderName )
    {
    	Enumeration loggers = LogManager.getCurrentLoggers();
    	Appender foundAppender = null;
    	while (loggers.hasMoreElements() && foundAppender == null){
    		Logger logger = (Logger)loggers.nextElement();
    		foundAppender = logger.getAppender(appenderName);
    	}
    	
    	/*                log = Logger.getRootLogger();*/
    	return foundAppender;
   	
    }

	public void setupAppenderLogLevel(String appenderName, String level) {
		Appender foundAppender = findAppender(appenderName);
		if (foundAppender != null) {
			if (foundAppender instanceof AppenderSkeleton){
				((AppenderSkeleton) foundAppender).setThreshold(Level.toLevel(level));
			}
		}
	}

	public String obtainAppenderLogLevel(String appenderName) {
		String result = "NO_THRESHOLD";
		Appender foundAppender = findAppender(appenderName);
		if (foundAppender != null) {
			if (foundAppender instanceof AppenderSkeleton){
				result = ((AppenderSkeleton) foundAppender).getThreshold().toString();
			}
		}
		return result;
	}
}
