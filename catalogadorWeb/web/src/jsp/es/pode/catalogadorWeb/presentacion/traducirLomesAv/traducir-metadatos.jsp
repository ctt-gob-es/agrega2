<%@ include file="/taglib-imports.jspf" %>


<html:xhtml/>

<tiles:insert definition="layout-avanzado">
<tiles:put name="title" type="string">
	<bean:message key="title.Avanzado"/>
</tiles:put>

<tiles:put name="body" type="string">

<!-- Inicio plantilla contenido  -->
<div class="plantilla_contenido">

<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
<jsp:include page="/layout/messages.jsp" flush="true" />

<form method="post" action="<html:rewrite action="/TraducirLomesAv/TraducirMetadatosComprobarIdiomas"/>" >
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div id="formulario" >			
			        <c:forEach var="datoIdioma" items="${form.datosIdiomasAsArray}" varStatus="indice">
						 <div class="fila_de_tabla">
		  						<div class="text ft_lateral" >	
		  							<c:choose>
		  								<c:when test="${indice.index==0}">
			  								<input type="radio" class="boton_radio" value="${datoIdioma.idTermino}" id="${datoIdioma.idTermino}" name="idiomaSeleccionado" checked />
					  						<label for="${datoIdioma.idTermino}" class="alineada">
												${datoIdioma.nomTermino}
											</label>
										</c:when>
										<c:otherwise>
											<input type="radio" class="boton_radio" value="${datoIdioma.idTermino}" id="${datoIdioma.idTermino}" name="idiomaSeleccionado" />
					  						<label for="${datoIdioma.idTermino}" class="alineada">
												${datoIdioma.nomTermino}
											</label>
										</c:otherwise>
									</c:choose>
								</div>
								<div class="linea_separadora"></div>
								<br class="oculto" />
						  </div>
						  <!--     -->
					</c:forEach>
					<c:forEach var="traduccion" items="${form.datosTraduccion}" varStatus="indiceT">
						<div class="fila_de_tabla">
		  						<div class="text ft_lateral" >
		  							<c:choose>
		  								<c:when test="${indiceT.index==0}">
			  								<input type="radio" class="boton_radio" value="${traduccion.identificador}" id="${traduccion.identificador}" name="tipoTraduccion" checked />
					  						<label for="${traduccion.identificador}" class="alineada">
												${traduccion.nombre}
											</label>
										</c:when>
										<c:otherwise>
											<input type="radio" class="boton_radio" value="${traduccion.identificador}" id="${traduccion.identificador}" name="tipoTraduccion" />
					  						<label for="${traduccion.identificador}" class="alineada">
												${traduccion.nombre}
											</label>
										</c:otherwise>
									</c:choose>
								</div>
								<div class="linea_separadora"></div>
								<br class="oculto" />
						  </div>
						  <!--     -->					
					</c:forEach>		  	
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
		<input class="boton_125_de_2"  type="submit" name="accion_bien" value="<bean:message key="catalogadorAvanzado.Aceptar"/>" />
		<input class="boton_125_de_2_izq"  type="submit" name="accion_Cancelar" value="<bean:message key="catalogadorAvanzado.Cancelar"/>" />
	</fieldset>
</form>

<!-- Fin plantilla contenido  -->
</tiles:put>
</tiles:insert>