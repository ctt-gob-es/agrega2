<?xml version="1.0" encoding="iso-8859-1" ?> 
<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="es" xml:lang="es">

<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="/WEB-INF/tags/serverProperties.tld" prefix="server" %>
<%@ taglib uri="/WEB-INF/tags/logos.tld" prefix="logos" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>

<html:xhtml/>
<head lang="es" dir="ltr">
<!-- Obligatorio para trabajar con IExplorer y HTML5 -->
<!--[if IE]>
<script src="<rewrite:rewrite url="static/Agrega2/js/html5.js"/>"></script>
<![endif]-->

<title>
	<tiles:insert attribute="title" flush="true"/>
</title>
<link rel="search" type="application/opensearchdescription+xml" title="Agrega" href="<rewrite:rewrite url="searchPlugin/searchPlugin.xml"/>" />
<link rel="shortcut icon" href="<rewrite:rewrite url="static/img/favicon.ico"/>"/>

<link rel="stylesheet" href="<rewrite:rewrite url="static/Agrega2/css/red.css"/>" type="text/css" />
<link rel="stylesheet" href="<rewrite:rewrite url="static/Agrega2/css/handheld.css"/>" type="text/css" />
<script type="text/javascript" src="<rewrite:rewrite url="static/Agrega2/js/jquery-1.4.2.min.js"/>"></script>

<!--
<link rel="stylesheet" media="screen" href="<rewrite:rewrite url="static/css/red.css"/>" type="text/css" />
<link rel="stylesheet" media="screen" href="<rewrite:rewrite url="logos/css/logos_patrocinadores.css"/>" type="text/css" />
-->

<script type="text/javascript" src="<rewrite:rewrite url="static/js/plantilla.js"/>"></script>
<script type="text/javascript" src="<rewrite:rewrite url="static/js/curvas.js"/>"></script>
<!--
<script type="text/javascript" src="<rewrite:rewrite url="static/js/jquery-1.3.2.js"/>"></script>
-->
	<script type='text/javascript' src="<rewrite:rewrite url="static/js/jquery.autocomplete.js"/>"></script>
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

<!-- Aqui va la cabecera -->
<div id="cabecera">
<!--
<h1>
	<span>
		<server:serverProperties property="ccaa"/>
	</span>
</h1>
-->
<tiles:insert attribute="metanavegacion" flush="true"/>
<tiles:insert attribute="buscador" flush="true"/>
</div>

<!-- INICIO CONTENEDOR (antes llamado INICIO CUERPO GENERAL) -->
<div id="contenedor_ie">
<div id="contenedor">
<div class="contenido_general">

<!-- Inicio cuerpo central de pagina con Menu Principal, capa Lateral y capa Central  -->
<div class="tronco_de_contenido">

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
<!--  FIN CONTENEDOR (antes llamado CUERPO GENERAL) -->
</div>
<!--  FIN CAPA MADRE   -->
</div>

<!-- Aqui van javascripts propios  -->
<script type='text/javascript' src="<rewrite:rewrite url="static/Agrega2/js/codigo.js"/>"></script>
<!-- Aqui van javascripts propios  -->

</body>
</html>
