<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html:xhtml/>

<tiles:insert definition="layout-administrador">

<tiles:put name="title" type="string">
      <bean:message key="title.Comun"/>
</tiles:put>


<tiles:put name="body" type="string">

<%@ include file="/taglib-imports.jspf" %>


<!--		Inicio plantilla contenido		-->

<div class="plantilla_contenido">


<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
<jsp:include page="/layout/messages.jsp" flush="true" />
	<!--		Inicio del formulario		-->
	<form id=verItinerarioAprendizajeDatosItinerarioRecuperarForm action='<html:rewrite action="/VerItinerarioAprendizaje/DatosItinerarioRecuperar"/>' method="post" >
<!-- Inicio Lateral Interior  -->
<input type="hidden" id="nombre" name="nombre" value="${form.nombre}" />
<input type="hidden" id="id" name="id" value="${form.id}" /> 
<input type="hidden" id="volver" name="volver" value="${form.volver}" /> 


<!--   -->

<!--   -->
<!--   -->



						
		

<!--   -->


<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->

<div class="globo_gris" id="contenido_medio" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03" >
				<div class="formu"   >
					<!--  INICIO GLOBO AZUL   -->
				<!--  INICIO GLOBO AZUL   -->
				<div class="globo_blanco gb_ie " >
				<div class="globo_blanco_01">
					<div class="globo_blanco_02">
						<div class="globo_blanco_03">
						
						<div class="item_comunidad nombre_itinerario">
						<img class="usu_flotante" src="${form.imagenGrupo}" alt="${form.nombre}" width="80" height="80"/>
						<span class="tit_obj">${form.nombre}</span><br />
						<c:if test="${!(form.descripcion eq null)}"><p class="parrafo_com"><em><bean:message key="grupoPublico.descripcion"/></em><br />${form.descripcion}</p></c:if>
						<p><bean:message key="usuarioPublico.administrador"/>:<br />${form.administrador}</p>
						</div>
					
						<!-- <strong class="tipo_h3">
							<a href="<html:rewrite action="/EliminarItinerariosAprendizaje/EliminarItinerariosAprendizaje.do?ids=${form.id}"/>"><bean:message key='usuario.eliminar.grupo.cabecera'/></a>
						</strong> -->
						
						<!--  INICIO GLOBO AZUL   -->
			<!--  INICIO GLOBO AZUL   -->
		<div class="globo_gris conmargen"  >
		<div class="globo_gris_01">
		<div class="globo_gris_02">
		<div class="globo_gris_03" >
		
		
		<h3 class="separacion07"><bean:message key="grupoPublico.usuariosPublicosAsociados"/> <c:if test="${form.verMas[1]}">(<a href="<html:rewrite action="/VerItinerarioAprendizaje/VerItinerarioAprendizaje.do?nombre=${form.nombre}&actionUsuario=actionUsuario&volver=${form.volver}"/>"><bean:message key='usuarioPublico.ver.mas'/></a>)</c:if></h3>
				
				<div class="formu especial_comunidad"   >
				
				<c:if test="${fn:length(form.usuarios)>0}">
				<pg:pager id="pgusuarios" items="${fn:length(form.usuarios)}" maxPageItems="4" maxIndexPages="10" scope="request" export="currentPageNumber=pageNumber">
				<c:forEach items="${form.usuarios}" var="usuario">
				<pg:item id="pgusuarios">
				<!--    -->

						<div class="globo_blanco conmargen" >
							<div class="globo_blanco_01">
								<div class="globo_blanco_02">
									<div class="globo_blanco_03">
									
										<div class="item_comunidad i_publicado">
											<div class="li_izq_usuario"><img src="${usuario.foto}" alt="${usuario.foto}" width="65" height="65"/>
											</div>
												<div class="li_der_usuario" >
													<a href="<html:rewrite action="${usuario.urlUsuario}&volver=${form.volver}"/>" id="${usuario.usuario}"  class="tit_obj">${usuario.usuario}</a><br />
													<p>${usuario.descripcion}</p>
	
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
			</div>
		
			

		<!--  FIN GLOBO AZUL   -->
		<!--  FIN GLOBO AZUL   -->
	<!--  INICIO GLOBO AZUL   -->
			<!--  INICIO GLOBO AZUL   -->
		<div class="globo_gris conmargen"  >
		<div class="globo_gris_01">
		<div class="globo_gris_02">
		<div class="globo_gris_03" >
		
		<h3 class="separacion07"><bean:message key="grupoPublico.odesAsociados"/><c:if test="${form.verMas[0]}">(<a href="<html:rewrite action="/VerItinerarioAprendizaje/VerItinerarioAprendizaje.do?nombre=${form.nombre}&actionOde=actionOde&volver=${form.volver}"/>"><bean:message key='usuarioPublico.ver.mas'/></a>)</c:if></h3>
				
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
						<div class="li_izq_usuario"><img src="${ode.urlImagen}" alt="${ode.urlImagen}" width="70"/></div>
						<div class="li_der_usuario" >
						<a href="${ode.urlFicha}" class="tit_obj" target="_blank">${ode.titulo}</a><br />

						<p><a href="${ode.urlPrevisualizar}&volver=${form.volver}" target="_blank"><bean:message key="usuarioPublico.favoritos.previsualizar"/></a></p>
	
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
			</div>
		


		<!--  FIN GLOBO Blanco   -->
		<!--  FIN GLOBO Blanco   -->
		
		
		<input class="boton_125"  type="submit"  value="<bean:message key="usuarios.solicitarAlta.volver"/>" />	
		</div>

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
	<!--		Fin del formulario		-->
	
	
</div>

<!--		Fin de la capa plantilla_contenido		-->

</div>
</tiles:put>


</tiles:insert>