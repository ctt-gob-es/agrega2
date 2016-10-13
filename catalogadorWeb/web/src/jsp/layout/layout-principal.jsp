<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<?xml version="1.0" encoding="iso-8859-1" ?>
<html xmlns="http://www.w3.org/1999/xhtml" lang="es" xml:lang="es">

<head lang="es" dir="ltr">
 <%@ include file="/taglib-imports.jspf" %>
 <%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %> 
 <%@ taglib uri="/WEB-INF/tags/agregaProperties.tld" prefix="agrega" %>
 <title>
	<tiles:insert attribute="title" flush="true"/>
</title>

  <link rel="shortcut icon" href="<rewrite:rewrite url='static/img/favicon.ico'/>" />

	  <link rel="stylesheet" media="screen" href="<rewrite:rewrite url='static/css/red.css'/>" type="text/css" />
	  <link rel="stylesheet" media="screen" href="<rewrite:rewrite url="logos/css/logos_patrocinadores.css"/>" type="text/css" />
      <script type="text/javascript" src="<rewrite:rewrite url='static/js/plantilla.js'/>"></script>
      <script type="text/javascript" src="<rewrite:rewrite url='static/js/curvas.js'/>"></script>
      <script type="text/javascript" src="<rewrite:rewrite url='static/js/jquery-1.7.2.min.js'/>"></script>

	<!-- JS para el uso de Ajax en la barra de progreso -->
	<script src="<rewrite:rewrite url='static/js/prototype.js'/>" type="text/javascript"></script>

<!--[if lte IE 8]>
<style title="Hoja de estilo oculta para Internet Explorer">
@import url("<rewrite:rewrite url='static/css/ancho_maximo.css'/>"); 
</style>
<![endif]-->		


</head>

<body>
<!--  CAPA QUE CONTIENE TODAS LAS DEMAS  -->
<!--  INICIO CAPA MADRE   -->
<div id="capa_madre">

<!--  INICIO CUERPO GENERAL   -->
<!--  INICIO CUERPO GENERAL   -->
<div id="cuerpo">
<div class="minwidth">
<div class="contenido_general">


<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- INICIO CABECERA  -->
<div id="cabecera">
	<h1 class="oculto"><span class="oculto"><agrega:agregaProperties property="ccaa"/></span></h1>
	<!-- INICIO METANAVEGACION   -->
	<tiles:insert attribute="metanavegacion" flush="true"/>
	<c:set var="sesion" value="${catalogadorBSession}"/>
	<c:set var="titulo" value=""/>
	<c:if test="${!empty sesion.tituloTrad}">
	    <c:set var="titulo" value="${sesion.tituloTrad}"/>
	</c:if>
	<c:choose>
		<c:when test="${!empty titulo}">
			<h2 class="h2_paq">${titulo}</h2><!-- cuando un ode se cataloge se sustituira por el titulo  -->
		</c:when>
		<c:otherwise>
			<c:if test="${!empty sesion.MBOSesion}">
				<c:if test="${!empty sesion.MBOSesion.general}">
					<c:if test="${!empty sesion.MBOSesion.general.titulo}">
						 <c:set var="titulo" value="${sesion.MBOSesion.general.titulo}"/>
						 <h2 class="h2_paq">${titulo}</h2>
					</c:if>
				</c:if>
			</c:if>
		</c:otherwise>
	</c:choose> 
	<br class="oculto"/>
</div>
<!-- FIN CABECERA  -->

<!-- Inicio cuerpo central de pagina con Menu Principal, capa Lateral y capa Central  -->
<div class="tronco_de_contenido">
<div id="madre_barra"><div class="fondolat"></div></div>


<!-- Inicio Contenido ESPECÍFICO  -->
<div id="contenido_central_largo">
	<tiles:insert attribute="body" flush="true"/>
</div>
<!-- Fin Contenido ESPECÍFICO  -->

</div>
<!-- Fin cuerpo central de pagina con Menu Principal, capa Lateral y capa Central  -->


<!-- INICIO PIE DE PAGINA -->
<tiles:insert attribute="pie" flush="true"/>
<!-- FIN PIE DE PAGINA -->

</div>
</div>
</div>
<!--  FIN CUERPO GENERAL   -->
</div>
<!--  FIN CAPA MADRE   -->
</body>
</html>