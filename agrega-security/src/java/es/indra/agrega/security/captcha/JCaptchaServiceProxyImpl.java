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
