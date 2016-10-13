<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/tags-empaquetador.tld" prefix="emp" %>
<bean:define id="urlArchivos"><html:rewrite action="/CrearRecursoAvanzado/TipoRecursoArchivos"/></bean:define>
<bean:define id="urlRecursos"><html:rewrite action="/CrearRecursoAvanzado/TipoRecursoRecursos"/></bean:define>
<bean:define id="urlOrganizaciones"><html:rewrite action="/CrearRecursoAvanzado/TipoRecursoOrganizaciones"/></bean:define>
<bean:define id="urlSubmanifiestos"><html:rewrite action="/CrearRecursoAvanzado/TipoRecursoSubmanifiestos"/></bean:define>
<bean:define id="urlGuardar"><html:rewrite action="/CrearRecursoAvanzado/TipoRecursoRecursos"/></bean:define>
<bean:define id="urlAsistente"><html:rewrite action="/GestorRecursos/GestorRecursosSubmit"/>?action=Crear Recurso</bean:define>
<bean:define id="selected" value="recursos"/>
<emp:layout selected="${selected}" urlArchivos="${urlArchivos}" urlRecursos="${urlRecursos}" urlSubmanifiestos="${urlSubmanifiestos}" urlOrganizaciones="${urlOrganizaciones}" urlGuardar="${urlGuardar}" urlAsistente="${urlAsistente}" titleKey="title.Comun">
<bean:define id="empaquetadorSesion" name="<%=es.pode.empaquetador.presentacion.EmpaquetadorSession.SESSION_KEY%>" scope="session"/>
<bean:define id="crearRecurso" name="<%=es.pode.empaquetador.presentacion.avanzado.recursos.crear.CrearRecursoAvanzadoSession.SESSION_KEY%>" scope="session"/>

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
<div class="plantilla_contenido">
<%@ include file="/layout/messages.jsp" %>


<logic:equal name="crearRecurso" property="modificar" value="false">
	<h2> <bean:message key="portalempaquetado.avanzado.recursos.crear.paso1.titulo" /></h2>
</logic:equal>
<logic:equal name="crearRecurso" property="modificar" value="true">
	<h2> <bean:message key="portalempaquetado.avanzado.recursos.modificar.paso1.titulo" /> </h2>
</logic:equal>





<form 
	method="post"
	id="crearRecursoAvanzadoTipoRecursoContinuarForm" 
	action="<html:rewrite action="/CrearRecursoAvanzado/TipoRecursoContinuar"/>">
	

<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->
<p class="parrafo_comun" id="separacion3">
	<bean:message key="portalempaquetado.avanzado.recursos.crear.paso1.tiposcorm"/>
	</p>
<bean:define id="tipoScorm" value="${form.tipo}" />
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">

		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div id="formulario_02" >
			 <div class="fila_de_tabla">
  						<div class="text ft_lateral">

<logic:equal name="tipoScorm" value="asset">
  							<input type="radio" class="boton_radio" checked="checked" name="tipo"
  								 value="asset" id="tipoAsset"/>
</logic:equal>
<logic:notEqual name="tipoScorm" value="asset">
  							<input type="radio" class="boton_radio" name="tipo" value="asset" id="tipoAsset"/>
</logic:notEqual>
  								<label for="tipoAsset" class="alineada">
  									<bean:message key="portalempaquetado.avanzado.recursos.crear.paso1.asset"/>
  								</label></div>
				<div class="linea_separadora"></div>
				<br class="oculto" />

				</div>
				<!--     -->
			<div class="fila_de_tabla">
  						<div class="text ft_lateral">
<logic:equal name="tipoScorm" value="sco">
  							<input  type="radio" class="boton_radio" checked="checked"  name="tipo" value="sco" id="tipoSco"/>
</logic:equal>
<logic:notEqual name="tipoScorm" value="sco">
  							<input  type="radio" class="boton_radio" name="tipo" value="sco" id="tipoSco"/>
</logic:notEqual>
  								<label for="tipoSco"  class="alineada2">
  									<bean:message key="portalempaquetado.avanzado.recursos.crear.paso1.sco"/>
  								</label></div>
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
<fieldset class="tipo ft_centrada">
<input class="boton_125_de_2"  type="submit" name="action"  
	value="<bean:message key='portalempaquetado.avanzado.recursos.crear.paso1.continuar'/>"/>
<input class="boton_125_de_2_izq"  type="submit" name="action"
	value="<bean:message key='portalempaquetado.avanzado.recursos.crear.paso1.cancelar'/>"/>
</fieldset>
<!-- Fin Botones  -->
<!-- Fin Botones  -->
</form>

<script type="text/javascript">
  document.forms[0].tipo[0].focus();
</script>

</div>
<!-- Fin plantilla contenido  -->
<!-- Fin plantilla contenido  -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
</emp:layout>
