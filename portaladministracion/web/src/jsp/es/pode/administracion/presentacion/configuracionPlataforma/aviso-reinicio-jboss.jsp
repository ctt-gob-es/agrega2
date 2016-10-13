<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<tiles:insert definition="layout-administrador">
<tiles:put name="title" type="string">
	<bean:message key="title.Comun"/>
</tiles:put>
<tiles:put name="body" type="string">

<link rel="stylesheet" media="screen" href="<rewrite:rewrite url="static/css/hoja_de_estilo.css"/>" type="text/css" />

<script type="text/javascript" src="<rewrite:rewrite url="static/js/plantilla.js"/>"></script>



<%@ include file="/taglib-imports.jspf" %>

<%@ include file="/es/pode/administracion/presentacion/configuracionPortal/configuracion/configuracion-portal-vars.jspf" %>



<!--  INICIO CAPA MADRE   -->
<div id="capa_madre">


<!-- Inicio plantilla contenido  -->
<div class="plantilla_contenido">
<!-- ## Para pintar los mensajes de error ## -->
	<jsp:include page="/layout/messages.jsp" flush="true" />
	
<form method="post" action="<html:rewrite action="/ConfiguracionPlataformaCU/AvisoReinicioJbossRespuesta.do"/>">	


<input type="hidden" name="categoria" value="${form.categoria}"/>


<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div id="formulario" >

			 	<div class="fila_de_tabla">
  						<div class="text" style="text-align:center">
  						<br>
  						<bean:message key="configPlataforma.avisoRequiereReinicioJboss" />
  						</br>  						
  						</br>
  						
						<c:forEach items="${form.nombresPropiedades}" var="propiedad" varStatus="status">
							
							<c:if test="${form.requiereReinicio[status.index]=='true'}">
							
								<c:if test="${form.instanciaJboss[status.index]!='global'}">	
									<strong>${propiedad} (${form.instanciaJboss[status.index]})</strong>
								</c:if>
								<c:if test="${form.instanciaJboss[status.index]=='global'}">	
									<strong>${propiedad}</strong>
								</c:if>
								
							</c:if>
							<br>			
							<input type="hidden" name="nombresPropiedades" value="${propiedad}"/>
							<input type="hidden" name="instanciaJboss" value="${form.instanciaJboss[status.index]}"/>
							<input type="hidden" name="nuevosValores" value="${form.nuevosValores[status.index]}"/>
						
						</c:forEach>
						
  						</div>
					<br class="oculto" />
				</div>
				<br />
				</div>
				<!--  FIN CAJA DE FORMULARIO   -->
			</div>

		</div>
	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->

<!-- Inicio Botones  -->
<!-- Inicio Botones  -->

<fieldset class="tipo">
<input class="boton_125_de_2_izq" name="action" type="submit"  value="<bean:message key="comun.cancelar" />" />
<input class="boton_125_de_2"  name="action" type="submit"  value="<bean:message key="comun.aceptar"/>"  />
</fieldset>
	
</form>

</fieldset>
</div>
</div>


</div>
<!-- Fin plantilla contenido  -->
<!-- Fin plantilla contenido  -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->


</div>

</tiles:put>
</tiles:insert>
