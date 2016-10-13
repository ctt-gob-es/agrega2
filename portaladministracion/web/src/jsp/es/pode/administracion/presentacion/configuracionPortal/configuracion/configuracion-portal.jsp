<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<tiles:insert definition="layout-administrador">
<tiles:put name="title" type="string">
	<bean:message key="title.Comun"/>
</tiles:put>
<tiles:put name="body" type="string">

<link rel="stylesheet" media="screen" href="<rewrite:rewrite url="static/css/hoja_de_estilo.css"/>" type="text/css" />

<script type="text/javascript" src="<rewrite:rewrite url="static/js/plantilla.js"/>"></script>



<%@ include file="/taglib-imports.jspf" %>

<%@ include file="/es/pode/administracion/presentacion/configuracionPortal/configuracion/configuracion-portal-vars.jspf" %>



<!--  INICIO CAPA MADRE   -->
<div id="capa_madre">


<!-- Inicio plantilla contenido  -->
<div class="plantilla_contenido">
<!-- ## Para pintar los mensajes de error ## -->
	<jsp:include page="/layout/messages.jsp" flush="true" />
<h2><bean:message key="modificar.config.formulario.cabecera"/></h2>

<form method="post" action="<html:rewrite action="/Configuracion/ConfiguracionPortalValidarFormulario"/>?progressMonitor=myProgressMonitor" enctype="multipart/form-data" onsubmit="progressBar()">	


<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
<div class="formu">
				<h3><bean:message key="modificar.config.formulario.titulo1"/></h3>



<!--  INICIO GLOBO BLANCO   -->
<!--  INICIO GLOBO BLANCO   -->
<div class="globo_blanco" >
	<div class="globo_blanco_01">
		<div class="globo_blanco_02">
			<div class="globo_blanco_03 tab_min">
<div class="formu" >
<!-- -->
<table border="0" class="tab_miniaturas" cellpadding="0" cellspacing="0" summary="Tabla de Activación:Noticias">
<tr >
	<td valign="top" class="tede_01"><a href="#"><img src="<rewrite:rewrite url="/static/img/miniatura_01.gif"/>" alt="Imagen 01" width="100" height="60" /></a></td>

	<td valign="top" class="opcion_tab_01">
	<h3><bean:message key="modificar.config.formulario.noticias"/></h3>
	<html:radio  property="noticias" styleId="noticias1"   value="1"  name="form" /><label for="noticias1"><bean:message key="modificar.config.formulario.EstadoActiv"/></label><html:radio  property="noticias" styleId="noticias0" value="0"  style="margin-left:15px;" name="form" /><label for="noticias0"><bean:message key="modificar.config.formulario.EstadoDesact"/></label>
	</td>
</tr>
</table>
<!-- -->
</div>
		</div>

		</div>
	</div>
</div>
<!--  FIN GLOBO BLANCO   -->
<!--  FIN GLOBO BLANCO   -->



<!--  INICIO GLOBO BLANCO   -->
<!--  INICIO GLOBO BLANCO   -->
<div class="globo_blanco" >
	<div class="globo_blanco_01">
		<div class="globo_blanco_02">

			<div class="globo_blanco_03 tab_min">
<div class="formu" >
<!-- -->
<table border="0" class="tab_miniaturas" cellpadding="0" cellspacing="0" summary="Tabla de Activación:Informes">
<tr >
	<td valign="top" class="tede_01"><a href="#"><img src="<rewrite:rewrite url="/static/img/miniatura_02.gif"/>" alt="Imagen 02" width="100" height="60" /></a></td>
	<td valign="top" class="opcion_tab_01">
	<h3><bean:message key="modificar.config.formulario.informes"/></h3>
	<html:radio  property="informes" styleId="informes1"  value="1"  name="form" /><label for="informes1"><bean:message key="modificar.config.formulario.EstadoActiv"/></label><html:radio  property="informes" styleId="informes0" value="0"  style="margin-left:15px;" name="form" /><label for="informes0"><bean:message key="modificar.config.formulario.EstadoDesact"/></label>

	</td>
</tr>
</table>
<!-- -->
</div>
		</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO BLANCO   -->
<!--  FIN GLOBO BLANCO   -->


<!--  INICIO GLOBO BLANCO   -->
<!--  INICIO GLOBO BLANCO   -->
<div class="globo_blanco" >
	<div class="globo_blanco_01">
		<div class="globo_blanco_02">
			<div class="globo_blanco_03 tab_min">
<div class="formu" >
<!-- -->
<table border="0" class="tab_miniaturas" cellpadding="0" cellspacing="0" summary="Tabla de Activación:Descargas">
<tr >
	<td valign="top" class="tede_01"><a href="#"><img src="<rewrite:rewrite url="/static/img/miniatura_03.gif"/>" alt="Imagen 03" width="100" height="60" /></a></td>
	<td valign="top" class="opcion_tab_01">

	<h3><bean:message key="modificar.config.formulario.descargas"/></h3>
	<html:radio  property="descargas" styleId="descargas1"  value="1"  name="form" /><label for="descargas1"><bean:message key="modificar.config.formulario.EstadoActiv"/></label><html:radio  property="descargas" styleId="descargas0" value="0"  style="margin-left:15px;" name="form" /><label for="descargas0"><bean:message key="modificar.config.formulario.EstadoDesact"/></label>
	</td>
</tr>
</table>
<!-- -->
</div>
		</div>
		</div>

	</div>
</div>
<!--  FIN GLOBO BLANCO   -->
<!--  FIN GLOBO BLANCO   -->


<!--  INICIO GLOBO BLANCO   -->
<!--  INICIO GLOBO BLANCO   -->
<div class="globo_blanco" >
	<div class="globo_blanco_01">
		<div class="globo_blanco_02">
			<div class="globo_blanco_03 tab_min">
<div class="formu" >

<!-- -->
<table border="0" class="tab_miniaturas" cellpadding="0" cellspacing="0" summary="Tabla de Activación:RSS Feeds">
<tr >
	<td valign="top" class="tede_01"><a href="#"><img src="<rewrite:rewrite url="/static/img/miniatura_04.gif"/>" alt="Imagen 04" width="100" height="60" /></a></td>
	<td valign="top" class="opcion_tab_01">
	<h3><bean:message key="modificar.config.formulario.rss"/></h3>
	<html:radio  property="rss" styleId="rss1"  value="1"  name="form" /><label for="rss1"><bean:message key="modificar.config.formulario.EstadoActiv"/></label><html:radio  property="rss" styleId="rss0" value="0"  style="margin-left:15px;" name="form" /><label for="rss0"><bean:message key="modificar.config.formulario.EstadoDesact"/></label>
	</td>
</tr>

</table>
<!-- -->
</div>
		</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO BLANCO   -->
<!--  FIN GLOBO BLANCO   -->


<!--  INICIO GLOBO BLANCO   -->
<!--  INICIO GLOBO BLANCO   -->
<div class="globo_blanco" >

	<div class="globo_blanco_01">
		<div class="globo_blanco_02">
			<div class="globo_blanco_03 tab_min">
<div class="formu" >
<!-- -->
<table border="0" class="tab_miniaturas" cellpadding="0" cellspacing="0" summary="Tabla de Activación:Agrega en tu web">
<tr >
	<td valign="top" class="tede_01"><a href="#"><img src="<rewrite:rewrite url="/static/img/miniatura_05.gif"/>" alt="Imagen 05" width="100" height="60" /></a></td>
	<td valign="top" class="opcion_tab_01">
	<h3><bean:message key="modificar.config.formulario.agregaweb"/></h3>

	<html:radio  property="agregaWeb" styleId="agregaWeb1" value="1"  name="form" /><label for="agregaWeb1"><bean:message key="modificar.config.formulario.EstadoActiv"/></label><html:radio  property="agregaWeb" styleId="agregaWeb0" value="0"  style="margin-left:15px;" name="form" /><label for="agregaWeb0"><bean:message key="modificar.config.formulario.EstadoDesact"/></label>
	</td>
</tr>
</table>
<!-- -->
</div>
		</div>
		</div>
	</div>
</div>

<!--  FIN GLOBO BLANCO   -->
<!--  FIN GLOBO BLANCO   -->


<!--  INICIO GLOBO BLANCO   -->
<!--  INICIO GLOBO BLANCO   -->
<div class="globo_blanco" >
	<div class="globo_blanco_01">
		<div class="globo_blanco_02">
			<div class="globo_blanco_03 tab_min">
<div class="formu" >
<!-- -->
<table border="0" class="tab_miniaturas" cellpadding="0" cellspacing="0" summary="Tabla de Activación:Etiquetado social">
<tr >

	<td valign="top" class="tede_01"><a href="#"><img src="<rewrite:rewrite url="/static/img/miniatura_06.gif"/>" alt="Imagen 06" width="100" height="60" /></a></td>
	<td valign="top" class="opcion_tab_01">
	<h3><bean:message key="modificar.config.formulario.tagging"/></h3>
	<html:radio  property="tagging" styleId="tagging1"  value="1"  name="form" /><label for="tagging1"><bean:message key="modificar.config.formulario.EstadoActiv"/></label><html:radio  property="tagging" styleId="tagging0" value="0"  style="margin-left:15px;" name="form" /><label for="tagging0"><bean:message key="modificar.config.formulario.EstadoDesact"/></label>
	</td>
</tr>
</table>
<!-- -->
</div>

		</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO BLANCO   -->
<!--  FIN GLOBO BLANCO   -->



<!--  INICIO GLOBO BLANCO   -->
<!--  INICIO GLOBO BLANCO   -->
<div class="globo_blanco" >
	<div class="globo_blanco_01">

		<div class="globo_blanco_02">
			<div class="globo_blanco_03 tab_min">
<div class="formu" >
<!-- -->
<table border="0" class="tab_miniaturas" cellpadding="0" cellspacing="0" summary="Tabla de Activación:Tags">
<tr >
	<td valign="top" class="tede_01"><a href="#"><img src="<rewrite:rewrite url="/static/img/miniatura_07.gif"/>" alt="Imagen 07" width="100" height="60" /></a></td>
	<td valign="top" class="opcion_tab_01">
	<h3><bean:message key="modificar.config.formulario.etiquetas"/></h3>
	<html:radio  property="etiquetas" styleId="etiquetas1" value="1"  name="form" /><label for="etiquetas1"><bean:message key="modificar.config.formulario.EstadoActiv"/></label><html:radio  property="etiquetas" styleId="etiquetas0" value="0"  style="margin-left:15px;" name="form" /><label for="etiquetas0"><bean:message key="modificar.config.formulario.EstadoDesact"/></label>
	</td>
</tr>
</table>
<!-- -->
</div>
		</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO BLANCO   -->
<!--  FIN GLOBO BLANCO   -->


<!--  INICIO GLOBO BLANCO   -->
<!--  INICIO GLOBO BLANCO   -->
<div class="globo_blanco" >
	<div class="globo_blanco_01">
		<div class="globo_blanco_02">
			<div class="globo_blanco_03 tab_min">
<div class="formu" >
<table border="0" class="tab_miniaturas" cellpadding="0" cellspacing="0" summary="Tabla de Activación:Imagen Principal">
<tr >
	<td valign="top" class="tede_01"><a href="#"><img src="<rewrite:rewrite url="/static/img/miniatura_08.gif"/>" alt="Imagen 08" width="100" height="60" /></a></td>
	<td valign="top" class="opcion_tab_01">

	<h3><bean:message key="modificar.config.formulario.imagenportada"/></h3>
	<label for="importarA01" style="padding-left:0;" ><span><bean:message key="modificar.config.formulario.seleccioneRSS"/>: &nbsp;</span></label>
	<html:select name="form" property="idRssGaleria" styleId="idRssGaleria" onchange="desactivar();">
		<html:optionsCollection name="form"  property="idRssGaleriaBackingList" label="label" value="value"/>
	</html:select>
	<label for="impor" style="padding-left:0;" ><span><bean:message key="modificar.config.formulario.seleccioneNumOdesGaleriaPortada"/>: &nbsp;</span></label>
	<html:select name="form" property="numOdesGaleria" styleId="numOdesGaleria">
		<html:optionsCollection name="form"  property="numOdesGaleriaBackingList" label="label" value="value"/>
	</html:select>
	
	</td>
</tr>
</table>

</div>
		</div>

		</div>
	</div>
</div>
<!--  FIN GLOBO BLANCO   -->
<!--  FIN GLOBO BLANCO   -->



<!--  INICIO GLOBO BLANCO   -->
<!--  INICIO GLOBO BLANCO   -->
<div class="globo_blanco" >
	<div class="globo_blanco_01">
		<div class="globo_blanco_02">

			<div class="globo_blanco_03 tab_min">
<div class="formu" >
<!-- -->
<table border="0" class="tab_miniaturas" cellpadding="0" cellspacing="0" summary="Tabla de Activación:Estadísticas">
<tr >
	<td valign="top" class="tede_01"><a href="#"><img src="<rewrite:rewrite url="/static/img/miniatura_09.gif"/>" alt="Imagen 09" width="100" height="60" /></a></td>
	<td valign="top" class="opcion_tab_01">
	<h3><bean:message key="modificar.config.formulario.estadisticas"/></h3>
	<html:radio  property="estadisticas" styleId="estadisticas1" value="1"  name="form" /><label for="estadisticas1"><bean:message key="modificar.config.formulario.EstadoActiv"/></label><html:radio  property="estadisticas" styleId="estadisticas0" value="0"  style="margin-left:15px;" name="form" /><label for="estadisticas0"><bean:message key="modificar.config.formulario.EstadoDesact"/></label>
	</td>
</tr>
</table><!-- -->
</div>
		</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO BLANCO   -->
<!--  FIN GLOBO BLANCO   -->



<!--  INICIO GLOBO BLANCO   -->
<!--  INICIO GLOBO BLANCO   -->
<div class="globo_blanco" >
	<div class="globo_blanco_01">
		<div class="globo_blanco_02">
			<div class="globo_blanco_03 tab_min">
<div class="formu" >
<!-- -->
<table border="0" class="tab_miniaturas" cellpadding="0" cellspacing="0" summary="Tabla de Activación:Widget Añadir Agrega">
<tr >
	<td valign="top" class="tede_01"><a href="#"><img src="<rewrite:rewrite url="/static/img/miniatura_10.gif"/>" alt="Imagen 10" width="100" height="60" /></a></td>
	<td valign="top" class="opcion_tab_01">

	<h3><bean:message key="modificar.config.formulario.plugginbusqueda"/></h3>
	<html:radio  property="plugginBusqueda" styleId="plugginBusqueda1"  value="1"  name="form" /><label for="plugginBusqueda1"><bean:message key="modificar.config.formulario.EstadoActiv"/></label><html:radio  property="plugginBusqueda" styleId="plugginBusqueda0" value="0"  style="margin-left:15px;" name="form" /><label for="plugginBusqueda0"><bean:message key="modificar.config.formulario.EstadoDesact"/></label>
	</td>
</tr>
</table><!-- -->
</div>
		</div>
		</div>
	</div>

</div>
<!--  FIN GLOBO BLANCO   -->
<!--  FIN GLOBO BLANCO   -->

<!--  INICIO GLOBO BLANCO   -->
<!--  INICIO GLOBO BLANCO   -->
<div class="globo_blanco" >
	<div class="globo_blanco_01">
		<div class="globo_blanco_02">

			<div class="globo_blanco_03 tab_min">
<div class="formu" >
<!-- -->
<table border="0" class="tab_miniaturas" cellpadding="0" cellspacing="0" summary="Tabla de Activación:Itinerarios Educativo">
<tr >
	<td valign="top" class="tede_01"><a href="#"><img src="<rewrite:rewrite url="/static/img/miniatura_12.gif"/>" alt="Imagen 09" width="100" height="60" /></a></td>
	<td valign="top" class="opcion_tab_01">
	<h3><bean:message key="modificar.config.formulario.itinerariosAprendizaje"/></h3>
	<html:radio  property="itinerariosAprendizaje" styleId="itinerariosAprendizaje1" value="1"  name="form" /><label for="itinerariosAprendizaje1"><bean:message key="modificar.config.formulario.EstadoActiv"/></label><html:radio  property="itinerariosAprendizaje" styleId="itinerariosAprendizaje0" value="0"  style="margin-left:15px;" name="form" /><label for="itinerariosAprendizaje0"><bean:message key="modificar.config.formulario.EstadoDesact"/></label>
	</td>
</tr>
</table><!-- -->
</div>
		</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO BLANCO   -->
<!--  FIN GLOBO BLANCO   -->




</div>
<!--  FIN CAJA DE FORMULARIO   -->

			</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO GRIS   -->

<!--  FIN GLOBO GRIS   -->


<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris conmargen" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div class="formu" >
				<h3><bean:message key="modificar.config.formulario.titulo3"/></h3>

<!--  INICIO GLOBO BLANCO   -->
<!--  INICIO GLOBO BLANCO   -->
<div class="globo_blanco" >
	<div class="globo_blanco_01">
		<div class="globo_blanco_02">
			<div class="globo_blanco_03 tab_min">
<div class="formu" >
<!-- -->
<table border="0" class="tab_miniaturas" cellpadding="0" cellspacing="0" summary="Tabla de Activación:Google Analytic">
<tr >
	<td valign="top" class="tede_01"><a href="#"><img src="<rewrite:rewrite url="/static/img/miniatura_10.gif"/>" alt="Imagen 10" width="100" height="60" /></a></td>
	<td valign="top" class="opcion_tab_01">

	<h3><bean:message key="modificar.config.formulario.googleAnalytic"/></h3>
	<html:radio  property="googleAnalytic" styleId="googleAnalytic1"  value="1"  name="form" /><label for="googleAnalytic1"><bean:message key="modificar.config.formulario.EstadoActiv"/></label><html:radio  property="googleAnalytic" styleId="googleAnalytic0" value="0"  style="margin-left:15px;" name="form" /><label for="googleAnalytic0"><bean:message key="modificar.config.formulario.EstadoDesact"/></label>
	</td>
</tr>
</table><!-- -->
</div>
		</div>
		</div>
	</div>

</div>
<!--  FIN GLOBO BLANCO   -->
<!--  FIN GLOBO BLANCO   -->

<!--  INICIO GLOBO BLANCO   -->
<!--  INICIO GLOBO BLANCO   -->
<div class="globo_blanco" >
	<div class="globo_blanco_01">
		<div class="globo_blanco_02">
			<div class="globo_blanco_03 tab_min">
<div class="formu" >
<!-- -->
<table border="0" class="tab_miniaturas" cellpadding="0" cellspacing="0" summary="Tabla de Activación:Codigo Google Analytic">
<tr >
	<td valign="top" class="tede_01"><a href="#"><img src="<rewrite:rewrite url="/static/img/miniatura_10.gif"/>" alt="Imagen 10" width="100" height="60" /></a></td>
	<td valign="top" class="opcion_tab_01">

	<h3><bean:message key="modificar.config.formulario.codga"/></h3>
	
	<html:textarea   property="codGa" name="form" cols="4" rows="4" title="<bean:message key='modificar.config.formulario.codga'/>" >${form.codGa}</html:textarea>


	</td>
</tr>
</table><!-- -->
</div>
		</div>
		</div>
	</div>

</div>
<!--  FIN GLOBO BLANCO   -->
<!--  FIN GLOBO BLANCO   -->



			</div>
			<!--  FIN CAJA DE FORMULARIO   -->

			</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->




<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris conmargen" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div class="formu" >
				<h3><bean:message key="modificar.config.formulario.titulo2"/></h3>

<!--  INICIO GLOBO BLANCO   -->
<!--  INICIO GLOBO BLANCO   -->
<div class="globo_blanco" >
	<div class="globo_blanco_01">
		<div class="globo_blanco_02">
			<div class="globo_blanco_03 tab_min">
<div class="formu" >
<!-- -->
<table border="0" class="tab_miniaturas" cellpadding="0" cellspacing="0" summary="Tabla de Activación:Imagen Principal">
<tr >
	<td valign="top" class="tede_01"><a href="#"><img src="<rewrite:rewrite url="/static/img/miniatura_log_01.gif"/>" alt="Imagen 01" width="100" height="60" /></a></td>
	<td valign="top" class="opcion_tab_01">

	<h3><bean:message key="modificar.config.formulario.imagen"/></h3>
	<label for="importarA01" style="padding-left:0;" ><span><bean:message key="modificar.config.formulario.selectimagen"/>: &nbsp;</span></label>
	<html:file name="form"  property="imagenFile"  style="margin-bottom:0 !important;"  title="Importe Objeto 01" styleId="SelImagenLogin"/><br />
	
	<html:radio  property="activarImagen" styleId="activarImagen5" onclick="document.getElementById('SelImagenLogin').disabled=false;
		document.getElementById('SelImagenLogin').style.backgroundColor='white'" value="5"  name="form" />
		<label for="activarImagen5" ><bean:message key="modificar.config.formulario.ActivImagenLogin"/></label>
		
	<html:radio  property="activarImagen" styleId="activarImagen4" value="4" style="margin-left:15px;" name="form" onclick="document.getElementById('SelImagenLogin').disabled=true;
		document.getElementById('SelImagenLogin').style.backgroundColor='white'" />
		<label for="activarImagen4"  >${form.imagen}</label>
</tr>
</table>
<!-- -->
</div>
<fieldset class="tipo" id="con_loading">
	
	<!-- Fin Botones  -->
	<!-- Fin Botones  -->
	</fieldset>
	<div id="loading"  style="z-index:100 !important">
		<%@ include file="/progressBar/barra.jsp"%>
	</div>
	<%@ include file="/progressBar/cabecera-barra.jsp" %>
		</div>

		</div>
	</div>
</div>
<!--  FIN GLOBO BLANCO   -->
<!--  FIN GLOBO BLANCO   -->
<!--  INICIO GLOBO BLANCO   -->
<!--  INICIO GLOBO BLANCO   -->
<div class="globo_blanco" >
	<div class="globo_blanco_01">
		<div class="globo_blanco_02">
			<div class="globo_blanco_03 tab_min">
<div class="formu" >

<!-- -->
<table border="0" class="tab_miniaturas" cellpadding="0" cellspacing="0" summary="Tabla de Activación:Open ID">
<tr >
	<td valign="top" class="tede_01"><a href="#"><img src="<rewrite:rewrite url="/static/img/miniatura_log_02.gif"/>" alt="Imagen 02" width="100" height="60" /></a></td>
	<td valign="top" class="opcion_tab_01">
	<h3><bean:message key="modificar.config.formulario.openid"/></h3>
	<html:radio  property="openID" styleId="openID1"  value="1"  name="form" /><label for="openID1"><bean:message key="modificar.config.formulario.EstadoActiv"/></label>
	<html:radio  property="openID" styleId="openID0" value="0"  style="margin-left:15px;" name="form" /><label for="openID0"><bean:message key="modificar.config.formulario.EstadoDesact"/></label>
	</td>
</tr>

</table>

<!-- -->
</div>
		</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO BLANCO   -->
<!--  FIN GLOBO BLANCO   -->
<!--  INICIO GLOBO BLANCO   -->
<!--  INICIO GLOBO BLANCO   -->
<div class="globo_blanco" >
	<div class="globo_blanco_01">

		<div class="globo_blanco_02">
			<div class="globo_blanco_03 tab_min">
<div class="formu" >
<!-- -->
<table border="0" class="tab_miniaturas" cellpadding="0" cellspacing="0" summary="Tabla de Activación:Contraseña">
<tr >
	<td valign="top" class="tede_01"><a href="#"><img src="<rewrite:rewrite url="/static/img/miniatura_log_03.gif"/>" alt="Imagen 03" width="100" height="60" /></a></td>
	<td valign="top" class="opcion_tab_01">
	<h3><bean:message key="modificar.config.formulario.enlace"/></h3>
	<html:radio  property="enlacePassword" styleId="enlace1"  value="1"  name="form" /><label for="enlace1"><bean:message key="modificar.config.formulario.EstadoActiv"/></label>
	<html:radio  property="enlacePassword" styleId="enlace0" value="0"  style="margin-left:15px;" name="form" /><label for="enlace0"><bean:message key="modificar.config.formulario.EstadoDesact"/></label>
	</td>
</tr>
</table>
<!-- -->
</div>
		</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO BLANCO   -->
<!--  FIN GLOBO BLANCO   -->
<!--  INICIO GLOBO BLANCO   -->
<!--  INICIO GLOBO BLANCO   -->

<div class="globo_blanco" >
	<div class="globo_blanco_01">
		<div class="globo_blanco_02">
			<div class="globo_blanco_03 tab_min">
<div class="formu" >
<!-- -->
<table border="0" class="tab_miniaturas" cellpadding="0" cellspacing="0" summary="Tabla de Activación:Registrarse">
<tr >
	<td valign="top" class="tede_01"><a href="#"><img src="<rewrite:rewrite url="/static/img/miniatura_log_04.gif"/>" alt="Imagen 04" width="100" height="60" /></a></td>
	<td valign="top" class="opcion_tab_01">
	<h3><bean:message key="modificar.config.formulario.registrese"/></h3>

	<html:radio  property="registrese" styleId="registrese1"  value="1"  name="form" /><label for="registrese1"><bean:message key="modificar.config.formulario.EstadoActiv"/></label>
	<html:radio  property="registrese" styleId="registrese0" value="0"  style="margin-left:15px;" name="form" /><label for="registrese0"><bean:message key="modificar.config.formulario.EstadoDesact"/></label>
	</td>
</tr>
</table>
<!-- -->
</div>
		</div>
		</div>
	</div>
</div>

<!--  FIN GLOBO BLANCO   -->
<!--  FIN GLOBO BLANCO   -->
</div>
<!--  FIN CAJA DE FORMULARIO   -->

			</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris conmargen" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div class="formu" >
				<h3><bean:message key="modificar.config.formulario.imagenPorDefecto"/></h3>
<!--  INICIO GLOBO BLANCO   -->
<!--  INICIO GLOBO BLANCO   -->

<div class="globo_blanco" >
	<div class="globo_blanco_01">
		<div class="globo_blanco_02">
			<div class="globo_blanco_03 tab_min">
<div class="formu" >
<!-- -->
<table border="0" class="tab_miniaturas" cellpadding="0" cellspacing="0" summary="Tabla de Activación:Cambio Imagen por defecto">
<tr >
	<td valign="top" class="tede_01"><a href="#"><img src="<rewrite:rewrite url="${form.imagenPorDefecto}"/>" alt="Imagen 04" width="100" height="60" /></a></td>
	<td valign="top" class="opcion_tab_01">
	<h3><bean:message key="modificar.config.formulario.imagenPorDefecto"/></h3>
	<label for="importarA01" style="padding-left:0;" ><span><bean:message key="modificar.config.formulario.imagenPorDefecto"/>: &nbsp;</span></label>
	<html:file name="form"  property="imagenPorDefectoFile"  style="margin-bottom:0 !important;"  title="Importe Objeto 01" styleId="SelImagenLogin"/><br />
	</td>
</tr>
</table>
<!-- -->
</div>
		</div>
		</div>
	</div>
</div>

<!--  FIN GLOBO BLANCO   -->
<!--  FIN GLOBO BLANCO   -->

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
<fieldset class="tipo ft_centrada">
<input class="boton_125"  type="submit"  value="<bean:message key="modificar.config.formulario.guardar"/>" />
</fieldset>
<!-- Fin Botones  -->
	
</form>
</div>
<!-- Fin plantilla contenido  -->
<!-- Fin plantilla contenido  -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->


</div>
<script type="text/javascript">
window.onload(carga());
function carga()
{

/* if(document.getElementById('activarImagenPortada2').checked==true)
 {
 	document.getElementById('SelImagen').disabled=true;
 }*/
 if(document.getElementById('activarImagen4').checked==true)
 {
 	document.getElementById('SelImagenLogin').disabled=true;
 }
  if(document.getElementById('idRssGaleria').value=='0.0')
 {
 	document.getElementById('numOdesGaleria').disabled=true;
 }
 }
function desactivar()
{

 if(document.getElementById('idRssGaleria').value=='0.0')
 {
 	document.getElementById('numOdesGaleria').disabled=true;
 }
 else
 {
 	document.getElementById('numOdesGaleria').disabled=false;
 }

}
function progressBar()
{
if(document.getElementById('activarImagen5').checked==true)
 {
 startLoading();
 }

}

</script>
</tiles:put>
</tiles:insert>
