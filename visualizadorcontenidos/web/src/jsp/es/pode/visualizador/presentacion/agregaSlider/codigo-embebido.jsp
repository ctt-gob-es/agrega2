<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>


<tiles:insert definition="layout-menu-0">

<tiles:put name="title" type="string">
      <bean:message key="title.Comun"/>
</tiles:put>

<tiles:put name="body-principal" type="string">

<!--		Inicio plantilla contenido		-->

<div class="col_der a_web" id="descargas">
		<section >
			<header>
			<h2><bean:message key="agregaSlider.codigoEmbebido.cabecera"/></h2>
			</header>
			
			<article class="bloque_titulares" id="agrega_dinamico">
	   		<form id="agregaSliderCodigoEmbebidoVolverForm" action='<html:rewrite action="AgregaSlider/CodigoEmbebidoVolver"/>' method="post" >
	   		<p><bean:message key="agregaSlider.explicacionCodigo" /></p>
			<fieldset class="clearfix con_b">
			<textarea class="con_dyn" readonly="readonly" id="codigo_embebido" title="<bean:message key="agregaSlider.codigo" />" >${form.codigo}</textarea>
			</fieldset>
			<fieldset class="botonera">
			<input class="boton boton_flot"  type="submit"  value="<bean:message key='agregaSlider.botonVolver'/>" />
			</fieldset>
			</form>
			</article>
		</section>
</div>
</tiles:put>
</tiles:insert>