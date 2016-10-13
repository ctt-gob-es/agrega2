<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tags/googleAnalytic.tld" prefix="analytic" %>
<bean:define id="sesion" name="<%=es.pode.buscador.presentacion.BuscarSession.SESSION_KEY%>" scope="session"/>

<tiles:insert definition="layout-descargarODE">

    <tiles:put name="title" type="string">
        <bean:message key="title.Comun"/>
    </tiles:put>

<tiles:put name="body-principal" type="string">
<%@ include file="/taglib-imports.jspf" %>

<analytic:googleAnalytic />
<form method="get" action="<html:rewrite action="/DescargarODECU/SeleccionarTipoFormatoAceptar"/>" >
<!--  estrellas -->	
 
	<header>
	<h2><bean:message key="descargar.tipoFormato.titulo"/>: ${form.titulo}</h2>
	</header>
	
	<section class="seccion clearfix" id="dbasicos">
		<fieldset class="clearfix"><html:radio property="formato" styleClass="boton_radio" styleId="Descarga01" name="form"  value="descargar.formatos.CONTENIDOS_VALUE" /><label for="Descarga01"><bean:message key="descargar.formatos.CONTENIDOS"/></label></fieldset>
		<fieldset class="clearfix"><html:radio property="formato" styleClass="boton_radio" styleId="Descarga01" name="form"  value="descargar.formatos.HTML_VALUE" /><label for="Descarga02"><bean:message key="descargar.formatos.HTML"/></label></fieldset>
		<fieldset class="clearfix"><html:radio property="formato" styleClass="boton_radio" styleId="Formato05" name="form"  value="descargar.formatos.METADATOS_PDF_VALUE" /><label for="Formato05" ><bean:message key="descargar.formatos.METADATOS_PDF_VALUE"/></label></fieldset>		
		<c:if test="${form.mostrarDescargaRecursoUnico=='true'}">
			<fieldset class="clearfix"><html:radio property="formato" styleClass="boton_radio" styleId="Formato06" name="form"  value="descargar.formatos.RECURSO_UNICO"/><label for="Formato06" ><bean:message key="descargar.formatos.RECURSO_UNICO" /></label></fieldset>
		</c:if>		
	</section>

	<p><bean:message key="descargar.formatos.texto"/>:</p>
	
	<section class="seccion clearfix" id="dformatos">
		<fieldset class="clearfix"><html:radio property="formato" styleClass="boton_radio" styleId="Formato01" name="form"  value="descargar.formatos.SCORM_2004_Sin_Sub_Manifiesto_VALUE" /><label for="Formato01" ><bean:message key="descargar.formatos.SCORM_2004_Sin_Sub_Manifiesto"/></label></fieldset>
		<fieldset class="clearfix"><html:radio property="formato" styleClass="boton_radio" styleId="Formato02" name="form"  value="descargar.formatos.SCORM_2004_VALUE" /><label for="Formato02" ><bean:message key="descargar.formatos.SCORM_2004"/></label></fieldset>
		<fieldset class="clearfix"><html:radio property="formato" styleClass="boton_radio" styleId="Formato03" name="form"  value="descargar.formatos.SCORM_1.2_VALUE" /><label for="Formato03" ><bean:message key="descargar.formatos.SCORM_1.2"/></label></fieldset>
		<fieldset class="clearfix"><html:radio property="formato" styleClass="boton_radio" styleId="Formato04" name="form"  value="descargar.formatos.IMS_CP_VALUE" /><label for="Formato04" ><bean:message key="descargar.formatos.IMS_CP"/></label></fieldset>
	</section>

	<bean:define id="texto" value="${sesion.textoLicencia}"/>	
	<c:if  test="${!empty texto }">
		<c:set var="longitud" value="${ fn:length(texto)}"/>
		<c:if test="${longitud>0 }">
			<p> <bean:message key="descargar.formatos.condicion"/></p>
			<section class="seccion clearfix" id="dclegal">
				<label for="con_legales" class="oculto"><bean:message key="descargar.formatos.condicion"/></label>
				<textarea readonly="readonly" title="<bean:message key="descargar.formatos.licencia"/>" id="con_legales">${texto}</textarea>
				<fieldset class="clearfix" id="licencia">
					<input type="checkbox" id="licenciaAceptar" checked="checked" name="licenciaAceptar" value="licenciaAceptar" /><label for="licenciaAceptar"><bean:message key="descargar.formatos.leido"/></label>
				</fieldset>
			</section>
		</c:if>
	</c:if>
	
	<fieldset class="botonera botonera_de_dos clearfix">
		<input type="hidden" name="idioma" value="${form.idioma}"/>
		<input type="hidden" name="titulo" value="${form.titulo}"/>
		<input type="hidden" name="identificadorODE" value="${form.identificadorODE}"/>
		<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>
		<input type="hidden" name="tieneIdentidadFederada" value="${form.tieneIdentidadFederada}"/>
		<input type="hidden" name="nodoOrigen" value="${form.nodoOrigen}"/>
		<input type="submit" title="Descargar" value="<bean:message key="listar.odecu.mostrar.resultados.detalles.descargar"/>" class="boton" />
		
		<a class="boton" style="float: left ! important; margin: 10px ! important; width: 140px; text-decoration: none;" id="b_previsualizar" href="javascript:history.back(1)">
		<bean:message key="descargar.boton.volver"/> </a>
	</fieldset>
</form>

</tiles:put>
</tiles:insert>
