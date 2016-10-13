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

<h2><bean:message key="odes.desasociarOdeGrupo.cabecera"/></h2>


<!--		Inicio del formulario principal		-->

<form id="desasociarOdeDeGrupoConfirmarDesasociacionObtenerAccionForm" action='<html:rewrite action="/DesasociarOdeDeGrupo/ConfirmarDesasociacionObtenerAccion"/>' method="post"  >

	<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
					
					
					<input type="hidden" name="id_mec" value="${form.id_mec}"/>
						<input type="hidden" name="titulo" value="${form.titulo}"/>	
						<input type="hidden" name="nombre" value="${form.nombre}"/>	
					
					<div id="formulario" class="ali_c">
					
					<br />
					<p><em class="corrector"><bean:message key="odes.desasociarOdeGrupo.confirmacion"/></em></p>
						<p>${form.nombre}</p>
						<p>${form.titulo}</p>
				<br />
				</div>

				<!--  FIN CAJA DE FORMULARIO   -->		
						
						
						
				</div>
			</div>
		</div>
	</div>
	
	<!--		FIN GLOBO GRIS		-->



	<!-- Inicio Botones  -->

	<input class="boton_125_de_2 tipo"  type="submit" name="accionAceptar"  value="<bean:message key='usuarios.aceptar'/>" />
	<input class="boton_125_de_2_izq tipo"  type="submit" name="accionCancelar" value="<bean:message key='usuarios.cancelar'/>" />
	
</form>



</div>

<!--		Fin de la capa plantilla_contenido		-->




</tiles:put>


</tiles:insert>