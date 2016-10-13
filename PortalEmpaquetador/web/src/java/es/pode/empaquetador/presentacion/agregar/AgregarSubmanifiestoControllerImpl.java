// license-header java merge-point
package es.pode.empaquetador.presentacion.agregar;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.empaquetador.presentacion.GestorSesion;
import es.pode.soporte.constantes.ConstantesAgrega;



/**
 * @see es.pode.empaquetador.presentacion.agregar.AgregarSubmanifiestoController
 */
public class AgregarSubmanifiestoControllerImpl extends AgregarSubmanifiestoController
{

	private static Logger logger = Logger.getLogger(AgregarSubmanifiestoControllerImpl.class);
	private GestorSesion gs = new GestorSesion();
	
	
    /**
     * @see es.pode.empaquetador.presentacion.agregar.AgregarSubmanifiestoController#seleccionarOrigen(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.agregar.SeleccionarOrigenForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String seleccionarOrigen(ActionMapping mapping, es.pode.empaquetador.presentacion.agregar.SeleccionarOrigenForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	java.util.Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
		String origen = form.getOrigen();
    	String accion = form.getAction();
    	String resultado = null;
    	
    	if ((request.getParameter("asistente")==null) || !(request.getParameter("asistente").equals("si"))){
    			//inicializamos session
    			this.getEmpaquetadorSession(request).setMensajeAsistente("");
        }
    
    	if (accion.equals(i18n.getString("portal_empaquetado_agregarSubmanifiestos.continuar")))
     	{
    		if((origen.equals("Local"))||(origen.equals("Personales"))||(origen.equals("Repositorio")))
    		{
    		    resultado=origen;
    		}
    		else
    		{
    			throw new ValidatorException("{portal_empaquetado.exception}");
    		}
    		
    	}
    	else
    	{
    		resultado="Cancelar";
    	}
      return resultado;
    }


    /**
     * @see es.pode.empaquetador.presentacion.agregar.AgregarSubmanifiestoController#tipoEmpaquetador(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.agregar.TipoEmpaquetadorForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String tipoEmpaquetador(ActionMapping mapping, es.pode.empaquetador.presentacion.agregar.TipoEmpaquetadorForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        return gs.tipoEmpaquetador(this.getEmpaquetadorSession(request));
    }


}