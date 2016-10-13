<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ page session="false" isErrorPage="true" %>
<%@ taglib uri="/WEB-INF/tlds/rewriteTag.tld" prefix="rewrite" %>

<link rel="shortcut icon" href="<rewrite:rewrite url='static/img/favicon.ico'/>" />

<link rel="stylesheet" media="screen" href="<rewrite:rewrite url="static/css/red.css"/>" type="text/css" />
<script type="text/javascript" src="<rewrite:rewrite url="static/js/plantilla.js"/>"></script>


<html>
<head>
	<title>
	      <bean:message key="title.Comun"/>
	</title>
	<meta name="robot" content="noindex,follow">
</head>
<!-- 		INICIO DEL BODY		  -->
<body id="pseudo_iframe">


<!--  INICIO CAPA MADRE   -->
<div id="capa_madre">

<div id="contenido_central_largo">


<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- Inicio plantilla contenido  -->
<div class="plantilla_contenido" id="contenido_it" style="position:relative;">
<!-- Inicio plantilla contenido  -->



<!--		Inicio del formulario principal		-->

<form id="errorAplicacion" method="post" >

	<!--		INICIO GLOBO GRIS		-->
	
										
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_blanco" >
	<div class="globo_blanco_01">
		<div class="globo_blanco_02">
			<div class="globo_blanco_03" >
			
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div id="formulario" style="padding-bottom:5px;"   >
			 <!--  INICIO GLOBO Blanco   -->
			<!--  INICIO GLOBO Blanco   -->
			
			
			
			
				<!--  INICIO GLOBO GRIS   -->
				<!--  INICIO GLOBO GRIS   -->
				<div class="globo_gris_bis" >
					<div class="globo_gris_01_bis">
						<div class="globo_gris_02_bis">
							<div class="globo_gris_03_bis">
								<div class="ali_c">
									<br />
									<p><bean:message key="iframe.contenidos.error"/></p>
									<br />
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--		FIN CAJA DE FORMULARIO		-->
					
					
			</div>
		</div>
	</div>
</div>
				
				<!--		FIN GLOBO GRIS		-->


	<!--		Boton Aceptar		-->
	
</form>

<!--		Fin del Formulario principal		-->
	

</div><!--		Fin de la capa plantilla_contenido		--> 
</div><!--		Fin de la capa contenido_central_largo	-->


</div>



</body>

<!--		FIN DEL BODY		-->


</html>