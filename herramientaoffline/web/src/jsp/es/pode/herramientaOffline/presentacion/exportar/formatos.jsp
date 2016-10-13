<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<tiles:insert definition="layout-offline">
	<tiles:put name="title" type="string">
		<bean:message key="title.Comun" />
	</tiles:put>
	<tiles:put name="body" type="string">
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- ATENCION!! RREMPLAZAR ESTA CAPA DE LA  PLANTILLA DE CONTENIDO ("plantilla_contenido") POR LA QUE SE DESEE EN LA PLANTILLA BASE  -->
<!-- Inicio plantilla contenido  -->
<div class="plantilla_contenido">
<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
<jsp:include page="/layout/messages.jsp" flush="true" />
<h2><bean:message key="exportar.cabecera"/></h2>
<form method="post" action="<html:rewrite action="/Exportar/FormatosSubmit"/>" >

<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div id="formulario_02" >

			 <div class="fila_de_tabla">
  						<div class="text ft_lateral" ><input type="radio" class="boton_radio" id="Descarga01" name="formato" value="CONTENIDOS"/><label for="Descarga01" class="alineada"><bean:message key="exportar.opciones.label.cym"/></label></div>
				<div class="linea_separadora"></div>
				<br class="oculto" />
				</div>
			<div class="fila_de_tabla">
  						<div class="text ft_lateral" ><input type="radio" class="boton_radio" id="Descarga02" name="formato" value="HTML"/><label for="Descarga02" class="alineada"><bean:message key="exportar.opciones.label.html"/></label></div>
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

<p class="parrafo_comun" id="separacion3"><bean:message key="exportar.opciones.texto"/></p>
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">

			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div id="formulario" >
			 <div class="fila_de_tabla">
  						<div class="text ft_lateral" ><input type="radio" class="boton_radio" id="Formato01"  name="formato" value="SCORM_2004_SIN_SUBMANIFIESTO"/><label for="Formato01" class="alineada"><bean:message key="exportar.opciones.label.scorm2004sm"/></label></div>
				<div class="linea_separadora"></div>
				<br class="oculto" />
				</div>

				<!--     -->
			<div class="fila_de_tabla">
  						<div class="text ft_lateral"><input type="radio" class="boton_radio" id="Formato02" checked="checked" name="formato" value="SCORM_2004"/><label for="Formato02"  class="alineada2"><bean:message key="exportar.opciones.label.scorm2004"/></label></div>
				<div class="linea_separadora"></div>
				<br class="oculto" />
				</div>
				<!--     -->
			<div class="fila_de_tabla">

  						<div class="text ft_lateral"><input type="radio" class="boton_radio" id="Formato03"  name="formato" value="SCORM_12"/><label for="Formato03"  class="alineada2"><bean:message key="exportar.opciones.label.scorm12"/></label></div>
				<div class="linea_separadora"></div>
				<br class="oculto" />
				</div>
				<!--     -->
			<div class="fila_de_tabla">
  						<div class="text ft_lateral"><input  type="radio" class="boton_radio" id="Formato04"  name="formato" value="IMS_CP"/><label for="Formato04"  class="alineada3"><bean:message key="exportar.opciones.label.imscp"/></label></div>
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
<input type="hidden" name="idOde" value="${form.idOde }"/>
<!-- Inicio Botones  -->
<!-- Inicio Botones  -->
<fieldset class="tipo">
<input class="boton_125_de_2_izq" name="action" type="submit"  value="<bean:message key="comun.Volver"/>" />
<input class="boton_125_de_2" name="action" type="submit"  value="<bean:message key="comun.Descargar"/>" />
</fieldset>
<!-- Fin Botones  -->
<!-- Fin Botones  -->
</form>

</div>
</tiles:put>
</tiles:insert>
