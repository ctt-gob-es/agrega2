<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<%@ taglib uri="/WEB-INF/tags/googleAnalytic.tld" prefix="analytic" %>
<%@ taglib prefix="agrega" uri="/WEB-INF/tags/agregaProperties.tld" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<tiles:insert definition="layout-menu-0">

	<tiles:put name="title" type="string">
        <bean:message key="title.Comun"/>
    </tiles:put>
	
	<tiles:put name="body-principal" type="string">
		<%@ include file="/es/pode/visualizador/presentacion/acercaDeAgrega/acerca-de-agrega-javascript.jspf" %>
		<div class="col_der">
			<section >
				<header>
					<h2>RSS</h2>
				</header>
				
				<article class="bloque_titulares" >
					<p class="parrafo_comun" id="separacion3"><bean:message key="feeds.agregador.descripcion"/></p>
					<!--  RSS TABLA  -->
					<bean:define id="urlObtenerFeed">http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos2/AgregadorFeedsCU/FormularioAgregadorFeedsObtenerFeed.do</bean:define>
					<bean:define id="feedDefault"><agrega:agregaProperties property="feed_default"/></bean:define>
					<bean:define id="feedOtros"><bean:message key="feeds.agregador.otros"/></bean:define>
					<bean:define id="feedGoogle"><bean:message key="feeds.agregador.google"/></bean:define>
					<bean:define id="feedBlogLines"><bean:message key="feeds.agregador.blogLines"/></bean:define>
					<bean:define id="feedNetvibes"><bean:message key="feeds.agregador.netvibes"/></bean:define>
					<bean:define id="feedLive"><bean:message key="feeds.agregador.live"/></bean:define>
					<bean:define id="feedYahoo"><bean:message key="feeds.agregador.yahoo"/></bean:define>
					<div class="tabla">
						<div>
							<table class="tabla_rss" >
								<logic:iterate id="feed" collection="${form.feeds}" indexId="index">
									<logic:equal name="feed" property="periodicidad" value="si">
												<tr > 
													<td>
														<div class="celda">
														<!-- OJO esta puesto para agregador de semana -->
															<bean:define id="nombreGA"><bean:message key='${feed.titulo}'/> <bean:message key='feeds.agregador.semana'/></bean:define>
															<ul class="icons">
																<li class="i_rss">
																	<a href="${urlObtenerFeed}?idFeed=${feed.id}.1.1&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}" title="${feedOtros}${urlObtenerFeed}?idFeed=${feed.id}.1.1&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}">
																		<em>RSS</em>
																	</a>
																</li>
																<li class="i_atom">
																	<a href="${urlObtenerFeed}?idFeed=${feed.id}.2.1&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}" title="${feedOtros}${urlObtenerFeed}?idFeed=${feed.id}.2.1&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}">
																		<em>ATOM</em>
																	</a>
																</li>
																<li class="i_google">
																	<a href="http://www.google.com/ig/add?feedurl=${urlObtenerFeed}?idFeed=${feed.id}.2.1&idiomaNavegacion=${form.idiomaNavegacion}" title="${feedGoogle}">
																		<em>Google</em>
																	</a>
																</li>
																<li class="i_blogline">
																	<a href="http://www.bloglines.com/sub/${urlObtenerFeed}?idFeed=${feed.id}.1.1&idiomaNavegacion=${form.idiomaNavegacion}" title="${feedBlogLines}">
																		<em>BlogLines</em>
																	</a>
																</li>
																<li class="i_netvibes">
																	<a href="http://www.netvibes.com/subscribe.php?url=${urlObtenerFeed}?idFeed=${feed.id}.1.1&idiomaNavegacion=${form.idiomaNavegacion}" title="${feedNetvibes}">
																		<em>Netvibes</em>
																	</a>
																</li>
																<li class="i_windows">
																	<a href="http://www.live.com/Default.aspx?add=${urlObtenerFeed}?idFeed=${feed.id}.1.1&idiomaNavegacion=${form.idiomaNavegacion}" title="${feedLive}">
																		<em>Windows Live</em>
																	</a>
																</li>
																<li class="i_yahoo">
																	<a href="http://add.my.yahoo.com/content?url=${urlObtenerFeed}?idFeed=${feed.id}.1.1&idiomaNavegacion=${form.idiomaNavegacion}" title="${feedYahoo}">
																		<em>Yahoo</em>
																	</a>
																</li>
																<li class="ver_listados"><a href="#" class="sec_imagelist poppup<fmt:formatNumber minIntegerDigits="2" type="number" pattern="##" value="${index}" />">Ver listado</a></li>
															</ul>
															<a target="_blank" href="${urlObtenerFeed}?idFeed=${feed.id}.1.1&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}" title="${feedOtros}${urlObtenerFeed}?idFeed=${feed.id}.1.1&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}" class="titular">
																<bean:message key='${feed.titulo}'/>
															</a>
														</div>
													</td>
												</tr>										
									</logic:equal>
									<logic:equal name="feed" property="periodicidad" value="no">
												<tr > 
													<td>
														<div class="celda">
														<!-- OJO esta puesto para agregador de semana -->
															<bean:define id="nombreGA"><bean:message key='${feed.titulo}'/> <bean:message key='feeds.agregador.semana'/></bean:define>
															<ul class="icons">
																<li class="i_rss">
																	<a href="${urlObtenerFeed}?idFeed=${feed.id}.1&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}" title="${feedOtros}${urlObtenerFeed}?idFeed=${feed.id}.1&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}">
																		<em>RSS</em>
																	</a>
																</li>
																<li class="i_atom">
																	<a href="${urlObtenerFeed}?idFeed=${feed.id}.2&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}" title="${feedOtros}${urlObtenerFeed}?idFeed=${feed.id}.2&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}">
																		<em>ATOM</em>
																	</a>
																</li>
																<li class="i_google">
																	<a href="http://www.google.com/ig/add?feedurl=${urlObtenerFeed}?idFeed=${feed.id}.2&idiomaNavegacion=${form.idiomaNavegacion}" title="${feedGoogle}">
																		<em>Google</em>
																	</a>
																</li>
																<li class="i_blogline">
																	<a href="http://www.bloglines.com/sub/${urlObtenerFeed}?idFeed=${feed.id}.1&idiomaNavegacion=${form.idiomaNavegacion}" title="${feedBlogLines}">
																		<em>BlogLines</em>
																	</a>
																</li>
																<li class="i_netvibes">
																	<a href="http://www.netvibes.com/subscribe.php?url=${urlObtenerFeed}?idFeed=${feed.id}.1&idiomaNavegacion=${form.idiomaNavegacion}" title="${feedNetvibes}">
																		<em>Netvibes</em>
																	</a>
																</li>
																<li class="i_windows">
																	<a href="http://www.live.com/Default.aspx?add=${urlObtenerFeed}?idFeed=${feed.id}.1&idiomaNavegacion=${form.idiomaNavegacion}" title="${feedLive}">
																		<em>Windows Live</em>
																	</a>
																</li>
																<li class="i_yahoo">
																	<a href="http://add.my.yahoo.com/content?url=${urlObtenerFeed}?idFeed=${feed.id}.1&idiomaNavegacion=${form.idiomaNavegacion}" title="${feedYahoo}">
																		<em>Yahoo</em>
																	</a>
																</li>
																<li class="ver_listados"><a href="#" class="sec_imagelist poppup<fmt:formatNumber minIntegerDigits="2" type="number" pattern="##" value="${index}" />">Ver listado</a></li>
															</ul>
															<a target="_blank" href="${urlObtenerFeed}?idFeed=${feed.id}.1&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}" title="${feedOtros}${urlObtenerFeed}?idFeed=${feed.id}.1&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}" class="titular">
																<bean:message key='${feed.titulo}'/>
															</a>
														</div>
													</td>
												</tr>
									</logic:equal>
								</logic:iterate>
							</table>
						</div>
					</div>
						
					<!--  FIN TABLA  --> <!--  TABLA  -->
					<!--<p>Usted tambi&eacute;n puede suscribirse a todos nuestros feeds a trav&eacute;s de nuestros dos ficheros OPML: <a href="#">OPML RSS</a> o <a href="#">OPML ATOM</a>
					</p>-->
					<p class="parrafo_comun" id="separacion3">
						<bean:message key="feeds.agregador.opml.texto"/>: <a href="${form.urlOpmlRss }">OPML RSS</a> <bean:message key="feeds.agregador.opml.letra.o"/> <a href="${form.urlOpmlAtom }">OPML ATOM</a>.
					</p>
				</article>
			<!--  -->
			</section>
		</div>
				<!-- Aqui va popup oculto -->
		<section id="ocultos">
			<logic:iterate id="feed" collection="${form.feeds}" indexId="index">
				<div class="thumb_list"id="pop_up_<fmt:formatNumber minIntegerDigits="2" type="number" pattern="##" value="${index}" />">
					<a class="close close_x sprited" href="#">close</a>
					<div class="showhide clearfix ">
						<h4><bean:message key='${feed.titulo}'/></h4>
						<div class="escroll">
							<table  class="tabla_rss_interna">
								<tr>
									<th ><bean:message key='${feed.titulo}'/></th>
								</tr>
								<logic:equal name="feed" property="periodicidad" value="si">
									<tr class="tr_blanco fc">
										<td ><span class="bld">1.</span> &nbsp;<a target="_blank" href="${urlObtenerFeed}?idFeed=${feed.id}.1.1&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}" title="${feedOtros}${urlObtenerFeed}?idFeed=${feed.id}.1.1&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}"><bean:message key='feeds.agregador.semana'/></a></td>
									</tr>
									<tr class="tr_gris">
										<td ><span class="bld">2.</span> &nbsp;<a target="_blank" href="${urlObtenerFeed}?idFeed=${feed.id}.1.2&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}" title="${feedOtros}${urlObtenerFeed}?idFeed=${feed.id}.1.1&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}"><bean:message key='feeds.agregador.mes'/></a></td>
									</tr>
									<tr class="tr_blanco">
										<td ><span class="bld">4.</span> &nbsp;<a target="_blank" href="${urlObtenerFeed}?idFeed=${feed.id}.1.3&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}" title="${feedOtros}${urlObtenerFeed}?idFeed=${feed.id}.1.1&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}"><bean:message key='feeds.agregador.anio'/></a></td>
									</tr>
								</logic:equal>
								<logic:equal name="feed" property="periodicidad" value="no">
									<tr class="tr_blanco fc">
										<td ><span class="bld">1.</span> &nbsp;<a target="_blank" href="${urlObtenerFeed}?idFeed=${feed.id}.1&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}" title="${feedOtros}${urlObtenerFeed}?idFeed=${feed.id}.1.1&feedNum=${feedDefault}&idiomaNavegacion=${form.idiomaNavegacion}"><bean:message key='${feed.titulo}'/></a></td>
									</tr>
								</logic:equal>
							</table>
						</div>
					</div> 
				</div>
			</logic:iterate>
		</section>
	</tiles:put>
</tiles:insert>


<!-- Aqui van javascripts propios  -->
<script type="text/javascript">
	$(window).load(function() {
		setTimeout(function(){
			$('#slider2').nivoSlider({effect:'sliceDown',directionNav:false,startSlide:14, pauseTime:9000, captionOpacity:0,pauseOnHover:false, animSpeed:500 });
		}, 1000);
	});
</script>
<script type='text/javascript' src="<rewrite:rewrite url="static/Agrega2/js/acceso.js"/>"></script>
<script type='text/javascript' src="<rewrite:rewrite url="static/Agrega2/js/jquery.nivo.slider.pack.js"/>"></script>
<script type='text/javascript' src="<rewrite:rewrite url="static/Agrega2/js/jquery.lightbox_me.js"/>"></script>  
<!-- Aqui van javascripts propios  -->
<!-- Aqui van javascripts propios  -->
