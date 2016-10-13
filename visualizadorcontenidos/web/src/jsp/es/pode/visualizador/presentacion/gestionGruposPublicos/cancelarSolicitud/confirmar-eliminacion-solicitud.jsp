<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
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

<h2><bean:message key="usuarios.cancelar.solicitud.cabecera"/></h2>


<!--		Inicio del formulario principal		-->

<form id="cancelarSolicitudConfirmarEliminacionSolicitudAceptarForm" action='<html:rewrite action="/CancelarSolicitud/ConfirmarEliminacionSolicitudAceptar"/>' method="post"  >

	<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
					
					
					<input type="hidden" name="id" value="${form.id}"/>
						<input type="hidden" name="grupo" value="${form.grupo}"/>	
						<input type="hidden" name="usuarioSolicitante" value="${form.usuarioSolicitante}"/>
					
					<div id="formulario" class="ali_c">
					
					<br />
						<p><em class="corrector"><bean:message key="usuarios.cancelar.solicitud.confirmacion1"/></em></p>
						
						<p>${form.usuarioSolicitante}</p>
						<p><em class="corrector"><bean:message key="usuarios.cancelar.solicitud.confirmacion2"/></em></p>
						<p>${form.grupo}</p>
						<p><em class="corrector"><bean:message key="usuarios.cancelar.solicitud.confirmacion3"/></em></p>

					<br />
				</div>

				<!--  FIN CAJA DE FORMULARIO   -->		
						
						
						
				</div>
			</div>
		</div>
	</div>
	
	<!--		FIN GLOBO GRIS		-->



	<!-- Inicio Botones  -->

	<input class="boton_125_de_2 tipo"  type="submit"  value="<bean:message key='usuarios.aceptar'/>" />
	
</form>


<!--		Formulario secundario y boton cancelar		-->

<form id="cancelarSolicitudConfirmarEliminacionSolicitudCancelarForm" action='<html:rewrite action="/CancelarSolicitud/ConfirmarEliminacionSolicitudCancelar"/>' method="post">
	<input class="boton_125_de_2_izq tipo"  type="submit"  value="<bean:message key='usuarios.cancelar'/>" />
</form>


</div>

<!--		Fin de la capa plantilla_contenido		-->




</tiles:put>


</tiles:insert>