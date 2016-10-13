<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>


<tiles:insert definition="layout-gestor-flujo-con-style">

	<tiles:put name="title" type="string">
		<bean:message key="title.Comun" />
	</tiles:put>


	<tiles:put name="body" type="string">
	
		<%@ include file="/taglib-imports.jspf"%>


		<!--		Inicio plantilla contenido		-->

		<div class="plantilla_contenido"><!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
		<jsp:include page="/layout/messages.jsp" flush="true" />
		
		<h2><bean:message key="gestorFlujo.envioAlbum.envioAlbum" /></h2>
		
		<!--		Inicio del formulario principal		-->
		<form id="envioAlbumCUListarAlbumesEnviarForm" action='<html:rewrite action="/${initParam.url_enviarAlbum_aceptar}" />'
			method="post">
		
		<!--		INICIO GLOBO GRIS		-->
		<div class="globo_gris">
		<div class="globo_gris_01">
		<div class="globo_gris_02">
		<div class="globo_gris_03"><!--		INICIO CAJA DE FORMULARIO		-->
		
			<div class="formu" >
			<p class="parrafo_comun" id="separacion06" ><bean:message key="gestorFlujo.envioAlbum.seleccioneAlbum" /></p>
				<div class="caja_tabla bordeada" >
				<display:table name="${form.listaAlbumes}" uid="fila" requestURI="" export="false"
					sort="list" style="border:1;width:100%;" class="administracion_tareas" 
					cellpadding="0" cellspacing="0"	summary="${summary}"
					decorator="es.pode.gestorFlujo.presentacion.objetosPersonales.envioAlbum.ChequearAlbumOde">
				
					<tr>
					<th id="esp_coloreada1"><label for="Coleccion01" class="oculto" >Seleccione </label>&nbsp;	</th>
					<th id="esp_coloreada"><span><bean:message key="gestorFlujo.envioAlbum.album" /></span></th>
					</tr>
		
					<display:setProperty name="css.tr.odd" value="tr_gris" />
					<display:setProperty name="css.tr.even" value="tr_blanco" />
					<display:setProperty name="basic.show.header" value="true" />

					<label for="Coleccion03" class="oculto" >Seleccione </label>
					<input type="radio" class="rad_col"   id="Coleccion03" /></td>
					<td valign="top" class="tar2"><span>${fila.titulo}</span></td>
					
					
					
					
			<!--		Columna de checkbox		-->
			<display:column style="valign:top;" class="sin_b" property="check" title="" />
		
			<!--		Columna de Nombre Album		-->

			<bean:define id="albumValue">
				<bean:message key="gestorFlujo.envioAlbum.album" />
			</bean:define>
			<display:column sortProperty="idAlbum" style="valign:top;"
				class="tar14" title="${albumValue}">${fila.titulo}</display:column>

		</display:table> <!-- </div> Se cierra en el displaytag.properties --> <!--		Fin Caja Tabla 		-->

		</div>
		</div>
		</div>
		</div>

		<!--		Fin GLOBO Blanco		--></div>

		<!--		FIN CAJA DE FORMULARIO		--></div>
		

		<!--		Boton Aceptar		--> 
		
		<input class="boton_125_de_2 tipo" type="submit" value="<bean:message key='gestorFlujo.aceptar'/>" />
		
		</form>

		<!--		Fin del Formulario principal		--> <!--		Inicio formulario secundario y boton Cancelar		-->

		<form
			action='<html:rewrite action="EnvioAlbumCU/ListarAlbumesCancelar"/>'
			method="post"><input class="boton_125_de_2_izq tipo"
			type="submit" value="<bean:message key='gestorFlujo.cancelar'/>" />
		</form>
		


		<!--		Fin formulario secundario		--></div>

		<!--		Fin de la capa plantilla_contenido		-->


	</tiles:put>


</tiles:insert>
