<%@ include file="/taglib-imports.jspf" %>

<tiles:insert definition="layout-avanzado">
<tiles:put name="title" type="string">
	<bean:message key="title.Avanzado"/>
</tiles:put>

<tiles:put name="body" type="string">
<!-- Inicio plantilla contenido  -->
<div class="plantilla_contenido">

<h2><bean:message key="title.Traduccion"/></h2>
<form method="post" action="<html:rewrite action="/TraducirLomesAv/ResultadoTraduccionInicio" />" >
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->

<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div id="formulario" class="ali_c">
			<br />
			<c:choose>
			 <c:when test="${!empty form.datosIdiomas}" >
				 <p><em class="correcto">
				   	<bean:message key="error.traduccion.resultadoOk"/>
				 </em></p>
			</c:when>
			<c:otherwise>
				<p><em class="incorrecto">
				   	<c:choose>
				   		<c:when test="${!empty form.mensajeFin}" >
				   			${form.mensajeFin}
				   		</c:when>
				   		<c:otherwise>
						   <bean:message key="error.traduccion.resultadoMal"/>
				   		</c:otherwise>
				   	</c:choose>
				 </em></p>
			</c:otherwise>
			</c:choose>
				<br />
				</div>
				<!--  FIN CAJA DE FORMULARIO   -->
			</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!-- Inicio Botones  -->
<fieldset class="tipo ft_centrada">
	<input type="submit" class="boton_125" value="<bean:message key="catalogadoravanzado.validacion.Volver"/>"/>
</fieldset>
<!-- Fin Botones  -->
<br/>
<br/>
</form>
</div>
<!-- Fin plantilla contenido  -->
</tiles:put>

</tiles:insert>