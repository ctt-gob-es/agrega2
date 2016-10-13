<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<html:xhtml/>


<tiles:insert definition="layout-administrador">

<tiles:put name="title" type="string">
      <bean:message key="title.Comun"/>
</tiles:put>

<tiles:put name="codigo-head" type="string">
<script type="text/javascript" src="<rewrite:rewrite url="static/js/prototype.js"/>"></script>
</tiles:put>

<tiles:put name="body" type="string">

<%@ include file="/taglib-imports.jspf" %>


<!--Inicio plantilla contenido-->

<div class="plantilla_contenido">


<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
<jsp:include page="/layout/messages.jsp" flush="true" />

<h2><bean:message key="nodos.alta.cabeceraSQI"/></h2>
<html:form styleId="altaNodoSQIFormularioAltaNodoSQIAceptarForm" action='/${initParam.url_nodoSQI_alta_aceptar }?progressMonitor=myProgressMonitor'  method="post" enctype="multipart/form-data" onsubmit="startLoading()">
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div id="formulario" >

			
			<!-- -->
				<div class="fila_de_tabla">
  					<div class="contenedor_izquierda_may">
  						<div class="text"><label for="nombreNodo">* <bean:message key="nodos.nombreNodoSQI"/></label></div>
 					</div>
					<div class="contenedor_derecha">
						<div class="text"><input name="nombreNodo" onfocus="limpiarTexto(this)" value="${form.nombreNodo}" onblur="this.style.backgroundColor='#e1e1e1'" id="nombreNodo" type="text" maxlength="70" title="<bean:message key="nodos.introduzcaNodoSQI"/>"  /></div>
					</div>

					<div class="linea_separadora"></div>
					<br class="oculto" />
				</div>
				<!-- -->
				<!-- -->
				<div class="fila_de_tabla">
  					<div class="contenedor_izquierda_may">
  						<div class="text"><label for="urlServicio">* <bean:message key="nodos.urlNodoSQI"/></label></div>

 					</div>
					<div class="contenedor_derecha">
						<div class="text"><input name="urlServicio" onfocus="limpiarTexto(this)"  onblur="this.style.backgroundColor='#e1e1e1'" value="${form.urlServicio}" id="urlServicio" type="text" maxlength="150" title="<bean:message key="nodos.introduzcaUrlSQI"/>"  /></div>
					</div>
					<div class="linea_separadora"></div>
					<br class="oculto" />
				</div>
				<!-- --><!-- -->
				<!-- -->

				<div class="fila_de_tabla">
  					<div class="contenedor_izquierda_may">
  						<div class="text"><label for="descripcionNodo">* <bean:message key="nodos.descripcionNodoSQI"/></label></div>
 					</div>
					<div class="contenedor_derecha">
						<div class="text"><input name="descripcionNodo" onfocus="limpiarTexto(this)"  onblur="this.style.backgroundColor='#e1e1e1'" value="${form.descripcionNodo}" id="descripcionNodo" type="text" maxlength="200" title="<bean:message key="nodos.introduzcaDescripcionSQI"/>"  /></div>
					</div>
					<div class="linea_separadora"></div>

					<br class="oculto" />
				</div>
				<!-- --><!-- -->
				<!-- -->
				<div class="fila_de_tabla">
  					<div class="contenedor_izquierda_may">
  						<div class="text"><label for="lenguajeConsulta">* <bean:message key="nodos.lenguajeConsultaSQI"/></label></div>
 					</div>

					<div class="contenedor_derecha">
						<div class="text"><input name="lenguajeConsulta" onfocus="limpiarTexto(this)"  onblur="this.style.backgroundColor='#e1e1e1'" value="${form.lenguajeConsulta}" id="lenguajeConsulta" type="text" maxlength="4" title="<bean:message key="nodos.introduzcaLenguajeConsultaSQI"/>"  /></div>
					</div>
					<div class="linea_separadora"></div>
					<br class="oculto" />
				</div>
				<!-- --><!-- -->
				<!-- -->
				<div class="fila_de_tabla">

  					<div class="contenedor_izquierda_may">
  						<div class="text"><label for="lenguajeRespuesta">* <bean:message key="nodos.lenguajeRespuestaSQI"/></label></div>
 					</div>
					<div class="contenedor_derecha">
						<div class="text"><input name="lenguajeRespuesta" onfocus="limpiarTexto(this)"  onblur="this.style.backgroundColor='#e1e1e1'" value="${form.lenguajeRespuesta}" id="lenguajeRespuesta" type="text" maxlength="6" title="<bean:message key="nodos.introduzcaLenguajeRespuestaSQI"/>"  /></div>
					</div>
					<div class="linea_separadora"></div>
					<br class="oculto" />

				</div>
				<!-- --><!-- -->
					<!-- -->
				<div class="fila_de_tabla">
  					<div class="contenedor_izquierda_may">
  						<div class="text"><label for="imagen"><bean:message key="nodos.ImagenSQI"/></label></div>
 					</div>
					<div class="contenedor_derecha">

						<div class="text"><html:file name="form" accept="image/jpeg" styleId="selecImagen" property="imagenNodo" onfocus="limpiarTexto(this)" style="width:50%" value="&nbsp;"  onblur="this.style.backgroundColor='#e1e1e1'" styleClass="nombreGrupo" titleKey="cambiar.imagen.seleccione.titleKey"  /><span class="enlinea rad_col2">&nbsp;(100x100 px. Max.)</span></div>
					</div>
					<div class="linea_separadora"></div>
					<br class="oculto" />
				</div>
				<!-- --><!-- -->	
			<br />
				</div>
				<!--  FIN CAJA DE FORMULARIO   -->

			</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->
<p class="parrafo_comun" id="separacion3"><bean:message key="nodos.identGestorObli"/></p>

<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">

		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div id="formulario_02" >
				<div class="fila_de_tabla">
  					<div class="contenedor_izquierda_may">
  						<div class="text"><label for="identificadorSesion"><bean:message key="nodos.identificadorSesion"/></label></div>
 					</div>

					<div class="contenedor_derecha">
						<div class="text"><input name="identificadorSesion" onfocus="limpiarTexto(this)"  onblur="this.style.backgroundColor='#e1e1e1'" value="${form.identificadorSesion}" id="identificadorSesion" type="text" maxlength="100" title="<bean:message key="nodos.introduzcaIdentificadorSesion"/>"  /></div>
					</div>
					<div class="linea_separadora"></div>
					<br class="oculto" />
				</div>
				<!-- -->
				<!-- -->
				<div class="fila_de_tabla">

  					<div class="contenedor_izquierda_may">
  						<div class="text"><label for="gestorSesion"><bean:message key="nodos.gestorSesion"/></label></div>
 					</div>
					<div class="contenedor_derecha">
						<div class="text"><input name="gestorSesion" onfocus="limpiarTexto(this)"  onblur="this.style.backgroundColor='#e1e1e1'" value="${form.gestorSesion}" id="gestorSesion" type="text" maxlength="100" title="<bean:message key="nodos.introduzcaGestorSesion"/>"  /></div>
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

<p class="parrafo_comun" id="separacion5"><bean:message key="nodos.usuarioClaveObli"/></p>

<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div  id="formulario_03" >

				<div class="fila_de_tabla">
  					<div class="contenedor_izquierda_may">
  						<div class="text"><label for="usuario"><bean:message key="nodos.usuarioSQI"/></label></div>
 					</div>
					<div class="contenedor_derecha">
						<div class="text"><input name="usuario" onfocus="limpiarTexto(this)"  onblur="this.style.backgroundColor='#e1e1e1'" value="${form.usuario}" id="usuario" type="text" maxlength="25" title="<bean:message key="nodos.introduzcaUsuarioSQI"/>"  /></div>
					</div>
					<div class="linea_separadora"></div>

					<br class="oculto" />
				</div>
				<!-- -->
				<!-- -->
				<div class="fila_de_tabla">
  					<div class="contenedor_izquierda_may">
  						<div class="text"><label for="clave"><bean:message key="nodos.claveSQI"/></label></div>
 					</div>

					<div class="contenedor_derecha">
						<div class="text"><input name="clave" onfocus="limpiarTexto(this)"  onblur="this.style.backgroundColor='#e1e1e1'" value="${form.clave}" id="clave" type="text" maxlength="25" title="<bean:message key="nodos.introduzcaClaveSQI"/>"  /></div>
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


		<!--		Boton Siguiente		-->
	<fieldset class="tipo ft_centrada" id="con_loading">
	
	<input class="boton_125_de_2"  type="submit"  value="<bean:message key='usuarios.aceptar'/>" />
	
</html:form>

<!--		Fin del Formulario principal		-->


<!--		Inicio formulario secundario y boton Cancelar		-->
	
<form id="altaNodoSQIFormularioAltaNodoSQICancelarForm" action="<html:rewrite action="${initParam.url_nodoSQI_alta_cancelar }"/>" method="post" >
	<input class="boton_125_de_2_izq"  type="submit"  value="<bean:message key='usuarios.cancelar'/>" />
</form>
	</fieldset>
	
<div id="loading"  style="z-index:100 !important">
<%@ include file="/progressBar/barra.jsp"%>
</div>
<%@ include file="/progressBar/cabecera-barra.jsp" %>
	
	
<!--		Fin formulario secundario		-->
	

</div>

<!--		Fin de la capa plantilla_contenido		-->


</tiles:put>


</tiles:insert>