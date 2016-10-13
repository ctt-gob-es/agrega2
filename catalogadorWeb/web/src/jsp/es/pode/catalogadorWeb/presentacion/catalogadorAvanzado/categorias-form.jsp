<%@ include file="/taglib-imports.jspf" %>
<html:xhtml/>

<tiles:insert definition="layout-avanzadoInicio">
<tiles:put name="title" type="string">
	<bean:message key="title.Avanzado"/>
</tiles:put>

<tiles:put name="body" type="string">
<%@ include file="/es/pode/catalogadorWeb/presentacion/catalogadorAvanzado/categorias-form-vars.jspf" %>

<c:if test="${(catalogadorAvSession.mensajeAsistente != 'null')  && (!empty catalogadorAvSession.mensajeAsistente)}">
	<div class="bocadillo"> 
		<div class="bocadillo_int"> 
			<div> 
				<strong class="titulo_boc"><bean:message key="catalogadorAvanzado.Asistente.Bocadillo.Titulo"/></strong>
				<p>${catalogadorAvSession.mensajeAsistente }</p> 
			</div> 
		</div>
	</div>
</c:if>

<div class="plantilla_contenido" id="rea">

<jsp:include page="/layout/messages.jsp" flush="true"/>


<form method="post" action="<html:rewrite action="/CatalogadorAvanzado/CategoriasFormSubmit"/>" >

<p class="parrafo_comun" id="separacion2"><bean:message key="catalogadorAvanzado.Explicacion"/></p>
<div class="caja_tabla" >
<table border="1" class="administracion_noticias" cellpadding="0" cellspacing="0" width="100%" summary="<bean:message key="catalogadorAvanzado.Tabla"/>">
<caption><strong><bean:message key="catalogadorAvanzado.Tabla"/></strong></caption>
<c:set var="sesion" value="${catalogadorAvSession.MDSesion}"/> 

<tr class="tr_gris">
	<c:if test="${empty sesion.general }">
		<td valign="top" class="new meta_datos_mas"><strong><bean:message key="catalogadorAvanzado.General"/></strong></td>
		<td valign="top"><span class="oculto">-</span><html:link action="/CatalogadorAvanzado/CategoriasFormModificarGeneral"><bean:message key="catalogadorAvanzado.Modificar"/></html:link></td>
	</c:if>
	<c:if test="${!empty sesion.general }"> 
		<td valign="top" class="new meta_datos"><strong><bean:message key="catalogadorAvanzado.General"/></strong></td>
		<td valign="top"><span class="oculto">-</span><html:link action="/CatalogadorAvanzado/CategoriasFormModificarGeneral"><bean:message key="catalogadorAvanzado.Modificar"/></html:link></td>
	</c:if>
</tr>
<tr class="tr_blanco">
	<c:if test="${empty sesion.lifeCycle }">
		<td valign="top" class="new meta_datos_mas"><strong><bean:message key="catalogadorAvanzado.CicloDeVida"/></strong></td>
		<td valign="top"><span class="oculto">-</span><html:link action="/CatalogadorAvanzado/CategoriasFormModificarCicloDeVida"><bean:message key="catalogadorAvanzado.Modificar"/></html:link></td>
	</c:if>
	<c:if test="${!empty sesion.lifeCycle }">
		<td valign="top" class="new meta_datos"><strong><bean:message key="catalogadorAvanzado.CicloDeVida"/></strong></td>
		<td valign="top"><span class="oculto">-</span><html:link action="/CatalogadorAvanzado/CategoriasFormModificarCicloDeVida"><bean:message key="catalogadorAvanzado.Modificar"/></html:link></td>
	</c:if> 
</tr>
<tr class="tr_gris">
	<c:if test="${empty sesion.metaMetadata }">
		<td valign="top" class="new meta_datos_mas"><strong><bean:message key="catalogadorAvanzado.MetaMetadatos"/></strong></td>
		<td valign="top"><span class="oculto">-</span><html:link action="/CatalogadorAvanzado/CategoriasFormModificarMetaMetadatos"><bean:message key="catalogadorAvanzado.Modificar"/></html:link></td>
	</c:if>
	<c:if test="${!empty sesion.metaMetadata }">
		<td valign="top" class="new meta_datos"><strong><bean:message key="catalogadorAvanzado.MetaMetadatos"/></strong></td>
		<td valign="top"><span class="oculto">-</span><html:link action="/CatalogadorAvanzado/CategoriasFormModificarMetaMetadatos"><bean:message key="catalogadorAvanzado.Modificar"/></html:link></td>
	</c:if> 
</tr>
<tr class="tr_blanco">
	<c:if test="${empty sesion.technical }">
		<td valign="top" class="new meta_datos_mas"><strong><bean:message key="catalogadorAvanzado.Tecnica"/></strong></td>
		<td valign="top"><span class="oculto">-</span><html:link action="/CatalogadorAvanzado/CategoriasFormModificarTecnica"><bean:message key="catalogadorAvanzado.Modificar"/></html:link></td>
	</c:if>
	<c:if test="${!empty sesion.technical }">
		<td valign="top" class="new meta_datos"><strong><bean:message key="catalogadorAvanzado.Tecnica"/></strong></td>
		<td valign="top"><span class="oculto">-</span><html:link action="/CatalogadorAvanzado/CategoriasFormModificarTecnica"><bean:message key="catalogadorAvanzado.Modificar"/></html:link></td>
	</c:if> 
</tr>
<tr class="tr_gris">
	<c:if test="${empty sesion.educational }">
		<td valign="top" class="new meta_datos_mas"><strong><bean:message key="catalogadorAvanzado.UsoEducativo"/></strong></td>
		<td valign="top"><span class="oculto">-</span><html:link action="/CatalogadorAvanzado/CategoriasFormModificarUsoEducativo"><bean:message key="catalogadorAvanzado.Modificar"/></html:link></td>
	</c:if>
	<c:if test="${!empty sesion.educational }">
		<td valign="top" class="new meta_datos"><strong><bean:message key="catalogadorAvanzado.UsoEducativo"/></strong></td>
		<td valign="top"><span class="oculto">-</span><html:link action="/CatalogadorAvanzado/CategoriasFormModificarUsoEducativo"><bean:message key="catalogadorAvanzado.Modificar"/></html:link></td>
	</c:if> 
</tr>
<tr class="tr_blanco">
	<c:if test="${empty sesion.derechos }">
		<td valign="top" class="new meta_datos_mas"><strong><bean:message key="catalogadorAvanzado.Derechos"/></strong></td>
		<td valign="top"><span class="oculto">-</span><html:link action="/CatalogadorAvanzado/CategoriasFormModificarDerechos.do"><bean:message key="catalogadorAvanzado.Modificar"/></html:link></td>
	</c:if>
	<c:if test="${!empty sesion.derechos }">
		<td valign="top" class="new meta_datos"><strong><bean:message key="catalogadorAvanzado.Derechos"/></strong></td>
		<td valign="top"><span class="oculto">-</span><html:link action="/CatalogadorAvanzado/CategoriasFormModificarDerechos"><bean:message key="catalogadorAvanzado.Modificar"/></html:link></td>
	</c:if>
</tr>
<tr class="tr_gris">
	<c:if test="${empty sesion.relaciones }">
		<td valign="top" class="new meta_datos_mas"><strong><bean:message key="catalogadorAvanzado.Relacion"/></strong></td>
		<td valign="top"><span class="oculto">-</span><html:link action="/CatalogadorAvanzado/CategoriasFormModificarRelacion"><bean:message key="catalogadorAvanzado.Modificar"/></html:link></td>
	</c:if>
	<c:if test="${!empty sesion.relaciones }">
		<td valign="top" class="new meta_datos"><strong><bean:message key="catalogadorAvanzado.Relacion"/></strong></td>
		<td valign="top"><span class="oculto">-</span><html:link action="/CatalogadorAvanzado/CategoriasFormModificarRelacion"><bean:message key="catalogadorAvanzado.Modificar"/></html:link></td>
	</c:if>
</tr>
<tr class="tr_blanco">
	<c:if test="${empty sesion.anotaciones }">
		<td valign="top" class="new meta_datos_mas"><strong><bean:message key="catalogadorAvanzado.Anotacion"/></strong></td>
		<td valign="top"><span class="oculto">-</span><html:link action="/CatalogadorAvanzado/CategoriasFormModificarAnotacion"><bean:message key="catalogadorAvanzado.Modificar"/></html:link></td>
	</c:if>
	<c:if test="${!empty sesion.anotaciones }">
		<td valign="top" class="new meta_datos"><strong><bean:message key="catalogadorAvanzado.Anotacion"/></strong></td>
		<td valign="top"><span class="oculto">-</span><html:link action="/CatalogadorAvanzado/CategoriasFormModificarAnotacion"><bean:message key="catalogadorAvanzado.Modificar"/></html:link></td>
	</c:if>
</tr>
<tr class="tr_gris">
	<c:if test="${empty sesion.clasificaciones }">
		<td valign="top" class="new meta_datos_mas"><strong><bean:message key="catalogadorAvanzado.Clasificacion"/></strong></td>
		<td valign="top"><span class="oculto">-</span><html:link action="/CatalogadorAvanzado/CategoriasFormModificarClasificacion"><bean:message key="catalogadorAvanzado.Modificar"/></html:link></td>
	</c:if>
	<c:if test="${!empty sesion.clasificaciones }">
		<td valign="top" class="new meta_datos"><strong><bean:message key="catalogadorAvanzado.Clasificacion"/></strong></td>
		<td valign="top"><span class="oculto">-</span><html:link action="/CatalogadorAvanzado/CategoriasFormModificarClasificacion"><bean:message key="catalogadorAvanzado.Modificar"/></html:link></td>
	</c:if>
</tr>
</table>
</div>


<!-- Inicio Botones  -->
<!-- Inicio Botones  -->
<div id="botones_centrados" style="text-align:left !important;margin-top:10px;"> 
	<fieldset id="esp_cata"> 
		<input class="boton_125_de_2_izq" type="submit" name="accion" value="<bean:message key="catalogadorAvanzado.Validar"/>" />
		<input class="boton_125_de_2_izq" type="submit" name="accion" value="<bean:message key="catalogadorAvanzado.Cancelar"/>" />
		<c:choose>
			<c:when test="${empty catalogadorAvSession.crearMetadato}">
				<input class="boton_125_de_2_izq" type="submit" name="accion" value="<bean:message key="catalogadorAvanzado.Aceptar"/>" />
			</c:when>
			<c:otherwise>
				<input class="boton_125_de_2_izq" type="submit" name="accion" id="desactivado" disabled="true" value="<bean:message key="catalogadorAvanzado.Aceptar"/>" />
			</c:otherwise>
		</c:choose>
	</fieldset> 
	<input class="boton_65_de_3_der  bot_mar_izq" style="margin-right:0;" type="submit" value="<bean:message key="avanzado.Ir"/>" />
	<label class="oculto" for="mas_acciones"><bean:message key="avanzado.MasAcciones"/></label>
	<select name="accionSubmitCombo" id="mas_acciones" title="<bean:message key="avanzado.MasAcciones"/>" >
		<option value="#" selected="selected"><bean:message key="avanzado.MasAcciones"/></option>
		<option value="<bean:message key="avanzado.Traducir"/>"><bean:message key="avanzado.Traducir"/></option>
		<option value="<bean:message key="avanzado.Exportar"/>" class="oscura"><bean:message key="avanzado.Exportar"/></option>
		<option value="<bean:message key="avanzado.Importar"/>"><bean:message key="avanzado.Importar"/></option>
	</select>
	<span class="oculto">-</span>
	<br class="oculto" />
	<span class="oculto">-</span>
</div>

<!-- Fin Botones  -->
<!-- Fin Botones  -->
</form>
</div>	<!-- Fin plantilla contenido  -->
</tiles:put>

</tiles:insert>
