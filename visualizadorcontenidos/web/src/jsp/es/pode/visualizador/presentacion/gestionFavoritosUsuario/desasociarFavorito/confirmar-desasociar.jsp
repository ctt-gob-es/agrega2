<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

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


<h2><bean:message key="odes.desasociarOde.cabecera"/></h2>


<!--		Inicio del formulario principal		-->


<form id="desasociarFavoritoConfirmarDesasociarAceptarForm" action='<html:rewrite action="/DesasociarFavorito/ConfirmarDesasociarAceptar"/>' method="post"  >

	<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
					
					
					<input type="hidden" name="id_mec" value="${form.id_mec}"/>
					<input type="hidden" name="titulo" value="${form.titulo}"/>	
					
					<div id="formulario" class="ali_c">
					<br />
			 			<p><em class="corrector"><bean:message key="odes.desasociarOde.confirmacion"/></em></p>
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

	<input class="boton_125_de_2 tipo"  type="submit"  value="<bean:message key='usuarios.aceptar'/>" />
	
</form>


<!--		Formulario secundario y boton cancelar		-->

<form id="desasociarFavoritoConfirmarDesasociarCancelarForm" action='<html:rewrite action="/DesasociarFavorito/ConfirmarDesasociarCancelar"/>' method="post">
	<input class="boton_125_de_2_izq tipo"  type="submit"  value="<bean:message key='usuarios.cancelar'/>" />
</form>

</div>

<!--		Fin de la capa plantilla_contenido		-->




</tiles:put>


</tiles:insert>
