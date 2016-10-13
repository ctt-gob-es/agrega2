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

<h2><bean:message key="importar.resultado.cabecera"/></h2>
<form method="post" action="<html:rewrite action="/Importar/ResultadoAceptar"/>" >
<bean:define id="verMessage"><bean:message key="importar.js.ver"/></bean:define>
<bean:define id="ocultarMessage"><bean:message key="importar.js.ocultar"/></bean:define>
<bean:define id="verMessageInfo"><bean:message key="importar.js.ver.info"/></bean:define>
<bean:define id="ocultarMessageInfo"><bean:message key="importar.js.ocultar.info"/></bean:define>
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<c:forEach items="${form.resultado}" var="item" varStatus="status">
<div class="globo_gris uno_b">
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
				<!--  INICIO CAJA DE FORMULARIO   -->
				
<c:choose>
<c:when test="${empty item.mensajes}">
<div class="caja_dinamica"><p><em class="correcto"><bean:message key="importar.resultado.valido.titulo" arg0="${item.titulo}"/></em></p></div>
</c:when>
<c:when test="${item.valido}">
	<div class="caja_dinamica"><a class="desplegado" id="pm${status.index }" href="#" onclick="expandirCaja('m${status.index }', '${verMessageInfo }', '${ocultarMessageInfo }');return false;" onkeypress="expandirCaja('m${status.index }', '${verMessageInfo }', '${ocultarMessageInfo }');return false;"><br class="falso" /><strong id="dm${status.index }" >${ocultarMessageInfo }</strong></a><p><em class="correcto"><bean:message key="importar.resultado.valido.titulo" arg0="${item.titulo}"/></em></p></div>
	
	<div id="m${status.index }" class="caja_abierta" >
	<div class="formu" style="padding-bottom:0;" >
		<p class="err_p"><bean:message key="importar.resultado.error.vocabularios.fechas.texto"/></p>
			<ul class="lista_validacion">
			<c:forEach items="${item.mensajes}" var="mensaje">
			<li>${mensaje }</li>
			</c:forEach>
			</ul>
	
	</div>
	</div>
</c:when>
<c:otherwise>
	<div class="caja_dinamica"><a class="desplegado" id="pm${status.index }" href="#" onclick="expandirCaja('m${status.index }', '${verMessage }', '${ocultarMessage }');return false;" onkeypress="expandirCaja('m${status.index }', '${verMessage }', '${ocultarMessage }');return false;"><br class="falso" /><strong id="dm${status.index }" >${ocultarMessage }</strong></a><p><em class="incorrecto"><bean:message key="importar.resultado.error.titulo" arg0="${item.titulo}"/></em></p></div>
	
	<div id="m${status.index }" class="caja_abierta" >
	<div class="formu" style="padding-bottom:0;" >
		<p class="err_p"><bean:message key="importar.resultado.error.texto"/></p>
			<ul class="lista_validacion">
			<c:forEach items="${item.mensajes}" var="mensaje">
			<li>${mensaje }</li>
			</c:forEach>
			</ul>
	
	</div>
	</div>
</c:otherwise>
</c:choose>
				<br /><!--  FIN CAJA DE FORMULARIO   -->
			</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO GRIS   -->
</c:forEach>

<!-- Inicio Botones  -->
<!-- Inicio Botones  -->
<fieldset class="tipo ft_centrada">
<input class="boton_125" name="action" type="submit"  value="<bean:message key="comun.Aceptar"/>" />
</fieldset>
<!-- Fin Botones  -->
<!-- Fin Botones  -->
</form>

</div>
</tiles:put>
</tiles:insert>