<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
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
<form method="post" id="" action="">
	<input type="hidden" name="form" property="retorno" value="objetosPersonales" />
	<input type="hidden" name="form" property="idUsuario" value="${form.idUsuario }"/>


<!-- Inicio Lateral Interior  -->
<div class="lateral_destacados" id="sin_mtop">

<!--   -->
<div class="nube_destacada inicial_n">
<div class="nub_d">
<p><bean:message key="utilizar"/> <strong>${form.porcentajeMemoriaCubierta}<bean:message key="porcentaje"/></strong> <bean:message key="limite"/> ${form.cuotaUsuario} <bean:message key="Mbyte"/>.</p>
</div>
</div>
<!--   -->

<ul class="listadebanners_peq">
<li id="des_int_01" >
<c:choose>
	<c:when test="${form.espacioLibre>'0'}">
		<a href="<html:rewrite action="/ObjetosPersonalesCU/MostrarODESPersonalesSubmit.do?action=Crear&espacioLibre=${form.espacioLibre}" />" type="submit" name="action" title='<bean:message key="gestorFlujo.mostrarOdes.crearObjeto"/>' id="boton_crear_objeto" ><span><bean:message key="gestorFlujo.mostrarOdes.crearObjeto"/></span></a>
	</c:when>
	<c:otherwise>
		<a href="" disabled="true" type="submit" name="action" title='<bean:message key="gestorFlujo.mostrarOdes.crearObjeto"/>' id="boton_crear_objeto" ><span><bean:message key="gestorFlujo.mostrarOdes.crearObjeto"/></span></a>
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
<!--   -->
<!--   -->
<div class="nube_destacada">
<div class="nub_d nub_d2">
<a href="#" id="flotantederecha"  onmouseover="this.style.cursor='pointer'" onfocus="this.blur();" onclick="document.getElementById('popup_mini').style.display = 'block' " onkeypress="document.getElementById('popup_mini').style.display = 'block'" ><bean:message key="gestorFlujo.album.queEsEsto"/></a>
<br class="oculto" />
<div id="popup_mini" >
<div class="pop_int">
<a class="cerrar_peque" onkeypress="document.getElementById('popup_mini').style.display = 'none'" onclick="document.getElementById('popup_mini').style.display = 'none' " ><span class="oculto">Cerrar</span>&nbsp;</a>
<strong><bean:message key="gestorFlujo.album.queEsEsto"/></strong>
<p><bean:message key="gestorFlujo.album.textoQueEsEsto"/></p>
</div>
</div>


<!-- ALBUMES -->
<h3><bean:message key="gestorFlujo.envioAlbum.album"/></h3>
<display:table name="${form.listaAlbumes}" uid="filaAlbum" requestURI=""  export="false" 
	sort="list" style="border:1;width:100%;" class="administracion_tareas"
	cellpadding="0" cellspacing="0" summary="${summary}">
	<display:column style="valign:top;">
		<ul id="lista_de_odespersonales">
			<td class="rojo"><a href="<html:rewrite action="${initParam.url_listarODEsAlbum }?idAlbum=${filaAlbum.id}"/>">${filaAlbum.titulo}  (${filaAlbum.numeroOdes })</a> </td>
			<td>
				<html:link action="${initParam.url_enviarAlbum_modificacion}?idAlbum=${filaAlbum.id}&titulo=${filaAlbum.titulo}&descripcion=${filaAlbum.descripcion }&retorno=objetosPersonales">
					<bean:message key="gestorFlujo.mostrarOdes.modificar" />
				</html:link>
			</td>
			<td><html:link action="${initParam.url_eliminarAlbum}?idAlbum=${filaAlbum.id}&titulo=${filaAlbum.titulo}&retorno=objetosPersonales">
					<bean:message key="gestorFlujo.mostrarOdes.eliminar" />
				</html:link>
			</td>
		</ul>
	</display:column> 
</display:table>
<html:link action="${initParam.url_altaAlbum}?retorno=objetosPersonales">
	<bean:message key="gestorFlujo.envioAlbum.crearAlbum" />
</html:link>

</div>
</div>


</div>
<!-- Fin Lateral Interior  -->


<!-- <h2>T�tulo XYZ</h2>  -->


<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" id="contenido_medio" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03" >
				<div id="menu_pestanias">
					<ul>
						<li class="pest_activa"><a href="<html:rewrite action="${initParam.url_objetosPersonales}"/>"><span><bean:message key="gestorFlujo.mostrarOdes.personales" /></span></a></li>
						<li><a href="<html:rewrite action="${initParam.url_objetosPropuestos }"/>"><span><bean:message key="gestorFlujo.mostrarOdes.propuestos" /></span></a></li>
						<li><a href="<html:rewrite action="${initParam.url_objetosPublicados }"/>"><span><bean:message key="gestorFlujo.mostrarOdes.publicados" /></span></a></li>
						<li><a href="<html:rewrite action="${initParam.url_objetosPublicadosAutonomos }"/>"><span><bean:message key="gestorFlujo.mostrarOdes.autopublicados" /></span></a></li>
						<li class="final_li"><a href="<html:rewrite action="${initParam.url_objetosCompartidos }"/>"><span><bean:message key="gestorFlujo.mostrarOdes.compartidos" /></span></a></li>
					</ul>
				</div>
  	
  	
			<div id="formulario">
			
			<pg:pager id="pgpersonales" url="" items="${fn:length(form.listadoOdesPersonalesTitulo)}" maxPageItems="4" maxIndexPages="10" scope="request" export="currentPageNumber=pageNumber">
			<ul id="lista_de_odespersonales">
			<!-- Tengo que pasar una fila de SIN ALBUM -->
			<c:if test="${fn:length(form.listadoOdesPersonalesTitulo)>0}">
			<c:forEach items="${form.listadoOdesPersonalesTitulo}" var="personales">
			<pg:item id="pgpersonales">	
			
  	
			
			<!--  INICIO GLOBO AZUL   --><!--  INICIO GLOBO AZUL   -->
			<div class="globo_blanco gb_ie" >
				<div class="globo_blanco_01">
					<div class="globo_blanco_02">
						<div class="globo_blanco_03">

						
						<div class="item_objeto c_p_personal">
						<div class="li_izq_usuario"><img src="${personales.urlImagen }" alt="${personales.titulo}" /></div>
						<div class="li_der_usuario" >
						
						<a href="<html:rewrite action="/ObjetosPersonalesCU/MostrarODESPersonalesPrevisualizar.do?idOde=${personales.idODE}&comunidadAgrega=false" />" class="tit_obj" target="_blank">${personales.titulo}</a>

						<p>${fn:escapeXml(personales.tamaino)} <bean:message key="Mbyte"/>
						<!--  ******************** VER HISTORIAL ***********************-->
						<html:link target="_blank" action="${initParam.url_objetosPersonales_mostrar_historial}?idODE=${personales.idODE}&idUsuario=${idUsuarioLocal}&titulo=${personales.titulo}">
							<bean:message key="gestorFlujo.mostrarOdes.historialODE" />
						</html:link>
						</p>
						<p>
						<!--  ******************** EXPORTAR (DESCARGAR) ***********************-->
						<html:link	action="${initParam.url_objetosPersonales_exportar}?idODE=${personales.idODE}&titulo=${personales.titulo}&retorno=objetosPersonales">
							<bean:message key="gestorFlujo.mostrarOdes.exportar" />
						</html:link>
						<!--  ******************** PROPONER ***********************-->
						<c:if test="${!el:esTaller()}">
							<html:link action="${initParam.url_objetosPersonales_proponer}?estaVersionandose=${personales.estaVersionandose}&idODE=${personales.idODE}&idUsuario=${idUsuarioLocal}&titulo=${personales.titulo}">
								<bean:message key="gestorFlujo.mostrarOdes.proponer" />
							</html:link>
						</c:if>
						<!--  ******************** AUTOPUBLICAR (PUBLICAR AUTONOMOS) ***********************-->
						<c:if test="${el:habilitadoPublicarAutonomo()}">
							<html:link	action="${initParam.url_objetosPersonales_publicarAutonomos}?idODE=${personales.idODE}&titulo=${personales.titulo}">
								<bean:message key="gestorFlujo.mostrarOdes.publicarAutonomos" />
							</html:link>
						</c:if>			
						<!--  ******************** PREVISUALIZAR ***********************-->
						<html:link action="${initParam.url_visualizar_personales}?idOde=${personales.idODE}&comunidadAgrega=false" target="blank">
							<bean:message key="gestorFlujo.mostrarOdes.previsualizar" />
						</html:link>
						<!--  ******************** CATALOGAR ***********************-->
						<a href="<rewrite:rewrite url="${initParam.url_objetosPersonales_catalogar}?identificador=${personales.idODE}&espacioLibre=${form.espacioLibre }&espacioODE=${personales.tamaino}&titulo=${personales.titulo}&catalogadorDirecto=CD"/>">
							<bean:message key="gestorFlujo.mostrarOdes.catalogar" />
						</a>
						<!--  ******************** MODIFICAR ***********************-->
						<c:choose>
							<c:when test="${form.espacioLibre>'0'}">
								<a href="<rewrite:rewrite url="${initParam.url_objetos}?identificador=${personales.idODE}&espacioLibre=${form.espacioLibre }&espacioODE=${personales.tamaino}"/>"><bean:message key="gestorFlujo.mostrarOdes.modificar" /></a>
							</c:when>
							<c:otherwise>
								<bean:message key="gestorFlujo.mostrarOdes.modificar" />
							</c:otherwise>
						</c:choose>
						<!--  ******************** ELIMINAR ***********************-->
						<html:link action="${initParam.url_objetosPersonales_eliminar}?identificador=${personales.idODE}&titulo=${personales.titulo}">
							<bean:message key="gestorFlujo.mostrarOdes.eliminar" />
						</html:link>					
						<!--  ******************** ACTUALIZAR ***********************-->
						<c:if test="${personales.estaVersionandose=='true'}">
							<html:link action="${initParam.url_actualizarVersionados}?idODE=${personales.idODE}&espacioLibre=${form.espacioLibre}">
								<bean:message key="gestorFlujo.mostrarOdes.actualizar" />
							</html:link>	
						</c:if>				
						</p>
						
					<!--  ******************** COMPARTIR/DESCOMPARTIR ***********************-->
						<p>
						<c:if test="${!el:esTaller()}">
							<c:if test="${personales.compartir}" >
								<html:link action="${initParam.url_compartir_descompartir_personal}?idODE=${personales.idODE}&compartidoSN=true"><bean:message key="gestorFlujo.mostrarOdes.descompartir" /></html:link>
							</c:if>
							<c:if test="${!personales.compartir}" >
								<html:link action="${initParam.url_compartir_descompartir_personal}?idODE=${personales.idODE}&compartidoSN=false"><bean:message key="gestorFlujo.mostrarOdes.compartir" /></html:link>
							</c:if>
						</c:if>
						<!--  ******************** ALBUM ***********************-->
						<!-- <span>Album XYZ (<a href="#" class="no_m">Cambiar</a>)</span></p>  -->
						<c:if test="${(personales.estaEnAlbum)==true}">
							<span>${personales.album} 
								<html:link action="${initParam.url_objetosPersonales_enviarAAlbum}?idODE=${personales.idODE}&idUsuario=${idUsuarioLocal}&retorno=objetosPersonales">
									(<bean:message key="gestorFlujo.envioAlbum.cambiar" />)
								</html:link></span></p>
						</c:if>
						<c:if test="${(personales.estaEnAlbum)==false}">
							<html:link
								action="${initParam.url_objetosPersonales_enviarAAlbum}?idODE=${personales.idODE}&idUsuario=${idUsuarioLocal}&retorno=objetosPersonales">
								<bean:message key="gestorFlujo.envioAlbum.envioAlbum" />
							</html:link>
						</c:if>
						
						<!-- fin elementos pastillas -->
						</div>
						<br class="limpiar_izq" />
						</div>

						</div>
					</div>

				</div>
		</div>
		<!--  FIN GLOBO AZUL   -->
		<!--  FIN GLOBO AZUL   -->
			
			
			</pg:item>
			</c:forEach>
			</c:if>
			</ul>
			<hr />
			
			 <c:if test="${fn:length(form.listadoOdesPersonalesTitulo)<1}">
			 	<div class="globo_blanco gb_ie" >
				<div class="globo_blanco_01">
				<div class="globo_blanco_02">
				<div class="globo_blanco_03">
					<p class="parrafo_com"><bean:message key="gestorFlujo.mostrarOdes.noHay"/></p>
				</div>
				</div>
				</div>
				</div>
			 </c:if>
			

			 <pg:index id="pgpersonales">
                 <div class="paginacion" id="separacion5">
                 <ul id="navlist">
			       <pg:prev id="pgpersonales">
                        <li><a href="${pageUrl }"><bean:message key="gestorFlujo.anterior"/></a></li>
                   </pg:prev>

                 <pg:pages id="pgpersonales">
	                 <c:choose>
	                 <c:when test="${pageNumber eq currentPageNumber}">
		                <li>${pageNumber }</li>
	                 </c:when>
		             <c:otherwise>
					      <li><a href="${pageUrl }">${pageNumber }</a></li>
		             </c:otherwise>
		             </c:choose>
                 </pg:pages>
				  <pg:next id="pgpersonales">
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
			<form id="buscarPersonalesCUListadoOdesPpersonalesPorTituloVolverForm" action="<html:rewrite action="/BuscarPersonalesCU/ListadoOdesPersonalesPorTituloVolver"/>" method="post" >
				<input class="boton_125"  type="submit"  value="<bean:message key='gestorFlujo.mostrarOdes.verTodos'/>" />
			</form>
		</fieldset>

</div>
<!-- Fin plantilla contenido  -->




</tiles:put>

</tiles:insert>


