package es.pode.util;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtilPublicacion {

	private static SessionFactory sf;
	
	private final static String dialecto_oracle	= "org.hibernate.dialect.Oracle9Dialect";
	private final static String dialecto_mysql	= "org.hibernate.dialect.MySQLDialect";
	private final static String driver_oracle	= "oracle.jdbc.driver.OracleDriver";
	private final static String driver_mysql	= "com.mysql.jdbc.Driver";
	
	private static Logger logger = Logger.getLogger(HibernateUtilPublicacion.class);
	
	public static Session getSession()
	{
		if(sf == null)
		{
//			sf = new Configuration().configure("hibernate_PUBLICACION.cfg.xml").buildSessionFactory();

			Configuration configuracion = null;
			configuracion =new Configuration();
//			Aniadimos configuracion a mano, leyendo del fichero de configuracion
			configuracion.addClass(es.pode.modelo.OdePublicado.class);// esto obliga a que el fichero de descripcion de la tabla este en el mismo directorio que la clase.
			
			configuracion.setProperty("hibernate.connection.url", Propiedades.getPropertyValue(Propiedades.ODE_URL));
			configuracion.setProperty("hibernate.connection.username", Propiedades.getPropertyValue(Propiedades.ODE_USERNAME));
			
//			Seleccionamos el driver de BD:
			String driverConfig = Propiedades.getPropertyValue(Propiedades.ODE_BD_DRIVER);
			if (Propiedades.BD_DRIVER_MYSQL.equals(driverConfig)) // si el driver es mysql
				configuracion.setProperty("hibernate.connection.driver_class", driver_mysql);
			else if (Propiedades.BD_DRIVER_ORACLE.equals(driverConfig)) // si el driver es oracle
				configuracion.setProperty("hibernate.connection.driver_class", driver_oracle);
			else // si el driver es otro, pues lo cogemos, otra cosa es que el driver este en el classpath
			{
				configuracion.setProperty("hibernate.connection.driver_class", driverConfig);
				logger.info("El driver de conexion que se utilizara es["+driverConfig+"] asegurese de que la clase esta en el classpath..");
			}
			
//			Seleccionamos el dialecto de Hibernate:
			String dialectConfig = Propiedades.getPropertyValue(Propiedades.ODE_BD_DIALECT);
			if (Propiedades.BD_DIALECT_MYSQL.equals(dialectConfig))
				configuracion.setProperty("hibernate.dialect", dialecto_mysql);
			else if (Propiedades.BD_DIALECT_ORACLE.equals(dialectConfig))
				configuracion.setProperty("hibernate.dialect", dialecto_oracle);
			else // si el dialecto es otro, pues lo cogemos, otra cosa es que sea compatible con hibernate
			{
				configuracion.setProperty("hibernate.dialect", dialectConfig);
				logger.info("El dialecto de hibernate que se utilizara es["+driverConfig+"] asegurese de que es compatible con hibernate..");
			}

			configuracion.setProperty("hibernate.connection.password", Propiedades.getPropertyValue(Propiedades.ODE_PWD));
			configuracion.setProperty("hibernate.transaction.factory_class", "org.hibernate.transaction.JDBCTransactionFactory");
			configuracion.setProperty("hibernate.current_session_context_class", "thread");
			configuracion.setProperty("hibernate.show_sql", "true");
			configuracion.setProperty("hibernate.hbm2ddl.auto", "update");
			configuracion.setProperty("hibernate.connection.autocommit", "true");
			sf = configuracion.buildSessionFactory();
		}
		return sf.openSession();
	}
	
}
