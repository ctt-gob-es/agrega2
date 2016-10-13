<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<%@ taglib uri="/WEB-INF/tags/googleAnalytic.tld" prefix="analytic" %>
<%@ taglib prefix="agrega" uri="/WEB-INF/tags/agregaProperties.tld" %>


<html:xhtml/>
<tiles:insert definition="layout-menu-0">
<tiles:put name="title" type="string">
	<bean:message key="title.Comun"/>
</tiles:put>
<tiles:put name="body-principal" type="string">

<%@ include file="/taglib-imports.jspf" %>
<%@ include file="/es/pode/visualizador/presentacion/portada/noticias-vars.jspf" %>

<analytic:googleAnalytic />
<%@ include file="/layout/messages.jsp" %>


<!-- Inicio NOTICIAS  -->
<div class="col_der" id="noticias">
	<section >
		<header>
		<bean:define id="urlObtenerFeed"><rewrite:rewrite url="visualizadorcontenidos2/AgregadorFeedsCU/FormularioAgregadorFeedsObtenerFeed.do"/></bean:define>
		<bean:define id="feedDefault"><agrega:agregaProperties property="feed_default"/></bean:define>
		<bean:define id="feedOtros"><bean:message key="feeds.agregador.otros"/></bean:define>
		<bean:define id="nombreGA"><bean:message key='feeds.agregador.noticias.titulo'/></bean:define>
			<analytic:enlaceGAnalytic title="${feedOtros}${urlObtenerFeed}?idFeed=0.1&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}" href="${urlObtenerFeed}?idFeed=0.1&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}" clase="rss_feed" textoEnlace="RSS" nombreAnalytic="Feed/RSS/${nombreGA}"/>
			<!-- <a href="#" class="rss_feed">RSS</a> -->
			<h2>
				<bean:message key="portada.mensaje.noticias"/>
				<logic:notEmpty name="form" property="categoria">
					- ${form.categoria}
				</logic:notEmpty>
			</h2>
		</header>
	

		
		<!-- 
		<analytic:enlaceGAnalytic title="${feedOtros}${urlObtenerFeed}?idFeed=0.1&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}" href="${urlObtenerFeed}?idFeed=0.1&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}" clase="rss_rss" textoEnlace="RSS" nombreAnalytic="Feed/RSS/${nombreGA}"/>
		<analytic:enlaceGAnalytic title="${feedOtros}${urlObtenerFeed}?idFeed=0.2&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}" href="${urlObtenerFeed}?idFeed=0.2&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}" clase="rss_rss" textoEnlace="ATOM" nombreAnalytic="Feed/ATOM/${nombreGA}"/>
		-->
		
		<!--  -->
	   	<article class="bloque_titulares">
	 	<ul >	
			<c:if test="${fn:length(form.noticias) < 1}" > 
				<p><bean:message key="listaNoticias.vacio"/></p><br>
			</c:if> 	
			<c:if test="${fn:length(form.noticias) > 0}" >
				<c:forEach items="${form.noticias}"  var="noticia" varStatus="status">
				
					<li class="clearfix"><a class="titular" href="<rewrite:rewrite url="${initParam.url_portada_noticias}"/>/${fn:replace(fn:replace(noticia.tituloCodex,"?", "_"),"&#37","_")}/${noticia.id }">${noticia.titulo}</a>
		   			<em><fmt:formatDate value="${noticia.fechaPublicacion.time}" pattern="dd/MM/yyyy"/></em>
					<p>${noticia.resumen}</p>
					<!-- <span><bean:message key="mostrarNoticia.categoria"/>--> 
					<span class="etiquetas clearfix"><a href="<rewrite:rewrite url="${initParam.url_noticias_categorias}"/>/${noticia.categoriaCodex}/${noticia.idCategoria}" class="cate">${noticia.categoria}</a></span>
	  				</li>
				</c:forEach>
			</c:if> 	
   		</ul>
   		<h3></h3>
    	</hgroup>
   	 	</header>
   		</article>
	</section>
</div>


<!--  Establecer el cursor por defecto en la caja del buscador   -->	
<script type="text/javascript">
  //document.forms[0].buscadorGeneral.focus();
</script>

</tiles:put>
</tiles:insert>