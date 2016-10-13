<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tags/googleAnalytic.tld" prefix="analytic" %>


<tiles:insert definition="layout-usuario">

<tiles:put name="title" type="string">
      <bean:message key="title.Comun"/>
</tiles:put>


<tiles:put name="body" type="string">

<%@ include file="/taglib-imports.jspf" %>

<!--		Inicio plantilla contenido		-->

<!--  INICIO CUERPO GENERAL   -->
<!--  INICIO CUERPO GENERAL   -->
<div class="plantilla_contenido" >
<analytic:googleAnalytic />

<h2><bean:message key="feeds.agregador.queEsEsto.titulo"/></h2>

<div class="parrafo_comun" id="separacion"><bean:message key="feeds.agregador.queEsEsto.opml.parrafo1"/>

</div>


<!-- Inicio Botones  -->
<!-- Inicio Botones  -->
<fieldset class="tipo ft_centrada">
<form id="agregadorFeedsCUFormularioQueEsEstoOPMLVolverOPMLForm" action='<html:rewrite action="/AgregadorFeedsCU/FormularioQueEsEstoOPMLVolverOPML"/>' method="post"  >
	<input class="boton_125"  type="submit"  value="<bean:message key="feeds.agregador.queEsEsto.opml.volver"/>" />
</form>
</fieldset>
<!-- Fin Botones  -->
<!-- Fin Botones  -->


</div>
<!--		Fin de la capa plantilla_contenido		-->


</tiles:put>


</tiles:insert>