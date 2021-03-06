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

<h2><bean:message key="odes.asociarOde.cabecera"/></h2>


<!--		Inicio del formulario principal		-->

<form id="asociarOdeAGrupoFormularioConfirmacionObtenerAccionForm" action='<html:rewrite action="/AsociarOdeAGrupo/FormularioConfirmacionObtenerAccion"/>' method="post"  >

	<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
					
					
					<input type="hidden" name="listaId" value="${form.listaId}"/>	
						<input type="hidden" name="id_mec" value="${form.id_mec}"/>
						<input type="hidden" name="titulo" value="${form.titulo}"/>	
						<input type="hidden" name="idioma" value="${form.idioma}"/>	
						<input type="hidden" id="vuelta" name="vuelta" value="${form.vuelta}"/>
						<input type="hidden" id="nombre" name="nombre" value="${form.nombre}"/>
					
					<div id="formulario" class="ali_c">
					
					<br />
			 			<p><bean:message key="odes.asociarOde.confirmacion"/></p>
						<c:forEach items="${form.grupos}" var="grupo">
						
						<p>${grupo.nombre}</p>
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

	<input class="boton_125_de_2 tipo"  type="submit" name="actionAceptar"  value="<bean:message key='usuarios.aceptar'/>" />
	



<!--		Formulario secundario y boton cancelar		-->


	<input class="boton_125_de_2_izq tipo"  type="submit" name="actionCancelar" value="<bean:message key='usuarios.cancelar'/>" />

	
</form>




</div>

<!--		Fin de la capa plantilla_contenido		-->




</tiles:put>


</tiles:insert>