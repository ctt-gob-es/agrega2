/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
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
