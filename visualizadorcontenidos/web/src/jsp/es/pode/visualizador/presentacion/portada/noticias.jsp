<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/WEB-INF/tags/imagenPortada.tld" prefix="portada" %>
<%@ taglib uri="/WEB-INF/tags/googleAnalytic.tld" prefix="analytic" %>

<html:xhtml/>
<tiles:insert definition="layout-menu-1-enriquecido">
	<tiles:put name="title" type="string">
		<bean:message key="title.Comun"/>
	</tiles:put>
	
	<tiles:put name="body-principal" type="string">
	
		<%@ include file="/taglib-imports.jspf" %>
		<%@ include file="/es/pode/visualizador/presentacion/portada/noticias-vars.jspf" %>
		<%@ include file="/es/pode/visualizador/presentacion/descargas/descargas-vars.jspf" %>
		<%@ include file="/layout/messages.jsp" %>
		
		<bean:define id="urlObtenerFeed">http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos2/AgregadorFeedsCU/FormularioAgregadorFeedsObtenerFeed.do%3FidFeed%3D${form.idRssGaleria}%26feedNum%3D${form.numOdesGaleria}</bean:define>
		<bean:define id="scrollerAgrega"><agrega:agregaProperties property="subdominio"/>/static/flash/scrollerAgrega/scrollerAgrega.swf</bean:define>
		
		
		<analytic:googleAnalytic />
		
		<article id="buscador">
			<header>
			<h2 class="titulo"><bean:message key="encuentra.los.mejores.contenidos.educativos"/></h2>
			</header>
			<tiles:insert definition="buscador-portada" flush="false"/>
		</article>
		
	</tiles:put>
</tiles:insert>