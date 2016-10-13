<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
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
<form id="gestionGrupoPublicoDatosGrupoPublicoRecogerAccionForm" action='<html:rewrite action="/GestionGrupoPublico/DatosGrupoPublicoRecogerAccion"/>' method="post" >

<!-- Inicio Lateral Interior  -->
<div class="lateral_destacados" id="sin_mtop">

<input type="hidden" id="textoBuscado" name="textoBuscado" value="${form.textoBuscado}" />
<input type="hidden" id="busqueda" name="busqueda" value="${form.busqueda}" />
<div class="nube_destacada_blanca inicial_n">
<div class="nub_d">
<strong class="tipo_h3"><bean:message key="grupos.busqueda.grupos" /></strong>
<p><label for="textoBusqueda"><bean:message key="texto.busqueda.grupos" /></label>

<input name="textoBusqueda" onfocus="limpiarTexto(this)" value="${form.textoBusqueda}"  maxlength="30"  onblur="this.style.backgroundColor='#e1e1e1'" id="textoBusqueda" type="text" title="<bean:message key="buscador.buscador"/> "  /><br class="oculto" /><br class="oculto" /><input type="submit" alt="<bean:message key="boton.buscar"/>"  class="boton_125_de_2_izq"  value="<bean:message key="buscador.buscador.boton"/>" name="buscar" /><br class="limpiar" />
</p>
</div>
</div>
<!--   -->


<!--   -->
<div class="nube_destacada_blanca">
<div class="nub_d ">
<strong class="tipo_h3"><bean:message key="perfilPublico.widget"/></strong>


<div class="ficha" id="compartir" >
<label for="comp_url" ><bean:message key="perfilPublico.url"/></label>
<input type="text" name="comp_url" id="comp_url" readonly="readonly"  title="<bean:message key="perfilPublico.url.introduzca"/>" value="${form.urlGrupo}" />
<br />
<label for="comp_insertar" ><bean:message key="perfilPublico.insertar"/></label>

<textarea name="comp_insertar" id="comp_insertar" readonly="readonly"   readonly="readonly" onclick="SelectAll(this);">${form.codigoEmbeb }</textarea>
<br class="limpiar" />
</div>




</div>
</div>
<!--   -->

<!--   -->

<div class="nube_destacada_blanca">
<div class="nub_d ">
<strong class="tipo_h3"><bean:message key="grupoPublico.usuariosPublicosAsociados"/> <c:if test="${form.verMas[1]}">(<a href="<html:rewrite action="/GestionGrupoPublico/GestionGrupoPublico.do?nombre=${form.nombre}&actionUsuarios=actionUsuarios"/>"><bean:message key='usuarioPublico.ver.mas'/></a>)</c:if></strong>
<c:if test="${fn:length(form.usuarios)>0}">
<pg:pager id="pgusuarios" items="${fn:length(form.usuarios)}" maxPageItems="4" maxIndexPages="10" scope="request" export="currentPageNumber=pageNumber">
<c:forEach items="${form.usuarios}" var="usuario">
<pg:item id="pgusuarios">
<ul class="lista_usuario">


<li >
<div class="div_li">
<div class="li_izq_usuario"><img src="${usuario.foto}" alt="${usuario.foto}" width="50" height="50"/></div>
<div class="li_der_usuario" >
<a href="<html:rewrite action="/VerContacto/VerContacto.do?contacto=${usuario.usuario}&vuelta=Grupo"/>" id="${usuario.usuario}"  class="nomb">${usuario.usuario}</a>
<p>${usuario.descripcion}</p>
<div class="desasociar">
	<c:if test="${(form.esAdministrador) and !(form.administrador eq usuario.usuario)}">
		<p><a href="<html:rewrite action="/DesasociarUsuarioDeGrupo/DesasociarUsuarioDeGrupo.do?usuario=${usuario.usuario}&nombre=${form.nombre }"/>" id="${form.nombre}"><bean:message key="usuarioPublico.favoritos.desasociar"/></a></p>
	</c:if>
</div>
</div>
<br class="limpiar" />
</div>
</li>

</ul>
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
<ul class="lista_usuario">
<li >
<p class="parrafo_com"><bean:message key="grupos.no.existe.actualmente"/></p>
</li>
</ul>
</c:if>
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
				
				<div id="menu_pestanias_02">
				<ul>
				<li class="pest_activa"><a href="<html:rewrite action="/GestionGruposPublicos/GestionGruposPublicos.do"/>"><span><bean:message key="usuarioPublico.itinerarios.aprendizaje"/></span></a></li>

				<li id="pest_medio"><a href="<html:rewrite action="/ListarContactosUsuario/ListarContactosUsuario.do"/>"><span><bean:message key="usuarioPublico.contactos" /></span></a></li>
				<li class="final_li"><a href="<html:rewrite action="/ListarFavoritosUsuario/ListarFavoritosUsuario.do"/>"><span><bean:message key="usuarioPublico.favoritos" /></span></a></li>
				</ul>
				</div>
				<div class="formu"   >
					<!--  INICIO GLOBO AZUL   -->
				<!--  INICIO GLOBO AZUL   -->
				<input type="hidden" id="nombre" name="nombre" value="${form.nombre}" />
				<div class="globo_blanco gb_ie " >
				<div class="globo_blanco_01">
	
					<div class="globo_blanco_02">
						<div class="globo_blanco_03">
						
						<div class="item_comunidad nombre_itinerario">
						<img class="usu_flotante" src="${form.imagen}" alt="${form.nombre}" width="65" height="65"/>
						<span class="tit_obj">${form.nombre}</span><br />
						<c:if test="${!(form.descripcion eq null)}"><p class="parrafo_com"><em><bean:message key="grupoPublico.descripcion"/></em><br />${form.descripcion}</p></c:if>
						<p class="parrafo_com"><em><bean:message key="usuarioPublico.administrador"/>:</em><br />${form.administrador}</p>
						<c:if test="${form.esAdministrador}">
							<p><a href="<html:rewrite action="/EliminarGrupoPublico/EliminarGrupoPublico.do?id=${form.id}&nombre=${form.nombre }&vuelta=Itinerario"/>" id="${form.nombre}"><bean:message key="usuarioPublico.grupoPublico.eliminar"/></a></p>
							<p><a href="<html:rewrite action="/ModificarGrupoPublico/ModificarGrupoPublico.do?nombre=${form.nombre }"/>"><bean:message key="grupo.publico.modificar"/></a></p>
						</c:if>
						<c:if test="${!form.esAdministrador and form.asociado}">
							<p><a href="<html:rewrite action="/DesasociarGrupoPublicoDeUsuario/DesasociarGrupoPublicoDeUsuario.do?id=${form.id}&nombre=${form.nombre }&usuario=${form.usuarioConsultor }&vuelta=Itinerario" />"id="${form.id}"><bean:message key="usuarioPublico.favoritos.desasociar"/></a></p>
						</c:if>																										
						<c:if test="${!form.asociado}">
							<p><a href="<html:rewrite action="/AsociarGrupoAUsuario/AsociarGrupoAUsuario.do?id=${ form.id}"/>"><bean:message key="texto.asociarte.a.grupo"/></a></p>
						</c:if>
						</div>
				
				<!--  INICIO GLOBO AZUL   -->
			<!--  INICIO GLOBO AZUL   -->
		<div class="globo_gris conmargen"  >
		<div class="globo_gris_01">
		<div class="globo_gris_02">
		<div class="globo_gris_03" >
		
		<h3 class="separacion07"><bean:message key="grupoPublico.odesAsociados"/><c:if test="${form.verMas[0]}">(<a href="<html:rewrite action="/GestionGrupoPublico/GestionGrupoPublico.do?nombre=${form.nombre}&actionOdes=actionOdes"/>"><bean:message key='usuarioPublico.ver.mas'/></a>)</c:if></h3>
				
				<div class="formu especial_comunidad"   >
				<c:if test="${fn:length(form.odes)>0}">
				<pg:pager id="pgodes" items="${fn:length(form.odes)}" maxPageItems="4" maxIndexPages="10" scope="request" export="currentPageNumber=pageNumber">
							<c:forEach items="${form.odes}" var="ode">
							<pg:item id="pgodes">
				<!--    -->

						<div class="globo_blanco conmargen" >
						<div class="globo_blanco_01">
						<div class="globo_blanco_02">
						<div class="globo_blanco_03">
									
						<div class="item_comunidad i_publicado">
						<div class="li_izq_usuario"><img src="${ode.urlImagen}" alt="${ode.titulo}" width="70"/></div>
						<div class="li_der_usuario" >
						<a href="${ode.urlFicha}" class="tit_obj" target="_blank">${ode.titulo}</a><br />

						<p><a href="${ode.urlPrevisualizar}" target="_blank"><bean:message key="usuarioPublico.favoritos.previsualizar"/></a></p>
						
						<c:if test="${form.esAdministrador}">
						
						<p><a href="<html:rewrite action="/DesasociarOdeDeGrupo/DesasociarOdeDeGrupo.do?id_mec=${ode.id_mec}&nombre=${form.nombre}&titulo=${ode.titulo }"/>"><bean:message key="usuarioPublico.favoritos.desasociar"/>
						</a></p>

					</c:if>
					<c:if test="${form.esAdministrador}">
						
						<p><a href="<html:rewrite action="/ListarGruposAsociados/ListarGruposAsociados.do?id_mec=${ode.id_mec }&idioma=${ode.idioma }&nombre=${form.nombre}&vuelta=Grupo&titulo=${ode.titulo }"/>"><bean:message key="usuarioPublico.favoritos.enviar.grupo"/>
						</a></p>

					</c:if>
						
						</div>
						<br class="limpiar_izq" />
						</div>
									
						</div>
						</div>
						</div>

						</div>					
						<!--    -->
						</pg:item>
						</c:forEach>
						<pg:index id="pgodes">
						<div class="paginacion" id="separacion5">
						<ul id="navlist">
						
						<pg:prev id="pgodes">
						<li><a href="${pageUrl }"><bean:message key="noticias.anterior"/></a></li>
						</pg:prev>
						
						<pg:pages id="pgodes">
						<c:choose>
						<c:when test="${pageNumber eq currentPageNumber}">
						<li>${pageNumber }</li>
						</c:when>
						<c:otherwise>
						<li><a href="${pageUrl }">${pageNumber }</a></li>
						</c:otherwise>
						</c:choose>
						</pg:pages>
						
						<pg:next id="pgodes">
						<li><a href="${pageUrl }"><bean:message key="noticias.siguiente"/></a></li>
						</pg:next>
						
						</ul>
						</div>
						</pg:index>
						</pg:pager>
						</c:if>
						<c:if test="${fn:length(form.odes)<1}">
						
					<div class="globo_blanco conmargen" >
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
						
					</div>
				</div>
			</div>

		<!--  FIN GLOBO Blanco   -->
		<!--  FIN GLOBO Blanco   -->
		
</div>
<c:if test="${!(form.busqueda eq null)}">
<input type="hidden" id="textoBusqueda" name="textoBusqueda" value="${form.textoBuscado}" />
		<input class="boton_125"  type="submit" name="volver"  value="<bean:message key="usuarios.solicitarAlta.volver"/>" />
		</c:if>
				<!-- Fin capa formu  -->		
						
						</div>
					</div>
				</div>
			</div>
		<!--  FIN GLOBO AZUL   -->
		<!--  FIN GLOBO AZUL   -->

</div>
<!-- Fin capa formu  -->
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

