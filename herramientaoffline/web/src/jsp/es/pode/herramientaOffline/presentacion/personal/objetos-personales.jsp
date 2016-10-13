<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<tiles:insert definition="layout-offline">
	<tiles:put name="title" type="string">
		<bean:message key="title.Comun" />
	</tiles:put>
	<tiles:put name="body" type="string">
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- ATENCION!! RREMPLAZAR ESTA CAPA DE LA  PLANTILLA DE CONTENIDO ("plantilla_contenido") POR LA QUE SE DESEE EN LA PLANTILLA BASE  -->
<!-- Inicio plantilla contenido  -->
<div class="plantilla_contenido">
<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
<jsp:include page="/layout/messages.jsp" flush="true" /> 
<!-- Inicio  PESTANIAS de FICHA -->
<!-- Inicio  PESTANIAS de FICHA -->

<div id="ficha_pestanias" >
<div><h2><bean:message key="CarpetaPersonal.titulo"/></h2></div>
<ul>
<li id="pest_activa"><a href="#" id="seleccionada"><bean:message key="CarpetaPersonal.pestania"/></a></li>
</ul>
</div>
<!-- Fin PESTANIAS de FICHA -->
<!-- Fin PESTANIAS de FICHA -->


<!-- Inicio CONTENIDO PESTANIAS -->

<!-- Inicio CONTENIDO PESTANIAS -->
<div class="interno_ficha">
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- ATENCION!! E3TA ES LA CAPA QUE DEBE REEMPLAZARSE POR LA DEL MISMO NOMBRE (plantilla contenido pestanias ) EN LA PLANTILLA DE  CONTENIDO  CON EL CONTENIDO DE FICHA -->
<div class="plantilla_contenido_pestanias">
<form method="post" action="<html:rewrite action="/CarpetaPersonal/ObjetosPersonalesSubmit"/>" >
<!-- CAJA TABLA -->
<!-- CAJA TABLA -->
<div class="caja_tabla" >
<h3 class="h3_generico"><bean:message key="CarpetaPersonal.pestania"/></h3>
<bean:define id="summary"><bean:message key="CarpetaPersonal.tabla.summary"/></bean:define>
<display:table
			name="${form.odes}" requestURI="" export="false" id="fila"
			class="administracion_tareas" style="border:1;width:100%;"
			cellpadding="0" cellspacing="0" summary="${summary}" sort="list"
			pagesize="20">
			<display:caption>
				<bean:message key="CarpetaPersonal.tabla.summary" />
			</display:caption>
			<display:setProperty name="css.tr.odd" value="tr_gris" />
			<display:setProperty name="css.tr.even" value="tr_blanco" />
			<display:setProperty name="basic.show.header" value="false" />
			<!--  ******************** COLUMNA DE CHECKBOX ***********************-->
			<display:column style="valign:top;" class="sin_b">
				<input type="checkbox" name="idOdeRowSelectionAsArray"
					value="${fila.idODE}"
					title="<bean:message key="comun.seleccione"/>" />
			</display:column>
			<!--  ******************** COLUMNA DE TITULO ***********************-->
			<display:column style="valign:top;" class="tar10">
				<a
					href="/${initParam.url_visualizador}?identificador=${fila.idODE}&amp;secuencia=true&amp;comunidadAgrega=false"
					class="paquete" target="_blank">${fn:escapeXml(fila.titulo)}</a>
			</display:column>
			<!--  ******************** CATALOGAR ***********************-->
			<display:column style="valign:top;">
				<a href="/${initParam.url_objetos}?identificador=${fila.idODE}&amp;titulo=${fila.titulo}&amp;catalogadorDirecto=CD&amp;urlCerrar=/${initParam.url_OfflinePersonales}">
					<bean:message key="CarpetaPersonal.link.catalogar" />
				</a>
			</display:column>
			<!-- ********************** COLUMNA MODIFICAR*********************** -->
			<display:column style="valign:top;" class="tar11">
				<span class="oculto">-</span>
				<a href="/${initParam.url_objetos}?identificador=${fila.idODE}&amp;urlCerrar=<html:rewrite action="/CarpetaPersonal/CarpetaPersonal"/>"><bean:message key="CarpetaPersonal.link.modificar"/></a>
			</display:column>
			<!-- ********************** COLUMNA DESCARGAR ********************** -->
			<display:column style="valign:top;">
				<span class="oculto">-</span>
				<a href="<html:rewrite action="/CarpetaPersonal/ObjetosPersonalesExportar"/>?idOde=${fila.idODE}">
					<bean:message key="CarpetaPersonal.link.descargar" />
				</a>
			</display:column>
			<!-- *********************** COLUMNA VALIDAR************************ -->
			<display:column style="valign:top;" class="ejec">
				<span class="oculto">-</span>
				<a href="<html:rewrite action="/CarpetaPersonal/ObjetosPersonalesValidar"/>?idOde=${fila.idODE}">
					<bean:message key="CarpetaPersonal.link.validar" />
				</a>
			</display:column>
</display:table>
<!-- No se cierra el div aqui porque se hace en el archivo displaytag.properties al paginar -->
<c:if test="${fn:length(form.odes) < 1}" >
	</div>
</c:if>

<!-- Inicio Botones  -->
<!-- Inicio Botones  -->

<fieldset >
<input class="boton_125_de_2" name="action" type="submit"  value="<bean:message key="CarpetaPersonal.boton.eliminar"/>" />

<br class="oculto" /><br class="oculto" />
<input class="boton_125_de_2 bot_mar_der" name="action" type="submit"  value="<bean:message key="CarpetaPersonal.boton.subir"/>" />
<input class="boton_125_de_2_izq bot_mar_der" name="action" type="submit"  value="<bean:message key="CarpetaPersonal.boton.crear"/>" />
<input class="boton_125_de_2_izq bot_mar_der" name="action" type="submit"  value="<bean:message key="CarpetaPersonal.boton.importar"/>" />
<input class="boton_125_de_2_izq bot_mar_der" name="action" type="submit"  value="<bean:message key="CarpetaPersonal.boton.crearMetadato"/>" />
</fieldset>
<!-- Fin Botones  -->
<!-- Fin Botones  -->
</form>
</div>
<!-- HASTA AQUI EL REEEMPLAZO -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
</div>
<!-- Fin CONTENIDO PESTANIAS -->
<!-- Fin CONTENIDO PESTANIAS -->
</div>
<!-- Fin plantilla contenido  -->
<!-- Fin plantilla contenido  -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
	</tiles:put>
</tiles:insert>