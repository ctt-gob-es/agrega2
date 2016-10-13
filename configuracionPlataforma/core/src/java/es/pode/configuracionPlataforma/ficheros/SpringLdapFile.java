package es.pode.configuracionPlataforma.ficheros;

import java.io.File;
import java.io.InputStream;

import org.apache.log4j.Logger;

import es.pode.configuracionPlataforma.negocio.dominio.PropiedadVO;



public class SpringLdapFile {

	protected static final Logger logger = Logger.getLogger(SpringLdapFile.class);
	
	/**
	 * Fichero/s destino de las variables de configuracion
	 */
	public static String ficheroDestino = "springldap.xml";
	
	public static String ficheroPlantilla = "/plantillas/springldap.xml";
	//public static String ficheroPlantillaDosLdaps = "/plantillas/springldap_2_ldaps.xml";
	public static String ficheroPlantillaSecundario = "/plantillas/springldap_secundario.xml";
	public static String ficheroPlantillaProcomun = "/plantillas/springldap_procomun.xml";
	public static String ficheroPlantillaSecundarioProcomun = "/plantillas/springldap_secundario_procomun.xml";
	
		
	/**
	 * Este metodo modifica el valor de las variables que le pasan en la plantilla del fichero springldap.xml
	 * Supone que el par variable-valor no es nulo y que cubren todas las variables que se encuentran en el fichero.
	 * @param prop Propiedad con el nombre de la variable y el valor.
	 * @param homeFile Directorio raiz donde se encuentra el fichero destino de la sustitucion.
	 */
	public static void modificaVariables(PropiedadVO[] props, String homeFile)throws Exception
	{	
		String ficheroDestinoDisco = homeFile + File.separator + "conf" + File.separator +ficheroDestino;		
		if (logger.isDebugEnabled())
			logger.debug("Vamos a actualizar el fichero :" + ficheroDestinoDisco);
		

		// Recorremos las propiedades del fichero en bbdd para analizar si tiene o no ldap secundario y ldap procomun
		// Si se informa valor SI en el campo contextoLdapSecundario es que trabaja con dos ldaps.
		// Si se informa valor SI en el campo contextoLdapProcomun es que trabaja con ldap de Procomun.
		boolean bLdapSecundario = false;
		boolean bLdapProcomun = false;
		
		for (int i = 0; i < props.length; i++) {
			PropiedadVO prop = props[i];
			if (prop.getNombre().equals("contextoLdapSecundario") && prop.getValor()!= null && prop.getValor().equals("true") )
			{
				bLdapSecundario = true;
			}
			if (prop.getNombre().equals("contextoLdapProcomun") && prop.getValor()!= null && prop.getValor().equals("true") )
			{
				bLdapProcomun = true;
			}
			
			if (prop.getNombre().equals("ldapusuariosbindsubpath")&& !prop.getValor().equals("") )
				props[i].setValor(props[i].getValor()+",");
			if (prop.getNombre().equals("ldapusuariosbindsubpathsecundario")&& !prop.getValor().equals("") )
				props[i].setValor(props[i].getValor()+",");
			if (prop.getNombre().equals("ldapusuariosbindsubpathProcomun")&& !prop.getValor().equals("") )
				props[i].setValor(props[i].getValor()+",");
			
			if (logger.isDebugEnabled())
				logger.debug("Valor propiedad :" + prop.getNombre() + ":" + props[i].getValor());
			
		}
		
					
		Sed.crearDirConPermisos(homeFile);			
		
		String plantilla = ficheroPlantilla;
		
		if (bLdapSecundario && bLdapProcomun)
			plantilla = ficheroPlantillaSecundarioProcomun;
		if (bLdapSecundario && !bLdapProcomun)
			plantilla = ficheroPlantillaSecundario;
		if (!bLdapSecundario && bLdapProcomun)
			plantilla = ficheroPlantillaProcomun;
				 
		
		InputStream ipstrm = AuthBackendFile.class.getResourceAsStream(plantilla);
		Sed.sustituir(ipstrm, ficheroDestinoDisco,props);
		
		if (logger.isDebugEnabled())
			logger.debug("Fichero actualizado correctamente");

		return;	
	}
	
	public static void crearBackup(String homeFile, String nombreInstancia)throws Exception
	{		
		
		String homeBackup = System.getProperty("jboss.server.data.dir");		
		
		String ficheroOrigen = homeFile + File.separator + "conf" + File.separator +ficheroDestino;		
		String ficheroBackup = homeBackup + File.separator + nombreInstancia + File.separator +ficheroDestino;
		
		File dirBackup = new File(homeBackup + File.separator + nombreInstancia);
		
		if (!dirBackup.exists())
			dirBackup.mkdir();

		if (logger.isDebugEnabled())
			logger.debug("Vamos a crear backup del fichero :" + ficheroOrigen + " en :" + ficheroBackup);
						
		Sed.copiar(ficheroOrigen, ficheroBackup);
		
		if (logger.isDebugEnabled())
			logger.debug("Backup creado correctamente");

		return;
	}
	
	public static void restaurarBackup(String homeFile, String nombreInstancia)throws Exception
	{		
		
		String homeBackup = System.getProperty("jboss.server.data.dir");		
		
		String ficheroBackup = homeFile + File.separator + "conf" + File.separator +ficheroDestino;		
		String ficheroOrigen = homeBackup + File.separator + nombreInstancia + File.separator +ficheroDestino;
			
		if (logger.isDebugEnabled())
			logger.debug("Vamos a restaurar backup del fichero :" + ficheroOrigen + " en :" + ficheroBackup);
				
		Sed.copiar(ficheroOrigen, ficheroBackup);
		
		if (logger.isDebugEnabled())
			logger.debug("Backup restaurado correctamente");

		return;
	}
}
