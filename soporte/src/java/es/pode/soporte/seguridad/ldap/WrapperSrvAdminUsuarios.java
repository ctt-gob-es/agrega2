package es.pode.soporte.seguridad.ldap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.acegisecurity.userdetails.User;
import org.acegisecurity.userdetails.UserDetails;
//import org.acegisecurity.userdetails.ldap.LdapUserDetails;
import org.apache.log4j.Logger;
import es.pode.soporte.seguridad.servicios.GrupoVO;


import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.soporte.seguridad.servicios.SrvAdminUsuariosService;
import es.pode.soporte.seguridad.servicios.SrvAdminUsuariosServiceServiceLocator;
import es.pode.soporte.seguridad.servicios.UsuarioVO;


/**
 * Wrapper para la invocacion del web service de got para obtener dado una coleccion de nombres de roles,
 * los roles de sistema asociados.Estos roles los meteremos como GrantedAuthority en el contexto de acegi
 *  
 * */
public class WrapperSrvAdminUsuarios {

	private static String PREFIJO_ROL_ACEGI="ROLE_";
	private static Logger logger = Logger.getLogger(WrapperSrvAdminUsuarios.class);
	
	/**
	 * Obtención de los roles del usuario e inserción en el contexto de seguridad de Acegi
	 * En el caso de que no exista el usuario en BD pero si en el ldap se procederá al registro del mismo en la base de datos.
	 * Devolverá una excepción en el caso de que no se pueda dar de alta en BD por faltar algún dato en el ldap
	 * @param authorities GrantedAuthority con las autorizaciones del usuario
	 * @param usuario string con el login del usuario
	 * @param password string con la clave del usuario
	 * @return GrantedAuthority[] array de GrantedAuthority
	 * */
	public static GrantedAuthority[] obtenerRoles(GrantedAuthority authorities[],String usuario,String password)
	{
		Collection rolesAcceso = new ArrayList();
		Collection resultadoNombres = null;
		boolean excepcion = false;
		String tipoException = "";

		if(logger.isDebugEnabled())logger.debug("Obtenemos los nombres de los roles de acceso recuperados de ldap");
		for(int i=0;i<authorities.length;i++){
			rolesAcceso.add(authorities[i].getAuthority());
			if(logger.isDebugEnabled())logger.debug("ROL del LDAP: " + authorities[i].getAuthority());
		}	
		
		if (!rolesAcceso.isEmpty())
		{
			transformarRolesAcceso(rolesAcceso);
			
//			SrvAdminUsuariosServiceServiceLocator serviceLocator = new SrvAdminUsuariosServiceServiceLocator();						
			SrvAdminUsuariosService servicio = null;
			
			try
			{
				
				/* Para la invocacion del ws con seguridad*/
				if (password == null) password = ""; // ms active directory no rellena el campo password al autenticar
				
                UserDetails user = new User(usuario,password,true,false,true,true,new GrantedAuthority[0]);
                if(logger.isDebugEnabled())logger.debug("Se ha creado el UserDetails " + user);
                Authentication currentAuth = new UsernamePasswordAuthenticationToken(user,null);
                if(logger.isDebugEnabled())logger.debug("Se ha creado el Authentication " + currentAuth);
                ((UsernamePasswordAuthenticationToken)currentAuth).setDetails(user);
                SecurityContextHolder.getContext().setAuthentication(currentAuth);        
                servicio = getSrvAdminUsuariosService();
                if(logger.isDebugEnabled())logger.debug("Referencia al servicio web " + servicio);
                if(logger.isDebugEnabled())logger.debug("Recuperamos los roles del usuario del web service");
                String[] rolesUsuarios = servicio.listarRolesUsuario(usuario);
                //Comprobamos si los rolesUsuario son null, en ese caso se le dara de alta en la BD con los datos que devuelva el ldap
               
                if(logger.isDebugEnabled())logger.debug("rolesUsuarios "+Arrays.toString(rolesUsuarios));
                              
                
                if(rolesUsuarios == null)
                {
                	
                if(logger.isDebugEnabled())logger.debug("El usuario no esta en BD pero si en el ldap lo doy de alta");
            	//Comprobamos si el atributo integracionLdap esta a true para registrarle en ese caso en BD
        		if((AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.INTEGRACION_LDAP)).equalsIgnoreCase("true"))
        		{
               		if(logger.isDebugEnabled())logger.debug("El usuario esta en ldap pero no en BD");
            		AutenticacionLdap autenticacionLdap = new AutenticacionLdap();
            		if(logger.isDebugEnabled())logger.debug("autenticacionLdap "+autenticacionLdap);
            		List listaPersonLdap = autenticacionLdap.getUser(usuario);
            		//Comprobamos si el resultado de autenticacionLdap es null o no, en el caso de que sea nulo se devolverá una excepción al usuario
            		           		
            		if((listaPersonLdap == null)||((listaPersonLdap.size() == 1)&&(listaPersonLdap.get(0) == null)))
            		{
            			logger.error("Falta algun atributo necesario en el ldap");
            			excepcion = true;
            			tipoException = "NeedAttribute"; 
               		}else
            		{
            			UsuarioVO usuarioVO = null;
            			UsuarioVO  datosUsuario = null;
            			Iterator iter = listaPersonLdap.iterator();
	            		PersonLdap personLdap = null;
	            		while (iter.hasNext())
	            		{
	            			personLdap = (PersonLdap)iter.next();
	            			if(logger.isDebugEnabled())logger.debug("PersonLdap apellidos "+personLdap.getApellidos());
	            			if(logger.isDebugEnabled())logger.debug("PersonLdap usuario "+personLdap.getUsuario());
	            		}
	            		//Obtenemos la información del ldap
	            		usuarioVO = new UsuarioVO();
	            		String apellido1 = "";
	            		String apellido2 = "";
	            		String apellidos = personLdap.getApellidos();
	            		apellido1 = (apellidos.split(" "))[0];
	            		//Comprobamos que el usuario tiene dos apellidos almacenados en el ldap
	            		if((apellidos.split(" ")).length > 1)
	            		{
	            			apellido2 = ((personLdap.getApellidos()).split(" "))[1];
	            		}
	            		usuarioVO.setApellido1(apellido1);
	            		usuarioVO.setApellido2(apellido2);
	            		usuarioVO.setNombre(personLdap.getNombre());
	            		usuarioVO.setNIF(personLdap.getNif());
	            		usuarioVO.setEmail(personLdap.getEmail());
	            		usuarioVO.setUsuario(personLdap.getUsuario());
	            		usuarioVO.setRecibirCorreoPublicacion(false);	            		
	            		if(logger.isDebugEnabled())logger.debug("Registramos al usuario en BD.");
	            		rolesUsuarios = WrapperSrvAdminUsuarios.registrarIntegracionLdap(usuarioVO);
	            		if(logger.isDebugEnabled())logger.debug("Roles del usuario: " + Arrays.toString(rolesUsuarios));
	            		if(rolesUsuarios == null)
	            		{
	            			logger.error("Ya existe algún usuario registrado con los mismos datos");
	            			excepcion = true;
	            			tipoException = "UserAlreadyExist";
	            		}else
	            		{
	            			resultadoNombres = Arrays.asList(rolesUsuarios);
	            		}
	                    
	             		}
            		         		
        		}
                }else
                {
                	resultadoNombres = Arrays.asList(rolesUsuarios);
                }
                
                		
                /* Reseteamos el contexto de Acegi */				
				SecurityContextHolder.getContext().setAuthentication(null);
			}
			catch (Exception e) {
				logger.error("Se ha producido un error al invocar al web service Administración de usuarios:",e);
				excepcion = true;				
			}			
		}
		
		if (resultadoNombres != null && !resultadoNombres.isEmpty())
		{
			Collection resultado = new ArrayList();
			String nombre = null;
			GrantedAuthority ga = null;
			
			for(Iterator it = resultadoNombres.iterator(); it.hasNext(); )
			{
				nombre = (String)it.next();
				ga = new GrantedAuthorityImpl(PREFIJO_ROL_ACEGI + nombre.toUpperCase());
				if(logger.isDebugEnabled())logger.debug("ROL añadido: " + PREFIJO_ROL_ACEGI + nombre.toUpperCase());
				resultado.add(ga);
			}
			
			return (GrantedAuthority[])resultado.toArray(new GrantedAuthority[resultado.size()]);			
		}
		if (excepcion)
		{
			excepcion = false;
			GrantedAuthority ga = null;
			if(tipoException.equalsIgnoreCase("NeedAttribute"))
			{
				ga = new GrantedAuthorityImpl("errorNeedAttribute");
				//throw new LdapAuthenticationException();
			}else
			{
				if(tipoException.equalsIgnoreCase("UserAlreadyExist"))
				{
					ga = new GrantedAuthorityImpl("userAlreadyExist");
				}else
				{
					ga = new GrantedAuthorityImpl("error");
				}
			}
			GrantedAuthority[] gas = {ga};
			return gas;
		}
		return new GrantedAuthority[0];
	}

	/**
	 * Obtención de los roles del usuario a partir unicamente del usuario (para validación openID) e inserción en el contexto de seguridad de Acegi
	 * 
	 * @param usuario string con el login del usuario
	 * @return GrantedAuthority[] array de GrantedAuthority
	 * */
	public static GrantedAuthority[] obtenerRolesOpenId(String usuario)
	{
		
		Collection resultadoNombres = new ArrayList();
		Collection resultado = new ArrayList();		
		SrvAdminUsuariosServiceServiceLocator serviceLocator = new SrvAdminUsuariosServiceServiceLocator();
		if(logger.isDebugEnabled())logger.debug("serviceLocator "+serviceLocator);
		SrvAdminUsuariosService servicio = null;
			
		try
		{
			servicio = getSrvAdminUsuariosService();
            if(logger.isDebugEnabled())logger.debug("servicio "+servicio);
            String[] rolesUsuarios = servicio.listarRolesUsuario(usuario);
           if(logger.isDebugEnabled())logger.debug("rolesUsuarios "+Arrays.toString(rolesUsuarios));
            for (int j = 0; j < rolesUsuarios.length; j++)
            {
            	resultadoNombres.add(rolesUsuarios[j]);
            	if(logger.isDebugEnabled())logger.debug("Roles BBDD: " + rolesUsuarios[j]);
            }
            
           		
            /* Reseteamos el contexto de Acegi */				
			SecurityContextHolder.getContext().setAuthentication(null);
			
			if (resultadoNombres != null && !resultadoNombres.isEmpty())
			{
				String nombre = null;
				GrantedAuthority ga = null;
				
				for(Iterator it = resultadoNombres.iterator(); it.hasNext(); )
				{
					nombre = (String)it.next();
					ga = new GrantedAuthorityImpl(PREFIJO_ROL_ACEGI + nombre.toUpperCase());
					if(logger.isDebugEnabled())logger.debug("ROL añadido: " + PREFIJO_ROL_ACEGI + nombre.toUpperCase());
					resultado.add(ga);
				}
				ga = new GrantedAuthorityImpl(PREFIJO_ROL_ACEGI + "ANONYMOUS");
				resultado.add(ga);
			}			
		}
		catch (Exception e) {
			logger.error("Error al invocar al servicio web de Administración de usuarios:",e);			
		}		
		
		return (GrantedAuthority[])resultado.toArray(new GrantedAuthority[resultado.size()]);
	}
	
	
	/**
	 * Obtención de los datos del usuario
	 *  
	 * @param usuario string con el login de usuario
	 * @return datosUsuario UsurioVO
	 * */
	public static UsuarioVO obtenerDatosUsuario(String usuario)
	{
//		SrvAdminUsuariosServiceServiceLocator serviceLocator = new SrvAdminUsuariosServiceServiceLocator();			
		SrvAdminUsuariosService servicio = null;
		UsuarioVO datosUsuario = null;
		
		try
		{	
			 servicio = getSrvAdminUsuariosService();
             if(logger.isDebugEnabled())logger.debug("Ref. Servicio web " + servicio);
             datosUsuario = servicio.obtenerDatosUsuario(usuario);
             if(logger.isDebugEnabled())logger.debug("Datos del usuario: " + datosUsuario);
		}
		catch (Exception e) 
		{
			logger.error("Se ha producido un error al invocar al servicio web de Administración de usuarios:",e);				
		}			
		
		return datosUsuario;
	}
	
	/**
	 * Obtención de los roles del usuario sin seguridad más los enviados
	 * @param authorities GrantedAuthority con las autorizaciones que queramos añadir a las que tenga el usuario
	 * @param usuario string con el login del usuario
	 * @param password string con la clave del usuario
	 * @return GrantedAuthority[] array de GrantedAuthority
	 * */
	public static GrantedAuthority[] obtenerAnadirRoles(GrantedAuthority authorities[], String usuario,String password)
	{
		Collection resultadoNombres = new ArrayList();
		Collection resultado = new ArrayList();		
//		SrvAdminUsuariosServiceServiceLocator serviceLocator = new SrvAdminUsuariosServiceServiceLocator();						
		SrvAdminUsuariosService servicio = null;
			
		try
		{
			servicio = getSrvAdminUsuariosService();
            if(logger.isDebugEnabled())logger.debug("Recuperamos los roles del usuario del web service");
            String[] rolesUsuarios = servicio.listarRolesUsuario(usuario);
     
            for (int j = 0; j < rolesUsuarios.length; j++)
            {
            	resultadoNombres.add(rolesUsuarios[j]);
            	if(logger.isDebugEnabled())logger.debug("Roles BBDD: " + rolesUsuarios[j]);
            }
            
            /* Se añaden los roles pasados por el usuario */
			if (authorities.length > 0)
			{
				for (int i = 0; i < authorities.length; i++)
				{
					resultadoNombres.add(authorities[i].getAuthority());
					if(logger.isDebugEnabled())logger.debug("Roles añadidos: " + authorities[i].getAuthority());
				}
			}
                   		
            /* Reseteamos el contexto de Acegi */				
			SecurityContextHolder.getContext().setAuthentication(null);
			
			if (resultadoNombres != null && !resultadoNombres.isEmpty())
			{
				String nombre = null;
				GrantedAuthority ga = null;
				
				for(Iterator it = resultadoNombres.iterator(); it.hasNext(); )
				{
					nombre = (String)it.next();
					ga = new GrantedAuthorityImpl(PREFIJO_ROL_ACEGI + nombre.toUpperCase());
					if(logger.isDebugEnabled())logger.debug("ROL añadido: " + PREFIJO_ROL_ACEGI + nombre.toUpperCase());
					resultado.add(ga);
				}
			}			
		}
		catch (Exception e) {
			logger.error("Error al invocar al servicio web de Administración de usuarios:",e);			
		}		
		
		return (GrantedAuthority[])resultado.toArray(new GrantedAuthority[resultado.size()]);
	}
	
	
	/**
	 * Obtención de los datos del usuario
	 *  
	 * @param urlOpenId string con la url del openId del usuario
	 * @return datosUsuario UsurioVO
	 * */
	public static UsuarioVO obtenerDatosUsuarioOpenId(String urlOpenId)
	{
		if(logger.isDebugEnabled())logger.debug("obtenerDatosUsuarioOpenId urlOpenId "+urlOpenId);
		SrvAdminUsuariosServiceServiceLocator serviceLocator = new SrvAdminUsuariosServiceServiceLocator();
		if(logger.isDebugEnabled())logger.debug("serviceLocator "+serviceLocator);
		SrvAdminUsuariosService servicio = null;
		UsuarioVO datosUsuario = null;
		
		try
		{	
			 servicio = getSrvAdminUsuariosService();
             if(logger.isDebugEnabled())logger.debug("Ref. Servicio web " + servicio);
             datosUsuario = servicio.obtenerUsuarioConOpenId(urlOpenId);
             if(logger.isDebugEnabled())logger.debug("Datos del usuario: " + datosUsuario);
		}
		catch (Exception e) 
		{
			logger.error("Se ha producido un error al invocar al servicio web de Administración de usuarios:",e);				
		}			
		
		return datosUsuario;
	}
	
	
	/**
	 * Obtención de los datos del usuario
	 *  
	 * @param usuario string con el login de usuario
	 * @return datosUsuario UsurioVO
	 * */
	public static String[] registrarIntegracionLdap(UsuarioVO usuario)
	{
		SrvAdminUsuariosServiceServiceLocator serviceLocator = new SrvAdminUsuariosServiceServiceLocator();			
		SrvAdminUsuariosService servicio = null;
		String[] datosUsuario = null;
		GrupoVO grupo = null;
		
		try
		{	
			 //servicio = serviceLocator.getSrvAdminUsuariosService();
			 servicio = getSrvAdminUsuariosService();
             if(logger.isDebugEnabled())logger.debug("Ref. Servicio web " + servicio);
             grupo = servicio.obtenerGrupoRol(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ROL_EMPAQUETADOR));
     		 if(logger.isDebugEnabled())logger.debug("Se obtiene el grupo cuyo unico rol sea el de docente el identificador "+grupo.getId()+" descripcion "+grupo.getDescripcion());
     		 usuario.setGrupos(new GrupoVO[]{grupo});
     		if(logger.isDebugEnabled())logger.debug("Asignado el grupo al usuario ");
             //Asignamos al usuario los grupos 
             datosUsuario = servicio.registrarIntegracionLdap(usuario);
             if(logger.isDebugEnabled())logger.debug("Datos del usuario: " + Arrays.toString(datosUsuario));
		}
		catch (Exception e) 
		{
			logger.error("Se ha producido un error al invocar al servicio web de Administración de usuarios:",e);				
		}			
		
		return datosUsuario;
	}
	
	/**
	 * Método que recibe una coleccion de nombres con el prefijo ROLE_ y devuelve esa misma coleccion
	 * sin el prefijo.
	 * 
	 * @param rolesAcceso Collection  de los roles
	 * */
	private static void transformarRolesAcceso(Collection rolesAcceso)
	{
		Collection tmp=new ArrayList();
		
		for(Iterator it=rolesAcceso.iterator(); it.hasNext(); )
		{
			tmp.add(((String)it.next()).substring(PREFIJO_ROL_ACEGI.length()));			
		}
		rolesAcceso.clear();
		rolesAcceso.addAll(tmp);
	}
	
	
    /**
     * Returns a reference to the srvAdminUsuariosService imported service.
     * @return es.pode.soporte.seguridad.servicios.SrvAdminUsuariosServiceImpl 
     * @throws java.lang.Exception Exception
     */
    protected final static es.pode.soporte.seguridad.servicios.SrvAdminUsuariosService getSrvAdminUsuariosService()
        throws java.lang.Exception
    {
        String srvAdminUsuariosServiceFile="importedServices.properties";	    
        java.io.InputStream srvAdminUsuariosServiceInputStream=WrapperSrvAdminUsuarios.class.getClassLoader().getResourceAsStream(srvAdminUsuariosServiceFile);
        java.util.Properties srvAdminUsuariosServiceProperties = new java.util.Properties();
        srvAdminUsuariosServiceProperties.load(srvAdminUsuariosServiceInputStream);
        String srvAdminUsuariosServiceEndPointAddress="";
        srvAdminUsuariosServiceEndPointAddress=(String) srvAdminUsuariosServiceProperties.get("srvAdminUsuariosServicePort");
        if(logger.isDebugEnabled())logger.debug("srvAdminUsuariosServiceEndPointAddress del fichero --> " + srvAdminUsuariosServiceEndPointAddress);
			es.pode.soporte.seguridad.servicios.SrvAdminUsuariosServiceService srvAdminUsuariosService = new es.pode.soporte.seguridad.servicios.SrvAdminUsuariosServiceServiceLocator();                                                                                                                                                                                                                                                    
        if (srvAdminUsuariosServiceEndPointAddress.length()>0) 
			((es.pode.soporte.seguridad.servicios.SrvAdminUsuariosServiceServiceLocator)srvAdminUsuariosService).setSrvAdminUsuariosServiceEndpointAddress(srvAdminUsuariosServiceEndPointAddress);				
    	es.pode.soporte.seguridad.servicios.SrvAdminUsuariosService port = srvAdminUsuariosService.getSrvAdminUsuariosService();	    
        return port;
    }
    
    /**
	 * Obtención de los datos del usuario
	 *  
	 * @param nif string con el login de usuario
	 * @return datosUsuario UsurioVO
	 * */
	public static UsuarioVO obtenerUsuario(String nif)
	{
//		SrvAdminUsuariosServiceServiceLocator serviceLocator = new SrvAdminUsuariosServiceServiceLocator();			
		SrvAdminUsuariosService servicio = null;
		UsuarioVO datosUsuario = null;
		
		try
		{	
			 servicio = getSrvAdminUsuariosService();
             if(logger.isDebugEnabled())logger.debug("Ref. Servicio web " + servicio);
             datosUsuario = servicio.obtenerUsuario(nif);
             if(logger.isDebugEnabled())logger.debug("Datos del usuario: " + datosUsuario);
		}
		catch (Exception e) 
		{
			logger.error("Se ha producido un error al invocar al servicio web de Administración de usuarios:",e);				
		}			
		
		return datosUsuario;
	}
	
	/**
     * Returns a reference to the srvAdminUsuariosService imported service.
	 * @param url Url del nodo origen de la petición federada
     * @return es.pode.soporte.seguridad.servicios.SrvAdminUsuariosServiceImpl 
     * @throws java.lang.Exception Exception
     */
    protected static es.pode.soporte.seguridad.servicios.SrvAdminUsuariosService getSrvAdminUsuariosServiceFederado(String url)
        throws java.lang.Exception
    {
       //La url tendra que venir con el subdominio si es el caso
        String srvAdminUsuariosServiceEndPointAddress="";
       	es.pode.soporte.seguridad.servicios.SrvAdminUsuariosServiceService srvAdminUsuariosService = new es.pode.soporte.seguridad.servicios.SrvAdminUsuariosServiceServiceLocator();
       	srvAdminUsuariosServiceEndPointAddress = "http://"+url+"/adminusuarios/services/SrvAdminUsuariosService";
       	((es.pode.soporte.seguridad.servicios.SrvAdminUsuariosServiceServiceLocator)srvAdminUsuariosService).setSrvAdminUsuariosServiceEndpointAddress(srvAdminUsuariosServiceEndPointAddress);				
    	es.pode.soporte.seguridad.servicios.SrvAdminUsuariosService port = srvAdminUsuariosService.getSrvAdminUsuariosService();	    
        return port;
    }
    
    /**
	 * Obtención de los datos del usuario
     * @param usuario usuario del que se quieren obtener la información
     * @param nodoOrigen nodo sobre el cual se va a realizar la petición
	 * @return datosUsuario UsurioVO
	 * */
	public static UsuarioVO obtenerDatosUsuarioFederado(String usuario,String nodoOrigen)
	{
		if(logger.isDebugEnabled())logger.debug("obtenerDatosUsuarioFederado para el usuario "+usuario+" en el nodo "+nodoOrigen);
		SrvAdminUsuariosService servicio = null;
		UsuarioVO datosUsuario = null;
		
		try
		{	
			 servicio = getSrvAdminUsuariosServiceFederado(nodoOrigen);
             if(logger.isDebugEnabled())logger.debug("Ref. Servicio web " + servicio);
             datosUsuario = servicio.obtenerDatosUsuario(usuario);
             if(logger.isDebugEnabled())logger.debug("Datos del usuario: " + datosUsuario);
		}
		catch (Exception e) 
		{
			logger.error("Se ha producido un error al invocar al servicio web de Administración de usuarios federado:",e);				
		}			
		
		return datosUsuario;
	}
}
