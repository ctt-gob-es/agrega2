<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<%@ include file="/taglib-imports.jspf" %>	
<%@ page import="es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tags/cabeceraTag.tld" prefix="cabecera" %>
<%@ taglib uri="/WEB-INF/tags/idiomasBanderas.tld" prefix="banderas" %>
<%@ taglib uri="/WEB-INF/tags/anadirMotorBusqueda.tld" prefix="motorBusqueda" %>

<html:xhtml/>
<!-- INICIO METANAVEGACION   -->
<!--
<div id="metanavegacion">
	<ol>
			<li class="oculto"><a href="#contenido_central" title="Contenido Principal"><strong><bean:message key="metanavegacion.contenido"/></strong></a></li>
-->			<!-- Estos dos links cambian cuando el usuario se ha autenticado -->
			<!-- TODO: Cambiar en fase II cuando se use autenticacion -->
			<!-- <li><a href="#" title="<bean:message key="metanavegacion.accesibilidad"/>" id="registro"><bean:message key="metanavegacion.accesibilidad"/></a></li> -->
			<!-- <li><a href="#" title="<bean:message key="metanavegacion.restringida"/>" id="restringida"><bean:message key="metanavegacion.restringida"/></a></li> -->
			<!-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->
<!--			
			<%@ include file="/layout/menu-perfil.jsp" %>
		    <cabecera:cabeceraTag/>
-->		    
		    <!-- Tag para pintar las banderas con los posibles idiomas de la plataforma -->			
<!--
			<banderas:idiomasBanderasTag urlIdiomasBanderas="${initParam.url_idiomas_banderas}"/>
		    
			<li><a href="<rewrite:rewrite url="${initParam.url_acercaDeAgrega}"/>" title="<bean:message key="metanavegacion.acercade"/>" ><bean:message key="metanavegacion.acercade"/></a></li>
			<li><a href="http://www.proyectoagrega.es" target="_blank" title="<bean:message key="metanavegacion.comunidad.title"/>"><bean:message key="metanavegacion.comunidad"/></a></li>
			<li><a href="<rewrite:rewrite url="${initParam.url_accesibilidad}"/>" title="<bean:message key="metanavegacion.accesibilidad"/>"><bean:message key="metanavegacion.accesibilidad"/></a></li>
			<li><a href="<rewrite:rewrite url="${initParam.url_faqs}"/>" title="<bean:message key="metanavegacion.faqs"/>"><bean:message key="metanavegacion.faqs"/></a></li>
			<li><a href="<rewrite:rewrite url="${initParam.url_mapa}"/>" title="<bean:message key="metanavegacion.mapa"/>"><bean:message key="metanavegacion.mapa"/></a></li>
			<li><a href="<rewrite:rewrite url="${initParam.url_contacto}"/>" title="<bean:message key="metanavegacion.contacto"/>" id="contacto"><bean:message key="metanavegacion.contacto"/></a></li>
	</ol>
</div>
-->
<!-- FIN METANAVEGACION   -->


<!-- INICIO CABECERA (antes llamado METANAVEGACION)  -->
<header id="cabecera">
<div>
<span class="acceso">
	<span><bean:message key="metanavegacion.cuenta"/></span> 
	<a href="<rewrite:rewrite url="visualizadorcontenidos2/Acceso/Acceso.do" />" title="<bean:message key="metanavegacion.restringida"/>"><bean:message key="metanavegacion.restringida"/></a>
</span> 
<span class="acerca"><a href="<rewrite:rewrite url="${initParam.url_acercaDeAgrega}"/>" title="<bean:message key="metanavegacion.acercade"/>" ><bean:message key="metanavegacion.acercade"/></a></span>
<ul class="metanavegacion">
<!-- Tag para pintar las banderas (o enlaces equivalentes) con los posibles idiomas de la plataforma -->			
<banderas:idiomasBanderasTag urlIdiomasBanderas="${initParam.url_idiomas_banderas}"/>
</ul>
</div>
</header>
<!-- FIN CABECERA (antes llamado METANAVEGACION)  -->