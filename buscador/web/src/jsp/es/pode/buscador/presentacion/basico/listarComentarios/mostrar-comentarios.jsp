<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tags/valoracion.tld" prefix="estrellas" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tags/googleAnalytic.tld" prefix="analytic" %>


<tiles:insert definition="layout-comentariosODE">

    <tiles:put name="title" type="string">
        <bean:message key="title.comun.comentarios"/>
    </tiles:put>

	<tiles:put name="body-principal" type="string">
	<%@ include file="/taglib-imports.jspf" %>		
		<!-- PRINCIPAL   -->
		<analytic:googleAnalytic />
		<jsp:include page="/layout/messages.jsp" flush="true" />

		<header>
		<h2>${form.tituloODE}</h2>
		</header>		
		<logic:notEmpty name="form" property="imagen">
			<section>
				<p><img src="${form.imagen}" class="imagenflotante" alt="Aplicaci&oacute;n" title="Aplicaci&oacute;n" />
				<br><br><br>
				</p>
			</section>
		</logic:notEmpty>
		<section class="seccion clearfix" id="dacomentarios">
			<h2><bean:message key="introducir.comentarios.ode.comentarios"/></h2>
			<div id="lista_de_comen">
				<!--  -->
				<logic:notEmpty name="form" property="comentarios">
				<logic:iterate name="form" property="comentarios" id="comentario">
					<div><em>${comentario.usuario}</em> <strong><fmt:formatDate value="${comentario.fecha}" pattern="dd/MM/yyyy HH:mm:ss"/>&nbsp;</strong>
					<p>${comentario.comentario}<br /></p></div>
				</logic:iterate>
				</logic:notEmpty>
				<!--  -->
			</div>
		</section>		
		<fieldset class="botonera botonera_de_dos clearfix">
			<logic:equal name="form" property="mostrarVuelta" value="true">
				<logic:equal name="form" property="busquedaSimpleAvanzada" value="BUSQUEDA_ARBOL">
					<form action="<html:rewrite action="/DetallarODECU/DetallarODECU.do"/>?identificadorODE=${form.idODE}&amp;idioma=${form.idiomaODE}&amp;mostrarVuelta=${form.mostrarVuelta}&amp;areaCurricularBusqueda=" method="post">
						<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>
						<input class="boton boton_flot"  type="submit"  value="<bean:message key="descargar.boton.volver"/>"/>
					</form>
				</logic:equal>
				<logic:notEqual name="form" property="busquedaSimpleAvanzada" value="BUSQUEDA_ARBOL">
					<form action="<html:rewrite action="/DetallarODECU/DetallarODECU.do"/>?identificadorODE=${form.idODE}&amp;idioma=${form.idiomaODE}&amp;mostrarVuelta=${form.mostrarVuelta}" method="post">
						<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>
						<input class="boton boton_flot"  type="submit"  value="<bean:message key="descargar.boton.volver"/>"/>
					</form>
				</logic:notEqual>
			</logic:equal>
			<logic:notEqual name="form" property="mostrarVuelta" value="true">
				<form action="<html:rewrite action="/DetallarODECU/DetallarODECU.do"/>?identificadorODE=${form.idODE}&amp;idioma=${form.idiomaODE}&amp;mostrarVuelta=${form.mostrarVuelta}" method="post">
					<input type="hidden" name="tipoLayoutBuscador" value="${form.tipoLayoutBuscador}"/>
					<input class="boton boton_flot"  type="submit"  value="<bean:message key="descargar.boton.volver"/>"/>
				</form>
			</logic:notEqual>
		</fieldset>
	</tiles:put>
</tiles:insert>
	
	
		
