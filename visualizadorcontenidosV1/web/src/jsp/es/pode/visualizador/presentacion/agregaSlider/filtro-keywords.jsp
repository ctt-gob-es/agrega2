<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<tiles:insert definition="layout-usuario">

<tiles:put name="title" type="string">
      <bean:message key="title.Comun"/>
</tiles:put>


<tiles:put name="body" type="string">

<%@ include file="/taglib-imports.jspf" %>


<div class="plantilla_contenido" id="ventana_flotante">

<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
<jsp:include page="/layout/messages.jsp" flush="true" />

<h2><bean:message key="agregaSlider.palabrasClave.cabecera"/></h2>


<form id="agregaSliderFiltroKeywordsSiguienteForm" action='<html:rewrite action="AgregaSlider/FiltroKeywordsSiguiente"/>' method="post" >
<p class="parrafo_comun" id="separacion2"><bean:message key='agregaSlider.textoExplicativoKeywords'/></p>
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">

			<!--  INICIO CAJA DE FORMULARIO   -->
			<div id="formulario" >
  				<div class="fila_de_tabla">
  					<div class="contenedor_izquierda">
  						<div class="text"><label for="Tipo"><bean:message key="agregaSlider.palabras.texto"/></label></div>
 					</div>
					<div class="contenedor_derecha">
						<div class="text"><input name="keywords" class="nombreGrupo" value="${form.keywords}" id="keywords" type="text" title="<bean:message key="agregaSlider.introduzcaKeywords"/>"/></div>

					</div>
					<div class="linea_separadora"></div>
					<br class="oculto" />
				</div>
				<!-- -->
  				<div class="fila_de_tabla">
  					<div class="contenedor_izquierda">
  						<div class="text"><label for="tipoIdioma"><bean:message key="agregaSlider.idioma.texto"/></label></div>

 					</div>
					<div class="contenedor_derecha">
						<bean:define id="idiomaAlt"><bean:message key="agregaSlider.idioma.alt"/></bean:define>
								<c:choose>
			                        <c:when test="${!empty form.idiomasComboBackingList}">
			                           <html:select name="form" property="idiomasCombo" styleId="IdiomaCombo" title="${idiomaAlt}">
			                               <html:optionsCollection name="form" property="idiomasComboBackingList" label="label" value="value"/>
			                           </html:select>
			                        </c:when>
			                        <c:otherwise>
			                            <html:select name="form" property="idiomasCombo" styleId="IdiomaCombo" title="${idiomaAlt}"/>
			                        </c:otherwise>
			                    </c:choose>

					</div>
					<div class="linea_separadora"></div>
					<br class="oculto" />
				</div>
				<!-- -->
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
<input class="boton_125_de_2"  type="submit"  value="<bean:message key='agregaSlider.botonSiguiente'/>" />
</form>

	<!--		Formulario secundario y boton volver		-->
		<form id="agregaSliderCodigoEmbebidoVolverForm" action='<html:rewrite action="Utilidades/Utilidades"/>' method="post" >
				<input class="boton_125_de_2_izq"  type="submit"  value="<bean:message key='agregaSlider.botonVolver'/>" />
		</form>
</fieldset>
<!-- Fin Botones  -->
<!-- Fin Botones  -->


</div>

<!-- Fin plantilla contenido  -->
<!-- Fin plantilla contenido  -->
</tiles:put>
</tiles:insert>