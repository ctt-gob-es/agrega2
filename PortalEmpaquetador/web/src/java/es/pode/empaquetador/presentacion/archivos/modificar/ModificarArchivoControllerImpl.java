// license-header java merge-point
package es.pode.empaquetador.presentacion.archivos.modificar;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.struts.action.ActionMapping;

import es.pode.empaquetador.negocio.servicio.ArchivoVO;
import es.pode.empaquetador.presentacion.EmpaquetadorSession;
import es.pode.empaquetador.presentacion.GestorSesion;
import es.pode.empaquetador.presentacion.archivos.GestorArchivosSession;
import es.pode.soporte.constantes.ConstantesAgrega;



/**
 * @see es.pode.empaquetador.presentacion.archivos.modificar.ModificarArchivoController
 */
public class ModificarArchivoControllerImpl extends ModificarArchivoController
{
	
    /**
     * @see es.pode.empaquetador.presentacion.archivos.modificar.ModificarArchivoController#modificar(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.archivos.modificar.ModificarForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void modificar(ActionMapping mapping, es.pode.empaquetador.presentacion.archivos.modificar.ModificarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	EmpaquetadorSession sesEmpaq = this.getEmpaquetadorSession(request);
		GestorArchivosSession sesArch = this.getGestorArchivosSession(request);

		java.util.Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",
				locale);

		String opcion = form.getAction();

		if (opcion.equals(i18n.getString("portal_empaquetado.modificarNombre"))) {

			//nuevoNombre debe ser la concatenación de la extensión y el nuevo nombre
			String extension = form.getExtension();
			String nuevoNombre;
			if(extension.equals("")) {
				nuevoNombre = form.getNuevoNombre();
			} else
			{
				StringBuffer nombre=new StringBuffer(form.getNuevoNombre());
				nombre.append(".").append(extension);
				nuevoNombre=nombre.toString();
			}
			GestorSesion gs = new GestorSesion();
			gs.validarNombreFichero(nuevoNombre);

			ArchivoVO archivo = (ArchivoVO) request.getSession().getAttribute(
					"archivoVO");
			String nombre = archivo.getNombre();
			
			if(!extension.equals("")) {
				nombre=nombre+"."+extension;
			}

			if (!nombre.equals(nuevoNombre)) {
				
				// obtengo el identificador del ODEVO
				String identificador = sesEmpaq.getIdLocalizador();

				List path = sesArch.getPath();
				ArchivoVO ultimoPath = (ArchivoVO) path.get(path.size() - 1);
				// obtengo la carpetaDestino
				String carpetaPadre = null;
				if (path.size() > 1 && ultimoPath.getCarpetaPadre() == null) {
					carpetaPadre = ultimoPath.getNombre();
				} else if (path.size() > 1
						&& ultimoPath.getCarpetaPadre() != null) {
					carpetaPadre = ultimoPath.getCarpetaPadre().concat("/")
							.concat(ultimoPath.getNombre());
				}

				try {
					this.getSrvEmpaquetadorBasicoService().renombrar(identificador,
							carpetaPadre, nombre, nuevoNombre);
					//Hemos modificado el archivo o carpeta
					sesEmpaq.setModificado(true);
					
				} catch (Exception e) {
					throw new ValidatorException(
							"{portalempaquetado.archivos.error.renombrar}");
				}
			}
		}
     }









}