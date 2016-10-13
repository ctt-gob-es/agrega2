<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tags/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tags/tags-visualizadorContenidos.tld" prefix="url"%>

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
<form id="verContactoVerContactoBuscarForm" action='<html:rewrite action="/VerContacto/VerContactoObtenerTextoUsuario"/>' method="post" >
<input type="hidden" name="contacto" value="${form.contacto}"/>
<input type="hidden" id="vuelta" name="vuelta" value="${form.vuelta}"/>
<input type="hidden" id="id" name="id" value="${form.id}"/>
<input type="hidden" id="textoBuscado" name="textoBuscado" value="${form.textoBuscado}" />
<input type="hidden" id="busqueda" name="busqueda" value="${form.busqueda}" />
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

<!--   -->
<div class="nube_destacada_blanca">
<div class="nub_d ">
<strong class="tipo_h3"><bean:message key="perfilPublico.widget"/></strong>


<div class="ficha" id="compartir" >
<label for="comp_url" ><bean:message key="perfilPublico.url"/></label>
<input type="text" name="comp_url" id="comp_url" readonly="readonly"  title="<bean:message key="perfilPublico.url.introduzca"/>" onclick="SelectAll(this);" value="${form.urlUsuario }" />
<br />
<label for="comp_insertar" ><bean:message key="perfilPublico.insertar"/></label>

<textarea name="comp_insertar" id="comp_insertar" readonly="readonly"   readonly="readonly" onclick="SelectAll(this);">${form.codigoEmbeb }</textarea>
<br class="limpiar" />
</div>





</div>
</div>
<!--   -->

<!--   -->
<c:if test="${form.mostrar[1]}">

<div class="nube_destacada_blanca">
<div class="nub_d ">
<strong class="tipo_h3"><bean:message key="usuarioPublico.itinerarios.aprendizaje"/> <c:if test="${form.verMas[1]}">(<a href="<html:rewrite action="/VerContacto/VerContacto.do?contacto=${form.contacto}&vuelta=${form.vuelta}&actionGrupos=actionGrupos"/>"><bean:message key='usuarioPublico.ver.mas'/></a>)</c:if></strong>
<c:if test="${fn:length(form.grupos)>0}">
<pg:pager id="pggrupos" items="${fn:length(form.grupos)}" maxPageItems="4" maxIndexPages="10" scope="request" export="currentPageNumber=pageNumber">
<c:forEach items="${form.grupos}" var="grupo">
<pg:item id="pggrupos">

<ul class="lista_usuario">

<li >
<div class="div_li">
<div class="li_izq_usuario esp_itinerarios"><img src="${grupo.imagenGrupo}" alt="${grupo.imagenGrupo}" width="50" height="50"/></div>

<div class="li_der_usuario" >
<a href="<html:rewrite action="/GestionGrupoPublico/GestionGrupoPublico.do?nombre=${grupo.nombre}"/>" id="${grupo.nombre}"  class="nomb">${grupo.nombre}</a>
<p>${grupo.descripcion}</p>

</div>
<br class="limpiar" />
</div>
</li>

</ul>
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
<c:if test="${fn:length(form.grupos)<1}">
<ul class="lista_usuario">
<li >
<p class="parrafo_com"><bean:message key="grupos.no.existe.actualmente"/></p>
</li>
</ul>
</c:if>


<!--   -->


</div>
</div>

</c:if>
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
  	
			<div class="formu"   >
			<!--  INICIO GLOBO AZUL   -->
			<!--  INICIO GLOBO AZUL   -->
	
			<div class="globo_blanco gb_ie" >
				<div class="globo_blanco_01">
					<div class="globo_blanco_02">
						<div class="globo_blanco_03">
						<div class="item_comunidad nombre_contacto">
						<img class="usu_flotante" src="${form.imagenContacto}" alt="${form.imagenContacto}" width="65" height="65"/>
						<span class="tit_obj">${form.nombreContacto}</span><br />
						<c:if test="${!(form.centroEducativo eq null)}"><p class="parrafo_com"><em><bean:message key="usuarioPublico.nombreCentro"/></em><br />${form.centroEducativo}</p></c:if>
						<c:if test="${!(form.descripcionUsuario eq null)}"><p class="parrafo_com"><em><bean:message key="grupoPublico.descripcion"/></em><br />${form.descripcionUsuario}</p></c:if>
						<c:if test="${(form.asociado) && !(form.contacto eq form.usuario)}">
							<p><a href="<html:rewrite action="/EliminarContacto/EliminarContacto.do?id=${form.id}&usuarioContacto=${form.contacto }&vuelta=Gestion"/>" id="${form.contacto}"><bean:message key="grupos.contacto.eliminar.agenda"/></a></p>
						</c:if>
						<c:if test="${(!form.asociado)&& !(form.contacto eq form.usuario)}">
							<p><a href="<html:rewrite action="/AnadirAAgenda/AnadirAAgenda.do?usuarioContacto=${form.contacto }"/>" id="${form.contacto}"/><bean:message key="usuarioPublico.anadir.contacto"/></a></p>
						</c:if>
						
						</div>
			
			<!--  INICIO GLOBO AZUL   -->
			<!--  INICIO GLOBO AZUL   -->
			<c:if test="${form.mostrar[2]}">
			
			<div class="globo_gris conmargen"  >
		<div class="globo_gris_01">
		<div class="globo_gris_02">
		<div class="globo_gris_03" >
		
			<h3 class="separacion07"><bean:message key="usuarioPublico.publicadosManual"/> <c:if test="${form.verMas[2]}">(<a href="<html:rewrite action="/VerContacto/VerContacto.do?contacto=${form.contacto}&vuelta=${form.vuelta}&actionManual=actionManual"/>"><bean:message key='usuarioPublico.ver.mas'/></a>)</c:if></h3>	
					<div class="formu especial_comunidad"   >	
							<c:if test="${fn:length(form.objetosManual)>0}">
							<pg:pager id="pgmanuales" items="${fn:length(form.objetosManual)}" maxPageItems="4" maxIndexPages="10" scope="request" export="currentPageNumber=pageNumber">
							<c:forEach items="${form.objetosManual}" var="manual">
							<pg:item id="pgmanuales">	
						<!--    -->
						<div class="globo_blanco conmargen" >
						<div class="globo_blanco_01">
						<div class="globo_blanco_02">
						<div class="globo_blanco_03">
						<div class="item_comunidad i_publicado">

						<div class="li_izq_usuario"><img src="${manual.urlImagen}" alt="${manual.urlImagen}" width="70" /></div>
						<div class="li_der_usuario" >
						<a href="${manual.urlFicha}" class="tit_obj" target="_blank">${manual.titulo}</a><br />
						<p><a href="${manual.urlPrevisualizar}" target="_blank"><bean:message key="usuarioPublico.favoritos.previsualizar"/></a></p>
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
					<c:if test="${fn:length(form.objetosManual)<1}">
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
			<h3 class="separacion07"><bean:message key="usuarioPublico.publicadoAutonomo"/> <c:if test="${form.verMas[3]}">(<a href="<html:rewrite action="/VerContacto/VerContacto.do?contacto=${form.contacto}&vuelta=${form.vuelta}&actionAuto=actionAuto"/>"><bean:message key='usuarioPublico.ver.mas'/></a>)</c:if></h3>	
				<div class="formu especial_comunidad"   >	
						<c:if test="${fn:length(form.objetosPublicados)>0}">
						<pg:pager id="pgautos" items="${fn:length(form.objetosPublicados)}" maxPageItems="4" maxIndexPages="10" scope="request" export="currentPageNumber=pageNumber">
							<c:forEach items="${form.objetosPublicados}" var="auto">
							<pg:item id="pgautos">			
						<!--    -->

						<div class="globo_blanco conmargen" >
						<div class="globo_blanco_01">
						<div class="globo_blanco_02">
						<div class="globo_blanco_03">
					
						<div class="item_comunidad i_autopublicado">
						<div class="li_izq_usuario"><img src="${auto.urlImagen}" alt="${auto.urlImagen}" width="70" /></div>
						<div class="li_der_usuario" >
						
						<a href="${auto.urlPrevisualizar}" class="tit_obj" target="_blank">${auto.titulo}</a><br />
						<p><a href="${auto.urlPrevisualizar}" target="_blank"><bean:message key="usuarioPublico.favoritos.previsualizar"/></a></p>
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
						<c:if test="${fn:length(form.objetosPublicados)<1}">
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
			
		</c:if>
		<!--  FIN GLOBO AZUL   -->
		<!--  FIN GLOBO AZUL   -->
			
			<!--  INICIO GLOBO AZUL   -->
			<!--  INICIO GLOBO AZUL   -->
		<c:if test="${form.mostrar[0]}">
		
		<div class="globo_gris conmargen"  >
		<div class="globo_gris_01">
		<div class="globo_gris_02">
		<div class="globo_gris_03" >
			<h3 class="separacion07"><bean:message key="usuarioPublico.favoritos"/> <c:if test="${form.verMas[0]}">(<a href="<html:rewrite action="/VerContacto/VerContacto.do?contacto=${form.contacto}&vuelta=${form.vuelta}&actionFavorito=actionFavorito"/>"><bean:message key='usuarioPublico.ver.mas'/></a>)</c:if></h3>
			<div class="formu especial_comunidad"   >
			<c:if test="${fn:length(form.favoritos)>0}">
			<pg:pager id="pgfavoritos" items="${fn:length(form.favoritos)}" maxPageItems="4" maxIndexPages="10" scope="request" export="currentPageNumber=pageNumber">
							<c:forEach items="${form.favoritos}" var="favorito">
							<pg:item id="pgfavoritos">	
							<!--    -->
						<div class="globo_blanco " >

						<div class="globo_blanco_01">
						<div class="globo_blanco_02">
						<div class="globo_blanco_03">
											
						<div class="item_comunidad i_favorito">
						<div class="li_izq_usuario"><img src="${ favorito.urlImagen}" alt="${ favorito.urlImagen}" width="70"/></div>
						<div class="li_der_usuario" >
						<a href="${favorito.urlFicha}" class="tit_obj" target="_blank">${favorito.titulo}</a><br />
						<p><a href="${favorito.urlPrevisualizar}" target="_blank"><bean:message key="usuarioPublico.favoritos.previsualizar"/></a></p>
						<p><a href="<html:rewrite action="/ListarGruposAsociados/ListarGruposAsociados.do?id_mec=${favorito.id_mec }&idioma=${favorito.idioma }&nombre=&vuelta=Contacto&titulo=${favorito.titulo }"/>"><bean:message key="usuarioPublico.favoritos.enviar.grupo"/></a></p>

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
		<c:if test="${fn:length(form.favoritos)<1}">
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
			</div>
			
		</c:if>
		<!--  FIN GLOBO AZUL   -->
		<!--  FIN GLOBO AZUL   -->	
		<c:if test="${!(form.busqueda eq null)}">
<input type="hidden" id="textoBusqueda" name="textoBusqueda" value="${form.textoBuscado}" />
		<input class="boton_125"  type="submit" name="volver"  value="<bean:message key="usuarios.solicitarAlta.volver"/>" />
		</c:if>	
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
	</div>
<!-- Fin plantilla contenido  -->
<!-- Fin plantilla contenido  -->
			

</tiles:put>
</tiles:insert>

