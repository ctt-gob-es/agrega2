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
<%@ include file="/es/pode/administracion/presentacion/gestionCargas/buscarCargas/formulario-por-zip-vars.jspf" %>


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

	<div class="interno_ficha">
	<div class="plantilla_contenido_pestanias">
	
	
	<div class="globo_gris" id="conmargen" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">

			<div class="globo_gris_03" id="tabla_in">
			<form id="listarCargasFormularioPorZipForm" action='<html:rewrite action="/BuscarCargas/FormularioPorZipAceptar"/>' method="post" >
			<h2><bean:message key="cargas.pestania.listado"/></h2>
			
			<div class="caja_tabla" id="ficha_dentro_gris"  >

		<!--		Inicio de la caja_tabla		-->
		
		<bean:define id="atributo" value="id" scope="page"/>
		<display:table name="${form.zipsEncontrados}" uid="fila" requestURI=""
		        export="false" pagesize="8" sort="list" style="border:1;width:100%;" class="administracion_tareas"
				cellpadding="0" cellspacing="0" summary="${summary}"
				decorator="es.pode.administracion.presentacion.MarcarModificado">
				
				<display:setProperty name="css.tr.odd" value="tr_gris"/>
				<display:setProperty name="css.tr.even" value="tr_blanco"/>
				<display:setProperty name="basic.show.header" value="true"/>

				
				<!--		Columna de Nombre de la tarea	-->
				<input type="hidden" name="id" value="${fila.id_mec}"/>
				<bean:define id="cargaValue"><b><bean:message key="cargas.cargasEjecutadas"/></b></bean:define>
				<display:column sortable="true" sortProperty="descripcion" style="valign:top;" class="tar" title="${cargaValue}" >
					${fila.nombreTarea}
				</display:column>
				
				<!--		Columna de Nombre del lote		-->
				<bean:define id="loteValue"><b><bean:message key="lote.cargasEjecutadas"/></b></bean:define>
				<display:column sortable="true" sortProperty="nombreLote" style="valign:top;" class="tar" title="${loteValue}" >
					${fila.nombreLote}
				</display:column>
				<!--		Columna de Descripcion		-->
				<bean:define id="descripcionValue"><b><bean:message key="descripcion.cargasEjecutadas"/></b></bean:define>
				<display:column sortable="true" sortProperty="descripcionTarea" style="valign:top;" class="tar" title="${descripcionValue}" >
					${fila.descripcionTarea}
				</display:column>

				<!--		Columna de Nombre del Zip		-->
				
				<bean:define id="zipValue"><b><bean:message key="zip.cargasEjecutadas"/></b></bean:define>
				<display:column sortable="true" sortProperty="nombreZip" title="${zipValue}"  style="valign:top;" >
					${fila.nombreZip}
				</display:column>
				
				
				<display:column style="valign:top;">
					<c:if test="${fila.despublicado==true}" >
							<input type="hidden" name="id" value="${fila.id}"/>
							<input type="hidden" name="nombreTarea" value="${fila.nombreTarea}"/>
							<input type="hidden" name="nombreZip" value="${fila.nombreZip}"/>
							<input type="hidden" name="id_mec" value="${fila.id_mec}"/>
							<input type="hidden" name="entrada" value="Lote"/>
							<html:link action="/DespublicarCargasEjecutadas/DespublicarCargasEjecutadas.do?id=${fila.id}&nombreTarea=${fila.nombreTarea}&entrada=Lote&nombreZip=${fila.nombreZip}&id_mec=${fila.id_mec }">
								<bean:message key="carga.Despublicar"/>
							</html:link>
						</c:if>
				</display:column>
				
		</display:table>
		</div>
		
		
		<!-- </div> Fin de la caja tabla (SE CIERRA EN displaytag.properties) -->
		<!-- en caso de no tener registros de usuarios se tiene que poner el div		-->
		<fieldset class="tipo ft_centrada">
			<input class="boton_125"  type="submit"  value="<bean:message key='usuarios.aceptar'/>" />	
		</fieldset>
</div>
	
	</div>

</div>
</div>
	
	
	</div>
</div>
</div>

<!--		Fin de la capa plantilla_contenido		-->

</tiles:put>


</tiles:insert>