<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/WEB-INF/tags/googleAnalytic.tld" prefix="analytic" %>
<%@ taglib prefix="agrega" uri="/WEB-INF/tags/agregaProperties.tld" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>

<tiles:insert definition="layout-usuario">

<tiles:put name="title" type="string">
      <bean:message key="title.Comun"/>
</tiles:put>

<tiles:put name="body" type="string">

<%@ include file="/taglib-imports.jspf" %>

<%@ include file="/es/pode/visualizador/presentacion/descargas/descargas-vars.jspf" %>

<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- ATENCION!! RREMPLAZAR ESTA CAPA DE LA  PLANTILLA DE CONTENIDO ("plantilla_contenido") POR LA QUE SE DESEE EN LA PLANTILLA BASE  -->
<!-- Inicio plantilla contenido  -->
<!-- Inicio plantilla contenido  -->
<div class="plantilla_contenido">
<analytic:googleAnalytic />
<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
<jsp:include page="/layout/messages.jsp" flush="true" />

<h2><bean:message key="listaDescargas.title"/></h2>

<div class="parrafo_comun" id="separacion"> 
<bean:message key="listaDescargas.cabecera"/>
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<c:if test="${fn:length(form.descargas) < 1}" >
	<div class="globo_gris conmargen" >
		<div class="globo_gris_01">
			<div class="globo_gris_02">
				<div class="globo_gris_03">
				<!--  INICIO CAJA DE FORMULARIO   -->
					<div><h3 class="h3_desc"><bean:message key="listaDescargas.vacio"/></h3>
	
					</div>
				<!--  FIN CAJA DE FORMULARIO   -->
					</div>
			</div>
		</div>
	</div>
</c:if>
<bean:define id="urlObtenerFeed"><rewrite:rewrite url="visualizadorcontenidos/AgregadorFeedsCU/FormularioAgregadorFeedsObtenerFeed.do"/></bean:define>
<bean:define id="feedDefault"><agrega:agregaProperties property="feed_default"/></bean:define>
<bean:define id="feedOtros"><bean:message key="feeds.agregador.otros"/></bean:define>
<bean:define id="nombreGA"><bean:message key='feeds.agregador.descargas.titulo'/></bean:define>
		
	
	
<c:if test="${fn:length(form.descargas) > 0}" >

<analytic:enlaceGAnalytic title="${feedOtros}${urlObtenerFeed}?idFeed=11.1&feedNum=${feedDefault}" href="${urlObtenerFeed}?idFeed=11.1&feedNum=${feedDefault}" clase="rss_rss" textoEnlace="RSS" nombreAnalytic="Feed/RSS/${nombreGA}"/>
<analytic:enlaceGAnalytic title="${feedOtros}${urlObtenerFeed}?idFeed=11.2&feedNum=${feedDefault}" href="${urlObtenerFeed}?idFeed=11.2&feedNum=${feedDefault}" clase="rss_rss" textoEnlace="ATOM" nombreAnalytic="Feed/ATOM/${nombreGA}"/>
	
<c:forEach items="${form.descargas}" var="descarga" varStatus="status">
	<div class="globo_gris conmargen" >
		<div class="globo_gris_01">
			<div class="globo_gris_02">
				<div class="globo_gris_03">
				<!--  INICIO CAJA DE FORMULARIO   -->
					<div><h3 class="h3_desc">${descarga.titulo}</h3>
	
					<p class="parrafo">${descarga.descripcion}<br />
					<a href="<html:rewrite action="/Descargas/DescargasDescarga"/>?identificador=${descarga.identificador}&titulo=${descarga.titulo}"><bean:message key="listaDescargas.descargar"/> (${descarga.peso})</a></p>
					<br/>		
			
	
					</div>
				<!--  FIN CAJA DE FORMULARIO   -->
					</div>
			</div>
		</div>
	</div>
</c:forEach>
</c:if>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->


</div>


</div>
<!-- Fin plantilla contenido  -->
<!-- Fin plantilla contenido  -->

<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
    </tiles:put>

</tiles:insert>