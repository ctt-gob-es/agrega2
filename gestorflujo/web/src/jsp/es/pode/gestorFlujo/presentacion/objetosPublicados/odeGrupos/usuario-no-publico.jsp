<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<html:xhtml/>
<tiles:insert definition="layout-gestor-flujo-con-style">

	<tiles:put name="title" type="string">
		<bean:message key="title.Comun" />
	</tiles:put>

	<tiles:put name="body" type="string">

		<!-- *****************************   Inicio  Plantilla contenido      ********************************** -->
		<div class="plantilla_contenido"><!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
		<jsp:include page="/layout/messages.jsp" flush="true" />

		<h2><bean:message key="gestorFlujo.mostrarOdes.enviarAGrupo"/></h2>

	
		<!--  INICIO GLOBO GRIS   -->
		<div class="globo_gris">
		<div class="globo_gris_01">
		<div class="globo_gris_02">
		<div class="globo_gris_03"><!--  INICIO CAJA DE FORMULARIO   -->
		<div id="formulario"><!--  INICIO GLOBO Blanco   --> <!--  INICIO GLOBO Blanco   -->
		
		<p class="parrafo_comun"><bean:message	key="gestorFlujo.envioGrupo.noUsuarioPublico" /></p>
		
		<br class="oculto" />
			
		</div>
<!--  FIN CAJA DE FORMULARIO   -->

		</div>
		</div>
		</div>
		</div>

		<!--  FIN GLOBO GRIS   --> <!--  FIN GLOBO GRIS   --> <!-- Inicio Botones  -->
		<form
			action="<html:rewrite action="${initParam.url_objetosPublicados}"/>"
			method="post"><input class="boton_125_de_2_izq tipo"
			type="submit"
			value="<bean:message key="gestorFlujo.cancelar"/>" /></form>
		<!-- Fin Botones  --></div>
		<!-- <div class="plantilla_contenido"> -->
	</tiles:put>

</tiles:insert>
