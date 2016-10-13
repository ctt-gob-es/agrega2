/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.administracion.presentacion.estadisticas;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasActividadVO;
import es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasCoberturaCurricularVO;
import es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasLicenciasVO;
import es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasOdesVO;
import es.pode.buscar.negocio.buscar.servicios.ResultadosEstadisticasTerminosVO;
import es.pode.buscar.negocio.buscar.servicios.SrvBuscarService;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @see es.pode.administracion.presentacion.estadisticas.EstadisticasController
 */
public class EstadisticasControllerImpl extends EstadisticasController {

	private static final Logger logger = Logger.getLogger(EstadisticasControllerImpl.class);
	public final static String APPLICATION_PROPERTIES = "application-resources";
	public final static String ACCION_DESCARGAR_ESTADISTICAS= "descargarExcelEstadisticas";	
	public final static String ACCION_CAMBIAR_FECHAS="cambiarFechas";

	
	/**
	 * @see es.pode.administracion.presentacion.estadisticas.EstadisticasController#obtenerEstadisticasPorFechas(org.apache.struts.action.ActionMapping,
	 *      es.pode.administracion.presentacion.estadisticas.ObtenerEstadisticasPorFechasForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public final void obtenerEstadisticasPorFechas(
			ActionMapping mapping,
			es.pode.administracion.presentacion.estadisticas.ObtenerEstadisticasPorFechasForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			
		SrvBuscarService SrvBuscarService = this.getSrvBuscarService();
		
		Calendar fechaDesdeActividad = Calendar.getInstance(); 
		Calendar fechaHastaActividad = Calendar.getInstance(); 
		
		checkFechas(form.getFechaDesdeActividad(), form.getFechaHastaActividad(), fechaDesdeActividad, fechaHastaActividad);
		
		try {
			ResultadosEstadisticasActividadVO resultadosEstadisticasActividadVO = SrvBuscarService.obtenerEstadisticasActividadPorFechas(fechaDesdeActividad, fechaHastaActividad);
			form.setBusquedasLocales(resultadosEstadisticasActividadVO.getBusquedasLocales());
			form.setBusquedasTotales(resultadosEstadisticasActividadVO.getBusquedasTotales());
			form.setComunidadesBusquedas(resultadosEstadisticasActividadVO.getComunidadesBusquedas());
			form.setComunidadesBusquedasValores(resultadosEstadisticasActividadVO.getComunidadesBusquedasValores());
			
			form.setDescargas(resultadosEstadisticasActividadVO.getDescargas());
			form.setDescargasTotales(resultadosEstadisticasActividadVO.getDescargasTotales());
			form.setComunidadesDescargas(resultadosEstadisticasActividadVO.getComunidadesDescargas());
			form.setComunidadesDescargasValores(resultadosEstadisticasActividadVO.getComunidadesDescargasValores());
			
			form.setFichasAccedidasLocales(resultadosEstadisticasActividadVO.getFichasAccedidasLocales());
			form.setFichasAccedidasTotales(resultadosEstadisticasActividadVO.getFichasAccedidasTotales());
			form.setComunidadesFichasAccedidas(resultadosEstadisticasActividadVO.getComunidadesFichasAccedidas());
			form.setComunidadesFichasAccedidasValores(resultadosEstadisticasActividadVO.getComunidadesFichasAccedidasValores());
			
			form.setOdesPrevisualizadosLocales(resultadosEstadisticasActividadVO.getOdesPrevisualizadosLocales());
			form.setOdesPrevisualizadosTotales(resultadosEstadisticasActividadVO.getOdesPrevisualizadosTotales());
			form.setComunidadesOdesPrevisualizados(resultadosEstadisticasActividadVO.getComunidadesOdesPrevisualizados());
			form.setComunidadesOdesPrevisualizadosValores(resultadosEstadisticasActividadVO.getComunidadesOdesPrevisualizadosValores());
		} catch (Exception ex) {
			logger.warn("No se han recuperado bien las estadisticas del bloque de actividad :" + ex.getMessage());
		}
		
		Calendar fechaDesdeCobertura = Calendar.getInstance();
		Calendar fechaHastaCobertura = Calendar.getInstance();
		
		checkFechas(form.getFechaDesdeCoberturaCurricular(), form.getFechaHastaCoberturaCurricular(), fechaDesdeCobertura, fechaHastaCobertura);
		
		try {
			ResultadosEstadisticasCoberturaCurricularVO resultadosEstadisticasCoberturaCurricularVO = SrvBuscarService.obtenerEstadisticasCoberturaCurricularPorFechas(fechaDesdeCobertura, fechaHastaCobertura);
			form.setBachilleratoLocales(resultadosEstadisticasCoberturaCurricularVO.getBachilleratoLocales());
			form.setBachilleratoTotales(resultadosEstadisticasCoberturaCurricularVO.getBachilleratoTotales());
			form.setComunidadesBachillerato(resultadosEstadisticasCoberturaCurricularVO.getComunidadesBachillerato());
			form.setComunidadesBachilleratoValores(resultadosEstadisticasCoberturaCurricularVO.getComunidadesBachilleratoValores());
			
			form.setEducacionInfantilLocales(resultadosEstadisticasCoberturaCurricularVO.getEducacionInfantilLocales());
			form.setEducacionInfantilTotales(resultadosEstadisticasCoberturaCurricularVO.getEducacionInfantilTotales());
			form.setComunidadesEducacionInfantil(resultadosEstadisticasCoberturaCurricularVO.getComunidadesEducacionInfantil());
			form.setComunidadesEducacionInfantilValores(resultadosEstadisticasCoberturaCurricularVO.getComunidadesEducacionInfantilValores());
			
			form.setEducacionPrimariaLocales(resultadosEstadisticasCoberturaCurricularVO.getEducacionPrimariaLocales());
			form.setEducacionPrimariaTotales(resultadosEstadisticasCoberturaCurricularVO.getEducacionPrimariaTotales());
			form.setComunidadesEducacionPrimaria(resultadosEstadisticasCoberturaCurricularVO.getComunidadesEducacionPrimaria());
			form.setComunidadesEducacionPrimariaValores(resultadosEstadisticasCoberturaCurricularVO.getComunidadesEducacionPrimariaValores());
			
			form.setEduSecundariaObligatoriaLocales(resultadosEstadisticasCoberturaCurricularVO.getEduSecundariaObligatoriaLocales());
			form.setEduSecundariaObligatoriaTotales(resultadosEstadisticasCoberturaCurricularVO.getEduSecundariaObligatoriaTotales());
			form.setComunidadesEduSecundariaObligatoria(resultadosEstadisticasCoberturaCurricularVO.getComunidadesEduSecundariaObligatoria());
			form.setComunidadesEduSecundariaObligatoriaValores(resultadosEstadisticasCoberturaCurricularVO.getComunidadesEduSecundariaObligatoriaValores());
			
			form.setEnsArtisticasLocales(resultadosEstadisticasCoberturaCurricularVO.getEnsArtisticasLocales());
			form.setEnsArtisticasTotales(resultadosEstadisticasCoberturaCurricularVO.getEnsArtisticasTotales());
			form.setComunidadesEnsArtisticas(resultadosEstadisticasCoberturaCurricularVO.getComunidadesEnsArtisticas());
			form.setComunidadesEnsArtisticasValores(resultadosEstadisticasCoberturaCurricularVO.getComunidadesEnsArtisticasValores());
			
			form.setEnsOficialIdiomasLocales(resultadosEstadisticasCoberturaCurricularVO.getEnsOficialIdiomasLocales());
			form.setEnsOficialIdiomasTotales(resultadosEstadisticasCoberturaCurricularVO.getEnsOficialIdiomasTotales());
			form.setComunidadesEnsOficialIdiomas(resultadosEstadisticasCoberturaCurricularVO.getComunidadesEnsOficialIdiomas());
			form.setComunidadesEnsOficialIdiomasValores(resultadosEstadisticasCoberturaCurricularVO.getComunidadesEnsOficialIdiomasValores());
			
			form.setFormacionProfesionalLocales(resultadosEstadisticasCoberturaCurricularVO.getFormacionProfesionalLocales());
			form.setFormacionProfesionalTotales(resultadosEstadisticasCoberturaCurricularVO.getFormacionProfesionalTotales());
			form.setComunidadesFormacionProfesional(resultadosEstadisticasCoberturaCurricularVO.getComunidadesFormacionProfesional());
			form.setComunidadesFormacionProfesionalValores(resultadosEstadisticasCoberturaCurricularVO.getComunidadesFormacionProfesionalValores());
		} catch (Exception ex) {
			logger.warn("No se han recuperado bien las estadisticas del bloque de cobertura curricular:" + ex.getMessage());
		}
		
		Calendar fechaDesdeLicencias = Calendar.getInstance();
		Calendar fechaHastaLicencias = Calendar.getInstance();
		
		checkFechas(form.getFechaDesdeLicencias(), form.getFechaHastaLicencias(), fechaDesdeLicencias, fechaHastaLicencias);
		
		try {
			ResultadosEstadisticasLicenciasVO resultadosEstadisticasLicenciasVO = SrvBuscarService.obtenerEstadisticasLicenciasPorFechas(fechaDesdeLicencias, fechaHastaLicencias);
			form.setGFDLLocales(resultadosEstadisticasLicenciasVO.getGFDLLocales());
			form.setGFDLTotales(resultadosEstadisticasLicenciasVO.getGFDLTotales());
			form.setComunidadesGFDL(resultadosEstadisticasLicenciasVO.getComunidadesGFDL());
			form.setComunidadesGFDLValores(resultadosEstadisticasLicenciasVO.getComunidadesGFDLValores());
			
			form.setLicenciaLibreDualLocales(resultadosEstadisticasLicenciasVO.getLicenciaLibreDualLocales());
			form.setLicenciaLibreDualTotales(resultadosEstadisticasLicenciasVO.getLicenciaLibreDualTotales());
			form.setComunidadesLicenciaLibreDual(resultadosEstadisticasLicenciasVO.getComunidadesLicenciaLibreDual());
			form.setComunidadesLicenciaLibreDualValores(resultadosEstadisticasLicenciasVO.getComunidadesLicenciaLibreDualValores());
			
			form.setLicenciaLibreEUPLLocales(resultadosEstadisticasLicenciasVO.getLicenciaLibreEUPLLocales());
			form.setLicenciaLibreEUPLTotlaes(resultadosEstadisticasLicenciasVO.getLicenciaLibreEUPLTotlaes());
			form.setComunidadesLicenciaLibreEUPL(resultadosEstadisticasLicenciasVO.getComunidadesLicenciaLibreEUPL());
			form.setComunidadesLicenciaLibreEUPLValores(resultadosEstadisticasLicenciasVO.getComunidadesLicenciaLibreEUPLValores());
			
			form.setLicenciaLibreGPLLoclaes(resultadosEstadisticasLicenciasVO.getLicenciaLibreGPLLoclaes());
			form.setLicenciaLibreGPLTotales(resultadosEstadisticasLicenciasVO.getLicenciaLibreGPLTotales());
			form.setComunidadesLicenciaLibreGPL(resultadosEstadisticasLicenciasVO.getComunidadesLicenciaLibreGPL());
			form.setComunidadesLicenciaLibreGPLValores(resultadosEstadisticasLicenciasVO.getComunidadesLicenciaLibreGPLValores());
			
			form.setNoCorrespondeLocales(resultadosEstadisticasLicenciasVO.getNoCorrespondeLocales());
			form.setNoCorrespondeTotales(resultadosEstadisticasLicenciasVO.getNoCorrespondeTotales());
			form.setComunidadesNoCorresponde(resultadosEstadisticasLicenciasVO.getComunidadesNoCorresponde());
			form.setComunidadesNoCorrespondeValores(resultadosEstadisticasLicenciasVO.getComunidadesNoCorrespondeValores());

			form.setOtrasLicenciasLibresLocales(resultadosEstadisticasLicenciasVO.getOtrasLicenciasLibresLocales());
			form.setOtrasLicenciasLibresTotales(resultadosEstadisticasLicenciasVO.getOtrasLicenciasLibresTotales());
			form.setComunidadesOtrasLicenciasLibres(resultadosEstadisticasLicenciasVO.getComunidadesOtrasLicenciasLibres());
			form.setComunidadesOtrasLicenciasLibresValores(resultadosEstadisticasLicenciasVO.getComunidadesOtrasLicenciasLibresValores());
			
			
			
			form.setLicenciaPropietariaLocales(resultadosEstadisticasLicenciasVO.getLicenciaPropietariaLocales());
			form.setLicenciaPropietariaTotales(resultadosEstadisticasLicenciasVO.getLicenciaPropietariaTotales());
			form.setComunidadesLicenciaPropietaria(resultadosEstadisticasLicenciasVO.getComunidadesLicenciaPropietaria());
			form.setComunidadesLicenciaPropietariaValores(resultadosEstadisticasLicenciasVO.getComunidadesLicenciaPropietariaValores());
			
			form.setDominioPublicoLocales(resultadosEstadisticasLicenciasVO.getDominioPublicoLocales());
			form.setDominioPublicoTotales(resultadosEstadisticasLicenciasVO.getDominioPublicoTotales());
			form.setComunidadesDominioPublico(resultadosEstadisticasLicenciasVO.getComunidadesDominioPublico());
			form.setComunidadesDominioPublicoValores(resultadosEstadisticasLicenciasVO.getComunidadesDominioPublicoValores());

			form.setLicenciaPropietariaIntelectualLocales(resultadosEstadisticasLicenciasVO.getLicenciaPropietariaIntelectualLocales());
			form.setLicenciaPropietariaIntelectualTotales(resultadosEstadisticasLicenciasVO.getLicenciaPropietariaIntelectualTotales());
			form.setComunidadesLicenciaPropietariaIntelectual(resultadosEstadisticasLicenciasVO.getComunidadesLicenciaPropietariaIntelectual());
			form.setComunidadesLicenciaPropietariaIntelectualValores(resultadosEstadisticasLicenciasVO.getComunidadesLicenciaPropietariaIntelectualValores());
			
			form.setCCRReconocimientoLocales(resultadosEstadisticasLicenciasVO.getCCRReconocimientoLocales());
			form.setCCRReconocimientoTotales(resultadosEstadisticasLicenciasVO.getCCRReconocimientoTotales());
			form.setComunidadesCCRReconocimiento(resultadosEstadisticasLicenciasVO.getComunidadesCCRReconocimiento());
			form.setComunidadesCCRReconocimientoValores(resultadosEstadisticasLicenciasVO.getComunidadesCCRReconocimientoValores());
			
			form.setCCRReconocimientoSinObraDerivadaLocales(resultadosEstadisticasLicenciasVO.getCCRReconocimientoSinObraDerivadaLocales());
			form.setCCRReconocimientoSinObraDerivadaTotales(resultadosEstadisticasLicenciasVO.getCCRReconocimientoSinObraDerivadaTotales());
			form.setComunidadesCCRReconocimientoSinObraDerivada(resultadosEstadisticasLicenciasVO.getComunidadesCCRReconocimientoSinObraDerivada());
			form.setComunidadesCCRReconocimientoSinObraDerivadaValores(resultadosEstadisticasLicenciasVO.getComunidadesCCRReconocimientoSinObraDerivadaValores());

			form.setCCRReconocimientoSinObraDerivadaNoComercialLocales(resultadosEstadisticasLicenciasVO.getCCRReconocimientoSinObraDerivadaNoComercialLocales());
			form.setCCRReconocimientoSinObraDerivadaNoComercialTotales(resultadosEstadisticasLicenciasVO.getCCRReconocimientoSinObraDerivadaNoComercialTotales());
			form.setComunidadesCCRReconocimientoSinObraDerivadaNoComercial(resultadosEstadisticasLicenciasVO.getComunidadesCCRReconocimientoSinObraDerivadaNoComercial());
			form.setComunidadesCCRReconocimientoSinObraDerivadaNoComercialValores(resultadosEstadisticasLicenciasVO.getComunidadesCCRReconocimientoSinObraDerivadaNoComercialValores());
			

			form.setCCRReconocimientoNoComercialLocales(resultadosEstadisticasLicenciasVO.getCCRReconocimientoNoComercialLocales());
			form.setCCRReconocimientoNoComercialTotales(resultadosEstadisticasLicenciasVO.getCCRReconocimientoNoComercialTotales());
			form.setComunidadesCCRReconocimientoNoComercial(resultadosEstadisticasLicenciasVO.getComunidadesCCRReconocimientoNoComercial());
			form.setComunidadesCCRReconocimientoNoComercialValores(resultadosEstadisticasLicenciasVO.getComunidadesCCRReconocimientoNoComercialValores());
			
			form.setCCRReconocimientoNoComercialCompartirIgualLocales(resultadosEstadisticasLicenciasVO.getCCRReconocimientoNoComercialCompartirIgualLocales());
			form.setCCRReconocimientoNoComercialCompartirIgualTotales(resultadosEstadisticasLicenciasVO.getCCRReconocimientoNoComercialCompartirIgualTotales());
			form.setComunidadesCCRReconocimientoNoComercialCompartirIgual(resultadosEstadisticasLicenciasVO.getComunidadesCCRReconocimientoNoComercialCompartirIgual());
			form.setComunidadesCCRReconocimientoNoComercialCompartirIgualValores(resultadosEstadisticasLicenciasVO.getComunidadesCCRReconocimientoNoComercialCompartirIgualValores());

			form.setCCRReconocimientoCompartirIgualLocales(resultadosEstadisticasLicenciasVO.getCCRReconocimientoCompartirIgualLocales());
			form.setCCRReconocimientoCompartirIgualTotales(resultadosEstadisticasLicenciasVO.getCCRReconocimientoCompartirIgualTotales());
			form.setComunidadesCCRReconocimientoCompartirIgual(resultadosEstadisticasLicenciasVO.getComunidadesCCRReconocimientoCompartirIgual());
			form.setComunidadesCCRReconocimientoCompartirIgualValores(resultadosEstadisticasLicenciasVO.getComunidadesCCRReconocimientoCompartirIgualValores());
		} catch (Exception ex) {
			logger.warn("No se han recuperado bien las estadisticas del bloque de licencias:" + ex.getMessage());
		}
		
		Calendar fechaDesdeOdes = Calendar.getInstance();
		Calendar fechaHastaODes = Calendar.getInstance();
		
		checkFechas(form.getFechaDesdeOdes(), form.getFechaHastaOdes(), fechaDesdeOdes, fechaHastaODes);
		
		try {
			ResultadosEstadisticasOdesVO resultadosEstadisticasOdesVO = SrvBuscarService.obtenerEstadisticasOdesPorFechas(fechaDesdeOdes, fechaHastaODes);
			form.setNumLocalCursos(resultadosEstadisticasOdesVO.getNumLocalCursos());
			form.setNumTotalCursos(resultadosEstadisticasOdesVO.getNumTotalCursos());
			form.setComunidadesCursos(resultadosEstadisticasOdesVO.getComunidadesCursos());
			form.setComunidadesCursosValores(resultadosEstadisticasOdesVO.getComunidadesCursosValores());
			
			form.setNumLocalMediosIntegrados(resultadosEstadisticasOdesVO.getNumLocalMediosIntegrados());
			form.setNumTotalMediosIntegrados(resultadosEstadisticasOdesVO.getNumTotalMediosIntegrados());
			form.setComunidadesMediosIntegrados(resultadosEstadisticasOdesVO.getComunidadesMediosIntegrados());
			form.setComunidadesMediosIntegradosValores(resultadosEstadisticasOdesVO.getComunidadesMediosIntegradosValores());
			
			form.setNumLocalObjetos(resultadosEstadisticasOdesVO.getNumLocalObjetos());
			form.setNumTotalObjetos(resultadosEstadisticasOdesVO.getNumTotalObjetos());
			form.setComunidadesObjetos(resultadosEstadisticasOdesVO.getComunidadesObjetos());
			form.setComunidadesObjetosValores(resultadosEstadisticasOdesVO.getComunidadesObjetosValores());
			
			form.setNumLocalObjetosAprendizaje(resultadosEstadisticasOdesVO.getNumLocalObjetosAprendizaje());
			form.setNumTotalObjetosAprendizaje(resultadosEstadisticasOdesVO.getNumTotalObjetosAprendizaje());
			form.setComunidadesObjAprendizaje(resultadosEstadisticasOdesVO.getComunidadesObjAprendizaje());
			form.setComunidadesObjAprendizajeValores(resultadosEstadisticasOdesVO.getComunidadesObjAprendizajeValores());
			
			form.setNumLocalSecuencias(resultadosEstadisticasOdesVO.getNumLocalSecuencias());
			form.setNumTotalSecuencias(resultadosEstadisticasOdesVO.getNumTotalSecuencias());
			form.setComunidadesSecuencias(resultadosEstadisticasOdesVO.getComunidadesSecuencias());
			form.setComunidadesSecuenciasValores(resultadosEstadisticasOdesVO.getComunidadesSecuenciasValores());
		} catch (Exception ex) {
			logger.warn("No se han recuperado bien las estadisticas del bloque de odes:" + ex.getMessage());
		}
		
		Calendar fechaDesdeTerminos = Calendar.getInstance();
		Calendar fechaHastaTerminos = Calendar.getInstance();
		
		checkFechas(form.getFechaDesdeTerminos(), form.getFechaHastaTerminos(), fechaDesdeTerminos, fechaHastaTerminos);
		
		try {
			ResultadosEstadisticasTerminosVO resultadosEstadisticasTerminosVO = SrvBuscarService.obtenerEstadisticasTerminosPorFechas(fechaDesdeTerminos, fechaHastaTerminos);
			form.setTerminosMasBuscadosLocales(resultadosEstadisticasTerminosVO.getTerminosMasBuscadosLocales());
			form.setTerminosMasBuscadosNumLocales(resultadosEstadisticasTerminosVO.getTerminosMasBuscadosNumLocales());
			
			form.setTerminosMasBuscadosTotales(resultadosEstadisticasTerminosVO.getTerminosMasBuscadosTotales());
			form.setTerminosMasBuscadosNumTotales(resultadosEstadisticasTerminosVO.getTerminosMasBuscadosNumTotales());
			
			form.setComunidadesMasBuscadoTotalPrimero(resultadosEstadisticasTerminosVO.getComunidadesMasBuscadoTotalPrimero());
			form.setComunidadesMasBuscadoTotalSegundo(resultadosEstadisticasTerminosVO.getComunidadesMasBuscadoTotalSegundo());
			form.setComunidadesMasBuscadoTotalTercero(resultadosEstadisticasTerminosVO.getComunidadesMasBuscadoTotalTercero());
			form.setComunidadesMasBuscadoTotalCuarto(resultadosEstadisticasTerminosVO.getComunidadesMasBuscadoTotalCuarto());
			form.setComunidadesMasBuscadoTotalQuinto(resultadosEstadisticasTerminosVO.getComunidadesMasBuscadoTotalQuinto());
			
			form.setComunidadesMasBuscadoTotalPrimeroValores(resultadosEstadisticasTerminosVO.getComunidadesMasBuscadoTotalPrimeroValores());
			form.setComunidadesMasBuscadoTotalSegundoValores(resultadosEstadisticasTerminosVO.getComunidadesMasBuscadoTotalSegundoValores());
			form.setComunidadesMasBuscadoTotalTerceroValores(resultadosEstadisticasTerminosVO.getComunidadesMasBuscadoTotalTerceroValores());
			form.setComunidadesMasBuscadoTotalCuartoValores(resultadosEstadisticasTerminosVO.getComunidadesMasBuscadoTotalCuartoValores());
			form.setComunidadesMasBuscadoTotalQuintoValores(resultadosEstadisticasTerminosVO.getComunidadesMasBuscadoTotalQuintoValores());
			
		} catch (Exception ex) {
			logger.warn("No se han recuperado bien las estadisticas del bloque de termoinos mas buscados:" + ex.getMessage());
		}
	}

	public void descargarExcelEstadisticas(org.apache.struts.action.ActionMapping mapping, es.pode.administracion.presentacion.estadisticas.DescargarExcelEstadisticasForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws java.lang.Exception {

		try
		{
		logger.info("descargarExcelEstadisticas inicio");
		// EJFENTE
		SrvBuscarService srvBuscarService = this.getSrvBuscarService();
					
		String pathFicheroGenerado = srvBuscarService.obtenerFicheroExcelEstadisticasPorFechas(form.getFechaHastaTerminos(),form.getFechaHastaOdes(),form.getFechaHastaLicencias(),form.getFechaHastaCoberturaCurricular(),form.getFechaHastaActividad(),form.getFechaDesdeTerminos(),form.getFechaDesdeOdes(),form.getFechaDesdeLicencias(),form.getFechaDesdeCoberturaCurricular(),form.getFechaDesdeActividad(),form.getBloqueEstadistica());
		


		
		if (logger.isDebugEnabled()) logger.debug("descargarEstadisticas obtiene el fic : " + pathFicheroGenerado);
		
	    response.setContentType("application/xls");	
	 	response.setHeader("Content-Disposition", "attachment;filename=Estadisticas.pdf");
	 	response.setHeader("Cache-Control", "public");	
	 	response.addDateHeader("Expires", System.currentTimeMillis()+(24*60*60*1000));
	 	response.sendRedirect("http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+pathFicheroGenerado);
		
		}catch (Exception e) {
			logger.error("descargarExcelEstadisticas error al intentar descargar excel de estadísticas: " +e.getMessage() );
			throw e;
		}
	
	}
	
	public java.lang.String analizarPulsacion(org.apache.struts.action.ActionMapping mapping, es.pode.administracion.presentacion.estadisticas.AnalizarPulsacionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws java.lang.Exception {
		
		ResourceBundle datosResources = I18n.getInstance().getResource(APPLICATION_PROPERTIES, (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));

		String pulsacion = form.getPulsacion();
		
		if (datosResources.getString("estadisticas.estadisticas.descargar.excel.estadisticas").equals(pulsacion))
			return ACCION_DESCARGAR_ESTADISTICAS;	
		else
			return ACCION_CAMBIAR_FECHAS;
	}
	
	private void checkFechas (String formDesde, String formHasta, Calendar fechaDesde, Calendar fechaHasta) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");	
		
		if (formDesde == null || formDesde.equals("")
			|| formHasta == null || formHasta.equals("")) {
			Date desde;
			try {
				desde = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2007");
			} catch (ParseException e) {
				desde = new Date(0);
			}
			Date hasta = new Date();
			fechaDesde.setTime(desde);
			fechaHasta.setTime(hasta);
		} else {
			try {
				Date desde = sdf.parse(formDesde);
				Date hasta = sdf.parse(formHasta);
				fechaDesde.setTime(desde);
				fechaHasta.setTime(hasta);
				
				// Si la fecha desde es mayor que la fecha hasta lanzamos excepcion
				if (fechaDesde.compareTo(fechaHasta) > 0) {
//					try {
//						desde = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2007");
//					} catch (ParseException e) {
//						desde = new Date(0);
//					}
//					hasta = new Date();
//					fechaDesde.setTime(desde);
//					fechaHasta.setTime(hasta);
					throw new ValidatorException("{estadisticas.error.fechas.incorrectas}");
				}
					
			} catch (ParseException ex) {
				throw new ValidatorException("{estadisticas.error.fechas.incorrectas}");
//				Date desde = new Date(0);
//				Date hasta = new Date();
//				fechaDesde.setTime(desde);
//				fechaHasta.setTime(hasta);
			}
		}
	}		
}