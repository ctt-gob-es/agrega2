<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/tags-empaquetador.tld" prefix="emp" %>
<bean:define id="urlArchivos"><html:rewrite action="/GestorArchivos/GestorArchivos"/></bean:define>
<bean:define id="urlRecursos"><html:rewrite action="/GestorRecursos/GestorRecursos"/></bean:define>
<bean:define id="urlOrganizaciones"><html:rewrite action="/GestorOrganizaciones/GestorOrganizaciones"/></bean:define>
<bean:define id="urlSubmanifiestos"><html:rewrite action="/GestorSubmanifiestos/GestorSubmanifiestos"/></bean:define>

<bean:define id="empaquetadorSesion" name="<%=es.pode.empaquetador.presentacion.EmpaquetadorSession.SESSION_KEY%>" scope="session"/>
<c:choose>
<c:when test="${empaquetadorSesion.tipoEmpaquetador=='Basico'}">
	<bean:define id="urlGuardar"><html:rewrite action="/GestorBasico/GestorBasico"/></bean:define>
	<bean:define id="urlAsistente"><html:rewrite action="/GestorBasico/GestorBasico"/>?action=Agregar</bean:define>
</c:when>
<c:otherwise>
	<bean:define id="urlGuardar"><html:rewrite action="/GestorSubmanifiestos/GestorSubmanifiestos"/></bean:define>
	<bean:define id="urlAsistente"><html:rewrite action="/GestorSubmanifiestos/GestorSubmanifiestosSubmit"/>?action=Agregar</bean:define>
</c:otherwise>
</c:choose>

<bean:define id="urlEstructura"><html:rewrite action="/GestorBasico/GestorBasico"/></bean:define>
<bean:define id="selected" value="submanifiestos"/>
<emp:layout selected="${selected}" urlEstructura="${urlEstructura}" urlArchivos="${urlArchivos}" urlRecursos="${urlRecursos}" urlSubmanifiestos="${urlSubmanifiestos}" urlOrganizaciones="${urlOrganizaciones}" urlGuardar="${urlGuardar}" urlAsistente="${urlAsistente}" titleKey="title.Comun">


<!-- Inicio plantilla contenido  -->
<!-- Inicio plantilla contenido  -->
<div class="plantilla_contenido">
<%@ include file="/layout/messages.jsp" %>

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


<h2><bean:message key="portal_empaquetado_gestorSubman_agregar.titulo"/></h2>
<form id="agregarSubmanifiestoSeleccionarOrigenAceptarForm" method="post" action="<html:rewrite action="/AgregarSubmanifiesto/SeleccionarOrigenAceptar"/>" enctype="multipart/form-data">
<p class="parrafo_comun" id="separacion2"><bean:message key="portal_empaquetado_gestorSubman_agregar.seleccionar"/></p>
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div id="formulario" >
			 <div class="fila_de_tabla">
  						<div class="text ft_lateral"><input type="radio" class="boton_radio" id="Personales" checked="checked" name="origen" value="Personales"/><label for="Personales" class="alineada"><bean:message key="portal_empaquetado_gestorSubman_agregar.crearSubmanObjetosPersonales"/></label></div>
				<div class="linea_separadora"></div>
				<br class="oculto" />
				</div>
				<!--     -->
			<div class="fila_de_tabla">
  						<div class="text ft_lateral"><input  type="radio" class="boton_radio" id="Local"  name="origen" value="Local" /><label for="Local"  class="alineada2"><bean:message key="portal_empaquetado_gestorSubman_agregar.crearSubmanOrdenador"/></label></div>
				<div class="linea_separadora"></div>
				<br class="oculto" />
				</div>
				<!--     -->
			<c:if test="${!empaquetadorSession.esOffline}">
			<div class="fila_de_tabla">
  						<div class="text ft_lateral"><input  type="radio" class="boton_radio" id="Repositorio"  name="origen" value="Repositorio" /><label for="Repositorio"  class="alineada3"><bean:message key="portal_empaquetado_gestorSubman_agregar.crearSubmanRepositorio"/> </label></div>
				<div class="linea_separadora"></div>
				<br class="oculto" />
				</div>
			</c:if>
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
<input class="boton_125_de_2"  type="submit" name="action" value="<bean:message key='portal_empaquetado_gestorSubman_agregar.continuar'/>" />
<input class="boton_125_de_2_izq"  type="submit" name="action"  value="<bean:message key='portal_empaquetado_gestorSubman_agregarLocal.cancelar'/>" />
</fieldset>
<!-- Fin Botones  -->
<!-- Fin Botones  -->
</form>
<script type="text/javascript">
  document.forms[0].origen[0].focus();
</script>

</div>
<!-- Fin plantilla contenido  -->
<!-- Fin plantilla contenido  -->
</emp:layout>