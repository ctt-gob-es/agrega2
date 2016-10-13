<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html:xhtml/>
<tiles:insert definition="layout-gestor-flujo-con-style">

	<tiles:put name="title" type="string">
		<bean:message key="title.Comun" />
	</tiles:put>
	
	<tiles:put name="body" type="string">

	<!--*****  Inicio plantilla contenido ********-->
		<div class="plantilla_contenido">
			<jsp:include page="/layout/messages.jsp" flush="true" />

			<h2><bean:message key="gestorFlujo.mostrarOdes.eliminar" /></h2>
		
			<!-- Inicio del formulario principal -->

			<form action='<html:rewrite action="/${initParam.url_eliminarAlbum_aceptar}?idAlbum=${form.idAlbum}"/>' method="post"  >
			<input type="hidden" name="retorno" value="${form.retorno}" />
			<input type="hidden" name="idAlbum" value="${form.idAlbum}" /> 
			
			<!--		INICIO GLOBO GRIS		-->
			<div class="globo_gris" >
			<div class="globo_gris_01">
			<div class="globo_gris_02">
			<div class="globo_gris_03">
			
				<!--		INICIO CAJA DE FORMULARIO		-->
				<div id="formulario" class="ali_c">
					<p><bean:message key="gestorFlujo.album.eliminarAlbum"/></p>
					<p> ${form.titulo} </p>
				</div>
				<!--		FIN CAJA DE FORMULARIO		-->
					
			</div>
			</div>
			</div>
			</div>
			<!--		FIN GLOBO GRIS		-->
	
			<!-- Inicio Botones  -->
			<input class="boton_125_de_2 tipo"  type="submit"  value="<bean:message key='gestorFlujo.aceptar'/>" />
			</form>

			<!--		Formulario secundario y boton cancelar		-->
			<form action='<html:rewrite action="EliminarAlbumCU/ConfirmaEliminacionRechazarEliminacion"/>' 
				method="post"><input class="boton_125_de_2_izq" 
				type="submit" value="<bean:message key='gestorFlujo.cancelar'/>" />
				<input type="hidden" name="retorno" value="${form.retorno}" /> 
			</form>

		</div><!-- Fin plantilla CONTENIDO -->
		
	</tiles:put>

</tiles:insert>
