// license-header java merge-point
package es.pode.buscador.presentacion.avanzado.formularioAvanzado;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.buscador.presentacion.BuscarSession;
import es.pode.buscador.presentacion.avanzado.buscarAvanzado.BuscarAvanzadoControllerImpl;
import es.pode.buscador.presentacion.basico.detallar.DetallarControllerImpl;



/**
 * @see es.pode.buscador.presentacion.avanzado.formularioAvanzado.FormularioAvanzadoController
 */
public class FormularioAvanzadoControllerImpl extends FormularioAvanzadoController
{

	private static Logger logger = Logger.getLogger(BuscarAvanzadoControllerImpl.class);

	private java.util.Properties pSpringProperties = null;


    /**
     * @see es.pode.buscador.presentacion.avanzado.formularioAvanzado.FormularioAvanzadoController#prepararConsulta(org.apache.struts.action.ActionMapping, es.pode.buscador.presentacion.avanzado.formularioAvanzado.PrepararConsultaForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void prepararConsulta(ActionMapping mapping, es.pode.buscador.presentacion.avanzado.formularioAvanzado.PrepararConsultaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	log("FormularioAvanzadoControllerImpl - prepararConsulta.");
    	inicializarSesion(request);
    	try{
	    	if("true".equals(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NEUTRO))) form.setTipoLayoutBuscador(DetallarControllerImpl.LAYOUT_NEUTRO);
	    	else form.setTipoLayoutBuscador(DetallarControllerImpl.LAYOUT_BUSCADOR);
    	}catch (Exception e)
    	{
    		log("No existe la propiedad neutro del agregaProperties");
    		form.setTipoLayoutBuscador(DetallarControllerImpl.LAYOUT_BUSCADOR);
    	}
     }

    private BuscarSession inicializarSesion(HttpServletRequest request){
		BuscarSession sesion = this.getBuscarSession(request);
//		Conservo los únicos parámetros constantes
		if(sesion.getEmpIdOde() != null || sesion.getEmpIdDestino() != null || sesion.getEmpTipoEmpaquetador() != null){
			log("FormularioAvanzadoControllerImpl - inicializarSesion: Se agrega ode federado al empaquetador con  IdOde:" +sesion.getEmpIdOde()+" IdDestino:"+sesion.getEmpIdDestino()+" TipoEmpaquetador:"+ sesion.getEmpTipoEmpaquetador());
			String idODe=sesion.getEmpIdOde();
			String idDestino=sesion.getEmpIdDestino();
			String tipoEmpaquetador=sesion.getEmpTipoEmpaquetador();
			sesion = new BuscarSession();
			sesion.setEmpIdOde(idODe);
			sesion.setEmpIdDestino(idDestino);
			sesion.setEmpTipoEmpaquetador(tipoEmpaquetador);
		}else{
			sesion = new BuscarSession();
		}
		this.setBuscarSession(request,sesion);
		return sesion;
	}

    private String getPropertyValue(String sKey) throws IOException {
		InputStream fIsSpringProperties = this.getClass().getResourceAsStream("/spring_buscador2.properties");
		if (this.pSpringProperties == null) {
			pSpringProperties = new java.util.Properties();
			pSpringProperties.load(fIsSpringProperties);
		}
		fIsSpringProperties.close();
		log("BuscarAvanzadoControllerImpl - getPropertyValue: Propiedad recuperada: " + sKey + " : "+ pSpringProperties.getProperty(sKey));
//		 devolvemos la propiedad
		return pSpringProperties.getProperty(sKey);
	}

    private void log(String traza){
		if (logger.isDebugEnabled())logger.debug(traza);
	}
}