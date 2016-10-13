<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tags/imagenNoticias.tld" prefix="image" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>

<tiles:insert definition="layout-menu-0">

<tiles:put name="title" type="string">
	<bean:message key="title.Comun"/>
</tiles:put>

<tiles:put name="body-principal" type="string">
<%@ include file="/es/pode/visualizador/presentacion/noticia/noticia-vars.jspf" %>

<div class="col_der" id="noticias">
<section class="detalle_noticia">

<%@ include file="/layout/messages.jsp" %>

	<header>
		<a href="<rewrite:rewrite url="${initParam.url_noticias }"/>" class="flotante"><bean:message key="mostrarNoticia.verMasNoticias"/> <span>></span></a></a>
  		<hgroup>
    			<h2><bean:message key="portada.mensaje.noticias"/></h2>
    			<h3>${form.titulo}</h3>
   		</hgroup>
	</header>

	<c:set var="noticiavo" value="${form.noticiavo}"/>
	<div id="contenido_noticia">
		<em class="fecha"><fmt:formatDate value="${noticiavo.fechaPublicacion.time}" pattern="dd/MM/yyyy"/></em>
		<p class="entradilla">${form.resumen}</p>
		<!--************************          TAG QUE PINTA LAS IMAGENES DE LAS NOTICIAS Y EL TEXTO      ****************************-->
		<image:imagenNoticias alineamiento="${form.alineamientoImg}" cuerpo="${form.cuerpo}" imagen="${form.URLImagen}"/> 	
	</div>	

	<a class="flotante f_bot" href="<rewrite:rewrite url="${initParam.url_noticias_portada}"/>"><bean:message key="mostrarNoticia.verMasNoticias"/> <span>></span></a>
	<span class="etiquetas clearfix">
		<a href="<rewrite:rewrite url="${initParam.url_noticias_categorias}"/>/${form.nombreCategoriaCodex}/${form.idCategoria }">
			${form.categoria}
		</a>
	</span>
</section>
</div>

</tiles:put>
</tiles:insert>
