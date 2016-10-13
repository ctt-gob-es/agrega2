<?xml version="1.0" encoding="iso-8859-1" ?> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<html xmlns="http://www.w3.org/1999/xhtml" lang="es" xml:lang="es">
<head lang="es" dir="ltr">
<html:xhtml/>	
<title>
	<bean:message key="title.Comun"/>
</title>

<link rel="shortcut icon" href="<rewrite:rewrite url="static/img/favicon.ico"/>" />
<link rel="stylesheet" media="screen" href="<rewrite:rewrite url="static/css/red.css"/>" type="text/css" />
<script type="text/javascript" src="<rewrite:rewrite url="static/js/plantilla.js"/>"></script>

</head>


<body>

<!--  CAPA QUE CONTIENE TODAS LAS DEMAS  -->
<!--  INICIO CAPA MADRE   -->
<div id="capa_madre_popup">

<!--  INICIO CUERPO GENERAL   -->
<!--  INICIO CUERPO GENERAL   -->
<div id="cuerpo"> 

<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- INICIO CABECERA  -->
<div id="cabecera">

<!-- INICIO METANAVEGACION   -->
<!-- INICIO METANAVEGACION   -->
<div id="metanavegacion">

<ol>
	<li class="oculto"><a href="#contenido_central" title="Contenido Principal"><strong><bean:message key="previsualizar.conSinSecuencia.contenido"/></strong></a></li>
	<li><a href="javascript:window.close();" title="<bean:message key="previsualizar.conSinSecuencia.CerrarVentana"/>" id="li_inicial"><bean:message key="previsualizar.conSinSecuencia.cerrarVentana"/></a></li>
		
</ol>
</div>
<!-- FIN METANAVEGACION   -->
<!-- FIN METANAVEGACION   -->



<br class="oculto" />

</div>

<!-- Inicio cuerpo central de pagina con Menu Principal, capa Lateral y capa Central  -->
<!-- Inicio cuerpo central de pagina con Menu Principal, capa Lateral y capa Central  -->
<div class="tronco_de_contenido">
<div id="madre_barra"><div class="fondolat"></div></div>


<!-- Inicio Contenido ESPECÍFICO  -->
<!-- Inicio Contenido ESPECÍFICO  -->
<div id="contenido_central_largo">

<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- ATENCION!! AQUÍ ES DONDE VA LA PLANTILLA DE CONTENIDO (REEMPLAZAR LA CAPA: "plantilla_contenido" DE LA PLANTILLA DE CONTENIDO QUE SE DESEE POR LA CAPA QUE VIENE A CONTINUACION  -->


<div class="plantilla_contenido">
<jsp:include page="/layout/messages.jsp" flush="true" />
<h2><bean:message key="previsualizar.conSinSecuencia.titulo"/> ${form.titulo}</h2>
<form method="get" action="<html:rewrite action="/PrevisualizarCU/SeleccionaCSSecuenciaMostrarODECSSecuencia"/>">

<p class="parrafo_comun" id="separacion2"><bean:message key="previsualizar.conSinSecuencia.titulo.explicacion"/></p>
<p class="parrafo_comun" id="separacion2"><bean:message key="previsualizar.conSinSecuencia.titulo.seleccioneVisualizacion"/>:</p>
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
				<div id="formulario" >
					<input type="hidden" name="identificadorODE" value="${form.identificadorODE}">
					<input type="hidden" name="idioma" value="${form.idioma}">
					<input type="hidden" name="tipoVisualizador" value="agrega">
					<div class="fila_de_tabla">
						<div class="text ft_lateral" ><input type="radio" class="boton_radio" id="Previsual01" checked="checked" name="secuencia" value="ConSecuencia"/><label for="Previsual01" class="alineada"><bean:message key="previsualizar.conSinSecuencia.titulo.conSecuencia"/> </label></div>
						<div class="linea_separadora"></div>
						<br class="oculto" />
					</div>
					<div class="fila_de_tabla">
						<div class="text ft_lateral"><input type="radio" class="boton_radio" id="Previsual02"  name="secuencia" value="SinSecuencia"/><label for="Previsual02"  class="alineada2"><bean:message key="previsualizar.conSinSecuencia.titulo.sinSecuencia"/>  </label></div>
						<div class="linea_separadora"></div>
						<br class="oculto" />
					</div>
					
				</div>
				<!--  FIN CAJA DE FORMULARIO   -->
			</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->
<fieldset class="tipo">
<input class="boton_125_de_2" target="_blank" type="submit"  value="<bean:message key="previsualizar.conSinSecuencia.botonAceptar"/>"/>
</form>

<input class="boton_125_de_2_izq" onclick="javascript:window.close();" type="button" name="accion" value="<bean:message key="previsualizar.conSinSecuencia.botonCancelar"/>" />
</fieldset>
</div>


<!-- Fin plantilla contenido  -->
<!-- Fin plantilla contenido  -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
</div>
<!-- Fin Contenido ESPECÍFICO  -->
<!-- Inicio Contenido ESPECÍFICO  -->
</div>
<!-- Fin cuerpo central de pagina con Menu Principal, capa Lateral y capa Central  -->
<!-- Fin cuerpo central de pagina con Menu Principal, capa Lateral y capa Central  -->



</div>
<!--  FIN CUERPO GENERAL   -->

<!--  FIN CUERPO GENERAL   -->

</div>
<!--  FIN CAPA MADRE   -->
<!--  FIN CAPA MADRE   -->
</body>


</html>

