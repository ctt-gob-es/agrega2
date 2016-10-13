<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<html:xhtml/>
<tiles:insert definition="layout-gestor-flujo-con-style">

	<tiles:put name="title" type="string">
		<bean:message key="title.Comun" />
	</tiles:put>
	<tiles:put name="body" type="string">

		<!--************************          Inicio plantilla contenido       ****************************-->
	<div class="plantilla_contenido">


<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
<jsp:include page="/layout/messages.jsp" flush="true" />


<h2><bean:message key="gestorFlujo.mostrarOdes.eliminar"/></h2>


<!--		Inicio del formulario principal		-->

<form action='<html:rewrite action="/${initParam.url_objetosPersonales}"/>' method="post" >

	<!--		INICIO GLOBO GRIS		-->
	
	<div class="globo_gris" >
		<div class="globo_gris_01">
			<div class="globo_gris_02">
				<div class="globo_gris_03">
					
					
					<!--		INICIO CAJA DE FORMULARIO		-->
					
					<div id="formulario" class="ali_c">
							
						<p><bean:message key="gestorFlujo.eliminar.resultado"/></p>
						<br/>
						<p>${form.titulo} </p>
				
					</div>
					
					<!--		FIN CAJA DE FORMULARIO		-->
					
					
				</div>
			</div>
		</div>
	</div>
	
	<!--		FIN GLOBO GRIS		-->


	<!--		Boton aceptar		-->
	<fieldset class="tipo ft_centrada">
	<input class="boton_125"  type="submit"  value="<bean:message key="gestorFlujo.aceptar"/>" /></fieldset>

</form>

<!--		Fin formulario principal		-->


</div>

	
	
				<!-- Fin plantilla CONTENIDO -->
	</tiles:put>

</tiles:insert>