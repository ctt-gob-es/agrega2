package es.agrega.soporte.help;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class HelpLink 
{
	private static HelpLink help= null;
	
	private HelpLink ()
	{
		
	}
	
	public static HelpLink getInstance()
	{
		if (HelpLink.help == null)
			HelpLink.help = new HelpLink();
		
		return HelpLink.help;
	}

	public String getRequestAttribute(ServletRequest request, String attrKey) 
	{
		String requestUrl = null;
		if (request != null && request instanceof HttpServletRequest)
		{
			HttpServletRequest httpRequest = (HttpServletRequest)request;
			requestUrl = String.valueOf(httpRequest.getAttribute(attrKey));
		}
		else
			requestUrl = String.valueOf(request.getAttribute(attrKey));
		return requestUrl;
	}

}
