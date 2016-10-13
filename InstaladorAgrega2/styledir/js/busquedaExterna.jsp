<html>
	<head>
	<meta charset="utf-8">
	<!-- Obligatorio para trabajar con IExplorer y HTML5 -->
	<!--[if IE]>
	<script src="http://www.therightdirection.biz/clientes/indra/red_2011/js/html5.js"></script>
	<![endif]-->
	
	<title>Agrega</title>
	<meta name="description" content="">
	<meta name="author" content="">
		<link rel="stylesheet" href="http://www.therightdirection.biz/clientes/indra/red_2011/css/red.css?v=3">
		<link rel="stylesheet" href="http://www.therightdirection.biz/clientes/indra/red_2011/css/handheld_v1.css">
		<script type="text/javascript" src="http://code.jquery.com/jquery-1.5.2.js"></script>
	
		<script language="javascript">
		
			// Read a page's GET URL variables and return them as an associative array.
			function getUrlVars() {
				return window.location.href.slice(window.location.href.indexOf('=') + 1);
			}
			
			function getIframeUrl() {
				return getUrlVars();
			}	
			
			function crearFrame(src) {
				var frame = document.createElement("IFRAME");
				frame.id = "frame";
				frame.src = src;
				frame.height = "75%";
				var control = document.getElementById("frame")
				if (control==null) {
					document.body.appendChild(frame);
				}
			}
			
			$(document).ready( function () {
				crearFrame(getIframeUrl());
			});
		
		</script>
		
		<style type="text/css">
			#frame {width:960px;padding: 0 !important;min-height: 600px}
		</style>
	</head>
	<!-- Para controlar las diferentes versiones de IExplorer -->
	<!--[if lt IE 7 ]> <body class="ie6"> <![endif]-->
	<!--[if IE 7 ]> <body class="ie7"> <![endif]-->
	<!--[if IE 8 ]> <body class="ie8"> <![endif]-->
	<!--[if IE 9 ]> <body class="ie9"> <![endif]-->
	<!--[if (gt IE 9)|!(IE)]><!-->
	<body > <!--<![endif]-->
		<div id="madre">
			<!-- CONTENEDOR  -->
			<div id="contenedor_ie">
			<div id="contenedor">
				<!-- NAV  -->		
				<nav id="menu">
					<h1><a href="/visualizadorcontenidos/Portada/Portada.do" title="Agrega"><strong>Agrega</strong></a></h1>
					<div id="nav_principal">
						<ul class="metanavegacion">
						<li><a href="/visualizadorcontenidos/Portada/Portada.do">Buscador Agrega</a></li>
						<li><a href="/visualizadorcontenidos/BusquedaGoogleCU/BusquedaGoogleCU.do"  id="activo">Buscador Externo</a></li>
						<li><a href="/buscador/NavegarArbolCurricularCU/NavegarArbolCurricularCU.do">&Aacute;rbol Curricular</a></li>
						</ul>
					</div>
				</nav>
			</div>
			</div>		
		</div>
	</body>
</html>
