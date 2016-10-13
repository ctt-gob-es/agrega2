<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<%@ taglib uri="/WEB-INF/tlds/tags-gestorFlujo.tld" prefix="el"%>
<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="pg" %>

<html:xhtml/>
<tiles:insert definition="layout-gestor-flujo-con-style">

	<tiles:put name="title" type="string">
		<bean:message key="title.Comun" />
	</tiles:put>

<tiles:put name="body" type="string">
		<!-- Inicio plantilla contenido  -->

<div class="plantilla_contenido">
<jsp:include page="/layout/messages.jsp" flush="true" />
<form method="post" action="" >
	<input type="hidden" name="espacioLibre" value="${form.espacioLibre}"/>
	
<!-- Inicio Lateral Interior  -->
<div class="lateral_destacados" id="sin_mtop">
	<!--   -->
	<strong class="oculto">Destacados:</strong>
	
	<ul class="listadebanners_peq">
	<li id="des_int_01" >
	<c:choose>
	<c:when test="${form.espacioLibre>'0'}">
		<a href="<html:rewrite action="/ObjetosPersonalesCU/MostrarODESPersonalesSubmit.do?action=Crear&espacioLibre=${form.espacioLibre}" />" type="submit" name="action" title='<bean:message key="gestorFlujo.mostrarOdes.crearObjeto"/>' id="boton_crear_objeto" ><span><bean:message key="gestorFlujo.mostrarOdes.crearObjeto"/></span></a>
	</c:when>
	<c:otherwise>
		<a href="" type="submit" name="action" disabled="true" title='<bean:message key="gestorFlujo.mostrarOdes.crearObjeto"/>' id="boton_crear_objeto" ><span><bean:message key="gestorFlujo.mostrarOdes.crearObjeto"/></span></a>
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
			<a href="" type="submit" name="action" disabled="true" title='<bean:message key="gestorFlujo.importar.objeto"/>' id="boton_importar_objeto" ><span><bean:message key="gestorFlujo.importar.objeto"/></span></a>
		</c:otherwise>
	</c:choose>
	</li>
	</ul>
</div>
<!-- Fin Lateral Interior  -->


<!-- <h2>Título XYZ</h2>  -->


<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" id="contenido_medio" >
	<div class="globo_gris_01">
	<div class="globo_gris_02">
	<div class="globo_gris_03" >
		<div id="menu_pestanias">
		<ul>
			<li><a href="<html:rewrite action="${initParam.url_objetosPersonales}"/>"><span><bean:message key="gestorFlujo.mostrarOdes.personales" /></span></a></li>
			<li><a href="<html:rewrite action="${initParam.url_objetosPropuestos }"/>"><span><bean:message key="gestorFlujo.mostrarOdes.propuestos" /></span></a></li>
			<li class="pest_activa"><a href="<html:rewrite action="${initParam.url_objetosPublicados }"/>"><span><bean:message key="gestorFlujo.mostrarOdes.publicados" /></span></a></li>
			<li><a href="<html:rewrite action="${initParam.url_objetosPublicadosAutonomos }"/>"><span><bean:message key="gestorFlujo.mostrarOdes.autopublicados" /></span></a></li>
			<li class="final_li"><a href="<html:rewrite action="${initParam.url_objetosCompartidos }"/>"><span><bean:message key="gestorFlujo.mostrarOdes.compartidos" /></span></a></li>
		</ul>
		</div>
	
		<div id="formulario"   >
		<pg:pager id="pgpublicadosTitulo" url="" items="${fn:length(form.listadoOdesPublicadosTitulo)}" maxPageItems="4" maxIndexPages="10" scope="request" export="currentPageNumber=pageNumber">	
			<ul id="lista_de_odespublicados_titulo">
			<c:if test="${fn:length(form.listadoOdesPublicadosTitulo)>0}">
			<c:forEach items="${form.listadoOdesPublicadosTitulo}" var="publicadosTitulo">
			<pg:item id="pgpublicadosTitulo">	
			
			<!--  INICIO GLOBO AZUL   --><!--  INICIO GLOBO AZUL   -->
			<div class="globo_blanco gb_ie" >
			<div class="globo_blanco_01">
			<div class="globo_blanco_02">
			<div class="globo_blanco_03">
			
				<div class="item_objeto c_p_publicado">
					<div class="li_der_usuario" >		
						<img src="${form.urlImagen }" alt="${publicadosTitulo.titulo}" width="70" class="flotante_ode" />
						<a href="<html:rewrite action="/ObjetosPublicadosCU/MostrarODESPublicadosPrevisualizar.do?idOde=${publicadosTitulo.idODE}&comunidadAgrega=true" />" class="tit_obj" target="_blank">${publicadosTitulo.titulo}</a>
						<p>
						<!--  ******************** VER HISTORIAL ***********************-->
						<html:link target="blank" action="${initParam.url_objetosPersonales_mostrar_historial}?idODE=${publicadosTitulo.idODE}&titulo=${publicadosTitulo.titulo}" >
							<bean:message key="gestorFlujo.mostrarOdes.historialODE" />
						</html:link>
						<!--  ******************** PREVISUALIZAR ***********************-->
						<html:link action="${initParam.url_visualizar_publicados}?idOde=${publicados.idODE}&comunidadAgrega=true" target="blank">
							<bean:message key="gestorFlujo.mostrarOdes.previsualizar" />
						</html:link>
						</p><p>
						<!--  ******************** ENVIAR A GRUPO ***********************-->
						<html:link action="${initParam.url_objetosPublicados_odesGrupo}?idODE=${publicadosTitulo.idODE}&titulo=${publicadosTitulo.titulo}">
							<bean:message key="gestorFlujo.mostrarOdes.enviarAGrupo" />
						</html:link>
						<!--  ******************** ENVIAR A CARPETA PERSONAL ***********************-->
						<html:link action="../buscador/AnadirCarpetaPersonalCU/AnadirCarpetaPersonalCU.do?identificadorODE=${publicadosTitulo.idODE}&idiomaODE=es&tipoLayoutBuscador=BUSCADOR&busquedaSimpleAvanzada=CARPETA_PERSONAL&mostrarVuelta=true">
							<bean:message key="gestorFlujo.mostrarOdes.crearNuevaVersion" />
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
			</ul>
			<hr/>
			
			<c:if test="${fn:length(form.listadoOdesPublicadosTitulo)<1}">
			<div class="globo_blanco gb_ie" >
			<div class="globo_blanco_01">
			<div class="globo_blanco_02">
			<div class="globo_blanco_03">
				<div class="item_objeto c_p_publicado">
					<p class="parrafo_com"><bean:message key="gestorFlujo.mostrarOdes.noHay"/></p>
				</div>
			</div>
			</div>
			</div>
			</div>
			</c:if>
			
				 <pg:index id="pgpublicadosTitulo">
                 	<div class="paginacion" id="separacion5">
                		<ul id="navlist">
			      		 <pg:prev id="pgpublicadosTitulo">
                       	 <li><a href="${pageUrl }"><bean:message key="gestorFlujo.anterior"/></a></li>
                  		 </pg:prev>

                 		<pg:pages id="pgpublicadosTitulo">
	                	 <c:choose>
	                	 <c:when test="${pageNumber eq currentPageNumber}">
		                	<li>${pageNumber }</li>
	                 	 </c:when>
		             	<c:otherwise>
					      <li><a href="${pageUrl }">${pageNumber }</a></li>
		             	</c:otherwise>
		             	</c:choose>
                 		</pg:pages>
						  <pg:next id="pgpublicadosTitulo">
						  		<li><a href="${pageUrl }"><bean:message key="gestorFlujo.siguiente"/></a></li>
						  </pg:next>
		         </ul>
				 </div>
				</pg:index>
	        </pg:pager> 
		

</div><!-- fin formulario -->
				
					
	</div>
	</div>
	</div>
	</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->

</form>
		<!-- Inicio Botones  -->
		<fieldset class="tipo ft_centrada">
			<form id="buscarPublicadosCUListadoOdesPublicadosPorTituloVolverForm" action="<html:rewrite action="/BuscarPublicadosCU/ListadoOdesPublicadosPorTituloVolver"/>" method="post" >
				<input class="boton_125"  type="submit"  value="<bean:message key='gestorFlujo.mostrarOdes.verTodos'/>" />
			</form>
		</fieldset>

</div>
<!-- Fin plantilla contenido  -->
</tiles:put>

</tiles:insert>
