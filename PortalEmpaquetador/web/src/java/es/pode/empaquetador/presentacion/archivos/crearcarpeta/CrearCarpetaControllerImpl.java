// license-header java merge-point
package es.pode.empaquetador.presentacion.archivos.crearcarpeta;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.empaquetador.negocio.servicio.ArchivoVO;
import es.pode.empaquetador.presentacion.EmpaquetadorSession;
import es.pode.empaquetador.presentacion.GestorSesion;
import es.pode.empaquetador.presentacion.archivos.GestorArchivosSession;
import es.pode.soporte.constantes.ConstantesAgrega;



/**
 * @see es.pode.empaquetador.presentacion.archivos.crearcarpeta.CrearCarpetaController
 */
public class CrearCarpetaControllerImpl extends CrearCarpetaController
{
	Logger logger = Logger.getLogger(this.getClass());
	/**
     * @see es.pode.empaquetador.presentacion.archivos.crearcarpeta.CrearCarpetaController#crearCarpeta(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.archivos.crearcarpeta.CrearCarpetaForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void crearCarpeta(ActionMapping mapping, es.pode.empaquetador.presentacion.archivos.crearcarpeta.CrearCarpetaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	EmpaquetadorSession sesEmpaq = this.getEmpaquetadorSession(request);
    	GestorArchivosSession sesArch = this.getGestorArchivosSession(request);
    	java.util.Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
		
		String accion = form.getAction();
    	

    	String nombreCarpeta = form.getNombre();
    	if((accion.equals(i18n.getString("portal_empaquetado.aceptarCrearCarpeta"))))
    	{

    		logger.debug("voy a validar los caracteres del nombre de la carpeta");
    		GestorSesion gs= new GestorSesion();
    		gs.validarNombreCarpeta(nombreCarpeta);
    		logger.debug("nombre de la carpeta validado");
        	
	    	
			//obtengo el identificador el ultimo ODEVO
			String identificador=sesEmpaq.getIdLocalizador();
			List path = sesArch.getPath();
			ArchivoVO ultimoPath = (ArchivoVO) path.get(path.size()-1);
			//obtengo la carpetaDestino
			
			String carpetaPadre = null;
			if(path.size()>1 && ultimoPath.getCarpetaPadre()==null) {
				carpetaPadre = ultimoPath.getNombre();
			} else if (path.size()>1&& ultimoPath.getCarpetaPadre()!=null) {
				carpetaPadre=ultimoPath.getCarpetaPadre().concat("/").concat(ultimoPath.getNombre());
			}
			try
			{
				this.getSrvGestorArchivosService().crearCarpeta(identificador, carpetaPadre, nombreCarpeta);
				//Una vez creada la carpeta, informamos de que se ha modificado el Objeto
				sesEmpaq.setModificado(true);
			}
			catch (Exception e) 
			{
				throw new ValidatorException("{portal_empaquetado.exception.crearCarpetaDuplicada}");
			}
    	}
     }


}