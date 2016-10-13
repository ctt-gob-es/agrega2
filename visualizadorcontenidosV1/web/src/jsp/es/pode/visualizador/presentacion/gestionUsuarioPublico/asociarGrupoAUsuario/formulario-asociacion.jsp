<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tags/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html:xhtml/> 
<tiles:insert definition="layout-gestor-flujo-con-style">
<tiles:put name="title" type="string">
	<bean:message key="title.Comun"/>
</tiles:put>
<tiles:put name="body" type="string">
<%@ include file="/taglib-imports.jspf" %>

<div class="plantilla_contenido">


<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
<jsp:include page="/layout/messages.jsp" flush="true" />

<h2><bean:message key="grupo.asociar.usuario.solicitud.cabecera"/></h2>


<!--		Inicio del formulario principal		-->

<form id="asociarGrupoAUsuarioAsociarGrupoAUsuarioVolverForm" action='<html:rewrite action="/AsociarGrupoAUsuario/FormularioAsociacionVolver"/>' method="post"  >

	<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
					
					
					
					
					<div id="formulario" class="ali_c">
					
					<br />
			 			<p><bean:message key="grupo.asociar.usuario.solicitud.confirmacion"/></p>
			 			<c:forEach items="${form.gruposAsociar}" var="grupo">
						
						<p>${grupo.grupo}</p>
						</c:forEach>
				<br />
				</div>

				<!--  FIN CAJA DE FORMULARIO   -->		
						
						
						
				</div>
			</div>
		</div>
	</div>
	
	<!--		FIN GLOBO GRIS		-->



	<!-- Inicio Botones  -->

	<input class="boton_125 tipo ft_centrada"  type="submit"  value="<bean:message key='usuarios.aceptar'/>" />
	
</form>




</div>

<!--		Fin de la capa plantilla_contenido		-->




</tiles:put>


</tiles:insert>