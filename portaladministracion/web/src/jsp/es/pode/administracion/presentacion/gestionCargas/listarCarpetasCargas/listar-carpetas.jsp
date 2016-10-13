<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html:xhtml/>

<tiles:insert definition="layout-administrador">

<tiles:put name="title" type="string">
      <bean:message key="title.Comun"/>
</tiles:put>


<tiles:put name="body" type="string">

<%@ include file="/taglib-imports.jspf" %>
<%@ include file="/es/pode/administracion/presentacion/gestionCargas/listarCarpetasCargas/listar-carpetas-vars.jspf" %>


<!--		Inicio plantilla contenido		-->

<div class="plantilla_contenido">


<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
<jsp:include page="/layout/messages.jsp" flush="true" />
<!--		Inicio  PESTANIAS de FICHA		 -->
	<div id="ficha_pestanias">
		<div><h2><bean:message key="cabecera.cargas.contenidos"/></h2></div>
		<ul>
			<li id="pest_activa"><a href="<html:rewrite action="/ListarCargasEjecutadas/ListarCargasEjecutadas.do"/>" id="seleccionada"><bean:message key="cargas.pestania.listado"/></a></li>
			<li><a href="<html:rewrite action="/ListarInformesCarga/ListarInformesCarga.do"/>"><bean:message key="informes.pestania.listado" /></a></li>
			
		</ul>
	</div>
	<!--		Fin  PESTANIAS de FICHA		-->
	<!--		Inicio CONTENIDO PESTANIAS		-->
<!--		Inicio del formulario		-->

	<!--		Inicio CONTENIDO PESTANIAS		-->
<div class="interno_ficha">
	<div class="plantilla_contenido_pestanias">
	<form id="listarCarpetasCargasListarCarpetasCargasVolverForm" action='<html:rewrite action="/ListarCarpetasCargas/ListarCarpetasVolver"/>' method="post" >	
	<div class="globo_gris">
	<div class="globo_gris_01">
		<div class="globo_gris_02">

			<div class="globo_gris_03">
				<!--  INICIO CAJA DE FORMULARIO   -->
				<div id="formulario" >
				<input type="hidden" name="nombreTarea" value="${form.nombreTarea}"/>
				<input type="hidden" name="nombreLote" value="${form.nombreLote}"/>
				<input type="hidden" name="fechaFin" value="${form.fechaFin}"/>
					<h2><bean:message key="carpetas.pestaina.listado"/></h2>

					<h3><b><bean:message key="cargas.cargasEjecutadas"/>: </b><span>${form.nombreTarea}</span></h3>
					<h3><b><bean:message key="lote.cargasEjecutadas"/>: </b><span>${form.nombreLote}</span></h3>
					<h3><b><bean:message key="tareas.cabeceraFecha"/>: </b><span>${form.fechaFin}</span></h3>
				
	
				<div class="caja_tabla" id="ficha_dentro_gris"  >
	
		
	
		<!--		Inicio de la caja_tabla		-->
		
		<bean:define id="atributo" value="id" scope="page"/>
		<display:table name="${form.carpetas}" uid="fila" requestURI=""
		        export="false" pagesize="8" sort="list" style="border:1;width:100%;" class="administracion_tareas"
				cellpadding="0" cellspacing="0" summary="${summary}"
				decorator="es.pode.administracion.presentacion.MarcarModificado">
				
				<display:setProperty name="css.tr.odd" value="tr_gris"/>
				<display:setProperty name="css.tr.even" value="tr_blanco"/>
				<display:setProperty name="basic.show.header" value="true"/>

				<!--		Columna de Nombre de la carpeta	-->
				
				<bean:define id="carpetaValue"><b><bean:message key="carpetas.nombreCarpeta"/></b></bean:define>
				<display:column sortable="true" sortProperty="pathOde" style="valign:top;" class="tar7" title="${carpetaValue}" >
				<c:if test="${fila.despublicado==true}" >
				<html:link action="/ListarCarpetasCargas/ListarCarpetasRecuperarInforme?id=${form.id}&pathOde=${fila.pathOde}">
					${fila.pathOde}
					</html:link>
					</c:if>
					<c:if test="${fila.despublicado==false}" >
					${fila.pathOde}
					</c:if>
				</display:column>
				
				
				<!--		Columna de Nombre del lote		-->
				<bean:define id="numeroValue"><b><bean:message key="carpetas.numeroOdes"/></b></bean:define>
				<display:column sortable="true" sortProperty="numOdes" style="valign:top;" class="tar6" title="${numeroValue}" >
					${fila.numOdes}
				</display:column>
				
				<!--		Columna de Despublicar		-->
				
					<display:column style="valign:top;">
						<c:if test="${fila.despublicado==true}" >
							<input type="hidden" name="id" value="${form.id}"/>
							<input type="hidden" name="nombreCarpeta" value="${fila.pathOde}"/>
							<input type="hidden" name="entrada" value="Carpeta"/>
							<html:link action="/DespublicarCargasEjecutadas/DespublicarCargasEjecutadas.do?id=${form.id}&nombreCarpeta=${fila.pathOde}&entrada=Carpeta&nombreTarea=${form.nombreTarea}&nombreLote=${form.nombreLote}&fechaFin=${form.fechaFin}">
								<bean:message key="carga.Despublicar"/>
							</html:link>
						</c:if>
					</display:column>
				
				
		</display:table>
		
		
		<!-- </div> Fin de la caja tabla (SE CIERRA EN displaytag.properties) -->
		<!-- en caso de no tener registros de usuarios se tiene que poner el div		-->
			

		
		
		
			<fieldset class="tipo ">
			<!--		Boton de Volver		-->
				<input class="boton_125_de_2"  type="submit"  value="<bean:message key='nodos.volver'/>" />
			</fieldset>


	</div>
	</div>
	</form>

	<!--		Fin del formulario		-->
	
	
	</div>
</div>
</div>
</div>
	
	
	
	</div>
</div>


<!--		Fin de la capa plantilla_contenido		-->


</tiles:put>


</tiles:insert>