<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html:xhtml/>

<tiles:importAttribute/>
<tiles:useAttribute id="selected" name="selected"/>
<tiles:useAttribute id="urlArchivos" name="urlArchivos"/>
<tiles:useAttribute id="urlEstructura" name="urlEstructura"/>
<tiles:useAttribute id="urlAsistente" name="urlAsistente"/>

<!-- Inicio MENU PRINCIPAL -->
<!-- Inicio MENU PRINCIPAL -->
<div id="madre_menu_horizontal">
<div id="cabecera_menu" style="text-align:center !important">

<!-- Inicio mensaje flotante -->
<logic:empty name="urlAsistente">
	<bean:define id="urlAsistente" value="${urlAsistente }"/>
</logic:empty>

<!-- Inicio mensaje flotante -->
<c:if test="${empaquetadorSession.asistenteAyuda==true}">
	<a href="<html:rewrite action="/ConsultarAsistente/ConsultarAsistente"/>?returnURL=${urlAsistente }" class="flotante_mensaje" style="font-size:11px !important">
	   <bean:message key="emp.ver.consulta"/>
	</a>
</c:if>
<!-- FIn mensaje flotante -->

<div id="menu_principal0">
<div id="menu_principal">
<div id="menu_principal3">
<ul  class="basic">
<c:choose>
<c:when test="${selected == 'estructura' }">
	<li id="portada"><div><span ><span ><bean:message key="emp.basico.menu.estructura"/></span></span></div></li>
	<li id="arbol" ><a href="${urlArchivos }" ><span ><span ><bean:message key="emp.avanzado.menu.archivos"/></span></span></a></li>
</c:when>
<c:when test="${selected == 'archivos' }">
	<li id="portada"><a href="${urlEstructura }" ><span ><span ><bean:message key="emp.basico.menu.estructura"/></span></span></a></li>
	<li id="arbol" ><div><span ><span ><bean:message key="emp.avanzado.menu.archivos"/></span></span></div></li>
</c:when>
<c:otherwise>
	<li id="portada"><a href="${urlEstructura }" ><span ><span ><bean:message key="emp.basico.menu.estructura"/></span></span></a></li>
	<li id="arbol" ><a href="${urlArchivos }" ><span ><span ><bean:message key="emp.avanzado.menu.archivos"/></span></span></a></li>
</c:otherwise>
</c:choose>
</ul>
</div>
</div>
</div>
</div>
</div>
<!-- Fin MENU PRINCIPAL -->
<!-- Fin MENU PRINCIPAL -->