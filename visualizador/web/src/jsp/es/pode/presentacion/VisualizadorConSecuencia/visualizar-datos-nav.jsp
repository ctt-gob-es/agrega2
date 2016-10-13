<%@ include file="/taglib-imports.jspf" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tlds/tags-visualizador.tld" prefix="vis" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<tiles:insert definition="layout-principal">



<tiles:put name="title" type="string">
	<bean:message key="title.Comun"/> - ${fn:escapeXml(form.tituloOde)}
</tiles:put>



<tiles:put name="body" type="string">

					<c:set var="flechas" value="${0}" /> 
					
					<!--  FLECHAS  -->
					<bean:define id="actionBoton"><html:rewrite action="/VisualizadorCS/VisualizarDatosNavSecuenciaBoton"/></bean:define>
				     <c:if test="${form.contador > 0 }" >
						<c:if test="${form.btnIzquierdo }">
							<c:set var="flechas" value="${flechas + 1 }" />
							<bean:define id="anterior"><bean:message key="cabecera.VerAnterior"/></bean:define>
							<html:link href="${actionBoton}?identificador=${form.identificador}&usuario=${form.usuario}&contador=${form.contador -1}&menuDesplegado=${form.menuDesplegado}" title="${anterior }" styleClass="tipoflecha f_left">&nbsp;&nbsp;<span><bean:message key="cabecera.InformeAnterior"/></span></html:link>
						</c:if>
					 </c:if>
					 
					 <c:set var="longitud" value="${fn:length(form.itemsFlow) -1}" />
					 <c:if test="${form.contador < longitud}">
						<c:if test="${form.btnDerecho }">
							<c:set var="flechas" value="${flechas + 1 }" />
							<bean:define id="siguiente"><bean:message key="cabecera.VerSiguiente"/></bean:define>
							<html:link href="${actionBoton}?identificador=${form.identificador}&usuario=${form.usuario}&contador=${form.contador +1}&menuDesplegado=${form.menuDesplegado}" title="${siguiente }" styleClass="tipoflecha f_right">&nbsp;&nbsp;<span><bean:message key="cabecera.InformeSiguiente"/></span></html:link>
						</c:if>
					</c:if>
						
					
					<!-- Panel contenidos!! -->
					<div id="capa_contenidos" name="capa_contenidos">
						<bean:define id="mensaje0"><bean:message key="frame.contenidos.mensaje0"/></bean:define>
						<bean:define id="mensaje1"><bean:message key="frame.contenidos.mensaje1"/></bean:define>
						<bean:define id="mensaje2"><bean:message key="frame.contenidos.mensaje2"/></bean:define>
						<bean:define id="mensaje3"><bean:message key="frame.contenidos.mensaje3"/></bean:define>
						<vis:contenidos 
							id="contenido"
							urlContenido="${form.urlContenido}" 
							mensajeIframe0="${mensaje0}"
							mensajeIframe1="${mensaje1}"
							mensajeIframe2="${mensaje2}"
							mensajeIframe3="${mensaje3}" />
						<br class="limpiar" />
					</div>
					
					<!-- Panel contenidos!! -->
			


	<script type="text/javascript">
	//<![CDATA[
		$("#contenido_central_largo").contenido({
			ancho_menu:430,
			inicio_menu_desplegado:'${form.menuDesplegado}',
			boton_menu:'#enlace_menu',
			contenedor_menu:'#panel_menu',
			contenedor_frame:'#contenido',
			flechas: ${flechas}
			}
		);
	//]]>
	</script>






</tiles:put>
</tiles:insert>
