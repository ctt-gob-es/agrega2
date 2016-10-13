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

		<h2><bean:message key="gestorFlujo.mostrarOdes.publicarAutonomos"/></h2>

	
		<form method="post"
			action="<html:rewrite action="/${initParam.url_publicadosAutonomos_aceptar}" />"><!--  INICIO GLOBO GRIS   -->
		<!--  INICIO GLOBO GRIS   -->
		<div class="globo_gris">
		<div class="globo_gris_01">
		<div class="globo_gris_02">
		<div class="globo_gris_03"><!--  INICIO CAJA DE FORMULARIO   -->
		<div id="formulario"><!--  INICIO GLOBO Blanco   --> <!--  INICIO GLOBO Blanco   -->

		<input type="hidden" name="idODE" value="${form.idODE}"/>
		<input type="hidden" name="idUsuario" value="${form.idUsuario}"/>
		<input type="hidden" name="titulo" value="${form.titulo}"/>
		
		<p class="parrafo_comun"><bean:message	key="gestorFlujo.autopublicados.legal1" /><bean:message	key="gestorFlujo.autopublicados.legal2" /></p>
		<p class="parrafo_comun"><bean:message key="gestorFlujo.autopublicados.legal3" /><bean:message key="gestorFlujo.autopublicados.legal4"/></p>
		<p class="parrafo_comun"><bean:message key="gestorFlujo.autopublicados.legal5" /></p>
		
		<p class="parrafo_comun"><bean:message	key="gestorFlujo.proponer.comentarios" />:</p>
		<div class="fila_de_tabla" style="width:auto;">
		<div class="text">
			<label for="Disclaimer" class="oculto" ><bean:message 	key="gestorFlujo.mostrarOdes.publicarAutonomos" /></label> 
			<textarea name="comentarios" rows="5" cols="40" class="ta_minimo_ancho"
			onfocus="limpiarTexto(this)" onblur="this.style.backgroundColor='#e1e1e1'" 
			id="proponer" title="<bean:message key="gestorFlujo.proponer.introduzca" />">${form.comentarios }</textarea></div></div>
		<div class="fila_de_tabla">
			<div class="text" ><label for="Disclaimer" class="oculto" ><bean:message key="gestorFlujo.heLeido.condiciones" /></label>
			
			<input type="checkbox" name="seleccion" id="Disclaimer" class="boton_radio" /> <span class="vert"><bean:message key="gestorFlujo.heLeido.condiciones" /></span></div>
			<div class="linea_separadora"></div>
			<br class="oculto" />
			
		</div>
		</div>
<!--  FIN CAJA DE FORMULARIO   -->

		</div>
		</div>
		</div>
		</div>

		<!--  FIN GLOBO GRIS   --> <!--  FIN GLOBO GRIS   --> <!-- Inicio Botones  -->
		<!-- Inicio Botones  --> <input class="boton_125_de_2 tipo" type="submit"
			value="<bean:message key="gestorFlujo.aceptar"/>" /></form>
		<form
			action="<html:rewrite action="PublicarAutonomosCU/ProponerPublicacionAutonomaCancelar"/>"
			method="post"><input class="boton_125_de_2_izq tipo"
			type="submit"
			value="<bean:message key="gestorFlujo.cancelar"/>" /></form>
		<!-- Fin Botones  --></div>
		<!-- <div class="plantilla_contenido"> -->
	</tiles:put>

</tiles:insert>
