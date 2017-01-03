<?xml version="1.0" encoding="iso-8859-1" ?> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="es" xml:lang="es">
<%@ page import="es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tags/serverProperties.tld" prefix="server" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<%@ include file="/taglib-imports.jspf" %>
<%@ taglib uri="/WEB-INF/tags/logos.tld" prefix="logos" %>
<%@ taglib uri="/WEB-INF/tags/idiomasBanderas.tld" prefix="banderas" %>
<html:xhtml/>

	<head lang="es" dir="ltr">
	<meta name="description" content="<bean:message key="meta.descripcion"/>" />
	<meta name="keywords" content="<bean:message key="meta.palabrasClave"/>" />
	<title><tiles:insert attribute="title" flush="true"/></title>
	<link rel="shortcut icon" href="<rewrite:rewrite url="static/img/favicon.ico"/>" />
	<logic:equal name="form" property="tipoLayoutBuscador" value="FEDERADO">
		<link rel="stylesheet" media="screen" href="<rewrite:rewrite url="static/css/red.css"/>" type="text/css" />
		<link rel="stylesheet" media="screen" href="<rewrite:rewrite url="static/css/red_categorias_nuevo.css"/>" type="text/css" />
		<link rel="stylesheet" media="screen" href="<rewrite:rewrite url="logos/css/logos_patrocinadores.css"/>" type="text/css" />
	</logic:equal>
	<logic:notEqual name="form" property="tipoLayoutBuscador" value="FEDERADO">	
		<logic:equal name="form" property="tipoLayoutBuscador" value="NEUTRO">
			<link rel="stylesheet" media="screen" href="<rewrite:rewrite url="static/css/red.css"/>" type="text/css" />
			<link rel="stylesheet" media="screen" href="<rewrite:rewrite url="static/css/red_categorias_nuevo.css"/>" type="text/css" />
			<link rel="stylesheet" media="screen" href="<rewrite:rewrite url="logos/css/logos_patrocinadores.css"/>" type="text/css" />
		</logic:equal>
		<logic:notEqual name="form" property="tipoLayoutBuscador" value="NEUTRO">
			<link rel="stylesheet" media="screen" href="<rewrite:rewrite url="static/css/red.css"/>" type="text/css" />
			<link rel="stylesheet" media="screen" href="<rewrite:rewrite url="static/css/red_categorias_nuevo.css"/>" type="text/css" />
			<link rel="stylesheet" media="screen" href="<rewrite:rewrite url="logos/css/logos_patrocinadores.css"/>" type="text/css" />
		</logic:notEqual>	
	</logic:notEqual>
		
	<script type="text/javascript" src="<rewrite:rewrite url="static/js/curvas.js"/>"></script>
	<script type="text/javascript" src="<rewrite:rewrite url="static/js/jquery-1.4.2.min.js"/>"></script>
	<script type="text/javascript" src="<rewrite:rewrite url="static/js/jquery.tipsy.js"/>"></script>
	<script type='text/javascript' src="<rewrite:rewrite url="static/js/jquery.autocomplete.js"/>"></script>
	<script type="text/javascript" src="<rewrite:rewrite url="static/js/plantilla.js"/>"></script>
<tiles:insert attribute="codigo-head" flush="true"/>

	
	<link rel="search" type="application/opensearchdescription+xml" title="Agrega" href="<rewrite:rewrite url="searchPlugin/searchPlugin.xml"/>"/>
	</head>
		<body>
		<div id="capa_madre">
			<div id="cuerpo">
				<div class="minwidth">
					<div class="contenido_general">					
						
						<!-- Aqui va la cabecera -->
							<logic:equal name="form" property="tipoLayoutBuscador" value="EMPAQUETADOR">
								<div id="cabecera">
									<h1 class="oculto"><span class="oculto"><bean:message key="cabecera.buscador.empaquetador"/></span></h1>
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
										<!-- Pie de pagina -->
										<tiles:insert attribute="pie" flush="true"/>
								
							</logic:equal>
							<logic:equal name="form" property="tipoLayoutBuscador" value="BUSCADOR">
								<div id="cabecera">								
									<tiles:insert attribute="metanavegacion" flush="true"/>
									<tiles:insert attribute="buscador" flush="true"/>
								</div>	
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
								<!-- Pie de pagina -->
								<tiles:insert attribute="pie" flush="true"/>
											
							</logic:equal>
							<logic:equal name="form" property="tipoLayoutBuscador" value="NEUTRO">
								<div id="metanavegacion" style="height:28px" >
									<ol>
										<banderas:idiomasBanderasTag urlIdiomasBanderas="${initParam.url_idiomas_banderas}"/>
									</ol>
								</div>
								
								<div id="cabecera02">
								
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- ESPACIO PARA EL INCLUDE DE LOS LOGOS DE COMUNIDADES, COLABORADORES Y PATROCINADORES  -->
<logos:renderLogoSet htmlFile="uploads/logos/logos-pie_actualizado.html"/>	
<!-- FIN DEL ESPACIO PARA EL INCLUDE DE COMUNIDADES. COLABORADORES Y PATROCINADORES  -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
									
								<h1><span>Agrega</span></h1>
									<br class="oculto"/>
									<a href="http://www.proyectoagrega.es" id="logo" title="<bean:message key="layout.cabecera.volver"/>">
										<span><bean:message key="layout.cabecera.volver"/></span>
									</a>
									<br class="oculto"/>
								</div>								
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
								
							</logic:equal>
							<logic:equal name="form" property="tipoLayoutBuscador" value="FEDERADO">
								<div id="cabecera">																
									<br class="oculto" />
									<div title="<server:serverProperties property="ccaa"/>" id="logo">
										<span><server:serverProperties property="ccaa"/></span>
									</div>
									<br class="oculto" />
									
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- ESPACIO PARA EL INCLUDE DE LOS LOGOS DE COMUNIDADES, COLABORADORES Y PATROCINADORES  -->
<logos:renderLogoSet htmlFile="uploads/logos/logos-pie_actualizado.html"/>									
<!-- FIN DEL ESPACIO PARA EL INCLUDE DE COMUNIDADES. COLABORADORES Y PATROCINADORES  -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
								
								</div>								
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
										
							</logic:equal>
						</div>
									
					
					
					
					</div>
				</div>
			</div>
			<tiles:insert attribute="end-body" flush="true"/>
		</body>
	</html>