<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/tags-empaquetador.tld" prefix="emp" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<bean:define id="urlArchivos"><html:rewrite action="/GestorArchivos/GestorArchivos"/></bean:define>
<bean:define id="urlRecursos"><html:rewrite action="/GestorRecursos/GestorRecusos"/></bean:define>
<bean:define id="urlOrganizaciones"><html:rewrite action="/GestorOrganizaciones/GestorOrganizaciones"/></bean:define>
<bean:define id="urlSubmanifiestos"><html:rewrite action="/GestorSubmanifiestos/GestorSubmanifiestos"/></bean:define>
<bean:define id="urlGuardar"><html:rewrite action="/GestorRecursos/GestorRecursos"/></bean:define>
<bean:define id="urlAsistente"><html:rewrite action="/GestorRecursos/GestorRecursos"/></bean:define>
<bean:define id="selected" value="recursos"/>
<emp:layout selected="${selected}" urlArchivos="${urlArchivos}" urlRecursos="${urlRecursos}" urlSubmanifiestos="${urlSubmanifiestos}" urlOrganizaciones="${urlOrganizaciones}" urlGuardar="${urlGuardar}" urlAsistente="${urlAsistente}" titleKey="title.Comun">
<bean:define id="empaquetadorSesion" name="<%=es.pode.empaquetador.presentacion.EmpaquetadorSession.SESSION_KEY%>" scope="session"/>
<!-- gestor de archivos session -->
<%@ include  file="/es/pode/empaquetador/presentacion/avanzado/recursos/gestor/gestor-recursos-vars.jspf" %>

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

<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- ATENCION!! E3TA ES LA CAPA QUE DEBE REEMPLAZARSE POR LA DEL MISMO NOMBRE (plantilla contenido pestanias ) EN LA PLANTILLA DE  CONTENIDO  CON EL CONTENIDO DE FICHA -->
<div class="plantilla_contenido_pestanias">


<form 	id="gestorRecursosGestorRecursosSubmitForm" 
		method="post" 
		action="<html:rewrite action="/GestorRecursos/GestorRecursosSubmit"/>" >
	<!-- Aviso de cambio de licencias -->
	<c:if test="${form.mostradoMensajeCompatibilidadLicencia == false}">
	<!-- Class exito se pinta en verde, luego debería pasar var para pintar como error o como exito -->
		<div class="error">
		<!-- Mostramos mensaje -->
		<p>${empaquetadorSesion.mensajeCompatibilidadLicencias }</p>
		</div>
	</c:if>
<!--  portapapeles  si es necesario -->




			<h2><bean:message key="presentacion.avanzado.recursos.gestor.cabecera.h2"/></h2>
		<!-- CAJA TABLA -->
		<!-- CAJA TABLA -->
			<div class="caja_tabla" >
			
			
			<table border="1" class="administracion_tareas" cellpadding="0" cellspacing="0" width="100%" summary="<bean:message key="presentacion.avanzado.recursos.gestor.table.summary"/>">
			
			<caption><strong><bean:message key="presentacion.avanzado.recursos.gestor.table.caption"/></strong></caption>
			
<!--  iteracion para la tabla  -->			
			
			
			
<!--  *****************************PARA QUE HAGA CAMPO GRIS,CAMPO BLANCO  *****************************-->

					<c:set var="contador" value="true"/>
					<c:set var="contflechas" value="0"/>
			
<logic:notEmpty name="listado">
			<logic:iterate id="recurso" name="listado">
						<logic:equal name="contador" value="true">
							<tr class="tr_gris">
						</logic:equal>

						<logic:equal name="contador" value="false">
							<tr class="tr_blanco">
						</logic:equal>
						<c:set var="contador" value="${!contador}"/>
						
<!--  *****************************FIN PARA QUE HAGA CAMPO GRIS,CAMPO BLANCO ***************************** -->

<!--  *****************************dibujo: recurso  ***************************** -->
				<td valign="top"  class="sin_b">
					<label for="Recurso" class="etiq_invisible" ><bean:message key="comunes.checkbox.seleccione"/></label>
					<input type="checkbox" name="identifierRowSelectionAsArray" value="${recurso.identifier}" />
				</td>
				<td valign="top"  class="tar7" >
			<logic:notEmpty name="recurso" property="href">
			<bean:define id="urlConHost"><rewrite:rewrite url="${recurso.recursoURL}"/></bean:define>
					<a href="<emp:encodeURL url="${urlConHost }"/>" target="_blank" class="recurso">${recurso.href}</a>
			</logic:notEmpty>
			<logic:empty name="recurso" property="href">
					<a href="#" class="recurso">${recurso.identifier}</a>
			</logic:empty>
			</td>
			<logic:empty name="recurso" property="lom" >
				<td valign="top" class="meta_datos_mas" >
			</logic:empty>
			<logic:notEmpty name="recurso" property="lom" >
				<td valign="top" class="meta_datos" >
			</logic:notEmpty>
					<span class="oculto">--</span>
					<a href="<html:rewrite action="/GestorRecursos/GestorRecursosMetadatos"/>?identifier=${recurso.identifier}&amp;returnURL=<html:rewrite action="/GestorRecursos/GestorRecursos"/>" ><bean:message key="presentacion.avanzado.recursos.gestor.table.link.metadatos"/></a>
				</td>
				<td valign="top" class="ejec">
					<span class="oculto">-</span>
					<a href="<html:rewrite action="/GestorRecursos/GestorRecursosModificar"/>?identifier=${recurso.identifier}"><bean:message key="presentacion.avanzado.recursos.gestor.table.link.modificar"/></a>
				</td>
				</tr>
		</logic:iterate>
		</logic:notEmpty>
		<logic:empty name="listado">
		<td><bean:message key="presentacion.avanzado.recursos.gestor.table.empty.message"/></td>
		</logic:empty>
<!-- fin iteracion para la tabla  -->			
			
			</table>
			</div>
		<!-- FIN CAJA TABLA -->
		<!-- FIN CAJA TABLA -->
			
			<!-- Inicio Botones  -->
			<!-- Inicio Botones  -->
				<fieldset class="tipo_interior_03">
				<input class="boton_125_de_2"  type="submit"  name="action" value="<bean:message key='presentacion.avanzado.recursos.gestor.submit.eliminar'/>" />
				<br class="oculto" /><br class="oculto" />
				<input class="boton_125_de_2_izq bot_mar_der"  type="submit"  name="action" value="<bean:message key='presentacion.avanzado.recursos.gestor.submit.crear'/>"/>
				<input class="boton_125_de_2_izq bot_mar_der"  type="submit"  name="action" value="<bean:message key='presentacion.avanzado.recursos.gestor.submit.importar'/>"/>
				<input class="boton_125_de_2_izq bot_mar_der"  type="submit"  name="action" value="<bean:message key='presentacion.avanzado.recursos.gestor.submit.exportar'/>"/>
				</fieldset>
			<!-- Fin Botones  -->
			<!-- Fin Botones  -->
<!--  FIN CAJA DE FORMULARIO   -->


</form>
</div>
<!-- HASTA AQUI EL REEEMPLAZO -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->


</div>
<!-- Fin plantilla contenido  -->
<!-- Fin plantilla contenido  -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
</emp:layout>
