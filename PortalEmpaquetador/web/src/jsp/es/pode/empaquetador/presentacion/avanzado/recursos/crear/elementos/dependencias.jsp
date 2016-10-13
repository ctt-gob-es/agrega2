<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/tags-empaquetador.tld" prefix="emp" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<bean:define id="urlArchivos"><html:rewrite action="/CrearRecursoAvanzadoElementos/RecursoArchivos"/></bean:define>
<bean:define id="urlRecursos"><html:rewrite action="/CrearRecursoAvanzadoElementos/RecursoRecursos"/></bean:define>
<bean:define id="urlOrganizaciones"><html:rewrite action="/CrearRecursoAvanzadoElementos/RecursoOrganizaciones"/></bean:define>
<bean:define id="urlSubmanifiestos"><html:rewrite action="/CrearRecursoAvanzadoElementos/RecursoSubmanifiestos"/></bean:define>
<bean:define id="urlGuardar"><html:rewrite action="/CrearRecursoAvanzadoElementos/RecursoRecursos"/></bean:define>
<bean:define id="urlAsistente"><html:rewrite action="/CrearRecursoAvanzadoElementos/RecursoSubmitDependencias"/>?action=Añadir Dependencias</bean:define>
<bean:define id="selected" value="recursos"/>
<emp:layout selected="${selected}" urlArchivos="${urlArchivos}" urlRecursos="${urlRecursos}" urlSubmanifiestos="${urlSubmanifiestos}" urlOrganizaciones="${urlOrganizaciones}" urlGuardar="${urlGuardar}" urlAsistente="${urlAsistente}" titleKey="title.Comun">
<bean:define id="empaquetadorSesion" name="<%=es.pode.empaquetador.presentacion.EmpaquetadorSession.SESSION_KEY%>" scope="session"/>

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


<h2><bean:message key="portalempaquetado.avanzado.recursos.crear.dependencias.titulo"/></h2>


<form method="post" 
	  id="crearRecursoAvanzadoElementosDependenciasCrearForm"
	  action="<html:rewrite action="/CrearRecursoAvanzadoElementos/DependenciasCrear" />" >
	  
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div id="formulario" >
	
	<!--  INICIO CAPA TABLA   -->					
 <div class="caja_tabla bordeada" >

		<table border="1" class="generica_02" cellpadding="0" cellspacing="0" width="100%" summary="Tabla de Dependencias (recursos)">
		<caption><strong><bean:message key="portalempaquetado.avanzado.recursos.crear.elementos.tablaDependencias"/></strong></caption>

<c:set var="contador" value="true"/>
<logic:notEmpty name="form" property= "recursos">
<logic:iterate name="form" property="recursos" id="recurso">
<logic:equal name="contador" value="true">
		<tr class="tr_gris">
</logic:equal>
<logic:equal name="contador" value="false">
		<tr class="tr_blanco">
</logic:equal>
<c:set var="contador" value="${!contador}"/>
			<td valign="top"  class="sin_b">
			<label for="Recurso01" class="oculto" >Seleccione </label>
	<logic:notEmpty name="form" property="dependencias">
	
	<c:set var="checked" value="false"/>
	<logic:iterate id="dependencia" name="form" property="dependencias" >
		<logic:equal name="dependencia" property="identifier" value="${recurso.identifier}" >
			<c:set var="checked" value="true"/>
		</logic:equal>
	</logic:iterate>
		<logic:equal name="checked" value="true" >
			<input type="checkbox" name="identifierRowSelectionAsArray" checked="checked" value="${recurso.identifier }"/></td>
		</logic:equal>
		<logic:equal name="checked" value="false" >
			<input type="checkbox" name="identifierRowSelectionAsArray" value="${recurso.identifier }"/></td>
		</logic:equal>
		
	</logic:notEmpty>
	<logic:empty name="form" property="dependencias">
		<input type="checkbox" name="identifierRowSelectionAsArray" value="${recurso.identifier }"/></td>
	</logic:empty>
		<td valign="top" class="td_larga">	
			<logic:notEmpty name="recurso" property="href">
					<bean:define id="urlConHost"><rewrite:rewrite url="${empaquetadorSesion.ode.localizadorURL}/${recurso.href}"/></bean:define>
					<a href="<emp:encodeURL url="${urlConHost }"/>" target="_blank" class="recurso">${recurso.href}</a>
			</logic:notEmpty>
			<logic:empty name="recurso" property="href">
					<a href="#" class="recurso">${recurso.identifier}</a>
			</logic:empty>
		</td>
		</tr>
</logic:iterate>
</logic:notEmpty>
<logic:empty name="form" property= "recursos">
<td><bean:message key="portalempaquetado.avanzado.recursos.gestor.noHayDependencias"/></td>
		</logic:empty>
		 </table>

</div>
	<!--  FIN CAPA TABLA   -->	
	
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

<input class="boton_125_de_2"  type="submit" name="action"  
	value="<bean:message key="portalempaquetado.avanzado.recursos.crear.dependencias.aceptar"/>" />
<input class="boton_125_de_2_izq"  type="submit" name="action" 
	value="<bean:message key="portalempaquetado.avanzado.recursos.crear.dependencias.cancelar"/>" />
</fieldset>
<!-- Fin Botones  -->
<!-- Fin Botones  -->
</form>
</div>

<!-- Fin plantilla contenido  -->
<!-- Fin plantilla contenido  -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
</emp:layout>
