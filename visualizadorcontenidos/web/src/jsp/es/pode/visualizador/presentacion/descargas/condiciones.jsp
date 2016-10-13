<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="analytic" uri="/WEB-INF/tags/googleAnalytic.tld"%>
<%@ taglib prefix="agrega" uri="/WEB-INF/tags/agregaProperties.tld" %>


<tiles:insert definition="layout-menu-0">
<tiles:put name="title" type="string">
      <bean:message key="title.Comun"/>
</tiles:put>
<tiles:put name="body-principal" type="string">

<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- ATENCION!! RREMPLAZAR ESTA CAPA DE LA  PLANTILLA DE CONTENIDO ("plantilla_contenido") POR LA QUE SE DESEE EN LA PLANTILLA BASE  -->
<!-- Inicio plantilla contenido  -->
<!-- Inicio plantilla contenido  -->
<div class="col_der">
<analytic:googleAnalytic />
<section >
<header>
<h2><bean:message key="descargas.condiciones.cabecera"/> : ${form.titulo}</h2>
</header>
<bean:define id="aceptar"><bean:message key="descargas.condiciones.aceptar"/></bean:define>
<form method="post" action="<html:rewrite action="/Descargas/CondicionesSubmit"/>" >
			<p><bean:message key="descargas.condiciones.texto1"/></p>
			<p><bean:message key="descargas.condiciones.texto2"/></p>
			<p><bean:message key="descargas.condiciones.texto3"/></p>
			<p><bean:message key="descargas.condiciones.texto4"/></p>
			<p><bean:message key="descargas.condiciones.texto5"/></p>
			<input type="hidden" name="identificador" value="${form.identificador }"/>
			<div class="text" ><label for="Disclaimer" class="oculto" ><bean:message key="descargas.condiciones.acepto"/></label>
			<input type="checkbox" id="Disclaimer" name="condiciones" class="boton_radio" /><span class="vert"><bean:message key="descargas.condiciones.acepto"/></span></div>
			<!-- Inicio Botones  -->
<!-- Inicio Botones  -->
<fieldset class="tipo">
<input class="boton_125_de_2_izq" name="action" type="submit"  value="<bean:message key="descargas.condiciones.cancelar"/>" />
<analytic:botonGoogleAnalyticTag name="action" type="submit" clase="boton_125_de_2" value="${aceptar}" nombreAnalytic="Descargas/${form.titulo}"/>
</fieldset>
<!-- Fin Botones  -->
<!-- Fin Botones  -->
</form>
</section>
</div>
</tiles:put>
</tiles:insert>