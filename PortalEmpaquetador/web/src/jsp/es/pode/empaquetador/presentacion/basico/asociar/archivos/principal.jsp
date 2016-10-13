<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/WEB-INF/tlds/tags-empaquetador.tld" prefix="emp" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<bean:define id="urlGuardar"><html:rewrite action="/AsociacionArchivos/PrincipalEstructura"/></bean:define>
<bean:define id="urlAsistente"><html:rewrite action="/AsociacionArchivos/PrincipalEstructura"/></bean:define>
<bean:define id="urlEstructura"><html:rewrite action="/AsociacionArchivos/PrincipalEstructura"/></bean:define>
<bean:define id="urlArchivos"><html:rewrite action="/AsociacionArchivos/PrincipalArchivos"/></bean:define>
<bean:define id="urlImagen">/PortalEmpaquetador/CambiarImagen/CambiarImagen.do</bean:define>
<bean:define id="selected" value="estructura"/>
<emp:layout selected="${selected}" urlArchivos="${urlArchivos}" urlEstructura="${urlEstructura}" urlGuardar="${urlGuardar}" urlAsistente="${urlAsistente}" urlImagen="${urlImagen}" titleKey="title.Comun">
<!-- Inicio plantilla contenido  -->
<div class="plantilla_contenido" id="ventana_flotante">
<%@ include file="/layout/messages.jsp" %>


<h2><bean:message key="gestor_basico.principal.cabecera"/></h2>

<form id="asociacionArchivosPrincipalSubmitForm" method="post" action="<html:rewrite action="/AsociacionArchivos/PrincipalSubmit"/>" >


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

<table border="1" class="generica_02" id="archivos_basico" cellpadding="0" cellspacing="0" width="100%" summary="Tabla de Archivos">
<caption><strong><bean:message key="portalempaquetado.basico.asociar.archivos.principal"/></strong></caption>


 <c:set var="contador" value="true"/>
<c:set var="contadorRadio" value="0"/>
 <logic:iterate id="archivo" name="form" property="ficheros">
	<logic:equal name="contador" value="true">
		<tr class="tr_gris">
	</logic:equal>

	<logic:equal name="contador" value="false">
		<tr class="tr_blanco">
	</logic:equal>
	<c:set var="contador" value="${!contador}"/>
	<bean:define id="urlConHost"><rewrite:rewrite url="${archivo.href }"/></bean:define>
	<td valign="top" class="td_larga3"><a href="<emp:encodeURL url="${urlConHost }"/>" target="_blank" class="archivo">${archivo.href}</a></td>
	<td valign="top"  class="sin_b" >
	<label for="${archivo.href}" class="oculto" ><bean:message key="portalempaquetado.basico.asociar.archivos.principal.seleccione"/></label>
	<logic:equal name="contadorRadio" value="0" >
		<input type="radio" id="${archivo.href}"  name="principal" value="${archivo.href}" checked="checked"/></td>
	</logic:equal>
	<logic:notEqual name="contadorRadio" value="0" >
		<input type="radio" id="${archivo.href}"  name="principal" value="${archivo.href}"/></td>
	</logic:notEqual>
	<c:set var="contadorRadio" value="${contadorRadio+1}"/>	
</logic:iterate>
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
<input class="boton_125_de_2"  type="submit"  name="action" value="<bean:message key='portalempaquetado.basico.asociar.archivos.aceptar'/>" />
<input class="boton_125_de_2_izq"  type="submit"  name="action" value="<bean:message key='portalempaquetado.basico.asociar.archivos.cancelar'/>" />
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


</div>
<!--  FIN CAPA MADRE   -->
</emp:layout>