/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.indra.agrega.security.captcha;

import org.acegisecurity.captcha.CaptchaServiceProxy;

import com.octo.captcha.service.CaptchaService;
import com.octo.captcha.service.CaptchaServiceException;

public class JCaptchaServiceProxyImpl implements CaptchaServiceProxy {

	private CaptchaService jcaptchaService;
 
	public boolean validateReponseForId(String id, Object response) {
 
		try {
			return jcaptchaService.validateResponseForID(id, response).booleanValue();
		} catch (CaptchaServiceException cse) {
			//fixes known bug in JCaptcha
			return false;
		}
	}
	 
	public void setJcaptchaService(CaptchaService jcaptchaService) {
		this.jcaptchaService = jcaptchaService;
	}
} 
