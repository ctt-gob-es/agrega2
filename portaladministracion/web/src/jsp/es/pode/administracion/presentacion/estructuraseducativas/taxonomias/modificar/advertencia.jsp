<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tiles:insert definition="layout-administrador">
<tiles:put name="title" type="string">
	<bean:message key="title.Comun"/>
</tiles:put>
<tiles:put name="body" type="string">
<%@ include file="/taglib-imports.jspf" %>

<!--************************          Inicio plantilla contenido       ****************************-->
<div class="plantilla_contenido">

<jsp:include page="/layout/messages.jsp" flush="true" />

 <h2> <bean:message key="estructuras.arboles.crear.titulo"/> </h2>
 
<form id="advertenciaSubmit" 
	action="<html:rewrite action="/ModificarTaxonomias/AdvertenciaSubmit"/>" method="post" >


	<html:hidden name="form" property="tipo" />
	<html:hidden name="form" property="identificadorVdex" />
	<html:hidden name="form" property="actualizarVigente" />

<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div id="formulario" >
			<c:if test="${form.actualizarVigente}" >	
				<p class="parrafo_comun" ><strong><b><bean:message key="estructuras.taxonomias.modi.vigente.advertencia"/></b></strong></p>
			</c:if>
				<p class="parrafo_comun" ><bean:message key="estructuras.taxonomias.modi.advertencia" arg0="${form.nombre}"/></p>
				<br class="oculto" />
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
<input class="boton_125_de_2_izq"   type="submit" name="action"  value="<bean:message key="estructuras.cancelar"/>" />
<input class="boton_125_de_2"   type="submit" name="action"  value="<bean:message key="estructuras.aceptar"/>" />
<!-- Fin Botones  -->
<!-- Fin Botones  --> 

</form>


</div><!-- plantilla contenido -->
</tiles:put>
</tiles:insert>