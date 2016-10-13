<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tags/pager-taglib.tld" prefix="pg" %>
<html:xhtml/> 
<tiles:insert definition="layout-gestor-flujo-con-style">
<tiles:put name="title" type="string">
	<bean:message key="title.Comun"/>
</tiles:put>



<tiles:put name="body" type="string">




<div class="plantilla_contenido">

<%@ include file="/taglib-imports.jspf" %>
<%@ include file="/es/pode/visualizador/presentacion/gestionGruposPublicos/mostrar-lista-grupos-vars.jspf" %>
<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
<jsp:include page="/layout/messages.jsp" flush="true" />
<form id="gestionGruposPublicosMostrarListaGruposBuscarGruposForm" action='<html:rewrite action="/GestionGruposPublicos/MostrarListaGruposBuscarGrupos"/>' method="post" >
<!-- Inicio Lateral Interior  -->
<div class="lateral_destacados" id="sin_mtop">


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
				<div class="formu"   >
					<!--  INICIO GLOBO AZUL   -->
				<!--  INICIO GLOBO AZUL   -->
				
				
				
				<div class="globo_blanco gb_ie" >

				<div class="globo_blanco_01">
					<div class="globo_blanco_02">
						<div class="globo_blanco_03">
						
						<h3 class="sinmargenaba"><bean:message key="texto.mis.grupos"/> <c:if test="${form.verMas[0]}">(<a href="<html:rewrite action="/GestionGruposPublicos/GestionGruposPublicos.do?actionGrupos=actionGrupos"/>"><bean:message key='usuarioPublico.ver.mas'/></a>)</c:if></h3>
						<c:if test="${fn:length(form.gruposPublicos)>0}">
						<pg:pager id="pggrupos" url="" items="${fn:length(form.gruposPublicos)}" maxPageItems="4" maxIndexPages="10" scope="request" export="currentPageNumber=pageNumber">
							<c:forEach items="${form.gruposPublicos}" var="grupo">
							<pg:item id="pggrupos">
							
							
							<!--    -->
							<div class="globo_gris conmargen"  >
							<div class="globo_gris_01">
	
							<div class="globo_gris_02">
							<div class="globo_gris_03" >
							<div class="item_comunidad i_crear_itinerario">
							<div class="li_izq_usuario"><img src="${grupo.imagenGrupo}" alt="${grupo.imagenGrupo}" width="50" height="50"/></div>
							<div class="li_der_usuario" >
						
							<a href="<html:rewrite action="/GestionGrupoPublico/GestionGrupoPublico.do?nombre=${grupo.nombre}"/>" id="${grupo.nombre}" class="tit_obj">${grupo.nombre}</a><c:if test="${!grupo.creador}"><strong class="socio">(<bean:message key="usuarioPublico.socio"/>)</strong><br /></c:if><c:if  test="${grupo.creador}"><strong class="socio">(<bean:message key="usuarioPublico.administrador"/>)</strong><br /></c:if>
							
							<p class="parrafo_com">${grupo.descripcion}</p>
							<p><c:if  test="${grupo.creador}"><a href="<html:rewrite action="/EliminarGrupoPublico/EliminarGrupoPublico.do?id=${grupo.id}&nombre=${grupo.nombre }&vuelta=Lista" />"id="${grupo.id}"><bean:message key="usuarioPublico.grupoPublico.eliminar"/></a></c:if></p>
							<p><c:if  test="${!grupo.creador}"><a href="<html:rewrite action="/DesasociarGrupoPublicoDeUsuario/DesasociarGrupoPublicoDeUsuario.do?id=${grupo.id}&usuario=${form.usuario }&vuelta=Lista&nombre=${grupo.nombre }" />"id="${grupo.id}"><bean:message key="usuarioPublico.favoritos.desasociar"/></a></c:if></p>
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
							<div class="globo_gris conmargen"  >
							<div class="globo_gris_01">
	
							<div class="globo_gris_02">
							<div class="globo_gris_03" >
										
							
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
		<!--  FIN GLOBO AZUL   -->
		<!--  FIN GLOBO AZUL   -->
			
						
		
				

				
				<!--  INICIO GLOBO AZUL   -->
		<!--  INICIO GLOBO AZUL   -->
		
		
		<div class="globo_blanco gb_ie" >
				<div class="globo_blanco_01">
					<div class="globo_blanco_02">
						<div class="globo_blanco_03">
						<h3 class="sinmargenaba"><bean:message key="usuarioPublico.gruposSolicitados"/> <c:if test="${form.verMas[1]}">(<a href="<html:rewrite action="/GestionGruposPublicos/GestionGruposPublicos.do?actionSolicitudes=actionSolicitudes"/>"><bean:message key='usuarioPublico.ver.mas'/></a>)</c:if></h3>
					<c:if test="${fn:length(form.solicitudes)>0}">
					<pg:pager id="pgsolicitudes" items="${fn:length(form.solicitudes)}" maxPageItems="4" maxIndexPages="10" scope="request" export="currentPageNumber=pageNumber">
							<c:forEach items="${form.solicitudes}" var="solicitudes">
						<pg:item id="pgsolicitudes">
							<!--    -->
						<div class="globo_gris conmargen"  >

						<div class="globo_gris_01">
						<div class="globo_gris_02">
						<div class="globo_gris_03" >
									
						<div class="item_comunidad i_crear_itinerario caja_botones">
						<div class="li_izq_usuario"><img src="${solicitudes.urlImagen}" alt="${solicitudes.urlImagen}" /></div>
						<div class="li_der_usuario" >
								
						<a href="<html:rewrite action="/GestionGrupoPublico/GestionGrupoPublico.do?nombre=${solicitudes.grupo}"/>" id="${solicitudes.grupo}" class="tit_obj">${solicitudes.grupo}</a><br />
						<p class="parrafo_com">${solicitudes.descripcionGrupo}</p>
						<p><a href="<html:rewrite action="/EliminarGruposSolicitados/EliminarGruposSolicitados.do?id=${solicitudes.id}&nombre=${solicitudes.grupo }"/>" id="${solicitudes.id}"><bean:message key="usuarioPublico.grupoPublico.eliminar"/></a></p>
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
						<pg:index id="pgsolicitudes">
						<div class="paginacion" id="separacion5">
						<ul id="navlist">
						
						<pg:prev id="pgsolicitudes">
						<li><a href="${pageUrl }"><bean:message key="noticias.anterior"/></a></li>
						</pg:prev>
						
						<pg:pages id="pgsolicitudes">
						<c:choose>
						<c:when test="${pageNumber eq currentPageNumber}">
						<li>${pageNumber }</li>
						</c:when>
						<c:otherwise>
						<li><a href="${pageUrl }">${pageNumber }</a></li>
						</c:otherwise>
						</c:choose>
						</pg:pages>
						
						<pg:next id="pgsolicitudes">
						<li><a href="${pageUrl }"><bean:message key="noticias.siguiente"/></a></li>
						</pg:next>
						
						</ul>
						</div>
						</pg:index>
						
						</pg:pager>	
					</c:if>
					<c:if test="${fn:length(form.solicitudes)<1}">
							<div class="globo_gris conmargen"  >
							<div class="globo_gris_01">
	
							<div class="globo_gris_02">
							<div class="globo_gris_03" >
										
							
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
					<!--  FIN GLOBO AZUL   -->
		<!--  FIN GLOBO AZUL   -->
			
		
			<!--  INICIO GLOBO AZUL   -->
			<!--  INICIO GLOBO AZUL   -->
			
			
			<div class="globo_blanco gb_ie" >
				<div class="globo_blanco_01">
					<div class="globo_blanco_02">
						<div class="globo_blanco_03">
						
						<h3 class="sinmargenaba"><bean:message key="usuarioPublico.solicitantes"/> <c:if test="${form.verMas[2]}">(<a href="<html:rewrite action="/GestionGruposPublicos/GestionGruposPublicos.do?actionSolicitantes=actionSolicitantes"/>" ><bean:message key='usuarioPublico.ver.mas'/></a>)</c:if></h3>
					<c:if test="${fn:length(form.solicitantes)>0}">
					<pg:pager id="pgsolicitantes" items="${fn:length(form.solicitantes)}" maxPageItems="4" maxIndexPages="10" scope="request" export="currentPageNumber=pageNumber">
						<c:forEach items="${form.solicitantes}" var="solicitantes">
						<pg:item id="pgsolicitantes">	
												
							<!--    -->
							<div class="globo_gris conmargen"  >
								<div class="globo_gris_01">
									<div class="globo_gris_02">
										<div class="globo_gris_03" >
										
											<div class="botones_com_dos_botones">
													<a href="<html:rewrite action="/AceptarSolicitud/AceptarSolicitud.do?id=${solicitantes.id}&grupo=${solicitantes.grupo }&usuarioSolicitante=${solicitantes.usuarioSolicitante }"/>" id="${solicitantes.id}"><bean:message key='usuarios.aceptar'/></a>
													<a href="<html:rewrite action="/CancelarSolicitud/CancelarSolicitud.do?id=${solicitantes.id}&grupo=${solicitantes.grupo }&usuarioSolicitante=${solicitantes.usuarioSolicitante }" />"id="${solicitantes.id}"><bean:message key='usuarios.cancelar'/></a>
											</div>
											
											<div class="item_comunidad i_crear_itinerario caja_botones">
											<div class="li_izq_usuario"><img src="${solicitantes.urlImagenUsuario}" alt="${solicitantes.urlImagenUsuario}" width="70"/></div>
											<div class="li_der_usuario" >
											<a href="<html:rewrite action="/VerContacto/VerContacto.do?contacto=${solicitantes.usuarioSolicitante}&vuelta=Grupo"/>" id="${solicitantes.usuarioSolicitante}"  class="tit_obj">${solicitantes.usuarioSolicitante}</a><br />
											<p class="parrafo_com">${solicitantes.descripcionUsuario}</p>
											<p>${solicitantes.grupo}</p>
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
						<pg:index id="pgsolicitantes">
						<div class="paginacion" id="separacion5">
						<ul id="navlist">
						
						<pg:prev id="pgsolicitantes">
						<li><a href="${pageUrl }"><bean:message key="noticias.anterior"/></a></li>
						</pg:prev>
						
						<pg:pages id="pgsolicitantes">
						<c:choose>
						<c:when test="${pageNumber eq currentPageNumber}">
						<li>${pageNumber }</li>
						</c:when>
						<c:otherwise>
						<li><a href="${pageUrl }">${pageNumber }</a></li>
						</c:otherwise>
						</c:choose>
						</pg:pages>
						
						<pg:next id="pgsolicitantes">
						<li><a href="${pageUrl }"><bean:message key="noticias.siguiente"/></a></li>
						</pg:next>
						
						</ul>
						</div>
		</pg:index>
		</pg:pager>	
					</c:if>
					<c:if test="${fn:length(form.solicitantes)<1}">
							<div class="globo_gris conmargen"  >
							<div class="globo_gris_01">
	
							<div class="globo_gris_02">
							<div class="globo_gris_03" >
										
							
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

