/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * distributed with this file and available online at
 * http://www.uportal.org/license.html
 */
package org.jasig.cas.web;

import org.jasig.cas.CentralAuthenticationService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.CookieGenerator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.agrega.soporte.agregaProperties.AgregaProperties;

/**
 * Controller to delete ticket granting ticket cookie in order to log out of
 * single sign on. This controller implements the idea of the ESUP Portail's
 * Logout patch to allow for redirecting to a url on logout. It also exposes a
 * log out link to the view via the WebConstants.LOGOUT constant.
 * 
 * @author Alicia Arribas
 * @version $Revision: 1.0 $ $Date: 2008/12/07 $
 * @since 3.0
 */
public final class AuthenticationOpenId extends AbstractController implements
    InitializingBean {

     /** Logout view name. */
    private String casOpenIdView;
    private String failureView;
    private java.util.Properties pSpringProperties = null;
	private java.util.Properties pSpringPropertiesAuth = null;
    //private Validator validator;

    /**
     * Boolean to determine if we will redirect to any url provided in the
     * service request parameter.
     */
    private boolean followServiceRedirects;

    public void afterPropertiesSet() throws Exception {
       // Assert.hasText(this.casOpenIdView, "casOpenIdView must have text.");
    	System.out.println("[AuthenticationOpenId] afterPropertiesSet");
      //  this.failureView = this.casOpenIdView;
    	//afterPropertiesSetInternal();
    }

    protected ModelAndView handleRequestInternal(
        final HttpServletRequest request, final HttpServletResponse response)
        throws Exception {
    	final Map model = new HashMap();
    	final String service = request.getParameter("service");
        String userName = request.getParameter("username");
        if((userName == null)||(userName == "")||(userName == " "))
        {
        	
        	model.put("code", "required.openIdUrl");
            model.put("description", getMessageSourceAccessor().getMessage(
                "required.openIdUrl", "required.openIdUrl"));
         //   return new ModelAndView(this.casOpenIdView, model);
            return new ModelAndView(new RedirectView(this.getAuthbackenPropertyValue("cas.http.url")+ "/" + "openid?code=required.openIdUrl&description="+getMessageSourceAccessor().getMessage("required.openIdUrl", "required.openIdUrl")));
        }
        //http://localhost:8080/gestorFlujo/j_acegi_cas_security_check
        //Se modifica la url destino para que se mantenga la saveRequest
        String returnTo = request.getParameter("return_to");
        int posicionAcegi = returnTo.indexOf("/j_acegi_cas_security_check");
        if(!(posicionAcegi == -1))
        {
        	int posicionModulo = returnTo.indexOf("/",7);
        	String modulo = returnTo.substring(posicionModulo + 1 ,posicionAcegi);
        	if(!(returnTo.indexOf(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SUBDOMINIO)) == -1))
        	{
        		return new ModelAndView(new RedirectView(AgregaPropertiesImpl.getInstance().getUrlNodo()+ "/" +modulo + "/j_spring_openid_security_check?username="+userName));
        	}else
        	{
        		return new ModelAndView(new RedirectView(AgregaPropertiesImpl.getInstance().getUrlNodo()+ "/" +modulo + "/j_spring_openid_security_check?username="+userName));
        	}
        	
        }else
        {
        	
        		return new ModelAndView(new RedirectView(AgregaPropertiesImpl.getInstance().getUrlNodo()+ "/portaladministracion/j_spring_openid_security_check?username="+userName));
        
        }
    }

    public void setCasOpenIdView(final String casOpenIdView) {
        this.casOpenIdView = casOpenIdView;
    }
    
    private String getAuthbackenPropertyValue(String sKey) throws IOException {
		InputStream fIsSpringProperties = this.getClass().getResourceAsStream("/authbackend.properties");
		if (this.pSpringPropertiesAuth == null) {
			pSpringProperties = new java.util.Properties();
			pSpringProperties.load(fIsSpringProperties);
		}

		// devolvemos la propiedad
		return pSpringProperties.getProperty(sKey);
	}
    
    
 }
