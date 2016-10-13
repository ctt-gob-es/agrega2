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

<h2><bean:message key="usuarios.contacto.eliminar.cabecera"/></h2>


<!--		Inicio del formulario principal		-->

<form id="eliminarContactoConfirmarEliminacionContactoObtenerAccionForm" action='<html:rewrite action="/EliminarContacto/ConfirmarEliminacionContactoObtenerAccion"/>' method="post"  >

<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
						<input type="hidden" name="id" value="${form.id}"/>
						<input type="hidden" name="usuarioContacto" value="${form.usuarioContacto}"/>	
						<input type="hidden" id="vuelta" name="vuelta" value="${form.vuelta}" />
						<input type="hidden" name="contacto" value="${form.usuarioContacto}"/>
			<div id="formulario" class="ali_c">
			<br />
			 <p><em class="corrector"><bean:message key="usuarios.contacto.eliminar.confirmacion"/></em></p>
			 <p>${form.usuarioContacto}</p>
				<br />
				</div>

				<!--  FIN CAJA DE FORMULARIO   -->
			</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->

<!-- Inicio Botones  -->
<!-- Inicio Botones  -->

<input class="boton_125_de_2 tipo"  type="submit" name="accionAceptar"  value="<bean:message key='usuarios.aceptar'/>" />

<input class="boton_125_de_2_izq tipo"  type="submit" name="accionCancelar" value="<bean:message key='usuarios.cancelar'/>" />
<!-- Fin Botones  -->
<!-- Fin Botones  -->


</form>





</div>

<!-- Fin plantilla contenido  -->
<!-- Fin plantilla contenido  -->





</tiles:put>


</tiles:insert>