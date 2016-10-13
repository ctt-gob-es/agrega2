package es.agrega.soporte.tag.redireccionRoles;



import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.acegisecurity.GrantedAuthority;
import org.apache.log4j.Logger;

import es.pode.soporte.menu.MenuController;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;

public class RedireccionRolesTag extends BodyTagSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(RedireccionRolesTag.class);	
	
	protected static final String FILE_NAME_PROPERTIES = "/menu.properties";
	
	protected static final String ROLE_INACTIVO = MenuController.getPropertyValue("inactivo",RedireccionRolesTag.FILE_NAME_PROPERTIES);
	protected static final String ROLE_EMPAQUETADOR = MenuController.getPropertyValue("empaquetador",RedireccionRolesTag.FILE_NAME_PROPERTIES);
	protected static final String ROLE_ADMINISTRADOR = MenuController.getPropertyValue("administrador",RedireccionRolesTag.FILE_NAME_PROPERTIES);
	protected static final String ROLE_CATALOGADOR = MenuController.getPropertyValue("catalogador",RedireccionRolesTag.FILE_NAME_PROPERTIES);
	protected static final String ROLE_PUBLICADOR = MenuController.getPropertyValue("publicador",RedireccionRolesTag.FILE_NAME_PROPERTIES);
	
	/**
	* doStartTag is called by the JSP container when the tag is encountered
	*/
	    public int doStartTag() throws JspException{
	    	try {
	    		
	    		JspWriter out = pageContext.getOut();
	    		logger.debug("LdapUserDetailsUtils.estaAutenticado() "+LdapUserDetailsUtils.estaAutenticado());
	    		if(LdapUserDetailsUtils.estaAutenticado())
	    		{ 
//	    			logger.debug("Comprobamos los roles");
	    			GrantedAuthority[] roles = LdapUserDetailsUtils.getRoles();
	    			ArrayList rolesDescripcion = new ArrayList();
	    			
	    			for(int i=0;i<roles.length;i++)
	    			{
	    				
	    				rolesDescripcion.add(roles[i].getAuthority());
	    			}
	    				    			
	    			if((!(rolesDescripcion.contains(ROLE_ADMINISTRADOR)))&&((!(rolesDescripcion.contains(ROLE_PUBLICADOR)))&&(!(rolesDescripcion.contains(ROLE_CATALOGADOR)))))
	    			{
//	    				logger.debug("Vamos a la portada");
	    				HttpServletResponse response = (HttpServletResponse)pageContext.getResponse();
	    				response.sendRedirect((AgregaPropertiesImpl.getInstance()).getProperty(AgregaProperties.SUBDOMINIO)+"/visualizadorcontenidos/Portada/Portada.do");
	    			}
	    			
	    		}
	    		 
	    	}catch(Exception e)
	    	{
	    		logger.error("exception "+e);
	    	}
	    		return SKIP_BODY;	
	    }
	   
	/**
	 * doEndTag is called by the JSP container when the tag is closed
	 */
		public int doEndTag(){
			return SKIP_BODY; 
		}
}