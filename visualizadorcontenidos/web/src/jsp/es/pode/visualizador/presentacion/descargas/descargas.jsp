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

<tiles:insert definition="layout-menu-0">

<tiles:put name="title" type="string">
      <bean:message key="title.Comun"/>
</tiles:put>

<tiles:put name="body-principal" type="string">

<%@ include file="/taglib-imports.jspf" %>

<!-- %@ include file="/es/pode/visualizador/presentacion/descargas/descargas-vars.jspf" %-->

<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- ATENCION!! RREMPLAZAR ESTA CAPA DE LA  PLANTILLA DE CONTENIDO ("plantilla_contenido") POR LA QUE SE DESEE EN LA PLANTILLA BASE  -->
<!-- Inicio plantilla contenido  -->
<!-- Inicio plantilla contenido  -->
<div class="col_der" id="descargas">
	
		<section >
			<header>
			<!--  <a href="rss.html" class="rss_feed">RSS</a>  -->
			<!-- <a href="#" class="rss_feed" style="margin-right:20px">ATOM</a> -->
			<analytic:googleAnalytic />
			<h2><bean:message key="listaDescargas.title"/></h2>
			</header>
		<!--  -->
    	<article class="bloque_titulares">
    	<p><bean:message key="listaDescargas.cabecera"/></p>

<bean:define id="urlObtenerFeed"><rewrite:rewrite url="visualizadorcontenidos2/AgregadorFeedsCU/FormularioAgregadorFeedsObtenerFeed.do"/></bean:define>
<bean:define id="feedDefault"><agrega:agregaProperties property="feed_default"/></bean:define>
<bean:define id="feedOtros"><bean:message key="feeds.agregador.otros"/></bean:define>
<bean:define id="nombreGA"><bean:message key='feeds.agregador.descargas.titulo'/></bean:define>
		
	
   		<ul >
			<c:if test="${fn:length(form.descargas) < 1}" >
				<li class="clearfix"> <bean:message key="listaDescargas.vacio"/></li>
			</c:if>

	
<c:if test="${fn:length(form.descargas) > 0}" >
	
<c:forEach items="${form.descargas}" var="descarga" varStatus="status">

	<li class="clearfix"><a href="<html:rewrite action="/Descargas/DescargasDescarga"/>?identificador=${descarga.identificador}&titulo=${descarga.titulo}" class="titular">${descarga.titulo}</a>
		<p>${descarga.descripcion}<br />
		<span class="descarga clearfix"><a href="<html:rewrite action="/Descargas/DescargasDescarga"/>?identificador=${descarga.identificador}&titulo=${descarga.titulo}"><bean:message key="listaDescargas.descargar"/> (${descarga.peso})</a></span>
   	</li>
</c:forEach>
</c:if>
   		 </ul>
    	</article>

<!--  -->
</section>
</div>
<!-- Fin plantilla contenido  -->
<!-- Fin plantilla contenido  -->

<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
    </tiles:put>

</tiles:insert>