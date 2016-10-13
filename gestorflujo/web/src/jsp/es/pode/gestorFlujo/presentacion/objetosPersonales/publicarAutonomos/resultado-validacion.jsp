<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>

<html:xhtml/>

<tiles:insert definition="layout-gestor-flujo-con-style">

	<tiles:put name="title" type="string">
		<bean:message key="title.Comun" />
	</tiles:put>
	<tiles:put name="body" type="string">

		<!--************************          Inicio plantilla contenido       ****************************-->
		<div class="plantilla_contenido"><!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
			<jsp:include page="/layout/messages.jsp" flush="true" />
			
			<!--		Inicio del formulario principal		--> 
			<form method="post" action='<html:rewrite action="PublicarAutonomosCU/ResultadoValidacionAceptar"/>'>
				<!--		INICIO GLOBO GRIS		-->
		
				<div class="globo_gris">
					<div class="globo_gris_01">
						<div class="globo_gris_02">
							<div class="globo_gris_03">
							<!--		INICIO CAJA DE FORMULARIO		-->
								<div id="formulario" class="ali_c">
									<br/>
									<c:if test="${fn:length(form.mensajes)==0}">
										<p><em class="correcto"><bean:message key="gestorFlujo.publicarAutonomo.exito" /></em></p>		
									</c:if> 
									<c:if test="${fn:length(form.mensajes)!=0}">
										<p><em class="incorrecto"><bean:message key="gestorFlujo.error.validacion" /></em></p>
									</c:if>
									<br/>
								</div>
								<!--		FIN CAJA DE FORMULARIO		-->	
							</div>
						</div>
					</div>
				</div>
				<!--		FIN GLOBO GRIS		-->
				<c:if test="${fn:length(form.mensajes)!=0}">
					<p class="parrafo_comun" id="separacion3">
						<bean:message key="gestorFlujo.error.exportar.acontinuacion"/>
					</p>
					<!--  INICIO GLOBO GRIS   -->
					<div class="globo_gris" >
						<div class="globo_gris_01">
							<div class="globo_gris_02">
								<div class="globo_gris_03">
									<!--  INICIO CAJA DE FORMULARIO   -->
									<div id="formulario_02" >
										<br />
									 	<ul class="lista_validacion">
											<logic:iterate id="errores" collection="${form.mensajes}">
												<li>${errores}</li>								
											</logic:iterate>
										</ul>
										<br/>
									</div>
									<!--  FIN CAJA DE FORMULARIO   -->
								</div>
							</div>
						</div>
					</div>
				</c:if>
				<!--  FIN GLOBO GRIS   -->
	
				<!--		Boton aceptar		-->
<!--   			<fieldset class="tipo ft_centrada">
				<input class="boton_125" type="submit" value="<bean:message key="gestorFlujo.aceptar"/>" /></fieldset> -->
				<bean:define id="aceptarValue"><bean:message key="gestorFlujo.aceptar"/></bean:define>	
				<html:submit styleClass="boton_125 tipo ft_centrada"  value="${aceptarValue}" ></html:submit>
			</form>
	
			<!--		Fin formulario principal		-->

		</div>



		<!-- Fin plantilla CONTENIDO -->
	</tiles:put>

</tiles:insert>
