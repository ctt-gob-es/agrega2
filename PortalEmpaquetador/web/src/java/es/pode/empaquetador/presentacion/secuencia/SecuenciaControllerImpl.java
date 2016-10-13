// license-header java merge-point
package es.pode.empaquetador.presentacion.secuencia;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.empaquetador.negocio.servicio.ControlModeVO;
import es.pode.empaquetador.negocio.servicio.GrupoVO;
import es.pode.empaquetador.negocio.servicio.OdeVO;
import es.pode.empaquetador.negocio.servicio.OrganizacionVO;
import es.pode.empaquetador.presentacion.EmpaquetadorSession;
import es.pode.empaquetador.presentacion.GestorSesion;
import es.pode.soporte.constantes.ConstantesAgrega;



/**
 * @see es.pode.empaquetador.presentacion.secuencia.SecuenciaController
 */
public class SecuenciaControllerImpl extends SecuenciaController
{

	private static Logger logger = Logger.getLogger(SecuenciaControllerImpl.class);
	
	private GestorSesion gs = new GestorSesion();
	
    /**
     * @see es.pode.empaquetador.presentacion.secuencia.SecuenciaController#modificarSecuencia(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.secuencia.ModificarSecuenciaForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void asociarSecuencia(ActionMapping mapping, AsociarSecuenciaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	EmpaquetadorSession sesEmpaq = this.getEmpaquetadorSession(request);
    	
    	List submanifestPath = sesEmpaq.getSubmanifestPath();
    	Integer index = submanifestPath.size()-1;
    	OdeVO ultimoSubm = (OdeVO) submanifestPath.get(index);
    	String identificador=index==0?sesEmpaq.getIdLocalizador():ultimoSubm.getIdentifier();
    	List idCollection = sesEmpaq.getIdCollection();
    	String idElemento ="";
    	if(idCollection.size()>1)
    	{
    		GrupoVO ultimoIdCollection = (GrupoVO) idCollection.get(idCollection.size()-1);
    		idElemento = ultimoIdCollection.getIdentifier();
    	}
    	else
    	{
    		OrganizacionVO ultimoIdCollection = (OrganizacionVO) idCollection.get(idCollection.size()-1);
    		idElemento = ultimoIdCollection.getIdentifier();
    		
    	}
    	
    	ControlModeVO controlMode=new ControlModeVO();
    	controlMode.setChoice(form.getChoice());
    	controlMode.setChoiceExit(form.getChoiceExit());
    	controlMode.setFlow(form.getFlow());
    	controlMode.setForwardOnly(form.getForwardOnly());
    	
    	this.getSrvGestorManifestService().asociarSecuencia(identificador, idElemento, controlMode);
    }

	/* (non-Javadoc)
	 * @see es.pode.empaquetador.presentacion.secuencia.SecuenciaController#recuperarDatos(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.secuencia.RecuperarDatosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public final void recuperarDatos(ActionMapping mapping, RecuperarDatosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		ControlModeVO ctrlModeVo = new ControlModeVO(); 
		List idCol = getEmpaquetadorSession(request).getIdCollection();
		
		if(idCol.get(idCol.size()-1) instanceof OrganizacionVO)
		{
			ctrlModeVo = gs.obtenerCM( ((OrganizacionVO) idCol.get(idCol.size()-1)).getSecuencia());
		}else if(idCol.get(idCol.size()-1) instanceof GrupoVO)
		{
			ctrlModeVo = gs.obtenerCM( ((GrupoVO) idCol.get(idCol.size()-1)).getSecuencia());
		}
		
      	form.setChoice(ctrlModeVo.getChoice());
      	form.setChoiceExit(ctrlModeVo.getChoiceExit());
      	form.setFlow(ctrlModeVo.getFlow());
      	form.setForwardOnly(ctrlModeVo.getForwardOnly());
 
	}

	public final void fijarPorDefecto(ActionMapping mapping, FijarPorDefectoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		form.setChoice(Boolean.TRUE);
		form.setChoiceExit(Boolean.FALSE);
		form.setFlow(Boolean.TRUE);
		form.setForwardOnly(Boolean.FALSE);
	}
	
	

	public String selectAction(ActionMapping mapping, SelectActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String resultado = null;
		
		java.util.Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
		
    	String accion = form.getAction();
    	
    	if (accion.equals(i18n.getString("portal_empaquetado_secuencia.aceptar")))
    	{
    		if((form.getChoice()==null) || (form.getChoiceExit()==null) 
    				||(form.getFlow()==null)||(form.getForwardOnly()==null))
    		{
    			throw new ValidatorException("{portal_empaquetado.exception}");
    		}
    	}
    	
    	if (form.getAction()== (null))
		{
			throw new ValidatorException("{portal_empaquetado.exception}");
		}
			
		else if (accion.equals(i18n.getString("portal_empaquetado_secuencia.cancelar")) )
		{
			resultado = "Cancelar";
		} 
		else if (accion.equals(i18n.getString("portal_empaquetado_secuencia.aceptar"))) 
		{
			resultado = "Aceptar";
		} 
		else if (accion.equals(i18n.getString("secuencia.valorPorDefecto"))) 
		{
			resultado = "Default";
		} 
		
    	return resultado;
	}

	

	public final String tipoEmpaquetador(ActionMapping mapping, TipoEmpaquetadorForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return gs.tipoEmpaquetador(this.getEmpaquetadorSession(request));
	}


}