<%@ include file="/taglib-imports.jspf" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<tiles:insert definition="layout-principal">



<tiles:put name="title" type="string">
	<bean:message key="title.Comun"/> - ${fn:escapeXml(form.tituloOde)}
</tiles:put>



<tiles:put name="body" type="string">
<!-- CONTENIDO -->
					
<!-- Panel contenidos!! -->
<div id="capa_contenidos" name="capa_contenidos">
<div id="contenido" name="contenido" style="margin-left: 0pt; min-width: 520px; min-height: 440px; height: 223px; width: 530px;">


<!-- Inicio plantilla contenido  -->
<!-- Inicio plantilla contenido  -->
<div class="plantilla_contenido" id="contenido_if" style="position:relative;">
<div id="contenido_externo">

<jsp:include page="/layout/messages.jsp" flush="true" />


<h2 align="left">Exportar - ${fn:escapeXml(form.tituloOde) }</h2>

<form method="POST" action="<html:rewrite action="/Exportar/ExportarSubmit"/>" >
					
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_blanco conmargen" >
<div class="globo_blanco uno_b" >
	<div class="globo_blanco_01">
		<div class="globo_blanco_02">
			<div class="globo_blanco_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
										<div id="formulario_02" align="left">
										
			<logic:iterate collection="${visualizadorSession.formatosExportacion}" id="formato" indexId="i">
			<c:if test="${ i < 3}">
											 <div class="fila_de_tabla">
				  								<div class="text ft_lateral" >
				  									<html:radio name="form" property="formato" styleClass="boton_radio" styleId="formato-${formato}"  value="${formato}" />
				  									<label for="formato-${formato}" class="alineada" title="<bean:message key="descargar.formato.${formato}"/>"><bean:message key="descargar.formato.${formato}"/></label>
				  								</div>
												<div class="linea_separadora"></div>
												<br class="oculto" />
											</div>
			</c:if>
			</logic:iterate>
			
			<c:if test="${form.mostrarDescargaRecursoUnico=='true'}">
											<div class="fila_de_tabla">
				  								<div class="text ft_lateral" >
				  									<html:radio name="form" property="formato" styleClass="boton_radio" styleId="descargar.formato.RECURSO_UNICO" value="descargar.formato.RECURSO_UNICO" />
				  									<label for="descargar.formato.RECURSO_UNICO" class="alineada" title="<bean:message key="descargar.formato.RECURSO_UNICO"/>"><bean:message key="descargar.formato.RECURSO_UNICO"/></label>
				  								</div>
												<div class="linea_separadora"></div>
												<br class="oculto" />
											</div>
			</c:if>		
										
										</div>
										<!--  FIN CAJA DE FORMULARIO   -->
			</div>
		</div>
	</div>
</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->
				
						<p align="left" class="parrafo_comun" id="separacion3"><bean:message key="descargar.mensaje.masformtatos"/></p>
						
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_blanco" >
	<div class="globo_blanco_01">
		<div class="globo_blanco_02">
			<div class="globo_blanco_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
										<div id="formulario" align="left">
										
			<logic:iterate collection="${visualizadorSession.formatosExportacion}" id="formato" indexId="i">
			<c:if test="${ i >= 3 }">
											 <div class="fila_de_tabla">
				  								<div class="text ft_lateral" >
				  									<html:radio name="form" property="formato" styleClass="boton_radio" styleId="formato-${formato}"  value="${formato}" />
				  									<label for="formato-${formato}" class="alineada" title="<bean:message key="descargar.formato.${formato}"/>"><bean:message key="descargar.formato.${formato}"/></label>
				  								</div>
												<div class="linea_separadora"></div>
												<br class="oculto" />
											</div>
			</c:if>
			</logic:iterate>
										</div>
			<!--  FIN CAJA DE FORMULARIO   -->
			</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->
			
<c:if test="${empty form.textoLicencia}">
		<input type="hidden" id="tieneLicencia" name="tieneLicencia" value="false" />
</c:if>
<c:if test="${!empty form.textoLicencia}">
		<input type="hidden" id="tieneLicencia" name="tieneLicencia" value="true" />
		<p class="parrafo_comun" id="separacion3" align="left"> <bean:message key="descargar.mensaje.condicioneslegales"/></p>
		
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_blanco conmargen" >
	<div class="globo_blanco_01">
		<div class="globo_blanco_02">
			<div class="globo_blanco_03">
				<!--  INICIO CAJA DE FORMULARIO   -->
						<div class="formu" align="left" >
						 <div class="fila_de_tabla">
			  					<div class="text ft_lateral" >
			  						<textarea rows="10" cols="40" class="ta_minimo_ancho" style="width:70%" id="textoLicencia" title="<bean:message key="descargar.licencia.titulo"/>" readonly="readonly">${form.textoLicencia}</textarea><br />
									<input type="checkbox" class="boton_radio" style="margin-top:2px !important;" id="aceptaLicencia"  name="aceptaLicencia" value="aceptaLicencia"/><label for="aceptaLicencia" class="alineada5"><bean:message key="descargar.licencia.leido"/></label>
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
</c:if>
<input type="hidden" name="identificador" value="${form.identificador}" />
						<fieldset class="tipo ft_centrada">	
							<input class="boton_125"  type="submit" name="action" value="<bean:message key="descargar.action.descargar"/>" alt="<bean:message key="descargar.action.descargar.alt"/>"/>
						</fieldset>
						<div class="linea_separadora"></div>
						<br class="oculto" />
</form>



			<div class="linea_separadora">
			<br class="oculto">
</div>
</div>


</div>
</div>

</div>

<script type="text/javascript">
//<![CDATA[
	$("#contenido_central_largo").contenido({
		ancho_menu:430,
		inicio_menu_desplegado:'false',
		boton_menu:'#enlace_menu',
		contenedor_menu:'#panel_menu',
		contenedor_frame:'#contenido'
		}
	);
//]]>
</script>

</tiles:put>
</tiles:insert>
