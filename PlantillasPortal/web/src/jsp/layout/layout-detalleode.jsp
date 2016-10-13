<!DOCTYPE html>
<html lang="es" class="no-js" >
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
		<link rel="stylesheet" href="<rewrite:rewrite url="static/css/red_v2.css"/>" />
		<link rel="stylesheet" media="handheld" href="<rewrite:rewrite url="static/css/handheld_v1.css"/>" />
		<script type="text/javascript" src="<rewrite:rewrite url="static/js/jquery-1.4.2.min.js"/>"></script> 
		-->
		
		<link rel="stylesheet" href="<rewrite:rewrite url="static/Agrega2/css/red.css"/>" type="text/css" />
		<link rel="stylesheet" href="<rewrite:rewrite url="static/Agrega2/css/red_categorias_nuevo.css"/>" type="text/css" />
		<link rel="stylesheet" href="<rewrite:rewrite url="static/Agrega2/css/handheld.css"/>" type="text/css" />
		<script type="text/javascript" src="<rewrite:rewrite url="static/Agrega2/js/jquery-1.4.2.min.js"/>"></script>
		<script type="text/javascript" src="<rewrite:rewrite url="static/Agrega2/js/jquery.tipsy.js"/>"></script>
		<script type="text/javascript" src="<rewrite:rewrite url="static/Agrega2/js/codigo_nuevo.js"/>"></script>
				
		<!--
		<script type="text/javascript" src="<rewrite:rewrite url="static/js/plantilla.js"/>"></script>
		<script type="text/javascript" src="<rewrite:rewrite url="static/js/curvas.js"/>"></script>
		<script type='text/javascript' src="<rewrite:rewrite url="static/js/jquery.autocomplete.js"/>"></script>
		<script type="text/javascript" src="<rewrite:rewrite url="static/js/swfobject.js"/>"></script>
		
		<tiles:insert attribute="codigo-head" flush="true"/>
		<style type="text/css" media="screen">
			el_flash {visibility:hidden}
		</style>
		-->
	</head>

	<!-- Para controlar las diferentes versiones de IExplorer -->
	<!--[if lt IE 7 ]> <body class="ie6" onload="javascript:$('.showhide').hide();"> <![endif]-->
	<!--[if IE 7 ]> <body class="ie7" onload="javascript:$('.showhide').hide();"> <![endif]-->
	<!--[if IE 8 ]> <body class="ie8" onload="javascript:$('.showhide').hide();"> <![endif]-->
	<!--[if IE 9 ]> <body class="ie9" onload="javascript:$('.showhide').hide();"> <![endif]-->
	<!--[if (gt IE 9)|!(IE)]><!-->
	<body  onload="javascript:$('.showhide').hide();" > <!--<![endif]-->
	
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
					<section id="principal" class="ficha">

						<article id="main" class="clearfix">
							<tiles:insert attribute="body-principal" flush="true"/>
						</article>
									
						<!-- Pie de pagina -->						
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


	</body>
</html>
				