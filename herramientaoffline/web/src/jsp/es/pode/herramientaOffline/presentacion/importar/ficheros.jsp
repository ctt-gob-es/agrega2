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
<h2><bean:message key="importar.cabecera"/></h2>
<form method="post" action="<html:rewrite action="/Importar/FicherosSubmit"/>?progressMonitor=myProgressMonitor" enctype="multipart/form-data" onsubmit="startLoading()">
<p class="parrafo_comun" id="separacion2"><bean:message key="importar.texto"/></p>
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->

			<div class="formu" >
			 <div class="fila_de_tabla">
  						<div class="text" ><label for="importarA01" class="red_cc"><span>01&nbsp;&nbsp;</span></label><input name="fichero1" class="nombreGrupo" onfocus="limpiarTexto(this)" style="width:50%" value=""  onblur="this.style.backgroundColor='#e1e1e1'" id="importarA01"  type="file" title="<bean:message key="comun.seleccione"/>"  /></div>
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


<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris conmargen"  >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">

			<!--  INICIO CAJA DE FORMULARIO   -->
			<div class="formu" >
			 <div class="fila_de_tabla">
  						<div class="text" ><label for="importarA02" class="red_cc"><span>02&nbsp;&nbsp;</span></label><input name="fichero2" class="nombreGrupo" onfocus="limpiarTexto(this)" style="width:50%" value=""  onblur="this.style.backgroundColor='#e1e1e1'" id="importarA02"  type="file" title="<bean:message key="comun.seleccione"/>"  /></div>
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


<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris conmargen"   >
	<div class="globo_gris_01">
		<div class="globo_gris_02">

			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div class="formu" >
			 <div class="fila_de_tabla">
  						<div class="text" ><label for="importarA03" class="red_cc"><span>03&nbsp;&nbsp;</span></label><input name="fichero3" class="nombreGrupo" onfocus="limpiarTexto(this)" style="width:50%" value=""  onblur="this.style.backgroundColor='#e1e1e1'" id="importarA03"  type="file" title="<bean:message key="comun.seleccione"/>"  /></div>
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


<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris conmargen"   >
	<div class="globo_gris_01">

		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div class="formu" >
			 <div class="fila_de_tabla">
  						<div class="text" ><label for="importarA04" class="red_cc"><span>04&nbsp;&nbsp;</span></label><input name="fichero4" class="nombreGrupo" onfocus="limpiarTexto(this)" style="width:50%" value=""  onblur="this.style.backgroundColor='#e1e1e1'" id="importarA04"  type="file" title="<bean:message key="comun.seleccione"/>"  /></div>
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


<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->

<div class="globo_gris conmargen" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div class="formu" >
			 <div class="fila_de_tabla">
  						<div class="text" ><label for="importarA05" class="red_cc"><span>05&nbsp;&nbsp;</span></label><input name="fichero5" class="nombreGrupo" onfocus="limpiarTexto(this)" style="width:50%" value=""  onblur="this.style.backgroundColor='#e1e1e1'" id="importarA05"  type="file" title="<bean:message key="comun.seleccione"/>"  /></div>
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

<!-- Inicio Botones  -->

<!-- Inicio Botones  -->
<fieldset class="tipo" id="con_loading">
<input class="boton_125_de_2_izq" name="action" type="submit"  value="<bean:message key="comun.Cancelar"/>" />
<input class="boton_125_de_2" name="action" onclick="cambio('capa1')" type="submit"  value="<bean:message key="comun.Aceptar"/>" />
</fieldset>
<!-- Fin Botones  -->
<div id="loading"  style="z-index:100 !important">
<%@ include file="/progressBar/barra.jsp"%>
</div>
<%@ include file="/progressBar/cabecera-barra.jsp" %>
<!-- Fin Botones  -->
</form>

</div>
</tiles:put>
</tiles:insert>