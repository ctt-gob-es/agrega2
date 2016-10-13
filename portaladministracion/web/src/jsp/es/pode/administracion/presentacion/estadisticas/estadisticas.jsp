<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<%@ include file="/taglib-imports.jspf" %>


<tiles:insert definition="layout-administrador">

	<tiles:put name="title" type="string">
		<bean:message key="title.Comun"/>
	</tiles:put>
	<tiles:put name="body" type="string">
		<div class="plantilla_contenido" id="po_obj">
		<jsp:include page="/layout/messages.jsp" flush="true" />
			<h2><bean:message key="estadisticas.title" /></h2>
		    <%@ include file="/es/pode/administracion/presentacion/estadisticas/estadisticas-javascript.jspf" %>
			<%@ include file="/es/pode/administracion/presentacion/estadisticas/estadisticas-vars.jspf" %>
			<%@ include file="/es/pode/administracion/presentacion/estadisticas/estadisticas-cambiar-fechas.jspf" %>
		</div>
		
	</tiles:put>
</tiles:insert>