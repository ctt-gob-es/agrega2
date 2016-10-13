package es.pode.soporte.seguridad.ldap;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;
import org.springframework.ldap.AttributesMapper;
import org.springframework.ldap.LdapTemplate;
import org.springframework.ldap.support.DistinguishedName;
import org.springframework.ldap.support.filter.EqualsFilter;
import org.springframework.ldap.support.filter.Filter;
import org.springframework.ldap.support.filter.OrFilter;



public class AutenticacionLdap
{

	private static java.util.Properties pSpringProperties = null;
	private static Logger log = Logger.getLogger(AutenticacionLdap.class);
	private static String SUFIJO_ATRIBUTOS_SEGUNDO_LDAP= "Secundario";
	private static String SUFIJO_ATRIBUTOS_INTEGRACION_LDAP_SEGUNDA_RAMA= "Rama2";
	
	
	/**
     * Devuelve la lista de usuarios del ldap cuyo usuario se le pasa como parámetro.
     * Puede trabajar usando el segundo LDAP y/o la integracion para la segunda rama
     * @return List 
     * @throws java.lang.Exception Exception
     */
	
	public List getUserAnyLdap(String user, boolean usarSegundoLdap, boolean usarRama2) throws Exception{
		
		log.info("Autenticacion contra ldap, obtenemos la informacion del Ldap");
		String dnValue = "";
		//String urlValue = "";
		String userSearchFilter = "";
		int indexDn = 0;
		List datosUsuario = null;
		String[] userValues = null;
		String equalsFilterString = "cn";

		String sufijoRama2="";
    	if(!usarRama2) {
    		log.debug("Usamos la rama principal");
    		sufijoRama2="";
    	} else {
    		log.debug("Usamos la rama secundaria");
    		sufijoRama2=SUFIJO_ATRIBUTOS_INTEGRACION_LDAP_SEGUNDA_RAMA; 		
    	}
		
		final String nombre = getPropertyValue("nombre"+sufijoRama2);
		final String apellidos = getPropertyValue("apellidos"+sufijoRama2);
		final String email = getPropertyValue("email"+sufijoRama2);
		final String nif = getPropertyValue("nif"+sufijoRama2);
		final String usuario = getPropertyValue("usuario"+sufijoRama2);

		final boolean usarSegundaRama=usarRama2;
		
		try {
			
			if(!usarSegundoLdap) {
	    		log.debug("Usamos el primer LDAP");
				dnValue = getPropertyValue("ibuilder.security.ldap.url");	
				userSearchFilter = getPropertyValue("ibuilder.security.ldap.userSearchFilter");
			} else {
	    		log.debug("Usamos el segundo LDAP");
		        dnValue = getPropertyValue("ibuilder.security.ldap.url"+SUFIJO_ATRIBUTOS_SEGUNDO_LDAP);		
		        userSearchFilter = getPropertyValue("ibuilder.security.ldap.userSearchFilter"+SUFIJO_ATRIBUTOS_SEGUNDO_LDAP);
			}
	        
	        if (log.isDebugEnabled())log.debug("userSearchFilter tiene el valor "+userSearchFilter); 
			log.info("El filtro de busqueda de usuarios es'"+userSearchFilter+"'");
	        indexDn = dnValue.lastIndexOf("/");	
	        dnValue = dnValue.substring(indexDn+1, dnValue.length());	
	        if (log.isDebugEnabled())log.debug("El valor de dnValue es "+dnValue);
	        //Obtenemos el valor sobre el que vamos a crear el filtro equals
	        if(!(userSearchFilter == null))
	        {
	        	userValues = userSearchFilter.split("=");	
	        	if (log.isDebugEnabled())log.debug("userValues length "+userValues.length);
	            
	        	// equalsFilterString = userValues[0];
	        	equalsFilterString = usuario;
	            //Chequeamos el valor de la cadena sobre la que se realizará la búsqueda, en el caso de comenzar
	            //por un paréntesis se le quitará
	            if(equalsFilterString.startsWith("("))
	            {
	            	equalsFilterString = equalsFilterString.substring(1, equalsFilterString.length());
	            }
	        }
	        if (log.isDebugEnabled())log.debug("Filtro sobre el que se realizara la busqueda '"+equalsFilterString+"'");
			if (log.isDebugEnabled())log.debug("El usuario del que se quiere obtener la informacion del ldap'"+user+"'");			
	        //Se realiza la busqueda sobre el campo que recoge el atributo userSearchFilter con el user que se pasa como parametro
	        
			String filterEncoded = "";
			/*
			//Su el campo usuario especificado en el authbackend.properties tiene un operador or
			//es que se debe buscar el usuario usando varios campos a la vez. 
			//Como por ejemplo buscar al usuario usando uid y cn
			if(equalsFilterString.contains("|")) {
				
				String[] filters = equalsFilterString.split("|");
				OrFilter filter = new OrFilter();
				
				for(int i=0; i<filters.length; i++)
					filter.or(new EqualsFilter(filters[i], user));
				
				filterEncoded = filter.encode();
				
			} else {*/
				Filter filter = new EqualsFilter(equalsFilterString, user);
				filterEncoded = filter.encode();
			//}
			
	    	DistinguishedName dn = new DistinguishedName(dnValue);
	    	if (log.isDebugEnabled())log.debug("DistinguishedName '"+dn+"'");
	    
	    	LdapTemplate ldap = this.getLdapHandler(usarSegundoLdap);
	    	
	    	datosUsuario = ldap.search("", filterEncoded,
	    		new AttributesMapper() {
	                			
	    		public Object mapFromAttributes(javax.naming.directory.Attributes attrs) throws NamingException {
		        	//Chequeamos si existen todos los campos en el ldap
					log.info("Se chequean si existen todos los campos en el ldap");
		        	String apellidoDefecto = "";
		        	String apellido1 = "";
		    		String apellido2 = "";
		    		String patronApellido = null;
		    		String posicionApellido1 = null;
		    		String posicionApellido2 = null;
	    		
		    		try
		    		{
	//	        		los campos del ldap que contienen la información necesaria se definirán en el fichero authbackend.properties
		        		//Necesitaremos como mínimo tener el nombre, nif, email y usuario. El atributo apellidos puede estar vacío en el fichero
		        		//authbackend.properties, en ese caso se le pondrán los apellidos por defecto (apellido1, apellido2).
		        		patronApellido = getPropertyValue("patronApellido");
		        		posicionApellido1 = getPropertyValue("posicionApellido1");
		        		posicionApellido2 = getPropertyValue("posicionApellido2");
		        		
		           		if(checkLdapParameter(attrs,usarSegundaRama))
		           		{
		           			if (log.isDebugEnabled())log.debug("El ldap tiene todos los atributos necesarios para el registro"); 
		        			PersonLdap person = new PersonLdap();
		        			//los campos del ldap que contienen la información necesaria se definirán en el fichero authbackend.properties
							log.debug("El valor del atributo usuario del ldap tiene el siguiente valor'"+(attrs.get(usuario)).get());
		        			person.setUsuario((String)(attrs.get(usuario)).get());
		        			//Comprobamos el campo definido en el authbackend.properties definido para los apellidos
							log.debug("El atributo donde se almacenan los apellidos en el ldap tienen un valor '"+apellidos+"'");
							
		        			if((apellidos == null)|| (apellidos == "") || (apellidos == " ") || (apellidos.length() == 0))
		        			{
		        				if (log.isDebugEnabled())log.debug("En el ldap no se almacenan los apellidos");
		        				person.setApellidos("apellido1 apellido2");
		        			}else
		        			{
		        				//Existe un campo en el ldap que supuestamente almacena los apellidos
		        				//comprobamos si ese campo almacena realmente información.
		        				apellidoDefecto = (String)((attrs.get(apellidos)).get());
								log.debug("El valor de los apellidos del ldap tienen un valor '"+apellidoDefecto+"'");
		        				
		        				if((apellidoDefecto == null) || (apellidoDefecto == ""))
		        				{
		        					if (log.isDebugEnabled())log.debug("En el ldap se almacenan los apellidos pero esta vacio se le ponen los apellidos por defecto");
		        					person.setApellidos("apellido1 apellido2");
		        				}else
		        				{
		        					//Comprobare como estan almacenados los apellidos en el ldap 
		        					//en la clase WrapperAdminUsuarios se hace un split por espacio, habra que almacenarlo
		        					//de esta manera
		        					if (log.isDebugEnabled())log.debug("patronApellido "+patronApellido);
		        					if (log.isDebugEnabled())log.debug("posicionApellido1 "+posicionApellido1);
		        					
		        					log.debug("patronApellido.length() "+patronApellido.length());
		        					if(patronApellido.length() == 0)
		        					{
		        						person.setApellidos(apellidoDefecto);
		        					}else
		        					{
		        						//Hay que realizar un tratamiento a los atributos para obtener los apellidos
		        						
		        						if (log.isDebugEnabled())log.debug("patronApellido tiene un valor "+patronApellido+"'");
		        						String[] arrayApellidos = apellidoDefecto.split("\\"+patronApellido);
		        						//Comprobamos si el patronApellido es correcto, es decir, existe ese carácter dentro de la cadena con los apellidos
		        						if((arrayApellidos == null) || (arrayApellidos.length == 1))
		        						{
		        							if (log.isDebugEnabled())log.debug("El valor del atributo patronApellido no es correcto se le ponen los apellidos por defecto");
		        							person.setApellidos("apellido1 apellido2");
		        							
		        						}else 
		        						{
			        						//Comprobamos si el valor de posicionApellido1 es correcto, es decir, existe ese valor dentro del array con los apellidos
			        						if(arrayApellidos.length >= new Integer(posicionApellido1).intValue())
			        						{
			        							apellido1 = arrayApellidos[((new Integer(posicionApellido1)).intValue())-1];
				        						if (log.isDebugEnabled())log.debug("Se recoge el primer apellido "+apellido1+"'");
			        						}else
			        						{
			        							apellido1 = "apellido1";
			        						}
			        						
			        						//obtenemos el segundo apellido siempre y cuando el atributo posicionApellido2 venga relleno
			        						if (log.isDebugEnabled())log.debug("posicionApellido2 "+posicionApellido2);
			        						if((posicionApellido2 == null)||(posicionApellido2.equalsIgnoreCase("")) || (posicionApellido2.equalsIgnoreCase(" ")))
			        						{
			        							if (log.isDebugEnabled())log.debug("No tenemos el segundo apellido");
			        							
			        						} else if(arrayApellidos.length >= new Integer(posicionApellido2).intValue())
		        							{
		        								apellido2 = arrayApellidos[((new Integer(posicionApellido2)).intValue())-1];
		        								if (log.isDebugEnabled())log.debug("El valor de apellido2 "+apellido2+"'");
		        							}
			        						person.setApellidos(apellido1+" "+apellido2);  				
		        						}
		        					}	
		        				}
		        			}
		        			person.setEmail((String)(attrs.get(email)).get());
							if (log.isDebugEnabled())log.debug("Asignado valor email "+(attrs.get(email)).get()+"'");
		        			person.setNif((String)(attrs.get(nif)).get());
							if (log.isDebugEnabled())log.debug("Asignado valor nif "+(attrs.get(nif)).get()+"'");
		        			if(nombre.length() == 0)
		        			{
		        				person.setNombre("nombre");
		        			}else
		        			{
		        				person.setNombre((String)(attrs.get(nombre)).get());
								if (log.isDebugEnabled())log.debug("Asignado valor nombre "+(attrs.get(nombre)).get()+"'");
		        			}		
   	    			
		        			if (log.isDebugEnabled())log.debug("Usuario "+person.getUsuario());
		        			if (log.isDebugEnabled())log.debug("Apellidos "+person.getApellidos());
		        			return person;
		           		}
						if (log.isDebugEnabled())log.info("Falta algun atributo obligatorio para el registro");
						return null;
	        	
		    		} catch(Exception e) {
		        		log.error("Exception al obtener la informacion del ldap "+e);
		        		return null;
		    		}
	    		}
	    		}
	    	);
	    	
	        log.debug("Se obtienen los datosUsuario del ldap "+datosUsuario+"'");
	        
		} catch(Exception e) {
			  log.error("Excepcion al obtener los datos del usuario del ldap "+e);
		}
		return datosUsuario;
	}
	

	/**
     * Devuelve la lista de usuarios del ldap primario cuyo usuario se le pasa como parámetro
     * @return List 
     * @throws java.lang.Exception Exception
     */
	public List getUser(String user) throws Exception{
		List datosUsuario = null;
		try {
			datosUsuario = getUserAnyLdap(user, false, false);
		} catch (Exception e) {
			log.warn("No se encontro al usuario en la rama principal por lo que se buscara en la secundaria");
			datosUsuario = getUserAnyLdap(user, false, true);
		}
		if(datosUsuario==null || datosUsuario.isEmpty()) {
			log.debug("No se encontro al usuario en la rama principal por lo que se buscara en la secundaria");
			datosUsuario = getUserAnyLdap(user, false, true);
		}
		return datosUsuario;
	}
	
	
	/**
     * Devuelve la lista de usuarios del ldap secundario cuyo usuario se le pasa como parámetro
     * @return List 
     * @throws java.lang.Exception Exception
     */
	public List getUserLdapSecundario(String user) throws Exception{
		List datosUsuario = null;
		try {
			datosUsuario = getUserAnyLdap(user, true, false);
		} catch (Exception e) {
			log.warn("No se encontro al usuario en la rama principal por lo que se buscara en la secundaria");
			datosUsuario = getUserAnyLdap(user, true, true);
		}
		if(datosUsuario==null || datosUsuario.isEmpty()) {
			log.debug("No se encontro al usuario en la rama principal por lo que se buscara en la secundaria");
			datosUsuario = getUserAnyLdap(user, true, true);
		}
		return datosUsuario;
	}	
	
	
	/**
     * Comprueba si se encuentran en el ldap todos los atributos necesarios para dar de alta el usuario
     * @return Boolean 
     * @throws java.lang.Exception Exception
     */
	
	private Boolean checkLdapParameter(javax.naming.directory.Attributes attrs, boolean usarRama2)throws IOException
	{
		//Comprueba si en el ldap se encuentran todos los atributos necesarios para registrar un usuario en BD: email, nif y usuario
		log.info("Se comprueba si existen todos los campos necesarios en el ldap para el registro del usuario");
		Boolean resultado = Boolean.TRUE;

		String sufijoRama2="";
    	if(!usarRama2) {
    		log.debug("Usamos la rama principal");
    		sufijoRama2="";
    	} else {
    		log.debug("Usamos la rama secundaria");
    		sufijoRama2=SUFIJO_ATRIBUTOS_INTEGRACION_LDAP_SEGUNDA_RAMA; 		
    	}
		
		//String nombre = getPropertyValue("nombre");
		///String apellidos = getPropertyValue("apellidos"); Si en el ldap no aparecen los apellidos se ponen los de por defecto
		String email = getPropertyValue("email"+sufijoRama2);
		String nif = getPropertyValue("nif"+sufijoRama2);
		String usuario = getPropertyValue("usuario"+sufijoRama2);
		
		log.debug("Se chequean los attrs que se envian por parametro '"+attrs+"'");
		log.info("usuario del ldap '"+attrs.get(usuario)+"'");
		log.info("email del ldap '"+attrs.get(email)+"'");
		log.info("nif del ldap '"+attrs.get(nif)+"'");
		
		if((attrs.get(usuario) == null)||(attrs.get(email) == null)||(attrs.get(nif) == null))
		{
			log.info("Algunos de los atributos necesarios no existen en la rama del ldap");
			resultado = Boolean.FALSE;
		}
		return resultado;
		
	}
	
	/**
     * Devuelve el valor en el fichero authbackend.properties de la clave que se pasa como parámetro
     * @return String 
     * @throws java.lang.Exception IOException
     */
	private String getPropertyValue(String key) throws IOException {
		
		InputStreamSource resource = new ClassPathResource("authbackend.properties");
		InputStream fIsSpringProperties = resource.getInputStream();
		if (pSpringProperties == null) {
			pSpringProperties = new java.util.Properties();
			pSpringProperties.load(fIsSpringProperties);
		}
		fIsSpringProperties.close();
		log.debug("El valor de la clave "+key+" tiene un valor "+pSpringProperties.getProperty(key));
		return pSpringProperties.getProperty(key);
		
	}
	
	private LdapTemplate getLdapHandler(boolean usarSegundoLdap) throws Exception
	{
		LdapTemplate ldapTemplate = null;
		try
		{
			Resource resource = new ClassPathResource("springldap.xml");
			if(log.isDebugEnabled())log.debug("Obtenemos el fichero de configuracion del ldap " + resource.getFilename()+"'");
			BeanFactory factory = new XmlBeanFactory(resource);
			if(log.isDebugEnabled())log.debug("Se obtiene la factoria de conexion " + factory+"'");
			if(!usarSegundoLdap)
				ldapTemplate = (LdapTemplate) factory.getBean("ldapTemplate");
			else
				ldapTemplate = (LdapTemplate) factory.getBean("ldapTemplateAux");
			if(log.isDebugEnabled())log.debug("Se obtiene el template '" + ldapTemplate+"'");
			return ldapTemplate;
		} catch (Exception e)
		{
			log.error("Se produce la siguiente excepcion al obtener getLdapHandler() " + e);
			throw e;
		}
	}
	
	/*
	//Metodo para ofrecer compatibilidad a implementaciones antiguas
	private LdapTemplate getLdapHandler() throws Exception
	{
		return getLdapHandler(false);
	}
	*/

}
