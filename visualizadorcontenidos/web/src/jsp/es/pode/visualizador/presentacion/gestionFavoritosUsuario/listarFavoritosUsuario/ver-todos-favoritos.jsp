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

<form id="listarFavoritosUsuarioVerTodosFavoritosBuscarForm" action='<html:rewrite action="/ListarFavoritosUsuario/VerTodosFavoritosBuscar"/>' method="post" >
<!-- Inicio Lateral Interior  -->
<div class="lateral_destacados" id="sin_mtop">

<!--   -->
<div class="nube_destacada_blanca inicial_n">
<div class="nub_d">
<strong class="tipo_h3"><bean:message key="favoritos.texto.buscar"/></strong>
<p><label for="textoBusqueda"><bean:message key="favoritos.texto.busqueda" /></label>

<input name="textoBusqueda" onfocus="limpiarTexto(this)" value="${form.textoBusqueda}"  maxlength="30"  onblur="this.style.backgroundColor='#e1e1e1'" id="textoBusqueda" type="text" title="<bean:message key="buscador.buscador"/> "  /><br class="oculto" /><br class="oculto" /><input type="submit" alt="<bean:message key="boton.buscar"/>"  class="boton_125_de_2_izq"  value="<bean:message key="buscador.buscador.boton"/>" name="buscar" /><br class="limpiar" />
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
				<li id="pest_medio"><a href="<html:rewrite action="/ListarContactosUsuario/ListarContactosUsuario.do"/>"><span><bean:message key="usuarioPublico.contactos" /></span></a></li>
				<li class="final_li pest_activa"><a href="<html:rewrite action="/ListarFavoritosUsuario/ListarFavoritosUsuario.do"/>"><span><bean:message key="usuarioPublico.favoritos" /></span></a></li>

				</ul>
				</div>
				<!--  SUBMENU   -->


				<div id="formulario"   >
			
			
			<c:if test="${fn:length(form.favoritos)>0}">
			<pg:pager id="pgfavoritos" items="${fn:length(form.favoritos)}" maxPageItems="4" maxIndexPages="10" scope="request" export="currentPageNumber=pageNumber">
							<c:forEach items="${form.favoritos}" var="favorito">
							<pg:item id="pgfavoritos">	
							<!--  INICIO GLOBO AZUL   -->
			<!--  INICIO GLOBO AZUL   -->
			<div class="globo_blanco gb_ie" >
				<div class="globo_blanco_01">
					<div class="globo_blanco_02">

						<div class="globo_blanco_03">
						
						<div class="item_comunidad i_favorito">
						<div class="li_izq_usuario"><img src="${favorito.urlImagen}" alt="${favorito.urlImagen}" width="70"/></div>
						<div class="li_der_usuario" >
						<a href="${favorito.urlFicha}" class="tit_obj" id="${favorito.titulo}" target="_blank">${favorito.titulo} </a>
						<p><a href="<html:rewrite action="/ListarGruposAsociados/ListarGruposAsociados.do?id_mec=${favorito.id_mec }&idioma=${favorito.idioma }&nombre=&vuelta=Favorito&titulo=${favorito.titulo }"/>"><bean:message key="usuarioPublico.favoritos.enviar.grupo"/></a></p>
						<p><a href="${favorito.urlPrevisualizar}" target="_blank"><bean:message key="usuarioPublico.favoritos.previsualizar"/></a></p>
						<p><a href="<html:rewrite action="/DesasociarFavorito/DesasociarFavorito.do?id_mec=${favorito.id_mec }&titulo=${favorito.titulo }"/>"><bean:message key="usuarioPublico.favoritos.desasociar"/></a></p>
						</div>
						<br class="limpiar_izq" />

						</div>

						</div>
					</div>
				</div>
		</div>
		</pg:item>
		</c:forEach>
		<pg:index id="pgfavoritos">
						<div class="paginacion" id="separacion5">
						<ul id="navlist">
						
						<pg:prev id="pgfavoritos">
						<li><a href="${pageUrl }"><bean:message key="noticias.anterior"/></a></li>
						</pg:prev>
						
						<pg:pages id="pgfavoritos">
						<c:choose>
						<c:when test="${pageNumber eq currentPageNumber}">
						<li>${pageNumber }</li>
						</c:when>
						<c:otherwise>
						<li><a href="${pageUrl }">${pageNumber }</a></li>
						</c:otherwise>
						</c:choose>
						</pg:pages>
						
						<pg:next id="pgfavoritos">
						<li><a href="${pageUrl }"><bean:message key="noticias.siguiente"/></a></li>
						</pg:next>
						
						</ul>
						</div>
						</pg:index>
						
		</pg:pager>	
		</c:if>
		<c:if test="${fn:length(form.favoritos)<1}">
		<div class="globo_blanco gb_ie" >
				<div class="globo_blanco_01">
					<div class="globo_blanco_02">

						<div class="globo_blanco_03">
							<p class="parrafo_com"><bean:message key="grupos.no.existe.actualmente"/></p>
						</div>
						</div>
						</div>
						</div>
		</c:if>

		
		</div>
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

