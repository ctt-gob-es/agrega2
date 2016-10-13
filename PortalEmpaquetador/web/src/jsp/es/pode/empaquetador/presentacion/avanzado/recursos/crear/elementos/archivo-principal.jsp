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
<bean:define id="urlAsistente"><html:rewrite action="/CrearRecursoAvanzadoElementos/RecursoSubmitPrincipal"/>?action=Seleccionar Archivo</bean:define>
<bean:define id="selected" value="recursos"/>
<emp:layout selected="${selected}" urlArchivos="${urlArchivos}" urlRecursos="${urlRecursos}" urlSubmanifiestos="${urlSubmanifiestos}" urlOrganizaciones="${urlOrganizaciones}" urlGuardar="${urlGuardar}" urlAsistente="${urlAsistente}" titleKey="title.Comun">
<bean:define id="empaquetadorSesion" name="<%=es.pode.empaquetador.presentacion.EmpaquetadorSession.SESSION_KEY%>" scope="session"/>
<bean:define id="crearRecursoArchivoSession" name="<%=es.pode.empaquetador.presentacion.avanzado.recursos.crear.archivos.CrearRecursoArchivosSession.SESSION_KEY %>" scope="session"/>

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


<!--  INICIO CAPA MADRE   -->
<div id="capa_madre">



<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- ATENCION!! RREMPLAZAR ESTA CAPA DE LA  PLANTILLA DE CONTENIDO ("plantilla_contenido") POR LA QUE SE DESEE EN LA PLANTILLA BASE  -->
<!-- Inicio plantilla contenido  -->
<!-- Inicio plantilla contenido  -->
<div class="plantilla_contenido" id="ventana_flotante">
<%@ include file="/layout/messages.jsp" %>


<h2><bean:message key="portalempaquetado.avanzado.recursos.crear.archivo.principal.titulo"/></h2>

<form 
	method="post" 
	id="crearRecursoAvanzadoElementosArchivoPrincipalAceptarForm"
	action="<html:rewrite action="/CrearRecursoAvanzadoElementos/ArchivoPrincipalAceptar"/>" >
<p class="parrafo_comun" id="separacion2"><bean:message key="portalempaquetado.avanzado.recursos.crear.archivo.principal.mensaje"/></p>
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

<logic:notEmpty name="crearRecursoArchivoSession" property= "archivos">

		<table border="1" class="generica_02" cellpadding="0" cellspacing="0" width="100%" summary="Tabla de Recursos">
		<caption><strong><bean:message key="portalempaquetado.avanzado.recursos.crear.tablaArchivoPrincipal"/></strong></caption>

<c:set var="contador" value="true"/>


<bean:define id="principal" value="${form.principal}" />

<logic:iterate id="file" name="crearRecursoArchivoSession" property="archivos">
<logic:equal name="contador" value="true">
		<tr class="tr_gris">
</logic:equal>
<logic:equal name="contador" value="false">
		<tr class="tr_blanco">
</logic:equal>
<c:set var="contador" value="${!contador}"/>
			<td valign="top"  class="sin_b">
			<label for="${file.href}" class="oculto" >Seleccione </label>
		
		<html:radio styleId="${file.href}" name="form" property="principal" value="${file.href }"/></td>
		<bean:define id="urlConHost"><rewrite:rewrite url="${file.url}"/></bean:define>		
		<td valign="top" class="td_larga"><a target="_blank" href="<emp:encodeURL url="${urlConHost}"/>" class="archivo">${file.href}</a></td>
		</tr>

</logic:iterate>

		 </table>
</logic:notEmpty>
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
	 value="<bean:message key="portalempaquetado.avanzado.recursos.crear.archivo.principal.aceptar"/>" />
<input class="boton_125_de_2_izq"  type="submit" name="action" 
	 value="<bean:message key="portalempaquetado.avanzado.recursos.crear.archivo.principal.cancelar"/>" />
</fieldset>
<!-- Fin Botones  -->

<!-- Fin Botones  -->
</form>
<script type="text/javascript">
  document.forms[0].principal[0].focus();
</script>
</div>
<!-- Fin plantilla contenido  -->
<!-- Fin plantilla contenido  -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->


</div>
<!--  FIN CAPA MADRE   -->
</emp:layout>
