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
	
<div id="ficha_pestanias">
<div><h2><bean:message key="modificar.config.formulario.cabecera"/></h2></div>
	<ul>
		<c:forEach items="${form.listaCategorias}" var="categoria" varStatus="status">	
			<c:if test="${categoria==form.categoria}">
				<li id="pest_activa"><a id="seleccionada" href="/portaladministracion/ConfiguracionPlataformaCU/ConfiguracionPlataformaCU.do?categoria=${categoria}">${categoria}</a></li>
			</c:if>
			<c:if test="${categoria!=form.categoria}">
				<li><a href="/portaladministracion/ConfiguracionPlataformaCU/ConfiguracionPlataformaCU.do?categoria=${categoria}">${categoria}</a></li>
			</c:if>
		</c:forEach>
		
	</ul>
</div>

<div class="interno_ficha">
<div class="plantilla_contenido_pestanias" style="
												width:716px !important;     
												border-bottom-left-radius: 5px;
											    border-bottom-right-radius: 5px;
											    padding: 16px 21px 10px 7px !important;">
<fieldset>

<form method="post" action="<html:rewrite action="/ConfiguracionPlataformaCU/ConfiguracionPlataformaSubmit.do"/>">	


<input type="hidden" name="categoria" value="${form.categoria}"/>


<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03" style="
											padding:12px 0px 8px 0px !important;
											background: url('../img/azul/esq_blanco_bot_der.gif');
											background-image: url('../img/azul/esq_blanco_bot_der.gif');
											background-repeat: no-repeat;
											background-attachment: scroll 100% 100% transparent;">
				<!--  INICIO CAJA DE FORMULARIO   -->
				<div class="formu" style="
										margin: 0px 8px !important;
										display: block;
 										font-size: 70%;
 										width: auto;">
					
					<c:if test="${fn:length(form.listaPropiedades) < 1}" > 
						<p>No se han recibido propiedades que mostrar. Esto puede deberese a que no 
						está correctamente configurada la conexión con la base de datos, o que en la base de datos
						no hay ninguna propiedad registrada.</p><br>
					</c:if> 	
					<c:if test="${fn:length(form.listaPropiedades) > 0}" >
										
						<c:forEach items="${form.listaPropiedades}" var="propiedad" varStatus="status">
							
							<c:if test="${status.index==0}">
													<h3>${propiedad.subcategoria}</h3>
							</c:if>
							<c:if test="${status.index>0}">
								<fmt:formatNumber var="lastIndex" value="${status.index-1}" type="number"/>
								<c:if test="${form.listaPropiedades[lastIndex].subcategoria!=propiedad.subcategoria}">	
									
													<!--  FIN CAJA DE FORMULARIO   -->
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="globo_gris conmargen" style="margin-top:17px !important">
										<div class="globo_gris_01">
											<div class="globo_gris_02">
												<div class="globo_gris_03" style="
																				padding:12px 0px 8px 0px !important;
																				background: url('../img/azul/esq_blanco_bot_der.gif');
																				background-image: url('../img/azul/esq_blanco_bot_der.gif');
																				background-repeat: no-repeat;
																				background-attachment: scroll 100% 100% transparent;">
													<!--  INICIO CAJA DE FORMULARIO   -->
													<div class="formu" style="
																			margin: 0px 8px !important;
																			display: block;
						    												font-size: 70%;
						    												width: auto;">
														<h3>${propiedad.subcategoria}</h3>
								</c:if> 	
							</c:if>
							
							
							<!--  INICIO GLOBO BLANCO   -->
							<!--  INICIO GLOBO BLANCO   -->
							<div class="globo_blanco" >
								<div class="globo_blanco_01">
									<div class="globo_blanco_02">
										<div class="globo_blanco_03 tab_min" style="
																				padding:12px 0px 8px 0px !important; 
																				background-color:white !important;
																				background:#ffffff !important">
											<div class="formu" style="
																	margin: 0px 8px !important;
																	display: block;
				    												font-size: 70%;
				    												width: auto;">
												<table border="0" class="tab_miniaturas" cellpadding="0" cellspacing="0" >
												<tr >
												
													<td valign="top" class="opcion_tab_01" style="border:0px !important; padding:0px 0px 0px 0px !important;">
													
													<input type="hidden" name="nombresPropiedades" value="${propiedad.nombre}"/>
													<input type="hidden" name="instanciaJboss" value="${propiedad.instanciaJboss}"/>
													
													<c:if test="${propiedad.instanciaJboss!='global'}">	
														<strong>${propiedad.nombre} (${propiedad.instanciaJboss}):</strong>
													</c:if>
													<c:if test="${propiedad.instanciaJboss=='global'}">	
														<strong>${propiedad.nombre}:</strong>
													</c:if>
													<c:if test="${propiedad.descripcion!=''}">	
														${propiedad.descripcion}<br>
													</c:if> 
													<br>
													
													<c:if test="${propiedad.tipologia=='boolean'}">	
														<tr >
															<td valign="top" class="opcion_tab_01" style="border:0px !important; padding:0px 0px 0px 0px !important;">
															
																<c:choose> 
																	<c:when test="${form.listaNuevosValores[status.index].valor=='true' || form.listaNuevosValores[status.index].valor=='false'}">
																		<c:choose> 
																			<c:when test="${form.listaNuevosValores[status.index].valor=='true'}">	
																				<input type="radio" name="nuevosValores${status.index}" value="true" onclick="setCampoHidden('true', ${status.index});" checked="checked" id="nuevosValores${status.index}" style="border:0px !important;">
																				<label for="nuevosValores">Si (true)</label>
																				<input type="radio" name="nuevosValores${status.index}" value="false" onclick="setCampoHidden('false', ${status.index});" id="nuevosValores${status.index}" style="margin-left:15px; border:0px !important;">
																				<label for="nuevosValores">No (false)</label>
																				
																				<input type="hidden" name="nuevosValores" value="true"/>
																			</c:when>	
																			<c:when test="${form.listaNuevosValores[status.index].valor=='false'}">	
																				<input type="radio" name="nuevosValores${status.index}" value="true" onclick="setCampoHidden('true', ${status.index});" id="nuevosValores${status.index}" style="border:0px !important;">
																				<label for="nuevosValores">Si (true)</label>
																				<input type="radio" name="nuevosValores${status.index}" value="false" onclick="setCampoHidden('false', ${status.index});" checked="checked" id="nuevosValores${status.index}" style="margin-left:15px; border:0px !important;">
																				<label for="nuevosValores">No (false)</label>
																				
																				<input type="hidden" name="nuevosValores" value="false"/>
																			</c:when>	
																		</c:choose>
																	</c:when>	
																	<c:when test="${form.listaNuevosValores[status.index].valor=='SI' || form.listaNuevosValores[status.index].valor=='NO'}">
																		<c:choose> 
																			<c:when test="${form.listaNuevosValores[status.index].valor=='SI'}">	
																				<input type="radio" name="nuevosValores${status.index}" value="SI" onclick="setCampoHidden('SI', ${status.index});" checked="checked" id="nuevosValores${status.index}" style="border:0px !important;">
																				<label for="nuevosValores">Si (true)</label>
																				<input type="radio" name="nuevosValores${status.index}" value="NO" onclick="setCampoHidden('NO', ${status.index});" id="nuevosValores${status.index}" style="margin-left:15px; border:0px !important;">
																				<label for="nuevosValores">No (false)</label>
																				
																				<input type="hidden" name="nuevosValores" value="SI"/>
																			</c:when>	
																			<c:when test="${form.listaNuevosValores[status.index].valor=='NO'}">	
																				<input type="radio" name="nuevosValores${status.index}" value="SI" onclick="setCampoHidden('SI', ${status.index});" id="nuevosValores${status.index}" style="border:0px !important;">
																				<label for="nuevosValores">Si (true)</label>
																				<input type="radio" name="nuevosValores${status.index}" value="NO" onclick="setCampoHidden('NO', ${status.index});" checked="checked" id="nuevosValores${status.index}" style="margin-left:15px; border:0px !important;">
																				<label for="nuevosValores">No (false)</label>
																				
																				<input type="hidden" name="nuevosValores" value="NO"/>
																			</c:when>	
																		</c:choose>
																	</c:when>	
																	<c:when test="${form.listaNuevosValores[status.index].valor=='Si' || form.listaNuevosValores[status.index].valor=='No'}">
																		<c:choose> 
																			<c:when test="${form.listaNuevosValores[status.index].valor=='Si'}">	
																				<input type="radio" name="nuevosValores${status.index}" value="Si" onclick="setCampoHidden('Si', ${status.index});" checked="checked" id="nuevosValores${status.index}" style="border:0px !important;">
																				<label for="nuevosValores">Si (true)</label>
																				<input type="radio" name="nuevosValores${status.index}" value="No" onclick="setCampoHidden('No', ${status.index});" id="nuevosValores${status.index}" style="margin-left:15px; border:0px !important;">
																				<label for="nuevosValores">No (false)</label>
																				
																				<input type="hidden" name="nuevosValores" value="Si"/>
																			</c:when>	
																			<c:when test="${form.listaNuevosValores[status.index].valor=='No'}">	
																				<input type="radio" name="nuevosValores${status.index}" value="Si" onclick="setCampoHidden('Si', ${status.index});" id="nuevosValores${status.index}" style="border:0px !important;">
																				<label for="nuevosValores">Si (true)</label>
																				<input type="radio" name="nuevosValores${status.index}" value="No" onclick="setCampoHidden('No', ${status.index});" checked="checked" id="nuevosValores${status.index}" style="margin-left:15px; border:0px !important;">
																				<label for="nuevosValores">No (false)</label>
																				
																				<input type="hidden" name="nuevosValores" value="No"/>
																			</c:when>	
																		</c:choose>
																	</c:when>
																	<c:when test="${form.listaNuevosValores[status.index].valor=='si' || form.listaNuevosValores[status.index].valor=='no'}">
																		<c:choose> 
																			<c:when test="${form.listaNuevosValores[status.index].valor=='si'}">	
																				<input type="radio" name="nuevosValores${status.index}" value="si" onclick="setCampoHidden('si', ${status.index});" checked="checked" id="nuevosValores${status.index}" style="border:0px !important;">
																				<label for="nuevosValores">Si (true)</label>
																				<input type="radio" name="nuevosValores${status.index}" value="no" onclick="setCampoHidden('no', ${status.index});" id="nuevosValores${status.index}" style="margin-left:15px; border:0px !important;">
																				<label for="nuevosValores">No (false)</label>
																				
																				<input type="hidden" name="nuevosValores" value="si"/>
																			</c:when>	
																			<c:when test="${form.listaNuevosValores[status.index].valor=='no'}">	
																				<input type="radio" name="nuevosValores${status.index}" value="si" onclick="setCampoHidden('si', ${status.index});" id="nuevosValores${status.index}" style="border:0px !important;">
																				<label for="nuevosValores">Si (true)</label>
																				<input type="radio" name="nuevosValores${status.index}" value="no" onclick="setCampoHidden('no', ${status.index});" checked="checked" id="nuevosValores${status.index}" style="margin-left:15px; border:0px !important;">
																				<label for="nuevosValores">No (false)</label>
																				
																				<input type="hidden" name="nuevosValores" value="no"/>
																			</c:when>	
																		</c:choose>
																	</c:when>	
																	<c:otherwise>
																		<input type="radio" name="nuevosValores${status.index}" value="true" onclick="setCampoHidden('true', ${status.index});" id="nuevosValores${status.index}" style="border:0px !important;">
																		<label for="nuevosValores">Si (true)</label>
																		<input type="radio" name="nuevosValores${status.index}" value="false" onclick="setCampoHidden('false', ${status.index});" id="nuevosValores${status.index}" style="margin-left:15px; border:0px !important;">
																		<label for="nuevosValores">No (false)</label>
																				
																		<input type="hidden" name="nuevosValores" value=""/>
																	</c:otherwise>
																</c:choose>
																<br />	
																<c:if test="${propiedad.requiereReinicioJboss=='true'}">
															<br />	
															<strong><bean:message key="mensaje.requiereReinicio"/></strong><br>														
														<br>
													</c:if> 
															</td>
														</tr>
													</c:if> 
													<c:if test="${propiedad.tipologia!='boolean'}">	
														<c:if test="${propiedad.ejemplo!=''}">
															<strong>Ejemplo/s:</strong> <em>${propiedad.ejemplo}</em><br>
														</c:if> 
														<br>
														<label for="nuevosValores" style="padding-left:0;" ><span><strong>Valor:</strong> &nbsp;</span></label>
														<input type="text" name="nuevosValores" value="${form.listaNuevosValores[status.index].valor}" id="nuevosValores" style="margin-bottom:0 !important;" title="${propiedad.nombre}" size="75">
														<br />	
																											<c:if test="${propiedad.requiereReinicioJboss=='true'}">
															<br />	
															<strong><bean:message key="mensaje.requiereReinicio"/></strong><br>														
														<br>
													</c:if>				
													</c:if> 
 
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



<script languaje="javascript">
function setCampoHidden(valor, numeroCampo) {
    var vectorNuevosValores = document.getElementsByName('nuevosValores');
    var elem = vectorNuevosValores[numeroCampo];
    elem.value=valor;
}
</script>



<!-- Inicio Botones  -->
<!-- Inicio Botones  -->
<fieldset class="tipo ft_centrada">
<input class="boton_125"  type="submit"  value="<bean:message key="modificar.config.formulario.guardar"/>" />
</fieldset>
<!-- Fin Botones  -->
	
</form>

</fieldset>
</div>
</div>


</div>
<!-- Fin plantilla contenido  -->
<!-- Fin plantilla contenido  -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->


</div>

</tiles:put>
</tiles:insert>
