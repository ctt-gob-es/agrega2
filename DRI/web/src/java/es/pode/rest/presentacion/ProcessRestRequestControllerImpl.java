/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.rest.presentacion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.rest.servicios.RestParamNames;
import es.pode.rest.servicios.RestRequestVO;

/**
 * @see es.pode.rest.presentacion.ProcessRestRequestController
 */
public class ProcessRestRequestControllerImpl extends
		ProcessRestRequestController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6521404072374324705L;
	private static Logger logger = Logger.getLogger(ProcessRestRequestControllerImpl.class);
	
	/**
	 * @see es.pode.rest.presentacion.ProcessRestRequestController#processRequest(org.apache.struts.action.ActionMapping,
	 *      es.pode.rest.presentacion.ProcessRequestForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public final void processRequest(ActionMapping mapping,
			es.pode.rest.presentacion.ProcessRequestForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RestRequestVO vo = new RestRequestVO();
		logger.debug("REST request : " + request.getQueryString());
		vo.setMethod(request.getParameter(RestParamNames.METHOD_PARAM.getValue()));
		vo.setNode(request.getParameter(RestParamNames.NODE_PARAM.getValue()));
		vo.setTag(request.getParameter(RestParamNames.TAG_PARAM.getValue()));
		vo.setUserId(request.getParameter(RestParamNames.USER_PARAM.getValue()));
		vo.setAgregationLevel(request.getParameter(RestParamNames.AGGREGATION_LEVEL_PARAM.getValue()));
		vo.setMinValue(request.getParameter(RestParamNames.MIN_VALUE_PARAM.getValue()));
		vo.setMaxValue(request.getParameter(RestParamNames.MAX_VALUE_PARAM.getValue()));
		vo.setPage(request.getParameter(RestParamNames.PAGE_PARAM.getValue()));
		vo.setLanguage(request.getParameter(RestParamNames.LANGUAGE_PARAM.getValue()));
		
		
		
		try {
			String respuesta = getSrvRestService().processRestRequest(vo);
			response.setContentType("text/xml");
			response.getOutputStream().print(respuesta);
		} catch (Exception e) {
			logger.error("Error durante la escritura en HttpServletResponse", e);
			response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "Service unavailable");
		}
	}

}