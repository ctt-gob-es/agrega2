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

		<!-- Inicio plantilla contenido  -->
		<div class="plantilla_contenido">
		<jsp:include page="/layout/messages.jsp" flush="true" /><!-- ## Para que los mensajes de error vayan bien en IE6 ## -->

		<h2><bean:message key="gestorFlujo.envioAlbum.crearAlbum" /></h2>

		<form id="altaAlbumCUCrearAceptarForm" method="post" action='<html:rewrite action="/${initParam.url_altaAlbum_aceptar}"/>' >
		<!--  INICIO GLOBO GRIS   --><!--  INICIO GLOBO GRIS   -->
			<div class="globo_gris" id="ficha">
			<div class="globo_gris_01">
			<div class="globo_gris_02">
			<div class="globo_gris_03">

			<input type="hidden" name="retorno" value="${form.retorno}" /> 
			<input type="hidden" name="idUsuario" value="${form.idUsuario}" /> 
				
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div id="formulario" >
				<p class="parrafo_comun" ><bean:message key="gestorFlujo.album.textoCrear" /></p>
			
				<!--  INICIO GLOBO AZUL   --><!--  INICIO GLOBO AZUL   -->
				<div class="globo_blanco gb_ie" >
				<div class="globo_blanco_01">
				<div class="globo_blanco_02">
				<div class="globo_blanco_03">
				
					<div class="fila_de_tabla">
  					<div class="contenedor_izquierda">
  						<div class="text"><label for="titulo"><bean:message key="gestorFlujo.album.nombre" /></label></div>
 					</div>
					<div class="contenedor_derecha">
						<div class="text">
							<input name="titulo" value="" id="titulo" maxlength="200" type="text" onblur="this.style.backgroundColor='#e1e1e1'" title="<bean:message key="gestorFlujo.album.introduzcaNombre"/>" />
						</div>
					</div>

					<div class="linea_separadora"></div>
					<br class="oculto" />
					</div>
						
								
				</div>
				</div>
				</div>
				</div>
				<!--  FIN GLOBO AZUL   --><!--  FIN GLOBO AZUL   -->
			</div><!--  FIN CAJA DE FORMULARIO   -->
				
			</div>
			</div>
			</div>
			</div>
			<!--  FIN GLOBO GRIS   --><!--  FIN GLOBO GRIS   -->

			<!-- Inicio Botones  --><!-- Inicio Botones  -->

			<input class="boton_125_de_2"  type="submit"  value="<bean:message key="gestorFlujo.aceptar"/>" />
			
		</form>

		<form
			action='<html:rewrite action="AltaAlbumCU/CrearCancelar"/>'
			method="post"><input class="boton_125_de_2_izq"
			type="submit" value="<bean:message key='gestorFlujo.cancelar'/>" />
			<input type="hidden" name="retorno" value="${form.retorno}" /> 
		</form>
		<!-- Fin Botones  --><!-- Fin Botones  -->

	</div><!-- Fin plantilla contenido  -->
	
	</tiles:put>

</tiles:insert>

