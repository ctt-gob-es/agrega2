<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/tags-empaquetador.tld" prefix="emp" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<bean:define id="urlArchivos"><html:rewrite action="/GestorArchivos/GestorArchivos"/></bean:define>
<bean:define id="urlRecursos"><html:rewrite action="/GestorRecursos/GestorRecursos"/></bean:define>
<bean:define id="urlOrganizaciones"><html:rewrite action="/GestorOrganizaciones/GestorOrganizaciones"/></bean:define>
<bean:define id="urlSubmanifiestos"><html:rewrite action="/GestorSubmanifiestos/GestorSubmanifiestos"/></bean:define>
<bean:define id="urlGuardar"><html:rewrite action="/GestorRecursos/GestorRecursos"/></bean:define>
<bean:define id="urlAsistente"><html:rewrite action="CrearArchivo/CrearArchivo"/></bean:define>
<bean:define id="urlEstructura"><html:rewrite action="/GestorBasico/GestorBasico"/></bean:define>
<bean:define id="selected" value="archivos"/>
<emp:layout selected="${selected}" urlEstructura="${urlEstructura}" urlArchivos="${urlArchivos}" urlRecursos="${urlRecursos}" urlSubmanifiestos="${urlSubmanifiestos}" urlOrganizaciones="${urlOrganizaciones}" urlGuardar="${urlGuardar}" urlAsistente="${urlAsistente}" titleKey="title.Comun">
<bean:define id="empaquetadorSesion" name="<%=es.pode.empaquetador.presentacion.EmpaquetadorSession.SESSION_KEY%>" scope="session"/>

<c:if test="${!empty empaquetadorSesion.mensajeAsistente}">
	<div class="bocadillo">
	<div class="bocadillo_int">
	<div>
	<strong class="titulo_boc"><bean:message key="empaquetador.asistente.Recomendacion"/></strong>
	<p>${empaquetadorSesion.mensajeAsistente}</p>
	</div>
	</div>
	</div>
</c:if>
 <!-- Inicio plantilla contenido  -->
<!-- Inicio plantilla contenido  -->
<div class="plantilla_contenido">
<%@ include file="/layout/messages.jsp" %>

<h2><bean:message key="presentacion.archivos.creararchivo.cabecera.h2"/></h2>
<form method="post" action="<html:rewrite action="/CrearArchivo/CrearArchivoAceptar"/>?progressMonitor=myProgressMonitor" enctype="multipart/form-data" onsubmit="startLoading()">
<p class="parrafo_comun" id="separacion2"><bean:message key="presentacion.archivos.creararchivo.cabecera.texto"/></p>

<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->

			<div id="formulario" >
			 <div class="fila_de_tabla">
  						<div class="text" ><label for="SelArchivo1" class="red_cc"><span>01&nbsp;&nbsp;</span></label><input name="fichero1" class="nombreGrupo" onfocus="limpiarTexto(this)" style="width:50%" value="&nbsp;"  onblur="this.style.backgroundColor='#e1e1e1'" id="SelArchivo1"  type="file" title="<bean:message key="presentacion.archivos.creararchivo.input.text.title"/> 01"  /></div>
					<br class="oculto" />
				</div>
			
			<div class="fila_de_tabla">
				<div class="text ft_lateral"><input type="radio" name="tipo1" class="boton_radio" id="ArchivoSolo1" value="FICHERO" checked="checked"  /><label for="ArchivoSolo1" class="alineada"><bean:message key="presentacion.archivos.creararchivo.input.radio.unico"/></label></div>
				<div class="linea_separadora"></div>

				<br class="oculto" />
				</div>
				<!--     -->
			 <div class="fila_de_tabla">
  				<div class="text ft_lateral"><input  type="radio" class="boton_radio" id="ArchivoComprimido1" value="ZIP"  name="tipo1" /><label for="ArchivoComprimido1"  class="alineada2"><bean:message key="presentacion.archivos.creararchivo.input.radio.zip"/></label></div>
				<div class="linea_separadora"></div>
				<br class="oculto" />
				</div>
				<!--     -->
			 <div class="fila_de_tabla">
  			  		<div class="text ft_lateral"><input  type="radio" class="boton_radio" id="ImagenDefecto3" value="IMAGEN_ODE"  name="tipo1" /><label for="ImagenDefecto1"  class="alineada2"><bean:message key="carpetapersonal.modificar.archivos.importar"/></label></div>
				<div class="linea_separadora"></div>
				<br class="oculto" />
			 </div>

				<!--     -->
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
<div class="globo_gris" id="conmargen0">
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div id="formulario_02" >
			 <div class="fila_de_tabla">
  					<div class="text" ><label for="SelArchivo2" class="red_cc"><span>02&nbsp;&nbsp;</span></label><input name="fichero2" class="nombreGrupo" onfocus="limpiarTexto(this)" style="width:50%" value="&nbsp;"  onblur="this.style.backgroundColor='#e1e1e1'" id="SelArchivo2"  type="file"  title="<bean:message key="presentacion.archivos.creararchivo.input.text.title"/> 02 "  /></div>
					<br class="oculto" />
 			 </div>
			
			<div class="fila_de_tabla">
  				  	<div class="text ft_lateral"><input type="radio" name="tipo2" class="boton_radio" id="ArchivoSolo2" value="FICHERO" checked="checked"  /><label for="ArchivoSolo2" class="alineada"><bean:message key="presentacion.archivos.creararchivo.input.radio.unico"/></label></div>
				<div class="linea_separadora"></div>
				<br class="oculto" />
			</div>
				<!--     -->

			 <div class="fila_de_tabla">
  						 <div class="text ft_lateral"><input  type="radio" class="boton_radio" id="ArchivoComprimido2" value="ZIP"  name="tipo2" /><label for="ArchivoComprimido2"  class="alineada2"><bean:message key="presentacion.archivos.creararchivo.input.radio.zip"/></label></div>
				<div class="linea_separadora"></div>
				<br class="oculto" />
			 </div>
				<!--     -->
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
<div class="globo_gris" id="conmargen" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">

			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div id="formulario_03" >
			 <div class="fila_de_tabla">
  					<div class="text" ><label for="SelArchivo3" class="red_cc"><span>03&nbsp;&nbsp;</span></label><input name="fichero3" class="nombreGrupo" onfocus="limpiarTexto(this)" style="width:50%" value="&nbsp;"  onblur="this.style.backgroundColor='#e1e1e1'" id="SelArchivo3" type="file"  title="<bean:message key="presentacion.archivos.creararchivo.input.text.title"/> 03"  /></div>
				<br class="oculto" />
			 </div>
			
			<div class="fila_de_tabla">
				<div class="text ft_lateral"><input type="radio" name="tipo3" class="boton_radio" id="ArchivoSolo3" value="FICHERO" checked="checked"  /><label for="ArchivoSolo3" class="alineada"><bean:message key="presentacion.archivos.creararchivo.input.radio.unico"/></label></div>
					<div class="linea_separadora"></div>
					<br class="oculto" />
				</div>
				<!--     -->
			 <div class="fila_de_tabla">
  			  		<div class="text ft_lateral"><input  type="radio" class="boton_radio" id="ArchivoComprimido3" value="ZIP"  name="tipo3" /><label for="ArchivoComprimido3"  class="alineada2"><bean:message key="presentacion.archivos.creararchivo.input.radio.zip"/></label></div>
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

<!-- Inicio Botones  -->
<!-- Inicio Botones  -->
<fieldset class="tipo" id="con_loading">
<input class="boton_125_de_2"  type="submit" name="action" value="<bean:message key='presentacion.archivos.creararchivo.input.submit.aceptar'/>" />
<!-- Cargando  -->
<!-- Cargando  -->
<input class="boton_125_de_2_izq" type="submit"  name="action" value="<bean:message key='presentacion.archivos.creararchivo.input.submit.cancelar'/>" />
</fieldset>
<!-- Fin Botones  -->
<div id="loading"  style="z-index:100 !important">
<%@ include file="/progressBar/barra.jsp"%>
</div>
<%@ include file="/progressBar/cabecera-barra.jsp" %>
<!-- Fin Botones  -->
</form>
<script type="text/javascript">
  document.forms[0].tipo1[0].focus();
</script>
</div>

</emp:layout>
