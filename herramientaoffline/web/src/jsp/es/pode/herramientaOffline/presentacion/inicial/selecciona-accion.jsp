<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tiles:insert definition="layout-offline">

	<tiles:put name="title" type="string">
		<bean:message key="title.Comun" />
	</tiles:put>

	<tiles:put name="body" type="string">

<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- ATENCION!! RREMPLAZAR ESTA CAPA DE LA  PLANTILLA DE CONTENIDO ("plantilla_contenido") POR LA QUE SE DESEE EN LA PLANTILLA BASE  -->
<!-- Inicio plantilla contenido  -->
<div class="plantilla_contenido">
<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
<jsp:include page="/layout/messages.jsp" flush="true" /> 

<!--  PARA LAS ACTUALIZACIONES   -->

<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" style="margin-top:10px" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
		<!--  INICIO CAJA DE FORMULARIO   -->
			<div class="formulario" style="padding-left:10px">
			<c:if test="${form.hayUpdate}" >
			<a class="titular_03" href="<html:rewrite action="/InicialCU/SeleccionaAccionVerUpdates"/>" id="crear_tarea">
			<strong><bean:message key="configurar.updates"/></strong></a>
			<p class="parrafo_comun" ><bean:message key="modificador.updates"/></p>
			<p class="parrafo_comun" ><strong><bean:message key="modificador.versionRemota"/> ${form.versionRemota}</strong></p>
			</c:if>
			<p class="parrafo_comun" ><strong><bean:message key="modificador.version"/> ${form.version}</strong></p>
			
			</div>
			
			
			
				<!--  FIN CAJA DE FORMULARIO   -->
			</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->


<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" style="margin-top:10px">
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div class="formulario" style="padding-left:10px">
			
			<a href="<html:rewrite action="/InicialCU/SeleccionaAccionCarpetaPersonal"/>" class="titular_03" id="crear_ode">
			<strong><bean:message key="carpetapersonal.titulo"/></strong></a>
			<p class="parrafo_comun" ><bean:message key="carpetapersonal.mensaje"/></p></div>
				<!--  FIN CAJA DE FORMULARIO   -->
			</div>

		</div>
	</div>
</div>

<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" style="margin-top:10px" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">

			<div class="globo_gris_03">
		<!--  INICIO CAJA DE FORMULARIO   -->
			<div class="formulario" style="padding-left:10px"><a class="titular_03" href="/${initParam.url_modificador }?urlCerrar=<html:rewrite action="InicialCU/InicialCU"/>" id="crear_tarea">
			<strong><bean:message key="modificador.titulo"/></strong></a>
			<p class="parrafo_comun" ><bean:message key="modificador.mensaje"/></p></div>
				<!--  FIN CAJA DE FORMULARIO   -->
			</div>
		</div>
	</div>
</div>


<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->


<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" style="margin-top:10px" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
		<!--  INICIO CAJA DE FORMULARIO   -->
			<div class="formulario" style="padding-left:10px"><a class="titular_03" href="<html:rewrite action="/InicialCU/SeleccionaAccionConfigurarLdap"/>" id="crear_tarea">
			<strong><bean:message key="configurar.titulo"/></strong></a>
			<p class="parrafo_comun" ><bean:message key="configurar.mensaje"/></p></div>

				<!--  FIN CAJA DE FORMULARIO   -->
			</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->

<!--  INICIO GLOBO GRIS DE SINCRONIZACION   -->
<!--  INICIO GLOBO GRIS   -->
  <div class="globo_gris" style="margin-top:10px" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03"> 
		<!--  INICIO CAJA DE FORMULARIO   -->
 		<div class="formulario" style="padding-left:10px">
			<a class="titular_03" href="<html:rewrite action="/InicialCU/SeleccionaAccionSincronizar"/>" id="crear_tarea">
			<strong><bean:message key="configurar.sincronizar"/></strong></a>
			<p class="parrafo_comun" ><bean:message key="modificador.sincronizar"/></p>
			
			</div>
			
				<!--  FIN CAJA DE FORMULARIO   -->
 		</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->

</div>
<!-- Fin plantilla contenido  -->
<!-- Fin plantilla contenido  -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->

		</div>
		<!-- Fin plantilla CONTENIDO -->
	</tiles:put>

</tiles:insert>

