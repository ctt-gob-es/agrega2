<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tags/googleAnalytic.tld" prefix="analytic" %>

<tiles:insert definition="layout-usuario">

<tiles:put name="title" type="string">
	<bean:message key="portada.title"/>
</tiles:put>

    <tiles:put name="title" type="string">
        <bean:message key="title.Comun"/>
    </tiles:put>

    
    <tiles:put name="body" type="string">
    
    	<%@ include file="/taglib-imports.jspf" %>
    
        <%@ include file="/es/pode/visualizador/presentacion/utilidades/utilidades-javascript.jspf" %>
        
        <div class="plantilla_contenido">
        <analytic:googleAnalytic />

<h2><bean:message key="utilidades.titulo"/></h2>


<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->

			<div class="formulario" style="padding-left:10px"><a href="<html:rewrite action="/AgregaSlider/AgregaSlider.do"/>" class="titular_03"><strong><bean:message key="utilidades.agregaSlider.titulo"/></strong></a><p class="parrafo_comun" ><bean:message key="utilidades.contenidoDinamico.texto"/></p></div>
				<!--  FIN CAJA DE FORMULARIO   -->
			</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->

<!--  INICIO GLOBO GRIS   -->

<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" style="margin-top:10px" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div class="formulario" style="padding-left:10px"><a class="titular_03" href="<html:rewrite action="/AgregaContenidoDinamico/AgregaContenidoDinamico.do"/>"><strong><bean:message key="utilidades.contenidoDinamico.titulo"/></strong></a><p class="parrafo_comun" ><bean:message key="utilidades.contenidoDinamico.texto"/></p></div>
				<!--  FIN CAJA DE FORMULARIO   -->

			</div>

		</div>
	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->



</div>
<!-- Fin plantilla contenido  -->
</tiles:put>
</tiles:insert>