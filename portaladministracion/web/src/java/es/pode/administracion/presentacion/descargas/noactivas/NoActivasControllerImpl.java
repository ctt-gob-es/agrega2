// license-header java merge-point
package es.pode.administracion.presentacion.descargas.noactivas;

import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.administracion.presentacion.descargas.DescargaInfo;
import es.pode.contenidos.negocio.descargas.servicio.DescDescargaVO;
import es.pode.contenidos.negocio.descargas.servicio.DescargaVO;
import es.pode.soporte.constantes.ConstantesAgrega;



/**
 * @see es.pode.administracion.presentacion.descargas.noactivas.NoActivasController
 */
public class NoActivasControllerImpl extends NoActivasController
{
	private static final long serialVersionUID = -7733434119825744533L;
	private static final Logger logger = Logger.getLogger(NoActivasControllerImpl.class);
	private static String VACIA ="";
	
    /**
     * @see es.pode.administracion.presentacion.descargas.noactivas.NoActivasController#obtenerDatos(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.descargas.noactivas.ObtenerDatosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerDatos(ActionMapping mapping, es.pode.administracion.presentacion.descargas.noactivas.ObtenerDatosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		DescargaVO descargas[] = getSrvDescargas().obtenerDescargasNoActivas();
		logger.debug("Recuperadas "+descargas.length+" descargas");
		ArrayList<DescargaInfo> listaDescargas = new ArrayList<DescargaInfo>();
		if (descargas!=null&&descargas.length>0) {
			String idioma = ((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
			DescDescargaVO[] descs = getSrvDescargas()
					.obtenerDescDescargasIdioma(descargas, idioma);
			logger.debug("Recuperadas " + descs.length
					+ " descripciones de descargas");
			for (int i = 0; i < descargas.length; i++) {
				DescargaInfo info = new DescargaInfo(descargas[i],descs[i]);
				listaDescargas.add(info);
			}
		}
		logger.debug("Lista de descargas construida");
		form.setDescargas(listaDescargas);
     }

    /**
     * @see es.pode.administracion.presentacion.descargas.noactivas.NoActivasController#activar(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.descargas.noactivas.ActivarForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void activar(ActionMapping mapping, es.pode.administracion.presentacion.descargas.noactivas.ActivarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	getSrvDescargas().modificarDescarga(form.getIdentificador(), VACIA, Boolean.TRUE, new DescDescargaVO[]{});
     }

}