<%@ include file="/taglib-imports.jspf"%>
<%@ taglib uri="/WEB-INF/tags/menuComunidad.tld" prefix="menuComunidad"%>
<html:xhtml/>
<tiles:importAttribute name="MENU_CABECERA" scope="page"/>
<logic:empty name="MENU_CABECERA">
<div id="madre_barra">
<div class="fondolat"></div>
</div>
</logic:empty>
<logic:notEmpty name="MENU_CABECERA">
<!-- Inicio MENU PRINCIPAL -->
<!-- Inicio MENU PRINCIPAL -->
<div id="madre_menu_horizontal">

<div id="cabecera_menu">
<div id="menu_principal0">
<div id="menu_principal">
<div id="menu_principal2">
<bean:define id="textoComunidad"><bean:message key="menu.cabecera.comunidad"/></bean:define>
<ul>
<logic:iterate name="MENU_CABECERA" id="menuItem">
<c:choose>
	<c:when test="${menuItem.id != textoComunidad }">
	<li id="<bean:write name="menuItem" property="id"/>">
		<logic:equal name="menuItem" property="selected" value="false">
			<a href="<bean:write name="menuItem" property="link"/>" <logic:equal name="menuItem" property="link" value="#">onclick="alert('Opción no disponible en esta versión');"</logic:equal> <logic:notEqual name="menuItem" property="target" value="">target="<bean:write name="menuItem" property="target"/>"</logic:notEqual>>
		</logic:equal>
		<logic:equal name="menuItem" property="selected" value="true">
			<div>
		</logic:equal>
			<span><span><bean:write name="menuItem" property="i18nKey"/></span></span>
		<logic:equal name="menuItem" property="selected" value="false">
			</a>
		</logic:equal>
		<logic:equal name="menuItem" property="selected" value="true">
			</div>
		</logic:equal>
	</li>
	</c:when>
	<c:otherwise>

		<c:if test="${menuComunidad:habilitadoPerfilPublico() }">
		<li id="<bean:write name="menuItem" property="id"/>">
		<logic:equal name="menuItem" property="selected" value="false">
			<a href="<bean:write name="menuItem" property="link"/>" <logic:equal name="menuItem" property="link" value="#">onclick="alert('Opción no disponible en esta versión');"</logic:equal> <logic:notEqual name="menuItem" property="target" value="">target="<bean:write name="menuItem" property="target"/>"</logic:notEqual>>
		</logic:equal>
		<logic:equal name="menuItem" property="selected" value="true">
			<div>
		</logic:equal>
			<span><span><bean:write name="menuItem" property="i18nKey"/></span></span>
		<logic:equal name="menuItem" property="selected" value="false">
			</a>
		</logic:equal>
		<logic:equal name="menuItem" property="selected" value="true">
			</div>
		</logic:equal>
	</li>
	</c:if>
	</c:otherwise>

</c:choose>
</logic:iterate>
	
</ul>
</div>
</div>
</div>
</div>
</div>
<!-- Fin MENU PRINCIPAL -->
<!-- Fin MENU PRINCIPAL -->
</logic:notEmpty>
