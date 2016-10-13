<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@ taglib uri="/WEB-INF/tlds/tags-modificadorWeb.tld"  prefix="mod" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<tiles:insert definition="${sessionScope.offline == true ? 'layout-offline' : 'layout-administrador'}">

<tiles:put name="title" type="string">
      <bean:message key="title.Comun"/>
</tiles:put>

<tiles:put name="body" type="string">

 
<!-- *****************************   Inicio  Plantilla contenido      ********************************** -->
<div class="plantilla_contenido">

<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
<jsp:include page="/layout/messages.jsp" flush="true" />

<h2><bean:message key="export.titulo"/></h2>
<form id="recogerDatosRecogerDatosSubmitForm" action="<html:rewrite action="/Export/RecogerDatosSubmit"/>"  method="post">
<p class="parrafo_comun" id="separacion2"><bean:message key="export.esNecesarioRellenarCompletar"/>.</p>

<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->

			<div id="formulario" >
  				<div class="fila_de_tabla" >
  					<div class="contenedor_izquierda">
  						<div class="text"><label for="destino"><bean:message key="export.destino"/></label></div>
 					</div>
					<div class="contenedor_derecha">
						<div class="text"><input name="pathDestino" value="${form.pathDestino}"  onblur="this.style.backgroundColor='#e1e1e1'" id="destino" type="text" title="<bean:message key="export.introduzcaDestino"/>"  /></div>
					</div>

					<div class="linea_separadora"></div>
					<br class="oculto" />
				</div>
				<!-- -->

			<div class="contenedor_izquierda">
  						<div class="text"><label for="tipoTransformacion"><bean:message key="export.formato"/></label></div>
 					</div>
			 <div class="fila_de_tabla">
  						<div class="text ft_lateral" >
  						<input type="radio" class="boton_radio" value="CONTENIDOS" id="Formato06" checked="checked" name="tipoTransformacion" />
  						<label for="Formato06" class="alineada"><bean:message key ="cambio.CONTENIDOS"/></label></div>
				<div class="linea_separadora"></div>
				<br class="oculto" />
				</div>
				       
				<div class="fila_de_tabla">
  						<div class="text ft_lateral">
  						<input  type="radio" class="boton_radio" value="HTML" id="Formato05"  name="tipoTransformacion" />
  						<label for="Formato05"  class="alineada2"><bean:message key ="cambio.HTML"/></label></div>
				<div class="linea_separadora"></div>
				<br class="oculto" />
				</div>


			 <div class="fila_de_tabla">
  						<div class="text ft_lateral" >
  						<input type="radio" class="boton_radio" value="SCORM2004SS" id="Formato01" name="tipoTransformacion" /><label for="Formato01" class="alineada">
							<bean:message key ="cambio.SCORM2004SS"/> </label></div>
				<div class="linea_separadora"></div>
				<br class="oculto" />
				</div>
				<!--     -->
			<div class="fila_de_tabla">
  						<div class="text ft_lateral">
  						<input type="radio" class="boton_radio" value="SCORM2004" id="Formato02" name="tipoTransformacion" /><label for="Formato02"  class="alineada2">
  						<bean:message key ="exportar.formato2"/></label></div>

				<div class="linea_separadora"></div>
				<br class="oculto" />
				</div>
				<!--     -->
			<div class="fila_de_tabla">
  						<div class="text ft_lateral">
  						<input type="radio" class="boton_radio" value="SCORM12" id="Formato03"  name="tipoTransformacion" /><label for="Formato03"  class="alineada2">
  						<bean:message key ="cambio.SCORM12"/>  </label></div>
				<div class="linea_separadora"></div>
				<br class="oculto" />

				</div>
				<!--     -->
			<div class="fila_de_tabla">
  						<div class="text ft_lateral">
  						<input  type="radio" class="boton_radio" value="IMSCP" id="Formato04"  name="tipoTransformacion" /><label for="Formato04"  class="alineada2">
  						<bean:message key ="cambio.IMSCP"/></label></div>
				<div class="linea_separadora"></div>
				<br class="oculto" />
				</div>
				

				

				<!--  FIN CAJA DE FORMULARIO   -->

	
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
<input class="boton_125_de_2"  type="submit" name="action" value="<bean:message key="export.aceptar"/>" />
<input class="boton_125_de_2_izq"  type="submit" name="action" value="<bean:message key="export.volver"/>" />
<input class="boton_125"  type="submit" name="action" value="<bean:message key="export.cancelar"/>" />
</fieldset>
<!-- Fin Botones  -->
<!-- Fin Botones  -->

</form>
</div>
<!-- Fin plantilla contenido  -->
    </tiles:put>

</tiles:insert>
