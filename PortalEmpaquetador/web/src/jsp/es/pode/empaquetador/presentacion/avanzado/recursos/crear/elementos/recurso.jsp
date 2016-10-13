<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tlds/tags-empaquetador.tld" prefix="emp" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<bean:define id="urlArchivos"><html:rewrite action="/CrearRecursoAvanzadoElementos/RecursoArchivos"/></bean:define>
<bean:define id="urlRecursos"><html:rewrite action="/CrearRecursoAvanzadoElementos/RecursoRecursos"/></bean:define>
<bean:define id="urlOrganizaciones"><html:rewrite action="/CrearRecursoAvanzadoElementos/RecursoOrganizaciones"/></bean:define>
<bean:define id="urlSubmanifiestos"><html:rewrite action="/CrearRecursoAvanzadoElementos/RecursoSubmanifiestos"/></bean:define>
<bean:define id="urlGuardar"><html:rewrite action="/CrearRecursoAvanzadoElementos/RecursoRecursos"/></bean:define>
<bean:define id="crearRecursoAvanzadoSesion" name="<%=es.pode.empaquetador.presentacion.avanzado.recursos.crear.CrearRecursoAvanzadoSession.SESSION_KEY%>" scope="session"/>
<bean:define id="urlAsistente"><html:rewrite action="/CrearRecursoAvanzado/TipoRecursoContinuar"/>?action=Continuar&tipo=${crearRecursoAvanzadoSesion.tipo}</bean:define>
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

<logic:equal name="crearRecursoAvanzadoSesion" property="modificar" value="false">
<h2> <bean:message key="portalempaquetado.avanzado.recursos.crear.paso2.titulo"/> </h2>
</logic:equal>
<logic:equal name="crearRecursoAvanzadoSesion" property="modificar" value="true">
<h2> <bean:message key="portalempaquetado.avanzado.recursos.modificar.paso2.titulo"/> </h2>
</logic:equal>

<!-- form archivos -->


<p class="parrafo_comun" id="separacion2">
	<bean:message key="portalempaquetado.avanzado.recursos.crear.paso2.mensaje"/>
</p>

<form 
	method="post" 
	id="crearRecursoAvanzadoElementosRecursoSubmitArchivosForm"
	action="<html:rewrite action="/CrearRecursoAvanzadoElementos/RecursoSubmitArchivos"/>" >
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris"  id="conmargen">
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
	
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div id="formulario_02" >

			<h3 class="h3_generico">
				<bean:message key="portalempaquetado.avanzado.recursos.crear.paso2.archivos"/>
			</h3>
			
<logic:empty  name="form" property="archivos">
<!-- no existen archivos -->
			<!--  INICIO CAPA TABLA Añadir archivos   -->
			<!--  INICIO CAPA TABLA Añadir archivos   -->					
 		 <div class="caja_tabla bordeada limpiar_breadcrumb" >
			<table border="1" class="generica_02" cellpadding="0" cellspacing="0" width="100%" summary="<bean:message key="portalempaquetado.avanzado.recursos.crear.archivo.tablaArchivos"/>">
			<caption><strong><bean:message key="portalempaquetado.avanzado.recursos.crear.archivo.tablaArchivos"/></strong></caption>
			<tr class="tr_beige">
				<td valign="top" class="td_larga acentrado"> <bean:message key="portalempaquetado.avanzado.recursos.crear.paso2.noArchivos"/> </td>

				</tr>
			</table>
			</div>
			</div>
			<fieldset class="tipo ft_centrada">
				<input class="boton_125"  type="submit"  style="width:150px !important" name="action"
				value="<bean:message key="portalempaquetado.avanzado.recursos.crear.paso2.archivos.anadir"/>" />
			</fieldset>
			<br class="limpiar" />
			<!--  FIN CAPA TABLA Añadir archivos   -->
			<!--  FIN CAPA TABLA Añadir archivos   -->
		
<!-- no existen archivos - FIN -->
</logic:empty>
<logic:notEmpty  name="form" property="archivos">
<!-- existen archivos -->
			<!--  INICIO CAPA TABLA   -->
			<!--  INICIO CAPA TABLA   -->

		<div class="caja_tabla bordeada limpiar_breadcrumb" >
			<table border="1" class="generica_02" cellpadding="0" cellspacing="0" width="100%" summary="Tabla de Archivos">
			<caption><strong><bean:message key="portalempaquetado.avanzado.recursos.crear.archivo.tablaArchivos"/></strong></caption>

<c:set var="contador" value="true"/>
<logic:iterate id="archivo" name="form" property="archivos">
<logic:equal name="contador" value="true">
			<tr class="tr_gris">
</logic:equal>
<logic:equal name="contador" value="false">
			<tr class="tr_blanco">
</logic:equal>
<c:set var="contador" value="${!contador}"/>
<bean:define id="principal" value="${form.principal}"></bean:define>
				<td valign="top"  class="sin_b">
					<label for="${fn:replace(archivo.href,'/','.') }" class="oculto" ><bean:message key="portalempaquetado.avanzado.recursos.crear.paso2.archivos.seleccione"/></label>
					<input id="${fn:replace(archivo.href,'/','.') }" type="checkbox" name="hrefRowSelectionAsArray" value="${archivo.href }" /></td>
					<bean:define id="urlConHost"><rewrite:rewrite url="${empaquetadorSesion.ode.localizadorURL}/${archivo.href}"/></bean:define>
					<logic:empty name="principal">
						<td valign="top" class="td_larg"><a target="_blank" href="<emp:encodeURL url="${urlConHost}"/>" class="archivo">${archivo.href }</a></td>	
					</logic:empty>
					<logic:notEmpty name="principal"> 
			<logic:equal name="principal" value="${archivo.href}">
				<td valign="top" class="td_larg"><b><a target="_blank" href="<emp:encodeURL url="${urlConHost}"/>" class="archivo">${archivo.href }</a></b></td>
			</logic:equal>
			<logic:notEqual name="principal" value="${archivo.href}">
				<td valign="top" class="td_larg"><a target="_blank" href="<emp:encodeURL url="${urlConHost}"/>" class="archivo">${archivo.href }</a></td>	
			</logic:notEqual>
			</logic:notEmpty>
			<logic:empty name="archivo" property="lom">
				<td valign="top" class="meta_datos_mas">
			</logic:empty>
			<logic:notEmpty name="archivo" property="lom">
				<td valign="top" class="meta_datos">
			</logic:notEmpty>
				<span class="oculto">-</span>
				<a href="<html:rewrite action="/GestorRecursos/GestorRecursosMetadatos"/>?href=${archivo.href}&amp;identifier=${crearRecursoAvanzadoSesion.identifier }&amp;returnURL=<html:rewrite action="/CrearRecursoAvanzadoElementos/CrearRecursoAvanzadoElementos"/>"><bean:message key="portalempaquetado.avanzado.recursos.crear.paso2.archivos.metadatos"/></a></td>
			</tr>
</logic:iterate>
			</table>
		</div>
	</div>
			<!-- Inicio Botones  -->
			<!-- Inicio Botones  -->
			<fieldset class="tipo_interior_04 ft_centrada" >
				<input class="boton_125_de_2"  type="submit" name="action"
					value="<bean:message key="portalempaquetado.avanzado.recursos.crear.paso2.archivos.eliminar"/>" />
				<input class="boton_125_de_2_izq"  type="submit" name="action"
					value="<bean:message key="portalempaquetado.avanzado.recursos.crear.paso2.archivos.anadir"/>" />
			</fieldset>
			<!-- Fin Botones  -->
			<!-- Fin Botones  -->
			<br class="limpiar" />
			<!--  FIN CAPA TABLA   -->		
			<!--  FIN CAPA TABLA   -->

<!-- existen archivos - FIN -->
</logic:notEmpty>
				<!--  FIN CAJA DE FORMULARIO   -->



			</div>
		</div>
	</div>
</div>

<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->
</form>

<br/>

<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->

<form method="post" id="crearRecursoAvanzadoElementosRecursoSubmitPrincipalForm" action="<html:rewrite action="/CrearRecursoAvanzadoElementos/RecursoSubmitPrincipal"/>"/>
<div class="globo_gris" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div id="formulario" >
			<h3 class="h3_generico">
				<bean:message key="presentacion.avanzado.recursos.crear.elementos.principal.h3"/>
			</h3>
			<div class="caja_tabla bordeada limpiar_breadcrumb" >
			<table border="1" class="generica_02" cellpadding="0" cellspacing="0" width="100%" summary="<bean:message key="presentacion.avanzado.recursos.crear.elementos.principal.h3"/>">
			<caption><strong><bean:message key="presentacion.avanzado.recursos.crear.elementos.principal.h3"/></strong></caption>
			<c:choose>
			<c:when test="${empty form.principal}">
			<tr class="tr_beige">
			<td valign="top" class="td_larga acentrado"> <bean:message key="presentacion.avanzado.recursos.crear.elementos.vacio"/> </td>
			</tr>
			</c:when>
			<c:otherwise>
			<tr class="tr_blanco">
			<bean:define id="urlConHost"><rewrite:rewrite url="${empaquetadorSesion.ode.localizadorURL}/${form.principal}"/></bean:define>
			<td valign="top" class="td_larg"><a target="_blank" href="<c:choose><c:when test="${emp:esFichero(form.principal,form.archivos)}"><emp:encodeURL url="${urlConHost}"/></c:when><c:otherwise><emp:encodeURL url="${form.principal}"/></c:otherwise></c:choose>">${form.principal }</a></td>
			</tr>
			</c:otherwise>
			</c:choose>
			</table>
			</div>
			</div>
<!-- Inicio Botones  -->
			<!-- Inicio Botones  -->
			<fieldset class="tipo_interior_02" >
				<input class="boton_125_de_2"  type="submit" name="action"
					value="<bean:message key="presentacion.avanzado.recursos.crear.elementos.principal.elegir"/>" />
					<br class="oculto" /><br class="oculto" />
				<input class="boton_125_de_2_izq"  type="submit" name="action"
					value="<bean:message key="presentacion.avanzado.recursos.crear.elementos.principal.editar"/>" />
			</fieldset>
			<!-- Fin Botones  -->
			<!-- Fin Botones  -->
			<br class="limpiar" />
			<!--  FIN CAPA TABLA   -->		
			<!--  FIN CAPA TABLA   -->

			</div>
		</div>
	</div>
</div>
</form>

<br/>
<!-- form dependencias -->
<form 
	method="post"
	id="crearRecursoAvanzadoElementosRecursoSubmitDependenciasForm"
	action="<html:rewrite action="/CrearRecursoAvanzadoElementos/RecursoSubmitDependencias"/>" >

<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" id="conmargen2">
	<div class="globo_gris_01">
		<div class="globo_gris_02">

			<div class="globo_gris_03">
			
			
			
			
			
			
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div id="formulario_03" >

			<h3 class="h3_generico">
				<bean:message key="portalempaquetado.avanzado.recursos.crear.paso2.dependencias"/>
			</h3>
			
	<logic:empty  name="form" property="dependencias">
				
										
 		 <div class="caja_tabla bordeada limpiar_breadcrumb" >
			<table border="1" class="generica_02" cellpadding="0" cellspacing="0" width="100%" summary="Tabla de Dependencias">
			<caption><strong><bean:message key="portalempaquetado.avanzado.recursos.crear.dependencias.tablaDepen"/></strong></caption>
			<tr class="tr_beige">
				<td valign="top" class="td_larga acentrado"><bean:message key="portalempaquetado.avanzado.recursos.crear.paso2.noDependencias"/> </td>

				</tr>
			</table>
			</div>
			</div>
			<fieldset class="tipo ft_centrada">
				<input class="boton_125"  type="submit"  style="width:150px !important" name="action"
				value="<bean:message key="portalempaquetado.avanzado.recursos.crear.paso2.dependencias.anadir"/>" />
			</fieldset>
			<br class="limpiar" />
			<!--  FIN CAPA TABLA Añadir archivos   -->
			<!--  FIN CAPA TABLA Añadir archivos   -->

<!-- no existen archivos - FIN -->
</logic:empty>

<logic:notEmpty  name="form" property="dependencias">
<!-- existen archivos -->
			<!--  INICIO CAPA TABLA   -->
			<!--  INICIO CAPA TABLA   -->

		<div class="caja_tabla bordeada limpiar_breadcrumb" >
			<table border="1" class="generica_02" cellpadding="0" cellspacing="0" width="100%" summary="Tabla de Dependencias">
			<caption><strong><bean:message key="portalempaquetado.avanzado.recursos.crear.dependencias.tablaDepen"/></strong></caption>

<c:set var="contador" value="true"/>

<logic:iterate id="dependencia" name="form" property="dependencias">
<logic:equal name="contador" value="true">
			<tr class="tr_gris">
</logic:equal>
<logic:equal name="contador" value="false">
			<tr class="tr_blanco">
</logic:equal>
<c:set var="contador" value="${!contador}"/>
				<td valign="top"  class="sin_b">
					<label for="${dependencia.href }" class="oculto" ><bean:message key="portalempaquetado.avanzado.recursos.crear.paso2.dependencias.seleccione"/></label>
					<input type="checkbox"  name="identifierRowSelectionAsArray" value="${dependencia.identifier }" /></td>
				
				<td valign="top" class="td_largo">	
					<logic:notEmpty name="dependencia" property="href">
					<bean:define id="urlConHost"><rewrite:rewrite url="${empaquetadorSesion.ode.localizadorURL}/${dependencia.href}"/></bean:define>
							<a href="<emp:encodeURL url="${urlConHost}"/>" target="_blank" class="recurso">${dependencia.href}</a>
					</logic:notEmpty>
					<logic:empty name="dependencia" property="href">
							<a href="#" class="recurso">${dependencia.identifier}</a>
					</logic:empty>
				</td>
				
			</tr>
</logic:iterate>

			</table>
		</div>
	</div>
	
			<!-- Inicio Botones  -->
			<!-- Inicio Botones  -->
			
			<br class="oculto" />
			<fieldset class="tipo_interior_02" >
				<input class="boton_125_de_2"  type="submit" name="action"
					value="<bean:message key="portalempaquetado.avanzado.recursos.crear.paso2.dependencias.eliminar"/>" />
			<br class="oculto" /><br class="oculto" />
			
				<input class="boton_125_de_2_izq bot_mar_der" style="width:150px !important" type="submit"  name="action"
					value="<bean:message key="portalempaquetado.avanzado.recursos.crear.paso2.dependencias.anadir"/>" />

			</fieldset>
			<!-- Fin Botones  -->
			<!-- Fin Botones  -->
			<br class="limpiar" />
			<!--  FIN CAPA TABLA   -->		
			<!--  FIN CAPA TABLA   -->

		

<!-- existen archivos - FIN -->
</logic:notEmpty>
				<!--  FIN CAJA DE FORMULARIO   -->

			</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->


</form>

<form
	method="post"
	id="crearRecursoAvanzadoElementosRecursoCrearForm"
	action="<html:rewrite action="/CrearRecursoAvanzadoElementos/RecursoCrear"/>">

		<!-- Inicio Botones  -->
		<!-- Inicio Botones  -->
		<fieldset class="tipo ft_centrada">
		
		<input class="boton_125_de_2"  type="submit" name="action" value="<bean:message key="portalempaquetado.avanzado.recursos.aceptar"/>" /> 
		<input class="boton_125_de_2_izq"  type="submit" name="action" value="<bean:message key="portalempaquetado.avanzado.recursos.volver"/>" /> 
		</fieldset>
		<!-- Fin Botones  -->
		<!-- Fin Botones  -->

</form>
			<br class="limpiar" />

</div>
<!-- Fin plantilla contenido  -->
<!-- Fin plantilla contenido  -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
</emp:layout>
