<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<tiles:insert definition="layout-offline">
	<tiles:put name="title" type="string">
		<bean:message key="title.Comun" />
	</tiles:put>
	<tiles:put name="body" type="string">
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- ATENCION!! RREMPLAZAR ESTA CAPA DE LA  PLANTILLA DE CONTENIDO ("plantilla_contenido") POR LA QUE SE DESEE EN LA PLANTILLA BASE  -->
<!-- Inicio plantilla contenido  -->
<div class="plantilla_contenido">
<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
<jsp:include page="/layout/messages.jsp" flush="true" />
<h2><bean:message key="exportar.error.cabecera"/></h2>


<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris">
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
				<!--  INICIO CAJA DE FORMULARIO   -->
<div id="formulario" class="ali_c">
			<br />
<p><em class="incorrecto"><bean:message key="exportar.error.texto"/>&nbsp;<a href="${form.href }"><bean:message key="exportar.error.aqui"/></a>&nbsp;<bean:message key="exportar.error.texto2"/></em></p>

<br />
				</div>
				<!--  FIN CAJA DE FORMULARIO   -->
			</div>
		</div>

	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->
<form method="post" action="<html:rewrite action="/Exportar/ErrorAceptar"/>" >

<p class="parrafo_comun" id="separacion3"><bean:message key="exportar.error.detalle"/></p>

<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">

			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div id="formulario_02" >
			<br />
		 <ul class="lista_validacion">
		 <c:forEach items="${form.mensajes}" var="mensaje">
		 <li>${mensaje }</li>
		 </c:forEach>
		 </ul>

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
<fieldset class="tipo ft_centrada">
<input class="boton_125"  type="submit"  value="<bean:message key="comun.Aceptar"/>" />
</fieldset>
<!-- Fin Botones  -->
<!-- Fin Botones  -->
</form>

</div>
</tiles:put>
</tiles:insert>