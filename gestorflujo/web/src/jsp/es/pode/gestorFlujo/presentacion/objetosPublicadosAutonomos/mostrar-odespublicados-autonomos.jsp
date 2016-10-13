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

	<!-- *****************************   Inicio  Plantilla contenido      ********************************** -->
	<div class="plantilla_contenido"><!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
		<jsp:include page="/layout/messages.jsp" flush="true" /> <!-- Inicio  PESTANIAS de FICHA -->
	
		<form method="post" action="" >
			<input type="hidden" name="espacioLibre" value="${form.espacioLibre}"/>
			
		<!-- Inicio Lateral Interior  -->
		<div class="lateral_destacados" id="sin_mtop">
			<div class="nube_destacada inicial_n">
				<div class="nub_d">
					<p><bean:message key="utilizar"/> <strong>${form.porcentajeMemoriaCubierta}<bean:message key="porcentaje"/></strong> <bean:message key="limite"/> ${form.cuotaUsuario} <bean:message key="Mbyte"/>.</p>
				</div>
			</div>
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
</div> <!-- Fin Lateral Interior  -->
		
	
	<!--  INICIO GLOBO GRIS   --><!--  INICIO GLOBO GRIS   -->
	<div class="globo_gris" id="contenido_medio" >
		<div class="globo_gris_01">
		<div class="globo_gris_02">
		<div class="globo_gris_03" >
			<div id="menu_pestanias">
			<ul>
				<li><a href="<html:rewrite action="${initParam.url_objetosPersonales}"/>"><span><bean:message key="gestorFlujo.mostrarOdes.personales" /></span></a></li>
				<li><a href="<html:rewrite action="${initParam.url_objetosPropuestos }"/>"><span><bean:message key="gestorFlujo.mostrarOdes.propuestos" /></span></a></li>
				<li><a href="<html:rewrite action="${initParam.url_objetosPublicados }"/>"><span><bean:message key="gestorFlujo.mostrarOdes.publicados" /></span></a></li>
				<li class="pest_activa"><a href="<html:rewrite action="${initParam.url_objetosPublicadosAutonomos }"/>"><span><bean:message key="gestorFlujo.mostrarOdes.autopublicados" /></span></a></li>
				<li class="final_li"><a href="<html:rewrite action="${initParam.url_objetosCompartidos }"/>"><span><bean:message key="gestorFlujo.mostrarOdes.compartidos" /></span></a></li>
			</ul>
			</div>	
		
			<div id="formulario">
				<pg:pager id="pgautopublicados" url="" items="${fn:length(form.listaODES)}" maxPageItems="4" maxIndexPages="10" scope="request" export="currentPageNumber=pageNumber">	
				<ul id="lista_de_odesautopublicados">
				<c:if test="${fn:length(form.listaODES)>0}">
				<c:forEach items="${form.listaODES}" var="autopublicados">
				<pg:item id="pgautopublicados">	
		
				<!--  INICIO GLOBO AZUL   --><!--  INICIO GLOBO AZUL   -->
				<div class="globo_blanco gb_ie" >
				<div class="globo_blanco_01">
				<div class="globo_blanco_02">
				<div class="globo_blanco_03">
			
				<div class="item_objeto c_p_autopublicado">
					<div class="li_izq_usuario">
						<img src="${form.urlImagen }" alt="${autopublicados.titulo}" />
					</div>
					
					<div class="li_der_usuario" >
						<a href=${autopublicados.urlPrevisualizar} class="tit_obj" target="_blank">${autopublicados.titulo}</a>
						<p>${fn:escapeXml(autopublicados.tamaino)} <bean:message key="Mbyte"/>
						<!--  ******************** VER HISTORIAL ***********************-->
						<html:link target="_blank" action="${initParam.url_objetosPersonales_mostrar_historial}?idODE=${autopublicados.idODE}&idUsuario=${idUsuarioLocal}&titulo=${autopublicados.titulo}">
							<bean:message key="gestorFlujo.mostrarOdes.historialODE" />
						</html:link>
						<!--  ******************** DESPUBLICAR ***********************-->
						<html:link	action="${initParam.url_objetosPublicadosAutonomos_despublicar}?idODE=${autopublicados.idODE}&idUsuario=${idUsuarioLocal}&titulo=${autopublicados.titulo}">
							<bean:message key="gestorFlujo.mostrarOdes.despublicarAutonomos" />
						</html:link>
						<!--  ******************** PREVISUALIZAR ***********************-->
						<a href=${autopublicados.urlPrevisualizar} target="_blank"><bean:message key="gestorFlujo.mostrarOdes.previsualizar" /></a>
						
						</p>
					</div>
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
			
				
			 <pg:index id="pgautopublicados">
                 <div class="paginacion" id="separacion5">
                 <ul id="navlist">
			       <pg:prev id="pgautopublicados">
                        <li><a href="${pageUrl }"><bean:message key="gestorFlujo.anterior"/></a></li>
                   </pg:prev>

                 <pg:pages id="pgautopublicados">
	                 <c:choose>
	                 <c:when test="${pageNumber eq currentPageNumber}">
		                <li>${pageNumber }</li>
	                 </c:when>
		             <c:otherwise>
					      <li><a href="${pageUrl }">${pageNumber }</a></li>
		             </c:otherwise>
		             </c:choose>
                 </pg:pages>
				  <pg:next id="pgautopublicados">
				  		<li><a href="${pageUrl }"><bean:message key="gestorFlujo.siguiente"/></a></li>
				  </pg:next>
		         </ul>
				 </div>
				  </pg:index>
	            </pg:pager> 
				
			
			
				<c:if test="${fn:length(form.listaODES)<1}">
					<div class="globo_blanco gb_ie" >
					<div class="globo_blanco_01">
					<div class="globo_blanco_02">
					<div class="globo_blanco_03">
						<div class="item_objeto c_p_autopublicado">
							<p class="parrafo_com"><bean:message key="gestorFlujo.mostrarOdes.noHay"/></p>
						</div>
					</div>
					</div>
					</div>
					</div>
				</c:if>
					
			</div><!-- fin formulario -->				
		
	</div>
	</div>
	</div>
	</div>
	<!--  FIN GLOBO GRIS   --><!--  FIN GLOBO GRIS -->		
					
	</form>			
	</div>				
	<!-- Fin plantilla CONTENIDO -->			
					
</tiles:put>

</tiles:insert>
