package es.pode.soporte.seguridad.ldap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttributes;
import javax.naming.ldap.Control;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.userdetails.ldap.LdapUserDetails;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;
import es.pode.soporte.seguridad.ldap.WrapperSrvAdminUsuarios;
import es.pode.soporte.seguridad.servicios.UsuarioVO;



/**
 * UserDetails de Agrega para cuando usamos como back-end el ldap.
 * Los atributos nuevos son:
 * -idioma predeterminado de las búsquedas
 * -idioma
 * -nombre completo
 * -correo electrónico
 * -tipo de empaquetador
 * -tipo visualizador
 * -si tiene perfil público
 * Se añadirán dos nuevos campos: tipo de catalogador y la cuota
 * */
public class LdapUserDetailsAgrega implements LdapUserDetails
{

	private static final long serialVersionUID = -2911263891208481635L;
	private static final GrantedAuthority NO_AUTHORITIES[] = new GrantedAuthority[0];
    private static final Control NO_CONTROLS[] = new Control[0];
    private Attributes attributes;
    private String dn;
    private String password;
    private String username;
    private GrantedAuthority authorities[];
    private Control controls[];
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    
    /**
     * Idioma predeterminado de las búsquedas
     * */
    private String idiomaBusqueda;
       
    /**
     * Codigo idioma del portal. Lo obtenemos del atributo preferredlanguage de ldap
     * */
    private String idioma;
    
    /**
     * Nombre completo del usuario.Lo obtenemos del atributo sn de ldap
     * */
    private String nombreCompleto;
        
    /**
     * Correo electrónico
     * */
    private String mail;    

    /**
     * Tipo de empaquetador elegido por defecto del usuario. Atributo employeeType del ldap
     * */
    private String empaquetador;    

    /**
     * Login del usuario. Atributo cn del ldap
     * */
    private String login;   
    
    /**
     * Tipo de catalogador
     * */
    private String catalogador;
    
    /**
     * Tipo de visualizador
     * */
    private String visualizador;

    /**
     * Cuota del usuario
     * */
    private Long cuota;
    
    
	 /*
       * Usuario público
     * */
    private Boolean usuarioPublico;

    
	public static class Essence
    {
		private static Logger logger = Logger.getLogger(Essence.class);
		
        public Essence addAuthority(GrantedAuthority a)
        {
            mutableAuthorities.add(a);
            return this;
        }

        public LdapUserDetails createUserDetails()
        {
            Assert.notNull(instance, "Essence can only be used to create a single instance");
            instance.authorities = getGrantedAuthorities();
            LdapUserDetails newInstance = instance;
            instance = null;            
            return newInstance;
        }

        public GrantedAuthority[] getGrantedAuthorities()
        {
            return (GrantedAuthority[])mutableAuthorities.toArray(new GrantedAuthority[0]);
        }

        public Essence setAccountNonExpired(boolean accountNonExpired)
        {
            instance.accountNonExpired = accountNonExpired;
            return this;
        }

        public Essence setAccountNonLocked(boolean accountNonLocked)
        {
            instance.accountNonLocked = accountNonLocked;
            return this;
        }

        /*
         * Modificamos este metodo para setear los siguientes atributos:
         * -idioma de las búsquedas
         * -idioma.
         * -nombreCompleto
         * -Correo electrónico
         * -Tipo de empaquetador
         * -Login del usuario
         * - Tipo de catalogador
         * - Cuota del usuario
         * - Tipo de visualizador
         Usuario Publico
         * */
        public Essence setAttributes(Attributes attributes) 
        {
            instance.attributes = attributes;
                         
            try 
            {
        		if(logger.isDebugEnabled())logger.debug("Se añaden los atributos propios del usuario");            	            	
				instance.idiomaBusqueda=(String)attributes.get("initials").get(0);
				instance.idioma=(String)attributes.get("preferredlanguage").get(0);
				instance.nombreCompleto=(String)attributes.get("sn").get(0);
				instance.mail=(String)attributes.get("mail").get(0);
				instance.empaquetador=(String)attributes.get("employeeType").get(0);
				instance.login=(String)attributes.get("cn").get(0);
				instance.cuota=(Long)attributes.get("employeeNumber").get(0);
				instance.catalogador=(String)attributes.get("businessCategory").get(0);
				instance.visualizador=(String)attributes.get("businessCategory").get(0);
				instance.usuarioPublico=(Boolean)attributes.get("businessCategory").get(0);
					
			} 
            catch (NamingException e) 
            {
            	logger.error(e);
			}
            
            return this;
        }
        
        /*
         * Modificamos este metodo para setear los siguientes atributos:
         * -idioma de las búsquedas
         * -idioma.
         * -nombreCompleto
         * -Correo electrónico
         * -Tipo de empaquetador
         * -Login del usuario
         * - Tipo de catalogador
         * - Cuota del usuario
       
         * - Tipo de visualizador
         * En el caso de que se trate de un usuario que no este registrado en la BD se recogerá la información necesaria del ldap y se registrará en BD
         * */
        public Essence setDatosUsuario(String usuario) 
        {
        	UsuarioVO usuarioVO = null;
            try 
            {
        		if(logger.isDebugEnabled())logger.debug("Se añaden los datos del usuario recuperados de un web service");
            	UsuarioVO datosUsuario = WrapperSrvAdminUsuarios.obtenerDatosUsuario(usuario);
                     	
				instance.idiomaBusqueda = datosUsuario.getIdiomaBusqueda();				
        		if(logger.isDebugEnabled())logger.debug("Idioma de búsqueda: " + instance.idiomaBusqueda);
        		
				instance.idioma = datosUsuario.getIdioma();
        		if(logger.isDebugEnabled())logger.debug("Idioma: " + instance.idioma);

				instance.mail = datosUsuario.getEmail();				
        		if(logger.isDebugEnabled())logger.debug("Email: " + instance.mail);

				instance.empaquetador = datosUsuario.getTipoEmpaquetador();
				if(logger.isDebugEnabled())logger.debug("Tipo empaquetador: " + instance.empaquetador);

				instance.login = datosUsuario.getUsuario();
				if(logger.isDebugEnabled())logger.debug("Login: " + instance.login);

				StringBuffer nombre = new StringBuffer();
				nombre.append(datosUsuario.getNombre());
				nombre.append(" ");
				nombre.append(datosUsuario.getApellido1());
				if(logger.isDebugEnabled())logger.debug("El segundo apellido es "+datosUsuario.getApellido2());
				if (datosUsuario.getApellido2()!=null){
					nombre.append(" ");
					nombre.append(datosUsuario.getApellido2());
				}
				
				instance.nombreCompleto = nombre.toString();
				if(logger.isDebugEnabled())logger.debug("Nombre: " + instance.nombreCompleto);
				
				instance.catalogador = datosUsuario.getTipoCatalogador();
				if(logger.isDebugEnabled())logger.debug("Catalogador: "+ instance.catalogador);
				
				instance.visualizador = datosUsuario.getTipoVisualizador();
				if(logger.isDebugEnabled())logger.debug("visualizador: "+ instance.visualizador);

				instance.cuota = datosUsuario.getCuota();
				if(logger.isDebugEnabled())logger.debug("Cuota: "+ instance.cuota);
            	//}
				//Para la obtención del usuario publico utilizamos el metodo WrapperSrvAdminUsuarios.obtenerUsuario a partir del nif
				
				UsuarioVO datosUsuarioPublico = WrapperSrvAdminUsuarios.obtenerUsuario(datosUsuario.getNIF());
								
				if(datosUsuarioPublico.getUsuarioPublico()!=null && datosUsuarioPublico.getUsuarioPublico().getActivo()){
					instance.usuarioPublico=true;
				}else{
					instance.usuarioPublico=false;
				}
				if(logger.isDebugEnabled())logger.debug("usuarioPublico: "+ instance.usuarioPublico);
			} 
            catch (Exception e) 
            {
            	logger.error(e);
			}
            
            return this;
        }
        
        
        public Essence setDatosUsuarioIdentidadFederada(String usuario,String nodoOrigen) 
        {
        	UsuarioVO usuarioVO = null;
            try 
            {
            	
            	if(logger.isDebugEnabled())logger.debug("setDatosUsuarioIdentidadFederada");
            	
            	
            	UsuarioVO datosUsuario = WrapperSrvAdminUsuarios.obtenerDatosUsuarioFederado(usuario, nodoOrigen);
            	
            	instance.idiomaBusqueda = datosUsuario.getIdiomaBusqueda();				
        		if(logger.isDebugEnabled())logger.debug("Idioma de búsqueda: " + instance.idiomaBusqueda);
        		
				instance.idioma = datosUsuario.getIdioma();
        		if(logger.isDebugEnabled())logger.debug("Idioma: " + instance.idioma);

				instance.mail = datosUsuario.getEmail();				
        		if(logger.isDebugEnabled())logger.debug("Email: " + instance.mail);

				instance.empaquetador = datosUsuario.getTipoEmpaquetador();
				if(logger.isDebugEnabled())logger.debug("Tipo empaquetador: " + instance.empaquetador);

				instance.login = datosUsuario.getUsuario();
				if(logger.isDebugEnabled())logger.debug("Login: " + instance.login);

				StringBuffer nombre = new StringBuffer();
				nombre.append(datosUsuario.getNombre());
				nombre.append(" ");
				nombre.append(datosUsuario.getApellido1());
				if (datosUsuario.getApellido2()!=null){
					nombre.append(" ");
					nombre.append(datosUsuario.getApellido2());
				}
				
				instance.nombreCompleto = nombre.toString();
				if(logger.isDebugEnabled())logger.debug("Nombre: " + instance.nombreCompleto);
				

				instance.catalogador = datosUsuario.getTipoCatalogador();
				if(logger.isDebugEnabled())logger.debug("Catalogador: "+ instance.catalogador);
				
				instance.cuota = datosUsuario.getCuota();
				if(logger.isDebugEnabled())logger.debug("Cuota: "+ instance.cuota);

            	//}
			} 
            catch (Exception e) 
            {
            	logger.error(e);
			}
            
            return this;
        }

        
        /*
         * Modificamos este metodo para añadir en el contexto los roles del sistema asociados a los roles
         * de acceso como GrantedAuthority
         * */

        public Essence setAuthorities(GrantedAuthority authorities[], String usuario, String password)
        {
            usuario = usuario.toLowerCase();
        	mutableAuthorities = new ArrayList(Arrays.asList(authorities));
            
           	if(logger.isDebugEnabled())logger.debug("Rol/es del usuario: " + mutableAuthorities.toString());
            
            GrantedAuthority[] rolesUsuario = WrapperSrvAdminUsuarios.obtenerRoles(authorities,usuario,password);
            
            if (rolesUsuario.length == 1 && rolesUsuario[0].getAuthority().equals("error"))
            {
        		if(logger.isDebugEnabled())logger.debug("Invalidación del contexto de Acegi");            	
            	mutableAuthorities.clear();
            	setEnabled(false);
            }
            else if(rolesUsuario.length > 0)
            {            	
            	if(logger.isDebugEnabled())logger.debug("Se añaden los roles al contexto de Acegi " + rolesUsuario.length);
            	mutableAuthorities.addAll(Arrays.asList(rolesUsuario));	
            }else if(rolesUsuario.length == 1 && rolesUsuario[0].getAuthority().equals("errorNeedAttribute"))
            {
            	if(logger.isDebugEnabled())logger.debug("Nos falta algun atributo en el ldap para darle de alta");
            	mutableAuthorities.addAll(Arrays.asList(rolesUsuario));
            }else if(rolesUsuario.length == 1 && rolesUsuario[0].getAuthority().equals("userAlreadyExist"))
            {
            	if(logger.isDebugEnabled())logger.debug("Ya existe un usuario dado de alta en la BD con los mismos datos");
            	mutableAuthorities.addAll(Arrays.asList(rolesUsuario));
            }
            
            return this;
        }
        
        /*
         * Modificamos este metodo para añadir en el contexto los roles del sistema asociados a los roles
         * de acceso como GrantedAuthority
         * */

        public Essence setAuthoritiesIdentidadFederada(GrantedAuthority authorities[], String usuario, String password)
        {
            usuario = usuario.toLowerCase();
        	mutableAuthorities = new ArrayList(Arrays.asList(authorities));
            
           	if(logger.isDebugEnabled())logger.debug("Rol/es del usuario: " + mutableAuthorities.toString());
                      
            return this;
        }

        public void setControls(Control controls[])
        {
            instance.controls = controls;
        }

        public Essence setCredentialsNonExpired(boolean credentialsNonExpired)
        {
            instance.credentialsNonExpired = credentialsNonExpired;
            return this;
        }

        public Essence setDn(String dn)
        {
            instance.dn = dn;
            return this;
        }

        public Essence setEnabled(boolean enabled)
        {
            instance.enabled = enabled;
            return this;
        }

        public Essence setPassword(String password)
        {
            instance.password = password;
            return this;
        }

        public Essence setUsername(String username)
        {
            instance.username = username;
            return this;
        }

        LdapUserDetailsAgrega instance;
        List mutableAuthorities;

        public Essence()
        {
            instance = new LdapUserDetailsAgrega();
            mutableAuthorities = new ArrayList();
        }

        public Essence(LdapUserDetails copyMe)
        {
        	if(logger.isDebugEnabled())logger.debug("Constructor Essence");
            instance = new LdapUserDetailsAgrega();
            mutableAuthorities = new ArrayList();
            setDn(copyMe.getDn());
            setUsername(copyMe.getUsername().toLowerCase());
            setPassword(copyMe.getPassword());
            setEnabled(copyMe.isEnabled());
            setAccountNonExpired(copyMe.isAccountNonExpired());
            setCredentialsNonExpired(copyMe.isCredentialsNonExpired());
            setAccountNonLocked(copyMe.isAccountNonLocked());
            setControls(copyMe.getControls());
            setAuthorities(copyMe.getAuthorities(),copyMe.getUsername(),copyMe.getPassword());
            //Aqui seteamos los siguiente atributos:idioma,idioma de búsqueda...
            setDatosUsuario(copyMe.getUsername().toLowerCase());
            //setAttributes(copyMe.getAttributes());
           
        }

    }

    protected LdapUserDetailsAgrega()
    {
        attributes = new BasicAttributes();
        authorities = NO_AUTHORITIES;
        controls = NO_CONTROLS;
        accountNonExpired = true;
        accountNonLocked = true;
        credentialsNonExpired = true;
        enabled = true;
    }

    public Attributes getAttributes()
    {
        return attributes;
    }

    public GrantedAuthority[] getAuthorities()
    {
        return authorities;
    }

    public Control[] getControls()
    {
        return controls;
    }

    public String getDn()
    {
        return dn;
    }

    public String getPassword()
    {
        return password;
    }

    public String getUsername()
    {
        return username;
    }

    public boolean isAccountNonExpired()
    {
        return accountNonExpired;
    }

    public boolean isAccountNonLocked()
    {
        return accountNonLocked;
    }

    public boolean isCredentialsNonExpired()
    {
        return credentialsNonExpired;
    }

    public boolean isEnabled()
    {
        return enabled;
    }

	public String getIdiomaBusqueda() 
	{
		return idiomaBusqueda;
	}

	public void setIdiomaBusqueda(String idiomaBusqueda) 
	{
		this.idiomaBusqueda = idiomaBusqueda;
	}

	public String getIdioma() 
	{
		return idioma;
	}

	public void setIdioma(String idioma) 
	{
		this.idioma = idioma;
	}

	public void setAccountNonExpired(boolean accountNonExpired) 
	{
		this.accountNonExpired = accountNonExpired;
	}

	public void setAccountNonLocked(boolean accountNonLocked) 
	{
		this.accountNonLocked = accountNonLocked;
	}

	public void setAttributes(Attributes attributes) 
	{
		this.attributes = attributes;
	}

	public void setAuthorities(GrantedAuthority[] authorities) 
	{
		this.authorities = authorities;
	}

	public void setControls(Control[] controls) 
	{
		this.controls = controls;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) 
	{
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public void setDn(String dn) 
	{
		this.dn = dn;
	}

	public void setEnabled(boolean enabled) 
	{
		this.enabled = enabled;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}

	public String getNombreCompleto() 
	{
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) 
	{
		this.nombreCompleto = nombreCompleto;
	}

	public String getMail() 
	{
		return mail;
	}

	public void setMail(String mail) 
	{
		this.mail = mail;
	}

	public String getEmpaquetador() 
	{
		return empaquetador;
	}

	public void setEmpaquetador(String empaquetador) 
	{
		this.empaquetador = empaquetador;
	}
	
	public String getLogin() 
	{
		return login;
	}

	public void setLogin(String login) 
	{
		this.login = login;
	}
	
	public String getCatalogador() 
	{
		return catalogador;
	}

	public void setCatalogador(String catalogador) 
	{
		this.catalogador = catalogador;
	}
	
	public Long getCuota() 
	{
		return cuota;
	}

	public void setCuota(Long cuota) 
	{
		this.cuota = cuota;
	}

	public String getVisualizador() {
		return visualizador;
	}

	public void setVisualizador(String visualizador) {
		this.visualizador = visualizador;
	}
	
	public Boolean getUsuarioPublico() {
		return usuarioPublico;
	}

	public void setUsuarioPublico(Boolean usuarioPublico) {
		this.usuarioPublico = usuarioPublico;
	}
	
	
}