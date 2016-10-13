<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html:xhtml/>


<tiles:insert definition="layout-gestor-flujo-con-style">

	<tiles:put name="title" type="string">
		<bean:message key="title.Comun" />
	</tiles:put>
	<tiles:put name="body" type="string">

		<!--************************          Inicio plantilla contenido       ****************************-->
		<div class="plantilla_contenido"><!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
		<jsp:include page="/layout/messages.jsp" flush="true" />

		<h2><bean:message key="gestorFlujo.mostrarOdes.actualizar.ode"/></h2>
		<!--		Inicio del formulario principal		--> <!--		INICIO GLOBO GRIS		-->
			
		<div class="globo_gris uno_b">
		<div class="globo_gris_01">
		<div class="globo_gris_02">
		<div class="globo_gris_03">
		
		<c:if test="${form.resultadoCorrecto == true}">
			<div id="formulario" class="ali_c">
				<br/>
				<p><em class="correcto"><bean:message key="gestorFlujo.actualizar.exito" /></em></p>
				<br/>
			</div>
		<!-- 
			<div class="caja_dinamica">
				<p><em class="correcto"><bean:message key="gestorFlujo.actualizar.exito" /></em></p>
			</div>
		-->
		</c:if> 

		<c:if test="${form.resultadoCorrecto == false}">
		<!-- 
			<div class="error">
				<bean:message key="gestorFlujo.actualizar.error" />
			</div>
		-->
		
			<div class="caja_dinamica">
				<p><em class="incorrecto"><bean:message key="gestorFlujo.actualizar.error" /></em></p>
			</div>
			<br>
			<div class="formu" style="padding-bottom:0;">
				${form.mensaje}
			</div>
		</c:if>

		<!--		FIN CAJA DE FORMULARIO		-->					
		</div>
		</div>
		</div>
		</div>
		<br/>
			
		<!--		Boton aceptar		-->
		<form method="post"	action='<html:rewrite action="/${initParam.url_actualizar_resultado }"/>'>
			<fieldset class="tipo ft_centrada">
			<input class="boton_125" type="submit" value="<bean:message key="gestorFlujo.aceptar"/>" /></fieldset>
		</form>

		<!--		Fin formulario principal		-->


		</div>

		<!-- Fin plantilla CONTENIDO -->
	</tiles:put>

</tiles:insert>
