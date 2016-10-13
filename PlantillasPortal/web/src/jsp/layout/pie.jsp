<%@ include file="/taglib-imports.jspf"%>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<%@ taglib uri="/WEB-INF/tags/serverProperties.tld" prefix="server" %>
<%@ taglib uri="/WEB-INF/tags/logos.tld" prefix="logos" %>
<%@ taglib prefix="agrega" uri="/WEB-INF/tags/agregaProperties.tld" %>


<%@ page import="es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils"%>

<!-- ASIDE -->
<aside id="patros_con_ccaa">
<logos:renderLogoSet htmlFile="uploads/logos/logos-pie_actualizado.html"/>
</aside>
<!-- FIN ASIDE -->

<!-- FOOTER -->
<footer id="pie_pagina">
	<ul>
		<li><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/${initParam.url_politicaPrivacidad}" id="politica" title="<bean:message key="pie.politicaPrivacidad"/>"><bean:message key="pie.politicaPrivacidad"/></a></li>
		<li><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/${initParam.url_informacionLegal}" id="info_legal" title="<bean:message key="pie.informacionLegal"/>" ><bean:message key="pie.informacionLegal"/></a></li>
		<li><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/${initParam.url_accesibilidad}" title="<bean:message key="metanavegacion.accesibilidad"/>" id="registro"><bean:message key="metanavegacion.accesibilidad"/></a></li>
		<li><a href="<rewrite:rewrite url="${initParam.url_contacto}"/>" title="<bean:message key="metanavegacion.contacto"/>" id="contacto"><bean:message key="metanavegacion.contacto"/></a></li>
		<%@ include file="/layout/pie-ayuda.jsp" %>
		<li><a href="<rewrite:rewrite url="${initParam.url_mapa}"/>" title="<bean:message key="metanavegacion.mapa"/>"><bean:message key="metanavegacion.mapa"/></a></li>
		<li><a href="http://<agrega:agregaProperties property="host"/><agrega:agregaProperties property="subdominio"/>/visualizadorcontenidos2/AgregadorFeedsCU/AgregadorFeedsCU.do" title="RSS">RSS</a></li>
	</ul>
</footer>
<!-- FIN FOOTER -->

