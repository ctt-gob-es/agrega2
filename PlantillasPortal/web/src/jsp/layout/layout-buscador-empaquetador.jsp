<?xml version="1.0" encoding="iso-8859-1" ?> 
<!DOCTYPE html>
<html lang="es">
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<%@ taglib uri="/WEB-INF/tags/logos.tld" prefix="logos" %>
<html:xhtml/>
<head lang="es" dir="ltr">
<!-- Obligatorio para trabajar con IExplorer y HTML5 -->
<!--[if IE]>
<script src="<rewrite:rewrite url="static/Agrega2/js/html5.js"/>"></script>
<![endif]-->

<title><tiles:insert attribute="title" flush="true"/></title>
<link rel="shortcut icon" href="<rewrite:rewrite url="static/img/favicon.ico"/>" />

<link rel="stylesheet" href="<rewrite:rewrite url="static/Agrega2/css/red.css"/>" type="text/css" />
<link rel="stylesheet" href="<rewrite:rewrite url="static/Agrega2/css/handheld.css"/>" type="text/css" />
<script type="text/javascript" src="<rewrite:rewrite url="static/Agrega2/js/jquery-1.4.2.min.js"/>"></script>

<!--
<link rel="stylesheet" media="screen" href="<rewrite:rewrite url="static/css/red.css"/>" type="text/css" />
<link rel="stylesheet" media="screen" href="<rewrite:rewrite url="logos/css/logos_patrocinadores.css"/>" type="text/css" />
-->

<script type="text/javascript" src="<rewrite:rewrite url="static/js/plantilla.js"/>"></script>
<script type="text/javascript" src="<rewrite:rewrite url="static/js/curvas.js"/>"></script>
</head>

<!-- Para controlar las diferentes versiones de IExplorer -->
<!--[if lt IE 7 ]> <body class="ie6"> <![endif]-->
<!--[if IE 7 ]> <body class="ie7"> <![endif]-->
<!--[if IE 8 ]> <body class="ie8"> <![endif]-->
<!--[if IE 9 ]> <body class="ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<body > <!--<![endif]-->


<!--  CAPA QUE CONTIENE TODAS LAS DEMAS  -->
<!--  INICIO CAPA MADRE   -->
<div id="madre">

<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- INICIO CABECERA  -->

<div id="cabecera">
<h1 class="oculto"><span class="oculto"><bean:message key="cabecera.buscador.empaquetador"/></span></h1>

<!-- INICIO METANAVEGACION   -->
<!-- INICIO METANAVEGACION   -->
<div id="metanavegacion">
<ol>
		<li class="oculto"><a href="#contenido_central" title="Contenido Principal"><strong>Contenido</strong></a></li>
		<li><a href="#" onclick="window.close()" title="<bean:message key="metanavegacion.cerrar"/>" id="li_inicial"><bean:message key="metanavegacion.cerrar"/></a></li>
</ol>
</div>
<!-- FIN METANAVEGACION   -->
<!-- FIN METANAVEGACION   -->

<h2 id="arb"><span><bean:message key="cabecera.buscador.empaquetador"/></span></h2>

<div class="limpiar"><br class="oculto" /></div>
</div>


<!-- INICIO CONTENEDOR (antes llamado INICIO CUERPO GENERAL) -->
<div id="contenedor_ie">
<div id="contenedor">
<div class="contenido_general">


<!-- Inicio cuerpo central de pagina con Menu Principal, capa Lateral y capa Central  -->
<!-- Inicio cuerpo central de pagina con Menu Principal, capa Lateral y capa Central  -->
<div class="tronco_de_contenido">

<div id="madre_barra">
<div class="fondolat"></div>
</div>

<!-- Inicio Contenido ESPECÍFICO  -->
<!-- Inicio Contenido ESPECÍFICO  -->
<div id="contenido_central_largo">
<tiles:insert attribute="body" flush="true"/>
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
</div>
<!-- Fin Contenido ESPECÍFICO  -->
<!-- Inicio Contenido ESPECÍFICO  -->
</div>
<!-- Fin cuerpo central de pagina con Menu Principal, capa Lateral y capa Central  -->
<!-- Fin cuerpo central de pagina con Menu Principal, capa Lateral y capa Central  -->

<tiles:insert attribute="pie" flush="true"/>

</div>
</div>
</div>
<!--  FIN CONTENEDOR (antes llamado CUERPO GENERAL) -->

</div>
<!--  FIN CAPA MADRE   -->
<!--  FIN CAPA MADRE   -->

<!-- Aqui van javascripts propios  -->
<script type='text/javascript' src="<rewrite:rewrite url="static/Agrega2/js/codigo.js"/>"></script>
<!-- Aqui van javascripts propios  -->

</body>
</html>
