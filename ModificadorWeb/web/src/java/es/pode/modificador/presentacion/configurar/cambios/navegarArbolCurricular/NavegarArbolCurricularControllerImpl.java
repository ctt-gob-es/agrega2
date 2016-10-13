// license-header java merge-point
package es.pode.modificador.presentacion.configurar.cambios.navegarArbolCurricular;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.modificador.negocio.servicio.TaxonomiaVO;
import es.pode.modificador.presentacion.configurar.cambios.CambioSession;
import es.pode.modificador.presentacion.idiomasBuscador.IdiomasBuscadorSingleton;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.modificador.presentacion.configurar.cambios.navegarArbolCurricular.NavegarArbolCurricularController
 */
public class NavegarArbolCurricularControllerImpl extends NavegarArbolCurricularController
{
	private Logger logger = Logger.getLogger(NavegarArbolCurricularController.class);

    /**
     * @see es.pode.modificador.presentacion.configurar.cambios.navegarArbolCurricular.NavegarArbolCurricularController#navegarETB(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.cambios.navegarArbolCurricular.NavegarETBForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */


    public String selectAction(ActionMapping mapping, SelectActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//    	 tan simple como esto
    	if(logger.isDebugEnabled())logger.debug("Accion en navegarController: " + form.getAction());
    	if("Aceptar".equals(form.getAction()) && form.getIdTermino()==null) {
    		throw new ValidatorException("{navegarArbolCurricular.elemento.necesario}");
    	}
		return form.getAction();
	}

	/**
     * @see es.pode.modificador.presentacion.configurar.cambios.navegarArbolCurricular.NavegarArbolCurricularController#guardarTermino(org.apache.struts.action.ActionMapping, es.pode.modificador.presentacion.configurar.cambios.navegarArbolCurricular.GuardarTerminoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    // Guardamos en la sesión el elemento que se ha seleccionado.
    public final void guardarTermino(
			ActionMapping mapping,
			es.pode.modificador.presentacion.configurar.cambios.navegarArbolCurricular.GuardarTerminoForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xml;
		CambioSession sesion = this.getCambioSession(request);
		try {
			if (form.getIdTermino() == null) {
				if(logger.isDebugEnabled())logger.debug("no se ha seleccionado un idTermino");
				saveErrorMessage(request,
						"navegarArbolCurricular.elemento.necesario");
			} else {
				TaxonomiaVO tx = this.getSrvHerramientaModificacion()
						.navegarTaxonomia(form.getIdTermino(), sesion.getTipoTaxonomia(),
								LdapUserDetailsUtils.getIdioma());
				xml = this.getSrvHerramientaModificacion()
						.generarArbolCurricular(tx);
				this.getCambioSession(request).setValorNuevo(xml);
				this.getCambioSession(request).setLomTerm("lom.classification.taxonPath");
		        this.getCambioSession(request).setIdLomTerm("9.2");
				logger.info("NavegarArbolCurricular: taxon xml guardado en sesión: "
						+ xml);
			}
		} catch (RuntimeException e) {

			logger.error("Excepcion en el servicio de herramienta modificacion al guardar termino: ",e);
		}
		
	}

	public void navegar(ActionMapping mapping, NavegarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// De momento en nombreTaxonomia pasamos un null, más adelante quizás se ponga un combo para escoger una.
		// El srv por debajo si hay null mete una clave que fuentesTaxonomicas interpreta como que queremos el vigente.
		CambioSession sesion = this.getCambioSession(request);
		try {
			if(logger.isDebugEnabled())logger.debug("Entramos en navegar en taxonomia con idioma "+ LdapUserDetailsUtils.getIdioma());
			TaxonomiaVO tx = null;
			if (form.getIdTermino()!=null&&form.getIdTermino().trim().length()>0)
			{
				if(logger.isDebugEnabled())logger.debug("La taxonomia que nos llega es " + sesion.getTipoTaxonomia());
				tx = this.getSrvHerramientaModificacion().navegarTaxonomia(form.getIdTermino(), sesion.getTipoTaxonomia(),LdapUserDetailsUtils.getIdioma()); //Segundo parámetro nombre taxonomía
				form.setTaxonomia(tx);
			}
			else {
				//para el primer caso
				if(logger.isDebugEnabled())logger.debug("La taxonomia que nos llega es " + sesion.getTipoTaxonomia());
				tx = this.getSrvHerramientaModificacion().navegarTaxonomia(null,  sesion.getTipoTaxonomia(),LdapUserDetailsUtils.getIdioma());
			}
//			 Es necesario hacer un reverse del taxonPath para mostrar correctamente la rama del arbol:
			//Ya no se necesario invertir el orden, ya lo hacemos
//			if(tx.getTaxonPath()!=null && tx.getTaxonPath().length>1) {
//				if(logger.isDebugEnabled()) logger.debug("Invierto el orden de taxonPath");
//				List taxonPath = Arrays.asList(tx.getTaxonPath());
//				Collections.reverse(taxonPath);
//				tx.setTaxonPath((TaxonVO[])taxonPath.toArray(new TaxonVO[taxonPath.size()]));
//			}
			form.setTaxonomia(tx);
		} catch (RuntimeException e) {
			logger.error("Excepcion en el servicio de herramienta modificacion al navegar: ",e);
			saveErrorMessage(request, "areaCurricular.error.inesperado");
				}
		logger.info("Taxonomia genereada correctamente");
		form.setIdiomaBuscadorBackingList(IdiomasBuscadorSingleton.getInstance().obtenerIdiomas(LdapUserDetailsUtils.getIdioma()), "idLocalizacion", "nombre");
	}

}