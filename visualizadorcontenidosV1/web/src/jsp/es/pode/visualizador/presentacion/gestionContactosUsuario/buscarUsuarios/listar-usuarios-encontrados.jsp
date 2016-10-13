<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tags/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html:xhtml/> 
<tiles:insert definition="layout-gestor-flujo-con-style">
<tiles:put name="title" type="string">
	<bean:message key="title.Comun"/>
</tiles:put>
<tiles:put name="body" type="string">
<%@ include file="/taglib-imports.jspf" %>

<div class="plantilla_contenido">


<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
<jsp:include page="/layout/messages.jsp" flush="true" />

<form id="buscarUsuariosListarUsuariosEncontradosBuscarForm" action='<html:rewrite action="/BuscarUsuarios/ListarUsuariosEncontradosBuscar"/>' method="post" >
<!-- Inicio Lateral Interior  -->
<div class="lateral_destacados" id="sin_mtop">

<!--   -->
<div class="nube_destacada_blanca inicial_n">
<div class="nub_d">
<strong class="tipo_h3"><bean:message key="usuarios.busqueda.usuarios"/></strong>
<p><label for="textoBusqueda"><bean:message key="texto.busqueda.usuarios" /></label>
<input name="textoBusqueda" onfocus="limpiarTexto(this)" value="${form.textoBusqueda}"   maxlength="30" onblur="this.style.backgroundColor='#e1e1e1'" id="textoBusqueda" type="text" title="<bean:message key="buscador.buscador"/>"  /><br class="oculto" /><br class="oculto" /><input type="submit" alt="<bean:message key="boton.buscar"/>"  class="boton_125_de_2_izq"  value=" <bean:message key="buscador.buscador.boton"/>" name="buscar" /><br class="limpiar" />
</p>
</div>
</div>
<!--   -->

</div>

<!-- Fin Lateral Interior  -->

<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" id="contenido_medio" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">

			<div class="globo_gris_03" >
				
				<!--  SUBMENU   -->
				<div id="menu_pestanias_02">
				<ul>
				<li><a href="<html:rewrite action="/GestionGruposPublicos/GestionGruposPublicos.do"/>"><span><bean:message key="usuarioPublico.itinerarios.aprendizaje"/></span></a></li>
				<li class="pest_activa" id="pest_medio"><a href="<html:rewrite action="/ListarContactosUsuario/ListarContactosUsuario.do"/>"><span><bean:message key="usuarioPublico.contactos" /></span></a></li>
				<li class="final_li"><a href="<html:rewrite action="/ListarFavoritosUsuario/ListarFavoritosUsuario.do"/>"><span><bean:message key="usuarioPublico.favoritos" /></span></a></li>

				</ul>
				</div>
				<!--  SUBMENU   -->
				<div id="formulario" >
				<h3><bean:message key="usuarioPublico.usuarios.cabecera" /></h3>
				
				<!--  INICIO GLOBO AZUL   -->
			<!--  INICIO GLOBO AZUL   -->
			<c:if test="${fn:length(form.usuarios)>0}">
			<pg:pager id="pgusuarios" items="${fn:length(form.usuarios)}" maxPageItems="4" maxIndexPages="10" scope="request" export="currentPageNumber=pageNumber">
			<c:forEach items="${form.usuarios}" var="usuario">
			<pg:item id="pgusuarios">
			<div class="globo_blanco gb_ie" style="margin-top:5px;">
				<div class="globo_blanco_01">
					<div class="globo_blanco_02">
						<div class="globo_blanco_03">
						
						<!--    -->
						<div class="item_com">
						<div class="li_izq_usuario"><img src="${usuario.imagenUsuario}" alt="${usuario.imagenUsuario}" width="65" height="65"/></div>
						<div class="li_der_usuario" >
						<a href="<html:rewrite action="/VerContacto/VerContacto.do?contacto=${usuario.usuarioContacto}&vuelta=Buscar&textoBuscado=${form.textoBuscado }&busqueda=Busqueda"/>" class="nomb" id="${usuario.usuarioContacto}">${usuario.usuarioContacto}</a>
						<p>${usuario.descripcionUsuario}</p>
						
						<c:if test="${!usuario.esAsociadoUsuario && !(usuario.usuarioContacto eq form.usuarioConsulta)}">					
							<p><a href="<html:rewrite action="/AnadirAAgenda/AnadirAAgenda.do?usuarioContacto=${usuario.usuarioContacto }"/>" id="${usuario.usuarioContacto}"><bean:message key="usuarioPublico.anadir.contacto"/></a></p>
						</c:if>
						<c:if test="${usuario.esAsociadoUsuario && !(usuario.usuarioContacto eq form.usuarioConsulta)}">					
							<p><a href="<html:rewrite action="/EliminarContacto/EliminarContacto.do?id=${usuario.id}&usuarioContacto=${usuario.usuarioContacto }&vuelta=Usuario"/>" id="${usuario.id}"><bean:message key="usuarioPublico.grupoPublico.eliminar"/></a></p>
						</c:if>
						</div>
						<br class="limpiar" />
						</div>
						<!--    -->

						</div>

					</div>
				</div>
		</div>
		</pg:item>
		</c:forEach>
		<pg:index id="pgusuarios">
						<div class="paginacion" id="separacion5">
						<ul id="navlist">
						
						<pg:prev id="pgusuarios">
						<li><a href="${pageUrl }"><bean:message key="noticias.anterior"/></a></li>
						</pg:prev>
						
						<pg:pages id="pgusuarios">
						<c:choose>
						<c:when test="${pageNumber eq currentPageNumber}">
						<li>${pageNumber }</li>
						</c:when>
						<c:otherwise>
						<li><a href="${pageUrl }">${pageNumber }</a></li>
						</c:otherwise>
						</c:choose>
						</pg:pages>
						
						<pg:next id="pgusuarios">
						<li><a href="${pageUrl }"><bean:message key="noticias.siguiente"/></a></li>
						</pg:next>
						
						</ul>
						</div>
						</pg:index>
						
		</pg:pager>			
		</c:if>
		<c:if test="${fn:length(form.usuarios)<1}">
		<div class="globo_blanco gb_ie" style="margin-top:5px;">
				<div class="globo_blanco_01">
					<div class="globo_blanco_02">
						<div class="globo_blanco_03">
						
						<!--    -->
						<p class="parrafo_com"><bean:message key="grupos.no.existe.actualmente"/></p>
						<!--    -->

						</div>

					</div>
				</div>
		</div>
		</c:if>
		<!--  FIN GLOBO AZUL   -->
		<!--  FIN GLOBO AZUL   -->
		
</div>
		</div>

	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->

</form>

</div>
<!-- Fin plantilla contenido  -->
<!-- Fin plantilla contenido  -->


</tiles:put>
</tiles:insert>

