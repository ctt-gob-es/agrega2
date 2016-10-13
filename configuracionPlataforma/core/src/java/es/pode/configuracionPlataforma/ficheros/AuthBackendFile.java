package es.pode.configuracionPlataforma.ficheros;

import java.io.File;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import es.pode.configuracionPlataforma.negocio.dominio.PropiedadVO;




public class AuthBackendFile {

	protected static final Logger logger = Logger.getLogger(AuthBackendFile.class);
	
	
	/**
	 * Fichero/s destino de las variables de configuracion
	 */

	public static String ficheroDestino = "authbackend.properties";
	
	public static String ficheroPlantilla = "/plantillas/authbackend.properties";

	/**
	 * Este metodo modifica el valor de las variables que le pasan en la plantilla del fichero authbackend.properties
	 * Supone que el par variable-valor no es nulo y que cubren todas las variables que se encuentran en el fichero.
	 * @param prop Propiedad con el nombre de la variable y el valor.
	 * @param homeFile Directorio raiz donde se encuentra el fichero destino de la sustitucion.
	 */
	public static void modificaVariables(PropiedadVO[] prop, String homeFile)throws Exception
	{
		Map<String,PropiedadVO> hProps = new Hashtable<String,PropiedadVO>();
		
		PropiedadVO propiedad=null;
		
		for (int i = 0; i < prop.length; i++) {
			hProps.put(prop[i].getNombre(), prop[i]);			
		}
		boolean bResetearSecundario=false;
		
		if (hProps.get("contextoLdapSecundario").getValor().equals("false"))
			bResetearSecundario=true;
		
		if (hProps.get("ldapurlsecundario").getValor().equals("") || bResetearSecundario)
		{
			propiedad = hProps.get("ldapurlsecundario");
			propiedad.setValor(hProps.get("ldapurl").getValor());
			hProps.put("ldapurlsecundario", propiedad);
		}
		if (hProps.get("ldappathsecundario").getValor().equals("") || bResetearSecundario)
		{
			propiedad = hProps.get("ldappathsecundario");
			propiedad.setValor(hProps.get("ldappath").getValor());
			hProps.put("ldappathsecundario", propiedad);
		}
		if (hProps.get("ldapmanagerDNsecundario").getValor().equals("") || bResetearSecundario)
		{
			propiedad = hProps.get("ldapmanagerDNsecundario");
			propiedad.setValor(hProps.get("ldapmanagerDN").getValor());
			hProps.put("ldapmanagerDNsecundario", propiedad);
		}
		if (hProps.get("ldapmanagerPasswdsecundario").getValor().equals("") || bResetearSecundario)
		{
			propiedad = hProps.get("ldapmanagerPasswdsecundario");
			propiedad.setValor(hProps.get("ldapmanagerPasswd").getValor());
			hProps.put("ldapmanagerPasswdsecundario", propiedad);
		}
		if (hProps.get("ldapusuariosbindsubpathsecundario").getValor().equals("") || bResetearSecundario)
		{
			propiedad = hProps.get("ldapusuariosbindsubpathsecundario");
			propiedad.setValor(hProps.get("ldapusuariosbindsubpath").getValor());
			hProps.put("ldapusuariosbindsubpathsecundario",propiedad);
		}
		if (hProps.get("userSearchFiltersecundario").getValor().equals("") || bResetearSecundario)
		{
			propiedad = hProps.get("userSearchFiltersecundario");
			propiedad.setValor(hProps.get("userSearchFilter").getValor());
			hProps.put("userSearchFiltersecundario",propiedad);
		}
		if (hProps.get("groupRoleAttributesecundario").getValor().equals("") || bResetearSecundario)
		{
			propiedad = hProps.get("groupRoleAttributesecundario");
			propiedad.setValor(hProps.get("groupRoleAttribute").getValor());
			hProps.put("groupRoleAttributesecundario",propiedad);
		}
		if (hProps.get("groupSearchBasesecundario").getValor().equals("") || bResetearSecundario)
		{
			propiedad = hProps.get("groupSearchBasesecundario");
			propiedad.setValor(hProps.get("groupSearchBase").getValor());
			hProps.put("groupSearchBasesecundario",propiedad);
		}
		
		PropiedadVO[] propsModif = new PropiedadVO[prop.length];
		int i=0;
		for (Iterator<PropiedadVO> iterator = hProps.values().iterator(); iterator.hasNext();) {
			PropiedadVO propModif =  iterator.next();
			propsModif[i]=propModif;
			i++;
		}
		
		String ficheroDestinoDisco = homeFile + File.separator + "conf" + File.separator +ficheroDestino;		
		if (logger.isDebugEnabled())
			logger.debug("Vamos a actualizar el fichero :" + ficheroDestinoDisco);
				
		Sed.crearDirConPermisos(homeFile);					
		
		InputStream ipstrm = AuthBackendFile.class.getResourceAsStream(ficheroPlantilla);
		Sed.sustituir(ipstrm, ficheroDestinoDisco,prop);
		
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
