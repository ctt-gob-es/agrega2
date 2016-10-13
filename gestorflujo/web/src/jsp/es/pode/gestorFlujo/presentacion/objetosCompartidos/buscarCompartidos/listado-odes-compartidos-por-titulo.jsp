<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="pg" %>

<html:xhtml/>
<tiles:insert definition="layout-gestor-flujo-con-style">

	<tiles:put name="title" type="string">
		<bean:message key="title.Comun" />
	</tiles:put>

	<tiles:put name="body" type="string">

		<!-- *****************************   Inicio  Plantilla contenido      ********************************** -->
		<div class="plantilla_contenido"><!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
		<jsp:include page="/layout/messages.jsp" flush="true" /> <!-- Inicio  PESTANIAS de FICHA -->
		<form method="post"	action="">
		<input type="hidden" name="form" property="retorno" value="objetosCompartidos" />
		<input type="hidden" name="espacioLibre" value="${form.espacioLibre}"/>

		<!-- Inicio Lateral Interior  -->
		<div class="lateral_destacados" id="sin_mtop">
		
		<!--   -->
		
		<ul class="listadebanners_peq">
			<li id="des_int_01" >
			<c:choose>
				<c:when test="${form.espacioLibre>'0'}">
					<a href="<html:rewrite action="/ObjetosPersonalesCU/MostrarODESPersonalesSubmit.do?action=Crear&espacioLibre=${form.espacioLibre}" />" type="submit" name="action" title='<bean:message key="gestorFlujo.mostrarOdes.crearObjeto"/>' id="boton_crear_objeto" ><span><bean:message key="gestorFlujo.mostrarOdes.crearObjeto"/></span></a>
				</c:when>
				<c:otherwise>
					<a href="<html:rewrite action="/BuscarCompartidosCU/ListadoOdesCompartidosPorTituloVolver.do" />" type="submit" name="action" disabled="true" title='<bean:message key="gestorFlujo.mostrarOdes.crearObjeto"/>' id="boton_crear_objeto" ><span><bean:message key="gestorFlujo.mostrarOdes.crearObjeto"/></span></a>
				</c:otherwise>
			</c:choose>
			</li>
			
			<li id="des_int_02" >
				<a href="<html:rewrite action="/ObjetosPersonalesCU/MostrarODESPersonalesSubmit.do?action=Crear Metadato" />" type="submit" name="action" title='<bean:message key="gestorFlujo.mostrarOdes.crearMetadato"/>' id="boton_crear_metadatos" ><span><bean:message key="gestorFlujo.mostrarOdes.crearMetadato"/></span></a>
			</li>
			
			<li id="des_int_03" >
			<c:choose>
				<c:when test="${form.espacioLibre>'0'}">
					<a href="<html:rewrite action="/ImportarCU/ImportarCU.do?espacioLibre=${form.espacioLibre}" />" type="submit" name="action" title='<bean:message key="gestorFlujo.importar.objeto"/>' id="boton_importar_objeto" ><span><bean:message key="gestorFlujo.importar.objeto"/></span></a>
				</c:when>
				<c:otherwise>
					<a href="<html:rewrite action="/BuscarCompartidosCU/ListadoOdesCompartidosPorTituloVolver.do" />" name="action" disabled="true" title='<bean:message key="gestorFlujo.importar.objeto"/>' id="boton_importar_objeto" ><span><bean:message key="gestorFlujo.importar.objeto"/></span></a>
				</c:otherwise>
			</c:choose>
			</li>
		</ul>

		</div>
		<!-- Fin Lateral Interior  -->

		<!--  INICIO GLOBO GRIS   --><!--  INICIO GLOBO GRIS   -->
		<div class="globo_gris" id="contenido_medio" >
			<div class="globo_gris_01">
			<div class="globo_gris_02">
			<div class="globo_gris_03" >
				<div id="menu_pestanias">
				<ul>
					<li><a href="<html:rewrite action="${initParam.url_objetosPersonales}"/>"><span><bean:message key="gestorFlujo.mostrarOdes.personales" /></span></a></li>
					<li><a href="<html:rewrite action="${initParam.url_objetosPropuestos }"/>"><span><bean:message key="gestorFlujo.mostrarOdes.propuestos" /></span></a></li>
					<li><a href="<html:rewrite action="${initParam.url_objetosPublicados }"/>"><span><bean:message key="gestorFlujo.mostrarOdes.publicados" /></span></a></li>
					<li><a href="<html:rewrite action="${initParam.url_objetosPublicadosAutonomos }"/>"><span><bean:message key="gestorFlujo.mostrarOdes.autopublicados" /></span></a></li>
					<li class="final_li pest_activa""><a href="<html:rewrite action="${initParam.url_objetosCompartidos }"/>"><span><bean:message key="gestorFlujo.mostrarOdes.compartidos" /></span></a></li>
				</ul>
				</div> 
				
				<div id="formulario"   >
				<h3><bean:message key="gestorFlujo.busqueda.resultadoBusqueda" /></h3>
				
				<pg:pager id="pgcompartidosTitulo" items="${fn:length(form.listadoOdesCompartidosTitulo)}" maxPageItems="4" maxIndexPages="10" scope="request" export="currentPageNumber=pageNumber">
				
				<c:if test="${fn:length(form.listadoOdesCompartidosTitulo)>0}">
				<c:forEach items="${form.listadoOdesCompartidosTitulo}" var="compartidosTitulo">
				<pg:item id="pgcompartidosTitulo">	
				<!--  INICIO GLOBO AZUL   --><!--  INICIO GLOBO AZUL   -->
				<div class="globo_blanco gb_ie" style="margin-top:5px;">
					<div class="globo_blanco_01">
					<div class="globo_blanco_02">
					<div class="globo_blanco_03">
						<div class="item_objeto c_p_compartido">
						<div class="li_izq_usuario">
							<img src="${form.urlImagen }" alt="${compartidosTitulo.idODE}" />
						</div>
						<div class="li_der_usuario" >		
							<a href="<html:rewrite action="/ObjetosCompartidosCU/MostrarODESCompartidosPrevisualizar.do?idOde=${compartidosTitulo.idODE}&comunidadAgrega=false" />" class="tit_obj" target="_blank">${compartidosTitulo.titulo}</a>
							<p>${fn:escapeXml(compartidosTitulo.tamaino)} <bean:message key="Mbyte"/>
							<em>${fn:escapeXml(compartidosTitulo.idUsuario)}</em> <!-- autor -->
							
							<!--  ******************** EXPORTAR (DESCARGAR) ***********************-->
							<html:link	action="${initParam.url_objetosPersonales_exportar}?idODE=${compartidosTitulo.idODE}&titulo=${compartidosTitulo.titulo}&retorno=objetosCompartidos">
								<bean:message key="gestorFlujo.mostrarOdes.exportar" />
							</html:link>	
							
							<!--  ******************** PREVISUALIZAR ***********************-->
							<html:link action="${initParam.url_visualizar_compartidos}?idOde=${compartidosTitulo.idODE}&comunidadAgrega=false" target="blank">
								<bean:message key="gestorFlujo.mostrarOdes.previsualizar" />
							</html:link>
							</p>
							
							<!--  ******************** COPIAR A CARPETA PERSONAL (IMPORTAR) ***********************-->
							<p>
							<html:link	action="${initParam.url_objetosCompartidos_importar}?idODE=${compartidosTitulo.idODE}&titulo=${compartidosTitulo.titulo }">
								<bean:message key="gestorFlujo.mostrarOdes.copiarAPersonal" />
							</html:link>
							</p>
						</div>
						<br class="limpiar_izq" />
						</div>								
					</div>
					</div>
					</div>
				</div>
				<!--  FIN GLOBO AZUL   --><!--  FIN GLOBO AZUL   -->
				</pg:item>
				</c:forEach>
				</c:if>
				<hr />

				<pg:index id="pgcompartidosTitulo">
				<div class="paginacion" id="separacion5">
				<ul id="navlist">
				<pg:prev id="pgcompartidosTitulo">
				<li><a href="${pageUrl }"><bean:message key="gestorFlujo.anterior"/></a></li>
				</pg:prev>
				
				<pg:pages id="pgcompartidosTitulo">
				<c:choose>
				<c:when test="${pageNumber eq currentPageNumber}">
				<li>${pageNumber }</li>
				</c:when>
				<c:otherwise>
				<li><a href="${pageUrl }">${pageNumber }</a></li>
				</c:otherwise>
				</c:choose>
				</pg:pages>
				<pg:next id="pgcompartidosTitulo">
				<li><a href="${pageUrl }"><bean:message key="gestorFlujo.siguiente"/></a></li>
				</pg:next>
				      </ul>
				</div>
				</pg:index>
				 
	          	</pg:pager>
	          
				<c:if test="${fn:length(form.listadoOdesCompartidosTitulo)<1}">
				<div class="globo_blanco gb_ie" >
				<div class="globo_blanco_01">
				<div class="globo_blanco_02">
				<div class="globo_blanco_03">
				<div class="item_objeto c_p_compartido">
				<p class="parrafo_com"><bean:message key="gestorFlujo.mostrarOdes.noHay"/></p>
				</div>
				</div>
				</div>
				</div>
				</div>
				</c:if>
					
		</div><!-- fin formulario -->		
		
		
			<br />

</div>
</div>
</div>
</div>
<!--  FIN GLOBO GRIS   --><!--  FIN GLOBO GRIS -->

</form>
		
		<!-- Inicio Botones  -->
		<fieldset class="tipo ft_centrada">
			<form id="buscarCompartidosCUListadoOdesCompartidosPorTituloVolverForm" action="<html:rewrite action="/BuscarCompartidosCU/ListadoOdesCompartidosPorTituloVolver"/>" method="post" >
				<input class="boton_125"  type="submit"  value="<bean:message key='gestorFlujo.mostrarOdes.verTodos'/>" />
			</form>
		</fieldset>
			
			
			
			
			
<!-- Fin Botones  -->
</div>
<!-- Fin plantilla contenido  -->


</tiles:put>

</tiles:insert>