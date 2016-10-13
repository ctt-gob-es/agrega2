// license-header java merge-point
package es.pode.empaquetador.presentacion.avanzado.recursos.importar;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimePartDataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import es.pode.empaquetador.negocio.servicio.AgregarVO;
import es.pode.empaquetador.negocio.servicio.OdeVO;
import es.pode.empaquetador.presentacion.EmpaquetadorSession;
import es.pode.empaquetador.presentacion.agregar.MetodosComunes;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.empaquetador.presentacion.avanzado.recursos.importar.ImportarRecursosController
 */
public class ImportarRecursosControllerImpl extends ImportarRecursosController
{

	private static Logger logger = Logger.getLogger(ImportarRecursosControllerImpl.class);
	
    public final void importarZIP(ActionMapping mapping, es.pode.empaquetador.presentacion.avanzado.recursos.importar.ImportarZIPForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	java.util.Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
		String opcion=form.getAction();
		
		if ((request.getParameter("asistente")==null) || !(request.getParameter("asistente").equals("si"))){
			//inicializamos session
			this.getEmpaquetadorSession(request).setMensajeAsistente("");
		}
		
		if (opcion.equals(i18n.getString("portalempaquetado.avanzado.recursos.aceptar"))){
			
			FormFile fichero = form.getFichero();
			if(fichero.getFileName()==null || fichero.getFileName().equals("") || fichero.getFileSize()==0) {
				throw new ValidatorException("{portalempaquetado.avanzado.recursos.importar.exception}");
				//tratar exception
			}
			
			try	{
				InternetHeaders ih = new InternetHeaders();
				MimeBodyPart mbp = null;
				DataSource source = null;
				DataHandler dFichero = null;
				
				mbp = new MimeBodyPart(ih, fichero.getFileData());
	    		source = new MimePartDataSource(mbp);
	    		dFichero = new DataHandler(source);
	    		
	    		EmpaquetadorSession sesEmpaq = this.getEmpaquetadorSession(request);
	        		        	
	        	List listSubmPath = sesEmpaq.getSubmanifestPath();
	        	
	        	//cojo el primer elemento que deberia ser el padre
	    		String identificador=sesEmpaq.getIdLocalizador();
	    		AgregarVO agregarVO=null;
	    		if(listSubmPath.size()==1)
	    		{
	    			agregarVO=getSrvGestorManifestService().importarRecursos(identificador,dFichero,null,LdapUserDetailsUtils.getUsuario(),LdapUserDetailsUtils.getIdioma());
	    			//Se ha realizado la importación, informamos del cambio
	    			sesEmpaq.setModificado(true);
	    		}
	    		else if(listSubmPath.size()>1)
	    		{
		    		OdeVO odeultim= (OdeVO) listSubmPath.get(listSubmPath.size()-1);
		    		String submanifestId=odeultim.getIdentifier();
		    		agregarVO=getSrvGestorManifestService().importarRecursos(identificador,dFichero, submanifestId,LdapUserDetailsUtils.getUsuario(),LdapUserDetailsUtils.getIdioma());
	    		}
		   		//Hacer algo con resultado
		   		String mensaje="";
		   		//BORRAR
		   		logger.debug("Tras importarRecursos, miramos resultado");
		   		if (agregarVO!=null&&agregarVO.isNecesitaCambio()) {
					if (agregarVO.isResultado()) {
						mensaje = i18n.getString("licencia.cambiadaA")+ agregarVO.getLicenciaAdicional();
					} else {
						mensaje = i18n.getString("licencia.noCompatible");
					}
					//BORRAR
					logger.debug("Mensaje es "+mensaje);
					sesEmpaq.setMensajeCompatibilidadLicencias(mensaje);
					sesEmpaq.setMostradoMensajeCompatibilidadLicencia(false);
/*					
 					if (agregarVO.isResultado()) {		
						//si comento esto y habilito el editar ODE de srvGestorManifest
						//me muestra el nuevo recurso añadido pero no me cambia la licencia
						OdeVO odeEditado = this.getSrvGestorManifestService().editarODEControlandoBackups(identificador, false);

						//getSrvGestorManifestService().guardarManifiestoControlandoBackups(identificador, false, false);
						//BORRAR
						logger.debug("Refrescamos ODE en sesion");
						MetodosComunes.refrescaSesion(sesEmpaq, odeEditado);
						//BORRAR
						logger.debug("Refrescado");
				       	setEmpaquetadorSession(request, sesEmpaq);
					}
*/
				} else {
					logger.debug("No es necesario un cambio de licencia");
				}
		   		
			} catch (Exception e) {
				logger.error(e,e);
				throw new ValidatorException("{portalempaquetado.avanzado.recursos.importar.exception.validar}");
			}
    	}
    }

}
