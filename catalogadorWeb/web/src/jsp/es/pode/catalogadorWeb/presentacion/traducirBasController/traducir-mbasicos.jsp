<%@ include file="/taglib-imports.jspf" %>
<html:xhtml/>

<tiles:insert definition="layout-principal">
<tiles:put name="title" type="string">
	<bean:message key="title.Basico"/>
</tiles:put>

<tiles:put name="body" type="string">

<!-- Inicio plantilla contenido  -->
<div class="plantilla_contenido">

<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
<jsp:include page="/layout/messages.jsp" flush="true" />
<h2><bean:message key="catalogadorBasico.Traducir.Lomes"/></h2>
<form method="post" action="<html:rewrite action="/TraducirLomesBas/TraducirMBasicosComprobarIdiomas"/>" >
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
				<!--  INICIO CAJA DE FORMULARIO   -->
				<div id="formulario" >
					<!--  INICIO CAJA GLOBO  AZUL   -->
					<div class="globo_blanco" >
						<div class="globo_blanco_01">
							<div class="globo_blanco_02">
								<div class="globo_blanco_03">
									<h3><bean:message key="catalogadorBasico.Traducir.Titulo"/></h3>
									<c:forEach var="datoIdioma" items="${form.datosIdiomasAsArray}" varStatus="indice">
										<c:choose>
			  								<c:when test="${indice.index==0}">
			  									<div class="fila_de_tabla conmargen02">
  													<div class="text ">
						  								<input type="radio" class="boton_radio" value="${datoIdioma.idTermino}" id="${datoIdioma.idTermino}" name="idiomaSeleccionado" checked />
								  						<label for="${datoIdioma.idTermino}" class="alineada">
															${datoIdioma.nomTermino}
														</label>
													</div>
													<div class="linea_separadora"></div>
													<br class="oculto" />
												</div>
											</c:when>
											<c:otherwise>
												<div class="fila_de_tabla">
  													<div class="text ">
														<input type="radio" class="boton_radio" value="${datoIdioma.idTermino}" id="${datoIdioma.idTermino}" name="idiomaSeleccionado" />
								  						<label for="${datoIdioma.idTermino}" class="alineada">
															${datoIdioma.nomTermino}
														</label>
													</div>
													<div class="linea_separadora"></div>
													<br class="oculto" />
												</div>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</div>
							</div>
						</div>
					</div>
					<!--  FIN CAJA GLOBO  AZUL   -->
				</div>
				<!--  FIN CAJA DE FORMULARIO   -->
			</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->

<!-- Inicio Botones  -->
<!-- Inicio Botones  -->
	<fieldset class="tipo">
		<input class="boton_125_de_2"  type="submit" name="accion_bien" value="<bean:message key="catalogadorBasico.Aceptar"/>" />
		<input class="boton_125_de_2_izq"  type="submit" name="accion_Cancelar" value="<bean:message key="catalogadorBasico.Cancelar"/>" />
	</fieldset>
</form>
</div>
<!-- Fin plantilla contenido  -->
</tiles:put>
</tiles:insert>