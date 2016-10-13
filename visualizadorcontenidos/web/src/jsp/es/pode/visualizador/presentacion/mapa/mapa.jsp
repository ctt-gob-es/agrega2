<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<%@ taglib uri="/WEB-INF/tags/googleAnalytic.tld" prefix="analytic" %>

<tiles:insert definition="layout-menu-0">

    <tiles:put name="title" type="string">
        <bean:message key="title.Comun"/>
    </tiles:put>

    <tiles:put name="style" type="string">
        <link rel="stylesheet" type="text/css" media="screen" href="<html:rewrite page="/es/pode/visualizador/presentacion/mapa/mapa.css"/>"></link>
    </tiles:put>

        <tiles:put name="body-principal" type="string">
        <%@ include file="/es/pode/visualizador/presentacion/mapa/mapa-javascript.jspf" %>
<!--  -->
<div class="col_der">
<analytic:googleAnalytic />
<section >
<header>
<h2><bean:message key="mapa.nombre"/></h2>
</header>
<div id="contenido_mapa">
<p><bean:message key="mapa.texto"/>.</p>

<!-- INICIO  LISTADO MAPA  --> 
<ul class="mapa">
	<li class="blanco"><div><a href="<rewrite:rewrite url="${initParam.url_restringida }"/>"><em><bean:message key="mapa.acceso"/></em></a></div>
	<li class="color"><div><a href="<rewrite:rewrite url="${initParam.url_portada }"/>"><em><bean:message key="mapa.portada"/></em></a></div>
	<li class="blanco"><div><a href="<rewrite:rewrite url="${initParam.url_busqueda_externa}"/>"><em><bean:message key="mapa.busqExterna"/></em></a></div>
	<li class="color" ><div><a href="<rewrite:rewrite url="${initParam.url_buscadorArbolCurricular}"/>"><em><bean:message key="mapa.busqueda.taxonomica"/></em></a></div></li>
	<li class="blanco"><div><a href="<rewrite:rewrite url="${initParam.url_acercaDeAgrega }"/>"><em><bean:message key="mapa.acercaDeAgrega"/></em></a></div>
		<ul class="submapa">
			<li class="color"><div><a href="<rewrite:rewrite url="${initParam.url_noticias }"/>"><em><bean:message key="mapa.noticias"/></em></a></div></li>
			<li class="blanco"><div><a href="<rewrite:rewrite url="${initParam.url_descargas_portal }"/>"><em><bean:message key="mapa.descargas"/></em></a></div></li>
			<li class="color"><div><a href="<rewrite:rewrite url="${initParam.url_estadisticas }"/>"><em><bean:message key="mapa.estadisticas"/></em></a></div></li>
			<li class="blanco"><div><a href="<rewrite:rewrite url="${initParam.url_utilidades }"/>"><em><bean:message key="mapa.agrega.en.tu.web"/></em></a></div></li>
			<li class="color"><div><a href="<rewrite:rewrite url="${initParam.url_faqs }"/>"><em><bean:message key="mapa.preguntasFrecuentes"/></em></a></div></li>
		</ul>
	</li>
	<li class="blanco" id="final"><div><a href="<rewrite:rewrite url="${initParam.url_politicaPrivacidad }"/>"><em><bean:message key="mapa.politicaPrivacidad"/></em></a></div></li>
	<li class="color" ><div><a href="<rewrite:rewrite url="${initParam.url_informacionLegal }"/>"><em><bean:message key="mapa.informacionLegal"/></em></a></div></li>
	<li class="blanco" ><div><a href="<rewrite:rewrite url="${initParam.url_ayuda_wiki }"/>"><em><bean:message key="mapa.ayuda"/></em></a></div></li>
	<li class="color" ><div><a href="<rewrite:rewrite url="${initParam.url_feeds }"/>"><em><bean:message key="mapa.rss"/></em></a></div></li>
	<li class="blanco"><div><a href="<rewrite:rewrite url="${initParam.url_contacto }"/>"><em><bean:message key="mapa.contacto"/></em></a></div></li>
	<li class="color"><div><a href="<rewrite:rewrite url="${initParam.url_accesibilidad }"/>"><em><bean:message key="mapa.accesibilidad"/></em></a></div></li>
</ul>

<!-- FIN LISTADO TIPO  -->

</div>
</section>
</div>

    </tiles:put>

</tiles:insert>
