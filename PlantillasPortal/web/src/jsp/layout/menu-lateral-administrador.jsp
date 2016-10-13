<%@ include file="/taglib-imports.jspf"%>
<%@ taglib uri="/WEB-INF/tags/menuComunidad.tld" prefix="menuComunidad"%>
<html:xhtml/>
<!-- INICIO LATERAL IZQUIERDO  -->
<div id="lateral">
<!-- INICIO NAVEGACIÓN VERTICAL -->
<!-- INICIO NAVEGACIÓN VERTICAL -->
<div id="menu_vertical">

<br class="oculto" />
<strong class="oculto"><bean:message key="menu.lateral.generico.menuVertical" />:</strong><br class="oculto" /><br class="oculto" />
<em><bean:message key="menu.lateral.administrador.portal" /></em>
<br class="oculto" />
<ul class="lista_admin">
<tiles:importAttribute name="MENU_LATERAL_CONTENIDOS" scope="page"/>
<tiles:importAttribute name="MENU_LATERAL_PLATAFORMA" scope="page"/>
<tiles:importAttribute name="MENU_LATERAL_PORTAL" scope="page"/>

<logic:iterate name="MENU_LATERAL_PORTAL" id="menuItem">
	<li>
		<logic:equal name="menuItem" property="selected" value="false">
			<a href="<bean:write name="menuItem" property="link"/>" <logic:equal name="menuItem" property="link" value="#">onclick="alert('Opción no disponible en esta versión');"</logic:equal> <logic:notEqual name="menuItem" property="target" value="">target="<bean:write name="menuItem" property="target"/>"</logic:notEqual>>
		</logic:equal>
		<logic:equal name="menuItem" property="selected" value="true">
			<div>
		</logic:equal>
			<span><bean:write name="menuItem" property="i18nKey"/></span>
		<logic:equal name="menuItem" property="selected" value="false">
			</a>
		</logic:equal>
		<logic:equal name="menuItem" property="selected" value="true">
			</div>
		</logic:equal>
	</li>
</logic:iterate>

</ul>
<br class="oculto" />

<em><bean:message key="menu.lateral.administrador.plataforma" /></em>
<br class="oculto" />
<bean:define id="textoItinerario"><bean:message key="menu.vertical.administrador.itinerarios"/></bean:define>
<ul class="lista_admin">
	<logic:iterate name="MENU_LATERAL_PLATAFORMA" id="menuItem">
	<c:choose>
	<c:when test="${menuItem.id != textoItinerario }">
		<li>
			<logic:equal name="menuItem" property="selected" value="false">
				<a href="<bean:write name="menuItem" property="link"/>" <logic:equal name="menuItem" property="link" value="#">onclick="alert('Opción no disponible en esta versión');"</logic:equal> <logic:notEqual name="menuItem" property="target" value="">target="<bean:write name="menuItem" property="target"/>"</logic:notEqual>>
			</logic:equal>
			<logic:equal name="menuItem" property="selected" value="true">
				<div>
			</logic:equal>
				<span><bean:write name="menuItem" property="i18nKey"/></span>
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
			<li>
			<logic:equal name="menuItem" property="selected" value="false">
				<a href="<bean:write name="menuItem" property="link"/>" <logic:equal name="menuItem" property="link" value="#">onclick="alert('Opción no disponible en esta versión');"</logic:equal> <logic:notEqual name="menuItem" property="target" value="">target="<bean:write name="menuItem" property="target"/>"</logic:notEqual>>
			</logic:equal>
			<logic:equal name="menuItem" property="selected" value="true">
				<div>
			</logic:equal>
				<span><bean:write name="menuItem" property="i18nKey"/></span>
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
<br class="oculto" />
<em><bean:message key="menu.lateral.administrador.contenidos" /></em>
<br class="oculto" />
<ul class="lista_admin">
<logic:iterate name="MENU_LATERAL_CONTENIDOS" id="menuItem">
	<li>
		<logic:equal name="menuItem" property="selected" value="false">
			<a href="<bean:write name="menuItem" property="link"/>" <logic:equal name="menuItem" property="link" value="#">onclick="alert('Opción no disponible en esta versión');"</logic:equal> <logic:notEqual name="menuItem" property="target" value="">target="<bean:write name="menuItem" property="target"/>"</logic:notEqual>>
		</logic:equal>
		<logic:equal name="menuItem" property="selected" value="true">
			<div>
		</logic:equal>
			<span><bean:write name="menuItem" property="i18nKey"/></span>
		<logic:equal name="menuItem" property="selected" value="false">
			</a>
		</logic:equal>
		<logic:equal name="menuItem" property="selected" value="true">
			</div>
		</logic:equal>
	</li>
</logic:iterate>
</ul>
</div>
<!-- FIN NAVEGACIÓN VERTICAL -->
<!-- FIN NAVEGACIÓN VERTICAL -->
<hr />
</div>
<!-- FIN LATERAL IZQUIERDO -->
<!-- FIN LATERAL IZQUIERDO -->