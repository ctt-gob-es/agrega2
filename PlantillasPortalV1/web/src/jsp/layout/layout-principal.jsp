<?xml version="1.0" encoding="iso-8859-1" ?> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
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
	<meta name="description" content="<bean:message key="meta.descripcion"/>" />
	<meta name="keywords" content="<bean:message key="meta.palabrasClave"/>" />
<title>
	<tiles:insert attribute="title" flush="true"/>
</title>
<link rel="search" type="application/opensearchdescription+xml" title="Agrega" href="/searchPlugin/searchPlugin.xml"/>
<link rel="shortcut icon" href="<rewrite:rewrite url="static/img/favicon.ico"/>" />
<link rel="stylesheet" media="screen" href="<rewrite:rewrite url="static/css/red.css"/>" type="text/css" />
<link rel="stylesheet" media="screen" href="<rewrite:rewrite url="logos/css/logos_patrocinadores.css"/>" type="text/css" />
<script type="text/javascript" src="<rewrite:rewrite url="static/js/plantilla.js"/>"></script>
<script type="text/javascript" src="<rewrite:rewrite url="static/js/curvas.js"/>"></script>
<script type="text/javascript" src="<rewrite:rewrite url="static/js/swfobject.js"/>"></script>
<script type="text/javascript" src="<rewrite:rewrite url="static/js/jquery-1.7.2.min.js"/>"></script>
<script type='text/javascript' src="<rewrite:rewrite url="static/js/jquery.autocomplete.js"/>"></script>
<tiles:insert attribute="codigo-head" flush="true"/>

<style type="text/css" media="screen">
el_flash {visibility:hidden}
</style>

</head>

<body>

<!--  CAPA QUE CONTIENE TODAS LAS DEMAS  -->
<!--  INICIO CAPA MADRE   -->
<div id="capa_madre">
<!-- AÑADIR MOTOR DE BUSQUEDA -->
	<tiles:insert attribute="motorBusqueda" flush="true"/>	
<!--  INICIO CUERPO GENERAL   -->
<div id="cuerpo">
<div class="minwidth">
<div class="contenido_general">

	

<!-- Aqui va la cabecera -->
<div id="cabecera">
<h1>
	<span>
		<server:serverProperties property="ccaa"/>
	</span>
</h1>
<tiles:insert attribute="metanavegacion" flush="true"/>
<tiles:insert attribute="buscador" flush="true"/>
</div>

<!-- Inicio cuerpo central de pagina con Menu Principal, capa Lateral y capa Central  -->
<div class="tronco_de_contenido">
<!-- Menu de cabecera -->
<tiles:insert attribute="menu-principal" flush="true"/>
<!-- Menu lateral -->
<tiles:insert attribute="menu-lateral" flush="true"/>
<!-- Inicio Contenido ESPECÍFICO  -->
<div id="contenido_central">
<tiles:insert attribute="body" flush="true"/>
<!--  FIN CAPA MADRE   -->
</div>
</div>
<!-- Fin cuerpo central de pagina con Menu Principal, capa Lateral y capa Central  -->

<!-- Pie de pagina -->
<tiles:insert attribute="pie" flush="true"/>

</div>
</div>
</div>
<!--  FIN CUERPO GENERAL   -->

</div>
<!--  FIN CAPA MADRE   -->
<tiles:insert attribute="end-body" flush="true"/>
</body>
</html>
