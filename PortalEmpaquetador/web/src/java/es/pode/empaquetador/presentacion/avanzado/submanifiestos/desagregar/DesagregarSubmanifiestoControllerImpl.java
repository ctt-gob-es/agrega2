// license-header java merge-point
package es.pode.empaquetador.presentacion.avanzado.submanifiestos.desagregar;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.empaquetador.negocio.servicio.OdeVO;
import es.pode.empaquetador.presentacion.DecisorOffline;
import es.pode.empaquetador.presentacion.EmpaquetadorSession;
import es.pode.empaquetador.presentacion.GestorSesion;
import es.pode.soporte.constantes.ConstantesAgrega;

/**
 * @see es.pode.empaquetador.presentacion.avanzado.submanifiestos.desagregar.DesagregarSubmanifiestoController
 */
public class DesagregarSubmanifiestoControllerImpl extends
		DesagregarSubmanifiestoController {

	private static final Logger logger = Logger.getLogger(DesagregarSubmanifiestoControllerImpl.class);
	
	public final void recuperarDatos(ActionMapping mapping,	RecuperarDatosForm form, HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		List identificadores = form.getIdentificadores();
		if (identificadores == null) 
		{
			identificadores = new java.util.ArrayList();
			Logger.getLogger(this.getClass()).error("Se ha llamado a Desagregar Submanifiestos sin identificadores");
		}
		this.getDesagregarSubmanifiestoSession(request).setIdentificadores(identificadores);
	}

	public void borrarObjetoSesion(ActionMapping mapping, BorrarObjetoSesionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		 if(this.getDesagregarSubmanifiestoSession(request)!=null)
         {
          	   request.getSession().removeAttribute(DesagregarSubmanifiestoSession.SESSION_KEY);
         }
		
	}

	public void desagregarLocal(ActionMapping mapping, DesagregarLocalForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		List identificadores = this.getDesagregarSubmanifiestoSession(request).getIdentificadores();
		
			EmpaquetadorSession sesEmpaq = this.getEmpaquetadorSession(request);
			List subman = sesEmpaq.getSubmanifestPath();
			String submanifestId = null;
			OdeVO ode = (OdeVO) subman.get(subman.size() - 1);

			if (subman.size() == 1) 
			{
				submanifestId = null;
			} 
			else 
			{
				submanifestId = ode.getIdentifier();
			}
			String identificadorPrimero = sesEmpaq.getIdLocalizador();
			OdeVO[] submanifList = ode.getSubmanifiestos();
			List submanifiestos = Arrays.asList(submanifList);

			for (int i = 0; (i < submanifiestos.size()); i++) 
			{
				if (identificadores.contains(((OdeVO) submanifiestos.get(i)).getIdentifier())) 
				{
					
						DataHandler dh=this.getSrvGestorManifestService().desagregarSubmanifiestoLocal
						(identificadorPrimero,((OdeVO) submanifiestos.get(i)).getIdentifier(), submanifestId);
						try {
							(new GestorSesion()).iniciarDescargaFichero(dh, response, ((OdeVO) submanifiestos.get(i)).getIdentifier());
							// se ha modificado
							sesEmpaq.setModificado(true);							
						} catch (Exception e) {
							logger.error("Error durante la generacion del submanifiesto desagregado.");
							if(logger.isDebugEnabled()) logger.debug(e);
							throw new ValidatorException("{presentacion.avanzado.submanifiestos.desagregar.error}");
						}
					
				}
			}
	}

	public void desagregarPersonales(ActionMapping mapping, DesagregarPersonalesForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		List identificadores = this.getDesagregarSubmanifiestoSession(request).getIdentificadores();
		

			EmpaquetadorSession sesEmpaq = this.getEmpaquetadorSession(request);
			List subman = sesEmpaq.getSubmanifestPath();
			String submanifestId = null;
			OdeVO ode = (OdeVO) subman.get(subman.size() - 1);

			if (subman.size() == 1) 
			{
				submanifestId = null;
			} 
			else 
			{
				submanifestId = ode.getIdentifier();
			}
			String identificadorPrimero = sesEmpaq.getIdLocalizador();
			OdeVO[] submanifList = ode.getSubmanifiestos();
			List submanifiestos = Arrays.asList(submanifList);

			for (int i = 0; (i < submanifiestos.size()); i++) 
			{
				if (identificadores.contains(((OdeVO) submanifiestos.get(i)).getIdentifier())) 
				{
						this.getSrvGestorManifestService().desagregarSubmanifiestoRepositorio
						(identificadorPrimero,((OdeVO) submanifiestos.get(i)).getIdentifier(), submanifestId);
					
				}
			}
	
		this.getDesagregarSubmanifiestoSession(request).setIdentificadores(null);
		//Desagregamos a personales
		sesEmpaq.setModificado(true);
		
	}

	public String selectAction(ActionMapping mapping, SelectActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		/*
		 * Metodo de decision para el action. Analiza los parametros
		 * actionSubmit (value de los botones submit) para redirigir al caso de uso
		 * correspondiente. El actionSubmit llegara
		 * internacionalizado, por lo que es necesario acceder al ResouceBundle
		 * para obtener el valor correcto en la comparacion.
		 */
    	
		String result = null;
		String actionSubmit = form.getDestino();
		java.util.Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
		
		if (form.getAction()== (null))
		{
			throw new ValidatorException("{portal_empaquetado.exception}");
		}
		else if(form.getAction().equals("Cancelar"))
		{
			return "Cancelar";
		}
		//Cancelar
		else if (actionSubmit.equals(i18n.getString("portal_empaquetado_gestorSubmanifiestos.desagregar.repositorio")) )
		{
			result = "personales";
		} 
		//Aceptar
		else if (actionSubmit.equals(i18n.getString("portal_empaquetado_gestorSubmanifiestos.desagregar.local"))) 
		{
			result = "local";
		} 
		
         return result;
	}

	public void submit(ActionMapping mapping, SubmitForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		java.util.Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
		
    	String accion = form.getAction();
    	String identificador = form.getDestino();
    	if (accion.equals(i18n.getString("portal_empaquetado_desagregarSubmanifiestos.continuar")))
    	{
    		if((identificador==null)||(identificador.equals("")))
    		{
    			throw new ValidatorException("{exportar.subirArchivo}");	
    		}
    		
    	}
		
	}

	public boolean esOffline(ActionMapping mapping, EsOfflineForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(logger.isDebugEnabled()) logger.debug("Version offline? " + DecisorOffline.esOffline());
		return DecisorOffline.esOffline();
	}
	
	

}