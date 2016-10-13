<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>


<link rel="stylesheet" media="screen" href="<rewrite:rewrite url="static/css/red.css"/>" type="text/css" />
<script type="text/javascript" src="<rewrite:rewrite url="static/js/plantilla.js"/>"></script>

<html>

<title>
      <bean:message key="title.Comun"/>
</title>


<!-- 		INICIO DEL BODY		  -->
<body>
 
 <!--  INICIO CAPA MADRE   -->
<div id="capa_madre">
			<div id="cuerpo">
				<div class="minwidth">
					<div class="contenido_general">
					
<div id="contenido_central_largo">

<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- Inicio plantilla contenido  -->
<!-- Inicio plantilla contenido  -->
<div class="plantilla_contenido" id="ventana_flotante">




<!--		Inicio del formulario principal		-->

<form id="errorAplicacion" action='<html:rewrite action="/Portada/Portada.do"/>' method="post" >

	<!--		INICIO GLOBO GRIS		-->
	
	<div class="globo_gris" >
		<div class="globo_gris_01">
			<div class="globo_gris_02">
				<div class="globo_gris_03">
				
				
					<!--		INICIO CAJA DE FORMULARIO		-->
				
					<div id="formulario" class="ali_c">
						
						<br />
						<p><bean:message key="gestionGrupo.grupo.desactivado" /></p>
                        		<p>${form.nombre}</p>
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
	<input class="boton_125 tipo ft_centrada"  type="submit"  value="<bean:message key='usuarios.aceptar'/>" />
</fieldset>	
		
</form>

<!--		Fin del Formulario principal		-->
	
</div><!--		Fin de la capa plantilla_contenido		--> 
</div><!--		Fin de la capa contenido_central_largo	-->
</div><!--		Fin de la capa contenido_general	-->
</div><!--		Fin de la capa minwidth	-->
</div><!--		Fin de la capa cuerpo	-->
</div><!--		Fin de la capa capa_madre	-->

</body>
<!--		FIN DEL BODY		-->

</html>