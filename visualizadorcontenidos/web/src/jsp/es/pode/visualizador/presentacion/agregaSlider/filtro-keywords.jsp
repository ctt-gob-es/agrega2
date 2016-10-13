<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<tiles:insert definition="layout-menu-0">

<tiles:put name="title" type="string">
      <bean:message key="title.Comun"/>
</tiles:put>


<tiles:put name="body-principal" type="string">

<%@ include file="/taglib-imports.jspf" %>
<%@ include file="/es/pode/visualizador/presentacion/agregaSlider/filtro-keywords-javascript.jspf" %>


<div class="col_der a_web" id="descargas">
		<section >
			<header>
			<h2><bean:message key="agregaSlider.palabrasClave.cabecera"/></h2>
			</header>
		<!--  -->
    	<article class="bloque_titulares" id="agrega_slider">
   		<form id="agregaSliderFiltroKeywordsSiguienteForm" action='<html:rewrite action="AgregaSlider/FiltroKeywordsSiguiente"/>' method="post" >
	   		<p><bean:message key='agregaSlider.textoExplicativoKeywords'/></p>
	
	   		<fieldset class="clearfix con_b">
		   		<label for="password_portada"><bean:message key="agregaSlider.palabras.texto"/></label>
				<input name="keywords" class="de_texto" value="${form.keywords}" id="keywords" type="text" title="<bean:message key="agregaSlider.introduzcaKeywords"/>"/>
			</fieldset>
			
			<fieldset class="clearfix con_b">
		   		<label for="idioma"><bean:message key="agregaSlider.idioma.texto"/></label>
				<span class="caja_de_boton">
					<bean:define id="idiomaAlt"><bean:message key="agregaSlider.idioma.alt"/></bean:define>
					<c:choose>
						<c:when test="${!empty form.idiomasComboBackingList}">
							<html:select name="form" property="idiomasCombo" styleId="IdiomaCombo" title="${idiomaAlt}" styleClass="select">
								<html:optionsCollection name="form" property="idiomasComboBackingList" label="label" value="value"/>
							</html:select>
						</c:when>
						<c:otherwise>
						    <html:select name="form" property="idiomasCombo" styleId="IdiomaCombo" title="${idiomaAlt}"/>
						</c:otherwise>
		            </c:choose>
	    		</span>
			</fieldset>
			
	   		<fieldset class="botonera botonera_de_dos clearfix">
		   		<input type="submit"  value="<bean:message key='agregaSlider.botonSiguiente'/>" class="boton" />
		   		<a class="boton boton_flot" href='<html:rewrite action="Utilidades/Utilidades"/>'><bean:message key='agregaSlider.botonVolver'/></a>
	   		</fieldset>
   		</form>
    	</article>

<!--  -->
</section>
</div>
<script type="text/javascript">
function cambiarIdioma(){
	jQuery("#buscadorGeneral")[0].autocompleter.flushCache();
	jQuery("#buscadorGeneral")[0].autocompleter.setExtraParams({idiomasCombo: document.getElementById('idioma').value})
}

</script>

</tiles:put>
</tiles:insert>