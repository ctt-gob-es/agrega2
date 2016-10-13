<?xml version="1.0" encoding="iso-8859-1" ?> 
<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="es" xml:lang="es">
<%@ include file="/taglib-imports.jspf" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tags/serverProperties.tld" prefix="server" %>
<%@ taglib uri="/WEB-INF/tags/link.tld" prefix="link" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<%@ taglib uri="/WEB-INF/tags/logos.tld" prefix="logos" %>
<html:xhtml/>
<head lang="es" dir="ltr">
	<!-- Obligatorio para trabajar con IExplorer y HTML5 -->
	<!--[if IE]>
	<script src="<rewrite:rewrite url="static/Agrega2/js/html5.js"/>"></script>
	<![endif]-->
	
	<meta name="description" content="<bean:message key="meta.descripcion"/>" />
	<meta name="keywords" content="<bean:message key="meta.palabrasClave"/>" />
<title>
	<tiles:insert attribute="title" flush="true"/>
</title>
<link rel="search" type="application/opensearchdescription+xml" title="Agrega" href="/searchPlugin/searchPlugin.xml"/>
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
<script type="text/javascript" src="<rewrite:rewrite url="static/js/swfobject.js"/>"></script>
<!--
<script type="text/javascript" src="<rewrite:rewrite url="static/js/jquery-1.3.2.js"/>"></script>
-->
<script type='text/javascript' src="<rewrite:rewrite url="static/js/jquery.autocomplete.js"/>"></script>
<tiles:insert attribute="codigo-head" flush="true"/>

<style type="text/css" media="screen">
el_flash {visibility:hidden}
</style>

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
<tiles:insert attribute="metanavegacion" flush="true"/>
</div>


<!-- AÑADIR MOTOR DE BUSQUEDA -->
	<tiles:insert attribute="motorBusqueda" flush="true"/>	

<!-- INICIO CONTENEDOR (antes llamado INICIO CUERPO GENERAL) -->
<div id="contenedor_ie">
<div id="contenedor">

<!-- NAV  -->
<nav id="menu">
<h1><a href="index.html" title="Agrega"><strong>Agrega</strong></a></h1>
<div id="nav_principal">
<ul class="metanavegacion">
<li><a href="#" id="activo"><bean:message key="menu.cabecera.buscador.agrega"/></a></li>
<li><a href="#">Buscador Externo</a></li>
<li><a href="#">&Aacute;rbol Curricular</a></li>
</ul>
</div>
</nav>
<!-- FIN NAV  -->

<!-- INICIO PRINCIPAL   -->
<section id="principal">
<tiles:insert attribute="buscador" flush="true"/>


<div class="contenido_general">

<!-- Inicio cuerpo central de pagina con Menu Principal, capa Lateral y capa Central  -->
<div class="tronco_de_contenido">
<!-- Menu de cabecera -->
<tiles:insert attribute="menu-principal" flush="true"/>
<!-- Menu lateral -->
<tiles:insert attribute="menu-lateral" flush="true"/>
<!-- Inicio Contenido ESPECÍFICO  -->
<div id="contenido_central">
<tiles:insert attribute="body" flush="true"/>
</div>
</div>
<!-- Fin cuerpo central de pagina con Menu Principal, capa Lateral y capa Central  -->

<!-- Pie de pagina -->
<tiles:insert attribute="pie" flush="true"/>

</div>

</section>
<!-- FIN PRINCIPAL -->

</div>
</div>
<!--  FIN CONTENEDOR (antes llamado CUERPO GENERAL) -->

</div>
<!--  FIN CAPA MADRE   -->
<tiles:insert attribute="end-body" flush="true"/>

<!-- Aqui van javascripts propios  -->
<script type='text/javascript' src="<rewrite:rewrite url="static/Agrega2/js/codigo.js"/>"></script>
<!-- Aqui van javascripts propios  -->

</body>
</html>
