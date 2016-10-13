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
		
		<h2><bean:message key="gestorFlujo.mostrarOdes.enviarAGrupo" /></h2>
		
		<!--		Inicio del formulario principal		-->
		<form id="odeGruposCUMostrarGruposUsuarioAceptarForm" action='<html:rewrite action="/${initParam.url_odesGrupo_aceptar}" />'
			method="post">
		
		<!--		INICIO GLOBO GRIS		-->
		<div class="globo_gris">
		<div class="globo_gris_01">
		<div class="globo_gris_02">
		<div class="globo_gris_03">
		
		<!--		INICIO CAJA DE FORMULARIO		-->
		<div class="formu" >
			<p class="parrafo_comun" id="separacion06" ><bean:message key="gestorFlujo.mostrarGrupos.seleccioneGrupo" /></p>
			
				<tr>
				<th id="esp_coloreada"><bean:define id="grupoValue"><span><bean:message key="gestorFlujo.mostrarGrupos.grupos" /></span></bean:define></th>
				</tr>
				
			<div class="caja_tabla bordeada">
			<display:table name="${form.listaGrupos}" uid="fila" requestURI="" export="false"
				sort="list" style="margin-bottom:10px;" class="administracion_tareas" 
				cellpadding="0" cellspacing="0"	summary="${summary}"
				decorator="es.pode.gestorFlujo.presentacion.objetosPublicados.odeGrupos.ChequearGruposUsuario">
			
			<display:setProperty name="css.tr.odd" value="tr_gris" />
			<display:setProperty name="css.tr.even" value="tr_blanco" />
			<display:setProperty name="basic.show.header" value="true" />
			
			<!--		Columna de checkbox		-->
			
			<display:column style="valign:top;" class="sin_b" property="check" title="" />
			
			<!--		Columna de Nombre Grupos		-->
			<display:column sortProperty="nombre" style="valign:top;"
				class="tar14" title="${grupoValue}">${fila.nombre}
			</display:column>
			
				</span>
			</tr>
			
			</display:table> <!-- </div> Se cierra en el displaytag.properties --> <!--		Fin Caja Tabla 		-->
			
			</div>
		</div> 
			
		</div>
		</div>
		</div>
		</div>

		<!--  FIN GLOBO GRIS   --><!--  FIN GLOBO GRIS   -->

		<!-- Inicio Botones  -->
		<input class="boton_125_de_2 tipo" type="submit" value="<bean:message key='gestorFlujo.aceptar'/>" />
		</form>
		<form
			action='<html:rewrite action="OdeGruposCU/MostrarGruposUsuarioCancelar"/>'
			method="post"><input class="boton_125_de_2_izq tipo"
			type="submit" value="<bean:message key='gestorFlujo.cancelar'/>" />
		</form>
		<!-- Fin Botones  -->
		
		</div>
		<!-- Fin plantilla contenido  -->



	</tiles:put>


</tiles:insert>
