<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>


<tiles:insert definition="layout-sinlateral">

<tiles:put name="title" type="string">
      <bean:message key="title.Comun"/>
</tiles:put>


<!-- 		INICIO DEL BODY		  -->

<tiles:put name="body" type="string">

<%@ include file="/taglib-imports.jspf" %>


<!--		Inicio plantilla contenido		-->

<div class="plantilla_contenido">


<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
<jsp:include page="/layout/messages.jsp" flush="true" />

<h2><bean:message key='cambiar.imagen.cabecera'/></h2>


<!--		Inicio del formulario principal		-->

<form id="cambiarImagenCUConfirmacionAceptarForm" action='<html:rewrite action="/CambiarImagenCU/ConfirmacionAceptar"/>' method="post"  >

	<!--		INICIO GLOBO GRIS		-->
	
	<div class="globo_gris" >
		<div class="globo_gris_01">
			<div class="globo_gris_02">
				<div class="globo_gris_03">
					
					
					<!--		INICIO CAJA DE FORMULARIO		-->
					
					<div id="formulario" class="ali_c">
					
			<input type="hidden" name="identificadorODE" value="${form.identificadorODE}"/>	
			<input type="hidden" name="extensionFich" value="${form.extensionFich}"/>
			<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>	
						
			<p><bean:message key='cambiar.imagen.confirmacion'/></p>
									
				</div>
			</div>
		</div>
	</div>
	
	<!--		FIN GLOBO GRIS		-->

	<!-- Inicio Botones  -->

	<input class="boton_125_de_2 tipo"  type="submit"  value="<bean:message key='odes.aceptar'/>" />
	
</form>

<!--		Formulario secundario y boton cancelar		-->

<form id="cambiarImagenCUConfirmacionCancelarForm" action='<html:rewrite action="/CambiarImagenCU/ConfirmacionCancelar"/>' method="post">
	<input class="boton_125_de_2_izq tipo"  type="submit"  value="<bean:message key='odes.cancelar'/>" />
</form>

</div>

<!--		Fin de la capa plantilla_contenido		-->

</tiles:put>

<!--		FIN DEL BODY		-->

</tiles:insert>