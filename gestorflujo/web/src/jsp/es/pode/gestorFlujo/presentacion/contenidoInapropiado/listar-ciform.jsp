<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<html:xhtml/>
<tiles:insert definition="layout-administrador">

<tiles:put name="title" type="string">
	<bean:message key="title.Admnistracion"/>
</tiles:put>

<tiles:put name="body" type="string">
<bean:define id="contenidoInapSession" name="<%=es.pode.gestorFlujo.presentacion.contenidoInapropiado.ContenidoInapSession.SESSION_KEY%>" scope="session"/>
<!-- *****************************   Inicio  Plantilla contenido      ********************************** -->
<div class="plantilla_contenido"><!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
<jsp:include page="/layout/messages.jsp" flush="true" /> <!-- Inicio  PESTANIAS de FICHA -->
	
	<!-- empieza plantilla -->
	
		<h2><bean:message key="gestorflujo.objetos.inapropiados"/></h2>
		<form method="post" action="<html:rewrite action="/ContenidoInapropiado/ListarCIFormEliminar"/>" >
		
		
		<!-- CAJA TABLA -->
		<!-- CAJA TABLA -->
		<div class="caja_tabla" >
		<bean:define id="summary"><bean:message key="gestorflujo.tablaContenidosInapropiados"/></bean:define>
		<display:table name="${form.contenidos}"
			requestURI="" export="false"
			id="fila" class="administracion_tareas" style="border:1;width:100%;"
			cellpadding="0" cellspacing="0" summary="${summary}" sort="list"
			partialList="False" pagesize="10">
			
			<display:caption>
				<strong><bean:message key="gestorflujo.tablaContenidosInapropiados"/></strong>
			</display:caption>

			<display:setProperty name="css.tr.odd" value="tr_gris" />
			<display:setProperty name="css.tr.even" value="tr_blanco" />
			<display:setProperty name="basic.show.header" value="true" />
		
			<!-- checkbox -->
			<display:column style="valign:top;" class="sin_b">
				<fmt:formatDate var ="fechaString" value="${fila.fecha_inactividad.time}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>
				<input type="checkbox" name="idOdeRowSelectionAsArray"
					value="${fila.idOde}/${fila.estado_ci }/${fila.estado }/${fechaString }/${fila.idioma_cat }"
					title="<bean:message key="gestorFlujo.mostrarOdes.seleccione"/>" />
			</display:column>
			
			<!-- titulo ficha ODE-->
			<bean:define id="tituloValue"><b><bean:message key="gestorflujo.contenidoinapropiado.titulo"/></b></bean:define>
			<display:column sortable="true" sortProperty="titulo" style="valign:top;" class="tar10" title="${tituloValue}" >
				<a href="<rewrite:rewrite url="${contenidoInapSession.fichaOde}/${fila.idioma_cat}/${fila.idOde }/true"/>" class ="paquete2" target="_blank">
					${fn:escapeXml(fila.titulo)}
				</a>
			</display:column>
			
		<!--  fecha  -->
		<bean:define id="fechaValue"><bean:message key="gestorflujo.contenidoinapropiado.fecha"/></bean:define>
		<display:column sortable="true" sortProperty="fecha" style="valign:top;" class="tar5" title="${fechaValue}" >
			<fmt:formatDate value="${fila.fecha.time}" type="date" pattern="dd/MM/yyyy"/>
		</display:column>
		
		<!-- usuario -->
		<bean:define id="usuarioValue"><bean:message key="gestorflujo.contenidoinapropiado.usuario"/></bean:define>
		<display:column sortable="true" sortProperty="usuario" style="valign:top;" class="tar9" title="${usuarioValue}" >
				${fn:escapeXml(fila.usuario)}			
		</display:column>

		<!-- ver reportes -->
		<display:column style="valign:top;" class="tar11">
			<span class="oculto">-</span>
			<fmt:formatDate var ="fechaString" value="${fila.fecha_inactividad.time}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>
			<a href="<html:rewrite action="/ContenidoInapropiado/ListarCIFormVerReporte"/>?idOde=${fila.idOde }&estado=${fila.estado }&estado_ci=${fila.estado_ci }&fecha_inactividad=${fechaString}&idioma_cat=${fila.idioma_cat}">
				<bean:message key="gestorflujo.contenidoinapropiado.verReportes"/>
			</a>
		</display:column>
			
		<!-- rechazar -->
		<display:column style="valign:top;" class="tar11">
			<span class="oculto">-</span>
			<c:choose>
				<c:when test="${fila.estado!='PUBLICADO' || (fila.estado=='PUBLICADO' && fila.estado_ci != true)}">
					<a href="#" class="inactivo"><bean:message key="gestorflujo.contenidoinapropiado.rechazar"/>
					</a>
				</c:when>
				<c:otherwise>
					<fmt:formatDate var ="fechaString" value="${fila.fecha_inactividad.time}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>
					<a href="<html:rewrite action="/ContenidoInapropiado/ListarCIFormRechazar"/>?idOde=${fila.idOde }&estado=${fila.estado }&estado_ci=${fila.estado_ci }&fecha_inactividad=${fechaString}&idioma_cat=${fila.idioma_cat}">
						<bean:message key="gestorflujo.contenidoinapropiado.rechazar"/>
					</a>
				</c:otherwise>
			</c:choose>
			
		</display:column>

		<!-- despublicar -->
		<display:column style="valign:top;" class="ejec">
			<span class="oculto">-</span>
			<c:choose>
				<c:when test="${fila.estado=='PUBLICADO' && fila.estado_ci == true}">
					<fmt:formatDate var ="fechaString" value="${fila.fecha_inactividad.time}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>
					<a href="<html:rewrite action="/ContenidoInapropiado/ListarCIFormDespublicar"/>?idOde=${fila.idOde }&estado=${fila.estado }&estado_ci=${fila.estado_ci }&fecha_inactividad=${fechaString}&idioma_cat=${fila.idioma_cat}">
						<bean:message key="gestorflujo.contenidoinapropiado.despublicar"/>
					</a>
				</c:when>
				<c:otherwise>
					<a href="#" class="inactivo">
						<bean:message key="gestorflujo.contenidoinapropiado.despublicar"/>
					</a>
				</c:otherwise>
			</c:choose>
			
		</display:column>
					
		</display:table>
		
		<c:if test="${!empty form.contenidos}">
			<!-- Inicio Botones  -->
			<fieldset class="tipo">
				<input class="boton_125_de_2"  type="submit"  value="<bean:message key="gestorFlujo.contenidoinapropiado.eliminar"/>" />
			</fieldset>
			<!-- Fin Botones  -->
		</c:if>
		</div>
		<!-- FIN CAJA TABLA -->
		</form>
	
	<!-- termina plantilla -->
		
</div>
<!-- Fin plantilla CONTENIDO -->
</tiles:put>

</tiles:insert>

