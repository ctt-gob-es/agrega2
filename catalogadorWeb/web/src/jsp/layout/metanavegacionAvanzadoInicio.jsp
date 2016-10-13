<%@ include file="/taglib-imports.jspf" %>
<%@ taglib uri="/WEB-INF/tags/link.tld" prefix="link" %>
<div id="metanavegacion">
<ol>
		<li class="oculto"><a href="#contenido_central" title="Contenido Principal"><strong>Contenido</strong></a></li>
		<bean:define id="ayuda"><bean:message key="catalogadorAvanzado.Ayuda"/></bean:define>
		<li>
			<link:link attrProperty="HELP_URL" title="${ayuda }" id="li_inicial" target="blank" i18nMessage="catalogadorAvanzado.Ayuda"></link:link>
		</li>
		<li>
			<a href="<html:rewrite action="/CatalogadorAvanzado/CategoriasFormAsistente"/>" title="<bean:message key="catalogadorAvanzado.Asistente.Titulo" />" id="asis">
				<c:choose>
					<c:when test="${catalogadorAvSession.asistente==false}">
						<bean:message key="cabecera.activarAsistente" />
					</c:when>
					<c:otherwise>
						<bean:message key="cabecera.desactivarAsistente" />
					</c:otherwise>
				</c:choose>
			</a>
		</li>
		<li id="titulo_empa"><span><bean:message key="catalogadorAvanzado.Catalogador"/></span></li>
	</ol>
</div>
