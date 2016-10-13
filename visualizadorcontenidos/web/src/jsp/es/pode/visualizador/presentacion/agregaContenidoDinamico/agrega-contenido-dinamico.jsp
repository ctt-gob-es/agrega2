<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>


<tiles:insert definition="layout-menu-0">

<tiles:put name="title" type="string">
      <bean:message key="title.Comun"/>
</tiles:put>


<tiles:put name="body-principal" type="string">

<%@ include file="/taglib-imports.jspf" %>

<!--		Inicio plantilla contenido		-->

<div class="col_der a_web" id="descargas">
		<section >
			<header>
			<h2><bean:message key="agregaContenidoDinamico.titulo"/></h2>
			</header>

<!--		Inicio del formulario principal		-->
		<article class="bloque_titulares" id="agrega_dinamico">
			<form id="agregaContenidoDinamicoAgregaContenidoDinamicoVolverForm" action='<html:rewrite action="/AgregaContenidoDinamico/AgregaContenidoDinamicoVolver"/>' method="post">
				<p id="separacion2"><bean:message key="agregaContenidoDinamico.texto" /></p>
	   		<fieldset class="clearfix con_b">
			<textarea class="con_dyn" readonly="readonly" title="<bean:message key="agregaContenidoDinamico.codigo" />" >${form.codigo}
	   		</textarea>
			</fieldset>
	   		<fieldset class="botonera">
	   		<input type="submit"  title="Volver" value="<bean:message key='agregaContenidoDinamico.botonVolver'/>" class="boton" />
	   		</fieldset>
	   		</form>	
    	</article>
<!--  -->

		</section>
</div>
</tiles:put>
</tiles:insert>