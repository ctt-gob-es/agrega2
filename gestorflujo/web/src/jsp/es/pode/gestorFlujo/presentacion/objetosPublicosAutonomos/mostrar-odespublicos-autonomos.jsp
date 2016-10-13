<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<html:xhtml/>
<tiles:insert definition="layout-administrador">

	<tiles:put name="title" type="string">
		<bean:message key="title.Admnistracion" />
	</tiles:put>

	<tiles:put name="body" type="string">

		<!-- *****************************   Inicio  Plantilla contenido      ********************************** -->
		<div class="plantilla_contenido"><!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
		<jsp:include page="/layout/messages.jsp" flush="true" /> <!-- Inicio  PESTANIAS de FICHA -->
		<!-- Inicio  PESTANIAS de FICHA -->

		<div id="ficha_pestanias">
		<div>
		<h2><bean:message key="gestorFlujo.mostrarOdes.autonomos" /></h2>
		</div>
		<ul>
			<li id="pest_activa"><a
				href="<html:rewrite action="${initParam.url_publicosAutonomos}"/>"
				id="seleccionada"><bean:message
				key="gestorFlujo.mostrarOdes.autopublicados" /></a></li>
			
		</ul>
		</div>
		<!-- Fin PESTANIAS de FICHA --> <!-- Fin PESTANIAS de FICHA --> <!-- Inicio CONTENIDO PESTANIAS -->

		<!-- Inicio CONTENIDO PESTANIAS -->
		<div class="interno_ficha"><!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
		<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
		<!-- ATENCION!! E3TA ES LA CAPA QUE DEBE REEMPLAZARSE POR LA DEL MISMO NOMBRE (plantilla contenido pestanias ) EN LA PLANTILLA DE  CONTENIDO  CON EL CONTENIDO DE FICHA -->
		<div class="plantilla_contenido_pestanias"><!-- CAJA TABLA --> <!-- CAJA TABLA -->
		<fieldset>
		
		<div class="caja_tabla">

		<h3 class="h3_generico"><bean:message key="gestorFlujo.mostrarOdes.autopublicados" /></h3>
		
		<bean:define id="idUsuarioLocal">${form.idUsuario}</bean:define> 
		
		<display:table name="${form.listaODES}" requestURI="" export="false"
			id="fila" class="administracion_tareas" style="border:1;width:100%;"
			cellpadding="0" cellspacing="0" summary="${summary}" sort="list"
			partialList="False" pagesize="20">

			<display:caption>
				<bean:message key="gestorFlujo.mostrarOdes.tablaPublicadosAutonomos" />
			</display:caption>

			<display:setProperty name="css.tr.odd" value="tr_gris" />
			<display:setProperty name="css.tr.even" value="tr_blanco" />
			<display:setProperty name="basic.show.header" value="true" />

			<!--  ******************** COLUMNA DE TITULO ***********************-->
			<bean:define id="tituloValue"><b><bean:message key="titulo"/></b></bean:define>
			<display:column sortable="true" sortProperty="titulo" style="valign:top;" class="tar10" title="${tituloValue}" >
				<html:link action="${initParam.url_visualizar_pend_catalogacion}?idOde=${fila.idODE}"
				styleClass="paquete" target="blank">
					${fn:escapeXml(fila.titulo)}
				</html:link>
			</display:column>
			<td valign="top"></td>
			<!--  ******************** COLUMNA DE AUTOR ***********************-->
			<bean:define id="autorValue"><b><bean:message key="autor"/></b></bean:define>
			<display:column  sortable="true" sortProperty="idUsuario" style="valign:top;" class="tar11" title="${autorValue}">
				 ${fn:escapeXml(fila.idUsuario)} 
			</display:column>
			<td valign="top"></td>
			
			<!--  ******************** COLUMNA DE HISTORIAL ***********************-->
			<display:column style="valign:top;" class="ejec2">
				<span class="oculto">-</span>
				<html:link target="blank"
					action="${initParam.url_objetosPublicosAutonomos_historial}?idODE=${fila.idODE}&titulo=${fila.titulo}">
					<bean:message key="gestorFlujo.mostrarOdes.verHistorial" />
				</html:link>
			</display:column>

			<!--  ******************** COLUMNA DE DESPUBLICAR ***********************-->
			<display:column style="valign:top;" class="ejec">
				<span class="oculto">-</span>
					<html:link
						action="${initParam.url_objetosPublicosAutonomos_despublicar}?idODE=${fila.idODE}&idUsuario=${idUsuarioLocal}&titulo=${fila.titulo}">
						<bean:message key="gestorFlujo.mostrarOdes.despublicarAutonomos" />
					</html:link>

			</display:column>
			
			
		</display:table> <!-- No se cierra el div aqui porque se hace en el archivo displaytag.properties al paginar -->
		<c:if test="${fn:length(form.listaODES) < 1}"></div>
		</c:if> <!-- FIN CAJA TABLA --> <!-- FIN CAJA TABLA --> <!-- Inicio Botones  -->
		<!-- Inicio Botones  --> 
			
		</fieldset>
		</div>
		<!-- HASTA AQUI EL REEEMPLAZO --> <!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
		<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->


		</div>
		<!-- Fin CONTENIDO PESTANIAS --> <!-- Fin CONTENIDO PESTANIAS --> <!-- HASTA AQUI EL REEEMPLAZO -->
		<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
		<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->

		</div>
		<!-- Fin plantilla CONTENIDO -->
	</tiles:put>

</tiles:insert>