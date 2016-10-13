<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<html:xhtml/>
<tiles:insert definition="layout-administrador">

<tiles:put name="title" type="string">
	<bean:message key="title.Admnistracion"/>
</tiles:put>

<tiles:put name="body" type="string">
<bean:define id="contenidoInapSession" name="<%=es.pode.gestorFlujo.presentacion.contenidoInapropiado.ContenidoInapSession.SESSION_KEY%>" scope="session"/>
<!-- *****************************   Inicio  Plantilla contenido      ********************************** -->
<div class="plantilla_contenido"><!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
<jsp:include page="/layout/messages.jsp" flush="true" /> <!-- Inicio  PESTANIAS de FICHA -->
	


<h2><bean:message key="gestorflujo.error.cabeceraDetalle"/></h2>
<form method="post" action="<html:rewrite action="/ContenidoInapropiado/ContenidoInapropiado"/>" >
<!--	SOBRE ESTE HAY Q ITERAR!!  INICIO GLOBO GRIS   -->
	<c:forEach var="dato" items="${form.contenidos}">
		<!--  INICIO GLOBO GRIS   -->
		<div class="globo_gris conmargen" >
			<div class="globo_gris_01">
				<div class="globo_gris_02">
					<div class="globo_gris_03">
					<!--  INICIO CAJA DE FORMULARIO   -->
					<div id="formulario" >
						<!-- fecha -->
						<div class="fila_de_tabla">
		  					<div class="contenedor_izquierda_may" >
		  						<div class="text">
		  							<span class="tipo_label">
		  								<bean:message key="gestorflujo.verreporte.fecha"/>
		  							</span></div></div>
		 					<div class="contenedor_derecha">
								<div class="text"><p>
									<fmt:formatDate value="${dato.fecha.time}" type="date" pattern="dd/MM/yyyy"/></p></div>
							</div>
							<div class="linea_separadora"></div>
							<br class="oculto" />
						</div>
						<!-- -->
						<!-- usuario -->						
		  				<div class="fila_de_tabla">
		  					<div class="contenedor_izquierda_may" >
		  						<div class="text"><span class="tipo_label">
		  							<bean:message key="gestorflujo.verreporte.usuario"/>
		  						</span></div></div>
							<div class="contenedor_derecha">
								<div class="text"><p>
									${fn:escapeXml(dato.usuario)}
								</p></div></div>
							<div class="linea_separadora"></div>
							<br class="oculto" />
						</div>
						<!-- -->
						<!-- comentario -->
						<div class="fila_de_tabla">
		  					<div class="contenedor_izquierda_may" >
		  						<div class="text"><span class="tipo_label">
		  						<bean:message key="gestorflujo.verreporte.comentario"/></span></div>
		 					</div>
							<div class="contenedor_derecha">
								<div class="text"><p>${fn:escapeXml(dato.comentario)}</p></div>
							</div>
							<div class="linea_separadora"></div>
							<br class="oculto" />
						</div>
						<!-- -->
						
						<!-- identificador -->
		  				<div class="fila_de_tabla">
		  					<div class="contenedor_izquierda_may" >
		  						<div class="text"><span class="tipo_label"><bean:message key="gestorflujo.verreporte.identificador"/></span></div>
		 					</div>
							<div class="contenedor_derecha">
								<div class="text"><p>${fn:escapeXml(dato.idOde)}</p></div>
							</div>
							<div class="linea_separadora"></div>
							<br class="oculto" />
						</div>
						<!-- -->
					
						</div>
		
						<!--  FIN CAJA DE FORMULARIO   -->
					</div>
				</div>
			</div>
		</div>
		<!--  FIN GLOBO GRIS   -->
	</c:forEach>

<!-- Inicio Botones  -->
<fieldset class="tipo ft_centrada">
	<input class="boton_125"  type="submit"  value="<bean:message key="gestorflujo.verreporte.Volver"/>" />
</fieldset>
<!-- Fin Botones  -->
<!-- Fin Botones  -->
</form>



</div>
<!-- Fin plantilla CONTENIDO -->
</tiles:put>

</tiles:insert>

