<%@ include file="/taglib-imports.jspf"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html:xhtml/>
<!-- Inicio MENU PRINCIPAL -->
<!-- Inicio MENU PRINCIPAL -->
<div id="madre_menu_horizontal">
<div id="cabecera_menu">

<tiles:importAttribute/>
<tiles:useAttribute id="selected" name="selected"/>
<tiles:useAttribute id="urlArchivos" name="urlArchivos"/>
<tiles:useAttribute id="urlRecursos" name="urlRecursos"/>
<tiles:useAttribute id="urlOrganizaciones" name="urlOrganizaciones"/>
<tiles:useAttribute id="urlSubmanifiestos" name="urlSubmanifiestos"/>

<tiles:useAttribute id="urlGuardar" name="urlGuardar"/>
<tiles:useAttribute id="urlAsistente" name="urlAsistente"/>

<logic:empty name="urlGuardar">
<bean:define id="urlGuardar" value="${urlGuardar }"/>
</logic:empty>

<logic:empty name="urlAsistente">
<bean:define id="urlAsistente" value="${urlAsistente }"/>
</logic:empty>

<!-- Inicio mensaje flotante -->
<c:if test="${empaquetadorSession.asistenteAyuda==true}">
	<!--<logic:equal name="selected" value="archivos">
		<a href="<html:rewrite action="/GestorBasico/GestorBasicoConsultarAsistente"/>" class="flotante_mensaje" style="font-size:11px !important">
			<bean:message key="emp.ver.consulta"/>
		</a>
	</logic:equal>
	<logic:equal name="selected" value="organizaciones">
		<a href="<html:rewrite action="/GestorElementos/GestorElementosConsultarAsistente"/>" class="flotante_mensaje" style="font-size:11px !important">
			<bean:message key="emp.ver.consulta"/>
		</a>
	</logic:equal>
	Probando cu nuevo -->
	<a href="<html:rewrite action="/ConsultarAsistente/ConsultarAsistente"/>?returnURL=${urlAsistente }" class="flotante_mensaje" style="font-size:11px !important">
	   <bean:message key="emp.ver.consulta"/>
	</a>
	

</c:if>
<!-- FIn mensaje flotante -->


<div id="menu_principal0">
<div id="menu_principal">
<div id="menu_principal2">

<ul>
<logic:equal name="selected" value="archivos">
	<li id="archivos"><div><span><span><bean:message key="emp.avanzado.menu.archivos"/></span></span></div></li>
</logic:equal>
<logic:notEqual name="selected" value="archivos">
	<li id="archivos"><a href="<tiles:getAsString name="urlArchivos"/>"><span><span><bean:message key="emp.avanzado.menu.archivos"/></span></span></a></li>
</logic:notEqual>
<logic:equal name="selected" value="recursos">
	<li id="recursos"><div><span><span><bean:message key="emp.avanzado.menu.recursos"/></span></span></div></li>
</logic:equal>
<logic:notEqual name="selected" value="recursos">
	<li id="recursos"><a href="<tiles:getAsString name="urlRecursos"/>"><span><span><bean:message key="emp.avanzado.menu.recursos"/></span></span></a></li>
</logic:notEqual>
<logic:equal name="selected" value="organizaciones">
	<li id="organizaciones"><div><span><span><bean:message key="emp.avanzado.menu.organizaciones"/></span></span></div></li>
</logic:equal>
<logic:notEqual name="selected" value="organizaciones">
	<li id="organizaciones"><a href="<tiles:getAsString name="urlOrganizaciones"/>"><span><span><bean:message key="emp.avanzado.menu.organizaciones"/></span></span></a></li>
</logic:notEqual>
<logic:equal name="selected" value="submanifiestos">
	<li id="submanifiestos"><div><span><span><bean:message key="emp.avanzado.menu.submanifiestos"/></span></span></div></li>
</logic:equal>
<logic:notEqual name="selected" value="submanifiestos">
	<li id="submanifiestos"><a href="<tiles:getAsString name="urlSubmanifiestos"/>"><span><span><bean:message key="emp.avanzado.menu.submanifiestos"/></span></span></a></li>
</logic:notEqual>
</ul>
</div>
</div>
</div>
</div>
</div>
<!-- Fin MENU PRINCIPAL -->
<!-- Fin MENU PRINCIPAL -->
