<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<tiles:insert definition="layout-sinlateral">

    <tiles:put name="title" type="string">
        <bean:message key="title.Comun"/>
    </tiles:put>

    <tiles:put name="body" type="string">
<%@ include file="/taglib-imports.jspf" %>

	<div class="plantilla_contenido">

	
	<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
	<jsp:include page="/layout/messages.jsp" flush="true" />

	<h2><bean:message key='licencia.tipoTitulo'/> ${form.titulo}</h2>
	<form method="post" action="<html:rewrite action="/DescargarODECU/VerTextoLicenciaAceptarLicencia"/>" >

		<!--  INICIO GLOBO GRIS   -->
		<!--  INICIO GLOBO GRIS   -->
		<div class="globo_gris" >
			<div class="globo_gris_01">
				<div class="globo_gris_02">
					<div class="globo_gris_03">
						<!--  INICIO CAJA DE FORMULARIO   -->
						<div id="formulario_02" >
							 <div class="fila_de_tabla">
  								<textarea rows="20" cols="20">
									${form.textoLicencia}
							</textarea>
							</div>	
					
						</div>
						<!--  FIN CAJA DE FORMULARIO   -->
					</div>
				</div>
			</div>
		</div>
		<!--  FIN GLOBO GRIS   -->
		<!--  FIN GLOBO GRIS   -->

		
			<input type="hidden" name="idioma" value="${form.idioma}"/>
			<input type="hidden" name="titulo" value="${form.titulo}"/>
			<input type="hidden" name="localizadorODE" value="${form.localizadorODE}"/>
			<input type="hidden" name="busquedaSimpleavanzada" value="${form.busquedaSimpleAvanzada}"/>
			<input type="hidden" name="identificadorODE" value="${form.identificadorODE}"/>
			<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>
			<input type="hidden" name="mostrarVuelta" value="${form.mostrarVuelta}"/>
			<input class="boton_125_de_2 tipo"  type="submit"  value="<bean:message key='licencia.aceptar'/>" />
	</form>
	<form id="" action='<html:rewrite action="/DescargarODECU/VerTextoLicenciaCancelarLicencia"/>?identificadorODE=${form.identificadorODE}&amp;idioma=${form.idioma}&amp;mostrarVuelta=${form.mostrarVuelta}' method="post">
	<input class="boton_125_de_2_izq tipo"  type="submit"  value="<bean:message key='licencia.cancelar'/>" />
</form>
	
</div>

</tiles:put>
</tiles:insert>
