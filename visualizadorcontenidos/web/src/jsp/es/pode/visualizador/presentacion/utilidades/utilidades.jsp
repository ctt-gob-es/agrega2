<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tags/googleAnalytic.tld" prefix="analytic" %>

<tiles:insert definition="layout-menu-0">

<tiles:put name="title" type="string">
	<bean:message key="portada.title"/>
</tiles:put>

    <tiles:put name="title" type="string">
        <bean:message key="title.Comun"/>
    </tiles:put>

    
    <tiles:put name="body-principal" type="string">
    
    	<%@ include file="/taglib-imports.jspf" %>
    
        <%@ include file="/es/pode/visualizador/presentacion/utilidades/utilidades-javascript.jspf" %>

<div class="col_der a_web" id="descargas">
	<analytic:googleAnalytic />
		<section >
			<header>
			<h2><bean:message key="utilidades.titulo"/></h2>

			</header>
		<!--  -->
    	<article class="bloque_titulares">
   	
   		<ul >
   			<li class="clearfix"><a href="<html:rewrite action="/AgregaSlider/AgregaSlider.do"/>" class="titular"><bean:message key="utilidades.agregaSlider.titulo"/></a>
   			<p><bean:message key="utilidades.contenidoDinamico.texto"/></p>
   			</li>

   			<!--  -->
   			<li class="clearfix"><a href="<html:rewrite action="/AgregaContenidoDinamico/AgregaContenidoDinamico.do"/>" class="titular"><bean:message key="utilidades.contenidoDinamico.titulo"/></a>
   			<p><bean:message key="utilidades.contenidoDinamico.texto2"/></p>

   			</li>
   		 </ul>
    	</article>
<!--  -->
</section>

</div>
<!-- Fin plantilla contenido  -->
</tiles:put>
</tiles:insert>