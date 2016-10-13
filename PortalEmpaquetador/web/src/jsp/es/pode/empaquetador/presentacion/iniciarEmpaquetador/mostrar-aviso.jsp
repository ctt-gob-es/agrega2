<?xml version="1.0" encoding="iso-8859-1" ?> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="es" xml:lang="es">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/tags-empaquetador.tld" prefix="emp" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<head lang="es" dir="ltr">

<title><bean:message key="comunes.titulo"/></title>

<link rel="stylesheet" media="screen" href="<rewrite:rewrite url="static/css/red.css"/>" type="text/css" />
<link rel="stylesheet" media="screen" href="<rewrite:rewrite url="logos/css/logos_patrocinadores.css"/>" type="text/css" />
<script type="text/javascript" src="<rewrite:rewrite url="static/js/plantilla.js"/>"></script>
<script type="text/javascript" src="<rewrite:rewrite url="static/js/curvas.js"/>"></script>
<link rel="shortcut icon" href="<rewrite:rewrite url="static/img/favicon.ico"/>" />
<!--[if lte IE 6]>
<style title="Hoja de estilo oculta para Internet Explorer">
@import url("/static/css/ancho_maximo.css"); 
</style>
<![endif]-->

</head>

<body>

<!--  CAPA QUE CONTIENE TODAS LAS DEMAS  -->
<!--  INICIO CAPA MADRE   -->
<div id="capa_madre">

<!--  INICIO CUERPO GENERAL   -->
<!--  INICIO CUERPO GENERAL   -->
<div id="cuerpo">
<div class="minwidth">
<div class="contenido_general">


<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- INICIO CABECERA  -->
<div id="cabecera">

<h1 class="oculto"><span class="oculto">Comunidad de Madrid</span></h1>

<!-- INICIO METANAVEGACION   -->
<!-- INICIO METANAVEGACION   -->
<div id="metanavegacion">
<ol>
	<li class="oculto"><a href="#contenido_central" title="Contenido Principal"><strong><bean:message key="metanavegacion.contenido" /></strong></a></li>
	<li><a href="<html:rewrite action="/InicioEmpaquetador/MostrarAvisoSubmit"/>?action=<bean:message key="iniciarEmpaquetador.cancelar" />" title="<bean:message key="metanavegacion.cerrar" />" id="li_inicial"><bean:message key="metanavegacion.cerrar" /></a></li>
	<li><emp:link attrProperty="HELP_URL" title="Ayuda" id="ayuda" target="blank" i18nMessage="cabecera.ayuda"/></li>


<!-- 	<li><span id="identificacion">Nombre Apellido Apellido,</span></li>
		<li><a href="#" title="Catalogar">Catalogar</a></li>
		<li><a href="#" title="Previsualizar">Previsualizar</a></li>
		<li><a href="#" title="Validar">Validar</a></li>
		<li><a href="#" title="Guardar" id="sinbarra">Guardar</a></li>-->
</ol>
</div>

<!-- FIN METANAVEGACION   -->
<!-- FIN METANAVEGACION   -->

<!-- 
<h2 class="h2_paq"><bean:message key="iniciarEmpaquetador.aviso"/></h2>
-->
<br class="oculto" />
</div>

<!-- Inicio cuerpo central de pagina con Menu Principal, capa Lateral y capa Central  -->
<!-- Inicio cuerpo central de pagina con Menu Principal, capa Lateral y capa Central  -->
<div class="tronco_de_contenido">
<!-- Inicio MENU PRINCIPAL -->
<!-- Inicio MENU PRINCIPAL -->
<div id="madre_barra">
<div class="fondolat"></div>
</div>
<!-- Fin MENU PRINCIPAL -->

<!-- Fin MENU PRINCIPAL -->

<!-- Inicio Contenido ESPECÍFICO  -->
<!-- Inicio Contenido ESPECÍFICO  -->
<div id="contenido_central_largo">

<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- ATENCION!! AQUÍ ES DONDE VA LA PLANTILLA DE CONTENIDO (REEMPLAZAR LA CAPA: "plantilla_contenido" DE LA PLANTILLA DE CONTENIDO QUE SE DESEE POR LA CAPA QUE VIENE A CONTINUACION  -->
<div class="plantilla_contenido">
<%@ include file="/layout/messages.jsp" %>
<h2><bean:message key="iniciarEmpaquetador.aviso"/></h2>
<form method="post" action="<html:rewrite action="/InicioEmpaquetador/MostrarAvisoSubmit"/>" >
<!-- 
<p class="parrafo_comun" id="separacion2"><bean:message key="iniciarEmpaquetador.aviso"/></p>
-->
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div id="formulario" >

			 	<div class="fila_de_tabla">
  						<div class="text" style="text-align:center">
  						<bean:message key="iniciarEmpaquetador.aviso.ode.en.edicion"/>
  						</div>
					<br class="oculto" />
				</div>
				<br />
				</div>
				<!--  FIN CAJA DE FORMULARIO   -->
			</div>

		</div>
	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->

<!-- Inicio Botones  -->
<!-- Inicio Botones  -->

<fieldset class="tipo">
<input class="boton_125_de_2_izq" name="action" type="submit"  value="<bean:message key="iniciarEmpaquetador.cancelar" />" />
<input class="boton_125_de_2"  name="action" type="submit"  value="<bean:message key="iniciarEmpaquetador.aceptar"/>"  />
<input type="hidden" name="identificador" value="${form.identificador}"/>
</fieldset>
<!-- Fin Botones  -->
<!-- Fin Botones  -->
</form>

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


<!-- INICIO PIE DE PAGINA -->
<!-- INICIO PIE DE PAGINA -->
<%@ include file="/layout/pie.jsp" %>
<!-- FIN PIE DE PAGINA -->
<!-- FIN PIE DE PAGINA -->

</div>
</div>
</div>
<!--  FIN CUERPO GENERAL   -->
<!--  FIN CUERPO GENERAL   -->

</div>
<!--  FIN CAPA MADRE   -->
<!--  FIN CAPA MADRE   -->
</body>
</html>
