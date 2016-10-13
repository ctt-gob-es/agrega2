<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@ taglib uri="/WEB-INF/tags/redireccionRoles.tld" prefix="redireccion" %>
<%@ taglib uri="/WEB-INF/tags/imagenPortada.tld" prefix="portada" %>
<%@ taglib uri="/WEB-INF/tags/flash.tld" prefix="flash" %>
<%@ taglib prefix="agrega" uri="/WEB-INF/tags/agregaProperties.tld" %>


<tiles:insert definition="layout-administrador-portada">

<tiles:put name="title" type="string">
      <bean:message key="title.Comun"/>
</tiles:put>


	<tiles:put name="body" type="string">
	
		<%@ include file="/taglib-imports.jspf" %>
		
		
		
		
		<!--		Inicio plantilla contenido		-->
		<div class="plantilla_contenido">
		<redireccion:redireccionRoles/>
		
		<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
		<jsp:include page="/layout/messages.jsp" flush="true" />
		
		
		<flash:flash/>
			
		</div>
		
		<!--		Fin de la capa plantilla_contenido		-->
	
	
	</tiles:put>


</tiles:insert>