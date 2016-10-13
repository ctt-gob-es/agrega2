<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="/WEB-INF/tags/googleAnalytic.tld" prefix="analytic"%>
<%@ taglib prefix="agrega" uri="/WEB-INF/tags/agregaProperties.tld"%>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite"%>
<%@ include file="/taglib-imports.jspf"%>


<tiles:insert definition="layout-usuario">
	<tiles:put name="body" type="string">
		<!--*************************        Inicio plantilla contenido      ********************************* -->
		<div class="plantilla_contenido" id="po_obj">
			<h2><bean:message key="estadisticasPortada.titulo" /></h2>
			<form method="post" action="">
				<!--  INICIO GLOBO GRIS   --><!--  INICIO GLOBO GRIS   -->
				<div class="globo_gris">
					<div class="globo_gris_01">
						<div class="globo_gris_02">
							<div class="globo_gris_03">
								<!-- INICIO CAJA DE FORMULARIO -->
								<div id="formulario" style="position:relative" >
									
									<!--  ODES  -->
									<div class="globo_blanco gb_ie">
										<div class="globo_blanco_01">
											<div class="globo_blanco_02">
												<div class="globo_blanco_03">
													
													<h3 class="clearfix theader">
														<em>ODES POR NIVEL DE AGREGACIÓN</em>
														<strong>Local</strong>
														<strong>Total</strong>
													</h3>

													<!-- INICIO GLOBO GRIS --><!-- INICIO GLOBO GRIS -->
													<div class="globo_gris_bis conmargen02" style="margin-bottom: 0;">
														<div class="globo_gris_01_bis">
															<div class="globo_gris_02_bis">
																<div class="globo_gris_03_bis">
																<table border="0" class="res_list_inv listado_estadisticas" cellpadding="0" cellspacing="0" summary="Medios Integrados">
																	<tr class="tr_blanco">
																		<td valign="top" class="dere00"><span><bean:message key="estadisticasPortada.medios" />:</span></td>
																		<td valign="top" class="alineado_izq objetos"><span><fmt:formatNumber type="number" value="${form.numLocalMediosIntegrados}" /></span></td>
																		<td class="alineado_izq objetos" valign="top"><span><fmt:formatNumber type="number" value="${form.numTotalMediosIntegrados}" /></span></td>
																	</tr>
																</table>
																</div>
															</div>
														</div>
													</div>
													<!-- FIN GLOBO GRIS --><!-- FIN GLOBO GRIS -->
													
													<!--  INICIO GLOBO GRIS   --><!-- INICIO GLOBO GRIS -->
													<div class="globo_gris_bis conmargen02" style="margin-bottom: 0;">
														<div class="globo_gris_01_bis">
															<div class="globo_gris_02_bis">
																<div class="globo_gris_03_bis">
																	<table border="0" class="res_list_inv listado_estadisticas" cellpadding="0" cellspacing="0" summary="Objetos Aprendizaje">
																		<tr class="tr_blanco">
																			<td valign="top" class="dere00"><span><bean:message key="estadisticasPortada.objetosAprendizaje" />:</span></td>
																			<td valign="top" class="alineado_izq objetos"><span><fmt:formatNumber type="number" value="${form.numLocalObjetosAprendizaje}" /></span></td>
																			<td class="alineado_izq objetos" valign="top"><span><fmt:formatNumber type="number" value="${form.numTotalObjetosAprendizaje}" /></span></td>
																		</tr>
																	</table>
																</div>
															</div>
														</div>
													</div>
													<!-- FIN GLOBO GRIS --><!-- FIN GLOBO GRIS --> 													
													
													<!-- INICIO GLOBO GRIS --><!-- INICIO GLOBO GRIS -->
													<div class="globo_gris_bis conmargen02" style="margin-bottom: 0;">
														<div class="globo_gris_01_bis">
															<div class="globo_gris_02_bis">
																<div class="globo_gris_03_bis">
																	<table border="0" class="res_list_inv listado_estadisticas" cellpadding="0" cellspacing="0" summary="Secuencias Didácticas">
																		<tr class="tr_blanco">
																			<td valign="top" class="dere00"><span><bean:message key="estadisticasPortada.secuencias" />:</span></td>
																			<td valign="top" class="alineado_izq objetos"><span><fmt:formatNumber type="number" value="${form.numLocalSecuencias}" /></span></td>
																			<td class="alineado_izq objetos" valign="top"><span><fmt:formatNumber type="number" value="${form.numTotalSecuencias}" /></span></td>
																		</tr>
																	</table>
																</div>
															</div>
														</div>
													</div>
													<!-- FIN GLOBO GRIS --><!-- FIN GLOBO GRIS --> 
													
													<!-- INICIO GLOBO GRIS   --><!-- INICIO GLOBO GRIS -->
													<div class="globo_gris_bis conmargen02" style="margin-bottom: 0;">
														<div class="globo_gris_01_bis">
															<div class="globo_gris_02_bis">
																<div class="globo_gris_03_bis">
																	<table border="0" class="res_list_inv listado_estadisticas" cellpadding="0" cellspacing="0" summary="Cursos">
																		<tr class="tr_blanco">
																			<td valign="top" class="dere00"><span><bean:message key="estadisticasPortada.cursos" />:</span></td>
																			<td valign="top" class="alineado_izq objetos"><span><fmt:formatNumber type="number" value="${form.numLocalCursos}" /></span></td>
																			<td class="alineado_izq objetos" valign="top"><span><fmt:formatNumber type="number" value="${form.numTotalCursos}" /></span></td>
																		</tr>
																	</table>
																</div>
															</div>
														</div>
													</div>
													<!-- FIN GLOBO GRIS   --><!--  FIN GLOBO GRIS --> 
																										
													<!-- INICIO GLOBO GRIS --> <!-- INICIO GLOBO GRIS -->
													<div class="globo_gris_bis conmargen02" style="margin-bottom: 0;">
														<div class="globo_gris_01_bis">
															<div class="globo_gris_02_bis">
																<div class="globo_gris_03_bis">
																	<table border="0" class="res_list_inv listado_estadisticas" cellpadding="0" cellspacing="0" summary="Objetos">
																		<tr class="tr_blanco">
																			<td valign="top" class="dere00"><span><bean:message key="estadisticasPortada.objetos" />:</span></td>
																			<td valign="top" class="alineado_izq objetos"><span><fmt:formatNumber type="number" value="${form.numLocalObjetos}" /></span></td>
																			<td class="alineado_izq objetos" valign="top"><span><fmt:formatNumber type="number" value="${form.numTotalObjetos}" /></span></td>
																		</tr>
																	</table>
																</div>
															</div>
														</div>
													</div>
													<!-- FIN GLOBO GRIS --><!-- FIN GLOBO GRIS --> 													
													
													<br>
													<a href="#" class="poppup01"><bean:message key="estadisticasPortada.mostrarDatosDesglosadosPorComunidades" /></a>
													
													<div class="oculto">
														<div class="thumb_list" id="pop_up_01">			
															<a class="close close_x sprited" href="#">close</a>
															<div class="showhide clearfix ">								
																<div class="escroll"> 	
																	<h3 class="h3_generico">Estadísticas de ODES POR NIVEL DE AGREGACIÓN desglosadas por comunidades</h3>					
																	<div class="caja_tabla" id="bordeada" >		
																		<table border="1" class="generica tabla_rss_estadisticas" width="100%" cellpadding="0" cellspacing="0"  summary="Tabla de Resultados">						
																			<c:forEach items="${form.comunidadesBusquedas}" var="comunidad" varStatus="status">
																				<col class="col_01">
																			</c:forEach>
																			<tr>
																				<th></th>
																				<c:forEach items="${form.comunidadesBusquedas}" var="comunidad" varStatus="status">
																					<th>${comunidad}</th>
																				</c:forEach>
																			</tr>
																			<tr class="tr_gris">
																				<td class="al_izq"><bean:message key="estadisticasPortada.medios" /></td>
																				<c:forEach items="${form.comunidadesMediosIntegradosValores}" var="valor" varStatus="status">
																					<td><fmt:formatNumber type="number" value="${valor}" /></td>
																				</c:forEach>
																			</tr>		
																			<tr class="tr_blanco">
																				<td class="al_izq"><bean:message key="estadisticasPortada.objetosAprendizaje" /></td>
																				<c:forEach items="${form.comunidadesObjAprendizajeValores}" var="valor" varStatus="status">
																					<td><fmt:formatNumber type="number" value="${valor}" /></td>
																				</c:forEach>
																			</tr>
																			<tr class="tr_gris">
																				<td class="al_izq"><bean:message key="estadisticasPortada.secuencias" /></td>
																				<c:forEach items="${form.comunidadesSecuenciasValores}" var="valor" varStatus="status">
																					<td><fmt:formatNumber type="number" value="${valor}" /></td>
																				</c:forEach>
																			</tr>																			
																			<tr class="tr_blanco">
																				<td class="al_izq"><bean:message key="estadisticasPortada.cursos" /></td>
																				<c:forEach items="${form.comunidadesCursosValores}" var="valor" varStatus="status">
																					<td><fmt:formatNumber type="number" value="${valor}" /></td>
																				</c:forEach>
																			</tr>	
																			<tr class="tr_gris">
																				<td class="al_izq"><bean:message key="estadisticasPortada.objetos" /></td>
																				<c:forEach items="${form.comunidadesObjetosValores}" var="valor" varStatus="status">
																					<td><fmt:formatNumber type="number" value="${valor}" /></td>
																				</c:forEach>
																			</tr>
																		</table>
																		<h4><p><bean:message key="estadisticasPortada.leyendaDatosNoActualizados" /></p></h4>
																	</div>
																</div>
															</div> 
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>

									<!--  ACTIVIDAD  -->
									<div class="globo_blanco gb_ie">
										<div class="globo_blanco_01">
											<div class="globo_blanco_02">
												<div class="globo_blanco_03">
													
													<h3 class="clearfix theader">
														<em><bean:message key="estadisticasPortada.actividad" /></em>
														<strong>Local</strong>
														<strong>Total</strong>
													</h3>
													
													<!-- INICIO GLOBO GRIS --> <!-- INICIO GLOBO GRIS -->
													<div class="globo_gris_bis conmargen02" style="margin-bottom: 0;">
														<div class="globo_gris_01_bis">
															<div class="globo_gris_02_bis">
																<div class="globo_gris_03_bis">
																	<table border="0" class="res_list_inv listado_estadisticas" cellpadding="0" cellspacing="0" summary="Busquedas">
																		<tr class="tr_blanco">
																			<td valign="top" class="dere00"><span><bean:message key="estadisticasPortada.busquedas" />:</span></td>
																			<td valign="top" class="alineado_izq objetos"><span><fmt:formatNumber type="number" value="${form.busquedasLocales}" /></span></td>
																			<td class="alineado_izq objetos" valign="top"><span><fmt:formatNumber type="number" value="${form.busquedasTotales}" /></span></td>
																		</tr>
																	</table>
																</div>
															</div>
														</div>
													</div>
													<!-- FIN GLOBO GRIS --><!-- FIN GLOBO GRIS --> 
													
													<!-- INICIO GLOBO GRIS   --><!-- INICIO GLOBO GRIS -->
													<div class="globo_gris_bis conmargen02" style="margin-bottom: 0;">
														<div class="globo_gris_01_bis">
															<div class="globo_gris_02_bis">
																<div class="globo_gris_03_bis">
																	<table border="0" class="res_list_inv listado_estadisticas" cellpadding="0" cellspacing="0" summary="Descargas">
																		<tr class="tr_blanco">
																			<td valign="top" class="dere00"><span><bean:message key="estadisticasPortada.descargas" />:</span></td>
																			<td valign="top" class="alineado_izq objetos"><span><fmt:formatNumber type="number" value="${form.descargas}" /></span></td>
																			<td class="alineado_izq objetos" valign="top"><span><fmt:formatNumber type="number" value="${form.descargasTotales}" /></span></td>
																		</tr>
																	</table>
																</div>
															</div>
														</div>
													</div>
													<!-- FIN GLOBO GRIS   --><!--  FIN GLOBO GRIS --> 
													
													<!-- INICIO GLOBO GRIS --><!-- INICIO GLOBO GRIS -->
													<div class="globo_gris_bis conmargen02" style="margin-bottom: 0;">
														<div class="globo_gris_01_bis">
															<div class="globo_gris_02_bis">
																<div class="globo_gris_03_bis">
																	<table border="0" class="res_list_inv listado_estadisticas" cellpadding="0" cellspacing="0" summary="Fichas Accedidas">
																		<tr class="tr_blanco">
																			<td valign="top" class="dere00"><span><bean:message key="estadisticasPortada.fichasAccedidas" />:</span></td>
																			<td valign="top" class="alineado_izq objetos"><span><fmt:formatNumber type="number" value="${form.fichasAccedidasLocales}" /></span></td>
																			<td class="alineado_izq objetos" valign="top"><span><fmt:formatNumber type="number" value="${form.fichasAccedidasTotales}" /></span></td>
																		</tr>
																	</table>
																</div>
															</div>
														</div>
													</div>
													<!-- FIN GLOBO GRIS --><!-- FIN GLOBO GRIS --> 
													
													<!--  INICIO GLOBO GRIS   --><!-- INICIO GLOBO GRIS -->
													<div class="globo_gris_bis conmargen02" style="margin-bottom: 0;">
														<div class="globo_gris_01_bis">
															<div class="globo_gris_02_bis">
																<div class="globo_gris_03_bis">
																	<table border="0" class="res_list_inv listado_estadisticas" cellpadding="0" cellspacing="0" summary="ODES Previsualizados">
																		<tr class="tr_blanco">
																			<td valign="top" class="dere00"><span><bean:message key="estadisticasPortada.odesPrevisualizados" />:</span></td>
																			<td valign="top" class="alineado_izq objetos"><span><fmt:formatNumber type="number" value="${form.odesPrevisualizadosLocales}" /></span></td>
																			<td class="alineado_izq objetos" valign="top"><span><fmt:formatNumber type="number" value="${form.odesPrevisualizadosTotales}" /></span></td>
																		</tr>
																	</table>
																</div>
															</div>
														</div>
													</div>
													<!-- FIN GLOBO GRIS --><!-- FIN GLOBO GRIS -->
													
													<br>
													<a href="#" class="poppup02"><bean:message key="estadisticasPortada.mostrarDatosDesglosadosPorComunidades" /></a>
													
													<div class="oculto">
														<div class="thumb_list" id="pop_up_02">			
															<a class="close close_x sprited" href="#">close</a>
															<div class="showhide clearfix ">								
																<div class="escroll"> 	
																	<h4><p>Estadísticas de ACTIVIDAD desglosadas por comunidades</p>	</h4>					
																	<div class="caja_tabla" id="bordeada" >		
																		<table border="1" class="generica tabla_rss_estadisticas" width="100%" cellpadding="0" cellspacing="0"  summary="Tabla de Resultados">						
																			<c:forEach items="${form.comunidadesBusquedas}" var="comunidad" varStatus="status">
																				<col class="col_01">
																			</c:forEach>
																			<tr>
																				<th></th>
																				<c:forEach items="${form.comunidadesBusquedas}" var="comunidad" varStatus="status">
																					<th>${comunidad}</th>
																				</c:forEach>
																				<th>Total</th>
																			</tr>
																			<tr class="tr_gris">
																				<td class="al_izq"><bean:message key="estadisticasPortada.busquedas" /></td>
																				<c:forEach items="${form.comunidadesBusquedasValores}" var="valor" varStatus="status">
																					<td><fmt:formatNumber type="number" value="${valor}" /></td>
																				</c:forEach>
																				<td class="al_der"><fmt:formatNumber type="number" value="${form.busquedasTotales}" /></td>
																			</tr>	
																			<tr class="tr_blanco">
																				<td class="al_izq"><bean:message key="estadisticasPortada.descargas" /></td>
																				<c:forEach items="${form.comunidadesDescargasValores}" var="valor" varStatus="status">
																					<td><fmt:formatNumber type="number" value="${valor}" /></td>
																				</c:forEach>
																				<td class="al_der"><fmt:formatNumber type="number" value="${form.descargasTotales}" /></td>
																			</tr>
																			<tr class="tr_gris">
																				<td class="al_izq"><bean:message key="estadisticasPortada.fichasAccedidas" /></td>
																				<c:forEach items="${form.comunidadesFichasAccedidasValores}" var="valor" varStatus="status">
																					<td><fmt:formatNumber type="number" value="${valor}" /></td>
																				</c:forEach>
																				<td class="al_der"><fmt:formatNumber type="number" value="${form.fichasAccedidasTotales}" /></td>
																			</tr>
																			<tr class="tr_blanco">
																				<td class="al_izq"><bean:message key="estadisticasPortada.odesPrevisualizados" /></td>
																				<c:forEach items="${form.comunidadesOdesPrevisualizadosValores}" var="valor" varStatus="status">
																					<td><fmt:formatNumber type="number" value="${valor}" /></td>
																				</c:forEach>
																				<td class="al_der"><fmt:formatNumber type="number" value="${form.odesPrevisualizadosTotales}" /></td>
																			</tr>
																			<tr class="tr_gris">
																				<td class="al_izq"><strong>TOTAL</strong></td>
																				<c:forEach items="${form.comunidadesOdesPrevisualizadosValores}" var="valor" varStatus="status">
																					<c:set var="totalCCAA" value="${form.comunidadesBusquedasValores[status.index] +form.comunidadesDescargasValores[status.index] + form.comunidadesFichasAccedidasValores[status.index] +form.comunidadesOdesPrevisualizadosValores[status.index]}" />												
																					<td class="al_der"><fmt:formatNumber type="number" value="${totalCCAA}"/></td>
																				</c:forEach>
																				<c:set var="totalActividadCCAA" value="${form.busquedasTotales +form.descargasTotales +form.fichasAccedidasTotales +form.odesPrevisualizadosTotales}" />												
																				<td class="al_der"><fmt:formatNumber type="number" value="${totalActividadCCAA}"/></td>												
																			</tr>																																																																													
																		</table>
																		<h4><p><bean:message key="estadisticasPortada.leyendaDatosNoActualizados" /></p></h4>
																		<br/>
																	</div>
																</div>
															</div> 
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>

									<!--  TERMINOS TOTALES  -->
									<div class="globo_blanco gb_ie">
										<div class="globo_blanco_01">
											<div class="globo_blanco_02">
												<div class="globo_blanco_03">
																					
													<h3><bean:message key="estadisticasPortada.terminosMasBuscados" /></h3>
													<h3 class="clearfix theader">
														<em>&nbsp;&nbsp;&nbsp;&nbsp;</em>
														<strong>Local</strong>
														<strong>Total</strong>
													</h3>													
													
													<!-- INICIO GLOBO GRIS --> <!-- INICIO GLOBO GRIS -->
													<div class="globo_gris_bis conmargen02" style="margin-bottom: 0;">
														<div class="globo_gris_01_bis">
															<div class="globo_gris_02_bis">
																<div class="globo_gris_03_bis">
																	<table border="0" class="res_list_inv listado_estadisticas" cellpadding="0" cellspacing="0" summary="Primer Mas Buscado">
																		<tr class="tr_blanco">
																			<td valign="top" class="dere00"><span>${form.terminosMasBuscadosTotales[0]}:</span></td>
																			<td valign="top" class="alineado_izq objetos"><span><fmt:formatNumber type="number" value="${form.terminosMasBuscadosNumLocales[0]}" /></span></td>
																			<td class="alineado_izq objetos" valign="top"><span><fmt:formatNumber type="number" value="${form.terminosMasBuscadosNumTotales[0]}" /></span></td>
																		</tr>
																	</table>
																</div>
															</div>
														</div>
													</div>
													<!-- FIN GLOBO GRIS --><!-- FIN GLOBO GRIS --> 
													
													<!-- INICIO GLOBO GRIS   --><!-- INICIO GLOBO GRIS -->
													<div class="globo_gris_bis conmargen02" style="margin-bottom: 0;">
														<div class="globo_gris_01_bis">
															<div class="globo_gris_02_bis">
																<div class="globo_gris_03_bis">
																	<table border="0" class="res_list_inv listado_estadisticas" cellpadding="0" cellspacing="0" summary="Segundo Mas Buscado">
																		<tr class="tr_blanco">
																			<td valign="top" class="dere00"><span>${form.terminosMasBuscadosTotales[1]}:</span></td>
																			<td valign="top" class="alineado_izq objetos"><span><fmt:formatNumber type="number" value="${form.terminosMasBuscadosNumLocales[1]}" /></span></td>
																			<td class="alineado_izq objetos" valign="top"><span><fmt:formatNumber type="number" value="${form.terminosMasBuscadosNumTotales[1]}" /></span></td>
																		</tr>
																	</table>
																</div>
															</div>
														</div>
													</div>
													<!-- FIN GLOBO GRIS   --><!--  FIN GLOBO GRIS --> 
													
													<!-- INICIO GLOBO GRIS --><!-- INICIO GLOBO GRIS -->
													<div class="globo_gris_bis conmargen02" style="margin-bottom: 0;">
														<div class="globo_gris_01_bis">
															<div class="globo_gris_02_bis">
																<div class="globo_gris_03_bis">
																	<table border="0" class="res_list_inv listado_estadisticas" cellpadding="0" cellspacing="0" summary="Tercero Mas Buscado">
																		<tr class="tr_blanco">
																			<td valign="top" class="dere00"><span>${form.terminosMasBuscadosTotales[2]}:</span></td>
																			<td valign="top" class="alineado_izq objetos"><span><fmt:formatNumber type="number" value="${form.terminosMasBuscadosNumLocales[2]}" /></span></td>
																			<td class="alineado_izq objetos" valign="top"><span><fmt:formatNumber type="number" value="${form.terminosMasBuscadosNumTotales[2]}" /></span></td>
																		</tr>
																	</table>
																</div>
															</div>
														</div>
													</div>
													<!-- FIN GLOBO GRIS --><!-- FIN GLOBO GRIS --> 
													
													<!--  INICIO GLOBO GRIS   --><!-- INICIO GLOBO GRIS -->
													<div class="globo_gris_bis conmargen02" style="margin-bottom: 0;">
														<div class="globo_gris_01_bis">
															<div class="globo_gris_02_bis">
																<div class="globo_gris_03_bis">
																	<table border="0" class="res_list_inv listado_estadisticas" cellpadding="0" cellspacing="0" summary="Cuarto Mas Buscado">
																		<tr class="tr_blanco">
																			<td valign="top" class="dere00"><span>${form.terminosMasBuscadosTotales[3]}:</span></td>
																			<td valign="top" class="alineado_izq objetos"><span><fmt:formatNumber type="number" value="${form.terminosMasBuscadosNumLocales[3]}" /></span></td>
																			<td class="alineado_izq objetos" valign="top"><span><fmt:formatNumber type="number" value="${form.terminosMasBuscadosNumTotales[3]}" /></span></td>
																		</tr>
																	</table>
																</div>
															</div>
														</div>
													</div>
													<!-- FIN GLOBO GRIS --><!-- FIN GLOBO GRIS --> 
													
													<!--  INICIO GLOBO GRIS   --><!-- INICIO GLOBO GRIS -->
													<div class="globo_gris_bis conmargen02" style="margin-bottom: 0;">
														<div class="globo_gris_01_bis">
															<div class="globo_gris_02_bis">
																<div class="globo_gris_03_bis">
																	<table border="0" class="res_list_inv listado_estadisticas" cellpadding="0" cellspacing="0" summary="Quinto Mas Buscado">
																		<tr class="tr_blanco">
																			<td valign="top" class="dere00"><span>${form.terminosMasBuscadosTotales[4]}:</span></td>
																			<td valign="top" class="alineado_izq objetos"><span><fmt:formatNumber type="number" value="${form.terminosMasBuscadosNumLocales[4]}" /></span></td>
																			<td class="alineado_izq objetos" valign="top"><span><fmt:formatNumber type="number" value="${form.terminosMasBuscadosNumTotales[4]}" /></span></td>
																		</tr>
																	</table>
																</div>
															</div>
														</div>
													</div>
													<!-- FIN GLOBO GRIS --><!-- FIN GLOBO GRIS -->
													
													<br>
													<a href="#" class="poppup03"><bean:message key="estadisticasPortada.mostrarDatosDesglosadosPorComunidades" /></a>
													
													<div class="oculto">
														<div class="thumb_list" id="pop_up_03">			
															<a class="close close_x sprited" href="#">close</a>
															<div class="showhide clearfix ">								
																<div class="escroll"> 	
																	<h4><p>Estadísticas de TÉRMINOS MÁS BUSCADOS desglosadas por comunidades</p>	</h4>					
																	<div class="caja_tabla" id="bordeada" >		
																		<table border="1" class="generica tabla_rss_estadisticas" width="100%" cellpadding="0" cellspacing="0"  summary="Tabla de Resultados">					
																			<c:forEach items="${form.comunidadesBusquedas}" var="comunidad" varStatus="status">
																				<col class="col_01">
																			</c:forEach>
																			<tr>
																				<th></th>
																				<c:forEach items="${form.comunidadesMasBuscadoTotalPrimero}" var="comunidad" varStatus="status">
																					<th class="al_der">${comunidad}</th>
																				</c:forEach>
																				<th>Total</th>
																			</tr>
																			<tr class="tr_gris">
																				<td class="al_izq">${form.terminosMasBuscadosTotales[0]}</td>
																				<c:forEach items="${form.comunidadesMasBuscadoTotalPrimeroValores}" var="valor" varStatus="status">
																					<td><fmt:formatNumber type="number" value="${valor}" /></td>
																				</c:forEach>
																				<td class="al_der"><fmt:formatNumber type="number" value="${form.terminosMasBuscadosNumTotales[0]}" /></td>
																			</tr>	
																			<tr class="tr_blanco">
																				<td class="al_izq">${form.terminosMasBuscadosTotales[1]}</td>
																				<c:forEach items="${form.comunidadesMasBuscadoTotalSegundoValores}" var="valor" varStatus="status">
																					<td><fmt:formatNumber type="number" value="${valor}" /></td>
																				</c:forEach>
																				<td class="al_der"><fmt:formatNumber type="number" value="${form.terminosMasBuscadosNumTotales[1]}" /></td>
																			</tr>
																			<tr class="tr_gris">
																				<td class="al_izq">${form.terminosMasBuscadosTotales[2]}</td>
																				<c:forEach items="${form.comunidadesMasBuscadoTotalTerceroValores}" var="valor" varStatus="status">
																					<td><fmt:formatNumber type="number" value="${valor}" /></td>
																				</c:forEach>
																				<td class="al_der"><fmt:formatNumber type="number" value="${form.terminosMasBuscadosNumTotales[2]}" /></td>
																			</tr>
																			<tr class="tr_blanco">
																				<td class="al_izq">${form.terminosMasBuscadosTotales[3]}</td>
																				<c:forEach items="${form.comunidadesMasBuscadoTotalCuartoValores}" var="valor" varStatus="status">
																					<td><fmt:formatNumber type="number" value="${valor}" /></td>
																				</c:forEach>
																				<td class="al_der"><fmt:formatNumber type="number" value="${form.terminosMasBuscadosNumTotales[3]}" /></td>
																			</tr>
																			<tr class="tr_gris">
																				<td class="al_izq">${form.terminosMasBuscadosTotales[4]}</td>
																				<c:forEach items="${form.comunidadesMasBuscadoTotalQuintoValores}" var="valor" varStatus="status">
																					<td><fmt:formatNumber type="number" value="${valor}" /></td>
																				</c:forEach>
																				<td class="al_der"><fmt:formatNumber type="number" value="${form.terminosMasBuscadosNumTotales[4]}" /></td>
																			</tr>
																			<tr class="tr_blanco">
																				<td class="al_izq">TOTAL</td>
																				<c:forEach items="${form.comunidadesMasBuscadoTotalPrimero}" var="valor" varStatus="status">
																					<c:set var="totalCCAA" value="${form.comunidadesMasBuscadoTotalPrimeroValores[status.index] +form.comunidadesMasBuscadoTotalSegundoValores[status.index] + form.comunidadesMasBuscadoTotalTerceroValores[status.index] +form.comunidadesMasBuscadoTotalCuartoValores[status.index]+form.comunidadesMasBuscadoTotalQuintoValores[status.index]}" />												
																					<td class="al_der"><fmt:formatNumber type="number" value="${totalCCAA}"/></td>
																				</c:forEach>
																				<c:set var="totalActividadCCAA" value="${form.terminosMasBuscadosNumTotales[0] +form.terminosMasBuscadosNumTotales[1] +form.terminosMasBuscadosNumTotales[2] +form.terminosMasBuscadosNumTotales[3] +form.terminosMasBuscadosNumTotales[4]}" />												
																				<td class="al_der"><fmt:formatNumber type="number" value="${totalActividadCCAA}"/></td>												
																			</tr>																																																																																																																				
																		</table>
																		<h4><p><bean:message key="estadisticasPortada.leyendaDatosNoActualizados" /></p></h4>
																		<br/>
																	</div>
																</div>
															</div> 
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>

									<!--  TERMINOS AÑO  -->
									<div class="globo_blanco gb_ie">
										<div class="globo_blanco_01">
											<div class="globo_blanco_02">
												<div class="globo_blanco_03">
																										
													<h3><bean:message key="estadisticasPortada.terminosMasBuscadosAnio" /></h3>
													<h3 class="clearfix theader">
														<em>&nbsp;&nbsp;&nbsp;&nbsp;</em>
														<strong>Local</strong>
														<strong>Total</strong>
													</h3>
													
													<!-- INICIO GLOBO GRIS --> <!-- INICIO GLOBO GRIS -->
													<div class="globo_gris_bis conmargen02" style="margin-bottom: 0;">
														<div class="globo_gris_01_bis">
															<div class="globo_gris_02_bis">
																<div class="globo_gris_03_bis">
																	<table border="0" class="res_list_inv listado_estadisticas" cellpadding="0" cellspacing="0" summary="Primer Mas Buscado Anio">
																		<tr class="tr_blanco">
																			<td valign="top" class="dere00"><span>${form.terminosMasBuscadosAnoTotales[0]}:</span></td>
																			<td valign="top" class="alineado_izq objetos"><span><fmt:formatNumber type="number" value="${form.terminosMasBuscadosAnoNumLocales[0]}" /></span></td>
																			<td class="alineado_izq objetos" valign="top"><span><fmt:formatNumber type="number" value="${form.terminosMasBuscadosAnoNumTotales[0]}" /></span></td>
																		</tr>
																	</table>
																</div>
															</div>
														</div>
													</div>
													<!-- FIN GLOBO GRIS --><!-- FIN GLOBO GRIS --> 
													
													<!-- INICIO GLOBO GRIS   --><!-- INICIO GLOBO GRIS -->
													<div class="globo_gris_bis conmargen02" style="margin-bottom: 0;">
														<div class="globo_gris_01_bis">
															<div class="globo_gris_02_bis">
																<div class="globo_gris_03_bis">
																	<table border="0" class="res_list_inv listado_estadisticas" cellpadding="0" cellspacing="0" summary="Segundo Mas Buscado Anio">
																		<tr class="tr_blanco">
																			<td valign="top" class="dere00"><span>${form.terminosMasBuscadosAnoTotales[1]}:</span></td>
																			<td valign="top" class="alineado_izq objetos"><span><fmt:formatNumber type="number" value="${form.terminosMasBuscadosAnoNumLocales[1]}" /></span></td>
																			<td class="alineado_izq objetos" valign="top"><span><fmt:formatNumber type="number" value="${form.terminosMasBuscadosAnoNumTotales[1]}" /></span></td>
																		</tr>
																	</table>
																</div>
															</div>
														</div>
													</div>
													<!-- FIN GLOBO GRIS   --><!--  FIN GLOBO GRIS --> 
													
													<!-- INICIO GLOBO GRIS --><!-- INICIO GLOBO GRIS -->
													<div class="globo_gris_bis conmargen02" style="margin-bottom: 0;">
														<div class="globo_gris_01_bis">
															<div class="globo_gris_02_bis">
																<div class="globo_gris_03_bis">
																	<table border="0" class="res_list_inv listado_estadisticas" cellpadding="0" cellspacing="0" summary="Tercero Mas Buscado Anio">
																		<tr class="tr_blanco">
																			<td valign="top" class="dere00"><span>${form.terminosMasBuscadosAnoTotales[2]}:</span></td>
																			<td valign="top" class="alineado_izq objetos"><span><fmt:formatNumber type="number" value="${form.terminosMasBuscadosAnoNumLocales[2]}" /></span></td>
																			<td class="alineado_izq objetos" valign="top"><span><fmt:formatNumber type="number" value="${form.terminosMasBuscadosAnoNumTotales[2]}" /></span></td>
																		</tr>
																	</table>
																</div>
															</div>
														</div>
													</div>
													<!-- FIN GLOBO GRIS --><!-- FIN GLOBO GRIS --> 
													
													<!--  INICIO GLOBO GRIS   --><!-- INICIO GLOBO GRIS -->
													<div class="globo_gris_bis conmargen02" style="margin-bottom: 0;">
														<div class="globo_gris_01_bis">
															<div class="globo_gris_02_bis">
																<div class="globo_gris_03_bis">
																	<table border="0" class="res_list_inv listado_estadisticas" cellpadding="0" cellspacing="0" summary="Cuarto Mas Buscado Anio">
																		<tr class="tr_blanco">
																			<td valign="top" class="dere00"><span>${form.terminosMasBuscadosAnoTotales[3]}:</span></td>
																			<td valign="top" class="alineado_izq objetos"><span><fmt:formatNumber type="number" value="${form.terminosMasBuscadosAnoNumLocales[3]}" /></span></td>
																			<td class="alineado_izq objetos" valign="top"><span><fmt:formatNumber type="number" value="${form.terminosMasBuscadosAnoNumTotales[3]}" /></span></td>
																		</tr>
																	</table>
																</div>
															</div>
														</div>
													</div>
													<!-- FIN GLOBO GRIS --><!-- FIN GLOBO GRIS --> 
													
													<!--  INICIO GLOBO GRIS   --><!-- INICIO GLOBO GRIS -->
													<div class="globo_gris_bis conmargen02" style="margin-bottom: 0;">
														<div class="globo_gris_01_bis">
															<div class="globo_gris_02_bis">
																<div class="globo_gris_03_bis">
																	<table border="0" class="res_list_inv listado_estadisticas" cellpadding="0" cellspacing="0" summary="Quinto Mas Buscado Anio">
																		<tr class="tr_blanco">
																			<td valign="top" class="dere00"><span>${form.terminosMasBuscadosAnoTotales[4]}:</span></td>
																			<td valign="top" class="alineado_izq objetos"><span><fmt:formatNumber type="number" value="${form.terminosMasBuscadosAnoNumLocales[4]}" /></span></td>
																			<td class="alineado_izq objetos" valign="top"><span><fmt:formatNumber type="number" value="${form.terminosMasBuscadosAnoNumTotales[4]}" /></span></td>
																		</tr>
																	</table>
																</div>
															</div>
														</div>
													</div>
													<!-- FIN GLOBO GRIS --><!-- FIN GLOBO GRIS -->
													
													<br>
													<a href="#" class="poppup04"><bean:message key="estadisticasPortada.mostrarDatosDesglosadosPorComunidades" /></a>
													
													<div class="oculto">
														<div class="thumb_list" id="pop_up_04">			
															<a class="close close_x sprited" href="#">close</a>
															<div class="showhide clearfix ">								
																<div class="escroll"> 	
																	<h4><p>Estadísticas de TÉRMINOS MÁS BUSCADOS EN EL AÑO desglosadas por comunidades</p>	</h4>					
																	<div class="caja_tabla" id="bordeada" >		
																		<table border="1" class="generica tabla_rss_estadisticas" width="100%" cellpadding="0" cellspacing="0"  summary="Tabla de Resultados">						
																			<c:forEach items="${form.comunidadesBusquedas}" var="comunidad" varStatus="status">
																				<col class="col_01">
																			</c:forEach>
																			<tr>
																				<th></th>
																				<c:forEach items="${form.comunidadesMasBuscadoAnioPrimero}" var="comunidad" varStatus="status">
																					<th>${comunidad}</th>
																				</c:forEach>
																				<th>Total</th>
																			</tr>
																			<tr class="tr_gris">
																				<td class="al_izq">${form.terminosMasBuscadosAnoTotales[0]}</td>
																				<c:forEach items="${form.comunidadesMasBuscadoAnioPrimeroValores}" var="valor" varStatus="status">
																					<td><fmt:formatNumber type="number" value="${valor}" /></td>
																				</c:forEach>
																				<td class="al_der"><fmt:formatNumber type="number" value="${form.terminosMasBuscadosAnoNumTotales[0]}" /></td>
																			</tr>	
																			<tr class="tr_blanco">
																				<td class="al_izq">${form.terminosMasBuscadosAnoTotales[1]}</td>
																				<c:forEach items="${form.comunidadesMasBuscadoAnioSegundoValores}" var="valor" varStatus="status">
																					<td><fmt:formatNumber type="number" value="${valor}" /></td>
																				</c:forEach>
																				<td class="al_der"><fmt:formatNumber type="number" value="${form.terminosMasBuscadosAnoNumTotales[1]}" /></td>
																			</tr>
																			<tr class="tr_gris">
																				<td class="al_izq">${form.terminosMasBuscadosAnoTotales[2]}</td>
																				<c:forEach items="${form.comunidadesMasBuscadoAnioTerceroValores}" var="valor" varStatus="status">
																					<td><fmt:formatNumber type="number" value="${valor}" /></td>
																				</c:forEach>
																				<td class="al_der"><fmt:formatNumber type="number" value="${form.terminosMasBuscadosAnoNumTotales[2]}" /></td>
																			</tr>
																			<tr class="tr_blanco">
																				<td class="al_izq">${form.terminosMasBuscadosAnoTotales[3]}</td>
																				<c:forEach items="${form.comunidadesMasBuscadoAnioCuartoValores}" var="valor" varStatus="status">
																					<td><fmt:formatNumber type="number" value="${valor}" /></td>
																				</c:forEach>
																				<td class="al_der"><fmt:formatNumber type="number" value="${form.terminosMasBuscadosAnoNumTotales[3]}" /></td>
																			</tr>
																			<tr class="tr_gris">
																				<td class="al_izq">${form.terminosMasBuscadosAnoTotales[4]}</td>
																				<c:forEach items="${form.comunidadesMasBuscadoAnioQuintoValores}" var="valor" varStatus="status">
																					<td><fmt:formatNumber type="number" value="${valor}" /></td>
																				</c:forEach>
																				<td class="al_der"><fmt:formatNumber type="number" value="${form.terminosMasBuscadosAnoNumTotales[4]}" /></td>
																			</tr>	
																			<tr class="tr_blanco">
																				<td class="al_izq">TOTAL</td>
																				<c:forEach items="${form.comunidadesMasBuscadoAnioPrimero}" var="valor" varStatus="status">
																					<c:set var="totalCCAA" value="${form.comunidadesMasBuscadoAnioPrimeroValores[status.index] +form.comunidadesMasBuscadoAnioSegundoValores[status.index] + form.comunidadesMasBuscadoAnioTerceroValores[status.index] +form.comunidadesMasBuscadoAnioCuartoValores[status.index]+form.comunidadesMasBuscadoAnioQuintoValores[status.index]}" />												
																					<td class="al_der"><fmt:formatNumber type="number" value="${totalCCAA}"/></td>
																				</c:forEach>
																				<c:set var="totalActividadCCAA" value="${form.terminosMasBuscadosAnoNumTotales[0] +form.terminosMasBuscadosAnoNumTotales[1] +form.terminosMasBuscadosAnoNumTotales[2] +form.terminosMasBuscadosAnoNumTotales[3] +form.terminosMasBuscadosAnoNumTotales[4]}" />												
																				<td class="al_der"><fmt:formatNumber type="number" value="${totalActividadCCAA}"/></td>												
																			</tr>																																					
																		</table>
																		<h4><p><bean:message key="estadisticasPortada.leyendaDatosNoActualizados" /></p></h4>
																		<br/>
																	</div>
																</div>
															</div> 
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>

									<!--  TERMINOS MES  -->
									<div class="globo_blanco gb_ie">
										<div class="globo_blanco_01">
											<div class="globo_blanco_02">
												<div class="globo_blanco_03">
													
													<h3><bean:message key="estadisticasPortada.terminosMasBuscadosMes" /></h3>
													<h3 class="clearfix theader">
														<em>&nbsp;&nbsp;&nbsp;&nbsp;</em>
														<strong>Local</strong>
														<strong>Total</strong>
													</h3>
													
													<!-- INICIO GLOBO GRIS --> <!-- INICIO GLOBO GRIS -->
													<div class="globo_gris_bis conmargen02" style="margin-bottom: 0;">
														<div class="globo_gris_01_bis">
															<div class="globo_gris_02_bis">
																<div class="globo_gris_03_bis">
																	<table border="0" class="res_list_inv listado_estadisticas" cellpadding="0" cellspacing="0" summary="Primer Mas Buscado Mes">
																		<tr class="tr_blanco">
																			<td valign="top" class="dere00"><span>${form.terminosMasBuscadosMesTotales[0]}:</span></td>
																			<td valign="top" class="alineado_izq objetos"><span><fmt:formatNumber type="number" value="${form.terminosMasBuscadosMesNumLocales[0]}" /></span></td>
																			<td class="alineado_izq objetos" valign="top"><span><fmt:formatNumber type="number" value="${form.terminosMasBuscadosMesNumTotales[0]}" /></span></td>
																		</tr>
																	</table>
																</div>
															</div>
														</div>
													</div>
													<!-- FIN GLOBO GRIS --><!-- FIN GLOBO GRIS --> 
													
													<!-- INICIO GLOBO GRIS   --><!-- INICIO GLOBO GRIS -->
													<div class="globo_gris_bis conmargen02" style="margin-bottom: 0;">
														<div class="globo_gris_01_bis">
															<div class="globo_gris_02_bis">
																<div class="globo_gris_03_bis">
																	<table border="0" class="res_list_inv listado_estadisticas" cellpadding="0" cellspacing="0" summary="Segundo Mas Buscado Mes">
																		<tr class="tr_blanco">
																			<td valign="top" class="dere00"><span>${form.terminosMasBuscadosMesTotales[1]}:</span></td>
																			<td valign="top" class="alineado_izq objetos"><span><fmt:formatNumber type="number" value="${form.terminosMasBuscadosMesNumLocales[1]}" /></span></td>
																			<td class="alineado_izq objetos" valign="top"><span><fmt:formatNumber type="number" value="${form.terminosMasBuscadosMesNumTotales[1]}" /></span></td>
																		</tr>
																	</table>
																</div>
															</div>
														</div>
													</div>
													<!-- FIN GLOBO GRIS   --><!--  FIN GLOBO GRIS --> 
													
													<!-- INICIO GLOBO GRIS --><!-- INICIO GLOBO GRIS -->
													<div class="globo_gris_bis conmargen02" style="margin-bottom: 0;">
														<div class="globo_gris_01_bis">
															<div class="globo_gris_02_bis">
																<div class="globo_gris_03_bis">
																	<table border="0" class="res_list_inv listado_estadisticas" cellpadding="0" cellspacing="0" summary="Tercero Mas Buscado Mes">
																		<tr class="tr_blanco">
																			<td valign="top" class="dere00"><span>${form.terminosMasBuscadosMesTotales[2]}:</span></td>
																			<td valign="top" class="alineado_izq objetos"><span><fmt:formatNumber type="number" value="${form.terminosMasBuscadosMesNumLocales[2]}" /></span></td>
																			<td class="alineado_izq objetos" valign="top"><span><fmt:formatNumber type="number" value="${form.terminosMasBuscadosMesNumTotales[2]}" /></span></td>
																		</tr>
																	</table>
																</div>
															</div>
														</div>
													</div>
													<!-- FIN GLOBO GRIS --><!-- FIN GLOBO GRIS --> 
													
													<!--  INICIO GLOBO GRIS   --><!-- INICIO GLOBO GRIS -->
													<div class="globo_gris_bis conmargen02" style="margin-bottom: 0;">
														<div class="globo_gris_01_bis">
															<div class="globo_gris_02_bis">
																<div class="globo_gris_03_bis">
																	<table border="0" class="res_list_inv listado_estadisticas" cellpadding="0" cellspacing="0" summary="Cuarto Mas Buscado Mes">
																		<tr class="tr_blanco">
																			<td valign="top" class="dere00"><span>${form.terminosMasBuscadosMesTotales[3]}:</span></td>
																			<td valign="top" class="alineado_izq objetos"><span><fmt:formatNumber type="number" value="${form.terminosMasBuscadosMesNumLocales[3]}" /></span></td>
																			<td class="alineado_izq objetos" valign="top"><span><fmt:formatNumber type="number" value="${form.terminosMasBuscadosMesNumTotales[3]}" /></span></td>
																		</tr>
																	</table>
																</div>
															</div>
														</div>
													</div>
													<!-- FIN GLOBO GRIS --><!-- FIN GLOBO GRIS --> 
													
													<!--  INICIO GLOBO GRIS   --><!-- INICIO GLOBO GRIS -->
													<div class="globo_gris_bis conmargen02" style="margin-bottom: 0;">
														<div class="globo_gris_01_bis">
															<div class="globo_gris_02_bis">
																<div class="globo_gris_03_bis">
																	<table border="0" class="res_list_inv listado_estadisticas" cellpadding="0" cellspacing="0" summary="Quinto Mas Buscado Mes">
																		<tr class="tr_blanco">
																			<td valign="top" class="dere00"><span>${form.terminosMasBuscadosMesTotales[4]}:</span></td>
																			<td valign="top" class="alineado_izq objetos"><span><fmt:formatNumber type="number" value="${form.terminosMasBuscadosMesNumLocales[4]}" /></span></td>
																			<td class="alineado_izq objetos" valign="top"><span><fmt:formatNumber type="number" value="${form.terminosMasBuscadosMesNumTotales[4]}" /></span></td>
																		</tr>
																	</table>
																</div>
															</div>
														</div>
													</div>
													<!-- FIN GLOBO GRIS --><!-- FIN GLOBO GRIS -->
													
													<br>
													<a href="#" class="poppup05"><bean:message key="estadisticasPortada.mostrarDatosDesglosadosPorComunidades" /></a>
													
													
													<div class="oculto">
														<div class="thumb_list" id="pop_up_05">			
															<a class="close close_x sprited" href="#">close</a>
															<div class="showhide clearfix ">								
																<div class="escroll"> 	
																	<h3 class="h3_generico">Estadísticas de TÉRMINOS MÁS BUSCADOS EN EL MES desglosadas por comunidades</h3>					
																	<div class="caja_tabla" id="bordeada" >		
																		<table border="1" class="generica tabla_rss_estadisticas" width="100%" cellpadding="0" cellspacing="0"  summary="Tabla de Resultados">						
																			
																			<tr>
																				<th></th>
																				<c:forEach items="${form.comunidadesMasBuscadoMesPrimero}" var="comunidad" varStatus="status">
																					<th class="al_der">${comunidad}</th>
																				</c:forEach>
																				<th>Total</th>
																			</tr>
																			<tr class="tr_gris">
																				<td class="al_izq">${form.terminosMasBuscadosMesTotales[0]}</td>
																				<c:forEach items="${form.comunidadesMasBuscadoMesPrimeroValores}" var="valor" varStatus="status">
																					<td><fmt:formatNumber type="number" value="${valor}" /></td>
																				</c:forEach>
																				<td class="al_der"><fmt:formatNumber type="number" value="${form.terminosMasBuscadosMesNumTotales[0]}" /></td>
																			</tr>	
																			<tr class="tr_blanco">
																				<td class="al_izq">${form.terminosMasBuscadosMesTotales[1]}</td>
																				<c:forEach items="${form.comunidadesMasBuscadoMesSegundoValores}" var="valor" varStatus="status">
																					<td><fmt:formatNumber type="number" value="${valor}" /></td>
																				</c:forEach>
																				<td class="al_der"><fmt:formatNumber type="number" value="${form.terminosMasBuscadosMesNumTotales[1]}" /></td>
																			</tr>
																			<tr class="tr_gris">
																				<td class="al_izq">${form.terminosMasBuscadosMesTotales[2]}</td>
																				<c:forEach items="${form.comunidadesMasBuscadoMesTerceroValores}" var="valor" varStatus="status">
																					<td><fmt:formatNumber type="number" value="${valor}" /></td>
																				</c:forEach>
																				<td class="al_der"><fmt:formatNumber type="number" value="${form.terminosMasBuscadosMesNumTotales[2]}" /></td>
																			</tr>
																			<tr class="tr_blanco">
																				<td class="al_izq">${form.terminosMasBuscadosMesTotales[3]}</td>
																				<c:forEach items="${form.comunidadesMasBuscadoMesCuartoValores}" var="valor" varStatus="status">
																					<td><fmt:formatNumber type="number" value="${valor}" /></td>
																				</c:forEach>
																				<td class="al_der"><fmt:formatNumber type="number" value="${form.terminosMasBuscadosMesNumTotales[3]}" /></td>
																			</tr>
																			<tr class="tr_gris">
																				<td class="al_izq">${form.terminosMasBuscadosMesTotales[4]}</td>
																				<c:forEach items="${form.comunidadesMasBuscadoMesQuintoValores}" var="valor" varStatus="status">
																					<td><fmt:formatNumber type="number" value="${valor}" /></td>
																				</c:forEach>
																				<td class="al_der"><fmt:formatNumber type="number" value="${form.terminosMasBuscadosMesNumTotales[4]}" /></td>
																			</tr>	
																			<tr class="tr_blanco">
																				<td class="al_izq">TOTAL</td>
																				<c:forEach items="${form.comunidadesMasBuscadoMesPrimero}" var="valor" varStatus="status">
																					<c:set var="totalCCAA" value="${form.comunidadesMasBuscadoMesPrimeroValores[status.index] +form.comunidadesMasBuscadoMesSegundoValores[status.index] + form.comunidadesMasBuscadoMesTerceroValores[status.index] +form.comunidadesMasBuscadoMesCuartoValores[status.index]+form.comunidadesMasBuscadoMesQuintoValores[status.index]}" />												
																					<td class="al_der"><fmt:formatNumber type="number" value="${totalCCAA}"/></td>
																				</c:forEach>
																				<c:set var="totalActividadCCAA" value="${form.terminosMasBuscadosMesNumTotales[0] +form.terminosMasBuscadosMesNumTotales[1] +form.terminosMasBuscadosMesNumTotales[2] +form.terminosMasBuscadosMesNumTotales[3] +form.terminosMasBuscadosMesNumTotales[4]}" />												
																				<td class="al_der"><fmt:formatNumber type="number" value="${totalActividadCCAA}"/></td>												
																			</tr>																																																																																																																	
																		</table>
																		<h4><p><bean:message key="estadisticasPortada.leyendaDatosNoActualizados" /></p></h4>
																		<br/>
																	</div>
																</div>
															</div> 
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>

									<!--  TERMINOS SEMANA  -->
									<div class="globo_blanco gb_ie">
										<div class="globo_blanco_01">
											<div class="globo_blanco_02">
												<div class="globo_blanco_03">
													<h3><bean:message key="estadisticasPortada.terminosMasBuscadosSemana" /></h3>
													<h3 class="clearfix theader">
														<em>&nbsp;&nbsp;&nbsp;&nbsp;</em>
														<strong>Local</strong>
														<strong>Total</strong>
													</h3>
													
													<!-- INICIO GLOBO GRIS --> <!-- INICIO GLOBO GRIS -->
													<div class="globo_gris_bis conmargen02" style="margin-bottom: 0;">
														<div class="globo_gris_01_bis">
															<div class="globo_gris_02_bis">
																<div class="globo_gris_03_bis">
																	<table border="0" class="res_list_inv listado_estadisticas" cellpadding="0" cellspacing="0" summary="Primer Mas Buscado Semana">
																		<tr class="tr_blanco">
																			<td valign="top" class="dere00"><span>${form.terminosMasBuscadosSemanaTotales[0]}:</span></td>
																			<td valign="top" class="alineado_izq objetos"><span><fmt:formatNumber type="number" value="${form.terminosMasBuscadosSemanaNumLocales[0]}" /></span></td>
																			<td class="alineado_izq objetos" valign="top"><span><fmt:formatNumber type="number" value="${form.terminosMasBuscadosSemanaNumTotales[0]}" /></span></td>
																		</tr>
																	</table>
																</div>
															</div>
														</div>
													</div>
													<!-- FIN GLOBO GRIS --><!-- FIN GLOBO GRIS --> 
													
													<!-- INICIO GLOBO GRIS   --><!-- INICIO GLOBO GRIS -->
													<div class="globo_gris_bis conmargen02" style="margin-bottom: 0;">
														<div class="globo_gris_01_bis">
															<div class="globo_gris_02_bis">
																<div class="globo_gris_03_bis">
																	<table border="0" class="res_list_inv listado_estadisticas" cellpadding="0" cellspacing="0" summary="Segundo Mas Buscado Semana">
																		<tr class="tr_blanco">
																			<td valign="top" class="dere00"><span>${form.terminosMasBuscadosSemanaTotales[1]}:</span></td>
																			<td valign="top" class="alineado_izq objetos"><span><fmt:formatNumber type="number" value="${form.terminosMasBuscadosSemanaNumLocales[1]}" /></span></td>
																			<td class="alineado_izq objetos" valign="top"><span><fmt:formatNumber type="number" value="${form.terminosMasBuscadosSemanaNumTotales[1]}" /></span></td>
																		</tr>
																	</table>
																</div>
															</div>
														</div>
													</div>
													<!-- FIN GLOBO GRIS   --><!--  FIN GLOBO GRIS --> 
													
													<!-- INICIO GLOBO GRIS --><!-- INICIO GLOBO GRIS -->
													<div class="globo_gris_bis conmargen02" style="margin-bottom: 0;">
														<div class="globo_gris_01_bis">
															<div class="globo_gris_02_bis">
																<div class="globo_gris_03_bis">
																	<table border="0" class="res_list_inv listado_estadisticas" cellpadding="0" cellspacing="0" summary="Tercero Mas Buscado Semana">
																		<tr class="tr_blanco">
																			<td valign="top" class="dere00"><span>${form.terminosMasBuscadosSemanaTotales[2]}:</span></td>
																			<td valign="top" class="alineado_izq objetos"><span><fmt:formatNumber type="number" value="${form.terminosMasBuscadosSemanaNumLocales[2]}" /></span></td>
																			<td class="alineado_izq objetos" valign="top"><span><fmt:formatNumber type="number" value="${form.terminosMasBuscadosSemanaNumTotales[2]}" /></span></td>
																		</tr>
																	</table>
																</div>
															</div>
														</div>
													</div>
													<!-- FIN GLOBO GRIS --><!-- FIN GLOBO GRIS --> 
													
													<!--  INICIO GLOBO GRIS   --><!-- INICIO GLOBO GRIS -->
													<div class="globo_gris_bis conmargen02" style="margin-bottom: 0;">
														<div class="globo_gris_01_bis">
															<div class="globo_gris_02_bis">
																<div class="globo_gris_03_bis">
																	<table border="0" class="res_list_inv listado_estadisticas" cellpadding="0" cellspacing="0" summary="Cuarto Mas Buscado Semana">
																		<tr class="tr_blanco">
																			<td valign="top" class="dere00"><span>${form.terminosMasBuscadosSemanaTotales[3]}:</span></td>
																			<td valign="top" class="alineado_izq objetos"><span><fmt:formatNumber type="number" value="${form.terminosMasBuscadosSemanaNumLocales[3]}" /></span></td>
																			<td class="alineado_izq objetos" valign="top"><span><fmt:formatNumber type="number" value="${form.terminosMasBuscadosSemanaNumTotales[3]}" /></span></td>
																		</tr>
																	</table>
																</div>
															</div>
														</div>
													</div>
													<!-- FIN GLOBO GRIS --><!-- FIN GLOBO GRIS --> 
													
													<!--  INICIO GLOBO GRIS   --><!-- INICIO GLOBO GRIS -->
													<div class="globo_gris_bis conmargen02" style="margin-bottom: 0;">
														<div class="globo_gris_01_bis">
															<div class="globo_gris_02_bis">
																<div class="globo_gris_03_bis">
																	<table border="0" class="res_list_inv listado_estadisticas" cellpadding="0" cellspacing="0" summary="Quinto Mas Buscado Semana">
																		<tr class="tr_blanco">
																			<td valign="top" class="dere00"><span>${form.terminosMasBuscadosSemanaTotales[4]}:</span></td>
																			<td valign="top" class="alineado_izq objetos"><span><fmt:formatNumber type="number" value="${form.terminosMasBuscadosSemanaNumLocales[4]}" /></span></td>
																			<td class="alineado_izq objetos" valign="top"><span><fmt:formatNumber type="number" value="${form.terminosMasBuscadosSemanaNumTotales[4]}" /></span></td>
																		</tr>
																	</table>
																</div>
															</div>
														</div>
													</div>
													<!-- FIN GLOBO GRIS --><!-- FIN GLOBO GRIS -->
													<br>
													<a href="#" class="poppup06"><bean:message key="estadisticasPortada.mostrarDatosDesglosadosPorComunidades" /></a>


													<div class="oculto">
														<div class="thumb_list" id="pop_up_06">			
															<a class="close close_x sprited" href="#">close</a>
															<div class="showhide clearfix ">								
																<div class="escroll"> 	
																	<h3 class="h3_generico">Estadísticas de TÉRMINOS MÁS BUSCADOS EN LA SEMANA desglosadas por comunidades</h3>					
																	<div class="caja_tabla" id="bordeada" >		
																		<table border="1" class="generica tabla_rss_estadisticas" width="100%" cellpadding="0" cellspacing="0"  summary="Tabla de Resultados">																								
																			<tr>
																			<th></th>
																				<c:forEach items="${form.comunidadesMasBuscadoSemanaPrimero}" var="comunidad" varStatus="status">
																					<th>${comunidad}</th>
																				</c:forEach>
																				<th>Total</th>
																			</tr>
																			<tr class="tr_gris">
																				<td class="al_izq">${form.terminosMasBuscadosSemanaTotales[0]}</td>
																				<c:forEach items="${form.comunidadesMasBuscadoSemanaPrimeroValores}" var="valor" varStatus="status">
																					<td><fmt:formatNumber type="number" value="${valor}" /></td>
																				</c:forEach>
																				<td class="al_der"><fmt:formatNumber type="number" value="${form.terminosMasBuscadosSemanaNumTotales[0]}" /></td>
																			</tr>	
																			<tr class="tr_blanco">
																				<td class="al_izq">${form.terminosMasBuscadosSemanaTotales[1]}</td>
																				<c:forEach items="${form.comunidadesMasBuscadoSemanaSegundoValores}" var="valor" varStatus="status">
																					<td><fmt:formatNumber type="number" value="${valor}" /></td>
																				</c:forEach>
																				<td class="al_der"><fmt:formatNumber type="number" value="${form.terminosMasBuscadosSemanaNumTotales[1]}" /></td>
																			</tr>
																			<tr class="tr_gris">
																				<td class="al_izq">${form.terminosMasBuscadosSemanaTotales[2]}</td>
																				<c:forEach items="${form.comunidadesMasBuscadoSemanaTerceroValores}" var="valor" varStatus="status">
																					<td><fmt:formatNumber type="number" value="${valor}" /></td>
																				</c:forEach>
																				<td class="al_der"><fmt:formatNumber type="number" value="${form.terminosMasBuscadosSemanaNumTotales[2]}" /></td>
																			</tr>
																			<tr class="tr_blanco">
																				<td class="al_izq">${form.terminosMasBuscadosSemanaTotales[3]}</td>
																				<c:forEach items="${form.comunidadesMasBuscadoSemanaCuartoValores}" var="valor" varStatus="status">
																					<td><fmt:formatNumber type="number" value="${valor}" /></td>
																				</c:forEach>
																				<td class="al_der"><fmt:formatNumber type="number" value="${form.terminosMasBuscadosSemanaNumTotales[3]}" /></td>
																			</tr>
																			<tr class="tr_gris">
																				<td class="al_izq">${form.terminosMasBuscadosSemanaTotales[4]}</td>
																				<c:forEach items="${form.comunidadesMasBuscadoSemanaQuintoValores}" var="valor" varStatus="status">
																					<td><fmt:formatNumber type="number" value="${valor}" /></td>
																				</c:forEach>
																				<td class="al_der"><fmt:formatNumber type="number" value="${form.terminosMasBuscadosSemanaNumTotales[4]}" /></td>
																			</tr>	
																			<tr class="tr_blanco">
																				<td class="al_izq">TOTAL</td>
																				<c:forEach items="${form.comunidadesMasBuscadoSemanaPrimero}" var="valor" varStatus="status">
																					<c:set var="totalCCAA" value="${form.comunidadesMasBuscadoSemanaPrimeroValores[status.index] +form.comunidadesMasBuscadoSemanaSegundoValores[status.index] + form.comunidadesMasBuscadoSemanaTerceroValores[status.index] +form.comunidadesMasBuscadoSemanaCuartoValores[status.index]+form.comunidadesMasBuscadoSemanaQuintoValores[status.index]}" />												
																					<td class="al_der"><fmt:formatNumber type="number" value="${totalCCAA}"/></td>
																				</c:forEach>
																				<c:set var="totalActividadCCAA" value="${form.terminosMasBuscadosSemanaNumTotales[0] +form.terminosMasBuscadosSemanaNumTotales[1] +form.terminosMasBuscadosSemanaNumTotales[2] +form.terminosMasBuscadosSemanaNumTotales[3] +form.terminosMasBuscadosSemanaNumTotales[4]}" />												
																				<td class="al_der"><fmt:formatNumber type="number" value="${totalActividadCCAA}"/></td>												
																			</tr>																																																																																															
																		</table>
																		<h4><p><bean:message key="estadisticasPortada.leyendaDatosNoActualizados" /></p></h4>
																		<br/>
																	</div>
																</div>
															</div> 
														</div>
													</section>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--  FIN GLOBO GRIS   --> <!--  FIN GLOBO GRIS   -->
			</form>
		</div>
		<!-- Fin plantilla contenido  -->
		<!-- ********************         Fin plantilla contenido        ******************************-->
	</tiles:put>
</tiles:insert>

<script type="text/javascript" src="/static/js/jquery.lightbox_me.js"></script>
<link rel="stylesheet" media="screen" href="/static/css/red2.css" type="text/css"/>


