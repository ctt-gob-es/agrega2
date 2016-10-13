<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>


<tiles:insert definition="layout-administrador">

<tiles:put name="title" type="string">
      <bean:message key="title.Comun"/>
</tiles:put>


<tiles:put name="body" type="string">

<%@ include file="/taglib-imports.jspf" %>


<!--		Inicio plantilla contenido		-->

<div class="plantilla_contenido">


<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
<jsp:include page="/layout/messages.jsp" flush="true" />

<h2><bean:message key="nodos.verNodo.cabeceraSQI"/></h2>

<!--		Inicio del formulario principal		-->
<form id="verNodoSQIDetalleNodoSQIAceptarForm" action='<html:rewrite action="/${initParam.url_nodoSQI_ver_aceptar }"/>' method="post" >

<!--  INICIO GLOBO GRIS   -->

	<div class="globo_gris conmargen" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
				<div class="formu"   >

						<div class="item_comunidad nombre_contacto" id="alta_nodo">
						<img class="usu_flotante" src="${form.imagenNodo}" alt="${form.nombreNodo}"/>
					<div class="fila_de_tabla">
  					<div class="contenedor_izquierda_esp">
  						<div class="text"><span class="tipo_label"><label for="NombreNodo"><bean:message key="nodos.nombreNodoSQI"/></label></span></div>
 					</div>
					<div class="contenedor_derecha">
						<div class="text"><p>${form.nombreNodo}</p></div>

					</div>
					<div class="linea_separadora"></div>
					<br class="oculto" />
				</div>
				<!-- -->
				<div class="fila_de_tabla">
  					<div class="contenedor_izquierda_esp">
  						<div class="text"><span class="tipo_label"><label for="NombreURL"><bean:message key="nodos.urlNodoSQI"/></label></span></div>

 					</div>
					<div class="contenedor_derecha">
						<div class="text"><p>${form.urlServicio}</p></div>
					</div>
					<div class="linea_separadora"></div>
					<br class="oculto" />
				</div>
				<!-- -->

				<div class="fila_de_tabla">
  					<div class="contenedor_izquierda_esp">
  						<div class="text"><span class="tipo_label"><label for="DescripcionNodo"><bean:message key="nodos.descripcionNodoSQI"/></label></span></div>
 					</div>
					<div class="contenedor_derecha">
						<div class="text"><p>${form.descripcionNodo}</p></div>
					</div>
					<div class="linea_separadora"></div>

					<br class="oculto" />
				</div>
				<!-- -->
				<div class="fila_de_tabla">
  					<div class="contenedor_izquierda_esp">
  						<div class="text"><span class="tipo_label"><label for="lenguajeConsulta"><bean:message key="nodos.lenguajeConsultaSQI"/></label></span></div>
 					</div>
					<div class="contenedor_derecha">

						<div class="text"><p>${form.lenguajeConsulta}</p></div>
					</div>
					<div class="linea_separadora"></div>
					<br class="oculto" />
				</div>
				<!-- -->
				<div class="fila_de_tabla">
  					<div class="contenedor_izquierda_esp">

  						<div class="text"><span class="tipo_label"><label for="lenguajeRespuesta"><bean:message key="nodos.lenguajeRespuestaSQI"/></label></span></div>
 					</div>
					<div class="contenedor_derecha">
						<div class="text"><p>${form.lenguajeRespuesta}</p></div>
					</div>
					<div class="linea_separadora"></div>
					<br class="oculto" />
				</div>

						</div>				
		
				</div>
				<!--  FIN CAJA DE FORMULARIO   -->
			</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->

<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->

<div class="globo_gris conmargen" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div id="formulario_02" >
				<div class="fila_de_tabla">
  					<div class="contenedor_izquierda_may">
  						<div class="text"><span class="tipo_label underl"><label for="identificadorSesion"><bean:message key="nodos.identificadorSesion"/></label></span></div>

 					</div>
					<div class="contenedor_derecha">
						<div class="text"><p>&nbsp;${form.identificadorSesion}</p></div>
					</div>
					<div class="linea_separadora"></div>
					<br class="oculto" />
				</div>
				<!-- -->

				<!-- -->
				<div class="fila_de_tabla">
  					<div class="contenedor_izquierda_may">
  						<div class="text"><span class="tipo_label"><label for="gestorSesion"><bean:message key="nodos.gestorSesion"/></label></span></div>
 					</div>
					<div class="contenedor_derecha">
						<div class="text"><p>&nbsp;${form.gestorSesion}</p></div>
					</div>

					<div class="linea_separadora"></div>
					<br class="oculto" />
				</div>
				<!-- --><!-- -->
				</div>
				<!--  FIN CAJA DE FORMULARIO   -->
			</div>
		</div>
	</div>

</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->

<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris conmargen" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div  id="formulario_03" >

				<div class="fila_de_tabla">
  					<div class="contenedor_izquierda_may">
  						<div class="text"><span class="tipo_label"><label for="usuario"><bean:message key="nodos.usuarioSQI"/></label></span></div>
 					</div>
					<div class="contenedor_derecha">
						<div class="text"><p>&nbsp;${form.usuario}</p></div>
					</div>
					<div class="linea_separadora"></div>
					<br class="oculto" />
				</div>
				<!-- -->
				<!-- -->
				<div class="fila_de_tabla">
  					<div class="contenedor_izquierda_may">
  						<div class="text"><span class="tipo_label"><label for="clave"><bean:message key="nodos.claveSQI"/></label></span></div>
 					</div>

					<div class="contenedor_derecha">
						<div class="text"><p>&nbsp;${form.clave}</p></div>
					</div>
					<div class="linea_separadora"></div>
					<br class="oculto" />
				</div>
				<!-- --><!-- -->
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
<fieldset class="tipo ft_centrada">
<input class="boton_125"  type="submit"  value="<bean:message key='nodos.volver'/>" />
</fieldset>
<!-- Fin Botones  -->
<!-- Fin Botones  -->
</form>
</div>



</tiles:put>


</tiles:insert>