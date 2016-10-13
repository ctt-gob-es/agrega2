// license-header java merge-point
package es.pode.modificador.presentacion.configurar.objetos.buscar.navegarTaxonomia;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.modificador.negocio.servicio.TaxonomiaVO;
import es.pode.modificador.presentacion.configurar.objetos.buscar.BusquedaSession;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.modificador.presentacion.configurar.objetos.buscar.navegarTaxonomia.NavegarTaxonomiaController
 */
public class NavegarTaxonomiaControllerImpl extends NavegarTaxonomiaController
{




	private static Logger logger = Logger.getLogger(NavegarTaxonomiaControllerImpl.class);

	@Override
	public TaxonomiaVO guardarTermino(ActionMapping mapping, GuardarTerminoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		TaxonomiaVO vuelta=new TaxonomiaVO();
		BusquedaSession sesion=getBusquedaSession(request);
		String nombreTax=sesion.getNombreTaxonomia();
		try {
			if (form.getIdTermino() == null) {
				if(logger.isDebugEnabled())logger.debug("no se ha seleccionado un idTermino");
				saveErrorMessage(request,
						"navegarArbolCurricular.elemento.necesario");
			} else {
				vuelta = this.getSrvHerramientaModificacion()
						.navegarTaxonomia(form.getIdTermino(), nombreTax,
								LdapUserDetailsUtils.getIdioma());

			}
			
			Collection<TaxonomiaVO> coleccionTaxones=sesion.getTaxonesSeleccionados();
			if(coleccionTaxones==null){
				coleccionTaxones=new ArrayList<TaxonomiaVO>();
				coleccionTaxones.add(vuelta);
			}else{
				coleccionTaxones.add(vuelta);
			}
			sesion.setTaxonesSeleccionados(coleccionTaxones);
		} catch (RuntimeException e) {

			logger.error("Excepcion en el servicio de herramienta modificacion al guardar termino: ",e);
		}
		return vuelta;
	}

	@Override
	public void navegar(ActionMapping mapping, NavegarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		 De momento en nombreTaxonomia pasamos un null, más adelante quizás se ponga un combo para escoger una.
		// El srv por debajo si hay null mete una clave que fuentesTaxonomicas interpreta como que queremos el vigente.
		BusquedaSession sesion=getBusquedaSession(request);
		String nomTaxonomia=sesion.getNombreTaxonomia();
		form.setNombreTaxonomia(nomTaxonomia);
		try {
			if(logger.isDebugEnabled())logger.debug("Entramos en navegar en taxonomia con idioma "+ LdapUserDetailsUtils.getIdioma());
			TaxonomiaVO tx = null;
			if (form.getIdTermino()!=null&&form.getIdTermino().trim().length()>0)
			{

				if(logger.isDebugEnabled())logger.debug("La taxonomia que nos llega es " + form.getNombreTaxonomia());
				tx = this.getSrvHerramientaModificacion().navegarTaxonomia(form.getIdTermino(), form.getNombreTaxonomia(),LdapUserDetailsUtils.getIdioma()); //Segundo parámetro nombre taxonomía
				form.setTaxonomia(tx);
			}
			else {
				//para el primer caso
				if(logger.isDebugEnabled())logger.debug("La taxonomia que nos llega es " + form.getNombreTaxonomia());
				tx = this.getSrvHerramientaModificacion().navegarTaxonomia(null,  form.getNombreTaxonomia(),LdapUserDetailsUtils.getIdioma());
			}
			form.setTaxonomia(tx);
		} catch (RuntimeException e) {
			logger.error("Excepcion en el servicio de herramienta modificacion al navegar: ",e);
			saveErrorMessage(request, "areaCurricular.error.inesperado");
				}
		logger.info("Taxonomia genereada correctamente");
		form.setIdioma(form.getIdioma());
		
	}

	@Override
	public String selectAction(ActionMapping mapping, SelectActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(logger.isDebugEnabled())logger.debug("Accion en NavegarTaxonomiaControllerImpl: " + form.getAction());
    	if("Aceptar".equals(form.getAction()) && form.getIdTermino()==null) {
    		throw new ValidatorException("{navegarArbolCurricular.elemento.necesario}");
    	}
		return form.getAction();
	}
	

	 






}