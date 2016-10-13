<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<%@ taglib uri="/WEB-INF/tags/googleAnalytic.tld" prefix="analytic" %>
<%@ taglib prefix="agrega" uri="/WEB-INF/tags/agregaProperties.tld" %>


<tiles:insert definition="layout-usuario">

<tiles:put name="title" type="string">
      <bean:message key="title.Comun"/>
</tiles:put>


<tiles:put name="body" type="string">

<%@ include file="/taglib-imports.jspf" %>


<!--		Inicio plantilla contenido		-->

<div class="plantilla_contenido">
<analytic:googleAnalytic />
<form id="agregadorFeedsCUAgregadorFeedsCUForm" action='<html:rewrite action="/AgregadorFeedsCU/AgregadorFeedsCU"/>' method="post" >

<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
<jsp:include page="/layout/messages.jsp" flush="true" />

<h2><bean:message key="feeds.agregador.titulo"/></h2>
<a href="<html:rewrite action="/AgregadorFeedsCU/FormularioAgregadorFeedsQueEsEsto"/>" class="resultados_bb" title="<bean:message key='feeds.agregador.queEsEsto.titulo'/>"><bean:message key='feeds.agregador.queEsEsto.titulo'/></a>
<p class="parrafo_comun" id="separacion3"><bean:message key="feeds.agregador.descripcion"/></p>

<!--		Inicio del formulario principal		-->

		<bean:define id="urlObtenerFeed">http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos/AgregadorFeedsCU/FormularioAgregadorFeedsObtenerFeed.do</bean:define>
		<bean:define id="feedDefault"><agrega:agregaProperties property="feed_default"/></bean:define>
		<bean:define id="feedOtros"><bean:message key="feeds.agregador.otros"/></bean:define>
		<bean:define id="feedGoogle"><bean:message key="feeds.agregador.google"/></bean:define>
		<bean:define id="feedBlogLines"><bean:message key="feeds.agregador.blogLines"/></bean:define>
		<bean:define id="feedNetvibes"><bean:message key="feeds.agregador.netvibes"/></bean:define>
		<bean:define id="feedLive"><bean:message key="feeds.agregador.live"/></bean:define>
		<bean:define id="feedYahoo"><bean:message key="feeds.agregador.yahoo"/></bean:define>
	
		<logic:iterate id="feed" collection="${form.feeds}">
					<logic:equal name="feed" property="periodicidad" value="si">
						<!--  INICIO GLOBO GRIS   -->
						<!--  INICIO GLOBO GRIS   -->
						<div class="globo_gris uno_b" >
							<div class="globo_gris_01">
								<div class="globo_gris_02">
									<div class="globo_gris_03">
										<!--  INICIO CAJA DE FORMULARIO   -->
									<div class="caja_dinamica"><a title='<bean:message key="feeds.agregador.ver"/>' class="desplegado" id="pm${feed.id}" href="#" onclick="expandirCaja('m${feed.id}');return false;" onkeypress="expandirCaja('m${feed.id}');return false;"><br class="falso" /><strong id="dm${feed.id}"><bean:message key="feeds.agregador.ver"/></strong></a><h3><bean:message key='${feed.titulo}'/></h3></div>
									<div id="m${feed.id}" class="caja_cerrada" >
						
						<br />
						<!--  INICIO CAJA DE FORMULARIO   -->
						<div id="formulario" >
							<!--  INICIO CAPA TABLA   -->
							<!--  INICIO CAPA TABLA   -->					
							 <div class="caja_tabla" style="margin-bottom:0">
							<table border="0" class="administracion_usuarios" cellpadding="0"  cellspacing="0" width="100%" summary="Tabla de RSS">
							<caption><strong>Tabla de RSS</strong></caption>
							
							<tr class="tr_gris">
								<td class="new sp_top" style="width:100px">
							
								<strong class="bld"><bean:message key='feeds.agregador.semana'/></strong> </td>
								<td class="new">
									<bean:define id="nombreGA"><bean:message key='${feed.titulo}'/> <bean:message key='feeds.agregador.semana'/></bean:define>
									<ul class="ul_up" style="width:500px !important;">
					
										<li><analytic:enlaceGAnalytic title="${feedOtros}${urlObtenerFeed}?idFeed=${feed.id}.1.1&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}" href="${urlObtenerFeed}?idFeed=${feed.id}.1.1&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}" clase="rss_rss" textoEnlace="RSS" nombreAnalytic="Feed/RSS/${nombreGA}"/>  |</li>
										<li><analytic:enlaceGAnalytic title="${feedOtros}${urlObtenerFeed}?idFeed=${feed.id}.2.1&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}" href="${urlObtenerFeed}?idFeed=${feed.id}.2.1&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}" clase="rss_rss" textoEnlace="ATOM" nombreAnalytic="Feed/ATOM/${nombreGA}"/>  |</li>
										<li><analytic:enlaceGAnalytic title="${feedGoogle}" href="http://www.google.com/ig/add?feedurl=${urlObtenerFeed}?idFeed=${feed.id}.2.1&idiomaNavegacion=${form.idiomaNavegacion}" clase="rss_google" textoEnlace="${feedGoogle}" nombreAnalytic="Feed/${feedGoogle}/${nombreGA}"/>  |</li>
										<li><analytic:enlaceGAnalytic title="${feedBlogLines}" href="http://www.bloglines.com/sub/${urlObtenerFeed}?idFeed=${feed.id}.1.1&idiomaNavegacion=${form.idiomaNavegacion}" clase="rss_bloglines" textoEnlace="${feedBlogLines}" nombreAnalytic="Feed/${feedBlogLines}/${nombreGA}"/>  |</li>
										<li><analytic:enlaceGAnalytic title="${feedNetvibes}" href="http://www.netvibes.com/subscribe.php?url=${urlObtenerFeed}?idFeed=${feed.id}.1.1&idiomaNavegacion=${form.idiomaNavegacion}" clase="rss_netvibes" textoEnlace="${feedNetvibes}" nombreAnalytic="Feed/${feedNetvibes}/${nombreGA}"/>  |</li>
										<li><analytic:enlaceGAnalytic title="${feedLive}" href="http://www.live.com/Default.aspx?add=${urlObtenerFeed}?idFeed=${feed.id}.1.1&idiomaNavegacion=${form.idiomaNavegacion}" clase="rss_windows" textoEnlace="${feedLive}" nombreAnalytic="Feed/${feedLive}/${nombreGA}"/>  |</li>
										<li><analytic:enlaceGAnalytic title="${feedYahoo}" href="http://add.my.yahoo.com/content?url=${urlObtenerFeed}?idFeed=${feed.id}.1.1&idiomaNavegacion=${form.idiomaNavegacion}" clase="rss_miyahoo" textoEnlace="${feedYahoo}" nombreAnalytic="Feed/${feedYahoo}/${nombreGA}"/></li>
					
									
									</ul></td>
							</tr>
							<tr class="tr_blanco">
								<td class="new sp_top" style="width:100px">
								<strong class="bld"><bean:message key='feeds.agregador.mes'/></strong> </td>
								<td class="new">
									<bean:define id="nombreGA"><bean:message key='${feed.titulo}'/> <bean:message key='feeds.agregador.mes'/></bean:define>
									<ul class="ul_up" style="width:500px !important;">
							
										<li><analytic:enlaceGAnalytic title="${feedOtros}${urlObtenerFeed}?idFeed=${feed.id}.1.2&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}" href="${urlObtenerFeed}?idFeed=${feed.id}.1.2&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}" clase="rss_rss" textoEnlace="RSS" nombreAnalytic="Feed/RSS/${nombreGA}"/>  |</li>
										<li><analytic:enlaceGAnalytic title="${feedOtros}${urlObtenerFeed}?idFeed=${feed.id}.2.2&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}" href="${urlObtenerFeed}?idFeed=${feed.id}.2.2&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}" clase="rss_rss" textoEnlace="ATOM" nombreAnalytic="Feed/ATOM/${nombreGA}"/>  |</li>
										<li><analytic:enlaceGAnalytic title="${feedGoogle}" href="http://www.google.com/ig/add?feedurl=${urlObtenerFeed}?idFeed=${feed.id}.2.2&idiomaNavegacion=${form.idiomaNavegacion}" clase="rss_google" textoEnlace="${feedGoogle}" nombreAnalytic="Feed/${feedGoogle}/${nombreGA}"/>  |</li>
										<li><analytic:enlaceGAnalytic title="${feedBlogLines}" href="http://www.bloglines.com/sub/${urlObtenerFeed}?idFeed=${feed.id}.1.2&idiomaNavegacion=${form.idiomaNavegacion}" clase="rss_bloglines" textoEnlace="${feedBlogLines}" nombreAnalytic="Feed/${feedBlogLines}/${nombreGA}"/>  |</li>
										<li><analytic:enlaceGAnalytic title="${feedNetvibes}" href="http://www.netvibes.com/subscribe.php?url=${urlObtenerFeed}?idFeed=${feed.id}.1.2&idiomaNavegacion=${form.idiomaNavegacion}" clase="rss_netvibes" textoEnlace="${feedNetvibes}" nombreAnalytic="Feed/${feedNetvibes}/${nombreGA}"/>  |</li>
										<li><analytic:enlaceGAnalytic title="${feedLive}" href="http://www.live.com/Default.aspx?add=${urlObtenerFeed}?idFeed=${feed.id}.1.2&idiomaNavegacion=${form.idiomaNavegacion}" clase="rss_windows" textoEnlace="${feedLive}" nombreAnalytic="Feed/${feedLive}/${nombreGA}"/>  |</li>
										<li><analytic:enlaceGAnalytic title="${feedYahoo}" href="http://add.my.yahoo.com/content?url=${urlObtenerFeed}?idFeed=${feed.id}.1.2&idiomaNavegacion=${form.idiomaNavegacion}" clase="rss_miyahoo" textoEnlace="${feedYahoo}" nombreAnalytic="Feed/${feedYahoo}/${nombreGA}"/></li>									

									</ul></td>
							</tr>
							<tr class="tr_gris">
								<td class="new sp_top" style="width:100px">
								<strong class="bld"><bean:message key='feeds.agregador.anio'/></strong> </td>
							
								<td class="new">
									<bean:define id="nombreGA"><bean:message key='${feed.titulo}'/> <bean:message key='feeds.agregador.anio'/></bean:define>
									<ul class="ul_up" style="width:500px !important;">
		
										<li><analytic:enlaceGAnalytic title="${feedOtros}${urlObtenerFeed}?idFeed=${feed.id}.1.3&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}" href="${urlObtenerFeed}?idFeed=${feed.id}.1.3&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}" clase="rss_rss" textoEnlace="RSS" nombreAnalytic="Feed/RSS/${nombreGA}"/>  |</li>
										<li><analytic:enlaceGAnalytic title="${feedOtros}${urlObtenerFeed}?idFeed=${feed.id}.2.3&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}" href="${urlObtenerFeed}?idFeed=${feed.id}.2.3&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}" clase="rss_rss" textoEnlace="ATOM" nombreAnalytic="Feed/ATOM/${nombreGA}"/>  |</li>
										<li><analytic:enlaceGAnalytic title="${feedGoogle}" href="http://www.google.com/ig/add?feedurl=${urlObtenerFeed}?idFeed=${feed.id}.2.3&idiomaNavegacion=${form.idiomaNavegacion}" clase="rss_google" textoEnlace="${feedGoogle}" nombreAnalytic="Feed/${feedGoogle}/${nombreGA}"/>  |</li>
										<li><analytic:enlaceGAnalytic title="${feedBlogLines}" href="http://www.bloglines.com/sub/${urlObtenerFeed}?idFeed=${feed.id}.1.3&idiomaNavegacion=${form.idiomaNavegacion}" clase="rss_bloglines" textoEnlace="${feedBlogLines}" nombreAnalytic="Feed/${feedBlogLines}/${nombreGA}"/>  |</li>
										<li><analytic:enlaceGAnalytic title="${feedNetvibes}" href="http://www.netvibes.com/subscribe.php?url=${urlObtenerFeed}?idFeed=${feed.id}.1.3&idiomaNavegacion=${form.idiomaNavegacion}" clase="rss_netvibes" textoEnlace="${feedNetvibes}" nombreAnalytic="Feed/${feedNetvibes}/${nombreGA}"/>  |</li>
										<li><analytic:enlaceGAnalytic title="${feedLive}" href="http://www.live.com/Default.aspx?add=${urlObtenerFeed}?idFeed=${feed.id}.1.3&idiomaNavegacion=${form.idiomaNavegacion}" clase="rss_windows" textoEnlace="${feedLive}" nombreAnalytic="Feed/${feedLive}/${nombreGA}"/>  |</li>
										<li><analytic:enlaceGAnalytic title="${feedYahoo}" href="http://add.my.yahoo.com/content?url=${urlObtenerFeed}?idFeed=${feed.id}.1.3&idiomaNavegacion=${form.idiomaNavegacion}" clase="rss_miyahoo" textoEnlace="${feedYahoo}" nombreAnalytic="Feed/${feedYahoo}/${nombreGA}"/></li>
	
		
									</ul></td>
							</tr>
							</table>
							</div>
								<!--  FIN CAPA TABLA   -->		
								<!--  FIN CAPA TABLA   -->	
								<br/>	
							<a href="<html:rewrite action="/ListarFeedsCU/ListarFeedsCU.do?idFeed=${feed.id}&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}&periodicidad=si&titulo=${feed.titulo}"/>" class="new"><bean:message key="feeds.agregador.verListado"/></a>
							</div>
						<!--  FIN CAJA DE FORMULARIO   -->
						</div>
						<br />
										<!--  FIN   -->
										
									</div>
								</div>
							</div>
						</div>
						<!--  FIN GLOBO GRIS   -->
						<!--  FIN GLOBO GRIS   -->
					</logic:equal>
					<logic:equal name="feed" property="periodicidad" value="no">
						<!--  INICIO GLOBO GRIS   -->
						<!--  INICIO GLOBO GRIS   -->
						<div class="globo_gris uno_b" >
							<div class="globo_gris_01">
								<div class="globo_gris_02">
									<div class="globo_gris_03">
										<!--  INICIO CAJA DE FORMULARIO   -->
									<div class="caja_dinamica"><a title='<bean:message key="feeds.agregador.ver"/>' class="desplegado" id="pm${feed.id}" href="#" onclick="expandirCaja('m${feed.id}');return false;" onkeypress="expandirCaja('m${feed.id}');return false;"><br class="falso" /><strong id="dm${feed.id}"><bean:message key="feeds.agregador.ver"/></strong></a><h3><bean:message key='${feed.titulo}'/></h3></div>
									<div id="m${feed.id}" class="caja_cerrada" >
						
						<br />
						<!--  INICIO CAJA DE FORMULARIO   -->
						<div id="formulario" >
							<!--  INICIO CAPA TABLA   -->
							<!--  INICIO CAPA TABLA   -->					
							 <div class="caja_tabla" style="margin-bottom:0">
							<table border="0" class="administracion_usuarios" cellpadding="0"  cellspacing="0" width="100%" summary="Tabla de RSS">
							<caption><strong>Tabla de RSS</strong></caption>
							
							<tr class="tr_gris">
								<td class="new sp_top" style="width:100px">
								<td class="new">
									<bean:define id="nombreGA"><bean:message key='${feed.titulo}'/></bean:define>
									<ul class="ul_up" style="width:500px !important;">
										
										<li><analytic:enlaceGAnalytic title="${feedOtros}${urlObtenerFeed}?idFeed=${feed.id}.1&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}" href="${urlObtenerFeed}?idFeed=${feed.id}.1&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}" clase="rss_rss" textoEnlace="RSS" nombreAnalytic="Feed/RSS/${nombreGA}"/>  |</li>
										<li><analytic:enlaceGAnalytic title="${feedOtros}${urlObtenerFeed}?idFeed=${feed.id}.2&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}" href="${urlObtenerFeed}?idFeed=${feed.id}.2&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}" clase="rss_rss" textoEnlace="ATOM" nombreAnalytic="Feed/ATOM/${nombreGA}"/>  |</li>
										<li><analytic:enlaceGAnalytic title="${feedGoogle}" href="http://www.google.com/ig/add?feedurl=${urlObtenerFeed}?idFeed=${feed.id}.2&idiomaNavegacion=${form.idiomaNavegacion}" clase="rss_google" textoEnlace="${feedGoogle}" nombreAnalytic="Feed/${feedGoogle}/${nombreGA}"/>  |</li>
										<li><analytic:enlaceGAnalytic title="${feedBlogLines}" href="http://www.bloglines.com/sub/${urlObtenerFeed}?idFeed=${feed.id}.1&idiomaNavegacion=${form.idiomaNavegacion}" clase="rss_bloglines" textoEnlace="${feedBlogLines}" nombreAnalytic="Feed/${feedBlogLines}/${nombreGA}"/>  |</li>
										<li><analytic:enlaceGAnalytic title="${feedNetvibes}" href="http://www.netvibes.com/subscribe.php?url=${urlObtenerFeed}?idFeed=${feed.id}.1&idiomaNavegacion=${form.idiomaNavegacion}" clase="rss_netvibes" textoEnlace="${feedNetvibes}" nombreAnalytic="Feed/${feedNetvibes}/${nombreGA}Netvibes"/>  |</li>
										<li><analytic:enlaceGAnalytic title="${feedLive}" href="http://www.live.com/Default.aspx?add=${urlObtenerFeed}?idFeed=${feed.id}.1&idiomaNavegacion=${form.idiomaNavegacion}" clase="rss_windows" textoEnlace="${feedLive}" nombreAnalytic="Feed/${feedLive}/${nombreGA}"/>  |</li>
										<li><analytic:enlaceGAnalytic title="${feedYahoo}" href="http://add.my.yahoo.com/content?url=${urlObtenerFeed}?idFeed=${feed.id}.1&idiomaNavegacion=${form.idiomaNavegacion}" clase="rss_miyahoo" textoEnlace="${feedYahoo}" nombreAnalytic="Feed/${feedYahoo}/${nombreGA}"/></li>
					
									</ul></td>
							</tr>
							</table>
							</div>
								<!--  FIN CAPA TABLA   -->		
								<!--  FIN CAPA TABLA   -->		
								<br/>	
							<a href="<html:rewrite action="/ListarFeedsCU/ListarFeedsCU.do?idFeed=${feed.id}.1&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}&periodicidad=no&titulo=${feed.titulo}"/>" class="new"><bean:message key="feeds.agregador.verListado"/></a>
							</div>
						<!--  FIN CAJA DE FORMULARIO   -->
						</div>
						<br />
										<!--  FIN   -->
										
									</div>
								</div>
							</div>
						</div>
						<!--  FIN GLOBO GRIS   -->
						<!--  FIN GLOBO GRIS   -->
					</logic:equal>

			</logic:iterate>
	
					<!--		FIN CAJA DE FORMULARIO (Feeds)		-->
					
	<p class="parrafo_comun" id="separacion3">
	<bean:message key="feeds.agregador.opml.texto"/>: <a href="${form.urlOpmlRss }">OPML RSS</a> <bean:message key="feeds.agregador.opml.letra.o"/> <a href="${form.urlOpmlAtom }">OPML ATOM</a>. <a href="<html:rewrite action="/AgregadorFeedsCU/FormularioAgregadorFeedsQueEsEstoOPML"/>"><bean:message key="feeds.agregador.opml.queEsOpml"/></a></p>

		
	
</form>

<!--		Fin del Formulario principal		-->



</div>

<!--		Fin de la capa plantilla_contenido		-->


</tiles:put>


</tiles:insert>


<SCRIPT LANGUAGE="JavaScript">
	var ocultar="<bean:message key="feeds.agregador.ocultar"/>";
	var ver="<bean:message key="feeds.agregador.ver"/>";
	
	
	function expandirCaja (i) {

if (document.getElementById(i).className=='caja_abierta') {
document.getElementById('p' + i).className = 'caja_cerrada';
document.getElementById('d'+i).innerHTML=ver;
document.getElementById(i).className = 'caja_cerrada';


}
        else {
document.getElementById('p' + i).className = 'caja_abierta';
document.getElementById('d'+i).innerHTML=ocultar;
document.getElementById(i).className = 'caja_abierta';
}
}
</SCRIPT>	