<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/WEB-INF/tags/imagenPortada.tld" prefix="portada" %>
<%@ taglib uri="/WEB-INF/tags/googleAnalytic.tld" prefix="analytic" %>
<%@ taglib prefix="agrega" uri="/WEB-INF/tags/agregaProperties.tld" %>

<html:xhtml/>

<%@ include file="/taglib-imports.jspf" %>
<%@ include file="/layout/messages.jsp" %>

<div class="col_izq">		
	<section class="bloque noticias">
		<header>
		<hgroup>
			<h2 class="noticias" title="Noticias"><bean:message key="portada.mensaje.noticias"/></h2>
		</hgroup>
		</header>
	
		<c:if test="${fn:length(form.noticias) < 1}" > 
			<p><bean:message key="listaNoticias.vacio"/></p><br>
		</c:if> 	
		<c:if test="${fn:length(form.noticias) > 0}" >
			<c:forEach items="${form.noticias}"  var="noticia" varStatus="status">
				<header>
					<h3>
					<a href="<rewrite:rewrite url="${initParam.url_portada_noticias}"/>/${fn:replace(fn:replace(noticia.tituloCodex,"?", "_"),"&#37","_")}/${noticia.id }">${noticia.titulo}</a><br/>
					</h3>
				</header>
				<p><fmt:formatDate value="${noticia.fechaPublicacion.time}" pattern="dd/MM/yyyy"/>, ${noticia.resumen}</p>
				<span><bean:message key="mostrarNoticia.categoria"/> 
				<a href="<rewrite:rewrite url="${initParam.url_noticias_categorias}"/>/${noticia.categoriaCodex}/${noticia.idCategoria}" class="cate">${noticia.categoria}</a></span></p>
			</c:forEach>
			
			<p class="texto_der sin_p"><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos2/Noticias/Noticias.do" class="flotante"><bean:message key="mostrarNoticia.verMasNoticias"/> <span>></span></a></p>
		</c:if> 				
	</section>
	
	
	<br>
    <section class="bloque descargas">
		<header>
		<hgroup>
			<h2 class="descargas" title="Descargas"><bean:message key="listaDescargas.title"/></h2>
		</hgroup>
		</header>
		
		<c:if test="${fn:length(form.descargas) < 1}" >
			<p><bean:message key="listaDescargas.vacio"/></p><br>
		</c:if> 	
		<c:if test="${fn:length(form.descargas) > 0}" >
			<%int cont = 1;%>	
			<c:forEach items="${form.descargas}" var="descarga" varStatus="status">
				<header>
					<h3>
					<a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos2/Descargas/Descargas.do?identificador=${descarga.identificador}&titulo=${descarga.titulo}"><bean:message key="listaDescargas.descargar"/> ${descarga.titulo} (${descarga.peso})</a><br/>
					<!--
					<a href="<html:rewrite action="/Descargas/DescargasDescarga"/>?identificador=${descarga.identificador}&titulo=${descarga.titulo}"><bean:message key="listaDescargas.descargar"/> ${descarga.titulo} (${descarga.peso})</a><br/>
					-->
					</h3>
				</header>				
	        	<c:set var="fin" value="295"/>
        
				<c:if test="${fn:length(descarga.descripcion)>0}">
					<c:out value="${param.number}" />					
					<c:choose>
						<c:when test="${fn:length(descarga.descripcion)>fin}">
							<p>${fn:substring(descarga.descripcion, 0,fin)}<a href="<%=cont%>"  title="Ver más" class="linkTextoDescarga"><span> ...[+]</span></a></p>
						</c:when>					
						<c:otherwise>
							<p>${descarga.descripcion}</p>					
						</c:otherwise>
					</c:choose>
				</c:if>      
				
				<section id="ocultos<%=cont%>">
					<div class="thumb_list" id="<%=cont%>">			
						<a class="close close_x sprited" href="#">close</a>
						<div class="showhide clearfix ">			
							<div class="escroll"> 				
								<h4><p><c:out value="${descarga.titulo}"/></p>	</h4>					
								<div align="left">					
									<p>${descarga.descripcion}<br />
								</div>			  				
							</div>
						</div> 
					</div>
				</section>	
				<%cont = cont+1;%>			
			</c:forEach>
			<p class="texto_der sin_p"><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos2/Descargas/Descargas.do" class="flotante"><bean:message key="listaDescargas.verMasDescargas"/> <span>></span></a></p>
			<!--
			<p class="texto_der sin_p"><a href="<html:rewrite action="/Descargas/Descargas.do"/>" class="flotante"><bean:message key="listaDescargas.verMasDescargas"/> <span>></span></a></p>
			-->
		</c:if> 	
    </section>

</div>


<script type="text/javascript" src="<rewrite:rewrite url="static/Agrega2/js/jquery.lightbox_me.js"/>"></script>
<script type="text/javascript">
        $(function() {
 function launch() {
                 $('.linkTextoDescarga').lightbox_me({centered: true, onLoad: function() { $('.linkCapa').find('input:first').focus()}});
            }
            
			$('.linkTextoDescarga').click(function(e) {
				$("#"+$(this).attr("href")).lightbox_me({centered: false, onLoad: function() {
					$(this).find("input:first").focus();
				}});
				e.preventDefault();
			});
            
             $('table tr:nth-child(even)').addClass('stripe');
          
        });
  </script>