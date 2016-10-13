<%@ include file="/taglib-imports.jspf" %>
<html:xhtml/>

<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>

<script type="text/javascript" src="<rewrite:rewrite url='static/js/prototype.js'/>"></script>

<tiles:insert definition="layout-avanzado">
<tiles:put name="title" type="string">
	<bean:message key="title.Avanzado"/>
</tiles:put>

<tiles:put name="body" type="string">
<jsp:include page="/layout/messages.jsp" flush="true"/>

<div class="plantilla_contenido">
<h2><bean:message key="catalogadorAvanzado.importar.titulo"/></h2>

<form method="post" action="<html:rewrite action="/ImportarLomes/ImportarLomesSubmit"/>?progressMonitor=myProgressMonitor" enctype="multipart/form-data" onsubmit="startLoading()">


<!--   FICHERO  -->

<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div id="formulario" >
				<div class="fila_de_tabla" >
  					<div class="contenedor_izquierda_may">
  						<div class="text">
  							<label for="<bean:message key="catalogadorAvanzado.importar.seleccione"/>">
  								<bean:message key="catalogadorAvanzado.importar.label"/>
  							</label>
  						</div>
 					</div>

					<div class="contenedor_derecha_may">
						
						<div class="text">
							<input  name="fichero"
									onfocus="limpiarTexto(this)"  
									value="&nbsp;"  
									onblur="this.style.backgroundColor='#e1e1e1'" id="fichero" 
									type="file" 
									class="selfuente" 
									title="<bean:message key="catalogadorAvanzado.importar.seleccione"/>"  />
						</div>
					</div>
					<div class="linea_separadora"></div>
					<br class="oculto" />
				</div>	
				
				</div>
				<!--  FIN CAJA DE FORMULARIO   -->
			</div>

		</div>
	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->
<!--   FICHERO   	  -->


<!-- Inicio Botones  -->
<!-- Inicio Botones  -->
<fieldset class="tipo" id="con_loading">
	<input class="boton_125_de_2_izq" name="accion" type="submit"  value="<bean:message key="catalogadorAvanzado.importar.cancelar"/>" />
	<input class="boton_125_de_2" name="accion" type="submit"  value="<bean:message key="catalogadorAvanzado.importar.aceptar"/>" />
</fieldset>
<!-- Fin Botones  -->

<div id="loading" style="z-index:100 !important">
<%@ include file="/progressBar/barra.jsp"%>
</div>
<%@ include file="/progressBar/cabecera-barra.jsp" %>

<!-- Fin Botones  -->


</form>
</div>	<!-- Fin plantilla contenido  -->
</tiles:put>

</tiles:insert>







