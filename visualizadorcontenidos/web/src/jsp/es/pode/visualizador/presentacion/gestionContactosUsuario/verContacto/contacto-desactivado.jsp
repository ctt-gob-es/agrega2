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


<form id="errorAplicacion" action='<html:rewrite action="/ListarContactosUsuario/ListarContactosUsuario.do"/>' method="post" >
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
				<div id="formulario" class="ali_c">
				<br />
						<p><bean:message key="verContacto.contacto.desactivado.${form.desactivadoCodigo}" /></p>
                        		<p>${form.contacto}</p>
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
<fieldset class="tipo ft_centrada">
<input class="boton_125"  type="submit"  value="<bean:message key="usuarios.aceptar"/>" />
</fieldset>

<!-- Fin Botones  -->
<!-- Fin Botones  -->
	


	

</form>
</div>
<!-- Fin plantilla contenido  -->
<!-- Fin plantilla contenido  -->



</tiles:put>


</tiles:insert>