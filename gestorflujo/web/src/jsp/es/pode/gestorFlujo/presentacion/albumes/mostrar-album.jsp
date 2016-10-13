<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>


<tiles:insert definition="layout-gestor-flujo-con-style">

<tiles:put name="title" type="string">
      <bean:message key="title.Comun"/>
</tiles:put>


<tiles:put name="body" type="string">

<%@ include file="/taglib-imports.jspf" %>


<!--		Inicio plantilla contenido		-->

<div class="plantilla_contenido"><!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
<jsp:include page="/layout/messages.jsp" flush="true" />

	<!--		Inicio del formulario principal		-->
<form id="albumesCUMostrarAlbumAltaModificacionForm" action='<html:rewrite action="/${initParam.url_enviarAlbum_altaModificacion}" />'
			method="post">
			
	<!--		INICIO GLOBO GRIS		-->
	

	
				
	<!--		INICIO CAJA DE FORMULARIO		-->
		<div id="formulario" >
					
		<!--		INICIO GLOBO Blanco		-->
				
		
		<div class="globo_blanco_01">
		<div class="globo_blanco_02">
		<div class="globo_blanco_03">
		
		<h3><bean:message key="gestorFlujo.envioAlbum.album"/></h3>
	  									
	  	<!-- 		Inicio Caja Tabla		 -->
	  	<div class="caja_tabla">
	  										
	  							
			<display:table name="${form.listaAlbumes}" uid="fila" requestURI=""  export="false" 
				sort="list" style="border:1;width:100%;" class="administracion_tareas"
				cellpadding="0" cellspacing="0" summary="${summary}">
												

				
				<!--		Columna de Albumes		-->
				<display:column sortProperty="idAlbum" style="valign:top;" 
					class="tar14" title="${albumValue}">${fila.titulo}</display:column>
				
				<display:column sortProperty="idAlbum" style="valign:top;" class="tar110" title="${albumValue}" >
					<html:link action="${initParam.url_enviarAlbum_altaModificacion}" >
					</html:link>
				</display:column>
				
				
				
				<!--  Columna de Modificar -->
				<display:column style="valign:top;" title="&nbsp;">
					<html:link
						action="${initParam.url_enviarAlbum_altaModificacion}?retorno=Modificar" >
						<bean:message key="gestorFlujo.mostrarOdes.modificar" />
					</html:link>
				</display:column>
				

				
				
					<!--  Columna de Eliminar -->
				<display:column style="valign:top;" title="&nbsp;">
					<html:link
						action="AlbumesCU/MostrarAlbumEliminar">
						<bean:message key="gestorFlujo.mostrarOdes.eliminar" />
					</html:link>
				</display:column>
				
				<!-- Fin columnas -->
				
				
				
				</display:table> <!-- </div> Se cierra en el displaytag.properties --> <!--		Fin Caja Tabla 		-->
										
				</div>
				</div>
				</div>
				</div>
				<!--		Fin GLOBO Blanco		-->
			
			
				<!--		FIN CAJA DE FORMULARIO		-->
				
				
	
				<!--		FIN GLOBO GRIS		-->

				
		
				</form>
				<!--		Fin del Formulario principal		-->  <!--		Inicio formulario secundario y boton Cancelar		-->
				
				<html:link
						action="${initParam.url_enviarAlbum_altaModificacion}?retorno=Modificar">
						<bean:message key="gestorFlujo.envioAlbum.crearAlbum" />
					</html:link>
	
				<!--		Fin formulario secundario		--></div>

				<!--		Fin de la capa plantilla_contenido		-->

		</tiles:put>

</tiles:insert>