<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="pg" %>

<html:xhtml/>
<tiles:insert definition="layout-administrador">

	<tiles:put name="title" type="string">
		<bean:message key="title.Admnistracion" />
	</tiles:put>
	
	<tiles:put name="body" type="string">
		<!--************************          Inicio plantilla contenido       ****************************-->
		<div class="plantilla_contenido">
			<jsp:include page="/layout/messages.jsp" flush="true" />

			<h2><bean:message key="gestorFlujo.mostrarOdes.publicar" /></h2>		

			<form method="post"	action="<html:rewrite action="CalcularDuplicadosCU/ListarDuplicadosAceptar" />">
			
			<div class="globo_gris">
				<div class="globo_gris_01">
				<div class="globo_gris_02">
				<div class="globo_gris_03"><!--  INICIO CAJA DE FORMULARIO   -->
					<div id="formulario">
						<h3><bean:message key="gestorFlujo.mostrarOdes.odesSimilares" /></h3>
						
						<pg:pager id="pgduplicados" items="${fn:length(form.listaDuplicados)}" maxPageItems="4" maxIndexPages="10" scope="request" export="currentPageNumber=pageNumber">	
							<ul id="lista_de_odesduplicados">
							<c:if test="${fn:length(form.listaDuplicados)>0}">
							<c:forEach items="${form.listaDuplicados}" var="duplicados">
							<pg:item id="pgduplicados">	
	
							<!--  INICIO GLOBO AZUL   --> <!--  INICIO GLOBO AZUL   -->
							<div class="globo_blanco gb_ie" style="margin-top:5px;">
							<div class="globo_blanco_01">
							<div class="globo_blanco_02">
							<div class="globo_blanco_03">
		
							<div class="item_com item_adm">
								<div class="li_izq_usuario">
									<img src="${duplicados.urlImagen }" alt="${duplicados.titulo}"/>
								</div>
								<div class="li_der_usuario"><span class="nomb"><bean:message key="titulo" />: ${duplicados.titulo }</span>
									<p><bean:message key="Similitud" /> ${duplicados.similitud }</p>
									<p><bean:message key="gestorFlujo.importarURL.URL" />: <a href="${duplicados.urlPrevisualizacion }" target="_blank"><bean:message key="gestorFlujo.urlPrevisualizacion" /></a></p>
								</div>
							<br class="limpiar" />
							</div>
					
							</div>
							</div>
							</div>
							</div>
							<!--  FIN GLOBO AZUL   --> <!--  FIN GLOBO AZUL   --> 
		
							</pg:item>
							</c:forEach>
							</c:if>
							</ul>
							<hr/>
				
							<pg:index id="pgduplicados">
		                 		<div class="paginacion" id="separacion5">
		                		 <ul id="navlist">
					     		  <pg:prev id="pgduplicados">
		                       	 	<li><a href="${pageUrl }"><bean:message key="gestorFlujo.anterior"/></a></li>
		                  		  </pg:prev>

		                	 <pg:pages id="pgduplicados">
			                	<c:choose>
			                 	<c:when test="${pageNumber eq currentPageNumber}">
				              	  <li>${pageNumber }</li>
			                 	</c:when>
				             	<c:otherwise>
							      <li><a href="${pageUrl }">${pageNumber }</a></li>
				             	</c:otherwise>
				             	</c:choose>
		                 	</pg:pages>
						  	<pg:next id="pgduplicados">
						  		<li><a href="${pageUrl }"><bean:message key="gestorFlujo.siguiente"/></a></li>
						  	</pg:next>
				         	</ul>
						 	</div>
							</pg:index>
	          		  	</pg:pager>
		
					</div><!--  FIN CAJA DE FORMULARIO   -->
				</div>
				</div>
				</div>
			</div>
		<!--  FIN GLOBO GRIS   --> <!--  FIN GLOBO GRIS   --> 
		
		<!-- Inicio Botones  -->	<!-- Inicio Botones  -->
		<input type="hidden" name="idODE" value="${form.idODE}"/>
		<input type="hidden" name="esDespublicado" value="${form.esDespublicado}"/>
		<input type="hidden" name="titulo" value="${form.titulo}"/>
		<input type="hidden" name="idUsuario" value="${form.idUsuario}"/>
		<input type="hidden" name="action" value="publicar"/>
		<input type="hidden" name="retorno" value="${form.retorno}" />

		
		<input class="boton_125_de_2 tipo" type="submit" value="<bean:message key="gestorFlujo.aceptar"/>" />
		</form>
		
		<form
			action="<html:rewrite action="${initParam.url_duplicados_cancelar}"/>"
			method="post">
			<input type="hidden" name="retorno" value="${form.retorno}" />
			<input class="boton_125_de_2_izq tipo"
			type="submit" value="<bean:message key="gestorFlujo.cancelar"/>" />
		</form>
		<!-- Fin Botones  -->
			
		
		</div>

		<!-- Fin plantilla CONTENIDO -->
	</tiles:put>

</tiles:insert>
