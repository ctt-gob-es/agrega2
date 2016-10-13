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

		<h2><bean:message key="gestorFlujo.proponer.proponer"/></h2>

	
		<form method="post"
			action="<html:rewrite action="ProponerCatalogacionCU/SelectorModoPublicacionSubmit"/>"><!--  INICIO GLOBO GRIS   -->
			<!--  INICIO GLOBO GRIS   -->
			<div class="globo_gris">
			<div class="globo_gris_01">
			<div class="globo_gris_02">
			<div class="globo_gris_03"><!--  INICIO CAJA DE FORMULARIO   -->
			<div id="formulario"><!--  INICIO GLOBO Blanco   --> <!--  INICIO GLOBO Blanco   -->
	
				<input type="hidden" name="idODE" value="${form.idODE}"/>
				<input type="hidden" name="idUsuario" value="${form.idUsuario}"/>
				<input type="hidden" name="titulo" value="${form.titulo}"/>
				<input type="hidden" name="mostrarAviso" value="${form.mostrarAviso}"/>
								
				<p class="parrafo_comun">
					<bean:message key="gestorFlujo.aviso.ode.versionandose1" />
					<!-- <br><bean:message key="gestorFlujo.aviso.ode.versionandose4" />
					-->
				</p>
				
				<div class="fila_de_tabla" title="<bean:message key="gestorFlujo.aviso.ode.versionandose2.explicacion" />">
					<div class="text" >
						<input type="radio" name="solicitarNuevaVersion" class="boton_radio" id="a1" value="true" checked="checked" title="<bean:message key="gestorFlujo.aviso.ode.versionandose2.explicacion" />" />
						<label for="a1" class="alineada" title="<bean:message key="gestorFlujo.aviso.ode.versionandose2.explicacion" />"><bean:message key="gestorFlujo.aviso.ode.versionandose2" /></label>
					</div>
					<div class="linea_separadora"></div>
					<br class="oculto" />
				</div>
				<div class="fila_de_tabla" title="<bean:message key="gestorFlujo.aviso.ode.versionandose3.explicacion" />">
					<div class="text">
						<input type="radio" name="solicitarNuevaVersion" class="boton_radio" id="a2" value="false" title="<bean:message key="gestorFlujo.aviso.ode.versionandose3.explicacion" />" />
						<label for="a2" class="alineada2" title="<bean:message key="gestorFlujo.aviso.ode.versionandose3.explicacion" />"><bean:message key="gestorFlujo.aviso.ode.versionandose3" /></label>
					</div>
					<div class="linea_separadora"></div>
					<br class="oculto" />
				</div>
	
			</div>
	<!--  FIN CAJA DE FORMULARIO   -->
	
			</div>
			</div>
			</div>
			</div>
		
			<fieldset class="tipo">
			<input class="boton_125_de_2" type="submit" name="action" value="<bean:message key='gestorFlujo.aceptar'/>" />
			<input class="boton_125_de_2_izq" type="submit" name="action" value="<bean:message key='gestorFlujo.cancelar'/>" />
			</fieldset>
		
		</form>
		
		<!-- Fin Botones  --></div>
		<!-- <div class="plantilla_contenido"> -->
	</tiles:put>

</tiles:insert>
