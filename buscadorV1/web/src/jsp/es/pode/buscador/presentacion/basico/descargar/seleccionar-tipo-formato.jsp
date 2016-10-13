<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tags/googleAnalytic.tld" prefix="analytic" %>
<bean:define id="sesion" name="<%=es.pode.buscador.presentacion.BuscarSession.SESSION_KEY%>" scope="session"/>
<tiles:insert definition="layout-sinlateral">

    <tiles:put name="title" type="string">
        <bean:message key="title.Comun"/>
    </tiles:put>

    <tiles:put name="body" type="string">
<%@ include file="/taglib-imports.jspf" %>

	<div class="plantilla_contenido">
	<analytic:googleAnalytic />
	
	<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
	<jsp:include page="/layout/messages.jsp" flush="true" />

	<h2><bean:message key="descargar.tipoFormato.titulo"/> ${form.titulo}</h2>
	<form method="get" action="<html:rewrite action="/DescargarODECU/SeleccionarTipoFormatoAceptar"/>" >

		<!--  INICIO GLOBO GRIS   -->
		<!--  INICIO GLOBO GRIS   -->
		<div class="globo_gris" >
			<div class="globo_gris_01">
				<div class="globo_gris_02">
					<div class="globo_gris_03">
						<!--  INICIO CAJA DE FORMULARIO   -->
						<div id="formulario_02" >
							 <div class="fila_de_tabla">
  								<div class="text ft_lateral" >
  									<html:radio property="formato" styleClass="boton_radio" styleId="Descarga01" name="form"  value="descargar.formatos.CONTENIDOS_VALUE" />
  									<label for="Descarga01" class="alineada"><bean:message key="descargar.formatos.CONTENIDOS"/></label>
  								</div>
								<div class="linea_separadora"></div>
								<br class="oculto" />
							</div>	
							
							
							<div class="fila_de_tabla">
  								<div class="text ft_lateral" >
  									<html:radio property="formato" styleClass="boton_radio" styleId="Descarga01" name="form"  value="descargar.formatos.HTML_VALUE" />
  									<label for="Descarga01" class="alineada"><bean:message key="descargar.formatos.HTML"/></label>
  								</div>
								<div class="linea_separadora"></div>
								<br class="oculto" />
							</div>	
							
							<div class="fila_de_tabla">
  								<div class="text ft_lateral" >
  									<html:radio property="formato" styleClass="boton_radio" styleId="Descarga01" name="form"  value="descargar.formatos.METADATOS_PDF_VALUE" />
  									<label for="Descarga01" class="alineada"><bean:message key="descargar.formatos.METADATOS_PDF_VALUE"/></label>
  								</div>
								<div class="linea_separadora"></div>
								<br class="oculto" />
							</div>		
							
							<c:if test="${form.mostrarDescargaRecursoUnico=='true'}">			
								<div class="fila_de_tabla">
	  								<div class="text ft_lateral" >
	  									<html:radio property="formato" styleClass="boton_radio" styleId="Descarga01" name="form"  value="descargar.formatos.RECURSO_UNICO" />
	  									<label for="Descarga01" class="alineada"><bean:message key="descargar.formatos.RECURSO_UNICO"/></label>
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
		<!--  FIN GLOBO GRIS   -->
		<!--  FIN GLOBO GRIS   -->

		<p class="parrafo_comun" id="separacion3"><bean:message key="descargar.formatos.texto"/>:</p>
		
		<!--  INICIO GLOBO GRIS   -->
		<!--  INICIO GLOBO GRIS   -->
		<div class="globo_gris" >
			<div class="globo_gris_01">
				<div class="globo_gris_02">	
					<div class="globo_gris_03">
					<!--  INICIO CAJA DE FORMULARIO   -->
					<div id="formulario" >
					 <div class="fila_de_tabla">
		  				<div class="text ft_lateral" >
		  					<html:radio property="formato" styleClass="boton_radio" styleId="Formato01" name="form"  value="descargar.formatos.SCORM_2004_Sin_Sub_Manifiesto_VALUE" />
		  					<label for="Formato01" class="alineada"><bean:message key="descargar.formatos.SCORM_2004_Sin_Sub_Manifiesto"/></label>
		  				</div>
						<div class="linea_separadora"></div>
						<br class="oculto" />
					</div>	
					<!--     -->
					<div class="fila_de_tabla">
		  				<div class="text ft_lateral">
		  				<html:radio property="formato" styleClass="boton_radio" styleId="Formato02" name="form"  value="descargar.formatos.SCORM_2004_VALUE" />
		  				<label for="Formato02"  class="alineada2"><bean:message key="descargar.formatos.SCORM_2004"/></label>
		  				</div>
						<div class="linea_separadora"></div>
						<br class="oculto" />
					</div>
					<!--     -->
					<div class="fila_de_tabla">	
		  				<div class="text ft_lateral">
		  				<html:radio property="formato" styleClass="boton_radio" styleId="Formato03" name="form"  value="descargar.formatos.SCORM_1.2_VALUE" />
		  				<label for="Formato03"  class="alineada2"><bean:message key="descargar.formatos.SCORM_1.2"/></label>
		  				</div>
						<div class="linea_separadora"></div>
						<br class="oculto" />
					</div>
					<!--     -->
					<div class="fila_de_tabla">
		  				<div class="text ft_lateral">
		  				<html:radio property="formato" styleClass="boton_radio" styleId="Formato04" name="form"  value="descargar.formatos.IMS_CP_VALUE" />
		  				<label for="Formato04"  class="alineada3"><bean:message key="descargar.formatos.IMS_CP"/></label>
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
		<!--  FIN GLOBO GRIS   -->
		<!--  FIN GLOBO GRIS   -->
	<bean:define id="texto" value="${sesion.textoLicencia}"/>	
	<c:if  test="${!empty texto }">
	<c:set var="longitud" value="${ fn:length(texto)}"/>
		<c:if test="${longitud>0 }">
		<p class="parrafo_comun" id="separacion3"> <bean:message key="descargar.formatos.condicion"/></p>
		
		<!--  INICIO GLOBO GRIS   -->
		<!--  INICIO GLOBO GRIS   -->
		<div class="globo_gris conmargen" >
			<div class="globo_gris_01">
				<div class="globo_gris_02">
					<div class="globo_gris_03">
						<!--  INICIO CAJA DE FORMULARIO   -->
		
					<div class="formu" >
					 <div class="fila_de_tabla">
		  						<div class="text ft_lateral" >
		  						<textarea rows="10" cols="40" class="ta_minimo_ancho" style="width:70%" id="textoLicencia" title="<bean:message key="descargar.formatos.licencia"/>" readonly="readonly">${texto}</textarea><br />
					<input type="checkbox" class="boton_radio" style="margin-top:2px !important;" id="licenciaAceptar"  name="licenciaAceptar" value="licenciaAceptar"/><label for="licenciaAceptar" class="alineada5"><bean:message key="descargar.formatos.leido"/></label>
					</div>
						<div class="linea_separadora"></div>
						<br class="oculto" />
						</div>
		
						
						</div>
						<!--  FIN CAJA DE FORMULARIO   -->
					<div class="formu" >
					
					</div>
						<!--  FIN CAJA DE FORMULARIO   -->
					</div>
				</div>
			</div>
		</div>
		</c:if>
	</c:if>

<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->
		
		
			<input type="hidden" name="idioma" value="${form.idioma}"/>
			<input type="hidden" name="titulo" value="${form.titulo}"/>
			<input type="hidden" name="identificadorODE" value="${form.identificadorODE}"/>
			<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>
			<input type="hidden" name="tieneIdentidadFederada" value="${form.tieneIdentidadFederada}"/>
			<input type="hidden" name="nodoOrigen" value="${form.nodoOrigen}"/>
			<input class="boton_125_de_2 tipo"  type="submit"  value="<bean:message key="listar.odecu.mostrar.resultados.detalles.descargar"/>" alt="Descarga fichero ZIP"/>
	</form>
	<logic:equal name="form" property="mostrarVuelta" value="true">
		<logic:equal name="form" property="busquedaSimpleAvanzada" value="BUSQUEDA_ARBOL">
			<form action="<html:rewrite action="/DetallarODECU/DetallarODECU.do"/>?identificadorODE=${form.identificadorODE}&amp;idioma=${form.idioma}&amp;mostrarVuelta=${form.mostrarVuelta}&amp;areaCurricularBusqueda=&amp;posicionamiento=POSICIONADO" method="post">
				<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>
				<input type="hidden" name="tieneIdentidadFederada" value="${form.tieneIdentidadFederada}"/>
				<input type="hidden" name="nodoOrigen" value="${form.nodoOrigen}"/>
				<input class="boton_125_de_2_izq tipo"  type="submit"  value="<bean:message key="descargar.boton.volver"/>"/>
			</form>
		</logic:equal>
		<logic:notEqual name="form" property="busquedaSimpleAvanzada" value="BUSQUEDA_ARBOL">
			<form action="<html:rewrite action="/DetallarODECU/DetallarODECU.do"/>?identificadorODE=${form.identificadorODE}&amp;idioma=${form.idioma}&amp;mostrarVuelta=${form.mostrarVuelta}&amp;posicionamiento=POSICIONADO" method="post">
				<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>
				<input type="hidden" name="tieneIdentidadFederada" value="${form.tieneIdentidadFederada}"/>
				<input type="hidden" name="nodoOrigen" value="${form.nodoOrigen}"/>
				<input class="boton_125_de_2_izq tipo"  type="submit"  value="<bean:message key="descargar.boton.volver"/>"/>
			</form>
		</logic:notEqual>
	</logic:equal>
	<logic:notEqual name="form" property="mostrarVuelta" value="true">
		<form action="<html:rewrite action="/DetallarODECU/DetallarODECU.do"/>?identificadorODE=${form.identificadorODE}&amp;idioma=${form.idioma}&amp;mostrarVuelta=${form.mostrarVuelta}" method="post">
			<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>
			<input type="hidden" name="tieneIdentidadFederada" value="${form.tieneIdentidadFederada}"/>
			<input type="hidden" name="nodoOrigen" value="${form.nodoOrigen}"/>
			<input class="boton_125_de_2_izq tipo"  type="submit"  value="<bean:message key="descargar.boton.volver"/>"/>
		</form>
	</logic:notEqual>
</div>

</tiles:put>
</tiles:insert>
