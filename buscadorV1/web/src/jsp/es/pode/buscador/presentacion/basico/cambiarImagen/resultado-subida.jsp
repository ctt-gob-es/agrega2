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

<form method="post" action='<html:rewrite action="/CambiarImagenCU/ResultadoSubidaResultadoSubirImagen"/>'>


	
	<!--		INICIO GLOBO GRIS		-->
	
	<div class="globo_gris uno_b">
	<div class="globo_gris_01">
	<div class="globo_gris_02">
	<div class="globo_gris_03">
				
	<!--		INICIO CAJA DE FORMULARIO		-->

				
				
		<c:if test="${form.resultado==true}">
				
			<div id="formulario" class="ali_i">
				<br/>
					<p><em class="correcto"><bean:message key='cambiar.imagen.correcto'/></em></p>
				<br/>
						
			</div>
		</c:if> 
		<c:if test="${form.resultado == false}">

		<!--  INICIO CAJA DE FORMULARIO   -->
			<div class="caja_dinamica">
				<br/>
				<p><em class="incorrecto"><bean:message key='cambiar.imagen.incorrecto'/></em></p>
				<br/>
			</div>
				
		</c:if>
	<!--		FIN CAJA DE FORMULARIO		-->					
	</div>
	</div>
	</div>
	</div>
	
	<!--		FIN GLOBO GRIS		-->
	<!--		Boton aceptar		-->

	<input class="boton_125 tipo ft_centrada"  type="submit"  value="<bean:message key="odes.aceptar"/>"/>

</form>

<!--		Fin formulario principal		-->


</div>

<!--		Fin de la capa plantilla_contenido		-->


</tiles:put>

<!--		FIN DEL BODY		-->


</tiles:insert> 