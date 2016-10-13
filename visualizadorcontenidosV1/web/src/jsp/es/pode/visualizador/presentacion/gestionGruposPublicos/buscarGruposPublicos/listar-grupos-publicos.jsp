<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tags/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
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

<form id="buscarGruposPublicosListarGruposPublicosBuscarForm" action='<html:rewrite action="/BuscarGruposPublicos/ListarGruposPublicosBuscar"/>' method="post" >

<!-- Inicio Lateral Interior  -->
<div class="lateral_destacados" id="sin_mtop">

<input type="hidden" id="textoBuscado" name="textoBuscado" value="${form.textoBuscado}" />

<div class="nube_destacada_blanca inicial_n">
<div class="nub_d">
<strong class="tipo_h3"><bean:message key="grupos.busqueda.grupos" /></strong>
<p><label for="textoBusqueda"><bean:message key="texto.busqueda.grupos" /></label>

<input name="textoBusqueda" onfocus="limpiarTexto(this)" value="${form.textoBusqueda}"  maxlength="30"  onblur="this.style.backgroundColor='#e1e1e1'" id="textoBusqueda" type="text" title="<bean:message key="buscador.buscador"/> "  /><br class="oculto" /><br class="oculto" /><input type="submit" alt="<bean:message key="boton.buscar"/>"  class="boton_125_de_2_izq"  value="<bean:message key="buscador.buscador.boton"/>" name="buscar" /><br class="limpiar" />
</p>
</div>
</div>

<strong class="oculto">Destacados:</strong>
<ul class="listadebanners_peq">
<li id="des_int_01" >
<a href="<html:rewrite action="/CrearGrupoPublico/CrearGrupoPublico.do"/>" title='<bean:message key="grupo.publico.crear"/>' id="boton_crear_itinerario" ><span><bean:message key="grupo.publico.crear"/></span></a></li>
</ul>
<!--   -->

</div>
<!-- Fin Lateral Interior  -->

<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" id="contenido_medio" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03" >
			
				<div id="menu_pestanias_02">
				<ul>
				<li class="pest_activa"><a href="<html:rewrite action="/GestionGruposPublicos/GestionGruposPublicos.do"/>"><span><bean:message key="usuarioPublico.itinerarios.aprendizaje"/></span></a></li>

				<li id="pest_medio"><a href="<html:rewrite action="/ListarContactosUsuario/ListarContactosUsuario.do"/>"><span><bean:message key="usuarioPublico.contactos" /></span></a></li>
				<li class="final_li"><a href="<html:rewrite action="/ListarFavoritosUsuario/ListarFavoritosUsuario.do"/>"><span><bean:message key="usuarioPublico.favoritos" /></span></a></li>
				</ul>
				</div>
				<div id="formulario"   >
				<h3><bean:message key="texto.grupos.lista"/></h3>
				
				<!--  INICIO GLOBO AZUL   -->
			<!--  INICIO GLOBO AZUL   -->

			
			
			<c:if test="${fn:length(form.gruposPublicos)>0}">
			<pg:pager id="pggrupos" items="${fn:length(form.gruposPublicos)}" maxPageItems="4" maxIndexPages="10" scope="request" export="currentPageNumber=pageNumber">
							<c:forEach items="${form.gruposPublicos}" var="grupo">
							<pg:item id="pggrupos">
			<div class="globo_blanco gb_ie" style="margin-top:5px;">
				<div class="globo_blanco_01">
					<div class="globo_blanco_02">
						<div class="globo_blanco_03">
						
						<div class="item_comunidad i_crear_itinerario i_crear_itinerario_int">

						<div class="li_izq_usuario"><img src="${grupo.imagenGrupo}" alt="${grupo.imagenGrupo}" width="50" height="50"/></div>
						<div class="li_der_usuario" >
						<a href="<html:rewrite action="/GestionGrupoPublico/GestionGrupoPublico.do?nombre=${grupo.nombre}&busqueda=Busqueda&textoBuscado=${form.textoBuscado }"/>" id="${grupo.nombre}"  class="tit_obj">${grupo.nombre} </a>
						<p class="parrafo_com">${grupo.descripcion}</p>
						<p class="parrafo_com">${grupo.administrador}</p>
						<c:if test="${!grupo.asociado}">
						<p><a href="<html:rewrite action="/AsociarGrupoAUsuario/AsociarGrupoAUsuario.do?id=${ grupo.id}"/>"><bean:message key="texto.asociarte.a.grupo"/></a></p>
		       			 </c:if>
		       			<c:if test="${(grupo.asociado) and (grupo.administrador eq form.usuario)}">
						<p><a href="<html:rewrite action="/EliminarGrupoPublico/EliminarGrupoPublico.do?id=${grupo.id}&nombre=${grupo.nombre }" />"id="${grupo.id}"><bean:message key="usuarioPublico.grupoPublico.eliminar"/></a></p>
						</c:if>
						<c:if test="${(grupo.asociado) and !(grupo.administrador eq form.usuario)}">
						<p><a href="<html:rewrite action="/DesasociarGrupoPublicoDeUsuario/DesasociarGrupoPublicoDeUsuario.do?id=${grupo.id}&usuario=${form.usuario }&vuelta=Lista&nombre=${grupo.nombre }"  />"id="${grupo.nombre}"><bean:message key="usuarioPublico.favoritos.desasociar"/></a></p>
		       			 </c:if>							
						
						</div>
						<br class="limpiar_izq" />

						</div>

						</div>
					</div>
				</div>
		</div>
		</pg:item>
		</c:forEach>
		<pg:index id="pggrupos">
						<div class="paginacion" id="separacion5">
						<ul id="navlist">
						
						<pg:prev id="pggrupos">
						<li><a href="${pageUrl }"><bean:message key="noticias.anterior"/></a></li>
						</pg:prev>
						
						<pg:pages id="pggrupos">
						<c:choose>
						<c:when test="${pageNumber eq currentPageNumber}">
						<li>${pageNumber }</li>
						</c:when>
						<c:otherwise>
						<li><a href="${pageUrl }">${pageNumber }</a></li>
						</c:otherwise>
						</c:choose>
						</pg:pages>
						
						<pg:next id="pggrupos">
						<li><a href="${pageUrl }"><bean:message key="noticias.siguiente"/></a></li>
						</pg:next>
						
						</ul>
						</div>
						</pg:index>
		</pg:pager>
		</c:if>
		<c:if test="${fn:length(form.gruposPublicos)<1}">
		
		<div class="globo_blanco gb_ie" style="margin-top:5px;">
				<div class="globo_blanco_01">
					<div class="globo_blanco_02">
						<div class="globo_blanco_03">
						<p class="parrafo_com"><bean:message key="grupos.no.existe.actualmente"/></p>
						
							</div>
							</div>
							</div>
							</div>
		</c:if>
		
		<!--  FIN GLOBO AZUL   -->
		<!--  FIN GLOBO AZUL   -->
		</div>
	<!--  INICIO PAGINACION   -->
		
		
		<!--  FIN PAGINACION   -->	

			</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->

</form>

</div>
		

</tiles:put>
</tiles:insert>

