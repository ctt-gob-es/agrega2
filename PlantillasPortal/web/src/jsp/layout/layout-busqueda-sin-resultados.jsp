<!doctype html>
<html lang="en" class="no-js">
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
		
		<!--
		<link rel="stylesheet" href="<rewrite:rewrite url="static/css/red_v3.css"/>" />
		<link rel="stylesheet" media="handheld" href="<rewrite:rewrite url="static/css/handheld_v1.css"/>" />
		<script type="text/javascript" src="<rewrite:rewrite url="static/js/jquery-1.4.2.min.js"/>"></script> 
		-->
		
		<link rel="stylesheet" href="<rewrite:rewrite url="static/Agrega2/css/red.css"/>" type="text/css" />
		<link rel="stylesheet" href="<rewrite:rewrite url="static/Agrega2/css/handheld.css"/>" type="text/css" />
		<script type="text/javascript" src="<rewrite:rewrite url="static/Agrega2/js/jquery-1.4.2.min.js"/>"></script>
		
		<script type="text/javascript" src="<rewrite:rewrite url="static/js/jquery.tipsy.js"/>"></script>
		<script type="text/javascript" src="<rewrite:rewrite url="static/js/plantilla.js"/>"></script>
		<script type="text/javascript" src="<rewrite:rewrite url="static/js/curvas.js"/>"></script>
		<script type='text/javascript' src="<rewrite:rewrite url="static/js/jquery.autocomplete.js"/>"></script>
		<script type="text/javascript" src="<rewrite:rewrite url="static/js/swfobject.js"/>"></script>
		
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
		
			<!-- INICIO CONTENEDOR -->
			<div id="contenedor_ie">
				<div id="contenedor">
				
					<!-- NAV  -->
					<tiles:insert attribute="menu-principal" flush="true"/>
					<!-- FIN NAV  -->
								
					<!-- INICIO PRINCIPAL   -->
					<section id="principal" class="resultados_busqueda">
					
						<article id="buscador">
							<header>
								<h2 class="oculto"><bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.resultadoBusqueda"/></h2>
							</header>
							<tiles:insert definition="buscador-sin-resultados" flush="true"/>
						</article>

						<article id="buscador_tipo">
						<section id="buscador_avanzado">
							<!-- Aqui el body contiene el contenido de mostrar-quiso-decir-avanzado.jsp o el de mostrar-resultados.jsp -->
							<tiles:insert attribute="body-principal" flush="true"/>
						</section>
						</article>
									
						<!-- Pie de pagina -->						
						<aside id="patros" class="con_borde"></aside>
						<tiles:insert attribute="pie" flush="true"/>

					</section>
					
					<!-- FIN PRINCIPAL -->

				</div>
			</div>
			<!--  FIN CONTENEDOR (antes llamado CUERPO GENERAL) -->

		</div>
		<!--  FIN CAPA MADRE   -->
		<tiles:insert attribute="end-body" flush="true"/>
			
		</div>
		<!--  FIN CAPA MADRE   -->

		<!-- Aqui van javascripts propios  -->
		<script type='text/javascript' src="<rewrite:rewrite url="static/Agrega2/js/acceso.js"/>"></script>
		<script type='text/javascript' src="<rewrite:rewrite url="static/Agrega2/js/codigo.js"/>"></script>
		<!-- Aqui van javascripts propios  -->

	</body>
</html>
				