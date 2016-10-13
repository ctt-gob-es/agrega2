/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.modificador.presentacion.informes.tarea;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.modificador.negocio.servicio.ComboEstados;
import es.pode.modificador.negocio.servicio.EstadosTarea;
import es.pode.modificador.negocio.servicio.ResultadoModificacionVO;
import es.pode.modificador.presentacion.idiomasBuscador.IdiomasBuscadorSingleton;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.modificador.presentacion.informes.tarea.InformeTareaController
 */
public class InformeTareaControllerImpl extends InformeTareaController
{
	private static final Logger logger = Logger.getLogger(InformeTareaControllerImpl.class);

	final int BUFFER_SIZE = 10000;

    /**
     * @see es.pode.modificador.presentacion.informes.tarea.InformeTareaController#obtenerDatos(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.informes.tarea.ObtenerDatosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerDatos(ActionMapping mapping, es.pode.modificador.presentacion.informes.tarea.ObtenerDatosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    
    {
    	form.setNombreTarea(this.getInformeSession(request).getNombreTarea());
    	if(this.getInformeSession(request).getDescResultado()!=null){
    	form.setDescResultado(this.getInformeSession(request).getDescResultado());
    	}else{
    		form.setDescResultado("msg.tarea.error1");
    	}
    	if(this.getInformeSession(request).getResultadoTarea()!=null){
    		form.setResultadoTarea(this.getInformeSession(request).getResultadoTarea());
    	}else{
    		form.setResultadoTarea("ERROR");
    	}
    	String origen=ConstantesInforme.Configurar;
    	if(this.getInformeSession(request).getOrigen()!=null){
    		origen=this.getInformeSession(request).getOrigen();
    	}
    	logger.debug("El origen es "+origen);
    	form.setIdiomaBuscadorBackingList(IdiomasBuscadorSingleton.getInstance().obtenerIdiomas(LdapUserDetailsUtils.getIdioma()), "idLocalizacion", "nombre");
    	form.setTextoError("informe.sinOdes");
    	if(form.getEstados()==null || form.getEstados().equals("") || form.getEstados().equals("idTODOS")){
    		logger.debug("Estamos en el estado inicial o en el caso de que en el combo se haya seleccionado TODOS "+ form.getEstados());
        	form.setOdes(this.getInformeSession(request).getOdes());
    	}else{
    		logger.debug("En el combo se ha seleccionado el estado  "+ form.getEstados());
    		String[] estados=new String[1];
    		estados[0]=form.getEstados();
    		ResultadoModificacionVO[] odes = this.getSrvHerramientaModificacion().obtenerModificacionesPorEstado(this.getInformeSession(request).getIdModificacion(), estados);
    		logger.debug("Hemos recibido "+odes.length+" odes");
    		form.setOdesAsArray(odes);
    		form.setTextoError("informe.sinEstado");
    	}	
		    String[] estadosString=new String[5];
		    estadosString[0]="idTODOS";
		    estadosString[1]=EstadosTarea.FINALIZADA.getValue();
		    estadosString[2]=EstadosTarea.ERROR.getValue();
		    estadosString[3]=EstadosTarea.RESTAURADA.getValue();
		    estadosString[4]=EstadosTarea.WARNING.getValue();
		    if(logger.isDebugEnabled())logger.debug("Estados creados, textos obtenidos");
		    
		    java.util.Locale locale = (java.util.Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
			ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
			
			String[] textoString=new String[5];
			textoString[0]=i18n.getString("estado.todos");
			textoString[1]=i18n.getString("estado."+ estadosString[1]);
			textoString[2]=i18n.getString("estado."+estadosString[2]);
			textoString[3]=i18n.getString("estado."+estadosString[3]);
			textoString[4]=i18n.getString("estado."+estadosString[4]);
	
	    	if(logger.isDebugEnabled())logger.debug(" textos internacionalizados obtenidos");
	    	
	    	ComboEstados[] vuelta = ComboEstados.getInstance(estadosString, textoString);
	    	if(logger.isDebugEnabled())logger.debug(" Combo estados creado");
			Collection idTax2 = Arrays.asList(vuelta);
	
			if(logger.isDebugEnabled())logger.debug("Lo insertamos en el form");
			form.setEstadosBackingList(idTax2, "identificador", "valor");
			if(logger.isDebugEnabled())logger.debug("Cargados los estados en el combo, longitud= "+form.getEstadosBackingList().length);
		
    	
	    
     }


    /**
     * @see es.pode.modificador.presentacion.informes.tarea.InformeTareaController#restaurarODEs(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.informes.tarea.RestaurarODEsForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void restaurarODEs(ActionMapping mapping, es.pode.modificador.presentacion.informes.tarea.RestaurarODEsForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	Long idModificacion = this.getInformeSession(request).getIdModificacion();
    	if(idModificacion!=null) {
	    	String idODE = form.getId();
			Boolean result = this.getSrvHerramientaModificacion().restaurar(idModificacion, Long.valueOf(idODE));
			if(Boolean.FALSE.equals(result)) {
				throw new ValidatorException("{tareas.error.restaurar}");
			}
			// Recupero la modificacion para reinsertarla en la sesion
			ResultadoModificacionVO[] odes = this.getSrvHerramientaModificacion().obtenerResultadoModificacion(idModificacion);
			this.getInformeSession(request).setOdes(Arrays.asList(odes));
    	} else {
    		if(logger.isDebugEnabled())logger.debug("Se ha intentado restaurar un ODE desde el informe de simulacion. Ignorando");
    	}
		
     }

    /**
     * @see es.pode.modificador.presentacion.informes.tarea.InformeTareaController#obtenerOde(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.informes.tarea.ObtenerOdeForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerOde(ActionMapping mapping, es.pode.modificador.presentacion.informes.tarea.ObtenerOdeForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
	    try
	    {
	    	String idOde = form.getId();
	    	logger.debug("idOde "+idOde);
	    	Long idModificacion = this.getInformeSession(request).getIdModificacion();
	    	logger.debug("idModificacion "+idModificacion);
	    	ResultadoModificacionVO ode =null;
	    	if(idModificacion==null) {
	    		ode = buscarOde(Long.valueOf(idOde),this.getInformeSession(request).getOdes());
	    	} else {
	    		logger.debug("recuperarResultadoODE ");
	    		ode = this.getSrvHerramientaModificacion().recuperarResultadoODE(idModificacion, Long.valueOf(idOde));
	    	}
	    	form.setOde(ode);
	     }
	    catch (Exception e) 
	    {
			logger.error("No se pudo obtener el ODE debido a "+e.getMessage());
			if(logger.isDebugEnabled()) logger.debug(e);
			throw new ValidatorException("{informe.exception}");
	    }
    }

    private ResultadoModificacionVO buscarOde(Long idOde,Collection odes) {
    	ResultadoModificacionVO result = null;
    	for(Iterator it=odes.iterator();it.hasNext() && result == null;) {
    		ResultadoModificacionVO temp = (ResultadoModificacionVO)it.next(); 
    		if(temp.getId().equals(idOde)) result = temp;
    	}
    	return result;
    }

	public String submitConfirmacion(ActionMapping mapping, SubmitConfirmacionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		java.util.Locale locale = (java.util.Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
    	String accion = form.getAction();
    	if (accion.equals(i18n.getString("modificacionesEjecutadas.aceptar")))
    	{
    		return "Aceptar";
    		
    	}
    	else
    	{
    		return "Cancelar";
    	}
	}

	public String selectOrigin(ActionMapping mapping, SelectOriginForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String origen=ConstantesInforme.Configurar;
    	if(this.getInformeSession(request).getOrigen()!=null){
    		origen=this.getInformeSession(request).getOrigen();
    	}
		logger.debug("El origen es "+origen+" y el estado "+form.getEstados());
		String result="";				
		java.util.Locale locale = (java.util.Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
		
		result = "Filtrar";
		
		if ((origen!=null) && origen.equals(ConstantesInforme.Configurar) && i18n.getString("tareas.volver").equals(form.getAction())) 
		{
			logger.debug("Vamos a Configurar");
			
			/*
			 * Volvemos a tareas configuradas marcando el ID de modificacion que
			 * debe resaltarse en amarillo (solo para el caso de simulacion)
			 */
			form.setIdModificacion(this.getInformeSession(request).getTareaSeleccionada());
			result = "Configurar";
//			Reseteamos sesión
			removeInformeSession(request);
		}else if((origen!=null) && origen.equals(ConstantesInforme.Ejecutadas) && i18n.getString("tareas.volver").equals(form.getAction())){
			logger.debug("Vamos a Ejecutadas");
			result = "Ejecutadas";
//			Reseteamos sesión
			removeInformeSession(request);
		}
		else if (!"".equals(form.getEstados()) && i18n.getString("filtrar").equals(form.getAction())) {
			logger.debug("Vamos a filtrar por "+form.getEstados());
			form.setIdModificacion(this.getInformeSession(request).getTareaSeleccionada());
			result= "Filtrar";
		}
		
		
        return result;
	}


	@Override
	public void descargarOde(ActionMapping mapping, DescargarOdeForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long idModificacion = this.getInformeSession(request).getIdModificacion();
		String titulo=form.getTitulo();
		DataHandler dataHandler = null;
		String tituloEnc = titulo.replace(' ', '_');
		tituloEnc = tituloEnc.replace('?', '_').replace('¿', '_').replace('/', '_').replace('[', '_').replace(']', '_').replace('@', '_');
		tituloEnc = tituloEnc.replace('ñ', 'n').replace('Ñ', 'N').replace(':', '_');
		tituloEnc = convertNonAscii(tituloEnc);
    	if(idModificacion!=null) {
	    	String idODE = form.getId();
	    	try {
	    		dataHandler=this.getSrvHerramientaModificacion().descargarModificacion(idModificacion, idODE, tituloEnc);
				
			}catch (Exception e){
				logger.error("Error al recuperar el fichero");
			}
			if (dataHandler==null){
				logger.error("El fichero recuperado está vacio");
				throw new ValidatorException("{recuperarFichero.FALLO}");
			}
			response.setContentType("application/zip");	
		    response.setHeader("Content-Disposition", "attachment;filename="+tituloEnc+".zip");	
		    response.setHeader("Cache-Control", "public");
		    OutputStream out = response.getOutputStream();
			InputStream in = dataHandler.getInputStream();
			if(logger.isDebugEnabled())logger.debug("recuperando el fichero " + tituloEnc);
			byte[] buffer = new byte[BUFFER_SIZE];
			int count;
			while((count = in.read(buffer, 0, BUFFER_SIZE))!= -1)
			{
				out.write(buffer, 0, count);
			}
			
			out.flush();
			out.close();
	    	
			
    	} else {
    		if(logger.isDebugEnabled())logger.debug("Se ha intentado descargar un ODE desde el informe de simulacion. Ignorando");
    	}
	}

	private String convertNonAscii(String s) 
	{
		final String PLAIN_ASCII =
		      "AaEeIiOoUu"    // grave
		    + "AaEeIiOoUuYy"  // acute
		    + "AaEeIiOoUuYy"  // circumflex
		    + "AaOo"          // tilde
		    + "AaEeIiOoUuYy"  // umlaut
		    + "Aa"            // ring
		    + "Cc"            // cedilla
		    ;

		final String UNICODE =
		 "\u00C0\u00E0\u00C8\u00E8\u00CC\u00EC\u00D2\u00F2\u00D9\u00F9"             
		+"\u00C1\u00E1\u00C9\u00E9\u00CD\u00ED\u00D3\u00F3\u00DA\u00FA\u00DD\u00FD" 
		+"\u00C2\u00E2\u00CA\u00EA\u00CE\u00EE\u00D4\u00F4\u00DB\u00FB\u0176\u0177" 
		+"\u00C3\u00E3\u00D5\u00F5"
		+"\u00C4\u00E4\u00CB\u00EB\u00CF\u00EF\u00D6\u00F6\u00DC\u00FC\u0178\u00FF" 
		+"\u00C5\u00E5"                                                             
		+"\u00C7\u00E7" ;
		
		
	       StringBuffer sb = new StringBuffer();
	       int n = s.length();
	       for (int i = 0; i < n; i++) {
	          char c = s.charAt(i);
	          int pos = UNICODE.indexOf(c);
	          if (pos > -1){
	              sb.append(PLAIN_ASCII.charAt(pos));
	          }
	          else {
	              sb.append(c);
	          }
	       }
	       return sb.toString();
	    }


	@Override
	public void recuperarInformeGeneral(ActionMapping mapping,
			RecuperarInformeGeneralForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List resultados= this.getInformeSession(request).getOdes();
		ResultadoModificacionVO informeGeneral=(ResultadoModificacionVO)resultados.get(resultados.size()-1);
		form.setUrl(informeGeneral.getTraza());
	}
	
}