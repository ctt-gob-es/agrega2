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
<%@ include file="/es/pode/administracion/presentacion/adminusuarios/verUsuarioPublico/datos-usuario-publico-vars.jspf" %>


<!--		Inicio plantilla contenido		-->

<div class="plantilla_contenido">


<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
<jsp:include page="/layout/messages.jsp" flush="true" />
	<!--		Inicio del formulario		-->
	<form id=verUsuarioPublicoDatosUsuarioPublicoRecuperarForm action='<html:rewrite action="/VerUsuarioPublico/DatosUsuarioPublicoRecuperar"/>' method="post" >
<!-- Inicio Lateral Interior  -->
<input type="hidden" id="usuario" name="usuario" value="${form.usuario}" /> 
<input type="hidden" id="volver" name="volver" value="${form.volver}" /> 


<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" id="contenido_medio" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03" >

<div class="formu"   >
			<!--  INICIO GLOBO AZUL   -->
			<!--  INICIO GLOBO AZUL   -->
	
			<div class="globo_blanco gb_ie" >
				<div class="globo_blanco_01">
					<div class="globo_blanco_02">
						<div class="globo_blanco_03">
						<div class="item_comunidad nombre_contacto">
						<img class="usu_flotante" src="${form.imagenUsuario}" alt="${form.imagenUsuario}" width="80" height="80"/>
						<span class="tit_obj">${form.nombre}</span><br />
						<c:if test="${!(form.centro eq null)}"><p class="parrafo_com"><em><bean:message key="usuarioPublico.nombreCentro"/></em><br />${form.centro}</p></c:if>
						<c:if test="${!(form.descripcion eq null)}"><p><bean:message key="grupoPublico.descripcion"/><br />${form.descripcion}</p></c:if>
						
						</div>
			
			<!--  INICIO GLOBO AZUL   -->
			<!--  INICIO GLOBO AZUL   -->
			<div class="globo_gris conmargen"  >
			<div class="globo_gris_01">
			<div class="globo_gris_02">
			<div class="globo_gris_03" >

<h3 class="separacion07">
<bean:message key="usuarioPublico.itinerarios.aprendizaje"/> <c:if test="${form.verMas[1]}">(<a href="<html:rewrite action="/VerUsuarioPublico/VerUsuarioPublico.do?usuario=${form.usuario}&actionGrupos=actionGrupos&volver=${form.volver}"/>"><bean:message key='usuarioPublico.ver.mas'/></a>)</c:if></h3>
<div class="formu especial_comunidad"   >	
<c:if test="${fn:length(form.gruposPublicos)>0}">
<pg:pager id="pggrupos" items="${fn:length(form.gruposPublicos)}" maxPageItems="4" maxIndexPages="10" scope="request" export="currentPageNumber=pageNumber">
<c:forEach items="${form.gruposPublicos}" var="grupo">
<pg:item id="pggrupos">

<!--    -->
						<div class="globo_blanco conmargen" >
						<div class="globo_blanco_01">
						<div class="globo_blanco_02">
						<div class="globo_blanco_03">
						<div class="item_comunidad i_publicado">
<div class="li_izq_usuario"><img src="${grupo.imagenGrupo}" alt="${grupo.imagenGrupo}" width="50" height="50"/></div>

<div class="li_der_usuario" >
<p class="parrafo_com"><a href="<html:rewrite action="${grupo.urlGrupo}&volver=${form.volver}"/>" id="${grupo.nombre}">${grupo.nombre}</a></p>
<p>${grupo.descripcion}</p>
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
					
				<!-- Fin capa formu  -->		
						
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
		
			<h3 class="separacion07"><bean:message key="usuarioPublico.publicadosManual"/> <c:if test="${form.verMas[2]}">(<a href="<html:rewrite action="/VerUsuarioPublico/VerUsuarioPublico.do?usuario=${form.usuario}&actionManual=actionManual&volver=${form.volver}"/>"><bean:message key='usuarioPublico.ver.mas'/></a>)</c:if></h3>	
					<div class="formu especial_comunidad"   >	
							<c:if test="${fn:length(form.publicados)>0}">
							<pg:pager id="pgmanuales" items="${fn:length(form.publicados)}" maxPageItems="4" maxIndexPages="10" scope="request" export="currentPageNumber=pageNumber">
							<c:forEach items="${form.publicados}" var="manual">
							<pg:item id="pgmanuales">	
						<!--    -->
						<div class="globo_blanco conmargen" >
						<div class="globo_blanco_01">
						<div class="globo_blanco_02">
						<div class="globo_blanco_03">
						<div class="item_comunidad i_publicado">

						<div class="li_izq_usuario"><img src="${manual.urlImagen}" alt="${manual.urlImagen}" width="50" height="50"/></div>
						<div class="li_der_usuario" >
						<a href="${manual.urlFicha}" class="tit_obj" target="_blank">${manual.titulo}</a><br />
						<p><a href="${manual.urlPrevisualizar}&volver=${form.volver}" target="_blank"><bean:message key="usuarioPublico.favoritos.previsualizar"/></a></p>
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
						
					<pg:index id="pgmanuales">
						<div class="paginacion" id="separacion5">
						<ul id="navlist">
						
						<pg:prev id="pgmanuales">
						<li><a href="${pageUrl }"><bean:message key="noticias.anterior"/></a></li>
						</pg:prev>
						
						<pg:pages id="pgmanuales">
						<c:choose>
						<c:when test="${pageNumber eq currentPageNumber}">
						<li>${pageNumber }</li>
						</c:when>
						<c:otherwise>
						<li><a href="${pageUrl }">${pageNumber }</a></li>
						</c:otherwise>
						</c:choose>
						</pg:pages>
						
						<pg:next id="pgmanuales">
						<li><a href="${pageUrl }"><bean:message key="noticias.siguiente"/></a></li>
						</pg:next>
						
						</ul>
						</div>
						</pg:index>
						</pg:pager>	
					</c:if>	
					<c:if test="${fn:length(form.publicados)<1}">
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
					
				<!-- Fin capa formu  -->		
						
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
			<h3 class="separacion07"><bean:message key="usuarioPublico.publicadoAutonomo"/> <c:if test="${form.verMas[3]}">(<a href="<html:rewrite action="/VerUsuarioPublico/VerUsuarioPublico.do?usuario=${form.usuario}&actionAuto=actionAuto&volver=${form.volver}"/>"><bean:message key='usuarioPublico.ver.mas'/></a>)</c:if></h3>	
				<div class="formu especial_comunidad"   >	
						<c:if test="${fn:length(form.publicadosAutonoma)>0}">
						<pg:pager id="pgautos" items="${fn:length(form.publicadosAutonoma)}" maxPageItems="4" maxIndexPages="10" scope="request" export="currentPageNumber=pageNumber">
							<c:forEach items="${form.publicadosAutonoma}" var="auto">
							<pg:item id="pgautos">			
						<!--    -->

						<div class="globo_blanco conmargen" >
						<div class="globo_blanco_01">
						<div class="globo_blanco_02">
						<div class="globo_blanco_03">
					
						<div class="item_comunidad i_autopublicado">
						<div class="li_izq_usuario"><img src="${ auto.urlImagen}" alt="${ auto.urlImagen}" width="70"/></div>
						<div class="li_der_usuario" >
						<a href="${auto.urlPrevisualizar}&volver=${form.volver}" class="tit_obj" target="_blank">${auto.titulo}</a><br />
						<p><a href="${auto.urlPrevisualizar}&volver=${form.volver}" target="_blank"><bean:message key="usuarioPublico.favoritos.previsualizar"/></a></p>
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
						<pg:index id="pgautos">
						<div class="paginacion" id="separacion5">
						<ul id="navlist">
						
						<pg:prev id="pgautos">
						<li><a href="${pageUrl }"><bean:message key="noticias.anterior"/></a></li>
						</pg:prev>
						
						<pg:pages id="pgautos">
						<c:choose>
						<c:when test="${pageNumber eq currentPageNumber}">
						<li>${pageNumber }</li>
						</c:when>
						<c:otherwise>
						<li><a href="${pageUrl }">${pageNumber }</a></li>
						</c:otherwise>
						</c:choose>
						</pg:pages>
						
						<pg:next id="pgautos">
						<li><a href="${pageUrl }"><bean:message key="noticias.siguiente"/></a></li>
						</pg:next>
						
						</ul>
						</div>
						</pg:index>
						</pg:pager>	
						</c:if>
						<c:if test="${fn:length(form.publicadosAutonoma)<1}">
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

				<!-- Fin capa formu  -->		
						
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
			<h3 class="separacion07"><bean:message key="usuarioPublico.favoritos"/> <c:if test="${form.verMas[0]}">(<a href="<html:rewrite action="/VerUsuarioPublico/VerUsuarioPublico.do?usuario=${form.usuario}&actionFavorito=actionFavorito&volver=${form.volver}"/>"><bean:message key='usuarioPublico.ver.mas'/></a>)</c:if></h3>
			<div class="formu especial_comunidad"   >
			<c:if test="${fn:length(form.odesFavoritos)>0}">
			<pg:pager id="pgfavoritos" items="${fn:length(form.odesFavoritos)}" maxPageItems="4" maxIndexPages="10" scope="request" export="currentPageNumber=pageNumber">
							<c:forEach items="${form.odesFavoritos}" var="favorito">
							<pg:item id="pgfavoritos">	
							<!--    -->
						<div class="globo_blanco " >

						<div class="globo_blanco_01">
						<div class="globo_blanco_02">
						<div class="globo_blanco_03">
											
						<div class="item_comunidad i_favorito">
						<div class="li_izq_usuario"><img src="${ favorito.urlImagen}" alt="${ favorito.urlImagen}" width="50" height="50"/></div>
						<div class="li_der_usuario" >
						<a href="${favorito.urlFicha}" class="tit_obj" target="_blank">${favorito.titulo}</a><br />
						<p><a href="${favorito.urlPrevisualizar}&volver=${form.volver}" target="_blank"><bean:message key="usuarioPublico.favoritos.previsualizar"/></a></p>
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
		<c:if test="${fn:length(form.odesFavoritos)<1}">
		<div class="globo_blanco " >

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
				<!-- Fin capa formu  -->	
				
				
					

			
						
						</div>
					</div>
				</div>
						<!-- Boton de volver -->
					
						
			</div>
			<input class="boton_125"  type="submit"  value="<bean:message key="usuarios.solicitarAlta.volver"/>" />
			
						
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
</div>
</div>
</div>
</div>
					
</form>
	<!--		Fin del formulario		-->
	
</div>	
	
</div>

<!--		Fin de la capa plantilla_contenido		-->


</tiles:put>


</tiles:insert>