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
<%@ taglib prefix="agrega" uri="/WEB-INF/tags/agregaProperties.tld" %>

<html:xhtml/>
<tiles:insert definition="layout-portada">
<tiles:put name="title" type="string">
	<bean:message key="title.Comun"/>
</tiles:put>
<tiles:put name="body" type="string">

<%@ include file="/taglib-imports.jspf" %>
<%@ include file="/es/pode/visualizador/presentacion/portada/noticias-vars.jspf" %>


<div class="plantilla_contenido">
<form method="post" action="" >
<%@ include file="/layout/messages.jsp" %>

<bean:define id="urlObtenerFeed">http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos/AgregadorFeedsCU/FormularioAgregadorFeedsObtenerFeed.do%3FidFeed%3D${form.idRssGaleria}%26feedNum%3D${form.numOdesGaleria}</bean:define>
<bean:define id="scrollerAgrega"><agrega:agregaProperties property="subdominio"/>/static/flash/scrollerAgrega/scrollerAgrega.swf</bean:define>


<c:if test="${form.idRssGaleria!='0.0'}">
<script type="text/javascript">
swfobject.embedSWF("${scrollerAgrega}", "el_flash", "660", "185", "9.0.0",false,false,{wmode:"transparent",base:".",allownetworking:"all",allowscriptaccess:"always",flashvars: "urlFeed=${urlObtenerFeed}"},false);

</script>

<!-- INICIO FLASH  -->
<div id="el_flash"></div>
<!-- FIN FLASH  -->
</c:if>


<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<c:if test="${itinerariosActivo==1}">
<div id="contenido_medio" class="globo_gris sinmargenaba2">
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03" >
				  	
			<div class="formu"   >

			<h3><bean:message key="itinerarios.portada.destacados"/></h3>
			<!--  INICIO GLOBO AZUL   -->
			<!--  INICIO GLOBO AZUL   -->
			
<!--  INICIO CAPA TABLA   -->
<!--  INICIO CAPA TABLA   -->

<div class="caja_tabla " >

<table border="1" id="ultimas_mod"  cellpadding="0" cellspacing="0" width="100%" summary="<bean:message key="itinerarios.portada.ultimas.modificados"/>">
<caption><strong><bean:message key="itinerarios.portada.ultimas.modificados"/></strong></caption>
<tr>
	<th  id="esp_coloreada_00"><span><bean:message key="itinerarios.portada.ultimas.modificados"/></span></th>
</tr>
<c:if test="${fn:length(form.gruposModificados)>0}">
	<logic:iterate id="modificados" collection="${form.gruposModificados}" indexId="indice">
		<c:if test="${indice % 2 == 0}">
			<tr class="tr_gris">
				<td valign="top" class="tar2"><a href="${modificados.urlPublico}" target="_blank">${modificados.nombre}</a></td>
			</tr>
		</c:if>
		<c:if test="${indice % 2 != 0}">
			<tr class="tr_blanco">
				<td valign="top" class="tar2"><a href="${modificados.urlPublico}" target="_blank">${modificados.nombre}</a></td>
			</tr>
		</c:if>
	</logic:iterate>
</c:if>
<c:if test="${fn:length(form.gruposModificados)<1}">
	<tr class="tr_gris">
		<td valign="top" class="tar2"><bean:message key="grupos.no.existe.actualmente"/></td>
	</tr>
</c:if>
</table>
<table border="1" id="ultimos_odes" style="margin-bottom:10px;" cellpadding="0" cellspacing="0" width="100%" summary="<bean:message key="itinerarios.portada.ultimas.creados"/>">
<caption><strong><bean:message key="itinerarios.portada.ultimas.creados"/></strong></caption>
<tr>
	<th  id="esp_coloreada_00"><span><bean:message key="itinerarios.portada.ultimas.creados"/></span></th>
</tr>
<c:if test="${fn:length(form.gruposCreados)>0}">
	<logic:iterate id="creados" collection="${form.gruposCreados}" indexId="indice2">
		<c:if test="${indice2 % 2 == 0}">
			<tr class="tr_gris">
				<td valign="top" class="tar2"><a href="${creados.urlPublico}" target="_blank">${creados.nombre}</a></td>
			</tr>
		</c:if>
		<c:if test="${indice2 % 2 != 0}">
			<tr class="tr_blanco">
				<td valign="top" class="tar2"><a href="${creados.urlPublico}" target="_blank">${creados.nombre}</a></td>
			</tr>
		</c:if>
	</logic:iterate>
</c:if>
<c:if test="${fn:length(form.gruposCreados)<1}">
	<tr class="tr_gris">
		<td valign="top" class="tar2"><bean:message key="grupos.no.existe.actualmente"/></td>
	</tr>
</c:if>
</table>

</div>
<!-- FIN CAJA TABLA -->
<!--  FIN CAPA TABLA   -->	

						</div>
					</div>
				</div>
		</div>
		<!--  FIN GLOBO AZUL   -->
		<!--  FIN GLOBO AZUL   -->
		
		

	
</div>

<!-- Fin capa formu  -->


<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->
</c:if>


<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->

<analytic:googleAnalytic />



<div id="contenido_medio_02" class="globo_gris conmargen3">
	<div class="globo_gris_01">
		<div class="globo_gris_02">

			<div class="globo_gris_03" >
			<c:if test="${fn:length(form.noticias) < 1}" >
			<!--  INICIO CAJA DE FORMULARIO   -->
					<div><bean:message key="listaNoticias.vacio"/>
	
					</div>
				<!--  FIN CAJA DE FORMULARIO   -->
			</c:if>
				  	
			<c:if test="${fn:length(form.noticias) > 0}" >
				  	
				<div class="formu"   >

					<div id="rev_02">
					<bean:define id="urlObtenerFeed"><rewrite:rewrite url="visualizadorcontenidos/AgregadorFeedsCU/FormularioAgregadorFeedsObtenerFeed.do"/></bean:define>
					<bean:define id="feedDefault"><agrega:agregaProperties property="feed_default"/></bean:define>
					<bean:define id="feedOtros"><bean:message key="feeds.agregador.otros"/></bean:define>
					<bean:define id="nombreGA"><bean:message key='feeds.agregador.noticias.titulo'/></bean:define>
								
	

		<analytic:enlaceGAnalytic title="${feedOtros}${urlObtenerFeed}?idFeed=0.1&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}" href="${urlObtenerFeed}?idFeed=0.1&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}" clase="rss_rss" textoEnlace="RSS" nombreAnalytic="Feed/RSS/${nombreGA}"/>
		|
		<analytic:enlaceGAnalytic title="${feedOtros}${urlObtenerFeed}?idFeed=0.2&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}" href="${urlObtenerFeed}?idFeed=0.2&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}" clase="rss_rss" textoEnlace="ATOM" nombreAnalytic="Feed/ATOM/${nombreGA}"/>

					</div>
					
<h3><bean:message key="portada.mensaje.noticias"/> </h3>
<ul id="listado_noticias" id="limpiar">



<c:forEach items="${form.noticias}"  var="noticia" varStatus="status" begin="0" end="2">
<li>




				<!--  INICIO CAJA DE FORMULARIO   -->
						<a href="<rewrite:rewrite url="${initParam.url_portada_noticias}"/>/${fn:replace(fn:replace(noticia.tituloCodex,"?", "_"),"&#37","_")}/${noticia.id }">${noticia.titulo}</a><br/>
						<em><fmt:formatDate value="${noticia.fechaPublicacion.time}" pattern="dd/MM/yyyy"/></em>
						<p>${noticia.resumen}<br/>
						<span><bean:message key="mostrarNoticia.categoria"/> 
						<a href="<rewrite:rewrite url="${initParam.url_noticias_categorias}"/>/${noticia.categoriaCodex}/${noticia.idCategoria}" class="cate">${noticia.categoria}</a></span></p>
					
				<!--  FIN CAJA DE FORMULARIO   -->
					

</li>
</c:forEach>

</ul>
						
						</c:if>
						
		<!-- Fin NOTICIAS  -->
<!-- Fin NOTICIAS  -->

<!--  FIN CAPA TABLA   -->	

						</div>
					</div>
				</div>

		</div>
<!--  FIN GLOBO AZUL   -->
		<!--  FIN GLOBO AZUL   -->

		

<!--  FIN GLOBO GRIS   -->

<!--  FIN GLOBO GRIS   -->

</form>
</div>

<!-- Fin plantilla contenido  -->
<!-- Fin plantilla contenido  -->
		
	
<!--  Establecer el cursor por defecto en la caja del buscador   -->	
<script type="text/javascript">
  document.forms[0].buscadorGeneral.focus();
</script>

</tiles:put>
</tiles:insert>