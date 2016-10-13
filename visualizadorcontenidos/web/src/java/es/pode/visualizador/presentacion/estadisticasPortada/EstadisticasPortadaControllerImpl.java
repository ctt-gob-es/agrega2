// license-header java merge-point
package es.pode.visualizador.presentacion.estadisticasPortada;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasVO;
import es.pode.buscar.negocio.buscar.servicios.SrvBuscarService;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;


/**
 * @see es.pode.visualizador.presentacion.estadisticasPortada.EstadisticasPortadaController
 */
public class EstadisticasPortadaControllerImpl extends EstadisticasPortadaController
{
	private static final long serialVersionUID = -7955910470493706247L;
	//	private java.util.Properties pSpringProperties = null;
	transient private static Logger logger = Logger.getLogger(EstadisticasPortadaControllerImpl.class);

    /**
     * @see es.pode.visualizador.presentacion.estadisticasPortada.EstadisticasPortadaController#cargarEstadisticas(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.estadisticasPortada.CargarEstadisticasForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
	public final void cargarEstadisticas(ActionMapping mapping, es.pode.visualizador.presentacion.estadisticasPortada.CargarEstadisticasForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	logger.info("Obteniendo estadisticas. ");
    	
    	try {
			SrvBuscarService srvBuscarService = this.getSrvBuscarService();
			ResultadosEstadisticasVO resultadosEstadisticasVO = srvBuscarService.obtenerEstadisticas();
			
			if(resultadosEstadisticasVO == null)  {
				logger.warn("No hay objetos en la plataforma");
				form.setNumLocalCursos(0);
				form.setNumLocalMediosIntegrados(0);
				form.setNumLocalObjetos(0);
				form.setNumLocalObjetosAprendizaje(0);
				form.setNumLocalSecuencias(0);
				form.setNumTotalCursos(0);
				form.setNumTotalMediosIntegrados(0);
				form.setNumTotalObjetos(0);
				form.setNumTotalObjetosAprendizaje(0);
				form.setNumTotalSecuencias(0);
				
				form.setComunidadesCursos(null);
				form.setComunidadesCursosValores(null);
				form.setComunidadesMediosIntegrados(null);
				form.setComunidadesMediosIntegradosValores(null);
				form.setComunidadesObjAprendizaje(null);
				form.setComunidadesObjAprendizajeValores(null);
				form.setComunidadesObjetos(null);
				form.setComunidadesObjetosValores(null);
				form.setComunidadesSecuencias(null);
				form.setComunidadesSecuenciasValores(null);
				
				
				
				form.setBusquedasLocales(0);
				form.setBusquedasTotales(0);
				form.setDescargas(0);
				form.setDescargasTotales(0);
				form.setFichasAccedidasLocales(0);
				form.setFichasAccedidasTotales(0);
				form.setOdesPrevisualizadosLocales(0);
				form.setOdesPrevisualizadosTotales(0);

				form.setComunidadesBusquedas(null);
				form.setComunidadesBusquedasValores(null);
				form.setComunidadesDescargas(null);
				form.setComunidadesDescargasValores(null);
				form.setComunidadesFichasAccedidas(null);
				form.setComunidadesFichasAccedidasValores(null);
				form.setComunidadesOdesPrevisualizados(null);
				form.setComunidadesOdesPrevisualizadosValores(null);
				
				
				
				form.setTerminosMasBuscadosAnoLocales(null);
				form.setTerminosMasBuscadosAnoNumLocales(null);
				form.setTerminosMasBuscadosAnoNumTotales(null);
				form.setTerminosMasBuscadosAnoTotales(null);
				form.setTerminosMasBuscadosLocales(null);
				form.setTerminosMasBuscadosMesLocales(null);
				form.setTerminosMasBuscadosMesNumLocales(null);
				form.setTerminosMasBuscadosMesNumTotales(null);
				form.setTerminosMasBuscadosNumLocales(null);
				form.setTerminosMasBuscadosNumTotales(null);
				form.setTerminosMasBuscadosSemanaLocales(null);
				form.setTerminosMasBuscadosSemanaNumLocales(null);
				form.setTerminosMasBuscadosSemanaNumTotales(null);
				form.setTerminosMasBuscadosSemanaTotales(null);
				form.setTerminosMasBuscadosTotales(null);
								
				form.setComunidadesMasBuscadoTotalPrimeroValores(null);
				form.setComunidadesMasBuscadoTotalPrimero(null);
				form.setComunidadesMasBuscadoTotalSegundoValores(null);
				form.setComunidadesMasBuscadoTotalSegundo(null);
				form.setComunidadesMasBuscadoTotalTerceroValores(null);
				form.setComunidadesMasBuscadoTotalTercero(null);
				form.setComunidadesMasBuscadoTotalCuartoValores(null);
				form.setComunidadesMasBuscadoTotalCuarto(null);
				form.setComunidadesMasBuscadoTotalQuintoValores(null);
				form.setComunidadesMasBuscadoTotalQuinto(null);

				form.setComunidadesMasBuscadoAnioPrimeroValores(null);
				form.setComunidadesMasBuscadoAnioPrimero(null);
				form.setComunidadesMasBuscadoAnioSegundoValores(null);
				form.setComunidadesMasBuscadoAnioSegundo(null);
				form.setComunidadesMasBuscadoAnioTerceroValores(null);
				form.setComunidadesMasBuscadoAnioTercero(null);
				form.setComunidadesMasBuscadoAnioCuartoValores(null);
				form.setComunidadesMasBuscadoAnioCuarto(null);
				form.setComunidadesMasBuscadoAnioQuintoValores(null);
				form.setComunidadesMasBuscadoAnioQuinto(null);
				
				form.setComunidadesMasBuscadoMesPrimeroValores(null);
				form.setComunidadesMasBuscadoMesPrimero(null);
				form.setComunidadesMasBuscadoMesSegundoValores(null);
				form.setComunidadesMasBuscadoMesSegundo(null);
				form.setComunidadesMasBuscadoMesTerceroValores(null);
				form.setComunidadesMasBuscadoMesTercero(null);
				form.setComunidadesMasBuscadoMesCuartoValores(null);
				form.setComunidadesMasBuscadoMesCuarto(null);
				form.setComunidadesMasBuscadoMesQuintoValores(null);
				form.setComunidadesMasBuscadoMesQuinto(null);
				
				form.setComunidadesMasBuscadoSemanaPrimeroValores(null);
				form.setComunidadesMasBuscadoSemanaPrimero(null);
				form.setComunidadesMasBuscadoSemanaSegundoValores(null);
				form.setComunidadesMasBuscadoSemanaSegundo(null);
				form.setComunidadesMasBuscadoSemanaTerceroValores(null);
				form.setComunidadesMasBuscadoSemanaTercero(null);
				form.setComunidadesMasBuscadoSemanaCuartoValores(null);
				form.setComunidadesMasBuscadoSemanaCuarto(null);
				form.setComunidadesMasBuscadoSemanaQuintoValores(null);
				form.setComunidadesMasBuscadoSemanaQuinto(null);
			} else {
				// ODES
				form.setNumLocalCursos(resultadosEstadisticasVO.getNumLocalCursos());
				form.setNumLocalMediosIntegrados(resultadosEstadisticasVO.getNumLocalMediosIntegrados());
				form.setNumLocalObjetos(resultadosEstadisticasVO.getNumLocalObjetos());
				form.setNumLocalObjetosAprendizaje(resultadosEstadisticasVO.getNumLocalObjetosAprendizaje());
				form.setNumLocalSecuencias(resultadosEstadisticasVO.getNumLocalSecuencias());
				form.setNumTotalCursos(resultadosEstadisticasVO.getNumTotalCursos());
				form.setNumTotalMediosIntegrados(resultadosEstadisticasVO.getNumTotalMediosIntegrados());
				form.setNumTotalObjetos(resultadosEstadisticasVO.getNumTotalObjetos());
				form.setNumTotalObjetosAprendizaje(resultadosEstadisticasVO.getNumTotalObjetosAprendizaje());
				form.setNumTotalSecuencias(resultadosEstadisticasVO.getNumTotalSecuencias());
				
				form.setComunidadesCursos(resultadosEstadisticasVO.getComunidadesCursos());
				form.setComunidadesCursosValores(resultadosEstadisticasVO.getComunidadesCursosValores());
				form.setComunidadesMediosIntegrados(resultadosEstadisticasVO.getComunidadesMediosIntegrados());
				form.setComunidadesMediosIntegradosValores(resultadosEstadisticasVO.getComunidadesMediosIntegradosValores());
				form.setComunidadesObjAprendizaje(resultadosEstadisticasVO.getComunidadesObjAprendizaje());
				form.setComunidadesObjAprendizajeValores(resultadosEstadisticasVO.getComunidadesObjAprendizajeValores());
				form.setComunidadesObjetos(resultadosEstadisticasVO.getComunidadesObjetos());
				form.setComunidadesObjetosValores(resultadosEstadisticasVO.getComunidadesObjetosValores());
				form.setComunidadesSecuencias(resultadosEstadisticasVO.getComunidadesSecuencias());
				form.setComunidadesSecuenciasValores(resultadosEstadisticasVO.getComunidadesSecuenciasValores());
				
				
				// ACTIVIDAD
				form.setBusquedasLocales(resultadosEstadisticasVO.getBusquedasLocales());
				form.setBusquedasTotales(resultadosEstadisticasVO.getBusquedasTotales());
				form.setDescargas(resultadosEstadisticasVO.getDescargas());
				form.setDescargasTotales(resultadosEstadisticasVO.getDescargasTotales());
				form.setFichasAccedidasLocales(resultadosEstadisticasVO.getFichasAccedidasLocales());
				form.setFichasAccedidasTotales(resultadosEstadisticasVO.getFichasAccedidasTotales());
				form.setOdesPrevisualizadosLocales(resultadosEstadisticasVO.getOdesPrevisualizadosLocales());
				form.setOdesPrevisualizadosTotales(resultadosEstadisticasVO.getOdesPrevisualizadosTotales());

				form.setComunidadesBusquedas(resultadosEstadisticasVO.getComunidadesBusquedas());
				form.setComunidadesBusquedasValores(resultadosEstadisticasVO.getComunidadesBusquedasValores());
				form.setComunidadesDescargas(resultadosEstadisticasVO.getComunidadesDescargas());
				form.setComunidadesDescargasValores(resultadosEstadisticasVO.getComunidadesDescargasValores());
				form.setComunidadesFichasAccedidas(resultadosEstadisticasVO.getComunidadesFichasAccedidas());
				form.setComunidadesFichasAccedidasValores(resultadosEstadisticasVO.getComunidadesFichasAccedidasValores());
				form.setComunidadesOdesPrevisualizados(resultadosEstadisticasVO.getComunidadesOdesPrevisualizados());
				form.setComunidadesOdesPrevisualizadosValores(resultadosEstadisticasVO.getComunidadesOdesPrevisualizadosValores());
				
				
				// TERMINOS
				form.setTerminosMasBuscadosLocales(resultadosEstadisticasVO.getTerminosMasBuscadosLocales());
				form.setTerminosMasBuscadosTotales(resultadosEstadisticasVO.getTerminosMasBuscadosTotales());
				form.setTerminosMasBuscadosNumLocales(resultadosEstadisticasVO.getTerminosMasBuscadosNumLocales());
				form.setTerminosMasBuscadosNumTotales(resultadosEstadisticasVO.getTerminosMasBuscadosNumTotales());				
				form.setTerminosMasBuscadosAnoLocales(resultadosEstadisticasVO.getTerminosMasBuscadosAnoLocales());
				form.setTerminosMasBuscadosAnoTotales(resultadosEstadisticasVO.getTerminosMasBuscadosAnoTotales());
				form.setTerminosMasBuscadosAnoNumLocales(resultadosEstadisticasVO.getTerminosMasBuscadosAnoNumLocales());
				form.setTerminosMasBuscadosAnoNumTotales(resultadosEstadisticasVO.getTerminosMasBuscadosAnoNumTotales());
				form.setTerminosMasBuscadosMesLocales(resultadosEstadisticasVO.getTerminosMasBuscadosMesLocales());
				form.setTerminosMasBuscadosMesTotales(resultadosEstadisticasVO.getTerminosMasBuscadosMesTotales());
				form.setTerminosMasBuscadosMesNumLocales(resultadosEstadisticasVO.getTerminosMasBuscadosMesNumLocales());
				form.setTerminosMasBuscadosMesNumTotales(resultadosEstadisticasVO.getTerminosMasBuscadosMesNumTotales());
				form.setTerminosMasBuscadosSemanaLocales(resultadosEstadisticasVO.getTerminosMasBuscadosSemanaLocales());
				form.setTerminosMasBuscadosSemanaTotales(resultadosEstadisticasVO.getTerminosMasBuscadosSemanaTotales());
				form.setTerminosMasBuscadosSemanaNumLocales(resultadosEstadisticasVO.getTerminosMasBuscadosSemanaNumLocales());
				form.setTerminosMasBuscadosSemanaNumTotales(resultadosEstadisticasVO.getTerminosMasBuscadosSemanaNumTotales());
								
				form.setComunidadesMasBuscadoTotalPrimeroValores(resultadosEstadisticasVO.getComunidadesMasBuscadoTotalPrimeroValores());
				form.setComunidadesMasBuscadoTotalPrimero(resultadosEstadisticasVO.getComunidadesMasBuscadoTotalPrimero());
				form.setComunidadesMasBuscadoTotalSegundoValores(resultadosEstadisticasVO.getComunidadesMasBuscadoTotalSegundoValores());
				form.setComunidadesMasBuscadoTotalSegundo(resultadosEstadisticasVO.getComunidadesMasBuscadoTotalSegundo());
				form.setComunidadesMasBuscadoTotalTerceroValores(resultadosEstadisticasVO.getComunidadesMasBuscadoTotalTerceroValores());
				form.setComunidadesMasBuscadoTotalTercero(resultadosEstadisticasVO.getComunidadesMasBuscadoTotalTercero());
				form.setComunidadesMasBuscadoTotalCuartoValores(resultadosEstadisticasVO.getComunidadesMasBuscadoTotalCuartoValores());
				form.setComunidadesMasBuscadoTotalCuarto(resultadosEstadisticasVO.getComunidadesMasBuscadoTotalCuarto());
				form.setComunidadesMasBuscadoTotalQuintoValores(resultadosEstadisticasVO.getComunidadesMasBuscadoTotalQuintoValores());
				form.setComunidadesMasBuscadoTotalQuinto(resultadosEstadisticasVO.getComunidadesMasBuscadoTotalQuinto());

				form.setComunidadesMasBuscadoAnioPrimeroValores(resultadosEstadisticasVO.getComunidadesMasBuscadoAnioPrimeroValores());
				form.setComunidadesMasBuscadoAnioPrimero(resultadosEstadisticasVO.getComunidadesMasBuscadoAnioPrimero());
				form.setComunidadesMasBuscadoAnioSegundoValores(resultadosEstadisticasVO.getComunidadesMasBuscadoAnioSegundoValores());
				form.setComunidadesMasBuscadoAnioSegundo(resultadosEstadisticasVO.getComunidadesMasBuscadoAnioSegundo());
				form.setComunidadesMasBuscadoAnioTerceroValores(resultadosEstadisticasVO.getComunidadesMasBuscadoAnioTerceroValores());
				form.setComunidadesMasBuscadoAnioTercero(resultadosEstadisticasVO.getComunidadesMasBuscadoAnioTercero());
				form.setComunidadesMasBuscadoAnioCuartoValores(resultadosEstadisticasVO.getComunidadesMasBuscadoAnioCuartoValores());
				form.setComunidadesMasBuscadoAnioCuarto(resultadosEstadisticasVO.getComunidadesMasBuscadoAnioCuarto());
				form.setComunidadesMasBuscadoAnioQuintoValores(resultadosEstadisticasVO.getComunidadesMasBuscadoAnioQuintoValores());
				form.setComunidadesMasBuscadoAnioQuinto(resultadosEstadisticasVO.getComunidadesMasBuscadoAnioQuinto());
				
				form.setComunidadesMasBuscadoMesPrimeroValores(resultadosEstadisticasVO.getComunidadesMasBuscadoMesPrimeroValores());
				form.setComunidadesMasBuscadoMesPrimero(resultadosEstadisticasVO.getComunidadesMasBuscadoMesPrimero());
				form.setComunidadesMasBuscadoMesSegundoValores(resultadosEstadisticasVO.getComunidadesMasBuscadoMesSegundoValores());
				form.setComunidadesMasBuscadoMesSegundo(resultadosEstadisticasVO.getComunidadesMasBuscadoMesSegundo());
				form.setComunidadesMasBuscadoMesTerceroValores(resultadosEstadisticasVO.getComunidadesMasBuscadoMesTerceroValores());
				form.setComunidadesMasBuscadoMesTercero(resultadosEstadisticasVO.getComunidadesMasBuscadoMesTercero());
				form.setComunidadesMasBuscadoMesCuartoValores(resultadosEstadisticasVO.getComunidadesMasBuscadoMesCuartoValores());
				form.setComunidadesMasBuscadoMesCuarto(resultadosEstadisticasVO.getComunidadesMasBuscadoMesCuarto());
				form.setComunidadesMasBuscadoMesQuintoValores(resultadosEstadisticasVO.getComunidadesMasBuscadoMesQuintoValores());
				form.setComunidadesMasBuscadoMesQuinto(resultadosEstadisticasVO.getComunidadesMasBuscadoMesQuinto());
				
				form.setComunidadesMasBuscadoSemanaPrimeroValores(resultadosEstadisticasVO.getComunidadesMasBuscadoSemanaPrimeroValores());
				form.setComunidadesMasBuscadoSemanaPrimero(resultadosEstadisticasVO.getComunidadesMasBuscadoSemanaPrimero());
				form.setComunidadesMasBuscadoSemanaSegundoValores(resultadosEstadisticasVO.getComunidadesMasBuscadoSemanaSegundoValores());
				form.setComunidadesMasBuscadoSemanaSegundo(resultadosEstadisticasVO.getComunidadesMasBuscadoSemanaSegundo());
				form.setComunidadesMasBuscadoSemanaTerceroValores(resultadosEstadisticasVO.getComunidadesMasBuscadoSemanaTerceroValores());
				form.setComunidadesMasBuscadoSemanaTercero(resultadosEstadisticasVO.getComunidadesMasBuscadoSemanaTercero());
				form.setComunidadesMasBuscadoSemanaCuartoValores(resultadosEstadisticasVO.getComunidadesMasBuscadoSemanaCuartoValores());
				form.setComunidadesMasBuscadoSemanaCuarto(resultadosEstadisticasVO.getComunidadesMasBuscadoSemanaCuarto());
				form.setComunidadesMasBuscadoSemanaQuintoValores(resultadosEstadisticasVO.getComunidadesMasBuscadoSemanaQuintoValores());
				form.setComunidadesMasBuscadoSemanaQuinto(resultadosEstadisticasVO.getComunidadesMasBuscadoSemanaQuinto());
			}
			
		}catch (Exception e) {
			logger.error("Excepción inesperada obteniendo las estadisticas: "+ e);
			throw e;
		}
    }
 
	public final void descargarEstadisticas(org.apache.struts.action.ActionMapping mapping, es.pode.visualizador.presentacion.estadisticasPortada.DescargarEstadisticasForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws java.lang.Exception{
		logger.info("descargarEstadisticas inicio");
		SrvBuscarService srvBuscarService = this.getSrvBuscarService();
		
		String pathFicheroGenerado = srvBuscarService.obtenerFicheroExcelEstadisticas();
		logger.info("descargarEstadisticas obtiene el fic : " + pathFicheroGenerado);
	    response.setContentType("application/xls");	
	 	response.setHeader("Content-Disposition", "attachment;filename=Estadisticas.pdf");
	 	response.setHeader("Cache-Control", "public");	
	 	response.addDateHeader("Expires", System.currentTimeMillis()+(24*60*60*1000));
	 	response.sendRedirect("http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+pathFicheroGenerado);
		
	}
}
