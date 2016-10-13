<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tags/valoracion.tld" prefix="estrellas" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tags/social.tld" prefix="social" %>
<%@ taglib uri="/WEB-INF/tags/formato.tld" prefix="format" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<%@ taglib uri="/WEB-INF/tags/googleAnalytic.tld" prefix="analytic" %>
<%@ taglib uri="/WEB-INF/tags/linkOaiOre.tld" prefix="linkOaiOre" %>
<%@ page import="es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils"%>

<script type="text/javascript" src="/static/js/addthis_widget.js"></script>
<script type="text/javascript">
	var addthis_config = {
           services_compact: 'facebook, twitter, google_plusone_share, pinterest_share, linkedin, more',
           services_exclude: 'print, email'
}
</script>


<tiles:insert definition="layout-sinlateral">

    <tiles:put name="title" type="string">
        <bean:message key="title.Comun"/>
    </tiles:put>
	<tiles:put name="codigo-head" type="String">
		<logic:notEmpty name="form" property="datosOaiOre">
			<linkOaiOre:linkOaiOre metadatoOaiOre="${form.datosOaiOre}"/>			
		</logic:notEmpty>		
	</tiles:put>
	<tiles:put name="body" type="string">
	<%@ include file="/taglib-imports.jspf" %>
	
	<!-- Inicio plantilla contenido  -->
	<div class="plantilla_contenido">

	
	<analytic:googleAnalytic />
		<jsp:include page="/layout/messages.jsp" flush="true" />
		<span class="resultados_bb"><bean:message key="listar.odecu.mostrar.resultados.detalles.fechaPublicacion"/> ${form.fechaPublicacion}</span>
		<h2>${form.titulo}</h2>		
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03" >
				<div id="categorias">

					<c:if test="${form.licencias!=null&&form.licencias!=''}"> 
						<div class="licencias">						  
								<c:choose> 
									<c:when test="${form.idLicencia=='6.2.1'}">
										<span class="licencias_int" id="copyright" title="${form.licencias}" alt="${form.licencias}"></span>		
									</c:when>
									<c:when test="${form.idLicencia=='6.2.2'}">
										<span class="licencias_int" id="eupl" title="${form.licencias}" alt="${form.licencias}"></span>		
									</c:when>
									<c:when test="${form.idLicencia=='6.2.3'}">
										<span class="licencias_int" id="gpl" title="${form.licencias}" alt="${form.licencias}"></span>		
									</c:when>
									<c:when test="${form.idLicencia=='6.2.4'}">
										<span class="licencias_int" id="gpl_eupl" title="${form.licencias}" alt="${form.licencias}"></span>		
									</c:when>
									<c:when test="${form.idLicencia=='6.2.5'}">
										<span class="licencias_int" id="otras_lic_libres" title="${form.licencias}" alt="${form.licencias}"></span>		
									</c:when>
									<c:when test="${form.idLicencia=='6.2.6'}">
										<span class="licencias_int" id="cc_pd" title="${form.licencias}" alt="${form.licencias}"></span>		
									</c:when>
									<c:when test="${form.idLicencia=='6.2.7'}">
										<span class="licencias_int" id="no_corresponde" title="${form.licencias}" alt="${form.licencias}"></span>		
									</c:when>
									<c:when test="${form.idLicencia=='6.2.8'}">
										<span class="licencias_int" id="lic_prop_intelectual" title="${form.licencias}" alt="${form.licencias}"></span>		
									</c:when>
									<c:when test="${form.idLicencia=='6.2.9'}">
										<span class="licencias_int" id="cc_by" title="${form.licencias}" alt="${form.licencias}"></span>					
									</c:when>
									<c:when test="${form.idLicencia=='6.2.10'}">
										<span class="licencias_int" id="cc_by_nd" title="${form.licencias}" alt="${form.licencias}"></span>		
									</c:when>
									<c:when test="${form.idLicencia=='6.2.11'}">
										<span class="licencias_int" id="cc_by_nc_nd" title="${form.licencias}" alt="${form.licencias}"></span>			
									</c:when>
									<c:when test="${form.idLicencia=='6.2.12'}">
										<span class="licencias_int" id="cc_by_nc" title="${form.licencias}" alt="${form.licencias}"></span>			
									</c:when>
									<c:when test="${form.idLicencia=='6.2.13'}">
										<span class="licencias_int" id="cc_by_nc_sa" title="${form.licencias}" alt="${form.licencias}"></span>			
									</c:when>
									<c:when test="${form.idLicencia=='6.2.14'}">
										<span class="licencias_int" id="cc_by_sa" title="${form.licencias}" alt="${form.licencias}"></span>			
									</c:when>
									<c:when test="${form.idLicencia=='6.2.15'}">
										<span class="licencias_int" id="gfdl" title="${form.licencias}" alt="${form.licencias}"></span>			
									</c:when>
								</c:choose>
							<span class="cat">
							<strong >
							<c:if test="${form.gruposLicencia!=null}">   
								<c:forEach items="${form.gruposLicencia}" var="grupo" begin="0">
									<c:choose> 
										<c:when test="${grupo==1}">
											<em id="licencia_b" title="<bean:message key="grupoLicenciasA"/>" alt="<bean:message key="grupoLicenciasA"/>">&nbsp;<span>Categor&iacute;a A</span></em>					
										</c:when>	
										<c:when test="${grupo==2}">
											<em id="licencia_a" title="<bean:message key="grupoLicenciasB"/>" alt="<bean:message key="grupoLicenciasB"/>">&nbsp;<span>Categor&iacute;a B</span></em>				
										</c:when>	
										<c:when test="${grupo==3}">
											<em id="licencia_c" title="<bean:message key="grupoLicenciasC"/>" alt="<bean:message key="grupoLicenciasC"/>">&nbsp;<span>Categor&iacute;a C</span></em>				
										</c:when>
										<c:otherwise>
											<em>&nbsp;-<span >-</span></em>	
										</c:otherwise>
									</c:choose>				
								</c:forEach>
							</c:if>	
							</strong></span>
						</div>	
					</c:if>	
				</div>		
			<!--  INICIO CAJA DE FORMULARIO   -->

			<div id="formulario"  style="position:relative"  >
						
								<logic:notEmpty name="form" property="imagen">
									<a href="${form.imagenAmpliada}" target="_blank" class="imagen_tab" id="imt"><img  src="${form.imagen}" alt="Imagen 02"/></a>
								</logic:notEmpty>
								<logic:empty name="form" property="imagen">
									<img  src="${form.imagen}" alt="Imagen 02" class="imagen_tab" id="imt" />
								</logic:empty>
								

								<c:if test="${form.usuarioLogado || form.tieneIdentidadFederada}">
									<logic:notEqual name="form" property="estaValorado" value="true">
										<div class="valorar" >
											<span><bean:message key="valorar"/>&nbsp;</span>
											<ul class="valorar-estrellas" >
													<li class="rating-actual" style="width:60%;"><bean:message key="valorar.estrellas0"/></li>
													<li><a href="<html:rewrite action="/DetallarODECU/MostrarDetalleODEValorarODE.do"/>?identificadorODE=${form.identificadorODE}&amp;idioma=${form.idioma}&amp;mostrarVuelta=${form.mostrarVuelta}&amp;valoracion=1&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;nodoOrigen=${form.nodoOrigen}&amp;tieneIdentidadFederada=${form.tieneIdentidadFederada}" title="<bean:message key="valorar.estrellas1"/>" class="uno-estrella">1</a></li>
													<li><a href="<html:rewrite action="/DetallarODECU/MostrarDetalleODEValorarODE.do"/>?identificadorODE=${form.identificadorODE}&amp;idioma=${form.idioma}&amp;mostrarVuelta=${form.mostrarVuelta}&amp;valoracion=2&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;nodoOrigen=${form.nodoOrigen}&amp;tieneIdentidadFederada=${form.tieneIdentidadFederada}" title="<bean:message key="valorar.estrellas2"/>" class="dos-estrellas">2</a></li>
													<li><a href="<html:rewrite action="/DetallarODECU/MostrarDetalleODEValorarODE.do"/>?identificadorODE=${form.identificadorODE}&amp;idioma=${form.idioma}&amp;mostrarVuelta=${form.mostrarVuelta}&amp;valoracion=3&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;nodoOrigen=${form.nodoOrigen}&amp;tieneIdentidadFederada=${form.tieneIdentidadFederada}" title="<bean:message key="valorar.estrellas3"/>" class="tres-estrellas">3</a></li>
													<li><a href="<html:rewrite action="/DetallarODECU/MostrarDetalleODEValorarODE.do"/>?identificadorODE=${form.identificadorODE}&amp;idioma=${form.idioma}&amp;mostrarVuelta=${form.mostrarVuelta}&amp;valoracion=4&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;nodoOrigen=${form.nodoOrigen}&amp;tieneIdentidadFederada=${form.tieneIdentidadFederada}" title="<bean:message key="valorar.estrellas4"/>" class="cuatro-estrellas">4</a></li>
													<li><a href="<html:rewrite action="/DetallarODECU/MostrarDetalleODEValorarODE.do"/>?identificadorODE=${form.identificadorODE}&amp;idioma=${form.idioma}&amp;mostrarVuelta=${form.mostrarVuelta}&amp;valoracion=5&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;nodoOrigen=${form.nodoOrigen}&amp;tieneIdentidadFederada=${form.tieneIdentidadFederada}" title="<bean:message key="valorar.estrellas5"/>" class="cinco-estrellas">5</a></li>
											</ul>
										</div>
									</logic:notEqual>
									</c:if>
					
									<estrellas:valoracion valoracion="${form.valoracion}" pagina="detallar" mensaje="listar.odecu.mostrar.resultados.detalles.valoracion"/>
									<br /><br /><br />
									<%
									String url = "http://" + LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio();
									%>	
									<c:if test="${form.licencias!=null&&form.licencias!=''}"> 
				
			</c:if>	
								<div class="texto_foto">${form.descripcion}   
									<div class="valorame" >
										<ul id="res_com">
											
											<SCRIPT LANGUAGE="JavaScript">	
																	
											var direccion= '<%=url%>/${initParam.url_buscadorDetalleCorta}/${form.idioma}/${form.identificadorODE}'; 
											var titulo='<bean:message key="listar.odecu.mostrar.resultados.detalles.tituloFavoritos"/>';											
											if (navigator.userAgent.indexOf("Opera")!=-1) document.write("")
											else{ 
												if (navigator.appName == "Netscape") document.write("") 
											    else{											       
											        var titleLink='<bean:message key="listar.odecu.mostrar.resultados.detalles.datos.favoritos"/>';
											        var literalLink='<bean:message key="listar.odecu.mostrar.resultados.detalles.datos.favoritos"/>';
											       	document.write(' <li class="comentarios_fav">  ')													
														document.write(' <a href="javascript:window.external.AddFavorite(direccion, titulo)"');														
															document.write(' title="'+titleLink+'">  ');															
															document.write(' <span>'+literalLink+'</span>');															
														document.write(' </a>  ')														
													document.write(' </li>  ')														
												}
											}											
											</SCRIPT>								
											
												
													<!--  #############################################################################   -->
													<!--  ############################## ENLACE COMENTARIOS ###########################   -->
													<!--  #############################################################################   -->
													
													<!--  ######### LAYOUTBUSCADOR = EMPAQUETADOR O FEDERADO ############   -->
													<logic:notEqual name="form" property="tipoLayoutBuscador" value="BUSCADOR">
														<c:choose> 
															<c:when test="${form.tipoLayoutBuscador=='FEDERADO' && (form.tieneIdentidadFederada || form.usuarioLogado)}">
																<li class="comentarios_an"><a href="<html:rewrite action="/IntroducirComentariosCU/IntroducirComentariosCU.do"/>?idODE=${form.identificadorODE}&amp;tituloODE=${form.titulo}&amp;imagen=${form.imagen}&amp;idiomaODE=${form.idioma}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;busquedaSimpleAvanzada=${form.busquedaSimpleAvanzada}&amp;mostrarVuelta=${form.mostrarVuelta}&amp;urlODE=${initParam.url_buscadorDetalleCorta}&amp;valoracion=${form.valoracion}&amp;nodoOrigen=${form.nodoOrigen}&amp;tieneIdentidadFederada=${form.tieneIdentidadFederada}"  title="<bean:message key="listar.odecu.mostrar.resultados.detalles.comentarios"/>"><span><bean:message key="listar.odecu.mostrar.resultados.detalles.comentarios"/>(${form.nrComentarios})</span></a></li>													
															</c:when>	
															<c:otherwise>
																<logic:equal name="form" property="nrComentarios" value="0">															
																	<li class="comentarios_an"><a title="<bean:message key="listar.odecu.mostrar.resultados.detalles.no.comentarios"/>"><span><bean:message key="listar.odecu.mostrar.resultados.detalles.comentarios"/>(${form.nrComentarios})</span></a></li>													
																</logic:equal>
																<logic:notEqual name="form" property="nrComentarios" value="0">																			
																	<li class="comentarios_an"><a href="<html:rewrite action="/ListarComentariosCU/ListarComentariosCU.do"/>?idODE=${form.identificadorODE}&amp;tituloODE=${form.titulo}&amp;imagen=${form.imagen}&amp;idiomaODE=${form.idioma}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;busquedaSimpleAvanzada=${form.busquedaSimpleAvanzada}&amp;mostrarVuelta=${form.mostrarVuelta}"  title="<bean:message key="listar.odecu.mostrar.resultados.detalles.comentarios"/>" ><span><bean:message key="listar.odecu.mostrar.resultados.detalles.comentarios"/>(${form.nrComentarios})</span></a></li>													
																</logic:notEqual>
															</c:otherwise>
														</c:choose>
													</logic:notEqual>		
													
													<!--  ########### LAYOUTBUSCADOR = BUSCADOR ##############   -->
													<logic:equal name="form" property="tipoLayoutBuscador" value="BUSCADOR">														
														<logic:equal name="form" property="usuarioLogado" value="true">																				
															<li class="comentarios_an"><a href="<html:rewrite action="/IntroducirComentariosCU/IntroducirComentariosCU.do"/>?idODE=${form.identificadorODE}&amp;tituloODE=${form.titulo}&amp;imagen=${form.imagen}&amp;idiomaODE=${form.idioma}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;busquedaSimpleAvanzada=${form.busquedaSimpleAvanzada}&amp;mostrarVuelta=${form.mostrarVuelta}&amp;urlODE=${initParam.url_buscadorDetalleCorta}&amp;valoracion=${form.valoracion}"  title="<bean:message key="listar.odecu.mostrar.resultados.detalles.comentarios"/>"><span><bean:message key="listar.odecu.mostrar.resultados.detalles.comentarios"/>(${form.nrComentarios})</span></a></li>													
														</logic:equal>
														<logic:equal name="form" property="usuarioLogado" value="false">	
															<logic:equal name="form" property="nrComentarios" value="0">															
																<li class="comentarios_an"><a title="<bean:message key="listar.odecu.mostrar.resultados.detalles.no.comentarios"/>"><span><bean:message key="listar.odecu.mostrar.resultados.detalles.comentarios"/>(${form.nrComentarios})</span></a></li>													
															</logic:equal>
															<logic:notEqual name="form" property="nrComentarios" value="0">																			
																<li class="comentarios_an"><a href="<html:rewrite action="/ListarComentariosCU/ListarComentariosCU.do"/>?idODE=${form.identificadorODE}&amp;tituloODE=${form.titulo}&amp;imagen=${form.imagen}&amp;idiomaODE=${form.idioma}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;busquedaSimpleAvanzada=${form.busquedaSimpleAvanzada}&amp;mostrarVuelta=${form.mostrarVuelta}"  title="<bean:message key="listar.odecu.mostrar.resultados.detalles.comentarios"/>" ><span><bean:message key="listar.odecu.mostrar.resultados.detalles.comentarios"/>(${form.nrComentarios})</span></a></li>													
															</logic:notEqual>
														</logic:equal>
													</logic:equal>
												
													<social:social url="/${initParam.url_buscadorDetalleCorta2}/${form.idioma}/${form.identificadorODE}" mostrarVuelta="${form.mostrarVuelta}" busquedaSimpleAvanzada="${form.busquedaSimpleAvanzada}" buscadorEmpaquetador="${form.tipoLayoutBuscador}" identificadorODE="${form.identificadorODE}" idioma="${form.idioma}" titulo="${form.titulo}" urlImagen="${form.imagen}" nodoOrigen="${form.nodoOrigen}" tieneIdentidadFederada="${form.tieneIdentidadFederada}"/>																										
										</ul>
									</div>
								</div>							
							<br />

							<!--  INICIO GLOBO Blanco   -->
			<!--  INICIO GLOBO Blanco   -->
			<div class="globo_blanco limpiar gb_ie" >

				<div class="globo_blanco_01">
					<div class="globo_blanco_02">
						<div class="globo_blanco_03">
			
											<h3><bean:message key="listar.odecu.mostrar.resultados.detalles.datos.basicos"/></h3>  	
											
												<!--*******************  TAMAÑO  **************-->
												<c:if test="${form.tamanio!=null&&form.tamanio!=''}">
												<!--  INICIO GLOBO GRIS   -->
												<div class="globo_gris_bis" id="especial011">
													<div class="globo_gris_01_bis">
														<div class="globo_gris_02_bis">

																<div class="globo_gris_03_bis">

																<table border="0" class="res_list_inv" cellpadding="0" cellspacing="0"  summary="datos">
																	<tr class="tr_blanco">
																		<td valign="top" class="dere"><span><bean:message key="listar.odecu.mostrar.resultados.detalles.tamanio"/>:</span></td>	
																		<td valign="top" ><span>${form.tamanio} <bean:message key="listar.odecu.mostrar.resultados.detalles.tamanio.mb"/></span></td>
																	
																	</tr>
																</table>
															</div>
														</div>
													</div>
												</div>		
												</c:if>		
					
												<!-- ******************* FORMATO ***********************  -->
												
												<!--  INICIO GLOBO GRIS   -->
												<div class="globo_gris_bis" id="especial021">
													<div class="globo_gris_01_bis">
														<div class="globo_gris_02_bis">
																<div class="globo_gris_03_bis">



																<table border="0" class="res_list_inv" cellpadding="0" cellspacing="0"  summary="datos">
																	<tr class="tr_blanco">
																		<td valign="top" class="dere"><span><bean:message key="listar.odecu.mostrar.resultados.detalles.formato"/>:</span></td>	
																		<td valign="top"><format:formato formato="${form.formato}" literalApli="listar.odecu.mostrar.resultados.consulta.vo.aplicacion" literalText="listar.odecu.mostrar.resultados.consulta.vo.texto" literalImag="listar.odecu.mostrar.resultados.consulta.vo.imagen" literalAud="listar.odecu.mostrar.resultados.consulta.vo.audio" literalVid="listar.odecu.mostrar.resultados.consulta.vo.video" /></td>
																	</tr>
																</table>
															</div>
														</div>
													</div>
												</div>
												
												<!--  FIN GLOBO GRIS   -->
												
												<!--****************  AMBITO *******************-->
												<c:if test="${form.ambito!=null&&form.ambito!=''}">
												
												<!--  INICIO GLOBO GRIS   -->
												<div class="globo_gris_bis conmargen" id="especial012">
													<div class="globo_gris_01_bis">
														<div class="globo_gris_02_bis">
															<div class="globo_gris_03_bis">

																<table border="0" class="res_list_inv" cellpadding="0" cellspacing="0"  summary="datos">
																	<tr class="tr_blanco">
																		<td valign="top" class="dere"><span><bean:message key="listar.odecu.mostrar.resultados.detalles.ambito"/>:</span></td>	
																		<td valign="top" ><span>${form.ambito}</span></td>
																	
																	</tr>
																</table>
															</div>
														</div>
													</div>
												</div>
												</c:if>
												<!--  FIN GLOBO GRIS   -->

												<!--  FIN GLOBO GRIS   -->

												<!--*******************  IDIOMA  ******************-->
												<c:if test="${form.idiomaODE!=null&&form.idiomaODE!=''}">
												<!--  INICIO GLOBO GRIS   -->
												<div class="globo_gris_bis conmargen" id="especial022">
													<div class="globo_gris_01_bis">
														<div class="globo_gris_02_bis">
															<div class="globo_gris_03_bis">

																<table border="0" class="res_list_inv" cellpadding="0" cellspacing="0"  summary="datos">
																	<tr class="tr_blanco">
																		<td valign="top" class="dere"><span><bean:message key="listar.odecu.mostrar.resultados.detalles.idioma"/>:</span></td>	
																		<td valign="top"><span>${form.idiomaODE}</span></td>
																	</tr>
																</table>
															</div>
														</div>
													</div>
												</div>
												</c:if>
												<!--  FIN GLOBO GRIS   -->
												
												<!--*******************  DESTINATARIOS ******************-->
												<c:if test="${form.destinatarios!=null&&form.destinatarios!=''}">
												<!--  INICIO GLOBO GRIS   -->
												<div class="globo_gris_bis conmargen" id="especial013">
													<div class="globo_gris_01_bis">

														<div class="globo_gris_02_bis">
																<div class="globo_gris_03_bis">

																<table border="0" class="res_list_inv" cellpadding="0" cellspacing="0"  summary="datos">
																	<tr class="tr_blanco">
																		<td valign="top"  class="dere"><span><bean:message key="listar.odecu.mostrar.resultados.detalles.destinatarios"/>:</span></td>	
																		<td valign="top"><span>${form.destinatarios}</span></td>
																	</tr>
																</table>	
															</div>	
														</div>
													</div>					
												</div>		
												</c:if>
												<!--  FIN GLOBO GRIS   -->

												<!--******************  LICENCIAS  *****************-->
												<c:if test="${form.licencias!=null&&form.licencias!=''}">
												<!--  INICIO GLOBO GRIS   -->
												<div class="globo_gris_bis conmargen" id="especial023">
													<div class="globo_gris_01_bis">
														<div class="globo_gris_02_bis">
															<div class="globo_gris_03_bis">
										
																<table border="0" class="res_list_inv" cellpadding="0" cellspacing="0"  summary="datos">
																	<tr class="tr_blanco">
																		<td valign="top" class="dere"><span><bean:message key="listar.odecu.mostrar.resultados.detalles.licencias"/>:</span></td>	
																		<td valign="top"><span>${form.licencias}</span></td>
																	</tr>
																</table>
															</div>
														</div>
													</div>												
												</div>
												</c:if>
												<!--  FIN GLOBO GRIS   -->
												<!--*******************  TIPOS DE RECURSOS  *********************-->
												<c:if test="${form.tipoRecursos!=null&&form.tipoRecursos!=''}">
												<!--  INICIO GLOBO GRIS   -->
												<div class="globo_gris_bis conmargen" >
													<div class="globo_gris_01_bis">
														<div class="globo_gris_02_bis">
															<div class="globo_gris_03_bis">

																<table border="0" class="res_list_inv" cellpadding="0" cellspacing="0"  summary="datos">
																	<tr class="tr_blanco">
									
																		<td valign="top"  class="dere"><span><bean:message key="listar.odecu.mostrar.resultados.detalles.tipoRecurso"/>:</span></td>	
																		<td valign="top"><p class="alineado_izq_p"><span>${form.tipoRecursos}</span></br></p></td>
																	</tr>
																</table>
															</div>
														</div>
													</div>
												</div>
												</c:if>
												<!--********************  GLOBO ORIENTACION DIDACTICA ********************-->
												<c:if test="${form.orientacionDidactica!=null&&form.orientacionDidactica!=''}">
												<!--  INICIO GLOBO GRIS   -->
													<div class="globo_gris_bis conmargen" style="margin-bottom:0;">
															<div class="globo_gris_01_bis">
																<div class="globo_gris_02_bis">
																		<div class="globo_gris_03_bis">

																<table border="0" class="res_list_inv" cellpadding="0" cellspacing="0"  summary="datos">
																	<tr class="tr_blanco">
																		<td valign="top"  class="dere"><span><bean:message key="listar.odecu.mostrar.resultados.detalles.orientacionDidactica"/>:</span></td>	
																		<td valign="top">
																		<fmt:formatNumber var="contItem" value="0" type="number"/>
																		<c:forEach items="${form.orientacionDidactica}" var="orientacion" varStatus="status">
																		<p class="alineado_izq_p">
																		<span >${orientacion}</span>
																		</br>
																		</p>
																	
																		
																		</c:forEach>
																	
																		</td>
																	</tr>
																</table>
															</div>
														</div>
													</div>
												</div>
												</c:if>
												<!--  FIN GLOBO GRIS   -->

												

											<br class="limpiar" />
											</div>

											</div>
									</div>
								</div>

								<!--  Fin GLOBO Blanco   -->

								
								
								<c:if test="${form.contribucion!=null}">
								<!--********************  CONTRIBUCIONES ********************-->
								<!--  INICIO GLOBO GRIS   -->
								<!--  INICIO GLOBO GRIS   -->
								<div class="globo_blanco" >
									<div class="globo_blanco_01">
										<div class="globo_blanco_02">
											<div class="globo_blanco_03">
		
											<!--  INICIO CAJA DE FORMULARIO   -->
												<div class="caja_dinamica caja_dinamica_especial" style="padding-bottom:5px !important"><a class="desplegado" id="pm1" href="#" onclick="expandirCaja('m1');return false;" onkeypress="expandirCaja('m1');return false;"><br class="falso" /><strong id="dm1"  ><bean:message key="configurar.avanzado.ver2"/></strong></a><h3><bean:message key="listar.odecu.mostrar.resultados.detalles.datos.contribuciones"/></h3></div>
											
													<div id="m1" class="caja_cerrada"   >
														<div class="conmargen" style="padding-bottom:8px !important">

															
													<br/>	
													<c:forEach items="${form.contribucion}" var="contribucion" varStatus="status">
															<c:if test="${contribucion.fecha!=null&&contribucion.fecha!=''}">
															<!--  INICIO GLOBO GRIS   -->
															<!--  INICIO GLOBO GRIS   -->
															<div class="globo_gris_bis conmargen" id="especial014">
																<div class="globo_gris_01_bis">
																	<div class="globo_gris_02_bis">
																		<div class="globo_gris_03_bis">
																	<table border="0" class="res_list_inv" cellpadding="0" cellspacing="0"  summary="datos">
																					<tr class="tr_blanco">	
																						<td valign="top" class="dere"><span><bean:message key="listar.odecu.mostrar.resultados.detalles.datos.contribuciones.fecha"/>:</span></td>	
																						<td valign="top"><span>${contribucion.fecha}</span></td>
																						</tr>
																					</table>
																	
																		</div>
																	</div>
																</div>

															</div>
															</c:if>
															<c:if test="${contribucion.tipoContribucion!=null&&contribucion.tipoContribucion!=''}">
																				<!--  INICIO GLOBO GRIS   -->
																				<!--  INICIO GLOBO GRIS   -->
																				<div class="globo_gris_bis conmargen" id="especial024">
																					<div class="globo_gris_01_bis">
																						<div class="globo_gris_02_bis">
																							<div class="globo_gris_03_bis">
																						<table border="0" class="res_list_inv" cellpadding="0" cellspacing="0"  summary="Rol">
																						
																						<tr class="tr_blanco">	
																						<td valign="top" class="dere"><span><bean:message key="listar.odecu.mostrar.resultados.detalles.datos.contribuciones.tipo"/>:</span></td>	
																						<td valign="top"><span>${contribucion.tipoContribucion}</span></td>
																					</tr>
																					</table>
															
																		</div>
																	</div>
																</div>

															</div>						
																						
															</c:if>
																		
																<c:if test="${contribucion.entidades!=null}">
																				<c:forEach items="${contribucion.entidades}" var="entidad" varStatus="status">
																					
																<c:if test="${entidad.nombre!=null && entidad.nombre!=''}">
																				<!--  INICIO GLOBO GRIS   -->
																					<!--  INICIO GLOBO GRIS   -->
																				<div class="globo_gris_bis conmargen" >
																						<div class="globo_gris_01_bis">
																							<div class="globo_gris_02_bis">
																								<div class="globo_gris_03_bis">
																				<table border="0" class="res_list_inv" cellpadding="0" cellspacing="0" summary="Nombre">
																				
																				<tr class="tr_blanco">	
																						<td valign="top" class="dere"><span><bean:message key="listar.odecu.mostrar.resultados.detalles.datos.entidad.nombre"/>:</span></td>	
																						<td valign="top"><span>${entidad.nombre}</span></td>
																				</tr>
																				</table>

																				</div>
																				</div>
																				</div>
																				</div>
																				<!--  FIN GLOBO GRIS   -->
																				<!--  FIN GLOBO GRIS   -->
																				
																</c:if>
																<c:if test="${entidad.organizacion!=null&&entidad.organizacion!=''}">
																				<!--  INICIO GLOBO GRIS   -->
																				<!--  INICIO GLOBO GRIS   -->
																				<div class="globo_gris_bis conmargen" >
																					<div class="globo_gris_01_bis">
																						<div class="globo_gris_02_bis">

																							<div class="globo_gris_03_bis">
																							<table border="0" class="res_list_inv" cellpadding="0" cellspacing="0" summary="Organización">
																				
																				<tr class="tr_blanco">	
																						<td valign="top" class="dere"><span><bean:message key="listar.odecu.mostrar.resultados.detalles.datos.entidad.organizacion"/>:</span></td>	
																						<td valign="top"><span>${entidad.organizacion}</span></td>
																				</tr>
																				</table>
																								</div>
																						</div>

																					</div>
																				</div>
																			<!--  FIN GLOBO GRIS   -->
																			<!--  FIN GLOBO GRIS   -->
																</c:if>
																				</c:forEach>
																				</c:if>
																		</c:forEach>	
																			</div>
																</div>
								<!--  FIN CAJA DE FORMULARIO   -->
														</div>
												</div>

											</div>
									</div>
<!--  FIN GLOBO GRIS  -->
<!--  FIN GLOBO GRIS   -->

																
															
													</c:if>

												<!--*******************  PALABRAS CLAVE  *************************-->
												<c:if test="${form.palabrasClave!=null&&form.palabrasClave!=''}">
												<!--  INICIO GLOBO GRIS   -->
													<!--  INICIO GLOBO Blanco   -->
												<!--  INICIO GLOBO Blanco   -->
												<div class="globo_blanco limpiar gb_ie" >
														<div class="globo_blanco_01">
																<div class="globo_blanco_02">
																	<div class="globo_blanco_03">
																	<h3><bean:message key="listar.odecu.mostrar.resultados.detalles.palabrasClave"/></h3>
																<!--  INICIO GLOBO GRIS   -->
												<!--  INICIO GLOBO GRIS   -->
														<div class="globo_gris_bis" >
															<div class="globo_gris_01_bis">
																<div class="globo_gris_02_bis">
																	<div class="globo_gris_03_bis">
																			<table border="0" class="res_list_inv" cellpadding="0" cellspacing="0"  summary="Palabras Clave">

																	<tr class="tr_blanco">
																		<td valign="top" class="alineado_izq">
																		
																		<fmt:formatNumber var="contItem" value="1" type="number"/>
																		<fmt:formatNumber var="total" value="${fn:length(form.palabrasClave)}" type="number"/>
																		<c:forEach items="${form.palabrasClave}" var="palabrasClave" varStatus="status">
																		<c:if test="${contItem<total}">	
																		<span>${palabrasClave},</span>
																		</c:if>
																		<c:if test="${contItem==total}">	
																		<span>${palabrasClave}</span>
																		</c:if>
																		<c:set var="contItem" value="${contItem+1}"/>
																		</c:forEach>
																		</td>
																	</tr>
																</table>
																		</div>
																	</div>
															</div>
														</div>
												<br class="limpiar" />
	
										<!--  FIN GLOBO GRIS   -->
											<!--  FIN GLOBO GRIS   -->
											</div>

											</div>
										</div>
									</div>
								<!--  Fin GLOBO Blanco   -->
								<!--  Fin GLOBO Blanco   -->
								</c:if>
								<logic:equal name="form" property="existenEtiquetas" value="true">
									<!--  INICIO GLOBO Blanco   -->
									<div class="globo_blanco limpiar gb_ie" >
										<div class="globo_blanco_01">
											<div class="globo_blanco_02">
												<div class="globo_blanco_03">						
													<h3><bean:message key="listar.odecu.mostrar.resultados.detalles.datos.etiquetas"/></h3>

													<!--  INICIO GLOBO GRIS   -->
													<div class="globo_gris_bis" >
														<div class="globo_gris_01_bis">
															<div class="globo_gris_02_bis">
																<div class="globo_gris_03_bis">
																	<table border="0" class="res_list_inv" cellpadding="0" cellspacing="0"  summary="datos">
																		<tr class="tr_blanco">
																			<td valign="top" class="alineado_izq">
																				<c:forEach items="${form.etiquetas}" var="etiqueta" varStatus="status">
																					<bean:define id="tag" ><c:out value="${etiqueta}" escapeXml="true" default="" /></bean:define>
																					<a href="<rewrite:rewrite url="TaggingWeb/ListarAgregaTags/ListadoMostrarOdesAgrega.do"/>?tag=${tag }">${etiqueta }</a>
																				</c:forEach>
																			</td>	
																		</tr>
																	</table>
																</div>
															</div>
														</div>
													</div>
													<br class="limpiar" />
													
													<!--  FIN GLOBO GRIS   -->
	
												</div>
											</div>
										</div>
									</div>
								</logic:equal>
								<!--  Fin GLOBO Blanco   -->
								
								<!--  INICIO GLOBO Blanco   -->
								<div class="globo_blanco limpiar gb_ie" >
									<div class="globo_blanco_01">
										<div class="globo_blanco_02">
											<div class="globo_blanco_03">											
												<h3><bean:message key="listar.odecu.mostrar.resultados.detalles.datos.estadisticas"/></h3>

												<!--********************  NUMERO VECES PREVISUALIZADO ********************-->
												<c:if test="${form.numVecesPrevisualizado!=null&&form.numVecesPrevisualizado!=''}">
												<!--  INICIO GLOBO GRIS   -->
												<div class="globo_gris_bis" id="especial01">
													<div class="globo_gris_01_bis">
														<div class="globo_gris_02_bis">
															<div class="globo_gris_03_bis" >
																<table border="0" class="res_list_inv" cellpadding="0" cellspacing="0"  summary="datos">
																	<tr class="tr_blanco">
																		<td valign="top" class="dere0"><span><bean:message key="listar.odecu.mostrar.resultados.detalles.previsualizado"/>:</span></td>	
																		<td valign="top" align="right" class="dere1"><span>${form.numVecesPrevisualizado}</span></td>
																	</tr>
																</table>
															</div>
														</div>
													</div>
												</div>
												</c:if>
												<!--  FIN GLOBO GRIS   -->

												<!--*******************  NUMERO VECES CONSULTADO  *************************-->
												<c:if test="${form.numVecesVisto!=null&&form.numVecesVisto!=''}">
												<!--  INICIO GLOBO GRIS   -->
												<div class="globo_gris_bis" id="especial02">
													<div class="globo_gris_01_bis">
														<div class="globo_gris_02_bis">
															<div class="globo_gris_03_bis" >
																<table border="0" class="res_list_inv" cellpadding="0" cellspacing="0"  summary="datos">
																	<tr class="tr_blanco">
																		<td valign="top" class="dere0"><span><bean:message key="listar.odecu.mostrar.resultados.detalles.visto"/>:</span></td>	
																		<td valign="top" align="right" class="dere1"><span>${form.numVecesVisto}</span></td>
																	</tr>
																</table>
															</div>
														</div>
													</div>
												</div>
												</c:if>
												<!--  FIN GLOBO GRIS   -->

												<!--*******************  NUMERO VECES DESCARGADO  *********************-->
												<c:if test="${form.numVecesDescargado!=null&&form.numVecesDescargado!=null}">
												<!--  INICIO GLOBO GRIS   -->
												<div class="globo_gris_bis" id="especial03">
													<div class="globo_gris_01_bis">
														<div class="globo_gris_02_bis">
															<div class="globo_gris_03_bis" >
																<table border="0" class="res_list_inv" cellpadding="0" cellspacing="0"  summary="datos">
																	<tr class="tr_blanco">
																		<td valign="top" class="dere0"><span><bean:message key="listar.odecu.mostrar.resultados.detalles.descargado"/>:</span></td>	
																		<td valign="top" align="right" class="dere1"><span>${form.numVecesDescargado}</span></td>
																	</tr>
																</table>
															</div>
														</div>
													</div>
												</div>
												</c:if>
												<!--  FIN GLOBO GRIS   -->

												<!--**********************  NUMERO VECES ENVIADO  *********************-->
												<c:if test="${form.numVecesEnviado!=null&&form.numVecesEnviado!=null}">
												<!--  INICIO GLOBO GRIS   -->
												<div class="globo_gris_bis" id="especial04">
													<div class="globo_gris_01_bis">
														<div class="globo_gris_02_bis">
															<div class="globo_gris_03_bis" >
																<table border="0" class="res_list_inv" cellpadding="0" cellspacing="0"  summary="datos">
																	<tr class="tr_blanco">
																		<td valign="top" class="dere0"><span><bean:message key="listar.odecu.mostrar.resultados.detalles.enviado"/>:</span></td>	
																		<td valign="top" align="right" class="dere1"><span>${form.numVecesEnviado}</span></td>
																	</tr>
																</table>
															</div>
														</div>
													</div>
												</div>
												</c:if>
												<!--**********************  NUMERO VECES EMBED  *********************-->
												<c:if test="${form.numVecesEmbed!=null&&form.numVecesEmbed!=null}">
												<!--  INICIO GLOBO GRIS   -->
												<div class="globo_gris_bis" id="especial04">
													<div class="globo_gris_01_bis">
														<div class="globo_gris_02_bis">
															<div class="globo_gris_03_bis" >
																<table border="0" class="res_list_inv" cellpadding="0" cellspacing="0"  summary="datos">
																	<tr class="tr_blanco">
																		<td valign="top" class="dere0"><span><bean:message key="listar.odecu.mostrar.resultados.detalles.embed"/>:</span></td>	
																		<td valign="top" align="right" class="dere1"><span>${form.numVecesEmbed}</span></td>
																	</tr>
																</table>
															</div>
														</div>
													</div>
												</div>
												</c:if>
												
												
			
												<br class="limpiar" />
												<br class="ie_2" />			
												<!--  FIN GLOBO GRIS   -->

											</div>
										</div>
									</div>
								</div>
								<!--  Fin GLOBO Blanco   -->

								
									<!--  INICIO GLOBO Blanco   -->
									<logic:equal name="form" property="existenOdesRelacionados" value="true">
									<div class="globo_blanco limpiar gb_ie" >
										<div class="globo_blanco_01">
											<div class="globo_blanco_02">
												<div class="globo_blanco_03">						
													<h3><bean:message key="listar.odecu.mostrar.resultados.detalles.odesRelacionados"/></h3>
													 <c:set var="cont" value="1"/>
														<logic:iterate name="form" property="odesRelacionados" id="resultados">
															<div class="globo_gris_bis" id="especial001${cont}">
																<div class="globo_gris_01_bis">
																	<div class="globo_gris_02_bis">
																		<div class="globo_gris_03_bis">
																			<a href="<html:rewrite action="/DetallarODECU/MostrarDetalleODEDetalleOdeRelacionado"/>?idioma=${form.idioma}&amp;identificadorODE=${resultados.id}&amp;tieneIdentidadFederada=${form.tieneIdentidadFederada}&amp;nodoOrigen=${form.nodoOrigen}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;posicionamientoAnterior=&amp;posicionamientoSiguiente=" class="img_tab_02" /><img  src="${resultados.urlImagen}" alt="Imagen 02"/></a>
																			<div class="texto_foto_peq">
																				<a 
																				href="<html:rewrite action="/DetallarODECU/MostrarDetalleODEDetalleOdeRelacionado"/>?idioma=${form.idioma}&amp;identificadorODE=${resultados.id}&amp;tieneIdentidadFederada=${form.tieneIdentidadFederada}&amp;nodoOrigen=${form.nodoOrigen}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;posicionamientoAnterior=&amp;posicionamientoSiguiente=" 
																				class="max_"
																				title="${resultados.titulo}" />
																				<c:choose>
																					<c:when test="${(fn:length(resultados.titulo)) < 17}">
																						${resultados.titulo}
																					</c:when>
																					<c:otherwise>
																						${fn:substring(resultados.titulo,0,12)}...
																					</c:otherwise>
																				</c:choose>
																				</a>
																				<br />
																				<a target="_blank" href="<html:rewrite action="/BuscarAvanzadoCU/MostrarResultadosImagenesPrevisualizarODE"/>?idioma=${form.idioma}&amp;identificadorODE=${resultados.id}&amp;nodoDestino=${resultados.nodo}&amp;titulo=${resultados.titulo}&amp;seleccionarSecuencia=${resultados.conSecuencia}&amp;tieneIdentidadFederada=${form.tieneIdentidadFederada}&amp;nodoOrigen=${form.nodoOrigen}" class="min"><bean:message key="listar.odecu.mostrar.resultados.detalles.previsualizar"/></a>					
																				<estrellas:valoracion valoracion="${resultados.valoracion}" pagina="detallar" mensaje="listar.odecu.mostrar.resultados.detalles.valoracion"/>																		
																			</div>
																			<br class="limpiar" />
																		</div>
																	</div>
																</div>
															</div>
														<c:set var="cont" value="${cont+1}"/>


<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
													</logic:iterate>
																				
													<br class="limpiar" />
											</div>
										</div>
									</div>
								</div>
<!--  Fin GLOBO Blanco   -->
<!--  Fin GLOBO Blanco   -->
								</logic:equal>
								<!--  Fin GLOBO Blanco   -->
								
</div>								
								
								
							


							<!-- Inicio Botones  -->
							<div id="botones_centrados">
								<!-- Botón de metadatos -->
								<span class="oculto">-</span>
								<logic:equal name="form" property="usuarioLogado" value="true">	
									<a id="b_metadatos" target="_blank" href="<html:rewrite action="/DetallarODECU/MostrarDetalleODEConsultarMetadatos.do"/>?identificadorODE=${form.identificadorODE}"><bean:message key="listar.odecu.mostrar.resultados.detalles.metadatos"/></a>
								</logic:equal>	
								<br class="oculto" />
								
								<!-- Botón de descargar -->
								<span class="oculto">-</span>						
									<logic:equal name="form" property="busquedaSimpleAvanzada" value="BUSQUEDA_ARBOL">
										<a href="<html:rewrite action="/DescargarODECU/DescargarODECU.do"/>?identificadorODE=${form.identificadorODE}&amp;idioma=${form.idioma}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;mostrarVuelta=${form.mostrarVuelta}&amp;areaCurricularBusqueda=&amp;tieneIdentidadFederada=${form.tieneIdentidadFederada}&amp;nodoOrigen=${form.nodoOrigen}&amp;idLicencia=${form.idLicencia}" id="b_descargar" ><bean:message key="listar.odecu.mostrar.resultados.detalles.descargar"/></a>
									</logic:equal>
									<logic:notEqual name="form" property="busquedaSimpleAvanzada" value="BUSQUEDA_ARBOL">	
										<a href="<html:rewrite action="/DescargarODECU/DescargarODECU.do"/>?identificadorODE=${form.identificadorODE}&amp;idioma=${form.idioma}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;mostrarVuelta=${form.mostrarVuelta}&amp;tieneIdentidadFederada=${form.tieneIdentidadFederada}&amp;nodoOrigen=${form.nodoOrigen}&amp;idLicencia=${form.idLicencia}" id="b_descargar" ><bean:message key="listar.odecu.mostrar.resultados.detalles.descargar"/></a>
									</logic:notEqual>
								<br class="oculto" />
								
								<!-- Botón de previsualizar -->
								<span class="oculto">-</span>
								<a id="b_previsualizar" target="_blank" href="<html:rewrite action="/DetallarODECU/MostrarDetalleODEPrevisualizar.do"/>?identificadorODE=${form.identificadorODE}&amp;seleccionarSecuencia=${form.seleccionarSecuencia}&amp;idioma=${form.idioma}&amp;tieneIdentidadFederada=${form.tieneIdentidadFederada}&amp;nodoOrigen=${form.nodoOrigen}"><bean:message key="listar.odecu.mostrar.resultados.detalles.previsualizar"/></a>															
								
								<logic:equal name="form" property="tipoLayoutBuscador" value="EMPAQUETADOR">
									<logic:equal name="form" property="agregarFederado" value="false">							
										<a id="b_metadatos" href="<html:rewrite action="/DetallarODECU/MostrarDetalleODEAgregacionOde.do"/>?identificadorODE=${form.identificadorODE}&amp;titulo=${form.titulo}&amp;idioma=${form.idioma}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}"  ><bean:message key="listar.odecu.mostrar.resultados.detalles.agregar"/></a>
									</logic:equal>
								</logic:equal>
								<br class="oculto" />
							</div>
							<logic:equal name="form" property="usuarioLogado" value="true">	
							<logic:equal name="form" property="usuarioValido" value="true">	
								<div id="botones_centrados">
									<span class="oculto">-</span>
									<a href="<html:rewrite action="/AnadirCarpetaPersonalCU/AnadirCarpetaPersonalCU.do"/>?identificadorODE=${form.identificadorODE}&amp;idiomaODE=${form.idioma}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;busquedaSimpleAvanzada=${form.busquedaSimpleAvanzada}&amp;mostrarVuelta=${form.mostrarVuelta}" id="b_metadatos"><bean:message key="anadir.carpeta.personal.boton"/></a>
									<br class="oculto" />
									<!-- Botón para Crear nueva versión -->
									<logic:equal name="form" property="permisoNuevaVersion" value="true">
										<span class="oculto">-</span>									
										<a href="<html:rewrite action="/AnadirCarpetaPersonalCU/AnadirCarpetaPersonalCU.do"/>?identificadorODE=${form.identificadorODE}&amp;idiomaODE=${form.idioma}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;busquedaSimpleAvanzada=CARPETA_PERSONAL&amp;mostrarVuelta=false" id="b_metadatos"><bean:message key="crear.nueva.version.boton"/></a>
										<br class="oculto" />
									</logic:equal>										
								</div>
								</logic:equal>
							</logic:equal>
							<!-- Fin Botones  -->
							<!-- Fin Botones  -->	
							<br class="limpiar" />
						</div>
					</div>
				</div>
			</div>
		
			<!--  FIN GLOBO GRIS   -->


		<!-- Inicio Botones  -->
		<fieldset class="tipo ft_centrada">
			<logic:equal name="form" property="existeSesion" value="true">
				<logic:equal name="form" property="mostrarVuelta" value="true">
					<logic:notEmpty name="form" property="posicionamientoSiguiente">
						<form action="<html:rewrite action="/DetallarODECU/MostrarDetalleODEGestionarPosicionado.do"/>" method="post">															
							<input class="boton_125_de_2" id="bot_flot_der" type="submit" value="<bean:message key="listar.odecu.mostrar.resultados.detalles.siguiente"/>" />
							<input type="hidden" name="idioma" value="${form.idioma}"/>
							<input type="hidden" name="nodoOrigen" value="${form.nodoOrigen}"/>
							<input type="hidden" name="identificadorODE" value="${form.identificadorODE}"/>
							<input type="hidden" name="posicionamiento" value="${form.posicionamientoSiguiente}"/>
							<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>
						</form>
					</logic:notEmpty>
					<logic:notEmpty name="form" property="posicionamientoAnterior">
						<form action="<html:rewrite action="/DetallarODECU/MostrarDetalleODEGestionarPosicionado.do"/>" method="post">															
							<input class="boton_125_de_2_izq" id="bot_flot_izq" type="submit" value="<bean:message key="listar.odecu.mostrar.resultados.detalles.anterior"/>" />
							<input type="hidden" name="idioma" value="${form.idioma}"/>
							<input type="hidden" name="nodoOrigen" value="${form.nodoOrigen}"/>
							<input type="hidden" name="identificadorODE" value="${form.identificadorODE}"/>
							<input type="hidden" name="posicionamiento" value="${form.posicionamientoAnterior}"/>
							<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>
						</form>
					</logic:notEmpty>
					<logic:equal name="form" property="existeSesion" value="true">
				
						<logic:equal name="form" property="busquedaSimpleAvanzada" value="BUSQUEDA_ARBOL">
							<form action="<html:rewrite action="/DetallarODECU/MostrarDetalleODEVolverBusqueda.do"/>?tipoBusquedaArbol=" method="post">															
								<input class="boton_125" id="ancho210" type="submit" value="<bean:message key="listarODE.arbolCurricular.resultados.busqueda"/>" />
							</form>
						</logic:equal>
						<logic:notEqual name="form" property="busquedaSimpleAvanzada" value="BUSQUEDA_ARBOL">
							<logic:notEqual name="form" property="busquedaSimpleAvanzada" value="">
								<form action="<html:rewrite action="/DetallarODECU/MostrarDetalleODEVolverBusqueda.do"/>" method="post">															
									<input class="boton_125" id="ancho210" type="submit" value="<bean:message key="listarODE.arbolCurricular.resultados.busqueda"/>" />
								</form>
							</logic:notEqual>
						</logic:notEqual>
					</logic:equal>
				</logic:equal>
			</logic:equal>
		</fieldset>						
		<!-- Fin Botones  -->
	</form>
						
	</div>
<!-- Fin plantilla contenido  -->
<!-- Fin plantilla contenido  -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->

<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->


  </tiles:put>
</tiles:insert>
<SCRIPT LANGUAGE="JavaScript">
var ocultar="<bean:message key='configurar.avanzado.ocultar2'/>";
var ver="<bean:message key='configurar.avanzado.ver2'/>";
function expandirCaja (i) 
	{
		if (document.getElementById(i).className=='caja_abierta') 
		{
			document.getElementById('p' + i).className = 'caja_cerrada';
			document.getElementById('d'+i).innerHTML=ver;
			document.getElementById(i).className = 'caja_cerrada';
		}
		else 
		{
			document.getElementById('p' + i).className = 'caja_abierta';
			document.getElementById('d'+i).innerHTML=ocultar;
			document.getElementById(i).className = 'caja_abierta';
		}
	}
</SCRIPT>

