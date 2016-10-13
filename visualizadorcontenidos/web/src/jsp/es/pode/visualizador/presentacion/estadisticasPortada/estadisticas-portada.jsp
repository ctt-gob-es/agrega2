<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/WEB-INF/tags/googleAnalytic.tld" prefix="analytic" %>
<%@ taglib prefix="agrega" uri="/WEB-INF/tags/agregaProperties.tld" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<%@ include file="/taglib-imports.jspf" %>


<tiles:insert definition="layout-menu-0">

	<tiles:put name="title" type="string">
		<bean:message key="title.Comun" />
	</tiles:put>

	<tiles:put name="body-principal" type="string">

		<!--*************************        Inicio plantilla contenido      ********************************* -->
		<div class="col_der" id="estadisticas">
			<section>
				<header>
					<h2><bean:message key="estadisticasPortada.titulo" /></h2>
				</header> 
				
				<!-- ODES --> 
				<article class="odes conmargen">
					<div class="caja">
						<h3 class="clearfix">
							<em><bean:message key="estadisticasPortada.odes" /></em>
							<strong>Local</strong>
							<strong>Total</strong>
						</h3>
						<div class="campos clearfix">
							<table class="estadisticas_table">
							<tbody>
								<tr>
									<td class="icon e_usuarios">
										<em><bean:message key="estadisticasPortada.medios" />:</em>
									</td>
									<td class="local_td"><fmt:formatNumber type="number" value="${form.numLocalMediosIntegrados}" /></td>
									<td class="total_td"><fmt:formatNumber type="number" value="${form.numTotalMediosIntegrados}" /></td>
								</tr>
								<tr>
									<td class="icon e_usuarios">
										<em><bean:message key="estadisticasPortada.objetosAprendizaje" />:</em>
									</td>
									<td class="local_td"><fmt:formatNumber type="number" value="${form.numLocalObjetosAprendizaje}" /></td>
									<td class="total_td"><fmt:formatNumber type="number" value="${form.numTotalObjetosAprendizaje}" /></td>
								</tr>
								<tr>
									<td class="icon e_usuarios">
										<em><bean:message key="estadisticasPortada.secuencias" />:</em>
									</td>
									<td class="local_td"><fmt:formatNumber type="number" value="${form.numLocalSecuencias}" /></td>
									<td class="total_td"><fmt:formatNumber type="number" value="${form.numTotalSecuencias}" /></td>
								</tr>																							
								<tr>
									<td class="icon e_usuarios">
										<em><bean:message key="estadisticasPortada.cursos" />:</em>
									</td>
									<td class="local_td"><fmt:formatNumber type="number" value="${form.numLocalCursos}" /></td>
									<td class="total_td"><fmt:formatNumber type="number" value="${form.numTotalCursos}" /></td>
								</tr>
								<tr>
									<td class="icon e_usuarios">
										<em><bean:message key="estadisticasPortada.objetos" />:</em>
									</td>
									<td class="local_td"><fmt:formatNumber type="number" value="${form.numLocalObjetos}" /></td>
									<td class="total_td"><fmt:formatNumber type="number" value="${form.numTotalObjetos}" /></td>
								</tr>
							</tbody>
							</table>
						</div>
						<a href="#" class="poppup01"><bean:message key="estadisticasPortada.mostrarDatosDesglosadosPorComunidades" /></a>
					</div>
					
					<section id="ocultos">
						<div class="thumb_list" id="pop_up_01">			
							<a class="close close_x sprited" href="#">close</a>
							<div class="showhide clearfix ">								
								<div class="escroll"> 	
									<h4><p>Estadísticas de ODES POR NIVEL DE AGREGACIÓN desglosadas por comunidades</p>	</h4>					
									<div align="left">		
										<table width="100%" class="tabla_rss_estadisticas">						
											<tr>
											<th></th>
												<c:forEach items="${form.comunidadesBusquedas}" var="comunidad" varStatus="status">
													<th class="al_der">${comunidad}</th>
												</c:forEach>
											</tr>
											<tr class="pijama">
												<td><bean:message key="estadisticasPortada.medios" /></td>
												<c:forEach items="${form.comunidadesMediosIntegradosValores}" var="valor" varStatus="status">
													<td class="al_der"><fmt:formatNumber type="number" value="${valor}" /></td>
												</c:forEach>
											</tr>		
											<tr>
												<td><bean:message key="estadisticasPortada.objetosAprendizaje" /></td>
												<c:forEach items="${form.comunidadesObjAprendizajeValores}" var="valor" varStatus="status">
													<td class="al_der"><fmt:formatNumber type="number" value="${valor}" /></td>
												</c:forEach>
											</tr>		
											<tr class="pijama">
												<td><bean:message key="estadisticasPortada.secuencias" /></td>
												<c:forEach items="${form.comunidadesSecuenciasValores}" var="valor" varStatus="status">
													<td class="al_der"><fmt:formatNumber type="number" value="${valor}" /></td>
												</c:forEach>
											</tr>																													
											<tr>
												<td><bean:message key="estadisticasPortada.cursos" /></td>
												<c:forEach items="${form.comunidadesCursosValores}" var="valor" varStatus="status">
													<td class="al_der"><fmt:formatNumber type="number" value="${valor}" /></td>
												</c:forEach>
											</tr>	
											<tr>
												<td><bean:message key="estadisticasPortada.objetos" /></td>
												<c:forEach items="${form.comunidadesObjetosValores}" var="valor" varStatus="status">
													<td class="al_der"><fmt:formatNumber type="number" value="${valor}" /></td>
												</c:forEach>
											</tr>										
										</table>
										<h1><p><bean:message key="estadisticasPortada.leyendaDatosNoActualizados" /></p></h1>
										<br/>
									</div>
								</div>
							</div> 
						</div>
					</section>
				</article> 
									
					
				<!-- ACTIVIDAD --> 
				<article class="odes conmargen">
					<div class="caja">
						<h3 class="clearfix">
							<em><bean:message key="estadisticasPortada.actividad" /></em>
							<strong>Local</strong>
							<strong>Total</strong>
						</h3>
						<div class="campos clearfix">
							<table class="estadisticas_table">
							<tbody>
								<tr>
									<td class="icon e_usuarios">
										<em><bean:message key="estadisticasPortada.busquedas" />:</em>
									</td>
									<td class="local_td"><fmt:formatNumber type="number" value="${form.busquedasLocales}" /></td>
									<td class="total_td"><fmt:formatNumber type="number" value="${form.busquedasTotales}" /></td>
								</tr>
									<td class="icon e_usuarios">
										<em><bean:message key="estadisticasPortada.descargas" />:</em>
									</td>
									<td class="local_td"><fmt:formatNumber type="number" value="${form.descargas}" /></td>
									<td class="total_td"><fmt:formatNumber type="number" value="${form.descargasTotales}" /></td>
								<tr>
									<td class="icon e_usuarios">
										<em><bean:message key="estadisticasPortada.fichasAccedidas" />:</em>
									</td>
									<td class="local_td"><fmt:formatNumber type="number" value="${form.fichasAccedidasLocales}" /></td>
									<td class="total_td"><fmt:formatNumber type="number" value="${form.fichasAccedidasTotales}" /></td>
								</tr>
								<tr>
									<td class="icon e_usuarios">
										<em><bean:message key="estadisticasPortada.odesPrevisualizados" />:</em>
									</td>
									<td class="local_td"><fmt:formatNumber type="number" value="${form.odesPrevisualizadosLocales}" /></td>
									<td class="total_td"><fmt:formatNumber type="number" value="${form.odesPrevisualizadosTotales}" /></td>
								</tr>
							</tbody>
							</table>						 												
						</div>
						<a href="#" class="poppup02"><bean:message key="estadisticasPortada.mostrarDatosDesglosadosPorComunidades" /></a>
					</div>
					
					<section id="ocultos">
						<div class="thumb_list" id="pop_up_02">			
							<a class="close close_x sprited" href="#">close</a>
							<div class="showhide clearfix ">						
								<div class="escroll"> 	
									<h4><p>Estadísticas de ACTIVIDAD desglosadas por comunidades</p>	</h4>					
									<div align="left">		
										<table width="100%" class="tabla_rss_estadisticas">						
											<tr>
											<th></th>
												<c:forEach items="${form.comunidadesBusquedas}" var="comunidad" varStatus="status">
													<th class="al_der">${comunidad}</th>
												</c:forEach>
												<th>Total</th>
											</tr>
											<tr>
												<td>Búsquedas</td>
												<c:forEach items="${form.comunidadesBusquedasValores}" var="valor" varStatus="status">
													<td class="al_der"><fmt:formatNumber type="number" value="${valor}" /></td>
												</c:forEach>
												<td class="al_der"><fmt:formatNumber type="number" value="${form.busquedasTotales}" /></td>
											</tr>	
											<tr class="pijama">
												<td>Descargas</td>
												<c:forEach items="${form.comunidadesDescargasValores}" var="valor" varStatus="status">
													<td class="al_der"><fmt:formatNumber type="number" value="${valor}" /></td>
												</c:forEach>
												<td class="al_der"><fmt:formatNumber type="number" value="${form.descargasTotales}" /></td>
											</tr>
											<tr>
												<td>Fichas accedidas locales</td>
												<c:forEach items="${form.comunidadesFichasAccedidasValores}" var="valor" varStatus="status">
													<td class="al_der"><fmt:formatNumber type="number" value="${valor}" /></td>
												</c:forEach>
												<td class="al_der"><fmt:formatNumber type="number" value="${form.fichasAccedidasTotales}" /></td>
											</tr>
											<tr class="pijama">
												<td>ODES previsualizados locales</td>
												<c:forEach items="${form.comunidadesOdesPrevisualizadosValores}" var="valor" varStatus="status">
													<td class="al_der"><fmt:formatNumber type="number" value="${valor}" /></td>
												</c:forEach>
												<td class="al_der"><fmt:formatNumber type="number" value="${form.odesPrevisualizadosTotales}" /></td>
											</tr>	
											<tr>
												<td>TOTAL</td>
												<c:forEach items="${form.comunidadesOdesPrevisualizadosValores}" var="valor" varStatus="status">
													<c:set var="totalCCAA" value="${form.comunidadesBusquedasValores[status.index] +form.comunidadesDescargasValores[status.index] + form.comunidadesFichasAccedidasValores[status.index] +form.comunidadesOdesPrevisualizadosValores[status.index]}" />												
													<td class="al_der"><fmt:formatNumber type="number" value="${totalCCAA}"/></td>
												</c:forEach>
												<c:set var="totalActividadCCAA" value="${form.busquedasTotales +form.descargasTotales +form.fichasAccedidasTotales +form.odesPrevisualizadosTotales}" />												
												<td class="al_der"><fmt:formatNumber type="number" value="${totalActividadCCAA}"/></td>												
											</tr>	
										</table>
										<h1><p><bean:message key="estadisticasPortada.leyendaDatosNoActualizados" /></p></h1>
										<br/>
									</div>
								</div>
							</div> 
						</div>
					</section>
				</article>

				<!-- TERMINOS TOTALES -->
				<article class="odes conmargen">
					<div class="caja">
						<h3 class="clearfix">
							<em><bean:message key="estadisticasPortada.terminosMasBuscados" /></em>
							<strong>Local</strong>
							<strong>Total</strong>
						</h3>
						<div class="campos clearfix">
							<table class="estadisticas_table">
							<tbody>
								<tr>
									<td class="icon e_usuarios">
										<em>${form.terminosMasBuscadosTotales[0]}:</em>
									</td>
									<td class="local_td"><fmt:formatNumber type="number" value="${form.terminosMasBuscadosNumLocales[0]}" /></td>
									<td class="total_td"><fmt:formatNumber type="number" value="${form.terminosMasBuscadosNumTotales[0]}" /></td>
								</tr>
								<tr>
									<td class="icon e_usuarios">
										<em>${form.terminosMasBuscadosTotales[1]}:</em>
									</td>
									<td class="local_td"><fmt:formatNumber type="number" value="${form.terminosMasBuscadosNumLocales[1]}" /></td>
									<td class="total_td"><fmt:formatNumber type="number" value="${form.terminosMasBuscadosNumTotales[1]}" /></td>
								</tr>
								<tr>
									<td class="icon e_usuarios">
										<em>${form.terminosMasBuscadosTotales[2]}:</em>
									</td>
									<td class="local_td"><fmt:formatNumber type="number" value="${form.terminosMasBuscadosNumLocales[2]}" /></td>
									<td class="total_td"><fmt:formatNumber type="number" value="${form.terminosMasBuscadosNumTotales[2]}" /></td>
								</tr>
								<tr>
									<td class="icon e_usuarios">
										<em>${form.terminosMasBuscadosTotales[3]}:</em>
									</td>
									<td class="local_td"><fmt:formatNumber type="number" value="${form.terminosMasBuscadosNumLocales[3]}" /></td>
									<td class="total_td"><fmt:formatNumber type="number" value="${form.terminosMasBuscadosNumTotales[3]}" /></td>
								</tr>
								<tr>
									<td class="icon e_usuarios">
										<em>${form.terminosMasBuscadosTotales[4]}:</em>
									</td>
									<td class="local_td"><fmt:formatNumber type="number" value="${form.terminosMasBuscadosNumLocales[4]}" /></td>
									<td class="total_td"><fmt:formatNumber type="number" value="${form.terminosMasBuscadosNumTotales[4]}" /></td>
								</tr>
							</tbody>
							</table>
						</div>
						<a href="#" class="poppup03"><bean:message key="estadisticasPortada.mostrarDatosDesglosadosPorComunidades" /></a>
					</div>
					
					<section id="ocultos">
						<div class="thumb_list" id="pop_up_03">			
							<a class="close close_x sprited" href="#">close</a>
							<div class="showhide clearfix ">						
								<div class="escroll"> 	
									<h4><p>Estadísticas de TÉRMINOS MÁS BUSCADOS desglosadas por comunidades</p>	</h4>					
									<div align="left">		
										<table width="100%" class="tabla_rss_estadisticas">						
											<tr>
											<th></th>
												<c:forEach items="${form.comunidadesMasBuscadoTotalPrimero}" var="comunidad" varStatus="status">
													<th class="al_der">${comunidad}</th>
												</c:forEach>
												<th>Total</th>
											</tr>
											<tr>
												<td>${form.terminosMasBuscadosTotales[0]}</td>
												<c:forEach items="${form.comunidadesMasBuscadoTotalPrimeroValores}" var="valor" varStatus="status">
													<td class="al_der"><fmt:formatNumber type="number" value="${valor}" /></td>
												</c:forEach>
												<td class="al_der"><fmt:formatNumber type="number" value="${form.terminosMasBuscadosNumTotales[0]}" /></td>
											</tr>	
											<tr class="pijama">
												<td>${form.terminosMasBuscadosTotales[1]}</td>
												<c:forEach items="${form.comunidadesMasBuscadoTotalSegundoValores}" var="valor" varStatus="status">
													<td class="al_der"><fmt:formatNumber type="number" value="${valor}" /></td>
												</c:forEach>
												<td class="al_der"><fmt:formatNumber type="number" value="${form.terminosMasBuscadosNumTotales[1]}" /></td>
											</tr>
											<tr>
												<td>${form.terminosMasBuscadosTotales[2]}</td>
												<c:forEach items="${form.comunidadesMasBuscadoTotalTerceroValores}" var="valor" varStatus="status">
													<td class="al_der"><fmt:formatNumber type="number" value="${valor}" /></td>
												</c:forEach>
												<td class="al_der"><fmt:formatNumber type="number" value="${form.terminosMasBuscadosNumTotales[2]}" /></td>
											</tr>
											<tr class="pijama">
												<td>${form.terminosMasBuscadosTotales[3]}</td>
												<c:forEach items="${form.comunidadesMasBuscadoTotalCuartoValores}" var="valor" varStatus="status">
													<td class="al_der"><fmt:formatNumber type="number" value="${valor}" /></td>
												</c:forEach>
												<td class="al_der"><fmt:formatNumber type="number" value="${form.terminosMasBuscadosNumTotales[3]}" /></td>
											</tr>
											<tr>
												<td>${form.terminosMasBuscadosTotales[4]}</td>
												<c:forEach items="${form.comunidadesMasBuscadoTotalQuintoValores}" var="valor" varStatus="status">
													<td class="al_der"><fmt:formatNumber type="number" value="${valor}" /></td>
												</c:forEach>
												<td class="al_der"><fmt:formatNumber type="number" value="${form.terminosMasBuscadosNumTotales[4]}" /></td>
											</tr>		
											<tr>
												<td>TOTAL</td>
												<c:forEach items="${form.comunidadesMasBuscadoTotalPrimero}" var="valor" varStatus="status">
													<c:set var="totalCCAA" value="${form.comunidadesMasBuscadoTotalPrimeroValores[status.index] +form.comunidadesMasBuscadoTotalSegundoValores[status.index] + form.comunidadesMasBuscadoTotalTerceroValores[status.index] +form.comunidadesMasBuscadoTotalCuartoValores[status.index]+form.comunidadesMasBuscadoTotalQuintoValores[status.index]}" />												
													<td class="al_der"><fmt:formatNumber type="number" value="${totalCCAA}"/></td>
												</c:forEach>
												<c:set var="totalActividadCCAA" value="${form.terminosMasBuscadosNumTotales[0] +form.terminosMasBuscadosNumTotales[1] +form.terminosMasBuscadosNumTotales[2] +form.terminosMasBuscadosNumTotales[3] +form.terminosMasBuscadosNumTotales[4]}" />												
												<td class="al_der"><fmt:formatNumber type="number" value="${totalActividadCCAA}"/></td>												
											</tr>																					
										</table>
										<h1><p><bean:message key="estadisticasPortada.leyendaDatosNoActualizados" /></p></h1>
										<br/>
									</div>
								</div>
							</div> 
						</div>
					</section>
				</article> 								
			</section>
		</div>
	</tiles:put>
</tiles:insert>

<script type='text/javascript' src="<rewrite:rewrite url="static/Agrega2/js/jquery.lightbox_me.js"/>"></script>