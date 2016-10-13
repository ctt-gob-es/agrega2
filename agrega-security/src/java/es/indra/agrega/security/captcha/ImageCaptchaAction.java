/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.indra.agrega.security.captcha;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.context.ApplicationContext;
import org.springframework.web.struts.ActionSupport;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageCaptchaAction extends ActionSupport{

	private final String JCAPTCHA_SERVICE_BEAN = "jcaptchaService";
	
	public ActionForward execute(ActionMapping mapping,	ActionForm form,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		ApplicationContext context = getWebApplicationContext();
		
		byte[] captchaChallengeAsJpeg = null;
		// the output stream to render the captcha image as jpeg into
		ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
	        // get the session id that will identify the generated captcha. 
	        //the same id must be used to validate the response, the session id is a good candidate!
	        String captchaId = request.getSession().getId();
	
		    //Get the JCaptcha Service
	        DefaultManageableImageCaptchaService captchaService = 
	        	(DefaultManageableImageCaptchaService)context.getBean(JCAPTCHA_SERVICE_BEAN);
		        
		    //call the ImageCaptchaService getChallenge method
		    BufferedImage challenge = captchaService.getImageChallengeForID(captchaId, request.getLocale());
		            
		    // a jpeg encoder
	    	JPEGImageEncoder jpegEncoder = JPEGCodec.createJPEGEncoder(jpegOutputStream);
	    	jpegEncoder.encode(challenge);
        } catch (IllegalArgumentException e) {
        	response.sendError(HttpServletResponse.SC_NOT_FOUND);
        	return null;
        } catch (CaptchaServiceException e) {
        	response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        	return null;
        }

        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();

        // flush it in the response
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		ServletOutputStream responseOutputStream = response.getOutputStream();
		responseOutputStream.write(captchaChallengeAsJpeg);
		responseOutputStream.flush();
		responseOutputStream.close();
		
		return null;
	}
}
