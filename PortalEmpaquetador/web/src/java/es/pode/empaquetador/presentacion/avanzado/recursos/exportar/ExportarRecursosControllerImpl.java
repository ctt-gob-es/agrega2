// license-header java merge-point
package es.pode.empaquetador.presentacion.avanzado.recursos.exportar;

import java.util.ArrayList;
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
import es.pode.empaquetador.negocio.servicio.RecursoVO;
import es.pode.empaquetador.presentacion.EmpaquetadorSession;
import es.pode.empaquetador.presentacion.GestorSesion;
import es.pode.soporte.constantes.ConstantesAgrega;



/**
 * @see es.pode.empaquetador.presentacion.avanzado.recursos.exportar.ExportarRecursosController
 */
public class ExportarRecursosControllerImpl extends ExportarRecursosController
{
	
	private static final Logger logger = Logger.getLogger(ExportarRecursosControllerImpl.class);

    public final void exportar(ActionMapping mapping, es.pode.empaquetador.presentacion.avanzado.recursos.exportar.ExportarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	java.util.Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
		String opcion=form.getAction();
		
		if (opcion.equals(i18n.getString("presentacion.avanzado.recursos.exportar.aceptar")))
    	{
		
			String nombre = form.getNombre();
			GestorSesion gs= new GestorSesion();
			gs.validarNombreFichero(nombre);
			
			List recursosVO =this.getExportarRecursosSession(request).getRecursosVO();
			if(logger.isDebugEnabled()) logger.debug("Recursos recuperados de la sesion " + recursosVO);
			List recursosList=new ArrayList();
			String identificadorRec="";
			String[] recursos = new String[recursosVO.size()];
			for (int i=0; i<recursosVO.size();i++)
			{
				identificadorRec=((RecursoVO)recursosVO.get(i)).getIdentifier();
				recursosList.add(i, identificadorRec);
				recursos[i]=identificadorRec;
			}
			
//			String[] recursos=(String[]) recursosList.toArray();
			EmpaquetadorSession sesEmpaq = this.getEmpaquetadorSession(request);
        	List listSubmPath = sesEmpaq.getSubmanifestPath();
        	
        	
        	//cojo el primer elemento que dedberia ser el padre
    		String identificador=sesEmpaq.getIdLocalizador();
    		DataHandler dh=null;
    		if(listSubmPath.size()==1)
    		{
    			dh= this.getSrvGestorManifestService().exportarRecursos(identificador, recursos, null, nombre);
    		}
    		else if(listSubmPath.size()>1)
    		{
	    		OdeVO odeultim= (OdeVO) listSubmPath.get(listSubmPath.size()-1);
	    		String submanifestId=odeultim.getIdentifier();
	    		dh =  this.getSrvGestorManifestService().exportarRecursos(identificador, recursos, submanifestId, nombre);
    		}
    		// Introducir el Datahandler en el response para la descarga
    		try {
    			(new GestorSesion()).iniciarDescargaFichero(dh, response, nombre+".zip");
    		} catch (Exception e) {
				logger.error("Error en la descarga de recursos exportados");
				if(logger.isDebugEnabled()) logger.debug(e);
				throw new ValidatorException("{presentacion.avanzado.recursos.exportar.error}");
			}
    	}
     }

	public String selectAction(ActionMapping mapping, SelectActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String result = null;
		String actionSubmit = form.getAction();
		java.util.Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
		
		if (form.getAction()== (null))
		{
			throw new ValidatorException("{portal_empaquetado.exception}");
		}
			
		else if (actionSubmit.equals(i18n.getString("presentacion.avanzado.recursos.exportar.cancelar")) )
		{
			result = "Cancelar";
		} 
		else if (actionSubmit.equals(i18n.getString("presentacion.avanzado.recursos.exportar.aceptar"))) 
		{
			result = "Aceptar";
		} else{
		
		Logger.getLogger(this.getClass()).debug("El valor del submit no es correcto (actionSubmit = "
							+ actionSubmit + ";");
		}
         return result;
	}

	public void crearSesion(ActionMapping mapping, CrearSesionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List recursos = form.getRecursosVO();
		this.getExportarRecursosSession(request).setRecursosVO(recursos);
	}

	public void terminarSesion(ActionMapping mapping, TerminarSesionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(this.getExportarRecursosSession(request)!=null) this.removeExportarRecursosSession(request);
	}









}