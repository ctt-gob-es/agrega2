<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<tiles:insert definition="layout-administrador">
<tiles:put name="title" type="string">
	<bean:message key="title.Comun"/>
</tiles:put>
<tiles:put name="body" type="string">

<link rel="stylesheet" media="screen" href="<rewrite:rewrite url="static/css/hoja_de_estilo.css"/>" type="text/css" />

<script type="text/javascript" src="<rewrite:rewrite url="static/js/plantilla.js"/>"></script>



<%@ include file="/taglib-imports.jspf" %>

<%@ include file="/es/pode/administracion/presentacion/configuracionPortal/configuracion/configuracion-portal-vars.jspf" %>



<!--  INICIO CAPA MADRE   -->
<div id="capa_madre">


<!-- Inicio plantilla contenido  -->
<div class="plantilla_contenido">
<!-- ## Para pintar los mensajes de error ## -->
	<jsp:include page="/layout/messages.jsp" flush="true" />
<h2><bean:message key="modificar.config.formulario.cabecera"/></h2>

<form method="post" action="<html:rewrite action="/ConfiguracionPlataformaCU/ConfiguracionPlataformaSubmit.do"/>">	


<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
				<!--  INICIO CAJA DE FORMULARIO   -->
				<div class="formu">
					<h3>Configuración de la plataforma</h3>
					
					<c:if test="${fn:length(form.listaVariables) < 1}" > 
						<p>No se han recibido variables que mostrar. Esto puede deberese a que no 
						está correctamente configurada la conexión con la base de datos, o que en la base de datos
						no hay ninguna variable registrada.</p><br>
					</c:if> 	
					<c:if test="${fn:length(form.listaVariables) > 0}" >
						
						<c:forEach items="${form.listaVariables}" var="variable" varStatus="status">	
						
							<!--  INICIO GLOBO BLANCO   -->
							<!--  INICIO GLOBO BLANCO   -->
							<div class="globo_blanco" >
								<div class="globo_blanco_01">
									<div class="globo_blanco_02">
										<div class="globo_blanco_03 tab_min">
											<div class="formu" >
												<table border="0" class="tab_miniaturas" cellpadding="0" cellspacing="0" >
												<tr >
													<td valign="top" class="opcion_tab_01">
													<h3>${variable.nombre}</h3>
													<c:if test="${variable.descripcion!=''}">	
														${variable.descripcion}.<br>
													</c:if> 
													<br>
													<c:choose> 
													
														<c:when test="${variable.nombre=='catalogo_agrega'}">
															<c:if test="${variable.ejemplo!=''}">
																<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
															</c:if> 
															<br>
															<label for="${variable.nombre}" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
															<input type="text" name="${variable.nombre}" value="${form.catalogo_agrega}" id="${variable.nombre}" style="margin-bottom:0 !important;" title="${variable.nombre}" size="75"><br />
														</c:when>
														
														<c:when test="${variable.nombre=='catalogo_mec'}">
															<c:if test="${variable.ejemplo!=''}">
																<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
															</c:if> 
															<br>
															<label for="${variable.nombre}" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
															<input type="text" name="${variable.nombre}" value="${form.catalogo_mec}" id="${variable.nombre}" style="margin-bottom:0 !important;" title="${variable.nombre}" size="75"><br />
														</c:when>													
														
														<c:when test="${variable.nombre=='check_password'}">
															<tr >
																<td valign="top" class="opcion_tab_01">
																	<c:choose> 
																		<c:when test="${form.check_password=='true'}">	
																			<input type="radio" name="${variable.nombre}" value="true" checked="checked" id="${variable.nombre}">
																			<label for="${variable.nombre}">Si</label>
																			<input type="radio" name="${variable.nombre}" value="false" id="${variable.nombre}" style="margin-left:15px;">
																			<label for="${variable.nombre}">No</label>
																		</c:when>	
																		<c:when test="${form.check_password=='false'}">	
																			<input type="radio" name="${variable.nombre}" value="true" id="${variable.nombre}">
																			<label for="${variable.nombre}">Si</label>
																			<input type="radio" name="${variable.nombre}" value="false" checked="checked" id="${variable.nombre}" style="margin-left:15px;">
																			<label for="${variable.nombre}">No</label>
																		</c:when>	
																		<c:otherwise>
																			<input type="radio" name="${variable.nombre}" value="true" id="${variable.nombre}">
																			<label for="${variable.nombre}">Si</label>
																			<input type="radio" name="${variable.nombre}" value="false" id="${variable.nombre}" style="margin-left:15px;">
																			<label for="${variable.nombre}">No</label>
																		</c:otherwise>
																	</c:choose>
																</td>
															</tr>
														</c:when>
														
														<c:when test="${variable.nombre=='emailAdmin'}">
															<c:if test="${variable.ejemplo!=''}">
																<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
															</c:if> 
															<br>
															<label for="${variable.nombre}" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
															<input type="text" name="${variable.nombre}" value="${form.emailAdmin}" id="${variable.nombre}" style="margin-bottom:0 !important;" title="${variable.nombre}" size="75"><br />
														</c:when>	
														
														<c:when test="${variable.nombre=='indexServer_port'}">
															<c:if test="${variable.ejemplo!=''}">
																<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
															</c:if> 
															<br>
															<label for="${variable.nombre}" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
															<input type="text" name="${variable.nombre}" value="${form.indexServer_port}" id="${variable.nombre}" style="margin-bottom:0 !important;" title="${variable.nombre}" size="6" maxlength="6"><br />
														</c:when>	
														
														<c:when test="${variable.nombre=='indexServer_url'}">
															<c:if test="${variable.ejemplo!=''}">
																<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
															</c:if> 
															<br>
															<label for="${variable.nombre}" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
															<input type="text" name="${variable.nombre}" value="${form.indexServer_url}" id="${variable.nombre}" style="margin-bottom:0 !important;" title="${variable.nombre}" size="75"><br />
														</c:when>
														
														<c:when test="${variable.nombre=='ldap_external_admin'}">
															<c:if test="${variable.ejemplo!=''}">
																<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
															</c:if> 
															<br>
															<label for="${variable.nombre}" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
															<input type="text" name="${variable.nombre}" value="${form.ldap_external_admin}" id="${variable.nombre}" style="margin-bottom:0 !important;" title="${variable.nombre}" size="75"><br />
														</c:when>
														
														<c:when test="${variable.nombre=='numDescargasMostradasEnResumen'}">
															<c:if test="${variable.ejemplo!=''}">
																<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
															</c:if> 
															<br>
															<label for="${variable.nombre}" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
															<input type="text" name="${variable.nombre}" value="${form.numDescargasMostradasEnResumen}" id="${variable.nombre}" style="margin-bottom:0 !important;" title="${variable.nombre}" size="2" maxlength="2"><br />
														</c:when>	
														
														<c:when test="${variable.nombre=='numNoticiasMostradasEnResumen'}">
															<c:if test="${variable.ejemplo!=''}">
																<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
															</c:if> 
															<br>
															<label for="${variable.nombre}" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
															<input type="text" name="${variable.nombre}" value="${form.numNoticiasMostradasEnResumen}" id="${variable.nombre}" style="margin-bottom:0 !important;" title="${variable.nombre}" size="2" maxlength="2"><br />
														</c:when>	
														
														<c:when test="${variable.nombre=='rest_resultados_por_pagina'}">
															<c:if test="${variable.ejemplo!=''}">
																<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
															</c:if> 
															<br>
															<label for="${variable.nombre}" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
															<input type="text" name="${variable.nombre}" value="${form.rest_resultados_por_pagina}" id="${variable.nombre}" style="margin-bottom:0 !important;" title="${variable.nombre}" size="6" maxlength="6"><br />
														</c:when>											
														
														<c:when test="${variable.nombre=='secuencia_sin_logar'}">
															<tr >
																<td valign="top" class="opcion_tab_01">
																	<c:choose> 
																		<c:when test="${form.secuencia_sin_logar=='true'}">	
																			<input type="radio" name="${variable.nombre}" value="true" checked="checked" id="${variable.nombre}">
																			<label for="${variable.nombre}">Si</label>
																			<input type="radio" name="${variable.nombre}" value="false" id="${variable.nombre}" style="margin-left:15px;">
																			<label for="${variable.nombre}">No</label>
																		</c:when>	
																		<c:when test="${form.secuencia_sin_logar=='false'}">	
																			<input type="radio" name="${variable.nombre}" value="true" id="check_password">
																			<label for="${variable.nombre}">Si</label>
																			<input type="radio" name="${variable.nombre}" value="false" checked="checked" id="${variable.nombre}" style="margin-left:15px;">
																			<label for="${variable.nombre}">No</label>
																		</c:when>	
																		<c:otherwise>
																			<input type="radio" name="${variable.nombre}" value="true" id="${variable.nombre}">
																			<label for="${variable.nombre}">Si</label>
																			<input type="radio" name="${variable.nombre}" value="false" id="${variable.nombre}" style="margin-left:15px;">
																			<label for="${variable.nombre}">No</label>
																		</c:otherwise>
																	</c:choose>
																</td>
															</tr>
														</c:when>
													
														<c:when test="${variable.nombre=='valorCuotaDefecto'}">
															<c:if test="${variable.ejemplo!=''}">
																<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
															</c:if> 
															<br>
															<label for="${variable.nombre}" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
															<input type="text" name="${variable.nombre}" value="${form.valorCuotaDefecto}" id="${variable.nombre}" style="margin-bottom:0 !important;" title="${variable.nombre}" size="6" maxlength="6"><br />
														</c:when>	
														
														<c:when test="${variable.nombre=='vistaPreviaAgrega'}">
															<c:if test="${variable.ejemplo!=''}">
																<strong>Ejemplo/s:</strong> <em>${variable.ejemplo}</em><br>
															</c:if> 
															<br>
															<label for="${variable.nombre}" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
															<input type="text" name="${variable.nombre}" value="${form.vistaPreviaAgrega}" id="${variable.nombre}" style="margin-bottom:0 !important;" title="${variable.nombre}" size="75"><br />
														</c:when>
														
													</c:choose>
													</td>
												</tr>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!--  FIN GLOBO BLANCO   -->
							<!--  FIN GLOBO BLANCO   -->
						
						
						</c:forEach>
					</c:if> 	

					
				<!--  FIN CAJA DE FORMULARIO   -->
				</div>
			</div>
		</div>
	</div>
</div>






<!-- Inicio Botones  -->
<!-- Inicio Botones  -->
<fieldset class="tipo ft_centrada">
<input class="boton_125"  type="submit"  value="<bean:message key="modificar.config.formulario.guardar"/>" />
</fieldset>
<!-- Fin Botones  -->
	
</form>
</div>
<!-- Fin plantilla contenido  -->
<!-- Fin plantilla contenido  -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->


</div>

</tiles:put>
</tiles:insert>
