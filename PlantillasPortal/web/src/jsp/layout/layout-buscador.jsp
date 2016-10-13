<!DOCTYPE html>

<html lang="es" class="no-js">
<%@ page import="es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tags/serverProperties.tld" prefix="server" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<%@ include file="/taglib-imports.jspf" %>
<%@ taglib uri="/WEB-INF/tags/logos.tld" prefix="logos" %>
<%@ taglib uri="/WEB-INF/tags/idiomasBanderas.tld" prefix="banderas" %>
<%@ taglib prefix="agrega" uri="/WEB-INF/tags/agregaProperties.tld" %>
<html:xhtml/>

	<head lang="es" dir="ltr">
	<!-- Obligatorio para trabajar con IExplorer y HTML5 -->
	<!--[if IE]>
	<script src="<rewrite:rewrite url="static/Agrega2/js/html5.js"/>"></script>
	<![endif]-->

	<meta name="description" content="<bean:message key="meta.descripcion"/>" />
	<meta name="keywords" content="<bean:message key="meta.palabrasClave"/>" />
	<title><tiles:insert attribute="title" flush="true"/></title>
	<link rel="shortcut icon" href="<rewrite:rewrite url="static/img/favicon.ico"/>" />
	<logic:equal name="form" property="tipoLayoutBuscador" value="FEDERADO">	
		
		<link rel="stylesheet" href="<rewrite:rewrite url="static/Agrega2/css/red.css"/>" type="text/css" />
		<link rel="stylesheet" href="<rewrite:rewrite url="static/Agrega2/css/handheld.css"/>" type="text/css" />

	</logic:equal>
	<logic:notEqual name="form" property="tipoLayoutBuscador" value="FEDERADO">	
		<logic:equal name="form" property="tipoLayoutBuscador" value="NEUTRO">
			
			<link rel="stylesheet" href="<rewrite:rewrite url="static/Agrega2/css/red.css"/>" type="text/css" />
			<link rel="stylesheet" href="<rewrite:rewrite url="static/Agrega2/css/handheld.css"/>" type="text/css" />

		</logic:equal>
		<logic:notEqual name="form" property="tipoLayoutBuscador" value="NEUTRO">	

			<link rel="stylesheet" href="<rewrite:rewrite url="static/Agrega2/css/red.css"/>" type="text/css" />
			<link rel="stylesheet" href="<rewrite:rewrite url="static/Agrega2/css/handheld.css"/>" type="text/css" />

		</logic:notEqual>	
	</logic:notEqual>
		
	<!--
	<script type="text/javascript" src="<rewrite:rewrite url="static/js/plantilla.js"/>"></script>
	<script type="text/javascript" src="<rewrite:rewrite url="static/js/curvas.js"/>"></script>
	-->
	<script type="text/javascript" src="<rewrite:rewrite url="static/Agrega2/js/jquery-1.4.2.min.js"/>"></script>
	
	<!--<script type='text/javascript' src="<rewrite:rewrite url="static/js/jquery.autocomplete.js"/>"></script>-->
	<!--<script type='text/javascript' src="<rewrite:rewrite url="static/js/jquery.auto-complete-1.1.1.js"/>"></script>-->
	
	<tiles:insert attribute="codigo-head" flush="true"/>

	<link rel="search" type="application/opensearchdescription+xml" title="Agrega" href="<rewrite:rewrite url="searchPlugin/searchPlugin.xml"/>"/>
	</head>
	<!-- Para controlar las diferentes versiones de IExplorer -->
	<!--[if lt IE 7 ]> <body class="ie6"> <![endif]-->
	<!--[if IE 7 ]> <body class="ie7"> <![endif]-->
	<!--[if IE 8 ]> <body class="ie8"> <![endif]-->
	<!--[if IE 9 ]> <body class="ie9"> <![endif]-->
	<!--[if (gt IE 9)|!(IE)]><!-->
	<body > <!--<![endif]-->
		<div id="madre">			
						
			<logic:equal name="form" property="tipoLayoutBuscador" value="EMPAQUETADOR">
				<div id="cabecera">								
					<tiles:insert attribute="metanavegacion" flush="true"/>
				</div>	
									
				<!-- INICIO CONTENEDOR (antes llamado INICIO CUERPO GENERAL) -->
				<div id="contenedor_ie">
				<div id="contenedor">				
			
				<!-- INICIO Popup de login -->
				<tiles:insert attribute="login" flush="true"/>
				<!-- FIN Popup de login -->
				
				<!-- NAV  -->
				<nav id="menu">
				<h1><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos2/Portada/Portada.do" title="<bean:message key='menu.cabecera.agrega'/>"><strong><bean:message key="menu.cabecera.agrega"/></strong></a></h1>
				<div id="nav_principal">
				<ul class="metanavegacion">
				<li><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos2/Portada/Portada.do" id="activo"><bean:message key="menu.cabecera.buscador.agrega"/></a></li>
				<li><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos2/BusquedaGoogleCU/BusquedaGoogleCU.do"><bean:message key="menu.cabecera.buscador.externo"/></a></li>
				<li><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/buscador2/NavegarArbolCurricularCU/NavegarArbolCurricularCU.do"><bean:message key="menu.cabecera.arbolcuricular"/></a></li>
				</ul>
				</div>
				</nav>
				<!-- FIN NAV  -->	
								
				<!-- INICIO PRINCIPAL   -->
				<section id="principal" class="resultados_busqueda">
					<!-- Aqui el body contiene el contenido de mostrar-resultados.jsp -->
					<tiles:insert attribute="body" flush="true"/>
				</section>
				
				<!-- Pie de pagina -->
				<aside id="patros" class="con_borde"></aside>
				<tiles:insert attribute="pie" flush="true"/>
				</div>
				</div>
				
			</logic:equal>
			<logic:equal name="form" property="tipoLayoutBuscador" value="BUSCADOR">
				<div id="cabecera">								
					<tiles:insert attribute="metanavegacion" flush="true"/>
				</div>	
									
				<!-- INICIO CONTENEDOR (antes llamado INICIO CUERPO GENERAL) -->
				<div id="contenedor_ie">
				<div id="contenedor">				
			
				<!-- INICIO Popup de login -->
				<tiles:insert attribute="login" flush="true"/>
				<!-- FIN Popup de login -->
				
				<!-- NAV  -->
				<nav id="menu">
				<h1><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos2/Portada/Portada.do" title="<bean:message key='menu.cabecera.agrega'/>"><strong><bean:message key="menu.cabecera.agrega"/></strong></a></h1>
				<div id="nav_principal">
				<ul class="metanavegacion">
				<li><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos2/Portada/Portada.do" id="activo"><bean:message key="menu.cabecera.buscador.agrega"/></a></li>
				<li><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos2/BusquedaGoogleCU/BusquedaGoogleCU.do"><bean:message key="menu.cabecera.buscador.externo"/></a></li>
				<li><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/buscador2/NavegarArbolCurricularCU/NavegarArbolCurricularCU.do"><bean:message key="menu.cabecera.arbolcuricular"/></a></li>
				</ul>
				</div>
				</nav>
				<!-- FIN NAV  -->	
								
				<!-- INICIO PRINCIPAL   -->
				<section id="principal" class="resultados_busqueda">
					<!-- Aqui el body contiene el contenido de mostrar-resultados.jsp -->
					<tiles:insert attribute="body" flush="true"/>
				</section>
				
				<!-- Pie de pagina -->
				<aside id="patros" class="con_borde"></aside>
				<tiles:insert attribute="pie" flush="true"/>
				</div>
				</div>
								
			</logic:equal>
			<logic:equal name="form" property="tipoLayoutBuscador" value="NEUTRO">
				<div id="cabecera">								
					<tiles:insert attribute="metanavegacion" flush="true"/>
				</div>	
									
				<!-- INICIO CONTENEDOR (antes llamado INICIO CUERPO GENERAL) -->
				<div id="contenedor_ie">
				<div id="contenedor">				
			
				<!-- INICIO Popup de login -->
				<tiles:insert attribute="login" flush="true"/>
				<!-- FIN Popup de login -->
				
				<!-- NAV  -->
				<nav id="menu">
				<h1><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos2/Portada/Portada.do" title="<bean:message key='menu.cabecera.agrega'/>"><strong><bean:message key="menu.cabecera.agrega"/></strong></a></h1>
				<div id="nav_principal">
				<ul class="metanavegacion">
				<li><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos2/Portada/Portada.do" id="activo"><bean:message key="menu.cabecera.buscador.agrega"/></a></li>
				<li><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos2/BusquedaGoogleCU/BusquedaGoogleCU.do"><bean:message key="menu.cabecera.buscador.externo"/></a></li>
				<li><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/buscador2/NavegarArbolCurricularCU/NavegarArbolCurricularCU.do"><bean:message key="menu.cabecera.arbolcuricular"/></a></li>
				</ul>
				</div>
				</nav>
				<!-- FIN NAV  -->	
								
				<!-- INICIO PRINCIPAL   -->
				<section id="principal" class="resultados_busqueda">
					<!-- Aqui el body contiene el contenido de mostrar-resultados.jsp -->
					<tiles:insert attribute="body" flush="true"/>
				</section>
				
				<!-- Pie de pagina -->
				<aside id="patros" class="con_borde"></aside>
				<tiles:insert attribute="pie" flush="true"/>
				</div>
				</div>
								
			</logic:equal>
			<logic:equal name="form" property="tipoLayoutBuscador" value="FEDERADO">
				<div id="cabecera">																
					<!--
					<br class="oculto" />
					<div title="<server:serverProperties property="ccaa"/>" id="logo">
						<span><server:serverProperties property="ccaa"/></span>
					</div>
					<br class="oculto" />
					-->
						
					<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
					<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
					<!-- ESPACIO PARA EL INCLUDE DE LOS LOGOS DE COMUNIDADES, COLABORADORES Y PATROCINADORES  -->
					<logos:renderLogoSet htmlFile="uploads/logos/logos-cabecera.html"/>									
					<!-- FIN DEL ESPACIO PARA EL INCLUDE DE COMUNIDADES. COLABORADORES Y PATROCINADORES  -->
					<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
					<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
								
				</div>	
					
				<!-- INICIO CONTENEDOR (antes llamado INICIO CUERPO GENERAL) -->
				<div id="contenedor_ie">
				<div id="contenedor">
				<div class="contenido_general">	
				
				<!-- Inicio cuerpo central de pagina con Menu Principal, capa Lateral y capa Central  -->
				<div class="tronco_de_contenido">
					<!-- Menu de cabecera -->
					<tiles:insert attribute="menu-principal" flush="true"/>
					<!-- Menu lateral -->
					
					<!-- Inicio Contenido ESPECÍFICO  -->
					<div id="contenido_central_largo">
						<tiles:insert attribute="body" flush="true"/>
					</div>	
				</div>
				<!-- Inicio Pie de pagina  -->
				<div id="pie_pagina">
					<hr />
					<a href="/${initParam.url_informacionLegal}" id="info" title="<bean:message key="pie.informacionLegal"/>"><span><bean:message key="pie.informacionLegal"/></span></a>
					<a href="/${initParam.url_politicaPrivacidad}" id="politica" title="<bean:message key="pie.politicaPrivacidad"/>"><span><bean:message key="pie.politicaPrivacidad"/></span></a>									
				</div>
				<!-- Fin Pie de pagina  -->
				</div>
				</div>
				</div>
										
			</logic:equal>
		</div>
									
					
		<tiles:insert attribute="end-body" flush="true"/>
		
		<!-- Aqui van javascripts propios  -->
		<script type='text/javascript' src="<rewrite:rewrite url="static/Agrega2/js/acceso.js"/>"></script>
		<script type='text/javascript' src="<rewrite:rewrite url="static/Agrega2/js/codigo.js"/>"></script>
		<!-- Aqui van javascripts propios  -->

	</body>
</html>