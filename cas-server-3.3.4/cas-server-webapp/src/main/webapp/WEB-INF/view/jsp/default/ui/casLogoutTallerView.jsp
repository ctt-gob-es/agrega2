
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ taglib prefix="server" uri="/WEB-INF/tags/serverProperties.tld" %>
<%@ taglib prefix="agrega" uri="/WEB-INF/tags/agregaProperties.tld" %>
<%@ taglib prefix="i18n" uri="/WEB-INF/tags/i18nProperties.tld" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<%@ taglib prefix="buscadorCas" uri="/WEB-INF/tags/buscadorCasTag.tld" %>
<%@ taglib uri="/WEB-INF/tags/logos.tld" prefix="logos" %>
<%@ page import="java.io.InputStream"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ taglib prefix="el" uri="/WEB-INF/tags/configurarPortalTag.tld" %>



<?xml version="1.0" encoding="iso-8859-1" ?> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="es" xml:lang="es">
<head lang="en" dir="ltr">

<title>
	
	Agrega - Portada

</title>
<link rel="shortcut icon" href="<rewrite:rewrite url="static/img/favicon.ico"/>" />
<link rel="stylesheet" media="screen"  type="text/css" href="<rewrite:rewrite url="static/css/red.css"/>" />
<link rel="stylesheet" media="screen" href="<rewrite:rewrite url="logos/css/logos_patrocinadores.css"/>" type="text/css" />
<script type="text/javascript" src="<rewrite:rewrite url="static/js/plantilla.js"/>"></script>
<script type="text/javascript" src="<rewrite:rewrite url="static/js/curvas.js"/>"></script>
<script type="text/javascript" src="<rewrite:rewrite url='static/js/jquery-1.7.2.min.js'/>"></script>

<!--[if lte IE 6]>
<style title="Hoja de estilo oculta para Internet Explorer">
@import url("/static/css/ancho_maximo.css"); 
</style>
<![endif]-->

</head>

<body>

<!--  CAPA QUE CONTIENE TODAS LAS DEMAS  -->
<!--  INICIO CAPA MADRE   -->
<div id="capa_madre">
<!--  INICIO CUERPO GENERAL   -->
<div id="cuerpo">
<div class="minwidth">
<div class="contenido_general">

<!-- Aqui va la cabecera -->
<div id="cabecera">
<h1><span><agrega:agregaProperties property="ccaa"/></span></h1>

<!--AJAX 20660905-->


<!--FIN AJAX 20660905-->
<!-- INICIO METANAVEGACION   -->
<div id="metanavegacion">
<ol>
		<li class="oculto"><a href="#contenido_central" title="Contenido Principal"><strong><spring:message code="contenido"/></strong></a></li>
		<!-- Estos dos links cambian cuando el usuario se ha autenticado -->
		<!-- TODO: Cambiar en fase II cuando se use autenticacion -->
		<!-- <li><a href="#" title="Accesibilidad" id="registro">Accesibilidad</a></li>
		<li><a href="#" title="Acceso Zona Restringida" id="restringida">Acceso Zona Restringida</a></li> -->
		<!-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->
			
		
</ol>
</div>
<!-- FIN METANAVEGACION   -->
<!--AJAX 20660905-->
<!--FIN AJAX 20660905-->

<br class="oculto" />
<a href="http://www.proyectoagrega.es/default/Inicio" title="<spring:message code="paginaInicio"/>" id="logo"><span><spring:message code="paginaInicio"/></span></a>
<br class="oculto" />
</div>

<!-- Inicio cuerpo central de pagina con Menu Principal, capa Lateral y capa Central  -->
<div class="tronco_de_contenido">
<!-- Menu de cabecera -->
<!--AJAX 20660905-->
<!--FIN AJAX 20660905-->
<!-- Inicio MENU PRINCIPAL -->
<!-- Inicio MENU PRINCIPAL -->
<div id="madre_barra">
<div class="fondolat"></div>
</div>
<!-- Fin MENU PRINCIPAL -->
<!-- Fin MENU PRINCIPAL -->



<!-- Inicio Contenido ESPECÍFICO  -->



<!--AJAX 20660905-->


<!--FIN AJAX 20660905-->


<div id="contenido_central_largo">
	
<div class="plantilla_contenido">
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div id="formulario" class="ali_c">

			<br/>
			 <p><img src="<rewrite:rewrite url="static/img/v.gif"/>"/><spring:message code="salirTaller"/>.</p>
			 <br /><br />
				<br />
				</div>
				<!--  FIN CAJA DE FORMULARIO   -->
			</div>
		</div>

	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->

<!-- Inicio Botones  -->
<!-- Inicio Botones  -->
<fieldset class="tipo ft_centrada">
<input type="button" class="boton_125" value="<spring:message code="aceptar"/>" onClick="window.close();"/>
</fieldset>
<!-- Fin Botones  -->
<!-- Fin Botones  -->
</div>
</div>


<!-- Fin cuerpo central de pagina con Menu Principal, capa Lateral y capa Central  -->

<!-- Pie de pagina -->
<!--AJAX 20660905-->


<!--FIN AJAX 20660905-->
<!-- INICIO PIE DE PAGINA -->
<!-- INICIO PIE DE PAGINA -->
<div id="pie_pagina">
<hr />
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- INCLUDE DE LOS LOGOS DE LOS COLABORADORES Y PATROCINADORES  -->
<div id="patros_con_ccaa">
<logos:renderLogoSet htmlFile="uploads/logos/logos-pie_actualizado.html" language="${param.locale}"/>
</div>
<!-- FIN DEL INCLUDE DE LOS LOGOS DE LOS COLABORADORES Y PATROCINADORES  -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->

<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
</div>


</div>
</div>
</div>
</div>
<!--  FIN CUERPO GENERAL   -->

</div>
<!--  FIN CAPA MADRE   -->
</body>
</html>