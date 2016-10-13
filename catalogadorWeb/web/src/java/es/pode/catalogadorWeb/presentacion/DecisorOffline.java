package es.pode.catalogadorWeb.presentacion;

public class DecisorOffline {
	public static final String FILE = "/offline.properties";
	public static final String KEY = "offline";
	private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(DecisorOffline.class);
	
	public static boolean esOffline() {
		boolean result = false;
		java.net.URL file = DecisorOffline.class.getResource(FILE);
		logger.debug("Hay fichero offline.properties? : " + (file!=null));
		if(file!=null) {
			java.util.Properties props = new java.util.Properties();
			try {
				props.load(file.openStream());
				String property = props.getProperty(KEY);
				if("true".equalsIgnoreCase(property)) result=true;
			} catch (Exception e) {
				logger.debug("No se ha podido cargar el fichero offline.properties",e);
			}
		}
		return result;
	}
}
