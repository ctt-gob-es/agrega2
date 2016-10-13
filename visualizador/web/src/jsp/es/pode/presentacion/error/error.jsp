<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ page session="false" isErrorPage="true" %>
<%@ taglib uri="/WEB-INF/tlds/rewriteTag.tld" prefix="rewrite" %>

<link rel="shortcut icon" href="<rewrite:rewrite url='static/img/favicon.ico'/>" />

<link rel="stylesheet" media="screen" href="<rewrite:rewrite url="static/css/red.css"/>" type="text/css" />
<script type="text/javascript" src="<rewrite:rewrite url="static/js/plantilla.js"/>"></script>
<bean:define id="codigo" ><%= request.getAttribute("codigo_error") %></bean:define>

<html>
<head>
	<title>
	      <bean:message key="title.Comun"/>
	</title>
	<meta name="robot" content="noindex,follow">
</head>
<!-- 		INICIO DEL BODY		  -->
<body>


<!--  INICIO CAPA MADRE   -->
<div id="capa_madre">
			<div id="cuerpo">
				<div class="minwidth">
					<div class="contenido_general">

<div class="tronco_de_contenido">
<div id="madre_barra"><div class="fondolat"></div></div>
<div id="contenido_central_largo">

<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- Inicio plantilla contenido  -->
<!-- Inicio plantilla contenido  -->
<div class="plantilla_contenido">




<h2><bean:message key="error.${codigo}.titulo"/></h2>


<!--		Inicio del formulario principal		-->

<form id="errorAplicacion" method="post" >

	<!--		INICIO GLOBO GRIS		-->
	
	<div class="globo_gris" >
		<div class="globo_gris_01">
			<div class="globo_gris_02">
				<div class="globo_gris_03">
				
				
					<!--		INICIO CAJA DE FORMULARIO		-->
					
					<div id="formulario" class="ali_c">
						<br />
						<p><bean:message key="error.${codigo}.mensaje"/></p>
						<br />
						
					</div>
					
					<!--		FIN CAJA DE FORMULARIO		-->
						
						
				</div>
			</div>
		</div>
	</div>
	
	<!--		FIN GLOBO GRIS		-->


	<!--		Boton Aceptar		-->
<fieldset class="tipo">
	<input class="boton_125 tipo ft_centrada" onclick="window.parent.close()" type="button"  value="Cerrar" />
</fieldset>	
	
</form>

<!--		Fin del Formulario principal		-->
	

</div><!--		Fin de la capa plantilla_contenido		--> 
</div><!--		Fin de la capa contenido_central_largo	-->
<div id="pie_pagina"></div>

</div>
</div><!--		Fin de la capa contenido_general	-->
</div><!--		Fin de la capa minwidth	-->
</div><!--		Fin de la capa cuerpo	-->
</div><!--		Fin de la capa capa_madre	-->



</body>

<!--		FIN DEL BODY		-->


</html>